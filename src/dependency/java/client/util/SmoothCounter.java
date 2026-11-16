package client.util;

import com.google.common.base.MoreObjects;
import java.util.List;

public class SmoothCounter {
   public final AuxiliaryCustom auxiliaryCustom;
   public final List list;
   public final int count;

   public int getCount() {
      return this.count;
   }

   public SmoothCounter(AuxiliaryCustom auxiliaryCustom2, List list2, int count2) {
      this.auxiliaryCustom = auxiliaryCustom2;
      this.list = list2;
      this.count = count2;
   }

   public CurveTracker resolveCurveTracker(int count2) {
      return count2 >= 0 && count2 < this.list.size()
         ? (CurveTracker)MoreObjects.firstNonNull(this.list.get(count2), SplineConverter.curveTracker)
         : SplineConverter.curveTracker;
   }
}
