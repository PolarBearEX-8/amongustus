package net.augustus.ui.notifications;

import net.augustus.Augustus;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.augustus.modules.render.Notifications;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketThreadUtil;

public class NotificationManager {
   private static final List ACTIVE_NOTIFICATIONS = new CopyOnWriteArrayList();

   private static void enqueueOnMainThread(String str, String str2, NotificationLevel notificationLevel) {
      addOrStackNotification(str, str2, notificationLevel);
   }

   public static void updateNotifications() {
      Notifications notifications = Augustus.getInstance().getModuleManager().notifications;

      for (HudNotification hudNotification : (Iterable<HudNotification>)(ACTIVE_NOTIFICATIONS)) {
         if (hudNotification.stackCount > 0) {
            TimeHelper elapsedTimer = hudNotification.elapsedTimer;
            elapsedTimer.setLastTime(System.currentTimeMillis() - 750L);
            elapsedTimer = hudNotification.stackDecayTimer;
            if (elapsedTimer.reached((long)(notifications.duplicateTime.getValue() * 1000.0))) {
               if (hudNotification.stackCount > 10000) {
                  hudNotification.stackCount -= 1000;
               } else if (hudNotification.stackCount > 1000) {
                  hudNotification.stackCount -= 100;
               } else if (hudNotification.stackCount > 100) {
                  hudNotification.stackCount -= 10;
               } else {
                  hudNotification.stackCount--;
               }

               hudNotification.stackDecayTimer.reset();
            }
         } else {
            if (hudNotification.isExpired()) {
               ACTIVE_NOTIFICATIONS.remove(hudNotification);
            }
         }
      }
   }

   public static boolean isEmpty() {
      return ACTIVE_NOTIFICATIONS.isEmpty();
   }

   public static void renderNotifications() {

      for (int i = 0; i < ACTIVE_NOTIFICATIONS.size(); i++) {
         ((HudNotification)ACTIVE_NOTIFICATIONS.get(i)).render(i);
      }
   }

   public static void pushNotification(String str, String str2, NotificationLevel notificationLevel) {
      if (!Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
         PacketThreadUtil.runOnMainThreadImmediate(() -> enqueueOnMainThread(str, str2, notificationLevel));
      } else {
         addOrStackNotification(str, str2, notificationLevel);
      }
   }


   private static void addOrStackNotification(String str, String str2, NotificationLevel notificationLevel) {
      if (Augustus.getInstance().getModuleManager().notifications.avoidDuplications.getValue()) {
         for (HudNotification hudNotification : (Iterable<HudNotification>)(ACTIVE_NOTIFICATIONS)) {
            if (hudNotification.getDescription().equals(str2) && hudNotification.getTitle().equals(str)) {
               hudNotification.incrementStack();
               return;
            }
         }
      }

      ACTIVE_NOTIFICATIONS.add(new HudNotification(str, str2, notificationLevel));
   }
}
