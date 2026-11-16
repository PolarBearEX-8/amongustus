package client.event;

public interface EventRenderHandCallback {
   EventRenderHand eventRenderHand = new EventRenderHand();

   static EventRenderHand resolveEventRenderHand(float factor, int count) {
      EventRenderHand.onRenderHand(eventRenderHand, factor, count);
      EventBusManager.getDietrichEvents2().post(42, eventRenderHand);
      return eventRenderHand;
   }

   void onRenderHand(EventRenderHand event);
}
