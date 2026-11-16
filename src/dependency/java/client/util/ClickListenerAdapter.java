package client.util;

import client.event.ClickListener;
import client.event.EventBusManager;
import client.event.EventClick;

public class ClickListenerAdapter extends ConsumerEntry implements ClickListener {
   @Override
   public void updateState2() {
      EventBusManager.getDietrichEvents2().unsubscribe(7, this);
   }

   @Override
   public String getTextAsText() {
      return "times";
   }

   @Override
   public void onClick(EventClick event) {
      if (event.getIndexOffset12() + (event.isState3() ? 0 : minecraft.gameSettings.keyBindUseItem.getPressTime()) > 0 && !event.isState2() && !event.isCancelled()) {
         double offset = 1.0;
         this.applyDouble(offset);
      }
   }

   @Override
   public String getText() {
      return "Rightclick";
   }

   @Override
   public void updateState() {
      EventBusManager.getDietrichEvents2().subscribe(7, this, 10000);
   }

   public ClickListenerAdapter(int count) {
      super((double)count);
   }
}
