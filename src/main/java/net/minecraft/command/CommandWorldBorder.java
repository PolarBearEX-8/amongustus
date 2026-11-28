package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.border.WorldBorder;

public class CommandWorldBorder extends CommandBase {

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.worldborder.usage";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 1) {
         throw new WrongUsageException("commands.worldborder.usage", new Object[0]);
      } else {
         WorldBorder worldBorder = this.getWorldBorder();
         if (strings[0].equals("set")) {
            if (strings.length != 2 && strings.length != 3) {
               throw new WrongUsageException("commands.worldborder.set.usage", new Object[0]);
            }

            double targetSize = worldBorder.getTargetSize();
            double d = parseDouble(strings[1], 1.0, 6.0E7);
            long l = strings.length > 2 ? parseLong(strings[2], 0L, 9223372036854775L) * 1000L : 0L;
            if (l > 0L) {
               worldBorder.setTransition(targetSize, d, l);
               if (targetSize > d) {
                  notifyOperators(
                     commandSender,
                     this,
                     "commands.worldborder.setSlowly.shrink.success",
                     new Object[]{String.format("%.1f", d), String.format("%.1f", targetSize), Long.toString(l / 1000L)}
                  );
               } else {
                  notifyOperators(
                     commandSender,
                     this,
                     "commands.worldborder.setSlowly.grow.success",
                     new Object[]{String.format("%.1f", d), String.format("%.1f", targetSize), Long.toString(l / 1000L)}
                  );
               }
            } else {
               worldBorder.setTransition(d);
               notifyOperators(commandSender, this, "commands.worldborder.set.success", new Object[]{String.format("%.1f", d), String.format("%.1f", targetSize)});
            }
         } else if (strings[0].equals("add")) {
            if (strings.length != 2 && strings.length != 3) {
               throw new WrongUsageException("commands.worldborder.add.usage", new Object[0]);
            }

            double diameter = worldBorder.getDiameter();
            double d2 = diameter + parseDouble(strings[1], -diameter, 6.0E7 - diameter);
            long l2 = worldBorder.getTimeUntilTarget() + (strings.length > 2 ? parseLong(strings[2], 0L, 9223372036854775L) * 1000L : 0L);
            if (l2 > 0L) {
               worldBorder.setTransition(diameter, d2, l2);
               if (diameter > d2) {
                  notifyOperators(
                     commandSender,
                     this,
                     "commands.worldborder.setSlowly.shrink.success",
                     new Object[]{String.format("%.1f", d2), String.format("%.1f", diameter), Long.toString(l2 / 1000L)}
                  );
               } else {
                  notifyOperators(
                     commandSender,
                     this,
                     "commands.worldborder.setSlowly.grow.success",
                     new Object[]{String.format("%.1f", d2), String.format("%.1f", diameter), Long.toString(l2 / 1000L)}
                  );
               }
            } else {
               worldBorder.setTransition(d2);
               notifyOperators(commandSender, this, "commands.worldborder.set.success", new Object[]{String.format("%.1f", d2), String.format("%.1f", diameter)});
            }
         } else if (strings[0].equals("center")) {
            if (strings.length != 3) {
               throw new WrongUsageException("commands.worldborder.center.usage", new Object[0]);
            }

            BlockPos position = commandSender.getPosition();
            double d3 = parseDouble((double)position.getX() + 0.5, strings[1], true);
            double d4 = parseDouble((double)position.getZ() + 0.5, strings[2], true);
            worldBorder.setCenter(d3, d4);
            notifyOperators(commandSender, this, "commands.worldborder.center.success", new Object[]{d3, d4});
         } else if (strings[0].equals("damage")) {
            if (strings.length < 2) {
               throw new WrongUsageException("commands.worldborder.damage.usage", new Object[0]);
            }

            if (strings[1].equals("buffer")) {
               if (strings.length != 3) {
                  throw new WrongUsageException("commands.worldborder.damage.buffer.usage", new Object[0]);
               }

               double d5 = parseDouble(strings[2], 0.0);
               double damageBuffer = worldBorder.getDamageBuffer();
               worldBorder.setDamageBuffer(d5);
               notifyOperators(
                  commandSender, this, "commands.worldborder.damage.buffer.success", new Object[]{String.format("%.1f", d5), String.format("%.1f", damageBuffer)}
               );
            } else if (strings[1].equals("amount")) {
               if (strings.length != 3) {
                  throw new WrongUsageException("commands.worldborder.damage.amount.usage", new Object[0]);
               }

               double d6 = parseDouble(strings[2], 0.0);
               double damageAmount = worldBorder.getDamageAmount();
               worldBorder.setDamageAmount(d6);
               notifyOperators(
                  commandSender, this, "commands.worldborder.damage.amount.success", new Object[]{String.format("%.2f", d6), String.format("%.2f", damageAmount)}
               );
            }
         } else if (strings[0].equals("warning")) {
            if (strings.length < 2) {
               throw new WrongUsageException("commands.worldborder.warning.usage", new Object[0]);
            }

            int n = parseInt(strings[2], 0);
            if (strings[1].equals("time")) {
               if (strings.length != 3) {
                  throw new WrongUsageException("commands.worldborder.warning.time.usage", new Object[0]);
               }

               int warningTime = worldBorder.getWarningTime();
               worldBorder.setWarningTime(n);
               notifyOperators(commandSender, this, "commands.worldborder.warning.time.success", new Object[]{n, warningTime});
            } else if (strings[1].equals("distance")) {
               if (strings.length != 3) {
                  throw new WrongUsageException("commands.worldborder.warning.distance.usage", new Object[0]);
               }

               int warningDistance = worldBorder.getWarningDistance();
               worldBorder.setWarningDistance(n);
               notifyOperators(commandSender, this, "commands.worldborder.warning.distance.success", new Object[]{n, warningDistance});
            }
         } else {
            if (!strings[0].equals("get")) {
               throw new WrongUsageException("commands.worldborder.usage", new Object[0]);
            }

            double diameter2 = worldBorder.getDiameter();
            commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, MathHelper.floor_double(diameter2 + 0.5));
            commandSender.addChatMessage(new ChatComponentTranslation("commands.worldborder.get.success", new Object[]{String.format("%.0f", diameter2)}));
         }
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, new String[]{"set", "center", "damage", "warning", "add", "get"})
         : (
            strings.length == 2 && strings[0].equals("damage")
               ? getListOfStringsMatchingLastWord(strings, new String[]{"buffer", "amount"})
               : (
                  strings.length >= 2 && strings.length <= 3 && strings[0].equals("center")
                     ? getTabCompletionCoordinateXZ(strings, 1, pos)
                     : (strings.length == 2 && strings[0].equals("warning") ? getListOfStringsMatchingLastWord(strings, new String[]{"time", "distance"}) : null)
               )
         );
   }

   public WorldBorder getWorldBorder() {
      return MinecraftServer.getServer().worldServers[0].getWorldBorder();
   }

   @Override
   public String getCommandName() {
      return "worldborder";
   }
}
