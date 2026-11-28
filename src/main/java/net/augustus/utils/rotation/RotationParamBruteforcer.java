package net.augustus.utils.rotation;

import net.augustus.Augustus;
import net.augustus.config.ClipboardConfigCodec;
import net.augustus.events.EventAIMoveSpeed;
import net.augustus.events.EventManager;
import net.augustus.events.listeners.AIMoveSpeedListener;
import net.augustus.modules.misc.helper.RecordedTickFrame;
import net.augustus.modules.modes.killaura.unoise3.UNoise3CircleAimMode;
import net.augustus.modules.modes.killaura.unoise3.UNoise3RotationEngine;
import net.augustus.modules.modes.killaura.unoise3.UNoise3SettingContext;
import net.augustus.settings.Setting;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.simulation.CachedPlayerPredictionProvider;
import net.augustus.utils.simulation.CachedTargetPredictionProvider;
import net.augustus.utils.simulation.EntityMotionSnapshot;
import net.augustus.utils.simulation.PlayerTickSnapshot;
import net.augustus.utils.simulation.SimulatedPlayerEntity;
import net.augustus.utils.simulation.SimulatedTargetEntity;
import net.augustus.utils.simulation.TrajectoryPredictionUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import net.augustus.modules.misc.DummyAura;
import net.augustus.modules.misc.RotationRecorder;
import net.augustus.settings.SettingGroup;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RotationParamBruteforcer implements AIMoveSpeedListener {
   private HashMap playerPredictionCache;
   private ArrayList recordedFrames;
   private HashMap targetPredictionCache;
   private float currentAiMoveSpeed;

   private JsonArray loadRecordedTicks(String str) throws IOException {
      byte[] bytes = RotationRecorder.decompressData(Files.readAllBytes(new File(str).toPath()));
      return JsonParser.parseString(new String(bytes, StandardCharsets.UTF_8)).getAsJsonArray();
   }

   @Override
   public void onAIMoveSpeed(EventAIMoveSpeed event) {
      event.setMoveSpeed(this.currentAiMoveSpeed);
   }

   public static HashMap getTargetPredictionCache(RotationParamBruteforcer rotationParamBruteforcer) {
      return rotationParamBruteforcer.targetPredictionCache;
   }

   public static HashMap getPlayerPredictionCache(RotationParamBruteforcer rotationParamBruteforcer) {
      return rotationParamBruteforcer.playerPredictionCache;
   }

   public RotationParamBruteforcer() throws IOException {
       super();
      this.recordedFrames = new ArrayList();
      this.targetPredictionCache = new HashMap();
      this.playerPredictionCache = new HashMap();
      EventManager.getEventBus().subscribe(0, this);
      JsonArray array = this.loadRecordedTicks("C:\\Users\\tobia\\AppData\\Roaming\\ElectricLauncher\\data\\data\\augustus\\augustus\\rotation\\standingtargetlegit");
      Gson gson = new Gson();

      for (int i = 0; i < array.size(); i++) {
         JsonObject asJsonObject = array.get(i).getAsJsonObject();
         this.recordedFrames.add(gson.fromJson(asJsonObject, RecordedTickFrame.class));
      }

      ChatUtil.sendChat("Read " + array.size() + " ticks");
      double d = 10000.0;
      DoubleSampleWindow doubleSampleWindow = null;
      DummyAura dummyAura = null;

      for (int j = 0; j < 100; j++) {
         Object[] array2 = this.evaluateRandomConfig();
         DoubleSampleWindow doubleSampleWindow2 = (DoubleSampleWindow)array2[0];
         DummyAura dummyAura2 = (DummyAura)array2[1];
         double d2 = doubleSampleWindow2.mean();
         System.out.println("#" + j + " " + d2 + " " + doubleSampleWindow2.min() + " " + doubleSampleWindow2.max());
         if (d > d2) {
            d = d2;
            doubleSampleWindow = doubleSampleWindow2;
            dummyAura = dummyAura2;
         }
      }

      ClipboardConfigCodec.exportModule(dummyAura);
      ChatUtil.sendChat(
         "avg=" + MathStatisticsUtil.roundToDecimalPlaces(doubleSampleWindow.mean(), 4) + " min=" + MathStatisticsUtil.roundToDecimalPlaces(doubleSampleWindow.min(), 4) + " max=" + MathStatisticsUtil.roundToDecimalPlaces(doubleSampleWindow.max(), 4)
      );
   }

   private Object[] evaluateRandomConfig() {
      Minecraft minecraft = Minecraft.getMinecraft();
      SimulatedPlayerEntity simulatedPlayerEntity = new SimulatedPlayerEntity(minecraft, minecraft.theWorld, minecraft.getNetHandler(), minecraft.thePlayer.getStatFileWriter());
      SimulatedTargetEntity simulatedTargetEntity = new SimulatedTargetEntity(minecraft.theWorld);
      DummyAura dummyAura = new DummyAura();
      SettingGroup randomizeGroup = new SettingGroup(dummyAura, "Randomize");
      UNoise3SettingContext uNoise3SettingContext = new UNoise3SettingContext(dummyAura, randomizeGroup);
      UNoise3RotationEngine uNoise3RotationEngine = new UNoise3RotationEngine(uNoise3SettingContext, () -> true);

      for (Setting setting : (Iterable<Setting>)(Augustus.getInstance().getSettingManager().getSettings())) {
         if (setting.getParentModule() == dummyAura) {
            setting.randomize();
         }
      }

      TrajectoryPredictionUtil.playerPredictionProvider = new CachedPlayerPredictionProvider(this, simulatedPlayerEntity);
      TrajectoryPredictionUtil.targetPredictionProvider = new CachedTargetPredictionProvider(this, simulatedPlayerEntity, simulatedTargetEntity);
      uNoise3RotationEngine.suppressTrailLogging = true;
      RecordedTickFrame recordedTickFrame = (RecordedTickFrame)this.recordedFrames.get(0);
      float f = MathHelper.wrapAngleTo180_float(recordedTickFrame.yaw);
      float pitch = recordedTickFrame.pitch;
      simulatedPlayerEntity.prevReportedYaw = simulatedPlayerEntity.lastReportedYaw = f;
      simulatedPlayerEntity.prevReportedPitch = simulatedPlayerEntity.lastReportedPitch = pitch;
      DoubleSampleWindow doubleSampleWindow = new DoubleSampleWindow(this.recordedFrames.size());
      RecordedTickFrame recordedTickFrame2 = (RecordedTickFrame)this.recordedFrames.get(0);

      for (RecordedTickFrame recordedTickFrame3 : (Iterable<RecordedTickFrame>)(this.recordedFrames)) {
         simulatedPlayerEntity.ticksExisted = recordedTickFrame3.ticksExisted;
         simulatedPlayerEntity.onGround = recordedTickFrame3.playerState.onGround;
         simulatedPlayerEntity.motionX = recordedTickFrame3.motionX;
         simulatedPlayerEntity.motionY = recordedTickFrame3.motionY;
         simulatedPlayerEntity.motionZ = recordedTickFrame3.motionZ;
         simulatedPlayerEntity.jumpMovementFactor = recordedTickFrame3.jumpMovementFactor;
         simulatedPlayerEntity.setSprinting(recordedTickFrame3.isSprinting);
         simulatedPlayerEntity.setPosition(recordedTickFrame3.playerX, recordedTickFrame3.playerY, recordedTickFrame3.playerZ);
         this.currentAiMoveSpeed = recordedTickFrame3.AI_MOVESPEED;
         simulatedTargetEntity.posX = recordedTickFrame3.targetX;
         simulatedTargetEntity.posY = recordedTickFrame3.targetY;
         simulatedTargetEntity.posZ = recordedTickFrame3.targetZ;
         PlayerTickSnapshot playerState = recordedTickFrame3.playerState;
         simulatedPlayerEntity.extraSnapshotHistory.add(playerState);
         simulatedPlayerEntity.movementInput = recordedTickFrame3.movementInput;
         Vec3 targetState = recordedTickFrame3.targetState;
         simulatedTargetEntity.positionHistory.add(targetState);
         EntityMotionSnapshot entityState = recordedTickFrame3.entityState;
         simulatedTargetEntity.motionSnapshotHistory.add(entityState);
         if (recordedTickFrame3.applyVelocity) {
            double d = (double)(simulatedPlayerEntity.ticksExisted + 1);
            uNoise3RotationEngine.uNoise3PredictionScheduler.uNoise3MovementFilter.inaccuracyWindow.add(d);
         }

         double d2 = 0.0;
         Rotation rotation = uNoise3RotationEngine.computeRotation(simulatedTargetEntity, simulatedPlayerEntity, f, pitch, 0.0, d2, (Vec3)null, (Vec3)null);
         float f2 = f;
         float f3 = pitch;
         f = rotation.yaw;
         pitch = rotation.pitch;
         double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta((double)f2, (double)f);
         double d5 = (double)(pitch - f3);
         double d6 = EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double((double)recordedTickFrame2.yaw), MathHelper.wrapAngleTo180_double((double)recordedTickFrame3.yaw));
         double d7 = (double)(recordedTickFrame3.pitch - recordedTickFrame2.pitch);
         double d8 = shortestSignedAngleDelta - d6;
         double d9 = d5 - d7;
         doubleSampleWindow.add(Math.sqrt(d8 * d8 + d9 * d9));
      }

      ArrayList list = new ArrayList();

      for (Setting setting2 : (Iterable<Setting>)(Augustus.getInstance().getSettingManager().getSettings())) {
         if (setting2.getParentModule() == dummyAura) {
            list.add(setting2);
         }
      }

      for (Setting setting3 : (Iterable<Setting>)(list)) {
         Augustus.getInstance().getSettingManager().getSettings().remove(setting3);
      }

      UNoise3CircleAimMode.clearInstances();
      return new Object[]{doubleSampleWindow, dummyAura};
   }





}
