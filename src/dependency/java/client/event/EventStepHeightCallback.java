package client.event;

public interface EventStepHeightCallback {
   EventStepHeight eventStepHeight = new EventStepHeight();

   static EventStepHeight resolveEventStepHeight(int count, double offset) {
      EventStepHeight.onStepHeight(eventStepHeight, count, offset);
      EventBusManager.getDietrichEvents2().post(12, eventStepHeight);
      return eventStepHeight;
   }

   void onStepHeight(EventStepHeight event);
}
