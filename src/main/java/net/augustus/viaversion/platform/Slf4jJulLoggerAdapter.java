package net.augustus.viaversion.platform;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public final class Slf4jJulLoggerAdapter extends Logger {
   private final org.slf4j.Logger slf4jLogger;

   @Override
   public void log(Level level, String str) {
      if (level == Level.FINE) {
         this.slf4jLogger.debug(str);
      } else if (level == Level.WARNING) {
         this.slf4jLogger.warn(str);
      } else if (level == Level.SEVERE) {
         this.slf4jLogger.error(str);
      } else if (level == Level.INFO) {
         this.slf4jLogger.info(str);
      } else {
         this.slf4jLogger.trace(str);
      }
   }

   @Override
   public void log(Level level, String str, Object obj) {
      if (level == Level.FINE) {
         this.slf4jLogger.debug(str, obj);
      } else if (level == Level.WARNING) {
         this.slf4jLogger.warn(str, obj);
      } else if (level == Level.SEVERE) {
         this.slf4jLogger.error(str, obj);
      } else if (level == Level.INFO) {
         this.slf4jLogger.info(str, obj);
      } else {
         this.slf4jLogger.trace(str, obj);
      }
   }

   public Slf4jJulLoggerAdapter(org.slf4j.Logger logger) {
      super("logger", null);
      this.slf4jLogger = logger;
   }

   @Override
   public void log(LogRecord logRecord) {
      String message = logRecord.getMessage();
      this.log(logRecord.getLevel(), message);
   }

   @Override
   public void log(Level level, String str, Throwable t) {
      if (level == Level.FINE) {
         this.slf4jLogger.debug(str, t);
      } else if (level == Level.WARNING) {
         this.slf4jLogger.warn(str, t);
      } else if (level == Level.SEVERE) {
         this.slf4jLogger.error(str, t);
      } else if (level == Level.INFO) {
         this.slf4jLogger.info(str, t);
      } else {
         this.slf4jLogger.trace(str, t);
      }
   }

   @Override
   public void log(Level level, String str, Object[] array) {
      this.log(level, MessageFormat.format(str, array));
   }



}
