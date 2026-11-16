package client.util;

public class RecordNode12 {
   public int count;
   public int count2;

   public RecordNode12(int count3, int count4) {
      this.count = count3;
      this.count2 = count4;
   }

   @Override
   public String toString() {
      return "(" + this.count + ", " + this.count2 + ")";
   }
}
