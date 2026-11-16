package cc.baritone.utils;

public class HeuristicOptimizer {
   public String labelText;
   public String labelText2;
   public String labelText3;
   public boolean enabled;
   public ControllerEntry controllerEntry;

   public String getLabelText3() {
      return this.labelText3;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public boolean equals(Object other) {
      if (!(other instanceof HeuristicOptimizer)) {
         return false;
      } else {
         HeuristicOptimizer heuristicOptimizer = (HeuristicOptimizer)other;
         return heuristicOptimizer.getLabelText().equals(this.labelText) && heuristicOptimizer.getLabelText3().equals(this.labelText3);
      }
   }

   public ControllerEntry getControllerEntry() {
      return this.controllerEntry;
   }

   @Override
   public String toString() {
      return String.format("Account[%s:%s:%s:%s]", this.labelText3, this.labelText, this.labelText2, this.enabled);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public boolean isEnabled() {
      return this.enabled;
   }
}
