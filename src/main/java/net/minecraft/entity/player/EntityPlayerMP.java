package net.minecraft.entity.player;

import net.minecraft.world.WorldSettings;
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
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
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
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonSerializableSet;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityPlayerMP extends EntityPlayer implements ICrafting {
   private static final Logger LOGGER = LogManager.getLogger();
   private String translator = "en_US";
   public NetHandlerPlayServer playerNetServerHandler;
   public final MinecraftServer mcServer;
   public final ItemInWorldManager theItemInWorldManager;
   public double managedPosX;
   public double managedPosZ;
   public final List loadedChunks = Lists.newLinkedList();
   private final List destroyedItemsNetCache = Lists.newLinkedList();
   private final StatisticsFile statsFile;
   private float combinedHealth = Float.MIN_VALUE;
   private float lastHealth = -1.0E8F;
   private int lastFoodLevel = -99999999;
   private boolean wasHungry = true;
   private int lastExperience = -99999999;
   private int respawnInvulnerabilityTicks = 60;
   private EntityPlayer.EnumChatVisibility chatVisibility;
   private boolean chatColours = true;
   private long playerLastActiveTime = System.currentTimeMillis();
   private Entity spectatingEntity = null;
   private int currentWindowId;
   public boolean isChangingQuantityOnly;
   public int ping;
   public boolean playerConqueredTheEnd;

   @Override
   public void onFinishedPotionEffect(PotionEffect potionEffect) {
      super.onFinishedPotionEffect(potionEffect);
      this.playerNetServerHandler.sendPacket(new S1EPacketRemoveEntityEffect(this.getEntityId(), potionEffect));
   }

   public void loadResourcePack(String str, String str2) {
      this.playerNetServerHandler.sendPacket(new S48PacketResourcePackSend(str, str2));
   }

   @Override
   public void addChatMessage(IChatComponent component) {
      this.playerNetServerHandler.sendPacket(new S02PacketChat(component));
   }

   public void updateHeldItem() {
      if (!this.isChangingQuantityOnly) {
         this.playerNetServerHandler.sendPacket(new S2FPacketSetSlot(-1, -1, this.inventory.getItemStack()));
      }
   }

   @Override
   public void travelToDimension(int n) {
      if (this.dimension == 1 && n == 1) {
         this.triggerAchievement(AchievementList.theEnd2);
         this.entityWorld.removeEntity(this);
         this.playerConqueredTheEnd = true;
         this.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(4, 0.0F));
      } else {
         if (this.dimension == 0 && n == 1) {
            this.triggerAchievement(AchievementList.theEnd);
            BlockPos spawnCoordinate = this.mcServer.worldServerForDimension(n).getSpawnCoordinate();
            if (spawnCoordinate != null) {
               this.playerNetServerHandler.setPlayerLocation((double)spawnCoordinate.getX(), (double)spawnCoordinate.getY(), (double)spawnCoordinate.getZ(), 0.0F, 0.0F);
            }

            n = 1;
         } else {
            this.triggerAchievement(AchievementList.portal);
         }

         this.mcServer.getConfigurationManager().transferPlayerToDimension(this, n);
         this.lastExperience = -1;
         this.lastHealth = -1.0F;
         this.lastFoodLevel = -1;
      }
   }

   @Override
   public void sendEnterCombat() {
      super.sendEnterCombat();
      this.playerNetServerHandler.sendPacket(new S42PacketCombatEvent(this.getCombatTracker(), S42PacketCombatEvent.Event.ENTER_COMBAT));
   }

   public void removeEntity(Entity entity) {
      if (entity instanceof EntityPlayer) {
         this.playerNetServerHandler.sendPacket(new S13PacketDestroyEntities(new int[]{entity.getEntityId()}));
      } else {
         this.destroyedItemsNetCache.add(entity.getEntityId());
      }
   }

   @Override
   public void updateFallState(double d, boolean flag, Block block, BlockPos pos) {
   }

   @Override
   public void sendPlayerAbilities() {
      if (this.playerNetServerHandler != null) {
         this.playerNetServerHandler.sendPacket(new S39PacketPlayerAbilities(this.capabilities));
         this.updatePotionMetadata();
      }
   }

   @Override
   public boolean isSpectator() {
      return this.theItemInWorldManager.getGameType() == WorldSettings.GameType.SPECTATOR;
   }

   @Override
   public void takeStat(StatBase statBase) {
      if (statBase != null) {
         this.statsFile.unlockAchievement(this, statBase, 0);

         for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(this.getWorldScoreboard().getObjectivesFromCriteria(statBase.getObjectiveCriteria()))) {
            this.getWorldScoreboard().getValueFromObjective(this.getName(), scoreObjective).setScorePoints(0);
         }

         if (this.statsFile.hasUnsentAchievement()) {
            this.statsFile.sendStats(this);
         }
      }
   }

   @Override
   public boolean canAttackPlayer(EntityPlayer player) {
      return !this.canPlayersAttack() ? false : super.canAttackPlayer(player);
   }

   @Override
   public void sendSlotContents(Container container, int n, ItemStack stack) {
      if (!(container.getSlot(n) instanceof SlotCrafting) && !this.isChangingQuantityOnly) {
         this.playerNetServerHandler.sendPacket(new S2FPacketSetSlot(container.windowId, n, stack));
      }
   }

   public Entity getSpectatingEntity() {
      return (Entity)(this.spectatingEntity == null ? this : this.spectatingEntity);
   }

   private boolean canPlayersAttack() {
      return this.mcServer.isPVPEnabled();
   }

   @Override
   public void setPositionAndUpdate(double d, double d2, double d3) {
      this.playerNetServerHandler.setPlayerLocation(d, d2, d3, this.rotationYaw, this.rotationPitch);
   }

   @Override
   public void addChatComponentMessage(IChatComponent component) {
      this.playerNetServerHandler.sendPacket(new S02PacketChat(component));
   }

   private void sendTileEntityUpdate(TileEntity tileEntity) {
      if (tileEntity != null) {
         Packet descriptionPacket = tileEntity.getDescriptionPacket();
         if (descriptionPacket != null) {
            this.playerNetServerHandler.sendPacket(descriptionPacket);
         }
      }
   }

   @Override
   public void clonePlayer(EntityPlayer player, boolean flag) {
      super.clonePlayer(player, flag);
      this.lastExperience = -1;
      this.lastHealth = -1.0F;
      this.lastFoodLevel = -1;
      this.destroyedItemsNetCache.addAll(((EntityPlayerMP)player).destroyedItemsNetCache);
   }

   @Override
   public void onEnchantmentCritical(Entity entity) {
      this.getServerForPlayer().getEntityTracker().sendToTrackingAndSelf(this, new S0BPacketAnimation(entity, 5));
   }

   public long getLastActiveTime() {
      return this.playerLastActiveTime;
   }

   public WorldServer getServerForPlayer() {
      return (WorldServer)this.entityWorld;
   }

   public void setEntityActionState(float f, float f2, boolean flag, boolean flag2) {
      if (this.ridingEntity != null) {
         if (f >= -1.0F && f <= 1.0F) {
            this.moveStrafing = f;
         }

         if (f2 >= -1.0F && f2 <= 1.0F) {
            this.moveForward = f2;
         }

         this.jumping = flag;
         this.setSneaking(flag2);
      }
   }

   @Override
   public void setGameType(WorldSettings.GameType gameType) {
      this.theItemInWorldManager.setGameType(gameType);
      this.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(3, (float)gameType.getID()));
      if (gameType == WorldSettings.GameType.SPECTATOR) {
         this.mountEntity((Entity)null);
      } else {
         this.setSpectatingEntity(this);
      }

      this.sendPlayerAbilities();
      this.markPotionsDirty();
   }

   @Override
   public boolean isSpectatedByPlayer(EntityPlayerMP entityPlayerMP) {
      return entityPlayerMP.isSpectator() ? this.getSpectatingEntity() == this : (this.isSpectator() ? false : super.isSpectatedByPlayer(entityPlayerMP));
   }

   @Override
   public void onDeath(DamageSource damageSource) {
      if (this.entityWorld.getGameRules().getBoolean("showDeathMessages")) {
         Team team = this.getTeam();
         if (team == null || team.getDeathMessageVisibility() == Team.EnumVisible.ALWAYS) {
            this.mcServer.getConfigurationManager().sendChatMsg(this.getCombatTracker().getDeathMessage());
         } else if (team.getDeathMessageVisibility() == Team.EnumVisible.HIDE_FOR_OTHER_TEAMS) {
            this.mcServer.getConfigurationManager().sendMessageToAllTeamMembers(this, this.getCombatTracker().getDeathMessage());
         } else if (team.getDeathMessageVisibility() == Team.EnumVisible.HIDE_FOR_OWN_TEAM) {
            this.mcServer.getConfigurationManager().sendMessageToTeamOrEvryPlayer(this, this.getCombatTracker().getDeathMessage());
         }
      }

      if (!this.entityWorld.getGameRules().getBoolean("keepInventory")) {
         this.inventory.dropAllItems();
      }

      for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(this.entityWorld.getScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.deathCount))) {
         this.getWorldScoreboard().getValueFromObjective(this.getName(), scoreObjective).incrementScore();
      }

      EntityLivingBase attackingEntity = this.getAttackingEntity();
      if (attackingEntity != null) {
         EntityList.EntityEggInfo entityEggInfo = (EntityList.EntityEggInfo)EntityList.entityEggs.get(EntityList.getEntityID(attackingEntity));
         if (entityEggInfo != null) {
            this.triggerAchievement(entityEggInfo.entityKilledByStat);
         }

         attackingEntity.addToPlayerScore(this, this.scoreValue);
      }

      this.triggerAchievement(StatList.deathsStat);
      this.takeStat(StatList.timeSinceDeathStat);
      this.getCombatTracker().reset();
   }

   @Override
   public void updateCraftingInventory(Container container, List list) {
      this.playerNetServerHandler.sendPacket(new S30PacketWindowItems(container.windowId, list));
      this.playerNetServerHandler.sendPacket(new S2FPacketSetSlot(-1, -1, this.inventory.getItemStack()));
   }

   public void setPlayerHealthUpdated() {
      this.lastHealth = -1.0E8F;
   }

   public void markPlayerActive() {
      this.playerLastActiveTime = MinecraftServer.getCurrentTimeMillis();
   }

   @Override
   public void onChangedPotionEffect(PotionEffect potionEffect, boolean flag) {
      super.onChangedPotionEffect(potionEffect, flag);
      this.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(this.getEntityId(), potionEffect));
   }

   public void addSelfToInternalCraftingInventory() {
      this.openContainer.onCraftGuiOpened(this);
   }

   public void sendContainerToPlayer(Container container) {
      this.updateCraftingInventory(container, container.getInventory());
   }

   @Override
   public EntityPlayer.EnumStatus trySleep(BlockPos pos) {
      EntityPlayer.EnumStatus enumStatus = super.trySleep(pos);
      if (enumStatus == EntityPlayer.EnumStatus.OK) {
         S0APacketUseBed packet = new S0APacketUseBed(this, pos);
         this.getServerForPlayer().getEntityTracker().sendToAllTrackingEntity(this, packet);
         this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         this.playerNetServerHandler.sendPacket(packet);
      }

      return enumStatus;
   }

   public void setSpectatingEntity(Entity entity) {
      Entity spectatingEntity2 = this.getSpectatingEntity();
      this.spectatingEntity = (Entity)(entity == null ? this : entity);
      if (spectatingEntity2 != this.spectatingEntity) {
         this.playerNetServerHandler.sendPacket(new S43PacketCamera(this.spectatingEntity));
         this.setPositionAndUpdate(this.spectatingEntity.posX, this.spectatingEntity.posY, this.spectatingEntity.posZ);
      }
   }

   @Override
   public void wakeUpPlayer(boolean flag, boolean flag2, boolean flag3) {
      if (this.isPlayerSleeping()) {
         this.getServerForPlayer().getEntityTracker().sendToTrackingAndSelf(this, new S0BPacketAnimation(this, 2));
      }

      super.wakeUpPlayer(flag, flag2, flag3);
      if (this.playerNetServerHandler != null) {
         this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         if (!(this.mcServer.isDedicatedServer() && this.canPlayersAttack() && "fall".equals(damageSource.damageType)) && this.respawnInvulnerabilityTicks > 0 && damageSource != DamageSource.outOfWorld) {
            return false;
         } else {
            if (damageSource instanceof EntityDamageSource) {
               Entity entity = damageSource.getEntity();
               if (entity instanceof EntityPlayer && !this.canAttackPlayer((EntityPlayer)entity)) {
                  return false;
               }

               if (entity instanceof EntityArrow) {
                  EntityArrow entityArrow = (EntityArrow)entity;
                  if (entityArrow.shootingEntity instanceof EntityPlayer && !this.canAttackPlayer((EntityPlayer)entityArrow.shootingEntity)) {
                     return false;
                  }
               }
            }

            return super.attackEntityFrom(damageSource, f);
         }
      }
   }

   @Override
   public BlockPos getPosition() {
      return new BlockPos(this.posX, this.posY + 0.5, this.posZ);
   }

   public void onUpdateEntity() {
      try {
         super.onUpdate();

         for (int i = 0; i < this.inventory.getSizeInventory(); i++) {
            ItemStack stackInSlot = this.inventory.getStackInSlot(i);
            if (stackInSlot != null && stackInSlot.getItem().isMap()) {
               Packet packet = ((ItemMapBase)stackInSlot.getItem()).createMapDataPacket(stackInSlot, this.entityWorld, this);
               if (packet != null) {
                  this.playerNetServerHandler.sendPacket(packet);
               }
            }
         }

         if (this.getCurrentHealth() != this.lastHealth
            || this.lastFoodLevel != this.foodStats.getFoodLevel()
            || this.foodStats.getSaturationLevel() == 0.0F != this.wasHungry) {
            this.playerNetServerHandler.sendPacket(new S06PacketUpdateHealth(this.getCurrentHealth(), this.foodStats.getFoodLevel(), this.foodStats.getSaturationLevel()));
            this.lastHealth = this.getCurrentHealth();
            this.lastFoodLevel = this.foodStats.getFoodLevel();
            this.wasHungry = this.foodStats.getSaturationLevel() == 0.0F;
         }

         if (this.getCurrentHealth() + this.getAbsorptionAmount() != this.combinedHealth) {
            this.combinedHealth = this.getCurrentHealth() + this.getAbsorptionAmount();

            for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(this.getWorldScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.health))) {
               this.getWorldScoreboard().getValueFromObjective(this.getName(), scoreObjective).setScoreFromPlayers(Arrays.asList(this));
            }
         }

         if (this.experienceTotal != this.lastExperience) {
            this.lastExperience = this.experienceTotal;
            this.playerNetServerHandler.sendPacket(new S1FPacketSetExperience(this.experience, this.experienceTotal, this.experienceLevel));
         }

         if (this.ticksExisted % 20 * 5 == 0 && !this.getStatFile().hasAchievementUnlocked(AchievementList.exploreAllBiomes)) {
            this.updateBiomesExplored();
         }
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Ticking player");
         this.addEntityCrashInfo(crashReport.makeCategory("Player being ticked"));
         throw new ReportedException(crashReport);
      }
   }

   @Override
   public void openEditSign(TileEntitySign tileEntitySign) {
      tileEntitySign.setPlayer(this);
      this.playerNetServerHandler.sendPacket(new S36PacketSignEditorOpen(tileEntitySign.getPos()));
   }

   public StatisticsFile getStatFile() {
      return this.statsFile;
   }

   @Override
   public void removeExperienceLevel(int n) {
      super.removeExperienceLevel(n);
      this.lastExperience = -1;
   }

   @Override
   public void displayGUIBook(ItemStack stack) {
      if (stack.getItem() == Items.written_book) {
         this.playerNetServerHandler.sendPacket(new S3FPacketCustomPayload("MC|BOpen", new PacketBuffer(Unpooled.buffer())));
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      if (tag.hasKey("playerGameType", 99)) {
         if (MinecraftServer.getServer().getForceGamemode()) {
            this.theItemInWorldManager.setGameType(MinecraftServer.getServer().getGameType());
         } else {
            this.theItemInWorldManager.setGameType(WorldSettings.GameType.getByID(tag.getInteger("playerGameType")));
         }
      }
   }

   @Override
   public void sendEndCombat() {
      super.sendEndCombat();
      this.playerNetServerHandler.sendPacket(new S42PacketCombatEvent(this.getCombatTracker(), S42PacketCombatEvent.Event.END_COMBAT));
   }

   @Override
   public void updatePotionMetadata() {
      if (this.isSpectator()) {
         this.resetPotionEffectMetadata();
         this.setInvisible(true);
      } else {
         super.updatePotionMetadata();
      }

      this.getServerForPlayer().getEntityTracker().updateVisibility(this);
   }

   @Override
   public void mountEntity(Entity entity) {
      Entity ridingEntity2 = this.ridingEntity;
      super.mountEntity(entity);
      if (entity != ridingEntity2) {
         this.playerNetServerHandler.sendPacket(new S1BPacketEntityAttach(0, this, this.ridingEntity));
         this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
      }
   }

   public IChatComponent getTabListDisplayName() {
      return null;
   }

   @Override
   public void onCriticalHit(Entity entity) {
      this.getServerForPlayer().getEntityTracker().sendToTrackingAndSelf(this, new S0BPacketAnimation(entity, 4));
   }

   @Override
   public void displayGui(IInteractionObject interactionObject) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, interactionObject.getGuiID(), interactionObject.getDisplayName()));
      this.openContainer = interactionObject.createContainer(this.inventory, this);
      this.openContainer.windowId = this.currentWindowId;
      this.openContainer.onCraftGuiOpened(this);
   }

   @Override
   public void onNewPotionEffect(PotionEffect potionEffect) {
      super.onNewPotionEffect(potionEffect);
      this.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(this.getEntityId(), potionEffect));
   }

   @Override
   public void onUpdate() {
      this.theItemInWorldManager.updateBlockRemoving();
      this.respawnInvulnerabilityTicks--;
      if (this.hurtResistantTime > 0) {
         this.hurtResistantTime--;
      }

      this.openContainer.detectAndSendChanges();
      if (!this.entityWorld.isRemote && !this.openContainer.canInteractWith(this)) {
         this.closeScreen();
         this.openContainer = this.inventoryContainer;
      }

      while (!this.destroyedItemsNetCache.isEmpty()) {
         int n = Math.min(this.destroyedItemsNetCache.size(), Integer.MAX_VALUE);
         int[] ints = new int[n];
         Iterator it = this.destroyedItemsNetCache.iterator();
         int n2 = 0;

         while (it.hasNext() && n2 < n) {
            ints[n2++] = (Integer)it.next();
            it.remove();
         }

         this.playerNetServerHandler.sendPacket(new S13PacketDestroyEntities(ints));
      }

      if (!this.loadedChunks.isEmpty()) {
         ArrayList list = Lists.newArrayList();
         Iterator it2 = this.loadedChunks.iterator();
         ArrayList list2 = Lists.newArrayList();

         while (it2.hasNext() && list.size() < 10) {
            ChunkCoordIntPair chunkCoordIntPair = (ChunkCoordIntPair)it2.next();
            if (chunkCoordIntPair != null) {
               if (this.entityWorld.isBlockLoaded(new BlockPos(chunkCoordIntPair.chunkXPos << 4, 0, chunkCoordIntPair.chunkZPos << 4))) {
                  Chunk chunkFromChunkCoords = this.entityWorld.getChunkFromChunkCoords(chunkCoordIntPair.chunkXPos, chunkCoordIntPair.chunkZPos);
                  if (chunkFromChunkCoords.isPopulated()) {
                     list.add(chunkFromChunkCoords);
                     list2.addAll(
                        ((WorldServer)this.entityWorld)
                           .getTileEntitiesIn(chunkCoordIntPair.chunkXPos * 16, 0, chunkCoordIntPair.chunkZPos * 16, chunkCoordIntPair.chunkXPos * 16 + 16, 256, chunkCoordIntPair.chunkZPos * 16 + 16)
                     );
                     it2.remove();
                  }
               }
            } else {
               it2.remove();
            }
         }

         if (!list.isEmpty()) {
            if (list.size() == 1) {
               this.playerNetServerHandler.sendPacket(new S21PacketChunkData((Chunk)list.get(0), true, 65535));
            } else {
               this.playerNetServerHandler.sendPacket(new S26PacketMapChunkBulk(list));
            }

            for (TileEntity tileEntity : (Iterable<TileEntity>)(list2)) {
               this.sendTileEntityUpdate(tileEntity);
            }

            for (Chunk chunk : (Iterable<Chunk>)(list)) {
               this.getServerForPlayer().getEntityTracker().sendLeashedEntitiesInChunk(this, chunk);
            }
         }
      }

      Entity spectatingEntity2 = this.getSpectatingEntity();
      if (spectatingEntity2 != this) {
         if (!spectatingEntity2.isEntityAlive()) {
            this.setSpectatingEntity(this);
         } else {
            this.setPositionAndRotation(spectatingEntity2.posX, spectatingEntity2.posY, spectatingEntity2.posZ, spectatingEntity2.rotationYaw, spectatingEntity2.rotationPitch);
            this.mcServer.getConfigurationManager().serverUpdateMountedMovingPlayer(this);
            if (this.isSneaking()) {
               this.setSpectatingEntity(this);
            }
         }
      }
   }

   @Override
   public void closeScreen() {
      this.playerNetServerHandler.sendPacket(new S2EPacketCloseWindow(this.openContainer.windowId));
      this.closeContainer();
   }

   private void getNextWindowId() {
      this.currentWindowId = this.currentWindowId % 100 + 1;
   }

   @Override
   public void setItemInUse(ItemStack stack, int n) {
      super.setItemInUse(stack, n);
      if (stack != null && stack.getItem() != null && stack.getItem().getItemUseAction(stack) == EnumAction.EAT) {
         this.getServerForPlayer().getEntityTracker().sendToTrackingAndSelf(this, new S0BPacketAnimation(this, 3));
      }
   }

   @Override
   public void displayVillagerTradeGui(IMerchant merchant) {
      this.getNextWindowId();
      this.openContainer = new ContainerMerchant(this.inventory, merchant, this.entityWorld);
      this.openContainer.windowId = this.currentWindowId;
      this.openContainer.onCraftGuiOpened(this);
      InventoryMerchant merchantInventory = ((ContainerMerchant)this.openContainer).getMerchantInventory();
      IChatComponent displayName = merchant.getDisplayName();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, "minecraft:villager", displayName, merchantInventory.getSizeInventory()));
      MerchantRecipeList recipes = merchant.getRecipes(this);
      if (recipes != null) {
         PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
         buf.writeInt(this.currentWindowId);
         recipes.writeToBuf(buf);
         this.playerNetServerHandler.sendPacket(new S3FPacketCustomPayload("MC|TrList", buf));
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("playerGameType", this.theItemInWorldManager.getGameType().getID());
   }

   @Override
   public void displayGUIChest(IInventory inventory2) {
      if (this.openContainer != this.inventoryContainer) {
         this.closeScreen();
      }

      if (inventory2 instanceof ILockableContainer) {
         ILockableContainer lockableContainer = (ILockableContainer)inventory2;
         if (lockableContainer.isLocked() && !this.canOpen(lockableContainer.getLockCode()) && !this.isSpectator()) {
            this.playerNetServerHandler
               .sendPacket(new S02PacketChat(new ChatComponentTranslation("container.isLocked", new Object[]{inventory2.getDisplayName()}), (byte)2));
            this.playerNetServerHandler.sendPacket(new S29PacketSoundEffect("random.door_close", this.posX, this.posY, this.posZ, 1.0F, 1.0F));
            return;
         }
      }

      this.getNextWindowId();
      if (inventory2 instanceof IInteractionObject) {
         this.playerNetServerHandler
            .sendPacket(new S2DPacketOpenWindow(this.currentWindowId, ((IInteractionObject)inventory2).getGuiID(), inventory2.getDisplayName(), inventory2.getSizeInventory()));
         this.openContainer = ((IInteractionObject)inventory2).createContainer(this.inventory, this);
      } else {
         this.playerNetServerHandler
            .sendPacket(new S2DPacketOpenWindow(this.currentWindowId, "minecraft:container", inventory2.getDisplayName(), inventory2.getSizeInventory()));
         this.openContainer = new ContainerChest(this.inventory, inventory2, this);
      }

      this.openContainer.windowId = this.currentWindowId;
      this.openContainer.onCraftGuiOpened(this);
   }

   public void mountEntityAndWakeUp() {
      if (this.riddenByEntity != null) {
         this.riddenByEntity.mountEntity(this);
      }

      if (this.sleeping) {
         this.wakeUpPlayer(true, false, false);
      }
   }

   public EntityPlayer.EnumChatVisibility getChatVisibility() {
      return this.chatVisibility;
   }

   public void closeContainer() {
      this.openContainer.onContainerClosed(this);
      this.openContainer = this.inventoryContainer;
   }

   @Override
   public void sendProgressBarUpdate(Container container, int n, int n2) {
      this.playerNetServerHandler.sendPacket(new S31PacketWindowProperty(container.windowId, n, n2));
   }

   public void handleClientSettings(C15PacketClientSettings packet) {
      this.translator = packet.getLang();
      this.chatVisibility = packet.getChatVisibility();
      this.chatColours = packet.isColorsEnabled();
      this.getDataWatcher().updateObject(10, (byte)packet.getModelPartFlags());
   }

   @Override
   public boolean canCommandSenderUseCommand(int n, String str) {
      if ("seed".equals(str) && !this.mcServer.isDedicatedServer()) {
         return true;
      } else if (!"tell".equals(str) && !"help".equals(str) && !"me".equals(str) && !"trigger".equals(str)) {
         if (this.mcServer.getConfigurationManager().canSendCommands(this.getGameProfile())) {
            UserListOpsEntry entry = (UserListOpsEntry)this.mcServer.getConfigurationManager().getOppedPlayers().getEntry(this.getGameProfile());
            return entry != null ? entry.getPermissionLevel() >= n : this.mcServer.getOpPermissionLevel() >= n;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   @Override
   public void onItemUseFinish() {
      this.playerNetServerHandler.sendPacket(new S19PacketEntityStatus(this, (byte)9));
      super.onItemUseFinish();
   }

   @Override
   public void sendAllWindowProperties(Container container, IInventory inventory2) {
      for (int i = 0; i < inventory2.getFieldCount(); i++) {
         this.playerNetServerHandler.sendPacket(new S31PacketWindowProperty(container.windowId, i, inventory2.getField(i)));
      }
   }

   @Override
   public void addStat(StatBase statBase, int n) {
      if (statBase != null) {
         this.statsFile.increaseStat(this, statBase, n);

         for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(this.getWorldScoreboard().getObjectivesFromCriteria(statBase.getObjectiveCriteria()))) {
            this.getWorldScoreboard().getValueFromObjective(this.getName(), scoreObjective).increseScore(n);
         }

         if (this.statsFile.hasUnsentAchievement()) {
            this.statsFile.sendStats(this);
         }
      }
   }

   public EntityPlayerMP(MinecraftServer minecraftServer, WorldServer world, GameProfile profile, ItemInWorldManager itemInWorldManager) {
      super(world, profile);
      itemInWorldManager.thisPlayerMP = this;
      this.theItemInWorldManager = itemInWorldManager;
      BlockPos spawnPoint = world.getSpawnPoint();
      if (!world.provider.getHasNoSky() && world.getWorldInfo().getGameType() != WorldSettings.GameType.ADVENTURE) {
         int n = Math.max(5, minecraftServer.getSpawnProtectionSize() - 6);
         int n2 = MathHelper.floor_double(world.getWorldBorder().getClosestDistance((double)spawnPoint.getX(), (double)spawnPoint.getZ()));
         if (n2 < n) {
            n = n2;
         }

         if (n2 <= 1) {
            n = 1;
         }

         spawnPoint = world.getTopSolidOrLiquidBlock(spawnPoint.add(this.rand.nextInt(n * 2) - n, 0, this.rand.nextInt(n * 2) - n));
      }

      this.mcServer = minecraftServer;
      this.statsFile = minecraftServer.getConfigurationManager().getPlayerStatsFile(this);
      this.stepHeight = 0.0F;
      this.moveToBlockPosAndAngles(spawnPoint, 0.0F, 0.0F);

      while (!world.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && this.posY < 255.0) {
         this.setPosition(this.posX, this.posY + 1.0, this.posZ);
      }
   }

   @Override
   public void displayGUIHorse(EntityHorse entityHorse, IInventory inventory2) {
      if (this.openContainer != this.inventoryContainer) {
         this.closeScreen();
      }

      this.getNextWindowId();
      this.playerNetServerHandler
         .sendPacket(new S2DPacketOpenWindow(this.currentWindowId, "EntityHorse", inventory2.getDisplayName(), inventory2.getSizeInventory(), entityHorse.getEntityId()));
      this.openContainer = new ContainerHorseInventory(this.inventory, inventory2, entityHorse, this);
      this.openContainer.windowId = this.currentWindowId;
      this.openContainer.onCraftGuiOpened(this);
   }

   public void handleFalling(double d, boolean flag) {
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.posY - 0.2F);
      int n3 = MathHelper.floor_double(this.posZ);
      BlockPos pos = new BlockPos(n, n2, n3);
      Block block = this.entityWorld.getBlockState(pos).getBlock();
      if (block.getMaterial() == Material.air) {
         Block block2 = this.entityWorld.getBlockState(pos.down()).getBlock();
         if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
            pos = pos.down();
            block = this.entityWorld.getBlockState(pos).getBlock();
         }
      }

      super.updateFallState(d, flag, block, pos);
   }

   public void updateBiomesExplored() {
      String biomeName = this.entityWorld.getBiomeGenForCoords(new BlockPos(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ))).biomeName;
      JsonSerializableSet progress = (JsonSerializableSet)this.getStatFile().getProgress(AchievementList.exploreAllBiomes);
      if (progress == null) {
         progress = (JsonSerializableSet)this.getStatFile().setProgress(AchievementList.exploreAllBiomes, new JsonSerializableSet());
      }

      progress.add(biomeName);
      if (this.getStatFile().canUnlockAchievement(AchievementList.exploreAllBiomes) && progress.size() >= BiomeGenBase.explorationBiomesList.size()) {
         HashSet set = Sets.newHashSet(BiomeGenBase.explorationBiomesList);

         for (String str : (Iterable<String>)(progress)) {
            Iterator it = set.iterator();

            while (it.hasNext()) {
               if (((BiomeGenBase)it.next()).biomeName.equals(str)) {
                  it.remove();
               }
            }

            if (set.isEmpty()) {
               break;
            }
         }

         if (set.isEmpty()) {
            this.triggerAchievement(AchievementList.exploreAllBiomes);
         }
      }
   }

   @Override
   public void onItemPickup(Entity entity, int n) {
      super.onItemPickup(entity, n);
      this.openContainer.detectAndSendChanges();
   }

   @Override
   public void attackTargetEntityWithCurrentItem(Entity entity) {
      if (this.theItemInWorldManager.getGameType() == WorldSettings.GameType.SPECTATOR) {
         this.setSpectatingEntity(entity);
      } else {
         super.attackTargetEntityWithCurrentItem(entity);
      }
   }

   public String getPlayerIP() {
      String str = this.playerNetServerHandler.netManager.getRemoteAddress().toString();
      str = str.substring(str.indexOf("/") + 1);
      return str.substring(0, str.indexOf(":"));
   }

   @Override
   public void addExperienceLevel(int n) {
      super.addExperienceLevel(n);
      this.lastExperience = -1;
   }
}
