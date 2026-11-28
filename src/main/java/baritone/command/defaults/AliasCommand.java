package baritone.command.defaults;

import baritone.api.command.argument.IArgConsumer;
import baritone.api.IBaritone;
import baritone.api.command.Command;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class AliasCommand extends Command {
   private final String shortDesc;
   public final String target;

   @Override
   public String getShortDesc() {
      return this.shortDesc;
   }

   public AliasCommand(IBaritone baritone2, List list, String str, String str2) {
      super(baritone2, (String[])list.toArray(new String[0]));
      this.shortDesc = str;
      this.target = str2;
   }

   public AliasCommand(IBaritone baritone2, String str, String str2, String str3) {
      super(baritone2, new String[]{str});
      this.shortDesc = str2;
      this.target = str3;
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return this.baritone.getCommandManager().tabComplete2(String.format("%s %s", this.target, argConsumer.rawRest()));
   }

   @Override
   public List getLongDesc() {
      return Collections.singletonList(String.format("This command is an alias, for: %s ...", this.target));
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) {
      this.baritone.getCommandManager().execute(String.format("%s %s", this.target, argConsumer.rawRest()));
   }
}
