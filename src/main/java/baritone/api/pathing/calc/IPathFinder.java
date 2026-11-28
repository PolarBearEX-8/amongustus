package baritone.api.pathing.calc;

import baritone.api.pathing.goals.Goal;
import baritone.api.utils.PathCalculationResult;
import java.util.Optional;

public interface IPathFinder {
   PathCalculationResult calculate(long l, long l2);

   Optional pathToMostRecentNodeConsidered();

   Optional bestPathSoFar();

   boolean isFinished();

   Goal getGoal();
}
