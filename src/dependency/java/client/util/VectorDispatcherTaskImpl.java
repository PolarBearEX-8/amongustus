package client.util;

import java.util.concurrent.Callable;

public class VectorDispatcherTaskImpl implements Callable {
   public final VectorDispatcher vectorDispatcher;

   public String getText() throws Exception {
      return this.vectorDispatcher.getLabelText();
   }

   public VectorDispatcherTaskImpl(VectorDispatcher vectorDispatcher2) {
      this.vectorDispatcher = vectorDispatcher2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
