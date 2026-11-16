package client.util;

public class ModelVariableController {
   public String labelText;
   public String labelText2;
   public AngleAggregator angleAggregator;
   public AimValidator aimValidator;

   public boolean matchesListener61Extension(Listener61Extension listener61Extension) {
      this.angleAggregator = listener61Extension.resolveAngleAggregator(this.labelText);
      if (this.angleAggregator == null) {
         BuildManager.applyText3("Model variable not found: " + this.labelText);
         return false;
      } else {
         try {
            TargetSorter targetSorter = new TargetSorter(listener61Extension);
            this.aimValidator = targetSorter.resolveAimValidator(this.labelText2);
            return true;
         } catch (AuxiliaryExceptionAdapter exception) {
            BuildManager.applyText3("Error parsing expression: " + this.labelText2);
            BuildManager.applyText3(exception.getClass().getName() + ": " + exception.getMessage());
            return false;
         }
      }
   }

   public ModelVariableController(String text, String text2) {
      this.labelText = text;
      this.labelText2 = text2;
   }

   public void updateState() {
      float factor = this.aimValidator.getFactor();
      this.angleAggregator.applyFloat(factor);
   }
}
