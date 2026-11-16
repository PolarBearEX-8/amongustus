package client.util;

import client.Client;
import client.ClientGlobals;
import client.event.EventMoveEntity;
import client.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;

public class MoveUtil implements MinecraftAccessor {
   public static final double value = 0.42F;

   public static void onMoveEntity(double offset, boolean flag, EventMoveEntity event) {
      double offset2 = Math.toRadians((double)computeFactor(flag));
      double offset3 = -(Math.sin(offset2) * offset);
      event.setCoordOffset2(offset3);
      double offset4 = Math.cos(offset2) * offset;
      event.setCoordOffset(offset4);
   }

   public static boolean isActive() {
      double motionX = MinecraftAccessor.minecraft.thePlayer.motionX;
      double motionZ = MinecraftAccessor.minecraft.thePlayer.motionZ;
      double offset = 0.05;

      while (
         motionX != 0.0
            && MinecraftAccessor.minecraft
               .theWorld
               .getCollidingBoundingBoxes(
                  MinecraftAccessor.minecraft.thePlayer, MinecraftAccessor.minecraft.thePlayer.getBoundingBox2().expand(motionX, -1.0, 0.0)
               )
               .isEmpty()
      ) {
         if (motionX < offset && motionX >= -offset) {
            motionX = 0.0;
         } else if (motionX > 0.0) {
            motionX -= offset;
         } else {
            motionX += offset;
         }
      }

      while (
         motionZ != 0.0
            && MinecraftAccessor.minecraft
               .theWorld
               .getCollidingBoundingBoxes(
                  MinecraftAccessor.minecraft.thePlayer, MinecraftAccessor.minecraft.thePlayer.getBoundingBox2().expand(0.0, -1.0, motionZ)
               )
               .isEmpty()
      ) {
         if (motionZ < offset && motionZ >= -offset) {
            motionZ = 0.0;
         } else if (motionZ > 0.0) {
            motionZ -= offset;
         } else {
            motionZ += offset;
         }
      }

      while (
         motionX != 0.0
            && motionZ != 0.0
            && MinecraftAccessor.minecraft
               .theWorld
               .getCollidingBoundingBoxes(
                  MinecraftAccessor.minecraft.thePlayer, MinecraftAccessor.minecraft.thePlayer.getBoundingBox2().expand(motionX, -1.0, motionZ)
               )
               .isEmpty()
      ) {
         if (motionX < offset && motionX >= -offset) {
            motionX = 0.0;
         } else if (motionX > 0.0) {
            motionX -= offset;
         } else {
            motionX += offset;
         }

         if (motionZ < offset && motionZ >= -offset) {
            motionZ = 0.0;
         } else if (motionZ > 0.0) {
            motionZ -= offset;
         } else {
            motionZ += offset;
         }
      }

      return MinecraftAccessor.minecraft.thePlayer.motionX != motionX || MinecraftAccessor.minecraft.thePlayer.motionZ != motionZ;
   }

   public static double getValue2() {
      return Math.sqrt(
         MinecraftAccessor.minecraft.thePlayer.motionX * MinecraftAccessor.minecraft.thePlayer.motionX
            + MinecraftAccessor.minecraft.thePlayer.motionZ * MinecraftAccessor.minecraft.thePlayer.motionZ
      );
   }

   public static void applyDoubleByDouble(double offset) {
      EntityPlayerSP player = MinecraftAccessor.minecraft.thePlayer;
      double floatItem = (double)getFloat2();
      boolean flag = player.secondaryFloatEntry.factor2 != 0.0F || player.secondaryFloatEntry.factor != 0.0F;
      boolean flag2 = player.secondaryFloatEntry.factor2 > 0.0F;
      boolean flag3 = player.secondaryFloatEntry.factor2 < 0.0F;
      boolean flag4 = player.secondaryFloatEntry.factor > 0.0F;
      boolean flag5 = player.secondaryFloatEntry.factor < 0.0F;
      boolean flag6 = flag5 || flag4;
      boolean flag7 = flag2 || flag3;
      if (flag) {
         if (flag2 && !flag6) {
            floatItem += 0.0;
         } else if (flag3 && !flag6) {
            floatItem += 180.0;
         } else if (flag2 && flag5) {
            floatItem += 45.0;
         } else if (flag2) {
            floatItem -= 45.0;
         } else if (!flag7 && flag5) {
            floatItem += 90.0;
         } else if (!flag7 && flag4) {
            floatItem -= 90.0;
         } else if (flag3 && flag5) {
            floatItem += 135.0;
         } else if (flag3) {
            floatItem -= 135.0;
         }

         double offset2 = Math.toRadians(floatItem);
         player.motionX = -Math.sin(offset2) * offset;
         player.motionZ = Math.cos(offset2) * offset;
      }
   }

   public static void processFloat(float factor) {
      double doubleItem = getDouble2();
      MinecraftAccessor.minecraft.thePlayer.motionX = -(Math.sin(doubleItem) * (double)factor);
      MinecraftAccessor.minecraft.thePlayer.motionZ = Math.cos(doubleItem) * (double)factor;
   }

   public static void onMoveEntity(double offset, float factor, EventMoveEntity event) {
      if (canExecute()) {
         double offset2 = offset * (double)factor;
         double offset3 = (double)(1.0F - factor);
         double offset4 = event.getCoordOffset2() * offset3;
         double offset5 = event.getCoordOffset() * offset3;
         double doubleItem = getDouble2();
         double offset6 = offset4 - Math.sin(doubleItem) * offset2;
         event.setCoordOffset2(offset6);
         double offset7 = offset5 + Math.cos(doubleItem) * offset2;
         event.setCoordOffset(offset7);
      }
   }

   public static void applyDouble(double offset, float factor) {
      if (canExecute()) {
         double offset2 = offset * (double)factor;
         double offset3 = (double)(1.0F - factor);
         double offset4 = MinecraftAccessor.minecraft.thePlayer.motionX * offset3;
         double offset5 = MinecraftAccessor.minecraft.thePlayer.motionZ * offset3;
         double doubleItem = getDouble2();
         MinecraftAccessor.minecraft.thePlayer.motionX = offset4 - Math.sin(doubleItem) * offset2;
         MinecraftAccessor.minecraft.thePlayer.motionZ = offset5 + Math.cos(doubleItem) * offset2;
      }
   }

   public static void processFloat2(float factor, boolean flag) {
      double offset = Math.toRadians((double)computeFactor(flag));
      MinecraftAccessor.minecraft.thePlayer.motionX = -(Math.sin(offset) * (double)factor);
      MinecraftAccessor.minecraft.thePlayer.motionZ = Math.cos(offset) * (double)factor;
   }

   public static double getDouble2() {
      return computeValue(getFloat2());
   }

   public static double getValueAsDouble() {
      return computeValue(0.42F);
   }

   public static boolean canExecute() {
      return MinecraftAccessor.minecraft.thePlayer.scaleFactor3 != 0.0F
         || MinecraftAccessor.minecraft.thePlayer.scaleFactor24 != 0.0F && !MinecraftAccessor.minecraft.thePlayer.isCollidedHorizontally;
   }

   public static void processAction(float factor) {
      applyDouble(getValue2(), factor);
   }

   public static void applyDouble(double offset, double offset2, float factor) {
      float factor2 = factor;
      float factor3 = MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor;
      float factor4 = MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor2;
      float factor5 = (float)offset;
      if (factor3 != 0.0F && factor4 != 0.0F) {
         if (factor3 > 0.0F) {
            factor2 = (float)(factor4 > 0.0F ? (double)factor - offset2 : (double)factor + offset2);
         } else {
            factor2 = (float)(factor4 > 0.0F ? (double)factor + offset2 : (double)factor - offset2);
         }

         factor3 = 0.0F;
      }

      float factor6 = MathHelper.abs(factor2 * (float) Math.PI / 180.0F);
      float factor7 = MathHelper.cos(factor2 * (float) Math.PI / 180.0F);
      MinecraftAccessor.minecraft.thePlayer.motionX = (double)(factor3 * factor5 * factor7 - factor4 * factor5 * factor6);
      MinecraftAccessor.minecraft.thePlayer.motionZ = (double)(factor4 * factor5 * factor7 + factor3 * factor5 * factor6);
   }

   public static float computeFactor(boolean flag) {
      return flag ? (float)Math.toDegrees(getDouble2()) : getFloat2();
   }

   public static float getFloat2() {
      return ((Module)ClientGlobals.moduleManager.scaffold).isToggled() && ClientGlobals.moduleManager.scaffold.settingMoveFix.getSelected().equals("None")
         ? Client.getClient().getViaProtocolHelper().scaleFactor1
         : (
            ClientGlobals.moduleManager.targetStrafe.targetEntity != null && ((Module)ClientGlobals.moduleManager.targetStrafe).isToggled()
               ? ClientGlobals.moduleManager.targetStrafe.motionMultiplier
               : Client.getClient().getViaProtocolHelper().scaleFactor1
         );
   }

   public static int[] getIntArray() {
      int factor = (int)MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor;
      int factor2 = (int)MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor2;
      float factor3 = MathHelper.sqrt_float(Client.getClient().getViaProtocolHelper().scaleFactor1 - Minecraft.getMinecraft().thePlayer.rotationYaw);
      byte byteValue = 0;
      byte byteValue2 = 0;
      int count;
      int count2;
      if ((double)factor3 >= 22.5 && (double)factor3 < 67.5) {
         int count3 = byteValue2 + factor;
         int count4 = byteValue + factor2;
         count2 = count3 - factor2;
         count = count4 + factor;
      } else if ((double)factor3 >= 67.5 && (double)factor3 < 112.5) {
         count2 = byteValue2 - factor2;
         count = byteValue + factor;
      } else if ((double)factor3 >= 112.5 && (double)factor3 < 157.5) {
         int count5 = byteValue2 - factor;
         int count6 = byteValue - factor2;
         count2 = count5 - factor2;
         count = count6 + factor;
      } else if ((double)factor3 >= 157.5 || (double)factor3 <= -157.5) {
         count2 = byteValue2 - factor;
         count = byteValue - factor2;
      } else if ((double)factor3 > -157.5 && (double)factor3 <= -112.5) {
         int count7 = byteValue2 - factor;
         int count8 = byteValue - factor2;
         count2 = count7 + factor2;
         count = count8 - factor;
      } else if ((double)factor3 > -112.5 && (double)factor3 <= -67.5) {
         count2 = byteValue2 + factor2;
         count = byteValue - factor;
      } else if ((double)factor3 > -67.5 && (double)factor3 <= -22.5) {
         int count9 = byteValue2 + factor;
         int count10 = byteValue + factor2;
         count2 = count9 + factor2;
         count = count10 - factor;
      } else {
         count2 = byteValue2 + factor;
         count = byteValue + factor2;
      }

      return new int[]{MathHelper.getInt12(count, -1, 1), MathHelper.getInt12(count2, -1, 1)};
   }

   public static double computeValue(float factor) {
      return getDouble7(
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor2, MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor, factor
      );
   }

   public static void processAction() {
      double value2 = getValue2();
      if (canExecute()) {
         float floatItem = getFloat2();
         if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor2 < 0.0F) {
            floatItem += 180.0F;
         } else {
            float factor = 1.0F;
            if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor2 < 0.0F) {
               factor = -0.5F;
            } else if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor2 > 0.0F) {
               factor = 0.5F;
            }

            if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor > 0.0F) {
               floatItem -= 90.0F * factor;
            }

            if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor < 0.0F) {
               floatItem += 90.0F * factor;
            }
         }

         double offset = Math.toRadians((double)floatItem);
         MinecraftAccessor.minecraft.thePlayer.motionX = -Math.sin(offset) * value2;
         MinecraftAccessor.minecraft.thePlayer.motionZ = Math.cos(offset) * value2;
      }
   }

   public static double computeValue(double offset) {
      return MinecraftAccessor.minecraft.thePlayer.checkPotion(Potion.potion9)
         ? offset + (double)((float)(MinecraftAccessor.minecraft.thePlayer.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F)
         : offset;
   }

   public static void applyDouble2(double offset) {
      applyDouble(offset, 1.0F);
   }

   public static void applyDouble(double offset, float factor, double offset2, double offset3) {
      float factor2 = factor;
      double offset4 = offset2;
      double offset5 = offset3;
      if (offset3 != 0.0) {
         if (offset2 > 0.0) {
            factor2 = factor + (float)(offset3 > 0.0 ? -45 : 45);
         } else if (offset2 < 0.0) {
            factor2 = factor + (float)(offset3 > 0.0 ? 45 : -45);
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

   public static void applyDouble(double offset) {
      applyDouble(
         offset,
         MinecraftAccessor.minecraft.thePlayer.rotationYaw,
         (double)MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveStrafe,
         (double)MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveForward
      );
   }

   public static double getValue() {
      double offset = (double)MinecraftAccessor.minecraft.thePlayer.invulnerableManager.getFactor2() * 2.873;
      if (MinecraftAccessor.minecraft.thePlayer.checkPotion(Potion.potion3)) {
         offset /= 1.0 + 0.2 * (double)(MinecraftAccessor.minecraft.thePlayer.getPotionEffect(Potion.potion3).getIndexOffset2() + 1);
      }

      if (MinecraftAccessor.minecraft.thePlayer.checkPotion(Potion.potion2)) {
         offset *= 1.0 + 0.2 * (double)(MinecraftAccessor.minecraft.thePlayer.getPotionEffect(Potion.potion2).getIndexOffset2() + 1);
      }

      return offset;
   }

   public static void updateState() {
      processAction(1.0F);
   }

   public static boolean canExecute2() {
      return MinecraftAccessor.minecraft.thePlayer.motionX != 0.0 || MinecraftAccessor.minecraft.thePlayer.motionZ != 0.0;
   }

   public static void processAction(double offset, boolean flag) {
      float factor = computeFactor(flag) * (float) (Math.PI / 180.0);
      MinecraftAccessor.minecraft.thePlayer.motionX = MinecraftAccessor.minecraft.thePlayer.motionX - (double)MathHelper.abs(factor) * offset;
      MinecraftAccessor.minecraft.thePlayer.motionZ = MinecraftAccessor.minecraft.thePlayer.motionZ + (double)MathHelper.cos(factor) * offset;
   }

   public static void onMoveEntityByEventMoveEntity(double offset, boolean flag, EventMoveEntity event) {
      float factor = computeFactor(flag) * (float) (Math.PI / 180.0);
      double offset2 = event.getCoordOffset2() - (double)MathHelper.abs(factor) * offset;
      event.setCoordOffset2(offset2);
      double offset3 = event.getCoordOffset() + (double)MathHelper.cos(factor) * offset;
      event.setCoordOffset(offset3);
   }

   public static double[] resolveDoubleArray(double offset, float posX, float posY, float posZ) {
      float factor = (float)offset;
      float factor2 = MathHelper.abs(posZ * (float) Math.PI / 180.0F);
      float factor3 = MathHelper.cos(posZ * (float) Math.PI / 180.0F);
      double offset2 = (double)(posX * factor * factor3 - posY * factor * factor2);
      double offset3 = (double)(posY * factor * factor3 + posX * factor * factor2);
      return new double[]{offset2, offset3};
   }

   public static double[] resolveDoubleArray(double offset, float factor) {
      float factor2 = 0.0F;
      float scaleFactor = MinecraftAccessor.minecraft.thePlayer.scaleFactor3;
      float factor3 = (float)offset;
      float factor4 = factor2 * factor2 + scaleFactor * scaleFactor;
      if (factor4 >= 1.0E-4F) {
         float factor5 = MathHelper.sin(factor4);
         if (factor5 < 1.0F) {
            factor5 = 1.0F;
         }

         float factor6 = factor3 / factor5;
         float factor7 = factor2 * factor6;
         float factor8 = scaleFactor * factor6;
         float factor9 = MathHelper.abs(factor * (float) Math.PI / 180.0F);
         float factor10 = MathHelper.cos(factor * (float) Math.PI / 180.0F);
         return new double[]{(double)(factor7 * factor10 - factor8 * factor9), (double)(factor8 * factor10 + factor7 * factor9)};
      } else {
         return new double[]{0.0, 0.0};
      }
   }

   public static void processDouble2(double offset, double offset2, float factor, boolean flag) {
      float factor2 = factor;
      float factor3 = MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor;
      float factor4 = MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.factor2;
      float factor5 = (float)offset;
      if (factor3 != 0.0F && factor4 != 0.0F) {
         if (factor3 > 0.0F) {
            factor2 = (float)(factor4 > 0.0F ? (double)factor - offset2 : (double)factor + offset2);
         } else {
            factor2 = (float)(factor4 > 0.0F ? (double)factor + offset2 : (double)factor - offset2);
         }

         factor3 = 0.0F;
      }

      float factor6 = MathHelper.abs(factor2 * (float) Math.PI / 180.0F);
      float factor7 = MathHelper.cos(factor2 * (float) Math.PI / 180.0F);
      MinecraftAccessor.minecraft.thePlayer.motionX = (double)(factor3 * factor5 * factor7 - factor4 * factor5 * factor6);
      MinecraftAccessor.minecraft.thePlayer.motionZ = (double)(factor4 * factor5 * factor7 + factor3 * factor5 * factor6);
      if (flag && MinecraftAccessor.minecraft.thePlayer.isRiding()) {
         float factor8 = factor2 * (float) (Math.PI / 180.0);
         MinecraftAccessor.minecraft.thePlayer.motionX = MinecraftAccessor.minecraft.thePlayer.motionX - (double)(MathHelper.abs(factor8) * 0.2F);
         MinecraftAccessor.minecraft.thePlayer.motionZ = MinecraftAccessor.minecraft.thePlayer.motionZ + (double)(MathHelper.cos(factor8) * 0.2F);
      }
   }

   public static double getDouble7(float posX, float posY, float posZ) {
      float factor = posZ;
      if (posX < 0.0F) {
         factor = posZ + 180.0F;
      }

      float factor2 = 1.0F;
      if (posX < 0.0F) {
         factor2 = -0.5F;
      } else if (posX > 0.0F) {
         factor2 = 0.5F;
      }

      if (posY > 0.0F) {
         factor -= 90.0F * factor2;
      }

      if (posY < 0.0F) {
         factor += 90.0F * factor2;
      }

      return Math.toRadians((double)factor);
   }
}
