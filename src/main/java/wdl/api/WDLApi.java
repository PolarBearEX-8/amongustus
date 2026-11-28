package wdl.api;

import wdl.EntityRealigner;
import wdl.HologramHandler;
import wdl.ModMessageTypeCategory;
import wdl.WDL;
import wdl.WDLMessages;
import wdl.WDLPluginChannels;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WDLApi {
   private static Logger logger = LogManager.getLogger();
   private static Map wdlMods = new HashMap();

   public static List getImplementingExtensions(Class clazz) {
      if (clazz == null) {
         throw new IllegalArgumentException("clazz must not be null!");
      } else {
         ArrayList list = new ArrayList();

         for (ModInfo modInfo : (Iterable<ModInfo>)(wdlMods.values())) {
            if (modInfo.isEnabled() && clazz.isAssignableFrom(modInfo.mod.getClass())) {
               list.add(modInfo);
            }
         }

         return list;
      }
   }

   public static void saveTileEntity(BlockPos pos, long l, TileEntity tileEntity) {
      if (!WDLPluginChannels.canSaveTileEntities(pos.getX() << 16, pos.getZ() << 16)) {
         logger.warn("saveTileEntity called with a blockpos (" + pos + ") that is not in the currently loaded world!  (tile entity: " + tileEntity + ")  Please report this to the mod author.");
         logStackTrace();
      } else {
         WDL.saveTileEntity(pos, tileEntity);
      }
   }

   static {
      logger.info("Loading default WDL extensions");
      addWDLMod("Hologram", "1.0", new HologramHandler());
      addWDLMod("EntityRealigner", "1.0", new EntityRealigner());
   }

   public static void addWDLMod(String str, String str2, IWDLMod wdlMod) {
      if (str == null) {
         throw new IllegalArgumentException("id must not be null!  (mod=" + wdlMod + ", version=" + str2 + ")");
      } else if (str2 == null) {
         throw new IllegalArgumentException("version must not be null!  (mod=" + wdlMod + ", id=" + str2 + ")");
      } else if (wdlMod == null) {
         throw new IllegalArgumentException("mod must not be null!  (id=" + str + ", version=" + str2 + ")");
      } else {
         ModInfo modInfo = new ModInfo(str, str2, wdlMod, null);
         if (wdlMods.containsKey(str)) {
            throw new IllegalArgumentException(
               "A mod by the name of '" + str + "' is already registered by " + wdlMods.get(str) + " (tried to register " + modInfo + " over it)"
            );
         } else if (!wdlMod.isValidEnvironment("1.8.9a-beta2")) {
            String str3 = wdlMod.getEnvironmentErrorMessage("1.8.9a-beta2");
            if (str3 != null) {
               throw new IllegalArgumentException(str3);
            } else {
               throw new IllegalArgumentException(
                  "Environment for " + modInfo + " is incorrect!  Perhaps it is for a different version of WDL?  You are running " + "1.8.9a-beta2" + "."
               );
            }
         } else {
            wdlMods.put(str, modInfo);
            if (wdlMod instanceof IMessageTypeAdder) {
               Map messageTypes = ((IMessageTypeAdder)wdlMod).getMessageTypes();
               ModMessageTypeCategory modMessageTypeCategory = new ModMessageTypeCategory(modInfo);

               for (Entry entry : (Iterable<Entry>)(messageTypes.entrySet())) {
                  WDLMessages.registerMessage((String)entry.getKey(), (IWDLMessageType)entry.getValue(), modMessageTypeCategory);
               }
            }
         }
      }
   }

   public static String getModInfo(String str) {
      return !wdlMods.containsKey(str) ? null : ((ModInfo)wdlMods.get(str)).getInfo();
   }

   public static List getAllImplementingExtensions(long l, Class clazz) {
      if (clazz == null) {
         throw new IllegalArgumentException("clazz must not be null!");
      } else {
         ArrayList list = new ArrayList();

         for (ModInfo modInfo : (Iterable<ModInfo>)(wdlMods.values())) {
            if (clazz.isAssignableFrom(modInfo.mod.getClass())) {
               list.add(modInfo);
            }
         }

         return list;
      }
   }

   public static Map getWDLMods() {
      return ImmutableMap.copyOf(wdlMods);
   }

   private static void logStackTrace() {

      for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
         logger.warn(stackTraceElement.toString());
      }
   }
}
