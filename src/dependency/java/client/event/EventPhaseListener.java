package client.event;

import client.util.CustomAbstractEventAdapter;

public interface EventPhaseListener {
   CustomAbstractEventAdapter customAbstractEventAdapter = new CustomAbstractEventAdapter();

   static void updateState13() {
      EventBusManager.getDietrichEvents2().post(27, customAbstractEventAdapter);
   }

   void updateState3();
}
