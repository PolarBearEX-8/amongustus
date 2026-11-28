package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ClickCommand extends Command {

   public ClickCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"click"});
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList("Opens click dude", "", "Usage:", "> click");
   }

   @Override
   public String getShortDesc() {
      return "Open click";
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      this.baritone.openClick();
      this.logDirect2("aight dude");
   }
}
