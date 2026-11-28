package net.augustus.modules.modes.anticheat.intave14.autoclicker;

import java.util.List;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.utils.math.NumberFormatUtil;
import net.augustus.modules.modes.anticheat.FlagCertainty;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;

public class Intave14AutoClickerDoubleClickCheck extends AbstractIntave14ClickCheck {
   private final AntiCheatCheckTracker antiCheatCheckTracker;

   @Override
   public void analyzeClicks(List list) {
      double d = this.calculateCps(list);
      if (d > 15.0 && this.violationLevel == 0) {
         this.antiCheatCheckTracker.addViolation(1.0);
         this.antiCheatCheckTracker.flagWithDetail(FlagCertainty.NONE, NumberFormatUtil.formatDecimal(d, 2) + "");
      }
   }

   public Intave14AutoClickerDoubleClickCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(
         str,
         antiCheatPlayerData2,
         60
      );
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(300, "clicking too fast without double clicks", AntiCheatCategory.AUTOCLICKER);
   }
}
