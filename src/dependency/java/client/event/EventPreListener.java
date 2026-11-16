package client.event;

public interface EventPreListener {
   EventPreAction eventPreAction = new EventPreAction();

   static EventPreAction getEventPreAction() {
      eventPreAction.setCancelled(false);
      EventBusManager.getDietrichEvents2().post(59, eventPreAction);
      return eventPreAction;
   }

   void onPreAction(EventPreAction event);
}
