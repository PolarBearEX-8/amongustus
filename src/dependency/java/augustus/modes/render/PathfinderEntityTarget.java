package augustus.modes.render;

import augustus.modules.render.Pathfinder;
import client.render.PathfinderRenderer;
import net.minecraft.entity.Entity;

public class PathfinderEntityTarget {
   public PathfinderRenderer pathfinderRenderer;
   public Entity entity;
   public final Pathfinder pathfinder;

   public PathfinderEntityTarget(Pathfinder pathfinder2, PathfinderRenderer pathfinderRenderer2, Entity entity2) {
      this.pathfinder = pathfinder2;
      this.pathfinderRenderer = pathfinderRenderer2;
      this.entity = entity2;
   }
}
