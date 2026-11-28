package net.optifine.config;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URI;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import net.optifine.GlErrors;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.FrameTimer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.DynamicLights;
import net.optifine.VersionCheckThread;
import net.optifine.gui.GuiMessage;
import net.optifine.shaders.Shaders;
import net.optifine.util.DisplayModeComparator;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.TextureUtils;
import net.optifine.util.TimedEvent;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.PixelFormat;

public class Config {
   public static final String OF_NAME = null;
   public static final String MC_VERSION = null;
   public static final String OF_EDITION = null;
   public static final String OF_RELEASE = null;
   public static final String VERSION = null;
   public static final Float DEF_ALPHA_FUNC_LEVEL = 0.1F;
   public static final boolean logDetail = System.getProperty("log.detail", "false").equals("true");
   private static final Logger LOGGER = LogManager.getLogger();
   public static String openGlVersion = null;
   public static String openGlRenderer = null;
   public static String openGlVendor = null;
   public static String[] openGlExtensions = null;
   public static GlVersion glVersion = null;
   public static GlVersion glslVersion = null;
   public static int minecraftVersionInt = -1;
   public static boolean fancyFogAvailable = false;
   public static boolean occlusionAvailable = false;
   public static boolean zoomMode = false;
   public static boolean zoomSmoothCamera = false;
   public static boolean waterOpacityChanged = false;
   public static float renderPartialTicks;
   private static String build = null;
   private static String newRelease = null;
   private static boolean notify64BitJava = false;
   private static GameSettings gameSettings = null;
   private static final Minecraft mc = Minecraft.getMinecraft();
   private static boolean initialized = false;
   private static Thread minecraftThread = null;
   private static DisplayMode desktopDisplayMode = null;
   private static DisplayMode[] displayModes = null;
   private static int antialiasingLevel = 0;
   private static int availableProcessors = 0;
   private static int texturePackClouds = 0;
   private static boolean fullscreenModeChecked = false;
   private static boolean desktopModeChecked = false;
   private static DefaultResourcePack defaultResourcePackLazy = null;
   private static String mcDebugLast = null;
   private static int fpsMinLast = 0;

   public static float getAmbientOcclusionLevel() {
      return isShaderPackLoaded() && Shaders.aoLevel >= 0.0F ? Shaders.aoLevel : gameSettings.ofAoLevel;
   }

   public static boolean isBetterGrass() {
      return gameSettings.ofBetterGrass != 3;
   }

   public static boolean isShowCapes() {
      return gameSettings.ofShowCapes;
   }

   public static boolean isCustomColors() {
      return gameSettings.ofCustomColors;
   }

   public static String[] getFullscreenModes() {
      DisplayMode[] displayModes2 = getDisplayModes();
      String[] strings = new String[displayModes2.length];

      for (int i = 0; i < displayModes2.length; i++) {
         DisplayMode displayMode = displayModes2[i];
         strings[i] = displayMode.getWidth() + "x" + displayMode.getHeight();
      }

      return strings;
   }

   public static boolean isCullFacesLeaves() {
      return gameSettings.ofTrees == 0 ? !gameSettings.fancyGraphics : gameSettings.ofTrees == 4;
   }

   private static DisplayMode[] getDisplayModes(DisplayMode[] displayModes2, Dimension dimension) {
      ArrayList list = new ArrayList();

      for (int i = 0; i < displayModes2.length; i++) {
         DisplayMode displayMode = displayModes2[i];
         if ((double)displayMode.getWidth() == dimension.getWidth() && (double)displayMode.getHeight() == dimension.getHeight()) {
            list.add(displayMode);
         }
      }

      return (DisplayMode[])list.toArray(new DisplayMode[list.size()]);
   }

   public static boolean isCustomSky() {
      return gameSettings.ofCustomSky;
   }

   public static boolean isMultiTexture() {
      return getAnisotropicFilterLevel() > 1 || getAntialiasingLevel() > 0;
   }

   public static String[] readLines(InputStream in) throws IOException {
      ArrayList list = new ArrayList();
      InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.US_ASCII);
      BufferedReader reader = new BufferedReader(inputStreamReader);

      while (true) {
         String str = reader.readLine();
         if (str == null) {
            return (String[])list.toArray(new String[list.size()]);
         }

         list.add(str);
      }
   }

   public static boolean isNotify64BitJava() {
      return notify64BitJava;
   }

   public static int[] toPrimitive(Integer[] nums) {
      if (nums == null) {
         return null;
      } else if (nums.length == 0) {
         return new int[0];
      } else {
         int[] ints = new int[nums.length];

         for (int i = 0; i < ints.length; i++) {
            ints[i] = nums[i];
         }

         return ints;
      }
   }

   public static boolean isTreesSmart() {
      return gameSettings.ofTrees == 4;
   }

   public static GlVersion getGlVersion() {
      if (glVersion == null) {
         glVersion = parseGlVersion(GL11.glGetString(7938), null);
         if (glVersion == null) {
            glVersion = getGlVersionLwjgl();
         }

         if (glVersion == null) {
            glVersion = new GlVersion(1, 0);
         }
      }

      return glVersion;
   }

   public static boolean isSunMoonEnabled() {
      return gameSettings.ofSunMoon;
   }

   public static boolean isDrippingWaterLava() {
      return gameSettings.ofDrippingWaterLava;
   }

   public static boolean isFireworkParticles() {
      return gameSettings.ofFireworkParticles;
   }

   public static boolean isDynamicHandLight() {
      return isDynamicLights() && (!isShaderPackLoaded() || Shaders.isDynamicHandLight());
   }

   public static Minecraft getMc() {
      return mc;
   }

   public static boolean isConnectedModels() {
      return false;
   }

   private static Set getDisplayModeDimensions(DisplayMode[] displayModes2) {
      HashSet set = new HashSet();

      for (int i = 0; i < displayModes2.length; i++) {
         DisplayMode displayMode = displayModes2[i];
         set.add(new Dimension(displayMode.getWidth(), displayMode.getHeight()));
      }

      return set;
   }

   public static String getBuild() {
      if (build == null) {
         try {
            InputStream resourceAsStream = Config.class.getResourceAsStream("/buildof.txt");
            if (resourceAsStream == null) {
               return null;
            }

            build = readLines(resourceAsStream)[0];
         } catch (Exception e) {
            warn(e.getClass().getName() + ": " + e.getMessage());
            build = "";
         }
      }

      return build;
   }

   public static Boolean parseBoolean(String str, Boolean bool) {
      try {
         if (str == null) {
            return bool;
         } else {
            str = str.trim().toLowerCase();
            return str.equals("true") ? Boolean.TRUE : (str.equals("false") ? Boolean.FALSE : bool);
         }
      } catch (NumberFormatException e) {
         return bool;
      }
   }

   public static boolean isAntialiasingConfigured() {
      return getGameSettings().ofAaLevel > 0;
   }

   public static boolean isVbo() {
      return OpenGlHelper.useVbo();
   }

   public static String readInputStream(InputStream in, String str) throws IOException {
      InputStreamReader inputStreamReader = new InputStreamReader(in, str);
      BufferedReader reader = new BufferedReader(inputStreamReader);
      StringBuffer sb = new StringBuffer();

      while (true) {
         String str2 = reader.readLine();
         if (str2 == null) {
            return sb.toString();
         }

         sb.append(str2);
         sb.append("\n");
      }
   }

   public static float limit3(float f, float f2, float f3) {
      return f < f2 ? f2 : (f > f3 ? f3 : f);
   }

   public static int getChunkViewDistance() {
      return gameSettings == null ? 10 : gameSettings.renderDistanceChunks;
   }

   public static boolean isCustomGuis() {
      return gameSettings.ofCustomGuis;
   }

   public static boolean isRandomEntities() {
      return gameSettings.ofRandomEntities;
   }

   public static boolean isSmoothBiomes() {
      return gameSettings.ofSmoothBiomes;
   }

   public static String arrayToString4(Object[] array, String str) {
      if (array == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(array.length * 5);

         for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            if (i > 0) {
               sb.append(str);
            }

            sb.append(obj);
         }

         return sb.toString();
      }
   }

   public static IResourcePack getDefiningResourcePack(ResourceLocation location) {
      ResourcePackRepository resourcePackRepository = mc.getResourcePackRepository();
      IResourcePack resourcePackInstance = resourcePackRepository.getResourcePackInstance();
      if (resourcePackInstance != null && resourcePackInstance.resourceExists(location)) {
         return resourcePackInstance;
      } else {
         List repositoryEntries = resourcePackRepository.repositoryEntries;

         for (int i = repositoryEntries.size() - 1; i >= 0; i--) {
            IResourcePack resourcePack = ((ResourcePackRepository.Entry)repositoryEntries.get(i)).getResourcePack();
            if (resourcePack.resourceExists(location)) {
               return resourcePack;
            }
         }

         return getDefaultResourcePack().resourceExists(location) ? getDefaultResourcePack() : null;
      }
   }

   public static String getVersion() {
      return "OptiFine_1.8.9_HD_U_M5";
   }

   public static InputStream getResourceStream(ResourceLocation location) throws IOException {
      return getResourceStream(mc.getResourceManager(), location);
   }

   public static boolean isSunTexture() {
      return isSunMoonEnabled() && (!isShaderPackLoaded() || Shaders.isSun());
   }

   public static boolean isGeneratedWater() {
      return gameSettings.ofAnimatedWater == 1;
   }

   public static boolean isAnimatedLava() {
      return gameSettings.ofAnimatedLava != 2;
   }

   public static boolean isFogOn() {
      return gameSettings.ofFogType != 3;
   }

   public static DisplayMode getLargestDisplayMode() {
      DisplayMode[] displayModes2 = getDisplayModes();
      if (displayModes2 != null && displayModes2.length >= 1) {
         DisplayMode displayMode = displayModes2[displayModes2.length - 1];
         return desktopDisplayMode.getWidth() > displayMode.getWidth() ? desktopDisplayMode : (desktopDisplayMode.getWidth() == displayMode.getWidth() && desktopDisplayMode.getHeight() > displayMode.getHeight() ? desktopDisplayMode : displayMode);
      } else {
         return desktopDisplayMode;
      }
   }

   public static int getUpdatesPerFrame() {
      return gameSettings.ofChunkUpdates;
   }

   public static boolean isCloudsOff() {
      return gameSettings.ofClouds != 0 ? gameSettings.ofClouds == 3 : (isShaderPackLoaded() && !Shaders.shaderPackClouds.isDefault() ? Shaders.shaderPackClouds.isOff() : texturePackClouds != 0 && texturePackClouds == 3);
   }

   public static void showGuiMessage(String str, String str2) {
      GuiMessage guiMessage = new GuiMessage(mc.currentScreen, str, str2);
      mc.displayGuiScreen((GuiScreen)guiMessage);
   }

   public static boolean isAnimatedRedstone() {
      return gameSettings.ofAnimatedRedstone;
   }

   public static boolean isCloudsFancy() {
      return gameSettings.ofClouds != 0 ? gameSettings.ofClouds == 2 : (isShaderPackLoaded() && !Shaders.shaderPackClouds.isDefault() ? Shaders.shaderPackClouds.isFancy() : (texturePackClouds != 0 ? texturePackClouds == 2 : gameSettings.fancyGraphics));
   }

   public static boolean isSmoothWorld() {
      return gameSettings.ofSmoothWorld;
   }

   public static boolean isUseAlphaFunc() {
      return getAlphaFuncLevel() > DEF_ALPHA_FUNC_LEVEL + 1.0E-5F;
   }

   public static IResource getResource(ResourceLocation location) throws IOException {
      return mc.getResourceManager().getResource(location);
   }

   public static boolean isStarsEnabled() {
      return gameSettings.ofStars;
   }

   public static boolean isAntialiasing() {
      return getAntialiasingLevel() > 0;
   }

   public static void updateThreadPriorities() {
      updateAvailableProcessors();
      if (isSingleProcessor()) {
         if (isSmoothWorld()) {
            minecraftThread.setPriority(10);
            setThreadPriority("Server thread", 1);
         } else {
            minecraftThread.setPriority(5);
            setThreadPriority("Server thread", 5);
         }
      } else {
         minecraftThread.setPriority(10);
         setThreadPriority("Server thread", 5);
      }
   }

   public static Dimension getFullscreenDimension() {
      if (desktopDisplayMode == null) {
         return null;
      } else if (gameSettings == null) {
         return new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight());
      } else {
         String ofFullscreenMode = gameSettings.ofFullscreenMode;
         if (ofFullscreenMode.equals("Default")) {
            return new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight());
         } else {
            String[] strings = tokenize(ofFullscreenMode, " x");
            return strings.length < 2 ? new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight()) : new Dimension(parseInt(strings[0], -1), parseInt(strings[1], -1));
         }
      }
   }

   public static Object[] removeObjectFromArray(Object[] array, Object obj) {
      ArrayList list = new ArrayList<>(Arrays.asList(array));
      list.remove(obj);
      return collectionToArray(list, array.getClass().getComponentType());
   }

   public static boolean isOcclusionAvailable() {
      return occlusionAvailable;
   }

   public static boolean isMoonTexture() {
      return isSunMoonEnabled() && (!isShaderPackLoaded() || Shaders.isMoon());
   }

   public static boolean isBetterGrassFancy() {
      return gameSettings.ofBetterGrass == 2;
   }

   public static int getBitsJre() {
      String[] strings = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};

      for (int i = 0; i < strings.length; i++) {
         String property = System.getProperty(strings[i]);
         if (property != null && property.contains("64")) {
            return 64;
         }
      }

      return 32;
   }

   public static boolean isFromDefaultResourcePack(ResourceLocation location) {
      return getDefiningResourcePack(location) == getDefaultResourcePack();
   }

   public static void warn(String str) {
      LOGGER.warn("[OptiFine] " + str);
   }

   public static void setNewRelease(String str) {
      newRelease = str;
   }

   public static String arrayToString3(int[] ints, String str) {
      if (ints == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(ints.length * 5);

         for (int i = 0; i < ints.length; i++) {
            int n = ints[i];
            if (i > 0) {
               sb.append(str);
            }

            sb.append(n);
         }

         return sb.toString();
      }
   }

   public static String listToString(List list, String str) {
      if (list == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(list.size() * 5);

         for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (i > 0) {
               sb.append(str);
            }

            sb.append(obj);
         }

         return sb.toString();
      }
   }

   public static String[] readLines2(File file) throws IOException {
      return readLines(new FileInputStream(file));
   }

   public static int getAvailableProcessors() {
      return availableProcessors;
   }

   public static int parseInt(String str, int n) {
      try {
         if (str == null) {
            return n;
         } else {
            str = str.trim();
            return Integer.parseInt(str);
         }
      } catch (NumberFormatException e) {
         return n;
      }
   }

   public static boolean isAnimatedTextures() {
      return gameSettings.ofAnimatedTextures;
   }

   public static boolean isMinecraftThread() {
      return Thread.currentThread() == minecraftThread;
   }

   public static boolean isClearWater() {
      return gameSettings.ofClearWater;
   }

   public static boolean isSingleProcessor() {
      return getAvailableProcessors() <= 1;
   }

   public static boolean isLazyChunkLoading() {
      return gameSettings.ofLazyChunkLoading;
   }

   public static void checkDisplayMode() {
      try {
         if (mc.isFullScreen()) {
            if (fullscreenModeChecked) {
               return;
            }

            fullscreenModeChecked = true;
            desktopModeChecked = false;
            DisplayMode displayMode = Display.getDisplayMode();
            Dimension fullscreenDimension = getFullscreenDimension();
            if (fullscreenDimension == null) {
               return;
            }

            if (displayMode.getWidth() == fullscreenDimension.width && displayMode.getHeight() == fullscreenDimension.height) {
               return;
            }

            DisplayMode displayMode2 = getDisplayMode(fullscreenDimension);
            if (displayMode2 == null) {
               return;
            }

            Display.setDisplayMode(displayMode2);
            mc.displayWidth = Display.getDisplayMode().getWidth();
            mc.displayHeight = Display.getDisplayMode().getHeight();
            if (mc.displayWidth <= 0) {
               mc.displayWidth = 1;
            }

            if (mc.displayHeight <= 0) {
               mc.displayHeight = 1;
            }

            if (mc.currentScreen != null) {
               ScaledResolution sr = new ScaledResolution(mc);
               int scaledWidth = sr.getScaledWidth();
               int scaledHeight = sr.getScaledHeight();
               mc.currentScreen.setWorldAndResolution(mc, scaledWidth, scaledHeight);
            }

            updateFramebufferSize();
            Display.setFullscreen(true);
            mc.gameSettings.updateVSync();
            GlStateManager.enableTexture2D();
         } else {
            if (desktopModeChecked) {
               return;
            }

            desktopModeChecked = true;
            fullscreenModeChecked = false;
            mc.gameSettings.updateVSync();
            Display.update();
            GlStateManager.enableTexture2D();
            if (!Minecraft.isRunningOnMac) {
               Display.setResizable(false);
               Display.setResizable(true);
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         gameSettings.ofFullscreenMode = "Default";
         gameSettings.saveOfOptions();
      }
   }

   public static boolean equalsOne2(Object obj, Object[] array) {
      if (array == null) {
         return false;
      } else {
         for (int i = 0; i < array.length; i++) {
            if (equals(obj, array[i])) {
               return true;
            }
         }

         return false;
      }
   }

   public static String arrayToString(float[] floats, String str) {
      if (floats == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(floats.length * 5);

         for (int i = 0; i < floats.length; i++) {
            float f = floats[i];
            if (i > 0) {
               sb.append(str);
            }

            sb.append(f);
         }

         return sb.toString();
      }
   }

   public static void sleep(long l) {
      try {
         Thread.sleep(l);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   public static boolean isNaturalTextures() {
      return gameSettings.ofNaturalTextures;
   }

   public static String getGlErrorString(int n) {
      switch (n) {
         case 0:
            return "No error";
         case 1280:
            return "Invalid enum";
         case 1281:
            return "Invalid value";
         case 1282:
            return "Invalid operation";
         case 1283:
            return "Stack overflow";
         case 1284:
            return "Stack underflow";
         case 1285:
            return "Out of memory";
         case 1286:
            return "Invalid framebuffer operation";
         default:
            return "Unknown";
      }
   }

   public static ModelManager getModelManager() {
      return mc.getRenderItem().modelManager;
   }

   public static boolean isRainSplash() {
      return gameSettings.ofRainSplash;
   }

   public static int intHash(int n) {
      n = n ^ 61 ^ n >> 16;
      n += n << 3;
      n ^= n >> 4;
      n *= 668265261;
      return n ^ n >> 15;
   }

   public static boolean hasResource(ResourceLocation location) {
      if (location == null) {
         return false;
      } else {
         return getDefiningResourcePack(location) != null;
      }
   }

   public static String getNewRelease() {
      return newRelease;
   }

   public static TextureManager getTextureManager() {
      return mc.getTextureManager();
   }

   public static void writeFile(File file, String str) throws IOException {
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      fileOutputStream.write(str.getBytes(StandardCharsets.US_ASCII));
      fileOutputStream.close();
   }

   public static boolean isShaderPackLoaded() {
      return Shaders.shaderPackLoaded;
   }

   public static boolean isAnimatedFlame() {
      return gameSettings.ofAnimatedFlame;
   }

   public static DefaultResourcePack getDefaultResourcePack() {
      if (defaultResourcePackLazy == null) {
         Minecraft minecraft = Minecraft.getMinecraft();
         defaultResourcePackLazy = minecraft.mcDefaultResourcePack;
         if (defaultResourcePackLazy == null) {
            ResourcePackRepository resourcePackRepository = minecraft.getResourcePackRepository();
            if (resourcePackRepository != null) {
               defaultResourcePackLazy = (DefaultResourcePack)resourcePackRepository.rprDefaultResourcePack;
            }
         }
      }

      return defaultResourcePackLazy;
   }

   public static DynamicTexture getMojangLogoTexture(DynamicTexture dynamicTexture) {
      try {
         InputStream resourceStream = getResourceStream(new ResourceLocation("textures/gui/title/mojang.png"));
         if (resourceStream == null) {
            return dynamicTexture;
         } else {
            BufferedImage image = ImageIO.read(resourceStream);
            return image == null ? dynamicTexture : new DynamicTexture(image);
         }
      } catch (Exception e) {
         warn(e.getClass().getName() + ": " + e.getMessage());
         return dynamicTexture;
      }
   }

   public static boolean isIntegratedServerRunning() {
      return mc.getIntegratedServer() != null && mc.isIntegratedServerRunning();
   }

   public static boolean isBetterSnow() {
      return gameSettings.ofBetterSnow;
   }

   public static boolean isDynamicUpdates() {
      return gameSettings.ofChunkUpdatesDynamic;
   }

   public static boolean isCustomEntityModels() {
      return gameSettings.ofCustomEntityModels;
   }

   public static byte[] readAll(InputStream in) throws IOException {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      byte[] bytes = new byte[1024];

      while (true) {
         int n = in.read(bytes);
         if (n < 0) {
            in.close();
            return byteArrayOutputStream.toByteArray();
         }

         byteArrayOutputStream.write(bytes, 0, n);
      }
   }

   public static boolean isConnectedTextures() {
      return gameSettings.ofConnectedTextures != 3;
   }

   public static GameSettings getGameSettings() {
      return gameSettings;
   }

   public static boolean isVignetteEnabled() {
      return (!isShaderPackLoaded() || Shaders.isVignette()) && (gameSettings.ofVignette == 0 ? gameSettings.fancyGraphics : gameSettings.ofVignette == 2);
   }

   public static int getBitsOs() {
      return System.getenv("ProgramFiles(X86)") != null ? 64 : 32;
   }

   public static TextureMap getTextureMap() {
      return getMc().getTextureMapBlocks();
   }

   public static boolean isGeneratedLava() {
      return gameSettings.ofAnimatedLava == 1;
   }

   public static void checkGlError(String str) {
      int n = GlStateManager.glGetError();
      if (n != 0 && GlErrors.isEnabled(n)) {
         String glErrorString = getGlErrorString(n);
         error(String.format("OpenGL error: %s (%s), at: %s", n, glErrorString, str));
         if (isShowGlErrors() && TimedEvent.isActive("ShowGlError", 10000L)) {
            String str3 = I18n.format("of.message.openglError", new Object[]{n, glErrorString});
            mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(str3));
         }
      }
   }

   public static void setNotify64BitJava(boolean flag) {
      notify64BitJava = flag;
   }

   public static boolean isAnimatedPortal() {
      return gameSettings.ofAnimatedPortal;
   }

   public static boolean between2(float f, float f2, float f3) {
      return f >= f2 && f <= f3;
   }

   public static boolean isTimeDayOnly() {
      return gameSettings.ofTime == 1;
   }

   public static void checkDisplaySettings() {
      int antialiasingLevel2 = getAntialiasingLevel();
      if (antialiasingLevel2 > 0) {
         DisplayMode displayMode = Display.getDisplayMode();
         dbg("FSAA Samples: " + antialiasingLevel2);

         try {
            Display.destroy();
            Display.setDisplayMode(displayMode);
            Display.create(new PixelFormat().withDepthBits(24).withSamples(antialiasingLevel2));
            Display.setResizable(false);
            Display.setResizable(true);
         } catch (LWJGLException e) {
            warn("Error setting FSAA: " + antialiasingLevel2 + "x");
            e.printStackTrace();

            try {
               Display.setDisplayMode(displayMode);
               Display.create(new PixelFormat().withDepthBits(24));
               Display.setResizable(false);
               Display.setResizable(true);
            } catch (LWJGLException e2) {
               e2.printStackTrace();

               try {
                  Display.setDisplayMode(displayMode);
                  Display.create();
                  Display.setResizable(false);
                  Display.setResizable(true);
               } catch (LWJGLException e3) {
                  e3.printStackTrace();
               }
            }
         }

         if (!Minecraft.isRunningOnMac && getDefaultResourcePack() != null) {
            InputStream in = null;
            InputStream in2 = null;

            try {
               in = getDefaultResourcePack().getInputStreamAssets(new ResourceLocation("icons/icon_16x16.png"));
               in2 = getDefaultResourcePack().getInputStreamAssets(new ResourceLocation("icons/icon_32x32.png"));
               if (in != null && in2 != null) {
                  Display.setIcon(new ByteBuffer[]{readIconImage(in), readIconImage(in2)});
               }
            } catch (IOException e4) {
               warn("Error setting window icon: " + e4.getClass().getName() + ": " + e4.getMessage());
            } finally {
               IOUtils.closeQuietly(in);
               IOUtils.closeQuietly(in2);
            }
         }
      }
   }

   public static boolean isSkyEnabled() {
      return gameSettings.ofSky;
   }

   public static int getAnisotropicFilterLevel() {
      return gameSettings.ofAfLevel;
   }

   public static boolean isFogFast() {
      return gameSettings.ofFogType == 1;
   }

   public static void drawFps() {
      int debugFPS = Minecraft.getDebugFPS();
      String updates = getUpdates(mc.debug);
      int countActiveRenderers = mc.renderGlobal.getCountActiveRenderers();
      int countEntitiesRendered = mc.renderGlobal.getCountEntitiesRendered();
      int n = mc.renderGlobal.getCountTileEntitiesRendered();
      String str = debugFPS + "/" + getFpsMin() + " fps, C: " + countActiveRenderers + ", E: " + countEntitiesRendered + "+" + n + ", U: " + updates;
      mc.fontRendererObj.drawString(str, 2, 2, -2039584);
   }

   public static String listToString(List list) {
      return listToString(list, ", ");
   }

   public static String arrayToString(int[] ints) {
      return arrayToString3(ints, ", ");
   }

   public static String arrayToString2(boolean[] flags, String str) {
      if (flags == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(flags.length * 5);

         for (int i = 0; i < flags.length; i++) {
            boolean flag = flags[i];
            if (i > 0) {
               sb.append(str);
            }

            sb.append(flag);
         }

         return sb.toString();
      }
   }

   public static String arrayToString3(Object[] array) {
      return arrayToString4(array, ", ");
   }

   public static int getAntialiasingLevel() {
      return antialiasingLevel;
   }

   public static void log(String str) {
      dbg(str);
   }

   public static int getMipmapLevels() {
      return gameSettings.mipmapLevels;
   }

   public static String normalize(String str) {
      return str == null ? "" : str;
   }

   public static void checkInitialized() {
      if (!initialized && Display.isCreated()) {
         initialized = true;
         checkOpenGlCaps();
         // HARDENED: OptiFine startup HTTP version check disabled.
         // startVersionCheckThread();
      }
   }

   public static boolean isSmoothFps() {
      return gameSettings.ofSmoothFps;
   }

   public static int getMipmapType() {
      switch (gameSettings.ofMipmapType) {
         case 0:
            return 9986;
         case 1:
            return 9986;
         case 2:
            if (isMultiTexture()) {
               return 9985;
            }

            return 9986;
         case 3:
            if (isMultiTexture()) {
               return 9987;
            }

            return 9986;
         default:
            return 9986;
      }
   }

   public static String getVersionDebug() {
      StringBuffer sb = new StringBuffer(32);
      if (isDynamicLights()) {
         sb.append("DL: ");
         sb.append(DynamicLights.getCount());
         sb.append(", ");
      }

      sb.append("OptiFine_1.8.9_HD_U_M5");
      String shaderPackName = Shaders.getShaderPackName();
      if (shaderPackName != null) {
         sb.append(", ");
         sb.append(shaderPackName);
      }

      return sb.toString();
   }

   public static Object[] collectionToArray(Collection items, Class clazz) {
      if (items == null) {
         return null;
      } else if (clazz == null) {
         return null;
      } else if (clazz.isPrimitive()) {
         throw new IllegalArgumentException("Can not make arrays with primitive elements (int, double), element class: " + clazz);
      } else {
         return items.toArray((Object[])Array.newInstance(clazz, items.size()));
      }
   }

   public static boolean isFogOff() {
      return gameSettings.ofFogType == 3;
   }

   private static String getUpdates(String str) {
      int n = str.indexOf(40);
      if (n < 0) {
         return "";
      } else {
         int n2 = str.indexOf(32, n);
         return n2 < 0 ? "" : str.substring(n + 1, n2);
      }
   }

   private static void startVersionCheckThread() {
      new VersionCheckThread().start();
   }

   public static IntBuffer createDirectIntBuffer(int n) {
      return GLAllocation.createDirectByteBuffer(n << 2).asIntBuffer();
   }

   public static int[] addIntToArray(int[] ints, int n) {
      return addIntsToArray(ints, new int[]{n});
   }

   public static boolean equalsOne(int n, int[] ints) {
      for (int i = 0; i < ints.length; i++) {
         if (ints[i] == n) {
            return true;
         }
      }

      return false;
   }

   public static DisplayMode getDisplayMode(Dimension dimension) throws LWJGLException {
      DisplayMode[] displayModes2 = getDisplayModes();

      for (int i = 0; i < displayModes2.length; i++) {
         DisplayMode displayMode = displayModes2[i];
         if (displayMode.getWidth() == dimension.width && displayMode.getHeight() == dimension.height) {
            return displayMode;
         }
      }

      return desktopDisplayMode;
   }

   public static boolean isRainOff() {
      return gameSettings.ofRain == 3;
   }

   public static String readInputStream(InputStream in) throws IOException {
      return readInputStream(in, "ASCII");
   }

   public static boolean isTimeNightOnly() {
      return gameSettings.ofTime == 2;
   }

   public static boolean isWaterParticles() {
      return gameSettings.ofWaterParticles;
   }

   public static String arrayToString2(float[] floats) {
      return arrayToString(floats, ", ");
   }

   public static boolean isAlternateBlocks() {
      return gameSettings.ofAlternateBlocks;
   }

   public static boolean isFastRender() {
      return gameSettings.ofFastRender;
   }

   public static void error(String str) {
      LOGGER.error("[OptiFine] " + str);
   }

   public static int getRandom(BlockPos pos, int n) {
      int n2 = intHash(n + 37);
      n2 = intHash(n2 + pos.getX());
      n2 = intHash(n2 + pos.getZ());
      return intHash(n2 + pos.getY());
   }

   public static float getAlphaFuncLevel() {
      return DEF_ALPHA_FUNC_LEVEL;
   }

   public static boolean isMipmaps() {
      return gameSettings.mipmapLevels > 0;
   }

   public static IResourceManager getResourceManager() {
      return mc.getResourceManager();
   }

   public static float limitTo1(float f) {
      return f < 0.0F ? 0.0F : (f > 1.0F ? 1.0F : f);
   }

   public static boolean isAnimatedSmoke() {
      return gameSettings.ofAnimatedSmoke;
   }

   public static Object[] addObjectsToArray(Object[] array, Object[] array2) {
      if (array == null) {
         throw new NullPointerException("The given array is NULL");
      } else if (array2.length == 0) {
         return array;
      } else {
         int length = array.length;
         int n = length + array2.length;
         Object[] array3 = (Object[])Array.newInstance(array.getClass().getComponentType(), n);
         System.arraycopy(array, 0, array3, 0, length);
         System.arraycopy(array2, 0, array3, length, array2.length);
         return array3;
      }
   }

   public static boolean isSwampColors() {
      return gameSettings.ofSwampColors;
   }

   public static boolean isTreesFancy() {
      return gameSettings.ofTrees == 0 ? gameSettings.fancyGraphics : gameSettings.ofTrees != 1;
   }

   public static boolean isWeatherEnabled() {
      return gameSettings.ofWeather;
   }

   private static DisplayMode getDisplayMode(DisplayMode[] displayModes2, DisplayMode displayMode) {
      if (displayMode != null) {
         for (int i = 0; i < displayModes2.length; i++) {
            DisplayMode displayMode2 = displayModes2[i];
            if (displayMode2.getBitsPerPixel() == displayMode.getBitsPerPixel() && displayMode2.getFrequency() == displayMode.getFrequency()) {
               return displayMode2;
            }
         }
      }

      if (displayModes2.length <= 0) {
         return null;
      } else {
         Arrays.sort(displayModes2, new DisplayModeComparator());
         return displayModes2[displayModes2.length - 1];
      }
   }

   public static boolean isAnisotropicFiltering() {
      return getAnisotropicFilterLevel() > 1;
   }

   public static String getOpenGlVersionString() {
      GlVersion glVersion2 = getGlVersion();
      return glVersion2.getMajor() + "." + glVersion2.getMinor() + "." + glVersion2.getRelease();
   }

   public static void updateFramebufferSize() {
      mc.getFramebuffer().createBindFramebuffer(mc.displayWidth, mc.displayHeight);
      if (mc.entityRenderer != null) {
         mc.entityRenderer.updateShaderGroupSize(mc.displayWidth, mc.displayHeight);
      }

      mc.loadingScreen = new LoadingScreenRenderer(mc);
   }

   public static boolean isVoidParticles() {
      return gameSettings.ofVoidParticles;
   }

   public static boolean isCustomItems() {
      return gameSettings.ofCustomItems;
   }

   public static boolean isShowGlErrors() {
      return gameSettings.ofShowGlErrors;
   }

   public static boolean isDynamicFov() {
      return gameSettings.ofDynamicFov;
   }

   public static boolean isAnimatedWater() {
      return gameSettings.ofAnimatedWater != 2;
   }

   public static void updateAvailableProcessors() {
      availableProcessors = Runtime.getRuntime().availableProcessors();
   }

   public static void checkNull(Object obj, String str) throws NullPointerException {
      if (obj == null) {
         throw new NullPointerException(str);
      }
   }

   public static boolean isCustomFonts() {
      return gameSettings.ofCustomFonts;
   }

   public static boolean hasResource(IResourceManager resourceManager, ResourceLocation location) {
      try {
         return resourceManager.getResource(location) != null;
      } catch (IOException e) {
         return false;
      }
   }

   public static int getScreenshotSize() {
      return gameSettings.ofScreenshotSize;
   }

   public static void initGameSettings(GameSettings gameSettings2) {
      if (gameSettings == null) {
         gameSettings = gameSettings2;
         desktopDisplayMode = Display.getDesktopDisplayMode();
         updateAvailableProcessors();
      }
   }

   public static String[] tokenize(String str, String str2) {
      StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
      ArrayList list = new ArrayList();

      while (stringTokenizer.hasMoreTokens()) {
         list.add(stringTokenizer.nextToken());
      }

      return (String[])list.toArray(new String[list.size()]);
   }

   public static boolean isFogFancy() {
      return isFancyFogAvailable() && gameSettings.ofFogType == 2;
   }

   public static boolean isPortalParticles() {
      return gameSettings.ofPortalParticles;
   }

   public static boolean isAnimatedFire() {
      return gameSettings.ofAnimatedFire;
   }

   public static boolean isTranslucentBlocksFancy() {
      return gameSettings.ofTranslucentBlocks == 0 ? gameSettings.fancyGraphics : gameSettings.ofTranslucentBlocks == 2;
   }

   private static String[] splitRelease(String str) {
      if (str != null && str.length() > 0) {
         Matcher matcher = Pattern.compile("([A-Z])([0-9]+)(.*)").matcher(str);
         if (!matcher.matches()) {
            return new String[]{"", "", ""};
         } else {
            String str2 = normalize(matcher.group(1));
            String str3 = normalize(matcher.group(2));
            String str4 = normalize(matcher.group(3));
            return new String[]{str2, str3, str4};
         }
      } else {
         return new String[]{"", "", ""};
      }
   }

   public static Object[] addObjectToArray(Object[] array, Object obj, int n) {
      ArrayList list = new ArrayList<>(Arrays.asList(array));
      list.add(n, obj);
      return list.toArray((Object[])Array.newInstance(array.getClass().getComponentType(), list.size()));
   }

   public static int getFpsMin() {
      if (mc.debug == mcDebugLast) {
         return fpsMinLast;
      } else {
         mcDebugLast = mc.debug;
         FrameTimer frameTimer2 = mc.getFrameTimer2();
         long[] frames = frameTimer2.getFrames();
         int index = frameTimer2.getIndex();
         int lastIndex = frameTimer2.getLastIndex();
         if (index == lastIndex) {
            return fpsMinLast;
         } else {
            int debugFPS = Minecraft.getDebugFPS();
            if (debugFPS <= 0) {
               debugFPS = 1;
            }

            long l2 = (long)(1.0 / (double)debugFPS * 1.0E9);
            long l3 = 0L;

            for (int i = MathHelper.normalizeAngle(index - 1, frames.length);
               i != lastIndex && (double)l3 < 1.0E9;
               i = MathHelper.normalizeAngle(i - 1, frames.length)
            ) {
               long l4 = frames[i];
               if (l4 > l2) {
                  l2 = l4;
               }

               l3 += l4;
            }

            double d = (double)l2 / 1.0E9;
            fpsMinLast = (int)(1.0 / d);
            return fpsMinLast;
         }
      }
   }

   private static void checkOpenGlCaps() {
      log("");
      log(getVersion());
      log("Build: " + getBuild());
      log("OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"));
      log("Java: " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
      log("VM: " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
      log("LWJGL: " + Sys.getVersion());
      openGlVersion = GL11.glGetString(7938);
      openGlRenderer = GL11.glGetString(7937);
      openGlVendor = GL11.glGetString(7936);
      log("OpenGL: " + openGlRenderer + ", version " + openGlVersion + ", " + openGlVendor);
      log("OpenGL Version: " + getOpenGlVersionString());
      if (!GLContext.getCapabilities().OpenGL12) {
         log("OpenGL Mipmap levels: Not available (GL12.GL_TEXTURE_MAX_LEVEL)");
      }

      fancyFogAvailable = GLContext.getCapabilities().GL_NV_fog_distance;
      if (!fancyFogAvailable) {
         log("OpenGL Fancy fog: Not available (GL_NV_fog_distance)");
      }

      occlusionAvailable = GLContext.getCapabilities().GL_ARB_occlusion_query;
      if (!occlusionAvailable) {
         log("OpenGL Occlussion culling: Not available (GL_ARB_occlusion_query)");
      }

      int glMaximumTextureSize = TextureUtils.getGLMaximumTextureSize();
      dbg("Maximum texture size: " + glMaximumTextureSize + "x" + glMaximumTextureSize);
   }

   public static void initDisplay() {
      checkInitialized();
      antialiasingLevel = gameSettings.ofAaLevel;
      checkDisplaySettings();
      checkDisplayMode();
      minecraftThread = Thread.currentThread();
      updateThreadPriorities();
      Shaders.startup(Minecraft.getMinecraft());
   }

   public static boolean openWebLink(URI uri) {
      try {
         Desktop.getDesktop().browse(uri);
         return true;
      } catch (Exception e) {
         warn("Error opening link: " + uri);
         warn(e.getClass().getName() + ": " + e.getMessage());
         return false;
      }
   }

   public static int[] addIntsToArray(int[] ints, int[] ints2) {
      if (ints != null && ints2 != null) {
         int length = ints.length;
         int[] ints3 = new int[length + ints2.length];
         System.arraycopy(ints, 0, ints3, 0, length);
         System.arraycopy(ints2, 0, ints3, 0 + length, ints2.length);
         return ints3;
      } else {
         throw new NullPointerException("The given array is NULL");
      }
   }

   public static boolean isRainFancy() {
      return gameSettings.ofRain == 0 ? gameSettings.fancyGraphics : gameSettings.ofRain == 2;
   }

   public static RenderGlobal getRenderGlobal() {
      return mc.renderGlobal;
   }

   public static IResourcePack[] getResourcePacks() {
      ResourcePackRepository resourcePackRepository = mc.getResourcePackRepository();
      List repositoryEntries = resourcePackRepository.getRepositoryEntries();
      ArrayList list = new ArrayList();

      for (ResourcePackRepository.Entry entry : (Iterable<ResourcePackRepository.Entry>)(repositoryEntries)) {
         list.add(entry.getResourcePack());
      }

      if (resourcePackRepository.getResourcePackInstance() != null) {
         list.add(resourcePackRepository.getResourcePackInstance());
      }

      return (IResourcePack[])list.toArray(new IResourcePack[list.size()]);
   }

   private static ByteBuffer readIconImage(InputStream in) throws IOException {
      BufferedImage image = ImageIO.read(in);
      int[] rgb = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
      ByteBuffer buf = ByteBuffer.allocate(4 * rgb.length);

      for (int n : rgb) {
         buf.putInt(n << 8 | n >> 24 & 0xFF);
      }

      ((Buffer)buf).flip();
      return buf;
   }

   public static boolean isPotionParticles() {
      return gameSettings.ofPotionParticles;
   }

   public static Object[] addObjectToArray(Object[] array, Object obj) {
      if (array == null) {
         throw new NullPointerException("The given array is NULL");
      } else {
         int length = array.length;
         int n = length + 1;
         Object[] array2 = (Object[])Array.newInstance(array.getClass().getComponentType(), n);
         System.arraycopy(array, 0, array2, 0, length);
         array2[length] = obj;
         return array2;
      }
   }

   private static void setThreadPriority(String str, int n) {
      try {
         ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
         if (threadGroup == null) {
            return;
         }

         Thread[] threads = new Thread[(threadGroup.activeCount() + 10) * 2];
         threadGroup.enumerate(threads, false);

         for (int i = 0; i < threads.length; i++) {
            Thread thread = threads[i];
            if (thread != null && thread.getName().startsWith(str)) {
               thread.setPriority(n);
            }
         }
      } catch (Throwable e) {
         warn(e.getClass().getName() + ": " + e.getMessage());
      }
   }

   public static int getMinecraftVersionInt() {
      if (minecraftVersionInt < 0) {
         String[] strings = tokenize("1.8.9", ".");
         int n = 0;
         if (strings.length > 0) {
            n += 10000 * parseInt(strings[0], 0);
         }

         if (strings.length > 1) {
            n += 100 * parseInt(strings[1], 0);
         }

         if (strings.length > 2) {
            n += parseInt(strings[2], 0);
         }

         minecraftVersionInt = n;
      }

      return minecraftVersionInt;
   }

   public static boolean isEmissiveTextures() {
      return gameSettings.ofEmissiveTextures;
   }

   public static int compareRelease(String str, String str2) {
      String[] strings = splitRelease(str);
      String[] strings2 = splitRelease(str2);
      String str3 = strings[0];
      String str4 = strings2[0];
      if (!str3.equals(str4)) {
         return str3.compareTo(str4);
      } else {
         int n = parseInt(strings[1], -1);
         int n2 = parseInt(strings2[1], -1);
         if (n != n2) {
            return n - n2;
         } else {
            String str5 = strings[2];
            String str6 = strings2[2];
            if (!str5.equals(str6)) {
               if (str5.isEmpty()) {
                  return 1;
               }

               if (str6.isEmpty()) {
                  return -1;
               }
            }

            return str5.compareTo(str6);
         }
      }
   }

   public static DisplayMode[] getDisplayModes() {
      if (displayModes == null) {
         try {
            DisplayMode[] availableDisplayModes = Display.getAvailableDisplayModes();
            Set displayModeDimensions = getDisplayModeDimensions(availableDisplayModes);
            ArrayList list = new ArrayList();

            for (Dimension dimension : (Iterable<Dimension>)(displayModeDimensions)) {
               DisplayMode displayMode = getDisplayMode(getDisplayModes(availableDisplayModes, dimension), desktopDisplayMode);
               if (displayMode != null) {
                  list.add(displayMode);
               }
            }

            DisplayMode[] displayModes3 = (DisplayMode[])list.toArray(new DisplayMode[list.size()]);
            Arrays.sort(displayModes3, new DisplayModeComparator());
            return displayModes3;
         } catch (Exception e) {
            e.printStackTrace();
            displayModes = new DisplayMode[]{desktopDisplayMode};
         }
      }

      return displayModes;
   }

   public static String[] getOpenGlExtensions() {
      if (openGlExtensions == null) {
         openGlExtensions = detectOpenGlExtensions();
      }

      return openGlExtensions;
   }

   public static void detail(String str) {
      if (logDetail) {
         LOGGER.info("[OptiFine] " + str);
      }
   }

   public static GlVersion parseGlVersion(String str, GlVersion glVersion2) {
      try {
         if (str == null) {
            return glVersion2;
         } else {
            Matcher matcher = Pattern.compile("([0-9]+)\\.([0-9]+)(\\.([0-9]+))?(.+)?").matcher(str);
            if (!matcher.matches()) {
               return glVersion2;
            } else {
               int n = Integer.parseInt(matcher.group(1));
               int n2 = Integer.parseInt(matcher.group(2));
               int n3 = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 0;
               String str2 = matcher.group(5);
               return new GlVersion(n, n2, n3, str2);
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         return glVersion2;
      }
   }

   public static boolean isSameOne(Object obj, Object[] array) {
      if (array == null) {
         return false;
      } else {
         for (int i = 0; i < array.length; i++) {
            if (obj == array[i]) {
               return true;
            }
         }

         return false;
      }
   }

   private static String[] detectOpenGlExtensions() {
      try {
         if (getGlVersion().getMajor() >= 3) {
            int n = GL11.glGetInteger(33309);
            if (n > 0) {
               String[] strings = new String[n];

               for (int i = 0; i < n; i++) {
                  strings[i] = GL30.glGetStringi(7939, i);
               }

               return strings;
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      try {
         return GL11.glGetString(7939).split(" ");
      } catch (Exception e2) {
         e2.printStackTrace();
         return new String[0];
      }
   }

   public static float parseFloat(String str, float f) {
      try {
         if (str == null) {
            return f;
         } else {
            str = str.trim();
            return Float.parseFloat(str);
         }
      } catch (NumberFormatException e) {
         return f;
      }
   }

   public static InputStream getResourceStream(IResourceManager resourceManager, ResourceLocation location) throws IOException {
      IResource resource = resourceManager.getResource(location);
      return resource == null ? null : resource.getInputStream();
   }

   public static boolean isAnimatedTerrain() {
      return gameSettings.ofAnimatedTerrain;
   }

   public static void updateTexturePackClouds() {
      texturePackClouds = 0;
      IResourceManager resourceManager = getResourceManager();
      if (resourceManager != null) {
         try {
            InputStream inputStream = resourceManager.getResource(new ResourceLocation("mcpatcher/color.properties")).getInputStream();
            if (inputStream == null) {
               return;
            }

            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            propertiesOrdered.load(inputStream);
            inputStream.close();
            String property = propertiesOrdered.getProperty("clouds");
            if (property == null) {
               return;
            }

            dbg("Texture pack clouds: " + property);
            property = property.toLowerCase();
            if (property.equals("fast")) {
               texturePackClouds = 1;
            }

            if (property.equals("fancy")) {
               texturePackClouds = 2;
            }

            if (property.equals("off")) {
               texturePackClouds = 3;
            }
         } catch (Exception e) {
         }
      }
   }

   public static boolean parseBoolean2(String str, boolean flag) {
      try {
         if (str == null) {
            return flag;
         } else {
            str = str.trim();
            return Boolean.parseBoolean(str);
         }
      } catch (NumberFormatException e) {
         return flag;
      }
   }

   public static boolean between(int n, int n2, int n3) {
      return n >= n2 && n <= n3;
   }

   public static float getFogStart() {
      return gameSettings.ofFogStart;
   }

   public static boolean isRenderRegions() {
      return gameSettings.ofRenderRegions;
   }

   public static String getResourcePackNames() {
      if (mc.getResourcePackRepository() == null) {
         return "";
      } else {
         IResourcePack[] resourcePacks = getResourcePacks();
         if (resourcePacks.length <= 0) {
            return getDefaultResourcePack().getPackName();
         } else {
            String[] strings = new String[resourcePacks.length];

            for (int i = 0; i < resourcePacks.length; i++) {
               strings[i] = resourcePacks[i].getPackName();
            }

            return arrayToString3((Object[])strings);
         }
      }
   }

   public static boolean isDroppedItemsFancy() {
      return gameSettings.ofDroppedItems == 0 ? gameSettings.fancyGraphics : gameSettings.ofDroppedItems == 2;
   }

   public static boolean isAnimatedExplosion() {
      return gameSettings.ofAnimatedExplosion;
   }

   private static GlVersion getGlVersionLwjgl() {
      return GLContext.getCapabilities().OpenGL44
         ? new GlVersion(4, 4)
         : (
            GLContext.getCapabilities().OpenGL43
               ? new GlVersion(4, 3)
               : (
                  GLContext.getCapabilities().OpenGL42
                     ? new GlVersion(4, 2)
                     : (
                        GLContext.getCapabilities().OpenGL41
                           ? new GlVersion(4, 1)
                           : (
                              GLContext.getCapabilities().OpenGL40
                                 ? new GlVersion(4, 0)
                                 : (
                                    GLContext.getCapabilities().OpenGL33
                                       ? new GlVersion(3, 3)
                                       : (
                                          GLContext.getCapabilities().OpenGL32
                                             ? new GlVersion(3, 2)
                                             : (
                                                GLContext.getCapabilities().OpenGL31
                                                   ? new GlVersion(3, 1)
                                                   : (
                                                      GLContext.getCapabilities().OpenGL30
                                                         ? new GlVersion(3, 0)
                                                         : (
                                                            GLContext.getCapabilities().OpenGL21
                                                               ? new GlVersion(2, 1)
                                                               : (
                                                                  GLContext.getCapabilities().OpenGL20
                                                                     ? new GlVersion(2, 0)
                                                                     : (
                                                                        GLContext.getCapabilities().OpenGL15
                                                                           ? new GlVersion(1, 5)
                                                                           : (
                                                                              GLContext.getCapabilities().OpenGL14
                                                                                 ? new GlVersion(1, 4)
                                                                                 : (
                                                                                    GLContext.getCapabilities().OpenGL13
                                                                                       ? new GlVersion(1, 3)
                                                                                       : (
                                                                                          GLContext.getCapabilities().OpenGL12
                                                                                             ? new GlVersion(1, 2)
                                                                                             : (
                                                                                                GLContext.getCapabilities().OpenGL11
                                                                                                   ? new GlVersion(1, 1)
                                                                                                   : new GlVersion(1, 0)
                                                                                             )
                                                                                       )
                                                                                 )
                                                                           )
                                                                     )
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public static boolean equals(Object obj, Object obj2) {
      return obj == obj2 || obj != null && obj.equals(obj2);
   }

   public static boolean isTimeDefault() {
      return gameSettings.ofTime == 0;
   }

   public static boolean isConnectedTexturesFancy() {
      return gameSettings.ofConnectedTextures == 2;
   }

   public static DisplayMode getDesktopDisplayMode() {
      return desktopDisplayMode;
   }

   public static boolean isDynamicLightsFast() {
      return gameSettings.ofDynamicLights == 1;
   }

   public static GlVersion getGlslVersion() {
      if (glslVersion == null) {
         glslVersion = parseGlVersion(GL11.glGetString(35724), null);
         if (glslVersion == null) {
            glslVersion = new GlVersion(1, 10);
         }
      }

      return glslVersion;
   }

   public static boolean isDynamicLights() {
      return gameSettings.ofDynamicLights != 3;
   }

   public static double limit2(double d, double d2, double d3) {
      return d < d2 ? d2 : (d > d3 ? d3 : d);
   }

   public static void dbg(String str) {
      LOGGER.info("[OptiFine] " + str);
   }

   public static boolean isTrue(Boolean bool) {
      return bool != null && bool;
   }

   public static int limit(int n, int n2, int n3) {
      return n < n2 ? n2 : (n > n3 ? n3 : n);
   }

   public static boolean isQuadsToTriangles() {
      return isShaderPackLoaded() && !Shaders.canRenderQuads();
   }

   public static String readFile(File file) throws IOException {
      return readInputStream(new FileInputStream(file), "ASCII");
   }

   public static boolean isFancyFogAvailable() {
      return fancyFogAvailable;
   }

}
