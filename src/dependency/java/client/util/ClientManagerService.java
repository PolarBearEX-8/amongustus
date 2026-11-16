package client.util;

import java.util.Random;
import net.minecraft.util.MathHelper;

public class ClientManagerService extends PrimaryClientManager {
   public InternalClientManagerService[] internalClientManagerServiceArray;
   public int count;

   public ClientManagerService(Random random, int count2) {
      this.count = count2;
      this.internalClientManagerServiceArray = new InternalClientManagerService[count2];

      for (int index = 0; index < count2; index++) {
         this.internalClientManagerServiceArray[index] = new InternalClientManagerService(random);
      }
   }

   public double[] resolveDoubleArray(double[] offsetArray, int count2, int count3, int count4, int count5, int count6, int count7, double posX, double posY, double posZ) {
      double[] offsetArray2 = offsetArray;
      if (offsetArray == null) {
         offsetArray2 = new double[count5 * count6 * count7];
      } else {
         for (int index = 0; index < offsetArray2.length; index++) {
            offsetArray2[index] = 0.0;
         }
      }

      double offset = 1.0;

      for (int index2 = 0; index2 < this.count; index2++) {
         double offset2 = (double)count2 * offset * posX;
         double offset3 = (double)count3 * offset * posY;
         double offset4 = (double)count4 * offset * posZ;
         long value = MathHelper.computeTimestamp(offset2);
         long value2 = MathHelper.computeTimestamp(offset4);
         double offset5 = offset2 - (double)value;
         double offset6 = offset4 - (double)value2;
         long value3 = value % 16777216L;
         long value4 = value2 % 16777216L;
         double offset7 = offset5 + (double)value3;
         double offset8 = offset6 + (double)value4;
         this.internalClientManagerServiceArray[index2]
            .applyDoubleArray(offsetArray2, offset7, offset3, offset8, count5, count6, count7, posX * offset, posY * offset, posZ * offset, offset);
         offset /= 2.0;
      }

      return offsetArray2;
   }

   public double[] resolveDoubleArray(double[] offsetArray, int count2, int count3, int count4, int count5, double posX, double posY, double posZ) {
      return this.resolveDoubleArray(offsetArray, count2, 10, count3, count4, 1, count5, posX, 1.0, posY);
   }
}
