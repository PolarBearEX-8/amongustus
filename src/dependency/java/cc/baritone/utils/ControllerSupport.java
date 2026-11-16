package cc.baritone.utils;

public final class ControllerSupport {
   public static final int count = 32768;
   public static final int count2 = 32767;
   public int count3;
   public int count4;
   public int count5;
   public final int[] countArray = new int[32768];
   public int count6;

   public void applyIndexByIndex(int count7) {
      int count8 = count7 << 3;
      this.count4 -= count8;
      this.count5 -= count8;
      if (this.count5 < 0) {
         this.count5 += 32768;
      }
   }

   public int computeCount(int count7) {
      int count8 = count7;
      this.count4 += count7;
      int count9 = 0;
      int count10 = this.count5;
      if (count10 + count7 < 32768) {
         while (count8-- > 0) {
            count9 = count9 << 1 | (this.countArray[count10++] != 0 ? 1 : 0);
         }
      } else {
         while (count8-- > 0) {
            count9 = count9 << 1 | (this.countArray[count10] != 0 ? 1 : 0);
            count10 = count10 + 1 & 32767;
         }
      }

      this.count5 = count10;
      return count9;
   }

   public int getCount4() {
      return this.count4;
   }

   public ControllerSupport() {
      this.count3 = 0;
      this.count4 = 0;
      this.count5 = 0;
   }

   public void applyIndex(int count7) {
      int count8 = this.count3;
      this.countArray[count8++] = count7 & 128;
      this.countArray[count8++] = count7 & 64;
      this.countArray[count8++] = count7 & 32;
      this.countArray[count8++] = count7 & 16;
      this.countArray[count8++] = count7 & 8;
      this.countArray[count8++] = count7 & 4;
      this.countArray[count8++] = count7 & 2;
      this.countArray[count8++] = count7 & 1;
      if (count8 == 32768) {
         this.count3 = 0;
      } else {
         this.count3 = count8;
      }
   }

   public int getCount() {
      this.count4++;
      int count7 = this.countArray[this.count5];
      this.count5 = this.count5 + 1 & 32767;
      return count7;
   }

   public void applyIndex2(int count7) {
      this.count4 -= count7;
      this.count5 -= count7;
      if (this.count5 < 0) {
         this.count5 += 32768;
      }
   }
}
