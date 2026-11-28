package net.minecraft.entity.monster;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityBlaze extends EntityMob {
   private float heightOffset = 0.5F;
   private int heightOffsetUpdateTime;

   @Override
   public boolean isBurning() {
      return this.isCharged();
   }

   public boolean isCharged() {
      return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   @Override
   public void onLivingUpdate() {
      if (!this.onGround && this.motionY < 0.0) {
         this.motionY *= 0.6;
      }

      if (this.entityWorld.isRemote) {
         if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
            this.entityWorld
               .playSound(
                  this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, "fire.fire", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false
               );
         }

         for (int i = 0; i < 2; i++) {
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.SMOKE_LARGE,
                  this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width,
                  this.posY + this.rand.nextDouble() * (double)this.height,
                  this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width,
                  0.0,
                  0.0,
                  0.0,
                  new int[0]
               );
         }
      }

      super.onLivingUpdate();
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      if (flag) {
         int n2 = this.rand.nextInt(2 + n);

         for (int i = 0; i < n2; i++) {
            this.dropItem(Items.blaze_rod, 1);
         }
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.blaze.hit";
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23F);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0);
   }

   @Override
   public float getBrightness(float f) {
      return 1.0F;
   }

   @Override
   public void fall(float f, float f2) {
   }

   @Override
   public int getBrightnessForRender(float f) {
      return 15728880;
   }

   public EntityBlaze(World world) {
      super(world);
      this.isImmuneToFire = true;
      this.experienceValue = 10;
      this.tasks.addTask(4, new EntityBlaze.AIFireballAttack(this));
      this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
   }

   @Override
   public String getLivingSound() {
      return "mob.blaze.breathe";
   }

   @Override
   public Item getDropItem() {
      return Items.blaze_rod;
   }

   @Override
   public String getDeathSound() {
      return "mob.blaze.death";
   }

   @Override
   public boolean isValidLightLevel() {
      return true;
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, (byte)0);
   }

   public void setOnFire(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(16);
      if (flag) {
         watchableObjectByte = (byte)(watchableObjectByte | 1);
      } else {
         watchableObjectByte = (byte)(watchableObjectByte & -2);
      }

      this.dataWatcher.updateObject(16, watchableObjectByte);
   }

   @Override
   public void updateAITasks() {
      if (this.isWet()) {
         this.attackEntityFrom(DamageSource.drown, 1.0F);
      }

      this.heightOffsetUpdateTime--;
      if (this.heightOffsetUpdateTime <= 0) {
         this.heightOffsetUpdateTime = 100;
         this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
      }

      EntityLivingBase attackTarget2 = this.getAttackTarget();
      if (attackTarget2 != null && attackTarget2.posY + (double)attackTarget2.getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
         this.motionY = this.motionY + (0.3F - this.motionY) * 0.3F;
         this.isAirBorne = true;
      }

      super.updateAITasks();
   }


   public static class AIFireballAttack extends EntityAIBase {
      private EntityBlaze blaze;
      private int attackStep;
      private int attackTime;

      @Override
      public void updateTask() {
         this.attackTime--;
         EntityLivingBase attackTarget = this.blaze.getAttackTarget();
         double distanceSqToEntity = this.blaze.getDistanceSqToEntity(attackTarget);
         if (distanceSqToEntity < 4.0) {
            if (this.attackTime <= 0) {
               this.attackTime = 20;
               this.blaze.attackEntityAsMob(attackTarget);
            }

            this.blaze.getMoveHelper().setMoveTo(attackTarget.posX, attackTarget.posY, attackTarget.posZ, 1.0);
         } else if (distanceSqToEntity < 256.0) {
            double d = attackTarget.posX - this.blaze.posX;
            double d2 = attackTarget.getEntityBoundingBox().minY + (double)(attackTarget.height / 2.0F) - (this.blaze.posY + (double)(this.blaze.height / 2.0F));
            double d3 = attackTarget.posZ - this.blaze.posZ;
            if (this.attackTime <= 0) {
               this.attackStep++;
               if (this.attackStep == 1) {
                  this.attackTime = 60;
                  this.blaze.setOnFire(true);
               } else if (this.attackStep <= 4) {
                  this.attackTime = 6;
               } else {
                  this.attackTime = 100;
                  this.attackStep = 0;
                  this.blaze.setOnFire(false);
               }

               if (this.attackStep > 1) {
                  float f = MathHelper.sqrt_float(MathHelper.sqrt_double(distanceSqToEntity)) * 0.5F;
                  this.blaze.entityWorld.playAuxSFXAtEntity((EntityPlayer)null, 1009, new BlockPos((int)this.blaze.posX, (int)this.blaze.posY, (int)this.blaze.posZ), 0);

                  for (int i = 0; i < 1; i++) {
                     EntitySmallFireball entitySmallFireball = new EntitySmallFireball(
                        this.blaze.entityWorld,
                        this.blaze,
                        d + this.blaze.getRNG().nextGaussian() * (double)f,
                        d2,
                        d3 + this.blaze.getRNG().nextGaussian() * (double)f
                     );
                     entitySmallFireball.posY = this.blaze.posY + (double)(this.blaze.height / 2.0F) + 0.5;
                     this.blaze.entityWorld.spawnEntityInWorld(entitySmallFireball);
                  }
               }
            }

            this.blaze.getLookHelper().setLookPositionWithEntity(attackTarget, 10.0F, 10.0F);
         } else {
            this.blaze.getNavigator().clearPathEntity();
            this.blaze.getMoveHelper().setMoveTo(attackTarget.posX, attackTarget.posY, attackTarget.posZ, 1.0);
         }

         super.updateTask();
      }

      @Override
      public boolean shouldExecute() {
         EntityLivingBase attackTarget = this.blaze.getAttackTarget();
         return attackTarget != null && attackTarget.isEntityAlive();
      }

      public AIFireballAttack(EntityBlaze entityBlaze) {
         this.blaze = entityBlaze;
         this.setMutexBits(3);
      }

      @Override
      public void startExecuting() {
         this.attackStep = 0;
      }

      @Override
      public void resetTask() {
         this.blaze.setOnFire(false);
      }
   }
}
