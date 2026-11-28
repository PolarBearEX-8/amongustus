package wdl.gui;

import net.minecraft.entity.player.EntityPlayer;
import wdl.WDL;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public abstract class GuiTurningCameraBase extends GuiScreen {
   private float yaw;
   private float yawNextTick;
   private int oldCameraMode;
   private boolean oldHideHud;
   private boolean oldShowDebug;
   private EntityPlayer.EnumChatVisibility oldChatVisibility;
   private EntityPlayerSP cam;
   private Entity oldRenderViewEntity;
   private boolean initializedCamera = false;
   private static final float ROTATION_SPEED = 1.0F;
   private static final float ROTATION_VARIANCE = 0.7F;

   @Override
   public void initGui() {
      if (!this.initializedCamera) {
         this.cam = new EntityPlayerSP(WDL.mc, WDL.worldClient, WDL.thePlayer.sendQueue, WDL.thePlayer.getStatFileWriter());
         this.cam
            .setLocationAndAngles(
               WDL.thePlayer.posX, WDL.thePlayer.posY - WDL.thePlayer.getYOffset(), WDL.thePlayer.posZ, WDL.thePlayer.rotationYaw, 0.0F
            );
         this.yaw = this.yawNextTick = WDL.thePlayer.rotationYaw;
         this.oldShowDebug = WDL.mc.gameSettings.showDebugProfilerChart;
         this.oldChatVisibility = WDL.mc.gameSettings.chatVisibility;
         WDL.mc.gameSettings.showDebugProfilerChart = false;
         WDL.mc.gameSettings.chatVisibility = EntityPlayer.EnumChatVisibility.HIDDEN;
         this.oldRenderViewEntity = WDL.mc.getRenderViewEntity();
         this.initializedCamera = true;
      }

      EntityPlayerSP cam2 = this.cam;
      WDL.mc.setRenderViewEntity((Entity)cam2);
   }

   private double truncateDistanceIfBlockInWay(double d, double d2, double d3) {
      Vec3 vec = WDL.thePlayer.getPositionVector().addVector(0.0, (double)WDL.thePlayer.getEyeHeight(), 0.0);
      Vec3 vec2 = new Vec3(WDL.thePlayer.posX - d3 * d, WDL.thePlayer.posY + (double)WDL.thePlayer.getEyeHeight(), WDL.thePlayer.posZ + d2);

      for (int i = 0; i < 9; i++) {
         float f = (i & 1) != 0 ? -0.1F : 0.1F;
         float f2 = (i & 2) != 0 ? -0.1F : 0.1F;
         float f3 = (i & 4) != 0 ? -0.1F : 0.1F;
         if (i == 8) {
            f = 0.0F;
            f2 = 0.0F;
            f3 = 0.0F;
         }

         Vec3 vec3 = vec.addVector((double)f, (double)f2, (double)f3);
         Vec3 vec4 = vec2.addVector((double)f, (double)f2, (double)f3);
         MovingObjectPosition hit = this.mc.theWorld.rayTraceBlocks(vec3.xCoord, vec3.yCoord, vec3.zCoord, vec4.xCoord, vec4.yCoord, vec4.zCoord);
         if (hit != null) {
            double d4 = hit.hitVec.distanceTo(vec);
            if (d4 < d3 && d4 > 0.0) {
               d3 = d4;
            }
         }
      }

      return d3 - 0.25;
   }

   @Override
   public void onGuiClosed() {
      super.onGuiClosed();
      WDL.mc.gameSettings.showDebugProfilerChart = this.oldShowDebug;
      WDL.mc.gameSettings.chatVisibility = this.oldChatVisibility;
      Entity oldRenderViewEntity2 = this.oldRenderViewEntity;
      WDL.mc.setRenderViewEntity(oldRenderViewEntity2);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.cam != null) {
         float f = this.yaw + (this.yawNextTick - this.yaw) * partialTicks;
         this.cam.prevRotationPitch = this.cam.rotationPitch = 0.0F;
         this.cam.prevRotationYaw = this.cam.rotationYaw = f;
         double d = Math.cos((double)f / 180.0 * 3.141592653589793);
         double d2 = Math.sin((double)(f - 90.0F) / 180.0 * 3.141592653589793);

         double d3;
         try {
            d3 = this.truncateDistanceIfBlockInWay(d, d2, 0.5);
         } catch (NullPointerException e) {
            return;
         }

         this.cam.lastTickPosY = this.cam.prevPosY = this.cam.posY = WDL.thePlayer.posY;
         this.cam.lastTickPosX = this.cam.prevPosX = this.cam.posX = WDL.thePlayer.posX - d3 * d;
         this.cam.lastTickPosZ = this.cam.prevPosZ = this.cam.posZ = WDL.thePlayer.posZ + d3 * d2;
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void updateScreen() {
      this.yaw = this.yawNextTick;
      this.yawNextTick = this.yaw + 1.0F * (float)(1.0 + 0.699999988079071 * Math.cos((double)(this.yaw + 45.0F) / 45.0 * 3.141592653589793));
   }
}
