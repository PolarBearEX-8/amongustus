package client.util;

import client.ClientGlobals;
import client.setting.ClientSettingsBlackType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class ThePlayerNameManager {
   public final Map map5 = Maps.newHashMap();
   public final Map map4 = Maps.newHashMap();
   public final Map map = Maps.newHashMap();
   public final ScoreboardData[] scoreboardDataArray = new ScoreboardData[19];
   public final Map map3 = Maps.newHashMap();
   public final Map map2 = Maps.newHashMap();
   public static String[] stringArray = null;

   public Collection keySet() {
      return this.map3.keySet();
   }

   public Collection resolveCollection(ScoreboardData scoreboardData) {
      ArrayList list = Lists.newArrayList();
      Iterator iterator = this.map.values().iterator();

      while (iterator.hasNext()) {
         ComparatorEntry comparatorEntry = (ComparatorEntry)((Map)iterator.next()).get(scoreboardData);
         if (comparatorEntry != null) {
            list.add(comparatorEntry);
         }
      }

      list.sort(ComparatorEntry.comparator);
      return list;
   }

   public void applyScoreboardData(int count, ScoreboardData scoreboardData) {
      this.scoreboardDataArray[count] = scoreboardData;
   }

   public static String[] getTextArray() {
      if (stringArray == null) {
         stringArray = new String[19];

         for (int index = 0; index < 19; index++) {
            stringArray[index] = resolveText(index);
         }
      }

      return stringArray;
   }

   public ScorePlayerTeam getByText(String text) {
      return (ScorePlayerTeam)this.map3.get(text);
   }

   public boolean matchesText(String text) {
      ScorePlayerTeam scorePlayerTeam = this.getScorePlayerTeam(text);
      if (scorePlayerTeam != null) {
         this.applyText(text, scorePlayerTeam);
         return true;
      } else {
         return false;
      }
   }

   public void applyScoreboardDataByScoreboardData(ScoreboardData scoreboardData) {
   }

   public void applyText(String text, ScoreboardData scoreboardData) {
      if (scoreboardData == null) {
         if ((Map)this.map.remove(text) != null) {
            this.applyText(text);
         }
      } else {
         Map map6 = (Map)this.map.get(text);
         if (map6 != null) {
            ComparatorEntry comparatorEntry = (ComparatorEntry)map6.remove(scoreboardData);
            if (map6.size() < 1) {
               if ((Map)this.map.remove(text) != null) {
                  this.applyText(text);
               }
            } else if (comparatorEntry != null) {
               this.applyTextByText(text, scoreboardData);
            }
         }
      }
   }

   public Collection getCollection() {
      Collection values = this.map.values();
      ArrayList list = Lists.newArrayList();

      for (Map map6 : (Iterable<Map>)(Iterable)values) {
         list.addAll(map6.values());
      }

      return list;
   }

   public ScoreboardData get(String text) {
      return (ScoreboardData)this.map5.get(text);
   }

   public void applyText(String text) {
   }

   public ScoreboardData getCustomItemEntry(int count) {
      return this.scoreboardDataArray[count];
   }

   public void applyScorePlayerTeam(ScorePlayerTeam scorePlayerTeam) {
      this.map3.remove(scorePlayerTeam.getLabelText2());

      for (String text : (Iterable<String>)(Iterable)scorePlayerTeam.getCollection()) {
         this.map2.remove(text);
      }

      this.applyScorePlayerTeamByScorePlayerTeam(scorePlayerTeam);
   }

   public boolean matchesText(String text, ScoreboardData scoreboardData) {
      Map map6 = (Map)this.map.get(text);
      return map6 == null ? false : (ComparatorEntry)map6.get(scoreboardData) != null;
   }

   public void applyScoreboardData(ScoreboardData scoreboardData) {
   }

   public void applyEntityByEntity(Entity entity) {
      if (entity != null && !(entity instanceof EntityPlayer) && !entity.canExecute()) {
         String text = entity.getEntityUniqueID().toString();
         this.applyText(text, (ScoreboardData)null);
         this.matchesText(text);
      }
   }

   public Collection values() {
      return this.map3.values();
   }

   public Collection resolveCollection(HealthHandler healthHandler) {
      Collection collection = (Collection)this.map4.get(healthHandler);
      return collection == null ? Lists.newArrayList() : Lists.newArrayList(collection);
   }

   public void applyScorePlayerTeam2(ScorePlayerTeam scorePlayerTeam) {
   }

   public ComparatorEntry getComparatorEntry(String text, ScoreboardData scoreboardData) {
      if (text.length() > 40) {
         throw new IllegalArgumentException("The player name '" + text + "' is too long!");
      } else {
         Map entry = (Map)this.map.get(text);
         if (entry == null) {
            entry = Maps.newHashMap();
            this.map.put(text, entry);
         }

         ComparatorEntry comparatorEntry = (ComparatorEntry)entry.get(scoreboardData);
         if (comparatorEntry == null) {
            comparatorEntry = new ComparatorEntry(this, scoreboardData, text);
            entry.put(scoreboardData, comparatorEntry);
         }

         return comparatorEntry;
      }
   }

   public void applyTextByText(String text, ScoreboardData scoreboardData) {
   }

   public ScorePlayerTeam resolveScorePlayerTeam(String text) {
      if (text.length() > 16) {
         throw new IllegalArgumentException("The team name '" + text + "' is too long!");
      } else if (this.getByText(text) != null) {
         throw new IllegalArgumentException("A team with the name '" + text + "' already exists!");
      } else {
         ScorePlayerTeam scorePlayerTeam = new ScorePlayerTeam(this, text);
         this.map3.put(text, scorePlayerTeam);
         this.applyScorePlayerTeam3(scorePlayerTeam);
         return scorePlayerTeam;
      }
   }

   public void applyScoreboardData3(ScoreboardData scoreboardData) {
      this.map5.remove(scoreboardData.getLabelText2());

      for (int index = 0; index < 19; index++) {
         if (this.getCustomItemEntry(index) == scoreboardData) {
            this.applyScoreboardData(index, (ScoreboardData)null);
         }
      }

      List list = (List)this.map4.get(scoreboardData.getHealthHandler());
      if (list != null) {
         list.remove(scoreboardData);
      }

      Iterator iterator = this.map.values().iterator();

      while (iterator.hasNext()) {
         ((Map)iterator.next()).remove(scoreboardData);
      }

      this.applyScoreboardData(scoreboardData);
   }

   public ScorePlayerTeam getScorePlayerTeam(String text) {
      return (ScorePlayerTeam)this.map2.get(text);
   }

   public void applyScorePlayerTeam3(ScorePlayerTeam scorePlayerTeam) {
   }

   public void applyComparatorEntry(ComparatorEntry comparatorEntry) {
   }

   public boolean matchesText(String text, String text2) {
      if (text.length() > 40) {
         throw new IllegalArgumentException("The player name '" + text + "' is too long!");
      } else if (!this.map3.containsKey(text2)) {
         return false;
      } else {
         ScorePlayerTeam byText = this.getByText(text2);
         if (this.getScorePlayerTeam(text) != null) {
            this.matchesText(text);
         }

         this.map2.put(text, byText);
         byText.getCollection().add(text);
         return true;
      }
   }

   public Collection valuesAsCollection() {
      return this.map5.values();
   }

   public static int computeCount(String text) {
      if (text.equalsIgnoreCase("list")) {
         return 0;
      } else if (text.equalsIgnoreCase("sidebar")) {
         return 1;
      } else if (text.equalsIgnoreCase("belowName")) {
         return 2;
      } else {
         if (text.startsWith("sidebar.team.")) {
            ClientSettingsBlackType clientSettingsBlackType = ClientSettingsBlackType.resolveClientSettingsBlackType(text.substring("sidebar.team.".length()));
            if (clientSettingsBlackType != null && clientSettingsBlackType.getIndex() >= 0) {
               return clientSettingsBlackType.getIndex() + 3;
            }
         }

         return -1;
      }
   }

   public void applyText(String text, ScorePlayerTeam scorePlayerTeam) {
      if (this.getScorePlayerTeam(text) != scorePlayerTeam) {
         if (!ClientGlobals.moduleManager.exploitFixer.settingHycraft.isEnabled()) {
            throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team '" + scorePlayerTeam.getLabelText2() + "'.");
         }
      } else {
         this.map2.remove(text);
         scorePlayerTeam.getCollection().remove(text);
      }
   }

   public ScoreboardData resolveScoreboardData(String text, HealthHandler healthHandler) {
      if (text.length() > 16) {
         throw new IllegalArgumentException("The objective name '" + text + "' is too long!");
      } else if (this.get(text) != null) {
         throw new IllegalArgumentException("An objective with the name '" + text + "' already exists!");
      } else {
         ScoreboardData scoreboardData = new ScoreboardData(this, text, healthHandler);
         List entry = (List)this.map4.get(healthHandler);
         if (entry == null) {
            entry = Lists.newArrayList();
            this.map4.put(healthHandler, entry);
         }

         entry.add(scoreboardData);
         this.map5.put(text, scoreboardData);
         this.applyScoreboardDataByScoreboardData(scoreboardData);
         return scoreboardData;
      }
   }

   public Collection keySetAsCollection() {
      return this.map.keySet();
   }

   public void applyScorePlayerTeamByScorePlayerTeam(ScorePlayerTeam scorePlayerTeam) {
   }

   public static String resolveText(int count) {
      switch (count) {
         case 0:
            return "list";
         case 1:
            return "sidebar";
         case 2:
            return "belowName";
         default:
            if (count >= 3 && count <= 18) {
               ClientSettingsBlackType clientSettingsBlackType = ClientSettingsBlackType.resolveClientSettingsBlackType(count - 3);
               if (clientSettingsBlackType != null && clientSettingsBlackType != ClientSettingsBlackType.RESET) {
                  return "sidebar.team." + clientSettingsBlackType.getText();
               }
            }

            return null;
      }
   }

   public void applyScoreboardData2(ScoreboardData scoreboardData) {
   }

   public Map resolveMap(String text) {
      Object entry = (Map)this.map.get(text);
      if (entry == null) {
         entry = Maps.newHashMap();
      }

      return (Map)entry;
   }
}
