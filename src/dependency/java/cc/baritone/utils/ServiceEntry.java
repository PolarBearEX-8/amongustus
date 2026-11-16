package cc.baritone.utils;

public interface ServiceEntry {
   double value = 4.63284688441047;
   double value2 = 9.09090909090909;
   double value3 = 9.26569376882094;
   double value4 = 8.51063829787234;
   double value5 = 6.666666666666667;
   double value6 = 15.384615384615383;
   double value7 = 3.563791874554526;
   double value8 = 0.7692444761225944;
   double value9 = 3.7062775075283763;
   double value10 = 0.9265693768820937;
   double value11 = 1000000.0;
   double[] valueArray = getDoubleArray();
   double value12 = computeValue(1.25);
   double value13 = computeValue(0.25);
   double value14 = value12 - value13;

   static double pow(int count) {
      return (Math.pow(0.98, (double)count) - 1.0) * -3.92;
   }

   static double[] getDoubleArray() {
      double[] offsetArray = new double[257];

      for (int index = 0; index < 257; index++) {
         offsetArray[index] = computeValue((double)index);
      }

      return offsetArray;
   }

   static double computeValue(double offset) {
      if (offset == 0.0) {
         return 0.0;
      } else {
         double offset2 = offset;
         int count = 0;

         while (true) {
            double offset3 = pow(count);
            if (offset2 <= offset3) {
               return (double)count + offset2 / offset3;
            }

            offset2 -= offset3;
            count++;
         }
      }
   }

   static double pow(double offset) {
      return -3.92 * (99.0 - 49.5 * (Math.pow(0.98, offset) + 1.0) - offset);
   }
}
