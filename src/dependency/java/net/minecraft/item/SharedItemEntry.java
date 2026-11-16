package net.minecraft.item;

import client.Client;
import client.util.AutoReportCache;
import client.util.FriendListManager;
import client.util.FriendProfile;
import client.util.FriendRecord;
import client.util.MapEntry3Entry;
import client.util.TimeHelper;
import net.minecraft.client.Minecraft;

public class SharedItemEntry {
   public static final MapEntry3Entry mapEntry3Entry = new MapEntry3Entry();
   public static final StringBuilder stringBuilder = new StringBuilder(" [");

   public static boolean matchesText(String text) {
      String text2 = (String)Minecraft.getMinecraft().getNetHandlerPlayClient().getEntryMap2().get(text);
      if (text2 == null) {
         return false;
      } else {
         FriendRecord friendRecord = (FriendRecord)AutoReportCache.entryMap2.get(text);
         return friendRecord != null && friendRecord.getLabelText().equals(text2);
      }
   }

   private static String resolveText(String text) {
      stringBuilder.delete(2, stringBuilder.length());
      FriendRecord friendRecord = (FriendRecord)AutoReportCache.entryMap2.get(text);
      if (friendRecord == null) {
         return "";
      } else {
         String text2 = friendRecord.getText();
         boolean flag = false;
         if (text2 != null) {
            FriendProfile friendProfile = (FriendProfile)FriendListManager.getEntryMap().get(text2);
            if (friendProfile != null && friendProfile.getIndex() > 0) {
               stringBuilder.append("F");
               flag = true;
            }
         }

         Client.getClient().getModuleManager();
         stringBuilder.append("]");
         return flag ? stringBuilder.toString() : "";
      }
   }

   public static String resolveText2(String text) {
      if (Minecraft.getMinecraft().session != null && !AutoReportCache.entryMap2.isEmpty()) {
         Client.getClient().getModuleManager();
      }

      return null;
   }

   public static String resolveTextByText(String text) {
      return resolveText(text);
   }
}
