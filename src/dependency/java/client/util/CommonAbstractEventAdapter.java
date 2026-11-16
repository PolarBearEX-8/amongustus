package client.util;

import client.network.StateUpdateListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class CommonAbstractEventAdapter extends AbstractEvent {
   public static final int count = 61;

   public void applyStateUpdateListener(StateUpdateListener stateUpdateListener) {
      stateUpdateListener.updateState();
   }

   public void call(Object entry) {
      StateUpdateListener stateUpdateListener = (StateUpdateListener)entry;
      this.applyStateUpdateListener(stateUpdateListener);
   }
}
