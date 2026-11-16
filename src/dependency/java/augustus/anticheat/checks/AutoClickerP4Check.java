package augustus.anticheat.checks;

import client.util.AuxiliaryStringEntryRegistry;
import client.util.DecimalFormatter;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AutoClickerP4Check extends AuxiliaryStringEntryRegistry {
   public double value = 0.0;

   @Override
   public void clear() {
      super.clear();
      this.value = 0.0;
   }

   @Override
   public void applyHashMap(HashMap map) {
      int count = 0;

      for (int index = 0; index < 11; index++) {
         ArrayList list = (ArrayList)map.get(index);
         if (list.size() >= 40) {
            double offset = PlayerUtil.computeValueByList(list);
            double coordinate = PlayerUtil.getCoordinate(list);
            double offset2 = (double)PlayerUtil.computeCount(list);
            int count2 = 0;
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
               if ((Integer)iterator.next() > 1) {
                  count2++;
               }
            }

            if (offset2 < 2.0 && coordinate > 0.95) {
               count++;
               RayTraceUtil.applyString("AutoClicker P4: " + index + " " + offset + " " + coordinate + " " + count2);
               list.clear();
            }
         }
      }

      if (count > 0) {
         this.value += (double)count;
         RayTraceUtil.applyString("AutoClicker P4: lv:" + DecimalFormatter.formatDecimal(this.value, 3));
      } else {
         this.value = Math.max(this.value - 0.001, 0.0);
      }

      if (this.value > 10.0) {
         this.value = 0.0;
         RayTraceUtil.applyString("Banned I think! Reset Violations");
      }
   }

   public AutoClickerP4Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager, 40);
   }
}
