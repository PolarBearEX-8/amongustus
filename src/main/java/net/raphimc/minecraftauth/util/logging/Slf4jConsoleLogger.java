package net.raphimc.minecraftauth.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jConsoleLogger implements ILogger {
   private final Logger logger;

   public Slf4jConsoleLogger() {
      this(LoggerFactory.getLogger("MinecraftAuth"));
   }

   public Slf4jConsoleLogger(Logger logger) {
      this.logger = logger;
   }

   @Override
   public void info(String message) {
      this.logger.info(message);
   }

   @Override
   public void warn(String message) {
      this.logger.warn(message);
   }

   @Override
   public void error(String message) {
      this.logger.error(message);
   }
}
