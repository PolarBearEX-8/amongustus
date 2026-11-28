package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandKill extends CommandBase {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.kill.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()) : null;
   }

   @Override
   public String getCommandName() {
      return "kill";
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length == 0) {
         EntityPlayerMP commandSenderAsPlayer = getCommandSenderAsPlayer(commandSender);
         commandSenderAsPlayer.onKillCommand();
         notifyOperators(commandSender, this, "commands.kill.successful", new Object[]{commandSenderAsPlayer.getDisplayName()});
      } else {
         Entity entity = getEntity(commandSender, strings[0]);
         entity.onKillCommand();
         notifyOperators(commandSender, this, "commands.kill.successful", new Object[]{entity.getDisplayName()});
      }
   }
}
