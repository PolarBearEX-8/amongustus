package client.util;

import client.setting.Setting;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class FriendListManager {
   public static final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

   public static void remove(String text) {
      concurrentHashMap.remove(text);
   }

   public static void clear() {
      concurrentHashMap.clear();
   }

   public static Collection values() {
      return concurrentHashMap.values();
   }

   private static Setting[] settingArray;

   public static Setting[] getSettingArray() {
      return settingArray;
   }

   public static void applyText(String text, int count, int count2) {
      getSettingArray();
      FriendProfile friendProfile = (FriendProfile)concurrentHashMap.get(text);
      if (friendProfile != null) {
         concurrentHashMap.remove(text);
         friendProfile.setCount(count);
         concurrentHashMap.put(text, friendProfile);
      } else {
         concurrentHashMap.put(text, new FriendProfile(text, count, count2));
      }
   }

   public static ConcurrentHashMap getEntryMap() {
      return concurrentHashMap;
   }

   static {
      Setting[] settingArray = new Setting[4];
   }
}
