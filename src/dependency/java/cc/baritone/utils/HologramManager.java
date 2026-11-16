package cc.baritone.utils;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HologramManager {
   public static Logger logger = LogManager.getLogger();
   public static Map map = new HashMap();

   public static List resolveList(int count, char character, Class clazz, int count2) {
      if (clazz == null) {
         throw new IllegalArgumentException("clazz must not be null!");
      } else {
         java.util.ArrayList list = new java.util.ArrayList();

         for (StringEntry stringEntry : (Iterable<StringEntry>)(Iterable<?>)map.values()) {
            if (clazz.isAssignableFrom(stringEntry.specialListener.getClass())) {
               list.add(stringEntry);
            }
         }

         return list;
      }
   }

   public static Map copyOf() {
      return ImmutableMap.copyOf(map);
   }

   public static List resolveList(Class clazz) {
      if (clazz == null) {
         throw new IllegalArgumentException("clazz must not be null!");
      } else {
         java.util.ArrayList list = new java.util.ArrayList();

         for (StringEntry stringEntry : (Iterable<StringEntry>)(Iterable<?>)map.values()) {
            if (stringEntry.isActive() && clazz.isAssignableFrom(stringEntry.specialListener.getClass())) {
               list.add(stringEntry);
            }
         }

         return list;
      }
   }

   public static String resolveText(String text) {
      return !map.containsKey(text) ? null : ((StringEntry)map.get(text)).getText();
   }

   public static void applyBlockPos(BlockPos blockPos, TileEntity tileEntity) {
      if (!DownloadInGeneralManager.checkIndex(blockPos.getX() << 16, blockPos.getZ() << 16)) {
         logger.warn("API attempted to save a tile entity without saving TileEntities being allowed!  Pos: " + blockPos + ", te: " + tileEntity + " (blocked)");
         updateState();
      } else {
         Pokechu22WorldDownloaderManager.applyBlockPos(blockPos, tileEntity);
      }
   }

   private static void updateState() {
      for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
         logger.warn(stackTraceElement.toString());
      }
   }

   public static void applyText(String text, String text2, SpecialListener specialListener) {
      if (text == null) {
         throw new IllegalArgumentException("id must not be null!  (mod=" + specialListener + ", version=" + text2 + ")");
      } else if (text2 == null) {
         throw new IllegalArgumentException("version must not be null!  (mod=" + specialListener + ", id=" + text2 + ")");
      } else if (specialListener == null) {
         throw new IllegalArgumentException("mod must not be null!  (id=" + text + ", version=" + text2 + ")");
      } else {
         StringEntry stringEntry = new StringEntry(text, text2, specialListener, null);
         if (map.containsKey(text)) {
            throw new IllegalArgumentException(
               "A mod by the name of '" + text + "' is already registered by " + map.get(text) + " (tried to register " + stringEntry + " over it)"
            );
         } else if (!specialListener.matchesText("1.8.9a-beta2")) {
            String text3 = specialListener.resolveText("1.8.9a-beta2");
            if (text3 != null) {
               throw new IllegalArgumentException(text3);
            } else {
               throw new IllegalArgumentException(
                  "Environment for " + stringEntry + " is incorrect!  Perhaps it is for a different version of WDL?  You are running " + "1.8.9a-beta2" + "."
               );
            }
         } else {
            map.put(text, stringEntry);
            if (specialListener instanceof InternalListener12Extension) {
               Map map2 = ((InternalListener12Extension)specialListener).getMap();
               StringPair stringPair = new StringPair(stringEntry);

               for (Entry entry : (Iterable<Entry>)(Iterable<?>)map2.entrySet()) {
                  LoggerEntry.applyText((String)entry.getKey(), (AltListener)entry.getValue(), stringPair);
               }
            }
         }
      }
   }

   static {
      logger.info("Loading default WDL extensions");
      applyText("Hologram", "1.0", new SecondaryClientManager());
      applyText("EntityRealigner", "1.0", new Listener12ExtensionAdapter());
   }
}
