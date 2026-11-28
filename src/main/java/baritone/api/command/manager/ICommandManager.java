package baritone.api.command.manager;

import baritone.api.IBaritone;
import baritone.api.command.ICommand;
import baritone.api.command.registry.Registry;
import java.util.stream.Stream;
import net.minecraft.util.Tuple;

public interface ICommandManager {
   ICommand getCommand(String str);

   IBaritone getBaritone();

   Registry getRegistry();

   Stream tabComplete2(String str);

   boolean execute(String str);

   boolean execute2(Tuple tuple);

   Stream tabComplete(Tuple tuple);
}
