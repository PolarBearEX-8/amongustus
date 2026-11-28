package net.minecraft.world.biome;

import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BiomeColorHelper {
   private static final BiomeColorHelper.ColorResolver GRASS_COLOR = new BiomeColorHelper.DefaultColorResolver();
   private static final BiomeColorHelper.ColorResolver FOLIAGE_COLOR = new BiomeColorHelper.DefaultColorResolver2();
   private static final BiomeColorHelper.ColorResolver WATER_COLOR_MULTIPLIER = new BiomeColorHelper.DefaultColorResolver3();

   public static int getFoliageColorAtPos(IBlockAccess world, BlockPos pos) {
      return getColorAtPos(world, pos, FOLIAGE_COLOR);
   }

   public static int getGrassColorAtPos(IBlockAccess world, BlockPos pos) {
      return getColorAtPos(world, pos, GRASS_COLOR);
   }

   public static int getWaterColorAtPos(IBlockAccess world, BlockPos pos) {
      return getColorAtPos(world, pos, WATER_COLOR_MULTIPLIER);
   }

   private static int getColorAtPos(IBlockAccess world, BlockPos pos, BiomeColorHelper.ColorResolver colorResolver) {
      int n = 0;
      int n2 = 0;
      int n3 = 0;

      for (BlockPos.MutableBlockPos pos2 : (Iterable<BlockPos.MutableBlockPos>)(BlockPos.getAllInBoxMutable(pos.add(-1, 0, -1), pos.add(1, 0, 1)))) {
         int colorAtPos = colorResolver.getColorAtPos(world.getBiomeGenForCoords(pos2), pos2);
         n += (colorAtPos & 0xFF0000) >> 16;
         n2 += (colorAtPos & 0xFF00) >> 8;
         n3 += colorAtPos & 0xFF;
      }

      return (n / 9 & 0xFF) << 16 | (n2 / 9 & 0xFF) << 8 | n3 / 9 & 0xFF;
   }


   public static final class DefaultColorResolver implements BiomeColorHelper.ColorResolver {
      @Override
      public int getColorAtPos(BiomeGenBase biomeGenBase, BlockPos pos) {
         return biomeGenBase.getGrassColorAtPos(pos);
      }
   }

   public static final class DefaultColorResolver2 implements BiomeColorHelper.ColorResolver {
      @Override
      public int getColorAtPos(BiomeGenBase biomeGenBase, BlockPos pos) {
         return biomeGenBase.getFoliageColorAtPos(pos);
      }
   }

   public static final class DefaultColorResolver3 implements BiomeColorHelper.ColorResolver {
      @Override
      public int getColorAtPos(BiomeGenBase biomeGenBase, BlockPos pos) {
         return biomeGenBase.waterColorMultiplier;
      }
   }

   public interface ColorResolver {
      int getColorAtPos(BiomeGenBase biomeGenBase, BlockPos pos);
   }
}
