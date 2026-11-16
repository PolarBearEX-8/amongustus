package client.util;

public class SplineReader {
   public final int count2;
   public final int count;

   public int getCount2() {
      return this.count2;
   }

   public boolean isActive() {
      return this.count == -1;
   }

   public int getCount() {
      return this.count;
   }

   public SplineReader(int count3) {
      this(count3, -1);
   }

   public SplineReader(int count3, int count4) {
      this.count2 = count3;
      this.count = count4;
   }
}
