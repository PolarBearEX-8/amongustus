package client.util;

public class Controller20 {
   public final int count;
   public final String labelText;
   public final boolean enabled;

   public Controller20(int count2, String text, boolean flag) {
      this.count = count2;
      this.labelText = text;
      this.enabled = flag;
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public boolean isEnabled() {
      return this.enabled;
   }
}
