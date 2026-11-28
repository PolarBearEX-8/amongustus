package net.augustus.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTraceUtil {
   public static String getStackTraceString(Throwable t) {
      StringWriter stringWriter = new StringWriter();
      t.printStackTrace(new PrintWriter(stringWriter));
      return stringWriter.toString();
   }
}
