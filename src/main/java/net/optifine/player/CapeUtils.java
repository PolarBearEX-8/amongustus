package net.optifine.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;

public class CapeUtils {
   private static final Pattern PATTERN_USERNAME = Pattern.compile("[a-zA-Z0-9_]+");

   public static void reloadCape(AbstractClientPlayer clientPlayer) {
      String nameClear = clientPlayer.getNameClear();
      ResourceLocation location = new ResourceLocation("capeof/" + nameClear);
      TextureManager textureManager = Config.getTextureManager();
      ITextureObject texture = textureManager.getTexture(location);
      if (texture instanceof SimpleTexture) {
         ((SimpleTexture)texture).deleteGlTexture();
         textureManager.deleteTexture(location);
      }

      clientPlayer.setLocationOfCape((ResourceLocation)null);
      clientPlayer.setElytraOfCape(false);
      downloadCape(clientPlayer);
   }

   public static void downloadCape(AbstractClientPlayer clientPlayer) {
      String nameClear = clientPlayer.getNameClear();
      if (nameClear != null && !nameClear.isEmpty() && !nameClear.contains("\u0000") && PATTERN_USERNAME.matcher(nameClear).matches()) {
         String str = "http://s.optifine.net/capes/" + nameClear + ".png";
         ResourceLocation location = new ResourceLocation("capeof/" + nameClear);
         TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
         ITextureObject texture = textureManager.getTexture(location);
         if (texture != null && texture instanceof ThreadDownloadImageData) {
            ThreadDownloadImageData threadDownloadImageData = (ThreadDownloadImageData)texture;
            if (threadDownloadImageData.imageFound != null) {
               if (threadDownloadImageData.imageFound) {
                  clientPlayer.setLocationOfCape(location);
                  if (threadDownloadImageData.getImageBuffer() instanceof CapeImageBuffer) {
                     clientPlayer.setElytraOfCape(((CapeImageBuffer)threadDownloadImageData.getImageBuffer()).isElytraOfCape());
                  }
               }

               return;
            }
         }

         CapeImageBuffer capeImageBuffer = new CapeImageBuffer(clientPlayer, location);
         ThreadDownloadImageData threadDownloadImageData2 = new ThreadDownloadImageData((File)null, str, (ResourceLocation)null, capeImageBuffer);
         threadDownloadImageData2.pipeline = true;
         textureManager.loadTexture(location, threadDownloadImageData2);
      }
   }

   public static BufferedImage parseCape(BufferedImage image) {
      int n = 64;
      int n2 = 32;
      int width = image.getWidth();

      for (int i = image.getHeight(); n < width || n2 < i; n2 *= 2) {
         n *= 2;
      }

      BufferedImage image2 = new BufferedImage(n, n2, 2);
      Graphics graphics = image2.getGraphics();
      graphics.drawImage(image, 0, 0, (ImageObserver)null);
      graphics.dispose();
      return image2;
   }

   public static boolean isElytraCape(BufferedImage image, BufferedImage image2) {
      return image.getWidth() > image2.getHeight();
   }
}
