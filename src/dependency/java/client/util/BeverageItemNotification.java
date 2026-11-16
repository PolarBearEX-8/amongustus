package client.util;

import augustus.modules.render.Notifications;
import client.Client;
import client.gui.ClientNotification;
import client.setting.BeverageNotifyInfoType;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;

public class BeverageItemNotification {
   public static final List list = new CopyOnWriteArrayList();

   private static void applyTextByText(String text, String text2, BeverageNotifyInfoType beverageNotifyInfoType) {
      if (Client.getClient().getModuleManager().notifications.settingAvoidDuplications.isEnabled()) {
         for (ClientNotification clientNotification : (Iterable<ClientNotification>)(Iterable<?>)list) {
            if (clientNotification.getLabelText2().equals(text2) && clientNotification.getLabelText().equals(text)) {
               clientNotification.renderElement();
               return;
            }
         }
      }

      list.add(new ClientNotification(text, text2, beverageNotifyInfoType));
   }

   private static void applyText(String text, String text2, BeverageNotifyInfoType beverageNotifyInfoType) {
      applyTextByText(text, text2, beverageNotifyInfoType);
   }

   public static void updateState() {
      for (int index = 0; index < list.size(); index++) {
         ((ClientNotification)list.get(index)).applyIndex(index);
      }
   }

   public static void updateText(String text, String text2, BeverageNotifyInfoType beverageNotifyInfoType) {
      if (!Minecraft.getMinecraft().isSingleplayer()) {
         ClientManager.applyRunnable2(() -> BeverageItemNotification.applyText(text, text2, beverageNotifyInfoType));
      } else {
         applyTextByText(text, text2, beverageNotifyInfoType);
      }
   }

   public static boolean canExecute() {
      return list.isEmpty();
   }

   public static void processChar() {
      Notifications notifications = Client.getClient().getModuleManager().notifications;

      for (ClientNotification clientNotification : (Iterable<ClientNotification>)(Iterable<?>)list) {
         if (clientNotification.count > 0) {
            TimeHelper timer = clientNotification.timer2;
            long value = System.currentTimeMillis() - 750L;
            timer.setEpochMillis(value);
            timer = clientNotification.timer;
            long value2 = (long)(notifications.settingDuplicateTime.getValue() * 1000.0);
            if (timer.hasReached(value2)) {
               if (clientNotification.count > 10000) {
                  clientNotification.count -= 1000;
               } else if (clientNotification.count > 1000) {
                  clientNotification.count -= 100;
               } else if (clientNotification.count > 100) {
                  clientNotification.count -= 10;
               } else {
                  clientNotification.count--;
               }

               clientNotification.timer.reset();
            }
         } else if (clientNotification.isActive()) {
            list.remove(clientNotification);
         }
      }
   }
}
