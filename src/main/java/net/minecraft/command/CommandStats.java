package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandStats extends CommandBase {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.stats.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "stats";
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return strings.length > 0 && strings[0].equals("entity") && n == 1;
   }

   public List getObjectiveNames() {
      Collection scoreObjectives = MinecraftServer.getServer().worldServerForDimension(0).getScoreboard().getScoreObjectives();
      ArrayList list = Lists.newArrayList();

      for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(scoreObjectives)) {
         if (!scoreObjective.getCriteria().isReadOnly()) {
            list.add(scoreObjective.getName());
         }
      }

      return list;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 1) {
         throw new WrongUsageException("commands.stats.usage", new Object[0]);
      } else {
         boolean flag;
         if (strings[0].equals("entity")) {
            flag = false;
         } else {
            if (!strings[0].equals("block")) {
               throw new WrongUsageException("commands.stats.usage", new Object[0]);
            }

            flag = true;
         }

         int n;
         if (flag) {
            if (strings.length < 5) {
               throw new WrongUsageException("commands.stats.block.usage", new Object[0]);
            }

            n = 4;
         } else {
            if (strings.length < 3) {
               throw new WrongUsageException("commands.stats.entity.usage", new Object[0]);
            }

            n = 2;
         }

         String str = strings[n++];
         if ("set".equals(str)) {
            if (strings.length < n + 3) {
               if (n == 5) {
                  throw new WrongUsageException("commands.stats.block.set.usage", new Object[0]);
               }

               throw new WrongUsageException("commands.stats.entity.set.usage", new Object[0]);
            }
         } else {
            if (!"clear".equals(str)) {
               throw new WrongUsageException("commands.stats.usage", new Object[0]);
            }

            if (strings.length < n + 1) {
               if (n == 5) {
                  throw new WrongUsageException("commands.stats.block.clear.usage", new Object[0]);
               }

               throw new WrongUsageException("commands.stats.entity.clear.usage", new Object[0]);
            }
         }

         CommandResultStats.Type typeByName = CommandResultStats.Type.getTypeByName(strings[n++]);
         if (typeByName == null) {
            throw new CommandException("commands.stats.failed", new Object[0]);
         } else {
            World entityWorld = commandSender.getEntityWorld();
            CommandResultStats commandResultStats;
            if (flag) {
               BlockPos pos = parseBlockPos(commandSender, strings, 1, false);
               TileEntity tileEntity = entityWorld.getTileEntity(pos);
               if (tileEntity == null) {
                  throw new CommandException("commands.stats.noCompatibleBlock", new Object[]{pos.getX(), pos.getY(), pos.getZ()});
               }

               if (tileEntity instanceof TileEntityCommandBlock) {
                  commandResultStats = ((TileEntityCommandBlock)tileEntity).getCommandResultStats();
               } else {
                  if (!(tileEntity instanceof TileEntitySign)) {
                     throw new CommandException("commands.stats.noCompatibleBlock", new Object[]{pos.getX(), pos.getY(), pos.getZ()});
                  }

                  commandResultStats = ((TileEntitySign)tileEntity).getStats();
               }
            } else {
               commandResultStats = getEntity(commandSender, strings[1]).getCommandStats();
            }

            if ("set".equals(str)) {
               String str2 = strings[n++];
               String str3 = strings[n];
               if (str2.length() == 0 || str3.length() == 0) {
                  throw new CommandException("commands.stats.failed", new Object[0]);
               }

               CommandResultStats.setScoreBoardStat(commandResultStats, typeByName, str2, str3);
               notifyOperators(commandSender, this, "commands.stats.success", new Object[]{typeByName.getTypeName(), str3, str2});
            } else if ("clear".equals(str)) {
               CommandResultStats.setScoreBoardStat(commandResultStats, typeByName, (String)null, (String)null);
               notifyOperators(commandSender, this, "commands.stats.cleared", new Object[]{typeByName.getTypeName()});
            }

            if (flag) {
               entityWorld.getTileEntity(parseBlockPos(commandSender, strings, 1, false)).markDirty();
            }
         }
      }
   }

   public String[] getPlayerUsernames() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, new String[]{"entity", "block"})
         : (
            strings.length == 2 && strings[0].equals("entity")
               ? getListOfStringsMatchingLastWord(strings, this.getPlayerUsernames())
               : (
                  strings.length >= 2 && strings.length <= 4 && strings[0].equals("block")
                     ? getTabCompletionCoordinate(strings, 1, pos)
                     : (
                        (strings.length != 3 || !strings[0].equals("entity")) && (strings.length != 5 || !strings[0].equals("block"))
                           ? (
                              (strings.length != 4 || !strings[0].equals("entity")) && (strings.length != 6 || !strings[0].equals("block"))
                                 ? (
                                    strings.length == 6 && strings[0].equals("entity") || strings.length == 8 && strings[0].equals("block")
                                       ? getListOfStringsMatchingLastWord(strings, this.getObjectiveNames())
                                       : null
                                 )
                                 : getListOfStringsMatchingLastWord(strings, CommandResultStats.Type.getTypeNames())
                           )
                           : getListOfStringsMatchingLastWord(strings, new String[]{"set", "clear"})
                     )
               )
         );
   }
}
