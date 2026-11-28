package net.minecraft.entity.monster;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class EntitySlime extends EntityLiving implements IMob {
   public float squishAmount;
   public float squishFactor;
   public float prevSquishFactor;
   private boolean wasOnGround;

   public void alterSquishAmount() {
      this.squishAmount *= 0.6F;
   }

   public EntitySlime(World world) {
      super(world);
      this.moveHelper = new EntitySlime.SlimeMoveHelper(this);
      this.tasks.addTask(1, new EntitySlime.AISlimeFloat(this));
      this.tasks.addTask(2, new EntitySlime.AISlimeAttack(this));
      this.tasks.addTask(3, new EntitySlime.AISlimeFaceRandom(this));
      this.tasks.addTask(5, new EntitySlime.AISlimeHop(this));
      this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
      this.targetTasks.addTask(3, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
   }

   public boolean makesSoundOnLand() {
      return this.getSlimeSize() > 2;
   }

   @Override
   public void onDataWatcherUpdate(int n) {
      if (n == 16) {
         int slimeSize = this.getSlimeSize();
         this.setSize(0.51000005F * (float)slimeSize, 0.51000005F * (float)slimeSize);
         this.rotationYaw = this.rotationYawHead;
         this.renderYawOffset = this.rotationYawHead;
         if (this.isInWater() && this.rand.nextInt(20) == 0) {
            this.resetHeight();
         }
      }

      super.onDataWatcherUpdate(n);
   }

   public EnumParticleTypes getParticleType() {
      return EnumParticleTypes.SLIME;
   }

   @Override
   public float getSoundVolume() {
      return 0.4F * (float)this.getSlimeSize();
   }

   @Override
   public Item getDropItem() {
      return this.getSlimeSize() == 1 ? Items.slime_ball : null;
   }

   public EntitySlime createInstance() {
      return new EntitySlime(this.entityWorld);
   }

   @Override
   public int getVerticalFaceSpeed() {
      return 0;
   }

   public int getAttackStrength() {
      return this.getSlimeSize();
   }

   public void setSlimeSize(int n) {
      this.dataWatcher.updateObject(16, (byte)n);
      this.setSize(0.51000005F * (float)n, 0.51000005F * (float)n);
      this.setPosition(this.posX, this.posY, this.posZ);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)(n * n));
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)(0.2F + 0.1F * (float)n));
      this.setHealth(this.getMaxHealth());
      this.experienceValue = n;
   }

   public boolean canDamagePlayer() {
      return this.getSlimeSize() > 1;
   }

   public boolean makesSoundOnJump() {
      return this.getSlimeSize() > 0;
   }

   @Override
   public String getDeathSound() {
      return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
   }

   public int getJumpDelay() {
      return this.rand.nextInt(20) + 10;
   }

   @Override
   public void setDead() {
      int slimeSize = this.getSlimeSize();
      if (!this.entityWorld.isRemote && slimeSize > 1 && this.getCurrentHealth() <= 0.0F) {
         int n = 2 + this.rand.nextInt(3);

         for (int i = 0; i < n; i++) {
            float f = ((float)(i % 2) - 0.5F) * (float)slimeSize / 4.0F;
            float f2 = ((float)(i / 2) - 0.5F) * (float)slimeSize / 4.0F;
            EntitySlime entitySlime = this.createInstance();
            if (this.hasCustomName()) {
               entitySlime.setCustomNameTag(this.getCustomNameTag());
            }

            if (this.isNoDespawnRequired()) {
               entitySlime.enablePersistence();
            }

            entitySlime.setSlimeSize(slimeSize / 2);
            entitySlime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5, this.posZ + (double)f2, this.rand.nextFloat() * 360.0F, 0.0F);
            this.entityWorld.spawnEntityInWorld(entitySlime);
         }
      }

      super.setDead();
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("Size", this.getSlimeSize() - 1);
      tag.setBoolean("wasOnGround", this.wasOnGround);
   }

   public String getJumpSound() {
      return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
   }

   public int getSlimeSize() {
      return this.dataWatcher.getWatchableObjectByte(16);
   }

   @Override
   public void jump() {
      this.motionY = 0.42F;
      this.isAirBorne = true;
   }

   public void dealDamage(EntityLivingBase entity) {
      int slimeSize = this.getSlimeSize();
      if (this.canEntityBeSeen(entity)
         && this.getDistanceSqToEntity(entity) < 0.6 * (double)slimeSize * 0.6 * (double)slimeSize
         && entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength())) {
         this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         this.applyEnchantments(this, entity);
      }
   }

   @Override
   public void onUpdate() {
      if (!this.entityWorld.isRemote && this.entityWorld.getDifficulty() == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0) {
         this.isDead = true;
      }

      this.squishFactor = this.squishFactor + (this.squishAmount - this.squishFactor) * 0.5F;
      this.prevSquishFactor = this.squishFactor;
      super.onUpdate();
      if (this.onGround && !this.wasOnGround) {
         int slimeSize = this.getSlimeSize();

         for (int i = 0; i < slimeSize * 8; i++) {
            float f = this.rand.nextFloat() * (float) Math.PI * 2.0F;
            float f2 = this.rand.nextFloat() * 0.5F + 0.5F;
            float f3 = MathHelper.sin(f) * (float)slimeSize * 0.5F * f2;
            float f4 = MathHelper.cos(f) * (float)slimeSize * 0.5F * f2;
            World entityWorld2 = this.entityWorld;
            EnumParticleTypes particleType = this.getParticleType();
            double d = this.posX + (double)f3;
            double d2 = this.posZ + (double)f4;
            entityWorld2.spawnParticle(particleType, d, this.getEntityBoundingBox().minY, d2, 0.0, 0.0, 0.0, new int[0]);
         }

         if (this.makesSoundOnLand()) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
         }

         this.squishAmount = -0.5F;
      } else if (!this.onGround && this.wasOnGround) {
         this.squishAmount = 1.0F;
      }

      this.wasOnGround = this.onGround;
      this.alterSquishAmount();
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, IEntityLivingData entityLivingData) {
      int n = this.rand.nextInt(3);
      if (n < 2 && this.rand.nextFloat() < 0.5F * difficultyInstance.getClampedAdditionalDifficulty()) {
         n++;
      }

      this.setSlimeSize(1 << n);
      return super.onInitialSpawn(difficultyInstance, entityLivingData);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      int integer = tag.getInteger("Size");
      if (integer < 0) {
         integer = 0;
      }

      this.setSlimeSize(integer + 1);
      this.wasOnGround = tag.getBoolean("wasOnGround");
   }

   @Override
   public void onCollideWithPlayer(EntityPlayer player) {
      if (this.canDamagePlayer()) {
         this.dealDamage(player);
      }
   }

   @Override
   public float getEyeHeight() {
      return 0.625F * this.height;
   }

   @Override
   public void applyEntityCollision(Entity entity) {
      super.applyEntityCollision(entity);
      if (entity instanceof EntityIronGolem && this.canDamagePlayer()) {
         this.dealDamage((EntityLivingBase)entity);
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
   }

   @Override
   public boolean getCanSpawnHere() {
      BlockPos pos = new BlockPos(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ));
      Chunk chunkFromBlockCoords = this.entityWorld.getChunkFromBlockCoords(pos);
      if (this.entityWorld.getWorldInfo().getTerrainType() == WorldType.FLAT && this.rand.nextInt(4) != 1) {
         return false;
      } else {
         if (this.entityWorld.getDifficulty() != EnumDifficulty.PEACEFUL) {
            if (this.entityWorld.getBiomeGenForCoords(pos) == BiomeGenBase.swampland
               && this.posY > 50.0
               && this.posY < 70.0
               && this.rand.nextFloat() < 0.5F
               && this.rand.nextFloat() < this.entityWorld.getCurrentMoonPhaseFactor()
               && this.entityWorld.getLight(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))
                  <= this.rand.nextInt(8)) {
               return super.getCanSpawnHere();
            }

            if (this.rand.nextInt(10) == 0 && chunkFromBlockCoords.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0) {
               return super.getCanSpawnHere();
            }
         }

         return false;
      }
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, (byte)1);
   }


   public static class AISlimeHop extends EntityAIBase {
      private EntitySlime slime;

      public AISlimeHop(EntitySlime entitySlime) {
         this.slime = entitySlime;
         this.setMutexBits(5);
      }

      @Override
      public boolean shouldExecute() {
         return true;
      }

      @Override
      public void updateTask() {
         ((EntitySlime.SlimeMoveHelper)this.slime.getMoveHelper()).setSpeed(1.0);
      }
   }

   public static class AISlimeFloat extends EntityAIBase {
      private EntitySlime slime;

      public AISlimeFloat(EntitySlime entitySlime) {
         this.slime = entitySlime;
         this.setMutexBits(5);
         ((PathNavigateGround)entitySlime.getNavigator()).setCanSwim(true);
      }

      @Override
      public void updateTask() {
         if (this.slime.getRNG().nextFloat() < 0.8F) {
            this.slime.getJumpHelper().setJumping();
         }

         ((EntitySlime.SlimeMoveHelper)this.slime.getMoveHelper()).setSpeed(1.2);
      }

      @Override
      public boolean shouldExecute() {
         return this.slime.isInWater() || this.slime.isInLava();
      }
   }

   public static class AISlimeAttack extends EntityAIBase {
      private EntitySlime slime;
      private int growTieredTimer;

      @Override
      public boolean continueExecuting() {
         EntityLivingBase attackTarget = this.slime.getAttackTarget();
         return attackTarget == null
            ? false
            : (!attackTarget.isEntityAlive() ? false : (attackTarget instanceof EntityPlayer && ((EntityPlayer)attackTarget).capabilities.disableDamage ? false : --this.growTieredTimer > 0));
      }

      @Override
      public void startExecuting() {
         this.growTieredTimer = 300;
         super.startExecuting();
      }

      public AISlimeAttack(EntitySlime entitySlime) {
         this.slime = entitySlime;
         this.setMutexBits(2);
      }

      @Override
      public void updateTask() {
         this.slime.faceEntity(this.slime.getAttackTarget(), 10.0F, 10.0F);
         ((EntitySlime.SlimeMoveHelper)this.slime.getMoveHelper()).setDirection(this.slime.rotationYaw, this.slime.canDamagePlayer());
      }

      @Override
      public boolean shouldExecute() {
         EntityLivingBase attackTarget = this.slime.getAttackTarget();
         return attackTarget == null ? false : (!attackTarget.isEntityAlive() ? false : !(attackTarget instanceof EntityPlayer) || !((EntityPlayer)attackTarget).capabilities.disableDamage);
      }
   }

   public static class SlimeMoveHelper extends EntityMoveHelper {
      private float yRot;
      private int jumpDelay;
      private EntitySlime slime;
      private boolean isAggressive;

      public SlimeMoveHelper(EntitySlime entitySlime) {
         super(entitySlime);
         this.slime = entitySlime;
      }

      @Override
      public void onUpdateMoveHelper() {
         this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.yRot, 30.0F);
         this.entity.rotationYawHead = this.entity.rotationYaw;
         this.entity.renderYawOffset = this.entity.rotationYaw;
         if (!this.update) {
            this.entity.setMoveForward(0.0F);
         } else {
            this.update = false;
            if (this.entity.onGround) {
               this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
               if (this.jumpDelay-- <= 0) {
                  this.jumpDelay = this.slime.getJumpDelay();
                  if (this.isAggressive) {
                     this.jumpDelay /= 3;
                  }

                  this.slime.getJumpHelper().setJumping();
                  if (this.slime.makesSoundOnJump()) {
                     this.slime
                        .playSound(
                           this.slime.getJumpSound(),
                           this.slime.getSoundVolume(),
                           ((this.slime.getRNG().nextFloat() - this.slime.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F
                        );
                  }
               } else {
                  this.slime.moveStrafing = this.slime.moveForward = 0.0F;
                  this.entity.setAIMoveSpeed(0.0F);
               }
            } else {
               this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
            }
         }
      }

      public void setSpeed(double d) {
         this.speed = d;
         this.update = true;
      }

      public void setDirection(float f, boolean flag) {
         this.yRot = f;
         this.isAggressive = flag;
      }
   }

   public static class AISlimeFaceRandom extends EntityAIBase {
      private EntitySlime slime;
      private float chosenDegrees;
      private int nextRandomizeTime;

      @Override
      public boolean shouldExecute() {
         return this.slime.getAttackTarget() == null && (this.slime.onGround || this.slime.isInWater() || this.slime.isInLava());
      }

      public AISlimeFaceRandom(EntitySlime entitySlime) {
         this.slime = entitySlime;
         this.setMutexBits(2);
      }

      @Override
      public void updateTask() {
         if (--this.nextRandomizeTime <= 0) {
            this.nextRandomizeTime = 40 + this.slime.getRNG().nextInt(60);
            this.chosenDegrees = (float)this.slime.getRNG().nextInt(360);
         }

         ((EntitySlime.SlimeMoveHelper)this.slime.getMoveHelper()).setDirection(this.chosenDegrees, false);
      }
   }
}
