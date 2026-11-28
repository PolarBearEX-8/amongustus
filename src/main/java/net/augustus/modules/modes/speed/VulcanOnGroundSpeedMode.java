package net.augustus.modules.modes.speed;

import net.augustus.Augustus;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.math.Vec2d;
import net.augustus.modules.movement.Speed;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;

public class VulcanOnGroundSpeedMode extends SpeedMode {
   private boolean spoofingAirGround = false;

   public VulcanOnGroundSpeedMode(String str, Speed speed2) {
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
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (mc.thePlayer.onGround) {
         if (!((double)mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ).getBlock().slipperiness > 0.601)) {
            if (mc.thePlayer.hurtTime <= 0) {
               if (!mc.thePlayer.movementInput.jump) {
                  float rawMoveStrafe = mc.thePlayer.movementInput.rawMoveStrafe;
                  float rawMoveForward = mc.thePlayer.movementInput.rawMoveForward;
                  double[] doubles = MoveUtil.calculateMotion(1.0, rawMoveStrafe, rawMoveForward, Augustus.getInstance().getYawPitchHelper().realYaw);
                  Vec2d vec2d = new Vec2d(doubles[0], doubles[1]);
                  if (vec2d.length() != 0.0) {
                     vec2d = vec2d.normalize();
                     double d = 0.0;
                     if (mc.thePlayer.getActivePotionEffect(Potion.moveSpeed) != null) {
                        d = 4.0;
                     }

                     double d2 = 0.2975
                        + d * 0.07500000298023224
                        + (mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() - 0.20000000298023224) * 3.5;
                     if (d2 < 0.2975) {
                        d2 = 0.2975 + d * 0.07500000298023224;
                     }

                     if (mc.thePlayer.ticksExisted % 2 == 0) {
                        vec2d = vec2d.scale(d2 + 0.323);
                     } else {
                        vec2d = vec2d.scale(d2);
                     }

                     event.setX(vec2d.x);
                     event.setZ(vec2d.y);
                  }
               }
            }
         }
      }
   }

   @Override
   public void onSendPacket(EventPacket event) {
      if (event.getPacket() instanceof C03PacketPlayer && mc.thePlayer.onGround && (mc.thePlayer.movementInput.rawMoveStrafe != 0.0F || mc.thePlayer.movementInput.rawMoveForward != 0.0F)) {
         if (mc.thePlayer.hurtTime > 0) {
            return;
         }

         if (mc.thePlayer.movementInput.jump) {
            return;
         }

         C03PacketPlayer packet = (C03PacketPlayer)event.getPacket();
         if (packet.getPositionY() % 0.015625 == 0.0) {
            if ((double)mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ).getBlock().slipperiness > 0.601) {
               return;
            }

            packet.setPositionY(packet.getPositionY() + 1.0E-4);
            if (mc.thePlayer.ticksExisted % 2 == 0) {
               packet.setOnGround(false);
               this.spoofingAirGround = true;
            } else {
               this.spoofingAirGround = false;
            }
         }
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.spoofingAirGround) {
         event.setJump(false);
      }
   }
}
