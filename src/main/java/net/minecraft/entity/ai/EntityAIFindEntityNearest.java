package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAIFindEntityNearest extends EntityAIBase {
   private static final Logger LOGGER = LogManager.getLogger();
   private EntityLiving mob;
   private final Predicate predicate;
   private final EntityAINearestAttackableTarget.Sorter sorter;
   private EntityLivingBase target;
   private Class classToCheck;

   public double getFollowRange() {
      IAttributeInstance entityAttribute = this.mob.getEntityAttribute(SharedMonsterAttributes.followRange);
      return entityAttribute == null ? 16.0 : entityAttribute.getAttributeValue();
   }

   public EntityAIFindEntityNearest(EntityLiving entityLiving, Class clazz) {
      this.mob = entityLiving;
      this.classToCheck = clazz;
      if (entityLiving instanceof EntityCreature) {
         LOGGER.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
      }

      this.predicate = new EntityAIFindEntityNearest.FilterPredicate(this);
      this.sorter = new EntityAINearestAttackableTarget.Sorter(entityLiving);
   }

   @Override
   public boolean shouldExecute() {
      double followRange = this.getFollowRange();
      List entitiesWithinAABB = this.mob.entityWorld.getEntitiesWithinAABB(this.classToCheck, this.mob.getEntityBoundingBox().expand(followRange, 4.0, followRange), this.predicate);
      Collections.sort(entitiesWithinAABB, this.sorter);
      if (entitiesWithinAABB.isEmpty()) {
         return false;
      } else {
         this.target = (EntityLivingBase)entitiesWithinAABB.get(0);
         return true;
      }
   }

   @Override
   public boolean continueExecuting() {
      EntityLivingBase attackTarget = this.mob.getAttackTarget();
      if (attackTarget == null) {
         return false;
      } else if (!attackTarget.isEntityAlive()) {
         return false;
      } else {
         double followRange = this.getFollowRange();
         return this.mob.getDistanceSqToEntity(attackTarget) > followRange * followRange
            ? false
            : !(attackTarget instanceof EntityPlayerMP) || !((EntityPlayerMP)attackTarget).theItemInWorldManager.isCreative();
      }
   }

   public static EntityLiving accessMob(EntityAIFindEntityNearest nearest) {
      return nearest.mob;
   }

   @Override
   public void resetTask() {
      this.mob.setAttackTarget((EntityLivingBase)null);
      super.startExecuting();
   }

   @Override
   public void startExecuting() {
      this.mob.setAttackTarget(this.target);
      super.startExecuting();
   }


   public static class FilterPredicate implements Predicate {
      public final EntityAIFindEntityNearest findEntityTask;

      public FilterPredicate(EntityAIFindEntityNearest nearest) {
         this.findEntityTask = nearest;
      }

      public boolean apply(EntityLivingBase entity) {
         double followRange = this.findEntityTask.getFollowRange();
         if (entity.isSneaking()) {
            followRange *= 0.8F;
         }

         return entity.isInvisible()
            ? false
            : (
               (double)entity.getDistanceToEntity(EntityAIFindEntityNearest.accessMob(this.findEntityTask)) > followRange
                  ? false
                  : EntityAITarget.isSuitableTarget(EntityAIFindEntityNearest.accessMob(this.findEntityTask), entity, false, true)
            );
      }

      public boolean apply(Object obj) {
         return this.apply((EntityLivingBase)obj);
      }
   }
}
