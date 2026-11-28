package net.minecraft.command.server;

import net.minecraft.command.CommandResultStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandListPlayers extends CommandBase {

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public String getCommandName() {
      return "list";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.players.usage";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      int currentPlayerCount = MinecraftServer.getServer().getCurrentPlayerCount();
      commandSender.addChatMessage(new ChatComponentTranslation("commands.players.list", new Object[]{currentPlayerCount, MinecraftServer.getServer().getMaxPlayers()}));
      commandSender.addChatMessage(
         new ChatComponentText(MinecraftServer.getServer().getConfigurationManager().getFormattedListOfPlayers(strings.length > 0 && "uuids".equalsIgnoreCase(strings[0])))
      );
      commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, currentPlayerCount);
   }
}
