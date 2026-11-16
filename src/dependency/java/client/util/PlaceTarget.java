package client.util;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class PlaceTarget {
   public BlockPos blockPos;
   public Vec3 vec3;
   public EnumFacing enumFacing;

   public void setVec3(Vec3 vec32) {
      this.vec3 = vec32;
   }

   public void setEnumFacing(EnumFacing enumFacing2) {
      this.enumFacing = enumFacing2;
   }

   public void setBlockPos(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }

   public Vec3 getHitVec() {
      return this.vec3;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public EnumFacing getFacing() {
      return this.enumFacing;
   }

   public PlaceTarget(BlockPos blockPos2, EnumFacing enumFacing2, Vec3 vec) {
      this.blockPos = blockPos2;
      this.enumFacing = enumFacing2;
      this.vec3 = vec;
   }
}
