package javazoom.jl.decoder;

import java.io.PrintStream;

public class JavaLayerException extends Exception {
   private Throwable exception;

   public Throwable getException() {
      return this.exception;
   }

   public JavaLayerException() {
   }

   @Override
   public void printStackTrace() {
      this.printStackTrace(System.err);
   }

   @Override
   public void printStackTrace(PrintStream out) {
      if (this.exception == null) {
         super.printStackTrace(out);
      } else {
         this.exception.printStackTrace();
      }
   }

   public JavaLayerException(String str) {
      super(str);
   }

   public JavaLayerException(String str, Throwable t) {
      super(str);
      this.exception = t;
   }
}
