package net.augustus.utils.rotation;

import net.augustus.utils.math.Vec2d;
import java.util.ArrayList;
import net.augustus.utils.TimeHelper;
import net.minecraft.util.MathHelper;

public class BezierCurve2D {
   public int durationTicks;
   public double[] targetPitchRange;
   public double[] targetYawRange;
   private double previousProgress;
   private double progress;
   public double speedScale;
   public ArrayList controlPoints = new ArrayList();
   public double targetCenterYaw = 0.0;
   public boolean stationary;

   public Vec2d evaluate(double d) {
      if (this.stationary) {
         return Vec2d.ZERO;
      } else {
         ArrayList list = new ArrayList(this.controlPoints);

         for (ArrayList i = new ArrayList(); list.size() > 1; i = new ArrayList()) {
            Vec2d vec2d = null;

            for (Vec2d vec2d2 : (Iterable<Vec2d>)(list)) {
               if (vec2d != null) {
                  i.add(vec2d.lerp(vec2d2, d));
               }

               vec2d = vec2d2;
            }

            list = i;
         }

         return (Vec2d)list.get(0);
      }
   }

   public void advanceProgress(double d) {
      this.setProgress(MathHelper.clamp_double(this.progress + d, 0.0, 1.0));
   }

   public double getPreviousProgress() {
      return this.previousProgress;
   }

   public BezierCurve2D() {
      this.speedScale = 1.0;
      this.stationary = false;
   }

   public double getProgress() {
      return this.progress;
   }

   public void setProgress(double d) {
      this.previousProgress = this.progress;
      this.progress = d;
   }
}
