package net.minecraft.scoreboard;

import java.util.List;
import net.minecraft.util.EnumChatFormatting;

public class GoalColor implements IScoreObjectiveCriteria {
   private final String goalName;

   @Override
   public String getName() {
      return this.goalName;
   }

   @Override
   public IScoreObjectiveCriteria.EnumRenderType getRenderType() {
      return IScoreObjectiveCriteria.EnumRenderType.INTEGER;
   }

   public GoalColor(String str, EnumChatFormatting enumChatFormatting) {
      this.goalName = str + enumChatFormatting.getFriendlyName();
      IScoreObjectiveCriteria.INSTANCES.put(this.goalName, this);
   }

   @Override
   public int computeScore(List list) {
      return 0;
   }

   @Override
   public boolean isReadOnly() {
      return false;
   }
}
