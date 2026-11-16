package client.util;

public class DampenerCollector {
   public final boolean enabled;
   public final String labelText;

   public DampenerCollector(boolean flag, String text) {
      this.enabled = flag;
      this.labelText = text;
   }
}
