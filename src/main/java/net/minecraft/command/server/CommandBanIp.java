package net.minecraft.command.server;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.IPBanEntry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class CommandBanIp extends CommandBase {
   public static final Pattern IP_PATTERN = Pattern.compile(
      "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"
   );

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()) : null;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.banip.usage";
   }

   public void banIp(ICommandSender commandSender, String str, String str2) {
      IPBanEntry pBanEntry = new IPBanEntry(str, (Date)null, commandSender.getName(), (Date)null, str2);
      MinecraftServer.getServer().getConfigurationManager().getBannedIPs().addEntry(pBanEntry);
      List playersMatchingAddress = MinecraftServer.getServer().getConfigurationManager().getPlayersMatchingAddress(str);
      String[] strings = new String[playersMatchingAddress.size()];
      int n = 0;

      for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(playersMatchingAddress)) {
         entityPlayerMP.playerNetServerHandler.kickPlayerFromServer("You have been IP banned.");
         strings[n++] = entityPlayerMP.getName();
      }

      if (playersMatchingAddress.isEmpty()) {
         notifyOperators(commandSender, this, "commands.banip.success", new Object[]{str});
      } else {
         notifyOperators(commandSender, this, "commands.banip.success.players", new Object[]{str, joinNiceString(strings)});
      }
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length >= 1 && strings[0].length() > 1) {
         IChatComponent component = strings.length >= 2 ? getChatComponentFromNthArg(commandSender, strings, 1) : null;
         if (IP_PATTERN.matcher(strings[0]).matches()) {
            this.banIp(commandSender, strings[0], component == null ? null : component.getUnformattedText());
         } else {
            EntityPlayerMP playerByUsername = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(strings[0]);
            if (playerByUsername == null) {
               throw new PlayerNotFoundException("commands.banip.invalid", new Object[0]);
            }

            this.banIp(commandSender, playerByUsername.getPlayerIP(), component == null ? null : component.getUnformattedText());
         }
      } else {
         throw new WrongUsageException("commands.banip.usage", new Object[0]);
      }
   }

   @Override
   public String getCommandName() {
      return "ban-ip";
   }

   @Override
   public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
      return MinecraftServer.getServer().getConfigurationManager().getBannedIPs().isLanServer() && super.canCommandSenderUseCommand(commandSender);
   }
}
