package client.util;

public class OWKNOWSManager {
   public BaseFloatEntry[] baseFloatEntryArray = new BaseFloatEntry[1024];
   public int count;

   public void applyBaseFloatEntry(BaseFloatEntry baseFloatEntry, float factor) {
      float factor2 = baseFloatEntry.factor2;
      baseFloatEntry.factor2 = factor;
      if (factor < factor2) {
         this.applyIndex(baseFloatEntry.count4);
      } else {
         this.applyIndexByIndex(baseFloatEntry.count4);
      }
   }

   private void applyIndex(int count2) {
      int count3 = count2;
      BaseFloatEntry baseFloatEntry = this.baseFloatEntryArray[count2];
      float factor = baseFloatEntry.factor2;

      while (count3 > 0) {
         int count4 = count3 - 1 >> 1;
         BaseFloatEntry baseFloatEntry2 = this.baseFloatEntryArray[count4];
         if (factor >= baseFloatEntry2.factor2) {
            break;
         }

         this.baseFloatEntryArray[count3] = baseFloatEntry2;
         baseFloatEntry2.count4 = count3;
         count3 = count4;
      }

      this.baseFloatEntryArray[count3] = baseFloatEntry;
      baseFloatEntry.count4 = count3;
   }

   public BaseFloatEntry getBaseFloatEntry() {
      BaseFloatEntry baseFloatEntry = this.baseFloatEntryArray[0];
      this.baseFloatEntryArray[0] = this.baseFloatEntryArray[--this.count];
      this.baseFloatEntryArray[this.count] = null;
      if (this.count > 0) {
         this.applyIndexByIndex(0);
      }

      baseFloatEntry.count4 = -1;
      return baseFloatEntry;
   }

   public BaseFloatEntry resolveBaseFloatEntry(BaseFloatEntry baseFloatEntry) {
      if (baseFloatEntry.count4 >= 0) {
         throw new IllegalStateException("OW KNOWS!");
      } else {
         if (this.count == this.baseFloatEntryArray.length) {
            BaseFloatEntry[] baseFloatEntryArray2 = new BaseFloatEntry[this.count << 1];
            System.arraycopy(this.baseFloatEntryArray, 0, baseFloatEntryArray2, 0, this.count);
            this.baseFloatEntryArray = baseFloatEntryArray2;
         }

         this.baseFloatEntryArray[this.count] = baseFloatEntry;
         baseFloatEntry.count4 = this.count;
         this.applyIndex(this.count++);
         return baseFloatEntry;
      }
   }

   private void applyIndexByIndex(int count2) {
      int count3 = count2;
      BaseFloatEntry baseFloatEntry = this.baseFloatEntryArray[count2];
      float factor = baseFloatEntry.factor2;

      while (true) {
         int count4 = 1 + (count3 << 1);
         int count5 = count4 + 1;
         if (count4 >= this.count) {
            break;
         }

         BaseFloatEntry baseFloatEntry2 = this.baseFloatEntryArray[count4];
         float factor2 = baseFloatEntry2.factor2;
         BaseFloatEntry baseFloatEntry3;
         float factor3;
         if (count5 >= this.count) {
            baseFloatEntry3 = null;
            factor3 = Float.POSITIVE_INFINITY;
         } else {
            baseFloatEntry3 = this.baseFloatEntryArray[count5];
            factor3 = baseFloatEntry3.factor2;
         }

         if (factor2 < factor3) {
            if (factor2 >= factor) {
               break;
            }

            this.baseFloatEntryArray[count3] = baseFloatEntry2;
            baseFloatEntry2.count4 = count3;
            count3 = count4;
         } else {
            if (factor3 >= factor) {
               break;
            }

            this.baseFloatEntryArray[count3] = baseFloatEntry3;
            baseFloatEntry3.count4 = count3;
            count3 = count5;
         }
      }

      this.baseFloatEntryArray[count3] = baseFloatEntry;
      baseFloatEntry.count4 = count3;
   }

   public boolean isActive() {
      return this.count == 0;
   }

   public void updateState() {
      this.count = 0;
   }
}
