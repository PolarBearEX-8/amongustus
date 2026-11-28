package net.augustus.utils.pathfinding;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class PathBlockPos {
   private Vec3 cachedVec;
   public final int y;
   private int cachedHash = 0;
   public final int z;
   private IBlockState blockState;
   public final int x;
   private String cachedKey = null;

   public IBlockState getBlockState() {
      return this.blockState;
   }

   @Override
   public boolean equals(Object obj) {
      PathBlockPos pathBlockPos = (PathBlockPos)obj;
      return this.x == pathBlockPos.x && this.y == pathBlockPos.y && this.z == pathBlockPos.z;
   }

   public PathBlockPos(int n, int n2, int n3) {
      this.cachedVec = null;
      this.x = n;
      this.y = n2;
      this.z = n3;
   }

   public PathBlockPos add(int n, int n2, int n3) {
      return new PathBlockPos(this.x + n, this.y + n2, this.z + n3);
   }

   public PathBlockPos subtract(int n, int n2, int n3) {
      return new PathBlockPos(this.x - n, this.y - n2, this.z - n3);
   }

   @Override
   public int hashCode() {
      if (this.cachedHash == 0) {
         this.cachedHash = (this.x * 31 + this.y) * 31 + this.z;
      }

      return this.cachedHash;
   }

   public double distanceTo(int n, int n2, int n3) {
      double d = (double)(this.x - n);
      double d2 = (double)(this.y - n2);
      double d3 = (double)(this.z - n3);
      return Math.sqrt(d * d + d2 * d2 + d3 * d3);
   }

   public double distanceTo(PathBlockPos pathBlockPos) {
      double d = (double)(this.x - pathBlockPos.x);
      double d2 = (double)(this.y - pathBlockPos.y);
      double d3 = (double)(this.z - pathBlockPos.z);
      return Math.sqrt(d * d + d2 * d2 + d3 * d3);
   }

   public void setBlockState(IBlockState state) {
      this.blockState = state;
   }

   public String toKeyString() {
      return this.cachedKey == null ? (this.cachedKey = this.x + "_" + this.y + "_" + this.z) : this.cachedKey;
   }

   public BlockPos toBlockPos() {
      return new BlockPos(this.x, this.y, this.z);
   }

   public Vec3 toVec3() {
      if (this.cachedVec == null) {
         this.cachedVec = new Vec3((double)this.x, (double)this.y, (double)this.z);
      }

      return this.cachedVec;
   }

   public double distanceSqTo(PathBlockPos pathBlockPos) {
      double d = (double)(this.x - pathBlockPos.x);
      double d2 = (double)(this.y - pathBlockPos.y);
      double d3 = (double)(this.z - pathBlockPos.z);
      return d * d + d2 * d2 + d3 * d3;
   }



}
