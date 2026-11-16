package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.AStarDispatcher;
import cc.baritone.utils.AStarSelector;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.DepthFirstCalibrator;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.PrimaryCustom;
import cc.baritone.utils.SecondaryListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GotoCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(1);
      return depthFirstManager.resolveStream(AStarSelector.INSTANCE);
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The goto command tells Baritone to head towards a given goal or block.",
         "",
         "Wherever a coordinate is expected, you can use ~ just like in regular Minecraft commands. Or, you can just use regular numbers.",
         "",
         "Usage:",
         "> goto <block> - Go to a block, wherever it is in the world",
         "> goto <y> - Go to a Y level",
         "> goto <x> <z> - Go to an X,Z position",
         "> goto <x> <y> <z> - Go to an X,Y,Z position"
      );
   }

   @Override
   public String getLabelText2() {
      return "Go to a coordinate or block";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      if (depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE) != null) {
         depthFirstManager.applyIndexByIndex(3);
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
         SecondaryListener secondaryListener = (SecondaryListener)depthFirstManager.resolveTarget(AStarDispatcher.INSTANCE, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS);
         this.applyText(String.format("Going to: %s", secondaryListener.toString()));
         this.activeListener.getINACTIVEHandlerExtension().applySecondaryListenerBySecondaryListene(secondaryListener);
      } else {
         depthFirstManager.applyIndexByIndex(1);
         PrimaryCustom primaryCustom = (PrimaryCustom)depthFirstManager.resolveTarget2(AStarSelector.INSTANCE);
         this.activeListener.getAuxiliaryINACTIVEHandlerExte().applyPrimaryCustom(primaryCustom);
      }
   }

   public GotoCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"goto"});
   }
}
