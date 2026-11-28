package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderDebug implements IChunkProvider {
   private static final List ALL_VALID_STATES = Lists.newArrayList();
   private static final int GRID_WIDTH;
   private static final int GRID_HEIGHT;
   private final World world;

   @Override
   public Chunk provideChunk(BlockPos pos) {
      return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
   }

   @Override
   public List getPossibleCreatures(EnumCreatureType enumCreatureType, BlockPos pos) {
      return this.world.getBiomeGenForCoords(pos).getSpawnableList(enumCreatureType);
   }

   @Override
   public boolean chunkExists(int n, int n2) {
      return true;
   }

   @Override
   public int getLoadedChunkCount() {
      return 0;
   }

   @Override
   public void populate(IChunkProvider chunkProvider, int n, int n2) {
   }

   @Override
   public String makeString() {
      return "DebugLevelSource";
   }

   @Override
   public boolean unloadQueuedChunks() {
      return false;
   }

   @Override
   public Chunk provideChunk(int n, int n2) {
      ChunkPrimer chunkPrimer = new ChunkPrimer();

      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            int n3 = n * 16 + i;
            int n4 = n2 * 16 + j;
            chunkPrimer.setBlockState(i, 60, j, Blocks.barrier.getDefaultState());
            IBlockState blockStateFor = getBlockStateFor(n3, n4);
            if (blockStateFor != null) {
               chunkPrimer.setBlockState(i, 70, j, blockStateFor);
            }
         }
      }

      Chunk chunk = new Chunk(this.world, chunkPrimer, n, n2);
      chunk.generateSkylightMap();
      BiomeGenBase[] biomeGenBases = this.world.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, n * 16, n2 * 16, 16, 16);
      byte[] biomeArray = chunk.getBiomeArray();

      for (int k = 0; k < biomeArray.length; k++) {
         biomeArray[k] = (byte)biomeGenBases[k].biomeID;
      }

      chunk.generateSkylightMap();
      return chunk;
   }

   public ChunkProviderDebug(World world2) {
      this.world = world2;
   }

   @Override
   public void saveExtraData() {
   }

   @Override
   public BlockPos getStrongholdGen(World world2, String str, BlockPos pos) {
      return null;
   }

   @Override
   public boolean saveChunks(boolean flag, IProgressUpdate progressUpdate) {
      return true;
   }

   static {
      for (Block block : (Iterable<Block>)(Block.blockRegistry)) {
         ALL_VALID_STATES.addAll(block.getBlockState().getValidStates());
      }

      GRID_WIDTH = MathHelper.ceiling_float_int(MathHelper.sqrt_float((float)ALL_VALID_STATES.size()));
      GRID_HEIGHT = MathHelper.ceiling_float_int((float)ALL_VALID_STATES.size() / (float)GRID_WIDTH);
   }

   @Override
   public void recreateStructures(Chunk chunk, int n, int n2) {
   }

   @Override
   public boolean canSave() {
      return true;
   }

   @Override
   public boolean populateChunk(IChunkProvider chunkProvider, Chunk chunk, int n, int n2) {
      return false;
   }

   public static IBlockState getBlockStateFor(int n, int n2) {
      IBlockState state = null;
      if (n > 0 && n2 > 0 && n % 2 != 0 && n2 % 2 != 0) {
         n /= 2;
         n2 /= 2;
         if (n <= GRID_WIDTH && n2 <= GRID_HEIGHT) {
            int n3 = MathHelper.abs_int(n * GRID_WIDTH + n2);
            if (n3 < ALL_VALID_STATES.size()) {
               state = (IBlockState)ALL_VALID_STATES.get(n3);
            }
         }
      }

      return state;
   }
}
