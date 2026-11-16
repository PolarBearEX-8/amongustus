package client.network;

import client.event.EventBusManager;
import client.util.CommonAbstractEventAdapter;

public interface StateUpdateListener {
   CommonAbstractEventAdapter commonAbstractEventAdapter = new CommonAbstractEventAdapter();

   static void updateState2() {
      EventBusManager.getDietrichEvents2().post(61, commonAbstractEventAdapter);
   }

   void updateState();
}
