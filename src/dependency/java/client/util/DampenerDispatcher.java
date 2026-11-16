package client.util;

public class DampenerDispatcher {
   public int count;
   public int count2;
   public int count3;
   public String labelText;

   public int getCount3() {
      return this.count3;
   }

   public int getCountAsIndex() {
      return this.count;
   }

   public int getCount() {
      return this.count2 > 9
         ? this.count * 100 + this.count2
         : (this.count3 > 9 ? this.count * 100 + this.count2 * 10 + 9 : this.count * 100 + this.count2 * 10 + this.count3);
   }

   public int getCount2() {
      return this.count2;
   }

   public DampenerDispatcher(int count4, int count5, int count6) {
      this(count4, count5, count6, (String)null);
   }

   public DampenerDispatcher(int count4, int count5) {
      this(count4, count5, 0);
   }

   public DampenerDispatcher(int count4, int count5, int count6, String text) {
      this.count = count4;
      this.count2 = count5;
      this.count3 = count6;
      this.labelText = text;
   }

   @Override
   public String toString() {
      return this.labelText == null
         ? "" + this.count + "." + this.count2 + "." + this.count3
         : "" + this.count + "." + this.count2 + "." + this.count3 + this.labelText;
   }
}
