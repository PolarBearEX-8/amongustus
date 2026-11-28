package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAITargetNonTamed extends EntityAINearestAttackableTarget {
   private EntityTameable theTameable;

   @Override
   public boolean shouldExecute() {
      return !this.theTameable.isTamed() && super.shouldExecute();
   }

   public EntityAITargetNonTamed(EntityTameable entityTameable, Class clazz, boolean flag, Predicate predicate) {
      super(entityTameable, clazz, 10, flag, false, predicate);
      this.theTameable = entityTameable;
   }
}
