package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;

public class EventTickState extends AbstractEvent {
   public static final int count = 37;
   public float factor;
   public StateTypes stateTypes;

   public void processAction(float factor2) {
      this.factor = factor2;
   }

   public void call(Object entry) {
      EventTickStateCallback event = (EventTickStateCallback)entry;
      this.onTickStateCallback(event);
   }

   public void onTickStateCallback(EventTickStateCallback event) {
      if (this.stateTypes == StateTypes.PRE) {
         event.onTickState(this);
      } else {
         event.updateState2();
      }
   }

   public float getScaleFactor() {
      return this.factor;
   }

   public static void onTickState(EventTickState event, float factor2, StateTypes stateTypes2) {
      event.applyStateTypes(factor2, stateTypes2);
   }

   private void applyStateTypes(float factor2, StateTypes stateTypes2) {
      this.factor = factor2;
      this.stateTypes = stateTypes2;
   }
}
