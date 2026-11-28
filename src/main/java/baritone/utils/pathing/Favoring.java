package baritone.utils.pathing;

import baritone.api.pathing.calc.IPath;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import baritone.api.utils.IPlayerContext;
import baritone.pathing.movement.CalculationContext;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;

public final class Favoring {
   private final Long2DoubleOpenHashMap favorings = new Long2DoubleOpenHashMap();

   public boolean isEmpty() {
      return this.favorings.isEmpty();
   }

   public Favoring(IPlayerContext playerContext, IPath path, CalculationContext calculationContext) {
      this(path, calculationContext);

      for (Avoidance avoidance : (Iterable<Avoidance>)(Avoidance.create(playerContext))) {
         avoidance.applySpherical(this.favorings);
      }

      Helper.HELPER.logDebug("Favoring size: " + this.favorings.size());
   }

   public double calculate(long l) {
      return this.favorings.get(l);
   }

   public Favoring(IPath path, CalculationContext calculationContext) {
      this.favorings.defaultReturnValue(1.0);
      double d = calculationContext.backtrackCostFavoringCoefficient;
      if (d != 1.0 && path != null) {
         path.positions().forEach(obj -> this.favorings.put(BetterBlockPos.longHash((BetterBlockPos)obj), d));
      }
   }

}
