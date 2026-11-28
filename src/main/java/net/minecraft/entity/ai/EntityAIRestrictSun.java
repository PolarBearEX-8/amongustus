package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAIRestrictSun extends EntityAIBase {
   private EntityCreature theEntity;

   @Override
   public boolean shouldExecute() {
      return this.theEntity.entityWorld.isDaytime();
   }

   public EntityAIRestrictSun(EntityCreature entityCreature) {
      this.theEntity = entityCreature;
   }

   @Override
   public void resetTask() {
      ((PathNavigateGround)this.theEntity.getNavigator()).setAvoidSun(false);
   }

   @Override
   public void startExecuting() {
      ((PathNavigateGround)this.theEntity.getNavigator()).setAvoidSun(true);
   }
}
