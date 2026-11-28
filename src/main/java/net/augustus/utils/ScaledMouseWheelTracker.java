package net.augustus.utils;

import org.lwjgl.input.Mouse;

public class ScaledMouseWheelTracker extends MouseWheelTracker {

   public ScaledMouseWheelTracker() {
       super();
   }

   @Override
   public void pollMouseWheel() {
      this.lastDelta = Mouse.getDWheel() * 4;
      this.accumulatedDelta = this.lastDelta;
   }
}
