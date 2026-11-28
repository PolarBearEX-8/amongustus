package net.minecraft.world;

import java.util.concurrent.Callable;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.DisconnectListener;
import net.augustus.Augustus;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldInfo;

public abstract class World implements IBlockAccess {
   private int seaLevel = 63;
   public boolean scheduledUpdatesAreImmediate;
   public final List loadedEntityList = Lists.newArrayList();
   public final List unloadedEntityList = Lists.newArrayList();
   public final List loadedTileEntityList = Lists.newArrayList();
   public final List tickableTileEntities = Lists.newArrayList();
   private final List addedTileEntityList = Lists.newArrayList();
   private final List tileEntitiesToBeRemoved = Lists.newArrayList();
   public final List playerEntities = Lists.newArrayList();
   public final HashMap playerEntityMap = new HashMap();
   public final List weatherEffects = Lists.newArrayList();
   public final IntHashMap entitiesById = new IntHashMap();
   private long cloudColour = 16777215L;
   private int skylightSubtracted;
   public int updateLCG = new Random().nextInt();
   public final int DIST_HASH_MAGIC = 1013904223;
   public float prevRainingStrength;
   public float rainingStrength;
   public float prevThunderingStrength;
   public float thunderingStrength;
   private int lastLightningBolt;
   public final Random rand = new Random();
   public final WorldProvider provider;
   public List worldAccesses = Lists.newArrayList();
   public IChunkProvider chunkProvider;
   public final ISaveHandler saveHandler;
   public WorldInfo worldInfo;
   public boolean findingSpawnPoint;
   public MapStorage mapStorage;
   public VillageCollection villageCollectionObj;
   public final Profiler theProfiler;
   private final Calendar theCalendar = Calendar.getInstance();
   public Scoreboard worldScoreboard = new Scoreboard();
   public final boolean isRemote;
   public ObjectOpenHashSet activeChunkSet = new ObjectOpenHashSet();
   private int ambientTickCountdown = this.rand.nextInt(12000);
   public boolean spawnHostileMobs = true;
   public boolean spawnPeacefulMobs = true;
   private boolean processingLoadedTiles;
   private final WorldBorder worldBorder;
   public int[] lightUpdateBlockList = new int[32768];

   public boolean isMaterialInBB(AxisAlignedBB box, Material material) {
      int n = MathHelper.floor_double(box.minX);
      int n2 = MathHelper.floor_double(box.maxX + 1.0);
      int n3 = MathHelper.floor_double(box.minY);
      int n4 = MathHelper.floor_double(box.maxY + 1.0);
      int n5 = MathHelper.floor_double(box.minZ);
      int n6 = MathHelper.floor_double(box.maxZ + 1.0);
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n; i < n2; i++) {
         for (int j = n3; j < n4; j++) {
            for (int k = n5; k < n6; k++) {
               if (this.getBlockState(pos.set(i, j, k)).getBlock().getMaterial() == material) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean isAreaLoaded(StructureBoundingBox structureBoundingBox) {
      return this.isAreaLoaded(structureBoundingBox, true);
   }

   public void notifyNeighborsOfStateChange(BlockPos pos, Block block) {
      this.notifyBlockOfStateChange(pos.west(), block);
      this.notifyBlockOfStateChange(pos.east(), block);
      this.notifyBlockOfStateChange(pos.down(), block);
      this.notifyBlockOfStateChange(pos.up(), block);
      this.notifyBlockOfStateChange(pos.north(), block);
      this.notifyBlockOfStateChange(pos.south(), block);
   }

   public boolean isAnyLiquid(AxisAlignedBB box) {
      int n = MathHelper.floor_double(box.minX);
      int n2 = MathHelper.floor_double(box.maxX);
      int n3 = MathHelper.floor_double(box.minY);
      int n4 = MathHelper.floor_double(box.maxY);
      int n5 = MathHelper.floor_double(box.minZ);
      int n6 = MathHelper.floor_double(box.maxZ);
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n; i <= n2; i++) {
         for (int j = n3; j <= n4; j++) {
            for (int k = n5; k <= n6; k++) {
               if (this.getBlockState(pos.set(i, j, k)).getBlock().getMaterial().isLiquid()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public double getHorizon() {
      return this.worldInfo.getTerrainType() == WorldType.FLAT ? 0.0 : 63.0;
   }

   public void setRainStrength(float f) {
      this.prevRainingStrength = f;
      this.rainingStrength = f;
   }

   public boolean setBlockState(BlockPos pos, IBlockState state, int n) {
      if (!this.isValid(pos)) {
         return false;
      } else if (!this.isRemote && this.worldInfo.getTerrainType() == WorldType.DEBUG_WORLD) {
         return false;
      } else {
         Chunk chunkFromBlockCoords = this.getChunkFromBlockCoords(pos);
         Block block = state.getBlock();
         IBlockState state2 = chunkFromBlockCoords.setBlockState(pos, state);
         if (state2 == null) {
            return false;
         } else {
            Block block2 = state2.getBlock();
            if (block.getLightOpacity() != block2.getLightOpacity() || block.getLightValue() != block2.getLightValue()) {
               this.theProfiler.startSection("checkLight");
               this.checkLight(pos.getX(), pos.getY(), pos.getZ());
               this.theProfiler.endSection();
            }

            if ((n & 2) != 0 && (!this.isRemote || (n & 4) == 0) && chunkFromBlockCoords.isPopulated()) {
               this.markBlockForUpdate(pos);
            }

            if (!this.isRemote && (n & 1) != 0) {
               this.notifyNeighborsRespectDebug(pos, state2.getBlock());
               if (block.hasComparatorInputOverride()) {
                  this.updateComparatorOutputLevel(pos, block);
               }
            }

            return true;
         }
      }
   }

   public void setLastLightningBolt(int n) {
      this.lastLightningBolt = n;
   }

   public boolean isBlockinHighHumidity(BlockPos pos) {
      return this.getBiomeGenForCoords(pos).isHighHumidity();
   }

   public ISaveHandler getSaveHandler() {
      return this.saveHandler;
   }

   public abstract IChunkProvider createChunkProvider();

   public boolean isBlockLoaded(int n, int n2, int n3, boolean flag) {
      return !this.isValid(n, n2, n3) ? false : this.isChunkLoaded(n >> 4, n3 >> 4, flag);
   }

   public boolean isThundering() {
      return (double)this.getThunderStrength(1.0F) > 0.9;
   }

   public long getWorldTime() {
      return this.worldInfo.getWorldTime();
   }

   private int getRawLight(int n, int n2, int n3, EnumSkyBlock enumSkyBlock) {
      if (enumSkyBlock == EnumSkyBlock.SKY && this.canSeeSky(n, n2, n3)) {
         return 15;
      } else {
         Block block = this.getBlockState2(n, n2, n3).getBlock();
         int n4 = enumSkyBlock == EnumSkyBlock.SKY ? 0 : block.getLightValue();
         int lightOpacity = block.getLightOpacity();
         if (lightOpacity >= 15 && block.getLightValue() > 0) {
            lightOpacity = 1;
         }

         if (lightOpacity < 1) {
            lightOpacity = 1;
         }

         if (lightOpacity >= 15) {
            return 0;
         } else if (n4 >= 14) {
            return n4;
         } else {
            for (EnumFacing facing : EnumFacing.getValues()) {
               int n5 = this.getLightFor(enumSkyBlock, n + facing.getFrontOffsetX(), n2 + facing.getFrontOffsetY(), n3 + facing.getFrontOffsetZ()) - lightOpacity;
               if (n5 > n4) {
                  n4 = n5;
               }

               if (n4 >= 14) {
                  return n4;
               }
            }

            return n4;
         }
      }
   }

   public boolean canBlockBePlaced(Block block, BlockPos pos, boolean flag, EnumFacing facing, Entity entity, ItemStack stack) {
      Block block2 = this.getBlockState(pos).getBlock();
      AxisAlignedBB box = flag ? null : block.getCollisionBoundingBox(this, block.getDefaultState(), pos.getX(), pos.getY(), pos.getZ());
      return box != null && !this.checkNoEntityCollision(box, entity)
         ? false
         : (
            block2.getMaterial() == Material.circuits && block == Blocks.anvil
               ? true
               : block2.getMaterial().isReplaceable() && block.canReplace(this, pos, facing, stack)
         );
   }

   public void setEntityState(Entity entity, byte b) {
   }

   public void spawnParticle(EnumParticleTypes enumParticleTypes, boolean flag, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
      this.spawnParticle(enumParticleTypes.getParticleID(), enumParticleTypes.getShouldIgnoreRange() | flag, d, d2, d3, d4, d5, d6, ints);
   }

   public void updateBlocks() {
      this.setActivePlayerChunksAndCheckLight();
   }

   public WorldSavedData loadItemData(Class clazz, String str) {
      return this.mapStorage.loadData(clazz, str);
   }

   public float getCelestialAngleRadians(float f) {
      return this.getCelestialAngle(f) * 3.1415927F * 2.0F;
   }

   public boolean isMaterialInBB(double d, double d2, double d3, double d4, double d5, double d6, Material material) {
      int n = MathHelper.floor_double(d);
      int n2 = MathHelper.floor_double(d4 + 1.0);
      int n3 = MathHelper.floor_double(d2);
      int n4 = MathHelper.floor_double(d5 + 1.0);
      int n5 = MathHelper.floor_double(d3);
      int n6 = MathHelper.floor_double(d6 + 1.0);
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n; i < n2; i++) {
         for (int j = n3; j < n4; j++) {
            for (int k = n5; k < n6; k++) {
               if (this.getBlockState(pos.set(i, j, k)).getBlock().getMaterial() == material) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public EntityPlayer getClosestPlayerToEntity(Entity entity, double d) {
      return this.getClosestPlayer(entity.posX, entity.posY, entity.posZ, d);
   }

   public MovingObjectPosition rayTraceBlocks(double d, double d2, double d3, double d4, double d5, double d6) {
      return this.rayTraceBlocks(d, d2, d3, d4, d5, d6, false, false, false);
   }

   public void playAuxSFXAtEntity(EntityPlayer player, int n, BlockPos pos, int n2) {
      try {
         for (int i = 0; i < this.worldAccesses.size(); i++) {
            ((IWorldAccess)this.worldAccesses.get(i)).playAuxSFX(player, n, pos, n2);
         }
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Playing level event");
         CrashReportCategory crashReportCategory = crashReport.makeCategory("Level event being played");
         crashReportCategory.addCrashSection("Block coordinates", CrashReportCategory.getCoordinateInfo(pos));
         crashReportCategory.addCrashSection("Event source", player);
         crashReportCategory.addCrashSection("Event type", n);
         crashReportCategory.addCrashSection("Event data", n2);
         throw new ReportedException(crashReport);
      }
   }

   public boolean checkNoEntityCollision(AxisAlignedBB box, Entity entity) {
      List list = this.getEntitiesWithinAABBExcludingEntity((Entity)null, box);

      for (int i = 0; i < list.size(); i++) {
         Entity entity2 = (Entity)list.get(i);
         if (!entity2.isDead && entity2.preventEntitySpawning && entity2 != entity && (entity == null || entity.ridingEntity != entity2 && entity.riddenByEntity != entity2)) {
            return false;
         }
      }

      return true;
   }

   public boolean isAreaLoaded(BlockPos pos, BlockPos pos2, boolean flag) {
      return this.isAreaLoaded(pos.getX(), pos.getY(), pos.getZ(), pos2.getX(), pos2.getY(), pos2.getZ(), flag);
   }

   public void setItemData(String str, WorldSavedData worldSavedData) {
      this.mapStorage.setData(str, worldSavedData);
   }

   public boolean spawnEntityInWorld(Entity entity) {
      int n = MathHelper.floor_double(entity.posX / 16.0);
      int n2 = MathHelper.floor_double(entity.posZ / 16.0);
      boolean forceSpawn = entity.forceSpawn;
      if (entity instanceof EntityPlayer) {
         forceSpawn = true;
      }

      if (!forceSpawn && !this.isChunkLoaded(n, n2, true)) {
         return false;
      } else {
         if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (player.getGameProfile() != null) {
               this.playerEntityMap.put(player.getGameProfile().getName(), player);
            }

            this.playerEntities.add(player);
            this.updateAllPlayersSleepingFlag();
         }

         this.getChunkFromChunkCoords(n, n2).addEntity(entity);
         this.loadedEntityList.add(entity);
         this.onEntityAdded(entity);
         return true;
      }
   }

   public TileEntity getTileEntity(BlockPos pos) {
      if (!this.isValid(pos)) {
         return null;
      } else {
         TileEntity tileEntity = null;
         if (this.processingLoadedTiles) {
            for (int i = 0; i < this.addedTileEntityList.size(); i++) {
               TileEntity tileEntity2 = (TileEntity)this.addedTileEntityList.get(i);
               if (!tileEntity2.isInvalid() && tileEntity2.getPos().equals(pos)) {
                  tileEntity = tileEntity2;
                  break;
               }
            }
         }

         if (tileEntity == null) {
            tileEntity = this.getChunkFromBlockCoords(pos).getTileEntity(pos, Chunk.EnumCreateEntityType.IMMEDIATE);
         }

         if (tileEntity == null) {
            for (int j = 0; j < this.addedTileEntityList.size(); j++) {
               TileEntity tileEntity3 = (TileEntity)this.addedTileEntityList.get(j);
               if (!tileEntity3.isInvalid() && tileEntity3.getPos().equals(pos)) {
                  tileEntity = tileEntity3;
                  break;
               }
            }
         }

         return tileEntity;
      }
   }

   public List getCollidingBoundingBoxesCapped(Entity entity, AxisAlignedBB box) {
      ArrayList list = Lists.newArrayList();
      int n = MathHelper.floor_double(box.minX);
      int n2 = MathHelper.floor_double(box.maxX + 1.0);
      int n3 = MathHelper.floor_double(box.minY);
      int n4 = MathHelper.floor_double(box.maxY + 1.0);
      int n5 = MathHelper.floor_double(box.minZ);
      int n6 = MathHelper.floor_double(box.maxZ + 1.0);
      WorldBorder worldBorder2 = this.getWorldBorder();
      boolean isOutsideBorder = entity.isOutsideBorder();
      boolean isInsideBorder = this.isInsideBorder(worldBorder2, entity);
      IBlockState defaultState = Blocks.stone.getDefaultState();
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
      int n7 = 8000;

      for (int i = n; i < n2; i++) {
         for (int j = n5; j < n6; j++) {
            if (this.isBlockLoaded(pos.set(i, 64, j))) {
               for (int k = n3 - 1; k < n4; k++) {
                  pos.set(i, k, j);
                  if (isOutsideBorder && isInsideBorder) {
                     entity.setOutsideBorder(false);
                  } else if (!isOutsideBorder && !isInsideBorder) {
                     entity.setOutsideBorder(true);
                  }

                  IBlockState state = defaultState;
                  if (worldBorder2.contains(pos) || !isInsideBorder) {
                     state = this.getBlockState(pos);
                  }

                  state.getBlock().addCollisionBoxesToList(this, pos, state, box, list, entity);
                  if (--n7 == 0) {
                     return list;
                  }
               }
            }
         }
      }

      double d = 0.25;
      List list2 = this.getEntitiesWithinAABBExcludingEntity(entity, box.expand(d, d, d));

      for (int m = 0; m < list2.size(); m++) {
         if (entity.riddenByEntity != list2 && entity.ridingEntity != list2) {
            AxisAlignedBB collisionBoundingBox = ((Entity)list2.get(m)).getCollisionBoundingBox();
            if (collisionBoundingBox != null && collisionBoundingBox.intersectsWith(box)) {
               list.add(collisionBoundingBox);
            }

            collisionBoundingBox = entity.getCollisionBox((Entity)list2.get(m));
            if (collisionBoundingBox != null && collisionBoundingBox.intersectsWith(box)) {
               list.add(collisionBoundingBox);
            }
         }
      }

      return list;
   }

   public boolean handleMaterialAcceleration(double d, double d2, double d3, double d4, double d5, double d6, Material material, Entity entity) {
      int n = MathHelper.floor_double(d);
      int n2 = MathHelper.floor_double(d4 + 1.0);
      int n3 = MathHelper.floor_double(d2);
      int n4 = MathHelper.floor_double(d5 + 1.0);
      int n5 = MathHelper.floor_double(d3);
      int n6 = MathHelper.floor_double(d6 + 1.0);
      if (!this.isAreaLoaded(n, n3, n5, n2, n4, n6, true)) {
         return false;
      } else {
         boolean flag = false;
         Vec3 vec = new Vec3(0.0, 0.0, 0.0);
         BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

         for (int i = n; i < n2; i++) {
            for (int j = n3; j < n4; j++) {
               for (int k = n5; k < n6; k++) {
                  pos.set(i, j, k);
                  IBlockState blockState = this.getBlockState(pos);
                  Block block = blockState.getBlock();
                  if (block.getMaterial() == material) {
                     if ((double)n4 >= ((double)((float)(j + 1) - BlockLiquid.getLiquidHeightPercent((Integer)blockState.getValue(BlockLiquid.LEVEL))))) {
                        flag = true;
                        vec = block.modifyAcceleration(this, pos, entity, vec);
                     }
                  }
               }
            }
         }

         if (vec.lengthVector() > 0.0 && entity.isPushedByWater()) {
            vec = vec.normalize();
            double d8 = 0.014;
            entity.motionX = entity.motionX + vec.xCoord * d8;
            entity.motionY = entity.motionY + vec.yCoord * d8;
            entity.motionZ = entity.motionZ + vec.zCoord * d8;
         }

         return flag;
      }
   }

   public IBlockState getBlockState(double d, double d2, double d3) {
      int n = MathHelper.floor_double(d);
      int n2 = MathHelper.floor_double(d2);
      int n3 = MathHelper.floor_double(d3);
      return this.getChunkFromBlockCoords(n, n3).getBlockState(n, n2, n3);
   }

   public CrashReportCategory addWorldInfoToCrashReport(CrashReport crashReport) {
      CrashReportCategory crashReportCategory = crashReport.makeCategoryDepth("Affected level", 1);
      crashReportCategory.addCrashSection("Level name", this.worldInfo == null ? "????" : this.worldInfo.getWorldName());
      crashReportCategory.addCrashSectionCallable("All players", new World.ValueCallable3(this));
      crashReportCategory.addCrashSectionCallable("Chunk stats", new World.ValueCallable4(this));

      try {
         this.worldInfo.addToCrashReport(crashReportCategory);
      } catch (Throwable e) {
         crashReportCategory.addCrashSectionThrowable("Level Data Unobtainable", e);
      }

      return crashReportCategory;
   }

   public void notifyBlockOfStateChange(BlockPos pos, Block block) {
      if (!this.isRemote) {
         IBlockState blockState = this.getBlockState(pos);

         try {
            blockState.getBlock().onNeighborBlockChange(this, pos, blockState, block);
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Exception while updating neighbours");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("Block being updated");
            crashReportCategory.addCrashSectionCallable("Source block type", new World.ValueCallable2(this, block));
            CrashReportCategory.addBlockInfo(crashReportCategory, pos, blockState);
            throw new ReportedException(crashReport);
         }
      }
   }

   public void setSkylightSubtracted(int n) {
      this.skylightSubtracted = n;
   }

   public abstract int getRenderDistanceChunks();

   public boolean isRainingAt(BlockPos pos) {
      if (!this.isRaining()) {
         return false;
      } else if (!this.canSeeSky(pos.getX(), pos.getY(), pos.getZ())) {
         return false;
      } else if (this.getPrecipitationHeight(pos).getY() > pos.getY()) {
         return false;
      } else {
         BiomeGenBase biomeGenForCoords = this.getBiomeGenForCoords(pos);
         return biomeGenForCoords.getEnableSnow() ? false : (this.canSnowAt(pos, false) ? false : biomeGenForCoords.canSpawnLightningBolt());
      }
   }

   public World init() {
      return this;
   }

   public MovingObjectPosition rayTraceBlocksCustom(double d, double d2, double d3, double d4, double d5, double d6, boolean flag, boolean flag2, boolean flag3) {
      return this.rayTraceBlocksCustom(d, d2, d3, d4, d5, d6, flag, flag2, flag3, false);
   }

   public List getPendingBlockUpdates2(StructureBoundingBox structureBoundingBox, boolean flag) {
      return null;
   }

   public void removePlayerEntityDangerously(Entity entity) {
      entity.setDead();
      if (entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;
         if (player.getGameProfile() != null) {
            this.playerEntityMap.remove(player.getGameProfile().getName());
         }

         this.playerEntities.remove(entity);
         this.updateAllPlayersSleepingFlag();
      }

      int chunkCoordX = entity.chunkCoordX;
      int chunkCoordZ = entity.chunkCoordZ;
      if (entity.addedToChunk && this.isChunkLoaded(chunkCoordX, chunkCoordZ, true)) {
         this.getChunkFromChunkCoords(chunkCoordX, chunkCoordZ).removeEntity(entity);
      }

      this.loadedEntityList.remove(entity);
      this.onEntityRemoved(entity);
   }

   public int getStrongPower(BlockPos pos) {
      int n = 0;
      n = Math.max(n, this.getStrongPower(pos.down(), EnumFacing.DOWN));
      if (n >= 15) {
         return n;
      } else {
         n = Math.max(n, this.getStrongPower(pos.up(), EnumFacing.UP));
         if (n >= 15) {
            return n;
         } else {
            n = Math.max(n, this.getStrongPower(pos.north(), EnumFacing.NORTH));
            if (n >= 15) {
               return n;
            } else {
               n = Math.max(n, this.getStrongPower(pos.south(), EnumFacing.SOUTH));
               if (n >= 15) {
                  return n;
               } else {
                  n = Math.max(n, this.getStrongPower(pos.west(), EnumFacing.WEST));
                  if (n >= 15) {
                     return n;
                  } else {
                     n = Math.max(n, this.getStrongPower(pos.east(), EnumFacing.EAST));
                     return n >= 15 ? n : n;
                  }
               }
            }
         }
      }
   }

   public boolean setBlockToAir(BlockPos pos) {
      return this.setBlockState(pos, Blocks.air.getDefaultState(), 3);
   }

   public Explosion createExplosion(Entity entity, double d, double d2, double d3, float f, boolean flag) {
      return this.newExplosion(entity, d, d2, d3, f, false, flag);
   }

   public BlockPos getHeight(BlockPos pos) {
      int n;
      if (pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000) {
         if (this.isChunkLoaded(pos.getX() >> 4, pos.getZ() >> 4, true)) {
            n = this.getChunkFromChunkCoords(pos.getX() >> 4, pos.getZ() >> 4).getHeightValue(pos.getX() & 15, pos.getZ() & 15);
         } else {
            n = 0;
         }
      } else {
         n = this.getSeaLevel() + 1;
      }

      return new BlockPos(pos.getX(), n, pos.getZ());
   }

   public void onEntityRemoved(Entity entity) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).onEntityRemoved(entity);
      }
   }

   public int getMoonPhase() {
      return this.provider.getMoonPhase(this.worldInfo.getWorldTime());
   }

   private boolean isValidHeight(BlockPos pos) {
      return pos.getY() >= 0 && pos.getY() < 256;
   }

   public WorldInfo getWorldInfo() {
      return this.worldInfo;
   }

   public void scheduleUpdate(BlockPos pos, Block block, int n) {
   }

   public void updateEntityWithOptionalForce(Entity entity, boolean flag) {
      int n = MathHelper.floor_double(entity.posX);
      int n2 = MathHelper.floor_double(entity.posZ);
      byte b = 32;
      if (!flag || this.isAreaLoaded(n - b, 0, n2 - b, n + b, 0, n2 + b, true)) {
         entity.lastTickPosX = entity.posX;
         entity.lastTickPosY = entity.posY;
         entity.lastTickPosZ = entity.posZ;
         entity.prevRotationYaw = entity.rotationYaw;
         entity.prevRotationPitch = entity.rotationPitch;
         if (flag && entity.addedToChunk) {
            entity.ticksExisted++;
            if (entity.ridingEntity != null) {
               entity.updateRidden();
            } else {
               entity.onUpdate();
            }
         }

         this.theProfiler.startSection("chunkCheck");
         if (Double.isNaN(entity.posX) || Double.isInfinite(entity.posX)) {
            entity.posX = entity.lastTickPosX;
         }

         if (Double.isNaN(entity.posY) || Double.isInfinite(entity.posY)) {
            entity.posY = entity.lastTickPosY;
         }

         if (Double.isNaN(entity.posZ) || Double.isInfinite(entity.posZ)) {
            entity.posZ = entity.lastTickPosZ;
         }

         if (Double.isNaN((double)entity.rotationPitch) || Double.isInfinite((double)entity.rotationPitch)) {
            entity.rotationPitch = entity.prevRotationPitch;
         }

         if (Double.isNaN((double)entity.rotationYaw) || Double.isInfinite((double)entity.rotationYaw)) {
            entity.rotationYaw = entity.prevRotationYaw;
         }

         int n3 = MathHelper.floor_double(entity.posX / 16.0);
         int n4 = MathHelper.floor_double(entity.posY / 16.0);
         int n5 = MathHelper.floor_double(entity.posZ / 16.0);
         if (!entity.addedToChunk || entity.chunkCoordX != n3 || entity.chunkCoordY != n4 || entity.chunkCoordZ != n5) {
            if (entity.addedToChunk && this.isChunkLoaded(entity.chunkCoordX, entity.chunkCoordZ, true)) {
               this.getChunkFromChunkCoords(entity.chunkCoordX, entity.chunkCoordZ).removeEntityAtIndex(entity, entity.chunkCoordY);
            }

            if (this.isChunkLoaded(n3, n5, true)) {
               entity.addedToChunk = true;
               this.getChunkFromChunkCoords(n3, n5).addEntity(entity);
            } else {
               entity.addedToChunk = false;
            }
         }

         this.theProfiler.endSection();
         if (flag && entity.addedToChunk && entity.riddenByEntity != null) {
            if (!entity.riddenByEntity.isDead && entity.riddenByEntity.ridingEntity == entity) {
               this.updateEntity(entity.riddenByEntity);
            } else {
               entity.riddenByEntity.ridingEntity = null;
               entity.riddenByEntity = null;
            }
         }
      }
   }

   public EnumDifficulty getDifficulty() {
      return this.getWorldInfo().getDifficulty();
   }

   public static boolean doesBlockHaveSolidTopSurface(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      Block block = blockState2.getBlock();
      return block.getMaterial().isOpaque() && block.isFullCube()
         ? true
         : (
            block instanceof BlockStairs
               ? blockState2.getValue(BlockStairs.HALF) == BlockStairs.EnumHalf.TOP
               : (
                  block instanceof BlockSlab
                     ? blockState2.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP
                     : (block instanceof BlockHopper ? true : (block instanceof BlockSnow ? (Integer)blockState2.getValue(BlockSnow.LAYERS) == 7 : false))
               )
         );
   }

   public int getRedstonePower(BlockPos pos, EnumFacing facing) {
      IBlockState blockState = this.getBlockState(pos);
      Block block = blockState.getBlock();
      return block.isNormalCube() ? this.getStrongPower(pos) : block.getWeakPower(this, pos, blockState, facing);
   }

   public MapStorage getMapStorage() {
      return this.mapStorage;
   }

   public void notifyLightSet(int n, int n2, int n3) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).notifyLightSet(n, n2, n3);
      }
   }

   public void notifyNeighborsRespectDebug(BlockPos pos, Block block) {
      if (this.worldInfo.getTerrainType() != WorldType.DEBUG_WORLD) {
         this.notifyNeighborsOfStateChange(pos, block);
      }
   }

   public void addTileEntities(Collection items) {
      if (this.processingLoadedTiles) {
         this.addedTileEntityList.addAll(items);
      } else {
         for (TileEntity tileEntity : (Iterable<TileEntity>)(items)) {
            this.loadedTileEntityList.add(tileEntity);
            if (tileEntity instanceof ITickable) {
               this.tickableTileEntities.add(tileEntity);
            }
         }
      }
   }

   public WorldChunkManager getWorldChunkManager() {
      return this.provider.getWorldChunkManager();
   }

   public int getSkylightSubtracted() {
      return this.skylightSubtracted;
   }

   public boolean addTileEntity(TileEntity tileEntity) {
      boolean flag = this.loadedTileEntityList.add(tileEntity);
      if (flag && tileEntity instanceof ITickable) {
         this.tickableTileEntities.add(tileEntity);
      }

      return flag;
   }

   public boolean checkLight(int n, int n2, int n3) {
      boolean flag = false;
      if (!this.provider.getHasNoSky()) {
         flag |= this.checkLightFor(EnumSkyBlock.SKY, n, n2, n3);
      }

      return flag | this.checkLightFor(EnumSkyBlock.BLOCK, n, n2, n3);
   }

   public Random setRandomSeed(int n, int n2, int n3) {
      long l = (long)n * 341873128712L + (long)n2 * 132897987541L + this.getWorldInfo().getSeed() + (long)n3;
      this.rand.setSeed(l);
      return this.rand;
   }

   public boolean extendedLevelsInChunkCache() {
      return false;
   }

   public boolean isAABBInMaterial(AxisAlignedBB box, Material material) {
      int n = MathHelper.floor_double(box.minX);
      int n2 = MathHelper.floor_double(box.maxX + 1.0);
      int n3 = MathHelper.floor_double(box.minY);
      int n4 = MathHelper.floor_double(box.maxY + 1.0);
      int n5 = MathHelper.floor_double(box.minZ);
      int n6 = MathHelper.floor_double(box.maxZ + 1.0);
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n; i < n2; i++) {
         for (int j = n3; j < n4; j++) {
            for (int k = n5; k < n6; k++) {
               IBlockState blockState = this.getBlockState(pos.set(i, j, k));
               if (blockState.getBlock().getMaterial() == material) {
                  int value = (Integer)blockState.getValue(BlockLiquid.LEVEL);
                  double d = (double)(j + 1);
                  if (value < 8) {
                     d = (double)(j + 1) - (double)value / 8.0;
                  }

                  if (d >= box.minY) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public DifficultyInstance getDifficultyForLocation(BlockPos pos) {
      long l = 0L;
      float f = 0.0F;
      if (this.isBlockLoaded(pos)) {
         f = this.getCurrentMoonPhaseFactor();
         l = this.getChunkFromBlockCoords(pos).getInhabitedTime();
      }

      return new DifficultyInstance(this.getDifficulty(), this.getWorldTime(), l, f);
   }

   private boolean isValid(BlockPos pos) {
      return pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000 && pos.getY() >= 0 && pos.getY() < 256;
   }

   public List getPlayers(Class clazz, Predicate predicate) {
      ArrayList list = Lists.newArrayList();

      for (Entity entity : (Iterable<Entity>)(this.playerEntities)) {
         if (clazz.isAssignableFrom(entity.getClass()) && predicate.apply(entity)) {
            list.add(entity);
         }
      }

      return list;
   }

   public boolean tickUpdates(boolean flag) {
      return false;
   }

   public BlockPos getTopSolidOrLiquidBlock(BlockPos pos) {
      Chunk chunkFromBlockCoords = this.getChunkFromBlockCoords(pos);
      BlockPos pos2 = new BlockPos(pos.getX(), chunkFromBlockCoords.getTopFilledSegment() + 16, pos.getZ());

      while (pos2.getY() >= 0) {
         BlockPos pos3 = pos2.down();
         Material material = chunkFromBlockCoords.getBlock(pos3).getMaterial();
         if (material.blocksMovement() && material != Material.leaves) {
            break;
         }

         pos2 = pos3;
      }

      return pos2;
   }

   public void playAuxSFX(int n, BlockPos pos, int n2) {
      this.playAuxSFXAtEntity((EntityPlayer)null, n, pos, n2);
   }

   public GameRules getGameRules() {
      return this.worldInfo.getGameRulesInstance();
   }

   public List getPendingBlockUpdates(Chunk chunk, boolean flag) {
      return null;
   }

   public void updateComparatorOutputLevel(BlockPos pos, Block block) {
      for (Object obj : EnumFacing.Plane.HORIZONTAL) {
         BlockPos pos2 = pos.offset((EnumFacing)obj);
         if (this.isBlockLoaded(pos2)) {
            IBlockState blockState = this.getBlockState(pos2);
            if (Blocks.unpowered_comparator.isAssociated(blockState.getBlock())) {
               blockState.getBlock().onNeighborBlockChange(this, pos2, blockState, block);
            } else if (blockState.getBlock().isNormalCube()) {
               pos2 = pos2.offset((EnumFacing)obj);
               blockState = this.getBlockState(pos2);
               if (Blocks.unpowered_comparator.isAssociated(blockState.getBlock())) {
                  blockState.getBlock().onNeighborBlockChange(this, pos2, blockState, block);
               }
            }
         }
      }
   }

   public boolean isAirBlock(BlockPos pos) {
      return this.getBlockState(pos).getBlock().getMaterial() == Material.air;
   }

   public float getCurrentMoonPhaseFactor() {
      return WorldProvider.moonPhaseFactors[this.provider.getMoonPhase(this.worldInfo.getWorldTime())];
   }

   public void initialize(WorldSettings worldSettings) {
      this.worldInfo.setServerInitialized(true);
   }

   public MovingObjectPosition rayTraceBlocks(double d, double d2, double d3, double d4, double d5, double d6, boolean flag) {
      return this.rayTraceBlocks(d, d2, d3, d4, d5, d6, flag, false, false);
   }

   public IChunkProvider getChunkProvider() {
      return this.chunkProvider;
   }

   public void setWorldTime(long l) {
      this.worldInfo.setWorldTime(l);
   }

   public void scheduleBlockUpdate(BlockPos pos, Block block, int n, int n2) {
   }

   public boolean canSeeSky(int n, int n2, int n3) {
      return this.getChunkFromBlockCoords(n, n3).canSeeSky(n, n2, n3);
   }

   public void markChunkDirty(BlockPos pos, TileEntity tileEntity) {
      if (this.isBlockLoaded(pos)) {
         this.getChunkFromBlockCoords(pos).setChunkModified();
      }
   }

   public VillageCollection getVillageCollection() {
      return this.villageCollectionObj;
   }

   public List getCollidingBoundingBoxes(Entity entity, AxisAlignedBB box) {
      ArrayList list = Lists.newArrayList();
      int n = MathHelper.floor_double(box.minX);
      int n2 = MathHelper.floor_double(box.maxX + 1.0);
      int n3 = MathHelper.floor_double(box.minY);
      int n4 = MathHelper.floor_double(box.maxY + 1.0);
      int n5 = MathHelper.floor_double(box.minZ);
      int n6 = MathHelper.floor_double(box.maxZ + 1.0);
      WorldBorder worldBorder2 = this.getWorldBorder();
      boolean isOutsideBorder = entity.isOutsideBorder();
      boolean isInsideBorder = this.isInsideBorder(worldBorder2, entity);
      IBlockState defaultState = Blocks.stone.getDefaultState();
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n; i < n2; i++) {
         for (int j = n5; j < n6; j++) {
            if (this.isBlockLoaded(pos.set(i, 64, j))) {
               for (int k = n3 - 1; k < n4; k++) {
                  pos.set(i, k, j);
                  if (isOutsideBorder && isInsideBorder) {
                     entity.setOutsideBorder(false);
                  } else if (!isOutsideBorder && !isInsideBorder) {
                     entity.setOutsideBorder(true);
                  }

                  IBlockState state = defaultState;
                  if (worldBorder2.contains(pos) || !isInsideBorder) {
                     state = this.getBlockState(pos);
                  }

                  state.getBlock().addCollisionBoxesToList(this, pos, state, box, list, entity);
               }
            }
         }
      }

      double d = 0.25;
      List list2 = this.getEntitiesWithinAABBExcludingEntity(entity, box.expand(d, d, d));

      for (int m = 0; m < list2.size(); m++) {
         if (entity.riddenByEntity != list2 && entity.ridingEntity != list2) {
            AxisAlignedBB collisionBoundingBox = ((Entity)list2.get(m)).getCollisionBoundingBox();
            if (collisionBoundingBox != null && collisionBoundingBox.intersectsWith(box)) {
               list.add(collisionBoundingBox);
            }

            collisionBoundingBox = entity.getCollisionBox((Entity)list2.get(m));
            if (collisionBoundingBox != null && collisionBoundingBox.intersectsWith(box)) {
               list.add(collisionBoundingBox);
            }
         }
      }

      return list;
   }

   public boolean canBlockFreeze(BlockPos pos, boolean flag) {
      if (this.getBiomeGenForCoords(pos).getFloatTemperature(pos) > 0.15F) {
         return false;
      } else {
         if (pos.getY() >= 0 && pos.getY() < 256 && this.getLightFor(EnumSkyBlock.BLOCK, pos.getX(), pos.getY(), pos.getZ()) < 10) {
            IBlockState blockState = this.getBlockState(pos);
            Block block = blockState.getBlock();
            if ((block == Blocks.water || block == Blocks.flowing_water) && (Integer)blockState.getValue(BlockLiquid.LEVEL) == 0) {
               if (!flag) {
                  return true;
               }

               if (!(this.isWater(pos.west()) && this.isWater(pos.east()) && this.isWater(pos.north()) && this.isWater(pos.south()))) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public long getTotalWorldTime() {
      return this.worldInfo.getWorldTotalTime();
   }

   public MovingObjectPosition rayTraceBlocksFast(double d, double d2, double d3, double d4, double d5, double d6) {
      return this.rayTraceBlocksFast(d, d2, d3, d4, d5, d6, false, false, false);
   }

   public void sendBlockBreakProgress(int n, BlockPos pos, int n2) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).sendBlockBreakProgress(n, pos, n2);
      }
   }

   public Scoreboard getScoreboard() {
      return this.worldScoreboard;
   }

   public int getCombinedLight(int n, int n2, int n3, int n4) {
      int lightFromNeighborsFor = this.getLightFromNeighborsFor(EnumSkyBlock.SKY, n, n2, n3);
      int lightFromNeighborsFor2 = this.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, n, n2, n3);
      if (lightFromNeighborsFor2 < n4) {
         lightFromNeighborsFor2 = n4;
      }

      return lightFromNeighborsFor << 20 | lightFromNeighborsFor2 << 4;
   }

   public boolean destroyBlock(BlockPos pos, boolean flag) {
      IBlockState blockState = this.getBlockState(pos);
      Block block = blockState.getBlock();
      if (block.getMaterial() == Material.air) {
         return false;
      } else {
         this.playAuxSFX(2001, pos, Block.getStateId(blockState));
         if (flag) {
            block.dropBlockAsItem(this, pos, blockState, 0);
         }

         return this.setBlockState(pos, Blocks.air.getDefaultState(), 3);
      }
   }

   public boolean isBlockTickPending(BlockPos pos, Block block) {
      return false;
   }

   public void joinEntityInSurroundings(Entity entity) {
      int n = MathHelper.floor_double(entity.posX / 16.0);
      int n2 = MathHelper.floor_double(entity.posZ / 16.0);
      byte b = 2;

      for (int i = n - b; i <= n + b; i++) {
         for (int j = n2 - b; j <= n2 + b; j++) {
            this.getChunkFromChunkCoords(i, j);
         }
      }

      if (!this.loadedEntityList.contains(entity)) {
         this.loadedEntityList.add(entity);
      }
   }

   public void setThunderStrength(float f) {
      this.prevThunderingStrength = f;
      this.thunderingStrength = f;
   }

   public int getStrongPower(BlockPos pos, EnumFacing facing) {
      IBlockState blockState = this.getBlockState(pos);
      return blockState.getBlock().getStrongPower(this, pos, blockState, facing);
   }

   public void playRecord(BlockPos pos, String str) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).playRecord(str, pos);
      }
   }

   public BlockPos getSpawnPoint() {
      BlockPos pos = new BlockPos(this.worldInfo.getSpawnX(), this.worldInfo.getSpawnY(), this.worldInfo.getSpawnZ());
      if (!this.getWorldBorder().contains(pos)) {
         pos = this.getHeight(new BlockPos(this.getWorldBorder().getCenterX(), 0.0, this.getWorldBorder().getCenterZ()));
      }

      return pos;
   }

   public Entity findNearestEntityWithinAABB(Class clazz, AxisAlignedBB box, Entity entity) {
      List entitiesWithinAABB = this.getEntitiesWithinAABB(clazz, box);
      Entity entity2 = null;
      double d = 1.7976931348623157E308;

      for (int i = 0; i < entitiesWithinAABB.size(); i++) {
         Entity entity3 = (Entity)entitiesWithinAABB.get(i);
         if (entity3 != entity && EntitySelectors.NOT_SPECTATING.apply(entity3)) {
            double distanceSqToEntity = entity.getDistanceSqToEntity(entity3);
            if (distanceSqToEntity <= d) {
               entity2 = entity3;
               d = distanceSqToEntity;
            }
         }
      }

      return entity2;
   }

   public boolean setBlockState(BlockPos pos, IBlockState state) {
      return this.setBlockState(pos, state, 3);
   }

   public void playMoodSoundAndCheckLight(int n, int n2, Chunk chunk) {
      this.theProfiler.endStartSection("moodSound");
      if (this.ambientTickCountdown == 0 && !this.isRemote) {
         this.updateLCG = this.updateLCG * 3 + 1013904223;
         int n3 = this.updateLCG >> 2;
         int n4 = n3 & 15;
         int n5 = n3 >> 8 & 15;
         int n6 = n3 >> 16 & 0xFF;
         Block block = chunk.getBlock(n4, n6, n5);
         n4 += n;
         n5 += n2;
         if (block.getMaterial() == Material.air && this.getLightFromNeighbors(n4, n6, n5) <= this.rand.nextInt(8) && this.getLightFor(EnumSkyBlock.SKY, n4, n6, n5) <= 0) {
            EntityPlayer closestPlayer = this.getClosestPlayer((double)n4 + 0.5, (double)n6 + 0.5, (double)n5 + 0.5, 8.0);
            if (closestPlayer != null && closestPlayer.getDistanceSq((double)n4 + 0.5, (double)n6 + 0.5, (double)n5 + 0.5) > 4.0) {
               this.playSoundEffect((double)n4 + 0.5, (double)n6 + 0.5, (double)n5 + 0.5, "ambient.cave.cave", 0.7F, 0.8F + this.rand.nextFloat() * 0.2F);
               this.ambientTickCountdown = this.rand.nextInt(12000) + 6000;
            }
         }
      }

      this.theProfiler.endStartSection("checkLight");
      chunk.enqueueRelightChecks();
   }

   public MovingObjectPosition rayTraceBlocksCustom(
      double d, double d2, double d3, double d4, double d5, double d6, boolean flag, boolean flag2, boolean flag3, boolean flag4
   ) {
      if (Double.isNaN(d) || Double.isNaN(d2) || Double.isNaN(d3)) {
         return null;
      } else if (!Double.isNaN(d4) && !Double.isNaN(d5) && !Double.isNaN(d6)) {
         int n = MathHelper.floor_double(d4);
         int n2 = MathHelper.floor_double(d5);
         int n3 = MathHelper.floor_double(d6);
         int n4 = MathHelper.floor_double(d);
         int n5 = MathHelper.floor_double(d2);
         int n6 = MathHelper.floor_double(d3);
         BlockPos pos = new BlockPos(n4, n5, n6);
         IBlockState blockState = this.getBlockState(pos);
         Block block = blockState.getBlock();
         if ((!flag2 || block.getCollisionBoundingBox(this, blockState, pos.getX(), pos.getY(), pos.getZ()) != null) && block.canCollideCheck(blockState, flag, flag4)) {
            MovingObjectPosition hit = block.collisionRayTraceExpanded(this, pos, d, d2, d3, d4, d5, d6);
            if (hit != null) {
               return hit;
            }
         }

         MovingObjectPosition hit2 = null;
         int n7 = 200;

         while (n7-- >= 0) {
            if (Double.isNaN(d) || Double.isNaN(d2) || Double.isNaN(d3)) {
               return null;
            }

            if (n4 == n && n5 == n2 && n6 == n3) {
               return flag3 ? hit2 : null;
            }

            boolean flag5 = true;
            boolean flag6 = true;
            boolean flag7 = true;
            double d7 = 999.0;
            double d8 = 999.0;
            double d9 = 999.0;
            if (n > n4) {
               d7 = (double)n4 + 1.0;
            } else if (n < n4) {
               d7 = (double)n4 + 0.0;
            } else {
               flag5 = false;
            }

            if (n2 > n5) {
               d8 = (double)n5 + 1.0;
            } else if (n2 < n5) {
               d8 = (double)n5 + 0.0;
            } else {
               flag6 = false;
            }

            if (n3 > n6) {
               d9 = (double)n6 + 1.0;
            } else if (n3 < n6) {
               d9 = (double)n6 + 0.0;
            } else {
               flag7 = false;
            }

            double d10 = 999.0;
            double d11 = 999.0;
            double d12 = 999.0;
            double d13 = d4 - d;
            double d14 = d5 - d2;
            double d15 = d6 - d3;
            if (flag5) {
               d10 = (d7 - d) / d13;
            }

            if (flag6) {
               d11 = (d8 - d2) / d14;
            }

            if (flag7) {
               d12 = (d9 - d3) / d15;
            }

            if (d10 == -0.0) {
               d10 = -1.0E-4;
            }

            if (d11 == -0.0) {
               d11 = -1.0E-4;
            }

            if (d12 == -0.0) {
               d12 = -1.0E-4;
            }

            EnumFacing facing;
            if (d10 < d11 && d10 < d12) {
               facing = n > n4 ? EnumFacing.WEST : EnumFacing.EAST;
               d = d7;
               d2 += d14 * d10;
               d3 += d15 * d10;
            } else if (d11 < d12) {
               facing = n2 > n5 ? EnumFacing.DOWN : EnumFacing.UP;
               d += d13 * d11;
               d2 = d8;
               d3 += d15 * d11;
            } else {
               facing = n3 > n6 ? EnumFacing.NORTH : EnumFacing.SOUTH;
               d += d13 * d12;
               d2 += d14 * d12;
               d3 = d9;
            }

            n4 = MathHelper.floor_double(d) - (facing == EnumFacing.EAST ? 1 : 0);
            n5 = MathHelper.floor_double(d2) - (facing == EnumFacing.UP ? 1 : 0);
            n6 = MathHelper.floor_double(d3) - (facing == EnumFacing.SOUTH ? 1 : 0);
            pos = new BlockPos(n4, n5, n6);
            IBlockState blockState2 = this.getBlockState(pos);
            Block block2 = blockState2.getBlock();
            if (!flag2 || block2.getCollisionBoundingBox(this, blockState2, pos.getX(), pos.getY(), pos.getZ()) != null) {
               if (block2.canCollideCheck(blockState2, flag, flag4)) {
                  MovingObjectPosition hit3 = block2.collisionRayTraceExpanded(this, pos, d, d2, d3, d4, d5, d6);
                  if (hit3 != null) {
                     return hit3;
                  }
               } else {
                  hit2 = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, new Vec3(d, d2, d3), facing, pos);
               }
            }
         }

         return flag3 ? hit2 : null;
      } else {
         return null;
      }
   }

   public void playBroadcastSound(int n, BlockPos pos, int n2) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).broadcastSound(n, pos, n2);
      }
   }

   public Calendar getCurrentDate() {
      if (this.getTotalWorldTime() % 600L == 0L) {
         this.theCalendar.setTimeInMillis(MinecraftServer.getCurrentTimeMillis());
      }

      return this.theCalendar;
   }

   private boolean isValid(int n, int n2, int n3) {
      return n >= -30000000 && n3 >= -30000000 && n < 30000000 && n3 < 30000000 && n2 >= 0 && n2 < 256;
   }

   public float getRainStrength(float f) {
      return Augustus.getInstance().getModuleManager().ambiance.isToggled()
         ? (float)Augustus.getInstance().getModuleManager().ambiance.rain.getValue()
         : this.prevRainingStrength + (this.rainingStrength - this.prevRainingStrength) * f;
   }

   public boolean isSidePowered(BlockPos pos, EnumFacing facing) {
      return this.getRedstonePower(pos, facing) > 0;
   }

   public Explosion newExplosion(Entity entity, double d, double d2, double d3, float f, boolean flag, boolean flag2) {
      Explosion explosion = new Explosion(this, entity, d, d2, d3, f, flag, flag2);
      explosion.doExplosionA();
      explosion.doExplosionB(true);
      return explosion;
   }

   public boolean isBlockLoaded(BlockPos pos) {
      return this.isBlockLoaded(pos, true);
   }

   public void forceBlockUpdateTick(Block block, BlockPos pos, Random rand2) {
      this.scheduledUpdatesAreImmediate = true;
      block.updateTick(this, pos, this.getBlockState(pos), rand2);
      this.scheduledUpdatesAreImmediate = false;
   }

   public boolean isAreaLoaded(StructureBoundingBox structureBoundingBox, boolean flag) {
      return this.isAreaLoaded(structureBoundingBox.minX, structureBoundingBox.minY, structureBoundingBox.minZ, structureBoundingBox.maxX, structureBoundingBox.maxY, structureBoundingBox.maxZ, flag);
   }

   public boolean isBlockPowered(BlockPos pos) {
      return this.getRedstonePower(pos.down(), EnumFacing.DOWN) > 0
         ? true
         : (
            this.getRedstonePower(pos.up(), EnumFacing.UP) > 0
               ? true
               : (
                  this.getRedstonePower(pos.north(), EnumFacing.NORTH) > 0
                     ? true
                     : (
                        this.getRedstonePower(pos.south(), EnumFacing.SOUTH) > 0
                           ? true
                           : (this.getRedstonePower(pos.west(), EnumFacing.WEST) > 0 ? true : this.getRedstonePower(pos.east(), EnumFacing.EAST) > 0)
                     )
               )
         );
   }

   public Chunk getChunkFromChunkCoords(int n, int n2) {
      return this.chunkProvider.provideChunk(n, n2);
   }

   private int updateEntityAtIndex(Entity entity, int n) {
      if (entity.ridingEntity != null) {
         if (!entity.ridingEntity.isDead && entity.ridingEntity.riddenByEntity == entity) {
            return n;
         }

         entity.ridingEntity.riddenByEntity = null;
         entity.ridingEntity = null;
      }

      this.theProfiler.startSection("tick");
      if (!entity.isDead) {
         try {
            this.updateEntity(entity);
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Ticking entity");
            entity.addEntityCrashInfo(crashReport.makeCategory("Entity being ticked"));
            throw new ReportedException(crashReport);
         }
      }

      this.theProfiler.endSection();
      this.theProfiler.startSection("remove");
      if (entity.isDead) {
         int chunkCoordX = entity.chunkCoordX;
         int chunkCoordZ = entity.chunkCoordZ;
         if (entity.addedToChunk && this.isChunkLoaded(chunkCoordX, chunkCoordZ, true)) {
            this.getChunkFromChunkCoords(chunkCoordX, chunkCoordZ).removeEntity(entity);
         }

         this.loadedEntityList.remove(n--);
         this.onEntityRemoved(entity);
      }

      this.theProfiler.endSection();
      return n;
   }

   public boolean extinguishFire(EntityPlayer player, BlockPos pos, EnumFacing facing) {
      pos = pos.offset(facing);
      if (this.getBlockState(pos).getBlock() == Blocks.fire) {
         this.playAuxSFXAtEntity(player, 1004, pos, 0);
         this.setBlockToAir(pos);
         return true;
      } else {
         return false;
      }
   }

   public EntityPlayer getPlayerEntityByName(String str) {
      for (int i = 0; i < this.playerEntities.size(); i++) {
         EntityPlayer player = (EntityPlayer)this.playerEntities.get(i);
         if (str.equals(player.getName())) {
            return player;
         }
      }

      return null;
   }

   public boolean canBlockFreezeNoWater(BlockPos pos) {
      return this.canBlockFreeze(pos, true);
   }

   public int getLightFor(EnumSkyBlock enumSkyBlock, int n, int n2, int n3) {
      if (n2 < 0) {
         n2 = 0;
      }

      if (!this.isValid(n, n2, n3)) {
         return enumSkyBlock.defaultLightValue;
      } else if (!this.isBlockLoaded(n, n2, n3)) {
         return enumSkyBlock.defaultLightValue;
      } else {
         return this.getChunkFromBlockCoords(n, n3).getLightFor(enumSkyBlock, n, n2, n3);
      }
   }

   public boolean isAreaLoaded(int n, int n2, int n3, int n4, boolean flag) {
      return this.isAreaLoaded(n - n4, n2 - n4, n3 - n4, n + n4, n2 + n4, n3 + n4, flag);
   }

   public boolean isAirBlock(int n, int n2, int n3) {
      return this.getBlockState2(n, n2, n3).getBlock().getMaterial() == Material.air;
   }

   public int getChunksLowestHorizon(int n, int n2) {
      if (n >= -30000000 && n2 >= -30000000 && n < 30000000 && n2 < 30000000) {
         if (!this.isChunkLoaded(n >> 4, n2 >> 4, true)) {
            return 0;
         } else {
            return this.getChunkFromChunkCoords(n >> 4, n2 >> 4).getLowestHeight();
         }
      } else {
         return this.getSeaLevel() + 1;
      }
   }

   public void removeEntity(Entity entity) {
      if (entity.riddenByEntity != null) {
         entity.riddenByEntity.mountEntity((Entity)null);
      }

      if (entity.ridingEntity != null) {
         entity.mountEntity((Entity)null);
      }

      entity.setDead();
      if (entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;
         if (player.getGameProfile() != null) {
            this.playerEntityMap.remove(player.getGameProfile().getName());
         }

         this.playerEntities.remove(entity);
         this.updateAllPlayersSleepingFlag();
         this.onEntityRemoved(entity);
      }
   }

   public List getEntitiesWithinAABB(Class clazz, AxisAlignedBB box) {
      return this.getEntitiesWithinAABB(clazz, box, EntitySelectors.NOT_SPECTATING);
   }

   public int getPrecipitationHeightY(int n, int n2) {
      return this.getChunkFromBlockCoords(n, n2).getPrecipitationHeightY(n, n2);
   }

   public void updateAllPlayersSleepingFlag() {
   }

   public boolean isFlammableWithin(AxisAlignedBB box) {
      int n = MathHelper.floor_double(box.minX);
      int n2 = MathHelper.floor_double(box.maxX + 1.0);
      int n3 = MathHelper.floor_double(box.minY);
      int n4 = MathHelper.floor_double(box.maxY + 1.0);
      int n5 = MathHelper.floor_double(box.minZ);
      int n6 = MathHelper.floor_double(box.maxZ + 1.0);
      if (this.isAreaLoaded(n, n3, n5, n2, n4, n6, true)) {
         BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

         for (int i = n; i < n2; i++) {
            for (int j = n3; j < n4; j++) {
               for (int k = n5; k < n6; k++) {
                  Block block = this.getBlockState(pos.set(i, j, k)).getBlock();
                  if (block == Blocks.fire || block == Blocks.flowing_lava || block == Blocks.lava) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public List getEntitiesInAABBexcluding(Entity entity, AxisAlignedBB box, Predicate predicate) {
      ArrayList list = Lists.newArrayList();
      int n = MathHelper.floor_double((box.minX - 2.0) / 16.0);
      int n2 = MathHelper.floor_double((box.maxX + 2.0) / 16.0);
      int n3 = MathHelper.floor_double((box.minZ - 2.0) / 16.0);
      int n4 = MathHelper.floor_double((box.maxZ + 2.0) / 16.0);
      if (ClientManagers.moduleManager.exploitFixer.isToggled()) {
         int n5 = 0;

         for (int i = n; i <= n2; i++) {
            for (int j = n3; j <= n4; j++) {
               if (this.isChunkLoaded(i, j, true)) {
                  this.getChunkFromChunkCoords(i, j).getEntitiesWithinAABBForEntity(entity, box, list, predicate);
               }

               if (++n5 > 100) {
                  return list;
               }
            }
         }
      } else {
         for (int k = n; k <= n2; k++) {
            for (int m = n3; m <= n4; m++) {
               if (this.isChunkLoaded(k, m, true)) {
                  this.getChunkFromChunkCoords(k, m).getEntitiesWithinAABBForEntity(entity, box, list, predicate);
               }
            }
         }
      }

      return list;
   }

   public boolean isAreaLoaded(BlockPos pos, BlockPos pos2) {
      return this.isAreaLoaded(pos, pos2, true);
   }

   public String getProviderName() {
      return this.chunkProvider.makeString();
   }

   public void addBlockEvent(BlockPos pos, Block block, int n, int n2) {
      block.onBlockEventReceived(this, pos, this.getBlockState(pos), n, n2);
   }

   public float getStarBrightness(float f) {
      float celestialAngle = this.getCelestialAngle(f);
      float f2 = 1.0F - (MathHelper.cos(celestialAngle * 3.1415927F * 2.0F) * 2.0F + 0.25F);
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      return f2 * f2 * 0.5F;
   }

   public IBlockState getBlockState2(int n, int n2, int n3) {
      return this.getChunkFromBlockCoords(n, n3).getBlockState(n, n2, n3);
   }

   public void updateEntity(Entity entity) {
      this.updateEntityWithOptionalForce(entity, true);
   }

   public Chunk getChunkFromBlockCoords(int n, int n2) {
      return this.getChunkFromChunkCoords(n >> 4, n2 >> 4);
   }

   public boolean isBlockFullCube(BlockPos pos) {
      IBlockState blockState = this.getBlockState(pos);
      AxisAlignedBB collisionBoundingBox = blockState.getBlock().getCollisionBoundingBox(this, blockState, pos.getX(), pos.getY(), pos.getZ());
      return collisionBoundingBox != null && collisionBoundingBox.getAverageEdgeLength() >= 1.0;
   }

   public void setTotalWorldTime(long l) {
      this.worldInfo.setWorldTotalTime(l);
   }

   public boolean isRaining() {
      return (double)this.getRainStrength(1.0F) > 0.2;
   }

   public void playSound(double d, double d2, double d3, String str, float f, float f2, boolean flag) {
   }

   public void loadEntities(Collection items) {
      this.loadedEntityList.addAll(items);

      for (Entity entity : (Iterable<Entity>)(items)) {
         this.onEntityAdded(entity);
      }
   }

   public Vec3 getFogColor(float f) {
      float celestialAngle = this.getCelestialAngle(f);
      return this.provider.getFogColor(celestialAngle, f);
   }

   public List getLoadedEntityList() {
      return this.loadedEntityList;
   }

   public boolean isBlockLoaded(int n, int n2, int n3) {
      return this.isBlockLoaded(n, n2, n3, true);
   }

   public void makeFireworks(double d, double d2, double d3, double d4, double d5, double d6, NBTTagCompound tag) {
   }

   public IBlockState getBlockState(BlockPos pos) {
      if (!this.isValid(pos)) {
         return Blocks.air.getDefaultState();
      } else {
         return this.getChunkFromBlockCoords(pos).getBlockState(pos);
      }
   }

   public boolean canBlockSeeSky(BlockPos pos) {
      if (pos.getY() >= this.getSeaLevel()) {
         return this.canSeeSky(pos.getX(), pos.getY(), pos.getZ());
      } else {
         BlockPos pos2 = new BlockPos(pos.getX(), this.getSeaLevel(), pos.getZ());
         if (!this.canSeeSky(pos2.getX(), pos2.getY(), pos2.getZ())) {
            return false;
         } else {
            for (BlockPos i = pos2.down(); i.getY() > pos.getY(); i = i.down()) {
               Block block = this.getBlockState(i).getBlock();
               if (block.getLightOpacity() > 0 && !block.getMaterial().isLiquid()) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public boolean addWeatherEffect(Entity entity) {
      this.weatherEffects.add(entity);
      return true;
   }

   public float getLightBrightness(int n, int n2, int n3) {
      return this.provider.getLightBrightnessTable()[this.getLight(n, n2, n3)];
   }

   public boolean isFindingSpawnPoint() {
      return this.findingSpawnPoint;
   }

   public void tick() {
      this.updateWeather();
   }

   public List getEntities(Class clazz, Predicate predicate) {
      ArrayList list = Lists.newArrayList();

      for (Entity entity : (Iterable<Entity>)(this.loadedEntityList)) {
         if (clazz.isAssignableFrom(entity.getClass()) && predicate.apply(entity)) {
            list.add(entity);
         }
      }

      return list;
   }

   public void updateBlockTick(BlockPos pos, Block block, int n, int n2) {
   }

   public void sendQuittingDisconnectingPacket() {
      DisconnectListener.postDisconnect();
   }

   public boolean checkBlockCollision(AxisAlignedBB box) {
      int n = MathHelper.floor_double(box.minX);
      int n2 = MathHelper.floor_double(box.maxX);
      int n3 = MathHelper.floor_double(box.minY);
      int n4 = MathHelper.floor_double(box.maxY);
      int n5 = MathHelper.floor_double(box.minZ);
      int n6 = MathHelper.floor_double(box.maxZ);
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n; i <= n2; i++) {
         for (int j = n3; j <= n4; j++) {
            for (int k = n5; k <= n6; k++) {
               if (this.getBlockState(pos.set(i, j, k)).getBlock().getMaterial() != Material.air) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public void calculateInitialWeather() {
      if (this.worldInfo.isRaining()) {
         this.rainingStrength = 1.0F;
         if (this.worldInfo.isThundering()) {
            this.thunderingStrength = 1.0F;
         }
      }
   }

   public int getUniqueDataId(String str) {
      return this.mapStorage.getUniqueDataId(str);
   }

   public int countEntities(Class clazz) {
      int n = 0;

      for (Entity entity : (Iterable<Entity>)(this.loadedEntityList)) {
         if ((!(entity instanceof EntityLiving) || !((EntityLiving)entity).isNoDespawnRequired()) && clazz.isAssignableFrom(entity.getClass())) {
            n++;
         }
      }

      return n;
   }

   public int getLightFromNeighbors(int n, int n2, int n3) {
      if (n2 < 0) {
         return 0;
      } else {
         if (n2 >= 256) {
            n2 = 255;
         }

         return this.getChunkFromBlockCoords(n, n3).getLightSubtracted(n, n2, n3, 0);
      }
   }

   public MovingObjectPosition rayTraceBlocksFast(double d, double d2, double d3, double d4, double d5, double d6, boolean flag, boolean flag2, boolean flag3) {
      if (Double.isNaN(d) || Double.isNaN(d2) || Double.isNaN(d3)) {
         return null;
      } else if (!Double.isNaN(d4) && !Double.isNaN(d5) && !Double.isNaN(d6)) {
         int n = MathHelper.floor_double(d4);
         int n2 = MathHelper.floor_double(d5);
         int n3 = MathHelper.floor_double(d6);
         int n4 = MathHelper.floor_double(d);
         int n5 = MathHelper.floor_double(d2);
         int n6 = MathHelper.floor_double(d3);
         IBlockState blockState2 = this.getBlockState2(n4, n5, n6);
         Block block = blockState2.getBlock();
         if ((!flag2 || block.getCollisionBoundingBox(this, blockState2, n4, n5, n6) != null) && block.canCollideCheck(blockState2, flag)) {
            MovingObjectPosition hit = block.collisionRayTrace(this, n4, n5, n6, d, d2, d5, d6, d3, d4);
            if (hit != null) {
               return hit;
            }
         }

         MovingObjectPosition hit2 = null;
         int n7 = 200;

         while (n7-- >= 0) {
            if (Double.isNaN(d) || Double.isNaN(d2) || Double.isNaN(d3)) {
               return null;
            }

            if (n4 == n && n5 == n2 && n6 == n3) {
               return flag3 ? hit2 : null;
            }

            boolean flag4 = true;
            boolean flag5 = true;
            boolean flag6 = true;
            double d7 = 999.0;
            double d8 = 999.0;
            double d9 = 999.0;
            if (n > n4) {
               d7 = (double)n4 + 1.0;
            } else if (n < n4) {
               d7 = (double)n4 + 0.0;
            } else {
               flag4 = false;
            }

            if (n2 > n5) {
               d8 = (double)n5 + 1.0;
            } else if (n2 < n5) {
               d8 = (double)n5 + 0.0;
            } else {
               flag5 = false;
            }

            if (n3 > n6) {
               d9 = (double)n6 + 1.0;
            } else if (n3 < n6) {
               d9 = (double)n6 + 0.0;
            } else {
               flag6 = false;
            }

            double d10 = 999.0;
            double d11 = 999.0;
            double d12 = 999.0;
            double d13 = d4 - d;
            double d14 = d5 - d2;
            double d15 = d6 - d3;
            if (flag4) {
               d10 = (d7 - d) / d13;
            }

            if (flag5) {
               d11 = (d8 - d2) / d14;
            }

            if (flag6) {
               d12 = (d9 - d3) / d15;
            }

            if (d10 == -0.0) {
               d10 = -1.0E-4;
            }

            if (d11 == -0.0) {
               d11 = -1.0E-4;
            }

            if (d12 == -0.0) {
               d12 = -1.0E-4;
            }

            EnumFacing facing;
            if (d10 < d11 && d10 < d12) {
               facing = n > n4 ? EnumFacing.WEST : EnumFacing.EAST;
               d = d7;
               d2 += d14 * d10;
               d3 += d15 * d10;
            } else if (d11 < d12) {
               facing = n2 > n5 ? EnumFacing.DOWN : EnumFacing.UP;
               d += d13 * d11;
               d2 = d8;
               d3 += d15 * d11;
            } else {
               facing = n3 > n6 ? EnumFacing.NORTH : EnumFacing.SOUTH;
               d += d13 * d12;
               d2 += d14 * d12;
               d3 = d9;
            }

            n4 = MathHelper.floor_double(d) - (facing == EnumFacing.EAST ? 1 : 0);
            n5 = MathHelper.floor_double(d2) - (facing == EnumFacing.UP ? 1 : 0);
            n6 = MathHelper.floor_double(d3) - (facing == EnumFacing.SOUTH ? 1 : 0);
            IBlockState blockState22 = this.getBlockState2(n4, n5, n6);
            Block block2 = blockState22.getBlock();
            if (!flag2 || block2.getCollisionBoundingBox(this, blockState22, n4, n5, n6) != null) {
               if (block2.canCollideCheck(blockState22, flag)) {
                  MovingObjectPosition hit3 = block2.collisionRayTrace(this, n4, n5, n6, d, d2, d5, d6, d3, d4);
                  if (hit3 != null) {
                     return hit3;
                  }
               } else {
                  hit2 = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, new Vec3(d, d2, d3), facing, new BlockPos(n4, n5, n6));
               }
            }
         }

         return flag3 ? hit2 : null;
      } else {
         return null;
      }
   }

   public void notifyNeighborsOfStateExcept(BlockPos pos, Block block, EnumFacing facing) {
      if (facing != EnumFacing.WEST) {
         this.notifyBlockOfStateChange(pos.west(), block);
      }

      if (facing != EnumFacing.EAST) {
         this.notifyBlockOfStateChange(pos.east(), block);
      }

      if (facing != EnumFacing.DOWN) {
         this.notifyBlockOfStateChange(pos.down(), block);
      }

      if (facing != EnumFacing.UP) {
         this.notifyBlockOfStateChange(pos.up(), block);
      }

      if (facing != EnumFacing.NORTH) {
         this.notifyBlockOfStateChange(pos.north(), block);
      }

      if (facing != EnumFacing.SOUTH) {
         this.notifyBlockOfStateChange(pos.south(), block);
      }
   }

   public void calculateInitialSkylight() {
      int n = this.calculateSkylightSubtracted(1.0F);
      if (n != this.skylightSubtracted) {
         this.skylightSubtracted = n;
      }
   }

   public void updateEntities() {
      this.theProfiler.startSection("entities");
      this.theProfiler.startSection("global");

      for (int i = 0; i < this.weatherEffects.size(); i++) {
         Entity entity = (Entity)this.weatherEffects.get(i);

         try {
            entity.ticksExisted++;
            entity.onUpdate();
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Ticking entity");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("Entity being ticked");
            if (entity == null) {
               crashReportCategory.addCrashSection("Entity", "~~NULL~~");
            } else {
               entity.addEntityCrashInfo(crashReportCategory);
            }

            throw new ReportedException(crashReport);
         }

         if (entity.isDead) {
            this.weatherEffects.remove(i--);
         }
      }

      this.theProfiler.endStartSection("remove");
      this.loadedEntityList.removeAll(this.unloadedEntityList);

      for (int j = 0; j < this.unloadedEntityList.size(); j++) {
         Entity entity2 = (Entity)this.unloadedEntityList.get(j);
         int chunkCoordX = entity2.chunkCoordX;
         int chunkCoordZ = entity2.chunkCoordZ;
         if (entity2.addedToChunk && this.isChunkLoaded(chunkCoordX, chunkCoordZ, true)) {
            this.getChunkFromChunkCoords(chunkCoordX, chunkCoordZ).removeEntity(entity2);
         }
      }

      for (int k = 0; k < this.unloadedEntityList.size(); k++) {
         this.onEntityRemoved((Entity)this.unloadedEntityList.get(k));
      }

      this.unloadedEntityList.clear();
      this.theProfiler.endStartSection("regular");
      int n = -1;

      for (int m = 0; m < this.loadedEntityList.size(); m++) {
         Entity entity3 = (Entity)this.loadedEntityList.get(m);
         if (entity3 instanceof EntityPlayerSP) {
            n = m;
         } else {
            m = this.updateEntityAtIndex(entity3, m);
         }
      }

      if (n != -1) {
         this.updateEntityAtIndex((Entity)this.loadedEntityList.get(n), n);
      }

      this.theProfiler.endStartSection("blockEntities");
      this.processingLoadedTiles = true;
      Iterator it = this.tickableTileEntities.iterator();

      while (it.hasNext()) {
         TileEntity tileEntity = (TileEntity)it.next();
         if (!tileEntity.isInvalid() && tileEntity.hasWorldObj()) {
            BlockPos pos = tileEntity.getPos();
            if (this.isBlockLoaded(pos) && this.worldBorder.contains(pos)) {
               try {
                  ((ITickable)tileEntity).update();
               } catch (Throwable e2) {
                  CrashReport crashReport2 = CrashReport.makeCrashReport(e2, "Ticking block entity");
                  tileEntity.addInfoToCrashReport(crashReport2.makeCategory("Block entity being ticked"));
                  throw new ReportedException(crashReport2);
               }
            }
         }

         if (tileEntity.isInvalid()) {
            it.remove();
            this.loadedTileEntityList.remove(tileEntity);
            if (this.isBlockLoaded(tileEntity.getPos())) {
               this.getChunkFromBlockCoords(tileEntity.getPos()).removeTileEntity(tileEntity.getPos());
            }
         }
      }

      this.processingLoadedTiles = false;
      if (!this.tileEntitiesToBeRemoved.isEmpty()) {
         this.tickableTileEntities.removeAll(this.tileEntitiesToBeRemoved);
         this.loadedTileEntityList.removeAll(this.tileEntitiesToBeRemoved);
         this.tileEntitiesToBeRemoved.clear();
      }

      this.theProfiler.endStartSection("pendingBlockEntities");
      if (!this.addedTileEntityList.isEmpty()) {
         for (int idx = 0; idx < this.addedTileEntityList.size(); idx++) {
            TileEntity tileEntity2 = (TileEntity)this.addedTileEntityList.get(idx);
            if (!tileEntity2.isInvalid()) {
               if (!this.loadedTileEntityList.contains(tileEntity2)) {
                  this.addTileEntity(tileEntity2);
               }

               if (this.isBlockLoaded(tileEntity2.getPos())) {
                  this.getChunkFromBlockCoords(tileEntity2.getPos()).addTileEntity(tileEntity2.getPos(), tileEntity2);
               }

               this.markBlockForUpdate(tileEntity2.getPos());
            }
         }

         this.addedTileEntityList.clear();
      }

      this.theProfiler.endSection();
      this.theProfiler.endSection();
   }

   public void checkSessionLock() throws MinecraftException {
      this.saveHandler.checkSessionLock();
   }

   public int getHeight() {
      return 256;
   }

   public BlockPos getStrongholdPos(String str, BlockPos pos) {
      return this.getChunkProvider().getStrongholdGen(this, str, pos);
   }

   public int getActualHeight() {
      return this.provider.getHasNoSky() ? 128 : 256;
   }

   public boolean isAreaLoaded(int n, int n2, int n3, int n4) {
      return this.isAreaLoaded(n, n2, n3, n4, true);
   }

   public boolean isSpawnChunk(int n, int n2) {
      BlockPos spawnPoint = this.getSpawnPoint();
      int n3 = n * 16 + 8 - spawnPoint.getX();
      int n4 = n2 * 16 + 8 - spawnPoint.getZ();
      short s = 128;
      return n3 >= -s && n3 <= s && n4 >= -s && n4 <= s;
   }

   public float getSunBrightness(float f) {
      float celestialAngle = this.getCelestialAngle(f);
      float f2 = 1.0F - (MathHelper.cos(celestialAngle * 3.1415927F * 2.0F) * 2.0F + 0.2F);
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      f2 = 1.0F - f2;
      f2 = (float)((double)f2 * (1.0 - (double)(this.getRainStrength(f) * 5.0F) / 16.0));
      f2 = (float)((double)f2 * (1.0 - (double)(this.getThunderStrength(f) * 5.0F) / 16.0));
      return f2 * 0.8F + 0.2F;
   }

   public boolean isBlockModifiable(EntityPlayer player, BlockPos pos) {
      return true;
   }

   public boolean checkLightFor(EnumSkyBlock enumSkyBlock, int n, int n2, int n3) {
      if (!this.isAreaLoaded(n, n2, n3, 17, false)) {
         return false;
      } else {
         int n4 = 0;
         int n5 = 0;
         this.theProfiler.startSection("getBrightness");
         int lightFor = this.getLightFor(enumSkyBlock, n, n2, n3);
         int rawLight = this.getRawLight(n, n2, n3, enumSkyBlock);
         int n6 = n;
         int n7 = n2;
         int n8 = n3;
         if (rawLight > lightFor) {
            this.lightUpdateBlockList[n5++] = 133152;
         } else if (rawLight < lightFor) {
            this.lightUpdateBlockList[n5++] = 133152 | lightFor << 18;

            while (n4 < n5) {
               int n9 = this.lightUpdateBlockList[n4++];
               int n10 = (n9 & 63) - 32 + n6;
               int n11 = (n9 >> 6 & 63) - 32 + n7;
               int n12 = (n9 >> 12 & 63) - 32 + n8;
               int n13 = n9 >> 18 & 15;
               int lightFor2 = this.getLightFor(enumSkyBlock, n10, n11, n12);
               if (lightFor2 == n13) {
                  this.setLightFor(enumSkyBlock, n10, n11, n12, 0);
                  if (n13 > 0) {
                     if (MathHelper.abs_int(n10 - n6) + MathHelper.abs_int(n11 - n7) + MathHelper.abs_int(n12 - n8) < 17) {
                        for (EnumFacing facing : EnumFacing.getValues()) {
                           int n17 = n10 + facing.getFrontOffsetX();
                           int n18 = n11 + facing.getFrontOffsetY();
                           int n19 = n12 + facing.getFrontOffsetZ();
                           int n20 = Math.max(1, this.getBlockState2(n17, n18, n19).getBlock().getLightOpacity());
                           lightFor2 = this.getLightFor(enumSkyBlock, n17, n18, n19);
                           if (lightFor2 == n13 - n20 && n5 < this.lightUpdateBlockList.length) {
                              this.lightUpdateBlockList[n5++] = n17 - n6 + 32 | n18 - n7 + 32 << 6 | n19 - n8 + 32 << 12 | n13 - n20 << 18;
                           }
                        }
                     }
                  }
               }
            }

            n4 = 0;
         }

         this.theProfiler.endSection();
         this.theProfiler.startSection("checkedPosition < toCheckCount");

         while (n4 < n5) {
            int n21 = this.lightUpdateBlockList[n4++];
            int n22 = (n21 & 63) - 32 + n6;
            int n23 = (n21 >> 6 & 63) - 32 + n7;
            int n24 = (n21 >> 12 & 63) - 32 + n8;
            int lightFor3 = this.getLightFor(enumSkyBlock, n22, n23, n24);
            int rawLight2 = this.getRawLight(n22, n23, n24, enumSkyBlock);
            if (rawLight2 != lightFor3) {
               this.setLightFor(enumSkyBlock, n22, n23, n24, rawLight2);
               if (rawLight2 > lightFor3) {
                  int n25 = Math.abs(n22 - n6);
                  int n26 = Math.abs(n23 - n7);
                  int n27 = Math.abs(n24 - n8);
                  boolean flag = n5 < this.lightUpdateBlockList.length - 6;
                  if (n25 + n26 + n27 < 17 && flag) {
                     if (this.getLightFor(enumSkyBlock, n22 - 1, n23, n24) < rawLight2) {
                        this.lightUpdateBlockList[n5++] = n22 - 1 - n6 + 32 + (n23 - n7 + 32 << 6) + (n24 - n8 + 32 << 12);
                     }

                     if (this.getLightFor(enumSkyBlock, n22 + 1, n23, n24) < rawLight2) {
                        this.lightUpdateBlockList[n5++] = n22 + 1 - n6 + 32 + (n23 - n7 + 32 << 6) + (n24 - n8 + 32 << 12);
                     }

                     if (this.getLightFor(enumSkyBlock, n22, n23 - 1, n24) < rawLight2) {
                        this.lightUpdateBlockList[n5++] = n22 - n6 + 32 + (n23 - 1 - n7 + 32 << 6) + (n24 - n8 + 32 << 12);
                     }

                     if (this.getLightFor(enumSkyBlock, n22, n23 + 1, n24) < rawLight2) {
                        this.lightUpdateBlockList[n5++] = n22 - n6 + 32 + (n23 + 1 - n7 + 32 << 6) + (n24 - n8 + 32 << 12);
                     }

                     if (this.getLightFor(enumSkyBlock, n22, n23, n24 - 1) < rawLight2) {
                        this.lightUpdateBlockList[n5++] = n22 - n6 + 32 + (n23 - n7 + 32 << 6) + (n24 - 1 - n8 + 32 << 12);
                     }

                     if (this.getLightFor(enumSkyBlock, n22, n23, n24 + 1) < rawLight2) {
                        this.lightUpdateBlockList[n5++] = n22 - n6 + 32 + (n23 - n7 + 32 << 6) + (n24 + 1 - n8 + 32 << 12);
                     }
                  }
               }
            }
         }

         this.theProfiler.endSection();
         return true;
      }
   }

   public void markTileEntityForRemoval(TileEntity tileEntity) {
      this.tileEntitiesToBeRemoved.add(tileEntity);
   }

   public float getBlockDensity(double d, double d2, double d3, AxisAlignedBB box) {
      double d4 = 1.0 / ((box.maxX - box.minX) * 2.0 + 1.0);
      double d5 = 1.0 / ((box.maxY - box.minY) * 2.0 + 1.0);
      double d6 = 1.0 / ((box.maxZ - box.minZ) * 2.0 + 1.0);
      double d7 = (1.0 - Math.floor(1.0 / d4) * d4) / 2.0;
      double d8 = (1.0 - Math.floor(1.0 / d6) * d6) / 2.0;
      if (d4 >= 0.0 && d5 >= 0.0 && d6 >= 0.0) {
         int n = 0;
         int n2 = 0;

         for (float i = 0.0F; i <= 1.0F; i = (float)((double)i + d4)) {
            for (float j = 0.0F; j <= 1.0F; j = (float)((double)j + d5)) {
               for (float k = 0.0F; k <= 1.0F; k = (float)((double)k + d6)) {
                  double d9 = box.minX + (box.maxX - box.minX) * (double)i;
                  double d10 = box.minY + (box.maxY - box.minY) * (double)j;
                  double d11 = box.minZ + (box.maxZ - box.minZ) * (double)k;
                  if (this.rayTraceBlocks(d9 + d7, d10, d11 + d8, d, d2, d3) == null) {
                     n++;
                  }

                  n2++;
               }
            }
         }

         return (float)n / (float)n2;
      } else {
         return 0.0F;
      }
   }

   public long getSeed() {
      return this.worldInfo.getSeed();
   }

   public BiomeGenBase getBiomeGenForCoords(BlockPos pos) {
      if (this.isBlockLoaded(pos.getX(), pos.getY(), pos.getZ())) {
         Chunk chunkFromBlockCoords = this.getChunkFromBlockCoords(pos);

         try {
            return chunkFromBlockCoords.getBiome(pos.getX(), pos.getZ(), this.provider.getWorldChunkManager());
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Getting biome");
            crashReport.makeCategory("Coordinates of biome request").addCrashSectionCallable("Location", new World.ValueCallable(this, pos));
            throw new ReportedException(crashReport);
         }
      } else {
         return this.provider.getWorldChunkManager().getBiome(pos.getX(), pos.getZ(), BiomeGenBase.plains);
      }
   }

   public void onEntityAdded(Entity entity) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).onEntityAdded(entity);
      }
   }

   public void setActivePlayerChunksAndCheckLight() {
      this.activeChunkSet.clear();
      this.theProfiler.startSection("buildList");
      ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(0, 0);

      for (EntityPlayer player : (Iterable<EntityPlayer>)(this.playerEntities)) {
         int n = MathHelper.floor_double(player.posX / 16.0);
         int n2 = MathHelper.floor_double(player.posZ / 16.0);
         int renderDistanceChunks = this.getRenderDistanceChunks();

         for (int i = -renderDistanceChunks; i <= renderDistanceChunks; i++) {
            for (int j = -renderDistanceChunks; j <= renderDistanceChunks; j++) {
               chunkCoordIntPair.chunkXPos = i + n;
               chunkCoordIntPair.chunkZPos = j + n2;
               chunkCoordIntPair.cachedHashCode = 0;
               if (!this.activeChunkSet.contains(chunkCoordIntPair)) {
                  this.activeChunkSet.add(chunkCoordIntPair);
                  chunkCoordIntPair = new ChunkCoordIntPair(0, 0);
               }
            }
         }
      }

      this.theProfiler.endSection();
      if (this.ambientTickCountdown > 0) {
         this.ambientTickCountdown--;
      }

      this.theProfiler.startSection("playerCheckLight");
      if (!this.playerEntities.isEmpty()) {
         int n3 = this.rand.nextInt(this.playerEntities.size());
         EntityPlayer player2 = (EntityPlayer)this.playerEntities.get(n3);
         this.checkLight(MathHelper.floor_double(player2.posX) + this.rand.nextInt(11) - 5, MathHelper.floor_double(player2.posY) + this.rand.nextInt(11) - 5, MathHelper.floor_double(player2.posZ) + this.rand.nextInt(11) - 5);
      }

      this.theProfiler.endSection();
   }

   public boolean isChunkLoaded(int n, int n2, boolean flag) {
      return this.chunkProvider.chunkExists(n, n2) && (flag || !this.chunkProvider.provideChunk(n, n2).isEmpty());
   }

   public void setSeaLevel(int n) {
      this.seaLevel = n;
   }

   public void playSoundAtEntity(Entity entity, String str, float f, float f2) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).playSound(str, entity.posX, entity.posY, entity.posZ, f, f2);
      }
   }

   public WorldBorder getWorldBorder() {
      return this.worldBorder;
   }

   public boolean isAreaLoaded(int n, int n2, int n3, int n4, int n5, int n6, boolean flag) {
      if (n5 >= 0 && n2 < 256) {
         n >>= 4;
         n3 >>= 4;
         n4 >>= 4;
         n6 >>= 4;

         for (int i = n; i <= n4; i++) {
            for (int j = n3; j <= n6; j++) {
               if (!this.isChunkLoaded(i, j, flag)) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public int getLastLightningBolt() {
      return this.lastLightningBolt;
   }

   public boolean canBlockFreezeWater(BlockPos pos) {
      return this.canBlockFreeze(pos, false);
   }

   public boolean isDaytime() {
      return this.skylightSubtracted < 4;
   }

   public String getDebugLoadedEntities() {
      return "All: " + this.loadedEntityList.size();
   }

   public void spawnParticle(EnumParticleTypes enumParticleTypes, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
      this.spawnParticle(enumParticleTypes.getParticleID(), enumParticleTypes.getShouldIgnoreRange(), d, d2, d3, d4, d5, d6, ints);
   }

   public void playSoundEffect(double d, double d2, double d3, String str, float f, float f2) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).playSound(str, d, d2, d3, f, f2);
      }
   }

   public int calculateSkylightSubtracted(float f) {
      float celestialAngle = this.getCelestialAngle(f);
      float f2 = 1.0F - (MathHelper.cos(celestialAngle * 3.1415927F * 2.0F) * 2.0F + 0.5F);
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      f2 = 1.0F - f2;
      f2 = (float)((double)f2 * (1.0 - (double)(this.getRainStrength(f) * 5.0F) / 16.0));
      f2 = (float)((double)f2 * (1.0 - (double)(this.getThunderStrength(f) * 5.0F) / 16.0));
      f2 = 1.0F - f2;
      return (int)(f2 * 11.0F);
   }

   public int getSeaLevel() {
      return this.seaLevel;
   }

   public WorldType getWorldType() {
      return this.worldInfo.getTerrainType();
   }

   public void unloadEntities(Collection items) {
      this.unloadedEntityList.addAll(items);
   }

   public List getEntitiesWithinAABBExcludingEntity(Entity entity, AxisAlignedBB box) {
      return this.getEntitiesInAABBexcluding(entity, box, EntitySelectors.NOT_SPECTATING);
   }

   public void removeWorldAccess(IWorldAccess worldAccess) {
      this.worldAccesses.remove(worldAccess);
   }

   public void playSoundToNearExcept(EntityPlayer player, String str, float f, float f2) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).playSoundToNearExcept(player, str, player.posX, player.posY, player.posZ, f, f2);
      }
   }

   public void markBlockRangeForRenderUpdate(int n, int n2, int n3, int n4, int n5, int n6) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).markBlockRangeForRenderUpdate(n, n2, n3, n4, n5, n6);
      }
   }

   public Block getGroundAboveSeaLevel(BlockPos pos) {
      BlockPos pos2 = new BlockPos(pos.getX(), this.getSeaLevel(), pos.getZ());

      while (!this.isAirBlock(pos2.up())) {
         pos2 = pos2.up();
      }

      return this.getBlockState(pos2).getBlock();
   }

   private void spawnParticle(int n, boolean flag, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).spawnParticle(n, flag, d, d2, d3, d4, d5, d6, ints);
      }
   }

   public int getLight(int n, int n2, int n3, boolean flag) {
      if (n < -30000000 || n3 < -30000000 || n >= 30000000 || n3 >= 30000000) {
         return 15;
      } else if (flag && this.getBlockState2(n, n2, n3).getBlock().getUseNeighborBrightness()) {
         int light = this.getLight(n, n2 + 1, n3, false);
         int light2 = this.getLight(n + 1, n2, n3, false);
         int light3 = this.getLight(n - 1, n2, n3, false);
         int light4 = this.getLight(n, n2, n3 + 1, false);
         int light5 = this.getLight(n, n2, n3 - 1, false);
         if (light2 > light) {
            light = light2;
         }

         if (light3 > light) {
            light = light3;
         }

         if (light4 > light) {
            light = light4;
         }

         if (light5 > light) {
            light = light5;
         }

         return light;
      } else if (n2 < 0) {
         return 0;
      } else {
         if (n2 >= 256) {
            n2 = 255;
         }

         return this.getChunkFromBlockCoords(n, n3).getLightSubtracted(n, n2, n3, this.skylightSubtracted);
      }
   }

   public boolean isBlockLoaded(BlockPos pos, boolean flag) {
      return !this.isValid(pos) ? false : this.isChunkLoaded(pos.getX() >> 4, pos.getZ() >> 4, flag);
   }

   public int getLightFromNeighborsFor(EnumSkyBlock enumSkyBlock, int n, int n2, int n3) {
      if (this.provider.getHasNoSky() && enumSkyBlock == EnumSkyBlock.SKY) {
         return 0;
      } else {
         if (n2 < 0) {
            n2 = 0;
         }

         if (!this.isValid(n, n2, n3)) {
            return enumSkyBlock.defaultLightValue;
         } else if (!this.isBlockLoaded(n, n2, n3)) {
            return enumSkyBlock.defaultLightValue;
         } else if (this.getBlockState2(n, n2, n3).getBlock().getUseNeighborBrightness()) {
            int lightFor = this.getLightFor(enumSkyBlock, n, n2 + 1, n3);
            int lightFor2 = this.getLightFor(enumSkyBlock, n + 1, n2, n3);
            int lightFor3 = this.getLightFor(enumSkyBlock, n - 1, n2, n3);
            int lightFor4 = this.getLightFor(enumSkyBlock, n, n2, n3 + 1);
            int lightFor5 = this.getLightFor(enumSkyBlock, n, n2, n3 - 1);
            if (lightFor2 > lightFor) {
               lightFor = lightFor2;
            }

            if (lightFor3 > lightFor) {
               lightFor = lightFor3;
            }

            if (lightFor4 > lightFor) {
               lightFor = lightFor4;
            }

            if (lightFor5 > lightFor) {
               lightFor = lightFor5;
            }

            return lightFor;
         } else {
            return this.getChunkFromBlockCoords(n, n3).getLightFor(enumSkyBlock, n, n2, n3);
         }
      }
   }

   public Chunk getChunkFromBlockCoords(BlockPos pos) {
      return this.getChunkFromChunkCoords(pos.getX() >> 4, pos.getZ() >> 4);
   }

   public BlockPos getPrecipitationHeight(BlockPos pos) {
      return this.getChunkFromBlockCoords(pos).getPrecipitationHeight(pos);
   }

   public void markBlockRangeForRenderUpdate(BlockPos pos, BlockPos pos2) {
      this.markBlockRangeForRenderUpdate(pos.getX(), pos.getY(), pos.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
   }

   public boolean isAnyPlayerWithinRangeAt(double d, double d2, double d3, double d4) {
      for (int i = 0; i < this.playerEntities.size(); i++) {
         EntityPlayer player = (EntityPlayer)this.playerEntities.get(i);
         if (EntitySelectors.NOT_SPECTATING.apply(player)) {
            double distanceSq = player.getDistanceSq(d, d2, d3);
            if (d4 < 0.0 || distanceSq < d4 * d4) {
               return true;
            }
         }
      }

      return false;
   }

   public void setLightFor(EnumSkyBlock enumSkyBlock, int n, int n2, int n3, int n4) {
      if (this.isValid(n, n2, n3) && this.isBlockLoaded(n, n2, n3)) {
         this.getChunkFromBlockCoords(n, n3).setLightFor(enumSkyBlock, n, n2, n3, n4);
         this.notifyLightSet(n, n2, n3);
      }
   }

   public MovingObjectPosition rayTraceBlocks(double d, double d2, double d3, double d4, double d5, double d6, boolean flag, boolean flag2, boolean flag3) {
      if (Double.isNaN(d) || Double.isNaN(d2) || Double.isNaN(d3)) {
         return null;
      } else if (!Double.isNaN(d4) && !Double.isNaN(d5) && !Double.isNaN(d6)) {
         int n = MathHelper.floor_double(d4);
         int n2 = MathHelper.floor_double(d5);
         int n3 = MathHelper.floor_double(d6);
         int n4 = MathHelper.floor_double(d);
         int n5 = MathHelper.floor_double(d2);
         int n6 = MathHelper.floor_double(d3);
         IBlockState blockState2 = this.getBlockState2(n4, n5, n6);
         Block block = blockState2.getBlock();
         if ((!flag2 || block.getCollisionBoundingBox(this, blockState2, n4, n5, n6) != null) && block.canCollideCheck(blockState2, flag)) {
            MovingObjectPosition hit = block.collisionRayTrace(this, n4, n5, n6, d, d2, d5, d6, d3, d4);
            if (hit != null) {
               return hit;
            }
         }

         MovingObjectPosition hit2 = null;
         int n7 = 200;

         while (n7-- >= 0) {
            if (Double.isNaN(d) || Double.isNaN(d2) || Double.isNaN(d3)) {
               return null;
            }

            if (n4 == n && n5 == n2 && n6 == n3) {
               return flag3 ? hit2 : null;
            }

            boolean flag4 = true;
            boolean flag5 = true;
            boolean flag6 = true;
            double d7 = 999.0;
            double d8 = 999.0;
            double d9 = 999.0;
            if (n > n4) {
               d7 = (double)n4 + 1.0;
            } else if (n < n4) {
               d7 = (double)n4 + 0.0;
            } else {
               flag4 = false;
            }

            if (n2 > n5) {
               d8 = (double)n5 + 1.0;
            } else if (n2 < n5) {
               d8 = (double)n5 + 0.0;
            } else {
               flag5 = false;
            }

            if (n3 > n6) {
               d9 = (double)n6 + 1.0;
            } else if (n3 < n6) {
               d9 = (double)n6 + 0.0;
            } else {
               flag6 = false;
            }

            double d10 = 999.0;
            double d11 = 999.0;
            double d12 = 999.0;
            double d13 = d4 - d;
            double d14 = d5 - d2;
            double d15 = d6 - d3;
            if (flag4) {
               d10 = (d7 - d) / d13;
            }

            if (flag5) {
               d11 = (d8 - d2) / d14;
            }

            if (flag6) {
               d12 = (d9 - d3) / d15;
            }

            if (d10 == -0.0) {
               d10 = -1.0E-4;
            }

            if (d11 == -0.0) {
               d11 = -1.0E-4;
            }

            if (d12 == -0.0) {
               d12 = -1.0E-4;
            }

            EnumFacing facing;
            if (d10 < d11 && d10 < d12) {
               facing = n > n4 ? EnumFacing.WEST : EnumFacing.EAST;
               d = d7;
               d2 += d14 * d10;
               d3 += d15 * d10;
            } else if (d11 < d12) {
               facing = n2 > n5 ? EnumFacing.DOWN : EnumFacing.UP;
               d += d13 * d11;
               d2 = d8;
               d3 += d15 * d11;
            } else {
               facing = n3 > n6 ? EnumFacing.NORTH : EnumFacing.SOUTH;
               d += d13 * d12;
               d2 += d14 * d12;
               d3 = d9;
            }

            n4 = MathHelper.floor_double(d) - (facing == EnumFacing.EAST ? 1 : 0);
            n5 = MathHelper.floor_double(d2) - (facing == EnumFacing.UP ? 1 : 0);
            n6 = MathHelper.floor_double(d3) - (facing == EnumFacing.SOUTH ? 1 : 0);
            IBlockState blockState22 = this.getBlockState2(n4, n5, n6);
            Block block2 = blockState22.getBlock();
            if (!flag2 || block2.getCollisionBoundingBox(this, blockState22, n4, n5, n6) != null) {
               if (block2.canCollideCheck(blockState22, flag)) {
                  MovingObjectPosition hit3 = block2.collisionRayTrace(this, n4, n5, n6, d, d2, d5, d6, d3, d4);
                  if (hit3 != null) {
                     return hit3;
                  }
               } else {
                  hit2 = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, new Vec3(d, d2, d3), facing, new BlockPos(n4, n5, n6));
               }
            }
         }

         return flag3 ? hit2 : null;
      } else {
         return null;
      }
   }

   public void setTileEntity(BlockPos pos, TileEntity tileEntity) {
      if (tileEntity != null && !tileEntity.isInvalid()) {
         if (this.processingLoadedTiles) {
            tileEntity.setPos(pos);
            Iterator it = this.addedTileEntityList.iterator();

            while (it.hasNext()) {
               TileEntity tileEntity2 = (TileEntity)it.next();
               if (tileEntity2.getPos().equals(pos)) {
                  tileEntity2.invalidate();
                  it.remove();
               }
            }

            this.addedTileEntityList.add(tileEntity);
         } else {
            this.addTileEntity(tileEntity);
            this.getChunkFromBlockCoords(pos).addTileEntity(pos, tileEntity);
         }
      }
   }

   public int getLight(int n, int n2, int n3) {
      return this.getLight(n, n2, n3, true);
   }

   public List getEntitiesWithinAABB(Class clazz, AxisAlignedBB box, Predicate predicate) {
      int n = MathHelper.floor_double((box.minX - 2.0) / 16.0);
      int n2 = MathHelper.floor_double((box.maxX + 2.0) / 16.0);
      int n3 = MathHelper.floor_double((box.minZ - 2.0) / 16.0);
      int n4 = MathHelper.floor_double((box.maxZ + 2.0) / 16.0);
      ArrayList list = Lists.newArrayList();

      for (int i = n; i <= n2; i++) {
         for (int j = n3; j <= n4; j++) {
            if (this.isChunkLoaded(i, j, true)) {
               this.getChunkFromChunkCoords(i, j).getEntitiesOfTypeWithinAAAB(clazz, box, list, predicate);
            }
         }
      }

      return list;
   }

   public boolean handleMaterialAcceleration(AxisAlignedBB box, Material material, Entity entity) {
      int n = MathHelper.floor_double(box.minX);
      int n2 = MathHelper.floor_double(box.maxX + 1.0);
      int n3 = MathHelper.floor_double(box.minY);
      int n4 = MathHelper.floor_double(box.maxY + 1.0);
      int n5 = MathHelper.floor_double(box.minZ);
      int n6 = MathHelper.floor_double(box.maxZ + 1.0);
      if (!this.isAreaLoaded(n, n3, n5, n2, n4, n6, true)) {
         return false;
      } else {
         boolean flag = false;
         Vec3 vec = new Vec3(0.0, 0.0, 0.0);
         BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

         for (int i = n; i < n2; i++) {
            for (int j = n3; j < n4; j++) {
               for (int k = n5; k < n6; k++) {
                  pos.set(i, j, k);
                  IBlockState blockState = this.getBlockState(pos);
                  Block block = blockState.getBlock();
                  if (block.getMaterial() == material) {
                     if ((double)n4 >= ((double)((float)(j + 1) - BlockLiquid.getLiquidHeightPercent((Integer)blockState.getValue(BlockLiquid.LEVEL))))) {
                        flag = true;
                        vec = block.modifyAcceleration(this, pos, entity, vec);
                     }
                  }
               }
            }
         }

         if (vec.lengthVector() > 0.0 && entity.isPushedByWater()) {
            vec = vec.normalize();
            double d2 = 0.014;
            entity.motionX = entity.motionX + vec.xCoord * d2;
            entity.motionY = entity.motionY + vec.yCoord * d2;
            entity.motionZ = entity.motionZ + vec.zCoord * d2;
         }

         return flag;
      }
   }

   private boolean isWater(BlockPos pos) {
      return this.getBlockState(pos).getBlock().getMaterial() == Material.water;
   }

   public Entity getEntityByID(int n) {
      return (Entity)this.entitiesById.lookup(n);
   }

   public void setInitialSpawnLocation() {
      this.setSpawnPoint(new BlockPos(8, 64, 8));
   }

   public void updateWeather() {
      if (!this.provider.getHasNoSky() && !this.isRemote) {
         int cleanWeatherTime = this.worldInfo.getCleanWeatherTime();
         if (cleanWeatherTime > 0) {
            this.worldInfo.setCleanWeatherTime(--cleanWeatherTime);
            this.worldInfo.setThunderTime(this.worldInfo.isThundering() ? 1 : 2);
            this.worldInfo.setRainTime(this.worldInfo.isRaining() ? 1 : 2);
         }

         int thunderTime = this.worldInfo.getThunderTime();
         if (thunderTime <= 0) {
            if (this.worldInfo.isThundering()) {
               this.worldInfo.setThunderTime(this.rand.nextInt(12000) + 3600);
            } else {
               this.worldInfo.setThunderTime(this.rand.nextInt(168000) + 12000);
            }
         } else {
            this.worldInfo.setThunderTime(--thunderTime);
            if (thunderTime <= 0) {
               this.worldInfo.setThundering(!this.worldInfo.isThundering());
            }
         }

         this.prevThunderingStrength = this.thunderingStrength;
         if (this.worldInfo.isThundering()) {
            this.thunderingStrength = (float)((double)this.thunderingStrength + 0.01);
         } else {
            this.thunderingStrength = (float)((double)this.thunderingStrength - 0.01);
         }

         this.thunderingStrength = MathHelper.clamp_float(this.thunderingStrength, 0.0F, 1.0F);
         int rainTime = this.worldInfo.getRainTime();
         if (rainTime <= 0) {
            if (this.worldInfo.isRaining()) {
               this.worldInfo.setRainTime(this.rand.nextInt(12000) + 12000);
            } else {
               this.worldInfo.setRainTime(this.rand.nextInt(168000) + 12000);
            }
         } else {
            this.worldInfo.setRainTime(--rainTime);
            if (rainTime <= 0) {
               this.worldInfo.setRaining(!this.worldInfo.isRaining());
            }
         }

         this.prevRainingStrength = this.rainingStrength;
         if (this.worldInfo.isRaining()) {
            this.rainingStrength = (float)((double)this.rainingStrength + 0.01);
         } else {
            this.rainingStrength = (float)((double)this.rainingStrength - 0.01);
         }

         this.rainingStrength = MathHelper.clamp_float(this.rainingStrength, 0.0F, 1.0F);
      }
   }

   public boolean canSnowAt(BlockPos pos, boolean flag) {
      if (this.getBiomeGenForCoords(pos).getFloatTemperature(pos) > 0.15F) {
         return false;
      } else if (!flag) {
         return true;
      } else {
         if (pos.getY() >= 0 && pos.getY() < 256 && this.getLightFor(EnumSkyBlock.BLOCK, pos.getX(), pos.getY(), pos.getZ()) < 10) {
            if (this.getBlockState(pos).getBlock().getMaterial() == Material.air && Blocks.snow_layer.canPlaceBlockAt(this, pos)) {
               return true;
            }
         }

         return false;
      }
   }

   public void markBlocksDirtyVertical(int n, int n2, int n3, int n4) {
      if (n3 > n4) {
         int n5 = n4;
         n4 = n3;
         n3 = n5;
      }

      if (!this.provider.getHasNoSky()) {
         for (int i = n3; i <= n4; i++) {
            this.checkLightFor(EnumSkyBlock.SKY, n, i, n2);
         }
      }

      this.markBlockRangeForRenderUpdate(n, n3, n2, n, n4, n2);
   }

   public float getCelestialAngle(float f) {
      return this.provider.calculateCelestialAngle(this.worldInfo.getWorldTime(), f);
   }

   public EntityPlayer getPlayerEntityByUUID(UUID uuid) {
      for (int i = 0; i < this.playerEntities.size(); i++) {
         EntityPlayer player = (EntityPlayer)this.playerEntities.get(i);
         if (uuid.equals(player.getUniqueID())) {
            return player;
         }
      }

      return null;
   }

   public void markBlockForUpdate(BlockPos pos) {
      for (int i = 0; i < this.worldAccesses.size(); i++) {
         ((IWorldAccess)this.worldAccesses.get(i)).markBlockForUpdate(pos);
      }
   }

   public Vec3 getSkyColor(Entity entity, float f) {
      float f2 = MathHelper.cos(this.getCelestialAngle(f) * 3.1415927F * 2.0F) * 2.0F + 0.5F;
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      int n = MathHelper.floor_double(entity.posX);
      int n2 = MathHelper.floor_double(entity.posY);
      int n3 = MathHelper.floor_double(entity.posZ);
      BlockPos pos = new BlockPos(n, n2, n3);
      BiomeGenBase biomeGenForCoords = this.getBiomeGenForCoords(pos);
      int skyColorByTemp = biomeGenForCoords.getSkyColorByTemp(biomeGenForCoords.getFloatTemperature(pos));
      float f3 = (float)(skyColorByTemp >> 16 & 0xFF) / 255.0F;
      float f4 = (float)(skyColorByTemp >> 8 & 0xFF) / 255.0F;
      float f5 = (float)(skyColorByTemp & 0xFF) / 255.0F;
      f3 *= f2;
      f4 *= f2;
      f5 *= f2;
      float rainStrength = this.getRainStrength(f);
      if (rainStrength > 0.0F) {
         float f6 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.6F;
         float f7 = 1.0F - rainStrength * 0.75F;
         f3 = f3 * f7 + f6 * (1.0F - f7);
         f4 = f4 * f7 + f6 * (1.0F - f7);
         f5 = f5 * f7 + f6 * (1.0F - f7);
      }

      float thunderStrength = this.getThunderStrength(f);
      if (thunderStrength > 0.0F) {
         float f8 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.2F;
         float f9 = 1.0F - thunderStrength * 0.75F;
         f3 = f3 * f9 + f8 * (1.0F - f9);
         f4 = f4 * f9 + f8 * (1.0F - f9);
         f5 = f5 * f9 + f8 * (1.0F - f9);
      }

      if (this.lastLightningBolt > 0) {
         float f10 = (float)this.lastLightningBolt - f;
         if (f10 > 1.0F) {
            f10 = 1.0F;
         }

         f10 *= 0.45F;
         f3 = f3 * (1.0F - f10) + 0.8F * f10;
         f4 = f4 * (1.0F - f10) + 0.8F * f10;
         f5 = f5 * (1.0F - f10) + 1.0F * f10;
      }

      return new Vec3((double)f3, (double)f4, (double)f5);
   }

   public void setSpawnPoint(BlockPos pos) {
      this.worldInfo.setSpawn(pos);
   }

   public World(ISaveHandler saveHandler2, WorldInfo worldInfo2, WorldProvider worldProvider, Profiler profiler, boolean flag) {
      this.saveHandler = saveHandler2;
      this.theProfiler = profiler;
      this.worldInfo = worldInfo2;
      this.provider = worldProvider;
      this.isRemote = flag;
      this.worldBorder = worldProvider.getWorldBorder();
   }

   public boolean isBlockNormalCube(BlockPos pos, boolean flag) {
      if (!this.isValid(pos)) {
         return flag;
      } else {
         if (this.chunkProvider.provideChunk(pos).isEmpty()) {
            return flag;
         } else {
            Block block = this.getBlockState(pos).getBlock();
            return block.getMaterial().isOpaque() && block.isFullCube();
         }
      }
   }

   public void setAllowedSpawnTypes(boolean flag, boolean flag2) {
      this.spawnHostileMobs = flag;
      this.spawnPeacefulMobs = flag2;
   }

   public List getCollisionBoxes(AxisAlignedBB box) {
      ArrayList list = Lists.newArrayList();
      int n = MathHelper.floor_double(box.minX);
      int n2 = MathHelper.floor_double(box.maxX + 1.0);
      int n3 = MathHelper.floor_double(box.minY);
      int n4 = MathHelper.floor_double(box.maxY + 1.0);
      int n5 = MathHelper.floor_double(box.minZ);
      int n6 = MathHelper.floor_double(box.maxZ + 1.0);
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n; i < n2; i++) {
         for (int j = n5; j < n6; j++) {
            if (this.isBlockLoaded(pos.set(i, 64, j))) {
               for (int k = n3 - 1; k < n4; k++) {
                  pos.set(i, k, j);
                  IBlockState state;
                  if (i >= -30000000 && i < 30000000 && j >= -30000000 && j < 30000000) {
                     state = this.getBlockState(pos);
                  } else {
                     state = Blocks.bedrock.getDefaultState();
                  }

                  state.getBlock().addCollisionBoxesToList(this, pos, state, box, list, (Entity)null);
               }
            }
         }
      }

      return list;
   }

   public boolean checkNoEntityCollision(AxisAlignedBB box) {
      return this.checkNoEntityCollision(box, (Entity)null);
   }

   public void removeTileEntity(BlockPos pos) {
      TileEntity tileEntity = this.getTileEntity(pos);
      if (tileEntity != null && this.processingLoadedTiles) {
         tileEntity.invalidate();
         this.addedTileEntityList.remove(tileEntity);
      } else {
         if (tileEntity != null) {
            this.addedTileEntityList.remove(tileEntity);
            this.loadedTileEntityList.remove(tileEntity);
            this.tickableTileEntities.remove(tileEntity);
         }

         this.getChunkFromBlockCoords(pos).removeTileEntity(pos);
      }
   }

   public boolean isInsideBorder(WorldBorder worldBorder2, Entity entity) {
      double d = worldBorder2.minX();
      double d2 = worldBorder2.minZ();
      double d3 = worldBorder2.maxX();
      double d4 = worldBorder2.maxZ();
      if (entity.isOutsideBorder()) {
         d++;
         d2++;
         d3--;
         d4--;
      } else {
         d--;
         d2--;
         d3++;
         d4++;
      }

      return entity.posX > d && entity.posX < d3 && entity.posZ > d2 && entity.posZ < d4;
   }

   public float getThunderStrength(float f) {
      return Augustus.getInstance().getModuleManager().ambiance.isToggled()
         ? (float)Augustus.getInstance().getModuleManager().ambiance.thunder.getValue()
         : (this.prevThunderingStrength + (this.thunderingStrength - this.prevThunderingStrength) * f) * this.getRainStrength(f);
   }

   public int isBlockIndirectlyGettingPowered(BlockPos pos) {
      int n = 0;

      for (EnumFacing facing : EnumFacing.getValues()) {
         int redstonePower = this.getRedstonePower(pos.offset(facing), facing);
         if (redstonePower >= 15) {
            return 15;
         }

         if (redstonePower > n) {
            n = redstonePower;
         }
      }

      return n;
   }

   @Deprecated
   public int getCombinedLight(BlockPos pos, int n) {
      return this.getCombinedLight(pos.getX(), pos.getY(), pos.getZ(), n);
   }

   public Vec3 getCloudColour(float f) {
      float f2 = MathHelper.cos(this.getCelestialAngle(f) * 3.1415927F * 2.0F) * 2.0F + 0.5F;
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      float f3 = (float)(this.cloudColour >> 16 & 255L) / 255.0F;
      float f4 = (float)(this.cloudColour >> 8 & 255L) / 255.0F;
      float f5 = (float)(this.cloudColour & 255L) / 255.0F;
      float rainStrength = this.getRainStrength(f);
      if (rainStrength > 0.0F) {
         float f6 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.6F;
         float f7 = 1.0F - rainStrength * 0.95F;
         f3 = f3 * f7 + f6 * (1.0F - f7);
         f4 = f4 * f7 + f6 * (1.0F - f7);
         f5 = f5 * f7 + f6 * (1.0F - f7);
      }

      f3 *= f2 * 0.9F + 0.1F;
      f4 *= f2 * 0.9F + 0.1F;
      f5 *= f2 * 0.85F + 0.15F;
      float thunderStrength = this.getThunderStrength(f);
      if (thunderStrength > 0.0F) {
         float f8 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.2F;
         float f9 = 1.0F - thunderStrength * 0.95F;
         f3 = f3 * f9 + f8 * (1.0F - f9);
         f4 = f4 * f9 + f8 * (1.0F - f9);
         f5 = f5 * f9 + f8 * (1.0F - f9);
      }

      return new Vec3((double)f3, (double)f4, (double)f5);
   }

   public EntityPlayer getClosestPlayer(double d, double d2, double d3, double d4) {
      double d5 = -1.0;
      EntityPlayer player = null;

      for (int i = 0; i < this.playerEntities.size(); i++) {
         EntityPlayer player2 = (EntityPlayer)this.playerEntities.get(i);
         if (EntitySelectors.NOT_SPECTATING.apply(player2)) {
            double distanceSq = player2.getDistanceSq(d, d2, d3);
            if ((d4 < 0.0 || distanceSq < d4 * d4) && (d5 == -1.0 || distanceSq < d5)) {
               d5 = distanceSq;
               player = player2;
            }
         }
      }

      return player;
   }

   public IBlockState getBlockStateFromChunk(Chunk chunk, int n, int n2, int n3) {
      return chunk.getBlockState(n, n2, n3);
   }

   public void addWorldAccess(IWorldAccess worldAccess) {
      this.worldAccesses.add(worldAccess);
   }



   public static class ValueCallable implements Callable {
      public final BlockPos blockPos;
      public final World world;

      public ValueCallable(World world2, BlockPos pos) {
         this.world = world2;
         this.blockPos = pos;
      }

      public String callAsString() throws Exception {
         return CrashReportCategory.getCoordinateInfo(this.blockPos);
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static class ValueCallable2 implements Callable {
      public final Block block;
      public final World world;

      public ValueCallable2(World world2, Block block2) {
         this.world = world2;
         this.block = block2;
      }

      public String callAsString() {
         try {
            return String.format("ID #%d (%s // %s)", Block.getIdFromBlock(this.block), this.block.getUnlocalizedName(), this.block.getClass().getCanonicalName());
         } catch (Throwable e) {
            return "ID #" + Block.getIdFromBlock(this.block);
         }
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static class ValueCallable3 implements Callable {
      public final World world;

      public ValueCallable3(World world2) {
         this.world = world2;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public String callAsString() {
         return this.world.playerEntities.size() + " total; " + this.world.playerEntities;
      }
   }

   public static class ValueCallable4 implements Callable {
      public final World world;

      public ValueCallable4(World world2) {
         this.world = world2;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public String callAsString() {
         return this.world.chunkProvider.makeString();
      }
   }
}
