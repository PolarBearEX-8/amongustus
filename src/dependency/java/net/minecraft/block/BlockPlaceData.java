package net.minecraft.block;

import client.util.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class BlockPlaceData {
   public final int indexOffset2;
   public final int indexOffset1;
   public Vec3 vec3;
   public int count = 0;
   public final int indexOffset;
   public String labelText = null;
   public IBlockState iBlockState;

   @Override
   public boolean equals(Object other) {
      BlockPlaceData blockPlaceData = (BlockPlaceData)other;
      return this.indexOffset1 == blockPlaceData.indexOffset1 && this.indexOffset2 == blockPlaceData.indexOffset2 && this.indexOffset == blockPlaceData.indexOffset;
   }

   public BlockPlaceData resolveBlockPlaceData(int count2, int count3, int count4) {
      return new BlockPlaceData(this.indexOffset1 - count2, this.indexOffset2 - count3, this.indexOffset - count4);
   }

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   public double computeValue(int count2, int count3, int count4) {
      double offset = (double)(this.indexOffset1 - count2);
      double offset2 = (double)(this.indexOffset2 - count3);
      double offset3 = (double)(this.indexOffset - count4);
      return Math.sqrt(offset * offset + offset2 * offset2 + offset3 * offset3);
   }

   public BlockPlaceData(int count2, int count3, int count4) {
      this.vec3 = null;
      this.indexOffset1 = count2;
      this.indexOffset2 = count3;
      this.indexOffset = count4;
   }

   public BlockPos getBlockPos() {
      return new BlockPos(this.indexOffset1, this.indexOffset2, this.indexOffset);
   }

   public BlockPlaceData resolveBlockPlaceDataByIndex(int count2, int count3, int count4) {
      return new BlockPlaceData(this.indexOffset1 + count2, this.indexOffset2 + count3, this.indexOffset + count4);
   }

   public String getString() {
      return this.labelText == null ? (this.labelText = this.indexOffset1 + "_" + this.indexOffset2 + "_" + this.indexOffset) : this.labelText;
   }

   public double computeValue(BlockPlaceData blockPlaceData) {
      double offset = (double)(this.indexOffset1 - blockPlaceData.indexOffset1);
      double offset2 = (double)(this.indexOffset2 - blockPlaceData.indexOffset2);
      double offset3 = (double)(this.indexOffset - blockPlaceData.indexOffset);
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   @Override
   public int hashCode() {
      if (this.count == 0) {
         this.count = (this.indexOffset1 * 31 + this.indexOffset2) * 31 + this.indexOffset;
      }

      return this.count;
   }

   public void setIBlockState(IBlockState iBlockState2) {
      this.iBlockState = iBlockState2;
   }

   public Vec3 getVec3() {
      if (this.vec3 == null) {
         this.vec3 = new Vec3((double)this.indexOffset1, (double)this.indexOffset2, (double)this.indexOffset);
      }

      return this.vec3;
   }

   public double getCoordinate(BlockPlaceData blockPlaceData) {
      double offset = (double)(this.indexOffset1 - blockPlaceData.indexOffset1);
      double offset2 = (double)(this.indexOffset2 - blockPlaceData.indexOffset2);
      double offset3 = (double)(this.indexOffset - blockPlaceData.indexOffset);
      return Math.sqrt(offset * offset + offset2 * offset2 + offset3 * offset3);
   }
}
