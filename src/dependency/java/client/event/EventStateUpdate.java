package client.event;

import de.florianmichael.dietrichevents2.CancellableEvent;
import de.florianmichael.dietrichevents2.StateTypes;

public class EventStateUpdate extends CancellableEvent {
   public StateTypes stateTypes;
   public static final int count = 43;

   public static void onStateUpdate(EventStateUpdate event, StateTypes stateTypes2) {
      event.setCancelled(stateTypes2);
   }

   public void call(Object entry) {
      UpdateStateListener updateStateListener = (UpdateStateListener)entry;
      this.applyUpdateStateListener(updateStateListener);
   }

   public void applyUpdateStateListener(UpdateStateListener updateStateListener) {
      if (this.stateTypes == StateTypes.PRE) {
         updateStateListener.onPostUpdateState();
         updateStateListener.onUpdateState(this);
      } else {
         updateStateListener.onPreUpdateState();
      }
   }

   private void setCancelled(StateTypes cancelled) {
      this.stateTypes = cancelled;
      this.setCancelled(false);
   }
}
