package client.util;

public class AuxiliaryCharArrayEntry {
   public static final char[] charCodeArray = "0123456789ABCDEF".toCharArray();
   private static String[] stringArray;

   public static String[] getStringArray() {
      return null;
   }

   public static synchronized byte[] resolveByteArrayByByteArray(byte[] byteValueArray, byte[] byteValueArray2, CoreByteArrayEntry coreByteArrayEntry) {
      byte[] entry = null;
      if (byteValueArray != null) {
         boolean flag = true;
         coreByteArrayEntry.applyByteArray(flag, byteValueArray2);
         int length = byteValueArray.length;
         int count2 = length;
         byte byteValue = 0;
         entry = coreByteArrayEntry.resolveByteArrayByByteArray(byteValueArray, byteValue, count2);
         coreByteArrayEntry.updateState();
         return (byte[])entry;
      } else {
         return null;
      }
   }

   public static synchronized byte[] resolveByteArray(byte[] byteValueArray, byte[] byteValueArray2, CoreByteArrayEntry coreByteArrayEntry) {
      byte[] entry = null;
      boolean flag = false;
      coreByteArrayEntry.applyByteArray(flag, byteValueArray2);
      int length = byteValueArray.length;
      byte byteValue = 0;
      entry = coreByteArrayEntry.resolveByteArray(byteValueArray, byteValue, length);
      coreByteArrayEntry.updateState();
      return (byte[])entry;
   }

   public static synchronized byte[] resolveByteArrayByByteArray(byte[] byteValueArray, byte[] byteValueArray2) {
      Object entry = null;
      entry = new CoreByteArrayEntry();
      boolean flag = false;
      ((CoreByteArrayEntry)entry).applyByteArray(flag, byteValueArray2);
      int length = byteValueArray.length;
      byte byteValue = 0;
      return ((CoreByteArrayEntry)entry).resolveByteArray(byteValueArray, byteValue, length);
   }

   public static String resolveText(byte[] byteValueArray) {
      char[] entry = null;
      int count = 0;
      entry = new char[byteValueArray.length * 2];

      for (int index = 0; index < byteValueArray.length; index++) {
         count = byteValueArray[index] & 255;
         entry[index * 2] = charCodeArray[count >>> 4];
         entry[index * 2 + 1] = charCodeArray[count & 15];
      }

      return new String((char[])entry);
   }

   public static synchronized byte[] resolveByteArray(byte[] byteValueArray, byte[] byteValueArray2) {
      Object entry = null;
      if (byteValueArray != null) {
         entry = new CoreByteArrayEntry();
         boolean flag = true;
         ((CoreByteArrayEntry)entry).applyByteArray(flag, byteValueArray2);
         int length = byteValueArray.length;
         int count2 = length;
         byte byteValue = 0;
         return ((CoreByteArrayEntry)entry).resolveByteArrayByByteArray(byteValueArray, byteValue, count2);
      } else {
         return null;
      }
   }
}
