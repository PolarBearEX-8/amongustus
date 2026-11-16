package net.minecraft.world;

import client.Client;
import client.ClientGlobals;
import client.event.ContextListener;
import client.event.PreUpdateConsumer;
import client.module.Module;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.CombatConfigProperty;
import client.setting.CombatGroupProperty;
import client.setting.GameSettingsPeacefulType;
import client.setting.MovementChoiceProperty;
import client.setting.MovementFactorProperty;
import client.setting.MovementModeProperty;
import client.util.AdapterStringEntry;
import client.util.AdvancedLongEntry;
import client.util.AlertNotificationSound;
import client.util.AuxiliaryCallableAdapter;
import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.CallableHandler;
import client.util.DataManager;
import client.util.DistanceAggregator;
import client.util.DoFireTickManager;
import client.util.ExceptionAdapter;
import client.util.IBlockState;
import client.util.InfoManager;
import client.util.InterpolationCollector;
import client.util.InvalidBiomeIdManager;
import client.util.LivingEntityFilter;
import client.util.MovingObjectType;
import client.util.RandomSeedManager;
import client.util.SecondaryFloatArrayEntry;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SpecialCallableAdapter;
import client.util.TertiaryCallableAdapter;
import client.util.ThePlayerNameManager;
import client.util.UNKNOWNManager;
import client.util.VanillaRunnable;
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
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakState;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockRedSandstoneStairs;
import net.minecraft.block.BlockSnowLayer;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExtraBlockAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.AltItemArrayEntry;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public abstract class World implements IBlockAccess {
   public int indexOffset1 = 63;
   public boolean enabled3;
   public final List loadedEntityList = Lists.newArrayList();
   public final List list3 = Lists.newArrayList();
   public final List itemList3 = Lists.newArrayList();
   public final List list2 = Lists.newArrayList();
   public final List list6 = Lists.newArrayList();
   public final List list5 = Lists.newArrayList();
   public final List itemList7 = Lists.newArrayList();
   public final HashMap hashMap = new HashMap();
   public final List list4 = Lists.newArrayList();
   public final AltItemArrayEntry altItemArrayEntry = new AltItemArrayEntry();
   public long timestamp = 16777215L;
   public int indexOffset;
   public int count2 = new Random().nextInt();
   public final int count = 1013904223;
   public float factor4;
   public float factor3;
   public float factor;
   public float factor2;
   public int indexOffset2;
   public final Random random = new Random();
   public final SecondaryFloatArrayEntry secondaryFloatArrayEntry;
   public List list = Lists.newArrayList();
   public IChunkProvider commonListener;
   public final ISaveHandler managedListener;
   public RandomSeedManager randomSeedManager;
   public boolean state3;
   public DataManager dataManager;
   public WorldCustomProperty worldCustomProperty;
   public final UNKNOWNManager uNKNOWNManager;
   public final Calendar calendar = Calendar.getInstance();
   public ThePlayerNameManager thePlayerNameManager = new ThePlayerNameManager();
   public final boolean state;
   public ObjectOpenHashSet objectOpenHashSet = new ObjectOpenHashSet();
   public int count3 = this.random.nextInt(12000);
   public boolean enabled4 = true;
   public boolean enabled = true;
   public boolean enabled2;
   public final WorldInfo internalListEntry;
   public int[] countArray = new int[32768];

   public long getTimestamp2() {
      return this.randomSeedManager.getTimestamp2();
   }

   public void updateState7() {
      if (this.randomSeedManager.isEnabled4()) {
         this.factor3 = 1.0F;
         if (this.randomSeedManager.isEnabled6()) {
            this.factor2 = 1.0F;
         }
      }
   }

   public IBlockState getIBlockState(AlertNotificationSound alertNotificationSound, int count4, int count5, int count6) {
      return alertNotificationSound.resolveIBlockState(count4, count5, count6);
   }

   public void applyBlockPos2(BlockPos blockPos, Block block, int count4, int count5) {
   }

   public void applyAdvancedLongEntry(AdvancedLongEntry advancedLongEntry) {
      this.randomSeedManager.setEnabled5(true);
   }

   public boolean matchesMaterialByMaterial(double posX, double posX2, double posX3, double posX4, double posY, double posZ, Material material, Entity entity) {
      int count4 = MathHelper.ceiling_double_int(posX);
      int count5 = MathHelper.ceiling_double_int(posX4 + 1.0);
      int count6 = MathHelper.ceiling_double_int(posX2);
      int count7 = MathHelper.ceiling_double_int(posY + 1.0);
      int count8 = MathHelper.ceiling_double_int(posX3);
      int count9 = MathHelper.ceiling_double_int(posZ + 1.0);
      if (!this.checkIndex(count4, count6, count8, count5, count7, count9, true)) {
         return false;
      } else {
         boolean flag = false;
         Vec3 vec = new Vec3(0.0, 0.0, 0.0);

         for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(); count4 < count5; count4++) {
            for (int index = count6; index < count7; index++) {
               for (int index2 = count8; index2 < count9; index2++) {
                  auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index, index2);
                  IBlockState blockState = this.getBlockState(auxiliaryBlockPosAdapter);
                  Block block = blockState.getBlock();
                  if (block.getMaterial() == material) {
                     double offset = (double)(
                        (float)(index + 1) - BlockLiquid.computeFactor((Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry))
                     );
                     if ((double)count7 >= offset) {
                        flag = true;
                        vec = block.processAction(this, auxiliaryBlockPosAdapter, entity, vec);
                     }
                  }
               }
            }
         }

         if (vec.getValueAsDouble() > 0.0 && entity.isActive8()) {
            vec = vec.normalize();
            double offset2 = 0.014;
            entity.motionX = entity.motionX + vec.xCoord * offset2;
            entity.motionY = entity.motionY + vec.yCoord * offset2;
            entity.motionZ = entity.motionZ + vec.zCoord * offset2;
         }

         return flag;
      }
   }

   public boolean matchesWorldInfoByWorldInfo(WorldInfo worldInfo, Entity entity) {
      double value = worldInfo.getValue9();
      double value2 = worldInfo.getValue();
      double value3 = worldInfo.getValue2();
      double valueAsDouble = worldInfo.getValueAsDouble();
      if (entity.isState()) {
         value++;
         value2++;
         value3--;
         valueAsDouble--;
      } else {
         value--;
         value2--;
         value3++;
         valueAsDouble++;
      }

      return entity.posX > value && entity.posX < value3 && entity.posZ > value2 && entity.posZ < valueAsDouble;
   }

   public BlockPos resolveBlockPosByBlockPos(BlockPos blockPos) {
      return this.getLoggerEntry(blockPos).resolveBlockPos(blockPos);
   }

   public boolean matchesBlockPos(BlockPos blockPos, EnumFacing enumFacing) {
      return this.computeCountByBlockPos(blockPos, enumFacing) > 0;
   }

   public void setIndexOffset(int indexOffset3) {
      this.indexOffset = indexOffset3;
   }

   public void applyBlock(Block block, BlockPos blockPos, Random random2) {
      this.enabled3 = true;
      block.updateTick(this, blockPos, this.getBlockState(blockPos), random2);
      this.enabled3 = false;
   }

   public DoFireTickManager getDoFireTickManager() {
      return this.randomSeedManager.getDoFireTickManager();
   }

   public AlertNotificationSound getLoggerEntry(int count4, int count5) {
      return this.getLoggerEntry2(count4 >> 4, count5 >> 4);
   }

   public boolean isActiveAsFlag() {
      return (double)this.getFloat7(1.0F) > 0.9;
   }

   public int computeCountByIndex(int count4, int count5, int count6) {
      return this.computeCount(count4, count5, count6, true);
   }

   public void applyBlockPos(BlockPos blockPos, TileEntity tileEntity) {
      if (tileEntity != null && !tileEntity.isEnabled()) {
         if (this.enabled2) {
            tileEntity.setBlockPos(blockPos);
            Iterator iterator = this.list6.iterator();

            while (iterator.hasNext()) {
               TileEntity tileEntity2 = (TileEntity)iterator.next();
               if (tileEntity2.getBlockPos().equals(blockPos)) {
                  tileEntity2.updateState3();
                  iterator.remove();
               }
            }

            this.list6.add(tileEntity);
         } else {
            this.matchesTileEntity(tileEntity);
            this.getLoggerEntry(blockPos).applyBlockPos(blockPos, tileEntity);
         }
      }
   }

   public boolean matchesEntityPlayer(EntityPlayer player, BlockPos blockPos) {
      return true;
   }

   public int computeCountByCombatConfigProp(CombatConfigProperty combatConfigProperty, int count4, int count5, int count6) {
      if (this.secondaryFloatArrayEntry.isEnabled() && combatConfigProperty == CombatConfigProperty.SKY) {
         return 0;
      } else {
         if (count5 < 0) {
            count5 = 0;
         }

         if (!this.checkIndex3(count4, count5, count6)) {
            return combatConfigProperty.defaultLightValue;
         } else if (!this.checkIndex(count4, count5, count6)) {
            return combatConfigProperty.defaultLightValue;
         } else if (this.getIBlockState(count4, count5, count6).getBlock().isState25()) {
            int count7 = this.computeCount(combatConfigProperty, count4, count5 + 1, count6);
            int count8 = this.computeCount(combatConfigProperty, count4 + 1, count5, count6);
            int count9 = this.computeCount(combatConfigProperty, count4 - 1, count5, count6);
            int count10 = this.computeCount(combatConfigProperty, count4, count5, count6 + 1);
            int count11 = this.computeCount(combatConfigProperty, count4, count5, count6 - 1);
            if (count8 > count7) {
               count7 = count8;
            }

            if (count9 > count7) {
               count7 = count9;
            }

            if (count10 > count7) {
               count7 = count10;
            }

            if (count11 > count7) {
               count7 = count11;
            }

            return count7;
         } else {
            return this.getLoggerEntry(count4, count6).computeCount(combatConfigProperty, count4, count5, count6);
         }
      }
   }

   public CrashReportCategory resolveCrashReportCategory(CrashReport crashReport) {
      CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Affected level", 1);
      crashReportCategory.add("Level name", this.randomSeedManager == null ? "????" : this.randomSeedManager.getLabelText());
      crashReportCategory.applyText("All players", new AuxiliaryCallableAdapter(this));
      crashReportCategory.applyText("Chunk stats", new TertiaryCallableAdapter(this));

      try {
         this.randomSeedManager.applyCrashReportCategory(crashReportCategory);
      } catch (Throwable throwable) {
         crashReportCategory.applyText("Level Data Unobtainable", throwable);
      }

      return crashReportCategory;
   }

   public void spawnEntityInWorld(Entity entity) {
      if (entity.entity != null) {
         entity.entity.setEntity8((Entity)null);
      }

      if (entity.ridingEntity != null) {
         entity.setEntity8((Entity)null);
      }

      entity.updateState9();
      if (entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;
         if (player.getGameProfile() != null) {
            this.hashMap.remove(player.getGameProfile().getName());
         }

         this.itemList7.remove(entity);
         this.updateState5();
         this.applyEntityByEntity(entity);
      }
   }

   @Override
   public IBlockState getBlockState(BlockPos blockPos) {
      return !this.matchesBlockPos4(blockPos) ? Blocks.air.getDefaultState() : this.getLoggerEntry(blockPos).resolveIBlockState(blockPos);
   }

   public WorldCustomProperty getWorldCustomProperty() {
      return this.worldCustomProperty;
   }

   public boolean matchesBlockPos8(BlockPos blockPos) {
      return this.matchesBlockPos(blockPos, Blocks.air.getDefaultState(), 3);
   }

   public void applyEntity(Entity entity, boolean flag) {
      int count4 = MathHelper.ceiling_double_int(entity.posX);
      int count5 = MathHelper.ceiling_double_int(entity.posZ);
      if (!flag || this.checkIndex(count4 - 32, 0, count5 - 32, count4 + 32, 0, count5 + 32, true)) {
         entity.lastTickPosX = entity.posX;
         entity.lastTickPosY = entity.posY;
         entity.lastTickPosZ = entity.posZ;
         entity.prevRotationYaw = entity.rotationYaw;
         entity.prevRotationPitch = entity.rotationPitch;
         if (flag && entity.enabled9) {
            entity.ticksExisted++;
            if (entity.ridingEntity != null) {
               entity.updateState21();
            } else {
               entity.updateState();
            }
         }

         this.uNKNOWNManager.applyTextByText("chunkCheck");
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

         count4 = MathHelper.ceiling_double_int(entity.posX / 16.0);
         count5 = MathHelper.ceiling_double_int(entity.posY / 16.0);
         int count6 = MathHelper.ceiling_double_int(entity.posZ / 16.0);
         if (!entity.enabled9 || entity.count7 != count4 || entity.count2 != count5 || entity.count8 != count6) {
            if (entity.enabled9 && this.checkIndex(entity.count7, entity.count8, true)) {
               this.getLoggerEntry2(entity.count7, entity.count8).applyEntityByEntity(entity, entity.count2);
            }

            if (this.checkIndex(count4, count6, true)) {
               entity.enabled9 = true;
               this.getLoggerEntry2(count4, count6).applyEntityByEntity(entity);
            } else {
               entity.enabled9 = false;
            }
         }

         this.uNKNOWNManager.updateState();
         if (flag && entity.enabled9 && entity.entity != null) {
            if (!entity.entity.isDead && entity.entity.ridingEntity == entity) {
               this.removeEntity(entity.entity);
            } else {
               entity.entity.ridingEntity = null;
               entity.entity = null;
            }
         }
      }
   }

   public boolean checkIndex2(int count4, int count5, int count6) {
      return this.getLoggerEntry(count4, count6).checkIndex(count4, count5, count6);
   }

   public BlockPos getBlockPos() {
      BlockPos blockPos = new BlockPos(this.randomSeedManager.getCount5(), this.randomSeedManager.getCount4(), this.randomSeedManager.getCount2());
      if (!this.getInternalListEntry().checkBlockPos(blockPos)) {
         blockPos = this.resolveBlockPos2(new BlockPos(this.getInternalListEntry().getValue5(), 0.0, this.getInternalListEntry().getValue6()));
      }

      return blockPos;
   }

   @Override
   public int computeCount(BlockPos blockPos, EnumFacing enumFacing) {
      IBlockState blockState = this.getBlockState(blockPos);
      return blockState.getBlock().processAction2(this, blockPos, blockState, enumFacing);
   }

   public void updateState4() {
      this.uNKNOWNManager.applyTextByText("entities");
      this.uNKNOWNManager.applyTextByText("global");

      for (int index = 0; index < this.list4.size(); index++) {
         Entity entity = (Entity)this.list4.get(index);

         try {
            entity.ticksExisted++;
            entity.updateState();
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Ticking entity");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Entity being ticked");
            if (entity == null) {
               crashReportCategory.add("Entity", "~~NULL~~");
            } else {
               entity.applyCrashReportCategory(crashReportCategory);
            }

            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }

         if (entity.isDead) {
            this.list4.remove(index--);
         }
      }

      this.uNKNOWNManager.applyText("remove");
      this.loadedEntityList.removeAll(this.list3);

      for (int index2 = 0; index2 < this.list3.size(); index2++) {
         Entity entity2 = (Entity)this.list3.get(index2);
         int count4 = entity2.count7;
         int count5 = entity2.count8;
         if (entity2.enabled9 && this.checkIndex(count4, count5, true)) {
            this.getLoggerEntry2(count4, count5).applyEntity3(entity2);
         }
      }

      for (int index3 = 0; index3 < this.list3.size(); index3++) {
         this.applyEntityByEntity((Entity)this.list3.get(index3));
      }

      this.list3.clear();
      this.uNKNOWNManager.applyText("regular");
      int count6 = -1;

      for (int index4 = 0; index4 < this.loadedEntityList.size(); index4++) {
         Entity entity3 = (Entity)this.loadedEntityList.get(index4);
         if (entity3 instanceof EntityPlayerSP) {
            count6 = index4;
         } else {
            index4 = this.computeCount(entity3, index4);
         }
      }

      if (count6 != -1) {
         Entity entity4 = (Entity)this.loadedEntityList.get(count6);
         this.computeCount(entity4, count6);
      }

      this.uNKNOWNManager.applyText("blockEntities");
      this.enabled2 = true;
      Iterator iterator = this.list2.iterator();

      while (iterator.hasNext()) {
         TileEntity tileEntity = (TileEntity)iterator.next();
         if (!tileEntity.isEnabled() && tileEntity.isActive()) {
            BlockPos blockPos = tileEntity.getBlockPos();
            if (this.checkBlockPos12(blockPos) && this.internalListEntry.checkBlockPos(blockPos)) {
               try {
                  ((PreUpdateConsumer)tileEntity).updateState();
               } catch (Throwable throwable2) {
                  CrashReport crashReport2 = CrashReport.resolveCrashReport(throwable2, "Ticking block entity");
                  CrashReportCategory crashReportCategory2 = crashReport2.resolveCrashReportCategory("Block entity being ticked");
                  tileEntity.applyCrashReportCategory(crashReportCategory2);
                  throw new SecondaryRuntimeExceptionAdapter(crashReport2);
               }
            }
         }

         if (tileEntity.isEnabled()) {
            iterator.remove();
            this.itemList3.remove(tileEntity);
            if (this.checkBlockPos12(tileEntity.getBlockPos())) {
               this.getLoggerEntry(tileEntity.getBlockPos()).applyBlockPos(tileEntity.getBlockPos());
            }
         }
      }

      this.enabled2 = false;
      if (!this.list5.isEmpty()) {
         this.list2.removeAll(this.list5);
         this.itemList3.removeAll(this.list5);
         this.list5.clear();
      }

      this.uNKNOWNManager.applyText("pendingBlockEntities");
      if (!this.list6.isEmpty()) {
         for (int index5 = 0; index5 < this.list6.size(); index5++) {
            TileEntity tileEntity2 = (TileEntity)this.list6.get(index5);
            if (!tileEntity2.isEnabled()) {
               if (!this.itemList3.contains(tileEntity2)) {
                  this.matchesTileEntity(tileEntity2);
               }

               if (this.checkBlockPos12(tileEntity2.getBlockPos())) {
                  this.getLoggerEntry(tileEntity2.getBlockPos()).applyBlockPos(tileEntity2.getBlockPos(), tileEntity2);
               }

               this.applyBlockPos2(tileEntity2.getBlockPos());
            }
         }

         this.list6.clear();
      }

      this.uNKNOWNManager.updateState();
      this.uNKNOWNManager.updateState();
   }

   public float computeFactor3(float factor5) {
      float factor6 = this.computeFactor(factor5);
      factor6 = 1.0F - (MathHelper.cos(factor6 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);
      factor6 = MathHelper.clamp_float(factor6, 0.0F, 1.0F);
      factor6 = 1.0F - factor6;
      factor6 = (float)((double)factor6 * (1.0 - (double)(this.getFloat6(factor5) * 5.0F) / 16.0));
      factor6 = (float)((double)factor6 * (1.0 - (double)(this.getFloat7(factor5) * 5.0F) / 16.0));
      return factor6 * 0.8F + 0.2F;
   }

   public void applyEntity4(Entity entity) {
      entity.updateState9();
      if (entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;
         if (player.getGameProfile() != null) {
            this.hashMap.remove(player.getGameProfile().getName());
         }

         this.itemList7.remove(entity);
         this.updateState5();
      }

      int count4 = entity.count7;
      int count5 = entity.count8;
      if (entity.enabled9 && this.checkIndex(count4, count5, true)) {
         this.getLoggerEntry2(count4, count5).applyEntity3(entity);
      }

      this.loadedEntityList.remove(entity);
      this.applyEntityByEntity(entity);
   }

   public boolean matchesBlockPos(BlockPos blockPos, BlockPos blockPos2, boolean flag) {
      return this.checkIndex(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), flag);
   }

   public int computeCount(float factor5) {
      float factor6 = this.computeFactor(factor5);
      factor6 = 1.0F - (MathHelper.cos(factor6 * (float) Math.PI * 2.0F) * 2.0F + 0.5F);
      factor6 = MathHelper.clamp_float(factor6, 0.0F, 1.0F);
      factor6 = 1.0F - factor6;
      factor6 = (float)((double)factor6 * (1.0 - (double)(this.getFloat6(factor5) * 5.0F) / 16.0));
      factor6 = (float)((double)factor6 * (1.0 - (double)(this.getFloat7(factor5) * 5.0F) / 16.0));
      factor6 = 1.0F - factor6;
      return (int)(factor6 * 11.0F);
   }

   public abstract int getCountAsIndex();

   @Override
   public TileEntity resolveTileEntity(BlockPos blockPos) {
      if (!this.matchesBlockPos4(blockPos)) {
         return null;
      } else {
         TileEntity tileEntity = null;
         if (this.enabled2) {
            for (int index = 0; index < this.list6.size(); index++) {
               TileEntity tileEntity2 = (TileEntity)this.list6.get(index);
               if (!tileEntity2.isEnabled() && tileEntity2.getBlockPos().equals(blockPos)) {
                  tileEntity = tileEntity2;
                  break;
               }
            }
         }

         if (tileEntity == null) {
            tileEntity = this.getLoggerEntry(blockPos).resolveTileEntity(blockPos, CombatGroupProperty.IMMEDIATE);
         }

         if (tileEntity == null) {
            for (int index2 = 0; index2 < this.list6.size(); index2++) {
               TileEntity tileEntity3 = (TileEntity)this.list6.get(index2);
               if (!tileEntity3.isEnabled() && tileEntity3.getBlockPos().equals(blockPos)) {
                  tileEntity = tileEntity3;
                  break;
               }
            }
         }

         return tileEntity;
      }
   }

   public void applyBlockPos(int count4, BlockPos blockPos, int count5) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyBlockPos(count4, blockPos, count5);
      }
   }

   public void add(ContextListener contextListener) {
      this.list.add(contextListener);
   }

   public boolean checkDouble(double posX, double posX2, double posY, double posZ) {
      for (int index = 0; index < this.itemList7.size(); index++) {
         EntityPlayer player = (EntityPlayer)this.itemList7.get(index);
         if (LivingEntityFilter.predicate4.apply(player)) {
            double doubleItem = player.getDouble3(posX, posX2, posY);
            if (posZ < 0.0 || doubleItem < posZ * posZ) {
               return true;
            }
         }
      }

      return false;
   }

   public void applyEntityPlayerByEntityPlayer(EntityPlayer player, int count4, BlockPos blockPos, int count5) {
      try {
         for (int index = 0; index < this.list.size(); index++) {
            ((ContextListener)this.list.get(index)).applyEntityPlayerByEntityPlayer(player, count4, blockPos, count5);
         }
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Playing level event");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Level event being played");
         crashReportCategory.add("Block coordinates", CrashReportCategory.resolveText(blockPos));
         crashReportCategory.add("Event source", player);
         crashReportCategory.add("Event type", count4);
         crashReportCategory.add("Event data", count5);
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   public Vec3 resolveVec3(Entity entity, float factor5) {
      float factor6 = MathHelper.cos(this.computeFactor(factor5) * (float) Math.PI * 2.0F) * 2.0F + 0.5F;
      factor6 = MathHelper.clamp_float(factor6, 0.0F, 1.0F);
      int count4 = MathHelper.ceiling_double_int(entity.posX);
      int count5 = MathHelper.ceiling_double_int(entity.posY);
      int count6 = MathHelper.ceiling_double_int(entity.posZ);
      BlockPos blockPos = new BlockPos(count4, count5, count6);
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = this.resolveBiomeIDIsOutOfBoundsManager(blockPos);
      float factor7 = biomeIDIsOutOfBoundsManager.computeFactor(blockPos);
      count4 = biomeIDIsOutOfBoundsManager.computeCount(factor7);
      factor7 = (float)(count4 >> 16 & 0xFF) / 255.0F;
      float factor8 = (float)(count4 >> 8 & 0xFF) / 255.0F;
      float factor9 = (float)(count4 & 0xFF) / 255.0F;
      factor7 *= factor6;
      factor8 *= factor6;
      factor9 *= factor6;
      factor6 = this.getFloat6(factor5);
      if (factor6 > 0.0F) {
         float factor10 = (factor7 * 0.3F + factor8 * 0.59F + factor9 * 0.11F) * 0.6F;
         factor6 = 1.0F - factor6 * 0.75F;
         factor7 = factor7 * factor6 + factor10 * (1.0F - factor6);
         factor8 = factor8 * factor6 + factor10 * (1.0F - factor6);
         factor9 = factor9 * factor6 + factor10 * (1.0F - factor6);
      }

      float floatItem = this.getFloat7(factor5);
      if (floatItem > 0.0F) {
         factor6 = (factor7 * 0.3F + factor8 * 0.59F + factor9 * 0.11F) * 0.2F;
         floatItem = 1.0F - floatItem * 0.75F;
         factor7 = factor7 * floatItem + factor6 * (1.0F - floatItem);
         factor8 = factor8 * floatItem + factor6 * (1.0F - floatItem);
         factor9 = factor9 * floatItem + factor6 * (1.0F - floatItem);
      }

      if (this.indexOffset2 > 0) {
         factor6 = (float)this.indexOffset2 - factor5;
         if (factor6 > 1.0F) {
            factor6 = 1.0F;
         }

         factor6 *= 0.45F;
         factor7 = factor7 * (1.0F - factor6) + 0.8F * factor6;
         factor8 = factor8 * (1.0F - factor6) + 0.8F * factor6;
         factor9 = factor9 * (1.0F - factor6) + 1.0F * factor6;
      }

      return new Vec3((double)factor7, (double)factor8, (double)factor9);
   }

   private boolean matchesBlockPos4(BlockPos blockPos) {
      return blockPos.getX() >= -30000000 && blockPos.getZ() >= -30000000 && blockPos.getX() < 30000000 && blockPos.getZ() < 30000000 && blockPos.getY() >= 0 && blockPos.getY() < 256;
   }

   public void applyEntityByEntity(Entity entity, String text, float factor5, float factor6) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyText(text, entity.posX, entity.posY, entity.posZ, factor5, factor6);
      }
   }

   @Deprecated
   public int computeCount(BlockPos blockPos, int count4) {
      return this.computeCount(blockPos.getX(), blockPos.getY(), blockPos.getZ(), count4);
   }

   public boolean matchesCombatConfigProperty(CombatConfigProperty combatConfigProperty, int count4, int count5, int count6) {
      if (!this.checkIndex(count4, count5, count6, 17, false)) {
         return false;
      } else {
         int count7 = 0;
         int count8 = 0;
         this.uNKNOWNManager.applyTextByText("getBrightness");
         int count9 = this.computeCount(combatConfigProperty, count4, count5, count6);
         int count10 = this.computeCount(count4, count5, count6, combatConfigProperty);
         int count11 = count4;
         int count12 = count5;
         int count13 = count6;
         if (count10 > count9) {
            this.countArray[count8++] = 133152;
         } else if (count10 < count9) {
            this.countArray[count8++] = 133152 | count9 << 18;

            while (count7 < count8) {
               count9 = this.countArray[count7++];
               count10 = (count9 & 63) - 32 + count11;
               int count14 = (count9 >> 6 & 63) - 32 + count12;
               int count15 = (count9 >> 12 & 63) - 32 + count13;
               count9 = count9 >> 18 & 15;
               int count16 = this.computeCount(combatConfigProperty, count10, count14, count15);
               if (count16 == count9) {
                  this.applyCombatConfigProperty(combatConfigProperty, count10, count14, count15, 0);
                  if (count9 > 0) {
                     int count17 = MathHelper.computeCount(count10 - count11);
                     int count18 = MathHelper.computeCount(count14 - count12);
                     int count19 = MathHelper.computeCount(count15 - count13);
                     if (count17 + count18 + count19 < 17) {
                        for (EnumFacing enumFacing : EnumFacing.getEntries()) {
                           count19 = count10 + enumFacing.getInt2();
                           int count20 = count14 + enumFacing.getInt3();
                           int count21 = count15 + enumFacing.getInt();
                           int count22 = Math.max(1, this.getIBlockState(count19, count20, count21).getBlock().getIndexOffset());
                           count16 = this.computeCount(combatConfigProperty, count19, count20, count21);
                           if (count16 == count9 - count22 && count8 < this.countArray.length) {
                              this.countArray[count8++] = count19 - count11 + 32 | count20 - count12 + 32 << 6 | count21 - count13 + 32 << 12 | count9 - count22 << 18;
                           }
                        }
                     }
                  }
               }
            }

            count7 = 0;
         }

         this.uNKNOWNManager.updateState();
         this.uNKNOWNManager.applyTextByText("checkedPosition < toCheckCount");

         while (count7 < count8) {
            count9 = this.countArray[count7++];
            count10 = (count9 & 63) - 32 + count11;
            int count23 = (count9 >> 6 & 63) - 32 + count12;
            int count24 = (count9 >> 12 & 63) - 32 + count13;
            count9 = this.computeCount(combatConfigProperty, count10, count23, count24);
            int count25 = this.computeCount(count10, count23, count24, combatConfigProperty);
            if (count25 != count9) {
               this.applyCombatConfigProperty(combatConfigProperty, count10, count23, count24, count25);
               if (count25 > count9) {
                  int count26 = Math.abs(count10 - count11);
                  int count27 = Math.abs(count23 - count12);
                  int count28 = Math.abs(count24 - count13);
                  boolean flag = count8 < this.countArray.length - 6;
                  if (count26 + count27 + count28 < 17 && flag) {
                     if (this.computeCount(combatConfigProperty, count10 - 1, count23, count24) < count25) {
                        this.countArray[count8++] = count10 - 1 - count11 + 32 + (count23 - count12 + 32 << 6) + (count24 - count13 + 32 << 12);
                     }

                     if (this.computeCount(combatConfigProperty, count10 + 1, count23, count24) < count25) {
                        this.countArray[count8++] = count10 + 1 - count11 + 32 + (count23 - count12 + 32 << 6) + (count24 - count13 + 32 << 12);
                     }

                     if (this.computeCount(combatConfigProperty, count10, count23 - 1, count24) < count25) {
                        this.countArray[count8++] = count10 - count11 + 32 + (count23 - 1 - count12 + 32 << 6) + (count24 - count13 + 32 << 12);
                     }

                     if (this.computeCount(combatConfigProperty, count10, count23 + 1, count24) < count25) {
                        this.countArray[count8++] = count10 - count11 + 32 + (count23 + 1 - count12 + 32 << 6) + (count24 - count13 + 32 << 12);
                     }

                     if (this.computeCount(combatConfigProperty, count10, count23, count24 - 1) < count25) {
                        this.countArray[count8++] = count10 - count11 + 32 + (count23 - count12 + 32 << 6) + (count24 - 1 - count13 + 32 << 12);
                     }

                     if (this.computeCount(combatConfigProperty, count10, count23, count24 + 1) < count25) {
                        this.countArray[count8++] = count10 - count11 + 32 + (count23 - count12 + 32 << 6) + (count24 + 1 - count13 + 32 << 12);
                     }
                  }
               }
            }
         }

         this.uNKNOWNManager.updateState();
         return true;
      }
   }

   public List resolveList(AxisAlignedBB boundingBox) {
      ArrayList list7 = Lists.newArrayList();
      int count4 = MathHelper.ceiling_double_int(boundingBox.minX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxX + 1.0);
      int count6 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count7 = MathHelper.ceiling_double_int(boundingBox.maxY + 1.0);
      int count8 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count9 = MathHelper.ceiling_double_int(boundingBox.maxZ + 1.0);

      for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(); count4 < count5; count4++) {
         for (int index = count8; index < count9; index++) {
            if (this.checkBlockPos12(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, 64, index))) {
               for (int index2 = count6 - 1; index2 < count7; index2++) {
                  auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index2, index);
                  IBlockState blockState;
                  if (count4 >= -30000000 && count4 < 30000000 && index >= -30000000 && index < 30000000) {
                     blockState = this.getBlockState(auxiliaryBlockPosAdapter);
                  } else {
                     blockState = Blocks.block95.getDefaultState();
                  }

                  blockState.getBlock().applyWorld(this, auxiliaryBlockPosAdapter, blockState, boundingBox, list7, (Entity)null);
               }
            }
         }
      }

      return list7;
   }

   public void applyEntity(Entity entity) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyEntity3(entity);
      }
   }

   public boolean checkIndex(int count4, int count5, int count6, boolean flag) {
      return !this.checkIndex3(count4, count5, count6) ? false : this.checkIndex(count4 >> 4, count6 >> 4, flag);
   }

   public boolean matchesBlockPos2(BlockPos blockPos, boolean flag) {
      return !this.matchesBlockPos4(blockPos) ? false : this.checkIndex(blockPos.getX() >> 4, blockPos.getZ() >> 4, flag);
   }

   public boolean checkIndexByIndex(int count4, int count5, int count6) {
      boolean flag = false;
      if (!this.secondaryFloatArrayEntry.isEnabled()) {
         flag |= this.matchesCombatConfigProperty(CombatConfigProperty.SKY, count4, count5, count6);
      }

      return flag | this.matchesCombatConfigProperty(CombatConfigProperty.BLOCK, count4, count5, count6);
   }

   public float computeFactor(float factor5) {
      return this.secondaryFloatArrayEntry.computeFactor(this.randomSeedManager.getTimestamp(), factor5);
   }

   public void updateState3() {
      this.updateState11();
   }

   @Override
   public boolean isEnabled() {
      return false;
   }

   public boolean matchesBlockPos(BlockPos blockPos, IBlockState blockState) {
      return this.matchesBlockPos(blockPos, blockState, 3);
   }

   public InvalidBiomeIdManager getInvalidBiomeIdManager() {
      return this.secondaryFloatArrayEntry.getInvalidBiomeIdManager();
   }

   public Entity resolveEntity(Class clazz, AxisAlignedBB boundingBox, Entity entity) {
      List list7 = this.resolveList(clazz, boundingBox);
      Entity entity2 = null;
      double offset = Double.MAX_VALUE;

      for (int index = 0; index < list7.size(); index++) {
         Entity entity3 = (Entity)list7.get(index);
         if (entity3 != entity && LivingEntityFilter.predicate4.apply(entity3)) {
            double coordinate = entity.getCoordinate(entity3);
            if (coordinate <= offset) {
               entity2 = entity3;
               offset = coordinate;
            }
         }
      }

      return entity2;
   }

   public void applyBlockPosByBlockPos(BlockPos blockPos, Block block) {
      this.applyBlockPos2(blockPos.getBlockPos(), block);
      this.applyBlockPos2(blockPos.getBlockPos3(), block);
      this.applyBlockPos2(blockPos.down(), block);
      this.applyBlockPos2(blockPos.up(), block);
      this.applyBlockPos2(blockPos.getBlockPosAsBlockPos(), block);
      this.applyBlockPos2(blockPos.getBlockPos2(), block);
   }

   public boolean matchesEntity(Entity entity) {
      int count4 = MathHelper.ceiling_double_int(entity.posX / 16.0);
      int count5 = MathHelper.ceiling_double_int(entity.posZ / 16.0);
      boolean enabled5 = entity.enabled10;
      if (entity instanceof EntityPlayer) {
         enabled5 = true;
      }

      if (!enabled5 && !this.checkIndex(count4, count5, true)) {
         return false;
      } else {
         if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (player.getGameProfile() != null) {
               this.hashMap.put(player.getGameProfile().getName(), player);
            }

            this.itemList7.add(player);
            this.updateState5();
         }

         this.getLoggerEntry2(count4, count5).applyEntityByEntity(entity);
         this.loadedEntityList.add(entity);
         this.applyEntity(entity);
         return true;
      }
   }

   public boolean matchesAxisAlignedBB(AxisAlignedBB boundingBox) {
      return this.matchesAxisAlignedBBByAxisAlignedBB(boundingBox, (Entity)null);
   }

   public int getCount() {
      return 256;
   }

   public boolean matchesBlockPos3(BlockPos blockPos) {
      return this.matchesBlockPosByBlockPos(blockPos, false);
   }

   public EntityPlayer getPlayerEntityByUUID(UUID uuid) {
      for (int index = 0; index < this.itemList7.size(); index++) {
         EntityPlayer player = (EntityPlayer)this.itemList7.get(index);
         if (uuid.equals(player.getEntityUniqueID())) {
            return player;
         }
      }

      return null;
   }

   @Override
   public int computeCount(int count4, int count5, int count6, int count7) {
      int count8 = this.computeCountByCombatConfigProp(CombatConfigProperty.SKY, count4, count5, count6);
      int count9 = this.computeCountByCombatConfigProp(CombatConfigProperty.BLOCK, count4, count5, count6);
      if (count9 < count7) {
         count9 = count7;
      }

      return count8 << 20 | count9 << 4;
   }

   public void addAll(Collection collection) {
      this.list3.addAll(collection);
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(BlockPos blockPos) {
      if (this.checkIndex(blockPos.getX(), blockPos.getY(), blockPos.getZ())) {
         AlertNotificationSound loggerEntry = this.getLoggerEntry(blockPos);

         try {
            return loggerEntry.resolveBiomeIDIsOutOfBoundsManager(blockPos.getX(), blockPos.getZ(), this.secondaryFloatArrayEntry.getInvalidBiomeIdManager());
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Getting biome");
            crashReport.resolveCrashReportCategory("Coordinates of biome request").applyText("Location", new CallableHandler(this, blockPos));
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      } else {
         return this.secondaryFloatArrayEntry
            .getInvalidBiomeIdManager()
            .resolveBiomeIDIsOutOfBoundsManager(blockPos.getX(), blockPos.getZ(), BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35);
      }
   }

   public void updateFloat(float factor5) {
      this.factor4 = factor5;
      this.factor3 = factor5;
   }

   public EntityPlayer getClosestPlayer(double posX, double posX2, double posY, double posZ) {
      double offset = -1.0;
      EntityPlayer player = null;

      for (int index = 0; index < this.itemList7.size(); index++) {
         EntityPlayer player2 = (EntityPlayer)this.itemList7.get(index);
         if (LivingEntityFilter.predicate4.apply(player2)) {
            double doubleItem = player2.getDouble3(posX, posX2, posY);
            if ((posZ < 0.0 || doubleItem < posZ * posZ) && (offset == -1.0 || doubleItem < offset)) {
               offset = doubleItem;
               player = player2;
            }
         }
      }

      return player;
   }

   public boolean matchesDistanceAggregator(DistanceAggregator distanceAggregator, boolean flag) {
      return this.checkIndex(distanceAggregator.count3, distanceAggregator.count6, distanceAggregator.count4, distanceAggregator.count5, distanceAggregator.count2, distanceAggregator.count, flag);
   }

   public List getEntitiesInAABBexcluding(Entity entity, AxisAlignedBB boundingBox, Predicate predicate) {
      ArrayList list7 = Lists.newArrayList();
      int count4 = MathHelper.ceiling_double_int((boundingBox.minX - 2.0) / 16.0);
      int count5 = MathHelper.ceiling_double_int((boundingBox.maxX + 2.0) / 16.0);
      int count6 = MathHelper.ceiling_double_int((boundingBox.minZ - 2.0) / 16.0);
      int count7 = MathHelper.ceiling_double_int((boundingBox.maxZ + 2.0) / 16.0);
      if (((Module)ClientGlobals.moduleManager.exploitFixer).isToggled()) {
         for (int index = 0; count4 <= count5; count4++) {
            for (int index2 = count6; index2 <= count7; index2++) {
               if (this.checkIndex(count4, index2, true)) {
                  this.getLoggerEntry2(count4, index2).applyEntityByEntity(entity, boundingBox, list7, predicate);
               }

               if (++index > 100) {
                  return list7;
               }
            }
         }
      } else {
         for (int index3 = count4; index3 <= count5; index3++) {
            for (int index4 = count6; index4 <= count7; index4++) {
               if (this.checkIndex(index3, index4, true)) {
                  this.getLoggerEntry2(index3, index4).applyEntityByEntity(entity, boundingBox, list7, predicate);
               }
            }
         }
      }

      return list7;
   }

   public long getTimestamp() {
      return this.randomSeedManager.getTimestamp5();
   }

   public int getCount2() {
      return this.secondaryFloatArrayEntry.computeCount(this.randomSeedManager.getTimestamp());
   }

   public void updateState2() {
      this.updateState9();
   }

   public List getCollidingBoundingBoxes(Entity entity, AxisAlignedBB boundingBox) {
      ArrayList list7 = Lists.newArrayList();
      int count4 = MathHelper.ceiling_double_int(boundingBox.minX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxX + 1.0);
      int count6 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count7 = MathHelper.ceiling_double_int(boundingBox.maxY + 1.0);
      int count8 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count9 = MathHelper.ceiling_double_int(boundingBox.maxZ + 1.0);
      WorldInfo internalListEntry2 = this.getInternalListEntry();
      boolean state2 = entity.isState();
      boolean flag = this.matchesWorldInfoByWorldInfo(internalListEntry2, entity);
      IBlockState defaultState = Blocks.stone.getDefaultState();
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index = 8000; count4 < count5; count4++) {
         for (int index2 = count8; index2 < count9; index2++) {
            if (this.checkBlockPos12(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, 64, index2))) {
               for (int index3 = count6 - 1; index3 < count7; index3++) {
                  auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index3, index2);
                  if (state2 && flag) {
                     entity.setState(false);
                  } else if (!state2 && !flag) {
                     entity.setState(true);
                  }

                  IBlockState blockState = defaultState;
                  if (internalListEntry2.checkBlockPos(auxiliaryBlockPosAdapter) || !flag) {
                     blockState = this.getBlockState(auxiliaryBlockPosAdapter);
                  }

                  blockState.getBlock().applyWorld(this, auxiliaryBlockPosAdapter, blockState, boundingBox, list7, entity);
                  if (--index == 0) {
                     return list7;
                  }
               }
            }
         }
      }

      double offset = 0.25;
      List list8 = this.resolveListByEntity(entity, boundingBox.offset(offset, offset, offset));

      for (int index4 = 0; index4 < list8.size(); index4++) {
         if (entity.entity != list8 && entity.ridingEntity != list8) {
            AxisAlignedBB axisAlignedBB = ((Entity)list8.get(index4)).getAxisAlignedBB();
            if (axisAlignedBB != null && axisAlignedBB.intersectsWith(boundingBox)) {
               list7.add(axisAlignedBB);
            }

            AxisAlignedBB boundingBox2 = entity.resolveAxisAlignedBB((Entity)list8.get(index4));
            if (boundingBox2 != null && boundingBox2.intersectsWith(boundingBox)) {
               list7.add(boundingBox2);
            }
         }
      }

      return list7;
   }

   public boolean matchesAxisAlignedBB(AxisAlignedBB boundingBox, Material material) {
      int count4 = MathHelper.ceiling_double_int(boundingBox.minX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxX + 1.0);
      int count6 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count7 = MathHelper.ceiling_double_int(boundingBox.maxY + 1.0);
      int count8 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count9 = MathHelper.ceiling_double_int(boundingBox.maxZ + 1.0);

      for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(); count4 < count5; count4++) {
         for (int index = count6; index < count7; index++) {
            for (int index2 = count8; index2 < count9; index2++) {
               if (this.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index, index2)).getBlock().getMaterial() == material) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean checkIndex(int count4, int count5, boolean flag) {
      return this.commonListener.checkIndex(count4, count5) && (flag || !this.commonListener.resolveAlertNotificationSound(count4, count5).canExecute());
   }

   public void applyBlockPosByBlockPos(BlockPos blockPos) {
      TileEntity tileEntity = this.resolveTileEntity(blockPos);
      if (tileEntity != null && this.enabled2) {
         tileEntity.updateState3();
         this.list6.remove(tileEntity);
      } else {
         if (tileEntity != null) {
            this.list6.remove(tileEntity);
            this.itemList3.remove(tileEntity);
            this.list2.remove(tileEntity);
         }

         this.getLoggerEntry(blockPos).applyBlockPos(blockPos);
      }
   }

   public MovingObjectPosition getMovingObjectPosition(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      return this.getMovingObjectPosition7(posX, posX2, posX3, posX4, posY, posZ, false, false, false);
   }

   public boolean matchesAxisAlignedBBByAxisAlignedBB(AxisAlignedBB boundingBox, Entity entity) {
      List list7 = this.resolveListByEntity((Entity)null, boundingBox);

      for (int index = 0; index < list7.size(); index++) {
         Entity entity2 = (Entity)list7.get(index);
         if (!entity2.isDead && entity2.enabled && entity2 != entity && (entity == null || entity.ridingEntity != entity2 && entity.entity != entity2)) {
            return false;
         }
      }

      return true;
   }

   public int computeCount(CombatConfigProperty combatConfigProperty, int count4, int count5, int count6) {
      if (count5 < 0) {
         count5 = 0;
      }

      if (!this.checkIndex3(count4, count5, count6)) {
         return combatConfigProperty.defaultLightValue;
      } else {
         return !this.checkIndex(count4, count5, count6) ? combatConfigProperty.defaultLightValue : this.getLoggerEntry(count4, count6).computeCount(combatConfigProperty, count4, count5, count6);
      }
   }

   public void applyBlockPos(BlockPos blockPos, Block block) {
      for (Object element : MovementFactorProperty.HORIZONTAL) {
         BlockPos blockPos2 = blockPos.offset((EnumFacing)element);
         if (this.checkBlockPos12(blockPos2)) {
            IBlockState blockState = this.getBlockState(blockPos2);
            if (Blocks.blockPoweredComparator2.matchesBlock2(blockState.getBlock())) {
               blockState.getBlock().processAction(this, blockPos2, blockState, block);
            } else if (blockState.getBlock().isActive2()) {
               blockPos2 = blockPos2.offset((EnumFacing)element);
               blockState = this.getBlockState(blockPos2);
               if (Blocks.blockPoweredComparator2.matchesBlock2(blockState.getBlock())) {
                  blockState.getBlock().processAction(this, blockPos2, blockState, block);
               }
            }
         }
      }
   }

   public void applyEntityPlayerByEntityPlayer(EntityPlayer player, String text, float factor5, float factor6) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyEntityPlayerByEntityPlayer(player, text, player.posX, player.posY, player.posZ, factor5, factor6);
      }
   }

   private boolean matchesBlockPos2(BlockPos blockPos) {
      return blockPos.getY() >= 0 && blockPos.getY() < 256;
   }

   public List resolveList(Class clazz, Predicate predicate) {
      ArrayList list7 = Lists.newArrayList();

      for (Entity entity : (Iterable<Entity>)(Iterable<?>)this.loadedEntityList) {
         if (clazz.isAssignableFrom(entity.getClass()) && predicate.apply(entity)) {
            list7.add(entity);
         }
      }

      return list7;
   }

   public boolean checkIndex(int count4, int count5, int count6, int count7, boolean flag) {
      return this.checkIndex(count4 - count7, count5 - count7, count6 - count7, count4 + count7, count5 + count7, count6 + count7, flag);
   }

   public void applyText(String text, AdapterStringEntry adapterStringEntry) {
      this.dataManager.put(text, adapterStringEntry);
   }

   public BlockPos resolveBlockPos(BlockPos blockPos) {
      AlertNotificationSound loggerEntry = this.getLoggerEntry(blockPos);
      BlockPos blockPos2 = new BlockPos(blockPos.getX(), loggerEntry.getCount() + 16, blockPos.getZ());

      while (blockPos2.getY() >= 0) {
         BlockPos blockPos3 = blockPos2.down();
         Material material = loggerEntry.resolveBlock(blockPos3).getMaterial();
         if (material.isActiveAsFlag() && material != Material.material9) {
            break;
         }

         blockPos2 = blockPos3;
      }

      return blockPos2;
   }

   public boolean matchesBlockPosByBlockPos(BlockPos blockPos, boolean flag) {
      if (this.resolveBiomeIDIsOutOfBoundsManager(blockPos).computeFactor(blockPos) > 0.15F) {
         return false;
      } else {
         if (blockPos.getY() >= 0 && blockPos.getY() < 256 && this.computeCount(CombatConfigProperty.BLOCK, blockPos.getX(), blockPos.getY(), blockPos.getZ()) < 10) {
            IBlockState blockState = this.getBlockState(blockPos);
            Block block = blockState.getBlock();
            if ((block == Blocks.blockLava2 || block == Blocks.blockFlowingLava) && (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) == 0) {
               if (!flag) {
                  return true;
               }

               if (!this.matchesBlockPos10(blockPos.getBlockPos())
                  || !this.matchesBlockPos10(blockPos.getBlockPos3())
                  || !this.matchesBlockPos10(blockPos.getBlockPosAsBlockPos())
                  || !this.matchesBlockPos10(blockPos.getBlockPos2())) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public void applyText(double posX, double posY, double posZ, String text, float factor5, float factor6) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyText(text, posX, posY, posZ, factor5, factor6);
      }
   }

   public void applyBlockPos(BlockPos blockPos, Block block, int count4, int count5) {
   }

   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player, BlockPos blockPos, EnumFacing enumFacing) {
      blockPos = blockPos.offset(enumFacing);
      if (this.getBlockState(blockPos).getBlock() == Blocks.blockFire) {
         this.applyEntityPlayerByEntityPlayer(player, 1004, blockPos, 0);
         this.matchesBlockPos8(blockPos);
         return true;
      } else {
         return false;
      }
   }

   public GameSettingsPeacefulType getGameSettingsPeacefulType() {
      return this.getRandomSeedManager().getGameSettingsPeacefulType2();
   }

   public void updateState8() {
      int count4 = this.computeCount(1.0F);
      if (count4 != this.indexOffset) {
         this.indexOffset = count4;
      }
   }

   public abstract IChunkProvider getIChunkProvider();

   public List resolveListByEntity(Entity entity, AxisAlignedBB boundingBox) {
      return this.getEntitiesInAABBexcluding(entity, boundingBox, LivingEntityFilter.predicate4);
   }

   public boolean matchesBlockPos(BlockPos blockPos, IBlockState blockState, int count4) {
      if (!this.matchesBlockPos4(blockPos)) {
         return false;
      } else if (!this.state && this.randomSeedManager.getInfoManager() == InfoManager.infoManager4) {
         return false;
      } else {
         AlertNotificationSound loggerEntry = this.getLoggerEntry(blockPos);
         Block block = blockState.getBlock();
         IBlockState blockState2 = loggerEntry.resolveIBlockState(blockPos, blockState);
         if (blockState2 == null) {
            return false;
         } else {
            Block block2 = blockState2.getBlock();
            if (block.getIndexOffset() != block2.getIndexOffset() || block.getIndexOffset1() != block2.getIndexOffset1()) {
               this.uNKNOWNManager.applyTextByText("checkLight");
               this.checkIndexByIndex(blockPos.getX(), blockPos.getY(), blockPos.getZ());
               this.uNKNOWNManager.updateState();
            }

            if ((count4 & 2) != 0 && (!this.state || (count4 & 4) == 0) && loggerEntry.isActive()) {
               this.applyBlockPos2(blockPos);
            }

            if (!this.state && (count4 & 1) != 0) {
               this.applyBlockPos3(blockPos, blockState2.getBlock());
               if (block.processAction16()) {
                  this.applyBlockPos(blockPos, block);
               }
            }

            return true;
         }
      }
   }

   public boolean checkBlockPos12(BlockPos blockPos) {
      return this.matchesBlockPos2(blockPos, true);
   }

   public void applyBlockPos(BlockPos blockPos) {
      this.randomSeedManager.applyBlockPos(blockPos);
   }

   public List resolveList(Class clazz, AxisAlignedBB boundingBox, Predicate predicate) {
      int count4 = MathHelper.ceiling_double_int((boundingBox.minX - 2.0) / 16.0);
      int count5 = MathHelper.ceiling_double_int((boundingBox.maxX + 2.0) / 16.0);
      int count6 = MathHelper.ceiling_double_int((boundingBox.minZ - 2.0) / 16.0);
      int count7 = MathHelper.ceiling_double_int((boundingBox.maxZ + 2.0) / 16.0);

      ArrayList list7;
      for (list7 = Lists.newArrayList(); count4 <= count5; count4++) {
         for (int index = count6; index <= count7; index++) {
            if (this.checkIndex(count4, index, true)) {
               this.getLoggerEntry2(count4, index).applyClass(clazz, boundingBox, list7, predicate);
            }
         }
      }

      return list7;
   }

   public String getTextAsText() {
      return this.commonListener.getText();
   }

   public boolean checkIndex(int count4, int count5) {
      BlockPos blockPos = this.getBlockPos();
      int count6 = count4 * 16 + 8 - blockPos.getX();
      int count7 = count5 * 16 + 8 - blockPos.getZ();
      return count6 >= -128 && count6 <= 128 && count7 >= -128 && count7 <= 128;
   }

   public void applyNBTTagCompound(double posX, double posX2, double posX3, double posX4, double posY, double posZ, NBTTagCompound tagCompound) {
   }

   public int computeCount(BlockPos blockPos) {
      int count4 = 0;

      for (EnumFacing enumFacing : EnumFacing.getEntries()) {
         int count5 = this.computeCountByBlockPos(blockPos.offset(enumFacing), enumFacing);
         if (count5 >= 15) {
            return 15;
         }

         if (count5 > count4) {
            count4 = count5;
         }
      }

      return count4;
   }

   public BlockBreakState resolveBlockBreakStateByEntity(Entity entity, double posX, double posY, double posZ, float factor5, boolean flag) {
      return this.resolveBlockBreakState(entity, posX, posY, posZ, factor5, false, flag);
   }

   public EntityPlayer resolveEntityPlayerByText(String text) {
      for (int index = 0; index < this.itemList7.size(); index++) {
         EntityPlayer player = (EntityPlayer)this.itemList7.get(index);
         if (text.equals(player.getText())) {
            return player;
         }
      }

      return null;
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public GoldenOre resolveGoldenOre(BlockPos blockPos) {
      long value = 0L;
      float factor5 = 0.0F;
      if (this.checkBlockPos12(blockPos)) {
         factor5 = this.getFactor();
         value = this.getLoggerEntry(blockPos).getTimestamp();
      }

      return new GoldenOre(this.getGameSettingsPeacefulType(), this.getTimestampAsTime(), value, factor5);
   }

   public boolean isActive() {
      return (double)this.getFloat6(1.0F) > 0.2;
   }

   public float computeFactor(int count4, int count5, int count6) {
      return this.secondaryFloatArrayEntry.getFactorArray()[this.computeCountByIndex(count4, count5, count6)];
   }

   public void updateState6() throws ExceptionAdapter {
      this.managedListener.updateState2();
   }

   public void applyCombatConfigProperty(CombatConfigProperty combatConfigProperty, int count4, int count5, int count6, int count7) {
      if (this.checkIndex3(count4, count5, count6) && this.checkIndex(count4, count5, count6)) {
         this.getLoggerEntry(count4, count6).applyCombatConfigProperty(combatConfigProperty, count4, count5, count6, count7);
         this.applyIndex(count4, count5, count6);
      }
   }

   public RandomSeedManager getRandomSeedManager() {
      return this.randomSeedManager;
   }

   public int computeCountByBlockPos(BlockPos blockPos, EnumFacing enumFacing) {
      IBlockState blockState = this.getBlockState(blockPos);
      Block block = blockState.getBlock();
      return block.isActive2() ? this.computeCountByBlockPos(blockPos) : block.processAction(this, blockPos, blockState, enumFacing);
   }

   public void applyBlockPos(BlockPos blockPos, BlockPos blockPos2) {
      this.applyIndex(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockPos2.getX(), blockPos2.getY(), blockPos2.getZ());
   }

   public boolean matchesMaterial(double posX, double posX2, double posX3, double posX4, double posY, double posZ, Material material) {
      int count4 = MathHelper.ceiling_double_int(posX);
      int count5 = MathHelper.ceiling_double_int(posX4 + 1.0);
      int count6 = MathHelper.ceiling_double_int(posX2);
      int count7 = MathHelper.ceiling_double_int(posY + 1.0);
      int count8 = MathHelper.ceiling_double_int(posX3);
      int count9 = MathHelper.ceiling_double_int(posZ + 1.0);

      for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(); count4 < count5; count4++) {
         for (int index = count6; index < count7; index++) {
            for (int index2 = count8; index2 < count9; index2++) {
               if (this.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index, index2)).getBlock().getMaterial() == material) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public void applyBlockPosByBlockPos(BlockPos blockPos, Block block, int count4, int count5) {
      block.processAction(this, blockPos, this.getBlockState(blockPos), count4, count5);
   }

   public void updateFloat2(float factor5) {
      this.factor = factor5;
      this.factor2 = factor5;
   }

   public List resolveList(Class clazz, AxisAlignedBB boundingBox) {
      return this.resolveList(clazz, boundingBox, LivingEntityFilter.predicate4);
   }

   public boolean matchesBlockPos9(BlockPos blockPos) {
      if (blockPos.getY() >= this.getIndexOffset1()) {
         return this.checkIndex2(blockPos.getX(), blockPos.getY(), blockPos.getZ());
      } else {
         BlockPos blockPos2 = new BlockPos(blockPos.getX(), this.getIndexOffset1(), blockPos.getZ());
         if (!this.checkIndex2(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ())) {
            return false;
         } else {
            for (BlockPos blockPos3 = blockPos2.down(); blockPos3.getY() > blockPos.getY(); blockPos3 = blockPos3.down()) {
               Block block = this.getBlockState(blockPos3).getBlock();
               if (block.getIndexOffset() > 0 && !block.getMaterial().isActive2()) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public World getWorld() {
      return this;
   }

   public AlertNotificationSound getLoggerEntry(BlockPos blockPos) {
      return this.getLoggerEntry2(blockPos.getX() >> 4, blockPos.getZ() >> 4);
   }

   public MovingObjectPosition getMovingObjectPosition2(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      return this.getMovingObjectPosition4(posX, posX2, posX3, posX4, posY, posZ, false, false, false);
   }

   public boolean matchesBlockPos(BlockPos blockPos, boolean flag) {
      if (this.resolveBiomeIDIsOutOfBoundsManager(blockPos).computeFactor(blockPos) > 0.15F) {
         return false;
      } else {
         return !flag
            ? true
            : blockPos.getY() >= 0
               && blockPos.getY() < 256
               && this.computeCount(CombatConfigProperty.BLOCK, blockPos.getX(), blockPos.getY(), blockPos.getZ()) < 10
               && this.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry
               && Blocks.block74.processAction7(this, blockPos);
      }
   }

   public List resolveList(DistanceAggregator distanceAggregator, boolean flag) {
      return null;
   }

   public boolean isActive2() {
      return this.indexOffset < 4;
   }

   public void applyIndex(int count4, int count5, int count6, int count7) {
      if (count6 > count7) {
         int count8 = count7;
         count7 = count6;
         count6 = count8;
      }

      if (!this.secondaryFloatArrayEntry.isEnabled()) {
         for (int index = count6; index <= count7; index++) {
            this.matchesCombatConfigProperty(CombatConfigProperty.SKY, count4, index, count5);
         }
      }

      this.applyIndex(count4, count6, count5, count4, count7, count5);
   }

   public MovingObjectPosition resolveMovingObjectPosition(double posX, double posX2, double posX3, double posX4, double posY, double posZ, boolean flag) {
      return this.getMovingObjectPosition4(posX, posX2, posX3, posX4, posY, posZ, flag, false, false);
   }

   public void applyBlockPos(BlockPos blockPos, Block block, EnumFacing enumFacing) {
      if (enumFacing != EnumFacing.WEST) {
         this.applyBlockPos2(blockPos.getBlockPos(), block);
      }

      if (enumFacing != EnumFacing.EAST) {
         this.applyBlockPos2(blockPos.getBlockPos3(), block);
      }

      if (enumFacing != EnumFacing.DOWN) {
         this.applyBlockPos2(blockPos.down(), block);
      }

      if (enumFacing != EnumFacing.UP) {
         this.applyBlockPos2(blockPos.up(), block);
      }

      if (enumFacing != EnumFacing.NORTH) {
         this.applyBlockPos2(blockPos.getBlockPosAsBlockPos(), block);
      }

      if (enumFacing != EnumFacing.SOUTH) {
         this.applyBlockPos2(blockPos.getBlockPos2(), block);
      }
   }

   public void applyBlockPos2(BlockPos blockPos) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyBlockPos(blockPos);
      }
   }

   public int computeCount(int count4, int count5) {
      return this.getLoggerEntry(count4, count5).getInt6(count4, count5);
   }

   public void applyCollectionByCollection(Collection collection) {
      if (this.enabled2) {
         this.list6.addAll(collection);
      } else {
         for (TileEntity tileEntity : (Iterable<TileEntity>)(Iterable<?>)collection) {
            this.itemList3.add(tileEntity);
            if (tileEntity instanceof PreUpdateConsumer) {
               this.list2.add(tileEntity);
            }
         }
      }
   }

   public MovingObjectPosition getMovingObjectPosition4(
      double posX, double posX2, double posX3, double posX4, double posY, double posZ, boolean flag, boolean flag2, boolean flag3
   ) {
      if (Double.isNaN(posX) || Double.isNaN(posX2) || Double.isNaN(posX3)) {
         return null;
      } else if (!Double.isNaN(posX4) && !Double.isNaN(posY) && !Double.isNaN(posZ)) {
         int count4 = MathHelper.ceiling_double_int(posX4);
         int count5 = MathHelper.ceiling_double_int(posY);
         int count6 = MathHelper.ceiling_double_int(posZ);
         int count7 = MathHelper.ceiling_double_int(posX);
         int count8 = MathHelper.ceiling_double_int(posX2);
         int count9 = MathHelper.ceiling_double_int(posX3);
         IBlockState iBlockState = this.getIBlockState(count7, count8, count9);
         Block block = iBlockState.getBlock();
         if ((!flag2 || block.processAction(this, iBlockState, count7, count8, count9) != null) && block.processAction(iBlockState, flag)) {
            MovingObjectPosition hitResult = block.resolveMovingObjectPositionByWorld(this, count7, count8, count9, posX, posX2, posY, posZ, posX3, posX4);
            if (hitResult != null) {
               return hitResult;
            }
         }

         MovingObjectPosition hitResult2 = null;
         int count10 = 200;

         while (count10-- >= 0) {
            if (Double.isNaN(posX) || Double.isNaN(posX2) || Double.isNaN(posX3)) {
               return null;
            }

            if (count7 == count4 && count8 == count5 && count9 == count6) {
               return flag3 ? hitResult2 : null;
            }

            boolean flag4 = true;
            boolean flag5 = true;
            boolean flag6 = true;
            double offset = 999.0;
            double offset2 = 999.0;
            double offset3 = 999.0;
            if (count4 > count7) {
               offset = (double)count7 + 1.0;
            } else if (count4 < count7) {
               offset = (double)count7 + 0.0;
            } else {
               flag4 = false;
            }

            if (count5 > count8) {
               offset2 = (double)count8 + 1.0;
            } else if (count5 < count8) {
               offset2 = (double)count8 + 0.0;
            } else {
               flag5 = false;
            }

            if (count6 > count9) {
               offset3 = (double)count9 + 1.0;
            } else if (count6 < count9) {
               offset3 = (double)count9 + 0.0;
            } else {
               flag6 = false;
            }

            double offset4 = 999.0;
            double offset5 = 999.0;
            double offset6 = 999.0;
            double offset7 = posX4 - posX;
            double offset8 = posY - posX2;
            double offset9 = posZ - posX3;
            if (flag4) {
               offset4 = (offset - posX) / offset7;
            }

            if (flag5) {
               offset5 = (offset2 - posX2) / offset8;
            }

            if (flag6) {
               offset6 = (offset3 - posX3) / offset9;
            }

            if (offset4 == -0.0) {
               offset4 = -1.0E-4;
            }

            if (offset5 == -0.0) {
               offset5 = -1.0E-4;
            }

            if (offset6 == -0.0) {
               offset6 = -1.0E-4;
            }

            EnumFacing enumFacing;
            if (offset4 < offset5 && offset4 < offset6) {
               enumFacing = count4 > count7 ? EnumFacing.WEST : EnumFacing.EAST;
               posX = offset;
               posX2 += offset8 * offset4;
               posX3 += offset9 * offset4;
            } else if (offset5 < offset6) {
               enumFacing = count5 > count8 ? EnumFacing.DOWN : EnumFacing.UP;
               posX += offset7 * offset5;
               posX2 = offset2;
               posX3 += offset9 * offset5;
            } else {
               enumFacing = count6 > count9 ? EnumFacing.NORTH : EnumFacing.SOUTH;
               posX += offset7 * offset6;
               posX2 += offset8 * offset6;
               posX3 = offset3;
            }

            count7 = MathHelper.ceiling_double_int(posX) - (enumFacing == EnumFacing.EAST ? 1 : 0);
            count8 = MathHelper.ceiling_double_int(posX2) - (enumFacing == EnumFacing.UP ? 1 : 0);
            count9 = MathHelper.ceiling_double_int(posX3) - (enumFacing == EnumFacing.SOUTH ? 1 : 0);
            IBlockState iBlockState2 = this.getIBlockState(count7, count8, count9);
            Block block2 = iBlockState2.getBlock();
            if (!flag2 || block2.processAction(this, iBlockState2, count7, count8, count9) != null) {
               if (block2.processAction(iBlockState2, flag)) {
                  MovingObjectPosition hitResult3 = block2.resolveMovingObjectPositionByWorld(this, count7, count8, count9, posX, posX2, posY, posZ, posX3, posX4);
                  if (hitResult3 != null) {
                     return hitResult3;
                  }
               } else {
                  hitResult2 = new MovingObjectPosition(MovingObjectType.MISS, new Vec3(posX, posX2, posX3), enumFacing, new BlockPos(count7, count8, count9));
               }
            }
         }

         return flag3 ? hitResult2 : null;
      } else {
         return null;
      }
   }

   @Override
   public InfoManager getInfoManager() {
      return this.randomSeedManager.getInfoManager();
   }

   public int computeCountByBlockPos(BlockPos blockPos) {
      int count4 = Math.max(0, this.computeCount(blockPos.down(), EnumFacing.DOWN));
      if (count4 >= 15) {
         return count4;
      } else {
         count4 = Math.max(count4, this.computeCount(blockPos.up(), EnumFacing.UP));
         if (count4 >= 15) {
            return count4;
         } else {
            count4 = Math.max(count4, this.computeCount(blockPos.getBlockPosAsBlockPos(), EnumFacing.NORTH));
            if (count4 >= 15) {
               return count4;
            } else {
               count4 = Math.max(count4, this.computeCount(blockPos.getBlockPos2(), EnumFacing.SOUTH));
               if (count4 >= 15) {
                  return count4;
               } else {
                  count4 = Math.max(count4, this.computeCount(blockPos.getBlockPos(), EnumFacing.WEST));
                  if (count4 >= 15) {
                     return count4;
                  } else {
                     count4 = Math.max(count4, this.computeCount(blockPos.getBlockPos3(), EnumFacing.EAST));
                     return count4 >= 15 ? count4 : count4;
                  }
               }
            }
         }
      }
   }

   public AdapterStringEntry resolveAdapterStringEntry(Class clazz, String text) {
      return this.dataManager.get(clazz, text);
   }

   public List getList2(Entity entity, AxisAlignedBB boundingBox) {
      ArrayList list7 = Lists.newArrayList();
      int count4 = MathHelper.ceiling_double_int(boundingBox.minX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxX + 1.0);
      int count6 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count7 = MathHelper.ceiling_double_int(boundingBox.maxY + 1.0);
      int count8 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count9 = MathHelper.ceiling_double_int(boundingBox.maxZ + 1.0);
      WorldInfo internalListEntry2 = this.getInternalListEntry();
      boolean state2 = entity.isState();
      boolean flag = this.matchesWorldInfoByWorldInfo(internalListEntry2, entity);
      IBlockState defaultState = Blocks.stone.getDefaultState();

      for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(); count4 < count5; count4++) {
         for (int index = count8; index < count9; index++) {
            if (this.checkBlockPos12(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, 64, index))) {
               for (int index2 = count6 - 1; index2 < count7; index2++) {
                  auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index2, index);
                  if (state2 && flag) {
                     entity.setState(false);
                  } else if (!state2 && !flag) {
                     entity.setState(true);
                  }

                  IBlockState blockState = defaultState;
                  if (internalListEntry2.checkBlockPos(auxiliaryBlockPosAdapter) || !flag) {
                     blockState = this.getBlockState(auxiliaryBlockPosAdapter);
                  }

                  blockState.getBlock().applyWorld(this, auxiliaryBlockPosAdapter, blockState, boundingBox, list7, entity);
               }
            }
         }
      }

      double offset = 0.25;
      List list8 = this.resolveListByEntity(entity, boundingBox.offset(offset, offset, offset));

      for (int index3 = 0; index3 < list8.size(); index3++) {
         if (entity.entity != list8 && entity.ridingEntity != list8) {
            AxisAlignedBB axisAlignedBB = ((Entity)list8.get(index3)).getAxisAlignedBB();
            if (axisAlignedBB != null && axisAlignedBB.intersectsWith(boundingBox)) {
               list7.add(axisAlignedBB);
            }

            AxisAlignedBB boundingBox2 = entity.resolveAxisAlignedBB((Entity)list8.get(index3));
            if (boundingBox2 != null && boundingBox2.intersectsWith(boundingBox)) {
               list7.add(boundingBox2);
            }
         }
      }

      return list7;
   }

   public void applyBlockPos(BlockPos blockPos, Block block, int count4) {
   }

   public Block resolveBlock(BlockPos blockPos) {
      BlockPos blockPos2 = new BlockPos(blockPos.getX(), this.getIndexOffset1(), blockPos.getZ());

      while (!this.isAirBlock(blockPos2.up())) {
         blockPos2 = blockPos2.up();
      }

      return this.getBlockState(blockPos2).getBlock();
   }

   public void applyBlockPosByBlockPos(int count4, BlockPos blockPos, int count5) {
      this.applyEntityPlayerByEntityPlayer((EntityPlayer)null, count4, blockPos, count5);
   }

   public void updateState10() {
      this.applyBlockPos(new BlockPos(8, 64, 8));
   }

   public float computeFactor2(float factor5) {
      return this.computeFactor(factor5) * (float) Math.PI * 2.0F;
   }

   public void applyIndex(int count4, int count5, int count6, int count7, int count8, int count9) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyIndex(count4, count5, count6, count7, count8, count9);
      }
   }

   public boolean matchesBlockPos(BlockPos blockPos, BlockPos blockPos2) {
      return this.matchesBlockPos(blockPos, blockPos2, true);
   }

   public boolean isState3() {
      return this.state3;
   }

   public int computeCount(int count4, int count5, int count6, boolean flag) {
      if (count4 < -30000000 || count6 < -30000000 || count4 >= 30000000 || count6 >= 30000000) {
         return 15;
      } else if (flag && this.getIBlockState(count4, count5, count6).getBlock().isState25()) {
         int count7 = this.computeCount(count4, count5 + 1, count6, false);
         int count8 = this.computeCount(count4 + 1, count5, count6, false);
         int count9 = this.computeCount(count4 - 1, count5, count6, false);
         int count10 = this.computeCount(count4, count5, count6 + 1, false);
         int count11 = this.computeCount(count4, count5, count6 - 1, false);
         if (count8 > count7) {
            count7 = count8;
         }

         if (count9 > count7) {
            count7 = count9;
         }

         if (count10 > count7) {
            count7 = count10;
         }

         if (count11 > count7) {
            count7 = count11;
         }

         return count7;
      } else if (count5 < 0) {
         return 0;
      } else {
         if (count5 >= 256) {
            count5 = 255;
         }

         return this.getLoggerEntry(count4, count6).computeCount(count4, count5, count6, this.indexOffset);
      }
   }

   public void setIndexOffset2(int indexOffset3) {
      this.indexOffset2 = indexOffset3;
   }

   public boolean matchesAxisAlignedBB3(AxisAlignedBB boundingBox) {
      int count4 = MathHelper.ceiling_double_int(boundingBox.minX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxX);
      int count6 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count7 = MathHelper.ceiling_double_int(boundingBox.maxY);
      int count8 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count9 = MathHelper.ceiling_double_int(boundingBox.maxZ);

      for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(); count4 <= count5; count4++) {
         for (int index = count6; index <= count7; index++) {
            for (int index2 = count8; index2 <= count9; index2++) {
               if (this.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index, index2)).getBlock().getMaterial() != Material.coreItemEntry) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public void applyTime(long value) {
      value = 6000L;
      this.randomSeedManager.applyTime(value);
   }

   public void applyEntity9(Entity entity) {
      int count4 = MathHelper.ceiling_double_int(entity.posX / 16.0);
      int count5 = MathHelper.ceiling_double_int(entity.posZ / 16.0);

      for (int index = count4 - 2; index <= count4 + 2; index++) {
         for (int index2 = count5 - 2; index2 <= count5 + 2; index2++) {
            this.getLoggerEntry2(index, index2);
         }
      }

      if (!this.loadedEntityList.contains(entity)) {
         this.loadedEntityList.add(entity);
      }
   }

   public Vec3 resolveVecByFloat(float factor5) {
      float factor6 = MathHelper.cos(this.computeFactor(factor5) * (float) Math.PI * 2.0F) * 2.0F + 0.5F;
      factor6 = MathHelper.clamp_float(factor6, 0.0F, 1.0F);
      float factor7 = (float)(this.timestamp >> 16 & 255L) / 255.0F;
      float factor8 = (float)(this.timestamp >> 8 & 255L) / 255.0F;
      float factor9 = (float)(this.timestamp & 255L) / 255.0F;
      float floatItem = this.getFloat6(factor5);
      if (floatItem > 0.0F) {
         float factor10 = (factor7 * 0.3F + factor8 * 0.59F + factor9 * 0.11F) * 0.6F;
         floatItem = 1.0F - floatItem * 0.95F;
         factor7 = factor7 * floatItem + factor10 * (1.0F - floatItem);
         factor8 = factor8 * floatItem + factor10 * (1.0F - floatItem);
         factor9 = factor9 * floatItem + factor10 * (1.0F - floatItem);
      }

      factor7 *= factor6 * 0.9F + 0.1F;
      factor8 *= factor6 * 0.9F + 0.1F;
      factor9 *= factor6 * 0.85F + 0.15F;
      float floatItem2 = this.getFloat7(factor5);
      if (floatItem2 > 0.0F) {
         floatItem = (factor7 * 0.3F + factor8 * 0.59F + factor9 * 0.11F) * 0.2F;
         factor6 = 1.0F - floatItem2 * 0.95F;
         factor7 = factor7 * factor6 + floatItem * (1.0F - factor6);
         factor8 = factor8 * factor6 + floatItem * (1.0F - factor6);
         factor9 = factor9 * factor6 + floatItem * (1.0F - factor6);
      }

      return new Vec3((double)factor7, (double)factor8, (double)factor9);
   }

   public void applyIndex(int count4, int count5, int count6) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyIndex(count4, count5, count6);
      }
   }

   public List getLoadedEntityList() {
      return this.loadedEntityList;
   }

   public float getFactor() {
      return SecondaryFloatArrayEntry.factorArray3[this.secondaryFloatArrayEntry.computeCount(this.randomSeedManager.getTimestamp())];
   }

   public boolean contains(BlockPos blockPos, Block block) {
      return false;
   }

   public AlertNotificationSound getLoggerEntry2(int count4, int count5) {
      return this.commonListener.resolveAlertNotificationSound(count4, count5);
   }

   @Override
   public IBlockState getIBlockState(int count4, int count5, int count6) {
      return this.getLoggerEntry(count4, count6).resolveIBlockState(count4, count5, count6);
   }

   private boolean matchesBlockPos10(BlockPos blockPos) {
      return this.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry1;
   }

   public ThePlayerNameManager getThePlayerNameManager() {
      return this.thePlayerNameManager;
   }

   public void updateState11() {
      this.objectOpenHashSet.clear();
      this.uNKNOWNManager.applyTextByText("buildList");
      InterpolationCollector interpolationCollector = new InterpolationCollector(0, 0);

      for (EntityPlayer player : (Iterable<EntityPlayer>)(Iterable<?>)this.itemList7) {
         int count4 = MathHelper.ceiling_double_int(player.posX / 16.0);
         int count5 = MathHelper.ceiling_double_int(player.posZ / 16.0);
         int countAsIndex = this.getCountAsIndex();

         for (int index = -countAsIndex; index <= countAsIndex; index++) {
            for (int index2 = -countAsIndex; index2 <= countAsIndex; index2++) {
               interpolationCollector.count3 = index + count4;
               interpolationCollector.count2 = index2 + count5;
               interpolationCollector.count = 0;
               if (!this.objectOpenHashSet.contains(interpolationCollector)) {
                  this.objectOpenHashSet.add(interpolationCollector);
                  interpolationCollector = new InterpolationCollector(0, 0);
               }
            }
         }
      }

      this.uNKNOWNManager.updateState();
      if (this.count3 > 0) {
         this.count3--;
      }

      this.uNKNOWNManager.applyTextByText("playerCheckLight");
      if (!this.itemList7.isEmpty()) {
         int count6 = this.random.nextInt(this.itemList7.size());
         EntityPlayer player2 = (EntityPlayer)this.itemList7.get(count6);
         int count7 = MathHelper.ceiling_double_int(player2.posX) + this.random.nextInt(11) - 5;
         count6 = MathHelper.ceiling_double_int(player2.posY) + this.random.nextInt(11) - 5;
         int count8 = MathHelper.ceiling_double_int(player2.posZ) + this.random.nextInt(11) - 5;
         this.checkIndexByIndex(count7, count6, count8);
      }

      this.uNKNOWNManager.updateState();
   }

   @Override
   public boolean checkCondition(int count4, int count5, int count6) {
      return this.getIBlockState(count4, count5, count6).getBlock().getMaterial() == Material.coreItemEntry;
   }

   public float getFloat6(float factor5) {
      return ((Module)Client.getClient().getModuleManager().ambiance).isToggled()
         ? (float)Client.getClient().getModuleManager().ambiance.settingRain.getValue()
         : this.factor4 + (this.factor3 - this.factor4) * factor5;
   }

   public boolean matchesBlockPos4(BlockPos blockPos, boolean flag) {
      if (!this.matchesBlockPos4(blockPos)) {
         return flag;
      } else if (this.commonListener.resolveAlertNotificationSound(blockPos).canExecute()) {
         return flag;
      } else {
         Block block = this.getBlockState(blockPos).getBlock();
         return block.getMaterial().isActive3() && block.processAction14();
      }
   }

   public int getCount3() {
      return this.secondaryFloatArrayEntry.isEnabled() ? 128 : 256;
   }

   public void applyBlockPos3(BlockPos blockPos, Block block) {
      if (this.randomSeedManager.getInfoManager() != InfoManager.infoManager4) {
         this.applyBlockPosByBlockPos(blockPos, block);
      }
   }

   public void applyCollection(Collection collection) {
      this.loadedEntityList.addAll(collection);

      for (Entity entity : (Iterable<Entity>)(Iterable<?>)collection) {
         this.applyEntity(entity);
      }
   }

   public void applyBlockPosByBlockPos(BlockPos blockPos, TileEntity tileEntity) {
      if (this.checkBlockPos12(blockPos)) {
         this.getLoggerEntry(blockPos).updateState5();
      }
   }

   public MovingObjectPosition getMovingObjectPosition5(
      double posX, double posX2, double posX3, double posX4, double posY, double posZ, boolean flag, boolean flag2, boolean flag3
   ) {
      return this.resolveMovingObjectPosition(posX, posX2, posX3, posX4, posY, posZ, flag, flag2, flag3, false);
   }

   public List resolveList(AlertNotificationSound alertNotificationSound, boolean flag) {
      return null;
   }

   public int getIndexOffset1() {
      return this.indexOffset1;
   }

   public int computeCountByIndex(int count4, int count5) {
      if (count4 >= -30000000 && count5 >= -30000000 && count4 < 30000000 && count5 < 30000000) {
         return !this.checkIndex(count4 >> 4, count5 >> 4, true) ? 0 : this.getLoggerEntry2(count4 >> 4, count5 >> 4).getCount2();
      } else {
         return this.getIndexOffset1() + 1;
      }
   }

   @Override
   public IBlockState getIBlockState2(double posX, double posY, double posZ) {
      int count4 = MathHelper.ceiling_double_int(posX);
      int count5 = MathHelper.ceiling_double_int(posY);
      int count6 = MathHelper.ceiling_double_int(posZ);
      return this.getLoggerEntry(count4, count6).resolveIBlockState(count4, count5, count6);
   }

   public boolean checkIndex(int count4, int count5, int count6, int count7) {
      return this.checkIndex(count4, count5, count6, count7, true);
   }

   private int computeCount(Entity entity, int count4) {
      if (entity.ridingEntity != null) {
         if (!entity.ridingEntity.isDead && entity.ridingEntity.entity == entity) {
            return count4;
         }

         entity.ridingEntity.entity = null;
         entity.ridingEntity = null;
      }

      this.uNKNOWNManager.applyTextByText("tick");
      if (!entity.isDead) {
         try {
            this.removeEntity(entity);
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Ticking entity");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Entity being ticked");
            entity.applyCrashReportCategory(crashReportCategory);
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      }

      this.uNKNOWNManager.updateState();
      this.uNKNOWNManager.applyTextByText("remove");
      if (entity.isDead) {
         int count5 = entity.count7;
         int count6 = entity.count8;
         if (entity.enabled9 && this.checkIndex(count5, count6, true)) {
            this.getLoggerEntry2(count5, count6).applyEntity3(entity);
         }

         this.loadedEntityList.remove(count4--);
         this.applyEntityByEntity(entity);
      }

      this.uNKNOWNManager.updateState();
      return count4;
   }

   public EntityPlayer resolveEntityPlayerByEntity(Entity entity, double offset) {
      return this.getClosestPlayer(entity.posX, entity.posY, entity.posZ, offset);
   }

   public double getDouble() {
      return this.randomSeedManager.getInfoManager() == InfoManager.infoManager2 ? 0.0 : 63.0;
   }

   public void applyFlag(boolean flag, boolean flag2) {
      this.enabled4 = flag;
      this.enabled = flag2;
   }

   private int computeCount(int count4, int count5, int count6, CombatConfigProperty combatConfigProperty) {
      if (combatConfigProperty == CombatConfigProperty.SKY && this.checkIndex2(count4, count5, count6)) {
         return 15;
      } else {
         Block block = this.getIBlockState(count4, count5, count6).getBlock();
         int count7 = combatConfigProperty == CombatConfigProperty.SKY ? 0 : block.getIndexOffset1();
         int indexOffset3 = block.getIndexOffset();
         if (indexOffset3 >= 15 && block.getIndexOffset1() > 0) {
            indexOffset3 = 1;
         }

         if (indexOffset3 < 1) {
            indexOffset3 = 1;
         }

         if (indexOffset3 >= 15) {
            return 0;
         } else if (count7 >= 14) {
            return count7;
         } else {
            for (EnumFacing enumFacing : EnumFacing.getEntries()) {
               int count8 = this.computeCount(combatConfigProperty, count4 + enumFacing.getInt2(), count5 + enumFacing.getInt3(), count6 + enumFacing.getInt()) - indexOffset3;
               if (count8 > count7) {
                  count7 = count8;
               }

               if (count7 >= 14) {
                  return count7;
               }
            }

            return count7;
         }
      }
   }

   public WorldInfo getInternalListEntry() {
      return this.internalListEntry;
   }

   public DataManager getDataManager() {
      return this.dataManager;
   }

   public int computeCount(int count4, int count5, int count6) {
      if (count5 < 0) {
         return 0;
      } else {
         if (count5 >= 256) {
            count5 = 255;
         }

         return this.getLoggerEntry(count4, count6).computeCount(count4, count5, count6, 0);
      }
   }

   public int computeCount(Class clazz) {
      int count4 = 0;

      for (Entity entity : (Iterable<Entity>)(Iterable<?>)this.loadedEntityList) {
         if ((!(entity instanceof EntityMob) || !((EntityMob)entity).isEnabled21()) && clazz.isAssignableFrom(entity.getClass())) {
            count4++;
         }
      }

      return count4;
   }

   public BlockPos resolveBlockPos2(BlockPos blockPos) {
      int count4;
      if (blockPos.getX() >= -30000000 && blockPos.getZ() >= -30000000 && blockPos.getX() < 30000000 && blockPos.getZ() < 30000000) {
         if (this.checkIndex(blockPos.getX() >> 4, blockPos.getZ() >> 4, true)) {
            count4 = this.getLoggerEntry2(blockPos.getX() >> 4, blockPos.getZ() >> 4).computeCount(blockPos.getX() & 15, blockPos.getZ() & 15);
         } else {
            count4 = 0;
         }
      } else {
         count4 = this.getIndexOffset1() + 1;
      }

      return new BlockPos(blockPos.getX(), count4, blockPos.getZ());
   }

   public int computeCount(String text) {
      return this.dataManager.computeCount(text);
   }

   public void applyAttackEffectsExplosionNorm(
      AttackEffectsExplosionNormalType attackEffectsExplosionNormalType, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray2
   ) {
      this.applyIndexArray(attackEffectsExplosionNormalType.getIndex(), attackEffectsExplosionNormalType.isEnabled(), posX, posX2, posX3, posX4, posY, posZ, countArray2);
   }

   public boolean matchesBlockPos6(BlockPos blockPos) {
      return this.resolveBiomeIDIsOutOfBoundsManager(blockPos).isActiveAsFlag();
   }

   public void updateState() {
   }

   public boolean matchesBlockPos3(BlockPos blockPos, boolean flag) {
      IBlockState blockState = this.getBlockState(blockPos);
      Block block = blockState.getBlock();
      if (block.getMaterial() == Material.coreItemEntry) {
         return false;
      } else {
         this.applyBlockPosByBlockPos(2001, blockPos, Block.processAction5(blockState));
         if (flag) {
            block.processAction(this, blockPos, blockState, 0);
         }

         return this.matchesBlockPos(blockPos, Blocks.air.getDefaultState(), 3);
      }
   }

   public Entity getEntityByID(int count4) {
      return (Entity)this.altItemArrayEntry.resolveTargetByIndex(count4);
   }

   public boolean matchesAxisAlignedBB2(AxisAlignedBB boundingBox) {
      int count4 = MathHelper.ceiling_double_int(boundingBox.minX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxX + 1.0);
      int count6 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count7 = MathHelper.ceiling_double_int(boundingBox.maxY + 1.0);
      int count8 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count9 = MathHelper.ceiling_double_int(boundingBox.maxZ + 1.0);
      if (this.checkIndex(count4, count6, count8, count5, count7, count9, true)) {
         for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(); count4 < count5; count4++) {
            for (int index = count6; index < count7; index++) {
               for (int index2 = count8; index2 < count9; index2++) {
                  Block block = this.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index, index2)).getBlock();
                  if (block == Blocks.blockFire || block == Blocks.blockFlowingLava2 || block == Blocks.blockLava) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public float getFloat7(float factor5) {
      return ((Module)Client.getClient().getModuleManager().ambiance).isToggled()
         ? (float)Client.getClient().getModuleManager().ambiance.settingThunder.getValue()
         : (this.factor + (this.factor2 - this.factor) * factor5) * this.getFloat6(factor5);
   }

   public void applyTimeByTime(long value) {
      this.randomSeedManager.setTimestamp2(value);
   }

   @Override
   public boolean isAirBlock(BlockPos blockPos) {
      return this.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry;
   }

   public boolean checkIndex(int count4, int count5, int count6) {
      return this.checkIndex(count4, count5, count6, true);
   }

   public void applyText(double posX, double posY, double posZ, String text, float factor5, float factor6, boolean flag) {
   }

   public void applyEntityByEntity(Entity entity) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyEntityByEntity(entity);
      }
   }

   public void setIndexOffset1(int indexOffset3) {
      this.indexOffset1 = indexOffset3;
   }

   public Random resolveRandom(int count4, int count5, int count6) {
      long value = (long)count4 * 341873128712L + (long)count5 * 132897987541L + this.getRandomSeedManager().getTimestamp5() + (long)count6;
      this.random.setSeed(value);
      return this.random;
   }

   public boolean matchesAxisAlignedBBByAxisAlignedBB(AxisAlignedBB boundingBox, Material material, Entity entity) {
      int count4 = MathHelper.ceiling_double_int(boundingBox.minX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxX + 1.0);
      int count6 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count7 = MathHelper.ceiling_double_int(boundingBox.maxY + 1.0);
      int count8 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count9 = MathHelper.ceiling_double_int(boundingBox.maxZ + 1.0);
      if (!this.checkIndex(count4, count6, count8, count5, count7, count9, true)) {
         return false;
      } else {
         boolean flag = false;
         Vec3 vec = new Vec3(0.0, 0.0, 0.0);

         for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(); count4 < count5; count4++) {
            for (int index = count6; index < count7; index++) {
               for (int index2 = count8; index2 < count9; index2++) {
                  auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index, index2);
                  IBlockState blockState = this.getBlockState(auxiliaryBlockPosAdapter);
                  Block block = blockState.getBlock();
                  if (block.getMaterial() == material) {
                     double offset = (double)(
                        (float)(index + 1) - BlockLiquid.computeFactor((Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry))
                     );
                     if ((double)count7 >= offset) {
                        flag = true;
                        vec = block.processAction(this, auxiliaryBlockPosAdapter, entity, vec);
                     }
                  }
               }
            }
         }

         if (vec.getValueAsDouble() > 0.0 && entity.isActive8()) {
            vec = vec.normalize();
            double offset2 = 0.014;
            entity.motionX = entity.motionX + vec.xCoord * offset2;
            entity.motionY = entity.motionY + vec.yCoord * offset2;
            entity.motionZ = entity.motionZ + vec.zCoord * offset2;
         }

         return flag;
      }
   }

   public boolean checkNoEntityCollision(Entity entity) {
      this.list4.add(entity);
      return true;
   }

   public boolean checkFlag(boolean flag) {
      return false;
   }

   public MovingObjectPosition resolveMovingObjectPosition(
      double posX, double posX2, double posX3, double posX4, double posY, double posZ, boolean flag, boolean flag2, boolean flag3, boolean flag4
   ) {
      if (Double.isNaN(posX) || Double.isNaN(posX2) || Double.isNaN(posX3)) {
         return null;
      } else if (!Double.isNaN(posX4) && !Double.isNaN(posY) && !Double.isNaN(posZ)) {
         int count4 = MathHelper.ceiling_double_int(posX4);
         int count5 = MathHelper.ceiling_double_int(posY);
         int count6 = MathHelper.ceiling_double_int(posZ);
         int count7 = MathHelper.ceiling_double_int(posX);
         int count8 = MathHelper.ceiling_double_int(posX2);
         int count9 = MathHelper.ceiling_double_int(posX3);
         BlockPos blockPos = new BlockPos(count7, count8, count9);
         IBlockState blockState = this.getBlockState(blockPos);
         Block block = blockState.getBlock();
         if ((!flag2 || block.processAction(this, blockState, blockPos.getX(), blockPos.getY(), blockPos.getZ()) != null) && block.processAction(blockState, flag, flag4)) {
            MovingObjectPosition hitResult = block.resolveMovingObjectPosition(this, blockPos, posX, posX2, posX3, posX4, posY, posZ);
            if (hitResult != null) {
               return hitResult;
            }
         }

         MovingObjectPosition hitResult2 = null;
         int count10 = 200;

         while (count10-- >= 0) {
            if (Double.isNaN(posX) || Double.isNaN(posX2) || Double.isNaN(posX3)) {
               return null;
            }

            if (count7 == count4 && count8 == count5 && count9 == count6) {
               return flag3 ? hitResult2 : null;
            }

            boolean flag5 = true;
            boolean flag6 = true;
            boolean flag7 = true;
            double offset = 999.0;
            double offset2 = 999.0;
            double offset3 = 999.0;
            if (count4 > count7) {
               offset = (double)count7 + 1.0;
            } else if (count4 < count7) {
               offset = (double)count7 + 0.0;
            } else {
               flag5 = false;
            }

            if (count5 > count8) {
               offset2 = (double)count8 + 1.0;
            } else if (count5 < count8) {
               offset2 = (double)count8 + 0.0;
            } else {
               flag6 = false;
            }

            if (count6 > count9) {
               offset3 = (double)count9 + 1.0;
            } else if (count6 < count9) {
               offset3 = (double)count9 + 0.0;
            } else {
               flag7 = false;
            }

            double offset4 = 999.0;
            double offset5 = 999.0;
            double offset6 = 999.0;
            double offset7 = posX4 - posX;
            double offset8 = posY - posX2;
            double offset9 = posZ - posX3;
            if (flag5) {
               offset4 = (offset - posX) / offset7;
            }

            if (flag6) {
               offset5 = (offset2 - posX2) / offset8;
            }

            if (flag7) {
               offset6 = (offset3 - posX3) / offset9;
            }

            if (offset4 == -0.0) {
               offset4 = -1.0E-4;
            }

            if (offset5 == -0.0) {
               offset5 = -1.0E-4;
            }

            if (offset6 == -0.0) {
               offset6 = -1.0E-4;
            }

            EnumFacing enumFacing;
            if (offset4 < offset5 && offset4 < offset6) {
               enumFacing = count4 > count7 ? EnumFacing.WEST : EnumFacing.EAST;
               posX = offset;
               posX2 += offset8 * offset4;
               posX3 += offset9 * offset4;
            } else if (offset5 < offset6) {
               enumFacing = count5 > count8 ? EnumFacing.DOWN : EnumFacing.UP;
               posX += offset7 * offset5;
               posX2 = offset2;
               posX3 += offset9 * offset5;
            } else {
               enumFacing = count6 > count9 ? EnumFacing.NORTH : EnumFacing.SOUTH;
               posX += offset7 * offset6;
               posX2 += offset8 * offset6;
               posX3 = offset3;
            }

            count7 = MathHelper.ceiling_double_int(posX) - (enumFacing == EnumFacing.EAST ? 1 : 0);
            count8 = MathHelper.ceiling_double_int(posX2) - (enumFacing == EnumFacing.UP ? 1 : 0);
            count9 = MathHelper.ceiling_double_int(posX3) - (enumFacing == EnumFacing.SOUTH ? 1 : 0);
            blockPos = new BlockPos(count7, count8, count9);
            IBlockState blockState2 = this.getBlockState(blockPos);
            Block block2 = blockState2.getBlock();
            if (!flag2 || block2.processAction(this, blockState2, blockPos.getX(), blockPos.getY(), blockPos.getZ()) != null) {
               if (block2.processAction(blockState2, flag, flag4)) {
                  MovingObjectPosition hitResult3 = block2.resolveMovingObjectPosition(this, blockPos, posX, posX2, posX3, posX4, posY, posZ);
                  if (hitResult3 != null) {
                     return hitResult3;
                  }
               } else {
                  hitResult2 = new MovingObjectPosition(MovingObjectType.MISS, new Vec3(posX, posX2, posX3), enumFacing, blockPos);
               }
            }
         }

         return flag3 ? hitResult2 : null;
      } else {
         return null;
      }
   }

   public void applyBlockPos(BlockPos blockPos, String text) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyText(text, blockPos);
      }
   }

   public void add(TileEntity tileEntity) {
      this.list5.add(tileEntity);
   }

   public static boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count4, int count5, int count6) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count4, count5, count6);
      Block block = iBlockState.getBlock();
      return block.getMaterial().isActive3() && block.processAction14()
         ? true
         : (
            block instanceof BlockRedSandstoneStairs
               ? iBlockState.getComparable(BlockRedSandstoneStairs.immutableSetEntry2) == MovementChoiceProperty.TOP
               : (
                  block instanceof ExtraBlockAdapter
                     ? iBlockState.getComparable(ExtraBlockAdapter.immutableSetEntry) == MovementModeProperty.TOP
                     : (
                        block instanceof BlockHopper
                           ? true
                           : (block instanceof BlockSnowLayer ? (Integer)iBlockState.getComparable(BlockSnowLayer.auxiliaryImmutableSetEntry) == 7 : false)
                     )
               )
         );
   }

   public void applyBlockPos2(BlockPos blockPos, Block block) {
      if (!this.state) {
         IBlockState blockState = this.getBlockState(blockPos);

         try {
            blockState.getBlock().processAction(this, blockPos, blockState, block);
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Exception while updating neighbours");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Block being updated");
            crashReportCategory.applyText("Source block type", new SpecialCallableAdapter(this, block));
            CrashReportCategory.applyCrashReportCategory(crashReportCategory, blockPos, blockState);
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      }
   }

   public void updateState9() {
      if (!this.secondaryFloatArrayEntry.isEnabled() && !this.state) {
         int count4 = this.randomSeedManager.getCount();
         if (count4 > 0) {
            this.randomSeedManager.setCount(--count4);
            this.randomSeedManager.setCount10(this.randomSeedManager.isEnabled6() ? 1 : 2);
            this.randomSeedManager.setCount6(this.randomSeedManager.isEnabled4() ? 1 : 2);
         }

         count4 = this.randomSeedManager.getCount10();
         if (count4 <= 0) {
            if (this.randomSeedManager.isEnabled6()) {
               this.randomSeedManager.setCount10(this.random.nextInt(12000) + 3600);
            } else {
               this.randomSeedManager.setCount10(this.random.nextInt(168000) + 12000);
            }
         } else {
            this.randomSeedManager.setCount10(--count4);
            if (count4 <= 0) {
               this.randomSeedManager.setEnabled6(!this.randomSeedManager.isEnabled6());
            }
         }

         this.factor = this.factor2;
         if (this.randomSeedManager.isEnabled6()) {
            this.factor2 = (float)((double)this.factor2 + 0.01);
         } else {
            this.factor2 = (float)((double)this.factor2 - 0.01);
         }

         this.factor2 = MathHelper.clamp_float(this.factor2, 0.0F, 1.0F);
         count4 = this.randomSeedManager.getCount6();
         if (count4 <= 0) {
            if (this.randomSeedManager.isEnabled4()) {
               this.randomSeedManager.setCount6(this.random.nextInt(12000) + 12000);
            } else {
               this.randomSeedManager.setCount6(this.random.nextInt(168000) + 12000);
            }
         } else {
            this.randomSeedManager.setCount6(--count4);
            if (count4 <= 0) {
               this.randomSeedManager.setEnabled4(!this.randomSeedManager.isEnabled4());
            }
         }

         this.factor4 = this.factor3;
         if (this.randomSeedManager.isEnabled4()) {
            this.factor3 = (float)((double)this.factor3 + 0.01);
         } else {
            this.factor3 = (float)((double)this.factor3 - 0.01);
         }

         this.factor3 = MathHelper.clamp_float(this.factor3, 0.0F, 1.0F);
      }
   }

   public void applyBlockPos19(int count4, BlockPos blockPos, int count5) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyBlockPosByBlockPos(count4, blockPos, count5);
      }
   }

   public boolean matchesTileEntity(TileEntity tileEntity) {
      boolean flag = this.itemList3.add(tileEntity);
      if (flag && tileEntity instanceof PreUpdateConsumer) {
         this.list2.add(tileEntity);
      }

      return flag;
   }

   public List resolveListByClass(Class clazz, Predicate predicate) {
      ArrayList list7 = Lists.newArrayList();

      for (Entity entity : (Iterable<Entity>)(Iterable<?>)this.itemList7) {
         if (clazz.isAssignableFrom(entity.getClass()) && predicate.apply(entity)) {
            list7.add(entity);
         }
      }

      return list7;
   }

   public boolean matchesAxisAlignedBBByAxisAlignedBB(AxisAlignedBB boundingBox, Material material) {
      int count4 = MathHelper.ceiling_double_int(boundingBox.minX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxX + 1.0);
      int count6 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count7 = MathHelper.ceiling_double_int(boundingBox.maxY + 1.0);
      int count8 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count9 = MathHelper.ceiling_double_int(boundingBox.maxZ + 1.0);

      for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(); count4 < count5; count4++) {
         for (int index = count6; index < count7; index++) {
            for (int index2 = count8; index2 < count9; index2++) {
               IBlockState blockState = this.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index, index2));
               if (blockState.getBlock().getMaterial() == material) {
                  int comparable = (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry);
                  double offset = (double)(index + 1);
                  if (comparable < 8) {
                     offset = (double)(index + 1) - (double)comparable / 8.0;
                  }

                  if (offset >= boundingBox.minY) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public boolean checkIndex(int count4, int count5, int count6, int count7, int count8, int count9, boolean flag) {
      if (count8 >= 0 && count5 < 256) {
         count4 >>= 4;
         count6 >>= 4;
         count7 >>= 4;
         count9 >>= 4;

         for (int index = count4; index <= count7; index++) {
            for (int index2 = count6; index2 <= count9; index2++) {
               if (!this.checkIndex(index, index2, flag)) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public IChunkProvider getCommonListener() {
      return this.commonListener;
   }

   public int getIndexOffset2() {
      return this.indexOffset2;
   }

   public Vec3 resolveVec3(float factor5) {
      float factor6 = this.computeFactor(factor5);
      return this.secondaryFloatArrayEntry.resolveVec3(factor6, factor5);
   }

   public long getTimestampAsTime() {
      return this.randomSeedManager.getTimestamp();
   }

   public void remove(ContextListener contextListener) {
      this.list.remove(contextListener);
   }

   public String getText() {
      return "All: " + this.loadedEntityList.size();
   }

   public boolean matchesBlock(Block block, BlockPos blockPos, boolean flag, EnumFacing enumFacing, Entity entity, ItemStack stack) {
      Block block2 = this.getBlockState(blockPos).getBlock();
      AxisAlignedBB boundingBox = flag ? null : block.processAction(this, block.getDefaultState(), blockPos.getX(), blockPos.getY(), blockPos.getZ());
      return boundingBox != null && !this.matchesAxisAlignedBBByAxisAlignedBB(boundingBox, entity)
         ? false
         : (
            block2.getMaterial() == Material.material22 && block == Blocks.block51
               ? true
               : block2.getMaterial().canExecute() && block.processAction(this, blockPos, enumFacing, stack)
         );
   }

   private boolean checkIndex3(int count4, int count5, int count6) {
      return count4 >= -30000000 && count6 >= -30000000 && count4 < 30000000 && count6 < 30000000 && count5 >= 0 && count5 < 256;
   }

   public void applyEntity(Entity entity, byte byteValue) {
   }

   public void applyAttackEffectsExplosionNorm(
      AttackEffectsExplosionNormalType attackEffectsExplosionNormalType, boolean flag, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray2
   ) {
      this.applyIndexArray(attackEffectsExplosionNormalType.getIndex(), attackEffectsExplosionNormalType.isEnabled() | flag, posX, posX2, posX3, posX4, posY, posZ, countArray2);
   }

   public void applyAlertNotificationSound(int count4, int count5, AlertNotificationSound alertNotificationSound) {
      this.uNKNOWNManager.applyText("moodSound");
      if (this.count3 == 0 && !this.state) {
         this.count2 = this.count2 * 3 + 1013904223;
         int count6 = this.count2 >> 2;
         int count7 = count6 & 15;
         int count8 = count6 >> 8 & 15;
         count6 = count6 >> 16 & 0xFF;
         Block block = alertNotificationSound.resolveBlock(count7, count6, count8);
         count7 += count4;
         count8 += count5;
         if (block.getMaterial() == Material.coreItemEntry
            && this.computeCount(count7, count6, count8) <= this.random.nextInt(8)
            && this.computeCount(CombatConfigProperty.SKY, count7, count6, count8) <= 0) {
            EntityPlayer closestPlayer = this.getClosestPlayer((double)count7 + 0.5, (double)count6 + 0.5, (double)count8 + 0.5, 8.0);
            if (closestPlayer != null && closestPlayer.getDouble3((double)count7 + 0.5, (double)count6 + 0.5, (double)count8 + 0.5) > 4.0) {
               this.applyText((double)count7 + 0.5, (double)count6 + 0.5, (double)count8 + 0.5, "ambient.cave.cave", 0.7F, 0.8F + this.random.nextFloat() * 0.2F);
               this.count3 = this.random.nextInt(12000) + 6000;
            }
         }
      }

      this.uNKNOWNManager.applyText("checkLight");
      alertNotificationSound.updateState3();
   }

   private void applyIndexArray(int count4, boolean flag, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray2) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ContextListener)this.list.get(index)).applyIndexArray(count4, flag, posX, posX2, posX3, posX4, posY, posZ, countArray2);
      }
   }

   public MovingObjectPosition getMovingObjectPosition7(
      double posX, double posX2, double posX3, double posX4, double posY, double posZ, boolean flag, boolean flag2, boolean flag3
   ) {
      if (Double.isNaN(posX) || Double.isNaN(posX2) || Double.isNaN(posX3)) {
         return null;
      } else if (!Double.isNaN(posX4) && !Double.isNaN(posY) && !Double.isNaN(posZ)) {
         int count4 = MathHelper.ceiling_double_int(posX4);
         int count5 = MathHelper.ceiling_double_int(posY);
         int count6 = MathHelper.ceiling_double_int(posZ);
         int count7 = MathHelper.ceiling_double_int(posX);
         int count8 = MathHelper.ceiling_double_int(posX2);
         int count9 = MathHelper.ceiling_double_int(posX3);
         IBlockState iBlockState = this.getIBlockState(count7, count8, count9);
         Block block = iBlockState.getBlock();
         if ((!flag2 || block.processAction(this, iBlockState, count7, count8, count9) != null) && block.processAction(iBlockState, flag)) {
            MovingObjectPosition hitResult = block.resolveMovingObjectPositionByWorld(this, count7, count8, count9, posX, posX2, posY, posZ, posX3, posX4);
            if (hitResult != null) {
               return hitResult;
            }
         }

         MovingObjectPosition hitResult2 = null;
         int count10 = 200;

         while (count10-- >= 0) {
            if (Double.isNaN(posX) || Double.isNaN(posX2) || Double.isNaN(posX3)) {
               return null;
            }

            if (count7 == count4 && count8 == count5 && count9 == count6) {
               return flag3 ? hitResult2 : null;
            }

            boolean flag4 = true;
            boolean flag5 = true;
            boolean flag6 = true;
            double offset = 999.0;
            double offset2 = 999.0;
            double offset3 = 999.0;
            if (count4 > count7) {
               offset = (double)count7 + 1.0;
            } else if (count4 < count7) {
               offset = (double)count7 + 0.0;
            } else {
               flag4 = false;
            }

            if (count5 > count8) {
               offset2 = (double)count8 + 1.0;
            } else if (count5 < count8) {
               offset2 = (double)count8 + 0.0;
            } else {
               flag5 = false;
            }

            if (count6 > count9) {
               offset3 = (double)count9 + 1.0;
            } else if (count6 < count9) {
               offset3 = (double)count9 + 0.0;
            } else {
               flag6 = false;
            }

            double offset4 = 999.0;
            double offset5 = 999.0;
            double offset6 = 999.0;
            double offset7 = posX4 - posX;
            double offset8 = posY - posX2;
            double offset9 = posZ - posX3;
            if (flag4) {
               offset4 = (offset - posX) / offset7;
            }

            if (flag5) {
               offset5 = (offset2 - posX2) / offset8;
            }

            if (flag6) {
               offset6 = (offset3 - posX3) / offset9;
            }

            if (offset4 == -0.0) {
               offset4 = -1.0E-4;
            }

            if (offset5 == -0.0) {
               offset5 = -1.0E-4;
            }

            if (offset6 == -0.0) {
               offset6 = -1.0E-4;
            }

            EnumFacing enumFacing;
            if (offset4 < offset5 && offset4 < offset6) {
               enumFacing = count4 > count7 ? EnumFacing.WEST : EnumFacing.EAST;
               posX = offset;
               posX2 += offset8 * offset4;
               posX3 += offset9 * offset4;
            } else if (offset5 < offset6) {
               enumFacing = count5 > count8 ? EnumFacing.DOWN : EnumFacing.UP;
               posX += offset7 * offset5;
               posX2 = offset2;
               posX3 += offset9 * offset5;
            } else {
               enumFacing = count6 > count9 ? EnumFacing.NORTH : EnumFacing.SOUTH;
               posX += offset7 * offset6;
               posX2 += offset8 * offset6;
               posX3 = offset3;
            }

            count7 = MathHelper.ceiling_double_int(posX) - (enumFacing == EnumFacing.EAST ? 1 : 0);
            count8 = MathHelper.ceiling_double_int(posX2) - (enumFacing == EnumFacing.UP ? 1 : 0);
            count9 = MathHelper.ceiling_double_int(posX3) - (enumFacing == EnumFacing.SOUTH ? 1 : 0);
            IBlockState iBlockState2 = this.getIBlockState(count7, count8, count9);
            Block block2 = iBlockState2.getBlock();
            if (!flag2 || block2.processAction(this, iBlockState2, count7, count8, count9) != null) {
               if (block2.processAction(iBlockState2, flag)) {
                  MovingObjectPosition hitResult3 = block2.resolveMovingObjectPositionByWorld(this, count7, count8, count9, posX, posX2, posY, posZ, posX3, posX4);
                  if (hitResult3 != null) {
                     return hitResult3;
                  }
               } else {
                  hitResult2 = new MovingObjectPosition(MovingObjectType.MISS, new Vec3(posX, posX2, posX3), enumFacing, new BlockPos(count7, count8, count9));
               }
            }
         }

         return flag3 ? hitResult2 : null;
      } else {
         return null;
      }
   }

   public Calendar getCalendar() {
      if (this.getTimestamp2() % 600L == 0L) {
         this.calendar.setTimeInMillis(VanillaRunnable.currentTimeMillis());
      }

      return this.calendar;
   }

   public float getScaleFactor(double posX, double posY, double posZ, AxisAlignedBB boundingBox) {
      double offset = 1.0 / ((boundingBox.maxX - boundingBox.minX) * 2.0 + 1.0);
      double offset2 = 1.0 / ((boundingBox.maxY - boundingBox.minY) * 2.0 + 1.0);
      double offset3 = 1.0 / ((boundingBox.maxZ - boundingBox.minZ) * 2.0 + 1.0);
      double offset4 = (1.0 - Math.floor(1.0 / offset) * offset) / 2.0;
      double offset5 = (1.0 - Math.floor(1.0 / offset3) * offset3) / 2.0;
      if (offset >= 0.0 && offset2 >= 0.0 && offset3 >= 0.0) {
         int count4 = 0;
         int count5 = 0;

         for (float factor5 = 0.0F; factor5 <= 1.0F; factor5 = (float)((double)factor5 + offset)) {
            for (float factor6 = 0.0F; factor6 <= 1.0F; factor6 = (float)((double)factor6 + offset2)) {
               for (float factor7 = 0.0F; factor7 <= 1.0F; factor7 = (float)((double)factor7 + offset3)) {
                  double offset6 = boundingBox.minX + (boundingBox.maxX - boundingBox.minX) * (double)factor5;
                  double offset7 = boundingBox.minY + (boundingBox.maxY - boundingBox.minY) * (double)factor6;
                  double offset8 = boundingBox.minZ + (boundingBox.maxZ - boundingBox.minZ) * (double)factor7;
                  if (this.getMovingObjectPosition2(offset6 + offset4, offset7, offset8 + offset5, posX, posY, posZ) == null) {
                     count4++;
                  }

                  count5++;
               }
            }
         }

         return (float)count4 / (float)count5;
      } else {
         return 0.0F;
      }
   }

   public void removeEntity(Entity entity) {
      this.applyEntity(entity, true);
   }

   public boolean matchesDistanceAggregator(DistanceAggregator distanceAggregator) {
      return this.matchesDistanceAggregator(distanceAggregator, true);
   }

   public BlockBreakState resolveBlockBreakState(Entity entity, double posX, double posY, double posZ, float factor5, boolean flag, boolean flag2) {
      BlockBreakState blockBreakState = new BlockBreakState(this, entity, posX, posY, posZ, factor5, flag, flag2);
      blockBreakState.updateState();
      blockBreakState.setEnabled(true);
      return blockBreakState;
   }

   public float computeFactorByFloat(float factor5) {
      float factor6 = this.computeFactor(factor5);
      factor6 = 1.0F - (MathHelper.cos(factor6 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);
      factor6 = MathHelper.clamp_float(factor6, 0.0F, 1.0F);
      return factor6 * factor6 * 0.5F;
   }

   public void updateState5() {
   }

   public boolean matchesAxisAlignedBBByAxisAlignedBB(AxisAlignedBB boundingBox) {
      int count4 = MathHelper.ceiling_double_int(boundingBox.minX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxX);
      int count6 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count7 = MathHelper.ceiling_double_int(boundingBox.maxY);
      int count8 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count9 = MathHelper.ceiling_double_int(boundingBox.maxZ);

      for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(); count4 <= count5; count4++) {
         for (int index = count6; index <= count7; index++) {
            for (int index2 = count8; index2 <= count9; index2++) {
               if (this.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index, index2)).getBlock().getMaterial().isActive2()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean matchesBlockPos5(BlockPos blockPos) {
      IBlockState blockState = this.getBlockState(blockPos);
      AxisAlignedBB boundingBox = blockState.getBlock().processAction(this, blockState, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      return boundingBox != null && boundingBox.getValue() >= 1.0;
   }

   public BlockPos resolveBlockPos(String text, BlockPos blockPos) {
      return this.getCommonListener().resolveBlockPos(this, text, blockPos);
   }

   public ISaveHandler getManagedListener() {
      return this.managedListener;
   }

   public boolean matchesBlockPos7(BlockPos blockPos) {
      return this.computeCountByBlockPos(blockPos.down(), EnumFacing.DOWN) > 0
         ? true
         : (
            this.computeCountByBlockPos(blockPos.up(), EnumFacing.UP) > 0
               ? true
               : (
                  this.computeCountByBlockPos(blockPos.getBlockPosAsBlockPos(), EnumFacing.NORTH) > 0
                     ? true
                     : (
                        this.computeCountByBlockPos(blockPos.getBlockPos2(), EnumFacing.SOUTH) > 0
                           ? true
                           : (
                              this.computeCountByBlockPos(blockPos.getBlockPos(), EnumFacing.WEST) > 0
                                 ? true
                                 : this.computeCountByBlockPos(blockPos.getBlockPos3(), EnumFacing.EAST) > 0
                           )
                     )
               )
         );
   }

   public boolean matchesBlockPos(BlockPos blockPos) {
      if (!this.isActive()) {
         return false;
      } else if (!this.checkIndex2(blockPos.getX(), blockPos.getY(), blockPos.getZ())) {
         return false;
      } else if (this.resolveBlockPosByBlockPos(blockPos).getY() > blockPos.getY()) {
         return false;
      } else {
         BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = this.resolveBiomeIDIsOutOfBoundsManager(blockPos);
         return biomeIDIsOutOfBoundsManager.isActive() ? false : (this.matchesBlockPos(blockPos, false) ? false : biomeIDIsOutOfBoundsManager.isActive2());
      }
   }

   public boolean matchesBlockPosByBlockPos(BlockPos blockPos) {
      return this.matchesBlockPosByBlockPos(blockPos, true);
   }

   public World(ISaveHandler iSaveHandler, RandomSeedManager randomSeedManager2, SecondaryFloatArrayEntry secondaryFloatArrayEntry2, UNKNOWNManager uNKNOWNManager2, boolean flag) {
      this.managedListener = iSaveHandler;
      this.uNKNOWNManager = uNKNOWNManager2;
      this.randomSeedManager = randomSeedManager2;
      this.secondaryFloatArrayEntry = secondaryFloatArrayEntry2;
      this.state = flag;
      this.internalListEntry = secondaryFloatArrayEntry2.getWorldInfo();
   }
}
