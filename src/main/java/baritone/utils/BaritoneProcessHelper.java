package baritone.utils;

import baritone.Baritone;
import baritone.api.process.IBaritoneProcess;
import baritone.api.utils.Helper;
import baritone.api.utils.IPlayerContext;

public abstract class BaritoneProcessHelper implements IBaritoneProcess, Helper {
   public final Baritone baritone;
   public final IPlayerContext ctx;

   public BaritoneProcessHelper(Baritone baritone2) {
      this.baritone = baritone2;
      this.ctx = baritone2.getPlayerContext();
   }

   @Override
   public boolean isTemporary() {
      return false;
   }
}
