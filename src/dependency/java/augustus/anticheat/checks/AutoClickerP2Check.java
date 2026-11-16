package augustus.anticheat.checks;

import client.util.AuxiliaryStringEntryRegistry;
import client.util.DecimalFormatter;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoClickerP2Check extends AuxiliaryStringEntryRegistry {
   public double value = 0.0;

   @Override
   public void clear() {
      super.clear();
      this.value = 0.0;
   }

   @Override
   public void applyHashMap(HashMap map) {
      int count = 0;
      int count2 = 0;

      for (int index = 0; index < 11; index++) {
         ArrayList list = (ArrayList)map.get(index);
         if (list.size() >= 25) {
            double offset = PlayerUtil.computeValueByList(list);
            double coordinate = PlayerUtil.getCoordinate(list);
            if (offset < 0.25 && coordinate <= 0.1) {
               count++;
               if (offset == 0.0) {
                  count++;
               }

               list.clear();
            } else {
               count2++;
            }
         }
      }

      if (count > 0 && count2 > 8) {
         this.value += (double)count;
         RayTraceUtil.applyString("AutoClicker P2: lv:" + DecimalFormatter.formatDecimal(this.value, 3));
      } else {
         this.value = Math.max(this.value - 0.001, 0.0);
      }

      if (this.value > 10.0) {
         this.value = 0.0;
         RayTraceUtil.applyString("Banned I think! Reset Violations");
      }
   }

   public AutoClickerP2Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager, 30);
      super.state = true;
   }
}
