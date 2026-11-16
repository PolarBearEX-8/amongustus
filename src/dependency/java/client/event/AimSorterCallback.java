package client.event;

import client.util.AimSorter;

public interface AimSorterCallback {
   AimSorter[] resolveAimSorterArray(PreUpdateObserver[] preUpdateObserverArray);
}
