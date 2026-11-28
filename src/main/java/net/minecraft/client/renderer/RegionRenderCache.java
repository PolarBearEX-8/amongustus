package net.minecraft.client.renderer;

import net.minecraft.world.chunk.Chunk;
import java.util.ArrayDeque;
import java.util.Arrays;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.optifine.DynamicLights;
import net.optifine.config.Config;

public class RegionRenderCache extends ChunkCache {
   private static final IBlockState DEFAULT_STATE = Blocks.air.getDefaultState();
   private final int positionX;
   private final int positionY;
   private final int positionZ;
   private int[] combinedLights;
   private IBlockState[] blockStates;
   private static ArrayDeque cacheLights = new ArrayDeque();
   private static ArrayDeque cacheStates = new ArrayDeque();
   private static int maxCacheSize = Config.limit(Runtime.getRuntime().availableProcessors(), 1, 32);

   private IBlockState getBlockStateRaw(int n, int n2, int n3) {
      if (n2 >= 0 && n2 < 256) {
         int n4 = (n >> 4) - this.chunkX;
         int n5 = (n3 >> 4) - this.chunkZ;
         return this.chunkArray[n4][n5].getBlockState(n, n2, n3);
      } else {
         return DEFAULT_STATE;
      }
   }

   private static int[] allocateLights(int n) {
      synchronized (cacheLights) {
         int[] ints = (int[])cacheLights.pollLast();
         if (ints == null || ints.length < n) {
            ints = new int[n];
         }

         return ints;
      }
   }

   @Override
   public IBlockState getBlockState2(int n, int n2, int n3) {
      int positionIndex = this.getPositionIndex(n, n2, n3);
      IBlockState state = this.blockStates[positionIndex];
      if (state == null) {
         state = this.getBlockStateRaw(n, n2, n3);
         this.blockStates[positionIndex] = state;
      }

      return state;
   }

   @Override
   public int getCombinedLight(int n, int n2, int n3, int n4) {
      int positionIndex = this.getPositionIndex(n, n2, n3);
      int n5 = this.combinedLights[positionIndex];
      if (n5 == -1) {
         n5 = super.getCombinedLight(n, n2, n3, n4);
         if (Config.isDynamicLights() && !this.getBlockState2(n, n2, n3).getBlock().isOpaqueCube()) {
            n5 = DynamicLights.getCombinedLight(n, n2, n3, n5);
         }

         this.combinedLights[positionIndex] = n5;
      }

      return n5;
   }

   public static void freeLights(int[] ints) {
      synchronized (cacheLights) {
         if (cacheLights.size() < maxCacheSize) {
            cacheLights.add(ints);
         }
      }
   }

   @Override
   public IBlockState getBlockState(BlockPos pos) {
      int positionIndex = this.getPositionIndex(pos);
      IBlockState state = this.blockStates[positionIndex];
      if (state == null) {
         state = this.getBlockStateRaw(pos);
         this.blockStates[positionIndex] = state;
      }

      return state;
   }

   private int getPositionIndex(int n, int n2, int n3) {
      int n4 = n - this.positionX;
      int n5 = n2 - this.positionY;
      return n4 * 400 + (n3 - this.positionZ) * 20 + n5;
   }

   @Override
   public TileEntity getTileEntity(BlockPos pos) {
      int n = (pos.getX() >> 4) - this.chunkX;
      int n2 = (pos.getZ() >> 4) - this.chunkZ;
      return this.chunkArray[n][n2].getTileEntity(pos, Chunk.EnumCreateEntityType.QUEUED);
   }

   public void freeBuffers() {
      freeLights(this.combinedLights);
      freeStates(this.blockStates);
   }

   @Override
   public IBlockState getBlockState(double d, double d2, double d3) {
      int n = MathHelper.floor_double(d);
      int n2 = MathHelper.floor_double(d2);
      int n3 = MathHelper.floor_double(d3);
      int positionIndex = this.getPositionIndex(n, n2, n3);
      IBlockState state = this.blockStates[positionIndex];
      if (state == null) {
         state = this.getBlockStateRaw(n, n2, n3);
         this.blockStates[positionIndex] = state;
      }

      return state;
   }

   public static void freeStates(IBlockState[] states) {
      synchronized (cacheStates) {
         if (cacheStates.size() < maxCacheSize) {
            cacheStates.add(states);
         }
      }
   }

   private IBlockState getBlockStateRaw(BlockPos pos) {
      if (pos.getY() >= 0 && pos.getY() < 256) {
         int n = (pos.getX() >> 4) - this.chunkX;
         int n2 = (pos.getZ() >> 4) - this.chunkZ;
         return this.chunkArray[n][n2].getBlockState(pos);
      } else {
         return DEFAULT_STATE;
      }
   }

   private int getPositionIndex(BlockPos pos) {
      int n = pos.getX() - this.positionX;
      int n2 = pos.getY() - this.positionY;
      return n * 400 + (pos.getZ() - this.positionZ) * 20 + n2;
   }

   private static IBlockState[] allocateStates(int n) {
      synchronized (cacheStates) {
         IBlockState[] states = (IBlockState[])cacheStates.pollLast();
         if (states != null && states.length >= n) {
            Arrays.fill(states, null);
         } else {
            states = new IBlockState[n];
         }

         return states;
      }
   }

   public RegionRenderCache(World world, int n, int n2, int n3, int n4, int n5, int n6, int n7) {
      super(world, n, n2, n3, n4, n5, n6, n7);
      this.positionX = n - n7;
      this.positionY = n2 - n7;
      this.positionZ = n3 - n7;
      this.combinedLights = allocateLights(8000);
      Arrays.fill(this.combinedLights, -1);
      this.blockStates = allocateStates(8000);
   }
}
