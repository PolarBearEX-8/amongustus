package net.minecraft.entity.passive;

import net.minecraft.block.BlockCarrot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityRabbit extends EntityAnimal {
   private EntityRabbit.AIAvoidEntity aiAvoidWolves;
   private int rabbitJumpTicks = 0;
   private int jumpDuration = 0;
   private boolean isRabbitJumping = false;
   private boolean wasOnGround = false;
   private int currentMoveTypeDuration = 0;
   private EntityRabbit.EnumMoveType moveType = EntityRabbit.EnumMoveType.HOP;
   private int carrotTicks = 0;
   private EntityPlayer carrotOwner = null;

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
   }

   private void updateMoveTypeDuration() {
      this.currentMoveTypeDuration = this.getMoveTypeDuration();
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      return this.isEntityInvulnerable(damageSource) ? false : super.attackEntityFrom(damageSource, f);
   }

   @Override
   public String getDeathSound() {
      return "mob.rabbit.death";
   }

   private void enableJumpControl() {
      ((EntityRabbit.RabbitJumpHelper)this.jumpHelper).setCanJump(true);
   }

   private boolean isCarrotEaten() {
      return this.carrotTicks == 0;
   }

   private void disableJumpControl() {
      ((EntityRabbit.RabbitJumpHelper)this.jumpHelper).setCanJump(false);
   }

   @Override
   public boolean attackEntityAsMob(Entity entity) {
      if (this.getRabbitType() == 99) {
         this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
      } else {
         return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("RabbitType", this.getRabbitType());
      tag.setInteger("MoreCarrotTicks", this.carrotTicks);
   }

   @Override
   public void addRandomDrop() {
      this.entityDropItem(new ItemStack(Items.rabbit_foot, 1), 0.0F);
   }

   @Override
   public int getTotalArmorValue() {
      return this.getRabbitType() == 99 ? 8 : super.getTotalArmorValue();
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, IEntityLivingData entityLivingData) {
      entityLivingData = super.onInitialSpawn(difficultyInstance, entityLivingData);
      int n = this.rand.nextInt(6);
      boolean flag = false;
      if (entityLivingData instanceof EntityRabbit.RabbitTypeData) {
         n = ((EntityRabbit.RabbitTypeData)entityLivingData).typeData;
         flag = true;
      } else {
         entityLivingData = new EntityRabbit.RabbitTypeData(n);
      }

      this.setRabbitType(n);
      if (flag) {
         this.setGrowingAge(-24000);
      }

      return entityLivingData;
   }

   public int getMoveTypeDuration() {
      return this.moveType.getDuration();
   }

   private boolean isRabbitBreedingItem(Item item) {
      return item == Items.carrot || item == Items.golden_carrot || item == Item.getItemFromBlock(Blocks.yellow_flower);
   }

   public int getRabbitType() {
      return this.dataWatcher.getWatchableObjectByte(18);
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 1) {
         this.createRunningParticles();
         this.jumpDuration = 10;
         this.rabbitJumpTicks = 0;
      } else {
         super.handleStatusUpdate(b);
      }
   }

   public static boolean invokeIsCarrotEaten(EntityRabbit entityRabbit) {
      return entityRabbit.isCarrotEaten();
   }

   public void setMovementSpeed(double d) {
      this.getNavigator().setSpeed(d);
      this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), d);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.setRabbitType(tag.getInteger("RabbitType"));
      this.carrotTicks = tag.getInteger("MoreCarrotTicks");
   }

   private void checkLandingDelay() {
      this.updateMoveTypeDuration();
      this.disableJumpControl();
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return stack != null && this.isRabbitBreedingItem(stack.getItem());
   }

   public EntityRabbit(World world) {
      super(world);
      this.setSize(0.6F, 0.7F);
      this.jumpHelper = new EntityRabbit.RabbitJumpHelper(this, this);
      this.moveHelper = new EntityRabbit.RabbitMoveHelper(this);
      ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
      this.navigator.setHeightRequirement(2.5F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityRabbit.AIPanic(this, 1.33));
      this.tasks.addTask(2, new EntityAITempt(this, 1.0, Items.carrot, false));
      this.tasks.addTask(2, new EntityAITempt(this, 1.0, Items.golden_carrot, false));
      this.tasks.addTask(2, new EntityAITempt(this, 1.0, Item.getItemFromBlock(Blocks.yellow_flower), false));
      this.tasks.addTask(3, new EntityAIMate(this, 0.8));
      this.tasks.addTask(5, new EntityRabbit.AIRaidFarm(this));
      this.tasks.addTask(5, new EntityAIWander(this, 0.6));
      this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
      this.aiAvoidWolves = new EntityRabbit.AIAvoidEntity(this, EntityWolf.class, 16.0F, 1.33, 1.33);
      this.tasks.addTask(4, this.aiAvoidWolves);
      this.setMovementSpeed(0.0);
   }

   public void setRabbitType(int n) {
      if (n == 99) {
         this.tasks.removeTask(this.aiAvoidWolves);
         this.tasks.addTask(4, new EntityRabbit.AIEvilAttack(this));
         this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, true));
         if (!this.hasCustomName()) {
            this.setCustomNameTag(StatCollector.translateToLocal("entity.KillerBunny.name"));
         }
      }

      this.dataWatcher.updateObject(18, (byte)n);
   }

   private void calculateRotationYaw(double d, double d2) {
      this.rotationYaw = (float)(MathHelper.atan2(d2 - this.posZ, d - this.posX) * 180.0 / Math.PI) - 90.0F;
   }

   public String getJumpingSound() {
      return "mob.rabbit.hop";
   }

   public void createEatingParticles() {
      this.entityWorld
         .spawnParticle(
            EnumParticleTypes.BLOCK_DUST,
            this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
            this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height),
            this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
            0.0,
            0.0,
            0.0,
            new int[]{Block.getStateId(Blocks.carrots.getStateFromMeta(7))}
         );
      this.carrotTicks = 100;
   }

   public boolean isRabbitJumping() {
      return this.isRabbitJumping;
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      int n2 = this.rand.nextInt(2) + this.rand.nextInt(1 + n);

      for (int i = 0; i < n2; i++) {
         this.dropItem(Items.rabbit_hide, 1);
      }

      n2 = this.rand.nextInt(2);

      for (int j = 0; j < n2; j++) {
         if (this.isBurning()) {
            this.dropItem(Items.cooked_rabbit, 1);
         } else {
            this.dropItem(Items.rabbit, 1);
         }
      }
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(18, (byte)0);
   }

   public float getJumpCompletion(float f) {
      return this.jumpDuration == 0 ? 0.0F : ((float)this.rabbitJumpTicks + f) / (float)this.jumpDuration;
   }

   public void setMoveType(EntityRabbit.EnumMoveType enumMoveType) {
      this.moveType = enumMoveType;
   }

   @Override
   public String getLivingSound() {
      return "mob.rabbit.idle";
   }

   public void doMovementAction(EntityRabbit.EnumMoveType enumMoveType) {
      this.setJumping(true, enumMoveType);
      this.jumpDuration = enumMoveType.getJumpDuration();
      this.rabbitJumpTicks = 0;
   }

   @Override
   public float getJumpUpwardsMotion() {
      return this.moveHelper.isUpdating() && this.moveHelper.getY() > this.posY + 0.5 ? 0.5F : this.moveType.getSpeedFactor();
   }

   @Override
   public void updateAITasks() {
      if (this.moveHelper.getSpeed() > 0.8) {
         this.setMoveType(EntityRabbit.EnumMoveType.SPRINT);
      } else if (this.moveType != EntityRabbit.EnumMoveType.ATTACK) {
         this.setMoveType(EntityRabbit.EnumMoveType.HOP);
      }

      if (this.currentMoveTypeDuration > 0) {
         this.currentMoveTypeDuration--;
      }

      if (this.carrotTicks > 0) {
         this.carrotTicks = this.carrotTicks - this.rand.nextInt(3);
         if (this.carrotTicks < 0) {
            this.carrotTicks = 0;
         }
      }

      if (this.onGround) {
         if (!this.wasOnGround) {
            this.setJumping(false, EntityRabbit.EnumMoveType.NONE);
            this.checkLandingDelay();
         }

         if (this.getRabbitType() == 99 && this.currentMoveTypeDuration == 0) {
            EntityLivingBase attackTarget2 = this.getAttackTarget();
            if (attackTarget2 != null && this.getDistanceSqToEntity(attackTarget2) < 16.0) {
               this.calculateRotationYaw(attackTarget2.posX, attackTarget2.posZ);
               this.moveHelper.setMoveTo(attackTarget2.posX, attackTarget2.posY, attackTarget2.posZ, this.moveHelper.getSpeed());
               this.doMovementAction(EntityRabbit.EnumMoveType.ATTACK);
               this.wasOnGround = true;
            }
         }

         EntityRabbit.RabbitJumpHelper jumpHelper2 = (EntityRabbit.RabbitJumpHelper)this.jumpHelper;
         if (!jumpHelper2.getIsJumping()) {
            if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0) {
               PathEntity path = this.navigator.getPath();
               Vec3 vec = new Vec3(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());
               if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
                  vec = path.getPosition(this);
               }

               this.calculateRotationYaw(vec.xCoord, vec.zCoord);
               this.doMovementAction(this.moveType);
            }
         } else if (!jumpHelper2.canJump()) {
            this.enableJumpControl();
         }
      }

      this.wasOnGround = this.onGround;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.rabbitJumpTicks != this.jumpDuration) {
         if (this.rabbitJumpTicks == 0 && !this.entityWorld.isRemote) {
            this.entityWorld.setEntityState(this, (byte)1);
         }

         this.rabbitJumpTicks++;
      } else if (this.jumpDuration != 0) {
         this.rabbitJumpTicks = 0;
         this.jumpDuration = 0;
      }
   }

   public EntityRabbit createChild(EntityAgeable entityAgeable) {
      EntityRabbit entityRabbit = new EntityRabbit(this.entityWorld);
      if (entityAgeable instanceof EntityRabbit) {
         entityRabbit.setRabbitType(this.rand.nextBoolean() ? this.getRabbitType() : ((EntityRabbit)entityAgeable).getRabbitType());
      }

      return entityRabbit;
   }

   @Override
   public String getHurtSound() {
      return "mob.rabbit.hurt";
   }

   @Override
   public void spawnRunningParticles() {
   }

   public void setJumping(boolean flag, EntityRabbit.EnumMoveType enumMoveType) {
      super.setJumping(flag);
      if (!flag) {
         if (this.moveType == EntityRabbit.EnumMoveType.ATTACK) {
            this.moveType = EntityRabbit.EnumMoveType.HOP;
         }
      } else {
         this.setMovementSpeed(1.5 * (double)enumMoveType.getSpeed());
         this.playSound(this.getJumpingSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
      }

      this.isRabbitJumping = flag;
   }

   @Override
   public EntityAgeable createAgeableChild(EntityAgeable entityAgeable) {
      return this.createChild(entityAgeable);
   }


   public static class AIPanic extends EntityAIPanic {
      private EntityRabbit theEntity;

      public AIPanic(EntityRabbit entityRabbit, double d) {
         super(entityRabbit, d);
         this.theEntity = entityRabbit;
      }

      @Override
      public void updateTask() {
         super.updateTask();
         this.theEntity.setMovementSpeed(this.speed);
      }
   }

   public static class AIRaidFarm extends EntityAIMoveToBlock {
      private final EntityRabbit rabbit;
      private boolean wantsToRaid;
      private boolean canRaid = false;

      @Override
      public void updateTask() {
         super.updateTask();
         this.rabbit
            .getLookHelper()
            .setLookPosition(
               (double)this.destinationBlock.getX() + 0.5,
               (double)(this.destinationBlock.getY() + 1),
               (double)this.destinationBlock.getZ() + 0.5,
               10.0F,
               (float)this.rabbit.getVerticalFaceSpeed()
            );
         if (this.getIsAboveDestination()) {
            World entityWorld = this.rabbit.entityWorld;
            BlockPos pos = this.destinationBlock.up();
            IBlockState blockState = entityWorld.getBlockState(pos);
            Block block = blockState.getBlock();
            if (this.canRaid && block instanceof BlockCarrot && (Integer)blockState.getValue(BlockCarrot.AGE) == 7) {
               entityWorld.setBlockState(pos, Blocks.air.getDefaultState(), 2);
               entityWorld.destroyBlock(pos, true);
               this.rabbit.createEatingParticles();
            }

            this.canRaid = false;
            this.runDelay = 10;
         }
      }

      @Override
      public boolean continueExecuting() {
         return this.canRaid && super.continueExecuting();
      }

      @Override
      public boolean shouldMoveTo(World world, BlockPos pos) {
         Block block = world.getBlockState(pos).getBlock();
         if (block == Blocks.farmland) {
            pos = pos.up();
            IBlockState blockState = world.getBlockState(pos);
            block = blockState.getBlock();
            if (block instanceof BlockCarrot && (Integer)blockState.getValue(BlockCarrot.AGE) == 7 && this.wantsToRaid && !this.canRaid) {
               this.canRaid = true;
               return true;
            }
         }

         return false;
      }

      @Override
      public void startExecuting() {
         super.startExecuting();
      }

      public AIRaidFarm(EntityRabbit entityRabbit) {
         super(entityRabbit, 0.7F, 16);
         this.rabbit = entityRabbit;
      }

      @Override
      public boolean shouldExecute() {
         if (this.runDelay <= 0) {
            if (!this.rabbit.entityWorld.getGameRules().getBoolean("mobGriefing")) {
               return false;
            }

            this.canRaid = false;
            this.wantsToRaid = EntityRabbit.invokeIsCarrotEaten(this.rabbit);
         }

         return super.shouldExecute();
      }

      @Override
      public void resetTask() {
         super.resetTask();
      }
   }

   public static class AIEvilAttack extends EntityAIAttackOnCollide {
      public AIEvilAttack(EntityRabbit entityRabbit) {
         super(entityRabbit, EntityLivingBase.class, 1.4, true);
      }

      @Override
      public double getAttackReachSqr(EntityLivingBase entity) {
         return (double)(4.0F + entity.width);
      }
   }

   public enum EnumMoveType {
      NONE(0.0F, 0.0F, 30, 1),
      HOP(0.8F, 0.2F, 20, 10),
      STEP(1.0F, 0.45F, 14, 14),
      SPRINT(1.75F, 0.4F, 1, 8),
      ATTACK(2.0F, 0.7F, 7, 8);

   private final float speed;
      private final float speedFactor;
      private final int duration;
      private final int jumpDuration;
   public int getDuration() {
         return this.duration;
      }

      public float getSpeedFactor() {
         return this.speedFactor;
      }

      public int getJumpDuration() {
         return this.jumpDuration;
      }

      private EnumMoveType(float f, float f2, int n, int n2) {
         this.speed = f;
         this.speedFactor = f2;
         this.duration = n;
         this.jumpDuration = n2;
      }

      public float getSpeed() {
         return this.speed;
      }
   }

   public static class AIAvoidEntity extends EntityAIAvoidEntity {
      private EntityRabbit entityInstance;

      @Override
      public void updateTask() {
         super.updateTask();
      }

      public AIAvoidEntity(EntityRabbit entityRabbit, Class clazz, float f, double d, double d2) {
         super(entityRabbit, clazz, f, d, d2);
         this.entityInstance = entityRabbit;
      }
   }

   public static class RabbitTypeData implements IEntityLivingData {
      public int typeData;

      public RabbitTypeData(int n) {
         this.typeData = n;
      }
   }

   public static class RabbitJumpHelper extends EntityJumpHelper {
      private EntityRabbit theEntity;
      private boolean canJump;
      public final EntityRabbit parentRabbit;

      public boolean getIsJumping() {
         return this.isJumping;
      }

      @Override
      public void doJump() {
         if (this.isJumping) {
            this.theEntity.doMovementAction(EntityRabbit.EnumMoveType.STEP);
            this.isJumping = false;
         }
      }

      public boolean canJump() {
         return this.canJump;
      }

      public void setCanJump(boolean flag) {
         this.canJump = flag;
      }

      public RabbitJumpHelper(EntityRabbit entityRabbit, EntityRabbit entityRabbit2) {
         super(entityRabbit2);
         this.parentRabbit = entityRabbit;
         this.canJump = false;
         this.theEntity = entityRabbit2;
      }
   }

   public static class RabbitMoveHelper extends EntityMoveHelper {
      private EntityRabbit theEntity;

      @Override
      public void onUpdateMoveHelper() {
         if (this.theEntity.onGround && !this.theEntity.isRabbitJumping()) {
            this.theEntity.setMovementSpeed(0.0);
         }

         super.onUpdateMoveHelper();
      }

      public RabbitMoveHelper(EntityRabbit entityRabbit) {
         super(entityRabbit);
         this.theEntity = entityRabbit;
      }
   }
}
