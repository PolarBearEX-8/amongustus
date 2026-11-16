package cc.baritone.utils;

import client.util.AlertNotificationSound;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DownloadInGeneralManager {
   public static Logger logger = LogManager.getLogger();
   public static HashSet hashSet = new HashSet();
   public static boolean enabled7 = true;
   public static boolean enabled5 = true;
   public static int count = -1;
   public static boolean enabled2 = true;
   public static boolean enabled3 = true;
   public static boolean enabled6 = true;
   public static boolean enabled4 = true;
   public static Map map2 = new HashMap();
   public static boolean enabled = false;
   public static String labelText = "";
   public static Map map3 = new HashMap();
   public static Map map = new HashMap();
   public static final List list3 = Arrays.asList("downloadInGeneral", "cacheChunks", "saveEntities", "saveTileEntities", "saveContainers", "getEntityRanges");
   public static final List list = Arrays.asList("saveRadius");
   public static List list2 = new java.util.ArrayList();

   public static boolean isActive7() {
      if (!isActive4()) {
         return false;
      } else {
         return hashSet.contains(1) ? enabled6 : isActive5();
      }
   }

   public static Map copyOfAsMap() {
      return ImmutableMap.copyOf(map);
   }

   public static int computeCount(String text) {
      if (!matchesAlertNotificationSound3(null)) {
         return -1;
      } else if (hashSet.contains(2)) {
         return map2.containsKey(text) ? (Integer)map2.get(text) : -1;
      } else {
         return -1;
      }
   }

   public static boolean matchesAlertNotificationSoundByAlertNotificatio(AlertNotificationSound alertNotificationSound) {
      return alertNotificationSound == null ? false : checkIndexByIndex(alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset);
   }

   public static boolean isEnabled2() {
      return enabled2;
   }

   public static boolean checkIndex(int count2, int count3) {
      return checkIndexByIndex(count2, count3) ? true : isActive7();
   }

   public static Map getMap() {
      return new HashMap(map2);
   }

   public static boolean isActiveAsFlag() {
      if (!hashSet.contains(4)) {
         return false;
      } else if (map3 != null && !map3.isEmpty()) {
         Iterator iterator = map3.values().iterator();

         while (iterator.hasNext()) {
            if (!((Multimap)iterator.next()).isEmpty()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static List copyOf() {
      return ImmutableList.copyOf(list2);
   }

   public static boolean matchesAlertNotificationSound(AlertNotificationSound alertNotificationSound) {
      return matchesAlertNotificationSoundByAlertNotificatio(alertNotificationSound) ? true : isActive7();
   }

   public static String getText() {
      return hashSet.contains(3) ? labelText : null;
   }

   public static boolean isActive() {
      if (!isActive4()) {
         return false;
      } else if (!isActive7()) {
         return false;
      } else {
         return hashSet.contains(1) ? enabled4 : isActive5();
      }
   }

   public static boolean isActive3() {
      if (!isActive4()) {
         return false;
      } else {
         return hashSet.contains(1) ? enabled6 : isActive5();
      }
   }

   public static void updateState() {
      Minecraft.getMinecraft();
      hashSet = new HashSet();
      map = new HashMap();
      list2 = new java.util.ArrayList();
      enabled7 = true;
   }

   public static boolean checkIndexByIndex(int count2, int count3) {
      Iterator iterator = map3.values().iterator();

      while (iterator.hasNext()) {
         for (AStarPlanner aStarPlanner : (Iterable<AStarPlanner>) (Iterable) (((Multimap)iterator.next()).values())) {
            if (count2 >= aStarPlanner.count2 && count2 <= aStarPlanner.count3 && count3 >= aStarPlanner.count && count3 <= aStarPlanner.count4) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean matchesAlertNotificationSound4(AlertNotificationSound alertNotificationSound) {
      if (matchesAlertNotificationSoundByAlertNotificatio(alertNotificationSound)) {
         return true;
      } else if (!isActive4()) {
         return false;
      } else if (!hashSet.contains(1)) {
         return isActive5();
      } else {
         if (!enabled2 && count >= 0) {
            int count2 = alertNotificationSound.indexOffset1 - Pokechu22WorldDownloaderManager.entityPlayerSP.count7;
            int count3 = alertNotificationSound.indexOffset - Pokechu22WorldDownloaderManager.entityPlayerSP.count8;
            if (Math.abs(count2) > count || Math.abs(count3) > count) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean isEmpty() {
      return hashSet != null && !hashSet.isEmpty();
   }

   public static boolean checkIndex3(int count2, int count3) {
      return checkIndexByIndex(count2, count3) ? true : isActive2();
   }

   public static boolean isActive9() {
      return hashSet.contains(3) && enabled;
   }

   public static void applyText(String text, byte[] byteValueArray) {
      if ("WDL|CONTROL".equals(text)) {
         ByteArrayDataInput byteArrayDataInput = ByteStreams.newDataInput(byteValueArray);
         int count2 = byteArrayDataInput.readInt();
         hashSet.add(count2);
         switch (count2) {
            case 0:
               enabled7 = byteArrayDataInput.readBoolean();
               LoggerEntry.applyAltListener(OptionType.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet0", new Object[]{enabled7});
               break;
            case 1:
               enabled5 = byteArrayDataInput.readBoolean();
               count = byteArrayDataInput.readInt();
               enabled2 = byteArrayDataInput.readBoolean();
               enabled3 = byteArrayDataInput.readBoolean();
               enabled6 = byteArrayDataInput.readBoolean();
               enabled4 = byteArrayDataInput.readBoolean();
               LoggerEntry.applyAltListener(
                  OptionType.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet1", new Object[]{enabled5, count, enabled2, enabled3, enabled6, enabled4}
               );
               if (!enabled5 && Pokechu22WorldDownloaderManager.enabled) {
                  LoggerEntry.applyAltListener(OptionType.ERROR, "wdl.messages.generalError.forbidden", new Object[0]);
                  Pokechu22WorldDownloaderManager.updateState9();
               }
               break;
            case 2:
               map2.clear();
               int count3 = byteArrayDataInput.readInt();

               for (int index = 0; index < count3; index++) {
                  String text2 = byteArrayDataInput.readUTF();
                  int count4 = byteArrayDataInput.readInt();
                  map2.put(text2, count4);
               }

               LoggerEntry.applyAltListener(OptionType.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet2", new Object[]{map2.size()});
               break;
            case 3:
               enabled = byteArrayDataInput.readBoolean();
               labelText = byteArrayDataInput.readUTF();
               LoggerEntry.applyAltListener(
                  OptionType.PLUGIN_CHANNEL_MESSAGE,
                  "wdl.messages.permissions.packet3",
                  new Object[]{enabled, labelText.length(), Integer.toHexString(labelText.hashCode())}
               );
               break;
            case 4:
               map3.clear();
               int count5 = byteArrayDataInput.readInt();
               int count6 = 0;

               for (int index2 = 0; index2 < count5; index2++) {
                  String text3 = byteArrayDataInput.readUTF();
                  int count7 = byteArrayDataInput.readInt();
                  HashMultimap hashMultimap = HashMultimap.create();

                  for (int index3 = 0; index3 < count7; index3++) {
                     AStarPlanner aStarPlanner = AStarPlanner.resolveAStarPlanner(byteArrayDataInput);
                     hashMultimap.put(aStarPlanner.labelText, aStarPlanner);
                  }

                  map3.put(text3, hashMultimap);
                  count6 += count7;
               }

               LoggerEntry.applyAltListener(OptionType.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet4", new Object[]{count5, count6});
               break;
            case 5:
               String text4 = byteArrayDataInput.readUTF();
               boolean flag = byteArrayDataInput.readBoolean();
               int count8 = byteArrayDataInput.readInt();
               HashMultimap hashMultimap2 = HashMultimap.create();
               if (!flag) {
                  hashMultimap2.putAll((Multimap)map3.get(text4));
               }

               for (int index4 = 0; index4 < count8; index4++) {
                  AStarPlanner aStarPlanner2 = AStarPlanner.resolveAStarPlanner(byteArrayDataInput);
                  hashMultimap2.put(aStarPlanner2.labelText, aStarPlanner2);
               }

               map3.put(text4, hashMultimap2);
               if (flag) {
                  LoggerEntry.applyAltListener(OptionType.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet5.set", new Object[]{count8, text4});
               } else {
                  LoggerEntry.applyAltListener(OptionType.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet5.added", new Object[]{count8, text4});
               }
               break;
            case 6:
               String text5 = byteArrayDataInput.readUTF();
               int count9 = byteArrayDataInput.readInt();
               String[] textArray = new String[count9];

               for (int index5 = 0; index5 < count9; index5++) {
                  textArray[index5] = byteArrayDataInput.readUTF();
               }

               int count10 = 0;

               for (String text6 : textArray) {
                  count10 += ((Multimap)map3.get(text5)).get(text6).size();
                  ((Multimap)map3.get(text5)).removeAll(text6);
               }

               LoggerEntry.applyAltListener(
                  OptionType.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet6", new Object[]{count10, text5, Arrays.toString((Object[])textArray)}
               );
               break;
            case 7:
               String text7 = byteArrayDataInput.readUTF();
               String text8 = byteArrayDataInput.readUTF();
               int count11 = byteArrayDataInput.readInt();
               int size = ((Multimap)map3.get(text7)).removeAll(text8).size();

               for (int index6 = 0; index6 < count11; index6++) {
                  ((Multimap)map3.get(text7)).put(text8, AStarPlanner.resolveAStarPlanner(byteArrayDataInput));
               }

               LoggerEntry.applyAltListener(OptionType.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet7", new Object[]{size, text7, text8, count11});
               break;
            default:
               LoggerEntry.applyAltListener(OptionType.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.unknownPacket", new Object[]{count2});
               StringBuilder builder = new StringBuilder();

               for (byte byteValue : byteValueArray) {
                  builder.append((int)byteValue).append(' ');
               }

               logger.info(builder.toString());
         }
      }
   }

   public static void updateState2() {
      if (!map.isEmpty() || !list2.isEmpty()) {
         ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
         byteArrayDataOutput.writeUTF("REQUEST REASON WILL GO HERE");
         byteArrayDataOutput.writeInt(map.size());

         for (Entry entry : (Iterable<Entry>) (Iterable) (map.entrySet())) {
            byteArrayDataOutput.writeUTF((String)entry.getKey());
            byteArrayDataOutput.writeUTF((String)entry.getValue());
         }

         byteArrayDataOutput.writeInt(list2.size());
         Iterator iterator = list2.iterator();

         while (iterator.hasNext()) {
            ((AStarPlanner)iterator.next()).applyByteArrayDataOutput(byteArrayDataOutput);
         }
      }
   }

   public static boolean matchesAlertNotificationSound3(AlertNotificationSound alertNotificationSound) {
      return matchesAlertNotificationSoundByAlertNotificatio(alertNotificationSound) ? true : isActive2();
   }

   public static boolean isActive6() {
      return isActiveAsFlag() ? true : isActive4();
   }

   public static void add(AStarPlanner aStarPlanner) {
      list2.add(aStarPlanner);
   }

   public static boolean isActive8() {
      return hashSet.contains(2) && map2.size() > 0;
   }

   public static boolean matchesText(String text, String text2) {
      if (text == null || text2 == null) {
         return false;
      } else if (list3.contains(text)) {
         return text2.equals("true") || text2.equals("false");
      } else if (list.contains(text)) {
         try {
            Integer.parseInt(text2);
            return true;
         } catch (NumberFormatException numberFormatException) {
            return false;
         }
      } else {
         return false;
      }
   }

   public static int getCount() {
      return count;
   }

   public static boolean checkIndex2(int count2, int count3) {
      return checkIndexByIndex(count2, count3) ? true : isActive();
   }

   public static Map getMapAsMap() {
      HashMap map4 = new HashMap();

      for (Entry entry : (Iterable<Entry>) (Iterable) (map3.entrySet())) {
         ImmutableMultimap immutableMultimap = ImmutableMultimap.copyOf((Multimap)entry.getValue());
         map4.put(entry.getKey(), immutableMultimap);
      }

      return ImmutableMap.copyOf(map4);
   }

   public static void applyText(String text, String text2) {
      if (matchesText(text, text2)) {
         map.put(text, text2);
      }
   }

   public static boolean isActive2() {
      if (!isActive4()) {
         return false;
      } else {
         return hashSet.contains(1) ? enabled3 : isActive5();
      }
   }

   public static boolean matchesAlertNotificationSound2(AlertNotificationSound alertNotificationSound) {
      return matchesAlertNotificationSoundByAlertNotificatio(alertNotificationSound) ? true : isActive();
   }

   public static boolean isActive4() {
      return hashSet.contains(1) ? enabled5 : isActive5();
   }

   public static boolean isActive5() {
      return hashSet.contains(0) ? enabled7 : true;
   }
}
