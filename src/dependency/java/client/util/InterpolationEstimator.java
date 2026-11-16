package client.util;

public abstract class InterpolationEstimator {
   public Object dataRef;
   public boolean enabled = false;

   public abstract Object getTarget();

   public Object getTargetAsTarget() {
      if (!this.enabled) {
         this.enabled = true;
         this.dataRef = this.getTarget();
      }

      return this.dataRef;
   }
}
