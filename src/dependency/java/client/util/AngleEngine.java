package client.util;

public class AngleEngine implements AimValidator, Callback22 {
   public AimValidator aimValidator;
   public boolean enabled;
   public float factor;

   @Override
   public AimSorter getBOOL() {
      return AimSorter.FLOAT;
   }

   @Override
   public void updateState() {
      this.enabled = false;
   }

   @Override
   public String toString() {
      return "cached(" + this.aimValidator + ")";
   }

   public AngleEngine(AimValidator aimValidator2) {
      this.aimValidator = aimValidator2;
   }

   @Override
   public float getFactor() {
      if (!this.enabled) {
         this.factor = this.aimValidator.getFactor();
         this.enabled = true;
      }

      return this.factor;
   }
}
