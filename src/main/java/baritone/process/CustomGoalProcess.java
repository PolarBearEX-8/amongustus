package baritone.process;

import baritone.Baritone;
import baritone.api.pathing.goals.Goal;
import baritone.api.process.ICustomGoalProcess;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.utils.BaritoneProcessHelper;

public final class CustomGoalProcess extends BaritoneProcessHelper implements ICustomGoalProcess {
   private Goal goal;
   private Goal mostRecentGoal;
   private CustomGoalProcess.State state;

   @Override
   public Goal mostRecentGoal() {
      return this.mostRecentGoal;
   }

   @Override
   public void path() {
      this.state = CustomGoalProcess.State.PATH_REQUESTED;
   }

   @Override
   public void setGoal(Goal goal2) {
      this.goal = goal2;
      this.mostRecentGoal = goal2;
      if (this.state == CustomGoalProcess.State.NONE) {
         this.state = CustomGoalProcess.State.GOAL_SET;
      }

      if (this.state == CustomGoalProcess.State.EXECUTING) {
         this.state = CustomGoalProcess.State.PATH_REQUESTED;
      }
   }

   public CustomGoalProcess(Baritone baritone2) {
      super(baritone2);
   }

   @Override
   public Goal getGoal() {
      return this.goal;
   }

   @Override
   public String displayName0() {
      return "Custom Goal " + this.goal;
   }

   @Override
   public PathingCommand onTick(boolean flag, boolean flag2) {
      switch (this.state) {
         case GOAL_SET:
            return new PathingCommand(this.goal, PathingCommandType.CANCEL_AND_SET_GOAL);
         case PATH_REQUESTED:
            PathingCommand pathingCommand = new PathingCommand(this.goal, PathingCommandType.FORCE_REVALIDATE_GOAL_AND_PATH);
            this.state = CustomGoalProcess.State.EXECUTING;
            return pathingCommand;
         case EXECUTING:
            if (flag) {
               this.onLostControl();
               return new PathingCommand(this.goal, PathingCommandType.CANCEL_AND_SET_GOAL);
            } else {
               if (this.goal == null || this.goal.isInGoal(this.ctx.playerFeet()) && this.goal.isInGoal(this.baritone.getPathingBehavior2().pathStart())) {
                  this.onLostControl();
                  if ((Boolean)Baritone.settings().disconnectOnArrival.value) {
                     this.ctx.world().sendQuittingDisconnectingPacket();
                  }

                  if ((Boolean)Baritone.settings().notificationOnPathComplete.value) {
                     this.logNotification("Pathing complete", false);
                  }

                  return new PathingCommand(this.goal, PathingCommandType.CANCEL_AND_SET_GOAL);
               }

               return new PathingCommand(this.goal, PathingCommandType.SET_GOAL_AND_PATH);
            }
         default:
            throw new IllegalStateException();
      }
   }

   @Override
   public boolean isActive() {
      return this.state != CustomGoalProcess.State.NONE;
   }

   @Override
   public void onLostControl() {
      this.state = CustomGoalProcess.State.NONE;
      this.goal = null;
   }

public enum State {
      NONE,
      GOAL_SET,
      PATH_REQUESTED,
      EXECUTING;
   }
}
