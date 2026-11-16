package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.DepthFirstResolver;
import cc.baritone.utils.ExceptionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ComeCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The come command tells Baritone to head towards your camera.",
         "",
         "This can be useful in hacked clients where freecam doesn't move your player position.",
         "",
         "Usage:",
         "> come"
      );
   }

   @Override
   public String getLabelText2() {
      return "Start heading towards your camera";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      this.activeListener
         .getINACTIVEHandlerExtension()
         .applySecondaryListenerBySecondaryListene(new DepthFirstResolver(this.staticListener.getBetterBlockPosXSYSZSBlockPos()));
      this.applyText("Coming");
   }

   public ComeCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"come"});
   }
}
