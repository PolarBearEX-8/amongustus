package cc.baritone.utils;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public class DepthFirstMonitor implements ManagedListener {
   public final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos;
   public final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2;
   public final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos3;
   public final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos4;
   public final Vec3i vec3i;
   public final AxisAlignedBB axisAlignedBB;

   @Override
   public ManagedListener resolveManagedListener2(EnumFacing enumFacing, int count) {
      return this.matchesEnumFacing(enumFacing)
         ? new DepthFirstMonitor(this.betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(enumFacing, count), this.betterBlockPosXSYSZSBlockPos2)
         : new DepthFirstMonitor(this.betterBlockPosXSYSZSBlockPos, this.betterBlockPosXSYSZSBlockPos2.resolveBetterBlockPosXSYSZSBlockPos(enumFacing, count));
   }

   @Override
   public String toString() {
      return String.format("Selection{pos1=%s,pos2=%s}", this.betterBlockPosXSYSZSBlockPos, this.betterBlockPosXSYSZSBlockPos2);
   }

   @Override
   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos() {
      return this.betterBlockPosXSYSZSBlockPos;
   }

   private boolean matchesEnumFacing(EnumFacing enumFacing) {
      boolean flag = enumFacing.getEnumFacingPositiveType().getCount() < 0;
      switch (DepthFirstMonitorEnumFacingXTypeSwitchMap.countArray[enumFacing.getEnumFacingXType().ordinal()]) {
         case 1:
            return this.betterBlockPosXSYSZSBlockPos2.count9 > this.betterBlockPosXSYSZSBlockPos.count9 ^ flag;
         case 2:
            return this.betterBlockPosXSYSZSBlockPos2.count12 > this.betterBlockPosXSYSZSBlockPos.count12 ^ flag;
         case 3:
            return this.betterBlockPosXSYSZSBlockPos2.count10 > this.betterBlockPosXSYSZSBlockPos.count10 ^ flag;
         default:
            throw new IllegalStateException("Bad EnumFacing.Axis");
      }
   }

   @Override
   public AxisAlignedBB getAxisAlignedBB() {
      return this.axisAlignedBB;
   }

   @Override
   public ManagedListener resolveManagedListener(EnumFacing enumFacing, int count) {
      return new DepthFirstMonitor(
         this.betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(enumFacing, count),
         this.betterBlockPosXSYSZSBlockPos2.resolveBetterBlockPosXSYSZSBlockPos(enumFacing, count)
      );
   }

   @Override
   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos2() {
      return this.betterBlockPosXSYSZSBlockPos2;
   }

   @Override
   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos3() {
      return this.betterBlockPosXSYSZSBlockPos3;
   }

   public DepthFirstMonitor(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos5, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos6) {
      this.betterBlockPosXSYSZSBlockPos = betterBlockPosXSYSZSBlockPos5;
      this.betterBlockPosXSYSZSBlockPos2 = betterBlockPosXSYSZSBlockPos6;
      this.betterBlockPosXSYSZSBlockPos3 = new BetterBlockPosXSYSZSBlockPos(
         Math.min(betterBlockPosXSYSZSBlockPos5.count9, betterBlockPosXSYSZSBlockPos6.count9), Math.min(betterBlockPosXSYSZSBlockPos5.count12, betterBlockPosXSYSZSBlockPos6.count12), Math.min(betterBlockPosXSYSZSBlockPos5.count10, betterBlockPosXSYSZSBlockPos6.count10)
      );
      this.betterBlockPosXSYSZSBlockPos4 = new BetterBlockPosXSYSZSBlockPos(
         Math.max(betterBlockPosXSYSZSBlockPos5.count9, betterBlockPosXSYSZSBlockPos6.count9), Math.max(betterBlockPosXSYSZSBlockPos5.count12, betterBlockPosXSYSZSBlockPos6.count12), Math.max(betterBlockPosXSYSZSBlockPos5.count10, betterBlockPosXSYSZSBlockPos6.count10)
      );
      this.vec3i = new Vec3i(
         this.betterBlockPosXSYSZSBlockPos4.count9 - this.betterBlockPosXSYSZSBlockPos3.count9 + 1,
         this.betterBlockPosXSYSZSBlockPos4.count12 - this.betterBlockPosXSYSZSBlockPos3.count12 + 1,
         this.betterBlockPosXSYSZSBlockPos4.count10 - this.betterBlockPosXSYSZSBlockPos3.count10 + 1
      );
      this.axisAlignedBB = new AxisAlignedBB(this.betterBlockPosXSYSZSBlockPos3, this.betterBlockPosXSYSZSBlockPos4.add(1, 1, 1));
   }

   @Override
   public int hashCode() {
      return this.betterBlockPosXSYSZSBlockPos.hashCode() ^ this.betterBlockPosXSYSZSBlockPos2.hashCode();
   }

   @Override
   public ManagedListener resolveManagedListenerByEnumFacing(EnumFacing enumFacing, int count) {
      return this.matchesEnumFacing(enumFacing)
         ? new DepthFirstMonitor(this.betterBlockPosXSYSZSBlockPos, this.betterBlockPosXSYSZSBlockPos2.resolveBetterBlockPosXSYSZSBlockPos(enumFacing, count))
         : new DepthFirstMonitor(this.betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(enumFacing, count), this.betterBlockPosXSYSZSBlockPos2);
   }

   @Override
   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos4() {
      return this.betterBlockPosXSYSZSBlockPos4;
   }

   @Override
   public Vec3i getVec3i() {
      return this.vec3i;
   }
}
