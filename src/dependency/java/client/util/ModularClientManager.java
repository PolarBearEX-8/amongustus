package client.util;

public final class ModularClientManager extends StepManager {
   @Override
   public String getTextAsText() {
      return "mob.slime.big";
   }

   @Override
   public String getText() {
      return "mob.slime.small";
   }

   @Override
   public String getText2() {
      return "mob.slime.big";
   }

   public ModularClientManager(String text, float factor, float factor2) {
      super(text, factor, factor2);
   }
}
