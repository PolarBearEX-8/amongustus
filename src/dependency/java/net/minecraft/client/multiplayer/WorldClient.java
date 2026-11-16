package net.minecraft.client.multiplayer;

import cc.baritone.api.SecondaryItemEntry;
import cc.baritone.utils.AStarResolver;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BreadthFirstService;
import cc.baritone.utils.SecondaryOptionType;
import cc.baritone.utils.SettingsTxtManager;
import client.Client;
import client.event.MultiplayerChunkCacheListener;
import client.event.SafeWalkListener;
import client.gui.OptifineGuiContainerManager;
import client.module.Module;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.GameSettingsPeacefulType;
import client.setting.WorldSettingsGameType;
import client.util.AdvancedLongEntry;
import client.util.AimChecker;
import client.util.AlertNotificationSound;
import client.util.AltCustom;
import client.util.AngleScheduler;
import client.util.BezierPredictor;
import client.util.BuildManager;
import client.util.CurveWriter;
import client.util.DampenerCounter;
import client.util.IBlockState;
import client.util.InternalConcurrentHashMapEntry;
import client.util.InternalNoneManager;
import client.util.InterpolationCollector;
import client.util.PlayerControllerMPHandler;
import client.util.RandomSeedManager;
import client.util.SecondaryFloatArrayEntry;
import client.util.SessionTokenManager;
import client.util.TargetEstimator;
import client.util.ThePlayerNameManager;
import client.util.UNKNOWNManager;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTadpoleAdapter;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldClient extends World {
   public NetHandlerPlayClient netHandlerPlayClient;
   public MultiplayerChunkCacheListener multiplayerChunkCacheListener;
   public final Set set2 = Sets.newHashSet();
   public final Set set3 = Sets.newHashSet();
   public final Minecraft mc = Minecraft.getMinecraft();
   public final Set set = Sets.newHashSet();
   public boolean state2 = false;

   @Override
   public void spawnEntityInWorld(Entity entity) {
      super.spawnEntityInWorld(entity);
      this.set2.remove(entity);
   }

   public void setThePlayerNameManager2(ThePlayerNameManager thePlayerNameManager) {
      this.thePlayerNameManager = thePlayerNameManager;
   }

   public static Set resolveSet2(WorldClient world) {
      return world.set2;
   }

   @Override
   public CrashReportCategory resolveCrashReportCategory(CrashReport crashReport) {
      CrashReportCategory crashReportCategory = super.resolveCrashReportCategory(crashReport);
      crashReportCategory.applyText("Forced entities", new DampenerCounter(this));
      crashReportCategory.applyText("Retry entities", new AltCustom(this));
      crashReportCategory.applyText("Server brand", new BezierPredictor(this));
      crashReportCategory.applyText("Server type", new CurveWriter(this));
      return crashReportCategory;
   }

   @Override
   public int getCountAsIndex() {
      return this.mc.gameSettings.indexOffset16;
   }

   public void applyIndexByIndex(int count, int count2, int count3) {
      Random random = new Random();
      ItemStack stack = this.mc.thePlayer.getItemStack();
      boolean flag = this.mc.playerController.getWorldSettingsGameType() == WorldSettingsGameType.CREATIVE
         && stack != null
         && Block.resolveBlock(stack.getItem()) == Blocks.block145;
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index = 0; index < 1000; index++) {
         int count4 = count + this.random.nextInt(16) - this.random.nextInt(16);
         int count5 = count2 + this.random.nextInt(16) - this.random.nextInt(16);
         int count6 = count3 + this.random.nextInt(16) - this.random.nextInt(16);
         auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, count5, count6);
         IBlockState blockState = this.getBlockState(auxiliaryBlockPosAdapter);
         blockState.getBlock().processAction3(this, auxiliaryBlockPosAdapter, blockState, random);
         if (flag && blockState.getBlock() == Blocks.block145) {
            this.applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.BARRIER,
               (double)((float)count4 + 0.5F),
               (double)((float)count5 + 0.5F),
               (double)((float)count6 + 0.5F),
               0.0,
               0.0,
               0.0,
               new int[0]
            );
         }
      }
   }

   @Override
   public boolean matchesEntity(Entity entity) {
      boolean flag = super.matchesEntity(entity);
      this.set2.add(entity);
      if (!flag) {
         this.set3.add(entity);
      } else if (entity instanceof EntityMinecart) {
         this.mc.getMcSoundHandler().applyISound2(new AngleScheduler((EntityMinecart)entity));
      }

      return flag;
   }

   @Override
   public void applyEntity(Entity entity) {
      super.applyEntity(entity);
      if (this.set3.contains(entity)) {
         this.set3.remove(entity);
      }
   }

   public void applyIndex(int count, int count2, boolean flag) {
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         for (ActiveListener activeListener : (Iterable<ActiveListener>)(Iterable<?>)SettingsTxtManager.getExtraListener().getList()) {
            if (activeListener.getStaticListener().getWorld() == this) {
               activeListener.getCompositeListener()
                  .applyAStarResolver(new AStarResolver(SecondaryOptionType.PRE, flag ? BreadthFirstService.LOAD : BreadthFirstService.UNLOAD, count, count2));
            }
         }
      }

      SecondaryItemEntry.applyWorldClientByWorldClient((short)0, this, count, count2, (short)28471, 1988279513, flag);
      if (flag) {
         this.multiplayerChunkCacheListener.resolveAlertNotificationSoundByIndex(count, count2);
      } else {
         this.multiplayerChunkCacheListener.applyIndex(count, count2);
      }

      if (!flag) {
         this.applyIndex(count * 16, 0, count2 * 16, count * 16 + 15, 256, count2 * 16 + 15);
      }

      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         for (ActiveListener activeListener2 : (Iterable<ActiveListener>)(Iterable<?>)SettingsTxtManager.getExtraListener().getList()) {
            if (activeListener2.getStaticListener().getWorld() == this) {
               activeListener2.getCompositeListener()
                  .applyAStarResolver(new AStarResolver(SecondaryOptionType.POST, flag ? BreadthFirstService.LOAD : BreadthFirstService.UNLOAD, count, count2));
            }
         }
      }
   }

   @Override
   public void updateState3() {
      super.updateState3();
      this.set.retainAll(this.objectOpenHashSet);
      if (this.set.size() == this.objectOpenHashSet.size()) {
         this.set.clear();
      }

      int count = 0;
      ObjectIterator iterator = this.objectOpenHashSet.iterator();

      while (iterator.hasNext()) {
         InterpolationCollector interpolationCollector = (InterpolationCollector)iterator.next();
         if (!this.set.contains(interpolationCollector)) {
            int count2 = interpolationCollector.count3 * 16;
            int count3 = interpolationCollector.count2 * 16;
            this.uNKNOWNManager.applyTextByText("getChunk");
            AlertNotificationSound loggerEntry = this.getLoggerEntry2(interpolationCollector.count3, interpolationCollector.count2);
            this.applyAlertNotificationSound(count2, count3, loggerEntry);
            this.uNKNOWNManager.updateState();
            this.set.add(interpolationCollector);
            if (++count >= 10) {
               return;
            }
         }
      }
   }

   public void applyIndexByIndex(int count, int count2, int count3, int count4, int count5, int count6) {
   }

   @Override
   public int computeCount(int count, int count2, int count3, int count4) {
      int count5 = super.computeCount(count, count2, count3, count4);
      if (BuildManager.isActive28()) {
         count5 = TargetEstimator.computeCount(count, count2, count3, count5);
      }

      return count5;
   }

   public boolean matchesBlockPosByBlockPos(BlockPos blockPos, IBlockState blockState) {
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();
      this.applyIndexByIndex(coordX, coordY, coordZ, coordX, coordY, coordZ);
      return super.matchesBlockPos(blockPos, blockState, 3);
   }

   @Override
   public void updateState2() {
      super.updateState2();
      this.applyTimeByTime(this.getTimestamp2() + 1L);
      if (this.getDoFireTickManager().matchesText("doDaylightCycle")) {
         this.applyTime(this.getTimestampAsTime() + 1L);
      }

      this.uNKNOWNManager.applyTextByText("reEntryProcessing");

      for (int index = 0; index < 10 && !this.set3.isEmpty(); index++) {
         Entity entity = (Entity)this.set3.iterator().next();
         this.set3.remove(entity);
         if (!this.loadedEntityList.contains(entity)) {
            this.matchesEntity(entity);
         }
      }

      this.uNKNOWNManager.applyText("chunkCache");
      this.multiplayerChunkCacheListener.isActive();
      this.uNKNOWNManager.applyText("blocks");
      this.updateState3();
      this.uNKNOWNManager.updateState();
      SecondaryItemEntry.applyWorldClient(this);
   }

   public void applyEntity10(int count, Entity entity) {
      Entity entityByID = this.getEntityByID(count);
      if (entityByID != null) {
         this.spawnEntityInWorld(entityByID);
      }

      this.set2.add(entity);
      entity.setEntityId(count);
      if (!this.matchesEntity(entity)) {
         this.set3.add(entity);
      }

      this.altItemArrayEntry.applyTarget(count, entity);
      InternalConcurrentHashMapEntry.concurrentHashMap.put(count, entity);
   }

   public boolean isState2() {
      return this.state2;
   }

   @Override
   public void applyNBTTagCompound(double posX, double posX2, double posX3, double posX4, double posY, double posZ, NBTTagCompound tagCompound) {
      this.mc.effectRenderer.applyTertiaryEntityAdapter2(new EntityTadpoleAdapter(this, posX, posX2, posX3, posX4, posY, posZ, this.mc.effectRenderer, tagCompound));
   }

   @Override
   public IChunkProvider getIChunkProvider() {
      this.multiplayerChunkCacheListener = new MultiplayerChunkCacheListener(this);
      return this.multiplayerChunkCacheListener;
   }

   @Override
   public boolean matchesBlockPos(BlockPos blockPos, IBlockState blockState, int count) {
      this.state2 = this.isActive3();
      boolean flag = super.matchesBlockPos(blockPos, blockState, count);
      this.state2 = false;
      return flag;
   }

   public static Minecraft resolveMinecraft(WorldClient world) {
      return world.mc;
   }

   @Override
   public void applyTime(long value) {
      long value2 = 6000L;
      this.getDoFireTickManager().applyText("doDaylightCycle", "true");
      super.applyTime(value2);
   }

   public void applyBlockPos(BlockPos blockPos, String text, float factor, float factor2, boolean flag) {
      this.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, text, factor, factor2, flag);
   }

   @Override
   public void updateState() {
      if (this.mc.getSession() != null) {
         this.mc.getSession().setEnabled(false);
      }

      SessionTokenManager.updateState();
      if (Client.getClient().getInternalHashMapHolder() != null) {
         Client.getClient().getInternalHashMapHolder().updateState();
      }

      this.netHandlerPlayClient.getNetworkManager().applyIChatComponent(new ChatComponentText("Quitting"));
   }

   @Override
   public Entity getEntityByID(int count) {
      return (Entity)(count == this.mc.thePlayer.getEntityId() ? this.mc.thePlayer : super.getEntityByID(count));
   }

   @Override
   public void applyText(double posX, double posY, double posZ, String text, float factor, float factor2, boolean flag) {
      SafeWalkListener.applyText(posX, posY, posZ, text);
      double doubleItem = this.mc.getRenderViewEntity().getDouble3(posX, posY, posZ);
      PositionedSoundRecord positionedSoundRecord = new PositionedSoundRecord(new ResourceLocation(text), factor, factor2, (float)posX, (float)posY, (float)posZ);
      if (flag && doubleItem > 100.0) {
         double offset = Math.sqrt(doubleItem) / 40.0;
         this.mc.getMcSoundHandler().applyISound(positionedSoundRecord, (int)(offset * 20.0));
      } else {
         this.mc.getMcSoundHandler().applyISound2(positionedSoundRecord);
      }
   }

   @Override
   public void applyEntityByEntity(Entity entity) {
      super.applyEntityByEntity(entity);
      if (this.set2.contains(entity)) {
         if (entity.canExecute()) {
            this.set3.add(entity);
         } else {
            this.set2.remove(entity);
         }
      }
   }

   private boolean isActive3() {
      return this.mc.playerController instanceof PlayerControllerMPHandler ? ((PlayerControllerMPHandler)this.mc.playerController).isEnabled() : false;
   }

   @Override
   public void updateState9() {
   }

   public Entity resolveEntity2(int count) {
      SecondaryItemEntry.applyWorldClient(this, count);
      Entity entity = (Entity)this.altItemArrayEntry.resolveTarget(count);
      InternalConcurrentHashMapEntry.concurrentHashMap.remove(count);
      if (entity != null) {
         this.set2.remove(entity);
         this.spawnEntityInWorld(entity);
      }

      return entity;
   }

   public static Set resolveSet(WorldClient world) {
      return world.set3;
   }

   public void updateState12() {
      this.loadedEntityList.removeAll(this.list3);

      for (int index = 0; index < this.list3.size(); index++) {
         Entity entity = (Entity)this.list3.get(index);
         int count = entity.count7;
         int count2 = entity.count8;
         if (entity.enabled9 && this.checkIndex(count, count2, true)) {
            this.getLoggerEntry2(count, count2).applyEntity3(entity);
         }
      }

      for (int index2 = 0; index2 < this.list3.size(); index2++) {
         this.applyEntityByEntity((Entity)this.list3.get(index2));
      }

      this.list3.clear();

      for (int index3 = 0; index3 < this.loadedEntityList.size(); index3++) {
         Entity entity2 = (Entity)this.loadedEntityList.get(index3);
         if (entity2.ridingEntity != null) {
            if (!entity2.ridingEntity.isDead && entity2.ridingEntity.entity == entity2) {
               continue;
            }

            entity2.ridingEntity.entity = null;
            entity2.ridingEntity = null;
         }

         if (entity2.isDead) {
            int count3 = entity2.count7;
            int count4 = entity2.count8;
            if (entity2.enabled9 && this.checkIndex(count3, count4, true)) {
               this.getLoggerEntry2(count3, count4).applyEntity3(entity2);
            }

            this.loadedEntityList.remove(index3--);
            this.applyEntityByEntity(entity2);
         }
      }
   }

   public WorldClient(NetHandlerPlayClient netHandlerPlayClient2, AdvancedLongEntry advancedLongEntry, int count, GameSettingsPeacefulType gameSettingsPeacefulType, UNKNOWNManager uNKNOWNManager) {
      super(new InternalNoneManager(), new RandomSeedManager(advancedLongEntry, "MpServer"), SecondaryFloatArrayEntry.resolveSecondaryFloatArrayEntry(count), uNKNOWNManager, true);
      InternalConcurrentHashMapEntry.concurrentHashMap.clear();
      this.netHandlerPlayClient = netHandlerPlayClient2;
      this.getRandomSeedManager().setGameSettingsPeacefulType2(gameSettingsPeacefulType);
      this.secondaryFloatArrayEntry.applyWorldByWorld(this);
      this.applyBlockPos(new BlockPos(8, 64, 8));
      this.commonListener = this.getIChunkProvider();
      this.dataManager = new AimChecker();
      this.updateState8();
      this.updateState7();
      if (this.mc.playerController != null && this.mc.playerController.getClass() == PlayerControllerMP.class) {
         Minecraft mc2 = this.mc;
         PlayerControllerMPHandler playerControllerMPHandler = new PlayerControllerMPHandler(this.mc, netHandlerPlayClient2);
         mc2.playerController = playerControllerMPHandler;
         OptifineGuiContainerManager.setPlayerControllerMPHandler((PlayerControllerMPHandler)this.mc.playerController);
      }
   }
}
