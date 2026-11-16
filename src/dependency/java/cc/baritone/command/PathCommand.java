package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.api.INACTIVEHandlerExtension;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.SettingsTxtManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PathCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("The path command tells Baritone to head towards the current goal.", "", "Usage:", "> path - Start the pathing.");
   }

   @Override
   public String getLabelText2() {
      return "Start heading towards the goal";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      INACTIVEHandlerExtension iNACTIVEHandlerExtension = this.activeListener.getINACTIVEHandlerExtension();
      depthFirstManager.applyIndexByIndex(0);
      SettingsTxtManager.getExtraListener().getSharedListener().computeCount(this.staticListener);
      iNACTIVEHandlerExtension.updateState2();
      this.applyText("Now pathing");
   }

   public PathCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"path"});
   }
}
