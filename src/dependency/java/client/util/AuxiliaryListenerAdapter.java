package client.util;

import client.event.DynamicListener;

public class AuxiliaryListenerAdapter implements DynamicListener {
   public final String labelText;

   @Override
   public void run(String text) {
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   public AuxiliaryListenerAdapter(String text) {
      this.labelText = text;
   }
}
