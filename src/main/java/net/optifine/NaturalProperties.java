package net.optifine;

import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.optifine.config.Config;

public class NaturalProperties {
   public int rotation = 1;
   public boolean flip = false;
   private Map[] quadMaps = new Map[8];

   public NaturalProperties(String str) {
      if (str.equals("4")) {
         this.rotation = 4;
      } else if (str.equals("2")) {
         this.rotation = 2;
      } else if (str.equals("F")) {
         this.flip = true;
      } else if (str.equals("4F")) {
         this.rotation = 4;
         this.flip = true;
      } else if (str.equals("2F")) {
         this.rotation = 2;
         this.flip = true;
      } else {
         Config.warn("NaturalTextures: Unknown type: " + str);
      }
   }

   private boolean isFullSprite(BakedQuad bakedQuad) {
      TextureAtlasSprite sprite = bakedQuad.getSprite();
      float minU = sprite.getMinU();
      float maxU = sprite.getMaxU();
      float f2 = (maxU - minU) / 256.0F;
      float minV = sprite.getMinV();
      float maxV = sprite.getMaxV();
      float f4 = (maxV - minV) / 256.0F;
      int[] vertexData = bakedQuad.getVertexData();
      int n = vertexData.length / 4;

      for (int i = 0; i < 4; i++) {
         int n2 = i * n;
         float f5 = Float.intBitsToFloat(vertexData[n2 + 4]);
         float f6 = Float.intBitsToFloat(vertexData[n2 + 4 + 1]);
         if (!this.equalsDelta(f5, minU, f2) && !this.equalsDelta(f5, maxU, f2)) {
            return false;
         }

         if (!this.equalsDelta(f6, minV, f4) && !this.equalsDelta(f6, maxV, f4)) {
            return false;
         }
      }

      return true;
   }

   public synchronized BakedQuad getQuad(BakedQuad bakedQuad, int n, boolean flag) {
      int n2 = n;
      if (flag) {
         n2 = n | 4;
      }

      if (n2 > 0 && n2 < this.quadMaps.length) {
         Map map = this.quadMaps[n2];
         if (map == null) {
            map = new IdentityHashMap(1);
            this.quadMaps[n2] = (Map)map;
         }

         BakedQuad bakedQuad2 = (BakedQuad)map.get(bakedQuad);
         if (bakedQuad2 == null) {
            bakedQuad2 = this.makeQuad(bakedQuad, n, flag);
            map.put(bakedQuad, bakedQuad2);
         }

         return bakedQuad2;
      } else {
         return bakedQuad;
      }
   }

   private int[] fixVertexData(int[] ints, int n, boolean flag) {
      int[] ints2 = (int[])ints.clone();
      int n2 = 4 - n;
      if (flag) {
         n2 += 3;
      }

      n2 %= 4;
      int n3 = ints2.length / 4;

      for (int i = 0; i < 4; i++) {
         int n4 = i * n3;
         int n5 = n2 * n3;
         ints2[n5 + 4] = ints[n4 + 4];
         ints2[n5 + 4 + 1] = ints[n4 + 4 + 1];
         if (flag) {
            if (--n2 < 0) {
               n2 = 3;
            }
         } else if (++n2 > 3) {
            n2 = 0;
         }
      }

      return ints2;
   }

   private boolean equalsDelta(float f, float f2, float f3) {
      return MathHelper.abs(f - f2) < f3;
   }

   private BakedQuad makeQuad(BakedQuad bakedQuad, int n, boolean flag) {
      int[] vertexData = bakedQuad.getVertexData();
      int tintIndex = bakedQuad.getTintIndex();
      EnumFacing face = bakedQuad.getFace();
      TextureAtlasSprite sprite = bakedQuad.getSprite();
      if (!this.isFullSprite(bakedQuad)) {
         n = 0;
      }

      vertexData = this.fixVertexData(vertexData, n, flag);
      return new BakedQuad(vertexData, tintIndex, face, sprite);
   }

   public boolean isValid() {
      return this.rotation != 2 && this.rotation != 4 ? this.flip : true;
   }
}
