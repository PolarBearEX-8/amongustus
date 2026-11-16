package client.util;

import client.event.BridgeListener;
import client.gui.OptifineGuiContainerManager;
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
import net.minecraft.block.BlocksGrassTopManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.ActiveItemEntry;
import net.minecraft.item.ContextItemEntry;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class InternalPngManager {
   public static final String labelText57 = "grass_top";
   public static final String labelText59 = "stone";
   public static final String labelText52 = "dirt";
   public static final String labelText23 = "coarse_dirt";
   public static final String labelText35 = "grass_side";
   public static final String labelText53 = "stone_slab_side";
   public static final String labelText37 = "stone_slab_top";
   public static final String labelText = "bedrock";
   public static final String labelText11 = "sand";
   public static final String labelText46 = "gravel";
   public static final String labelText61 = "log_oak";
   public static final String labelText14 = "log_big_oak";
   public static final String labelText33 = "log_acacia";
   public static final String labelText6 = "log_spruce";
   public static final String labelText28 = "log_birch";
   public static final String labelText54 = "log_jungle";
   public static final String labelText9 = "log_oak_top";
   public static final String labelText40 = "log_big_oak_top";
   public static final String labelText55 = "log_acacia_top";
   public static final String labelText16 = "log_spruce_top";
   public static final String labelText2 = "log_birch_top";
   public static final String labelText49 = "log_jungle_top";
   public static final String labelText13 = "leaves_oak";
   public static final String labelText17 = "leaves_big_oak";
   public static final String labelText21 = "leaves_acacia";
   public static final String labelText5 = "leaves_birch";
   public static final String labelText60 = "leaves_spruce";
   public static final String labelText38 = "leaves_jungle";
   public static final String labelText63 = "gold_ore";
   public static final String labelText44 = "iron_ore";
   public static final String labelText10 = "coal_ore";
   public static final String labelText36 = "obsidian";
   public static final String labelText65 = "grass_side_overlay";
   public static final String labelText24 = "snow";
   public static final String labelText39 = "grass_side_snowed";
   public static final String labelText66 = "mycelium_side";
   public static final String labelText18 = "mycelium_top";
   public static final String labelText8 = "diamond_ore";
   public static final String labelText30 = "redstone_ore";
   public static final String labelText19 = "lapis_ore";
   public static final String labelText4 = "cactus_side";
   public static final String labelText48 = "clay";
   public static final String labelText32 = "farmland_wet";
   public static final String labelText58 = "farmland_dry";
   public static final String labelText41 = "netherrack";
   public static final String labelText7 = "soul_sand";
   public static final String labelText56 = "glowstone";
   public static final String labelText15 = "leaves_spruce";
   public static final String labelText29 = "leaves_spruce_opaque";
   public static final String labelText64 = "end_stone";
   public static final String labelText27 = "sandstone_top";
   public static final String labelText50 = "sandstone_bottom";
   public static final String labelText20 = "redstone_lamp_off";
   public static final String labelText34 = "redstone_lamp_on";
   public static final String labelText26 = "water_still";
   public static final String labelText3 = "water_flow";
   public static final String labelText31 = "lava_still";
   public static final String labelText45 = "lava_flow";
   public static final String labelText43 = "fire_layer_0";
   public static final String labelText51 = "fire_layer_1";
   public static final String labelText42 = "portal";
   public static final String labelText47 = "glass";
   public static final String labelText22 = "glass_pane_top";
   public static final String labelText67 = "compass";
   public static final String labelText25 = "clock";
   public static ServiceStringEntry serviceStringEntry16;
   public static ServiceStringEntry serviceStringEntry12;
   public static ServiceStringEntry serviceStringEntry;
   public static ServiceStringEntry serviceStringEntry7;
   public static ServiceStringEntry serviceStringEntry14;
   public static ServiceStringEntry serviceStringEntry3;
   public static ServiceStringEntry serviceStringEntry10;
   public static ServiceStringEntry serviceStringEntry15;
   public static ServiceStringEntry serviceStringEntry13;
   public static ServiceStringEntry serviceStringEntry2;
   public static ServiceStringEntry serviceStringEntry4;
   public static ServiceStringEntry serviceStringEntry9;
   public static ServiceStringEntry serviceStringEntry17;
   public static ServiceStringEntry serviceStringEntry6;
   public static ServiceStringEntry serviceStringEntry11;
   public static ServiceStringEntry serviceStringEntry5;
   public static ServiceStringEntry serviceStringEntry8;
   public static ServiceStringEntry serviceStringEntry18;
   public static final String labelText12 = "minecraft:blocks/";
   public static final String labelText62 = "minecraft:items/";
   public static IntBuffer intBuffer = VectorMatcher.resolveIntBuffer(256);

   public static boolean checkIndex(int count) {
      return MathHelper.processAction(count) == count;
   }

   public static void applyIndex(int count) {
      GlStateManager.setActiveTexture(count);
   }

   public static int computeCountByIndex(int count) {
      int value = 1;

      for (int index = 0; index < count; index++) {
         value *= 2;
      }

      return value;
   }

   public static ListenerAdapter27 getListenerAdapter27() {
      return Minecraft.getMinecraft().getListenerAdapter27();
   }

   public static int getCount() {
      for (int index = 65536; index > 0; index >>= 1) {
         GlStateManager.glStateAction(32868, 0, 6408, index, index, 0, 6408, 5121, (IntBuffer)null);
         GL11.glGetError();
         if (GlStateManager.getTexLevelParameteri(32868, 0, 4096) != 0) {
            return index;
         }
      }

      return -1;
   }

   public static void applyServiceStringEntry(ServiceStringEntry serviceStringEntry19) {
      int[][] countMatrix = serviceStringEntry19.get(0);

      for (int index = 0; index < countMatrix.length; index++) {
         int[] countArray = countMatrix[index];
         if (countArray == null) {
            BuildManager.applyText4("" + index + ": " + countArray);
         } else {
            BuildManager.applyText4("" + index + ": " + countArray.length);
         }
      }
   }

   public static int computeCount(int count) {
      int value = 1;

      while (value < count) {
         value *= 2;
      }

      return value;
   }

   public static void applyText(String text, int count, int count2, int count3, int count4) {
      applyIndex(count);
      GL11.glPixelStorei(3333, 1);
      GL11.glPixelStorei(3317, 1);
      File parentFile = new File(text).getParentFile();
      if (parentFile != null) {
         parentFile.mkdirs();
      }

      for (int index = 0; index < 16; index++) {
         new File(text + "_" + index + ".png").delete();
      }

      for (int index2 = 0; index2 <= count2; index2++) {
         File file = new File(text + "_" + index2 + ".png");
         int count5 = count3 >> index2;
         int count6 = count4 >> index2;
         int count7 = count5 * count6;
         IntBuffer buffer = BufferUtils.createIntBuffer(count7);
         int[] countArray = new int[count7];
         GL11.glGetTexImage(3553, index2, 32993, 33639, buffer);
         buffer.get(countArray);
         BufferedImage bufferedImage = new BufferedImage(count5, count6, 2);
         bufferedImage.setRGB(0, 0, count5, count6, countArray, 0, count5);

         try {
            ImageIO.write(bufferedImage, "png", file);
            BuildManager.applyText4("Exported: " + file);
         } catch (Exception exception) {
            BuildManager.applyText3("Error writing: " + file);
            BuildManager.applyText3("" + exception.getClass().getName() + ": " + exception.getMessage());
         }
      }
   }

   public static int[] resolveIndexArray(ServiceStringEntry serviceStringEntry19, int count, int count2) {
      List list = serviceStringEntry19.addAll();
      if (list.size() <= count) {
         return null;
      } else {
         int[][] countMatrix = (int[][])list.get(count);
         return countMatrix != null && countMatrix.length > count2 ? countMatrix[count2] : null;
      }
   }

   public static ResourceLocation resolveResourceLocation(ResourceLocation location, String text) {
      ResourceLocation location2 = location;
      if (!location.getText().equals("minecraft")) {
         return location;
      } else {
         String text2 = location.getText1();
         String text3 = resolveText(text2, text);
         if (text3 != text2) {
            location2 = new ResourceLocation(location.getText(), text3);
         }

         return location2;
      }
   }

   public static void updateState() {
      IResourceManager iResourceManager = BuildManager.getIResourceManager();
      if (iResourceManager instanceof Listener69Extension) {
         Listener69Extension listener69Extension = (Listener69Extension)iResourceManager;
         AngleValidator angleValidator = new AngleValidator();
         listener69Extension.applyIResourceManagerReloadList(angleValidator);
      }

      VectorObserver vectorObserver = new VectorObserver();
      ResourceLocation location = new ResourceLocation("optifine/TickableTextures");
      BuildManager.getTextureManagerAsTextureManager().matchesResourceLocationByResourceLocation(location, vectorObserver);
   }

   public static String resolveText(String text, String text2) {
      String text3 = text;
      String text4 = text2;
      String text5 = "assets/minecraft/";
      if (text.startsWith(text5)) {
         return text.substring(text5.length());
      } else if (text.startsWith("./")) {
         text3 = text.substring(2);
         if (!text2.endsWith("/")) {
            text4 = text2 + "/";
         }

         return text4 + text3;
      } else {
         if (text.startsWith("/~")) {
            text3 = text.substring(1);
         }

         String text6 = "mcpatcher/";
         if (text3.startsWith("~/")) {
            text3 = text3.substring(2);
            return text6 + text3;
         } else {
            return text3.startsWith("/") ? text6 + text3.substring(1) : text3;
         }
      }
   }

   public static String resolveText(String text) {
      int count = text.lastIndexOf(47);
      return count < 0 ? "" : text.substring(0, count);
   }

   public static void updateState3() {
      if (GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic) {
         float factor = GL11.glGetFloat(34047);
         float factor2 = Math.min((float)BuildManager.getCount(), factor);
         GL11.glTexParameterf(3553, 34046, factor2);
      }
   }

   public static int computeCount2(int count) {
      int value = 1;

      int count2;
      for (count2 = 0; value < count; count2++) {
         value *= 2;
      }

      return count2;
   }

   public static int computeCountByIndex(int count, int count2) {
      if (count >= count2) {
         return count;
      } else {
         int count3 = count2 / count * count;

         while (count3 < count2) {
            count3 += count;
         }

         return count3;
      }
   }

   public static void applyServiceStringEntry(ServiceStringEntry serviceStringEntry19, int count) {
      int count2 = serviceStringEntry19.getCount();
      int count3 = serviceStringEntry19.getCount2();
      if (serviceStringEntry19.size() < 1) {
         ArrayList list = new ArrayList();
         int[][] countMatrix = new int[count + 1][];
         int[] countArray = new int[count2 * count3];
         countMatrix[0] = countArray;
         list.add(countMatrix);
         serviceStringEntry19.applyList(list);
      }

      ArrayList list2 = new ArrayList();
      int size = serviceStringEntry19.size();

      for (int index = 0; index < size; index++) {
         int[] countArray2 = resolveIndexArray(serviceStringEntry19, index, 0);
         if (countArray2 == null || countArray2.length < 1) {
            countArray2 = new int[count2 * count3];
         }

         if (countArray2.length != count2 * count3) {
            int count4 = (int)Math.round(Math.sqrt((double)countArray2.length));
            if (count4 * count4 != countArray2.length) {
               countArray2 = new int[1];
               count4 = 1;
            }

            BufferedImage bufferedImage = new BufferedImage(count4, count4, 2);
            bufferedImage.setRGB(0, 0, count4, count4, countArray2, 0, count4);
            BufferedImage bufferedImage2 = resolveBufferedImage(bufferedImage, count2);
            countArray2 = new int[count2 * count3];
            bufferedImage2.getRGB(0, 0, count2, count3, countArray2, 0, count2);
         }

         int[][] countMatrix2 = new int[count + 1][];
         countMatrix2[0] = countArray2;
         list2.add(countMatrix2);
      }

      serviceStringEntry19.applyList(list2);
      serviceStringEntry19.applyIndex4(count);
   }

   public static void applyIResourceManager(IResourceManager iResourceManager) {
      if (getListenerAdapter27() != null) {
         BuildManager.applyText4("*** Reloading custom textures ***");
         McpatcherSkyWorldManager.updateState2();
         AnimManager.updateState();
         updateState2();
         NotFoundManager.updateState();
         BlocksGrassTopManager.updateState();
         ContextItemEntry.updateState();
         AnimManager.updateState2();
         LoadingManager.updateState2();
         McpatcherSkyWorldManager.updateState();
         OptifineRandomManager.updateState();
         InvalidItemIDManager.updateState();
         AdapterManager.updateState();
         PreEndHandManager.updateState27();
         GeneratorDefaultManager.updateState();
         BuildManager.updateState4();
         ModelManager.updateState();
         WeightManager.updateState();
         OptifineGuiContainerManager.renderElement();
         ActiveItemEntry.updateState();
         PngManager.updateState();
         LayerManager.updateState();
         BuildManager.getTextureManagerAsTextureManager().updateState2();
      }
   }

   public static BridgeListener resolveBridgeListenerByResourceLocation(ResourceLocation location) {
      BridgeListener bridgeListener = BuildManager.getTextureManagerAsTextureManager().get(location);
      if (bridgeListener != null) {
         return bridgeListener;
      } else if (!BuildManager.matchesResourceLocation3(location)) {
         return null;
      } else {
         DefaultLoggerEntry defaultLoggerEntry = new DefaultLoggerEntry(location, true);
         BuildManager.getTextureManagerAsTextureManager().matchesResourceLocation(location, defaultLoggerEntry);
         return defaultLoggerEntry;
      }
   }

   public static void updateState2() {
      ListenerAdapter27 listenerAdapter = getListenerAdapter27();
      if (listenerAdapter != null) {
         String text = "minecraft:blocks/";
         serviceStringEntry16 = listenerAdapter.resolveServiceStringEntry(text + "grass_top");
         serviceStringEntry12 = listenerAdapter.resolveServiceStringEntry(text + "grass_side");
         serviceStringEntry = listenerAdapter.resolveServiceStringEntry(text + "grass_side_overlay");
         serviceStringEntry7 = listenerAdapter.resolveServiceStringEntry(text + "snow");
         serviceStringEntry14 = listenerAdapter.resolveServiceStringEntry(text + "grass_side_snowed");
         serviceStringEntry3 = listenerAdapter.resolveServiceStringEntry(text + "mycelium_side");
         serviceStringEntry10 = listenerAdapter.resolveServiceStringEntry(text + "mycelium_top");
         serviceStringEntry15 = listenerAdapter.resolveServiceStringEntry(text + "water_still");
         serviceStringEntry13 = listenerAdapter.resolveServiceStringEntry(text + "water_flow");
         serviceStringEntry2 = listenerAdapter.resolveServiceStringEntry(text + "lava_still");
         serviceStringEntry4 = listenerAdapter.resolveServiceStringEntry(text + "lava_flow");
         serviceStringEntry17 = listenerAdapter.resolveServiceStringEntry(text + "fire_layer_0");
         serviceStringEntry6 = listenerAdapter.resolveServiceStringEntry(text + "fire_layer_1");
         serviceStringEntry9 = listenerAdapter.resolveServiceStringEntry(text + "portal");
         serviceStringEntry11 = listenerAdapter.resolveServiceStringEntry(text + "glass");
         serviceStringEntry5 = listenerAdapter.resolveServiceStringEntry(text + "glass_pane_top");
         String text2 = "minecraft:items/";
         serviceStringEntry8 = listenerAdapter.resolveServiceStringEntry(text2 + "compass");
         serviceStringEntry18 = listenerAdapter.resolveServiceStringEntry(text2 + "clock");
      }
   }

   public static Dimension resolveDimension(InputStream inputStream, String text) {
      Iterator imageReadersBySuffix = ImageIO.getImageReadersBySuffix(text);

      while (imageReadersBySuffix.hasNext()) {
         ImageReader imageReader = (ImageReader)imageReadersBySuffix.next();

         Dimension dimension;
         try {
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(inputStream);
            imageReader.setInput(imageInputStream);
            int width = imageReader.getWidth(imageReader.getMinIndex());
            int height = imageReader.getHeight(imageReader.getMinIndex());
            dimension = new Dimension(width, height);
         } catch (IOException ioException) {
            continue;
         } finally {
            imageReader.dispose();
         }

         return dimension;
      }

      return null;
   }

   public static int computeCount(int count, int count2) {
      if (count == count2) {
         return count;
      } else {
         int count3 = count / count2 * count2;

         while (count3 < count) {
            count3 += count2;
         }

         return count3;
      }
   }

   public static BufferedImage resolveBufferedImage(String text, BufferedImage bufferedImage) {
      if (text.startsWith("/mob/zombie") || text.startsWith("/mob/pigzombie")) {
         int width = bufferedImage.getWidth();
         int height = bufferedImage.getHeight();
         if (width == height * 2) {
            BufferedImage bufferedImage2 = new BufferedImage(width, height * 2, 2);
            Graphics2D graphics2D = bufferedImage2.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(bufferedImage, 0, 0, width, height, (ImageObserver)null);
            return bufferedImage2;
         }
      }

      return bufferedImage;
   }

   public static BufferedImage resolveBufferedImage(BufferedImage bufferedImage, int count) {
      int width = bufferedImage.getWidth();
      int count2 = bufferedImage.getHeight() * count / width;
      BufferedImage bufferedImage2 = new BufferedImage(count, count2, 2);
      Graphics2D graphics2D = bufferedImage2.createGraphics();
      Object entry = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
      if (count < width || count % width != 0) {
         entry = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
      }

      graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, entry);
      graphics2D.drawImage(bufferedImage, 0, 0, count, count2, (ImageObserver)null);
      return bufferedImage2;
   }
}
