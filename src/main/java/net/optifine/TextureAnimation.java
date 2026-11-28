package net.optifine;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Properties;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.util.TextureUtils;
import org.lwjgl.opengl.GL11;

public class TextureAnimation {
   private String srcTex = null;
   private String dstTex = null;
   public ResourceLocation dstTexLoc = null;
   private int dstTextId = -1;
   private int dstX = 0;
   private int dstY = 0;
   private int frameWidth = 0;
   private int frameHeight = 0;
   private TextureAnimationFrame[] frames = null;
   private int currentFrameIndex = 0;
   private boolean interpolate = false;
   private int interpolateSkip = 0;
   private ByteBuffer interpolateData = null;
   public byte[] srcData = null;
   private ByteBuffer imageData = null;
   private boolean active = true;
   private boolean valid = true;

   public int getFrames() {
      return this.frames.length;
   }

   public String getSrcTex() {
      return this.srcTex;
   }

   public ResourceLocation getDstTexLoc() {
      return this.dstTexLoc;
   }

   public TextureAnimation(String str, byte[] bytes, String str2, ResourceLocation location, int n, int n2, int n3, int n4, Properties properties) {
      this.srcTex = str;
      this.dstTex = str2;
      this.dstTexLoc = location;
      this.dstX = n;
      this.dstY = n2;
      this.frameWidth = n3;
      this.frameHeight = n4;
      int n5 = n3 * n4 * 4;
      if (bytes.length % n5 != 0) {
         Config.warn("Invalid animated texture length: " + bytes.length + ", frameWidth: " + n3 + ", frameHeight: " + n4);
      }

      this.srcData = bytes;
      int n6 = bytes.length / n5;
      if (properties.get("tile.0") != null) {
         for (int i = 0; properties.get("tile." + i) != null; i++) {
            n6 = i + 1;
         }
      }

      int n7 = Math.max(Config.parseInt((String)properties.get("duration"), 1), 1);
      this.frames = new TextureAnimationFrame[n6];

      for (int j = 0; j < this.frames.length; j++) {
         int n8 = Config.parseInt((String)properties.get("tile." + j), j);
         int n9 = Math.max(Config.parseInt((String)properties.get("duration." + j), n7), 1);
         TextureAnimationFrame textureAnimationFrame = new TextureAnimationFrame(n8, n9);
         this.frames[j] = textureAnimationFrame;
      }

      this.interpolate = Config.parseBoolean2(properties.getProperty("interpolate"), false);
      this.interpolateSkip = Config.parseInt(properties.getProperty("skip"), 0);
      if (this.interpolate) {
         this.interpolateData = GLAllocation.createDirectByteBuffer(n5);
      }
   }

   private void updateTextureInterpolate(TextureAnimationFrame textureAnimationFrame, TextureAnimationFrame textureAnimationFrame2, double d) {
      int n = this.frameWidth * this.frameHeight * 4;
      int n2 = n * textureAnimationFrame.index;
      if (n2 + n <= this.imageData.limit()) {
         int n3 = n * textureAnimationFrame2.index;
         if (n3 + n <= this.imageData.limit()) {
            ((Buffer)this.interpolateData).clear();

            for (int i = 0; i < n; i++) {
               byte b = (byte)this.mix(this.imageData.get(n2 + i) & 255, this.imageData.get(n3 + i) & 255, d);
               this.interpolateData.put(b);
            }

            ((Buffer)this.interpolateData).flip();
            GlStateManager.bindTexture(this.dstTextId);
            GL11.glTexSubImage2D(3553, 0, this.dstX, this.dstY, this.frameWidth, this.frameHeight, 6408, 5121, this.interpolateData);
         }
      }
   }

   public TextureAnimationFrame getCurrentFrame() {
      return this.getFrame(this.currentFrameIndex);
   }

   public TextureAnimationFrame getFrame(int n) {
      if (this.frames.length <= 0) {
         return null;
      } else {
         if (n < 0 || n >= this.frames.length) {
            n = 0;
         }

         return this.frames[n];
      }
   }

   public boolean nextFrame() {
      TextureAnimationFrame currentFrame = this.getCurrentFrame();
      if (currentFrame == null) {
         return false;
      } else {
         currentFrame.counter++;
         if (currentFrame.counter < currentFrame.duration) {
            return this.interpolate;
         } else {
            currentFrame.counter = 0;
            this.currentFrameIndex++;
            if (this.currentFrameIndex >= this.frames.length) {
               this.currentFrameIndex = 0;
            }

            return true;
         }
      }
   }

   public boolean isActive() {
      return this.active;
   }

   private int mix(int n, int n2, double d) {
      return (int)((double)n * (1.0 - d) + (double)n2 * d);
   }

   public void updateTexture() {
      if (this.valid) {
         if (this.dstTextId < 0) {
            ITextureObject texture = TextureUtils.getTexture(this.dstTexLoc);
            if (texture == null) {
               this.valid = false;
               return;
            }

            this.dstTextId = texture.getGlTextureId();
         }

         if (this.imageData == null) {
            this.imageData = GLAllocation.createDirectByteBuffer(this.srcData.length);
            this.imageData.put(this.srcData);
            ((Buffer)this.imageData).flip();
            this.srcData = null;
         }

         this.active = SmartAnimations.isActive() ? SmartAnimations.isTextureRendered(this.dstTextId) : true;
         if (this.nextFrame() && this.active) {
            int n = this.frameWidth * this.frameHeight * 4;
            TextureAnimationFrame currentFrame = this.getCurrentFrame();
            if (currentFrame != null) {
               int n2 = n * currentFrame.index;
               if (n2 + n <= this.imageData.limit()) {
                  if (!this.interpolate || currentFrame.counter <= 0) {
                     ((Buffer)this.imageData).position(n2);
                     GlStateManager.bindTexture(this.dstTextId);
                     GL11.glTexSubImage2D(3553, 0, this.dstX, this.dstY, this.frameWidth, this.frameHeight, 6408, 5121, this.imageData);
                  } else if (this.interpolateSkip <= 1 || currentFrame.counter % this.interpolateSkip == 0) {
                     this.updateTextureInterpolate(currentFrame, this.getFrame(this.currentFrameIndex + 1), 1.0 * (double)currentFrame.counter / (double)currentFrame.duration);
                  }
               }
            }
         }
      }
   }

   public String getDstTex() {
      return this.dstTex;
   }
}
