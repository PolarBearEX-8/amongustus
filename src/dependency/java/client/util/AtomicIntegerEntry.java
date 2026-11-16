package client.util;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AtomicIntegerEntry {
   public static final AtomicInteger atomicInteger = new AtomicInteger(0);
   public static final Logger logger = LogManager.getLogger();

   public static Logger getLogger() {
      return logger;
   }

   public static AtomicInteger getAtomicInteger() {
      return atomicInteger;
   }
}
