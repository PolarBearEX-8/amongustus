package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandTrigger extends CommandBase {

   @Override
   public String getCommandName() {
      return "trigger";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.trigger.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      if (strings.length == 1) {
         Scoreboard scoreboard = MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();
         ArrayList list = Lists.newArrayList();

         for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(scoreboard.getScoreObjectives())) {
            if (scoreObjective.getCriteria() == IScoreObjectiveCriteria.TRIGGER) {
               list.add(scoreObjective.getName());
            }
         }

         return getListOfStringsMatchingLastWord(strings, (String[])list.toArray(new String[list.size()]));
      } else {
         return strings.length == 2 ? getListOfStringsMatchingLastWord(strings, new String[]{"add", "set"}) : null;
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 3) {
         throw new WrongUsageException("commands.trigger.usage", new Object[0]);
      } else {
         EntityPlayerMP entityPlayerMP;
         if (commandSender instanceof EntityPlayerMP) {
            entityPlayerMP = (EntityPlayerMP)commandSender;
         } else {
            Entity commandSenderEntity = commandSender.getCommandSenderEntity();
            if (!(commandSenderEntity instanceof EntityPlayerMP)) {
               throw new CommandException("commands.trigger.invalidPlayer", new Object[0]);
            }

            entityPlayerMP = (EntityPlayerMP)commandSenderEntity;
         }

         Scoreboard scoreboard = MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();
         ScoreObjective objective = scoreboard.getObjective(strings[0]);
         if (objective != null && objective.getCriteria() == IScoreObjectiveCriteria.TRIGGER) {
            int n = parseInt(strings[2]);
            if (!scoreboard.entityHasObjective(entityPlayerMP.getName(), objective)) {
               throw new CommandException("commands.trigger.invalidObjective", new Object[]{strings[0]});
            } else {
               Score valueFromObjective = scoreboard.getValueFromObjective(entityPlayerMP.getName(), objective);
               if (valueFromObjective.isLocked()) {
                  throw new CommandException("commands.trigger.disabled", new Object[]{strings[0]});
               } else {
                  if ("set".equals(strings[1])) {
                     valueFromObjective.setScorePoints(n);
                  } else {
                     if (!"add".equals(strings[1])) {
                        throw new CommandException("commands.trigger.invalidMode", new Object[]{strings[1]});
                     }

                     valueFromObjective.increseScore(n);
                  }

                  valueFromObjective.setLocked(true);
                  if (entityPlayerMP.theItemInWorldManager.isCreative()) {
                     notifyOperators(commandSender, this, "commands.trigger.success", new Object[]{strings[0], strings[1], strings[2]});
                  }
               }
            }
         } else {
            throw new CommandException("commands.trigger.invalidObjective", new Object[]{strings[0]});
         }
      }
   }
}
