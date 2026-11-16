package client.util;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public final class SplineConverterCurveTracker implements CurveTracker {
   @Override
   public void applySplineConverter(SplineConverter splineConverter) {
   }

   @Override
   public boolean isEmpty() {
      return false;
   }

   @Override
   public void applyFloat(float factor, int count) {
   }

   @Override
   public IChatComponent getName() {
      return new ChatComponentText("");
   }
}
