package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ThisCommandIsAnAliasForSManager extends ExtendedItemEntry {
   public final String labelText2;
   public final String labelText;

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return this.activeListener.getGenericListener().resolveStream(String.format("%s %s", this.labelText, depthFirstManager.getText3()));
   }

   @Override
   public List getList() {
      return Collections.singletonList(String.format("This command is an alias, for: %s ...", this.labelText));
   }

   @Override
   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) {
      this.activeListener.getGenericListener().matchesText(String.format("%s %s", this.labelText, depthFirstManager.getText3()));
   }

   public ThisCommandIsAnAliasForSManager(ActiveListener activeListener, List list, String text, String text2) {
      super(activeListener, (String[])list.toArray(new String[0]));
      this.labelText2 = text;
      this.labelText = text2;
   }

   public ThisCommandIsAnAliasForSManager(ActiveListener activeListener, String text, String text2, String text3) {
      super(activeListener, new String[]{text});
      this.labelText2 = text2;
      this.labelText = text3;
   }
}
