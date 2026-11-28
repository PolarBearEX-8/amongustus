package net.augustus.modules.fun.mlg;

import net.augustus.utils.audio.ClientSounds;
import net.augustus.utils.file.JarResourceListingUtil;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class AnimatedGifSequence {
   private ResourceLocation currentFrameLocation;
   private final int frameDelayMs;
   private int width;
   private int height;
   private ResourceLocation soundLocation;
   private final ArrayList frames;
   private int currentFrameIndex;
   private final int frameCount;
   private final String resourceFolder;
   private long lastFrameTimeMs;

   public ResourceLocation getCurrentFrame() {
      return this.updateAndGetFrame(this.frameDelayMs);
   }

   public int getWidth() {
      return this.width;
   }

   public int getTotalDurationMs() {
      return this.frameDelayMs * this.frameCount;
   }

   private void resetAnimation() {
      this.currentFrameIndex = -1;
      this.lastFrameTimeMs = 0L;
   }

   public int getHeight() {
      return this.height;
   }

   private void advanceFrame() {
      this.currentFrameIndex++;
      if (this.currentFrameIndex >= this.frameCount) {
         this.currentFrameIndex = -1;
         this.lastFrameTimeMs = 0L;
      }
   }

   private ResourceLocation updateAndGetFrame(int n) {
      long currentTimeMs = System.currentTimeMillis();
      if (this.lastFrameTimeMs == 0L) {
         if (this.soundLocation != null && Minecraft.getMinecraft() != null && Minecraft.getMinecraft().getSoundHandler() != null) {
            ClientSounds.playSound(this.soundLocation);
         }

         this.lastFrameTimeMs = currentTimeMs;
         this.advanceFrame();
         this.currentFrameLocation = (ResourceLocation)this.frames.get(this.currentFrameIndex);
      } else if (currentTimeMs - this.lastFrameTimeMs > (long)n) {
         this.lastFrameTimeMs = currentTimeMs;
         this.currentFrameLocation = (ResourceLocation)this.frames.get(this.currentFrameIndex);
         this.advanceFrame();
      }

      return this.currentFrameLocation;
   }

   public String getResourceFolder() {
      return this.resourceFolder;
   }

   public AnimatedGifSequence(String str, int n) {
      this.frames = new ArrayList();
      this.currentFrameIndex = -1;
      this.lastFrameTimeMs = 0L;
      this.soundLocation = null;
      n = !str.contains("yolo") && !str.contains("pussy")
         ? (str.contains("wow") ? 31 : (str.contains("snoop") ? 40 : (str.contains("sonic") ? 900 : n)))
         : 36;
      this.frameDelayMs = n;
      this.resourceFolder = str;
      ArrayList list = JarResourceListingUtil.listResourceEntries(str);
      Collections.sort(list);
      String str2 = (String)list.get(0);
      if (str2.endsWith(".ogg")) {
         str2 = (String)list.get(1);
      }

      try {
         BufferedImage image = ImageIO.read(AnimatedGifSequence.class.getClassLoader().getResourceAsStream("assets/augustus/" + str + "/" + str2));
         this.width = image.getWidth();
         this.height = image.getHeight();
      } catch (IOException e) {
         this.width = 0;
         this.height = 0;
      }

      for (String str3 : (Iterable<String>)(list)) {
         if (str3.endsWith(".ogg")) {
            String str4 = "mlg/gif/" + str.substring(9) + "/";
            String str5 = "sounds/" + str4;
            ArrayList list2 = JarResourceListingUtil.listResourceEntries(str5);
            if (!list2.isEmpty() && Minecraft.getMinecraft() != null && Minecraft.getMinecraft().getSoundHandler() != null) {
               String str6 = (String)list2.get(0);
               this.soundLocation = new ResourceLocation("augustus", str5 + str6);
               Minecraft.getMinecraft().getSoundHandler().registerAugustusSound(this.soundLocation, str4 + str6.substring(0, str6.length() - 4));
            }
         } else {
            this.frames.add(new ResourceLocation("augustus", str + "/" + str3));
         }
      }

      this.frameCount = this.frames.size();
   }

   public int getElapsedFrameTimeMs() {
      return (int)(System.currentTimeMillis() - this.lastFrameTimeMs);
   }

}
