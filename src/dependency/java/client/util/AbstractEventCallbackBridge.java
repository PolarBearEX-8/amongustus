package client.util;

import client.event.Event;
import client.event.EventBusManager;
import client.event.EventListener;

public class AbstractEventCallbackBridge extends ConsumerEntry implements EventListener {
   @Override
   public void updateState2() {
      EventBusManager.getDietrichEvents2().unsubscribe(53, this);
   }

   @Override
   public String getTextAsText() {
      return "times";
   }

   @Override
   public String getText() {
      return "Leftclick";
   }

   @Override
   public void applyEvent(Event event) {
      double offset = 1.0;
      this.applyDouble(offset);
   }

   @Override
   public void updateState() {
      EventBusManager.getDietrichEvents2().subscribe(53, this);
   }

   public AbstractEventCallbackBridge(int count) {
      super((double)count);
   }
}
