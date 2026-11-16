package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.HeuristicTracker;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class HighwayCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("The axis command sets a goal that tells Baritone to head towards the nearest axis. That is, X=0 or Z=0.", "", "Usage:", "> axis");
   }

   @Override
   public String getLabelText2() {
      return "Set a goal to the axes";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      HeuristicTracker heuristicTracker = new HeuristicTracker();
      this.activeListener.getINACTIVEHandlerExtension().applySecondaryListener(heuristicTracker);
      this.applyText(String.format("Goal: %s", heuristicTracker.toString()));
   }

   public HighwayCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"axis", "highway"});
   }
}
