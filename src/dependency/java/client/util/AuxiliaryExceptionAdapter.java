package client.util;

public class AuxiliaryExceptionAdapter extends Exception {
   public AuxiliaryExceptionAdapter(String text) {
      super(text);
   }

   public AuxiliaryExceptionAdapter(String text, Throwable throwable) {
      super(text, throwable);
   }
}
