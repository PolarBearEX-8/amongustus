package client.util;

import client.event.EventBusManager;
import client.event.EventRotation;
import client.event.RotationListener;
import client.event.TickListener;

public class ConsumerEntryAdapter extends ConsumerEntry implements RotationListener, TickListener {
   public final TimeHelper timer;
   public boolean enabled2 = false;

   @Override
   public void updateState2() {
      EventBusManager.getDietrichEvents2().unsubscribe(28, this);
      EventBusManager.getDietrichEvents2().unsubscribe(40, this);
   }

   @Override
   public void onRotation(EventRotation event) {
   }

   @Override
   public void onPostRotation(EventRotation event) {
      if (minecraft.thePlayer.onGround && minecraft.currentScreen == null && this.enabled2 && this.timer.hasReached(300L)) {
         double offset = 1.0;
         this.applyDouble(offset);
         this.enabled2 = false;
      }

      this.timer.reset();
   }

   @Override
   public String getTextAsText() {
      return "times";
   }

   @Override
   public String getText() {
      return "Jump";
   }

   @Override
   public void updateState() {
      EventBusManager.getDietrichEvents2().subscribe(28, this);
      EventBusManager.getDietrichEvents2().subscribe(40, this);
   }

   @Override
   public void onPreTick() {
      if (minecraft.thePlayer.indexOffset41 > 8) {
         this.enabled2 = true;
      }
   }

   public ConsumerEntryAdapter(int count) {
      super((double)count);
      this.timer = new TimeHelper();
   }
}
