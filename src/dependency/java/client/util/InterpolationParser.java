package client.util;

import client.event.PreRenderObserver;
import java.awt.Color;

public class InterpolationParser implements PreRenderObserver {
   public static int count;
   public static final int[] countArray = new int[360];

   public InterpolationParser() {
      for (int index = 0; index < 360; index++) {
         countArray[index] = Color.HSBtoRGB((float)index / 360.0F, 1.0F, 1.0F);
      }
   }

   @Override
   public void applyIndex(int count2) {
      count = countArray[count2 * AngleFormatter.getAngleFormatter().count % 360];
   }
}
