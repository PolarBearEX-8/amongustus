package net.minecraft.client.renderer.texture;

import java.util.concurrent.Callable;
import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.optifine.SmartAnimations;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import net.optifine.util.CounterInt;
import net.optifine.util.TextureUtils;

public class TextureAtlasSprite {
   private final String iconName;
   public List framesTextureData = Lists.newArrayList();
   public int[][] interpolatedFrameData;
   private AnimationMetadataSection animationMetadata;
   public boolean rotated;
   public int originX;
   public int originY;
   public int width;
   public int height;
   private float minU;
   private float maxU;
   private float minV;
   private float maxV;
   public int frameCounter;
   public int tickCounter;
   private static String locationNameClock = "builtin/clock";
   private static String locationNameCompass = "builtin/compass";
   private int indexInMap = -1;
   public float baseU;
   public float baseV;
   public int sheetWidth;
   public int sheetHeight;
   public int glSpriteTextureId = -1;
   public TextureAtlasSprite spriteSingle = null;
   public boolean isSpriteSingle = false;
   public int mipmapLevels = 0;
   public TextureAtlasSprite spriteNormal = null;
   public TextureAtlasSprite spriteSpecular = null;
   public boolean isShadersSprite = false;
   public boolean isEmissive = false;
   public TextureAtlasSprite spriteEmissive = null;
   private int animationIndex = -1;
   private boolean animationActive = false;

   public void clearFramesTextureData() {
      this.framesTextureData.clear();
      if (this.spriteSingle != null) {
         this.spriteSingle.clearFramesTextureData();
      }
   }

   public void updateAnimation() {
      if (this.animationMetadata != null) {
         this.animationActive = SmartAnimations.isActive() ? SmartAnimations.isSpriteRendered(this.animationIndex) : true;
         this.tickCounter++;
         if (this.tickCounter >= this.animationMetadata.getFrameTimeSingle(this.frameCounter)) {
            int frameIndex = this.animationMetadata.getFrameIndex(this.frameCounter);
            int n = this.animationMetadata.getFrameCount() == 0 ? this.framesTextureData.size() : this.animationMetadata.getFrameCount();
            this.frameCounter = (this.frameCounter + 1) % n;
            this.tickCounter = 0;
            int frameIndex2 = this.animationMetadata.getFrameIndex(this.frameCounter);
            boolean isSpriteSingle2 = this.isSpriteSingle;
            if (!this.animationActive) {
               return;
            }

            if (frameIndex != frameIndex2 && frameIndex2 >= 0 && frameIndex2 < this.framesTextureData.size()) {
               TextureUtil.uploadTextureMipmap((int[][])this.framesTextureData.get(frameIndex2), this.width, this.height, this.originX, this.originY, false, isSpriteSingle2);
            }
         } else if (this.animationMetadata.isInterpolate()) {
            if (!this.animationActive) {
               return;
            }

            this.updateAnimationInterpolated();
         }
      }
   }

   public int getOriginY() {
      return this.originY;
   }

   public boolean hasCustomLoader(IResourceManager resourceManager, ResourceLocation location) {
      return false;
   }

   public int getOriginX() {
      return this.originX;
   }

   public void updateIndexInMap(CounterInt counterInt) {
      if (this.indexInMap < 0) {
         this.indexInMap = counterInt.nextValue();
      }
   }

   public void copyFrom(TextureAtlasSprite textureAtlasSprite) {
      this.originX = textureAtlasSprite.originX;
      this.originY = textureAtlasSprite.originY;
      this.width = textureAtlasSprite.width;
      this.height = textureAtlasSprite.height;
      this.rotated = textureAtlasSprite.rotated;
      this.minU = textureAtlasSprite.minU;
      this.maxU = textureAtlasSprite.maxU;
      this.minV = textureAtlasSprite.minV;
      this.maxV = textureAtlasSprite.maxV;
      if (textureAtlasSprite != Config.getTextureMap().getMissingSprite()) {
         this.indexInMap = textureAtlasSprite.indexInMap;
      }

      this.baseU = textureAtlasSprite.baseU;
      this.baseV = textureAtlasSprite.baseV;
      this.sheetWidth = textureAtlasSprite.sheetWidth;
      this.sheetHeight = textureAtlasSprite.sheetHeight;
      this.glSpriteTextureId = textureAtlasSprite.glSpriteTextureId;
      this.mipmapLevels = textureAtlasSprite.mipmapLevels;
      if (this.spriteSingle != null) {
         this.spriteSingle.initSprite(this.width, this.height, 0, 0, false);
      }

      this.animationIndex = textureAtlasSprite.animationIndex;
   }

   public void bindSpriteTexture() {
      if (this.glSpriteTextureId < 0) {
         this.glSpriteTextureId = TextureUtil.glGenTextures();
         TextureUtil.allocateTextureImpl(this.glSpriteTextureId, this.mipmapLevels, this.width, this.height);
         TextureUtils.applyAnisotropicLevel();
      }

      TextureUtils.bindTexture(this.glSpriteTextureId);
   }

   public float getMaxU() {
      return this.maxU;
   }

   public boolean isAnimationActive() {
      return this.animationActive;
   }

   public float getInterpolatedV(double d) {
      float f = this.maxV - this.minV;
      return this.minV + f * ((float)d / 16.0F);
   }

   public float getMinU() {
      return this.minU;
   }

   public int getFrameCount() {
      return this.framesTextureData.size();
   }

   public double getSpriteU16(float f) {
      float f2 = this.maxU - this.minU;
      return (double)((f - this.minU) / f2 * 16.0F);
   }

   public int getIconWidth() {
      return this.width;
   }

   public int getAnimationIndex() {
      return this.animationIndex;
   }

   public TextureAtlasSprite(String str) {
      this.iconName = str;
      if (Config.isMultiTexture()) {
         this.spriteSingle = new TextureAtlasSprite(this.getIconName() + ".spriteSingle", true);
      }
   }

   public int getIndexInMap() {
      return this.indexInMap;
   }

   private void loadShadersSprites() {
      if (Shaders.configNormalMap) {
         String str = this.iconName + "_n";
         ResourceLocation location = new ResourceLocation(str);
         location = Config.getTextureMap().completeResourceLocation(location);
         if (Config.hasResource(location)) {
            this.spriteNormal = new TextureAtlasSprite(str);
            this.spriteNormal.isShadersSprite = true;
            this.spriteNormal.copyFrom(this);
            this.spriteNormal.generateMipmaps(this.mipmapLevels);
         }
      }

      if (Shaders.configSpecularMap) {
         String str2 = this.iconName + "_s";
         ResourceLocation location2 = new ResourceLocation(str2);
         location2 = Config.getTextureMap().completeResourceLocation(location2);
         if (Config.hasResource(location2)) {
            this.spriteSpecular = new TextureAtlasSprite(str2);
            this.spriteSpecular.isShadersSprite = true;
            this.spriteSpecular.copyFrom(this);
            this.spriteSpecular.generateMipmaps(this.mipmapLevels);
         }
      }
   }

   public float getMaxV() {
      return this.maxV;
   }

   public void setFramesTextureData(List list) {
      this.framesTextureData = list;
      if (this.spriteSingle != null) {
         this.spriteSingle.setFramesTextureData(list);
      }
   }

   public void setIconHeight(int n) {
      this.height = n;
      if (this.spriteSingle != null) {
         this.spriteSingle.setIconHeight(this.height);
      }
   }

   private static int[][] getFrameTextureData(int[][] intsArr, int n, int n2, int n3) {
      int[][] intsArr2 = new int[intsArr.length][];

      for (int i = 0; i < intsArr.length; i++) {
         int[] ints = intsArr[i];
         if (ints != null) {
            intsArr2[i] = new int[(n >> i) * (n2 >> i)];
            System.arraycopy(ints, n3 * intsArr2[i].length, intsArr2[i], 0, intsArr2[i].length);
         }
      }

      return intsArr2;
   }

   private void resetSprite() {
      this.animationMetadata = null;
      this.setFramesTextureData(Lists.newArrayList());
      this.frameCounter = 0;
      this.tickCounter = 0;
      if (this.spriteSingle != null) {
         this.spriteSingle.resetSprite();
      }
   }

   public double getSpriteV16(float f) {
      float f2 = this.maxV - this.minV;
      return (double)((f - this.minV) / f2 * 16.0F);
   }

   @Override
   public String toString() {
      return "TextureAtlasSprite{name='"
         + this.iconName
         + '\''
         + ", frameCount="
         + this.framesTextureData.size()
         + ", rotated="
         + this.rotated
         + ", x="
         + this.originX
         + ", y="
         + this.originY
         + ", height="
         + this.height
         + ", width="
         + this.width
         + ", u0="
         + this.minU
         + ", u1="
         + this.maxU
         + ", v0="
         + this.minV
         + ", v1="
         + this.maxV
         + '}';
   }

   public void setAnimationMetadata(AnimationMetadataSection animationMetadataSection) {
      this.animationMetadata = animationMetadataSection;
   }

   private void updateAnimationInterpolated() {
      double d = 1.0 - (double)this.tickCounter / (double)this.animationMetadata.getFrameTimeSingle(this.frameCounter);
      int frameIndex = this.animationMetadata.getFrameIndex(this.frameCounter);
      int n = this.animationMetadata.getFrameCount() == 0 ? this.framesTextureData.size() : this.animationMetadata.getFrameCount();
      int frameIndex2 = this.animationMetadata.getFrameIndex((this.frameCounter + 1) % n);
      if (frameIndex != frameIndex2 && frameIndex2 >= 0 && frameIndex2 < this.framesTextureData.size()) {
         int[][] intsArr = (int[][])this.framesTextureData.get(frameIndex);
         int[][] intsArr2 = (int[][])this.framesTextureData.get(frameIndex2);
         if (this.interpolatedFrameData == null || this.interpolatedFrameData.length != intsArr.length) {
            this.interpolatedFrameData = new int[intsArr.length][];
         }

         for (int i = 0; i < intsArr.length; i++) {
            if (this.interpolatedFrameData[i] == null) {
               this.interpolatedFrameData[i] = new int[intsArr[i].length];
            }

            if (i < intsArr2.length && intsArr2[i].length == intsArr[i].length) {
               for (int j = 0; j < intsArr[i].length; j++) {
                  int n2 = intsArr[i][j];
                  int n3 = intsArr2[i][j];
                  int n4 = (int)((double)((n2 & 0xFF0000) >> 16) * d + (double)((n3 & 0xFF0000) >> 16) * (1.0 - d));
                  int n5 = (int)((double)((n2 & 0xFF00) >> 8) * d + (double)((n3 & 0xFF00) >> 8) * (1.0 - d));
                  int n6 = (int)((double)(n2 & 0xFF) * d + (double)(n3 & 0xFF) * (1.0 - d));
                  this.interpolatedFrameData[i][j] = n2 & 0xFF000000 | n4 << 16 | n5 << 8 | n6;
               }
            }
         }

         TextureUtil.uploadTextureMipmap(this.interpolatedFrameData, this.width, this.height, this.originX, this.originY, false, false);
      }
   }

   private void allocateFrameTextureData(int n) {
      if (this.framesTextureData.size() <= n) {
         for (int i = this.framesTextureData.size(); i <= n; i++) {
            this.framesTextureData.add(null);
         }
      }

      if (this.spriteSingle != null) {
         this.spriteSingle.allocateFrameTextureData(n);
      }
   }

   public boolean hasAnimationMetadata() {
      return this.animationMetadata != null;
   }

   public float getInterpolatedU(double d) {
      float f = this.maxU - this.minU;
      return this.minU + f * (float)d / 16.0F;
   }

   private void fixTransparentColor(int[] ints) {
      if (ints != null) {
         long l = 0L;
         long l2 = 0L;
         long l3 = 0L;
         long l4 = 0L;

         for (int i = 0; i < ints.length; i++) {
            int n = ints[i];
            if ((n >> 24 & 0xFF) >= 16) {
               int n3 = n >> 16 & 0xFF;
               int n4 = n >> 8 & 0xFF;
               int n5 = n & 0xFF;
               l += (long)n3;
               l2 += (long)n4;
               l3 += (long)n5;
               l4++;
            }
         }

         if (l4 > 0L) {
            int n9 = ((int)(l / l4)) << 16 | ((int)(l2 / l4)) << 8 | ((int)(l3 / l4));

            for (int j = 0; j < ints.length; j++) {
               if ((ints[j] >> 24 & 0xFF) <= 16) {
                  ints[j] = n9;
               }
            }
         }
      }
   }

   public String getIconName() {
      return this.iconName;
   }

   public float toSingleU(float f) {
      f -= this.baseU;
      return f * ((float)this.sheetWidth / (float)this.width);
   }

   public int[][] getFrameTextureData(int n) {
      return (int[][])this.framesTextureData.get(n);
   }

   public void deleteSpriteTexture() {
      if (this.glSpriteTextureId >= 0) {
         TextureUtil.deleteTexture(this.glSpriteTextureId);
         this.glSpriteTextureId = -1;
      }
   }

   public static TextureAtlasSprite makeAtlasSprite(ResourceLocation location) {
      String str = location.toString();
      return (TextureAtlasSprite)(locationNameClock.equals(str)
         ? new TextureClock(str)
         : (locationNameCompass.equals(str) ? new TextureCompass(str) : new TextureAtlasSprite(str)));
   }

   public boolean load(IResourceManager resourceManager, ResourceLocation location) {
      return true;
   }

   public static void setLocationNameCompass(String str) {
      locationNameCompass = str;
   }

   public void loadSprite(BufferedImage[] images, AnimationMetadataSection animationMetadataSection) throws IOException {
      this.resetSprite();
      int width2 = images[0].getWidth();
      int height2 = images[0].getHeight();
      this.width = width2;
      this.height = height2;
      if (this.spriteSingle != null) {
         this.spriteSingle.width = this.width;
         this.spriteSingle.height = this.height;
      }

      int[][] intsArr = new int[images.length][];

      for (int i = 0; i < images.length; i++) {
         BufferedImage image = images[i];
         if (image != null) {
            if (this.width >> i != image.getWidth()) {
               image = TextureUtils.scaleImage(image, this.width >> i);
            }

            if (i > 0 && (image.getWidth() != width2 >> i || image.getHeight() != height2 >> i)) {
               throw new RuntimeException(
                  String.format(
                     "Unable to load miplevel: %d, image is size: %dx%d, expected %dx%d", i, image.getWidth(), image.getHeight(), width2 >> i, height2 >> i
                  )
               );
            }

            intsArr[i] = new int[image.getWidth() * image.getHeight()];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), intsArr[i], 0, image.getWidth());
         }
      }

      if (animationMetadataSection == null) {
         if (height2 != width2) {
            throw new RuntimeException("broken aspect ratio and not an animation");
         }

         this.framesTextureData.add(intsArr);
      } else {
         int n = height2 / width2;
         this.height = this.width;
         if (animationMetadataSection.getFrameCount() > 0) {
            for (int n2 : (Iterable<Integer>)(animationMetadataSection.getFrameIndexSet())) {
               if (n2 >= n) {
                  throw new RuntimeException("invalid frameindex " + n2);
               }

               this.allocateFrameTextureData(n2);
               this.framesTextureData.set(n2, getFrameTextureData(intsArr, width2, width2, n2));
            }

            this.animationMetadata = animationMetadataSection;
         } else {
            ArrayList list = Lists.newArrayList();

            for (int j = 0; j < n; j++) {
               this.framesTextureData.add(getFrameTextureData(intsArr, width2, width2, j));
               list.add(new AnimationFrame(j, -1));
            }

            this.animationMetadata = new AnimationMetadataSection(list, this.width, this.height, animationMetadataSection.getFrameTime(), animationMetadataSection.isInterpolate());
         }
      }

      if (!this.isShadersSprite) {
         if (Config.isShaderPackLoaded()) {
            this.loadShadersSprites();
         }

         for (int k = 0; k < this.framesTextureData.size(); k++) {
            int[][] intsArr2 = (int[][])this.framesTextureData.get(k);
            if (intsArr2 != null && !this.iconName.startsWith("minecraft:blocks/leaves_")) {
               for (int m = 0; m < intsArr2.length; m++) {
                  this.fixTransparentColor(intsArr2[m]);
               }
            }
         }

         if (this.spriteSingle != null) {
            this.spriteSingle.loadSprite(images, animationMetadataSection);
         }
      }
   }

   public float getMinV() {
      return this.minV;
   }

   public static void setLocationNameClock(String str) {
      locationNameClock = str;
   }

   public void setIconWidth(int n) {
      this.width = n;
      if (this.spriteSingle != null) {
         this.spriteSingle.setIconWidth(this.width);
      }
   }

   public void setIndexInMap(int n) {
      this.indexInMap = n;
   }

   public void setAnimationIndex(int n) {
      this.animationIndex = n;
      if (this.spriteNormal != null) {
         this.spriteNormal.setAnimationIndex(n);
      }

      if (this.spriteSpecular != null) {
         this.spriteSpecular.setAnimationIndex(n);
      }
   }

   private TextureAtlasSprite(String str, boolean flag) {
      this.iconName = str;
      this.isSpriteSingle = flag;
   }

   public AnimationMetadataSection getAnimationMetadata() {
      return this.animationMetadata;
   }

   public int getIconHeight() {
      return this.height;
   }

   public float toSingleV(float f) {
      f -= this.baseV;
      return f * ((float)this.sheetHeight / (float)this.height);
   }

   public List getFramesTextureData() {
      ArrayList list = new ArrayList();
      list.addAll(this.framesTextureData);
      return list;
   }

   public void generateMipmaps(int n) {
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < this.framesTextureData.size(); i++) {
         int[][] intsArr = (int[][])this.framesTextureData.get(i);
         if (intsArr != null) {
            try {
               list.add(TextureUtil.generateMipmapData(n, this.width, intsArr));
            } catch (Throwable e) {
               CrashReport crashReport = CrashReport.makeCrashReport(e, "Generating mipmaps for frame");
               CrashReportCategory crashReportCategory = crashReport.makeCategory("Frame being iterated");
               crashReportCategory.addCrashSection("Frame index", i);
               crashReportCategory.addCrashSectionCallable("Frame sizes", new TextureAtlasSprite.ValueCallable(this, intsArr));
               throw new ReportedException(crashReport);
            }
         }
      }

      this.setFramesTextureData(list);
      if (this.spriteSingle != null) {
         this.spriteSingle.generateMipmaps(n);
      }
   }

   public void initSprite(int n, int n2, int n3, int n4, boolean flag) {
      this.originX = n3;
      this.originY = n4;
      this.rotated = flag;
      float f = (float)(0.01F / (double)n);
      float f2 = (float)(0.01F / (double)n2);
      this.minU = (float)n3 / (float)((double)n) + f;
      this.maxU = (float)(n3 + this.width) / (float)((double)n) - f;
      this.minV = (float)n4 / (float)n2 + f2;
      this.maxV = (float)(n4 + this.height) / (float)n2 - f2;
      this.baseU = Math.min(this.minU, this.maxU);
      this.baseV = Math.min(this.minV, this.maxV);
      if (this.spriteSingle != null) {
         this.spriteSingle.initSprite(this.width, this.height, 0, 0, false);
      }

      if (this.spriteNormal != null) {
         this.spriteNormal.copyFrom(this);
      }

      if (this.spriteSpecular != null) {
         this.spriteSpecular.copyFrom(this);
      }
   }


   public static class ValueCallable implements Callable {
      public final int[][] frameData;
      public final TextureAtlasSprite textureAtlasSprite;

      public String getFrameSizes() throws Exception {
         StringBuilder sb = new StringBuilder();

         for (int[] ints : this.frameData) {
            if (sb.length() > 0) {
               sb.append(", ");
            }

            sb.append(ints == null ? "null" : ints.length);
         }

         return sb.toString();
      }

      @Override
      public Object call() throws Exception {
         return this.getFrameSizes();
      }

      public ValueCallable(TextureAtlasSprite textureAtlasSprite2, int[][] intsArr) {
         this.textureAtlasSprite = textureAtlasSprite2;
         this.frameData = intsArr;
      }
   }
}
