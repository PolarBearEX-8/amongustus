package client.event;

public interface KeyInputListener {
   EventKeyInput eventKeyInput = new EventKeyInput();

   void onKeyInput(EventKeyInput event);

   static void applyIndex(int count, boolean flag) {
      EventKeyInput.onKeyInput(eventKeyInput, count, flag);
      EventBusManager.getDietrichEvents2().post(14, eventKeyInput);
   }
}
