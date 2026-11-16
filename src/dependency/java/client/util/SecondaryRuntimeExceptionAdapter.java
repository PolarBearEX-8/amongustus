package client.util;

import net.minecraft.crash.CrashReport;

public class SecondaryRuntimeExceptionAdapter extends RuntimeException {
   public final CrashReport crashReport;

   public CrashReport getCrashReport() {
      return this.crashReport;
   }

   public SecondaryRuntimeExceptionAdapter(CrashReport crashReport2) {
      this.crashReport = crashReport2;
   }

   @Override
   public Throwable getCause() {
      return this.crashReport.getThrowable();
   }

   @Override
   public String getMessage() {
      return this.crashReport.getLabelText();
   }
}
