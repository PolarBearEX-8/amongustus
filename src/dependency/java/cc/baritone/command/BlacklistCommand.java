package cc.baritone.command;

import cc.baritone.api.AuxiliaryINACTIVEHandlerExtension;
import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.InternalClientManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BlacklistCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "While going to a block this command blacklists the closest block so that Baritone won't attempt to get to it.", "", "Usage:", "> blacklist"
      );
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      AuxiliaryINACTIVEHandlerExtension auxiliaryINACTIVEHandlerExte = this.activeListener.getAuxiliaryINACTIVEHandlerExte();
      if (!auxiliaryINACTIVEHandlerExte.isEnabled()) {
         throw new InternalClientManager("GetToBlockProcess is not currently active");
      } else if (auxiliaryINACTIVEHandlerExte.isActiveAsFlag()) {
         this.applyText("Blacklisted closest instances");
      } else {
         throw new InternalClientManager("No known locations, unable to blacklist");
      }
   }

   @Override
   public String getLabelText2() {
      return "Blacklist closest block";
   }

   public BlacklistCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"blacklist"});
   }
}
