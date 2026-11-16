package cc.baritone.utils;

import java.util.List;

public class DynamicListenerException extends RuntimeException implements EnhancedListener {
   @Override
   public void applyDynamicListener(DynamicListener dynamicListener, List list) {
      DRNHandler.dRNHandler.applyThrowable(this);
   }

   public DynamicListenerException(Throwable throwable) {
      super(throwable);
   }

   public DynamicListenerException(String text) {
      super(text);
   }
}
