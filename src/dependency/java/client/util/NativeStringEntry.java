package client.util;

public class NativeStringEntry {
   public String labelText;
   public int count;
   public String labelText2;
   public String labelText3;

   public String getLabelText3() {
      return this.labelText3;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public NativeStringEntry(String text, String text2, String text3, int count2) {
      this.labelText3 = text;
      this.labelText2 = text2;
      this.count = count2;
      this.labelText = text3;
   }
}
