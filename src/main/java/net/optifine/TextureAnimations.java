package net.optifine;

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
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.TextureUtils;

public class TextureAnimations {
   private static TextureAnimation[] textureAnimations = null;
   private static int countAnimationsActive = 0;
   private static int frameCountAnimations = 0;

   private static byte[] getCustomTextureData(String str, int n) {
      GameSettings gameSettings = Config.getGameSettings();

      try {
         InputStream resourceStream = Config.getResourceStream(new ResourceLocation(str));
         if (resourceStream == null) {
            return null;
         } else {
            BufferedImage image = readTextureImage(resourceStream);
            resourceStream.close();
            if (image == null) {
               return null;
            } else {
               if (n > 0 && image.getWidth() != n) {
                  image = scaleBufferedImage(image, n, (int)((double)n * ((double)(image.getHeight() / image.getWidth()))));
               }

               int width = image.getWidth();
               int height = image.getHeight();
               int[] ints = new int[width * height];
               byte[] bytes = new byte[width * height * 4];
               image.getRGB(0, 0, width, height, ints, 0, width);

               for (int i = 0; i < ints.length; i++) {
                  int n3 = ints[i] >> 24 & 0xFF;
                  int n4 = ints[i] >> 16 & 0xFF;
                  int n5 = ints[i] >> 8 & 0xFF;
                  int n6 = ints[i] & 0xFF;
                  if (gameSettings != null && gameSettings.anaglyph) {
                     int n7 = (n4 * 30 + n5 * 59 + n6 * 11) / 100;
                     int n8 = (n4 * 30 + n5 * 70) / 100;
                     int n9 = (n4 * 30 + n6 * 70) / 100;
                     n4 = n7;
                     n5 = n8;
                     n6 = n9;
                  }

                  bytes[i * 4 + 0] = (byte)n4;
                  bytes[i * 4 + 1] = (byte)n5;
                  bytes[i * 4 + 2] = (byte)n6;
                  bytes[i * 4 + 3] = (byte)n3;
               }

               return bytes;
            }
         }
      } catch (FileNotFoundException e) {
         return null;
      } catch (Exception e2) {
         e2.printStackTrace();
         return null;
      }
   }

   private static TextureAnimation[] getTextureAnimations(IResourcePack resourcePack) {
      String[] strings = ResUtils.collectFiles(resourcePack, "mcpatcher/anim/", ".properties", (String[])null);
      if (strings.length <= 0) {
         return null;
      } else {
         ArrayList list = new ArrayList();

         for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            Config.dbg("Texture animation: " + str);

            try {
               ResourceLocation location = new ResourceLocation(str);
               InputStream inputStream = resourcePack.getInputStream(location);
               PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
               propertiesOrdered.load(inputStream);
               inputStream.close();
               TextureAnimation textureAnimation = makeTextureAnimation(propertiesOrdered, location);
               if (textureAnimation != null) {
                  if (Config.getDefiningResourcePack(new ResourceLocation(textureAnimation.getDstTex())) != resourcePack) {
                     Config.dbg("Skipped: " + str + ", target texture not loaded from same resource pack");
                  } else {
                     list.add(textureAnimation);
                  }
               }
            } catch (FileNotFoundException e) {
               Config.warn("File not found: " + e.getMessage());
            } catch (IOException e2) {
               e2.printStackTrace();
            }
         }

         return (TextureAnimation[])list.toArray(new TextureAnimation[list.size()]);
      }
   }

   public static void updateAnimations() {
      if (textureAnimations != null && Config.isAnimatedTextures()) {
         int n = 0;

         for (int i = 0; i < textureAnimations.length; i++) {
            TextureAnimation textureAnimation = textureAnimations[i];
            textureAnimation.updateTexture();
            if (textureAnimation.isActive()) {
               n++;
            }
         }

         int frameCount = Config.getMc().entityRenderer.frameCount;
         if (frameCount != frameCountAnimations) {
            countAnimationsActive = n;
            frameCountAnimations = frameCount;
         }

         if (SmartAnimations.isActive()) {
            SmartAnimations.resetTexturesRendered();
         }
      } else {
         countAnimationsActive = 0;
      }
   }

   private static TextureAnimation makeTextureAnimation(Properties properties, ResourceLocation location) {
      String property = properties.getProperty("from");
      String property2 = properties.getProperty("to");
      int n = Config.parseInt(properties.getProperty("x"), -1);
      int n2 = Config.parseInt(properties.getProperty("y"), -1);
      int n3 = Config.parseInt(properties.getProperty("w"), -1);
      int n4 = Config.parseInt(properties.getProperty("h"), -1);
      if (property == null || property2 == null) {
         Config.warn("TextureAnimation: Source or target texture not specified");
         return null;
      } else if (n >= 0 && n2 >= 0 && n3 >= 0 && n4 >= 0) {
         property = property.trim();
         property2 = property2.trim();
         String basePath = TextureUtils.getBasePath(location.getResourcePath());
         property = TextureUtils.fixResourcePath(property, basePath);
         property2 = TextureUtils.fixResourcePath(property2, basePath);
         byte[] bytes = loadImage(property, n3);
         if (bytes == null) {
            Config.warn("TextureAnimation: Source texture not found: " + property2);
            return null;
         } else {
            int n5 = bytes.length / 4;
            if (n5 != ((n5 / (n3 * n4)) * n3 * n4)) {
               Config.warn("TextureAnimation: Source texture has invalid number of frames: " + property + ", frames: " + (float)n5 / (float)(n3 * n4));
               return null;
            } else {
               ResourceLocation location2 = new ResourceLocation(property2);

               try {
                  InputStream resourceStream = Config.getResourceStream(location2);
                  if (resourceStream == null) {
                     Config.warn("TextureAnimation: Target texture not found: " + property2);
                     return null;
                  } else {
                     BufferedImage image = readTextureImage(resourceStream);
                     if (n + n3 <= image.getWidth() && n2 + n4 <= image.getHeight()) {
                        return new TextureAnimation(property, bytes, property2, location2, n, n2, n3, n4, properties);
                     } else {
                        Config.warn("TextureAnimation: Animation coordinates are outside the target texture: " + property2);
                        return null;
                     }
                  }
               } catch (IOException e) {
                  Config.warn("TextureAnimation: Target texture not found: " + property2);
                  return null;
               }
            }
         }
      } else {
         Config.warn("TextureAnimation: Invalid coordinates");
         return null;
      }
   }

   public static void reset() {
      textureAnimations = null;
   }

   public static int getCountAnimationsActive() {
      return countAnimationsActive;
   }

   private static BufferedImage readTextureImage(InputStream in) throws IOException {
      BufferedImage image = ImageIO.read(in);
      in.close();
      return image;
   }

   private static BufferedImage scaleBufferedImage(BufferedImage image, int n, int n2) {
      BufferedImage image2 = new BufferedImage(n, n2, 2);
      Graphics2D g2d = image2.createGraphics();
      g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g2d.drawImage(image, 0, 0, n, n2, (ImageObserver)null);
      return image2;
   }

   public static int getCountAnimations() {
      return textureAnimations == null ? 0 : textureAnimations.length;
   }

   private static byte[] loadImage(String str, int n) {
      byte[] customTextureData = getCustomTextureData(str, n);
      if (customTextureData == null) {
         customTextureData = getCustomTextureData("/anim" + str, n);
      }

      return customTextureData;
   }

   public static void update() {
      textureAnimations = null;
      countAnimationsActive = 0;
      textureAnimations = getTextureAnimations2(Config.getResourcePacks());
      updateAnimations();
   }

   private static TextureAnimation[] getTextureAnimations2(IResourcePack[] resourcePacks) {
      ArrayList list = new ArrayList();

      for (int i = 0; i < resourcePacks.length; i++) {
         TextureAnimation[] textureAnimations2 = getTextureAnimations(resourcePacks[i]);
         if (textureAnimations2 != null) {
            list.addAll(Arrays.asList(textureAnimations2));
         }
      }

      return (TextureAnimation[])list.toArray(new TextureAnimation[list.size()]);
   }
}
