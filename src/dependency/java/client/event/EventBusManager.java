package client.event;

import client.Client;
import client.util.ProfileConfigManager;
import de.florianmichael.dietrichevents2.DietrichEvents2;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class EventBusManager {
   public static long timestamp = 0L;
   public static final DietrichEvents2 eventBus = new DietrichEvents2(32, EventBusManager::applyThrowableByThrowable);

   public static void applyThrowable(Throwable throwable) {
      long timestamp2 = System.currentTimeMillis();

      try {
         if (timestamp2 - timestamp > 20000L) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            throwable.printStackTrace(printWriter);
            String text = stringWriter.toString();
            stringWriter.close();
            printWriter.close();
            if (text != null) {
               String text2 = text.substring(0, Math.min(text.length() - 1, 1000));
               ProfileConfigManager profileConfigManager = new ProfileConfigManager();
               String text3 = Client.getClient().getString7() + " " + text2;
               profileConfigManager.applyText2(text3, 0x480462dfa2f4L);
            }

            timestamp = timestamp2;
         }
      } catch (IOException ioException) {
      }
   }

   public static DietrichEvents2 getDietrichEvents2() {
      return eventBus;
   }

   private static void applyThrowableByThrowable(Throwable throwable) {
      Client.getClient().getClass();
      applyThrowable(throwable);
   }
}
