package net.optifine;

import net.optifine.config.Matches;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.util.TextureUtils;

public class CustomColormap implements IColorizer {
   public String name = null;
   public String basePath = null;
   private int format = -1;
   private MatchBlock[] matchBlocks = null;
   private String source = null;
   private int color = -1;
   private int yVariance = 0;
   private int yOffset = 0;
   private int width = 0;
   private int height = 0;
   private int[] colors = null;
   private float[][] colorsRgb = (float[][])null;
   private static final int FORMAT_UNKNOWN = -1;
   private static final int FORMAT_VANILLA = 0;
   private static final int FORMAT_GRID = 1;
   private static final int FORMAT_FIXED = 2;
   public static final String FORMAT_VANILLA_STRING = null;
   public static final String FORMAT_GRID_STRING = null;
   public static final String FORMAT_FIXED_STRING = null;
   public static final String[] FORMAT_STRINGS = new String[]{"vanilla", "grid", "fixed"};
   public static final String KEY_FORMAT = null;
   public static final String KEY_BLOCKS = null;
   public static final String KEY_SOURCE = null;
   public static final String KEY_COLOR = null;
   public static final String KEY_Y_VARIANCE = null;
   public static final String KEY_Y_OFFSET = null;

   private static String parseTexture(String str, String str2, String str3) {
      if (str != null) {
         str = str.trim();
         String str4 = ".png";
         if (str.endsWith(str4)) {
            str = str.substring(0, str.length() - str4.length());
         }

         return fixTextureName(str, str3);
      } else {
         String str5 = str2;
         int n = str2.lastIndexOf(47);
         if (n >= 0) {
            str5 = str2.substring(n + 1);
         }

         int n2 = str5.lastIndexOf(46);
         if (n2 >= 0) {
            str5 = str5.substring(0, n2);
         }

         return fixTextureName(str5, str3);
      }
   }

   private MatchBlock getMatchBlock(int n) {
      if (this.matchBlocks == null) {
         return null;
      } else {
         for (int i = 0; i < this.matchBlocks.length; i++) {
            MatchBlock matchBlock = this.matchBlocks[i];
            if (matchBlock.getBlockId() == n) {
               return matchBlock;
            }
         }

         return null;
      }
   }

   @Override
   public int getColor(IBlockState state, IBlockAccess world, BlockPos pos) {
      return this.getColor2(world, pos);
   }

   public int getColor(int n) {
      n = Config.limit(n, 0, this.colors.length - 1);
      return this.colors[n] & 16777215;
   }

   private void readColors() {
      try {
         this.colors = null;
         if (this.source == null) {
            return;
         }

         String str = this.source + ".png";
         InputStream resourceStream = Config.getResourceStream(new ResourceLocation(str));
         if (resourceStream == null) {
            return;
         }

         BufferedImage image = TextureUtil.readBufferedImage(resourceStream);
         if (image == null) {
            return;
         }

         int width2 = image.getWidth();
         int height2 = image.getHeight();
         boolean flag = this.width < 0 || this.width == width2;
         boolean flag2 = this.height < 0 || this.height == height2;
         if (!flag || !flag2) {
            dbg("Non-standard palette size: " + width2 + "x" + height2 + ", should be: " + this.width + "x" + this.height + ", path: " + str);
         }

         this.width = width2;
         this.height = height2;
         if (this.width <= 0 || this.height <= 0) {
            warn("Invalid palette size: " + width2 + "x" + height2 + ", path: " + str);
            return;
         }

         this.colors = new int[width2 * height2];
         image.getRGB(0, 0, width2, height2, this.colors, 0, width2);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static void dbg(String str) {
      Config.dbg("CustomColors: " + str);
   }

   public int getColorRandom() {
      if (this.format == 2) {
         return this.color;
      } else {
         int n = CustomColors.random.nextInt(this.colors.length);
         return this.colors[n];
      }
   }

   private static String fixTextureName(String str, String str2) {
      str = TextureUtils.fixResourcePath(str, str2);
      if (!str.startsWith(str2) && !str.startsWith("textures/") && !str.startsWith("mcpatcher/")) {
         str = str2 + "/" + str;
      }

      if (str.endsWith(".png")) {
         str = str.substring(0, str.length() - 4);
      }

      String str3 = "textures/blocks/";
      if (str.startsWith(str3)) {
         str = str.substring(str3.length());
      }

      if (str.startsWith("/")) {
         str = str.substring(1);
      }

      return str;
   }

   @Override
   public boolean isColorConstant() {
      return this.format == 2;
   }

   public boolean isValid(String str) {
      if (this.format != 0 && this.format != 1) {
         if (this.format != 2) {
            return false;
         }

         if (this.color < 0) {
            this.color = 16777215;
         }
      } else {
         if (this.source == null) {
            warn("Source not defined: " + str);
            return false;
         }

         this.readColors();
         if (this.colors == null) {
            return false;
         }

         if (this.color < 0) {
            if (this.format == 0) {
               this.color = this.getColor3(127, 127);
            }

            if (this.format == 1) {
               this.color = this.getColorGrid(BiomeGenBase.plains, new BlockPos(0, 64, 0));
            }
         }
      }

      return true;
   }

   public CustomColormap(Properties properties, String str, int n, int n2, String str2) {
      ConnectedParser connectedParser = new ConnectedParser("Colormap");
      this.name = connectedParser.parseName(str);
      this.basePath = connectedParser.parseBasePath(str);
      this.format = this.parseFormat(properties.getProperty("format", str2));
      this.matchBlocks = connectedParser.parseMatchBlocks(properties.getProperty("blocks"));
      this.source = parseTexture(properties.getProperty("source"), str, this.basePath);
      this.color = ConnectedParser.parseColor(properties.getProperty("color"), -1);
      this.yVariance = connectedParser.parseInt(properties.getProperty("yVariance"), 0);
      this.yOffset = connectedParser.parseInt(properties.getProperty("yOffset"), 0);
      this.width = n;
      this.height = n2;
   }

   public float[][] getColorsRgb() {
      if (this.colorsRgb == null) {
         this.colorsRgb = toRgb(this.colors);
      }

      return this.colorsRgb;
   }

   public int getHeight() {
      return this.height;
   }

   private MatchBlock[] detectMatchBlocks() {
      Block blockFromName = Block.getBlockFromName(this.name);
      if (blockFromName != null) {
         return new MatchBlock[]{new MatchBlock(Block.getIdFromBlock(blockFromName))};
      } else {
         Matcher matcher = Pattern.compile("^block([0-9]+).*$").matcher(this.name);
         if (matcher.matches()) {
            int n = Config.parseInt(matcher.group(1), -1);
            if (n >= 0) {
               return new MatchBlock[]{new MatchBlock(n)};
            }
         }

         MatchBlock[] matchBlocks2 = new ConnectedParser("Colormap").parseMatchBlock(this.name);
         return matchBlocks2 != null ? matchBlocks2 : null;
      }
   }

   public int getColor(BiomeGenBase biomeGenBase, BlockPos pos) {
      return this.format == 0 ? this.getColorVanilla(biomeGenBase, pos) : (this.format == 1 ? this.getColorGrid(biomeGenBase, pos) : this.color);
   }

   private int parseFormat(String str) {
      if (str == null) {
         return 0;
      } else {
         str = str.trim();
         if (str.equals("vanilla")) {
            return 0;
         } else if (str.equals("grid")) {
            return 1;
         } else if (str.equals("fixed")) {
            return 2;
         } else {
            warn("Unknown format: " + str);
            return -1;
         }
      }
   }

   public int[] getMatchBlockIds() {
      if (this.matchBlocks == null) {
         return null;
      } else {
         HashSet set = new HashSet();

         for (int i = 0; i < this.matchBlocks.length; i++) {
            MatchBlock matchBlock = this.matchBlocks[i];
            if (matchBlock.getBlockId() >= 0) {
               set.add(matchBlock.getBlockId());
            }
         }

         Integer[] nums = (Integer[])set.toArray(new Integer[set.size()]);
         int[] ints = new int[nums.length];

         for (int j = 0; j < nums.length; j++) {
            ints[j] = nums[j];
         }

         return ints;
      }
   }

   public int getColor3(int n, int n2) {
      n = Config.limit(n, 0, this.width - 1);
      n2 = Config.limit(n2, 0, this.height - 1);
      return this.colors[n2 * this.width + n] & 16777215;
   }

   public int getColorSmooth(IBlockAccess world, double d, double d2, double d3, int n) {
      if (this.format == 2) {
         return this.color;
      } else {
         int n2 = MathHelper.floor_double(d);
         int n3 = MathHelper.floor_double(d2);
         int n4 = MathHelper.floor_double(d3);
         int n5 = 0;
         int n6 = 0;
         int n7 = 0;
         int n8 = 0;
         BlockPosM blockPosM = new BlockPosM(0, 0, 0);

         for (int i = n2 - n; i <= n2 + n; i++) {
            for (int j = n4 - n; j <= n4 + n; j++) {
               blockPosM.setXyz(i, n3, j);
               int color2 = this.getColor2(world, blockPosM);
               n5 += color2 >> 16 & 0xFF;
               n6 += color2 >> 8 & 0xFF;
               n7 += color2 & 0xFF;
               n8++;
            }
         }

         if (n8 == 0) {
            return 0;
         } else {
            return (n5 / n8) << 16 | (n6 / n8) << 8 | (n7 / n8);
         }
      }
   }

   private static void warn(String str) {
      Config.warn("CustomColors: " + str);
   }

   public void addMatchBlock(int n, int n2) {
      MatchBlock matchBlock = this.getMatchBlock(n);
      if (matchBlock != null) {
         if (n2 >= 0) {
            matchBlock.addMetadata(n2);
         }
      } else {
         this.addMatchBlock(new MatchBlock(n, n2));
      }
   }

   public int getWidth() {
      return this.width;
   }

   public void addMatchBlock(MatchBlock matchBlock) {
      if (this.matchBlocks == null) {
         this.matchBlocks = new MatchBlock[0];
      }

      this.matchBlocks = (MatchBlock[])Config.addObjectToArray(this.matchBlocks, matchBlock);
   }

   public int getColor2(IBlockAccess world, BlockPos pos) {
      return this.getColor(CustomColors.getColorBiome(world, pos), pos);
   }

   private static float[][] toRgb(int[] ints) {
      float[][] floatsArr = new float[ints.length][3];

      for (int i = 0; i < ints.length; i++) {
         int n = ints[i];
         float f = (float)(n >> 16 & 0xFF) / 255.0F;
         float f2 = (float)(n >> 8 & 0xFF) / 255.0F;
         float f3 = (float)(n & 0xFF) / 255.0F;
         float[] floats = floatsArr[i];
         floats[0] = f;
         floats[1] = f2;
         floats[2] = f3;
      }

      return floatsArr;
   }

   private int getColorGrid(BiomeGenBase biomeGenBase, BlockPos pos) {
      int biomeID = biomeGenBase.biomeID;
      int n = pos.getY() - this.yOffset;
      if (this.yVariance > 0) {
         int n5 = (Config.intHash(pos.getX() << 16 + pos.getZ()) & 0xFF) % (this.yVariance * 2 + 1) - this.yVariance;
         n += n5;
      }

      return this.getColor3(biomeID, n);
   }

   public boolean matchesBlock(BlockStateBase blockStateBase) {
      return Matches.block(blockStateBase, this.matchBlocks);
   }

   @Override
   public String toString() {
      return "" + this.basePath + "/" + this.name + ", blocks: " + Config.arrayToString3(this.matchBlocks) + ", source: " + this.source;
   }

   private int getColorVanilla(BiomeGenBase biomeGenBase, BlockPos pos) {
      double d = (double)MathHelper.clamp_float(biomeGenBase.getFloatTemperature(pos), 0.0F, 1.0F);
      double d2 = (double)MathHelper.clamp_float(biomeGenBase.getFloatRainfall(), 0.0F, 1.0F);
      d2 *= d;
      return this.getColor3((int)((1.0 - d) * (double)(this.width - 1)), (int)((1.0 - d2) * (double)(this.height - 1)));
   }

   public int getLength() {
      return this.format == 2 ? 1 : this.colors.length;
   }

   public boolean isValidMatchBlocks(String str) {
      if (this.matchBlocks == null) {
         this.matchBlocks = this.detectMatchBlocks();
         if (this.matchBlocks == null) {
            warn("Match blocks not defined: " + str);
            return false;
         }
      }

      return true;
   }
}
