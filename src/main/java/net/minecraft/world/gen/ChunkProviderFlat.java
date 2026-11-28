package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;

public class ChunkProviderFlat implements IChunkProvider {
   private World worldObj;
   private Random random;
   private final IBlockState[] cachedBlockIDs = new IBlockState[256];
   private final FlatGeneratorInfo flatWorldGenInfo;
   private final List structureGenerators = Lists.newArrayList();
   private final boolean hasDecoration;
   private final boolean hasDungeons;
   private WorldGenLakes waterLakeGenerator;
   private WorldGenLakes lavaLakeGenerator;

   @Override
   public boolean chunkExists(int n, int n2) {
      return true;
   }

   @Override
   public int getLoadedChunkCount() {
      return 0;
   }

   @Override
   public void saveExtraData() {
   }

   @Override
   public boolean saveChunks(boolean flag, IProgressUpdate progressUpdate) {
      return true;
   }

   @Override
   public void recreateStructures(Chunk chunk, int n, int n2) {
      for (MapGenStructure mapGenStructure : (Iterable<MapGenStructure>)(this.structureGenerators)) {
         mapGenStructure.generate(this, this.worldObj, n, n2, (ChunkPrimer)null);
      }
   }

   @Override
   public BlockPos getStrongholdGen(World world, String str, BlockPos pos) {
      if ("Stronghold".equals(str)) {
         for (MapGenStructure mapGenStructure : (Iterable<MapGenStructure>)(this.structureGenerators)) {
            if (mapGenStructure instanceof MapGenStronghold) {
               return mapGenStructure.getClosestStrongholdPos(world, pos);
            }
         }
      }

      return null;
   }

   @Override
   public Chunk provideChunk(BlockPos pos) {
      return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
   }

   public ChunkProviderFlat(World world, long l, boolean flag, String str) {
      this.worldObj = world;
      this.random = new Random(l);
      this.flatWorldGenInfo = FlatGeneratorInfo.createFlatGeneratorFromString(str);
      if (flag) {
         Map worldFeatures = this.flatWorldGenInfo.getWorldFeatures();
         if (worldFeatures.containsKey("village")) {
            Map map = (Map)worldFeatures.get("village");
            if (!map.containsKey("size")) {
               map.put("size", "1");
            }

            this.structureGenerators.add(new MapGenVillage(map));
         }

         if (worldFeatures.containsKey("biome_1")) {
            this.structureGenerators.add(new MapGenScatteredFeature((Map)worldFeatures.get("biome_1")));
         }

         if (worldFeatures.containsKey("mineshaft")) {
            this.structureGenerators.add(new MapGenMineshaft((Map)worldFeatures.get("mineshaft")));
         }

         if (worldFeatures.containsKey("stronghold")) {
            this.structureGenerators.add(new MapGenStronghold((Map)worldFeatures.get("stronghold")));
         }

         if (worldFeatures.containsKey("oceanmonument")) {
            this.structureGenerators.add(new StructureOceanMonument((Map)worldFeatures.get("oceanmonument")));
         }
      }

      if (this.flatWorldGenInfo.getWorldFeatures().containsKey("lake")) {
         this.waterLakeGenerator = new WorldGenLakes(Blocks.water);
      }

      if (this.flatWorldGenInfo.getWorldFeatures().containsKey("lava_lake")) {
         this.lavaLakeGenerator = new WorldGenLakes(Blocks.lava);
      }

      this.hasDungeons = this.flatWorldGenInfo.getWorldFeatures().containsKey("dungeon");
      int n = 0;
      int n2 = 0;
      boolean flag2 = true;

      for (FlatLayerInfo flatLayerInfo : (Iterable<FlatLayerInfo>)(this.flatWorldGenInfo.getFlatLayers())) {
         for (int i = flatLayerInfo.getMinY(); i < flatLayerInfo.getMinY() + flatLayerInfo.getLayerCount(); i++) {
            IBlockState layerMaterial = flatLayerInfo.getLayerMaterial();
            if (layerMaterial.getBlock() != Blocks.air) {
               flag2 = false;
               this.cachedBlockIDs[i] = layerMaterial;
            }
         }

         if (flatLayerInfo.getLayerMaterial().getBlock() == Blocks.air) {
            n2 += flatLayerInfo.getLayerCount();
         } else {
            n += flatLayerInfo.getLayerCount() + n2;
            n2 = 0;
         }
      }

      world.setSeaLevel(n);
      this.hasDecoration = flag2 ? false : this.flatWorldGenInfo.getWorldFeatures().containsKey("decoration");
   }

   @Override
   public Chunk provideChunk(int n, int n2) {
      ChunkPrimer chunkPrimer = new ChunkPrimer();

      for (int i = 0; i < this.cachedBlockIDs.length; i++) {
         IBlockState state = this.cachedBlockIDs[i];
         if (state != null) {
            for (int j = 0; j < 16; j++) {
               for (int k = 0; k < 16; k++) {
                  chunkPrimer.setBlockState(j, i, k, state);
               }
            }
         }
      }

      for (MapGenBase mapGenBase : (Iterable<MapGenBase>)(this.structureGenerators)) {
         mapGenBase.generate(this, this.worldObj, n, n2, chunkPrimer);
      }

      Chunk chunk = new Chunk(this.worldObj, chunkPrimer, n, n2);
      BiomeGenBase[] biomeGenBases = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, n * 16, n2 * 16, 16, 16);
      byte[] biomeArray = chunk.getBiomeArray();

      for (int m = 0; m < biomeArray.length; m++) {
         biomeArray[m] = (byte)biomeGenBases[m].biomeID;
      }

      chunk.generateSkylightMap();
      return chunk;
   }

   @Override
   public boolean canSave() {
      return true;
   }

   @Override
   public List getPossibleCreatures(EnumCreatureType enumCreatureType, BlockPos pos) {
      return this.worldObj.getBiomeGenForCoords(pos).getSpawnableList(enumCreatureType);
   }

   @Override
   public String makeString() {
      return "FlatLevelSource";
   }

   @Override
   public void populate(IChunkProvider chunkProvider, int n, int n2) {
      int n3 = n * 16;
      int n4 = n2 * 16;
      BlockPos pos = new BlockPos(n3, 0, n4);
      BiomeGenBase biomeGenForCoords = this.worldObj.getBiomeGenForCoords(new BlockPos(n3 + 16, 0, n4 + 16));
      boolean flag = false;
      this.random.setSeed(this.worldObj.getSeed());
      long l = this.random.nextLong() / 2L * 2L + 1L;
      long l2 = this.random.nextLong() / 2L * 2L + 1L;
      this.random.setSeed((long)n * l + (long)n2 * l2 ^ this.worldObj.getSeed());
      ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(n, n2);

      for (MapGenStructure mapGenStructure : (Iterable<MapGenStructure>)(this.structureGenerators)) {
         boolean flag2 = mapGenStructure.generateStructure(this.worldObj, this.random, chunkCoordIntPair);
         if (mapGenStructure instanceof MapGenVillage) {
            flag |= flag2;
         }
      }

      if (this.waterLakeGenerator != null && !flag && this.random.nextInt(4) == 0) {
         this.waterLakeGenerator
            .generate(this.worldObj, this.random, pos.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
      }

      if (this.lavaLakeGenerator != null && !flag && this.random.nextInt(8) == 0) {
         BlockPos pos2 = pos.add(this.random.nextInt(16) + 8, this.random.nextInt(this.random.nextInt(248) + 8), this.random.nextInt(16) + 8);
         if (pos2.getY() < this.worldObj.getSeaLevel() || this.random.nextInt(10) == 0) {
            this.lavaLakeGenerator.generate(this.worldObj, this.random, pos2);
         }
      }

      if (this.hasDungeons) {
         for (int i = 0; i < 8; i++) {
            new WorldGenDungeons()
               .generate(this.worldObj, this.random, pos.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
         }
      }

      if (this.hasDecoration) {
         biomeGenForCoords.decorate(this.worldObj, this.random, pos);
      }
   }

   @Override
   public boolean unloadQueuedChunks() {
      return false;
   }

   @Override
   public boolean populateChunk(IChunkProvider chunkProvider, Chunk chunk, int n, int n2) {
      return false;
   }
}
