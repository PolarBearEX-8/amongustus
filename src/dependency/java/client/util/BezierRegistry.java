package client.util;

import java.util.concurrent.Callable;

public class BezierRegistry implements Callable {
   public final ServiceStringEntry serviceStringEntry;
   public final ListenerAdapter27 listenerAdapter27;

   public String getText() throws Exception {
      return this.serviceStringEntry.size() + " frames";
   }

   public BezierRegistry(ListenerAdapter27 listenerAdapter, ServiceStringEntry serviceStringEntry2) {
      this.listenerAdapter27 = listenerAdapter;
      this.serviceStringEntry = serviceStringEntry2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
