package net.minecraft.village;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class VillageDoorInfo {
   private final BlockPos doorBlockPos;
   private final BlockPos insideBlock;
   private final EnumFacing insideDirection;
   private int lastActivityTimestamp;
   private boolean isDetachedFromVillageFlag;
   private int doorOpeningRestrictionCounter;

   public void resetDoorOpeningRestrictionCounter() {
      this.doorOpeningRestrictionCounter = 0;
   }

   public int getDistanceToDoorBlockSq(BlockPos pos) {
      return (int)pos.distanceSq(this.getDoorBlockPos());
   }

   public BlockPos getDoorBlockPos() {
      return this.doorBlockPos;
   }

   public BlockPos getInsideBlockPos() {
      return this.insideBlock;
   }

   public VillageDoorInfo(BlockPos pos, EnumFacing facing, int n) {
      this.doorBlockPos = pos;
      this.insideDirection = facing;
      this.insideBlock = pos.offset(facing, 2);
      this.lastActivityTimestamp = n;
   }

   public void setIsDetachedFromVillageFlag(boolean flag) {
      this.isDetachedFromVillageFlag = flag;
   }

   public void incrementDoorOpeningRestrictionCounter() {
      this.doorOpeningRestrictionCounter++;
   }

   public boolean isInsideSide(BlockPos pos) {
      int n = pos.getX() - this.doorBlockPos.getX();
      int n2 = pos.getZ() - this.doorBlockPos.getY();
      return n * this.insideDirection.getFrontOffsetX() + n2 * this.insideDirection.getFrontOffsetZ() >= 0;
   }

   private static EnumFacing getFaceDirection(int n, int n2) {
      return n < 0 ? EnumFacing.WEST : (n > 0 ? EnumFacing.EAST : (n2 < 0 ? EnumFacing.NORTH : EnumFacing.SOUTH));
   }

   public VillageDoorInfo(BlockPos pos, int n, int n2, int n3) {
      this(pos, getFaceDirection(n, n2), n3);
   }

   public int getDistanceSquared(int n, int n2, int n3) {
      return (int)this.doorBlockPos.distanceSq((double)n, (double)n2, (double)n3);
   }

   public int getDoorOpeningRestrictionCounter() {
      return this.doorOpeningRestrictionCounter;
   }

   public int getInsideOffsetX() {
      return this.insideDirection.getFrontOffsetX() * 2;
   }

   public int getDistanceToInsideBlockSq(BlockPos pos) {
      return (int)this.insideBlock.distanceSq(pos);
   }

   public int getInsideOffsetZ() {
      return this.insideDirection.getFrontOffsetZ() * 2;
   }

   public int getInsidePosY() {
      return this.lastActivityTimestamp;
   }

   public boolean getIsDetachedFromVillageFlag() {
      return this.isDetachedFromVillageFlag;
   }

   public void setLastActivityTimestamp(int n) {
      this.lastActivityTimestamp = n;
   }
}
