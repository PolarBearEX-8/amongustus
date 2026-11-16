package client.event;

public interface EventListener {
   Event event = new Event();

   static Event getAbstractEventAdapter() {
      EventBusManager.getDietrichEvents2().post(53, event);
      return event;
   }

   void applyEvent(Event event2);
}
