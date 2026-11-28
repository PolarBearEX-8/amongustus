package de.florianmichael.dietrichevents2;

public abstract class CancellableEvent<T> extends AbstractEvent<T> {
   private boolean cancelled;

   public void cancel() {
      this.setCancelled(true);
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void setCancelled(boolean cancelled) {
      this.cancelled = cancelled;
   }
}
