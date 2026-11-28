package net.augustus.render.waveycape;

import net.augustus.render.cape.WaveyCapeConfig;
import java.util.ArrayList;
import java.util.List;

public class StickSimulation {
   public float gravity;
   private WaveyCapeConfig config;
   public List sticks;
   public List points = new ArrayList();
   public int numIterations;
   private float maxBend;

   public void simulate() {
      this.gravity = (float)this.config.gravity;
      float f = 0.05F;
      CapeVector2f capeVector2f = new CapeVector2f(0.0F, this.gravity * f);
      CapeVector2f capeVector2f2 = new CapeVector2f(0.0F, 0.0F);

      for (StickPoint stickPoint : (Iterable<StickPoint>)(this.points)) {
         if (!stickPoint.locked) {
            capeVector2f2.copy(stickPoint.position);
            stickPoint.position.subtract(capeVector2f);
            stickPoint.prevPosition.copy(capeVector2f2);
         }
      }

      StickPoint stickPoint2 = (StickPoint)this.points.get(0);

      for (StickPoint stickPoint3 : (Iterable<StickPoint>)(this.points)) {
         if (stickPoint3 != stickPoint2 && stickPoint3.position.x - stickPoint2.position.x > 0.0F) {
            stickPoint3.position.x = stickPoint2.position.x - 0.1F;
         }
      }

      for (int i = this.points.size() - 2; i >= 1; i--) {
         double angle = this.getAngle(((StickPoint)this.points.get(i)).position, ((StickPoint)this.points.get(i - 1)).position, ((StickPoint)this.points.get(i + 1)).position);
         angle *= 57.2958;
         if (angle > 360.0) {
            angle -= 360.0;
         }

         if (angle < -360.0) {
            angle += 360.0;
         }

         double d = Math.abs(angle);
         if (d < (double)(180.0F - this.maxBend)) {
            CapeVector2f position5 = ((StickPoint)this.points.get(i)).position;
            double d2 = (double)(180.0F - this.maxBend + 1.0F);
            CapeVector2f capeVector2f6 = this.constrainPointAngle(position5, ((StickPoint)this.points.get(i - 1)).position, angle, d2);
            ((StickPoint)this.points.get(i + 1)).position = capeVector2f6;
         }

         if (d > (double)(180.0F + this.maxBend)) {
            CapeVector2f position7 = ((StickPoint)this.points.get(i)).position;
            double d3 = (double)(180.0F + this.maxBend - 1.0F);
            CapeVector2f capeVector2f8 = this.constrainPointAngle(position7, ((StickPoint)this.points.get(i - 1)).position, angle, d3);
            ((StickPoint)this.points.get(i + 1)).position = capeVector2f8;
         }
      }

      for (int j = 0; j < this.numIterations; j++) {
         for (int k = this.sticks.size() - 1; k >= 0; k--) {
            StickConstraint stickConstraint = (StickConstraint)this.sticks.get(k);
            CapeVector2f capeVector2f9 = stickConstraint.pointA.position.copy();
            capeVector2f9 = capeVector2f9.add(stickConstraint.pointB.position);
            CapeVector2f capeVector2f10 = capeVector2f9.div(2.0F);
            capeVector2f9 = stickConstraint.pointA.position.copy();
            CapeVector2f capeVector2f11 = capeVector2f9.subtract(stickConstraint.pointB.position).normalize();
            if (!stickConstraint.pointA.locked) {
               stickConstraint.pointA.position = capeVector2f10.copy().add(capeVector2f11.copy().mul(stickConstraint.length / 2.0F));
            }

            if (!stickConstraint.pointB.locked) {
               stickConstraint.pointB.position = capeVector2f10.copy().subtract(capeVector2f11.copy().mul(stickConstraint.length / 2.0F));
            }
         }
      }

      for (int m = 0; m < this.sticks.size(); m++) {
         StickConstraint stickConstraint2 = (StickConstraint)this.sticks.get(m);
         CapeVector2f capeVector2f19 = stickConstraint2.pointA.position.copy().subtract(stickConstraint2.pointB.position).normalize();
         if (!stickConstraint2.pointB.locked) {
            stickConstraint2.pointB.position = stickConstraint2.pointA.position.copy().subtract(capeVector2f19.mul(stickConstraint2.length));
         }
      }
   }

   public StickSimulation(WaveyCapeConfig waveyCapeConfig) {
      this.sticks = new ArrayList();
      this.gravity = 20.0F;
      this.numIterations = 30;
      this.maxBend = 5.0F;
      this.config = waveyCapeConfig;
   }

   private CapeVector2f constrainPointAngle(CapeVector2f capeVector2f, CapeVector2f capeVector2f2, double d, double d2) {
      double d3 = d2 / 57.2958;
      float f = capeVector2f2.x - capeVector2f.x;
      float f2 = capeVector2f2.y - capeVector2f.y;
      if (d < 0.0) {
         d3 *= -1.0;
      }

      double d4 = Math.cos(d3);
      double d5 = Math.sin(d3);
      return new CapeVector2f(
         (float)((double)f * d4 - (double)f2 * d5 + (double)capeVector2f.x), (float)((double)f * d5 + (double)f2 * d4 + (double)capeVector2f.y)
      );
   }

   private double getAngle(CapeVector2f capeVector2f, CapeVector2f capeVector2f2, CapeVector2f capeVector2f3) {
      return Math.atan2((double)(capeVector2f3.y - capeVector2f.y), (double)(capeVector2f3.x - capeVector2f.x))
         - Math.atan2((double)(capeVector2f2.y - capeVector2f.y), (double)(capeVector2f2.x - capeVector2f.x));
   }
}
