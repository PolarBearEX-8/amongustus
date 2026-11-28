package baritone.api.pathing.movement;

public enum MovementStatus {
   PREPPING(false),
   WAITING(false),
   RUNNING(false),
   SUCCESS(true),
   UNREACHABLE(true),
   FAILED(true),
   CANCELED(true);

private final boolean complete;

   private MovementStatus(boolean flag) {
      this.complete = flag;
   }

   public final boolean isComplete() {
      return this.complete;
   }
}
