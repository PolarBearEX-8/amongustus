package net.minecraft.client.multiplayer;

import java.util.concurrent.Callable;
import net.minecraft.client.particle.EntityFirework;
import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.event.events.ChunkEvent;
import baritone.api.event.events.type.EventState;
import wdl.WDLHooks;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Random;
import java.util.Set;
import net.augustus.modules.Module;
import net.augustus.Augustus;
import net.augustus.events.listeners.SoundListener;
import net.augustus.utils.network.ServerEntityPositionTracker;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSoundMinecart;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.SaveDataMemoryStorage;
import net.minecraft.world.storage.SaveHandlerMP;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.CustomGuis;
import net.optifine.DynamicLights;
import net.optifine.config.Config;
import net.optifine.override.PlayerControllerOF;

public class WorldClient extends World {
   private NetHandlerPlayClient sendQueue;
   private ChunkProviderClient clientChunkProvider;
   private final Set entityList = Sets.newHashSet();
   private final Set entitySpawnQueue = Sets.newHashSet();
   private final Minecraft mc = Minecraft.getMinecraft();
   private final Set previousActiveChunkSet = Sets.newHashSet();
   private boolean playerUpdate = false;

   @Override
   public void sendQuittingDisconnectingPacket() {
      super.sendQuittingDisconnectingPacket();
      if (this.mc.getSession() != null) {
         this.mc.getSession().updatePlayTime(false);
      }

      this.sendQueue.getNetworkManager().closeChannel(new ChatComponentText("Quitting"));
   }

   @Override
   public void tick() {
      super.tick();
      this.setTotalWorldTime(this.getTotalWorldTime() + 1L);
      if (this.getGameRules().getBoolean("doDaylightCycle")) {
         this.setWorldTime(this.getWorldTime() + 1L);
      } else if (Augustus.getInstance().getModuleManager().ambiance.isToggled()) {
         super.setWorldTime(this.getWorldTime());
      }

      this.theProfiler.startSection("reEntryProcessing");

      for (int i = 0; i < 10 && !this.entitySpawnQueue.isEmpty(); i++) {
         Entity entity = (Entity)this.entitySpawnQueue.iterator().next();
         this.entitySpawnQueue.remove(entity);
         if (!this.loadedEntityList.contains(entity)) {
            this.spawnEntityInWorld(entity);
         }
      }

      this.theProfiler.endStartSection("chunkCache");
      this.clientChunkProvider.unloadQueuedChunks();
      this.theProfiler.endStartSection("blocks");
      this.updateBlocks();
      this.theProfiler.endSection();
      WDLHooks.onWorldClientTick(this);
   }

   @Override
   public boolean spawnEntityInWorld(Entity entity) {
      boolean flag = super.spawnEntityInWorld(entity);
      this.entityList.add(entity);
      if (!flag) {
         this.entitySpawnQueue.add(entity);
      } else if (entity instanceof EntityMinecart) {
         this.mc.getSoundHandler().playSound(new MovingSoundMinecart((EntityMinecart)entity));
      }

      return flag;
   }

   private boolean isPlayerActing() {
      if (this.mc.playerController instanceof PlayerControllerOF) {
         return ((PlayerControllerOF)this.mc.playerController).isActing();
      } else {
         return false;
      }
   }

   public static Set getEntityList(WorldClient world) {
      return world.entityList;
   }

   @Override
   public void removeEntity(Entity entity) {
      super.removeEntity(entity);
      this.entityList.remove(entity);
   }

   public static Minecraft accessMc(WorldClient world) {
      return world.mc;
   }

   @Override
   public Entity getEntityByID(int n) {
      return (Entity)(n == this.mc.thePlayer.getEntityId() ? this.mc.thePlayer : super.getEntityByID(n));
   }

   public void playSoundAtPos(BlockPos pos, String str, float f, float f2, boolean flag) {
      this.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, str, f, f2, flag);
   }

   @Override
   public void onEntityRemoved(Entity entity) {
      super.onEntityRemoved(entity);
      if (this.entityList.contains(entity)) {
         if (entity.isEntityAlive()) {
            this.entitySpawnQueue.add(entity);
         } else {
            this.entityList.remove(entity);
         }
      }
   }

   public boolean isPlayerUpdate() {
      return this.playerUpdate;
   }

   @Override
   public void updateWeather() {
   }

   @Override
   public void onEntityAdded(Entity entity) {
      super.onEntityAdded(entity);
      if (this.entitySpawnQueue.contains(entity)) {
         this.entitySpawnQueue.remove(entity);
      }
   }

   @Override
   public boolean setBlockState(BlockPos pos, IBlockState state, int n) {
      this.playerUpdate = this.isPlayerActing();
      boolean flag = super.setBlockState(pos, state, n);
      this.playerUpdate = false;
      return flag;
   }

   @Override
   public void playSound(double d, double d2, double d3, String str, float f, float f2, boolean flag) {
      SoundListener.onSound(d, d2, d3, str);
      double distanceSq = this.mc.getRenderViewEntity().getDistanceSq(d, d2, d3);
      PositionedSoundRecord positionedSoundRecord = new PositionedSoundRecord(new ResourceLocation(str), f, f2, (float)d, (float)d2, (float)d3);
      if (flag && distanceSq > 100.0) {
         double d4 = Math.sqrt(distanceSq) / 40.0;
         this.mc.getSoundHandler().playDelayedSound(positionedSoundRecord, (int)(d4 * 20.0));
      } else {
         this.mc.getSoundHandler().playSound(positionedSoundRecord);
      }
   }

   public void doPreChunk(int n, int n2, boolean flag) {
      if (((Module)Augustus.getInstance().getModuleManager().baritone).isToggled()) {
         for (IBaritone baritone : (Iterable<IBaritone>)(BaritoneAPI.getProvider().getAllBaritones())) {
            if (baritone.getPlayerContext().world() == this) {
               baritone.getGameEventHandler().onChunkEvent(new ChunkEvent(EventState.PRE, flag ? ChunkEvent.Type.LOAD : ChunkEvent.Type.UNLOAD, n, n2));
            }
         }
      }

      WDLHooks.onWorldDoPreChunk(this, n, n2, flag);
      if (flag) {
         this.clientChunkProvider.loadChunk(n, n2);
      } else {
         this.clientChunkProvider.unloadChunk(n, n2);
      }

      if (!flag) {
         this.markBlockRangeForRenderUpdate(n * 16, 0, n2 * 16, n * 16 + 15, 256, n2 * 16 + 15);
      }

      if (((Module)Augustus.getInstance().getModuleManager().baritone).isToggled()) {
         for (IBaritone baritone2 : (Iterable<IBaritone>)(BaritoneAPI.getProvider().getAllBaritones())) {
            if (baritone2.getPlayerContext().world() == this) {
               baritone2.getGameEventHandler().onChunkEvent(new ChunkEvent(EventState.POST, flag ? ChunkEvent.Type.LOAD : ChunkEvent.Type.UNLOAD, n, n2));
            }
         }
      }
   }

   @Override
   public CrashReportCategory addWorldInfoToCrashReport(CrashReport crashReport) {
      CrashReportCategory crashReportCategory = super.addWorldInfoToCrashReport(crashReport);
      crashReportCategory.addCrashSectionCallable("Forced entities", new WorldClient.ValueCallable(this));
      crashReportCategory.addCrashSectionCallable("Retry entities", new WorldClient.ValueCallable2(this));
      crashReportCategory.addCrashSectionCallable("Server brand", new WorldClient.ValueCallable3(this));
      crashReportCategory.addCrashSectionCallable("Server type", new WorldClient.ValueCallable4(this));
      return crashReportCategory;
   }

   public void doVoidFogParticles(int n, int n2, int n3) {
      byte b = 16;
      Random rand2 = new Random();
      ItemStack heldItem = this.mc.thePlayer.getHeldItem();
      boolean flag = this.mc.playerController.getCurrentGameType() == WorldSettings.GameType.CREATIVE
         && heldItem != null
         && Block.getBlockFromItem(heldItem.getItem()) == Blocks.barrier;
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = 0; i < 1000; i++) {
         int n4 = n + this.rand.nextInt(b) - this.rand.nextInt(b);
         int n5 = n2 + this.rand.nextInt(b) - this.rand.nextInt(b);
         int n6 = n3 + this.rand.nextInt(b) - this.rand.nextInt(b);
         pos.set(n4, n5, n6);
         IBlockState blockState = this.getBlockState(pos);
         blockState.getBlock().randomDisplayTick(this, pos, blockState, rand2);
         if (flag && blockState.getBlock() == Blocks.barrier) {
            this.spawnParticle(
               EnumParticleTypes.BARRIER,
               (double)((float)n4 + 0.5F),
               (double)((float)n5 + 0.5F),
               (double)((float)n6 + 0.5F),
               0.0,
               0.0,
               0.0,
               new int[0]
            );
         }
      }
   }

   @Override
   public int getRenderDistanceChunks() {
      return this.mc.gameSettings.renderDistanceChunks;
   }

   @Override
   public void updateBlocks() {
      super.updateBlocks();
      this.previousActiveChunkSet.retainAll(this.activeChunkSet);
      if (this.previousActiveChunkSet.size() == this.activeChunkSet.size()) {
         this.previousActiveChunkSet.clear();
      }

      int n = 0;
      ObjectIterator it = this.activeChunkSet.iterator();

      while (it.hasNext()) {
         ChunkCoordIntPair chunkCoordIntPair = (ChunkCoordIntPair)it.next();
         if (!this.previousActiveChunkSet.contains(chunkCoordIntPair)) {
            int n2 = chunkCoordIntPair.chunkXPos * 16;
            int n3 = chunkCoordIntPair.chunkZPos * 16;
            this.theProfiler.startSection("getChunk");
            this.playMoodSoundAndCheckLight(n2, n3, this.getChunkFromChunkCoords(chunkCoordIntPair.chunkXPos, chunkCoordIntPair.chunkZPos));
            this.theProfiler.endSection();
            this.previousActiveChunkSet.add(chunkCoordIntPair);
            if (++n >= 10) {
               return;
            }
         }
      }
   }

   public void addEntityToWorld(int n, Entity entity) {
      Entity entityByID = this.getEntityByID(n);
      if (entityByID != null) {
         this.removeEntity(entityByID);
      }

      this.entityList.add(entity);
      entity.setEntityId(n);
      if (!this.spawnEntityInWorld(entity)) {
         this.entitySpawnQueue.add(entity);
      }

      this.entitiesById.addKey(n, entity);
      ServerEntityPositionTracker.TRACKED_ENTITIES.put(n, entity);
   }

   public void removeAllEntities() {
      this.loadedEntityList.removeAll(this.unloadedEntityList);

      for (int i = 0; i < this.unloadedEntityList.size(); i++) {
         Entity entity = (Entity)this.unloadedEntityList.get(i);
         int chunkCoordX = entity.chunkCoordX;
         int chunkCoordZ = entity.chunkCoordZ;
         if (entity.addedToChunk && this.isChunkLoaded(chunkCoordX, chunkCoordZ, true)) {
            this.getChunkFromChunkCoords(chunkCoordX, chunkCoordZ).removeEntity(entity);
         }
      }

      for (int j = 0; j < this.unloadedEntityList.size(); j++) {
         this.onEntityRemoved((Entity)this.unloadedEntityList.get(j));
      }

      this.unloadedEntityList.clear();

      for (int k = 0; k < this.loadedEntityList.size(); k++) {
         Entity entity2 = (Entity)this.loadedEntityList.get(k);
         if (entity2.ridingEntity != null) {
            if (!entity2.ridingEntity.isDead && entity2.ridingEntity.riddenByEntity == entity2) {
               continue;
            }

            entity2.ridingEntity.riddenByEntity = null;
            entity2.ridingEntity = null;
         }

         if (entity2.isDead) {
            int chunkCoordX2 = entity2.chunkCoordX;
            int chunkCoordZ2 = entity2.chunkCoordZ;
            if (entity2.addedToChunk && this.isChunkLoaded(chunkCoordX2, chunkCoordZ2, true)) {
               this.getChunkFromChunkCoords(chunkCoordX2, chunkCoordZ2).removeEntity(entity2);
            }

            this.loadedEntityList.remove(k--);
            this.onEntityRemoved(entity2);
         }
      }
   }

   @Override
   public IChunkProvider createChunkProvider() {
      this.clientChunkProvider = new ChunkProviderClient(this);
      return this.clientChunkProvider;
   }

   public WorldClient(NetHandlerPlayClient netHandlerPlayClient, WorldSettings worldSettings, int n, EnumDifficulty enumDifficulty, Profiler profiler) {
      super(new SaveHandlerMP(), new WorldInfo(worldSettings, "MpServer"), WorldProvider.getProviderForDimension(n), profiler, true);
      ServerEntityPositionTracker.TRACKED_ENTITIES.clear();
      this.sendQueue = netHandlerPlayClient;
      this.getWorldInfo().setDifficulty(enumDifficulty);
      this.provider.registerWorld(this);
      this.setSpawnPoint(new BlockPos(8, 64, 8));
      this.chunkProvider = this.createChunkProvider();
      this.mapStorage = new SaveDataMemoryStorage();
      this.calculateInitialSkylight();
      this.calculateInitialWeather();
      if (this.mc.playerController != null && this.mc.playerController.getClass() == PlayerControllerMP.class) {
         this.mc.playerController = new PlayerControllerOF(this.mc, netHandlerPlayClient);
         CustomGuis.setPlayerControllerOF((PlayerControllerOF)this.mc.playerController);
      }
   }

   public Entity removeEntityFromWorld(int n) {
      WDLHooks.onWorldRemoveEntityFromWorld(this, n);
      Entity entity = (Entity)this.entitiesById.removeObject(n);
      ServerEntityPositionTracker.TRACKED_ENTITIES.remove(n);
      if (entity != null) {
         this.entityList.remove(entity);
         this.removeEntity(entity);
      }

      return entity;
   }

   public void invalidateBlockReceiveRegion(int n, int n2, int n3, int n4, int n5, int n6) {
   }

   @Override
   public void setWorldTime(long l) {
      if (l < 0L) {
         l = -l;
         this.getGameRules().setOrCreateGameRule("doDaylightCycle", "false");
      } else {
         this.getGameRules().setOrCreateGameRule("doDaylightCycle", "true");
      }

      super.setWorldTime(l);
   }

   @Override
   public int getCombinedLight(int n, int n2, int n3, int n4) {
      int combinedLight = super.getCombinedLight(n, n2, n3, n4);
      if (Config.isDynamicLights()) {
         combinedLight = DynamicLights.getCombinedLight(n, n2, n3, combinedLight);
      }

      return combinedLight;
   }

   public static Set getEntitySpawnQueue(WorldClient world) {
      return world.entitySpawnQueue;
   }

   @Override
   public void makeFireworks(double d, double d2, double d3, double d4, double d5, double d6, NBTTagCompound tag) {
      this.mc.effectRenderer.addEffect(new EntityFirework.StarterFX(this, d, d2, d3, d4, d5, d6, this.mc.effectRenderer, tag));
   }

   public boolean invalidateRegionAndSetBlock(BlockPos pos, IBlockState state) {
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();
      this.invalidateBlockReceiveRegion(x, y, z, x, y, z);
      return super.setBlockState(pos, state, 3);
   }

   public void setWorldScoreboard(Scoreboard scoreboard) {
      this.worldScoreboard = scoreboard;
   }



   public static class ValueCallable implements Callable {
      public final WorldClient worldClient;

      @Override
      public Object call() throws Exception {
         return this.getEntityCountReport();
      }

      public ValueCallable(WorldClient world) {
         this.worldClient = world;
      }

      public String getEntityCountReport() {
         return WorldClient.getEntityList(this.worldClient).size() + " total; " + WorldClient.getEntityList(this.worldClient);
      }
   }

   public static class ValueCallable2 implements Callable {
      public final WorldClient worldClient;

      @Override
      public Object call() throws Exception {
         return this.getEntitySpawnQueueReport();
      }

      public String getEntitySpawnQueueReport() {
         return WorldClient.getEntitySpawnQueue(this.worldClient).size() + " total; " + WorldClient.getEntitySpawnQueue(this.worldClient);
      }

      public ValueCallable2(WorldClient world) {
         this.worldClient = world;
      }
   }

   public static class ValueCallable3 implements Callable {
      public final WorldClient worldClient;

      public String getClientBrandReport() throws Exception {
         return WorldClient.accessMc(this.worldClient).thePlayer.getClientBrand();
      }

      public ValueCallable3(WorldClient world) {
         this.worldClient = world;
      }

      @Override
      public Object call() throws Exception {
         return this.getClientBrandReport();
      }
   }

   public static class ValueCallable4 implements Callable {
      public final WorldClient worldClient;

      public String getServerTypeReport() throws Exception {
         return WorldClient.accessMc(this.worldClient).getIntegratedServer() == null ? "Non-integrated multiplayer server" : "Integrated singleplayer server";
      }

      @Override
      public Object call() throws Exception {
         return this.getServerTypeReport();
      }

      public ValueCallable4(WorldClient world) {
         this.worldClient = world;
      }
   }
}
