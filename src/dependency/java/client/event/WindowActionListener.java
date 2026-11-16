package client.event;

public interface WindowActionListener {
   void onWindowAction(EventWindowAction event);

   static void applyIndexByIndex(int count, int count2) {
      EventBusManager.getDietrichEvents2().post(56, new EventWindowAction(count, count2));
   }
}
