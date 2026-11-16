package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.SettingsTxtManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RescanCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("Repack chunks around you. This basically re-caches them.", "", "Usage:", "> repack - Repack chunks.");
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      this.applyText(
         String.format("Queued %d chunks for repacking", SettingsTxtManager.getExtraListener().getSharedListener().computeCount(this.staticListener))
      );
   }

   @Override
   public String getLabelText2() {
      return "Re-cache chunks";
   }

   public RescanCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"repack", "rescan"});
   }
}
