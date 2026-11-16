package cc.baritone.utils;

public abstract class ExceptionAdapter extends Exception implements EnhancedListener {
   public ExceptionAdapter(String text) {
      super(text);
   }

   public ExceptionAdapter(String text, Throwable throwable) {
      super(text, throwable);
   }
}
