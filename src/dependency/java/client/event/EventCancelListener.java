package client.event;

public interface EventCancelListener {
   EventCancelAction eventCancelAction = new EventCancelAction();

   void onCancelAction(EventCancelAction event);

   static EventCancelAction getEventCancelAction() {
      eventCancelAction.setCancelled(false);
      EventBusManager.getDietrichEvents2().post(4, eventCancelAction);
      return eventCancelAction;
   }
}
