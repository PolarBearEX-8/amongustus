package client.util;

public final class BridgeClientManager extends StepManager {
   @Override
   public String getTextAsText() {
      return "random.anvil_land";
   }

   @Override
   public String getText2() {
      return "dig.stone";
   }

   public BridgeClientManager(String text, float factor, float factor2) {
      super(text, factor, factor2);
   }
}
