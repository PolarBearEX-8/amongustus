package client.setting;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public enum MovementTimeoutProperty {
    DOWN(EnumFacing.DOWN),
    UP(EnumFacing.UP),
    NORTH(EnumFacing.NORTH),
    SOUTH(EnumFacing.SOUTH),
    WEST(EnumFacing.WEST),
    EAST(EnumFacing.EAST),
    NORTH_WEST(EnumFacing.NORTH, EnumFacing.WEST),
    NORTH_EAST(EnumFacing.NORTH, EnumFacing.EAST),
    SOUTH_WEST(EnumFacing.SOUTH, EnumFacing.WEST),
    SOUTH_EAST(EnumFacing.SOUTH, EnumFacing.EAST),
    DOWN_NORTH(EnumFacing.DOWN, EnumFacing.NORTH),
    DOWN_SOUTH(EnumFacing.DOWN, EnumFacing.SOUTH),
    UP_NORTH(EnumFacing.UP, EnumFacing.NORTH),
    UP_SOUTH(EnumFacing.UP, EnumFacing.SOUTH),
    DOWN_WEST(EnumFacing.DOWN, EnumFacing.WEST),
    DOWN_EAST(EnumFacing.DOWN, EnumFacing.EAST),
    UP_WEST(EnumFacing.UP, EnumFacing.WEST),
    UP_EAST(EnumFacing.UP, EnumFacing.EAST);

   public EnumFacing enumFacing2;
   public EnumFacing enumFacing;

   public BlockPos resolveBlockPos(BlockPos blockPos) {
      BlockPos blockPos2 = blockPos.getBlockPos(this.enumFacing2, 1);
      if (this.enumFacing != null) {
         blockPos2 = blockPos2.getBlockPos(this.enumFacing, 1);
      }

      return blockPos2;
   }

   public EnumFacing getEnumFacing() {
      return this.enumFacing;
   }

   public EnumFacing getEnumFacing2() {
      return this.enumFacing2;
   }

   public int getCount() {
      int intItem = this.enumFacing2.getInt3();
      if (this.enumFacing != null) {
         intItem += this.enumFacing.getInt3();
      }

      return intItem;
   }

   public boolean isActive() {
      return this.enumFacing != null;
   }

   public int getCount2() {
      int intItem = this.enumFacing2.getInt2();
      if (this.enumFacing != null) {
         intItem += this.enumFacing.getInt2();
      }

      return intItem;
   }

   public int getCountAsIndex() {
      int intItem = this.enumFacing2.getInt();
      if (this.enumFacing != null) {
         intItem += this.enumFacing.getInt();
      }

      return intItem;
   }

   private MovementTimeoutProperty(EnumFacing enumFacing3) {
      this.enumFacing2 = enumFacing3;
   }

   private MovementTimeoutProperty(EnumFacing enumFacing3, EnumFacing enumFacing4) {
      this.enumFacing2 = enumFacing3;
      this.enumFacing = enumFacing4;
   }


}
