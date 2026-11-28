package net.augustus.utils;

import java.io.File;

public enum OSUtil {
   MAC("Library" + File.separator + "Application Support" + File.separator + "minecraft"),
   LINUX(".minecraft"),
   WINDOWS("AppData" + File.separator + "Roaming" + File.separator + ".minecraft");

   private final String minecraftPathSuffix;

   public String getMinecraftDirectory() {
      return System.getProperty("user.home") + this.minecraftPathSuffix;
   }

   private OSUtil(String str) {
      this.minecraftPathSuffix = File.separator + str + File.separator;
   }


   public static final OSUtil detectOS() {
      String str = System.getProperty("os.name").toLowerCase();
      if (str.startsWith("windows")) {
         return WINDOWS;
      } else {
         return str.startsWith("mac") ? MAC : LINUX;
      }
   }



}
