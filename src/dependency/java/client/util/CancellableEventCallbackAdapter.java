package client.util;

import client.event.EventBusManager;

public interface CancellableEventCallbackAdapter {
   CancellableEventAdapter cancellableEventAdapter = new CancellableEventAdapter();

   static CancellableEventAdapter resolveCancellableEventAdapter(float factor, float factor2) {
      CancellableEventAdapter.applyCancellableEventAdapter(cancellableEventAdapter, factor, factor2);
      EventBusManager.getDietrichEvents2().post(38, cancellableEventAdapter);
      return cancellableEventAdapter;
   }

   void applyCancellableEventAdapter(CancellableEventAdapter event);
}
