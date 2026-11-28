package net.augustus.render.waveycape;


public class CapeVector2f {
   public float x;
   public float y;

   public CapeVector2f normalize() {
      float f = (float)Math.sqrt((double)(this.x * this.x + this.y * this.y));
      if (f < 1.0E-4F) {
         this.x = 0.0F;
         this.y = 0.0F;
      } else {
         this.x /= f;
         this.y /= f;
      }

      return this;
   }

   public CapeVector2f mul(float f) {
      this.x *= f;
      this.y *= f;
      return this;
   }

   public CapeVector2f subtract(CapeVector2f capeVector2f) {
      this.x = this.x - capeVector2f.x;
      this.y = this.y - capeVector2f.y;
      return this;
   }

   public CapeVector2f copy() {
      return new CapeVector2f(this.x, this.y);
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return this.copy();
   }

   public CapeVector2f(float f, float f2) {
      this.x = f;
      this.y = f2;
   }

   public CapeVector2f div(float f) {
      this.x /= f;
      this.y /= f;
      return this;
   }

   public CapeVector2f add(CapeVector2f capeVector2f) {
      this.x = this.x + capeVector2f.x;
      this.y = this.y + capeVector2f.y;
      return this;
   }

   @Override
   public String toString() {
      return "Vector2 [x=" + this.x + ", y=" + this.y + "]";
   }

   public void copy(CapeVector2f capeVector2f) {
      this.x = capeVector2f.x;
      this.y = capeVector2f.y;
   }
}
