package client.event;

import client.util.InternalAbstractEventAdapter;

public interface EventAutoSoupListener {
   InternalAbstractEventAdapter internalAbstractEventAdapter = new InternalAbstractEventAdapter();

   void eatSoup();

   static void updateState10() {
      EventBusManager.getDietrichEvents2().post(9, internalAbstractEventAdapter);
   }
}
