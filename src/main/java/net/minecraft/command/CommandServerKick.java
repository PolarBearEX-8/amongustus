package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandServerKick extends CommandBase {

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length > 0 && strings[0].length() > 1) {
         EntityPlayerMP playerByUsername = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(strings[0]);
         String str = "Kicked by an operator.";
         boolean flag = false;
         if (playerByUsername == null) {
            throw new PlayerNotFoundException();
         } else {
            if (strings.length >= 2) {
               str = getChatComponentFromNthArg(commandSender, strings, 1).getUnformattedText();
               flag = true;
            }

            playerByUsername.playerNetServerHandler.kickPlayerFromServer(str);
            if (flag) {
               notifyOperators(commandSender, this, "commands.kick.success.reason", new Object[]{playerByUsername.getName(), str});
            } else {
               notifyOperators(commandSender, this, "commands.kick.success", new Object[]{playerByUsername.getName()});
            }
         }
      } else {
         throw new WrongUsageException("commands.kick.usage", new Object[0]);
      }
   }

   @Override
   public String getCommandName() {
      return "kick";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.kick.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length >= 1 ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()) : null;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }
}
