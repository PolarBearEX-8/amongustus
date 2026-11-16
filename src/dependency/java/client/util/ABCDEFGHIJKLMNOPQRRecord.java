package client.util;

public class ABCDEFGHIJKLMNOPQRRecord {
   public static final String labelText;
   public static final String labelText2;

   private ABCDEFGHIJKLMNOPQRRecord() {
   }

   static {
      String[] textArray = new String[]{"0123456789", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"};
      labelText2 = textArray[0];
      labelText = textArray[1];
   }
}
