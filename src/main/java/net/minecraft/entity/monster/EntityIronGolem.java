package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIDefendVillage;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookAtVillager;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityIronGolem extends EntityGolem {
   public Village villageObj;
   private int homeCheckTimer;
   private int attackTimer;
   private int holdRoseTick;

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.setPlayerCreated(tag.getBoolean("PlayerCreated"));
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 4) {
         this.attackTimer = 10;
         this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
      } else if (b == 11) {
         this.holdRoseTick = 400;
      } else {
         super.handleStatusUpdate(b);
      }
   }

   public void setHoldingRose(boolean flag) {
      this.holdRoseTick = flag ? 400 : 0;
      this.entityWorld.setEntityState(this, (byte)11);
   }

   @Override
   public String getHurtSound() {
      return "mob.irongolem.hit";
   }

   @Override
   public void collideWithEntity(Entity entity) {
      if (entity instanceof IMob && !(entity instanceof EntityCreeper) && this.getRNG().nextInt(20) == 0) {
         this.setAttackTarget((EntityLivingBase)entity);
      }

      super.collideWithEntity(entity);
   }

   public boolean isPlayerCreated() {
      return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
   }

   public EntityIronGolem(World world) {
      super(world);
      this.setSize(1.4F, 2.9F);
      ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
      this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0, true));
      this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9, 32.0F));
      this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6, true));
      this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.tasks.addTask(5, new EntityAILookAtVillager(this));
      this.tasks.addTask(6, new EntityAIWander(this, 0.6));
      this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIDefendVillage(this));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.targetTasks.addTask(3, new EntityIronGolem.AINearestAttackableTargetNonCreeper(this, EntityLiving.class, 10, false, true, IMob.VISIBLE_MOB_SELECTOR));
   }

   public Village getVillage() {
      return this.villageObj;
   }

   @Override
   public int decreaseAirSupply(int n) {
      return n;
   }

   public int getAttackTimer() {
      return this.attackTimer;
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, (byte)0);
   }

   @Override
   public void updateAITasks() {
      if (--this.homeCheckTimer <= 0) {
         this.homeCheckTimer = 70 + this.rand.nextInt(50);
         this.villageObj = this.entityWorld.getVillageCollection().getNearestVillage(new BlockPos(this), 32);
         if (this.villageObj == null) {
            this.detachHome();
         } else {
            this.setHomePosAndDistance(this.villageObj.getCenter(), (int)((float)this.villageObj.getVillageRadius() * 0.6F));
         }
      }

      super.updateAITasks();
   }

   @Override
   public void onDeath(DamageSource damageSource) {
      if (!this.isPlayerCreated() && this.attackingPlayer != null && this.villageObj != null) {
         this.villageObj.setReputationForPlayer(this.attackingPlayer.getName(), -5);
      }

      super.onDeath(damageSource);
   }

   public void setPlayerCreated(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(16);
      if (flag) {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte | 1));
      } else {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte & -2));
      }
   }

   public int getHoldRoseTick() {
      return this.holdRoseTick;
   }

   @Override
   public boolean canAttackClass(Class clazz) {
      return (!this.isPlayerCreated() || !EntityPlayer.class.isAssignableFrom(clazz)) && clazz != EntityCreeper.class && super.canAttackClass(clazz);
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.attackTimer > 0) {
         this.attackTimer--;
      }

      if (this.holdRoseTick > 0) {
         this.holdRoseTick--;
      }

      if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.5000003E-7F && this.rand.nextInt(5) == 0) {
         int n = MathHelper.floor_double(this.posX);
         int n2 = MathHelper.floor_double(this.posY - 0.2F);
         int n3 = MathHelper.floor_double(this.posZ);
         IBlockState blockState = this.entityWorld.getBlockState(new BlockPos(n, n2, n3));
         if (blockState.getBlock().getMaterial() != Material.air) {
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.BLOCK_CRACK,
                  this.posX + ((double)this.rand.nextFloat() - 0.5) * (double)this.width,
                  this.getEntityBoundingBox().minY + 0.1,
                  this.posZ + ((double)this.rand.nextFloat() - 0.5) * (double)this.width,
                  4.0 * ((double)this.rand.nextFloat() - 0.5),
                  0.5,
                  ((double)this.rand.nextFloat() - 0.5) * 4.0,
                  new int[]{Block.getStateId(blockState)}
               );
         }
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setBoolean("PlayerCreated", this.isPlayerCreated());
   }

   @Override
   public void playStepSound(BlockPos pos, Block block) {
      this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
   }

   @Override
   public String getDeathSound() {
      return "mob.irongolem.death";
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      int n2 = this.rand.nextInt(3);

      for (int i = 0; i < n2; i++) {
         this.dropItemWithOffset(Item.getItemFromBlock(Blocks.red_flower), 1, (float)BlockFlower.EnumFlowerType.POPPY.getMeta());
      }

      int n3 = 3 + this.rand.nextInt(3);

      for (int j = 0; j < n3; j++) {
         this.dropItem(Items.iron_ingot, 1);
      }
   }

   @Override
   public boolean attackEntityAsMob(Entity entity) {
      this.attackTimer = 10;
      this.entityWorld.setEntityState(this, (byte)4);
      boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));
      if (flag) {
         entity.motionY += 0.4F;
         this.applyEnchantments(this, entity);
      }

      this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
      return flag;
   }


   public static class AINearestAttackableTargetNonCreeper extends EntityAINearestAttackableTarget {
      public AINearestAttackableTargetNonCreeper(EntityCreature entityCreature, Class clazz, int n, boolean flag, boolean flag2, Predicate predicate) {
         super(entityCreature, clazz, n, flag, flag2, predicate);
         this.targetEntitySelector = new EntityIronGolem.AINearestAttackableTargetNonCreeper.FilterPredicate(this, predicate, entityCreature);
      }

      public static boolean invokeIsSuitableTarget(AINearestAttackableTargetNonCreeper creeper, EntityLivingBase entity, boolean flag) {
         return creeper.isSuitableTarget(entity, flag);
      }

      public static double invokeGetTargetDistance(AINearestAttackableTargetNonCreeper creeper) {
         return creeper.getTargetDistance();
      }


      public static class FilterPredicate implements Predicate {
         public final Predicate targetSelector;
         public final EntityCreature creature;
         public final EntityIronGolem.AINearestAttackableTargetNonCreeper nearestTargetTask;

         public boolean apply(Object obj) {
            return this.apply((EntityLivingBase)obj);
         }

         public FilterPredicate(EntityIronGolem.AINearestAttackableTargetNonCreeper creeper, Predicate predicate, EntityCreature entityCreature) {
            this.nearestTargetTask = creeper;
            this.targetSelector = predicate;
            this.creature = entityCreature;
         }

         public boolean apply(EntityLivingBase entity) {
            if (this.targetSelector != null && !this.targetSelector.apply(entity)) {
               return false;
            } else if (entity instanceof EntityCreeper) {
               return false;
            } else {
               if (entity instanceof EntityPlayer) {
                  double d = EntityIronGolem.AINearestAttackableTargetNonCreeper.invokeGetTargetDistance(this.nearestTargetTask);
                  if (entity.isSneaking()) {
                     d *= 0.8F;
                  }

                  if (entity.isInvisible()) {
                     float armorVisibility = ((EntityPlayer)entity).getArmorVisibility();
                     if (armorVisibility < 0.1F) {
                        armorVisibility = 0.1F;
                     }

                     d *= (double)(0.7F * armorVisibility);
                  }

                  if ((double)entity.getDistanceToEntity(this.creature) > d) {
                     return false;
                  }
               }

               return EntityIronGolem.AINearestAttackableTargetNonCreeper.invokeIsSuitableTarget(this.nearestTargetTask, entity, false);
            }
         }
      }
   }
}
