package client.util;

public class DampenerDeserializer {
   public final String labelText;
   public final int count;

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public DampenerDeserializer(String text, int count2) {
      this.labelText = text;
      this.count = count2;
   }
}
