package client.util;

import client.setting.ClientSettingsBlackType;
import client.setting.MovementBooleanProperty;
import client.setting.TabListIntegerType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuxiliaryStringEntryHandler extends AdapterStringEntry {
   public static final Logger logger = LogManager.getLogger();
   public ThePlayerNameManager thePlayerNameManager;
   public NBTTagCompound nBTTagCompound;

   public void applyNBTTagListByNBTTagList(NBTTagList nBTTagList) {
      for (int index = 0; index < nBTTagList.getInt(); index++) {
         NBTTagCompound nBTTagCompound2 = nBTTagList.getNBTTagCompound(index);
         ScoreboardData scoreboardData = this.thePlayerNameManager.get(nBTTagCompound2.resolveText("Objective"));
         String text = nBTTagCompound2.resolveText("Name");
         if (text.length() > 40) {
            text = text.substring(0, 40);
         }

         ComparatorEntry comparatorEntry = this.thePlayerNameManager.getComparatorEntry(text, scoreboardData);
         comparatorEntry.applyIndex(nBTTagCompound2.computeCount("Score"));
         if (nBTTagCompound2.containsKey("Locked")) {
            comparatorEntry.setEnabled(nBTTagCompound2.matchesText("Locked"));
         }
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      if (this.thePlayerNameManager == null) {
         logger.warn("Tried to save scoreboard without having a scoreboard...");
      } else {
         tagCompound.applyText("Objectives", this.getNBTTagList());
         tagCompound.applyText("PlayerScores", this.getNBTTagListAsNBTTagList());
         tagCompound.applyText("Teams", this.getNBTTagList2());
         this.applyNBTTagCompound3(tagCompound);
      }
   }

   public NBTTagList getNBTTagListAsNBTTagList() {
      NBTTagList nBTTagList = new NBTTagList();

      for (ComparatorEntry comparatorEntry : (Iterable<ComparatorEntry>)(Iterable<?>)this.thePlayerNameManager.getCollection()) {
         if (comparatorEntry.getScoreboardData() != null) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.put("Name", comparatorEntry.getLabelText());
            tagCompound.put("Objective", comparatorEntry.getScoreboardData().getLabelText2());
            tagCompound.put("Score", comparatorEntry.getIndex());
            tagCompound.applyText("Locked", comparatorEntry.isEnabled());
            nBTTagList.applyLISTManager(tagCompound);
         }
      }

      return nBTTagList;
   }

   public NBTTagList getNBTTagList() {
      NBTTagList nBTTagList = new NBTTagList();

      for (ScoreboardData scoreboardData : (Iterable<ScoreboardData>)(Iterable<?>)this.thePlayerNameManager.valuesAsCollection()) {
         if (scoreboardData.getHealthHandler() != null) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.put("Name", scoreboardData.getLabelText2());
            tagCompound.put("CriteriaName", scoreboardData.getHealthHandler().getLabelText());
            tagCompound.put("DisplayName", scoreboardData.getLabelText());
            tagCompound.put("RenderType", scoreboardData.getTabListIntegerType().getLabelText());
            nBTTagList.applyLISTManager(tagCompound);
         }
      }

      return nBTTagList;
   }

   public void applyNBTTagList(NBTTagList nBTTagList) {
      for (int index = 0; index < nBTTagList.getInt(); index++) {
         NBTTagCompound nBTTagCompound2 = nBTTagList.getNBTTagCompound(index);
         String text = nBTTagCompound2.resolveText("Name");
         if (text.length() > 16) {
            text = text.substring(0, 16);
         }

         ScorePlayerTeam scorePlayerTeam = this.thePlayerNameManager.resolveScorePlayerTeam(text);
         String text2 = nBTTagCompound2.resolveText("DisplayName");
         if (text2.length() > 32) {
            text2 = text2.substring(0, 32);
         }

         scorePlayerTeam.applyText2(text2);
         if (nBTTagCompound2.matchesText("TeamColor", 8)) {
            scorePlayerTeam.setClientSettingsBlackType(ClientSettingsBlackType.resolveClientSettingsBlackType(nBTTagCompound2.resolveText("TeamColor")));
         }

         scorePlayerTeam.applyText(nBTTagCompound2.resolveText("Prefix"));
         scorePlayerTeam.applyTextByText(nBTTagCompound2.resolveText("Suffix"));
         if (nBTTagCompound2.matchesText("AllowFriendlyFire", 99)) {
            scorePlayerTeam.setEnabled(nBTTagCompound2.matchesText("AllowFriendlyFire"));
         }

         if (nBTTagCompound2.matchesText("SeeFriendlyInvisibles", 99)) {
            scorePlayerTeam.setEnabledByFlag(nBTTagCompound2.matchesText("SeeFriendlyInvisibles"));
         }

         if (nBTTagCompound2.matchesText("NameTagVisibility", 8)) {
            MovementBooleanProperty movementBooleanProperty = MovementBooleanProperty.get(nBTTagCompound2.resolveText("NameTagVisibility"));
            if (movementBooleanProperty != null) {
               scorePlayerTeam.applyMovementBooleanPropertyByMovementBooleanP(movementBooleanProperty);
            }
         }

         if (nBTTagCompound2.matchesText("DeathMessageVisibility", 8)) {
            MovementBooleanProperty movementBooleanProperty2 = MovementBooleanProperty.get(nBTTagCompound2.resolveText("DeathMessageVisibility"));
            if (movementBooleanProperty2 != null) {
               scorePlayerTeam.applyMovementBooleanProperty(movementBooleanProperty2);
            }
         }

         this.applyScorePlayerTeam(scorePlayerTeam, nBTTagCompound2.getExtendedLoggerEntry("Players", 8));
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      if (this.thePlayerNameManager == null) {
         this.nBTTagCompound = tagCompound;
      } else {
         this.applyNBTTagList2(tagCompound.getExtendedLoggerEntry("Objectives", 10));
         this.applyNBTTagListByNBTTagList(tagCompound.getExtendedLoggerEntry("PlayerScores", 10));
         if (tagCompound.matchesText("DisplaySlots", 10)) {
            this.applyNBTTagCompound2(tagCompound.getNBTTagCompound("DisplaySlots"));
         }

         if (tagCompound.matchesText("Teams", 9)) {
            this.applyNBTTagList(tagCompound.getExtendedLoggerEntry("Teams", 10));
         }
      }
   }

   public void applyNBTTagCompound3(NBTTagCompound tagCompound) {
      NBTTagCompound tagCompound2 = new NBTTagCompound();
      boolean flag = false;

      for (int index = 0; index < 19; index++) {
         ScoreboardData customItemEntry = this.thePlayerNameManager.getCustomItemEntry(index);
         if (customItemEntry != null) {
            tagCompound2.put("slot_" + index, customItemEntry.getLabelText2());
            flag = true;
         }
      }

      if (flag) {
         tagCompound.applyText("DisplaySlots", tagCompound2);
      }
   }

   public void applyNBTTagList2(NBTTagList nBTTagList) {
      for (int index = 0; index < nBTTagList.getInt(); index++) {
         NBTTagCompound nBTTagCompound2 = nBTTagList.getNBTTagCompound(index);
         HealthHandler healthHandler = (HealthHandler)HealthHandler.map.get(nBTTagCompound2.resolveText("CriteriaName"));
         if (healthHandler != null) {
            String text = nBTTagCompound2.resolveText("Name");
            if (text.length() > 16) {
               text = text.substring(0, 16);
            }

            ScoreboardData scoreboardData = this.thePlayerNameManager.resolveScoreboardData(text, healthHandler);
            scoreboardData.applyText(nBTTagCompound2.resolveText("DisplayName"));
            scoreboardData.applyTabListIntegerType(TabListIntegerType.get(nBTTagCompound2.resolveText("RenderType")));
         }
      }
   }

   public void applyThePlayerNameManager(ThePlayerNameManager thePlayerNameManager2) {
      this.thePlayerNameManager = thePlayerNameManager2;
      if (this.nBTTagCompound != null) {
         this.applyNBTTagCompoundByNBTTagCompound(this.nBTTagCompound);
      }
   }

   public NBTTagList getNBTTagList2() {
      NBTTagList nBTTagList = new NBTTagList();

      for (ScorePlayerTeam scorePlayerTeam : (Iterable<ScorePlayerTeam>)(Iterable<?>)this.thePlayerNameManager.values()) {
         NBTTagCompound tagCompound = new NBTTagCompound();
         tagCompound.put("Name", scorePlayerTeam.getLabelText2());
         tagCompound.put("DisplayName", scorePlayerTeam.getText2());
         if (scorePlayerTeam.getClientSettingsBlackType().getIndex() >= 0) {
            tagCompound.put("TeamColor", scorePlayerTeam.getClientSettingsBlackType().getText());
         }

         tagCompound.put("Prefix", scorePlayerTeam.getText());
         tagCompound.put("Suffix", scorePlayerTeam.getLabelText());
         tagCompound.applyText("AllowFriendlyFire", scorePlayerTeam.isEnabled());
         tagCompound.applyText("SeeFriendlyInvisibles", scorePlayerTeam.isEnabled2());
         tagCompound.put("NameTagVisibility", scorePlayerTeam.getMovementBooleanProperty2().labelText);
         tagCompound.put("DeathMessageVisibility", scorePlayerTeam.getMovementBooleanProperty().labelText);
         NBTTagList nBTTagList2 = new NBTTagList();

         for (String text : (Iterable<String>)(Iterable<?>)scorePlayerTeam.getCollection()) {
            nBTTagList2.applyLISTManager(new HandlerStringEntry(text));
         }

         tagCompound.applyText("Players", nBTTagList2);
         nBTTagList.applyLISTManager(tagCompound);
      }

      return nBTTagList;
   }

   public AuxiliaryStringEntryHandler(String text) {
      super(text);
   }

   public void applyScorePlayerTeam(ScorePlayerTeam scorePlayerTeam, NBTTagList nBTTagList) {
      for (int index = 0; index < nBTTagList.getInt(); index++) {
         this.thePlayerNameManager.matchesText(nBTTagList.resolveText(index), scorePlayerTeam.getLabelText2());
      }
   }

   public void applyNBTTagCompound2(NBTTagCompound tagCompound) {
      for (int index = 0; index < 19; index++) {
         if (tagCompound.matchesText("slot_" + index, 8)) {
            String text = tagCompound.resolveText("slot_" + index);
            ScoreboardData scoreboardData = this.thePlayerNameManager.get(text);
            this.thePlayerNameManager.applyScoreboardData(index, scoreboardData);
         }
      }
   }

   public AuxiliaryStringEntryHandler() {
      this("scoreboard");
   }
}
