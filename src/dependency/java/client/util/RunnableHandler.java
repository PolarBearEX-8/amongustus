package client.util;

import client.event.SharedListener;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class RunnableHandler implements Runnable {
   public static final RunnableHandler runnableHandler = new RunnableHandler();
   public List list = Collections.synchronizedList(Lists.newArrayList());
   public volatile long timestamp;
   public volatile long timestamp2;
   public volatile boolean enabled;

   @Override
   public void run() {
      while (true) {
         this.updateState();
      }
   }

   public static RunnableHandler getRunnableHandler() {
      return runnableHandler;
   }

   public void sleep() throws InterruptedException {
      this.enabled = true;

      while (this.timestamp != this.timestamp2) {
         Thread.sleep(10L);
      }

      this.enabled = false;
   }

   private void updateState() {
      for (int index = 0; index < this.list.size(); index++) {
         if (!((SharedListener)this.list.get(index)).isActive()) {
            this.list.remove(index--);
            this.timestamp2++;
         }

         try {
            Thread.sleep(this.enabled ? 0L : 10L);
         } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
         }
      }

      if (this.list.isEmpty()) {
         try {
            Thread.sleep(25L);
         } catch (InterruptedException interruptedException2) {
            interruptedException2.printStackTrace();
         }
      }
   }

   public void applySharedListener(SharedListener sharedListener) {
      if (!this.list.contains(sharedListener)) {
         this.timestamp++;
         this.list.add(sharedListener);
      }
   }

   private RunnableHandler() {
      Thread thread = new Thread(this, "File IO Thread");
      thread.setPriority(1);
      thread.start();
   }
}
