package client.util;

import client.setting.TabListIntegerType;

public class ScoreboardData {
   public final ThePlayerNameManager thePlayerNameManager;
   public final String labelText2;
   public final HealthHandler healthHandler;
   public TabListIntegerType tabListIntegerType;
   public String labelText;

   public ThePlayerNameManager getThePlayerNameManager() {
      return this.thePlayerNameManager;
   }

   public void applyTabListIntegerType(TabListIntegerType tabListIntegerType2) {
      this.tabListIntegerType = tabListIntegerType2;
      this.thePlayerNameManager.applyScoreboardData2(this);
   }

   public HealthHandler getHealthHandler() {
      return this.healthHandler;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public TabListIntegerType getTabListIntegerType() {
      return this.tabListIntegerType;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void applyText(String text) {
      this.labelText = text;
      this.thePlayerNameManager.applyScoreboardData2(this);
   }

   public ScoreboardData(ThePlayerNameManager thePlayerNameManager2, String text, HealthHandler healthHandler2) {
      this.thePlayerNameManager = thePlayerNameManager2;
      this.labelText2 = text;
      this.healthHandler = healthHandler2;
      this.labelText = text;
      this.tabListIntegerType = healthHandler2.getINTEGER();
   }
}
