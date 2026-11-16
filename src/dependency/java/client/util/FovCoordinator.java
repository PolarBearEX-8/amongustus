package client.util;

import java.util.concurrent.Callable;

public class FovCoordinator implements Callable {
   public final ServiceStringEntry serviceStringEntry;
   public final ListenerAdapter27 listenerAdapter27;

   public String getText() throws Exception {
      return this.serviceStringEntry.getLabelText3();
   }

   public FovCoordinator(ListenerAdapter27 listenerAdapter, ServiceStringEntry serviceStringEntry2) {
      this.listenerAdapter27 = listenerAdapter;
      this.serviceStringEntry = serviceStringEntry2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
