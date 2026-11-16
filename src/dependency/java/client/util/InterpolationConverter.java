package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

public class InterpolationConverter {
   public final BaseFloatEntry[] baseFloatEntryArray;
   public int count;
   public int count2;

   public int getCount2() {
      return this.count2;
   }

   public BaseFloatEntry resolveBaseFloatEntry(int count3) {
      return this.baseFloatEntryArray[count3];
   }

   public BaseFloatEntry getBaseFloatEntry() {
      return this.count2 > 0 ? this.baseFloatEntryArray[this.count2 - 1] : null;
   }

   public void updateState() {
      this.count++;
   }

   public void setCount2(int count3) {
      this.count2 = count3;
   }

   public Vec3 resolveVec3(Entity entity) {
      return this.resolveVecByEntity(entity, this.count);
   }

   public InterpolationConverter(BaseFloatEntry[] baseFloatEntryArray2) {
      this.baseFloatEntryArray = baseFloatEntryArray2;
      this.count2 = baseFloatEntryArray2.length;
   }

   public int getCount() {
      return this.count;
   }

   public boolean matchesInterpolationConverter(InterpolationConverter interpolationConverter) {
      if (interpolationConverter == null) {
         return false;
      } else if (interpolationConverter.baseFloatEntryArray.length != this.baseFloatEntryArray.length) {
         return false;
      } else {
         for (int index = 0; index < this.baseFloatEntryArray.length; index++) {
            if (this.baseFloatEntryArray[index].count != interpolationConverter.baseFloatEntryArray[index].count
               || this.baseFloatEntryArray[index].count5 != interpolationConverter.baseFloatEntryArray[index].count5
               || this.baseFloatEntryArray[index].count3 != interpolationConverter.baseFloatEntryArray[index].count3) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean isActive() {
      return this.count >= this.count2;
   }

   public boolean matchesVec3(Vec3 vec) {
      BaseFloatEntry baseFloatEntry = this.getBaseFloatEntry();
      return baseFloatEntry == null ? false : baseFloatEntry.count == (int)vec.xCoord && baseFloatEntry.count3 == (int)vec.zCoord;
   }

   public Vec3 resolveVecByEntity(Entity entity, int count3) {
      double offset = (double)this.baseFloatEntryArray[count3].count + (double)((int)(entity.width + 1.0F)) * 0.5;
      double count4 = (double)this.baseFloatEntryArray[count3].count5;
      double offset2 = (double)this.baseFloatEntryArray[count3].count3 + (double)((int)(entity.width + 1.0F)) * 0.5;
      return new Vec3(offset, count4, offset2);
   }

   public void setCount(int count3) {
      this.count = count3;
   }
}
