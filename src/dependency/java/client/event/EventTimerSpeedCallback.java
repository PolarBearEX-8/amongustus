package client.event;

public interface EventTimerSpeedCallback {
   EventTimerSpeed eventTimerSpeed = new EventTimerSpeed();

   static EventTimerSpeed resolveEventTimerSpeed(float factor, boolean flag) {
      EventTimerSpeed.onTimerSpeed(eventTimerSpeed, factor, flag);
      EventBusManager.getDietrichEvents2().post(48, eventTimerSpeed);
      return eventTimerSpeed;
   }

   void onTimerSpeed(EventTimerSpeed event);
}
