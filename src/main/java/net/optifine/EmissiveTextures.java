package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.util.PropertiesOrdered;

public class EmissiveTextures {
   private static String suffixEmissive = null;
   private static String suffixEmissivePng = null;
   private static boolean active = false;
   private static boolean render = false;
   private static boolean hasEmissive = false;
   private static boolean renderEmissive = false;
   private static float lightMapX;
   private static float lightMapY;
   private static final String SUFFIX_PNG = null;
   private static final ResourceLocation EMPTY_TEXTURE = new ResourceLocation("mcpatcher/ctm/default/empty.png");

   public static boolean isEmissive(ResourceLocation location) {
      return suffixEmissivePng == null ? false : location.getResourcePath().endsWith(suffixEmissivePng);
   }

   public static void endRenderEmissive() {
      renderEmissive = false;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapX, lightMapY);
   }

   public static void beginRender() {
      render = true;
      hasEmissive = false;
   }

   public static void endRender() {
      render = false;
      hasEmissive = false;
   }

   public static ITextureObject getEmissiveTexture(ITextureObject textureObject, Map map) {
      if (!render) {
         return textureObject;
      } else if (!(textureObject instanceof SimpleTexture)) {
         return textureObject;
      } else {
         ResourceLocation locationEmissive = ((SimpleTexture)textureObject).locationEmissive;
         if (!renderEmissive) {
            if (locationEmissive != null) {
               hasEmissive = true;
            }

            return textureObject;
         } else {
            if (locationEmissive == null) {
               locationEmissive = EMPTY_TEXTURE;
            }

            Object obj = (ITextureObject)map.get(locationEmissive);
            if (obj == null) {
               obj = new SimpleTexture(locationEmissive, true);
               Config.getTextureManager().loadTexture(locationEmissive, (ITextureObject)obj);
            }

            return (ITextureObject)obj;
         }
      }
   }

   public static String getSuffixEmissive() {
      return suffixEmissive;
   }

   public static boolean isActive() {
      return active;
   }

   public static void beginRenderEmissive() {
      lightMapX = OpenGlHelper.lastBrightnessX;
      lightMapY = OpenGlHelper.lastBrightnessY;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, lightMapY);
      renderEmissive = true;
   }

   public static boolean hasEmissive() {
      return hasEmissive;
   }

   private static void warn(String str) {
      Config.warn("EmissiveTextures: " + str);
   }

   public static void update() {
      active = false;
      suffixEmissive = null;
      suffixEmissivePng = null;
      if (Config.isEmissiveTextures()) {
         try {
            String str = "optifine/emissive.properties";
            InputStream resourceStream = Config.getResourceStream(new ResourceLocation(str));
            if (resourceStream == null) {
               return;
            }

            dbg("Loading " + str);
            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            propertiesOrdered.load(resourceStream);
            resourceStream.close();
            suffixEmissive = propertiesOrdered.getProperty("suffix.emissive");
            if (suffixEmissive != null) {
               suffixEmissivePng = suffixEmissive + ".png";
            }

            active = suffixEmissive != null;
         } catch (FileNotFoundException e) {
            return;
         } catch (IOException e2) {
            e2.printStackTrace();
         }
      }
   }

   private static void dbg(String str) {
      Config.dbg("EmissiveTextures: " + str);
   }

   public static void loadTexture(ResourceLocation location, SimpleTexture simpleTexture) {
      if (location != null && simpleTexture != null) {
         simpleTexture.isEmissive = false;
         simpleTexture.locationEmissive = null;
         if (suffixEmissivePng != null) {
            String resourcePath = location.getResourcePath();
            if (resourcePath.endsWith(".png")) {
               if (resourcePath.endsWith(suffixEmissivePng)) {
                  simpleTexture.isEmissive = true;
               } else {
                  String str = resourcePath.substring(0, resourcePath.length() - ".png".length()) + suffixEmissivePng;
                  ResourceLocation location2 = new ResourceLocation(location.getResourceDomain(), str);
                  if (Config.hasResource(location2)) {
                     simpleTexture.locationEmissive = location2;
                  }
               }
            }
         }
      }
   }
}
