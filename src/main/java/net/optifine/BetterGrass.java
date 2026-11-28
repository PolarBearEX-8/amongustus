package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.optifine.config.Config;
import net.optifine.model.BlockModelUtils;
import net.optifine.util.PropertiesOrdered;

public class BetterGrass {
   private static boolean betterGrass = true;
   private static boolean betterMycelium = true;
   private static boolean betterPodzol = true;
   private static boolean betterGrassSnow = true;
   private static boolean betterMyceliumSnow = true;
   private static boolean betterPodzolSnow = true;
   private static boolean grassMultilayer = false;
   private static TextureAtlasSprite spriteGrass = null;
   private static TextureAtlasSprite spriteGrassSide = null;
   private static TextureAtlasSprite spriteMycelium = null;
   private static TextureAtlasSprite spritePodzol = null;
   private static TextureAtlasSprite spriteSnow = null;
   private static boolean spritesLoaded = false;
   private static IBakedModel modelCubeGrass = null;
   private static IBakedModel modelCubeMycelium = null;
   private static IBakedModel modelCubePodzol = null;
   private static IBakedModel modelCubeSnow = null;
   private static boolean modelsLoaded = false;
   private static final String TEXTURE_GRASS_DEFAULT = null;
   private static final String TEXTURE_GRASS_SIDE_DEFAULT = null;
   private static final String TEXTURE_MYCELIUM_DEFAULT = null;
   private static final String TEXTURE_PODZOL_DEFAULT = null;
   private static final String TEXTURE_SNOW_DEFAULT = null;

   public static List getFaceQuads(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing, List list) {
      if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
         return list;
      } else if (!modelsLoaded) {
         return list;
      } else {
         Block block = state.getBlock();
         return block instanceof BlockMycelium
            ? getFaceQuadsMycelium(world, state, pos, facing, list)
            : (block instanceof BlockDirt ? getFaceQuadsDirt(world, state, pos, facing, list) : (block instanceof BlockGrass ? getFaceQuadsGrass(world, state, pos, facing, list) : list));
      }
   }

   private static List getFaceQuadsMycelium(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing, List list) {
      Block block = world.getBlockState(pos.up()).getBlock();
      boolean flag = block == Blocks.snow || block == Blocks.snow_layer;
      if (Config.isBetterGrassFancy()) {
         if (flag) {
            if (betterMyceliumSnow && getBlockAt(pos, facing, world) == Blocks.snow_layer) {
               return modelCubeSnow.getFaceQuads(facing);
            }
         } else if (betterMycelium && getBlockAt(pos.down(), facing, world) == Blocks.mycelium) {
            return modelCubeMycelium.getFaceQuads(facing);
         }
      } else if (flag) {
         if (betterMyceliumSnow) {
            return modelCubeSnow.getFaceQuads(facing);
         }
      } else if (betterMycelium) {
         return modelCubeMycelium.getFaceQuads(facing);
      }

      return list;
   }

   private static List getFaceQuadsDirt(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing, List list) {
      Block blockAt = getBlockAt(pos, EnumFacing.UP, world);
      if (state.getValue(BlockDirt.VARIANT) != BlockDirt.DirtType.PODZOL) {
         return list;
      } else {
         boolean flag = blockAt == Blocks.snow || blockAt == Blocks.snow_layer;
         if (Config.isBetterGrassFancy()) {
            if (flag) {
               if (betterPodzolSnow && getBlockAt(pos, facing, world) == Blocks.snow_layer) {
                  return modelCubeSnow.getFaceQuads(facing);
               }
            } else if (betterPodzol) {
               IBlockState blockState = world.getBlockState(pos.down().offset(facing));
               if (blockState.getBlock() == Blocks.dirt && blockState.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.PODZOL) {
                  return modelCubePodzol.getFaceQuads(facing);
               }
            }
         } else if (flag) {
            if (betterPodzolSnow) {
               return modelCubeSnow.getFaceQuads(facing);
            }
         } else if (betterPodzol) {
            return modelCubePodzol.getFaceQuads(facing);
         }

         return list;
      }
   }

   public static void updateIcons(TextureMap textureMap) {
      spritesLoaded = false;
      modelsLoaded = false;
      loadProperties(textureMap);
   }

   private static TextureAtlasSprite registerSprite(Properties properties, String str, String str2, TextureMap textureMap) {
      String property = properties.getProperty(str);
      if (property == null) {
         property = str2;
      }

      ResourceLocation location = new ResourceLocation("textures/" + property + ".png");
      if (!Config.hasResource(location)) {
         Config.warn("BetterGrass texture not found: " + location);
         property = str2;
      }

      return textureMap.registerSprite(new ResourceLocation(property));
   }

   private static List getFaceQuadsGrass(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing, List list) {
      Block block = world.getBlockState(pos.up()).getBlock();
      boolean flag = block == Blocks.snow || block == Blocks.snow_layer;
      if (Config.isBetterGrassFancy()) {
         if (flag) {
            if (betterGrassSnow && getBlockAt(pos, facing, world) == Blocks.snow_layer) {
               return modelCubeSnow.getFaceQuads(facing);
            }
         } else if (betterGrass && getBlockAt(pos.down(), facing, world) == Blocks.grass) {
            return modelCubeGrass.getFaceQuads(facing);
         }
      } else if (flag) {
         if (betterGrassSnow) {
            return modelCubeSnow.getFaceQuads(facing);
         }
      } else if (betterGrass) {
         return modelCubeGrass.getFaceQuads(facing);
      }

      return list;
   }

   private static Block getBlockAt(BlockPos pos, EnumFacing facing, IBlockAccess world) {
      return world.getBlockState(pos.offset(facing)).getBlock();
   }

   public static void update() {
      if (spritesLoaded) {
         modelCubeGrass = BlockModelUtils.makeModelCube(spriteGrass, 0);
         if (grassMultilayer) {
            modelCubeGrass = BlockModelUtils.joinModelsCube(BlockModelUtils.makeModelCube(spriteGrassSide, -1), modelCubeGrass);
         }

         modelCubeMycelium = BlockModelUtils.makeModelCube(spriteMycelium, -1);
         modelCubePodzol = BlockModelUtils.makeModelCube(spritePodzol, 0);
         modelCubeSnow = BlockModelUtils.makeModelCube(spriteSnow, -1);
         modelsLoaded = true;
      }
   }

   private static void loadProperties(TextureMap textureMap) {
      betterGrass = true;
      betterMycelium = true;
      betterPodzol = true;
      betterGrassSnow = true;
      betterMyceliumSnow = true;
      betterPodzolSnow = true;
      spriteGrass = textureMap.registerSprite(new ResourceLocation("blocks/grass_top"));
      spriteGrassSide = textureMap.registerSprite(new ResourceLocation("blocks/grass_side"));
      spriteMycelium = textureMap.registerSprite(new ResourceLocation("blocks/mycelium_top"));
      spritePodzol = textureMap.registerSprite(new ResourceLocation("blocks/dirt_podzol_top"));
      spriteSnow = textureMap.registerSprite(new ResourceLocation("blocks/snow"));
      spritesLoaded = true;
      String str = "optifine/bettergrass.properties";

      try {
         ResourceLocation location = new ResourceLocation(str);
         if (!Config.hasResource(location)) {
            return;
         }

         InputStream resourceStream = Config.getResourceStream(location);
         if (resourceStream == null) {
            return;
         }

         if (Config.isFromDefaultResourcePack(location)) {
            Config.dbg("BetterGrass: Parsing default configuration " + str);
         } else {
            Config.dbg("BetterGrass: Parsing configuration " + str);
         }

         PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
         propertiesOrdered.load(resourceStream);
         resourceStream.close();
         betterGrass = getBoolean(propertiesOrdered, "grass", true);
         betterMycelium = getBoolean(propertiesOrdered, "mycelium", true);
         betterPodzol = getBoolean(propertiesOrdered, "podzol", true);
         betterGrassSnow = getBoolean(propertiesOrdered, "grass.snow", true);
         betterMyceliumSnow = getBoolean(propertiesOrdered, "mycelium.snow", true);
         betterPodzolSnow = getBoolean(propertiesOrdered, "podzol.snow", true);
         grassMultilayer = getBoolean(propertiesOrdered, "grass.multilayer", false);
         spriteGrass = registerSprite(propertiesOrdered, "texture.grass", "blocks/grass_top", textureMap);
         spriteGrassSide = registerSprite(propertiesOrdered, "texture.grass_side", "blocks/grass_side", textureMap);
         spriteMycelium = registerSprite(propertiesOrdered, "texture.mycelium", "blocks/mycelium_top", textureMap);
         spritePodzol = registerSprite(propertiesOrdered, "texture.podzol", "blocks/dirt_podzol_top", textureMap);
         spriteSnow = registerSprite(propertiesOrdered, "texture.snow", "blocks/snow", textureMap);
      } catch (IOException e) {
         Config.warn("Error reading: " + str + ", " + e.getClass().getName() + ": " + e.getMessage());
      }
   }

   private static boolean getBoolean(Properties properties, String str, boolean flag) {
      String property = properties.getProperty(str);
      return property == null ? flag : Boolean.parseBoolean(property);
   }
}
