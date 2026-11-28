package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandSetSpawnpoint extends CommandBase {

   @Override
   public String getCommandName() {
      return "spawnpoint";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames())
         : (strings.length > 1 && strings.length <= 4 ? getTabCompletionCoordinate(strings, 1, pos) : null);
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.spawnpoint.usage";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length > 1 && strings.length < 4) {
         throw new WrongUsageException("commands.spawnpoint.usage", new Object[0]);
      } else {
         EntityPlayerMP entityPlayerMP = strings.length > 0 ? getPlayer(commandSender, strings[0]) : getCommandSenderAsPlayer(commandSender);
         BlockPos pos = strings.length > 3 ? parseBlockPos(commandSender, strings, 1, true) : entityPlayerMP.getPosition();
         if (entityPlayerMP.entityWorld != null) {
            entityPlayerMP.setSpawnPoint(pos, true);
            notifyOperators(commandSender, this, "commands.spawnpoint.success", new Object[]{entityPlayerMP.getName(), pos.getX(), pos.getY(), pos.getZ()});
         }
      }
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }
}
