package net.minecraft.entity.ai;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityAIMate extends EntityAIBase {
   private EntityAnimal theAnimal;
   public World theWorld;
   private EntityAnimal targetMate;
   public int spawnBabyDelay;
   public double moveSpeed;

   @Override
   public void updateTask() {
      this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
      this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
      this.spawnBabyDelay++;
      if (this.spawnBabyDelay >= 60 && this.theAnimal.getDistanceSqToEntity(this.targetMate) < 9.0) {
         this.spawnBaby();
      }
   }

   @Override
   public void resetTask() {
      this.targetMate = null;
      this.spawnBabyDelay = 0;
   }

   private void spawnBaby() {
      EntityAgeable entityAgeable = this.theAnimal.createAgeableChild(this.targetMate);
      if (entityAgeable != null) {
         EntityPlayer playerInLove = this.theAnimal.getPlayerInLove();
         if (playerInLove == null && this.targetMate.getPlayerInLove() != null) {
            playerInLove = this.targetMate.getPlayerInLove();
         }

         if (playerInLove != null) {
            playerInLove.triggerAchievement(StatList.animalsBredStat);
            if (this.theAnimal instanceof EntityCow) {
               playerInLove.triggerAchievement(AchievementList.breedCow);
            }
         }

         this.theAnimal.setGrowingAge(6000);
         this.targetMate.setGrowingAge(6000);
         this.theAnimal.resetInLove();
         this.targetMate.resetInLove();
         entityAgeable.setGrowingAge(-24000);
         entityAgeable.setLocationAndAngles(this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, 0.0F, 0.0F);
         this.theWorld.spawnEntityInWorld(entityAgeable);
         Random rng = this.theAnimal.getRNG();

         for (int i = 0; i < 7; i++) {
            double d = rng.nextGaussian() * 0.02;
            double d2 = rng.nextGaussian() * 0.02;
            double d3 = rng.nextGaussian() * 0.02;
            double d4 = rng.nextDouble() * (double)this.theAnimal.width * 2.0 - (double)this.theAnimal.width;
            double d5 = 0.5 + rng.nextDouble() * (double)this.theAnimal.height;
            double d6 = rng.nextDouble() * (double)this.theAnimal.width * 2.0 - (double)this.theAnimal.width;
            this.theWorld
               .spawnParticle(
                  EnumParticleTypes.HEART, this.theAnimal.posX + d4, this.theAnimal.posY + d5, this.theAnimal.posZ + d6, d, d2, d3, new int[0]
               );
         }

         if (this.theWorld.getGameRules().getBoolean("doMobLoot")) {
            this.theWorld
               .spawnEntityInWorld(new EntityXPOrb(this.theWorld, this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, rng.nextInt(7) + 1));
         }
      }
   }

   private EntityAnimal getNearbyMate() {
      float f = 8.0F;
      List entitiesWithinAABB = this.theWorld
         .getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal.getEntityBoundingBox().expand((double)f, (double)f, (double)f));
      double maxvalue = Double.MAX_VALUE;
      EntityAnimal entityAnimal = null;

      for (EntityAnimal entityAnimal2 : (Iterable<EntityAnimal>)(entitiesWithinAABB)) {
         if (this.theAnimal.canMateWith(entityAnimal2) && this.theAnimal.getDistanceSqToEntity(entityAnimal2) < maxvalue) {
            entityAnimal = entityAnimal2;
            maxvalue = this.theAnimal.getDistanceSqToEntity(entityAnimal2);
         }
      }

      return entityAnimal;
   }

   public EntityAIMate(EntityAnimal entityAnimal, double d) {
      this.theAnimal = entityAnimal;
      this.theWorld = entityAnimal.entityWorld;
      this.moveSpeed = d;
      this.setMutexBits(3);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theAnimal.isInLove()) {
         return false;
      } else {
         this.targetMate = this.getNearbyMate();
         return this.targetMate != null;
      }
   }

   @Override
   public boolean continueExecuting() {
      return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
   }
}
