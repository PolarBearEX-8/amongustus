package net.augustus.utils;

import net.augustus.events.EventManager;
import net.augustus.events.listeners.TickListener;
import org.lwjgl.input.Mouse;

public class MouseWheelTracker implements TickListener {
   public int lastDelta = 0;
   public int accumulatedDelta = 0;

   public int consumeDelta() {
      int accumulatedDelta2 = this.accumulatedDelta;
      this.accumulatedDelta = 0;
      return accumulatedDelta2;
   }

   public MouseWheelTracker() {
      EventManager.getEventBus().subscribe(40, this);
   }

   public void unregister() {
      EventManager.getEventBus().unsubscribe(40, this);
   }

   @Override
   public void onPostTick() {
      this.accumulatedDelta = 0;
   }

   public void pollMouseWheel() {
      this.lastDelta = Mouse.getDWheel();
      this.accumulatedDelta = this.accumulatedDelta + this.lastDelta;
   }
}
