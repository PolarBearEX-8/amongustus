package net.minecraft.scoreboard;

import java.util.List;

public class ScoreDummyCriteria implements IScoreObjectiveCriteria {
   private final String dummyName;

   @Override
   public IScoreObjectiveCriteria.EnumRenderType getRenderType() {
      return IScoreObjectiveCriteria.EnumRenderType.INTEGER;
   }

   @Override
   public int computeScore(List list) {
      return 0;
   }

   @Override
   public String getName() {
      return this.dummyName;
   }

   @Override
   public boolean isReadOnly() {
      return false;
   }

   public ScoreDummyCriteria(String str) {
      this.dummyName = str;
      IScoreObjectiveCriteria.INSTANCES.put(str, this);
   }
}
