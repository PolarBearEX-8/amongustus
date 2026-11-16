package cc.baritone.utils;

import cc.SettingsUtil;
import javax.annotation.Nonnull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;

public final class BetterBlockPosXSYSZSBlockPos extends BlockPos {
   public static final int count16 = 26;
   public static final int count11 = 26;
   public static final int count14 = 12;
   public static final int count15 = 26;
   public static final int count13 = 38;
   public static final long timestamp4 = 4095L;
   public static final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = new BetterBlockPosXSYSZSBlockPos(0, 0, 0);
   public final int count9;
   public final int count12;
   public final int count10;

   @Override
   public BlockPos resolveBlockPos4(int count) {
      return this.resolveBetterBlockPosXSYSZSBlockPos4(count);
   }

   public static BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos(BlockPos blockPos) {
      return blockPos == null ? null : new BetterBlockPosXSYSZSBlockPos(blockPos);
   }

   @Override
   public BlockPos getBlockPos() {
      return this.getBetterBlockPosXSYSZSBlockPos();
   }

   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos() {
      return new BetterBlockPosXSYSZSBlockPos(this.count9 - 1, this.count12, this.count10);
   }

   public BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos2(int count) {
      return count == 0 ? this : new BetterBlockPosXSYSZSBlockPos(this.count9, this.count12 + count, this.count10);
   }

   public BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos(EnumFacing enumFacing) {
      Vec3i vec3i = enumFacing.getVec3i();
      return new BetterBlockPosXSYSZSBlockPos(this.count9 + vec3i.getX(), this.count12 + vec3i.getY(), this.count10 + vec3i.getZ());
   }

   public static long computeTimestamp(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      return computeTimestampByIndex(betterBlockPosXSYSZSBlockPos2.count9, betterBlockPosXSYSZSBlockPos2.count12, betterBlockPosXSYSZSBlockPos2.count10);
   }

   @Override
   public BlockPos offset(EnumFacing enumFacing) {
      return this.resolveBetterBlockPosXSYSZSBlockPos(enumFacing);
   }

   @Override
   public BlockPos resolveBlockPosByIndex(int count) {
      return this.resolveBetterBlockPosXSYSZSBlockPosByIndex(count);
   }

   public BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPosByIndex(int count) {
      return count == 0 ? this : new BetterBlockPosXSYSZSBlockPos(this.count9 + count, this.count12, this.count10);
   }

   @Override
   public BlockPos up() {
      return this.getBetterBlockPosXSYSZSBlockPos3();
   }

   public BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos(int count) {
      return count == 0 ? this : new BetterBlockPosXSYSZSBlockPos(this.count9, this.count12, this.count10 - count);
   }

   public double computeValue(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      double offset = (double)this.count9 - (double)betterBlockPosXSYSZSBlockPos2.count9;
      double offset2 = (double)this.count12 - (double)betterBlockPosXSYSZSBlockPos2.count12;
      double offset3 = (double)this.count10 - (double)betterBlockPosXSYSZSBlockPos2.count10;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   @Override
   public BlockPos resolveBlockPos(int count) {
      return this.resolveBetterBlockPosXSYSZSBlockPos3(count);
   }

   public BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos4(int count) {
      return count == 0 ? this : new BetterBlockPosXSYSZSBlockPos(this.count9, this.count12 - count, this.count10);
   }

   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos2() {
      return new BetterBlockPosXSYSZSBlockPos(this.count9 + 1, this.count12, this.count10);
   }

   @Override
   public BlockPos resolveBlockPos3(int count) {
      return this.resolveBetterBlockPosXSYSZSBlockPos(count);
   }

   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos4() {
      return new BetterBlockPosXSYSZSBlockPos(this.count9, this.count12 - 1, this.count10);
   }

   public static long computeTimestamp(int count, int count2, int count3) {
      return ((long)count & 67108863L) << 38 | ((long)count2 & 4095L) << 26 | (long)count3 & 67108863L;
   }

   public static long computeTimestampByIndex(int count, int count2, int count3) {
      long value = 11206370049L + (long)count;
      long value2 = 8734625L * value + (long)count2;
      return 2873465L * value2 + (long)count3;
   }

   @Override
   public BlockPos resolveBlockPos5(int count) {
      return this.resolveBetterBlockPosXSYSZSBlockPos5(count);
   }

   public BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos5(int count) {
      return count == 0 ? this : new BetterBlockPosXSYSZSBlockPos(this.count9 - count, this.count12, this.count10);
   }

   @Override
   public BlockPos getBlockPos3() {
      return this.getBetterBlockPosXSYSZSBlockPos2();
   }

   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos5() {
      return new BetterBlockPosXSYSZSBlockPos(this.count9, this.count12, this.count10 - 1);
   }

   @Override
   public BlockPos getBlockPos(EnumFacing enumFacing, int count) {
      return this.resolveBetterBlockPosXSYSZSBlockPos(enumFacing, count);
   }

   @Override
   public BlockPos down() {
      return this.getBetterBlockPosXSYSZSBlockPos4();
   }

   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos3() {
      return new BetterBlockPosXSYSZSBlockPos(this.count9, this.count12 + 1, this.count10);
   }

   @Override
   public BlockPos getBlockPos2() {
      return this.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
   }

   public BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos(EnumFacing enumFacing, int count) {
      if (count == 0) {
         return this;
      } else {
         Vec3i vec3i = enumFacing.getVec3i();
         return new BetterBlockPosXSYSZSBlockPos(this.count9 + vec3i.getX() * count, this.count12 + vec3i.getY() * count, this.count10 + vec3i.getZ() * count);
      }
   }

   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS() {
      return new BetterBlockPosXSYSZSBlockPos(this.count9, this.count12, this.count10 + 1);
   }

   public BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos3(int count) {
      return count == 0 ? this : new BetterBlockPosXSYSZSBlockPos(this.count9, this.count12, this.count10 + count);
   }

   @Override
   public BlockPos resolveBlockPos2(int count) {
      return this.resolveBetterBlockPosXSYSZSBlockPos2(count);
   }

   public double computeValueByBetterBlockPosXS(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      double offset = (double)this.count9 - (double)betterBlockPosXSYSZSBlockPos2.count9;
      double offset2 = (double)this.count12 - (double)betterBlockPosXSYSZSBlockPos2.count12;
      double offset3 = (double)this.count10 - (double)betterBlockPosXSYSZSBlockPos2.count10;
      return Math.sqrt(offset * offset + offset2 * offset2 + offset3 * offset3);
   }

   public static BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos(long value) {
      int count = (int)(value << 0 >> 38);
      int count2 = (int)(value << 26 >> 52);
      int count3 = (int)(value << 38 >> 38);
      return new BetterBlockPosXSYSZSBlockPos(count, count2, count3);
   }

   @Override
   public BlockPos getBlockPosAsBlockPos() {
      return this.getBetterBlockPosXSYSZSBlockPos5();
   }

   public BetterBlockPosXSYSZSBlockPos(int count, int count2, int count3) {
      super(count, count2, count3);
      this.count9 = count;
      this.count12 = count2;
      this.count10 = count3;
   }

   public BetterBlockPosXSYSZSBlockPos(BlockPos blockPos) {
      this(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   public BetterBlockPosXSYSZSBlockPos(double posX, double posY, double posZ) {
      this(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY), MathHelper.ceiling_double_int(posZ));
   }

   @Override
   public boolean equals(Object other) {
      if (other == null) {
         return false;
      } else if (other instanceof BetterBlockPosXSYSZSBlockPos) {
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = (BetterBlockPosXSYSZSBlockPos)other;
         return betterBlockPosXSYSZSBlockPos2.count9 == this.count9 && betterBlockPosXSYSZSBlockPos2.count12 == this.count12 && betterBlockPosXSYSZSBlockPos2.count10 == this.count10;
      } else {
         BlockPos blockPos = (BlockPos)other;
         return blockPos.getX() == this.count9 && blockPos.getY() == this.count12 && blockPos.getZ() == this.count10;
      }
   }

   @Nonnull
   @Override
   public String toString() {
      return String.format(
         "BetterBlockPos{x=%s,y=%s,z=%s}",
         SettingsUtil.maybeCensor(this.count9),
         SettingsUtil.maybeCensor(this.count12),
         SettingsUtil.maybeCensor(this.count10)
      );
   }

   @Override
   public int hashCode() {
      return (int)computeTimestampByIndex(this.count9, this.count12, this.count10);
   }
}
