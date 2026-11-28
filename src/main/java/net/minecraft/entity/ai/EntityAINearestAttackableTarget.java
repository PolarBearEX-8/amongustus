package net.minecraft.entity.ai;

import java.util.Comparator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntitySelectors;

public class EntityAINearestAttackableTarget extends EntityAITarget {
   public final Class targetClass;
   private final int targetChance;
   public final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
   public Predicate targetEntitySelector;
   public EntityLivingBase targetEntity;

   @Override
   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.targetEntity);
      super.startExecuting();
   }

   public EntityAINearestAttackableTarget(EntityCreature entityCreature, Class clazz, int n, boolean flag, boolean flag2, Predicate predicate) {
      super(entityCreature, flag, flag2);
      this.targetClass = clazz;
      this.targetChance = n;
      this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(entityCreature);
      this.setMutexBits(1);
      this.targetEntitySelector = new EntityAINearestAttackableTarget.FilterPredicate(this, predicate);
   }

   public EntityAINearestAttackableTarget(EntityCreature entityCreature, Class clazz, boolean flag, boolean flag2) {
      this(entityCreature, clazz, 10, flag, flag2, (Predicate)null);
   }

   public EntityAINearestAttackableTarget(EntityCreature entityCreature, Class clazz, boolean flag) {
      this(entityCreature, clazz, flag, false);
   }

   @Override
   public boolean shouldExecute() {
      if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
         return false;
      } else {
         double targetDistance = this.getTargetDistance();
         List entitiesWithinAABB = this.taskOwner
            .entityWorld
            .getEntitiesWithinAABB(
               this.targetClass,
               this.taskOwner.getEntityBoundingBox().expand(targetDistance, 4.0, targetDistance),
               Predicates.and(this.targetEntitySelector, EntitySelectors.NOT_SPECTATING)
            );
         Collections.sort(entitiesWithinAABB, this.theNearestAttackableTargetSorter);
         if (entitiesWithinAABB.isEmpty()) {
            return false;
         } else {
            this.targetEntity = (EntityLivingBase)entitiesWithinAABB.get(0);
            return true;
         }
      }
   }


   public static class FilterPredicate implements Predicate {
      public final Predicate targetSelector;
      public final EntityAINearestAttackableTarget nearestTargetTask;

      public boolean apply(Object obj) {
         return this.apply((EntityLivingBase)obj);
      }

      public FilterPredicate(EntityAINearestAttackableTarget target, Predicate predicate) {
         this.nearestTargetTask = target;
         this.targetSelector = predicate;
      }

      public boolean apply(EntityLivingBase entity) {
         if (this.targetSelector != null && !this.targetSelector.apply(entity)) {
            return false;
         } else {
            if (entity instanceof EntityPlayer) {
               double targetDistance = this.nearestTargetTask.getTargetDistance();
               if (entity.isSneaking()) {
                  targetDistance *= 0.8F;
               }

               if (entity.isInvisible()) {
                  float armorVisibility = ((EntityPlayer)entity).getArmorVisibility();
                  if (armorVisibility < 0.1F) {
                     armorVisibility = 0.1F;
                  }

                  targetDistance *= (double)(0.7F * armorVisibility);
               }

               if ((double)entity.getDistanceToEntity(this.nearestTargetTask.taskOwner) > targetDistance) {
                  return false;
               }
            }

            return this.nearestTargetTask.isSuitableTarget(entity, false);
         }
      }
   }

   public static class Sorter implements Comparator {
      private final Entity theEntity;

      public Sorter(Entity entity) {
         this.theEntity = entity;
      }

      @Override
      public int compare(Object a, Object b) {
         return this.compare((Entity)a, (Entity)b);
      }

      public int compare(Entity a, Entity b) {
         double distanceSqToEntity = this.theEntity.getDistanceSqToEntity(a);
         double distanceSqToEntity2 = this.theEntity.getDistanceSqToEntity(b);
         return distanceSqToEntity < distanceSqToEntity2 ? -1 : (distanceSqToEntity > distanceSqToEntity2 ? 1 : 0);
      }
   }
}
