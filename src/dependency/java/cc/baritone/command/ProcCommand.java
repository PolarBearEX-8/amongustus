package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.api.INACTIVEHandler;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.InternalClientManager;
import cc.baritone.utils.Listener5Entry;
import cc.baritone.utils.LocalListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ProcCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The proc command provides miscellaneous information about the process currently controlling Baritone.",
         "",
         "You are not expected to understand this if you aren't familiar with how Baritone works.",
         "",
         "Usage:",
         "> proc - View process information, if present"
      );
   }

   @Override
   public String getLabelText2() {
      return "View process state information";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      LocalListener localListener = this.activeListener.getLocalListener();
      INACTIVEHandler iNACTIVEHandler = (INACTIVEHandler)localListener.ofNullableAsOptional().orElse(null);
      if (iNACTIVEHandler == null) {
         throw new InternalClientManager("No process in control");
      } else {
         this.applyText(
            String.format(
               "Class: %s\nPriority: %f\nTemporary: %b\nDisplay name: %s\nLast command: %s",
               iNACTIVEHandler.getClass().getTypeName(),
               iNACTIVEHandler.getValue(),
               iNACTIVEHandler.isActive(),
               iNACTIVEHandler.getTextAsText(),
               localListener.ofNullable().map(entry -> ((Listener5Entry)entry).toString()).orElse("None")
            )
         );
      }
   }

   public ProcCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"proc"});
   }
}
