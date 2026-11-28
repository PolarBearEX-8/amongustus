package net.optifine.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ArrayUtils {

   public static String arrayToString(float[] floats, String str, String str2) {
      if (floats == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(floats.length * 5);

         for (int i = 0; i < floats.length; i++) {
            float f = floats[i];
            if (i > 0) {
               sb.append(str);
            }

            sb.append(String.format(str2, f));
         }

         return sb.toString();
      }
   }

   public static boolean equals(Object obj, Object obj2) {
      return obj == obj2 ? true : (obj == null ? false : obj.equals(obj2));
   }

   public static String arrayToString4(boolean[] flags, String str) {
      if (flags == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(flags.length * 5);

         for (int i = 0; i < flags.length; i++) {
            boolean flag = flags[i];
            if (i > 0) {
               sb.append(str);
            }

            sb.append(String.valueOf(flag));
         }

         return sb.toString();
      }
   }

   public static String arrayToString3(int[] ints) {
      return arrayToString2(ints, ", ");
   }

   public static String arrayToHexString(int[] ints, String str) {
      if (ints == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(ints.length * 5);

         for (int i = 0; i < ints.length; i++) {
            int n = ints[i];
            if (i > 0) {
               sb.append(str);
            }

            sb.append("0x");
            sb.append(Integer.toHexString(n));
         }

         return sb.toString();
      }
   }

   public static int[] addIntToArray(int[] ints, int n) {
      return addIntsToArray(ints, new int[]{n});
   }

   public static Object[] collectionToArray(Collection items, Class clazz) {
      if (items == null) {
         return null;
      } else if (clazz == null) {
         return null;
      } else if (clazz.isPrimitive()) {
         throw new IllegalArgumentException("Can not make arrays with primitive elements (int, double), element class: " + clazz);
      } else {
         return items.toArray((Object[])Array.newInstance(clazz, items.size()));
      }
   }

   public static String arrayToString2(int[] ints, String str) {
      if (ints == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(ints.length * 5);

         for (int i = 0; i < ints.length; i++) {
            int n = ints[i];
            if (i > 0) {
               sb.append(str);
            }

            sb.append(String.valueOf(n));
         }

         return sb.toString();
      }
   }

   public static boolean isSameOne(Object obj, Object[] array) {
      if (array == null) {
         return false;
      } else {
         for (int i = 0; i < array.length; i++) {
            if (obj == array[i]) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean equalsOne(int n, int[] ints) {
      for (int i = 0; i < ints.length; i++) {
         if (ints[i] == n) {
            return true;
         }
      }

      return false;
   }

   public static Object[] addObjectToArray(Object[] array, Object obj, int n) {
      ArrayList list = new ArrayList<>(Arrays.asList(array));
      list.add(n, obj);
      return list.toArray((Object[])Array.newInstance(array.getClass().getComponentType(), list.size()));
   }

   public static Object[] removeObjectFromArray(Object[] array, Object obj) {
      ArrayList list = new ArrayList<>(Arrays.asList(array));
      list.remove(obj);
      return collectionToArray(list, array.getClass().getComponentType());
   }

   public static String arrayToString3(Object[] array, String str) {
      if (array == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(array.length * 5);

         for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            if (i > 0) {
               sb.append(str);
            }

            sb.append(String.valueOf(obj));
         }

         return sb.toString();
      }
   }

   public static Object[] addObjectsToArray(Object[] array, Object[] array2) {
      if (array == null) {
         throw new NullPointerException("The given array is NULL");
      } else if (array2.length == 0) {
         return array;
      } else {
         int length = array.length;
         int n = length + array2.length;
         Object[] array3 = (Object[])Array.newInstance(array.getClass().getComponentType(), n);
         System.arraycopy(array, 0, array3, 0, length);
         System.arraycopy(array2, 0, array3, length, array2.length);
         return array3;
      }
   }

   public static Object[] addObjectToArray(Object[] array, Object obj) {
      if (array == null) {
         throw new NullPointerException("The given array is NULL");
      } else {
         int length = array.length;
         int n = length + 1;
         Object[] array2 = (Object[])Array.newInstance(array.getClass().getComponentType(), n);
         System.arraycopy(array, 0, array2, 0, length);
         array2[length] = obj;
         return array2;
      }
   }

   public static int[] addIntsToArray(int[] ints, int[] ints2) {
      if (ints != null && ints2 != null) {
         int length = ints.length;
         int[] ints3 = new int[length + ints2.length];
         System.arraycopy(ints, 0, ints3, 0, length);

         for (int i = 0; i < ints2.length; i++) {
            ints3[i + length] = ints2[i];
         }

         return ints3;
      } else {
         throw new NullPointerException("The given array is NULL");
      }
   }

   public static int[] toPrimitive(Integer[] nums) {
      if (nums == null) {
         return null;
      } else if (nums.length == 0) {
         return new int[0];
      } else {
         int[] ints = new int[nums.length];

         for (int i = 0; i < ints.length; i++) {
            ints[i] = nums[i];
         }

         return ints;
      }
   }

   public static String arrayToString(Object[] array) {
      return arrayToString3(array, ", ");
   }

   public static boolean contains(Object[] array, Object obj) {
      if (array == null) {
         return false;
      } else {
         for (int i = 0; i < array.length; i++) {
            if (array[i] == obj) {
               return true;
            }
         }

         return false;
      }
   }

   public static String arrayToString2(float[] floats) {
      return arrayToString(floats, ", ");
   }

   public static String arrayToString(float[] floats, String str) {
      if (floats == null) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer(floats.length * 5);

         for (int i = 0; i < floats.length; i++) {
            float f = floats[i];
            if (i > 0) {
               sb.append(str);
            }

            sb.append(String.valueOf(f));
         }

         return sb.toString();
      }
   }

   public static boolean equalsOne2(Object obj, Object[] array) {
      if (array == null) {
         return false;
      } else {
         for (int i = 0; i < array.length; i++) {
            if (equals(obj, array[i])) {
               return true;
            }
         }

         return false;
      }
   }
}
