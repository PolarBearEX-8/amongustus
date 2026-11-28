package wdl;

import java.util.concurrent.Callable;

public final class WDLCrashInfoCallable implements Callable {

   @Override
   public Object call() throws Exception {
      return this.getCrashInfo();
   }

   public String getCrashInfo() {
      return WDL.getDebugInfo();
   }
}
