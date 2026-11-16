package client.util;

import net.minecraft.nbt.NBTTagList;

public class ManagedFloatEntry {
   public final float factor3;
   public final float factor;
   public final float factor2;

   public NBTTagList getNBTTagList() {
      NBTTagList nBTTagList = new NBTTagList();
      nBTTagList.applyLISTManager(new InternalClientManagerRegistry(this.factor3));
      nBTTagList.applyLISTManager(new InternalClientManagerRegistry(this.factor));
      nBTTagList.applyLISTManager(new InternalClientManagerRegistry(this.factor2));
      return nBTTagList;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public float getFactor() {
      return this.factor;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public ManagedFloatEntry(NBTTagList nBTTagList) {
      this.factor3 = nBTTagList.computeFactor(0);
      this.factor = nBTTagList.computeFactor(1);
      this.factor2 = nBTTagList.computeFactor(2);
   }

   public ManagedFloatEntry(float posX, float posY, float posZ) {
      this.factor3 = posX;
      this.factor = posY;
      this.factor2 = posZ;
   }

   @Override
   public boolean equals(Object other) {
      if (!(other instanceof ManagedFloatEntry)) {
         return false;
      } else {
         ManagedFloatEntry managedFloatEntry = (ManagedFloatEntry)other;
         return this.factor3 == managedFloatEntry.factor3 && this.factor == managedFloatEntry.factor && this.factor2 == managedFloatEntry.factor2;
      }
   }
}
