package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.village.Village;

public class EntityAIDefendVillage extends EntityAITarget {
   public EntityIronGolem irongolem;
   public EntityLivingBase villageAgressorTarget;

   public EntityAIDefendVillage(EntityIronGolem entityIronGolem) {
      super(entityIronGolem, false, true);
      this.irongolem = entityIronGolem;
      this.setMutexBits(1);
   }

   @Override
   public void startExecuting() {
      this.irongolem.setAttackTarget(this.villageAgressorTarget);
      super.startExecuting();
   }

   @Override
   public boolean shouldExecute() {
      Village village = this.irongolem.getVillage();
      if (village == null) {
         return false;
      } else {
         this.villageAgressorTarget = village.findNearestVillageAggressor(this.irongolem);
         if (this.villageAgressorTarget instanceof EntityCreeper) {
            return false;
         } else if (!this.isSuitableTarget(this.villageAgressorTarget, false)) {
            if (this.taskOwner.getRNG().nextInt(20) == 0) {
               this.villageAgressorTarget = village.getNearestTargetPlayer(this.irongolem);
               return this.isSuitableTarget(this.villageAgressorTarget, false);
            } else {
               return false;
            }
         } else {
            return true;
         }
      }
   }
}
