package baritone.api.utils;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;

public final class BetterBlockPos extends BlockPos {
   private static final int NUM_X_BITS2 = 26;
   private static final int NUM_Z_BITS2 = 26;
   private static final int NUM_Y_BITS2 = 12;
   private static final int Y_SHIFT2 = 26;
   private static final int X_SHIFT2 = 38;
   private static final long X_MASK2 = 67108863L;
   private static final long Y_MASK2 = 4095L;
   private static final long Z_MASK2 = 67108863L;
   public static final BetterBlockPos ORIGIN2 = new BetterBlockPos(0, 0, 0);
   public final int x2;
   public final int y2;
   public final int z2;

   @Override
   public BlockPos south(int n) {
      return this.south2(n);
   }

   public static long longHash(BetterBlockPos betterBlockPos) {
      return longHash(betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2);
   }

   @Override
   public BlockPos north() {
      return this.north2();
   }

   public BetterBlockPos south2(int n) {
      return n == 0 ? this : new BetterBlockPos(this.x2, this.y2, this.z2 + n);
   }

   public BetterBlockPos west2() {
      return new BetterBlockPos(this.x2 - 1, this.y2, this.z2);
   }

   public BetterBlockPos(double d, double d2, double d3) {
      this(MathHelper.floor_double(d), MathHelper.floor_double(d2), MathHelper.floor_double(d3));
   }

   public BetterBlockPos offset2(EnumFacing facing) {
      Vec3i directionVec = facing.getDirectionVec();
      return new BetterBlockPos(this.x2 + directionVec.getX(), this.y2 + directionVec.getY(), this.z2 + directionVec.getZ());
   }

   public static BetterBlockPos deserializeFromLong(long l) {
      int n = (int)(l << 0 >> 38);
      int n2 = (int)(l << 26 >> 52);
      int n3 = (int)(l << 38 >> 38);
      return new BetterBlockPos(n, n2, n3);
   }

   @Override
   public BlockPos west(int n) {
      return this.west2(n);
   }

   public static BetterBlockPos from(BlockPos pos) {
      return pos == null ? null : new BetterBlockPos(pos);
   }

   public BetterBlockPos(BlockPos pos) {
      this(pos.getX(), pos.getY(), pos.getZ());
   }

   @Override
   public BlockPos down(int n) {
      return this.down2(n);
   }

   public BetterBlockPos north2(int n) {
      return n == 0 ? this : new BetterBlockPos(this.x2, this.y2, this.z2 - n);
   }

   @Override
   public BlockPos east(int n) {
      return this.east2(n);
   }

   @Override
   public int hashCode() {
      return (int)longHash(this.x2, this.y2, this.z2);
   }

   public BetterBlockPos(int n, int n2, int n3) {
      super(n, n2, n3);
      this.x2 = n;
      this.y2 = n2;
      this.z2 = n3;
   }

   public BetterBlockPos up2(int n) {
      return n == 0 ? this : new BetterBlockPos(this.x2, this.y2 + n, this.z2);
   }

   public static long longHash(int n, int n2, int n3) {
      long l = 3241L;
      l = 3457689L * l + (long)n;
      l = 8734625L * l + (long)n2;
      return 2873465L * l + (long)n3;
   }

   @Override
   public BlockPos down() {
      return this.down2();
   }

   public BetterBlockPos east2() {
      return new BetterBlockPos(this.x2 + 1, this.y2, this.z2);
   }

   public BetterBlockPos down2(int n) {
      return n == 0 ? this : new BetterBlockPos(this.x2, this.y2 - n, this.z2);
   }

   public static long serializeToLong(int n, int n2, int n3) {
      return ((long)n & 67108863L) << 38 | ((long)n2 & 4095L) << 26 | (long)n3 & 67108863L;
   }

   @Override
   public String toString() {
      return String.format(
         "BetterBlockPos{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x2), SettingsUtil.maybeCensor(this.y2), SettingsUtil.maybeCensor(this.z2)
      );
   }

   public double distanceSq2(BetterBlockPos betterBlockPos) {
      double d = (double)this.x2 - (double)betterBlockPos.x2;
      double d2 = (double)this.y2 - (double)betterBlockPos.y2;
      double d3 = (double)this.z2 - (double)betterBlockPos.z2;
      return d * d + d2 * d2 + d3 * d3;
   }

   @Override
   public BlockPos up() {
      return this.up2();
   }

   @Override
   public BlockPos north(int n) {
      return this.north2(n);
   }

   public BetterBlockPos west2(int n) {
      return n == 0 ? this : new BetterBlockPos(this.x2 - n, this.y2, this.z2);
   }

   @Override
   public BlockPos east() {
      return this.east2();
   }

   @Override
   public BlockPos west() {
      return this.west2();
   }

   public BetterBlockPos offset2(EnumFacing facing, int n) {
      if (n == 0) {
         return this;
      } else {
         Vec3i directionVec = facing.getDirectionVec();
         return new BetterBlockPos(this.x2 + directionVec.getX() * n, this.y2 + directionVec.getY() * n, this.z2 + directionVec.getZ() * n);
      }
   }

   @Override
   public BlockPos offset(EnumFacing facing, int n) {
      return this.offset2(facing, n);
   }

   @Override
   public BlockPos offset(EnumFacing facing) {
      return this.offset2(facing);
   }

   public BetterBlockPos east2(int n) {
      return n == 0 ? this : new BetterBlockPos(this.x2 + n, this.y2, this.z2);
   }

   public double distance(BetterBlockPos betterBlockPos) {
      double d = (double)this.x2 - (double)betterBlockPos.x2;
      double d2 = (double)this.y2 - (double)betterBlockPos.y2;
      double d3 = (double)this.z2 - (double)betterBlockPos.z2;
      return Math.sqrt(d * d + d2 * d2 + d3 * d3);
   }

   public BetterBlockPos down2() {
      return new BetterBlockPos(this.x2, this.y2 - 1, this.z2);
   }

   public BetterBlockPos south2() {
      return new BetterBlockPos(this.x2, this.y2, this.z2 + 1);
   }

   public BetterBlockPos north2() {
      return new BetterBlockPos(this.x2, this.y2, this.z2 - 1);
   }

   public BetterBlockPos up2() {
      return new BetterBlockPos(this.x2, this.y2 + 1, this.z2);
   }

   @Override
   public BlockPos up(int n) {
      return this.up2(n);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (obj instanceof BetterBlockPos) {
         BetterBlockPos betterBlockPos = (BetterBlockPos)obj;
         return betterBlockPos.x2 == this.x2 && betterBlockPos.y2 == this.y2 && betterBlockPos.z2 == this.z2;
      } else {
         BlockPos pos = (BlockPos)obj;
         return pos.getX() == this.x2 && pos.getY() == this.y2 && pos.getZ() == this.z2;
      }
   }

   @Override
   public BlockPos south() {
      return this.south2();
   }
}
