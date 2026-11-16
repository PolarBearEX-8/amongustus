package cc.baritone.command;

import cc.baritone.utils.DRNHandler;
import cc.baritone.utils.DynamicListener;
import cc.baritone.utils.ExceptionAdapter;
import java.util.List;

public class CommandNotFoundSManager extends ExceptionAdapter {
   public final String labelText;

   @Override
   public void applyDynamicListener(DynamicListener dynamicListener, List list) {
      DRNHandler.dRNHandler.applyText(this.getMessage());
   }

   public CommandNotFoundSManager(String text) {
      super(String.format("Command not found: %s", text));
      this.labelText = text;
   }
}
