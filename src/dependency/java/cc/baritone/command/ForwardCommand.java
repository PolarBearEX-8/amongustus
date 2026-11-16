package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.DiagonalEvaluator;
import cc.baritone.utils.ExceptionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ForwardCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "Creates a GoalXZ some amount of blocks in the direction you're currently looking",
         "",
         "Usage:",
         "> thisway <distance> - makes a GoalXZ distance blocks in front of you"
      );
   }

   @Override
   public String getLabelText2() {
      return "Travel in your current direction";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndex(1);
      DiagonalEvaluator diagonalEvaluator = DiagonalEvaluator.resolveDiagonalEvaluator(
         this.staticListener.getVec2(), this.staticListener.getEntityPlayerSP().scaleFactor12, (Double)depthFirstManager.resolveTarget3(Double.class)
      );
      this.activeListener.getINACTIVEHandlerExtension().applySecondaryListener(diagonalEvaluator);
      this.applyText(String.format("Goal: %s", diagonalEvaluator));
   }

   public ForwardCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"thisway", "forward"});
   }
}
