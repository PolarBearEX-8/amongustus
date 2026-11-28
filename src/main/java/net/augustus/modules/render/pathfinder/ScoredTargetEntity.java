package net.augustus.modules.render.pathfinder;

import net.augustus.modules.render.Pathfinder;
import net.minecraft.entity.Entity;

public class ScoredTargetEntity implements Comparable {
   private Entity entity;
   private double distanceSq;
   public final Pathfinder pathfinder;

   public ScoredTargetEntity(Pathfinder pathfinder2, Entity entity2, double d) {
      this.pathfinder = pathfinder2;
      this.entity = entity2;
      this.distanceSq = d;
   }

   public int compareByDistance(ScoredTargetEntity scoredTargetEntity) {
      return -Double.compare(this.distanceSq, scoredTargetEntity.distanceSq);
   }

   @Override
   public int compareTo(Object other) {
      return this.compareByDistance((ScoredTargetEntity)other);
   }

   public static Entity getEntity(ScoredTargetEntity scoredTargetEntity) {
      return scoredTargetEntity.entity;
   }
}
