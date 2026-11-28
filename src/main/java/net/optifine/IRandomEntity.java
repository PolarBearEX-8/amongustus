package net.optifine;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;

public interface IRandomEntity {
   int getId();

   BiomeGenBase getSpawnBiome();

   BlockPos getSpawnPosition();

   int getMaxHealth();

   int getHealth();

   String getName();
}
