package net.minecraft.entity.ai;

import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;

public class EntityAILookAtVillager extends EntityAIBase {
   private EntityIronGolem theGolem;
   private EntityVillager theVillager;
   private int lookTime;

   @Override
   public boolean continueExecuting() {
      return this.lookTime > 0;
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theGolem.entityWorld.isDaytime()) {
         return false;
      } else if (this.theGolem.getRNG().nextInt(8000) != 0) {
         return false;
      } else {
         this.theVillager = (EntityVillager)this.theGolem
            .entityWorld
            .findNearestEntityWithinAABB(EntityVillager.class, this.theGolem.getEntityBoundingBox().expand(6.0, 2.0, 6.0), this.theGolem);
         return this.theVillager != null;
      }
   }

   @Override
   public void updateTask() {
      this.theGolem.getLookHelper().setLookPositionWithEntity(this.theVillager, 30.0F, 30.0F);
      this.lookTime--;
   }

   @Override
   public void startExecuting() {
      this.lookTime = 400;
      this.theGolem.setHoldingRose(true);
   }

   public EntityAILookAtVillager(EntityIronGolem entityIronGolem) {
      this.theGolem = entityIronGolem;
      this.setMutexBits(3);
   }

   @Override
   public void resetTask() {
      this.theGolem.setHoldingRose(false);
      this.theVillager = null;
   }
}
