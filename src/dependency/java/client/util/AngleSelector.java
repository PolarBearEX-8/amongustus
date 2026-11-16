package client.util;

import client.event.HandlerListener;

public class AngleSelector implements HandlerListener {
   public final double value;
   public final double value2;
   public final double value3;

   public AngleSelector(double posX, double posY, double posZ) {
      this.value = posX;
      this.value2 = posY;
      this.value3 = posZ;
   }

   @Override
   public double getValue3() {
      return this.value3;
   }

   @Override
   public double getValue() {
      return this.value;
   }

   @Override
   public double getValue2() {
      return this.value2;
   }
}
