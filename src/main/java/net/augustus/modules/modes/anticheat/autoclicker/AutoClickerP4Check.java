package net.augustus.modules.modes.anticheat.autoclicker;

import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.math.NumberFormatUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoClickerP4Check extends AbstractHurtTimeClickCheck {
   private double violationLevel = 0.0;

   @Override
   public void resetState() {
      super.resetState();
      this.violationLevel = 0.0;
   }

   public AutoClickerP4Check(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2, 40);
   }

   @Override
   public void analyzeHurtTimeSamples(HashMap map) {
      int n = 0;

      for (int i = 0; i < 11; i++) {
         ArrayList list = (ArrayList)map.get(i);
         if (list.size() >= 40) {
            double d = MathStatisticsUtil.stdDevInt(list);
            double d2 = MathStatisticsUtil.averageInt(list);
            double d3 = (double)MathStatisticsUtil.maxInt(list);
            int n2 = 0;

            for (int n3 : (Iterable<Integer>)(list)) {
               if (n3 > 1) {
                  n2++;
               }
            }

            if (d3 < 2.0 && d2 > 0.95) {
               n++;
               ChatUtil.sendChat("AutoClicker P4: " + i + " " + d + " " + d2 + " " + n2);
               list.clear();
            }
         }
      }

      if (n > 0) {
         this.violationLevel += (double)n;
         ChatUtil.sendChat("AutoClicker P4: lv:" + NumberFormatUtil.formatDecimal(this.violationLevel, 3));
      } else {
         this.violationLevel = Math.max(this.violationLevel - 0.001, 0.0);
      }

      if (this.violationLevel > 10.0) {
         this.violationLevel = 0.0;
         ChatUtil.sendChat("Banned I think! Reset Violations");
      }
   }


}
