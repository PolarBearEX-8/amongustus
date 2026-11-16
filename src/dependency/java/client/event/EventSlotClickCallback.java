package client.event;

public interface EventSlotClickCallback {
   EventSlotClick eventSlotClick = new EventSlotClick();

   void onSlotClick(EventSlotClick event);

   static EventSlotClick getEventSlotClick() {
      EventSlotClick.onSlotClick(eventSlotClick);
      EventBusManager.getDietrichEvents2().post(51, eventSlotClick);
      return eventSlotClick;
   }
}
