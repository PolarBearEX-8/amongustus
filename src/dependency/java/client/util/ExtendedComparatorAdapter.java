package client.util;

import java.util.Comparator;
import org.lwjgl.opengl.DisplayMode;

public class ExtendedComparatorAdapter implements Comparator {
   @Override
   public int compare(Object entry, Object entry2) {
      DisplayMode displayMode = (DisplayMode)entry;
      DisplayMode displayMode2 = (DisplayMode)entry2;
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
