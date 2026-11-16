package client.event;

import client.util.ExtendedAbstractEventAdapter;
import de.florianmichael.dietrichevents2.StateTypes;

public interface MotionListener {
   ExtendedAbstractEventAdapter extendedAbstractEventAdapter = new ExtendedAbstractEventAdapter();

   static void applyStateTypesByStateTypes(StateTypes stateTypes) {
      ExtendedAbstractEventAdapter.resolveStateTypes(extendedAbstractEventAdapter, stateTypes);
      EventBusManager.getDietrichEvents2().post(34, extendedAbstractEventAdapter);
   }

   default void onPreMotion() {
   }

   void onPostMotion();
}
