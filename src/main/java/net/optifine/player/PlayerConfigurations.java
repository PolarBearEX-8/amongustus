package net.optifine.player;

import java.util.HashMap;
import java.util.Map;
import net.optifine.http.FileDownloadThread;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.optifine.http.HttpUtils;

public class PlayerConfigurations {
   private static Map mapConfigurations = null;
   private static boolean reloadPlayerItems = Boolean.getBoolean("player.models.reload");
   private static long timeReloadPlayerItemsMs = System.currentTimeMillis();

   public static synchronized PlayerConfiguration getPlayerConfiguration(AbstractClientPlayer clientPlayer) {
      if (reloadPlayerItems && System.currentTimeMillis() > timeReloadPlayerItemsMs + 5000L) {
         EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
         if (thePlayer != null) {
            setPlayerConfiguration(thePlayer.getNameClear(), (PlayerConfiguration)null);
            timeReloadPlayerItemsMs = System.currentTimeMillis();
         }
      }

      String nameClear = clientPlayer.getNameClear();
      if (nameClear == null) {
         return null;
      } else {
         PlayerConfiguration playerConfiguration = (PlayerConfiguration)getMapConfigurations().get(nameClear);
         if (playerConfiguration == null) {
            playerConfiguration = new PlayerConfiguration();
            getMapConfigurations().put(nameClear, playerConfiguration);
            PlayerConfigurationReceiver receiver = new PlayerConfigurationReceiver(nameClear);
            String str = HttpUtils.getPlayerItemsUrl() + "/users/" + nameClear + ".cfg";
            new FileDownloadThread(str, receiver).start();
         }

         return playerConfiguration;
      }
   }

   public static void renderPlayerItems(ModelBiped modelBiped, AbstractClientPlayer clientPlayer, float f, float f2) {
      PlayerConfiguration playerConfiguration = getPlayerConfiguration(clientPlayer);
      if (playerConfiguration != null) {
         playerConfiguration.renderPlayerItems(modelBiped, clientPlayer, f, f2);
      }
   }

   public static synchronized void setPlayerConfiguration(String str, PlayerConfiguration playerConfiguration) {
      getMapConfigurations().put(str, playerConfiguration);
   }

   private static Map getMapConfigurations() {
      if (mapConfigurations == null) {
         mapConfigurations = new HashMap();
      }

      return mapConfigurations;
   }
}
