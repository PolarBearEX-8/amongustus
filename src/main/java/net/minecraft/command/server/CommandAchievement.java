package net.minecraft.command.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;

public class CommandAchievement extends CommandBase {

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "achievement";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      if (strings.length == 1) {
         return getListOfStringsMatchingLastWord(strings, new String[]{"give", "take"});
      } else if (strings.length != 2) {
         return strings.length == 3 ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()) : null;
      } else {
         ArrayList list = Lists.newArrayList();

         for (StatBase statBase : (Iterable<StatBase>)(StatList.allStats)) {
            list.add(statBase.statId);
         }

         return getListOfStringsMatchingLastWord(strings, list);
      }
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 2;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 2) {
         throw new WrongUsageException("commands.achievement.usage", new Object[0]);
      } else {
         StatBase oneShotStat = StatList.getOneShotStat(strings[1]);
         if (oneShotStat == null && !strings[1].equals("*")) {
            throw new CommandException("commands.achievement.unknownAchievement", new Object[]{strings[1]});
         } else {
            EntityPlayerMP entityPlayerMP = strings.length >= 3 ? getPlayer(commandSender, strings[2]) : getCommandSenderAsPlayer(commandSender);
            boolean flag = strings[0].equalsIgnoreCase("give");
            boolean flag2 = strings[0].equalsIgnoreCase("take");
            if (flag || flag2) {
               if (oneShotStat == null) {
                  if (flag) {
                     for (Achievement achievement : (Iterable<Achievement>)(AchievementList.achievementList)) {
                        entityPlayerMP.triggerAchievement(achievement);
                     }

                     notifyOperators(commandSender, this, "commands.achievement.give.success.all", new Object[]{entityPlayerMP.getName()});
                  } else if (flag2) {
                     for (Achievement achievement2 : (Iterable<Achievement>)(Lists.reverse(AchievementList.achievementList))) {
                        entityPlayerMP.takeStat(achievement2);
                     }

                     notifyOperators(commandSender, this, "commands.achievement.take.success.all", new Object[]{entityPlayerMP.getName()});
                  }
               } else {
                  if (oneShotStat instanceof Achievement) {
                     Achievement achievement3 = (Achievement)oneShotStat;
                     if (flag) {
                        if (entityPlayerMP.getStatFile().hasAchievementUnlocked(achievement3)) {
                           throw new CommandException("commands.achievement.alreadyHave", new Object[]{entityPlayerMP.getName(), oneShotStat.createChatComponent()});
                        }

                        ArrayList list;
                        for (list = Lists.newArrayList();
                           achievement3.parentAchievement != null && !entityPlayerMP.getStatFile().hasAchievementUnlocked(achievement3.parentAchievement);
                           achievement3 = achievement3.parentAchievement
                        ) {
                           list.add(achievement3.parentAchievement);
                        }

                        for (Achievement achievement4 : (Iterable<Achievement>)(Lists.reverse(list))) {
                           entityPlayerMP.triggerAchievement(achievement4);
                        }
                     } else if (flag2) {
                        if (!entityPlayerMP.getStatFile().hasAchievementUnlocked(achievement3)) {
                           throw new CommandException("commands.achievement.dontHave", new Object[]{entityPlayerMP.getName(), oneShotStat.createChatComponent()});
                        }

                        ArrayList list2 = Lists.newArrayList(
                           Iterators.filter(AchievementList.achievementList.iterator(), new CommandAchievement.FilterPredicate(this, entityPlayerMP, oneShotStat))
                        );
                        ArrayList list3 = Lists.newArrayList(list2);

                        for (Achievement achievement5 : (Iterable<Achievement>)(list2)) {
                           Achievement achievement6 = achievement5;

                           boolean flag3;
                           for (flag3 = false; achievement6 != null; achievement6 = achievement6.parentAchievement) {
                              if (achievement6 == oneShotStat) {
                                 flag3 = true;
                              }
                           }

                           if (!flag3) {
                              for (Achievement i = achievement5; i != null; i = i.parentAchievement) {
                                 list3.remove(achievement5);
                              }
                           }
                        }

                        for (Achievement achievement7 : (Iterable<Achievement>)(list3)) {
                           entityPlayerMP.takeStat(achievement7);
                        }
                     }
                  }

                  if (flag) {
                     entityPlayerMP.triggerAchievement(oneShotStat);
                     notifyOperators(commandSender, this, "commands.achievement.give.success.one", new Object[]{entityPlayerMP.getName(), oneShotStat.createChatComponent()});
                  } else if (flag2) {
                     entityPlayerMP.takeStat(oneShotStat);
                     notifyOperators(commandSender, this, "commands.achievement.take.success.one", new Object[]{oneShotStat.createChatComponent(), entityPlayerMP.getName()});
                  }
               }
            }
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.achievement.usage";
   }


   public static class FilterPredicate implements Predicate {
      public final EntityPlayerMP player;
      public final StatBase targetStat;
      public final CommandAchievement commandAchievement;

      public FilterPredicate(CommandAchievement commandAchievement2, EntityPlayerMP entityPlayerMP, StatBase statBase) {
         this.commandAchievement = commandAchievement2;
         this.player = entityPlayerMP;
         this.targetStat = statBase;
      }

      public boolean apply(Object obj) {
         return this.apply((Achievement)obj);
      }

      public boolean apply(Achievement achievement) {
         return this.player.getStatFile().hasAchievementUnlocked(achievement) && achievement != this.targetStat;
      }
   }
}
