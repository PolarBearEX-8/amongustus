package client.util;

import client.setting.MovementIntervalProperty;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.logging.log4j.Logger;

public class OsNameManager {
   public static MovementIntervalProperty getMovementIntervalProperty() {
      String text = System.getProperty("os.name").toLowerCase();
      return text.contains("win")
         ? MovementIntervalProperty.WINDOWS
         : (
            text.contains("mac")
               ? MovementIntervalProperty.OSX
               : (
                  text.contains("solaris")
                     ? MovementIntervalProperty.SOLARIS
                     : (
                        text.contains("sunos")
                           ? MovementIntervalProperty.SOLARIS
                           : (
                              text.contains("linux")
                                 ? MovementIntervalProperty.LINUX
                                 : (text.contains("unix") ? MovementIntervalProperty.LINUX : MovementIntervalProperty.UNKNOWN)
                           )
                     )
               )
         );
   }

   public static Object resolveTarget(FutureTask futureTask, Logger logger) {
      try {
         futureTask.run();
         return futureTask.get();
      } catch (ExecutionException executionException) {
      } catch (InterruptedException interruptedException) {
         logger.fatal("Error executing task", interruptedException);
      }

      return null;
   }
}
