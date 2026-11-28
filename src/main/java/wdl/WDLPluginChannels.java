package wdl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WDLPluginChannels {
   private static Logger logger = LogManager.getLogger();
   private static HashSet receivedPackets = new HashSet();
   private static boolean canUseFunctionsUnknownToServer = true;
   private static boolean canDownloadInGeneral = true;
   private static int saveRadius = -1;
   private static boolean canCacheChunks = true;
   private static boolean canSaveEntities = true;
   private static boolean canSaveTileEntities = true;
   private static boolean canSaveContainers = true;
   private static Map entityRanges = new HashMap();
   private static boolean canRequestPermissions = false;
   private static String requestMessage = "";
   private static Map chunkOverrides = new HashMap();
   private static Map requests = new HashMap();
   public static final List BOOLEAN_REQUEST_FIELDS = Arrays.asList("downloadInGeneral", "cacheChunks", "saveEntities", "saveTileEntities", "saveContainers", "getEntityRanges");
   public static final List INTEGER_REQUEST_FIELDS = Arrays.asList("saveRadius");
   private static List chunkOverrideRequests = new ArrayList();

   public static boolean canRequestPermissions() {
      return receivedPackets.contains(3) && canRequestPermissions;
   }

   public static boolean hasServerEntityRange() {
      return receivedPackets.contains(2) && entityRanges.size() > 0;
   }

   public static boolean canSaveEntities(int n, int n2) {
      return isChunkOverridden(n, n2) ? true : canSaveEntities();
   }

   public static Map getRequests() {
      return ImmutableMap.copyOf(requests);
   }

   public static String getRequestMessage() {
      return receivedPackets.contains(3) ? requestMessage : null;
   }

   public static void onPluginChannelPacket(String str, byte[] bytes) {
      if ("WDL|CONTROL".equals(str)) {
         ByteArrayDataInput byteArrayDataInput = ByteStreams.newDataInput(bytes);
         int n = byteArrayDataInput.readInt();
         receivedPackets.add(n);
         switch (n) {
            case 0:
               canUseFunctionsUnknownToServer = byteArrayDataInput.readBoolean();
               WDLMessages.chatMessageTranslated(WDLMessageTypes.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet0", new Object[]{canUseFunctionsUnknownToServer});
               break;
            case 1:
               canDownloadInGeneral = byteArrayDataInput.readBoolean();
               saveRadius = byteArrayDataInput.readInt();
               canCacheChunks = byteArrayDataInput.readBoolean();
               canSaveEntities = byteArrayDataInput.readBoolean();
               canSaveTileEntities = byteArrayDataInput.readBoolean();
               canSaveContainers = byteArrayDataInput.readBoolean();
               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.PLUGIN_CHANNEL_MESSAGE,
                  "wdl.messages.permissions.packet1",
                  new Object[]{canDownloadInGeneral, saveRadius, canCacheChunks, canSaveEntities, canSaveTileEntities, canSaveContainers}
               );
               if (!canDownloadInGeneral && WDL.downloading) {
                  WDLMessages.chatMessageTranslated(WDLMessageTypes.ERROR, "wdl.messages.generalError.forbidden", new Object[0]);
                  WDL.cancelDownload();
               }
               break;
            case 2:
               entityRanges.clear();
               int n2 = byteArrayDataInput.readInt();

               for (int i = 0; i < n2; i++) {
                  String str2 = byteArrayDataInput.readUTF();
                  int n3 = byteArrayDataInput.readInt();
                  entityRanges.put(str2, n3);
               }

               WDLMessages.chatMessageTranslated(WDLMessageTypes.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet2", new Object[]{entityRanges.size()});
               break;
            case 3:
               canRequestPermissions = byteArrayDataInput.readBoolean();
               requestMessage = byteArrayDataInput.readUTF();
               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.PLUGIN_CHANNEL_MESSAGE,
                  "wdl.messages.permissions.packet3",
                  new Object[]{canRequestPermissions, requestMessage.length(), Integer.toHexString(requestMessage.hashCode())}
               );
               break;
            case 4:
               chunkOverrides.clear();
               int n4 = byteArrayDataInput.readInt();
               int n5 = 0;

               for (int j = 0; j < n4; j++) {
                  String str3 = byteArrayDataInput.readUTF();
                  int n6 = byteArrayDataInput.readInt();
                  HashMultimap hashMultimap = HashMultimap.create();

                  for (int k = 0; k < n6; k++) {
                     ChunkRange chunkRange = ChunkRange.readFromInput(byteArrayDataInput);
                     hashMultimap.put(chunkRange.tag, chunkRange);
                  }

                  chunkOverrides.put(str3, hashMultimap);
                  n5 += n6;
               }

               WDLMessages.chatMessageTranslated(WDLMessageTypes.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet4", new Object[]{n4, n5});
               break;
            case 5:
               String str4 = byteArrayDataInput.readUTF();
               boolean flag = byteArrayDataInput.readBoolean();
               int n7 = byteArrayDataInput.readInt();
               HashMultimap hashMultimap2 = HashMultimap.create();
               if (!flag) {
                  hashMultimap2.putAll((Multimap)chunkOverrides.get(str4));
               }

               for (int m = 0; m < n7; m++) {
                  ChunkRange chunkRange2 = ChunkRange.readFromInput(byteArrayDataInput);
                  hashMultimap2.put(chunkRange2.tag, chunkRange2);
               }

               chunkOverrides.put(str4, hashMultimap2);
               if (flag) {
                  WDLMessages.chatMessageTranslated(
                     WDLMessageTypes.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet5.set", new Object[]{n7, str4}
                  );
               } else {
                  WDLMessages.chatMessageTranslated(
                     WDLMessageTypes.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet5.added", new Object[]{n7, str4}
                  );
               }
               break;
            case 6:
               String str5 = byteArrayDataInput.readUTF();
               int n8 = byteArrayDataInput.readInt();
               String[] strings = new String[n8];

               for (int idx = 0; idx < n8; idx++) {
                  strings[idx] = byteArrayDataInput.readUTF();
               }

               int n9 = 0;

               for (String str6 : strings) {
                  n9 += ((Multimap)chunkOverrides.get(str5)).get(str6).size();
                  ((Multimap)chunkOverrides.get(str5)).removeAll(str6);
               }

               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.PLUGIN_CHANNEL_MESSAGE,
                  "wdl.messages.permissions.packet6",
                  new Object[]{n9, str5, Arrays.toString((Object[])strings)}
               );
               break;
            case 7:
               String str7 = byteArrayDataInput.readUTF();
               String str8 = byteArrayDataInput.readUTF();
               int n10 = byteArrayDataInput.readInt();
               int size = ((Multimap)chunkOverrides.get(str7)).removeAll(str8).size();

               for (int index = 0; index < n10; index++) {
                  ((Multimap)chunkOverrides.get(str7)).put(str8, ChunkRange.readFromInput(byteArrayDataInput));
               }

               WDLMessages.chatMessageTranslated(
                  WDLMessageTypes.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.packet7", new Object[]{size, str7, str8, n10}
               );
               break;
            default:
               WDLMessages.chatMessageTranslated(WDLMessageTypes.PLUGIN_CHANNEL_MESSAGE, "wdl.messages.permissions.unknownPacket", new Object[]{n});
               StringBuilder sb = new StringBuilder();

               for (byte b : bytes) {
                  sb.append((int)b).append(' ');
               }

               logger.info(sb.toString());
         }
      }
   }

   public static Map getChunkOverrides() {
      HashMap map = new HashMap();

      for (Entry entry : (Iterable<Entry>)(chunkOverrides.entrySet())) {
         ImmutableMultimap immutableMultimap = ImmutableMultimap.copyOf((Multimap)entry.getValue());
         map.put(entry.getKey(), immutableMultimap);
      }

      return ImmutableMap.copyOf(map);
   }

   public static List getChunkOverrideRequests() {
      return ImmutableList.copyOf(chunkOverrideRequests);
   }

   public static boolean isValidRequest(String str, String str2) {
      if (str == null || str2 == null) {
         return false;
      } else if (BOOLEAN_REQUEST_FIELDS.contains(str)) {
         return str2.equals("true") || str2.equals("false");
      } else if (INTEGER_REQUEST_FIELDS.contains(str)) {
         try {
            Integer.parseInt(str2);
            return true;
         } catch (NumberFormatException e) {
            return false;
         }
      } else {
         return false;
      }
   }

   public static int getSaveRadius() {
      return saveRadius;
   }

   public static boolean canDownloadInGeneral() {
      return receivedPackets.contains(1) ? canDownloadInGeneral : canUseFunctionsUnknownToServer();
   }

   public static boolean canDownloadAtAll() {
      return hasChunkOverrides() ? true : canDownloadInGeneral();
   }

   public static void onWorldLoad() {
      Minecraft minecraft = Minecraft.getMinecraft();
      receivedPackets = new HashSet();
      requests = new HashMap();
      chunkOverrideRequests = new ArrayList();
      canUseFunctionsUnknownToServer = true;
   }

   public static boolean canSaveEntities(Chunk chunk) {
      return isChunkOverridden(chunk) ? true : canSaveEntities();
   }

   public static boolean canSaveTileEntities(Chunk chunk) {
      return isChunkOverridden(chunk) ? true : canSaveTileEntities();
   }

   public static boolean isChunkOverridden(int n, int n2) {
      for (Multimap multimap : (Iterable<Multimap>)(chunkOverrides.values())) {
         for (ChunkRange chunkRange : (Iterable<ChunkRange>)(multimap.values())) {
            if (n >= chunkRange.x1 && n <= chunkRange.x2 && n2 >= chunkRange.z1 && n2 <= chunkRange.z2) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean canSaveTileEntities(int n, int n2) {
      return isChunkOverridden(n, n2) ? true : canSaveTileEntities();
   }

   public static void addRequest(String str, String str2) {
      if (isValidRequest(str, str2)) {
         requests.put(str, str2);
      }
   }

   public static int getEntityRange(String str) {
      if (!canSaveEntities((Chunk)null)) {
         return -1;
      } else if (receivedPackets.contains(2)) {
         return entityRanges.containsKey(str) ? (Integer)entityRanges.get(str) : -1;
      } else {
         return -1;
      }
   }

   public static boolean canCacheChunks() {
      return canCacheChunks;
   }

   public static Map getEntityRanges() {
      return new HashMap(entityRanges);
   }

   public static boolean canSaveMaps() {
      if (!canDownloadInGeneral()) {
         return false;
      } else {
         return receivedPackets.contains(1) ? canSaveTileEntities : canUseFunctionsUnknownToServer();
      }
   }

   public static boolean hasChunkOverrides() {
      if (!receivedPackets.contains(4)) {
         return false;
      } else if (chunkOverrides != null && !chunkOverrides.isEmpty()) {
         for (Multimap multimap : (Iterable<Multimap>)(chunkOverrides.values())) {
            if (!multimap.isEmpty()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static void sendRequests() {
      if (!requests.isEmpty() || !chunkOverrideRequests.isEmpty()) {
         ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
         byteArrayDataOutput.writeUTF("REQUEST REASON WILL GO HERE");
         byteArrayDataOutput.writeInt(requests.size());

         for (Entry entry : (Iterable<Entry>)(requests.entrySet())) {
            byteArrayDataOutput.writeUTF((String)entry.getKey());
            byteArrayDataOutput.writeUTF((String)entry.getValue());
         }

         byteArrayDataOutput.writeInt(chunkOverrideRequests.size());

         for (ChunkRange chunkRange : (Iterable<ChunkRange>)(chunkOverrideRequests)) {
            chunkRange.writeToOutput(byteArrayDataOutput);
         }
      }
   }

   public static boolean canSaveContainers() {
      if (!canDownloadInGeneral()) {
         return false;
      } else if (!canSaveTileEntities()) {
         return false;
      } else {
         return receivedPackets.contains(1) ? canSaveContainers : canUseFunctionsUnknownToServer();
      }
   }

   public static boolean canSaveEntities() {
      if (!canDownloadInGeneral()) {
         return false;
      } else {
         return receivedPackets.contains(1) ? canSaveEntities : canUseFunctionsUnknownToServer();
      }
   }

   public static boolean isChunkOverridden(Chunk chunk) {
      return chunk == null ? false : isChunkOverridden(chunk.xPosition, chunk.zPosition);
   }

   public static boolean hasPermissions() {
      return receivedPackets != null && !receivedPackets.isEmpty();
   }

   public static boolean canSaveTileEntities() {
      if (!canDownloadInGeneral()) {
         return false;
      } else {
         return receivedPackets.contains(1) ? canSaveTileEntities : canUseFunctionsUnknownToServer();
      }
   }

   public static boolean canUseFunctionsUnknownToServer() {
      return receivedPackets.contains(0) ? canUseFunctionsUnknownToServer : true;
   }

   public static boolean canSaveContainers(Chunk chunk) {
      return isChunkOverridden(chunk) ? true : canSaveContainers();
   }

   public static boolean canSaveChunk(Chunk chunk) {
      if (isChunkOverridden(chunk)) {
         return true;
      } else if (!canDownloadInGeneral()) {
         return false;
      } else if (!receivedPackets.contains(1)) {
         return canUseFunctionsUnknownToServer();
      } else {
         if (!canCacheChunks && saveRadius >= 0) {
            int n = chunk.xPosition - WDL.thePlayer.chunkCoordX;
            int n2 = chunk.zPosition - WDL.thePlayer.chunkCoordZ;
            if (Math.abs(n) > saveRadius || Math.abs(n2) > saveRadius) {
               return false;
            }
         }

         return true;
      }
   }

   public static void addChunkOverrideRequest(ChunkRange chunkRange) {
      chunkOverrideRequests.add(chunkRange);
   }

   public static boolean canSaveContainers(int n, int n2) {
      return isChunkOverridden(n, n2) ? true : canSaveContainers();
   }
}
