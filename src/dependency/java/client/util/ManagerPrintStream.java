package client.util;

import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManagerPrintStream extends PrintStream {
   public static final Logger logger = LogManager.getLogger();
   public final String labelText;

   private void applyText(String text) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      StackTraceElement stackTraceElement = stackTrace[Math.min(3, stackTrace.length)];
      logger.info("[{}]@.({}:{}): {}", new Object[]{this.labelText, stackTraceElement.getFileName(), stackTraceElement.getLineNumber(), text});
   }

   public ManagerPrintStream(String text, OutputStream outputStream) {
      super(outputStream);
      this.labelText = text;
   }

   @Override
   public void println(String text) {
      this.applyText(text);
   }

   @Override
   public void println(Object entry) {
      this.applyText(String.valueOf(entry));
   }
}
