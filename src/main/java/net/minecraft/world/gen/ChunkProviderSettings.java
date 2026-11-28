package net.minecraft.world.gen;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import net.minecraft.world.biome.BiomeGenBase;
public class ChunkProviderSettings {
   public final float coordinateScale;
   public final float heightScale;
   public final float upperLimitScale;
   public final float lowerLimitScale;
   public final float depthNoiseScaleX;
   public final float depthNoiseScaleZ;
   public final float depthNoiseScaleExponent;
   public final float mainNoiseScaleX;
   public final float mainNoiseScaleY;
   public final float mainNoiseScaleZ;
   public final float baseSize;
   public final float stretchY;
   public final float biomeDepthWeight;
   public final float biomeDepthOffSet;
   public final float biomeScaleWeight;
   public final float biomeScaleOffset;
   public final int seaLevel;
   public final boolean useCaves;
   public final boolean useDungeons;
   public final int dungeonChance;
   public final boolean useStrongholds;
   public final boolean useVillages;
   public final boolean useMineShafts;
   public final boolean useTemples;
   public final boolean useMonuments;
   public final boolean useRavines;
   public final boolean useWaterLakes;
   public final int waterLakeChance;
   public final boolean useLavaLakes;
   public final int lavaLakeChance;
   public final boolean useLavaOceans;
   public final int fixedBiome;
   public final int biomeSize;
   public final int riverSize;
   public final int dirtSize;
   public final int dirtCount;
   public final int dirtMinHeight;
   public final int dirtMaxHeight;
   public final int gravelSize;
   public final int gravelCount;
   public final int gravelMinHeight;
   public final int gravelMaxHeight;
   public final int graniteSize;
   public final int graniteCount;
   public final int graniteMinHeight;
   public final int graniteMaxHeight;
   public final int dioriteSize;
   public final int dioriteCount;
   public final int dioriteMinHeight;
   public final int dioriteMaxHeight;
   public final int andesiteSize;
   public final int andesiteCount;
   public final int andesiteMinHeight;
   public final int andesiteMaxHeight;
   public final int coalSize;
   public final int coalCount;
   public final int coalMinHeight;
   public final int coalMaxHeight;
   public final int ironSize;
   public final int ironCount;
   public final int ironMinHeight;
   public final int ironMaxHeight;
   public final int goldSize;
   public final int goldCount;
   public final int goldMinHeight;
   public final int goldMaxHeight;
   public final int redstoneSize;
   public final int redstoneCount;
   public final int redstoneMinHeight;
   public final int redstoneMaxHeight;
   public final int diamondSize;
   public final int diamondCount;
   public final int diamondMinHeight;
   public final int diamondMaxHeight;
   public final int lapisSize;
   public final int lapisCount;
   public final int lapisCenterHeight;
   public final int lapisSpread;

   private ChunkProviderSettings(ChunkProviderSettings.Factory factory) {
      this.coordinateScale = factory.coordinateScale;
      this.heightScale = factory.heightScale;
      this.upperLimitScale = factory.upperLimitScale;
      this.lowerLimitScale = factory.lowerLimitScale;
      this.depthNoiseScaleX = factory.depthNoiseScaleX;
      this.depthNoiseScaleZ = factory.depthNoiseScaleZ;
      this.depthNoiseScaleExponent = factory.depthNoiseScaleExponent;
      this.mainNoiseScaleX = factory.mainNoiseScaleX;
      this.mainNoiseScaleY = factory.mainNoiseScaleY;
      this.mainNoiseScaleZ = factory.mainNoiseScaleZ;
      this.baseSize = factory.baseSize;
      this.stretchY = factory.stretchY;
      this.biomeDepthWeight = factory.biomeDepthWeight;
      this.biomeDepthOffSet = factory.biomeDepthOffset;
      this.biomeScaleWeight = factory.biomeScaleWeight;
      this.biomeScaleOffset = factory.biomeScaleOffset;
      this.seaLevel = factory.seaLevel;
      this.useCaves = factory.useCaves;
      this.useDungeons = factory.useDungeons;
      this.dungeonChance = factory.dungeonChance;
      this.useStrongholds = factory.useStrongholds;
      this.useVillages = factory.useVillages;
      this.useMineShafts = factory.useMineShafts;
      this.useTemples = factory.useTemples;
      this.useMonuments = factory.useMonuments;
      this.useRavines = factory.useRavines;
      this.useWaterLakes = factory.useWaterLakes;
      this.waterLakeChance = factory.waterLakeChance;
      this.useLavaLakes = factory.useLavaLakes;
      this.lavaLakeChance = factory.lavaLakeChance;
      this.useLavaOceans = factory.useLavaOceans;
      this.fixedBiome = factory.fixedBiome;
      this.biomeSize = factory.biomeSize;
      this.riverSize = factory.riverSize;
      this.dirtSize = factory.dirtSize;
      this.dirtCount = factory.dirtCount;
      this.dirtMinHeight = factory.dirtMinHeight;
      this.dirtMaxHeight = factory.dirtMaxHeight;
      this.gravelSize = factory.gravelSize;
      this.gravelCount = factory.gravelCount;
      this.gravelMinHeight = factory.gravelMinHeight;
      this.gravelMaxHeight = factory.gravelMaxHeight;
      this.graniteSize = factory.graniteSize;
      this.graniteCount = factory.graniteCount;
      this.graniteMinHeight = factory.graniteMinHeight;
      this.graniteMaxHeight = factory.graniteMaxHeight;
      this.dioriteSize = factory.dioriteSize;
      this.dioriteCount = factory.dioriteCount;
      this.dioriteMinHeight = factory.dioriteMinHeight;
      this.dioriteMaxHeight = factory.dioriteMaxHeight;
      this.andesiteSize = factory.andesiteSize;
      this.andesiteCount = factory.andesiteCount;
      this.andesiteMinHeight = factory.andesiteMinHeight;
      this.andesiteMaxHeight = factory.andesiteMaxHeight;
      this.coalSize = factory.coalSize;
      this.coalCount = factory.coalCount;
      this.coalMinHeight = factory.coalMinHeight;
      this.coalMaxHeight = factory.coalMaxHeight;
      this.ironSize = factory.ironSize;
      this.ironCount = factory.ironCount;
      this.ironMinHeight = factory.ironMinHeight;
      this.ironMaxHeight = factory.ironMaxHeight;
      this.goldSize = factory.goldSize;
      this.goldCount = factory.goldCount;
      this.goldMinHeight = factory.goldMinHeight;
      this.goldMaxHeight = factory.goldMaxHeight;
      this.redstoneSize = factory.redstoneSize;
      this.redstoneCount = factory.redstoneCount;
      this.redstoneMinHeight = factory.redstoneMinHeight;
      this.redstoneMaxHeight = factory.redstoneMaxHeight;
      this.diamondSize = factory.diamondSize;
      this.diamondCount = factory.diamondCount;
      this.diamondMinHeight = factory.diamondMinHeight;
      this.diamondMaxHeight = factory.diamondMaxHeight;
      this.lapisSize = factory.lapisSize;
      this.lapisCount = factory.lapisCount;
      this.lapisCenterHeight = factory.lapisCenterHeight;
      this.lapisSpread = factory.lapisSpread;
   }

   public ChunkProviderSettings(ChunkProviderSettings.Factory factory, ChunkProviderSettings.InnerEntry obj) {
      this(factory);
   }


   public static class InnerEntry {
   }

   public static class Factory {
      public static final Gson JSON_ADAPTER = new GsonBuilder()
         .registerTypeAdapter(Factory.class, new ChunkProviderSettings.Serializer())
         .create();
      public float coordinateScale = 684.412F;
      public float heightScale = 684.412F;
      public float upperLimitScale = 512.0F;
      public float lowerLimitScale = 512.0F;
      public float depthNoiseScaleX = 200.0F;
      public float depthNoiseScaleZ = 200.0F;
      public float depthNoiseScaleExponent = 0.5F;
      public float mainNoiseScaleX = 80.0F;
      public float mainNoiseScaleY = 160.0F;
      public float mainNoiseScaleZ = 80.0F;
      public float baseSize = 8.5F;
      public float stretchY = 12.0F;
      public float biomeDepthWeight = 1.0F;
      public float biomeDepthOffset = 0.0F;
      public float biomeScaleWeight = 1.0F;
      public float biomeScaleOffset = 0.0F;
      public int seaLevel = 63;
      public boolean useCaves = true;
      public boolean useDungeons = true;
      public int dungeonChance = 8;
      public boolean useStrongholds = true;
      public boolean useVillages = true;
      public boolean useMineShafts = true;
      public boolean useTemples = true;
      public boolean useMonuments = true;
      public boolean useRavines = true;
      public boolean useWaterLakes = true;
      public int waterLakeChance = 4;
      public boolean useLavaLakes = true;
      public int lavaLakeChance = 80;
      public boolean useLavaOceans = false;
      public int fixedBiome = -1;
      public int biomeSize = 4;
      public int riverSize = 4;
      public int dirtSize = 33;
      public int dirtCount = 10;
      public int dirtMinHeight = 0;
      public int dirtMaxHeight = 256;
      public int gravelSize = 33;
      public int gravelCount = 8;
      public int gravelMinHeight = 0;
      public int gravelMaxHeight = 256;
      public int graniteSize = 33;
      public int graniteCount = 10;
      public int graniteMinHeight = 0;
      public int graniteMaxHeight = 80;
      public int dioriteSize = 33;
      public int dioriteCount = 10;
      public int dioriteMinHeight = 0;
      public int dioriteMaxHeight = 80;
      public int andesiteSize = 33;
      public int andesiteCount = 10;
      public int andesiteMinHeight = 0;
      public int andesiteMaxHeight = 80;
      public int coalSize = 17;
      public int coalCount = 20;
      public int coalMinHeight = 0;
      public int coalMaxHeight = 128;
      public int ironSize = 9;
      public int ironCount = 20;
      public int ironMinHeight = 0;
      public int ironMaxHeight = 64;
      public int goldSize = 9;
      public int goldCount = 2;
      public int goldMinHeight = 0;
      public int goldMaxHeight = 32;
      public int redstoneSize = 8;
      public int redstoneCount = 8;
      public int redstoneMinHeight = 0;
      public int redstoneMaxHeight = 16;
      public int diamondSize = 8;
      public int diamondCount = 1;
      public int diamondMinHeight = 0;
      public int diamondMaxHeight = 16;
      public int lapisSize = 7;
      public int lapisCount = 1;
      public int lapisCenterHeight = 16;
      public int lapisSpread = 16;

      @Override
      public int hashCode() {
         int n = this.coordinateScale != 0.0F ? Float.floatToIntBits(this.coordinateScale) : 0;
         n = 31 * n + (this.heightScale != 0.0F ? Float.floatToIntBits(this.heightScale) : 0);
         n = 31 * n + (this.upperLimitScale != 0.0F ? Float.floatToIntBits(this.upperLimitScale) : 0);
         n = 31 * n + (this.lowerLimitScale != 0.0F ? Float.floatToIntBits(this.lowerLimitScale) : 0);
         n = 31 * n + (this.depthNoiseScaleX != 0.0F ? Float.floatToIntBits(this.depthNoiseScaleX) : 0);
         n = 31 * n + (this.depthNoiseScaleZ != 0.0F ? Float.floatToIntBits(this.depthNoiseScaleZ) : 0);
         n = 31 * n + (this.depthNoiseScaleExponent != 0.0F ? Float.floatToIntBits(this.depthNoiseScaleExponent) : 0);
         n = 31 * n + (this.mainNoiseScaleX != 0.0F ? Float.floatToIntBits(this.mainNoiseScaleX) : 0);
         n = 31 * n + (this.mainNoiseScaleY != 0.0F ? Float.floatToIntBits(this.mainNoiseScaleY) : 0);
         n = 31 * n + (this.mainNoiseScaleZ != 0.0F ? Float.floatToIntBits(this.mainNoiseScaleZ) : 0);
         n = 31 * n + (this.baseSize != 0.0F ? Float.floatToIntBits(this.baseSize) : 0);
         n = 31 * n + (this.stretchY != 0.0F ? Float.floatToIntBits(this.stretchY) : 0);
         n = 31 * n + (this.biomeDepthWeight != 0.0F ? Float.floatToIntBits(this.biomeDepthWeight) : 0);
         n = 31 * n + (this.biomeDepthOffset != 0.0F ? Float.floatToIntBits(this.biomeDepthOffset) : 0);
         n = 31 * n + (this.biomeScaleWeight != 0.0F ? Float.floatToIntBits(this.biomeScaleWeight) : 0);
         n = 31 * n + (this.biomeScaleOffset != 0.0F ? Float.floatToIntBits(this.biomeScaleOffset) : 0);
         n = 31 * n + this.seaLevel;
         n = 31 * n + (this.useCaves ? 1 : 0);
         n = 31 * n + (this.useDungeons ? 1 : 0);
         n = 31 * n + this.dungeonChance;
         n = 31 * n + (this.useStrongholds ? 1 : 0);
         n = 31 * n + (this.useVillages ? 1 : 0);
         n = 31 * n + (this.useMineShafts ? 1 : 0);
         n = 31 * n + (this.useTemples ? 1 : 0);
         n = 31 * n + (this.useMonuments ? 1 : 0);
         n = 31 * n + (this.useRavines ? 1 : 0);
         n = 31 * n + (this.useWaterLakes ? 1 : 0);
         n = 31 * n + this.waterLakeChance;
         n = 31 * n + (this.useLavaLakes ? 1 : 0);
         n = 31 * n + this.lavaLakeChance;
         n = 31 * n + (this.useLavaOceans ? 1 : 0);
         n = 31 * n + this.fixedBiome;
         n = 31 * n + this.biomeSize;
         n = 31 * n + this.riverSize;
         n = 31 * n + this.dirtSize;
         n = 31 * n + this.dirtCount;
         n = 31 * n + this.dirtMinHeight;
         n = 31 * n + this.dirtMaxHeight;
         n = 31 * n + this.gravelSize;
         n = 31 * n + this.gravelCount;
         n = 31 * n + this.gravelMinHeight;
         n = 31 * n + this.gravelMaxHeight;
         n = 31 * n + this.graniteSize;
         n = 31 * n + this.graniteCount;
         n = 31 * n + this.graniteMinHeight;
         n = 31 * n + this.graniteMaxHeight;
         n = 31 * n + this.dioriteSize;
         n = 31 * n + this.dioriteCount;
         n = 31 * n + this.dioriteMinHeight;
         n = 31 * n + this.dioriteMaxHeight;
         n = 31 * n + this.andesiteSize;
         n = 31 * n + this.andesiteCount;
         n = 31 * n + this.andesiteMinHeight;
         n = 31 * n + this.andesiteMaxHeight;
         n = 31 * n + this.coalSize;
         n = 31 * n + this.coalCount;
         n = 31 * n + this.coalMinHeight;
         n = 31 * n + this.coalMaxHeight;
         n = 31 * n + this.ironSize;
         n = 31 * n + this.ironCount;
         n = 31 * n + this.ironMinHeight;
         n = 31 * n + this.ironMaxHeight;
         n = 31 * n + this.goldSize;
         n = 31 * n + this.goldCount;
         n = 31 * n + this.goldMinHeight;
         n = 31 * n + this.goldMaxHeight;
         n = 31 * n + this.redstoneSize;
         n = 31 * n + this.redstoneCount;
         n = 31 * n + this.redstoneMinHeight;
         n = 31 * n + this.redstoneMaxHeight;
         n = 31 * n + this.diamondSize;
         n = 31 * n + this.diamondCount;
         n = 31 * n + this.diamondMinHeight;
         n = 31 * n + this.diamondMaxHeight;
         n = 31 * n + this.lapisSize;
         n = 31 * n + this.lapisCount;
         n = 31 * n + this.lapisCenterHeight;
         return 31 * n + this.lapisSpread;
      }

      public ChunkProviderSettings build() {
         return new ChunkProviderSettings(this, null);
      }

      @Override
      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj != null && this.getClass() == obj.getClass()) {
            Factory factory = (Factory)obj;
            return this.andesiteCount != factory.andesiteCount
               ? false
               : (
                  this.andesiteMaxHeight != factory.andesiteMaxHeight
                     ? false
                     : (
                        this.andesiteMinHeight != factory.andesiteMinHeight
                           ? false
                           : (
                              this.andesiteSize != factory.andesiteSize
                                 ? false
                                 : (
                                    Float.compare(factory.baseSize, this.baseSize) != 0
                                       ? false
                                       : (
                                          Float.compare(factory.biomeDepthOffset, this.biomeDepthOffset) != 0
                                             ? false
                                             : (
                                                Float.compare(factory.biomeDepthWeight, this.biomeDepthWeight) != 0
                                                   ? false
                                                   : (
                                                      Float.compare(factory.biomeScaleOffset, this.biomeScaleOffset) != 0
                                                         ? false
                                                         : (
                                                            Float.compare(factory.biomeScaleWeight, this.biomeScaleWeight) != 0
                                                               ? false
                                                               : (
                                                                  this.biomeSize != factory.biomeSize
                                                                     ? false
                                                                     : (
                                                                        this.coalCount != factory.coalCount
                                                                           ? false
                                                                           : (
                                                                              this.coalMaxHeight != factory.coalMaxHeight
                                                                                 ? false
                                                                                 : (
                                                                                    this.coalMinHeight != factory.coalMinHeight
                                                                                       ? false
                                                                                       : (
                                                                                          this.coalSize != factory.coalSize
                                                                                             ? false
                                                                                             : (
                                                                                                Float.compare(factory.coordinateScale, this.coordinateScale) != 0
                                                                                                   ? false
                                                                                                   : (
                                                                                                      Float.compare(
                                                                                                               factory.depthNoiseScaleExponent,
                                                                                                               this.depthNoiseScaleExponent
                                                                                                            )
                                                                                                            != 0
                                                                                                         ? false
                                                                                                         : (
                                                                                                            Float.compare(
                                                                                                                     factory.depthNoiseScaleX, this.depthNoiseScaleX
                                                                                                                  )
                                                                                                                  != 0
                                                                                                               ? false
                                                                                                               : (
                                                                                                                  Float.compare(
                                                                                                                           factory.depthNoiseScaleZ,
                                                                                                                           this.depthNoiseScaleZ
                                                                                                                        )
                                                                                                                        != 0
                                                                                                                     ? false
                                                                                                                     : (
                                                                                                                        this.diamondCount != factory.diamondCount
                                                                                                                           ? false
                                                                                                                           : (
                                                                                                                              this.diamondMaxHeight
                                                                                                                                    != factory.diamondMaxHeight
                                                                                                                                 ? false
                                                                                                                                 : (
                                                                                                                                    this.diamondMinHeight
                                                                                                                                          != factory.diamondMinHeight
                                                                                                                                       ? false
                                                                                                                                       : (
                                                                                                                                          this.diamondSize
                                                                                                                                                != factory.diamondSize
                                                                                                                                             ? false
                                                                                                                                             : (
                                                                                                                                                this.dioriteCount
                                                                                                                                                      != factory.dioriteCount
                                                                                                                                                   ? false
                                                                                                                                                   : (
                                                                                                                                                      this.dioriteMaxHeight
                                                                                                                                                            != factory.dioriteMaxHeight
                                                                                                                                                         ? false
                                                                                                                                                         : (
                                                                                                                                                            this.dioriteMinHeight
                                                                                                                                                                  != factory.dioriteMinHeight
                                                                                                                                                               ? false
                                                                                                                                                               : (
                                                                                                                                                                  this.dioriteSize
                                                                                                                                                                        != factory.dioriteSize
                                                                                                                                                                     ? false
                                                                                                                                                                     : (
                                                                                                                                                                        this.dirtCount
                                                                                                                                                                              != factory.dirtCount
                                                                                                                                                                           ? false
                                                                                                                                                                           : (
                                                                                                                                                                              this.dirtMaxHeight
                                                                                                                                                                                    != factory.dirtMaxHeight
                                                                                                                                                                                 ? false
                                                                                                                                                                                 : (
                                                                                                                                                                                    this.dirtMinHeight
                                                                                                                                                                                          != factory.dirtMinHeight
                                                                                                                                                                                       ? false
                                                                                                                                                                                       : (
                                                                                                                                                                                          this.dirtSize
                                                                                                                                                                                                != factory.dirtSize
                                                                                                                                                                                             ? false
                                                                                                                                                                                             : (
                                                                                                                                                                                                this.dungeonChance
                                                                                                                                                                                                      != factory.dungeonChance
                                                                                                                                                                                                   ? false
                                                                                                                                                                                                   : (
                                                                                                                                                                                                      this.fixedBiome
                                                                                                                                                                                                            != factory.fixedBiome
                                                                                                                                                                                                         ? false
                                                                                                                                                                                                         : (
                                                                                                                                                                                                            this.goldCount
                                                                                                                                                                                                                  != factory.goldCount
                                                                                                                                                                                                               ? false
                                                                                                                                                                                                               : (
                                                                                                                                                                                                                  this.goldMaxHeight
                                                                                                                                                                                                                        != factory.goldMaxHeight
                                                                                                                                                                                                                     ? false
                                                                                                                                                                                                                     : (
                                                                                                                                                                                                                        this.goldMinHeight
                                                                                                                                                                                                                              != factory.goldMinHeight
                                                                                                                                                                                                                           ? false
                                                                                                                                                                                                                           : (
                                                                                                                                                                                                                              this.goldSize
                                                                                                                                                                                                                                    != factory.goldSize
                                                                                                                                                                                                                                 ? false
                                                                                                                                                                                                                                 : (
                                                                                                                                                                                                                                    this.graniteCount
                                                                                                                                                                                                                                          != factory.graniteCount
                                                                                                                                                                                                                                       ? false
                                                                                                                                                                                                                                       : (
                                                                                                                                                                                                                                          this.graniteMaxHeight
                                                                                                                                                                                                                                                != factory.graniteMaxHeight
                                                                                                                                                                                                                                             ? false
                                                                                                                                                                                                                                             : (
                                                                                                                                                                                                                                                this.graniteMinHeight
                                                                                                                                                                                                                                                      != factory.graniteMinHeight
                                                                                                                                                                                                                                                   ? false
                                                                                                                                                                                                                                                   : (
                                                                                                                                                                                                                                                      this.graniteSize
                                                                                                                                                                                                                                                            != factory.graniteSize
                                                                                                                                                                                                                                                         ? false
                                                                                                                                                                                                                                                         : (
                                                                                                                                                                                                                                                            this.gravelCount
                                                                                                                                                                                                                                                                  != factory.gravelCount
                                                                                                                                                                                                                                                               ? false
                                                                                                                                                                                                                                                               : (
                                                                                                                                                                                                                                                                  this.gravelMaxHeight
                                                                                                                                                                                                                                                                        != factory.gravelMaxHeight
                                                                                                                                                                                                                                                                     ? false
                                                                                                                                                                                                                                                                     : (
                                                                                                                                                                                                                                                                        this.gravelMinHeight
                                                                                                                                                                                                                                                                              != factory.gravelMinHeight
                                                                                                                                                                                                                                                                           ? false
                                                                                                                                                                                                                                                                           : (
                                                                                                                                                                                                                                                                              this.gravelSize
                                                                                                                                                                                                                                                                                    != factory.gravelSize
                                                                                                                                                                                                                                                                                 ? false
                                                                                                                                                                                                                                                                                 : (
                                                                                                                                                                                                                                                                                    Float.compare(
                                                                                                                                                                                                                                                                                             factory.heightScale,
                                                                                                                                                                                                                                                                                             this.heightScale
                                                                                                                                                                                                                                                                                          )
                                                                                                                                                                                                                                                                                          != 0
                                                                                                                                                                                                                                                                                       ? false
                                                                                                                                                                                                                                                                                       : (
                                                                                                                                                                                                                                                                                          this.ironCount
                                                                                                                                                                                                                                                                                                != factory.ironCount
                                                                                                                                                                                                                                                                                             ? false
                                                                                                                                                                                                                                                                                             : (
                                                                                                                                                                                                                                                                                                this.ironMaxHeight
                                                                                                                                                                                                                                                                                                      != factory.ironMaxHeight
                                                                                                                                                                                                                                                                                                   ? false
                                                                                                                                                                                                                                                                                                   : (
                                                                                                                                                                                                                                                                                                      this.ironMinHeight
                                                                                                                                                                                                                                                                                                            != factory.ironMinHeight
                                                                                                                                                                                                                                                                                                         ? false
                                                                                                                                                                                                                                                                                                         : (
                                                                                                                                                                                                                                                                                                            this.ironSize
                                                                                                                                                                                                                                                                                                                  != factory.ironSize
                                                                                                                                                                                                                                                                                                               ? false
                                                                                                                                                                                                                                                                                                               : (
                                                                                                                                                                                                                                                                                                                  this.lapisCenterHeight
                                                                                                                                                                                                                                                                                                                        != factory.lapisCenterHeight
                                                                                                                                                                                                                                                                                                                     ? false
                                                                                                                                                                                                                                                                                                                     : (
                                                                                                                                                                                                                                                                                                                        this.lapisCount
                                                                                                                                                                                                                                                                                                                              != factory.lapisCount
                                                                                                                                                                                                                                                                                                                           ? false
                                                                                                                                                                                                                                                                                                                           : (
                                                                                                                                                                                                                                                                                                                              this.lapisSize
                                                                                                                                                                                                                                                                                                                                    != factory.lapisSize
                                                                                                                                                                                                                                                                                                                                 ? false
                                                                                                                                                                                                                                                                                                                                 : (
                                                                                                                                                                                                                                                                                                                                    this.lapisSpread
                                                                                                                                                                                                                                                                                                                                          != factory.lapisSpread
                                                                                                                                                                                                                                                                                                                                       ? false
                                                                                                                                                                                                                                                                                                                                       : (
                                                                                                                                                                                                                                                                                                                                          this.lavaLakeChance
                                                                                                                                                                                                                                                                                                                                                != factory.lavaLakeChance
                                                                                                                                                                                                                                                                                                                                             ? false
                                                                                                                                                                                                                                                                                                                                             : (
                                                                                                                                                                                                                                                                                                                                                Float.compare(
                                                                                                                                                                                                                                                                                                                                                         factory.lowerLimitScale,
                                                                                                                                                                                                                                                                                                                                                         this.lowerLimitScale
                                                                                                                                                                                                                                                                                                                                                      )
                                                                                                                                                                                                                                                                                                                                                      != 0
                                                                                                                                                                                                                                                                                                                                                   ? false
                                                                                                                                                                                                                                                                                                                                                   : (
                                                                                                                                                                                                                                                                                                                                                      Float.compare(
                                                                                                                                                                                                                                                                                                                                                               factory.mainNoiseScaleX,
                                                                                                                                                                                                                                                                                                                                                               this.mainNoiseScaleX
                                                                                                                                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                                                                                                                                            != 0
                                                                                                                                                                                                                                                                                                                                                         ? false
                                                                                                                                                                                                                                                                                                                                                         : (
                                                                                                                                                                                                                                                                                                                                                            Float.compare(
                                                                                                                                                                                                                                                                                                                                                                     factory.mainNoiseScaleY,
                                                                                                                                                                                                                                                                                                                                                                     this.mainNoiseScaleY
                                                                                                                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                                                                                                                                  != 0
                                                                                                                                                                                                                                                                                                                                                               ? false
                                                                                                                                                                                                                                                                                                                                                               : (
                                                                                                                                                                                                                                                                                                                                                                  Float.compare(
                                                                                                                                                                                                                                                                                                                                                                           factory.mainNoiseScaleZ,
                                                                                                                                                                                                                                                                                                                                                                           this.mainNoiseScaleZ
                                                                                                                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                                                                                                                        != 0
                                                                                                                                                                                                                                                                                                                                                                     ? false
                                                                                                                                                                                                                                                                                                                                                                     : (
                                                                                                                                                                                                                                                                                                                                                                        this.redstoneCount
                                                                                                                                                                                                                                                                                                                                                                              != factory.redstoneCount
                                                                                                                                                                                                                                                                                                                                                                           ? false
                                                                                                                                                                                                                                                                                                                                                                           : (
                                                                                                                                                                                                                                                                                                                                                                              this.redstoneMaxHeight
                                                                                                                                                                                                                                                                                                                                                                                    != factory.redstoneMaxHeight
                                                                                                                                                                                                                                                                                                                                                                                 ? false
                                                                                                                                                                                                                                                                                                                                                                                 : (
                                                                                                                                                                                                                                                                                                                                                                                    this.redstoneMinHeight
                                                                                                                                                                                                                                                                                                                                                                                          != factory.redstoneMinHeight
                                                                                                                                                                                                                                                                                                                                                                                       ? false
                                                                                                                                                                                                                                                                                                                                                                                       : (
                                                                                                                                                                                                                                                                                                                                                                                          this.redstoneSize
                                                                                                                                                                                                                                                                                                                                                                                                != factory.redstoneSize
                                                                                                                                                                                                                                                                                                                                                                                             ? false
                                                                                                                                                                                                                                                                                                                                                                                             : (
                                                                                                                                                                                                                                                                                                                                                                                                this.riverSize
                                                                                                                                                                                                                                                                                                                                                                                                      != factory.riverSize
                                                                                                                                                                                                                                                                                                                                                                                                   ? false
                                                                                                                                                                                                                                                                                                                                                                                                   : (
                                                                                                                                                                                                                                                                                                                                                                                                      this.seaLevel
                                                                                                                                                                                                                                                                                                                                                                                                            != factory.seaLevel
                                                                                                                                                                                                                                                                                                                                                                                                         ? false
                                                                                                                                                                                                                                                                                                                                                                                                         : (
                                                                                                                                                                                                                                                                                                                                                                                                            Float.compare(
                                                                                                                                                                                                                                                                                                                                                                                                                     factory.stretchY,
                                                                                                                                                                                                                                                                                                                                                                                                                     this.stretchY
                                                                                                                                                                                                                                                                                                                                                                                                                  )
                                                                                                                                                                                                                                                                                                                                                                                                                  != 0
                                                                                                                                                                                                                                                                                                                                                                                                               ? false
                                                                                                                                                                                                                                                                                                                                                                                                               : (
                                                                                                                                                                                                                                                                                                                                                                                                                  Float.compare(
                                                                                                                                                                                                                                                                                                                                                                                                                           factory.upperLimitScale,
                                                                                                                                                                                                                                                                                                                                                                                                                           this.upperLimitScale
                                                                                                                                                                                                                                                                                                                                                                                                                        )
                                                                                                                                                                                                                                                                                                                                                                                                                        != 0
                                                                                                                                                                                                                                                                                                                                                                                                                     ? false
                                                                                                                                                                                                                                                                                                                                                                                                                     : (
                                                                                                                                                                                                                                                                                                                                                                                                                        this.useCaves
                                                                                                                                                                                                                                                                                                                                                                                                                              != factory.useCaves
                                                                                                                                                                                                                                                                                                                                                                                                                           ? false
                                                                                                                                                                                                                                                                                                                                                                                                                           : (
                                                                                                                                                                                                                                                                                                                                                                                                                              this.useDungeons
                                                                                                                                                                                                                                                                                                                                                                                                                                    != factory.useDungeons
                                                                                                                                                                                                                                                                                                                                                                                                                                 ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                 : (
                                                                                                                                                                                                                                                                                                                                                                                                                                    this.useLavaLakes
                                                                                                                                                                                                                                                                                                                                                                                                                                          != factory.useLavaLakes
                                                                                                                                                                                                                                                                                                                                                                                                                                       ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                       : (
                                                                                                                                                                                                                                                                                                                                                                                                                                          this.useLavaOceans
                                                                                                                                                                                                                                                                                                                                                                                                                                                != factory.useLavaOceans
                                                                                                                                                                                                                                                                                                                                                                                                                                             ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                             : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                this.useMineShafts
                                                                                                                                                                                                                                                                                                                                                                                                                                                      != factory.useMineShafts
                                                                                                                                                                                                                                                                                                                                                                                                                                                   ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                   : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                      this.useRavines
                                                                                                                                                                                                                                                                                                                                                                                                                                                            != factory.useRavines
                                                                                                                                                                                                                                                                                                                                                                                                                                                         ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                         : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                            this.useStrongholds
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  != factory.useStrongholds
                                                                                                                                                                                                                                                                                                                                                                                                                                                               ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                               : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  this.useTemples
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        != factory.useTemples
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        this.useMonuments
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              != factory.useMonuments
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              this.useVillages
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    != factory.useVillages
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 : (
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    this.useWaterLakes
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          != factory.useWaterLakes
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ? false
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       : this.waterLakeChance
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          == factory.waterLakeChance
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
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               );
         } else {
            return false;
         }
      }

      public Factory() {
         this.setDefaults();
      }

      public static Factory jsonToFactory(String str) {
         if (str.length() == 0) {
            return new Factory();
         } else {
            try {
               return (Factory)JSON_ADAPTER.fromJson(str, Factory.class);
            } catch (Exception e) {
               return new Factory();
            }
         }
      }

      public void setDefaults() {
         this.coordinateScale = 684.412F;
         this.heightScale = 684.412F;
         this.upperLimitScale = 512.0F;
         this.lowerLimitScale = 512.0F;
         this.depthNoiseScaleX = 200.0F;
         this.depthNoiseScaleZ = 200.0F;
         this.depthNoiseScaleExponent = 0.5F;
         this.mainNoiseScaleX = 80.0F;
         this.mainNoiseScaleY = 160.0F;
         this.mainNoiseScaleZ = 80.0F;
         this.baseSize = 8.5F;
         this.stretchY = 12.0F;
         this.biomeDepthWeight = 1.0F;
         this.biomeDepthOffset = 0.0F;
         this.biomeScaleWeight = 1.0F;
         this.biomeScaleOffset = 0.0F;
         this.seaLevel = 63;
         this.useCaves = true;
         this.useDungeons = true;
         this.dungeonChance = 8;
         this.useStrongholds = true;
         this.useVillages = true;
         this.useMineShafts = true;
         this.useTemples = true;
         this.useMonuments = true;
         this.useRavines = true;
         this.useWaterLakes = true;
         this.waterLakeChance = 4;
         this.useLavaLakes = true;
         this.lavaLakeChance = 80;
         this.useLavaOceans = false;
         this.fixedBiome = -1;
         this.biomeSize = 4;
         this.riverSize = 4;
         this.dirtSize = 33;
         this.dirtCount = 10;
         this.dirtMinHeight = 0;
         this.dirtMaxHeight = 256;
         this.gravelSize = 33;
         this.gravelCount = 8;
         this.gravelMinHeight = 0;
         this.gravelMaxHeight = 256;
         this.graniteSize = 33;
         this.graniteCount = 10;
         this.graniteMinHeight = 0;
         this.graniteMaxHeight = 80;
         this.dioriteSize = 33;
         this.dioriteCount = 10;
         this.dioriteMinHeight = 0;
         this.dioriteMaxHeight = 80;
         this.andesiteSize = 33;
         this.andesiteCount = 10;
         this.andesiteMinHeight = 0;
         this.andesiteMaxHeight = 80;
         this.coalSize = 17;
         this.coalCount = 20;
         this.coalMinHeight = 0;
         this.coalMaxHeight = 128;
         this.ironSize = 9;
         this.ironCount = 20;
         this.ironMinHeight = 0;
         this.ironMaxHeight = 64;
         this.goldSize = 9;
         this.goldCount = 2;
         this.goldMinHeight = 0;
         this.goldMaxHeight = 32;
         this.redstoneSize = 8;
         this.redstoneCount = 8;
         this.redstoneMinHeight = 0;
         this.redstoneMaxHeight = 16;
         this.diamondSize = 8;
         this.diamondCount = 1;
         this.diamondMinHeight = 0;
         this.diamondMaxHeight = 16;
         this.lapisSize = 7;
         this.lapisCount = 1;
         this.lapisCenterHeight = 16;
         this.lapisSpread = 16;
      }

      @Override
      public String toString() {
         return JSON_ADAPTER.toJson(this);
      }
   }

   public static class Serializer implements JsonDeserializer, JsonSerializer {

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeSettings(elem, type, context);
      }

      public JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
         return this.serialize((ChunkProviderSettings.Factory)obj, type, jsonSerializationContext);
      }

      public JsonElement serialize(ChunkProviderSettings.Factory factory, Type type, JsonSerializationContext jsonSerializationContext) {
         JsonObject json = new JsonObject();
         json.addProperty("coordinateScale", factory.coordinateScale);
         json.addProperty("heightScale", factory.heightScale);
         json.addProperty("lowerLimitScale", factory.lowerLimitScale);
         json.addProperty("upperLimitScale", factory.upperLimitScale);
         json.addProperty("depthNoiseScaleX", factory.depthNoiseScaleX);
         json.addProperty("depthNoiseScaleZ", factory.depthNoiseScaleZ);
         json.addProperty("depthNoiseScaleExponent", factory.depthNoiseScaleExponent);
         json.addProperty("mainNoiseScaleX", factory.mainNoiseScaleX);
         json.addProperty("mainNoiseScaleY", factory.mainNoiseScaleY);
         json.addProperty("mainNoiseScaleZ", factory.mainNoiseScaleZ);
         json.addProperty("baseSize", factory.baseSize);
         json.addProperty("stretchY", factory.stretchY);
         json.addProperty("biomeDepthWeight", factory.biomeDepthWeight);
         json.addProperty("biomeDepthOffset", factory.biomeDepthOffset);
         json.addProperty("biomeScaleWeight", factory.biomeScaleWeight);
         json.addProperty("biomeScaleOffset", factory.biomeScaleOffset);
         json.addProperty("seaLevel", factory.seaLevel);
         json.addProperty("useCaves", factory.useCaves);
         json.addProperty("useDungeons", factory.useDungeons);
         json.addProperty("dungeonChance", factory.dungeonChance);
         json.addProperty("useStrongholds", factory.useStrongholds);
         json.addProperty("useVillages", factory.useVillages);
         json.addProperty("useMineShafts", factory.useMineShafts);
         json.addProperty("useTemples", factory.useTemples);
         json.addProperty("useMonuments", factory.useMonuments);
         json.addProperty("useRavines", factory.useRavines);
         json.addProperty("useWaterLakes", factory.useWaterLakes);
         json.addProperty("waterLakeChance", factory.waterLakeChance);
         json.addProperty("useLavaLakes", factory.useLavaLakes);
         json.addProperty("lavaLakeChance", factory.lavaLakeChance);
         json.addProperty("useLavaOceans", factory.useLavaOceans);
         json.addProperty("fixedBiome", factory.fixedBiome);
         json.addProperty("biomeSize", factory.biomeSize);
         json.addProperty("riverSize", factory.riverSize);
         json.addProperty("dirtSize", factory.dirtSize);
         json.addProperty("dirtCount", factory.dirtCount);
         json.addProperty("dirtMinHeight", factory.dirtMinHeight);
         json.addProperty("dirtMaxHeight", factory.dirtMaxHeight);
         json.addProperty("gravelSize", factory.gravelSize);
         json.addProperty("gravelCount", factory.gravelCount);
         json.addProperty("gravelMinHeight", factory.gravelMinHeight);
         json.addProperty("gravelMaxHeight", factory.gravelMaxHeight);
         json.addProperty("graniteSize", factory.graniteSize);
         json.addProperty("graniteCount", factory.graniteCount);
         json.addProperty("graniteMinHeight", factory.graniteMinHeight);
         json.addProperty("graniteMaxHeight", factory.graniteMaxHeight);
         json.addProperty("dioriteSize", factory.dioriteSize);
         json.addProperty("dioriteCount", factory.dioriteCount);
         json.addProperty("dioriteMinHeight", factory.dioriteMinHeight);
         json.addProperty("dioriteMaxHeight", factory.dioriteMaxHeight);
         json.addProperty("andesiteSize", factory.andesiteSize);
         json.addProperty("andesiteCount", factory.andesiteCount);
         json.addProperty("andesiteMinHeight", factory.andesiteMinHeight);
         json.addProperty("andesiteMaxHeight", factory.andesiteMaxHeight);
         json.addProperty("coalSize", factory.coalSize);
         json.addProperty("coalCount", factory.coalCount);
         json.addProperty("coalMinHeight", factory.coalMinHeight);
         json.addProperty("coalMaxHeight", factory.coalMaxHeight);
         json.addProperty("ironSize", factory.ironSize);
         json.addProperty("ironCount", factory.ironCount);
         json.addProperty("ironMinHeight", factory.ironMinHeight);
         json.addProperty("ironMaxHeight", factory.ironMaxHeight);
         json.addProperty("goldSize", factory.goldSize);
         json.addProperty("goldCount", factory.goldCount);
         json.addProperty("goldMinHeight", factory.goldMinHeight);
         json.addProperty("goldMaxHeight", factory.goldMaxHeight);
         json.addProperty("redstoneSize", factory.redstoneSize);
         json.addProperty("redstoneCount", factory.redstoneCount);
         json.addProperty("redstoneMinHeight", factory.redstoneMinHeight);
         json.addProperty("redstoneMaxHeight", factory.redstoneMaxHeight);
         json.addProperty("diamondSize", factory.diamondSize);
         json.addProperty("diamondCount", factory.diamondCount);
         json.addProperty("diamondMinHeight", factory.diamondMinHeight);
         json.addProperty("diamondMaxHeight", factory.diamondMaxHeight);
         json.addProperty("lapisSize", factory.lapisSize);
         json.addProperty("lapisCount", factory.lapisCount);
         json.addProperty("lapisCenterHeight", factory.lapisCenterHeight);
         json.addProperty("lapisSpread", factory.lapisSpread);
         return json;
      }

      public ChunkProviderSettings.Factory deserializeSettings(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         JsonObject asJsonObject = elem.getAsJsonObject();
         ChunkProviderSettings.Factory factory = new ChunkProviderSettings.Factory();

         try {
            factory.coordinateScale = JsonUtils.getFloat(asJsonObject, "coordinateScale", factory.coordinateScale);
            factory.heightScale = JsonUtils.getFloat(asJsonObject, "heightScale", factory.heightScale);
            factory.lowerLimitScale = JsonUtils.getFloat(asJsonObject, "lowerLimitScale", factory.lowerLimitScale);
            factory.upperLimitScale = JsonUtils.getFloat(asJsonObject, "upperLimitScale", factory.upperLimitScale);
            factory.depthNoiseScaleX = JsonUtils.getFloat(asJsonObject, "depthNoiseScaleX", factory.depthNoiseScaleX);
            factory.depthNoiseScaleZ = JsonUtils.getFloat(asJsonObject, "depthNoiseScaleZ", factory.depthNoiseScaleZ);
            factory.depthNoiseScaleExponent = JsonUtils.getFloat(asJsonObject, "depthNoiseScaleExponent", factory.depthNoiseScaleExponent);
            factory.mainNoiseScaleX = JsonUtils.getFloat(asJsonObject, "mainNoiseScaleX", factory.mainNoiseScaleX);
            factory.mainNoiseScaleY = JsonUtils.getFloat(asJsonObject, "mainNoiseScaleY", factory.mainNoiseScaleY);
            factory.mainNoiseScaleZ = JsonUtils.getFloat(asJsonObject, "mainNoiseScaleZ", factory.mainNoiseScaleZ);
            factory.baseSize = JsonUtils.getFloat(asJsonObject, "baseSize", factory.baseSize);
            factory.stretchY = JsonUtils.getFloat(asJsonObject, "stretchY", factory.stretchY);
            factory.biomeDepthWeight = JsonUtils.getFloat(asJsonObject, "biomeDepthWeight", factory.biomeDepthWeight);
            factory.biomeDepthOffset = JsonUtils.getFloat(asJsonObject, "biomeDepthOffset", factory.biomeDepthOffset);
            factory.biomeScaleWeight = JsonUtils.getFloat(asJsonObject, "biomeScaleWeight", factory.biomeScaleWeight);
            factory.biomeScaleOffset = JsonUtils.getFloat(asJsonObject, "biomeScaleOffset", factory.biomeScaleOffset);
            factory.seaLevel = JsonUtils.getInt(asJsonObject, "seaLevel", factory.seaLevel);
            factory.useCaves = JsonUtils.getBoolean(asJsonObject, "useCaves", factory.useCaves);
            factory.useDungeons = JsonUtils.getBoolean(asJsonObject, "useDungeons", factory.useDungeons);
            factory.dungeonChance = JsonUtils.getInt(asJsonObject, "dungeonChance", factory.dungeonChance);
            factory.useStrongholds = JsonUtils.getBoolean(asJsonObject, "useStrongholds", factory.useStrongholds);
            factory.useVillages = JsonUtils.getBoolean(asJsonObject, "useVillages", factory.useVillages);
            factory.useMineShafts = JsonUtils.getBoolean(asJsonObject, "useMineShafts", factory.useMineShafts);
            factory.useTemples = JsonUtils.getBoolean(asJsonObject, "useTemples", factory.useTemples);
            factory.useMonuments = JsonUtils.getBoolean(asJsonObject, "useMonuments", factory.useMonuments);
            factory.useRavines = JsonUtils.getBoolean(asJsonObject, "useRavines", factory.useRavines);
            factory.useWaterLakes = JsonUtils.getBoolean(asJsonObject, "useWaterLakes", factory.useWaterLakes);
            factory.waterLakeChance = JsonUtils.getInt(asJsonObject, "waterLakeChance", factory.waterLakeChance);
            factory.useLavaLakes = JsonUtils.getBoolean(asJsonObject, "useLavaLakes", factory.useLavaLakes);
            factory.lavaLakeChance = JsonUtils.getInt(asJsonObject, "lavaLakeChance", factory.lavaLakeChance);
            factory.useLavaOceans = JsonUtils.getBoolean(asJsonObject, "useLavaOceans", factory.useLavaOceans);
            factory.fixedBiome = JsonUtils.getInt(asJsonObject, "fixedBiome", factory.fixedBiome);
            if (factory.fixedBiome >= 38 || factory.fixedBiome < -1) {
               factory.fixedBiome = -1;
            } else if (factory.fixedBiome >= BiomeGenBase.hell.biomeID) {
               factory.fixedBiome += 2;
            }

            factory.biomeSize = JsonUtils.getInt(asJsonObject, "biomeSize", factory.biomeSize);
            factory.riverSize = JsonUtils.getInt(asJsonObject, "riverSize", factory.riverSize);
            factory.dirtSize = JsonUtils.getInt(asJsonObject, "dirtSize", factory.dirtSize);
            factory.dirtCount = JsonUtils.getInt(asJsonObject, "dirtCount", factory.dirtCount);
            factory.dirtMinHeight = JsonUtils.getInt(asJsonObject, "dirtMinHeight", factory.dirtMinHeight);
            factory.dirtMaxHeight = JsonUtils.getInt(asJsonObject, "dirtMaxHeight", factory.dirtMaxHeight);
            factory.gravelSize = JsonUtils.getInt(asJsonObject, "gravelSize", factory.gravelSize);
            factory.gravelCount = JsonUtils.getInt(asJsonObject, "gravelCount", factory.gravelCount);
            factory.gravelMinHeight = JsonUtils.getInt(asJsonObject, "gravelMinHeight", factory.gravelMinHeight);
            factory.gravelMaxHeight = JsonUtils.getInt(asJsonObject, "gravelMaxHeight", factory.gravelMaxHeight);
            factory.graniteSize = JsonUtils.getInt(asJsonObject, "graniteSize", factory.graniteSize);
            factory.graniteCount = JsonUtils.getInt(asJsonObject, "graniteCount", factory.graniteCount);
            factory.graniteMinHeight = JsonUtils.getInt(asJsonObject, "graniteMinHeight", factory.graniteMinHeight);
            factory.graniteMaxHeight = JsonUtils.getInt(asJsonObject, "graniteMaxHeight", factory.graniteMaxHeight);
            factory.dioriteSize = JsonUtils.getInt(asJsonObject, "dioriteSize", factory.dioriteSize);
            factory.dioriteCount = JsonUtils.getInt(asJsonObject, "dioriteCount", factory.dioriteCount);
            factory.dioriteMinHeight = JsonUtils.getInt(asJsonObject, "dioriteMinHeight", factory.dioriteMinHeight);
            factory.dioriteMaxHeight = JsonUtils.getInt(asJsonObject, "dioriteMaxHeight", factory.dioriteMaxHeight);
            factory.andesiteSize = JsonUtils.getInt(asJsonObject, "andesiteSize", factory.andesiteSize);
            factory.andesiteCount = JsonUtils.getInt(asJsonObject, "andesiteCount", factory.andesiteCount);
            factory.andesiteMinHeight = JsonUtils.getInt(asJsonObject, "andesiteMinHeight", factory.andesiteMinHeight);
            factory.andesiteMaxHeight = JsonUtils.getInt(asJsonObject, "andesiteMaxHeight", factory.andesiteMaxHeight);
            factory.coalSize = JsonUtils.getInt(asJsonObject, "coalSize", factory.coalSize);
            factory.coalCount = JsonUtils.getInt(asJsonObject, "coalCount", factory.coalCount);
            factory.coalMinHeight = JsonUtils.getInt(asJsonObject, "coalMinHeight", factory.coalMinHeight);
            factory.coalMaxHeight = JsonUtils.getInt(asJsonObject, "coalMaxHeight", factory.coalMaxHeight);
            factory.ironSize = JsonUtils.getInt(asJsonObject, "ironSize", factory.ironSize);
            factory.ironCount = JsonUtils.getInt(asJsonObject, "ironCount", factory.ironCount);
            factory.ironMinHeight = JsonUtils.getInt(asJsonObject, "ironMinHeight", factory.ironMinHeight);
            factory.ironMaxHeight = JsonUtils.getInt(asJsonObject, "ironMaxHeight", factory.ironMaxHeight);
            factory.goldSize = JsonUtils.getInt(asJsonObject, "goldSize", factory.goldSize);
            factory.goldCount = JsonUtils.getInt(asJsonObject, "goldCount", factory.goldCount);
            factory.goldMinHeight = JsonUtils.getInt(asJsonObject, "goldMinHeight", factory.goldMinHeight);
            factory.goldMaxHeight = JsonUtils.getInt(asJsonObject, "goldMaxHeight", factory.goldMaxHeight);
            factory.redstoneSize = JsonUtils.getInt(asJsonObject, "redstoneSize", factory.redstoneSize);
            factory.redstoneCount = JsonUtils.getInt(asJsonObject, "redstoneCount", factory.redstoneCount);
            factory.redstoneMinHeight = JsonUtils.getInt(asJsonObject, "redstoneMinHeight", factory.redstoneMinHeight);
            factory.redstoneMaxHeight = JsonUtils.getInt(asJsonObject, "redstoneMaxHeight", factory.redstoneMaxHeight);
            factory.diamondSize = JsonUtils.getInt(asJsonObject, "diamondSize", factory.diamondSize);
            factory.diamondCount = JsonUtils.getInt(asJsonObject, "diamondCount", factory.diamondCount);
            factory.diamondMinHeight = JsonUtils.getInt(asJsonObject, "diamondMinHeight", factory.diamondMinHeight);
            factory.diamondMaxHeight = JsonUtils.getInt(asJsonObject, "diamondMaxHeight", factory.diamondMaxHeight);
            factory.lapisSize = JsonUtils.getInt(asJsonObject, "lapisSize", factory.lapisSize);
            factory.lapisCount = JsonUtils.getInt(asJsonObject, "lapisCount", factory.lapisCount);
            factory.lapisCenterHeight = JsonUtils.getInt(asJsonObject, "lapisCenterHeight", factory.lapisCenterHeight);
            factory.lapisSpread = JsonUtils.getInt(asJsonObject, "lapisSpread", factory.lapisSpread);
         } catch (Exception e) {
         }

         return factory;
      }
   }
}
