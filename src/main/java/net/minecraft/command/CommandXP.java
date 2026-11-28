package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandXP extends CommandBase {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.xp.usage";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length <= 0) {
         throw new WrongUsageException("commands.xp.usage", new Object[0]);
      } else {
         String str = strings[0];
         boolean flag = str.endsWith("l") || str.endsWith("L");
         if (flag && str.length() > 1) {
            str = str.substring(0, str.length() - 1);
         }

         int n = parseInt(str);
         boolean flag2 = n < 0;
         if (flag2) {
            n *= -1;
         }

         EntityPlayerMP entityPlayerMP = strings.length > 1 ? getPlayer(commandSender, strings[1]) : getCommandSenderAsPlayer(commandSender);
         if (flag) {
            commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, entityPlayerMP.experienceLevel);
            if (flag2) {
               entityPlayerMP.addExperienceLevel(-n);
               notifyOperators(commandSender, this, "commands.xp.success.negative.levels", new Object[]{n, entityPlayerMP.getName()});
            } else {
               entityPlayerMP.addExperienceLevel(n);
               notifyOperators(commandSender, this, "commands.xp.success.levels", new Object[]{n, entityPlayerMP.getName()});
            }
         } else {
            commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, entityPlayerMP.experienceTotal);
            if (flag2) {
               throw new CommandException("commands.xp.failure.widthdrawXp", new Object[0]);
            }

            entityPlayerMP.addExperience(n);
            notifyOperators(commandSender, this, "commands.xp.success", new Object[]{n, entityPlayerMP.getName()});
         }
      }
   }

   public String[] getAllUsernames() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   @Override
   public String getCommandName() {
      return "xp";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 2 ? getListOfStringsMatchingLastWord(strings, this.getAllUsernames()) : null;
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 1;
   }
}
