package net.augustus.utils;

import java.text.DecimalFormat;

public class ByteSizeFormatter {
   private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
   private static final String[] UNITS = new String[]{"B", "KiB", "MiB", "GiB", "TiB"};

   public static String formatBytes(long l) {
      int n = (int)(Math.log((double)l) / Math.log(1024.0));
      double d = (double)l / Math.pow(1024.0, (double)n);
      if (n < 0) {
         n = 0;
      }

      if (Double.isNaN(d)) {
         d = 0.0;
      }

      return DECIMAL_FORMAT.format(d) + " " + UNITS[n];
   }
}
