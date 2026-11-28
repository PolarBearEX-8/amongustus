package net.minecraft.entity.player;

import net.minecraft.world.WorldSettings;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.augustus.events.listeners.SprintListener;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.AttackSlowdownListener;
import net.augustus.events.EventAttackSlowdown;
import net.augustus.events.AttackSlowdownStage;
import net.augustus.events.SprintEventType;
import net.augustus.events.listeners.NoClipListener;
import net.augustus.Augustus;
import net.augustus.events.EventSprint;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;

public abstract class EntityPlayer extends EntityLivingBase {
   private String customDisplayName = "";
   public boolean isCustomPlayer = false;
   public InventoryPlayer inventory = new InventoryPlayer(this);
   private InventoryEnderChest theInventoryEnderChest = new InventoryEnderChest();
   public Container inventoryContainer;
   public Container openContainer;
   public FoodStats foodStats = new FoodStats();
   public int flyToggleTimer;
   public float prevCameraYaw;
   public float cameraYaw;
   public int xpCooldown;
   public double prevChasingPosX;
   public double prevChasingPosY;
   public double prevChasingPosZ;
   public double chasingPosX;
   public double chasingPosY;
   public double chasingPosZ;
   public boolean sleeping;
   public BlockPos playerLocation;
   private int sleepTimer;
   public float renderOffsetX;
   public float renderOffsetY;
   public float renderOffsetZ;
   private BlockPos spawnChunk;
   private boolean spawnForced;
   private BlockPos startMinecartRidingCoordinate;
   public PlayerCapabilities capabilities = new PlayerCapabilities();
   public int experienceLevel;
   public int experienceTotal;
   public float experience;
   private int xpSeed;
   private ItemStack itemInUse;
   private int itemInUseCount;
   public float speedOnGround = 0.1F;
   public float speedInAir = 0.02F;
   private int lastXPSound;
   private final GameProfile gameProfile;
   private boolean hasReducedDebug = false;
   public EntityFishHook fishEntity;

   @Override
   public String getDisplayNameString() {
      return ScorePlayerTeam.formatPlayerName(this.getTeam(), this.getName()) + EnumChatFormatting.RESET;
   }

   @Override
   public double getYOffset() {
      return -0.35;
   }

   public int getScore() {
      return this.dataWatcher.getWatchableObjectInt(18);
   }

   public boolean shouldHeal() {
      return this.getCurrentHealth() > 0.0F && this.getCurrentHealth() < this.getMaxHealth();
   }

   public void openEditSign(TileEntitySign tileEntitySign) {
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public float getBedOrientationInDegrees() {
      if (this.playerLocation != null) {
         IBlockState blockState = this.entityWorld.getBlockState(this.playerLocation);
         if (!blockState.hasProperty(BlockDirectional.FACING)) {
            return 0.0F;
         }

         EnumFacing value = (EnumFacing)blockState.getValue(BlockDirectional.FACING);
         switch (value) {
            case SOUTH:
               return 90.0F;
            case NORTH:
               return 270.0F;
            case WEST:
               return 0.0F;
            case EAST:
               return 180.0F;
         }
      }

      return 0.0F;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.10000000149011612);
   }

   @Override
   public boolean replaceItemInInventory(int n, ItemStack stack) {
      if (n >= 0 && n < this.inventory.mainInventory.length) {
         this.inventory.setInventorySlotContents(n, stack);
         return true;
      } else {
         int n2 = n - 100;
         if (n2 >= 0 && n2 < this.inventory.armorInventory.length) {
            int n3 = n2 + 1;
            if (stack != null && stack.getItem() != null) {
               if (stack.getItem() instanceof ItemArmor) {
                  if (EntityLiving.getArmorPosition(stack) != n3) {
                     return false;
                  }
               } else if (n3 != 4 || stack.getItem() != Items.skull && !(stack.getItem() instanceof ItemBlock)) {
                  return false;
               }
            }

            this.inventory.setInventorySlotContents(n2 + this.inventory.mainInventory.length, stack);
            return true;
         } else {
            int n4 = n - 200;
            if (n4 >= 0 && n4 < this.theInventoryEnderChest.getSizeInventory()) {
               this.theInventoryEnderChest.setInventorySlotContents(n4, stack);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   public void setSpeedOnGround(float f) {
      this.speedOnGround = f;
   }

   public void wakeUpPlayer(boolean flag, boolean flag2, boolean flag3) {
      this.setSize(0.6F, 1.8F);
      IBlockState blockState = this.entityWorld.getBlockState(this.playerLocation);
      if (this.playerLocation != null && blockState.getBlock() == Blocks.bed) {
         this.entityWorld.setBlockState(this.playerLocation, blockState.withProperty(BlockBed.OCCUPIED, Boolean.FALSE), 4);
         BlockPos safeExitLocation = BlockBed.getSafeExitLocation(this.entityWorld, this.playerLocation, 0);
         if (safeExitLocation == null) {
            safeExitLocation = this.playerLocation.up();
         }

         this.setPosition((double)((float)safeExitLocation.getX() + 0.5F), (double)((float)safeExitLocation.getY() + 0.1F), (double)((float)safeExitLocation.getZ() + 0.5F));
      }

      this.sleeping = false;
      if (!this.entityWorld.isRemote && flag2) {
         this.entityWorld.updateAllPlayersSleepingFlag();
      }

      this.sleepTimer = flag ? 0 : 100;
      if (flag3) {
         this.setSpawnPoint(this.playerLocation, false);
      }
   }

   @Override
   public ItemStack[] getInventory() {
      return this.inventory.armorInventory;
   }

   public boolean hasReducedDebug() {
      return !Augustus.getInstance().getModuleManager().fixes.isNoReducedDebugInfoEnabled() && this.hasReducedDebug;
   }

   @Override
   public void updateRidden() {
      if (!this.entityWorld.isRemote && this.isSneaking()) {
         this.mountEntity((Entity)null);
         this.setSneaking(false);
      } else {
         double posX2 = this.posX;
         double posY2 = this.posY;
         double posZ2 = this.posZ;
         float rotationYaw2 = this.rotationYaw;
         float rotationPitch2 = this.rotationPitch;
         super.updateRidden();
         this.prevCameraYaw = this.cameraYaw;
         this.cameraYaw = 0.0F;
         this.addMountedMovementStat(this.posX - posX2, this.posY - posY2, this.posZ - posZ2);
         if (this.ridingEntity instanceof EntityPig) {
            this.rotationPitch = rotationPitch2;
            this.rotationYaw = rotationYaw2;
            this.renderYawOffset = ((EntityPig)this.ridingEntity).renderYawOffset;
         }
      }
   }

   public void setCustomDisplayName(String str) {
      this.customDisplayName = str;
   }

   @Override
   public void onUpdate() {
      this.noClip = this.isSpectator();
      if (this instanceof EntityPlayerSP) {
         NoClipListener.postNoClip();
      }

      if (this.isSpectator()) {
         this.onGround = false;
      }

      if (this.itemInUse != null) {
         ItemStack currentItem = this.inventory.getCurrentItem();
         if (currentItem == this.itemInUse) {
            if (this.itemInUseCount <= 25 && this.itemInUseCount % 4 == 0) {
               this.updateItemUse(currentItem, 5);
            }

            if (--this.itemInUseCount == 0 && !this.entityWorld.isRemote) {
               this.onItemUseFinish();
            }
         } else {
            if (this instanceof EntityPlayerSP) {
               ClientManagers.moduleManager.killAura.itemUseCleared = true;
            }

            this.clearItemInUse();
         }
      }

      if (this.xpCooldown > 0) {
         this.xpCooldown--;
      }

      if (this.isPlayerSleeping()) {
         this.sleepTimer++;
         if (this.sleepTimer > 100) {
            this.sleepTimer = 100;
         }

         if (!this.entityWorld.isRemote) {
            if (!this.isInBed()) {
               this.wakeUpPlayer(true, true, false);
            } else if (this.entityWorld.isDaytime()) {
               this.wakeUpPlayer(false, true, true);
            }
         }
      } else if (this.sleepTimer > 0) {
         this.sleepTimer++;
         if (this.sleepTimer >= 110) {
            this.sleepTimer = 0;
         }
      }

      if (this instanceof EntityPlayerSP) {
         ((EntityPlayerSP)this).ticksSinceLastSwing++;
      }

      super.onUpdate();
      if (!this.entityWorld.isRemote && this.openContainer != null && !this.openContainer.canInteractWith(this)) {
         this.closeScreen();
         this.openContainer = this.inventoryContainer;
      }

      if (this.isBurning() && this.capabilities.disableDamage) {
         this.extinguish();
      }

      this.prevChasingPosX = this.chasingPosX;
      this.prevChasingPosY = this.chasingPosY;
      this.prevChasingPosZ = this.chasingPosZ;
      double d = this.posX - this.chasingPosX;
      double d2 = this.posY - this.chasingPosY;
      double d3 = this.posZ - this.chasingPosZ;
      double d4 = 10.0;
      if (d > d4) {
         this.prevChasingPosX = this.chasingPosX = this.posX;
      }

      if (d3 > d4) {
         this.prevChasingPosZ = this.chasingPosZ = this.posZ;
      }

      if (d2 > d4) {
         this.prevChasingPosY = this.chasingPosY = this.posY;
      }

      if (d < -d4) {
         this.prevChasingPosX = this.chasingPosX = this.posX;
      }

      if (d3 < -d4) {
         this.prevChasingPosZ = this.chasingPosZ = this.posZ;
      }

      if (d2 < -d4) {
         this.prevChasingPosY = this.chasingPosY = this.posY;
      }

      this.chasingPosX += d * 0.25;
      this.chasingPosZ += d3 * 0.25;
      this.chasingPosY += d2 * 0.25;
      if (this.ridingEntity == null) {
         this.startMinecartRidingCoordinate = null;
      }

      if (!this.entityWorld.isRemote) {
         this.foodStats.onUpdate(this);
         this.triggerAchievement(StatList.minutesPlayedStat);
         if (this.isEntityAlive()) {
            this.triggerAchievement(StatList.timeSinceDeathStat);
         }
      }

      double d5 = MathHelper.clamp_double(this.posX, -2.9999999E7, 2.9999999E7);
      double d6 = MathHelper.clamp_double(this.posZ, -2.9999999E7, 2.9999999E7);
      if (d5 != this.posX || d6 != this.posZ) {
         this.setPosition(d5, this.posY, d6);
      }
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 9) {
         this.onItemUseFinish();
      } else if (b == 23) {
         this.hasReducedDebug = false;
      } else if (b == 22) {
         this.hasReducedDebug = true;
      } else {
         super.handleStatusUpdate(b);
      }
   }

   public int getSleepTimer() {
      return this.sleepTimer;
   }

   public static UUID getUUID(GameProfile profile) {
      UUID id = profile.getId();
      if (id == null) {
         id = getOfflineUUID(profile.getName());
      }

      return id;
   }

   @Override
   public void setInWeb() {
      if (!this.capabilities.isFlying) {
         super.setInWeb();
      }
   }

   public boolean canEat(boolean flag) {
      return (flag || this.foodStats.needFood()) && !this.capabilities.disableDamage;
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else if (this.capabilities.disableDamage && !damageSource.canHarmInCreative()) {
         return false;
      } else {
         this.age = 0;
         if (this.getCurrentHealth() <= 0.0F) {
            return false;
         } else {
            if (this.isPlayerSleeping() && !this.entityWorld.isRemote) {
               this.wakeUpPlayer(true, true, false);
            }

            if (damageSource.isDifficultyScaled()) {
               if (this.entityWorld.getDifficulty() == EnumDifficulty.PEACEFUL) {
                  f = 0.0F;
               }

               if (this.entityWorld.getDifficulty() == EnumDifficulty.EASY) {
                  f = f / 2.0F + 1.0F;
               }

               if (this.entityWorld.getDifficulty() == EnumDifficulty.HARD) {
                  f = f * 3.0F / 2.0F;
               }
            }

            if (f == 0.0F) {
               return false;
            } else {
               Entity entity = damageSource.getEntity();
               if (entity instanceof EntityArrow && ((EntityArrow)entity).shootingEntity != null) {
                  entity = ((EntityArrow)entity).shootingEntity;
               }

               return super.attackEntityFrom(damageSource, f);
            }
         }
      }
   }

   public void addExperience(int n) {
      this.addScore(n);
      int n2 = 2147483647 - this.experienceTotal;
      if (n > n2) {
         n = n2;
      }

      this.experience = this.experience + (float)n / (float)this.xpBarCap();

      for (this.experienceTotal += n; this.experience >= 1.0F; this.experience = this.experience / (float)this.xpBarCap()) {
         this.experience = (this.experience - 1.0F) * (float)this.xpBarCap();
         this.addExperienceLevel(1);
      }
   }

   public boolean canHarvestBlock(Block block) {
      return this.inventory.canHeldItemHarvest(block);
   }

   @Override
   public String getName() {
      return this.gameProfile.getName();
   }

   public float getArmorVisibility() {
      int n = 0;

      for (ItemStack stack : this.inventory.armorInventory) {
         if (stack != null) {
            n++;
         }
      }

      return (float)n / (float)this.inventory.armorInventory.length;
   }

   @Override
   public String getDeathSound() {
      return "game.player.die";
   }

   @Override
   public void onLivingUpdate() {
      if (this.flyToggleTimer > 0) {
         this.flyToggleTimer--;
      }

      if (this.entityWorld.getDifficulty() == EnumDifficulty.PEACEFUL && this.entityWorld.getGameRules().getBoolean("naturalRegeneration")) {
         if (this.getCurrentHealth() < this.getMaxHealth() && this.ticksExisted % 20 == 0) {
            this.heal(1.0F);
         }

         if (this.foodStats.needFood() && this.ticksExisted % 10 == 0) {
            this.foodStats.setFoodLevel(this.foodStats.getFoodLevel() + 1);
         }
      }

      this.inventory.decrementAnimations();
      this.prevCameraYaw = this.cameraYaw;
      super.onLivingUpdate();
      IAttributeInstance entityAttribute = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      if (!this.entityWorld.isRemote) {
         entityAttribute.setBaseValue((double)this.capabilities.getWalkSpeed());
      }

      this.jumpMovementFactor = this.speedInAir;
      EventSprint event = SprintListener.postSprint(SprintEventType.JUMP_SPRINT);
      if (this.isSprinting() || event.isOmniJumpSprint()) {
         this.jumpMovementFactor = (float)((double)this.jumpMovementFactor + (double)this.speedInAir * 0.3);
      }

      this.setAIMoveSpeed((float)entityAttribute.getAttributeValue());
      float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      float f2 = (float)(Math.atan(-this.motionY * 0.20000000298023224) * 15.0);
      if (f > 0.1F) {
         f = 0.1F;
      }

      if (!this.onGround || this.getCurrentHealth() <= 0.0F) {
         f = 0.0F;
      }

      if (this.onGround || this.getCurrentHealth() <= 0.0F) {
         f2 = 0.0F;
      }

      this.cameraYaw = this.cameraYaw + (f - this.cameraYaw) * 0.4F;
      this.cameraPitch = this.cameraPitch + (f2 - this.cameraPitch) * 0.8F;
      if (this.getCurrentHealth() > 0.0F && !this.isSpectator()) {
         Object obj = null;
         if (this.ridingEntity != null && !this.ridingEntity.isDead) {
            obj = this.getEntityBoundingBox().union(this.ridingEntity.getEntityBoundingBox()).expand(1.0, 0.0, 1.0);
         } else {
            obj = this.getEntityBoundingBox().expand(1.0, 0.5, 1.0);
         }

         List list = this.entityWorld.getEntitiesWithinAABBExcludingEntity(this, (AxisAlignedBB)obj);

         for (int i = 0; i < list.size(); i++) {
            Entity entity = (Entity)list.get(i);
            if (!entity.isDead) {
               this.collideWithPlayer(entity);
            }
         }
      }
   }

   public static UUID getOfflineUUID(String str) {
      return UUID.nameUUIDFromBytes(("OfflinePlayer:" + str).getBytes(Charsets.UTF_8));
   }

   public EntityPlayer(World world, GameProfile profile) {
      super(world);
      this.entityUniqueID = getUUID(profile);
      this.gameProfile = profile;
      this.inventoryContainer = new ContainerPlayer(this.inventory, !world.isRemote, this, false);
      this.openContainer = this.inventoryContainer;
      BlockPos spawnPoint = world.getSpawnPoint();
      this.setLocationAndAngles((double)spawnPoint.getX() + 0.5, (double)(spawnPoint.getY() + 1), (double)spawnPoint.getZ() + 0.5, 0.0F, 0.0F);
      this.unused180 = 180.0F;
      this.fireResistance = 20;
   }

   @Override
   public void damageEntity(DamageSource damageSource, float f) {
      if (!this.isEntityInvulnerable(damageSource)) {
         if (!damageSource.isUnblockable() && this.isBlocking() && f > 0.0F) {
            f = (1.0F + f) * 0.5F;
         }

         f = this.applyArmorCalculations(damageSource, f);
         f = this.applyPotionDamageCalculations(damageSource, f);
         float f2 = Math.max(f - this.getAbsorptionAmount(), 0.0F);
         this.setAbsorptionAmount(this.getAbsorptionAmount() - (f - f2));
         if (f2 != 0.0F) {
            this.addExhaustion(damageSource.getHungerDamage());
            float currentHealth = this.getCurrentHealth();
            this.setHealth(this.getCurrentHealth() - f2);
            this.getCombatTracker().trackDamage(damageSource, currentHealth, f2);
            if (f2 < 3.4028235E37F) {
               this.addStat(StatList.damageTakenStat, Math.round(f2 * 10.0F));
            }
         }
      }
   }

   @Override
   public void setAbsorptionAmount(float f) {
      if (f < 0.0F) {
         f = 0.0F;
      }

      this.getDataWatcher().updateObject(17, f);
   }

   public ItemStack getCurrentEquippedItem() {
      return this.inventory.getCurrentItem();
   }

   public void setGameType(WorldSettings.GameType gameType) {
   }

   @Override
   public void damageArmor(float f) {
      this.inventory.damageArmor(f);
   }

   public void displayVillagerTradeGui(IMerchant merchant) {
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, (byte)0);
      this.dataWatcher.addObject(17, 0.0F);
      this.dataWatcher.addObject(18, 0);
      this.dataWatcher.addObject(10, (byte)0);
   }

   public ItemStack getItemInUse() {
      return this.itemInUse;
   }

   private void collideWithPlayer(Entity entity) {
      entity.onCollideWithPlayer(this);
   }

   @Override
   public void preparePlayerToSpawn() {
      this.setSize(0.6F, 1.8F);
      super.preparePlayerToSpawn();
      this.setHealth(this.getMaxHealth());
      this.deathTime = 0;
   }

   @Override
   public boolean sendCommandFeedback() {
      return MinecraftServer.getServer().worldServers[0].getGameRules().getBoolean("sendCommandFeedback");
   }

   @Override
   public boolean isInvisibleToPlayer(EntityPlayer player) {
      if (!this.isInvisible()) {
         return false;
      } else if (player.isSpectator()) {
         return false;
      } else {
         Team team = this.getTeam();
         return team == null || player == null || player.getTeam() != team || !team.getSeeFriendlyInvisiblesEnabled();
      }
   }

   public float getToolDigEfficiency(Block block) {
      float strVsBlock = this.inventory.getStrVsBlock(block);
      if (strVsBlock > 1.0F) {
         int efficiencyModifier = EnchantmentHelper.getEfficiencyModifier(this);
         ItemStack currentItem = this.inventory.getCurrentItem();
         if (efficiencyModifier > 0 && currentItem != null) {
            strVsBlock += (float)(efficiencyModifier * efficiencyModifier + 1);
         }
      }

      boolean flag = ClientManagers.moduleManager.fastBreak.shouldFastBreakBlock(block);
      if (this.isPotionActive(Potion.digSpeed) || flag && ClientManagers.moduleManager.fastBreak.getArtificialMiningSpeedEffect(block) > 0.0) {
         int n = flag && ClientManagers.moduleManager.fastBreak.getArtificialMiningSpeedEffect(block) > 0.0 ? (int)ClientManagers.moduleManager.fastBreak.getArtificialMiningSpeedEffect(block) : 0;
         if (this.isPotionActive(Potion.digSpeed)) {
            n += this.getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1;
         }

         strVsBlock *= 1.0F + (float)n * 0.2F;
      }

      if (this.isPotionActive(Potion.digSlowdown) && (!flag || !ClientManagers.moduleManager.fastBreak.ignoreMiningFatigue.getValue())) {
         float f = 1.0F;
         switch (this.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) {
            case 0:
               f = 0.3F;
               break;
            case 1:
               f = 0.09F;
               break;
            case 2:
               f = 0.0027F;
               break;
            case 3:
            default:
               f = 8.1E-4F;
         }

         strVsBlock *= f;
      }

      if (this.isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(this)) {
         strVsBlock /= 5.0F;
      }

      if (!this.onGround) {
         if (flag) {
            if (ClientManagers.moduleManager.fastBreak.isCustomInAirMultiplierEnabled(block)) {
               strVsBlock *= (float)ClientManagers.moduleManager.fastBreak.getInAirMultiplier(block);
               return strVsBlock;
            }
         }

         strVsBlock /= 5.0F;
      }

      return strVsBlock;
   }

   @Override
   public int getPortalCooldown() {
      return 10;
   }

   @Override
   public boolean isMovementBlocked() {
      return this.getCurrentHealth() <= 0.0F || this.isPlayerSleeping();
   }

   @Override
   public float getAIMoveSpeed() {
      return Float.valueOf((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
   }

   @Override
   public void resetHeight() {
      if (!this.isSpectator()) {
         super.resetHeight();
      }
   }

   @Override
   public boolean isChild() {
      return Augustus.getInstance().getModuleManager().child.isToggled();
   }

   public Scoreboard getWorldScoreboard() {
      return this.entityWorld.getScoreboard();
   }

   public void closeScreen() {
      this.openContainer = this.inventoryContainer;
   }

   public boolean isUser() {
      return false;
   }

   public boolean canPlayerEdit(BlockPos pos, EnumFacing facing, ItemStack stack) {
      if (this.capabilities.allowEdit) {
         return true;
      } else if (stack == null) {
         return false;
      } else {
         BlockPos pos2 = pos.offset(facing.getOpposite());
         return stack.canPlaceOn(this.entityWorld.getBlockState(pos2).getBlock()) || stack.canEditBlocks();
      }
   }

   public boolean isWearing(EnumPlayerModelParts enumPlayerModelParts) {
      return (this.getDataWatcher().getWatchableObjectByte(10) & enumPlayerModelParts.getPartMask()) == enumPlayerModelParts.getPartMask();
   }

   @Override
   public boolean isPushedByWater() {
      return !this.capabilities.isFlying;
   }

   public void addExperienceLevel(int n) {
      this.experienceLevel += n;
      if (this.experienceLevel < 0) {
         this.experienceLevel = 0;
         this.experience = 0.0F;
         this.experienceTotal = 0;
      }

      if (n > 0 && this.experienceLevel % 5 == 0 && (float)this.lastXPSound < (float)this.ticksExisted - 100.0F) {
         float f = this.experienceLevel > 30 ? 1.0F : (float)this.experienceLevel / 30.0F;
         this.entityWorld.playSoundAtEntity(this, "random.levelup", f * 0.75F, 1.0F);
         this.lastXPSound = this.ticksExisted;
      }
   }

   public void setItemInUse(ItemStack stack, int n) {
      if (stack != this.itemInUse) {
         this.itemInUse = stack;
         this.itemInUseCount = n;
         if (!this.entityWorld.isRemote) {
            this.setEating(true);
         }
      }
   }

   public int getXPSeed() {
      return this.xpSeed;
   }

   @Override
   public String getSplashSound() {
      return "game.player.swim.splash";
   }

   public void setReducedDebug(boolean flag) {
      this.hasReducedDebug = flag;
   }

   @Override
   public boolean getAlwaysRenderNameTagForRender() {
      return true;
   }

   public boolean isBlocking() {
      return this.isUsingItem() && this.itemInUse.getItem().getItemUseAction(this.itemInUse) == EnumAction.BLOCK;
   }

   @Override
   public boolean canTriggerWalking() {
      return !this.capabilities.isFlying;
   }

   public void onItemUseFinish() {
      if (this.itemInUse != null) {
         this.updateItemUse(this.itemInUse, 16);
         int stackSize = this.itemInUse.stackSize;
         ItemStack stack = this.itemInUse.onItemUseFinish(this.entityWorld, this);
         if (stack != this.itemInUse || stack != null && stack.stackSize != stackSize) {
            this.inventory.mainInventory[this.inventory.currentItem] = stack;
            if (stack.stackSize == 0) {
               this.inventory.mainInventory[this.inventory.currentItem] = null;
            }
         }

         this.clearItemInUse();
      }
   }

   @Override
   public void updateEntityActionState() {
      super.updateEntityActionState();
      this.updateArmSwingProgress();
      this.rotationYawHead = this.rotationYaw;
      this.rotationPitchHead = this.rotationPitch;
   }

   public void clearItemInUse() {
      this.itemInUse = null;
      this.itemInUseCount = 0;
      if (!this.entityWorld.isRemote) {
         this.setEating(false);
      }
   }

   public void addChatComponentMessage(IChatComponent component) {
   }

   private boolean isInBed() {
      return this.entityWorld.getBlockState(this.playerLocation).getBlock() == Blocks.bed;
   }

   public void displayGUIChest(IInventory inventory2) {
   }

   @Override
   public IChatComponent getDisplayName() {
      ChatComponentText chatComponentText = new ChatComponentText(ScorePlayerTeam.formatPlayerName(this.getTeam(), this.getName()));
      chatComponentText.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + this.getName() + " "));
      chatComponentText.getChatStyle().setChatHoverEvent(this.getHoverEvent());
      chatComponentText.getChatStyle().setInsertion(this.getName());
      return chatComponentText;
   }

   public void addMovementStat(double d, double d2, double d3) {
      if (this.ridingEntity == null) {
         if (this.isInsideOfMaterial(Material.water)) {
            int n = Math.round(MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3) * 100.0F);
            if (n > 0) {
               this.addStat(StatList.distanceDoveStat, n);
               this.addExhaustion(0.015F * (float)n * 0.01F);
            }
         } else if (this.isInWater()) {
            int n2 = Math.round(MathHelper.sqrt_double(d * d + d3 * d3) * 100.0F);
            if (n2 > 0) {
               this.addStat(StatList.distanceSwumStat, n2);
               this.addExhaustion(0.015F * (float)n2 * 0.01F);
            }
         } else if (this.isOnLadder()) {
            if (d2 > 0.0) {
               this.addStat(StatList.distanceClimbedStat, (int)Math.round(d2 * 100.0));
            }
         } else if (this.onGround) {
            int n3 = Math.round(MathHelper.sqrt_double(d * d + d3 * d3) * 100.0F);
            if (n3 > 0) {
               this.addStat(StatList.distanceWalkedStat, n3);
               if (this.isSprinting()) {
                  this.addStat(StatList.distanceSprintedStat, n3);
                  this.addExhaustion(0.099999994F * (float)n3 * 0.01F);
               } else {
                  if (this.isSneaking()) {
                     this.addStat(StatList.distanceCrouchedStat, n3);
                  }

                  this.addExhaustion(0.01F * (float)n3 * 0.01F);
               }
            }
         } else {
            int n4 = Math.round(MathHelper.sqrt_double(d * d + d3 * d3) * 100.0F);
            if (n4 > 25) {
               this.addStat(StatList.distanceFlownStat, n4);
            }
         }
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setTag("Inventory", this.inventory.writeToNBT(new NBTTagList()));
      tag.setInteger("SelectedItemSlot", this.inventory.currentItem);
      tag.setBoolean("Sleeping", this.sleeping);
      tag.setShort("SleepTimer", (short)this.sleepTimer);
      tag.setFloat("XpP", this.experience);
      tag.setInteger("XpLevel", this.experienceLevel);
      tag.setInteger("XpTotal", this.experienceTotal);
      tag.setInteger("XpSeed", this.xpSeed);
      tag.setInteger("Score", this.getScore());
      if (this.spawnChunk != null) {
         tag.setInteger("SpawnX", this.spawnChunk.getX());
         tag.setInteger("SpawnY", this.spawnChunk.getY());
         tag.setInteger("SpawnZ", this.spawnChunk.getZ());
         tag.setBoolean("SpawnForced", this.spawnForced);
      }

      this.foodStats.writeNBT(tag);
      this.capabilities.writeCapabilitiesToNBT(tag);
      tag.setTag("EnderItems", this.theInventoryEnderChest.saveInventoryToNBT());
      ItemStack currentItem = this.inventory.getCurrentItem();
      if (currentItem != null && currentItem.getItem() != null) {
         tag.setTag("SelectedItem", currentItem.writeToNBT(new NBTTagCompound()));
      }
   }

   public void destroyCurrentEquippedItem() {
      this.inventory.setInventorySlotContents(this.inventory.currentItem, (ItemStack)null);
   }

   public void onEnchantmentCritical(Entity entity) {
   }

   @Override
   public ItemStack getHeldItem() {
      return this.inventory.getCurrentItem();
   }

   public void takeStat(StatBase statBase) {
   }

   @Override
   public boolean isPlayerSleeping() {
      return this.sleeping;
   }

   public boolean isUsingItem() {
      return this.itemInUse != null;
   }

   public EntityItem dropPlayerItemWithRandomChoice(ItemStack stack, boolean flag) {
      return this.dropItem(stack, false, false);
   }

   public void setItemInUseCount(int n) {
      this.itemInUseCount = n;
   }

   public boolean isPlayerFullyAsleep() {
      return this.sleeping && this.sleepTimer >= 100;
   }

   public float getSpeedOnGround() {
      return this.speedOnGround;
   }

   public BlockPos getBedLocation() {
      return this.spawnChunk;
   }

   public EntityPlayer.EnumStatus trySleep(BlockPos pos) {
      if (!this.entityWorld.isRemote) {
         if (this.isPlayerSleeping() || !this.isEntityAlive()) {
            return EntityPlayer.EnumStatus.OTHER_PROBLEM;
         }

         if (!this.entityWorld.provider.isSurfaceWorld()) {
            return EntityPlayer.EnumStatus.NOT_POSSIBLE_HERE;
         }

         if (this.entityWorld.isDaytime()) {
            return EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW;
         }

         if (Math.abs(this.posX - (double)pos.getX()) > 3.0
            || Math.abs(this.posY - (double)pos.getY()) > 2.0
            || Math.abs(this.posZ - (double)pos.getZ()) > 3.0) {
            return EntityPlayer.EnumStatus.TOO_FAR_AWAY;
         }

         double d = 8.0;
         double d2 = 5.0;
         List entitiesWithinAABB = this.entityWorld
            .getEntitiesWithinAABB(
               EntityMob.class,
               new AxisAlignedBB(
                  (double)pos.getX() - d,
                  (double)pos.getY() - d2,
                  (double)pos.getZ() - d,
                  (double)pos.getX() + d,
                  (double)pos.getY() + d2,
                  (double)pos.getZ() + d
               )
            );
         if (!entitiesWithinAABB.isEmpty()) {
            return EntityPlayer.EnumStatus.NOT_SAFE;
         }
      }

      if (this.isRiding()) {
         this.mountEntity((Entity)null);
      }

      this.setSize(0.2F, 0.2F);
      if (this.entityWorld.isBlockLoaded(pos)) {
         EnumFacing value = (EnumFacing)this.entityWorld.getBlockState(pos).getValue(BlockDirectional.FACING);
         float f = 0.5F;
         float f2 = 0.5F;
         switch (value) {
            case SOUTH:
               f2 = 0.9F;
               break;
            case NORTH:
               f2 = 0.1F;
               break;
            case WEST:
               f = 0.1F;
               break;
            case EAST:
               f = 0.9F;
         }

         this.setRenderOffsetForSleep(value);
         this.setPosition((double)((float)pos.getX() + f), (double)((float)pos.getY() + 0.6875F), (double)((float)pos.getZ() + f2));
      } else {
         this.setPosition((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.6875F), (double)((float)pos.getZ() + 0.5F));
      }

      this.sleeping = true;
      this.sleepTimer = 0;
      this.playerLocation = pos;
      this.motionX = this.motionZ = this.motionY = 0.0;
      if (!this.entityWorld.isRemote) {
         this.entityWorld.updateAllPlayersSleepingFlag();
      }

      return EntityPlayer.EnumStatus.OK;
   }

   public EntityItem dropOneItem(boolean flag) {
      return this.dropItem(
         this.inventory
            .decrStackSize(this.inventory.currentItem, flag && this.inventory.getCurrentItem() != null ? this.inventory.getCurrentItem().stackSize : 1),
         false,
         true
      );
   }

   @Override
   public void addToPlayerScore(Entity entity, int n) {
      this.addScore(n);
      Collection objectivesFromCriteria = this.getWorldScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.totalKillCount);
      if (entity instanceof EntityPlayer) {
         this.triggerAchievement(StatList.playerKillsStat);
         objectivesFromCriteria.addAll(this.getWorldScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.playerKillCount));
         objectivesFromCriteria.addAll(this.giveObjectiveScores(entity));
      } else {
         this.triggerAchievement(StatList.mobKillsStat);
      }

      for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(objectivesFromCriteria)) {
         this.getWorldScoreboard().getValueFromObjective(this.getName(), scoreObjective).incrementScore();
      }
   }

   public void openEditCommandBlock(CommandBlockLogic commandBlockLogic) {
   }

   public void setSpawnPoint(BlockPos pos, boolean flag) {
      if (pos != null) {
         this.spawnChunk = pos;
         this.spawnForced = flag;
      } else {
         this.spawnChunk = null;
         this.spawnForced = false;
      }
   }

   private Collection giveObjectiveScores(Entity entity) {
      ScorePlayerTeam playersTeam = this.getWorldScoreboard().getPlayersTeam(this.getName());
      if (playersTeam != null) {
         int colorIndex = playersTeam.getChatFormat().getColorIndex();
         if (colorIndex >= 0 && colorIndex < IScoreObjectiveCriteria.KILLED_BY_TEAM.length) {
            for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(this.getWorldScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.KILLED_BY_TEAM[colorIndex]))) {
               this.getWorldScoreboard().getValueFromObjective(entity.getName(), scoreObjective).incrementScore();
            }
         }
      }

      ScorePlayerTeam playersTeam2 = this.getWorldScoreboard().getPlayersTeam(entity.getName());
      if (playersTeam2 != null) {
         int colorIndex2 = playersTeam2.getChatFormat().getColorIndex();
         if (colorIndex2 >= 0 && colorIndex2 < IScoreObjectiveCriteria.TEAM_KILL.length) {
            return this.getWorldScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.TEAM_KILL[colorIndex2]);
         }
      }

      return Lists.newArrayList();
   }

   public int getItemInUseDuration() {
      return this.isUsingItem() ? this.itemInUse.getMaxItemUseDuration() - this.itemInUseCount : 0;
   }

   @Override
   public ItemStack getCurrentArmor(int n) {
      return this.inventory.armorItemInSlot(n);
   }

   @Override
   public void setCurrentItemOrArmor(int n, ItemStack stack) {
      this.inventory.armorInventory[n] = stack;
   }

   public boolean canAttackPlayer(EntityPlayer player) {
      Team team = this.getTeam();
      Team team2 = player.getTeam();
      return team == null ? true : (!team.isSameTeam(team2) ? true : team.getAllowFriendlyFire());
   }

   public void setSpeedInAir(float f) {
      this.speedInAir = f;
   }

   @Override
   public int getTotalArmorValue() {
      return this.inventory.getTotalArmorValue();
   }

   private void setRenderOffsetForSleep(EnumFacing facing) {
      this.renderOffsetX = 0.0F;
      this.renderOffsetZ = 0.0F;
      switch (facing) {
         case SOUTH:
            this.renderOffsetZ = -1.8F;
            break;
         case NORTH:
            this.renderOffsetZ = 1.8F;
            break;
         case WEST:
            this.renderOffsetX = 1.8F;
            break;
         case EAST:
            this.renderOffsetX = -1.8F;
      }
   }

   @Override
   public String getFallSoundString(int n) {
      return n > 4 ? "game.player.hurt.fall.big" : "game.player.hurt.fall.small";
   }

   @Override
   public void jump() {
      super.jump();
      this.triggerAchievement(StatList.jumpStat);
      if (this.isSprinting()) {
         this.addExhaustion(0.8F);
      } else {
         this.addExhaustion(0.2F);
      }
   }

   public InventoryEnderChest getInventoryEnderChest() {
      return this.theInventoryEnderChest;
   }

   @Override
   public String getSwimSound() {
      return "game.player.swim";
   }

   public void removeExperienceLevel(int n) {
      this.experienceLevel -= n;
      if (this.experienceLevel < 0) {
         this.experienceLevel = 0;
         this.experience = 0.0F;
         this.experienceTotal = 0;
      }

      this.xpSeed = this.rand.nextInt();
   }

   public String getCustomDisplayName() {
      return this.customDisplayName;
   }

   public void onCriticalHit(Entity entity) {
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.entityUniqueID = getUUID(this.gameProfile);
      NBTTagList tagList = tag.getTagList("Inventory", 10);
      this.inventory.readFromNBT(tagList);
      this.inventory.currentItem = tag.getInteger("SelectedItemSlot");
      this.sleeping = tag.getBoolean("Sleeping");
      this.sleepTimer = tag.getShort("SleepTimer");
      this.experience = tag.getFloat("XpP");
      this.experienceLevel = tag.getInteger("XpLevel");
      this.experienceTotal = tag.getInteger("XpTotal");
      this.xpSeed = tag.getInteger("XpSeed");
      if (this.xpSeed == 0) {
         this.xpSeed = this.rand.nextInt();
      }

      this.setScore(tag.getInteger("Score"));
      if (this.sleeping) {
         this.playerLocation = new BlockPos(this);
         this.wakeUpPlayer(true, true, false);
      }

      if (tag.hasKey("SpawnX", 99) && tag.hasKey("SpawnY", 99) && tag.hasKey("SpawnZ", 99)) {
         this.spawnChunk = new BlockPos(tag.getInteger("SpawnX"), tag.getInteger("SpawnY"), tag.getInteger("SpawnZ"));
         this.spawnForced = tag.getBoolean("SpawnForced");
      }

      this.foodStats.readNBT(tag);
      this.capabilities.readCapabilitiesFromNBT(tag);
      if (tag.hasKey("EnderItems", 9)) {
         NBTTagList tagList2 = tag.getTagList("EnderItems", 10);
         this.theInventoryEnderChest.loadInventoryFromNBT(tagList2);
      }
   }

   @Override
   public String getHurtSound() {
      return "game.player.hurt";
   }

   public void displayGUIHorse(EntityHorse entityHorse, IInventory inventory2) {
   }

   public int getItemInUseCount() {
      return this.itemInUseCount;
   }

   public void stopUsingItem() {
      if (this.itemInUse != null) {
         this.itemInUse.onPlayerStoppedUsing(this.entityWorld, this, this.itemInUseCount);
      }

      this.clearItemInUse();
   }

   @Override
   public boolean isPlayer() {
      return true;
   }

   public void respawnPlayer() {
   }

   public EntityItem dropItem(ItemStack stack, boolean flag, boolean flag2) {
      if (stack == null) {
         return null;
      } else if (stack.stackSize == 0) {
         return null;
      } else {
         double d = this.posY - 0.30000001192092896 + (double)this.getEyeHeight();
         EntityItem entityItem = new EntityItem(this.entityWorld, this.posX, d, this.posZ, stack);
         entityItem.setPickupDelay(40);
         if (flag2) {
            entityItem.setThrower(this.getName());
         }

         if (flag) {
            float f = this.rand.nextFloat() * 0.5F;
            float f2 = this.rand.nextFloat() * 3.1415927F * 2.0F;
            entityItem.motionX = (double)(-MathHelper.sin(f2) * f);
            entityItem.motionZ = (double)(MathHelper.cos(f2) * f);
            entityItem.motionY = 0.20000000298023224;
         } else {
            float f3 = 0.3F;
            entityItem.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * f3);
            entityItem.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * f3);
            entityItem.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F) * f3 + 0.1F);
            float f4 = this.rand.nextFloat() * 3.1415927F * 2.0F;
            f3 = 0.02F * this.rand.nextFloat();
            entityItem.motionX = entityItem.motionX + Math.cos((double)f4) * (double)f3;
            entityItem.motionY = entityItem.motionY + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
            entityItem.motionZ = entityItem.motionZ + Math.sin((double)f4) * (double)f3;
         }

         this.joinEntityItemWithWorld(entityItem);
         if (flag2) {
            this.triggerAchievement(StatList.dropStat);
         }

         return entityItem;
      }
   }

   private void addMountedMovementStat(double d, double d2, double d3) {
      if (this.ridingEntity != null) {
         int n = Math.round(MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3) * 100.0F);
         if (n > 0) {
            if (this.ridingEntity instanceof EntityMinecart) {
               this.addStat(StatList.distanceByMinecartStat, n);
               if (this.startMinecartRidingCoordinate == null) {
                  this.startMinecartRidingCoordinate = new BlockPos(this);
               } else if (this.startMinecartRidingCoordinate
                     .distanceSq(
                        (double)MathHelper.floor_double(this.posX), (double)MathHelper.floor_double(this.posY), (double)MathHelper.floor_double(this.posZ)
                     )
                  >= 1000000.0) {
                  this.triggerAchievement(AchievementList.onARail);
               }
            } else if (this.ridingEntity instanceof EntityBoat) {
               this.addStat(StatList.distanceByBoatStat, n);
            } else if (this.ridingEntity instanceof EntityPig) {
               this.addStat(StatList.distanceByPigStat, n);
            } else if (this.ridingEntity instanceof EntityHorse) {
               this.addStat(StatList.distanceByHorseStat, n);
            }
         }
      }
   }

   @Override
   public void onDeath(DamageSource damageSource) {
      super.onDeath(damageSource);
      this.setSize(0.2F, 0.2F);
      this.setPosition(this.posX, this.posY, this.posZ);
      this.motionY = 0.10000000149011612;
      if (this.getName().equals("Notch")) {
         this.dropItem(new ItemStack(Items.apple, 1), true, false);
      }

      if (!this.entityWorld.getGameRules().getBoolean("keepInventory")) {
         this.inventory.dropAllItems();
      }

      if (damageSource != null) {
         this.motionX = (double)(-MathHelper.cos((this.attackedAtYaw + this.rotationYaw) * 3.1415927F / 180.0F) * 0.1F);
         this.motionZ = (double)(-MathHelper.sin((this.attackedAtYaw + this.rotationYaw) * 3.1415927F / 180.0F) * 0.1F);
      } else {
         this.motionX = this.motionZ = 0.0;
      }

      this.triggerAchievement(StatList.deathsStat);
      this.takeStat(StatList.timeSinceDeathStat);
   }

   public FoodStats getFoodStats() {
      return this.foodStats;
   }

   public boolean isAllowEdit() {
      return this.capabilities.allowEdit;
   }

   public void addExhaustion(float f) {
      if (!this.capabilities.disableDamage && !this.entityWorld.isRemote) {
         this.foodStats.addExhaustion(f);
      }
   }

   public boolean isSpawnForced() {
      return this.spawnForced;
   }

   @Override
   public int getMaxInPortalTime() {
      return this.capabilities.disableDamage ? 0 : 80;
   }

   public void clonePlayer(EntityPlayer player, boolean flag) {
      if (flag) {
         this.inventory.copyInventory(player.inventory);
         this.setHealth(player.getCurrentHealth());
         this.foodStats = player.foodStats;
         this.experienceLevel = player.experienceLevel;
         this.experienceTotal = player.experienceTotal;
         this.experience = player.experience;
         this.setScore(player.getScore());
         this.lastPortalPos = player.lastPortalPos;
         this.lastPortalVec = player.lastPortalVec;
         this.teleportDirection = player.teleportDirection;
      } else if (this.entityWorld.getGameRules().getBoolean("keepInventory")) {
         this.inventory.copyInventory(player.inventory);
         this.experienceLevel = player.experienceLevel;
         this.experienceTotal = player.experienceTotal;
         this.experience = player.experience;
         this.setScore(player.getScore());
      }

      this.xpSeed = player.xpSeed;
      this.theInventoryEnderChest = player.theInventoryEnderChest;
      this.getDataWatcher().updateObject(10, player.getDataWatcher().getWatchableObjectByte(10));
   }

   public void attackTargetEntityWithCurrentItem(Entity entity) {
      if (entity.canAttackWithItem() && !entity.hitByEntity(this)) {
         float attributeValue = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
         int n = 0;
         float f = 0.0F;
         if (entity instanceof EntityLivingBase) {
            f = EnchantmentHelper.getModifierForCreature(this.getHeldItem(), ((EntityLivingBase)entity).getCreatureAttribute());
         } else {
            f = EnchantmentHelper.getModifierForCreature(this.getHeldItem(), EnumCreatureAttribute.UNDEFINED);
         }

         if (this instanceof EntityPlayerSP) {
            ((EntityPlayerSP)this).resetTicksSinceLastSwing();
         }

         n += EnchantmentHelper.getKnockbackModifier(this);
         if (this.isSprinting()) {
            n++;
         }

         if (attributeValue > 0.0F || f > 0.0F) {
            boolean flag = this.fallDistance > 0.0F
               && !this.onGround
               && !this.isOnLadder()
               && !this.isInWater()
               && !this.isPotionActive(Potion.blindness)
               && this.ridingEntity == null
               && entity instanceof EntityLivingBase;
            if (flag && attributeValue > 0.0F) {
               attributeValue *= 1.5F;
            }

            attributeValue += f;
            boolean flag2 = false;
            int fireAspectModifier = EnchantmentHelper.getFireAspectModifier(this);
            if (entity instanceof EntityLivingBase && fireAspectModifier > 0 && !entity.isBurning()) {
               flag2 = true;
               entity.setFire(1);
            }

            double motionX2 = entity.motionX;
            double motionY2 = entity.motionY;
            double motionZ2 = entity.motionZ;
            if (entity.attackEntityFrom(DamageSource.causePlayerDamage(this), attributeValue)) {
               if (n > 0) {
                  entity.addVelocity(
                     (double)(-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * (float)n * 0.5F),
                     0.1,
                     (double)(MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * (float)n * 0.5F)
                  );
                  EventAttackSlowdown event = AttackSlowdownListener.onAttackSlowdown(AttackSlowdownStage.FIRST);
                  this.motionX = this.motionX * event.getSlowDownFactor();
                  this.motionZ = this.motionZ * event.getSlowDownFactor();
                  this.setSprinting(event.isSprint());
                  ClientManagers.moduleManager.velocity.attackSlowdownCount++;
               } else {
                  EventAttackSlowdown event2 = AttackSlowdownListener.onAttackSlowdown(AttackSlowdownStage.SECOND);
                  if (event2.isApplySecondStage()) {
                     this.motionX = this.motionX * event2.getSlowDownFactor();
                     this.motionZ = this.motionZ * event2.getSlowDownFactor();
                     this.setSprinting(event2.isSprint());
                     ClientManagers.moduleManager.velocity.attackSlowdownCount++;
                  }
               }

               if (entity instanceof EntityPlayerMP && entity.velocityChanged) {
                  ((EntityPlayerMP)entity).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(entity));
                  entity.velocityChanged = false;
                  entity.motionX = motionX2;
                  entity.motionY = motionY2;
                  entity.motionZ = motionZ2;
               }

               if (flag) {
                  this.onCriticalHit(entity);
               }

               if (f > 0.0F) {
                  this.onEnchantmentCritical(entity);
               }

               if (attributeValue >= 18.0F) {
                  this.triggerAchievement(AchievementList.overkill);
               }

               this.setLastAttacker(entity);
               if (entity instanceof EntityLivingBase) {
                  EnchantmentHelper.applyThornEnchantments((EntityLivingBase)entity, this);
               }

               EnchantmentHelper.applyArthropodEnchantments(this, entity);
               ItemStack currentEquippedItem = this.getCurrentEquippedItem();
               Object obj = entity;
               if (entity instanceof EntityDragonPart) {
                  IEntityMultiPart entityDragonObj = ((EntityDragonPart)entity).entityDragonObj;
                  if (entityDragonObj instanceof EntityLivingBase) {
                     obj = (EntityLivingBase)entityDragonObj;
                  }
               }

               if (currentEquippedItem != null && obj instanceof EntityLivingBase) {
                  currentEquippedItem.hitEntity((EntityLivingBase)obj, this);
                  if (currentEquippedItem.stackSize <= 0) {
                     this.destroyCurrentEquippedItem();
                  }
               }

               if (entity instanceof EntityLivingBase) {
                  this.addStat(StatList.damageDealtStat, Math.round(attributeValue * 10.0F));
                  if (fireAspectModifier > 0) {
                     entity.setFire(fireAspectModifier * 4);
                  }
               }

               this.addExhaustion(0.3F);
            } else if (flag2) {
               entity.extinguish();
            }
         }
      }
   }

   @Override
   public float getAbsorptionAmount() {
      return this.getDataWatcher().getWatchableObjectFloat(17);
   }

   public void sendPlayerAbilities() {
   }

   @Override
   public void onKillEntity(EntityLivingBase entity) {
      if (entity instanceof IMob) {
         this.triggerAchievement(AchievementList.killEnemy);
      }

      EntityList.EntityEggInfo entityEggInfo = (EntityList.EntityEggInfo)EntityList.entityEggs.get(EntityList.getEntityID(entity));
      if (entityEggInfo != null) {
         this.triggerAchievement(entityEggInfo.killEntityStat);
      }
   }

   public void displayGui(IInteractionObject interactionObject) {
   }

   public float getSpeedInAir() {
      return this.speedInAir;
   }

   public void displayGUIBook(ItemStack stack) {
   }

   @Override
   public boolean isEntityInsideOpaqueBlock() {
      return !this.sleeping && super.isEntityInsideOpaqueBlock();
   }

   public static BlockPos getBedSpawnLocation(World world, BlockPos pos, boolean flag) {
      Block block = world.getBlockState(pos).getBlock();
      if (block != Blocks.bed) {
         if (!flag) {
            return null;
         } else {
            boolean flag2 = block.canSpawnInBlock();
            boolean flag3 = world.getBlockState(pos.up()).getBlock().canSpawnInBlock();
            return flag2 && flag3 ? pos : null;
         }
      } else {
         return BlockBed.getSafeExitLocation(world, pos, 0);
      }
   }

   @Override
   public Team getTeam() {
      return this.getWorldScoreboard().getPlayersTeam(this.getName());
   }

   public int xpBarCap() {
      return this.experienceLevel >= 30
         ? 112 + (this.experienceLevel - 30) * 9
         : (this.experienceLevel >= 15 ? 37 + (this.experienceLevel - 15) * 5 : 7 + this.experienceLevel * 2);
   }

   @Override
   public float getEyeHeight() {
      float f = 1.62F;
      if (this.isPlayerSleeping()) {
         f = 0.2F;
      }

      if (this.isSneaking()) {
         f -= 0.08F;
      }

      return f;
   }

   @Override
   public int getExperiencePoints(EntityPlayer player) {
      if (this.entityWorld.getGameRules().getBoolean("keepInventory")) {
         return 0;
      } else {
         int n = this.experienceLevel * 7;
         return n > 100 ? 100 : n;
      }
   }

   public void updateItemUse(ItemStack stack, int n) {
      if (stack.getItemUseAction() == EnumAction.DRINK) {
         this.playSound("random.drink", 0.5F, this.entityWorld.rand.nextFloat() * 0.1F + 0.9F);
      }

      if (stack.getItemUseAction() == EnumAction.EAT) {
         for (int i = 0; i < n; i++) {
            Vec3 vec = new Vec3(((double)this.rand.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
            vec = vec.rotatePitch(-this.rotationPitch * 3.1415927F / 180.0F);
            vec = vec.rotateYaw(-this.rotationYaw * 3.1415927F / 180.0F);
            double d = (double)(-this.rand.nextFloat()) * 0.6 - 0.3;
            Vec3 vec2 = new Vec3(((double)this.rand.nextFloat() - 0.5) * 0.3, d, 0.6);
            vec2 = vec2.rotatePitch(-this.rotationPitch * 3.1415927F / 180.0F);
            vec2 = vec2.rotateYaw(-this.rotationYaw * 3.1415927F / 180.0F);
            vec2 = vec2.addVector(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
            if (stack.getHasSubtypes()) {
               this.entityWorld
                  .spawnParticle(
                     EnumParticleTypes.ITEM_CRACK,
                     vec2.xCoord,
                     vec2.yCoord,
                     vec2.zCoord,
                     vec.xCoord,
                     vec.yCoord + 0.05,
                     vec.zCoord,
                     new int[]{Item.getIdFromItem(stack.getItem()), stack.getMetadata()}
                  );
            } else {
               this.entityWorld
                  .spawnParticle(
                     EnumParticleTypes.ITEM_CRACK,
                     vec2.xCoord,
                     vec2.yCoord,
                     vec2.zCoord,
                     vec.xCoord,
                     vec.yCoord + 0.05,
                     vec.zCoord,
                     new int[]{Item.getIdFromItem(stack.getItem())}
                  );
            }
         }

         this.playSound("random.eat", 0.5F + 0.5F * (float)this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
      }
   }

   public void triggerAchievement(StatBase statBase) {
      this.addStat(statBase, 1);
   }

   public void addScore(int n) {
      int score = this.getScore();
      this.dataWatcher.updateObject(18, score + n);
   }

   public void joinEntityItemWithWorld(EntityItem entityItem) {
      this.entityWorld.spawnEntityInWorld(entityItem);
   }

   @Override
   public void setDead() {
      super.setDead();
      this.inventoryContainer.onContainerClosed(this);
      if (this.openContainer != null) {
         this.openContainer.onContainerClosed(this);
      }
   }

   @Override
   public void fall(float f, float f2) {
      if (!this.capabilities.allowFlying) {
         if (f >= 2.0F) {
            this.addStat(StatList.distanceFallenStat, (int)Math.round((double)f * 100.0));
         }

         super.fall(f, f2);
      }
   }

   @Override
   public ItemStack getEquipmentInSlot(int n) {
      return n == 0 ? this.inventory.getCurrentItem() : this.inventory.armorInventory[n - 1];
   }

   public boolean canOpen(LockCode lockCode) {
      if (lockCode.isEmpty()) {
         return true;
      } else {
         ItemStack currentEquippedItem = this.getCurrentEquippedItem();
         return currentEquippedItem != null && currentEquippedItem.hasDisplayName() ? currentEquippedItem.getDisplayName().equals(lockCode.getLock()) : false;
      }
   }

   public abstract boolean isSpectator();

   public void addStat(StatBase statBase, int n) {
   }

   public boolean interactWith(Entity entity) {
      if (this.isSpectator()) {
         if (entity instanceof IInventory) {
            this.displayGUIChest((IInventory)entity);
         }

         return false;
      } else {
         ItemStack currentEquippedItem = this.getCurrentEquippedItem();
         ItemStack stack = currentEquippedItem != null ? currentEquippedItem.copy() : null;
         if (!entity.interactFirst(this)) {
            if (currentEquippedItem != null && entity instanceof EntityLivingBase) {
               if (this.capabilities.isCreativeMode) {
                  currentEquippedItem = stack;
               }

               if (currentEquippedItem.interactWithEntity(this, (EntityLivingBase)entity)) {
                  if (currentEquippedItem.stackSize <= 0 && !this.capabilities.isCreativeMode) {
                     this.destroyCurrentEquippedItem();
                  }

                  return true;
               }
            }

            return false;
         } else {
            if (currentEquippedItem != null && currentEquippedItem == this.getCurrentEquippedItem()) {
               if (currentEquippedItem.stackSize <= 0 && !this.capabilities.isCreativeMode) {
                  this.destroyCurrentEquippedItem();
               } else if (currentEquippedItem.stackSize < stack.stackSize && this.capabilities.isCreativeMode) {
                  currentEquippedItem.stackSize = stack.stackSize;
               }
            }

            return true;
         }
      }
   }

   @Override
   public void moveEntityWithHeading(float f, float f2) {
      double posX2 = this.posX;
      double posY2 = this.posY;
      double posZ2 = this.posZ;
      if (this.capabilities.isFlying && this.ridingEntity == null) {
         double motionY2 = this.motionY;
         float jumpMovementFactor2 = this.jumpMovementFactor;
         this.jumpMovementFactor = this.capabilities.getFlySpeed() * (float)(this.isSprinting() ? 2 : 1);
         super.moveEntityWithHeading(f, f2);
         this.motionY = motionY2 * 0.6;
         this.jumpMovementFactor = jumpMovementFactor2;
      } else {
         if (this.isInWater() && !this.isRiding() && ClientManagers.moduleManager.jesus.isToggled() && ClientManagers.moduleManager.jesus.mode.getSelectedValue().equals("1.13")) {
            double yCoord = this.getVectorForRotation(this.rotationPitch, this.rotationYaw).yCoord;
            double d = yCoord < -0.2 ? 0.085 : 0.06;
            if (yCoord <= 0.0
               || this.jumping
               || !(this.entityWorld.getBlockState(new BlockPos(this.posX, this.posY + 1.0 - 0.1, this.posZ)).getBlock() instanceof BlockLiquid)) {
               this.motionY = this.motionY + (yCoord - this.motionY) * d;
            }
         }

         super.moveEntityWithHeading(f, f2);
      }

      this.addMovementStat(this.posX - posX2, this.posY - posY2, this.posZ - posZ2);
   }

   @Override
   public void playSound(String str, float f, float f2) {
      this.entityWorld.playSoundToNearExcept(this, str, f, f2);
   }

   public void setScore(int n) {
      this.dataWatcher.updateObject(18, n);
   }

public enum EnumStatus {
      OK,
      NOT_POSSIBLE_HERE,
      NOT_POSSIBLE_NOW,
      TOO_FAR_AWAY,
      OTHER_PROBLEM,
      NOT_SAFE;
   }

   public enum EnumChatVisibility {
      FULL(0, "options.chat.visibility.full"),
      SYSTEM(1, "options.chat.visibility.system"),
      HIDDEN(2, "options.chat.visibility.hidden");

   private static final EnumChatVisibility[] ID_LOOKUP = new EnumChatVisibility[values().length];
      private final int chatVisibility;
      private final String resourceKey;
   public int getChatVisibility() {
         return this.chatVisibility;
      }

      public static EnumChatVisibility getEnumChatVisibility(int n) {
         return ID_LOOKUP[n % ID_LOOKUP.length];
      }

      public String getResourceKey() {
         return this.resourceKey;
      }

      static {
         for (EnumChatVisibility enumChatVisibility : values()) {
            ID_LOOKUP[enumChatVisibility.chatVisibility] = enumChatVisibility;
         }
      }

      private EnumChatVisibility(int n, String str) {
         this.chatVisibility = n;
         this.resourceKey = str;
      }
   }
}
