package de.florianmichael.dietrichevents2;

public abstract class BreakableEvent<T> extends AbstractEvent<T> {
   private boolean abort;

   @Override
   public void call(T listener) {
      if (!this.isAbort()) {
         this.call0(listener);
      }
   }

   public abstract void call0(T obj);

   public void stopHandling() {
      this.setAbort(true);
   }

   public boolean isAbort() {
      return this.abort;
   }

   public void setAbort(boolean abort) {
      this.abort = abort;
   }
}
