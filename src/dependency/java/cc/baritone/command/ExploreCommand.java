package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.DiagonalEvaluator;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.StaticCustom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ExploreCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return depthFirstManager.sizeByIndex(2) ? depthFirstManager.resolveStream(StaticCustom.INSTANCE) : Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "Tell Baritone to explore randomly. If you used explorefilter before this, it will be applied.",
         "",
         "Usage:",
         "> explore - Explore from your current position.",
         "> explore <x> <z> - Explore from the specified X and Z position."
      );
   }

   @Override
   public String getLabelText2() {
      return "Explore things";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      if (depthFirstManager.isActive()) {
         depthFirstManager.applyIndex(2);
      } else {
         depthFirstManager.applyIndexByIndex(0);
      }

      DiagonalEvaluator diagonalEvaluator = depthFirstManager.isActive()
         ? (DiagonalEvaluator)depthFirstManager.resolveTarget(StaticCustom.INSTANCE, this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())
         : new DiagonalEvaluator(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS());
      this.activeListener.getInternalINACTIVEHandlerExten().applyIndex(diagonalEvaluator.getCount(), diagonalEvaluator.getCount2());
      this.applyText(String.format("Exploring from %s", diagonalEvaluator.toString()));
   }

   public ExploreCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"explore"});
   }
}
