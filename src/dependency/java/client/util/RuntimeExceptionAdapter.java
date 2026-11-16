package client.util;

public final class RuntimeExceptionAdapter extends RuntimeException {
   public static final RuntimeExceptionAdapter runtimeExceptionAdapter = new RuntimeExceptionAdapter();

   private RuntimeExceptionAdapter() {
      this.setStackTrace(new StackTraceElement[0]);
   }

   @Override
   public synchronized Throwable fillInStackTrace() {
      this.setStackTrace(new StackTraceElement[0]);
      return this;
   }
}
