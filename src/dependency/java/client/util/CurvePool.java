package client.util;

import java.util.concurrent.Callable;

public class CurvePool implements Callable {
   public final int[][] countArray;
   public final ServiceStringEntry serviceStringEntry;

   public CurvePool(ServiceStringEntry serviceStringEntry2, int[][] countMatrix) {
      this.serviceStringEntry = serviceStringEntry2;
      this.countArray = countMatrix;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      StringBuilder builder = new StringBuilder();

      for (int[] countArray2 : this.countArray) {
         if (builder.length() > 0) {
            builder.append(", ");
         }

         builder.append(countArray2 == null ? "null" : countArray2.length);
      }

      return builder.toString();
   }
}
