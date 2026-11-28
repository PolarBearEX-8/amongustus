package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandListBans extends CommandBase {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.banlist.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, new String[]{"players", "ips"}) : null;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public String getCommandName() {
      return "banlist";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length >= 1 && strings[0].equalsIgnoreCase("ips")) {
         commandSender.addChatMessage(
            new ChatComponentTranslation(
               "commands.banlist.ips", new Object[]{MinecraftServer.getServer().getConfigurationManager().getBannedIPs().getKeys().length}
            )
         );
         commandSender.addChatMessage(new ChatComponentText(joinNiceString(MinecraftServer.getServer().getConfigurationManager().getBannedIPs().getKeys())));
      } else {
         commandSender.addChatMessage(
            new ChatComponentTranslation(
               "commands.banlist.players", new Object[]{MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().getKeys().length}
            )
         );
         commandSender.addChatMessage(new ChatComponentText(joinNiceString(MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().getKeys())));
      }
   }

   @Override
   public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
      return (
            MinecraftServer.getServer().getConfigurationManager().getBannedIPs().isLanServer()
               || MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().isLanServer()
         )
         && super.canCommandSenderUseCommand(commandSender);
   }
}
