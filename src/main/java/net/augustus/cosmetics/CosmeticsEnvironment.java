package net.augustus.cosmetics;

import com.google.gson.JsonArray;

public class CosmeticsEnvironment {
   private static boolean forge;
   public static String minecraftVersion;
   private static boolean obfuscatedRuntime;
   private static boolean standaloneMod;
   private static boolean fabric;
   public static String modLoader;
   public static String clientBrand;
   private static boolean onlineMode = true;
   private static JsonArray loadedModules;

   public static void setObfuscatedRuntime(boolean flag) {
      obfuscatedRuntime = flag;
   }

   public static void setForge(boolean flag) {
      forge = flag;
   }

   public static void detectEnvironment() {
      if (modLoader == null) {
         if (CosmeticsHttpUtil.isClassPresent("net.minecraftforge.common.MinecraftForge")) {
            modLoader = "Forge";
            setForge(true);
         } else {
            if (CosmeticsHttpUtil.isClassPresent("net.fabricmc.api.ModInitializer")) {
               modLoader = "Fabric";
               setFabric(true);
            } else {
               modLoader = "Vanilla";
            }
         }
      }

      if (minecraftVersion == null) {
         for (String str : new String[]{"1_8", "1_16", "1_17", "1_18", "1_19", "1_19_3"}) {
            if (CosmeticsHttpUtil.isClassPresent("v" + str + ".morecosmetics.VersionImpl")) {
               minecraftVersion = str.replace("_", ".");
               break;
            }
         }
      }

      if (clientBrand == null) {
         clientBrand = "Unknown";
      }
   }

   public static void setFabric(boolean flag) {
      fabric = flag;
   }

   public static boolean isOnlineMode() {
      return onlineMode;
   }

   public static JsonArray getLoadedModules() {
      return loadedModules;
   }

   public static void setLoadedModules(JsonArray array) {
      loadedModules = array;
   }

   public static void setOnlineMode(boolean flag) {
      onlineMode = flag;
   }

   public static void detectRuntimeFlags() {
      obfuscatedRuntime = CosmeticsHttpUtil.isClassPresent("BytecodeMethods");
      standaloneMod = CosmeticsHttpUtil.isClassPresent("de.leonardox.cosmeticsmod.Main");
   }

   public static boolean isStandaloneMod() {
      return standaloneMod;
   }

   public static boolean isForge() {
      return forge;
   }

   public static boolean isFabric() {
      return fabric;
   }

   public static boolean isObfuscatedRuntime() {
      return obfuscatedRuntime;
   }
}
