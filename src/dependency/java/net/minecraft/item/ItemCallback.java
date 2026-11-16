package net.minecraft.item;

import client.event.EventBusManager;
import client.util.AltAbstractEventAdapter;

public interface ItemCallback {
   AltAbstractEventAdapter altAbstractEventAdapter = new AltAbstractEventAdapter();

   default void updateState6() {
   }

   static void updateState5() {
      AltAbstractEventAdapter.applyAltAbstractEventAdapter(altAbstractEventAdapter, false);
      EventBusManager.getDietrichEvents2().post(6, altAbstractEventAdapter);
   }

   static void updateState7() {
      AltAbstractEventAdapter.applyAltAbstractEventAdapter(altAbstractEventAdapter, true);
      EventBusManager.getDietrichEvents2().post(6, altAbstractEventAdapter);
   }

   void updateKillAura();
}
