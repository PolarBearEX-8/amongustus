package cc.baritone.utils;

public class ControllerManager {
   public Object dataRef;
   public boolean enabled;

   public ControllerManager(Object entry) {
      this.dataRef = entry;
   }

   public Object getDataRef() {
      return this.dataRef;
   }

   public void applyTarget(Object entry) {
      this.dataRef = entry;
      this.enabled = true;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public String toString() {
      return String.format("Overrideable{modified=%b,value=%s}", this.enabled, this.dataRef.toString());
   }
}
