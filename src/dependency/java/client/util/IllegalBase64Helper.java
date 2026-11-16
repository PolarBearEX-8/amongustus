package client.util;

import java.util.Objects;

public class IllegalBase64Helper {
   public static InputArrayController getInputArrayController() {
      return InputArrayController.inputArrayController;
   }

   public static InputArrayController getInputArrayController2() {
      return InputArrayController.inputArrayController2;
   }

   public static OutputArrayController resolveOutputArrayController(int count, byte[] byteValueArray) {
      Objects.requireNonNull(byteValueArray);
      int[] table = InputArrayController.getCountArray2();

      for (byte byteValue : byteValueArray) {
         if (table[byteValue & 255] != -1) {
            throw new IllegalArgumentException("Illegal base64 line separator character 0x" + Integer.toString(byteValue, 16));
         }
      }

      return count > 0 ? new OutputArrayController(false, byteValueArray, count >> 2 << 2, true, null) : OutputArrayController.outputArrayController;
   }

   private IllegalBase64Helper() {
   }

   public static InputArrayController getInputArrayController3() {
      return InputArrayController.inputArrayController3;
   }

   public static OutputArrayController getOutputArrayController2() {
      return OutputArrayController.outputArrayController2;
   }

   public static OutputArrayController getOutputArrayController() {
      return OutputArrayController.outputArrayController;
   }

   public static OutputArrayController getOutputArrayController3() {
      return OutputArrayController.outputArrayController3;
   }
}
