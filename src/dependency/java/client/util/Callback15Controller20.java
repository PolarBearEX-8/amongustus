package client.util;

public class Callback15Controller20 extends Controller20 {
   public final Callback15 callback15;
   public final float factor;
   public final float factor2;
   public final float factor3;

   public float getFactor() {
      return this.factor;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public Callback15 getCallback15() {
      return this.callback15;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public Callback15Controller20(int count, String text, boolean flag, Callback15 callback, float posX, float posY, float posZ) {
      super(count, text, flag);
      this.callback15 = callback;
      this.factor = posX;
      this.factor2 = posY;
      this.factor3 = posZ;
   }
}
