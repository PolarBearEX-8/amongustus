package net.minecraft.entity.monster;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityZombie extends EntityMob {
   public static final IAttribute reinforcementChance = new RangedAttribute((IAttribute)null, "zombie.spawnReinforcements", 0.0, 0.0, 1.0)
      .setDescription("Spawn Reinforcements Chance");
   private static final UUID babySpeedBoostUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
   private static final AttributeModifier babySpeedBoostModifier = new AttributeModifier(babySpeedBoostUUID, "Baby speed boost", 0.5, 1);
   private final EntityAIBreakDoor breakDoor = new EntityAIBreakDoor(this);
   private int conversionTime;
   private boolean isBreakDoorsTaskSet = false;
   private float zombieWidth = -1.0F;
   private float zombieHeight;

   public boolean isBreakDoorsTaskSet() {
      return this.isBreakDoorsTaskSet;
   }

   @Override
   public int getExperiencePoints(EntityPlayer player) {
      if (this.isChild()) {
         this.experienceValue = (int)((float)this.experienceValue * 2.5F);
      }

      return super.getExperiencePoints(player);
   }

   public final void multiplySize(float f) {
      super.setSize(this.zombieWidth * f, this.zombieHeight * f);
   }

   public int getConversionTimeBoost() {
      int n = 1;
      if (this.rand.nextFloat() < 0.01F) {
         int n2 = 0;
         BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

         for (int i = (int)this.posX - 4; i < (int)this.posX + 4 && n2 < 14; i++) {
            for (int j = (int)this.posY - 4; j < (int)this.posY + 4 && n2 < 14; j++) {
               for (int k = (int)this.posZ - 4; k < (int)this.posZ + 4 && n2 < 14; k++) {
                  Block block = this.entityWorld.getBlockState(pos.set(i, j, k)).getBlock();
                  if (block == Blocks.iron_bars || block == Blocks.bed) {
                     if (this.rand.nextFloat() < 0.3F) {
                        n++;
                     }

                     n2++;
                  }
               }
            }
         }
      }

      return n;
   }

   public void convertToVillager() {
      EntityVillager entityVillager = new EntityVillager(this.entityWorld);
      entityVillager.copyLocationAndAnglesFrom(this);
      entityVillager.onInitialSpawn(this.entityWorld.getDifficultyForLocation(new BlockPos(entityVillager)), (IEntityLivingData)null);
      entityVillager.setLookingForHome();
      if (this.isChild()) {
         entityVillager.setGrowingAge(-24000);
      }

      this.entityWorld.removeEntity(this);
      entityVillager.setNoAI(this.isAIDisabled());
      if (this.hasCustomName()) {
         entityVillager.setCustomNameTag(this.getCustomNameTag());
         entityVillager.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
      }

      this.entityWorld.spawnEntityInWorld(entityVillager);
      entityVillager.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
      this.entityWorld.playAuxSFXAtEntity((EntityPlayer)null, 1017, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
   }

   @Override
   public void onKillEntity(EntityLivingBase entity) {
      super.onKillEntity(entity);
      if ((this.entityWorld.getDifficulty() == EnumDifficulty.NORMAL || this.entityWorld.getDifficulty() == EnumDifficulty.HARD) && entity instanceof EntityVillager) {
         if (this.entityWorld.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean()) {
            return;
         }

         EntityLiving entityLiving = (EntityLiving)entity;
         EntityZombie entityZombie = new EntityZombie(this.entityWorld);
         entityZombie.copyLocationAndAnglesFrom(entity);
         this.entityWorld.removeEntity(entity);
         entityZombie.onInitialSpawn(this.entityWorld.getDifficultyForLocation(new BlockPos(entityZombie)), (IEntityLivingData)null);
         entityZombie.setVillager(true);
         if (entity.isChild()) {
            entityZombie.setChild(true);
         }

         entityZombie.setNoAI(entityLiving.isAIDisabled());
         if (entityLiving.hasCustomName()) {
            entityZombie.setCustomNameTag(entityLiving.getCustomNameTag());
            entityZombie.setAlwaysRenderNameTag(entityLiving.getAlwaysRenderNameTag());
         }

         this.entityWorld.spawnEntityInWorld(entityZombie);
         this.entityWorld.playAuxSFXAtEntity((EntityPlayer)null, 1016, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
      }
   }

   @Override
   public boolean attackEntityAsMob(Entity entity) {
      boolean flag = super.attackEntityAsMob(entity);
      if (flag) {
         int difficultyId = this.entityWorld.getDifficulty().getDifficultyId();
         if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < (float)difficultyId * 0.3F) {
            entity.setFire(2 * difficultyId);
         }
      }

      return flag;
   }

   @Override
   public String getDeathSound() {
      return "mob.zombie.death";
   }

   public void startConversion(int n) {
      this.conversionTime = n;
      this.getDataWatcher().updateObject(14, (byte)1);
      this.removePotionEffect(Potion.weakness.id);
      this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, n, Math.min(this.entityWorld.getDifficulty().getDifficultyId() - 1, 0)));
      this.entityWorld.setEntityState(this, (byte)16);
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (super.attackEntityFrom(damageSource, f)) {
         EntityLivingBase attackTarget2 = this.getAttackTarget();
         if (attackTarget2 == null && damageSource.getEntity() instanceof EntityLivingBase) {
            attackTarget2 = (EntityLivingBase)damageSource.getEntity();
         }

         if (attackTarget2 != null
            && this.entityWorld.getDifficulty() == EnumDifficulty.HARD
            && (double)this.rand.nextFloat() < this.getEntityAttribute(reinforcementChance).getAttributeValue()) {
            int n = MathHelper.floor_double(this.posX);
            int n2 = MathHelper.floor_double(this.posY);
            int n3 = MathHelper.floor_double(this.posZ);
            EntityZombie entityZombie = new EntityZombie(this.entityWorld);

            for (int i = 0; i < 50; i++) {
               int n4 = n + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
               int n5 = n2 + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
               int n6 = n3 + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
               if (World.doesBlockHaveSolidTopSurface(this.entityWorld, n4, n5 - 1, n6) && this.entityWorld.getLight(n4, n5, n6) < 10) {
                  entityZombie.setPosition((double)n4, (double)n5, (double)n6);
                  if (!this.entityWorld.isAnyPlayerWithinRangeAt((double)n4, (double)n5, (double)n6, 7.0)
                     && this.entityWorld.checkNoEntityCollision(entityZombie.getEntityBoundingBox(), entityZombie)
                     && this.entityWorld.getCollidingBoundingBoxes(entityZombie, entityZombie.getEntityBoundingBox()).isEmpty()
                     && !this.entityWorld.isAnyLiquid(entityZombie.getEntityBoundingBox())) {
                     this.entityWorld.spawnEntityInWorld(entityZombie);
                     entityZombie.setAttackTarget(attackTarget2);
                     entityZombie.onInitialSpawn(this.entityWorld.getDifficultyForLocation(new BlockPos(entityZombie)), (IEntityLivingData)null);
                     this.getEntityAttribute(reinforcementChance).applyModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.05F, 0));
                     entityZombie.getEntityAttribute(reinforcementChance).applyModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05F, 0));
                     break;
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23F);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
      this.getAttributeMap().registerAttribute(reinforcementChance).setBaseValue(this.rand.nextDouble() * 0.1F);
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 16) {
         if (!this.isSilent()) {
            this.entityWorld
               .playSound(
                  this.posX + 0.5,
                  this.posY + 0.5,
                  this.posZ + 0.5,
                  "mob.zombie.remedy",
                  1.0F + this.rand.nextFloat(),
                  this.rand.nextFloat() * 0.7F + 0.3F,
                  false
               );
         }
      } else {
         super.handleStatusUpdate(b);
      }
   }

   public EntityZombie(World world) {
      super(world);
      ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0, false));
      this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.applyEntityAI();
      this.setSize(0.6F, 1.95F);
   }

   @Override
   public void playStepSound(BlockPos pos, Block block) {
      this.playSound("mob.zombie.step", 0.15F, 1.0F);
   }

   @Override
   public boolean canDespawn() {
      return !this.isConverting();
   }

   @Override
   public Item getDropItem() {
      return Items.rotten_flesh;
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   public boolean isVillager() {
      return this.getDataWatcher().getWatchableObjectByte(13) == 1;
   }

   public void applyEntityAI() {
      this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0, true));
      this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityIronGolem.class, 1.0, true));
      this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0, false));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityPigZombie.class}));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.getDataWatcher().addObject(12, (byte)0);
      this.getDataWatcher().addObject(13, (byte)0);
      this.getDataWatcher().addObject(14, (byte)0);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      if (tag.getBoolean("IsBaby")) {
         this.setChild(true);
      }

      if (tag.getBoolean("IsVillager")) {
         this.setVillager(true);
      }

      if (tag.hasKey("ConversionTime", 99) && tag.getInteger("ConversionTime") > -1) {
         this.startConversion(tag.getInteger("ConversionTime"));
      }

      this.setBreakDoorsAItask(tag.getBoolean("CanBreakDoors"));
   }

   @Override
   public boolean interact(EntityPlayer player) {
      ItemStack currentEquippedItem = player.getCurrentEquippedItem();
      if (currentEquippedItem != null && currentEquippedItem.getItem() == Items.golden_apple && currentEquippedItem.getMetadata() == 0 && this.isVillager() && this.isPotionActive(Potion.weakness)) {
         if (!player.capabilities.isCreativeMode) {
            currentEquippedItem.stackSize--;
         }

         if (currentEquippedItem.stackSize <= 0) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
         }

         if (!this.entityWorld.isRemote) {
            this.startConversion(this.rand.nextInt(2401) + 3600);
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void onDeath(DamageSource damageSource) {
      super.onDeath(damageSource);
      if (damageSource.getEntity() instanceof EntityCreeper
         && !(this instanceof EntityPigZombie)
         && ((EntityCreeper)damageSource.getEntity()).getPowered()
         && ((EntityCreeper)damageSource.getEntity()).isAIEnabled()) {
         ((EntityCreeper)damageSource.getEntity()).incrementDroppedSkulls();
         this.entityDropItem(new ItemStack(Items.skull, 1, 2), 0.0F);
      }
   }

   @Override
   public String getLivingSound() {
      return "mob.zombie.say";
   }

   @Override
   public float getEyeHeight() {
      float f = 1.74F;
      if (this.isChild()) {
         f = (float)((double)f - 0.81);
      }

      return f;
   }

   @Override
   public int getTotalArmorValue() {
      int n = super.getTotalArmorValue() + 2;
      if (n > 20) {
         n = 20;
      }

      return n;
   }

   @Override
   public boolean canEquipItem(ItemStack stack) {
      return stack.getItem() == Items.egg && this.isChild() && this.isRiding() ? false : super.canEquipItem(stack);
   }

   @Override
   public final void setSize(float f, float f2) {
      boolean flag = this.zombieWidth > 0.0F && this.zombieHeight > 0.0F;
      this.zombieWidth = f;
      this.zombieHeight = f2;
      if (!flag) {
         this.multiplySize(1.0F);
      }
   }

   @Override
   public void addRandomDrop() {
      switch (this.rand.nextInt(3)) {
         case 0:
            this.dropItem(Items.iron_ingot, 1);
            break;
         case 1:
            this.dropItem(Items.carrot, 1);
            break;
         case 2:
            this.dropItem(Items.potato, 1);
      }
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, IEntityLivingData entityLivingData) {
      entityLivingData = super.onInitialSpawn(difficultyInstance, entityLivingData);
      float f = difficultyInstance.getClampedAdditionalDifficulty();
      this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);
      if (entityLivingData == null) {
         entityLivingData = new EntityZombie.GroupData(this, this.entityWorld.rand.nextFloat() < 0.05F, this.entityWorld.rand.nextFloat() < 0.05F, null);
      }

      if (entityLivingData instanceof EntityZombie.GroupData) {
         EntityZombie.GroupData groupData = (EntityZombie.GroupData)entityLivingData;
         if (groupData.isVillager) {
            this.setVillager(true);
         }

         if (groupData.isChild) {
            this.setChild(true);
            if ((double)this.entityWorld.rand.nextFloat() < 0.05) {
               List entitiesWithinAABB = this.entityWorld
                  .getEntitiesWithinAABB(EntityChicken.class, this.getEntityBoundingBox().expand(5.0, 3.0, 5.0), EntitySelectors.IS_STANDALONE);
               if (!entitiesWithinAABB.isEmpty()) {
                  EntityChicken entityChicken = (EntityChicken)entitiesWithinAABB.get(0);
                  entityChicken.setChickenJockey(true);
                  this.mountEntity(entityChicken);
               }
            } else if ((double)this.entityWorld.rand.nextFloat() < 0.05) {
               EntityChicken entityChicken2 = new EntityChicken(this.entityWorld);
               entityChicken2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
               entityChicken2.onInitialSpawn(difficultyInstance, (IEntityLivingData)null);
               entityChicken2.setChickenJockey(true);
               this.entityWorld.spawnEntityInWorld(entityChicken2);
               this.mountEntity(entityChicken2);
            }
         }
      }

      this.setBreakDoorsAItask(this.rand.nextFloat() < f * 0.1F);
      this.setEquipmentBasedOnDifficulty(difficultyInstance);
      this.setEnchantmentBasedOnDifficulty(difficultyInstance);
      if (this.getEquipmentInSlot(4) == null) {
         Calendar currentDate = this.entityWorld.getCurrentDate();
         if (currentDate.get(2) + 1 == 10 && currentDate.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
            this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
            this.equipmentDropChances[4] = 0.0F;
         }
      }

      this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
         .applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05F, 0));
      double d = this.rand.nextDouble() * 1.5 * (double)f;
      if (d > 1.0) {
         this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", d, 2));
      }

      if (this.rand.nextFloat() < f * 0.05F) {
         this.getEntityAttribute(reinforcementChance).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 0.25 + 0.5, 0));
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 3.0 + 1.0, 2));
         this.setBreakDoorsAItask(true);
      }

      return entityLivingData;
   }

   @Override
   public void onUpdate() {
      if (!this.entityWorld.isRemote && this.isConverting()) {
         int conversionTimeBoost = this.getConversionTimeBoost();
         this.conversionTime -= conversionTimeBoost;
         if (this.conversionTime <= 0) {
            this.convertToVillager();
         }
      }

      super.onUpdate();
   }

   public void setBreakDoorsAItask(boolean flag) {
      if (this.isBreakDoorsTaskSet != flag) {
         this.isBreakDoorsTaskSet = flag;
         if (flag) {
            this.tasks.addTask(1, this.breakDoor);
         } else {
            this.tasks.removeTask(this.breakDoor);
         }
      }
   }

   public void setChild(boolean flag) {
      this.getDataWatcher().updateObject(12, (byte)(flag ? 1 : 0));
      if (this.entityWorld != null && !this.entityWorld.isRemote) {
         IAttributeInstance entityAttribute = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         entityAttribute.removeModifier(babySpeedBoostModifier);
         if (flag) {
            entityAttribute.applyModifier(babySpeedBoostModifier);
         }
      }

      this.setChildSize(flag);
   }

   @Override
   public boolean isChild() {
      return this.getDataWatcher().getWatchableObjectByte(12) == 1;
   }

   @Override
   public void setEquipmentBasedOnDifficulty(DifficultyInstance difficultyInstance) {
      super.setEquipmentBasedOnDifficulty(difficultyInstance);
      if (this.rand.nextFloat() < (this.entityWorld.getDifficulty() == EnumDifficulty.HARD ? 0.05F : 0.01F)) {
         if (this.rand.nextInt(3) == 0) {
            this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
         } else {
            this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_shovel));
         }
      }
   }

   public void setChildSize(boolean flag) {
      this.multiplySize(flag ? 0.5F : 1.0F);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      if (this.isChild()) {
         tag.setBoolean("IsBaby", true);
      }

      if (this.isVillager()) {
         tag.setBoolean("IsVillager", true);
      }

      tag.setInteger("ConversionTime", this.isConverting() ? this.conversionTime : -1);
      tag.setBoolean("CanBreakDoors", this.isBreakDoorsTaskSet());
   }

   public void setVillager(boolean flag) {
      this.getDataWatcher().updateObject(13, (byte)(flag ? 1 : 0));
   }

   @Override
   public double getYOffset() {
      return this.isChild() ? 0.0 : -0.35;
   }

   public boolean isConverting() {
      return this.getDataWatcher().getWatchableObjectByte(14) == 1;
   }

   @Override
   public String getHurtSound() {
      return "mob.zombie.hurt";
   }

   @Override
   public void onLivingUpdate() {
      if (this.entityWorld.isDaytime() && !this.entityWorld.isRemote && !this.isChild()) {
         float brightness = this.getBrightness(1.0F);
         if (brightness > 0.5F
            && this.rand.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F
            && this.entityWorld.canSeeSky(MathHelper.floor_double(this.posX), MathHelper.floor_double((double)Math.round(this.posY)), MathHelper.floor_double(this.posZ))) {
            boolean flag = true;
            ItemStack equipmentInSlot = this.getEquipmentInSlot(4);
            if (equipmentInSlot != null) {
               if (equipmentInSlot.isItemStackDamageable()) {
                  equipmentInSlot.setItemDamage(equipmentInSlot.getItemDamage() + this.rand.nextInt(2));
                  if (equipmentInSlot.getItemDamage() >= equipmentInSlot.getMaxDamage()) {
                     this.renderBrokenItemStack(equipmentInSlot);
                     this.setCurrentItemOrArmor(4, (ItemStack)null);
                  }
               }

               flag = false;
            }

            if (flag) {
               this.setFire(8);
            }
         }
      }

      if (this.isRiding() && this.getAttackTarget() != null && this.ridingEntity instanceof EntityChicken) {
         ((EntityLiving)this.ridingEntity).getNavigator().setPath(this.getNavigator().getPath(), 1.5);
      }

      super.onLivingUpdate();
   }


   public static class InnerEntry {
   }

   public static class GroupData implements IEntityLivingData {
      public boolean isChild;
      public boolean isVillager;
      public final EntityZombie zombie;

      private GroupData(EntityZombie entityZombie, boolean flag, boolean flag2) {
         this.zombie = entityZombie;
         this.isChild = false;
         this.isVillager = false;
         this.isChild = flag;
         this.isVillager = flag2;
      }

      public GroupData(EntityZombie entityZombie, boolean flag, boolean flag2, EntityZombie.InnerEntry obj) {
         this(entityZombie, flag, flag2);
      }
   }
}
