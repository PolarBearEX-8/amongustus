package client.util;

import client.event.PreUpdateObserver;

public class PreUpdateObserverTargetObserver implements TargetObserver {
   public PlusMinusType plusMinusType;
   public PreUpdateObserver[] preUpdateObserverArray;

   @Override
   public String toString() {
      return "" + this.plusMinusType + "()";
   }

   @Override
   public AimSorter getBOOL() {
      return AimSorter.BOOL;
   }

   @Override
   public boolean isActive() {
      return this.plusMinusType.matchesPreUpdateObserverArray(this.preUpdateObserverArray);
   }

   public PreUpdateObserverTargetObserver(PlusMinusType plusMinusType2, PreUpdateObserver[] preUpdateObserverArray2) {
      this.plusMinusType = plusMinusType2;
      this.preUpdateObserverArray = preUpdateObserverArray2;
   }
}
