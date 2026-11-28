package net.minecraft.entity.monster;

import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySpider extends EntityMob {

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.entityWorld.isRemote) {
         this.setBesideClimbableBlock(this.isCollidedHorizontally);
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.spider.say";
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.ARTHROPOD;
   }

   @Override
   public Item getDropItem() {
      return Items.string;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
   }

   @Override
   public boolean isPotionApplicable(PotionEffect potionEffect) {
      return potionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(potionEffect);
   }

   @Override
   public double getMountedYOffset() {
      return (double)(this.height * 0.5F);
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, (byte)0);
   }

   public void setBesideClimbableBlock(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(16);
      if (flag) {
         watchableObjectByte = (byte)(watchableObjectByte | 1);
      } else {
         watchableObjectByte = (byte)(watchableObjectByte & -2);
      }

      this.dataWatcher.updateObject(16, watchableObjectByte);
   }

   @Override
   public String getDeathSound() {
      return "mob.spider.death";
   }

   @Override
   public PathNavigate getNewNavigator(World world) {
      return new PathNavigateClimber(this, world);
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, IEntityLivingData entityLivingData) {
      entityLivingData = super.onInitialSpawn(difficultyInstance, entityLivingData);
      if (this.entityWorld.rand.nextInt(100) == 0) {
         EntitySkeleton entitySkeleton = new EntitySkeleton(this.entityWorld);
         entitySkeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         entitySkeleton.onInitialSpawn(difficultyInstance, (IEntityLivingData)null);
         this.entityWorld.spawnEntityInWorld(entitySkeleton);
         entitySkeleton.mountEntity(this);
      }

      if (entityLivingData == null) {
         entityLivingData = new EntitySpider.GroupData();
         if (this.entityWorld.getDifficulty() == EnumDifficulty.HARD && this.entityWorld.rand.nextFloat() < 0.1F * difficultyInstance.getClampedAdditionalDifficulty()) {
            ((EntitySpider.GroupData)entityLivingData).setRandomEffect(this.entityWorld.rand);
         }
      }

      if (entityLivingData instanceof EntitySpider.GroupData) {
         int potionEffectId = ((EntitySpider.GroupData)entityLivingData).potionEffectId;
         if (potionEffectId > 0 && Potion.potionTypes[potionEffectId] != null) {
            this.addPotionEffect(new PotionEffect(potionEffectId, Integer.MAX_VALUE));
         }
      }

      return entityLivingData;
   }

   @Override
   public boolean isOnLadder() {
      return this.isBesideClimbableBlock();
   }

   @Override
   public void setInWeb() {
   }

   public EntitySpider(World world) {
      super(world);
      this.setSize(1.4F, 0.9F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
      this.tasks.addTask(4, new EntitySpider.AISpiderAttack(this, EntityPlayer.class));
      this.tasks.addTask(4, new EntitySpider.AISpiderAttack(this, EntityIronGolem.class));
      this.tasks.addTask(5, new EntityAIWander(this, 0.8));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.targetTasks.addTask(2, new EntitySpider.AISpiderTarget(this, EntityPlayer.class));
      this.targetTasks.addTask(3, new EntitySpider.AISpiderTarget(this, EntityIronGolem.class));
   }

   public boolean isBesideClimbableBlock() {
      return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      super.dropFewItems(flag, n);
      if (flag && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + n) > 0)) {
         this.dropItem(Items.spider_eye, 1);
      }
   }

   @Override
   public float getEyeHeight() {
      return 0.65F;
   }

   @Override
   public void playStepSound(BlockPos pos, Block block) {
      this.playSound("mob.spider.step", 0.15F, 1.0F);
   }

   @Override
   public String getLivingSound() {
      return "mob.spider.say";
   }


   public static class GroupData implements IEntityLivingData {
      public int potionEffectId;

      public void setRandomEffect(Random rand) {
         int n = rand.nextInt(5);
         if (n <= 1) {
            this.potionEffectId = Potion.moveSpeed.id;
         } else if (n <= 2) {
            this.potionEffectId = Potion.damageBoost.id;
         } else if (n <= 3) {
            this.potionEffectId = Potion.regeneration.id;
         } else if (n <= 4) {
            this.potionEffectId = Potion.invisibility.id;
         }
      }
   }

   public static class AISpiderAttack extends EntityAIAttackOnCollide {
      @Override
      public boolean continueExecuting() {
         if (this.attacker.getBrightness(1.0F) >= 0.5F && this.attacker.getRNG().nextInt(100) == 0) {
            this.attacker.setAttackTarget((EntityLivingBase)null);
            return false;
         } else {
            return super.continueExecuting();
         }
      }

      public AISpiderAttack(EntitySpider entitySpider, Class clazz) {
         super(entitySpider, clazz, 1.0, true);
      }

      @Override
      public double getAttackReachSqr(EntityLivingBase entity) {
         return (double)(4.0F + entity.width);
      }
   }

   public static class AISpiderTarget extends EntityAINearestAttackableTarget {
      @Override
      public boolean shouldExecute() {
         return this.taskOwner.getBrightness(1.0F) >= 0.5F ? false : super.shouldExecute();
      }

      public AISpiderTarget(EntitySpider entitySpider, Class clazz) {
         super(entitySpider, clazz, true);
      }
   }
}
