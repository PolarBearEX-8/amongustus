package client.util;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class SmoothProfiler {
   public int indexOffset;
   public int indexOffset1;

   public void updateState() {
      Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
      Mouse.setGrabbed(false);
   }

   public void updateState2() {
      this.indexOffset = Mouse.getDX();
      this.indexOffset1 = Mouse.getDY();
   }

   public void setGrabbed() {
      Mouse.setGrabbed(true);
      this.indexOffset = 0;
      this.indexOffset1 = 0;
   }
}
