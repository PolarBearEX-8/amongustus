package baritone.utils.pathing;

import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.pathing.calc.IPath;
import baritone.api.pathing.goals.Goal;
import baritone.pathing.path.CutoffPath;
import baritone.utils.BlockStateInterface;
import net.minecraft.util.BlockPos;

public abstract class PathBase implements IPath {
   @Override
   public IPath staticCutoff(Goal goal) {
      return this.staticCutoff2(goal);
   }

   @Override
   public IPath cutoffAtLoadedChunks(Object obj) {
      return this.cutoffAtLoadedChunks2(obj);
   }

   public PathBase staticCutoff2(Goal goal) {
      int value = (Integer)BaritoneAPI.getSettings().pathCutoffMinimumLength.value;
      if (this.length() < value) {
         return this;
      } else if (goal != null && !goal.isInGoal(this.getDest())) {
         double value2 = (Double)BaritoneAPI.getSettings().pathCutoffFactor.value;
         int n = (int)((double)(this.length() - value) * value2) + value - 1;
         return new CutoffPath(this, n);
      } else {
         return this;
      }
   }

   public PathBase cutoffAtLoadedChunks2(Object obj) {
      if (!(Boolean)Baritone.settings().cutoffAtLoadBoundary.value) {
         return this;
      } else {
         BlockStateInterface blockStateInterface = (BlockStateInterface)obj;

         for (int i = 0; i < this.positions().size(); i++) {
            BlockPos pos = (BlockPos)this.positions().get(i);
            if (!blockStateInterface.worldContainsLoadedChunk(pos.getX(), pos.getZ())) {
               return new CutoffPath(this, i);
            }
         }

         return this;
      }
   }
}
