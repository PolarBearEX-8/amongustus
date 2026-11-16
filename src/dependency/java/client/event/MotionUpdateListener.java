package client.event;

import de.florianmichael.dietrichevents2.StateTypes;

public interface MotionUpdateListener {
   EventMotionUpdate eventMotionUpdate = new EventMotionUpdate();

   static EventMotionUpdate getEventMotionUpdate(boolean flag, float factor, float factor2, double posX, double posY, double posZ) {
      StateTypes stateTypes = StateTypes.PRE;
      EventMotionUpdate.onMotionUpdate(eventMotionUpdate, flag, factor, factor2, posX, posY, posZ, stateTypes);
      EventBusManager.getDietrichEvents2().post(18, eventMotionUpdate);
      return eventMotionUpdate;
   }

   void onMotionUpdate(EventMotionUpdate event);

   default void onPreMotionUpdate() {
   }

   static void onPostMotionUpdate() {
      StateTypes stateTypes = StateTypes.POST;
      double offset = 0.0;
      double offset2 = 0.0;
      double offset3 = 0.0;
      float factor = 0.0F;
      float factor2 = 0.0F;
      boolean flag = false;
      EventMotionUpdate.onMotionUpdate(eventMotionUpdate, flag, factor2, factor, offset3, offset2, offset, stateTypes);
      EventBusManager.getDietrichEvents2().post(18, eventMotionUpdate);
   }
}
