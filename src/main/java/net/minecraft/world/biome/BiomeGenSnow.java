package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenIcePath;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeGenSnow extends BiomeGenBase {
   private boolean superIcy;
   private WorldGenIceSpike iceSpike = new WorldGenIceSpike();
   private WorldGenIcePath icePatch = new WorldGenIcePath(4);

   public BiomeGenSnow(int n, boolean flag) {
      super(n);
      this.superIcy = flag;
      if (flag) {
         this.topBlock = Blocks.snow.getDefaultState();
      }

      this.spawnableCreatureList.clear();
   }

   @Override
   public BiomeGenBase createMutatedBiome(int n) {
      BiomeGenBase biomeGenBase = new BiomeGenSnow(n, true)
         .setColor(13828095, true)
         .setBiomeName(this.biomeName + " Spikes")
         .setEnableSnow()
         .setTemperatureRainfall(0.0F, 0.5F)
         .setHeight(new BiomeGenBase.Height(this.minHeight + 0.1F, this.maxHeight + 0.1F));
      biomeGenBase.minHeight = this.minHeight + 0.3F;
      biomeGenBase.maxHeight = this.maxHeight + 0.4F;
      return biomeGenBase;
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random rand) {
      return new WorldGenTaiga2(false);
   }

   @Override
   public void decorate(World world, Random rand, BlockPos pos) {
      if (this.superIcy) {
         for (int i = 0; i < 3; i++) {
            int n = rand.nextInt(16) + 8;
            int n2 = rand.nextInt(16) + 8;
            this.iceSpike.generate(world, rand, world.getHeight(pos.add(n, 0, n2)));
         }

         for (int j = 0; j < 2; j++) {
            int n3 = rand.nextInt(16) + 8;
            int n4 = rand.nextInt(16) + 8;
            this.icePatch.generate(world, rand, world.getHeight(pos.add(n3, 0, n4)));
         }
      }

      super.decorate(world, rand, pos);
   }
}
