package net.augustus.utils;

import net.augustus.ui.account.AccountEntry;
import net.augustus.ui.account.AccountManagerScreen;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;

public class MinotarAvatarCache {
   public static final Map headCache = new HashMap();
   public static final Map skinCache = new HashMap();
   public static final Map avatarCache = new HashMap();

   public static ResourceLocation getAvatar(String str, int n) {
      ResourceLocation location = (ResourceLocation)avatarCache.get(str);
      if (location != null) {
         return location;
      } else {
         String str2 = "https://minotar.net/avatar/" + str + "/" + n + ".png";
         ResourceLocation location2 = new ResourceLocation("skins/" + str);
         ThreadDownloadImageData threadDownloadImageData = new ThreadDownloadImageData((File)null, str2, (ResourceLocation)null, (IImageBuffer)null);
         Minecraft.getMinecraft().getTextureManager().loadTexture(location2, threadDownloadImageData);
         avatarCache.put(str, location2);
         AbstractClientPlayer.getDownloadImageSkin(location2, str);
         return location2;
      }
   }

   public static void preloadAccountAvatars() {
      for (Object obj : AccountManagerScreen.accounts) {
         AccountEntry accountEntry = (AccountEntry)obj;
         ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

         try {
            getAvatar(accountEntry.getUsername(), accountEntry.getRenderHeight() * sr.getScaleFactor());
         } catch (Exception e) {
            System.err.println("Failed to load Skins!");
         }
      }
   }
}
