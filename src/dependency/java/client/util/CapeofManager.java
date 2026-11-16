package client.util;

import client.event.BridgeListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class CapeofManager {
   public static final Pattern pattern = Pattern.compile("[a-zA-Z0-9_]+");

   public static void applyAbstractClientPlayer2(AbstractClientPlayer player) {
      String labelText = player.getLabelText();
      ResourceLocation location = new ResourceLocation("capeof/" + labelText);
      TextureManager textureManagerAsTextureManager = BuildManager.getTextureManagerAsTextureManager();
      BridgeListener bridgeListener = textureManagerAsTextureManager.get(location);
      if (bridgeListener instanceof DefaultLoggerEntry) {
         ((DefaultLoggerEntry)bridgeListener).updateState3();
         textureManagerAsTextureManager.applyResourceLocation2(location);
      }

      player.setResourceLocation((ResourceLocation)null);
      player.setEnabled25(false);
      applyAbstractClientPlayer(player);
   }

   public static void applyAbstractClientPlayer(AbstractClientPlayer player) {
      String labelText = player.getLabelText();
      if (labelText != null && !labelText.isEmpty() && !labelText.contains("\u0000") && pattern.matcher(labelText).matches()) {
         String text = "http://s.optifine.net/capes/" + labelText + ".png";
         ResourceLocation location = new ResourceLocation("capeof/" + labelText);
         TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
         BridgeListener bridgeListener = textureManager.get(location);
         if (bridgeListener != null && bridgeListener instanceof LoggerEntryHandler) {
            LoggerEntryHandler loggerEntryHandler = (LoggerEntryHandler)bridgeListener;
            if (loggerEntryHandler.memberRef != null) {
               if (loggerEntryHandler.memberRef) {
                  player.setResourceLocation(location);
                  if (loggerEntryHandler.getPreRenderBroadcaster() instanceof AbstractClientPlayerEntry) {
                     AbstractClientPlayerEntry preRenderBroadcaster = (AbstractClientPlayerEntry)loggerEntryHandler.getPreRenderBroadcaster();
                     player.setEnabled25(preRenderBroadcaster.isEnabled());
                  }
               }

               return;
            }
         }

         AbstractClientPlayerEntry abstractClientPlayerEntry = new AbstractClientPlayerEntry(player, location);
         LoggerEntryHandler loggerEntryHandler2 = new LoggerEntryHandler((File)null, text, (ResourceLocation)null, abstractClientPlayerEntry);
         loggerEntryHandler2.enabled7 = true;
         textureManager.matchesResourceLocation(location, loggerEntryHandler2);
      }
   }

   public static BufferedImage getBufferedImage(BufferedImage bufferedImage) {
      int imageWidth = 64;
      int imageHeight = 32;
      int width = bufferedImage.getWidth();

      for (int height = bufferedImage.getHeight(); imageWidth < width || imageHeight < height; imageHeight *= 2) {
         imageWidth *= 2;
      }

      BufferedImage bufferedImage2 = new BufferedImage(imageWidth, imageHeight, 2);
      Graphics graphics = bufferedImage2.getGraphics();
      graphics.drawImage(bufferedImage, 0, 0, (ImageObserver)null);
      graphics.dispose();
      return bufferedImage2;
   }

   public static boolean matchesBufferedImage(BufferedImage bufferedImage, BufferedImage bufferedImage2) {
      return bufferedImage.getWidth() > bufferedImage2.getHeight();
   }
}
