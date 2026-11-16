package net.minecraft.item;

import client.event.EventBusManager;
import client.util.SpecialAbstractEventAdapter;

public interface SecondaryItemCallback {
   SpecialAbstractEventAdapter specialAbstractEventAdapter = new SpecialAbstractEventAdapter();

   static SpecialAbstractEventAdapter resolveSpecialAbstractEventAdapter(double offset) {
      SpecialAbstractEventAdapter.applySpecialAbstractEventAdapte(specialAbstractEventAdapter, offset);
      EventBusManager.getDietrichEvents2().post(15, specialAbstractEventAdapter);
      return specialAbstractEventAdapter;
   }

   void applySpecialAbstractEventAdapte(SpecialAbstractEventAdapter event);
}
