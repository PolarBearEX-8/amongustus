package client.event;

public interface SafeWalkListener {
   EventSafeWalk eventSafeWalk = new EventSafeWalk();

   void onSafeWalk(EventSafeWalk event);

   static void applyText(double posX, double posY, double posZ, String text) {
      EventSafeWalk.onSafeWalk(eventSafeWalk, posX, posY, posZ, text);
      EventBusManager.getDietrichEvents2().post(35, eventSafeWalk);
   }
}
