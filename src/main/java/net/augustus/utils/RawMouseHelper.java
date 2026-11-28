package net.augustus.utils;

import net.augustus.utils.input.RawMouseInputThread;
import net.augustus.modules.misc.RawMouseInput;
import net.minecraft.util.MouseHelper;

public class RawMouseHelper extends MouseHelper {

   @Override
   public void grabMouseCursor() {
      super.grabMouseCursor();
      RawMouseInputThread.deltaX = 0;
      RawMouseInputThread.deltaY = 0;
   }

   @Override
   public void mouseXYChange() {
      if (!RawMouseInput.isActive()) {
         super.mouseXYChange();
         RawMouseInputThread.deltaX = 0;
         RawMouseInputThread.deltaY = 0;
      } else {
         this.deltaX = RawMouseInputThread.deltaX;
         RawMouseInputThread.deltaX = 0;
         this.deltaY = -RawMouseInputThread.deltaY;
         RawMouseInputThread.deltaY = 0;
      }
   }

   @Override
   public void resetMouseDelta() {
      super.resetMouseDelta();
      RawMouseInputThread.deltaX = 0;
      RawMouseInputThread.deltaY = 0;
   }




}
