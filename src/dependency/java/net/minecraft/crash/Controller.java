package net.minecraft.crash;

public class Controller {
   public final String labelText;
   public final String labelText2;

   public String getLabelText() {
      return this.labelText;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public Controller(String text, Object entry) {
      this.labelText = text;
      if (entry == null) {
         this.labelText2 = "~~NULL~~";
      } else if (entry instanceof Throwable) {
         Throwable throwable = (Throwable)entry;
         this.labelText2 = "~~ERROR~~ " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
      } else {
         this.labelText2 = entry.toString();
      }
   }
}
