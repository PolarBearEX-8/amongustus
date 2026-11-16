package client.util;

import client.setting.CombatScaleProperty;
import java.util.Arrays;

public class SplineParser extends Number implements Comparable {
   public static final int count13 = 7;
   public static final SignumComparable signumComparable = SignumComparable.resolveSignumComparableByTime(Long.MIN_VALUE);
   public static final boolean enabled = !SplineParser.class.desiredAssertionStatus();
   public static final SplineParser[] splineParserArray = new SplineParser[]{
      new SplineParser(SignumComparable.signumComparable5, 0L, 0, 1),
      new SplineParser(SignumComparable.signumComparable, 1L, 0, 1),
      new SplineParser(SignumComparable.resolveSignumComparableByTime(2L), 2L, 0, 1),
      new SplineParser(SignumComparable.resolveSignumComparableByTime(3L), 3L, 0, 1),
      new SplineParser(SignumComparable.resolveSignumComparableByTime(4L), 4L, 0, 1),
      new SplineParser(SignumComparable.resolveSignumComparableByTime(5L), 5L, 0, 1),
      new SplineParser(SignumComparable.resolveSignumComparableByTime(6L), 6L, 0, 1),
      new SplineParser(SignumComparable.resolveSignumComparableByTime(7L), 7L, 0, 1),
      new SplineParser(SignumComparable.resolveSignumComparableByTime(8L), 8L, 0, 1),
      new SplineParser(SignumComparable.resolveSignumComparableByTime(9L), 9L, 0, 1),
      new SplineParser(SignumComparable.signumComparable3, 10L, 0, 2)
   };
   public static final SplineParser splineParser = splineParserArray[0];
   public static final SplineParser splineParser2 = splineParserArray[1];
   public static final SplineParser splineParser3 = splineParserArray[10];
   public static volatile SignumComparable[] signumComparableArray = new SignumComparable[]{
      SignumComparable.signumComparable,
      SignumComparable.resolveSignumComparableByTime(10L),
      SignumComparable.resolveSignumComparableByTime(100L),
      SignumComparable.resolveSignumComparableByTime(1000L),
      SignumComparable.resolveSignumComparableByTime(10000L),
      SignumComparable.resolveSignumComparableByTime(100000L),
      SignumComparable.resolveSignumComparableByTime(1000000L),
      SignumComparable.resolveSignumComparableByTime(10000000L),
      SignumComparable.resolveSignumComparableByTime(100000000L),
      SignumComparable.resolveSignumComparableByTime(1000000000L),
      SignumComparable.resolveSignumComparableByTime(10000000000L),
      SignumComparable.resolveSignumComparableByTime(100000000000L),
      SignumComparable.resolveSignumComparableByTime(1000000000000L),
      SignumComparable.resolveSignumComparableByTime(10000000000000L),
      SignumComparable.resolveSignumComparableByTime(100000000000000L),
      SignumComparable.resolveSignumComparableByTime(0x38d7ea4c68000L),
      SignumComparable.resolveSignumComparableByTime(0x2386f26fc10000L),
      SignumComparable.resolveSignumComparableByTime(0x16345785d8a0000L),
      SignumComparable.resolveSignumComparableByTime(0xde0b6b3a7640000L)
   };
   public final int count5;
   public static final long serialVersionUID = 0x54c71557f981284fL;
   public static final long timestamp = 4294967296L;
   public static final ThreadLocal threadLocal = new ThreadLocalAdapter();
   public static final long[] timestampArray = new long[]{
      Long.MAX_VALUE,
      0xcccccccccccccccL,
      0x147ae147ae147aeL,
      0x20c49ba5e353f7L,
      0x346dc5d638865L,
      0x53e2d6238da3L,
      0x8637bd05af6L,
      922337203685L,
      92233720368L,
      9223372036L,
      922337203L,
      92233720L,
      9223372L,
      922337L,
      92233L,
      9223L,
      922L,
      92L,
      9L
   };
   public static final int count2 = 6;
   public static final float[] factorArray = new float[]{1.0F, 10.0F, 100.0F, 1000.0F, 10000.0F, 100000.0F, 1000000.0F, 1.0E7F, 1.0E8F, 1.0E9F, 1.0E10F};
   public static final SplineParser[] splineParserArray2 = new SplineParser[]{
      SplineParser.splineParserArray[0],
      new SplineParser(SignumComparable.signumComparable5, 0L, 1, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 2, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 3, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 4, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 5, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 6, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 7, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 8, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 9, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 10, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 11, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 12, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 13, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 14, 1),
      new SplineParser(SignumComparable.signumComparable5, 0L, 15, 1)
   };
   public static final int count = 2;
   public static final int count4 = signumComparableArray.length;
   public static final int count11 = 1;
   public static final int count3 = 0;
   public static final long[] timestampArray2 = new long[]{
      1L,
      10L,
      100L,
      1000L,
      10000L,
      100000L,
      1000000L,
      10000000L,
      100000000L,
      1000000000L,
      10000000000L,
      100000000000L,
      1000000000000L,
      10000000000000L,
      100000000000000L,
      0x38d7ea4c68000L,
      0x2386f26fc10000L,
      0x16345785d8a0000L,
      0xde0b6b3a7640000L
   };
   public transient String labelText2;
   public static final long timestamp3 = 0x3fffffffffffffffL;
   public static final long timestamp4 = -0x4000000000000000L;
   public static final double[] valueArray = new double[]{
      1.0,
      10.0,
      100.0,
      1000.0,
      10000.0,
      100000.0,
      1000000.0,
      1.0E7,
      1.0E8,
      1.0E9,
      1.0E10,
      1.0E11,
      1.0E12,
      1.0E13,
      1.0E14,
      1.0E15,
      1.0E16,
      1.0E17,
      1.0E18,
      1.0E19,
      1.0E20,
      1.0E21,
      1.0E22
   };
   public final SignumComparable signumComparable2;
   public static final long[][] timestampArray3 = new long[][]{
      {0L, -0x7538dcfb76180000L},
      {5L, 0x6bc75e2d63100000L},
      {54L, 0x35c9adc5dea00000L},
      {542L, 0x19e0c9bab2400000L},
      {5421L, 0x2c7e14af6800000L},
      {54210L, 0x1bcecceda1000000L},
      {542101L, 0x161401484a000000L},
      {5421010L, -0x2337f32d1c000000L},
      {54210108L, -0x602f7fc318000000L},
      {542101086L, 0x3e25026110000000L},
      {5421010862L, 0x6d7217caa0000000L},
      {54210108624L, 0x4674edea40000000L},
      {542101086242L, -0x3f6eb4d980000000L},
      {0x4ee2d6d415bL, -0x7a53107f00000000L},
      {0x314dc6448d93L, 0x38c15b0a00000000L},
      {0x1ed09bead87c0L, 0x378d8e6400000000L},
      {0x13426172c74d82L, 0x2b878fe800000000L},
      {0xc097ce7bc90715L, -0x4cb460f000000000L},
      {0x785ee10d5da46d9L, 0xf436a000000000L},
      {0x4b3b4ca85a86c47aL, 0x98a224000000000L}
   };
   public static final long timestamp5 = Long.MIN_VALUE;
   public static final int count12 = 3;
   public transient int count9;
   public final transient long timestamp2;
   public static final int count10 = 18;
   public static final int count7 = 4;
   public static final int count8 = 5;
   public static final int count6 = 16 * count4;

   private static boolean matchesCurveAccumulator(CurveAccumulator curveAccumulator, int count14, int count15, CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3) {
      int count16 = 0;
      if (!enabled && curveAccumulator3.isActive2()) {
         throw new AssertionError();
      } else {
         count16 = curveAccumulator3.computeCount3(curveAccumulator);
         return checkIndex(count14, count15, count16, curveAccumulator2.isActive3());
      }
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, int count14, SignumComparable signumComparable4, int count15) {
      SignumComparable signumComparable5 = signumComparable3;
      SignumComparable signumComparable6 = signumComparable4;
      int count17 = 0;
      long value2 = 0L;
      int count18 = 0;
      SignumComparable signumComparable7 = null;
      long value3 = 0xc90e696253158ecL;
      long value4 = 0x3dc693c6f4ab7e90L;

      SplineParser splineParser4;
      while (true) {
         int count19 = (int)(value3 % 12L);
         if (count19 != 11) {
            if (count19 != 7) {
               if (count19 != 10) {
                  if (count19 != 5) {
                     if (count19 != 0) {
                        if (count19 != 1) {
                           if (count19 != 8) {
                              if (count19 != 3) {
                                 if (count19 != 6) {
                                    if (count19 != 4) {
                                       if (count19 != 2) {
                                          count17 = count15;
                                          value3 = (value3 ^ 0x355277133bdbcb33L) + 0x1841a29fd14f770L;
                                          value4 = (value4 ^ -0x5283cee59f7a3120L) - 0x79e730ae0440ac7aL;
                                       } else {
                                          signumComparable6 = resolveSignumComparable(signumComparable6, count18);
                                          value3 = (value3 ^ 0x1efd32657f99d4a1L) - 0x4df76d1d3eef8e9fL;
                                          value4 = (value4 ^ 0x79b97c7722ff96deL) - 0x7576f6a7390233d7L;
                                       }
                                    } else {
                                       count17 = count14;
                                       value3 = (value3 ^ -0xefd206b9d8f7728L) + 0x377f8c92b566accfL;
                                       value4 = (value4 ^ -0x6df0fb66fbcce725L) + 0x47929060aebe6f2cL;
                                    }
                                    continue;
                                 }

                                 splineParser4 = new SplineParser(signumComparable7, Long.MIN_VALUE, count17, 0);
                                 break;
                              }

                              value2 = (long)count17 - (long)count15;
                              value3 = (value3 ^ -0x4a8099e3d6838bf9L) - 0x4106d097f1da9565L;
                              value4 = (value4 ^ 0x2fd72599068af9bfL) + 0x289cd975f80cfff3L;
                              continue;
                           }

                           if (value2 >= 0L) {
                              long value5 = (value3 ^ -0x6c3d75359bb690c6L) - 0x6e2b4d29bd23df31L;
                              long value6 = (value4 ^ 0x19b921aca451a42bL) - 0x6880cd2005c74b21L;
                              count18 = computeCountBySignumComparable(signumComparable6, value2);
                              value3 = (value5 ^ 0xa8b6e5886949237L) + 0x663cd8cdac68f024L;
                              value4 = (value6 ^ 0x19eb622f58d4da88L) - 0x164ecf559bbc809aL;
                           } else {
                              value3 = (value3 ^ 0x77c38ba9bd0e4899L) - 0x4a78cbf681d59894L;
                              value4 = (value4 ^ 0x489d6840251083eaL) - 0x4bd7d1ad70ad73e0L;
                           }
                           continue;
                        }

                        value3 = (value3 ^ -0x56e093b24ccf0880L) + 0x66a49bf85f4d30d3L;
                        value4 = (value4 ^ -0xe165a258ec9f4afL) + 0x3391723ce3a7060aL;
                     }

                     signumComparable7 = signumComparable5.resolveSignumComparable5(signumComparable6);
                     value3 = (value3 ^ -0x30122f6e433496b8L) - 0x643c8721d42c0759L;
                     value4 = (value4 ^ 0x73a862891e6e0842L) - 0x6ca803bcf0c77356L;
                  } else {
                     long value7 = -value2;
                     long value8 = value7;
                     count18 = computeCountBySignumComparable(signumComparable5, value8);
                     value3 = (value3 ^ 0x4dce21ef385467e1L) - 0x66d189470f1e4e83L;
                     value4 = (value4 ^ -0xae651a4bd8168ccL) + 0x53b1d1dcf5b5ee8fL;
                  }
               } else {
                  signumComparable5 = resolveSignumComparable(signumComparable5, count18);
                  value3 = (value3 ^ -0x61c99559d6a9762dL) + 0x7ab31ce9a234e0f8L;
                  value4 = (value4 ^ -0x3407e9dbe22880bbL) + 0x745cb456fcf0e778L;
               }
            } else if (value2 == 0L) {
               value3 = (value3 ^ 0x1120f9dbae8f625fL) - 0x154622d5697cbfe0L;
               value4 = (value4 ^ -0x57449b7936014353L) - 0x7487bb3607183344L;
            } else {
               value3 = (value3 ^ 0x7b6cfc5cc574ef2dL) - 0x295e66b89603c22eL;
               value4 = (value4 ^ 0x3507b175c423c3a6L) + 0xd31b5c630991d74L;
            }
         } else {
            if (signumComparable5.count != signumComparable6.count) {
               byte byteValue = 0;
               splineParser4 = resolveSplineParser(signumComparable7, count17, byteValue);
               break;
            }

            value3 = (value3 ^ -0x6ab5d3ff414a9fd8L) + 0x37431b414c296367L;
            value4 = (value4 ^ 0x5c19c9225c6ed954L) - 0x4a687d0b81bf3026L;
         }
      }

      return splineParser4;
   }

   private static int computeCountBySignumComparable(SignumComparable signumComparable3, long value) {
      int count14 = 0;
      count14 = (int)value;
      if ((long)count14 != value) {
         count14 = value <= 2147483647L ? Integer.MIN_VALUE : Integer.MAX_VALUE;
         if (signumComparable3.getCount8() != 0) {
            throw new ArithmeticException(count14 <= 0 ? "Overflow" : "Underflow");
         }
      }

      return count14;
   }

   private static SplineParser resolveSplineParser(long value, SignumComparable signumComparable3, int count14) {
      return value != 0L ? new SplineParser(signumComparable3.resolveSignumComparable(value), Long.MIN_VALUE, count14, 0) : resolveSplineParser3(count14);
   }

   public int getCount2() {
      long value = 0L;
      value = this.getTimestamp();
      if ((long)((int)value) == value) {
         return (int)value;
      } else {
         throw new ArithmeticException("Overflow");
      }
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, long value, int count14, int count15, int count16) {
      long value2 = 0L;
      Object entry = null;
      Object entry2 = null;
      long value3 = 0x60d5ba431d1eb6b4L;
      value2 = 0L;
      value3 = (value3 ^ 0x47ddde4edf3cc131L) + 0x4108c73530f12233L;
      entry = null;
      value3 = (value3 ^ 0x35a096d3542fda87L) - 0x69b26c5137ab803dL;
      entry2 = new CurveAccumulator(signumComparable3.countArray);
      value3 = (value3 ^ -0x3827247bc52f225fL) - 0x161da46c5a8d1645L;
      entry = new CurveAccumulator();
      value3 = (value3 ^ -0x4f62cac86a1daec0L) + 0x75773d48c40688b8L;
      value2 = ((CurveAccumulator)entry2).computeTimestamp(value, (CurveAccumulator)entry);
      value3 = (value3 ^ 0x41ca4a5753925e8cL) - 0x4121aa0ac63d24cdL;
      boolean flag;
      if (value2 != 0L) {
         flag = false;
      } else {
         value3 = (value3 ^ -0x7bbaddf76c2cd102L) - 0x12905c49e4534a18L;
         flag = true;
      }

      boolean flag2 = flag;
      int count17 = value >= 0L ? signumComparable3.count : -signumComparable3.count;
      if (flag2) {
         if (count16 == count14) {
            return ((CurveAccumulator)entry).resolveSplineParser(count17, count14);
         } else {
            long value4 = ((CurveAccumulator)entry).computeTimestampByIndex(count17);
            if (value4 == Long.MIN_VALUE) {
               SignumComparable signumComparable4 = ((CurveAccumulator)entry).resolveSignumComparableByIndex(count17);
               long value5 = (long)count16;
               return resolveSplineParser(signumComparable4, count14, value5);
            } else {
               return resolveSplineParser(value4, count14, (long)count16);
            }
         }
      } else {
         if (matchesCurveAccumulator(value, count15, count17, (CurveAccumulator)entry, value2)) {
            CurveAccumulator curveAccumulator = CurveAccumulator.curveAccumulator;
            ((CurveAccumulator)entry).applyCurveAccumulator(curveAccumulator);
         }

         return ((CurveAccumulator)entry).resolveSplineParser(count17, count14);
      }
   }

   public byte getByteCode() {
      long value = 0L;
      value = this.getTimestamp();
      if ((long)((byte)((int)value)) == value) {
         return (byte)((int)value);
      } else {
         throw new ArithmeticException("Overflow");
      }
   }

   public SplineParser resolveSplineParser(SplineParser splineParser4, int count14, CombatScaleProperty combatScaleProperty) {
      int count15 = combatScaleProperty.count;
      return this.resolveSplineParser(splineParser4, count14, count15);
   }

   private static SignumComparable resolveSignumComparable2(int count14) {
      synchronized (SplineParser.class) {
         SignumComparable[] signumComparableArray2 = signumComparableArray;
         int length = signumComparableArray2.length;
         if (length <= count14) {
            int count15 = length << 1;

            while (count15 <= count14) {
               count15 <<= 1;
            }

            for (signumComparableArray2 = Arrays.copyOf(signumComparableArray2, count15); length < count15; length++) {
               SignumComparable signumComparable3 = signumComparableArray2[length - 1];
               SignumComparable signumComparable4 = SignumComparable.signumComparable3;
               signumComparableArray2[length] = signumComparable3.resolveSignumComparable4(signumComparable4);
            }

            signumComparableArray = signumComparableArray2;
         }

         return signumComparableArray2[count14];
      }
   }

   public SplineParser resolveSplineParser4(int count14) {
      int count15 = 0;
      if (count14 >= 0 && count14 <= 999999999) {
         long value = (long)this.count5 * (long)count14;
         count15 = this.computeCount2(value);
         return new SplineParser(this.getSignumComparable().resolveSignumComparable8(count14), count15);
      } else {
         throw new ArithmeticException("Invalid operation");
      }
   }

   private static long computeTimestamp(long value, int count14) {
      long[] entry = null;
      long[] entry2 = null;
      long value2 = 0L;
      long value3 = -0x3f8328f6651b2aefL;
      if (value != 0L) {
         value3 = (value3 ^ -0x37f83827dae6e2bL) + 0x6d98f9162ee4778fL;
         if (count14 > 0) {
            value3 = (value3 ^ -0x18813fa595170ac3L) + 0x1612e54daa02c475L;
            entry = timestampArray2;
            value3 = (value3 ^ -0x60550b2341b69575L) - 0x1ae01ec642a25a66L;
            entry2 = timestampArray;
            value3 = (value3 ^ 0x517d031f32500ce9L) + 0x75eb52178ec43aceL;
            if (count14 >= entry.length) {
               value3 = (value3 ^ -0x34669b41c3567b25L) - 0x71431f5a2e5d3d00L;
            } else {
               value3 = (value3 ^ -0x1b8f903f51e2b68bL) + 0x37ca8bb8878f56d7L;
               if (count14 >= entry2.length) {
                  value3 = (value3 ^ -0x2b18924ac3260e40L) + 0x4f8209a4c6d13dffL;
               } else {
                  value3 = (value3 ^ 0x18e375019715fb30L) - 0x538bd1342066b7b3L;
                  value2 = entry[count14];
                  value3 = (value3 ^ 0x574b3e3274ca7a6eL) + 0x14930ccae5a96ea9L;
                  if (value == (value3 % 749152L ^ -281002L)) {
                     return value2;
                  }

                  value3 = (value3 ^ 0x77b86731a34f3e8cL) - 0x5aae5ae8c9323abfL;
                  if (Math.abs(value) <= entry2[count14]) {
                     return value * value2;
                  }

                  value3 = (value3 ^ 0x50fa83d02c9d271fL) + 0xdc780983479527fL;
               }
            }

            return Long.MIN_VALUE;
         }
      }

      return value;
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, SignumComparable signumComparable4, int count14) {
      return new SplineParser(signumComparable3.resolveSignumComparable4(signumComparable4), Long.MIN_VALUE, count14, 0);
   }

   private static long computeTimestampByTime(long value, long value2) {
      return value << 32 | value2;
   }

   private static SplineParser resolveSplineParser(long value, long value2, long value3, int count14, int count15, int count16, int count17) {
      long value4 = value3;
      long value5 = (long)0;
      long value6 = 0L;
      long value7 = 0L;
      long value8 = 0L;
      long value9 = 0L;
      long value10 = 0L;
      long value11 = 0L;
      long value12 = 0L;
      long value13 = 0L;
      long[] valueArray2 = null;
      long[] valueArray3 = null;
      long value14 = 0L;
      SignumComparable signumComparable3 = null;
      boolean flag = false;
      long value15 = 0L;
      CurveAccumulator curveAccumulator = null;
      long value16 = 0L;
      long value17 = 0L;
      long value18 = 0x42ad7b20f75201c2L;
      long value19 = 0x626cf722562d45c0L;

      while (true) {
         int count18 = (int)(value18 % 65L);
         if (count18 == 17) {
            value13 += value6;
            value18 = (value18 ^ -0x7c9de4f7f495c973L) + 0x7b8fd3c91bfff640L;
            value19 = (value19 ^ 0x213dd560ed3d415dL) + 0x86db04416a4a794L;
         } else if (count18 != 34) {
            if (count18 != 12) {
               if (count18 == 55) {
                  return resolveSplineParserByTime(value16, count15);
               }

               if (count18 == 9) {
                  value7 = value4 & 4294967295L;
                  value18 = (value18 ^ 0x59ce114a167c8c80L) - 0x3ab2c184a7c88220L;
                  value19 = (value19 ^ 0x3f7e02cbb5b49201L) - 0x61c1debb2e79121aL;
               } else if (count18 == 4) {
                  value13 = valueArray3[0];
                  value18 = (value18 ^ 0x73b10a01f7e2f66aL) + 0x33fc8e0f987b294cL;
                  value19 = (value19 ^ -0x34d81f67075a8ceL) - 0x61a92be107666941L;
               } else if (count18 == 59) {
                  value8 = value2 << value5;
                  value18 = (value18 ^ -0x28627c9ca89c6af7L) + 0x4f1842a49dd637c3L;
                  value19 = (value19 ^ 0x5a51aa0883ab70faL) + 0x5b2e5ae57e49f155L;
               } else if (count18 == 38) {
                  value15 = valueArray3[1];
                  value18 = (value18 ^ 0x27392ab50d1feea3L) + 0x459e1b3bda9fe029L;
                  value19 = (value19 ^ 0x7f2974cede5a97fL) - 0x4dee6a26b27dcf4fL;
               } else if (count18 != 21) {
                  long value20;
                  long value21;
                  checkBlock: {
                     guardBlock: {
                        long value22;
                        long value23;
                        searchBlock: {
                           if (count18 != 45) {
                              if (count18 == 37) {
                                 if (value6 != (value19 % 716034L ^ 475888L)) {
                                    long value24 = (value18 ^ 0x48509875ced8bb19L) - 0x137bc6b185bafdcfL;
                                    long value25 = (value19 ^ -0x6332cefa6c4df5b4L) + 0x59009b7e5d83f1d9L;
                                    if (value8 < 0L) {
                                       long value26 = (value24 ^ -0xabc642fdfc7baL) + 0x7f9f97d1c314f9edL;
                                       long value27 = (value25 ^ -0x2998bda87780be31L) - 0x73d72bd4f91e27deL;
                                       valueArray2 = resolveTimeArray(value8, value6);
                                       value18 = (value26 ^ 0x4913000e6b314cbcL) - 0x6c37eba2a3269ceL;
                                       value19 = (value27 ^ 0x47920f9b798d46caL) + 0x2e93f3a02a230975L;
                                    } else {
                                       value18 = (value24 ^ -0x252643b2f87d7926L) - 0x4bbfff662332824fL;
                                       value19 = (value25 ^ 0x2ce378646a0f42acL) + 0x4453749f07f2fdd0L;
                                    }
                                 } else {
                                    value18 = (value18 ^ -0x3b63e3aaaf3d88d7L) + 0x5486e181d79d6dc2L;
                                    value19 = (value19 ^ -0x53f47995ee00c79fL) - 0xab9750a8c89a969L;
                                 }
                                 continue;
                              }

                              if (count18 == 14) {
                                 curveAccumulator = new CurveAccumulator(new int[]{(int)value12, (int)value15});
                                 value18 = (value18 ^ 0xfa6ac8f040453d5L) + 0x49ee9310e66d6b2fL;
                                 value19 = (value19 ^ -0x3e6abd09e3e29c4fL) + 0x76dc2e435b4e89d1L;
                                 continue;
                              }

                              if (count18 == 28) {
                                 if (checkTimeByTime(value12 * value7, computeTimestampByTime(value13, value9))) {
                                    value18 = (value18 ^ 0x7ffb8a9b3612c71eL) - 0x3bbb4e4aad14896bL;
                                    value19 = (value19 ^ -0x7a43b0f68e2e5475L) + 0x696b4cf577d74450L;
                                    continue;
                                 }

                                 value20 = (value18 ^ -0x43f1b93ebb1bac5cL) - 0x6efe267f7e58edc1L;
                                 value21 = (value19 ^ -0x3ed2bc35d60e3675L) - 0x61fec6887c329cc9L;
                                 break checkBlock;
                              }

                              if (count18 == 49) {
                                 if (count16 != 1) {
                                    value18 = (value18 ^ 0x7efdfb40b244c785L) + 0x241fa884b3913d5eL;
                                    value19 = (value19 ^ 0x6ad44e6a13951b56L) + 0x85ed88e7fcc4b58L;
                                 } else {
                                    value18 = (value18 ^ 0x2ae8e71f62d92942L) - 0x3f0a10f1483a87c7L;
                                    value19 = (value19 ^ -0x22e602d4973a96f9L) + 0x573963c2d8456187L;
                                 }
                                 continue;
                              }

                              if (count18 == 29) {
                                 value13 = 0L;
                                 value18 = (value18 ^ -0x584f801502fb44a4L) - 0x7dfc3d522ca35162L;
                                 value19 = (value19 ^ 0x3da76a5dfa72af3aL) + 0x3ea94ed7c48e9416L;
                                 continue;
                              }

                              if (count18 == 0) {
                                 signumComparable3 = curveAccumulator.resolveSignumComparableByIndex(count14);
                                 value18 = (value18 ^ 0x352e542dd6010bd3L) + 0xc9fd4e7b8ca69bfL;
                                 value19 = (value19 ^ 0x7c5a6f9de0b84f9fL) + 0x2e0609a2f2484facL;
                                 continue;
                              }

                              if (count18 == 26) {
                                 if (!matchesCurveAccumulator(value4 >>> value5, count16, count14, curveAccumulator, value14)) {
                                    value18 = (value18 ^ 0x371a4e704788fc9cL) + 0x5ec6106363774f2L;
                                    value19 = (value19 ^ 0x5ea66052156514dL) - 0x42d61a08ccaa53b7L;
                                 } else {
                                    value18 = (value18 ^ -0xf3fd63823ef01bdL) + 0x21dfb626d44b9fbcL;
                                    value19 = (value19 ^ 0x3284899262355f40L) + 0x28a9152f5b110977L;
                                 }
                                 continue;
                              }

                              if (count18 == 54) {
                                 value12 = value8 / value6;
                                 value18 = (value18 ^ 0x80282ceaf7b845bL) + 0x300aa0b6cb00a165L;
                                 value19 = (value19 ^ 0xf030e2e37363b42L) - 0x7930b93b54fc006dL;
                                 continue;
                              }

                              if (count18 == 11) {
                                 if (value14 == 0L) {
                                    value18 = (value18 ^ 0x334f4742128e59ceL) - 0x2e3906405e4ca65fL;
                                    value19 = (value19 ^ -0x565f18e668625f11L) - 0xf668e440dd55694L;
                                 } else {
                                    value18 = (value18 ^ 0x60b8baef15af989L) - 0x1f955f9a98e2fde2L;
                                    value19 = (value19 ^ -0x215224e972536abdL) - 0x23f856bf85bdf91bL;
                                 }
                                 continue;
                              }

                              if (count18 == 57) {
                                 value8 = value << value5 | value2 >>> 64 - value5;
                                 value18 = (value18 ^ 0x4ac23c066b423008L) - 0xde1f3eccc50f5c9L;
                                 value19 = (value19 ^ -0x392eaba7f1cb3fe6L) - 0x10a8505585616fb7L;
                                 continue;
                              }

                              if (count18 == 20) {
                                 value16 *= (long)count14;
                                 value18 = (value18 ^ 0x7c0d4f6e966744e4L) - 0x14c637698b34052L;
                                 value19 = (value19 ^ 0x6db385201da6db5L) - 0x5316de4e08ed2e37L;
                                 continue;
                              }

                              if (count18 == 43) {
                                 value13 = value8 - value15 * value6;
                                 value18 = (value18 ^ 0x44d292665d91507cL) - 0x1180829590319264L;
                                 value19 = (value19 ^ -0x437051c62e7ca4c6L) + 0x131a855c07adc653L;
                                 continue;
                              }

                              if (count18 == 36) {
                                 value22 = (value18 ^ -0x4e4a2a8e893c64fL) + 0x490abd310ed3e68fL;
                                 value23 = (value19 ^ -0x66fda3f657abff73L) + 0x66fe5bf6a7d4007dL;
                                 break searchBlock;
                              }

                              if (count18 == 51) {
                                 value20 = (value18 ^ -0x3b288885f41e6ef0L) + 0x3c3690b609608ef0L;
                                 value21 = (value19 ^ 0x669bf78b2a032beaL) - 0x268bee76d5fce99aL;
                                 break checkBlock;
                              }

                              if (count18 == 35) {
                                 value13 = 0L;
                                 value18 = (value18 ^ -0x2cb65eefa97f4805L) + 0x52465c05452bbaacL;
                                 value19 = (value19 ^ 0x6ffc2dce9b1acd1dL) + 0x3280142b92b1d3c4L;
                                 continue;
                              }

                              if (count18 == 30) {
                                 value9 = value8 & 4294967295L;
                                 value18 = (value18 ^ 0x29b556a8007aa0feL) - 0x106820b9d655b824L;
                                 value19 = (value19 ^ -0x5cf4ffb17f2ac71fL) + 0x3e04489994eef7f4L;
                                 continue;
                              }

                              if (count18 == 15) {
                                 value15 -= value19 % 652099L ^ 29162L;
                                 value18 = (value18 ^ 0x5bd0c2ccc87e0ce1L) - 0xa2cf5825df3b4fbL;
                                 value19 = (value19 ^ -0x366969334d938b80L) - 0x3601ad675d481c6fL;
                                 continue;
                              }

                              if (count18 == 61) {
                                 value14 = computeTimestamp(value9, value10, value6, value7, value15) >>> value5;
                                 value18 = (value18 ^ 0x7700388651765b4bL) - 0x119e50d7194aedaL;
                                 value19 = (value19 ^ -0x4a1a652b94fab61bL) - 0x55455f906bb62a37L;
                                 continue;
                              }

                              if (count18 == 31) {
                                 return null;
                              }

                              if (count18 == 53) {
                                 if (value < value4) {
                                    value18 = (value18 ^ -0x15a158cadf651bb0L) - 0x7b7c8d15403f3e83L;
                                    value19 = (value19 ^ 0x76db6e746c3f7a83L) - 0xbe35ae5e0626e9fL;
                                    continue;
                                 }

                                 return null;
                              }

                              if (count18 == 13) {
                                 return curveAccumulator.resolveSplineParser(count14, count15);
                              }

                              if (count18 == 47) {
                                 value9 = value8 >>> 32;
                                 value18 = (value18 ^ -0x367971af60feff10L) + 0x490c67d54d2dbe97L;
                                 value19 = (value19 ^ -0x28de067b4fc1ef10L) - 0x4cb931d0b8990c88L;
                                 continue;
                              }

                              if (count18 == 7) {
                                 if (flag) {
                                    value18 = (value18 ^ 0x7765486ab4993bf4L) + 0xcb0893e9007f6e7L;
                                    value19 = (value19 ^ 0x7b5677349f5e94ceL) + 0x4cfda815023e0cbL;
                                    continue;
                                 }

                                 return resolveSplineParserByTime(value16, count15);
                              }

                              if (count18 == 64) {
                                 if (count15 != count17) {
                                    value18 = (value18 ^ 0xd90df950b3a7553L) + 0x43f7c1a7cf46fb97L;
                                    value19 = (value19 ^ -0x5fae0dfe5b962dd8L) - 0x7ab10b6cefae7400L;
                                 } else {
                                    value18 = (value18 ^ -0x7b660ce38b505db4L) + 0x71db77a18cb32b04L;
                                    value19 = (value19 ^ -0x5db32f337022a053L) - 0x3f03cc10844f4a2fL;
                                 }
                                 continue;
                              }

                              if (count18 == 42) {
                                 if (value6 != (value19 % 566592L ^ 440742L)) {
                                    long value28 = (value18 ^ -0x73aa8d8cd3d825fL) + 0x2e94e24fca4cbc0fL;
                                    long value29 = (value19 ^ -0x17ccc2379a9f007cL) + 0x3aa05e520a717f0eL;
                                    if (value8 < 0L) {
                                       long value30 = (value28 ^ -0x64f16f6e1c26d398L) - 0x6c82c1bf5c391faaL;
                                       long value31 = (value29 ^ 0x38e7851cd838dab5L) - 0x7057e9f0c0059802L;
                                       valueArray3 = resolveTimeArray(value8, value6);
                                       value18 = (value30 ^ 0x5c4d11c484a8faf3L) - 0x4ed320ce949432d3L;
                                       value19 = (value31 ^ -0x5c2577e898457612L) + 0x2d6b2aab946ba461L;
                                    } else {
                                       value18 = (value28 ^ 0x345b19a305b88d36L) - 0x2d253a38e10aafc9L;
                                       value19 = (value29 ^ -0x15a6eaa21fe38f6aL) - 0x6df3a7fb3e5c45fdL;
                                    }
                                 } else {
                                    value18 = (value18 ^ 0x7435b897976d5f8bL) - 0xcd7ce373b407e9fL;
                                    value19 = (value19 ^ 0x4e208a3c340a097dL) + 0x6edd08e4b322cbbdL;
                                 }
                                 continue;
                              }

                              if (count18 == 58) {
                                 break guardBlock;
                              }

                              if (count18 == 10) {
                                 value12 = value8;
                                 value18 = (value18 ^ 0x574e473e385ffb09L) - 0x11c410cc93f961ecL;
                                 value19 = (value19 ^ 0x1978a728e70cdb83L) - 0x2f74ddb4bb4a4442L;
                                 continue;
                              }

                              if (count18 != 32) {
                                 if (count18 == 33) {
                                    value5 = (long)Long.numberOfLeadingZeros(value4);
                                    value18 = (value18 ^ -0x2ec2c5eb274d9618L) + 0x31664c8204825194L;
                                    value19 = (value19 ^ -0xec6c469d01f87f3L) + 0x4664582c3071dffeL;
                                    continue;
                                 }

                                 if (count18 == 19) {
                                    if (value17 == 0L) {
                                       if (count17 == count15) {
                                          return resolveSplineParserByTime(value16, count15);
                                       }

                                       return resolveSplineParser(value16, count15, (long)count17);
                                    }

                                    value18 = (value18 ^ 0x7184ae477ddafc9bL) + 0x3fef1940b53ee54L;
                                    value19 = (value19 ^ -0x3d5eabc1cb84827aL) - 0x6d861d87f93c11c9L;
                                    continue;
                                 }

                                 if (count18 == 39) {
                                    value15 = value8;
                                    value18 = (value18 ^ 0x56c465eb4a0a844aL) + 0x44a0a35a6b3b6c37L;
                                    value19 = (value19 ^ 0x30376455b7c850c0L) - 0x3e8b0115d87a1de7L;
                                    continue;
                                 }

                                 if (count18 == 41) {
                                    value18 = (value18 ^ 0x2b5049a2a98d1ebeL) + 0x261c43eb16174374L;
                                    value19 = (value19 ^ -0x51a9e748c7e22cfeL) - 0x7a040f8f85345b70L;
                                    break guardBlock;
                                 }

                                 if (count18 == 2) {
                                    value12 -= value19 % 125644L ^ -36756L;
                                    value18 = (value18 ^ 0x7d6ee24292b25cb7L) - 0x1d1023094e29fc91L;
                                    value19 = (value19 ^ 0x54614964412e9425L) - 0x35baacb5ebc1826bL;
                                    continue;
                                 }

                                 if (count18 == 44) {
                                    if (count15 != count17) {
                                       value18 = (value18 ^ 0x3f622c007014a321L) - 0x3851e5124b1ecfbL;
                                       value19 = (value19 ^ -0x113d313ac71de8ecL) - 0x695c7137e646b0f4L;
                                    } else {
                                       value18 = (value18 ^ 0x4ce73387731fa6fdL) - 0x3f714d37fc0e03cfL;
                                       value19 = (value19 ^ 0x6890ab62d684f232L) - 0x1a260133d73e569eL;
                                    }
                                    continue;
                                 }

                                 if (count18 == 23) {
                                    value15 = value8 / value6;
                                    value18 = (value18 ^ -0x5090c58db9c61b16L) + 0x5af4e6cdd5ba10c3L;
                                    value19 = (value19 ^ -0x2c932b163f791b4bL) - 0x439dbc9fa371060aL;
                                    continue;
                                 }

                                 if (count18 != 8) {
                                    if (count18 == 56) {
                                       value11 = value8 & 4294967295L;
                                       value18 = (value18 ^ 0x680d6ddfe058d120L) - 0x406eb30669b88dfbL;
                                       value19 = (value19 ^ -0x3bb665797834452dL) - 0x5cb4618eca93ee4eL;
                                       continue;
                                    }

                                    if (count18 == 63) {
                                       return resolveSplineParserByTime(value16 + (long)count14, count15);
                                    }

                                    if (count18 == 40) {
                                       value10 = value8 & 4294967295L;
                                       value18 = (value18 ^ -0x1cc4657602dda226L) + 0x3f13eef18b278a14L;
                                       value19 = (value19 ^ 0x59d8f7d2d650f67fL) + 0x39e9e576c10b191fL;
                                       continue;
                                    }

                                    if (count18 == 5) {
                                       if (count17 == count15) {
                                          value18 = (value18 ^ 0x51299dfa717d40a1L) - 0x16c13a1be4b3dfdaL;
                                          value19 = (value19 ^ -0x258a76f6a1e9ca1cL) + 0x1e13832e51e31cd6L;
                                       } else {
                                          value18 = (value18 ^ 0x5eed3c087111cc2eL) + 0x3baad63fdcc5aa8cL;
                                          value19 = (value19 ^ 0x2f7ce7ae110cfb12L) - 0x5aeba1821ed2707bL;
                                       }
                                       continue;
                                    }

                                    if (count18 == 1) {
                                       value13 += value6;
                                       value18 = (value18 ^ 0x5ab9157209b5d70fL) + 0x6b9d5631a96c8fd5L;
                                       value19 = (value19 ^ 0x5631a2e4ce55703L) + 0x7ccbb2727e572c9fL;
                                       continue;
                                    }

                                    if (count18 == 6) {
                                       if (value13 < 4294967296L) {
                                          value18 = (value18 ^ -0xfe4a9e9b61ab6b5L) - 0x34fd75bf91f162f9L;
                                          value19 = (value19 ^ -0x6e09c83e429a53dfL) - 0x74e653f48dfdf2ceL;
                                       } else {
                                          value18 = (value18 ^ -0xe338e359437a78cL) - 0x6f4ce43e4089312eL;
                                          value19 = (value19 ^ -0x1063c659e05c30fL) - 0x66e18a6562437a40L;
                                       }
                                       continue;
                                    }

                                    if (count18 == 62) {
                                       value17 = computeTimestamp(value9, value10, value6, value7, value15) >>> value5;
                                       value18 = (value18 ^ -0x22a141b506c968fdL) + 0x584ed5c778f19dc0L;
                                       value19 = (value19 ^ 0x357dcee1fac54e4L) - 0x739a8a662193002dL;
                                       continue;
                                    }

                                    if (count18 == 48) {
                                       value16 = computeTimestampByTime(value12, value15);
                                       value18 = (value18 ^ -0x1a038e52e48a2eccL) - 0x72fc71e6fdd52222L;
                                       value19 = (value19 ^ -0x20c9f811a59359ddL) - 0x1f519f3612ccf01eL;
                                       continue;
                                    }

                                    if (count18 == 46) {
                                       long value32 = (long)count17;
                                       return resolveSplineParser(signumComparable3, count15, value32);
                                    }

                                    if (count18 == 50) {
                                       return curveAccumulator.resolveSplineParser(count14, count15);
                                    }

                                    if (count18 == 52) {
                                       return curveAccumulator.resolveSplineParser(count14, count15);
                                    }

                                    if (count18 == 25) {
                                       value18 = (value18 ^ -0x1963b6e20800c0bdL) + 0x5cdc72193e472d0bL;
                                       value19 = (value19 ^ -0x115d750b45a047f5L) - 0x62c8f05cfef9432aL;
                                       break guardBlock;
                                    }

                                    if (count18 == 22) {
                                       value12 = valueArray2[1];
                                       value18 = (value18 ^ -0x3bcb337311a54a29L) - 0x743b0d36fac5e83dL;
                                       value19 = (value19 ^ -0x5497abb1b8e86f97L) - 0x504bd1347b72c744L;
                                       continue;
                                    }

                                    if (count18 == 60) {
                                       CurveAccumulator curveAccumulator2 = CurveAccumulator.curveAccumulator;
                                       curveAccumulator.applyCurveAccumulator(curveAccumulator2);
                                       value18 = (value18 ^ -0x50ce9dde2e4f27c8L) + 0x6fa42b2f47e30b8fL;
                                       value19 = (value19 ^ 0x15b6e6420fbfea81L) - 0x6c671e0d64cccdb0L;
                                       continue;
                                    }

                                    if (count18 != 18) {
                                       if (count18 != 27) {
                                          if (count18 != 16) {
                                             if (count18 != 24) {
                                                if (count16 != 1) {
                                                   value18 = (value18 ^ -0x856707d85f16859L) - 0x720a53e41533f2caL;
                                                   value19 = (value19 ^ -0xf1bb0081b61d04L) - 0x1f9288b3bf509f10L;
                                                } else {
                                                   value18 = (value18 ^ 0x30614a8dc3b20d5aL) - 0x4a20e93285fa01L;
                                                   value19 = (value19 ^ 0x3e2aad400098f41bL) - 0x440d74e75ab9d00bL;
                                                }
                                             } else {
                                                value6 = value4 >>> 32;
                                                value18 = (value18 ^ 0x2a5f3a6e3a7dc523L) - 0x26f4d76fdd65e298L;
                                                value19 = (value19 ^ -0xfc7b5b8aaca9bb2L) + 0x126360624a5c07a7L;
                                             }
                                          } else {
                                             value13 = value8 - value12 * value6;
                                             value18 = (value18 ^ -0x2f413d8dba5acb5aL) - 0x68065f40adcc6f66L;
                                             value19 = (value19 ^ -0x4427e39e448e8250L) - 0x7c9acee56809ed90L;
                                          }
                                       } else {
                                          value13 = valueArray2[0];
                                          value18 = (value18 ^ -0x5e9807adcc85f8a1L) - 0x4119724629f41478L;
                                          value19 = (value19 ^ 0x1c636b489cf3acacL) + 0x696e4fde9d2843e7L;
                                       }
                                       continue;
                                    }

                                    if (checkTimeByTime(value15 * value7, computeTimestampByTime(value13, value10))) {
                                       value18 = (value18 ^ 0x6700b7561749950dL) - 0x4f49c43b1cb2f954L;
                                       value19 = (value19 ^ 0x53f91b7ddaa0b77fL) - 0x1a43c7653869b86eL;
                                       continue;
                                    }

                                    value22 = (value18 ^ -0x591ea6b867a4e6a5L) - 0x6da02422df755cd8L;
                                    value23 = (value19 ^ -0x4856b3918b5357bfL) - 0xf7101f0d1795550L;
                                    break searchBlock;
                                 }

                                 value18 = (value18 ^ 0x391c923494d7dd0eL) - 0x28094be00075f028L;
                                 value19 = (value19 ^ -0xadc08e845424bccL) - 0x25c498e7522d434cL;
                              } else {
                                 value18 = (value18 ^ 0x5820706becbd6040L) - 0x12a222e9b317a51dL;
                                 value19 = (value19 ^ 0x438cb55f3a5b0251L) - 0x12a43487518bead4L;
                              }
                           }

                           if (value12 >= 4294967296L) {
                              value18 = (value18 ^ -0x57e0b2d0e714c2f3L) + 0x13ad848d8f194883L;
                              value19 = (value19 ^ 0x6b8208cafd875062L) + 0x17892531a8d0dbb9L;
                           } else {
                              value18 = (value18 ^ -0xba15888934bfe4bL) - 0x71ce9056eda39a8cL;
                              value19 = (value19 ^ 0x1eb4265a5827a6dcL) + 0x15df66b39b473644L;
                           }
                           continue;
                        }

                        if ((int)value12 >= 0) {
                           value18 = (value22 ^ 0x115f117f2b533fbeL) - 0xd51b59ea48e65e3L;
                           value19 = (value23 ^ -0xf514bc5adf6c5c0L) - 0x52ce4bdc921ddbdaL;
                        } else {
                           value18 = (value22 ^ -0x50c4a3353a1fc89L) + 0x519dd7ec20186ba8L;
                           value19 = (value23 ^ -0x3e8264d5b994741fL) + 0x2667b006af7f47aeL;
                        }
                        continue;
                     }

                     if (value15 >= 4294967296L) {
                        value18 = (value18 ^ 0x14a5df5f09162d43L) - 0x50fc578a34e22b82L;
                        value19 = (value19 ^ -0x54696dd653234245L) + 0x26c8b210293d1a80L;
                     } else {
                        value18 = (value18 ^ 0x1e8cb80f4a33c4a5L) - 0x28ca2cf56bd88687L;
                        value19 = (value19 ^ -0x40fdd4e81f466020L) + 0x251e7c76bab41f6L;
                     }
                     continue;
                  }

                  value8 = computeTimestamp(value11, value9, value6, value7, value12);
                  value18 = (value20 ^ -0x5b85cbd94f033876L) + 0x5879edf123d708e8L;
                  value19 = (value21 ^ -0x6ea5a2fa23e6b117L) + 0x7bd7cfcb1a630511L;
               } else {
                  flag = checkTime(value4 >>> value5, count16, count14, value16, value17);
                  value18 = (value18 ^ -0x43cff56573b871e8L) + 0x5642ffdea97c29f8L;
                  value19 = (value19 ^ -0x41d9df8b3ddafe7aL) - 0x336aa7e8f6bc571fL;
               }
            } else {
               value4 <<= value5;
               value18 = (value18 ^ -0x4a90bc2df148db19L) - 0x7b44a93221440f41L;
               value19 = (value19 ^ 0x60c56ccbcb532d15L) + 0x50ed5cebf8e71afaL;
            }
         } else if (value13 < 4294967296L) {
            value18 = (value18 ^ -0x273db4313943fc87L) - 0x50f2eb6febb3b795L;
            value19 = (value19 ^ 0x5f26b1a72635ed85L) - 0x3a0c8f921ebc2060L;
         } else {
            value18 = (value18 ^ -0xd82cb830347015bL) + 0x6af726cca0bc0828L;
            value19 = (value19 ^ 0x412d7b4d35bf25bbL) - 0x448b159bde5822f7L;
         }
      }
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, int count14, SignumComparable signumComparable4, int count15, int count16, int count17) {
      int count19 = 0;
      int count20 = 0;
      Object entry = null;
      long value2 = (long)count16 + (long)count15;
      char character = 0;
      long value3 = value2;
      if (computeCountBySignumComparable(signumComparable3, value3) <= count14) {
         value2 = (long)count14 - (long)count16;
         character = 0;
         long value4 = value2;
         count19 = computeCountBySignumComparable(signumComparable4, value4);
         count20 = count19 - count15;
         entry = resolveSignumComparable(signumComparable4, count20);
         return resolveSplineParser(signumComparable3, (SignumComparable)entry, count16, count17, count16);
      } else {
         count19 = count16 + count15;
         count20 = count19 - count14;
         entry = resolveSignumComparable(signumComparable3, count20);
         return resolveSplineParser((SignumComparable)entry, signumComparable4, count16, count17, count16);
      }
   }

   public SignumComparable getSignumComparableAsSignumComparable() {
      return this.resolveSplineParser(0, 7).getSignumComparable();
   }

   private static int computeCountByTime(long value) {
      int count14 = 0;
      count14 = (int)value;
      if ((long)count14 == value) {
         return count14;
      } else {
         throw new ArithmeticException(count14 <= 0 ? "Overflow" : "Underflow");
      }
   }

   public SplineParser resolveSplineParser(SplineParser splineParser4, CombatScaleProperty combatScaleProperty) {
      int count14 = combatScaleProperty.count;
      int count15 = this.count5;
      return this.resolveSplineParser(splineParser4, count15, count14);
   }

   public SplineParser resolveSplineParser(int count14) {
      long value = (long)this.count5 - (long)count14;
      return new SplineParser(this.signumComparable2, this.timestamp2, this.computeCount2(value), this.count9);
   }

   public SplineParser[] resolveSplineParserArray(SplineParser splineParser4) {
      SplineParser[] splineParser5 = null;
      splineParser5 = new SplineParser[]{this.resolveSplineParser(splineParser4), null};
      SplineParser splineParser6 = splineParser5[0].resolveSplineParser7(splineParser4);
      splineParser5[1] = this.resolveSplineParser3(splineParser6);
      return splineParser5;
   }

   public SplineParser resolveSplineParser(int count14, int count15) {
      int count16 = 0;
      long value = 0L;
      int count17 = 0;
      Object entry = null;
      if (count15 >= 0 && count15 <= 7) {
         count16 = this.count5;
         if (count14 == count16) {
            return this;
         } else if (this.getCountAsIndex() == 0) {
            return resolveSplineParser3(count14);
         } else if (this.timestamp2 != Long.MIN_VALUE) {
            value = this.timestamp2;
            if (count14 <= count16) {
               long value2 = (long)count16 - (long)count14;
               int count18 = this.computeCount2(value2);
               if (count18 >= timestampArray2.length) {
                  SignumComparable signumComparable3 = this.getSignumComparable();
                  SignumComparable signumComparable4 = resolveSignumComparableByIndex(count18);
                  return resolveSplineParser(signumComparable3, signumComparable4, count14, count15, count14);
               } else {
                  return resolveSplineParser(value, timestampArray2[count18], count14, count15, count14);
               }
            } else {
               long value3 = (long)count14 - (long)count16;
               count17 = this.computeCount2(value3);
               if ((value = computeTimestamp(value, count17)) == Long.MIN_VALUE) {
                  entry = this.resolveSignumComparable(count17);
                  return new SplineParser((SignumComparable)entry, Long.MIN_VALUE, count14, this.count9 <= 0 ? 0 : this.count9 + count17);
               } else {
                  return resolveSplineParserByTime(value, count14);
               }
            }
         } else if (count14 <= count16) {
            long value4 = (long)count16 - (long)count14;
            int count19 = this.computeCount2(value4);
            if (count19 >= timestampArray2.length) {
               SignumComparable signumComparable5 = this.signumComparable2;
               SignumComparable signumComparable6 = resolveSignumComparableByIndex(count19);
               return resolveSplineParser(signumComparable5, signumComparable6, count14, count15, count14);
            } else {
               long value5 = timestampArray2[count19];
               return resolveSplineParser(this.signumComparable2, value5, count14, count15, count14);
            }
         } else {
            long value6 = (long)count14 - (long)count16;
            int count20 = this.computeCount2(value6);
            SignumComparable signumComparable7 = resolveSignumComparable(this.signumComparable2, count20);
            return new SplineParser(signumComparable7, Long.MIN_VALUE, count14, this.count9 <= 0 ? 0 : this.count9 + count20);
         }
      } else {
         throw new IllegalArgumentException("Invalid rounding mode");
      }
   }

   private static void applyText(String text, SplineParser splineParser4) {
      System.err.format("%s:\tintCompact %d\tintVal %d\tscale %d\tprecision %d%n", text, splineParser4.timestamp2, splineParser4.signumComparable2, splineParser4.count5, splineParser4.count9);
   }

   private static long computeTimestamp(char[] characterArray, int count14, int count15) {
      long value = 0L;
      char character = '\u0000';
      long value2 = 0x6595607769c30678L;
      long value3 = 0x6a90713daa7a81feL;
      value = 0L;
      value2 = (value2 ^ -0x2a4a24d7eb0c081fL) + 0x6877f13750d1bb88L;
      value3 = (value3 ^ -0x50b2a17aa77f939fL) - 0x5b81d8b1441db022L;
      int count16 = count14 + 1;
      character = characterArray[count16];
      value2 = (value2 ^ 0xe16437f4dc1c0deL) - 0x1671b2fcdd7f6ec8L;
      value3 = (value3 ^ -0x7a80c5368ea4df9aL) + 0x6601e8f2762460faL;
      int count17 = count15 - 1;
      long value4;
      long value5;
      boolean flag;
      if (character != '-') {
         value4 = (value2 ^ -0x3045d705a9dfad09L) - 0x7be1a4960fab3966L;
         value5 = (value3 ^ -0x3176a46f987693d2L) - 0x7a8414260b87bc27L;
         flag = false;
      } else {
         value2 = (value2 ^ -0x46c2c7d6ab4901abL) - 0x655a9545123c8d08L;
         value3 = (value3 ^ 0x547299ad369a9391L) + 0x1fd629bec36a6890L;
         flag = true;
         value4 = (value2 ^ -0x2402d66d2f38921cL) - 0x3472d6914fc6a564L;
         value5 = (value3 ^ -0x18fd7f987bb5509cL) + 0x5b027fde83c95ca4L;
      }

      boolean flag2;
      searchBlock: {
         flag2 = flag;
         if (flag2) {
            value4 = (value4 ^ -0x79d2b6e45bcb98aL) - 0x57e24b907a42bdd2L;
            value5 = (value5 ^ 0x51e35dcfa2656a34L) - 0x4f9eabb95e62e60cL;
         } else if (character != '+') {
            value4 = (value4 ^ -0x7c69ebc943358c3aL) - 0x7c8e0c357bb99342L;
            value5 = (value5 ^ -0x6fdb1647bde4de0cL) + 0x701ce897c21ade34L;
            break searchBlock;
         }

         character = characterArray[++count16];
         count17--;
      }

      if (count17 <= 0) {
         throw new NumberFormatException();
      } else {
         long value6 = (value4 ^ 0x59f38ade5de5e0e3L) + 0x498e8a22235adfa1L;
         long value7 = (value5 ^ -0x75455f1e331dae4fL) + 0x759a9f603c224e6fL;

         long value8;
         long value9;
         while (true) {
            if (count17 <= 10) {
               value8 = (value6 ^ -0x7c15ca515d7a2bc1L) - 0x7c69eaad61852c0bL;
               value9 = (value7 ^ -0x46967b12cde86979L) - 0x7718849aedf16a7fL;
               break;
            }

            if (character == '0') {
               value6 = (value6 ^ -0x4ffba6a536d778dfL) + 0x700438a6b72780dfL;
               value7 = (value7 ^ -0x372c27d8237de145L) + 0x3731d81e2c820d65L;
            } else if (Character.digit(character, 10) != 0) {
               value8 = (value6 ^ 0x776f34aa17e32359L) + 0x2f1b1455e95d1d57L;
               value9 = (value7 ^ -0x6b5c6d162b54fa55L) + 0x6ba18d68346b0255L;
               break;
            }

            character = characterArray[++count16];
            count17--;
            value6 = (value6 ^ -0x20e1c0c231a6d18L) - 0x5a6f1cf25ba57258L;
            value7 = (value7 ^ 0x6d63d3f28fa49814L) - 0x2b1e31ae82a477ecL;
         }

         if (count17 > 10) {
            throw new NumberFormatException();
         } else {
            long value10 = (value8 ^ -0x2285ee73cb6847f7L) - 0x3afa0e8bf37757f9L;
            long value11 = (value9 ^ -0x2d26bca581eb6f29L) + 0x2d373eb9ce0b8f51L;

            while (true) {
               int count18;
               long value12;
               long value13;
               guardBlock: {
                  long value14;
                  long value15;
                  if (character < '0') {
                     value14 = (value10 ^ -0x5e6a34ff35af1c20L) + 0x6174cb00b5cfdca0L;
                     value15 = (value11 ^ -0x2110ce6a1c58b61fL) + 0x2165306c615f361fL;
                  } else {
                     if (character <= '9') {
                        count18 = character - '0';
                        value12 = (value10 ^ 0x6816dafa82d77620L) + 0x17f1ba067e4971e0L;
                        value13 = (value11 ^ -0x3f147335e1a31ed7L) + 0x3f698349edd39ed7L;
                        break guardBlock;
                     }

                     value14 = (value10 ^ -0x4746ba4a4371df60L) + 0x6849454b84821f60L;
                     value15 = (value11 ^ -0x1c574a817ec8b4aeL) + 0x5ea8acd57eef38ceL;
                  }

                  count18 = Character.digit(character, 10);
                  if (count18 < 0) {
                     throw new NumberFormatException();
                  }

                  value12 = (value14 ^ -0x4c9e1f61e54baf5L) - 0x552e0208205abcf7L;
                  value13 = (value15 ^ 0x1e042f49950c8966L) - 0x1e03dd378cfc895eL;
               }

               value = value * 10L + (long)count18;
               if (count17 == 1) {
                  if (flag2) {
                     value = -value;
                  }

                  return value;
               }

               long value16 = (value12 ^ -0x101ab4f2f532fb78L) - 0x685ab50d09bcfc78L;
               long value17 = (value13 ^ -0x60dab6102fb67e31L) + 0x631b48662fc87e39L;
               character = characterArray[++count16];
               count17--;
               value10 = (value16 ^ -0x7d18cd48bd522596L) - 0x7d5cedb6c15d39d6L;
               value11 = (value17 ^ -0x6e35b97236d670ccL) + 0x704a398436e87cf4L;
            }
         }
      }
   }

   private static boolean matchesCurveAccumulator(long value, int count14, int count15, CurveAccumulator curveAccumulator, long value2) {
      int count16 = 0;
      long value3 = 0x2bd93f3ed396fbfdL;
      long value4 = -0x5bef6101860b8b7bL;

      while (true) {
         int count17 = (int)(value3 % 8L);
         if (count17 == 7) {
            value4 = (value4 ^ -0x3f22e3fd38840e56L) + 0x380c9e5a5933f131L;
            break;
         }

         if (count17 != 2) {
            if (count17 == 0) {
               break;
            }

            if (count17 != 4) {
               if (count17 != 3) {
                  if (count17 != 1) {
                     if (enabled) {
                        value3 = (value3 ^ 0x4e99cef6c8a3f16L) - 0x385a0f10ebd0b27L;
                        value4 = (value4 ^ -0x6ee046583121ec1eL) - 0x5ee773fd1a9f20a2L;
                     } else {
                        value3 = (value3 ^ -0x5db9cbb74190e07bL) - 0x36087cb0528e0435L;
                        value4 = (value4 ^ 0x4b564e8226a3ec91L) + 0x39e34116f507f3c7L;
                     }
                  } else if (value2 <= 0x3fffffffffffffffL) {
                     long value5 = (value3 ^ 0x22e13efcc1840e76L) - 0x1ca20aed1e4643a8L;
                     long value6 = (value4 ^ -0x3af467ad8c881913L) - 0x12827980ae4685b6L;
                     count16 = computeCount3(2L * value2, value);
                     value3 = (value5 ^ 0x64a996365a10d688L) - 0x29f139284104b36fL;
                     value4 = (value6 ^ -0x74fe4b61995602dL) + 0x505f97a178434b68L;
                  } else {
                     value3 = (value3 ^ -0x15ce48c4eafff553L) + 0xe5418c9c2e7593eL;
                     value4 = (value4 ^ -0x791eca6ecbe29034L) + 0x4f44007fe6397bbcL;
                  }
               } else {
                  if (value2 == 0L) {
                     throw new AssertionError();
                  }

                  value3 = (value3 ^ -0x3c246c613a2d709bL) - 0x64a21a782e59b562L;
                  value4 = (value4 ^ -0x2f15d0d653d20454L) - 0x23988b5e5be729b2L;
               }
            } else if (value2 <= -0x4000000000000000L) {
               value3 = (value3 ^ 0x7a692313014a9037L) - 0x45210abf919976f9L;
               value4 = (value4 ^ -0x1a1dcb0f429709feL) - 0x2c58af238f6f3182L;
            } else {
               value3 = (value3 ^ 0x41bab9fdbeec3843L) - 0x559471cbc51f2e76L;
               value4 = (value4 ^ 0x53289fd812388d99L) - 0x46453eeaf68d5f17L;
            }
         } else {
            count16 = 1;
            value3 = (value3 ^ 0x59481ff27e796796L) - 0x3bee9c05f738bbadL;
            value4 = (value4 ^ 0x1a6cab58d19a96b9L) - 0x33c0778506e3e50aL;
         }
      }

      return checkIndex(count14, count15, count16, curveAccumulator.isActive3());
   }

   public SplineParser resolveSplineParser(FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      return this.resolveSplineParser2(fG7jSMNbfywSerializable);
   }

   private SignumComparable getSignumComparable() {
      return this.signumComparable2 != null ? this.signumComparable2 : SignumComparable.resolveSignumComparableByTime(this.timestamp2);
   }

   public int getCount() {
      int count14 = 0;
      long value = 0L;
      long value2 = 0x6d91b04662d59bf8L;
      long value3 = -0x58f2b5e224c3410cL;

      while (true) {
         int count15 = (int)(value2 % 8L);
         if (count15 != 6) {
            if (count15 != 1) {
               if (count15 != 7) {
                  if (count15 != 2) {
                     if (count15 == 4) {
                        break;
                     }

                     if (count15 == 5) {
                        if (count14 != 0) {
                           break;
                        }

                        value2 = (value2 ^ -0x33e8e563e41c3585L) - 0x52f4f80975f6fbc7L;
                        value3 = (value3 ^ 0x60cbffb10ec0e352L) - 0x5621256aa13c4429L;
                        continue;
                     }

                     if (count15 != 3) {
                        count14 = this.count9;
                        value2 = (value2 ^ -0x1b6c962863feca63L) - 0x23e2f10e34adda40L;
                        value3 = (value3 ^ -0x5a8910fa5998a91aL) + 0x37d8b9616b33d66fL;
                        continue;
                     }

                     value2 = (value2 ^ -0x67ffbe3ae50ae8d9L) - 0x5cca040441b1847aL;
                     value3 = (value3 ^ 0x1af77f79d0382644L) + 0x2dd40cd2a3ebe8e9L;
                  }

                  this.count9 = count14;
                  break;
               } else {
                  value = this.timestamp2;
                  value2 = (value2 ^ -0x109ed637d7baa3f3L) + 0x4a22f3cb4e08b82cL;
                  value3 = (value3 ^ 0x14ac0ddefd7e4dc4L) - 0x22bf78e62f1ef042L;
               }
            } else {
               count14 = computeCount4(value);
               value2 = (value2 ^ -0x303f6e77044ad27fL) - 0x72df28cbdd909c55L;
               value3 = (value3 ^ 0x61ccc80cb2175a21L) + 0x13ec419a9dc51bd0L;
            }
         } else if (value == Long.MIN_VALUE) {
            long value4 = (value2 ^ -0x208783cd1f3f8dc1L) + 0x604a8b3f33977c3aL;
            long value5 = (value3 ^ -0x57fa8afd54369b54L) + 0x3a1966856bb9bbb1L;
            count14 = computeCount(this.signumComparable2);
            value2 = (value4 ^ -0x468a5f1f3fc21819L) - 0x3bdfe31f8868d53aL;
            value3 = (value5 ^ -0x6166ba539dd51aa2L) - 0x560e197aea05d611L;
         } else {
            value2 = (value2 ^ -0x665f2bdc8e2e9b8dL) - 0x1a5d155be67be2acL;
            value3 = (value3 ^ -0x1f3c05a6425172e0L) - 0x1488834f273eeccL;
         }
      }

      return count14;
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, int count14, long value, int count15, int count16, int count17) {
      int count19 = 0;
      int count20 = 0;
      Object entry = null;
      long value3 = 0L;
      long value4 = (long)count16 + (long)count15;
      long value5 = value4;
      if (computeCountBySignumComparable(signumComparable3, value5) <= count14) {
         count19 = computeCount(value, (long)count14 - (long)count16);
         count20 = count19 - count15;
         if (count20 < timestampArray2.length && (value3 = computeTimestamp(value, count20)) != Long.MIN_VALUE) {
            return resolveSplineParser(signumComparable3, value3, count16, count17, count16);
         } else {
            entry = resolveSignumComparable(value, count20);
            return resolveSplineParser(signumComparable3, (SignumComparable)entry, count16, count17, count16);
         }
      } else {
         count19 = count16 + count15;
         count20 = count19 - count14;
         entry = resolveSignumComparable(signumComparable3, count20);
         return resolveSplineParser((SignumComparable)entry, value, count16, count17, count16);
      }
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, int count14, long value) {
      Object entry = signumComparable3;
      int count15 = count14;
      SignumComparable[] signumComparableArray2 = null;
      long value3 = 0x2d8fd83144e6ff9L;
      long value4 = 0x1800119f52c97ec7L;

      while (true) {
         int count17 = (int)(value3 % 8L);
         if (count17 != 1) {
            if (count17 == 2) {
               long value5 = (long)count15 - (value4 % 821213L ^ 518138L);
               long value6 = value5;
               count15 = computeCountBySignumComparable((SignumComparable)entry, value6);
               value3 = (value3 ^ 0x78340e476da7962aL) - 0x18d95f948dc7058dL;
               value4 = (value4 ^ 0x1f4c7603373c7c42L) + 0x3a259b2bf4a3c161L;
               continue;
            }

            if (count17 != 3) {
               if (count17 == 0) {
                  break;
               }

               if (count17 != 4) {
                  if (count17 == 6) {
                     break;
                  }

                  if (count17 != 5) {
                     byte byteValue2 = 0;
                     if (!((SignumComparable)entry).checkIndexByIndex(byteValue2)) {
                        long value7 = (value3 ^ 0x1d143bc8e68c1253L) + 0x12875d6b442bd572L;
                        long value8 = (value4 ^ -0x5acf964187fe1f37L) + 0x7c43715897c77ed3L;
                        SignumComparable signumComparable4 = SignumComparable.signumComparable3;
                        signumComparableArray2 = ((SignumComparable)entry).resolveSignumComparableArray(signumComparable4);
                        value3 = (value7 ^ 0x2bae1c48167fd380L) - 0x278cf170dfc753f2L;
                        value4 = (value8 ^ 0x6aaae113d889d829L) + 0x4a67145c3f859baeL;
                     } else {
                        value3 = (value3 ^ 0x4a8cc260474d6465L) + 0x61eee703a5ecdf84L;
                        value4 = (value4 ^ -0xb1a69e067e28518L) + 0x4af7a0f9b7bc18f2L;
                     }
                     continue;
                  }

                  if ((long)count15 <= value) {
                     break;
                  }

                  value3 = (value3 ^ -0x1e7a7c2f15d8d5e5L) + 0x57e8e1ccd4c4ab61L;
                  value4 = (value4 ^ -0x1e3f1828757ec74bL) + 0x55067c78ab14bdefL;
                  continue;
               }

               if (signumComparableArray2[1].getCount8() == 0) {
                  long value9 = (value3 ^ -0x2556413fa71c3e52L) + 0x34ac242a8c58ddc6L;
                  long value10 = (value4 ^ 0x4c49f1865b8e6faeL) - 0x1de9e5e7fe132a25L;
                  entry = signumComparableArray2[0];
                  value3 = (value9 ^ 0x2d4b134c49cc1626L) - 0x19e942d544753ac4L;
                  value4 = (value10 ^ -0x5e69815ead30a475L) - 0x6c994c5d921a3d75L;
               } else {
                  value3 = (value3 ^ -0x3c6a63f692e013b7L) + 0x4be84563973cf263L;
                  value4 = (value4 ^ 0x4b3c8d20a92e898bL) - 0x20df494e4fb40802L;
               }
               continue;
            }

            value3 = (value3 ^ 0x4c4816b72e768d6eL) - 0x2ae387ba94b85facL;
            value4 = (value4 ^ -0x33471b3a53db677aL) - 0x7eb1620a9a59e0afL;
         }

         SignumComparable signumComparable5 = SignumComparable.signumComparable3;
         if (((SignumComparable)entry).computeCount(signumComparable5) < 0) {
            break;
         }

         value3 = (value3 ^ -0x7713e6cca213d018L) - 0x72c7c930910c3dc4L;
         value4 = (value4 ^ 0x24f78cd4d774a73dL) - 0x115fb702abc7e073L;
      }

      byte byteValue3 = 0;
      return resolveSplineParser((SignumComparable)entry, count15, byteValue3);
   }

   private static SplineParser resolveSplineParser(long value, int count14, long value2, int count15, long value3, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count16 = count15;
      int count17 = 0;
      int count18 = 0;
      int count19 = 0;
      SplineParser splineParser4 = null;
      int count20 = 0;
      SignumComparable signumComparable3 = null;
      SignumComparable signumComparable4 = null;
      int count21 = 0;
      long value5 = 0x6fe18a4d72524f92L;
      long value6 = -0x5cec17d6d2357326L;

      while (true) {
         int count22 = (int)(value5 % 23L);
         if (count22 != 5) {
            if (count22 == 7) {
               signumComparable4 = resolveSignumComparable(value2, count21);
               value5 = (value5 ^ -0x2e474828a039b5a1L) + 0x7cea89e0a8a01d6bL;
               value6 = (value6 ^ -0x1381eceee7783ae7L) + 0xe090631e49889b7L;
            } else if (count22 != 13) {
               if (count22 != 19) {
                  if (count22 != 8) {
                     if (count22 != 20) {
                        if (count22 != 15) {
                           if (count22 != 6) {
                              if (count22 != 12) {
                                 if (count22 != 4) {
                                    if (count22 == 9) {
                                       return resolveSplineParserByFG7jSMNbfywSeria(value, count14, value2, count16, value3, fG7jSMNbfywSerializable);
                                    }

                                    if (count22 == 0) {
                                       count20 = computeCountByTime((long)count17 + (long)count16 - (long)count14);
                                       value5 = (value5 ^ -0x6d1bf736a676ffc0L) + 0x637674e6626d0906L;
                                       value6 = (value6 ^ -0x62ee0c6326cb4281L) - 0x34c022763cf2506aL;
                                       continue;
                                    }

                                    if (count22 == 17) {
                                       splineParser4 = resolveSplineParser(value, value2, count19, count18, computeCountByTime(value3));
                                       value5 = (value5 ^ 0x7c454128c4f35597L) - 0x45d5e582230e03bbL;
                                       value6 = (value6 ^ -0x173a017b830bc661L) - 0x6de8d5abf1695c4L;
                                       continue;
                                    }

                                    if (count22 == 1) {
                                       break;
                                    }

                                    if (count22 == 14) {
                                       long value7;
                                       if ((value7 = computeTimestamp(value2, count21)) != Long.MIN_VALUE) {
                                          long value8 = (value5 ^ 0x38ec2db3588fd914L) - 0x64864d18ac1ace21L;
                                          long value9 = (value6 ^ 0x74d3912a4a2fed9fL) + 0x2362d4943e77f979L;
                                          splineParser4 = resolveSplineParser(value, value7, count19, count18, computeCountByTime(value3));
                                          value5 = (value8 ^ 0x2de4c7599cfa479cL) + 0x49c2f25b9aecbee8L;
                                          value6 = (value9 ^ -0x7f48d9d10f5d1afL) - 0x2546e4a5389ee50bL;
                                       } else {
                                          value5 = (value5 ^ 0x1cc0f4e36d29e094L) + 0x3071a32f9391763aL;
                                          value6 = (value6 ^ -0x60a47d6e01458c2cL) + 0x5cb19954156e9e65L;
                                       }
                                       continue;
                                    }

                                    if (count22 == 3) {
                                       if (count17 >= 18) {
                                          value5 = (value5 ^ -0x5cc1f4aab98213c4L) - 0x387b666d943a41a0L;
                                          value6 = (value6 ^ -0x5b599378cb86134fL) - 0x25998df0f320e051L;
                                       } else {
                                          value5 = (value5 ^ -0x12d140ef3ab73dbeL) - 0x69724eb26ef21b74L;
                                          value6 = (value6 ^ -0x38ac280a74fdb35fL) - 0x352f71c4a8d203bdL;
                                       }
                                       continue;
                                    }

                                    if (count22 == 2) {
                                       value6 = (value6 ^ 0x79847a62a0fb7179L) + 0x1ba2cd70e7c06e5bL;
                                       break;
                                    }

                                    if (count22 == 21) {
                                       count17 = fG7jSMNbfywSerializable.count2;
                                       value5 = (value5 ^ 0x2bbf2f5bf23e7645L) - 0x2142499ebbb8f0ecL;
                                       value6 = (value6 ^ -0x566bf8d6b09115c6L) - 0x5bb2b1fd8a63861cL;
                                       continue;
                                    }

                                    if (count22 == 18) {
                                       if (count14 > count16) {
                                          value5 = (value5 ^ -0x6f82970253439b2cL) - 0x6157c091ef600f56L;
                                          value6 = (value6 ^ 0x6c362bdce877c267L) + 0xfa2052180a90fdL;
                                       } else {
                                          value5 = (value5 ^ 0x1f8dd192e564976aL) - 0x263ef3775c5aaa2fL;
                                          value6 = (value6 ^ 0x2cee6883efc6643L) + 0x4452999c2f1c5592L;
                                       }
                                       continue;
                                    }

                                    if (count22 == 10) {
                                       count19 = computeCountByTime(value3 + (long)count16 - (long)count14 + (long)count17);
                                       value5 = (value5 ^ 0x787c950e6e23b05L) + 0x68fe556e604a3f69L;
                                       value6 = (value6 ^ 0x35627856d01352e2L) - 0x1466506ecafba9d2L;
                                       continue;
                                    }

                                    if (count22 == 11) {
                                       long value10;
                                       if ((value10 = computeTimestamp(value, count20)) != Long.MIN_VALUE) {
                                          long value11 = (value5 ^ -0x75e757acea85e9bL) - 0x7800537fb6a2410bL;
                                          long value12 = (value6 ^ -0x37abde20e1951664L) + 0x7780fb5e87c99442L;
                                          splineParser4 = resolveSplineParser(value10, value2, count19, count18, computeCountByTime(value3));
                                          value5 = (value11 ^ -0x75bdcc87c95ec3a3L) + 0x79c2a33f1f3d76b6L;
                                          value6 = (value12 ^ 0x3b97a860e9c52c96L) - 0x4f54659c1b18434dL;
                                       } else {
                                          value5 = (value5 ^ -0x4c5085910de31fe2L) + 0x55ff1e9db963741fL;
                                          value6 = (value6 ^ -0x6fd4c2e34c7ba078L) - 0x5f66014d51fe59ffL;
                                       }
                                       continue;
                                    }

                                    if (count22 == 22) {
                                       signumComparable3 = resolveSignumComparable(value, count20);
                                       value5 = (value5 ^ -0x6357038dcc3b0ee3L) + 0x6ef7f550d2cbd6c1L;
                                       value6 = (value6 ^ -0x7bb33c87d48f3f77L) - 0x544b0d3e2ad4f4a1L;
                                       continue;
                                    }
                                 } else {
                                    value5 = (value5 ^ -0x30b1e99e5088e4f1L) + 0x34b68057b7eb9968L;
                                    value6 = (value6 ^ -0x5fbb8957efd67ec9L) + 0x2ade57acbafc1114L;
                                 }

                                 value6 = (value6 ^ -0x54b391d9b5f96572L) - 0x16946faf219a4710L;
                                 break;
                              }

                              value6 = (value6 ^ -0x20cd81f346ebd4d9L) + 0x1a2276c9d4b185fL;
                              break;
                           }

                           SignumComparable signumComparable5 = SignumComparable.resolveSignumComparableByTime(value);
                           int count23 = computeCountByTime(value3);
                           splineParser4 = resolveSplineParser(signumComparable5, signumComparable4, count19, count18, count23);
                           value5 = (value5 ^ 0x38703999305bddcbL) - 0x16e721baabafdbbcL;
                           value6 = (value6 ^ 0x13e312be30375d1cL) - 0x7d60b651e7ac62c3L;
                           continue;
                        }

                        int count24 = computeCountByTime(value3);
                        splineParser4 = resolveSplineParser(signumComparable3, value2, count19, count18, count24);
                        value5 = (value5 ^ 0x6e740cc0b42e7881L) - 0x424d178a4c0aa5d0L;
                        value6 = (value6 ^ 0xbbfc4b258246e31L) + 0x75cb8cf425eddf74L;
                        continue;
                     }

                     count16--;
                  } else {
                     if (computeCount(value, count14, value2, count16) > 0) {
                        value5 = (value5 ^ -0x329ee8f275d7628bL) + 0x76f58c37629921caL;
                        value6 = (value6 ^ -0x376ab46c8d0292a6L) - 0x6fdecace402aeb6dL;
                        continue;
                     }

                     value5 = (value5 ^ 0x19737fd28856462aL) - 0x351c1afd9bd8b75dL;
                     value6 = (value6 ^ 0x3c7391202ff6de94L) - 0x6444f43b6d2579a7L;
                  }

                  count18 = fG7jSMNbfywSerializable.combatScaleProperty.count;
                  value5 = (value5 ^ 0xf5a1e425020728dL) - 0x10d72d91468b8c65L;
                  value6 = (value6 ^ -0x340a37a423aa479L) - 0x68982dee30e65c9fL;
               } else if (count16 >= 18) {
                  value5 = (value5 ^ -0x46e45e3e1da9a79L) + 0x644645869d56cc14L;
                  value6 = (value6 ^ -0x398561ffcfd80966L) - 0x7237b3f6ddbd76e3L;
               } else {
                  value5 = (value5 ^ 0x190c7b0ec8cd5b1dL) + 0x1a5be5d03ce44466L;
                  value6 = (value6 ^ -0x7dbb2a25d6878c21L) - 0x2459f7572d853086L;
               }
            } else if (computeCountByTime((long)count17 + (long)count16 - (long)count14) <= 0) {
               long value13 = (value5 ^ -0x14283fd4693a53a1L) - 0x616f1df78fa3addbL;
               long value14 = (value6 ^ -0x340a168950122257L) - 0x2230964287736226L;
               count20 = computeCountByTime((long)count14 - (long)count17);
               value5 = (value13 ^ -0x7a4326ef83c8774dL) - 0x3585f57bbc25fd65L;
               value6 = (value14 ^ 0x4f4c632c78c511c1L) + 0x309cc6b5f031bd4bL;
            } else {
               value5 = (value5 ^ -0x6229c7a79fd2c870L) - 0x67ab1b6f41dedfd3L;
               value6 = (value6 ^ 0x1b97d4d46b612715L) - 0x1333be3b187f999aL;
            }
         } else if (count20 != count16) {
            long value15 = (value5 ^ 0x232ee8166f3b3b95L) - 0x2595999047a746deL;
            long value16 = (value6 ^ -0x181f3d57556a17dfL) + 0x250f5deace625dcdL;
            count21 = computeCountByTime((long)count20 - (long)count16);
            value5 = (value15 ^ -0x1148772542da4fd1L) + 0x7e15e1a2f191024fL;
            value6 = (value16 ^ -0x513fe1ac8539c6faL) + 0x2df7fa8ad12125c4L;
         } else {
            value5 = (value5 ^ -0x30155ff026b60d99L) + 0x5140d522e20aab86L;
            value6 = (value6 ^ -0x5cf6994829215e19L) + 0x3fd9cce06dd02446L;
         }
      }

      return resolveSplineParser(splineParser4, fG7jSMNbfywSerializable);
   }

   private static SplineParser resolveSplineParser(long value, long value2, int count14) {
      long value3 = 0L;
      value3 = computeTimestamp2(value, value2);
      return value3 == Long.MIN_VALUE
         ? new SplineParser(SignumComparable.resolveSignumComparableByTime(value).resolveSignumComparable2(value2), count14)
         : resolveSplineParserByTime(value3, count14);
   }

   public SplineParser getSplineParser() {
      return this.timestamp2 != Long.MIN_VALUE
         ? resolveSplineParser(-this.timestamp2, this.count5, this.count9)
         : new SplineParser(this.signumComparable2.getSignumComparable2(), Long.MIN_VALUE, this.count5, this.count9);
   }

   private static SignumComparable resolveSignumComparable(long value, int count14) {
      return count14 > 0 ? resolveSignumComparableByIndex(count14).resolveSignumComparable(value) : SignumComparable.resolveSignumComparableByTime(value);
   }

   public SplineParser resolveSplineParserBySplineParser(SplineParser splineParser4) {
      return this.resolveSplineParserArray(splineParser4)[1];
   }

   public SplineParser resolveSplineParserBySplineParser(SplineParser splineParser4, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      return this.resolveSplineParserArray(splineParser4, fG7jSMNbfywSerializable)[1];
   }

   public SplineParser resolveSplineParser(SplineParser splineParser4) {
      int count14 = 0;
      int count15 = 0;
      SplineParser splineParser5 = null;
      count14 = computeCount((long)this.count5 - (long)splineParser4.count5);
      if (this.computeCount(splineParser4) >= 0) {
         if (this.getCountAsIndex() == 0 && splineParser4.getCountAsIndex() != 0) {
            byte byteValue = 7;
            return this.resolveSplineParser(count14, byteValue);
         } else {
            count15 = (int)Math.min(
               (long)this.getCount() + (long)Math.ceil(10.0 * (double)splineParser4.getCount() / 3.0) + Math.abs((long)this.getCount5() - (long)splineParser4.getCount5()) + 2L,
               2147483647L
            );
            FG7jSMNbfywSerializable fG7jSMNbfywSerializable = new FG7jSMNbfywSerializable(count15, CombatScaleProperty.DOWN);
            splineParser5 = this.resolveSplineParser2(splineParser4, fG7jSMNbfywSerializable);
            if (splineParser5.count5 > 0) {
               CombatScaleProperty combatScaleProperty = CombatScaleProperty.DOWN;
               byte byteValue2 = 0;
               splineParser5 = splineParser5.resolveSplineParser(byteValue2, combatScaleProperty);
               int count16 = splineParser5.count5;
               long timestamp6 = splineParser5.timestamp2;
               splineParser5 = resolveSplineParser(splineParser5.signumComparable2, timestamp6, count16, count14);
            }

            if (splineParser5.count5 < count14) {
               byte byteValue3 = 7;
               splineParser5 = splineParser5.resolveSplineParser(count14, byteValue3);
            }

            return splineParser5;
         }
      } else {
         return resolveSplineParser3(count14);
      }
   }

   public int getCount5() {
      return this.count5;
   }

   private static SignumComparable resolveSignumComparable(SignumComparable signumComparable3, int count14, int count15) {
      long value2 = 0x8610cdfe4afd541L;

      SignumComparable signumComparable4;
      while (true) {
         if ((int)(value2 % 4L) != 1) {
            long value3 = timestampArray2[count14];
            long value4 = value3;
            signumComparable4 = resolveSignumComparableBySignumComparable(signumComparable3, value4, count15);
            break;
         }

         if (count14 >= timestampArray2.length) {
            SignumComparable signumComparable5 = resolveSignumComparableByIndex(count14);
            signumComparable4 = resolveSignumComparable(signumComparable3, signumComparable5, count15);
            break;
         }

         value2 = (value2 ^ -0x3a80c7702af820f3L) - 0x7133648127e19b0dL;
      }

      return signumComparable4;
   }

   private String resolveText(boolean flag) {
      long value2 = 0x1062ff6fa985797eL;
      long value3 = 0xcc7c6cc985156c3L;
      if (this.count5 != 0) {
         long value4 = (value2 ^ 0x5a8d2bbcbfc42b0dL) - 0x471a24b92c8c34a8L;
         long value5 = (value3 ^ -0x397834c6291f756fL) - 0x3148090f11e107b9L;
         long value6;
         long value7;
         if (this.count5 != 2) {
            value6 = (value4 ^ 0x78e14a0831fe6eaL) - 0x85f49f80f5dd56L;
            value7 = (value5 ^ 0x79f5aa9350499140L) - 0x4815a98f2fc87140L;
         } else if (this.timestamp2 < 0L) {
            value6 = (value4 ^ 0x4a64185fb45203e1L) - 0x45dbf82c7432005fL;
            value7 = (value5 ^ -0x2326603f8d57b33cL) + 0x54d669bfee583c3cL;
         } else {
            if (this.timestamp2 < 2147483647L) {
               int count15 = (int)this.timestamp2 % 100;
               int count16 = (int)this.timestamp2 / 100;
               return Integer.toString(count16) + '.' + SmoothCollector.charCodeArray[count15] + SmoothCollector.charCodeArray2[count15];
            }

            value6 = (value4 ^ 0x142aaccdf5048311L) - 0x14296cba32fc810fL;
            value7 = (value5 ^ 0x7746d64f2121d14eL) - 0x56c6cdc2e120313eL;
         }

         SmoothCollector smoothCollector = (SmoothCollector)threadLocal.get();
         char[] characterArray;
         int count17;
         long value8;
         long value9;
         if (this.timestamp2 == Long.MIN_VALUE) {
            value8 = (value6 ^ -0x15d7caa057769dceL) + 0x19d82ad3a8789dd2L;
            value9 = (value7 ^ -0x17814b56e303ba14L) + 0x287154971ca44324L;
            count17 = 0;
            characterArray = this.signumComparable2.getSignumComparable5().toString().toCharArray();
         } else {
            long value10 = Math.abs(this.timestamp2);
            long value11 = value10;
            count17 = smoothCollector.computeCount(value11);
            characterArray = smoothCollector.getCharCodeArray3();
            value8 = (value6 ^ -0x62239bd2a5c9269aL) + 0x65cbdbe53631591eL;
            value9 = (value7 ^ 0x8d8ef44ed6f12b1L) + 0x8d719436b110e71L;
         }

         StringBuilder builder = smoothCollector.setLength();
         if (this.getCountAsIndex() >= 0) {
            value8 = (value8 ^ -0x65570b192f7b8d31L) + 0x6a586b1ab083aec7L;
            value9 = (value9 ^ -0x69a8f55d0cb9db7eL) - 0x75b7095e92c61b82L;
         } else {
            builder.append('-');
         }

         int count18 = characterArray.length - count17;
         long value12 = -((long)this.count5) + (long)(count18 - 1);
         if (this.count5 >= 0 && value12 >= -6L) {
            int count19 = this.count5 - count18;
            if (count19 < 0) {
               builder.append(characterArray, count17, -count19);
               builder.append('.');
               builder.append(characterArray, -count19 + count17, this.count5);
            } else {
               builder.append('0');
               builder.append('.');

               while (count19 > 0) {
                  builder.append('0');
                  count19--;
                  value8 = (value8 ^ 0x366ae89766178319L) - 0x31e9a874a5ed8107L;
                  value9 = (value9 ^ 0x2c398bcca6a46961L) - 0x1bc98a445da3e95fL;
               }

               builder.append(characterArray, count17, count18);
            }
         } else {
            if (!flag) {
               int count20 = (int)(value12 % 3L);
               if (count20 < 0) {
                  count20 += 3;
               }

               value12 -= (long)count20;
               count20++;
               if (this.getCountAsIndex() != 0) {
                  if (count20 < count18) {
                     builder.append(characterArray, count17, count20);
                     builder.append('.');
                     builder.append(characterArray, count17 + count20, count18 - count20);
                  } else {
                     builder.append(characterArray, count17, count18);

                     for (int index = count20 - count18; index > 0; index--) {
                        builder.append('0');
                     }
                  }
               } else if (count20 == 3) {
                  builder.append("0.0");
                  value12 += 3L;
               } else if (count20 == 2) {
                  builder.append("0.00");
                  value12 += 3L;
               } else {
                  if (count20 != 1) {
                     throw new AssertionError("Unexpected sig value " + count20);
                  }

                  builder.append('0');
               }
            } else {
               builder.append(characterArray[count17]);
               if (count18 > 1) {
                  builder.append('.');
                  builder.append(characterArray, count17 + 1, count18 - 1);
               }
            }

            if (value12 != 0L) {
               builder.append('E');
               if (value12 > 0L) {
                  builder.append('+');
               }

               builder.append(value12);
            }
         }

         return builder.toString();
      } else {
         value2 = (value2 ^ -0x8e86ac316e7d659L) + 0x1afed27ecf324396L;
         value3 = (value3 ^ -0x7a20ecaeda99dbefL) - 0x6b5daf3d37b91b93L;
         String text;
         if (this.timestamp2 == Long.MIN_VALUE) {
            text = this.signumComparable2.toString();
         } else {
            value2 = (value2 ^ 0x579f27982af63e02L) - 0x52156b303b848ca2L;
            value3 = (value3 ^ 0x5539de4223dafa2aL) + 0x50750cc3962c2784L;
            text = Long.toString(this.timestamp2);
         }

         return text;
      }
   }

   public SplineParser resolveSplineParser(SplineParser splineParser4, int count14) {
      int count15 = this.count5;
      return this.resolveSplineParser(splineParser4, count15, count14);
   }

   public String getText() {
      int count14 = 0;
      StringBuilder builder = null;
      String text = null;
      int count15 = 0;
      String text2 = null;
      long value2 = 0x73a4ccd9276b620cL;
      long value3 = -0x49b0a24568009369L;

      while (true) {
         int count16 = (int)(value2 % 22L);
         if (count16 != 13) {
            if (count16 != 3) {
               if (count16 == 12) {
                  count14 = computeCountByTime(-((long)this.count5));
                  value2 = (value2 ^ 0x26a8a5ec7c0b943fL) + 0x4811c36c4eb7ba4bL;
                  value3 = (value3 ^ 0x33cbd4c97553b0d4L) + 0xc13df6294e4ef70L;
                  continue;
               }

               if (count16 != 14) {
                  if (count16 != 16) {
                     if (count16 != 10) {
                        if (count16 != 4) {
                           if (count16 != 17) {
                              if (count16 == 6) {
                                 value3 = (value3 ^ 0x2d7a1e27585ca74eL) + 0x379038e4fad1e92cL;
                                 break;
                              }

                              if (count16 == 21) {
                                 break;
                              }

                              if (count16 != 18) {
                                 if (count16 == 19) {
                                    builder = new StringBuilder(text.length() + count14);
                                    value2 = (value2 ^ 0x2ef830d59648742fL) - 0x3ccc5ad8428bbe67L;
                                    value3 = (value3 ^ 0x33b1df937e9ab4afL) - 0x6e1203c53f2b921dL;
                                    continue;
                                 }

                                 if (count16 == 15) {
                                    if (this.timestamp2 != Long.MIN_VALUE) {
                                       return Long.toString(this.timestamp2);
                                    }

                                    value2 = (value2 ^ 0x542e945a3d0afc96L) - 0x4a89069560e48e7bL;
                                    value3 = (value3 ^ -0x1066e5248e26d9a6L) - 0x679862999d47102fL;
                                    continue;
                                 }

                                 if (count16 == 1) {
                                    builder.append(text);
                                    value2 = (value2 ^ -0x3d8ebb42baa94b94L) + 0x2138abf4d2a84fb2L;
                                    value3 = (value3 ^ 0x7cc3509d9f14d5a5L) - 0x39b885b0a266e79bL;
                                    continue;
                                 }

                                 if (count16 == 11) {
                                    builder.append(this.timestamp2);
                                    value2 = (value2 ^ 0x1c8b19f0c10f977aL) + 0x68689e699e3fe79L;
                                    value3 = (value3 ^ 0x2daaab66ad8eb382L) - 0x4841d2ddc09b402dL;
                                    continue;
                                 }

                                 if (count16 == 0) {
                                    return this.signumComparable2.toString();
                                 }

                                 if (count16 == 2) {
                                    text2 = Long.toString(Math.abs(this.timestamp2));
                                    value2 = (value2 ^ -0x138b4853ef1f17eeL) - 0x685ae5f8f7e338fcL;
                                    value3 = (value3 ^ -0x6039b422ea510955L) + 0x2f5e5a84e4b6ccaaL;
                                    continue;
                                 }

                                 if (count16 == 8) {
                                    builder.append('0');
                                    value2 = (value2 ^ 0x43cf7c58f9214228L) + 0xdec07a7272e939bL;
                                    value3 = (value3 ^ 0x62cb46237a38cdabL) - 0x58cd1f1b6d40b0ebL;
                                    continue;
                                 }

                                 if (count16 == 9) {
                                    if (this.count5 >= 0) {
                                       value2 = (value2 ^ 0x4bf351eeace3d7dcL) + 0x68cfac40c34a09f0L;
                                       value3 = (value3 ^ -0x6d7df00cf840a1d8L) - 0x571c1067b2372709L;
                                    } else {
                                       value2 = (value2 ^ 0x19d51d8c7d83ff41L) - 0x35d5826e0d5c005eL;
                                       value3 = (value3 ^ 0x2230e293b6c9f78bL) - 0x7fa35825b3b3e313L;
                                    }
                                    continue;
                                 }
                              } else {
                                 value2 = (value2 ^ -0x55bc50dbb9875471L) + 0x460128e36660a516L;
                                 value3 = (value3 ^ 0x657b73bbffc53361L) - 0x21ed776dc5e19d0eL;
                              }

                              count15 = 0;
                              value2 = (value2 ^ -0x5f5f6d765624078aL) - 0x2699920e053b30a1L;
                              value3 = (value3 ^ -0x29adcb29ba36fb83L) + 0x73fa868337b55aa6L;
                              continue;
                           }

                           builder = new StringBuilder(20 + count14);
                           value2 = (value2 ^ -0x6f870d923fa30d0eL) - 0x6ac71f5f84a4b70eL;
                           value3 = (value3 ^ -0x6c705e181c5bf49eL) - 0x7c58e3c308fab90aL;
                           continue;
                        }

                        if (this.count5 != 0) {
                           value2 = (value2 ^ 0x2af5e041196a3070L) - 0x10610726c78d79ffL;
                           value3 = (value3 ^ 0x418f5f71a7b5ba8eL) - 0x65b4f024779556a3L;
                        } else {
                           value2 = (value2 ^ -0x52011523197a03b1L) + 0x26befc6a0d4cd200L;
                           value3 = (value3 ^ -0x478d89295afb9a9dL) + 0x6db984e5579d5ad2L;
                        }
                        continue;
                     }

                     if (this.getCountAsIndex() == 0) {
                        return "0";
                     }

                     value2 = (value2 ^ -0x30ba1c93b2b3db10L) + 0x324e709b9e92e81aL;
                     value3 = (value3 ^ 0x33c34c452124850cL) + 0x5342b8f517f30947L;
                     continue;
                  }

                  if (this.timestamp2 == Long.MIN_VALUE) {
                     long value4 = (value2 ^ 0x1f8c1a226560f2b2L) - 0x5469faa31a65a2eL;
                     long value5 = (value3 ^ 0x4135035b6ebd422eL) + 0x31c66e5e0fa88abaL;
                     text = this.signumComparable2.toString();
                     value2 = (value4 ^ 0x2af28e27c2c67863L) - 0x7429cd1fe1ed036L;
                     value3 = (value5 ^ -0x300f401d69c7782cL) + 0x34764a586689d0f5L;
                  } else {
                     value2 = (value2 ^ -0x534c62aff56c2129L) - 0x5447e337a4b176e2L;
                     value3 = (value3 ^ 0x41b085e180a70f90L) + 0x66ce5940bb664cdbL;
                  }
                  continue;
               }
            } else {
               count15++;
               value2 = (value2 ^ 0x28913a4e6ea65b87L) + 0x424c78f57027d6c4L;
               value3 = (value3 ^ 0x60dcc4d16382ccc2L) + 0x204b026d371b663bL;
            }

            if (count15 >= count14) {
               return builder.toString();
            }

            value2 = (value2 ^ 0x7daa846477ab17f6L) + 0x3ae6511deb4b8532L;
            value3 = (value3 ^ -0x5db8f3f6a7ad20cL) - 0xe73ee137db2639L;
         } else if (this.timestamp2 == Long.MIN_VALUE) {
            long value6 = (value2 ^ -0x51147969cf0665baL) + 0x7162fb995f28aa17L;
            long value7 = (value3 ^ 0x25785eaccf6e1e73L) - 0x6bf139634a170c21L;
            text2 = this.signumComparable2.getSignumComparable5().toString();
            value2 = (value6 ^ -0x3031f35acb9593e3L) + 0x5c7508d66cd01d74L;
            value3 = (value7 ^ -0x694df8d3e78ae5cL) + 0x6b9f072f64a8ded6L;
         } else {
            value2 = (value2 ^ -0x6176515fb1cc9275L) + 0x7d27b16440e61adeL;
            value3 = (value3 ^ 0xe3b9d34adbb887L) - 0x3a8abfb5c4c744e2L;
         }
      }

      int countAsIndex = this.getCountAsIndex();
      int count17 = this.count5;
      int count18 = countAsIndex;
      return this.resolveText(count18, text2, count17);
   }

   private static int computeCount(SignumComparable signumComparable3, int count14, SignumComparable signumComparable4, int count15) {
      int count16 = 0;
      count16 = count14 - count15;
      if (count16 >= 0) {
         SignumComparable signumComparable5 = resolveSignumComparable(signumComparable4, count16);
         return signumComparable3.computeCount(signumComparable5);
      } else {
         int count17 = -count16;
         return resolveSignumComparable(signumComparable3, count17).computeCount(signumComparable4);
      }
   }

   private static int computeCount(long value) {
      int count14 = 0;
      count14 = (int)value;
      return value != (long)count14 ? (value >= 0L ? Integer.MAX_VALUE : Integer.MIN_VALUE) : count14;
   }

   private static SplineParser resolveSplineParser(SplineParser splineParser4, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count14 = 0;
      boolean flag = false;
      SignumComparable signumComparable3 = null;
      long value = 0L;
      int count15 = 0;
      int count16 = 0;
      int count17 = 0;
      int count18 = 0;
      long value2 = 0x2cebc112c35aa976L;
      long value3 = 0x74824dee26b9f302L;

      SplineParser splineParser5;
      while (true) {
         int count19 = (int)(value2 % 30L);
         if (count19 == 24) {
            splineParser5 = new SplineParser(signumComparable3, value, count15, count16);
            break;
         }

         if (count19 == 20) {
            flag = false;
            value2 = (value2 ^ -0x22fa90d575f465fcL) + 0x89432ba2bd2f24bL;
            value3 = (value3 ^ -0x5b88395a9891b8f5L) - 0x7a445cececc11222L;
         } else if (count19 != 26) {
            if (count19 == 12) {
               count14 = fG7jSMNbfywSerializable.count2;
               value2 = (value2 ^ -0x3bc14cb8d32dad19L) + 0x3e5679801c2c165fL;
               value3 = (value3 ^ -0x230e884cf22eebbL) + 0x5d8a917e56ff9edcL;
            } else if (count19 != 22) {
               if (count19 == 21) {
                  flag = true;
                  value2 = (value2 ^ -0x5e660f043571689dL) + 0x51dc1f4402383f5fL;
                  value3 = (value3 ^ 0x7103d18cd6c3a709L) + 0x69ec2f504fa885d2L;
               } else {
                  long value4;
                  long value5;
                  checkBlock: {
                     guardBlock: {
                        long value6;
                        long value7;
                        searchBlock:
                        if (count19 != 18) {
                           if (count19 == 10) {
                              value = computeTimestamp(value, timestampArray2[count18], fG7jSMNbfywSerializable.combatScaleProperty.count);
                              value2 = (value2 ^ 0x5b9fcf66490de6daL) + 0x2e4156830c1d29b9L;
                              value3 = (value3 ^ 0x21c44b95677174e7L) + 0x4cdd689386a36257L;
                              continue;
                           }

                           if (count19 == 7) {
                              count17 = fG7jSMNbfywSerializable.combatScaleProperty.count;
                              value2 = (value2 ^ 0x607b4e1ac9fcb336L) + 0x2278100d76b31de4L;
                              value3 = (value3 ^ -0x5a7060e871f588acL) + 0x1e2ea8684817f859L;
                              continue;
                           }

                           if (count19 == 29) {
                              if (value == Long.MIN_VALUE) {
                                 long value8 = (value2 ^ 0x1b4a0bbe48d269a8L) + 0x3b86e2f9d9983707L;
                                 long value9 = (value3 ^ -0x5b23f2ee3fbd7155L) - 0x5dc06416222d6daL;
                                 count16 = computeCount(signumComparable3);
                                 value2 = (value8 ^ 0x7e50daad3175fe6eL) - 0x9bcc69faaec2a5L;
                                 value3 = (value9 ^ 0x2438898c1439b0efL) + 0x5c507eb6620bab89L;
                              } else {
                                 value2 = (value2 ^ -0x7a0207bd050f0bc2L) + 0x6df1cf114c4c6270L;
                                 value3 = (value3 ^ -0x6668f3559a01edbL) - 0x5d08e9d3d7fb343aL;
                              }
                              continue;
                           }

                           if (count19 == 14) {
                              signumComparable3 = resolveSignumComparable(signumComparable3, count18, count17);
                              value2 = (value2 ^ -0x7bc32c2c05bac8c3L) - 0x3b4446057caa82faL;
                              value3 = (value3 ^ -0x6cf74cb4322751ecL) - 0x442db9dc0db63f19L;
                              continue;
                           }

                           if (count19 != 25) {
                              if (count19 == 16) {
                                 value2 = (value2 ^ 0x309c5cdb5562ae87L) - 0xeae1a9806b35e05L;
                                 value3 = (value3 ^ -0x4d1d4473a43fa25dL) - 0x758f009502ecb362L;
                                 break guardBlock;
                              }

                              if (count19 == 5) {
                                 count16 = splineParser4.getCount();
                                 value2 = (value2 ^ 0x27681e6ed2ad1f78L) - 0xec0a5d4ea7e1604L;
                                 value3 = (value3 ^ -0x2661ae2d62c5b965L) - 0x14cdf3844928f6c0L;
                                 continue;
                              }

                              if (count19 == 17) {
                                 count18 = count16 - count14;
                                 value2 = (value2 ^ 0x1fa17cf435f4f65dL) + 0x2937da93e6201f41L;
                                 value3 = (value3 ^ 0x3590067ba89e3310L) - 0x13ce59f461394be4L;
                                 continue;
                              }

                              if (count19 == 8) {
                                 value = splineParser4.timestamp2;
                                 value2 = (value2 ^ 0x5fdf7e4dffdae8c3L) + 0x286ce0a5f365644dL;
                                 value3 = (value3 ^ -0x41775a350911a618L) - 0x7f6658d01a799257L;
                                 continue;
                              }

                              if (count19 == 1) {
                                 flag = true;
                                 value2 = (value2 ^ 0x7f187a4855ef3a52L) - 0x17fcaa0a3bf9436bL;
                                 value3 = (value3 ^ -0x1629b7ab2ed5d004L) + 0x12b2d5170eada6bbL;
                                 continue;
                              }

                              if (count19 == 13) {
                                 count18 = count16 - count14;
                                 value2 = (value2 ^ 0x1273e3697358570eL) + 0x780c61a440932e45L;
                                 value3 = (value3 ^ 0x30feced5e2cf3487L) + 0x50e19a3a8eb6c02dL;
                                 continue;
                              }

                              if (count19 == 11) {
                                 signumComparable3 = splineParser4.signumComparable2;
                                 value2 = (value2 ^ -0x67a6200a39e4bc12L) - 0x71dc75b1a89eea5dL;
                                 value3 = (value3 ^ -0x60575aea4f62ae25L) - 0x3b10775d6fb18329L;
                                 continue;
                              }

                              if (count19 == 4) {
                                 count15 = splineParser4.count5;
                                 value2 = (value2 ^ -0x5efcfe740b5acf87L) + 0x675a1d32956abfd6L;
                                 value3 = (value3 ^ 0x66ee54b3363c61f6L) + 0x675dc2056eaeddf6L;
                                 continue;
                              }

                              if (count19 == 2) {
                                 value = computeTimestamp(signumComparable3);
                                 value2 = (value2 ^ -0x231c65474d7c166aL) + 0x2e40fad3a5751733L;
                                 value3 = (value3 ^ -0x3f6e9fdaf10d3c17L) + 0x2f75888079d56415L;
                                 continue;
                              }

                              if (count19 == 15) {
                                 count15 = computeCountByTime((long)count15 - (long)count18);
                                 value2 = (value2 ^ -0xffec0500d4ce7d4L) + 0x2b229159faea933fL;
                                 value3 = (value3 ^ -0x5aaa1186224474a5L) + 0x56384bfbee2da9fdL;
                                 continue;
                              }

                              if (count19 == 0) {
                                 signumComparable3 = null;
                                 value2 = (value2 ^ 0x436db47c1e66bdf4L) + 0x2eec30732642851eL;
                                 value3 = (value3 ^ -0x444753648551f831L) - 0x2d1ac05b70b06be9L;
                                 continue;
                              }

                              if (count19 == 23) {
                                 count16 = computeCount4(value);
                                 value2 = (value2 ^ -0x77aeab8bf771e92cL) - 0x2910af66e820b18bL;
                                 value3 = (value3 ^ 0x103c5c1e47c51f1cL) - 0x1562ce526a71db22L;
                                 continue;
                              }

                              if (count19 == 19) {
                                 if (value == Long.MIN_VALUE) {
                                    value2 = (value2 ^ -0x3dc7b644275a0813L) + 0x3b67ae8de21895a3L;
                                    value3 = (value3 ^ -0x682707009339e80fL) + 0x3e3876c8545b3cdeL;
                                    continue;
                                 }

                                 value6 = (value2 ^ -0x51ab139dd86ddfc3L) - 0x1260411615a38186L;
                                 value7 = (value3 ^ -0x56026bc7c89a424dL) - 0x41e43e271dea52dcL;
                                 break searchBlock;
                              }

                              if (count19 != 28) {
                                 if (count19 != 6) {
                                    if (count19 != 27) {
                                       if (count19 != 9) {
                                          if (count14 <= 0) {
                                             return splineParser4;
                                          }

                                          value2 = (value2 ^ -0x557e7d4438d4bc5dL) - 0x36ec173e39232717L;
                                          value3 = (value3 ^ -0x7490cad6e9847362L) - 0x13ae9ef5b78ebe08L;
                                       } else {
                                          count15 = computeCountByTime((long)count15 - (long)count18);
                                          value2 = (value2 ^ -0x604b3e1b0b293e8cL) - 0x7e4ae371052341a1L;
                                          value3 = (value3 ^ 0x28669ecfdb3e28d8L) - 0x6da78b87062cbc4aL;
                                       }
                                    } else {
                                       count16 = computeCount4(value);
                                       value2 = (value2 ^ 0x18c8bff079198e3bL) + 0x2ca9f3a766578e98L;
                                       value3 = (value3 ^ 0x1e57df2a9bc26223L) + 0x4e5380fb1640178dL;
                                    }
                                    continue;
                                 }
                                 break guardBlock;
                              }

                              value2 = (value2 ^ -0x4267d7c46feb5294L) - 0x5e94c6023c8e67e7L;
                              value3 = (value3 ^ -0x1f80a738a97f9e1cL) + 0x6e0acd369b11be0L;
                           }

                           if (count18 > 0) {
                              value2 = (value2 ^ -0xd4d808c3fdbd30L) - 0x7d4566a307b2b4f6L;
                              value3 = (value3 ^ 0x53df5c4a09baa3cfL) - 0x4b1bde43c2dc6472L;
                              continue;
                           }

                           value6 = (value2 ^ 0x5b79bfe9eb14e809L) + 0x3bd345b3d3adfb1cL;
                           value7 = (value3 ^ 0x23aea9a1912456ceL) - 0x4750dc768454e546L;
                        } else {
                           value6 = (value2 ^ 0x76d01f9526894d8L) + 0xc5a5ce7337fdb34L;
                           value7 = (value3 ^ 0x5743dcc92723289eL) - 0x54f46e9f85001076L;
                        }

                        if (value != Long.MIN_VALUE) {
                           value2 = (value6 ^ -0x2af05993b70334f3L) - 0x63725475d163dc07L;
                           value3 = (value7 ^ 0x327fdc1bd5edca0aL) - 0x3af3f3dcc144ec50L;
                           continue;
                        }

                        value4 = (value6 ^ -0x536bf39158a176b5L) - 0x69fad226e359e8f9L;
                        value5 = (value7 ^ -0x3317d06d1494fbf3L) + 0x623efadde5a6a692L;
                        break checkBlock;
                     }

                     if (count18 > 0) {
                        value2 = (value2 ^ -0x276bfea85469ec06L) - 0x769edc8d5122da93L;
                        value3 = (value3 ^ -0x1634604a4b8d7363L) + 0x29c6c14a1789e0dcL;
                        continue;
                     }

                     value4 = (value2 ^ -0x756c6e1b365e8659L) - 0x5729d1a36a8a2d15L;
                     value5 = (value3 ^ -0x2502bc4dd6885be6L) - 0x5787850f95ff659L;
                  }

                  if (!flag) {
                     splineParser5 = splineParser4;
                     break;
                  }

                  value2 = (value4 ^ -0x7c274822f5da7a4cL) + 0x2c4b89605b79afa4L;
                  value3 = (value5 ^ 0x51a5785d5085637cL) - 0x4f39c93b5ce5bd19L;
               }
            } else {
               count18 = count16 - count14;
               value2 = (value2 ^ 0x1ff6223686dff898L) + 0x2b781f833871666L;
               value3 = (value3 ^ 0x7ff53972aaa7e489L) + 0x681887e666ea6524L;
            }
         } else {
            count18 = count16 - count14;
            value2 = (value2 ^ -0x3ffb769adeb1c713L) - 0x3e411d8f7aee9f53L;
            value3 = (value3 ^ -0x2e1e4218a120ce23L) - 0x1f4b7ccdd921ec05L;
         }
      }

      return splineParser5;
   }

   public static SplineParser resolveSplineParser(SignumComparable signumComparable3, int count14, int count15) {
      long value = 0L;
      value = computeTimestamp(signumComparable3);
      if (value != 0L) {
         return count14 == 0 && value >= 0L && value < (long)splineParserArray.length ? splineParserArray[(int)value] : new SplineParser(signumComparable3, value, count14, count15);
      } else {
         return resolveSplineParser3(count14);
      }
   }

   private static SplineParser resolveSplineParser(long value, long value2, int count14, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      long value3 = value;
      long value4 = value2;
      long value5 = 0L;
      byte byteValue = 0;
      long value6 = 0L;
      long value7 = 0L;
      long value8 = 0L;
      long value9 = 0L;
      long value10 = 0L;
      long value11 = 0L;
      long value12 = 0L;
      long value13 = 0L;
      long value14 = 0L;
      long value15 = 0L;
      SplineParser splineParser4 = null;
      value5 = computeTimestamp(value, value2);
      if (value5 == Long.MIN_VALUE) {
         byteValue = 1;
         if (value < 0L) {
            value3 = -value;
            byteValue = -1;
         }

         if (value2 < 0L) {
            value4 = -value2;
            byteValue *= -1;
         }

         value6 = value3 >>> 32;
         value7 = value3 & 4294967295L;
         value8 = value4 >>> 32;
         value9 = value4 & 4294967295L;
         value5 = value7 * value9;
         value10 = value5 & 4294967295L;
         value11 = value5 >>> 32;
         value5 = value6 * value9 + value11;
         value11 = value5 & 4294967295L;
         value12 = value5 >>> 32;
         value5 = value7 * value8 + value11;
         value11 = value5 & 4294967295L;
         value12 += value5 >>> 32;
         value13 = value12 >>> 32;
         value12 &= 4294967295L;
         value5 = value6 * value8 + value12;
         value12 = value5 & 4294967295L;
         value13 = (value5 >>> 32) + value13 & 4294967295L;
         value14 = computeTimestampByTime(value13, value12);
         value15 = computeTimestampByTime(value11, value10);
         splineParser4 = resolveSplineParser(value14, value15, byteValue, count14, fG7jSMNbfywSerializable);
         if (splineParser4 == null) {
            SignumComparable signumComparable3 = SignumComparable.resolveSignumComparableByTime(value3);
            long value16 = value4 * (long)byteValue;
            splineParser4 = new SplineParser(signumComparable3.resolveSignumComparable(value16), Long.MIN_VALUE, count14, 0);
            return resolveSplineParser(splineParser4, fG7jSMNbfywSerializable);
         } else {
            return splineParser4;
         }
      } else {
         return resolveSplineParser(value5, count14, fG7jSMNbfywSerializable);
      }
   }

   public static SplineParser resolveSplineParser(long value) {
      if (value >= 0L && value < (long)splineParserArray.length) {
         return splineParserArray[(int)value];
      } else {
         return value == Long.MIN_VALUE ? new SplineParser(signumComparable, value, 0, 0) : new SplineParser(null, value, 0, 0);
      }
   }

   private static boolean checkIndex(int count14, int count15, int count16, boolean flag) {
      long value = 0x2aa0f08b24eb6a94L;
      value = (value ^ 0x3593fe85d0573253L) + 0x32982cb6a27050bdL;
      if (count14 == 2) {
         value = (value ^ -0x14fb912022b99d9aL) - 0x4860759f97b9e78eL;
         boolean flag2;
         if (count15 <= 0) {
            flag2 = false;
         } else {
            value = (value ^ 0x3177366f6e740afdL) + 0x31e6a7f6e451fa04L;
            flag2 = true;
         }

         return flag2;
      } else {
         value = (value ^ -0x153ec0025f527468L) - 0x6eb961adbe6293f6L;
         if (count14 != 4) {
            value = (value ^ 0x25d4a67f4105bd6L) + 0x3ebd3edc3e5ca66L;
            if (count14 == 7) {
               throw new ArithmeticException("Rounding necessary");
            }

            value = (value ^ 0x7b8c3d7af9033e8cL) - 0x27bee7b12ac6c4b4L;
            if (count14 != 5) {
               value = (value ^ 0x434ee0f17003cba1L) - 0xf52eb8eac44070L;
               if (count14 == 1) {
                  return false;
               }

               value = (value ^ 0x586d0c5bd341067aL) - 0x5e901340556342deL;
               if (count14 == 3) {
                  return count15 < 0;
               }

               value = (value ^ -0x6f6ddd67eb931527L) + 0x256a2d2a903a10baL;
               if (count14 != 6) {
                  value = (value ^ 0x335463b995da1327L) - 0x5f1b4fe5b483f2cL;
                  if (count14 == 0) {
                     return true;
                  }

                  value = (value ^ 0x173b81fbe9ae268L) + 0x2e23dcf2017bc491L;
               }
            }
         }

         if (enabled || count14 >= 4 && count14 <= 6) {
            if (count16 < 0) {
               return false;
            } else if (count16 > 0) {
               return true;
            } else if (!enabled && count16 != 0) {
               throw new AssertionError();
            } else if (count14 == 5) {
               return false;
            } else if (count14 == 4) {
               return true;
            } else if (count14 == 6) {
               return flag;
            } else {
               throw new AssertionError("Unexpected rounding mode" + count14);
            }
         } else {
            throw new AssertionError("Unexpected rounding mode" + CombatScaleProperty.resolveCombatScaleProperty(count14));
         }
      }
   }

   private static SplineParser resolveSplineParser(long value, long value2, int count14, int count15, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count16 = 0;
      int count17 = 0;
      Object entry = null;
      count16 = fG7jSMNbfywSerializable.count2;
      entry = null;
      if ((count17 = computeCount5(value, value2) - count16) > 0 && count17 < timestampArray2.length) {
         int count18 = computeCountByTime((long)count15 - (long)count17);
         entry = resolveSplineParser(value, value2, timestampArray2[count17], count14, count18, fG7jSMNbfywSerializable.combatScaleProperty.count, count18);
         if (entry != null) {
            return resolveSplineParser((SplineParser)entry, fG7jSMNbfywSerializable);
         }
      }

      return null;
   }

   public SplineParser resolveSplineParserByFG7jSMNbfywSeria(FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      return this.getSplineParser().resolveSplineParser2(fG7jSMNbfywSerializable);
   }

   public SplineParser resolveSplineParser2(SplineParser splineParser4, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      short shortValue = 22156;
      int count14 = 13544600;
      byte byteValue = -90;
      int count15 = 0;
      long value = 0L;
      int count16 = 0;
      int count17 = 0;
      count15 = fG7jSMNbfywSerializable.count2;
      if (count15 != 0) {
         value = (long)this.count5 - (long)splineParser4.count5;
         if (splineParser4.getCountAsIndex() != 0) {
            if (this.getCountAsIndex() != 0) {
               count16 = this.getCount();
               count17 = splineParser4.getCount();
               if (this.timestamp2 == Long.MIN_VALUE) {
                  if (splineParser4.timestamp2 == Long.MIN_VALUE) {
                     SignumComparable signumComparable3 = splineParser4.signumComparable2;
                     return resolveSplineParser(this.signumComparable2, count16, signumComparable3, count17, value, fG7jSMNbfywSerializable);
                  } else {
                     long timestamp6 = splineParser4.timestamp2;
                     return resolveSplineParser(this.signumComparable2, count16, timestamp6, count17, value, fG7jSMNbfywSerializable);
                  }
               } else {
                  return splineParser4.timestamp2 == Long.MIN_VALUE
                     ? resolveSplineParser(this.timestamp2, count16, splineParser4.signumComparable2, count17, value, fG7jSMNbfywSerializable)
                     : resolveSplineParser(this.timestamp2, count16, splineParser4.timestamp2, count17, value, fG7jSMNbfywSerializable);
               }
            } else {
               return resolveSplineParser3(computeCount(value));
            }
         } else if (this.getCountAsIndex() != 0) {
            throw new ArithmeticException("Division by zero");
         } else {
            throw new ArithmeticException("Division undefined");
         }
      } else {
         return this.resolveSplineParser4(splineParser4);
      }
   }

   private static long computeTimestamp(long value, long value2, int count14) {
      long value3 = 0L;
      long value4 = 0L;
      value3 = value / value2;
      if (count14 != 1) {
         value4 = value % value2;
         int count15 = value < 0L != value2 < 0L ? -1 : 1;
         if (value4 == 0L) {
            return value3;
         } else {
            boolean flag = checkTime(value2, count14, count15, value3, value4);
            return !flag ? value3 : value3 + (long)count15;
         }
      } else {
         return value3;
      }
   }

   private static int computeCount(long value, int count14, SignumComparable signumComparable3, int count15) {
      int count16 = 0;
      if (value != 0L) {
         count16 = count14 - count15;
         return count16 < 0 && computeTimestamp(value, -count16) == Long.MIN_VALUE ? resolveSignumComparable(value, -count16).computeCount(signumComparable3) : -1;
      } else {
         return -1;
      }
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, int count14, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      SignumComparable signumComparable4 = signumComparable3;
      int count15 = count14;
      int count16 = 0;
      int count17 = 0;
      long value = 0L;
      int count18 = 0;
      int count19 = 0;
      long value2 = 0x51817d3566895f7cL;
      long value3 = -0x3bbea97bd0e6091aL;

      while (true) {
         int count20 = (int)(value2 % 25L);
         if (count20 == 2) {
            value = computeTimestamp(signumComparable4);
            value2 = (value2 ^ -0x1d4cc3d5b68b002aL) - 0x724d4146def3916dL;
            value3 = (value3 ^ -0x4ab513571d1c38faL) + 0x34d4627528496b82L;
         } else if (count20 != 11) {
            long value4;
            long value5;
            if (count20 == 3) {
               if (value == Long.MIN_VALUE) {
                  value2 = (value2 ^ -0x32294a18175f8606L) - 0x44410f9235204cbeL;
                  value3 = (value3 ^ -0x39304a60a05b3d4bL) - 0x4d63dc59e3cdbb95L;
                  continue;
               }

               value4 = (value2 ^ 0x2052a3fc2a26a449L) - 0x14be73cb1eb6cb12L;
               value5 = (value3 ^ -0x73540b884959e426L) + 0x36a831b9bcafc5f8L;
            } else {
               guardBlock: {
                  searchBlock:
                  if (count20 != 5) {
                     if (count20 == 1) {
                        count19 = count17 - count16;
                        value2 = (value2 ^ 0x3eb32d5a5a0dc342L) - 0x1f3f83592af37b40L;
                        value3 = (value3 ^ -0x59a813aa36f997fL) - 0x3b5588fb5153b311L;
                        continue;
                     }

                     if (count20 == 22) {
                        signumComparable4 = resolveSignumComparable(signumComparable4, count19, count18);
                        value2 = (value2 ^ 0x418d094357e36662L) + 0x2f5637d89fc126abL;
                        value3 = (value3 ^ 0x62ae3b27cbc3a102L) + 0x1566a946a7746150L;
                        continue;
                     }

                     if (count20 != 7) {
                        if (count20 == 10) {
                           return new SplineParser(signumComparable4, Long.MIN_VALUE, count15, count17);
                        }

                        if (count20 != 15) {
                           if (count20 == 14) {
                              count19 = count17 - count16;
                              value2 = (value2 ^ -0x1ddd0dd33ba6715dL) - 0x1df965edbb1a6317L;
                              value3 = (value3 ^ -0x297dd8e51a49cfb9L) - 0x784b5edf51022706L;
                              continue;
                           }

                           if (count20 == 0) {
                              count18 = fG7jSMNbfywSerializable.combatScaleProperty.count;
                              value2 = (value2 ^ -0x7447002ed88af9cdL) + 0x6a5ecd76c888a709L;
                              value3 = (value3 ^ 0x7bf7ff782d46e645L) + 0x680b1c1867736f73L;
                              continue;
                           }

                           if (count20 == 19) {
                              value4 = (value2 ^ -0x36861c64cd081810L) + 0x53dce703cd592330L;
                              value5 = (value3 ^ -0x12d1ab417bd52269L) - 0x1d972e87ab6a8ff5L;
                              break guardBlock;
                           }

                           if (count20 != 20) {
                              if (count20 == 23) {
                                 count17 = computeCount(signumComparable4);
                                 value2 = (value2 ^ -0x2b2c83e3e3bcd3d3L) - 0x3e6b26922cb395a1L;
                                 value3 = (value3 ^ 0xcf33ade5c88aae4L) - 0x717ddf96af33dfa1L;
                                 continue;
                              }

                              if (count20 == 8) {
                                 count15 = computeCountByTime((long)count15 - (long)count19);
                                 value2 = (value2 ^ -0x5952ac88566806c6L) - 0x6da333109c9ea60bL;
                                 value3 = (value3 ^ -0x1d41f736312cdd3aL) - 0x2ccfedf867972ef9L;
                                 continue;
                              }

                              if (count20 == 17) {
                                 count19 = count17 - count16;
                                 value2 = (value2 ^ 0x7efe82bf896d76b1L) + 0x3c543c756418fe70L;
                                 value3 = (value3 ^ -0x6d3a056c2f954e29L) - 0x795df649a4f62733L;
                                 continue;
                              }

                              if (count20 == 6) {
                                 value = computeTimestamp(signumComparable4);
                                 value2 = (value2 ^ -0x6e39533ade9d37c9L) + 0x2d748ec674807270L;
                                 value3 = (value3 ^ -0x453b61be8b5606fL) + 0x74fe78cef27a93L;
                                 continue;
                              }

                              if (count20 == 16) {
                                 count17 = 0;
                                 value2 = (value2 ^ 0x4f72ce701a97fcc6L) - 0x1d5de735f3ce70f3L;
                                 value3 = (value3 ^ 0x3bf56b4b8ea75afdL) - 0x2d554333dc152ca8L;
                                 continue;
                              }

                              if (count20 == 24) {
                                 if (value == Long.MIN_VALUE) {
                                    long value6 = (value2 ^ 0x57acaea1b64fa9d9L) - 0x3bafbe695f373f61L;
                                    long value7 = (value3 ^ 0x140d200716c2aa60L) + 0x413460186b56d3ebL;
                                    count17 = computeCount(signumComparable4);
                                    value2 = (value6 ^ 0x7a65a6defd9e08d9L) - 0x693e339764d4cbbbL;
                                    value3 = (value7 ^ -0x32688f1182f79621L) + 0x17e860e0fa3b9de5L;
                                 } else {
                                    value2 = (value2 ^ 0x4e4787f1bb7a80ddL) - 0x531a971954626665L;
                                    value3 = (value3 ^ -0x22826c5e4fe922f1L) + 0x863ac76d182ab18L;
                                 }
                                 continue;
                              }

                              if (count20 == 21) {
                                 value = computeTimestamp(value, timestampArray2[count19], fG7jSMNbfywSerializable.combatScaleProperty.count);
                                 value2 = (value2 ^ 0x4e3c43347bde5ebdL) - 0x3e1fdb74e985b1a5L;
                                 value3 = (value3 ^ 0x24da92875914c171L) + 0x64ad7ff11e45161fL;
                                 continue;
                              }

                              if (count20 != 4) {
                                 if (count20 != 13) {
                                    if (count20 != 12) {
                                       if (count20 != 18) {
                                          count17 = computeCount4(value);
                                          value2 = (value2 ^ 0x1db82a38ee8983d7L) - 0x2e3fcb7ee4a428eeL;
                                          value3 = (value3 ^ 0x3e16cddb27190becL) - 0x300f50e619da615eL;
                                       } else {
                                          count17 = computeCount4(value);
                                          value2 = (value2 ^ 0x594380e86d51d856L) - 0x33825fc1169f8f48L;
                                          value3 = (value3 ^ -0x47d0c728d6ea5d93L) - 0x561b2d219a4d788fL;
                                       }
                                    } else {
                                       count15 = computeCountByTime((long)count15 - (long)count19);
                                       value2 = (value2 ^ -0x37fedad6b8a72708L) + 0x6c2ef8e51bcf8f49L;
                                       value3 = (value3 ^ -0x684d3d8f11252795L) - 0x72fcd6e2c8638fc5L;
                                    }
                                 } else {
                                    count19 = count17 - count16;
                                    value2 = (value2 ^ 0x493b9b5ab55725bL) + 0x26f9e6ade421cee5L;
                                    value3 = (value3 ^ -0x64d5d422818c8f60L) - 0x1fbfa9497ced7353L;
                                 }
                                 continue;
                              }
                              break searchBlock;
                           }
                        } else {
                           value2 = (value2 ^ 0x23f9a662e633ccb0L) + 0x39ea01da7ea98d37L;
                           value3 = (value3 ^ -0x1aafd3dc13533218L) + 0x3da98a27e0bfb504L;
                        }

                        if (count19 > 0) {
                           value2 = (value2 ^ -0x78ddd3f45d1eb81fL) + 0x5aabcc74e1e61417L;
                           value3 = (value3 ^ -0x17030e1fcfe0039fL) - 0x6fff3ce783e82a9eL;
                           continue;
                        }

                        value4 = (value2 ^ 0x62a051efe356a9d8L) + 0x13c936f7c564611L;
                        value5 = (value3 ^ -0x548b2e0cf3fcf68L) + 0x58598c9b2323764cL;
                        break guardBlock;
                     }

                     if (count16 <= 0) {
                        value2 = (value2 ^ 0x5c71e2eb0f686613L) - 0xcb27b4631e57b4fL;
                        value3 = (value3 ^ -0x166872a3d0c5920cL) - 0x67a496b33672c38L;
                     } else {
                        value2 = (value2 ^ -0x11a6ee6b677f0581L) - 0x45e3f4b21ddc6524L;
                        value3 = (value3 ^ 0x8af9da33f4a9d05L) + 0x68490731899754a0L;
                     }
                     continue;
                  } else {
                     value2 = (value2 ^ 0x72bfd110221d516fL) + 0x6046a0177e948ca9L;
                     value3 = (value3 ^ 0x3449bb43193cfbbcL) + 0x5bf5ec7fdde66245L;
                  }

                  if (count19 <= 0) {
                     return resolveSplineParser(value, count15, count17);
                  }

                  value2 = (value2 ^ 0x4da0aa70f08609e7L) + 0x10d8dd1114be0b93L;
                  value3 = (value3 ^ -0x2bb432c4f63b84b4L) + 0x4c34d48519014a96L;
                  continue;
               }
            }

            if (value == Long.MIN_VALUE) {
               value2 = (value4 ^ -0x69a15acc377d4229L) - 0x759011b081587fbaL;
               value3 = (value5 ^ -0x5fece1ea2bde60e4L) - 0x49c78202a6d77001L;
            } else {
               value2 = (value4 ^ -0x53c894379028740cL) - 0x477f9a046aa265eeL;
               value3 = (value5 ^ -0x23ddcd6a40b4009bL) + 0x4a90dd66aaa0b815L;
            }
         } else {
            count16 = fG7jSMNbfywSerializable.count2;
            value2 = (value2 ^ -0x2be15e269e8cbe8cL) - 0x594584528d9fce0eL;
            value3 = (value3 ^ -0x2134d083bd2a1341L) + 0x17b9a01c6992f5a4L;
         }
      }
   }

   private static SignumComparable resolveSignumComparable(SignumComparable signumComparable3, int count14) {
      if (count14 > 0) {
         if (count14 >= timestampArray2.length) {
            SignumComparable signumComparable4 = resolveSignumComparableByIndex(count14);
            return signumComparable3.resolveSignumComparable4(signumComparable4);
         } else {
            long value = timestampArray2[count14];
            return signumComparable3.resolveSignumComparable(value);
         }
      } else {
         return signumComparable3;
      }
   }

   public short getShortCode() {
      long value = 0L;
      value = this.getTimestamp();
      if ((long)((short)((int)value)) == value) {
         return (short)((int)value);
      } else {
         throw new ArithmeticException("Overflow");
      }
   }

   public static SplineParser resolveSplineParser(long value, int count14, int count15) {
      if (count14 == 0 && value >= 0L && value < (long)splineParserArray.length) {
         return splineParserArray[(int)value];
      } else {
         return value != 0L ? new SplineParser(value != Long.MIN_VALUE ? null : signumComparable, value, count14, count15) : resolveSplineParser3(count14);
      }
   }

   private static int computeCount(SignumComparable signumComparable3) {
      int count14 = 0;
      long value = -0x691d25b7d69fd74bL;
      if (signumComparable3.count != 0) {
         value = (value ^ -0x7f8c8378e5fe65ddL) - 0x3cfc3dccd8ce06faL;
         count14 = (int)(((long)signumComparable3.getCount3() + (value % 740927L ^ -230132L)) * 646456993L >>> 31);
         value = (value ^ 0xa593d5c6da77a0dL) + 0x3dc7de6fe62fd24L;
         SignumComparable signumComparable4 = resolveSignumComparableByIndex(count14);
         int count15;
         if (signumComparable3.computeCount(signumComparable4) >= 0) {
            count15 = count14 + 1;
         } else {
            value = (value ^ -0x6bf94bf13b2a07c9L) + 0x3945da2df39f6bf8L;
            count15 = count14;
         }

         return count15;
      } else {
         return 1;
      }
   }

   public String getTextAsText() {
      return this.resolveText(false);
   }

   public static int computeCount4(long value) {
      long value2 = value;
      int count14 = 0;
      long[] entry = null;
      if (!enabled && value == Long.MIN_VALUE) {
         throw new AssertionError();
      } else {
         if (value < 0L) {
            value2 = -value;
         }

         if (value2 < 10L) {
            return 1;
         } else {
            count14 = (64 - Long.numberOfLeadingZeros(value2) + 1) * 1233 >>> 12;
            entry = timestampArray2;
            return count14 < entry.length && value2 >= ((long[])entry)[count14] ? count14 + 1 : count14;
         }
      }
   }

   private static int computeCount3(long value, long value2) {
      long value3 = value;
      long value4 = value2;
      if (value < 0L) {
         value3 = -value;
      }

      if (value2 < 0L) {
         value4 = -value2;
      }

      return value3 >= value4 ? (value3 != value4 ? 1 : 0) : -1;
   }

   public static SignumComparable resolveSignumComparable(SplineParser splineParser4) {
      return splineParser4.getSignumComparable();
   }

   public SplineParser resolveSplineParser2(int count14) {
      int count15 = 0;
      Object entry = null;
      long value = (long)this.count5 - (long)count14;
      count15 = this.computeCount2(value);
      entry = new SplineParser(this.signumComparable2, this.timestamp2, count15, 0);
      SplineParser splineParser4;
      if (((SplineParser)entry).count5 >= 0) {
         splineParser4 = (SplineParser)entry;
      } else {
         byte byteValue = 7;
         byte byteValue2 = 0;
         splineParser4 = ((SplineParser)entry).resolveSplineParser(byteValue2, byteValue);
      }

      return splineParser4;
   }

   private static SplineParser resolveSplineParser(long value, int count14, long value2, int count15) {
      long value3 = 0L;
      int count16 = 0;
      long value4 = 0L;
      Object entry = null;
      value3 = (long)count14 - (long)count15;
      if (value3 != 0L) {
         if (value3 >= 0L) {
            int count17 = computeCount(value2, value3);
            long value5 = computeTimestamp(value2, count17);
            if (value5 == Long.MIN_VALUE) {
               SignumComparable signumComparable3 = resolveSignumComparable(value2, count17).resolveSignumComparable2(value);
               SplineParser splineParser4;
               if ((value ^ value2) < 0L) {
                  byte byteValue = 0;
                  splineParser4 = resolveSplineParser(signumComparable3, count14, byteValue);
               } else {
                  splineParser4 = new SplineParser(signumComparable3, Long.MIN_VALUE, count14, 0);
               }

               return splineParser4;
            } else {
               return resolveSplineParser(value, value5, count14);
            }
         } else {
            count16 = computeCount(value, -value3);
            value4 = computeTimestamp(value, count16);
            if (value4 == Long.MIN_VALUE) {
               entry = resolveSignumComparable(value, count16).resolveSignumComparable2(value2);
               SplineParser splineParser5;
               if ((value ^ value2) < 0L) {
                  byte byteValue2 = 0;
                  splineParser5 = resolveSplineParser((SignumComparable)entry, count15, byteValue2);
               } else {
                  splineParser5 = new SplineParser((SignumComparable)entry, Long.MIN_VALUE, count15, 0);
               }

               return splineParser5;
            } else {
               return resolveSplineParser(value4, value2, count15);
            }
         }
      } else {
         return resolveSplineParser(value, value2, count14);
      }
   }

   public SplineParser resolveSplineParser3(SplineParser splineParser4) {
      short shortValue = 31804;
      short shortValue2 = 30468;
      char character = '\ubcef';
      if (this.timestamp2 == Long.MIN_VALUE) {
         if (splineParser4.timestamp2 == Long.MIN_VALUE) {
            SignumComparable signumComparable3 = this.signumComparable2;
            int count14 = this.count5;
            SignumComparable signumComparable4 = splineParser4.signumComparable2.getSignumComparable2();
            int count15 = splineParser4.count5;
            SignumComparable signumComparable5 = signumComparable4;
            int count16 = count14;
            return resolveSplineParser(signumComparable3, count16, signumComparable5, count15);
         } else {
            long value = -splineParser4.timestamp2;
            int count17 = this.count5;
            return resolveSplineParser(value, splineParser4.count5, this.signumComparable2, count17);
         }
      } else if (splineParser4.timestamp2 == Long.MIN_VALUE) {
         long timestamp6 = this.timestamp2;
         int count18 = this.count5;
         SignumComparable signumComparable6 = splineParser4.signumComparable2.getSignumComparable2();
         int count19 = splineParser4.count5;
         return resolveSplineParser(timestamp6, count18, signumComparable6, count19);
      } else {
         return resolveSplineParser(this.timestamp2, this.count5, -splineParser4.timestamp2, splineParser4.count5);
      }
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, SignumComparable signumComparable4, int count14, int count15, int count16) {
      boolean flag = false;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      long value = -0x527a454d4e72faadL;
      entry = new CurveAccumulator(signumComparable3.countArray);
      value = (value ^ -0x5a45135c6412b032L) - 0x6c31b7cee23f497cL;
      entry2 = new CurveAccumulator();
      value = (value ^ -0x4e32179958d80f9bL) + 0x3826e041b29e09f2L;
      entry3 = new CurveAccumulator(signumComparable4.countArray);
      value = (value ^ -0x468c8fb8a29de2beL) + 0x6295f1bcca6cf87aL;
      entry4 = ((CurveAccumulator)entry).resolveCurveAccumulator4((CurveAccumulator)entry3, (CurveAccumulator)entry2);
      value = (value ^ 0xd66abc0f3ae72bcL) - 0x52b8607bf947a8c9L;
      flag = ((CurveAccumulator)entry4).isActive2();
      value = (value ^ 0x3a6521855eddad61L) - 0x1edf503ec1feb020L;
      byte byteValue;
      if (signumComparable3.count == signumComparable4.count) {
         byteValue = 1;
      } else {
         value = (value ^ -0x2ce6b7e8c9565fcdL) + 0x5bff389045ad3044L;
         byteValue = -1;
      }

      byte byteValue2 = byteValue;
      if (flag) {
         if (count16 == count14) {
            return ((CurveAccumulator)entry2).resolveSplineParser(byteValue2, count14);
         } else {
            long value2 = ((CurveAccumulator)entry2).computeTimestampByIndex(byteValue2);
            if (value2 == Long.MIN_VALUE) {
               SignumComparable signumComparable5 = ((CurveAccumulator)entry2).resolveSignumComparableByIndex(byteValue2);
               long value3 = (long)count16;
               return resolveSplineParser(signumComparable5, count14, value3);
            } else {
               return resolveSplineParser(value2, count14, (long)count16);
            }
         }
      } else {
         if (matchesCurveAccumulator((CurveAccumulator)entry3, count15, byteValue2, (CurveAccumulator)entry2, (CurveAccumulator)entry4)) {
            CurveAccumulator curveAccumulator = CurveAccumulator.curveAccumulator;
            ((CurveAccumulator)entry2).applyCurveAccumulator(curveAccumulator);
         }

         return ((CurveAccumulator)entry2).resolveSplineParser(byteValue2, count14);
      }
   }

   private static long computeTimestamp(long value, long value2, long value3, long value4, long value5) {
      long value6 = value2 - value5 * value4;
      return computeTimestampByTime(value + (value6 >>> 32) - value5 * value3, value6 & 4294967295L);
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, long value, int count14, int count15) {
      if (value == Long.MIN_VALUE) {
         SignumComparable signumComparable4 = signumComparable3 != null ? signumComparable3 : signumComparable;
         long value2 = (long)count15;
         return resolveSplineParser(signumComparable4, count14, value2);
      } else {
         return resolveSplineParser(value, count14, (long)count15);
      }
   }

   public SplineParser getSplineParser4() {
      return this;
   }

   private String resolveText(int count14, String text, int count15) {
      int count16 = 0;
      long value = 0x27f53ef4f3b09ed7L;
      long value2 = -0x6a099174a9aff785L;
      count16 = text.length() - count15;
      value = (value ^ -0x1f3b71c9698e14edL) + 0x6f4fd84bc9743a3eL;
      value2 = (value2 ^ 0x25c5d6e27eb945f1L) - 0x669e99363d52123bL;
      if (count16 == 0) {
         value = (value ^ 0x7177617911014c2fL) + 0xfb05a511cdcb83L;
         value2 = (value2 ^ 0x29d7cab30fdd862L) - 0x6713c109de09ab31L;
         return (count14 >= 0 ? "0." : "-0.") + text;
      } else {
         StringBuilder builder;
         if (count16 <= 0) {
            builder = new StringBuilder(3 - count16 + text.length());
            builder.append(count14 >= 0 ? "0." : "-0.");

            for (int index = 0; index < -count16; index++) {
               builder.append('0');
            }

            builder.append(text);
         } else {
            builder = new StringBuilder(text);
            builder.insert(count16, '.');
            if (count14 < 0) {
               builder.insert(0, '-');
            }
         }

         return builder.toString();
      }
   }

   public SplineParser resolveSplineParser6(SplineParser splineParser4) {
      if (this.timestamp2 == Long.MIN_VALUE) {
         if (splineParser4.timestamp2 == Long.MIN_VALUE) {
            int count14 = splineParser4.count5;
            SignumComparable signumComparable3 = splineParser4.signumComparable2;
            int count15 = this.count5;
            return resolveSplineParser(this.signumComparable2, count15, signumComparable3, count14);
         } else {
            int count16 = this.count5;
            return resolveSplineParser(splineParser4.timestamp2, splineParser4.count5, this.signumComparable2, count16);
         }
      } else if (splineParser4.timestamp2 == Long.MIN_VALUE) {
         int count17 = splineParser4.count5;
         return resolveSplineParser(this.timestamp2, this.count5, splineParser4.signumComparable2, count17);
      } else {
         return resolveSplineParser(this.timestamp2, this.count5, splineParser4.timestamp2, splineParser4.count5);
      }
   }

   public SplineParser resolveSplineParser5(SplineParser splineParser4, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      SplineParser splineParser5 = splineParser4;
      byte byteValue = 0;
      int count14 = 759584595;
      short shortValue = 30050;
      SplineParser splineParser6 = null;
      long value = 0x2481be4b52851f27L;
      long value2 = -0x412064b9013964eaL;
      if (fG7jSMNbfywSerializable.count2 != 0) {
         value = (value ^ -0x513a92e511cad617L) - 0xb710159fe647fffL;
         value2 = (value2 ^ 0x3968d79f550f004cL) + 0x37077a344731f5baL;
         splineParser6 = this;
         value = (value ^ 0x4f9d3aec97801a17L) + 0x31c0fb2916da8a10L;
         value2 = (value2 ^ -0x3891c8e88271554cL) - 0x210bf37eb41209L;
         long value3;
         boolean flag;
         if (this.getCountAsIndex() != 0) {
            value3 = (value2 ^ -0x129b41d8e3f880dbL) + 0x79e3e5c286c4551bL;
            flag = false;
         } else {
            value = (value ^ 0x24b864bf65feffd6L) - 0x28ca8f4c77ab157aL;
            value2 = (value2 ^ -0x68aa7c1452458e30L) + 0x1fb4d7f5d60f5386L;
            flag = true;
            value3 = (value2 ^ -0x210045e4a67cd61dL) + 0x3e5eb9e8c981299fL;
         }

         boolean flag2 = flag;
         long value4;
         if (splineParser4.getCountAsIndex() != 0) {
            value4 = (value3 ^ 0x5eb6e12fc468b8cL) + 0x36aedf223be8b8cL;
            flag = false;
         } else {
            flag = true;
            value4 = (value3 ^ -0x2d26af506e51127dL) + 0x3238d0559166657fL;
         }

         boolean flag3 = flag;
         if (flag2) {
            value4 = (value4 ^ 0x5ded9f2d08fad08aL) - 0x4493612d07e5cf7aL;
         } else if (!flag3) {
            long value5 = (long)this.count5 - (long)splineParser4.count5;
            if (value5 != 0L) {
               SplineParser[] splineParserArray3 = this.resolveSplineParserArray(this, splineParser4, value5, fG7jSMNbfywSerializable);
               applySplineParserArray(splineParserArray3);
               splineParser6 = splineParserArray3[0];
               splineParser5 = splineParserArray3[1];
            }

            SignumComparable signumComparable3 = splineParser6.getSignumComparable();
            SignumComparable signumComparable4 = splineParser5.getSignumComparable();
            SignumComparable signumComparable5 = signumComparable3.resolveSignumComparable5(signumComparable4);
            int count15 = splineParser6.count5;
            return resolveSplineParser(signumComparable5, count15, fG7jSMNbfywSerializable);
         }

         int count16 = Math.max(this.getCount5(), splineParser4.getCount5());
         if (flag2 && flag3) {
            return resolveSplineParser3(count16);
         } else {
            SplineParser splineParser7 = !flag2 ? resolveSplineParser(this, fG7jSMNbfywSerializable) : resolveSplineParser(splineParser4, fG7jSMNbfywSerializable);
            if (splineParser7.getCount5() != count16) {
               if (splineParser7.getCount5() <= count16) {
                  int count17 = fG7jSMNbfywSerializable.count2 - splineParser7.getCount();
                  int count18 = count16 - splineParser7.getCount5();
                  if (count17 < count18) {
                     int count19 = splineParser7.getCount5() + count17;
                     return splineParser7.resolveSplineParser5(count19);
                  } else {
                     return splineParser7.resolveSplineParser5(count16);
                  }
               } else {
                  int count20 = splineParser7.count5;
                  long timestamp6 = splineParser7.timestamp2;
                  return resolveSplineParser(splineParser7.signumComparable2, timestamp6, count20, count16);
               }
            } else {
               return splineParser7;
            }
         }
      } else {
         value = (value ^ 0x3a22773c2f9094afL) + 0x2fd7002c42c903bfL;
         value2 = (value2 ^ -0x39a7fc305e5b82acL) - 0x3ed6994bfe86c4f6L;
         return this.resolveSplineParser6(splineParser4);
      }
   }

   private static long computeTimestamp(SignumComparable signumComparable3) {
      int[] entry = null;
      int count14 = 0;
      int count15 = 0;
      entry = signumComparable3.countArray;
      count14 = entry.length;
      if (count14 == 0) {
         return 0L;
      } else {
         count15 = (int)entry[0];
         if (count14 <= 2 && (count14 != 2 || count15 >= 0)) {
            long value = count14 != 2 ? (long)count15 & 4294967295L : ((long)entry[1] & 4294967295L) + ((long)count15 << 32);
            return signumComparable3.count >= 0 ? value : -value;
         } else {
            return Long.MIN_VALUE;
         }
      }
   }

   private SignumComparable resolveSignumComparable(int count14) {
      if (count14 > 0) {
         if (this.timestamp2 == Long.MIN_VALUE) {
            SignumComparable signumComparable3 = this.signumComparable2;
            SignumComparable signumComparable4 = resolveSignumComparableByIndex(count14);
            return signumComparable3.resolveSignumComparable4(signumComparable4);
         } else {
            SignumComparable signumComparable5 = resolveSignumComparableByIndex(count14);
            long timestamp6 = this.timestamp2;
            return signumComparable5.resolveSignumComparable(timestamp6);
         }
      } else {
         return this.getSignumComparable();
      }
   }

   private static long computeTimestamp2(long value, long value2) {
      long value3 = 0L;
      value3 = value + value2;
      return ((value3 ^ value) & (value3 ^ value2)) < 0L ? Long.MIN_VALUE : value3;
   }

   public SplineParser[] resolveSplineParserArray(SplineParser splineParser4, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      short shortValue = 4782;
      char character = '\ufeef';
      char character2 = '\ubafb';
      SplineParser[] splineParser5 = null;
      if (fG7jSMNbfywSerializable.count2 != 0) {
         splineParser5 = new SplineParser[]{this.resolveSplineParser3(splineParser4, fG7jSMNbfywSerializable), null};
         SplineParser splineParser6 = splineParser5[0].resolveSplineParser7(splineParser4);
         splineParser5[1] = this.resolveSplineParser3(splineParser6);
         return splineParser5;
      } else {
         return this.resolveSplineParserArray(splineParser4);
      }
   }

   public static SplineParser resolveSplineParserByTime(long value, int count14) {
      if (count14 != 0) {
         return value != 0L ? new SplineParser(value != Long.MIN_VALUE ? null : signumComparable, value, count14, 0) : resolveSplineParser3(count14);
      } else {
         return resolveSplineParser(value);
      }
   }

   private static SplineParser resolveSplineParserByFG7jSMNbfywSeria(long value, int count14, long value2, int count15, long value3, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count16 = 0;
      int count17 = 0;
      int count18 = 0;
      long value5 = 0x275998ef6becf2bdL;
      long value6 = 0x29a9d27ae90ea9d6L;
      count16 = fG7jSMNbfywSerializable.count2;
      value5 = (value5 ^ -0x4700fa9eca8dbbf9L) + 0x62f5a720eb5f9604L;
      value6 = (value6 ^ -0x39468e3a71e57642L) - 0x535090dea4dcdce9L;
      count17 = fG7jSMNbfywSerializable.combatScaleProperty.count;
      value5 = (value5 ^ 0x7324ade7b46cee15L) - 0x23920fd6c8d9cc07L;
      value6 = (value6 ^ -0x57893a7cdb9f6121L) - 0x5e9835e0b2d0cbddL;
      if (enabled) {
         value5 = (value5 ^ 0x61d7bed684fe69c9L) - 0x2269233afab78454L;
         value6 = (value6 ^ -0x3f60a77676e8b774L) + 0x61936b8e254733ebL;
      } else {
         value5 = (value5 ^ -0x27a0f2b6b4e48d5fL) - 0x38b1cd6600f9eb6dL;
         value6 = (value6 ^ 0x6e0c26e1f6bbdb8fL) - 0x5766df8d7291b597L;
         if (count14 > count15) {
            throw new AssertionError();
         }

         value5 = (value5 ^ -0x4023e301685ccddfL) + 0x7d0303ff78a6d8d1L;
         value6 = (value6 ^ -0x40edcad73604198L) - 0x2b53e280193e440fL;
         if (count15 >= 18) {
            throw new AssertionError();
         }

         value5 = (value5 ^ 0x6732f8056d7091c9L) + 0x139b9c69980fef3cL;
         value6 = (value6 ^ -0x704706b802f4e983L) + 0xd3f6bba1fa621e5L;
         if (count16 >= 18) {
            throw new AssertionError();
         }

         value5 = (value5 ^ -0x1a350ff98166aacaL) + 0x5f7cd1d7db4686d0L;
         value6 = (value6 ^ -0x2868a0e1cbf24283L) + 0x3eaacab472295d55L;
      }

      count18 = count15 - count14;
      value5 = (value5 ^ -0x2155be509eeab46dL) + 0x40bada4f32eba9b7L;
      value6 = (value6 ^ 0x37e2ad2ebe958dbaL) + 0x33529133df4a6b50L;
      long value7;
      long value8;
      long value9;
      if (count18 != 0) {
         value7 = (value5 ^ -0x183a52ce7918548fL) + 0x64068b5fe61af0c3L;
         value8 = (value6 ^ -0x7ee5676b46c7edceL) - 0x2e56a1fa7eedb588L;
         value9 = computeTimestamp(value, count18);
      } else {
         value5 = (value5 ^ -0x513eb0dc487ab821L) - 0x64fdd6ae4886bbabL;
         value6 = (value6 ^ -0x4b41e1ab401b390fL) + 0x605dbc57b5e1739L;
         value9 = value;
         value7 = (value5 ^ 0x30d75fdb48fb9f4aL) - 0x10a8aed4c80a9cc6L;
         value8 = (value6 ^ 0x5e9e050da88b0351L) + 0x2e95fafaa878fd4fL;
      }

      long value10 = value9;
      int count19 = computeCount3(value10, value2);
      SplineParser splineParser4;
      if (count19 <= 0) {
         int count20 = computeCountByTime(value3 + (long)count15 - (long)count14 + (long)count16);
         if (count19 != 0) {
            long value11;
            if ((value11 = computeTimestamp(value10, count16)) != Long.MIN_VALUE) {
               splineParser4 = resolveSplineParser(value11, value2, count20, count17, computeCountByTime(value3));
            } else {
               splineParser4 = null;
               if (count16 < timestampArray2.length) {
                  splineParser4 = resolveSplineParser(timestampArray2[count16], value10, value2, count20, count17, computeCountByTime(value3));
               }

               if (splineParser4 == null) {
                  SignumComparable signumComparable3 = resolveSignumComparable(value10, count16);
                  int count21 = computeCountByTime(value3);
                  splineParser4 = resolveSplineParser(signumComparable3, value2, count20, count17, count21);
               }
            }
         } else {
            int count22 = value10 < 0L != value2 < 0L ? -1 : 1;
            int count23 = computeCountByTime(value3);
            int count24 = count22;
            splineParser4 = resolveSplineParser(count24, count16, count20, count23);
         }
      } else {
         int count25 = count15 - 1;
         int count26 = computeCountByTime(value3 + (long)count25 - (long)count14 + (long)count16);
         if (computeCountByTime((long)count16 + (long)count25 - (long)count14) <= 0) {
            int count27 = computeCountByTime((long)count14 - (long)count16);
            if (count27 != count25) {
               int count28 = computeCountByTime((long)count27 - (long)count25);
               long value12;
               if ((value12 = computeTimestamp(value2, count28)) != Long.MIN_VALUE) {
                  splineParser4 = resolveSplineParser(value, value12, count26, count17, computeCountByTime(value3));
               } else {
                  SignumComparable signumComparable4 = resolveSignumComparable(value2, count28);
                  SignumComparable signumComparable5 = SignumComparable.resolveSignumComparableByTime(value);
                  int count29 = computeCountByTime(value3);
                  splineParser4 = resolveSplineParser(signumComparable5, signumComparable4, count26, count17, count29);
               }
            } else {
               splineParser4 = resolveSplineParser(value, value2, count26, count17, computeCountByTime(value3));
            }
         } else {
            int count30 = computeCountByTime((long)count16 + (long)count25 - (long)count14);
            long value13;
            if ((value13 = computeTimestamp(value, count30)) != Long.MIN_VALUE) {
               splineParser4 = resolveSplineParser(value13, value2, count26, count17, computeCountByTime(value3));
            } else {
               splineParser4 = null;
               if (count16 - 1 < 0) {
                  value7 = (value7 ^ -0x1568cdc706ee2a43L) - 0x5a69123836f0d25bL;
                  value8 = (value8 ^ -0x2707edd5b22102dL) - 0x747b81226354672fL;
               } else if (count16 - 1 >= timestampArray2.length) {
                  value7 = (value7 ^ 0x44275f5362135152L) + 0x3be750b3620cb152L;
                  value8 = (value8 ^ -0x5c8963690ac09935L) + 0x616d6471eacd21c9L;
               } else {
                  splineParser4 = resolveSplineParser(timestampArray2[count16 - 1], value10, value2, count26, count17, computeCountByTime(value3));
               }

               if (splineParser4 == null) {
                  SignumComparable signumComparable6 = resolveSignumComparable(value10, count16 - 1);
                  int count31 = computeCountByTime(value3);
                  splineParser4 = resolveSplineParser(signumComparable6, value2, count26, count17, count31);
               }
            }
         }
      }

      return resolveSplineParser(splineParser4, fG7jSMNbfywSerializable);
   }

   private int computeCount(int count14, long value) {
      long value2 = 0L;
      value2 = (long)count14 - value;
      if (value2 <= 2147483647L && value2 >= -2147483648L) {
         return (int)value2;
      } else {
         throw new NumberFormatException("Scale out of range.");
      }
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, int count14, long value, int count15, long value2, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count16 = count15;
      byte byteValue = 0;
      int count17 = 0;
      int count18 = 0;
      SplineParser splineParser4 = null;
      int count19 = 0;
      int count20 = 0;
      SignumComparable signumComparable4 = null;
      SignumComparable signumComparable5 = null;
      int count21 = 0;
      long value4 = 0x7b7581289cf2a1f3L;
      long value5 = 0x48a7200d527f1fe5L;

      while (true) {
         int count22 = (int)(value4 % 17L);
         if (count22 == 13) {
            value5 = (value5 ^ 0x2a6a56884e91f690L) + 0x33a54b13db98dd55L;
            break;
         }

         if (count22 == 12) {
            break;
         }

         if (count22 != 10) {
            if (count22 != 5) {
               if (count22 != 8) {
                  if (count22 != 6) {
                     if (count22 != 4) {
                        if (count22 == 7) {
                           if (count20 != count16) {
                              long value6 = (value4 ^ 0x5336b1ab9a54a443L) - 0x49bf77d0170bd24L;
                              long value7 = (value5 ^ 0x63032b20162717eaL) + 0x2ddb0de873430452L;
                              count21 = computeCountByTime((long)count20 - (long)count16);
                              value4 = (value6 ^ -0x5b5242c834af5e9L) - 0x540c27025b50e714L;
                              value5 = (value7 ^ 0x10011622222a0bd2L) - 0x513ec61461cbfce5L;
                           } else {
                              value4 = (value4 ^ -0x4eac94751cbda0ebL) + 0x56ae544990b576f1L;
                              value5 = (value5 ^ -0x22aa24b8050f0434L) + 0x346ba2195a8cdb7fL;
                           }
                           continue;
                        }

                        if (count22 == 9) {
                           int count23 = computeCountByTime(value2);
                           splineParser4 = resolveSplineParser(signumComparable3, signumComparable5, count19, count18, count23);
                           value4 = (value4 ^ 0x5bde91c1553d0bd0L) - 0x277bf196ddd87008L;
                           value5 = (value5 ^ -0x6e01d042520d2416L) - 0x4e57a219e2770e1L;
                           continue;
                        }

                        if (count22 == 15) {
                           int count24 = computeCountByTime(value2);
                           splineParser4 = resolveSplineParser(signumComparable4, value, count19, count18, count24);
                           value4 = (value4 ^ -0x3b073925eb622b61L) - 0x79755bdabaa79338L;
                           value5 = (value5 ^ -0x1c43c817105bfa73L) + 0x15d0a18aa6305ab8L;
                           continue;
                        }

                        if (count22 == 14) {
                           signumComparable4 = resolveSignumComparable(signumComparable3, count20);
                           value4 = (value4 ^ 0x3b4824fae721ce14L) + 0x5956b9b854217df6L;
                           value5 = (value5 ^ -0x20aa50df163049cfL) - 0x159dc456b10beab5L;
                           continue;
                        }

                        if (count22 == 16) {
                           count20 = computeCountByTime((long)count17 + (long)count16 - (long)count14);
                           value4 = (value4 ^ 0x70ef2b986f833125L) - 0x1cfa67d57024e381L;
                           value5 = (value5 ^ 0x5784559de0dc7194L) + 0x4e4927067712f718L;
                           continue;
                        }

                        if (count22 == 1) {
                           count18 = fG7jSMNbfywSerializable.combatScaleProperty.count;
                           value4 = (value4 ^ -0x37e59764e7d1c2e6L) - 0x709cecb36c1a2b12L;
                           value5 = (value5 ^ -0x898c156e381c1c4L) - 0x60b08df0ffad593dL;
                           continue;
                        }

                        if (count22 == 2) {
                           value5 = (value5 ^ -0x282ac33d58573369L) - 0x2bea56ee4f55e6a6L;
                           break;
                        }

                        if (count22 != 0) {
                           if (count22 != 3) {
                              value5 = (value5 ^ -0x27a555c360cf834fL) - 0x70555c7c0a9187bcL;
                              break;
                           }

                           count19 = computeCountByTime(value2 + (long)count16 - (long)count14 + (long)count17);
                           value4 = (value4 ^ -0x34b76cd087ac1b49L) + 0x40c05dc43b187629L;
                           value5 = (value5 ^ -0x289d20c4403c3baaL) + 0x2c20d198d0de3ad2L;
                           continue;
                        }

                        count16--;
                     } else {
                        if (-computeCount(value, count16, signumComparable3, count14) > 0) {
                           value4 = (value4 ^ -0x7904853c7ce29625L) - 0x7e641c42ab3f108dL;
                           value5 = (value5 ^ 0x4a6ea68a069d2206L) + 0x30ce57dd0db98616L;
                           continue;
                        }

                        value4 = (value4 ^ -0x19a7734d67042fc0L) - 0x1e022df18f58ba18L;
                        value5 = (value5 ^ 0x49bd3f713b3f6830L) + 0x327dbee7f95b4c24L;
                     }

                     count17 = fG7jSMNbfywSerializable.count2;
                     value4 = (value4 ^ -0x61d151c48cbf17dcL) + 0x76e8f06c9e0e76a8L;
                     value5 = (value5 ^ -0xd0ff300497592e4L) - 0x276f81ccba62178bL;
                  } else if (computeCountByTime((long)count17 + (long)count16 - (long)count14) <= 0) {
                     long value8 = (value4 ^ 0x5218c3c3db34bec3L) - 0x3b7639674a0c7500L;
                     long value9 = (value5 ^ 0x7e2c09e702554a6bL) + 0x2ec1bea36951d21dL;
                     count20 = computeCountByTime((long)count14 - (long)count17);
                     value4 = (value8 ^ 0x84d701f19dd045cL) - 0x26746c4487659dd5L;
                     value5 = (value9 ^ 0x3c17a12218d253bcL) - 0x3ed70eb6dbdbd8ecL;
                  } else {
                     value4 = (value4 ^ -0x4fa1d724d7270642L) - 0x5a2a7c5f8487d896L;
                     value5 = (value5 ^ -0x11e2fd7012c237c0L) + 0x76c105973d0f2839L;
                  }
               } else {
                  long value10;
                  if ((value10 = computeTimestamp(value, count21)) != Long.MIN_VALUE) {
                     long value11 = (value4 ^ 0x573cd5a2f59b5bc8L) + 0x1c0b4758c2dae75aL;
                     long value12 = (value5 ^ -0x456a19c196cc09c7L) - 0x5b2d410bbe199794L;
                     int count25 = computeCountByTime(value2);
                     splineParser4 = resolveSplineParser(signumComparable3, value10, count19, count18, count25);
                     value4 = (value11 ^ 0x1e4944c859e946b8L) + 0x2dc68e8b02101587L;
                     value5 = (value12 ^ 0x63d27df8319a60f1L) - 0x1402cdbc4d5b8c8aL;
                  } else {
                     value4 = (value4 ^ -0x46e7c5442917d580L) + 0x5ff5d222d41812c0L;
                     value5 = (value5 ^ 0x2684e92080f7afb1L) + 0x32791a89926d8894L;
                  }
               }
            } else {
               signumComparable5 = resolveSignumComparable(value, count21);
               value4 = (value4 ^ 0x15ab2137c966ff0dL) - 0x3b600c8cec3329d1L;
               value5 = (value5 ^ -0x633f5eb2690e8L) + 0xecb6fb765b742efL;
            }
         } else {
            int count26 = computeCountByTime(value2);
            splineParser4 = resolveSplineParser(signumComparable3, value, count19, count18, count26);
            value4 = (value4 ^ -0x7db9a203fb04fd2aL) - 0x3be0e8811f50ccb5L;
            value5 = (value5 ^ -0x4538a187e0dd10bdL) + 0x5e0859f541a2fb76L;
         }
      }

      return resolveSplineParser(splineParser4, fG7jSMNbfywSerializable);
   }

   private SplineParser getSplineParser5() {
      long value = 0L;
      if (this.timestamp2 != Long.MIN_VALUE) {
         if (this.signumComparable2 != null) {
            value = this.signumComparable2.getTimestamp();
            if (value != this.timestamp2) {
               applyText("audit", this);
               throw new AssertionError("Inconsistent state, intCompact=" + this.timestamp2 + "\t intVal=" + value);
            }
         }

         if (this.count9 > 0 && this.count9 != computeCount4(this.timestamp2)) {
            applyText("audit", this);
            throw new AssertionError("precision mismatch");
         }
      } else {
         if (this.signumComparable2 == null) {
            applyText("audit", this);
            throw new AssertionError("null intVal");
         }

         if (this.count9 > 0 && this.count9 != computeCount(this.signumComparable2)) {
            applyText("audit", this);
            throw new AssertionError("precision mismatch");
         }
      }

      return this;
   }

   public SplineParser resolveSplineParser5(int count14) {
      return this.resolveSplineParser(count14, 7);
   }

   private static SplineParser resolveSplineParser(long value, int count14, long value2) {
      long value3 = value;
      int count15 = count14;
      long value4 = 0L;
      long value5 = 0x190d99c5a25f4be5L;
      long value6 = -0x6f6f60898975099bL;

      while (true) {
         int count16 = (int)(value5 % 8L);
         if (count16 != 7) {
            if (count16 != 2) {
               if (count16 != 1) {
                  if (count16 == 4) {
                     break;
                  }

                  if (count16 != 5) {
                     if (count16 != 3) {
                        if (count16 == 6) {
                           break;
                        }

                        count15 = computeCount(value3, (long)count15 - (value6 % 156123L ^ -128491L));
                        value5 = (value5 ^ -0x99a447caba32ffeL) + 0x6102e8135f764e71L;
                        value6 = (value6 ^ 0x6e158776d94b0724L) + 0x720a78828b734eacL;
                        continue;
                     }

                     value5 = (value5 ^ 0x7d72edd8432e7908L) - 0x14e42b50f7224a16L;
                     value6 = (value6 ^ 0x200f4af4873b0e3bL) + 0x2b9e5115f13dbf5bL;
                  }

                  if (Math.abs(value3) < 10L) {
                     break;
                  }

                  value5 = (value5 ^ 0x281b7d492e06101eL) + 0x396f95b6289810d4L;
                  value6 = (value6 ^ -0x555a39c38016c3e9L) + 0x47cdc459757fdb9eL;
               } else if (value4 == 0L) {
                  long value7 = (value5 ^ 0x11e6319dd5c67b34L) + 0x1f98b1ef425be6cfL;
                  long value8 = (value6 ^ -0x3c2a7667bf5e8c63L) - 0xaa77a13657aedd8L;
                  value3 /= 10L;
                  value5 = (value7 ^ 0x6778f97f439f4b8aL) - 0x207a1510e58dd2f6L;
                  value6 = (value8 ^ -0x634fea386f4e6d00L) - 0x5867b6dc4f828499L;
               } else {
                  value5 = (value5 ^ -0x6d16a0ab29c28336L) - 0x61767057bf7f18d7L;
                  value6 = (value6 ^ -0x441bfc4b139783fL) - 0x22cec0b06f215a14L;
               }
            } else if ((value3 & (value6 % 294178L ^ 284248L)) == 0L) {
               long value9 = (value5 ^ -0x421ba2fd39cf374L) + 0x2b9c7eb6ba4d4780L;
               long value10 = (value6 ^ -0x1678bf1038549807L) + 0x156d0d44c2a7fd5L;
               value4 = value3 % 10L;
               value5 = (value9 ^ 0x9353ee12eace9e8L) - 0xb3818555755fdL;
               value6 = (value10 ^ -0x541d29efa8d20e19L) - 0x227afb1cdcaa411L;
            } else {
               value5 = (value5 ^ -0x4417d9b0eaae7ee0L) + 0x6bb21d4df17ac42cL;
               value6 = (value6 ^ -0x1438d8025c73f279L) - 0xe9961d57eee9c5L;
            }
         } else {
            if ((long)count15 <= value2) {
               break;
            }

            value5 = (value5 ^ 0x65c22ca7df4ec041L) - 0xcca925a1969329cL;
            value6 = (value6 ^ 0x3b4e28678b8be1f6L) + 0x5f588a4e380c2ea5L;
         }
      }

      return resolveSplineParserByTime(value3, count15);
   }

   private static int computeCount5(long value, long value2) {
      long value3 = 0x27d828b13a484341L;
      long value4 = 0x2c9f62134410d9d8L;
      if (value == 0L) {
         value3 = (value3 ^ 0x6a98a2cb60ff6f91L) - 0x4971062a0cae391bL;
         value4 = (value4 ^ 0xd32a3bfecdcb2cL) + 0x536b8b252d039ef4L;
         if (value2 < 0L) {
            value3 = (value3 ^ -0x13b5e397af1f07e4L) + 0x69a7360758725011L;
            value4 = (value4 ^ 0x6f7a7ab66179502fL) - 0x675c130de0f21cb2L;
            byte byteValue;
            if (!checkTime(value2, timestampArray3[0][1])) {
               byteValue = 19;
            } else {
               value3 = (value3 ^ -0x7e4754f4f54ba02fL) + 0x6d5842b08d38b3b0L;
               value4 = (value4 ^ 0x2b2fe8de5b8a196aL) - 0xedfaa64ea4a71d5L;
               byteValue = 20;
            }

            return byteValue;
         } else {
            value3 = (value3 ^ 0x18d0618e5cfbb46dL) - 0x592dae560298991L;
            value4 = (value4 ^ 0x4c6bfdcb7a74111bL) + 0x579e8b50d6bc95dL;
            return computeCount4(value2);
         }
      } else {
         int count14 = (128 - Long.numberOfLeadingZeros(value) + 1) * 1233 >>> 12;
         int count15 = count14 - 19;
         return count15 < timestampArray3.length && !checkTime(value, value2, timestampArray3[count15][0], timestampArray3[count15][1]) ? count14 + 1 : count14;
      }
   }

   public SplineParser resolveSplineParser7(SplineParser splineParser4) {
      int count14 = 0;
      long value = (long)this.count5 + (long)splineParser4.count5;
      count14 = this.computeCount2(value);
      if (this.timestamp2 == Long.MIN_VALUE) {
         if (splineParser4.timestamp2 == Long.MIN_VALUE) {
            SignumComparable signumComparable3 = splineParser4.signumComparable2;
            return resolveSplineParser(this.signumComparable2, signumComparable3, count14);
         } else {
            return resolveSplineParser(splineParser4.timestamp2, this.signumComparable2, count14);
         }
      } else {
         return splineParser4.timestamp2 == Long.MIN_VALUE
            ? resolveSplineParser(this.timestamp2, splineParser4.signumComparable2, count14)
            : resolveSplineParserByTime(this.timestamp2, splineParser4.timestamp2, count14);
      }
   }

   public SplineParser resolveSplineParser3(FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      return this.getCountAsIndex() < 0 ? this.resolveSplineParserByFG7jSMNbfywSeria(fG7jSMNbfywSerializable) : this.resolveSplineParser2(fG7jSMNbfywSerializable);
   }

   public static SplineParser resolveSplineParser(int count14, int count15, int count16) {
      Object entry = null;
      if (count14 >= timestampArray2.length) {
         entry = resolveSignumComparableByIndex(count14);
         if (count15 == -1) {
            entry = ((SignumComparable)entry).getSignumComparable2();
         }

         return new SplineParser((SignumComparable)entry, Long.MIN_VALUE, count16, count14 + 1);
      } else {
         return resolveSplineParserByTime((long)count15 * timestampArray2[count14], count16);
      }
   }

   public SplineParser resolveSplineParser6(SplineParser splineParser4, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      if (fG7jSMNbfywSerializable.count2 != 0) {
         SplineParser splineParser5 = splineParser4.getSplineParser();
         SplineParser splineParser6 = splineParser5;
         return this.resolveSplineParser5(splineParser6, fG7jSMNbfywSerializable);
      } else {
         return this.resolveSplineParser3(splineParser4);
      }
   }

   private static SplineParser resolveSplineParser(long value, int count14, long value2, int count15, int count16, int count17) {
      int count18 = 0;
      int count19 = 0;
      long value3 = 0L;
      SplineParser splineParser4 = null;
      Object entry = null;
      if (computeCount(value, (long)count16 + (long)count15) <= count14) {
         count18 = computeCount(value2, (long)count14 - (long)count16);
         count19 = count18 - count15;
         if (count19 < timestampArray2.length && (value3 = computeTimestamp(value2, count19)) != Long.MIN_VALUE) {
            return resolveSplineParser(value, value3, count16, count17, count16);
         } else {
            entry = resolveSignumComparable(value2, count19);
            return resolveSplineParser(SignumComparable.resolveSignumComparableByTime(value), (SignumComparable)entry, count16, count17, count16);
         }
      } else {
         count18 = count16 + count15;
         count19 = count18 - count14;
         if (count19 < timestampArray2.length) {
            if ((value3 = computeTimestamp(value, count19)) != Long.MIN_VALUE) {
               return resolveSplineParser(value3, value2, count16, count17, count16);
            }

            splineParser4 = resolveSplineParser(timestampArray2[count19], value, value2, count16, count17, count16);
            if (splineParser4 != null) {
               return splineParser4;
            }
         }

         entry = resolveSignumComparable(value, count19);
         return resolveSplineParser((SignumComparable)entry, value2, count16, count17, count16);
      }
   }

   public SplineParser resolveSplineParser(int count14, CombatScaleProperty combatScaleProperty) {
      int count15 = combatScaleProperty.count;
      return this.resolveSplineParser(count14, count15);
   }

   private static boolean checkTimeByTime(long value, long value2) {
      return value + Long.MIN_VALUE > value2 + Long.MIN_VALUE;
   }

   public SplineParser resolveSplineParser3(SplineParser splineParser4, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count14 = 0;
      SplineParser splineParser5 = null;
      SplineParser splineParser6 = null;
      int count15 = 0;
      long value = 0x4ffe01326b96b6bfL;
      long value2 = -0x73e22b7c16daa4bfL;

      while (true) {
         int count16 = (int)(value % 15L);
         if (count16 != 5) {
            if (count16 != 0) {
               if (count16 == 8) {
                  return this.resolveSplineParser(splineParser4);
               }

               if (count16 != 4) {
                  if (count16 != 2) {
                     if (count16 != 14) {
                        if (count16 != 12) {
                           if (count16 == 10) {
                              int count17 = splineParser5.getCount5() + Math.min(count15, count14 - splineParser5.count5);
                              return splineParser5.resolveSplineParser5(count17);
                           }

                           if (count16 == 6) {
                              int count18 = splineParser5.count5;
                              long timestamp6 = splineParser5.timestamp2;
                              return resolveSplineParser(splineParser5.signumComparable2, timestamp6, count18, count14);
                           }

                           if (count16 == 7) {
                              if (this.computeCount(splineParser4) >= 0) {
                                 value = (value ^ -0x7e915cdfa2b1a71eL) - 0x628e501474885c82L;
                                 value2 = (value2 ^ -0x472f2a740b75574fL) - 0x32161e897d9c8007L;
                              } else {
                                 value = (value ^ -0x31911532f60609cfL) - 0x3dea493a7dbf88edL;
                                 value2 = (value2 ^ -0x5e1ac62b0988df55L) + 0x6b6f35813ac89a4L;
                              }
                              continue;
                           }

                           if (count16 == 13) {
                              throw new ArithmeticException("Division impossible");
                           }

                           if (count16 == 9) {
                              if (fG7jSMNbfywSerializable.count2 == 0) {
                                 value = (value ^ -0x58c876a665ac98b5L) + 0x75721b8e6ea59701L;
                                 value2 = (value2 ^ -0x2da4eeaf79c92b72L) - 0x14d6fb2ec6c86f41L;
                              } else {
                                 value = (value ^ -0x3e0b584b9f28ca94L) - 0x3bbf9e8c336e7c00L;
                                 value2 = (value2 ^ 0x6eada65a632f0373L) + 0xad7bbed8df5e0fL;
                              }
                              continue;
                           }

                           if (count16 == 3) {
                              FG7jSMNbfywSerializable fG7jSMNbfywSerializable2 = new FG7jSMNbfywSerializable(fG7jSMNbfywSerializable.count2, CombatScaleProperty.DOWN);
                              splineParser5 = this.resolveSplineParser2(splineParser4, fG7jSMNbfywSerializable2);
                              value = (value ^ 0x7a3de0316c9e7e09L) - 0x13f980f5be537e32L;
                              value2 = (value2 ^ 0x30b4248eb6ee4535L) - 0x5a816623196d7dd8L;
                              continue;
                           }

                           if (count16 != 11) {
                              if (this.resolveSplineParser3(splineParser6).computeCount(splineParser4) < 0) {
                                 value = (value ^ -0xf05be6f09973263L) - 0x2be1d2a44245415dL;
                                 value2 = (value2 ^ -0xe0ca475be4be26dL) - 0x19aa4d852be51f6L;
                              } else {
                                 value = (value ^ 0x4e57f07b4b69a594L) + 0x4143830ff777565L;
                                 value2 = (value2 ^ -0xfed15e5526e06c6L) - 0x2aa9902c4a09420L;
                              }
                              continue;
                           }

                           value = (value ^ 0x3cc1567ff1a3546eL) - 0x479d6c7c905951a1L;
                           value2 = (value2 ^ 0x4906f8cc72e99920L) - 0x6a50c893d45609bfL;
                        }

                        if (count14 <= splineParser5.getCount5()) {
                           value = (value ^ -0x3f7e27f37d38ad69L) + 0x742f4b7102744871L;
                           value2 = (value2 ^ 0x6e6e8ef65e2f8e39L) - 0x3e5caecc88096719L;
                        } else {
                           value = (value ^ -0x7b996902b5615ccfL) + 0x73af81c9d627c172L;
                           value2 = (value2 ^ 0xc970eb44c1cbb90L) + 0x6d5d1e4a93417c9fL;
                        }
                     } else {
                        count14 = computeCount((long)this.count5 - (long)splineParser4.count5);
                        value = (value ^ 0x72967eb5eeabb76L) - 0x69a4687d43b2fe87L;
                        value2 = (value2 ^ 0xa22405ba8963d6aL) + 0x3a012e04a9433f6fL;
                     }
                  } else {
                     CombatScaleProperty combatScaleProperty = CombatScaleProperty.DOWN;
                     byte byteValue = 0;
                     splineParser5 = splineParser5.resolveSplineParser(byteValue, combatScaleProperty);
                     value = (value ^ -0x192264f225e5d37L) + 0x6aefcd65b07b9c51L;
                     value2 = (value2 ^ -0x49d4e7494bc97571L) + 0x2f52ef5f3f14b184L;
                  }
               } else {
                  splineParser6 = splineParser5.resolveSplineParser7(splineParser4);
                  value = (value ^ 0x1ad83e57d265f477L) + 0xec04b66e26668bL;
                  value2 = (value2 ^ 0x2599c138256e6cd1L) - 0x25269ae0553559afL;
               }
            } else if (splineParser5.getCount5() >= 0) {
               long value3 = (value ^ 0x377120ba301d2de7L) + 0x101a0a9ae039d350L;
               long value4 = (value2 ^ 0x33280d6d02b84c16L) - 0x647b0d1008cc3b55L;
               if (splineParser5.getCount5() <= 0) {
                  value = (value3 ^ -0x44e4846c3508bae2L) + 0x37a870df487a9dcfL;
                  value2 = (value4 ^ -0x2bfc3d90be6788aL) + 0x41e9f382a519fbf3L;
               } else {
                  value = (value3 ^ -0x2423b409a1a8693aL) - 0x655f85da90ffca21L;
                  value2 = (value4 ^ -0x55c97e27662cb936L) - 0x22eaa9694012f9a0L;
               }
            } else {
               value = (value ^ 0x49d721513b4a2463L) + 0x4e1b94437ed154e2L;
               value2 = (value2 ^ -0x5d6c47c1b2ef5557L) - 0x5d970f5809f2acddL;
            }
         } else if ((count15 = fG7jSMNbfywSerializable.count2 - splineParser5.getCount()) <= 0) {
            value = (value ^ 0x2bb3934dd514ab38L) + 0x193309d320e0bc81L;
            value2 = (value2 ^ 0x4de2e72278470337L) - 0x18202673115ccce0L;
         } else {
            value = (value ^ 0x4f6a9c0cbd9cb155L) + 0x1dde881f13b7f78fL;
            value2 = (value2 ^ 0x7dcb9818411ac6e5L) + 0x3516263406a2ba3L;
         }
      }
   }

   private static SplineParser resolveSplineParser(int count14, int count15, int count16, int count17) {
      int count18 = 0;
      if (count16 <= count17) {
         return resolveSplineParser(count15, count14, count16);
      } else {
         count18 = count16 - count17;
         return count18 >= count15 ? resolveSplineParserByTime((long)count14, count16 - count15) : resolveSplineParser(count15 - count18, count14, count17);
      }
   }

   public static SplineParser resolveSplineParser3(int count14) {
      long value = 0x6e813d7ee9a0b913L;

      while (true) {
         if ((int)(value % 4L) != 3) {
            if (count14 < splineParserArray2.length) {
               return splineParserArray2[count14];
            }
            break;
         }

         if (count14 < 0) {
            break;
         }

         value = (value ^ -0x3f7f80e752c061L) - 0x1a0a780c178cbb04L;
      }

      return new SplineParser(SignumComparable.signumComparable5, 0L, count14, 1);
   }

   public SplineParser resolveSplineParser4(SplineParser splineParser4) {
      if (splineParser4.getCountAsIndex() == 0) {
         if (this.getCountAsIndex() == 0) {
            throw new ArithmeticException("Division undefined");
         } else {
            throw new ArithmeticException("Division by zero");
         }
      } else {
         int count14 = computeCount((long)this.count5 - (long)splineParser4.count5);
         if (this.getCountAsIndex() == 0) {
            return resolveSplineParser3(count14);
         } else {
            FG7jSMNbfywSerializable fG7jSMNbfywSerializable = new FG7jSMNbfywSerializable(
               (int)Math.min((long)this.getCount() + (long)Math.ceil(10.0 * (double)splineParser4.getCount() / 3.0), 2147483647L), CombatScaleProperty.UNNECESSARY
            );

            SplineParser splineParser5;
            try {
               splineParser5 = this.resolveSplineParser2(splineParser4, fG7jSMNbfywSerializable);
            } catch (ArithmeticException arithmeticException) {
               throw new ArithmeticException("Non-terminating decimal expansion; no exact representable decimal result.");
            }

            int count15 = splineParser5.getCount5();
            return count14 > count15 ? splineParser5.resolveSplineParser(count14, 7) : splineParser5;
         }
      }
   }

   public SplineParser getSplineParser2() {
      if (this.timestamp2 != 0L && (this.signumComparable2 == null || this.signumComparable2.getCount8() != 0)) {
         if (this.timestamp2 == Long.MIN_VALUE) {
            long value2 = Long.MIN_VALUE;
            int count15 = this.count5;
            return resolveSplineParser(this.signumComparable2, count15, value2);
         } else {
            return resolveSplineParser(this.timestamp2, this.count5, Long.MIN_VALUE);
         }
      } else {
         return splineParser;
      }
   }

   private int computeCount(SplineParser splineParser4) {
      short shortValue = 2391;
      short shortValue2 = 26373;
      short shortValue3 = -20637;
      long value = 0L;
      long value2 = 0L;
      long value3 = 0x1789caabd7855415L;
      long value4 = 0x6aaa947122f98769L;
      value = splineParser4.timestamp2;
      value3 = (value3 ^ 0x5615506ccb996f61L) - 0x1f90c6c9a470a19aL;
      value4 = (value4 ^ -0x7cd9e31878d30528L) + 0x48b45a419e50f6e8L;
      value2 = this.timestamp2;
      value3 = (value3 ^ 0x2d8793f5891a1b55L) + 0x102fb30c1540cfd3L;
      value4 = (value4 ^ -0x69f0e8a8d9dd658bL) - 0x22bcbc23208ed5deL;
      if (value2 != 0L) {
         long value5 = (value3 ^ 0x2e79c6a2b65b9f0fL) - 0xcdf10be1739df1L;
         long value6 = (value4 ^ -0x422d4ba59ec732e1L) - 0x1b1fe039f889480cL;
         if (value == 0L) {
            return 1;
         } else {
            long value7 = (value5 ^ -0x2b4ce44c86642f44L) + 0x4cace59306882fbcL;
            long value8 = (value6 ^ 0x3dacd2d36a18e7b4L) + 0x4942eccd9e82054L;
            long value9 = (long)this.count5 - (long)splineParser4.count5;
            if (value9 != 0L) {
               long value10 = (long)this.getCount() - (long)this.count5;
               long value11 = (long)splineParser4.getCount() - (long)splineParser4.count5;
               if (value10 < value11) {
                  return -1;
               }

               long value12 = (value7 ^ 0x72310137f0f2d560L) - 0x11d100f0708ecaa0L;
               long value13 = (value8 ^ 0x417ac96824c86258L) - 0x3f39b897db486258L;
               if (value10 > value11) {
                  return 1;
               }

               long value14 = (value12 ^ -0xb2b8d9678caf3cfL) + 0x6cd40de981330c2fL;
               long value15 = (value13 ^ 0x77afa97ca87a3255L) - 0x356c588467fa324bL;
               if (value9 >= 0L) {
                  if (value9 <= 2147483647L
                     && (value == Long.MIN_VALUE || (value = computeTimestamp(value, (int)value9)) == Long.MIN_VALUE)
                     && value2 == Long.MIN_VALUE) {
                     int count14 = (int)value9;
                     SignumComparable signumComparable3 = splineParser4.resolveSignumComparable(count14);
                     return this.signumComparable2.computeCount(signumComparable3);
                  }
               } else {
                  searchBlock:
                  if (value9 > -2147483648L) {
                     if (value2 == Long.MIN_VALUE) {
                        value14 = (value14 ^ -0x47b54d82e5e61468L) - 0x57cab22b19f99568L;
                        value15 = (value15 ^ 0x2d2809f7fa4306f3L) + 0x151807f04a3f010fL;
                     } else if ((value2 = computeTimestamp(value2, (int)(-value9))) != Long.MIN_VALUE) {
                        break searchBlock;
                     }

                     if (value == Long.MIN_VALUE) {
                        int count15 = (int)(-value9);
                        SignumComparable signumComparable4 = this.resolveSignumComparable(count15);
                        SignumComparable signumComparable5 = splineParser4.signumComparable2;
                        return signumComparable4.computeCount(signumComparable5);
                     }
                  }
               }
            }

            if (value2 == Long.MIN_VALUE) {
               if (value == Long.MIN_VALUE) {
                  SignumComparable signumComparable6 = splineParser4.signumComparable2;
                  return this.signumComparable2.computeCount(signumComparable6);
               } else {
                  return 1;
               }
            } else {
               return value == Long.MIN_VALUE ? -1 : computeCount3(value2, value);
            }
         }
      } else {
         value3 = (value3 ^ 0x5f8a36161828b1b6L) - 0x3c0e31993587b165L;
         value4 = (value4 ^ 0xda9a370b91506e5L) - 0x63e7c64b373b4040L;
         byte byteValue;
         if (value != 0L) {
            byteValue = -1;
         } else {
            value3 = (value3 ^ -0x2a49a6980e2fc5e2L) + 0x5f5e7a9d9cb3270bL;
            value4 = (value4 ^ 0x7c610b234322e122L) - 0x33122bf65a3fb284L;
            byteValue = 0;
         }

         return byteValue;
      }
   }

   private static SplineParser resolveSplineParser(long value, int count14, SignumComparable signumComparable3, int count15, long value2, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count16 = count15;
      int count17 = 0;
      int count18 = 0;
      SplineParser splineParser4 = null;
      int count19 = 0;
      int count20 = 0;
      SignumComparable signumComparable4 = null;
      SignumComparable signumComparable5 = null;
      int count21 = 0;
      long value4 = 0x7dd0b12aa876ce96L;
      long value5 = 0x6883a308d9a530aaL;

      while (true) {
         int count22 = (int)(value4 % 13L);
         if (count22 != 11) {
            if (count22 == 6) {
               value5 = (value5 ^ 0x225b860fe0be36fdL) - 0x6f05f26575624634L;
               break;
            }

            if (count22 != 10) {
               if (count22 == 5) {
                  signumComparable4 = resolveSignumComparable(value, count20);
                  value4 = (value4 ^ -0x491fe878828e9265L) - 0x7360c87c33f9f673L;
                  value5 = (value5 ^ 0x1f002d076b2ca968L) + 0x4282ec7734a5feebL;
                  continue;
               }

               if (count22 == 7) {
                  count21 = computeCountByTime((long)count20 - (long)count16);
                  value4 = (value4 ^ -0x176152ae24cb1b77L) - 0x6b77bd231fa56a14L;
                  value5 = (value5 ^ -0x339f67bb0e5d1994L) + 0x416d1e446778fd41L;
                  continue;
               }

               if (count22 == 9) {
                  count19 = computeCountByTime(value2 + (long)count16 - (long)count14 + (long)count17);
                  value4 = (value4 ^ -0x6ae9c7ef3583d933L) - 0x358cea8f3b13e2aeL;
                  value5 = (value5 ^ 0x595c09010b72dc3dL) + 0x5aff34ae57ef0b72L;
                  continue;
               }

               if (count22 == 8) {
                  break;
               }

               if (count22 != 0) {
                  if (count22 != 12) {
                     if (count22 != 4) {
                        if (count22 != 2) {
                           if (count22 != 1) {
                              count18 = fG7jSMNbfywSerializable.combatScaleProperty.count;
                              value4 = (value4 ^ -0x69ca28db889d24adL) + 0x62143a825c9230c0L;
                              value5 = (value5 ^ 0x30c2ea60e574e556L) - 0x34cd58d6f7f7751bL;
                           } else {
                              signumComparable5 = resolveSignumComparable(signumComparable3, count21);
                              value4 = (value4 ^ -0x519aadd30ce90b42L) - 0x7eefee78b80ac19bL;
                              value5 = (value5 ^ -0x7a525e7feea96c37L) + 0x36f056f0aca1a796L;
                           }
                        } else {
                           SignumComparable signumComparable6 = SignumComparable.resolveSignumComparableByTime(value);
                           int count23 = computeCountByTime(value2);
                           splineParser4 = resolveSplineParser(signumComparable6, signumComparable5, count19, count18, count23);
                           value4 = (value4 ^ 0x5f1820e8a4142d32L) + 0x245c0ec9cac1d13bL;
                           value5 = (value5 ^ -0x3735da476ef18462L) + 0x299e4c5b54fbb348L;
                        }
                     } else {
                        count20 = computeCountByTime((long)count17 + (long)count16 - (long)count14);
                        value4 = (value4 ^ 0x41046e14de2f8e1L) - 0x4e15e73ca99f884aL;
                        value5 = (value5 ^ 0x48c63d7f4c35dfe5L) - 0x2b866f807a5b75a4L;
                     }
                  } else {
                     int count24 = computeCountByTime(value2);
                     splineParser4 = resolveSplineParser(signumComparable4, signumComparable3, count19, count18, count24);
                     value4 = (value4 ^ -0x3448f7f949e53bf0L) + 0x36c97f4917e68bfaL;
                     value5 = (value5 ^ 0x4af0e024c6687e9cL) - 0x523e4ae9199cd930L;
                  }
                  continue;
               }

               count16--;
            } else {
               if (computeCount(value, count14, signumComparable3, count16) > 0) {
                  value4 = (value4 ^ 0x47f223e40528718fL) - 0xd3dd603ff87755fL;
                  value5 = (value5 ^ -0x3ea1c76862c6608eL) + 0x2eea1c34e24dc131L;
                  continue;
               }

               value4 = (value4 ^ -0x1d35fb9f6ba8ba1bL) - 0x7235f87f8e4a41b9L;
               value5 = (value5 ^ 0x242f20bcd27d1161L) - 0x73e4cbdfe4edb0c2L;
            }

            count17 = fG7jSMNbfywSerializable.count2;
            value4 = (value4 ^ -0x1b64b9c53972f095L) + 0x6edff6bcb8eb79bcL;
            value5 = (value5 ^ -0x26d2b51973ac0821L) - 0x51d76b2f2db1d5b7L;
         } else if (computeCountByTime((long)count17 + (long)count16 - (long)count14) <= 0) {
            long value6 = (value4 ^ -0x7621b42ab57a7a8dL) + 0x45f06bf19a2b4a0bL;
            long value7 = (value5 ^ 0x60b3a671286c2e34L) - 0xa15b4fe696bca1cL;
            count20 = computeCountByTime((long)count14 - (long)count17);
            value4 = (value6 ^ 0x3670fc861f595246L) - 0x1023d14383860998L;
            value5 = (value7 ^ 0x3f1466e5344325b3L) + 0x4a3ebe1b5b7f9b92L;
         } else {
            value4 = (value4 ^ -0x442de2b142d0beabL) + 0x7f1826de3b8a20eaL;
            value5 = (value5 ^ 0x3f96ddeb7e722a2fL) + 0x63055f8c35288aa5L;
         }
      }

      return resolveSplineParser(splineParser4, fG7jSMNbfywSerializable);
   }

   private SplineParser[] resolveSplineParserArray(SplineParser splineParser4, SplineParser splineParser5, long value, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      SplineParser splineParser6 = null;
      SplineParser splineParser7 = null;
      long value2 = 0L;
      long value3 = 0L;
      SplineParser[] splineParserArray3 = null;
      long value4 = 0x441312abea652642L;
      long value5 = -0x3819a586df15f193L;

      while (true) {
         int count14 = (int)(value4 % 15L);
         if (count14 == 7) {
            break;
         }

         if (count14 == 14) {
            splineParser6 = splineParser4;
            value4 = (value4 ^ 0x5e99bf5c9f8e067bL) + 0xe8fe7e92dd29826L;
            value5 = (value5 ^ -0x5b2e795ba6ebd16cL) + 0x5d3be6880b134649L;
         } else {
            if (count14 != 12) {
               if (count14 == 0) {
                  throw new AssertionError();
               }

               if (count14 == 6) {
                  splineParserArray3 = new SplineParser[]{splineParser6, splineParser7};
                  break;
               }

               if (count14 == 3) {
                  if (value3 <= value2 + 2L) {
                     value4 = (value4 ^ 0x4c6428bf777ca9ffL) - 0x48940036ad51120fL;
                     value5 = (value5 ^ -0x3f0c72bf4ce997f6L) - 0x214aa5595b32fc9dL;
                  } else {
                     value4 = (value4 ^ 0x45b595db45a2a5a9L) - 0xff4523771ec90f7L;
                     value5 = (value5 ^ 0x2b30d91d9db5f198L) - 0x4a8b9a0579066f66L;
                  }
                  continue;
               }

               if (count14 == 1) {
                  value3 = (long)splineParser7.count5 - (long)splineParser7.getCount() + (value5 % 609034L ^ -127983L);
                  value4 = (value4 ^ -0x1006faffd21bff23L) - 0x78eb93cb85cbb376L;
                  value5 = (value5 ^ -0x17fd972508b0a2eL) + 0x6683c2b84a246431L;
                  continue;
               }

               if (count14 == 13) {
                  if (value3 <= (long)(splineParser6.count5 + 2)) {
                     value4 = (value4 ^ 0x3d992cf1334ddcb2L) - 0x2f2aed79c85d3738L;
                     value5 = (value5 ^ -0x2bc078b411c8b463L) + 0x43bf9ff5060d36f9L;
                  } else {
                     value4 = (value4 ^ 0x59c0f74d0b50f1f8L) + 0x30ade5c467eb9c7cL;
                     value5 = (value5 ^ -0x7d5326544f9ef2daL) - 0x8f9f2d1f40072efL;
                  }
                  continue;
               }

               if (count14 == 8) {
                  if (value >= 0L) {
                     long value6 = (value4 ^ 0x4d5b4cd1158770aeL) + 0x3bff1018f5b7535aL;
                     long value7 = (value5 ^ -0x4caba4bb6f72015cL) + 0x38af497278750ecdL;
                     splineParser6 = splineParser5;
                     value4 = (value6 ^ -0x240901192f26e824L) - 0xdaba21c93c4b033L;
                     value5 = (value7 ^ -0x2f2c95c5cdb211d9L) - 0x6b193378f193aa74L;
                  } else {
                     value4 = (value4 ^ 0x4bd073253d1d1168L) + 0x5aa47b21ba5726b7L;
                     value5 = (value5 ^ -0x6343874620f98eacL) - 0x440e25d73b5c2d6eL;
                  }
                  continue;
               }

               if (count14 == 5) {
                  long countAsIndex = (long)splineParser7.getCountAsIndex();
                  long value8 = Math.max((long)splineParser6.count5, value2) + 3L;
                  splineParser7 = resolveSplineParserByTime(countAsIndex, this.computeCount2(value8));
                  value4 = (value4 ^ 0x1e8577e4597c6af2L) - 0x4b7488ff729e26bcL;
                  value5 = (value5 ^ 0x30e295c213ae6f3fL) - 0x4d1cacd2c72680e0L;
                  continue;
               }

               if (count14 == 10) {
                  if (enabled) {
                     value4 = (value4 ^ 0x75fc045ffe15c83aL) + 0x25a832e72e48fb59L;
                     value5 = (value5 ^ 0x5997e6dca93e3f25L) - 0x1d220412a4df1193L;
                  } else {
                     value4 = (value4 ^ 0x670e5ad0656140e7L) + 0x4cd90c1bca2dc580L;
                     value5 = (value5 ^ 0x1c0138591059f6a6L) - 0x94a4e8f591ede64L;
                  }
                  continue;
               }

               if (count14 == 4) {
                  splineParser7 = splineParser5;
                  value4 = (value4 ^ -0x646675e0dc67cb70L) - 0x55cd6657aae698c8L;
                  value5 = (value5 ^ -0x999bc6012be6cdfL) + 0x3eb4ec616316cacdL;
                  continue;
               }

               if (count14 == 11) {
                  splineParser7 = splineParser4;
                  value4 = (value4 ^ 0x55cfea606e6b574cL) + 0x1c8217d16b52c3acL;
                  value5 = (value5 ^ 0x72eb22178c6b1f4aL) + 0x30b29331259d9554L;
                  continue;
               }

               if (count14 == 2) {
                  if (value != 0L) {
                     value4 = (value4 ^ 0x10fd2d80466d2ac7L) - 0x27742f3bdca51d11L;
                     value5 = (value5 ^ 0x6d083b472f8877acL) - 0x3e45704513284e16L;
                     continue;
                  }

                  throw new AssertionError();
               }

               value4 = (value4 ^ 0x8fa04d24356e339L) - 0x175156563e4d2e96L;
               value5 = (value5 ^ 0x56cf8f901adbf2ccL) + 0x30c2224690cec111L;
            }

            value2 = (long)splineParser6.count5 - (long)splineParser6.getCount() + (long)fG7jSMNbfywSerializable.count2;
            value4 = (value4 ^ -0x21283ac2d61d800L) + 0x7fca368b1e65e458L;
            value5 = (value5 ^ 0x41d605aecce67eb8L) - 0x310c8a5453ecb181L;
         }
      }

      return splineParserArray3;
   }

   private static long[] resolveTimeArray(long value, long value2) {
      long value3 = 0L;
      long value4 = 0L;
      long value5 = 0x4528a402ceea18cL;
      long value6 = 0x638fbb99d467c389L;

      while (true) {
         int count14 = (int)(value5 % 15L);
         if (count14 == 8) {
            throw new AssertionError("Unity denominator");
         }

         if (count14 != 0) {
            if (count14 != 6) {
               if (count14 != 7) {
                  if (count14 != 9) {
                     if (count14 == 13) {
                        throw new AssertionError("Non-negative numerator " + value);
                     }

                     if (count14 != 12) {
                        if (count14 != 3) {
                           long value7;
                           long value8;
                           searchBlock: {
                              if (count14 != 10) {
                                 if (count14 == 2) {
                                    value7 = (value5 ^ 0xdbf1240522effeeL) + 0x57c48298e472e942L;
                                    value8 = (value6 ^ 0x6b28b56ee7f1088fL) + 0x299b30f4385d59beL;
                                    break searchBlock;
                                 }

                                 if (count14 != 11) {
                                    if (count14 != 1) {
                                       if (count14 != 14) {
                                          if (count14 != 5) {
                                             value4 += value2;
                                             value5 = (value5 ^ 0x4c792a58d83d3653L) - 0x8864750eb4cd9b5L;
                                             value6 = (value6 ^ 0x2ffcaa2d36b8efcfL) + 0x1ccb5c43c5126d7dL;
                                          } else if (value < 0L) {
                                             value5 = (value5 ^ 0x286234710c1f5a81L) - 0x1738e78656bbdd83L;
                                             value6 = (value6 ^ 0x65d1bd7329049ed6L) - 0x595da5fc6fd8098aL;
                                          } else {
                                             value5 = (value5 ^ -0x2422936c8ab6dacfL) + 0x4e1e8db02241d03eL;
                                             value6 = (value6 ^ 0x78009d7833145108L) + 0x13ad899e94b9d785L;
                                          }
                                       } else {
                                          value3 += value6 % 604668L ^ 238498L;
                                          value5 = (value5 ^ -0x14e8eed727db5f99L) - 0x7b01938e7a062189L;
                                          value6 = (value6 ^ 0x72e06518a13394f3L) - 0x16d7d59049550aadL;
                                       }
                                    } else if (enabled) {
                                       value5 = (value5 ^ -0x36f7e4f92b106be5L) + 0x49ae13eac77a926fL;
                                       value6 = (value6 ^ 0x128ef1ec9d228319L) - 0xf969df39f7767f0L;
                                    } else {
                                       value5 = (value5 ^ 0xa0abc4632c7ae2fL) - 0x8347d3d0400109bL;
                                       value6 = (value6 ^ -0x749d2215ae4c59a3L) - 0x9d377665532e8d8L;
                                    }
                                    continue;
                                 }
                              } else {
                                 value5 = (value5 ^ 0x7f364e191ca8437L) - 0x184841089fe8e06cL;
                                 value6 = (value6 ^ -0x4b6e99aa77aab037L) - 0x5650d2f296118afaL;
                              }

                              if (value4 < 0L) {
                                 value5 = (value5 ^ -0xf61b7c670a775adL) - 0x3dca391ec5f1480bL;
                                 value6 = (value6 ^ -0x29cffed2bdc1abafL) - 0x46b3fdd14e167ddfL;
                                 continue;
                              }

                              value7 = (value5 ^ -0x7a691f0d97ec64bdL) - 0x5a5bc2e648521ce8L;
                              value8 = (value6 ^ 0x6bb35499bfdbcc83L) + 0x7350fcc1756c97c0L;
                           }

                           if (value4 < value2) {
                              return new long[]{value4, value3};
                           }

                           value5 = (value7 ^ -0x22b0652d581b9eb1L) - 0x4b5b3985416c5185L;
                           value6 = (value8 ^ -0x11f8a6878bd7557fL) + 0x5a0a98e0a3340070L;
                        } else {
                           value3 -= value6 % 806082L ^ 665900L;
                           value5 = (value5 ^ 0x7e02a61f74c58457L) + 0x1470915ddca2ae82L;
                           value6 = (value6 ^ 0x64541fe3d3c2f6c2L) + 0x61f97ecff86602d9L;
                        }
                     } else {
                        value3 = (value >>> 1) / (value2 >>> 1);
                        value5 = (value5 ^ 0x7df4182d349e4043L) - 0x19ff17eddf36845eL;
                        value6 = (value6 ^ -0x24c5f099941c8d94L) + 0x7bd40c3138bacf13L;
                     }
                  } else if (value2 != (value6 % 749235L ^ -574500L)) {
                     value5 = (value5 ^ 0x7955055150501baaL) - 0x28e5e8a1e2ab7e6cL;
                     value6 = (value6 ^ -0x3ab2c2d0c093b9abL) + 0xc2e8a9c5994e726L;
                  } else {
                     value5 = (value5 ^ -0x6f5266bb54da95b6L) - 0x55615649417f4822L;
                     value6 = (value6 ^ -0x670dbe7e71e28b27L) + 0x6aaaa8f403d4bd8dL;
                  }
               } else {
                  value4 = value - value3 * value2;
                  value5 = (value5 ^ -0x3a358134a1531003L) + 0x5aca4ab6bdaae218L;
                  value6 = (value6 ^ 0x135a1707846e5024L) + 0x5d234eaa4de64933L;
               }
            } else {
               value4 -= value2;
               value5 = (value5 ^ 0x12391899390dc5f9L) - 0x9ec116334578b53L;
               value6 = (value6 ^ -0x5fe7c928e0985ae4L) - 0xa59fbd1ef80e364L;
            }
         } else if (enabled) {
            value5 = (value5 ^ 0x8ff357d81251765L) + 0xf3e54276c64219bL;
            value6 = (value6 ^ 0x48525f290a33a9d5L) + 0x503e8b8d3efaf4feL;
         } else {
            value5 = (value5 ^ -0x6fe45112c2959dd6L) - 0x65c44397a4d3456cL;
            value6 = (value6 ^ -0x257c441c6789ab5cL) - 0x13e34dab7bb352ecL;
         }
      }
   }

   private static SignumComparable resolveSignumComparableBySignumComparable(SignumComparable signumComparable3, long value, int count14) {
      long value2 = 0L;
      Object entry = null;
      Object entry2 = null;
      long value3 = 0x5a793f18e768e8a9L;
      value2 = 0L;
      value3 = (value3 ^ 0x577058ea6cbaa67cL) - 0x5594e92f9bf9a4b3L;
      entry = null;
      value3 = (value3 ^ -0x3eb86234bd994587L) - 0x2c3790a0713ca74fL;
      entry2 = new CurveAccumulator(signumComparable3.countArray);
      value3 = (value3 ^ 0x5106b87703e8b23bL) - 0x20e4d56e94dc3c84L;
      entry = new CurveAccumulator();
      value3 = (value3 ^ -0x5fffdf3ebfb33de7L) - 0x6de0f396ffc3cda1L;
      value2 = ((CurveAccumulator)entry2).computeTimestamp(value, (CurveAccumulator)entry);
      value3 = (value3 ^ -0x6f3cce81a237cd2bL) - 0x9b9c77ebc2852e9L;
      boolean flag;
      if (value2 != 0L) {
         flag = false;
      } else {
         value3 = (value3 ^ 0x1658ee6cb6f9f7dL) + 0x644a212c7880080cL;
         flag = true;
      }

      boolean flag2 = flag;
      int count15 = value >= 0L ? signumComparable3.count : -signumComparable3.count;
      if (!flag2 && matchesCurveAccumulator(value, count14, count15, (CurveAccumulator)entry, value2)) {
         CurveAccumulator curveAccumulator = CurveAccumulator.curveAccumulator;
         ((CurveAccumulator)entry).applyCurveAccumulator(curveAccumulator);
      }

      return ((CurveAccumulator)entry).resolveSignumComparableByIndex(count15);
   }

   private boolean isActive() {
      if (!enabled && this.getCountAsIndex() == 0) {
         throw new AssertionError();
      } else {
         return this.getCount() - this.count5 <= 0;
      }
   }

   private static SplineParser resolveSplineParserByTime(long value, long value2, int count14) {
      long value3 = 0L;
      value3 = computeTimestamp(value, value2);
      return value3 == Long.MIN_VALUE
         ? new SplineParser(SignumComparable.resolveSignumComparableByTime(value).resolveSignumComparable(value2), Long.MIN_VALUE, count14, 0)
         : resolveSplineParserByTime(value3, count14);
   }

   public SignumComparable getSignumComparable3() {
      return this.resolveSplineParser(0, 1).getSignumComparable();
   }

   public int computeCountBySplineParser(SplineParser splineParser4) {
      long value = 0L;
      long value2 = 0L;
      if (this.count5 == splineParser4.count5) {
         value = this.timestamp2;
         value2 = splineParser4.timestamp2;
         if (value != Long.MIN_VALUE && value2 != Long.MIN_VALUE) {
            return value == value2 ? 0 : (value <= value2 ? -1 : 1);
         }
      }

      int countAsIndex = this.getCountAsIndex();
      int countAsIndex2 = splineParser4.getCountAsIndex();
      if (countAsIndex == countAsIndex2) {
         if (countAsIndex != 0) {
            int count14 = this.computeCount(splineParser4);
            return countAsIndex <= 0 ? -count14 : count14;
         } else {
            return 0;
         }
      } else {
         return countAsIndex <= countAsIndex2 ? -1 : 1;
      }
   }

   private static boolean checkTime(long value, long value2, long value3, long value4) {
      return value == value3 ? value2 + Long.MIN_VALUE < value4 + Long.MIN_VALUE : value < value3;
   }

   public SplineParser resolveSplineParser(int count14, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      SplineParser splineParser4 = null;
      FG7jSMNbfywSerializable fG7jSMNbfywSerializable2 = null;
      int count15 = 0;
      int count16 = 0;
      boolean flag = false;
      int count17 = 0;
      SplineParser splineParser5 = null;
      long value = 0x1ebdc0f3cfa113f7L;
      long value2 = 0x3fd62f8248292cfaL;

      while (true) {
         int count18 = (int)(value % 28L);
         if (count18 != 18) {
            if (count18 != 11) {
               if (count18 == 16) {
                  break;
               }

               if (count18 == 21) {
                  throw new ArithmeticException("Invalid operation");
               }

               if (count18 != 8) {
                  if (count18 != 20) {
                     if (count18 != 10) {
                        if (count18 == 3) {
                           splineParser5 = splineParser5.resolveSplineParser4(splineParser4, fG7jSMNbfywSerializable2);
                           value = (value ^ 0x2fe51a09466ff25aL) + 0x7aea96eafe884f37L;
                           value2 = (value2 ^ -0x2b0a5ed9e71d9031L) - 0x11ead80567c49ac8L;
                           continue;
                        }

                        if (count18 == 19) {
                           splineParser5 = splineParser2;
                           value = (value ^ -0x6f8756036590fab9L) - 0x7fe922c35f4f5eb0L;
                           value2 = (value2 ^ -0x5cd94784c4229f02L) + 0x6f56ac8fb69fbc6aL;
                           continue;
                        }

                        if (count18 == 12) {
                           throw new ArithmeticException("Invalid operation");
                        }

                        if (count18 == 6) {
                           if (count14 <= 999999999) {
                              value = (value ^ 0x783bd330f15951aeL) + 0x117bb900a43bc672L;
                              value2 = (value2 ^ -0x7a458465415c0cb2L) - 0x54f8b97d25108e0aL;
                           } else {
                              value = (value ^ -0x737c83b22b99ae96L) + 0x6925085aa392004dL;
                              value2 = (value2 ^ -0x559d1e2c81c45966L) - 0x3d11b7bba395665cL;
                           }
                           continue;
                        }

                        if (count18 == 15) {
                           return resolveSplineParser(splineParser5, fG7jSMNbfywSerializable);
                        }

                        if (count18 == 24) {
                           fG7jSMNbfywSerializable2 = fG7jSMNbfywSerializable;
                           value = (value ^ 0x2e2bab31d7e442aaL) + 0x191c5f1ed5750403L;
                           value2 = (value2 ^ -0x37a7145d8f71542dL) - 0x512db4a76ebef999L;
                           continue;
                        }

                        if (count18 == 27) {
                           if (fG7jSMNbfywSerializable.count2 <= 0) {
                              value = (value ^ -0x2fe25ae6d2f0f8abL) + 0x6e3867a6f73485a5L;
                              value2 = (value2 ^ 0x4af5c129073b9297L) + 0x313c3d3e7d1de872L;
                           } else {
                              value = (value ^ -0x76ed3aca3319d05cL) + 0x483dcc9bfcc04e13L;
                              value2 = (value2 ^ -0x7059c99bca39195cL) - 0x450b112463a5174dL;
                           }
                           continue;
                        }

                        if (count18 != 14) {
                           if (count18 != 1) {
                              if (count18 != 23) {
                                 if (count18 != 26) {
                                    if (count18 != 17) {
                                       if (count18 == 9) {
                                          return this.resolveSplineParser4(count14);
                                       }

                                       if (count18 != 22) {
                                          if (count18 != 25) {
                                             if (count18 != 5) {
                                                if (count18 != 13) {
                                                   if (count18 != 4) {
                                                      if (count18 != 2) {
                                                         if (count18 != 7) {
                                                            flag = false;
                                                            value = (value ^ -0x7f797356e82d080cL) - 0x41f28d784cf68b1fL;
                                                            value2 = (value2 ^ 0x6152a37e5505533cL) + 0x2d0f57a469087237L;
                                                         } else {
                                                            long value3 = (value ^ 0x79dc114b135d60caL) + 0x3886a20c8b2fc56dL;
                                                            long value4 = (value2 ^ -0x1a0ef00f9e8a0b04L) + 0x7a96046a6c43c3b6L;
                                                            if (count14 >= 0) {
                                                               value = (value3 ^ 0x543474d4772d007fL) + 0x5066db580faefcbeL;
                                                               value2 = (value4 ^ -0x23d0bedabede1d0aL) - 0x3ff530468a7230c0L;
                                                            } else {
                                                               value = (value3 ^ -0x431617ad45460433L) + 0x2ea5d827b207e92eL;
                                                               value2 = (value4 ^ 0x2c81432ce725f09eL) + 0xb269ac3b52862aeL;
                                                            }
                                                         }
                                                      } else {
                                                         splineParser4 = this;
                                                         value = (value ^ 0x2b8e6daab301c133L) - 0xaf4cc9994523d81L;
                                                         value2 = (value2 ^ 0x7bdd16bce59fa777L) + 0x56a740b3c42123b6L;
                                                      }
                                                   } else if (count17 != 31) {
                                                      long value5 = (value ^ 0xcea1f4a0db2a6ccL) - 0x180febf51daf6cf9L;
                                                      long value6 = (value2 ^ -0x109a89f7becd4b9aL) + 0x4c07f351295c84d1L;
                                                      if (!flag) {
                                                         value = (value5 ^ -0x4e8a067ca17d5566L) + 0x70ee795455698fbdL;
                                                         value2 = (value6 ^ -0x61a2a7a5564ecdacL) + 0x3632350fb3fffd0eL;
                                                      } else {
                                                         value = (value5 ^ -0x330ad720d7ef7eeL) - 0x38cbd5bd35abe1abL;
                                                         value2 = (value6 ^ -0x536f1185d55083bdL) - 0x191e4b04c07e1af6L;
                                                      }
                                                   } else {
                                                      value = (value ^ 0xf6dbfb2d6d39725L) - 0x17968c1c46ce5ce2L;
                                                      value2 = (value2 ^ 0x1b586f82ca42fecaL) - 0x481cf6296193408bL;
                                                   }
                                                } else {
                                                   count17 = 1;
                                                   value = (value ^ 0x6a4f3576956f5975L) + 0x21a609711819288eL;
                                                   value2 = (value2 ^ 0x52acde1824a76968L) + 0x45fb14c8191da03bL;
                                                }
                                             } else {
                                                splineParser5 = splineParser2.resolveSplineParser2(splineParser5, fG7jSMNbfywSerializable2);
                                                value = (value ^ 0x2ffc6990a42510e4L) + 0x37f2a839b45848a2L;
                                                value2 = (value2 ^ -0x54da00c4333d5d34L) - 0x62aa4038dcd94492L;
                                             }
                                          } else {
                                             flag = true;
                                             value = (value ^ -0x7ba0ec8b9331756L) + 0x3e0e08618b7ba3a0L;
                                             value2 = (value2 ^ 0x3451a63a1345dea0L) - 0x4eaa69141bc34549L;
                                          }
                                       } else {
                                          count16 = computeCount4((long)count15);
                                          value = (value ^ 0x46e63870f54fcdb8L) - 0x65ddd5dc7d41ab55L;
                                          value2 = (value2 ^ 0x3dbfefca6a0c0240L) - 0x2aee5c2ac66b12a5L;
                                       }
                                    } else if (count16 <= fG7jSMNbfywSerializable.count2) {
                                       value = (value ^ 0x3ae7f773cc1ab384L) + 0x66a0eb2a6cb1977L;
                                       value2 = (value2 ^ 0x76abee3ee8313b6fL) - 0x23c5936b7fbc5dd4L;
                                    } else {
                                       value = (value ^ 0x6ca89c164720e78fL) - 0x5dce9b4f24e53b4eL;
                                       value2 = (value2 ^ 0x28496f4b6d48f7d6L) + 0x2f8360741480aee8L;
                                    }
                                 } else {
                                    splineParser5 = splineParser5.resolveSplineParser4(splineParser5, fG7jSMNbfywSerializable2);
                                    value = (value ^ -0x181811361104e477L) - 0x2d809843bf632b89L;
                                    value2 = (value2 ^ 0x65adc0f3b4dc6d56L) + 0x39b76e6642c0c0a7L;
                                 }
                              } else if (count15 >= 0) {
                                 value = (value ^ 0x2c3585c03fde5156L) + 0x2f784e7d2b12138bL;
                                 value2 = (value2 ^ 0x679a5221b4cc07b5L) + 0x44f9bb43d0c82d1fL;
                              } else {
                                 value = (value ^ 0x568cc249bbc2da85L) - 0x1f986641d377ef8dL;
                                 value2 = (value2 ^ -0x4d0b8ccabbced927L) + 0x70fba424dc96f125L;
                              }
                           } else {
                              count15 = Math.abs(count14);
                              value = (value ^ 0x66cb91b95c92a19eL) + 0x3ce13061f889d580L;
                              value2 = (value2 ^ 0x4ab850a224b32d39L) - 0xf8c7235f5f48cf8L;
                           }
                           continue;
                        }

                        count17++;
                        value = (value ^ 0x7bc730391aa93c07L) - 0xc02174c21117bL;
                        value2 = (value2 ^ 0x1c4eb33b6b2fe6f6L) + 0x336cd2c973879102L;
                     }

                     count15 += count15;
                     value = (value ^ -0x5cc710d2b7dfc6cbL) - 0x20e84ce40f9574e0L;
                     value2 = (value2 ^ -0xb798383d8ced8cdL) + 0x36c42ef7ce8bf886L;
                  } else {
                     fG7jSMNbfywSerializable2 = new FG7jSMNbfywSerializable(fG7jSMNbfywSerializable.count2 + count16 + 1, fG7jSMNbfywSerializable.combatScaleProperty);
                     value = (value ^ -0x753ca33e1dd76afdL) + 0x7102c0000fea4768L;
                     value2 = (value2 ^ 0x39fca77714ae532L) + 0x7469efdd98c00c5aL;
                  }
               } else if (count14 < -999999999) {
                  value = (value ^ -0x7ccb8a8568a97a8eL) - 0x3b402715c0a5fa45L;
                  value2 = (value2 ^ 0x5047321fe01b565cL) - 0x2e1063902cdd75b7L;
               } else {
                  value = (value ^ 0x488bc4e27b3adcc7L) + 0x5a0753b9b522debL;
                  value2 = (value2 ^ 0x50fd3065cc9b78d3L) - 0x57330e8b228f8b52L;
               }
            } else if (fG7jSMNbfywSerializable.count2 != 0) {
               value = (value ^ 0x51b1e625a02650b2L) - 0x4cbc24811728ea91L;
               value2 = (value2 ^ -0x62770afe4a0a164dL) + 0x781188d14ab5c9eaL;
            } else {
               value = (value ^ 0xc6eaea91094633eL) + 0x35f9cc81066d9e40L;
               value2 = (value2 ^ -0x6967e43c9757dc33L) - 0x2c4c2f5bcd548254L;
            }
         } else {
            if (count14 == 0) {
               break;
            }

            value = (value ^ 0x562047079b3be864L) - 0x301c4d19351377ccL;
            value2 = (value2 ^ -0xfdafa55a1c27350L) - 0x74b4512bc2ee92adL;
         }
      }

      return splineParser2;
   }

   private static SplineParser resolveSplineParser(long value, int count14, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      long value2 = value;
      int count15 = count14;
      int count16 = 0;
      int count17 = 0;
      count16 = fG7jSMNbfywSerializable.count2;
      if (count16 > 0 && count16 < 19) {
         count17 = computeCount4(value);

         for (int index = count17 - count16; index > 0; index = count17 - count16) {
            count15 = computeCountByTime((long)count15 - (long)index);
            value2 = computeTimestamp(value2, timestampArray2[index], fG7jSMNbfywSerializable.combatScaleProperty.count);
            count17 = computeCount4(value2);
         }

         return resolveSplineParser(value2, count15, count17);
      } else {
         return resolveSplineParserByTime(value, count14);
      }
   }

   private static SplineParser resolveSplineParser(long value, int count14, SignumComparable signumComparable3, int count15) {
      int count16 = 0;
      long value3 = 0L;
      count16 = count14;
      value3 = (long)count14 - (long)count15;
      boolean flag = Long.signum(value) == signumComparable3.count;
      SignumComparable signumComparable4;
      if (value3 >= 0L) {
         int count17 = computeCountBySignumComparable(signumComparable3, value3);
         SignumComparable signumComparable5 = resolveSignumComparable(signumComparable3, count17);
         signumComparable4 = signumComparable5.resolveSignumComparable2(value);
      } else {
         int count18 = computeCount(value, -value3);
         count16 = count15;
         long value4 = computeTimestamp(value, count18);
         if (value4 != Long.MIN_VALUE) {
            signumComparable4 = signumComparable3.resolveSignumComparable2(value4);
         } else {
            SignumComparable signumComparable6 = resolveSignumComparable(value, count18);
            signumComparable4 = signumComparable3.resolveSignumComparable5(signumComparable6);
         }
      }

      SplineParser splineParser4;
      if (!flag) {
         byte byteValue = 0;
         splineParser4 = resolveSplineParser(signumComparable4, count16, byteValue);
      } else {
         splineParser4 = new SplineParser(signumComparable4, Long.MIN_VALUE, count16, 0);
      }

      return splineParser4;
   }

   private static SignumComparable resolveSignumComparable(SignumComparable signumComparable3, SignumComparable signumComparable4, int count14) {
      boolean flag = false;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      long value = 0x1e2733dfeebb99c1L;
      entry = new CurveAccumulator(signumComparable3.countArray);
      value = (value ^ 0x1a331bd84a4d3231L) + 0x532726f468dc5f7bL;
      entry2 = new CurveAccumulator();
      value = (value ^ -0x404b31533a0fed3eL) + 0x71c108ab1b63e2b0L;
      entry3 = new CurveAccumulator(signumComparable4.countArray);
      value = (value ^ 0x1e6841c74ad038afL) + 0x738c83a282eda25dL;
      entry4 = ((CurveAccumulator)entry).resolveCurveAccumulator4((CurveAccumulator)entry3, (CurveAccumulator)entry2);
      value = (value ^ -0x1fcd90e09289dd25L) + 0x550ae3bef36f6114L;
      flag = ((CurveAccumulator)entry4).isActive2();
      value = (value ^ -0x4d3eec7afc4534a9L) + 0x50ee3fd3bf7edda1L;
      byte byteValue;
      if (signumComparable3.count == signumComparable4.count) {
         byteValue = 1;
      } else {
         value = (value ^ -0x52307b57bd59384dL) + 0x426ea1915f3f644bL;
         byteValue = -1;
      }

      byte byteValue2 = byteValue;
      if (!flag && matchesCurveAccumulator((CurveAccumulator)entry3, count14, byteValue2, (CurveAccumulator)entry2, (CurveAccumulator)entry4)) {
         CurveAccumulator curveAccumulator = CurveAccumulator.curveAccumulator;
         ((CurveAccumulator)entry2).applyCurveAccumulator(curveAccumulator);
      }

      return ((CurveAccumulator)entry2).resolveSignumComparableByIndex(byteValue2);
   }

   public SplineParser resolveSplineParser4(SplineParser splineParser4, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count14 = 0;
      if (fG7jSMNbfywSerializable.count2 != 0) {
         long value = (long)this.count5 + (long)splineParser4.count5;
         count14 = this.computeCount2(value);
         if (this.timestamp2 == Long.MIN_VALUE) {
            if (splineParser4.timestamp2 == Long.MIN_VALUE) {
               SignumComparable signumComparable3 = splineParser4.signumComparable2;
               return resolveSplineParser(this.signumComparable2, signumComparable3, count14, fG7jSMNbfywSerializable);
            } else {
               return resolveSplineParser(splineParser4.timestamp2, this.signumComparable2, count14, fG7jSMNbfywSerializable);
            }
         } else {
            return splineParser4.timestamp2 == Long.MIN_VALUE
               ? resolveSplineParser(this.timestamp2, splineParser4.signumComparable2, count14, fG7jSMNbfywSerializable)
               : resolveSplineParser(this.timestamp2, splineParser4.timestamp2, count14, fG7jSMNbfywSerializable);
         }
      } else {
         return this.resolveSplineParser7(splineParser4);
      }
   }

   public SplineParser resolveSplineParser(SplineParser splineParser4, int count14, int count15) {
      if (count15 < 0 || count15 > 7) {
         throw new IllegalArgumentException("Invalid rounding mode");
      } else if (this.timestamp2 == Long.MIN_VALUE) {
         if (splineParser4.timestamp2 == Long.MIN_VALUE) {
            int count16 = splineParser4.count5;
            SignumComparable signumComparable3 = splineParser4.signumComparable2;
            int count17 = this.count5;
            return resolveSplineParser(this.signumComparable2, count17, signumComparable3, count16, count14, count15);
         } else {
            int count18 = splineParser4.count5;
            long timestamp6 = splineParser4.timestamp2;
            int count19 = this.count5;
            return resolveSplineParser(this.signumComparable2, count19, timestamp6, count18, count14, count15);
         }
      } else if (splineParser4.timestamp2 == Long.MIN_VALUE) {
         int count20 = splineParser4.count5;
         return resolveSplineParser(this.timestamp2, this.count5, splineParser4.signumComparable2, count20, count14, count15);
      } else {
         return resolveSplineParser(this.timestamp2, this.count5, splineParser4.timestamp2, splineParser4.count5, count14, count15);
      }
   }

   public SplineParser getSplineParser3() {
      return resolveSplineParser(1L, this.getCount5(), 1);
   }

   private static void applySplineParserArray(SplineParser[] splineParserArray3) {
      long value = 0x14df72aa512ecc6eL;
      long value2 = -0x2ce787c2b281596bL;

      while (true) {
         int count14 = (int)(value % 6L);
         if (count14 != 4) {
            if (count14 != 2) {
               if (count14 != 1 && count14 != 3) {
                  if (count14 != 0) {
                     SplineParser splineParser4 = splineParserArray3[0];
                     byte byteValue = 7;
                     int count15 = splineParserArray3[1].count5;
                     splineParserArray3[0] = splineParser4.resolveSplineParser(count15, byteValue);
                  } else {
                     SplineParser splineParser5 = splineParserArray3[1];
                     byte byteValue2 = 7;
                     int count16 = splineParserArray3[0].count5;
                     splineParserArray3[1] = splineParser5.resolveSplineParser(count16, byteValue2);
                  }
               }
               break;
            }

            if (splineParserArray3[0].count5 != splineParserArray3[1].count5) {
               long value3 = (value ^ -0x16a7ded10c548d43L) + 0x1998b4b6906fd905L;
               long value4 = (value2 ^ -0x796d0181d9f7211eL) - 0x7465b6f64f5720c2L;
               if (splineParserArray3[0].count5 < splineParserArray3[1].count5) {
                  value = (value3 ^ 0x59777ca263ca17d7L) + 0x1c656c150cec2bacL;
                  value2 = (value4 ^ -0x42ed8293313572a7L) + 0x4635dd72945e9834L;
                  continue;
               }

               long value5 = (value3 ^ -0x5cb6f2cefb0a382bL) - 0x35190b0f3dc90d14L;
               long value6 = (value4 ^ 0x73b5a2b2ba61af99L) + 0x391505a5c4051644L;
               if (splineParserArray3[1].count5 < splineParserArray3[0].count5) {
                  value = (value5 ^ -0x62e194681f96ceeaL) - 0x716a372e9548871fL;
                  value2 = (value6 ^ -0x66b5ff62e35fbd16L) + 0x245a768c62991960L;
                  continue;
               }
               break;
            }
         }

         return;
      }
   }

   public SplineParser resolveSplineParser2(FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      for (long index = 0x1d7ba24aa116e3cdL; (int)(index % 3L) == 1; index = (index ^ 0x21456a558638cda7L) - 0x23cb2924cf5b22d8L) {
         if (fG7jSMNbfywSerializable.count2 == 0) {
            return this;
         }
      }

      return resolveSplineParser(this, fG7jSMNbfywSerializable);
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, int count14, SignumComparable signumComparable4, int count15, long value, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count16 = count15;
      int count17 = 0;
      int count18 = 0;
      SplineParser splineParser4 = null;
      int count19 = 0;
      int count20 = 0;
      SignumComparable signumComparable5 = null;
      SignumComparable signumComparable6 = null;
      int count21 = 0;
      long value3 = 0x671508ed6f0fc249L;
      long value4 = -0x5f8899931bb8c2a0L;

      while (true) {
         int count22 = (int)(value3 % 13L);
         if (count22 != 5) {
            if (count22 == 6) {
               break;
            }

            if (count22 != 2) {
               if (count22 == 3) {
                  value4 = (value4 ^ 0x24fd606b25328866L) + 0x5bb30d2f4f0e9acL;
                  break;
               }

               if (count22 != 7) {
                  if (count22 != 9) {
                     if (count22 != 10) {
                        if (count22 == 1) {
                           signumComparable5 = resolveSignumComparable(signumComparable3, count20);
                           value3 = (value3 ^ -0x3021e5133e3a120cL) - 0x7010a9ccf4598a2dL;
                           value4 = (value4 ^ -0x1aa478f1fd00458dL) + 0x730bae3ae0f4bf46L;
                           continue;
                        }

                        if (count22 == 8) {
                           signumComparable6 = resolveSignumComparable(signumComparable4, count21);
                           value3 = (value3 ^ 0xaf9d4a6614b27ddL) + 0xfa59df106da8387L;
                           value4 = (value4 ^ 0x77098eb62940b305L) - 0x2fdf705a10b90df2L;
                           continue;
                        }

                        if (count22 != 0) {
                           if (count22 != 12) {
                              if (count22 != 4) {
                                 count20 = computeCountByTime((long)count17 + (long)count16 - (long)count14);
                                 value3 = (value3 ^ 0x2c9e4ab57cab8587L) - 0x22192ce7f88e695cL;
                                 value4 = (value4 ^ 0x312746cadb6610a0L) + 0x2f672e1d0f11bd70L;
                              } else if (computeCountByTime((long)count17 + (long)count16 - (long)count14) <= 0) {
                                 long value5 = (value3 ^ -0x25322cd59dc06cb7L) + 0x57589f16ec9ff251L;
                                 long value6 = (value4 ^ -0x37047260ba03108dL) + 0x218a606e95f4ccfcL;
                                 count20 = computeCountByTime((long)count14 - (long)count17);
                                 value3 = (value5 ^ 0x2091dc0ec46d3695L) - 0x6479b6f1f242c1fL;
                                 value4 = (value6 ^ -0x7e69f434be2eb550L) - 0x10aae8c13981810dL;
                              } else {
                                 value3 = (value3 ^ 0xd840527105a6fc2L) - 0x57951c6824b66710L;
                                 value4 = (value4 ^ 0x65d253f5392372cdL) - 0x28126943bdd96f8aL;
                              }
                           } else {
                              count21 = computeCountByTime((long)count20 - (long)count16);
                              value3 = (value3 ^ 0x7ff497b22f282e41L) - 0x56132817fec015e5L;
                              value4 = (value4 ^ 0x4b12e99ca6b3d0eaL) - 0x44f31b707b6a58ecL;
                           }
                           continue;
                        }

                        count16--;
                     } else {
                        if (computeCount(signumComparable3, count14, signumComparable4, count16) > 0) {
                           value3 = (value3 ^ 0x4b9a88c11fa6467aL) + 0x33a4ad3dacdb2c86L;
                           value4 = (value4 ^ 0x7acfa433f8227d72L) + 0x36ff1f096446eee6L;
                           continue;
                        }

                        value3 = (value3 ^ -0x7d92435bb13d045dL) + 0x7abb7920fbb776cfL;
                        value4 = (value4 ^ 0x22eae6ad62324adfL) - 0x70e59f5905c948c7L;
                     }

                     count17 = fG7jSMNbfywSerializable.count2;
                     value3 = (value3 ^ -0x22d25352f881f34cL) + 0x7f01196fe31390b7L;
                     value4 = (value4 ^ 0x56b98c26c092017cL) + 0x41c65182204f37a9L;
                  } else {
                     int count23 = computeCountByTime(value);
                     splineParser4 = resolveSplineParser(signumComparable5, signumComparable4, count19, count18, count23);
                     value3 = (value3 ^ -0x453f2005961d2d97L) + 0x336ea23d00575103L;
                     value4 = (value4 ^ -0x1c513c13715c19b0L) + 0xfaa922fc69a86e1L;
                  }
               } else {
                  int count24 = computeCountByTime(value);
                  splineParser4 = resolveSplineParser(signumComparable3, signumComparable6, count19, count18, count24);
                  value3 = (value3 ^ 0x2746379e9eebc14bL) - 0x37bc44eaae3a7d4cL;
                  value4 = (value4 ^ 0x79394d3d3ce65273L) + 0xf3927ef660bc3dL;
               }
            } else {
               count18 = fG7jSMNbfywSerializable.combatScaleProperty.count;
               value3 = (value3 ^ 0x34eda62c722c3b93L) + 0x4c054d27e56c3d62L;
               value4 = (value4 ^ -0x220dcded4841419aL) + 0x56cc0aa87ff626d7L;
            }
         } else {
            count19 = computeCountByTime(value + (long)count16 - (long)count14 + (long)count17);
            value3 = (value3 ^ -0x55990711ab26d6d4L) + 0x64485ec50d7c123aL;
            value4 = (value4 ^ -0x7ed040c2af14c2c2L) + 0xde2092d6f3deaeL;
         }
      }

      return resolveSplineParser(splineParser4, fG7jSMNbfywSerializable);
   }

   public SplineParser resolveSplineParser5(SplineParser splineParser4) {
      return this.computeCountBySplineParser(splineParser4) <= 0 ? this : splineParser4;
   }

   private static SplineParser resolveSplineParser(SignumComparable signumComparable3, SignumComparable signumComparable4, int count14, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      return resolveSplineParser(signumComparable3.resolveSignumComparable4(signumComparable4), count14, fG7jSMNbfywSerializable);
   }

   private static SplineParser resolveSplineParser(long value, long value2, long value3, int count14, int count15, int count16) {
      int count17 = 0;
      long value4 = 0L;
      long value5 = 0L;
      long value6 = 0L;
      long value7 = 0L;
      long value8 = 0L;
      long value9 = 0L;
      long value10 = 0L;
      long value11 = 0L;
      long value12 = 0L;
      long value13 = 0L;
      long value14 = 0L;
      count17 = Long.signum(value) * Long.signum(value2) * Long.signum(value3);
      long value15 = Math.abs(value);
      long value16 = Math.abs(value2);
      long value17 = Math.abs(value3);
      value4 = value15 >>> 32;
      value5 = value15 & 4294967295L;
      value6 = value16 >>> 32;
      value7 = value16 & 4294967295L;
      value8 = value5 * value7;
      value9 = value8 & 4294967295L;
      value10 = value8 >>> 32;
      value8 = value4 * value7 + value10;
      value10 = value8 & 4294967295L;
      value11 = value8 >>> 32;
      value8 = value5 * value6 + value10;
      value10 = value8 & 4294967295L;
      value11 += value8 >>> 32;
      value12 = value11 >>> 32;
      value11 &= 4294967295L;
      value8 = value4 * value6 + value11;
      value11 = value8 & 4294967295L;
      value12 = (value8 >>> 32) + value12 & 4294967295L;
      value13 = computeTimestampByTime(value12, value11);
      value14 = computeTimestampByTime(value10, value9);
      return resolveSplineParser(value13, value14, value17, count17, count14, count15, count16);
   }

   public SplineParser resolveSplineParserByIndex(int count14) {
      int count15 = 0;
      Object entry = null;
      long value = (long)this.count5 + (long)count14;
      count15 = this.computeCount2(value);
      entry = new SplineParser(this.signumComparable2, this.timestamp2, count15, 0);
      SplineParser splineParser4;
      if (((SplineParser)entry).count5 >= 0) {
         splineParser4 = (SplineParser)entry;
      } else {
         byte byteValue = 7;
         byte byteValue2 = 0;
         splineParser4 = ((SplineParser)entry).resolveSplineParser(byteValue2, byteValue);
      }

      return splineParser4;
   }

   public long getTimestamp() {
      Object entry = null;
      if (this.timestamp2 != Long.MIN_VALUE && this.count5 == 0) {
         return this.timestamp2;
      } else if (this.getCountAsIndex() != 0) {
         if (!this.isActive()) {
            if (this.getCount() - this.count5 <= 19) {
               byte byteValue = 7;
               byte byteValue2 = 0;
               entry = this.resolveSplineParser(byteValue2, byteValue);
               if (((SplineParser)entry).getCount() >= 19) {
                  OverflowHelper.applySplineParser((SplineParser)entry);
               }

               return ((SplineParser)entry).getSignumComparable().getTimestamp();
            } else {
               throw new ArithmeticException("Overflow");
            }
         } else {
            throw new ArithmeticException("Rounding necessary");
         }
      } else {
         return 0L;
      }
   }

   private static SplineParser resolveSplineParser(long value, long value2, int count14, int count15, int count16) {
      long value3 = 0L;
      long value4 = 0L;
      value3 = value / value2;
      if (count15 == 1 && count14 == count16) {
         return resolveSplineParserByTime(value3, count14);
      } else {
         value4 = value % value2;
         int count17 = value < 0L != value2 < 0L ? -1 : 1;
         if (value4 == 0L) {
            return count16 == count14 ? resolveSplineParserByTime(value3, count14) : resolveSplineParser(value3, count14, (long)count16);
         } else {
            boolean flag = checkTime(value2, count15, count17, value3, value4);
            return resolveSplineParserByTime(!flag ? value3 : value3 + (long)count17, count14);
         }
      }
   }

   public SplineParser getSplineParserAsSplineParser() {
      return this.getCountAsIndex() < 0 ? this.getSplineParser() : this;
   }

   private static boolean checkTime(long value, long value2) {
      return value + Long.MIN_VALUE >= value2 + Long.MIN_VALUE;
   }

   public int getCountAsIndex() {
      return this.timestamp2 != Long.MIN_VALUE ? Long.signum(this.timestamp2) : this.signumComparable2.getCount8();
   }

   private static SplineParser resolveSplineParser(long value, SignumComparable signumComparable3, int count14, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      return value != 0L ? resolveSplineParser(signumComparable3.resolveSignumComparable(value), count14, fG7jSMNbfywSerializable) : resolveSplineParser3(count14);
   }

   private static int computeCount(long value, long value2) {
      int count14 = 0;
      count14 = (int)value2;
      if ((long)count14 != value2) {
         count14 = value2 <= 2147483647L ? Integer.MIN_VALUE : Integer.MAX_VALUE;
         if (value != 0L) {
            throw new ArithmeticException(count14 <= 0 ? "Overflow" : "Underflow");
         }
      }

      return count14;
   }

   private static long computeTimestamp(long value, long value2) {
      long value3 = 0L;
      long value4 = 0L;
      long value5 = 0L;
      value3 = value * value2;
      value4 = Math.abs(value);
      value5 = Math.abs(value2);
      return (value4 | value5) >>> 31 != 0L && value2 != 0L && value3 / value2 != value ? Long.MIN_VALUE : value3;
   }

   public SplineParser resolveSplineParser2(SplineParser splineParser4) {
      return this.computeCountBySplineParser(splineParser4) >= 0 ? this : splineParser4;
   }

   private static boolean checkTime(long value, int count14, int count15, long value2, long value3) {
      int count16 = 0;
      long value4 = 0x6fa09cb7b967b10aL;
      long value5 = -0x326f929028cffe45L;

      while (true) {
         int count17 = (int)(value4 % 8L);
         if (count17 == 1) {
            break;
         }

         if (count17 != 3) {
            if (count17 != 4) {
               if (count17 != 2) {
                  if (count17 != 7) {
                     if (count17 != 5) {
                        if (count17 != 0) {
                           value4 = (value4 ^ 0x2bce25e4ec02206L) + 0x7bfb2d561aa5ae8L;
                           value5 = (value5 ^ 0x75da259f3f870a89L) + 0xc948a6bca9931bcL;
                        }

                        return checkIndex(count14, count15, count16, (value2 & (value5 % 139729L ^ -71716L)) != 0L);
                     }

                     count16 = 1;
                     value4 = (value4 ^ 0x60bf8b9acb6beb09L) - 0x1732594d15f82c1eL;
                     value5 = (value5 ^ -0x40fecca0ef3d276dL) + 0x6d805cb4f1f688bfL;
                  } else if (value3 <= 0x3fffffffffffffffL) {
                     long value6 = (value4 ^ 0x10cb210f25166019L) - 0x2aa4d75efc276e50L;
                     long value7 = (value5 ^ -0x33c25c1cc00e304L) - 0x6b74777516f4f4d4L;
                     count16 = computeCount3(2L * value3, value);
                     value4 = (value6 ^ -0x2ab0697a691bfbf9L) + 0x3ecd26ae398e7ce7L;
                     value5 = (value7 ^ 0x49bc0e5e0b6c7c73L) - 0x2f518f55087c38eaL;
                  } else {
                     value4 = (value4 ^ 0x66753ce708203d52L) + 0x2371f440c05ab010L;
                     value5 = (value5 ^ 0x12626ac64d484bd8L) - 0x5697182f5b99fae2L;
                  }
               } else if (enabled) {
                  value4 = (value4 ^ -0x418baed626f756cL) + 0x6d31fe56b9e016e5L;
                  value5 = (value5 ^ 0x3c8dc97567a06dedL) + 0x217b9af8604b300L;
               } else {
                  value4 = (value4 ^ -0x3c0ebd5dc29a9c96L) + 0x5920c82420a3aedcL;
                  value5 = (value5 ^ 0x2876e3350c260bbbL) + 0x8f291e69c7a8a8eL;
               }
            } else {
               if (value3 == 0L) {
                  break;
               }

               value4 = (value4 ^ -0x311810e5421ed93L) + 0x7dcff33cf5ebf32L;
               value5 = (value5 ^ 0x22af48782b265535L) + 0x26bef590d9de5d9bL;
            }
         } else if (value3 <= -0x4000000000000000L) {
            value4 = (value4 ^ 0x38afd86b1d991fceL) + 0x2303563b1f9782e8L;
            value5 = (value5 ^ 0x68e4e9eba30fc8e4L) - 0x63c6f7709059fc42L;
         } else {
            value4 = (value4 ^ -0x178effc08e009746L) + 0x760975a77b82e33eL;
            value5 = (value5 ^ 0x139f89b3712848d3L) - 0x43e716531a2ab9fbL;
         }
      }

      throw new AssertionError();
   }

   public SignumComparable getSignumComparable2() {
      return this.getSignumComparable();
   }

   private int computeCount2(long value) {
      int count14 = 0;
      long value2 = 0x3ca67e170e403a96L;
      long value3 = -0x3c05f07b5c1fec54L;
      count14 = (int)value;
      value2 = (value2 ^ -0x54845383733170a4L) - 0x3c36b8d039281c7aL;
      value3 = (value3 ^ -0x7c79745a5dffad89L) + 0x54ff726461b8eabeL;
      if ((long)count14 != value) {
         value2 = (value2 ^ 0x6135a8db67e6f980L) - 0x3ada3470e4310d5L;
         value3 = (value3 ^ 0x21e42c0869acc5efL) + 0x7e52c2337698a296L;
         int count15;
         if (value <= 2147483647L) {
            count15 = Integer.MIN_VALUE;
         } else {
            value2 = (value2 ^ 0x425a04b375fc8c6aL) - 0x7057f57bcf7f5230L;
            value3 = (value3 ^ 0x7396b169fd685b4eL) + 0x297bc4d4b163da81L;
            count15 = Integer.MAX_VALUE;
         }

         count14 = count15;
         if (this.timestamp2 != 0L) {
            SignumComparable signumComparable3 = this.signumComparable2;
            if (this.signumComparable2 == null || signumComparable3.getCount8() != 0) {
               throw new ArithmeticException(count14 <= 0 ? "Overflow" : "Underflow");
            }
         }
      }

      return count14;
   }

   private static SplineParser resolveSplineParser(long value, int count14, SignumComparable signumComparable3, int count15, int count16, int count17) {
      int count19 = 0;
      int count20 = 0;
      Object entry = null;
      if (computeCount(value, (long)count16 + (long)count15) <= count14) {
         long value3 = (long)count14 - (long)count16;
         long value4 = value3;
         count19 = computeCountBySignumComparable(signumComparable3, value4);
         count20 = count19 - count15;
         entry = resolveSignumComparable(signumComparable3, count20);
         return resolveSplineParser(SignumComparable.resolveSignumComparableByTime(value), (SignumComparable)entry, count16, count17, count16);
      } else {
         count19 = count16 + count15;
         count20 = count19 - count14;
         entry = resolveSignumComparable(value, count20);
         return resolveSplineParser((SignumComparable)entry, signumComparable3, count16, count17, count16);
      }
   }

   private static SignumComparable resolveSignumComparableByIndex(int count14) {
      Object entry = null;
      if (count14 >= 0) {
         if (count14 >= count6) {
            return SignumComparable.signumComparable3.resolveSignumComparable8(count14);
         } else {
            entry = signumComparableArray;
            return (SignumComparable)(count14 >= ((Object[])entry).length ? resolveSignumComparable2(count14) : ((Object[])entry)[count14]);
         }
      } else {
         return SignumComparable.signumComparable5;
      }
   }

   public static SplineParser resolveSplineParser(double offset) {
      return new SplineParser(Double.toString(offset));
   }

   private static int computeCount(long value, int count14, long value2, int count15) {
      long value3 = value;
      long value4 = value2;
      int count16 = 0;
      long value5 = 0x5266167b93770dd8L;
      long value6 = 0xf4b32df607a3895L;

      int count17;
      while (true) {
         int count18 = (int)(value5 % 8L);
         if (count18 != 6) {
            if (count18 == 5) {
               value3 = computeTimestamp(value3, -count16);
               value5 = (value5 ^ 0x7234a5a4fd56d263L) - 0x586156aa0d757682L;
               value6 = (value6 ^ 0xcd12b9ff752b2b4L) - 0x550000183523aa4cL;
            } else if (count18 != 2) {
               if (count18 != 4) {
                  if (count18 == 3) {
                     if (count16 == 0) {
                        value5 = (value5 ^ 0x5acc591c23c3026dL) + 0xbf1c3753f4b1c8bL;
                        value6 = (value6 ^ 0x7200739b9948110L) + 0x7d85955f83a4164aL;
                     } else {
                        value5 = (value5 ^ 0x1f97c34b993f61d3L) + 0x110c4902f395d066L;
                        value6 = (value6 ^ -0x166c95370dda8579L) + 0x15fd18a54034f6abL;
                     }
                     continue;
                  }

                  if (count18 != 1) {
                     if (count18 == 0) {
                        count16 = count14 - count15;
                        value5 = (value5 ^ -0x638fe4d925c1b157L) + 0x3b8706b55c63c4f2L;
                        value6 = (value6 ^ 0x68ea381712171101L) - 0x5861e6135a794d61L;
                        continue;
                     }

                     count17 = computeCount3(value3, value4);
                     break;
                  }
               } else {
                  value5 = (value5 ^ 0x7ffe722c54f6e0a7L) - 0x972c8b437029eb2L;
                  value6 = (value6 ^ 0x561e769884f8e169L) + 0x6ff9c108462dd2f7L;
               }

               if (value3 == Long.MIN_VALUE) {
                  return 1;
               }

               value5 = (value5 ^ 0x2075565787fa5b13L) - 0x3a42b64145c787d8L;
               value6 = (value6 ^ -0x1caed2cec0df5732L) - 0x15ceb43a71f1ef46L;
            } else {
               if (value4 == Long.MIN_VALUE) {
                  count17 = -1;
                  break;
               }

               value5 = (value5 ^ 0x7bae01c24653e15bL) + 0x2da59db1c9d6ddaeL;
               value6 = (value6 ^ 0x190255b8dc6b5529L) - 0x48b4095381fec3c9L;
            }
         } else if (count16 >= 0) {
            long value7 = (value5 ^ 0x7977d206f4fe6bf8L) - 0x4715473f1c892c02L;
            long value8 = (value6 ^ -0x370a271479ab0d4bL) + 0xf57c1b2cec5d135L;
            value4 = computeTimestamp(value4, count16);
            value5 = (value7 ^ -0x290a4c2db319ba95L) - 0x627b08461ef239eeL;
            value6 = (value8 ^ 0x546d798bbbb622a8L) + 0x6dcabffb5d780fb6L;
         } else {
            value5 = (value5 ^ 0x55d5f923814021b1L) - 0x5529351e68d3cd9aL;
            value6 = (value6 ^ 0x1de990b010aecdaaL) - 0x4cdb4d86e38af716L;
         }
      }

      return count17;
   }

   public SplineParser(int count14, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count15 = fG7jSMNbfywSerializable.count2;
      long value = (long)count14;
      int count16 = 0;
      int count17 = 0;
      if (count15 > 0) {
         count17 = computeCount4(value);

         for (int index = count17 - count15; index > 0; index = count17 - count15) {
            count16 = computeCountByTime((long)count16 - (long)index);
            value = computeTimestamp(value, timestampArray2[index], fG7jSMNbfywSerializable.combatScaleProperty.count);
            count17 = computeCount4(value);
         }
      }

      this.signumComparable2 = null;
      this.timestamp2 = value;
      this.count5 = count16;
      this.count9 = count17;
   }

   public SplineParser(String text) {
      this(text.toCharArray(), 0, text.length());
   }

   public SplineParser(int count14) {
      this.timestamp2 = (long)count14;
      this.count5 = 0;
      this.signumComparable2 = null;
   }

   public SplineParser(char[] characterArray, int count14, int count15, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      if ((characterArray.length | count15 | count14) >= 0 && count15 <= characterArray.length - count14) {
         int count16 = 0;
         int count17 = 0;
         long value = 0L;
         SignumComparable signumComparable3 = null;

         long value2;
         try {
            boolean flag = false;
            if (characterArray[count14] == '-') {
               flag = true;
               count14++;
               count15--;
            } else if (characterArray[count14] == '+') {
               count14++;
               count15--;
            }

            boolean flag2 = false;
            long value3 = 0L;
            boolean flag3 = count15 <= 18;
            int count18 = 0;
            if (!flag3) {
               char[] characterArray2;
               for (characterArray2 = new char[count15]; count15 > 0; count15--) {
                  char character = characterArray[count14];
                  if ((character < '0' || character > '9') && !Character.isDigit(character)) {
                     if (character != '.') {
                        if (character != 'e' && character != 'E') {
                           throw new NumberFormatException();
                        }

                        value3 = computeTimestamp(characterArray, count14, count15);
                        if ((long)((int)value3) != value3) {
                           throw new NumberFormatException();
                        }
                        break;
                     }

                     if (flag2) {
                        throw new NumberFormatException();
                     }

                     flag2 = true;
                  } else {
                     if (character != '0' && Character.digit(character, 10) != 0) {
                        if (count16 != 1 || count18 != 0) {
                           count16++;
                        }

                        characterArray2[count18++] = character;
                     } else if (count16 == 0) {
                        characterArray2[count18] = character;
                        count16 = 1;
                     } else if (count18 != 0) {
                        characterArray2[count18++] = character;
                        count16++;
                     }

                     if (flag2) {
                        count17++;
                     }
                  }

                  count14++;
               }

               if (count16 == 0) {
                  throw new NumberFormatException();
               }

               if (value3 != 0L) {
                  count17 = this.computeCount(count17, value3);
               }

               int count19 = flag ? -1 : 1;
               signumComparable3 = new SignumComparable(characterArray2, count19, count16);
               value2 = computeTimestamp(signumComparable3);
               int count20 = fG7jSMNbfywSerializable.count2;
               if (count20 > 0 && count16 > count20) {
                  if (value2 == Long.MIN_VALUE) {
                     for (int index = count16 - count20; index > 0; index = count16 - count20) {
                        count17 = computeCountByTime((long)count17 - (long)index);
                        int count21 = fG7jSMNbfywSerializable.combatScaleProperty.count;
                        signumComparable3 = resolveSignumComparable(signumComparable3, index, count21);
                        value2 = computeTimestamp(signumComparable3);
                        if (value2 != Long.MIN_VALUE) {
                           count16 = computeCount4(value2);
                           break;
                        }

                        count16 = computeCount(signumComparable3);
                     }
                  }

                  if (value2 != Long.MIN_VALUE) {
                     for (int index2 = count16 - count20; index2 > 0; index2 = count16 - count20) {
                        count17 = computeCountByTime((long)count17 - (long)index2);
                        value2 = computeTimestamp(value2, timestampArray2[index2], fG7jSMNbfywSerializable.combatScaleProperty.count);
                        count16 = computeCount4(value2);
                     }

                     signumComparable3 = null;
                  }
               }
            } else {
               while (count15 > 0) {
                  char character2 = characterArray[count14];
                  if (character2 == '0') {
                     if (count16 == 0) {
                        count16 = 1;
                     } else if (value != 0L) {
                        value *= 10L;
                        count16++;
                     }

                     if (flag2) {
                        count17++;
                     }
                  } else if (character2 >= '1' && character2 <= '9') {
                     int count22 = character2 - '0';
                     if (count16 != 1 || value != 0L) {
                        count16++;
                     }

                     value = value * 10L + (long)count22;
                     if (flag2) {
                        count17++;
                     }
                  } else if (character2 == '.') {
                     if (flag2) {
                        throw new NumberFormatException();
                     }

                     flag2 = true;
                  } else {
                     if (!Character.isDigit(character2)) {
                        if (character2 != 'e' && character2 != 'E') {
                           throw new NumberFormatException();
                        }

                        value3 = computeTimestamp(characterArray, count14, count15);
                        if ((long)((int)value3) != value3) {
                           throw new NumberFormatException();
                        }
                        break;
                     }

                     int count23 = Character.digit(character2, 10);
                     if (count23 == 0) {
                        if (count16 == 0) {
                           count16 = 1;
                        } else if (value != 0L) {
                           value *= 10L;
                           count16++;
                        }
                     } else {
                        if (count16 != 1 || value != 0L) {
                           count16++;
                        }

                        value = value * 10L + (long)count23;
                     }

                     if (flag2) {
                        count17++;
                     }
                  }

                  count14++;
                  count15--;
               }

               if (count16 == 0) {
                  throw new NumberFormatException();
               }

               if (value3 != 0L) {
                  count17 = this.computeCount(count17, value3);
               }

               value2 = flag ? -value : value;
               int count24 = fG7jSMNbfywSerializable.count2;
               int count25 = count16 - count24;
               if (count24 > 0 && count25 > 0) {
                  while (count25 > 0) {
                     count17 = computeCountByTime((long)count17 - (long)count25);
                     value2 = computeTimestamp(value2, timestampArray2[count25], fG7jSMNbfywSerializable.combatScaleProperty.count);
                     count16 = computeCount4(value2);
                     count25 = count16 - count24;
                  }
               }
            }
         } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new NumberFormatException();
         } catch (NegativeArraySizeException negativeArraySizeException) {
            throw new NumberFormatException();
         }

         this.count5 = count17;
         this.count9 = count16;
         this.timestamp2 = value2;
         this.signumComparable2 = signumComparable3;
      } else {
         throw new NumberFormatException("Bad offset or len arguments for char[] input.");
      }
   }

   public SplineParser(double offset, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      if (!Double.isInfinite(offset) && !Double.isNaN(offset)) {
         long value = Double.doubleToLongBits(offset);
         int count14 = value >> 63 == 0L ? 1 : -1;
         int count15 = (int)(value >> 52 & 2047L);
         long value2 = count15 == 0 ? (value & 4503599627370495L) << 1 : value & 4503599627370495L | 4503599627370496L;
         count15 -= 1075;
         if (value2 == 0L) {
            this.signumComparable2 = SignumComparable.signumComparable5;
            this.count5 = 0;
            this.timestamp2 = 0L;
            this.count9 = 1;
         } else {
            while ((value2 & 1L) == 0L) {
               value2 >>= 1;
               count15++;
            }

            int count16 = 0;
            long value3 = (long)count14 * value2;
            SignumComparable signumComparable3;
            if (count15 == 0) {
               signumComparable3 = value3 == Long.MIN_VALUE ? signumComparable : null;
            } else {
               if (count15 < 0) {
                  SignumComparable signumComparable4 = SignumComparable.resolveSignumComparableByTime(5L);
                  int count17 = -count15;
                  signumComparable3 = signumComparable4.resolveSignumComparable8(count17).resolveSignumComparable(value3);
                  count16 = -count15;
               } else {
                  signumComparable3 = SignumComparable.resolveSignumComparableByTime(2L).resolveSignumComparable8(count15).resolveSignumComparable(value3);
               }

               value3 = computeTimestamp(signumComparable3);
            }

            int count18 = 0;
            int count19 = fG7jSMNbfywSerializable.count2;
            if (count19 > 0) {
               int count20 = fG7jSMNbfywSerializable.combatScaleProperty.count;
               if (value3 == Long.MIN_VALUE) {
                  count18 = computeCount(signumComparable3);

                  for (int index = count18 - count19; index > 0; index = count18 - count19) {
                     count16 = computeCountByTime((long)count16 - (long)index);
                     signumComparable3 = resolveSignumComparable(signumComparable3, index, count20);
                     value3 = computeTimestamp(signumComparable3);
                     if (value3 != Long.MIN_VALUE) {
                        break;
                     }

                     count18 = computeCount(signumComparable3);
                  }
               }

               if (value3 != Long.MIN_VALUE) {
                  count18 = computeCount4(value3);

                  for (int index2 = count18 - count19; index2 > 0; index2 = count18 - count19) {
                     count16 = computeCountByTime((long)count16 - (long)index2);
                     value3 = computeTimestamp(value3, timestampArray2[index2], fG7jSMNbfywSerializable.combatScaleProperty.count);
                     count18 = computeCount4(value3);
                  }

                  signumComparable3 = null;
               }
            }

            this.signumComparable2 = signumComparable3;
            this.timestamp2 = value3;
            this.count5 = count16;
            this.count9 = count18;
         }
      } else {
         throw new NumberFormatException("Infinite or NaN");
      }
   }

   public SplineParser(char[] characterArray, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      this(characterArray, 0, characterArray.length, fG7jSMNbfywSerializable);
   }

   public SplineParser(char[] characterArray) {
      this(characterArray, 0, characterArray.length);
   }

   public SplineParser(SignumComparable signumComparable3, long value, int count14, int count15) {
      this.count5 = count14;
      this.count9 = count15;
      this.timestamp2 = value;
      this.signumComparable2 = signumComparable3;
   }

   public SplineParser(String text, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      this(text.toCharArray(), 0, text.length(), fG7jSMNbfywSerializable);
   }

   public SplineParser(SignumComparable signumComparable3, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      this(signumComparable3, 0, fG7jSMNbfywSerializable);
   }

   public SplineParser(SignumComparable signumComparable3, int count14) {
      this.signumComparable2 = signumComparable3;
      this.timestamp2 = computeTimestamp(signumComparable3);
      this.count5 = count14;
   }

   public SplineParser(char[] characterArray, int count14, int count15) {
      this(characterArray, count14, count15, FG7jSMNbfywSerializable.fG7jSMNbfywSerializable3);
   }

   public SplineParser(double offset) {
      this(offset, FG7jSMNbfywSerializable.fG7jSMNbfywSerializable3);
   }

   public SplineParser(long value) {
      this.timestamp2 = value;
      this.signumComparable2 = value == Long.MIN_VALUE ? signumComparable : null;
      this.count5 = 0;
   }

   public SplineParser(SignumComparable signumComparable3) {
      this.count5 = 0;
      this.signumComparable2 = signumComparable3;
      this.timestamp2 = computeTimestamp(signumComparable3);
   }

   public SplineParser(long value, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      int count14 = fG7jSMNbfywSerializable.count2;
      int count15 = fG7jSMNbfywSerializable.combatScaleProperty.count;
      int count16 = 0;
      int count17 = 0;
      SignumComparable signumComparable3 = value == Long.MIN_VALUE ? signumComparable : null;
      if (count14 > 0) {
         if (value == Long.MIN_VALUE) {
            count16 = 19;

            for (int index = 19 - count14; index > 0; index = count16 - count14) {
               count17 = computeCountByTime((long)count17 - (long)index);
               signumComparable3 = resolveSignumComparable(signumComparable3, index, count15);
               value = computeTimestamp(signumComparable3);
               if (value != Long.MIN_VALUE) {
                  break;
               }

               count16 = computeCount(signumComparable3);
            }
         }

         if (value != Long.MIN_VALUE) {
            count16 = computeCount4(value);

            for (int index2 = count16 - count14; index2 > 0; index2 = count16 - count14) {
               count17 = computeCountByTime((long)count17 - (long)index2);
               value = computeTimestamp(value, timestampArray2[index2], fG7jSMNbfywSerializable.combatScaleProperty.count);
               count16 = computeCount4(value);
            }

            signumComparable3 = null;
         }
      }

      this.signumComparable2 = signumComparable3;
      this.timestamp2 = value;
      this.count5 = count17;
      this.count9 = count16;
   }

   public SplineParser(SignumComparable signumComparable3, int count14, FG7jSMNbfywSerializable fG7jSMNbfywSerializable) {
      long value = computeTimestamp(signumComparable3);
      int count15 = fG7jSMNbfywSerializable.count2;
      int count16 = 0;
      if (count15 > 0) {
         int count17 = fG7jSMNbfywSerializable.combatScaleProperty.count;
         if (value == Long.MIN_VALUE) {
            count16 = computeCount(signumComparable3);

            for (int index = count16 - count15; index > 0; index = count16 - count15) {
               count14 = computeCountByTime((long)count14 - (long)index);
               signumComparable3 = resolveSignumComparable(signumComparable3, index, count17);
               value = computeTimestamp(signumComparable3);
               if (value != Long.MIN_VALUE) {
                  break;
               }

               count16 = computeCount(signumComparable3);
            }
         }

         if (value != Long.MIN_VALUE) {
            count16 = computeCount4(value);

            for (int index2 = count16 - count15; index2 > 0; index2 = count16 - count15) {
               count14 = computeCountByTime((long)count14 - (long)index2);
               value = computeTimestamp(value, timestampArray2[index2], count17);
               count16 = computeCount4(value);
            }

            signumComparable3 = null;
         }
      }

      this.signumComparable2 = signumComparable3;
      this.timestamp2 = value;
      this.count5 = count14;
      this.count9 = count16;
   }

   @Override
   public boolean equals(Object other) {
      SplineParser splineParser4 = null;
      long value = 0L;
      long value2 = 0L;
      if (other instanceof SplineParser) {
         splineParser4 = (SplineParser)other;
         if (other != this) {
            if (this.count5 == splineParser4.count5) {
               value = this.timestamp2;
               value2 = splineParser4.timestamp2;
               if (value == Long.MIN_VALUE) {
                  if (value2 == Long.MIN_VALUE) {
                     SignumComparable signumComparable3 = this.getSignumComparable();
                     SignumComparable signumComparable4 = splineParser4.getSignumComparable();
                     return signumComparable3.equals(signumComparable4);
                  } else {
                     return value2 == computeTimestamp(this.signumComparable2);
                  }
               } else {
                  if (value2 == Long.MIN_VALUE) {
                     value2 = computeTimestamp(splineParser4.signumComparable2);
                  }

                  return value2 == value;
               }
            } else {
               return false;
            }
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      String text = null;
      text = this.labelText2;
      if (text == null) {
         boolean flag = true;
         this.labelText2 = text = this.resolveText(flag);
      }

      return text;
   }

   @Override
   public int hashCode() {
      if (this.timestamp2 == Long.MIN_VALUE) {
         return 31 * this.signumComparable2.hashCode() + this.count5;
      } else {
         long value = this.timestamp2 >= 0L ? this.timestamp2 : -this.timestamp2;
         int count14 = (int)((long)((int)(value >>> 32) * 31) + (value & 4294967295L));
         return 31 * (this.timestamp2 >= 0L ? count14 : -count14) + this.count5;
      }
   }

   @Override
   public int compareTo(Object entry) {
      SplineParser splineParser4 = (SplineParser)entry;
      return this.computeCountBySplineParser(splineParser4);
   }

   @Override
   public int intValue() {
      return this.timestamp2 != Long.MIN_VALUE && this.count5 == 0 ? (int)this.timestamp2 : (int)this.longValue();
   }

   @Override
   public long longValue() {
      if (this.timestamp2 != Long.MIN_VALUE && this.count5 == 0) {
         return this.timestamp2;
      } else {
         return this.getCountAsIndex() != 0 && !this.isActive() && this.count5 > -64 ? this.getSignumComparable3().getTimestamp() : 0L;
      }
   }

   @Override
   public float floatValue() {
      if (this.timestamp2 != Long.MIN_VALUE) {
         if (this.count5 == 0) {
            return (float)this.timestamp2;
         }

         if (Math.abs(this.timestamp2) < 4194304L) {
            if (this.count5 > 0 && this.count5 < factorArray.length) {
               return (float)this.timestamp2 / factorArray[this.count5];
            }

            if (this.count5 < 0 && this.count5 > -factorArray.length) {
               return (float)this.timestamp2 * factorArray[-this.count5];
            }
         }
      }

      return Float.parseFloat(this.toString());
   }

   @Override
   public double doubleValue() {
      if (this.timestamp2 != Long.MIN_VALUE) {
         if (this.count5 == 0) {
            return (double)this.timestamp2;
         }

         if (Math.abs(this.timestamp2) < 4503599627370496L) {
            if (this.count5 > 0 && this.count5 < valueArray.length) {
               return (double)this.timestamp2 / valueArray[this.count5];
            }

            if (this.count5 < 0 && this.count5 > -valueArray.length) {
               return (double)this.timestamp2 * valueArray[-this.count5];
            }
         }
      }

      return Double.parseDouble(this.toString());
   }
}
