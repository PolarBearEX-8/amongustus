package net.optifine.shaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class SMCLog {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final String PREFIX = null;

   public static void info(String str) {
      LOGGER.info("[Shaders] " + str);
   }

   public static void severe(String str) {
      LOGGER.error("[Shaders] " + str);
   }

   public static void severe(String str, Object[] array) {
      String str2 = String.format(str, array);
      LOGGER.error("[Shaders] " + str2);
   }

   public static void warning(String str, Object[] array) {
      String str2 = String.format(str, array);
      LOGGER.warn("[Shaders] " + str2);
   }

   public static void fine(String str) {
      LOGGER.debug("[Shaders] " + str);
   }

   public static void info(String str, Object[] array) {
      String str2 = String.format(str, array);
      LOGGER.info("[Shaders] " + str2);
   }

   public static void fine(String str, Object[] array) {
      String str2 = String.format(str, array);
      LOGGER.debug("[Shaders] " + str2);
   }

   public static void warning(String str) {
      LOGGER.warn("[Shaders] " + str);
   }
}
