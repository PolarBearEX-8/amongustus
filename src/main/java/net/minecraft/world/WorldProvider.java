package net.minecraft.world;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderDebug;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.FlatGeneratorInfo;

public abstract class WorldProvider {
   public static final float[] moonPhaseFactors = new float[]{1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
   public World worldObj;
   private WorldType terrainType;
   private String generatorSettings;
   public WorldChunkManager worldChunkMgr;
   public boolean isHellWorld;
   public boolean hasNoSky;
   public final float[] lightBrightnessTable = new float[16];
   public int dimensionId;
   private final float[] colorsSunriseSunset = new float[4];

   public float[] calcSunriseSunsetColors(float f, float f2) {
      float f3 = 0.4F;
      float f4 = MathHelper.cos(f * (float) Math.PI * 2.0F) - 0.0F;
      float f5 = -0.0F;
      if (f4 >= f5 - f3 && f4 <= f5 + f3) {
         float f6 = (f4 - f5) / f3 * 0.5F + 0.5F;
         float f7 = 1.0F - (1.0F - MathHelper.sin(f6 * (float) Math.PI)) * 0.99F;
         f7 *= f7;
         this.colorsSunriseSunset[0] = f6 * 0.3F + 0.7F;
         this.colorsSunriseSunset[1] = f6 * f6 * 0.7F + 0.2F;
         this.colorsSunriseSunset[2] = f6 * f6 * 0.0F + 0.2F;
         this.colorsSunriseSunset[3] = f7;
         return this.colorsSunriseSunset;
      } else {
         return null;
      }
   }

   public float calculateCelestialAngle(long l, float f) {
      float f2 = ((float)((int)(l % 24000L)) + f) / 24000.0F - 0.25F;
      if (f2 < 0.0F) {
         f2++;
      }

      if (f2 > 1.0F) {
         f2--;
      }

      f2 = 1.0F - (float)((Math.cos((double)f2 * Math.PI) + 1.0) / 2.0);
      return f2 + (f2 - f2) / 3.0F;
   }

   public WorldBorder getWorldBorder() {
      return new WorldBorder();
   }

   public boolean isSurfaceWorld() {
      return true;
   }

   public float[] getLightBrightnessTable() {
      return this.lightBrightnessTable;
   }

   public boolean getHasNoSky() {
      return this.hasNoSky;
   }

   public double getVoidFogYFactor() {
      return this.terrainType == WorldType.FLAT ? 1.0 : 0.03125;
   }

   public void generateLightBrightnessTable() {
      float f = 0.0F;

      for (int i = 0; i <= 15; i++) {
         float f2 = 1.0F - (float)i / 15.0F;
         this.lightBrightnessTable[i] = (1.0F - f2) / (f2 * 3.0F + 1.0F) * (1.0F - f) + f;
      }
   }

   public boolean canRespawnHere() {
      return true;
   }

   public boolean canCoordinateBeSpawn(int n, int n2) {
      return this.worldObj.getGroundAboveSeaLevel(new BlockPos(n, 0, n2)) == Blocks.grass;
   }

   public boolean doesXZShowFog(int n, int n2) {
      return false;
   }

   public int getMoonPhase(long l) {
      return (int)(l / 24000L % 8L + 8L) % 8;
   }

   public float getCloudHeight() {
      return 128.0F;
   }

   public abstract String getInternalNameSuffix();

   public void registerWorldChunkManager() {
      WorldType terrainType2 = this.worldObj.getWorldInfo().getTerrainType();
      if (terrainType2 == WorldType.FLAT) {
         FlatGeneratorInfo flatGeneratorInfo = FlatGeneratorInfo.createFlatGeneratorFromString(this.worldObj.getWorldInfo().getGeneratorOptions());
         this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.getBiomeFromBiomeList(flatGeneratorInfo.getBiome(), BiomeGenBase.DEFAULT), 0.5F);
      } else if (terrainType2 == WorldType.DEBUG_WORLD) {
         this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.plains, 0.0F);
      } else {
         this.worldChunkMgr = new WorldChunkManager(this.worldObj);
      }
   }

   public int getDimensionId() {
      return this.dimensionId;
   }

   public final void registerWorld(World world) {
      this.worldObj = world;
      this.terrainType = world.getWorldInfo().getTerrainType();
      this.generatorSettings = world.getWorldInfo().getGeneratorOptions();
      this.registerWorldChunkManager();
      this.generateLightBrightnessTable();
   }

   public BlockPos getSpawnCoordinate() {
      return null;
   }

   public WorldChunkManager getWorldChunkManager() {
      return this.worldChunkMgr;
   }

   public static WorldProvider getProviderForDimension(int n) {
      return (WorldProvider)(n == -1 ? new WorldProviderHell() : (n == 0 ? new WorldProviderSurface() : (n == 1 ? new WorldProviderEnd() : null)));
   }

   public abstract String getDimensionName();

   public IChunkProvider createChunkGenerator() {
      return (IChunkProvider)(this.terrainType == WorldType.FLAT
         ? new ChunkProviderFlat(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.generatorSettings)
         : (
            this.terrainType == WorldType.DEBUG_WORLD
               ? new ChunkProviderDebug(this.worldObj)
               : (
                  this.terrainType == WorldType.CUSTOMIZED
                     ? new ChunkProviderGenerate(
                        this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.generatorSettings
                     )
                     : new ChunkProviderGenerate(
                        this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.generatorSettings
                     )
               )
         ));
   }

   public Vec3 getFogColor(float f, float f2) {
      float f3 = MathHelper.cos(f * (float) Math.PI * 2.0F) * 2.0F + 0.5F;
      f3 = MathHelper.clamp_float(f3, 0.0F, 1.0F);
      float f4 = 0.7529412F;
      float f5 = 0.84705883F;
      float f6 = 1.0F;
      f4 *= f3 * 0.94F + 0.06F;
      f5 *= f3 * 0.94F + 0.06F;
      f6 *= f3 * 0.91F + 0.09F;
      return new Vec3((double)f4, (double)f5, (double)f6);
   }

   public boolean isSkyColored() {
      return true;
   }

   public boolean doesWaterVaporize() {
      return this.isHellWorld;
   }

   public int getAverageGroundLevel() {
      return this.terrainType == WorldType.FLAT ? 4 : this.worldObj.getSeaLevel() + 1;
   }
}
