package cc.baritone.utils;

public enum WAITINGCANCELEDEnum {
    PREPPING(false),
    WAITING(false),
    RUNNING(false),
    SUCCESS(true),
    UNREACHABLE(true),
    FAILED(true),
    CANCELED(true);

   public final boolean enabled;

   private WAITINGCANCELEDEnum(boolean flag) {
      this.enabled = flag;
   }

   public final boolean isEnabled() {
      return this.enabled;
   }
}
