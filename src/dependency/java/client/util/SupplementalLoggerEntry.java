package client.util;

import client.setting.ClientSettingsOpenUrlType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class SupplementalLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
   public static IntBuffer intBuffer;
   public static int[] countArray;
   public static CompletableFuture completableFuture = null;

   public static void applyFile(File file, int count, int count2, Framebuffer framebuffer) {
      applyFile(file, null, count, count2, framebuffer);
   }

   private static void applyIndex(int count, int count2) {
      Minecraft mc = Minecraft.getMinecraft();
      mc.displayWidth = Math.max(1, count);
      mc.displayHeight = Math.max(1, count2);
      if (mc.currentScreen != null) {
         ScaledResolution scaledResolution = new ScaledResolution(mc);
         mc.currentScreen.applyMinecraft(mc, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight());
      }

      updateState();
   }

   private static File resolveFile(File file) {
      String text = dateFormat.format(new Date());
      int count = 1;

      while (true) {
         File file2 = new File(file, text + (count == 1 ? "" : "_" + count) + ".png");
         if (!file2.exists()) {
            return file2;
         }

         count++;
      }
   }

   private static void updateState() {
      Minecraft mc = Minecraft.getMinecraft();
      mc.getFramebuffer().applyIndex4(mc.displayWidth, mc.displayHeight);
      if (mc.entityRenderer != null) {
         mc.entityRenderer.applyIndex(mc.displayWidth, mc.displayHeight);
      }
   }

   public static void applyFile(File file, String text, int count, int count2, Framebuffer framebuffer) {
      int count3 = count;
      int count4 = count2;

      try {
         File file2 = new File(file, "screenshots");
         file2.mkdir();
         Minecraft mc = Minecraft.getMinecraft();
         int indexOffset = BuildManager.getGameSettings().indexOffset134;
         int scaleFactor = new ScaledResolution(mc).getScaleFactor();
         int count5 = BuildManager.getCount4();
         boolean flag = OpenGlHelper.isActiveAsFlag() && count5 > 1;
         if (flag) {
            BuildManager.getGameSettings().indexOffset134 = scaleFactor * count5;
            applyIndex(count3 * count5, count4 * count5);
            GlStateManager.pushMatrix();
            GlStateManager.glClear(16640);
            mc.getFramebuffer().setState(true);
            mc.entityRenderer.applyFloatByFloat(BuildManager.factor, System.nanoTime());
         }

         if (OpenGlHelper.isActiveAsFlag()) {
            count3 = framebuffer.count4;
            count4 = framebuffer.count5;
         }

         int count6 = count3 * count4;
         if (intBuffer == null || intBuffer.capacity() < count6) {
            intBuffer = BufferUtils.createIntBuffer(count6);
            countArray = new int[count6];
         }

         GL11.glPixelStorei(3333, 1);
         GL11.glPixelStorei(3317, 1);
         ((Buffer)intBuffer).clear();
         if (OpenGlHelper.isActiveAsFlag()) {
            GlStateManager.setActiveTexture(framebuffer.indexOffset);
            GL11.glGetTexImage(3553, 0, 32993, 33639, intBuffer);
         } else {
            GL11.glReadPixels(0, 0, count3, count4, 32993, 33639, intBuffer);
         }

         if (completableFuture != null && !completableFuture.isDone()) {
            return;
         }

         int width = count3;
         int height = count4;
         completableFuture = CompletableFuture.runAsync(
            () -> SupplementalLoggerEntry.applyFramebuffer(width, height, framebuffer, flag, mc, indexOffset, text, file2)
         );
      } catch (Exception exception) {
         logger.warn("Couldn't save screenshot", exception);
         Minecraft.getMinecraft()
            .ingameGUI
            .getInternalLoggerEntry()
            .applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("screenshot.failure", new Object[]{exception.getMessage()}));
      }
   }

   private static void applyFramebuffer(int count, int count2, Framebuffer framebuffer, boolean flag, Minecraft mc, int count3, String text, File file) {
      intBuffer.get(countArray);
      SpecialLoggerEntry.applyIndexArray(countArray, count, count2);
      BufferedImage bufferedImage;
      if (OpenGlHelper.isActiveAsFlag()) {
         bufferedImage = new BufferedImage(framebuffer.indexOffset2, framebuffer.indexOffset1, 1);
         int count4 = framebuffer.count5 - framebuffer.indexOffset1;

         for (int index = count4; index < framebuffer.count5; index++) {
            for (int index2 = 0; index2 < framebuffer.indexOffset2; index2++) {
               bufferedImage.setRGB(index2, index - count4, countArray[index * framebuffer.count4 + index2]);
            }
         }
      } else {
         bufferedImage = new BufferedImage(count, count2, 1);
         bufferedImage.setRGB(0, 0, count, count2, countArray, 0, count);
      }

      if (flag) {
         mc.getFramebuffer().renderElement();
         GlStateManager.popMatrix();
         BuildManager.getGameSettings().indexOffset134 = count3;
         applyIndex(count, count2);
      }

      File file2;
      if (text == null) {
         file2 = resolveFile(file);
      } else {
         file2 = new File(file, text);
      }

      File file3;
      try {
         file3 = file2.getCanonicalFile();
         if (!file3.getParentFile().exists()) {
            file3.getParentFile().mkdirs();
         }

         ImageIO.write(bufferedImage, "png", file3);
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }

      ChatComponentText chatComponentText = new ChatComponentText(file3.getName());
      chatComponentText.getStyleHasParentManager().getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.OPEN_FILE, file3.getAbsolutePath()));
      chatComponentText.getStyleHasParentManager().withMemberRef3(Boolean.TRUE);
      Minecraft.getMinecraft()
         .ingameGUI
         .getInternalLoggerEntry()
         .applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("screenshot.success", new Object[]{chatComponentText}));
   }
}
