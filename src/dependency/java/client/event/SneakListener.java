package client.event;

public interface SneakListener {
   EventSneak eventSneak = new EventSneak();

   void onSneak(EventSneak event);

   static EventSneak getEventSneak() {
      EventSneak.onSneak(eventSneak);
      EventBusManager.getDietrichEvents2().post(39, eventSneak);
      return eventSneak;
   }
}
