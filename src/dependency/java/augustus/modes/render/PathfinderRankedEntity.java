package augustus.modes.render;

import augustus.modules.render.Pathfinder;
import net.minecraft.entity.Entity;

public class PathfinderRankedEntity implements Comparable {
   public Entity entity;
   public double value;
   public final Pathfinder pathfinder;

   public PathfinderRankedEntity(Pathfinder pathfinder2, Entity entity2, double offset) {
      this.pathfinder = pathfinder2;
      this.entity = entity2;
      this.value = offset;
   }

   public int compare(PathfinderRankedEntity pathfinderRankedEntity) {
      return -Double.compare(this.value, pathfinderRankedEntity.value);
   }

   @Override
   public int compareTo(Object entry) {
      PathfinderRankedEntity pathfinderRankedEntity = (PathfinderRankedEntity)entry;
      return this.compare(pathfinderRankedEntity);
   }

   public static Entity getEntity(PathfinderRankedEntity pathfinderRankedEntity) {
      return pathfinderRankedEntity.entity;
   }
}
