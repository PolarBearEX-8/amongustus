package net.minecraft.entity.passive;

import com.google.common.base.Predicate;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWolf extends EntityTameable {
   private float headRotationCourse;
   private float headRotationCourseOld;
   private boolean isWet;
   private boolean isShaking;
   private float timeWolfIsShaking;
   private float prevTimeWolfIsShaking;

   @Override
   public EntityAgeable createAgeableChild(EntityAgeable entityAgeable) {
      return this.createChild(entityAgeable);
   }

   @Override
   public boolean shouldAttackEntity(EntityLivingBase entity, EntityLivingBase entity2) {
      if (!(entity instanceof EntityCreeper) && !(entity instanceof EntityGhast)) {
         if (entity instanceof EntityWolf) {
            EntityWolf entityWolf = (EntityWolf)entity;
            if (entityWolf.isTamed() && entityWolf.getOwnerEntity() == entity2) {
               return false;
            }
         }

         return entity instanceof EntityPlayer && entity2 instanceof EntityPlayer && !((EntityPlayer)entity2).canAttackPlayer((EntityPlayer)entity)
            ? false
            : !(entity instanceof EntityHorse) || !((EntityHorse)entity).isTame();
      } else {
         return false;
      }
   }

   public EnumDyeColor getCollarColor() {
      return EnumDyeColor.byDyeDamage(this.dataWatcher.getWatchableObjectByte(20) & 15);
   }

   @Override
   public float getEyeHeight() {
      return this.height * 0.8F;
   }

   public boolean isBegging() {
      return this.dataWatcher.getWatchableObjectByte(19) == 1;
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(18, this.getCurrentHealth());
      this.dataWatcher.addObject(19, (byte)0);
      this.dataWatcher.addObject(20, (byte)EnumDyeColor.RED.getMetadata());
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      this.headRotationCourseOld = this.headRotationCourse;
      if (this.isBegging()) {
         this.headRotationCourse = this.headRotationCourse + (1.0F - this.headRotationCourse) * 0.4F;
      } else {
         this.headRotationCourse = this.headRotationCourse + (0.0F - this.headRotationCourse) * 0.4F;
      }

      if (this.isWet()) {
         this.isWet = true;
         this.isShaking = false;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
      } else if ((this.isWet || this.isShaking) && this.isShaking) {
         if (this.timeWolfIsShaking == 0.0F) {
            this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         }

         this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
         this.timeWolfIsShaking += 0.05F;
         if (this.prevTimeWolfIsShaking >= 2.0F) {
            this.isWet = false;
            this.isShaking = false;
            this.prevTimeWolfIsShaking = 0.0F;
            this.timeWolfIsShaking = 0.0F;
         }

         if (this.timeWolfIsShaking > 0.4F) {
            float minY = (float)this.getEntityBoundingBox().minY;
            int n = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * (float) Math.PI) * 7.0F);

            for (int i = 0; i < n; i++) {
               float f = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
               float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
               this.entityWorld
                  .spawnParticle(
                     EnumParticleTypes.WATER_SPLASH,
                     this.posX + (double)f,
                     (double)(minY + 0.8F),
                     this.posZ + (double)f2,
                     this.motionX,
                     this.motionY,
                     this.motionZ,
                     new int[0]
                  );
            }
         }
      }
   }

   @Override
   public boolean canDespawn() {
      return !this.isTamed() && this.ticksExisted > 2400;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.entityWorld.isRemote && this.isWet && !this.isShaking && !this.hasPath() && this.onGround) {
         this.isShaking = true;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
         this.entityWorld.setEntityState(this, (byte)8);
      }

      if (!this.entityWorld.isRemote && this.getAttackTarget() == null && this.isAngry()) {
         this.setAngry(false);
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         Entity entity = damageSource.getEntity();
         this.aiSit.setSitting(false);
         if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
            f = (f + 1.0F) / 2.0F;
         }

         return super.attackEntityFrom(damageSource, f);
      }
   }

   @Override
   public boolean allowLeashing() {
      return !this.isAngry() && super.allowLeashing();
   }

   public void setAngry(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(16);
      if (flag) {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte | 2));
      } else {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte & -3));
      }
   }

   @Override
   public boolean interact(EntityPlayer player) {
      ItemStack currentItem = player.inventory.getCurrentItem();
      if (this.isTamed()) {
         if (currentItem != null) {
            if (currentItem.getItem() instanceof ItemFood) {
               ItemFood item = (ItemFood)currentItem.getItem();
               if (item.isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F) {
                  if (!player.capabilities.isCreativeMode) {
                     currentItem.stackSize--;
                  }

                  this.heal((float)item.getHealAmount(currentItem));
                  if (currentItem.stackSize <= 0) {
                     player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            } else if (currentItem.getItem() == Items.dye) {
               EnumDyeColor enumDyeColor = EnumDyeColor.byDyeDamage(currentItem.getMetadata());
               if (enumDyeColor != this.getCollarColor()) {
                  this.setCollarColor(enumDyeColor);
                  if (!player.capabilities.isCreativeMode && --currentItem.stackSize <= 0) {
                     player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            }
         }

         if (this.isOwner(player) && !this.entityWorld.isRemote && !this.isBreedingItem(currentItem)) {
            this.aiSit.setSitting(!this.isSitting());
            this.jumping = false;
            this.navigator.clearPathEntity();
            this.setAttackTarget((EntityLivingBase)null);
         }
      } else if (currentItem != null && currentItem.getItem() == Items.bone && !this.isAngry()) {
         if (!player.capabilities.isCreativeMode) {
            currentItem.stackSize--;
         }

         if (currentItem.stackSize <= 0) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
         }

         if (!this.entityWorld.isRemote) {
            if (this.rand.nextInt(3) == 0) {
               this.setTamed(true);
               this.navigator.clearPathEntity();
               this.setAttackTarget((EntityLivingBase)null);
               this.aiSit.setSitting(true);
               this.setHealth(20.0F);
               this.setOwnerId(player.getUniqueID().toString());
               this.playTameEffect(true);
               this.entityWorld.setEntityState(this, (byte)7);
            } else {
               this.playTameEffect(false);
               this.entityWorld.setEntityState(this, (byte)6);
            }
         }

         return true;
      }

      return super.interact(player);
   }

   public void setCollarColor(EnumDyeColor enumDyeColor) {
      this.dataWatcher.updateObject(20, (byte)(enumDyeColor.getDyeDamage() & 15));
   }

   @Override
   public void playStepSound(BlockPos pos, Block block) {
      this.playSound("mob.wolf.step", 0.15F, 1.0F);
   }

   @Override
   public void setTamed(boolean flag) {
      super.setTamed(flag);
      if (flag) {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
      }

      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
   }

   @Override
   public int getVerticalFaceSpeed() {
      return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
   }

   public float getShadingWhileWet(float f) {
      return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * f) / 2.0F * 0.25F;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
      if (this.isTamed()) {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
      }

      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
   }

   @Override
   public void setAttackTarget(EntityLivingBase entity) {
      super.setAttackTarget(entity);
      if (entity == null) {
         this.setAngry(false);
      } else if (!this.isTamed()) {
         this.setAngry(true);
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setBoolean("Angry", this.isAngry());
      tag.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
   }

   public void setBegging(boolean flag) {
      if (flag) {
         this.dataWatcher.updateObject(19, (byte)1);
      } else {
         this.dataWatcher.updateObject(19, (byte)0);
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.setAngry(tag.getBoolean("Angry"));
      if (tag.hasKey("CollarColor", 99)) {
         this.setCollarColor(EnumDyeColor.byDyeDamage(tag.getByte("CollarColor")));
      }
   }

   public boolean isWolfWet() {
      return this.isWet;
   }

   public float getTailRotation() {
      return this.isAngry()
         ? 1.5393804F
         : (this.isTamed() ? (0.55F - (20.0F - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * (float) Math.PI : (float) (Math.PI / 5));
   }

   @Override
   public boolean canMateWith(EntityAnimal entityAnimal) {
      if (entityAnimal == this) {
         return false;
      } else if (!this.isTamed()) {
         return false;
      } else if (!(entityAnimal instanceof EntityWolf)) {
         return false;
      } else {
         EntityWolf entityWolf = (EntityWolf)entityAnimal;
         return !entityWolf.isTamed() ? false : (entityWolf.isSitting() ? false : this.isInLove() && entityWolf.isInLove());
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.wolf.hurt";
   }

   public float getShakeAngle(float f, float f2) {
      float f3 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * f + f2) / 1.8F;
      if (f3 < 0.0F) {
         f3 = 0.0F;
      } else if (f3 > 1.0F) {
         f3 = 1.0F;
      }

      return MathHelper.sin(f3 * (float) Math.PI) * MathHelper.sin(f3 * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 8) {
         this.isShaking = true;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
      } else {
         super.handleStatusUpdate(b);
      }
   }

   @Override
   public float getSoundVolume() {
      return 0.4F;
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return stack == null ? false : (!(stack.getItem() instanceof ItemFood) ? false : ((ItemFood)stack.getItem()).isWolfsFavoriteMeat());
   }

   @Override
   public int getMaxSpawnedInChunk() {
      return 8;
   }

   public EntityWolf(World world) {
      super(world);
      this.setSize(0.6F, 0.8F);
      ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, this.aiSit);
      this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
      this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0, true));
      this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0, 10.0F, 2.0F));
      this.tasks.addTask(6, new EntityAIMate(this, 1.0));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(8, new EntityAIBeg(this, 8.0F));
      this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(9, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
      this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
      this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new EntityWolf.FilterPredicate(this)));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, false));
      this.setTamed(false);
   }

   @Override
   public Item getDropItem() {
      return Item.getItemById(-1);
   }

   @Override
   public void updateAITasks() {
      this.dataWatcher.updateObject(18, this.getCurrentHealth());
   }

   public EntityWolf createChild(EntityAgeable entityAgeable) {
      EntityWolf entityWolf = new EntityWolf(this.entityWorld);
      String ownerId = this.getOwnerId();
      if (ownerId != null && ownerId.trim().length() > 0) {
         entityWolf.setOwnerId(ownerId);
         entityWolf.setTamed(true);
      }

      return entityWolf;
   }

   @Override
   public String getLivingSound() {
      return this.isAngry()
         ? "mob.wolf.growl"
         : (
            this.rand.nextInt(3) == 0
               ? (this.isTamed() && this.dataWatcher.getWatchableObjectFloat(18) < 10.0F ? "mob.wolf.whine" : "mob.wolf.panting")
               : "mob.wolf.bark"
         );
   }

   @Override
   public String getDeathSound() {
      return "mob.wolf.death";
   }

   public boolean isAngry() {
      return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
   }

   public float getInterestedAngle(float f) {
      return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * f) * 0.15F * (float) Math.PI;
   }

   @Override
   public boolean attackEntityAsMob(Entity entity) {
      boolean flag = entity.attackEntityFrom(
         DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue())
      );
      if (flag) {
         this.applyEnchantments(this, entity);
      }

      return flag;
   }


   public static class FilterPredicate implements Predicate {
      public final EntityWolf wolf;

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         return entity instanceof EntitySheep || entity instanceof EntityRabbit;
      }

      public FilterPredicate(EntityWolf entityWolf) {
         this.wolf = entityWolf;
      }
   }
}
