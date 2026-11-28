package net.minecraft.scoreboard;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class ScoreHealthCriteria extends ScoreDummyCriteria {
   @Override
   public boolean isReadOnly() {
      return true;
   }

   @Override
   public IScoreObjectiveCriteria.EnumRenderType getRenderType() {
      return IScoreObjectiveCriteria.EnumRenderType.HEARTS;
   }

   public ScoreHealthCriteria(String str) {
      super(str);
   }

   @Override
   public int computeScore(List list) {
      float f = 0.0F;

      for (EntityPlayer player : (Iterable<EntityPlayer>)(list)) {
         f += player.getCurrentHealth() + player.getAbsorptionAmount();
      }

      if (list.size() > 0) {
         f /= (float)list.size();
      }

      return MathHelper.ceiling_float_int(f);
   }
}
