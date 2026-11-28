package net.minecraft.scoreboard;

public class ScoreObjective {
   private final Scoreboard theScoreboard;
   private final String name;
   private final IScoreObjectiveCriteria objectiveCriteria;
   private IScoreObjectiveCriteria.EnumRenderType renderType;
   private String displayName;

   public void setRenderType(IScoreObjectiveCriteria.EnumRenderType enumRenderType) {
      this.renderType = enumRenderType;
      this.theScoreboard.onObjectiveDisplayNameChanged(this);
   }

   public Scoreboard getScoreboard() {
      return this.theScoreboard;
   }

   public IScoreObjectiveCriteria.EnumRenderType getRenderType() {
      return this.renderType;
   }

   public String getName() {
      return this.name;
   }

   public void setDisplayName(String str) {
      this.displayName = str;
      this.theScoreboard.onObjectiveDisplayNameChanged(this);
   }

   public ScoreObjective(Scoreboard scoreboard, String str, IScoreObjectiveCriteria scoreObjectiveCriteria) {
      this.theScoreboard = scoreboard;
      this.name = str;
      this.objectiveCriteria = scoreObjectiveCriteria;
      this.displayName = str;
      this.renderType = scoreObjectiveCriteria.getRenderType();
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public IScoreObjectiveCriteria getCriteria() {
      return this.objectiveCriteria;
   }
}
