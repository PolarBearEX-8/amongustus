package baritone.behavior;

import baritone.Baritone;
import baritone.api.behavior.IBehavior;
import baritone.api.utils.IPlayerContext;

public class Behavior implements IBehavior {
   public final Baritone baritone;
   public final IPlayerContext ctx;

   public Behavior(Baritone baritone2) {
      this.baritone = baritone2;
      this.ctx = baritone2.getPlayerContext();
   }
}
