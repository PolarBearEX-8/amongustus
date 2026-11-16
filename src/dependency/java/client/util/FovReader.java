package client.util;

import java.util.concurrent.Callable;

public class FovReader implements Callable {
   public final ServiceStringEntry serviceStringEntry;
   public final ListenerAdapter27 listenerAdapter27;

   public FovReader(ListenerAdapter27 listenerAdapter, ServiceStringEntry serviceStringEntry2) {
      this.listenerAdapter27 = listenerAdapter;
      this.serviceStringEntry = serviceStringEntry2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return this.serviceStringEntry.getCount() + " x " + this.serviceStringEntry.getCount2();
   }
}
