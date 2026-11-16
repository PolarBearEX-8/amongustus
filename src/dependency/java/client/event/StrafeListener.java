package client.event;

public interface StrafeListener {
   EventStrafe eventStrafe = new EventStrafe();

   static EventStrafe resolveEventStrafe(float posX, float posY, float posZ) {
      EventStrafe.onStrafe(eventStrafe, posX, posY, posZ);
      EventBusManager.getDietrichEvents2().post(24, eventStrafe);
      return eventStrafe;
   }

   void onStrafe(EventStrafe event);
}
