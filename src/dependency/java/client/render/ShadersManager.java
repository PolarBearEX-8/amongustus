package client.render;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ShadersManager {
   public static final Logger logger = LogManager.getLogger();
   public static final String labelText = "[Shaders] ";

   public static void applyText2(String text) {
      logger.debug("[Shaders] " + text);
   }

   public static void applyText(String text) {
      logger.info("[Shaders] " + text);
   }

   public static void applyTextByText(String text) {
      logger.error("[Shaders] " + text);
   }

   public static void applyText(String text, Object[] entryArray) {
      String text2 = String.format(text, entryArray);
      logger.error("[Shaders] " + text2);
   }

   public static void applyText2(String text, Object[] entryArray) {
      String text2 = String.format(text, entryArray);
      logger.info("[Shaders] " + text2);
   }

   public static void applyText3(String text) {
      logger.warn("[Shaders] " + text);
   }

   public static void applyText3(String text, Object[] entryArray) {
      String text2 = String.format(text, entryArray);
      logger.warn("[Shaders] " + text2);
   }

   public static void applyTextByText(String text, Object[] entryArray) {
      String text2 = String.format(text, entryArray);
      logger.debug("[Shaders] " + text2);
   }
}
