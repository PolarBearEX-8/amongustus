package net.optifine.override;

import java.util.Arrays;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.DynamicLights;
import net.optifine.config.Config;
import net.optifine.util.ArrayCache;

public class ChunkCacheOF implements IBlockAccess {
   private final ChunkCache chunkCache;
   private final int posX;
   private final int posY;
   private final int posZ;
   private final int sizeX;
   private final int sizeY;
   private final int sizeZ;
   private final int sizeXY;
   private int[] combinedLights;
   private IBlockState[] blockStates;
   private final int arraySize;
   private final boolean dynamicLights = Config.isDynamicLights();
   private static final ArrayCache cacheCombinedLights = new ArrayCache(int.class, 16);
   private static final ArrayCache cacheBlockStates = new ArrayCache(IBlockState.class, 16);

   private int getPositionIndex(int n, int n2, int n3) {
      int n4 = n - this.posX;
      if (n4 >= 0 && n4 < this.sizeX) {
         int n5 = n2 - this.posY;
         if (n5 >= 0 && n5 < this.sizeY) {
            int n6 = n3 - this.posZ;
            return n6 >= 0 && n6 < this.sizeZ ? n6 * this.sizeXY + n5 * this.sizeX + n4 : -1;
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }

   @Override
   public WorldType getWorldType() {
      return this.chunkCache.getWorldType();
   }

   @Override
   public BiomeGenBase getBiomeGenForCoords(BlockPos pos) {
      return this.chunkCache.getBiomeGenForCoords(pos);
   }

   public int getCombinedLight(BlockPos pos, int n) {
      return this.getCombinedLight(pos.getX(), pos.getY(), pos.getZ(), n);
   }

   public boolean isSideSolid(BlockPos pos, EnumFacing facing, boolean flag) {
      return false;
   }

   private int getCombinedLightRaw(int n, int n2, int n3, int n4) {
      int combinedLight = this.chunkCache.getCombinedLight(n, n2, n3, n4);
      if (this.dynamicLights && !this.getBlockState2(n, n2, n3).getBlock().isOpaqueCube()) {
         combinedLight = DynamicLights.getCombinedLight(n, n2, n3, combinedLight);
      }

      return combinedLight;
   }

   @Override
   public TileEntity getTileEntity(BlockPos pos) {
      return this.chunkCache.getTileEntity(pos);
   }

   @Override
   public IBlockState getBlockState(double d, double d2, double d3) {
      return this.getBlockState2(MathHelper.floor_double(d), MathHelper.floor_double(d2), MathHelper.floor_double(d3));
   }

   @Override
   public boolean extendedLevelsInChunkCache() {
      return this.chunkCache.extendedLevelsInChunkCache();
   }

   @Override
   public IBlockState getBlockState2(int n, int n2, int n3) {
      int positionIndex = this.getPositionIndex(n, n2, n3);
      if (positionIndex >= 0 && positionIndex < this.arraySize && this.blockStates != null) {
         IBlockState state = this.blockStates[positionIndex];
         if (state == null) {
            state = this.chunkCache.getBlockState2(n, n2, n3);
            this.blockStates[positionIndex] = state;
         }

         return state;
      } else {
         return this.chunkCache.getBlockState2(n, n2, n3);
      }
   }

   @Override
   public IBlockState getBlockState(BlockPos pos) {
      return this.getBlockState2(pos.getX(), pos.getY(), pos.getZ());
   }

   @Override
   public int getCombinedLight(int n, int n2, int n3, int n4) {
      int positionIndex = this.getPositionIndex(n, n2, n3);
      if (positionIndex >= 0 && positionIndex < this.arraySize && this.combinedLights != null) {
         int n5 = this.combinedLights[positionIndex];
         if (n5 == -1) {
            n5 = this.getCombinedLightRaw(n, n2, n3, n4);
            this.combinedLights[positionIndex] = n5;
         }

         return n5;
      } else {
         return this.getCombinedLightRaw(n, n2, n3, n4);
      }
   }

   public ChunkCacheOF(ChunkCache chunkCache2, BlockPos pos, BlockPos pos2, int n) {
      this.chunkCache = chunkCache2;
      int n2 = pos.getX() - n >> 4;
      int n3 = pos.getY() - n >> 4;
      int n4 = pos.getZ() - n >> 4;
      int n5 = pos2.getX() + n >> 4;
      int n6 = pos2.getY() + n >> 4;
      int n7 = pos2.getZ() + n >> 4;
      this.sizeX = n5 - n2 + 1 << 4;
      this.sizeY = n6 - n3 + 1 << 4;
      this.sizeZ = n7 - n4 + 1 << 4;
      this.sizeXY = this.sizeX * this.sizeY;
      this.arraySize = this.sizeX * this.sizeY * this.sizeZ;
      this.posX = n2 << 4;
      this.posY = n3 << 4;
      this.posZ = n4 << 4;
   }

   public void renderFinish() {
      cacheCombinedLights.free(this.combinedLights);
      this.combinedLights = null;
      cacheBlockStates.free(this.blockStates);
      this.blockStates = null;
   }

   @Override
   public boolean isAirBlock(BlockPos pos) {
      return this.isAirBlock(pos.getX(), pos.getY(), pos.getZ());
   }

   public void renderStart() {
      if (this.combinedLights == null) {
         this.combinedLights = (int[])cacheCombinedLights.allocate(this.arraySize);
      }

      Arrays.fill(this.combinedLights, -1);
      if (this.blockStates == null) {
         this.blockStates = (IBlockState[])cacheBlockStates.allocate(this.arraySize);
      }

      Arrays.fill(this.blockStates, null);
   }

   @Override
   public int getStrongPower(BlockPos pos, EnumFacing facing) {
      return this.chunkCache.getStrongPower(pos, facing);
   }

   private int getCombinedLightRaw(BlockPos pos, int n) {
      return this.getCombinedLightRaw(pos.getX(), pos.getY(), pos.getZ(), n);
   }

   @Override
   public boolean isAirBlock(int n, int n2, int n3) {
      return this.chunkCache.isAirBlock(n, n2, n3);
   }
}
