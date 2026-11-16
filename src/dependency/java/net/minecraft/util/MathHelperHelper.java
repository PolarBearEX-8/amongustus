package net.minecraft.util;

import client.util.AltFloatEntry;
import client.util.COSACOSEnum;

public class MathHelperHelper {
   public static void println(String text) {
      System.out.println(text);
   }

   public static void main(String[] textArray) throws Exception {
      COSACOSEnum[] values = COSACOSEnum.values();

      for (int index = 0; index < values.length; index++) {
         COSACOSEnum cOSACOSEnum = values[index];
         println("******** " + cOSACOSEnum + " ***********");
         applyCOSACOSEnum(cOSACOSEnum, false);
      }
   }

   private static void applyCOSACOSEnum(COSACOSEnum cOSACOSEnum, boolean flag) {
      MathHelper.state = flag;
      double offset;
      double offset2;
      switch (MathHelperHelperCOSACOSEnumSwitchMap.countArray[cOSACOSEnum.ordinal()]) {
         case 1:
         case 2:
            offset = (float) -Math.PI;
            offset2 = (float) Math.PI;
            break;
         case 3:
         case 4:
            offset = -1.0;
            offset2 = 1.0;
            break;
         default:
            return;
      }

      for (int index = 0; index <= 10; index++) {
         double offset3 = offset + (double)index * (offset2 - offset) / (double)10;
         float factor;
         float factor2;
         switch (MathHelperHelperCOSACOSEnumSwitchMap.countArray[cOSACOSEnum.ordinal()]) {
            case 1:
               factor = (float)Math.sin(offset3);
               factor2 = MathHelper.abs((float)offset3);
               break;
            case 2:
               factor = (float)Math.cos(offset3);
               factor2 = MathHelper.cos((float)offset3);
               break;
            case 3:
               factor = (float)Math.asin(offset3);
               factor2 = AltFloatEntry.computeFactorByFloat((float)offset3);
               break;
            case 4:
               factor = (float)Math.acos(offset3);
               factor2 = AltFloatEntry.computeFactor2((float)offset3);
               break;
            default:
               return;
         }

         println(String.format("%.2f, Math: %f, Helper: %f, diff: %f", offset3, factor, factor2, Math.abs(factor - factor2)));
      }
   }
}
