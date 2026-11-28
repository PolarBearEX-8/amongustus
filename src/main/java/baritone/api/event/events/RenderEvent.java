package baritone.api.event.events;

public final class RenderEvent {
   private final float partialTicks;

   public final float getPartialTicks() {
      return this.partialTicks;
   }

   public RenderEvent(float f) {
      this.partialTicks = f;
   }
}
