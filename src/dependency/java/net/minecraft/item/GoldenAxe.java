package net.minecraft.item;

import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.FlowerForestBiomeIDIsOutOfBoundsManager;
import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GoldenAxe extends GoldenHoe {
   public final FlowerForestBiomeIDIsOutOfBoundsManager flowerForestBiomeIDIsOutOfBoundsManager;

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      this.biomeIDIsOutOfBoundsManager42.applyWorld(world, random, blockPos);
   }

   public GoldenAxe(FlowerForestBiomeIDIsOutOfBoundsManager flowerForestBiomeIDIsOutOfBoundsManager2, int count, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      super(count, biomeIDIsOutOfBoundsManager);
      this.flowerForestBiomeIDIsOutOfBoundsManager = flowerForestBiomeIDIsOutOfBoundsManager2;
   }
}
