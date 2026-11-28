package net.augustus.events;

import net.augustus.events.listeners.GameLoopListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventGameLoop extends AbstractEvent<GameLoopListener> {
   private int elapsedTicks;
   private float renderPartialTicks;
   private boolean allowSkipRender = true;
   private boolean skipRender = false;
   public static final int ID = 16;

   private void reset(int n, float f) {
      this.elapsedTicks = n;
      this.renderPartialTicks = f;
      this.allowSkipRender = true;
      this.skipRender = false;
   }


   @Override
   public void call(GameLoopListener listener) {
      listener.onGameLoop(this);
   }

   public void setSkipRender(boolean flag) {
      this.skipRender = flag;
   }

   public void setElapsedTicks(int n) {
      this.elapsedTicks = n;
   }

   public void setAllowSkipRender(boolean flag) {
      this.allowSkipRender = flag;
   }

   public float getRenderPartialTicks() {
      return this.renderPartialTicks;
   }

   public boolean isAllowSkipRender() {
      return this.allowSkipRender;
   }

   public int getElapsedTicks() {
      return this.elapsedTicks;
   }

   public boolean isSkipRender() {
      return this.skipRender;
   }

   public static void reset(EventGameLoop event, int n, float f) {
      event.reset(n, f);
   }

   public void setRenderPartialTicks(float f) {
      this.renderPartialTicks = f;
   }
}
