package net.augustus.cosmetics.render;

import net.augustus.cosmetics.CosmeticsHttpUtil;
import net.augustus.cosmetics.MoreCosmeticsCore;
import net.augustus.cosmetics.TrustedHostWhitelist;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;

public class CosmeticTextureManager {
   private static CosmeticTextureManager cosmeticTextureManager;
   private HashMap resourceLocations;
   private HashMap staticTextures;
   private String namespace;
   private HashMap animatedTextures;

   private void downloadGifTask(String str, String str2, BufferedImage image, CosmeticImageProcessor cosmeticImageProcessor) {

      try {
         this.registerGifFrames(str2, CosmeticGifDecoder.decodeGifFrames(CosmeticsHttpUtil.openUrlStream(str, (short)5000, new Object[0])), image, cosmeticImageProcessor);
      } catch (Exception e) {
         MoreCosmeticsCore.logInfo("Failed to load " + str + ": " + e.toString());
      }
   }

   private void readLocalImageTask(String str, ResourceLocation location, CosmeticImageProcessor cosmeticImageProcessor, File file, BufferedImage image, String str2) {

      try {
         this.registerStaticTexture(str, location, cosmeticImageProcessor.processImage(ImageIO.read(file)), image);
      } catch (IOException e) {
         MoreCosmeticsCore.logInfo("Failed to load " + str2 + ": " + e.toString());
      }
   }

   public ResourceLocation getTextureLocation(String str, String str2) {
      CosmeticBufferedTexture orLoadTexture = this.getOrLoadTexture(str, str2, (BufferedImage)null);
      return orLoadTexture != null ? orLoadTexture.getTextureLocation() : null;
   }

   public static CosmeticTextureManager getInstance() {
      return cosmeticTextureManager != null ? cosmeticTextureManager : (cosmeticTextureManager = new CosmeticTextureManager("morecosmetics"));
   }

   public void removeTexture(String str) {
      this.staticTextures.remove(str);
      this.animatedTextures.remove(str);
      this.resourceLocations.remove(str);
   }

   private void loadLocalImage(String str, String str2, BufferedImage image, CosmeticImageProcessor cosmeticImageProcessor) {
      File file = new File(str2);
      if (!file.exists()) {
         MoreCosmeticsCore.logInfo("Failed to load file: " + str2);
      } else {
         ResourceLocation location = new ResourceLocation(this.namespace + "/" + str.hashCode());
         MoreCosmeticsCore.EXECUTOR.execute(() -> this.readLocalImageTask(str, location, cosmeticImageProcessor, file, image, str2));
      }
   }

   public AnimatedCosmeticTexture getAnimatedTexture(String str) {
      return (AnimatedCosmeticTexture)this.animatedTextures.get(str);
   }

   private void loadLocalGif(String str, String str2, BufferedImage image, CosmeticImageProcessor cosmeticImageProcessor) {
      File file = new File(str2);
      if (!file.exists()) {
         MoreCosmeticsCore.logInfo("File not found: " + str2);
      } else {
         MoreCosmeticsCore.EXECUTOR.execute(() -> {

            try {
               this.registerGifFrames(str, CosmeticGifDecoder.decodeGifFrames(FileUtils.openInputStream(file)), image, cosmeticImageProcessor);
            } catch (IOException e) {
               MoreCosmeticsCore.logInfo("Failed to load " + str2 + ": " + e.toString());
            }
         });
      }
   }

   public CosmeticTextureManager(String str) {
      this.staticTextures = new HashMap();
      this.animatedTextures = new HashMap();
      this.resourceLocations = new HashMap();
      this.namespace = str;
   }

   private void downloadImageTask(String str, String str2, ResourceLocation location, CosmeticImageProcessor cosmeticImageProcessor, BufferedImage image) {

      try {
         this.registerStaticTexture(str2, location, cosmeticImageProcessor.processImage(ImageIO.read(CosmeticsHttpUtil.openUrlStream(str, (short)5000, new Object[0]))), TrustedHostWhitelist.isOfficialHost(str) ? null : image);
      } catch (Exception e) {
         MoreCosmeticsCore.logInfo("Failed to load " + str + ": " + e.toString());
      }
   }

   public CosmeticBufferedTexture getOrLoadTexture(String str, String str2, BufferedImage image) {
      return this.getOrLoadTexture(str, str2, image, CosmeticImageProcessor.IDENTITY);
   }

   public CosmeticBufferedTexture getOrLoadTexture(String str, String str2, BufferedImage image, CosmeticImageProcessor cosmeticImageProcessor) {
      if (this.animatedTextures.containsKey(str)) {
         return ((AnimatedCosmeticTexture)this.animatedTextures.get(str)).getCurrentFrame();
      } else if (this.staticTextures.containsKey(str)) {
         return (CosmeticBufferedTexture)this.staticTextures.get(str);
      } else {
         this.staticTextures.put(str, null);
         String str3 = str2.toLowerCase();
         boolean flag = str3.startsWith("http");
         boolean flag2 = str3.contains(".jpeg") || str3.contains(".jpg");
         if (!str3.contains(".png") && !flag2) {
            if (str3.contains(".gif")) {
               if (flag) {
                  this.loadRemoteGif(str, str2, image, cosmeticImageProcessor);
               } else {
                  this.loadLocalGif(str, str2, image, cosmeticImageProcessor);
               }
            } else if (flag) {
               this.loadRemoteAutoDetect(str, str2, image, cosmeticImageProcessor);
            }
         } else if (flag) {
            this.loadRemoteImage(str, str2, image, cosmeticImageProcessor);
         } else {
            this.loadLocalImage(str, str2, image, cosmeticImageProcessor);
         }

         return null;
      }
   }

   private void registerGifFrames(String str, CosmeticGifFrame[] cosmeticGifFrames, BufferedImage image, CosmeticImageProcessor cosmeticImageProcessor) {
      int length = cosmeticGifFrames.length;
      int[] ints = new int[length];
      CosmeticBufferedTexture[] cosmeticBufferedTextures = new CosmeticBufferedTexture[length];
      CosmeticTextureAlphaMask cosmeticTextureAlphaMask = null;
      String str2 = this.namespace + "/gif/" + str.hashCode() + "_";

      for (int i = 0; i < length; i++) {
         CosmeticGifFrame cosmeticGifFrame = cosmeticGifFrames[i];
         BufferedImage image3 = cosmeticImageProcessor.processImage(cosmeticGifFrame.getImage());
         if (i == 0 && image != null) {
            cosmeticTextureAlphaMask = new CosmeticTextureAlphaMask(image, image3.getWidth(), image3.getHeight());
         }

         int delay = cosmeticGifFrame.getDelay();
         ints[i] = delay == 0 ? 50 : delay * 10;
         ResourceLocation location = new ResourceLocation(str2 + i);
         image3 = cosmeticTextureAlphaMask == null ? image3 : cosmeticTextureAlphaMask.applyMask(image3);
         CosmeticBufferedTexture cosmeticBufferedTexture = cosmeticBufferedTextures[i] = new CosmeticBufferedTexture(location, image3);
         if (image != null) {
            cosmeticBufferedTexture.setAspectRatio((float)image.getWidth() / (float)image.getHeight());
         }

         Minecraft.getMinecraft().getTextureManager().loadTexture(location, cosmeticBufferedTexture);
      }

      this.animatedTextures.put(str, new AnimatedCosmeticTexture(cosmeticBufferedTextures, ints));
   }

   private void loadRemoteAutoDetect(String str, String str2, BufferedImage image, CosmeticImageProcessor cosmeticImageProcessor) {
      ResourceLocation location = new ResourceLocation(this.namespace + "/" + str.hashCode());
      MoreCosmeticsCore.EXECUTOR.execute(() -> {

         try {
            String[] strings = new String[1];
            InputStream in = CosmeticsHttpUtil.openUrlStream(str2, (short)5000, strings, (Object[])null);
            if (strings[0].contains("jpeg") || strings[0].contains("png")) {
               this.registerStaticTexture(str, location, cosmeticImageProcessor.processImage(ImageIO.read(in)), image);
            } else if (strings[0].contains("gif")) {
               this.registerGifFrames(str, CosmeticGifDecoder.decodeGifFrames(in), image, cosmeticImageProcessor);
            }
         } catch (Exception e) {
            MoreCosmeticsCore.logInfo("Failed to load " + str2 + ": " + e.toString());
         }
      });
   }

   public ResourceLocation getOrCreateResourceLocation(String str, String str2) {
      ResourceLocation location = (ResourceLocation)this.resourceLocations.get(str);
      ResourceLocation location2 = location;
      if (null != null) {
         if (location == null) {
            this.resourceLocations.put(str, location = new ResourceLocation(str2));
         }

         location2 = location;
      }

      return location2;
   }

   private void loadRemoteImage(String str, String str2, BufferedImage image, CosmeticImageProcessor cosmeticImageProcessor) {
      ResourceLocation location = new ResourceLocation(this.namespace + "/" + str.hashCode());
      MoreCosmeticsCore.EXECUTOR.execute(() -> this.downloadImageTask(str2, str, location, cosmeticImageProcessor, image));
   }

   private void loadRemoteGif(String str, String str2, BufferedImage image, CosmeticImageProcessor cosmeticImageProcessor) {
      MoreCosmeticsCore.EXECUTOR.execute(() -> this.downloadGifTask(str2, str, image, cosmeticImageProcessor));
   }

   public void clearAll() {
      this.staticTextures.clear();
      this.animatedTextures.clear();
      this.resourceLocations.clear();
   }

   private void registerStaticTexture(String str, ResourceLocation location, BufferedImage image, BufferedImage image2) {
      if (image2 != null) {
         image = new CosmeticTextureAlphaMask(image2, image.getWidth(), image.getHeight(), true).applyMask(image);
      }

      CosmeticBufferedTexture cosmeticBufferedTexture = new CosmeticBufferedTexture(location, image);
      if (image2 != null) {
         cosmeticBufferedTexture.setAspectRatio((float)image2.getWidth() / (float)image2.getHeight());
      }

      this.staticTextures.put(str, cosmeticBufferedTexture);
      Minecraft.getMinecraft().getTextureManager().loadTexture(location, cosmeticBufferedTexture);
   }

}
