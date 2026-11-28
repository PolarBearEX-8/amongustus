package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIHurtByTarget extends EntityAITarget {
   private boolean entityCallsForHelp;
   private int revengeTimerOld;
   private final Class[] targetClasses;

   public void setEntityAttackTarget(EntityCreature entityCreature, EntityLivingBase entity) {
      entityCreature.setAttackTarget(entity);
   }

   @Override
   public boolean shouldExecute() {
      return this.taskOwner.getRevengeTimer() != this.revengeTimerOld && this.isSuitableTarget(this.taskOwner.getAITarget(), false);
   }

   public EntityAIHurtByTarget(EntityCreature entityCreature, boolean flag, Class[] clazzs) {
      super(entityCreature, false);
      this.entityCallsForHelp = flag;
      this.targetClasses = clazzs;
      this.setMutexBits(1);
   }

   @Override
   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
      this.revengeTimerOld = this.taskOwner.getRevengeTimer();
      if (this.entityCallsForHelp) {
         double targetDistance = this.getTargetDistance();

         for (EntityCreature entityCreature : (Iterable<EntityCreature>)(this.taskOwner
            .entityWorld
            .getEntitiesWithinAABB(
               this.taskOwner.getClass(),
               new AxisAlignedBB(
                     this.taskOwner.posX,
                     this.taskOwner.posY,
                     this.taskOwner.posZ,
                     this.taskOwner.posX + 1.0,
                     this.taskOwner.posY + 1.0,
                     this.taskOwner.posZ + 1.0
                  )
                  .expand(targetDistance, 10.0, targetDistance)
            ))) {
            if (this.taskOwner != entityCreature && entityCreature.getAttackTarget() == null && !entityCreature.isOnSameTeam(this.taskOwner.getAITarget())) {
               boolean flag = false;

               for (Class clazz : this.targetClasses) {
                  if (entityCreature.getClass() == clazz) {
                     flag = true;
                     break;
                  }
               }

               if (!flag) {
                  this.setEntityAttackTarget(entityCreature, this.taskOwner.getAITarget());
               }
            }
         }
      }

      super.startExecuting();
   }
}
