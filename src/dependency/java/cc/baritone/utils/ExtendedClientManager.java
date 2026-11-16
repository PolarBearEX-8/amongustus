package cc.baritone.utils;

public abstract class ExtendedClientManager extends ExceptionAdapter {
   public ExtendedClientManager(String text) {
      super(text);
   }

   public ExtendedClientManager(String text, Throwable throwable) {
      super(text, throwable);
   }
}
