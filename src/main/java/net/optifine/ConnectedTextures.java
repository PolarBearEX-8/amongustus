package net.optifine;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import net.optifine.model.ListQuadsOverlay;
import net.optifine.config.Matches;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.Config;
import net.optifine.model.BlockModelUtils;
import net.optifine.render.RenderEnv;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.TileEntityUtils;

public class ConnectedTextures {
   private static Map[] spriteQuadMaps = null;
   private static Map[] spriteQuadFullMaps = null;
   private static Map[][] spriteQuadCompactMaps = (Map[][])null;
   private static ConnectedProperties[][] blockProperties = (ConnectedProperties[][])null;
   private static ConnectedProperties[][] tileProperties = (ConnectedProperties[][])null;
   private static boolean multipass = false;
   public static final int SIDE_UNKNOWN = -1;
   public static final int Y_NEG_DOWN = 0;
   public static final int Y_POS_UP = 1;
   public static final int Z_NEG_NORTH = 2;
   public static final int Z_POS_SOUTH = 3;
   public static final int X_NEG_WEST = 4;
   public static final int X_POS_EAST = 5;
   private static final int Y_AXIS = 0;
   private static final int Z_AXIS = 1;
   private static final int X_AXIS = 2;
   public static final IBlockState AIR_DEFAULT_STATE = Blocks.air.getDefaultState();
   private static TextureAtlasSprite emptySprite = null;
   private static final BlockDir[] SIDES_Y_NEG_DOWN = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.NORTH, BlockDir.SOUTH};
   private static final BlockDir[] SIDES_Y_POS_UP = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.SOUTH, BlockDir.NORTH};
   private static final BlockDir[] SIDES_Z_NEG_NORTH = new BlockDir[]{BlockDir.EAST, BlockDir.WEST, BlockDir.DOWN, BlockDir.UP};
   private static final BlockDir[] SIDES_Z_POS_SOUTH = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.DOWN, BlockDir.UP};
   private static final BlockDir[] SIDES_X_NEG_WEST = new BlockDir[]{BlockDir.NORTH, BlockDir.SOUTH, BlockDir.DOWN, BlockDir.UP};
   private static final BlockDir[] SIDES_X_POS_EAST = new BlockDir[]{BlockDir.SOUTH, BlockDir.NORTH, BlockDir.DOWN, BlockDir.UP};
   private static final BlockDir[] SIDES_Z_NEG_NORTH_Z_AXIS = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.UP, BlockDir.DOWN};
   private static final BlockDir[] SIDES_X_POS_EAST_X_AXIS = new BlockDir[]{BlockDir.NORTH, BlockDir.SOUTH, BlockDir.UP, BlockDir.DOWN};
   private static final BlockDir[] EDGES_Y_NEG_DOWN = new BlockDir[]{BlockDir.NORTH_EAST, BlockDir.NORTH_WEST, BlockDir.SOUTH_EAST, BlockDir.SOUTH_WEST};
   private static final BlockDir[] EDGES_Y_POS_UP = new BlockDir[]{BlockDir.SOUTH_EAST, BlockDir.SOUTH_WEST, BlockDir.NORTH_EAST, BlockDir.NORTH_WEST};
   private static final BlockDir[] EDGES_Z_NEG_NORTH = new BlockDir[]{BlockDir.DOWN_WEST, BlockDir.DOWN_EAST, BlockDir.UP_WEST, BlockDir.UP_EAST};
   private static final BlockDir[] EDGES_Z_POS_SOUTH = new BlockDir[]{BlockDir.DOWN_EAST, BlockDir.DOWN_WEST, BlockDir.UP_EAST, BlockDir.UP_WEST};
   private static final BlockDir[] EDGES_X_NEG_WEST = new BlockDir[]{BlockDir.DOWN_SOUTH, BlockDir.DOWN_NORTH, BlockDir.UP_SOUTH, BlockDir.UP_NORTH};
   private static final BlockDir[] EDGES_X_POS_EAST = new BlockDir[]{BlockDir.DOWN_NORTH, BlockDir.DOWN_SOUTH, BlockDir.UP_NORTH, BlockDir.UP_SOUTH};
   private static final BlockDir[] EDGES_Z_NEG_NORTH_Z_AXIS = new BlockDir[]{BlockDir.UP_EAST, BlockDir.UP_WEST, BlockDir.DOWN_EAST, BlockDir.DOWN_WEST};
   private static final BlockDir[] EDGES_X_POS_EAST_X_AXIS = new BlockDir[]{BlockDir.UP_SOUTH, BlockDir.UP_NORTH, BlockDir.DOWN_SOUTH, BlockDir.DOWN_NORTH};
   public static final TextureAtlasSprite SPRITE_DEFAULT = new TextureAtlasSprite("<default>");

   private static TextureAtlasSprite getConnectedTextureTop(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, int n2, TextureAtlasSprite textureAtlasSprite, int n3
   ) {
      boolean flag = false;
      switch (n) {
         case 0:
            if (n2 == 1 || n2 == 0) {
               return null;
            }

            flag = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
            break;
         case 1:
            if (n2 == 3 || n2 == 2) {
               return null;
            }

            flag = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
            break;
         case 2:
            if (n2 == 5 || n2 == 4) {
               return null;
            }

            flag = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
      }

      return flag ? connectedProperties.tileIcons[0] : null;
   }

   private static boolean isNeighbour(ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, TextureAtlasSprite textureAtlasSprite, int n2) {
      return isNeighbour(connectedProperties, world, state, pos, world.getBlockState(pos), n, textureAtlasSprite, n2);
   }

   public static Map[][] getSpriteQuadCompactMaps() {
      return spriteQuadCompactMaps;
   }

   private static BakedQuad makeSpriteQuad(BakedQuad bakedQuad, TextureAtlasSprite textureAtlasSprite) {
      int[] ints = (int[])bakedQuad.getVertexData().clone();
      TextureAtlasSprite sprite = bakedQuad.getSprite();

      for (int i = 0; i < 4; i++) {
         remapVertexUv(ints, i, sprite, textureAtlasSprite);
      }

      return new BakedQuad(ints, bakedQuad.getTintIndex(), bakedQuad.getFace(), textureAtlasSprite);
   }

   private static TextureAtlasSprite getConnectedTextureFixed(ConnectedProperties connectedProperties) {
      return connectedProperties.tileIcons[0];
   }

   private static TextureAtlasSprite getConnectedTextureHorizontalVertical(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, int n2, TextureAtlasSprite textureAtlasSprite, int n3
   ) {
      TextureAtlasSprite[] tileIcons = connectedProperties.tileIcons;
      TextureAtlasSprite textureAtlasSprite2 = getConnectedTextureHorizontal(connectedProperties, world, state, pos, n, n2, textureAtlasSprite, n3);
      if (textureAtlasSprite2 != null && textureAtlasSprite2 != textureAtlasSprite && textureAtlasSprite2 != tileIcons[3]) {
         return textureAtlasSprite2;
      } else {
         TextureAtlasSprite textureAtlasSprite3 = getConnectedTextureVertical(connectedProperties, world, state, pos, n, n2, textureAtlasSprite, n3);
         return textureAtlasSprite3 == tileIcons[0] ? tileIcons[4] : (textureAtlasSprite3 == tileIcons[1] ? tileIcons[5] : (textureAtlasSprite3 == tileIcons[2] ? tileIcons[6] : textureAtlasSprite3));
      }
   }

   private static BakedQuad[] getConnectedTexture(
      ConnectedProperties connectedProperties, IBlockAccess world, BlockStateBase blockStateBase, BlockPos pos, int n, BakedQuad bakedQuad, int n2, RenderEnv renderEnv
   ) {
      int n3 = 0;
      int metadata = blockStateBase.getMetadata();
      int n4 = metadata;
      Block block = blockStateBase.getBlock();
      if (block instanceof BlockRotatedPillar) {
         n3 = getWoodAxis(n, metadata);
         if (connectedProperties.getMetadataMax() <= 3) {
            n4 = metadata & 3;
         }
      }

      if (block instanceof BlockQuartz) {
         n3 = getQuartzAxis(n, metadata);
         if (connectedProperties.getMetadataMax() <= 2 && n4 > 2) {
            n4 = 2;
         }
      }

      if (!connectedProperties.matchesBlock(blockStateBase.getBlockId(), n4)) {
         return null;
      } else {
         if (n >= 0 && connectedProperties.faces != 63) {
            int n5 = n;
            if (n3 != 0) {
               n5 = fixSideByAxis(n, n3);
            }

            if ((1 << n5 & connectedProperties.faces) == 0) {
               return null;
            }
         }

         int y = pos.getY();
         if (connectedProperties.heights != null && !connectedProperties.heights.isInRange(y)) {
            return null;
         } else {
            if (connectedProperties.biomes != null) {
               if (!connectedProperties.matchesBiome(world.getBiomeGenForCoords(pos))) {
                  return null;
               }
            }

            if (connectedProperties.nbtName != null) {
               String tileEntityName = TileEntityUtils.getTileEntityName(world, pos);
               if (!connectedProperties.nbtName.matchesValue(tileEntityName)) {
                  return null;
               }
            }

            TextureAtlasSprite sprite = bakedQuad.getSprite();
            switch (connectedProperties.method) {
               case 1:
                  return getQuads(getConnectedTextureCtm(connectedProperties, world, blockStateBase, pos, n3, n, sprite, metadata, renderEnv), bakedQuad, renderEnv);
               case 2:
                  return getQuads(getConnectedTextureHorizontal(connectedProperties, world, blockStateBase, pos, n3, n, sprite, metadata), bakedQuad, renderEnv);
               case 3:
                  return getQuads(getConnectedTextureTop(connectedProperties, world, blockStateBase, pos, n3, n, sprite, metadata), bakedQuad, renderEnv);
               case 4:
                  return getQuads(getConnectedTextureRandom(connectedProperties, world, blockStateBase, pos, n), bakedQuad, renderEnv);
               case 5:
                  return getQuads(getConnectedTextureRepeat(connectedProperties, pos, n), bakedQuad, renderEnv);
               case 6:
                  return getQuads(getConnectedTextureVertical(connectedProperties, world, blockStateBase, pos, n3, n, sprite, metadata), bakedQuad, renderEnv);
               case 7:
                  return getQuads(getConnectedTextureFixed(connectedProperties), bakedQuad, renderEnv);
               case 8:
                  return getQuads(getConnectedTextureHorizontalVertical(connectedProperties, world, blockStateBase, pos, n3, n, sprite, metadata), bakedQuad, renderEnv);
               case 9:
                  return getQuads(getConnectedTextureVerticalHorizontal(connectedProperties, world, blockStateBase, pos, n3, n, sprite, metadata), bakedQuad, renderEnv);
               case 10:
                  if (n2 == 0) {
                     return getConnectedTextureCtmCompact(connectedProperties, world, blockStateBase, pos, n3, n, bakedQuad, metadata, renderEnv);
                  }
               default:
                  return null;
               case 11:
                  return getConnectedTextureOverlay(connectedProperties, world, blockStateBase, pos, n3, n, bakedQuad, metadata, renderEnv);
               case 12:
                  return getConnectedTextureOverlayFixed(connectedProperties, bakedQuad, renderEnv);
               case 13:
                  return getConnectedTextureOverlayRandom(connectedProperties, world, blockStateBase, pos, n, bakedQuad, renderEnv);
               case 14:
                  return getConnectedTextureOverlayRepeat(connectedProperties, pos, n, bakedQuad, renderEnv);
               case 15:
                  return getConnectedTextureOverlayCtm(connectedProperties, world, blockStateBase, pos, n3, n, bakedQuad, metadata, renderEnv);
            }
         }
      }
   }

   private static int getConnectedTextureCtmIndex(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, int n2, TextureAtlasSprite textureAtlasSprite, int n3, RenderEnv renderEnv
   ) {
      boolean[] borderFlags = renderEnv.getBorderFlags();
      switch (n2) {
         case 0:
            borderFlags[0] = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
            borderFlags[1] = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
            borderFlags[2] = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
            borderFlags[3] = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
            if (connectedProperties.innerSeams) {
               BlockPos pos2 = pos.down();
               borderFlags[0] = borderFlags[0] && !isNeighbour(connectedProperties, world, state, pos2.west(), n2, textureAtlasSprite, n3);
               borderFlags[1] = borderFlags[1] && !isNeighbour(connectedProperties, world, state, pos2.east(), n2, textureAtlasSprite, n3);
               borderFlags[2] = borderFlags[2] && !isNeighbour(connectedProperties, world, state, pos2.north(), n2, textureAtlasSprite, n3);
               borderFlags[3] = borderFlags[3] && !isNeighbour(connectedProperties, world, state, pos2.south(), n2, textureAtlasSprite, n3);
            }
            break;
         case 1:
            borderFlags[0] = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
            borderFlags[1] = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
            borderFlags[2] = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
            borderFlags[3] = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
            if (connectedProperties.innerSeams) {
               BlockPos pos3 = pos.up();
               borderFlags[0] = borderFlags[0] && !isNeighbour(connectedProperties, world, state, pos3.west(), n2, textureAtlasSprite, n3);
               borderFlags[1] = borderFlags[1] && !isNeighbour(connectedProperties, world, state, pos3.east(), n2, textureAtlasSprite, n3);
               borderFlags[2] = borderFlags[2] && !isNeighbour(connectedProperties, world, state, pos3.south(), n2, textureAtlasSprite, n3);
               borderFlags[3] = borderFlags[3] && !isNeighbour(connectedProperties, world, state, pos3.north(), n2, textureAtlasSprite, n3);
            }
            break;
         case 2:
            borderFlags[0] = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
            borderFlags[1] = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
            borderFlags[2] = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
            borderFlags[3] = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
            if (connectedProperties.innerSeams) {
               BlockPos pos4 = pos.north();
               borderFlags[0] = borderFlags[0] && !isNeighbour(connectedProperties, world, state, pos4.east(), n2, textureAtlasSprite, n3);
               borderFlags[1] = borderFlags[1] && !isNeighbour(connectedProperties, world, state, pos4.west(), n2, textureAtlasSprite, n3);
               borderFlags[2] = borderFlags[2] && !isNeighbour(connectedProperties, world, state, pos4.down(), n2, textureAtlasSprite, n3);
               borderFlags[3] = borderFlags[3] && !isNeighbour(connectedProperties, world, state, pos4.up(), n2, textureAtlasSprite, n3);
            }

            if (n == 1) {
               switchValues(0, 1, borderFlags);
               switchValues(2, 3, borderFlags);
            }
            break;
         case 3:
            borderFlags[0] = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
            borderFlags[1] = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
            borderFlags[2] = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
            borderFlags[3] = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
            if (connectedProperties.innerSeams) {
               BlockPos pos5 = pos.south();
               borderFlags[0] = borderFlags[0] && !isNeighbour(connectedProperties, world, state, pos5.west(), n2, textureAtlasSprite, n3);
               borderFlags[1] = borderFlags[1] && !isNeighbour(connectedProperties, world, state, pos5.east(), n2, textureAtlasSprite, n3);
               borderFlags[2] = borderFlags[2] && !isNeighbour(connectedProperties, world, state, pos5.down(), n2, textureAtlasSprite, n3);
               borderFlags[3] = borderFlags[3] && !isNeighbour(connectedProperties, world, state, pos5.up(), n2, textureAtlasSprite, n3);
            }
            break;
         case 4:
            borderFlags[0] = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
            borderFlags[1] = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
            borderFlags[2] = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
            borderFlags[3] = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
            if (connectedProperties.innerSeams) {
               BlockPos pos6 = pos.west();
               borderFlags[0] = borderFlags[0] && !isNeighbour(connectedProperties, world, state, pos6.north(), n2, textureAtlasSprite, n3);
               borderFlags[1] = borderFlags[1] && !isNeighbour(connectedProperties, world, state, pos6.south(), n2, textureAtlasSprite, n3);
               borderFlags[2] = borderFlags[2] && !isNeighbour(connectedProperties, world, state, pos6.down(), n2, textureAtlasSprite, n3);
               borderFlags[3] = borderFlags[3] && !isNeighbour(connectedProperties, world, state, pos6.up(), n2, textureAtlasSprite, n3);
            }
            break;
         case 5:
            borderFlags[0] = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
            borderFlags[1] = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
            borderFlags[2] = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
            borderFlags[3] = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
            if (connectedProperties.innerSeams) {
               BlockPos pos7 = pos.east();
               borderFlags[0] = borderFlags[0] && !isNeighbour(connectedProperties, world, state, pos7.south(), n2, textureAtlasSprite, n3);
               borderFlags[1] = borderFlags[1] && !isNeighbour(connectedProperties, world, state, pos7.north(), n2, textureAtlasSprite, n3);
               borderFlags[2] = borderFlags[2] && !isNeighbour(connectedProperties, world, state, pos7.down(), n2, textureAtlasSprite, n3);
               borderFlags[3] = borderFlags[3] && !isNeighbour(connectedProperties, world, state, pos7.up(), n2, textureAtlasSprite, n3);
            }

            if (n == 2) {
               switchValues(0, 1, borderFlags);
               switchValues(2, 3, borderFlags);
            }
      }

      byte b = 0;
      if (borderFlags[0] & !borderFlags[1] & !borderFlags[2] & !borderFlags[3]) {
         b = 3;
      } else if (!borderFlags[0] & borderFlags[1] & !borderFlags[2] & !borderFlags[3]) {
         b = 1;
      } else if (!borderFlags[0] & !borderFlags[1] & borderFlags[2] & !borderFlags[3]) {
         b = 12;
      } else if (!borderFlags[0] & !borderFlags[1] & !borderFlags[2] & borderFlags[3]) {
         b = 36;
      } else if (borderFlags[0] & borderFlags[1] & !borderFlags[2] & !borderFlags[3]) {
         b = 2;
      } else if (!borderFlags[0] & !borderFlags[1] & borderFlags[2] & borderFlags[3]) {
         b = 24;
      } else if (borderFlags[0] & !borderFlags[1] & borderFlags[2] & !borderFlags[3]) {
         b = 15;
      } else if (borderFlags[0] & !borderFlags[1] & !borderFlags[2] & borderFlags[3]) {
         b = 39;
      } else if (!borderFlags[0] & borderFlags[1] & borderFlags[2] & !borderFlags[3]) {
         b = 13;
      } else if (!borderFlags[0] & borderFlags[1] & !borderFlags[2] & borderFlags[3]) {
         b = 37;
      } else if (!borderFlags[0] & borderFlags[1] & borderFlags[2] & borderFlags[3]) {
         b = 25;
      } else if (borderFlags[0] & !borderFlags[1] & borderFlags[2] & borderFlags[3]) {
         b = 27;
      } else if (borderFlags[0] & borderFlags[1] & !borderFlags[2] & borderFlags[3]) {
         b = 38;
      } else if (borderFlags[0] & borderFlags[1] & borderFlags[2] & !borderFlags[3]) {
         b = 14;
      } else if (borderFlags[0] & borderFlags[1] & borderFlags[2] & borderFlags[3]) {
         b = 26;
      }

      if (b == 0) {
         return b;
      } else if (!Config.isConnectedTexturesFancy()) {
         return b;
      } else {
         switch (n2) {
            case 0:
               borderFlags[0] = !isNeighbour(connectedProperties, world, state, pos.east().north(), n2, textureAtlasSprite, n3);
               borderFlags[1] = !isNeighbour(connectedProperties, world, state, pos.west().north(), n2, textureAtlasSprite, n3);
               borderFlags[2] = !isNeighbour(connectedProperties, world, state, pos.east().south(), n2, textureAtlasSprite, n3);
               borderFlags[3] = !isNeighbour(connectedProperties, world, state, pos.west().south(), n2, textureAtlasSprite, n3);
               if (connectedProperties.innerSeams) {
                  BlockPos pos8 = pos.down();
                  borderFlags[0] = borderFlags[0] || isNeighbour(connectedProperties, world, state, pos8.east().north(), n2, textureAtlasSprite, n3);
                  borderFlags[1] = borderFlags[1] || isNeighbour(connectedProperties, world, state, pos8.west().north(), n2, textureAtlasSprite, n3);
                  borderFlags[2] = borderFlags[2] || isNeighbour(connectedProperties, world, state, pos8.east().south(), n2, textureAtlasSprite, n3);
                  borderFlags[3] = borderFlags[3] || isNeighbour(connectedProperties, world, state, pos8.west().south(), n2, textureAtlasSprite, n3);
               }
               break;
            case 1:
               borderFlags[0] = !isNeighbour(connectedProperties, world, state, pos.east().south(), n2, textureAtlasSprite, n3);
               borderFlags[1] = !isNeighbour(connectedProperties, world, state, pos.west().south(), n2, textureAtlasSprite, n3);
               borderFlags[2] = !isNeighbour(connectedProperties, world, state, pos.east().north(), n2, textureAtlasSprite, n3);
               borderFlags[3] = !isNeighbour(connectedProperties, world, state, pos.west().north(), n2, textureAtlasSprite, n3);
               if (connectedProperties.innerSeams) {
                  BlockPos pos9 = pos.up();
                  borderFlags[0] = borderFlags[0] || isNeighbour(connectedProperties, world, state, pos9.east().south(), n2, textureAtlasSprite, n3);
                  borderFlags[1] = borderFlags[1] || isNeighbour(connectedProperties, world, state, pos9.west().south(), n2, textureAtlasSprite, n3);
                  borderFlags[2] = borderFlags[2] || isNeighbour(connectedProperties, world, state, pos9.east().north(), n2, textureAtlasSprite, n3);
                  borderFlags[3] = borderFlags[3] || isNeighbour(connectedProperties, world, state, pos9.west().north(), n2, textureAtlasSprite, n3);
               }
               break;
            case 2:
               borderFlags[0] = !isNeighbour(connectedProperties, world, state, pos.west().down(), n2, textureAtlasSprite, n3);
               borderFlags[1] = !isNeighbour(connectedProperties, world, state, pos.east().down(), n2, textureAtlasSprite, n3);
               borderFlags[2] = !isNeighbour(connectedProperties, world, state, pos.west().up(), n2, textureAtlasSprite, n3);
               borderFlags[3] = !isNeighbour(connectedProperties, world, state, pos.east().up(), n2, textureAtlasSprite, n3);
               if (connectedProperties.innerSeams) {
                  BlockPos pos10 = pos.north();
                  borderFlags[0] = borderFlags[0] || isNeighbour(connectedProperties, world, state, pos10.west().down(), n2, textureAtlasSprite, n3);
                  borderFlags[1] = borderFlags[1] || isNeighbour(connectedProperties, world, state, pos10.east().down(), n2, textureAtlasSprite, n3);
                  borderFlags[2] = borderFlags[2] || isNeighbour(connectedProperties, world, state, pos10.west().up(), n2, textureAtlasSprite, n3);
                  borderFlags[3] = borderFlags[3] || isNeighbour(connectedProperties, world, state, pos10.east().up(), n2, textureAtlasSprite, n3);
               }

               if (n == 1) {
                  switchValues(0, 3, borderFlags);
                  switchValues(1, 2, borderFlags);
               }
               break;
            case 3:
               borderFlags[0] = !isNeighbour(connectedProperties, world, state, pos.east().down(), n2, textureAtlasSprite, n3);
               borderFlags[1] = !isNeighbour(connectedProperties, world, state, pos.west().down(), n2, textureAtlasSprite, n3);
               borderFlags[2] = !isNeighbour(connectedProperties, world, state, pos.east().up(), n2, textureAtlasSprite, n3);
               borderFlags[3] = !isNeighbour(connectedProperties, world, state, pos.west().up(), n2, textureAtlasSprite, n3);
               if (connectedProperties.innerSeams) {
                  BlockPos pos11 = pos.south();
                  borderFlags[0] = borderFlags[0] || isNeighbour(connectedProperties, world, state, pos11.east().down(), n2, textureAtlasSprite, n3);
                  borderFlags[1] = borderFlags[1] || isNeighbour(connectedProperties, world, state, pos11.west().down(), n2, textureAtlasSprite, n3);
                  borderFlags[2] = borderFlags[2] || isNeighbour(connectedProperties, world, state, pos11.east().up(), n2, textureAtlasSprite, n3);
                  borderFlags[3] = borderFlags[3] || isNeighbour(connectedProperties, world, state, pos11.west().up(), n2, textureAtlasSprite, n3);
               }
               break;
            case 4:
               borderFlags[0] = !isNeighbour(connectedProperties, world, state, pos.down().south(), n2, textureAtlasSprite, n3);
               borderFlags[1] = !isNeighbour(connectedProperties, world, state, pos.down().north(), n2, textureAtlasSprite, n3);
               borderFlags[2] = !isNeighbour(connectedProperties, world, state, pos.up().south(), n2, textureAtlasSprite, n3);
               borderFlags[3] = !isNeighbour(connectedProperties, world, state, pos.up().north(), n2, textureAtlasSprite, n3);
               if (connectedProperties.innerSeams) {
                  BlockPos pos12 = pos.west();
                  borderFlags[0] = borderFlags[0] || isNeighbour(connectedProperties, world, state, pos12.down().south(), n2, textureAtlasSprite, n3);
                  borderFlags[1] = borderFlags[1] || isNeighbour(connectedProperties, world, state, pos12.down().north(), n2, textureAtlasSprite, n3);
                  borderFlags[2] = borderFlags[2] || isNeighbour(connectedProperties, world, state, pos12.up().south(), n2, textureAtlasSprite, n3);
                  borderFlags[3] = borderFlags[3] || isNeighbour(connectedProperties, world, state, pos12.up().north(), n2, textureAtlasSprite, n3);
               }
               break;
            case 5:
               borderFlags[0] = !isNeighbour(connectedProperties, world, state, pos.down().north(), n2, textureAtlasSprite, n3);
               borderFlags[1] = !isNeighbour(connectedProperties, world, state, pos.down().south(), n2, textureAtlasSprite, n3);
               borderFlags[2] = !isNeighbour(connectedProperties, world, state, pos.up().north(), n2, textureAtlasSprite, n3);
               borderFlags[3] = !isNeighbour(connectedProperties, world, state, pos.up().south(), n2, textureAtlasSprite, n3);
               if (connectedProperties.innerSeams) {
                  BlockPos pos13 = pos.east();
                  borderFlags[0] = borderFlags[0] || isNeighbour(connectedProperties, world, state, pos13.down().north(), n2, textureAtlasSprite, n3);
                  borderFlags[1] = borderFlags[1] || isNeighbour(connectedProperties, world, state, pos13.down().south(), n2, textureAtlasSprite, n3);
                  borderFlags[2] = borderFlags[2] || isNeighbour(connectedProperties, world, state, pos13.up().north(), n2, textureAtlasSprite, n3);
                  borderFlags[3] = borderFlags[3] || isNeighbour(connectedProperties, world, state, pos13.up().south(), n2, textureAtlasSprite, n3);
               }

               if (n == 2) {
                  switchValues(0, 3, borderFlags);
                  switchValues(1, 2, borderFlags);
               }
         }

         if (b == 13 && borderFlags[0]) {
            b = 4;
         } else if (b == 15 && borderFlags[1]) {
            b = 5;
         } else if (b == 37 && borderFlags[2]) {
            b = 16;
         } else if (b == 39 && borderFlags[3]) {
            b = 17;
         } else if (b == 14 && borderFlags[0] && borderFlags[1]) {
            b = 7;
         } else if (b == 25 && borderFlags[0] && borderFlags[2]) {
            b = 6;
         } else if (b == 27 && borderFlags[3] && borderFlags[1]) {
            b = 19;
         } else if (b == 38 && borderFlags[3] && borderFlags[2]) {
            b = 18;
         } else if (b == 14 && !borderFlags[0] && borderFlags[1]) {
            b = 31;
         } else if (b == 25 && borderFlags[0] && !borderFlags[2]) {
            b = 30;
         } else if (b == 27 && !borderFlags[3] && borderFlags[1]) {
            b = 41;
         } else if (b == 38 && borderFlags[3] && !borderFlags[2]) {
            b = 40;
         } else if (b == 14 && borderFlags[0] && !borderFlags[1]) {
            b = 29;
         } else if (b == 25 && !borderFlags[0] && borderFlags[2]) {
            b = 28;
         } else if (b == 27 && borderFlags[3] && !borderFlags[1]) {
            b = 43;
         } else if (b == 38 && !borderFlags[3] && borderFlags[2]) {
            b = 42;
         } else if (b == 26 && borderFlags[0] && borderFlags[1] && borderFlags[2] && borderFlags[3]) {
            b = 46;
         } else if (b == 26 && !borderFlags[0] && borderFlags[1] && borderFlags[2] && borderFlags[3]) {
            b = 9;
         } else if (b == 26 && borderFlags[0] && !borderFlags[1] && borderFlags[2] && borderFlags[3]) {
            b = 21;
         } else if (b == 26 && borderFlags[0] && borderFlags[1] && !borderFlags[2] && borderFlags[3]) {
            b = 8;
         } else if (b == 26 && borderFlags[0] && borderFlags[1] && borderFlags[2] && !borderFlags[3]) {
            b = 20;
         } else if (b == 26 && borderFlags[0] && borderFlags[1] && !borderFlags[2] && !borderFlags[3]) {
            b = 11;
         } else if (b == 26 && !borderFlags[0] && !borderFlags[1] && borderFlags[2] && borderFlags[3]) {
            b = 22;
         } else if (b == 26 && !borderFlags[0] && borderFlags[1] && !borderFlags[2] && borderFlags[3]) {
            b = 23;
         } else if (b == 26 && borderFlags[0] && !borderFlags[1] && borderFlags[2] && !borderFlags[3]) {
            b = 10;
         } else if (b == 26 && borderFlags[0] && !borderFlags[1] && !borderFlags[2] && borderFlags[3]) {
            b = 34;
         } else if (b == 26 && !borderFlags[0] && borderFlags[1] && borderFlags[2] && !borderFlags[3]) {
            b = 35;
         } else if (b == 26 && borderFlags[0] && !borderFlags[1] && !borderFlags[2] && !borderFlags[3]) {
            b = 32;
         } else if (b == 26 && !borderFlags[0] && borderFlags[1] && !borderFlags[2] && !borderFlags[3]) {
            b = 33;
         } else if (b == 26 && !borderFlags[0] && !borderFlags[1] && borderFlags[2] && !borderFlags[3]) {
            b = 44;
         } else if (b == 26 && !borderFlags[0] && !borderFlags[1] && !borderFlags[2] && borderFlags[3]) {
            b = 45;
         }

         return b;
      }
   }

   private static int getWoodAxis(int n, int n2) {
      switch ((n2 & 12) >> 2) {
         case 1:
            return 2;
         case 2:
            return 1;
         default:
            return 0;
      }
   }

   private static TextureAtlasSprite getConnectedTextureVertical(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, int n2, TextureAtlasSprite textureAtlasSprite, int n3
   ) {
      boolean flag = false;
      boolean flag2 = false;
      switch (n) {
         case 0:
            if (n2 == 1) {
               flag = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
               flag2 = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
            } else if (n2 == 0) {
               flag = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
               flag2 = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
            } else {
               flag = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
               flag2 = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
            }
            break;
         case 1:
            if (n2 == 3) {
               flag = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
               flag2 = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
            } else if (n2 == 2) {
               flag = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
               flag2 = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
            } else {
               flag = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
               flag2 = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
            }
            break;
         case 2:
            if (n2 == 5) {
               flag = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
               flag2 = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
            } else if (n2 == 4) {
               flag = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
               flag2 = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
            } else {
               flag = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
               flag2 = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
            }
      }

      byte b = 3;
      if (flag) {
         if (flag2) {
            b = 1;
         } else {
            b = 2;
         }
      } else if (flag2) {
         b = 0;
      } else {
         b = 3;
      }

      return connectedProperties.tileIcons[b];
   }

   private static List makePropertyList(ConnectedProperties[][] connectedPropertiesArrs) {
      ArrayList list = new ArrayList();
      if (connectedPropertiesArrs != null) {
         for (int i = 0; i < connectedPropertiesArrs.length; i++) {
            ConnectedProperties[] connectedPropertiesArr = connectedPropertiesArrs[i];
            ArrayList list2 = null;
            if (connectedPropertiesArr != null) {
               list2 = new ArrayList<>(Arrays.asList(connectedPropertiesArr));
            }

            list.add(list2);
         }
      }

      return list;
   }

   private static int getQuartzAxis(int n, int n2) {
      switch (n2) {
         case 3:
            return 2;
         case 4:
            return 1;
         default:
            return 0;
      }
   }

   private static void addToBlockList(ConnectedProperties connectedProperties, List list) {
      if (connectedProperties.matchBlocks != null) {
         for (int i = 0; i < connectedProperties.matchBlocks.length; i++) {
            int blockId = connectedProperties.matchBlocks[i].getBlockId();
            if (blockId < 0) {
               Config.warn("Invalid block ID: " + blockId);
            } else {
               addToList(connectedProperties, list, blockId);
            }
         }
      }
   }

   public static BakedQuad[] getConnectedTextureSingle(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing, BakedQuad bakedQuad, boolean flag, int n, RenderEnv renderEnv) {
      Block block = state.getBlock();
      if (!(state instanceof BlockStateBase)) {
         return renderEnv.getArrayQuadsCtm(bakedQuad);
      } else {
         BlockStateBase blockStateBase = (BlockStateBase)state;
         TextureAtlasSprite sprite = bakedQuad.getSprite();
         if (tileProperties != null) {
            int indexInMap = sprite.getIndexInMap();
            if (indexInMap >= 0 && indexInMap < tileProperties.length) {
               ConnectedProperties[] connectedPropertiesArr = tileProperties[indexInMap];
               if (connectedPropertiesArr != null) {
                  int side = getSide(facing);

                  for (int i = 0; i < connectedPropertiesArr.length; i++) {
                     ConnectedProperties connectedProperties = connectedPropertiesArr[i];
                     if (connectedProperties != null && connectedProperties.matchesBlockId(blockStateBase.getBlockId())) {
                        BakedQuad[] connectedTexture = getConnectedTexture(connectedProperties, world, blockStateBase, pos, side, bakedQuad, n, renderEnv);
                        if (connectedTexture != null) {
                           return connectedTexture;
                        }
                     }
                  }
               }
            }
         }

         if (blockProperties != null && flag) {
            int blockId = renderEnv.getBlockId();
            if (blockId >= 0 && blockId < blockProperties.length) {
               ConnectedProperties[] connectedPropertiesArr2 = blockProperties[blockId];
               if (connectedPropertiesArr2 != null) {
                  int side2 = getSide(facing);

                  for (int j = 0; j < connectedPropertiesArr2.length; j++) {
                     ConnectedProperties connectedProperties2 = connectedPropertiesArr2[j];
                     if (connectedProperties2 != null && connectedProperties2.matchesIcon(sprite)) {
                        BakedQuad[] connectedTexture2 = getConnectedTexture(connectedProperties2, world, blockStateBase, pos, side2, bakedQuad, n, renderEnv);
                        if (connectedTexture2 != null) {
                           return connectedTexture2;
                        }
                     }
                  }
               }
            }
         }

         return renderEnv.getArrayQuadsCtm(bakedQuad);
      }
   }

   private static TextureAtlasSprite getConnectedTextureCtm(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, int n2, TextureAtlasSprite textureAtlasSprite, int n3, RenderEnv renderEnv
   ) {
      int n4 = getConnectedTextureCtmIndex(connectedProperties, world, state, pos, n, n2, textureAtlasSprite, n3, renderEnv);
      return connectedProperties.tileIcons[n4];
   }

   public static int getSide(EnumFacing facing) {
      if (facing == null) {
         return -1;
      } else {
         switch (facing) {
            case DOWN:
               return 0;
            case UP:
               return 1;
            case EAST:
               return 5;
            case WEST:
               return 4;
            case NORTH:
               return 2;
            case SOUTH:
               return 3;
            default:
               return -1;
         }
      }
   }

   private static BakedQuad[] getConnectedTextureMultiPass(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing, BakedQuad bakedQuad, RenderEnv renderEnv) {
      BakedQuad[] connectedTextureSingle = getConnectedTextureSingle(world, state, pos, facing, bakedQuad, true, 0, renderEnv);
      if (!multipass) {
         return connectedTextureSingle;
      } else if (connectedTextureSingle.length == 1 && connectedTextureSingle[0] == bakedQuad) {
         return connectedTextureSingle;
      } else {
         List listQuadsCtmMultipass = renderEnv.getListQuadsCtmMultipass(connectedTextureSingle);

         for (int i = 0; i < listQuadsCtmMultipass.size(); i++) {
            BakedQuad bakedQuad3 = (BakedQuad)listQuadsCtmMultipass.get(i);

            for (int j = 0; j < 3; j++) {
               BakedQuad[] connectedTextureSingle2 = getConnectedTextureSingle(world, state, pos, facing, bakedQuad3, false, j + 1, renderEnv);
               if (connectedTextureSingle2.length != 1 || connectedTextureSingle2[0] == bakedQuad3) {
                  break;
               }

               bakedQuad3 = connectedTextureSingle2[0];
            }

            listQuadsCtmMultipass.set(i, bakedQuad3);
         }

         for (int k = 0; k < connectedTextureSingle.length; k++) {
            connectedTextureSingle[k] = (BakedQuad)listQuadsCtmMultipass.get(k);
         }

         return connectedTextureSingle;
      }
   }

   public static BakedQuad[] getConnectedTexture(IBlockAccess world, IBlockState state, BlockPos pos, BakedQuad bakedQuad, RenderEnv renderEnv) {
      if (bakedQuad.getSprite() == null) {
         return renderEnv.getArrayQuadsCtm(bakedQuad);
      } else {
         Block block = state.getBlock();
         if (isHideGlassPane(world, state, pos, bakedQuad, renderEnv)) {
            bakedQuad = getQuad(emptySprite, bakedQuad);
            return renderEnv.getArrayQuadsCtm(bakedQuad);
         } else {
            return getConnectedTextureMultiPass(world, state, pos, bakedQuad.getFace(), bakedQuad, renderEnv);
         }
      }
   }

   private static synchronized BakedQuad getQuad(TextureAtlasSprite textureAtlasSprite, BakedQuad bakedQuad) {
      if (spriteQuadMaps == null) {
         return bakedQuad;
      } else {
         int indexInMap = textureAtlasSprite.getIndexInMap();
         if (indexInMap >= 0 && indexInMap < spriteQuadMaps.length) {
            Object obj = spriteQuadMaps[indexInMap];
            if (obj == null) {
               obj = new IdentityHashMap(1);
               spriteQuadMaps[indexInMap] = (Map)obj;
            }

            BakedQuad bakedQuad2 = (BakedQuad)((Map)obj).get(bakedQuad);
            if (bakedQuad2 == null) {
               bakedQuad2 = makeSpriteQuad(bakedQuad, textureAtlasSprite);
               ((Map)obj).put(bakedQuad, bakedQuad2);
            }

            return bakedQuad2;
         } else {
            return bakedQuad;
         }
      }
   }

   private static boolean isNeighbourMatching(ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, TextureAtlasSprite textureAtlasSprite, int n2) {
      IBlockState blockState = world.getBlockState(pos);
      if (blockState == AIR_DEFAULT_STATE) {
         return false;
      } else {
         if (connectedProperties.matchBlocks != null && blockState instanceof BlockStateBase) {
            BlockStateBase blockStateBase = (BlockStateBase)blockState;
            if (!connectedProperties.matchesBlock(blockStateBase.getBlockId(), blockStateBase.getMetadata())) {
               return false;
            }
         }

         if (connectedProperties.matchTileIcons != null) {
            if (getNeighbourIcon(world, state, pos, blockState, n) != textureAtlasSprite) {
               return false;
            }
         }

         IBlockState blockState2 = world.getBlockState(pos.offset(getFacing(n)));
         return blockState2.getBlock().isOpaqueCube() ? false : n != 1 || blockState2.getBlock() != Blocks.snow_layer;
      }
   }

   private static int fixSideByAxis(int n, int n2) {
      switch (n2) {
         case 0:
            return n;
         case 1:
            switch (n) {
               case 0:
                  return 2;
               case 1:
                  return 3;
               case 2:
                  return 1;
               case 3:
                  return 0;
               default:
                  return n;
            }
         case 2:
            switch (n) {
               case 0:
                  return 4;
               case 1:
                  return 5;
               case 2:
               case 3:
               default:
                  return n;
               case 4:
                  return 1;
               case 5:
                  return 0;
            }
         default:
            return n;
      }
   }

   private static BakedQuad[] getConnectedTextureOverlay(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, int n2, BakedQuad bakedQuad, int n3, RenderEnv renderEnv
   ) {
      if (!bakedQuad.isFullFace()) {
         return null;
      } else {
         TextureAtlasSprite sprite = bakedQuad.getSprite();
         BlockDir[] sideDirections = getSideDirections(n2, n);
         boolean[] borderFlags = renderEnv.getBorderFlags();

         for (int i = 0; i < 4; i++) {
            borderFlags[i] = isNeighbourOverlay(connectedProperties, world, state, sideDirections[i].offset(pos), n2, sprite, n3);
         }

         ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(connectedProperties.layer);

         Object obj;
         try {
            if (!borderFlags[0] || !borderFlags[1] || !borderFlags[2] || !borderFlags[3]) {
               if (borderFlags[0] && borderFlags[1] && borderFlags[2]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[5], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  obj = null;
                  return (BakedQuad[])obj;
               }

               if (borderFlags[0] && borderFlags[2] && borderFlags[3]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[6], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  obj = null;
                  return (BakedQuad[])obj;
               }

               if (borderFlags[1] && borderFlags[2] && borderFlags[3]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[12], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  obj = null;
                  return (BakedQuad[])obj;
               }

               if (borderFlags[0] && borderFlags[1] && borderFlags[3]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[13], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  obj = null;
                  return (BakedQuad[])obj;
               }

               BlockDir[] edgeDirections = getEdgeDirections(n2, n);
               boolean[] borderFlags2 = renderEnv.getBorderFlags2();

               for (int j = 0; j < 4; j++) {
                  borderFlags2[j] = isNeighbourOverlay(connectedProperties, world, state, edgeDirections[j].offset(pos), n2, sprite, n3);
               }

               if (borderFlags[1] && borderFlags[2]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[3], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  if (borderFlags2[3]) {
                     listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[16], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  }

                  return (BakedQuad[])null;
               }

               if (borderFlags[0] && borderFlags[2]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[4], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  if (borderFlags2[2]) {
                     listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[14], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  }

                  return (BakedQuad[])null;
               }

               if (borderFlags[1] && borderFlags[3]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[10], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  if (borderFlags2[1]) {
                     listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[2], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  }

                  return (BakedQuad[])null;
               }

               if (borderFlags[0] && borderFlags[3]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[11], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  if (borderFlags2[0]) {
                     listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[0], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
                  }

                  return (BakedQuad[])null;
               }

               boolean[] borderFlags3 = renderEnv.getBorderFlags3();

               for (int k = 0; k < 4; k++) {
                  borderFlags3[k] = isNeighbourMatching(connectedProperties, world, state, sideDirections[k].offset(pos), n2, sprite, n3);
               }

               if (borderFlags[0]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[9], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
               }

               if (borderFlags[1]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[7], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
               }

               if (borderFlags[2]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[1], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
               }

               if (borderFlags[3]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[15], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
               }

               if (borderFlags2[0] && (borderFlags3[1] || borderFlags3[2]) && !borderFlags[1] && !borderFlags[2]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[0], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
               }

               if (borderFlags2[1] && (borderFlags3[0] || borderFlags3[2]) && !borderFlags[0] && !borderFlags[2]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[2], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
               }

               if (borderFlags2[2] && (borderFlags3[1] || borderFlags3[3]) && !borderFlags[1] && !borderFlags[3]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[14], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
               }

               if (borderFlags2[3] && (borderFlags3[0] || borderFlags3[3]) && !borderFlags[0] && !borderFlags[3]) {
                  listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[16], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
               }

               return (BakedQuad[])null;
            }

            listQuadsOverlay.addQuad(getQuadFull(connectedProperties.tileIcons[8], bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
            obj = null;
         } finally {
            if (listQuadsOverlay.size() > 0) {
               renderEnv.setOverlaysRendered(true);
            }
         }

         return (BakedQuad[])obj;
      }
   }

   private static TextureAtlasSprite getConnectedTextureVerticalHorizontal(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, int n2, TextureAtlasSprite textureAtlasSprite, int n3
   ) {
      TextureAtlasSprite[] tileIcons = connectedProperties.tileIcons;
      TextureAtlasSprite textureAtlasSprite2 = getConnectedTextureVertical(connectedProperties, world, state, pos, n, n2, textureAtlasSprite, n3);
      if (textureAtlasSprite2 != null && textureAtlasSprite2 != textureAtlasSprite && textureAtlasSprite2 != tileIcons[3]) {
         return textureAtlasSprite2;
      } else {
         TextureAtlasSprite textureAtlasSprite3 = getConnectedTextureHorizontal(connectedProperties, world, state, pos, n, n2, textureAtlasSprite, n3);
         return textureAtlasSprite3 == tileIcons[0] ? tileIcons[4] : (textureAtlasSprite3 == tileIcons[1] ? tileIcons[5] : (textureAtlasSprite3 == tileIcons[2] ? tileIcons[6] : textureAtlasSprite3));
      }
   }

   private static BakedQuad[] getConnectedTextureOverlayCtm(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, int n2, BakedQuad bakedQuad, int n3, RenderEnv renderEnv
   ) {
      if (!bakedQuad.isFullFace()) {
         return null;
      } else {
         ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(connectedProperties.layer);

         Object obj;
         try {
            TextureAtlasSprite connectedTextureCtm = getConnectedTextureCtm(connectedProperties, world, state, pos, n, n2, bakedQuad.getSprite(), n3, renderEnv);
            if (connectedTextureCtm != null) {
               listQuadsOverlay.addQuad(getQuadFull(connectedTextureCtm, bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
            }

            obj = null;
         } finally {
            if (listQuadsOverlay.size() > 0) {
               renderEnv.setOverlaysRendered(true);
            }
         }

         return (BakedQuad[])obj;
      }
   }

   private static EnumFacing getFacing(int n) {
      switch (n) {
         case 0:
            return EnumFacing.DOWN;
         case 1:
            return EnumFacing.UP;
         case 2:
            return EnumFacing.NORTH;
         case 3:
            return EnumFacing.SOUTH;
         case 4:
            return EnumFacing.WEST;
         case 5:
            return EnumFacing.EAST;
         default:
            return EnumFacing.UP;
      }
   }

   private static TextureAtlasSprite getConnectedTextureRepeat(ConnectedProperties connectedProperties, BlockPos pos, int n) {
      if (connectedProperties.tileIcons.length == 1) {
         return connectedProperties.tileIcons[0];
      } else {
         int x = pos.getX();
         int y = pos.getY();
         int z = pos.getZ();
         int n2 = 0;
         int n3 = 0;
         switch (n) {
            case 0:
               n2 = x;
               n3 = -z - 1;
               break;
            case 1:
               n2 = x;
               n3 = z;
               break;
            case 2:
               n2 = -x - 1;
               n3 = -y;
               break;
            case 3:
               n2 = x;
               n3 = -y;
               break;
            case 4:
               n2 = z;
               n3 = -y;
               break;
            case 5:
               n2 = -z - 1;
               n3 = -y;
         }

         n2 %= connectedProperties.width;
         n3 %= connectedProperties.height;
         if (n2 < 0) {
            n2 += connectedProperties.width;
         }

         if (n3 < 0) {
            n3 += connectedProperties.height;
         }

         int n4 = n3 * connectedProperties.width + n2;
         return connectedProperties.tileIcons[n4];
      }
   }

   private static boolean isFullCubeModel(IBlockState state) {
      if (state.getBlock().isFullCube()) {
         return true;
      } else {
         Block block = state.getBlock();
         return block instanceof BlockGlass ? true : block instanceof BlockStainedGlass;
      }
   }

   private static TextureAtlasSprite getConnectedTextureHorizontal(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, int n2, TextureAtlasSprite textureAtlasSprite, int n3
   ) {
      boolean flag;
      boolean flag2;
      flag = false;
      flag2 = false;
      switch (n) {
         case 0:
            switch (n2) {
               case 0:
                  flag = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
                  break;
               case 1:
                  flag = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
                  break;
               case 2:
                  flag = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
                  break;
               case 3:
                  flag = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
                  break;
               case 4:
                  flag = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
                  break;
               case 5:
                  flag = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
               default:
                  break;
            }
            break;
         case 1:
            switch (n2) {
               case 0:
                  flag = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
                  break;
               case 1:
                  flag = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
                  break;
               case 2:
                  flag = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
                  break;
               case 3:
                  flag = isNeighbour(connectedProperties, world, state, pos.west(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.east(), n2, textureAtlasSprite, n3);
                  break;
               case 4:
                  flag = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
                  break;
               case 5:
                  flag = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
               default:
                  break;
            }
            break;
         case 2:
            switch (n2) {
               case 0:
                  flag = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
                  break;
               case 1:
                  flag = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
                  break;
               case 2:
                  flag = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
                  break;
               case 3:
                  flag = isNeighbour(connectedProperties, world, state, pos.up(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.down(), n2, textureAtlasSprite, n3);
                  break;
               case 4:
                  flag = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
                  break;
               case 5:
                  flag = isNeighbour(connectedProperties, world, state, pos.north(), n2, textureAtlasSprite, n3);
                  flag2 = isNeighbour(connectedProperties, world, state, pos.south(), n2, textureAtlasSprite, n3);
            }
      }

      byte b = 3;
      if (flag) {
         if (flag2) {
            b = 1;
         } else {
            b = 2;
         }
      } else if (flag2) {
         b = 0;
      } else {
         b = 3;
      }

      return connectedProperties.tileIcons[b];
   }

   private static BlockDir[] getSideDirections(int n, int n2) {
      switch (n) {
         case 0:
            return SIDES_Y_NEG_DOWN;
         case 1:
            return SIDES_Y_POS_UP;
         case 2:
            if (n2 == 1) {
               return SIDES_Z_NEG_NORTH_Z_AXIS;
            }

            return SIDES_Z_NEG_NORTH;
         case 3:
            return SIDES_Z_POS_SOUTH;
         case 4:
            return SIDES_X_NEG_WEST;
         case 5:
            if (n2 == 2) {
               return SIDES_X_POS_EAST_X_AXIS;
            }

            return SIDES_X_POS_EAST;
         default:
            throw new IllegalArgumentException("Unknown side: " + n);
      }
   }

   private static boolean detectMultipass() {
      ArrayList list = new ArrayList();

      for (int i = 0; i < tileProperties.length; i++) {
         ConnectedProperties[] connectedPropertiesArr = tileProperties[i];
         if (connectedPropertiesArr != null) {
            list.addAll(Arrays.asList(connectedPropertiesArr));
         }
      }

      for (int j = 0; j < blockProperties.length; j++) {
         ConnectedProperties[] connectedPropertiesArr2 = blockProperties[j];
         if (connectedPropertiesArr2 != null) {
            list.addAll(Arrays.asList(connectedPropertiesArr2));
         }
      }

      ConnectedProperties[] connectedPropertiesArr3 = (ConnectedProperties[])list.toArray(new ConnectedProperties[list.size()]);
      HashSet set = new HashSet();
      HashSet set2 = new HashSet();

      for (int k = 0; k < connectedPropertiesArr3.length; k++) {
         ConnectedProperties connectedProperties = connectedPropertiesArr3[k];
         if (connectedProperties.matchTileIcons != null) {
            set.addAll(Arrays.asList(connectedProperties.matchTileIcons));
         }

         if (connectedProperties.tileIcons != null) {
            set2.addAll(Arrays.asList(connectedProperties.tileIcons));
         }
      }

      set.retainAll(set2);
      return !set.isEmpty();
   }

   private static synchronized BakedQuad getQuadFull(TextureAtlasSprite textureAtlasSprite, BakedQuad bakedQuad, int n) {
      if (spriteQuadFullMaps == null) {
         return null;
      } else if (textureAtlasSprite == null) {
         return null;
      } else {
         int indexInMap = textureAtlasSprite.getIndexInMap();
         if (indexInMap >= 0 && indexInMap < spriteQuadFullMaps.length) {
            Object obj = spriteQuadFullMaps[indexInMap];
            if (obj == null) {
               obj = new EnumMap<>(EnumFacing.class);
               spriteQuadFullMaps[indexInMap] = (Map)obj;
            }

            EnumFacing face = bakedQuad.getFace();
            BakedQuad bakedQuad2 = (BakedQuad)((Map)obj).get(face);
            if (bakedQuad2 == null) {
               bakedQuad2 = BlockModelUtils.makeBakedQuad(face, textureAtlasSprite, n);
               ((Map)obj).put(face, bakedQuad2);
            }

            return bakedQuad2;
         } else {
            return null;
         }
      }
   }

   private static BakedQuad[] getConnectedTextureOverlayFixed(ConnectedProperties connectedProperties, BakedQuad bakedQuad, RenderEnv renderEnv) {
      if (!bakedQuad.isFullFace()) {
         return null;
      } else {
         ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(connectedProperties.layer);

         Object obj;
         try {
            TextureAtlasSprite connectedTextureFixed = getConnectedTextureFixed(connectedProperties);
            if (connectedTextureFixed != null) {
               listQuadsOverlay.addQuad(getQuadFull(connectedTextureFixed, bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
            }

            obj = null;
         } finally {
            if (listQuadsOverlay.size() > 0) {
               renderEnv.setOverlaysRendered(true);
            }
         }

         return (BakedQuad[])obj;
      }
   }

   private static void addToList(ConnectedProperties connectedProperties, List list, int n) {
      while (n >= list.size()) {
         list.add(null);
      }

      Object obj = (List)list.get(n);
      if (obj == null) {
         obj = new ArrayList();
         list.set(n, obj);
      }

      ((List)obj).add(connectedProperties);
   }

   public static void updateIcons(TextureMap textureMap) {
      blockProperties = (ConnectedProperties[][])null;
      tileProperties = (ConnectedProperties[][])null;
      spriteQuadMaps = null;
      spriteQuadCompactMaps = (Map[][])null;
      if (Config.isConnectedTextures()) {
         IResourcePack[] resourcePacks = Config.getResourcePacks();

         for (int i = resourcePacks.length - 1; i >= 0; i--) {
            updateIcons(textureMap, resourcePacks[i]);
         }

         updateIcons(textureMap, Config.getDefaultResourcePack());
         emptySprite = textureMap.registerSprite(new ResourceLocation("mcpatcher/ctm/default/empty"));
         spriteQuadMaps = new Map[textureMap.getCountRegisteredSprites() + 1];
         spriteQuadFullMaps = new Map[textureMap.getCountRegisteredSprites() + 1];
         spriteQuadCompactMaps = new Map[textureMap.getCountRegisteredSprites() + 1][];
         if (blockProperties.length <= 0) {
            blockProperties = (ConnectedProperties[][])null;
         }

         if (tileProperties.length <= 0) {
            tileProperties = (ConnectedProperties[][])null;
         }
      }
   }

   private static BakedQuad[] getConnectedTextureOverlayRepeat(ConnectedProperties connectedProperties, BlockPos pos, int n, BakedQuad bakedQuad, RenderEnv renderEnv) {
      if (!bakedQuad.isFullFace()) {
         return null;
      } else {
         ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(connectedProperties.layer);

         Object obj;
         try {
            TextureAtlasSprite connectedTextureRepeat = getConnectedTextureRepeat(connectedProperties, pos, n);
            if (connectedTextureRepeat != null) {
               listQuadsOverlay.addQuad(getQuadFull(connectedTextureRepeat, bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
            }

            obj = null;
         } finally {
            if (listQuadsOverlay.size() > 0) {
               renderEnv.setOverlaysRendered(true);
            }
         }

         return (BakedQuad[])obj;
      }
   }

   private static void addToTileList(ConnectedProperties connectedProperties, List list) {
      if (connectedProperties.matchTileIcons != null) {
         for (int i = 0; i < connectedProperties.matchTileIcons.length; i++) {
            TextureAtlasSprite textureAtlasSprite = connectedProperties.matchTileIcons[i];
            if (!(textureAtlasSprite instanceof TextureAtlasSprite)) {
               Config.warn("TextureAtlasSprite is not TextureAtlasSprite: " + textureAtlasSprite + ", name: " + textureAtlasSprite.getIconName());
            } else {
               int indexInMap = textureAtlasSprite.getIndexInMap();
               if (indexInMap < 0) {
                  Config.warn("Invalid tile ID: " + indexInMap + ", icon: " + textureAtlasSprite.getIconName());
               } else {
                  addToList(connectedProperties, list, indexInMap);
               }
            }
         }
      }
   }

   private static void remapVertexUv(int[] ints, int n, TextureAtlasSprite textureAtlasSprite, TextureAtlasSprite textureAtlasSprite2) {
      int n3 = (ints.length / 4) * n;
      float f = Float.intBitsToFloat(ints[n3 + 4]);
      float f2 = Float.intBitsToFloat(ints[n3 + 4 + 1]);
      double spriteU16 = textureAtlasSprite.getSpriteU16(f);
      double spriteV16 = textureAtlasSprite.getSpriteV16(f2);
      ints[n3 + 4] = Float.floatToRawIntBits(textureAtlasSprite2.getInterpolatedU(spriteU16));
      ints[n3 + 4 + 1] = Float.floatToRawIntBits(textureAtlasSprite2.getInterpolatedV(spriteV16));
   }

   private static void updateIconEmpty(TextureMap textureMap) {
   }

   private static boolean isHideGlassPane(IBlockAccess world, IBlockState state, BlockPos pos, BakedQuad bakedQuad, RenderEnv renderEnv) {
      Block block = state.getBlock();
      if (block instanceof BlockPane) {
         if (bakedQuad.getSprite().getIconName().startsWith("minecraft:blocks/glass_pane_top")) {
            return world.getBlockState(pos.offset(bakedQuad.getFace())) == state;
         }
      }

      if (block instanceof BlockPane) {
         EnumFacing face = bakedQuad.getFace();
         if (face != EnumFacing.UP && face != EnumFacing.DOWN) {
            return false;
         }

         if (!bakedQuad.isFaceQuad()) {
            return false;
         }

         BlockPos pos2 = pos.offset(bakedQuad.getFace());
         IBlockState blockState2 = world.getBlockState(pos2);
         if (blockState2.getBlock() != block) {
            return false;
         }

         if (block == Blocks.stained_glass_pane && blockState2.getValue(BlockStainedGlassPane.COLOR) != state.getValue(BlockStainedGlassPane.COLOR)) {
            return false;
         }

         blockState2 = blockState2.getBlock().getActualState(blockState2, world, pos2.getX(), pos2.getY(), pos2.getZ());
         double midX = (double)bakedQuad.getMidX();
         if (midX < 0.4) {
            if ((Boolean)blockState2.getValue(BlockPane.WEST)) {
               return true;
            }
         } else if (midX > 0.6) {
            if ((Boolean)blockState2.getValue(BlockPane.EAST)) {
               return true;
            }
         } else {
            double midZ = bakedQuad.getMidZ();
            if (midZ < 0.4) {
               if ((Boolean)blockState2.getValue(BlockPane.NORTH)) {
                  return true;
               }
            } else {
               if (midZ <= 0.6) {
                  return true;
               }

               if ((Boolean)blockState2.getValue(BlockPane.SOUTH)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private static String[] getDefaultCtmPaths() {
      ArrayList list = new ArrayList();
      String str = "mcpatcher/ctm/default/";
      if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/glass.png"))) {
         list.add(str + "glass.properties");
         list.add(str + "glasspane.properties");
      }

      if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/bookshelf.png"))) {
         list.add(str + "bookshelf.properties");
      }

      if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/sandstone_normal.png"))) {
         list.add(str + "sandstone.properties");
      }

      String[] strings = new String[]{
         "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"
      };

      for (int i = 0; i < strings.length; i++) {
         String str2 = strings[i];
         if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/glass_" + str2 + ".png"))) {
            list.add(str + i + "_glass_" + str2 + "/glass_" + str2 + ".properties");
            list.add(str + i + "_glass_" + str2 + "/glass_pane_" + str2 + ".properties");
         }
      }

      return (String[])list.toArray(new String[list.size()]);
   }

   public static void updateIcons(TextureMap textureMap, IResourcePack resourcePack) {
      String[] strings = ResUtils.collectFiles(resourcePack, "mcpatcher/ctm/", ".properties", getDefaultCtmPaths());
      Arrays.sort((Object[])strings);
      List list = makePropertyList(tileProperties);
      List list2 = makePropertyList(blockProperties);

      for (int i = 0; i < strings.length; i++) {
         String str = strings[i];
         Config.dbg("ConnectedTextures: " + str);

         try {
            InputStream inputStream = resourcePack.getInputStream(new ResourceLocation(str));
            if (inputStream == null) {
               Config.warn("ConnectedTextures file not found: " + str);
            } else {
               PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
               propertiesOrdered.load(inputStream);
               inputStream.close();
               ConnectedProperties connectedProperties = new ConnectedProperties(propertiesOrdered, str);
               if (connectedProperties.isValid(str)) {
                  connectedProperties.updateIcons(textureMap);
                  addToTileList(connectedProperties, list);
                  addToBlockList(connectedProperties, list2);
               }
            }
         } catch (FileNotFoundException e) {
            Config.warn("ConnectedTextures file not found: " + str);
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }

      blockProperties = propertyListToArray(list2);
      tileProperties = propertyListToArray(list);
      multipass = detectMultipass();
      Config.dbg("Multipass connected textures: " + multipass);
   }

   private static synchronized BakedQuad[] getConnectedTextureCtmCompact(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, int n2, BakedQuad bakedQuad, int n3, RenderEnv renderEnv
   ) {
      return ConnectedTexturesCompact.getConnectedTextureCtmCompact(getConnectedTextureCtmIndex(connectedProperties, world, state, pos, n, n2, bakedQuad.getSprite(), n3, renderEnv), connectedProperties, n2, bakedQuad, renderEnv);
   }

   private static TextureAtlasSprite getConnectedTextureRandom(ConnectedProperties connectedProperties, IBlockAccess world, BlockStateBase blockStateBase, BlockPos pos, int n) {
      if (connectedProperties.tileIcons.length == 1) {
         return connectedProperties.tileIcons[0];
      } else {
         int n2 = n / connectedProperties.symmetry * connectedProperties.symmetry;
         if (connectedProperties.linked) {
            BlockPos pos2 = pos.down();

            for (IBlockState i = world.getBlockState(pos2); i.getBlock() == blockStateBase.getBlock(); i = world.getBlockState(pos2)) {
               pos = pos2;
               pos2 = pos2.down();
               if (pos2.getY() < 0) {
                  break;
               }
            }
         }

         int n3 = Config.getRandom(pos, n2) & 2147483647;

         for (int j = 0; j < connectedProperties.randomLoops; j++) {
            n3 = Config.intHash(n3);
         }

         int n4 = 0;
         if (connectedProperties.weights == null) {
            n4 = n3 % connectedProperties.tileIcons.length;
         } else {
            int n5 = n3 % connectedProperties.sumAllWeights;
            int[] sumWeights = connectedProperties.sumWeights;

            for (int k = 0; k < sumWeights.length; k++) {
               if (n5 < sumWeights[k]) {
                  n4 = k;
                  break;
               }
            }
         }

         return connectedProperties.tileIcons[n4];
      }
   }

   private static TextureAtlasSprite getNeighbourIcon(IBlockAccess world, IBlockState state, BlockPos pos, IBlockState state2, int n) {
      state2 = state2.getBlock().getActualState(state2, world, pos.getX(), pos.getY(), pos.getZ());
      IBakedModel modelForState = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(state2);
      if (modelForState == null) {
         return null;
      } else {
         EnumFacing facing = getFacing(n);
         List faceQuads = modelForState.getFaceQuads(facing);
         if (faceQuads == null) {
            return null;
         } else {
            if (Config.isBetterGrass()) {
               faceQuads = BetterGrass.getFaceQuads(world, state2, pos, facing, faceQuads);
            }

            if (faceQuads.size() > 0) {
               return ((BakedQuad)faceQuads.get(0)).getSprite();
            } else {
               List generalQuads = modelForState.getGeneralQuads();
               if (generalQuads == null) {
                  return null;
               } else {
                  for (int i = 0; i < generalQuads.size(); i++) {
                     BakedQuad bakedQuad2 = (BakedQuad)generalQuads.get(i);
                     if (bakedQuad2.getFace() == facing) {
                        return bakedQuad2.getSprite();
                     }
                  }

                  return null;
               }
            }
         }
      }
   }

   private static BakedQuad[] getConnectedTextureOverlayRandom(ConnectedProperties connectedProperties, IBlockAccess world, BlockStateBase blockStateBase, BlockPos pos, int n, BakedQuad bakedQuad, RenderEnv renderEnv) {
      if (!bakedQuad.isFullFace()) {
         return null;
      } else {
         ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(connectedProperties.layer);

         Object obj;
         try {
            TextureAtlasSprite connectedTextureRandom = getConnectedTextureRandom(connectedProperties, world, blockStateBase, pos, n);
            if (connectedTextureRandom != null) {
               listQuadsOverlay.addQuad(getQuadFull(connectedTextureRandom, bakedQuad, connectedProperties.tintIndex), connectedProperties.tintBlockState);
            }

            obj = null;
         } finally {
            if (listQuadsOverlay.size() > 0) {
               renderEnv.setOverlaysRendered(true);
            }
         }

         return (BakedQuad[])obj;
      }
   }

   private static boolean isNeighbourOverlay(ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, int n, TextureAtlasSprite textureAtlasSprite, int n2) {
      IBlockState blockState = world.getBlockState(pos);
      if (!isFullCubeModel(blockState)) {
         return false;
      } else {
         if (connectedProperties.connectBlocks != null) {
            BlockStateBase blockStateBase = (BlockStateBase)blockState;
            if (!Matches.block(blockStateBase.getBlockId(), blockStateBase.getMetadata(), connectedProperties.connectBlocks)) {
               return false;
            }
         }

         if (connectedProperties.connectTileIcons != null) {
            if (!Config.isSameOne(getNeighbourIcon(world, state, pos, blockState, n), connectedProperties.connectTileIcons)) {
               return false;
            }
         }

         IBlockState blockState2 = world.getBlockState(pos.offset(getFacing(n)));
         return blockState2.getBlock().isOpaqueCube()
            ? false
            : (n == 1 && blockState2.getBlock() == Blocks.snow_layer ? false : !isNeighbour(connectedProperties, world, state, pos, blockState, n, textureAtlasSprite, n2));
      }
   }

   private static void switchValues(int n, int n2, boolean[] flags) {
      boolean flag = flags[n];
      flags[n] = flags[n2];
      flags[n2] = flag;
   }

   private static ConnectedProperties[][] propertyListToArray(List list) {
      ConnectedProperties[][] connectedPropertiesArrs = new ConnectedProperties[list.size()][];

      for (int i = 0; i < list.size(); i++) {
         List list2 = (List)list.get(i);
         if (list2 != null) {
            connectedPropertiesArrs[i] = (ConnectedProperties[])list2.toArray(new ConnectedProperties[list2.size()]);
         }
      }

      return connectedPropertiesArrs;
   }

   private static boolean isNeighbour(
      ConnectedProperties connectedProperties, IBlockAccess world, IBlockState state, BlockPos pos, IBlockState state2, int n, TextureAtlasSprite textureAtlasSprite, int n2
   ) {
      if (state == state2) {
         return true;
      } else if (connectedProperties.connect == 2) {
         if (state2 == null) {
            return false;
         } else if (state2 == AIR_DEFAULT_STATE) {
            return false;
         } else {
            return getNeighbourIcon(world, state, pos, state2, n) == textureAtlasSprite;
         }
      } else if (connectedProperties.connect == 3) {
         return state2 == null ? false : (state2 == AIR_DEFAULT_STATE ? false : state2.getBlock().getMaterial() == state.getBlock().getMaterial());
      } else if (!(state2 instanceof BlockStateBase)) {
         return false;
      } else {
         BlockStateBase blockStateBase = (BlockStateBase)state2;
         Block block = blockStateBase.getBlock();
         int metadata = blockStateBase.getMetadata();
         return block == state.getBlock() && metadata == n2;
      }
   }

   public static BakedQuad[] getQuads(TextureAtlasSprite textureAtlasSprite, BakedQuad bakedQuad, RenderEnv renderEnv) {
      if (textureAtlasSprite == null) {
         return null;
      } else if (textureAtlasSprite == SPRITE_DEFAULT) {
         return renderEnv.getArrayQuadsCtm(bakedQuad);
      } else {
         return renderEnv.getArrayQuadsCtm(getQuad(textureAtlasSprite, bakedQuad));
      }
   }

   private static BlockDir[] getEdgeDirections(int n, int n2) {
      switch (n) {
         case 0:
            return EDGES_Y_NEG_DOWN;
         case 1:
            return EDGES_Y_POS_UP;
         case 2:
            if (n2 == 1) {
               return EDGES_Z_NEG_NORTH_Z_AXIS;
            }

            return EDGES_Z_NEG_NORTH;
         case 3:
            return EDGES_Z_POS_SOUTH;
         case 4:
            return EDGES_X_NEG_WEST;
         case 5:
            if (n2 == 2) {
               return EDGES_X_POS_EAST_X_AXIS;
            }

            return EDGES_X_POS_EAST;
         default:
            throw new IllegalArgumentException("Unknown side: " + n);
      }
   }

}
