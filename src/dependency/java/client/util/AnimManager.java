package client.util;

import client.event.PreUpdateProcessor;
import client.setting.GameSettings;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import javax.imageio.ImageIO;
import net.minecraft.util.ResourceLocation;

public class AnimManager {
   public static FrameWidthManager[] frameWidthManagerArray = null;
   public static int count2 = 0;
   public static int count = 0;

   public static void updateState2() {
      frameWidthManagerArray = null;
      count2 = 0;
      frameWidthManagerArray = resolveFrameWidthManagerArray(BuildManager.getPreUpdateProcessorArray());
      updateState3();
   }

   private static byte[] resolveByteArrayByText(String text, int count3) {
      GameSettings gameSettings = BuildManager.getGameSettings();

      try {
         InputStream inputStream = BuildManager.resolveInputStream(new ResourceLocation(text));
         if (inputStream == null) {
            return null;
         } else {
            BufferedImage bufferedImage = resolveBufferedImage(inputStream);
            inputStream.close();
            if (bufferedImage == null) {
               return null;
            } else {
               if (count3 > 0 && bufferedImage.getWidth() != count3) {
                  double offset = (double)(bufferedImage.getHeight() / bufferedImage.getWidth());
                  int count4 = (int)((double)count3 * offset);
                  bufferedImage = resolveBufferedImage(bufferedImage, count3, count4);
               }

               int width = bufferedImage.getWidth();
               int height = bufferedImage.getHeight();
               int[] countArray = new int[width * height];
               byte[] byteValueArray = new byte[width * height * 4];
               bufferedImage.getRGB(0, 0, width, height, countArray, 0, width);

               for (int index = 0; index < countArray.length; index++) {
                  int count5 = countArray[index] >> 24 & 0xFF;
                  int count6 = countArray[index] >> 16 & 0xFF;
                  int count7 = countArray[index] >> 8 & 0xFF;
                  int count8 = countArray[index] & 0xFF;
                  if (gameSettings != null && gameSettings.state105) {
                     int count9 = (count6 * 30 + count7 * 59 + count8 * 11) / 100;
                     count7 = (count6 * 30 + count7 * 70) / 100;
                     count8 = (count6 * 30 + count8 * 70) / 100;
                     count6 = count9;
                  }

                  byteValueArray[index * 4 + 0] = (byte)count6;
                  byteValueArray[index * 4 + 1] = (byte)count7;
                  byteValueArray[index * 4 + 2] = (byte)count8;
                  byteValueArray[index * 4 + 3] = (byte)count5;
               }

               return byteValueArray;
            }
         }
      } catch (FileNotFoundException fileNotFoundException) {
         return null;
      } catch (Exception exception) {
         exception.printStackTrace();
         return null;
      }
   }

   private static FrameWidthManager[] resolveFrameWidthManagerArray(PreUpdateProcessor[] preUpdateProcessorArray) {
      ArrayList list = new ArrayList();

      for (int index = 0; index < preUpdateProcessorArray.length; index++) {
         FrameWidthManager[] frameWidthManagerArray2 = resolveFrameWidthManagerArray(preUpdateProcessorArray[index]);
         if (frameWidthManagerArray2 != null) {
            list.addAll(Arrays.asList(frameWidthManagerArray2));
         }
      }

      return ((FrameWidthManager[])list.toArray(new FrameWidthManager[list.size()]));
   }

   private static byte[] resolveByteArray(String text, int count3) {
      byte[] byteValueArray = resolveByteArrayByText(text, count3);
      if (byteValueArray == null) {
         byteValueArray = resolveByteArrayByText("/anim" + text, count3);
      }

      return byteValueArray;
   }

   private static FrameWidthManager resolveFrameWidthManager(Properties properties, ResourceLocation location) {
      String property = properties.getProperty("from");
      String property2 = properties.getProperty("to");
      int count3 = BuildManager.computeCount(properties.getProperty("x"), -1);
      int count4 = BuildManager.computeCount(properties.getProperty("y"), -1);
      int count5 = BuildManager.computeCount(properties.getProperty("w"), -1);
      int count6 = BuildManager.computeCount(properties.getProperty("h"), -1);
      if (property == null || property2 == null) {
         BuildManager.applyText3("TextureAnimation: Source or target texture not specified");
         return null;
      } else if (count3 >= 0 && count4 >= 0 && count5 >= 0 && count6 >= 0) {
         String text = property.trim();
         String text2 = property2.trim();
         String text3 = InternalPngManager.resolveText(location.getText1());
         String text4 = InternalPngManager.resolveText(text, text3);
         String text5 = InternalPngManager.resolveText(text2, text3);
         byte[] byteValueArray = resolveByteArray(text4, count5);
         if (byteValueArray == null) {
            BuildManager.applyText3("TextureAnimation: Source texture not found: " + text5);
            return null;
         } else {
            int count7 = byteValueArray.length / 4;
            int count8 = count7 / (count5 * count6) * count5 * count6;
            if (count7 != count8) {
               BuildManager.applyText3(
                  "TextureAnimation: Source texture has invalid number of frames: " + text4 + ", frames: " + (float)count7 / (float)(count5 * count6)
               );
               return null;
            } else {
               ResourceLocation location2 = new ResourceLocation(text5);

               try {
                  InputStream inputStream = BuildManager.resolveInputStream(location2);
                  if (inputStream == null) {
                     BuildManager.applyText3("TextureAnimation: Target texture not found: " + text5);
                     return null;
                  } else {
                     BufferedImage bufferedImage = resolveBufferedImage(inputStream);
                     if (count3 + count5 <= bufferedImage.getWidth() && count4 + count6 <= bufferedImage.getHeight()) {
                        return new FrameWidthManager(text4, byteValueArray, text5, location2, count3, count4, count5, count6, properties);
                     } else {
                        BuildManager.applyText3("TextureAnimation: Animation coordinates are outside the target texture: " + text5);
                        return null;
                     }
                  }
               } catch (IOException ioException) {
                  BuildManager.applyText3("TextureAnimation: Target texture not found: " + text5);
                  return null;
               }
            }
         }
      } else {
         BuildManager.applyText3("TextureAnimation: Invalid coordinates");
         return null;
      }
   }

   private static BufferedImage resolveBufferedImage(InputStream inputStream) throws IOException {
      BufferedImage bufferedImage = ImageIO.read(inputStream);
      inputStream.close();
      return bufferedImage;
   }

   public static void updateState3() {
      if (frameWidthManagerArray != null && BuildManager.isActive5()) {
         int count3 = 0;

         for (int index = 0; index < frameWidthManagerArray.length; index++) {
            FrameWidthManager frameWidthManager = frameWidthManagerArray[index];
            frameWidthManager.updateState();
            if (frameWidthManager.isEnabled3()) {
               count3++;
            }
         }

         int count4 = BuildManager.getMc().entityRenderer.count5;
         if (count4 != count) {
            count2 = count3;
            count = count4;
         }

         if (BitSetHolder.isActive()) {
            BitSetHolder.clear2();
         }
      } else {
         count2 = 0;
      }
   }

   private static BufferedImage resolveBufferedImage(BufferedImage bufferedImage, int count3, int count4) {
      BufferedImage bufferedImage2 = new BufferedImage(count3, count4, 2);
      Graphics2D graphics2D = bufferedImage2.createGraphics();
      graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      graphics2D.drawImage(bufferedImage, 0, 0, count3, count4, (ImageObserver)null);
      return bufferedImage2;
   }

   public static int getCount2() {
      return count2;
   }

   public static void updateState() {
      frameWidthManagerArray = null;
   }

   public static int getCount() {
      return frameWidthManagerArray == null ? 0 : frameWidthManagerArray.length;
   }

   private static FrameWidthManager[] resolveFrameWidthManagerArray(PreUpdateProcessor preUpdateProcessor) {
      String[] textArray = AssetsMinecraftManager.resolveTextArray(preUpdateProcessor, "mcpatcher/anim/", ".properties", (String[])null);
      if (textArray.length <= 0) {
         return null;
      } else {
         ArrayList list = new ArrayList();

         for (int index = 0; index < textArray.length; index++) {
            String text = textArray[index];
            BuildManager.applyText4("Texture animation: " + text);

            try {
               ResourceLocation location = new ResourceLocation(text);
               InputStream inputStream = preUpdateProcessor.resolveInputStream(location);
               PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
               propertiesAdapter.load(inputStream);
               inputStream.close();
               FrameWidthManager frameWidthManager = resolveFrameWidthManager(propertiesAdapter, location);
               if (frameWidthManager != null) {
                  if (BuildManager.resolvePreUpdateProcessor(new ResourceLocation(frameWidthManager.getLabelText2())) != preUpdateProcessor) {
                     BuildManager.applyText4("Skipped: " + text + ", target texture not loaded from same resource pack");
                  } else {
                     list.add(frameWidthManager);
                  }
               }
            } catch (FileNotFoundException fileNotFoundException) {
               BuildManager.applyText3("File not found: " + fileNotFoundException.getMessage());
            } catch (IOException ioException) {
               ioException.printStackTrace();
            }
         }

         return ((FrameWidthManager[])list.toArray(new FrameWidthManager[list.size()]));
      }
   }
}
