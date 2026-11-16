package client.util;

import net.minecraft.util.IChatComponent;

public interface CurveTracker {
   IChatComponent getName();

   void applySplineConverter(SplineConverter splineConverter);

   boolean isEmpty();

   void applyFloat(float factor, int count);
}
