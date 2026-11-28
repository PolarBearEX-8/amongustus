package net.augustus.modules.movement;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.listeners.ShouldRenderEntityListener;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.settings.Setting;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.MoveUtil;
import net.augustus.events.listeners.RenderEntitiesListener;
import net.augustus.events.EventRenderEntities;
import net.augustus.utils.rotation.RotationController;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.render.RenderUtil;
import net.augustus.Augustus;
import net.augustus.events.EventShouldRenderEntity;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.rotation.YawPitchHelper;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class FreeCam extends Module implements ClickListener, MoveInputListener, RenderListener, RenderEntitiesListener, ShouldRenderEntityListener, TickListener {
   private final DoubleValue speed;
   public final BooleanValue spectatorRender;
   public final BooleanValue updateRaytrace;
   public final BooleanValue showRealPlayerModel;
   public final DoubleValue minimumDistanceToShowPlayerModel;
   private final BooleanValue displayCoordinates;
   private final ColorSetting highlightColor;
   private final ColorSetting textColor;
   private boolean pendingDisable;
   public Vec3 cameraPos;
   public Vec3 prevCameraPos;
   public double realPosX;
   public double realPosY;
   public double realPosZ;
   public double realLastTickPosX;
   public double realLastTickPosY;
   public double realLastTickPosZ;
   public float savedYaw;
   public float savedPitch;
   public float cameraYaw;
   public float cameraPitch;

   public FreeCam() {
       super("FreeCam", "FreieKamera", new Color(235, 116, 52), Categorys.MOVEMENT);
      this.speed = (DoubleValue)(new DoubleValue(this, "Speed", 1.0, 0.0, 4.0, 2)).markVisualOnly();
      this.spectatorRender = (BooleanValue)(new BooleanValue(this, "Spectator Render", false)).markVisualOnly();
      this.updateRaytrace = (BooleanValue)(new BooleanValue(this, "Update Raytrace", false)).markVisualOnly();
      this.showRealPlayerModel = (BooleanValue)(new BooleanValue(this, "Show real Player Model", true)).markVisualOnly();
      DoubleValue doubleSetting = new DoubleValue(this, "Minimum distance to show player model", 1.0, 0.0, 10.0, 1);
      BooleanValue showRealPlayerModel2 = this.showRealPlayerModel;
      BooleanValue booleanValue = showRealPlayerModel2;
      this.minimumDistanceToShowPlayerModel = (DoubleValue)doubleSetting.visibleWhen(() -> booleanValue.getValue()).markVisualOnly();
      this.displayCoordinates = (BooleanValue)(new BooleanValue(this, "Display Coordinates", false)).markVisualOnly();
      ColorSetting colorSetting = new ColorSetting(this, "Highlight Color", new Color(5, 245, 69, 50));
      showRealPlayerModel2 = this.displayCoordinates;
      BooleanValue booleanValue2 = showRealPlayerModel2;
      this.highlightColor = (ColorSetting)((ColorSetting)colorSetting.withVisibility(() -> booleanValue2.getValue())).markVisualOnly();
      ColorSetting colorSetting2 = new ColorSetting(this, "Text Color", new Color(255, 255, 255, 255));
      showRealPlayerModel2 = this.displayCoordinates;
      BooleanValue booleanValue3 = showRealPlayerModel2;
      this.textColor = (ColorSetting)((ColorSetting)colorSetting2.withVisibility(() -> booleanValue3.getValue())).markVisualOnly();
      this.pendingDisable = false;
      this.cameraPos = new Vec3(0.0, 0.0, 0.0);
      this.prevCameraPos = new Vec3(0.0, 0.0, 0.0);
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer != null) {
         this.savedYaw = mc.thePlayer.rotationYaw;
         this.savedPitch = mc.thePlayer.rotationPitch;
         this.cameraYaw = mc.thePlayer.rotationYaw;
         this.cameraPitch = mc.thePlayer.rotationPitch;
         this.cameraPos = mc.thePlayer.getInterpolatedPos(1.0F);
         this.prevCameraPos = mc.thePlayer.getInterpolatedPos(0.0F);
      }
   }

   @Override
   public void onDisable() {
      if (mc.thePlayer != null) {
         float rotationYaw = mc.thePlayer.rotationYaw;
         float rotationPitch = mc.thePlayer.rotationPitch;
         ClientManagers.rotationController.requestRotation(new Rotation(rotationYaw, rotationPitch), (byte)3, this);
         YawPitchHelper yawPitchHelper = Augustus.getInstance().getYawPitchHelper();
         yawPitchHelper.realYaw = rotationYaw;
         yawPitchHelper.realPitch = rotationPitch;
         yawPitchHelper.realLastYaw = rotationYaw;
         yawPitchHelper.realLastPitch = rotationPitch;
         mc.thePlayer.rotationYaw = rotationYaw;
         mc.thePlayer.rotationPitch = rotationPitch;
      }
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(21, 100);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(57);
      this.toggleEventSubscription(58);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onPreTick() {
      if (mc.thePlayer == null) {
         this.pendingDisable = true;
      } else {
         ClientManagers.rotationController.requestRotation(new Rotation(mc.thePlayer.lastReportedYaw, mc.thePlayer.lastReportedPitch), (byte)-50, this);
         mc.thePlayer.rotationYaw = mc.thePlayer.lastReportedYaw;
         mc.thePlayer.rotationPitch = mc.thePlayer.lastReportedPitch;
      }
   }

   @Override
   public void onPostTick() {
      if (mc.thePlayer == null) {
         this.pendingDisable = true;
      } else {
         mc.thePlayer.rotationYaw = this.cameraYaw;
         mc.thePlayer.rotationPitch = this.cameraPitch;
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.pendingDisable) {
         this.pendingDisable = false;
         this.toggle();
      } else {
         if (true) {
            double[] doubles = MoveUtil.calculateMotion(0.5, event.getMoveStrafe(), event.getMoveForward(), this.cameraYaw);
            Vec3 vec = new Vec3(doubles[0], event.isOriginalJump() ? 0.4 : (event.isOriginalSneak() ? -0.4 : 0.0), doubles[1]);
            vec = vec.scale(this.speed.getValue());
            event.setJump(false);
            event.setSneak(false);
            event.setMoveForward(0.0F);
            event.setMoveStrafe(0.0F);
            this.prevCameraPos = new Vec3(this.cameraPos);
            this.cameraPos = this.cameraPos.add(vec);
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (!this.updateRaytrace.getValue()) {
         event.cancel();
         event.setBlockAllClicks(true);
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      this.cameraYaw = mc.thePlayer.rotationYaw;
      this.cameraPitch = mc.thePlayer.rotationPitch;
      if (this.displayCoordinates.getValue()) {
         Vec3 vec = RotationUtil.getLookVector(mc.thePlayer.rotationPitch, mc.thePlayer.rotationYaw).scale(200.0);
         Vec3 vec2 = this.prevCameraPos.lerp(this.cameraPos, (double)partialTicks);
         MovingObjectPosition hit = mc.theWorld
            .rayTraceBlocksFast(
               vec2.xCoord,
               vec2.yCoord + (double)mc.thePlayer.getEyeHeight(),
               vec2.zCoord,
               vec.xCoord + vec2.xCoord,
               vec.yCoord + vec2.yCoord + (double)mc.thePlayer.getEyeHeight(),
               vec.zCoord + vec2.zCoord
            );
         if (hit != null && hit.hitVec != null) {
            RenderUtil.begin3DRender();
            GL11.glPushMatrix();
            GL11.glLineWidth(3.0F);
            GL11.glTranslated(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
            GlStateManager.disableAlpha();
            RenderUtil.setGlColor(this.highlightColor.getColorInt((byte)0));
            BlockPos blockPos = hit.getBlockPos();
            RenderUtil.drawFilledBox(
               (float)blockPos.getX(),
               (float)blockPos.getY(),
               (float)blockPos.getZ(),
               (float)(blockPos.getX() + 1),
               (float)(blockPos.getY() + 1),
               (float)(blockPos.getZ() + 1)
            );
            GL11.glPopMatrix();
            RenderUtil.end3DRender();
            GlStateManager.enableAlpha();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         }
      }
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (this.displayCoordinates.getValue()) {
         Vec3 vec = RotationUtil.getLookVector(mc.thePlayer.rotationPitch, mc.thePlayer.rotationYaw).scale(200.0);
         Vec3 vec2 = this.prevCameraPos.lerp(this.cameraPos, (double)partialTicks);
         MovingObjectPosition hit = mc.theWorld
            .rayTraceBlocksFast(
               vec2.xCoord,
               vec2.yCoord + (double)mc.thePlayer.getEyeHeight(),
               vec2.zCoord,
               vec.xCoord + vec2.xCoord,
               vec.yCoord + vec2.yCoord + (double)mc.thePlayer.getEyeHeight(),
               vec.zCoord + vec2.zCoord
            );
         if (hit != null && hit.hitVec != null) {
            BlockPos blockPos = hit.getBlockPos();
            String str = "x=" + blockPos.getX() + " y=" + blockPos.getY() + " z=" + blockPos.getZ();
            mc.fontRendererObj.drawString(str, (sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(str)) / 2, sr.getScaledHeight() / 2 + mc.fontRendererObj.FONT_HEIGHT * 3, this.textColor.getColorInt((byte)0));
         }

         RenderUtil.resetGlColorState();
      }
   }

   @Override
   public void onRenderEntities(EventRenderEntities event) {
      if (this.showRealPlayerModel.getValue()) {
         if (this.cameraPos == null || !(this.cameraPos.distanceTo(this.realPosX, this.realPosY, this.realPosZ) < this.minimumDistanceToShowPlayerModel.getValue())) {
            EntityPlayerSP thePlayer = mc.thePlayer;
            double posX = thePlayer.posX;
            double posY = thePlayer.posY;
            double posZ = thePlayer.posZ;
            double lastTickPosX = thePlayer.lastTickPosX;
            double lastTickPosY = thePlayer.lastTickPosY;
            double lastTickPosZ = thePlayer.lastTickPosZ;
            thePlayer.posX = this.realPosX;
            thePlayer.posY = this.realPosY;
            thePlayer.posZ = this.realPosZ;
            thePlayer.lastTickPosX = this.realLastTickPosX;
            thePlayer.lastTickPosY = this.realLastTickPosY;
            thePlayer.lastTickPosZ = this.realLastTickPosZ;
            mc.renderGlobal.countEntitiesRendered++;
            if (event.isShadersEnabled()) {
               Shaders.nextEntity(thePlayer);
            }

            mc.renderGlobal.renderManager.renderEntitySimple(thePlayer, event.getPartialTicks());
            thePlayer.posX = posX;
            thePlayer.posY = posY;
            thePlayer.posZ = posZ;
            thePlayer.lastTickPosX = lastTickPosX;
            thePlayer.lastTickPosY = lastTickPosY;
            thePlayer.lastTickPosZ = lastTickPosZ;
         }
      }
   }

   @Override
   public void onShouldRenderEntity(EventShouldRenderEntity event) {
      if (event.getEntity() == mc.thePlayer) {
         event.setShouldRender(true);
      }
   }


}
