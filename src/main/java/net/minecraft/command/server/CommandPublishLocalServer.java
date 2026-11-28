package net.minecraft.command.server;

import net.minecraft.world.WorldSettings;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandPublishLocalServer extends CommandBase {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.publish.usage";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      String str = MinecraftServer.getServer().shareToLAN(WorldSettings.GameType.SURVIVAL, false);
      if (str != null) {
         notifyOperators(commandSender, this, "commands.publish.started", new Object[]{str});
      } else {
         notifyOperators(commandSender, this, "commands.publish.failed", new Object[0]);
      }
   }

   @Override
   public String getCommandName() {
      return "publish";
   }
}
