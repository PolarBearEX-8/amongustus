package client.event;

import client.util.StandardAbstractEventAdapter;

public interface RenderTickListener {
   StandardAbstractEventAdapter standardAbstractEventAdapter = new StandardAbstractEventAdapter();

   static void updateState11() {
      EventBusManager.getDietrichEvents2().post(29, standardAbstractEventAdapter);
   }

   void onPostRenderTick();
}
