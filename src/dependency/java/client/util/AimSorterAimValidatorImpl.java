package client.util;

public class AimSorterAimValidatorImpl implements AimValidator {
   public float factor;

   @Override
   public float getFactor() {
      return this.factor;
   }

   public AimSorterAimValidatorImpl(float factor2) {
      this.factor = factor2;
   }

   @Override
   public AimSorter getBOOL() {
      return AimSorter.FLOAT;
   }

   @Override
   public String toString() {
      return "" + this.factor;
   }
}
