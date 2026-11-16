package client.util;

import com.google.common.base.MoreObjects;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public class DistanceAggregator {
   public int count3;
   public int count6;
   public int count4;
   public int count5;
   public int count2;
   public int count;

   public Vec3i getVec3i() {
      return new Vec3i(this.count5 - this.count3, this.count2 - this.count6, this.count - this.count4);
   }

   public static DistanceAggregator resolveDistanceAggregator(
      int count7, int count8, int count9, int count10, int count11, int count12, int count13, int count14, int count15, EnumFacing enumFacing
   ) {
      switch (DistanceAggregatorEnumFacingSwitchMap.countArray[enumFacing.ordinal()]) {
         case 1:
            return new DistanceAggregator(count7 + count10, count8 + count11, count9 - count15 + 1 + count12, count7 + count13 - 1 + count10, count8 + count14 - 1 + count11, count9 + count12);
         case 2:
            return new DistanceAggregator(count7 + count10, count8 + count11, count9 + count12, count7 + count13 - 1 + count10, count8 + count14 - 1 + count11, count9 + count15 - 1 + count12);
         case 3:
            return new DistanceAggregator(count7 - count15 + 1 + count12, count8 + count11, count9 + count10, count7 + count12, count8 + count14 - 1 + count11, count9 + count13 - 1 + count10);
         case 4:
            return new DistanceAggregator(count7 + count12, count8 + count11, count9 + count10, count7 + count15 - 1 + count12, count8 + count14 - 1 + count11, count9 + count13 - 1 + count10);
         default:
            return new DistanceAggregator(count7 + count10, count8 + count11, count9 + count12, count7 + count13 - 1 + count10, count8 + count14 - 1 + count11, count9 + count15 - 1 + count12);
      }
   }

   public DistanceAggregator() {
   }

   public DistanceAggregator(int[] countArray) {
      if (countArray.length == 6) {
         this.count3 = countArray[0];
         this.count6 = countArray[1];
         this.count4 = countArray[2];
         this.count5 = countArray[3];
         this.count2 = countArray[4];
         this.count = countArray[5];
      }
   }

   public int getCount2() {
      return this.count2 - this.count6 + 1;
   }

   public boolean matchesVec3i(Vec3i vec) {
      return vec.getX() >= this.count3
         && vec.getX() <= this.count5
         && vec.getZ() >= this.count4
         && vec.getZ() <= this.count
         && vec.getY() >= this.count6
         && vec.getY() <= this.count2;
   }

   public void applyIndex(int count7, int count8, int count9) {
      this.count3 += count7;
      this.count6 += count8;
      this.count4 += count9;
      this.count5 += count7;
      this.count2 += count8;
      this.count += count9;
   }

   public int getCount() {
      return this.count - this.count4 + 1;
   }

   public DistanceAggregator(Vec3i vec, Vec3i vec2) {
      this.count3 = Math.min(vec.getX(), vec2.getX());
      this.count6 = Math.min(vec.getY(), vec2.getY());
      this.count4 = Math.min(vec.getZ(), vec2.getZ());
      this.count5 = Math.max(vec.getX(), vec2.getX());
      this.count2 = Math.max(vec.getY(), vec2.getY());
      this.count = Math.max(vec.getZ(), vec2.getZ());
   }

   public static DistanceAggregator resolveDistanceAggregator(int count7, int count8, int count9, int count10, int count11, int count12) {
      return new DistanceAggregator(
         Math.min(count7, count10), Math.min(count8, count11), Math.min(count9, count12), Math.max(count7, count10), Math.max(count8, count11), Math.max(count9, count12)
      );
   }

   public boolean checkIndex(int count7, int count8, int count9, int count10) {
      return this.count5 >= count7 && this.count3 <= count9 && this.count >= count8 && this.count4 <= count10;
   }

   public DistanceAggregator(int count7, int count8, int count9, int count10, int count11, int count12) {
      this.count3 = count7;
      this.count6 = count8;
      this.count4 = count9;
      this.count5 = count10;
      this.count2 = count11;
      this.count = count12;
   }

   public Vec3i getVec3iAsVec3i() {
      return new BlockPos(
         this.count3 + (this.count5 - this.count3 + 1) / 2, this.count6 + (this.count2 - this.count6 + 1) / 2, this.count4 + (this.count - this.count4 + 1) / 2
      );
   }

   public void applyDistanceAggregator(DistanceAggregator distanceAggregator) {
      this.count3 = Math.min(this.count3, distanceAggregator.count3);
      this.count6 = Math.min(this.count6, distanceAggregator.count6);
      this.count4 = Math.min(this.count4, distanceAggregator.count4);
      this.count5 = Math.max(this.count5, distanceAggregator.count5);
      this.count2 = Math.max(this.count2, distanceAggregator.count2);
      this.count = Math.max(this.count, distanceAggregator.count);
   }

   public DistanceAggregator(int count7, int count8, int count9, int count10) {
      this.count3 = count7;
      this.count4 = count8;
      this.count5 = count9;
      this.count = count10;
      this.count6 = 1;
      this.count2 = 512;
   }

   public ProviderIntArrayEntry getProviderIntArrayEntry() {
      return new ProviderIntArrayEntry(new int[]{this.count3, this.count6, this.count4, this.count5, this.count2, this.count});
   }

   public boolean matchesDistanceAggregator(DistanceAggregator distanceAggregator) {
      return this.count5 >= distanceAggregator.count3
         && this.count3 <= distanceAggregator.count5
         && this.count >= distanceAggregator.count4
         && this.count4 <= distanceAggregator.count
         && this.count2 >= distanceAggregator.count6
         && this.count6 <= distanceAggregator.count2;
   }

   public static DistanceAggregator getDistanceAggregator() {
      return new DistanceAggregator(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this)
         .add("x0", this.count3)
         .add("y0", this.count6)
         .add("z0", this.count4)
         .add("x1", this.count5)
         .add("y1", this.count2)
         .add("z1", this.count)
         .toString();
   }

   public int getCountAsIndex() {
      return this.count5 - this.count3 + 1;
   }

   public DistanceAggregator(DistanceAggregator distanceAggregator) {
      this.count3 = distanceAggregator.count3;
      this.count6 = distanceAggregator.count6;
      this.count4 = distanceAggregator.count4;
      this.count5 = distanceAggregator.count5;
      this.count2 = distanceAggregator.count2;
      this.count = distanceAggregator.count;
   }
}
