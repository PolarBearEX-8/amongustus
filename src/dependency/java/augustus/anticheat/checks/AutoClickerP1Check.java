package augustus.anticheat.checks;

import client.util.AuxiliaryStringEntryRegistry;
import client.util.DecimalFormatter;
import client.util.MinecraftAccessor;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoClickerP1Check extends AuxiliaryStringEntryRegistry {
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
         if (list.size() >= 30) {
            double offset = PlayerUtil.computeValueByList(list);
            double coordinate = PlayerUtil.getCoordinate(list);
            double offset2 = (double)PlayerUtil.computeCountByList(list);
            double offset3 = (double)PlayerUtil.computeCount(list);
            if (MinecraftAccessor.minecraft.thePlayer.ticksExisted % 100 == 0) {
               System.out
                  .println(
                     "Hurttime " + index + ": STD: " + offset + "; AVG: " + coordinate + "; MIN: " + offset2 + "; MAX: " + offset3 + "; SampleSize: " + list.size()
                  );
            }

            if (offset < 0.25 && coordinate > 0.1 || offset2 > 0.0) {
               count++;
               if (offset == 0.0) {
                  count++;
               }

               if (offset2 > 0.0) {
                  count += 2;
               }

               RayTraceUtil.applyString("AutoClicker P1: " + index + " " + offset + " " + coordinate + " " + offset2 + " " + offset3);
               list.clear();
            }
         }
      }

      if (count > 0) {
         this.value += (double)count;
         RayTraceUtil.applyString("AutoClicker P1: lv:" + DecimalFormatter.formatDecimal(this.value, 3));
      } else {
         this.value = Math.max(this.value - 0.001, 0.0);
      }

      if (this.value > 10.0) {
         this.value = 0.0;
         RayTraceUtil.applyString("Banned I think! Reset Violations");
      }
   }

   public AutoClickerP1Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager, 30);
      super.state = true;
   }
}
