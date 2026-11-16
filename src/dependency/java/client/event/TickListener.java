package client.event;

import client.setting.RenderSliderProperty;
import client.util.SecondaryAbstractEventAdapter;

public interface TickListener {
   SecondaryAbstractEventAdapter secondaryAbstractEventAdapter = new SecondaryAbstractEventAdapter();

   static void applyRenderSliderProperty(RenderSliderProperty renderSliderProperty) {
      SecondaryAbstractEventAdapter.applySecondaryAbstractEventAdap(secondaryAbstractEventAdapter, renderSliderProperty);
      EventBusManager.getDietrichEvents2().post(40, secondaryAbstractEventAdapter);
   }

   default void onRunTick() {
   }

   default void onClientTick() {
   }

   default void onTick() {
   }

   default void onPreTick() {
   }

   default void onPostTick() {
   }
}
