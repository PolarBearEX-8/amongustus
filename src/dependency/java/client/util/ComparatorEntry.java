package client.util;

import java.util.Comparator;
import java.util.List;

public class ComparatorEntry {
   public static final Comparator comparator = new CustomComparatorAdapter();
   public final ThePlayerNameManager thePlayerNameManager;
   public final ScoreboardData scoreboardData;
   public final String labelText;
   public int count;
   public boolean enabled;
   public boolean enabled2;

   public ThePlayerNameManager getThePlayerNameManager() {
      return this.thePlayerNameManager;
   }

   public void applyList(List list) {
      this.applyIndex(this.scoreboardData.getHealthHandler().computeCount(list));
   }

   public int getIndex() {
      return this.count;
   }

   public void updateState() {
      if (this.scoreboardData.getHealthHandler().isActive()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.applyIndexByIndex(1);
      }
   }

   public void applyIndex(int count2) {
      int count3 = this.count;
      this.count = count2;
      if (count3 != count2 || this.enabled2) {
         this.enabled2 = false;
         this.getThePlayerNameManager().applyComparatorEntry(this);
      }
   }

   public void applyIndex2(int count2) {
      if (this.scoreboardData.getHealthHandler().isActive()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.applyIndex(this.getIndex() - count2);
      }
   }

   public void setEnabled(boolean enabled3) {
      this.enabled = enabled3;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public ScoreboardData getScoreboardData() {
      return this.scoreboardData;
   }

   public void applyIndexByIndex(int count2) {
      if (this.scoreboardData.getHealthHandler().isActive()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.applyIndex(this.getIndex() + count2);
      }
   }

   public ComparatorEntry(ThePlayerNameManager thePlayerNameManager2, ScoreboardData scoreboardData2, String text) {
      this.thePlayerNameManager = thePlayerNameManager2;
      this.scoreboardData = scoreboardData2;
      this.labelText = text;
      this.enabled2 = true;
   }
}
