package client.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.item.CommonItemArrayEntry;
import net.minecraft.item.ExtendedItemCallback;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.GifLogicalScreenDHelper;

public class JpegManager {
   public HashMap hashMap = new HashMap();
   public HashMap hashMap2 = new HashMap();
   public String labelText;
   public HashMap hashMap3 = new HashMap();
   public static JpegManager jpegManager;

   private void applyText3(String text, String text2, BufferedImage bufferedImage, ExtendedItemCallback extendedItemCallback) {
      File file = new File(text2);
      if (!file.exists()) {
         MoreCosmeticsManager.applyText("File not found: " + text2);
      } else {
         MoreCosmeticsManager.executorService.execute(() -> this.applyText(text, file, bufferedImage, extendedItemCallback, text2));
      }
   }

   public CommonItemArrayEntry get(String text) {
      return (CommonItemArrayEntry)this.hashMap2.get(text);
   }

   private void applyText(String text, String text2, BufferedImage bufferedImage, ExtendedItemCallback extendedItemCallback, ResourceLocation location) {
      try {
         String[] textArray = new String[1];
         Object[] entryArray = null;
         short shortValue = 5000;
         InputStream inputStream = SSLSocketFactoryEntry.resolveInputStream(text, shortValue, textArray, entryArray);
         if (textArray[0].contains("jpeg") || textArray[0].contains("png")) {
            BufferedImage bufferedImage2 = ImageIO.read(inputStream);
            BufferedImage bufferedImage3 = extendedItemCallback.resolveBufferedImage(bufferedImage2);
            this.applyTextByText(text2, location, bufferedImage3, bufferedImage);
         } else if (textArray[0].contains("gif")) {
            BufferedImageEntry[] bufferedImageEntryArray = GifLogicalScreenDHelper.resolveBufferedImageEntryArray(inputStream);
            this.applyText(text2, bufferedImageEntryArray, bufferedImage, extendedItemCallback);
         }
      } catch (Exception exception) {
         MoreCosmeticsManager.applyText("Failed to load " + text + ": " + exception.toString());
      }
   }

   public void applyText(String text) {
      this.hashMap.remove(text);
      this.hashMap2.remove(text);
      this.hashMap3.remove(text);
   }

   private void applyText(String text, File file, BufferedImage bufferedImage, ExtendedItemCallback extendedItemCallback, String text2) {
      try {
         BufferedImageEntry[] bufferedImageEntryArray = GifLogicalScreenDHelper.resolveBufferedImageEntryArray(FileUtils.openInputStream(file));
         this.applyText(text, bufferedImageEntryArray, bufferedImage, extendedItemCallback);
      } catch (IOException ioException) {
         MoreCosmeticsManager.applyText("Failed to load " + text2 + ": " + ioException.toString());
      }
   }

   private void applyText(String text, String text2, ResourceLocation location, ExtendedItemCallback extendedItemCallback, BufferedImage bufferedImage) {
      try {
         Object[] entryArray = new Object[0];
         short shortValue = 5000;
         InputStream inputStream = SSLSocketFactoryEntry.resolveInputStream(text, shortValue, entryArray);
         BufferedImage bufferedImage2 = ImageIO.read(inputStream);
         BufferedImage bufferedImage3 = extendedItemCallback.resolveBufferedImage(bufferedImage2);
         BufferedImage bufferedImage4 = CollectionHelper.matchesTextByText(text) ? null : bufferedImage;
         BufferedImage bufferedImage5 = bufferedImage3;
         this.applyTextByText(text2, location, bufferedImage5, bufferedImage4);
      } catch (Exception exception) {
         MoreCosmeticsManager.applyText("Failed to load " + text + ": " + exception.toString());
      }
   }

   private void applyText(String text, String text2, BufferedImage bufferedImage, ExtendedItemCallback extendedItemCallback) {
      ResourceLocation location = new ResourceLocation(this.labelText + "/" + text.hashCode());
      MoreCosmeticsManager.executorService.execute(() -> this.applyText(text2, text, bufferedImage, extendedItemCallback, location));
   }

   private void applyTextByText(String text, ResourceLocation location, BufferedImage bufferedImage, BufferedImage bufferedImage2) {
      BufferedImage bufferedImage3 = bufferedImage;
      if (bufferedImage2 != null) {
         bufferedImage3 = (new CurveMatcher(bufferedImage2, bufferedImage.getWidth(), bufferedImage.getHeight(), true)).resolveBufferedImage(bufferedImage);
      }

      AuxiliaryLoggerEntryBridge auxiliaryLoggerEntryBridge = new AuxiliaryLoggerEntryBridge(location, bufferedImage3);
      if (bufferedImage2 != null) {
         float factor = (float)bufferedImage2.getWidth() / (float)bufferedImage2.getHeight();
         auxiliaryLoggerEntryBridge.setFactor(factor);
      }

      this.hashMap.put(text, auxiliaryLoggerEntryBridge);
      Minecraft.getMinecraft().getTextureManager().matchesResourceLocation(location, auxiliaryLoggerEntryBridge);
   }

   public ResourceLocation resolveResourceLocation(String text, String text2) {
      ResourceLocation location = (ResourceLocation)this.hashMap3.get(text);
      if (location == null) {
         this.hashMap3.put(text, location = new ResourceLocation(text2));
      }

      return location;
   }

   private void applyTextByText(String text, ResourceLocation location, ExtendedItemCallback extendedItemCallback, File file, BufferedImage bufferedImage, String text2) {
      try {
         BufferedImage bufferedImage2 = ImageIO.read(file);
         BufferedImage bufferedImage3 = extendedItemCallback.resolveBufferedImage(bufferedImage2);
         this.applyTextByText(text, location, bufferedImage3, bufferedImage);
      } catch (IOException ioException) {
         MoreCosmeticsManager.applyText("Failed to load " + text2 + ": " + ioException.toString());
      }
   }

   public ResourceLocation resolveResourceLocationByText(String text, String text2) {
      BufferedImage bufferedImage = null;
      AuxiliaryLoggerEntryBridge auxiliaryLoggerEntryBridge = this.resolveAuxiliaryLoggerEntryBridge(text, text2, bufferedImage);
      return auxiliaryLoggerEntryBridge != null ? auxiliaryLoggerEntryBridge.getResourceLocation3() : null;
   }

   private void execute(String text, String text2, BufferedImage bufferedImage, ExtendedItemCallback extendedItemCallback) {
      MoreCosmeticsManager.executorService.execute(() -> this.applyText2(text2, text, bufferedImage, extendedItemCallback));
   }

   public static JpegManager getJpegManager() {
      return jpegManager != null ? jpegManager : (jpegManager = new JpegManager("morecosmetics"));
   }

   private void applyText2(String text, String text2, BufferedImage bufferedImage, ExtendedItemCallback extendedItemCallback) {
      try {
         Object[] entryArray = new Object[0];
         short shortValue = 5000;
         InputStream inputStream = SSLSocketFactoryEntry.resolveInputStream(text, shortValue, entryArray);
         BufferedImageEntry[] bufferedImageEntryArray = GifLogicalScreenDHelper.resolveBufferedImageEntryArray(inputStream);
         this.applyText(text2, bufferedImageEntryArray, bufferedImage, extendedItemCallback);
      } catch (Exception exception) {
         MoreCosmeticsManager.applyText("Failed to load " + text + ": " + exception.toString());
      }
   }

   public AuxiliaryLoggerEntryBridge resolveAuxiliaryLoggerEntryBridge(String text, String text2, BufferedImage bufferedImage, ExtendedItemCallback extendedItemCallback) {
      if (this.hashMap2.containsKey(text)) {
         return ((CommonItemArrayEntry)this.hashMap2.get(text)).getAuxiliaryLoggerEntryBridge();
      } else if (this.hashMap.containsKey(text)) {
         return (AuxiliaryLoggerEntryBridge)this.hashMap.get(text);
      } else {
         this.hashMap.put(text, null);
         String text3 = text2.toLowerCase();
         boolean flag = text3.startsWith("http");
         boolean flag2 = text3.contains(".jpeg") || text3.contains(".jpg");
         if (!text3.contains(".png") && !flag2) {
            if (text3.contains(".gif")) {
               if (flag) {
                  this.execute(text, text2, bufferedImage, extendedItemCallback);
               } else {
                  this.applyText3(text, text2, bufferedImage, extendedItemCallback);
               }
            } else if (flag) {
               this.applyText(text, text2, bufferedImage, extendedItemCallback);
            }
         } else if (flag) {
            this.applyTextByText(text, text2, bufferedImage, extendedItemCallback);
         } else {
            this.applyText4(text, text2, bufferedImage, extendedItemCallback);
         }

         return null;
      }
   }

   private void applyText(String text, BufferedImageEntry[] bufferedImageEntryArray, BufferedImage bufferedImage, ExtendedItemCallback extendedItemCallback) {
      int length = bufferedImageEntryArray.length;
      int[] countArray = new int[length];
      AuxiliaryLoggerEntryBridge[] auxiliaryLoggerEntryBridgeArray = new AuxiliaryLoggerEntryBridge[length];
      CurveMatcher curveMatcher = null;
      String text2 = this.labelText + "/gif/" + text.hashCode() + "_";

      for (int index = 0; index < length; index++) {
         BufferedImageEntry bufferedImageEntry = bufferedImageEntryArray[index];
         BufferedImage bufferedImage2 = bufferedImageEntry.getBufferedImage();
         BufferedImage bufferedImage3 = extendedItemCallback.resolveBufferedImage(bufferedImage2);
         if (index == 0 && bufferedImage != null) {
            curveMatcher = new CurveMatcher(bufferedImage, bufferedImage3.getWidth(), bufferedImage3.getHeight());
         }

         int count = bufferedImageEntry.getCount();
         countArray[index] = count == 0 ? 50 : count * 10;
         ResourceLocation location = new ResourceLocation(text2 + index);
         BufferedImage bufferedImage4 = curveMatcher == null ? bufferedImage3 : curveMatcher.resolveBufferedImage(bufferedImage3);
         AuxiliaryLoggerEntryBridge auxiliaryLoggerEntryBridge = auxiliaryLoggerEntryBridgeArray[index] = new AuxiliaryLoggerEntryBridge(location, bufferedImage4);
         if (bufferedImage != null) {
            float factor = (float)bufferedImage.getWidth() / (float)bufferedImage.getHeight();
            auxiliaryLoggerEntryBridge.setFactor(factor);
         }

         Minecraft.getMinecraft().getTextureManager().matchesResourceLocation(location, auxiliaryLoggerEntryBridge);
      }

      this.hashMap2.put(text, new CommonItemArrayEntry(auxiliaryLoggerEntryBridgeArray, countArray));
   }

   public void updateState() {
      this.hashMap.clear();
      this.hashMap2.clear();
      this.hashMap3.clear();
   }

   public AuxiliaryLoggerEntryBridge resolveAuxiliaryLoggerEntryBridge(String text, String text2, BufferedImage bufferedImage) {
      ExtendedItemCallback extendedItemCallback = ExtendedItemCallback.extendedItemCallback2;
      return this.resolveAuxiliaryLoggerEntryBridge(text, text2, bufferedImage, extendedItemCallback);
   }

   private void applyText4(String text, String text2, BufferedImage bufferedImage, ExtendedItemCallback extendedItemCallback) {
      File file = new File(text2);
      if (!file.exists()) {
         MoreCosmeticsManager.applyText("Failed to load file: " + text2);
      } else {
         ResourceLocation location = new ResourceLocation(this.labelText + "/" + text.hashCode());
         MoreCosmeticsManager.executorService.execute(() -> this.applyTextByText(text, location, extendedItemCallback, file, bufferedImage, text2));
      }
   }

   private void applyTextByText(String text, String text2, BufferedImage bufferedImage, ExtendedItemCallback extendedItemCallback) {
      ResourceLocation location = new ResourceLocation(this.labelText + "/" + text.hashCode());
      MoreCosmeticsManager.executorService.execute(() -> this.applyText(text2, text, location, extendedItemCallback, bufferedImage));
   }

   public JpegManager(String text) {
      this.labelText = text;
   }
}
