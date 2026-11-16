package client.util;

public class SplineEvaluator {
   public final String labelText;
   public final long timestamp;
   public final boolean enabled;
   public final String labelText2;

   public SplineEvaluator(String text, String text2, long value, boolean flag) {
      this.labelText = text;
      this.labelText2 = text2;
      this.timestamp = value;
      this.enabled = flag;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public String getLabelText() {
      return this.labelText;
   }
}
