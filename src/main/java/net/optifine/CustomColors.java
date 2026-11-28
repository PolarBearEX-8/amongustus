package net.optifine;

import net.minecraft.world.ColorizerFoliage;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockStem;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.render.RenderEnv;
import net.optifine.util.EntityUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class CustomColors {
   private static String paletteFormatDefault = "vanilla";
   private static CustomColormap waterColors = null;
   private static CustomColormap foliagePineColors = null;
   private static CustomColormap foliageBirchColors = null;
   private static CustomColormap swampFoliageColors = null;
   private static CustomColormap swampGrassColors = null;
   private static CustomColormap[] colorsBlockColormaps = null;
   private static CustomColormap[][] blockColormaps = (CustomColormap[][])null;
   private static CustomColormap skyColors = null;
   private static CustomColorFader skyColorFader = new CustomColorFader();
   private static CustomColormap fogColors = null;
   private static CustomColorFader fogColorFader = new CustomColorFader();
   private static CustomColormap underwaterColors = null;
   private static CustomColorFader underwaterColorFader = new CustomColorFader();
   private static CustomColormap underlavaColors = null;
   private static CustomColorFader underlavaColorFader = new CustomColorFader();
   private static LightMapPack[] lightMapPacks = null;
   private static int lightmapMinDimensionId = 0;
   private static CustomColormap redstoneColors = null;
   private static CustomColormap xpOrbColors = null;
   private static int xpOrbTime = -1;
   private static CustomColormap durabilityColors = null;
   private static CustomColormap stemColors = null;
   private static CustomColormap stemMelonColors = null;
   private static CustomColormap stemPumpkinColors = null;
   private static CustomColormap myceliumParticleColors = null;
   private static boolean useDefaultGrassFoliageColors = true;
   private static int particleWaterColor = -1;
   private static int particlePortalColor = -1;
   private static int lilyPadColor = -1;
   private static int expBarTextColor = -1;
   private static int bossTextColor = -1;
   private static int signTextColor = -1;
   private static Vec3 fogColorNether = null;
   private static Vec3 fogColorEnd = null;
   private static Vec3 skyColorEnd = null;
   private static int[] spawnEggPrimaryColors = null;
   private static int[] spawnEggSecondaryColors = null;
   private static float[][] wolfCollarColors = (float[][])null;
   private static float[][] sheepColors = (float[][])null;
   private static int[] textColors = null;
   private static int[] mapColorsOriginal = null;
   private static int[] potionColors = null;
   private static final IBlockState BLOCK_STATE_DIRT = Blocks.dirt.getDefaultState();
   private static final IBlockState BLOCK_STATE_WATER = Blocks.water.getDefaultState();
   public static Random random = new Random();
   private static final IColorizer COLORIZER_GRASS = new CustomColors.DefaultColorizer();
   private static final IColorizer COLORIZER_FOLIAGE = new CustomColors.DefaultColorizer2();
   private static final IColorizer COLORIZER_FOLIAGE_PINE = new CustomColors.DefaultColorizer3();
   private static final IColorizer COLORIZER_FOLIAGE_BIRCH = new CustomColors.DefaultColorizer4();
   private static final IColorizer COLORIZER_WATER = new CustomColors.DefaultColorizer5();

   public static void update() {
      paletteFormatDefault = "vanilla";
      waterColors = null;
      foliageBirchColors = null;
      foliagePineColors = null;
      swampGrassColors = null;
      swampFoliageColors = null;
      skyColors = null;
      fogColors = null;
      underwaterColors = null;
      underlavaColors = null;
      redstoneColors = null;
      xpOrbColors = null;
      xpOrbTime = -1;
      durabilityColors = null;
      stemColors = null;
      myceliumParticleColors = null;
      lightMapPacks = null;
      particleWaterColor = -1;
      particlePortalColor = -1;
      lilyPadColor = -1;
      expBarTextColor = -1;
      bossTextColor = -1;
      signTextColor = -1;
      fogColorNether = null;
      fogColorEnd = null;
      skyColorEnd = null;
      colorsBlockColormaps = null;
      blockColormaps = (CustomColormap[][])null;
      useDefaultGrassFoliageColors = true;
      spawnEggPrimaryColors = null;
      spawnEggSecondaryColors = null;
      wolfCollarColors = (float[][])null;
      sheepColors = (float[][])null;
      textColors = null;
      setMapColors(mapColorsOriginal);
      potionColors = null;
      paletteFormatDefault = readProperty("mcpatcher/color.properties", "palette.format", CustomColormap.FORMAT_STRINGS, "vanilla");
      String str = "mcpatcher/colormap/";
      waterColors = getCustomColors(str, new String[]{"water.png", "watercolorX.png"}, 256, 256);
      updateUseDefaultGrassFoliageColors();
      if (Config.isCustomColors()) {
         foliagePineColors = getCustomColors(str, new String[]{"pine.png", "pinecolor.png"}, 256, 256);
         foliageBirchColors = getCustomColors(str, new String[]{"birch.png", "birchcolor.png"}, 256, 256);
         swampGrassColors = getCustomColors(str, new String[]{"swampgrass.png", "swampgrasscolor.png"}, 256, 256);
         swampFoliageColors = getCustomColors(str, new String[]{"swampfoliage.png", "swampfoliagecolor.png"}, 256, 256);
         skyColors = getCustomColors(str, new String[]{"sky0.png", "skycolor0.png"}, 256, 256);
         fogColors = getCustomColors(str, new String[]{"fog0.png", "fogcolor0.png"}, 256, 256);
         underwaterColors = getCustomColors(str, new String[]{"underwater.png", "underwatercolor.png"}, 256, 256);
         underlavaColors = getCustomColors(str, new String[]{"underlava.png", "underlavacolor.png"}, 256, 256);
         redstoneColors = getCustomColors(str, new String[]{"redstone.png", "redstonecolor.png"}, 16, 1);
         xpOrbColors = getCustomColors(str + "xporb.png", -1, -1);
         durabilityColors = getCustomColors(str + "durability.png", -1, -1);
         stemColors = getCustomColors(str, new String[]{"stem.png", "stemcolor.png"}, 8, 1);
         stemPumpkinColors = getCustomColors(str + "pumpkinstem.png", 8, 1);
         stemMelonColors = getCustomColors(str + "melonstem.png", 8, 1);
         myceliumParticleColors = getCustomColors(str, new String[]{"myceliumparticle.png", "myceliumparticlecolor.png"}, -1, -1);
         Pair pair = parseLightMapPacks();
         lightMapPacks = (LightMapPack[])pair.getLeft();
         lightmapMinDimensionId = (Integer)pair.getRight();
         readColorProperties("mcpatcher/color.properties");
         blockColormaps = readBlockColormaps(new String[]{str + "custom/", str + "blocks/"}, colorsBlockColormaps, 256, 256);
         updateUseDefaultGrassFoliageColors();
      }
   }

   private static String readProperty(String str, String str2, String[] strings, String str3) {
      try {
         InputStream resourceStream = Config.getResourceStream(new ResourceLocation(str));
         if (resourceStream == null) {
            return str3;
         } else {
            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            propertiesOrdered.load(resourceStream);
            resourceStream.close();
            String property = propertiesOrdered.getProperty(str2);
            if (property == null) {
               return str3;
            } else {
               List list = Arrays.asList(strings);
               if (!list.contains(property)) {
                  warn("Invalid value: " + str2 + "=" + property);
                  warn("Expected values: " + Config.arrayToString3(strings));
                  return str3;
               } else {
                  dbg("" + str2 + "=" + property);
                  return property;
               }
            }
         }
      } catch (FileNotFoundException e) {
         return str3;
      } catch (IOException e2) {
         e2.printStackTrace();
         return str3;
      }
   }

   private static Pair parseLightMapPacks() {
      String str = "mcpatcher/lightmap/world";
      String str2 = ".png";
      String[] strings = ResUtils.collectFiles2(str, str2);
      HashMap map = new HashMap();

      for (int i = 0; i < strings.length; i++) {
         String str3 = strings[i];
         String str4 = StrUtils.removePrefixSuffix(str3, str, str2);
         int n = Config.parseInt(str4, Integer.MIN_VALUE);
         if (n == Integer.MIN_VALUE) {
            warn("Invalid dimension ID: " + str4 + ", path: " + str3);
         } else {
            map.put(n, str3);
         }
      }

      Set keys = map.keySet();
      Integer[] nums = (Integer[])keys.toArray(new Integer[keys.size()]);
      Arrays.sort(nums);
      if (nums.length <= 0) {
         return new ImmutablePair(null, 0);
      } else {
         int n2 = nums[0];
         int n3 = nums[nums.length - 1];
         CustomColormap[] customColormaps = new CustomColormap[n3 - n2 + 1];

         for (int j = 0; j < nums.length; j++) {
            Integer num = nums[j];
            String str5 = (String)map.get(num);
            CustomColormap customColors = getCustomColors(str5, -1, -1);
            if (customColors != null) {
               if (customColors.getWidth() < 16) {
                  warn("Invalid lightmap width: " + customColors.getWidth() + ", path: " + str5);
               } else {
                  customColormaps[num - n2] = customColors;
               }
            }
         }

         LightMapPack[] lightMapPacks2 = new LightMapPack[customColormaps.length];

         for (int k = 0; k < customColormaps.length; k++) {
            CustomColormap customColormap = customColormaps[k];
            if (customColormap != null) {
               String name = customColormap.name;
               String basePath = customColormap.basePath;
               CustomColormap customColors2 = getCustomColors(basePath + "/" + name + "_rain.png", -1, -1);
               CustomColormap customColors3 = getCustomColors(basePath + "/" + name + "_thunder.png", -1, -1);
               LightMap lightMap = new LightMap(customColormap);
               LightMap lightMap2 = customColors2 != null ? new LightMap(customColors2) : null;
               LightMap lightMap3 = customColors3 != null ? new LightMap(customColors3) : null;
               lightMapPacks2[k] = new LightMapPack(lightMap, lightMap2, lightMap3);
            }
         }

         return new ImmutablePair(lightMapPacks2, n2);
      }
   }

   private static int readTextureHeight(String str, int n) {
      try {
         InputStream resourceStream = Config.getResourceStream(new ResourceLocation(str));
         if (resourceStream == null) {
            return n;
         } else {
            BufferedImage image = ImageIO.read(resourceStream);
            resourceStream.close();
            return image == null ? n : image.getHeight();
         }
      } catch (IOException e) {
         return n;
      }
   }

   private static void readColorProperties(String str) {
      try {
         InputStream resourceStream = Config.getResourceStream(new ResourceLocation(str));
         if (resourceStream == null) {
            return;
         }

         dbg("Loading " + str);
         PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
         propertiesOrdered.load(resourceStream);
         resourceStream.close();
         particleWaterColor = readColor2(propertiesOrdered, new String[]{"particle.water", "drop.water"});
         particlePortalColor = readColor(propertiesOrdered, "particle.portal");
         lilyPadColor = readColor(propertiesOrdered, "lilypad");
         expBarTextColor = readColor(propertiesOrdered, "text.xpbar");
         bossTextColor = readColor(propertiesOrdered, "text.boss");
         signTextColor = readColor(propertiesOrdered, "text.sign");
         fogColorNether = readColorVec3(propertiesOrdered, "fog.nether");
         fogColorEnd = readColorVec3(propertiesOrdered, "fog.end");
         skyColorEnd = readColorVec3(propertiesOrdered, "sky.end");
         colorsBlockColormaps = readCustomColormaps(propertiesOrdered, str);
         spawnEggPrimaryColors = readSpawnEggColors(propertiesOrdered, str, "egg.shell.", "Spawn egg shell");
         spawnEggSecondaryColors = readSpawnEggColors(propertiesOrdered, str, "egg.spots.", "Spawn egg spot");
         wolfCollarColors = readDyeColors(propertiesOrdered, str, "collar.", "Wolf collar");
         sheepColors = readDyeColors(propertiesOrdered, str, "sheep.", "Sheep");
         textColors = readTextColors(propertiesOrdered, str, "text.code.", "Text");
         int[] ints = readMapColors(propertiesOrdered, str, "map.", "Map");
         if (ints != null) {
            if (mapColorsOriginal == null) {
               mapColorsOriginal = getMapColors();
            }

            setMapColors(ints);
         }

         potionColors = readPotionColors(propertiesOrdered, str, "potion.", "Potion");
         xpOrbTime = Config.parseInt(propertiesOrdered.getProperty("xporb.time"), -1);
      } catch (FileNotFoundException e) {
         return;
      } catch (IOException e2) {
         e2.printStackTrace();
      }
   }

   private static CustomColormap[] readCustomColormaps(Properties properties, String str) {
      ArrayList list = new ArrayList();
      String str2 = "palette.block.";
      HashMap map = new HashMap();

      for (Object obj : properties.keySet()) {
         String str3 = (String)obj;
         String property = properties.getProperty(str3);
         if (str3.startsWith(str2)) {
            map.put(str3, property);
         }
      }

      String[] strings = (String[])map.keySet().toArray(new String[map.size()]);

      for (int i = 0; i < strings.length; i++) {
         String str4 = strings[i];
         String property2 = properties.getProperty(str4);
         dbg("Block palette: " + str4 + " = " + property2);
         String str5 = str4.substring(str2.length());
         str5 = TextureUtils.fixResourcePath(str5, TextureUtils.getBasePath(str));
         CustomColormap customColors = getCustomColors(str5, 256, 256);
         if (customColors == null) {
            warn("Colormap not found: " + str5);
         } else {
            MatchBlock[] matchBlocks = new ConnectedParser("CustomColors").parseMatchBlocks(property2);
            if (matchBlocks != null && matchBlocks.length > 0) {
               for (int j = 0; j < matchBlocks.length; j++) {
                  customColors.addMatchBlock(matchBlocks[j]);
               }

               list.add(customColors);
            } else {
               warn("Invalid match blocks: " + property2);
            }
         }
      }

      return list.size() <= 0 ? null : (CustomColormap[])list.toArray(new CustomColormap[list.size()]);
   }

   private static CustomColormap[][] readBlockColormaps(String[] strings, CustomColormap[] customColormaps, int n, int n2) {
      String[] strings2 = ResUtils.collectFiles(strings, new String[]{".properties"});
      Arrays.sort(strings2);
      ArrayList list = new ArrayList();

      for (int i = 0; i < strings2.length; i++) {
         String str = strings2[i];
         dbg("Block colormap: " + str);

         try {
            InputStream resourceStream = Config.getResourceStream(new ResourceLocation("minecraft", str));
            if (resourceStream == null) {
               warn("File not found: " + str);
            } else {
               PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
               propertiesOrdered.load(resourceStream);
               resourceStream.close();
               CustomColormap customColormap = new CustomColormap(propertiesOrdered, str, n, n2, paletteFormatDefault);
               if (customColormap.isValid(str) && customColormap.isValidMatchBlocks(str)) {
                  addToBlockList(customColormap, list);
               }
            }
         } catch (FileNotFoundException e) {
            warn("File not found: " + str);
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }

      if (customColormaps != null) {
         for (int j = 0; j < customColormaps.length; j++) {
            addToBlockList(customColormaps[j], list);
         }
      }

      return list.size() <= 0 ? (CustomColormap[][])null : blockListToArray(list);
   }

   private static void addToBlockList(CustomColormap customColormap, List list) {
      int[] matchBlockIds = customColormap.getMatchBlockIds();
      if (matchBlockIds != null && matchBlockIds.length > 0) {
         for (int i = 0; i < matchBlockIds.length; i++) {
            int n = matchBlockIds[i];
            if (n < 0) {
               warn("Invalid block ID: " + n);
            } else {
               addToList(customColormap, list, n);
            }
         }
      } else {
         warn("No match blocks: " + Config.arrayToString(matchBlockIds));
      }
   }

   private static void addToList(CustomColormap customColormap, List list, int n) {
      while (n >= list.size()) {
         list.add(null);
      }

      List list2 = (List)list.get(n);
      if (list2 == null) {
         list2 = new ArrayList();
         list.set(n, list2);
      }

      list2.add(customColormap);
   }

   private static CustomColormap[][] blockListToArray(List list) {
      CustomColormap[][] customColormapsArr = new CustomColormap[list.size()][];

      for (int i = 0; i < list.size(); i++) {
         List list2 = (List)list.get(i);
         if (list2 != null) {
            customColormapsArr[i] = (CustomColormap[])list2.toArray(new CustomColormap[list2.size()]);
         }
      }

      return customColormapsArr;
   }

   private static int readColor2(Properties properties, String[] strings) {
      for (int i = 0; i < strings.length; i++) {
         int n = readColor(properties, strings[i]);
         if (n >= 0) {
            return n;
         }
      }

      return -1;
   }

   private static int readColor(Properties properties, String str) {
      String property = properties.getProperty(str);
      if (property == null) {
         return -1;
      } else {
         property = property.trim();
         int n = parseColor(property);
         if (n < 0) {
            warn("Invalid color: " + str + " = " + property);
            return n;
         } else {
            dbg(str + " = " + property);
            return n;
         }
      }
   }

   private static int parseColor(String str) {
      if (str == null) {
         return -1;
      } else {
         str = str.trim();

         try {
            return Integer.parseInt(str, 16) & 16777215;
         } catch (NumberFormatException e) {
            return -1;
         }
      }
   }

   private static Vec3 readColorVec3(Properties properties, String str) {
      int n = readColor(properties, str);
      if (n < 0) {
         return null;
      } else {
         int n2 = n >> 16 & 0xFF;
         int n3 = n >> 8 & 0xFF;
         int n4 = n & 0xFF;
         float f = (float)n2 / 255.0F;
         float f2 = (float)n3 / 255.0F;
         float f3 = (float)n4 / 255.0F;
         return new Vec3((double)f, (double)f2, (double)f3);
      }
   }

   private static CustomColormap getCustomColors(String str, String[] strings, int n, int n2) {
      for (int i = 0; i < strings.length; i++) {
         String str2 = strings[i];
         str2 = str + str2;
         CustomColormap customColors = getCustomColors(str2, n, n2);
         if (customColors != null) {
            return customColors;
         }
      }

      return null;
   }

   public static CustomColormap getCustomColors(String str, int n, int n2) {
      try {
         if (!Config.hasResource(new ResourceLocation(str))) {
            return null;
         } else {
            dbg("Colormap " + str);
            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            String str2 = StrUtils.replaceSuffix(str, ".png", ".properties");
            ResourceLocation location2 = new ResourceLocation(str2);
            if (Config.hasResource(location2)) {
               InputStream resourceStream = Config.getResourceStream(location2);
               propertiesOrdered.load(resourceStream);
               resourceStream.close();
               dbg("Colormap properties: " + str2);
            } else {
               propertiesOrdered.put("format", paletteFormatDefault);
               propertiesOrdered.put("source", str);
               str2 = str;
            }

            CustomColormap customColormap = new CustomColormap(propertiesOrdered, str2, n, n2, paletteFormatDefault);
            return !customColormap.isValid(str2) ? null : customColormap;
         }
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   public static void updateUseDefaultGrassFoliageColors() {
      useDefaultGrassFoliageColors = foliageBirchColors == null && foliagePineColors == null && swampGrassColors == null && swampFoliageColors == null && Config.isSwampColors() && Config.isSmoothBiomes();
   }

   public static int getColorMultiplier(BakedQuad bakedQuad, IBlockState state, IBlockAccess world, BlockPos pos, RenderEnv renderEnv) {
      Block block = state.getBlock();
      IBlockState blockState = renderEnv.getBlockState();
      if (blockColormaps != null) {
         if (!bakedQuad.hasTintIndex()) {
            if (block == Blocks.grass) {
               blockState = BLOCK_STATE_DIRT;
            }

            if (block == Blocks.redstone_wire) {
               return -1;
            }
         }

         if (block == Blocks.double_plant && renderEnv.getMetadata() >= 8) {
            pos = pos.down();
            blockState = world.getBlockState(pos);
         }

         CustomColormap blockColormap = getBlockColormap(blockState);
         if (blockColormap != null) {
            if (Config.isSmoothBiomes() && !blockColormap.isColorConstant()) {
               return getSmoothColorMultiplier(state, world, pos, blockColormap, renderEnv.getColorizerBlockPosM());
            }

            return blockColormap.getColor2(world, pos);
         }
      }

      if (!bakedQuad.hasTintIndex()) {
         return -1;
      } else if (block == Blocks.waterlily) {
         return getLilypadColorMultiplier(world, pos);
      } else if (block == Blocks.redstone_wire) {
         return getRedstoneColor(renderEnv.getBlockState());
      } else if (block instanceof BlockStem) {
         return getStemColorMultiplier(block, world, pos, renderEnv);
      } else if (useDefaultGrassFoliageColors) {
         return -1;
      } else {
         int metadata = renderEnv.getMetadata();
         IColorizer colorizer;
         if (block == Blocks.grass || block == Blocks.tallgrass || block == Blocks.double_plant) {
            colorizer = COLORIZER_GRASS;
         } else if (block == Blocks.double_plant) {
            colorizer = COLORIZER_GRASS;
            if (metadata >= 8) {
               pos = pos.down();
            }
         } else if (block == Blocks.leaves) {
            switch (metadata & 3) {
               case 0:
                  colorizer = COLORIZER_FOLIAGE;
                  break;
               case 1:
                  colorizer = COLORIZER_FOLIAGE_PINE;
                  break;
               case 2:
                  colorizer = COLORIZER_FOLIAGE_BIRCH;
                  break;
               default:
                  colorizer = COLORIZER_FOLIAGE;
            }
         } else if (block == Blocks.leaves2) {
            colorizer = COLORIZER_FOLIAGE;
         } else {
            if (block != Blocks.vine) {
               return -1;
            }

            colorizer = COLORIZER_FOLIAGE;
         }

         return Config.isSmoothBiomes() && !colorizer.isColorConstant() ? getSmoothColorMultiplier(state, world, pos, colorizer, renderEnv.getColorizerBlockPosM()) : colorizer.getColor(blockState, world, pos);
      }
   }

   public static BiomeGenBase getColorBiome(IBlockAccess world, BlockPos pos) {
      BiomeGenBase biomeGenForCoords = world.getBiomeGenForCoords(pos);
      if (biomeGenForCoords == BiomeGenBase.swampland && !Config.isSwampColors()) {
         biomeGenForCoords = BiomeGenBase.plains;
      }

      return biomeGenForCoords;
   }

   private static CustomColormap getBlockColormap(IBlockState state) {
      if (blockColormaps == null) {
         return null;
      } else if (!(state instanceof BlockStateBase)) {
         return null;
      } else {
         BlockStateBase blockStateBase = (BlockStateBase)state;
         int blockId = blockStateBase.getBlockId();
         if (blockId >= 0 && blockId < blockColormaps.length) {
            CustomColormap[] customColormaps = blockColormaps[blockId];
            if (customColormaps == null) {
               return null;
            } else {
               for (int i = 0; i < customColormaps.length; i++) {
                  CustomColormap customColormap = customColormaps[i];
                  if (customColormap.matchesBlock(blockStateBase)) {
                     return customColormap;
                  }
               }

               return null;
            }
         } else {
            return null;
         }
      }
   }

   private static int getSmoothColorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, IColorizer colorizer, BlockPosM blockPosM) {
      int n = 0;
      int n2 = 0;
      int n3 = 0;
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();
      BlockPosM blockPosM2 = blockPosM;

      for (int i = x - 1; i <= x + 1; i++) {
         for (int j = z - 1; j <= z + 1; j++) {
            blockPosM2.setXyz(i, y, j);
            int color = colorizer.getColor(state, world, blockPosM2);
            n += color >> 16 & 0xFF;
            n2 += color >> 8 & 0xFF;
            n3 += color & 0xFF;
         }
      }

      return (n / 9) << 16 | (n2 / 9) << 8 | (n3 / 9);
   }

   public static int getFluidColor(IBlockAccess world, IBlockState state, BlockPos pos, RenderEnv renderEnv) {
      Block block = state.getBlock();
      Object blockColormap = getBlockColormap(state);
      if (blockColormap == null && state.getBlock().getMaterial() == Material.water) {
         blockColormap = COLORIZER_WATER;
      }

      return blockColormap == null
         ? block.colorMultiplier(world, pos, 0)
         : (
            Config.isSmoothBiomes() && !((IColorizer)blockColormap).isColorConstant()
               ? getSmoothColorMultiplier(state, world, pos, (IColorizer)blockColormap, renderEnv.getColorizerBlockPosM())
               : ((IColorizer)blockColormap).getColor(state, world, pos)
         );
   }

   public static void updatePortalFX(EntityFX entityFX) {
      if (particlePortalColor >= 0) {
         int n = particlePortalColor;
         int n2 = n >> 16 & 0xFF;
         int n3 = n >> 8 & 0xFF;
         int n4 = n & 0xFF;
         entityFX.setRBGColorF((float)n2 / 255.0F, (float)n3 / 255.0F, (float)n4 / 255.0F);
      }
   }

   public static void updateMyceliumFX(EntityFX entityFX) {
      if (myceliumParticleColors != null) {
         int colorRandom = myceliumParticleColors.getColorRandom();
         int n = colorRandom >> 16 & 0xFF;
         int n2 = colorRandom >> 8 & 0xFF;
         int n3 = colorRandom & 0xFF;
         entityFX.setRBGColorF((float)n / 255.0F, (float)n2 / 255.0F, (float)n3 / 255.0F);
      }
   }

   private static int getRedstoneColor(IBlockState state) {
      if (redstoneColors == null) {
         return -1;
      } else {
         int redstoneLevel = getRedstoneLevel(state, 15);
         return redstoneColors.getColor(redstoneLevel);
      }
   }

   public static void updateReddustFX(EntityFX entityFX, IBlockAccess world, double d, double d2, double d3) {
      if (redstoneColors != null) {
         int redstoneLevel = getRedstoneLevel(world.getBlockState(new BlockPos(d, d2, d3)), 15);
         int color = redstoneColors.getColor(redstoneLevel);
         int n = color >> 16 & 0xFF;
         int n2 = color >> 8 & 0xFF;
         int n3 = color & 0xFF;
         entityFX.setRBGColorF((float)n / 255.0F, (float)n2 / 255.0F, (float)n3 / 255.0F);
      }
   }

   private static int getRedstoneLevel(IBlockState state, int n) {
      if (!(state.getBlock() instanceof BlockRedstoneWire)) {
         return n;
      } else {
         Comparable value = state.getValue(BlockRedstoneWire.POWER);
         if (!(value instanceof Integer)) {
            return n;
         } else {
            return (Integer)value;
         }
      }
   }

   public static float getXpOrbTimer(float f) {
      if (xpOrbTime <= 0) {
         return f;
      } else {
         return f * (628.0F / (float)xpOrbTime);
      }
   }

   public static int getXpOrbColor(float f) {
      if (xpOrbColors == null) {
         return -1;
      } else {
         int n = (int)Math.round((double)((MathHelper.sin(f) + 1.0F) * (float)(xpOrbColors.getLength() - 1)) / 2.0);
         return xpOrbColors.getColor(n);
      }
   }

   public static int getDurabilityColor(int n) {
      if (durabilityColors == null) {
         return -1;
      } else {
         int n2 = n * durabilityColors.getLength() / 255;
         return durabilityColors.getColor(n2);
      }
   }

   public static void updateWaterFX(EntityFX entityFX, IBlockAccess world, double d, double d2, double d3, RenderEnv renderEnv) {
      if (waterColors != null || blockColormaps != null || particleWaterColor >= 0) {
         BlockPos pos = new BlockPos(d, d2, d3);
         renderEnv.reset(BLOCK_STATE_WATER, pos);
         int fluidColor = getFluidColor(world, BLOCK_STATE_WATER, pos, renderEnv);
         int n = fluidColor >> 16 & 0xFF;
         int n2 = fluidColor >> 8 & 0xFF;
         int n3 = fluidColor & 0xFF;
         float f = (float)n / 255.0F;
         float f2 = (float)n2 / 255.0F;
         float f3 = (float)n3 / 255.0F;
         if (particleWaterColor >= 0) {
            int n4 = particleWaterColor >> 16 & 0xFF;
            int n5 = particleWaterColor >> 8 & 0xFF;
            int n6 = particleWaterColor & 0xFF;
            f *= (float)n4 / 255.0F;
            f2 *= (float)n5 / 255.0F;
            f3 *= (float)n6 / 255.0F;
         }

         entityFX.setRBGColorF(f, f2, f3);
      }
   }

   private static int getLilypadColorMultiplier(IBlockAccess world, BlockPos pos) {
      return lilyPadColor < 0 ? Blocks.waterlily.colorMultiplier(world, pos) : lilyPadColor;
   }

   private static Vec3 getFogColorNether(Vec3 vec) {
      return fogColorNether == null ? vec : fogColorNether;
   }

   private static Vec3 getFogColorEnd(Vec3 vec) {
      return fogColorEnd == null ? vec : fogColorEnd;
   }

   private static Vec3 getSkyColorEnd(Vec3 vec) {
      return skyColorEnd == null ? vec : skyColorEnd;
   }

   public static Vec3 getSkyColor(Vec3 vec, IBlockAccess world, double d, double d2, double d3) {
      if (skyColors == null) {
         return vec;
      } else {
         int colorSmooth = skyColors.getColorSmooth(world, d, d2, d3, 3);
         int n = colorSmooth >> 16 & 0xFF;
         int n2 = colorSmooth >> 8 & 0xFF;
         int n3 = colorSmooth & 0xFF;
         float f = (float)n / 255.0F;
         float f2 = (float)n2 / 255.0F;
         float f3 = (float)n3 / 255.0F;
         float f4 = (float)vec.xCoord / 0.5F;
         float f5 = (float)vec.yCoord / 0.66275F;
         float zCoord = (float)vec.zCoord;
         f *= f4;
         f2 *= f5;
         f3 *= zCoord;
         return skyColorFader.getColor((double)f, (double)f2, (double)f3);
      }
   }

   private static Vec3 getFogColor(Vec3 vec, IBlockAccess world, double d, double d2, double d3) {
      if (fogColors == null) {
         return vec;
      } else {
         int colorSmooth = fogColors.getColorSmooth(world, d, d2, d3, 3);
         int n = colorSmooth >> 16 & 0xFF;
         int n2 = colorSmooth >> 8 & 0xFF;
         int n3 = colorSmooth & 0xFF;
         float f = (float)n / 255.0F;
         float f2 = (float)n2 / 255.0F;
         float f3 = (float)n3 / 255.0F;
         float f4 = (float)vec.xCoord / 0.753F;
         float f5 = (float)vec.yCoord / 0.8471F;
         float zCoord = (float)vec.zCoord;
         f *= f4;
         f2 *= f5;
         f3 *= zCoord;
         return fogColorFader.getColor((double)f, (double)f2, (double)f3);
      }
   }

   public static Vec3 getUnderwaterColor(IBlockAccess world, double d, double d2, double d3) {
      return getUnderFluidColor(world, d, d2, d3, underwaterColors, underwaterColorFader);
   }

   public static Vec3 getUnderlavaColor(IBlockAccess world, double d, double d2, double d3) {
      return getUnderFluidColor(world, d, d2, d3, underlavaColors, underlavaColorFader);
   }

   public static Vec3 getUnderFluidColor(IBlockAccess world, double d, double d2, double d3, CustomColormap customColormap, CustomColorFader customColorFader) {
      if (customColormap == null) {
         return null;
      } else {
         int colorSmooth = customColormap.getColorSmooth(world, d, d2, d3, 3);
         int n = colorSmooth >> 16 & 0xFF;
         int n2 = colorSmooth >> 8 & 0xFF;
         int n3 = colorSmooth & 0xFF;
         return customColorFader.getColor((double)((float)n / 255.0F), (double)((float)n2 / 255.0F), (double)((float)n3 / 255.0F));
      }
   }

   private static int getStemColorMultiplier(Block block, IBlockAccess world, BlockPos pos, RenderEnv renderEnv) {
      CustomColormap customColormap = stemColors;
      if (block == Blocks.pumpkin_stem && stemPumpkinColors != null) {
         customColormap = stemPumpkinColors;
      }

      if (block == Blocks.melon_stem && stemMelonColors != null) {
         customColormap = stemMelonColors;
      }

      if (customColormap == null) {
         return -1;
      } else {
         return customColormap.getColor(renderEnv.getMetadata());
      }
   }

   public static boolean updateLightmap(World world, float f, int[] ints, boolean flag, float f2) {
      if (world == null) {
         return false;
      } else if (lightMapPacks == null) {
         return false;
      } else {
         int n = world.provider.getDimensionId() - lightmapMinDimensionId;
         if (n >= 0 && n < lightMapPacks.length) {
            LightMapPack lightMapPack = lightMapPacks[n];
            return lightMapPack == null ? false : lightMapPack.updateLightmap(world, f, ints, flag, f2);
         } else {
            return false;
         }
      }
   }

   public static Vec3 getWorldFogColor(Vec3 vec, World world, Entity entity, float f) {
      switch (world.provider.getDimensionId()) {
         case -1:
            vec = getFogColorNether(vec);
            break;
         case 0:
            vec = getFogColor(vec, Minecraft.getMinecraft().theWorld, entity.posX, entity.posY + 1.0, entity.posZ);
            break;
         case 1:
            vec = getFogColorEnd(vec);
      }

      return vec;
   }

   public static Vec3 getWorldSkyColor(Vec3 vec, World world, Entity entity, float f) {
      switch (world.provider.getDimensionId()) {
         case 0:
            vec = getSkyColor(vec, Minecraft.getMinecraft().theWorld, entity.posX, entity.posY + 1.0, entity.posZ);
            break;
         case 1:
            vec = getSkyColorEnd(vec);
      }

      return vec;
   }

   private static int[] readSpawnEggColors(Properties properties, String str, String str2, String str3) {
      ArrayList list = new ArrayList();
      Set keys = properties.keySet();
      int n = 0;

      for (Object obj : keys) {
         String str4 = (String)obj;
         String property = properties.getProperty(str4);
         if (str4.startsWith(str2)) {
            int entityIdByName = EntityUtils.getEntityIdByName(StrUtils.removePrefix(str4, str2));
            if (entityIdByName < 0) {
               warn("Invalid spawn egg name: " + str4);
            } else {
               int n2 = parseColor(property);
               if (n2 < 0) {
                  warn("Invalid spawn egg color: " + str4 + " = " + property);
               } else {
                  while (list.size() <= entityIdByName) {
                     list.add(-1);
                  }

                  list.set(entityIdByName, n2);
                  n++;
               }
            }
         }
      }

      if (n <= 0) {
         return null;
      } else {
         dbg(str3 + " colors: " + n);
         int[] ints = new int[list.size()];

         for (int i = 0; i < ints.length; i++) {
            ints[i] = (Integer)list.get(i);
         }

         return ints;
      }
   }

   private static int getSpawnEggColor(ItemMonsterPlacer itemMonsterPlacer, ItemStack stack, int n, int n2) {
      int metadata = stack.getMetadata();
      int[] ints = n == 0 ? spawnEggPrimaryColors : spawnEggSecondaryColors;
      if (ints == null) {
         return n2;
      } else if (metadata >= 0 && metadata < ints.length) {
         int n3 = ints[metadata];
         return n3 < 0 ? n2 : n3;
      } else {
         return n2;
      }
   }

   public static int getColorFromItemStack(ItemStack stack, int n, int n2) {
      if (stack == null) {
         return n2;
      } else {
         Item item = stack.getItem();
         return item == null ? n2 : (item instanceof ItemMonsterPlacer ? getSpawnEggColor((ItemMonsterPlacer)item, stack, n, n2) : n2);
      }
   }

   private static float[][] readDyeColors(Properties properties, String str, String str2, String str3) {
      EnumDyeColor[] values = EnumDyeColor.values();
      HashMap map = new HashMap();

      for (int i = 0; i < values.length; i++) {
         EnumDyeColor enumDyeColor = values[i];
         map.put(enumDyeColor.getName(), enumDyeColor);
      }

      float[][] floatsArr = new float[values.length][];
      int n = 0;

      for (Object obj : properties.keySet()) {
         String str4 = (String)obj;
         String property = properties.getProperty(str4);
         if (str4.startsWith(str2)) {
            String str5 = StrUtils.removePrefix(str4, str2);
            if (str5.equals("lightBlue")) {
               str5 = "light_blue";
            }

            EnumDyeColor enumDyeColor2 = (EnumDyeColor)map.get(str5);
            int n2 = parseColor(property);
            if (enumDyeColor2 != null && n2 >= 0) {
               float[] floats = new float[]{(float)(n2 >> 16 & 0xFF) / 255.0F, (float)(n2 >> 8 & 0xFF) / 255.0F, (float)(n2 & 0xFF) / 255.0F};
               floatsArr[enumDyeColor2.ordinal()] = floats;
               n++;
            } else {
               warn("Invalid color: " + str4 + " = " + property);
            }
         }
      }

      if (n <= 0) {
         return (float[][])null;
      } else {
         dbg(str3 + " colors: " + n);
         return floatsArr;
      }
   }

   private static float[] getDyeColors(EnumDyeColor enumDyeColor, float[][] floatsArr, float[] floats) {
      if (floatsArr == null) {
         return floats;
      } else if (enumDyeColor == null) {
         return floats;
      } else {
         float[] floats2 = floatsArr[enumDyeColor.ordinal()];
         return floats2 == null ? floats : floats2;
      }
   }

   public static float[] getWolfCollarColors(EnumDyeColor enumDyeColor, float[] floats) {
      return getDyeColors(enumDyeColor, wolfCollarColors, floats);
   }

   public static float[] getSheepColors(EnumDyeColor enumDyeColor, float[] floats) {
      return getDyeColors(enumDyeColor, sheepColors, floats);
   }

   private static int[] readTextColors(Properties properties, String str, String str2, String str3) {
      int[] ints = new int[32];
      Arrays.fill(ints, -1);
      int n = 0;

      for (Object obj : properties.keySet()) {
         String str4 = (String)obj;
         String property = properties.getProperty(str4);
         if (str4.startsWith(str2)) {
            int n2 = Config.parseInt(StrUtils.removePrefix(str4, str2), -1);
            int n3 = parseColor(property);
            if (n2 >= 0 && n2 < ints.length && n3 >= 0) {
               ints[n2] = n3;
               n++;
            } else {
               warn("Invalid color: " + str4 + " = " + property);
            }
         }
      }

      if (n <= 0) {
         return null;
      } else {
         dbg(str3 + " colors: " + n);
         return ints;
      }
   }

   public static int getTextColor(int n, int n2) {
      if (textColors == null) {
         return n2;
      } else if (n >= 0 && n < textColors.length) {
         int n3 = textColors[n];
         return n3 < 0 ? n2 : n3;
      } else {
         return n2;
      }
   }

   private static int[] readMapColors(Properties properties, String str, String str2, String str3) {
      int[] ints = new int[MapColor.mapColorArray.length];
      Arrays.fill(ints, -1);
      int n = 0;

      for (Object obj : properties.keySet()) {
         String str4 = (String)obj;
         String property = properties.getProperty(str4);
         if (str4.startsWith(str2)) {
            int mapColorIndex = getMapColorIndex(StrUtils.removePrefix(str4, str2));
            int n2 = parseColor(property);
            if (mapColorIndex >= 0 && mapColorIndex < ints.length && n2 >= 0) {
               ints[mapColorIndex] = n2;
               n++;
            } else {
               warn("Invalid color: " + str4 + " = " + property);
            }
         }
      }

      if (n <= 0) {
         return null;
      } else {
         dbg(str3 + " colors: " + n);
         return ints;
      }
   }

   private static int[] readPotionColors(Properties properties, String str, String str2, String str3) {
      int[] ints = new int[Potion.potionTypes.length];
      Arrays.fill(ints, -1);
      int n = 0;

      for (Object obj : properties.keySet()) {
         String str4 = (String)obj;
         String property = properties.getProperty(str4);
         if (str4.startsWith(str2)) {
            int potionId = getPotionId(str4);
            int n2 = parseColor(property);
            if (potionId >= 0 && potionId < ints.length && n2 >= 0) {
               ints[potionId] = n2;
               n++;
            } else {
               warn("Invalid color: " + str4 + " = " + property);
            }
         }
      }

      if (n <= 0) {
         return null;
      } else {
         dbg(str3 + " colors: " + n);
         return ints;
      }
   }

   private static int getPotionId(String str) {
      if (str.equals("potion.water")) {
         return 0;
      } else {
         Potion[] potionTypes = Potion.potionTypes;

         for (int i = 0; i < potionTypes.length; i++) {
            Potion potion = potionTypes[i];
            if (potion != null && potion.getName().equals(str)) {
               return potion.getId();
            }
         }

         return -1;
      }
   }

   public static int getPotionColor(int n, int n2) {
      if (potionColors == null) {
         return n2;
      } else if (n >= 0 && n < potionColors.length) {
         int n3 = potionColors[n];
         return n3 < 0 ? n2 : n3;
      } else {
         return n2;
      }
   }

   private static int getMapColorIndex(String str) {
      return str == null
         ? -1
         : (
            str.equals("air")
               ? MapColor.airColor.colorIndex
               : (
                  str.equals("grass")
                     ? MapColor.grassColor.colorIndex
                     : (
                        str.equals("sand")
                           ? MapColor.sandColor.colorIndex
                           : (
                              str.equals("cloth")
                                 ? MapColor.clothColor.colorIndex
                                 : (
                                    str.equals("tnt")
                                       ? MapColor.tntColor.colorIndex
                                       : (
                                          str.equals("ice")
                                             ? MapColor.iceColor.colorIndex
                                             : (
                                                str.equals("iron")
                                                   ? MapColor.ironColor.colorIndex
                                                   : (
                                                      str.equals("foliage")
                                                         ? MapColor.foliageColor.colorIndex
                                                         : (
                                                            str.equals("clay")
                                                               ? MapColor.clayColor.colorIndex
                                                               : (
                                                                  str.equals("dirt")
                                                                     ? MapColor.dirtColor.colorIndex
                                                                     : (
                                                                        str.equals("stone")
                                                                           ? MapColor.stoneColor.colorIndex
                                                                           : (
                                                                              str.equals("water")
                                                                                 ? MapColor.waterColor.colorIndex
                                                                                 : (
                                                                                    str.equals("wood")
                                                                                       ? MapColor.woodColor.colorIndex
                                                                                       : (
                                                                                          str.equals("quartz")
                                                                                             ? MapColor.quartzColor.colorIndex
                                                                                             : (
                                                                                                str.equals("gold")
                                                                                                   ? MapColor.goldColor.colorIndex
                                                                                                   : (
                                                                                                      str.equals("diamond")
                                                                                                         ? MapColor.diamondColor.colorIndex
                                                                                                         : (
                                                                                                            str.equals("lapis")
                                                                                                               ? MapColor.lapisColor.colorIndex
                                                                                                               : (
                                                                                                                  str.equals("emerald")
                                                                                                                     ? MapColor.emeraldColor.colorIndex
                                                                                                                     : (
                                                                                                                        str.equals("podzol")
                                                                                                                           ? MapColor.obsidianColor.colorIndex
                                                                                                                           : (
                                                                                                                              str.equals("netherrack")
                                                                                                                                 ? MapColor.netherrackColor.colorIndex
                                                                                                                                 : (
                                                                                                                                    str.equals("snow")
                                                                                                                                          || str.equals(
                                                                                                                                             "white"
                                                                                                                                          )
                                                                                                                                       ? MapColor.snowColor.colorIndex
                                                                                                                                       : (
                                                                                                                                          str.equals("adobe")
                                                                                                                                                || str.equals(
                                                                                                                                                   "orange"
                                                                                                                                                )
                                                                                                                                             ? MapColor.adobeColor
                                                                                                                                                .colorIndex
                                                                                                                                             : (
                                                                                                                                                str.equals(
                                                                                                                                                      "magenta"
                                                                                                                                                   )
                                                                                                                                                   ? MapColor.magentaColor
                                                                                                                                                      .colorIndex
                                                                                                                                                   : (
                                                                                                                                                      !str.equals(
                                                                                                                                                               "light_blue"
                                                                                                                                                            )
                                                                                                                                                            && !str.equals(
                                                                                                                                                               "lightBlue"
                                                                                                                                                            )
                                                                                                                                                         ? (
                                                                                                                                                            str.equals(
                                                                                                                                                                  "yellow"
                                                                                                                                                               )
                                                                                                                                                               ? MapColor.yellowColor
                                                                                                                                                                  .colorIndex
                                                                                                                                                               : (
                                                                                                                                                                  str.equals(
                                                                                                                                                                        "lime"
                                                                                                                                                                     )
                                                                                                                                                                     ? MapColor.limeColor
                                                                                                                                                                        .colorIndex
                                                                                                                                                                     : (
                                                                                                                                                                        str.equals(
                                                                                                                                                                              "pink"
                                                                                                                                                                           )
                                                                                                                                                                           ? MapColor.pinkColor
                                                                                                                                                                              .colorIndex
                                                                                                                                                                           : (
                                                                                                                                                                              str.equals(
                                                                                                                                                                                    "gray"
                                                                                                                                                                                 )
                                                                                                                                                                                 ? MapColor.grayColor
                                                                                                                                                                                    .colorIndex
                                                                                                                                                                                 : (
                                                                                                                                                                                    str.equals(
                                                                                                                                                                                          "silver"
                                                                                                                                                                                       )
                                                                                                                                                                                       ? MapColor.silverColor
                                                                                                                                                                                          .colorIndex
                                                                                                                                                                                       : (
                                                                                                                                                                                          str.equals(
                                                                                                                                                                                                "cyan"
                                                                                                                                                                                             )
                                                                                                                                                                                             ? MapColor.cyanColor
                                                                                                                                                                                                .colorIndex
                                                                                                                                                                                             : (
                                                                                                                                                                                                str.equals(
                                                                                                                                                                                                      "purple"
                                                                                                                                                                                                   )
                                                                                                                                                                                                   ? MapColor.purpleColor
                                                                                                                                                                                                      .colorIndex
                                                                                                                                                                                                   : (
                                                                                                                                                                                                      str.equals(
                                                                                                                                                                                                            "blue"
                                                                                                                                                                                                         )
                                                                                                                                                                                                         ? MapColor.blueColor
                                                                                                                                                                                                            .colorIndex
                                                                                                                                                                                                         : (
                                                                                                                                                                                                            str.equals(
                                                                                                                                                                                                                  "brown"
                                                                                                                                                                                                               )
                                                                                                                                                                                                               ? MapColor.brownColor
                                                                                                                                                                                                                  .colorIndex
                                                                                                                                                                                                               : (
                                                                                                                                                                                                                  str.equals(
                                                                                                                                                                                                                        "green"
                                                                                                                                                                                                                     )
                                                                                                                                                                                                                     ? MapColor.greenColor
                                                                                                                                                                                                                        .colorIndex
                                                                                                                                                                                                                     : (
                                                                                                                                                                                                                        str.equals(
                                                                                                                                                                                                                              "red"
                                                                                                                                                                                                                           )
                                                                                                                                                                                                                           ? MapColor.redColor
                                                                                                                                                                                                                              .colorIndex
                                                                                                                                                                                                                           : (
                                                                                                                                                                                                                              str.equals(
                                                                                                                                                                                                                                    "black"
                                                                                                                                                                                                                                 )
                                                                                                                                                                                                                                 ? MapColor.blackColor
                                                                                                                                                                                                                                    .colorIndex
                                                                                                                                                                                                                                 : -1
                                                                                                                                                                                                                           )
                                                                                                                                                                                                                     )
                                                                                                                                                                                                               )
                                                                                                                                                                                                         )
                                                                                                                                                                                                   )
                                                                                                                                                                                             )
                                                                                                                                                                                       )
                                                                                                                                                                                 )
                                                                                                                                                                           )
                                                                                                                                                                     )
                                                                                                                                                               )
                                                                                                                                                         )
                                                                                                                                                         : MapColor.lightBlueColor
                                                                                                                                                            .colorIndex
                                                                                                                                                   )
                                                                                                                                             )
                                                                                                                                       )
                                                                                                                                 )
                                                                                                                           )
                                                                                                                     )
                                                                                                               )
                                                                                                         )
                                                                                                   )
                                                                                             )
                                                                                       )
                                                                                 )
                                                                           )
                                                                     )
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }

   private static int[] getMapColors() {
      MapColor[] mapColorArray = MapColor.mapColorArray;
      int[] ints = new int[mapColorArray.length];
      Arrays.fill(ints, -1);

      for (int i = 0; i < mapColorArray.length && i < ints.length; i++) {
         MapColor mapColor = mapColorArray[i];
         if (mapColor != null) {
            ints[i] = mapColor.colorValue;
         }
      }

      return ints;
   }

   private static void setMapColors(int[] ints) {
      if (ints != null) {
         MapColor[] mapColorArray = MapColor.mapColorArray;
         boolean flag = false;

         for (int i = 0; i < mapColorArray.length && i < ints.length; i++) {
            MapColor mapColor = mapColorArray[i];
            if (mapColor != null) {
               int n = ints[i];
               if (n >= 0 && mapColor.colorValue != n) {
                  mapColor.colorValue = n;
                  flag = true;
               }
            }
         }

         if (flag) {
            Minecraft.getMinecraft().getTextureManager().reloadBannerTextures();
         }
      }
   }

   private static void dbg(String str) {
      Config.dbg("CustomColors: " + str);
   }

   private static void warn(String str) {
      Config.warn("CustomColors: " + str);
   }

   public static int getExpBarTextColor(int n) {
      return expBarTextColor < 0 ? n : expBarTextColor;
   }

   public static int getBossTextColor(int n) {
      return bossTextColor < 0 ? n : bossTextColor;
   }

   public static int getSignTextColor(int n) {
      return signTextColor < 0 ? n : signTextColor;
   }

   public static CustomColormap getSwampGrassColors() {
      return swampGrassColors;
   }

   public static CustomColormap getSwampFoliageColors() {
      return swampFoliageColors;
   }

   public static CustomColormap getFoliagePineColors() {
      return foliagePineColors;
   }

   public static CustomColormap getFoliageBirchColors() {
      return foliageBirchColors;
   }

   public static CustomColormap getWaterColors() {
      return waterColors;
   }


   public static final class DefaultColorizer implements IColorizer {
      @Override
      public boolean isColorConstant() {
         return false;
      }

      @Override
      public int getColor(IBlockState state, IBlockAccess world, BlockPos pos) {
         BiomeGenBase colorBiome = CustomColors.getColorBiome(world, pos);
         return CustomColors.getSwampGrassColors() != null && colorBiome == BiomeGenBase.swampland ? CustomColors.getSwampGrassColors().getColor(colorBiome, pos) : colorBiome.getGrassColorAtPos(pos);
      }
   }

   public static final class DefaultColorizer2 implements IColorizer {
      @Override
      public boolean isColorConstant() {
         return false;
      }

      @Override
      public int getColor(IBlockState state, IBlockAccess world, BlockPos pos) {
         BiomeGenBase colorBiome = CustomColors.getColorBiome(world, pos);
         return CustomColors.getSwampFoliageColors() != null && colorBiome == BiomeGenBase.swampland ? CustomColors.getSwampFoliageColors().getColor(colorBiome, pos) : colorBiome.getFoliageColorAtPos(pos);
      }
   }

   public static final class DefaultColorizer3 implements IColorizer {
      @Override
      public int getColor(IBlockState state, IBlockAccess world, BlockPos pos) {
         return CustomColors.getFoliagePineColors() != null ? CustomColors.getFoliagePineColors().getColor2(world, pos) : ColorizerFoliage.getFoliageColorPine();
      }

      @Override
      public boolean isColorConstant() {
         return CustomColors.getFoliagePineColors() == null;
      }
   }

   public static final class DefaultColorizer4 implements IColorizer {
      @Override
      public int getColor(IBlockState state, IBlockAccess world, BlockPos pos) {
         return CustomColors.getFoliageBirchColors() != null ? CustomColors.getFoliageBirchColors().getColor2(world, pos) : ColorizerFoliage.getFoliageColorBirch();
      }

      @Override
      public boolean isColorConstant() {
         return CustomColors.getFoliageBirchColors() == null;
      }
   }

   public static final class DefaultColorizer5 implements IColorizer {
      @Override
      public boolean isColorConstant() {
         return false;
      }

      @Override
      public int getColor(IBlockState state, IBlockAccess world, BlockPos pos) {
         BiomeGenBase colorBiome = CustomColors.getColorBiome(world, pos);
         return CustomColors.getWaterColors() != null ? CustomColors.getWaterColors().getColor(colorBiome, pos) : colorBiome.waterColorMultiplier;
      }
   }
}
