package client.util;

import client.event.PreUpdateObserver;

public class PreUpdateObserverAimValidator implements AimValidator {
   public PlusMinusType plusMinusType;
   public PreUpdateObserver[] preUpdateObserverArray;
   public int count = -1;

   private static float computeFactor(PreUpdateObserver[] preUpdateObserverArray2, int count2) {
      return ((AimValidator)preUpdateObserverArray2[count2]).getFactor();
   }

   public PreUpdateObserverAimValidator(PlusMinusType plusMinusType2, PreUpdateObserver[] preUpdateObserverArray2) {
      this.plusMinusType = plusMinusType2;
      this.preUpdateObserverArray = preUpdateObserverArray2;
   }

   @Override
   public AimSorter getBOOL() {
      return AimSorter.FLOAT;
   }

   @Override
   public float getFactor() {
      PreUpdateObserver[] preUpdateObserverArray2 = this.preUpdateObserverArray;
      switch (PreUpdateObserverAimValiPlusMinusTypeSwitchMap.countArray[this.plusMinusType.ordinal()]) {
         case 1:
            if (!(preUpdateObserverArray2[0] instanceof AimSorterAimValidatorImpl)) {
               float factor = computeFactor(preUpdateObserverArray2, 0);
               float factor2 = preUpdateObserverArray2.length > 1 ? computeFactor(preUpdateObserverArray2, 1) : 1.0F;
               float factor3 = preUpdateObserverArray2.length > 2 ? computeFactor(preUpdateObserverArray2, 2) : factor2;
               if (this.count < 0) {
                  this.count = ExtraMapEntry.getCount();
               }

               return ExtraMapEntry.computeFactor(this.count, factor, factor2, factor3);
            }
         default:
            return this.plusMinusType.computeFactor2(this.preUpdateObserverArray);
      }
   }

   @Override
   public String toString() {
      return "" + this.plusMinusType + "()";
   }
}
