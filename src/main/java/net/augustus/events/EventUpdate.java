package net.augustus.events;

import net.augustus.events.listeners.UpdateListener;
import de.florianmichael.dietrichevents2.CancellableEvent;
import de.florianmichael.dietrichevents2.StateTypes;

public class EventUpdate extends CancellableEvent<UpdateListener> {
   public StateTypes state;
   public static final int ID = 43;

   private void reset(StateTypes stateTypes) {
      this.state = stateTypes;
      this.setCancelled(false);
   }

   @Override
   public void call(UpdateListener listener) {
      if (this.state == StateTypes.PRE) {
         listener.onPreUpdate();
         listener.onPreUpdate(this);
      } else {
         listener.onPostUpdate();
         listener.onPostUpdate(this);
      }
   }

   public static void reset(EventUpdate event, StateTypes stateTypes) {
      event.reset(stateTypes);
   }

}
