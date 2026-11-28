package net.minecraft.util;

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
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.event.ClickEvent;
import net.minecraft.network.PacketThreadUtil;
import net.optifine.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ScreenShotHelper {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
   private static IntBuffer pixelBuffer;
   private static int[] pixelValues;
   private static CompletableFuture saveScreenshotFuture = null;

   private static void resize(int n, int n2) {
      Minecraft minecraft = Minecraft.getMinecraft();
      minecraft.displayWidth = Math.max(1, n);
      minecraft.displayHeight = Math.max(1, n2);
      if (minecraft.currentScreen != null) {
         ScaledResolution sr = new ScaledResolution(minecraft);
         minecraft.currentScreen.onResize(minecraft, sr.getScaledWidth(), sr.getScaledHeight());
      }

      updateFramebufferSize();
   }

   public static void saveScreenshotAsync(File file, String str, int n, int n2, Framebuffer framebuffer) {
      try {
         File file2 = new File(file, "screenshots");
         file2.mkdir();
         Minecraft minecraft = Minecraft.getMinecraft();
         int particleSetting = Config.getGameSettings().particleSetting;
         int scaleFactor = new ScaledResolution(minecraft).getScaleFactor();
         int screenshotSize = Config.getScreenshotSize();
         boolean flag = OpenGlHelper.isFramebufferEnabled() && screenshotSize > 1;
         if (flag) {
            Config.getGameSettings().particleSetting = scaleFactor * screenshotSize;
            resize(n * screenshotSize, n2 * screenshotSize);
            GlStateManager.pushMatrix();
            GlStateManager.clear(16640);
            minecraft.getFramebuffer().bindFramebuffer(true);
            minecraft.entityRenderer.updateCameraAndRender(Config.renderPartialTicks, System.nanoTime(), 0L);
         }

         if (OpenGlHelper.isFramebufferEnabled()) {
            n = framebuffer.framebufferTextureWidth;
            n2 = framebuffer.framebufferTextureHeight;
         }

         int n3 = n * n2;
         if (pixelBuffer == null || pixelBuffer.capacity() < n3) {
            pixelBuffer = BufferUtils.createIntBuffer(n3);
            pixelValues = new int[n3];
         }

         GL11.glPixelStorei(3333, 1);
         GL11.glPixelStorei(3317, 1);
         ((Buffer)pixelBuffer).clear();
         if (OpenGlHelper.isFramebufferEnabled()) {
            GlStateManager.bindTexture(framebuffer.framebufferTexture);
            GL11.glGetTexImage(3553, 0, 32993, 33639, pixelBuffer);
         } else {
            GL11.glReadPixels(0, 0, n, n2, 32993, 33639, pixelBuffer);
         }

         int n4 = n;
         int n5 = n2;
         if (saveScreenshotFuture != null && !saveScreenshotFuture.isDone()) {
            return;
         }

         saveScreenshotFuture = CompletableFuture.runAsync(
            () -> {
               pixelBuffer.get(pixelValues);
               TextureUtil.processPixelValues(pixelValues, n4, n5);
               BufferedImage image = null;
               if (OpenGlHelper.isFramebufferEnabled()) {
                  image = new BufferedImage(framebuffer.framebufferWidth, framebuffer.framebufferHeight, 1);
                  int n6 = framebuffer.framebufferTextureHeight - framebuffer.framebufferHeight;

                  for (int i = n6; i < framebuffer.framebufferTextureHeight; i++) {
                     for (int j = 0; j < framebuffer.framebufferWidth; j++) {
                        image.setRGB(j, i - n6, pixelValues[i * framebuffer.framebufferTextureWidth + j]);
                     }
                  }
               } else {
                  image = new BufferedImage(n4, n5, 1);
                  image.setRGB(0, 0, n4, n5, pixelValues, 0, n4);
               }

               if (flag) {
                  minecraft.getFramebuffer().unbindFramebuffer();
                  GlStateManager.popMatrix();
                  Config.getGameSettings().particleSetting = particleSetting;
                  resize(n4, n5);
               }

               File file3;
               if (str == null) {
                  file3 = getTimestampedPNGFileForDirectory(file2);
               } else {
                  file3 = new File(file2, str);
               }

               try {
                  file3 = file3.getCanonicalFile();
                  if (!file3.getParentFile().exists()) {
                     file3.getParentFile().mkdirs();
                  }

                  ImageIO.write(image, "png", file3);
               } catch (IOException e) {
                  throw new RuntimeException(e);
               }

               ChatComponentText chatComponentText = new ChatComponentText(file3.getName());
               chatComponentText.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, file3.getAbsolutePath()));
               chatComponentText.getChatStyle().setUnderlined(Boolean.TRUE);
               if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
                  Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentTranslation("screenshot.success", new Object[]{chatComponentText}));
               } else {
                  PacketThreadUtil.runOnMainThreadImmediate(
                     () -> Minecraft.getMinecraft()
                           .ingameGUI
                           .getChatGUI()
                           .printChatMessage(new ChatComponentTranslation("screenshot.success", new Object[]{chatComponentText}))
                  );
               }
            }
         );
      } catch (Exception e2) {
         LOGGER.warn("Couldn't save screenshot", e2);
         if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentTranslation("screenshot.failure", new Object[]{e2.getMessage()}));
         } else {
            PacketThreadUtil.runOnMainThreadImmediate(
               () -> Minecraft.getMinecraft()
                     .ingameGUI
                     .getChatGUI()
                     .printChatMessage(new ChatComponentTranslation("screenshot.failure", new Object[]{e2.getMessage()}))
            );
         }
      }
   }

   private static void updateFramebufferSize() {
      Minecraft minecraft = Minecraft.getMinecraft();
      minecraft.getFramebuffer().createBindFramebuffer(minecraft.displayWidth, minecraft.displayHeight);
      if (minecraft.entityRenderer != null) {
         minecraft.entityRenderer.updateShaderGroupSize(minecraft.displayWidth, minecraft.displayHeight);
      }
   }

   public static void saveScreenshotAsync(File file, int n, int n2, Framebuffer framebuffer) {
      saveScreenshotAsync(file, null, n, n2, framebuffer);
   }

   private static File getTimestampedPNGFileForDirectory(File file) {
      String str = dateFormat.format(new Date());
      int n = 1;

      while (true) {
         File file2 = new File(file, str + (n == 1 ? "" : "_" + n) + ".png");
         if (!file2.exists()) {
            return file2;
         }

         n++;
      }
   }

}
