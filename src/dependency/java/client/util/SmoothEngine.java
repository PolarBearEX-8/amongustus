package client.util;

import java.util.IdentityHashMap;
import java.util.Map;

public class SmoothEngine {
   public static final int count = 0;
   public static final int count2 = 1;
   public static final int count3 = 2;
   public static final int count4 = 3;
   public static final int count5 = 4;

   private static LocalIntArrayEntry resolveLocalIntArrayEntry(
      LocalIntArrayEntry localIntArrayEntry, ServiceStringEntry serviceStringEntry, int count6, int count7, int count8, int count9, int count10
   ) {
      int[] countArray = (int[])localIntArrayEntry.getIndexArray().clone();
      ServiceStringEntry serviceStringEntry2 = localIntArrayEntry.getServiceStringEntry();

      for (int index = 0; index < 4; index++) {
         applyIndexArray(countArray, index, serviceStringEntry2, serviceStringEntry, count6, count7, count8, count9, count10);
      }

      return new LocalIntArrayEntry(countArray, localIntArrayEntry.getCount(), localIntArrayEntry.getEnumFacing(), serviceStringEntry);
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(int count6, ServiceStringEntry[] serviceStringEntryArray, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry) {
      return McpatcherCtmDefaultManager.resolveLocalIntArrayEntryArray(serviceStringEntryArray[count6], localIntArrayEntry, baseIBlockStateEntry);
   }

   private static LocalIntArrayEntry resolveLocalIntArrayEntry(
      ServiceStringEntry serviceStringEntry, DOWNRIGHTEnum dOWNRIGHTEnum, int count6, int count7, int count8, int count9, int count10, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      Map[][] mapArray = McpatcherCtmDefaultManager.getMapArray2();
      if (mapArray == null) {
         return localIntArrayEntry;
      } else {
         int count11 = serviceStringEntry.getCount8();
         if (count11 >= 0 && count11 < mapArray.length) {
            Map[] mapArray2 = mapArray[count11];
            if (mapArray2 == null) {
               mapArray2 = new Map[DOWNRIGHTEnum.VALUES.length];
               mapArray[count11] = mapArray2;
            }

            Map entry = mapArray2[dOWNRIGHTEnum.ordinal()];
            if (entry == null) {
               entry = new IdentityHashMap(1);
               mapArray2[dOWNRIGHTEnum.ordinal()] = (Map)entry;
            }

            LocalIntArrayEntry localIntArrayEntry2 = (LocalIntArrayEntry)entry.get(localIntArrayEntry);
            if (localIntArrayEntry2 == null) {
               localIntArrayEntry2 = resolveLocalIntArrayEntry(localIntArrayEntry, serviceStringEntry, count10, count6, count7, count8, count9);
               entry.put(localIntArrayEntry, localIntArrayEntry2);
            }

            return localIntArrayEntry2;
         } else {
            return localIntArrayEntry;
         }
      }
   }

   private static LocalIntArrayEntry resolveLocalIntArrayEntry(
      ServiceStringEntry serviceStringEntry, DOWNRIGHTEnum dOWNRIGHTEnum, int count6, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      switch (SmoothEngineDOWNRIGHTEnumSwitchMap.countArray[dOWNRIGHTEnum.ordinal()]) {
         case 1:
            return resolveLocalIntArrayEntry(serviceStringEntry, dOWNRIGHTEnum, 0, 0, 16, 8, count6, localIntArrayEntry, baseIBlockStateEntry);
         case 2:
            return resolveLocalIntArrayEntry(serviceStringEntry, dOWNRIGHTEnum, 8, 0, 16, 8, count6, localIntArrayEntry, baseIBlockStateEntry);
         case 3:
            return resolveLocalIntArrayEntry(serviceStringEntry, dOWNRIGHTEnum, 8, 0, 16, 16, count6, localIntArrayEntry, baseIBlockStateEntry);
         case 4:
            return resolveLocalIntArrayEntry(serviceStringEntry, dOWNRIGHTEnum, 8, 8, 16, 16, count6, localIntArrayEntry, baseIBlockStateEntry);
         case 5:
            return resolveLocalIntArrayEntry(serviceStringEntry, dOWNRIGHTEnum, 0, 8, 16, 16, count6, localIntArrayEntry, baseIBlockStateEntry);
         case 6:
            return resolveLocalIntArrayEntry(serviceStringEntry, dOWNRIGHTEnum, 0, 8, 8, 16, count6, localIntArrayEntry, baseIBlockStateEntry);
         case 7:
            return resolveLocalIntArrayEntry(serviceStringEntry, dOWNRIGHTEnum, 0, 0, 8, 16, count6, localIntArrayEntry, baseIBlockStateEntry);
         case 8:
            return resolveLocalIntArrayEntry(serviceStringEntry, dOWNRIGHTEnum, 0, 0, 8, 8, count6, localIntArrayEntry, baseIBlockStateEntry);
         default:
            return localIntArrayEntry;
      }
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      DOWNRIGHTEnum dOWNRIGHTEnum,
      int count6,
      DOWNRIGHTEnum dOWNRIGHTEnum2,
      int count7,
      DOWNRIGHTEnum dOWNRIGHTEnum3,
      int count8,
      ServiceStringEntry[] serviceStringEntryArray,
      int count9,
      LocalIntArrayEntry localIntArrayEntry,
      BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      LocalIntArrayEntry localIntArrayEntry2 = resolveLocalIntArrayEntry(serviceStringEntryArray[count6], dOWNRIGHTEnum, count9, localIntArrayEntry, baseIBlockStateEntry);
      LocalIntArrayEntry localIntArrayEntry3 = resolveLocalIntArrayEntry(serviceStringEntryArray[count7], dOWNRIGHTEnum2, count9, localIntArrayEntry, baseIBlockStateEntry);
      LocalIntArrayEntry localIntArrayEntry4 = resolveLocalIntArrayEntry(serviceStringEntryArray[count8], dOWNRIGHTEnum3, count9, localIntArrayEntry, baseIBlockStateEntry);
      return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry2, localIntArrayEntry3, localIntArrayEntry4);
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      int count6, int count7, int count8, int count9, ServiceStringEntry[] serviceStringEntryArray, int count10, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      return count6 == count7
         ? (
            count8 == count9
               ? resolveLocalIntArrayEntryArray(DOWNRIGHTEnum.UP, count6, DOWNRIGHTEnum.DOWN, count8, serviceStringEntryArray, count10, localIntArrayEntry, baseIBlockStateEntry)
               : resolveLocalIntArrayEntryArray(DOWNRIGHTEnum.UP, count6, DOWNRIGHTEnum.DOWN_LEFT, count8, DOWNRIGHTEnum.DOWN_RIGHT, count9, serviceStringEntryArray, count10, localIntArrayEntry, baseIBlockStateEntry)
         )
         : (
            count8 == count9
               ? resolveLocalIntArrayEntryArray(DOWNRIGHTEnum.UP_LEFT, count6, DOWNRIGHTEnum.UP_RIGHT, count7, DOWNRIGHTEnum.DOWN, count8, serviceStringEntryArray, count10, localIntArrayEntry, baseIBlockStateEntry)
               : (
                  count6 == count8
                     ? (
                        count7 == count9
                           ? resolveLocalIntArrayEntryArray(DOWNRIGHTEnum.LEFT, count6, DOWNRIGHTEnum.RIGHT, count7, serviceStringEntryArray, count10, localIntArrayEntry, baseIBlockStateEntry)
                           : resolveLocalIntArrayEntryArray(
                              DOWNRIGHTEnum.LEFT, count6, DOWNRIGHTEnum.UP_RIGHT, count7, DOWNRIGHTEnum.DOWN_RIGHT, count9, serviceStringEntryArray, count10, localIntArrayEntry, baseIBlockStateEntry
                           )
                     )
                     : (
                        count7 == count9
                           ? resolveLocalIntArrayEntryArray(
                              DOWNRIGHTEnum.UP_LEFT, count6, DOWNRIGHTEnum.DOWN_LEFT, count8, DOWNRIGHTEnum.RIGHT, count7, serviceStringEntryArray, count10, localIntArrayEntry, baseIBlockStateEntry
                           )
                           : resolveLocalIntArrayEntryArray(
                              DOWNRIGHTEnum.UP_LEFT,
                              count6,
                              DOWNRIGHTEnum.UP_RIGHT,
                              count7,
                              DOWNRIGHTEnum.DOWN_LEFT,
                              count8,
                              DOWNRIGHTEnum.DOWN_RIGHT,
                              count9,
                              serviceStringEntryArray,
                              count10,
                              localIntArrayEntry,
                              baseIBlockStateEntry
                           )
                     )
               )
         );
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      DOWNRIGHTEnum dOWNRIGHTEnum, int count6, DOWNRIGHTEnum dOWNRIGHTEnum2, int count7, ServiceStringEntry[] serviceStringEntryArray, int count8, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      LocalIntArrayEntry localIntArrayEntry2 = resolveLocalIntArrayEntry(serviceStringEntryArray[count6], dOWNRIGHTEnum, count8, localIntArrayEntry, baseIBlockStateEntry);
      LocalIntArrayEntry localIntArrayEntry3 = resolveLocalIntArrayEntry(serviceStringEntryArray[count7], dOWNRIGHTEnum2, count8, localIntArrayEntry, baseIBlockStateEntry);
      return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry2, localIntArrayEntry3);
   }

   public static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      int count6, AuxiliaryPngManager auxiliaryPngManager, int count7, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      if (auxiliaryPngManager.countArray2 != null && count6 >= 0 && count6 < auxiliaryPngManager.countArray2.length) {
         int count8 = auxiliaryPngManager.countArray2[count6];
         if (count8 >= 0 && count8 <= auxiliaryPngManager.serviceStringEntryArray.length) {
            return resolveLocalIntArrayEntryArray(count8, auxiliaryPngManager.serviceStringEntryArray, localIntArrayEntry, baseIBlockStateEntry);
         }
      }

      switch (count6) {
         case 1:
            return resolveLocalIntArrayEntryArray(0, 3, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 2:
            return resolveLocalIntArrayEntryArray(3, auxiliaryPngManager.serviceStringEntryArray, localIntArrayEntry, baseIBlockStateEntry);
         case 3:
            return resolveLocalIntArrayEntryArray(3, 0, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 4:
            return resolveLocalIntArrayEntryArray(0, 3, 2, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 5:
            return resolveLocalIntArrayEntryArray(3, 0, 4, 2, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 6:
            return resolveLocalIntArrayEntryArray(2, 4, 2, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 7:
            return resolveLocalIntArrayEntryArray(3, 3, 4, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 8:
            return resolveLocalIntArrayEntryArray(4, 1, 4, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 9:
            return resolveLocalIntArrayEntryArray(4, 4, 4, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 10:
            return resolveLocalIntArrayEntryArray(1, 4, 1, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 11:
            return resolveLocalIntArrayEntryArray(1, 1, 4, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 12:
            return resolveLocalIntArrayEntryArrayByServiceStringEnt(0, 2, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 13:
            return resolveLocalIntArrayEntryArray(0, 3, 2, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 14:
            return resolveLocalIntArrayEntryArrayByServiceStringEnt(3, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 15:
            return resolveLocalIntArrayEntryArray(3, 0, 1, 2, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 16:
            return resolveLocalIntArrayEntryArray(2, 4, 0, 3, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 17:
            return resolveLocalIntArrayEntryArray(4, 2, 3, 0, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 18:
            return resolveLocalIntArrayEntryArray(4, 4, 3, 3, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 19:
            return resolveLocalIntArrayEntryArray(4, 2, 4, 2, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 20:
            return resolveLocalIntArrayEntryArray(1, 4, 4, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 21:
            return resolveLocalIntArrayEntryArray(4, 4, 1, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 22:
            return resolveLocalIntArrayEntryArray(4, 4, 1, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 23:
            return resolveLocalIntArrayEntryArray(4, 1, 4, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 24:
            return resolveLocalIntArrayEntryArray(2, auxiliaryPngManager.serviceStringEntryArray, localIntArrayEntry, baseIBlockStateEntry);
         case 25:
            return resolveLocalIntArrayEntryArray(2, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 26:
            return resolveLocalIntArrayEntryArray(1, auxiliaryPngManager.serviceStringEntryArray, localIntArrayEntry, baseIBlockStateEntry);
         case 27:
            return resolveLocalIntArrayEntryArray(1, 2, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 28:
            return resolveLocalIntArrayEntryArray(2, 4, 2, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 29:
            return resolveLocalIntArrayEntryArray(3, 3, 1, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 30:
            return resolveLocalIntArrayEntryArray(2, 1, 2, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 31:
            return resolveLocalIntArrayEntryArray(3, 3, 4, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 32:
            return resolveLocalIntArrayEntryArray(1, 1, 1, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 33:
            return resolveLocalIntArrayEntryArray(1, 1, 4, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 34:
            return resolveLocalIntArrayEntryArray(4, 1, 1, 4, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 35:
            return resolveLocalIntArrayEntryArray(1, 4, 4, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 36:
            return resolveLocalIntArrayEntryArrayByServiceStringEnt(2, 0, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 37:
            return resolveLocalIntArrayEntryArray(2, 1, 0, 3, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 38:
            return resolveLocalIntArrayEntryArrayByServiceStringEnt(1, 3, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 39:
            return resolveLocalIntArrayEntryArray(1, 2, 3, 0, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 40:
            return resolveLocalIntArrayEntryArray(4, 1, 3, 3, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 41:
            return resolveLocalIntArrayEntryArray(1, 2, 4, 2, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 42:
            return resolveLocalIntArrayEntryArray(1, 4, 3, 3, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 43:
            return resolveLocalIntArrayEntryArray(4, 2, 1, 2, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 44:
            return resolveLocalIntArrayEntryArray(1, 4, 1, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 45:
            return resolveLocalIntArrayEntryArray(4, 1, 1, 1, auxiliaryPngManager.serviceStringEntryArray, count7, localIntArrayEntry, baseIBlockStateEntry);
         case 46:
            return resolveLocalIntArrayEntryArray(4, auxiliaryPngManager.serviceStringEntryArray, localIntArrayEntry, baseIBlockStateEntry);
         default:
            return resolveLocalIntArrayEntryArray(0, auxiliaryPngManager.serviceStringEntryArray, localIntArrayEntry, baseIBlockStateEntry);
      }
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      DOWNRIGHTEnum dOWNRIGHTEnum,
      int count6,
      DOWNRIGHTEnum dOWNRIGHTEnum2,
      int count7,
      DOWNRIGHTEnum dOWNRIGHTEnum3,
      int count8,
      DOWNRIGHTEnum dOWNRIGHTEnum4,
      int count9,
      ServiceStringEntry[] serviceStringEntryArray,
      int count10,
      LocalIntArrayEntry localIntArrayEntry,
      BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      LocalIntArrayEntry localIntArrayEntry2 = resolveLocalIntArrayEntry(serviceStringEntryArray[count6], dOWNRIGHTEnum, count10, localIntArrayEntry, baseIBlockStateEntry);
      LocalIntArrayEntry localIntArrayEntry3 = resolveLocalIntArrayEntry(serviceStringEntryArray[count7], dOWNRIGHTEnum2, count10, localIntArrayEntry, baseIBlockStateEntry);
      LocalIntArrayEntry localIntArrayEntry4 = resolveLocalIntArrayEntry(serviceStringEntryArray[count8], dOWNRIGHTEnum3, count10, localIntArrayEntry, baseIBlockStateEntry);
      LocalIntArrayEntry localIntArrayEntry5 = resolveLocalIntArrayEntry(serviceStringEntryArray[count9], dOWNRIGHTEnum4, count10, localIntArrayEntry, baseIBlockStateEntry);
      return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry2, localIntArrayEntry3, localIntArrayEntry4, localIntArrayEntry5);
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      int count6, int count7, ServiceStringEntry[] serviceStringEntryArray, int count8, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      return resolveLocalIntArrayEntryArray(DOWNRIGHTEnum.LEFT, count6, DOWNRIGHTEnum.RIGHT, count7, serviceStringEntryArray, count8, localIntArrayEntry, baseIBlockStateEntry);
   }

   private static void applyIndexArray(int[] countArray, int count6, ServiceStringEntry serviceStringEntry, ServiceStringEntry serviceStringEntry2, int count7, int count8, int count9, int count10, int count11) {
      int count12 = countArray.length / 4 * count6;
      float factor = Float.intBitsToFloat(countArray[count12 + 4]);
      float factor2 = Float.intBitsToFloat(countArray[count12 + 4 + 1]);
      double offset = serviceStringEntry.computeValue(factor);
      double offset2 = serviceStringEntry.computeValueByFloat(factor2);
      float factor3 = Float.intBitsToFloat(countArray[count12 + 0]);
      float factor4 = Float.intBitsToFloat(countArray[count12 + 1]);
      float factor5 = Float.intBitsToFloat(countArray[count12 + 2]);
      float factor6;
      float factor7;
      switch (count7) {
         case 0:
            factor6 = factor3;
            factor7 = 1.0F - factor5;
            break;
         case 1:
            factor6 = factor3;
            factor7 = factor5;
            break;
         case 2:
            factor6 = 1.0F - factor3;
            factor7 = 1.0F - factor4;
            break;
         case 3:
            factor6 = factor3;
            factor7 = 1.0F - factor4;
            break;
         case 4:
            factor6 = factor5;
            factor7 = 1.0F - factor4;
            break;
         case 5:
            factor6 = 1.0F - factor5;
            factor7 = 1.0F - factor4;
            break;
         default:
            return;
      }

      float factor8 = 15.968F;
      float factor9 = 15.968F;
      if (offset < (double)count8) {
         factor6 = (float)((double)factor6 + ((double)count8 - offset) / (double)factor8);
         offset = (double)count8;
      }

      if (offset > (double)count10) {
         factor6 = (float)((double)factor6 - (offset - (double)count10) / (double)factor8);
         offset = (double)count10;
      }

      if (offset2 < (double)count9) {
         factor7 = (float)((double)factor7 + ((double)count9 - offset2) / (double)factor9);
         offset2 = (double)count9;
      }

      if (offset2 > (double)count11) {
         factor7 = (float)((double)factor7 - (offset2 - (double)count11) / (double)factor9);
         offset2 = (double)count11;
      }

      switch (count7) {
         case 0:
            factor3 = factor6;
            factor5 = 1.0F - factor7;
            break;
         case 1:
            factor3 = factor6;
            factor5 = factor7;
            break;
         case 2:
            factor3 = 1.0F - factor6;
            factor4 = 1.0F - factor7;
            break;
         case 3:
            factor3 = factor6;
            factor4 = 1.0F - factor7;
            break;
         case 4:
            factor5 = factor6;
            factor4 = 1.0F - factor7;
            break;
         case 5:
            factor5 = 1.0F - factor6;
            factor4 = 1.0F - factor7;
            break;
         default:
            return;
      }

      countArray[count12 + 4] = Float.floatToRawIntBits(serviceStringEntry2.computeFactorByDouble(offset));
      countArray[count12 + 4 + 1] = Float.floatToRawIntBits(serviceStringEntry2.computeFactor(offset2));
      countArray[count12 + 0] = Float.floatToRawIntBits(factor3);
      countArray[count12 + 1] = Float.floatToRawIntBits(factor4);
      countArray[count12 + 2] = Float.floatToRawIntBits(factor5);
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArrayByServiceStringEnt(
      int count6, int count7, ServiceStringEntry[] serviceStringEntryArray, int count8, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      return resolveLocalIntArrayEntryArray(DOWNRIGHTEnum.UP, count6, DOWNRIGHTEnum.DOWN, count7, serviceStringEntryArray, count8, localIntArrayEntry, baseIBlockStateEntry);
   }
}
