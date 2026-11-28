package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandSetDefaultSpawnpoint extends CommandBase {

   @Override
   public String getCommandName() {
      return "setworldspawn";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length > 0 && strings.length <= 3 ? getTabCompletionCoordinate(strings, 0, pos) : null;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.setworldspawn.usage";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      BlockPos pos;
      if (strings.length == 0) {
         pos = getCommandSenderAsPlayer(commandSender).getPosition();
      } else {
         if (strings.length != 3 || commandSender.getEntityWorld() == null) {
            throw new WrongUsageException("commands.setworldspawn.usage", new Object[0]);
         }

         pos = parseBlockPos(commandSender, strings, 0, true);
      }

      commandSender.getEntityWorld().setSpawnPoint(pos);
      MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(new S05PacketSpawnPosition(pos));
      notifyOperators(commandSender, this, "commands.setworldspawn.success", new Object[]{pos.getX(), pos.getY(), pos.getZ()});
   }
}
