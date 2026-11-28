package net.minecraft.client.renderer.block.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.optifine.config.Config;
import net.optifine.model.QuadBounds;

public class BakedQuad {
   public final int tintIndex;
   public int[] vertexData;
   public EnumFacing face;
   public TextureAtlasSprite sprite;
   private int[] vertexDataSingle = null;
   private QuadBounds quadBounds;
   private boolean quadEmissiveChecked;
   private BakedQuad quadEmissive;

   public int[] getVertexData() {
      this.fixVertexData();
      return this.vertexData;
   }

   public void fixVertexData() {
      if (Config.isShaderPackLoaded()) {
         if (this.vertexData.length == 28) {
            this.vertexData = expandVertexData(this.vertexData);
         }
      } else if (this.vertexData.length == 56) {
         this.vertexData = compactVertexData(this.vertexData);
      }
   }

   public BakedQuad getQuadEmissive() {
      if (this.quadEmissiveChecked) {
         return this.quadEmissive;
      } else {
         if (this.quadEmissive == null && this.sprite != null && this.sprite.spriteEmissive != null) {
            this.quadEmissive = new BreakingFour(this, this.sprite.spriteEmissive);
         }

         this.quadEmissiveChecked = true;
         return this.quadEmissive;
      }
   }

   public float getMidX() {
      QuadBounds quadBounds2 = this.getQuadBounds();
      return (quadBounds2.getMaxX() + quadBounds2.getMinX()) / 2.0F;
   }

   public QuadBounds getQuadBounds() {
      if (this.quadBounds == null) {
         this.quadBounds = new QuadBounds(this.getVertexData());
      }

      return this.quadBounds;
   }

   public boolean isFullFace() {
      return this.getQuadBounds().isFullFace(this.face);
   }

   public int getTintIndex() {
      return this.tintIndex;
   }

   public EnumFacing getFace() {
      if (this.face == null) {
         this.face = FaceBakery.getFacingFromVertexData(this.getVertexData());
      }

      return this.face;
   }

   private static int[] compactVertexData(int[] ints) {
      int n = ints.length / 4;
      int n2 = n / 2;
      int[] ints2 = new int[n2 * 4];

      for (int i = 0; i < 4; i++) {
         System.arraycopy(ints, i * n, ints2, i * n2, n2);
      }

      return ints2;
   }

   private static int[] makeVertexDataSingle(int[] ints, TextureAtlasSprite textureAtlasSprite) {
      int[] ints2 = (int[])ints.clone();
      int n = ints2.length / 4;

      for (int i = 0; i < 4; i++) {
         int n2 = i * n;
         float f = Float.intBitsToFloat(ints2[n2 + 4]);
         float f2 = Float.intBitsToFloat(ints2[n2 + 4 + 1]);
         float f3 = textureAtlasSprite.toSingleU(f);
         float f4 = textureAtlasSprite.toSingleV(f2);
         ints2[n2 + 4] = Float.floatToRawIntBits(f3);
         ints2[n2 + 4 + 1] = Float.floatToRawIntBits(f4);
      }

      return ints2;
   }

   public double getMidY() {
      QuadBounds quadBounds2 = this.getQuadBounds();
      return (double)((quadBounds2.getMaxY() + quadBounds2.getMinY()) / 2.0F);
   }

   public boolean isFullQuad() {
      return this.isFullFace() && this.isFaceQuad();
   }

   public boolean isFaceQuad() {
      return this.getQuadBounds().isFaceQuad(this.face);
   }

   private static int[] expandVertexData(int[] ints) {
      int n = ints.length / 4;
      int n2 = n * 2;
      int[] ints2 = new int[n2 * 4];

      for (int i = 0; i < 4; i++) {
         System.arraycopy(ints, i * n, ints2, i * n2, n);
      }

      return ints2;
   }

   public TextureAtlasSprite getSprite() {
      if (this.sprite == null) {
         this.sprite = getSpriteByUv(this.getVertexData());
      }

      return this.sprite;
   }

   public double getMidZ() {
      QuadBounds quadBounds2 = this.getQuadBounds();
      return (double)((quadBounds2.getMaxZ() + quadBounds2.getMinZ()) / 2.0F);
   }

   public boolean hasTintIndex() {
      return this.tintIndex != -1;
   }

   @Override
   public String toString() {
      return "vertex: " + this.vertexData.length / 7 + ", tint: " + this.tintIndex + ", facing: " + this.face + ", sprite: " + this.sprite;
   }

   public int[] getVertexDataSingle() {
      if (this.vertexDataSingle == null) {
         this.vertexDataSingle = makeVertexDataSingle(this.getVertexData(), this.getSprite());
      }

      return this.vertexDataSingle;
   }

   private static TextureAtlasSprite getSpriteByUv(int[] ints) {
      float f = 1.0F;
      float f2 = 1.0F;
      float f3 = 0.0F;
      float f4 = 0.0F;
      int n = ints.length / 4;

      for (int i = 0; i < 4; i++) {
         int n2 = i * n;
         float f5 = Float.intBitsToFloat(ints[n2 + 4]);
         float f6 = Float.intBitsToFloat(ints[n2 + 4 + 1]);
         f = Math.min(f, f5);
         f2 = Math.min(f2, f6);
         f3 = Math.max(f3, f5);
         f4 = Math.max(f4, f6);
      }

      float f7 = (f + f3) / 2.0F;
      float f8 = (f2 + f4) / 2.0F;
      return Minecraft.getMinecraft().getTextureMapBlocks().getIconByUV((double)f7, (double)f8);
   }

   public BakedQuad(int[] ints, int n, EnumFacing facing, TextureAtlasSprite textureAtlasSprite) {
      this.vertexData = ints;
      this.tintIndex = n;
      this.face = facing;
      this.sprite = textureAtlasSprite;
      this.fixVertexData();
   }

   public BakedQuad(int[] ints, int n, EnumFacing facing) {
      this.vertexData = ints;
      this.tintIndex = n;
      this.face = facing;
      this.fixVertexData();
   }
}
