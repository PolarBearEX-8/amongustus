package net.augustus.modules.render.pathfinder;

import net.augustus.utils.pathfinding.TimedAStarPathFinder;
import net.augustus.modules.render.Pathfinder;
import net.minecraft.entity.Entity;

public class EntityPathJob {
   public TimedAStarPathFinder pathFinder;
   public Entity targetEntity;
   public final Pathfinder pathfinder;

   public EntityPathJob(Pathfinder pathfinder2, TimedAStarPathFinder timedAStarPathFinder, Entity entity) {
      this.pathfinder = pathfinder2;
      this.pathFinder = timedAStarPathFinder;
      this.targetEntity = entity;
   }
}
