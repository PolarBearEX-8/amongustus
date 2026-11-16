package client.util;

public class ExceptionImpl extends RuntimeException {
   public ExceptionImpl(String text, String text2, Throwable throwable) {
      super(text + ": " + text2, throwable);
   }

   public ExceptionImpl(String text, String text2) {
      super(text + ": " + text2);
   }
}
