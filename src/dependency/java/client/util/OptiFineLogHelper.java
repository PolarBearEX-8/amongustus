package client.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OptiFineLogHelper {
   public static final Logger logger = LogManager.getLogger();
   public static final boolean enabled = System.getProperty("log.detail", "false").equals("true");

   public static void applyText3(String text) {
      if (enabled) {
         logger.info("[OptiFine] " + text);
      }
   }

   public static void applyText(String text) {
      logger.error("[OptiFine] " + text);
   }

   public static void applyTextByText(String text, Throwable throwable) {
      logger.warn("[OptiFine] " + text, throwable);
   }

   public static void applyText4(String text) {
      logger.warn("[OptiFine] " + text);
   }

   public static void applyText(String text, Throwable throwable) {
      logger.error("[OptiFine] " + text, throwable);
   }

   public static void applyText2(String text) {
      applyTextByText(text);
   }

   public static void applyTextByText(String text) {
      logger.info("[OptiFine] " + text);
   }
}
