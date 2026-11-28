package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Fly;
import net.minecraft.client.entity.EntityPlayerSP;

public class VClipGlideFlyMode extends FlyMode {
   public VClipGlideFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer.onGround) {
         mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 10.0, mc.thePlayer.posZ);
      }
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
   }

   @Override
   public void onMotion(EventMotion event) {
      if (mc.thePlayer.movementInput.moveForward == 0.0F && mc.thePlayer.movementInput.moveStrafe == 0.0F) {
         mc.thePlayer.motionY *= 0.0;
         mc.thePlayer.motionX *= 0.0;
         mc.thePlayer.motionZ *= 0.0;
      } else {
         EntityPlayerSP thePlayer = mc.thePlayer;
         if (mc.thePlayer.ticksExisted % 40 > 30) {
            if (mc.thePlayer.ticksExisted % 40 == 35) {
               if (mc.thePlayer.ticksExisted % 80 == 70) {
                  mc.thePlayer.motionY *= 0.5;
               } else {
                  mc.thePlayer.motionY *= 0.2;
               }
            }
         } else {
            if (thePlayer.ticksExisted % 4 == 0) {
               mc.thePlayer.motionY = 0.0;
            }

            mc.thePlayer.motionY *= 0.5;
            double d = Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
            if (d < 0.4 && d > 0.0 && mc.thePlayer.movementInput.moveForward != 0.0F) {
               MoveUtil.addMotion(0.0199 + Math.random() * 0.01, false);
            }
         }
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }
}
