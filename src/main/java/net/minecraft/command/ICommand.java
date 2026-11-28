package net.minecraft.command;

import java.util.List;
import net.minecraft.util.BlockPos;

public interface ICommand extends Comparable {
   boolean isUsernameIndex(String[] strings, int n);

   List getCommandAliases();

   List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos);

   String getCommandName();

   void processCommand(ICommandSender commandSender, String[] strings) throws CommandException;

   String getCommandUsage(ICommandSender commandSender);

   boolean canCommandSenderUseCommand(ICommandSender commandSender);
}
