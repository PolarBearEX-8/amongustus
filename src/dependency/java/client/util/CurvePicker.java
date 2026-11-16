package client.util;

import java.util.Random;

public class CurvePicker {
   public int count;
   public long[] timestampArray;
   public static CurvePicker curvePicker = new CurvePicker();

   private void applyIndex(int count2, int count3, int count4) {
      int count5 = count3;

      while (count5 < count2) {
         this.applyIndex(count5);
         count5 += count4;
      }
   }

   private boolean checkIndex(int count2) {
      int count3 = 0;
      count3 = computeCount(count2);
      return (this.timestampArray[count3] & computeTimestamp(count2)) != 0L;
   }

   private static int computeCount(int count2) {
      return count2 >>> 6;
   }

   private CurvePicker() {
      this.count = 9600;
      this.timestampArray = new long[computeCount(this.count - 1) + 1];
      int byteValue = 0;
      this.applyIndex(byteValue);
      int count2 = 1;
      int count3 = 3;

      do {
         int count4 = count2 + count3;
         int count5 = this.count;
         this.applyIndex(count5, count4, count3);
         int count6 = count2 + 1;
         int count7 = this.count;
         count2 = this.computeCount(count7, count6);
         count3 = 2 * count2 + 1;
      } while (count2 > 0 && count3 < this.count);
   }

   private static long computeTimestamp(int count2) {
      return 1L << (count2 & 63);
   }

   private void applyIndex(int count2) {
      int count3 = 0;
      count3 = computeCount(count2);
      this.timestampArray[count3] = this.timestampArray[count3] | computeTimestamp(count2);
   }

   public SignumComparable resolveSignumComparable(SignumComparable signumComparable, int count2, Random random) {
      int byteValue = 0;
      int count3 = 0;
      long value2 = 0L;
      int count4 = 0;
      SignumComparable signumComparable2 = null;
      long value3 = 0x63acaf00ee1380f2L;
      long value4 = 0x49f09b0827b80a4eL;

      while (true) {
         while (true) {
            int count5 = (int)(value3 % 12L);
            if (count5 != 0) {
               if (count5 == 4) {
                  value2 = ~this.timestampArray[count3];
                  value3 = (value3 ^ 0x60a5291d0a7966bfL) + 0x56e4a77c39a33833L;
                  value4 = (value4 ^ -0x64dbea37ee3298d9L) - 0x4d085a1d2fbe3522L;
                  continue;
               }

               if (count5 == 9) {
                  if (signumComparable2.matchesRandomByRandom(count2, random)) {
                     return signumComparable2;
                  }

                  value3 = (value3 ^ 0x7ad4302044487cacL) - 0x3fa3268cbcc236cL;
                  value4 = (value4 ^ -0x22ab79d850cdcd74L) - 0x5dc0c706b9fc11f1L;
                  continue;
               }

               if (count5 == 6) {
                  return signumComparable2;
               }

               if (count5 != 3) {
                  if (count5 == 10) {
                     count4 = 0;
                     value3 = (value3 ^ -0x34d3123b09396784L) + 0x7a23e2b8f3e71865L;
                     value4 = (value4 ^ -0x2d1bf02b22f55331L) - 0x65f10ababfd79fd0L;
                     continue;
                  }

                  if (count5 != 11) {
                     if (count5 != 1) {
                        if (count5 != 5) {
                           if (count5 != 2) {
                              if (count5 != 8) {
                                 SignumComparable signumComparable3 = SignumComparable.resolveSignumComparableByTime((long)byteValue);
                                 signumComparable2 = signumComparable.resolveSignumComparable5(signumComparable3);
                                 value3 = (value3 ^ -0x46946eb4a94f31ceL) - 0x73aa24ae89a687e4L;
                                 value4 = (value4 ^ -0x4067614f5cbf97dfL) + 0x31f58ef4e5ba6036L;
                              } else {
                                 count3 = 0;
                                 value3 = (value3 ^ 0x4b94c8eb8dee7379L) - 0x19ffb68e30527a26L;
                                 value4 = (value4 ^ 0x4ab64a80bcdab3aL) - 0x5230b26bc2a52471L;
                              }
                           } else {
                              byteValue = 1;
                              value3 = (value3 ^ 0x6723df17f7a88c5dL) + 0x6fdcce4b7cdddb65L;
                              value4 = (value4 ^ 0x20756b962c14b46bL) - 0x1b06143d0357b9feL;
                           }
                        } else {
                           value2 >>>= 1;
                           value3 = (value3 ^ -0x737b327da4cbeddcL) + 0x604166c98e48e83fL;
                           value4 = (value4 ^ 0x78883454d7e0085fL) - 0x174c49045faa83cdL;
                        }
                     } else if ((value2 & (value4 % 322416L ^ -115279L)) != (value4 % 435871L ^ -357194L)) {
                        value3 = (value3 ^ 0x64b33d2fbd4a0978L) + 0x210946ad4f7eadbcL;
                        value4 = (value4 ^ -0x7b3f8e3351debfb6L) + 0x4e745893e8dff748L;
                     } else {
                        value3 = (value3 ^ 0x1c4000c70e912032L) - 0x3ca1d67cc40edcf0L;
                        value4 = (value4 ^ 0x1e5d96613f0cf199L) + 0x25b5ae567a93eddfL;
                     }
                     continue;
                  }
                  break;
               }
            } else {
               byteValue += 2;
               count4++;
               value3 = (value3 ^ -0x5db2ec5a1502ba1eL) - 0x6b9859c39deab953L;
               value4 = (value4 ^ -0x2afcaeae0f948bfcL) + 0x49bf46ca9bc7c296L;
            }

            if (count4 >= 64) {
               long value5 = (value3 ^ 0x661a9ca7f564287eL) - 0x18a54c318e6419c1L;
               long value6 = (value4 ^ -0x56c3adcd27db11c3L) - 0x558ec94c9457c4a7L;
               count3++;
               value3 = (value5 ^ -0x855fc7aafccf5e9L) + 0x5b71b120dbe9c9f8L;
               value4 = (value6 ^ -0x4b510305f9054358L) - 0xcb258242576fabcL;
               break;
            }

            value3 = (value3 ^ -0x846a0455c075966L) - 0x788481483ab24904L;
            value4 = (value4 ^ -0x27336032d357acafL) + 0x712151238070bbb5L;
         }

         if (count3 >= this.timestampArray.length) {
            return null;
         }

         value3 = (value3 ^ 0x5fd375782bda52bdL) + 0xdc76a0d23a4dfaL;
         value4 = (value4 ^ -0x1edb60fd0dfb3b96L) + 0x6ef416f0bd8be65aL;
      }
   }

   public CurvePicker(SignumComparable signumComparable, int count2) {
      this.timestampArray = new long[computeCount(count2 - 1) + 1];
      this.count = count2;
      int count3 = curvePicker.count;
      int count4 = curvePicker.computeCount(count3, 0);
      int count5 = count4 * 2 + 1;
      CurveAccumulator curveAccumulator = new CurveAccumulator(signumComparable);
      CurveAccumulator curveAccumulator2 = new CurveAccumulator();

      do {
         int count6 = curveAccumulator.computeCount(count5, curveAccumulator2);
         int count7 = count5 - count6;
         if (count7 % 2 == 0) {
            count7 += count5;
         }

         int count8 = (count7 - 1) / 2;
         this.applyIndex(count2, count8, count5);
         int count9 = count4 + 1;
         int count10 = curvePicker.count;
         count4 = curvePicker.computeCount(count10, count9);
         count5 = count4 * 2 + 1;
      } while (count4 > 0);
   }

   private int computeCount(int count2, int count3) {
      int count4 = 0;
      if (count3 < count2) {
         count4 = count3;

         while (this.checkIndex(count4)) {
            if (++count4 >= count2 - 1) {
               return -1;
            }
         }

         return count4;
      } else {
         return -1;
      }
   }
}
