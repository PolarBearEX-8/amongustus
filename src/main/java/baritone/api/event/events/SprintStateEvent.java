package baritone.api.event.events;

public final class SprintStateEvent {
   private Boolean state;

   public final Boolean getState() {
      return this.state;
   }

   public final void setState(boolean flag) {
      this.state = flag;
   }
}
