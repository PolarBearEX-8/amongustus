package net.minecraft.item;

import client.event.EventBusManager;
import client.util.TertiaryAbstractEventAdapter;

public interface TertiaryItemCallback {
   TertiaryAbstractEventAdapter tertiaryAbstractEventAdapter = new TertiaryAbstractEventAdapter();

   void applyIndex(int count, int count2);

   static void applyIndex3(int count, int count2) {
      TertiaryAbstractEventAdapter.applyTertiaryAbstractEventAdapt(tertiaryAbstractEventAdapter, count, count2);
      EventBusManager.getDietrichEvents2().post(47, tertiaryAbstractEventAdapter);
   }
}
