package client.event;

import client.util.AimSorter;

public class AimSorterAimSorterCallbackImpl implements AimSorterCallback {
   public AimSorter[] aimSorterArray;

   public AimSorterAimSorterCallbackImpl(AimSorter[] aimSorterArray2) {
      this.aimSorterArray = aimSorterArray2;
   }

   @Override
   public AimSorter[] resolveAimSorterArray(PreUpdateObserver[] preUpdateObserverArray) {
      return this.aimSorterArray;
   }
}
