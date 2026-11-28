package net.minecraft.client.renderer.texture;

import java.util.concurrent.Callable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import net.optifine.SmartAnimations;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.optifine.BetterGrass;
import net.optifine.ConnectedTextures;
import net.optifine.CustomItems;
import net.optifine.EmissiveTextures;
import net.optifine.config.Config;
import net.optifine.shaders.ShadersTex;
import net.optifine.util.CounterInt;
import net.optifine.util.TextureUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureMap extends AbstractTexture implements ITickableTextureObject {
   private static final boolean SKIP_FIRST = Boolean.parseBoolean(System.getProperty("fml.skipFirstTextureLoad", "true"));
   private static final Logger LOGGER = LogManager.getLogger();
   public static final ResourceLocation LOCATION_MISSING_TEXTURE = new ResourceLocation("missingno");
   public static final ResourceLocation locationBlocksTexture = new ResourceLocation("textures/atlas/blocks.png");
   private final List listAnimatedSprites;
   private final Map mapRegisteredSprites;
   private final Map mapUploadedSprites;
   private final String basePath;
   private final IIconCreator iconCreator;
   private int mipmapLevels;
   private final TextureAtlasSprite missingImage;
   private boolean skipFirst = false;
   private TextureAtlasSprite[] iconGrid = null;
   private int iconGridSize = -1;
   private int iconGridCountX = -1;
   private int iconGridCountY = -1;
   private double iconGridSizeU = -1.0;
   private double iconGridSizeV = -1.0;
   private CounterInt counterIndexInMap = new CounterInt(0);
   public int atlasWidth = 0;
   public int atlasHeight = 0;
   private int countAnimationsActive;
   private int frameCountAnimations;

   public boolean isBound() {
      return GlStateManager.getBoundTexture() == this.getGlTextureId();
   }

   public int getMipmapLevels() {
      return this.mipmapLevels;
   }

   public int getCountAnimationsActive() {
      return this.countAnimationsActive;
   }

   private int detectMinimumSpriteSize(Map map, IResourceManager resourceManager, int n) {
      HashMap map2 = new HashMap();

      for (Object obj : map.entrySet()) {
         TextureAtlasSprite value = (TextureAtlasSprite)((Entry)obj).getValue();
         ResourceLocation location = new ResourceLocation(value.getIconName());
         ResourceLocation location2 = this.completeResourceLocation(location);
         if (!value.hasCustomLoader(resourceManager, location)) {
            try {
               IResource resource = resourceManager.getResource(location2);
               if (resource != null) {
                  InputStream inputStream = resource.getInputStream();
                  if (inputStream != null) {
                     Dimension imageSize = TextureUtils.getImageSize(inputStream, "png");
                     inputStream.close();
                     if (imageSize != null) {
                        int n2 = MathHelper.roundUpToPowerOfTwo(imageSize.width);
                        if (!map2.containsKey(n2)) {
                           map2.put(n2, 1);
                        } else {
                           int n3 = (Integer)map2.get(n2);
                           map2.put(n2, n3 + 1);
                        }
                     }
                  }
               }
            } catch (Exception e) {
            }
         }
      }

      int n4 = 0;
      Set keys = map2.keySet();
      TreeSet treeSet = new TreeSet(keys);

      for (int n5 : (Iterable<Integer>)(treeSet)) {
         int n6 = (Integer)map2.get(n5);
         n4 += n6;
      }

      int n7 = 16;
      int n8 = 0;
      int n9 = n4 * n / 100;

      for (int n10 : (Iterable<Integer>)(treeSet)) {
         int n11 = (Integer)map2.get(n10);
         n8 += n11;
         if (n10 > n7) {
            n7 = n10;
         }

         if (n8 > n9) {
            return n7;
         }
      }

      return n7;
   }

   public TextureAtlasSprite getAtlasSprite(String str) {
      TextureAtlasSprite textureAtlasSprite = (TextureAtlasSprite)this.mapUploadedSprites.get(str);
      if (textureAtlasSprite == null) {
         textureAtlasSprite = this.missingImage;
      }

      return textureAtlasSprite;
   }

   public int getCountRegisteredSprites() {
      return this.counterIndexInMap.getValue();
   }

   public void updateAnimations() {
      boolean flag = false;
      boolean flag2 = false;
      TextureUtil.bindTexture(this.getGlTextureId());
      int n = 0;

      for (TextureAtlasSprite textureAtlasSprite : (Iterable<TextureAtlasSprite>)(this.listAnimatedSprites)) {
         if (this.isTerrainAnimationActive(textureAtlasSprite)) {
            textureAtlasSprite.updateAnimation();
            if (textureAtlasSprite.isAnimationActive()) {
               n++;
            }

            if (textureAtlasSprite.spriteNormal != null) {
               flag = true;
            }

            if (textureAtlasSprite.spriteSpecular != null) {
               flag2 = true;
            }
         }
      }

      if (Config.isMultiTexture()) {
         for (TextureAtlasSprite textureAtlasSprite2 : (Iterable<TextureAtlasSprite>)(this.listAnimatedSprites)) {
            if (this.isTerrainAnimationActive(textureAtlasSprite2)) {
               TextureAtlasSprite spriteSingle = textureAtlasSprite2.spriteSingle;
               if (spriteSingle != null) {
                  if (textureAtlasSprite2 == TextureUtils.iconClock || textureAtlasSprite2 == TextureUtils.iconCompass) {
                     spriteSingle.frameCounter = textureAtlasSprite2.frameCounter;
                  }

                  textureAtlasSprite2.bindSpriteTexture();
                  spriteSingle.updateAnimation();
                  if (spriteSingle.isAnimationActive()) {
                     n++;
                  }
               }
            }
         }

         TextureUtil.bindTexture(this.getGlTextureId());
      }

      if (Config.isShaderPackLoaded()) {
         if (flag) {
            TextureUtil.bindTexture(this.getMultiTexID().norm);

            for (TextureAtlasSprite textureAtlasSprite3 : (Iterable<TextureAtlasSprite>)(this.listAnimatedSprites)) {
               if (textureAtlasSprite3.spriteNormal != null && this.isTerrainAnimationActive(textureAtlasSprite3)) {
                  if (textureAtlasSprite3 == TextureUtils.iconClock || textureAtlasSprite3 == TextureUtils.iconCompass) {
                     textureAtlasSprite3.spriteNormal.frameCounter = textureAtlasSprite3.frameCounter;
                  }

                  textureAtlasSprite3.spriteNormal.updateAnimation();
                  if (textureAtlasSprite3.spriteNormal.isAnimationActive()) {
                     n++;
                  }
               }
            }
         }

         if (flag2) {
            TextureUtil.bindTexture(this.getMultiTexID().spec);

            for (TextureAtlasSprite textureAtlasSprite4 : (Iterable<TextureAtlasSprite>)(this.listAnimatedSprites)) {
               if (textureAtlasSprite4.spriteSpecular != null && this.isTerrainAnimationActive(textureAtlasSprite4)) {
                  if (textureAtlasSprite4 == TextureUtils.iconClock || textureAtlasSprite4 == TextureUtils.iconCompass) {
                     textureAtlasSprite4.spriteNormal.frameCounter = textureAtlasSprite4.frameCounter;
                  }

                  textureAtlasSprite4.spriteSpecular.updateAnimation();
                  if (textureAtlasSprite4.spriteSpecular.isAnimationActive()) {
                     n++;
                  }
               }
            }
         }

         if (flag || flag2) {
            TextureUtil.bindTexture(this.getGlTextureId());
         }
      }

      int frameCount = Config.getMc().entityRenderer.frameCount;
      if (frameCount != this.frameCountAnimations) {
         this.countAnimationsActive = n;
         this.frameCountAnimations = frameCount;
      }

      if (SmartAnimations.isActive()) {
         SmartAnimations.resetSpritesRendered();
      }
   }

   public TextureAtlasSprite getRegisteredSprite(ResourceLocation location) {
      return (TextureAtlasSprite)this.mapRegisteredSprites.get(location.toString());
   }

   @Override
   public void loadTexture(IResourceManager resourceManager) throws IOException {
      if (this.iconCreator != null) {
         this.loadSprites(resourceManager, this.iconCreator);
      }
   }

   private int getMinSpriteSize() {
      int n = 1 << this.mipmapLevels;
      if (n < 8) {
         n = 8;
      }

      return n;
   }

   public TextureAtlasSprite getMissingSprite() {
      return this.missingImage;
   }

   public TextureAtlasSprite getSpriteSafe(String str) {
      ResourceLocation location = new ResourceLocation(str);
      return (TextureAtlasSprite)this.mapRegisteredSprites.get(location.toString());
   }

   public ResourceLocation completeResourceLocation(ResourceLocation location) {
      return this.completeResourceLocation(location, 0);
   }

   public void loadSprites(IResourceManager resourceManager, IIconCreator iconCreator2) {
      this.mapRegisteredSprites.clear();
      this.counterIndexInMap.reset();
      iconCreator2.registerSprites(this);
      if (this.mipmapLevels >= 4) {
         this.mipmapLevels = this.detectMaxMipmapLevel(this.mapRegisteredSprites, resourceManager);
         Config.log("Mipmap levels: " + this.mipmapLevels);
      }

      this.initMissingImage();
      this.deleteGlTexture();
      this.loadTextureAtlas(resourceManager);
   }

   private void updateIconGrid(int n, int n2) {
      this.iconGridCountX = -1;
      this.iconGridCountY = -1;
      this.iconGrid = null;
      if (this.iconGridSize > 0) {
         this.iconGridCountX = n / this.iconGridSize;
         this.iconGridCountY = n2 / this.iconGridSize;
         this.iconGrid = new TextureAtlasSprite[this.iconGridCountX * this.iconGridCountY];
         this.iconGridSizeU = 1.0 / (double)this.iconGridCountX;
         this.iconGridSizeV = 1.0 / (double)this.iconGridCountY;

         for (TextureAtlasSprite textureAtlasSprite : (Iterable<TextureAtlasSprite>)(this.mapUploadedSprites.values())) {
            double d = 0.5 / (double)n;
            double d2 = 0.5 / (double)n2;
            double d3 = (double)Math.min(textureAtlasSprite.getMinU(), textureAtlasSprite.getMaxU()) + d;
            double d4 = (double)Math.min(textureAtlasSprite.getMinV(), textureAtlasSprite.getMaxV()) + d2;
            double d5 = (double)Math.max(textureAtlasSprite.getMinU(), textureAtlasSprite.getMaxU()) - d;
            double d6 = (double)Math.max(textureAtlasSprite.getMinV(), textureAtlasSprite.getMaxV()) - d2;
            int n3 = (int)(d3 / this.iconGridSizeU);
            int n4 = (int)(d4 / this.iconGridSizeV);
            int n5 = (int)(d5 / this.iconGridSizeU);
            int n6 = (int)(d6 / this.iconGridSizeV);

            for (int i = n3; i <= n5; i++) {
               if (i >= 0 && i < this.iconGridCountX) {
                  for (int j = n4; j <= n6; j++) {
                     if (j >= 0 && j < this.iconGridCountX) {
                        int n7 = j * this.iconGridCountX + i;
                        this.iconGrid[n7] = textureAtlasSprite;
                     } else {
                        Config.warn("Invalid grid V: " + j + ", icon: " + textureAtlasSprite.getIconName());
                     }
                  }
               } else {
                  Config.warn("Invalid grid U: " + i + ", icon: " + textureAtlasSprite.getIconName());
               }
            }
         }
      }
   }

   public boolean setTextureEntry(String str, TextureAtlasSprite textureAtlasSprite) {
      if (!this.mapRegisteredSprites.containsKey(str)) {
         this.mapRegisteredSprites.put(str, textureAtlasSprite);
         textureAtlasSprite.updateIndexInMap(this.counterIndexInMap);
         return true;
      } else {
         return false;
      }
   }

   public void setMipmapLevels(int n) {
      this.mipmapLevels = n;
   }

   public TextureAtlasSprite getIconByUV(double d, double d2) {
      if (this.iconGrid == null) {
         return null;
      } else {
         int n = (int)(d / this.iconGridSizeU);
         int n3 = ((int)(d2 / this.iconGridSizeV)) * this.iconGridCountX + n;
         return n3 >= 0 && n3 <= this.iconGrid.length ? this.iconGrid[n3] : null;
      }
   }

   public TextureMap(String str, IIconCreator iconCreator2, boolean flag) {
      this.listAnimatedSprites = Lists.newArrayList();
      this.mapRegisteredSprites = Maps.newHashMap();
      this.mapUploadedSprites = Maps.newHashMap();
      this.missingImage = new TextureAtlasSprite("missingno");
      this.basePath = str;
      this.iconCreator = iconCreator2;
      this.skipFirst = flag && SKIP_FIRST;
   }

   private boolean isAbsoluteLocation(ResourceLocation location) {
      return this.isAbsoluteLocationPath(location.getResourcePath());
   }

   @Override
   public void tick() {
      this.updateAnimations();
   }

   public int getCountAnimations() {
      return this.listAnimatedSprites.size();
   }

   private int[] makeMissingImageData(int n) {
      BufferedImage image = new BufferedImage(16, 16, 2);
      image.setRGB(0, 0, 16, 16, TextureUtil.missingTextureData, 0, 16);
      BufferedImage image2 = TextureUtils.scaleImage(image, n);
      int[] ints = new int[n * n];
      image2.getRGB(0, 0, n, n, ints, 0, n);
      return ints;
   }

   private void checkEmissive(ResourceLocation location, TextureAtlasSprite textureAtlasSprite) {
      String suffixEmissive = EmissiveTextures.getSuffixEmissive();
      if (suffixEmissive != null && !location.getResourcePath().endsWith(suffixEmissive)) {
         ResourceLocation location2 = new ResourceLocation(location.getResourceDomain(), location.getResourcePath() + suffixEmissive);
         if (Config.hasResource(this.completeResourceLocation(location2))) {
            TextureAtlasSprite textureAtlasSprite2 = this.registerSprite(location2);
            textureAtlasSprite2.isEmissive = true;
            textureAtlasSprite.spriteEmissive = textureAtlasSprite2;
         }
      }
   }

   public TextureMap(String str, boolean flag) {
      this(str, (IIconCreator)null, flag);
   }

   public TextureMap(String str, IIconCreator iconCreator2) {
      this(str, iconCreator2, false);
   }

   public ResourceLocation completeResourceLocation(ResourceLocation location, int n) {
      return this.isAbsoluteLocation(location)
         ? new ResourceLocation(location.getResourceDomain(), location.getResourcePath() + ".png")
         : (
            n == 0
               ? new ResourceLocation(location.getResourceDomain(), String.format("%s/%s%s", this.basePath, location.getResourcePath(), ".png"))
               : new ResourceLocation(location.getResourceDomain(), String.format("%s/mipmaps/%s.%d%s", this.basePath, location.getResourcePath(), n, ".png"))
         );
   }

   public TextureAtlasSprite getTextureEntry(String str) {
      return (TextureAtlasSprite)this.mapRegisteredSprites.get(str);
   }

   public String getBasePath() {
      return this.basePath;
   }

   public void loadTextureAtlas(IResourceManager resourceManager) {
      Config.dbg("Multitexture: " + Config.isMultiTexture());
      if (Config.isMultiTexture()) {
         for (TextureAtlasSprite textureAtlasSprite : (Iterable<TextureAtlasSprite>)(this.mapUploadedSprites.values())) {
            textureAtlasSprite.deleteSpriteTexture();
         }
      }

      ConnectedTextures.updateIcons(this);
      CustomItems.updateIcons(this);
      BetterGrass.updateIcons(this);
      int glMaximumTextureSize = TextureUtils.getGLMaximumTextureSize();
      Stitcher stitcher = new Stitcher(glMaximumTextureSize, glMaximumTextureSize, true, 0, this.mipmapLevels);
      this.mapUploadedSprites.clear();
      this.listAnimatedSprites.clear();
      int maxvalue = Integer.MAX_VALUE;
      int minSpriteSize = this.getMinSpriteSize();
      this.iconGridSize = minSpriteSize;
      int n = 1 << this.mipmapLevels;
      int n2 = 0;
      int n3 = 0;

      for (Entry entry : (Iterable<Entry>)(this.mapRegisteredSprites.entrySet())) {
         if (!this.skipFirst) {
            TextureAtlasSprite value = (TextureAtlasSprite)entry.getValue();
            ResourceLocation location = new ResourceLocation(value.getIconName());
            ResourceLocation location2 = this.completeResourceLocation(location, 0);
            value.updateIndexInMap(this.counterIndexInMap);
            if (value.hasCustomLoader(resourceManager, location)) {
               if (!value.load(resourceManager, location)) {
                  maxvalue = Math.min(maxvalue, Math.min(value.getIconWidth(), value.getIconHeight()));
                  stitcher.addSprite(value);
                  Config.detail("Custom loader (skipped): " + value);
                  n3++;
               }

               Config.detail("Custom loader: " + value);
               n2++;
            } else {
               try {
                  IResource resource = resourceManager.getResource(location2);
                  BufferedImage[] images = new BufferedImage[1 + this.mipmapLevels];
                  images[0] = TextureUtil.readBufferedImage(resource.getInputStream());
                  int width = images[0].getWidth();
                  int height = images[0].getHeight();
                  if (width < 1 || height < 1) {
                     Config.warn("Invalid sprite size: " + value);
                     continue;
                  }

                  if (width < minSpriteSize || this.mipmapLevels > 0) {
                     int n4 = this.mipmapLevels > 0 ? TextureUtils.scaleToGrid(width, minSpriteSize) : TextureUtils.scaleToMin(width, minSpriteSize);
                     if (n4 != width) {
                        if (!TextureUtils.isPowerOfTwo(width)) {
                           Config.log("Scaled non power of 2: " + value.getIconName() + ", " + width + " -> " + n4);
                        } else {
                           Config.log("Scaled too small texture: " + value.getIconName() + ", " + width + " -> " + n4);
                        }

                        int n5 = height * n4 / width;
                        images[0] = TextureUtils.scaleImage(images[0], n4);
                     }
                  }

                  TextureMetadataSection metadata = (TextureMetadataSection)resource.getMetadata("texture");
                  if (metadata != null) {
                     List listMipmaps = metadata.getListMipmaps();
                     if (!listMipmaps.isEmpty()) {
                        int width2 = images[0].getWidth();
                        int height2 = images[0].getHeight();
                        if (MathHelper.roundUpToPowerOfTwo(width2) != width2 || MathHelper.roundUpToPowerOfTwo(height2) != height2) {
                           throw new RuntimeException("Unable to load extra miplevels, source-texture is not power of two");
                        }
                     }

                     for (int n6 : (Iterable<Integer>)(listMipmaps)) {
                        if (n6 > 0 && n6 < images.length - 1 && images[n6] == null) {
                           ResourceLocation location3 = this.completeResourceLocation(location, n6);

                           try {
                              images[n6] = TextureUtil.readBufferedImage(resourceManager.getResource(location3).getInputStream());
                           } catch (IOException e) {
                              LOGGER.error("Unable to load miplevel {} from: {}", new Object[]{n6, location3, e});
                           }
                        }
                     }
                  }

                  value.loadSprite(images, (AnimationMetadataSection)resource.getMetadata("animation"));
               } catch (RuntimeException e2) {
                  LOGGER.error("Unable to parse metadata from " + location2, e2);
                  continue;
               } catch (IOException e3) {
                  LOGGER.error("Using missing texture, unable to load " + location2 + ", " + e3.getClass().getName());
                  continue;
               }

               maxvalue = Math.min(maxvalue, Math.min(value.getIconWidth(), value.getIconHeight()));
               int n7 = Math.min(Integer.lowestOneBit(value.getIconWidth()), Integer.lowestOneBit(value.getIconHeight()));
               if (n7 < n) {
                  LOGGER.warn(
                     "Texture {} with size {}x{} limits mip level from {} to {}",
                     new Object[]{
                        location2, value.getIconWidth(), value.getIconHeight(), MathHelper.calculateLogBaseTwo(n), MathHelper.calculateLogBaseTwo(n7)
                     }
                  );
                  n = n7;
               }

               stitcher.addSprite(value);
            }
            continue;
         }
         break;
      }

      if (n2 > 0) {
         Config.dbg("Custom loader sprites: " + n2);
      }

      if (n3 > 0) {
         Config.dbg("Custom loader sprites (skipped): " + n3);
      }

      int n8 = Math.min(maxvalue, n);
      int n9 = MathHelper.calculateLogBaseTwo(n8);
      if (n9 < 0) {
         n9 = 0;
      }

      if (n9 < this.mipmapLevels) {
         LOGGER.warn("{}: dropping miplevel from {} to {}, because of minimum power of two: {}", new Object[]{this.basePath, this.mipmapLevels, n9, n8});
         this.mipmapLevels = n9;
      }

      for (TextureAtlasSprite textureAtlasSprite2 : (Iterable<TextureAtlasSprite>)(this.mapRegisteredSprites.values())) {
         if (this.skipFirst) {
            break;
         }

         try {
            textureAtlasSprite2.generateMipmaps(this.mipmapLevels);
         } catch (Throwable e4) {
            CrashReport crashReport = CrashReport.makeCrashReport(e4, "Applying mipmap");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("Sprite being mipmapped");
            crashReportCategory.addCrashSectionCallable("Sprite name", new TextureMap.ValueCallable(this, textureAtlasSprite2));
            crashReportCategory.addCrashSectionCallable("Sprite size", new TextureMap.ValueCallable2(this, textureAtlasSprite2));
            crashReportCategory.addCrashSectionCallable("Sprite frames", new TextureMap.ValueCallable3(this, textureAtlasSprite2));
            crashReportCategory.addCrashSection("Mipmap levels", this.mipmapLevels);
            throw new ReportedException(crashReport);
         }
      }

      this.missingImage.generateMipmaps(this.mipmapLevels);
      stitcher.addSprite(this.missingImage);
      this.skipFirst = false;

      try {
         stitcher.doStitch();
      } catch (StitcherException e5) {
         throw e5;
      }

      LOGGER.info("Created: {}x{} {}-atlas", new Object[]{stitcher.getCurrentWidth(), stitcher.getCurrentHeight(), this.basePath});
      if (Config.isShaderPackLoaded()) {
         ShadersTex.allocateTextureMap(this.getGlTextureId(), this.mipmapLevels, stitcher.getCurrentWidth(), stitcher.getCurrentHeight(), stitcher, this);
      } else {
         TextureUtil.allocateTextureImpl(this.getGlTextureId(), this.mipmapLevels, stitcher.getCurrentWidth(), stitcher.getCurrentHeight());
      }

      HashMap map = Maps.newHashMap(this.mapRegisteredSprites);

      for (TextureAtlasSprite textureAtlasSprite3 : (Iterable<TextureAtlasSprite>)(stitcher.getStichSlots())) {
         String iconName = textureAtlasSprite3.getIconName();
         map.remove(iconName);
         this.mapUploadedSprites.put(iconName, textureAtlasSprite3);

         try {
            if (Config.isShaderPackLoaded()) {
               ShadersTex.uploadTexSubForLoadAtlas(
                  this,
                  textureAtlasSprite3.getIconName(),
                  textureAtlasSprite3.getFrameTextureData(0),
                  textureAtlasSprite3.getIconWidth(),
                  textureAtlasSprite3.getIconHeight(),
                  textureAtlasSprite3.getOriginX(),
                  textureAtlasSprite3.getOriginY(),
                  false,
                  false
               );
            } else {
               TextureUtil.uploadTextureMipmap(
                  textureAtlasSprite3.getFrameTextureData(0), textureAtlasSprite3.getIconWidth(), textureAtlasSprite3.getIconHeight(), textureAtlasSprite3.getOriginX(), textureAtlasSprite3.getOriginY(), false, false
               );
            }
         } catch (Throwable e6) {
            CrashReport crashReport2 = CrashReport.makeCrashReport(e6, "Stitching texture atlas");
            CrashReportCategory crashReportCategory2 = crashReport2.makeCategory("Texture being stitched together");
            crashReportCategory2.addCrashSection("Atlas path", this.basePath);
            crashReportCategory2.addCrashSection("Sprite", textureAtlasSprite3);
            throw new ReportedException(crashReport2);
         }

         if (textureAtlasSprite3.hasAnimationMetadata()) {
            textureAtlasSprite3.setAnimationIndex(this.listAnimatedSprites.size());
            this.listAnimatedSprites.add(textureAtlasSprite3);
         }
      }

      for (TextureAtlasSprite textureAtlasSprite4 : (Iterable<TextureAtlasSprite>)(map.values())) {
         textureAtlasSprite4.copyFrom(this.missingImage);
      }

      Config.log("Animated sprites: " + this.listAnimatedSprites.size());
      if (Config.isMultiTexture()) {
         int currentWidth = stitcher.getCurrentWidth();
         int currentHeight = stitcher.getCurrentHeight();

         for (TextureAtlasSprite textureAtlasSprite5 : (Iterable<TextureAtlasSprite>)(stitcher.getStichSlots())) {
            textureAtlasSprite5.sheetWidth = currentWidth;
            textureAtlasSprite5.sheetHeight = currentHeight;
            textureAtlasSprite5.mipmapLevels = this.mipmapLevels;
            TextureAtlasSprite spriteSingle = textureAtlasSprite5.spriteSingle;
            if (spriteSingle != null) {
               if (spriteSingle.getIconWidth() <= 0) {
                  spriteSingle.setIconWidth(textureAtlasSprite5.getIconWidth());
                  spriteSingle.setIconHeight(textureAtlasSprite5.getIconHeight());
                  spriteSingle.initSprite(textureAtlasSprite5.getIconWidth(), textureAtlasSprite5.getIconHeight(), 0, 0, false);
                  spriteSingle.clearFramesTextureData();
                  spriteSingle.setFramesTextureData(textureAtlasSprite5.getFramesTextureData());
                  spriteSingle.setAnimationMetadata(textureAtlasSprite5.getAnimationMetadata());
               }

               spriteSingle.sheetWidth = currentWidth;
               spriteSingle.sheetHeight = currentHeight;
               spriteSingle.mipmapLevels = this.mipmapLevels;
               spriteSingle.setAnimationIndex(textureAtlasSprite5.getAnimationIndex());
               textureAtlasSprite5.bindSpriteTexture();

               try {
                  TextureUtil.uploadTextureMipmap(
                     spriteSingle.getFrameTextureData(0), spriteSingle.getIconWidth(), spriteSingle.getIconHeight(), spriteSingle.getOriginX(), spriteSingle.getOriginY(), false, true
                  );
               } catch (Exception e7) {
                  Config.dbg("Error uploading sprite single: " + spriteSingle + ", parent: " + textureAtlasSprite5);
                  e7.printStackTrace();
               }
            }
         }

         Config.getMc().getTextureManager().bindTexture(locationBlocksTexture);
      }

      this.updateIconGrid(stitcher.getCurrentWidth(), stitcher.getCurrentHeight());
      if (Config.equals(System.getProperty("saveTextureMap"), "true")) {
         Config.dbg("Exporting texture map: " + this.basePath);
         TextureUtils.saveGlTexture(
            "debug/" + this.basePath.replaceAll("/", "_"), this.getGlTextureId(), this.mipmapLevels, stitcher.getCurrentWidth(), stitcher.getCurrentHeight()
         );
      }
   }

   public TextureMap(String str) {
      this(str, (IIconCreator)null);
   }

   public TextureAtlasSprite registerSprite(ResourceLocation location) {
      if (location == null) {
         throw new IllegalArgumentException("Location cannot be null!");
      } else {
         TextureAtlasSprite textureAtlasSprite = (TextureAtlasSprite)this.mapRegisteredSprites.get(location.toString());
         if (textureAtlasSprite == null) {
            textureAtlasSprite = TextureAtlasSprite.makeAtlasSprite(location);
            this.mapRegisteredSprites.put(location.toString(), textureAtlasSprite);
            textureAtlasSprite.updateIndexInMap(this.counterIndexInMap);
            if (Config.isEmissiveTextures()) {
               this.checkEmissive(location, textureAtlasSprite);
            }
         }

         return textureAtlasSprite;
      }
   }

   public boolean setTextureEntry(TextureAtlasSprite textureAtlasSprite) {
      return this.setTextureEntry(textureAtlasSprite.getIconName(), textureAtlasSprite);
   }

   private boolean isAbsoluteLocationPath(String str) {
      String str2 = str.toLowerCase();
      return str2.startsWith("mcpatcher/") || str2.startsWith("optifine/");
   }

   private void initMissingImage() {
      int minSpriteSize = this.getMinSpriteSize();
      int[] ints = this.makeMissingImageData(minSpriteSize);
      this.missingImage.setIconWidth(minSpriteSize);
      this.missingImage.setIconHeight(minSpriteSize);
      int[][] intsArr = new int[this.mipmapLevels + 1][];
      intsArr[0] = ints;
      this.missingImage.setFramesTextureData(Lists.newArrayList(new int[][][]{intsArr}));
      this.missingImage.setIndexInMap(this.counterIndexInMap.nextValue());
   }

   private int detectMaxMipmapLevel(Map map, IResourceManager resourceManager) {
      int n = this.detectMinimumSpriteSize(map, resourceManager, 20);
      if (n < 16) {
         n = 16;
      }

      n = MathHelper.roundUpToPowerOfTwo(n);
      if (n > 16) {
         Config.log("Sprite size: " + n);
      }

      int n2 = MathHelper.calculateLogBaseTwo(n);
      if (n2 < 4) {
         n2 = 4;
      }

      return n2;
   }

   private boolean isTerrainAnimationActive(TextureAtlasSprite textureAtlasSprite) {
      return textureAtlasSprite == TextureUtils.iconWaterStill || textureAtlasSprite == TextureUtils.iconWaterFlow
         ? Config.isAnimatedWater()
         : (
            textureAtlasSprite == TextureUtils.iconLavaStill || textureAtlasSprite == TextureUtils.iconLavaFlow
               ? Config.isAnimatedLava()
               : (
                  textureAtlasSprite == TextureUtils.iconFireLayer0 || textureAtlasSprite == TextureUtils.iconFireLayer1
                     ? Config.isAnimatedFire()
                     : (textureAtlasSprite == TextureUtils.iconPortal ? Config.isAnimatedPortal() : (textureAtlasSprite != TextureUtils.iconClock && textureAtlasSprite != TextureUtils.iconCompass ? Config.isAnimatedTerrain() : true))
               )
         );
   }


   public static class ValueCallable implements Callable {
      public final TextureAtlasSprite sprite;
      public final TextureMap textureMap;

      @Override
      public Object call() throws Exception {
         return this.getSpriteName();
      }

      public String getSpriteName() throws Exception {
         return this.sprite.getIconName();
      }

      public ValueCallable(TextureMap textureMap2, TextureAtlasSprite textureAtlasSprite) {
         this.textureMap = textureMap2;
         this.sprite = textureAtlasSprite;
      }
   }

   public static class ValueCallable2 implements Callable {
      public final TextureAtlasSprite sprite;
      public final TextureMap textureMap;

      public String getSpriteSize() throws Exception {
         return this.sprite.getIconWidth() + " x " + this.sprite.getIconHeight();
      }

      public ValueCallable2(TextureMap textureMap2, TextureAtlasSprite textureAtlasSprite) {
         this.textureMap = textureMap2;
         this.sprite = textureAtlasSprite;
      }

      @Override
      public Object call() throws Exception {
         return this.getSpriteSize();
      }
   }

   public static class ValueCallable3 implements Callable {
      public final TextureAtlasSprite sprite;
      public final TextureMap textureMap;

      public String getSpriteFrames() throws Exception {
         return this.sprite.getFrameCount() + " frames";
      }

      public ValueCallable3(TextureMap textureMap2, TextureAtlasSprite textureAtlasSprite) {
         this.textureMap = textureMap2;
         this.sprite = textureAtlasSprite;
      }

      @Override
      public Object call() throws Exception {
         return this.getSpriteFrames();
      }
   }
}
