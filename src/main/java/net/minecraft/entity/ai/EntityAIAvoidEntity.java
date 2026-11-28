package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.Vec3;

public class EntityAIAvoidEntity extends EntityAIBase {
   private final Predicate canBeSeenSelector = new EntityAIAvoidEntity.FilterPredicate(this);
   public EntityCreature theEntity;
   private double farSpeed;
   private double nearSpeed;
   public Entity closestLivingEntity;
   private float avoidDistance;
   private PathEntity entityPathEntity;
   private PathNavigate entityPathNavigate;
   private Class classToAvoid;
   private Predicate avoidTargetSelector;

   @Override
   public void startExecuting() {
      this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
   }

   @Override
   public boolean shouldExecute() {
      List entitiesWithinAABB = this.theEntity
         .entityWorld
         .getEntitiesWithinAABB(
            this.classToAvoid,
            this.theEntity.getEntityBoundingBox().expand((double)this.avoidDistance, 3.0, (double)this.avoidDistance),
            Predicates.and(new Predicate[]{EntitySelectors.NOT_SPECTATING, this.canBeSeenSelector, this.avoidTargetSelector})
         );
      if (entitiesWithinAABB.isEmpty()) {
         return false;
      } else {
         this.closestLivingEntity = (Entity)entitiesWithinAABB.get(0);
         Vec3 vec = RandomPositionGenerator.findRandomTargetBlockAwayFrom(
            this.theEntity, 16, 7, new Vec3(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ)
         );
         if (vec == null) {
            return false;
         } else if (this.closestLivingEntity.getDistanceSq(vec.xCoord, vec.yCoord, vec.zCoord)
            < this.closestLivingEntity.getDistanceSqToEntity(this.theEntity)) {
            return false;
         } else {
            this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(vec.xCoord, vec.yCoord, vec.zCoord);
            return this.entityPathEntity == null ? false : this.entityPathEntity.isDestinationSame(vec);
         }
      }
   }

   @Override
   public void updateTask() {
      if (this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0) {
         this.theEntity.getNavigator().setSpeed(this.nearSpeed);
      } else {
         this.theEntity.getNavigator().setSpeed(this.farSpeed);
      }
   }

   public EntityAIAvoidEntity(EntityCreature entityCreature, Class clazz, Predicate predicate, float f, double d, double d2) {
      this.theEntity = entityCreature;
      this.classToAvoid = clazz;
      this.avoidTargetSelector = predicate;
      this.avoidDistance = f;
      this.farSpeed = d;
      this.nearSpeed = d2;
      this.entityPathNavigate = entityCreature.getNavigator();
      this.setMutexBits(1);
   }

   public EntityAIAvoidEntity(EntityCreature entityCreature, Class clazz, float f, double d, double d2) {
      this(entityCreature, clazz, Predicates.alwaysTrue(), f, d, d2);
   }

   @Override
   public boolean continueExecuting() {
      return !this.entityPathNavigate.noPath();
   }

   @Override
   public void resetTask() {
      this.closestLivingEntity = null;
   }


   public static class FilterPredicate implements Predicate {
      public final EntityAIAvoidEntity avoidTask;

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public FilterPredicate(EntityAIAvoidEntity entityAIAvoidEntity) {
         this.avoidTask = entityAIAvoidEntity;
      }

      public boolean apply(Entity entity) {
         return entity.isEntityAlive() && this.avoidTask.theEntity.getEntitySenses().canSee(entity);
      }
   }
}
