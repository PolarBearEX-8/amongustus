package net.minecraft.util;

import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingPrintStream extends PrintStream {
   private static final Logger LOGGER = LogManager.getLogger();
   private final String domain;

   private void logString(String str) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      StackTraceElement stackTraceElement = stackTrace[Math.min(3, stackTrace.length)];
      LOGGER.info("[{}]@.({}:{}): {}", new Object[]{this.domain, stackTraceElement.getFileName(), stackTraceElement.getLineNumber(), str});
   }

   @Override
   public void println(Object obj) {
      this.logString(String.valueOf(obj));
   }

   public LoggingPrintStream(String str, OutputStream out2) {
      super(out2);
      this.domain = str;
   }

   @Override
   public void println(String str) {
      this.logString(str);
   }
}
