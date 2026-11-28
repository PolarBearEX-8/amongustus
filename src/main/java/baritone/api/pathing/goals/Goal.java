package baritone.api.pathing.goals;

import net.minecraft.util.BlockPos;

public interface Goal {
   default double heuristic(BlockPos pos) {
      return this.heuristic(pos.getX(), pos.getY(), pos.getZ());
   }

   default boolean isInGoal(BlockPos pos) {
      return this.isInGoal(pos.getX(), pos.getY(), pos.getZ());
   }

   default double heuristic() {
      return 0.0;
   }

   double heuristic(int n, int n2, int n3);

   boolean isInGoal(int n, int n2, int n3);
}
