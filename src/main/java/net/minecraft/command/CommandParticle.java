package net.minecraft.command;

import java.util.List;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CommandParticle extends CommandBase {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.particle.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "particle";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 8) {
         throw new WrongUsageException("commands.particle.usage", new Object[0]);
      } else {
         boolean flag = false;
         EnumParticleTypes enumParticleTypes = null;

         for (EnumParticleTypes enumParticleTypes2 : EnumParticleTypes.values()) {
            if (enumParticleTypes2.hasArguments()) {
               if (strings[0].startsWith(enumParticleTypes2.getParticleName())) {
                  flag = true;
                  enumParticleTypes = enumParticleTypes2;
                  break;
               }
            } else if (strings[0].equals(enumParticleTypes2.getParticleName())) {
               flag = true;
               enumParticleTypes = enumParticleTypes2;
               break;
            }
         }

         if (!flag) {
            throw new CommandException("commands.particle.notFound", new Object[]{strings[0]});
         } else {
            String str = strings[0];
            Vec3 positionVector = commandSender.getPositionVector();
            double d = (double)((float)parseDouble(positionVector.xCoord, strings[1], true));
            double d2 = (double)((float)parseDouble(positionVector.yCoord, strings[2], true));
            double d3 = (double)((float)parseDouble(positionVector.zCoord, strings[3], true));
            double d4 = (double)((float)parseDouble(strings[4]));
            double d5 = (double)((float)parseDouble(strings[5]));
            double d6 = (double)((float)parseDouble(strings[6]));
            double d7 = (double)((float)parseDouble(strings[7]));
            int n = 0;
            if (strings.length > 8) {
               n = parseInt(strings[8], 0);
            }

            boolean flag2 = false;
            if (strings.length > 9 && "force".equals(strings[9])) {
               flag2 = true;
            }

            World entityWorld = commandSender.getEntityWorld();
            if (entityWorld instanceof WorldServer) {
               WorldServer world = (WorldServer)entityWorld;
               int[] ints = new int[enumParticleTypes.getArgumentCount()];
               if (enumParticleTypes.hasArguments()) {
                  String[] strings2 = strings[0].split("_", 3);

                  for (int i = 1; i < strings2.length; i++) {
                     try {
                        ints[i - 1] = Integer.parseInt(strings2[i]);
                     } catch (NumberFormatException e) {
                        throw new CommandException("commands.particle.notFound", new Object[]{strings[0]});
                     }
                  }
               }

               world.spawnParticle(enumParticleTypes, flag2, d, d2, d3, n, d4, d5, d6, d7, ints);
               notifyOperators(commandSender, this, "commands.particle.success", new Object[]{str, Math.max(n, 1)});
            }
         }
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, EnumParticleTypes.getParticleNames())
         : (
            strings.length > 1 && strings.length <= 4
               ? getTabCompletionCoordinate(strings, 1, pos)
               : (strings.length == 10 ? getListOfStringsMatchingLastWord(strings, new String[]{"normal", "force"}) : null)
         );
   }
}
