package client.util;

public class RayTracePicker {
   public void applyTimeArray(long[][] valueMatrix) {
      Object entry = null;
      entry = new long[5][5];

      for (int index = 0; index < valueMatrix.length; index++) {
         System.arraycopy(valueMatrix[index], 0, ((Object[])entry)[index], 0, valueMatrix[index].length);
      }

      valueMatrix[0][0] = (long)((Object[])((Object[])entry)[0])[0];
   }
}
