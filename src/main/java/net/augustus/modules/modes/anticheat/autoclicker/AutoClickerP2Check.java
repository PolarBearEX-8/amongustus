package net.augustus.modules.modes.anticheat.autoclicker;

import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.math.NumberFormatUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoClickerP2Check extends AbstractHurtTimeClickCheck {
   private double violationLevel = 0.0;

   public AutoClickerP2Check(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2, 30);
      this.visibleInGui = true;
   }

   @Override
   public void resetState() {
      super.resetState();
      this.violationLevel = 0.0;
   }

   @Override
   public void analyzeHurtTimeSamples(HashMap map) {
      int n = 0;
      int n2 = 0;

      for (int i = 0; i < 11; i++) {
         ArrayList list = (ArrayList)map.get(i);
         if (list.size() >= 25) {
            double d = MathStatisticsUtil.stdDevInt(list);
            double d2 = MathStatisticsUtil.averageInt(list);
            if (d < 0.25 && d2 <= 0.1) {
               n++;
               if (d == 0.0) {
                  n++;
               }

               list.clear();
            } else {
               n2++;
            }
         }
      }

      if (n > 0 && n2 > 8) {
         this.violationLevel += (double)n;
         ChatUtil.sendChat("AutoClicker P2: lv:" + NumberFormatUtil.formatDecimal(this.violationLevel, 3));
      } else {
         this.violationLevel = Math.max(this.violationLevel - 0.001, 0.0);
      }

      if (this.violationLevel > 10.0) {
         this.violationLevel = 0.0;
         ChatUtil.sendChat("Banned I think! Reset Violations");
      }
   }


}
