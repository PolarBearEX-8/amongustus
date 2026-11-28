package net.augustus.utils.math;

import net.augustus.utils.TimeHelper;

public class Vec2d {
   public double x;
   public static final Vec2d ZERO = new Vec2d(0.0, 0.0);
   public double y;

   public Vec2d clampLength(double d) {
      double d2 = 0.0;
      return d2 > d ? new Vec2d(this.x / d2 * d, this.y / d2 * d) : new Vec2d(this.x, this.y);
   }

   public Vec2d perpendicular() {
      double d = 1.0 / this.x;
      double d2;
      if (!Double.isFinite(d)) {
         d2 = 0.0;
         d = 1.0;
      } else {
         d2 = -this.x * d / this.y;
         if (!Double.isFinite(d2)) {
            d2 = 1.0;
            d = 0.0;
         }
      }

      return new Vec2d(d, d2);
   }

   public Vec2d add(Vec2d vec2d) {
      return new Vec2d(this.x + vec2d.x, this.y + vec2d.y);
   }

   public Vec2d normalize() {
      return this.scale(1.0 / this.length());
   }

   public Vec2d scale(double d) {
      return new Vec2d(this.x * d, this.y * d);
   }

   public Vec2d(double d, double d2) {
      this.x = d;
      this.y = d2;
   }

   public Vec2d swapXY() {
      return new Vec2d(this.y, this.x);
   }

   public Vec2d subtract(Vec2d vec2d) {
      return new Vec2d(this.x - vec2d.x, this.y - vec2d.y);
   }

   public double dot(Vec2d vec2d) {
      return this.x * vec2d.x + this.y * vec2d.y;
   }

   public double length() {
      return Math.sqrt(this.x * this.x + this.y * this.y);
   }


   public double angleDegrees(Vec2d vec2d) {
      double d = Math.acos(this.dot(vec2d) / (this.length() * vec2d.length())) * 180.0 / 3.141592653589793;
      return d > 180.0 ? 180.0 - d : d;
   }

   public Vec2d lerp(Vec2d vec2d, double d) {
      return new Vec2d(MathStatisticsUtil.lerp(this.x, vec2d.x, d), MathStatisticsUtil.lerp(this.y, vec2d.y, d));
   }

   public Vec2d rotate(double d) {
      double d2 = Math.cos(d);
      double d3 = Math.sin(d);
      return new Vec2d(this.x * d2 - this.y * d3, this.x * d3 + this.y * d2);
   }

   public Vec2d(Vec2d vec2d) {
      this(vec2d.x, vec2d.y);
   }

   public boolean equalsVec(Vec2d vec2d) {
      return this.x == vec2d.x && this.y == vec2d.y;
   }
}
