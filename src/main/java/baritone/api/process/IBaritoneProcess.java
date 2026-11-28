package baritone.api.process;

public interface IBaritoneProcess {
   double DEFAULT_PRIORITY = -1.0;
   String e = "INACTIVE";

   default double priority() {
      return -1.0;
   }

   boolean isActive();

   boolean isTemporary();

   void onLostControl();

   String displayName0();

   PathingCommand onTick(boolean flag, boolean flag2);

   default String displayName() {
      return !this.isActive() ? e : this.displayName0();
   }
}
