package client.util;

import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

public class MapArrayHolder {
   public int count = 1;
   public boolean enabled = false;
   public Map[] mapArray = new Map[8];

   private boolean checkFloat(float posX, float posY, float posZ) {
      return MathHelper.computeFactor(posX - posY) < posZ;
   }

   private boolean matchesLocalIntArrayEntry(LocalIntArrayEntry localIntArrayEntry) {
      ServiceStringEntry serviceStringEntry = localIntArrayEntry.getServiceStringEntry();
      float factor = serviceStringEntry.getFactor5();
      float factor2 = serviceStringEntry.getFactor2();
      float factor3 = (factor2 - factor) / 256.0F;
      float factor4 = serviceStringEntry.getFactor();
      float factor5 = serviceStringEntry.getFactor6();
      float factor6 = (factor5 - factor4) / 256.0F;
      int[] indexArray = localIntArrayEntry.getIndexArray();
      int count2 = indexArray.length / 4;

      for (int index = 0; index < 4; index++) {
         int count3 = index * count2;
         float factor7 = Float.intBitsToFloat(indexArray[count3 + 4]);
         float factor8 = Float.intBitsToFloat(indexArray[count3 + 4 + 1]);
         if (!this.checkFloat(factor7, factor, factor3) && !this.checkFloat(factor7, factor2, factor3)) {
            return false;
         }

         if (!this.checkFloat(factor8, factor4, factor6) && !this.checkFloat(factor8, factor5, factor6)) {
            return false;
         }
      }

      return true;
   }

   private int[] resolveIndexArray(int[] countArray, int count2, boolean flag) {
      int[] countArray2 = (int[])countArray.clone();
      int count3 = 4 - count2;
      if (flag) {
         count3 += 3;
      }

      int count4 = count3 % 4;
      int count5 = countArray2.length / 4;

      for (int index = 0; index < 4; index++) {
         int count6 = index * count5;
         int count7 = count4 * count5;
         countArray2[count7 + 4] = countArray[count6 + 4];
         countArray2[count7 + 4 + 1] = countArray[count6 + 4 + 1];
         if (flag) {
            if (--count4 < 0) {
               count4 = 3;
            }
         } else if (++count4 > 3) {
            count4 = 0;
         }
      }

      return countArray2;
   }

   public boolean isActive() {
      return this.count != 2 && this.count != 4 ? this.enabled : true;
   }

   public synchronized LocalIntArrayEntry resolveLocalIntArrayEntryByLocalIntArrayEnt(LocalIntArrayEntry localIntArrayEntry, int count2, boolean flag) {
      int count3 = count2;
      if (flag) {
         count3 = count2 | 4;
      }

      if (count3 > 0 && count3 < this.mapArray.length) {
         Map entry = this.mapArray[count3];
         if (entry == null) {
            entry = new IdentityHashMap(1);
            this.mapArray[count3] = (Map)entry;
         }

         LocalIntArrayEntry localIntArrayEntry2 = (LocalIntArrayEntry)entry.get(localIntArrayEntry);
         if (localIntArrayEntry2 == null) {
            localIntArrayEntry2 = this.resolveLocalIntArrayEntry(localIntArrayEntry, count2, flag);
            entry.put(localIntArrayEntry, localIntArrayEntry2);
         }

         return localIntArrayEntry2;
      } else {
         return localIntArrayEntry;
      }
   }

   private LocalIntArrayEntry resolveLocalIntArrayEntry(LocalIntArrayEntry localIntArrayEntry, int count2, boolean flag) {
      int count3 = count2;
      int[] indexArray = localIntArrayEntry.getIndexArray();
      int count4 = localIntArrayEntry.getCount();
      EnumFacing enumFacing = localIntArrayEntry.getEnumFacing();
      ServiceStringEntry serviceStringEntry = localIntArrayEntry.getServiceStringEntry();
      if (!this.matchesLocalIntArrayEntry(localIntArrayEntry)) {
         count3 = 0;
      }

      int[] countArray = this.resolveIndexArray(indexArray, count3, flag);
      return new LocalIntArrayEntry(countArray, count4, enumFacing, serviceStringEntry);
   }

   public MapArrayHolder(String text) {
      if (text.equals("4")) {
         this.count = 4;
      } else if (text.equals("2")) {
         this.count = 2;
      } else if (text.equals("F")) {
         this.enabled = true;
      } else if (text.equals("4F")) {
         this.count = 4;
         this.enabled = true;
      } else if (text.equals("2F")) {
         this.count = 2;
         this.enabled = true;
      } else {
         BuildManager.applyText3("NaturalTextures: Unknown type: " + text);
      }
   }
}
