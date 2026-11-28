package net.augustus.events;

import net.augustus.Augustus;
import de.florianmichael.dietrichevents2.DietrichEvents2;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class EventManager {
   private static long lastExceptionReportTime = 0L;
   private static final DietrichEvents2 EVENT_BUS;

   public static void reportException(Throwable t) {
      long currentTimeMs = System.currentTimeMillis();

      try {
         if (currentTimeMs - lastExceptionReportTime > 20000L) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            t.printStackTrace(writer);
            String str = stringWriter.toString();
            stringWriter.close();
            writer.close();
            if (str != null) {
               str = str.substring(0, Math.min(str.length() - 1, 1000));
            }

            lastExceptionReportTime = currentTimeMs;
         }
      } catch (IOException e) {
      }
   }

   private static void handleException(Throwable t) {
      reportException(t);
   }

   public static DietrichEvents2 getEventBus() {
      return EVENT_BUS;
   }

   static {
      EVENT_BUS = new DietrichEvents2(32, t -> handleException(t));
   }
}
