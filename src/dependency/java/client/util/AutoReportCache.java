package client.util;

import augustus.modules.render.CustomName;
import client.Client;
import client.module.Module;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class AutoReportCache {
   public static final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
   public static final ConcurrentHashMap entryMap2 = new ConcurrentHashMap();

   public static void updateState() {
      concurrentHashMap.clear();
      entryMap2.clear();
   }

   public static String resolveText(String text, String text2, String text3, String text4, int count, String text5) {
      switch (text) {
         case "Xanax":
            return "§7[" + text4 + "§7] §c§l" + text3 + " §7[§e" + count + "§7]§r" + text5;
         case "Xanax2":
            return "§7[" + text4 + "§7] §c§l" + text2 + " §7[§e" + count + "§7]§r" + text5;
         case "Centaurus":
            return "§f[§9A§f-§6" + text4 + "§f] " + text3 + " §7[§e" + count + "§7]§r" + text5;
         case "Centaurus2":
            return "§6[§9" + text4 + "§6] §f" + text3 + " §7[§e" + count + "§7]§r" + text5;
         case "Skid":
            return "§8[§aAugi-" + text4 + "§8] §r" + text2 + " §7[§e" + count + "§7]§r" + text5;
         case "Simple":
            return text2 + " §6[§9" + text3 + "§6] §r§7[§r" + text4 + "§r§7] [§e" + count + "§7]" + text5;
         default:
            return text2;
      }
   }

   public static String[] getStringArray() {
      return null;
   }

   public static boolean checkEntityPlayer(EntityPlayer player, int count, String text) {
      return player != null && player.getGameProfile() != null
         ? count == player.getEntityId()
            || Minecraft.getMinecraft().getServerData() != null
               && player.getGameProfile().getId() != null
               && player.getGameProfile().getId().toString().equals(text)
         : false;
   }

   public static void applyText(String text, String text2, String text3, int count, String text4, String text5, String text6, long value, int count2) {
      FriendRecord friendRecord = new FriendRecord(text, text2, text3, count, text4, text5, text6, value, count2);
      concurrentHashMap.put(value, friendRecord);
      entryMap2.put(friendRecord.getLabelText5(), friendRecord);
   }

   public static void applyTime(long value) {
      FriendRecord friendRecord = (FriendRecord)concurrentHashMap.get(value);
      if (friendRecord != null) {
         entryMap2.remove(friendRecord.getLabelText5());
         concurrentHashMap.remove(value);
      }
   }

   private static String resolveText(String text, String text2, String text3, String text4, String text5, int count) {
      int count2 = text.indexOf(text2);
      if (count2 == -1) {
         return text;
      } else {
         StringBuilder builder = new StringBuilder(text.length());

         int count3;
         for (count3 = 0; count2 != -1; count2 = text.indexOf(text2, count3)) {
            builder.append(text, count3, count2);
            String text6 = TabListBotFilter.resolveText(text.substring(0, count2), '§');
            builder.append(resolveText(text4, text2, text3, text5, count, text6));
            count3 = count2 + text2.length();
         }

         builder.append(text.substring(count3));
         return builder.toString();
      }
   }

   public static String resolveText(String text) {
      if (Minecraft.getMinecraft().session != null && ((Module)Client.getClient().getModuleManager().customName).isToggled()) {
         String text2 = Minecraft.getMinecraft().session.getText1();
         if (!text.contains(text2)) {
            return text;
         } else {
            Client clientItem = Client.getClient();
            CustomName customName = clientItem.getModuleManager().customName;
            String displayName = customName.displayName2;
            String text3 = clientItem.getString3() + clientItem.getString6();
            int intItem = clientItem.getInt2();
            if (!displayName.isEmpty() && !text3.isEmpty()) {
               String selected = customName.settingNameFormatting.getSelected();
               return resolveText(text, text2, displayName, selected, text3, intItem);
            } else {
               return text;
            }
         }
      } else {
         return text;
      }
   }
}
