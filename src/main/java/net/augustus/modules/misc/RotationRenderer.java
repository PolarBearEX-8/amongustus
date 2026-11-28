package net.augustus.modules.misc;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.events.listeners.RenderListener;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.modules.misc.helper.RecordedTickFrame;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.simulation.PlayerTickSnapshot;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.rotation.EntityBoundsUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.BoundedHistoryList;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.BooleanValue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RotationRenderer extends Module implements RenderListener, TickListener {
   private final BooleanValue recordedPredictions;
   private final BooleanValue recordedAura;
   private ArrayList playerPositions;
   private ArrayList targetPositions;
   public ArrayList recordedRotations;
   public double playbackIndex;
   private Vec3 originOffset;
   private long lastRenderTimeMs;
   private int lastFlooredTickIndex;

   public RotationRenderer() {
       super("RotationRenderer", "RotationsAnzeiger", Color.BLACK, Categorys.MISC);
      this.recordedPredictions = new BooleanValue(this, "Recorded Predictions", false);
      this.recordedAura = new BooleanValue(this, "Recorded Aura", false);
      this.playerPositions = new ArrayList();
      this.targetPositions = new ArrayList();
      this.recordedRotations = new ArrayList();
      this.playbackIndex = 0.0;
      this.lastFlooredTickIndex = 0;
   }

   public void onClientInit() {
      Augustus.getInstance().getModuleRegistry().getModules().remove(this);
      Augustus.getInstance().getModuleRegistry().getSortedModules().remove(this);
   }

   public void onEnable() {
      if (mc.theWorld == null) {
         this.toggle();
      } else {
         FileDialog fileDialog = new FileDialog((Frame)null);
         fileDialog.setVisible(true);
         File file = new File(fileDialog.getDirectory() + fileDialog.getFile());

         try {
            this.recordedRotations.clear();
            this.playerPositions.clear();
            this.targetPositions.clear();
            JsonArray array = this.loadRecordingJson(file);
            Gson gson = new Gson();
            ArrayList list = new ArrayList();

            for (int i = 0; i < array.size(); i++) {
               list.add(gson.fromJson(array.get(i).getAsJsonObject(), RecordedTickFrame.class));
            }

            for (RecordedTickFrame recordedTickFrame : (Iterable<RecordedTickFrame>)(list)) {
               this.recordedRotations.add(new double[]{(double)recordedTickFrame.yaw, (double)recordedTickFrame.pitch});
               this.playerPositions.add(new Vec3(recordedTickFrame.playerX, recordedTickFrame.playerY, recordedTickFrame.playerZ));
               this.targetPositions.add(new Vec3(recordedTickFrame.targetX, recordedTickFrame.targetY, recordedTickFrame.targetZ));
            }

            this.originOffset = ((Vec3)this.targetPositions.get(0)).addVector(0.0, -100.0, 0.0);
            this.playbackIndex = 0.0;
            this.sendChatMessage("Loaded " + this.recordedRotations.size() + " ticks!");
         } catch (Throwable e) {
            e.printStackTrace();
            this.toggle();
         }
      }
   }

   private JsonArray loadRecordingJson(File file) throws IOException {
      byte[] bytes = RotationRecorder.decompressData(Files.readAllBytes(file.toPath()));
      return JsonParser.parseString(new String(bytes, StandardCharsets.UTF_8)).getAsJsonArray();
   }

   public void onDisable() {
      this.playerPositions.clear();
      this.targetPositions.clear();
      this.recordedRotations.clear();
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(40);
   }

   public void onEarlyTick() {
      if (mc.currentScreen != null) {
         mc.thePlayer.motionY = 0.0;
      } else {
         mc.gameSettings.keyBindForward.setPressed(false);
         mc.gameSettings.keyBindBack.setPressed(false);
         mc.gameSettings.keyBindLeft.setPressed(false);
         mc.gameSettings.keyBindRight.setPressed(false);
         mc.gameSettings.keyBindSneak.setPressed(false);
         mc.gameSettings.keyBindJump.setPressed(false);
         double d = (double)(mc.thePlayer.rotationYaw + 90.0F);
         boolean flag = false;
         boolean isKeyDown = KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode());
         boolean isKeyDown2 = KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode());
         if (isKeyDown) {
            flag = true;
         }

         if (isKeyDown2) {
            d -= 180.0;
            flag = true;
         }

         if (KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode())) {
            if (!isKeyDown2) {
               d -= isKeyDown ? 45.0 : 90.0;
            } else {
               d += 45.0;
            }

            flag = true;
         }

         if (KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())) {
            if (!isKeyDown2) {
               d += isKeyDown ? 45.0 : 90.0;
            } else {
               d -= 45.0;
            }

            flag = true;
         }

         mc.thePlayer.motionY = 0.0;
         if (KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode())) {
            mc.thePlayer.motionY -= 0.05;
         }

         if (KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode())) {
            mc.thePlayer.motionY += 0.05;
         }

         if (flag) {
            double d2 = Math.cos(Math.toRadians(d)) * 0.8944271909999159 / 10.0;
            double d3 = Math.sin(Math.toRadians(d)) * 0.8944271909999159 / 10.0;
            mc.thePlayer.motionX = d2;
            mc.thePlayer.motionZ = d3;
         } else {
            mc.thePlayer.motionX = 0.0;
            mc.thePlayer.motionZ = 0.0;
         }
      }
   }

   public void onRender3D(float partialTicks) {
      long currentTimeMs = System.currentTimeMillis();
      if (KeyboardInputUtil.isKeyDown(203)) {
         this.playbackIndex = this.playbackIndex - (double)(currentTimeMs - this.lastRenderTimeMs) / 200.0;
      }

      if (KeyboardInputUtil.isKeyDown(205)) {
         this.playbackIndex = this.playbackIndex + (double)(currentTimeMs - this.lastRenderTimeMs) / 200.0;
      }

      this.playbackIndex = MathHelper.clamp_double(this.playbackIndex, 0.0, (double)(this.playerPositions.size() - 1));
      boolean flag = (double)this.lastFlooredTickIndex != Math.floor(this.playbackIndex);
      this.lastFlooredTickIndex = (int)Math.floor(this.playbackIndex);
      this.lastRenderTimeMs = currentTimeMs;
      RenderUtil.begin3DRender();
      GL11.glPushMatrix();
      GL11.glTranslated(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
      GlStateManager.color(0.0F, 0.0F, 0.0F, 1.0F);
      GL11.glBegin(7);
      GL11.glVertex3d(-100.0, 90.0, -100.0);
      GL11.glVertex3d(-100.0, 90.0, 100.0);
      GL11.glVertex3d(100.0, 90.0, 100.0);
      GL11.glVertex3d(100.0, 90.0, -100.0);
      GL11.glEnd();
      int n = 0;

      for (int i = Math.max((int)this.playbackIndex - 10, 0); (double)i < Math.min(Math.ceil(this.playbackIndex) - 1.0, (double)this.recordedRotations.size()); i++) {
         double[] doubles = (double[])this.recordedRotations.get(i);
         Vec3 lookVector = RotationUtil.getLookVector((float)doubles[1], (float)doubles[0]);
         Vec3 vec = ((Vec3)this.playerPositions.get(i)).subtract(this.originOffset).addVector(0.0, 1.62, 0.0);
         Vec3 vec2 = ((Vec3)this.targetPositions.get(i)).subtract(this.originOffset);
         Vec3 vec3 = vec.add(lookVector.scale(6.0));
         Vec3 vec4 = vec.add(lookVector.scale(0.1));
         AxisAlignedBB box = EntityBoundsUtil.createPlayerBoundingBox(vec2, true);
         float f2 = (float)n / 20.0F;
         if (n >= 18) {
            f2 *= 0.1F;
         }

         if (n == 17) {
            f2 *= 0.05F;
         }

         if (n < 17) {
            f2 *= 0.01F;
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, f2);
         GL11.glBegin(3);
         GL11.glVertex3d(vec.xCoord, vec.yCoord, vec.zCoord);
         GL11.glVertex3d(vec3.xCoord, vec3.yCoord, vec3.zCoord);
         GL11.glEnd();
         GlStateManager.color(1.0F, 0.0F, 0.0F, f2);
         GL11.glBegin(3);
         GL11.glVertex3d(vec.xCoord, vec.yCoord, vec.zCoord);
         GL11.glVertex3d(vec4.xCoord, vec4.yCoord, vec4.zCoord);
         GL11.glEnd();
         GlStateManager.color(1.0F, 1.0F, 1.0F, f2);
         RenderUtil.drawOutlinedBoundingBox(box);
         n++;
      }

      int n2 = MathHelper.clamp_int((int)(Math.ceil(this.playbackIndex) - 1.0), 0, this.recordedRotations.size());
      int n3 = (int)Math.min(Math.ceil(this.playbackIndex), (double)this.recordedRotations.size());
      double d = this.playbackIndex % 1.0;
      Vec3 vec5 = ((Vec3)this.targetPositions.get(n2)).lerp((Vec3)this.targetPositions.get(n3), d).subtract(this.originOffset);
      AxisAlignedBB box2 = EntityBoundsUtil.createPlayerBoundingBox(vec5, true);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.drawOutlinedBoundingBox(AxisAlignedBB.fromCenter(vec5.addVector(0.0, 1.62, 0.0), 0.05));
      GlStateManager.color(0.0F, 1.0F, 0.0F, 1.0F);
      RenderUtil.drawOutlinedBoundingBox(AxisAlignedBB.fromCenter(vec5.addVector(0.0, 1.62, 0.0), 0.4));
      double[] doubles2 = (double[])this.recordedRotations.get(n2);
      double[] doubles3 = (double[])this.recordedRotations.get(n3);
      double[] doubles4 = null;
      double d2 = 0.0 / 0.0;
      double d3 = doubles2[0];

      for (int j = n2; j < this.recordedRotations.size(); j++) {
         double[] doubles5 = (double[])this.recordedRotations.get(j);
         if (doubles4 != null) {
            double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double(doubles4[0]), MathHelper.wrapAngleTo180_double(doubles5[0]));
            if (Math.abs(shortestSignedAngleDelta) <= 0.15) {
               d3 = doubles5[0];
               break;
            }

            if (Double.isFinite(d2) && Math.signum(d2) != Math.signum(shortestSignedAngleDelta)) {
               d3 = MathHelper.wrapAngleTo180_double(doubles5[0] + d2 / (shortestSignedAngleDelta - d2) * shortestSignedAngleDelta);
               break;
            }

            d2 = shortestSignedAngleDelta;
         }

         doubles4 = doubles5;
      }

      BoundedHistoryList boundedHistoryList = new BoundedHistoryList(10);
      BoundedHistoryList boundedHistoryList2 = new BoundedHistoryList(10);

      for (int k = Math.max((int)this.playbackIndex - 10, 0); (double)k < Math.min(Math.ceil(this.playbackIndex) - 1.0, (double)this.playerPositions.size()); k++) {
         boundedHistoryList.add(new PlayerTickSnapshot((Vec3)this.playerPositions.get(k), k));
      }

      for (int m = Math.max((int)this.playbackIndex - 10, 0); (double)m < Math.min(Math.ceil(this.playbackIndex) - 1.0, (double)this.targetPositions.size()); m++) {
         boundedHistoryList2.add(this.targetPositions.get(m));
      }

      if (boundedHistoryList.isFull()) {
         double d5 = this.predictLinearRegressionAngle(boundedHistoryList, boundedHistoryList2, (byte)0, flag);
         double d6 = this.predictLinearRegressionAngle(boundedHistoryList, boundedHistoryList2, (byte)1, flag);
         Vec3 vec6 = vec5.addVector(0.0, 1.62, 0.0);
         double[] doubles6 = new double[]{d5, doubles2[1]};
         double[] doubles7 = new double[]{d5, doubles3[1]};
         float f3 = 0.0F;
         float f4 = 1.0F;
         this.renderInterpolatedLookRay(vec6, doubles6, doubles7, d, n2, n3, 1.0F, f4, f3);
         vec6 = vec5.addVector(0.0, 1.62, 0.0);
         doubles6 = new double[]{d6, doubles2[1]};
         doubles7 = new double[]{d6, doubles3[1]};
         float f6 = 0.0F;
         float f7 = 1.0F;
         this.renderInterpolatedLookRay(vec6, doubles6, doubles7, d, n2, n3, 1.0F, f7, f6);
      }

      Vec3 vec9 = vec5.addVector(0.0, 1.62, 0.0);
      float f9 = 1.0F;
      float f10 = 1.0F;
      this.renderInterpolatedLookRay(vec9, doubles2, doubles3, d, n2, n3, 1.0F, f10, f9);
      vec9 = vec5.addVector(0.0, 1.62, 0.0);
      double[] doubles12 = new double[]{d3, doubles2[1]};
      double[] doubles13 = new double[]{d3, doubles3[1]};
      float f12 = 0.4F;
      float f13 = 0.5F;
      this.renderInterpolatedLookRay(vec9, doubles12, doubles13, d, n2, n3, 0.8F, f13, f12);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.drawOutlinedBoundingBox(box2);
      RenderUtil.end3DRender();
      GL11.glPopMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private double predictLinearRegressionAngle(BoundedHistoryList boundedHistoryList, BoundedHistoryList boundedHistoryList2, int n, boolean flag) {
      DoubleSampleWindow doubleSampleWindow = new DoubleSampleWindow(4);
      double d = 0.0 / 0.0;
      double d2 = 0.0;
      double d3 = 0.0;
      int n2 = 0;

      for (int i = 3; i > 0; i--) {
         PlayerTickSnapshot fromEnd = (PlayerTickSnapshot)boundedHistoryList.getFromEnd(i + 4);
         double[] yawRange = HitboxAngleUtil.getYawRange(fromEnd.position, EntityBoundsUtil.createPlayerBoundingBox((Vec3)boundedHistoryList2.getFromEnd(i + 4), true));
         if (Double.isNaN(d)) {
            d = MathHelper.wrapAngleTo180_double(yawRange[n]);
            doubleSampleWindow.add(0.0);
            d2 = d;
            n2 = ((PlayerTickSnapshot)boundedHistoryList.getFromEnd((byte)0)).ticksExisted - fromEnd.ticksExisted;
         } else {
            double d5 = EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double(d2), MathHelper.wrapAngleTo180_double(yawRange[n])) + d3;
            doubleSampleWindow.add(d5);
            d2 = yawRange[n];
            d3 = d5;
         }
      }

      double[] doubles = doubleSampleWindow.linearRegression();
      double d6 = doubles[0] * (double)n2 + doubles[1];
      double d7 = doubles[0] * (double)(n2 + 1) + doubles[1];
      double d8 = doubleSampleWindow.mean();
      double d9 = 0.0;
      double d10 = 0.0;

      for (int j = 0; j < doubleSampleWindow.getSamples().size(); j++) {
         double d11 = (Double)doubleSampleWindow.getSamples().get(j);
         d9 += Math.pow(d11 - d8, 2.0);
         d10 += Math.pow(d11 - (doubles[0] * (double)j + doubles[1]), 2.0);
      }

      Vec3 position2 = ((PlayerTickSnapshot)boundedHistoryList.getFromEnd((byte)0)).position;
      Vec3 fromEnd3 = (Vec3)boundedHistoryList2.getFromEnd((byte)0);
      double d12 = Math.abs(
         EntityBoundsUtil.getShortestSignedAngleDelta(
            MathHelper.wrapAngleTo180_double(HitboxAngleUtil.getYawRange(position2, EntityBoundsUtil.createPlayerBoundingBox(fromEnd3, true))[n]), MathHelper.wrapAngleTo180_double(d6 + d)
         )
      );
      position2 = ((PlayerTickSnapshot)boundedHistoryList.getFromEnd((byte)0)).position;
      fromEnd3 = (Vec3)boundedHistoryList2.getFromEnd((byte)0);
      double d13 = Math.abs(
         EntityBoundsUtil.getShortestSignedAngleDelta(
            MathHelper.wrapAngleTo180_double(HitboxAngleUtil.getYawRange(position2, EntityBoundsUtil.createPlayerBoundingBox(fromEnd3, true))[n]), MathHelper.wrapAngleTo180_double(d7 + d)
         )
      );
      if (flag) {
         String str = "#"
            + this.playbackIndex
            + " delta="
            + MathStatisticsUtil.formatRounded(d6, 3)
            + " r^2="
            + MathStatisticsUtil.formatRounded(1.0 - d10 / d9, 7)
            + " fd="
            + MathStatisticsUtil.formatRounded(d12, 4)
            + " fd2="
            + MathStatisticsUtil.formatRounded(d13, 4)
            + " "
            + Arrays.toString(doubleSampleWindow.getSamples().toArray())
            + " x="
            + n2
            + " func="
            + Arrays.toString(doubles);
         this.sendChatMessage(str);
      }

      return MathHelper.wrapAngleTo180_double(d6 + d);
   }

   private void renderInterpolatedLookRay(Vec3 vec, double[] doubles, double[] doubles2, double d, int n, int n2, float f, float f2, float f3) {
      double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double(doubles[0]), MathHelper.wrapAngleTo180_double(doubles2[0]));
      double d3 = doubles2[1] - doubles[1];
      Vec3 lookVector = RotationUtil.getLookVector((float)(doubles[1] + d3 * d), (float)(doubles[0] + shortestSignedAngleDelta * d));
      Vec3 vec2 = ((Vec3)this.playerPositions.get(n)).lerp((Vec3)this.playerPositions.get(n2), d).subtract(this.originOffset).addVector(0.0, 1.62, 0.0);
      Vec3 vec3 = vec2.add(lookVector.scale(6.0));
      Vec3 vec4 = vec2.add(lookVector.scale(0.1));
      GlStateManager.color(f, f2, f3, 1.0F);
      GL11.glBegin(3);
      GL11.glVertex3d(vec2.xCoord, vec2.yCoord, vec2.zCoord);
      GL11.glVertex3d(vec3.xCoord, vec3.yCoord, vec3.zCoord);
      GL11.glEnd();
      GlStateManager.color(1.0F, 0.0F, 0.0F, 1.0F);
      GL11.glBegin(3);
      GL11.glVertex3d(vec2.xCoord, vec2.yCoord, vec2.zCoord);
      GL11.glVertex3d(vec4.xCoord, vec4.yCoord, vec4.zCoord);
      GL11.glEnd();
      GlStateManager.color(0.0F, 0.0F, 1.0F, 1.0F);
   }

}
