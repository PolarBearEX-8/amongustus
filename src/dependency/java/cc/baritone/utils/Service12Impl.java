package cc.baritone.utils;

public class Service12Impl implements Service12 {
   public boolean enabled;

   @Override
   public final boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public final void updateState() {
      this.enabled = true;
   }
}
