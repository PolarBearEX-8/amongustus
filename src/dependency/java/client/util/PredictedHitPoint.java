package client.util;

public class PredictedHitPoint {
   public double coordOffset;
   public boolean[] booleanValueArray = null;
   public double coordOffset2;
   public double coordOffset1;

   public PredictedHitPoint(double posX, double posY, double posZ) {
      this.coordOffset2 = posX;
      this.coordOffset1 = posY;
      this.coordOffset = posZ;
   }
}
