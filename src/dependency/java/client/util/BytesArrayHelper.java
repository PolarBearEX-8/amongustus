package client.util;

import java.security.InvalidParameterException;

public class BytesArrayHelper {
   public static String resolveText(byte[] byteValueArray) {
      Object entry = null;
      if (byteValueArray == null) {
         throw new InvalidParameterException("Bytes array cannot be null");
      } else {
         entry = new StringBuilder();

         for (byte byteValue : byteValueArray) {
            applyStringBuilder(byteValue, (StringBuilder)entry);
         }

         return entry.toString();
      }
   }

   private static char computeCharCode(byte byteValue) {
      return byteValue < '\n' ? (char)('0' + byteValue) : (char)('A' + byteValue - 10);
   }

   private static void applyStringBuilder(byte byteValue, StringBuilder builder) {
      byte byteValue2 = 0;
      byte byteValue3 = 0;
      byteValue2 = (byte)(byteValue & 15);
      byteValue3 = (byte)(byteValue >> 4 & 15);
      builder.append(computeCharCode(byteValue3));
      builder.append(computeCharCode(byteValue2));
   }
}
