package net.minecraft.scoreboard;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldSavedData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScoreboardSaveData extends WorldSavedData {
   private static final Logger LOGGER = LogManager.getLogger();
   private Scoreboard theScoreboard;
   private NBTTagCompound delayedInitNbt;

   public void loadTeamPlayers(ScorePlayerTeam scorePlayerTeam, NBTTagList tagList) {
      for (int i = 0; i < tagList.tagCount(); i++) {
         this.theScoreboard.addPlayerToTeam(tagList.getStringTagAt(i), scorePlayerTeam.getRegisteredName());
      }
   }

   public void readObjectives(NBTTagList tagList) {
      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         IScoreObjectiveCriteria scoreObjectiveCriteria = (IScoreObjectiveCriteria)IScoreObjectiveCriteria.INSTANCES.get(compoundTagAt.getString("CriteriaName"));
         if (scoreObjectiveCriteria != null) {
            String string = compoundTagAt.getString("Name");
            if (string.length() > 16) {
               string = string.substring(0, 16);
            }

            ScoreObjective scoreObjective = this.theScoreboard.addScoreObjective(string, scoreObjectiveCriteria);
            scoreObjective.setDisplayName(compoundTagAt.getString("DisplayName"));
            scoreObjective.setRenderType(IScoreObjectiveCriteria.EnumRenderType.getByName(compoundTagAt.getString("RenderType")));
         }
      }
   }

   public void setScoreboard(Scoreboard scoreboard) {
      this.theScoreboard = scoreboard;
      if (this.delayedInitNbt != null) {
         this.readFromNBT(this.delayedInitNbt);
      }
   }

   public void readScores(NBTTagList tagList) {
      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         ScoreObjective objective = this.theScoreboard.getObjective(compoundTagAt.getString("Objective"));
         String string = compoundTagAt.getString("Name");
         if (string.length() > 40) {
            string = string.substring(0, 40);
         }

         Score valueFromObjective = this.theScoreboard.getValueFromObjective(string, objective);
         valueFromObjective.setScorePoints(compoundTagAt.getInteger("Score"));
         if (compoundTagAt.hasKey("Locked")) {
            valueFromObjective.setLocked(compoundTagAt.getBoolean("Locked"));
         }
      }
   }

   public ScoreboardSaveData(String str) {
      super(str);
   }

   public void readTeams(NBTTagList tagList) {
      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         String string = compoundTagAt.getString("Name");
         if (string.length() > 16) {
            string = string.substring(0, 16);
         }

         ScorePlayerTeam scorePlayerTeam = this.theScoreboard.createTeam(string);
         String string2 = compoundTagAt.getString("DisplayName");
         if (string2.length() > 32) {
            string2 = string2.substring(0, 32);
         }

         scorePlayerTeam.setTeamName(string2);
         if (compoundTagAt.hasKey("TeamColor", 8)) {
            scorePlayerTeam.setChatFormat(EnumChatFormatting.getValueByName(compoundTagAt.getString("TeamColor")));
         }

         scorePlayerTeam.setNamePrefix(compoundTagAt.getString("Prefix"));
         scorePlayerTeam.setNameSuffix(compoundTagAt.getString("Suffix"));
         if (compoundTagAt.hasKey("AllowFriendlyFire", 99)) {
            scorePlayerTeam.setAllowFriendlyFire(compoundTagAt.getBoolean("AllowFriendlyFire"));
         }

         if (compoundTagAt.hasKey("SeeFriendlyInvisibles", 99)) {
            scorePlayerTeam.setSeeFriendlyInvisiblesEnabled(compoundTagAt.getBoolean("SeeFriendlyInvisibles"));
         }

         if (compoundTagAt.hasKey("NameTagVisibility", 8)) {
            Team.EnumVisible byName = Team.EnumVisible.getByName(compoundTagAt.getString("NameTagVisibility"));
            if (byName != null) {
               scorePlayerTeam.setNameTagVisibility(byName);
            }
         }

         if (compoundTagAt.hasKey("DeathMessageVisibility", 8)) {
            Team.EnumVisible byName2 = Team.EnumVisible.getByName(compoundTagAt.getString("DeathMessageVisibility"));
            if (byName2 != null) {
               scorePlayerTeam.setDeathMessageVisibility(byName2);
            }
         }

         this.loadTeamPlayers(scorePlayerTeam, compoundTagAt.getTagList("Players", 8));
      }
   }

   public NBTTagList teamsToNbt() {
      NBTTagList tagList = new NBTTagList();

      for (ScorePlayerTeam scorePlayerTeam : (Iterable<ScorePlayerTeam>)(this.theScoreboard.getTeams())) {
         NBTTagCompound tag = new NBTTagCompound();
         tag.setString("Name", scorePlayerTeam.getRegisteredName());
         tag.setString("DisplayName", scorePlayerTeam.getTeamName());
         if (scorePlayerTeam.getChatFormat().getColorIndex() >= 0) {
            tag.setString("TeamColor", scorePlayerTeam.getChatFormat().getFriendlyName());
         }

         tag.setString("Prefix", scorePlayerTeam.getColorPrefix());
         tag.setString("Suffix", scorePlayerTeam.getColorSuffix());
         tag.setBoolean("AllowFriendlyFire", scorePlayerTeam.getAllowFriendlyFire());
         tag.setBoolean("SeeFriendlyInvisibles", scorePlayerTeam.getSeeFriendlyInvisiblesEnabled());
         tag.setString("NameTagVisibility", scorePlayerTeam.getNameTagVisibility().internalName);
         tag.setString("DeathMessageVisibility", scorePlayerTeam.getDeathMessageVisibility().internalName);
         NBTTagList tagList2 = new NBTTagList();

         for (String str : (Iterable<String>)(scorePlayerTeam.getMembershipCollection())) {
            tagList2.appendTag(new NBTTagString(str));
         }

         tag.setTag("Players", tagList2);
         tagList.appendTag(tag);
      }

      return tagList;
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      if (this.theScoreboard == null) {
         this.delayedInitNbt = tag;
      } else {
         this.readObjectives(tag.getTagList("Objectives", 10));
         this.readScores(tag.getTagList("PlayerScores", 10));
         if (tag.hasKey("DisplaySlots", 10)) {
            this.readDisplayConfig(tag.getCompoundTag("DisplaySlots"));
         }

         if (tag.hasKey("Teams", 9)) {
            this.readTeams(tag.getTagList("Teams", 10));
         }
      }
   }

   public NBTTagList scoresToNbt() {
      NBTTagList tagList = new NBTTagList();

      for (Score score : (Iterable<Score>)(this.theScoreboard.getScores())) {
         if (score.getObjective() != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("Name", score.getPlayerName());
            tag.setString("Objective", score.getObjective().getName());
            tag.setInteger("Score", score.getScorePoints());
            tag.setBoolean("Locked", score.isLocked());
            tagList.appendTag(tag);
         }
      }

      return tagList;
   }

   public NBTTagList objectivesToNbt() {
      NBTTagList tagList = new NBTTagList();

      for (ScoreObjective scoreObjective : (Iterable<ScoreObjective>)(this.theScoreboard.getScoreObjectives())) {
         if (scoreObjective.getCriteria() != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("Name", scoreObjective.getName());
            tag.setString("CriteriaName", scoreObjective.getCriteria().getName());
            tag.setString("DisplayName", scoreObjective.getDisplayName());
            tag.setString("RenderType", scoreObjective.getRenderType().getRenderType());
            tagList.appendTag(tag);
         }
      }

      return tagList;
   }

   public void writeDisplayConfig(NBTTagCompound tag) {
      NBTTagCompound tag2 = new NBTTagCompound();
      boolean flag = false;

      for (int i = 0; i < 19; i++) {
         ScoreObjective objectiveInDisplaySlot = this.theScoreboard.getObjectiveInDisplaySlot(i);
         if (objectiveInDisplaySlot != null) {
            tag2.setString("slot_" + i, objectiveInDisplaySlot.getName());
            flag = true;
         }
      }

      if (flag) {
         tag.setTag("DisplaySlots", tag2);
      }
   }

   public ScoreboardSaveData() {
      this("scoreboard");
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      if (this.theScoreboard == null) {
         LOGGER.warn("Tried to save scoreboard without having a scoreboard...");
      } else {
         tag.setTag("Objectives", this.objectivesToNbt());
         tag.setTag("PlayerScores", this.scoresToNbt());
         tag.setTag("Teams", this.teamsToNbt());
         this.writeDisplayConfig(tag);
      }
   }

   public void readDisplayConfig(NBTTagCompound tag) {
      for (int i = 0; i < 19; i++) {
         if (tag.hasKey("slot_" + i, 8)) {
            String string = tag.getString("slot_" + i);
            ScoreObjective objective = this.theScoreboard.getObjective(string);
            this.theScoreboard.setObjectiveInDisplaySlot(i, objective);
         }
      }
   }
}
