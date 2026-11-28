package baritone.api.process;

import baritone.api.pathing.goals.Goal;

public interface ICustomGoalProcess extends IBaritoneProcess {
   void path();

   default void setGoalAndPath(Goal goal) {
      this.setGoal(goal);
      this.path();
   }

   Goal mostRecentGoal();

   void setGoal(Goal goal);

   Goal getGoal();
}
