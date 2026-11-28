package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;

public class CommandTime extends CommandBase {

   @Override
   public String getCommandName() {
      return "time";
   }

   public void setTime(ICommandSender commandSender, int n) {
      for (int i = 0; i < MinecraftServer.getServer().worldServers.length; i++) {
         MinecraftServer.getServer().worldServers[i].setWorldTime((long)n);
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, new String[]{"set", "add", "query"})
         : (
            strings.length == 2 && strings[0].equals("set")
               ? getListOfStringsMatchingLastWord(strings, new String[]{"day", "night"})
               : (strings.length == 2 && strings[0].equals("query") ? getListOfStringsMatchingLastWord(strings, new String[]{"daytime", "gametime"}) : null)
         );
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.time.usage";
   }

   public void addTime(ICommandSender commandSender, int n) {
      for (int i = 0; i < MinecraftServer.getServer().worldServers.length; i++) {
         WorldServer world = MinecraftServer.getServer().worldServers[i];
         world.setWorldTime(world.getWorldTime() + (long)n);
      }
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length > 1) {
         if (strings[0].equals("set")) {
            int n;
            if (strings[1].equals("day")) {
               n = 1000;
            } else if (strings[1].equals("night")) {
               n = 13000;
            } else {
               n = parseInt(strings[1], 0);
            }

            this.setTime(commandSender, n);
            notifyOperators(commandSender, this, "commands.time.set", new Object[]{n});
            return;
         }

         if (strings[0].equals("add")) {
            int n2 = parseInt(strings[1], 0);
            this.addTime(commandSender, n2);
            notifyOperators(commandSender, this, "commands.time.added", new Object[]{n2});
            return;
         }

         if (strings[0].equals("query")) {
            if (strings[1].equals("daytime")) {
               int n3 = (int)(commandSender.getEntityWorld().getWorldTime() % 2147483647L);
               commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, n3);
               notifyOperators(commandSender, this, "commands.time.query", new Object[]{n3});
               return;
            }

            if (strings[1].equals("gametime")) {
               int n4 = (int)(commandSender.getEntityWorld().getTotalWorldTime() % 2147483647L);
               commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, n4);
               notifyOperators(commandSender, this, "commands.time.query", new Object[]{n4});
               return;
            }
         }
      }

      throw new WrongUsageException("commands.time.usage", new Object[0]);
   }
}
