package client.util;

import java.util.concurrent.Callable;

public class VectorDispatcherTask implements Callable {
   public final VectorDispatcher vectorDispatcher;

   public String getText() throws Exception {
      return this.vectorDispatcher.getText();
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public VectorDispatcherTask(VectorDispatcher vectorDispatcher2) {
      this.vectorDispatcher = vectorDispatcher2;
   }
}
