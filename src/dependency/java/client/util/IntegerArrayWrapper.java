package client.util;

public class IntegerArrayWrapper {
   public static final Integer[] integerArray = new Integer[65535];

   public static Integer resolveInteger(int count) {
      return count >= 0 && count < integerArray.length ? integerArray[count] : new Integer(count);
   }

   static {
      int count = 0;

      for (int index = integerArray.length; count < index; count++) {
         integerArray[count] = count;
      }
   }
}
