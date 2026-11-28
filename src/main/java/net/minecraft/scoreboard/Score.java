package net.minecraft.scoreboard;

import java.util.Comparator;
import java.util.List;

public class Score {
   public static final Comparator scoreComparator = new Score.EntryComparator();
   private final Scoreboard theScoreboard;
   private final ScoreObjective theScoreObjective;
   private final String scorePlayerName;
   private int scorePoints;
   private boolean locked;
   private boolean forceUpdate;

   public Scoreboard getScoreScoreboard() {
      return this.theScoreboard;
   }

   public ScoreObjective getObjective() {
      return this.theScoreObjective;
   }

   public Score(Scoreboard scoreboard, ScoreObjective scoreObjective, String str) {
      this.theScoreboard = scoreboard;
      this.theScoreObjective = scoreObjective;
      this.scorePlayerName = str;
      this.forceUpdate = true;
   }

   public int getScorePoints() {
      return this.scorePoints;
   }

   public void increseScore(int n) {
      if (this.theScoreObjective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.setScorePoints(this.getScorePoints() + n);
      }
   }

   public void incrementScore() {
      if (this.theScoreObjective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.increseScore(1);
      }
   }

   public boolean isLocked() {
      return this.locked;
   }

   public void setLocked(boolean flag) {
      this.locked = flag;
   }

   public void setScorePoints(int n) {
      int scorePoints2 = this.scorePoints;
      this.scorePoints = n;
      if (scorePoints2 != n || this.forceUpdate) {
         this.forceUpdate = false;
         this.getScoreScoreboard().onScoreUpdated(this);
      }
   }

   public void setScoreFromPlayers(List list) {
      this.setScorePoints(this.theScoreObjective.getCriteria().computeScore(list));
   }

   public String getPlayerName() {
      return this.scorePlayerName;
   }

   public void decreaseScore(int n) {
      if (this.theScoreObjective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.setScorePoints(this.getScorePoints() - n);
      }
   }


   public static final class EntryComparator implements Comparator {
      public int compare(Score a, Score b) {
         return a.getScorePoints() > b.getScorePoints()
            ? 1
            : (a.getScorePoints() < b.getScorePoints() ? -1 : b.getPlayerName().compareToIgnoreCase(a.getPlayerName()));
      }

      @Override
      public int compare(Object a, Object b) {
         return this.compare((Score)a, (Score)b);
      }
   }
}
