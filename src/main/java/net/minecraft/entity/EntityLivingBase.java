package net.minecraft.entity;

import com.google.common.base.Predicate;
import baritone.api.BaritoneAPI;
import baritone.api.event.events.RotationMoveEvent;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import net.augustus.modules.Module;
import net.augustus.events.EventLiquidJump;
import net.augustus.events.EventSwim;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.EntityHurtListener;
import net.augustus.events.listeners.LiquidPhysicsListener;
import net.augustus.events.listeners.LadderListener;
import net.augustus.utils.simulation.EntityMotionSnapshot;
import net.augustus.utils.rotation.RotationController;
import net.augustus.events.listeners.LiquidJumpListener;
import net.augustus.events.listeners.PotionParticleListener;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.events.EventPotionParticle;
import net.augustus.utils.BoundedHistoryList;
import net.augustus.Augustus;
import net.augustus.utils.rotation.YawPitchHelper;
import net.augustus.events.EventLadder;
import net.augustus.events.EventLiquidPhysics;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.events.listeners.SwimListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityLivingBase extends Entity {
   private static final UUID sprintingSpeedBoostModifierUUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
   public static final AttributeModifier sprintingSpeedBoostModifier = new AttributeModifier(
         sprintingSpeedBoostModifierUUID, "Sprinting speed boost", 0.30000001192092896, 2
      )
      .setSaved(false);
   public final BoundedHistoryList targetPosHistory = new BoundedHistoryList(23);
   public final BoundedHistoryList motionSnapshotHistory = new BoundedHistoryList(23);
   public final BoundedHistoryList tickSnapshotHistory = new BoundedHistoryList(23);
   public final BoundedHistoryList extraSnapshotHistory = new BoundedHistoryList(23);
   public final HashMap packetPositionMap = new HashMap();
   private final CombatTracker _combatTracker = new CombatTracker(this);
   private final Map activePotionsMap = Maps.newHashMap();
   private final ItemStack[] previousEquipment = new ItemStack[5];
   public boolean isSwingInProgress;
   public int swingProgressInt;
   public int arrowHitTimer;
   public int hurtTime;
   public int prevHurtTime;
   public int autoBlockHurtTicks;
   public int clientHurtTime = 0;
   public int unclampedHurtTime = 0;
   public int maxHurtTime;
   public float attackedAtYaw;
   public int deathTime;
   public float prevSwingProgress;
   public float swingProgress;
   public float prevLimbSwingAmount;
   public float limbSwingAmount;
   public float limbSwing;
   public int maxHurtResistantTime = 20;
   public float prevCameraPitch;
   public float cameraPitch;
   public float randomUnused2;
   public float randomUnused1;
   public float renderYawOffset;
   public float prevRenderYawOffset;
   public float rotationYawHead;
   public float prevRotationYawHead;
   public float rotationPitchHead;
   public float prevRotationPitchHead;
   public float jumpMovementFactor = 0.02F;
   public boolean jumping;
   public float moveStrafing;
   public float moveForward;
   public int newPosRotationIncrements;
   public int realPosRotationIncrements;
   public double newPosX;
   public double newPosY;
   public double newPosZ;
   public double realPosX = 0.0;
   public double realPosY = 0.0;
   public double realPosZ = 0.0;
   public double predictedPosX = 0.0;
   public double predictedPosY = 0.0;
   public double predictedPosZ = 0.0;
   public double trackedPosX = 0.0;
   public double trackedPosY = 0.0;
   public double trackedPosZ = 0.0;
   public double newRotationYaw;
   public double newRotationPitch;
   public int hurtAnimationCount = 0;
   public int hurtStatusCount = 0;
   public int lastHurtStatusTick = 0;
   public int totalHurtEvents = 0;
   public int ticksSinceJump;
   public AnimationUtil colorAnimation = new AnimationUtil(0.0F, 0.0F, 0.0F, 0.0F);
   public int realServerPosX = 0;
   public int realServerPosY = 0;
   public int realServerPosZ = 0;
   public double lastMotionX = 0.0;
   public double lastMotionY = 0.0;
   public double lastMotionZ = 0.0;
   public Vec3 interpolatedServerPos;
   public Vec3 prevInterpolatedServerPos;
   public AxisAlignedBB serverBoundingBox = null;
   public Vec3 rawServerPos;
   public Vec3 prevRawServerPos;
   public EntityPlayer attackingPlayer;
   public int recentlyHit;
   public boolean dead;
   public int age;
   public float prevOnGroundSpeedFactor;
   public float onGroundSpeedFactor;
   public float movedDistance;
   public float prevMovedDistance;
   public float unused180;
   public int scoreValue;
   public float lastDamage;
   public float randomYawVelocity;
   private BaseAttributeMap attributeMap;
   private boolean potionsNeedUpdate = true;
   private EntityLivingBase entityLivingToAttack;
   private int revengeTimer;
   private EntityLivingBase lastAttacker;
   private int lastAttackerTime;
   private float aiMoveSpeed;
   private int jumpTicks;
   private float absorptionAmount;
   private RotationMoveEvent jumpRotationEvent;
   private RotationMoveEvent moveRotationEvent;

   @Override
   public Vec3 getLook(float f) {
      if (f == 1.0F) {
         return this.getVectorForRotation(this.rotationPitch, this.rotationYawHead);
      } else {
         return this.getVectorForRotation(this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * f, this.prevRotationYawHead + (this.rotationYawHead - this.prevRotationYawHead) * f);
      }
   }

   public void onChangedPotionEffect(PotionEffect potionEffect, boolean flag) {
      this.potionsNeedUpdate = true;
      if (flag && !this.entityWorld.isRemote) {
         Potion.potionTypes[potionEffect.getPotionID()].removeAttributesModifiersFromEntity(this, this.getAttributeMap(), potionEffect.getAmplifier());
         Potion.potionTypes[potionEffect.getPotionID()].applyAttributesModifiersToEntity(this, this.getAttributeMap(), potionEffect.getAmplifier());
      }
   }

   public boolean isOnTeam(Team team) {
      return this.getTeam() != null && this.getTeam().isSameTeam(team);
   }

   public BaseAttributeMap getAttributeMap() {
      if (this.attributeMap == null) {
         this.attributeMap = new ServersideAttributeMap();
      }

      return this.attributeMap;
   }

   public boolean isOnLadder() {
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.getEntityBoundingBox().minY);
      int n3 = MathHelper.floor_double(this.posZ);
      Block block = this.entityWorld.getBlockState2(n, n2, n3).getBlock();
      return (block == Blocks.ladder || block == Blocks.vine) && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).isSpectator());
   }

   public void setAbsorptionAmount(float f) {
      if (f < 0.0F) {
         f = 0.0F;
      }

      this.absorptionAmount = f;
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEFINED;
   }

   @Override
   public void updateRidden() {
      super.updateRidden();
      this.prevOnGroundSpeedFactor = this.onGroundSpeedFactor;
      this.onGroundSpeedFactor = 0.0F;
      this.fallDistance = 0.0F;
      this.clientFallDistance = 0.0F;
   }

   @Override
   public abstract ItemStack[] getInventory();

   public void dismountEntity(Entity entity) {
      double posX2 = entity.posX;
      double d = entity.getEntityBoundingBox().minY + (double)entity.height;
      double posZ2 = entity.posZ;
      byte b = 1;

      for (int i = -b; i <= b; i++) {
         for (int j = -b; j < b; j++) {
            if (i != 0 || j != 0) {
               int n = (int)(this.posX + (double)i);
               int n2 = (int)(this.posZ + (double)j);
               AxisAlignedBB box = this.getEntityBoundingBox().offset((double)i, 1.0, (double)j);
               if (this.entityWorld.getCollisionBoxes(box).isEmpty()) {
                  if (World.doesBlockHaveSolidTopSurface(this.entityWorld, n, MathHelper.floor_double(this.posY), n2)) {
                     this.setPositionAndUpdate(this.posX + (double)i, this.posY + 1.0, this.posZ + (double)j);
                     return;
                  }

                  if (World.doesBlockHaveSolidTopSurface(this.entityWorld, n, MathHelper.floor_double(this.posY - 1.0), n2)
                     || this.entityWorld.getBlockState(new BlockPos(n, (int)this.posY - 1, n2)).getBlock().getMaterial() == Material.water) {
                     posX2 = this.posX + (double)i;
                     d = this.posY + 1.0;
                     posZ2 = this.posZ + (double)j;
                  }
               }
            }
         }
      }

      this.setPositionAndUpdate(posX2, d, posZ2);
   }

   public Collection getActivePotionEffects() {
      return this.activePotionsMap.values();
   }

   public void setHealth(float f) {
      this.dataWatcher.updateObject(6, MathHelper.clamp_float(f, 0.0F, this.getMaxHealth()));
   }

   public boolean isOnSameTeam(EntityLivingBase entity) {
      return this.isOnTeam(entity.getTeam());
   }

   public float getAbsorptionAmount() {
      return this.absorptionAmount;
   }

   public Vec3 getPredictedPosition() {
      if (this.realPosRotationIncrements > 0) {
         double d = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
         double d2 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
         double d3 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
         return new Vec3(d, d2, d3);
      } else {
         return new Vec3(this.posX, this.posY, this.posZ);
      }
   }

   public float getSoundPitch() {
      return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.5F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F;
   }

   public void dropFewItems(boolean flag, int n) {
   }

   public boolean canEntityBeSeen(Entity entity) {
      return this.entityWorld.rayTraceBlocks(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)
         == null;
   }

   public void setInitialServerPosition(int n, int n2, int n3) {
      this.realPosX = this.predictedPosX = this.trackedPosX = (double)n / 32.0;
      this.realPosY = this.predictedPosY = this.trackedPosY = (double)n2 / 32.0;
      this.realPosZ = this.predictedPosZ = this.trackedPosZ = (double)n3 / 32.0;
      this.realServerPosX = n;
      this.realServerPosY = n2;
      this.realServerPosZ = n3;
      this.interpolatedServerPos = new Vec3(this.realPosX, this.realPosY, this.realPosZ);
      this.prevInterpolatedServerPos = new Vec3(this.interpolatedServerPos);
      this.rawServerPos = new Vec3(this.prevInterpolatedServerPos);
      this.prevRawServerPos = new Vec3(this.prevRawServerPos);
   }

   public PotionEffect getActivePotionEffect(Potion potion) {
      return (PotionEffect)this.activePotionsMap.get(potion.id);
   }

   public boolean isPotionActive(int n) {
      return this.activePotionsMap.containsKey(n);
   }

   public void handleJumpLava(EventLiquidJump event) {
      this.motionY += 0.03999999910593033;
   }

   public void markPotionsDirty() {
      this.potionsNeedUpdate = true;
   }

   public int getArmSwingAnimationEnd() {
      int n = this.isPotionActive(Potion.digSpeed)
         ? 6 - (1 + this.getActivePotionEffect(Potion.digSpeed).getAmplifier())
         : (this.isPotionActive(Potion.digSlowdown) ? 6 + (1 + this.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2 : 6);
      if (((Module)ClientManagers.moduleManager.blockAnimation).isToggled() && this instanceof EntityPlayerSP) {
         n = (int)((double)n / ClientManagers.moduleManager.blockAnimation.speed.getValue());
      }

      return n;
   }

   public EntityLivingBase(World world) {
      super(world);
      this.applyEntityAttributes();
      this.setHealth(this.getMaxHealth());
      this.preventEntitySpawning = true;
      this.randomUnused1 = (float)((Math.random() + 1.0) * 0.009999999776482582);
      this.setPosition(this.posX, this.posY, this.posZ);
      this.interpolatedServerPos = new Vec3(this.posX, this.posY, this.posZ);
      this.prevInterpolatedServerPos = new Vec3(this.posX, this.posY, this.posZ);
      this.rawServerPos = new Vec3(this.posX, this.posY, this.posZ);
      this.prevRawServerPos = new Vec3(this.posX, this.posY, this.posZ);
      this.randomUnused2 = (float)Math.random() * 12398.0F;
      this.rotationYaw = (float)(Math.random() * 3.141592653589793 * 2.0);
      this.rotationYawHead = this.rotationYaw;
      this.stepHeight = 0.6F;
   }

   public void setJumping(boolean flag) {
      this.jumping = flag;
   }

   public String getHurtSound() {
      return "game.neutral.hurt";
   }

   public float applyPotionDamageCalculations(DamageSource damageSource, float f) {
      if (damageSource.isDamageAbsolute()) {
         return f;
      } else {
         if (this.isPotionActive(Potion.resistance) && damageSource != DamageSource.outOfWorld) {
            f = (f * (float)(25 - ((this.getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5))) / 25.0F;
         }

         if (f <= 0.0F) {
            return 0.0F;
         } else {
            int n3 = EnchantmentHelper.getEnchantmentModifierDamage(this.getInventory(), damageSource);
            if (n3 > 20) {
               n3 = 20;
            }

            if (n3 > 0 && n3 <= 20) {
               f = (f * (float)(25 - n3)) / 25.0F;
            }

            return f;
         }
      }
   }

   @Override
   public void kill() {
      this.attackEntityFrom(DamageSource.outOfWorld, 4.0F);
   }

   public void updateArmSwingProgress() {
      int armSwingAnimationEnd = this.getArmSwingAnimationEnd();
      if (this.isSwingInProgress) {
         this.swingProgressInt++;
         if (this.swingProgressInt >= armSwingAnimationEnd) {
            this.swingProgressInt = 0;
            this.isSwingInProgress = false;
         }
      } else {
         this.swingProgressInt = 0;
      }

      this.swingProgress = (float)this.swingProgressInt / (float)armSwingAnimationEnd;
   }

   @Override
   public boolean canBePushed() {
      return !this.isDead;
   }

   public void handleJumpWater(EventLiquidJump event) {
      this.motionY += 0.03999999910593033;
   }

   public void renderBrokenItemStack(ItemStack stack) {
      this.playSound("random.break", 0.8F, 0.8F + this.entityWorld.rand.nextFloat() * 0.4F);

      for (int i = 0; i < 5; i++) {
         Vec3 vec = new Vec3(((double)this.rand.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
         vec = vec.rotatePitch(-this.rotationPitch * 3.1415927F / 180.0F);
         vec = vec.rotateYaw(-this.rotationYaw * 3.1415927F / 180.0F);
         double d = (double)(-this.rand.nextFloat()) * 0.6 - 0.3;
         Vec3 vec2 = new Vec3(((double)this.rand.nextFloat() - 0.5) * 0.3, d, 0.6);
         vec2 = vec2.rotatePitch(-this.rotationPitch * 3.1415927F / 180.0F);
         vec2 = vec2.rotateYaw(-this.rotationYaw * 3.1415927F / 180.0F);
         vec2 = vec2.addVector(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
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

   @Override
   public void onEntityUpdate() {
      this.prevRotationPitchHead = this.rotationPitchHead;
      this.prevSwingProgress = this.swingProgress;
      super.onEntityUpdate();
      this.entityWorld.theProfiler.startSection("livingEntityBaseTick");
      boolean flag = this instanceof EntityPlayer;
      if (this.isEntityAlive()) {
         if (this.isEntityInsideOpaqueBlock()) {
            this.attackEntityFrom(DamageSource.inWall, 1.0F);
         } else if (flag && !this.entityWorld.getWorldBorder().contains(this.getEntityBoundingBox())) {
            double d = this.entityWorld.getWorldBorder().getClosestDistance(this) + this.entityWorld.getWorldBorder().getDamageBuffer();
            if (d < 0.0) {
               this.attackEntityFrom(DamageSource.inWall, (float)Math.max(1, MathHelper.floor_double(-d * this.entityWorld.getWorldBorder().getDamageAmount())));
            }
         }
      }

      if (this.isImmuneToFire() || this.entityWorld.isRemote) {
         this.extinguish();
      }

      boolean flag2 = flag && ((EntityPlayer)this).capabilities.disableDamage;
      if (this.isEntityAlive()) {
         if (this.isInsideOfMaterial(Material.water)) {
            if (!this.canBreatheUnderwater() && !this.isPotionActive(Potion.waterBreathing.id) && !flag2) {
               this.setAir(this.decreaseAirSupply(this.getAir()));
               if (this.getAir() == -20) {
                  this.setAir(0);

                  for (int i = 0; i < 8; i++) {
                     float f = this.rand.nextFloat() - this.rand.nextFloat();
                     float f2 = this.rand.nextFloat() - this.rand.nextFloat();
                     float f3 = this.rand.nextFloat() - this.rand.nextFloat();
                     this.entityWorld
                        .spawnParticle(
                           EnumParticleTypes.WATER_BUBBLE,
                           this.posX + (double)f,
                           this.posY + (double)f2,
                           this.posZ + (double)f3,
                           this.motionX,
                           this.motionY,
                           this.motionZ,
                           new int[0]
                        );
                  }

                  this.attackEntityFrom(DamageSource.drown, 2.0F);
               }
            }

            if (!this.entityWorld.isRemote && this.isRiding() && this.ridingEntity instanceof EntityLivingBase) {
               this.mountEntity(null);
            }
         } else {
            this.setAir(300);
         }
      }

      if (this.isEntityAlive() && this.isWet()) {
         this.extinguish();
      }

      this.prevCameraPitch = this.cameraPitch;
      this.prevHurtTime = this.hurtTime;
      if (this.hurtTime > 0) {
         this.hurtTime--;
      }

      if (this.autoBlockHurtTicks > 0) {
         this.autoBlockHurtTicks--;
      }

      if (this.clientHurtTime > 0) {
         this.clientHurtTime--;
      }

      this.unclampedHurtTime--;
      if (this.hurtResistantTime > 0 && !(this instanceof EntityPlayerMP)) {
         this.hurtResistantTime--;
      }

      if (this.getCurrentHealth() <= 0.0F) {
         this.onDeathUpdate();
      }

      if (this.recentlyHit > 0) {
         this.recentlyHit--;
      } else {
         this.attackingPlayer = null;
      }

      if (this.lastAttacker != null && !this.lastAttacker.isEntityAlive()) {
         this.lastAttacker = null;
      }

      if (this.entityLivingToAttack != null) {
         if (!this.entityLivingToAttack.isEntityAlive()) {
            this.setRevengeTarget(null);
         } else if (this.ticksExisted - this.revengeTimer > 100) {
            this.setRevengeTarget(null);
         }
      }

      this.updatePotionEffects();
      this.prevMovedDistance = this.movedDistance;
      this.prevRenderYawOffset = this.renderYawOffset;
      this.prevRotationYawHead = this.rotationYawHead;
      this.prevRotationYaw = this.rotationYaw;
      this.prevRotationPitch = this.rotationPitch;
      this.entityWorld.theProfiler.endSection();
   }

   public int getRevengeTimer() {
      return this.revengeTimer;
   }

   public void updatePotionMetadata() {
      if (this.activePotionsMap.isEmpty()) {
         this.resetPotionEffectMetadata();
         this.setInvisible(false);
      } else {
         int n = PotionHelper.calcPotionLiquidColor(this.activePotionsMap.values());
         this.dataWatcher.updateObject(8, (byte)(PotionHelper.getAreAmbient(this.activePotionsMap.values()) ? 1 : 0));
         this.dataWatcher.updateObject(7, n);
         this.setInvisible(this.isPotionActive(Potion.invisibility.id));
      }
   }

   @Override
   public boolean isEntityAlive() {
      return !this.isDead && this.getCurrentHealth() > 0.0F;
   }

   public final int getArrowCountInEntity() {
      return this.dataWatcher.getWatchableObjectByte(9);
   }

   public boolean isPlayer() {
      return false;
   }

   public boolean isPlayerSleeping() {
      return false;
   }

   private float overrideJumpYaw(float f) {
      return ((Module)Augustus.getInstance().getModuleManager().baritone).isToggled() && this instanceof EntityPlayerSP && BaritoneAPI.getProvider().getBaritoneForPlayer((EntityPlayerSP)this) != null
         ? this.jumpRotationEvent.getYaw()
         : f;
   }

   public EntityLivingBase getAITarget() {
      return this.entityLivingToAttack;
   }

   public boolean isMovementBlocked() {
      return this.getCurrentHealth() <= 0.0F;
   }

   public void onFinishedPotionEffect(PotionEffect potionEffect) {
      this.potionsNeedUpdate = true;
      if (!this.entityWorld.isRemote) {
         Potion.potionTypes[potionEffect.getPotionID()].removeAttributesModifiersFromEntity(this, this.getAttributeMap(), potionEffect.getAmplifier());
      }
   }

   public void heal(float f) {
      float currentHealth = this.getCurrentHealth();
      if (currentHealth > 0.0F) {
         this.setHealth(currentHealth + f);
      }
   }

   public abstract ItemStack getEquipmentInSlot(int n);

   @Override
   public void setRenderYawOffset(float f) {
      this.renderYawOffset = f;
   }

   public int decreaseAirSupply(int n) {
      int respiration = EnchantmentHelper.getRespiration(this);
      return respiration > 0 && this.rand.nextInt(respiration + 1) > 0 ? n : n - 1;
   }

   public boolean canEntityBeSeen(double d, double d2, double d3) {
      return this.entityWorld.rayTraceBlocks(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, d, d2, d3) == null;
   }

   public float getSwingProgress(float f) {
      float f2 = this.swingProgress - this.prevSwingProgress;
      if (f2 < 0.0F) {
         f2++;
      }

      return this.prevSwingProgress + f2 * f;
   }

   @Override
   public float getRotationYawHead() {
      return this.rotationYawHead;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      this.setAbsorptionAmount(tag.getFloat("AbsorptionAmount"));
      if (tag.hasKey("Attributes", 9) && this.entityWorld != null && !this.entityWorld.isRemote) {
         SharedMonsterAttributes.setAttributeModifiers(this.getAttributeMap(), tag.getTagList("Attributes", 10));
      }

      if (tag.hasKey("ActiveEffects", 9)) {
         NBTTagList tagList = tag.getTagList("ActiveEffects", 10);

         for (int i = 0; i < tagList.tagCount(); i++) {
            PotionEffect potionEffect = PotionEffect.readCustomPotionEffectFromNBT(tagList.getCompoundTagAt(i));
            if (potionEffect != null) {
               this.activePotionsMap.put(potionEffect.getPotionID(), potionEffect);
            }
         }
      }

      if (tag.hasKey("HealF", 99)) {
         this.setHealth(tag.getFloat("HealF"));
      } else {
         NBTBase tag2 = tag.getTag("Health");
         if (tag2 == null) {
            this.setHealth(this.getMaxHealth());
         } else if (tag2.getId() == 5) {
            this.setHealth(((NBTTagFloat)tag2).getFloat());
         } else if (tag2.getId() == 2) {
            this.setHealth((float)((NBTTagShort)tag2).getShort());
         }
      }

      this.hurtTime = tag.getShort("HurtTime");
      this.deathTime = tag.getShort("DeathTime");
      this.revengeTimer = tag.getInteger("HurtByTimestamp");
   }

   @Override
   public void entityInit() {
      this.dataWatcher.addObject(7, 0);
      this.dataWatcher.addObject(8, (byte)0);
      this.dataWatcher.addObject(9, (byte)0);
      this.dataWatcher.addObject(6, 1.0F);
   }

   public boolean attackEntityAsMob(Entity entity) {
      this.setLastAttacker(entity);
      return false;
   }

   public void sendEnterCombat() {
   }

   public String getFallSoundString(int n) {
      return n > 4 ? "game.neutral.hurt.fall.big" : "game.neutral.hurt.fall.small";
   }

   @Override
   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   @Override
   public void setSprinting(boolean flag) {
      super.setSprinting(flag);
      IAttributeInstance entityAttribute = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      if (entityAttribute.getModifier(sprintingSpeedBoostModifierUUID) != null) {
         entityAttribute.removeModifier(sprintingSpeedBoostModifier);
      }

      if (flag) {
         entityAttribute.applyModifier(sprintingSpeedBoostModifier);
      }
   }

   @Override
   public void mountEntity(Entity entity) {
      if (this.ridingEntity != null && entity == null) {
         if (!this.entityWorld.isRemote) {
            this.dismountEntity(this.ridingEntity);
         }

         if (this.ridingEntity != null) {
            this.ridingEntity.riddenByEntity = null;
         }

         this.ridingEntity = null;
      } else {
         super.mountEntity(entity);
      }
   }

   public void applyEntityAttributes() {
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed);
   }

   public void damageArmor(float f) {
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setFloat("HealF", this.getCurrentHealth());
      tag.setShort("Health", (short)((int)Math.ceil((double)this.getCurrentHealth())));
      tag.setShort("HurtTime", (short)this.hurtTime);
      tag.setInteger("HurtByTimestamp", this.revengeTimer);
      tag.setShort("DeathTime", (short)this.deathTime);
      tag.setFloat("AbsorptionAmount", this.getAbsorptionAmount());

      for (ItemStack stack : this.getInventory()) {
         if (stack != null) {
            this.attributeMap.removeAttributeModifiers(stack.getAttributeModifiers());
         }
      }

      tag.setTag("Attributes", SharedMonsterAttributes.writeBaseAttributeMapToNBT(this.getAttributeMap()));

      for (ItemStack stack2 : this.getInventory()) {
         if (stack2 != null) {
            this.attributeMap.applyAttributeModifiers(stack2.getAttributeModifiers());
         }
      }

      if (!this.activePotionsMap.isEmpty()) {
         NBTTagList tagList = new NBTTagList();

         for (PotionEffect potionEffect : (Iterable<PotionEffect>)(this.activePotionsMap.values())) {
            tagList.appendTag(potionEffect.writeCustomPotionEffectToNBT(new NBTTagCompound()));
         }

         tag.setTag("ActiveEffects", tagList);
      }
   }

   public void damageEntity(DamageSource damageSource, float f) {
      if (!this.isEntityInvulnerable(damageSource)) {
         f = this.applyArmorCalculations(damageSource, f);
         f = this.applyPotionDamageCalculations(damageSource, f);
         float f2 = Math.max(f - this.getAbsorptionAmount(), 0.0F);
         this.setAbsorptionAmount(this.getAbsorptionAmount() - (f - f2));
         if (f2 != 0.0F) {
            float currentHealth = this.getCurrentHealth();
            this.setHealth(currentHealth - f2);
            this.getCombatTracker().trackDamage(damageSource, currentHealth, f2);
            this.setAbsorptionAmount(this.getAbsorptionAmount() - f2);
         }
      }
   }

   public void setRevengeTarget(EntityLivingBase entity) {
      this.entityLivingToAttack = entity;
      this.revengeTimer = this.ticksExisted;
   }

   public int getAge() {
      return this.age;
   }

   public boolean canDropLoot() {
      return !this.isChild();
   }

   public boolean isEntityUndead() {
      return this.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD;
   }

   @Override
   public void setRotationYawHead(float f) {
      this.rotationYawHead = f;
   }

   public final void setArrowCountInEntity(int n) {
      this.dataWatcher.updateObject(9, (byte)n);
   }

   public boolean isPotionActive(Potion potion) {
      return this.activePotionsMap.containsKey(potion.id);
   }

   public float getAIMoveSpeed() {
      return this.aiMoveSpeed;
   }

   @Override
   public void setPositionAndRotation2(double d, double d2, double d3, float f, float f2, int n, boolean flag) {
      this.newPosX = d;
      this.newPosY = d2;
      this.newPosZ = d3;
      this.newRotationYaw = (double)f;
      this.newRotationPitch = (double)f2;
      this.newPosRotationIncrements = n;
   }

   @Override
   public abstract void setCurrentItemOrArmor(int n, ItemStack stack);

   public void knockBack(Entity entity, float f, double d, double d2) {
      if (this.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()) {
         this.isAirBorne = true;
         float f2 = MathHelper.sqrt_double(d * d + d2 * d2);
         float f3 = 0.4F;
         this.motionX /= 2.0;
         this.motionY /= 2.0;
         this.motionZ /= 2.0;
         this.motionX -= d / (double)f2 * (double)f3;
         this.motionY += (double)f3;
         this.motionZ -= d2 / (double)f2 * (double)f3;
         if (this.motionY > 0.4000000059604645) {
            this.motionY = 0.4000000059604645;
         }
      }
   }

   @Override
   public void updateFallState(double d, boolean flag, Block block, BlockPos pos) {
      if (!this.isInWater()) {
         this.handleWaterMovement();
      }

      if (!this.entityWorld.isRemote && this.fallDistance > 3.0F && flag) {
         IBlockState blockState = this.entityWorld.getBlockState(pos);
         Block block2 = blockState.getBlock();
         float f = (float)MathHelper.ceiling_float_int(this.fallDistance - 3.0F);
         if (block2.getMaterial() != Material.air) {
            double d2 = (double)Math.min(0.2F + f / 15.0F, 10.0F);
            if (d2 > 2.5) {
               d2 = 2.5;
            }

            int n = (int)(150.0 * d2);
            ((WorldServer)this.entityWorld)
               .spawnParticle(
                  EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, n, 0.0, 0.0, 0.0, 0.15000000596046448, new int[]{Block.getStateId(blockState)}
               );
         }
      }

      super.updateFallState(d, flag, block, pos);
   }

   public float applyArmorCalculations(DamageSource damageSource, float f) {
      if (!damageSource.isUnblockable()) {
         float f2 = f * (float)(25 - this.getTotalArmorValue());
         this.damageArmor(f);
         f = f2 / 25.0F;
      }

      return f;
   }

   public void onDeathUpdate() {
      this.deathTime++;
      if (this.deathTime == 20) {
         if (!this.entityWorld.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && this.canDropLoot() && this.entityWorld.getGameRules().getBoolean("doMobLoot")) {
            int experiencePoints = this.getExperiencePoints(this.attackingPlayer);

            while (experiencePoints > 0) {
               int xpSplit = EntityXPOrb.getXPSplit(experiencePoints);
               experiencePoints -= xpSplit;
               this.entityWorld.spawnEntityInWorld(new EntityXPOrb(this.entityWorld, this.posX, this.posY, this.posZ, xpSplit));
            }
         }

         this.setDead();

         for (int i = 0; i < 20; i++) {
            double d = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = this.rand.nextGaussian() * 0.02;
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.EXPLOSION_NORMAL,
                  this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
                  this.posY + (double)(this.rand.nextFloat() * this.height),
                  this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
                  d,
                  d2,
                  d3,
                  new int[0]
               );
         }
      }
   }

   public void dropEquipment(boolean flag, int n) {
   }

   public CombatTracker getCombatTracker() {
      return this._combatTracker;
   }

   public void removePotionEffect(int n) {
      PotionEffect potionEffect = (PotionEffect)this.activePotionsMap.remove(n);
      if (potionEffect != null) {
         this.onFinishedPotionEffect(potionEffect);
      }
   }

   public void onItemPickup(Entity entity, int n) {
      if (!entity.isDead && !this.entityWorld.isRemote) {
         EntityTracker entityTracker = ((WorldServer)this.entityWorld).getEntityTracker();
         if (entity instanceof EntityItem) {
            entityTracker.sendToAllTrackingEntity(entity, new S0DPacketCollectItem(entity.getEntityId(), this.getEntityId()));
         }

         if (entity instanceof EntityArrow) {
            entityTracker.sendToAllTrackingEntity(entity, new S0DPacketCollectItem(entity.getEntityId(), this.getEntityId()));
         }

         if (entity instanceof EntityXPOrb) {
            entityTracker.sendToAllTrackingEntity(entity, new S0DPacketCollectItem(entity.getEntityId(), this.getEntityId()));
         }
      }
   }

   public void clearActivePotions() {
      Iterator it = this.activePotionsMap.keySet().iterator();

      while (it.hasNext()) {
         Integer num = (Integer)it.next();
         PotionEffect potionEffect = (PotionEffect)this.activePotionsMap.get(num);
         if (!this.entityWorld.isRemote) {
            it.remove();
            this.onFinishedPotionEffect(potionEffect);
         }
      }
   }

   public void setLastAttacker(Entity entity) {
      if (entity instanceof EntityLivingBase) {
         this.lastAttacker = (EntityLivingBase)entity;
      } else {
         this.lastAttacker = null;
      }

      this.lastAttackerTime = this.ticksExisted;
   }

   public void collideWithNearbyEntities() {
      List list = this.entityWorld
         .getEntitiesInAABBexcluding(
            this,
            this.getEntityBoundingBox().expand(0.20000000298023224, 0.0, 0.20000000298023224),
            Predicates.and(EntitySelectors.NOT_SPECTATING, new EntityLivingBase.FilterPredicate(this))
         );
      if (!list.isEmpty()) {
         for (int i = 0; i < list.size(); i++) {
            this.collideWithEntity((Entity)list.get(i));
         }
      }
   }

   public String getDeathSound() {
      return "game.neutral.die";
   }

   public void onLivingUpdate() {
      if (this.jumpTicks > 0) {
         this.jumpTicks--;
      }

      this.prevInterpolatedServerPos = new Vec3(this.interpolatedServerPos.xCoord, this.interpolatedServerPos.yCoord, this.interpolatedServerPos.zCoord);
      this.prevRawServerPos = new Vec3(this.rawServerPos.xCoord, this.rawServerPos.yCoord, this.rawServerPos.zCoord);
      if (this.realPosRotationIncrements > 0) {
         double d = this.interpolatedServerPos.xCoord + (this.realPosX - this.interpolatedServerPos.xCoord) / (double)this.realPosRotationIncrements;
         double d2 = this.interpolatedServerPos.yCoord + (this.realPosY - this.interpolatedServerPos.yCoord) / (double)this.realPosRotationIncrements;
         double d3 = this.interpolatedServerPos.zCoord + (this.realPosZ - this.interpolatedServerPos.zCoord) / (double)this.realPosRotationIncrements;
         this.realPosRotationIncrements--;
         this.interpolatedServerPos = new Vec3(d, d2, d3);
         float f = this.width / 2.0F;
         float height2 = this.height;
         this.serverBoundingBox = new AxisAlignedBB(
            d - (double)f, d2, d3 - (double)f, d + (double)f, d2 + (double)height2, d3 + (double)f
         );
         if (this == Minecraft.getMinecraft().thePlayer) {
            float rotationPitch2 = this.rotationPitch;
            float rotationYaw2 = this.rotationYaw;
            ClientManagers.moduleManager.killAura.rotationUtil.bezierRotationEngine.syncCurrentRotation(rotationYaw2, rotationPitch2, false);
         }
      }

      this.rawServerPos = new Vec3(this.realPosX, this.realPosY, this.realPosZ);
      if (this.newPosRotationIncrements > 0) {
         double d4 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
         double d5 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
         double d6 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
         double d7 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)this.rotationYaw);
         this.rotationYaw = (float)((double)this.rotationYaw + d7 / (double)this.newPosRotationIncrements);
         this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
         this.newPosRotationIncrements--;
         this.setPosition(d4, d5, d6);
         this.setRotation(this.rotationYaw, this.rotationPitch);
         if (this instanceof EntityPlayerSP) {
            Augustus.getInstance().getRotationController().forceRotation(new Rotation(this.rotationYaw, this.rotationPitch));
         }
      } else if (!this.isServerWorld()) {
         this.motionX *= 0.98;
         this.motionY *= 0.98;
         this.motionZ *= 0.98;
      }

      if (this.ticksExisted > 5 && Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
         this.motionSnapshotHistory.add(new EntityMotionSnapshot(this));
      }

      double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
      if (Math.abs(this.motionX) < motionZeroThreshold) {
         this.motionX = 0.0;
      }

      if (Math.abs(this.motionY) < motionZeroThreshold) {
         this.motionY = 0.0;
      }

      if (Math.abs(this.motionZ) < motionZeroThreshold) {
         this.motionZ = 0.0;
      }

      this.entityWorld.theProfiler.startSection("ai");
      if (this.isMovementBlocked()) {
         this.jumping = false;
         this.moveStrafing = 0.0F;
         this.moveForward = 0.0F;
         this.randomYawVelocity = 0.0F;
      } else if (this.isServerWorld()) {
         this.entityWorld.theProfiler.startSection("newAi");
         this.updateEntityActionState();
         this.entityWorld.theProfiler.endSection();
      }

      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.startSection("jump");
      EventLiquidJump event = LiquidJumpListener.onLiquidJump(this instanceof EntityPlayerSP, false, false, 0.03999999910593033);
      if (this.jumping) {
         if (this.isInWater() && !event.isIgnoreWater()) {
            this.handleJumpWater(event);
         } else if (this.isInLava() && !event.isIgnoreLava()) {
            this.handleJumpLava(event);
         } else if (this.onGround && this.jumpTicks == 0) {
            this.jump();
            this.jumpTicks = 10;
         }
      } else {
         this.jumpTicks = 0;
      }

      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.startSection("travel");
      this.moveStrafing *= 0.98F;
      this.moveForward *= 0.98F;
      this.randomYawVelocity *= 0.9F;
      this.moveEntityWithHeading(this.moveStrafing, this.moveForward);
      this.lastMotionX = this.motionX;
      this.lastMotionY = this.motionY;
      this.lastMotionZ = this.motionZ;
      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.startSection("push");
      if (!this.entityWorld.isRemote) {
         this.collideWithNearbyEntities();
      }

      this.entityWorld.theProfiler.endSection();
   }

   private Optional getBaritone() {
      return this instanceof EntityPlayerSP ? Optional.ofNullable(BaritoneAPI.getProvider().getBaritoneForPlayer((EntityPlayerSP)this)) : Optional.empty();
   }

   public int getExperiencePoints(EntityPlayer player) {
      return 0;
   }

   public Random getRNG() {
      return this.rand;
   }

   public void removePotionEffectClient(int n) {
      this.activePotionsMap.remove(n);
   }

   @Override
   public void performHurtAnimation() {
      this.hurtTime = this.maxHurtTime = 10;
      this.clientHurtTime = this.maxHurtTime = 10;
      this.unclampedHurtTime = this.maxHurtTime = 10;
      this.attackedAtYaw = 0.0F;
      EntityHurtListener.postEntityHurt(this);
   }

   public void onDeath(DamageSource damageSource) {
      Entity entity = damageSource.getEntity();
      EntityLivingBase attackingEntity = this.getAttackingEntity();
      if (this.scoreValue >= 0 && attackingEntity != null) {
         attackingEntity.addToPlayerScore(this, this.scoreValue);
      }

      if (entity != null) {
         entity.onKillEntity(this);
      }

      this.dead = true;
      this.getCombatTracker().reset();
      if (!this.entityWorld.isRemote) {
         int n = 0;
         if (entity instanceof EntityPlayer) {
            n = EnchantmentHelper.getLootingModifier((EntityLivingBase)entity);
         }

         if (this.canDropLoot() && this.entityWorld.getGameRules().getBoolean("doMobLoot")) {
            this.dropFewItems(this.recentlyHit > 0, n);
            this.dropEquipment(this.recentlyHit > 0, n);
            if (this.recentlyHit > 0 && this.rand.nextFloat() < 0.025F + (float)n * 0.01F) {
               this.addRandomDrop();
            }
         }
      }

      this.entityWorld.setEntityState(this, (byte)3);
   }

   public boolean canBreatheUnderwater() {
      return false;
   }

   public abstract ItemStack getHeldItem();

   public int getLastAttackerTime() {
      return this.lastAttackerTime;
   }

   public void updatePotionEffects() {
      Iterator it = this.activePotionsMap.keySet().iterator();

      while (it.hasNext()) {
         Integer num = (Integer)it.next();
         PotionEffect potionEffect = (PotionEffect)this.activePotionsMap.get(num);
         if (!potionEffect.onUpdate(this)) {
            if (!this.entityWorld.isRemote) {
               it.remove();
               this.onFinishedPotionEffect(potionEffect);
            }
         } else if (potionEffect.getDuration() % 600 == 0) {
            this.onChangedPotionEffect(potionEffect, false);
         }
      }

      if (this.potionsNeedUpdate) {
         if (!this.entityWorld.isRemote) {
            this.updatePotionMetadata();
         }

         this.potionsNeedUpdate = false;
      }

      if (!PotionParticleListener.postPotionParticle(this).isCancelled()) {
         int watchableObjectInt = this.dataWatcher.getWatchableObjectInt(7);
         boolean flag = this.dataWatcher.getWatchableObjectByte(8) > 0;
         if (watchableObjectInt > 0) {
            boolean flag2 = false;
            if (!this.isInvisible()) {
               flag2 = this.rand.nextBoolean();
            } else {
               flag2 = this.rand.nextInt(15) == 0;
            }

            if (flag) {
               flag2 &= this.rand.nextInt(5) == 0;
            }

            if (flag2 && watchableObjectInt > 0) {
               double d = (double)(watchableObjectInt >> 16 & 0xFF) / 255.0;
               double d2 = (double)(watchableObjectInt >> 8 & 0xFF) / 255.0;
               double d3 = (double)(watchableObjectInt >> 0 & 0xFF) / 255.0;
               this.entityWorld
                  .spawnParticle(
                     flag ? EnumParticleTypes.SPELL_MOB_AMBIENT : EnumParticleTypes.SPELL_MOB,
                     this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width,
                     this.posY + this.rand.nextDouble() * (double)this.height,
                     this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width,
                     d,
                     d2,
                     d3,
                     new int[0]
                  );
            }
         }
      }
   }

   @Override
   public void fall(float f, float f2) {
      super.fall(f, f2);
      PotionEffect activePotionEffect = this.getActivePotionEffect(Potion.jump);
      float f3 = activePotionEffect != null ? (float)(activePotionEffect.getAmplifier() + 1) : 0.0F;
      int n = MathHelper.ceiling_float_int((f - 3.0F - f3) * f2);
      if (n > 0) {
         this.playSound(this.getFallSoundString(n), 1.0F, 1.0F);
         this.attackEntityFrom(DamageSource.fall, (float)n);
         int n2 = MathHelper.floor_double(this.posX);
         int n3 = MathHelper.floor_double(this.posY - 0.20000000298023224);
         int n4 = MathHelper.floor_double(this.posZ);
         Block block = this.entityWorld.getBlockState(new BlockPos(n2, n3, n4)).getBlock();
         if (block.getMaterial() != Material.air) {
            Block.SoundType stepSound = block.stepSound;
            this.playSound(stepSound.getStepSound(), stepSound.getVolume() * 0.5F, stepSound.getFrequency() * 0.75F);
         }
      }
   }

   public void updateEntityActionState() {
   }

   public abstract ItemStack getCurrentArmor(int n);

   public void addRandomDrop() {
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.entityWorld.isRemote) {
         int arrowCountInEntity = this.getArrowCountInEntity();
         if (arrowCountInEntity > 0) {
            if (this.arrowHitTimer <= 0) {
               this.arrowHitTimer = 20 * (30 - arrowCountInEntity);
            }

            this.arrowHitTimer--;
            if (this.arrowHitTimer <= 0) {
               this.setArrowCountInEntity(arrowCountInEntity - 1);
            }
         }

         for (int i = 0; i < 5; i++) {
            ItemStack stack = this.previousEquipment[i];
            ItemStack equipmentInSlot = this.getEquipmentInSlot(i);
            if (!ItemStack.areItemStacksEqual(equipmentInSlot, stack)) {
               ((WorldServer)this.entityWorld).getEntityTracker().sendToAllTrackingEntity(this, new S04PacketEntityEquipment(this.getEntityId(), i, equipmentInSlot));
               if (stack != null) {
                  this.attributeMap.removeAttributeModifiers(stack.getAttributeModifiers());
               }

               if (equipmentInSlot != null) {
                  this.attributeMap.applyAttributeModifiers(equipmentInSlot.getAttributeModifiers());
               }

               this.previousEquipment[i] = equipmentInSlot == null ? null : equipmentInSlot.copy();
            }
         }

         if (this.ticksExisted % 20 == 0) {
            this.getCombatTracker().reset();
         }
      }

      this.onLivingUpdate();
      double d = this.posX - this.prevPosX;
      double d2 = this.posZ - this.prevPosZ;
      float f = (float)(d * d + d2 * d2);
      float renderYawOffset2 = this.renderYawOffset;
      float f2 = 0.0F;
      this.prevOnGroundSpeedFactor = this.onGroundSpeedFactor;
      float f3 = 0.0F;
      if (f > 0.0025000002F) {
         f3 = 1.0F;
         f2 = (float)Math.sqrt((double)f) * 3.0F;
         renderYawOffset2 = (float)MathHelper.atan2(d2, d) * 180.0F / 3.1415927F - 90.0F;
      }

      if (this.swingProgress > 0.0F) {
         renderYawOffset2 = this.rotationYaw;
      }

      if (!this.onGround) {
         f3 = 0.0F;
      }

      this.onGroundSpeedFactor = this.onGroundSpeedFactor + (f3 - this.onGroundSpeedFactor) * 0.3F;
      this.entityWorld.theProfiler.startSection("headTurn");
      f2 = this.updateDistance(renderYawOffset2, f2);
      this.entityWorld.theProfiler.endSection();
      this.entityWorld.theProfiler.startSection("rangeChecks");

      while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
         this.prevRotationYaw -= 360.0F;
      }

      while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
         this.prevRotationYaw += 360.0F;
      }

      while (this.renderYawOffset - this.prevRenderYawOffset < -180.0F) {
         this.prevRenderYawOffset -= 360.0F;
      }

      while (this.renderYawOffset - this.prevRenderYawOffset >= 180.0F) {
         this.prevRenderYawOffset += 360.0F;
      }

      while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
         this.prevRotationPitch -= 360.0F;
      }

      while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
         this.prevRotationPitch += 360.0F;
      }

      while (this.rotationYawHead - this.prevRotationYawHead < -180.0F) {
         this.prevRotationYawHead -= 360.0F;
      }

      while (this.rotationYawHead - this.prevRotationYawHead >= 180.0F) {
         this.prevRotationYawHead += 360.0F;
      }

      if (this instanceof EntityPlayerSP) {
         YawPitchHelper yawPitchHelper = Augustus.getInstance().getYawPitchHelper();
         float realLastYaw = yawPitchHelper.realLastYaw;
         yawPitchHelper.realLastYaw = yawPitchHelper.wrapRelativeYaw(yawPitchHelper.realYaw, realLastYaw);
         float realLastPitch = yawPitchHelper.realLastPitch;
         yawPitchHelper.realLastPitch = yawPitchHelper.wrapRelativeYaw(yawPitchHelper.realPitch, realLastPitch);
      }

      this.lastMotionX = this.motionX;
      this.lastMotionY = this.motionY;
      this.lastMotionZ = this.motionZ;
      this.entityWorld.theProfiler.endSection();
      this.movedDistance += f2;
   }

   public void collideWithEntity(Entity entity) {
      entity.applyEntityCollision(this);
   }

   public boolean isServerWorld() {
      return !this.entityWorld.isRemote;
   }

   public final float getCurrentHealth() {
      return this.dataWatcher.getWatchableObjectFloat(6);
   }

   @Override
   public boolean getAlwaysRenderNameTagForRender() {
      return false;
   }

   public int getJumpTicks() {
      return this.jumpTicks;
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else if (this.entityWorld.isRemote) {
         return false;
      } else {
         this.age = 0;
         if (this.getCurrentHealth() <= 0.0F) {
            return false;
         } else if (damageSource.isFireDamage() && this.isPotionActive(Potion.fireResistance)) {
            return false;
         } else {
            if ((damageSource == DamageSource.anvil || damageSource == DamageSource.fallingBlock) && this.getEquipmentInSlot(4) != null) {
               this.getEquipmentInSlot(4).damageItem((int)(f * 4.0F + this.rand.nextFloat() * f * 2.0F), this);
               f *= 0.75F;
            }

            this.limbSwingAmount = 1.5F;
            boolean flag = true;
            if ((float)this.hurtResistantTime > (float)this.maxHurtResistantTime / 2.0F) {
               if (f <= this.lastDamage) {
                  return false;
               }

               this.damageEntity(damageSource, f - this.lastDamage);
               this.lastDamage = f;
               flag = false;
            } else {
               this.lastDamage = f;
               this.hurtResistantTime = this.maxHurtResistantTime;
               this.damageEntity(damageSource, f);
               this.hurtTime = this.maxHurtTime = 10;
               this.clientHurtTime = this.maxHurtTime = 10;
               this.unclampedHurtTime = this.maxHurtTime = 10;
               EntityHurtListener.postEntityHurt(this);
            }

            this.attackedAtYaw = 0.0F;
            Entity entity = damageSource.getEntity();
            if (entity != null) {
               if (entity instanceof EntityLivingBase) {
                  this.setRevengeTarget((EntityLivingBase)entity);
               }

               if (entity instanceof EntityPlayer) {
                  this.recentlyHit = 100;
                  this.attackingPlayer = (EntityPlayer)entity;
               } else if (entity instanceof EntityWolf) {
                  if (((EntityWolf)entity).isTamed()) {
                     this.recentlyHit = 100;
                     this.attackingPlayer = null;
                  }
               }
            }

            if (flag) {
               this.entityWorld.setEntityState(this, (byte)2);
               if (damageSource != DamageSource.drown) {
                  this.setBeenAttacked();
               }

               if (entity != null) {
                  double d = entity.posX - this.posX;

                  double d2;
                  for (d2 = entity.posZ - this.posZ; d * d + d2 * d2 < 1.0E-4; d2 = (Math.random() - Math.random()) * 0.01) {
                     d = (Math.random() - Math.random()) * 0.01;
                  }

                  this.attackedAtYaw = (float)(MathHelper.atan2(d2, d) * 180.0 / 3.141592653589793 - (double)this.rotationYaw);
                  this.knockBack(entity, f, d, d2);
               } else {
                  this.attackedAtYaw = (float)((int)(Math.random() * 2.0) * 180);
               }
            }

            if (this.getCurrentHealth() <= 0.0F) {
               String deathSound = this.getDeathSound();
               if (flag && deathSound != null) {
                  this.playSound(deathSound, this.getSoundVolume(), this.getSoundPitch());
               }

               this.onDeath(damageSource);
            } else {
               String hurtSound = this.getHurtSound();
               if (flag && hurtSound != null) {
                  this.playSound(hurtSound, this.getSoundVolume(), this.getSoundPitch());
               }
            }

            return true;
         }
      }
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 2) {
         this.limbSwingAmount = 1.5F;
         this.hurtResistantTime = this.maxHurtResistantTime;
         this.hurtTime = this.maxHurtTime = 10;
         this.clientHurtTime = this.maxHurtTime = 10;
         this.unclampedHurtTime = this.maxHurtTime = 10;
         EntityHurtListener.postEntityHurt(this);
         this.attackedAtYaw = 0.0F;
         if (this.getHurtSound() != null) {
            this.playSound(this.getHurtSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         }

         this.attackEntityFrom(DamageSource.generic, 0.0F);
      } else if (b == 3) {
         if (this.getDeathSound() != null) {
            this.playSound(this.getDeathSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         }

         this.setHealth(0.0F);
         this.onDeath(DamageSource.generic);
      } else {
         super.handleStatusUpdate(b);
      }
   }

   public IAttributeInstance getEntityAttribute(IAttribute attribute) {
      return this.getAttributeMap().getAttribute(attribute);
   }

   public boolean isChild() {
      return false;
   }

   public int getTotalArmorValue() {
      int n = 0;

      for (ItemStack stack : this.getInventory()) {
         if (stack != null && stack.getItem() instanceof ItemArmor) {
            int damageReduceAmount = ((ItemArmor)stack.getItem()).damageReduceAmount;
            n += damageReduceAmount;
         }
      }

      return n;
   }

   public void swingItem() {
      if (!this.isSwingInProgress || this.swingProgressInt >= this.getArmSwingAnimationEnd() / 2 || this.swingProgressInt < 0) {
         this.swingProgressInt = -1;
         this.isSwingInProgress = true;
         if (this.entityWorld instanceof WorldServer) {
            ((WorldServer)this.entityWorld).getEntityTracker().sendToAllTrackingEntity(this, new S0BPacketAnimation(this, 0));
         }
      }
   }

   public void setJumpTicks(int n) {
      this.jumpTicks = n;
   }

   public float updateDistance(float f, float f2) {
      float f3 = MathHelper.wrapAngleTo180_float(f - this.renderYawOffset);
      this.renderYawOffset += f3 * 0.3F;
      float f4 = MathHelper.wrapAngleTo180_float(this.rotationYaw - this.renderYawOffset);
      boolean flag = f4 < -90.0F || f4 >= 90.0F;
      if (f4 < -75.0F) {
         f4 = -75.0F;
      }

      if (f4 >= 75.0F) {
         f4 = 75.0F;
      }

      this.renderYawOffset = this.rotationYaw - f4;
      if (f4 * f4 > 2500.0F) {
         this.renderYawOffset += f4 * 0.2F;
      }

      if (flag) {
         f2 *= -1.0F;
      }

      return f2;
   }

   public void moveEntityWithHeading(float f, float f2) {
      if (this.isServerWorld()) {
         this.ticksSinceJump++;
         EventLiquidPhysics event = LiquidPhysicsListener.onLiquidPhysics(this instanceof EntityPlayerSP, 0.8F, 0.800000011920929, 0.02, false, 0.5, 0.5, 0.02, false, 0.08);
         if (!this.isInWater() || event.isIgnoreWater() || this instanceof EntityPlayer && ((EntityPlayer)this).capabilities.isFlying) {
            if (!this.isInLava() || event.isIgnoreLava() || this instanceof EntityPlayer && ((EntityPlayer)this).capabilities.isFlying) {
               float f3 = 0.91F;
               if (this.onGround) {
                  f3 = this.entityWorld
                        .getBlockState(
                           new BlockPos(
                              MathHelper.floor_double(this.posX),
                              MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1,
                              MathHelper.floor_double(this.posZ)
                           )
                        )
                        .getBlock()
                        .slipperiness
                     * 0.91F;
               }

               float f4 = 0.16277136F / (f3 * f3 * f3);
               float f5;
               if (this.onGround) {
                  f5 = this.getAIMoveSpeed() * f4;
               } else {
                  f5 = this.jumpMovementFactor;
               }

               this.moveFlying(f, f2, f5);
               f3 = 0.91F;
               if (this.onGround) {
                  f3 = this.entityWorld
                        .getBlockState(
                           new BlockPos(
                              MathHelper.floor_double(this.posX),
                              MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1,
                              MathHelper.floor_double(this.posZ)
                           )
                        )
                        .getBlock()
                        .slipperiness
                     * 0.91F;
               }

               if (this.isOnLadder()) {
                  float f6 = 0.15F;
                  this.motionX = MathHelper.clamp_double(this.motionX, (double)(-f6), (double)f6);
                  this.motionZ = MathHelper.clamp_double(this.motionZ, (double)(-f6), (double)f6);
                  this.fallDistance = 0.0F;
                  if (this.motionY < -0.15) {
                     this.motionY = -0.15;
                  }

                  if ((this.isSneaking() && this instanceof EntityPlayer) && this.motionY < 0.0) {
                     this.motionY = 0.0;
                  }
               }

               this.moveEntity(this.motionX, this.motionY, this.motionZ);
               if (this.isCollidedHorizontally && this.isOnLadder()) {
                  if (this instanceof EntityPlayerSP) {
                     this.motionY = LadderListener.postLadder(0.2).getMotionY();
                  } else {
                     this.motionY = 0.2;
                  }
               }

               if (this.entityWorld.isRemote
                  && (
                     !this.entityWorld.isBlockLoaded(new BlockPos((int)this.posX, 0, (int)this.posZ))
                        || !this.entityWorld.getChunkFromBlockCoords(new BlockPos((int)this.posX, 0, (int)this.posZ)).isLoaded()
                  )) {
                  if (this.posY > 0.0) {
                     this.motionY = -0.1;
                  } else {
                     this.motionY = 0.0;
                  }
               } else {
                  this.motionY = this.motionY - event.getAirGravity();
               }

               this.motionY *= 0.9800000190734863;
               this.motionX *= (double)f3;
               this.motionZ *= (double)f3;
            } else {
               double posY2 = this.posY;
               this.moveFlying(f, f2, 0.02F);
               this.moveEntity(this.motionX, this.motionY, this.motionZ);
               this.motionX = this.motionX * event.getLavaHorizontalFriction();
               this.motionY = this.motionY * event.getLavaVerticalFriction();
               this.motionZ = this.motionZ * event.getLavaHorizontalFriction();
               this.motionY = this.motionY - event.getLavaGravity();
               if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579 - this.posY + posY2, this.motionZ)) {
                  this.motionY = 0.30000001192092896;
               }
            }
         } else if (this instanceof EntityPlayerSP) {
            if (!SwimListener.onSwim(f2, f).isCancelled()) {
               double posY3 = this.posY;
               float f7 = event.getWaterHorizontalFriction();
               float f8 = 0.02F;
               float depthStriderModifier = (float)EnchantmentHelper.getDepthStriderModifier(this);
               if (depthStriderModifier > 3.0F) {
                  depthStriderModifier = 3.0F;
               }

               if (!this.onGround) {
                  depthStriderModifier *= 0.5F;
               }

               if (depthStriderModifier > 0.0F) {
                  f7 += (0.54600006F - f7) * depthStriderModifier / 3.0F;
                  f8 += (this.getAIMoveSpeed() - f8) * depthStriderModifier / 3.0F;
               }

               this.moveFlying(f, f2, f8);
               this.moveEntity(this.motionX, this.motionY, this.motionZ);
               this.motionX *= (double)f7;
               this.motionY = this.motionY * event.getWaterVerticalFriction();
               this.motionZ *= (double)f7;
               this.motionY = this.motionY - event.getWaterGravity();
               if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579 - this.posY + posY3, this.motionZ)) {
                  this.motionY = 0.30000001192092896;
               }
            }
         } else {
            double posY4 = this.posY;
            float f9 = 0.8F;
            float f10 = 0.02F;
            float depthStriderModifier2 = (float)EnchantmentHelper.getDepthStriderModifier(this);
            if (depthStriderModifier2 > 3.0F) {
               depthStriderModifier2 = 3.0F;
            }

            if (!this.onGround) {
               depthStriderModifier2 *= 0.5F;
            }

            if (depthStriderModifier2 > 0.0F) {
               f9 += (0.54600006F - f9) * depthStriderModifier2 / 3.0F;
               f10 += (this.getAIMoveSpeed() - f10) * depthStriderModifier2 / 3.0F;
            }

            this.moveFlying(f, f2, f10);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)f9;
            this.motionY *= 0.800000011920929;
            this.motionZ *= (double)f9;
            this.motionY -= 0.02;
            if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579 - this.posY + posY4, this.motionZ)) {
               this.motionY = 0.30000001192092896;
            }
         }
      }

      this.prevLimbSwingAmount = this.limbSwingAmount;
      double d = this.posX - this.prevPosX;
      double d2 = this.posZ - this.prevPosZ;
      float f11 = MathHelper.sqrt_double(d * d + d2 * d2) * 4.0F;
      if (f11 > 1.0F) {
         f11 = 1.0F;
      }

      this.limbSwingAmount = this.limbSwingAmount + (f11 - this.limbSwingAmount) * 0.4F;
      this.limbSwing = this.limbSwing + this.limbSwingAmount;
   }

   public EntityLivingBase getAttackingEntity() {
      return (EntityLivingBase)(this._combatTracker.getBestAttacker() != null
         ? this._combatTracker.getBestAttacker()
         : (this.attackingPlayer != null ? this.attackingPlayer : (this.entityLivingToAttack != null ? this.entityLivingToAttack : null)));
   }

   public void onNewPotionEffect(PotionEffect potionEffect) {
      this.potionsNeedUpdate = true;
      if (!this.entityWorld.isRemote) {
         Potion.potionTypes[potionEffect.getPotionID()].applyAttributesModifiersToEntity(this, this.getAttributeMap(), potionEffect.getAmplifier());
      }
   }

   public void jump() {
      this.motionY = (double)this.getJumpUpwardsMotion();
      if (this.isPotionActive(Potion.jump)) {
         this.motionY = this.motionY + (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
      }

      this.ticksSinceJump = 0;
      if (this.isSprinting()) {
         float f = this.overrideJumpYaw(this.rotationYaw) * 0.017453292F;
         this.motionX = this.motionX - (double)(MathHelper.sin(f) * 0.2F);
         this.motionZ = this.motionZ + (double)(MathHelper.cos(f) * 0.2F);
      }

      this.isAirBorne = true;
   }

   public void resetPotionEffectMetadata() {
      this.dataWatcher.updateObject(8, (byte)0);
      this.dataWatcher.updateObject(7, 0);
   }

   public boolean isPotionApplicable(PotionEffect potionEffect) {
      if (this.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD) {
         return true;
      } else {
         int potionID = potionEffect.getPotionID();
         return potionID != Potion.regeneration.id && potionID != Potion.poison.id;
      }
   }

   @Override
   public void setBeenAttacked() {
      this.velocityChanged = this.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue();
   }

   @Override
   public void onKillCommand() {
      this.attackEntityFrom(DamageSource.outOfWorld, 3.4028235E38F);
   }

   public EntityLivingBase getLastAttacker() {
      return this.lastAttacker;
   }

   public float getJumpUpwardsMotion() {
      return 0.42F;
   }

   public void addPotionEffect(PotionEffect potionEffect) {
      if (this.isPotionApplicable(potionEffect)) {
         if (this.activePotionsMap.containsKey(potionEffect.getPotionID())) {
            ((PotionEffect)this.activePotionsMap.get(potionEffect.getPotionID())).combine(potionEffect);
            this.onChangedPotionEffect((PotionEffect)this.activePotionsMap.get(potionEffect.getPotionID()), true);
         } else {
            this.activePotionsMap.put(potionEffect.getPotionID(), potionEffect);
            this.onNewPotionEffect(potionEffect);
         }
      }
   }

   public float getSoundVolume() {
      return 1.0F;
   }

   @Override
   public Vec3 getLookVec() {
      return this.getLook(1.0F);
   }

   public final float getMaxHealth() {
      return (float)this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
   }

   public Team getTeam() {
      return this.entityWorld.getScoreboard().getPlayersTeam(this.getUniqueID().toString());
   }

   public void sendEndCombat() {
   }

   public void setAIMoveSpeed(float f) {
      this.aiMoveSpeed = f;
   }



   public static class FilterPredicate implements Predicate {
      public final EntityLivingBase entity;

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity2) {
         return entity2.canBePushed();
      }

      public FilterPredicate(EntityLivingBase entity2) {
         this.entity = entity2;
      }
   }
}
