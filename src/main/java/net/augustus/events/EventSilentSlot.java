package net.augustus.events;

import net.augustus.events.listeners.SilentSlotListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;

public class EventSilentSlot extends AbstractEvent<SilentSlotListener> {
   private StateTypes state;
   public static final int ID = 34;

   public static StateTypes setState(EventSilentSlot event, StateTypes stateTypes) {
      return event.state = stateTypes;
   }

   @Override
   public void call(SilentSlotListener listener) {
      if (this.state == StateTypes.PRE) {
         listener.onPreSilentSlot();
      } else {
         listener.onPostSilentSlot();
      }
   }

}
