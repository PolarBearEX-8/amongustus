package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RenderCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("The render command fixes glitched chunk rendering without having to reload all of them.", "", "Usage:", "> render");
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      int count = (this.staticListener.getMc().gameSettings.indexOffset16 + 1) * 16;
      int count2 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count9 - count;
      int count3 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count10 - count;
      int count4 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count9 + count;
      this.staticListener.getMc().renderGlobal.applyIndex(count2, 0, count3, count4, 255, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count10 + count);
      this.applyText("Done");
   }

   @Override
   public String getLabelText2() {
      return "Fix glitched chunks";
   }

   public RenderCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"render"});
   }
}
