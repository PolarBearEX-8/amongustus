package net.minecraft.world;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class ChunkCache implements IBlockAccess {
   public int chunkX;
   public int chunkZ;
   public Chunk[][] chunkArray;
   public boolean hasExtendedLevels;
   public World worldObj;

   @Override
   public boolean isAirBlock(BlockPos pos) {
      return this.getBlockState(pos).getBlock().getMaterial() == Material.air;
   }

   public int getLightFor(EnumSkyBlock enumSkyBlock, int n, int n2, int n3) {
      if (n2 >= 0 && n2 < 256) {
         int n4 = (n >> 4) - this.chunkX;
         int n5 = (n3 >> 4) - this.chunkZ;
         return this.chunkArray[n4][n5].getLightFor(enumSkyBlock, n, n2, n3);
      } else {
         return enumSkyBlock.defaultLightValue;
      }
   }

   @Override
   public int getCombinedLight(int n, int n2, int n3, int n4) {
      int lightForExt = this.getLightForExt(EnumSkyBlock.SKY, n, n2, n3);
      int lightForExt2 = this.getLightForExt(EnumSkyBlock.BLOCK, n, n2, n3);
      if (lightForExt2 < n4) {
         lightForExt2 = n4;
      }

      return lightForExt << 20 | lightForExt2 << 4;
   }

   @Override
   public TileEntity getTileEntity(BlockPos pos) {
      int n = (pos.getX() >> 4) - this.chunkX;
      int n2 = (pos.getZ() >> 4) - this.chunkZ;
      return this.chunkArray[n][n2].getTileEntity(pos, Chunk.EnumCreateEntityType.IMMEDIATE);
   }

   @Override
   public IBlockState getBlockState2(int n, int n2, int n3) {
      if (n2 >= 0 && n2 < 256) {
         int n4 = (n >> 4) - this.chunkX;
         int n5 = (n3 >> 4) - this.chunkZ;
         if (n4 >= 0 && n4 < this.chunkArray.length && n5 >= 0 && n5 < this.chunkArray[n4].length) {
            Chunk chunk = this.chunkArray[n4][n5];
            if (chunk != null) {
               return chunk.getBlockState(n, n2, n3);
            }
         }
      }

      return Blocks.air.getDefaultState();
   }

   @Override
   public IBlockState getBlockState(double d, double d2, double d3) {
      int n = MathHelper.floor_double(d);
      int n2 = MathHelper.floor_double(d2);
      int n3 = MathHelper.floor_double(d3);
      if (n2 >= 0 && n2 < 256) {
         int n4 = (n >> 4) - this.chunkX;
         int n5 = (n3 >> 4) - this.chunkZ;
         if (n4 >= 0 && n4 < this.chunkArray.length && n5 >= 0 && n5 < this.chunkArray[n4].length) {
            Chunk chunk = this.chunkArray[n4][n5];
            if (chunk != null) {
               return chunk.getBlockState(n, n2, n3);
            }
         }
      }

      return Blocks.air.getDefaultState();
   }

   @Override
   public IBlockState getBlockState(BlockPos pos) {
      if (pos.getY() >= 0 && pos.getY() < 256) {
         int n = (pos.getX() >> 4) - this.chunkX;
         int n2 = (pos.getZ() >> 4) - this.chunkZ;
         if (n >= 0 && n < this.chunkArray.length && n2 >= 0 && n2 < this.chunkArray[n].length) {
            Chunk chunk = this.chunkArray[n][n2];
            if (chunk != null) {
               return chunk.getBlockState(pos);
            }
         }
      }

      return Blocks.air.getDefaultState();
   }

   @Override
   public BiomeGenBase getBiomeGenForCoords(BlockPos pos) {
      return this.worldObj.getBiomeGenForCoords(pos);
   }

   @Override
   public boolean extendedLevelsInChunkCache() {
      return this.hasExtendedLevels;
   }

   @Override
   public WorldType getWorldType() {
      return this.worldObj.getWorldType();
   }

   private int getLightForExt(EnumSkyBlock enumSkyBlock, int n, int n2, int n3) {
      if (enumSkyBlock == EnumSkyBlock.SKY && this.worldObj.provider.getHasNoSky()) {
         return 0;
      } else if (n2 >= 0 && n2 < 256) {
         if (this.getBlockState2(n, n2, n3).getBlock().getUseNeighborBrightness()) {
            int n4 = 0;

            for (EnumFacing facing : EnumFacing.getValues()) {
               int lightFor = this.getLightFor(enumSkyBlock, n + facing.getFrontOffsetX(), n2 + facing.getFrontOffsetY(), n3 + facing.getFrontOffsetZ());
               if (lightFor > n4) {
                  n4 = lightFor;
               }

               if (n4 >= 15) {
                  return n4;
               }
            }

            return n4;
         } else {
            int n5 = (n >> 4) - this.chunkX;
            int n6 = (n3 >> 4) - this.chunkZ;
            return this.chunkArray[n5][n6].getLightFor(enumSkyBlock, n, n2, n3);
         }
      } else {
         return enumSkyBlock.defaultLightValue;
      }
   }

   @Override
   public boolean isAirBlock(int n, int n2, int n3) {
      return this.getBlockState2(n, n2, n3).getBlock().getMaterial() == Material.air;
   }

   public ChunkCache(World world, int n, int n2, int n3, int n4, int n5, int n6, int n7) {
      this.worldObj = world;
      this.chunkX = n - n7 >> 4;
      this.chunkZ = n3 - n7 >> 4;
      int n8 = n4 + n7 >> 4;
      int n9 = n6 + n7 >> 4;
      this.chunkArray = new Chunk[n8 - this.chunkX + 1][n9 - this.chunkZ + 1];
      this.hasExtendedLevels = true;

      for (int i = this.chunkX; i <= n8; i++) {
         for (int j = this.chunkZ; j <= n9; j++) {
            this.chunkArray[i - this.chunkX][j - this.chunkZ] = world.getChunkFromChunkCoords(i, j);
         }
      }

      for (int k = n >> 4; k <= n4 >> 4; k++) {
         for (int m = n3 >> 4; m <= n6 >> 4; m++) {
            Chunk chunk = this.chunkArray[k - this.chunkX][m - this.chunkZ];
            if (chunk != null && !chunk.getAreLevelsEmpty(n2, n5)) {
               this.hasExtendedLevels = false;
            }
         }
      }
   }

   @Override
   public int getStrongPower(BlockPos pos, EnumFacing facing) {
      IBlockState blockState = this.getBlockState(pos);
      return blockState.getBlock().getStrongPower(this, pos, blockState, facing);
   }
}
