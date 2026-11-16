package client.util;

import client.event.DynamicListener;
import java.util.function.Supplier;

@Deprecated
public class SecondaryListenerRegistry implements DynamicListener {
   public final Supplier supplier;
   public final Runnable runnable;

   @Override
   public void run(String text) {
      this.runnable.run();
   }

   @Override
   public String getLabelText() {
      return (String)this.supplier.get();
   }

   public SecondaryListenerRegistry(Supplier supplier2, Runnable task) {
      this.supplier = supplier2;
      this.runnable = task;
   }
}
