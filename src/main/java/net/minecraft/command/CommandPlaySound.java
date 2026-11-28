package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class CommandPlaySound extends CommandBase {

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.playsound.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 2
         ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames())
         : (strings.length > 2 && strings.length <= 5 ? getTabCompletionCoordinate(strings, 2, pos) : null);
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 2) {
         throw new WrongUsageException(this.getCommandUsage(commandSender), new Object[0]);
      } else {
         int n = 0;
         String str = strings[n++];
         EntityPlayerMP player = getPlayer(commandSender, strings[n++]);
         Vec3 positionVector = commandSender.getPositionVector();
         double xCoord = positionVector.xCoord;
         if (strings.length > n) {
            xCoord = parseDouble(xCoord, strings[n++], true);
         }

         double yCoord = positionVector.yCoord;
         if (strings.length > n) {
            yCoord = parseDouble(yCoord, strings[n++], 0, 0, false);
         }

         double zCoord = positionVector.zCoord;
         if (strings.length > n) {
            zCoord = parseDouble(zCoord, strings[n++], true);
         }

         double d = 1.0;
         if (strings.length > n) {
            d = parseDouble(strings[n++], 0.0, Float.MAX_VALUE);
         }

         double d2 = 1.0;
         if (strings.length > n) {
            d2 = parseDouble(strings[n++], 0.0, 2.0);
         }

         double d3 = 0.0;
         if (strings.length > n) {
            d3 = parseDouble(strings[n], 0.0, 1.0);
         }

         double d4 = d > 1.0 ? d * 16.0 : 16.0;
         if (player.getDistance(xCoord, yCoord, zCoord) > d4) {
            if (d3 <= 0.0) {
               throw new CommandException("commands.playsound.playerTooFar", new Object[]{player.getName()});
            }

            double d5 = xCoord - player.posX;
            double d6 = yCoord - player.posY;
            double d7 = zCoord - player.posZ;
            double d8 = Math.sqrt(d5 * d5 + d6 * d6 + d7 * d7);
            if (d8 > 0.0) {
               xCoord = player.posX + d5 / d8 * 2.0;
               yCoord = player.posY + d6 / d8 * 2.0;
               zCoord = player.posZ + d7 / d8 * 2.0;
            }

            d = d3;
         }

         player.playerNetServerHandler.sendPacket(new S29PacketSoundEffect(str, xCoord, yCoord, zCoord, (float)d, (float)d2));
         notifyOperators(commandSender, this, "commands.playsound.success", new Object[]{str, player.getName()});
      }
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 1;
   }

   @Override
   public String getCommandName() {
      return "playsound";
   }
}
