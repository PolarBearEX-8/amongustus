package client.util;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class GeometryRanker {
   public static CoreDoubleEntry[] resolveCoreDoubleEntryArrayByListener72Entry(
      Listener72Entry listener72Entry, EntityPlayerSP player, EntityLivingBase livingEntity, int count, int count2, int count3, double offset, int count4
   ) {
      ArrayListData arrayListData = new ArrayListData(count3);
      int count5 = count - count3 + 1;
      double offset2 = 0.0;
      double offset3 = 0.0;
      double offset4 = 0.0;

      for (int index = count5; index <= count; index++) {
         Vec3 vec = listener72Entry.resolveVec3(player, count, index);
         AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(listener72Entry.resolveVec3(livingEntity, count, index), true);
         double doubleItem = MathHelper.getDouble4(TrajectoryPredictor.getCoordOffsetArray2(vec, axisAlignedBB)[count4]);
         if (index == count5) {
            offset3 = doubleItem;
            offset4 = doubleItem;
            double offset5 = 0.0;
            arrayListData.processAction(offset5);
         } else {
            double doubleItem2 = HitBoxUtils.getDouble(offset3, doubleItem);
            if (axisAlignedBB.checkDouble(vec.xCoord, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0, vec.zCoord) || Math.abs(doubleItem2) > 90.0) {
               break;
            }

            offset2 += doubleItem2;
            offset3 = doubleItem;
            arrayListData.processAction(offset2);
         }
      }

      int count6 = count2 - count5;
      if (arrayListData.getItemList().size() < 2) {
         return null;
      } else {
         double[] doubleArray = arrayListData.getDoubleArray();
         double offset6 = doubleArray[0];
         double offset7 = doubleArray[1];
         double offset8 = Math.abs(offset6);
         double offset9 = Math.abs(offset7);
         return new CoreDoubleEntry[]{
            new CoreDoubleEntry(offset8, offset9, MathHelper.getDouble4(offset8 * (double)count6 + offset9 + offset4), offset4, count6),
            new CoreDoubleEntry(-offset8, -offset9, MathHelper.getDouble4(-offset8 * (double)count6 - offset9 + offset4), offset4, count6)
         };
      }
   }
}
