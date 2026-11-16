package net.minecraft.world;

import client.event.CompositeListener;
import client.event.EnhancedListener;
import client.event.GenericListener;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.GameSettingsPeacefulType;
import client.setting.MovementThresholdProperty;
import client.setting.WorldSettingsGameType;
import client.util.AdvancedLongEntry;
import client.util.AimPipeline;
import client.util.AlertNotificationSound;
import client.util.ArrayListAdapter;
import client.util.AuxiliaryStringEntryHandler;
import client.util.ClassHolder;
import client.util.ClientManagerAdapter2;
import client.util.DataManager;
import client.util.DistanceAggregator;
import client.util.ExceptionAdapter;
import client.util.ExtendedComparableAdapter;
import client.util.GeometryFormatter;
import client.util.HUIHRVManager;
import client.util.HitBoxBuffer;
import client.util.IBlockState;
import client.util.InfoManager;
import client.util.InternalCharArrayEntry;
import client.util.InternalSetEntry;
import client.util.InterpolationCollector;
import client.util.InterpolationPool;
import client.util.InvalidBiomeIdManager;
import client.util.ListenerAdapter21;
import client.util.ManagedLoggerEntry;
import client.util.RandomSeedManager;
import client.util.RotationStack;
import client.util.RotationWatcher;
import client.util.SecondaryFloatArrayEntry;
import client.util.SecondaryLoggerEntry;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SmoothSystem;
import client.util.TertiaryWorldEntry;
import client.util.ThePlayerNameManagerAdapter;
import client.util.UNKNOWNManager;
import client.util.VanillaRunnable;
import client.util.WorldServerEntry;
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
import net.minecraft.block.BlockBreakState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldServer extends World implements EnhancedListener {
   public static final Logger logger = LogManager.getLogger();
   public final VanillaRunnable vanillaRunnable;
   public final SecondaryLoggerEntry secondaryLoggerEntry;
   public final ManagedLoggerEntry managedLoggerEntry;
   public final Set set = Sets.newHashSet();
   public final TreeSet treeSet = new TreeSet();
   public final Map map = Maps.newHashMap();
   public ListenerAdapter21 listenerAdapter21;
   public boolean enabled8;
   public boolean enabled6;
   public int count4;
   public final WorldServerEntry worldServerEntry;
   public final InternalSetEntry internalSetEntry = new InternalSetEntry();
   public final TertiaryWorldEntry tertiaryWorldEntry = new TertiaryWorldEntry(this);
   public ArrayListAdapter[] arrayListAdapterArray = new ArrayListAdapter[]{new ArrayListAdapter(null), new ArrayListAdapter(null)};
   public int count5;
   public static final List list7 = Lists.newArrayList(
      new ClientManagerAdapter2[]{
         new ClientManagerAdapter2(Items.stick, 0, 1, 3, 10),
         new ClientManagerAdapter2(Item.processAction2(Blocks.block142), 0, 1, 3, 10),
         new ClientManagerAdapter2(Item.processAction2(Blocks.block140), 0, 1, 3, 10),
         new ClientManagerAdapter2(Items.stoneAxe, 0, 1, 1, 3),
         new ClientManagerAdapter2(Items.woodenAxe, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.stonePickaxe, 0, 1, 1, 3),
         new ClientManagerAdapter2(Items.woodenPickaxe, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.apple, 0, 2, 3, 5),
         new ClientManagerAdapter2(Items.bread, 0, 2, 3, 3),
         new ClientManagerAdapter2(Item.processAction2(Blocks.block104), 0, 1, 3, 10)
      }
   );
   public List list10 = Lists.newArrayList();

   @Override
   public void applyBlockPos2(BlockPos blockPos, Block block, int count, int count2) {
      ExtendedComparableAdapter extendedComparableAdapter = new ExtendedComparableAdapter(blockPos, block);
      if (this.enabled3 && block.getMaterial() != Material.coreItemEntry) {
         if (block.processAction5()) {
            if (this.matchesBlockPos(extendedComparableAdapter.blockPos.add(-8, -8, -8), extendedComparableAdapter.blockPos.add(8, 8, 8))) {
               IBlockState blockState = this.getBlockState(extendedComparableAdapter.blockPos);
               if (blockState.getBlock().getMaterial() != Material.coreItemEntry && blockState.getBlock() == extendedComparableAdapter.getBlock()) {
                  blockState.getBlock().updateTick(this, extendedComparableAdapter.blockPos, blockState, this.random);
               }
            }

            return;
         }

         count = 1;
      }

      if (this.matchesBlockPos(blockPos.add(0, 0, 0), blockPos.add(0, 0, 0))) {
         if (block.getMaterial() != Material.coreItemEntry) {
            extendedComparableAdapter.withTimestamp((long)count + this.randomSeedManager.getTimestamp2());
            extendedComparableAdapter.setCount(count2);
         }

         if (!this.set.contains(extendedComparableAdapter)) {
            this.set.add(extendedComparableAdapter);
            this.treeSet.add(extendedComparableAdapter);
         }
      }
   }

   public SecondaryLoggerEntry getSecondaryLoggerEntry() {
      return this.secondaryLoggerEntry;
   }

   @Override
   public void applyAdvancedLongEntry(AdvancedLongEntry advancedLongEntry) {
      if (!this.randomSeedManager.isEnabled5()) {
         try {
            this.applyAdvancedLongEntryByAdvancedLongEntr(advancedLongEntry);
            if (this.randomSeedManager.getInfoManager() == InfoManager.infoManager4) {
               this.updateState19();
            }

            super.applyAdvancedLongEntry(advancedLongEntry);
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Exception initializing level");

            try {
               this.resolveCrashReportCategory(crashReport);
            } catch (Throwable throwable2) {
            }

            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }

         this.randomSeedManager.setEnabled5(true);
      }
   }

   @Override
   public void applyEntity(Entity entity, boolean flag) {
      if (!this.isActive3() && (entity instanceof HitBoxBuffer || entity instanceof AimPipeline)) {
         entity.updateState9();
      }

      if (!this.isActive5() && entity instanceof InterpolationPool) {
         entity.updateState9();
      }

      super.applyEntity(entity, flag);
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player, BlockPos blockPos) {
      return !this.vanillaRunnable.matchesWorld(this, blockPos, player) && this.getInternalListEntry().checkBlockPos(blockPos);
   }

   @Override
   public void updateState4() {
      if (this.itemList7.isEmpty()) {
         if (this.count4++ >= 1200) {
            return;
         }
      } else {
         this.updateState20();
      }

      super.updateState4();
   }

   public boolean isActive4() {
      if (this.enabled6 && !this.state) {
         for (EntityPlayer player : (Iterable<EntityPlayer>)(Iterable<?>)this.itemList7) {
            if (player.isActive23() || !player.isActive25()) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public int getCountAsIndex() {
      return this.vanillaRunnable.getMovingManager().getCount3();
   }

   public void applyAttackEffectsExplosionNorm(
      AttackEffectsExplosionNormalType attackEffectsExplosionNormalType,
      boolean flag,
      double posX,
      double posY,
      double posZ,
      int count,
      double posX2,
      double posX3,
      double posY2,
      double posZ2,
      int[] countArray
   ) {
      S2APacketParticles packet = new S2APacketParticles(
         attackEffectsExplosionNormalType, flag, (float)posX, (float)posY, (float)posZ, (float)posX2, (float)posX3, (float)posY2, (float)posZ2, count, countArray
      );

      for (int index = 0; index < this.itemList7.size(); index++) {
         EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)this.itemList7.get(index);
         double offset = entityPlayerAdapter.getBlockPos().computeValue(posX, posY, posZ);
         if (offset <= 256.0 || flag && offset <= 65536.0) {
            entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet);
         }
      }
   }

   @Override
   public void applyEntity(Entity entity) {
      super.applyEntity(entity);
      this.altItemArrayEntry.applyTarget(entity.getEntityId(), entity);
      this.map.put(entity.getEntityUniqueID(), entity);
      Entity[] entityArray = entity.getEntityArray();
      if (entityArray != null) {
         for (int index = 0; index < entityArray.length; index++) {
            this.altItemArrayEntry.applyTarget(entityArray[index].getEntityId(), entityArray[index]);
         }
      }
   }

   public Entity get(UUID uuid) {
      return (Entity)this.map.get(uuid);
   }

   @Override
   public void updateState3() {
      super.updateState3();
      if (this.randomSeedManager.getInfoManager() == InfoManager.infoManager4) {
         ObjectIterator iterator = this.objectOpenHashSet.iterator();

         while (iterator.hasNext()) {
            InterpolationCollector interpolationCollector = (InterpolationCollector)iterator.next();
            this.getLoggerEntry2(interpolationCollector.count3, interpolationCollector.count2).setEnabled4(false);
         }
      } else {
         for (ObjectIterator iterator2 = this.objectOpenHashSet.iterator(); iterator2.hasNext(); this.uNKNOWNManager.updateState()) {
            InterpolationCollector interpolationCollector2 = (InterpolationCollector)iterator2.next();
            int count = interpolationCollector2.count3 * 16;
            int count2 = interpolationCollector2.count2 * 16;
            this.uNKNOWNManager.applyTextByText("getChunk");
            AlertNotificationSound loggerEntry = this.getLoggerEntry2(interpolationCollector2.count3, interpolationCollector2.count2);
            this.applyAlertNotificationSound(count, count2, loggerEntry);
            this.uNKNOWNManager.applyText("tickChunk");
            loggerEntry.setEnabled4(false);
            this.uNKNOWNManager.applyText("thunder");
            if (this.random.nextInt(100000) == 0 && this.isActive() && this.isActiveAsFlag()) {
               this.count2 = this.count2 * 3 + 1013904223;
               int count3 = this.count2 >> 2;
               BlockPos blockPos = this.resolveBlockPos3(new BlockPos(count + (count3 & 15), 0, count2 + (count3 >> 8 & 15)));
               if (this.matchesBlockPos(blockPos)) {
                  this.checkNoEntityCollision(new HUIHRVManager(this, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()));
               }
            }

            this.uNKNOWNManager.applyText("iceandsnow");
            if (this.random.nextInt(16) == 0) {
               this.count2 = this.count2 * 3 + 1013904223;
               int count6 = this.count2 >> 2;
               BlockPos blockPos2 = this.resolveBlockPosByBlockPos(new BlockPos(count + (count6 & 15), 0, count2 + (count6 >> 8 & 15)));
               BlockPos blockPos3 = blockPos2.down();
               if (this.matchesBlockPosByBlockPos(blockPos3)) {
                  this.matchesBlockPos(blockPos3, Blocks.block53.getDefaultState());
               }

               if (this.isActive() && this.matchesBlockPos(blockPos2, true)) {
                  this.matchesBlockPos(blockPos2, Blocks.block74.getDefaultState());
               }

               if (this.isActive() && this.resolveBiomeIDIsOutOfBoundsManager(blockPos3).isActive2()) {
                  this.getBlockState(blockPos3).getBlock().processAction3(this, blockPos3);
               }
            }

            this.uNKNOWNManager.applyText("tickBlocks");
            int count7 = this.getDoFireTickManager().computeCount("randomTickSpeed");
            if (count7 > 0) {
               for (InternalCharArrayEntry internalCharArrayEntry : loggerEntry.getInternalCharArrayEntryArray()) {
                  if (internalCharArrayEntry != null && internalCharArrayEntry.isActive()) {
                     for (int index = 0; index < count7; index++) {
                        this.count2 = this.count2 * 3 + 1013904223;
                        int count8 = this.count2 >> 2;
                        int count9 = count8 & 15;
                        int count10 = count8 >> 8 & 15;
                        count8 = count8 >> 16 & 15;
                        IBlockState blockState = internalCharArrayEntry.resolveIBlockState(count9, count8, count10);
                        Block block = blockState.getBlock();
                        if (block.isState23()) {
                           block.processAction2(this, new BlockPos(count9 + count, count8 + internalCharArrayEntry.getCount3(), count10 + count2), blockState, this.random);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block, int count, int count2) {
      ExtendedComparableAdapter extendedComparableAdapter = new ExtendedComparableAdapter(blockPos, block);
      extendedComparableAdapter.setCount(count2);
      if (block.getMaterial() != Material.coreItemEntry) {
         extendedComparableAdapter.withTimestamp((long)count + this.randomSeedManager.getTimestamp2());
      }

      if (!this.set.contains(extendedComparableAdapter)) {
         this.set.add(extendedComparableAdapter);
         this.treeSet.add(extendedComparableAdapter);
      }
   }

   public List resolveList(int count, int count2, int count3, int count6, int count7, int count8) {
      ArrayList list = Lists.newArrayList();

      for (int index = 0; index < this.itemList3.size(); index++) {
         TileEntity tileEntity = (TileEntity)this.itemList3.get(index);
         BlockPos blockPos = tileEntity.getBlockPos();
         if (blockPos.getX() >= count && blockPos.getY() >= count2 && blockPos.getZ() >= count3 && blockPos.getX() < count6 && blockPos.getY() < count7 && blockPos.getZ() < count8) {
            list.add(tileEntity);
         }
      }

      return list;
   }

   @Override
   public void updateState2() {
      super.updateState2();
      if (this.getRandomSeedManager().isEnabled2() && this.getGameSettingsPeacefulType() != GameSettingsPeacefulType.HARD) {
         this.getRandomSeedManager().setGameSettingsPeacefulType2(GameSettingsPeacefulType.HARD);
      }

      this.secondaryFloatArrayEntry.getInvalidBiomeIdManager().updateState();
      if (this.isActive4()) {
         if (this.getDoFireTickManager().matchesText("doDaylightCycle")) {
            long value = this.randomSeedManager.getTimestamp() + 24000L;
            this.randomSeedManager.applyTime(value - value % 24000L);
         }

         this.updateState16();
      }

      this.uNKNOWNManager.applyTextByText("mobSpawner");
      if (this.getDoFireTickManager().matchesText("doMobSpawning") && this.randomSeedManager.getInfoManager() != InfoManager.infoManager4) {
         this.internalSetEntry.computeCount(this, this.enabled4, this.enabled, this.randomSeedManager.getTimestamp2() % 400L == 0L);
      }

      this.uNKNOWNManager.applyText("chunkSource");
      this.commonListener.isActive();
      int count = this.computeCount(1.0F);
      if (count != this.getIndexOffset()) {
         this.setIndexOffset(count);
      }

      this.randomSeedManager.setTimestamp2(this.randomSeedManager.getTimestamp2() + 1L);
      if (this.getDoFireTickManager().matchesText("doDaylightCycle")) {
         this.randomSeedManager.applyTime(this.randomSeedManager.getTimestamp() + 1L);
      }

      this.uNKNOWNManager.applyText("tickPending");
      this.checkFlag(false);
      this.uNKNOWNManager.applyText("tickBlocks");
      this.updateState3();
      this.uNKNOWNManager.applyText("chunkMap");
      this.managedLoggerEntry.updateState();
      this.uNKNOWNManager.applyText("village");
      this.worldCustomProperty.updateState4();
      this.tertiaryWorldEntry.updateState();
      this.uNKNOWNManager.applyText("portalForcer");
      this.worldServerEntry.applyTime(this.getTimestamp2());
      this.uNKNOWNManager.updateState();
      this.updateState13();
   }

   private boolean isActive3() {
      return this.vanillaRunnable.isEnabled12();
   }

   @Override
   public IChunkProvider getIChunkProvider() {
      GenericListener genericListener = this.managedListener.resolveGenericListener(this.secondaryFloatArrayEntry);
      this.listenerAdapter21 = new ListenerAdapter21(this, genericListener, this.secondaryFloatArrayEntry.getIChunkProvider());
      return this.listenerAdapter21;
   }

   @Override
   public ListenableFuture resolveListenableFuture(Runnable task) {
      return this.vanillaRunnable.resolveListenableFuture(task);
   }

   @Override
   public void applyBlockPosByBlockPos(BlockPos blockPos, Block block, int count, int count2) {
      RotationWatcher rotationWatcher = new RotationWatcher(blockPos, block, count, count2);
      Iterator iterator = this.arrayListAdapterArray[this.count5].iterator();

      while (iterator.hasNext()) {
         if (((RotationWatcher)iterator.next()).equals(rotationWatcher)) {
            return;
         }
      }

      this.arrayListAdapterArray[this.count5].add(rotationWatcher);
   }

   private boolean matchesRotationWatcher(RotationWatcher rotationWatcher) {
      IBlockState blockState = this.getBlockState(rotationWatcher.getBlockPos());
      return blockState.getBlock() == rotationWatcher.getBlock() ? blockState.getBlock().processAction(this, rotationWatcher.getBlockPos(), blockState, rotationWatcher.getCount(), rotationWatcher.getCount2()) : false;
   }

   @Override
   public void updateState10() {
      if (this.randomSeedManager.getCount4() <= 0) {
         this.randomSeedManager.setCount4(this.getIndexOffset1() + 1);
      }

      int count = this.randomSeedManager.getCount5();
      int count2 = this.randomSeedManager.getCount2();
      int count3 = 0;

      while (this.resolveBlock(new BlockPos(count, 0, count2)).getMaterial() == Material.coreItemEntry) {
         count += this.random.nextInt(8) - this.random.nextInt(8);
         count2 += this.random.nextInt(8) - this.random.nextInt(8);
         if (++count3 == 10000) {
            break;
         }
      }

      this.randomSeedManager.setCount5(count);
      this.randomSeedManager.setCount2(count2);
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block, int count) {
      this.applyBlockPos2(blockPos, block, count, 0);
   }

   @Override
   public World getWorld() {
      this.dataManager = new DataManager(this.managedListener);
      String text = WorldCustomProperty.resolveText(this.secondaryFloatArrayEntry);
      WorldCustomProperty worldCustomProperty = (WorldCustomProperty)this.dataManager.get(WorldCustomProperty.class, text);
      if (worldCustomProperty == null) {
         this.worldCustomProperty = new WorldCustomProperty(this);
         this.dataManager.put(text, this.worldCustomProperty);
      } else {
         this.worldCustomProperty = worldCustomProperty;
         this.worldCustomProperty.applyWorldByWorld(this);
      }

      this.thePlayerNameManager = new ThePlayerNameManagerAdapter(this.vanillaRunnable);
      AuxiliaryStringEntryHandler auxiliaryStringEntryHandler = (AuxiliaryStringEntryHandler)this.dataManager.get(AuxiliaryStringEntryHandler.class, "scoreboard");
      if (auxiliaryStringEntryHandler == null) {
         auxiliaryStringEntryHandler = new AuxiliaryStringEntryHandler();
         this.dataManager.put("scoreboard", auxiliaryStringEntryHandler);
      }

      auxiliaryStringEntryHandler.applyThePlayerNameManager(this.thePlayerNameManager);
      ((ThePlayerNameManagerAdapter)this.thePlayerNameManager).setAuxiliaryStringEntryHandler(auxiliaryStringEntryHandler);
      this.getInternalListEntry().applyDouble(this.randomSeedManager.getValue(), this.randomSeedManager.getValue6());
      this.getInternalListEntry().applyDouble2(this.randomSeedManager.getValue4());
      this.getInternalListEntry().applyDoubleByDouble(this.randomSeedManager.getValue2());
      this.getInternalListEntry().applyIndex(this.randomSeedManager.getCount8());
      this.getInternalListEntry().applyIndexByIndex(this.randomSeedManager.getCount3());
      if (this.randomSeedManager.getTimestamp6() > 0L) {
         this.getInternalListEntry()
            .applyDouble(this.randomSeedManager.getValue5(), this.randomSeedManager.getValue3(), this.randomSeedManager.getTimestamp6());
      } else {
         this.getInternalListEntry().applyDouble(this.randomSeedManager.getValue5());
      }

      return this;
   }

   public ClassHolder resolveClassHolder(MovementThresholdProperty movementThresholdProperty, BlockPos blockPos) {
      List list = this.getCommonListener().resolveList(movementThresholdProperty, blockPos);
      return list != null && !list.isEmpty() ? (ClassHolder)SmoothSystem.resolveDampenerSorter(this.random, list) : null;
   }

   private void applyAdvancedLongEntryByAdvancedLongEntr(AdvancedLongEntry advancedLongEntry) {
      if (!this.secondaryFloatArrayEntry.isActiveAsFlag()) {
         this.randomSeedManager.applyBlockPos(BlockPos.origin.resolveBlockPos2(this.secondaryFloatArrayEntry.getCount()));
      } else if (this.randomSeedManager.getInfoManager() == InfoManager.infoManager4) {
         this.randomSeedManager.applyBlockPos(BlockPos.origin.up());
      } else {
         this.state3 = true;
         InvalidBiomeIdManager invalidBiomeIdManager = this.secondaryFloatArrayEntry.getInvalidBiomeIdManager();
         List list = invalidBiomeIdManager.getList();
         Random random = new Random(this.getTimestamp());
         BlockPos blockPos = invalidBiomeIdManager.resolveBlockPos(0, 0, 256, list, random);
         int count = 0;
         int count2 = this.secondaryFloatArrayEntry.getCount();
         int count3 = 0;
         if (blockPos != null) {
            count = blockPos.getX();
            count3 = blockPos.getZ();
         } else {
            logger.warn("Unable to find spawn biome");
         }

         int count6 = 0;

         while (!this.secondaryFloatArrayEntry.checkIndex(count, count3)) {
            count += random.nextInt(64) - random.nextInt(64);
            count3 += random.nextInt(64) - random.nextInt(64);
            if (++count6 == 1000) {
               break;
            }
         }

         this.randomSeedManager.applyBlockPos(new BlockPos(count, count2, count3));
         this.state3 = false;
         if (advancedLongEntry.isEnabled4()) {
            this.updateState15();
         }
      }
   }

   @Override
   public List resolveList(DistanceAggregator distanceAggregator, boolean flag) {
      ArrayList list = null;

      for (int index = 0; index < 2; index++) {
         Iterator iterator;
         if (index == 0) {
            iterator = this.treeSet.iterator();
         } else {
            iterator = this.list10.iterator();
         }

         while (iterator.hasNext()) {
            ExtendedComparableAdapter extendedComparableAdapter = (ExtendedComparableAdapter)iterator.next();
            BlockPos blockPos = extendedComparableAdapter.blockPos;
            if (blockPos.getX() >= distanceAggregator.count3 && blockPos.getX() < distanceAggregator.count5 && blockPos.getZ() >= distanceAggregator.count4 && blockPos.getZ() < distanceAggregator.count) {
               if (flag) {
                  this.set.remove(extendedComparableAdapter);
                  iterator.remove();
               }

               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(extendedComparableAdapter);
            }
         }
      }

      return list;
   }

   public BlockPos resolveBlockPos3(BlockPos blockPos) {
      BlockPos blockPos2 = this.resolveBlockPosByBlockPos(blockPos);
      AxisAlignedBB boundingBox = new AxisAlignedBB(blockPos2, new BlockPos(blockPos2.getX(), this.getCount(), blockPos2.getZ())).offset(3.0, 3.0, 3.0);
      List list = this.resolveList(EntityLivingBase.class, boundingBox, new GeometryFormatter(this));
      return !list.isEmpty() ? ((EntityLivingBase)list.get(this.random.nextInt(list.size()))).getBlockPos() : blockPos2;
   }

   @Override
   public boolean contains(BlockPos blockPos, Block block) {
      ExtendedComparableAdapter extendedComparableAdapter = new ExtendedComparableAdapter(blockPos, block);
      return this.list10.contains(extendedComparableAdapter);
   }

   @Override
   public ListenableFuture resolveListenableFutureByRunnable(Runnable task) {
      return this.vanillaRunnable.resolveListenableFuture(task);
   }

   @Override
   public List resolveList(AlertNotificationSound alertNotificationSound, boolean flag) {
      InterpolationCollector interpolationCollector = alertNotificationSound.getInterpolationCollector();
      int count = (interpolationCollector.count3 << 4) - 2;
      int count2 = count + 16 + 2;
      int count3 = (interpolationCollector.count2 << 4) - 2;
      int count6 = count3 + 16 + 2;
      return this.resolveList(new DistanceAggregator(count, 0, count3, count2, 256, count6), flag);
   }

   private boolean isActive5() {
      return this.vanillaRunnable.isEnabled8();
   }

   public void applyAttackEffectsExplosionNorm(
      AttackEffectsExplosionNormalType attackEffectsExplosionNormalType,
      double posX,
      double posY,
      double posZ,
      int count,
      double posX2,
      double posX3,
      double posY2,
      double posZ2,
      int[] countArray
   ) {
      this.applyAttackEffectsExplosionNorm(attackEffectsExplosionNormalType, false, posX, posY, posZ, count, posX2, posX3, posY2, posZ2, countArray);
   }

   @Override
   public void applyEntityByEntity(Entity entity) {
      super.applyEntityByEntity(entity);
      this.altItemArrayEntry.resolveTarget(entity.getEntityId());
      this.map.remove(entity.getEntityUniqueID());
      Entity[] entityArray = entity.getEntityArray();
      if (entityArray != null) {
         for (int index = 0; index < entityArray.length; index++) {
            this.altItemArrayEntry.resolveTarget(entityArray[index].getEntityId());
         }
      }
   }

   @Override
   public boolean checkNoEntityCollision(Entity entity) {
      if (super.checkNoEntityCollision(entity)) {
         this.vanillaRunnable
            .getMovingManager()
            .applyPacket(entity.posX, entity.posY, entity.posZ, 512.0, this.secondaryFloatArrayEntry.getCountAsIndex(), new S2CPacketSpawnGlobalEntity(entity));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean isSingleplayer() {
      return this.vanillaRunnable.isSingleplayer();
   }

   public WorldServerEntry getWorldServerEntry() {
      return this.worldServerEntry;
   }

   public ManagedLoggerEntry getManagedLoggerEntry() {
      return this.managedLoggerEntry;
   }

   public void applyCompositeListener(boolean flag, CompositeListener compositeListener) throws ExceptionAdapter {
      if (this.commonListener.isActiveAsFlag()) {
         if (compositeListener != null) {
            compositeListener.applyText2("Saving level");
         }

         this.updateState12();
         if (compositeListener != null) {
            compositeListener.applyText("Saving chunks");
         }

         this.commonListener.matchesCompositeListener(flag, compositeListener);

         for (AlertNotificationSound alertNotificationSound : (Iterable<AlertNotificationSound>)(Iterable<?>)Lists.newArrayList(this.listenerAdapter21.getList())) {
            if (alertNotificationSound != null && !this.managedLoggerEntry.checkIndex(alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset)) {
               this.listenerAdapter21.applyIndex(alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset);
            }
         }
      }
   }

   public VanillaRunnable getVanillaRunnable() {
      return this.vanillaRunnable;
   }

   @Override
   public void updateState9() {
      boolean active = this.isActive();
      super.updateState9();
      if (this.factor4 != this.factor3) {
         this.vanillaRunnable.getMovingManager().applyPacket(new S2BPacketChangeGameState(7, this.factor3), this.secondaryFloatArrayEntry.getCountAsIndex());
      }

      if (this.factor != this.factor2) {
         this.vanillaRunnable.getMovingManager().applyPacket(new S2BPacketChangeGameState(8, this.factor2), this.secondaryFloatArrayEntry.getCountAsIndex());
      }

      if (active != this.isActive()) {
         if (active) {
            this.vanillaRunnable.getMovingManager().applyPacket(new S2BPacketChangeGameState(2, 0.0F));
         } else {
            this.vanillaRunnable.getMovingManager().applyPacket(new S2BPacketChangeGameState(1, 0.0F));
         }

         this.vanillaRunnable.getMovingManager().applyPacket(new S2BPacketChangeGameState(7, this.factor3));
         this.vanillaRunnable.getMovingManager().applyPacket(new S2BPacketChangeGameState(8, this.factor2));
      }
   }

   @Override
   public boolean checkFlag(boolean flag) {
      if (this.randomSeedManager.getInfoManager() == InfoManager.infoManager4) {
         return false;
      } else {
         int size = this.treeSet.size();
         if (size != this.set.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
         } else {
            if (size > 1000) {
               size = 1000;
            }

            this.uNKNOWNManager.applyTextByText("cleaning");

            for (int index = 0; index < size; index++) {
               ExtendedComparableAdapter extendedComparableAdapter = (ExtendedComparableAdapter)this.treeSet.first();
               if (!flag && extendedComparableAdapter.timestamp > this.randomSeedManager.getTimestamp2()) {
                  break;
               }

               this.treeSet.remove(extendedComparableAdapter);
               this.set.remove(extendedComparableAdapter);
               this.list10.add(extendedComparableAdapter);
            }

            this.uNKNOWNManager.updateState();
            this.uNKNOWNManager.applyTextByText("ticking");
            Iterator iterator = this.list10.iterator();

            while (iterator.hasNext()) {
               ExtendedComparableAdapter extendedComparableAdapter2 = (ExtendedComparableAdapter)iterator.next();
               iterator.remove();
               if (this.matchesBlockPos(extendedComparableAdapter2.blockPos.add(0, 0, 0), extendedComparableAdapter2.blockPos.add(0, 0, 0))) {
                  IBlockState blockState = this.getBlockState(extendedComparableAdapter2.blockPos);
                  if (blockState.getBlock().getMaterial() != Material.coreItemEntry && Block.matchesBlock(blockState.getBlock(), extendedComparableAdapter2.getBlock())) {
                     try {
                        blockState.getBlock().updateTick(this, extendedComparableAdapter2.blockPos, blockState, this.random);
                     } catch (Throwable throwable) {
                        CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Exception while ticking a block");
                        CrashReportCategory.applyCrashReportCategory(crashReport.resolveCrashReportCategory("Block being ticked"), extendedComparableAdapter2.blockPos, blockState);
                        throw new SecondaryRuntimeExceptionAdapter(crashReport);
                     }
                  }
               } else {
                  this.applyBlockPos(extendedComparableAdapter2.blockPos, extendedComparableAdapter2.getBlock(), 0);
               }
            }

            this.uNKNOWNManager.updateState();
            this.list10.clear();
            return !this.treeSet.isEmpty();
         }
      }
   }

   @Override
   public void applyEntity(Entity entity, byte byteValue) {
      this.getSecondaryLoggerEntry().applyEntity2(entity, new S19PacketEntityStatus(entity, byteValue));
   }

   @Override
   public BlockBreakState resolveBlockBreakState(Entity entity, double posX, double posY, double posZ, float factor, boolean flag, boolean flag2) {
      BlockBreakState blockBreakState = new BlockBreakState(this, entity, posX, posY, posZ, factor, flag, flag2);
      blockBreakState.updateState();
      blockBreakState.setEnabled(false);
      if (!flag2) {
         blockBreakState.clear();
      }

      for (EntityPlayer player : (Iterable<EntityPlayer>)(Iterable<?>)this.itemList7) {
         if (player.getDouble3(posX, posY, posZ) < 4096.0) {
            ((EntityPlayerAdapter)player)
               .auxiliaryListenerAdapter4
               .applyPacket(new S27PacketExplosion(posX, posY, posZ, factor, blockBreakState.getList(), (Vec3)blockBreakState.getMap().get(player)));
         }
      }

      return blockBreakState;
   }

   public BlockPos getBlockPosAsBlockPos() {
      return this.secondaryFloatArrayEntry.getBlockPos();
   }

   @Override
   public void updateState5() {
      this.enabled6 = false;
      if (!this.itemList7.isEmpty()) {
         int count = 0;
         int count2 = 0;

         for (EntityPlayer player : (Iterable<EntityPlayer>)(Iterable<?>)this.itemList7) {
            if (player.isActive23()) {
               count++;
            } else if (player.isState5()) {
               count2++;
            }
         }

         this.enabled6 = count2 > 0 && count2 >= this.itemList7.size() - count;
      }
   }

   public boolean matchesMovementThresholdProperty(MovementThresholdProperty movementThresholdProperty, ClassHolder classHolder, BlockPos blockPos) {
      List list = this.getCommonListener().resolveList(movementThresholdProperty, blockPos);
      return list != null && !list.isEmpty() ? list.contains(classHolder) : false;
   }

   public void updateState18() {
      if (this.commonListener.isActiveAsFlag()) {
         this.commonListener.updateState();
      }
   }

   private void updateState13() {
      while (!this.arrayListAdapterArray[this.count5].isEmpty()) {
         int count = this.count5;
         this.count5 ^= 1;

         for (RotationWatcher rotationWatcher : (Iterable<RotationWatcher>)(Iterable<?>)this.arrayListAdapterArray[count]) {
            if (this.matchesRotationWatcher(rotationWatcher)) {
               this.vanillaRunnable
                  .getMovingManager()
                  .applyPacket(
                     (double)rotationWatcher.getBlockPos().getX(),
                     (double)rotationWatcher.getBlockPos().getY(),
                     (double)rotationWatcher.getBlockPos().getZ(),
                     64.0,
                     this.secondaryFloatArrayEntry.getCountAsIndex(),
                     new S24PacketBlockAction(rotationWatcher.getBlockPos(), rotationWatcher.getBlock(), rotationWatcher.getCount(), rotationWatcher.getCount2())
                  );
            }
         }

         this.arrayListAdapterArray[count].clear();
      }
   }

   private void updateState19() {
      this.randomSeedManager.setEnabled7(false);
      this.randomSeedManager.setEnabled3(true);
      this.randomSeedManager.setEnabled4(false);
      this.randomSeedManager.setEnabled6(false);
      this.randomSeedManager.setCount(1000000000);
      this.randomSeedManager.applyTime(6000L);
      this.randomSeedManager.setWorldSettingsGameType(WorldSettingsGameType.SPECTATOR);
      this.randomSeedManager.setEnabled2(false);
      this.randomSeedManager.setGameSettingsPeacefulType2(GameSettingsPeacefulType.PEACEFUL);
      this.randomSeedManager.setEnabled(true);
      this.getDoFireTickManager().applyText("doDaylightCycle", "false");
   }

   public void updateState12() throws ExceptionAdapter {
      this.updateState6();
      this.randomSeedManager.setValue5(this.getInternalListEntry().getValue3());
      this.randomSeedManager.setValue(this.getInternalListEntry().getValue5());
      this.randomSeedManager.setValue6(this.getInternalListEntry().getValue6());
      this.randomSeedManager.setValue2(this.getInternalListEntry().getValue4());
      this.randomSeedManager.setValue4(this.getInternalListEntry().getValue10());
      this.randomSeedManager.setCount8(this.getInternalListEntry().getCount2());
      this.randomSeedManager.setCount3(this.getInternalListEntry().getCount3());
      this.randomSeedManager.setValue3(this.getInternalListEntry().getValue7());
      this.randomSeedManager.setTimestamp6(this.getInternalListEntry().getTimestamp());
      this.managedListener.applyRandomSeedManager(this.randomSeedManager, this.vanillaRunnable.getMovingManager().getNBTTagCompound());
      this.dataManager.updateState();
   }

   public void updateState15() {
      RotationStack rotationStack = new RotationStack(list7, 10);

      for (int index = 0; index < 10; index++) {
         int count = this.randomSeedManager.getCount5() + this.random.nextInt(6) - this.random.nextInt(6);
         int count2 = this.randomSeedManager.getCount2() + this.random.nextInt(6) - this.random.nextInt(6);
         BlockPos blockPos = this.resolveBlockPos(new BlockPos(count, 0, count2)).up();
         if (rotationStack.matchesWorld(this, this.random, blockPos)) {
            break;
         }
      }
   }

   public void updateState17() {
      this.managedListener.updateState();
   }

   private void updateState14() {
      this.randomSeedManager.setCount6(0);
      this.randomSeedManager.setEnabled4(false);
      this.randomSeedManager.setCount10(0);
      this.randomSeedManager.setEnabled6(false);
   }

   public void updateState16() {
      this.enabled6 = false;

      for (EntityPlayer player : (Iterable<EntityPlayer>)(Iterable<?>)this.itemList7) {
         if (player.isState5()) {
            player.applyFlag(false, false, true);
         }
      }

      this.updateState14();
   }

   public void updateState20() {
      this.count4 = 0;
   }

   public WorldServer(VanillaRunnable vanillaRunnable2, ISaveHandler iSaveHandler, RandomSeedManager randomSeedManager, int count, UNKNOWNManager uNKNOWNManager) {
      super(iSaveHandler, randomSeedManager, SecondaryFloatArrayEntry.resolveSecondaryFloatArrayEntry(count), uNKNOWNManager, false);
      this.vanillaRunnable = vanillaRunnable2;
      this.secondaryLoggerEntry = new SecondaryLoggerEntry(this);
      this.managedLoggerEntry = new ManagedLoggerEntry(this);
      this.secondaryFloatArrayEntry.applyWorldByWorld(this);
      this.commonListener = this.getIChunkProvider();
      this.worldServerEntry = new WorldServerEntry(this);
      this.updateState8();
      this.updateState7();
      this.getInternalListEntry().setCount(vanillaRunnable2.getCount5());
   }
}
