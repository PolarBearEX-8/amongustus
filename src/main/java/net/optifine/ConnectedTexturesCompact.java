package net.optifine;

import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.optifine.render.RenderEnv;

public class ConnectedTexturesCompact {
   private static final int COMPACT_NONE = 0;
   private static final int COMPACT_ALL = 1;
   private static final int COMPACT_V = 2;
   private static final int COMPACT_H = 3;
   private static final int COMPACT_HV = 4;

   private static BakedQuad makeSpriteQuadCompact(BakedQuad bakedQuad, TextureAtlasSprite textureAtlasSprite, int n, int n2, int n3, int n4, int n5) {
      int[] ints = (int[])bakedQuad.getVertexData().clone();
      TextureAtlasSprite sprite = bakedQuad.getSprite();

      for (int i = 0; i < 4; i++) {
         makeSpriteQuadCompactVertex(ints, i, sprite, textureAtlasSprite, n, n2, n3, n4, n5);
      }

      return new BakedQuad(ints, bakedQuad.getTintIndex(), bakedQuad.getFace(), textureAtlasSprite);
   }

   private static BakedQuad[] getQuadsCompact(
      ConnectedTexturesDir connectedTexturesDir, int n, ConnectedTexturesDir connectedTexturesDir2, int n2, ConnectedTexturesDir connectedTexturesDir3, int n3, TextureAtlasSprite[] textureAtlasSprites, int n4, BakedQuad bakedQuad, RenderEnv renderEnv
   ) {
      return renderEnv.getArrayQuadsCtm(getQuadCompact(textureAtlasSprites[n], connectedTexturesDir, n4, bakedQuad, renderEnv), getQuadCompact(textureAtlasSprites[n2], connectedTexturesDir2, n4, bakedQuad, renderEnv), getQuadCompact(textureAtlasSprites[n3], connectedTexturesDir3, n4, bakedQuad, renderEnv));
   }

   private static BakedQuad getQuadCompact(TextureAtlasSprite textureAtlasSprite, ConnectedTexturesDir connectedTexturesDir, int n, BakedQuad bakedQuad, RenderEnv renderEnv) {
      switch (connectedTexturesDir) {
         case UP:
            return getQuadCompact(textureAtlasSprite, connectedTexturesDir, 0, 0, 16, 8, n, bakedQuad, renderEnv);
         case UP_RIGHT:
            return getQuadCompact(textureAtlasSprite, connectedTexturesDir, 8, 0, 16, 8, n, bakedQuad, renderEnv);
         case RIGHT:
            return getQuadCompact(textureAtlasSprite, connectedTexturesDir, 8, 0, 16, 16, n, bakedQuad, renderEnv);
         case DOWN_RIGHT:
            return getQuadCompact(textureAtlasSprite, connectedTexturesDir, 8, 8, 16, 16, n, bakedQuad, renderEnv);
         case DOWN:
            return getQuadCompact(textureAtlasSprite, connectedTexturesDir, 0, 8, 16, 16, n, bakedQuad, renderEnv);
         case DOWN_LEFT:
            return getQuadCompact(textureAtlasSprite, connectedTexturesDir, 0, 8, 8, 16, n, bakedQuad, renderEnv);
         case LEFT:
            return getQuadCompact(textureAtlasSprite, connectedTexturesDir, 0, 0, 8, 16, n, bakedQuad, renderEnv);
         case UP_LEFT:
            return getQuadCompact(textureAtlasSprite, connectedTexturesDir, 0, 0, 8, 8, n, bakedQuad, renderEnv);
         default:
            return bakedQuad;
      }
   }

   private static BakedQuad[] getQuadsCompact(int n, TextureAtlasSprite[] textureAtlasSprites, BakedQuad bakedQuad, RenderEnv renderEnv) {
      return ConnectedTextures.getQuads(textureAtlasSprites[n], bakedQuad, renderEnv);
   }

   private static BakedQuad[] getQuadsCompactV(int n, int n2, TextureAtlasSprite[] textureAtlasSprites, int n3, BakedQuad bakedQuad, RenderEnv renderEnv) {
      return getQuadsCompact(ConnectedTexturesDir.UP, n, ConnectedTexturesDir.DOWN, n2, textureAtlasSprites, n3, bakedQuad, renderEnv);
   }

   public static BakedQuad[] getConnectedTextureCtmCompact(int n, ConnectedProperties connectedProperties, int n2, BakedQuad bakedQuad, RenderEnv renderEnv) {
      if (connectedProperties.ctmTileIndexes != null && n >= 0 && n < connectedProperties.ctmTileIndexes.length) {
         int n3 = connectedProperties.ctmTileIndexes[n];
         if (n3 >= 0 && n3 <= connectedProperties.tileIcons.length) {
            return getQuadsCompact(n3, connectedProperties.tileIcons, bakedQuad, renderEnv);
         }
      }

      switch (n) {
         case 1:
            return getQuadsCompactH(0, 3, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 2:
            return getQuadsCompact(3, connectedProperties.tileIcons, bakedQuad, renderEnv);
         case 3:
            return getQuadsCompactH(3, 0, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 4:
            return getQuadsCompact4(0, 3, 2, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 5:
            return getQuadsCompact4(3, 0, 4, 2, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 6:
            return getQuadsCompact4(2, 4, 2, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 7:
            return getQuadsCompact4(3, 3, 4, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 8:
            return getQuadsCompact4(4, 1, 4, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 9:
            return getQuadsCompact4(4, 4, 4, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 10:
            return getQuadsCompact4(1, 4, 1, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 11:
            return getQuadsCompact4(1, 1, 4, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 12:
            return getQuadsCompactV(0, 2, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 13:
            return getQuadsCompact4(0, 3, 2, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 14:
            return getQuadsCompactV(3, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 15:
            return getQuadsCompact4(3, 0, 1, 2, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 16:
            return getQuadsCompact4(2, 4, 0, 3, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 17:
            return getQuadsCompact4(4, 2, 3, 0, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 18:
            return getQuadsCompact4(4, 4, 3, 3, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 19:
            return getQuadsCompact4(4, 2, 4, 2, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 20:
            return getQuadsCompact4(1, 4, 4, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 21:
            return getQuadsCompact4(4, 4, 1, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 22:
            return getQuadsCompact4(4, 4, 1, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 23:
            return getQuadsCompact4(4, 1, 4, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 24:
            return getQuadsCompact(2, connectedProperties.tileIcons, bakedQuad, renderEnv);
         case 25:
            return getQuadsCompactH(2, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 26:
            return getQuadsCompact(1, connectedProperties.tileIcons, bakedQuad, renderEnv);
         case 27:
            return getQuadsCompactH(1, 2, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 28:
            return getQuadsCompact4(2, 4, 2, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 29:
            return getQuadsCompact4(3, 3, 1, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 30:
            return getQuadsCompact4(2, 1, 2, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 31:
            return getQuadsCompact4(3, 3, 4, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 32:
            return getQuadsCompact4(1, 1, 1, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 33:
            return getQuadsCompact4(1, 1, 4, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 34:
            return getQuadsCompact4(4, 1, 1, 4, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 35:
            return getQuadsCompact4(1, 4, 4, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 36:
            return getQuadsCompactV(2, 0, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 37:
            return getQuadsCompact4(2, 1, 0, 3, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 38:
            return getQuadsCompactV(1, 3, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 39:
            return getQuadsCompact4(1, 2, 3, 0, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 40:
            return getQuadsCompact4(4, 1, 3, 3, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 41:
            return getQuadsCompact4(1, 2, 4, 2, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 42:
            return getQuadsCompact4(1, 4, 3, 3, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 43:
            return getQuadsCompact4(4, 2, 1, 2, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 44:
            return getQuadsCompact4(1, 4, 1, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 45:
            return getQuadsCompact4(4, 1, 1, 1, connectedProperties.tileIcons, n2, bakedQuad, renderEnv);
         case 46:
            return getQuadsCompact(4, connectedProperties.tileIcons, bakedQuad, renderEnv);
         default:
            return getQuadsCompact(0, connectedProperties.tileIcons, bakedQuad, renderEnv);
      }
   }

   private static void makeSpriteQuadCompactVertex(int[] ints, int n, TextureAtlasSprite textureAtlasSprite, TextureAtlasSprite textureAtlasSprite2, int n2, int n3, int n4, int n5, int n6) {
      int n8 = (ints.length / 4) * n;
      float f = Float.intBitsToFloat(ints[n8 + 4]);
      float f2 = Float.intBitsToFloat(ints[n8 + 4 + 1]);
      double spriteU16 = textureAtlasSprite.getSpriteU16(f);
      double spriteV16 = textureAtlasSprite.getSpriteV16(f2);
      float f3 = Float.intBitsToFloat(ints[n8 + 0]);
      float f4 = Float.intBitsToFloat(ints[n8 + 1]);
      float f5 = Float.intBitsToFloat(ints[n8 + 2]);
      float f6;
      float f7;
      switch (n2) {
         case 0:
            f6 = f3;
            f7 = 1.0F - f5;
            break;
         case 1:
            f6 = f3;
            f7 = f5;
            break;
         case 2:
            f6 = 1.0F - f3;
            f7 = 1.0F - f4;
            break;
         case 3:
            f6 = f3;
            f7 = 1.0F - f4;
            break;
         case 4:
            f6 = f5;
            f7 = 1.0F - f4;
            break;
         case 5:
            f6 = 1.0F - f5;
            f7 = 1.0F - f4;
            break;
         default:
            return;
      }

      float f8 = 15.968F;
      float f9 = 15.968F;
      if (spriteU16 < (double)n3) {
         f6 = (float)((double)f6 + ((double)n3 - spriteU16) / (double)f8);
         spriteU16 = (double)n3;
      }

      if (spriteU16 > (double)n5) {
         f6 = (float)((double)f6 - (spriteU16 - (double)n5) / (double)f8);
         spriteU16 = (double)n5;
      }

      if (spriteV16 < (double)n4) {
         f7 = (float)((double)f7 + ((double)n4 - spriteV16) / (double)f9);
         spriteV16 = (double)n4;
      }

      if (spriteV16 > (double)n6) {
         f7 = (float)((double)f7 - (spriteV16 - (double)n6) / (double)f9);
         spriteV16 = (double)n6;
      }

      switch (n2) {
         case 0:
            f3 = f6;
            f5 = 1.0F - f7;
            break;
         case 1:
            f3 = f6;
            f5 = f7;
            break;
         case 2:
            f3 = 1.0F - f6;
            f4 = 1.0F - f7;
            break;
         case 3:
            f3 = f6;
            f4 = 1.0F - f7;
            break;
         case 4:
            f5 = f6;
            f4 = 1.0F - f7;
            break;
         case 5:
            f5 = 1.0F - f6;
            f4 = 1.0F - f7;
            break;
         default:
            return;
      }

      ints[n8 + 4] = Float.floatToRawIntBits(textureAtlasSprite2.getInterpolatedU(spriteU16));
      ints[n8 + 4 + 1] = Float.floatToRawIntBits(textureAtlasSprite2.getInterpolatedV(spriteV16));
      ints[n8 + 0] = Float.floatToRawIntBits(f3);
      ints[n8 + 1] = Float.floatToRawIntBits(f4);
      ints[n8 + 2] = Float.floatToRawIntBits(f5);
   }

   private static BakedQuad[] getQuadsCompact(ConnectedTexturesDir connectedTexturesDir, int n, ConnectedTexturesDir connectedTexturesDir2, int n2, TextureAtlasSprite[] textureAtlasSprites, int n3, BakedQuad bakedQuad, RenderEnv renderEnv) {
      return renderEnv.getArrayQuadsCtm(getQuadCompact(textureAtlasSprites[n], connectedTexturesDir, n3, bakedQuad, renderEnv), getQuadCompact(textureAtlasSprites[n2], connectedTexturesDir2, n3, bakedQuad, renderEnv));
   }

   private static BakedQuad getQuadCompact(TextureAtlasSprite textureAtlasSprite, ConnectedTexturesDir connectedTexturesDir, int n, int n2, int n3, int n4, int n5, BakedQuad bakedQuad, RenderEnv renderEnv) {
      Map[][] spriteQuadCompactMaps = ConnectedTextures.getSpriteQuadCompactMaps();
      if (spriteQuadCompactMaps == null) {
         return bakedQuad;
      } else {
         int indexInMap = textureAtlasSprite.getIndexInMap();
         if (indexInMap >= 0 && indexInMap < spriteQuadCompactMaps.length) {
            Map[] maps = spriteQuadCompactMaps[indexInMap];
            if (maps == null) {
               maps = new Map[ConnectedTexturesDir.VALUES.length];
               spriteQuadCompactMaps[indexInMap] = maps;
            }

            Map map = maps[connectedTexturesDir.ordinal()];
            if (map == null) {
               map = new IdentityHashMap(1);
               maps[connectedTexturesDir.ordinal()] = (Map)map;
            }

            BakedQuad bakedQuad2 = (BakedQuad)map.get(bakedQuad);
            if (bakedQuad2 == null) {
               bakedQuad2 = makeSpriteQuadCompact(bakedQuad, textureAtlasSprite, n5, n, n2, n3, n4);
               map.put(bakedQuad, bakedQuad2);
            }

            return bakedQuad2;
         } else {
            return bakedQuad;
         }
      }
   }

   private static BakedQuad[] getQuadsCompactH(int n, int n2, TextureAtlasSprite[] textureAtlasSprites, int n3, BakedQuad bakedQuad, RenderEnv renderEnv) {
      return getQuadsCompact(ConnectedTexturesDir.LEFT, n, ConnectedTexturesDir.RIGHT, n2, textureAtlasSprites, n3, bakedQuad, renderEnv);
   }

   private static BakedQuad[] getQuadsCompact(
      ConnectedTexturesDir connectedTexturesDir,
      int n,
      ConnectedTexturesDir connectedTexturesDir2,
      int n2,
      ConnectedTexturesDir connectedTexturesDir3,
      int n3,
      ConnectedTexturesDir connectedTexturesDir4,
      int n4,
      TextureAtlasSprite[] textureAtlasSprites,
      int n5,
      BakedQuad bakedQuad,
      RenderEnv renderEnv
   ) {
      return renderEnv.getArrayQuadsCtm(getQuadCompact(textureAtlasSprites[n], connectedTexturesDir, n5, bakedQuad, renderEnv), getQuadCompact(textureAtlasSprites[n2], connectedTexturesDir2, n5, bakedQuad, renderEnv), getQuadCompact(textureAtlasSprites[n3], connectedTexturesDir3, n5, bakedQuad, renderEnv), getQuadCompact(textureAtlasSprites[n4], connectedTexturesDir4, n5, bakedQuad, renderEnv));
   }

   private static BakedQuad[] getQuadsCompact4(int n, int n2, int n3, int n4, TextureAtlasSprite[] textureAtlasSprites, int n5, BakedQuad bakedQuad, RenderEnv renderEnv) {
      return n == n2
         ? (
            n3 == n4
               ? getQuadsCompact(ConnectedTexturesDir.UP, n, ConnectedTexturesDir.DOWN, n3, textureAtlasSprites, n5, bakedQuad, renderEnv)
               : getQuadsCompact(ConnectedTexturesDir.UP, n, ConnectedTexturesDir.DOWN_LEFT, n3, ConnectedTexturesDir.DOWN_RIGHT, n4, textureAtlasSprites, n5, bakedQuad, renderEnv)
         )
         : (
            n3 == n4
               ? getQuadsCompact(ConnectedTexturesDir.UP_LEFT, n, ConnectedTexturesDir.UP_RIGHT, n2, ConnectedTexturesDir.DOWN, n3, textureAtlasSprites, n5, bakedQuad, renderEnv)
               : (
                  n == n3
                     ? (
                        n2 == n4
                           ? getQuadsCompact(ConnectedTexturesDir.LEFT, n, ConnectedTexturesDir.RIGHT, n2, textureAtlasSprites, n5, bakedQuad, renderEnv)
                           : getQuadsCompact(ConnectedTexturesDir.LEFT, n, ConnectedTexturesDir.UP_RIGHT, n2, ConnectedTexturesDir.DOWN_RIGHT, n4, textureAtlasSprites, n5, bakedQuad, renderEnv)
                     )
                     : (
                        n2 == n4
                           ? getQuadsCompact(ConnectedTexturesDir.UP_LEFT, n, ConnectedTexturesDir.DOWN_LEFT, n3, ConnectedTexturesDir.RIGHT, n2, textureAtlasSprites, n5, bakedQuad, renderEnv)
                           : getQuadsCompact(
                              ConnectedTexturesDir.UP_LEFT,
                              n,
                              ConnectedTexturesDir.UP_RIGHT,
                              n2,
                              ConnectedTexturesDir.DOWN_LEFT,
                              n3,
                              ConnectedTexturesDir.DOWN_RIGHT,
                              n4,
                              textureAtlasSprites,
                              n5,
                              bakedQuad,
                              renderEnv
                           )
                     )
               )
         );
   }

}
