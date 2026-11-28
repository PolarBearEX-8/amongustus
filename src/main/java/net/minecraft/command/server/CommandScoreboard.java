package net.minecraft.command.server;

import net.minecraft.command.CommandResultStats;
import net.minecraft.scoreboard.Team;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class CommandScoreboard extends CommandBase {

   public void leaveTeam(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      HashSet set = Sets.newHashSet();
      HashSet set2 = Sets.newHashSet();
      if (commandSender instanceof EntityPlayer && n == strings.length) {
         String name = getCommandSenderAsPlayer(commandSender).getName();
         if (scoreboard.removePlayerFromTeams(name)) {
            set.add(name);
         } else {
            set2.add(name);
         }
      } else {
         while (n < strings.length) {
            String str = strings[n++];
            if (str.startsWith("@")) {
               for (Entity entity : (Iterable<Entity>)(getEntityList(commandSender, str))) {
                  String entityName = getEntityName(commandSender, entity.getUniqueID().toString());
                  if (scoreboard.removePlayerFromTeams(entityName)) {
                     set.add(entityName);
                  } else {
                     set2.add(entityName);
                  }
               }
            } else {
               String entityName2 = getEntityName(commandSender, str);
               if (scoreboard.removePlayerFromTeams(entityName2)) {
                  set.add(entityName2);
               } else {
                  set2.add(entityName2);
               }
            }
         }
      }

      if (!set.isEmpty()) {
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ENTITIES, set.size());
         notifyOperators(
            commandSender, this, "commands.scoreboard.teams.leave.success", new Object[]{set.size(), joinNiceString(set.toArray(new String[set.size()]))}
         );
      }

      if (!set2.isEmpty()) {
         throw new CommandException("commands.scoreboard.teams.leave.failure", new Object[]{set2.size(), joinNiceString(set2.toArray(new String[set2.size()]))});
      }
   }

   public void testPlayerScore(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      String entityName = getEntityName(commandSender, strings[n++]);
      if (entityName.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{entityName, 40});
      } else {
         ScoreObjective objective = this.getObjective(strings[n++], false);
         if (!scoreboard.entityHasObjective(entityName, objective)) {
            throw new CommandException("commands.scoreboard.players.test.notFound", new Object[]{objective.getName(), entityName});
         } else {
            int n2 = strings[n].equals("*") ? Integer.MIN_VALUE : parseInt(strings[n]);
            n++;
            int n3 = n < strings.length && !strings[n].equals("*") ? parseInt(strings[n], n2) : Integer.MAX_VALUE;
            Score valueFromObjective = scoreboard.getValueFromObjective(entityName, objective);
            if (valueFromObjective.getScorePoints() >= n2 && valueFromObjective.getScorePoints() <= n3) {
               notifyOperators(commandSender, this, "commands.scoreboard.players.test.success", new Object[]{valueFromObjective.getScorePoints(), n2, n3});
            } else {
               throw new CommandException("commands.scoreboard.players.test.failed", new Object[]{valueFromObjective.getScorePoints(), n2, n3});
            }
         }
      }
   }

   public List getTriggerObjectives() {
      Collection scoreObjectives = this.getScoreboard().getScoreObjectives();
      ArrayList list = Lists.newArrayList();

      for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(scoreObjectives)) {
         if (scoreObjective.getCriteria() == IScoreObjectiveCriteria.TRIGGER) {
            list.add(scoreObjective.getName());
         }
      }

      return list;
   }

   public void setPlayer(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      String str = strings[n - 1];
      int n2 = n;
      String entityName = getEntityName(commandSender, strings[n++]);
      if (entityName.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{entityName, 40});
      } else {
         ScoreObjective objective = this.getObjective(strings[n++], true);
         int n3 = str.equalsIgnoreCase("set") ? parseInt(strings[n++]) : parseInt(strings[n++], 0);
         if (strings.length > n) {
            Entity entity = getEntity(commandSender, strings[n2]);

            try {
               NBTTagCompound tagFromJson = JsonToNBT.getTagFromJson(buildString(strings, n));
               NBTTagCompound tag = new NBTTagCompound();
               entity.writeToNBT(tag);
               if (!NBTUtil.areNBTEquals(tagFromJson, tag, true)) {
                  throw new CommandException("commands.scoreboard.players.set.tagMismatch", new Object[]{entityName});
               }
            } catch (NBTException e) {
               throw new CommandException("commands.scoreboard.players.set.tagError", new Object[]{e.getMessage()});
            }
         }

         Score valueFromObjective = this.getScoreboard().getValueFromObjective(entityName, objective);
         if (str.equalsIgnoreCase("set")) {
            valueFromObjective.setScorePoints(n3);
         } else if (str.equalsIgnoreCase("add")) {
            valueFromObjective.increseScore(n3);
         } else {
            valueFromObjective.decreaseScore(n3);
         }

         notifyOperators(commandSender, this, "commands.scoreboard.players.set.success", new Object[]{objective.getName(), entityName, valueFromObjective.getScorePoints()});
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      if (strings.length == 1) {
         return getListOfStringsMatchingLastWord(strings, new String[]{"objectives", "players", "teams"});
      } else {
         if (strings[0].equalsIgnoreCase("objectives")) {
            if (strings.length == 2) {
               return getListOfStringsMatchingLastWord(strings, new String[]{"list", "add", "remove", "setdisplay"});
            }

            if (strings[1].equalsIgnoreCase("add")) {
               if (strings.length == 4) {
                  return getListOfStringsMatchingLastWord(strings, IScoreObjectiveCriteria.INSTANCES.keySet());
               }
            } else if (strings[1].equalsIgnoreCase("remove")) {
               if (strings.length == 3) {
                  return getListOfStringsMatchingLastWord(strings, this.getObjectives(false));
               }
            } else if (strings[1].equalsIgnoreCase("setdisplay")) {
               if (strings.length == 3) {
                  return getListOfStringsMatchingLastWord(strings, Scoreboard.getDisplaySlotStrings());
               }

               if (strings.length == 4) {
                  return getListOfStringsMatchingLastWord(strings, this.getObjectives(false));
               }
            }
         } else if (strings[0].equalsIgnoreCase("players")) {
            if (strings.length == 2) {
               return getListOfStringsMatchingLastWord(strings, new String[]{"set", "add", "remove", "reset", "list", "enable", "test", "operation"});
            }

            if (!strings[1].equalsIgnoreCase("set")
               && !strings[1].equalsIgnoreCase("add")
               && !strings[1].equalsIgnoreCase("remove")
               && !strings[1].equalsIgnoreCase("reset")) {
               if (strings[1].equalsIgnoreCase("enable")) {
                  if (strings.length == 3) {
                     return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
                  }

                  if (strings.length == 4) {
                     return getListOfStringsMatchingLastWord(strings, this.getTriggerObjectives());
                  }
               } else if (!strings[1].equalsIgnoreCase("list") && !strings[1].equalsIgnoreCase("test")) {
                  if (strings[1].equalsIgnoreCase("operation")) {
                     if (strings.length == 3) {
                        return getListOfStringsMatchingLastWord(strings, this.getScoreboard().getObjectiveNames());
                     }

                     if (strings.length == 4) {
                        return getListOfStringsMatchingLastWord(strings, this.getObjectives(true));
                     }

                     if (strings.length == 5) {
                        return getListOfStringsMatchingLastWord(strings, new String[]{"+=", "-=", "*=", "/=", "%=", "=", "<", ">", "><"});
                     }

                     if (strings.length == 6) {
                        return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
                     }

                     if (strings.length == 7) {
                        return getListOfStringsMatchingLastWord(strings, this.getObjectives(false));
                     }
                  }
               } else {
                  if (strings.length == 3) {
                     return getListOfStringsMatchingLastWord(strings, this.getScoreboard().getObjectiveNames());
                  }

                  if (strings.length == 4 && strings[1].equalsIgnoreCase("test")) {
                     return getListOfStringsMatchingLastWord(strings, this.getObjectives(false));
                  }
               }
            } else {
               if (strings.length == 3) {
                  return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
               }

               if (strings.length == 4) {
                  return getListOfStringsMatchingLastWord(strings, this.getObjectives(true));
               }
            }
         } else if (strings[0].equalsIgnoreCase("teams")) {
            if (strings.length == 2) {
               return getListOfStringsMatchingLastWord(strings, new String[]{"add", "remove", "join", "leave", "empty", "list", "option"});
            }

            if (strings[1].equalsIgnoreCase("join")) {
               if (strings.length == 3) {
                  return getListOfStringsMatchingLastWord(strings, this.getScoreboard().getTeamNames());
               }

               if (strings.length >= 4) {
                  return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
               }
            } else {
               if (strings[1].equalsIgnoreCase("leave")) {
                  return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
               }

               if (!strings[1].equalsIgnoreCase("empty") && !strings[1].equalsIgnoreCase("list") && !strings[1].equalsIgnoreCase("remove")) {
                  if (strings[1].equalsIgnoreCase("option")) {
                     if (strings.length == 3) {
                        return getListOfStringsMatchingLastWord(strings, this.getScoreboard().getTeamNames());
                     }

                     if (strings.length == 4) {
                        return getListOfStringsMatchingLastWord(
                           strings, new String[]{"color", "friendlyfire", "seeFriendlyInvisibles", "nametagVisibility", "deathMessageVisibility"}
                        );
                     }

                     if (strings.length == 5) {
                        if (strings[3].equalsIgnoreCase("color")) {
                           return getListOfStringsMatchingLastWord(strings, EnumChatFormatting.getValidValues(true, false));
                        }

                        if (strings[3].equalsIgnoreCase("nametagVisibility") || strings[3].equalsIgnoreCase("deathMessageVisibility")) {
                           return getListOfStringsMatchingLastWord(strings, Team.EnumVisible.getAllNames());
                        }

                        if (strings[3].equalsIgnoreCase("friendlyfire") || strings[3].equalsIgnoreCase("seeFriendlyInvisibles")) {
                           return getListOfStringsMatchingLastWord(strings, new String[]{"true", "false"});
                        }
                     }
                  }
               } else if (strings.length == 3) {
                  return getListOfStringsMatchingLastWord(strings, this.getScoreboard().getTeamNames());
               }
            }
         }

         return null;
      }
   }

   public void removeObjective(ICommandSender commandSender, String str) throws CommandException {
      this.getScoreboard().removeObjective(this.getObjective(str, false));
      notifyOperators(commandSender, this, "commands.scoreboard.objectives.remove.success", new Object[]{str});
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (!this.handleUserWildcards(commandSender, strings)) {
         if (strings.length < 1) {
            throw new WrongUsageException("commands.scoreboard.usage", new Object[0]);
         }

         if (strings[0].equalsIgnoreCase("objectives")) {
            if (strings.length == 1) {
               throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
            }

            if (strings[1].equalsIgnoreCase("list")) {
               this.listObjectives(commandSender);
            } else if (strings[1].equalsIgnoreCase("add")) {
               if (strings.length < 4) {
                  throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
               }

               this.addObjective(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("remove")) {
               if (strings.length != 3) {
                  throw new WrongUsageException("commands.scoreboard.objectives.remove.usage", new Object[0]);
               }

               this.removeObjective(commandSender, strings[2]);
            } else {
               if (!strings[1].equalsIgnoreCase("setdisplay")) {
                  throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
               }

               if (strings.length != 3 && strings.length != 4) {
                  throw new WrongUsageException("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
               }

               this.setObjectiveDisplay(commandSender, strings, 2);
            }
         } else if (strings[0].equalsIgnoreCase("players")) {
            if (strings.length == 1) {
               throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
            }

            if (strings[1].equalsIgnoreCase("list")) {
               if (strings.length > 3) {
                  throw new WrongUsageException("commands.scoreboard.players.list.usage", new Object[0]);
               }

               this.listPlayers(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("add")) {
               if (strings.length < 5) {
                  throw new WrongUsageException("commands.scoreboard.players.add.usage", new Object[0]);
               }

               this.setPlayer(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("remove")) {
               if (strings.length < 5) {
                  throw new WrongUsageException("commands.scoreboard.players.remove.usage", new Object[0]);
               }

               this.setPlayer(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("set")) {
               if (strings.length < 5) {
                  throw new WrongUsageException("commands.scoreboard.players.set.usage", new Object[0]);
               }

               this.setPlayer(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("reset")) {
               if (strings.length != 3 && strings.length != 4) {
                  throw new WrongUsageException("commands.scoreboard.players.reset.usage", new Object[0]);
               }

               this.resetPlayers(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("enable")) {
               if (strings.length != 4) {
                  throw new WrongUsageException("commands.scoreboard.players.enable.usage", new Object[0]);
               }

               this.enablePlayerTrigger(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("test")) {
               if (strings.length != 5 && strings.length != 6) {
                  throw new WrongUsageException("commands.scoreboard.players.test.usage", new Object[0]);
               }

               this.testPlayerScore(commandSender, strings, 2);
            } else {
               if (!strings[1].equalsIgnoreCase("operation")) {
                  throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
               }

               if (strings.length != 7) {
                  throw new WrongUsageException("commands.scoreboard.players.operation.usage", new Object[0]);
               }

               this.applyPlayerOperation(commandSender, strings, 2);
            }
         } else {
            if (!strings[0].equalsIgnoreCase("teams")) {
               throw new WrongUsageException("commands.scoreboard.usage", new Object[0]);
            }

            if (strings.length == 1) {
               throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
            }

            if (strings[1].equalsIgnoreCase("list")) {
               if (strings.length > 3) {
                  throw new WrongUsageException("commands.scoreboard.teams.list.usage", new Object[0]);
               }

               this.listTeams(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("add")) {
               if (strings.length < 3) {
                  throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
               }

               this.addTeam(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("remove")) {
               if (strings.length != 3) {
                  throw new WrongUsageException("commands.scoreboard.teams.remove.usage", new Object[0]);
               }

               this.removeTeam(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("empty")) {
               if (strings.length != 3) {
                  throw new WrongUsageException("commands.scoreboard.teams.empty.usage", new Object[0]);
               }

               this.emptyTeam(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("join")) {
               if (strings.length < 4 && (strings.length != 3 || !(commandSender instanceof EntityPlayer))) {
                  throw new WrongUsageException("commands.scoreboard.teams.join.usage", new Object[0]);
               }

               this.joinTeam(commandSender, strings, 2);
            } else if (strings[1].equalsIgnoreCase("leave")) {
               if (strings.length < 3 && !(commandSender instanceof EntityPlayer)) {
                  throw new WrongUsageException("commands.scoreboard.teams.leave.usage", new Object[0]);
               }

               this.leaveTeam(commandSender, strings, 2);
            } else {
               if (!strings[1].equalsIgnoreCase("option")) {
                  throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
               }

               if (strings.length != 4 && strings.length != 5) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
               }

               this.setTeamOption(commandSender, strings, 2);
            }
         }
      }
   }

   public void applyPlayerOperation(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      String entityName = getEntityName(commandSender, strings[n++]);
      ScoreObjective objective = this.getObjective(strings[n++], true);
      String str = strings[n++];
      String entityName2 = getEntityName(commandSender, strings[n++]);
      ScoreObjective objective2 = this.getObjective(strings[n], false);
      if (entityName.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{entityName, 40});
      } else if (entityName2.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{entityName2, 40});
      } else {
         Score valueFromObjective = scoreboard.getValueFromObjective(entityName, objective);
         if (!scoreboard.entityHasObjective(entityName2, objective2)) {
            throw new CommandException("commands.scoreboard.players.operation.notFound", new Object[]{objective2.getName(), entityName2});
         } else {
            Score valueFromObjective2 = scoreboard.getValueFromObjective(entityName2, objective2);
            if (str.equals("+=")) {
               valueFromObjective.setScorePoints(valueFromObjective.getScorePoints() + valueFromObjective2.getScorePoints());
            } else if (str.equals("-=")) {
               valueFromObjective.setScorePoints(valueFromObjective.getScorePoints() - valueFromObjective2.getScorePoints());
            } else if (str.equals("*=")) {
               valueFromObjective.setScorePoints(valueFromObjective.getScorePoints() * valueFromObjective2.getScorePoints());
            } else if (str.equals("/=")) {
               if (valueFromObjective2.getScorePoints() != 0) {
                  valueFromObjective.setScorePoints(valueFromObjective.getScorePoints() / valueFromObjective2.getScorePoints());
               }
            } else if (str.equals("%=")) {
               if (valueFromObjective2.getScorePoints() != 0) {
                  valueFromObjective.setScorePoints(valueFromObjective.getScorePoints() % valueFromObjective2.getScorePoints());
               }
            } else if (str.equals("=")) {
               valueFromObjective.setScorePoints(valueFromObjective2.getScorePoints());
            } else if (str.equals("<")) {
               valueFromObjective.setScorePoints(Math.min(valueFromObjective.getScorePoints(), valueFromObjective2.getScorePoints()));
            } else if (str.equals(">")) {
               valueFromObjective.setScorePoints(Math.max(valueFromObjective.getScorePoints(), valueFromObjective2.getScorePoints()));
            } else {
               if (!str.equals("><")) {
                  throw new CommandException("commands.scoreboard.players.operation.invalidOperation", new Object[]{str});
               }

               int scorePoints = valueFromObjective.getScorePoints();
               valueFromObjective.setScorePoints(valueFromObjective2.getScorePoints());
               valueFromObjective2.setScorePoints(scorePoints);
            }

            notifyOperators(commandSender, this, "commands.scoreboard.players.operation.success", new Object[0]);
         }
      }
   }

   public void enablePlayerTrigger(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      String playerName = getPlayerName(commandSender, strings[n++]);
      if (playerName.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{playerName, 40});
      } else {
         ScoreObjective objective = this.getObjective(strings[n], false);
         if (objective.getCriteria() != IScoreObjectiveCriteria.TRIGGER) {
            throw new CommandException("commands.scoreboard.players.enable.noTrigger", new Object[]{objective.getName()});
         } else {
            scoreboard.getValueFromObjective(playerName, objective).setLocked(false);
            notifyOperators(commandSender, this, "commands.scoreboard.players.enable.success", new Object[]{objective.getName(), playerName});
         }
      }
   }

   public void emptyTeam(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      ScorePlayerTeam team = this.getTeam(strings[n]);
      if (team != null) {
         ArrayList list = Lists.newArrayList(team.getMembershipCollection());
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ENTITIES, list.size());
         if (list.isEmpty()) {
            throw new CommandException("commands.scoreboard.teams.empty.alreadyEmpty", new Object[]{team.getRegisteredName()});
         }

         for (String str : (Iterable<String>)(list)) {
            scoreboard.removePlayerFromTeam(str, team);
         }

         notifyOperators(commandSender, this, "commands.scoreboard.teams.empty.success", new Object[]{list.size(), team.getRegisteredName()});
      }
   }

   public void addTeam(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      String str = strings[n++];
      Scoreboard scoreboard = this.getScoreboard();
      if (scoreboard.getTeam(str) != null) {
         throw new CommandException("commands.scoreboard.teams.add.alreadyExists", new Object[]{str});
      } else if (str.length() > 16) {
         throw new SyntaxErrorException("commands.scoreboard.teams.add.tooLong", new Object[]{str, 16});
      } else if (str.length() == 0) {
         throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
      } else {
         if (strings.length > n) {
            String unformattedText = getChatComponentFromNthArg(commandSender, strings, n).getUnformattedText();
            if (unformattedText.length() > 32) {
               throw new SyntaxErrorException("commands.scoreboard.teams.add.displayTooLong", new Object[]{unformattedText, 32});
            }

            if (unformattedText.length() > 0) {
               scoreboard.createTeam(str).setTeamName(unformattedText);
            } else {
               scoreboard.createTeam(str);
            }
         } else {
            scoreboard.createTeam(str);
         }

         notifyOperators(commandSender, this, "commands.scoreboard.teams.add.success", new Object[]{str});
      }
   }

   public List getObjectives(boolean flag) {
      Collection scoreObjectives = this.getScoreboard().getScoreObjectives();
      ArrayList list = Lists.newArrayList();

      for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(scoreObjectives)) {
         if (!flag || !scoreObjective.getCriteria().isReadOnly()) {
            list.add(scoreObjective.getName());
         }
      }

      return list;
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return !strings[0].equalsIgnoreCase("players")
         ? (strings[0].equalsIgnoreCase("teams") ? n == 2 : false)
         : (strings.length > 1 && strings[1].equalsIgnoreCase("operation") ? n == 2 || n == 5 : n == 2);
   }

   public void setTeamOption(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      ScorePlayerTeam team = this.getTeam(strings[n++]);
      if (team != null) {
         String str = strings[n++].toLowerCase();
         if (!str.equalsIgnoreCase("color")
            && !str.equalsIgnoreCase("friendlyfire")
            && !str.equalsIgnoreCase("seeFriendlyInvisibles")
            && !str.equalsIgnoreCase("nametagVisibility")
            && !str.equalsIgnoreCase("deathMessageVisibility")) {
            throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
         }

         if (strings.length == 4) {
            if (str.equalsIgnoreCase("color")) {
               throw new WrongUsageException(
                  "commands.scoreboard.teams.option.noValue", new Object[]{str, joinNiceStringFromCollection(EnumChatFormatting.getValidValues(true, false))}
               );
            }

            if (!str.equalsIgnoreCase("friendlyfire") && !str.equalsIgnoreCase("seeFriendlyInvisibles")) {
               if (!str.equalsIgnoreCase("nametagVisibility") && !str.equalsIgnoreCase("deathMessageVisibility")) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
               }

               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{str, joinNiceString(Team.EnumVisible.getAllNames())});
            }

            throw new WrongUsageException(
               "commands.scoreboard.teams.option.noValue", new Object[]{str, joinNiceStringFromCollection(Arrays.asList("true", "false"))}
            );
         }

         String str2 = strings[n];
         if (str.equalsIgnoreCase("color")) {
            EnumChatFormatting valueByName = EnumChatFormatting.getValueByName(str2);
            if (valueByName == null || valueByName.isFancyStyling()) {
               throw new WrongUsageException(
                  "commands.scoreboard.teams.option.noValue", new Object[]{str, joinNiceStringFromCollection(EnumChatFormatting.getValidValues(true, false))}
               );
            }

            team.setChatFormat(valueByName);
            team.setNamePrefix(valueByName.toString());
            team.setNameSuffix(EnumChatFormatting.RESET.toString());
         } else if (str.equalsIgnoreCase("friendlyfire")) {
            if (!str2.equalsIgnoreCase("true") && !str2.equalsIgnoreCase("false")) {
               throw new WrongUsageException(
                  "commands.scoreboard.teams.option.noValue", new Object[]{str, joinNiceStringFromCollection(Arrays.asList("true", "false"))}
               );
            }

            team.setAllowFriendlyFire(str2.equalsIgnoreCase("true"));
         } else if (str.equalsIgnoreCase("seeFriendlyInvisibles")) {
            if (!str2.equalsIgnoreCase("true") && !str2.equalsIgnoreCase("false")) {
               throw new WrongUsageException(
                  "commands.scoreboard.teams.option.noValue", new Object[]{str, joinNiceStringFromCollection(Arrays.asList("true", "false"))}
               );
            }

            team.setSeeFriendlyInvisiblesEnabled(str2.equalsIgnoreCase("true"));
         } else if (str.equalsIgnoreCase("nametagVisibility")) {
            Team.EnumVisible byName = Team.EnumVisible.getByName(str2);
            if (byName == null) {
               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{str, joinNiceString(Team.EnumVisible.getAllNames())});
            }

            team.setNameTagVisibility(byName);
         } else if (str.equalsIgnoreCase("deathMessageVisibility")) {
            Team.EnumVisible byName2 = Team.EnumVisible.getByName(str2);
            if (byName2 == null) {
               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{str, joinNiceString(Team.EnumVisible.getAllNames())});
            }

            team.setDeathMessageVisibility(byName2);
         }

         notifyOperators(commandSender, this, "commands.scoreboard.teams.option.success", new Object[]{str, team.getRegisteredName(), str2});
      }
   }

   public void listPlayers(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      if (strings.length > n) {
         String entityName = getEntityName(commandSender, strings[n]);
         Map objectivesForEntity = scoreboard.getObjectivesForEntity(entityName);
         commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, objectivesForEntity.size());
         if (objectivesForEntity.size() <= 0) {
            throw new CommandException("commands.scoreboard.players.list.player.empty", new Object[]{entityName});
         }

         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("commands.scoreboard.players.list.player.count", new Object[]{objectivesForEntity.size(), entityName});
         chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
         commandSender.addChatMessage(chatComponentTranslation);

         for (Score score : (Iterable<Score>)(objectivesForEntity.values())) {
            commandSender.addChatMessage(
               new ChatComponentTranslation(
                  "commands.scoreboard.players.list.player.entry",
                  new Object[]{score.getScorePoints(), score.getObjective().getDisplayName(), score.getObjective().getName()}
               )
            );
         }
      } else {
         Collection objectiveNames = scoreboard.getObjectiveNames();
         commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, objectiveNames.size());
         if (objectiveNames.size() <= 0) {
            throw new CommandException("commands.scoreboard.players.list.empty", new Object[0]);
         }

         ChatComponentTranslation chatComponentTranslation2 = new ChatComponentTranslation("commands.scoreboard.players.list.count", new Object[]{objectiveNames.size()});
         chatComponentTranslation2.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
         commandSender.addChatMessage(chatComponentTranslation2);
         commandSender.addChatMessage(new ChatComponentText(joinNiceString(objectiveNames.toArray())));
      }
   }

   public Scoreboard getScoreboard() {
      return MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();
   }

   public void listTeams(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      if (strings.length > n) {
         ScorePlayerTeam team = this.getTeam(strings[n]);
         if (team == null) {
            return;
         }

         Collection membershipCollection = team.getMembershipCollection();
         commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, membershipCollection.size());
         if (membershipCollection.size() <= 0) {
            throw new CommandException("commands.scoreboard.teams.list.player.empty", new Object[]{team.getRegisteredName()});
         }

         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation(
            "commands.scoreboard.teams.list.player.count", new Object[]{membershipCollection.size(), team.getRegisteredName()}
         );
         chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
         commandSender.addChatMessage(chatComponentTranslation);
         commandSender.addChatMessage(new ChatComponentText(joinNiceString(membershipCollection.toArray())));
      } else {
         Collection teams = scoreboard.getTeams();
         commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, teams.size());
         if (teams.size() <= 0) {
            throw new CommandException("commands.scoreboard.teams.list.empty", new Object[0]);
         }

         ChatComponentTranslation chatComponentTranslation2 = new ChatComponentTranslation("commands.scoreboard.teams.list.count", new Object[]{teams.size()});
         chatComponentTranslation2.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
         commandSender.addChatMessage(chatComponentTranslation2);

         for (ScorePlayerTeam scorePlayerTeam : (Iterable<ScorePlayerTeam>)(teams)) {
            commandSender.addChatMessage(
               new ChatComponentTranslation(
                  "commands.scoreboard.teams.list.entry", new Object[]{scorePlayerTeam.getRegisteredName(), scorePlayerTeam.getTeamName(), scorePlayerTeam.getMembershipCollection().size()}
               )
            );
         }
      }
   }

   @Override
   public String getCommandName() {
      return "scoreboard";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.scoreboard.usage";
   }

   public void setObjectiveDisplay(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      String str = strings[n++];
      int n2 = Scoreboard.getObjectiveDisplaySlotNumber(str);
      ScoreObjective scoreObjective = null;
      if (strings.length == 4) {
         scoreObjective = this.getObjective(strings[n], false);
      }

      if (n2 < 0) {
         throw new CommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[]{str});
      } else {
         scoreboard.setObjectiveInDisplaySlot(n2, scoreObjective);
         if (scoreObjective != null) {
            notifyOperators(
               commandSender, this, "commands.scoreboard.objectives.setdisplay.successSet", new Object[]{Scoreboard.getObjectiveDisplaySlot(n2), scoreObjective.getName()}
            );
         } else {
            notifyOperators(commandSender, this, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[]{Scoreboard.getObjectiveDisplaySlot(n2)});
         }
      }
   }

   public void removeTeam(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      ScorePlayerTeam team = this.getTeam(strings[n]);
      if (team != null) {
         scoreboard.removeTeam(team);
         notifyOperators(commandSender, this, "commands.scoreboard.teams.remove.success", new Object[]{team.getRegisteredName()});
      }
   }

   public ScoreObjective getObjective(String str, boolean flag) throws CommandException {
      ScoreObjective objective = this.getScoreboard().getObjective(str);
      if (objective == null) {
         throw new CommandException("commands.scoreboard.objectiveNotFound", new Object[]{str});
      } else if (flag && objective.getCriteria().isReadOnly()) {
         throw new CommandException("commands.scoreboard.objectiveReadOnly", new Object[]{str});
      } else {
         return objective;
      }
   }

   public void addObjective(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      String str = strings[n++];
      String str2 = strings[n++];
      Scoreboard scoreboard = this.getScoreboard();
      IScoreObjectiveCriteria scoreObjectiveCriteria = (IScoreObjectiveCriteria)IScoreObjectiveCriteria.INSTANCES.get(str2);
      if (scoreObjectiveCriteria == null) {
         throw new WrongUsageException("commands.scoreboard.objectives.add.wrongType", new Object[]{str2});
      } else if (scoreboard.getObjective(str) != null) {
         throw new CommandException("commands.scoreboard.objectives.add.alreadyExists", new Object[]{str});
      } else if (str.length() > 16) {
         throw new SyntaxErrorException("commands.scoreboard.objectives.add.tooLong", new Object[]{str, 16});
      } else if (str.length() == 0) {
         throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
      } else {
         if (strings.length > n) {
            String unformattedText = getChatComponentFromNthArg(commandSender, strings, n).getUnformattedText();
            if (unformattedText.length() > 32) {
               throw new SyntaxErrorException("commands.scoreboard.objectives.add.displayTooLong", new Object[]{unformattedText, 32});
            }

            if (unformattedText.length() > 0) {
               scoreboard.addScoreObjective(str, scoreObjectiveCriteria).setDisplayName(unformattedText);
            } else {
               scoreboard.addScoreObjective(str, scoreObjectiveCriteria);
            }
         } else {
            scoreboard.addScoreObjective(str, scoreObjectiveCriteria);
         }

         notifyOperators(commandSender, this, "commands.scoreboard.objectives.add.success", new Object[]{str});
      }
   }

   public ScorePlayerTeam getTeam(String str) throws CommandException {
      ScorePlayerTeam team = this.getScoreboard().getTeam(str);
      if (team == null) {
         throw new CommandException("commands.scoreboard.teamNotFound", new Object[]{str});
      } else {
         return team;
      }
   }

   private boolean handleUserWildcards(ICommandSender commandSender, String[] strings) throws CommandException {
      int n = -1;

      for (int i = 0; i < strings.length; i++) {
         if (this.isUsernameIndex(strings, i) && "*".equals(strings[i])) {
            if (n >= 0) {
               throw new CommandException("commands.scoreboard.noMultiWildcard", new Object[0]);
            }

            n = i;
         }
      }

      if (n < 0) {
         return false;
      } else {
         ArrayList list = Lists.newArrayList(this.getScoreboard().getObjectiveNames());
         String str = strings[n];
         ArrayList list2 = Lists.newArrayList();

         for (String str2 : (Iterable<String>)(list)) {
            strings[n] = str2;

            try {
               this.processCommand(commandSender, strings);
               list2.add(str2);
            } catch (CommandException e) {
               ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation(e.getMessage(), e.getErrorObjects());
               chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.RED);
               commandSender.addChatMessage(chatComponentTranslation);
            }
         }

         strings[n] = str;
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ENTITIES, list2.size());
         if (list2.size() == 0) {
            throw new WrongUsageException("commands.scoreboard.allMatchesFailed", new Object[0]);
         } else {
            return true;
         }
      }
   }

   public void joinTeam(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      String str = strings[n++];
      HashSet set = Sets.newHashSet();
      HashSet set2 = Sets.newHashSet();
      if (commandSender instanceof EntityPlayer && n == strings.length) {
         String name = getCommandSenderAsPlayer(commandSender).getName();
         if (scoreboard.addPlayerToTeam(name, str)) {
            set.add(name);
         } else {
            set2.add(name);
         }
      } else {
         while (n < strings.length) {
            String str2 = strings[n++];
            if (str2.startsWith("@")) {
               for (Entity entity : (Iterable<Entity>)(getEntityList(commandSender, str2))) {
                  String entityName = getEntityName(commandSender, entity.getUniqueID().toString());
                  if (scoreboard.addPlayerToTeam(entityName, str)) {
                     set.add(entityName);
                  } else {
                     set2.add(entityName);
                  }
               }
            } else {
               String entityName2 = getEntityName(commandSender, str2);
               if (scoreboard.addPlayerToTeam(entityName2, str)) {
                  set.add(entityName2);
               } else {
                  set2.add(entityName2);
               }
            }
         }
      }

      if (!set.isEmpty()) {
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ENTITIES, set.size());
         notifyOperators(
            commandSender, this, "commands.scoreboard.teams.join.success", new Object[]{set.size(), str, joinNiceString(set.toArray(new String[set.size()]))}
         );
      }

      if (!set2.isEmpty()) {
         throw new CommandException(
            "commands.scoreboard.teams.join.failure", new Object[]{set2.size(), str, joinNiceString(set2.toArray(new String[set2.size()]))}
         );
      }
   }

   public void listObjectives(ICommandSender commandSender) throws CommandException {
      Collection scoreObjectives = this.getScoreboard().getScoreObjectives();
      if (scoreObjectives.size() <= 0) {
         throw new CommandException("commands.scoreboard.objectives.list.empty", new Object[0]);
      } else {
         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("commands.scoreboard.objectives.list.count", new Object[]{scoreObjectives.size()});
         chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
         commandSender.addChatMessage(chatComponentTranslation);

         for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(scoreObjectives)) {
            commandSender.addChatMessage(
               new ChatComponentTranslation(
                  "commands.scoreboard.objectives.list.entry", new Object[]{scoreObjective.getName(), scoreObjective.getDisplayName(), scoreObjective.getCriteria().getName()}
               )
            );
         }
      }
   }

   public void resetPlayers(ICommandSender commandSender, String[] strings, int n) throws CommandException {
      Scoreboard scoreboard = this.getScoreboard();
      String entityName = getEntityName(commandSender, strings[n++]);
      if (strings.length > n) {
         ScoreObjective objective = this.getObjective(strings[n++], false);
         scoreboard.removeObjectiveFromEntity(entityName, objective);
         notifyOperators(commandSender, this, "commands.scoreboard.players.resetscore.success", new Object[]{objective.getName(), entityName});
      } else {
         scoreboard.removeObjectiveFromEntity(entityName, (ScoreObjective)null);
         notifyOperators(commandSender, this, "commands.scoreboard.players.reset.success", new Object[]{entityName});
      }
   }
}
