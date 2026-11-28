package net.augustus.events;

import net.augustus.events.listeners.MouseMoveListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;

public class EventMouseMove extends AbstractEvent<MouseMoveListener> {
   private boolean bypassSmoothCamera;
   public static final int ID = 19;
   private float partialTicks;
   private int deltaX;
   private int deltaY;
   private StateTypes state;
   private boolean ignoreInvertMouse = false;

   public EventMouseMove() {
      this.bypassSmoothCamera = false;
   }

   public boolean isIgnoreInvertMouse() {
      return this.ignoreInvertMouse;
   }

   public static void reset(EventMouseMove event, int n, int n2, float f, StateTypes stateTypes) {
      event.reset(n, n2, f, stateTypes);
   }

   @Override
   public void call(MouseMoveListener listener) {
      if (this.state == StateTypes.PRE) {
         listener.onMouseMove(this);
      } else {
         listener.onPostMouseMove();
      }
   }

   private void reset(int n, int n2, float f, StateTypes stateTypes) {
      this.deltaX = n;
      this.deltaY = n2;
      this.partialTicks = f;
      this.state = stateTypes;
      this.bypassSmoothCamera = false;
      this.ignoreInvertMouse = false;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public void setDeltaX(int n) {
      this.deltaX = n;
   }

   public boolean isBypassSmoothCamera() {
      return this.bypassSmoothCamera;
   }

   public void setIgnoreInvertMouse(boolean flag) {
      this.ignoreInvertMouse = flag;
   }

   public int getDeltaY() {
      return this.deltaY;
   }

   public void setDeltaY(int n) {
      this.deltaY = n;
   }

   public void setBypassSmoothCamera(boolean flag) {
      this.bypassSmoothCamera = flag;
   }


   public int getDeltaX() {
      return this.deltaX;
   }

   public void setPartialTicks(float f) {
      this.partialTicks = f;
   }
}
