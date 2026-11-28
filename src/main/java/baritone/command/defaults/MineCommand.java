package baritone.command.defaults;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.datatypes.ForBlockOptionalMeta;
import baritone.api.command.exception.CommandException;
import baritone.api.utils.BlockOptionalMeta;
import baritone.api.command.argument.IArgConsumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MineCommand extends Command {

   public MineCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"mine"});
   }

   @Override
   public String getShortDesc() {
      return "Mine some blocks";
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.getAsOrDefault(Integer.class, 0);

      while (argConsumer.has(2)) {
         argConsumer.getDatatypeFor(ForBlockOptionalMeta.INSTANCE);
      }

      return argConsumer.tabCompleteDatatype(ForBlockOptionalMeta.INSTANCE);
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The mine command allows you to tell Baritone to search for and mine individual blocks.",
         "",
         "The specified blocks can be ores (which are commonly cached), or any other block.",
         "",
         "Also see the legitMine settings (see #set l legitMine).",
         "",
         "Usage:",
         "> mine diamond_ore - Mines all diamonds it can find.",
         "> mine redstone_ore lit_redstone_ore - Mines redstone ore.",
         "> mine log:0 - Mines only oak logs."
      );
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      int asOrDefault = (Integer)argConsumer.getAsOrDefault(Integer.class, 0);
      argConsumer.requireMin(1);
      ArrayList list = new ArrayList();

      while (argConsumer.hasAny()) {
         list.add(argConsumer.getDatatypeFor(ForBlockOptionalMeta.INSTANCE));
      }

      BaritoneAPI.getProvider().getInstance3().repack(this.ctx);
      this.logDirect2(String.format("Mining %s", list.toString()));
      this.baritone.getMineProcess().mine2(asOrDefault, (BlockOptionalMeta[])list.toArray(new BlockOptionalMeta[0]));
   }
}
