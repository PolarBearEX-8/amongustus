package client.util;

import net.minecraft.util.Vec3;

public class Controller6Controller {
   public Controller6 controller6;
   public final double value;
   public final double value2;
   public Controller4 controller4;
   public final double value3;
   public final double value4 = 0.05;

   public void applySimulationSnapshot(SimulationSnapshot simulationSnapshot, SimulationSnapshot simulationSnapshot2, double offset) {
      double value5 = simulationSnapshot2.value2;
      double value6 = simulationSnapshot2.value;
      double value7 = simulationSnapshot.value2;
      double value8 = simulationSnapshot.value;
      this.applyDouble(value8, value7, value6, value5, offset);
   }

   public Controller6Controller() {
      this.value3 = 0.05;
      this.value = 8.2;
      this.value2 = 2.4;
      this.controller6 = new Controller6();
      this.controller4 = new Controller4();
   }

   public void applyDouble(double posX, double posX2, double posX3, double posY, double posZ) {
      double offset = (posX3 - posX) / posZ;
      double offset2 = (posY - posX2) / posZ;
      double offset3 = 0.0;
      double offset4 = 0.0;
      double[] offsetArray = new double[]{posX, offset, offset3, posX2, offset2, offset4};
      this.controller6.copyOf(offsetArray);
      double offset5 = Math.sqrt(offset * offset + offset2 * offset2);
      double offset6 = Math.atan2(offset2, offset);
      double offset7 = 0.0;
      double[] offsetArray2 = new double[]{posX, posX2, offset5, offset6, offset7};
      this.controller4.copyOf(offsetArray2);
   }

   public void applyDouble(double offset, double offset2) {
      double[] offsetArray = new double[]{offset, offset2};
      this.controller6.applyDoubleArray(offsetArray);
      this.controller4.applyDoubleArray(offsetArray);
   }

   public double[] getDoubleArray() {
      double offset = 0.05;
      double[] offsetArray = this.controller6.resolveDoubleArray(offset);
      double offset2 = 0.05;
      double[] offsetArray2 = this.controller4.resolveDoubleArray(offset2);
      double offset3 = Math.abs(this.controller4.getValue());
      double offset4 = 1.0 / (1.0 + Math.exp(-8.2 * (offset3 - 2.4)));
      double offset5 = (1.0 - offset4) * offsetArray[0] + offset4 * offsetArray2[0];
      double offset6 = (1.0 - offset4) * offsetArray[1] + offset4 * offsetArray2[1];
      return new double[]{offset5, offset6};
   }

   public void applyVec3(Vec3 vec, Vec3 vec2, double offset) {
      double posZ = vec2.zCoord;
      double posX = vec2.xCoord;
      double posZ2 = vec.zCoord;
      double posX2 = vec.xCoord;
      this.applyDouble(posX2, posZ2, posX, posZ, offset);
   }

   public void applyVecByVec3(Vec3 vec, Vec3 vec2, double offset) {
      double offset2 = 0.0;
      double posY = vec2.yCoord;
      double offset3 = 0.0;
      double posY2 = vec.yCoord;
      this.applyDouble(posY2, offset3, posY, offset2, offset);
   }
}
