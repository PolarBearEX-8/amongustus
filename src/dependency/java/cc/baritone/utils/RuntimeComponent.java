package cc.baritone.utils;

public class RuntimeComponent extends Exception {
   public final int errorCode;

   public RuntimeComponent(int errorCode, Throwable cause) {
      super(cause);
      this.errorCode = errorCode;
   }
}
