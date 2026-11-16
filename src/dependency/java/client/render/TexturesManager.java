package client.render;

import client.event.PostUpdateBroadcaster;
import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.BuildManager;
import client.util.GeometrySelector;
import client.util.IBlockState;
import client.util.IBlockStateAdapter;
import client.util.InternalPngManager;
import client.util.LoadingManager;
import client.util.NetherManager;
import client.util.SmoothMatcher;
import client.util.SpecialLoggerEntry;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.ExtendedBlockPosAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class TexturesManager implements PostUpdateBroadcaster {
   public String labelText = null;
   public String labelText7 = null;
   public int count10 = -1;
   public SmoothMatcher[] smoothMatcherArray = null;
   public String labelText5 = null;
   public int count5 = -1;
   public int count3 = 0;
   public int count4 = 0;
   public int count2 = 0;
   public int count7 = 0;
   public int[] countArray = null;
   public float[][] factorArray = null;
   public static final int count = -1;
   public static final int count8 = 0;
   public static final int count9 = 1;
   public static final int count6 = 2;
   public static final String labelText8 = "vanilla";
   public static final String labelText11 = "grid";
   public static final String labelText3 = "fixed";
   public static final String[] stringArray = new String[]{"vanilla", "grid", "fixed"};
   public static final String labelText12 = "format";
   public static final String labelText6 = "blocks";
   public static final String labelText10 = "source";
   public static final String labelText2 = "color";
   public static final String labelText4 = "yVariance";
   public static final String labelText9 = "yOffset";

   public void applyIndex(int count11, int count12) {
      SmoothMatcher smoothMatcher = this.resolveSmoothMatcher(count11);
      if (smoothMatcher != null) {
         if (count12 >= 0) {
            smoothMatcher.applyIndex(count12);
         }
      } else {
         this.applySmoothMatcher(new SmoothMatcher(count11, count12));
      }
   }

   @Override
   public int computeCount(IBlockState blockState, IBlockAccess iBlockAccess, BlockPos blockPos) {
      return this.computeCount(iBlockAccess, blockPos);
   }

   @Override
   public boolean isActive() {
      return this.count10 == 2;
   }

   public int computeCount(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, BlockPos blockPos) {
      return this.count10 == 0 ? this.computeCountByBiomeIDIsOutOfBo(biomeIDIsOutOfBoundsManager, blockPos) : (this.count10 == 1 ? this.computeCount2(biomeIDIsOutOfBoundsManager, blockPos) : this.count5);
   }

   private static String resolveText(String text, String text2) {
      String text3 = InternalPngManager.resolveText(text, text2);
      if (!text3.startsWith(text2) && !text3.startsWith("textures/") && !text3.startsWith("mcpatcher/")) {
         text3 = text2 + "/" + text3;
      }

      if (text3.endsWith(".png")) {
         text3 = text3.substring(0, text3.length() - 4);
      }

      String text4 = "textures/blocks/";
      if (text3.startsWith(text4)) {
         text3 = text3.substring(text4.length());
      }

      if (text3.startsWith("/")) {
         text3 = text3.substring(1);
      }

      return text3;
   }

   public boolean matchesIBlockStateAdapter(IBlockStateAdapter iBlockStateAdapter) {
      return GeometrySelector.matchesIBlockStateAdapter(iBlockStateAdapter, this.smoothMatcherArray);
   }

   public boolean matchesText(String text) {
      if (this.count10 != 0 && this.count10 != 1) {
         if (this.count10 != 2) {
            return false;
         }

         if (this.count5 < 0) {
            this.count5 = 16777215;
         }
      } else {
         if (this.labelText5 == null) {
            applyText("Source not defined: " + text);
            return false;
         }

         this.renderElement();
         if (this.countArray == null) {
            return false;
         }

         if (this.count5 < 0) {
            if (this.count10 == 0) {
               this.count5 = this.computeCount(127, 127);
            }

            if (this.count10 == 1) {
               this.count5 = this.computeCount2(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35, new BlockPos(0, 64, 0));
            }
         }
      }

      return true;
   }

   private SmoothMatcher resolveSmoothMatcher(int count11) {
      if (this.smoothMatcherArray == null) {
         return null;
      } else {
         for (int index = 0; index < this.smoothMatcherArray.length; index++) {
            SmoothMatcher smoothMatcher = this.smoothMatcherArray[index];
            if (smoothMatcher.getCount() == count11) {
               return smoothMatcher;
            }
         }

         return null;
      }
   }

   public int getCount2() {
      return this.count2;
   }

   public float[][] getFloatArray() {
      if (this.factorArray == null) {
         this.factorArray = resolveFloatArray(this.countArray);
      }

      return this.factorArray;
   }

   public int computeCount(IBlockAccess iBlockAccess, double posX, double posY, double posZ, int count11) {
      if (this.count10 == 2) {
         return this.count5;
      } else {
         int count12 = MathHelper.ceiling_double_int(posX);
         int count13 = MathHelper.ceiling_double_int(posY);
         int count14 = MathHelper.ceiling_double_int(posZ);
         int count15 = 0;
         int count16 = 0;
         int count17 = 0;
         int count18 = 0;
         ExtendedBlockPosAdapter extendedBlockPosAdapter = new ExtendedBlockPosAdapter(0, 0, 0);

         for (int index = count12 - count11; index <= count12 + count11; index++) {
            for (int index2 = count14 - count11; index2 <= count14 + count11; index2++) {
               extendedBlockPosAdapter.applyIndex(index, count13, index2);
               int count19 = this.computeCount(iBlockAccess, extendedBlockPosAdapter);
               count15 += count19 >> 16 & 0xFF;
               count16 += count19 >> 8 & 0xFF;
               count17 += count19 & 0xFF;
               count18++;
            }
         }

         int count20 = count15 / count18;
         int count21 = count16 / count18;
         int count22 = count17 / count18;
         return count20 << 16 | count21 << 8 | count22;
      }
   }

   public int getCount7() {
      return this.count7;
   }

   public boolean matchesTextByText(String text) {
      if (this.smoothMatcherArray == null) {
         this.smoothMatcherArray = this.getSmoothMatcherArray();
         if (this.smoothMatcherArray == null) {
            applyText("Match blocks not defined: " + text);
            return false;
         }
      }

      return true;
   }

   public int nextInt() {
      if (this.count10 == 2) {
         return this.count5;
      } else {
         int count11 = LoadingManager.random.nextInt(this.countArray.length);
         return this.countArray[count11];
      }
   }

   public int getCount() {
      return this.count10 == 2 ? 1 : this.countArray.length;
   }

   private int computeCount2(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, BlockPos blockPos) {
      int count11 = biomeIDIsOutOfBoundsManager.count;
      int count12 = blockPos.getY() - this.count4;
      if (this.count3 > 0) {
         int count13 = BuildManager.computeCount(blockPos.getX() << 16 + blockPos.getZ());
         int count14 = this.count3 * 2 + 1;
         int count15 = (count13 & 0xFF) % count14 - this.count3;
         count12 += count15;
      }

      return this.computeCount(count11, count12);
   }

   private static void applyTextByText(String text) {
      BuildManager.applyText4("CustomColors: " + text);
   }

   private static float[][] resolveFloatArray(int[] countArray2) {
      float[][] factorMatrix = new float[countArray2.length][3];

      for (int index = 0; index < countArray2.length; index++) {
         int count11 = countArray2[index];
         float factor = (float)(count11 >> 16 & 0xFF) / 255.0F;
         float factor2 = (float)(count11 >> 8 & 0xFF) / 255.0F;
         float factor3 = (float)(count11 & 0xFF) / 255.0F;
         float[] factorArray2 = factorMatrix[index];
         factorArray2[0] = factor;
         factorArray2[1] = factor2;
         factorArray2[2] = factor3;
      }

      return factorMatrix;
   }

   private static String resolveText(String text, String text2, String text3) {
      if (text != null) {
         String text4 = text.trim();
         String text5 = ".png";
         if (text4.endsWith(text5)) {
            text4 = text4.substring(0, text4.length() - text5.length());
         }

         return resolveText(text4, text3);
      } else {
         String text6 = text2;
         int count11 = text2.lastIndexOf(47);
         if (count11 >= 0) {
            text6 = text2.substring(count11 + 1);
         }

         int count12 = text6.lastIndexOf(46);
         if (count12 >= 0) {
            text6 = text6.substring(0, count12);
         }

         return resolveText(text6, text3);
      }
   }

   private int computeCountByBiomeIDIsOutOfBo(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, BlockPos blockPos) {
      double offset = (double)MathHelper.clamp_float(biomeIDIsOutOfBoundsManager.computeFactor(blockPos), 0.0F, 1.0F);
      double offset2 = (double)MathHelper.clamp_float(biomeIDIsOutOfBoundsManager.getFactor3(), 0.0F, 1.0F) * offset;
      int count11 = (int)((1.0 - offset) * (double)(this.count2 - 1));
      int count12 = (int)((1.0 - offset2) * (double)(this.count7 - 1));
      return this.computeCount(count11, count12);
   }

   public int computeCount(IBlockAccess iBlockAccess, BlockPos blockPos) {
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = LoadingManager.resolveBiomeIDIsOutOfBoundsManager(iBlockAccess, blockPos);
      return this.computeCount(biomeIDIsOutOfBoundsManager, blockPos);
   }

   public void applySmoothMatcher(SmoothMatcher smoothMatcher) {
      if (this.smoothMatcherArray == null) {
         this.smoothMatcherArray = new SmoothMatcher[0];
      }

      this.smoothMatcherArray = (SmoothMatcher[])BuildManager.resolveTargetArrayByTargetArray(this.smoothMatcherArray, smoothMatcher);
   }

   private static void applyText(String text) {
      BuildManager.applyText3("CustomColors: " + text);
   }

   public int computeCount(int count11, int count12) {
      int count13 = BuildManager.computeCount(count11, 0, this.count2 - 1);
      int count14 = BuildManager.computeCount(count12, 0, this.count7 - 1);
      return this.countArray[count14 * this.count2 + count13] & 16777215;
   }

   public int[] getIndexArray() {
      if (this.smoothMatcherArray == null) {
         return null;
      } else {
         HashSet set = new HashSet();

         for (int index = 0; index < this.smoothMatcherArray.length; index++) {
            SmoothMatcher smoothMatcher = this.smoothMatcherArray[index];
            if (smoothMatcher.getCount() >= 0) {
               set.add(smoothMatcher.getCount());
            }
         }

         Integer[] countArray2 = ((Integer[])set.toArray(new Integer[set.size()]));
         int[] countArray3 = new int[countArray2.length];

         for (int index2 = 0; index2 < countArray2.length; index2++) {
            countArray3[index2] = countArray2[index2];
         }

         return countArray3;
      }
   }

   private void renderElement() {
      try {
         this.countArray = null;
         if (this.labelText5 == null) {
            return;
         }

         String text = this.labelText5 + ".png";
         InputStream inputStream = BuildManager.resolveInputStream(new ResourceLocation(text));
         if (inputStream == null) {
            return;
         }

         BufferedImage bufferedImage = SpecialLoggerEntry.resolveBufferedImage(inputStream);
         if (bufferedImage == null) {
            return;
         }

         int width = bufferedImage.getWidth();
         int height = bufferedImage.getHeight();
         boolean flag = this.count2 < 0 || this.count2 == width;
         boolean flag2 = this.count7 < 0 || this.count7 == height;
         if (!flag || !flag2) {
            applyTextByText("Non-standard palette size: " + width + "x" + height + ", should be: " + this.count2 + "x" + this.count7 + ", path: " + text);
         }

         this.count2 = width;
         this.count7 = height;
         if (this.count2 <= 0 || this.count7 <= 0) {
            applyText("Invalid palette size: " + width + "x" + height + ", path: " + text);
            return;
         }

         this.countArray = new int[width * height];
         bufferedImage.getRGB(0, 0, width, height, this.countArray, 0, width);
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   public int computeCount(int count11) {
      int count12 = BuildManager.computeCount(count11, 0, this.countArray.length - 1);
      return this.countArray[count12] & 16777215;
   }

   private int computeCount(String text) {
      if (text == null) {
         return 0;
      } else {
         String text2 = text.trim();
         if (text2.equals("vanilla")) {
            return 0;
         } else if (text2.equals("grid")) {
            return 1;
         } else if (text2.equals("fixed")) {
            return 2;
         } else {
            applyText("Unknown format: " + text2);
            return -1;
         }
      }
   }

   private SmoothMatcher[] getSmoothMatcherArray() {
      Block blockFromName = Block.getBlockFromName(this.labelText);
      if (blockFromName != null) {
         return new SmoothMatcher[]{new SmoothMatcher(Block.getIdFromBlock(blockFromName))};
      } else {
         Matcher matcher = Pattern.compile("^block([0-9]+).*$").matcher(this.labelText);
         if (matcher.matches()) {
            int count11 = BuildManager.computeCount(matcher.group(1), -1);
            if (count11 >= 0) {
               return new SmoothMatcher[]{new SmoothMatcher(count11)};
            }
         }

         SmoothMatcher[] smoothMatcherArray2 = new NetherManager("Colormap").resolveSmoothMatcherArray(this.labelText);
         return smoothMatcherArray2 != null ? smoothMatcherArray2 : null;
      }
   }

   public TexturesManager(Properties properties, String text, int count11, int count12, String text2) {
      NetherManager netherManager = new NetherManager("Colormap");
      this.labelText = netherManager.resolveText(text);
      this.labelText7 = netherManager.resolveTextByText(text);
      this.count10 = this.computeCount(properties.getProperty("format", text2));
      this.smoothMatcherArray = netherManager.resolveSmoothMatcherArrayByText(properties.getProperty("blocks"));
      this.labelText5 = resolveText(properties.getProperty("source"), text, this.labelText7);
      this.count5 = NetherManager.computeCount2(properties.getProperty("color"), -1);
      this.count3 = netherManager.computeCount(properties.getProperty("yVariance"), 0);
      this.count4 = netherManager.computeCount(properties.getProperty("yOffset"), 0);
      this.count2 = count11;
      this.count7 = count12;
   }

   @Override
   public String toString() {
      return "" + this.labelText7 + "/" + this.labelText + ", blocks: " + BuildManager.resolveText(this.smoothMatcherArray) + ", source: " + this.labelText5;
   }
}
