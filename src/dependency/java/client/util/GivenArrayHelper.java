package client.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GivenArrayHelper {
   public static Object[] resolveTargetArray(Object[] entryArray, Object[] entryArray2) {
      if (entryArray == null) {
         throw new NullPointerException("The given array is NULL");
      } else if (entryArray2.length == 0) {
         return entryArray;
      } else {
         int length = entryArray.length;
         int count = length + entryArray2.length;
         Object[] entryArray3 = (Object[])Array.newInstance(entryArray.getClass().getComponentType(), count);
         System.arraycopy(entryArray, 0, entryArray3, 0, length);
         System.arraycopy(entryArray2, 0, entryArray3, length, entryArray2.length);
         return entryArray3;
      }
   }

   public static Object[] resolveTargetArray(Object[] entryArray, Object entry, int count) {
      ArrayList list = new ArrayList<>(Arrays.asList(entryArray));
      list.add(count, entry);
      Object[] entryArray2 = (Object[])Array.newInstance(entryArray.getClass().getComponentType(), list.size());
      return list.toArray(entryArray2);
   }

   public static Object[] resolveTargetArrayByTargetArray(Object[] entryArray, Object entry) {
      if (entryArray == null) {
         throw new NullPointerException("The given array is NULL");
      } else {
         int length = entryArray.length;
         int count = length + 1;
         Object[] entryArray2 = (Object[])Array.newInstance(entryArray.getClass().getComponentType(), count);
         System.arraycopy(entryArray, 0, entryArray2, 0, length);
         entryArray2[length] = entry;
         return entryArray2;
      }
   }

   public static String resolveTextByIndexArray(int[] countArray, String text) {
      if (countArray == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(countArray.length * 5);

         for (int index = 0; index < countArray.length; index++) {
            int count = countArray[index];
            if (index > 0) {
               builder.append(text);
            }

            builder.append(String.valueOf(count));
         }

         return builder.toString();
      }
   }

   public static int[] resolveIndexArray(int[] countArray, int[] countArray2) {
      if (countArray != null && countArray2 != null) {
         int length = countArray.length;
         int[] countArray3 = new int[length + countArray2.length];
         System.arraycopy(countArray, 0, countArray3, 0, length);

         for (int index = 0; index < countArray2.length; index++) {
            countArray3[index + length] = countArray2[index];
         }

         return countArray3;
      } else {
         throw new NullPointerException("The given array is NULL");
      }
   }

   public static String resolveText(float[] factorArray, String text, String text2) {
      if (factorArray == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(factorArray.length * 5);

         for (int index = 0; index < factorArray.length; index++) {
            float factor = factorArray[index];
            if (index > 0) {
               builder.append(text);
            }

            builder.append(String.format(text2, factor));
         }

         return builder.toString();
      }
   }

   public static String resolveText(float[] factorArray, String text) {
      if (factorArray == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(factorArray.length * 5);

         for (int index = 0; index < factorArray.length; index++) {
            float factor = factorArray[index];
            if (index > 0) {
               builder.append(text);
            }

            builder.append(String.valueOf(factor));
         }

         return builder.toString();
      }
   }

   public static String resolveText(boolean[] flagArray, String text) {
      if (flagArray == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(flagArray.length * 5);

         for (int index = 0; index < flagArray.length; index++) {
            boolean flag = flagArray[index];
            if (index > 0) {
               builder.append(text);
            }

            builder.append(String.valueOf(flag));
         }

         return builder.toString();
      }
   }

   public static boolean matchesTarget(Object entry, Object[] entryArray) {
      if (entryArray == null) {
         return false;
      } else {
         for (int index = 0; index < entryArray.length; index++) {
            Object entry2 = entryArray[index];
            if (matchesTarget(entry, entry2)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean matchesTargetByTarget(Object entry, Object[] entryArray) {
      if (entryArray == null) {
         return false;
      } else {
         for (int index = 0; index < entryArray.length; index++) {
            Object entry2 = entryArray[index];
            if (entry == entry2) {
               return true;
            }
         }

         return false;
      }
   }

   public static int[] resolveIndexArray(int[] countArray, int count) {
      return resolveIndexArray(countArray, new int[]{count});
   }

   public static String resolveText(float[] factorArray) {
      return resolveText(factorArray, ", ");
   }

   public static boolean matchesTargetArray(Object[] entryArray, Object entry) {
      if (entryArray == null) {
         return false;
      } else {
         for (int index = 0; index < entryArray.length; index++) {
            if (entryArray[index] == entry) {
               return true;
            }
         }

         return false;
      }
   }

   public static String resolveText(Object[] entryArray) {
      return resolveText(entryArray, ", ");
   }

   public static Object[] resolveTargetArray(Collection collection, Class clazz) {
      if (collection == null) {
         return null;
      } else if (clazz == null) {
         return null;
      } else if (clazz.isPrimitive()) {
         throw new IllegalArgumentException("Can not make arrays with primitive elements (int, double), element class: " + clazz);
      } else {
         Object[] entryArray = (Object[])Array.newInstance(clazz, collection.size());
         return collection.toArray(entryArray);
      }
   }

   public static Object[] resolveTargetArray(Object[] entryArray, Object entry) {
      ArrayList list = new ArrayList<>(Arrays.asList(entryArray));
      list.remove(entry);
      return resolveTargetArray(list, entryArray.getClass().getComponentType());
   }

   public static boolean matchesTarget(Object entry, Object entry2) {
      return entry == entry2 ? true : (entry == null ? false : entry.equals(entry2));
   }

   public static String resolveText(Object[] entryArray, String text) {
      if (entryArray == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(entryArray.length * 5);

         for (int index = 0; index < entryArray.length; index++) {
            Object entry = entryArray[index];
            if (index > 0) {
               builder.append(text);
            }

            builder.append(String.valueOf(entry));
         }

         return builder.toString();
      }
   }

   public static int[] resolveIndexArray(Integer[] countArray) {
      if (countArray == null) {
         return null;
      } else if (countArray.length == 0) {
         return new int[0];
      } else {
         int[] countArray2 = new int[countArray.length];

         for (int index = 0; index < countArray2.length; index++) {
            countArray2[index] = countArray[index];
         }

         return countArray2;
      }
   }

   public static String resolveText(int[] countArray, String text) {
      if (countArray == null) {
         return "";
      } else {
         StringBuffer builder = new StringBuffer(countArray.length * 5);

         for (int index = 0; index < countArray.length; index++) {
            int count = countArray[index];
            if (index > 0) {
               builder.append(text);
            }

            builder.append("0x");
            builder.append(Integer.toHexString(count));
         }

         return builder.toString();
      }
   }

   public static boolean matchesIndexArray(int count, int[] countArray) {
      for (int index = 0; index < countArray.length; index++) {
         if (countArray[index] == count) {
            return true;
         }
      }

      return false;
   }

   public static String resolveText(int[] countArray) {
      return resolveTextByIndexArray(countArray, ", ");
   }
}
