package client.util;

import client.event.MotionListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;

public class ExtendedAbstractEventAdapter extends AbstractEvent {
   public StateTypes stateTypes;
   public static final int count = 34;

   public static StateTypes resolveStateTypes(ExtendedAbstractEventAdapter event, StateTypes stateTypes2) {
      return event.stateTypes = stateTypes2;
   }

   public void applyMotionListener(MotionListener motionListener) {
      if (this.stateTypes == StateTypes.PRE) {
         motionListener.onPreMotion();
      } else {
         motionListener.onPostMotion();
      }
   }

   public void call(Object entry) {
      MotionListener motionListener = (MotionListener)entry;
      this.applyMotionListener(motionListener);
   }
}
