package net.minecraft.scoreboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.augustus.ClientManagers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class Scoreboard {
   private final Map scoreObjectives = Maps.newHashMap();
   private final Map scoreObjectiveCriterias = Maps.newHashMap();
   private final Map entitiesScoreObjectives = Maps.newHashMap();
   private final ScoreObjective[] objectiveDisplaySlots = new ScoreObjective[19];
   private final Map teams = Maps.newHashMap();
   private final Map teamMemberships = Maps.newHashMap();
   private static String[] displaySlots = null;

   public void broadcastScoreUpdate(String str, ScoreObjective scoreObjective) {
   }

   public void broadcastTeamCreated(ScorePlayerTeam scorePlayerTeam) {
   }

   public ScorePlayerTeam createTeam(String str) {
      if (str.length() > 16) {
         throw new IllegalArgumentException("The team name '" + str + "' is too long!");
      } else {
         ScorePlayerTeam team = this.getTeam(str);
         if (team != null) {
            throw new IllegalArgumentException("A team with the name '" + str + "' already exists!");
         } else {
            team = new ScorePlayerTeam(this, str);
            this.teams.put(str, team);
            this.broadcastTeamCreated(team);
            return team;
         }
      }
   }

   public Collection getObjectiveNames() {
      return this.entitiesScoreObjectives.keySet();
   }

   public Collection getSortedScores(ScoreObjective scoreObjective) {
      ArrayList list = Lists.newArrayList();

      for (Map map : (Iterable<Map>)(this.entitiesScoreObjectives.values())) {
         Score score = (Score)map.get(scoreObjective);
         if (score != null) {
            list.add(score);
         }
      }

      list.sort(Score.scoreComparator);
      return list;
   }

   public void removeObjective(ScoreObjective scoreObjective) {
      this.scoreObjectives.remove(scoreObjective.getName());

      for (int i = 0; i < 19; i++) {
         if (this.getObjectiveInDisplaySlot(i) == scoreObjective) {
            this.setObjectiveInDisplaySlot(i, (ScoreObjective)null);
         }
      }

      List list = (List)this.scoreObjectiveCriterias.get(scoreObjective.getCriteria());
      if (list != null) {
         list.remove(scoreObjective);
      }

      for (Map map : (Iterable<Map>)(this.entitiesScoreObjectives.values())) {
         map.remove(scoreObjective);
      }

      this.onScoreObjectiveRemoved(scoreObjective);
   }

   public static String[] getDisplaySlotStrings() {
      if (displaySlots == null) {
         displaySlots = new String[19];

         for (int i = 0; i < 19; i++) {
            displaySlots[i] = getObjectiveDisplaySlot(i);
         }
      }

      return displaySlots;
   }

   public Collection getScores() {
      Collection values = this.entitiesScoreObjectives.values();
      ArrayList list = Lists.newArrayList();

      for (Map map : (Iterable<Map>)(values)) {
         list.addAll(map.values());
      }

      return list;
   }

   public void removeEntity(Entity entity) {
      if (entity != null && !(entity instanceof EntityPlayer) && !entity.isEntityAlive()) {
         String str = entity.getUniqueID().toString();
         this.removeObjectiveFromEntity(str, (ScoreObjective)null);
         this.removePlayerFromTeams(str);
      }
   }

   public Map getObjectivesForEntity(String str) {
      Object obj = (Map)this.entitiesScoreObjectives.get(str);
      if (obj == null) {
         obj = Maps.newHashMap();
      }

      return (Map)obj;
   }

   public void onScoreUpdated(Score score) {
   }

   public boolean entityHasObjective(String str, ScoreObjective scoreObjective) {
      Map map = (Map)this.entitiesScoreObjectives.get(str);
      if (map == null) {
         return false;
      } else {
         return ((Score)map.get(scoreObjective)) != null;
      }
   }

   public void removePlayerFromTeam(String str, ScorePlayerTeam scorePlayerTeam) {
      if (this.getPlayersTeam(str) != scorePlayerTeam) {
         if (!ClientManagers.moduleManager.exploitFixer.hycraft.getValue()) {
            throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team '" + scorePlayerTeam.getRegisteredName() + "'.");
         }
      } else {
         this.teamMemberships.remove(str);
         scorePlayerTeam.getMembershipCollection().remove(str);
      }
   }

   public ScoreObjective getObjective(String str) {
      return (ScoreObjective)this.scoreObjectives.get(str);
   }

   public Collection getTeamNames() {
      return this.teams.keySet();
   }

   public Collection getScoreObjectives() {
      return this.scoreObjectives.values();
   }

   public void onScoreObjectiveAdded(ScoreObjective scoreObjective) {
   }

   public boolean addPlayerToTeam(String str, String str2) {
      if (str.length() > 40) {
         throw new IllegalArgumentException("The player name '" + str + "' is too long!");
      } else if (!this.teams.containsKey(str2)) {
         return false;
      } else {
         ScorePlayerTeam team = this.getTeam(str2);
         if (this.getPlayersTeam(str) != null) {
            this.removePlayerFromTeams(str);
         }

         this.teamMemberships.put(str, team);
         team.getMembershipCollection().add(str);
         return true;
      }
   }

   public void removeObjectiveFromEntity(String str, ScoreObjective scoreObjective) {
      if (scoreObjective == null) {
         if (((Map)this.entitiesScoreObjectives.remove(str)) != null) {
            this.broadcastScoreUpdate(str);
         }
      } else {
         Map map2 = (Map)this.entitiesScoreObjectives.get(str);
         if (map2 != null) {
            Score score = (Score)map2.remove(scoreObjective);
            if (map2.size() < 1) {
               if (((Map)this.entitiesScoreObjectives.remove(str)) != null) {
                  this.broadcastScoreUpdate(str);
               }
            } else if (score != null) {
               this.broadcastScoreUpdate(str, scoreObjective);
            }
         }
      }
   }

   public void setObjectiveInDisplaySlot(int n, ScoreObjective scoreObjective) {
      this.objectiveDisplaySlots[n] = scoreObjective;
   }

   public void onScoreObjectiveRemoved(ScoreObjective scoreObjective) {
   }

   public void sendTeamUpdate(ScorePlayerTeam scorePlayerTeam) {
   }

   public ScorePlayerTeam getTeam(String str) {
      return (ScorePlayerTeam)this.teams.get(str);
   }

   public void broadcastScoreUpdate(String str) {
   }

   public boolean removePlayerFromTeams(String str) {
      ScorePlayerTeam playersTeam = this.getPlayersTeam(str);
      if (playersTeam != null) {
         this.removePlayerFromTeam(str, playersTeam);
         return true;
      } else {
         return false;
      }
   }

   public void broadcastTeamRemove(ScorePlayerTeam scorePlayerTeam) {
   }

   public ScoreObjective getObjectiveInDisplaySlot(int n) {
      return this.objectiveDisplaySlots[n];
   }

   public Collection getObjectivesFromCriteria(IScoreObjectiveCriteria scoreObjectiveCriteria) {
      Collection items = (Collection)this.scoreObjectiveCriterias.get(scoreObjectiveCriteria);
      return items == null ? Lists.newArrayList() : Lists.newArrayList(items);
   }

   public static String getObjectiveDisplaySlot(int n) {
      switch (n) {
         case 0:
            return "list";
         case 1:
            return "sidebar";
         case 2:
            return "belowName";
         default:
            if (n >= 3 && n <= 18) {
               EnumChatFormatting enumChatFormatting = EnumChatFormatting.fromColorIndex(n - 3);
               if (enumChatFormatting != null && enumChatFormatting != EnumChatFormatting.RESET) {
                  return "sidebar.team." + enumChatFormatting.getFriendlyName();
               }
            }

            return null;
      }
   }

   public Score getValueFromObjective(String str, ScoreObjective scoreObjective) {
      if (str.length() > 40) {
         throw new IllegalArgumentException("The player name '" + str + "' is too long!");
      } else {
         Map map = (Map)this.entitiesScoreObjectives.get(str);
         if (map == null) {
            map = Maps.newHashMap();
            this.entitiesScoreObjectives.put(str, map);
         }

         Score score = (Score)map.get(scoreObjective);
         if (score == null) {
            score = new Score(this, scoreObjective, str);
            map.put(scoreObjective, score);
         }

         return score;
      }
   }

   public ScorePlayerTeam getPlayersTeam(String str) {
      return (ScorePlayerTeam)this.teamMemberships.get(str);
   }

   public static int getObjectiveDisplaySlotNumber(String str) {
      if (str.equalsIgnoreCase("list")) {
         return 0;
      } else if (str.equalsIgnoreCase("sidebar")) {
         return 1;
      } else if (str.equalsIgnoreCase("belowName")) {
         return 2;
      } else {
         if (str.startsWith("sidebar.team.")) {
            EnumChatFormatting valueByName = EnumChatFormatting.getValueByName(str.substring("sidebar.team.".length()));
            if (valueByName != null && valueByName.getColorIndex() >= 0) {
               return valueByName.getColorIndex() + 3;
            }
         }

         return -1;
      }
   }

   public void onObjectiveDisplayNameChanged(ScoreObjective scoreObjective) {
   }

   public void removeTeam(ScorePlayerTeam scorePlayerTeam) {
      this.teams.remove(scorePlayerTeam.getRegisteredName());

      for (String str : (Iterable<String>)(scorePlayerTeam.getMembershipCollection())) {
         this.teamMemberships.remove(str);
      }

      this.broadcastTeamRemove(scorePlayerTeam);
   }

   public ScoreObjective addScoreObjective(String str, IScoreObjectiveCriteria scoreObjectiveCriteria) {
      if (str.length() > 16) {
         throw new IllegalArgumentException("The objective name '" + str + "' is too long!");
      } else {
         ScoreObjective objective = this.getObjective(str);
         if (objective != null) {
            throw new IllegalArgumentException("An objective with the name '" + str + "' already exists!");
         } else {
            objective = new ScoreObjective(this, str, scoreObjectiveCriteria);
            List list = (List)this.scoreObjectiveCriterias.get(scoreObjectiveCriteria);
            if (list == null) {
               list = Lists.newArrayList();
               this.scoreObjectiveCriterias.put(scoreObjectiveCriteria, list);
            }

            list.add(objective);
            this.scoreObjectives.put(str, objective);
            this.onScoreObjectiveAdded(objective);
            return objective;
         }
      }
   }

   public Collection getTeams() {
      return this.teams.values();
   }

}
