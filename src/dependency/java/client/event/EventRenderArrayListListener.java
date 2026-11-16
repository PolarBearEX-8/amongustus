package client.event;

import client.util.AdvancedAbstractEventAdapter;

public interface EventRenderArrayListListener {
   AdvancedAbstractEventAdapter advancedAbstractEventAdapter = new AdvancedAbstractEventAdapter();

   void updateState();

   static void updateState12() {
      EventBusManager.getDietrichEvents2().post(11, advancedAbstractEventAdapter);
   }
}
