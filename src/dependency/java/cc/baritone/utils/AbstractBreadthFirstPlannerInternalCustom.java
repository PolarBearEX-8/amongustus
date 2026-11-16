package cc.baritone.utils;

public abstract class AbstractBreadthFirstPlannerInternalCustom implements InternalCustom {
   public boolean enabled = false;
   public BreadthFirstPlanner breadthFirstPlanner = null;

   @Override
   public void applyShortArray(short[] shortValueArray, int count, int count2) throws AuxiliaryExceptionAdapter {
      if (this.isEnabled()) {
         this.applyShortArrayByShortArray(shortValueArray, count, count2);
      }
   }

   @Override
   public synchronized void applyBreadthFirstPlanner(BreadthFirstPlanner breadthFirstPlanner2) throws AuxiliaryExceptionAdapter {
      if (!this.isEnabled()) {
         this.breadthFirstPlanner = breadthFirstPlanner2;
         this.updateState3();
         this.setEnabled(true);
      }
   }

   public BreadthFirstPlanner getBreadthFirstPlanner() {
      return this.breadthFirstPlanner;
   }

   public void drain() {
   }

   public void updateState3() throws AuxiliaryExceptionAdapter {
   }

   @Override
   public synchronized void updateState() {
      if (this.isEnabled()) {
         this.close();
         this.setEnabled(false);
         this.breadthFirstPlanner = null;
      }
   }

   @Override
   public void updateState2() {
      if (this.isEnabled()) {
         this.drain();
      }
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public void close() {
   }

   public void applyShortArrayByShortArray(short[] shortValueArray, int count, int count2) throws AuxiliaryExceptionAdapter {
   }

   @Override
   public synchronized boolean isEnabled() {
      return this.enabled;
   }
}
