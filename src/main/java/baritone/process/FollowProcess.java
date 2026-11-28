package baritone.process;

import baritone.Baritone;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalComposite;
import baritone.api.pathing.goals.GoalNear;
import baritone.api.pathing.goals.GoalXZ;
import baritone.api.process.IFollowProcess;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.utils.BaritoneProcessHelper;
import java.util.Collection;
import java.util.List;
import net.minecraft.entity.Entity;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.util.BlockPos;

public final class FollowProcess extends BaritoneProcessHelper implements IFollowProcess {
   private Predicate filter;
   private List cache;

   @Override
   public Predicate currentFilter() {
      return this.filter;
   }

   @Override
   public void onLostControl() {
      this.filter = null;
      this.cache = null;
   }

   public FollowProcess(Baritone baritone2) {
      super(baritone2);
   }

   @Override
   public boolean isActive() {
      if (this.filter == null) {
         return false;
      } else {
         this.scanWorld();
         return !this.cache.isEmpty();
      }
   }

   @Override
   public String displayName0() {
      return "Following " + this.cache;
   }

   @Override
   public List following() {
      return this.cache;
   }

   @Override
   public PathingCommand onTick(boolean flag, boolean flag2) {
      this.scanWorld();
      GoalComposite goalComposite = new GoalComposite((Goal[])
         this.cache
            .stream()
            .map(
               obj -> {
                  BlockPos pos;
                  if ((Double)Baritone.settings().followOffsetDistance.value == 0.0) {
                     pos = new BlockPos((Entity)obj);
                  } else {
                     GoalXZ goalXZ = GoalXZ.fromDirection(
                        ((Entity)obj).getPositionVector(), (Float)Baritone.settings().followOffsetDirection.value, (Double)Baritone.settings().followOffsetDistance.value
                     );
                     pos = new BlockPos((double)goalXZ.getX(), ((Entity)obj).posY, (double)goalXZ.getZ());
                  }

                  return new GoalNear(pos, (Integer)Baritone.settings().followRadius.value);
               }
            )
            .toArray(Goal[]::new)
      );
      return new PathingCommand(goalComposite, PathingCommandType.REVALIDATE_GOAL_AND_PATH);
   }

   @Override
   public void follow(Predicate predicate) {
      this.filter = predicate;
   }

   private void scanWorld() {
      this.cache = (List)Stream.of(this.ctx.world().loadedEntityList, this.ctx.world().playerEntities).flatMap(Collection::stream).filter(obj -> {
         if (obj == null) {
            return false;
         } else if (((Entity)obj).isDead) {
            return false;
         } else {
            return obj.equals(this.ctx.player()) ? false : this.ctx.world().loadedEntityList.contains(obj);
         }
      }).filter(this.filter).distinct().collect(Collectors.toList());
   }

}
