package cc.baritone.utils;

import java.io.PrintStream;

public class AuxiliaryExceptionAdapter extends Exception {
   public Throwable throwable;

   public Throwable getThrowable() {
      return this.throwable;
   }

   public AuxiliaryExceptionAdapter(String text, Throwable throwable2) {
      super(text);
      this.throwable = throwable2;
   }

   public AuxiliaryExceptionAdapter() {
   }

   public AuxiliaryExceptionAdapter(String text) {
      super(text);
   }

   @Override
   public void printStackTrace(PrintStream printStream) {
      if (this.throwable == null) {
         super.printStackTrace(printStream);
      } else {
         this.throwable.printStackTrace();
      }
   }

   @Override
   public void printStackTrace() {
      this.printStackTrace(System.err);
   }
}
