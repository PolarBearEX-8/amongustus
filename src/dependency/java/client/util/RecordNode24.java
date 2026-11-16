package client.util;

public class RecordNode24 {
   public float[] factorArray;
   public float factor;
   public float factor4;
   public float factor2;
   public float factor3;

   public RecordNode24(float posX, float posX2, float posY, float posZ, float[] factorArray2) {
      this.factor = posX;
      this.factor2 = posX2;
      this.factor3 = posY;
      this.factor4 = posZ;
      this.factorArray = (float[])factorArray2.clone();
   }
}
