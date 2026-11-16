package client.util;

public class JsonArrayEntry {
   public static String labelText2;
   public static String labelText;
   public static String labelText3;
   public static boolean enabled2;
   public static boolean enabled4;

   public static void setEnabled2(boolean enabled) {
      enabled2 = enabled;
   }

   public static boolean isEnabled4() {
      return enabled4;
   }

   public static boolean isEnabled2() {
      return enabled2;
   }

   public static void updateState2() {
      if (labelText == null) {
         if (SSLSocketFactoryEntry.matchesText("net.minecraftforge.common.MinecraftForge")) {
            labelText = "Forge";
         } else if (SSLSocketFactoryEntry.matchesText("net.fabricmc.api.ModInitializer")) {
            labelText = "Fabric";
         } else {
            labelText = "Vanilla";
         }
      }

      if (labelText3 == null) {
         for (String text : new String[]{"1_8", "1_16", "1_17", "1_18", "1_19", "1_19_3"}) {
            if (SSLSocketFactoryEntry.matchesText("v" + text + ".morecosmetics.VersionImpl")) {
               labelText3 = text.replace("_", ".");
               break;
            }
         }
      }

      if (labelText2 == null) {
         labelText2 = "Unknown";
      }
   }

   public static void updateState() {
      enabled2 = SSLSocketFactoryEntry.matchesText("BytecodeMethods");
      enabled4 = SSLSocketFactoryEntry.matchesText("de.leonardox.cosmeticsmod.Main");
   }
}
