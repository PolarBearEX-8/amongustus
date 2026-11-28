package net.minecraft.command;

import java.util.List;
import java.util.Map;
import net.minecraft.util.BlockPos;

public interface ICommandManager {
   Map getCommands();

   List getPossibleCommands(ICommandSender commandSender);

   int executeCommand(ICommandSender commandSender, String str);

   List getTabCompletionOptions(ICommandSender commandSender, String str, BlockPos pos);
}
