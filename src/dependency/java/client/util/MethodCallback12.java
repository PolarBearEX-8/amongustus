package client.util;

import java.lang.reflect.Method;

public final class MethodCallback12 implements Callback12 {
   public boolean enabled;
   public final Method method;
   public final Object dataRef;

   public MethodCallback12(Method method2, Object entry) {
      this.method = method2;
      this.dataRef = entry;
      this.enabled = false;
   }

   @Override
   public long getTimestamp() {
      if (this.enabled) {
         return -1L;
      } else {
         try {
            return (Long)this.method.invoke(this.dataRef);
         } catch (Throwable throwable) {
            BuildManager.applyText3("" + throwable.getClass().getName() + ": " + throwable.getMessage());
            this.enabled = true;
            return -1L;
         }
      }
   }
}
