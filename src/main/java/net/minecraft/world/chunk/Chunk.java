package net.minecraft.world.chunk;

import java.util.concurrent.Callable;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.augustus.events.listeners.BlockChangeListener;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.ChunkProviderDebug;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Chunk {
   private static final Logger LOGGER = LogManager.getLogger();
   private final ExtendedBlockStorage[] storageArrays = new ExtendedBlockStorage[16];
   private final byte[] blockBiomeArray = new byte[256];
   private final int[] precipitationHeightMap = new int[256];
   private final boolean[] updateSkylightColumns = new boolean[256];
   private boolean isChunkLoaded;
   private final World worldObj;
   private final int[] heightMap;
   public final int xPosition;
   public final int zPosition;
   private boolean isGapLightingUpdated;
   private final Map chunkTileEntityMap = Maps.newHashMap();
   private final ClassInheritanceMultiMap[] entityLists;
   private boolean isTerrainPopulated;
   private boolean isLightPopulated;
   private boolean isTicked;
   private boolean isModified;
   public boolean hasEntities;
   private long lastSaveTime;
   private int heightMapMinimum;
   private long inhabitedTime;
   private int queuedLightChecks = 4096;
   private ConcurrentLinkedQueue tileEntityPosQueue = Queues.newConcurrentLinkedQueue();

   public void onChunkUnload() {
      this.isChunkLoaded = false;

      for (TileEntity tileEntity : (Iterable<TileEntity>)(this.chunkTileEntityMap.values())) {
         this.worldObj.markTileEntityForRemoval(tileEntity);
      }

      for (int i = 0; i < this.entityLists.length; i++) {
         this.worldObj.unloadEntities(this.entityLists[i]);
      }
   }

   public boolean getAreLevelsEmpty(int n, int n2) {
      if (n < 0) {
         n = 0;
      }

      if (n2 >= 256) {
         n2 = 255;
      }

      for (int i = n; i <= n2; i += 16) {
         ExtendedBlockStorage extendedBlockStorage = this.storageArrays[i >> 4];
         if (extendedBlockStorage != null && !extendedBlockStorage.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public Random getRandomWithSeed(long l) {
      return new Random(
         this.worldObj.getSeed()
               + (long)(this.xPosition * this.xPosition * 4987142)
               + (long)(this.xPosition * 5947611)
               + (long)(this.zPosition * this.zPosition) * 4392871L
               + (long)(this.zPosition * 389711)
            ^ l
      );
   }

   public ExtendedBlockStorage[] getBlockStorageArray() {
      return this.storageArrays;
   }

   public void fillChunk(byte[] bytes, int n, boolean flag) {
      int n2 = 0;
      boolean flag2 = !this.worldObj.provider.getHasNoSky();

      for (int i = 0; i < this.storageArrays.length; i++) {
         if ((n & 1 << i) != 0) {
            if (this.storageArrays[i] == null) {
               this.storageArrays[i] = new ExtendedBlockStorage(i << 4, flag2);
            }

            char[] data = this.storageArrays[i].getData();

            for (int j = 0; j < data.length; j++) {
               data[j] = (char)((bytes[n2 + 1] & 255) << 8 | bytes[n2] & 255);
               n2 += 2;
            }
         } else if (flag && this.storageArrays[i] != null) {
            this.storageArrays[i] = null;
         }
      }

      for (int k = 0; k < this.storageArrays.length; k++) {
         if ((n & 1 << k) != 0 && this.storageArrays[k] != null) {
            NibbleArray blocklightArray = this.storageArrays[k].getBlocklightArray();
            System.arraycopy(bytes, n2, blocklightArray.getData(), 0, blocklightArray.getData().length);
            n2 += blocklightArray.getData().length;
         }
      }

      if (flag2) {
         for (int m = 0; m < this.storageArrays.length; m++) {
            if ((n & 1 << m) != 0 && this.storageArrays[m] != null) {
               NibbleArray skylightArray = this.storageArrays[m].getSkylightArray();
               System.arraycopy(bytes, n2, skylightArray.getData(), 0, skylightArray.getData().length);
               n2 += skylightArray.getData().length;
            }
         }
      }

      if (flag) {
         System.arraycopy(bytes, n2, this.blockBiomeArray, 0, this.blockBiomeArray.length);
         int n3 = n2 + this.blockBiomeArray.length;
      }

      for (int idx = 0; idx < this.storageArrays.length; idx++) {
         if (this.storageArrays[idx] != null && (n & 1 << idx) != 0) {
            this.storageArrays[idx].removeInvalidBlocks();
         }
      }

      this.isLightPopulated = true;
      this.isTerrainPopulated = true;
      this.generateHeightMap();

      for (TileEntity tileEntity : (Iterable<TileEntity>)(this.chunkTileEntityMap.values())) {
         tileEntity.updateContainingBlockInfo();
      }
   }

   public Chunk(World world, ChunkPrimer chunkPrimer, int n, int n2) {
      this(world, n, n2);
      short s = 256;
      boolean flag = !world.provider.getHasNoSky();

      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            for (int k = 0; k < s; k++) {
               IBlockState blockState = chunkPrimer.getBlockState(i * s * 16 | j * s | k);
               if (blockState.getBlock().getMaterial() != Material.air) {
                  int n4 = k >> 4;
                  if (this.storageArrays[n4] == null) {
                     this.storageArrays[n4] = new ExtendedBlockStorage(n4 << 4, flag);
                  }

                  this.storageArrays[n4].set(i, k & 15, j, blockState);
               }
            }
         }
      }
   }

   public long getInhabitedTime() {
      return this.inhabitedTime;
   }

   public int getLightFor(EnumSkyBlock enumSkyBlock, int n, int n2, int n3) {
      int n4 = n & 15;
      int n5 = n3 & 15;
      ExtendedBlockStorage extendedBlockStorage = this.storageArrays[n2 >> 4];
      return extendedBlockStorage == null
         ? (this.canSeeSky(n, n2, n3) ? enumSkyBlock.defaultLightValue : 0)
         : (
            enumSkyBlock == EnumSkyBlock.SKY
               ? (this.worldObj.provider.getHasNoSky() ? 0 : extendedBlockStorage.getExtSkylightValue(n4, n2 & 15, n5))
               : (enumSkyBlock == EnumSkyBlock.BLOCK ? extendedBlockStorage.getExtBlocklightValue(n4, n2 & 15, n5) : enumSkyBlock.defaultLightValue)
         );
   }

   public int getLightSubtracted(int n, int n2, int n3, int n4) {
      int n5 = n & 15;
      int n6 = n3 & 15;
      ExtendedBlockStorage extendedBlockStorage = this.storageArrays[n2 >> 4];
      if (extendedBlockStorage != null) {
         int n7 = this.worldObj.provider.getHasNoSky() ? 0 : extendedBlockStorage.getExtSkylightValue(n5, n2 & 15, n6);
         n7 -= n4;
         int extBlocklightValue = extendedBlockStorage.getExtBlocklightValue(n5, n2 & 15, n6);
         if (extBlocklightValue > n7) {
            n7 = extBlocklightValue;
         }

         return n7;
      } else {
         return !this.worldObj.provider.getHasNoSky() && n4 < EnumSkyBlock.SKY.defaultLightValue ? EnumSkyBlock.SKY.defaultLightValue - n4 : 0;
      }
   }

   private boolean checkLight(int n, int n2) {
      int topFilledSegment = this.getTopFilledSegment();
      boolean flag = false;
      boolean flag2 = false;
      int n3 = (this.xPosition << 4) + n;
      int n4 = 0;
      int n5 = (this.zPosition << 4) + n2;

      for (int i = topFilledSegment + 16 - 1; i > this.worldObj.getSeaLevel() || i > 0 && !flag2; i--) {
         n4 = i;
         int blockLightOpacity = this.getBlockLightOpacity(n3, i, n5);
         if (blockLightOpacity == 255 && i < this.worldObj.getSeaLevel()) {
            flag2 = true;
         }

         if (!flag && blockLightOpacity > 0) {
            flag = true;
         } else if (flag && blockLightOpacity == 0 && !this.worldObj.checkLight(n3, i, n5)) {
            return false;
         }
      }

      for (int j = n4; j > 0; j--) {
         if (this.getBlock(n3, j, n5).getLightValue() > 0) {
            this.worldObj.checkLight(n3, j, n5);
         }
      }

      return true;
   }

   public boolean needsSaving(boolean flag) {
      if (flag) {
         if (this.hasEntities && this.worldObj.getTotalWorldTime() != this.lastSaveTime || this.isModified) {
            return true;
         }
      } else if (this.hasEntities && this.worldObj.getTotalWorldTime() >= this.lastSaveTime + 600L) {
         return true;
      }

      return this.isModified;
   }

   public Block getBlock(int n, int n2, int n3) {
      try {
         return this.getBlock0(n & 15, n2, n3 & 15);
      } catch (ReportedException e) {
         e.getCrashReport().makeCategory("Block being got").addCrashSectionCallable("Location", new Chunk.ValueCallable(this, n, n2, n3));
         throw e;
      }
   }

   public void generateHeightMap() {
      int topFilledSegment = this.getTopFilledSegment();
      this.heightMapMinimum = 2147483647;

      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            this.precipitationHeightMap[i + (j << 4)] = -999;

            for (int k = topFilledSegment + 16; k > 0; k--) {
               if (this.getBlock0(i, k - 1, j).getLightOpacity() != 0) {
                  this.heightMap[j << 4 | i] = k;
                  if (k < this.heightMapMinimum) {
                     this.heightMapMinimum = k;
                  }
                  break;
               }
            }
         }
      }

      this.isModified = true;
   }

   public World getWorld() {
      return this.worldObj;
   }

   public void removeTileEntity(BlockPos pos) {
      if (this.isChunkLoaded) {
         TileEntity tileEntity = (TileEntity)this.chunkTileEntityMap.remove(pos);
         if (tileEntity != null) {
            tileEntity.invalidate();
         }
      }
   }

   private Block getBlock0(int n, int n2, int n3) {
      Block air = Blocks.air;
      if (n2 >= 0 && n2 >> 4 < this.storageArrays.length) {
         ExtendedBlockStorage extendedBlockStorage = this.storageArrays[n2 >> 4];
         if (extendedBlockStorage != null) {
            try {
               air = extendedBlockStorage.getBlockByExtId(n, n2 & 15, n3);
            } catch (Throwable e) {
               CrashReport crashReport = CrashReport.makeCrashReport(e, "Getting block");
               throw new ReportedException(crashReport);
            }
         }
      }

      return air;
   }

   public boolean isEmpty() {
      return false;
   }

   public ChunkCoordIntPair getChunkCoordIntPair() {
      return new ChunkCoordIntPair(this.xPosition, this.zPosition);
   }

   public void setLightFor(EnumSkyBlock enumSkyBlock, int n, int n2, int n3, int n4) {
      int n5 = n & 15;
      int n6 = n3 & 15;
      ExtendedBlockStorage extendedBlockStorage = this.storageArrays[n2 >> 4];
      if (extendedBlockStorage == null) {
         extendedBlockStorage = this.storageArrays[n2 >> 4] = new ExtendedBlockStorage(n2 >> 4 << 4, !this.worldObj.provider.getHasNoSky());
         this.generateSkylightMap();
      }

      this.isModified = true;
      if (enumSkyBlock == EnumSkyBlock.SKY) {
         if (!this.worldObj.provider.getHasNoSky()) {
            extendedBlockStorage.setExtSkylightValue(n5, n2 & 15, n6, n4);
         }
      } else if (enumSkyBlock == EnumSkyBlock.BLOCK) {
         extendedBlockStorage.setExtBlocklightValue(n5, n2 & 15, n6, n4);
      }
   }

   private int getBlockMetadata(int n, int n2, int n3) {
      if (n2 >> 4 >= this.storageArrays.length) {
         return 0;
      } else {
         ExtendedBlockStorage extendedBlockStorage = this.storageArrays[n2 >> 4];
         return extendedBlockStorage != null ? extendedBlockStorage.getExtBlockMetadata(n, n2 & 15, n3) : 0;
      }
   }

   private void relightBlock(int n, int n2, int n3) {
      int n4 = this.heightMap[n3 << 4 | n] & 0xFF;
      int n5 = n4;
      if (n2 > n4) {
         n5 = n2;
      }

      while (n5 > 0 && this.getBlockLightOpacity0(n, n5 - 1, n3) == 0) {
         n5--;
      }

      if (n5 != n4) {
         this.worldObj.markBlocksDirtyVertical(n + this.xPosition * 16, n3 + this.zPosition * 16, n5, n4);
         this.heightMap[n3 << 4 | n] = n5;
         int n6 = this.xPosition * 16 + n;
         int n7 = this.zPosition * 16 + n3;
         if (!this.worldObj.provider.getHasNoSky()) {
            if (n5 < n4) {
               for (int i = n5; i < n4; i++) {
                  ExtendedBlockStorage extendedBlockStorage = this.storageArrays[i >> 4];
                  if (extendedBlockStorage != null) {
                     extendedBlockStorage.setExtSkylightValue(n, i & 15, n3, 15);
                     this.worldObj.notifyLightSet((this.xPosition << 4) + n, i, (this.zPosition << 4) + n3);
                  }
               }
            } else {
               for (int j = n4; j < n5; j++) {
                  ExtendedBlockStorage extendedBlockStorage2 = this.storageArrays[j >> 4];
                  if (extendedBlockStorage2 != null) {
                     extendedBlockStorage2.setExtSkylightValue(n, j & 15, n3, 0);
                     this.worldObj.notifyLightSet((this.xPosition << 4) + n, j, (this.zPosition << 4) + n3);
                  }
               }
            }

            int n8 = 15;

            while (n5 > 0 && n8 > 0) {
               int blockLightOpacity0 = this.getBlockLightOpacity0(n, --n5, n3);
               if (blockLightOpacity0 == 0) {
                  blockLightOpacity0 = 1;
               }

               n8 -= blockLightOpacity0;
               if (n8 < 0) {
                  n8 = 0;
               }

               ExtendedBlockStorage extendedBlockStorage3 = this.storageArrays[n5 >> 4];
               if (extendedBlockStorage3 != null) {
                  extendedBlockStorage3.setExtSkylightValue(n, n5 & 15, n3, n8);
               }
            }
         }

         int n9 = this.heightMap[n3 << 4 | n];
         int n10 = n4;
         int n11 = n9;
         if (n9 < n4) {
            n10 = n9;
            n11 = n4;
         }

         if (n9 < this.heightMapMinimum) {
            this.heightMapMinimum = n9;
         }

         if (!this.worldObj.provider.getHasNoSky()) {
            for (Object obj : EnumFacing.Plane.HORIZONTAL) {
               this.updateSkylightNeighborHeight(n6 + ((EnumFacing)obj).getFrontOffsetX(), n7 + ((EnumFacing)obj).getFrontOffsetZ(), n10, n11);
            }

            this.updateSkylightNeighborHeight(n6, n7, n10, n11);
         }

         this.isModified = true;
      }
   }

   private void checkSkylightNeighborHeight(int n, int n2, int n3) {
      int y = this.worldObj.getHeight(new BlockPos(n, 0, n2)).getY();
      if (y > n3) {
         this.updateSkylightNeighborHeight(n, n2, n3, y + 1);
      } else if (y < n3) {
         this.updateSkylightNeighborHeight(n, n2, y, n3 + 1);
      }
   }

   public void addTileEntity(BlockPos pos, TileEntity tileEntity) {
      tileEntity.setWorldObj(this.worldObj);
      tileEntity.setPos(pos);
      if (this.getBlock(pos) instanceof ITileEntityProvider) {
         if (this.chunkTileEntityMap.containsKey(pos)) {
            ((TileEntity)this.chunkTileEntityMap.get(pos)).invalidate();
         }

         tileEntity.validate();
         this.chunkTileEntityMap.put(pos, tileEntity);
      }
   }

   public BlockPos getPrecipitationHeight(BlockPos pos) {
      int n3 = (pos.getX() & 15) | (pos.getZ() & 15) << 4;
      int x = pos.getX();
      int n4 = this.precipitationHeightMap[n3];
      int z = pos.getZ();
      if (n4 == -999) {
         n4 = this.getTopFilledSegment() + 15;
         int n5 = -1;

         while (n4 > 0 && n5 == -1) {
            Material material = this.getBlock(x, n4, z).getMaterial();
            if (!material.blocksMovement() && !material.isLiquid()) {
               n4--;
            } else {
               n5 = n4 + 1;
            }
         }

         this.precipitationHeightMap[n3] = n5;
      }

      return new BlockPos(pos.getX(), this.precipitationHeightMap[n3], pos.getZ());
   }

   private void updateSkylightNeighborHeight(int n, int n2, int n3, int n4) {
      if (n4 > n3 && this.worldObj.isAreaLoaded(n, 0, n2, 16)) {
         for (int i = n3; i < n4; i++) {
            this.worldObj.checkLightFor(EnumSkyBlock.SKY, n, i, n2);
         }

         this.isModified = true;
      }
   }

   public byte[] getBiomeArray() {
      return this.blockBiomeArray;
   }

   public void generateSkylightMap() {
      int topFilledSegment = this.getTopFilledSegment();
      this.heightMapMinimum = 2147483647;

      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            this.precipitationHeightMap[i + (j << 4)] = -999;

            for (int k = topFilledSegment + 16; k > 0; k--) {
               if (this.getBlockLightOpacity0(i, k - 1, j) != 0) {
                  this.heightMap[j << 4 | i] = k;
                  if (k < this.heightMapMinimum) {
                     this.heightMapMinimum = k;
                  }
                  break;
               }
            }

            if (!this.worldObj.provider.getHasNoSky()) {
               int n = 15;
               int n2 = topFilledSegment + 16 - 1;

               while (true) {
                  int blockLightOpacity0 = this.getBlockLightOpacity0(i, n2, j);
                  if (blockLightOpacity0 == 0 && n != 15) {
                     blockLightOpacity0 = 1;
                  }

                  n -= blockLightOpacity0;
                  if (n > 0) {
                     ExtendedBlockStorage extendedBlockStorage = this.storageArrays[n2 >> 4];
                     if (extendedBlockStorage != null) {
                        extendedBlockStorage.setExtSkylightValue(i, n2 & 15, j, n);
                        this.worldObj.notifyLightSet((this.xPosition << 4) + i, n2, (this.zPosition << 4) + j);
                     }
                  }

                  if (--n2 <= 0 || n <= 0) {
                     break;
                  }
               }
            }
         }
      }

      this.isModified = true;
   }

   public void addTileEntity(TileEntity tileEntity) {
      this.addTileEntity(tileEntity.getPos(), tileEntity);
      if (this.isChunkLoaded) {
         this.worldObj.addTileEntity(tileEntity);
      }
   }

   private TileEntity createNewTileEntity(BlockPos pos) {
      Block block = this.getBlock(pos);
      return !block.hasTileEntity() ? null : ((ITileEntityProvider)block).createNewTileEntity(this.worldObj, this.getBlockMetadata(pos));
   }

   public TileEntity getTileEntity(BlockPos pos, Chunk.EnumCreateEntityType enumCreateEntityType) {
      TileEntity tileEntity = (TileEntity)this.chunkTileEntityMap.get(pos);
      if (tileEntity == null) {
         if (enumCreateEntityType == Chunk.EnumCreateEntityType.IMMEDIATE) {
            tileEntity = this.createNewTileEntity(pos);
            this.worldObj.setTileEntity(pos, tileEntity);
         } else if (enumCreateEntityType == Chunk.EnumCreateEntityType.QUEUED) {
            this.tileEntityPosQueue.add(pos);
         }
      } else if (tileEntity.isInvalid()) {
         this.chunkTileEntityMap.remove(pos);
         return null;
      }

      return tileEntity;
   }

   public void setHeightMap(int[] ints) {
      if (this.heightMap.length != ints.length) {
         LOGGER.warn("Could not set level chunk heightmap, array length is " + ints.length + " instead of " + this.heightMap.length);
      } else {
         for (int i = 0; i < this.heightMap.length; i++) {
            this.heightMap[i] = ints[i];
         }
      }
   }

   public Map getTileEntityMap() {
      return this.chunkTileEntityMap;
   }

   public void resetRelightChecks() {
      this.queuedLightChecks = 0;
   }

   public int getTopFilledSegment() {
      for (int i = this.storageArrays.length - 1; i >= 0; i--) {
         if (this.storageArrays[i] != null) {
            return this.storageArrays[i].getYLocation();
         }
      }

      return 0;
   }

   private void propagateSkylightOcclusion(int n, int n2) {
      this.updateSkylightColumns[n + n2 * 16] = true;
      this.isGapLightingUpdated = true;
   }

   public void addEntity(Entity entity) {
      this.hasEntities = true;
      int n = MathHelper.floor_double(entity.posX / 16.0);
      int n2 = MathHelper.floor_double(entity.posZ / 16.0);
      if (n != this.xPosition || n2 != this.zPosition) {
         LOGGER.warn("Wrong location! (" + n + ", " + n2 + ") should be (" + this.xPosition + ", " + this.zPosition + "), " + entity, new Object[]{entity});
         entity.setDead();
      }

      int n3 = MathHelper.floor_double(entity.posY / 16.0);
      if (n3 < 0) {
         n3 = 0;
      }

      if (n3 >= this.entityLists.length) {
         n3 = this.entityLists.length - 1;
      }

      entity.addedToChunk = true;
      entity.chunkCoordX = this.xPosition;
      entity.chunkCoordY = n3;
      entity.chunkCoordZ = this.zPosition;
      this.entityLists[n3].add(entity);
   }

   public IBlockState setBlockState(BlockPos pos, IBlockState state) {
      int n = pos.getX() & 15;
      int y = pos.getY();
      int n2 = pos.getZ() & 15;
      int n3 = n2 << 4 | n;
      if (y >= this.precipitationHeightMap[n3] - 1) {
         this.precipitationHeightMap[n3] = -999;
      }

      int n4 = this.heightMap[n3];
      IBlockState blockState = this.getBlockState(pos);
      if (blockState == state) {
         return null;
      } else {
         Block block = state.getBlock();
         Block block2 = blockState.getBlock();
         ExtendedBlockStorage extendedBlockStorage = this.storageArrays[y >> 4];
         boolean flag = false;
         if (extendedBlockStorage == null) {
            if (block == Blocks.air) {
               BlockChangeListener.onBlockChange(pos.getX(), pos.getY(), pos.getZ(), state, pos);
               return null;
            }

            extendedBlockStorage = this.storageArrays[y >> 4] = new ExtendedBlockStorage(y >> 4 << 4, !this.worldObj.provider.getHasNoSky());
            flag = y >= n4;
         }

         extendedBlockStorage.set(n, y & 15, n2, state);
         if (block2 != block) {
            if (!this.worldObj.isRemote) {
               block2.breakBlock(this.worldObj, pos, blockState);
            } else if (block2 instanceof ITileEntityProvider) {
               this.worldObj.removeTileEntity(pos);
            }
         }

         if (extendedBlockStorage.getBlockByExtId(n, y & 15, n2) != block) {
            BlockChangeListener.onBlockChange(pos.getX(), pos.getY(), pos.getZ(), state, pos);
            return null;
         } else {
            if (flag) {
               this.generateSkylightMap();
            } else {
               int lightOpacity = block.getLightOpacity();
               int lightOpacity2 = block2.getLightOpacity();
               if (lightOpacity > 0) {
                  if (y >= n4) {
                     this.relightBlock(n, y + 1, n2);
                  }
               } else if (y == n4 - 1) {
                  this.relightBlock(n, y, n2);
               }

               if (lightOpacity != lightOpacity2
                  && (
                     lightOpacity < lightOpacity2
                        || this.getLightFor(EnumSkyBlock.SKY, pos.getX(), pos.getY(), pos.getZ()) > 0
                        || this.getLightFor(EnumSkyBlock.BLOCK, pos.getX(), pos.getY(), pos.getZ()) > 0
                  )) {
                  this.propagateSkylightOcclusion(n, n2);
               }
            }

            if (block2 instanceof ITileEntityProvider) {
               TileEntity tileEntity = this.getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK);
               if (tileEntity != null) {
                  tileEntity.updateContainingBlockInfo();
               }
            }

            if (!this.worldObj.isRemote && block2 != block) {
               block.onBlockAdded(this.worldObj, pos, state);
            }

            if (block instanceof ITileEntityProvider) {
               TileEntity tileEntity2 = this.getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK);
               if (tileEntity2 == null) {
                  tileEntity2 = ((ITileEntityProvider)block).createNewTileEntity(this.worldObj, block.getMetaFromState(state));
                  this.worldObj.setTileEntity(pos, tileEntity2);
               }

               if (tileEntity2 != null) {
                  tileEntity2.updateContainingBlockInfo();
               }
            }

            this.isModified = true;
            BlockChangeListener.onBlockChange(pos.getX(), pos.getY(), pos.getZ(), state, pos);
            return blockState;
         }
      }
   }

   public void setBiomeArray(byte[] bytes) {
      if (this.blockBiomeArray.length != bytes.length) {
         LOGGER.warn("Could not set level chunk biomes, array length is " + bytes.length + " instead of " + this.blockBiomeArray.length);
      } else {
         for (int i = 0; i < this.blockBiomeArray.length; i++) {
            this.blockBiomeArray[i] = bytes[i];
         }
      }
   }

   private void checkLightSide(EnumFacing facing) {
      if (this.isTerrainPopulated) {
         if (facing == EnumFacing.EAST) {
            for (int i = 0; i < 16; i++) {
               this.checkLight(15, i);
            }
         } else if (facing == EnumFacing.WEST) {
            for (int j = 0; j < 16; j++) {
               this.checkLight(0, j);
            }
         } else if (facing == EnumFacing.SOUTH) {
            for (int k = 0; k < 16; k++) {
               this.checkLight(k, 15);
            }
         } else if (facing == EnumFacing.NORTH) {
            for (int m = 0; m < 16; m++) {
               this.checkLight(m, 0);
            }
         }
      }
   }

   public void removeEntityAtIndex(Entity entity, int n) {
      if (n < 0) {
         n = 0;
      }

      if (n >= this.entityLists.length) {
         n = this.entityLists.length - 1;
      }

      this.entityLists[n].remove(entity);
   }

   public void setHasEntities(boolean flag) {
      this.hasEntities = flag;
   }

   public void enqueueRelightChecks() {
      int n = this.xPosition << 4;
      int n2 = this.zPosition << 4;

      for (int i = 0; i < 8; i++) {
         if (this.queuedLightChecks >= 4096) {
            return;
         }

         int n3 = this.queuedLightChecks % 16;
         int n4 = this.queuedLightChecks / 16 % 16;
         int n5 = this.queuedLightChecks / 256;
         this.queuedLightChecks++;

         for (int j = 0; j < 16; j++) {
            int n6 = n + n4;
            int n7 = ((byte)0) + (n3 << 4) + j;
            int n8 = n2 + n5;
            boolean flag = j == 0 || j == 15 || n4 == 0 || n4 == 15 || n5 == 0 || n5 == 15;
            if (this.storageArrays[n3] == null && flag
               || this.storageArrays[n3] != null && this.storageArrays[n3].getBlockByExtId(n4, j, n5).getMaterial() == Material.air) {
               for (EnumFacing facing : EnumFacing.getValues()) {
                  int n9 = n6 + facing.getFrontOffsetX();
                  int n10 = n7 + facing.getFrontOffsetY();
                  int n11 = n8 + facing.getFrontOffsetZ();
                  if (this.worldObj.getBlockState2(n9, n10, n11).getBlock().getLightValue() > 0) {
                     this.worldObj.checkLight(n9, n10, n11);
                  }
               }

               this.worldObj.checkLight(n6, n7, n8);
            }
         }
      }
   }

   public boolean isAtLocation(int n, int n2) {
      return n == this.xPosition && n2 == this.zPosition;
   }

   public void setChunkLoaded(boolean flag) {
      this.isChunkLoaded = flag;
   }

   public boolean isLightPopulated() {
      return this.isLightPopulated;
   }

   public int getBlockLightOpacity(int n, int n2, int n3) {
      return this.getBlock(n, n2, n3).getLightOpacity();
   }

   public void checkLight() {
      this.isTerrainPopulated = true;
      this.isLightPopulated = true;
      BlockPos pos = new BlockPos(this.xPosition << 4, 0, this.zPosition << 4);
      if (!this.worldObj.provider.getHasNoSky()) {
         if (this.worldObj.isAreaLoaded(pos.add(-1, 0, -1), pos.add(16, this.worldObj.getSeaLevel(), 16))) {
            outerLoop:
            for (int i = 0; i < 16; i++) {
               for (int j = 0; j < 16; j++) {
                  if (!this.checkLight(i, j)) {
                     this.isLightPopulated = false;
                     break outerLoop;
                  }
               }
            }

            if (this.isLightPopulated) {
               for (Object obj : EnumFacing.Plane.HORIZONTAL) {
                  int n = ((EnumFacing)obj).getAxisDirection() == EnumFacing.AxisDirection.POSITIVE ? 16 : 1;
                  this.worldObj.getChunkFromBlockCoords(pos.offset((EnumFacing)obj, n)).checkLightSide(((EnumFacing)obj).getOpposite());
               }

               this.setSkylightUpdated();
            }
         } else {
            this.isLightPopulated = false;
         }
      }
   }

   public void setModified(boolean flag) {
      this.isModified = flag;
   }

   public BiomeGenBase getBiome(int n, int n2, WorldChunkManager worldChunkManager) {
      int n3 = n & 15;
      int n4 = n2 & 15;
      int n5 = this.blockBiomeArray[n4 << 4 | n3] & 255;
      if (n5 == 255) {
         n5 = worldChunkManager.getBiome(n, n2, BiomeGenBase.plains).biomeID;
         this.blockBiomeArray[n4 << 4 | n3] = (byte)(n5 & 0xFF);
      }

      BiomeGenBase biome2 = BiomeGenBase.getBiome(n5);
      return biome2 == null ? BiomeGenBase.plains : biome2;
   }

   public void getEntitiesWithinAABBForEntity(Entity entity, AxisAlignedBB box, List list, Predicate predicate) {
      int n = MathHelper.floor_double((box.minY - 2.0) / 16.0);
      int n2 = MathHelper.floor_double((box.maxY + 2.0) / 16.0);
      n = MathHelper.clamp_int(n, 0, this.entityLists.length - 1);
      n2 = MathHelper.clamp_int(n2, 0, this.entityLists.length - 1);

      for (int i = n; i <= n2; i++) {
         if (!this.entityLists[i].isEmpty()) {
            ClassInheritanceMultiMap classInheritanceMultiMap = this.entityLists[i];

            for (int j = 0; j < classInheritanceMultiMap.size(); j++) {
               Entity entity2 = (Entity)classInheritanceMultiMap.get(j);
               if (entity2 != entity && entity2.getEntityBoundingBox().intersectsWith(box)) {
                  if (predicate == null || predicate.apply(entity2)) {
                     list.add(entity2);
                  }

                  Entity[] parts = entity2.getParts();
                  if (parts != null) {
                     for (Entity entity3 : parts) {
                        if (entity3 != entity && entity3.getEntityBoundingBox().intersectsWith(box) && (predicate == null || predicate.apply(entity3))) {
                           list.add(entity3);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void setInhabitedTime(long l) {
      this.inhabitedTime = l;
   }

   public boolean isLoaded() {
      return this.isChunkLoaded;
   }

   public void getEntitiesOfTypeWithinAAAB(Class clazz, AxisAlignedBB box, List list, Predicate predicate) {
      int n = MathHelper.floor_double((box.minY - 2.0) / 16.0);
      int n2 = MathHelper.floor_double((box.maxY + 2.0) / 16.0);
      n = MathHelper.clamp_int(n, 0, this.entityLists.length - 1);
      n2 = MathHelper.clamp_int(n2, 0, this.entityLists.length - 1);

      for (int i = n; i <= n2; i++) {
         for (Entity entity : (Iterable<Entity>)(this.entityLists[i].getByClass(clazz))) {
            if (entity.getEntityBoundingBox().intersectsWith(box) && (predicate == null || predicate.apply(entity))) {
               list.add(entity);
            }
         }
      }
   }

   public void setChunkModified() {
      this.isModified = true;
   }

   private void setSkylightUpdated() {
      for (int i = 0; i < this.updateSkylightColumns.length; i++) {
         this.updateSkylightColumns[i] = true;
      }

      this.recheckGaps(false);
   }

   public void setLightPopulated(boolean flag) {
      this.isLightPopulated = flag;
   }

   public int getPrecipitationHeightY(int n, int n2) {
      int n5 = (n & 15) | (n2 & 15) << 4;
      int n6 = this.precipitationHeightMap[n5];
      if (n6 == -999) {
         n6 = this.getTopFilledSegment() + 15;
         int n7 = -1;

         while (n6 > 0 && n7 == -1) {
            Material material = this.getBlock(n, n6, n2).getMaterial();
            if (!material.blocksMovement() && !material.isLiquid()) {
               n6--;
            } else {
               n7 = n6 + 1;
            }
         }

         this.precipitationHeightMap[n5] = n7;
      }

      return this.precipitationHeightMap[n5];
   }

   public int getHeightValue(int n, int n2) {
      return this.heightMap[n2 << 4 | n];
   }

   public void onTick(boolean flag) {
      if (this.isGapLightingUpdated && !this.worldObj.provider.getHasNoSky() && !flag) {
         this.recheckGaps(this.worldObj.isRemote);
      }

      this.isTicked = true;
      if (!this.isLightPopulated && this.isTerrainPopulated) {
         this.checkLight();
      }

      while (!this.tileEntityPosQueue.isEmpty()) {
         BlockPos pos = (BlockPos)this.tileEntityPosQueue.poll();
         if (this.getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) == null && this.getBlock(pos).hasTileEntity()) {
            TileEntity tileEntity = this.createNewTileEntity(pos);
            this.worldObj.setTileEntity(pos, tileEntity);
            this.worldObj.markBlockRangeForRenderUpdate(pos, pos);
         }
      }
   }

   public boolean isTerrainPopulated() {
      return this.isTerrainPopulated;
   }

   public ClassInheritanceMultiMap[] getEntityLists() {
      return this.entityLists;
   }

   public boolean isPopulated() {
      return this.isTicked && this.isTerrainPopulated && this.isLightPopulated;
   }

   public void setLastSaveTime(long l) {
      this.lastSaveTime = l;
   }

   private void recheckGaps(boolean flag) {
      this.worldObj.theProfiler.startSection("recheckGaps");
      if (this.worldObj.isAreaLoaded(this.xPosition * 16 + 8, 0, this.zPosition * 16 + 8, 16)) {
         for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
               if (this.updateSkylightColumns[i + j * 16]) {
                  this.updateSkylightColumns[i + j * 16] = false;
                  int heightValue = this.getHeightValue(i, j);
                  int n = this.xPosition * 16 + i;
                  int n2 = this.zPosition * 16 + j;
                  int n3 = 2147483647;

                  for (Object obj : EnumFacing.Plane.HORIZONTAL) {
                     n3 = Math.min(
                        n3, this.worldObj.getChunksLowestHorizon(n + ((EnumFacing)obj).getFrontOffsetX(), n2 + ((EnumFacing)obj).getFrontOffsetZ())
                     );
                  }

                  this.checkSkylightNeighborHeight(n, n2, n3);

                  for (Object obj2 : EnumFacing.Plane.HORIZONTAL) {
                     this.checkSkylightNeighborHeight(n + ((EnumFacing)obj2).getFrontOffsetX(), n2 + ((EnumFacing)obj2).getFrontOffsetZ(), heightValue);
                  }

                  if (flag) {
                     this.worldObj.theProfiler.endSection();
                     return;
                  }
               }
            }
         }

         this.isGapLightingUpdated = false;
      }

      this.worldObj.theProfiler.endSection();
   }

   public int getBlockMetadata(BlockPos pos) {
      return this.getBlockMetadata(pos.getX() & 15, pos.getY(), pos.getZ() & 15);
   }

   private int getBlockLightOpacity0(int n, int n2, int n3) {
      return this.getBlock0(n, n2, n3).getLightOpacity();
   }

   public void removeEntity(Entity entity) {
      this.removeEntityAtIndex(entity, entity.chunkCoordY);
   }

   public int getLowestHeight() {
      return this.heightMapMinimum;
   }

   public void populateChunk(IChunkProvider chunkProvider, IChunkProvider chunkProvider2, int n, int n2) {
      boolean flag = chunkProvider.chunkExists(n, n2 - 1);
      boolean flag2 = chunkProvider.chunkExists(n + 1, n2);
      boolean flag3 = chunkProvider.chunkExists(n, n2 + 1);
      boolean flag4 = chunkProvider.chunkExists(n - 1, n2);
      boolean flag5 = chunkProvider.chunkExists(n - 1, n2 - 1);
      boolean flag6 = chunkProvider.chunkExists(n + 1, n2 + 1);
      boolean flag7 = chunkProvider.chunkExists(n - 1, n2 + 1);
      boolean flag8 = chunkProvider.chunkExists(n + 1, n2 - 1);
      if (flag2 && flag3 && flag6) {
         if (!this.isTerrainPopulated) {
            chunkProvider.populate(chunkProvider2, n, n2);
         } else {
            chunkProvider.populateChunk(chunkProvider2, this, n, n2);
         }
      }

      if (flag4 && flag3 && flag7) {
         Chunk chunk = chunkProvider.provideChunk(n - 1, n2);
         if (!chunk.isTerrainPopulated) {
            chunkProvider.populate(chunkProvider2, n - 1, n2);
         } else {
            chunkProvider.populateChunk(chunkProvider2, chunk, n - 1, n2);
         }
      }

      if (flag && flag2 && flag8) {
         Chunk chunk2 = chunkProvider.provideChunk(n, n2 - 1);
         if (!chunk2.isTerrainPopulated) {
            chunkProvider.populate(chunkProvider2, n, n2 - 1);
         } else {
            chunkProvider.populateChunk(chunkProvider2, chunk2, n, n2 - 1);
         }
      }

      if (flag5 && flag && flag4) {
         Chunk chunk3 = chunkProvider.provideChunk(n - 1, n2 - 1);
         if (!chunk3.isTerrainPopulated) {
            chunkProvider.populate(chunkProvider2, n - 1, n2 - 1);
         } else {
            chunkProvider.populateChunk(chunkProvider2, chunk3, n - 1, n2 - 1);
         }
      }
   }

   public void setStorageArrays(ExtendedBlockStorage[] extendedBlockStorages) {
      if (this.storageArrays.length != extendedBlockStorages.length) {
         LOGGER.warn("Could not set level chunk sections, array length is " + extendedBlockStorages.length + " instead of " + this.storageArrays.length);
      } else {
         System.arraycopy(extendedBlockStorages, 0, this.storageArrays, 0, this.storageArrays.length);
      }
   }

   public Chunk(World world, int n, int n2) {
      this.entityLists = new ClassInheritanceMultiMap[16];
      this.worldObj = world;
      this.xPosition = n;
      this.zPosition = n2;
      this.heightMap = new int[256];

      for (int i = 0; i < this.entityLists.length; i++) {
         this.entityLists[i] = new ClassInheritanceMultiMap(Entity.class);
      }

      Arrays.fill(this.precipitationHeightMap, -999);
      Arrays.fill(this.blockBiomeArray, (byte)-1);
   }

   public IBlockState getBlockState(BlockPos pos) {
      if (this.worldObj.getWorldType() == WorldType.DEBUG_WORLD) {
         IBlockState state = null;
         if (pos.getY() == 60) {
            state = Blocks.barrier.getDefaultState();
         }

         if (pos.getY() == 70) {
            state = ChunkProviderDebug.getBlockStateFor(pos.getX(), pos.getZ());
         }

         return state == null ? Blocks.air.getDefaultState() : state;
      } else {
         try {
            if (pos.getY() >= 0 && pos.getY() >> 4 < this.storageArrays.length) {
               ExtendedBlockStorage extendedBlockStorage = this.storageArrays[pos.getY() >> 4];
               if (extendedBlockStorage != null) {
                  return extendedBlockStorage.get(pos.getX() & 15, pos.getY() & 15, pos.getZ() & 15);
               }
            }

            return Blocks.air.getDefaultState();
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Getting block state");
            crashReport.makeCategory("Block being got").addCrashSectionCallable("Location", new Chunk.ValueCallable3(this, pos));
            throw new ReportedException(crashReport);
         }
      }
   }

   public Block getBlock(BlockPos pos) {
      try {
         return this.getBlock0(pos.getX() & 15, pos.getY(), pos.getZ() & 15);
      } catch (ReportedException e) {
         e.getCrashReport().makeCategory("Block being got").addCrashSectionCallable("Location", new Chunk.ValueCallable2(this, pos));
         throw e;
      }
   }

   public boolean canSeeSky(int n, int n2, int n3) {
      int n4 = n & 15;
      int n5 = n3 & 15;
      return n2 >= this.heightMap[n5 << 4 | n4];
   }

   public void setTerrainPopulated(boolean flag) {
      this.isTerrainPopulated = flag;
   }

   public IBlockState getBlockState(int n, int n2, int n3) {
      if (n2 >= 0 && n2 >> 4 < this.storageArrays.length) {
         ExtendedBlockStorage extendedBlockStorage = this.storageArrays[n2 >> 4];
         if (extendedBlockStorage != null) {
            return extendedBlockStorage.get(n & 15, n2 & 15, n3 & 15);
         }
      }

      return Blocks.air.getDefaultState();
   }

   public int getHeight(BlockPos pos) {
      return this.getHeightValue(pos.getX() & 15, pos.getZ() & 15);
   }

   public void onChunkLoad() {
      this.isChunkLoaded = true;
      this.worldObj.addTileEntities(this.chunkTileEntityMap.values());

      for (int i = 0; i < this.entityLists.length; i++) {
         for (Entity entity : (Iterable<Entity>)(this.entityLists[i])) {
            entity.onChunkLoad();
         }

         this.worldObj.loadEntities(this.entityLists[i]);
      }
   }

   public int[] getHeightMap() {
      return this.heightMap;
   }


   public static class ValueCallable implements Callable {
      public final int localX;
      public final int posY;
      public final int localZ;
      public final Chunk chunk;

      public String callAsString() throws Exception {
         return CrashReportCategory.getCoordinateInfo(new BlockPos(this.chunk.xPosition * 16 + this.localX, this.posY, this.chunk.zPosition * 16 + this.localZ));
      }

      public ValueCallable(Chunk chunk2, int n, int n2, int n3) {
         this.chunk = chunk2;
         this.localX = n;
         this.posY = n2;
         this.localZ = n3;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static class ValueCallable2 implements Callable {
      public final BlockPos blockPos;
      public final Chunk chunk;

      public String callAsString() throws Exception {
         return CrashReportCategory.getCoordinateInfo(this.blockPos);
      }

      public ValueCallable2(Chunk chunk2, BlockPos pos) {
         this.chunk = chunk2;
         this.blockPos = pos;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static class ValueCallable3 implements Callable {
      public final BlockPos blockPos;
      public final Chunk chunk;

      public ValueCallable3(Chunk chunk2, BlockPos pos) {
         this.chunk = chunk2;
         this.blockPos = pos;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public String callAsString() throws Exception {
         return CrashReportCategory.getCoordinateInfo(this.blockPos);
      }
   }

   public enum EnumCreateEntityType {
      IMMEDIATE,
      QUEUED,
      CHECK;

   }
}
