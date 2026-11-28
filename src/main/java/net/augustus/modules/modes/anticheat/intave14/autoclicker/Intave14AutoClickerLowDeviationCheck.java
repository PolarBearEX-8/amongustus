package net.augustus.modules.modes.anticheat.intave14.autoclicker;

import java.util.List;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.FlagCertainty;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;

public class Intave14AutoClickerLowDeviationCheck extends AbstractIntave14ClickCheck {
   private final AntiCheatCheckTracker antiCheatCheckTracker;

   @Override
   public void analyzeClicks(List list) {
      double d = this.calculateStdDev(list);
      if (d < 0.45) {
         this.antiCheatCheckTracker.addViolation(1.0);
         if (this.antiCheatCheckTracker.getViolationLevel() >= 3.0) {
            this.antiCheatCheckTracker.flagWithDetail(FlagCertainty.NONE, d + "");
         }
      } else {
         this.antiCheatCheckTracker.addViolation(-0.5);
         if (this.antiCheatCheckTracker.getViolationLevel() < 0.0) {
            this.antiCheatCheckTracker.addViolation(-this.antiCheatCheckTracker.getViolationLevel());
         }
      }
   }

   public Intave14AutoClickerLowDeviationCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(
         str,
         antiCheatPlayerData2,
         100
      );
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(310, "Clicking with low deviation", AntiCheatCategory.AUTOCLICKER);
   }
}
