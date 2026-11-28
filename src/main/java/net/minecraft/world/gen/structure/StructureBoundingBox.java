package net.minecraft.world.gen.structure;

import com.google.common.base.MoreObjects;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public class StructureBoundingBox {
   public int minX;
   public int minY;
   public int minZ;
   public int maxX;
   public int maxY;
   public int maxZ;

   public void expandTo(StructureBoundingBox structureBoundingBox) {
      this.minX = Math.min(this.minX, structureBoundingBox.minX);
      this.minY = Math.min(this.minY, structureBoundingBox.minY);
      this.minZ = Math.min(this.minZ, structureBoundingBox.minZ);
      this.maxX = Math.max(this.maxX, structureBoundingBox.maxX);
      this.maxY = Math.max(this.maxY, structureBoundingBox.maxY);
      this.maxZ = Math.max(this.maxZ, structureBoundingBox.maxZ);
   }

   public StructureBoundingBox(int[] ints) {
      if (ints.length == 6) {
         this.minX = ints[0];
         this.minY = ints[1];
         this.minZ = ints[2];
         this.maxX = ints[3];
         this.maxY = ints[4];
         this.maxZ = ints[5];
      }
   }

   public Vec3i getLength() {
      return new Vec3i(this.maxX - this.minX, this.maxY - this.minY, this.maxZ - this.minZ);
   }

   public int getZSize() {
      return this.maxZ - this.minZ + 1;
   }

   public StructureBoundingBox(Vec3i vec, Vec3i vec2) {
      this.minX = Math.min(vec.getX(), vec2.getX());
      this.minY = Math.min(vec.getY(), vec2.getY());
      this.minZ = Math.min(vec.getZ(), vec2.getZ());
      this.maxX = Math.max(vec.getX(), vec2.getX());
      this.maxY = Math.max(vec.getY(), vec2.getY());
      this.maxZ = Math.max(vec.getZ(), vec2.getZ());
   }

   public static StructureBoundingBox getComponentToAddBoundingBox(
      int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, EnumFacing facing
   ) {
      switch (facing) {
         case NORTH:
            return new StructureBoundingBox(n + n4, n2 + n5, n3 - n9 + 1 + n6, n + n7 - 1 + n4, n2 + n8 - 1 + n5, n3 + n6);
         case SOUTH:
            return new StructureBoundingBox(n + n4, n2 + n5, n3 + n6, n + n7 - 1 + n4, n2 + n8 - 1 + n5, n3 + n9 - 1 + n6);
         case WEST:
            return new StructureBoundingBox(n - n9 + 1 + n6, n2 + n5, n3 + n4, n + n6, n2 + n8 - 1 + n5, n3 + n7 - 1 + n4);
         case EAST:
            return new StructureBoundingBox(n + n6, n2 + n5, n3 + n4, n + n9 - 1 + n6, n2 + n8 - 1 + n5, n3 + n7 - 1 + n4);
         default:
            return new StructureBoundingBox(n + n4, n2 + n5, n3 + n6, n + n7 - 1 + n4, n2 + n8 - 1 + n5, n3 + n9 - 1 + n6);
      }
   }

   public Vec3i getCenter() {
      return new BlockPos(this.minX + (this.maxX - this.minX + 1) / 2, this.minY + (this.maxY - this.minY + 1) / 2, this.minZ + (this.maxZ - this.minZ + 1) / 2);
   }

   public boolean intersectsWith(int n, int n2, int n3, int n4) {
      return this.maxX >= n && this.minX <= n3 && this.maxZ >= n2 && this.minZ <= n4;
   }

   public static StructureBoundingBox getNewBoundingBox() {
      return new StructureBoundingBox(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
   }

   public NBTTagIntArray toNBTTagIntArray() {
      return new NBTTagIntArray(new int[]{this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ});
   }

   public int getYSize() {
      return this.maxY - this.minY + 1;
   }

   public static StructureBoundingBox createProper(int n, int n2, int n3, int n4, int n5, int n6) {
      return new StructureBoundingBox(
         Math.min(n, n4), Math.min(n2, n5), Math.min(n3, n6), Math.max(n, n4), Math.max(n2, n5), Math.max(n3, n6)
      );
   }

   public StructureBoundingBox() {
   }

   public boolean intersectsWith(StructureBoundingBox structureBoundingBox) {
      return this.maxX >= structureBoundingBox.minX
         && this.minX <= structureBoundingBox.maxX
         && this.maxZ >= structureBoundingBox.minZ
         && this.minZ <= structureBoundingBox.maxZ
         && this.maxY >= structureBoundingBox.minY
         && this.minY <= structureBoundingBox.maxY;
   }

   public StructureBoundingBox(StructureBoundingBox structureBoundingBox) {
      this.minX = structureBoundingBox.minX;
      this.minY = structureBoundingBox.minY;
      this.minZ = structureBoundingBox.minZ;
      this.maxX = structureBoundingBox.maxX;
      this.maxY = structureBoundingBox.maxY;
      this.maxZ = structureBoundingBox.maxZ;
   }

   public int getXSize() {
      return this.maxX - this.minX + 1;
   }

   public StructureBoundingBox(int n, int n2, int n3, int n4, int n5, int n6) {
      this.minX = n;
      this.minY = n2;
      this.minZ = n3;
      this.maxX = n4;
      this.maxY = n5;
      this.maxZ = n6;
   }

   public void offset(int n, int n2, int n3) {
      this.minX += n;
      this.minY += n2;
      this.minZ += n3;
      this.maxX += n;
      this.maxY += n2;
      this.maxZ += n3;
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this)
         .add("x0", this.minX)
         .add("y0", this.minY)
         .add("z0", this.minZ)
         .add("x1", this.maxX)
         .add("y1", this.maxY)
         .add("z1", this.maxZ)
         .toString();
   }

   public boolean isVecInside(Vec3i vec) {
      return vec.getX() >= this.minX
         && vec.getX() <= this.maxX
         && vec.getZ() >= this.minZ
         && vec.getZ() <= this.maxZ
         && vec.getY() >= this.minY
         && vec.getY() <= this.maxY;
   }

   public StructureBoundingBox(int n, int n2, int n3, int n4) {
      this.minX = n;
      this.minZ = n2;
      this.maxX = n3;
      this.maxZ = n4;
      this.minY = 1;
      this.maxY = 512;
   }

}
