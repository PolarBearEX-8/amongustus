package net.minecraft.item;

public class AltItemArrayEntry {
   public transient IronWand[] ironWandArray = new IronWand[16];
   public transient int count;
   public int count2 = 12;
   public final float factor = 0.75F;

   public final IronWand resolveIronWandByIndex(int count3) {
      int count4 = computeCountByIndex(count3);

      for (IronWand ironWand = this.ironWandArray[computeCount(count4, this.ironWandArray.length)]; ironWand != null; ironWand = ironWand.ironWand) {
         if (ironWand.count2 == count3) {
            return ironWand;
         }
      }

      return null;
   }

   public Object resolveTarget(int count3) {
      IronWand ironWand = this.resolveIronWand(count3);
      return ironWand == null ? null : ironWand.dataRef;
   }

   public boolean checkIndex(int count3) {
      return this.resolveIronWandByIndex(count3) != null;
   }

   private void applyTarget(int count3, int count4, Object entry, int count5) {
      IronWand ironWand = this.ironWandArray[count5];
      this.ironWandArray[count5] = new IronWand(count3, count4, entry, ironWand);
      if (this.count++ >= this.count2) {
         this.applyIndex(2 * this.ironWandArray.length);
      }
   }

   public Object resolveTargetByIndex(int count3) {
      int count4 = computeCountByIndex(count3);

      for (IronWand ironWand = this.ironWandArray[computeCount(count4, this.ironWandArray.length)]; ironWand != null; ironWand = ironWand.ironWand) {
         if (ironWand.count2 == count3) {
            return ironWand.dataRef;
         }
      }

      return null;
   }

   private static int computeCountByIndex(int count3) {
      int count4 = count3 ^ count3 >>> 20 ^ count3 >>> 12;
      return count4 ^ count4 >>> 7 ^ count4 >>> 4;
   }

   public final IronWand resolveIronWand(int count3) {
      int count4 = computeCount(computeCountByIndex(count3), this.ironWandArray.length);
      IronWand ironWand = this.ironWandArray[count4];
      IronWand ironWand2 = ironWand;

      while (ironWand2 != null) {
         IronWand ironWand3 = ironWand2.ironWand;
         if (ironWand2.count2 == count3) {
            this.count--;
            if (ironWand == ironWand2) {
               this.ironWandArray[count4] = ironWand3;
            } else {
               ironWand.ironWand = ironWand3;
            }

            return ironWand2;
         }

         ironWand = ironWand2;
         ironWand2 = ironWand3;
      }

      return ironWand2;
   }

   public void applyTarget(int count3, Object entry) {
      int count4 = computeCountByIndex(count3);
      int count5 = computeCount(count4, this.ironWandArray.length);

      for (IronWand ironWand = this.ironWandArray[count5]; ironWand != null; ironWand = ironWand.ironWand) {
         if (ironWand.count2 == count3) {
            ironWand.dataRef = entry;
            return;
         }
      }

      this.applyTarget(count4, count3, entry, count5);
   }

   public static int computeCount(int count3) {
      return computeCountByIndex(count3);
   }

   public void updateState() {
      IronWand[] ironWandArray2 = this.ironWandArray;

      for (int index = 0; index < ironWandArray2.length; index++) {
         ironWandArray2[index] = null;
      }

      this.count = 0;
   }

   private void applyIronWandArray(IronWand[] ironWandArray2) {
      IronWand[] ironWandArray3 = this.ironWandArray;
      int length = ironWandArray2.length;

      for (int index = 0; index < ironWandArray3.length; index++) {
         IronWand ironWand = ironWandArray3[index];
         if (ironWand != null) {
            ironWandArray3[index] = null;

            while (true) {
               IronWand ironWand2 = ironWand.ironWand;
               int count3 = computeCount(ironWand.count, length);
               ironWand.ironWand = ironWandArray2[count3];
               ironWandArray2[count3] = ironWand;
               ironWand = ironWand2;
               if (ironWand2 == null) {
                  break;
               }
            }
         }
      }
   }

   private void applyIndex(int count3) {
      if (this.ironWandArray.length == 1073741824) {
         this.count2 = Integer.MAX_VALUE;
      } else {
         IronWand[] ironWandArray2 = new IronWand[count3];
         this.applyIronWandArray(ironWandArray2);
         this.ironWandArray = ironWandArray2;
         this.count2 = (int)((float)count3 * 0.75F);
      }
   }

   private static int computeCount(int count3, int count4) {
      return count3 & count4 - 1;
   }
}
