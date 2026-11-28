package net.minecraft.client.renderer;

import com.google.common.primitives.Floats;
import java.util.Comparator;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.BitSet;
import net.optifine.SmartAnimations;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.optifine.config.Config;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;
import net.optifine.util.TextureUtils;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GL11;

public class WorldRenderer {
   private ByteBuffer byteBuffer;
   public IntBuffer rawIntBuffer;
   private ShortBuffer rawShortBuffer;
   public FloatBuffer rawFloatBuffer;
   public int vertexCount;
   private VertexFormatElement vertexFormatElement;
   private int vertexFormatIndex;
   private boolean noColor;
   public int drawMode;
   private double xOffset;
   private double yOffset;
   private double zOffset;
   private VertexFormat vertexFormat;
   private boolean isDrawing;
   private EnumWorldBlockLayer blockLayer = null;
   private boolean[] drawnIcons = new boolean[256];
   private TextureAtlasSprite[] quadSprites = null;
   private TextureAtlasSprite[] quadSpritesPrev = null;
   private TextureAtlasSprite quadSprite = null;
   public SVertexBuilder sVertexBuilder;
   public RenderEnv renderEnv = null;
   public BitSet animatedSprites = null;
   public BitSet animatedSpritesCached = new BitSet();
   private boolean modeTriangles = false;
   private ByteBuffer byteBufferTriangles;

   public void setSprite(TextureAtlasSprite textureAtlasSprite) {
      if (this.animatedSprites != null && textureAtlasSprite != null && textureAtlasSprite.getAnimationIndex() >= 0) {
         this.animatedSprites.set(textureAtlasSprite.getAnimationIndex());
      }

      if (this.quadSprites != null) {
         this.quadSprite = textureAtlasSprite;
      }
   }

   public WorldRenderer lightmap(int n, int n2) {
      int n3 = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);
      switch (this.vertexFormatElement.getType()) {
         case FLOAT:
            this.byteBuffer.putFloat(n3, (float)n);
            this.byteBuffer.putFloat(n3 + 4, (float)n2);
            break;
         case UINT:
         case INT:
            this.byteBuffer.putInt(n3, n);
            this.byteBuffer.putInt(n3 + 4, n2);
            break;
         case USHORT:
         case SHORT:
            this.byteBuffer.putShort(n3, (short)n2);
            this.byteBuffer.putShort(n3 + 2, (short)n);
            break;
         case UBYTE:
         case BYTE:
            this.byteBuffer.put(n3, (byte)n2);
            this.byteBuffer.put(n3 + 1, (byte)n);
      }

      this.nextVertexFormatIndex();
      return this;
   }

   public void addVertexData(int[] ints) {
      if (Config.isShaderPackLoaded()) {
         SVertexBuilder.beginAddVertexData(this, ints);
      }

      this.growBuffer(ints.length);
      ((Buffer)this.rawIntBuffer).position(this.getBufferSize());
      this.rawIntBuffer.put(ints);
      this.vertexCount = this.vertexCount + ints.length / this.vertexFormat.getIntegerSize();
      if (Config.isShaderPackLoaded()) {
         SVertexBuilder.endAddVertexData(this);
      }
   }

   public void setTranslation(double d, double d2, double d3) {
      this.xOffset = d;
      this.yOffset = d2;
      this.zOffset = d3;
   }

   public void putColorRGB_F(float f, float f2, float f3, int n) {
      this.putColorRGBA(this.getColorIndex(n), MathHelper.clamp_int((int)(f * 255.0F), 0, 255), MathHelper.clamp_int((int)(f2 * 255.0F), 0, 255), MathHelper.clamp_int((int)(f3 * 255.0F), 0, 255), 255);
   }

   public void noColor() {
      this.noColor = true;
   }

   public int getColorIndex(int n) {
      return ((this.vertexCount - n) * this.vertexFormat.getNextOffset() + this.vertexFormat.getColorOffset()) / 4;
   }

   public WorldRenderer color(int n, int n2, int n3, int n4) {
      if (this.noColor) {
         return this;
      } else {
         int n5 = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);
         switch (this.vertexFormatElement.getType()) {
            case FLOAT:
               this.byteBuffer.putFloat(n5, (float)n / 255.0F);
               this.byteBuffer.putFloat(n5 + 4, (float)n2 / 255.0F);
               this.byteBuffer.putFloat(n5 + 8, (float)n3 / 255.0F);
               this.byteBuffer.putFloat(n5 + 12, (float)n4 / 255.0F);
               break;
            case UINT:
            case INT:
               this.byteBuffer.putFloat(n5, (float)n);
               this.byteBuffer.putFloat(n5 + 4, (float)n2);
               this.byteBuffer.putFloat(n5 + 8, (float)n3);
               this.byteBuffer.putFloat(n5 + 12, (float)n4);
               break;
            case USHORT:
            case SHORT:
               this.byteBuffer.putShort(n5, (short)n);
               this.byteBuffer.putShort(n5 + 2, (short)n2);
               this.byteBuffer.putShort(n5 + 4, (short)n3);
               this.byteBuffer.putShort(n5 + 6, (short)n4);
               break;
            case UBYTE:
            case BYTE:
               if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                  this.byteBuffer.put(n5, (byte)n);
                  this.byteBuffer.put(n5 + 1, (byte)n2);
                  this.byteBuffer.put(n5 + 2, (byte)n3);
                  this.byteBuffer.put(n5 + 3, (byte)n4);
               } else {
                  this.byteBuffer.put(n5, (byte)n4);
                  this.byteBuffer.put(n5 + 1, (byte)n3);
                  this.byteBuffer.put(n5 + 2, (byte)n2);
                  this.byteBuffer.put(n5 + 3, (byte)n);
               }
         }

         this.nextVertexFormatIndex();
         return this;
      }
   }

   public void putPosition(double d, double d2, double d3) {
      int integerSize = this.vertexFormat.getIntegerSize();
      int n = (this.vertexCount - 4) * integerSize;

      for (int i = 0; i < 4; i++) {
         int n2 = n + i * integerSize;
         int n3 = n2 + 1;
         int n4 = n3 + 1;
         this.rawIntBuffer.put(n2, Float.floatToRawIntBits((float)(d + this.xOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(n2))));
         this.rawIntBuffer.put(n3, Float.floatToRawIntBits((float)(d2 + this.yOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(n3))));
         this.rawIntBuffer.put(n4, Float.floatToRawIntBits((float)(d3 + this.zOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(n4))));
      }
   }

   public void reset() {
      this.vertexCount = 0;
      this.vertexFormatElement = null;
      this.vertexFormatIndex = 0;
      this.quadSprite = null;
      if (SmartAnimations.isActive()) {
         if (this.animatedSprites == null) {
            this.animatedSprites = this.animatedSpritesCached;
         }

         this.animatedSprites.clear();
      } else if (this.animatedSprites != null) {
         this.animatedSprites = null;
      }

      this.modeTriangles = false;
   }

   private static float getDistanceSq(FloatBuffer floatBuffer, float f, float f2, float f3, int n, int n2) {
      float f4 = floatBuffer.get(n2 + n * 0 + 0);
      float f5 = floatBuffer.get(n2 + n * 0 + 1);
      float f6 = floatBuffer.get(n2 + n * 0 + 2);
      float f7 = floatBuffer.get(n2 + n * 1 + 0);
      float f8 = floatBuffer.get(n2 + n * 1 + 1);
      float f9 = floatBuffer.get(n2 + n * 1 + 2);
      float f10 = floatBuffer.get(n2 + n * 2 + 0);
      float f11 = floatBuffer.get(n2 + n * 2 + 1);
      float f12 = floatBuffer.get(n2 + n * 2 + 2);
      float f13 = floatBuffer.get(n2 + n * 3 + 0);
      float f14 = floatBuffer.get(n2 + n * 3 + 1);
      float f15 = floatBuffer.get(n2 + n * 3 + 2);
      float f16 = (f4 + f7 + f10 + f13) * 0.25F - f;
      float f17 = (f5 + f8 + f11 + f14) * 0.25F - f2;
      float f18 = (f6 + f9 + f12 + f15) * 0.25F - f3;
      return f16 * f16 + f17 * f17 + f18 * f18;
   }

   public double getXOffset() {
      return this.xOffset;
   }

   public void sortVertexData(float f, float f2, float f3) {
      int n = this.vertexCount / 4;
      float[] floats = new float[n];

      for (int i = 0; i < n; i++) {
         floats[i] = getDistanceSq(
            this.rawFloatBuffer,
            (float)((double)f + this.xOffset),
            (float)((double)f2 + this.yOffset),
            (float)((double)f3 + this.zOffset),
            this.vertexFormat.getIntegerSize(),
            i * this.vertexFormat.getNextOffset()
         );
      }

      Integer[] nums = new Integer[n];

      for (int j = 0; j < nums.length; j++) {
         nums[j] = j;
      }

      Arrays.sort(nums, new WorldRenderer.EntryComparator(this, floats));
      BitSet bitSet = new BitSet();
      int nextOffset = this.vertexFormat.getNextOffset();
      int[] ints = new int[nextOffset];

      for (int k = 0; (k = bitSet.nextClearBit(k)) < nums.length; k++) {
         int n2 = nums[k];
         if (n2 != k) {
            ((Buffer)this.rawIntBuffer).limit(n2 * nextOffset + nextOffset);
            ((Buffer)this.rawIntBuffer).position(n2 * nextOffset);
            this.rawIntBuffer.get(ints);
            int n3 = n2;

            for (int m = nums[n2]; n3 != k; m = nums[m]) {
               ((Buffer)this.rawIntBuffer).limit(m * nextOffset + nextOffset);
               ((Buffer)this.rawIntBuffer).position(m * nextOffset);
               IntBuffer intBuffer = this.rawIntBuffer.slice();
               ((Buffer)this.rawIntBuffer).limit(n3 * nextOffset + nextOffset);
               ((Buffer)this.rawIntBuffer).position(n3 * nextOffset);
               this.rawIntBuffer.put(intBuffer);
               bitSet.set(n3);
               n3 = m;
            }

            ((Buffer)this.rawIntBuffer).limit(k * nextOffset + nextOffset);
            ((Buffer)this.rawIntBuffer).position(k * nextOffset);
            this.rawIntBuffer.put(ints);
         }

         bitSet.set(k);
      }

      ((Buffer)this.rawIntBuffer).limit(this.rawIntBuffer.capacity());
      ((Buffer)this.rawIntBuffer).position(this.getBufferSize());
      if (this.quadSprites != null) {
         TextureAtlasSprite[] textureAtlasSprites = new TextureAtlasSprite[this.vertexCount / 4];
         int n4 = this.vertexFormat.getNextOffset() / 4 * 4;

         for (int idx = 0; idx < nums.length; idx++) {
            int n5 = nums[idx];
            textureAtlasSprites[idx] = this.quadSprites[n5];
         }

         System.arraycopy(textureAtlasSprites, 0, this.quadSprites, 0, textureAtlasSprites.length);
      }
   }

   public RenderEnv getRenderEnv(IBlockState state, BlockPos pos) {
      if (this.renderEnv == null) {
         this.renderEnv = new RenderEnv(state, pos);
         return this.renderEnv;
      } else {
         this.renderEnv.reset(state, pos);
         return this.renderEnv;
      }
   }

   private void growBuffer(int n) {
      if (n > this.rawIntBuffer.remaining()) {
         int n2 = this.byteBuffer.capacity();
         int n3 = n2 % 2097152;
         int n4 = n3 + (((this.rawIntBuffer.position() + n) * 4 - n3) / 2097152 + 1) * 2097152;
         LogManager.getLogger().warn("Needed to grow BufferBuilder buffer: Old size " + n2 + " bytes, new size " + n4 + " bytes.");
         int n5 = this.rawIntBuffer.position();
         ByteBuffer buf = GLAllocation.createDirectByteBuffer(n4);
         ((Buffer)this.byteBuffer).position(0);
         buf.put(this.byteBuffer);
         ((Buffer)buf).rewind();
         this.byteBuffer = buf;
         this.rawFloatBuffer = this.byteBuffer.asFloatBuffer();
         this.rawIntBuffer = this.byteBuffer.asIntBuffer();
         ((Buffer)this.rawIntBuffer).position(n5);
         this.rawShortBuffer = this.byteBuffer.asShortBuffer();
         ((Buffer)this.rawShortBuffer).position(n5 << 1);
         if (this.quadSprites != null) {
            TextureAtlasSprite[] quadSprites2 = this.quadSprites;
            this.quadSprites = new TextureAtlasSprite[this.getBufferQuadSize()];
            System.arraycopy(quadSprites2, 0, this.quadSprites, 0, Math.min(quadSprites2.length, this.quadSprites.length));
            this.quadSpritesPrev = null;
         }
      }
   }

   public int getVertexCount() {
      return this.modeTriangles ? this.vertexCount / 4 * 6 : this.vertexCount;
   }

   public void drawMultiTexture() {
      if (this.quadSprites != null) {
         int countRegisteredSprites = Config.getMc().getTextureMapBlocks().getCountRegisteredSprites();
         if (this.drawnIcons.length <= countRegisteredSprites) {
            this.drawnIcons = new boolean[countRegisteredSprites + 1];
         }

         Arrays.fill(this.drawnIcons, false);
         int n = 0;
         int n2 = -1;
         int n3 = this.vertexCount / 4;

         for (int i = 0; i < n3; i++) {
            TextureAtlasSprite textureAtlasSprite = this.quadSprites[i];
            if (textureAtlasSprite != null) {
               int indexInMap = textureAtlasSprite.getIndexInMap();
               if (!this.drawnIcons[indexInMap]) {
                  if (textureAtlasSprite == TextureUtils.iconGrassSideOverlay) {
                     if (n2 < 0) {
                        n2 = i;
                     }
                  } else {
                     i = this.drawForIcon(textureAtlasSprite, i) - 1;
                     n++;
                     if (this.blockLayer != EnumWorldBlockLayer.TRANSLUCENT) {
                        this.drawnIcons[indexInMap] = true;
                     }
                  }
               }
            }
         }

         if (n2 >= 0) {
            this.drawForIcon(TextureUtils.iconGrassSideOverlay, n2);
            n++;
         }

         if (n > 0) {
         }
      }
   }

   private void draw(int n, int n2) {
      int n3 = n2 - n;
      if (n3 > 0) {
         int n4 = n * 4;
         int n5 = n3 * 4;
         GL11.glDrawArrays(this.drawMode, n4, n5);
      }
   }

   public ByteBuffer getByteBuffer() {
      return this.modeTriangles ? this.byteBufferTriangles : this.byteBuffer;
   }

   public boolean isNoColor() {
      return this.noColor;
   }

   public WorldRenderer color(float f, float f2, float f3, float f4) {
      return this.color((int)(f * 255.0F), (int)(f2 * 255.0F), (int)(f3 * 255.0F), (int)(f4 * 255.0F));
   }

   public void putColorMultiplierRgba(float f, float f2, float f3, float f4, int n) {
      int colorIndex = this.getColorIndex(n);
      int n2 = -1;
      if (!this.noColor) {
         n2 = this.rawIntBuffer.get(colorIndex);
         if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            int n3 = (int)((float)(n2 & 0xFF) * f);
            int n4 = (int)((float)(n2 >> 8 & 0xFF) * f2);
            int n5 = (int)((float)(n2 >> 16 & 0xFF) * f3);
            n2 = ((int)((float)(n2 >> 24 & 0xFF) * f4)) << 24 | n5 << 16 | n4 << 8 | n3;
         } else {
            n2 = ((int)((float)(n2 >> 24 & 0xFF) * f)) << 24 | ((int)((float)(n2 >> 16 & 0xFF) * f2)) << 16 | ((int)((float)(n2 >> 8 & 0xFF) * f3)) << 8 | ((int)((float)(n2 & 0xFF) * f4));
         }
      }

      this.rawIntBuffer.put(colorIndex, n2);
   }

   public void putBrightness4(int n, int n2, int n3, int n4) {
      int n5 = (this.vertexCount - 4) * this.vertexFormat.getIntegerSize() + this.vertexFormat.getUvOffsetById(1) / 4;
      int n6 = this.vertexFormat.getNextOffset() >> 2;
      this.rawIntBuffer.put(n5, n);
      this.rawIntBuffer.put(n5 + n6, n2);
      this.rawIntBuffer.put(n5 + n6 * 2, n3);
      this.rawIntBuffer.put(n5 + n6 * 3, n4);
   }

   public double getZOffset() {
      return this.zOffset;
   }

   public double getYOffset() {
      return this.yOffset;
   }

   public WorldRenderer(int n) {
      this.byteBuffer = GLAllocation.createDirectByteBuffer(n * 4);
      this.rawIntBuffer = this.byteBuffer.asIntBuffer();
      this.rawShortBuffer = this.byteBuffer.asShortBuffer();
      this.rawFloatBuffer = this.byteBuffer.asFloatBuffer();
      SVertexBuilder.initVertexBuilder(this);
   }

   public void setBlockLayer(EnumWorldBlockLayer enumWorldBlockLayer) {
      this.blockLayer = enumWorldBlockLayer;
      if (enumWorldBlockLayer == null) {
         if (this.quadSprites != null) {
            this.quadSpritesPrev = this.quadSprites;
         }

         this.quadSprites = null;
         this.quadSprite = null;
      }
   }

   public void putColorMultiplier(float f, float f2, float f3, int n) {
      int colorIndex = this.getColorIndex(n);
      int n2 = -1;
      if (!this.noColor) {
         n2 = this.rawIntBuffer.get(colorIndex);
         if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            int n3 = (int)((float)(n2 & 0xFF) * f);
            int n4 = (int)((float)(n2 >> 8 & 0xFF) * f2);
            int n5 = (int)((float)(n2 >> 16 & 0xFF) * f3);
            n2 &= -16777216;
            n2 = n2 | n5 << 16 | n4 << 8 | n3;
         } else {
            int n6 = (int)((float)(n2 >> 24 & 0xFF) * f);
            int n7 = (int)((float)(n2 >> 16 & 0xFF) * f2);
            int n8 = (int)((float)(n2 >> 8 & 0xFF) * f3);
            n2 &= 255;
            n2 = n2 | n6 << 24 | n7 << 16 | n8 << 8;
         }
      }

      this.rawIntBuffer.put(colorIndex, n2);
   }

   public boolean isDrawing() {
      return this.isDrawing;
   }

   private void nextVertexFormatIndex() {
      this.vertexFormatIndex++;
      this.vertexFormatIndex = this.vertexFormatIndex % this.vertexFormat.getElementCount();
      this.vertexFormatElement = this.vertexFormat.getElement(this.vertexFormatIndex);
      if (this.vertexFormatElement.getUsage() == VertexFormatElement.EnumUsage.PADDING) {
         this.nextVertexFormatIndex();
      }
   }

   public void putSprite(TextureAtlasSprite textureAtlasSprite) {
      if (this.animatedSprites != null && textureAtlasSprite != null && textureAtlasSprite.getAnimationIndex() >= 0) {
         this.animatedSprites.set(textureAtlasSprite.getAnimationIndex());
      }

      if (this.quadSprites != null) {
         int n = this.vertexCount / 4;
         this.quadSprites[n - 1] = textureAtlasSprite;
      }
   }

   public int getBufferSize() {
      return this.vertexCount * this.vertexFormat.getIntegerSize();
   }

   public WorldRenderer.State getVertexState() {
      ((Buffer)this.rawIntBuffer).rewind();
      int bufferSize = this.getBufferSize();
      ((Buffer)this.rawIntBuffer).limit(bufferSize);
      int[] ints = new int[bufferSize];
      this.rawIntBuffer.get(ints);
      ((Buffer)this.rawIntBuffer).limit(this.rawIntBuffer.capacity());
      ((Buffer)this.rawIntBuffer).position(bufferSize);
      TextureAtlasSprite[] textureAtlasSprites = null;
      if (this.quadSprites != null) {
         int n = this.vertexCount / 4;
         textureAtlasSprites = new TextureAtlasSprite[n];
         System.arraycopy(this.quadSprites, 0, textureAtlasSprites, 0, n);
      }

      return new WorldRenderer.State(this, ints, new VertexFormat(this.vertexFormat), textureAtlasSprites);
   }

   public void begin(int n, VertexFormat vertexFormat2) {
      if (this.isDrawing) {
         throw new IllegalStateException("Already building!");
      } else {
         this.isDrawing = true;
         this.reset();
         this.drawMode = n;
         this.vertexFormat = vertexFormat2;
         this.vertexFormatElement = vertexFormat2.getElement(this.vertexFormatIndex);
         this.noColor = false;
         ((Buffer)this.byteBuffer).limit(this.byteBuffer.capacity());
         if (Config.isShaderPackLoaded()) {
            SVertexBuilder.updateVertexFormatIndex(this);
         }

         if (Config.isMultiTexture()) {
            if (this.blockLayer != null) {
               if (this.quadSprites == null) {
                  this.quadSprites = this.quadSpritesPrev;
               }

               if (this.quadSprites == null || this.quadSprites.length < this.getBufferQuadSize()) {
                  this.quadSprites = new TextureAtlasSprite[this.getBufferQuadSize()];
               }
            }
         } else {
            if (this.quadSprites != null) {
               this.quadSpritesPrev = this.quadSprites;
            }

            this.quadSprites = null;
         }
      }
   }

   public void putColorRGBA(int n, int n2, int n3, int n4, int n5) {
      if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
         this.rawIntBuffer.put(n, n5 << 24 | n4 << 16 | n3 << 8 | n2);
      } else {
         this.rawIntBuffer.put(n, n2 << 24 | n3 << 16 | n4 << 8 | n5);
      }
   }

   public EnumWorldBlockLayer getBlockLayer() {
      return this.blockLayer;
   }

   public WorldRenderer tex(double d, double d2) {
      if (this.quadSprite != null && this.quadSprites != null) {
         d = (double)this.quadSprite.toSingleU((float)d);
         d2 = (double)this.quadSprite.toSingleV((float)d2);
         this.quadSprites[this.vertexCount / 4] = this.quadSprite;
      }

      int n = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);
      switch (this.vertexFormatElement.getType()) {
         case FLOAT:
            this.byteBuffer.putFloat(n, (float)d);
            this.byteBuffer.putFloat(n + 4, (float)d2);
            break;
         case UINT:
         case INT:
            this.byteBuffer.putInt(n, (int)d);
            this.byteBuffer.putInt(n + 4, (int)d2);
            break;
         case USHORT:
         case SHORT:
            this.byteBuffer.putShort(n, (short)((int)d2));
            this.byteBuffer.putShort(n + 2, (short)((int)d));
            break;
         case UBYTE:
         case BYTE:
            this.byteBuffer.put(n, (byte)((int)d2));
            this.byteBuffer.put(n + 1, (byte)((int)d));
      }

      this.nextVertexFormatIndex();
      return this;
   }

   public int getDrawMode() {
      return this.modeTriangles ? 4 : this.drawMode;
   }

   public void putColorRGB_F4(float f, float f2, float f3) {
      for (int i = 0; i < 4; i++) {
         this.putColorRGB_F(f, f2, f3, i + 1);
      }
   }

   public void putNormal(float f, float f2, float f3) {
      int n4 = ((byte)((int)(f * 127.0F)) & 255) | ((byte)((int)(f2 * 127.0F)) & 255) << 8 | ((byte)((int)(f3 * 127.0F)) & 255) << 16;
      int n5 = this.vertexFormat.getNextOffset() >> 2;
      int n6 = (this.vertexCount - 4) * n5 + this.vertexFormat.getNormalOffset() / 4;
      this.rawIntBuffer.put(n6, n4);
      this.rawIntBuffer.put(n6 + n5, n4);
      this.rawIntBuffer.put(n6 + n5 * 2, n4);
      this.rawIntBuffer.put(n6 + n5 * 3, n4);
   }

   public WorldRenderer normal(float f, float f2, float f3) {
      int n = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);
      switch (this.vertexFormatElement.getType()) {
         case FLOAT:
            this.byteBuffer.putFloat(n, f);
            this.byteBuffer.putFloat(n + 4, f2);
            this.byteBuffer.putFloat(n + 8, f3);
            break;
         case UINT:
         case INT:
            this.byteBuffer.putInt(n, (int)f);
            this.byteBuffer.putInt(n + 4, (int)f2);
            this.byteBuffer.putInt(n + 8, (int)f3);
            break;
         case USHORT:
         case SHORT:
            this.byteBuffer.putShort(n, (short)((int)(f * 32767.0F) & 65535));
            this.byteBuffer.putShort(n + 2, (short)((int)(f2 * 32767.0F) & 65535));
            this.byteBuffer.putShort(n + 4, (short)((int)(f3 * 32767.0F) & 65535));
            break;
         case UBYTE:
         case BYTE:
            this.byteBuffer.put(n, (byte)((int)(f * 127.0F) & 0xFF));
            this.byteBuffer.put(n + 1, (byte)((int)(f2 * 127.0F) & 0xFF));
            this.byteBuffer.put(n + 2, (byte)((int)(f3 * 127.0F) & 0xFF));
      }

      this.nextVertexFormatIndex();
      return this;
   }

   private int drawForIcon(TextureAtlasSprite textureAtlasSprite, int n) {
      GL11.glBindTexture(3553, textureAtlasSprite.glSpriteTextureId);
      int n2 = -1;
      int n3 = -1;
      int n4 = this.vertexCount / 4;

      for (int i = n; i < n4; i++) {
         if (this.quadSprites[i] == textureAtlasSprite) {
            if (n3 < 0) {
               n3 = i;
            }
         } else if (n3 >= 0) {
            this.draw(n3, i);
            if (this.blockLayer == EnumWorldBlockLayer.TRANSLUCENT) {
               return i;
            }

            n3 = -1;
            if (n2 < 0) {
               n2 = i;
            }
         }
      }

      if (n3 >= 0) {
         this.draw(n3, n4);
      }

      if (n2 < 0) {
         n2 = n4;
      }

      return n2;
   }

   public void finishDrawing() {
      if (!this.isDrawing) {
         throw new IllegalStateException("Not building!");
      } else {
         this.isDrawing = false;
         ((Buffer)this.byteBuffer).position(0);
         ((Buffer)this.byteBuffer).limit(this.getBufferSize() * 4);
      }
   }

   public WorldRenderer pos(double d, double d2, double d3) {
      if (Config.isShaderPackLoaded()) {
         SVertexBuilder.endSetVertexFormat(this);
      }

      int n = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);
      switch (this.vertexFormatElement.getType()) {
         case FLOAT:
            this.byteBuffer.putFloat(n, (float)(d + this.xOffset));
            this.byteBuffer.putFloat(n + 4, (float)(d2 + this.yOffset));
            this.byteBuffer.putFloat(n + 8, (float)(d3 + this.zOffset));
            break;
         case UINT:
         case INT:
            this.byteBuffer.putInt(n, Float.floatToRawIntBits((float)(d + this.xOffset)));
            this.byteBuffer.putInt(n + 4, Float.floatToRawIntBits((float)(d2 + this.yOffset)));
            this.byteBuffer.putInt(n + 8, Float.floatToRawIntBits((float)(d3 + this.zOffset)));
            break;
         case USHORT:
         case SHORT:
            this.byteBuffer.putShort(n, (short)((int)(d + this.xOffset)));
            this.byteBuffer.putShort(n + 2, (short)((int)(d2 + this.yOffset)));
            this.byteBuffer.putShort(n + 4, (short)((int)(d3 + this.zOffset)));
            break;
         case UBYTE:
         case BYTE:
            this.byteBuffer.put(n, (byte)((int)(d + this.xOffset)));
            this.byteBuffer.put(n + 1, (byte)((int)(d2 + this.yOffset)));
            this.byteBuffer.put(n + 2, (byte)((int)(d3 + this.zOffset)));
      }

      this.nextVertexFormatIndex();
      return this;
   }

   private int getBufferQuadSize() {
      return this.rawIntBuffer.capacity() * 4 / (this.vertexFormat.getIntegerSize() * 4);
   }

   public void putColor4(int n) {
      for (int i = 0; i < 4; i++) {
         this.putColor(n, i + 1);
      }
   }

   public void quadsToTriangles() {
      if (this.drawMode == 7) {
         if (this.byteBufferTriangles == null) {
            this.byteBufferTriangles = GLAllocation.createDirectByteBuffer(this.byteBuffer.capacity() * 2);
         }

         if (this.byteBufferTriangles.capacity() < this.byteBuffer.capacity() * 2) {
            this.byteBufferTriangles = GLAllocation.createDirectByteBuffer(this.byteBuffer.capacity() * 2);
         }

         int nextOffset = this.vertexFormat.getNextOffset();
         int n = this.byteBuffer.limit();
         ((Buffer)this.byteBuffer).rewind();
         ((Buffer)this.byteBufferTriangles).clear();

         for (int i = 0; i < this.vertexCount; i += 4) {
            ((Buffer)this.byteBuffer).limit((i + 3) * nextOffset);
            ((Buffer)this.byteBuffer).position(i * nextOffset);
            this.byteBufferTriangles.put(this.byteBuffer);
            ((Buffer)this.byteBuffer).limit((i + 1) * nextOffset);
            ((Buffer)this.byteBuffer).position(i * nextOffset);
            this.byteBufferTriangles.put(this.byteBuffer);
            ((Buffer)this.byteBuffer).limit((i + 2 + 2) * nextOffset);
            ((Buffer)this.byteBuffer).position((i + 2) * nextOffset);
            this.byteBufferTriangles.put(this.byteBuffer);
         }

         ((Buffer)this.byteBuffer).limit(n);
         ((Buffer)this.byteBuffer).rewind();
         ((Buffer)this.byteBufferTriangles).flip();
         this.modeTriangles = true;
      }
   }

   public void setVertexState(WorldRenderer.State state) {
      ((Buffer)this.rawIntBuffer).clear();
      this.growBuffer(state.getRawBuffer().length);
      this.rawIntBuffer.put(state.getRawBuffer());
      this.vertexCount = state.getVertexCount();
      this.vertexFormat = new VertexFormat(state.getVertexFormat());
      if (WorldRenderer.State.getStateQuadSprites(state) != null) {
         if (this.quadSprites == null) {
            this.quadSprites = this.quadSpritesPrev;
         }

         if (this.quadSprites == null || this.quadSprites.length < this.getBufferQuadSize()) {
            this.quadSprites = new TextureAtlasSprite[this.getBufferQuadSize()];
         }

         TextureAtlasSprite[] stateQuadSprites = WorldRenderer.State.getStateQuadSprites(state);
         System.arraycopy(stateQuadSprites, 0, this.quadSprites, 0, stateQuadSprites.length);
      } else {
         if (this.quadSprites != null) {
            this.quadSpritesPrev = this.quadSprites;
         }

         this.quadSprites = null;
      }
   }

   public void endVertex() {
      this.vertexCount++;
      this.growBuffer(this.vertexFormat.getIntegerSize());
      this.vertexFormatIndex = 0;
      this.vertexFormatElement = this.vertexFormat.getElement(this.vertexFormatIndex);
      if (Config.isShaderPackLoaded()) {
         SVertexBuilder.endAddVertex(this);
      }
   }

   private void putColor(int n, int n2) {
      this.putColorRGBA(this.getColorIndex(n2), n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF, n >> 24 & 0xFF);
   }

   public VertexFormat getVertexFormat() {
      return this.vertexFormat;
   }

   public boolean isMultiTexture() {
      return this.quadSprites != null;
   }

public static class EntryComparator implements Comparator {
      public final float[] quadDistances;
      public final WorldRenderer worldRenderer;

      public EntryComparator(WorldRenderer worldRenderer2, float[] floats) {
         this.worldRenderer = worldRenderer2;
         this.quadDistances = floats;
      }

      @Override
      public int compare(Object a, Object b) {
         return this.compare((Integer)a, (Integer)b);
      }

      public int compare(Integer a, Integer b) {
         return Floats.compare(this.quadDistances[b], this.quadDistances[a]);
      }
   }

public static class State {
      private final int[] stateRawBuffer;
      private final VertexFormat stateVertexFormat;
      private TextureAtlasSprite[] stateQuadSprites;
      public final WorldRenderer worldRenderer;

      public int getVertexCount() {
         return this.stateRawBuffer.length / this.stateVertexFormat.getIntegerSize();
      }

      public State(WorldRenderer worldRenderer2, int[] ints, VertexFormat vertexFormat, TextureAtlasSprite[] textureAtlasSprites) {
         this.worldRenderer = worldRenderer2;
         this.stateRawBuffer = ints;
         this.stateVertexFormat = vertexFormat;
         this.stateQuadSprites = textureAtlasSprites;
      }

      public int[] getRawBuffer() {
         return this.stateRawBuffer;
      }

      public VertexFormat getVertexFormat() {
         return this.stateVertexFormat;
      }

      public State(WorldRenderer worldRenderer2, int[] ints, VertexFormat vertexFormat) {
         this.worldRenderer = worldRenderer2;
         this.stateRawBuffer = ints;
         this.stateVertexFormat = vertexFormat;
      }

      public static TextureAtlasSprite[] getStateQuadSprites(State state) {
         return state.stateQuadSprites;
      }
   }
}
