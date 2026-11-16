package client.event;

import client.util.BaseAbstractEventAdapter;

public interface ClickTickListener {
   BaseAbstractEventAdapter baseAbstractEventAdapter = new BaseAbstractEventAdapter();

   static void updateState4() {
      BaseAbstractEventAdapter.applyBaseAbstractEventAdapter(baseAbstractEventAdapter);
      EventBusManager.getDietrichEvents2().post(52, baseAbstractEventAdapter);
   }

   boolean checkCondition();
}
