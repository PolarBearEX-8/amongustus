package net.minecraft.entity.player;

import client.event.CoreListener;
import client.event.LocalListener;
import client.setting.GameSettingsFullType;
import client.setting.ItemNoneType;
import client.setting.MovementBooleanProperty;
import client.setting.MovementStateProperty;
import client.setting.WorldSettingsGameType;
import client.util.AchievementTheEnd2Manager;
import client.util.AdvancedContainerAdapter;
import client.util.AlertNotificationSound;
import client.util.AuxiliaryArrayListAdapter;
import client.util.AuxiliaryListenerAdapter4;
import client.util.AuxiliarySlotAdapter;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.DamageSourceAdapter;
import client.util.DuplicateStatIdManager;
import client.util.DuplicateStatIdManagerEntry;
import client.util.ExtendedContainerAdapter;
import client.util.ForwardingSet;
import client.util.HealthHandler;
import client.util.IInventoryExtension;
import client.util.InternalWorldEntry;
import client.util.InterpolationCollector;
import client.util.Listener63Extension;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.ScoreboardData;
import client.util.SecondaryClientManager;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SkippingEntityWithIdManager;
import client.util.SplineFormatter;
import client.util.SplineSorter;
import client.util.StatCraftItemManager;
import client.util.StatFileWriterHandler;
import client.util.TargetDirector;
import client.util.TertiaryContainerAdapter;
import client.util.VanillaRunnable;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockCobblestoneWall;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.minecraft.network.play.server.S43PacketCamera;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityPlayerAdapter extends EntityPlayer implements CoreListener {
   public static final Logger logger = LogManager.getLogger();
   public String labelText = "en_US";
   public AuxiliaryListenerAdapter4 auxiliaryListenerAdapter4;
   public final VanillaRunnable vanillaRunnable;
   public final InternalWorldEntry internalWorldEntry;
   public double value40;
   public double value39;
   public final List list = Lists.newLinkedList();
   public final List list2 = Lists.newLinkedList();
   public final StatFileWriterHandler statFileWriterHandler;
   public float factor52 = Float.MIN_VALUE;
   public float factor54 = -1.0E8F;
   public int count37 = -99999999;
   public boolean enabled31 = true;
   public int count40 = -99999999;
   public int count36 = 60;
   public GameSettingsFullType gameSettingsFullType;
   public boolean enabled27 = true;
   public long timestamp2 = System.currentTimeMillis();
   public Entity entity5 = null;
   public int count33;
   public boolean enabled29;
   public int count41;
   public boolean enabled26;

   @Override
   public void applyWorldSettingsGameType(WorldSettingsGameType worldSettingsGameType) {
      this.internalWorldEntry.applyWorldSettingsGameTypeByWorldSettingsGam(worldSettingsGameType);
      this.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(3, (float)worldSettingsGameType.getIndex()));
      if (worldSettingsGameType == WorldSettingsGameType.SPECTATOR) {
         this.setEntity8((Entity)null);
      } else {
         this.applyEntity32(this);
      }

      this.updateState43();
      this.updateState31();
   }

   @Override
   public boolean matchesText(int count, String text) {
      if ("seed".equals(text) && !this.vanillaRunnable.isActive5()) {
         return true;
      } else if (!"tell".equals(text) && !"help".equals(text) && !"me".equals(text) && !"trigger".equals(text)) {
         if (this.vanillaRunnable.getMovingManager().matchesGameProfileByGameProfile(this.getGameProfile())) {
            TargetDirector targetDirector = (TargetDirector)this.vanillaRunnable
               .getMovingManager()
               .getExtendedLoggerEntryAdapter()
               .resolveTargetStack(this.getGameProfile());
            return targetDirector != null ? targetDirector.getCount() >= count : this.vanillaRunnable.getCount() >= count;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   @Override
   public void applyIInventory(IInventory iInventory) {
      if (this.container2 != this.container) {
         this.updateState5();
      }

      if (iInventory instanceof IInventoryExtension) {
         IInventoryExtension iInventoryExtension = (IInventoryExtension)iInventory;
         if (iInventoryExtension.isActive16() && !this.matchesLockCode(iInventoryExtension.getLockCode()) && !this.isActive23()) {
            this.auxiliaryListenerAdapter4
               .applyPacket(new S02PacketChat(new BaseManagerStyleIChatComponentAdapter("container.isLocked", new Object[]{iInventory.getChatComponent()}), (byte)2));
            this.auxiliaryListenerAdapter4.applyPacket(new S29PacketSoundEffect("random.door_close", this.posX, this.posY, this.posZ, 1.0F, 1.0F));
            return;
         }
      }

      this.updateState49();
      if (iInventory instanceof Listener63Extension) {
         this.auxiliaryListenerAdapter4
            .applyPacket(new S2DPacketOpenWindow(this.count33, ((Listener63Extension)iInventory).getLabelText2(), iInventory.getChatComponent(), iInventory.processAction4()));
         this.container2 = ((Listener63Extension)iInventory).resolveContainer(this.inventoryPlayer, this);
      } else {
         this.auxiliaryListenerAdapter4
            .applyPacket(new S2DPacketOpenWindow(this.count33, "minecraft:container", iInventory.getChatComponent(), iInventory.processAction4()));
         this.container2 = new AdvancedContainerAdapter(this.inventoryPlayer, iInventory, this);
      }

      this.container2.indexOffset2 = this.count33;
      this.container2.applyCoreListener(this);
   }

   public void applyFloat(float factor, float factor2, boolean flag, boolean flag2) {
      if (this.ridingEntity != null) {
         if (factor >= -1.0F && factor <= 1.0F) {
            this.scaleFactor24 = factor;
         }

         if (factor2 >= -1.0F && factor2 <= 1.0F) {
            this.scaleFactor3 = factor2;
         }

         this.state31 = flag;
         this.setEnabled2(flag2);
      }
   }

   @Override
   public void applyIChatComponentByIChatComponent(IChatComponent iChatComponent) {
      this.auxiliaryListenerAdapter4.applyPacket(new S02PacketChat(iChatComponent));
   }

   @Override
   public void applyContainer(Container container, List list3) {
      this.auxiliaryListenerAdapter4.applyPacket(new S30PacketWindowItems(container.indexOffset2, list3));
      this.auxiliaryListenerAdapter4.applyPacket(new S2FPacketSetSlot(-1, -1, this.inventoryPlayer.getItemStack()));
   }

   @Override
   public void applyFlag(boolean flag, boolean flag2, boolean flag3) {
      if (this.isState5()) {
         this.getWorldServer().getSecondaryLoggerEntry().applyEntity2(this, new S0BPacketAnimation(this, 2));
      }

      super.applyFlag(flag, flag2, flag3);
      if (this.auxiliaryListenerAdapter4 != null) {
         this.auxiliaryListenerAdapter4.applyDouble(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
      }
   }

   public void applyText(String text, String text2) {
      this.auxiliaryListenerAdapter4.applyPacket(new S48PacketResourcePackSend(text, text2));
   }

   private void applyTileEntity(TileEntity tileEntity) {
      if (tileEntity != null) {
         Packet packet = tileEntity.getPacket();
         if (packet != null) {
            this.auxiliaryListenerAdapter4.applyPacket(packet);
         }
      }
   }

   public Entity getEntity2() {
      return (Entity)(this.entity5 == null ? this : this.entity5);
   }

   public void applyC15PacketClientSettings(C15PacketClientSettings packet) {
      this.labelText = packet.getLabelText();
      this.gameSettingsFullType = packet.getGameSettingsFullType();
      this.enabled27 = packet.isEnabled();
      this.getDataWatcher().applyTargetByTarget(10, (byte)packet.getCount());
   }

   @Override
   public void applyPotionEffect(PotionEffect potionEffect) {
      super.applyPotionEffect(potionEffect);
      this.auxiliaryListenerAdapter4.applyPacket(new S1DPacketEntityEffect(this.getEntityId(), potionEffect));
   }

   @Override
   public void setEntity8(Entity entity) {
      Entity entity2 = this.ridingEntity;
      super.setEntity8(entity);
      if (entity != entity2) {
         this.auxiliaryListenerAdapter4.applyPacket(new S1BPacketEntityAttach(0, this, this.ridingEntity));
         this.auxiliaryListenerAdapter4.applyDouble(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("playerGameType", 99)) {
         if (VanillaRunnable.getVanillaRunnable().isEnabled10()) {
            this.internalWorldEntry.applyWorldSettingsGameTypeByWorldSettingsGam(VanillaRunnable.getVanillaRunnable().getWorldSettingsGameType());
         } else {
            this.internalWorldEntry
               .applyWorldSettingsGameTypeByWorldSettingsGam(WorldSettingsGameType.resolveWorldSettingsGameType(tagCompound.computeCount("playerGameType")));
         }
      }
   }

   @Override
   public boolean matchesEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      return entityPlayerAdapter.isActive23() ? this.getEntity2() == this : (this.isActive23() ? false : super.matchesEntityPlayerAdapter(entityPlayerAdapter));
   }

   @Override
   public void applyContainer(Container container, int count, ItemStack stack) {
      if (!(container.getSlot(count) instanceof AuxiliarySlotAdapter) && !this.enabled29) {
         this.auxiliaryListenerAdapter4.applyPacket(new S2FPacketSetSlot(container.indexOffset2, count, stack));
      }
   }

   @Override
   public void updateDuplicateStatIdManager(DuplicateStatIdManager duplicateStatIdManager, int count) {
      if (duplicateStatIdManager != null) {
         this.statFileWriterHandler.applyEntityPlayer2(this, duplicateStatIdManager, count);

         for (ScoreboardData scoreboardData : (Iterable<ScoreboardData>)(Iterable<?>)this.getThePlayerNameManager().resolveCollection(duplicateStatIdManager.getHealthHandler())) {
            this.getThePlayerNameManager().getComparatorEntry(this.getText(), scoreboardData).applyIndexByIndex(count);
         }

         if (this.statFileWriterHandler.isEnabled()) {
            this.statFileWriterHandler.applyEntityPlayerAdapterByEntityPlayerAdap(this);
         }
      }
   }

   @Override
   public void applyListener63Extension(Listener63Extension listener63Extension) {
      this.updateState49();
      this.auxiliaryListenerAdapter4.applyPacket(new S2DPacketOpenWindow(this.count33, listener63Extension.getLabelText2(), listener63Extension.getChatComponent()));
      this.container2 = listener63Extension.resolveContainer(this.inventoryPlayer, this);
      this.container2.indexOffset2 = this.count33;
      this.container2.applyCoreListener(this);
   }

   @Override
   public void applyEntity2(Entity entity, int count) {
      super.applyEntity2(entity, count);
      this.container2.detectAndSendChanges();
   }

   @Override
   public void applyTileEntitySign(TileEntitySign tileEntitySign) {
      tileEntitySign.setEntityPlayer(this);
      this.auxiliaryListenerAdapter4.applyPacket(new S36PacketSignEditorOpen(tileEntitySign.getBlockPos()));
   }

   public String getTextAsText() {
      String text = this.auxiliaryListenerAdapter4.networkManager.getSocketAddress().toString();
      String text2 = text.substring(text.indexOf("/") + 1);
      return text2.substring(0, text2.indexOf(":"));
   }

   public void applyEntity30(Entity entity) {
      if (entity instanceof EntityPlayer) {
         this.auxiliaryListenerAdapter4.applyPacket(new S13PacketDestroyEntities(new int[]{entity.getEntityId()}));
      } else {
         this.list2.add(entity.getEntityId());
      }
   }

   @Override
   public void applyItemStack(ItemStack stack) {
      if (stack.getItem() == Items.writtenBook) {
         this.auxiliaryListenerAdapter4.applyPacket(new S3FPacketCustomPayload("MC|BOpen", new PacketBuffer(Unpooled.buffer())));
      }
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      this.auxiliaryListenerAdapter4.applyPacket(new S02PacketChat(iChatComponent));
   }

   @Override
   public void applyLocalListener(LocalListener localListener) {
      this.updateState49();
      this.container2 = new ExtendedContainerAdapter(this.inventoryPlayer, localListener, this.worldObj);
      this.container2.indexOffset2 = this.count33;
      this.container2.applyCoreListener(this);
      SplineFormatter splineFormatter = ((ExtendedContainerAdapter)this.container2).getSplineFormatter();
      IChatComponent chatComponent = localListener.getChatComponent();
      this.auxiliaryListenerAdapter4.applyPacket(new S2DPacketOpenWindow(this.count33, "minecraft:villager", chatComponent, splineFormatter.processAction4()));
      AuxiliaryArrayListAdapter auxiliaryArrayListAdapter = localListener.resolveAuxiliaryArrayListAdapterByEntityPlayer(this);
      if (auxiliaryArrayListAdapter != null) {
         PacketBuffer packet = new PacketBuffer(Unpooled.buffer());
         packet.writeInt(this.count33);
         auxiliaryArrayListAdapter.applyPacketBuffer(packet);
         this.auxiliaryListenerAdapter4.applyPacket(new S3FPacketCustomPayload("MC|TrList", packet));
      }
   }

   public GameSettingsFullType getGameSettingsFullType() {
      return this.gameSettingsFullType;
   }

   @Override
   public void applyItemStack(ItemStack stack, int count) {
      super.applyItemStack(stack, count);
      if (stack != null && stack.getItem() != null && stack.getItem().processAction4(stack) == ItemNoneType.EAT) {
         this.getWorldServer().getSecondaryLoggerEntry().applyEntity2(this, new S0BPacketAnimation(this, 3));
      }
   }

   @Override
   public void applyDoubleByDouble(double posX, double posY, double posZ) {
      this.auxiliaryListenerAdapter4.applyDouble(posX, posY, posZ, this.rotationYaw, this.rotationPitch);
   }

   public long getTimestamp2() {
      return this.timestamp2;
   }

   public IChatComponent getIChatComponent() {
      return null;
   }

   public void applyDouble(double offset, boolean flag) {
      int count = MathHelper.ceiling_double_int(this.posX);
      int count2 = MathHelper.ceiling_double_int(this.posY - 0.2F);
      int count3 = MathHelper.ceiling_double_int(this.posZ);
      BlockPos blockPos = new BlockPos(count, count2, count3);
      Block block = this.worldObj.getBlockState(blockPos).getBlock();
      if (block.getMaterial() == Material.coreItemEntry) {
         Block block2 = this.worldObj.getBlockState(blockPos.down()).getBlock();
         if (block2 instanceof BlockAcaciaFence || block2 instanceof BlockCobblestoneWall || block2 instanceof BlockAcaciaFenceGate) {
            blockPos = blockPos.down();
            block = this.worldObj.getBlockState(blockPos).getBlock();
         }
      }

      super.applyBlock(offset, flag, block, blockPos);
   }

   @Override
   public SplineSorter resolveSplineSorter(BlockPos blockPos) {
      SplineSorter splineSorter = super.resolveSplineSorter(blockPos);
      if (splineSorter == SplineSorter.OK) {
         S0APacketUseBed packet = new S0APacketUseBed(this, blockPos);
         this.getWorldServer().getSecondaryLoggerEntry().applyEntity3(this, packet);
         this.auxiliaryListenerAdapter4.applyDouble(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         this.auxiliaryListenerAdapter4.applyPacket(packet);
      }

      return splineSorter;
   }

   @Override
   public void applyContainer(Container container, IInventory iInventory) {
      for (int index = 0; index < iInventory.processAction3(); index++) {
         this.auxiliaryListenerAdapter4.applyPacket(new S31PacketWindowProperty(container.indexOffset2, index, iInventory.processAction(index)));
      }
   }

   public StatFileWriterHandler getStatFileWriterHandler() {
      return this.statFileWriterHandler;
   }

   @Override
   public void applyPotionEffect2(PotionEffect potionEffect) {
      super.applyPotionEffect2(potionEffect);
      this.auxiliaryListenerAdapter4.applyPacket(new S1EPacketRemoveEntityEffect(this.getEntityId(), potionEffect));
   }

   @Override
   public void applyEntity18(Entity entity) {
      if (this.internalWorldEntry.getWorldSettingsGameType() == WorldSettingsGameType.SPECTATOR) {
         this.applyEntity32(entity);
      } else {
         super.applyEntity18(entity);
      }
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      return !this.isActive27() ? false : super.matchesEntityPlayer6(player);
   }

   @Override
   public void applyContainer(Container container, int count, int count2) {
      this.auxiliaryListenerAdapter4.applyPacket(new S31PacketWindowProperty(container.indexOffset2, count, count2));
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      if (this.worldObj.getDoFireTickManager().matchesText("showDeathMessages")) {
         SecondaryClientManager secondaryClientManager = this.getSecondaryClientManager();
         if (secondaryClientManager == null || secondaryClientManager.getMovementBooleanProperty() == MovementBooleanProperty.ALWAYS) {
            this.vanillaRunnable.getMovingManager().applyIChatComponent(this.getGenericManager().getIChatComponent());
         } else if (secondaryClientManager.getMovementBooleanProperty() == MovementBooleanProperty.HIDE_FOR_OTHER_TEAMS) {
            this.vanillaRunnable.getMovingManager().applyEntityPlayer(this, this.getGenericManager().getIChatComponent());
         } else if (secondaryClientManager.getMovementBooleanProperty() == MovementBooleanProperty.HIDE_FOR_OWN_TEAM) {
            this.vanillaRunnable.getMovingManager().applyEntityPlayer2(this, this.getGenericManager().getIChatComponent());
         }
      }

      if (!this.worldObj.getDoFireTickManager().matchesText("keepInventory")) {
         this.inventoryPlayer.updateState4();
      }

      for (ScoreboardData scoreboardData : (Iterable<ScoreboardData>)(Iterable<?>)this.worldObj.getThePlayerNameManager().resolveCollection(HealthHandler.healthHandler5)) {
         this.getThePlayerNameManager().getComparatorEntry(this.getText(), scoreboardData).updateState();
      }

      EntityLivingBase entityLivingBase = this.getEntityLivingBase();
      if (entityLivingBase != null) {
         DuplicateStatIdManagerEntry duplicateStatIdManagerEntry = (DuplicateStatIdManagerEntry)SkippingEntityWithIdManager.map6.get(SkippingEntityWithIdManager.computeCount(entityLivingBase));
         if (duplicateStatIdManagerEntry != null) {
            this.applyDuplicateStatIdManagerByDuplicateStatIdM(duplicateStatIdManagerEntry.duplicateStatIdManager);
         }

         entityLivingBase.applyEntityByEntity(this, this.count17);
      }

      this.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager42);
      this.applyDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManager40);
      this.getGenericManager().updateState();
   }

   public WorldServer getWorldServer() {
      return (WorldServer)this.worldObj;
   }

   @Override
   public void applyEntityPlayer(EntityPlayer player, boolean flag) {
      super.applyEntityPlayer(player, flag);
      this.count40 = -1;
      this.factor54 = -1.0F;
      this.count37 = -1;
      this.list2.addAll(((EntityPlayerAdapter)player).list2);
   }

   @Override
   public void applyIndex2(int count) {
      int count2 = count;
      if (this.count4 == 1 && count == 1) {
         this.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter18);
         this.worldObj.spawnEntityInWorld(this);
         this.enabled26 = true;
         this.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(4, 0.0F));
      } else {
         if (this.count4 == 0 && count == 1) {
            this.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter24);
            BlockPos blockPosAsBlockPos = this.vanillaRunnable.resolveWorldServer(count).getBlockPosAsBlockPos();
            if (blockPosAsBlockPos != null) {
               this.auxiliaryListenerAdapter4.applyDouble((double)blockPosAsBlockPos.getX(), (double)blockPosAsBlockPos.getY(), (double)blockPosAsBlockPos.getZ(), 0.0F, 0.0F);
            }

            count2 = 1;
         } else {
            this.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter5);
         }

         this.vanillaRunnable.getMovingManager().applyEntityPlayerAdapter(this, count2);
         this.count40 = -1;
         this.factor54 = -1.0F;
         this.count37 = -1;
      }
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else if ((!this.vanillaRunnable.isActive5() || !this.isActive27() || !"fall".equals(damageSource.labelText))
         && this.count36 > 0
         && damageSource != DamageSource.damageSource10) {
         return false;
      } else {
         if (damageSource instanceof DamageSourceAdapter) {
            Entity entity = damageSource.getEntity();
            if (entity instanceof EntityPlayer && !this.matchesEntityPlayer6((EntityPlayer)entity)) {
               return false;
            }

            if (entity instanceof EntityArrow) {
               EntityArrow entityArrow = (EntityArrow)entity;
               if (entityArrow.entity2 instanceof EntityPlayer && !this.matchesEntityPlayer6((EntityPlayer)entityArrow.entity2)) {
                  return false;
               }
            }
         }

         return super.matchesDamageSource(damageSource, factor);
      }
   }

   @Override
   public void applyBlock(double offset, boolean flag, Block block, BlockPos blockPos) {
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("playerGameType", this.internalWorldEntry.getWorldSettingsGameType().getIndex());
   }

   @Override
   public void applyEntityHorse(EntityHorse entityHorse, IInventory iInventory) {
      if (this.container2 != this.container) {
         this.updateState5();
      }

      this.updateState49();
      this.auxiliaryListenerAdapter4
         .applyPacket(new S2DPacketOpenWindow(this.count33, "EntityHorse", iInventory.getChatComponent(), iInventory.processAction4(), entityHorse.getEntityId()));
      this.container2 = new TertiaryContainerAdapter(this.inventoryPlayer, iInventory, entityHorse, this);
      this.container2.indexOffset2 = this.count33;
      this.container2.applyCoreListener(this);
   }

   @Override
   public void applyEntity17(Entity entity) {
      this.getWorldServer().getSecondaryLoggerEntry().applyEntity2(this, new S0BPacketAnimation(entity, 4));
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(this.posX, this.posY + 0.5, this.posZ);
   }

   @Override
   public void applyPotionEffect(PotionEffect potionEffect, boolean flag) {
      super.applyPotionEffect(potionEffect, flag);
      this.auxiliaryListenerAdapter4.applyPacket(new S1DPacketEntityEffect(this.getEntityId(), potionEffect));
   }

   @Override
   public void applyDuplicateStatIdManager(DuplicateStatIdManager duplicateStatIdManager) {
      if (duplicateStatIdManager != null) {
         this.statFileWriterHandler.applyEntityPlayer(this, duplicateStatIdManager, 0);

         for (ScoreboardData scoreboardData : (Iterable<ScoreboardData>)(Iterable<?>)this.getThePlayerNameManager().resolveCollection(duplicateStatIdManager.getHealthHandler())) {
            this.getThePlayerNameManager().getComparatorEntry(this.getText(), scoreboardData).applyIndex(0);
         }

         if (this.statFileWriterHandler.isEnabled()) {
            this.statFileWriterHandler.applyEntityPlayerAdapterByEntityPlayerAdap(this);
         }
      }
   }

   public void applyContainer(Container container) {
      this.applyContainer(container, container.getList());
   }

   @Override
   public void updateState28() {
      if (this.isActive23()) {
         this.updateState30();
         this.setEnabledByFlag(true);
      } else {
         super.updateState28();
      }

      this.getWorldServer().getSecondaryLoggerEntry().applyEntityPlayerAdapter(this);
   }

   public void updateState53() {
      this.container2.applyCoreListener(this);
   }

   public void updateState6() {
      if (this.entity != null) {
         this.entity.setEntity8(this);
      }

      if (this.state5) {
         this.applyFlag(true, false, false);
      }
   }

   public void updateState48() {
      this.timestamp2 = VanillaRunnable.currentTimeMillis();
   }

   public void updateState46() {
      try {
         super.updateState();

         for (int index = 0; index < this.inventoryPlayer.processAction4(); index++) {
            ItemStack stack = this.inventoryPlayer.processAction3(index);
            if (stack != null && stack.getItem().processAction6()) {
               Packet packet = ((ItemMapBase)stack.getItem()).resolvePacketByItemStack(stack, this.worldObj, this);
               if (packet != null) {
                  this.auxiliaryListenerAdapter4.applyPacket(packet);
               }
            }
         }

         if (this.getFloat2() != this.factor54
            || this.count37 != this.foodLevelManager.getIndex()
            || this.foodLevelManager.getFactor() == 0.0F != this.enabled31) {
            this.auxiliaryListenerAdapter4
               .applyPacket(new S06PacketUpdateHealth(this.getFloat2(), this.foodLevelManager.getIndex(), this.foodLevelManager.getFactor()));
            this.factor54 = this.getFloat2();
            this.count37 = this.foodLevelManager.getIndex();
            this.enabled31 = this.foodLevelManager.getFactor() == 0.0F;
         }

         if (this.getFloat2() + this.getScaleFactor13() != this.factor52) {
            this.factor52 = this.getFloat2() + this.getScaleFactor13();

            for (ScoreboardData scoreboardData : (Iterable<ScoreboardData>)(Iterable<?>)this.getThePlayerNameManager().resolveCollection(HealthHandler.healthHandler)) {
               this.getThePlayerNameManager().getComparatorEntry(this.getText(), scoreboardData).applyList(Arrays.asList(this));
            }
         }

         if (this.count32 != this.count40) {
            this.count40 = this.count32;
            this.auxiliaryListenerAdapter4.applyPacket(new S1FPacketSetExperience(this.factor39, this.count32, this.count25));
         }

         if (this.ticksExisted % 20 * 5 == 0
            && !this.getStatFileWriterHandler()
               .matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter26)) {
            this.updateState52();
         }
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Ticking player");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Player being ticked");
         this.applyCrashReportCategory(crashReportCategory);
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   public void updateState51() {
      this.factor54 = -1.0E8F;
   }

   public void updateState50() {
      this.container2.applyEntityPlayer(this);
      this.container2 = this.container;
   }

   @Override
   public void applyIndex9(int count) {
      super.applyIndex9(count);
      this.count40 = -1;
   }

   @Override
   public void applyIndex8(int count) {
      super.applyIndex8(count);
      this.count40 = -1;
   }

   @Override
   public void updateState42() {
      this.auxiliaryListenerAdapter4.applyPacket(new S19PacketEntityStatus(this, (byte)9));
      super.updateState42();
   }

   @Override
   public void updateState5() {
      this.auxiliaryListenerAdapter4.applyPacket(new S2EPacketCloseWindow(this.container2.indexOffset2));
      this.updateState50();
   }

   @Override
   public void updateState43() {
      if (this.auxiliaryListenerAdapter4 != null) {
         this.auxiliaryListenerAdapter4.applyPacket(new S39PacketPlayerAbilities(this.invulnerableManager));
         this.updateState28();
      }
   }

   public void updateState52() {
      String labelText2 = this.worldObj
         .resolveBiomeIDIsOutOfBoundsManager(new BlockPos(MathHelper.ceiling_double_int(this.posX), 0, MathHelper.ceiling_double_int(this.posZ)))
         .labelText;
      ForwardingSet forwardingSet = (ForwardingSet)this.getStatFileWriterHandler()
         .resolvePreRenderInterceptor(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter26);
      if (forwardingSet == null) {
         forwardingSet = (ForwardingSet)this.getStatFileWriterHandler()
            .resolvePreRenderInterceptor(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter26, new ForwardingSet());
      }

      forwardingSet.add(labelText2);
      if (this.getStatFileWriterHandler().matchesAuxiliaryDuplicateStatIdMa(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter26)
         && forwardingSet.size() >= BiomeIDIsOutOfBoundsManager.set.size()) {
         HashSet set = Sets.newHashSet(BiomeIDIsOutOfBoundsManager.set);

         for (String text : (Iterable<String>)(Iterable<?>)forwardingSet) {
            Iterator iterator = set.iterator();

            while (iterator.hasNext()) {
               if (((BiomeIDIsOutOfBoundsManager)iterator.next()).labelText.equals(text)) {
                  iterator.remove();
               }
            }

            if (set.isEmpty()) {
               break;
            }
         }

         if (set.isEmpty()) {
            this.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter26);
         }
      }
   }

   public void updateState47() {
      if (!this.enabled29) {
         this.auxiliaryListenerAdapter4.applyPacket(new S2FPacketSetSlot(-1, -1, this.inventoryPlayer.getItemStack()));
      }
   }

   private void updateState49() {
      this.count33 = this.count33 % 100 + 1;
   }

   private boolean isActive27() {
      return this.vanillaRunnable.onWorldTick();
   }

   @Override
   public void applyEntity16(Entity entity) {
      this.getWorldServer().getSecondaryLoggerEntry().applyEntity2(this, new S0BPacketAnimation(entity, 5));
   }

   @Override
   public boolean isActive23() {
      return this.internalWorldEntry.getWorldSettingsGameType() == WorldSettingsGameType.SPECTATOR;
   }

   @Override
   public void updateState() {
      this.internalWorldEntry.updateState2();
      this.count36--;
      if (this.hurtResistantTime > 0) {
         this.hurtResistantTime--;
      }

      this.container2.detectAndSendChanges();
      if (!this.worldObj.state && !this.container2.matchesEntityPlayer(this)) {
         this.updateState5();
         this.container2 = this.container;
      }

      while (!this.list2.isEmpty()) {
         int count = Math.min(this.list2.size(), Integer.MAX_VALUE);
         int[] countArray = new int[count];
         Iterator iterator = this.list2.iterator();
         int count2 = 0;

         while (iterator.hasNext() && count2 < count) {
            countArray[count2++] = (Integer)iterator.next();
            iterator.remove();
         }

         this.auxiliaryListenerAdapter4.applyPacket(new S13PacketDestroyEntities(countArray));
      }

      if (!this.list.isEmpty()) {
         ArrayList list3 = Lists.newArrayList();
         Iterator iterator2 = this.list.iterator();
         ArrayList list4 = Lists.newArrayList();

         while (iterator2.hasNext() && list3.size() < 10) {
            InterpolationCollector interpolationCollector = (InterpolationCollector)iterator2.next();
            if (interpolationCollector != null) {
               if (this.worldObj.checkBlockPos12(new BlockPos(interpolationCollector.count3 << 4, 0, interpolationCollector.count2 << 4))) {
                  AlertNotificationSound loggerEntry = this.worldObj.getLoggerEntry2(interpolationCollector.count3, interpolationCollector.count2);
                  if (loggerEntry.isActive()) {
                     list3.add(loggerEntry);
                     list4.addAll(
                        ((WorldServer)this.worldObj).resolveList(interpolationCollector.count3 * 16, 0, interpolationCollector.count2 * 16, interpolationCollector.count3 * 16 + 16, 256, interpolationCollector.count2 * 16 + 16)
                     );
                     iterator2.remove();
                  }
               }
            } else {
               iterator2.remove();
            }
         }

         if (!list3.isEmpty()) {
            if (list3.size() == 1) {
               this.auxiliaryListenerAdapter4.applyPacket(new S21PacketChunkData((AlertNotificationSound)list3.get(0), true, 65535));
            } else {
               this.auxiliaryListenerAdapter4.applyPacket(new S26PacketMapChunkBulk(list3));
            }

            for (TileEntity tileEntity : (Iterable<TileEntity>)(Iterable<?>)list4) {
               this.applyTileEntity(tileEntity);
            }

            for (AlertNotificationSound alertNotificationSound : (Iterable<AlertNotificationSound>)(Iterable<?>)list3) {
               this.getWorldServer().getSecondaryLoggerEntry().applyEntityPlayerAdapter(this, alertNotificationSound);
            }
         }
      }

      Entity entity = this.getEntity2();
      if (entity != this) {
         if (!entity.canExecute()) {
            this.applyEntity32(this);
         } else {
            this.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
            this.vanillaRunnable.getMovingManager().applyEntityPlayerAdapter2(this);
            if (this.isSprinting()) {
               this.applyEntity32(this);
            }
         }
      }
   }

   public void applyEntity32(Entity entity) {
      Entity entity2 = this.getEntity2();
      this.entity5 = (Entity)(entity == null ? this : entity);
      if (entity2 != this.entity5) {
         this.auxiliaryListenerAdapter4.applyPacket(new S43PacketCamera(this.entity5));
         this.applyDoubleByDouble(this.entity5.posX, this.entity5.posY, this.entity5.posZ);
      }
   }

   @Override
   public void updateState32() {
      super.updateState32();
      this.auxiliaryListenerAdapter4.applyPacket(new S42PacketCombatEvent(this.getGenericManager(), MovementStateProperty.ENTER_COMBAT));
   }

   @Override
   public void updateState29() {
      super.updateState29();
      this.auxiliaryListenerAdapter4.applyPacket(new S42PacketCombatEvent(this.getGenericManager(), MovementStateProperty.END_COMBAT));
   }

   public EntityPlayerAdapter(VanillaRunnable vanillaRunnable2, WorldServer worldServer, GameProfile gameProfile, InternalWorldEntry internalWorldEntry2) {
      super(worldServer, gameProfile);
      internalWorldEntry2.entityPlayerAdapter = this;
      this.internalWorldEntry = internalWorldEntry2;
      BlockPos blockPos = worldServer.getBlockPos();
      if (!worldServer.secondaryFloatArrayEntry.isEnabled() && worldServer.getRandomSeedManager().getWorldSettingsGameType() != WorldSettingsGameType.ADVENTURE) {
         int count = Math.max(5, vanillaRunnable2.getCount6() - 6);
         int count2 = MathHelper.ceiling_double_int(worldServer.getInternalListEntry().computeValue((double)blockPos.getX(), (double)blockPos.getZ()));
         if (count2 < count) {
            count = count2;
         }

         if (count2 <= 1) {
            count = 1;
         }

         blockPos = worldServer.resolveBlockPos(blockPos.add(this.random.nextInt(count * 2) - count, 0, this.random.nextInt(count * 2) - count));
      }

      this.vanillaRunnable = vanillaRunnable2;
      this.statFileWriterHandler = vanillaRunnable2.getMovingManager().resolveStatFileWriterHandler(this);
      this.stepHeight = 0.0F;
      this.applyBlockPos(blockPos, 0.0F, 0.0F);

      while (!worldServer.getList2(this, this.getBoundingBox2()).isEmpty() && this.posY < 255.0) {
         this.processDouble2(this.posX, this.posY + 1.0, this.posZ);
      }
   }
}
