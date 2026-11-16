package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.AStarSelector;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.PrimaryCustom;
import cc.baritone.utils.SettingsTxtManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MineCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.resolveTargetByClass(Integer.class, Integer.valueOf(0));

      while (depthFirstManager.size(2)) {
         depthFirstManager.resolveTarget2(AStarSelector.INSTANCE);
      }

      return depthFirstManager.resolveStream(AStarSelector.INSTANCE);
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The mine command allows you to tell Baritone to search for and mine individual blocks.",
         "",
         "The specified blocks can be ores (which are commonly cached), or any other block.",
         "",
         "Also see the legitMine settings (see #set l legitMine).",
         "",
         "Usage:",
         "> mine diamond_ore - Mines all diamonds it can find.",
         "> mine redstone_ore lit_redstone_ore - Mines redstone ore.",
         "> mine log:0 - Mines only oak logs."
      );
   }

   @Override
   public String getLabelText2() {
      return "Mine some blocks";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      int count = (Integer)depthFirstManager.resolveTargetByClass(Integer.class, Integer.valueOf(0));
      depthFirstManager.applyIndex2(1);
      ArrayList list = new ArrayList();

      while (depthFirstManager.isActive()) {
         list.add(depthFirstManager.resolveTarget2(AStarSelector.INSTANCE));
      }

      SettingsTxtManager.getExtraListener().getSharedListener().computeCount(this.staticListener);
      this.applyText(String.format("Mining %s", list.toString()));
      this.activeListener.getExtendedINACTIVEHandlerExten().applyPrimaryCustomArray(count, (PrimaryCustom[]) list.toArray(new PrimaryCustom[0]));
   }

   public MineCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"mine"});
   }
}
