package net.minecraft.entity.monster;

import java.util.Random;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class EntitySilverfish extends EntityMob {
   private EntitySilverfish.AISummonSilverfish summonSilverfish;

   @Override
   public void playStepSound(BlockPos pos, Block block) {
      this.playSound("mob.silverfish.step", 0.15F, 1.0F);
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   @Override
   public Item getDropItem() {
      return null;
   }

   @Override
   public float getBlockPathWeight(BlockPos pos) {
      return this.entityWorld.getBlockState(pos.down()).getBlock() == Blocks.stone ? 10.0F : super.getBlockPathWeight(pos);
   }

   @Override
   public boolean isValidLightLevel() {
      return true;
   }

   public EntitySilverfish(World world) {
      super(world);
      this.setSize(0.4F, 0.3F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(3, this.summonSilverfish = new EntitySilverfish.AISummonSilverfish(this));
      this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0, false));
      this.tasks.addTask(5, new EntitySilverfish.AIHideInStone(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
   }

   @Override
   public boolean getCanSpawnHere() {
      if (super.getCanSpawnHere()) {
         return this.entityWorld.getClosestPlayerToEntity(this, 5.0) == null;
      } else {
         return false;
      }
   }

   @Override
   public String getDeathSound() {
      return "mob.silverfish.kill";
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.ARTHROPOD;
   }

   @Override
   public String getHurtSound() {
      return "mob.silverfish.hit";
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         if (damageSource instanceof EntityDamageSource || damageSource == DamageSource.magic) {
            this.summonSilverfish.notifyHurt();
         }

         return super.attackEntityFrom(damageSource, f);
      }
   }

   @Override
   public String getLivingSound() {
      return "mob.silverfish.say";
   }

   @Override
   public double getYOffset() {
      return 0.2;
   }

   @Override
   public float getEyeHeight() {
      return 0.1F;
   }

   @Override
   public void onUpdate() {
      this.renderYawOffset = this.rotationYaw;
      super.onUpdate();
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
   }


   public static class AIHideInStone extends EntityAIWander {
      private final EntitySilverfish silverfish;
      private EnumFacing facing;
      private boolean doMerge;

      @Override
      public void startExecuting() {
         if (!this.doMerge) {
            super.startExecuting();
         } else {
            World entityWorld = this.silverfish.entityWorld;
            BlockPos pos = new BlockPos(this.silverfish.posX, this.silverfish.posY + 0.5, this.silverfish.posZ).offset(this.facing);
            IBlockState blockState = entityWorld.getBlockState(pos);
            if (BlockSilverfish.canContainSilverfish(blockState)) {
               entityWorld.setBlockState(
                  pos, Blocks.monster_egg.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.forModelBlock(blockState)), 3
               );
               this.silverfish.spawnExplosionParticle();
               this.silverfish.setDead();
            }
         }
      }

      @Override
      public boolean continueExecuting() {
         return this.doMerge ? false : super.continueExecuting();
      }

      @Override
      public boolean shouldExecute() {
         if (this.silverfish.getAttackTarget() != null) {
            return false;
         } else if (!this.silverfish.getNavigator().noPath()) {
            return false;
         } else {
            Random rng = this.silverfish.getRNG();
            if (rng.nextInt(10) == 0) {
               this.facing = EnumFacing.random(rng);
               BlockPos pos = new BlockPos(this.silverfish.posX, this.silverfish.posY + 0.5, this.silverfish.posZ).offset(this.facing);
               if (BlockSilverfish.canContainSilverfish(this.silverfish.entityWorld.getBlockState(pos))) {
                  this.doMerge = true;
                  return true;
               }
            }

            this.doMerge = false;
            return super.shouldExecute();
         }
      }

      public AIHideInStone(EntitySilverfish entitySilverfish) {
         super(entitySilverfish, 1.0, 10);
         this.silverfish = entitySilverfish;
         this.setMutexBits(1);
      }
   }

   public static class AISummonSilverfish extends EntityAIBase {
      private EntitySilverfish silverfish;
      private int lookForFriends;

      @Override
      public void updateTask() {
         this.lookForFriends--;
         if (this.lookForFriends <= 0) {
            World entityWorld = this.silverfish.entityWorld;
            Random rng = this.silverfish.getRNG();
            BlockPos pos = new BlockPos(this.silverfish);

            for (int i = 0; i <= 5 && i >= -5; i = i <= 0 ? 1 - i : 0 - i) {
               for (int j = 0; j <= 10 && j >= -10; j = j <= 0 ? 1 - j : 0 - j) {
                  for (int k = 0; k <= 10 && k >= -10; k = k <= 0 ? 1 - k : 0 - k) {
                     BlockPos pos2 = pos.add(j, i, k);
                     IBlockState blockState = entityWorld.getBlockState(pos2);
                     if (blockState.getBlock() == Blocks.monster_egg) {
                        if (entityWorld.getGameRules().getBoolean("mobGriefing")) {
                           entityWorld.destroyBlock(pos2, true);
                        } else {
                           entityWorld.setBlockState(pos2, ((BlockSilverfish.EnumType)blockState.getValue(BlockSilverfish.VARIANT)).getModelBlock(), 3);
                        }

                        if (rng.nextBoolean()) {
                           return;
                        }
                     }
                  }
               }
            }
         }
      }

      public void notifyHurt() {
         if (this.lookForFriends == 0) {
            this.lookForFriends = 20;
         }
      }

      @Override
      public boolean shouldExecute() {
         return this.lookForFriends > 0;
      }

      public AISummonSilverfish(EntitySilverfish entitySilverfish) {
         this.silverfish = entitySilverfish;
      }
   }
}
