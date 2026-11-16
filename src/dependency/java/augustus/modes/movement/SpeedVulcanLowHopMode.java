package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.CombatUtil;
import client.util.MinecraftAccessor;
import net.minecraft.potion.Potion;

public class SpeedVulcanLowHopMode extends SpeedModeHandler {
   public int count2;

   private void applyDouble(double offset, float factor, double offset2, double offset3) {
      float factor2 = factor;
      double offset4 = offset2;
      double offset5 = offset3;
      if (offset3 != 0.0) {
         if (offset2 > 0.0) {
            factor2 = factor + (offset3 > 0.0 ? -45.0F : 45.0F);
         } else if (offset2 < 0.0) {
            factor2 = factor + (offset3 > 0.0 ? 45.0F : -45.0F);
         }

         offset4 = 0.0;
         if (offset3 > 0.0) {
            offset5 = 1.0;
         } else if (offset3 < 0.0) {
            offset5 = -1.0;
         }
      }

      if (offset4 > 0.0) {
         offset4 = 1.0;
      } else if (offset4 < 0.0) {
         offset4 = -1.0;
      }

      double offset6 = Math.cos(Math.toRadians((double)(factor2 + 90.0F)));
      double offset7 = Math.sin(Math.toRadians((double)(factor2 + 90.0F)));
      MinecraftAccessor.minecraft.thePlayer.motionX = offset5 * offset * offset6 + offset4 * offset * offset7;
      MinecraftAccessor.minecraft.thePlayer.motionZ = offset5 * offset * offset7 - offset4 * offset * offset6;
   }

   @Override
   public void onMove(EventMove event) {
   }

   private double getValue() {
      double offset = 0.2875;
      if (MinecraftAccessor.minecraft.thePlayer.checkPotion(Potion.potion2)) {
         offset *= 1.0 + 0.2 * (double)(MinecraftAccessor.minecraft.thePlayer.getPotionEffect(Potion.potion2).getIndexOffset2() + 1);
      }

      return offset;
   }

   private float getFactor() {
      return (float)Math.sqrt(
         MinecraftAccessor.minecraft.thePlayer.motionX * MinecraftAccessor.minecraft.thePlayer.motionX
            + MinecraftAccessor.minecraft.thePlayer.motionZ * MinecraftAccessor.minecraft.thePlayer.motionZ
      );
   }

   @Override
   public void updateState2() {
      this.count2 = 0;
   }

   @Override
   public void processAction() {
   }

   @Override
   public void processChar() {
      this.count2++;
      if (MinecraftAccessor.minecraft.thePlayer.hurtTime <= 0) {
         if (this.isActive2()) {
            MinecraftAccessor.minecraft.thePlayer.closeScreen();
            double offset;
            if (MinecraftAccessor.minecraft.thePlayer.scaleFactor3 != 0.0F && MinecraftAccessor.minecraft.thePlayer.scaleFactor24 == 0.0F) {
               offset = 0.521;
            } else if (MinecraftAccessor.minecraft.thePlayer.scaleFactor24 != 0.0F) {
               offset = 0.46;
            } else {
               offset = this.getValue() * 1.8;
            }

            if (CombatUtil.matchesPotion(Potion.potion2)) {
               offset *= 1.0 + 0.11 * (double)(MinecraftAccessor.minecraft.thePlayer.getPotionEffect(Potion.potion2).getIndexOffset2() + 1);
            }

            if (CombatUtil.matchesPotion(Potion.potion3)) {
               offset *= 0.9F;
            }

            this.applyDouble(offset);
            this.count2 = 0;
         } else {
            MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.0F;
            if ((double)this.getFactor() > 0.332) {
               double offset2 = 0.331;
               this.applyDouble(offset2);
            }

            if (this.count2 == 4) {
               MinecraftAccessor.minecraft.thePlayer.motionY = -0.315;
            }

            if (this.count2 > 3) {
               MinecraftAccessor.minecraft.thePlayer.motionY -= 4.0E-4;
            }
         }
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
      this.count2 = 0;
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   private boolean isActive2() {
      return this.isActiveAsFlag() && MinecraftAccessor.minecraft.thePlayer.onGround;
   }

   private void applyDouble(double offset) {
      byte byteValue = 0;
      short shortValue = 9968;
      int count = -189210109;
      double moveStrafe = (double)MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveStrafe;
      double moveForward = (double)MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveForward;
      double offset2 = moveStrafe;
      float yaw = MinecraftAccessor.minecraft.thePlayer.rotationYaw;
      this.applyDouble(offset, yaw, offset2, moveForward);
   }

   public SpeedVulcanLowHopMode(String text, Speed speed) {
      super(text, speed);
   }
}
