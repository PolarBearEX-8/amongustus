package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventOrientCamera;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.events.EventPlayerView;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.PlayerViewListener;
import net.augustus.events.EventMouseMove;
import net.augustus.events.listeners.OrientCameraListener;
import net.augustus.Augustus;
import net.augustus.settings.BooleanValue;
import net.minecraft.util.MathHelper;

public class Freelook extends Module implements OrientCameraListener, MouseMoveListener, PlayerViewListener {
   private final BooleanValue thirdPerson;
   private final BooleanValue disableItInFistPerson;
   private final BooleanValue rotateNametags;
   private float cameraYaw;
   private float prevCameraYaw;
   private float cameraPitch;
   private float prevCameraPitch;
   private int savedThirdPersonView;

   public Freelook() {
       super("Freelook", "FreiesSchauen", new Color(152, 245, 167), Categorys.RENDER);
      this.thirdPerson = new BooleanValue(this, "ThirdPerson", true);
      this.disableItInFistPerson = new BooleanValue(this, "DisableItInFistPerson", true);
      this.rotateNametags = new BooleanValue(this, "RotateNametags", true);
   }

   public void onEnable() {
      if (mc.thePlayer != null) {
         this.resetCameraAngles();
         this.savedThirdPersonView = mc.gameSettings.thirdPersonView;
         if (this.thirdPerson.getValue()) {
            mc.gameSettings.thirdPersonView = 1;
         }
      }
   }

   public void onDisable() {
      if (this.thirdPerson.getValue()) {
         mc.gameSettings.thirdPersonView = this.savedThirdPersonView;
      }
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(24);
      this.toggleEventSubscription(19);
      this.toggleEventSubscription(63);
   }

   public void onOrientCamera(EventOrientCamera event) {
      if (mc.gameSettings.thirdPersonView == 0 && this.disableItInFistPerson.getValue()) {
         this.resetCameraAngles();
      } else {
         event.setYaw(this.cameraYaw);
         event.setPitch(this.cameraPitch);
         event.setOverrideRotation(true);
      }
   }

   public void onMouseMove(EventMouseMove event) {
      if (mc.gameSettings.thirdPersonView != 0 || !this.disableItInFistPerson.getValue()) {
         if (!ClientManagers.moduleManager.aimAssist.isToggled() && !ClientManagers.moduleManager.aimBot.isToggled()) {
            event.setDeltaX((byte)0);
            event.setDeltaY((byte)0);
         }

         float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
         float f2 = f * f * f * 8.0F;
         float f3 = (float)mc.mouseHelper.deltaX * f2;
         float f4 = (float)mc.mouseHelper.deltaY * f2;
         if (mc.gameSettings.invertMouse) {
            f4 *= -1.0F;
         }

         this.cameraYaw = (float)((double)this.cameraYaw + (double)f3 * 0.15);
         this.cameraPitch = (float)((double)this.cameraPitch - (double)f4 * 0.15);
         this.cameraPitch = MathHelper.clamp_float(this.cameraPitch, -90.0F, 90.0F);
      }
   }

   public void onPlayerView(EventPlayerView event) {
      if (this.rotateNametags.getValue()) {
         float partialTicks = event.getPartialTicks();
         event.setPlayerViewY(this.prevCameraYaw + (this.cameraYaw - this.prevCameraYaw) * partialTicks);
         event.setPlayerViewX(this.prevCameraPitch + (this.cameraPitch - this.prevCameraPitch) * partialTicks);
         this.prevCameraYaw = this.cameraYaw;
         this.prevCameraPitch = this.cameraPitch;
      }
   }

   private void resetCameraAngles() {
      this.cameraYaw = this.prevCameraYaw = Augustus.getInstance().getYawPitchHelper().realYaw;
      this.cameraPitch = this.prevCameraPitch = Augustus.getInstance().getYawPitchHelper().realPitch;
   }


}
