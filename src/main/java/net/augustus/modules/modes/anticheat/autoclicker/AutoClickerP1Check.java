package net.augustus.modules.modes.anticheat.autoclicker;

import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.math.NumberFormatUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoClickerP1Check extends AbstractHurtTimeClickCheck {
   private double violationLevel = 0.0;

   @Override
   public void resetState() {
      super.resetState();
      this.violationLevel = 0.0;
   }

   public AutoClickerP1Check(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2, 30);
      this.visibleInGui = true;
   }

   @Override
   public void analyzeHurtTimeSamples(HashMap map) {
      int n = 0;

      for (int i = 0; i < 11; i++) {
         ArrayList list = (ArrayList)map.get(i);
         if (list.size() >= 30) {
            double d = MathStatisticsUtil.stdDevInt(list);
            double d2 = MathStatisticsUtil.averageInt(list);
            double d3 = (double)MathStatisticsUtil.minInt(list);
            double d4 = (double)MathStatisticsUtil.maxInt(list);
            if (mc.thePlayer.ticksExisted % 100 == 0) {
               System.out
                  .println(
                     "Hurttime " + i + ": STD: " + d + "; AVG: " + d2 + "; MIN: " + d3 + "; MAX: " + d4 + "; SampleSize: " + list.size()
                  );
            }

            if (d < 0.25 && d2 > 0.1 || d3 > 0.0) {
               n++;
               if (d == 0.0) {
                  n++;
               }

               if (d3 > 0.0) {
                  n += 2;
               }

               ChatUtil.sendChat("AutoClicker P1: " + i + " " + d + " " + d2 + " " + d3 + " " + d4);
               list.clear();
            }
         }
      }

      if (n > 0) {
         this.violationLevel += (double)n;
         ChatUtil.sendChat("AutoClicker P1: lv:" + NumberFormatUtil.formatDecimal(this.violationLevel, 3));
      } else {
         this.violationLevel = Math.max(this.violationLevel - 0.001, 0.0);
      }

      if (this.violationLevel > 10.0) {
         this.violationLevel = 0.0;
         ChatUtil.sendChat("Banned I think! Reset Violations");
      }
   }


}
