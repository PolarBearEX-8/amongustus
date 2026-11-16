package client.util;

import client.setting.ClientSettingsChristmasType;

public class HolidayHelper {
   public static boolean enabled = false;
   public static ClientSettingsChristmasType clientSettingsChristmasType = null;

   public static ClientSettingsChristmasType getClientSettingsChristmasType() {
      if (!enabled) {
         for (ClientSettingsChristmasType clientSettingsChristmasType2 : ClientSettingsChristmasType.values()) {
            if (clientSettingsChristmasType2.enabled) {
               clientSettingsChristmasType = clientSettingsChristmasType2;
               break;
            }
         }

         enabled = true;
      }

      return clientSettingsChristmasType;
   }
}
