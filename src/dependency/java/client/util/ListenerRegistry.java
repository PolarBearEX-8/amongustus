package client.util;

import client.event.CompositeListener;

public class ListenerRegistry implements CompositeListener {
   public long timestamp;
   public final VanillaRunnable vanillaRunnable;

   @Override
   public void applyText(String text) {
   }

   @Override
   public void applyText2(String text) {
   }

   @Override
   public void setCount2(int count) {
      if (System.currentTimeMillis() - this.timestamp >= 1000L) {
         this.timestamp = System.currentTimeMillis();
         VanillaRunnable.getLogger().info("Converting... " + count + "%");
      }
   }

   @Override
   public void applyTextByText(String text) {
   }

   @Override
   public void updateState() {
   }

   public ListenerRegistry(VanillaRunnable vanillaRunnable2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.timestamp = System.currentTimeMillis();
   }
}
