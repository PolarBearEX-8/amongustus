package net.minecraft.command;

import net.minecraft.server.MinecraftServer;

public class CommandSetPlayerTimeout extends CommandBase {

   @Override
   public String getCommandName() {
      return "setidletimeout";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length != 1) {
         throw new WrongUsageException("commands.setidletimeout.usage", new Object[0]);
      } else {
         int n = parseInt(strings[0], 0);
         MinecraftServer.getServer().setPlayerIdleTimeout(n);
         notifyOperators(commandSender, this, "commands.setidletimeout.success", new Object[]{n});
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.setidletimeout.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }
}
