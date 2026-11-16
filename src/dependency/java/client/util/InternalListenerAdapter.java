package client.util;

import client.event.DynamicListener;

public class InternalListenerAdapter implements DynamicListener {
   public final String labelText;
   public final Runnable runnable;

   @Override
   public void run(String text) {
      this.runnable.run();
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   public InternalListenerAdapter(String text, Runnable task) {
      this.labelText = text;
      this.runnable = task;
   }
}
