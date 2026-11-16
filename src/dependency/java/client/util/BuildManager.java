package client.util;

import augustus.modes.movement.MovementVersioncheckMode;
import client.event.PreUpdateProcessor;
import client.render.TexturepackiconManager;
import client.setting.GameSettings;
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
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.ResourceLanguageManager;
import net.minecraft.item.IronDust;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
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

public class BuildManager {
   public static final String labelText2 = "OptiFine";
   public static final String labelText7 = "1.8.9";
   public static final String labelText10 = "HD_U";
   public static final String labelText4 = "M5";
   public static final String labelText8 = "OptiFine_1.8.9_HD_U_M5";
   public static final Float memberRef = 0.1F;
   public static final boolean enabled3 = System.getProperty("log.detail", "false").equals("true");
   public static final Logger logger = LogManager.getLogger();
   public static String labelText5 = null;
   public static String labelText = null;
   public static String labelText6 = null;
   public static String[] stringArray = null;
   public static DampenerDispatcher dampenerDispatcher2 = null;
   public static DampenerDispatcher dampenerDispatcher = null;
   public static int count4 = -1;
   public static boolean enabled8 = false;
   public static boolean enabled4 = false;
   public static boolean enabled10 = false;
   public static boolean enabled6 = false;
   public static boolean enabled = false;
   public static float factor;
   public static String labelText9 = null;
   public static String labelText11 = null;
   public static boolean enabled9 = false;
   public static GameSettings gameSettings = null;
   public static final Minecraft mc = Minecraft.getMinecraft();
   public static boolean enabled7 = false;
   public static Thread thread = null;
   public static DisplayMode displayMode = null;
   public static DisplayMode[] displayModeArray = null;
   public static int count = 0;
   public static int count5 = 0;
   public static int count3 = 0;
   public static boolean enabled5 = false;
   public static boolean enabled2 = false;
   public static IReloadableResourceManager iReloadableResourceManager = null;
   public static String labelText3 = null;
   public static int count2 = 0;

   private static void updateState7() {
      applyText("");
      applyText(getText2());
      applyText("Build: " + getText4());
      applyText("OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"));
      applyText("Java: " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
      applyText("VM: " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
      applyText("LWJGL: " + Sys.getVersion());
      labelText5 = GL11.glGetString(7938);
      labelText = GL11.glGetString(7937);
      labelText6 = GL11.glGetString(7936);
      applyText("OpenGL: " + labelText + ", version " + labelText5 + ", " + labelText6);
      applyText("OpenGL Version: " + getText());
      if (!GLContext.getCapabilities().OpenGL12) {
         applyText("OpenGL Mipmap levels: Not available (GL12.GL_TEXTURE_MAX_LEVEL)");
      }

      enabled8 = GLContext.getCapabilities().GL_NV_fog_distance;
      if (!enabled8) {
         applyText("OpenGL Fancy fog: Not available (GL_NV_fog_distance)");
      }

      enabled4 = GLContext.getCapabilities().GL_ARB_occlusion_query;
      if (!enabled4) {
         applyText("OpenGL Occlussion culling: Not available (GL_ARB_occlusion_query)");
      }

      int count6 = InternalPngManager.getCount();
      applyText4("Maximum texture size: " + count6 + "x" + count6);
   }

   public static boolean matchesTargetByTarget(Object entry, Object[] entryArray) {
      if (entryArray == null) {
         return false;
      } else {
         for (int index = 0; index < entryArray.length; index++) {
            Object entry2 = entryArray[index];
            if (entry == entry2) {
               return true;
            }
         }

         return false;
      }
   }

   public static DisplayMode getDisplayModeAsDisplayMode() {
      return displayMode;
   }

   public static String resolveText(List list, String text) {
      if (list == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(list.size() * 5);

         for (int index = 0; index < list.size(); index++) {
            Object entry = list.get(index);
            if (index > 0) {
               builder.append(text);
            }

            builder.append(entry);
         }

         return builder.toString();
      }
   }

   public static int getCount7() {
      return gameSettings == null ? 10 : gameSettings.indexOffset16;
   }

   public static int computeCount(int count6) {
      count6 = count6 ^ 61 ^ count6 >> 16;
      count6 += count6 << 3;
      count6 ^= count6 >> 4;
      count6 *= 668265261;
      return count6 ^ count6 >> 15;
   }

   public static IResource resolveIResourceByResourceLocation(ResourceLocation location) throws IOException {
      return mc.getIResourceManager().resolveIResourceByResourceLocation(location);
   }

   public static boolean isActive77() {
      return isActive59() && (!canExecute() || PreEndHandManager.isActive4());
   }

   public static IResourceManager getIResourceManager() {
      return mc.getIResourceManager();
   }

   public static boolean matchesResourceLocationByResourceLocation(ResourceLocation location) {
      return resolvePreUpdateProcessor(location) == getIReloadableResourceManager();
   }

   public static DisplayMode resolveDisplayMode(Dimension dimension) throws LWJGLException {
      DisplayMode[] displayModeArray2 = getDisplayModeArray();

      for (int index = 0; index < displayModeArray2.length; index++) {
         DisplayMode displayMode2 = displayModeArray2[index];
         if (displayMode2.getWidth() == dimension.width && displayMode2.getHeight() == dimension.height) {
            return displayMode2;
         }
      }

      return displayMode;
   }

   public static Minecraft getMc() {
      return mc;
   }

   public static boolean matchesText(String text, boolean flag) {
      try {
         if (text == null) {
            return flag;
         } else {
            text = text.trim();
            return Boolean.parseBoolean(text);
         }
      } catch (NumberFormatException numberFormatException) {
         return flag;
      }
   }

   public static GameSettings getGameSettings() {
      return gameSettings;
   }

   public static String[] resolveTextArray(File file) throws IOException {
      return resolveTextArray(new FileInputStream(file));
   }

   private static String resolveText(String text) {
      int count6 = text.indexOf(40);
      if (count6 < 0) {
         return "";
      } else {
         int count7 = text.indexOf(32, count6);
         return count7 < 0 ? "" : text.substring(count6 + 1, count7);
      }
   }

   public static void applyText2(String text) {
      logger.error("[OptiFine] " + text);
   }

   public static void updateState3() {
      int limitFramerate = Minecraft.getLimitFramerate();
      String text = resolveText(mc.labelText4);
      int size = mc.renderGlobal.size();
      int indexOffset = mc.renderGlobal.getIndexOffset();
      int count6 = mc.renderGlobal.getCount8();
      text = limitFramerate + "/" + getCount9() + " fps, C: " + size + ", E: " + indexOffset + "+" + count6 + ", U: " + text;
      mc.fontRendererObj.getIndex(text, 2, 2, -2039584);
   }

   public static boolean isActive2() {
      return gameSettings.indexOffset121 == 0 ? !gameSettings.state5 : gameSettings.indexOffset121 == 4;
   }

   public static int getCount3() {
      return count;
   }

   public static float computeFactor(String text, float factor2) {
      try {
         if (text == null) {
            return factor2;
         } else {
            text = text.trim();
            return Float.parseFloat(text);
         }
      } catch (NumberFormatException numberFormatException) {
         return factor2;
      }
   }

   public static void applyTarget(Object entry, String text) throws NullPointerException {
      if (entry == null) {
         throw new NullPointerException(text);
      }
   }

   public static String getText() {
      DampenerDispatcher dampenerDispatcherAsDampenerDispatch = getDampenerDispatcherAsDampenerDispatch();
      return dampenerDispatcherAsDampenerDispatch.getCountAsIndex() + "." + dampenerDispatcherAsDampenerDispatch.getCount2() + "." + dampenerDispatcherAsDampenerDispatch.getCount3();
   }

   public static float computeFactor(float factor2) {
      return factor2 < 0.0F ? 0.0F : (factor2 > 1.0F ? 1.0F : factor2);
   }

   public static String[] getTextArrayAsTextArray() {
      if (stringArray == null) {
         stringArray = getTextArray();
      }

      return stringArray;
   }

   public static void applyTime(long value) {
      try {
         Thread.sleep(value);
      } catch (InterruptedException interruptedException) {
         interruptedException.printStackTrace();
      }
   }

   public static int computeCount(BlockPos blockPos, int count6) {
      int count7 = computeCount(count6 + 37);
      count7 = computeCount(count7 + blockPos.getX());
      count7 = computeCount(count7 + blockPos.getZ());
      return computeCount(count7 + blockPos.getY());
   }

   public static boolean isActiveAsFlag() {
      return gameSettings.state34;
   }

   public static DampenerDispatcher getDampenerDispatcher() {
      if (dampenerDispatcher == null) {
         dampenerDispatcher = resolveDampenerDispatcher(GL11.glGetString(35724), null);
         if (dampenerDispatcher == null) {
            dampenerDispatcher = new DampenerDispatcher(1, 10);
         }
      }

      return dampenerDispatcher;
   }

   private static ByteBuffer resolveByteBuffer(InputStream inputStream) throws IOException {
      BufferedImage bufferedImage = ImageIO.read(inputStream);
      int[] rgb = bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null, 0, bufferedImage.getWidth());
      ByteBuffer buffer = ByteBuffer.allocate(4 * rgb.length);

      for (int count6 : rgb) {
         buffer.putInt(count6 << 8 | count6 >> 24 & 0xFF);
      }

      ((Buffer)buffer).flip();
      return buffer;
   }

   public static String resolveText(List list) {
      return resolveText(list, ", ");
   }

   public static String resolveText(int[] countArray) {
      return resolveText(countArray, ", ");
   }

   public static byte[] resolveByteArray(InputStream inputStream) throws IOException {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      byte[] byteValueArray = new byte[1024];

      while (true) {
         int count6 = inputStream.read(byteValueArray);
         if (count6 < 0) {
            inputStream.close();
            return byteArrayOutputStream.toByteArray();
         }

         byteArrayOutputStream.write(byteValueArray, 0, count6);
      }
   }

   public static String resolveText(Object[] entryArray) {
      return resolveText(entryArray, ", ");
   }

   public static String resolveText(float[] factorArray) {
      return resolveText(factorArray, ", ");
   }

   public static void updateState() {
      updateState6();
      count = gameSettings.indexOffset89;
      updateState5();
      updateState2();
      thread = Thread.currentThread();
      updateState9();
      PreEndHandManager.applyMinecraftByMinecraft(Minecraft.getMinecraft());
   }

   public static void applyTextByText(String text) {
      if (enabled3) {
         logger.info("[OptiFine] " + text);
      }
   }

   public static int getCount5() {
      return count5;
   }

   public static String resolveText(int count6) {
      switch (count6) {
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

   public static int computeCount(String text, int count6) {
      try {
         if (text == null) {
            return count6;
         } else {
            text = text.trim();
            return Integer.parseInt(text);
         }
      } catch (NumberFormatException numberFormatException) {
         return count6;
      }
   }

   public static boolean checkIndex(int count6, int count7, int count8) {
      return count6 >= count7 && count6 <= count8;
   }

   public static boolean isActive() {
      return gameSettings.state78;
   }

   public static int getCount() {
      return gameSettings.indexOffset18;
   }

   private static String[] getTextArray() {
      try {
         DampenerDispatcher dampenerDispatcherAsDampenerDispatch = getDampenerDispatcherAsDampenerDispatch();
         if (dampenerDispatcherAsDampenerDispatch.getCountAsIndex() >= 3) {
            int count6 = GL11.glGetInteger(33309);
            if (count6 > 0) {
               String[] textArray = new String[count6];

               for (int index = 0; index < count6; index++) {
                  textArray[index] = GL30.glGetStringi(7939, index);
               }

               return textArray;
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      try {
         String text = GL11.glGetString(7939);
         return text.split(" ");
      } catch (Exception exception2) {
         exception2.printStackTrace();
         return new String[0];
      }
   }

   public static String getLabelText11() {
      return labelText11;
   }

   public static void applyText(String text) {
      applyText4(text);
   }

   public static Object[] resolveTargetArray(Object[] entryArray, Object[] entryArray2) {
      if (entryArray == null) {
         throw new NullPointerException("The given array is NULL");
      } else if (entryArray2.length == 0) {
         return entryArray;
      } else {
         int length = entryArray.length;
         int count6 = length + entryArray2.length;
         Object[] entryArray3 = (Object[])Array.newInstance(entryArray.getClass().getComponentType(), count6);
         System.arraycopy(entryArray, 0, entryArray3, 0, length);
         System.arraycopy(entryArray2, 0, entryArray3, length, entryArray2.length);
         return entryArray3;
      }
   }

   public static IntBuffer resolveIntBuffer(int count6) {
      return VectorMatcher.resolveByteBuffer(count6 << 2).asIntBuffer();
   }

   private static void start() {
      new MovementVersioncheckMode().start();
   }

   private static DisplayMode[] resolveDisplayModeArray(DisplayMode[] displayModeArray2, Dimension dimension) {
      ArrayList list = new ArrayList();

      for (int index = 0; index < displayModeArray2.length; index++) {
         DisplayMode displayMode2 = displayModeArray2[index];
         if ((double)displayMode2.getWidth() == dimension.getWidth() && (double)displayMode2.getHeight() == dimension.getHeight()) {
            list.add(displayMode2);
         }
      }

      return (DisplayMode[]) list.toArray(new DisplayMode[list.size()]);
   }

   public static DisplayMode[] getDisplayModeArray() {
      if (displayModeArray == null) {
         try {
            DisplayMode[] availableDisplayModes = Display.getAvailableDisplayModes();
            Set set = resolveSet(availableDisplayModes);
            ArrayList list = new ArrayList();

            for (Dimension dimension : (Iterable<Dimension>) (Iterable) (set)) {
               DisplayMode displayMode2 = resolveDisplayMode(resolveDisplayModeArray(availableDisplayModes, dimension), displayMode);
               if (displayMode2 != null) {
                  list.add(displayMode2);
               }
            }

            DisplayMode[] displayModeArray2 = (DisplayMode[]) list.toArray(new DisplayMode[list.size()]);
            Arrays.sort(displayModeArray2, new ExtendedComparatorAdapter());
            return displayModeArray2;
         } catch (Exception exception) {
            exception.printStackTrace();
            displayModeArray = new DisplayMode[]{displayMode};
         }
      }

      return displayModeArray;
   }

   public static Object[] resolveTargetArray(Object[] entryArray, Object entry, int count6) {
      ArrayList list = new ArrayList<>(Arrays.asList(entryArray));
      list.add(count6, entry);
      Object[] entryArray2 = (Object[])Array.newInstance(entryArray.getClass().getComponentType(), list.size());
      return list.toArray(entryArray2);
   }

   public static void applyFile(File file, String text) throws IOException {
      FileOutputStream outputStream = new FileOutputStream(file);
      byte[] bytes = text.getBytes(StandardCharsets.US_ASCII);
      outputStream.write(bytes);
      outputStream.close();
   }

   public static ListenerAdapter27 getListenerAdapter27() {
      return getMc().getListenerAdapter27();
   }

   public static double computeValue(double posX, double posY, double posZ) {
      return posX < posY ? posY : (posX > posZ ? posZ : posX);
   }

   public static String[] resolveTextArray(String text, String text2) {
      StringTokenizer stringTokenizer = new StringTokenizer(text, text2);
      ArrayList list = new ArrayList();

      while (stringTokenizer.hasMoreTokens()) {
         String text3 = stringTokenizer.nextToken();
         list.add(text3);
      }

      return (String[]) list.toArray(new String[list.size()]);
   }

   public static PreUpdateProcessor resolvePreUpdateProcessor(ResourceLocation location) {
      ResourceLanguageManager resourceLanguageManager = mc.getResourceLanguageManager();
      PreUpdateProcessor preUpdateProcessor = resourceLanguageManager.getPreUpdateProcessor();
      if (preUpdateProcessor != null && preUpdateProcessor.matchesResourceLocation(location)) {
         return preUpdateProcessor;
      } else {
         List list = resourceLanguageManager.list;

         for (int index = list.size() - 1; index >= 0; index--) {
            preUpdateProcessor = ((TexturepackiconManager)list.get(index)).getPreUpdateProcessor();
            if (preUpdateProcessor.matchesResourceLocation(location)) {
               return preUpdateProcessor;
            }
         }

         return getIReloadableResourceManager().matchesResourceLocation(location) ? getIReloadableResourceManager() : null;
      }
   }

   public static String[] getTextArray2() {
      DisplayMode[] displayModeArray2 = getDisplayModeArray();
      String[] textArray = new String[displayModeArray2.length];

      for (int index = 0; index < displayModeArray2.length; index++) {
         DisplayMode displayMode2 = displayModeArray2[index];
         String text = displayMode2.getWidth() + "x" + displayMode2.getHeight();
         textArray[index] = text;
      }

      return textArray;
   }

   public static Object[] resolveTargetArray(Collection collection, Class clazz) {
      if (collection == null) {
         return null;
      } else if (clazz == null) {
         return null;
      } else if (clazz.isPrimitive()) {
         throw new IllegalArgumentException("Can not make arrays with primitive elements (int, double), element class: " + clazz);
      } else {
         Object[] entryArray = (Object[])Array.newInstance(clazz, collection.size());
         return collection.toArray(entryArray);
      }
   }

   public static int getCount6() {
      return gameSettings.indexOffset40;
   }

   public static boolean matchesTarget(Object entry, Object[] entryArray) {
      if (entryArray == null) {
         return false;
      } else {
         for (int index = 0; index < entryArray.length; index++) {
            Object entry2 = entryArray[index];
            if (matchesTarget(entry, entry2)) {
               return true;
            }
         }

         return false;
      }
   }

   public static DisplayMode getDisplayMode() {
      DisplayMode[] displayModeArray2 = getDisplayModeArray();
      if (displayModeArray2 != null && displayModeArray2.length >= 1) {
         DisplayMode displayMode2 = displayModeArray2[displayModeArray2.length - 1];
         return displayMode.getWidth() > displayMode2.getWidth()
            ? displayMode
            : (displayMode.getWidth() == displayMode2.getWidth() && displayMode.getHeight() > displayMode2.getHeight() ? displayMode : displayMode2);
      } else {
         return displayMode;
      }
   }

   public static float floatValue() {
      return memberRef;
   }

   public static boolean matchesIndexArray(int count6, int[] countArray) {
      for (int index = 0; index < countArray.length; index++) {
         if (countArray[index] == count6) {
            return true;
         }
      }

      return false;
   }

   public static String getTextAsText() {
      StringBuffer builder = new StringBuffer(32);
      if (isActive28()) {
         builder.append("DL: ");
         builder.append(TargetEstimator.getCount());
         builder.append(", ");
      }

      builder.append("OptiFine_1.8.9_HD_U_M5");
      String text = PreEndHandManager.getText();
      if (text != null) {
         builder.append(", ");
         builder.append(text);
      }

      return builder.toString();
   }

   public static boolean isActive4() {
      return gameSettings.indexOffset74 == 2;
   }

   public static void updateState5() {
      int count6 = getCount3();
      if (count6 > 0) {
         DisplayMode displayMode2 = Display.getDisplayMode();
         applyText4("FSAA Samples: " + count6);

         try {
            Display.destroy();
            Display.setDisplayMode(displayMode2);
            Display.create(new PixelFormat().withDepthBits(24).withSamples(count6));
            Display.setResizable(false);
            Display.setResizable(true);
         } catch (LWJGLException lWJGLException) {
            applyText3("Error setting FSAA: " + count6 + "x");
            lWJGLException.printStackTrace();

            try {
               Display.setDisplayMode(displayMode2);
               Display.create(new PixelFormat().withDepthBits(24));
               Display.setResizable(false);
               Display.setResizable(true);
            } catch (LWJGLException lWJGLException2) {
               lWJGLException2.printStackTrace();

               try {
                  Display.setDisplayMode(displayMode2);
                  Display.create();
                  Display.setResizable(false);
                  Display.setResizable(true);
               } catch (LWJGLException lWJGLException3) {
                  lWJGLException3.printStackTrace();
               }
            }
         }

         if (!Minecraft.state3 && getIReloadableResourceManager() != null) {
            InputStream inputStream = null;
            InputStream inputStream2 = null;

            try {
               inputStream = getIReloadableResourceManager().resolveInputStream5(new ResourceLocation("icons/icon_16x16.png"));
               inputStream2 = getIReloadableResourceManager().resolveInputStream5(new ResourceLocation("icons/icon_32x32.png"));
               if (inputStream != null && inputStream2 != null) {
                  Display.setIcon(new ByteBuffer[]{resolveByteBuffer(inputStream), resolveByteBuffer(inputStream2)});
               }
            } catch (IOException ioException) {
               applyText3("Error setting window icon: " + ioException.getClass().getName() + ": " + ioException.getMessage());
            } finally {
               IOUtils.closeQuietly(inputStream);
               IOUtils.closeQuietly(inputStream2);
            }
         }
      }
   }

   public static boolean booleanValue(Boolean flag) {
      return flag != null && flag;
   }

   public static int computeCount(int count6, int count7, int count8) {
      return count6 < count7 ? count7 : (count6 > count8 ? count8 : count6);
   }

   public static String resolveText(InputStream inputStream) throws IOException {
      return resolveText(inputStream, "ASCII");
   }

   private static void applyText(String text, int count6) {
      try {
         ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
         if (threadGroup == null) {
            return;
         }

         Thread[] threadArray = new Thread[(threadGroup.activeCount() + 10) * 2];
         threadGroup.enumerate(threadArray, false);

         for (int index = 0; index < threadArray.length; index++) {
            Thread thread2 = threadArray[index];
            if (thread2 != null && thread2.getName().startsWith(text)) {
               thread2.setPriority(count6);
            }
         }
      } catch (Throwable throwable) {
         applyText3(throwable.getClass().getName() + ": " + throwable.getMessage());
      }
   }

   public static Boolean resolveBoolean(String text, Boolean flag) {
      try {
         if (text == null) {
            return flag;
         } else {
            text = text.trim().toLowerCase();
            return text.equals("true") ? Boolean.TRUE : (text.equals("false") ? Boolean.FALSE : flag);
         }
      } catch (NumberFormatException numberFormatException) {
         return flag;
      }
   }

   public static void applyText(String text, String text2) {
      CurveConverter curveConverter = new CurveConverter(mc.currentScreen, text, text2);
      mc.displayGuiScreen(curveConverter);
   }

   public static DampenerDispatcher getDampenerDispatcherAsDampenerDispatch() {
      if (dampenerDispatcher2 == null) {
         dampenerDispatcher2 = resolveDampenerDispatcher(GL11.glGetString(7938), null);
         if (dampenerDispatcher2 == null) {
            dampenerDispatcher2 = getDampenerDispatcher2();
         }

         if (dampenerDispatcher2 == null) {
            dampenerDispatcher2 = new DampenerDispatcher(1, 0);
         }
      }

      return dampenerDispatcher2;
   }

   public static PreUpdateProcessor[] getPreUpdateProcessorArray() {
      ResourceLanguageManager resourceLanguageManager = mc.getResourceLanguageManager();
      List list = resourceLanguageManager.copyOfAsList();
      ArrayList list2 = new ArrayList();

      for (TexturepackiconManager texturepackiconManager : (Iterable<TexturepackiconManager>) (Iterable) (list)) {
         list2.add(texturepackiconManager.getPreUpdateProcessor());
      }

      if (resourceLanguageManager.getPreUpdateProcessor() != null) {
         list2.add(resourceLanguageManager.getPreUpdateProcessor());
      }

      return (PreUpdateProcessor[]) list2.toArray(new PreUpdateProcessor[list2.size()]);
   }

   public static DynamicTexture resolveDynamicTextureByDynamicTexture(DynamicTexture dynamicTexture) {
      try {
         ResourceLocation location = new ResourceLocation("textures/gui/title/mojang.png");
         InputStream inputStream = resolveInputStream(location);
         if (inputStream == null) {
            return dynamicTexture;
         } else {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            return bufferedImage == null ? dynamicTexture : new DynamicTexture(bufferedImage);
         }
      } catch (Exception exception) {
         applyText3(exception.getClass().getName() + ": " + exception.getMessage());
         return dynamicTexture;
      }
   }

   public static boolean checkFloat(float posX, float posY, float posZ) {
      return posX >= posY && posX <= posZ;
   }

   public static boolean matchesURI(URI uri) {
      try {
         Desktop.getDesktop().browse(uri);
         return true;
      } catch (Exception exception) {
         applyText3("Error opening link: " + uri);
         applyText3(exception.getClass().getName() + ": " + exception.getMessage());
         return false;
      }
   }

   public static float getFactorAsFloat() {
      return gameSettings.scaleFactor21;
   }

   public static InputStream resolveInputStream(IResourceManager iResourceManager, ResourceLocation location) throws IOException {
      IResource iResource = iResourceManager.resolveIResourceByResourceLocation(location);
      return iResource == null ? null : iResource.getInputStream();
   }

   public static InputStream resolveInputStream(ResourceLocation location) throws IOException {
      return resolveInputStream(mc.getIResourceManager(), location);
   }

   public static int[] resolveIndexArray(int[] countArray, int count6) {
      return resolveIndexArray(countArray, new int[]{count6});
   }

   private static Set resolveSet(DisplayMode[] displayModeArray2) {
      HashSet set = new HashSet();

      for (int index = 0; index < displayModeArray2.length; index++) {
         DisplayMode displayMode2 = displayModeArray2[index];
         Dimension dimension = new Dimension(displayMode2.getWidth(), displayMode2.getHeight());
         set.add(dimension);
      }

      return set;
   }

   public static boolean canExecute() {
      return PreEndHandManager.enabled19;
   }

   public static int getCount9() {
      if (mc.labelText4 == labelText3) {
         return count2;
      } else {
         labelText3 = mc.labelText4;
         Profiler profiler = mc.getProfiler();
         long[] timestampArray = profiler.getTimestampArray();
         int count6 = profiler.getCount3();
         int count7 = profiler.getCount2();
         if (count6 == count7) {
            return count2;
         } else {
            int limitFramerate = Minecraft.getLimitFramerate();
            if (limitFramerate <= 0) {
               limitFramerate = 1;
            }

            long value = (long)(1.0 / (double)limitFramerate * 1.0E9);
            long value2 = 0L;

            for (int index = MathHelper.processAction2(count6 - 1, timestampArray.length);
               index != count7 && (double)value2 < 1.0E9;
               index = MathHelper.processAction2(index - 1, timestampArray.length)
            ) {
               long value3 = timestampArray[index];
               if (value3 > value) {
                  value = value3;
               }

               value2 += value3;
            }

            double offset = (double)value / 1.0E9;
            count2 = (int)(1.0 / offset);
            return count2;
         }
      }
   }

   public static boolean matchesIResourceManager(IResourceManager iResourceManager, ResourceLocation location) {
      try {
         return iResourceManager.resolveIResourceByResourceLocation(location) != null;
      } catch (IOException ioException) {
         return false;
      }
   }

   public static String getText4() {
      if (labelText9 == null) {
         try {
            InputStream resourceAsStream = BuildManager.class.getResourceAsStream("/buildof.txt");
            if (resourceAsStream == null) {
               return null;
            }

            labelText9 = resolveTextArray(resourceAsStream)[0];
         } catch (Exception exception) {
            applyText3(exception.getClass().getName() + ": " + exception.getMessage());
            labelText9 = "";
         }
      }

      return labelText9;
   }

   public static int[] resolveIndexArray(int[] countArray, int[] countArray2) {
      if (countArray != null && countArray2 != null) {
         int length = countArray.length;
         int[] countArray3 = new int[length + countArray2.length];
         System.arraycopy(countArray, 0, countArray3, 0, length);
         System.arraycopy(countArray2, 0, countArray3, 0 + length, countArray2.length);
         return countArray3;
      } else {
         throw new NullPointerException("The given array is NULL");
      }
   }

   public static String resolveText(float[] factorArray, String text) {
      if (factorArray == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(factorArray.length * 5);

         for (int index = 0; index < factorArray.length; index++) {
            float factor2 = factorArray[index];
            if (index > 0) {
               builder.append(text);
            }

            builder.append(factor2);
         }

         return builder.toString();
      }
   }

   public static void updateState9() {
      updateState8();
      if (isActive29()) {
         if (isActive52()) {
            thread.setPriority(10);
            applyText("Server thread", 1);
         } else {
            thread.setPriority(5);
            applyText("Server thread", 5);
         }
      } else {
         thread.setPriority(10);
         applyText("Server thread", 5);
      }
   }

   public static String resolveText(File file) throws IOException {
      return resolveText(new FileInputStream(file), "ASCII");
   }

   public static int computeCount(String text, String text2) {
      String[] textArray = resolveTextArray(text);
      String[] textArray2 = resolveTextArray(text2);
      String text3 = textArray[0];
      String text4 = textArray2[0];
      if (!text3.equals(text4)) {
         return text3.compareTo(text4);
      } else {
         int count6 = computeCount(textArray[1], -1);
         int count7 = computeCount(textArray2[1], -1);
         if (count6 != count7) {
            return count6 - count7;
         } else {
            String text5 = textArray[2];
            String text6 = textArray2[2];
            if (!text5.equals(text6)) {
               if (text5.isEmpty()) {
                  return 1;
               }

               if (text6.isEmpty()) {
                  return -1;
               }
            }

            return text5.compareTo(text6);
         }
      }
   }

   public static String resolveText(boolean[] flagArray, String text) {
      if (flagArray == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(flagArray.length * 5);

         for (int index = 0; index < flagArray.length; index++) {
            boolean flag = flagArray[index];
            if (index > 0) {
               builder.append(text);
            }

            builder.append(flag);
         }

         return builder.toString();
      }
   }

   public static void applyText5(String text) {
      int error = GlStateManager.getError();
      if (error != 0 && SuppressedManager.checkIndex(error)) {
         String text2 = resolveText(error);
         applyText2(String.format("OpenGL error: %s (%s), at: %s", error, text2, text));
         if (isActive20() && SupplementalMapEntry.matchesText("ShowGlError", 10000L)) {
            text2 = StatCollector.getText("of.message.openglError", new Object[]{error, text2});
            mc.ingameGUI.getInternalLoggerEntry().applyIChatComponent(new ChatComponentText(text2));
         }
      }
   }

   public static int getCount10() {
      String[] textArray = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};

      for (int index = 0; index < textArray.length; index++) {
         String property = System.getProperty(textArray[index]);
         if (property != null && property.contains("64")) {
            return 64;
         }
      }

      return 32;
   }

   public static DampenerDispatcher resolveDampenerDispatcher(String text, DampenerDispatcher dampenerDispatcher3) {
      try {
         if (text == null) {
            return dampenerDispatcher3;
         } else {
            Pattern pattern = Pattern.compile("([0-9]+)\\.([0-9]+)(\\.([0-9]+))?(.+)?");
            Matcher matcher = pattern.matcher(text);
            if (!matcher.matches()) {
               return dampenerDispatcher3;
            } else {
               int count6 = Integer.parseInt(matcher.group(1));
               int count7 = Integer.parseInt(matcher.group(2));
               int count8 = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 0;
               String text2 = matcher.group(5);
               return new DampenerDispatcher(count6, count7, count8, text2);
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
         return dampenerDispatcher3;
      }
   }

   private static DampenerDispatcher getDampenerDispatcher2() {
      return GLContext.getCapabilities().OpenGL44
         ? new DampenerDispatcher(4, 4)
         : (
            GLContext.getCapabilities().OpenGL43
               ? new DampenerDispatcher(4, 3)
               : (
                  GLContext.getCapabilities().OpenGL42
                     ? new DampenerDispatcher(4, 2)
                     : (
                        GLContext.getCapabilities().OpenGL41
                           ? new DampenerDispatcher(4, 1)
                           : (
                              GLContext.getCapabilities().OpenGL40
                                 ? new DampenerDispatcher(4, 0)
                                 : (
                                    GLContext.getCapabilities().OpenGL33
                                       ? new DampenerDispatcher(3, 3)
                                       : (
                                          GLContext.getCapabilities().OpenGL32
                                             ? new DampenerDispatcher(3, 2)
                                             : (
                                                GLContext.getCapabilities().OpenGL31
                                                   ? new DampenerDispatcher(3, 1)
                                                   : (
                                                      GLContext.getCapabilities().OpenGL30
                                                         ? new DampenerDispatcher(3, 0)
                                                         : (
                                                            GLContext.getCapabilities().OpenGL21
                                                               ? new DampenerDispatcher(2, 1)
                                                               : (
                                                                  GLContext.getCapabilities().OpenGL20
                                                                     ? new DampenerDispatcher(2, 0)
                                                                     : (
                                                                        GLContext.getCapabilities().OpenGL15
                                                                           ? new DampenerDispatcher(1, 5)
                                                                           : (
                                                                              GLContext.getCapabilities().OpenGL14
                                                                                 ? new DampenerDispatcher(1, 4)
                                                                                 : (
                                                                                    GLContext.getCapabilities().OpenGL13
                                                                                       ? new DampenerDispatcher(1, 3)
                                                                                       : (
                                                                                          GLContext.getCapabilities().OpenGL12
                                                                                             ? new DampenerDispatcher(1, 2)
                                                                                             : (
                                                                                                GLContext.getCapabilities().OpenGL11
                                                                                                   ? new DampenerDispatcher(1, 1)
                                                                                                   : new DampenerDispatcher(1, 0)
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

   public static IronDust getIronDust() {
      return mc.getRenderItem().ironDust;
   }

   public static String resolveText(InputStream inputStream, String text) throws IOException {
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, text);
      BufferedReader reader = new BufferedReader(inputStreamReader);
      StringBuffer builder = new StringBuffer();

      while (true) {
         String line = reader.readLine();
         if (line == null) {
            return builder.toString();
         }

         builder.append(line);
         builder.append("\n");
      }
   }

   public static String resolveText(int[] countArray, String text) {
      if (countArray == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(countArray.length * 5);

         for (int index = 0; index < countArray.length; index++) {
            int count6 = countArray[index];
            if (index > 0) {
               builder.append(text);
            }

            builder.append(count6);
         }

         return builder.toString();
      }
   }

   public static boolean isActive79() {
      return floatValue() > memberRef + 1.0E-5F;
   }

   public static void updateState10() {
      mc.getFramebuffer().applyIndex4(mc.displayWidth, mc.displayHeight);
      if (mc.entityRenderer != null) {
         mc.entityRenderer.applyIndex(mc.displayWidth, mc.displayHeight);
      }

      Minecraft mc2 = mc;
      LoadingScreenRenderer loadingScreenRenderer = new LoadingScreenRenderer(mc);
      mc2.loadingScreenRenderer = loadingScreenRenderer;
   }

   public static Object[] resolveTargetArrayByTargetArray(Object[] entryArray, Object entry) {
      if (entryArray == null) {
         throw new NullPointerException("The given array is NULL");
      } else {
         int length = entryArray.length;
         int count6 = length + 1;
         Object[] entryArray2 = (Object[])Array.newInstance(entryArray.getClass().getComponentType(), count6);
         System.arraycopy(entryArray, 0, entryArray2, 0, length);
         entryArray2[length] = entry;
         return entryArray2;
      }
   }

   public static boolean matchesTarget(Object entry, Object entry2) {
      return entry == entry2 || entry != null && entry.equals(entry2);
   }

   public static String[] resolveTextArray(InputStream inputStream) throws IOException {
      ArrayList list = new ArrayList();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
      BufferedReader reader = new BufferedReader(inputStreamReader);

      while (true) {
         String line = reader.readLine();
         if (line == null) {
            return (String[]) list.toArray(new String[list.size()]);
         }

         list.add(line);
      }
   }

   public static IReloadableResourceManager getIReloadableResourceManager() {
      if (iReloadableResourceManager == null) {
         Minecraft mc2 = Minecraft.getMinecraft();
         iReloadableResourceManager = mc2.iReloadableResourceManager;
         if (iReloadableResourceManager == null) {
            ResourceLanguageManager resourceLanguageManager = mc2.getResourceLanguageManager();
            if (resourceLanguageManager != null) {
               iReloadableResourceManager = (IReloadableResourceManager)resourceLanguageManager.preUpdateProcessor2;
            }
         }
      }

      return iReloadableResourceManager;
   }

   public static String getText2() {
      return "OptiFine_1.8.9_HD_U_M5";
   }

   public static void updateState6() {
      if (!enabled7 && Display.isCreated()) {
         enabled7 = true;
         updateState7();
         start();
      }
   }

   public static int getenv() {
      return System.getenv("ProgramFiles(X86)") != null ? 64 : 32;
   }

   public static TextureManager getTextureManagerAsTextureManager() {
      return mc.getTextureManager();
   }

   public static void applyText3(String text) {
      logger.warn("[OptiFine] " + text);
   }

   public static boolean isActive17() {
      return gameSettings.state77;
   }

   public static void applyGameSettings(GameSettings gameSettings2) {
      if (gameSettings == null) {
         gameSettings = gameSettings2;
         displayMode = Display.getDesktopDisplayMode();
         updateState8();
      }
   }

   public static String resolveText(Object[] entryArray, String text) {
      if (entryArray == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(entryArray.length * 5);

         for (int index = 0; index < entryArray.length; index++) {
            Object entry = entryArray[index];
            if (index > 0) {
               builder.append(text);
            }

            builder.append(entry);
         }

         return builder.toString();
      }
   }

   public static boolean matchesResourceLocation3(ResourceLocation location) {
      return location == null ? false : resolvePreUpdateProcessor(location) != null;
   }

   public static String getText3() {
      if (mc.getResourceLanguageManager() == null) {
         return "";
      } else {
         PreUpdateProcessor[] preUpdateProcessorArray = getPreUpdateProcessorArray();
         if (preUpdateProcessorArray.length <= 0) {
            return getIReloadableResourceManager().getName();
         } else {
            String[] textArray = new String[preUpdateProcessorArray.length];

            for (int index = 0; index < preUpdateProcessorArray.length; index++) {
               textArray[index] = preUpdateProcessorArray[index].getName();
            }

            return resolveText((Object[])textArray);
         }
      }
   }

   public static void applyText4(String text) {
      logger.info("[OptiFine] " + text);
   }

   private static String[] resolveTextArray(String text) {
      if (text != null && text.length() > 0) {
         Matcher matcher = Pattern.compile("([A-Z])([0-9]+)(.*)").matcher(text);
         if (!matcher.matches()) {
            return new String[]{"", "", ""};
         } else {
            String text2 = resolveTextByText(matcher.group(1));
            String text3 = resolveTextByText(matcher.group(2));
            String text4 = resolveTextByText(matcher.group(3));
            return new String[]{text2, text3, text4};
         }
      } else {
         return new String[]{"", "", ""};
      }
   }

   public static int getCount8() {
      return gameSettings.indexOffset118;
   }

   public static float getFactor() {
      return canExecute() && PreEndHandManager.factor29 >= 0.0F ? PreEndHandManager.factor29 : gameSettings.scaleFactor6;
   }

   public static void updateState8() {
      count5 = Runtime.getRuntime().availableProcessors();
   }

   public static boolean isActive78() {
      return canExecute() && !PreEndHandManager.isActive2();
   }

   public static float computeFactor(float posX, float posY, float posZ) {
      return posX < posY ? posY : (posX > posZ ? posZ : posX);
   }

   public static String resolveTextByText(String text) {
      return text == null ? "" : text;
   }

   private static DisplayMode resolveDisplayMode(DisplayMode[] displayModeArray2, DisplayMode displayMode2) {
      if (displayMode2 != null) {
         for (int index = 0; index < displayModeArray2.length; index++) {
            DisplayMode displayMode3 = displayModeArray2[index];
            if (displayMode3.getBitsPerPixel() == displayMode2.getBitsPerPixel() && displayMode3.getFrequency() == displayMode2.getFrequency()) {
               return displayMode3;
            }
         }
      }

      if (displayModeArray2.length <= 0) {
         return null;
      } else {
         Arrays.sort(displayModeArray2, new ExtendedComparatorAdapter());
         return displayModeArray2[displayModeArray2.length - 1];
      }
   }

   public static Dimension getDimension() {
      if (displayMode == null) {
         return null;
      } else if (gameSettings == null) {
         return new Dimension(displayMode.getWidth(), displayMode.getHeight());
      } else {
         String text = gameSettings.text42;
         if (text.equals("Default")) {
            return new Dimension(displayMode.getWidth(), displayMode.getHeight());
         } else {
            String[] textArray = resolveTextArray(text, " x");
            return textArray.length < 2
               ? new Dimension(displayMode.getWidth(), displayMode.getHeight())
               : new Dimension(computeCount(textArray[0], -1), computeCount(textArray[1], -1));
         }
      }
   }

   public static void setEnabled9(boolean enabled11) {
      enabled9 = enabled11;
   }

   public static int getCount4() {
      return gameSettings.indexOffset101;
   }

   public static RenderGlobal getRenderGlobalAsRenderGlobal() {
      return mc.renderGlobal;
   }

   public static void updateState4() {
      count3 = 0;
      IResourceManager iResourceManager = getIResourceManager();
      if (iResourceManager != null) {
         try {
            InputStream inputStream = iResourceManager.resolveIResourceByResourceLocation(new ResourceLocation("mcpatcher/color.properties")).getInputStream();
            if (inputStream == null) {
               return;
            }

            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            propertiesAdapter.load(inputStream);
            inputStream.close();
            String property = propertiesAdapter.getProperty("clouds");
            if (property == null) {
               return;
            }

            applyText4("Texture pack clouds: " + property);
            String text = property.toLowerCase();
            if (text.equals("fast")) {
               count3 = 1;
            }

            if (text.equals("fancy")) {
               count3 = 2;
            }

            if (text.equals("off")) {
               count3 = 3;
            }
         } catch (Exception exception) {
         }
      }
   }

   public static void setLabelText11(String labelText12) {
      labelText11 = labelText12;
   }

   public static boolean isActive3() {
      return (!canExecute() || PreEndHandManager.isActive14()) && (gameSettings.indexOffset111 == 0 ? gameSettings.state5 : gameSettings.indexOffset111 == 2);
   }

   public static Object[] resolveTargetArray(Object[] entryArray, Object entry) {
      ArrayList list = new ArrayList<>(Arrays.asList(entryArray));
      list.remove(entry);
      return resolveTargetArray(list, entryArray.getClass().getComponentType());
   }

   public static int[] resolveIndexArray(Integer[] countArray) {
      if (countArray == null) {
         return null;
      } else if (countArray.length == 0) {
         return new int[0];
      } else {
         int[] countArray2 = new int[countArray.length];

         for (int index = 0; index < countArray2.length; index++) {
            countArray2[index] = countArray[index];
         }

         return countArray2;
      }
   }

   public static boolean isActive74() {
      return mc.getIntegratedServer() != null && mc.isGamePaused();
   }

   public static boolean isActive60() {
      return gameSettings.state120;
   }

   public static boolean isActive18() {
      return gameSettings.indexOffset92 != 0
         ? gameSettings.indexOffset92 == 3
         : (
            canExecute() && !PreEndHandManager.auxiliaryManagerAdapter.isActive()
               ? PreEndHandManager.auxiliaryManagerAdapter.isActiveAsFlag()
               : count3 != 0 && count3 == 3
         );
   }

   public static boolean isActive56() {
      return gameSettings.state30;
   }

   public static boolean currentThread() {
      return Thread.currentThread() == thread;
   }

   public static boolean isActive21() {
      return gameSettings.state3;
   }

   public static boolean isActive43() {
      return gameSettings.state79;
   }

   public static boolean isActive72() {
      return gameSettings.state70;
   }

   public static boolean isActive36() {
      return gameSettings.indexOffset46 != 3;
   }

   public static boolean isActive44() {
      return gameSettings.indexOffset7 == 0 ? gameSettings.state5 : gameSettings.indexOffset7 == 2;
   }

   public static boolean isActive46() {
      return getCount() > 1 || getCount3() > 0;
   }

   public static boolean isActive41() {
      return gameSettings.state133;
   }

   public static boolean isActive19() {
      return gameSettings.indexOffset69 == 0 ? gameSettings.state5 : gameSettings.indexOffset69 == 2;
   }

   public static boolean isActive31() {
      return gameSettings.indexOffset57 == 1;
   }

   public static boolean isActive70() {
      return gameSettings.state37;
   }

   public static boolean isActive65() {
      return gameSettings.indexOffset74 == 1;
   }

   public static boolean isActive22() {
      return isEnabled8() && gameSettings.indexOffset57 == 2;
   }

   public static boolean isActive73() {
      return gameSettings.state66;
   }

   public static boolean isActive45() {
      return gameSettings.indexOffset118 > 0;
   }

   public static boolean isActive47() {
      return gameSettings.indexOffset95 != 3;
   }

   public static boolean canExecute2() {
      return getCount() > 1;
   }

   public static boolean isActive66() {
      return gameSettings.state24;
   }

   public static boolean isActive69() {
      return gameSettings.state86;
   }

   public static boolean canExecute3() {
      return gameSettings.state56;
   }

   public static boolean isEnabled8() {
      return enabled8;
   }

   public static boolean isEnabled9() {
      return enabled9;
   }

   public static boolean isActive26() {
      return OpenGlHelper.isActive();
   }

   public static boolean isActive37() {
      return gameSettings.indexOffset74 == 0;
   }

   public static boolean isActive29() {
      return getCount5() <= 1;
   }

   public static boolean isActive51() {
      return gameSettings.state125;
   }

   public static int getCount2() {
      switch (gameSettings.indexOffset43) {
         case 0:
            return 9986;
         case 1:
            return 9986;
         case 2:
            if (isActive46()) {
               return 9985;
            }

            return 9986;
         case 3:
            if (isActive46()) {
               return 9987;
            }

            return 9986;
         default:
            return 9986;
      }
   }

   public static void updateState2() {
      try {
         if (mc.isCallingFromMinecraftThread()) {
            if (enabled5) {
               return;
            }

            enabled5 = true;
            enabled2 = false;
            DisplayMode displayMode2 = Display.getDisplayMode();
            Dimension dimension = getDimension();
            if (dimension == null) {
               return;
            }

            if (displayMode2.getWidth() == dimension.width && displayMode2.getHeight() == dimension.height) {
               return;
            }

            displayMode2 = resolveDisplayMode(dimension);
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
               ScaledResolution scaledResolution = new ScaledResolution(mc);
               int scaledWidth = scaledResolution.getScaledWidth();
               int scaledHeight = scaledResolution.getScaledHeight();
               mc.currentScreen.applyMinecraft2(mc, scaledWidth, scaledHeight);
            }

            updateState10();
            Display.setFullscreen(true);
            mc.gameSettings.setVSyncEnabled();
            GlStateManager.disableTexture2D();
         } else {
            if (enabled2) {
               return;
            }

            enabled2 = true;
            enabled5 = false;
            mc.gameSettings.setVSyncEnabled();
            Display.update();
            GlStateManager.disableTexture2D();
            if (!Minecraft.state3) {
               Display.setResizable(false);
               Display.setResizable(true);
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
         gameSettings.text42 = "Default";
         gameSettings.updateState3();
      }
   }

   public static boolean isActive40() {
      return gameSettings.state1;
   }

   public static boolean isEnabled4() {
      return enabled4;
   }

   public static boolean isActive35() {
      return gameSettings.state44;
   }

   public static boolean isActive28() {
      return gameSettings.indexOffset61 != 3;
   }

   public static boolean isActive39() {
      return gameSettings.state;
   }

   public static boolean isActive27() {
      return gameSettings.state110;
   }

   public static boolean isActive64() {
      return gameSettings.indexOffset46 == 2;
   }

   public static boolean isActive38() {
      return gameSettings.state39;
   }

   public static boolean isActive59() {
      return gameSettings.state53;
   }

   public static boolean isActive57() {
      return gameSettings.state67;
   }

   public static boolean isActive62() {
      return gameSettings.state76;
   }

   public static boolean isActive71() {
      return gameSettings.indexOffset121 == 4;
   }

   public static boolean isActive34() {
      return gameSettings.state45;
   }

   public static boolean isActive20() {
      return gameSettings.state135;
   }

   public static boolean isActive42() {
      return gameSettings.state51;
   }

   public static int getCountAsIndex() {
      if (count4 < 0) {
         String[] textArray = resolveTextArray("1.8.9", ".");
         int count6 = 0;
         if (textArray.length > 0) {
            count6 += 10000 * computeCount(textArray[0], 0);
         }

         if (textArray.length > 1) {
            count6 += 100 * computeCount(textArray[1], 0);
         }

         if (textArray.length > 2) {
            count6 += computeCount(textArray[2], 0);
         }

         count4 = count6;
      }

      return count4;
   }

   public static boolean isActive53() {
      return gameSettings.state119;
   }

   public static boolean isActive55() {
      return gameSettings.state75;
   }

   public static boolean isActive76() {
      return gameSettings.state58;
   }

   public static boolean isActive32() {
      return gameSettings.state17;
   }

   public static boolean isActive61() {
      return gameSettings.state84;
   }

   public static boolean isActive33() {
      return gameSettings.state127;
   }

   public static boolean isActive24() {
      return gameSettings.indexOffset95 == 2;
   }

   public static boolean isActive68() {
      return gameSettings.state126;
   }

   public static boolean isActive58() {
      return isActive28() && (!canExecute() || PreEndHandManager.isActive6());
   }

   public static boolean isActive52() {
      return gameSettings.state83;
   }

   public static boolean isActive23() {
      return gameSettings.state31;
   }

   public static boolean isActive48() {
      return gameSettings.state122;
   }

   public static boolean isActive25() {
      return gameSettings.indexOffset15 != 2;
   }

   public static boolean isActive54() {
      return gameSettings.indexOffset25 != 2;
   }

   public static boolean isActive63() {
      return gameSettings.indexOffset57 != 3;
   }

   public static boolean isActive49() {
      return false;
   }

   public static boolean isActive50() {
      return gameSettings.state2;
   }

   public static boolean isActive30() {
      return gameSettings.state114;
   }

   public static boolean isActive75() {
      return gameSettings.state104;
   }

   public static boolean isActive67() {
      return gameSettings.indexOffset121 == 0 ? gameSettings.state5 : gameSettings.indexOffset121 != 1;
   }

   public static boolean isActive10() {
      return gameSettings.indexOffset25 == 1;
   }

   public static boolean isActive12() {
      return gameSettings.indexOffset == 0 ? gameSettings.state5 : gameSettings.indexOffset == 2;
   }

   public static boolean isActive5() {
      return gameSettings.state113;
   }

   public static boolean isActive11() {
      return gameSettings.state49;
   }

   public static boolean isActive13() {
      return isActive59() && (!canExecute() || PreEndHandManager.isActive());
   }

   public static boolean isActive6() {
      return getGameSettings().indexOffset89 > 0;
   }

   public static boolean isActive7() {
      return gameSettings.indexOffset57 == 3;
   }

   public static boolean isActive9() {
      return gameSettings.indexOffset92 != 0
         ? gameSettings.indexOffset92 == 2
         : (
            canExecute() && !PreEndHandManager.auxiliaryManagerAdapter.isActive()
               ? PreEndHandManager.auxiliaryManagerAdapter.isActive3()
               : (count3 != 0 ? count3 == 2 : gameSettings.state5)
         );
   }

   public static boolean canExecute4() {
      return getCount3() > 0;
   }

   public static boolean isActive15() {
      return gameSettings.indexOffset == 3;
   }

   public static boolean isActive8() {
      return gameSettings.indexOffset61 == 1;
   }

   public static boolean isActive14() {
      return gameSettings.state38;
   }

   public static boolean isActive16() {
      return gameSettings.indexOffset15 == 1;
   }
}
