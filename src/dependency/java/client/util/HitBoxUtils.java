package client.util;

import client.setting.Setting;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class HitBoxUtils {
   public static double getDouble(double offset, double offset2) {
      double doubleItem = TrajectoryPredictor.getDouble4(offset, offset2);
      double doubleItem2 = MathHelper.getDouble4(offset + doubleItem);
      double doubleItem3 = MathHelper.getDouble4(offset - doubleItem);
      if (TrajectoryPredictor.getDouble4(doubleItem2, offset2) > TrajectoryPredictor.getDouble4(doubleItem3, offset2)) {
         doubleItem = -doubleItem;
      }

      return doubleItem;
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   public static AxisAlignedBB getAxisAlignedBB(Vec3 vec, boolean flag) {
      if (vec == null) {
         return null;
      } else {
         float factor = 0.3F;
         double offset = 0.1F;
         if (!flag) {
            offset = 0.0;
         }

         return new AxisAlignedBB(
            vec.xCoord - (double)factor - offset,
            vec.yCoord - offset,
            vec.zCoord - (double)factor - offset,
            vec.xCoord + (double)factor + offset,
            vec.yCoord + 1.8F + offset,
            vec.zCoord + (double)factor + offset
         );
      }
   }
}
