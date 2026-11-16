package client.event;

public interface MouseClickListener {
   EventMouseClick eventMouseClick = new EventMouseClick();

   void onMouseClick(EventMouseClick event);

   static EventMouseClick resolveEventMouseClick(int count, float factor) {
      EventMouseClick.onMouseClick(eventMouseClick, count, factor);
      EventBusManager.getDietrichEvents2().post(16, eventMouseClick);
      return eventMouseClick;
   }
}
