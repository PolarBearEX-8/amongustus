package client.util;

public class RotationMatcher {
   public double value7;
   public double value3;
   public double value;
   public double value6 = 1.0;
   public double value2;
   public double value4;
   public double value5;

   public RotationMatcher(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      this.value2 = posX;
      this.value7 = posX2;
      this.value5 = posX3;
      this.value3 = posX4;
      this.value = posZ;
      this.value4 = posY;
   }

   public RotationMatcher getRotationMatcher() {
      RotationMatcher rotationMatcher = new RotationMatcher();
      rotationMatcher.value5 = this.value5;
      rotationMatcher.value2 = this.value2;
      rotationMatcher.value4 = this.value4;
      rotationMatcher.value = this.value;
      rotationMatcher.value3 = this.value3;
      rotationMatcher.value7 = this.value7;
      rotationMatcher.value6 = this.value6;
      return rotationMatcher;
   }

   public RotationMatcher() {
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return this.getRotationMatcher();
   }
}
