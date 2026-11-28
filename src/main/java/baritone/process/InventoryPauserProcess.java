package baritone.process;

import baritone.Baritone;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.utils.BaritoneProcessHelper;

public class InventoryPauserProcess extends BaritoneProcessHelper {
   public boolean pauseRequestedLastTick;
   public boolean safeToCancel;
   public int ticks;

   private boolean stationaryNow() {
      return this.motion() < 1.0E-5;
   }

   @Override
   public double priority() {
      return 5.1;
   }

   @Override
   public String displayName0() {
      return "inventory pauser";
   }

   public boolean stationaryForInventoryMove() {
      this.pauseRequestedLastTick = true;
      return this.safeToCancel && this.ticks > 1;
   }

   public InventoryPauserProcess(Baritone baritone2) {
      super(baritone2);
   }

   @Override
   public boolean isActive() {
      return this.ctx.player() != null && this.ctx.world() != null;
   }

   @Override
   public void onLostControl() {
   }

   private double motion() {
      return Math.sqrt(this.ctx.player().motionX * this.ctx.player().motionX + this.ctx.player().motionZ * this.ctx.player().motionZ);
   }

   @Override
   public PathingCommand onTick(boolean flag, boolean flag2) {
      this.safeToCancel = flag2;
      if (this.pauseRequestedLastTick) {
         this.pauseRequestedLastTick = false;
         if (this.stationaryNow()) {
            this.ticks++;
         }

         return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
      } else {
         this.ticks = 0;
         return new PathingCommand(null, PathingCommandType.DEFER);
      }
   }

   @Override
   public boolean isTemporary() {
      return true;
   }
}
