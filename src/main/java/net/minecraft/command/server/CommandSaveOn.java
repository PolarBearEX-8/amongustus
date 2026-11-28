package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandSaveOn extends CommandBase {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.save-on.usage";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      MinecraftServer server = MinecraftServer.getServer();
      boolean flag = false;

      for (int i = 0; i < server.worldServers.length; i++) {
         if (server.worldServers[i] != null) {
            WorldServer world = server.worldServers[i];
            if (world.disableLevelSaving) {
               world.disableLevelSaving = false;
               flag = true;
            }
         }
      }

      if (flag) {
         notifyOperators(commandSender, this, "commands.save.enabled", new Object[0]);
      } else {
         throw new CommandException("commands.save-on.alreadyOn", new Object[0]);
      }
   }

   @Override
   public String getCommandName() {
      return "save-on";
   }
}
