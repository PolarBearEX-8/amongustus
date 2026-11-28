package net.minecraft.world;

import com.google.common.base.Predicate;
import net.minecraft.world.biome.BiomeGenBase;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListenableFuture;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEventData;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.INpc;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.village.VillageCollection;
import net.minecraft.village.VillageSiege;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldServer extends World implements IThreadListener {
   private static final Logger LOGGER = LogManager.getLogger();
   private final MinecraftServer mcServer;
   private final EntityTracker theEntityTracker;
   private final PlayerManager thePlayerManager;
   private final Set pendingTickListEntriesHashSet = Sets.newHashSet();
   private final TreeSet pendingTickListEntriesTreeSet = new TreeSet();
   private final Map entitiesByUuid = Maps.newHashMap();
   public ChunkProviderServer theChunkProviderServer;
   public boolean disableLevelSaving;
   private boolean allPlayersSleeping;
   private int updateEntityTick;
   private final Teleporter worldTeleporter;
   private final SpawnerAnimals mobSpawner = new SpawnerAnimals();
   public final VillageSiege villageSiege = new VillageSiege(this);
   private WorldServer.ServerBlockEventList[] blockEventQueues = new WorldServer.ServerBlockEventList[]{
      new WorldServer.ServerBlockEventList(null), new WorldServer.ServerBlockEventList(null)
   };
   private int blockEventCacheIndex;
   private static final List bonusChestContent = Lists.newArrayList(
      new WeightedRandomChestContent[]{
         new WeightedRandomChestContent(Items.stick, 0, 1, 3, 10),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.planks), 0, 1, 3, 10),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log), 0, 1, 3, 10),
         new WeightedRandomChestContent(Items.stone_axe, 0, 1, 1, 3),
         new WeightedRandomChestContent(Items.wooden_axe, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.stone_pickaxe, 0, 1, 1, 3),
         new WeightedRandomChestContent(Items.wooden_pickaxe, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.apple, 0, 2, 3, 5),
         new WeightedRandomChestContent(Items.bread, 0, 2, 3, 3),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log2), 0, 1, 3, 10)
      }
   );
   private List pendingTickListEntriesThisTick = Lists.newArrayList();

   @Override
   public void updateAllPlayersSleepingFlag() {
      this.allPlayersSleeping = false;
      if (!this.playerEntities.isEmpty()) {
         int n = 0;
         int n2 = 0;

         for (EntityPlayer player : (Iterable<EntityPlayer>)(this.playerEntities)) {
            if (player.isSpectator()) {
               n++;
            } else if (player.isPlayerSleeping()) {
               n2++;
            }
         }

         this.allPlayersSleeping = n2 > 0 && n2 >= this.playerEntities.size() - n;
      }
   }

   private void resetRainAndThunder() {
      this.worldInfo.setRainTime(0);
      this.worldInfo.setRaining(false);
      this.worldInfo.setThunderTime(0);
      this.worldInfo.setThundering(false);
   }

   public WorldServer(MinecraftServer minecraftServer, ISaveHandler saveHandler2, WorldInfo worldInfo2, int n, Profiler profiler) {
      super(saveHandler2, worldInfo2, WorldProvider.getProviderForDimension(n), profiler, false);
      this.mcServer = minecraftServer;
      this.theEntityTracker = new EntityTracker(this);
      this.thePlayerManager = new PlayerManager(this);
      this.provider.registerWorld(this);
      this.chunkProvider = this.createChunkProvider();
      this.worldTeleporter = new Teleporter(this);
      this.calculateInitialSkylight();
      this.calculateInitialWeather();
      this.getWorldBorder().setSize(minecraftServer.getMaxWorldSize());
   }

   public void saveLevel() throws MinecraftException {
      this.checkSessionLock();
      this.worldInfo.setBorderSize(this.getWorldBorder().getDiameter());
      this.worldInfo.getBorderCenterX(this.getWorldBorder().getCenterX());
      this.worldInfo.getBorderCenterZ(this.getWorldBorder().getCenterZ());
      this.worldInfo.setBorderSafeZone(this.getWorldBorder().getDamageBuffer());
      this.worldInfo.setBorderDamagePerBlock(this.getWorldBorder().getDamageAmount());
      this.worldInfo.setBorderWarningDistance(this.getWorldBorder().getWarningDistance());
      this.worldInfo.setBorderWarningTime(this.getWorldBorder().getWarningTime());
      this.worldInfo.setBorderLerpTarget(this.getWorldBorder().getTargetSize());
      this.worldInfo.setBorderLerpTime(this.getWorldBorder().getTimeUntilTarget());
      this.saveHandler.saveWorldInfoWithPlayer(this.worldInfo, this.mcServer.getConfigurationManager().getHostPlayerData());
      this.mapStorage.saveAllData();
   }

   @Override
   public IChunkProvider createChunkProvider() {
      IChunkLoader chunkLoader = this.saveHandler.getChunkLoader(this.provider);
      this.theChunkProviderServer = new ChunkProviderServer(this, chunkLoader, this.provider.createChunkGenerator());
      return this.theChunkProviderServer;
   }

   @Override
   public boolean addWeatherEffect(Entity entity) {
      if (super.addWeatherEffect(entity)) {
         this.mcServer
            .getConfigurationManager()
            .sendToAllNear(entity.posX, entity.posY, entity.posZ, 512.0, this.provider.getDimensionId(), new S2CPacketSpawnGlobalEntity(entity));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public List getPendingBlockUpdates2(StructureBoundingBox structureBoundingBox, boolean flag) {
      ArrayList list = null;

      for (int i = 0; i < 2; i++) {
         Iterator it;
         if (i == 0) {
            it = this.pendingTickListEntriesTreeSet.iterator();
         } else {
            it = this.pendingTickListEntriesThisTick.iterator();
         }

         while (it.hasNext()) {
            NextTickListEntry nextTickListEntry = (NextTickListEntry)it.next();
            BlockPos position = nextTickListEntry.position;
            if (position.getX() >= structureBoundingBox.minX && position.getX() < structureBoundingBox.maxX && position.getZ() >= structureBoundingBox.minZ && position.getZ() < structureBoundingBox.maxZ) {
               if (flag) {
                  this.pendingTickListEntriesHashSet.remove(nextTickListEntry);
                  it.remove();
               }

               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(nextTickListEntry);
            }
         }
      }

      return list;
   }

   @Override
   public ListenableFuture addScheduledTaskDirect(Runnable runnable) {
      return this.mcServer.addScheduledTask(runnable);
   }

   @Override
   public Explosion newExplosion(Entity entity, double d, double d2, double d3, float f, boolean flag, boolean flag2) {
      Explosion explosion = new Explosion(this, entity, d, d2, d3, f, flag, flag2);
      explosion.doExplosionA();
      explosion.doExplosionB(false);
      if (!flag2) {
         explosion.clearAffectedBlockPositions();
      }

      for (EntityPlayer player : (Iterable<EntityPlayer>)(this.playerEntities)) {
         if (player.getDistanceSq(d, d2, d3) < 4096.0) {
            ((EntityPlayerMP)player)
               .playerNetServerHandler
               .sendPacket(new S27PacketExplosion(d, d2, d3, f, explosion.getAffectedBlockPositions(), (Vec3)explosion.getPlayerKnockbackMap().get(player)));
         }
      }

      return explosion;
   }

   public BlockPos getSpawnCoordinate() {
      return this.provider.getSpawnCoordinate();
   }

   public void resetUpdateEntityTick() {
      this.updateEntityTick = 0;
   }

   @Override
   public void setEntityState(Entity entity, byte b) {
      this.getEntityTracker().sendToTrackingAndSelf(entity, new S19PacketEntityStatus(entity, b));
   }

   public BiomeGenBase.SpawnListEntry getSpawnListEntryForTypeAt(EnumCreatureType enumCreatureType, BlockPos pos) {
      List possibleCreatures = this.getChunkProvider().getPossibleCreatures(enumCreatureType, pos);
      return possibleCreatures != null && !possibleCreatures.isEmpty() ? (BiomeGenBase.SpawnListEntry)WeightedRandom.getRandomItem(this.rand, possibleCreatures) : null;
   }

   @Override
   public void initialize(WorldSettings worldSettings) {
      if (!this.worldInfo.isInitialized()) {
         try {
            this.createSpawnPosition(worldSettings);
            if (this.worldInfo.getTerrainType() == WorldType.DEBUG_WORLD) {
               this.setDebugWorldSettings();
            }

            super.initialize(worldSettings);
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Exception initializing level");

            try {
               this.addWorldInfoToCrashReport(crashReport);
            } catch (Throwable e2) {
            }

            throw new ReportedException(crashReport);
         }

         this.worldInfo.setServerInitialized(true);
      }
   }

   public boolean canCreatureTypeSpawnHere(EnumCreatureType enumCreatureType, BiomeGenBase.SpawnListEntry spawnListEntry, BlockPos pos) {
      List possibleCreatures = this.getChunkProvider().getPossibleCreatures(enumCreatureType, pos);
      return possibleCreatures != null && !possibleCreatures.isEmpty() ? possibleCreatures.contains(spawnListEntry) : false;
   }

   private void setDebugWorldSettings() {
      this.worldInfo.setMapFeaturesEnabled(false);
      this.worldInfo.setAllowCommands(true);
      this.worldInfo.setRaining(false);
      this.worldInfo.setThundering(false);
      this.worldInfo.setCleanWeatherTime(1000000000);
      this.worldInfo.setWorldTime(6000L);
      this.worldInfo.setGameType(WorldSettings.GameType.SPECTATOR);
      this.worldInfo.setHardcore(false);
      this.worldInfo.setDifficulty(EnumDifficulty.PEACEFUL);
      this.worldInfo.setDifficultyLocked(true);
      this.getGameRules().setOrCreateGameRule("doDaylightCycle", "false");
   }

   @Override
   public void addBlockEvent(BlockPos pos, Block block, int n, int n2) {
      BlockEventData blockEventData = new BlockEventData(pos, block, n, n2);

      for (BlockEventData blockEventData2 : (Iterable<BlockEventData>)(this.blockEventQueues[this.blockEventCacheIndex])) {
         if (blockEventData2.equals(blockEventData)) {
            return;
         }
      }

      this.blockEventQueues[this.blockEventCacheIndex].add(blockEventData);
   }

   public BlockPos adjustPosToNearbyEntity(BlockPos pos) {
      BlockPos precipitationHeight = this.getPrecipitationHeight(pos);
      AxisAlignedBB box = new AxisAlignedBB(precipitationHeight, new BlockPos(precipitationHeight.getX(), this.getHeight(), precipitationHeight.getZ())).expand(3.0, 3.0, 3.0);
      List entitiesWithinAABB = this.getEntitiesWithinAABB(EntityLivingBase.class, box, new WorldServer.FilterPredicate(this));
      return !entitiesWithinAABB.isEmpty() ? ((EntityLivingBase)entitiesWithinAABB.get(this.rand.nextInt(entitiesWithinAABB.size()))).getPosition() : precipitationHeight;
   }

   @Override
   public boolean isCallingFromMinecraftThread() {
      return this.mcServer.isCallingFromMinecraftThread();
   }

   @Override
   public List getPendingBlockUpdates(Chunk chunk, boolean flag) {
      ChunkCoordIntPair chunkCoordIntPair = chunk.getChunkCoordIntPair();
      int n = (chunkCoordIntPair.chunkXPos << 4) - 2;
      int n2 = n + 16 + 2;
      int n3 = (chunkCoordIntPair.chunkZPos << 4) - 2;
      int n4 = n3 + 16 + 2;
      return this.getPendingBlockUpdates2(new StructureBoundingBox(n, 0, n3, n2, 256, n4), flag);
   }

   public void spawnParticle(
      EnumParticleTypes enumParticleTypes, double d, double d2, double d3, int n, double d4, double d5, double d6, double d7, int[] ints
   ) {
      this.spawnParticle(enumParticleTypes, false, d, d2, d3, n, d4, d5, d6, d7, ints);
   }

   public PlayerManager getPlayerManager() {
      return this.thePlayerManager;
   }

   public Teleporter getDefaultTeleporter() {
      return this.worldTeleporter;
   }

   private void createSpawnPosition(WorldSettings worldSettings) {
      if (!this.provider.canRespawnHere()) {
         this.worldInfo.setSpawn(BlockPos.ORIGIN.up(this.provider.getAverageGroundLevel()));
      } else if (this.worldInfo.getTerrainType() == WorldType.DEBUG_WORLD) {
         this.worldInfo.setSpawn(BlockPos.ORIGIN.up());
      } else {
         this.findingSpawnPoint = true;
         WorldChunkManager worldChunkManager = this.provider.getWorldChunkManager();
         List biomesToSpawnIn = worldChunkManager.getBiomesToSpawnIn();
         Random rand2 = new Random(this.getSeed());
         BlockPos pos = worldChunkManager.findBiomePosition(0, 0, 256, biomesToSpawnIn, rand2);
         int n = 0;
         int averageGroundLevel = this.provider.getAverageGroundLevel();
         int n2 = 0;
         if (pos != null) {
            n = pos.getX();
            n2 = pos.getZ();
         } else {
            LOGGER.warn("Unable to find spawn biome");
         }

         int n3 = 0;

         while (!this.provider.canCoordinateBeSpawn(n, n2)) {
            n += rand2.nextInt(64) - rand2.nextInt(64);
            n2 += rand2.nextInt(64) - rand2.nextInt(64);
            if (++n3 == 1000) {
               break;
            }
         }

         this.worldInfo.setSpawn(new BlockPos(n, averageGroundLevel, n2));
         this.findingSpawnPoint = false;
         if (worldSettings.isBonusChestEnabled()) {
            this.createBonusChest();
         }
      }
   }

   @Override
   public void updateBlocks() {
      super.updateBlocks();
      if (this.worldInfo.getTerrainType() == WorldType.DEBUG_WORLD) {
         ObjectIterator it = this.activeChunkSet.iterator();

         while (it.hasNext()) {
            ChunkCoordIntPair chunkCoordIntPair = (ChunkCoordIntPair)it.next();
            this.getChunkFromChunkCoords(chunkCoordIntPair.chunkXPos, chunkCoordIntPair.chunkZPos).onTick(false);
         }
      } else {
         for (ObjectIterator i = this.activeChunkSet.iterator(); i.hasNext(); this.theProfiler.endSection()) {
            ChunkCoordIntPair chunkCoordIntPair2 = (ChunkCoordIntPair)i.next();
            int n = chunkCoordIntPair2.chunkXPos * 16;
            int n2 = chunkCoordIntPair2.chunkZPos * 16;
            this.theProfiler.startSection("getChunk");
            Chunk chunkFromChunkCoords = this.getChunkFromChunkCoords(chunkCoordIntPair2.chunkXPos, chunkCoordIntPair2.chunkZPos);
            this.playMoodSoundAndCheckLight(n, n2, chunkFromChunkCoords);
            this.theProfiler.endStartSection("tickChunk");
            chunkFromChunkCoords.onTick(false);
            this.theProfiler.endStartSection("thunder");
            if (this.rand.nextInt(100000) == 0 && this.isRaining() && this.isThundering()) {
               this.updateLCG = this.updateLCG * 3 + 1013904223;
               int n3 = this.updateLCG >> 2;
               BlockPos pos = this.adjustPosToNearbyEntity(new BlockPos(n + (n3 & 15), 0, n2 + (n3 >> 8 & 15)));
               if (this.isRainingAt(pos)) {
                  this.addWeatherEffect(new EntityLightningBolt(this, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ()));
               }
            }

            this.theProfiler.endStartSection("iceandsnow");
            if (this.rand.nextInt(16) == 0) {
               this.updateLCG = this.updateLCG * 3 + 1013904223;
               int n4 = this.updateLCG >> 2;
               BlockPos precipitationHeight = this.getPrecipitationHeight(new BlockPos(n + (n4 & 15), 0, n2 + (n4 >> 8 & 15)));
               BlockPos pos2 = precipitationHeight.down();
               if (this.canBlockFreezeNoWater(pos2)) {
                  this.setBlockState(pos2, Blocks.ice.getDefaultState());
               }

               if (this.isRaining() && this.canSnowAt(precipitationHeight, true)) {
                  this.setBlockState(precipitationHeight, Blocks.snow_layer.getDefaultState());
               }

               if (this.isRaining() && this.getBiomeGenForCoords(pos2).canSpawnLightningBolt()) {
                  this.getBlockState(pos2).getBlock().fillWithRain(this, pos2);
               }
            }

            this.theProfiler.endStartSection("tickBlocks");
            int n5 = this.getGameRules().getInt("randomTickSpeed");
            if (n5 > 0) {
               for (ExtendedBlockStorage extendedBlockStorage : chunkFromChunkCoords.getBlockStorageArray()) {
                  if (extendedBlockStorage != null && extendedBlockStorage.getNeedsRandomTick()) {
                     for (int j = 0; j < n5; j++) {
                        this.updateLCG = this.updateLCG * 3 + 1013904223;
                        int n6 = this.updateLCG >> 2;
                        int n7 = n6 & 15;
                        int n8 = n6 >> 8 & 15;
                        int n9 = n6 >> 16 & 15;
                        IBlockState state = extendedBlockStorage.get(n7, n9, n8);
                        Block block = state.getBlock();
                        if (block.getTickRandomly()) {
                           block.randomTick(this, new BlockPos(n7 + n, n9 + extendedBlockStorage.getYLocation(), n8 + n2), state, this.rand);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void flush() {
      this.saveHandler.flush();
   }

   public boolean areAllPlayersAsleep() {
      if (this.allPlayersSleeping && !this.isRemote) {
         for (EntityPlayer player : (Iterable<EntityPlayer>)(this.playerEntities)) {
            if (player.isSpectator() || !player.isPlayerFullyAsleep()) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean fireBlockEvent(BlockEventData blockEventData) {
      IBlockState blockState = this.getBlockState(blockEventData.getPosition());
      return blockState.getBlock() == blockEventData.getBlock()
         ? blockState.getBlock().onBlockEventReceived(this, blockEventData.getPosition(), blockState, blockEventData.getEventID(), blockEventData.getEventParameter())
         : false;
   }

   @Override
   public boolean isBlockModifiable(EntityPlayer player, BlockPos pos) {
      return !this.mcServer.isBlockProtected(this, pos, player) && this.getWorldBorder().contains(pos);
   }

   @Override
   public void scheduleBlockUpdate(BlockPos pos, Block block, int n, int n2) {
      NextTickListEntry nextTickListEntry = new NextTickListEntry(pos, block);
      nextTickListEntry.setPriority(n2);
      if (block.getMaterial() != Material.air) {
         nextTickListEntry.setScheduledTime((long)n + this.worldInfo.getWorldTotalTime());
      }

      if (!this.pendingTickListEntriesHashSet.contains(nextTickListEntry)) {
         this.pendingTickListEntriesHashSet.add(nextTickListEntry);
         this.pendingTickListEntriesTreeSet.add(nextTickListEntry);
      }
   }

   public void saveAllChunks(boolean flag, IProgressUpdate progressUpdate) throws MinecraftException {
      if (this.chunkProvider.canSave()) {
         if (progressUpdate != null) {
            progressUpdate.displaySavingString("Saving level");
         }

         this.saveLevel();
         if (progressUpdate != null) {
            progressUpdate.displayLoadingString("Saving chunks");
         }

         this.chunkProvider.saveChunks(flag, progressUpdate);

         for (Chunk chunk : (Iterable<Chunk>)(Lists.newArrayList(this.theChunkProviderServer.getLoadedChunks()))) {
            if (chunk != null && !this.thePlayerManager.hasPlayerInstance(chunk.xPosition, chunk.zPosition)) {
               this.theChunkProviderServer.dropChunk(chunk.xPosition, chunk.zPosition);
            }
         }
      }
   }

   @Override
   public boolean isBlockTickPending(BlockPos pos, Block block) {
      NextTickListEntry nextTickListEntry = new NextTickListEntry(pos, block);
      return this.pendingTickListEntriesThisTick.contains(nextTickListEntry);
   }

   @Override
   public World init() {
      this.mapStorage = new MapStorage(this.saveHandler);
      String str = VillageCollection.fileNameForProvider(this.provider);
      VillageCollection villageCollection = (VillageCollection)this.mapStorage.loadData(VillageCollection.class, str);
      if (villageCollection == null) {
         this.villageCollectionObj = new VillageCollection(this);
         this.mapStorage.setData(str, this.villageCollectionObj);
      } else {
         this.villageCollectionObj = villageCollection;
         this.villageCollectionObj.setWorldsForAll(this);
      }

      this.worldScoreboard = new ServerScoreboard(this.mcServer);
      ScoreboardSaveData scoreboardSaveData = (ScoreboardSaveData)this.mapStorage.loadData(ScoreboardSaveData.class, "scoreboard");
      if (scoreboardSaveData == null) {
         scoreboardSaveData = new ScoreboardSaveData();
         this.mapStorage.setData("scoreboard", scoreboardSaveData);
      }

      scoreboardSaveData.setScoreboard(this.worldScoreboard);
      ((ServerScoreboard)this.worldScoreboard).setScoreboardSaveData(scoreboardSaveData);
      this.getWorldBorder().setCenter(this.worldInfo.getBorderCenterX(), this.worldInfo.getBorderCenterZ());
      this.getWorldBorder().setDamageAmount(this.worldInfo.getBorderDamagePerBlock());
      this.getWorldBorder().setDamageBuffer(this.worldInfo.getBorderSafeZone());
      this.getWorldBorder().setWarningDistance(this.worldInfo.getBorderWarningDistance());
      this.getWorldBorder().setWarningTime(this.worldInfo.getBorderWarningTime());
      if (this.worldInfo.getBorderLerpTime() > 0L) {
         this.getWorldBorder().setTransition(this.worldInfo.getBorderSize(), this.worldInfo.getBorderLerpTarget(), this.worldInfo.getBorderLerpTime());
      } else {
         this.getWorldBorder().setTransition(this.worldInfo.getBorderSize());
      }

      return this;
   }

   public Entity getEntityFromUuid(UUID uuid) {
      return (Entity)this.entitiesByUuid.get(uuid);
   }

   public List getTileEntitiesIn(int n, int n2, int n3, int n4, int n5, int n6) {
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < this.loadedTileEntityList.size(); i++) {
         TileEntity tileEntity = (TileEntity)this.loadedTileEntityList.get(i);
         BlockPos pos = tileEntity.getPos();
         if (pos.getX() >= n && pos.getY() >= n2 && pos.getZ() >= n3 && pos.getX() < n4 && pos.getY() < n5 && pos.getZ() < n6) {
            list.add(tileEntity);
         }
      }

      return list;
   }

   @Override
   public void updateBlockTick(BlockPos pos, Block block, int n, int n2) {
      NextTickListEntry nextTickListEntry = new NextTickListEntry(pos, block);
      byte b = 0;
      if (this.scheduledUpdatesAreImmediate && block.getMaterial() != Material.air) {
         if (block.requiresUpdates()) {
            b = 8;
            if (this.isAreaLoaded(nextTickListEntry.position.add(-b, -b, -b), nextTickListEntry.position.add(b, b, b))) {
               IBlockState blockState = this.getBlockState(nextTickListEntry.position);
               if (blockState.getBlock().getMaterial() != Material.air && blockState.getBlock() == nextTickListEntry.getBlock()) {
                  blockState.getBlock().updateTick(this, nextTickListEntry.position, blockState, this.rand);
               }
            }

            return;
         }

         n = 1;
      }

      if (this.isAreaLoaded(pos.add(-b, -b, -b), pos.add(b, b, b))) {
         if (block.getMaterial() != Material.air) {
            nextTickListEntry.setScheduledTime((long)n + this.worldInfo.getWorldTotalTime());
            nextTickListEntry.setPriority(n2);
         }

         if (!this.pendingTickListEntriesHashSet.contains(nextTickListEntry)) {
            this.pendingTickListEntriesHashSet.add(nextTickListEntry);
            this.pendingTickListEntriesTreeSet.add(nextTickListEntry);
         }
      }
   }

   public void wakeAllPlayers() {
      this.allPlayersSleeping = false;

      for (EntityPlayer player : (Iterable<EntityPlayer>)(this.playerEntities)) {
         if (player.isPlayerSleeping()) {
            player.wakeUpPlayer(false, false, true);
         }
      }

      this.resetRainAndThunder();
   }

   @Override
   public void setInitialSpawnLocation() {
      if (this.worldInfo.getSpawnY() <= 0) {
         this.worldInfo.setSpawnY(this.getSeaLevel() + 1);
      }

      int spawnX = this.worldInfo.getSpawnX();
      int spawnZ = this.worldInfo.getSpawnZ();
      int n = 0;

      while (this.getGroundAboveSeaLevel(new BlockPos(spawnX, 0, spawnZ)).getMaterial() == Material.air) {
         spawnX += this.rand.nextInt(8) - this.rand.nextInt(8);
         spawnZ += this.rand.nextInt(8) - this.rand.nextInt(8);
         if (++n == 10000) {
            break;
         }
      }

      this.worldInfo.setSpawnX(spawnX);
      this.worldInfo.setSpawnZ(spawnZ);
   }

   public void spawnParticle(
      EnumParticleTypes enumParticleTypes,
      boolean flag,
      double d,
      double d2,
      double d3,
      int n,
      double d4,
      double d5,
      double d6,
      double d7,
      int[] ints
   ) {
      S2APacketParticles packet = new S2APacketParticles(
         enumParticleTypes, flag, (float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, (float)d7, n, ints
      );

      for (int i = 0; i < this.playerEntities.size(); i++) {
         EntityPlayerMP entityPlayerMP = (EntityPlayerMP)this.playerEntities.get(i);
         double d8 = entityPlayerMP.getPosition().distanceSq(d, d2, d3);
         if (d8 <= 256.0 || flag && d8 <= 65536.0) {
            entityPlayerMP.playerNetServerHandler.sendPacket(packet);
         }
      }
   }

   @Override
   public void updateEntities() {
      if (this.playerEntities.isEmpty()) {
         if (this.updateEntityTick++ >= 1200) {
            return;
         }
      } else {
         this.resetUpdateEntityTick();
      }

      super.updateEntities();
   }

   @Override
   public void scheduleUpdate(BlockPos pos, Block block, int n) {
      this.updateBlockTick(pos, block, n, 0);
   }

   @Override
   public void updateEntityWithOptionalForce(Entity entity, boolean flag) {
      if (!this.canSpawnAnimals() && (entity instanceof EntityAnimal || entity instanceof EntityWaterMob)) {
         entity.setDead();
      }

      if (!this.canSpawnNPCs() && entity instanceof INpc) {
         entity.setDead();
      }

      super.updateEntityWithOptionalForce(entity, flag);
   }

   @Override
   public boolean tickUpdates(boolean flag) {
      if (this.worldInfo.getTerrainType() == WorldType.DEBUG_WORLD) {
         return false;
      } else {
         int size = this.pendingTickListEntriesTreeSet.size();
         if (size != this.pendingTickListEntriesHashSet.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
         } else {
            if (size > 1000) {
               size = 1000;
            }

            this.theProfiler.startSection("cleaning");

            for (int i = 0; i < size; i++) {
               NextTickListEntry nextTickListEntry = (NextTickListEntry)this.pendingTickListEntriesTreeSet.first();
               if (!flag && nextTickListEntry.scheduledTime > this.worldInfo.getWorldTotalTime()) {
                  break;
               }

               this.pendingTickListEntriesTreeSet.remove(nextTickListEntry);
               this.pendingTickListEntriesHashSet.remove(nextTickListEntry);
               this.pendingTickListEntriesThisTick.add(nextTickListEntry);
            }

            this.theProfiler.endSection();
            this.theProfiler.startSection("ticking");
            Iterator it = this.pendingTickListEntriesThisTick.iterator();

            while (it.hasNext()) {
               NextTickListEntry nextTickListEntry2 = (NextTickListEntry)it.next();
               it.remove();
               byte b = 0;
               if (this.isAreaLoaded(nextTickListEntry2.position.add(-b, -b, -b), nextTickListEntry2.position.add(b, b, b))) {
                  IBlockState blockState = this.getBlockState(nextTickListEntry2.position);
                  if (blockState.getBlock().getMaterial() != Material.air && Block.isEqualTo(blockState.getBlock(), nextTickListEntry2.getBlock())) {
                     try {
                        blockState.getBlock().updateTick(this, nextTickListEntry2.position, blockState, this.rand);
                     } catch (Throwable e) {
                        CrashReport crashReport = CrashReport.makeCrashReport(e, "Exception while ticking a block");
                        CrashReportCategory.addBlockInfo(crashReport.makeCategory("Block being ticked"), nextTickListEntry2.position, blockState);
                        throw new ReportedException(crashReport);
                     }
                  }
               } else {
                  this.scheduleUpdate(nextTickListEntry2.position, nextTickListEntry2.getBlock(), 0);
               }
            }

            this.theProfiler.endSection();
            this.pendingTickListEntriesThisTick.clear();
            return !this.pendingTickListEntriesTreeSet.isEmpty();
         }
      }
   }

   public void saveChunkData() {
      if (this.chunkProvider.canSave()) {
         this.chunkProvider.saveExtraData();
      }
   }

   @Override
   public void updateWeather() {
      boolean isRaining = this.isRaining();
      super.updateWeather();
      if (this.prevRainingStrength != this.rainingStrength) {
         this.mcServer.getConfigurationManager().sendPacketToAllPlayersInDimension(new S2BPacketChangeGameState(7, this.rainingStrength), this.provider.getDimensionId());
      }

      if (this.prevThunderingStrength != this.thunderingStrength) {
         this.mcServer.getConfigurationManager().sendPacketToAllPlayersInDimension(new S2BPacketChangeGameState(8, this.thunderingStrength), this.provider.getDimensionId());
      }

      if (isRaining != this.isRaining()) {
         if (isRaining) {
            this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(2, 0.0F));
         } else {
            this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(1, 0.0F));
         }

         this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(7, this.rainingStrength));
         this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(8, this.thunderingStrength));
      }
   }

   @Override
   public void onEntityAdded(Entity entity) {
      super.onEntityAdded(entity);
      this.entitiesById.addKey(entity.getEntityId(), entity);
      this.entitiesByUuid.put(entity.getUniqueID(), entity);
      Entity[] parts = entity.getParts();
      if (parts != null) {
         for (int i = 0; i < parts.length; i++) {
            this.entitiesById.addKey(parts[i].getEntityId(), parts[i]);
         }
      }
   }

   public EntityTracker getEntityTracker() {
      return this.theEntityTracker;
   }

   public void createBonusChest() {
      WorldGeneratorBonusChest worldGeneratorBonusChest = new WorldGeneratorBonusChest(bonusChestContent, 10);

      for (int i = 0; i < 10; i++) {
         int n = this.worldInfo.getSpawnX() + this.rand.nextInt(6) - this.rand.nextInt(6);
         int n2 = this.worldInfo.getSpawnZ() + this.rand.nextInt(6) - this.rand.nextInt(6);
         BlockPos pos = this.getTopSolidOrLiquidBlock(new BlockPos(n, 0, n2)).up();
         if (worldGeneratorBonusChest.generate(this, this.rand, pos)) {
            break;
         }
      }
   }

   @Override
   public int getRenderDistanceChunks() {
      return this.mcServer.getConfigurationManager().getViewDistance();
   }

   public MinecraftServer getMinecraftServer() {
      return this.mcServer;
   }

   @Override
   public void onEntityRemoved(Entity entity) {
      super.onEntityRemoved(entity);
      this.entitiesById.removeObject(entity.getEntityId());
      this.entitiesByUuid.remove(entity.getUniqueID());
      Entity[] parts = entity.getParts();
      if (parts != null) {
         for (int i = 0; i < parts.length; i++) {
            this.entitiesById.removeObject(parts[i].getEntityId());
         }
      }
   }

   private boolean canSpawnAnimals() {
      return this.mcServer.getCanSpawnAnimals();
   }

   @Override
   public ListenableFuture addScheduledTask(Runnable runnable) {
      return this.mcServer.addScheduledTask(runnable);
   }

   @Override
   public void tick() {
      super.tick();
      if (this.getWorldInfo().isHardcoreModeEnabled() && this.getDifficulty() != EnumDifficulty.HARD) {
         this.getWorldInfo().setDifficulty(EnumDifficulty.HARD);
      }

      this.provider.getWorldChunkManager().cleanupCache();
      if (this.areAllPlayersAsleep()) {
         if (this.getGameRules().getBoolean("doDaylightCycle")) {
            long l = this.worldInfo.getWorldTime() + 24000L;
            this.worldInfo.setWorldTime(l - l % 24000L);
         }

         this.wakeAllPlayers();
      }

      this.theProfiler.startSection("mobSpawner");
      if (this.getGameRules().getBoolean("doMobSpawning") && this.worldInfo.getTerrainType() != WorldType.DEBUG_WORLD) {
         this.mobSpawner.findChunksForSpawning(this, this.spawnHostileMobs, this.spawnPeacefulMobs, this.worldInfo.getWorldTotalTime() % 400L == 0L);
      }

      this.theProfiler.endStartSection("chunkSource");
      this.chunkProvider.unloadQueuedChunks();
      int n = this.calculateSkylightSubtracted(1.0F);
      if (n != this.getSkylightSubtracted()) {
         this.setSkylightSubtracted(n);
      }

      this.worldInfo.setWorldTotalTime(this.worldInfo.getWorldTotalTime() + 1L);
      if (this.getGameRules().getBoolean("doDaylightCycle")) {
         this.worldInfo.setWorldTime(this.worldInfo.getWorldTime() + 1L);
      }

      this.theProfiler.endStartSection("tickPending");
      this.tickUpdates(false);
      this.theProfiler.endStartSection("tickBlocks");
      this.updateBlocks();
      this.theProfiler.endStartSection("chunkMap");
      this.thePlayerManager.updatePlayerInstances();
      this.theProfiler.endStartSection("village");
      this.villageCollectionObj.tick();
      this.villageSiege.tick();
      this.theProfiler.endStartSection("portalForcer");
      this.worldTeleporter.removeStalePortalLocations(this.getTotalWorldTime());
      this.theProfiler.endSection();
      this.sendQueuedBlockEvents();
   }

   private boolean canSpawnNPCs() {
      return this.mcServer.getCanSpawnNPCs();
   }

   private void sendQueuedBlockEvents() {
      while (!this.blockEventQueues[this.blockEventCacheIndex].isEmpty()) {
         int blockEventCacheIndex2 = this.blockEventCacheIndex;
         this.blockEventCacheIndex ^= 1;

         for (BlockEventData blockEventData : (Iterable<BlockEventData>)(this.blockEventQueues[blockEventCacheIndex2])) {
            if (this.fireBlockEvent(blockEventData)) {
               this.mcServer
                  .getConfigurationManager()
                  .sendToAllNear(
                     (double)blockEventData.getPosition().getX(),
                     (double)blockEventData.getPosition().getY(),
                     (double)blockEventData.getPosition().getZ(),
                     64.0,
                     this.provider.getDimensionId(),
                     new S24PacketBlockAction(blockEventData.getPosition(), blockEventData.getBlock(), blockEventData.getEventID(), blockEventData.getEventParameter())
                  );
            }
         }

         this.blockEventQueues[blockEventCacheIndex2].clear();
      }
   }


   public static class FilterPredicate implements Predicate {
      public final WorldServer worldServer;

      public boolean apply(Object obj) {
         return this.apply((EntityLivingBase)obj);
      }

      public FilterPredicate(WorldServer world) {
         this.worldServer = world;
      }

      public boolean apply(EntityLivingBase entity) {
         return entity != null && entity.isEntityAlive() && this.worldServer.canSeeSky(entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ());
      }
   }

   public static class ServerBlockEventList extends ArrayList {
      public ServerBlockEventList(WorldServer.FilterPredicate obj) {
         this();
      }

      private ServerBlockEventList() {
      }
   }
}
