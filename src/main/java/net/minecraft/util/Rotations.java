package net.minecraft.util;

import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;

public class Rotations {
   public final float x;
   public final float y;
   public final float z;

   public float getY() {
      return this.y;
   }

   public float getX() {
      return this.x;
   }

   public float getZ() {
      return this.z;
   }

   public Rotations(float f, float f2, float f3) {
      this.x = f;
      this.y = f2;
      this.z = f3;
   }

   public NBTTagList writeToNBT() {
      NBTTagList tagList = new NBTTagList();
      tagList.appendTag(new NBTTagFloat(this.x));
      tagList.appendTag(new NBTTagFloat(this.y));
      tagList.appendTag(new NBTTagFloat(this.z));
      return tagList;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof Rotations)) {
         return false;
      } else {
         Rotations rotations = (Rotations)obj;
         return this.x == rotations.x && this.y == rotations.y && this.z == rotations.z;
      }
   }

   public Rotations(NBTTagList tagList) {
      this.x = tagList.getFloatAt(0);
      this.y = tagList.getFloatAt(1);
      this.z = tagList.getFloatAt(2);
   }
}
