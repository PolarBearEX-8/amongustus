package client.util;

public final class AdapterClientManager extends StepManager {
   @Override
   public String getTextAsText() {
      return "step.stone";
   }

   @Override
   public String getText2() {
      return "dig.glass";
   }

   public AdapterClientManager(String text, float factor, float factor2) {
      super(text, factor, factor2);
   }
}
