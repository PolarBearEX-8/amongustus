package client.util;

import client.event.PreUpdateObserver;

public class PlusMinusTypeDistanceBridge implements DistanceBridge {
   public PlusMinusType plusMinusType;
   public PreUpdateObserver[] preUpdateObserverArray;

   @Override
   public String toString() {
      return "" + this.plusMinusType + "()";
   }

   public PlusMinusTypeDistanceBridge(PlusMinusType plusMinusType2, PreUpdateObserver[] preUpdateObserverArray2) {
      this.plusMinusType = plusMinusType2;
      this.preUpdateObserverArray = preUpdateObserverArray2;
   }

   @Override
   public float[] getFloatArray() {
      return this.plusMinusType.resolveFloatArray(this.preUpdateObserverArray);
   }

   @Override
   public AimSorter getBOOL() {
      return AimSorter.FLOAT_ARRAY;
   }
}
