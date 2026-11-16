package cc.baritone.api;

import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.HeuristicCoordinator;
import cc.baritone.utils.Listener5Entry;

public class InventoryPauserINACTIVEHandlerAdapter extends INACTIVEHandlerAdapter {
   public boolean enabled;
   public boolean enabled2;
   public int count;

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public String getText() {
      return "inventory pauser";
   }

   private double getValueAsDouble() {
      return Math.sqrt(
         this.staticListener.getEntityPlayerSP().motionX * this.staticListener.getEntityPlayerSP().motionX
            + this.staticListener.getEntityPlayerSP().motionZ * this.staticListener.getEntityPlayerSP().motionZ
      );
   }

   @Override
   public void updateState() {
   }

   @Override
   public double getValue() {
      return 5.1;
   }

   @Override
   public boolean isEnabled() {
      return this.staticListener.getEntityPlayerSP() != null && this.staticListener.getWorld() != null;
   }

   public boolean isActive2() {
      this.enabled = true;
      return this.enabled2 && this.count > 1;
   }

   @Override
   public Listener5Entry resolveListener5Entry(boolean flag, boolean flag2) {
      this.enabled2 = flag2;
      if (this.enabled) {
         this.enabled = false;
         if (this.isActiveAsFlag()) {
            this.count++;
         }

         return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
      } else {
         this.count = 0;
         return new Listener5Entry(null, HeuristicCoordinator.DEFER);
      }
   }

   private boolean isActiveAsFlag() {
      return this.getValueAsDouble() < 1.0E-5;
   }

   public InventoryPauserINACTIVEHandlerAdapter(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
