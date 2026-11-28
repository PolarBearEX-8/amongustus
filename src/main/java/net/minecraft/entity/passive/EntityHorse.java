package net.minecraft.entity.passive;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityHorse extends EntityAnimal implements IInvBasic {
   private static final Predicate horseBreedingSelector = new EntityHorse.FilterPredicate();
   private static final IAttribute horseJumpStrength = new RangedAttribute((IAttribute)null, "horse.jumpStrength", 0.7, 0.0, 2.0)
      .setDescription("Jump Strength")
      .setShouldWatch(true);
   public static final String[] horseArmorTextures = new String[]{
      null,
      "textures/entity/horse/armor/horse_armor_iron.png",
      "textures/entity/horse/armor/horse_armor_gold.png",
      "textures/entity/horse/armor/horse_armor_diamond.png"
   };
   public static final String[] HORSE_ARMOR_TEXTURES_ABBR = new String[]{"", "meo", "goo", "dio"};
   private static final int[] armorValues = new int[]{0, 5, 7, 11};
   private static final String[] horseTextures = new String[]{
      "textures/entity/horse/horse_white.png",
      "textures/entity/horse/horse_creamy.png",
      "textures/entity/horse/horse_chestnut.png",
      "textures/entity/horse/horse_brown.png",
      "textures/entity/horse/horse_black.png",
      "textures/entity/horse/horse_gray.png",
      "textures/entity/horse/horse_darkbrown.png"
   };
   private static final String[] HORSE_TEXTURES_ABBR = new String[]{"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
   private static final String[] horseMarkingTextures = new String[]{
      null,
      "textures/entity/horse/horse_markings_white.png",
      "textures/entity/horse/horse_markings_whitefield.png",
      "textures/entity/horse/horse_markings_whitedots.png",
      "textures/entity/horse/horse_markings_blackdots.png"
   };
   private static final String[] HORSE_MARKING_TEXTURES_ABBR = new String[]{"", "wo_", "wmo", "wdo", "bdo"};
   private int eatingHaystackCounter;
   private int openMouthCounter;
   private int jumpRearingCounter;
   public int tailCounter;
   public int sprintCounter;
   public boolean horseJumping;
   private AnimalChest horseChest;
   private boolean hasReproduced;
   public int temper;
   public float jumpPower;
   private boolean allowStandSliding;
   private float headLean;
   private float prevHeadLean;
   private float rearingAmount;
   private float prevRearingAmount;
   private float mouthOpenness;
   private float prevMouthOpenness;
   private int gallopTime;
   private String texturePrefix;
   private String[] horseTexturesArray = new String[3];
   private boolean hasUniqueTexture = false;

   public EntityHorse getClosestHorse(Entity entity, double d) {
      double maxvalue = Double.MAX_VALUE;
      Entity entity2 = null;

      for (Entity entity3 : (Iterable<Entity>)(this.entityWorld.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().addCoord(d, d, d), horseBreedingSelector))) {
         double distanceSq = entity3.getDistanceSq(entity.posX, entity.posY, entity.posZ);
         if (distanceSq < maxvalue) {
            entity2 = entity3;
            maxvalue = distanceSq;
         }
      }

      return (EntityHorse)entity2;
   }

   public boolean isAdultHorse() {
      return !this.isChild();
   }

   public static boolean isArmorItem(Item item) {
      return item == Items.iron_horse_armor || item == Items.golden_horse_armor || item == Items.diamond_horse_armor;
   }

   public void setHorseArmorStack(ItemStack stack) {
      this.dataWatcher.updateObject(22, this.getHorseArmorIndex(stack));
      this.resetTexturePrefix();
   }

   private double getModifiedJumpStrength() {
      return 0.4F + this.rand.nextDouble() * 0.2 + this.rand.nextDouble() * 0.2 + this.rand.nextDouble() * 0.2;
   }

   @Override
   public float getSoundVolume() {
      return 0.8F;
   }

   public boolean prepareChunkForSpawn() {
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.posZ);
      this.entityWorld.getBiomeGenForCoords(new BlockPos(n, 0, n2));
      return true;
   }

   @Override
   public boolean isOnLadder() {
      return false;
   }

   public int increaseTemper(int n) {
      int n2 = MathHelper.clamp_int(this.getTemper() + n, 0, this.getMaxTemper());
      this.setTemper(n2);
      return n2;
   }

   @Override
   public void onLivingUpdate() {
      if (this.rand.nextInt(200) == 0) {
         this.moveTail();
      }

      super.onLivingUpdate();
      if (!this.entityWorld.isRemote) {
         if (this.rand.nextInt(900) == 0 && this.deathTime == 0) {
            this.heal(1.0F);
         }

         if (!this.isEatingHaystack()
            && this.riddenByEntity == null
            && this.rand.nextInt(300) == 0
            && this.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - 1, MathHelper.floor_double(this.posZ)))
                  .getBlock()
               == Blocks.grass) {
            this.setEatingHaystack(true);
         }

         if (this.isEatingHaystack() && ++this.eatingHaystackCounter > 50) {
            this.eatingHaystackCounter = 0;
            this.setEatingHaystack(false);
         }

         if (this.isBreeding() && !this.isAdultHorse() && !this.isEatingHaystack()) {
            EntityHorse closestHorse = this.getClosestHorse(this, 16.0);
            if (closestHorse != null && this.getDistanceSqToEntity(closestHorse) > 4.0) {
               this.navigator.getPathToEntityLiving(closestHorse);
            }
         }
      }
   }

   @Override
   public boolean allowLeashing() {
      return !this.isUndead() && super.allowLeashing();
   }

   public boolean isTame() {
      return this.getHorseWatchableBoolean(2);
   }

   public void dropChests() {
      if (!this.entityWorld.isRemote && this.isChested()) {
         this.dropItem(Item.getItemFromBlock(Blocks.chest), 1);
         this.setChested(false);
      }
   }

   public void spawnHorseParticles(boolean flag) {
      EnumParticleTypes enumParticleTypes = flag ? EnumParticleTypes.HEART : EnumParticleTypes.SMOKE_NORMAL;

      for (int i = 0; i < 7; i++) {
         double d = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         double d3 = this.rand.nextGaussian() * 0.02;
         this.entityWorld
            .spawnParticle(
               enumParticleTypes,
               this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
               this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height),
               this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
               d,
               d2,
               d3,
               new int[0]
            );
      }
   }

   private int getHorseArmorIndex(ItemStack stack) {
      if (stack == null) {
         return 0;
      } else {
         Item item = stack.getItem();
         return item == Items.iron_horse_armor ? 1 : (item == Items.golden_horse_armor ? 2 : (item == Items.diamond_horse_armor ? 3 : 0));
      }
   }

   public int getMaxTemper() {
      return 100;
   }

   public int getTemper() {
      return this.temper;
   }

   public void setBreeding(boolean flag) {
      this.setHorseWatchableBoolean(16, flag);
   }

   public String[] getVariantTexturePaths() {
      if (this.texturePrefix == null) {
         this.setHorseTexturePaths();
      }

      return this.horseTexturesArray;
   }

   private void updateHorseSlots() {
      if (!this.entityWorld.isRemote) {
         this.setHorseSaddled(this.horseChest.getStackInSlot(0) != null);
         if (this.canWearArmor()) {
            this.setHorseArmorStack(this.horseChest.getStackInSlot(1));
         }
      }
   }

   public boolean getHasReproduced() {
      return this.hasReproduced;
   }

   @Override
   public int getTalkInterval() {
      return 400;
   }

   public boolean isHorseSaddled() {
      return this.getHorseWatchableBoolean(4);
   }

   public void setTemper(int n) {
      this.temper = n;
   }

   @Override
   public void moveEntityWithHeading(float f, float f2) {
      if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase && this.isHorseSaddled()) {
         this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
         this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
         this.setRotation(this.rotationYaw, this.rotationPitch);
         this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
         f = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
         f2 = ((EntityLivingBase)this.riddenByEntity).moveForward;
         if (f2 <= 0.0F) {
            f2 *= 0.25F;
            this.gallopTime = 0;
         }

         if (this.onGround && this.jumpPower == 0.0F && this.isRearing() && !this.allowStandSliding) {
            f = 0.0F;
            f2 = 0.0F;
         }

         if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround) {
            this.motionY = this.getHorseJumpStrength() * (double)this.jumpPower;
            if (this.isPotionActive(Potion.jump)) {
               this.motionY = this.motionY + (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
            }

            this.setHorseJumping(true);
            this.isAirBorne = true;
            if (f2 > 0.0F) {
               float f3 = MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F);
               float f4 = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F);
               this.motionX = this.motionX + (double)(-0.4F * f3 * this.jumpPower);
               this.motionZ = this.motionZ + (double)(0.4F * f4 * this.jumpPower);
               this.playSound("mob.horse.jump", 0.4F, 1.0F);
            }

            this.jumpPower = 0.0F;
         }

         this.stepHeight = 1.0F;
         this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
         if (!this.entityWorld.isRemote) {
            this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
            super.moveEntityWithHeading(f, f2);
         }

         if (this.onGround) {
            this.jumpPower = 0.0F;
            this.setHorseJumping(false);
         }

         this.prevLimbSwingAmount = this.limbSwingAmount;
         double d = this.posX - this.prevPosX;
         double d2 = this.posZ - this.prevPosZ;
         float f5 = MathHelper.sqrt_double(d * d + d2 * d2) * 4.0F;
         if (f5 > 1.0F) {
            f5 = 1.0F;
         }

         this.limbSwingAmount = this.limbSwingAmount + (f5 - this.limbSwingAmount) * 0.4F;
         this.limbSwing = this.limbSwing + this.limbSwingAmount;
      } else {
         this.stepHeight = 0.5F;
         this.jumpMovementFactor = 0.02F;
         super.moveEntityWithHeading(f, f2);
      }
   }

   public void setHorseVariant(int n) {
      this.dataWatcher.updateObject(20, n);
      this.resetTexturePrefix();
   }

   public boolean isSterile() {
      return this.isUndead() || this.getHorseType() == 2;
   }

   public void dropChestItems() {
      this.dropItemsInChest(this, this.horseChest);
      this.dropChests();
   }

   public EntityHorse(World world) {
      super(world);
      this.setSize(1.4F, 1.6F);
      this.isImmuneToFire = false;
      this.setChested(false);
      ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIPanic(this, 1.2));
      this.tasks.addTask(1, new EntityAIRunAroundLikeCrazy(this, 1.2));
      this.tasks.addTask(2, new EntityAIMate(this, 1.0));
      this.tasks.addTask(4, new EntityAIFollowParent(this, 1.0));
      this.tasks.addTask(6, new EntityAIWander(this, 0.7));
      this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.initHorseChest();
   }

   @Override
   public boolean canBePushed() {
      return this.riddenByEntity == null;
   }

   @Override
   public float getEyeHeight() {
      return this.height;
   }

   public float getMouthOpennessAngle(float f) {
      return this.prevMouthOpenness + (this.mouthOpenness - this.prevMouthOpenness) * f;
   }

   public void setHorseJumping(boolean flag) {
      this.horseJumping = flag;
   }

   @Override
   public int getTotalArmorValue() {
      return armorValues[this.getHorseArmorIndexSynced()];
   }

   public void setChested(boolean flag) {
      this.setHorseWatchableBoolean(8, flag);
   }

   @Override
   public void fall(float f, float f2) {
      if (f > 1.0F) {
         this.playSound("mob.horse.land", 0.4F, 1.0F);
      }

      int n = MathHelper.ceiling_float_int((f * 0.5F - 3.0F) * f2);
      if (n > 0) {
         this.attackEntityFrom(DamageSource.fall, (float)n);
         if (this.riddenByEntity != null) {
            this.riddenByEntity.attackEntityFrom(DamageSource.fall, (float)n);
         }

         Block block = this.entityWorld.getBlockState(new BlockPos(this.posX, this.posY - 0.2 - (double)this.prevRotationYaw, this.posZ)).getBlock();
         if (block.getMaterial() != Material.air && !this.isSilent()) {
            Block.SoundType stepSound = block.stepSound;
            this.entityWorld.playSoundAtEntity(this, stepSound.getStepSound(), stepSound.getVolume() * 0.5F, stepSound.getFrequency() * 0.75F);
         }
      }
   }

   public void setHorseType(int n) {
      this.dataWatcher.updateObject(19, (byte)n);
      this.resetTexturePrefix();
   }

   public boolean hasUniqueTexture() {
      return this.hasUniqueTexture;
   }

   private void initHorseChest() {
      AnimalChest horseChest2 = this.horseChest;
      this.horseChest = new AnimalChest("HorseChest", this.getChestSize());
      this.horseChest.setCustomName(this.getName());
      if (horseChest2 != null) {
         horseChest2.removeInventoryChangeListener(this);
         int n = Math.min(horseChest2.getSizeInventory(), this.horseChest.getSizeInventory());

         for (int i = 0; i < n; i++) {
            ItemStack stackInSlot = horseChest2.getStackInSlot(i);
            if (stackInSlot != null) {
               this.horseChest.setInventorySlotContents(i, stackInSlot.copy());
            }
         }
      }

      this.horseChest.addInventoryChangeListener(this);
      this.updateHorseSlots();
   }

   private void dropItemsInChest(Entity entity, AnimalChest animalChest) {
      if (animalChest != null && !this.entityWorld.isRemote) {
         for (int i = 0; i < animalChest.getSizeInventory(); i++) {
            ItemStack stackInSlot = animalChest.getStackInSlot(i);
            if (stackInSlot != null) {
               this.entityDropItem(stackInSlot, 0.0F);
            }
         }
      }
   }

   @Override
   public boolean replaceItemInInventory(int n, ItemStack stack) {
      if (n == 499 && this.canCarryChest()) {
         if (stack == null && this.isChested()) {
            this.setChested(false);
            this.initHorseChest();
            return true;
         }

         if (stack != null && stack.getItem() == Item.getItemFromBlock(Blocks.chest) && !this.isChested()) {
            this.setChested(true);
            this.initHorseChest();
            return true;
         }
      }

      int n2 = n - 400;
      if (n2 >= 0 && n2 < 2 && n2 < this.horseChest.getSizeInventory()) {
         if (n2 == 0 && stack != null && stack.getItem() != Items.saddle) {
            return false;
         } else if (n2 == 1 && (stack != null && !isArmorItem(stack.getItem()) || !this.canWearArmor())) {
            return false;
         } else {
            this.horseChest.setInventorySlotContents(n2, stack);
            this.updateHorseSlots();
            return true;
         }
      } else {
         int n3 = n - 500 + 2;
         if (n3 >= 2 && n3 < this.horseChest.getSizeInventory()) {
            this.horseChest.setInventorySlotContents(n3, stack);
            return true;
         } else {
            return false;
         }
      }
   }

   public void setOwnerId(String str) {
      this.dataWatcher.updateObject(21, str);
   }

   @Override
   public void onLeashDistance(float f) {
      if (f > 6.0F && this.isEatingHaystack()) {
         this.setEatingHaystack(false);
      }
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.entityWorld.isRemote && this.dataWatcher.hasObjectChanged()) {
         this.dataWatcher.markClean();
         this.resetTexturePrefix();
      }

      if (this.openMouthCounter > 0 && ++this.openMouthCounter > 30) {
         this.openMouthCounter = 0;
         this.setHorseWatchableBoolean(128, false);
      }

      if (!this.entityWorld.isRemote && this.jumpRearingCounter > 0 && ++this.jumpRearingCounter > 20) {
         this.jumpRearingCounter = 0;
         this.setRearing(false);
      }

      if (this.tailCounter > 0 && ++this.tailCounter > 8) {
         this.tailCounter = 0;
      }

      if (this.sprintCounter > 0) {
         this.sprintCounter++;
         if (this.sprintCounter > 300) {
            this.sprintCounter = 0;
         }
      }

      this.prevHeadLean = this.headLean;
      if (this.isEatingHaystack()) {
         this.headLean = this.headLean + (1.0F - this.headLean) * 0.4F + 0.05F;
         if (this.headLean > 1.0F) {
            this.headLean = 1.0F;
         }
      } else {
         this.headLean = this.headLean + ((0.0F - this.headLean) * 0.4F - 0.05F);
         if (this.headLean < 0.0F) {
            this.headLean = 0.0F;
         }
      }

      this.prevRearingAmount = this.rearingAmount;
      if (this.isRearing()) {
         this.prevHeadLean = this.headLean = 0.0F;
         this.rearingAmount = this.rearingAmount + (1.0F - this.rearingAmount) * 0.4F + 0.05F;
         if (this.rearingAmount > 1.0F) {
            this.rearingAmount = 1.0F;
         }
      } else {
         this.allowStandSliding = false;
         this.rearingAmount = this.rearingAmount + ((0.8F * this.rearingAmount * this.rearingAmount * this.rearingAmount - this.rearingAmount) * 0.6F - 0.05F);
         if (this.rearingAmount < 0.0F) {
            this.rearingAmount = 0.0F;
         }
      }

      this.prevMouthOpenness = this.mouthOpenness;
      if (this.getHorseWatchableBoolean(128)) {
         this.mouthOpenness = this.mouthOpenness + (1.0F - this.mouthOpenness) * 0.7F + 0.05F;
         if (this.mouthOpenness > 1.0F) {
            this.mouthOpenness = 1.0F;
         }
      } else {
         this.mouthOpenness = this.mouthOpenness + ((0.0F - this.mouthOpenness) * 0.7F - 0.05F);
         if (this.mouthOpenness < 0.0F) {
            this.mouthOpenness = 0.0F;
         }
      }
   }

   public String getAngrySoundName() {
      this.openHorseMouth();
      this.makeHorseRear();
      int horseType = this.getHorseType();
      return horseType != 3 && horseType != 4 ? (horseType != 1 && horseType != 2 ? "mob.horse.angry" : "mob.horse.donkey.angry") : null;
   }

   public void setHorseSaddled(boolean flag) {
      this.setHorseWatchableBoolean(4, flag);
   }

   public void setEatingHaystack(boolean flag) {
      this.setEating(flag);
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, IEntityLivingData entityLivingData) {
      entityLivingData = super.onInitialSpawn(difficultyInstance, entityLivingData);
      int n = 0;
      int n2 = 0;
      if (entityLivingData instanceof EntityHorse.GroupData) {
         n = ((EntityHorse.GroupData)entityLivingData).horseType;
         n2 = ((EntityHorse.GroupData)entityLivingData).horseVariant & 0xFF | this.rand.nextInt(5) << 8;
      } else {
         if (this.rand.nextInt(10) == 0) {
            n = 1;
         } else {
            int n3 = this.rand.nextInt(7);
            int n4 = this.rand.nextInt(5);
            n = 0;
            n2 = n3 | n4 << 8;
         }

         entityLivingData = new EntityHorse.GroupData(n, n2);
      }

      this.setHorseType(n);
      this.setHorseVariant(n2);
      if (this.rand.nextInt(5) == 0) {
         this.setGrowingAge(-24000);
      }

      if (n != 4 && n != 3) {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.getModifiedMaxHealth());
         if (n == 0) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.getModifiedMovementSpeed());
         } else {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.175F);
         }
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2F);
      }

      if (n != 2 && n != 1) {
         this.getEntityAttribute(horseJumpStrength).setBaseValue(this.getModifiedJumpStrength());
      } else {
         this.getEntityAttribute(horseJumpStrength).setBaseValue(0.5);
      }

      this.setHealth(this.getMaxHealth());
      return entityLivingData;
   }

   private float getModifiedMaxHealth() {
      return 15.0F + (float)this.rand.nextInt(8) + (float)this.rand.nextInt(9);
   }

   public boolean isUndead() {
      int horseType = this.getHorseType();
      return horseType == 3 || horseType == 4;
   }

   @Override
   public void onInventoryChanged(InventoryBasic inventoryBasic) {
      int horseArmorIndexSynced = this.getHorseArmorIndexSynced();
      boolean isHorseSaddled = this.isHorseSaddled();
      this.updateHorseSlots();
      if (this.ticksExisted > 20) {
         if (horseArmorIndexSynced == 0 && horseArmorIndexSynced != this.getHorseArmorIndexSynced()) {
            this.playSound("mob.horse.armor", 0.5F, 1.0F);
         } else if (horseArmorIndexSynced != this.getHorseArmorIndexSynced()) {
            this.playSound("mob.horse.armor", 0.5F, 1.0F);
         }

         if (!isHorseSaddled && this.isHorseSaddled()) {
            this.playSound("mob.horse.leather", 0.5F, 1.0F);
         }
      }
   }

   @Override
   public String getHurtSound() {
      this.openHorseMouth();
      if (this.rand.nextInt(3) == 0) {
         this.makeHorseRear();
      }

      int horseType = this.getHorseType();
      return horseType == 3 ? "mob.horse.zombie.hit" : (horseType == 4 ? "mob.horse.skeleton.hit" : (horseType != 1 && horseType != 2 ? "mob.horse.hit" : "mob.horse.donkey.hit"));
   }

   private void setHorseWatchableBoolean(int n, boolean flag) {
      int watchableObjectInt = this.dataWatcher.getWatchableObjectInt(16);
      if (flag) {
         this.dataWatcher.updateObject(16, watchableObjectInt | n);
      } else {
         this.dataWatcher.updateObject(16, watchableObjectInt & ~n);
      }
   }

   @Override
   public void updateRiderPosition() {
      super.updateRiderPosition();
      if (this.prevRearingAmount > 0.0F) {
         float f = MathHelper.sin(this.renderYawOffset * (float) Math.PI / 180.0F);
         float f2 = MathHelper.cos(this.renderYawOffset * (float) Math.PI / 180.0F);
         float f3 = 0.7F * this.prevRearingAmount;
         float f4 = 0.15F * this.prevRearingAmount;
         this.riddenByEntity
            .setPosition(
               this.posX + (double)(f3 * f),
               this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset() + (double)f4,
               this.posZ - (double)(f3 * f2)
            );
         if (this.riddenByEntity instanceof EntityLivingBase) {
            ((EntityLivingBase)this.riddenByEntity).renderYawOffset = this.renderYawOffset;
         }
      }
   }

   private boolean canMate() {
      return this.riddenByEntity == null
         && this.ridingEntity == null
         && this.isTame()
         && this.isAdultHorse()
         && !this.isSterile()
         && this.getCurrentHealth() >= this.getMaxHealth()
         && this.isInLove();
   }

   public boolean canCarryChest() {
      int horseType = this.getHorseType();
      return horseType == 2 || horseType == 1;
   }

   public boolean isChested() {
      return this.getHorseWatchableBoolean(8);
   }

   public int getHorseType() {
      return this.dataWatcher.getWatchableObjectByte(19);
   }

   @Override
   public boolean isMovementBlocked() {
      return this.riddenByEntity != null && this.isHorseSaddled() ? true : this.isEatingHaystack() || this.isRearing();
   }

   private void resetTexturePrefix() {
      this.texturePrefix = null;
   }

   @Override
   public String getDeathSound() {
      this.openHorseMouth();
      int horseType = this.getHorseType();
      return horseType == 3
         ? "mob.horse.zombie.death"
         : (horseType == 4 ? "mob.horse.skeleton.death" : (horseType != 1 && horseType != 2 ? "mob.horse.death" : "mob.horse.donkey.death"));
   }

   public int getHorseArmorIndexSynced() {
      return this.dataWatcher.getWatchableObjectInt(22);
   }

   @Override
   public int getMaxSpawnedInChunk() {
      return 6;
   }

   public void setHasReproduced(boolean flag) {
      this.hasReproduced = flag;
   }

   @Override
   public void playStepSound(BlockPos pos, Block block) {
      Block.SoundType stepSound = block.stepSound;
      if (this.entityWorld.getBlockState(pos.up()).getBlock() == Blocks.snow_layer) {
         stepSound = Blocks.snow_layer.stepSound;
      }

      if (!block.getMaterial().isLiquid()) {
         int horseType = this.getHorseType();
         if (this.riddenByEntity != null && horseType != 1 && horseType != 2) {
            this.gallopTime++;
            if (this.gallopTime > 5 && this.gallopTime % 3 == 0) {
               this.playSound("mob.horse.gallop", stepSound.getVolume() * 0.15F, stepSound.getFrequency());
               if (horseType == 0 && this.rand.nextInt(10) == 0) {
                  this.playSound("mob.horse.breathe", stepSound.getVolume() * 0.6F, stepSound.getFrequency());
               }
            } else if (this.gallopTime <= 5) {
               this.playSound("mob.horse.wood", stepSound.getVolume() * 0.15F, stepSound.getFrequency());
            }
         } else if (stepSound == Block.soundTypeWood) {
            this.playSound("mob.horse.wood", stepSound.getVolume() * 0.15F, stepSound.getFrequency());
         } else {
            this.playSound("mob.horse.soft", stepSound.getVolume() * 0.15F, stepSound.getFrequency());
         }
      }
   }

   public boolean canBeSaddled() {
      return this.isAdultHorse();
   }

   public String getOwnerId() {
      return this.dataWatcher.getWatchableObjectString(21);
   }

   public int getHorseVariant() {
      return this.dataWatcher.getWatchableObjectInt(20);
   }

   public double getHorseJumpStrength() {
      return this.getEntityAttribute(horseJumpStrength).getAttributeValue();
   }

   public boolean isBreeding() {
      return this.getHorseWatchableBoolean(16);
   }

   public boolean isHorseJumping() {
      return this.horseJumping;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.setEatingHaystack(tag.getBoolean("EatingHaystack"));
      this.setBreeding(tag.getBoolean("Bred"));
      this.setChested(tag.getBoolean("ChestedHorse"));
      this.setHasReproduced(tag.getBoolean("HasReproduced"));
      this.setHorseType(tag.getInteger("Type"));
      this.setHorseVariant(tag.getInteger("Variant"));
      this.setTemper(tag.getInteger("Temper"));
      this.setHorseTamed(tag.getBoolean("Tame"));
      String str = "";
      if (tag.hasKey("OwnerUUID", 8)) {
         str = tag.getString("OwnerUUID");
      } else {
         str = PreYggdrasilConverter.getStringUUIDFromName(tag.getString("Owner"));
      }

      if (str.length() > 0) {
         this.setOwnerId(str);
      }

      IAttributeInstance attributeInstance = this.getAttributeMap().getAttributeInstanceByName("Speed");
      if (attributeInstance != null) {
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(attributeInstance.getBaseValue() * 0.25);
      }

      if (this.isChested()) {
         NBTTagList tagList = tag.getTagList("Items", 10);
         this.initHorseChest();

         for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
            int n = compoundTagAt.getByte("Slot") & 255;
            if (n >= 2 && n < this.horseChest.getSizeInventory()) {
               this.horseChest.setInventorySlotContents(n, ItemStack.loadItemStackFromNBT(compoundTagAt));
            }
         }
      }

      if (tag.hasKey("ArmorItem", 10)) {
         ItemStack stack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("ArmorItem"));
         if (stack != null && isArmorItem(stack.getItem())) {
            this.horseChest.setInventorySlotContents(1, stack);
         }
      }

      if (tag.hasKey("SaddleItem", 10)) {
         ItemStack stack2 = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("SaddleItem"));
         if (stack2 != null && stack2.getItem() == Items.saddle) {
            this.horseChest.setInventorySlotContents(0, stack2);
         }
      } else if (tag.getBoolean("Saddle")) {
         this.horseChest.setInventorySlotContents(0, new ItemStack(Items.saddle));
      }

      this.updateHorseSlots();
   }

   private void setHorseTexturePaths() {
      this.texturePrefix = "horse/";
      this.horseTexturesArray[0] = null;
      this.horseTexturesArray[1] = null;
      this.horseTexturesArray[2] = null;
      int horseType = this.getHorseType();
      int horseVariant = this.getHorseVariant();
      if (horseType == 0) {
         int n = horseVariant & 0xFF;
         int n2 = (horseVariant & 0xFF00) >> 8;
         if (n >= horseTextures.length) {
            this.hasUniqueTexture = false;
            return;
         }

         this.horseTexturesArray[0] = horseTextures[n];
         this.texturePrefix = this.texturePrefix + HORSE_TEXTURES_ABBR[n];
         if (n2 >= horseMarkingTextures.length) {
            this.hasUniqueTexture = false;
            return;
         }

         this.horseTexturesArray[1] = horseMarkingTextures[n2];
         this.texturePrefix = this.texturePrefix + HORSE_MARKING_TEXTURES_ABBR[n2];
      } else {
         this.horseTexturesArray[0] = "";
         this.texturePrefix = this.texturePrefix + "_" + horseType + "_";
      }

      int horseArmorIndexSynced = this.getHorseArmorIndexSynced();
      if (horseArmorIndexSynced >= horseArmorTextures.length) {
         this.hasUniqueTexture = false;
      } else {
         this.horseTexturesArray[2] = horseArmorTextures[horseArmorIndexSynced];
         this.texturePrefix = this.texturePrefix + HORSE_ARMOR_TEXTURES_ABBR[horseArmorIndexSynced];
         this.hasUniqueTexture = true;
      }
   }

   public void makeHorseRearWithSound() {
      this.makeHorseRear();
      String angrySoundName = this.getAngrySoundName();
      if (angrySoundName != null) {
         this.playSound(angrySoundName, this.getSoundVolume(), this.getSoundPitch());
      }
   }

   public void setRearing(boolean flag) {
      if (flag) {
         this.setEatingHaystack(false);
      }

      this.setHorseWatchableBoolean(64, flag);
   }

   private void moveTail() {
      this.tailCounter = 1;
   }

   @Override
   public boolean canMateWith(EntityAnimal entityAnimal) {
      if (entityAnimal == this) {
         return false;
      } else if (entityAnimal.getClass() != this.getClass()) {
         return false;
      } else {
         EntityHorse entityHorse = (EntityHorse)entityAnimal;
         if (this.canMate() && entityHorse.canMate()) {
            int horseType = this.getHorseType();
            int horseType2 = entityHorse.getHorseType();
            return horseType == horseType2 || horseType == 0 && horseType2 == 1 || horseType == 1 && horseType2 == 0;
         } else {
            return false;
         }
      }
   }

   public boolean canWearArmor() {
      return this.getHorseType() == 0;
   }

   public String getHorseTexture() {
      if (this.texturePrefix == null) {
         this.setHorseTexturePaths();
      }

      return this.texturePrefix;
   }

   private int getChestSize() {
      int horseType = this.getHorseType();
      return this.isChested() && (horseType == 1 || horseType == 2) ? 17 : 2;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setBoolean("EatingHaystack", this.isEatingHaystack());
      tag.setBoolean("ChestedHorse", this.isChested());
      tag.setBoolean("HasReproduced", this.getHasReproduced());
      tag.setBoolean("Bred", this.isBreeding());
      tag.setInteger("Type", this.getHorseType());
      tag.setInteger("Variant", this.getHorseVariant());
      tag.setInteger("Temper", this.getTemper());
      tag.setBoolean("Tame", this.isTame());
      tag.setString("OwnerUUID", this.getOwnerId());
      if (this.isChested()) {
         NBTTagList tagList = new NBTTagList();

         for (int i = 2; i < this.horseChest.getSizeInventory(); i++) {
            ItemStack stackInSlot = this.horseChest.getStackInSlot(i);
            if (stackInSlot != null) {
               NBTTagCompound tag2 = new NBTTagCompound();
               tag2.setByte("Slot", (byte)i);
               stackInSlot.writeToNBT(tag2);
               tagList.appendTag(tag2);
            }
         }

         tag.setTag("Items", tagList);
      }

      if (this.horseChest.getStackInSlot(1) != null) {
         tag.setTag("ArmorItem", this.horseChest.getStackInSlot(1).writeToNBT(new NBTTagCompound()));
      }

      if (this.horseChest.getStackInSlot(0) != null) {
         tag.setTag("SaddleItem", this.horseChest.getStackInSlot(0).writeToNBT(new NBTTagCompound()));
      }
   }

   @Override
   public Item getDropItem() {
      boolean flag = this.rand.nextInt(4) == 0;
      int horseType = this.getHorseType();
      return horseType == 4 ? Items.bone : (horseType == 3 ? (flag ? null : Items.rotten_flesh) : Items.leather);
   }

   public boolean isRearing() {
      return this.getHorseWatchableBoolean(64);
   }

   private void mountTo(EntityPlayer player) {
      player.rotationYaw = this.rotationYaw;
      player.rotationPitch = this.rotationPitch;
      this.setEatingHaystack(false);
      this.setRearing(false);
      if (!this.entityWorld.isRemote) {
         player.mountEntity(this);
      }
   }

   public float getHorseSize() {
      return 0.5F;
   }

   @Override
   public boolean interact(EntityPlayer player) {
      ItemStack currentItem = player.inventory.getCurrentItem();
      if (currentItem != null && currentItem.getItem() == Items.spawn_egg) {
         return super.interact(player);
      } else if (!this.isTame() && this.isUndead()) {
         return false;
      } else if (this.isTame() && this.isAdultHorse() && player.isSneaking()) {
         this.openGUI(player);
         return true;
      } else if (this.canBeSaddled() && this.riddenByEntity != null) {
         return super.interact(player);
      } else {
         if (currentItem != null) {
            boolean flag = false;
            if (this.canWearArmor()) {
               byte b = -1;
               if (currentItem.getItem() == Items.iron_horse_armor) {
                  b = 1;
               } else if (currentItem.getItem() == Items.golden_horse_armor) {
                  b = 2;
               } else if (currentItem.getItem() == Items.diamond_horse_armor) {
                  b = 3;
               }

               if (b >= 0) {
                  if (!this.isTame()) {
                     this.makeHorseRearWithSound();
                     return true;
                  }

                  this.openGUI(player);
                  return true;
               }
            }

            if (!flag && !this.isUndead()) {
               float f = 0.0F;
               short s = 0;
               byte b2 = 0;
               if (currentItem.getItem() == Items.wheat) {
                  f = 2.0F;
                  s = 20;
                  b2 = 3;
               } else if (currentItem.getItem() == Items.sugar) {
                  f = 1.0F;
                  s = 30;
                  b2 = 3;
               } else if (Block.getBlockFromItem(currentItem.getItem()) == Blocks.hay_block) {
                  f = 20.0F;
                  s = 180;
               } else if (currentItem.getItem() == Items.apple) {
                  f = 3.0F;
                  s = 60;
                  b2 = 3;
               } else if (currentItem.getItem() == Items.golden_carrot) {
                  f = 4.0F;
                  s = 60;
                  b2 = 5;
                  if (this.isTame() && this.getGrowingAge() == 0) {
                     flag = true;
                     this.setInLove(player);
                  }
               } else if (currentItem.getItem() == Items.golden_apple) {
                  f = 10.0F;
                  s = 240;
                  b2 = 10;
                  if (this.isTame() && this.getGrowingAge() == 0) {
                     flag = true;
                     this.setInLove(player);
                  }
               }

               if (this.getCurrentHealth() < this.getMaxHealth() && f > 0.0F) {
                  this.heal(f);
                  flag = true;
               }

               if (!this.isAdultHorse() && s > 0) {
                  this.addGrowth(s);
                  flag = true;
               }

               if (b2 > 0 && (flag || !this.isTame()) && b2 < this.getMaxTemper()) {
                  flag = true;
                  this.increaseTemper(b2);
               }

               if (flag) {
                  this.playEatingSound();
               }
            }

            if (!this.isTame() && !flag) {
               if (currentItem != null && currentItem.interactWithEntity(player, this)) {
                  return true;
               }

               this.makeHorseRearWithSound();
               return true;
            }

            if (!flag && this.canCarryChest() && !this.isChested() && currentItem.getItem() == Item.getItemFromBlock(Blocks.chest)) {
               this.setChested(true);
               this.playSound("mob.chickenplop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
               flag = true;
               this.initHorseChest();
            }

            if (!flag && this.canBeSaddled() && !this.isHorseSaddled() && currentItem.getItem() == Items.saddle) {
               this.openGUI(player);
               return true;
            }

            if (flag) {
               if (!player.capabilities.isCreativeMode && --currentItem.stackSize == 0) {
                  player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
               }

               return true;
            }
         }

         if (!this.canBeSaddled() || this.riddenByEntity != null) {
            return super.interact(player);
         } else if (currentItem != null && currentItem.interactWithEntity(player, this)) {
            return true;
         } else {
            this.mountTo(player);
            return true;
         }
      }
   }

   public void openGUI(EntityPlayer player) {
      if (!this.entityWorld.isRemote && (this.riddenByEntity == null || this.riddenByEntity == player) && this.isTame()) {
         this.horseChest.setCustomName(this.getName());
         player.displayGUIHorse(this, this.horseChest);
      }
   }

   private double getModifiedMovementSpeed() {
      return (0.45F + this.rand.nextDouble() * 0.3 + this.rand.nextDouble() * 0.3 + this.rand.nextDouble() * 0.3) * 0.25;
   }

   public boolean setTamedBy(EntityPlayer player) {
      this.setOwnerId(player.getUniqueID().toString());
      this.setHorseTamed(true);
      return true;
   }

   public float getGrassEatingAmount(float f) {
      return this.prevHeadLean + (this.headLean - this.prevHeadLean) * f;
   }

   private void makeHorseRear() {
      if (!this.entityWorld.isRemote) {
         this.jumpRearingCounter = 1;
         this.setRearing(true);
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      Entity entity = damageSource.getEntity();
      return this.riddenByEntity != null && this.riddenByEntity.equals(entity) ? false : super.attackEntityFrom(damageSource, f);
   }

   public boolean usesLayeredTexture() {
      return this.getHorseType() == 0 || this.getHorseArmorIndexSynced() > 0;
   }

   private void openHorseMouth() {
      if (!this.entityWorld.isRemote) {
         this.openMouthCounter = 1;
         this.setHorseWatchableBoolean(128, true);
      }
   }

   public float getRearingAmount(float f) {
      return this.prevRearingAmount + (this.rearingAmount - this.prevRearingAmount) * f;
   }

   private void playEatingSound() {
      this.openHorseMouth();
      if (!this.isSilent()) {
         this.entityWorld.playSoundAtEntity(this, "eating", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
      }
   }

   @Override
   public void setScaleForAge(boolean flag) {
      if (flag) {
         this.setScale(this.getHorseSize());
      } else {
         this.setScale(1.0F);
      }
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return false;
   }

   @Override
   public boolean getCanSpawnHere() {
      this.prepareChunkForSpawn();
      return super.getCanSpawnHere();
   }

   public boolean isEatingHaystack() {
      return this.getHorseWatchableBoolean(32);
   }

   @Override
   public void setEating(boolean flag) {
      this.setHorseWatchableBoolean(32, flag);
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, 0);
      this.dataWatcher.addObject(19, (byte)0);
      this.dataWatcher.addObject(20, 0);
      this.dataWatcher.addObject(21, String.valueOf(""));
      this.dataWatcher.addObject(22, 0);
   }

   private boolean getHorseWatchableBoolean(int n) {
      return (this.dataWatcher.getWatchableObjectInt(16) & n) != 0;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(horseJumpStrength);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(53.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.225F);
   }

   @Override
   public void onDeath(DamageSource damageSource) {
      super.onDeath(damageSource);
      if (!this.entityWorld.isRemote) {
         this.dropChestItems();
      }
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 7) {
         this.spawnHorseParticles(true);
      } else if (b == 6) {
         this.spawnHorseParticles(false);
      } else {
         super.handleStatusUpdate(b);
      }
   }

   public void setHorseTamed(boolean flag) {
      this.setHorseWatchableBoolean(2, flag);
   }

   @Override
   public String getLivingSound() {
      this.openHorseMouth();
      if (this.rand.nextInt(10) == 0 && !this.isMovementBlocked()) {
         this.makeHorseRear();
      }

      int horseType = this.getHorseType();
      return horseType == 3
         ? "mob.horse.zombie.idle"
         : (horseType == 4 ? "mob.horse.skeleton.idle" : (horseType != 1 && horseType != 2 ? "mob.horse.idle" : "mob.horse.donkey.idle"));
   }

   public void setJumpPower(int n) {
      if (this.isHorseSaddled()) {
         if (n < 0) {
            n = 0;
         } else {
            this.allowStandSliding = true;
            this.makeHorseRear();
         }

         if (n >= 90) {
            this.jumpPower = 1.0F;
         } else {
            this.jumpPower = 0.4F + 0.4F * (float)n / 90.0F;
         }
      }
   }

   @Override
   public EntityAgeable createAgeableChild(EntityAgeable entityAgeable) {
      EntityHorse entityHorse = (EntityHorse)entityAgeable;
      EntityHorse entityHorse2 = new EntityHorse(this.entityWorld);
      int horseType = this.getHorseType();
      int horseType2 = entityHorse.getHorseType();
      int n = 0;
      if (horseType == horseType2) {
         n = horseType;
      } else if (horseType == 0 && horseType2 == 1 || horseType == 1 && horseType2 == 0) {
         n = 2;
      }

      if (n == 0) {
         int n2 = this.rand.nextInt(9);
         int n3;
         if (n2 < 4) {
            n3 = this.getHorseVariant() & 0xFF;
         } else if (n2 < 8) {
            n3 = entityHorse.getHorseVariant() & 0xFF;
         } else {
            n3 = this.rand.nextInt(7);
         }

         int n4 = this.rand.nextInt(5);
         if (n4 < 2) {
            n3 |= this.getHorseVariant() & 0xFF00;
         } else if (n4 < 4) {
            n3 |= entityHorse.getHorseVariant() & 0xFF00;
         } else {
            n3 |= this.rand.nextInt(5) << 8 & 0xFF00;
         }

         entityHorse2.setHorseVariant(n3);
      }

      entityHorse2.setHorseType(n);
      double d = this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue()
         + entityAgeable.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue()
         + (double)this.getModifiedMaxHealth();
      entityHorse2.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(d / 3.0);
      double d2 = this.getEntityAttribute(horseJumpStrength).getBaseValue() + entityAgeable.getEntityAttribute(horseJumpStrength).getBaseValue() + this.getModifiedJumpStrength();
      entityHorse2.getEntityAttribute(horseJumpStrength).setBaseValue(d2 / 3.0);
      double d3 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()
         + entityAgeable.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()
         + this.getModifiedMovementSpeed();
      entityHorse2.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(d3 / 3.0);
      return entityHorse2;
   }

   @Override
   public String getName() {
      if (this.hasCustomName()) {
         return this.getCustomNameTag();
      } else {
         switch (this.getHorseType()) {
            case 0:
            default:
               return StatCollector.translateToLocal("entity.horse.name");
            case 1:
               return StatCollector.translateToLocal("entity.donkey.name");
            case 2:
               return StatCollector.translateToLocal("entity.mule.name");
            case 3:
               return StatCollector.translateToLocal("entity.zombiehorse.name");
            case 4:
               return StatCollector.translateToLocal("entity.skeletonhorse.name");
         }
      }
   }


   public static final class FilterPredicate implements Predicate {
      public boolean apply(Entity entity) {
         return entity instanceof EntityHorse && ((EntityHorse)entity).isBreeding();
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }
   }

   public static class GroupData implements IEntityLivingData {
      public int horseType;
      public int horseVariant;

      public GroupData(int n, int n2) {
         this.horseType = n;
         this.horseVariant = n2;
      }
   }
}
