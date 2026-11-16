package client.util;

public class FriendRecord {
   public final String labelText4;
   public final String labelText2;
   public final String labelText3;
   public final int count2;
   public final long timestamp;
   public final String labelText;
   public final int count;
   public final String labelText6;
   public final String labelText5;

   public String getTextAsText() {
      return this.labelText4 + this.labelText3;
   }

   public int getCount2() {
      return this.count2;
   }

   public String getLabelText5() {
      return this.labelText5;
   }

   public String getLabelText6() {
      return this.labelText6;
   }

   public String getText() {
      return this.labelText2;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public String getLabelText4() {
      return this.labelText4;
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public FriendRecord(String text, String text2, String text3, int count3, String text4, String text5, String text6, long value, int count4) {
      this.labelText5 = text;
      this.labelText2 = text2;
      this.count = count3;
      this.labelText6 = text4;
      this.labelText3 = text5;
      this.labelText4 = text6;
      this.timestamp = value;
      this.labelText = text3;
      this.count2 = count4;
   }
}
