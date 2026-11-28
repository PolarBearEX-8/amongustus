package net.augustus.modules.world.scaffold;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class BlockPlacementData {
   private BlockPos blockPos;
   private double score;
   private EnumFacing facing;
   private Vec3 hitVec;

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public void setHitVec(Vec3 vec) {
      this.hitVec = vec;
   }

   public BlockPlacementData(BlockPos pos, EnumFacing facing2, Vec3 vec) {
      this.blockPos = pos;
      this.facing = facing2;
      this.hitVec = vec;
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   public void setFacing(EnumFacing facing2) {
      this.facing = facing2;
   }

   public Vec3 getHitVec() {
      return this.hitVec;
   }

   public BlockPlacementData(BlockPos pos, EnumFacing facing2, Vec3 vec, double d) {
      this.blockPos = pos;
      this.facing = facing2;
      this.hitVec = vec;
      this.score = d;
   }

   public double getScore() {
      return this.score;
   }

   public void setBlockPos(BlockPos pos) {
      this.blockPos = pos;
   }



}
