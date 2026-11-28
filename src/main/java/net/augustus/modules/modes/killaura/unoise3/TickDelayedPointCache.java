package net.augustus.modules.modes.killaura.unoise3;

import java.util.function.DoubleSupplier;

public class TickDelayedPointCache {
   private int remainingDelayTicks;
   private DoubleSupplier delaySupplier;
   private Object cachedValue;

   public Object getCachedValue() {
      return this.cachedValue;
   }

   public void resetDelay() {
      this.remainingDelayTicks = 0;
   }

   public TickDelayedPointCache(DoubleSupplier doubleSupplier) {
      this.delaySupplier = doubleSupplier;
   }

   public void update(Object obj) {
      if (this.remainingDelayTicks == 0) {
         this.cachedValue = obj;
         this.remainingDelayTicks = (int)this.delaySupplier.getAsDouble();
      } else {
         if (this.remainingDelayTicks > 0) {
            this.remainingDelayTicks--;
         }
      }
   }
}
