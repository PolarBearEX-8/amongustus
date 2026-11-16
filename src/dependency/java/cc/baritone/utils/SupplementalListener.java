package cc.baritone.utils;

import java.util.function.Consumer;

public interface SupplementalListener {
   default void applyConsumer(Consumer consumer) {
      NativeListener nativeListener = this.getNativeListener();
      if (nativeListener != null) {
         consumer.accept(nativeListener);
      }
   }

   NativeListener getNativeListener();
}
