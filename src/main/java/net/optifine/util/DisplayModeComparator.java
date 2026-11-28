package net.optifine.util;

import java.util.Comparator;
import org.lwjgl.opengl.DisplayMode;

public class DisplayModeComparator implements Comparator {
   @Override
   public int compare(Object a, Object b) {
      DisplayMode displayMode = (DisplayMode)a;
      DisplayMode displayMode2 = (DisplayMode)b;
      return displayMode.getWidth() != displayMode2.getWidth()
         ? displayMode.getWidth() - displayMode2.getWidth()
         : (
            displayMode.getHeight() != displayMode2.getHeight()
               ? displayMode.getHeight() - displayMode2.getHeight()
               : (
                  displayMode.getBitsPerPixel() != displayMode2.getBitsPerPixel()
                     ? displayMode.getBitsPerPixel() - displayMode2.getBitsPerPixel()
                     : (displayMode.getFrequency() != displayMode2.getFrequency() ? displayMode.getFrequency() - displayMode2.getFrequency() : 0)
               )
         );
   }
}
