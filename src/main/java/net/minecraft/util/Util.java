package net.minecraft.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.logging.log4j.Logger;

public class Util {

   public static Util.EnumOS getOSType() {
      String str = System.getProperty("os.name").toLowerCase();
      return str.contains("win")
         ? Util.EnumOS.WINDOWS
         : (
            str.contains("mac")
               ? Util.EnumOS.OSX
               : (
                  str.contains("solaris")
                     ? Util.EnumOS.SOLARIS
                     : (
                        str.contains("sunos")
                           ? Util.EnumOS.SOLARIS
                           : (str.contains("linux") ? Util.EnumOS.LINUX : (str.contains("unix") ? Util.EnumOS.LINUX : Util.EnumOS.UNKNOWN))
                     )
               )
         );
   }

   public static Object runTask(FutureTask futureTask, Logger logger) {
      try {
         futureTask.run();
         return futureTask.get();
      } catch (ExecutionException e) {
      } catch (InterruptedException e2) {
         logger.fatal("Error executing task", e2);
      }

      return null;
   }


   public enum EnumOS {
      LINUX,
      SOLARIS,
      WINDOWS,
      OSX,
      UNKNOWN;
   }
}
