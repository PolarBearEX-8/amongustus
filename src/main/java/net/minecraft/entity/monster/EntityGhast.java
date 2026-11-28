package net.minecraft.entity.monster;

import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGhast extends EntityFlying implements IMob {
   private int explosionStrength = 1;

   @Override
   public String getDeathSound() {
      return "mob.ghast.death";
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      int n2 = this.rand.nextInt(2) + this.rand.nextInt(1 + n);

      for (int i = 0; i < n2; i++) {
         this.dropItem(Items.ghast_tear, 1);
      }

      n2 = this.rand.nextInt(3) + this.rand.nextInt(1 + n);

      for (int j = 0; j < n2; j++) {
         this.dropItem(Items.gunpowder, 1);
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(100.0);
   }

   @Override
   public int getMaxSpawnedInChunk() {
      return 1;
   }

   @Override
   public String getHurtSound() {
      return "mob.ghast.scream";
   }

   @Override
   public String getLivingSound() {
      return "mob.ghast.moan";
   }

   public EntityGhast(World world) {
      super(world);
      this.setSize(4.0F, 4.0F);
      this.isImmuneToFire = true;
      this.experienceValue = 5;
      this.moveHelper = new EntityGhast.GhastMoveHelper(this);
      this.tasks.addTask(5, new EntityGhast.AIRandomFly(this));
      this.tasks.addTask(7, new EntityGhast.AILookAround(this));
      this.tasks.addTask(7, new EntityGhast.AIFireballAttack(this));
      this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("ExplosionPower", this.explosionStrength);
   }

   public boolean isAttacking() {
      return this.dataWatcher.getWatchableObjectByte(16) != 0;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      if (tag.hasKey("ExplosionPower", 99)) {
         this.explosionStrength = tag.getInteger("ExplosionPower");
      }
   }

   @Override
   public float getSoundVolume() {
      return 10.0F;
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.rand.nextInt(20) == 0 && super.getCanSpawnHere() && this.entityWorld.getDifficulty() != EnumDifficulty.PEACEFUL;
   }

   @Override
   public float getEyeHeight() {
      return 2.6F;
   }

   public int getFireballStrength() {
      return this.explosionStrength;
   }

   public void setAttacking(boolean flag) {
      this.dataWatcher.updateObject(16, (byte)(flag ? 1 : 0));
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.entityWorld.isRemote && this.entityWorld.getDifficulty() == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }
   }

   @Override
   public Item getDropItem() {
      return Items.gunpowder;
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, (byte)0);
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else if ("fireball".equals(damageSource.getDamageType()) && damageSource.getEntity() instanceof EntityPlayer) {
         super.attackEntityFrom(damageSource, 1000.0F);
         ((EntityPlayer)damageSource.getEntity()).triggerAchievement(AchievementList.ghast);
         return true;
      } else {
         return super.attackEntityFrom(damageSource, f);
      }
   }


   public static class AIRandomFly extends EntityAIBase {
      private EntityGhast parentEntity;

      public AIRandomFly(EntityGhast entityGhast) {
         this.parentEntity = entityGhast;
         this.setMutexBits(1);
      }

      @Override
      public void startExecuting() {
         Random rng = this.parentEntity.getRNG();
         double d = this.parentEntity.posX + (double)((rng.nextFloat() * 2.0F - 1.0F) * 16.0F);
         double d2 = this.parentEntity.posY + (double)((rng.nextFloat() * 2.0F - 1.0F) * 16.0F);
         double d3 = this.parentEntity.posZ + (double)((rng.nextFloat() * 2.0F - 1.0F) * 16.0F);
         this.parentEntity.getMoveHelper().setMoveTo(d, d2, d3, 1.0);
      }

      @Override
      public boolean shouldExecute() {
         EntityMoveHelper moveHelper = this.parentEntity.getMoveHelper();
         if (!moveHelper.isUpdating()) {
            return true;
         } else {
            double d = moveHelper.getX() - this.parentEntity.posX;
            double d2 = moveHelper.getY() - this.parentEntity.posY;
            double d3 = moveHelper.getZ() - this.parentEntity.posZ;
            double d4 = d * d + d2 * d2 + d3 * d3;
            return d4 < 1.0 || d4 > 3600.0;
         }
      }

      @Override
      public boolean continueExecuting() {
         return false;
      }
   }

   public static class AILookAround extends EntityAIBase {
      private EntityGhast parentEntity;

      public AILookAround(EntityGhast entityGhast) {
         this.parentEntity = entityGhast;
         this.setMutexBits(2);
      }

      @Override
      public boolean shouldExecute() {
         return true;
      }

      @Override
      public void updateTask() {
         if (this.parentEntity.getAttackTarget() == null) {
            this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw = -((float)MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ))
               * 180.0F
               / (float) Math.PI;
         } else {
            EntityLivingBase attackTarget = this.parentEntity.getAttackTarget();
            double d = 64.0;
            if (attackTarget.getDistanceSqToEntity(this.parentEntity) < d * d) {
               double d2 = attackTarget.posX - this.parentEntity.posX;
               double d3 = attackTarget.posZ - this.parentEntity.posZ;
               this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d2, d3)) * 180.0F / (float) Math.PI;
            }
         }
      }
   }

   public static class GhastMoveHelper extends EntityMoveHelper {
      private EntityGhast parentEntity;
      private int courseChangeCooldown;

      public GhastMoveHelper(EntityGhast entityGhast) {
         super(entityGhast);
         this.parentEntity = entityGhast;
      }

      @Override
      public void onUpdateMoveHelper() {
         if (this.update) {
            double d = this.posX - this.parentEntity.posX;
            double d2 = this.posY - this.parentEntity.posY;
            double d3 = this.posZ - this.parentEntity.posZ;
            double d4 = d * d + d2 * d2 + d3 * d3;
            if (this.courseChangeCooldown-- <= 0) {
               this.courseChangeCooldown = this.courseChangeCooldown + this.parentEntity.getRNG().nextInt(5) + 2;
               d4 = (double)MathHelper.sqrt_double(d4);
               if (this.isNotColliding(this.posX, this.posY, this.posZ, d4)) {
                  this.parentEntity.motionX += d / d4 * 0.1;
                  this.parentEntity.motionY += d2 / d4 * 0.1;
                  this.parentEntity.motionZ += d3 / d4 * 0.1;
               } else {
                  this.update = false;
               }
            }
         }
      }

      private boolean isNotColliding(double d, double d2, double d3, double d4) {
         double d5 = (d - this.parentEntity.posX) / d4;
         double d6 = (d2 - this.parentEntity.posY) / d4;
         double d7 = (d3 - this.parentEntity.posZ) / d4;
         AxisAlignedBB entityBoundingBox = this.parentEntity.getEntityBoundingBox();

         for (int i = 1; (double)i < d4; i++) {
            entityBoundingBox = entityBoundingBox.offset(d5, d6, d7);
            if (!this.parentEntity.entityWorld.getCollidingBoundingBoxes(this.parentEntity, entityBoundingBox).isEmpty()) {
               return false;
            }
         }

         return true;
      }
   }

   public static class AIFireballAttack extends EntityAIBase {
      private EntityGhast parentEntity;
      public int attackTimer;

      @Override
      public void resetTask() {
         this.parentEntity.setAttacking(false);
      }

      @Override
      public void startExecuting() {
         this.attackTimer = 0;
      }

      @Override
      public void updateTask() {
         EntityLivingBase attackTarget = this.parentEntity.getAttackTarget();
         double d = 64.0;
         if (attackTarget.getDistanceSqToEntity(this.parentEntity) < d * d && this.parentEntity.canEntityBeSeen(attackTarget)) {
            World entityWorld = this.parentEntity.entityWorld;
            this.attackTimer++;
            if (this.attackTimer == 10) {
               entityWorld.playAuxSFXAtEntity((EntityPlayer)null, 1007, new BlockPos(this.parentEntity), 0);
            }

            if (this.attackTimer == 20) {
               double d2 = 4.0;
               Vec3 look = this.parentEntity.getLook(1.0F);
               double d3 = attackTarget.posX - (this.parentEntity.posX + look.xCoord * d2);
               double d4 = attackTarget.getEntityBoundingBox().minY
                  + (double)(attackTarget.height / 2.0F)
                  - (0.5 + this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F));
               double d5 = attackTarget.posZ - (this.parentEntity.posZ + look.zCoord * d2);
               entityWorld.playAuxSFXAtEntity((EntityPlayer)null, 1008, new BlockPos(this.parentEntity), 0);
               EntityLargeFireball entityLargeFireball = new EntityLargeFireball(entityWorld, this.parentEntity, d3, d4, d5);
               entityLargeFireball.explosionPower = this.parentEntity.getFireballStrength();
               entityLargeFireball.posX = this.parentEntity.posX + look.xCoord * d2;
               entityLargeFireball.posY = this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F) + 0.5;
               entityLargeFireball.posZ = this.parentEntity.posZ + look.zCoord * d2;
               entityWorld.spawnEntityInWorld(entityLargeFireball);
               this.attackTimer = -40;
            }
         } else if (this.attackTimer > 0) {
            this.attackTimer--;
         }

         this.parentEntity.setAttacking(this.attackTimer > 10);
      }

      public AIFireballAttack(EntityGhast entityGhast) {
         this.parentEntity = entityGhast;
      }

      @Override
      public boolean shouldExecute() {
         return this.parentEntity.getAttackTarget() != null;
      }
   }
}
