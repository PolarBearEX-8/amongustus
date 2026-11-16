package client.util;

import client.event.VirtualListener;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class SplineConverter {
   public static final CurveTracker curveTracker2 = new CloseMenuCurveTracker(null);
   public static final CurveTracker curveTracker3 = new RuntimeComponentProvider(-1, true);
   public static final CurveTracker curveTracker4 = new RuntimeComponentProvider(1, true);
   public static final CurveTracker curveTracker5 = new RuntimeComponentProvider(1, false);
   public static final CurveTracker curveTracker = new SplineConverterCurveTracker();
   public final VirtualListener virtualListener;
   public final List list = Lists.newArrayList();
   public AuxiliaryCustom auxiliaryCustom = new PressKeyAuxiliaryCustom();
   public int count = -1;
   public int count2;

   public void updateState() {
      this.virtualListener.applySplineConverter(this);
   }

   public SplineConverter(VirtualListener virtualListener2) {
      this.virtualListener = virtualListener2;
   }

   public CurveTracker getCurveTracker() {
      return this.resolveCurveTracker(this.count);
   }

   public SmoothCounter getSmoothCounter() {
      return new SmoothCounter(this.auxiliaryCustom, this.getList(), this.count);
   }

   public List getList() {
      ArrayList list2 = Lists.newArrayList();

      for (int index = 0; index <= 8; index++) {
         list2.add(this.resolveCurveTracker(index));
      }

      return list2;
   }

   public void applyIndex(int count3) {
      CurveTracker curveTracker6 = this.resolveCurveTracker(count3);
      if (curveTracker6 != curveTracker) {
         if (this.count == count3 && curveTracker6.isEmpty()) {
            curveTracker6.applySplineConverter(this);
         } else {
            this.count = count3;
         }
      }
   }

   public CurveTracker resolveCurveTracker(int count3) {
      int count4 = count3 + this.count2 * 6;
      return this.count2 > 0 && count3 == 0
         ? curveTracker3
         : (
            count3 == 7
               ? (count4 < this.auxiliaryCustom.getList().size() ? curveTracker4 : curveTracker5)
               : (
                  count3 == 8
                     ? curveTracker2
                     : (
                        count4 >= 0 && count4 < this.auxiliaryCustom.getList().size()
                           ? (CurveTracker)MoreObjects.firstNonNull(this.auxiliaryCustom.getList().get(count4), curveTracker)
                           : curveTracker
                     )
               )
         );
   }

   public AuxiliaryCustom getAuxiliaryCustom() {
      return this.auxiliaryCustom;
   }

   public static int computeCount(SplineConverter splineConverter, int count3) {
      return splineConverter.count2 = count3;
   }

   public int getCount() {
      return this.count;
   }

   public void applyAuxiliaryCustom(AuxiliaryCustom auxiliaryCustom2) {
      this.list.add(this.getSmoothCounter());
      this.auxiliaryCustom = auxiliaryCustom2;
      this.count = -1;
      this.count2 = 0;
   }
}
