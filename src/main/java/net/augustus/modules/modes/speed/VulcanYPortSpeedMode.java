package net.augustus.modules.modes.speed;

import net.augustus.Augustus;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.math.Vec2d;
import net.augustus.modules.movement.Speed;

public class VulcanYPortSpeedMode extends SpeedMode {
   private boolean blockJumpInput = false;
   private int activeMoveTicks;

   public VulcanYPortSpeedMode(String str, Speed speed2) {
      super(str, speed2);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
   }

   @Override
   public void onPostUpdate() {
   }

   @Override
   public void onMotion(EventMotion event) {
      int activeMoveTicks2 = this.activeMoveTicks;
      this.activeMoveTicks = 0;
      if (!((double)mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ).getBlock().slipperiness > 0.601)) {
         if (!mc.thePlayer.movementInput.jump) {
            if (!((double)mc.thePlayer.fallDistance > 0.42)) {
               if (mc.thePlayer.hurtTime <= 0) {
                  float rawMoveStrafe = mc.thePlayer.movementInput.rawMoveStrafe;
                  float rawMoveForward = mc.thePlayer.movementInput.rawMoveForward;
                  if (rawMoveStrafe != 0.0F || rawMoveForward != 0.0F) {
                     this.activeMoveTicks = activeMoveTicks2 + 1;
                     if (mc.thePlayer.ticksExisted % 3 == 2) {
                        mc.thePlayer.motionY = 0.41999998688697815;
                     } else {
                        mc.thePlayer.motionY = -8.0;
                     }

                  }
               }
            }
         }
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (this.activeMoveTicks >= 6) {
         if (!((double)mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ).getBlock().slipperiness > 0.601)) {
            if (mc.thePlayer.hurtTime <= 0) {
               if (!mc.thePlayer.movementInput.jump) {
                  if (!((double)mc.thePlayer.fallDistance > 0.42)) {
                     float rawMoveStrafe = mc.thePlayer.movementInput.rawMoveStrafe;
                     float rawMoveForward = mc.thePlayer.movementInput.rawMoveForward;
                     if (rawMoveStrafe != 0.0F || rawMoveForward != 0.0F) {
                        double[] doubles = MoveUtil.calculateMotion(1.0, rawMoveStrafe, rawMoveForward, Augustus.getInstance().getYawPitchHelper().realYaw);
                        Vec2d vec2d = new Vec2d(doubles[0], doubles[1]);
                        if (vec2d.length() != 0.0) {
                           vec2d = vec2d.normalize();
                           double d = 0.0;
                           switch (mc.thePlayer.ticksExisted % 3) {
                              case 0:
                                 d = 0.54121;
                                 break;
                              case 1:
                                 d = 0.30113;
                                 break;
                              case 2:
                                 d = 0.4041;
                           }

                           vec2d = vec2d.scale(d);
                           event.setX(vec2d.x);
                           event.setZ(vec2d.y);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public void onSendPacket(EventPacket event) {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.blockJumpInput) {
         event.setJump(false);
      }
   }
}
