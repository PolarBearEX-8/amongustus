package client.event;

import client.util.ExtraAbstractEventAdapter;

public interface EventClickGuiListener {
   ExtraAbstractEventAdapter extraAbstractEventAdapter = new ExtraAbstractEventAdapter();

   void processAction();

   static void updateState14() {
      EventBusManager.getDietrichEvents2().post(8, extraAbstractEventAdapter);
   }
}
