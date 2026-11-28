package net.augustus.modules.misc;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.rotation.BezierRotationEngine;
import net.augustus.utils.file.ClientPaths;
import net.augustus.events.EventClick;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.events.EventMouseMove;
import net.augustus.modules.misc.helper.RotationRecorderExclusionStrategy;
import net.augustus.Augustus;
import net.augustus.settings.options.RotationRecorderTargetOption;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.Vec3;

public class RotationRecorder extends Module implements TickListener, ClickListener, MouseMoveListener, PacketListener {
   private final BooleanValue createNewDatasetIfTargetIsNull;
   private final BooleanValue frameRecording;
   private final BooleanValue recordPredictions;
   private final BooleanValue recordAura;
   private final BooleanValue killAuraTarget;
   private final DoubleValue searchReach;
   private final BooleanValue onlyOnClick;
   private final DoubleValue yawFov;
   private final DoubleValue pitchFov;
   private final MultiOptionSetting targets;
   private final TimeHelper timeHelper;
   private DataOutputStream outputStream;
   private int savedFramerateLimit;
   private BezierRotationEngine bezierRotationEngine;
   private Rotation lastRotation;
   private HashSet recordedEntityIds;
   private JsonArray recordedFrames;
   private Gson gson;
   private boolean velocityReceived;

   public RotationRecorder() {
       super("RotationRecorder", "DrehungsAufzeichner", new Color(252, 186, 3), Categorys.MISC);
      this.createNewDatasetIfTargetIsNull = new BooleanValue(this, "Create new dataset if target is null", true);
      this.frameRecording = new BooleanValue(this, "FrameRecording", true);
      this.recordPredictions = (BooleanValue)new BooleanValue(this, "Record Predictions", false).withVisibility(() -> this.isRecordPredictionsVisible());
      this.recordAura = (BooleanValue)new BooleanValue(this, "Record Aura", false).withVisibility(() -> this.isRecordAuraVisible());
      this.killAuraTarget = new BooleanValue(this, "KillAuraTarget", true);
      this.searchReach = new DoubleValue(this, "SearchReach", 7.0, 3.0, 10.0, 1).visibleWhen(() -> this.isSearchReachVisible());
      BooleanValue booleanValue3 = new BooleanValue(this, "OnlyOnClick", true);
      SettingVisibilityCondition condition4 = () -> {
         return !this.killAuraTarget.getValue();
      };
      this.onlyOnClick = (BooleanValue)booleanValue3.withVisibility(condition4);
      this.yawFov = new DoubleValue(this, "YawFov", 90.0, 0.0, 180.0, 0).visibleWhen(() -> this.isYawFovVisible());
      this.pitchFov = new DoubleValue(this, "PitchFov", 90.0, 0.0, 180.0, 0).visibleWhen(() -> this.isPitchFovVisible());
      MultiOptionSetting multiOptionSetting = new MultiOptionSetting(this, "Targets", RotationRecorderTargetOption.values());
      SettingVisibilityCondition condition7 = () -> {
         return !this.killAuraTarget.getValue();
      };
      this.targets = multiOptionSetting.visibleWhen(condition7);
      this.timeHelper = new TimeHelper();
      this.outputStream = null;
      this.savedFramerateLimit = 0;
      this.bezierRotationEngine = new BezierRotationEngine();
      this.recordedEntityIds = new HashSet();
      this.recordedFrames = null;
      RotationRecorderExclusionStrategy strategy = new RotationRecorderExclusionStrategy(this);
      this.gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy[]{strategy}).create();
   }

   @Override
   public void onClientInit() {
      Augustus.getInstance().getModuleRegistry().getModules().remove(this);
      Augustus.getInstance().getModuleRegistry().getSortedModules().remove(this);
   }

   @Override
   public void onEnable() {
      this.finishAndCloseRecording();
      this.savedFramerateLimit = mc.gameSettings.limitFramerate;
      mc.gameSettings.limitFramerate = 60;
   }

   @Override
   public void onDisable() {
      this.finishAndCloseRecording();
      mc.gameSettings.limitFramerate = this.savedFramerateLimit;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(19);
      this.toggleEventSubscription(26);
   }

   @Override
   public void onTick() {
      if (!this.frameRecording.getValue()) {
         this.recordTickOrFrame(this.frameRecording.getValue());
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (mc.gameSettings.keyBindAttack.isKeyDown() || mc.gameSettings.keyBindAttack.getPressTime() > 0) {
         this.timeHelper.reset();
      }
   }

   @Override
   public void onMouseMove(EventMouseMove event) {
   }

   @Override
   public void onPostMouseMove() {
      if (this.frameRecording.getValue()) {
         this.recordTickOrFrame(this.frameRecording.getValue());
      }
   }

   private void recordTickOrFrame(boolean flag) {
      EntityLivingBase entity = this.killAuraTarget.getValue() ? ClientManagers.moduleManager.killAura.target : this.findTargetEntity();
      if (entity == null) {
         if (this.createNewDatasetIfTargetIsNull.getValue() && this.outputStream != null) {
            this.finishAndCloseRecording();
         }
      } else {
         if (this.outputStream == null) {
            this.openNewRecordingFile();
         }

         if (flag) {
            this.recordRenderFrame(entity);
         } else {
            this.recordTickSnapshot(entity);
         }
      }
   }

   private void writeCompressedFrames() {

      try {
         this.outputStream.write(compressData(this.recordedFrames.toString().getBytes(StandardCharsets.UTF_8)));
         this.outputStream.flush();
         this.recordedFrames = null;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private static byte[] compressData(byte[] bytes) {
      Deflater deflater = new Deflater(9, true);
      deflater.setInput(bytes);
      deflater.finish();
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      byte[] bytes2 = new byte[1024];

      while (!deflater.finished()) {
         byteArrayOutputStream.write(bytes2, 0, deflater.deflate(bytes2));
      }

      return byteArrayOutputStream.toByteArray();
   }

   private void closeOutputStream() {
      try {
         this.outputStream.close();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void recordRenderFrame(EntityLivingBase entity) {
      JsonObject json = new JsonObject();
      json.addProperty("ticksExisted", mc.thePlayer.ticksExisted);
      json.addProperty("yaw", mc.thePlayer.rotationYaw);
      json.addProperty("pitch", mc.thePlayer.rotationPitch);
      Vec3 interpolatedPos = mc.thePlayer.getInterpolatedPos(mc.getTimer().renderPartialTicks);
      json.addProperty("playerX", interpolatedPos.xCoord);
      json.addProperty("playerY", interpolatedPos.yCoord);
      json.addProperty("playerZ", interpolatedPos.zCoord);
      interpolatedPos = entity.getInterpolatedPos(mc.getTimer().renderPartialTicks);
      json.addProperty("targetX", interpolatedPos.xCoord);
      json.addProperty("targetY", interpolatedPos.yCoord);
      json.addProperty("targetZ", interpolatedPos.zCoord);
      this.recordedFrames.add(json);
   }

   private void recordTickSnapshot(EntityLivingBase entity) {
      if (entity.motionSnapshotHistory.size() != 0) {
         JsonObject json = new JsonObject();
         json.addProperty("ticksExisted", mc.thePlayer.ticksExisted);
         json.addProperty("yaw", mc.thePlayer.rotationYaw);
         json.addProperty("pitch", mc.thePlayer.rotationPitch);
         json.addProperty("playerX", mc.thePlayer.posX);
         json.addProperty("playerY", mc.thePlayer.posY);
         json.addProperty("playerZ", mc.thePlayer.posZ);
         json.addProperty("targetX", entity.posX);
         json.addProperty("targetY", entity.posY);
         json.addProperty("targetZ", entity.posZ);
         json.addProperty("applyVelocity", this.velocityReceived);
         json.addProperty("AI_MOVESPEED", mc.thePlayer.getAIMoveSpeed());
         json.addProperty("jumpMovementFactor", mc.thePlayer.jumpMovementFactor);
         json.addProperty("motionX", mc.thePlayer.motionX);
         json.addProperty("motionY", mc.thePlayer.motionY);
         json.addProperty("motionZ", mc.thePlayer.motionZ);
         json.addProperty("isSprinting", mc.thePlayer.isSprinting());
         json.add("entityState", this.gson.toJsonTree(entity.motionSnapshotHistory.getFromEnd((byte)0)));
         json.add("movementInput", this.gson.toJsonTree(mc.thePlayer.movementInput));
         json.add("playerState", this.gson.toJsonTree(mc.thePlayer.extraSnapshotHistory.getFromEnd((byte)0)));
         json.add("targetState", this.gson.toJsonTree(entity.positionHistory.getFromEnd((byte)0)));
         this.recordedFrames.add(json);
         this.velocityReceived = false;
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (event.getPacket() instanceof S12PacketEntityVelocity) {
         if (((S12PacketEntityVelocity)event.getPacket()).getEntityID() == mc.thePlayer.getEntityId()) {
            this.velocityReceived = true;
         }
      } else if (event.getPacket() instanceof S27PacketExplosion) {
         S27PacketExplosion packet2 = (S27PacketExplosion)event.getPacket();
         if (Math.abs(packet2.getMotionX()) > 0.0F && Math.abs(packet2.getMotionY()) > 0.0F) {
            this.velocityReceived = true;
         }
      }
   }

   private void openNewRecordingFile() {
      this.recordedFrames = new JsonArray();
      this.velocityReceived = false;
      String str = ClientPaths.CLIENT_DIR + File.separator + "rotation";
      ClientFileUtil.ensureDirectory(str);

      try {
         File file = new File(str + File.separator + "data" + System.currentTimeMillis());
         file.createNewFile();
         this.outputStream = new DataOutputStream(Files.newOutputStream(file.toPath()));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void finishAndCloseRecording() {
      if (this.outputStream != null) {
         this.writeCompressedFrames();
         this.closeOutputStream();
         this.outputStream = null;
         this.recordedEntityIds.clear();
      }
   }

   private EntityLivingBase findTargetEntity() {
      if (mc.isNotInGui()) {
         if (!this.timeHelper.reached(1000L) || !this.onlyOnClick.getValue()) {
            Stream stream = mc.theWorld.loadedEntityList.stream();
            stream = stream.filter(
               obj -> {
                  if (obj instanceof EntityLivingBase) {
                     boolean isSelected = RotationRecorderTargetOption.PLAYER.isSelected();
                     boolean isSelected2 = RotationRecorderTargetOption.MOB.isSelected();
                     boolean isSelected3 = RotationRecorderTargetOption.ANIMAL.isSelected();
                     boolean isSelected4 = RotationRecorderTargetOption.VILLAGER.isSelected();
                     boolean isSelected5 = RotationRecorderTargetOption.ARMOR_STAND.isSelected();
                     boolean isSelected6 = RotationRecorderTargetOption.INVISIBLE.isSelected();
                     boolean isSelected7 = RotationRecorderTargetOption.DEAD.isSelected();
                     if (CombatTargetUtil.isValidTarget((Entity)obj, isSelected, isSelected2, isSelected3, isSelected4, isSelected5, isSelected6, true, true, true, isSelected7, true)) {
                        float realYaw = Augustus.getInstance().getYawPitchHelper().realYaw;
                        float realPitch = Augustus.getInstance().getYawPitchHelper().realPitch;
                        float[] floats = RotationUtil.getAngleDiffToEntityNearestPoint((Entity)obj, realYaw, realPitch);
                        return !(obj instanceof EntityPlayerSP)
                           && (double)mc.thePlayer.getDistanceToEntityBox((Entity)obj) < this.searchReach.getValue()
                           && (double)Math.abs(floats[0]) <= this.yawFov.getValue()
                           && (double)Math.abs(floats[1]) <= this.pitchFov.getValue();
                     }
                  }

                  return false;
               }
            );
            Object[] array = stream.sorted(
                  Comparator.comparingDouble(
                     obj2 -> {
                        if (obj2 instanceof EntityLivingBase) {
                           float[] angleDiffToPos = RotationUtil.getAngleDiffToPos(
                              ((Entity)obj2).posX,
                              ((Entity)obj2).posY + (double)((Entity)obj2).getEyeHeight(),
                              ((Entity)obj2).posZ,
                              Augustus.getInstance().getYawPitchHelper().realYaw,
                              Augustus.getInstance().getYawPitchHelper().realPitch
                           );
                           return Math.sqrt((double)(angleDiffToPos[0] * angleDiffToPos[0] + angleDiffToPos[1] * angleDiffToPos[1]));
                        } else {
                           return 1000.0;
                        }
                     }
                  )
               )
               .toArray();
            if (array.length == 0) {
               return null;
            }

            return (EntityLivingBase)array[0];
         }
      }

      return null;
   }

   public static byte[] decompressData(byte[] bytes) {

      try {
         Inflater inflater = new Inflater(true);
         inflater.setInput(bytes);
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         byte[] bytes2 = new byte[1024];

         while (!inflater.finished()) {
            byteArrayOutputStream.write(bytes2, 0, inflater.inflate(bytes2));
         }

         inflater.end();
         return byteArrayOutputStream.toByteArray();
      } catch (DataFormatException e) {
         return null;
      }
   }

   private boolean isPitchFovVisible() {
      return !this.killAuraTarget.getValue();
   }

   private boolean isYawFovVisible() {
      return !this.killAuraTarget.getValue();
   }

   private boolean isSearchReachVisible() {
      return !this.killAuraTarget.getValue();
   }

   private boolean isRecordAuraVisible() {
      return !this.frameRecording.getValue();
   }

   private boolean isRecordPredictionsVisible() {
      return !this.frameRecording.getValue();
   }

}
