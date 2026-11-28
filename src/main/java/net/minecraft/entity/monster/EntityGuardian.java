package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.ItemFishFood;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.World;

public class EntityGuardian extends EntityMob {
   private float clientSideTailAnimation;
   private float clientSideTailAnimationO;
   private float clientSideTailAnimationSpeed;
   private float clientSideSpikesAnimation;
   private float clientSideSpikesAnimationO;
   private EntityLivingBase targetedEntity;
   private int clientSideAttackTime;
   private boolean clientSideTouchedGround;
   private EntityAIWander wander;

   public static EntityAIWander accessWander(EntityGuardian entityGuardian) {
      return entityGuardian.wander;
   }

   @Override
   public void moveEntityWithHeading(float f, float f2) {
      if (this.isServerWorld()) {
         if (this.isInWater()) {
            this.moveFlying(f, f2, 0.1F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.9F;
            this.motionY *= 0.9F;
            this.motionZ *= 0.9F;
            if (!this.isMoving() && this.getAttackTarget() == null) {
               this.motionY -= 0.005;
            }
         } else {
            super.moveEntityWithHeading(f, f2);
         }
      } else {
         super.moveEntityWithHeading(f, f2);
      }
   }

   @Override
   public float getBlockPathWeight(BlockPos pos) {
      return this.entityWorld.getBlockState(pos).getBlock().getMaterial() == Material.water
         ? 10.0F + this.entityWorld.getLightBrightness(pos.getX(), pos.getY(), pos.getZ()) - 0.5F
         : super.getBlockPathWeight(pos);
   }

   @Override
   public int getTalkInterval() {
      return 160;
   }

   private boolean isSyncedFlagSet(int n) {
      return (this.dataWatcher.getWatchableObjectInt(16) & n) != 0;
   }

   public boolean isMoving() {
      return this.isSyncedFlagSet(2);
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      int n2 = this.rand.nextInt(3) + this.rand.nextInt(n + 1);
      if (n2 > 0) {
         this.entityDropItem(new ItemStack(Items.prismarine_shard, n2, 0), 1.0F);
      }

      if (this.rand.nextInt(3 + n) > 1) {
         this.entityDropItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.getMetadata()), 1.0F);
      } else if (this.rand.nextInt(3 + n) > 1) {
         this.entityDropItem(new ItemStack(Items.prismarine_crystals, 1, 0), 1.0F);
      }

      if (flag && this.isElder()) {
         this.entityDropItem(new ItemStack(Blocks.sponge, 1, 1), 1.0F);
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
   }

   @Override
   public boolean isValidLightLevel() {
      return true;
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   private void setTargetedEntity(int n) {
      this.dataWatcher.updateObject(17, n);
   }

   public float getAttackAnimationScale(float f) {
      return ((float)this.clientSideAttackTime + f) / (float)this.getAttackDuration();
   }

   public boolean isElder() {
      return this.isSyncedFlagSet(4);
   }

   public static void invokeSetTargetedEntity(EntityGuardian entityGuardian, int n) {
      entityGuardian.setTargetedEntity(n);
   }

   private void setMoving(boolean flag) {
      this.setSyncedFlag(2, flag);
   }

   public EntityLivingBase getTargetedEntity() {
      if (!this.hasTargetedEntity()) {
         return null;
      } else if (this.entityWorld.isRemote) {
         if (this.targetedEntity != null) {
            return this.targetedEntity;
         } else {
            Entity entityByID = this.entityWorld.getEntityByID(this.dataWatcher.getWatchableObjectInt(17));
            if (entityByID instanceof EntityLivingBase) {
               this.targetedEntity = (EntityLivingBase)entityByID;
               return this.targetedEntity;
            } else {
               return null;
            }
         }
      } else {
         return this.getAttackTarget();
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (!this.isMoving() && !damageSource.isMagicDamage() && damageSource.getSourceOfDamage() instanceof EntityLivingBase) {
         EntityLivingBase sourceOfDamage = (EntityLivingBase)damageSource.getSourceOfDamage();
         if (!damageSource.isExplosion()) {
            sourceOfDamage.attackEntityFrom(DamageSource.causeThornsDamage(this), 2.0F);
            sourceOfDamage.playSound("damage.thorns", 0.5F, 1.0F);
         }
      }

      this.wander.makeUpdate();
      return super.attackEntityFrom(damageSource, f);
   }

   @Override
   public void addRandomDrop() {
      this.entityDropItem(((WeightedRandomFishable)WeightedRandom.getRandomItem(this.rand, EntityFishHook.getFish())).getItemStack(this.rand), 1.0F);
   }

   private void setSyncedFlag(int n, boolean flag) {
      int watchableObjectInt = this.dataWatcher.getWatchableObjectInt(16);
      if (flag) {
         this.dataWatcher.updateObject(16, watchableObjectInt | n);
      } else {
         this.dataWatcher.updateObject(16, watchableObjectInt & ~n);
      }
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, 0);
      this.dataWatcher.addObject(17, 0);
   }

   public static void invokeSetMoving(EntityGuardian entityGuardian, boolean flag) {
      entityGuardian.setMoving(flag);
   }

   @Override
   public String getLivingSound() {
      return !this.isInWater() ? "mob.guardian.land.idle" : (this.isElder() ? "mob.guardian.elder.idle" : "mob.guardian.idle");
   }

   @Override
   public boolean isNotColliding() {
      return this.entityWorld.checkNoEntityCollision(this.getEntityBoundingBox(), this)
         && this.entityWorld.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty();
   }

   public void setElder(boolean flag) {
      this.setSyncedFlag(4, flag);
      if (flag) {
         this.setSize(1.9975F, 1.9975F);
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
         this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0);
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
         this.enablePersistence();
         this.wander.setExecutionChance(400);
      }
   }

   @Override
   public boolean getCanSpawnHere() {
      return (this.rand.nextInt(20) == 0 || !this.entityWorld.canBlockSeeSky(new BlockPos(this))) && super.getCanSpawnHere();
   }

   public void setElder() {
      this.setElder(true);
      this.clientSideSpikesAnimationO = this.clientSideSpikesAnimation = 1.0F;
   }

   public float getTailAnimation(float f) {
      return this.clientSideTailAnimationO + (this.clientSideTailAnimation - this.clientSideTailAnimationO) * f;
   }

   @Override
   public void onDataWatcherUpdate(int n) {
      super.onDataWatcherUpdate(n);
      if (n == 16) {
         if (this.isElder() && this.width < 1.0F) {
            this.setSize(1.9975F, 1.9975F);
         }
      } else if (n == 17) {
         this.clientSideAttackTime = 0;
         this.targetedEntity = null;
      }
   }

   public boolean hasTargetedEntity() {
      return this.dataWatcher.getWatchableObjectInt(17) != 0;
   }

   @Override
   public String getHurtSound() {
      return !this.isInWater() ? "mob.guardian.land.hit" : (this.isElder() ? "mob.guardian.elder.hit" : "mob.guardian.hit");
   }

   public EntityGuardian(World world) {
      super(world);
      this.experienceValue = 10;
      this.setSize(0.85F, 0.85F);
      this.tasks.addTask(4, new EntityGuardian.AIGuardianAttack(this));
      EntityAIMoveTowardsRestriction restriction;
      this.tasks.addTask(5, restriction = new EntityAIMoveTowardsRestriction(this, 1.0));
      this.tasks.addTask(7, this.wander = new EntityAIWander(this, 1.0, 80));
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityGuardian.class, 12.0F, 0.01F));
      this.tasks.addTask(9, new EntityAILookIdle(this));
      this.wander.setMutexBits(3);
      restriction.setMutexBits(3);
      this.targetTasks
         .addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, true, false, new EntityGuardian.GuardianTargetSelector(this)));
      this.moveHelper = new EntityGuardian.GuardianMoveHelper(this);
      this.clientSideTailAnimationO = this.clientSideTailAnimation = this.rand.nextFloat();
   }

   @Override
   public int getVerticalFaceSpeed() {
      return 180;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setBoolean("Elder", this.isElder());
   }

   @Override
   public void onLivingUpdate() {
      if (this.entityWorld.isRemote) {
         this.clientSideTailAnimationO = this.clientSideTailAnimation;
         if (!this.isInWater()) {
            this.clientSideTailAnimationSpeed = 2.0F;
            if (this.motionY > 0.0 && this.clientSideTouchedGround && !this.isSilent()) {
               this.entityWorld.playSound(this.posX, this.posY, this.posZ, "mob.guardian.flop", 1.0F, 1.0F, false);
            }

            this.clientSideTouchedGround = this.motionY < 0.0 && this.entityWorld.isBlockNormalCube(new BlockPos(this).down(), false);
         } else if (this.isMoving()) {
            if (this.clientSideTailAnimationSpeed < 0.5F) {
               this.clientSideTailAnimationSpeed = 4.0F;
            } else {
               this.clientSideTailAnimationSpeed = this.clientSideTailAnimationSpeed + (0.5F - this.clientSideTailAnimationSpeed) * 0.1F;
            }
         } else {
            this.clientSideTailAnimationSpeed = this.clientSideTailAnimationSpeed + (0.125F - this.clientSideTailAnimationSpeed) * 0.2F;
         }

         this.clientSideTailAnimation = this.clientSideTailAnimation + this.clientSideTailAnimationSpeed;
         this.clientSideSpikesAnimationO = this.clientSideSpikesAnimation;
         if (!this.isInWater()) {
            this.clientSideSpikesAnimation = this.rand.nextFloat();
         } else if (this.isMoving()) {
            this.clientSideSpikesAnimation = this.clientSideSpikesAnimation + (0.0F - this.clientSideSpikesAnimation) * 0.25F;
         } else {
            this.clientSideSpikesAnimation = this.clientSideSpikesAnimation + (1.0F - this.clientSideSpikesAnimation) * 0.06F;
         }

         if (this.isMoving() && this.isInWater()) {
            Vec3 look = this.getLook(0.0F);

            for (int i = 0; i < 2; i++) {
               this.entityWorld
                  .spawnParticle(
                     EnumParticleTypes.WATER_BUBBLE,
                     this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width - look.xCoord * 1.5,
                     this.posY + this.rand.nextDouble() * (double)this.height - look.yCoord * 1.5,
                     this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width - look.zCoord * 1.5,
                     0.0,
                     0.0,
                     0.0,
                     new int[0]
                  );
            }
         }

         if (this.hasTargetedEntity()) {
            if (this.clientSideAttackTime < this.getAttackDuration()) {
               this.clientSideAttackTime++;
            }

            EntityLivingBase targetedEntity2 = this.getTargetedEntity();
            if (targetedEntity2 != null) {
               this.getLookHelper().setLookPositionWithEntity(targetedEntity2, 90.0F, 90.0F);
               this.getLookHelper().onUpdateLook();
               double attackAnimationScale = (double)this.getAttackAnimationScale(0.0F);
               double d = targetedEntity2.posX - this.posX;
               double d2 = targetedEntity2.posY + (double)(targetedEntity2.height * 0.5F) - (this.posY + (double)this.getEyeHeight());
               double d3 = targetedEntity2.posZ - this.posZ;
               double d4 = Math.sqrt(d * d + d2 * d2 + d3 * d3);
               d /= d4;
               d2 /= d4;
               d3 /= d4;
               double d5 = this.rand.nextDouble();

               while (d5 < d4) {
                  d5 += 1.8 - attackAnimationScale + this.rand.nextDouble() * (1.7 - attackAnimationScale);
                  this.entityWorld
                     .spawnParticle(
                        EnumParticleTypes.WATER_BUBBLE,
                        this.posX + d * d5,
                        this.posY + d2 * d5 + (double)this.getEyeHeight(),
                        this.posZ + d3 * d5,
                        0.0,
                        0.0,
                        0.0,
                        new int[0]
                     );
               }
            }
         }
      }

      if (this.inWater) {
         this.setAir(300);
      } else if (this.onGround) {
         this.motionY += 0.5;
         this.motionX = this.motionX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
         this.motionZ = this.motionZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
         this.rotationYaw = this.rand.nextFloat() * 360.0F;
         this.onGround = false;
         this.isAirBorne = true;
      }

      if (this.hasTargetedEntity()) {
         this.rotationYaw = this.rotationYawHead;
      }

      super.onLivingUpdate();
   }

   @Override
   public PathNavigate getNewNavigator(World world) {
      return new PathNavigateSwimmer(this, world);
   }

   public float getSpikesAnimation(float f) {
      return this.clientSideSpikesAnimationO + (this.clientSideSpikesAnimation - this.clientSideSpikesAnimationO) * f;
   }

   public int getAttackDuration() {
      return this.isElder() ? 60 : 80;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.setElder(tag.getBoolean("Elder"));
   }

   @Override
   public void updateAITasks() {
      super.updateAITasks();
      if (this.isElder()) {
         if ((this.ticksExisted + this.getEntityId()) % 1200 == 0) {
            Potion digSlowdown = Potion.digSlowdown;

            for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(this.entityWorld.getPlayers(EntityPlayerMP.class, new EntityGuardian.FilterPredicate(this)))) {
               if (!entityPlayerMP.isPotionActive(digSlowdown) || entityPlayerMP.getActivePotionEffect(digSlowdown).getAmplifier() < 2 || entityPlayerMP.getActivePotionEffect(digSlowdown).getDuration() < 1200) {
                  entityPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(10, 0.0F));
                  entityPlayerMP.addPotionEffect(new PotionEffect(digSlowdown.id, 6000, 2));
               }
            }
         }

         if (!this.hasHome()) {
            this.setHomePosAndDistance(new BlockPos(this), 16);
         }
      }
   }

   @Override
   public float getEyeHeight() {
      return this.height * 0.5F;
   }

   @Override
   public String getDeathSound() {
      return !this.isInWater() ? "mob.guardian.land.death" : (this.isElder() ? "mob.guardian.elder.death" : "mob.guardian.death");
   }


   public static class FilterPredicate implements Predicate {
      public final EntityGuardian guardian;

      public boolean apply(Object obj) {
         return this.apply((EntityPlayerMP)obj);
      }

      public boolean apply(EntityPlayerMP entityPlayerMP) {
         return this.guardian.getDistanceSqToEntity(entityPlayerMP) < 2500.0 && entityPlayerMP.theItemInWorldManager.survivalOrAdventure();
      }

      public FilterPredicate(EntityGuardian entityGuardian) {
         this.guardian = entityGuardian;
      }
   }

   public static class AIGuardianAttack extends EntityAIBase {
      private EntityGuardian theEntity;
      private int tickCounter;

      @Override
      public void resetTask() {
         EntityGuardian.invokeSetTargetedEntity(this.theEntity, 0);
         this.theEntity.setAttackTarget((EntityLivingBase)null);
         EntityGuardian.accessWander(this.theEntity).makeUpdate();
      }

      @Override
      public void startExecuting() {
         this.tickCounter = -10;
         this.theEntity.getNavigator().clearPathEntity();
         this.theEntity.getLookHelper().setLookPositionWithEntity(this.theEntity.getAttackTarget(), 90.0F, 90.0F);
         this.theEntity.isAirBorne = true;
      }

      @Override
      public void updateTask() {
         EntityLivingBase attackTarget = this.theEntity.getAttackTarget();
         this.theEntity.getNavigator().clearPathEntity();
         this.theEntity.getLookHelper().setLookPositionWithEntity(attackTarget, 90.0F, 90.0F);
         if (!this.theEntity.canEntityBeSeen(attackTarget)) {
            this.theEntity.setAttackTarget((EntityLivingBase)null);
         } else {
            this.tickCounter++;
            if (this.tickCounter == 0) {
               EntityGuardian.invokeSetTargetedEntity(this.theEntity, this.theEntity.getAttackTarget().getEntityId());
               this.theEntity.entityWorld.setEntityState(this.theEntity, (byte)21);
            } else if (this.tickCounter >= this.theEntity.getAttackDuration()) {
               float f = 1.0F;
               if (this.theEntity.entityWorld.getDifficulty() == EnumDifficulty.HARD) {
                  f += 2.0F;
               }

               if (this.theEntity.isElder()) {
                  f += 2.0F;
               }

               attackTarget.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.theEntity, this.theEntity), f);
               attackTarget.attackEntityFrom(
                  DamageSource.causeMobDamage(this.theEntity), (float)this.theEntity.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue()
               );
               this.theEntity.setAttackTarget((EntityLivingBase)null);
            } else if (this.tickCounter >= 60 && this.tickCounter % 20 == 0) {
            }

            super.updateTask();
         }
      }

      @Override
      public boolean shouldExecute() {
         EntityLivingBase attackTarget = this.theEntity.getAttackTarget();
         return attackTarget != null && attackTarget.isEntityAlive();
      }

      @Override
      public boolean continueExecuting() {
         return super.continueExecuting() && (this.theEntity.isElder() || this.theEntity.getDistanceSqToEntity(this.theEntity.getAttackTarget()) > 9.0);
      }

      public AIGuardianAttack(EntityGuardian entityGuardian) {
         this.theEntity = entityGuardian;
         this.setMutexBits(3);
      }
   }

   public static class GuardianMoveHelper extends EntityMoveHelper {
      private EntityGuardian entityGuardian;

      @Override
      public void onUpdateMoveHelper() {
         if (this.update && !this.entityGuardian.getNavigator().noPath()) {
            double d = this.posX - this.entityGuardian.posX;
            double d2 = this.posY - this.entityGuardian.posY;
            double d3 = this.posZ - this.entityGuardian.posZ;
            double d4 = d * d + d2 * d2 + d3 * d3;
            d4 = (double)MathHelper.sqrt_double(d4);
            d2 /= d4;
            float f = (float)(MathHelper.atan2(d3, d) * 180.0 / Math.PI) - 90.0F;
            this.entityGuardian.rotationYaw = this.limitAngle(this.entityGuardian.rotationYaw, f, 30.0F);
            this.entityGuardian.renderYawOffset = this.entityGuardian.rotationYaw;
            float f2 = (float)(this.speed * this.entityGuardian.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
            this.entityGuardian.setAIMoveSpeed(this.entityGuardian.getAIMoveSpeed() + (f2 - this.entityGuardian.getAIMoveSpeed()) * 0.125F);
            double d5 = Math.sin((double)(this.entityGuardian.ticksExisted + this.entityGuardian.getEntityId()) * 0.5) * 0.05;
            double d6 = Math.cos((double)(this.entityGuardian.rotationYaw * (float) Math.PI / 180.0F));
            double d7 = Math.sin((double)(this.entityGuardian.rotationYaw * (float) Math.PI / 180.0F));
            this.entityGuardian.motionX += d5 * d6;
            this.entityGuardian.motionZ += d5 * d7;
            d5 = Math.sin((double)(this.entityGuardian.ticksExisted + this.entityGuardian.getEntityId()) * 0.75) * 0.05;
            this.entityGuardian.motionY += d5 * (d7 + d6) * 0.25;
            this.entityGuardian.motionY = this.entityGuardian.motionY + (double)this.entityGuardian.getAIMoveSpeed() * d2 * 0.1;
            EntityLookHelper lookHelper = this.entityGuardian.getLookHelper();
            double d8 = this.entityGuardian.posX + d / d4 * 2.0;
            double d9 = (double)this.entityGuardian.getEyeHeight() + this.entityGuardian.posY + d2 / d4 * 1.0;
            double d10 = this.entityGuardian.posZ + d3 / d4 * 2.0;
            double lookPosX = lookHelper.getLookPosX();
            double lookPosY = lookHelper.getLookPosY();
            double lookPosZ = lookHelper.getLookPosZ();
            if (!lookHelper.getIsLooking()) {
               lookPosX = d8;
               lookPosY = d9;
               lookPosZ = d10;
            }

            this.entityGuardian
               .getLookHelper()
               .setLookPosition(lookPosX + (d8 - lookPosX) * 0.125, lookPosY + (d9 - lookPosY) * 0.125, lookPosZ + (d10 - lookPosZ) * 0.125, 10.0F, 40.0F);
            EntityGuardian.invokeSetMoving(this.entityGuardian, true);
         } else {
            this.entityGuardian.setAIMoveSpeed(0.0F);
            EntityGuardian.invokeSetMoving(this.entityGuardian, false);
         }
      }

      public GuardianMoveHelper(EntityGuardian entityGuardian2) {
         super(entityGuardian2);
         this.entityGuardian = entityGuardian2;
      }
   }

   public static class GuardianTargetSelector implements Predicate {
      private EntityGuardian parentEntity;

      public GuardianTargetSelector(EntityGuardian entityGuardian) {
         this.parentEntity = entityGuardian;
      }

      public boolean apply(Object obj) {
         return this.apply((EntityLivingBase)obj);
      }

      public boolean apply(EntityLivingBase entity) {
         return (entity instanceof EntityPlayer || entity instanceof EntitySquid) && entity.getDistanceSqToEntity(this.parentEntity) > 9.0;
      }
   }
}
