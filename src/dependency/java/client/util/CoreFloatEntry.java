package client.util;

public class CoreFloatEntry {
   public float factor;
   public float factor2;

   public CoreFloatEntry getCoreFloatEntry() {
      return new CoreFloatEntry(this.factor * 0.8F, this.factor2 * 0.6F);
   }

   public CoreFloatEntry(float factor3, float factor4) {
      this.factor = factor3;
      this.factor2 = factor4;
   }
}
