package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.api.INACTIVEHandlerExtension;
import cc.baritone.utils.AStarDispatcher;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.DepthFirstCalibrator;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.HeuristicEstimator;
import cc.baritone.utils.SecondaryListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GoalCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      HeuristicEstimator heuristicEstimator = new HeuristicEstimator();
      if (depthFirstManager.isActiveAsFlag()) {
         heuristicEstimator.resolveHeuristicEstimatorByTextArray(new String[]{"reset", "clear", "none", "~"});
      } else if (depthFirstManager.sizeByIndex(3)) {
         while (depthFirstManager.size(2) && depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE) != null) {
            depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
            if (!depthFirstManager.size(2)) {
               heuristicEstimator.resolveHeuristicEstimatorByTextArray(new String[]{"~"});
            }
         }
      }

      return heuristicEstimator.resolveHeuristicEstimatorByText(depthFirstManager.getText()).getStream();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The goal command allows you to set or clear Baritone's goal.",
         "",
         "Wherever a coordinate is expected, you can use ~ just like in regular Minecraft commands. Or, you can just use regular numbers.",
         "",
         "Usage:",
         "> goal - Set the goal to your current position",
         "> goal <reset/clear/none> - Erase the goal",
         "> goal <y> - Set the goal to a Y level",
         "> goal <x> <z> - Set the goal to an X,Z position",
         "> goal <x> <y> <z> - Set the goal to an X,Y,Z position"
      );
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      INACTIVEHandlerExtension iNACTIVEHandlerExtension = this.activeListener.getINACTIVEHandlerExtension();
      if (depthFirstManager.isActive() && Arrays.asList("reset", "clear", "none").contains(depthFirstManager.getTextAsText())) {
         depthFirstManager.applyIndexByIndex(1);
         if (iNACTIVEHandlerExtension.getSecondaryListener() != null) {
            iNACTIVEHandlerExtension.applySecondaryListener(null);
            this.applyText("Cleared goal");
         } else {
            this.applyText("There was no goal to clear");
         }
      } else {
         depthFirstManager.applyIndexByIndex(3);
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
         SecondaryListener secondaryListener = (SecondaryListener)depthFirstManager.resolveTarget(AStarDispatcher.INSTANCE, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS);
         iNACTIVEHandlerExtension.applySecondaryListener(secondaryListener);
         this.applyText(String.format("Goal: %s", secondaryListener.toString()));
      }
   }

   @Override
   public String getLabelText2() {
      return "Set or clear the goal";
   }

   public GoalCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"goal"});
   }
}
