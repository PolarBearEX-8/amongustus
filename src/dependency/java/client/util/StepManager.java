package client.util;

public class StepManager {
   public final String labelText;
   public final float factor2;
   public final float factor;

   public String getTextAsText() {
      return this.getText2();
   }

   public String getText() {
      return "step." + this.labelText;
   }

   public String getText2() {
      return "dig." + this.labelText;
   }

   public float getFactor() {
      return this.factor;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public StepManager(String text, float factor3, float factor4) {
      this.labelText = text;
      this.factor2 = factor3;
      this.factor = factor4;
   }
}
