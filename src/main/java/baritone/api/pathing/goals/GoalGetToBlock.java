package baritone.api.pathing.goals;

import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.SettingsUtil;
import baritone.api.utils.interfaces.IGoalRenderPos;
import net.minecraft.util.BlockPos;

public class GoalGetToBlock implements Goal, IGoalRenderPos {
   public final int x;
   public final int y;
   public final int z;

   @Override
   public double heuristic(int n, int n2, int n3) {
      int n4 = n - this.x;
      int n5 = n2 - this.y;
      int n6 = n3 - this.z;
      return GoalBlock.calculate((double)n4, n5 < 0 ? n5 + 1 : n5, (double)n6);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GoalGetToBlock goalGetToBlock = (GoalGetToBlock)obj;
         return this.x == goalGetToBlock.x && this.y == goalGetToBlock.y && this.z == goalGetToBlock.z;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return String.format(
         "GoalGetToBlock{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z)
      );
   }

   @Override
   public BlockPos getGoalPos() {
      return new BlockPos(this.x, this.y, this.z);
   }

   @Override
   public int hashCode() {
      return (int)BetterBlockPos.longHash(this.x, this.y, this.z) * -49639096;
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      int n4 = n - this.x;
      int n5 = n2 - this.y;
      int n6 = n3 - this.z;
      return Math.abs(n4) + Math.abs(n5 < 0 ? n5 + 1 : n5) + Math.abs(n6) <= 1;
   }

   public GoalGetToBlock(BlockPos pos) {
      this.x = pos.getX();
      this.y = pos.getY();
      this.z = pos.getZ();
   }
}
