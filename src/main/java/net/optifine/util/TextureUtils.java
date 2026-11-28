package net.optifine.util;

import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.optifine.shaders.MultiTexID;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.BetterGrass;
import net.optifine.BetterSnow;
import net.optifine.CustomBlockLayers;
import net.optifine.CustomColors;
import net.optifine.CustomGuis;
import net.optifine.CustomItems;
import net.optifine.CustomLoadingScreens;
import net.optifine.CustomPanorama;
import net.optifine.CustomSky;
import net.optifine.Lang;
import net.optifine.NaturalTextures;
import net.optifine.RandomEntities;
import net.optifine.SmartLeaves;
import net.optifine.TextureAnimations;
import net.optifine.config.Config;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.shaders.Shaders;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class TextureUtils {
   public static final String texGrassTop = null;
   public static final String texStone = null;
   public static final String texDirt = null;
   public static final String texCoarseDirt = null;
   public static final String texGrassSide = null;
   public static final String texStoneslabSide = null;
   public static final String texStoneslabTop = null;
   public static final String texBedrock = null;
   public static final String texSand = null;
   public static final String texGravel = null;
   public static final String texLogOak = null;
   public static final String texLogBigOak = null;
   public static final String texLogAcacia = null;
   public static final String texLogSpruce = null;
   public static final String texLogBirch = null;
   public static final String texLogJungle = null;
   public static final String texLogOakTop = null;
   public static final String texLogBigOakTop = null;
   public static final String texLogAcaciaTop = null;
   public static final String texLogSpruceTop = null;
   public static final String texLogBirchTop = null;
   public static final String texLogJungleTop = null;
   public static final String texLeavesOak = null;
   public static final String texLeavesBigOak = null;
   public static final String texLeavesAcacia = null;
   public static final String texLeavesBirch = null;
   public static final String texLeavesSpruce = null;
   public static final String texLeavesJungle = null;
   public static final String texGoldOre = null;
   public static final String texIronOre = null;
   public static final String texCoalOre = null;
   public static final String texObsidian = null;
   public static final String texGrassSideOverlay = null;
   public static final String texSnow = null;
   public static final String texGrassSideSnowed = null;
   public static final String texMyceliumSide = null;
   public static final String texMyceliumTop = null;
   public static final String texDiamondOre = null;
   public static final String texRedstoneOre = null;
   public static final String texLapisOre = null;
   public static final String texCactusSide = null;
   public static final String texClay = null;
   public static final String texFarmlandWet = null;
   public static final String texFarmlandDry = null;
   public static final String texNetherrack = null;
   public static final String texSoulSand = null;
   public static final String texGlowstone = null;
   public static final String texEndStone = null;
   public static final String texSandstoneTop = null;
   public static final String texSandstoneBottom = null;
   public static final String texRedstoneLampOff = null;
   public static final String texRedstoneLampOn = null;
   public static final String texQuartzOre = null;
   public static final String texPrismarineRough = null;
   public static final String texWaterStill = null;
   public static final String texWaterFlow = null;
   public static final String texLavaStill = null;
   public static final String texLavaFlow = null;
   public static final String texFireLayer0 = null;
   public static final String texFireLayer1 = null;
   public static final String texPortal = null;
   public static final String texGlass = null;
   public static final String texGlassPaneTop = null;
   public static final String texCompass = null;
   public static final String texClock = null;
   public static TextureAtlasSprite iconGrassTop;
   public static TextureAtlasSprite iconGrassSide;
   public static TextureAtlasSprite iconGrassSideOverlay;
   public static TextureAtlasSprite iconSnow;
   public static TextureAtlasSprite iconGrassSideSnowed;
   public static TextureAtlasSprite iconMyceliumSide;
   public static TextureAtlasSprite iconMyceliumTop;
   public static TextureAtlasSprite iconWaterStill;
   public static TextureAtlasSprite iconWaterFlow;
   public static TextureAtlasSprite iconLavaStill;
   public static TextureAtlasSprite iconLavaFlow;
   public static TextureAtlasSprite iconPortal;
   public static TextureAtlasSprite iconFireLayer0;
   public static TextureAtlasSprite iconFireLayer1;
   public static TextureAtlasSprite iconGlass;
   public static TextureAtlasSprite iconGlassPaneTop;
   public static TextureAtlasSprite iconCompass;
   public static TextureAtlasSprite iconClock;
   public static final String SPRITE_PREFIX_BLOCKS = null;
   public static final String SPRITE_PREFIX_ITEMS = null;
   private static IntBuffer staticBuffer = GLAllocation.createDirectIntBuffer(256);

   public static void bindTexture(int n) {
      GlStateManager.bindTexture(n);
   }

   public static TextureMap getTextureMapBlocks() {
      return Minecraft.getMinecraft().getTextureMapBlocks();
   }

   public static boolean isPowerOfTwo(int n) {
      return MathHelper.roundUpToPowerOfTwo(n) == n;
   }

   public static ITextureObject getTexture(ResourceLocation location) {
      ITextureObject texture = Config.getTextureManager().getTexture(location);
      if (texture != null) {
         return texture;
      } else if (!Config.hasResource(location)) {
         return null;
      } else {
         SimpleTexture simpleTexture = new SimpleTexture(location, true);
         Config.getTextureManager().loadTexture(location, simpleTexture);
         return simpleTexture;
      }
   }

   public static void resourcesReloaded(IResourceManager resourceManager) {
      if (getTextureMapBlocks() != null) {
         Config.dbg("*** Reloading custom textures ***");
         CustomSky.reset();
         TextureAnimations.reset();
         update();
         NaturalTextures.update();
         BetterGrass.update();
         BetterSnow.update();
         TextureAnimations.update();
         CustomColors.update();
         CustomSky.update();
         RandomEntities.update();
         CustomItems.updateModels();
         CustomEntityModels.update();
         Shaders.resourcesReloaded();
         Lang.resourcesReloaded();
         Config.updateTexturePackClouds();
         SmartLeaves.updateLeavesModels();
         CustomPanorama.update();
         CustomGuis.update();
         LayerMooshroomMushroom.update();
         CustomLoadingScreens.update();
         CustomBlockLayers.update();
         Config.getTextureManager().tick();
      }
   }

   public static int scaleToMin(int n, int n2) {
      if (n >= n2) {
         return n;
      } else {
         int n3 = n2 / n * n;

         while (n3 < n2) {
            n3 += n;
         }

         return n3;
      }
   }

   public static int ceilPowerOfTwo(int n) {
      int n2 = 1;

      while (n2 < n) {
         n2 *= 2;
      }

      return n2;
   }

   public static int[] getFrameData(TextureAtlasSprite textureAtlasSprite, int n, int n2) {
      List framesTextureData = textureAtlasSprite.getFramesTextureData();
      if (framesTextureData.size() <= n) {
         return null;
      } else {
         int[][] intsArr = (int[][])framesTextureData.get(n);
         return intsArr != null && intsArr.length > n2 ? intsArr[n2] : null;
      }
   }

   public static void saveGlTexture(String str, int n, int n2, int n3, int n4) {
      bindTexture(n);
      GL11.glPixelStorei(3333, 1);
      GL11.glPixelStorei(3317, 1);
      File parentFile = new File(str).getParentFile();
      if (parentFile != null) {
         parentFile.mkdirs();
      }

      for (int i = 0; i < 16; i++) {
         new File(str + "_" + i + ".png").delete();
      }

      for (int j = 0; j <= n2; j++) {
         File file3 = new File(str + "_" + j + ".png");
         int n5 = n3 >> j;
         int n6 = n4 >> j;
         int n7 = n5 * n6;
         IntBuffer intBuffer = BufferUtils.createIntBuffer(n7);
         int[] ints = new int[n7];
         GL11.glGetTexImage(3553, j, 32993, 33639, intBuffer);
         intBuffer.get(ints);
         BufferedImage image = new BufferedImage(n5, n6, 2);
         image.setRGB(0, 0, n5, n6, ints, 0, n5);

         try {
            ImageIO.write(image, "png", file3);
            Config.dbg("Exported: " + file3);
         } catch (Exception e) {
            Config.warn("Error writing: " + file3);
            Config.warn("" + e.getClass().getName() + ": " + e.getMessage());
         }
      }
   }

   public static int getGLMaximumTextureSize() {
      for (int i = 65536; i > 0; i >>= 1) {
         GlStateManager.glTexImage2D(32868, 0, 6408, i, i, 0, 6408, 5121, (IntBuffer)null);
         GL11.glGetError();
         if (GlStateManager.glGetTexLevelParameteri(32868, 0, 4096) != 0) {
            return i;
         }
      }

      return -1;
   }

   public static void registerResourceListener() {
      IResourceManager resourceManager = Config.getResourceManager();
      if (resourceManager instanceof IReloadableResourceManager) {
         ((IReloadableResourceManager)resourceManager).registerReloadListener(new TextureUtils.DefaultResourceManagerReloadListener());
      }

      TextureUtils.DefaultTickableTextureObject obj2 = new TextureUtils.DefaultTickableTextureObject();
      ResourceLocation location = new ResourceLocation("optifine/TickableTextures");
      Config.getTextureManager().loadTickableTexture(location, obj2);
   }

   public static String getBasePath(String str) {
      int n = str.lastIndexOf(47);
      return n < 0 ? "" : str.substring(0, n);
   }

   public static BufferedImage scaleImage(BufferedImage image, int n) {
      int width = image.getWidth();
      int n2 = image.getHeight() * n / width;
      BufferedImage image2 = new BufferedImage(n, n2, 2);
      Graphics2D g2d = image2.createGraphics();
      Object obj = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
      if (n < width || n % width != 0) {
         obj = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
      }

      g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, obj);
      g2d.drawImage(image, 0, 0, n, n2, (ImageObserver)null);
      return image2;
   }

   public static int twoToPower(int n) {
      int n2 = 1;

      for (int i = 0; i < n; i++) {
         n2 *= 2;
      }

      return n2;
   }

   public static int getPowerOfTwo(int n) {
      int n2 = 1;

      int n3;
      for (n3 = 0; n2 < n; n3++) {
         n2 *= 2;
      }

      return n3;
   }

   public static ResourceLocation fixResourceLocation(ResourceLocation location, String str) {
      if (!location.getResourceDomain().equals("minecraft")) {
         return location;
      } else {
         String resourcePath = location.getResourcePath();
         String str2 = fixResourcePath(resourcePath, str);
         if (str2 != resourcePath) {
            location = new ResourceLocation(location.getResourceDomain(), str2);
         }

         return location;
      }
   }

   public static int scaleToGrid(int n, int n2) {
      if (n == n2) {
         return n;
      } else {
         int n3 = n / n2 * n2;

         while (n3 < n) {
            n3 += n2;
         }

         return n3;
      }
   }

   public static void dbgMipmaps(TextureAtlasSprite textureAtlasSprite) {
      int[][] frameTextureData = textureAtlasSprite.getFrameTextureData(0);

      for (int i = 0; i < frameTextureData.length; i++) {
         int[] ints = frameTextureData[i];
         if (ints == null) {
            Config.dbg("" + i + ": " + ints);
         } else {
            Config.dbg("" + i + ": " + ints.length);
         }
      }
   }

   public static void applyAnisotropicLevel() {
      if (GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic) {
         float f = GL11.glGetFloat(34047);
         float anisotropicFilterLevel = (float)Config.getAnisotropicFilterLevel();
         anisotropicFilterLevel = Math.min(anisotropicFilterLevel, f);
         GL11.glTexParameterf(3553, 34046, anisotropicFilterLevel);
      }
   }

   public static String fixResourcePath(String str, String str2) {
      String str3 = "assets/minecraft/";
      if (str.startsWith(str3)) {
         return str.substring(str3.length());
      } else if (str.startsWith("./")) {
         str = str.substring(2);
         if (!str2.endsWith("/")) {
            str2 = str2 + "/";
         }

         return str2 + str;
      } else {
         if (str.startsWith("/~")) {
            str = str.substring(1);
         }

         String str4 = "mcpatcher/";
         if (str.startsWith("~/")) {
            str = str.substring(2);
            return str4 + str;
         } else {
            return str.startsWith("/") ? str4 + str.substring(1) : str;
         }
      }
   }

   public static void update() {
      TextureMap textureMapBlocks = getTextureMapBlocks();
      if (textureMapBlocks != null) {
         String str = "minecraft:blocks/";
         iconGrassTop = textureMapBlocks.getSpriteSafe(str + "grass_top");
         iconGrassSide = textureMapBlocks.getSpriteSafe(str + "grass_side");
         iconGrassSideOverlay = textureMapBlocks.getSpriteSafe(str + "grass_side_overlay");
         iconSnow = textureMapBlocks.getSpriteSafe(str + "snow");
         iconGrassSideSnowed = textureMapBlocks.getSpriteSafe(str + "grass_side_snowed");
         iconMyceliumSide = textureMapBlocks.getSpriteSafe(str + "mycelium_side");
         iconMyceliumTop = textureMapBlocks.getSpriteSafe(str + "mycelium_top");
         iconWaterStill = textureMapBlocks.getSpriteSafe(str + "water_still");
         iconWaterFlow = textureMapBlocks.getSpriteSafe(str + "water_flow");
         iconLavaStill = textureMapBlocks.getSpriteSafe(str + "lava_still");
         iconLavaFlow = textureMapBlocks.getSpriteSafe(str + "lava_flow");
         iconFireLayer0 = textureMapBlocks.getSpriteSafe(str + "fire_layer_0");
         iconFireLayer1 = textureMapBlocks.getSpriteSafe(str + "fire_layer_1");
         iconPortal = textureMapBlocks.getSpriteSafe(str + "portal");
         iconGlass = textureMapBlocks.getSpriteSafe(str + "glass");
         iconGlassPaneTop = textureMapBlocks.getSpriteSafe(str + "glass_pane_top");
         String str2 = "minecraft:items/";
         iconCompass = textureMapBlocks.getSpriteSafe(str2 + "compass");
         iconClock = textureMapBlocks.getSpriteSafe(str2 + "clock");
      }
   }

   public static BufferedImage fixTextureDimensions(String str, BufferedImage image) {
      if (str.startsWith("/mob/zombie") || str.startsWith("/mob/pigzombie")) {
         int width = image.getWidth();
         int height = image.getHeight();
         if (width == height * 2) {
            BufferedImage image2 = new BufferedImage(width, height * 2, 2);
            Graphics2D g2d = image2.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(image, 0, 0, width, height, (ImageObserver)null);
            return image2;
         }
      }

      return image;
   }

   public static Dimension getImageSize(InputStream in, String str) {
      Iterator imageReadersBySuffix = ImageIO.getImageReadersBySuffix(str);

      while (imageReadersBySuffix.hasNext()) {
         ImageReader imageReader = (ImageReader)imageReadersBySuffix.next();

         Dimension dimension;
         try {
            imageReader.setInput(ImageIO.createImageInputStream(in));
            int width = imageReader.getWidth(imageReader.getMinIndex());
            int height = imageReader.getHeight(imageReader.getMinIndex());
            dimension = new Dimension(width, height);
         } catch (IOException e) {
            continue;
         } finally {
            imageReader.dispose();
         }

         return dimension;
      }

      return null;
   }

   public static void generateCustomMipmaps(TextureAtlasSprite textureAtlasSprite, int n) {
      int iconWidth = textureAtlasSprite.getIconWidth();
      int iconHeight = textureAtlasSprite.getIconHeight();
      if (textureAtlasSprite.getFrameCount() < 1) {
         ArrayList list = new ArrayList();
         int[][] intsArr = new int[n + 1][];
         intsArr[0] = new int[iconWidth * iconHeight];
         list.add(intsArr);
         textureAtlasSprite.setFramesTextureData(list);
      }

      ArrayList list2 = new ArrayList();
      int frameCount = textureAtlasSprite.getFrameCount();

      for (int i = 0; i < frameCount; i++) {
         int[] frameData = getFrameData(textureAtlasSprite, i, 0);
         if (frameData == null || frameData.length < 1) {
            frameData = new int[iconWidth * iconHeight];
         }

         if (frameData.length != iconWidth * iconHeight) {
            int n2 = (int)Math.round(Math.sqrt((double)frameData.length));
            if (n2 * n2 != frameData.length) {
               frameData = new int[1];
               n2 = 1;
            }

            BufferedImage image = new BufferedImage(n2, n2, 2);
            image.setRGB(0, 0, n2, n2, frameData, 0, n2);
            BufferedImage image2 = scaleImage(image, iconWidth);
            int[] ints2 = new int[iconWidth * iconHeight];
            image2.getRGB(0, 0, iconWidth, iconHeight, ints2, 0, iconWidth);
            frameData = ints2;
         }

         int[][] intsArr2 = new int[n + 1][];
         intsArr2[0] = frameData;
         list2.add(intsArr2);
      }

      textureAtlasSprite.setFramesTextureData(list2);
      textureAtlasSprite.generateMipmaps(n);
   }


   public static final class DefaultResourceManagerReloadListener implements IResourceManagerReloadListener {
      @Override
      public void onResourceManagerReload(IResourceManager resourceManager) {
         TextureUtils.resourcesReloaded(resourceManager);
      }
   }

   public static final class DefaultTickableTextureObject implements ITickableTextureObject {
      @Override
      public void restoreLastBlurMipmap() {
      }

      @Override
      public int getGlTextureId() {
         return 0;
      }

      @Override
      public MultiTexID getMultiTexID() {
         return null;
      }

      @Override
      public void tick() {
         TextureAnimations.updateAnimations();
      }

      @Override
      public void setBlurMipmap(boolean flag, boolean flag2) {
      }

      @Override
      public void loadTexture(IResourceManager resourceManager) throws IOException {
      }
   }
}
