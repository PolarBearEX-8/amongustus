package client.util;

import java.text.DecimalFormat;

public class ByteRateFormatter {
   public static final String[] stringArray;
   public static final DecimalFormat decimalFormat;

   public static String formatByteRate(long value) {
      int count = (int)(Math.log((double)value) / Math.log(1024.0));
      double offset = (double)value / Math.pow(1024.0, (double)count);
      if (count < 0) {
         count = 0;
      }

      if (Double.isNaN(offset)) {
         offset = 0.0;
      }

      return decimalFormat.format(offset) + " " + stringArray[count];
   }

   static {
      String[] textArray = new String[]{"MiB", "KiB", "TiB", "#.##", "GiB"};
      stringArray = new String[]{"B", textArray[1], textArray[0], textArray[4], textArray[2]};
      decimalFormat = new DecimalFormat(textArray[3]);
   }
}
