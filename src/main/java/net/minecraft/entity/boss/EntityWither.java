package net.minecraft.entity.boss;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityWither extends EntityMob implements IRangedAttackMob, IBossDisplayData {
   private float[] xRotationHeads = new float[2];
   private float[] yRotationHeads = new float[2];
   private float[] prevXRotationHeads = new float[2];
   private float[] prevYRotationHeads = new float[2];
   private int[] nextHeadUpdate = new int[2];
   private int[] idleHeadUpdates = new int[2];
   private int blockBreakCounter;

   @Override
   public float getHealth() { return this.getCurrentHealth(); }

   @Override
   public float getBossMaxHealth() { return this.getMaxHealth(); }

   @Override
   public net.minecraft.util.IChatComponent getBossDisplayName() { return this.getDisplayName(); }
   private static final Predicate attackEntitySelector = new EntityWither.FilterPredicate();

   @Override
   public void dropFewItems(boolean flag, int n) {
      EntityItem entityItem = this.dropItem(Items.nether_star, 1);
      if (entityItem != null) {
         entityItem.setNoDespawn();
      }

      if (!this.entityWorld.isRemote) {
         for (EntityPlayer player : (Iterable<EntityPlayer>)(this.entityWorld.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(50.0, 100.0, 50.0)))) {
            player.triggerAchievement(AchievementList.killWither);
         }
      }
   }

   public void updateWatchedTargetId(int n, int n2) {
      this.dataWatcher.updateObject(17 + n, n2);
   }

   public boolean isArmored() {
      return this.getHealth() <= this.getBossMaxHealth() / 2.0F;
   }

   public int getInvulTime() {
      return this.dataWatcher.getWatchableObjectInt(20);
   }

   @Override
   public void mountEntity(Entity entity) {
      this.ridingEntity = null;
   }

   public void setInvulTime(int n) {
      this.dataWatcher.updateObject(20, n);
   }

   @Override
   public void fall(float f, float f2) {
   }

   public float getHeadXRotation(int n) {
      return this.xRotationHeads[n];
   }

   private float rotlerp(float f, float f2, float f3) {
      float f4 = MathHelper.wrapAngleTo180_float(f2 - f);
      if (f4 > f3) {
         f4 = f3;
      }

      if (f4 < -f3) {
         f4 = -f3;
      }

      return f + f4;
   }

   private double getHeadX(int n) {
      if (n <= 0) {
         return this.posX;
      } else {
         float f2 = MathHelper.cos((this.renderYawOffset + (float)(180 * (n - 1))) / 180.0F * (float) Math.PI);
         return this.posX + (double)f2 * 1.3;
      }
   }

   public EntityWither(World world) {
      super(world);
      this.setHealth(this.getBossMaxHealth());
      this.setSize(0.9F, 3.5F);
      this.isImmuneToFire = true;
      ((PathNavigateGround)this.getNavigator()).setCanSwim(true);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0, 40, 20.0F));
      this.tasks.addTask(5, new EntityAIWander(this, 1.0));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(7, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, attackEntitySelector));
      this.experienceValue = 50;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.setInvulTime(tag.getInteger("Invul"));
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("Invul", this.getInvulTime());
   }

   @Override
   public void despawnEntity() {
      this.age = 0;
   }

   @Override
   public int getBrightnessForRender(float f) {
      return 15728880;
   }

   @Override
   public void setInWeb() {
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(17, 0);
      this.dataWatcher.addObject(18, 0);
      this.dataWatcher.addObject(19, 0);
      this.dataWatcher.addObject(20, 0);
   }

   private void launchWitherSkullToCoords(int n, double d, double d2, double d3, boolean flag) {
      this.entityWorld.playAuxSFXAtEntity((EntityPlayer)null, 1014, new BlockPos(this), 0);
      double headX = this.getHeadX(n);
      double headY = this.getHeadY(n);
      double headZ = this.getHeadZ(n);
      double d4 = d - headX;
      double d5 = d2 - headY;
      double d6 = d3 - headZ;
      EntityWitherSkull entityWitherSkull = new EntityWitherSkull(this.entityWorld, this, d4, d5, d6);
      if (flag) {
         entityWitherSkull.setInvulnerable(true);
      }

      entityWitherSkull.posY = headY;
      entityWitherSkull.posX = headX;
      entityWitherSkull.posZ = headZ;
      this.entityWorld.spawnEntityInWorld(entityWitherSkull);
   }

   public int getWatchedTargetId(int n) {
      return this.dataWatcher.getWatchableObjectInt(17 + n);
   }

   @Override
   public String getDeathSound() {
      return "mob.wither.death";
   }

   public void ignite() {
      this.setInvulTime(220);
      this.setHealth(this.getBossMaxHealth() / 3.0F);
   }

   private double getHeadZ(int n) {
      if (n <= 0) {
         return this.posZ;
      } else {
         float f2 = MathHelper.sin((this.renderYawOffset + (float)(180 * (n - 1))) / 180.0F * (float) Math.PI);
         return this.posZ + (double)f2 * 1.3;
      }
   }

   private double getHeadY(int n) {
      return n <= 0 ? this.posY + 3.0 : this.posY + 2.2;
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else if (damageSource == DamageSource.drown || damageSource.getEntity() instanceof EntityWither) {
         return false;
      } else if (this.getInvulTime() > 0 && damageSource != DamageSource.outOfWorld) {
         return false;
      } else {
         if (this.isArmored()) {
            if (damageSource.getSourceOfDamage() instanceof EntityArrow) {
               return false;
            }
         }

         Entity entity = damageSource.getEntity();
         if (entity != null
            && !(entity instanceof EntityPlayer)
            && entity instanceof EntityLivingBase
            && ((EntityLivingBase)entity).getCreatureAttribute() == this.getCreatureAttribute()) {
            return false;
         } else {
            if (this.blockBreakCounter <= 0) {
               this.blockBreakCounter = 20;
            }

            for (int i = 0; i < this.idleHeadUpdates.length; i++) {
               this.idleHeadUpdates[i] = this.idleHeadUpdates[i] + 3;
            }

            return super.attackEntityFrom(damageSource, f);
         }
      }
   }

   @Override
   public void updateAITasks() {
      if (this.getInvulTime() > 0) {
         int n = this.getInvulTime() - 1;
         if (n <= 0) {
            this.entityWorld
               .newExplosion(
                  this, this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, 7.0F, false, this.entityWorld.getGameRules().getBoolean("mobGriefing")
               );
            this.entityWorld.playBroadcastSound(1013, new BlockPos(this), 0);
         }

         this.setInvulTime(n);
         if (this.ticksExisted % 10 == 0) {
            this.heal(10.0F);
         }
      } else {
         super.updateAITasks();

         for (int i = 1; i < 3; i++) {
            if (this.ticksExisted >= this.nextHeadUpdate[i - 1]) {
               this.nextHeadUpdate[i - 1] = this.ticksExisted + 10 + this.rand.nextInt(10);
               if (this.entityWorld.getDifficulty() == EnumDifficulty.NORMAL || this.entityWorld.getDifficulty() == EnumDifficulty.HARD) {
                  int n2 = i - 1;
                  int n3 = this.idleHeadUpdates[i - 1];
                  this.idleHeadUpdates[n2] = this.idleHeadUpdates[i - 1] + 1;
                  if (n3 > 15) {
                     float f = 10.0F;
                     float f2 = 5.0F;
                     this.launchWitherSkullToCoords(i + 1, MathHelper.getRandomDoubleInRange(this.rand, this.posX - (double)f, this.posX + (double)f), MathHelper.getRandomDoubleInRange(this.rand, this.posY - (double)f2, this.posY + (double)f2), MathHelper.getRandomDoubleInRange(this.rand, this.posZ - (double)f, this.posZ + (double)f), true);
                     this.idleHeadUpdates[i - 1] = 0;
                  }
               }

               int watchedTargetId = this.getWatchedTargetId(i);
               if (watchedTargetId > 0) {
                  Entity entityByID = this.entityWorld.getEntityByID(watchedTargetId);
                  if (entityByID == null || !entityByID.isEntityAlive() || !(this.getDistanceSqToEntity(entityByID) <= 900.0) || !this.canEntityBeSeen(entityByID)) {
                     this.updateWatchedTargetId(i, 0);
                  } else if (entityByID instanceof EntityPlayer && ((EntityPlayer)entityByID).capabilities.disableDamage) {
                     this.updateWatchedTargetId(i, 0);
                  } else {
                     this.launchWitherSkullToEntity(i + 1, (EntityLivingBase)entityByID);
                     this.nextHeadUpdate[i - 1] = this.ticksExisted + 40 + this.rand.nextInt(20);
                     this.idleHeadUpdates[i - 1] = 0;
                  }
               } else {
                  List entitiesWithinAABB = this.entityWorld
                     .getEntitiesWithinAABB(
                        EntityLivingBase.class,
                        this.getEntityBoundingBox().expand(20.0, 8.0, 20.0),
                        Predicates.and(attackEntitySelector, EntitySelectors.NOT_SPECTATING)
                     );

                  for (int j = 0; j < 10 && !entitiesWithinAABB.isEmpty(); j++) {
                     EntityLivingBase entity = (EntityLivingBase)entitiesWithinAABB.get(this.rand.nextInt(entitiesWithinAABB.size()));
                     if (entity != this && entity.isEntityAlive() && this.canEntityBeSeen(entity)) {
                        if (entity instanceof EntityPlayer) {
                           if (!((EntityPlayer)entity).capabilities.disableDamage) {
                              this.updateWatchedTargetId(i, entity.getEntityId());
                           }
                        } else {
                           this.updateWatchedTargetId(i, entity.getEntityId());
                        }
                        break;
                     }

                     entitiesWithinAABB.remove(entity);
                  }
               }
            }
         }

         if (this.getAttackTarget() != null) {
            this.updateWatchedTargetId(0, this.getAttackTarget().getEntityId());
         } else {
            this.updateWatchedTargetId(0, 0);
         }

         if (this.blockBreakCounter > 0) {
            this.blockBreakCounter--;
            if (this.blockBreakCounter == 0 && this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
               int n4 = MathHelper.floor_double(this.posY);
               int n5 = MathHelper.floor_double(this.posX);
               int n6 = MathHelper.floor_double(this.posZ);
               boolean flag = false;

               for (int k = -1; k <= 1; k++) {
                  for (int m = -1; m <= 1; m++) {
                     for (int idx = 0; idx <= 3; idx++) {
                        int n7 = n5 + k;
                        int n8 = n4 + idx;
                        int n9 = n6 + m;
                        BlockPos pos = new BlockPos(n7, n8, n9);
                        Block block = this.entityWorld.getBlockState(pos).getBlock();
                        if (block.getMaterial() != Material.air && canDestroyBlock(block)) {
                           flag = this.entityWorld.destroyBlock(pos, true) || flag;
                        }
                     }
                  }
               }

               if (flag) {
                  this.entityWorld.playAuxSFXAtEntity((EntityPlayer)null, 1012, new BlockPos(this), 0);
               }
            }
         }

         if (this.ticksExisted % 20 == 0) {
            this.heal(1.0F);
         }
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.wither.hurt";
   }

   public float getHeadYRotation(int n) {
      return this.yRotationHeads[n];
   }

   private void launchWitherSkullToEntity(int n, EntityLivingBase entity) {
      this.launchWitherSkullToCoords(n, entity.posX, entity.posY + (double)entity.getEyeHeight() * 0.5, entity.posZ, n == 0 && this.rand.nextFloat() < 0.001F);
   }

   @Override
   public void onLivingUpdate() {
      this.motionY *= 0.6F;
      if (!this.entityWorld.isRemote && this.getWatchedTargetId(0) > 0) {
         Entity entityByID = this.entityWorld.getEntityByID(this.getWatchedTargetId(0));
         if (entityByID != null) {
            if (this.posY < entityByID.posY || !this.isArmored() && this.posY < entityByID.posY + 5.0) {
               if (this.motionY < 0.0) {
                  this.motionY = 0.0;
               }

               this.motionY = this.motionY + (0.5 - this.motionY) * 0.6F;
            }

            double d = entityByID.posX - this.posX;
            double d2 = entityByID.posZ - this.posZ;
            double d3 = d * d + d2 * d2;
            if (d3 > 9.0) {
               double d4 = (double)MathHelper.sqrt_double(d3);
               this.motionX = this.motionX + (d / d4 * 0.5 - this.motionX) * 0.6F;
               this.motionZ = this.motionZ + (d2 / d4 * 0.5 - this.motionZ) * 0.6F;
            }
         }
      }

      if (this.motionX * this.motionX + this.motionZ * this.motionZ > 0.05F) {
         this.rotationYaw = (float)MathHelper.atan2(this.motionZ, this.motionX) * (180.0F / (float)Math.PI) - 90.0F;
      }

      super.onLivingUpdate();

      for (int i = 0; i < 2; i++) {
         this.prevYRotationHeads[i] = this.yRotationHeads[i];
         this.prevXRotationHeads[i] = this.xRotationHeads[i];
      }

      for (int j = 0; j < 2; j++) {
         int watchedTargetId = this.getWatchedTargetId(j + 1);
         Entity entity = null;
         if (watchedTargetId > 0) {
            entity = this.entityWorld.getEntityByID(watchedTargetId);
         }

         if (entity != null) {
            double headX = this.getHeadX(j + 1);
            double headY = this.getHeadY(j + 1);
            double headZ = this.getHeadZ(j + 1);
            double d5 = entity.posX - headX;
            double d6 = entity.posY + (double)entity.getEyeHeight() - headY;
            double d7 = entity.posZ - headZ;
            double d8 = (double)MathHelper.sqrt_double(d5 * d5 + d7 * d7);
            float f = (float)(MathHelper.atan2(d7, d5) * 180.0 / Math.PI) - 90.0F;
            float f2 = (float)(-(MathHelper.atan2(d6, d8) * 180.0 / Math.PI));
            this.xRotationHeads[j] = this.rotlerp(this.xRotationHeads[j], f2, 40.0F);
            this.yRotationHeads[j] = this.rotlerp(this.yRotationHeads[j], f, 10.0F);
         } else {
            this.yRotationHeads[j] = this.rotlerp(this.yRotationHeads[j], this.renderYawOffset, 10.0F);
         }
      }

      boolean isArmored = this.isArmored();

      for (int k = 0; k < 3; k++) {
         double headX2 = this.getHeadX(k);
         double headY2 = this.getHeadY(k);
         double headZ2 = this.getHeadZ(k);
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.SMOKE_NORMAL,
               headX2 + this.rand.nextGaussian() * 0.3F,
               headY2 + this.rand.nextGaussian() * 0.3F,
               headZ2 + this.rand.nextGaussian() * 0.3F,
               0.0,
               0.0,
               0.0,
               new int[0]
            );
         if (isArmored && this.entityWorld.rand.nextInt(4) == 0) {
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.SPELL_MOB,
                  headX2 + this.rand.nextGaussian() * 0.3F,
                  headY2 + this.rand.nextGaussian() * 0.3F,
                  headZ2 + this.rand.nextGaussian() * 0.3F,
                  0.7F,
                  0.7F,
                  0.5,
                  new int[0]
               );
         }
      }

      if (this.getInvulTime() > 0) {
         for (int m = 0; m < 3; m++) {
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.SPELL_MOB,
                  this.posX + this.rand.nextGaussian() * 1.0,
                  this.posY + (double)(this.rand.nextFloat() * 3.3F),
                  this.posZ + this.rand.nextGaussian() * 1.0,
                  0.7F,
                  0.7F,
                  0.9F,
                  new int[0]
               );
         }
      }
   }

   @Override
   public int getTotalArmorValue() {
      return 4;
   }

   @Override
   public void attackEntityWithRangedAttack(EntityLivingBase entity, float f) {
      this.launchWitherSkullToEntity(0, entity);
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6F);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0);
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   @Override
   public void addPotionEffect(PotionEffect potionEffect) {
   }

   @Override
   public String getLivingSound() {
      return "mob.wither.idle";
   }

   public static boolean canDestroyBlock(Block block) {
      return block != Blocks.bedrock && block != Blocks.end_portal && block != Blocks.end_portal_frame && block != Blocks.command_block && block != Blocks.barrier;
   }


   public static final class FilterPredicate implements Predicate {
      public boolean apply(Entity entity) {
         return entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }
   }
}
