package client.util;

import java.io.ObjectStreamField;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SignumComparable implements Comparable {
   public static final boolean enabled2 = !SignumComparable.class.desiredAssertionStatus();
   public static final int count12 = 512;
   public final int count;
   public static SignumComparable[] signumComparableArray2 = new SignumComparable[17];
   public static final int count19 = 128;
   public static final SignumComparable signumComparable5;
   public static long[] timestampArray = new long[]{
      0L,
      0L,
      1024L,
      1624L,
      2048L,
      2378L,
      2648L,
      2875L,
      3072L,
      3247L,
      3402L,
      3543L,
      3672L,
      3790L,
      3899L,
      4001L,
      4096L,
      4186L,
      4271L,
      4350L,
      4426L,
      4498L,
      4567L,
      4633L,
      4696L,
      4756L,
      4814L,
      4870L,
      4923L,
      4975L,
      5025L,
      5074L,
      5120L,
      5166L,
      5210L,
      5253L,
      5295L
   };
   public static final SignumComparable signumComparable4;
   public static final int count3 = 100;
   public static volatile SignumComparable[][] signumComparableArray;
   public static final SignumComparable signumComparable2 = resolveSignumComparableByTime(0x8a5b6470af95L);
   public static int[] countArray2;
   public static int[] countArray5;
   @Deprecated
   public int count17;
   @Deprecated
   public int count5;
   public final int[] countArray;
   public static final int count11 = 67108864;
   public static final SignumComparable signumComparable3;
   public static final double value = Math.log(2.0);
   public static final long timestamp = 4294967295L;
   public static final ObjectStreamField[] serialPersistentFields;
   public static final int count9 = 500000000;
   public static final int count18 = 16;
   @Deprecated
   public int count13;
   public static SignumComparable[] signumComparableArray3;
   public static final double[] valueArray;
   public static final int count10 = 216;
   public static final int count7 = 20;
   public static int[] countArray4;
   public static final SignumComparable signumComparable;
   public static final SignumComparable signumComparable6;
   public static final int count14 = 95;
   public static final int count8 = 40;
   public static final int count6 = 80;
   @Deprecated
   public int count2;
   public static int[] countArray3;
   public static SignumComparable[] signumComparableArray4 = new SignumComparable[17];
   public static String[] stringArray;
   public static final int count15 = 80;
   public static final int count16 = 240;
   public static final int count4 = 20;

   public SignumComparable resolveSignumComparable14(SignumComparable signumComparable7) {
      return this.computeCountBySignumComparable(signumComparable7) < 0 ? this : signumComparable7;
   }

   public static int computeCount(int[] countArray6, int count20, int count21, int count22) {
      int count23 = count21;
      long value2 = 0L;
      int count24 = countArray6.length - 1 - count21 - count20;
      value2 = ((long)countArray6[count24] & 4294967295L) + ((long)count22 & 4294967295L);
      countArray6[count24] = (int)value2;
      if (value2 >>> 32 == 0L) {
         return 0;
      } else {
         while (--count23 >= 0) {
            if (--count24 < 0) {
               return 1;
            }

            countArray6[count24]++;
            if (countArray6[count24] != 0) {
               return 0;
            }
         }

         return 1;
      }
   }

   public int getCount6() {
      int count20 = 0;
      int count21 = 0;
      int count22 = 0;
      long value2 = 0x6ca39bdde2cd14ecL;
      long value3 = -0x374bfe3a2558e406L;

      while (true) {
         while (true) {
            int count23 = (int)(value2 % 14L);
            if (count23 != 10) {
               if (count23 == 0) {
                  break;
               }

               if (count23 == 7) {
                  count21 = 0;
                  value2 = (value2 ^ 0x3a7da6301235fbbdL) + 0xe05c69cb07f603aL;
                  value3 = (value3 ^ -0x55fe4fb6c90c56b7L) + 0x292926ae3b2543eaL;
                  continue;
               }

               if (count23 == 2) {
                  if (count20 != -1) {
                     return count20;
                  }

                  value2 = (value2 ^ -0x3aa50f87fd398af5L) + 0x572a865ae0d4a863L;
                  value3 = (value3 ^ 0x38d959bdf48f3f3dL) - 0x4a38a1b0e09599edL;
                  continue;
               }

               if (count23 == 11) {
                  count21 += 32;
                  count22--;
                  value2 = (value2 ^ 0x7d0d46d276173597L) - 0x17cf87a6e5a4a000L;
                  value3 = (value3 ^ 0x5c3a831c209d1275L) + 0x662f2efd0e8c9bcbL;
                  break;
               }

               if (count23 == 6) {
                  count21 = 0;
                  value2 = (value2 ^ 0x3d198ae291bfdeecL) - 0x2a3a0d718047a9e0L;
                  value3 = (value3 ^ -0x6be4d71573608899L) + 0x3f3ae14a1ed4d097L;
                  continue;
               }

               if (count23 == 13) {
                  count20 += Integer.bitCount(this.countArray[count21]);
                  value2 = (value2 ^ 0x218644ced05cf5b9L) - 0x3b687ad632101c07L;
                  value3 = (value3 ^ -0x4031d43eef1348e0L) + 0x256258379ed36e11L;
                  continue;
               }

               if (count23 == 5) {
                  count20 += count21 - 1;
                  value2 = (value2 ^ 0x645343b17e65980cL) + 0x813255170c18ed8L;
                  value3 = (value3 ^ -0x730b6e2b3d4cae77L) + 0x23990ae298110a5aL;
                  continue;
               }

               if (count23 == 4) {
                  count20 = this.count13 - 1;
                  value2 = (value2 ^ -0x59e4f3d453a1fc36L) + 0x72637984a093a58cL;
                  value3 = (value3 ^ 0x36aa4dff4500d2dbL) - 0x270b25d47ce55308L;
                  continue;
               }

               if (count23 == 3) {
                  this.count13 = count20 + 1;
                  return count20;
               }

               if (count23 != 1) {
                  if (count23 != 8) {
                     count22 = this.countArray.length - 1;
                     value2 = (value2 ^ -0x1311c37154eeafaL) + 0x1f815084bc4bf6a6L;
                     value3 = (value3 ^ 0x3cba0e93c2a65804L) - 0x52c2faa0b69f116L;
                  } else {
                     count20 = 0;
                     value2 = (value2 ^ -0x476a9291a54d21a6L) + 0x73ac1e85fa8fe96dL;
                     value3 = (value3 ^ 0x277c5cd7045cb631L) - 0x6672c390c1b5a909L;
                  }
                  continue;
               }

               count21++;
               value2 = (value2 ^ 0x4acd9a27cf477aaL) + 0x2fe4ea9080c68adfL;
               value3 = (value3 ^ -0x1bceb1b450d3d830L) - 0x744ed645407901deL;
            }

            if (count21 >= this.countArray.length) {
               long value4 = (value2 ^ 0x78d62397e432a41L) - 0x23db83e782b3af46L;
               long value5 = (value3 ^ -0x14a7ed372b7395b5L) - 0x7d39e4be68173529L;
               if (this.count >= 0) {
                  value2 = (value4 ^ -0x38dc500a3ad39fbL) + 0x54823ea5a38f0e85L;
                  value3 = (value5 ^ 0x29e23ea894e381f8L) + 0x7041c65d1953086bL;
               } else {
                  value2 = (value4 ^ -0x31644abaeb98154aL) + 0xfc8f572373514d5L;
                  value3 = (value5 ^ 0x654ad49fd6cb4024L) - 0x1de1e61e7c9d7138L;
               }
            } else {
               value2 = (value2 ^ 0x7c0b9951380bfc4eL) + 0x2d949e4bbdb9736dL;
               value3 = (value3 ^ 0x1aa05c0a9deec762L) + 0x688d580cc2963cbfL;
            }
         }

         if (this.countArray[count22] != 0) {
            long value6 = (value2 ^ -0x2c328f7fb45d3e5dL) - 0x6df0820ae10a33d4L;
            long value7 = (value3 ^ -0x348e4beb6c1d8bbeL) + 0x1675a8333df409a6L;
            count21 += Integer.numberOfTrailingZeros(this.countArray[count22]);
            value2 = (value6 ^ -0x46bd1d3d6af56bf9L) - 0x7bc21beac472a277L;
            value3 = (value7 ^ 0x6b873f6450cac22eL) - 0x5a1cfa5ed148aec6L;
         } else {
            value2 = (value2 ^ 0x128e014185982659L) + 0x514c4cb4591b6db2L;
            value3 = (value3 ^ 0x37a4505915f81413L) + 0x422b8477bbee69f5L;
         }
      }
   }

   private static int[] resolveIndexArray3(int[] countArray6) {
      int count20 = 0;
      int count21 = 0;
      long value2 = 0x4dc6cbe06f609944L;
      long value3 = 0x6466c0b2f595c831L;

      int[] countArray7;
      while (true) {
         int count22 = (int)(value2 % 6L);
         if (count22 != 3) {
            long value4;
            long value5;
            searchBlock: {
               if (count22 != 4) {
                  if (count22 != 5) {
                     if (count22 == 0) {
                        countArray7 = countArray6;
                        break;
                     }

                     if (count22 == 2) {
                        count20 = countArray6.length;
                        value2 = (value2 ^ 0x15ebc4af114a076eL) - 0x320dd368073b6c13L;
                        value3 = (value3 ^ -0x4eeb5b5c828005ccL) - 0x6871bfe03caae8f6L;
                        continue;
                     }

                     if (countArray6[count21] == 0) {
                        value2 = (value2 ^ -0x6c822beba6ee0034L) + 0x5769dff333df5260L;
                        value3 = (value3 ^ 0x7edf4cdf5f5f4678L) - 0x11147c739a391c61L;
                        continue;
                     }

                     value4 = (value2 ^ -0x6e2f74015407dfb1L) + 0x5506824c618810e3L;
                     value5 = (value3 ^ 0x5022a2e55a5c6cb9L) - 0x27415e49953a0720L;
                     break searchBlock;
                  }

                  count21++;
                  value2 = (value2 ^ -0x4ad7075aebaedf5dL) - 0x6f543f32cb7024aL;
                  value3 = (value3 ^ -0xaa5c781cc8f9c68L) + 0x46963f37030a423cL;
               }

               if (count21 < count20) {
                  value2 = (value2 ^ -0x152b54ae0046bb62L) - 0x1df5295a93230979L;
                  value3 = (value3 ^ 0x5f266c595dc01b9L) + 0x2965b4b7ac7fb73cL;
                  continue;
               }

               value4 = (value2 ^ -0x51bbbfa624c8f579L) + 0x61d00b7c4536ed32L;
               value5 = (value3 ^ 0x73102af09caa2f64L) - 0x405c50419b9ffa38L;
            }

            if (count21 != 0) {
               countArray7 = Arrays.copyOfRange(countArray6, count21, count20);
               break;
            }

            value2 = (value4 ^ 0x734fd89e4d9c81d2L) + 0x360a79dd2f31e217L;
            value3 = (value5 ^ -0x3e20ba955df79800L) + 0x7e1184cdc4feebc3L;
         } else {
            count21 = 0;
            value2 = (value2 ^ -0x304168d4c9d72b2L) - 0x5f501cd5a0c8e06bL;
            value3 = (value3 ^ 0x37e862c026148231L) - 0x4b4655fcfe454da4L;
         }
      }

      return countArray7;
   }

   private static int computeCount(int count20) {
      if (count20 <= 500000001) {
         return count20 / 20 * 64;
      } else {
         throw new ArithmeticException("Prime search implementation restriction on bitLength");
      }
   }

   public int computeCountBySignumComparable(SignumComparable signumComparable7) {
      int count20 = 0;
      if (this.count != signumComparable7.count) {
         return this.count <= signumComparable7.count ? -1 : 1;
      } else {
         count20 = this.count;
         if (count20 == -1) {
            return signumComparable7.computeCount(this);
         } else {
            return count20 == 1 ? this.computeCount(signumComparable7) : 0;
         }
      }
   }

   public SignumComparable resolveSignumComparable6(int count20) {
      if (this.count != 0) {
         if (count20 <= 0) {
            if (count20 != 0) {
               int count21 = -count20;
               return new SignumComparable(resolveIndexArrayByIndexArray(this.countArray, count21), this.count);
            } else {
               return this;
            }
         } else {
            return this.resolveSignumComparable7(count20);
         }
      } else {
         return signumComparable5;
      }
   }

   private static int[] resolveIndexArray2(int[] countArray6, long value2) {
      long value3 = 0L;
      int count20 = 0;
      int[] countArray7 = null;
      int count21 = 0;
      long value4 = 0x75891c5ba7b078adL;
      long value5 = -0x5588bbc42c35267aL;

      while (true) {
         int count22 = (int)(value4 % 20L);
         if (count22 == 18) {
            value3 = ((long)countArray6[--count20] & 4294967295L) + (value2 & 4294967295L);
            value4 = (value4 ^ 0x7ce6be2256cdd3afL) - 0x25fbc9a8e8b0748bL;
            value5 = (value5 ^ -0x169f47f6a95d9b33L) - 0x5475ed2a99a6d8bcL;
         } else if (count22 != 4) {
            if (count22 != 6) {
               if (count22 == 10) {
                  count20 = countArray6.length;
                  value4 = (value4 ^ 0x295ab5799906e7abL) - 0x322872999d742c09L;
                  value5 = (value5 ^ 0x6167d3dd5402b1d2L) - 0x3f1e575c68c5b253L;
                  continue;
               }

               if (count22 == 3) {
                  int count23 = countArray6[0];
               } else if (count22 != 13) {
                  if (count22 == 16) {
                     countArray7 = new int[count20];
                     value4 = (value4 ^ 0x118f0714f9108a16L) - 0x640276a17b40243eL;
                     value5 = (value5 ^ -0x7197e1fd0482bbf0L) + 0x1c2adb0bd44282eL;
                     continue;
                  }

                  if (count22 == 0) {
                     value3 = ((long)countArray6[--count20] & 4294967295L) + value2;
                     value4 = (value4 ^ -0x66dc6b6b5b3b05c0L) + 0x69a4f05a3f28eca3L;
                     value5 = (value5 ^ -0x482a4fbf1758da81L) + 0x68513bb6168ce02fL;
                     continue;
                  }

                  if (count22 == 11) {
                     countArray7 = new int[count20];
                     value4 = (value4 ^ -0x7c1e768be813b146L) - 0x599423e40a3ffadfL;
                     value5 = (value5 ^ 0x3ba215f817183b3L) - 0x30dfc4f00476d0e5L;
                     continue;
                  }

                  long value6;
                  long value7;
                  boolean flag;
                  searchBlock: {
                     if (count22 != 14) {
                        if (count22 == 8) {
                           flag = true;
                           value6 = (value4 ^ 0x5b3b5ad600b872d6L) - 0x5706baca00782db6L;
                           value7 = (value5 ^ -0x38617855bd7d49e6L) + 0x38958555bd7eb1eaL;
                           break searchBlock;
                        }

                        if (count22 == 15) {
                           countArray7[count20] = (int)value3;
                           value4 = (value4 ^ -0x4652bcba1af014aL) + 0x6363882743d330c5L;
                           value5 = (value5 ^ -0x799cc3b54e053f1cL) + 0xf77042823f6a624L;
                           continue;
                        }

                        if (count22 == 2) {
                           countArray7[count20] = (int)value3;
                           value4 = (value4 ^ -0x52c3a7401861bd7fL) + 0x45c4f7a0df7662e8L;
                           value5 = (value5 ^ -0x5db0eb46f0f825abL) + 0x1aa1cc6675f2d51eL;
                           continue;
                        }

                        if (count22 == 17) {
                           countArray7[1] = (int)value3;
                           return countArray7;
                        }

                        if (count22 == 9) {
                           value3 = 0L;
                           value4 = (value4 ^ 0x5e7da4ec01bc9279L) + 0x367a7d0c71ca6d32L;
                           value5 = (value5 ^ 0x7a4d4d1d303de81eL) - 0x78cb51572324db56L;
                           continue;
                        }

                        if (count22 == 7) {
                           countArray7[count20] = (int)value3;
                           value4 = (value4 ^ 0x4309bd3f6830ddc7L) + 0x8c4cea951b912a5L;
                           value5 = (value5 ^ 0x56504a7d75684e15L) - 0x2b85d42b06bf2d57L;
                           continue;
                        }

                        if (count22 == 12) {
                           count21 = (int)(value2 >>> 32);
                           value4 = (value4 ^ -0x6c3dbc7d6d631dc9L) - 0x4b53a3fbff8cd01fL;
                           value5 = (value5 ^ -0x7a404d3ffef3a387L) + 0xef2e1578d932a4cL;
                           continue;
                        }

                        if (count22 == 1) {
                           countArray7[0] = (int)(value3 >>> 32);
                           return countArray7;
                        }

                        if (count22 == 19) {
                           value3 = ((long)countArray6[--count20] & 4294967295L) + ((long)count21 & 4294967295L) + (value3 >>> 32);
                           value4 = (value4 ^ -0x64b907586cca4ef4L) + 0x7f2fa68039805e8cL;
                           value5 = (value5 ^ -0x3ebe3f2f34579d1dL) + 0x659e9c99e5ef83e8L;
                           continue;
                        }

                        value4 = (value4 ^ -0x20d2e9faf3a0e473L) - 0x4706c8608f5f296aL;
                        value5 = (value5 ^ 0x4f87ed540ee9599dL) - 0x58d9d0635c720d9fL;
                     }

                     if (value3 >>> 32 != 0L) {
                        value4 = (value4 ^ -0x273bf7b79cfec80bL) - 0x76b7cb10d7fa500fL;
                        value5 = (value5 ^ 0x63e3407de26d0647L) - 0x493f4971edf5831dL;
                        continue;
                     }

                     value6 = (value4 ^ 0x286764708287a146L) - 0x46571f49b973b99cL;
                     value7 = (value5 ^ -0x534d7436792a5291L) + 0x7991ead9adb217bbL;
                     flag = false;
                  }

                  boolean flag2 = flag;

                  while (count20 > 0 && flag2) {
                     count20--;
                     long value8;
                     long value9;
                     if ((countArray7[count20] = countArray6[count20] + 1) != 0) {
                        value8 = (value6 ^ 0x1cdf89028129212aL) - 0x132a88fe80e8df2aL;
                        value9 = (value7 ^ -0x5e6551b77831cd7L) + 0x6169a1c88045d17L;
                        flag = false;
                     } else {
                        flag = true;
                        value8 = (value6 ^ 0x680951e29a65c124L) - 0x27f8b19e759cbf24L;
                        value9 = (value7 ^ -0x6bb70acd6eb94d61L) + 0x6c47d2ee8f3aad6dL;
                     }

                     flag2 = flag;
                     value6 = (value8 ^ -0x4a4b66e5028cf6cfL) - 0x6c93790694b2f711L;
                     value7 = (value9 ^ -0x1f8d86ac65a05015L) + 0x203246cf962398d9L;
                  }

                  while (count20 > 0) {
                     count20--;
                     countArray7[count20] = countArray6[count20];
                  }

                  if (!flag2) {
                     return countArray7;
                  }

                  int[] countArray8 = new int[countArray7.length + 1];
                  System.arraycopy(countArray7, 0, countArray8, 1, countArray7.length);
                  countArray8[0] = 1;
                  return countArray8;
               } else {
                  countArray7 = new int[2];
               }
            }

            return countArray7;
         } else if (count21 != 0) {
            long value10 = (value4 ^ 0x16c2db938ec3cd91L) + 0x29b6d73705ea7d74L;
            long value11 = (value5 ^ 0x22ce155279e3c38cL) - 0x53b65ce9f953e91eL;
            if (count20 != 1) {
               value4 = (value10 ^ -0x21d950811fc19193L) - 0x743f6125c8e36ad9L;
               value5 = (value11 ^ 0x5ad9413a32b33408L) - 0x3a711f57e6a07725L;
            } else {
               value4 = (value10 ^ -0x646d388d5b11c9c2L) - 0x4b1aa97161dfcf26L;
               value5 = (value11 ^ 0x955e955455499f4L) + 0x1325b66fda263316L;
            }
         } else {
            value4 = (value4 ^ -0x4505e0401679e0c4L) - 0xa11f75816363c70L;
            value5 = (value5 ^ 0x5845439d31946ceL) + 0x76ff43ffe8e61df2L;
         }
      }
   }

   public SignumComparable resolveSignumComparable2(long value2) {
      int count20 = 0;
      if (value2 != 0L) {
         if (this.count != 0) {
            if (Long.signum(value2) != this.count) {
               count20 = this.computeCount(value2);
               if (count20 != 0) {
                  int[] countArray6;
                  if (count20 <= 0) {
                     countArray6 = resolveIndexArray(Math.abs(value2), this.countArray);
                  } else {
                     countArray6 = this.countArray;
                     long value3 = Math.abs(value2);
                     countArray6 = resolveIndexArrayByIndexArray(countArray6, value3);
                  }

                  int[] countArray7 = countArray6;
                  int[] countArray8 = resolveIndexArray3(countArray7);
                  return new SignumComparable(countArray8, count20 != this.count ? -1 : 1);
               } else {
                  return signumComparable5;
               }
            } else {
               int[] countArray9 = this.countArray;
               long value4 = Math.abs(value2);
               return new SignumComparable(resolveIndexArray2(countArray9, value4), this.count);
            }
         } else {
            return resolveSignumComparableByTime(value2);
         }
      } else {
         return this;
      }
   }

   private SignumComparable resolveSignumComparable(boolean flag) {
      int count20 = 0;
      Object entry = null;
      if (this.count != 0) {
         count20 = this.countArray.length;
         if (count20 >= 128) {
            if (count20 >= 216) {
               if (!flag) {
                  int length = this.countArray.length;
                  if ((long)computeCount(this.countArray, length) > 1073741824L) {
                     updateState();
                  }
               }

               return this.getSignumComparable7();
            } else {
               return this.getSignumComparable3();
            }
         } else {
            Object entry2 = null;
            entry = resolveIndexArray(this.countArray, count20, (int[])entry2);
            return new SignumComparable(resolveIndexArray3((int[])entry), 1);
         }
      } else {
         return signumComparable5;
      }
   }

   private void updateState2() {
      if (this.countArray.length > 67108864 || this.countArray.length == 67108864 && this.countArray[0] < 0) {
         updateState();
      }
   }

   public static void applyIndexArray(int[] countArray6, int count20, int count21) {
      int count22 = 0;
      int count23 = 0;
      int count24 = 0;
      count22 = 32 - count21;
      count23 = count20 - 1;

      for (int index = countArray6[count23]; count23 > 0; count23--) {
         count24 = index;
         index = countArray6[count23 - 1];
         countArray6[count23] = index << count22 | count24 >>> count21;
      }

      countArray6[0] >>>= count21;
   }

   private static int computeCountByIndexArray(int[] countArray6, int[] countArray7, int count20) {
      int count21 = count20;
      long value2 = 0L;
      value2 = 0L;

      while (--count21 >= 0) {
         value2 = ((long)countArray6[count21] & 4294967295L) - ((long)countArray7[count21] & 4294967295L) + (value2 >> 32);
         countArray6[count21] = (int)value2;
      }

      return (int)(value2 >> 32);
   }

   private static void applyIndexArray(int[] countArray6, int[] countArray7, int count20, int count21, int count22) {
      if (count21 <= countArray7.length) {
         if (count20 >= 0) {
            if (count20 <= countArray6.length - 1) {
               if (count21 > countArray6.length - count20) {
                  throw new IllegalArgumentException("input len is out of bound: " + count21 + " > " + (countArray6.length - count20));
               }
            } else {
               throw new IllegalArgumentException("input offset is out of bound: " + count20 + " > " + (countArray6.length - 1));
            }
         } else {
            throw new IllegalArgumentException("input offset is invalid: " + count20);
         }
      } else {
         throw new IllegalArgumentException("input length is out of bound: " + count21 + " > " + countArray7.length);
      }
   }

   public long getTimestampAsTime() {
      if (this.countArray.length <= 2 && this.getCount3() <= 63) {
         return this.getTimestamp();
      } else {
         throw new ArithmeticException("BigInteger out of long range");
      }
   }

   public SignumComparable resolveSignumComparable2(int count20) {
      int count21 = 0;
      int[] countArray6 = null;
      int count22 = 0;
      long value2 = 0x3a7a95a3f628baabL;
      long value3 = 0x2de3679ae0482708L;

      while (true) {
         int count23 = (int)(value2 % 9L);
         if (count23 != 4) {
            if (count23 == 1) {
               return resolveSignumComparable(countArray6);
            }

            if (count23 == 5) {
               throw new ArithmeticException("Negative bit address");
            }

            if (count23 != 2) {
               if (count23 == 7) {
                  if (count20 >= 0) {
                     value2 = (value2 ^ 0x4f8a8c16baa82348L) - 0x138f5bcfb8241523L;
                     value3 = (value3 ^ 0x5be30811116ac3fcL) - 0x6812ea4804a885b8L;
                  } else {
                     value2 = (value2 ^ 0x7665815d44b8fd27L) - 0x1ef57bed2c6a9b53L;
                     value3 = (value3 ^ 0x40ac6eb67a54fd43L) + 0x14cb4eecce190699L;
                  }
                  continue;
               }

               if (count23 != 3) {
                  if (count23 != 8) {
                     if (count23 != 6) {
                        count21 = count20 >>> 5;
                        value2 = (value2 ^ 0x7c393e5e3ed36c1aL) + 0x2a367c3169ba3ddcL;
                        value3 = (value3 ^ -0x6c1d07dc68804dfeL) + 0x5729fe6f2553fc0fL;
                     } else {
                        countArray6[countArray6.length - count22 - 1] = this.computeCountByIndex(count22);
                        value2 = (value2 ^ -0xca4dbc29cded595L) + 0x6e8dd1768fa624ceL;
                        value3 = (value3 ^ -0x61c60ad0b52acdfaL) - 0x37274cfe93c385feL;
                     }
                  } else {
                     count22 = 0;
                     value2 = (value2 ^ -0x7b7b8bbbe3b2c7d5L) - 0x37c166b5d2104cd9L;
                     value3 = (value3 ^ 0xb935f329e612c7fL) + 0x4da48958447e6a4cL;
                  }
                  continue;
               }

               count22++;
               value2 = (value2 ^ 0x5c3622e098cc2162L) + 0x43944168d4c01442L;
               value3 = (value3 ^ -0x6841aa37db799618L) - 0x19b38b12969d1104L;
            }

            if (count22 >= countArray6.length) {
               long value4 = (value2 ^ 0xc736fbc7541d079L) + 0x1448f62a404d7b55L;
               long value5 = (value3 ^ 0x5578a53ca886de85L) - 0xc798a7a1c5fba19L;
               countArray6[countArray6.length - count21 - 1] = countArray6[countArray6.length - count21 - 1] ^ 1 << (count20 & 31);
               value2 = (value4 ^ 0x1bb37cca78ea793fL) + 0x3975be3ed2f18a1L;
               value3 = (value5 ^ 0x4e3ab55fcde448b1L) - 0x1c57c776be2666afL;
            } else {
               value2 = (value2 ^ -0x79960797fb42b5bL) + 0x647a98cbc6c04110L;
               value3 = (value3 ^ -0x51e4e5db18585ad7L) - 0x4ed80b6e99a80dc1L;
            }
         } else {
            countArray6 = new int[Math.max(this.getCountAsIndex(), count21 + 2)];
            value2 = (value2 ^ -0x7f25aaedb38fe433L) + 0x3b3334d4f6fee77eL;
            value3 = (value3 ^ -0x1308e2b35de9d0f5L) + 0x73fac86643f619c3L;
         }
      }
   }

   public SignumComparable resolveSignumComparable2(SignumComparable signumComparable7) {
      Object entry = null;
      if (signumComparable7.count > 0) {
         entry = this.resolveSignumComparable7(signumComparable7);
         return (SignumComparable)(((SignumComparable)entry).count < 0 ? ((SignumComparable)entry).resolveSignumComparable5(signumComparable7) : entry);
      } else {
         throw new ArithmeticException("BigInteger: modulus not positive");
      }
   }

   public SignumComparable getSignumComparable2() {
      return new SignumComparable(this.countArray, -this.count);
   }

   private static int[] resolveIndexArray(int[] countArray6, int count20) {
      int[] countArray7 = countArray6;
      if (countArray6 == null || countArray6.length < count20) {
         countArray7 = new int[count20];
      }

      return countArray7;
   }

   private static int[] resolveIndexArrayByIndexArray(int[] countArray6, int[] countArray7, int[] countArray8, int count20, long value2, int[] countArray9) {
      int[] countArray10 = resolveIndexArray(countArray6, count20, countArray7, count20, countArray9);
      int count21 = (int)value2;
      return resolveIndexArray(countArray10, countArray8, count20, count21);
   }

   public int getCount2() {
      if (this.countArray.length <= 1 && this.getCount3() <= 31) {
         return this.getCount5();
      } else {
         throw new ArithmeticException("BigInteger out of int range");
      }
   }

   public static SignumComparable resolveSignumComparableByTime(long value2) {
      if (value2 != 0L) {
         if (value2 > 0L && value2 <= 16L) {
            return signumComparableArray4[(int)value2];
         } else {
            return value2 < 0L && value2 >= -16L ? signumComparableArray2[(int)(-value2)] : new SignumComparable(value2);
         }
      } else {
         return signumComparable5;
      }
   }

   private static SignumComparable resolveSignumComparable(int[] countArray6, int count20, int count21) {
      int count22 = 0;
      int[] countArray7 = null;
      long value2 = 0L;
      long value3 = 0L;
      int count23 = 0;
      int count24 = 0;
      long value4 = 0L;
      long value5 = 0x57c3737727aac758L;
      long value6 = 0x2f9813a669e966bbL;

      while (true) {
         int count25 = (int)(value5 % 16L);
         if (count25 != 8) {
            if (count25 != 1) {
               if (count25 == 12) {
                  break;
               }

               if (count25 != 5) {
                  if (count25 != 13) {
                     if (count25 != 15) {
                        if (count25 != 2) {
                           if (count25 != 9) {
                              if (count25 != 0) {
                                 if (count25 == 14) {
                                    int count26 = Integer.numberOfTrailingZeros(count20);
                                    return new SignumComparable(resolveIndexArrayByIndexArray(countArray6, count26), count21);
                                 }

                                 if (count25 == 6) {
                                    break;
                                 }

                                 if (count25 != 3) {
                                    if (count25 != 4) {
                                       if (count25 != 7) {
                                          if (count25 != 10) {
                                             value2 = value4 >>> 32;
                                             value5 = (value5 ^ 0x631f084ab943c73bL) + 0x515ace4418088d0dL;
                                             value6 = (value6 ^ -0x376375c8a3eadadbL) + 0x4a3390aad29bcf8L;
                                          } else {
                                             count23 = countArray7.length - 1;
                                             value5 = (value5 ^ 0x7f0ba82daba70894L) + 0x4d83a5dbd419dcfbL;
                                             value6 = (value6 ^ 0x72f38837ddd971fcL) + 0x29e31e289bd4ae5aL;
                                          }
                                       } else {
                                          count22 = countArray6.length;
                                          value5 = (value5 ^ 0x1665b84e53dc3137L) - 0x17d1d588fcb78a2bL;
                                          value6 = (value6 ^ -0x6ea616a302ce217cL) + 0x428bd0c35fc5aea7L;
                                       }
                                    } else {
                                       countArray7[count23--] = (int)value4;
                                       value5 = (value5 ^ 0x3fcbd8c3cbed68ddL) + 0x60087d27bec47af2L;
                                       value6 = (value6 ^ -0x2b63857609a55f70L) + 0x4bbfc62dfe129b47L;
                                    }
                                    continue;
                                 }

                                 countArray7 = Arrays.copyOfRange(countArray7, 1, countArray7.length);
                                 break;
                              }

                              value4 = ((long)countArray6[count24] & 4294967295L) * value3 + value2;
                              value5 = (value5 ^ 0x756d2b7224b974bfL) - 0x29b9dc66d028987bL;
                              value6 = (value6 ^ 0x7d65baaf937de778L) - 0x6d5d56f7c23a5f3cL;
                              continue;
                           }

                           count24 = count22 - 1;
                           value5 = (value5 ^ 0x3992a66738488a10L) - 0x29c38307b46af2aaL;
                           value6 = (value6 ^ -0x3cf8b4af0fed2e5dL) + 0x196476ca73e09488L;
                           continue;
                        }

                        value2 = 0L;
                        value5 = (value5 ^ -0x7dee032c9f2bba77L) + 0x685f9e9fa1861426L;
                        value6 = (value6 ^ 0x28c169be5743425L) - 0x7896cd6897af5b55L;
                        continue;
                     }
                  } else {
                     count24--;
                     value5 = (value5 ^ -0x115ff1e456309971L) + 0x6c9e1e13844acbfdL;
                     value6 = (value6 ^ 0x9dc68616adb5af0L) + 0x749d664fc1db8cc3L;
                  }

                  if (count24 < 0) {
                     long value7 = (value5 ^ -0x75d7306e5cff1fe3L) - 0x386661a67d09c955L;
                     long value8 = (value6 ^ -0x63efb9250cc4f89L) - 0x37823a9d1a4462c0L;
                     if (value2 != 0L) {
                        countArray7[count23] = (int)value2;
                        break;
                     }

                     value5 = (value7 ^ 0x6372d6af34e84082L) - 0x88964ca3dffa22cL;
                     value6 = (value8 ^ -0x126831430ad63030L) - 0x42ed10db6eed1131L;
                  } else {
                     value5 = (value5 ^ 0x7952e809b5b579f4L) - 0x3a858d3adaa4569bL;
                     value6 = (value6 ^ -0x60bcdde95f4270f2L) + 0x76d895d91dd8f851L;
                  }
               } else {
                  countArray7 = new int[count22 + 1];
                  value5 = (value5 ^ -0xeb7d21c795e34b4L) + 0x68a1140bda658309L;
                  value6 = (value6 ^ -0x2a379c3890c06784L) - 0x5c25a83bbc683f1eL;
               }
            } else {
               value3 = (long)count20 & 4294967295L;
               value5 = (value5 ^ -0x15f87e5d7b807b19L) + 0x54454f886afff684L;
               value6 = (value6 ^ -0x70ee62e07aa6e953L) + 0x28006cb9f43162d6L;
            }
         } else if (Integer.bitCount(count20) != 1) {
            value5 = (value5 ^ 0x7196710c0494e2eL) + 0xbaf5d1e851d7931L;
            value6 = (value6 ^ -0x3d3d297946038e4L) + 0x503255bc3f033821L;
         } else {
            value5 = (value5 ^ 0x3b4be868995e72c8L) + 0xeca58816babb2aeL;
            value6 = (value6 ^ -0x13a7290727883ac6L) - 0x6cba2074f8c6da4bL;
         }
      }

      return new SignumComparable(countArray7, count21);
   }

   private static int[] resolveIndexArray(int[] countArray6, int count20, int count21) {
      int count22 = 0;
      int count23 = 0;
      int count24 = 0;
      int[] entry = null;
      count22 = count21 >>> 5;
      count23 = count21 & 31;
      count24 = numberOfLeadingZeros(countArray6[0]);
      if (count21 > 32 - count24) {
         if (count23 > 32 - count24) {
            entry = new int[count22 + count20 + 1];
            System.arraycopy(countArray6, 0, entry, 0, count20);
            int length = entry.length;
            int count25 = 32 - count23;
            int count26 = length;
            applyIndexArray((int[])entry, count26, count25);
            return (int[])entry;
         } else {
            int[] countArray7 = new int[count22 + count20];
            System.arraycopy(countArray6, 0, countArray7, 0, count20);
            int length2 = countArray7.length;
            applyIndexArrayByIndexArray(countArray7, length2, count23);
            return countArray7;
         }
      } else {
         applyIndexArrayByIndexArray(countArray6, count20, count23);
         return countArray6;
      }
   }

   public SignumComparable resolveSignumComparableByIndex(int count20) {
      int count21 = 0;
      int[] countArray6 = null;
      int count22 = 0;
      long value2 = 0x7a9b95238209469bL;
      long value3 = -0x15336e130b68245bL;

      while (true) {
         int count23 = (int)(value2 % 9L);
         if (count23 != 7) {
            if (count23 == 4) {
               throw new ArithmeticException("Negative bit address");
            }

            if (count23 == 6) {
               if (count20 >= 0) {
                  value2 = (value2 ^ -0x61dadb6d134a11c7L) + 0x3f16dc4de0362086L;
                  value3 = (value3 ^ -0x4f81e8c3672ec2d1L) - 0x44505721bcb9ed28L;
               } else {
                  value2 = (value2 ^ -0x1c654a6df77712c8L) + 0x7b5c79def1f2687dL;
                  value3 = (value3 ^ 0x13bb22859dd0c45aL) - 0x573c721fea90b564L;
               }
               continue;
            }

            if (count23 == 0) {
               count22 = 0;
               value2 = (value2 ^ 0x3627c66a44717cafL) - 0xc8a5e305dd99fa3L;
               value3 = (value3 ^ -0x5a663187208bc40cL) + 0x5eb4fe3dff94ce9aL;
               continue;
            }

            if (count23 == 8) {
               count21 = count20 >>> 5;
               value2 = (value2 ^ 0x133f528707955350L) + 0x17f93cd2f215bd93L;
               value3 = (value3 ^ 0x55057ae905a75ce2L) + 0x7fa4c9e247958dc7L;
               continue;
            }

            if (count23 != 3) {
               if (count23 != 5) {
                  if (count23 == 2) {
                     return resolveSignumComparable(countArray6);
                  }

                  countArray6[countArray6.length - count22 - 1] = this.computeCountByIndex(count22);
                  value2 = (value2 ^ -0x7c71e716c280c607L) - 0x74c7d76a15f7828aL;
                  value3 = (value3 ^ -0x73944cc877e7c599L) - 0x6bbbd64f8254072eL;
               } else {
                  countArray6 = new int[Math.max(this.getCountAsIndex(), (count20 + 1 >>> 5) + 1)];
                  value2 = (value2 ^ 0x7bd741065a5a1455L) - 0x10de848ba53c5607L;
                  value3 = (value3 ^ 0x210e9fd284435562L) - 0x32441756f629c502L;
               }
               continue;
            }

            count22++;
            value2 = (value2 ^ -0x72e0e85c11c50f28L) + 0x334034c0126ccd9aL;
            value3 = (value3 ^ 0x5b27b089e60b7b45L) + 0x78fc0ff6dfd22f33L;
         }

         if (count22 >= countArray6.length) {
            long value4 = (value2 ^ 0x60e54f2796328f4eL) - 0xd55f343d68376b8L;
            long value5 = (value3 ^ -0x2e3cfaf1e384643fL) - 0x4587c6a29d6b135L;
            countArray6[countArray6.length - count21 - 1] = countArray6[countArray6.length - count21 - 1] & ~(1 << (count20 & 31));
            value2 = (value4 ^ 0x349a37286924e93eL) - 0x6377a47a18390c7aL;
            value3 = (value5 ^ -0xff6cd1f5cb6fdf9L) - 0x495be4d542986b7eL;
         } else {
            value2 = (value2 ^ -0x47b47537a7667af5L) - 0x71b1df48af40184aL;
            value3 = (value3 ^ 0x44d8733a4076dc09L) - 0x7f1b85528b9742aaL;
         }
      }
   }

   private static int computeCount(int[] countArray6, int[] countArray7, int count20) {
      long value2 = 0L;
      long value3 = 0L;

      for (int index = 0; index < count20; index++) {
         value2 = (long)countArray6[index] & 4294967295L;
         value3 = (long)countArray7[index] & 4294967295L;
         if (value2 < value3) {
            return -1;
         }

         if (value2 > value3) {
            return 1;
         }
      }

      return 0;
   }

   public boolean checkIndex(int count20) {
      Object entry = null;
      if (count20 > 0) {
         entry = this.getSignumComparable5();
         SignumComparable signumComparable7 = signumComparable4;
         if (!((SignumComparable)entry).equals(signumComparable7)) {
            int byteValue = 0;
            if (((SignumComparable)entry).checkIndexByIndex(byteValue)) {
               SignumComparable signumComparable8 = signumComparable;
               if (!((SignumComparable)entry).equals(signumComparable8)) {
                  Object entry2 = null;
                  return ((SignumComparable)entry).matchesRandomByRandom(count20, (Random)entry2);
               }
            }

            return false;
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   public SignumComparable resolveSignumComparable(long value2) {
      long value3 = value2;
      long value4 = 0x6d947e2e6a16aac6L;
      long value5 = -0x2ad6ee85f1a91633L;
      if (value2 != 0L) {
         value4 = (value4 ^ 0x4784fef70e528f7L) - 0xb4ce85bf9eea1e0L;
         value5 = (value5 ^ 0x35e00436ac6f66d2L) - 0xed90533d8252c1bL;
         if (this.count != 0) {
            value4 = (value4 ^ 0x31569c12a3683ec3L) - 0x1948978ed730f8a8L;
            value5 = (value5 ^ -0x78574e3de3218693L) - 0x4db6d95d03567f4L;
            if (value2 == Long.MIN_VALUE) {
               value4 = (value4 ^ -0x550af90ff29c2f6fL) + 0x3562b39b9a932feaL;
               value5 = (value5 ^ 0xc27633c91ededdaL) + 0xc3fa13fdd0ed99L;
               SignumComparable signumComparable7 = resolveSignumComparableByTime(value2);
               return this.resolveSignumComparable4(signumComparable7);
            }

            value4 = (value4 ^ -0x1dc467d1fccbed3bL) - 0x75065b9d2042d383L;
            value5 = (value5 ^ -0x69443244907ac24cL) + 0x610ea608a511a655L;
            long value6;
            long value7;
            int count20;
            if (value2 <= 0L) {
               value6 = (value4 ^ -0xcf8a533cad8fabeL) + 0x7795767559218dffL;
               value7 = (value5 ^ 0x2fe8d54c7c01d524L) - 0x83a6c5178681aabL;
               count20 = -this.count;
            } else {
               value4 = (value4 ^ 0x29e4ec54e82f3e36L) + 0x2df7e0dd9c299753L;
               value5 = (value5 ^ 0x6f2e66795a425daeL) - 0x48f8bd845aa6a331L;
               count20 = this.count;
               value6 = (value4 ^ 0x6be8b16647c5a508L) + 0x14a8511dc03da308L;
               value7 = (value5 ^ 0x33a8b2d9c5535b88L) + 0x32574ed9bcd13578L;
            }

            int count21 = count20;
            if (value2 >= 0L) {
               value6 = (value6 ^ -0x397ad7bbb6d28e4aL) - 0x3e8527c4391b106eL;
               value7 = (value7 ^ 0x3525acac7b32c80fL) + 0x34dc64a47732c7ffL;
            } else {
               value3 = -value2;
            }

            long value8 = value3 >>> 32;
            long value9 = value3 & 4294967295L;
            int length = this.countArray.length;
            int[] countArray6 = this.countArray;
            long value10;
            long value11;
            int[] countArray7;
            if (value8 != 0L) {
               value10 = (value6 ^ -0x4033279e0ee1c2afL) + 0x48c428200ef62b31L;
               value11 = (value7 ^ -0x5e55eaeab1d6bab3L) - 0x5fa614f2bdd8c543L;
               countArray7 = new int[length + 2];
            } else {
               countArray7 = new int[length + 1];
               value10 = (value6 ^ -0x5df81054ed10a7b9L) + 0x5df91e6aed26c843L;
               value11 = (value7 ^ -0x432d77a230fb2615L) - 0x44ce77aa3d0348e5L;
            }

            int[] countArray8 = countArray7;
            long value12 = 0L;
            int count22 = countArray8.length - 1;

            for (int index = length - 1; index >= 0; value11 = (value11 ^ -0x555b3d1ce8084ef9L) - 0x56a4c120f492b0f9L) {
               long value13 = ((long)countArray6[index] & 4294967295L) * value9 + value12;
               countArray8[count22--] = (int)value13;
               value12 = value13 >>> 32;
               index--;
               value10 = (value10 ^ 0x47cd3179f5b41dabL) + 0x40c2d138124c0ba7L;
            }

            countArray8[count22] = (int)value12;
            if (value8 != 0L) {
               value12 = 0L;
               int count23 = countArray8.length - 2;

               for (int index2 = length - 1; index2 >= 0; index2--) {
                  long value14 = ((long)countArray6[index2] & 4294967295L) * value8 + ((long)countArray8[count23] & 4294967295L) + value12;
                  countArray8[count23--] = (int)value14;
                  value12 = value14 >>> 32;
               }

               countArray8[0] = (int)value12;
            }

            if (value12 == 0L) {
               countArray8 = Arrays.copyOfRange(countArray8, 1, countArray8.length);
            }

            return new SignumComparable(countArray8, count21);
         }
      }

      return signumComparable5;
   }

   private int getCountAsIndex() {
      return (this.getCount3() >>> 5) + 1;
   }

   public SignumComparable resolveSignumComparableBySignumComparable(SignumComparable signumComparable7) {
      short shortValue = 997;
      char character = '\ufeeb';
      short shortValue2 = -1170;
      int[] entry = null;
      entry = new int[Math.max(this.getCountAsIndex(), signumComparable7.getCountAsIndex())];

      for (int index = 0; index < entry.length; index++) {
         int count20 = entry.length - index - 1;
         int count21 = this.computeCountByIndex(count20);
         int count22 = entry.length - index - 1;
         entry[index] = count21 ^ signumComparable7.computeCountByIndex(count22);
      }

      return resolveSignumComparable((int[])entry);
   }

   private boolean matchesRandom(int count20, Random random) {
      SignumComparable signumComparable7 = null;
      SignumComparable signumComparable8 = null;
      int count21 = 0;
      int count22 = 0;
      SignumComparable signumComparable9 = null;
      int count23 = 0;
      SignumComparable signumComparable10 = null;
      long value2 = 0x9bb52aaf1225ac1L;
      long value3 = 0x520b0609a9da9748L;

      while (true) {
         int count24 = (int)(value2 % 22L);
         if (count24 != 21) {
            guardBlock: {
               searchBlock:
               if (count24 != 10) {
                  if (count24 == 8) {
                     count21 = signumComparable8.getCount4();
                     value2 = (value2 ^ 0x1eb42ccfc83a5874L) + 0x41328b5954749f58L;
                     value3 = (value3 ^ 0x40f78ff54a29f2eeL) + 0x120b09d0e51321dL;
                     continue;
                  }

                  if (count24 == 20) {
                     ThreadLocalRandom.current();
                     value2 = (value2 ^ -0x28a5c40d10a7ff86L) - 0x4d8d6b623ed23c2bL;
                     value3 = (value3 ^ -0x4ef8d1d68065f2fL) + 0x6b02d62c708add47L;
                     continue;
                  }

                  if (count24 == 5) {
                     if (signumComparable9.computeCountBySignumComparable(this) >= 0) {
                        value2 = (value2 ^ 0x2a40210a8a4076deL) + 0x59be7e96aea72657L;
                        value3 = (value3 ^ -0x40068eb4605f4e58L) + 0x6ca940e10ba7f669L;
                     } else {
                        value2 = (value2 ^ 0x98a30eeff056e40L) - 0xe9a906eb9adf5eaL;
                        value3 = (value3 ^ 0x7bdc9e83ba144cf6L) + 0x9de72b62650afd1L;
                     }
                     continue;
                  }

                  if (count24 != 12) {
                     if (count24 == 16) {
                        SignumComparable signumComparable11 = signumComparable4;
                        signumComparable10 = signumComparable10.resolveSignumComparable2(signumComparable11, this);
                        value2 = (value2 ^ 0x538d76a73be36c96L) + 0x86077dca8a0b884L;
                        value3 = (value3 ^ -0x2c2fcf46218fe0f7L) + 0x284b54bbc57fc67bL;
                        continue;
                     }

                     long value4;
                     long value5;
                     if (count24 != 17) {
                        if (count24 == 0) {
                           signumComparable9 = new SignumComparable(this.getCount3(), random);
                           value2 = (value2 ^ -0x234c142016437c33L) + 0x711d50f60adcb1e7L;
                           value3 = (value3 ^ 0x2cd7299d4cc783e4L) + 0x5321e4ebfd311e7eL;
                           continue;
                        }

                        if (count24 == 3) {
                           if (++count23 == count21) {
                              break;
                           }

                           value2 = (value2 ^ -0x44b18dd8a0b217fL) - 0x641ddaa9a692bf98L;
                           value3 = (value3 ^ 0x67682d4fcb1c523aL) - 0x49d76726a4133460L;
                           continue;
                        }

                        if (count24 == 1) {
                           SignumComparable signumComparable12 = signumComparable;
                           signumComparable7 = this.resolveSignumComparable11(signumComparable12);
                           value2 = (value2 ^ -0xaa335e3931976f2L) + 0x740126562a7dfeecL;
                           value3 = (value3 ^ -0x5d0c117d99302246L) + 0x4d994dadcceeb677L;
                           continue;
                        }

                        if (count24 == 11) {
                           count22 = 0;
                           value2 = (value2 ^ -0x7e16f006ee3647b9L) - 0x57bfaba362cfc284L;
                           value3 = (value3 ^ -0x7e47365b6763a0a2L) + 0x1921be35266d3aaaL;
                           continue;
                        }

                        if (count24 == 7) {
                           SignumComparable signumComparable13 = signumComparable;
                           if (signumComparable10.equals(signumComparable13)) {
                              break;
                           }

                           value2 = (value2 ^ 0x148acb2df8c2c1c0L) - 0x4841fd981f2e1caeL;
                           value3 = (value3 ^ -0x2c0a598c3858c3f3L) + 0x357b6dd05c837dbbL;
                           continue;
                        }

                        if (count24 == 6) {
                           signumComparable8 = signumComparable8.resolveSignumComparable6(count21);
                           value2 = (value2 ^ 0x12855faf260c6353L) - 0x5bcbd7ec366b714L;
                           value3 = (value3 ^ 0x7593bcb9e415bd7aL) + 0xeb166f66020fffcL;
                           continue;
                        }

                        if (count24 == 18) {
                           if (count23 <= 0) {
                              value2 = (value2 ^ -0x28f305fa7396505bL) + 0x531ac7415036ff32L;
                              value3 = (value3 ^ -0x7b68196ad67f37c0L) - 0x606306ceaa56dc5L;
                           } else {
                              value2 = (value2 ^ 0x22d3dc4e11fd021fL) + 0x4216a020b3af085aL;
                              value3 = (value3 ^ -0x6cabbbce6dc94874L) + 0x7f93aa6e9b27a841L;
                           }
                           continue;
                        }

                        if (count24 == 2) {
                           signumComparable10 = signumComparable9.resolveSignumComparable2(signumComparable8, this);
                           value2 = (value2 ^ 0x72c0ed86f047e0c8L) - 0x3dafce674c32a650L;
                           value3 = (value3 ^ -0x31961213ea1e964aL) - 0x2eb47f4bdfcede24L;
                           continue;
                        }

                        if (count24 == 19) {
                           break;
                        }

                        if (count24 == 14) {
                           break searchBlock;
                        }

                        if (count24 != 13) {
                           if (count24 == 15) {
                              if (random != null) {
                                 value2 = (value2 ^ -0x6a00c5f7b20d7494L) + 0x4b52485d039b94feL;
                                 value3 = (value3 ^ -0x731a1829b543b725L) + 0x4462c26d515979dbL;
                              } else {
                                 value2 = (value2 ^ -0x3f90a38b1497f8e1L) - 0x69ea70417a33ba32L;
                                 value3 = (value3 ^ 0x7440d85cff9e42baL) + 0x290b472d008537c4L;
                              }
                              continue;
                           }

                           if (count24 == 9) {
                              signumComparable8 = signumComparable7;
                              value2 = (value2 ^ 0x4036997316113dc9L) + 0xe6e8c865dcb5b14L;
                              value3 = (value3 ^ -0x25d9ebbbec4924c6L) - 0x69912c620d67709L;
                              continue;
                           }
                           break guardBlock;
                        }

                        if (!signumComparable10.equals(signumComparable7)) {
                           value2 = (value2 ^ 0x7a3303f1bfd1e46dL) - 0x2761d8cce1526d12L;
                           value3 = (value3 ^ 0x1f3fbe5ebda3de55L) + 0x437cd7fc1be68091L;
                           continue;
                        }

                        value4 = (value2 ^ -0x548695015c987037L) - 0x62dd1fca9c906bb6L;
                        value5 = (value3 ^ 0x6ff46371459ab10aL) - 0x491b9bef3385de55L;
                     } else {
                        SignumComparable signumComparable14 = signumComparable;
                        if (!signumComparable10.equals(signumComparable14)) {
                           value2 = (value2 ^ -0x4a38d477ae41fd16L) + 0x41a7120d7b404cd6L;
                           value3 = (value3 ^ 0x6e26f8523748b03dL) - 0x62ed5ef9987b4e04L;
                           continue;
                        }

                        value4 = (value2 ^ 0x7f08f27aa2b7f171L) - 0x512bccc2f67351eL;
                        value5 = (value3 ^ -0x40c0d876f9dbeda8L) - 0x3345d15e9f1af984L;
                     }

                     count22++;
                     value2 = (value4 ^ 0x8a6d2d1c4c9e1eL) + 0x426c9b5e7a79d550L;
                     value3 = (value5 ^ 0x63331fd1065cb5c1L) + 0x544956e9affe2f33L;
                     break guardBlock;
                  }

                  SignumComparable signumComparable15 = signumComparable;
                  if (signumComparable9.computeCountBySignumComparable(signumComparable15) <= 0) {
                     value2 = (value2 ^ -0x372edcdc4dc0bcf1L) - 0x5cb906fe52a47597L;
                     value3 = (value3 ^ -0x1a1b3a7c6a96689L) + 0x225965eeeec1cc11L;
                  } else {
                     value2 = (value2 ^ 0x469db5266eb3e624L) - 0x26731291aeff047dL;
                     value3 = (value3 ^ -0x378cb2d0c9f289a5L) + 0x431c32ce6d3f53dcL;
                  }
                  continue;
               } else {
                  value2 = (value2 ^ -0x7b592563cb00939eL) + 0x6d6a605b9baf8f94L;
                  value3 = (value3 ^ -0x7058160f3f2a23a8L) - 0x1937bd4cffbfab5cL;
               }

               if (count23 != 0) {
                  value2 = (value2 ^ 0x3b3e8efb93e2060bL) + 0xde76a01fc7505f2L;
                  value3 = (value3 ^ -0x3ff547ff0971bf3eL) - 0xfbab5253fff0ac3L;
               } else {
                  value2 = (value2 ^ 0xb81b5e373f34263L) + 0x30881893f1ad7a5aL;
                  value3 = (value3 ^ -0x227eed51702dd95fL) - 0x6527543b2588667fL;
               }
               continue;
            }

            if (count22 >= count20) {
               return true;
            }

            value2 = (value2 ^ -0x6cad458c5ac3558dL) - 0x6ba8e72db3d30055L;
            value3 = (value3 ^ 0x1e0d9456a6d13de9L) - 0x5ed5d757a5234893L;
         } else {
            count23 = 0;
            value2 = (value2 ^ -0x297b5bc68cee9a71L) + 0x2d67b4de67889260L;
            value3 = (value3 ^ -0x8648da7e72d290fL) + 0x533cbcc3d21d9db7L;
         }
      }

      return false;
   }

   private SignumComparable getSignumComparableAsSignumComparable() {
      int count20 = 0;
      int[] countArray6 = null;
      long value2 = 0L;
      long value3 = 0L;
      long value4 = 0L;
      long value5 = 0L;
      int count21 = 0;
      long value6 = 0x2978e6a58ca5d360L;
      long value7 = 0x1a655848e37cb0ccL;

      while (true) {
         int count22 = (int)(value6 % 18L);
         if (count22 != 4) {
            if (count22 == 5) {
               countArray6[count21] = (int)value4;
               value6 = (value6 ^ -0x481ca2d26f673f90L) - 0x542d4c38ce05db26L;
               value7 = (value7 ^ 0x4ab1a4c209b87055L) + 0x44932a5a0da52764L;
            } else if (count22 == 17) {
               value5 += value7 % 926979L ^ 697819L;
               value6 = (value6 ^ -0x5df36ba00d85017bL) - 0x7c758128bbf6648fL;
               value7 = (value7 ^ -0x417817977a29158L) + 0x4cefd9a19ac7dfc0L;
            } else if (count22 != 14) {
               searchBlock: {
                  if (count22 != 6) {
                     if (count22 == 16) {
                        countArray6 = new int[count20];
                        value6 = (value6 ^ 0x709a647d804b380aL) + 0x3107d584333ba43L;
                        value7 = (value7 ^ -0x29d95a0a41bd78efL) - 0x2ca8e03d8c013462L;
                        continue;
                     }

                     if (count22 == 1) {
                        value6 = (value6 ^ 0xba1d011f3d0638aL) + 0x571c9dc04142cb16L;
                        value7 = (value7 ^ 0x422e46a390157abdL) + 0x6008a7d18cb554ffL;
                        break searchBlock;
                     }

                     if (count22 == 13) {
                        if (value4 < 1431655766L) {
                           value6 = (value6 ^ -0x61e6c7652b3df4c9L) + 0x6557e56f2fba174bL;
                           value7 = (value7 ^ 0x415a8eb084e3dc68L) - 0x3eb414708f13e059L;
                        } else {
                           value6 = (value6 ^ -0x7e6c11af9b9f9498L) - 0x68900749007c9197L;
                           value7 = (value7 ^ -0x40c3643df4638659L) - 0x598d2d75f7b7aa3aL;
                        }
                        continue;
                     }

                     if (count22 == 2) {
                        if (value5 <= value2) {
                           long value8 = (value6 ^ 0x2a2116e621b3bf1dL) - 0x614c9d1d98b4e1ecL;
                           long value9 = (value7 ^ 0x50cd3f08ec423aa2L) + 0x5d36370b15eef604L;
                           value5 = 0L;
                           value6 = (value8 ^ -0x351ff9fea028129fL) - 0x6a23343012c3a713L;
                           value7 = (value9 ^ 0x12ed37a584e1c1dL) + 0x1f093c7b447e3b5fL;
                        } else {
                           value6 = (value6 ^ 0x4b04c1922164d06bL) - 0x105d9bf41f453159L;
                           value7 = (value7 ^ -0x770009a22fcebdb0L) - 0xf7a8358ccc953a4L;
                        }
                        continue;
                     }

                     if (count22 == 9) {
                        break;
                     }

                     if (count22 == 8) {
                        value5 = value7 % 715562L ^ -59335L;
                        value6 = (value6 ^ -0x69070a2772f3c1caL) - 0x7bfb16a5ff30e193L;
                        value7 = (value7 ^ 0x6febbe1b91e80f74L) - 0x1d8b98cfcb426936L;
                        continue;
                     }

                     if (count22 == 11) {
                        value2 = (long)this.countArray[count21] & 4294967295L;
                        value6 = (value6 ^ 0x66483361d13535a0L) + 0x3631aa2795c86047L;
                        value7 = (value7 ^ -0x16b1dcd921979088L) + 0x6a7a6e80ff3062cfL;
                        continue;
                     }

                     if (count22 == 0) {
                        value3 = value2 - value5;
                        value6 = (value6 ^ 0x71406e411492475aL) + 0x1fa40b9be44af12cL;
                        value7 = (value7 ^ 0x4fc1390d9393643L) + 0x7cc62d6e1f06ae1fL;
                        continue;
                     }

                     if (count22 == 15) {
                        value5 = 0L;
                        value6 = (value6 ^ 0x61b8be77a9283947L) - 0x25f2f29ba7d2aa5aL;
                        value7 = (value7 ^ 0x771f96198439a6efL) - 0x69d0b8e6f52908d0L;
                        continue;
                     }

                     if (count22 == 12) {
                        count21 = count20 - 1;
                        value6 = (value6 ^ -0x43467a5c1292b7bfL) - 0x329839392828fe99L;
                        value7 = (value7 ^ 0xfb9df7870827bcL) - 0x40a10b3efaebd715L;
                        continue;
                     }

                     if (count22 == 10) {
                        count20 = this.countArray.length;
                        value6 = (value6 ^ -0xe16b26512b527d1L) + 0x7e3f2c2c25e88803L;
                        value7 = (value7 ^ -0x55dd7c640645e089L) - 0x15467ece7cb7f80eL;
                        continue;
                     }

                     if (count22 != 3) {
                        break searchBlock;
                     }

                     count21--;
                     value6 = (value6 ^ -0x56abdb2d5f0f44a5L) - 0x4cb46a438aed46fcL;
                     value7 = (value7 ^ 0x7a7a1e37debcd41aL) + 0x57222dfaf509b7fbL;
                  }

                  if (count21 < 0) {
                     countArray6 = resolveIndexArray3(countArray6);
                     break;
                  }

                  value6 = (value6 ^ 0x7023d5a85a5e9c89L) + 0x4ce73a9b6ac2d23aL;
                  value7 = (value7 ^ -0x7e02aff65a085bb2L) + 0x76cb676a97511e4fL;
                  continue;
               }

               value4 = value3 * 2863311531L & 4294967295L;
               value6 = (value6 ^ 0x619692b2b6c9e340L) + 0x313e21b8e9146c24L;
               value7 = (value7 ^ 0x3ce881ba1136ccc3L) - 0x251f96932156ded6L;
            } else {
               value5 += value7 % 870256L ^ 396552L;
               value6 = (value6 ^ 0xe0dc3a13608188dL) + 0x262a54d427cad19dL;
               value7 = (value7 ^ 0x60461f455c0204f0L) + 0x5230dd3f8d7c83eL;
            }
         } else if (value4 < 2863311531L) {
            value6 = (value6 ^ -0x4e420a433addf271L) + 0x36ec41e7c6e83306L;
            value7 = (value7 ^ -0x5f449dfa3c55ddf8L) + 0x3474c76b71b84034L;
         } else {
            value6 = (value6 ^ 0x20d34f00b3866b73L) - 0x1b811bb216230002L;
            value7 = (value7 ^ 0x4618455c0b5c675aL) - 0x4c2248e794c7912L;
         }
      }

      return new SignumComparable(countArray6, this.count);
   }

   private static SignumComparable resolveSignumComparable(int[] countArray6) {
      return countArray6[0] > 0 ? new SignumComparable(countArray6, 1) : new SignumComparable(countArray6);
   }

   private static int[] resolveIndexArray(byte[] byteValueArray) {
      int count20 = 0;
      int count21 = 0;
      int count22 = 0;
      int[] countArray6 = null;
      int count23 = 0;
      int count24 = 0;
      int count25 = 0;
      int count26 = 0;
      int byteValue = 0;
      long value2 = 0x63c508692b077a2cL;
      long value3 = 0x45bde0bb67e3ebdL;

      while (true) {
         long value4;
         long value5;
         while (true) {
            int count27 = (int)(value2 % 16L);
            if (count27 == 8) {
               if (byteValueArray[count21] == 0) {
                  value2 = (value2 ^ 0x23c58f0019e8e243L) - 0x49098af8dbe276L;
                  value3 = (value3 ^ -0x435c4e23c18a564L) + 0x83d81173192cd58L;
               } else {
                  value4 = (value2 ^ -0x44da37584ace04a6L) + 0x78173811b2cd0483L;
                  value5 = (value3 ^ 0x1e4b176759b7808fL) + 0x6abc6d8cab42f765L;
                  break;
               }
            } else if (count27 != 7) {
               if (count27 == 9) {
                  countArray6[count24] |= (byteValueArray[count23--] & 255) << byteValue;
                  value2 = (value2 ^ 0x3c8af129b74451bdL) + 0x2f0085660b1002ffL;
                  value3 = (value3 ^ 0x3d350202f91eb832L) - 0x79ae01fb220e99aL;
               } else {
                  guardBlock: {
                     searchBlock: {
                        if (count27 != 10) {
                           if (count27 == 0) {
                              count25 = count23 - count21 + 1;
                              value2 = (value2 ^ -0x734206ff2145df00L) - 0x3de79d866caf6ea9L;
                              value3 = (value3 ^ 0x4aa4ca47b2a11c80L) - 0x40daf709fea74ff5L;
                              continue;
                           }

                           if (count27 == 5) {
                              count21++;
                              value2 = (value2 ^ -0x48ef801a745c85d9L) + 0x40842c22d8d4851dL;
                              value3 = (value3 ^ -0x393c2ba94e02ed72L) - 0xac8e61a82a4f4fbL;
                              break searchBlock;
                           }

                           if (count27 == 1) {
                              byteValue = 8;
                              value2 = (value2 ^ -0xe6616985c46bf5fL) - 0x2cebe0e8603c4686L;
                              value3 = (value3 ^ 0x4a6e3d29e0256c8eL) - 0x587c184fc053459fL;
                              continue;
                           }

                           if (count27 == 2) {
                              break guardBlock;
                           }

                           if (count27 == 12) {
                              count20 = byteValueArray.length;
                              value2 = (value2 ^ 0x20656887fa81eecL) + 0x1ae6d2e45da2259eL;
                              value3 = (value3 ^ 0x45720d1b7e05163L) - 0x3e12ecb315dc2d84L;
                              continue;
                           }

                           if (count27 == 13) {
                              countArray6[count24] = byteValueArray[count23--] & 255;
                              value2 = (value2 ^ 0x74e5ff92731f4143L) - 0x395dd8f2c17a928eL;
                              value3 = (value3 ^ 0x6688e76ade630e4L) - 0x5bc03dcced5ccd6L;
                              continue;
                           }

                           if (count27 == 14) {
                              count21 = 0;
                              value2 = (value2 ^ 0x68c315f38a22efa5L) + 0x469cce79889fb14L;
                              value3 = (value3 ^ 0x3580b148be84eb55L) - 0x4936f420a2b5b90L;
                              continue;
                           }

                           if (count27 == 4) {
                              count24 = count22 - 1;
                              value2 = (value2 ^ 0x539313d7a2036e27L) + 0x46e247de4717048fL;
                              value3 = (value3 ^ 0x245f09f0d89aca17L) - 0x7379efa669d8eca4L;
                              continue;
                           }

                           if (count27 == 6) {
                              countArray6 = new int[count22];
                              value2 = (value2 ^ -0x7660737da6e2821aL) + 0x6c91c68add68d5dbL;
                              value3 = (value3 ^ -0x3ab51ed5374586a0L) + 0xe57818541ea21f3L;
                              continue;
                           }

                           if (count27 != 3) {
                              if (count27 == 11) {
                                 count23 = count20 - 1;
                                 value2 = (value2 ^ 0xe11b9a5ee7c7ac8L) + 0x41fa00c1c457ae01L;
                                 value3 = (value3 ^ -0x1ac3423d10a21b4aL) - 0x147db26eff3efc8L;
                                 continue;
                              }
                              break searchBlock;
                           }

                           byteValue += 8;
                           value2 = (value2 ^ 0x5bb0b5abf71bc245L) - 0x1049209fac05deecL;
                           value3 = (value3 ^ -0x468936041b8f1667L) + 0x76ee9f4d0f77abffL;
                        }

                        if (byteValue <= count26 << 3) {
                           value2 = (value2 ^ 0x57944eb7c4c6073fL) + 0x30d7a09ca2606bd4L;
                           value3 = (value3 ^ -0x2b17fcd23c4e6b38L) + 0x290d0f1d6445903cL;
                           continue;
                        }

                        long value6 = (value2 ^ 0x5c4a08e359dbd5e1L) + 0x359376a7c1325088L;
                        long value7 = (value3 ^ -0x66eccd78378f0ffaL) + 0x5b91fc995467ba6eL;
                        count24--;
                        value2 = (value6 ^ 0x33cd4fce5462f738L) + 0x62712e2b2971f3b7L;
                        value3 = (value7 ^ -0x3d5961d285b6894fL) - 0x36cd3b7fccd691cL;
                        break guardBlock;
                     }

                     if (count21 < count20) {
                        value2 = (value2 ^ -0x3050c2fc02c1deeeL) + 0x74e47f8c47cc25ebL;
                        value3 = (value3 ^ 0x49c485832ed47543L) + 0x29d8c635237ea0c4L;
                        continue;
                     }

                     value4 = (value2 ^ -0x4aa2cd9816caf85dL) - 0x3e31085ac63cc4d7L;
                     value5 = (value3 ^ -0x2ec66962dbdcceadL) - 0x26ad4a0f2fb2b38L;
                     break;
                  }

                  if (count24 < 0) {
                     return countArray6;
                  }

                  value2 = (value2 ^ -0x1ae0e9d427ba6321L) + 0x7a1083d714211850L;
                  value3 = (value3 ^ -0x346098c3ffca15bbL) - 0x7a35f3a9573d89cdL;
               }
            } else {
               count26 = Math.min(3, count25);
               value2 = (value2 ^ -0x6d56cd94a3756efbL) - 0x7efe9876ee27c0c1L;
               value3 = (value3 ^ 0x47fc22db2c8582b5L) + 0x60ee7201e055ab94L;
            }
         }

         count22 = count20 - count21 + 3 >>> 2;
         value2 = (value4 ^ 0x9c1efeda63b7565L) - 0x2392c8baeae1c5baL;
         value3 = (value5 ^ 0x62caa68514e86c3L) + 0x7d42d37cc0ca39fcL;
      }
   }

   public SignumComparable[] resolveSignumComparableArray(SignumComparable signumComparable7) {
      return signumComparable7.countArray.length >= 80 && this.countArray.length - signumComparable7.countArray.length >= 40
         ? this.resolveSignumComparableArray2(signumComparable7)
         : this.resolveSignumComparableArrayBySignumComparable(signumComparable7);
   }

   private static SignumComparable resolveSignumComparable(int count20, int count21, Random random) {
      SignumComparable signumComparable7 = null;
      int count22 = 0;
      Object entry = null;
      SignumComparable signumComparable8 = null;
      SignumComparable signumComparable9 = new SignumComparable(count20, random);
      int count23 = count20 - 1;
      signumComparable7 = signumComparable9.resolveSignumComparable3(count23);
      signumComparable7.countArray[signumComparable7.countArray.length - 1] = signumComparable7.countArray[signumComparable7.countArray.length - 1] & -2;
      count22 = computeCount(count20);
      entry = new CurvePicker(signumComparable7, count22);
      signumComparable8 = ((CurvePicker)entry).resolveSignumComparable(signumComparable7, count21, random);

      while (signumComparable8 == null || signumComparable8.getCount3() != count20) {
         SignumComparable signumComparable10 = resolveSignumComparableByTime((long)(2 * count22));
         signumComparable7 = signumComparable7.resolveSignumComparable5(signumComparable10);
         if (signumComparable7.getCount3() != count20) {
            signumComparable9 = new SignumComparable(count20, random);
            int count24 = count20 - 1;
            signumComparable7 = signumComparable9.resolveSignumComparable3(count24);
         }

         signumComparable7.countArray[signumComparable7.countArray.length - 1] = signumComparable7.countArray[signumComparable7.countArray.length - 1] & -2;
         entry = new CurvePicker(signumComparable7, count22);
         signumComparable8 = ((CurvePicker)entry).resolveSignumComparable(signumComparable7, count21, random);
      }

      return signumComparable8;
   }

   private static int[] resolveIndexArray(int[] countArray6, int[] countArray7, int[] countArray8, int count20, long value2, int[] countArray9) {
      applyIndexArray(countArray6, countArray7, countArray8, count20, countArray9);
      if (count20 <= 512) {
         int[] countArray10 = resolveIndexArray(countArray9, count20);
         return resolveIndexArrayByIndexArray(countArray6, countArray7, countArray8, count20, value2, countArray10);
      } else {
         int[] countArray11 = resolveIndexArray(countArray6, count20, countArray7, count20, countArray9);
         int count21 = (int)value2;
         return resolveIndexArray(countArray11, countArray8, count20, count21);
      }
   }

   private int getCount() {
      return this.count < 0 ? 1 : 0;
   }

   public SignumComparable resolveSignumComparable(SignumComparable signumComparable7) {
      return this.computeCountBySignumComparable(signumComparable7) > 0 ? this : signumComparable7;
   }

   private static int[] resolveIndexArray(int[] countArray6, int count20, int[] countArray7, int count21, int[] countArray8) {
      int[] countArray9 = countArray8;
      int count22 = 0;
      int count23 = 0;
      long value2 = 0L;
      int count24 = 0;
      int count25 = 0;
      long value3 = 0L;
      int count26 = 0;
      long value4 = 0L;
      long value5 = 0x48e5548fd5e3ad73L;
      long value6 = 0x8a2f0e792a11570L;

      while (true) {
         int count27 = (int)(value5 % 24L);
         if (count27 == 17) {
            value2 = 0L;
            value5 = (value5 ^ -0x29f84d3e51976e6cL) - 0x6c0bb70fc48fad22L;
            value6 = (value6 ^ 0x27b51c84597a72c1L) - 0x7bec5ccb18b2b742L;
         } else if (count27 != 2) {
            if (count27 != 21) {
               guardBlock: {
                  searchBlock:
                  if (count27 != 8) {
                     if (count27 == 1) {
                        value2 = value3 >>> 32;
                        value5 = (value5 ^ -0x4e9c9ff1c66f3c88L) - 0x685de6793f242e39L;
                        value6 = (value6 ^ -0x61147eb72b198747L) - 0x33731e63701afc86L;
                        continue;
                     }

                     if (count27 == 3) {
                        if (countArray9.length >= count20 + count21) {
                           value5 = (value5 ^ -0x46c047b8c092ccc1L) - 0x400fdc21fadf4e8bL;
                           value6 = (value6 ^ -0x13be4d3a7d0c2fa9L) - 0x3c0e272cd9bd067dL;
                        } else {
                           value5 = (value5 ^ 0xe150651bc86aa17L) + 0x5d0f1aa123c5e6f9L;
                           value6 = (value6 ^ -0x2b458af632033b8dL) - 0x3d252de72033e39dL;
                        }
                        continue;
                     }

                     if (count27 == 11) {
                        count22 = count20 - 1;
                        value5 = (value5 ^ -0x57cd18203fe0b560L) - 0x7261c92c978980eeL;
                        value6 = (value6 ^ -0x2ebda0d0e3bee888L) + 0x949257f9f75d440L;
                        continue;
                     }

                     if (count27 != 20) {
                        if (count27 == 15) {
                           value4 = ((long)countArray7[count25] & 4294967295L) * ((long)countArray6[count24] & 4294967295L) + ((long)countArray9[count26] & 4294967295L) + value2;
                           value5 = (value5 ^ -0x62617bbc751d641fL) + 0x4a3ad8bd5f1d0774L;
                           value6 = (value6 ^ 0x3a7b770454f1f503L) - 0x2efbe691179b2fa6L;
                           continue;
                        }

                        if (count27 == 23) {
                           count24 = count22 - 1;
                           value5 = (value5 ^ -0x9150385d4e67f1dL) + 0x65454b85c9283bfaL;
                           value6 = (value6 ^ -0x7b9f63fc89c9714dL) - 0x3abfcadc0e2fb192L;
                           continue;
                        }

                        if (count27 == 4) {
                           count25 = count23;
                           value5 = (value5 ^ -0x11c5731337257e18L) - 0x7083dc3eab266806L;
                           value6 = (value6 ^ 0x34e27e8b60039a8dL) + 0x7672ae08f3c00a33L;
                           continue;
                        }

                        if (count27 == 19) {
                           count24 = count23;
                           value5 = (value5 ^ -0x5ec8d82c4ef9f73dL) + 0x108664a543bed382L;
                           value6 = (value6 ^ 0x234f7129920b8a7aL) + 0x72587c92026738f3L;
                           continue;
                        }

                        if (count27 == 6) {
                           count26 = count23 + 1 + count24;
                           value5 = (value5 ^ -0x1cd68bf2be1583d2L) + 0x4bddcc68ad3f7458L;
                           value6 = (value6 ^ -0x7f0537658bb3873bL) - 0x6680cad721b6822eL;
                           continue;
                        }

                        if (count27 == 14) {
                           value2 = 0L;
                           value5 = (value5 ^ 0x59b525422f0308d9L) - 0x31ba9ed6d21299fbL;
                           value6 = (value6 ^ 0x3039ba914547aed2L) - 0x33e030ea91897c89L;
                           continue;
                        }

                        if (count27 == 9) {
                           value2 = value4 >>> 32;
                           value5 = (value5 ^ 0x4251e3f1a255b73dL) - 0x5731c1ebfa237c68L;
                           value6 = (value6 ^ -0x2af9776a23db7c9fL) - 0x608d10619ad10d1eL;
                           continue;
                        }

                        if (count27 == 16) {
                           if (countArray9 == null) {
                              value5 = (value5 ^ 0x647795edd55d8120L) + 0x173b84c56869d8edL;
                              value6 = (value6 ^ -0x11dbe87020763462L) + 0x1de9bbb7c895bff4L;
                           } else {
                              value5 = (value5 ^ -0x7c009ac9b3452b59L) + 0x5bbec01d968cb77cL;
                              value6 = (value6 ^ -0x14b007eea4113866L) + 0x34c2ad0f9ea0e3c8L;
                           }
                           continue;
                        }

                        if (count27 == 12) {
                           countArray9[count25] = (int)value3;
                           value5 = (value5 ^ 0x491165f5e9f6c011L) + 0x5ec4cc762ca7aeecL;
                           value6 = (value6 ^ 0x3ac4abb0102e761aL) + 0x5753deffb506bea4L;
                           continue;
                        }

                        if (count27 == 7) {
                           count24--;
                           value5 = (value5 ^ 0x30b40ca1b5026d17L) + 0x3b26a31ada934da6L;
                           value6 = (value6 ^ 0x6c1df815333e4694L) - 0x5150bc0f1ccb1277L;
                           break guardBlock;
                        }

                        if (count27 == 13) {
                           break searchBlock;
                        }

                        if (count27 != 0) {
                           if (count27 != 22) {
                              if (count27 != 10) {
                                 if (count27 != 5) {
                                    countArray9[count26] = (int)value4;
                                    value5 = (value5 ^ -0x2983924f5b91dc6cL) + 0x625290fa6d867bdbL;
                                    value6 = (value6 ^ -0x3c8623368d9ae8L) - 0x137a559f624df7f1L;
                                 } else {
                                    countArray9 = new int[count20 + count21];
                                    value5 = (value5 ^ 0x40e7382a6b8537a1L) + 0x352bbf4138fc0345L;
                                    value6 = (value6 ^ 0x5a0a59737a5c2887L) + 0x1f251c72d93be883L;
                                 }
                              } else {
                                 value3 = ((long)countArray7[count24] & 4294967295L) * ((long)countArray6[count22] & 4294967295L) + value2;
                                 value5 = (value5 ^ -0x72b2ebb011300ee2L) - 0x72f8a187629ab5f0L;
                                 value6 = (value6 ^ 0x424f27032f1eb651L) + 0x8208f076ffcf9b1L;
                              }
                              continue;
                           }
                           break guardBlock;
                        }
                     } else {
                        count25--;
                        count26--;
                        value5 = (value5 ^ -0x6af9f3a2c3c53612L) - 0x6d7ed9957d635a8aL;
                        value6 = (value6 ^ 0x4e297071662679a5L) + 0x5bd90b7a2256c259L;
                     }

                     if (count25 < 0) {
                        long value7 = (value5 ^ 0x39ad82e550202bd3L) - 0xe2aa7f25ec2749fL;
                        long value8 = (value6 ^ 0x7eec70f911686222L) + 0x6c1c140e0c23b8eeL;
                        countArray9[count24] = (int)value2;
                        value5 = (value7 ^ 0x2aaa01ee91ce55c5L) + 0xa5872240c9e9396L;
                        value6 = (value8 ^ 0x1e5f8b528048d35cL) + 0x509b7fd3c1b44b98L;
                     } else {
                        value5 = (value5 ^ 0x798e03d276a54c2bL) - 0x14ff8b275363a40cL;
                        value6 = (value6 ^ -0x2d876b0cc5b9dbdbL) - 0x230e851e46e5a92eL;
                     }
                     continue;
                  } else {
                     count24--;
                     count25--;
                     value5 = (value5 ^ -0x1c1282120e0e50b6L) - 0x3817f73697d92865L;
                     value6 = (value6 ^ 0x3e25f0821607f670L) + 0x2b7cca6846534629L;
                  }

                  if (count24 < 0) {
                     long value9 = (value5 ^ -0x11ed1ff46d06da6cL) - 0x3af15e38a6ce5341L;
                     long value10 = (value6 ^ -0x70202d9962e24c20L) + 0x7be2f2b44cad13a7L;
                     countArray9[count22] = (int)value2;
                     value5 = (value9 ^ -0x5b2e3d8665f138c9L) + 0x50be6996d41337a8L;
                     value6 = (value10 ^ -0x68e03c00bb22b8ceL) - 0x29c33a837c16afecL;
                  } else {
                     value5 = (value5 ^ 0x4624f04b36dca51cL) + 0x4252dedb796f1151L;
                     value6 = (value6 ^ -0x34eb8a15549a7029L) - 0x2348bb6d9b1ebf6fL;
                  }
                  continue;
               }

               if (count24 < 0) {
                  return countArray9;
               }

               value5 = (value5 ^ -0x81f3a5d559908aeL) + 0x662b628a5907ab32L;
               value6 = (value6 ^ -0x2789acc6716ee4f6L) + 0x2f2c6c774a9b6615L;
            } else {
               count23 = count21 - 1;
               value5 = (value5 ^ -0x15868a600eb598d0L) - 0x487ded2023d695e5L;
               value6 = (value6 ^ -0xb1c89ae9c8a035bL) - 0x6e7f8f4d8e8fad5cL;
            }
         } else {
            count25 = count23 + 1 + count22;
            value5 = (value5 ^ -0x2a963f108edc373L) + 0x51479668768d22deL;
            value6 = (value6 ^ 0xec6f55bab62f792L) + 0x5cbfbb81987d9b95L;
         }
      }
   }

   private SignumComparable getSignumComparable() {
      return this.resolveSignumComparable(false);
   }

   private static int[] resolveIndexArray(int[] countArray6, int[] countArray7, int count20, int count21) {
      int count22 = 0;
      int count23 = 0;
      int count24 = 0;
      int count25 = 0;
      int count26 = 0;
      long value2 = 0x559c6c9aaa60a2dfL;
      long value3 = -0x1af71618a14e38bL;

      while (true) {
         int count27 = (int)(value2 % 12L);
         if (count27 == 5) {
            count22 += computeCountByIndexArray(countArray6, countArray7, count20);
            value2 = (value2 ^ 0x4db157a85645f080L) + 0x241eb783751f74edL;
            value3 = (value3 ^ -0x158b89e8cbfa3b88L) - 0x123e2c0b3646d85cL;
         } else if (count27 != 10) {
            if (count27 != 7) {
               if (count27 != 0) {
                  if (count27 != 4) {
                     if (count27 != 1) {
                        if (count27 != 11) {
                           if (count27 != 3) {
                              long value4;
                              long value5;
                              searchBlock: {
                                 if (count27 != 2) {
                                    if (count27 != 6) {
                                       if (count27 == 9) {
                                          count25 = countArray6[countArray6.length - 1 - count24];
                                          value2 = (value2 ^ 0x306e7ac4e7416e73L) + 0x57be6ba64074be30L;
                                          value3 = (value3 ^ 0x2a39a337e9475116L) + 0x4d7c1b1534ce8b22L;
                                          continue;
                                       }

                                       value4 = (value2 ^ 0x4b51b245713e90afL) + 0x17aeea9de5427a57L;
                                       value5 = (value3 ^ 0x3e807d9809508924L) + 0x2837aa699746b08eL;
                                       break searchBlock;
                                    }
                                 } else {
                                    value2 = (value2 ^ 0x5a563e5bf0c2b8f8L) + 0x62467273bc53ec5cL;
                                    value3 = (value3 ^ -0x6b88a9287eb1bda1L) - 0x67559027f55f5025L;
                                 }

                                 if (count22 > 0) {
                                    value2 = (value2 ^ -0x1c4684596c968c74L) - 0x3558bbb342869c1dL;
                                    value3 = (value3 ^ 0x250b74e9f946b2d1L) - 0x6ae5f03bfde9c3e7L;
                                    continue;
                                 }

                                 value4 = (value2 ^ -0x56143327ccabac0eL) + 0x79e223eae4a1495eL;
                                 value5 = (value3 ^ 0x24fe1301f54d777bL) + 0x1824b4f9ebc4dabfL;
                              }

                              if (computeCount(countArray6, countArray7, count20) < 0) {
                                 return countArray6;
                              }

                              value2 = (value4 ^ -0x2e5cdddce8973ecL) - 0x3a7f6503282f9785L;
                              value3 = (value5 ^ 0x1c9d207c84c8d753L) + 0x171d0fc077383921L;
                           } else {
                              count23 = count20;
                              value2 = (value2 ^ 0x3036ac7c770d82b1L) + 0x2e21e1aad56f950aL;
                              value3 = (value3 ^ 0x1966b1ec7b12b4e7L) - 0x27058b0b132f8e38L;
                           }
                        } else {
                           count22 = 0;
                           value2 = (value2 ^ -0x7fccf010293e3b64L) + 0x3ffcde3103833a64L;
                           value3 = (value3 ^ 0x7ceb5db2d2fa92b0L) - 0x721fcdac9fa89c03L;
                        }
                     } else {
                        computeCountByIndexArray(countArray6, countArray7, count20);
                        value2 = (value2 ^ -0x2898574a5154fe95L) - 0x23e3cdef78aedf06L;
                        value3 = (value3 ^ 0x2473209b38ca4d03L) - 0x2dbcb4fe78bf68b9L;
                     }
                  } else {
                     count24++;
                     if (--count23 > 0) {
                        value2 = (value2 ^ 0x1773cd5c2b7d8f10L) + 0x1007f0fb27584141L;
                        value3 = (value3 ^ 0xf2f8d329a67caa9L) + 0x14151089b4f8ca41L;
                     } else {
                        value2 = (value2 ^ -0x128fa9bfb2889b39L) - 0x756a8afd5a265021L;
                        value3 = (value3 ^ -0x372a1eef8f48a091L) + 0x2a09ff44d5f250f0L;
                     }
                  }
               } else {
                  count24 = 0;
                  value2 = (value2 ^ -0x4c5163a77e1baa90L) + 0x458e09bdb8b49e2dL;
                  value3 = (value3 ^ -0xd955c6ef16cedf7L) + 0x55cdd3d0d8bda6d2L;
               }
            } else {
               count22 += computeCount(countArray6, count24, count20, count26);
               value2 = (value2 ^ 0x4d80bf40fd92b787L) - 0x53650d6d0d798b4L;
               value3 = (value3 ^ -0x435bc71512059c97L) + 0x3d62c5d6c30ba8afL;
            }
         } else {
            int count28 = count21 * count25;
            count26 = computeCountByIndexArray(countArray6, countArray7, count24, count20, count28);
            value2 = (value2 ^ -0x32d95289d59cc39cL) - 0x5b08f095cf9790f3L;
            value3 = (value3 ^ 0x22fd3a634ddc466aL) - 0x1e7b5da3f02b2b64L;
         }
      }
   }

   public int[] resolveIndexArray(int[] countArray6) {
      int[] countArray7 = countArray6;
      int count20 = 0;
      int count21 = 0;
      long value2 = 0x23b54b094c7dbe0cL;
      long value3 = -0x6441b21d45587549L;

      while (true) {
         int count22 = (int)(value2 % 9L);
         if (count22 == 5) {
            countArray7[0] = 1;
            break;
         }

         long value4;
         long value5;
         if (count22 != 7) {
            if (count22 == 4) {
               count20 = 0;
               value2 = (value2 ^ -0x374f7828e9adfb7aL) - 0x7f2e6f6e32147b1fL;
               value3 = (value3 ^ 0x39d2dfd225749361L) - 0x7bb5dab31245b362L;
               continue;
            }

            if (count22 != 1) {
               if (count22 == 8) {
                  count21 = countArray7.length - 1;
                  value2 = (value2 ^ -0x16e998515e1397dL) - 0x265a10cf8f3bd7f7L;
                  value3 = (value3 ^ 0x6e77f6bce6b3dbb3L) + 0x14137d64306306f6L;
                  continue;
               }

               if (count22 == 0) {
                  count20 = ++countArray7[count21];
                  value2 = (value2 ^ -0x6d1ece53de7e80ecL) + 0x67bd6b2b302ea397L;
                  value3 = (value3 ^ -0xd3989a2068115f7L) - 0x250c00048580d36bL;
                  continue;
               }

               if (count22 == 3) {
                  countArray7 = new int[countArray7.length + 1];
                  countArray7[0] = 1;
                  break;
               }

               count21--;
               value2 = (value2 ^ 0x1985f7b8c4a87ae7L) + 0x5896e90708c63d1bL;
               value3 = (value3 ^ 0x580df56ef27fc1f7L) - 0x56a996c78cdb42e7L;
            }

            if (count21 >= 0) {
               value2 = (value2 ^ -0x5c6f9af0d9a60733L) - 0x7d9d9d71eef8061bL;
               value3 = (value3 ^ 0x5ce67e9bfd2c72e1L) + 0x392657cc0b1a6accL;
               continue;
            }

            value4 = (value2 ^ 0x75f1de946e8f76f8L) - 0xb4d3e226d99b8d8L;
            value5 = (value3 ^ -0x1f2650ca9f022706L) + 0x2f669072dea6aa0cL;
         } else {
            if (count20 == 0) {
               value2 = (value2 ^ -0x61e8887b8d24b322L) + 0x6928b48748bce58fL;
               value3 = (value3 ^ 0x198dd272223e6802L) - 0x3e1af522ac98d573L;
               continue;
            }

            value4 = (value2 ^ -0x7219193b317a9ba8L) + 0x4d985f86038ce6b8L;
            value5 = (value3 ^ -0x788c1003b9e851beL) + 0x2d48aa0da24336e9L;
         }

         if (count20 != 0) {
            break;
         }

         value2 = (value4 ^ 0x2761ea747412e48fL) + 0x4114f63c8f0923adL;
         value3 = (value5 ^ -0x74d2462c88c4b590L) - 0x599b4e8bafbf7dfdL;
      }

      return countArray7;
   }

   public long getTimestamp() {
      long value2 = 0L;
      value2 = 0L;

      for (int index = 1; index >= 0; index--) {
         value2 = (value2 << 32) + ((long)this.computeCountByIndex(index) & 4294967295L);
      }

      return value2;
   }

   private SignumComparable resolveSignumComparable(int count20) {
      int count21 = 0;
      Object entry = null;
      count21 = this.countArray.length;
      if (count21 > count20) {
         entry = new int[count20];
         System.arraycopy(this.countArray, count21 - count20, entry, 0, count20);
         return new SignumComparable(resolveIndexArray3((int[])entry), 1);
      } else {
         return this.getSignumComparable5();
      }
   }

   private static void applyIndexArray(int[] countArray6, int[] countArray7, int[] countArray8, int count20, int[] countArray9) throws RuntimeException {
      if (count20 % 2 == 0) {
         if (count20 < 1) {
            throw new IllegalArgumentException("invalid input length: " + count20);
         } else if (count20 > countArray6.length || count20 > countArray7.length || count20 > countArray8.length || countArray9 != null && count20 > countArray9.length) {
            throw new IllegalArgumentException("input array length out of bound: " + count20);
         }
      } else {
         throw new IllegalArgumentException("input array length must be even: " + count20);
      }
   }

   private static final int[] resolveIndexArray(int[] countArray6, int count20, int[] countArray7) {
      int[] countArray8 = countArray7;
      int count21 = 0;
      count21 = count20 << 1;
      if (countArray7 == null || countArray7.length < count21) {
         countArray8 = new int[count21];
      }

      applyIndexArray(countArray6, count20, countArray8, count21);
      return resolveIndexArray(countArray6, count20, countArray8, count21);
   }

   private String resolveText(int count20) {
      int count21 = 0;
      String[] textArray = null;
      SignumComparable signumComparable7 = null;
      int count22 = 0;
      SignumComparable signumComparable8 = null;
      CurveAccumulator curveAccumulator = null;
      CurveAccumulator curveAccumulator2 = null;
      CurveAccumulator curveAccumulator3 = null;
      CurveAccumulator curveAccumulator4 = null;
      SignumComparable signumComparable9 = null;
      SignumComparable signumComparable10 = null;
      StringBuilder builder = null;
      int count23 = 0;
      int count24 = 0;
      long value2 = 0x480052d445617a74L;
      long value3 = -0x32213aee50ef2b1L;

      while (true) {
         int count25 = (int)(value2 % 27L);
         if (count25 == 14) {
            builder.append(stringArray[count24]);
            value2 = (value2 ^ 0x1a0e568bf38515fdL) - 0x29558ae550d07b67L;
            value3 = (value3 ^ -0x7022262c3c17ad46L) + 0x1c73bc5357f00b7bL;
         } else if (count25 != 0) {
            if (count25 != 4) {
               searchBlock:
               if (count25 != 22) {
                  if (count25 == 26) {
                     curveAccumulator3 = new CurveAccumulator(signumComparable8.countArray);
                     value2 = (value2 ^ -0x74a5baf016a4b5a0L) - 0x2eb9d5dd577415a8L;
                     value3 = (value3 ^ -0x6fd2048441e0081eL) + 0x58e3472f73f1824bL;
                     continue;
                  }

                  if (count25 != 15) {
                     if (count25 == 5) {
                        signumComparable7 = signumComparable9;
                        value2 = (value2 ^ -0x228570c972a02173L) - 0x6c80923bc37ad759L;
                        value3 = (value3 ^ -0x6200f01142ab8e83L) + 0x75b5bc53a18db13bL;
                        continue;
                     }

                     if (count25 == 13) {
                        signumComparable8 = signumComparableArray3[count20];
                        value2 = (value2 ^ -0x5f1d9f31da47047dL) - 0x70ca0eab0162cdd6L;
                        value3 = (value3 ^ -0x49398a8922ccce91L) + 0x4530ef57fd13e486L;
                        continue;
                     }

                     if (count25 == 24) {
                        count23 = count22 - 2;
                        value2 = (value2 ^ 0x75a61317f4f888f6L) + 0x1f2e92476f0388f5L;
                        value3 = (value3 ^ 0x6a0b9a6e847ef6ffL) + 0x620b642cfa02b261L;
                        continue;
                     }

                     if (count25 == 18) {
                        break searchBlock;
                     }

                     if (count25 == 12) {
                        count22 = 0;
                        value2 = (value2 ^ -0x183627b95af6d3cbL) - 0x7f8cc233596ccec7L;
                        value3 = (value3 ^ 0x17f875772e480ef4L) - 0x6a5ce3e68517ad38L;
                        continue;
                     }

                     if (count25 == 11) {
                        curveAccumulator4 = curveAccumulator2.resolveCurveAccumulator4(curveAccumulator3, curveAccumulator);
                        value2 = (value2 ^ -0x7490a07292528788L) + 0x6adb8df68cdffaf4L;
                        value3 = (value3 ^ 0x772ed577334faf16L) - 0x4ee4d7da12b92e57L;
                        continue;
                     }

                     if (count25 == 20) {
                        if (this.count == 0) {
                           break;
                        }

                        value2 = (value2 ^ -0x14b9254903d53ef8L) - 0x27754c5e9fe574b6L;
                        value3 = (value3 ^ -0x7537bcc0fdbffefeL) + 0x28bcae1f56585baL;
                        continue;
                     }

                     if (count25 == 9) {
                        curveAccumulator = new CurveAccumulator();
                        value2 = (value2 ^ 0x4e39ca14aa7be48dL) - 0x6a11528676780570L;
                        value3 = (value3 ^ -0x4b360e14ae150cc2L) - 0x5109a699eaed67e7L;
                        continue;
                     }

                     if (count25 == 25) {
                        builder.append('-');
                        value2 = (value2 ^ -0xb1bab181c40b0c2L) - 0x62d948c8f78e90b3L;
                        value3 = (value3 ^ -0x4248b41c58682d81L) - 0x2d88d241b8250073L;
                        continue;
                     }

                     if (count25 == 21) {
                        count24 = countArray3[count20] - textArray[count23].length();
                        value2 = (value2 ^ 0x1962577364249b1L) - 0x9ae1ad3168aa208L;
                        value3 = (value3 ^ -0x3400e677a3f7c360L) + 0x1e5447135e3e43a2L;
                        continue;
                     }

                     if (count25 == 16) {
                        break;
                     }

                     if (count25 == 6) {
                        if (count24 == 0) {
                           value2 = (value2 ^ -0x4e6fc16cac3233abL) + 0x77b968c2acb0eba0L;
                           value3 = (value3 ^ 0x312606ef532a86a6L) - 0x20770f2c7b19228cL;
                        } else {
                           value2 = (value2 ^ 0x19a8cd33b80d7c41L) + 0xf3bb6bd12450a0eL;
                           value3 = (value3 ^ 0x59bca2333f0c17e2L) + 0x3769a67742b5750fL;
                        }
                        continue;
                     }

                     if (count25 == 1) {
                        curveAccumulator2 = new CurveAccumulator(signumComparable7.countArray);
                        value2 = (value2 ^ 0x323e3b7c652e29baL) - 0x1289b351b69c0da5L;
                        value3 = (value3 ^ 0x879dca5b654dc09L) - 0x57c1a6fcff8f344eL;
                        continue;
                     }

                     if (count25 == 23) {
                        count21 = (4 * this.countArray.length + 6) / 7;
                        value2 = (value2 ^ -0xe9a8b9a8d6ae4dL) - 0x69a6638a19ac6abaL;
                        value3 = (value3 ^ -0x9729961b5a5b756L) + 0xdeace856fc86d4dL;
                        continue;
                     }

                     if (count25 == 19) {
                        int count26 = signumComparable7.count * signumComparable8.count;
                        signumComparable10 = curveAccumulator4.resolveSignumComparableByIndex(count26);
                        value2 = (value2 ^ -0x55e4a1a5a405d33aL) + 0x5dba0b7ddb8a913eL;
                        value3 = (value3 ^ -0x7bd8d16421ef10afL) + 0x6e738aebfde53480L;
                        continue;
                     }

                     if (count25 == 17) {
                        builder.append(textArray[count22 - 1]);
                        value2 = (value2 ^ -0x22e054cf6b5f7aabL) - 0x104584fead81f809L;
                        value3 = (value3 ^ 0x54b8ba13adb00301L) - 0x160e245a8bb266aeL;
                        continue;
                     }

                     if (count25 == 8) {
                        textArray = new String[count21];
                        value2 = (value2 ^ 0x1b25e33fd04d2ae0L) + 0x462d32be8b853f73L;
                        value3 = (value3 ^ 0x39bbcb531505f242L) - 0x62ccd93ad479d62dL;
                        continue;
                     }

                     if (count25 == 2) {
                        int count27 = signumComparable7.count * signumComparable8.count;
                        signumComparable9 = curveAccumulator.resolveSignumComparableByIndex(count27);
                        value2 = (value2 ^ -0x2b69a859810316a3L) - 0x2cf5839be42b7edcL;
                        value3 = (value3 ^ 0x19a2f5dad110d705L) - 0x1fbb6cd290c39a4aL;
                        continue;
                     }

                     if (count25 == 7) {
                        signumComparable7 = this.getSignumComparable5();
                        value2 = (value2 ^ 0x3b1f4b0df66101faL) - 0x136a054914c61fdeL;
                        value3 = (value3 ^ -0x47a594ccceecadefL) - 0x4678e5e8c0eccdc3L;
                        continue;
                     }

                     if (count25 != 10) {
                        textArray[count22++] = Long.toString(signumComparable10.getTimestamp(), count20);
                        value2 = (value2 ^ -0x13d992090a176e11L) + 0x59879604eada297dL;
                        value3 = (value3 ^ -0x5205630e58c54931L) - 0x5d068a2a807d1aa5L;
                        continue;
                     }

                     count23--;
                     value2 = (value2 ^ -0x7407f5029c822586L) + 0x7dc235b19bd07fdeL;
                     value3 = (value3 ^ 0x45a2b60640d0ca6cL) - 0x5a18286bad3e1ad5L;
                  }

                  if (count23 < 0) {
                     return builder.toString();
                  }

                  value2 = (value2 ^ 0x1794969fbc6a8113L) + 0x44c602dabe7d38c3L;
                  value3 = (value3 ^ -0x1d8642d18d797241L) - 0x42a9b4822ba1fe13L;
                  continue;
               } else {
                  value2 = (value2 ^ 0x7b0a744b33c3839fL) - 0x983ac45b8df2b42L;
                  value3 = (value3 ^ 0x460ed02eca9683d3L) - 0x7f0d139c77f72ce1L;
               }

               if (signumComparable7.count == 0) {
                  long value4 = (value2 ^ -0x5d671e57e69ec7eaL) - 0x49da1f9d633d1a49L;
                  long value5 = (value3 ^ 0x2bb1fe1b38059d30L) - 0x1485ea87e695ebbcL;
                  builder = new StringBuilder(count22 * countArray3[count20] + 1);
                  value2 = (value4 ^ 0x73ff8ca6cbaadd7eL) + 0x756431064fe3133L;
                  value3 = (value5 ^ 0x70accb235a874c3fL) - 0x7cad9a9ea5daff4L;
               } else {
                  value2 = (value2 ^ -0x2b83dd752026a432L) + 0x2f6c1960af9e2d1dL;
                  value3 = (value3 ^ 0x44fd114cb2d54764L) + 0x26b29de4ade30ce9L;
               }
            } else {
               builder.append(textArray[count23]);
               value2 = (value2 ^ 0xc37c69d7205ac2bL) - 0xb89de6d4c56ec01L;
               value3 = (value3 ^ 0x22c62a8fb1c02a9L) + 0x7cc2a616ea79c4L;
            }
         } else if (this.count >= 0) {
            value2 = (value2 ^ -0x4e994b14084ee45cL) - 0x51161b8cb2c2abe7L;
            value3 = (value3 ^ 0x120c77cfca197be4L) + 0x14db4b8218292577L;
         } else {
            value2 = (value2 ^ -0xebbe5fb0bbfc92L) + 0x66ad444f7069b280L;
            value3 = (value3 ^ 0x43d8d33e08d01817L) - 0x5a120cae0d57ce6eL;
         }
      }

      return "0";
   }

   private static int[] resolveIndexArray2(int[] countArray6) {
      int count20 = 0;
      int count21 = 0;
      count20 = countArray6.length;
      count21 = 0;

      while (count21 < count20 && countArray6[count21] == 0) {
         count21++;
      }

      return Arrays.copyOfRange(countArray6, count21, count20);
   }

   public boolean matchesRandomByRandom(int count20, Random random) {
      int byteValue = 0;
      int count21 = 0;
      int count22 = 0;
      byteValue = (byte)0;
      count21 = (Math.min(count20, 2147483646) + 1) / 2;
      count22 = this.getCount3();
      if (count22 >= 100) {
         byte byteValue2;
         if (count22 >= 256) {
            if (count22 >= 512) {
               if (count22 >= 768) {
                  if (count22 >= 1024) {
                     byteValue2 = 2;
                  } else {
                     byteValue2 = 4;
                  }
               } else {
                  byteValue2 = 8;
               }
            } else {
               byteValue2 = 15;
            }
         } else {
            byteValue2 = 27;
         }

         int count23 = count21 >= byteValue2 ? byteValue2 : count21;
         return this.matchesRandom(count23, random) && this.isActive2();
      } else {
         byteValue = 50;
         int count24 = count21 >= byteValue ? byteValue : count21;
         return this.matchesRandom(count24, random);
      }
   }

   public SignumComparable resolveSignumComparable4(SignumComparable signumComparable7) {
      boolean flag = false;
      return this.resolveSignumComparable(signumComparable7, flag);
   }

   public static void applyIndexArrayByIndexArray(int[] countArray6, int count20, int count21) {
      int count22 = 0;
      int count23 = 0;
      int count24 = 0;
      int count25 = 0;
      int count26 = 0;
      long value2 = 0xaf6fb25ace6cf09L;
      long value3 = -0x552633b935207d49L;

      while (true) {
         int count27 = (int)(value2 % 12L);
         if (count27 != 10) {
            if (count27 == 9) {
               count24 = countArray6[count23 + 1];
               value2 = (value2 ^ 0xfb367a08ac160e4L) + 0x48729a994556f101L;
               value3 = (value3 ^ -0x3415dc2366d05ae5L) + 0x1f790c18a0da7400L;
               continue;
            }

            if (count27 != 11) {
               if (count27 == 3) {
                  break;
               }

               if (count27 != 1) {
                  if (count27 != 4) {
                     if (count27 != 7) {
                        if (count27 != 6) {
                           if (count27 != 8) {
                              if (count27 != 0) {
                                 count23 = 0;
                                 value2 = (value2 ^ 0x5e1eff85217fd2a4L) - 0x2c3417ac8fef7751L;
                                 value3 = (value3 ^ 0x721ee8220997e4cfL) - 0x3978b8974a093bccL;
                              } else {
                                 count25 = count23 + count20 - 1;
                                 value2 = (value2 ^ -0x4e0ee390330f189L) + 0x1424ad6dba0cca5bL;
                                 value3 = (value3 ^ -0x59c00bba8e679948L) - 0x3baf5df9cf9fe977L;
                              }
                           } else {
                              count26 = count24;
                              value2 = (value2 ^ 0x3fbbdb4dfeb6d7faL) + 0xb437770360bf8f3L;
                              value3 = (value3 ^ -0x39fc3ef3c3d79497L) + 0x10b5c368c52ed901L;
                           }
                        } else {
                           countArray6[count23] = count26 << count21 | count24 >>> count22;
                           value2 = (value2 ^ -0x195eeb201c090e90L) - 0x4bada72a1247d2a7L;
                           value3 = (value3 ^ 0x6f78bac12feb896dL) - 0x2e0cf3dc95640671L;
                        }
                     } else {
                        if (count21 == 0) {
                           break;
                        }

                        long value4 = (value2 ^ -0x5ea08ad8bfac46c5L) + 0x203239bccc72647fL;
                        long value5 = (value3 ^ 0xec9d7451dbb1d4bL) - 0x28fd75d438e4817eL;
                        count22 = 32 - count21;
                        value2 = (value4 ^ -0x26ee8fe586f8adf8L) + 0x6be66463da7f681eL;
                        value3 = (value5 ^ -0x79b46b5ed0dccecaL) + 0x1f3cfc7c59c62e81L;
                     }
                  } else {
                     count24 = countArray6[count23];
                     value2 = (value2 ^ 0x66b0dbfd3700bd41L) - 0x514ffd2ae9baedf5L;
                     value3 = (value3 ^ -0x3a8433e4bb23a8a8L) + 0x87d2359d7fba996L;
                  }
               } else {
                  if (count20 == 0) {
                     break;
                  }

                  value2 = (value2 ^ 0x76cdab1cd261ec87L) - 0x26cf3fe52ce64833L;
                  value3 = (value3 ^ -0x2a2aef1e5b2d74bfL) - 0x51b24cf4f9b61f34L;
               }
               continue;
            }

            count23++;
            value2 = (value2 ^ 0x1644e098edec7819L) - 0x4be9125003ea0520L;
            value3 = (value3 ^ -0x693661f6c6716abfL) - 0x77f35f915b919358L;
         }

         if (count23 >= count25) {
            countArray6[count20 - 1] = countArray6[count20 - 1] << count21;
            return;
         }

         value2 = (value2 ^ -0xd7a3cd276e48ba4L) + 0x22c1fddfb8c65b6aL;
         value3 = (value3 ^ -0x655264c9ca4bc832L) + 0x676f52e5231c1634L;
      }
   }

   public SignumComparable resolveSignumComparable4(int count20) {
      if (this.count != 0) {
         if (count20 <= 0) {
            if (count20 != 0) {
               int count21 = -count20;
               return this.resolveSignumComparable7(count21);
            } else {
               return this;
            }
         } else {
            return new SignumComparable(resolveIndexArrayByIndexArray(this.countArray, count20), this.count);
         }
      } else {
         return signumComparable5;
      }
   }

   private SignumComparable[] resolveSignumComparableArrayBySignumComparable(SignumComparable signumComparable7) {
      SignumComparable[] signumComparable8 = new SignumComparable[2];
      CurveAccumulator entry = new CurveAccumulator();
      CurveAccumulator entry2 = new CurveAccumulator(this.countArray);
      CurveAccumulator entry3 = new CurveAccumulator(signumComparable7.countArray);
      CurveAccumulator entry4 = entry2.resolveCurveAccumulator(entry3, entry);
      int count20 = this.count != signumComparable7.count ? -1 : 1;
      signumComparable8[0] = entry.resolveSignumComparableByIndex(count20);
      int count21 = this.count;
      signumComparable8[1] = entry4.resolveSignumComparableByIndex(count21);
      return signumComparable8;
   }

   private static void updateState() {
      throw new ArithmeticException("BigInteger would overflow supported range");
   }

   public SignumComparable getSignumComparable4() {
      int[] entry = null;
      entry = new int[this.getCountAsIndex()];

      for (int index = 0; index < entry.length; index++) {
         int count20 = entry.length - index - 1;
         entry[index] = ~this.computeCountByIndex(count20);
      }

      return resolveSignumComparable((int[])entry);
   }

   public int getCount4() {
      int count20 = 0;
      int count21 = 0;
      long value2 = 0x5aa573c87e160e24L;
      long value3 = 0x742e697809ba2688L;

      while (true) {
         int count22 = (int)(value2 % 9L);
         searchBlock:
         if (count22 != 5) {
            if (count22 == 8) {
               if (count20 != -2) {
                  break;
               }

               value2 = (value2 ^ 0x3bd459ac6736af2dL) - 0x8a26c733a1271fcL;
               value3 = (value3 ^ -0x109c4439ca1826f8L) + 0x326b7cb442cd34d9L;
               continue;
            }

            if (count22 != 2) {
               if (count22 != 6) {
                  if (count22 == 1) {
                     count20 = 0;
                     value2 = (value2 ^ 0x6845b8856ec98a31L) + 0x36587e1d98e33628L;
                     value3 = (value3 ^ -0x5a945d861ee53d76L) + 0x544d9ea766401c13L;
                     continue;
                  }

                  if (count22 == 3) {
                     if (this.count != 0) {
                        long value4 = (value2 ^ -0x24982a0d16cae85fL) - 0x735583d57122426cL;
                        long value5 = (value3 ^ -0x10b0706b5647b93L) - 0x277940be6f053142L;
                        count21 = 0;
                        value2 = (value4 ^ 0x771e5156bda720b1L) - 0x56543b7189be4c38L;
                        value3 = (value5 ^ 0x4121afc2a43b1201L) - 0x5440b93500f21d44L;
                     } else {
                        value2 = (value2 ^ -0x79ebcf2ca208417dL) + 0x37bdd948e31b6472L;
                        value3 = (value3 ^ -0xad83efb21c875caL) - 0x2dc64ab0e2713aedL;
                     }
                     continue;
                  }

                  if (count22 != 7) {
                     count20 = this.count2 - 2;
                     value2 = (value2 ^ 0x23269a4b69cf8664L) - 0x71704b942903e2cL;
                     value3 = (value3 ^ 0x2a59a96dab9eb2eeL) - 0x6f36ad599d63073fL;
                     continue;
                  }
                  break searchBlock;
               }

               count21++;
               value2 = (value2 ^ 0x1212e927ed5c5545L) - 0x2286a65ed9730ef2L;
               value3 = (value3 ^ 0x6b1c7f4bf9895108L) + 0x50ad363ccfd168cfL;
            }

            int count23;
            if ((count23 = this.computeCountByIndex(count21)) != 0) {
               long value6 = (value2 ^ 0x6ae33287558ef945L) - 0x5a6f713f3142a70eL;
               long value7 = (value3 ^ -0x60f7d8d89f7f8cf8L) - 0x5a89221fc8caa4cfL;
               count20 += (count21 << 5) + Integer.numberOfTrailingZeros(count23);
               value2 = (value6 ^ -0x194c0423e18faf8L) - 0x7164629d098e912cL;
               value3 = (value7 ^ -0x520cd8961fdbffd9L) + 0x52f35528b910fcceL;
            } else {
               value2 = (value2 ^ 0x601ac32092246d49L) - 0x4f867fdff1ad1b12L;
               value3 = (value3 ^ -0x671652768d6b026bL) - 0x602a988db6d6333aL;
            }
            continue;
         } else {
            count20--;
            value2 = (value2 ^ 0x1a53aa548d5556d0L) + 0x625956c838eb96faL;
            value3 = (value3 ^ -0x729db280dc5cfc15L) - 0x4fcb5fd5977f6904L;
         }

         this.count2 = count20 + 2;
         break;
      }

      return count20;
   }

   private byte[] getByteArray() {
      int count20 = 0;
      long value2 = 0x4d90e9da55d6ccc9L;
      long value3 = 0x3b09b003b1a1370cL;
      count20 = this.countArray.length;
      value2 = (value2 ^ -0x7b1ec0cb2fa21d7cL) - 0x602b6b41873fb08fL;
      value3 = (value3 ^ -0x674257a810df7313L) + 0x752f06ca01f7a83aL;
      long value4;
      long value5;
      int count21;
      if (count20 != 0) {
         value4 = (value2 ^ -0x6c634918a56bd92L) - 0x63856e3255bf9c70L;
         value5 = (value3 ^ -0x707625d18f2279abL) - 0x4558a811fb885587L;
         count21 = (count20 - 1 << 5) + numberOfLeadingZeros(this.countArray[0]);
      } else {
         value2 = (value2 ^ -0x7519a8ee79c2db5eL) + 0x4959f5d2bdac4a44L;
         value3 = (value3 ^ 0x6665ab6c9687e661L) - 0x2c749754e1e1f5b3L;
         count21 = 0;
         value4 = (value2 ^ -0x33bba677546292a7L) + 0x4c3bc7779862d527L;
         value5 = (value3 ^ 0x15536d12b8dbb9e5L) + 0xad0ad11675557a5L;
      }

      int count22 = count21;
      int count23 = count22 + 7 >>> 3;
      byte[] byteValueArray = new byte[count23];
      int count24 = count23 - 1;
      int count25 = 4;
      int count26 = count20 - 1;
      int count27 = 0;

      while (count24 >= 0) {
         long value6;
         long value7;
         if (count25 != 4) {
            value6 = (value4 ^ 0x27a7134467f8040L) - 0x18611143a3a7fc0L;
            value7 = (value5 ^ -0x45bd06b7a92fdda4L) + 0x69c138c7d14fde2cL;
            count27 >>>= 8;
            count25++;
         } else {
            count27 = this.countArray[count26--];
            count25 = 1;
            value6 = (value4 ^ -0x6b3f40adb3f764c1L) + 0x74bfa4cdbbf86b01L;
            value7 = (value5 ^ -0x5af404c80c1709faL) + 0x5af836f42e281206L;
         }

         byteValueArray[count24] = (byte)count27;
         count24--;
         value4 = (value6 ^ 0x766393eb24f57cb6L) - 0x2d9f6eeadcb53c76L;
         value5 = (value7 ^ -0xd7ad36f08e4711fL) - 0x4e851470ccea755fL;
      }

      return byteValueArray;
   }

   public SignumComparable resolveSignumComparable8(int count20) {
      long value2 = 0x376414bad496f559L;
      long value3 = 0x10f1d13eed944a65L;
      if (count20 < 0) {
         value2 = (value2 ^ -0x7dd0dca7dc6d1f1cL) - 0x4bb4ea151f4aceb6L;
         value3 = (value3 ^ -0x79368721e487e261L) + 0x25a9c712029bd311L;
         throw new ArithmeticException("Negative exponent");
      } else {
         value2 = (value2 ^ 0x6812487eef7eb7e0L) + 0xcb6f776e7bfc523L;
         value3 = (value3 ^ 0x3aacf58b73c12233L) - 0x40df95538de76d38L;
         if (this.count != 0) {
            long value4 = (value2 ^ 0x5ca71ad994336b4fL) + 0x2cf3c831cbe8bdf1L;
            long value5 = (value3 ^ 0x18696a4e9bd40bc2L) - 0x1ce88f34879c75dL;
            SignumComparable signumComparable7 = this.getSignumComparable5();
            int count21 = signumComparable7.getCount4();
            long value6 = (long)count21 * (long)count20;
            if (value6 <= 2147483647L) {
               value4 = (value4 ^ 0x34b780000cd410d2L) - 0xc4b80000bcc0fd2L;
               value5 = (value5 ^ 0x7ed382352b9f9003L) + 0x5db07e2cda607003L;
            } else {
               updateState();
            }

            int count22 = (int)value6;
            int count23;
            long value7;
            if (count21 <= 0) {
               long value8 = (value5 ^ -0x54b1a6a2094b8881L) - 0x54c1a92b72b43481L;
               count23 = signumComparable7.getCount3();
               if (count23 == 1) {
                  if (this.count < 0 && (count20 & 1) == 1) {
                     return signumComparable6;
                  }

                  return signumComparable;
               }

               value7 = (value8 ^ 0x1bbb43199d24fb7eL) - 0x5b6c2e79b24a882L;
            } else {
               signumComparable7 = signumComparable7.resolveSignumComparable6(count21);
               count23 = signumComparable7.getCount3();
               if (count23 == 1) {
                  if (this.count < 0 && (count20 & 1) == 1) {
                     return signumComparable6.resolveSignumComparable4(count22);
                  }

                  return signumComparable.resolveSignumComparable4(count22);
               }

               value7 = (value5 ^ -0x175b84f48d0add42L) - 0x189bcaf8ee74e242L;
            }

            long value9 = (long)count23 * (long)count20;
            if (signumComparable7.countArray.length == 1 && value9 <= 62L) {
               long value10;
               byte byteValue;
               searchBlock: {
                  if (this.count >= 0) {
                     value10 = (value7 ^ 0x35aca8bb4968db7fL) + 0x145b67b73917377fL;
                  } else {
                     if ((count20 & 1) == 1) {
                        byteValue = -1;
                        value10 = (value7 ^ 0x66a1952ce9086c9L) + 0x61a16cdb56f79c9L;
                        break searchBlock;
                     }

                     value10 = (value7 ^ 0x113a241ffe38b679L) - 0xf361bed7a387587L;
                  }

                  byteValue = 1;
               }

               byte byteValue2 = byteValue;
               long keySalt = value10 % 665086L ^ -332826L;
               long value11 = (long)signumComparable7.countArray[0] & 4294967295L;
               int count24 = count20;

               while (count24 != 0) {
                  if ((count24 & 1) != 1) {
                     value10 = (value10 ^ -0x320e0bc7098fe33dL) - 0x33714bc871f00c3dL;
                  } else {
                     keySalt *= value11;
                  }

                  if ((count24 >>>= 1) == 0) {
                     value10 = (value10 ^ 0x773a1aee6bdaab01L) + 0x56ca15621aa5a701L;
                  } else {
                     value11 *= value11;
                     value10 = (value10 ^ 0xae5ee5b4c8fd4c4L) + 0x9a2a9d733702bc4L;
                  }
               }

               if (count21 <= 0) {
                  return resolveSignumComparableByTime(keySalt * (long)byteValue2);
               } else {
                  return (long)count22 + value9 > 62L
                     ? resolveSignumComparableByTime(keySalt * (long)byteValue2).resolveSignumComparable4(count22)
                     : resolveSignumComparableByTime((keySalt << count22) * (long)byteValue2);
               }
            } else {
               if ((long)this.getCount3() * (long)count20 / 32L > 67108864L) {
                  updateState();
               }

               SignumComparable signumComparable8 = signumComparable;
               int count25 = count20;

               while (count25 != 0) {
                  if ((count25 & 1) == 1) {
                     signumComparable8 = signumComparable8.resolveSignumComparable4(signumComparable7);
                  }

                  if ((count25 >>>= 1) != 0) {
                     signumComparable7 = signumComparable7.getSignumComparable();
                  }
               }

               if (count21 > 0) {
                  signumComparable8 = signumComparable8.resolveSignumComparable4(count22);
               }

               return this.count < 0 && (count20 & 1) == 1 ? signumComparable8.getSignumComparable2() : signumComparable8;
            }
         } else {
            value2 = (value2 ^ -0x3ae35d9ba23e5f1eL) + 0x749771e21fdc431aL;
            value3 = (value3 ^ -0x215bb7e1afb1cd94L) - 0x1f404fe7b0be570dL;
            SignumComparable signumComparable9;
            if (count20 != 0) {
               signumComparable9 = this;
            } else {
               value2 = (value2 ^ 0x23a32c1e218fce9aL) + 0x1f13d2b4c3ba05c2L;
               value3 = (value3 ^ 0x2110673f3c9db4feL) - 0x4a42b47270b89d1cL;
               signumComparable9 = signumComparable;
            }

            return signumComparable9;
         }
      }
   }

   public int getCount8() {
      return this.count;
   }

   private static SignumComparable resolveSignumComparable(int count20, SignumComparable signumComparable7, SignumComparable signumComparable8) {
      Object entry = null;
      SignumComparable signumComparable9 = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      entry = resolveSignumComparableByTime((long)count20);
      signumComparable9 = signumComparable;
      entry3 = signumComparable;

      for (int index = signumComparable7.getCount3() - 2; index >= 0; index--) {
         entry2 = signumComparable9.resolveSignumComparable4((SignumComparable)entry3).resolveSignumComparable2(signumComparable8);
         SignumComparable signumComparable10 = ((SignumComparable)entry3).getSignumComparable();
         SignumComparable signumComparable11 = signumComparable9.getSignumComparable();
         SignumComparable signumComparable12 = ((SignumComparable)entry).resolveSignumComparable4(signumComparable11);
         entry4 = signumComparable10.resolveSignumComparable5(signumComparable12).resolveSignumComparable2(signumComparable8);
         int byteValue = 0;
         if (((SignumComparable)entry4).checkIndexByIndex(byteValue)) {
            entry4 = ((SignumComparable)entry4).resolveSignumComparable11(signumComparable8);
         }

         byte byteValue2 = 1;
         entry4 = ((SignumComparable)entry4).resolveSignumComparable6(byteValue2);
         signumComparable9 = (SignumComparable)entry2;
         entry3 = entry4;
         if (signumComparable7.checkIndexByIndex(index)) {
            entry2 = ((SignumComparable)entry2).resolveSignumComparable5((SignumComparable)entry4).resolveSignumComparable2(signumComparable8);
            int byteValue3 = 0;
            if (((SignumComparable)entry2).checkIndexByIndex(byteValue3)) {
               entry2 = ((SignumComparable)entry2).resolveSignumComparable11(signumComparable8);
            }

            byte byteValue4 = 1;
            SignumComparable signumComparable13 = ((SignumComparable)entry2).resolveSignumComparable6(byteValue4);
            SignumComparable signumComparable14 = ((SignumComparable)entry).resolveSignumComparable4((SignumComparable)entry2);
            entry4 = ((SignumComparable)entry4).resolveSignumComparable5(signumComparable14).resolveSignumComparable2(signumComparable8);
            byte byteValue5 = 0;
            if (((SignumComparable)entry4).checkIndexByIndex(byteValue5)) {
               entry4 = ((SignumComparable)entry4).resolveSignumComparable11(signumComparable8);
            }

            byte byteValue6 = 1;
            entry4 = ((SignumComparable)entry4).resolveSignumComparable6(byteValue6);
            signumComparable9 = signumComparable13;
            entry3 = entry4;
         }
      }

      return signumComparable9;
   }

   private static void applyIndexArray(int[] countArray6, int count20, int[] countArray7, int count21) throws RuntimeException {
      if (count20 >= 1) {
         if (count20 <= countArray6.length) {
            if (count20 * 2 <= countArray7.length) {
               if (count21 >= 1) {
                  if (count21 > countArray7.length) {
                     throw new IllegalArgumentException("input length out of bound: " + count20 + " > " + countArray7.length);
                  }
               } else {
                  throw new IllegalArgumentException("invalid input length: " + count21);
               }
            } else {
               throw new IllegalArgumentException("input length out of bound: " + count20 * 2 + " > " + countArray7.length);
            }
         } else {
            throw new IllegalArgumentException("input length out of bound: " + count20 + " > " + countArray6.length);
         }
      } else {
         throw new IllegalArgumentException("invalid input length: " + count20);
      }
   }

   public static SignumComparable resolveSignumComparable(int count20, Random random) {
      if (count20 >= 2) {
         return count20 >= 95 ? resolveSignumComparable(count20, 100, random) : resolveSignumComparableByRandom(count20, 100, random);
      } else {
         throw new ArithmeticException("bitLength < 2");
      }
   }

   private SignumComparable resolveSignumComparable16(SignumComparable signumComparable7) {
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      entry = new CurveAccumulator();
      entry2 = new CurveAccumulator(this.countArray);
      entry3 = new CurveAccumulator(signumComparable7.countArray);
      boolean flag = false;
      ((CurveAccumulator)entry2).resolveCurveAccumulator((CurveAccumulator)entry3, (CurveAccumulator)entry, flag);
      int count20 = this.count * signumComparable7.count;
      return ((CurveAccumulator)entry).resolveSignumComparableByIndex(count20);
   }

   private int computeCountByIndex(int count20) {
      int count21 = 0;
      if (count20 >= 0) {
         if (count20 < this.countArray.length) {
            count21 = this.countArray[this.countArray.length - count20 - 1];
            return this.count < 0 ? (count20 > this.getCount9() ? ~count21 : -count21) : count21;
         } else {
            return this.getCount7();
         }
      } else {
         return 0;
      }
   }

   private SignumComparable resolveSignumComparable(SignumComparable signumComparable7, boolean flag) {
      short shortValue = 10275;
      short shortValue2 = 15675;
      short shortValue3 = -26219;
      int count20 = 0;
      int count21 = 0;
      long value2 = -0x2baf3636d8cb286cL;
      if (signumComparable7.count != 0) {
         value2 = (value2 ^ 0x9ce669fff50e9ecL) - 0x234ae190fa0576a6L;
         if (this.count != 0) {
            value2 = (value2 ^ 0x746e79ca37cfffd6L) - 0x72bb946ac72f5dd7L;
            count20 = this.countArray.length;
            value2 = (value2 ^ -0x40576ea8e98fb97dL) - 0x27ca9ab326f1f00eL;
            if (signumComparable7 != this) {
               value2 = (value2 ^ 0x59acb7501c71d543L) - 0x17aa66ab361a09eaL;
            } else {
               value2 = (value2 ^ 0x6b9c43db44598336L) + 0x73976dec3f39e548L;
               if (count20 > 20) {
                  value2 = (value2 ^ 0x5d134127ef8fb674L) - 0x42f1b256b0bfe3d1L;
                  return this.getSignumComparable();
               }

               value2 = (value2 ^ -0x696cd47e12d8fde2L) - 0xf7f318d8a52a6c7L;
            }

            count21 = signumComparable7.countArray.length;
            value2 = (value2 ^ 0x3ed2b8bb7dd9571cL) + 0x537f15b9b2d68094L;
            if (count20 < 80) {
               value2 = (value2 ^ 0x73f94725c85fec54L) + 0x2776304b87b8f762L;
            } else {
               value2 = (value2 ^ 0x76d67c686b5f7dafL) + 0x7c0d56f46980acaaL;
               if (count21 >= 80) {
                  if (count20 < 240 && count21 < 240) {
                     return resolveSignumComparable3(this, signumComparable7);
                  }

                  if (!flag) {
                     int length = this.countArray.length;
                     int count22 = computeCount(this.countArray, length);
                     int length2 = signumComparable7.countArray.length;
                     if ((long)(count22 + computeCount(signumComparable7.countArray, length2)) > 2147483648L) {
                        updateState();
                     }
                  }

                  return resolveSignumComparable(this, signumComparable7);
               }

               value2 = (value2 ^ -0x278718466865259bL) - 0x174e4d93f3325f56L;
            }

            byte byteValue;
            if (this.count != signumComparable7.count) {
               byteValue = -1;
            } else {
               value2 = (value2 ^ -0x4e0bfc08f8cd10cbL) + 0x2baec534218e90fbL;
               byteValue = 1;
            }

            byte byteValue2 = byteValue;
            if (signumComparable7.countArray.length != 1) {
               if (this.countArray.length != 1) {
                  Object entry = null;
                  int[] countArray6 = signumComparable7.countArray;
                  int[] countArray7 = resolveIndexArray(this.countArray, count20, countArray6, count21, (int[])entry);
                  int[] countArray8 = resolveIndexArray3(countArray7);
                  return new SignumComparable(countArray8, byteValue2);
               }

               int count23 = this.countArray[0];
               return resolveSignumComparable(signumComparable7.countArray, count23, byteValue2);
            }

            int count24 = signumComparable7.countArray[0];
            return resolveSignumComparable(this.countArray, count24, byteValue2);
         }
      }

      return signumComparable5;
   }

   public static int computeCountByIndexArray(int[] countArray6, int[] countArray7, int count20, int count21, int count22) {
      applyIndexArray(countArray6, countArray7, count20, count21, count22);
      return computeCount(countArray6, countArray7, count20, count21, count22);
   }

   public SignumComparable getSignumComparable6() {
      short shortValue = 4581;
      int byteValue = -38;
      int count20 = 4184799;
      SignumComparable signumComparable7 = null;
      long value2 = 0L;
      SignumComparable signumComparable8 = null;
      int count21 = 0;
      CurvePicker curvePicker = null;
      long value3 = 0x339d29df87bbe2dcL;
      long value4 = -0x53862997113bd162L;

      while (true) {
         int count22 = (int)(value3 % 38L);
         if (count22 != 28) {
            if (count22 != 3) {
               guardBlock: {
                  long value5;
                  long value6;
                  searchBlock: {
                     if (count22 != 9) {
                        if (count22 == 8) {
                           throw new ArithmeticException("start < 0: " + this);
                        }

                        if (count22 == 16) {
                           if (signumComparable7.getCount3() >= 95) {
                              long value7 = (value3 ^ -0x29499c8fa3dd6ff3L) - 0x2158ef1f7cebb182L;
                              long value8 = (value4 ^ 0x424e80b8572a4e8fL) - 0xfcb0d167eece91dL;
                              byte byteValue2 = 0;
                              if (!signumComparable7.checkIndexByIndex(byteValue2)) {
                                 value3 = (value7 ^ -0x7924c1b8e2f58771L) + 0x20a0dede9bdd11e8L;
                                 value4 = (value8 ^ 0x13f31dd0ae33e09aL) - 0x271c670803bbc34aL;
                              } else {
                                 value3 = (value7 ^ 0x74e619d416035d4bL) + 0x587fe19d3fa1624cL;
                                 value4 = (value8 ^ -0x6efc9bec6b39d368L) + 0x670f7a690ea16ecdL;
                              }
                           } else {
                              value3 = (value3 ^ -0x402d094ce216734aL) + 0x5748795aa1b6aa73L;
                              value4 = (value4 ^ -0x3c433b42e63ba2fL) - 0x404b960846c4dcaeL;
                           }
                           continue;
                        }

                        if (count22 == 15) {
                           SignumComparable signumComparable9 = signumComparable4;
                           signumComparable7 = signumComparable7.resolveSignumComparable5(signumComparable9);
                           value3 = (value3 ^ 0x27e95ec07288896dL) + 0xedf375169a03d01L;
                           value4 = (value4 ^ 0x6b3664e7725b9144L) + 0x26a614b746bd25e6L;
                           continue;
                        }

                        if (count22 == 23) {
                           if (value2 % 41L == 0L) {
                              value3 = (value3 ^ -0x26234f35d0458ca3L) + 0x3c3d7e36b8468623L;
                              value4 = (value4 ^ -0x31c5870b1806e910L) - 0x5d4782a33bdff350L;
                              continue;
                           }

                           value5 = (value3 ^ 0x3f2097d77fdbe858L) + 0x1af305a8d1cc6d4cL;
                           value6 = (value4 ^ 0x71906180fd0c338dL) + 0x221f3614c77da582L;
                           break searchBlock;
                        }

                        if (count22 == 20) {
                           SignumComparable signumComparable10 = signumComparable;
                           signumComparable7 = signumComparable7.resolveSignumComparable5(signumComparable10);
                           value3 = (value3 ^ -0x6cd1b698a555d0edL) + 0x78da7f198bf9184fL;
                           value4 = (value4 ^ -0x275b8b37933f74bbL) - 0x6a6695c6d18dffd3L;
                           continue;
                        }

                        if (count22 == 35) {
                           if (value2 % 31L == 0L) {
                              value3 = (value3 ^ -0x459bfc1d29a5e565L) - 0x7e51a5a72350abf7L;
                              value4 = (value4 ^ 0x272a9ba3088f99e2L) - 0x4fc997652dfaa9a2L;
                           } else {
                              value3 = (value3 ^ 0x1d818e372c0ac3a0L) - 0x23f669f9a30973deL;
                              value4 = (value4 ^ 0x33226b6ec151f988L) + 0x10555742cf2b7ae9L;
                           }
                           continue;
                        }

                        if (count22 == 24) {
                           SignumComparable signumComparable11 = signumComparable;
                           signumComparable7 = signumComparable7.resolveSignumComparable11(signumComparable11);
                           value3 = (value3 ^ -0xa804bc1d88c64d3L) + 0x66df43e64eb3692bL;
                           value4 = (value4 ^ 0x78bfa2ac8e2b23c8L) + 0x7c2d382640bca91fL;
                           continue;
                        }

                        if (count22 == 14) {
                           return signumComparable7;
                        }

                        if (count22 == 7) {
                           int byteValue3 = 0;
                           if (signumComparable7.checkIndexByIndex(byteValue3)) {
                              value3 = (value3 ^ -0x74e26f4523269f35L) + 0x43856bc4fdcda2b2L;
                              value4 = (value4 ^ -0x16c7bae5fb4685aeL) - 0x17f4a7f185dd2a3eL;
                           } else {
                              value3 = (value3 ^ -0xa4b0e0700329572L) + 0x58f8064ff5367117L;
                              value4 = (value4 ^ -0x7011f678ae78c168L) + 0x19c03df2bd569610L;
                           }
                           continue;
                        }

                        if (count22 == 22) {
                           if (this.count >= 0) {
                              value3 = (value3 ^ -0x748c59f5406314eeL) - 0x432484734cd7716eL;
                              value4 = (value4 ^ -0x5190c1ed567ac5fL) + 0x2b3c444f2f7041a4L;
                           } else {
                              value3 = (value3 ^ 0x1c82b38e1a347cdL) - 0x1d1baf1e0aee51b7L;
                              value4 = (value4 ^ 0xd549ac5b98bd0f7L) + 0x50c2a79896ae363bL;
                           }
                           continue;
                        }

                        if (count22 != 19) {
                           if (count22 == 26) {
                              Object entry = null;
                              byte byteValue4 = 100;
                              if (!signumComparable7.matchesRandomByRandom(byteValue4, (Random)entry)) {
                                 value3 = (value3 ^ -0x68f4d5030338db83L) - 0x7d860da6ba213b6eL;
                                 value4 = (value4 ^ 0x7cbb9358fe592dd0L) - 0x73733b66151f5ce7L;
                                 continue;
                              }

                              return signumComparable7;
                           }

                           if (count22 == 1) {
                              return signumComparable8;
                           }

                           if (count22 == 32) {
                              if (value2 % 19L == 0L) {
                                 value3 = (value3 ^ 0x3a0dd6779a279a4dL) + 0x4a13b32f493d0d4L;
                                 value4 = (value4 ^ 0x461c069ccc9b952aL) + 0x1fd4c76efdcc3f5dL;
                              } else {
                                 value3 = (value3 ^ 0x3818219320c47265L) + 0x3e81a60fec1c4b23L;
                                 value4 = (value4 ^ -0x7798b2f0bab8b08L) - 0x4b8dbd11c1d5e0beL;
                              }
                              continue;
                           }

                           if (count22 == 33) {
                              Object entry2 = null;
                              byte byteValue5 = 100;
                              signumComparable8 = curvePicker.resolveSignumComparable(signumComparable7, byteValue5, (Random)entry2);
                              value3 = (value3 ^ 0x10248ca67c48f3b8L) + 0x1f59ea94cebe7e0eL;
                              value4 = (value4 ^ 0xa8be0e4c596e8fbL) - 0x51e6fdc78d1629b2L;
                              continue;
                           }

                           if (count22 == 37) {
                              if (value2 % 5L == 0L) {
                                 value3 = (value3 ^ 0x5d17d0ecaa15554cL) - 0x264b74d6073d9566L;
                                 value4 = (value4 ^ -0x4a0f3a32fcff9826L) - 0x2717dd5eb3e5881dL;
                              } else {
                                 value3 = (value3 ^ 0x25fc42e2d4fa8a87L) - 0x27d04571970c9e17L;
                                 value4 = (value4 ^ 0x381b382428f18157L) + 0x3636ec1cf3f68ee5L;
                              }
                              continue;
                           }

                           if (count22 == 10) {
                              SignumComparable signumComparable12 = signumComparable;
                              if (!this.equals(signumComparable12)) {
                                 value3 = (value3 ^ -0xb6afb4cd0a4f31eL) + 0x19e0c9f2395cf6fcL;
                                 value4 = (value4 ^ -0x214737d4fbf02ba3L) + 0x55457de84c4463adL;
                                 continue;
                              }

                              return signumComparable4;
                           }

                           if (count22 == 34) {
                              if (value2 % 3L == 0L) {
                                 value3 = (value3 ^ 0x502501208a7d08e7L) - 0x3c2aec51dcc2f75eL;
                                 value4 = (value4 ^ -0x27fe8339f026b858L) + 0x1070b5ce1dc69cd8L;
                              } else {
                                 value3 = (value3 ^ -0x5ba0220aaa8e3edL) + 0x67384d6941975f90L;
                                 value4 = (value4 ^ -0x217abd294fe8e45aL) - 0x5aa2d99ebd1e20cbL;
                              }
                              continue;
                           }

                           if (count22 == 36) {
                              if (this.count != 0) {
                                 value3 = (value3 ^ 0x323de8cbe2fab89eL) - 0x41324a905d91b5faL;
                                 value4 = (value4 ^ 0x23cd5a118a6305ceL) - 0x2ae42b707d62cdddL;
                                 continue;
                              }

                              return signumComparable4;
                           }

                           if (count22 == 2) {
                              count21 = computeCount(signumComparable7.getCount3());
                              value3 = (value3 ^ -0x51c7ca7512461d07L) - 0x38206a9b882c16c2L;
                              value4 = (value4 ^ -0x5f86a2f49c5ee1d0L) - 0xfff3d8cd11d670cL;
                              continue;
                           }

                           if (count22 == 0) {
                              if (value2 % 11L == 0L) {
                                 value3 = (value3 ^ -0x404db18655674426L) + 0x5a88001a1c672fd9L;
                                 value4 = (value4 ^ 0x201ef8f98a52af6dL) + 0x5f3537ef00962ab2L;
                              } else {
                                 value3 = (value3 ^ 0x32c6f030856d4605L) + 0x4a40d5b085581cfcL;
                                 value4 = (value4 ^ -0x45f11072746fc527L) + 0x11a20fe9e0d9e6a8L;
                              }
                              continue;
                           }

                           if (count22 != 18) {
                              if (count22 == 12) {
                                 SignumComparable signumComparable13 = signumComparable;
                                 signumComparable7 = this.resolveSignumComparable5(signumComparable13);
                                 value3 = (value3 ^ 0x2ca8dab43274ecefL) + 0x328407e120beb64fL;
                                 value4 = (value4 ^ -0x7ccfb61148924bfL) + 0x583cb3e74541e919L;
                                 continue;
                              }

                              if (count22 == 27) {
                                 if (value2 % 37L == 0L) {
                                    value3 = (value3 ^ -0x75e69b16d8696555L) - 0x6d5fa27c8a8814edL;
                                    value4 = (value4 ^ -0xec593f8f0c92896L) + 0x5ce02d17bf8a6671L;
                                 } else {
                                    value3 = (value3 ^ 0x24ff894490e62064L) - 0x27273c8ca4e5247eL;
                                    value4 = (value4 ^ 0x4c6e04b1b0153ec3L) + 0x77b54004ccb3e708L;
                                 }
                                 continue;
                              }

                              if (count22 == 5) {
                                 SignumComparable signumComparable14 = signumComparable4;
                                 signumComparable7 = signumComparable7.resolveSignumComparable5(signumComparable14);
                                 value3 = (value3 ^ -0x7e4e50dd73fe63e7L) - 0x3f86e4b6325d0be1L;
                                 value4 = (value4 ^ -0x61c42945c711a6bcL) - 0x5cb686c9da4baab7L;
                                 continue;
                              }

                              if (count22 == 21) {
                                 if (value2 % 13L == 0L) {
                                    value3 = (value3 ^ -0x533270172e3235acL) + 0x464004882f87624aL;
                                    value4 = (value4 ^ 0x5261e2e6da37ef96L) - 0x469c8bc5997544d8L;
                                 } else {
                                    value3 = (value3 ^ -0x202ed4c15c9ce9eaL) - 0x301f780ca71d556aL;
                                    value4 = (value4 ^ 0x612c13b087817247L) + 0x60799afd3869813cL;
                                 }
                                 continue;
                              }

                              if (count22 == 25) {
                                 break guardBlock;
                              }

                              if (count22 == 29) {
                                 SignumComparable signumComparable15 = signumComparable2;
                                 value2 = signumComparable7.resolveSignumComparable7(signumComparable15).getTimestamp();
                                 value3 = (value3 ^ -0x78e4f898fe83dce2L) + 0x15fe19981c91cae9L;
                                 value4 = (value4 ^ 0x3cf809b443c9284L) - 0x573a26212c1d9472L;
                                 continue;
                              }

                              if (count22 == 6) {
                                 if (value2 % 29L == 0L) {
                                    value3 = (value3 ^ 0x1d656dc30919c342L) - 0x2860748ecdcc21e7L;
                                    value4 = (value4 ^ 0x4484c0b311daa65cL) + 0x1dfacc774ef05bd7L;
                                 } else {
                                    value3 = (value3 ^ 0x734b4ae7b40191feL) - 0x2298f5ec2fb912b1L;
                                    value4 = (value4 ^ 0x5f1ee248f5c57964L) - 0x51ac128c977ac02dL;
                                 }
                                 continue;
                              }

                              if (count22 == 4) {
                                 return signumComparable4;
                              }

                              if (count22 == 30) {
                                 value3 = (value3 ^ -0x5805d8b421adf1a0L) - 0x7eb81b9ddd195f15L;
                                 value4 = (value4 ^ 0x78ba8233f32c552dL) + 0x74fe5d09b7e08a61L;
                                 break guardBlock;
                              }

                              if (count22 == 17) {
                                 return signumComparable7;
                              }

                              if (count22 != 13) {
                                 if (count22 != 31) {
                                    if (value2 % 7L == 0L) {
                                       value3 = (value3 ^ -0x3c51f246dbaafa8L) + 0x3532e2e384701a34L;
                                       value4 = (value4 ^ -0xf4d80ba34be2642L) - 0x1a086fe9478f2c09L;
                                    } else {
                                       value3 = (value3 ^ -0x131f6660e9cad186L) + 0x1202ee25861150f3L;
                                       value4 = (value4 ^ -0x6ecec8bd65d686e3L) + 0x4851f8e692e0f4cdL;
                                    }
                                 } else if (value2 % 17L == 0L) {
                                    value3 = (value3 ^ -0x59d90717bbd1a31fL) + 0x4126406a89694e97L;
                                    value4 = (value4 ^ -0x4a477c4a24c58ae2L) - 0x6c6a1a28da7fabd5L;
                                 } else {
                                    value3 = (value3 ^ 0x3604b1709e446055L) - 0x1d4d7f184ba8df4eL;
                                    value4 = (value4 ^ 0x213d5e10929070ceL) - 0x52b8075e653a851cL;
                                 }
                                 continue;
                              }

                              if (signumComparable8 == null) {
                                 value3 = (value3 ^ 0x3e97b5963aa4a113L) + 0x72a92775dbd65297L;
                                 value4 = (value4 ^ -0x2c9da17f94bb7e8eL) + 0x55d56555e53d1e20L;
                                 continue;
                              }

                              return signumComparable8;
                           }
                        } else {
                           value3 = (value3 ^ -0x1a5c58966a7a75abL) + 0x5fe20d98dfc2804eL;
                           value4 = (value4 ^ -0x5a19742c1b8c6a62L) - 0x3b3d795f8351f777L;
                        }
                     } else {
                        value3 = (value3 ^ -0x6e21eb1813ec92b3L) + 0x1d0305b6ec2366c6L;
                        value4 = (value4 ^ -0x342562f64c4a53a7L) - 0x5270dc203d228220L;
                     }

                     if (signumComparable7.getCount3() > 6) {
                        value3 = (value3 ^ 0x34a6f549450bc13dL) + 0x444e09056b5ff894L;
                        value4 = (value4 ^ 0x3769dc64fef2f7e2L) + 0x46c29f7d52a02e5cL;
                        continue;
                     }

                     value5 = (value3 ^ 0x6b92aef0e699bd9aL) - 0x220ecad22ddd9423L;
                     value6 = (value4 ^ 0x17b8166b8fb4669aL) - 0x598526d7aa07a3bdL;
                  }

                  if (signumComparable7.getCount3() >= 4) {
                     value3 = (value5 ^ -0x58ba7c472c64e913L) + 0x76e00781e3ec0b2aL;
                     value4 = (value6 ^ -0x25770babb6277787L) - 0x5254ed643e569f86L;
                     continue;
                  }

                  return signumComparable7;
               }

               curvePicker = new CurvePicker(signumComparable7, count21);
               value3 = (value3 ^ -0x4287fde10b87d062L) + 0x445038ed43c30064L;
               value4 = (value4 ^ 0x7a851153b33c9a83L) - 0x19b2561ca0c563c7L;
            } else {
               SignumComparable signumComparable16 = resolveSignumComparableByTime((long)(2 * count21));
               signumComparable7 = signumComparable7.resolveSignumComparable5(signumComparable16);
               value3 = (value3 ^ 0x4605595e5c359a73L) + 0x23d27a476a9b0998L;
               value4 = (value4 ^ -0x4bdc155bc607d7aL) + 0x9134cd518af61e1L;
            }
         } else if (value2 % 23L == 0L) {
            value3 = (value3 ^ -0x679689a5da6cde7aL) + 0x3fb350f1c9c74f43L;
            value4 = (value4 ^ -0xd78085b99bc2cecL) + 0x105b8798dd2a53e1L;
         } else {
            value3 = (value3 ^ -0x369a5a83a1723aa0L) - 0x639d7f64a6586a6eL;
            value4 = (value4 ^ 0x713d0aa4717a0451L) + 0x346f6fb0a79b06e9L;
         }
      }
   }

   private static int[] resolveIndexArray(int[] countArray6, int[] countArray7, int count20, long value2, int[] countArray8) {
      int[] countArray9 = resolveIndexArray(countArray6, count20, countArray8);
      int count21 = (int)value2;
      return resolveIndexArray(countArray9, countArray7, count20, count21);
   }

   private int computeCount(char[] characterArray, int count20, int count21) {
      int count22 = 0;
      int count23 = 0;
      int count24 = count20 + 1;
      count22 = Character.digit(characterArray[count20], 10);
      if (count22 == -1) {
         throw new NumberFormatException(new String(characterArray));
      } else {
         for (int index = count24; index < count21; index++) {
            count23 = Character.digit(characterArray[index], 10);
            if (count23 == -1) {
               throw new NumberFormatException(new String(characterArray));
            }

            count22 = 10 * count22 + count23;
         }

         return count22;
      }
   }

   public String resolveTextByIndex(int count20) {
      int count21 = count20;
      StringBuilder builder = null;
      if (this.count == 0) {
         return "0";
      } else {
         if (count20 < 2 || count20 > 36) {
            count21 = 10;
         }

         if (this.countArray.length > 20) {
            builder = new StringBuilder();
            if (this.count >= 0) {
               int byteValue = 0;
               applySignumComparable(this, builder, count21, byteValue);
            } else {
               SignumComparable signumComparable7 = this.getSignumComparable2();
               byte byteValue2 = 0;
               applySignumComparable(signumComparable7, builder, count21, byteValue2);
               builder.insert(0, '-');
            }

            return builder.toString();
         } else {
            return this.resolveText(count21);
         }
      }
   }

   private static int computeCount(int[] countArray6, int count20) {
      for (long index = 0x1a602156a8eaa9c3L; (int)(index % 3L) != 0; index = (index ^ -0x407f201648f3dcb1L) + 0x6896fc6138da23cbL) {
         if (count20 == 0) {
            return 0;
         }
      }

      return (count20 - 1 << 5) + numberOfLeadingZeros(countArray6[0]);
   }

   private static void applyIndexArray2(int[] countArray6, int count20, int count21) {
      long value2 = 0L;
      long value3 = 0L;
      int count22 = 0;
      long value4 = 0L;
      long value5 = 0L;
      int count23 = 0;
      int count24 = 0;
      long value6 = 0L;
      long value7 = 0x15b229eefdd1702dL;
      long value8 = 0x34fc7f8225712899L;

      while (true) {
         int count25 = (int)(value7 % 19L);
         if (count25 == 15) {
            value2 = (long)count20 & 4294967295L;
            value7 = (value7 ^ 0x51d191b46092d89eL) + 0xfddf77a0747f9c6L;
            value8 = (value8 ^ 0x51fc742cfa475228L) - 0x231dabbccae46655L;
         } else if (count25 != 12) {
            if (count25 != 0) {
               if (count25 != 8) {
                  if (count25 == 4) {
                     value5 = value6 >>> 32;
                     value7 = (value7 ^ -0x5e337aa0c04c9cd6L) - 0x5fa26885ad763d3dL;
                     value8 = (value8 ^ -0x6d6a704c9e9b5f70L) + 0x595af8cab718afe3L;
                     continue;
                  }

                  if (count25 == 17) {
                     value6 = ((long)countArray6[count24] & 4294967295L) + value5;
                     value7 = (value7 ^ -0x6834c8f065fdbe98L) - 0x36937bf84a1dc559L;
                     value8 = (value8 ^ 0x5f96d75f1fbd8f30L) + 0x70bddfa4c7a8a631L;
                     continue;
                  }

                  if (count25 == 2) {
                     countArray6[count23] = (int)value4;
                     value7 = (value7 ^ 0xb1ff5430b415520L) - 0x7570873bfd7c3d0L;
                     value8 = (value8 ^ -0x112d4dad7a274b6fL) - 0x1b8b8f9cdd7f05ebL;
                     continue;
                  }

                  if (count25 == 18) {
                     count22 = countArray6.length;
                     value7 = (value7 ^ -0x48bbdb585e9a9775L) - 0x56af34c7f8f3c897L;
                     value8 = (value8 ^ -0x6a4914eefdc8c261L) - 0x4896e35bf930b524L;
                     continue;
                  }

                  if (count25 != 5) {
                     if (count25 != 13) {
                        if (count25 != 9) {
                           if (count25 == 11) {
                              value5 = value6 >>> 32;
                              value7 = (value7 ^ 0x7ee0cfad7ae7c58fL) + 0x17e52ba35a198bc1L;
                              value8 = (value8 ^ 0x7fced3f9337be29aL) - 0x43f4275640154d9fL;
                              continue;
                           }

                           if (count25 == 1) {
                              countArray6[count24] = (int)value6;
                              value7 = (value7 ^ -0x23770a1ddc9a4141L) - 0x4f15a2fd16da4874L;
                              value8 = (value8 ^ -0x66adf64fdf53843fL) + 0x865529f9bb31f6cL;
                              continue;
                           }

                           if (count25 == 6) {
                              value5 = 0L;
                              value7 = (value7 ^ 0x4ad402f2d088415L) + 0x247edbf1c4d62aaL;
                              value8 = (value8 ^ 0x4e6e91228e807cc9L) + 0x7c8a2135de34bb48L;
                              continue;
                           }

                           if (count25 == 7) {
                              value4 = 0L;
                              value7 = (value7 ^ -0x2265408b521a4d3bL) - 0x387379076a58aab7L;
                              value8 = (value8 ^ 0x5fe54fb5a2bf43ebL) + 0x40de7a8781ec776fL;
                              continue;
                           }

                           if (count25 != 3) {
                              if (count25 != 10) {
                                 if (count25 != 16) {
                                    value3 = (long)count21 & 4294967295L;
                                    value7 = (value7 ^ -0x6c6e445c0a5192cdL) + 0x3e72aeecc90d01dbL;
                                    value8 = (value8 ^ -0x4bd011b43b454ac8L) - 0x5ac852ce94d7f027L;
                                 } else {
                                    value4 = value2 * ((long)countArray6[count23] & 4294967295L) + value5;
                                    value7 = (value7 ^ 0x70c8c3f2aabc6c3L) - 0x206655bfd6679350L;
                                    value8 = (value8 ^ 0xcdbb771df578991L) + 0x54896eb1ebba4a80L;
                                 }
                              } else {
                                 value5 = value4 >>> 32;
                                 value7 = (value7 ^ 0x1222480f7895730dL) - 0xf7465c95b45510fL;
                                 value8 = (value8 ^ -0x5d3e8e90e76b3541L) + 0x509c30f2feac97a4L;
                              }
                              continue;
                           }
                        } else {
                           count24--;
                           value7 = (value7 ^ -0x68a9615dae0125a1L) + 0x5f37dd6420ca90eaL;
                           value8 = (value8 ^ 0x9b0bc6951d7c2a0L) + 0x64c40daaafdaa7bcL;
                        }

                        if (count24 < 0) {
                           return;
                        }

                        value7 = (value7 ^ -0x4c0775b44f6b2c3L) + 0x58828f81d1355c38L;
                        value8 = (value8 ^ -0x21e9b0be5ee2e3b3L) - 0x19b40ece25a4685eL;
                     } else {
                        countArray6[count22 - 1] = (int)value6;
                        value7 = (value7 ^ 0x27496192efe5a14cL) - 0x2d6455ee7cd8e9dfL;
                        value8 = (value8 ^ 0x171ec788ccfa913fL) - 0x3ba3e2e14f573260L;
                     }
                     continue;
                  }
               } else {
                  count23--;
                  value7 = (value7 ^ -0x4ddb12460bf1ba54L) - 0x46856850b71c7560L;
                  value8 = (value8 ^ 0x5a90cc97c548b127L) - 0x29b6c7cc4e7d6e39L;
               }

               if (count23 < 0) {
                  long value9 = (value7 ^ 0x502ea38470f4037eL) - 0x1b7921e34c01430eL;
                  long value10 = (value8 ^ -0x27cbd300fee43909L) - 0x6784c834f7660399L;
                  value6 = ((long)countArray6[count22 - 1] & 4294967295L) + value3;
                  value7 = (value9 ^ -0xd53897ba1e00b59L) + 0x680909953c222360L;
                  value8 = (value10 ^ 0x7452b4b4efdffc8dL) + 0x78f8c34877ad5abbL;
               } else {
                  value7 = (value7 ^ -0x6c849705264a1c8cL) + 0x568baf71b0422fbdL;
                  value8 = (value8 ^ -0x25bcd4f1c91bcdc0L) - 0x694b29da000755e0L;
               }
            } else {
               count24 = count22 - 2;
               value7 = (value7 ^ 0xd05016367bbf6ebL) - 0x310825d80c82d74L;
               value8 = (value8 ^ -0x75da2d91c576fa6dL) + 0x2659bf84f51884d8L;
            }
         } else {
            count23 = count22 - 1;
            value7 = (value7 ^ 0x2da8b49dc19ae9efL) + 0x14059fb0b6fb84fbL;
            value8 = (value8 ^ 0x79f55cb8eef6ea51L) - 0x143e7d46715dbc18L;
         }
      }
   }

   private static int computeCount(int count20, SignumComparable signumComparable7) {
      int count21 = count20;
      int count22 = 0;
      int count23 = 0;
      int count24 = 0;
      long value2 = 0x2298ee5b396176e9L;
      long value3 = 0x552228c7892d4025L;

      while (true) {
         int count25 = (int)(value2 % 40L);
         if (count25 == 28) {
            count24 = count23;
            value2 = (value2 ^ 0x77c91bf97b11b7dfL) + 0x2b6909ac01e71d23L;
            value3 = (value3 ^ -0x1539c990882c9fd1L) - 0x1ca1dffb150e51d4L;
         } else if (count25 != 21) {
            if (count25 != 3) {
               if (count25 != 33) {
                  if (count25 != 8) {
                     if (count25 != 11) {
                        if (count25 != 36) {
                           if (count25 != 20) {
                              if (count25 != 37) {
                                 if (count25 != 2) {
                                    if (count25 != 31) {
                                       if (count25 != 0) {
                                          guardBlock: {
                                             searchBlock:
                                             if (count25 != 27) {
                                                if (count25 == 6) {
                                                   count21 >>= 2;
                                                   value2 = (value2 ^ -0x4fffa52b0d02cd6fL) - 0x58ec777760ccabb8L;
                                                   value3 = (value3 ^ 0x277522f7a6310722L) + 0x70406965a5e52f6L;
                                                   continue;
                                                }

                                                if (count25 == 9) {
                                                   if (count24 != 7) {
                                                      value2 = (value2 ^ 0x3285a5addb1f8c91L) - 0x3e44dfd34d3c551dL;
                                                      value3 = (value3 ^ 0x70765b36eb85bf58L) - 0x72a53be102a18ce7L;
                                                   } else {
                                                      value2 = (value2 ^ -0x20f02a0012f0fedL) - 0x3b14c2005452e036L;
                                                      value3 = (value3 ^ 0xffc649b301b452fL) + 0x114d32c09cb0eb9eL;
                                                   }
                                                   continue;
                                                }

                                                if (count25 == 29) {
                                                   count23 >>= 1;
                                                   value2 = (value2 ^ 0x69cc243fa9d517a3L) + 0xa82805a20b59855L;
                                                   value3 = (value3 ^ -0x6af911650ef69afaL) - 0x7f57244a65ac57bL;
                                                   continue;
                                                }

                                                if (count25 == 22) {
                                                   count23 = count21;
                                                   value2 = (value2 ^ -0x23d1d41fec87c24L) - 0x4752ff3432bf80e6L;
                                                   value3 = (value3 ^ 0x2f783be70ed9817fL) - 0x5fc9efd87353353aL;
                                                   continue;
                                                }

                                                if (count25 == 12) {
                                                   count22 = -count22;
                                                   value2 = (value2 ^ 0x5da34fc755d71061L) + 0x14dc695a4e889e76L;
                                                   value3 = (value3 ^ -0x3185953c0f63e785L) - 0xf86d40e83307b4dL;
                                                   continue;
                                                }

                                                if (count25 == 38) {
                                                   if (count23 >= count21) {
                                                      throw new AssertionError();
                                                   }

                                                   value2 = (value2 ^ 0xca2d5f0972d588fL) + 0x40cfed616272cac3L;
                                                   value3 = (value3 ^ -0x48ee1a307a47c8b4L) + 0x433ab593a5fe14b0L;
                                                   continue;
                                                }

                                                if (count25 != 10) {
                                                   if (count25 == 32) {
                                                      count22 = -count22;
                                                      value2 = (value2 ^ 0x5d2d9c2df4021d2bL) + 0x3c826e837ed48294L;
                                                      value3 = (value3 ^ -0x1b1c9a8fd06c9698L) - 0x3b94b716c0b0d0eeL;
                                                      continue;
                                                   }

                                                   if (count25 == 34) {
                                                      SignumComparable signumComparable8 = resolveSignumComparableByTime((long)count21);
                                                      count23 = signumComparable7.resolveSignumComparable2(signumComparable8).getCount5();
                                                      value2 = (value2 ^ -0x37fbaf07a52a27e3L) - 0x36591cb098ce8e55L;
                                                      value3 = (value3 ^ 0x42d846c60eaae8c5L) - 0x386bd8f0861f23e4L;
                                                      continue;
                                                   }

                                                   if (count25 != 26) {
                                                      if (count25 == 7) {
                                                         if (count23 == 1) {
                                                            return count22;
                                                         }

                                                         value2 = (value2 ^ 0x4e92133c309d0484L) + 0xff8c7e984d012aaL;
                                                         value3 = (value3 ^ -0x709fb4f55f997191L) - 0x4246a52baca6b6e1L;
                                                         continue;
                                                      }

                                                      if (count25 == 39) {
                                                         if (count21 >= 0) {
                                                            value2 = (value2 ^ -0x3391865be10ec834L) - 0x727eddbb95f41360L;
                                                            value3 = (value3 ^ 0x28458ca42ce39bb9L) + 0x50dc57c51346c2bcL;
                                                         } else {
                                                            value2 = (value2 ^ 0x6e3e96adf7a512fbL) + 0x72d82208ec6f499fL;
                                                            value3 = (value3 ^ 0x341f450682e5153L) - 0x27b3a34c03909fc5L;
                                                         }
                                                         continue;
                                                      }

                                                      if (count25 == 35) {
                                                         if (((count21 ^ count21 >> 1) & 2) == 0) {
                                                            value2 = (value2 ^ 0x2a46620ce6bcfb23L) + 0x44d0d4fb1620b24fL;
                                                            value3 = (value3 ^ 0x28f65ff9cbf54cL) + 0x669e705f5cd4ef5aL;
                                                         } else {
                                                            value2 = (value2 ^ 0x5fa119e71270bc63L) - 0x2ff4895187e62a20L;
                                                            value3 = (value3 ^ -0x79794fb42eb53cbcL) + 0x40020a725b77e718L;
                                                         }
                                                         continue;
                                                      }

                                                      if (count25 == 16) {
                                                         count23 = signumComparable7.countArray[signumComparable7.countArray.length - 1];
                                                         value2 = (value2 ^ 0x4544dbfb5b29f952L) + 0x23a932ac212de5adL;
                                                         value3 = (value3 ^ 0x6a45e15a956005c1L) + 0x572053ac42c12567L;
                                                         continue;
                                                      }

                                                      if (count25 == 17) {
                                                         count23 %= count21;
                                                         value2 = (value2 ^ -0xd1af3d5a5a1a59cL) + 0x57d0c9b68e6001a5L;
                                                         value3 = (value3 ^ -0x46e1157b8f6b222bL) - 0x30e505b3315801ecL;
                                                         continue;
                                                      }

                                                      if (count25 == 19) {
                                                         value2 = (value2 ^ 0x448e56f6e08e9f17L) + 0x44873ff3a955721bL;
                                                         value3 = (value3 ^ 0x4ba6dfd40bcbe2c2L) + 0x502d6e9df04b31dbL;
                                                         break guardBlock;
                                                      }

                                                      if (count25 == 4) {
                                                         count21 >>= 1;
                                                         value2 = (value2 ^ 0x2ef292d6ec4818c2L) + 0x209a68fe185c61f3L;
                                                         value3 = (value3 ^ 0x479bfca8094f6ea5L) - 0x41ef93eb29eb3accL;
                                                         continue;
                                                      }

                                                      if (count25 == 25) {
                                                         if (count21 == 0) {
                                                            return 0;
                                                         }

                                                         value2 = (value2 ^ 0x5a5c3e325edd9cfeL) - 0x2cad656fa0c651L;
                                                         value3 = (value3 ^ 0x58512ae62377414fL) - 0x7bebb8eeced0baddL;
                                                         continue;
                                                      }

                                                      if (count25 != 15) {
                                                         if (count25 == 13) {
                                                            if (enabled2) {
                                                               value2 = (value2 ^ 0x297e05a8f1c301afL) + 0x60af71d45aa3a96aL;
                                                               value3 = (value3 ^ 0x7a96e0f103b67383L) + 0x300f26f492575fcdL;
                                                            } else {
                                                               value2 = (value2 ^ -0x7214e698249cbce2L) - 0x77f3bb65544a168dL;
                                                               value3 = (value3 ^ 0x4c31352bb3a0ed43L) + 0x115d7ee9c1cadb19L;
                                                            }
                                                            continue;
                                                         }

                                                         if (count25 == 14) {
                                                            count22 = 1;
                                                            value2 = (value2 ^ 0x352629f09befe7a7L) + 0x2e1fccb743a8561fL;
                                                            value3 = (value3 ^ 0x43658f796c8d996eL) + 0x67e8c66f64e2b4cdL;
                                                            continue;
                                                         }

                                                         if (count25 == 30) {
                                                            count23 >>= 2;
                                                            value2 = (value2 ^ -0x28e27652e5ae70a6L) - 0x7fbbfdab8d8440c9L;
                                                            value3 = (value3 ^ 0x29f04beab783633aL) + 0x483936781128c58eL;
                                                            continue;
                                                         }
                                                         break guardBlock;
                                                      }

                                                      value2 = (value2 ^ 0x56dda3e8ea08c675L) + 0xd9b5532e97aa149L;
                                                      value3 = (value3 ^ 0x3f6aff340ba11578L) + 0x433e77ef8ba1e277L;
                                                      break searchBlock;
                                                   }

                                                   value2 = (value2 ^ 0x63738750cae3be65L) + 0x1b44c54c62cb6493L;
                                                   value3 = (value3 ^ 0x6f4c05d4a76b73c0L) - 0x48cba733fc77255bL;
                                                }

                                                if (count23 == 0) {
                                                   return 0;
                                                }

                                                value2 = (value2 ^ -0x7131974e6db9b60L) - 0x13ee2174ca385bbbL;
                                                value3 = (value3 ^ 0x66ef0f276fc37e64L) - 0x489aefbe7328e198L;
                                                continue;
                                             }

                                             if ((count21 & 3) != 0) {
                                                long value4 = (value2 ^ 0x4196518b39a77869L) - 0x16b8091eb0d06bbL;
                                                long value5 = (value3 ^ 0x226ada4f7ac2840fL) - 0x1fc9aad4db425b34L;
                                                if ((count21 & 1) != 0) {
                                                   value2 = (value4 ^ 0x2e3daac6929a0ab9L) - 0x1d1c55f38b6c7651L;
                                                   value3 = (value5 ^ 0x24cab1f56644ab30L) - 0x392afa16668878e6L;
                                                } else {
                                                   value2 = (value4 ^ -0x68ca12e570e9f8edL) + 0x7a1ebf13e18dde00L;
                                                   value3 = (value5 ^ -0x5bc094902cd397daL) + 0x4e40fd71387d40ddL;
                                                }
                                             } else {
                                                value2 = (value2 ^ -0x9ff7f0d8771277aL) - 0x6bf1753ddd0c292fL;
                                                value3 = (value3 ^ -0x5ee6a1b03be7d75cL) + 0x7acbf521bdb9ea11L;
                                             }
                                             continue;
                                          }

                                          if ((count23 & 3) != 0) {
                                             long value6 = (value2 ^ 0x3bad8f1cde458fa7L) + 0x25639947e4de627bL;
                                             long value7 = (value3 ^ 0x6d4c77e2da2ae6feL) + 0x52f3d690dfe20dc1L;
                                             if ((count23 & 1) != 0) {
                                                value2 = (value6 ^ -0x784c80b366efe85L) - 0x3c13ef340e2362b1L;
                                                value3 = (value7 ^ -0x20324b234b9ce35bL) - 0x3e35f42a0b4f7ac4L;
                                             } else {
                                                value2 = (value6 ^ 0x8b9dde5823084ecL) + 0x2509f66fdf863b6L;
                                                value3 = (value7 ^ 0x5ec39d56a92589b1L) + 0x50f029b303b4beb3L;
                                             }
                                          } else {
                                             value2 = (value2 ^ 0xa59d613eb313299L) - 0x5bf6ca5096405520L;
                                             value3 = (value3 ^ 0x3aa518540260fadaL) + 0x43543c3b9af4bb45L;
                                          }
                                       } else if ((count21 & count23 & 2) == 0) {
                                          value2 = (value2 ^ 0x4fcc8567b4d0f40aL) + 0x6188bb852de56e80L;
                                          value3 = (value3 ^ 0x22b3f9b4d269e923L) - 0x5b6bd53ca1301261L;
                                       } else {
                                          value2 = (value2 ^ 0x343544ed4872b0c7L) - 0xb875fabe21b84d3L;
                                          value3 = (value3 ^ -0x48469fb111d2eebaL) - 0x25b7c17878037c85L;
                                       }
                                    } else {
                                       count22 = -count22;
                                       value2 = (value2 ^ -0x1be2a24d799fec6cL) - 0x7e66c2820f5bb392L;
                                       value3 = (value3 ^ 0x253ebaf95070ac89L) - 0xca4eab1452049edL;
                                    }
                                 } else if (count24 == 3) {
                                    value2 = (value2 ^ -0x330d40ccd2514138L) - 0x429be59da1c77ceeL;
                                    value3 = (value3 ^ 0x678b1a8d42d201e0L) + 0x30aa1c27dbfd979eL;
                                 } else {
                                    value2 = (value2 ^ -0x7d5a3e7714444774L) + 0x5b1ea1413626ecf3L;
                                    value3 = (value3 ^ 0x13a4ba0b2dbcbbdfL) - 0x568b9aa9ef979ef0L;
                                 }
                              } else {
                                 if (count21 == 1) {
                                    return count22;
                                 }

                                 value2 = (value2 ^ 0x3ec022e083de9a83L) + 0x3aa4b42e0d995a92L;
                                 value3 = (value3 ^ 0x565ddc7837a33667L) + 0x3479dbf3fcc96898L;
                              }
                           } else {
                              count22 = -count22;
                              value2 = (value2 ^ -0x1f474a14bffa4312L) - 0x1ab1f07e1463ec90L;
                              value3 = (value3 ^ 0x7002cec6b0ee8fc5L) + 0x6f52b45c6a76274aL;
                           }
                        } else {
                           count21 = count24;
                           value2 = (value2 ^ 0x1eb35bc10a2cb455L) - 0x239278274bec5b19L;
                           value3 = (value3 ^ -0x51cd2a0e328b89b0L) + 0x31e49a2c46ca7dcdL;
                        }
                     } else {
                        count22 = -count22;
                        value2 = (value2 ^ 0x68904694f2897c29L) + 0x25966a16e160ebd3L;
                        value3 = (value3 ^ -0x6de35843d43fbe2L) + 0x50d437a4df025db3L;
                     }
                  } else if ((count23 & count21 & 2) == 0) {
                     value2 = (value2 ^ 0xa6a01744b37f675L) - 0x175749136d3be624L;
                     value3 = (value3 ^ 0x67008a63defa9a24L) + 0x5010c484e9962527L;
                  } else {
                     value2 = (value2 ^ 0x27151067a8a15727L) - 0x202e720dffeea3f0L;
                     value3 = (value3 ^ 0x4e62f120898c8531L) - 0x5d016fad29a71b60L;
                  }
               } else if (((count23 ^ count23 >> 1) & 2) == 0) {
                  value2 = (value2 ^ -0x6e2573d730f97df1L) + 0x45764f007c6fbec7L;
                  value3 = (value3 ^ 0x4cc125a04136458bL) - 0x4f592b49b1efa627L;
               } else {
                  value2 = (value2 ^ -0x7435ffb9c1ac20dcL) - 0x71a0d88f852eb3faL;
                  value3 = (value3 ^ -0x64dd036f2172976fL) - 0x46a84f4a4a10f2eaL;
               }
            } else {
               count21 = -count21;
               value2 = (value2 ^ 0x756532882e6d681fL) - 0xa1b025525f8a5b7L;
               value3 = (value3 ^ 0x7072a86c600d487dL) - 0xf91a45932dee41aL;
            }
         } else {
            count24 = count23 & 7;
            value2 = (value2 ^ 0x6880e33cd8a442abL) + 0xa6674d0b65f8234L;
            value3 = (value3 ^ -0x6f0276da7a4f84f7L) - 0x34732e55d397615L;
         }
      }
   }

   private static final int[] resolveIndexArray(int[] countArray6, int count20, int[] countArray7, int count21) {
      short shortValue = 4713;
      char character = '\u994b';
      char character2 = '\uefe1';
      int count22 = 0;
      int count23 = 0;
      int count24 = 0;
      long value2 = 0L;
      long value3 = 0L;
      int count25 = 0;
      long value4 = 0x13358bfc41872b75L;
      long value5 = 0x44a1b1a8a277808fL;

      while (true) {
         int count26 = (int)(value4 % 18L);
         if (count26 != 11) {
            if (count26 == 15) {
               countArray7[count21 - 1] = countArray7[count21 - 1] | countArray6[count20 - 1] & 1;
               break;
            }

            if (count26 != 14) {
               if (count26 != 9) {
                  if (count26 != 16) {
                     if (count26 == 1) {
                        countArray7[count24++] = (int)(value3 >>> 1);
                        value4 = (value4 ^ -0x39fcc4f665f8d6eeL) - 0x2edac33542199b31L;
                        value5 = (value5 ^ -0x662a1f67ff1d092L) + 0x7f7b5e03c007d6aeL;
                        continue;
                     }

                     if (count26 == 0) {
                        value3 = value2 * value2;
                        value4 = (value4 ^ -0x708c6858bf74529eL) + 0x4abfbe9aa45e6b9fL;
                        value5 = (value5 ^ -0x1e34ae397dcd981aL) - 0x3ce98890ac7c32c5L;
                        continue;
                     }

                     if (count26 == 5) {
                        int count27 = count24 - 1;
                        computeCount(countArray7, count27, count23, count25);
                        value4 = (value4 ^ -0x1988fee8b57a06cdL) - 0x588ed782a733f6acL;
                        value5 = (value5 ^ -0x2cf6e1f018311cffL) + 0x3f9a14f0ae1d5245L;
                        continue;
                     }

                     if (count26 != 2) {
                        if (count26 == 3) {
                           break;
                        }

                        if (count26 != 6) {
                           if (count26 != 10) {
                              if (count26 != 7) {
                                 if (count26 != 17) {
                                    if (count26 != 12) {
                                       if (count26 != 4) {
                                          if (count26 != 13) {
                                             count23--;
                                             count24 += 2;
                                             value4 = (value4 ^ 0x7c42e28814891217L) + 0xafdb5ba905283ecL;
                                             value5 = (value5 ^ -0x28a44649664d8ceL) + 0x617e3a35cbd745a4L;
                                          }

                                          if (count23 <= 0) {
                                             int byteValue = 1;
                                             applyIndexArrayByIndexArray(countArray7, count21, byteValue);
                                             countArray7[count21 - 1] = countArray7[count21 - 1] | countArray6[count20 - 1] & 1;
                                             break;
                                          }

                                          value4 = (value4 ^ 0x47eb5a14736b055cL) + 0x4599e961a6d85d15L;
                                          value5 = (value5 ^ 0x3db4378ef89ea427L) + 0x486b8575d5dc8b7L;
                                          continue;
                                       }

                                       count22 = (int)value3;
                                       value4 = (value4 ^ -0x7834655cb71dd76dL) - 0x794dfddd3edb9f19L;
                                       value5 = (value5 ^ 0x1adaf58b7f7de613L) - 0x6ac664b83b605aacL;
                                       continue;
                                    }

                                    count23 = 0;
                                    value4 = (value4 ^ 0x15b22d5bf757f683L) + 0x46966f808416e0b9L;
                                    value5 = (value5 ^ 0x71bc2504ae635826L) - 0x35e5503f6ab58193L;
                                    continue;
                                 }

                                 value2 = (long)countArray6[count23] & 4294967295L;
                                 value4 = (value4 ^ -0x59a7b8464e009f1bL) - 0x3be0d04d7807ea3eL;
                                 value5 = (value5 ^ 0x9cd3c7b21c4384bL) + 0x8caf94873800ddcL;
                                 continue;
                              }

                              count22 = 0;
                              value4 = (value4 ^ -0x145ecfc87f8b90afL) + 0x2f4b851164229078L;
                              value5 = (value5 ^ -0x6d5f6468747158e4L) + 0x773175ee4cac9da9L;
                              continue;
                           }

                           int count28 = count23 - 1;
                           count25 = computeCountByIndexArray(countArray7, countArray6, count24, count28, count25);
                           value4 = (value4 ^ 0x25e8a19b1c52f5c7L) + 0x322dbe9760fa95fcL;
                           value5 = (value5 ^ 0x2413b96c802c3029L) - 0x5e3825e5842cf7d6L;
                           continue;
                        }

                        count24 = 0;
                        value4 = (value4 ^ 0x79a513bf3a955f8cL) + 0x41c3fc75136bc332L;
                        value5 = (value5 ^ -0x1100fd7ea058850cL) + 0x6e472123ab0b3ec3L;
                        continue;
                     }

                     count23++;
                     value4 = (value4 ^ -0x7a41db87c694aa35L) + 0x69910b6964d82c79L;
                     value5 = (value5 ^ 0x69f2a1bb78e94067L) - 0x26de5407c5ab2f07L;
                  }

                  if (count23 >= count20) {
                     long value6 = (value4 ^ -0x5de30a8f75b1dca0L) + 0x7b315d5f1dbe9de0L;
                     long value7 = (value5 ^ 0x10e04c7f26b27990L) - 0x31f411c41fee4a4cL;
                     count23 = count20;
                     value4 = (value6 ^ -0x12e4fec2cdd531bcL) - 0x667bec9f11343605L;
                     value5 = (value7 ^ 0x352df7f1a202c90aL) - 0x6853e58abf524c3eL;
                  } else {
                     value4 = (value4 ^ 0x164bade298edf7c4L) - 0x4234d576ff19c6dfL;
                     value5 = (value5 ^ -0x2bf8188e72befe9fL) + 0x7a44ec5b6eb513e1L;
                  }
               } else {
                  countArray7[count24++] = count22 << 31 | (int)(value3 >>> 33);
                  value4 = (value4 ^ -0x6e259373e18feebdL) + 0x76aeda24e68ca2e5L;
                  value5 = (value5 ^ 0x49e9cd2a670d1f9bL) - 0x773d2ad6232cbc76L;
               }
            } else {
               count25 = countArray6[count23 - 1];
               value4 = (value4 ^ 0x216f1220aefcff24L) - 0x66873641a611aa50L;
               value5 = (value5 ^ 0x7e537adc9489770eL) - 0x33e0e4e4d64bb00bL;
            }
         } else {
            count24 = 1;
            value4 = (value4 ^ -0x728eb45ee7c4a3d5L) - 0x72d871120e366cb9L;
            value5 = (value5 ^ 0x7d641261dd841298L) - 0x417741f8fb6dd3b9L;
         }
      }

      return countArray7;
   }

   private static SignumComparable resolveSignumComparable(int count20, int count21) {
      SignumComparable[] signumComparableArray5 = null;
      int count22 = 0;
      int count23 = 0;
      SignumComparable[][] signumComparableMatrix = null;
      long value2 = 0x559fd6495f1b3a9fL;
      long value3 = -0x7e0d3bb8607d80dfL;

      while (true) {
         int count24 = (int)(value2 % 14L);
         if (count24 == 0) {
            signumComparableMatrix = (SignumComparable[][])signumComparableMatrix.clone();
            value2 = (value2 ^ -0x1684fcdc38487842L) + 0x651463cceee3ec2eL;
            value3 = (value3 ^ -0x797c599aedb152f2L) + 0x7d2e23992c3afad9L;
         } else if (count24 != 13) {
            if (count24 != 9) {
               if (count24 == 6) {
                  return signumComparableArray5[count21];
               }

               if (count24 != 11) {
                  if (count24 == 3) {
                     signumComparableArray = signumComparableMatrix;
                     return signumComparableArray5[count21];
                  }

                  if (count24 == 8) {
                     SignumComparable signumComparable7 = signumComparableArray5[count23 - 1];
                     int byteValue = 2;
                     signumComparableArray5[count23] = signumComparable7.resolveSignumComparable8(byteValue);
                     value2 = (value2 ^ 0x43d7b844067e4dc2L) - 0x414aee958b2899feL;
                     value3 = (value3 ^ 0x566777d5ee135844L) + 0x7cfdfbd97424e5ffL;
                     continue;
                  }

                  if (count24 == 7) {
                     if (count21 >= signumComparableArray5.length) {
                        value2 = (value2 ^ -0x653350056173220dL) - 0x4f3d6cd42db80b67L;
                        value3 = (value3 ^ 0x292d7ec540fae565L) + 0x67c3a238d9a05a2fL;
                        continue;
                     }

                     return signumComparableArray5[count21];
                  }

                  if (count24 == 4) {
                     return signumComparableArray5[count21];
                  }

                  if (count24 == 5) {
                     if (count21 >= signumComparableMatrix[count20].length) {
                        value2 = (value2 ^ 0x1b71b63f02906b4bL) - 0x363bec2db4728934L;
                        value3 = (value3 ^ 0x1f782c38d67edb75L) - 0x1e9dd0f01e446746L;
                        continue;
                     }

                     return signumComparableArray5[count21];
                  }

                  if (count24 == 1) {
                     signumComparableArray5 = Arrays.copyOf(signumComparableArray5, count21 + 1);
                     value2 = (value2 ^ -0x3c1462feec16898cL) + 0x6594d27139b9955dL;
                     value3 = (value3 ^ 0x1035034f1698f066L) + 0xe10b645e12c24c8L;
                     continue;
                  }

                  if (count24 != 12) {
                     if (count24 != 10) {
                        count23 = count22;
                        value2 = (value2 ^ -0x2877f5be5774af1aL) + 0x3974b2b1b75f4f33L;
                        value3 = (value3 ^ -0x2f0e2cd0adc0eb31L) + 0x1013d0206921d27L;
                     } else {
                        signumComparableMatrix[count20] = signumComparableArray5;
                        value2 = (value2 ^ 0x60a0219e3de82d1fL) + 0x3f6e97e52f09bf92L;
                        value3 = (value3 ^ -0x33c522821ec22b74L) - 0x7ad06ea1f112740aL;
                     }
                     continue;
                  }

                  count23++;
                  value2 = (value2 ^ 0x14956b3d2109de4cL) - 0x2690f3ae9b7c52e5L;
                  value3 = (value3 ^ 0x4774349f314ba004L) - 0x65ca4e7c36fdc980L;
               }

               if (count23 > count21) {
                  long value4 = (value2 ^ 0x76e65309ea9cae4aL) - 0x3a80145e5f0f0521L;
                  long value5 = (value3 ^ -0x174952a04a3ec39aL) - 0x349c483ca28baa9eL;
                  signumComparableMatrix = signumComparableArray;
                  value2 = (value4 ^ -0x32c88ba1349b7289L) + 0x5821155f63607304L;
                  value3 = (value5 ^ -0x5ccffd799cadfbfcL) + 0x1e5d3ccfc938bd35L;
               } else {
                  value2 = (value2 ^ 0x3bec18f215505bd1L) + 0x397d75aec77c71aL;
                  value3 = (value3 ^ 0x486998c0c8c83667L) - 0x43be0e70559cd2d8L;
               }
            } else {
               signumComparableArray5 = signumComparableArray[count20];
               value2 = (value2 ^ -0x45cb0983e6c01099L) + 0x25072449f270fb5dL;
               value3 = (value3 ^ 0x342f476daebf5680L) + 0x7f24dcfc5f272862L;
            }
         } else {
            count22 = signumComparableArray5.length;
            value2 = (value2 ^ 0x3f862f57133b6712L) + 0x790493f567850a72L;
            value3 = (value3 ^ -0x434f28dc465a271fL) + 0x708c2a36c9d5e601L;
         }
      }
   }

   public final int computeCount(long value2) {
      long value3 = value2;
      int[] entry = null;
      int count20 = 0;
      int count21 = 0;
      int count22 = 0;
      int count23 = 0;

      assert value2 != Long.MIN_VALUE;

      entry = this.countArray;
      count20 = entry.length;
      if (count20 <= 2) {
         if (value2 < 0L) {
            value3 = -value2;
         }

         count21 = (int)(value3 >>> 32);
         if (count21 != 0) {
            if (count20 >= 2) {
               int count24 = (int)entry[0];
               if (count24 == count21) {
                  int count25 = (int)entry[1];
                  int count26 = (int)value3;
                  if (count25 == count26) {
                     return 0;
                  } else {
                     return ((long)count25 & 4294967295L) >= ((long)count26 & 4294967295L) ? 1 : -1;
                  }
               } else {
                  return ((long)count24 & 4294967295L) >= ((long)count21 & 4294967295L) ? 1 : -1;
               }
            } else {
               return -1;
            }
         } else if (count20 >= 1) {
            if (count20 <= 1) {
               count22 = (int)entry[0];
               count23 = (int)value3;
               if (count22 == count23) {
                  return 0;
               } else {
                  return ((long)count22 & 4294967295L) >= ((long)count23 & 4294967295L) ? 1 : -1;
               }
            } else {
               return 1;
            }
         } else {
            return -1;
         }
      } else {
         return 1;
      }
   }

   private static SignumComparable resolveSignumComparable3(SignumComparable signumComparable7, SignumComparable signumComparable8) {
      int count20 = 0;
      int count21 = 0;
      int count22 = 0;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      Object entry5 = null;
      Object entry6 = null;
      Object entry7 = null;
      Object entry8 = null;
      count20 = signumComparable7.countArray.length;
      count21 = signumComparable8.countArray.length;
      count22 = (Math.max(count20, count21) + 1) / 2;
      entry = signumComparable7.resolveSignumComparable(count22);
      entry2 = signumComparable7.resolveSignumComparable9(count22);
      entry3 = signumComparable8.resolveSignumComparable(count22);
      entry4 = signumComparable8.resolveSignumComparable9(count22);
      entry5 = ((SignumComparable)entry2).resolveSignumComparable4((SignumComparable)entry4);
      entry6 = ((SignumComparable)entry).resolveSignumComparable4((SignumComparable)entry3);
      SignumComparable signumComparable9 = ((SignumComparable)entry2).resolveSignumComparable5((SignumComparable)entry);
      SignumComparable signumComparable10 = ((SignumComparable)entry4).resolveSignumComparable5((SignumComparable)entry3);
      entry7 = signumComparable9.resolveSignumComparable4(signumComparable10);
      int count23 = 32 * count22;
      signumComparable9 = ((SignumComparable)entry5).resolveSignumComparable4(count23);
      SignumComparable signumComparable11 = ((SignumComparable)entry7).resolveSignumComparable11((SignumComparable)entry5).resolveSignumComparable11((SignumComparable)entry6);
      signumComparable9 = signumComparable9.resolveSignumComparable5(signumComparable11);
      int count24 = 32 * count22;
      entry8 = signumComparable9.resolveSignumComparable4(count24).resolveSignumComparable5((SignumComparable)entry6);
      return (SignumComparable)(signumComparable7.count == signumComparable8.count ? entry8 : ((SignumComparable)entry8).getSignumComparable2());
   }

   private static void applySignumComparable(SignumComparable signumComparable7, StringBuilder builder, int count20, int count21) {
      int byteValue = 0;
      String text = null;
      int count22 = 0;
      SignumComparable signumComparable8 = null;
      SignumComparable[] signumComparableArray5 = null;
      int count23 = 0;
      int count24 = 0;
      long value3 = 0x5374481cf6a9ff58L;
      long value4 = -0x4cc5e53c108ba694L;

      while (true) {
         int count25 = (int)(value3 % 16L);
         if (count25 != 8) {
            if (count25 != 15) {
               if (count25 != 11) {
                  if (count25 == 13) {
                     return;
                  }

                  searchBlock: {
                     if (count25 != 3) {
                        if (count25 == 7) {
                           if (builder.length() > 0) {
                              value3 = (value3 ^ 0x3447ea491c791ec0L) - 0x28ad8931c3ea6f46L;
                              value4 = (value4 ^ -0x4c17cf50becc23d5L) + 0x4b0f1c1e94ed3cd6L;
                              continue;
                           }
                           break searchBlock;
                        }

                        if (count25 == 5) {
                           count22 = (int)Math.round(Math.log((double)count24 * value / valueArray[count20]) / value - 1.0);
                           value3 = (value3 ^ -0x48400cd9386825bcL) - 0x519059307009d786L;
                           value4 = (value4 ^ 0x5075b42411ed3aaeL) - 0x2eede2ff1b8893edL;
                           continue;
                        }

                        if (count25 == 10) {
                           SignumComparable signumComparable9 = signumComparableArray5[0];
                           int count26 = count21 - count23;
                           applySignumComparable(signumComparable9, builder, count20, count26);
                           value3 = (value3 ^ -0x3bf3fb7a7670fa0bL) + 0x54e99a00eb1f5b0dL;
                           value4 = (value4 ^ 0x56fb39db5e9b3a0cL) + 0x40730927be97bfeaL;
                           continue;
                        }

                        if (count25 == 12) {
                           applySignumComparable(signumComparableArray5[1], builder, count20, count23);
                           return;
                        }

                        if (count25 == 9) {
                           if (text.length() < count21) {
                              value3 = (value3 ^ 0x6c3df1e19feb064dL) + 0x6ce9df584c59be3L;
                              value4 = (value4 ^ 0x7950ea7ee14c2184L) + 0x2026f544bf4106c0L;
                              continue;
                           }
                           break searchBlock;
                        }

                        if (count25 != 2) {
                           if (count25 != 4) {
                              if (count25 != 6) {
                                 if (count25 != 1) {
                                    text = signumComparable7.resolveText(count20);
                                    value3 = (value3 ^ 0xcd74ac8b11df783L) + 0x21f78109193e9b56L;
                                    value4 = (value4 ^ 0x4ca48c541b174edL) + 0x56f597ba8db20854L;
                                 } else {
                                    count22 = text.length();
                                    value3 = (value3 ^ -0x105c709c0fffc70aL) - 0x59c02067c36d0e95L;
                                    value4 = (value4 ^ -0x6bd99b0e35ec6b4bL) - 0x309426cfd8091c1cL;
                                 }
                              } else {
                                 builder.append('0');
                                 value3 = (value3 ^ 0x1f3a195262518f27L) + 0xac3a99f9ce8f682L;
                                 value4 = (value4 ^ -0x24d456d8a316fc88L) + 0x2253cc2be829927bL;
                              }
                           } else {
                              count23 = 1 << count22;
                              value3 = (value3 ^ -0x9f5d7fe47aa7751L) + 0x32f8f474d76d3c5fL;
                              value4 = (value4 ^ -0x14c4b4a0456287c7L) + 0x77f67b31b24599edL;
                           }
                           continue;
                        }
                     } else {
                        count22++;
                        value3 = (value3 ^ -0x73cbefa725b9e061L) - 0x4a12ca539725e9baL;
                        value4 = (value4 ^ -0x5accef1be518f162L) - 0x106ad52c8e600caaL;
                     }

                     if (count22 < count21) {
                        value3 = (value3 ^ -0x691eeddc75a294c1L) + 0x52f4358b5bf958c9L;
                        value4 = (value4 ^ -0x2c4f827831b30124L) + 0x7cde967422e451b1L;
                        continue;
                     }
                  }

                  builder.append(text);
                  return;
               } else {
                  signumComparable8 = resolveSignumComparable(count20, count22);
                  value3 = (value3 ^ -0x76c9b2a4856fa010L) + 0x5677337d6cdc8904L;
                  value4 = (value4 ^ -0x567380fc9b6f327eL) - 0x72383e9ce4051575L;
               }
            } else {
               signumComparableArray5 = signumComparable7.resolveSignumComparableArray(signumComparable8);
               value3 = (value3 ^ 0x5a99b85f56e96f95L) - 0x1706f209010fe666L;
               value4 = (value4 ^ -0x3d6b8eb6700648aL) + 0x7714be1009abcc11L;
            }
         } else if (signumComparable7.countArray.length > 20) {
            long value5 = (value3 ^ -0x10e286076f64a190L) + 0x5203cde207f3e8f6L;
            long value6 = (value4 ^ 0x2c400bcdfc701c34L) - 0x3f6455859d11cc36L;
            count24 = signumComparable7.getCount3();
            value3 = (value5 ^ -0x68f70da7bcc8fa3eL) - 0x20efa0e2bde44dc7L;
            value4 = (value6 ^ -0x7536de318f30a7c5L) - 0x6bd09c7b35bc901eL;
         } else {
            value3 = (value3 ^ 0x7668899d15fed21eL) - 0x9f6ae0dd0525f66L;
            value4 = (value4 ^ 0x1fd5b42b19e2c0a4L) + 0x746ae284db9508cfL;
         }
      }
   }

   private static byte[] resolveByteArray(int count20, Random random) {
      int count21 = 0;
      byte[] entry = null;
      if (count20 >= 0) {
         count21 = (int)(((long)count20 + 7L) / 8L);
         entry = new byte[count21];
         if (count21 > 0) {
            random.nextBytes((byte[])entry);
         }

         return (byte[])entry;
      } else {
         throw new IllegalArgumentException("numBits must be non-negative");
      }
   }

   private static int[] resolveIndexArrayByIndexArray(int[] countArray6, int[] countArray7) {
      int count20 = 0;
      int[] countArray8 = null;
      int count21 = 0;
      long value2 = 0L;
      long value3 = 0x12f0d9dad2654fe8L;
      long value4 = -0x19af574d8b4820c8L;

      long value5;
      long value6;
      boolean flag;
      while (true) {
         int count22 = (int)(value3 % 9L);
         if (count22 != 7) {
            if (count22 != 2) {
               if (count22 == 0) {
                  value2 = 0L;
                  value3 = (value3 ^ -0x684657aa1288fa28L) + 0x5cc86aa9bb2d973bL;
                  value4 = (value4 ^ -0x24c4fc356e4fc25eL) + 0x23cdfcf0acbc8fceL;
                  continue;
               }

               if (count22 == 1) {
                  count20 = countArray6.length;
                  value3 = (value3 ^ 0x6e1087f79fd78fbeL) - 0x45a858f47a7fa5feL;
                  value4 = (value4 ^ -0x7fc3af837f080308L) - 0x590f42df6eada2daL;
                  continue;
               }

               if (count22 == 3) {
                  countArray8[count20] = (int)value2;
                  value3 = (value3 ^ 0x4ac9dbf2b398ad56L) + 0x554ee2d5e9ffb27L;
                  value4 = (value4 ^ -0x5657080cc2707a44L) - 0x219a4ea495e39854L;
                  continue;
               }

               if (count22 != 5) {
                  if (count22 != 8) {
                     if (count22 != 4) {
                        flag = true;
                        value5 = (value3 ^ -0x13765cd84e400d42L) - 0x248961254f74d256L;
                        value6 = (value4 ^ -0x16787bc59795a6a8L) + 0x27847bf9986a28b8L;
                        break;
                     }

                     count21 = countArray7.length;
                     value3 = (value3 ^ -0x7155fe40c33e8728L) - 0x5c14c190aa8e6ef4L;
                     value4 = (value4 ^ -0x232359632ad3899aL) + 0x936f07a9c7df47bL;
                     continue;
                  }

                  countArray8 = new int[count20];
                  value3 = (value3 ^ -0x387f15c222c4c2afL) + 0x24c16c23697577b7L;
                  value4 = (value4 ^ -0xc72b838eb4261fcL) + 0xdd2b7a3c9b9fbebL;
                  continue;
               }
            } else {
               value3 = (value3 ^ 0x33b99a32e61ec12cL) + 0x1a5a0a0f981a05bL;
               value4 = (value4 ^ 0x23faf19ddb51b787L) - 0x489880d62b27b376L;
            }

            if (count21 <= 0) {
               long value7 = (value3 ^ -0x598675c7b438258L) + 0x31f05e1064e03151L;
               long value8 = (value4 ^ -0x6c710a6fda20893eL) - 0x68c2850d00ea7241L;
               if (value2 >> 32 == 0L) {
                  value5 = (value7 ^ -0x71ba76207ecca377L) - 0x5a82bfa7e9c35f44L;
                  value6 = (value8 ^ -0x683604cf5786e6d2L) + 0x3217f6391fef31a7L;
                  flag = false;
                  break;
               }

               value3 = (value7 ^ 0x756e370f34ceb6d4L) + 0x1e62d2a7a2907711L;
               value4 = (value8 ^ 0x1d5ec757939f719eL) - 0x4372e563d62af6c9L;
            } else {
               value3 = (value3 ^ -0x3b939e83b3184b1cL) - 0x4742abc524b59f2bL;
               value4 = (value4 ^ -0x5e3a46facd03afd1L) + 0xabcd03ce17d4563L;
            }
         } else {
            long value9 = (long)countArray6[--count20] & 4294967295L;
            count21--;
            value2 = value9 - ((long)countArray7[count21] & 4294967295L) + (value2 >> 32);
            value3 = (value3 ^ -0x318686f4d2153cbcL) - 0x559b8f03d05edc4dL;
            value4 = (value4 ^ -0x42a3105e51f17199L) - 0x6809dc8abdf94a68L;
         }
      }

      boolean flag2 = flag;

      while (count20 > 0 && flag2) {
         count20--;
         long value10;
         long value11;
         if ((countArray8[count20] = countArray6[count20] - 1) != -1) {
            value10 = (value5 ^ -0x50c06e0dad428df9L) - 0x67138ff1ae7351fdL;
            value11 = (value6 ^ -0x4d93c8032c5ca78L) + 0x14e5bebe3b1a0c88L;
            flag = false;
         } else {
            flag = true;
            value10 = (value5 ^ -0x660e818ad527b67eL) + 0x66aed18d29a7b87eL;
            value11 = (value6 ^ -0x20ba3c62b8fe2b19L) + 0x3942bf9cc4feab19L;
         }

         flag2 = flag;
         value5 = (value10 ^ 0x51f4e3a0cf1111deL) + 0x2eb35e5fcef1104aL;
         value6 = (value11 ^ 0xa9048420ee887b9L) + 0x69047c1fee77e57L;
      }

      while (count20 > 0) {
         count20--;
         countArray8[count20] = countArray6[count20];
      }

      return countArray8;
   }

   public SignumComparable resolveSignumComparable17(SignumComparable signumComparable7) {
      return signumComparable7.countArray.length >= 80 && this.countArray.length - signumComparable7.countArray.length >= 40
         ? this.resolveSignumComparable13(signumComparable7)
         : this.resolveSignumComparable16(signumComparable7);
   }

   private static int[] resolveIndexArrayByIndexArray(int[] countArray6, int[] countArray7, int count20, long value2, int[] countArray8) {
      short shortValue = 32355;
      long value3 = 169236313L;
      applyIndexArray(countArray6, countArray6, countArray7, count20, countArray8);
      if (count20 <= 512) {
         int[] countArray9 = resolveIndexArray(countArray8, count20);
         return resolveIndexArray(countArray6, countArray7, count20, value2, countArray9);
      } else {
         int[] countArray10 = resolveIndexArray(countArray6, count20, countArray8);
         int count21 = (int)value2;
         return resolveIndexArray(countArray10, countArray7, count20, count21);
      }
   }

   private int getCount9() {
      int count20 = 0;
      int count21 = 0;
      int count22 = 0;
      long value2 = 0xcf390b9c44a844aL;
      long value3 = -0x55e720acf1ca250fL;

      while (true) {
         int count23 = (int)(value2 % 10L);
         if (count23 == 1) {
            count22 = this.countArray.length;
            value2 = (value2 ^ -0x3db5426044ac244aL) - 0x62bf78cf4dc925e7L;
            value3 = (value3 ^ 0x28fc1578ad4c53f1L) - 0x6399d756e18f5501L;
         } else if (count23 == 4) {
            count21 = count22 - 1;
            value2 = (value2 ^ -0x3147a3d290c776a2L) - 0xef26d482fd23563L;
            value3 = (value3 ^ 0x295c634acee4fb16L) - 0x7b227b85fd838972L;
         } else if (count23 != 7) {
            long value4;
            long value5;
            searchBlock: {
               if (count23 != 2) {
                  if (count23 != 9) {
                     if (count23 == 6) {
                        break;
                     }

                     if (count23 != 8) {
                        if (count23 != 0) {
                           if (count23 != 3) {
                              this.count5 = count20 + 2;
                              break;
                           }

                           if (count20 != -2) {
                              break;
                           }

                           value2 = (value2 ^ -0x2f97c05d217b398cL) + 0x425c0efece082afeL;
                           value3 = (value3 ^ 0x30284becdb39cb10L) + 0x4290c2c941b70d25L;
                           continue;
                        }

                        count20 = this.count5 - 2;
                        value2 = (value2 ^ 0x75c3cb0c382d6a76L) - 0x57056d5c241d8181L;
                        value3 = (value3 ^ -0x7bbd69abe0a03d1bL) - 0x363d78eec945de0L;
                        continue;
                     }

                     if (this.countArray[count21] == 0) {
                        value2 = (value2 ^ -0x46ce9836b7def87cL) - 0x25063de5f2fe7c9cL;
                        value3 = (value3 ^ 0x22fd9e89f8f0617bL) + 0x79fcdc5155b26467L;
                        continue;
                     }

                     value4 = (value2 ^ 0x1e7a9bd2a17d52cL) + 0x634374662ed3b4ccL;
                     value5 = (value3 ^ 0x2e86549a1e4606c5L) + 0x6e3196647800c4b1L;
                     break searchBlock;
                  }
               } else {
                  count21--;
                  value2 = (value2 ^ 0x134b4d7c91cc0f3L) - 0x92540aa582c8a8cL;
                  value3 = (value3 ^ -0x1e0118ec3082b923L) + 0x2a14991e2365f244L;
               }

               if (count21 >= 0) {
                  value2 = (value2 ^ -0x27d7607d005aab08L) + 0x703f3d7571ea9ba1L;
                  value3 = (value3 ^ 0x8cb1d5812d70e4cL) - 0x2d656ab2cdf289bbL;
                  continue;
               }

               value4 = (value2 ^ 0xdd2be1c73169194L) + 0x6fe3ace421d58e5L;
               value5 = (value3 ^ 0x753927cbdce013baL) - 0x35a45669aa1aca63L;
            }

            count20 = count22 - count21 - 1;
            value2 = (value4 ^ -0x4bdd8bf92c1745d3L) + 0x522ba69611adfb46L;
            value3 = (value5 ^ 0x40c2b7b847839066L) - 0x2a8ee8fa982cb802L;
         } else {
            count20 = 0;
            value2 = (value2 ^ -0x32e5e59c13bd1308L) + 0x765cda96c7c6ac74L;
            value3 = (value3 ^ -0x1e4da2100d6d723fL) - 0x19b81ab8e41e5dc5L;
         }
      }

      return count20;
   }

   private SignumComparable getSignumComparable7() {
      int count20 = 0;
      int count21 = 0;
      int count22 = 0;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      Object entry5 = null;
      Object entry6 = null;
      Object entry7 = null;
      Object entry8 = null;
      Object entry9 = null;
      Object entry10 = null;
      Object entry11 = null;
      Object entry12 = null;
      int count23 = 0;
      count20 = this.countArray.length;
      count21 = (count20 + 2) / 3;
      count22 = count20 - 2 * count21;
      int byteValue = 0;
      entry3 = this.resolveSignumComparable(count21, count22, byteValue, count20);
      byte byteValue2 = 1;
      entry2 = this.resolveSignumComparable(count21, count22, byteValue2, count20);
      int byteValue3 = 2;
      entry = this.resolveSignumComparable(count21, count22, byteValue3, count20);
      boolean flag = true;
      entry4 = ((SignumComparable)entry).resolveSignumComparable(flag);
      entry12 = ((SignumComparable)entry3).resolveSignumComparable5((SignumComparable)entry);
      SignumComparable signumComparable7 = ((SignumComparable)entry12).resolveSignumComparable11((SignumComparable)entry2);
      boolean flag2 = true;
      entry7 = signumComparable7.resolveSignumComparable(flag2);
      entry12 = ((SignumComparable)entry12).resolveSignumComparable5((SignumComparable)entry2);
      boolean flag3 = true;
      entry5 = ((SignumComparable)entry12).resolveSignumComparable(flag3);
      boolean flag4 = true;
      entry8 = ((SignumComparable)entry3).resolveSignumComparable(flag4);
      signumComparable7 = ((SignumComparable)entry12).resolveSignumComparable5((SignumComparable)entry3);
      byte byteValue4 = 1;
      signumComparable7 = signumComparable7.resolveSignumComparable4(byteValue4).resolveSignumComparable11((SignumComparable)entry);
      boolean flag5 = true;
      entry6 = signumComparable7.resolveSignumComparable(flag5);
      entry10 = ((SignumComparable)entry6).resolveSignumComparable11((SignumComparable)entry7).getSignumComparableAsSignumComparable();
      signumComparable7 = ((SignumComparable)entry5).resolveSignumComparable11((SignumComparable)entry7);
      byte byteValue5 = 1;
      entry11 = signumComparable7.resolveSignumComparable6(byteValue5);
      entry9 = ((SignumComparable)entry5).resolveSignumComparable11((SignumComparable)entry4);
      signumComparable7 = ((SignumComparable)entry10).resolveSignumComparable11((SignumComparable)entry9);
      byte byteValue6 = 1;
      entry10 = signumComparable7.resolveSignumComparable6(byteValue6);
      entry9 = ((SignumComparable)entry9).resolveSignumComparable11((SignumComparable)entry11).resolveSignumComparable11((SignumComparable)entry8);
      byte byteValue7 = 1;
      SignumComparable signumComparable8 = ((SignumComparable)entry8).resolveSignumComparable4(byteValue7);
      entry10 = ((SignumComparable)entry10).resolveSignumComparable11(signumComparable8);
      entry11 = ((SignumComparable)entry11).resolveSignumComparable11((SignumComparable)entry10);
      count23 = count21 * 32;
      return ((SignumComparable)entry8)
         .resolveSignumComparable4(count23)
         .resolveSignumComparable5((SignumComparable)entry10)
         .resolveSignumComparable4(count23)
         .resolveSignumComparable5((SignumComparable)entry9)
         .resolveSignumComparable4(count23)
         .resolveSignumComparable5((SignumComparable)entry11)
         .resolveSignumComparable4(count23)
         .resolveSignumComparable5((SignumComparable)entry4);
   }

   private SignumComparable resolveSignumComparable9(int count20) {
      int count21 = 0;
      int count22 = 0;
      Object entry = null;
      count21 = this.countArray.length;
      if (count21 > count20) {
         count22 = count21 - count20;
         entry = new int[count22];
         System.arraycopy(this.countArray, 0, entry, 0, count22);
         return new SignumComparable(resolveIndexArray3((int[])entry), 1);
      } else {
         return signumComparable5;
      }
   }

   public SignumComparable getSignumComparable5() {
      return this.count >= 0 ? this : this.getSignumComparable2();
   }

   private SignumComparable resolveSignumComparable5(int count20) {
      int count21 = 0;
      int[] entry = null;
      int count22 = 0;
      long value2 = -0x28586ba9cccb8a44L;
      if (this.getCount3() > count20) {
         value2 = (value2 ^ 0x2de0806c1b5db1a6L) - 0x5631d42388d275f2L;
         count21 = count20 + 31 >>> 5;
         value2 = (value2 ^ -0x421e2c554bb5bb7L) - 0x3501b864c8d904beL;
         entry = new int[count21];
         value2 = (value2 ^ 0x1838ace4bb06018cL) + 0x6355c21ef287a90fL;
         System.arraycopy(this.countArray, this.countArray.length - count21, entry, 0, count21);
         value2 = (value2 ^ -0x23199923d1e411baL) - 0x330edda17fdc6d56L;
         count22 = (count21 << 5) - count20;
         value2 = (value2 ^ 0xc3681b9ceb385fdL) - 0x6945facbb2943c5eL;
         entry[0] = (int)((long)entry[0] & ((value2 % 128929L ^ -21089L) << 32 - count22) - (value2 % 913343L ^ -516181L));
         value2 = (value2 ^ 0x27f4d1b0302b60a9L) + 0x7b2c8a6c51fe5eeL;
         SignumComparable signumComparable7;
         if (entry[0] != 0) {
            signumComparable7 = new SignumComparable((int[])entry, 1);
         } else {
            value2 = (value2 ^ 0x24fe4bbc025de0ccL) - 0x448f928a0f4223L;
            signumComparable7 = new SignumComparable(1, (int[])entry);
         }

         return signumComparable7;
      } else {
         return this;
      }
   }

   private static SignumComparable resolveSignumComparableByRandom(int count20, int count21, Random random) {
      int count22 = 0;
      int[] countArray6 = null;
      int count23 = 0;
      int count24 = 0;
      int count25 = 0;
      long value2 = 0L;
      SignumComparable signumComparable7 = null;
      long value3 = 0x13200bbe772a22cfL;
      long value4 = 0x5e44066983e96208L;

      while (true) {
         int count26 = (int)(value3 % 30L);
         if (count26 == 9) {
            count22 = count20 + 31 >>> 5;
            value3 = (value3 ^ 0x3d4fc67c98f628a4L) + 0x463ae36cb9d5a75fL;
            value4 = (value4 ^ 0x38ae46025b0e3f26L) - 0x4b427c885744df2eL;
         } else if (count26 == 5) {
            count24 = (count23 << 1) - 1;
            value3 = (value3 ^ -0x110c870bfc556d69L) + 0x5595b3dcb722a664L;
            value4 = (value4 ^ 0xb953ee8cc73ace3L) - 0x363dc998761798e4L;
         } else if (count26 == 17) {
            SignumComparable signumComparable8 = signumComparable2;
            value2 = signumComparable7.resolveSignumComparable7(signumComparable8).getTimestamp();
            value3 = (value3 ^ -0x330d6f59225474d5L) - 0x7d2305b80155a227L;
            value4 = (value4 ^ -0x3f085eb328b838fcL) + 0x7693d3c0e726864fL;
         } else if (count26 != 27) {
            if (count26 != 8) {
               if (count26 != 28) {
                  if (count26 != 18) {
                     if (count26 == 2) {
                        return signumComparable7;
                     }

                     if (count26 == 16) {
                        count25 = 0;
                        value3 = (value3 ^ -0x31b4adfa15b36304L) - 0x7eea7c532baf369dL;
                        value4 = (value4 ^ 0x53c9b80260c0790L) + 0x6964c33389576e30L;
                     } else {
                        guardBlock:
                        if (count26 == 26) {
                           if (value2 % 5L == 0L) {
                              value3 = (value3 ^ 0x420df9522ffe8eb6L) - 0x17ed7bacafac4360L;
                              value4 = (value4 ^ 0x34eae98589737ea4L) - 0x47753e6b7cffb9f3L;
                           } else {
                              value3 = (value3 ^ -0x16109313e58397f0L) - 0x2646251251b3264fL;
                              value4 = (value4 ^ -0x8177caa4fd07eb4L) + 0x6f8c6a545a6b8L;
                           }
                        } else {
                           searchBlock: {
                              long value5;
                              long value6;
                              if (count26 != 21) {
                                 if (count26 == 7) {
                                    break searchBlock;
                                 }

                                 if (count26 != 6) {
                                    if (count26 == 25) {
                                       return signumComparable7;
                                    }

                                    if (count26 == 10) {
                                       value3 = (value3 ^ -0x342ab21b85e75d91L) + 0x55bd3159557c9719L;
                                       value4 = (value4 ^ 0x395818264a0056b4L) - 0x12f6ad86cfaad494L;
                                       break guardBlock;
                                    }

                                    if (count26 == 13) {
                                       value3 = (value3 ^ 0x5516d834f4545e94L) - 0x33343f635e69083L;
                                       value4 = (value4 ^ 0x140497002cb74adaL) - 0x78d055310b5802a2L;
                                       break guardBlock;
                                    }

                                    if (count26 == 12) {
                                       countArray6[count22 - 1] = countArray6[count22 - 1] | 1;
                                       value3 = (value3 ^ -0x19171248df3e8e4dL) - 0x6417133fdd2c29e2L;
                                       value4 = (value4 ^ 0x73c14303209f42e1L) - 0x50d3a14e4b81c1afL;
                                       continue;
                                    }

                                    if (count26 == 3) {
                                       if (value2 % 19L == 0L) {
                                          value3 = (value3 ^ -0x2b76c18a64a5526eL) - 0x6f54891817877351L;
                                          value4 = (value4 ^ -0x696a70698936a42aL) + 0x53adda58dbe3dbcdL;
                                       } else {
                                          value3 = (value3 ^ 0x47ecaec89f849b35L) + 0x1e92c6a7cfec8e6dL;
                                          value4 = (value4 ^ 0x2fd3e1923df4d3ecL) + 0x3230e05a28071af0L;
                                       }
                                       continue;
                                    }

                                    if (count26 == 24) {
                                       if (value2 % 17L == 0L) {
                                          value3 = (value3 ^ 0x155b59a73c1978b2L) - 0x3aba9e7dd14d200eL;
                                          value4 = (value4 ^ 0x659daabd9910fe71L) - 0x3db56d35261ef306L;
                                       } else {
                                          value3 = (value3 ^ 0x29ad30c506afbe8eL) - 0x1a49a09524545c17L;
                                          value4 = (value4 ^ 0x3d9b112b30724ac2L) + 0x506db4e42d2b168L;
                                       }
                                       continue;
                                    }

                                    if (count26 == 20) {
                                       countArray6 = new int[count22];
                                       value3 = (value3 ^ 0x52e51353b5cf4676L) + 0x303ca46c782a5beL;
                                       value4 = (value4 ^ -0x51c3ce01ca3f922L) + 0x793371b67ddb5982L;
                                       continue;
                                    }

                                    if (count26 == 19) {
                                       signumComparable7 = new SignumComparable(countArray6, 1);
                                       value3 = (value3 ^ -0x6753ffa85f6f76e8L) - 0x46d9347cd088d017L;
                                       value4 = (value4 ^ 0x300247b0e32878c6L) - 0x3e0e04cbaeffe699L;
                                       continue;
                                    }

                                    if (count26 == 22) {
                                       if (!signumComparable7.matchesRandomByRandom(count21, random)) {
                                          value3 = (value3 ^ 0x61ca4693b9de466eL) + 0x164d6104d95deb39L;
                                          value4 = (value4 ^ 0x41243c23b8a6e987L) + 0x3ae9d83b2fa2fbbfL;
                                          continue;
                                       }

                                       return signumComparable7;
                                    }

                                    if (count26 == 29) {
                                       if (value2 % 13L == 0L) {
                                          value3 = (value3 ^ 0x6f4b6c7ac1942efeL) - 0x1d64055ec9426513L;
                                          value4 = (value4 ^ -0x4b1ee309fe680cdfL) - 0x91eab501d2cbcf6L;
                                       } else {
                                          value3 = (value3 ^ -0x7e8113df92625f57L) + 0x6c52627d67fa31a0L;
                                          value4 = (value4 ^ 0x7ba70c1c0d646164L) + 0x5f10c63ae125e1cL;
                                       }
                                       continue;
                                    }

                                    if (count26 == 11) {
                                       if (value2 % 37L == 0L) {
                                          value3 = (value3 ^ -0x2705e3ca66f7b94aL) + 0x66e1ba07aa91ec65L;
                                          value4 = (value4 ^ -0x2670990601eb875aL) + 0x39135c35f3891e2aL;
                                       } else {
                                          value3 = (value3 ^ -0x61f53892bbc04b78L) - 0x7be8c0640f58afc8L;
                                          value4 = (value4 ^ 0x30a9fa3f04ceb12cL) + 0x1a66deb9337371bbL;
                                       }
                                       continue;
                                    }

                                    if (count26 == 4) {
                                       count23 = 1 << (count20 + 31 & 31);
                                       value3 = (value3 ^ 0x6590d61f9ca34236L) - 0x2034f7c2a6b748adL;
                                       value4 = (value4 ^ 0x59a666e540b40df9L) + 0x510d2781599d153cL;
                                       continue;
                                    }

                                    if (count26 == 15) {
                                       if (value2 % 7L == 0L) {
                                          value3 = (value3 ^ -0x1068714298ddf941L) + 0x7e0798bcab67c6c8L;
                                          value4 = (value4 ^ 0x61a6e6d81f7517d6L) + 0x56eb4c56096b849fL;
                                       } else {
                                          value3 = (value3 ^ 0x47c49dfc59c3e7bfL) - 0xc79617c46cd9464L;
                                          value4 = (value4 ^ -0x3859820fb8544172L) + 0x3bb2df8fd55eb0a1L;
                                       }
                                       continue;
                                    }

                                    if (count26 != 14) {
                                       if (count26 != 23) {
                                          if (count26 != 0) {
                                             if (value2 % 23L == 0L) {
                                                value3 = (value3 ^ 0x49d7fe328ad4ddd1L) - 0x632f61697591b9eaL;
                                                value4 = (value4 ^ 0x3c4ec53e4f53efcdL) - 0x78f9ef12d7b2aa10L;
                                             } else {
                                                value3 = (value3 ^ 0x3cebafc1064f8a71L) + 0x5fafa4e6b6179516L;
                                                value4 = (value4 ^ 0x7403c9a1983e8960L) - 0x796bfde398f7d021L;
                                             }
                                          } else if (value2 % 11L == 0L) {
                                             value3 = (value3 ^ -0x45512f6508181d67L) + 0x78a1bd821db81d93L;
                                             value4 = (value4 ^ 0x2e9451d9fb537bc9L) - 0x5832c68878727ff3L;
                                          } else {
                                             value3 = (value3 ^ -0x3f7a3b0798de4948L) + 0x74e1732497c44889L;
                                             value4 = (value4 ^ -0x6647271c41f08ad1L) + 0x505e6ef75e915dc6L;
                                          }
                                       } else if (value2 % 3L == 0L) {
                                          value3 = (value3 ^ -0xe4b0b38821daed7L) + 0x79976b49e9fc9f1aL;
                                          value4 = (value4 ^ -0x22b78ff702d15619L) - 0x5cd8a1c87118f447L;
                                       } else {
                                          value3 = (value3 ^ -0x66ae7c09493d654fL) + 0x7b739e79c2a9a2b0L;
                                          value4 = (value4 ^ -0x2562f2fc7735159fL) - 0x3dc80ce4908ce83eL;
                                       }
                                       continue;
                                    }

                                    count25++;
                                    value3 = (value3 ^ 0x14ace428857d704dL) + 0x3f2ff2f9745aebd2L;
                                    value4 = (value4 ^ 0x39c280231c720e66L) + 0x8819de50121ef43L;
                                    break searchBlock;
                                 }

                                 if (count20 > 6) {
                                    value3 = (value3 ^ 0x19fa50562fd497e6L) - 0x45fbfac346288779L;
                                    value4 = (value4 ^ -0x1fa7d1ac5e015ae1L) - 0x79b877fda9c1d546L;
                                    continue;
                                 }

                                 value5 = (value3 ^ 0x2d158ce24f46fc25L) - 0x497749199a0be385L;
                                 value6 = (value4 ^ 0x4614064049ef9ef6L) - 0x358ee7c25a304e46L;
                              } else {
                                 if (value2 % 41L == 0L) {
                                    value3 = (value3 ^ 0x59c44944dc80b859L) - 0x189973dbde0d72ecL;
                                    value4 = (value4 ^ -0x8a9c8cf047289dfL) + 0x2f4bdbe8471418d6L;
                                    continue;
                                 }

                                 value5 = (value3 ^ 0x4e118c60fb9a8007L) - 0x2c45aebfc5176b3aL;
                                 value6 = (value4 ^ -0x275859e76b07e1c6L) + 0xb7a6cbfdc7f00ebL;
                              }

                              if (count20 >= 4) {
                                 value3 = (value5 ^ 0x565aa5ed00b18401L) - 0x6108a17812ff611L;
                                 value4 = (value6 ^ -0x66497740090c87b2L) - 0x612265d91d325634L;
                                 continue;
                              }

                              return signumComparable7;
                           }

                           if (count25 >= count22) {
                              long value7 = (value3 ^ -0x47f18da6a2a6cd9cL) + 0x1d2d7786dc3196d5L;
                              long value8 = (value4 ^ 0x6986ed4a13ac33e8L) - 0x473a48bc0997db51L;
                              countArray6[0] = countArray6[0] & count24 | count23;
                              value3 = (value7 ^ -0x7064bfcd2a84b08dL) - 0x4acc4920f982dbfaL;
                              value4 = (value8 ^ 0x61c995db2bd2e3c0L) - 0x13d0109cdef73256L;
                           } else {
                              value3 = (value3 ^ -0x69a0fef5c9689e92L) - 0x47c62cb301df8d01L;
                              value4 = (value4 ^ -0x5d5a5a15b5769511L) + 0x7534e6b01b20391dL;
                           }
                        }
                     }
                  } else if (value2 % 29L == 0L) {
                     value3 = (value3 ^ 0x18393ca67be16388L) - 0x5e03a93cca010018L;
                     value4 = (value4 ^ -0x6b7a007ceb883f06L) + 0x44b163c51d6abffeL;
                  } else {
                     value3 = (value3 ^ 0x33c14c409769390dL) - 0x2a50e2d82a9b775L;
                     value4 = (value4 ^ -0x2c39077b3c52c575L) + 0x5260eb22a2e97e05L;
                  }
               } else {
                  countArray6[count25] = random.nextInt();
                  value3 = (value3 ^ -0x5112308ee0583ca4L) + 0x350fcdda26a991acL;
                  value4 = (value4 ^ -0xa1f71e63b4de8f6L) + 0x5b4e21fcedf2a799L;
               }
            } else if (value2 % 31L == 0L) {
               value3 = (value3 ^ 0x7f7326ce1527e974L) - 0xc260d439f01278cL;
               value4 = (value4 ^ -0x3e9378e38e953aa7L) - 0xb08014ca91d8721L;
            } else {
               value3 = (value3 ^ -0x75541bcadc4ffb22L) - 0x681615fdd71943a1L;
               value4 = (value4 ^ -0x52abc79ea2d88ebbL) - 0x21b6ca01126618e1L;
            }
         } else if (count20 <= 2) {
            value3 = (value3 ^ 0x2eb2695ca6ed515L) + 0x238f5cae877817b3L;
            value4 = (value4 ^ -0x438c2903d4dd7e9aL) + 0xdd28d3a7ecbe5ecL;
         } else {
            value3 = (value3 ^ 0x114b3fd3bf8a66a7L) - 0x4e1843e8d767f0cL;
            value4 = (value4 ^ -0x5e7eff12bbd47e64L) + 0x47544598fdbd6662L;
         }
      }
   }

   private SignumComparable resolveSignumComparableBySignumComparable(SignumComparable signumComparable7, SignumComparable signumComparable8) {
      short shortValue = 2325;
      short shortValue2 = 5019;
      char character = '\u96ca';
      int[] countArray6 = null;
      int[] countArray7 = null;
      int[] countArray8 = null;
      int count20 = 0;
      int[] countArray9 = null;
      int count21 = 0;
      int count22 = 0;
      int[][] countMatrix = null;
      int count23 = 0;
      long value2 = 0L;
      int[] countArray10 = null;
      CurveAccumulator curveAccumulator = null;
      CurveAccumulator curveAccumulator2 = null;
      CurveAccumulator curveAccumulator3 = null;
      CurveAccumulator curveAccumulator4 = null;
      int count24 = 0;
      int[] countArray11 = null;
      int count25 = 0;
      int count26 = 0;
      int count27 = 0;
      int count28 = 0;
      int count29 = 0;
      int count30 = 0;
      long value3 = 0L;
      int[] countArray12 = null;
      long value4 = 0x3d3af5ed6ad919d4L;
      long value5 = -0x28ff6d2dae98e6d2L;

      while (true) {
         int count31 = (int)(value4 % 48L);
         if (count31 != 5) {
            scanBlock: {
               if (count31 != 7) {
                  if (count31 != 0) {
                     if (count31 == 12) {
                        if (count21 != 17) {
                           value4 = (value4 ^ 0x47650c940580e5e3L) - 0x212a4a94edea1788L;
                           value5 = (value5 ^ 0x2ef20466986935eeL) - 0x42f57f1b573e372dL;
                        } else {
                           value4 = (value4 ^ -0x16a3d478b66a5a27L) - 0x481ea26bd32a39d6L;
                           value5 = (value5 ^ -0x5acb3841ff2b7b1aL) + 0x3981c40f422023b8L;
                        }
                        continue;
                     }

                     long value6;
                     long value7;
                     evalBlock: {
                        checkBlock: {
                           guardBlock:
                           if (count31 != 17) {
                              if (count31 == 6) {
                                 int length = countArray7.length;
                                 count21 = computeCount(countArray7, length);
                                 value4 = (value4 ^ -0x61f507b407d3e3e7L) - 0x596b9d246eb4a2e3L;
                                 value5 = (value5 ^ -0x5b221601e36ea6cdL) - 0x18b4af3e556126e7L;
                                 continue;
                              }

                              if (count31 == 13) {
                                 countArray7 = signumComparable7.countArray;
                                 value4 = (value4 ^ -0x20807d81cea0fa56L) + 0x5c78c6dbbaf48168L;
                                 value5 = (value5 ^ 0x21b8ad6248d0b99fL) + 0x212b15b2aae2b84aL;
                                 continue;
                              }

                              if (count31 == 37) {
                                 curveAccumulator2 = new CurveAccumulator(countArray10);
                                 value4 = (value4 ^ 0x5849187b230b54cdL) + 0x13f35c81750ea14aL;
                                 value5 = (value5 ^ 0x2dc3062810d2170cL) - 0x52c22a8cea53ec00L;
                                 continue;
                              }

                              if (count31 == 20) {
                                 count20++;
                                 break scanBlock;
                              }

                              if (count31 == 22) {
                                 countMatrix[count23] = new int[count20];
                                 value4 = (value4 ^ -0x65ca394d4a992b6dL) - 0x2b0d5d2c39b5b964L;
                                 value5 = (value5 ^ -0x2bd81ad144abcec5L) - 0x49b38551844fb7a7L;
                                 continue;
                              }

                              if (count31 == 25) {
                                 count26 = 0;
                                 value4 = (value4 ^ -0x7938f05d6fa692cbL) - 0x6c28fce0281afd52L;
                                 value5 = (value5 ^ 0x5b3e4c0ee4aaf1e2L) + 0x7c3375659ad0d8f5L;
                                 continue;
                              }

                              searchBlock: {
                                 if (count31 != 39) {
                                    if (count31 == 18) {
                                       curveAccumulator3 = new CurveAccumulator(countArray8);
                                       value4 = (value4 ^ 0x58c26b7e75cc61b6L) - 0x2142ba79f2097741L;
                                       value5 = (value5 ^ 0x61d6d51deed8a296L) + 0x56aaa2538d6946dfL;
                                       continue;
                                    }

                                    if (count31 == 15) {
                                       countArray8 = signumComparable8.countArray;
                                       value4 = (value4 ^ -0x3981e2778a7aa559L) - 0x5069891ba74b9208L;
                                       value5 = (value5 ^ -0x1d42251650b19a9dL) - 0x5b2036dd398536e4L;
                                       continue;
                                    }

                                    if (count31 == 36) {
                                       SignumComparable signumComparable9 = signumComparable;
                                       if (!signumComparable7.equals(signumComparable9)) {
                                          value4 = (value4 ^ 0x7a1cc3516b5bd4d7L) - 0x4671bcf720a0e02cL;
                                          value5 = (value5 ^ -0x2245ed1d2fabee69L) - 0x1b177429d1e30a46L;
                                          continue;
                                       }

                                       return this;
                                    }

                                    if (count31 == 44) {
                                       count25++;
                                       value4 = (value4 ^ 0x583915a7284d0de9L) + 0x63c8acdd7f2dd13bL;
                                       value5 = (value5 ^ 0x773d9c54b20122c6L) - 0x327775df2b4d0f3bL;
                                       break checkBlock;
                                    }

                                    if (count31 == 40) {
                                       break searchBlock;
                                    }

                                    if (count31 == 46) {
                                       countArray6 = (int[])this.countArray.clone();
                                       value4 = (value4 ^ 0x7569310d3202f5d6L) - 0x4015f74b2a085fbL;
                                       value5 = (value5 ^ -0x477b204a51a4fa09L) - 0x3dc20f9e23eb7850L;
                                       continue;
                                    }

                                    if (count31 == 8) {
                                       count24 = count20 - countMatrix[0].length;
                                       value4 = (value4 ^ 0x7a1e836f73bce8a2L) + 0x116a273701497e73L;
                                       value5 = (value5 ^ -0x2ae28f5f5cff9828L) - 0x4f4730c7e7d69f0aL;
                                       continue;
                                    }

                                    if (count31 == 23) {
                                       if (this.count != 0) {
                                          value4 = (value4 ^ 0x58fad0ffdaec2c92L) + 0x79761f6877a1f79L;
                                          value5 = (value5 ^ -0x3ca34bb076f9b5acL) + 0x61288a278fb575ebL;
                                          continue;
                                       }

                                       return signumComparable5;
                                    }

                                    if (count31 == 29) {
                                       Object entry = countMatrix[0];
                                       Object entry2 = null;
                                       countArray12 = resolveIndexArrayByIndexArray((int[])entry, countArray8, count20, value2, (int[])entry2);
                                       value4 = (value4 ^ -0x6fd90bb7593cff75L) - 0x488c38f810282691L;
                                       value5 = (value5 ^ -0x55ec3c5edfc37881L) + 0x11dcbc6a5cf70b96L;
                                       continue;
                                    }

                                    if (count31 == 1) {
                                       break guardBlock;
                                    }

                                    if (count31 == 4) {
                                       value2 = -CurveAccumulator.computeTimestamp(value3);
                                       value4 = (value4 ^ -0x4d51edbde4b2ef48L) + 0x7fe114d1a27d364cL;
                                       value5 = (value5 ^ 0x2c6c2bce6d6c6f09L) - 0x5df9d0d686f9440eL;
                                       continue;
                                    }

                                    if (count31 == 32) {
                                       curveAccumulator4 = curveAccumulator2.resolveCurveAccumulator4(curveAccumulator3, curveAccumulator);
                                       value4 = (value4 ^ -0x7bcc42df9262fd58L) + 0x47625ffe09d3e3a3L;
                                       value5 = (value5 ^ 0x5d023ee517f21f68L) - 0x3f75a8ea729b638dL;
                                       continue;
                                    }

                                    if (count31 == 28) {
                                       countMatrix = new int[count22][];
                                       value4 = (value4 ^ 0x7577e0da240ea8a9L) - 0x1645301de1fa48caL;
                                       value5 = (value5 ^ -0x5d475dfad02caf96L) + 0x3ed6e1ff0c109604L;
                                       continue;
                                    }

                                    if (count31 == 35) {
                                       if (countMatrix[0].length >= count20) {
                                          value4 = (value4 ^ -0x5795e519c1ceec8dL) + 0x5aab7d33781967edL;
                                          value5 = (value5 ^ -0x23a2d3405d44dcafL) - 0x21ea9a3f4555a26bL;
                                       } else {
                                          value4 = (value4 ^ -0x1a3dbbdd0b40e9c5L) - 0x41d4334ff6c3bcc0L;
                                          value5 = (value5 ^ 0x22bee5a3fc1a4f5eL) - 0x685ea6c4afee58bcL;
                                       }
                                       continue;
                                    }

                                    if (count31 == 34) {
                                       int count32 = count26 << 1;
                                       long value8;
                                       long value9;
                                       byte byteValue;
                                       if ((countArray7[count28] & count25) == 0) {
                                          value8 = (value4 ^ 0x70c65e0e86be34a3L) - 0x4f3a5df2799dd35fL;
                                          value9 = (value5 ^ 0x705ab17bcbcd96c0L) + 0x70266eeb3a348e40L;
                                          byteValue = 0;
                                       } else {
                                          byteValue = 1;
                                          value8 = (value4 ^ 0x164f4a3c3142b98dL) + 0xa4cb5dc10bea773L;
                                          value9 = (value5 ^ -0xedbeea70d09b3a1L) - 0x111bf137aed5bc59L;
                                       }

                                       count26 = count32 | byteValue;
                                       count25 >>>= 1;
                                       if (count25 != 0) {
                                          value8 = (value8 ^ 0x7a85f095bf1668dbL) - 0x597cf08d70f62737L;
                                          value9 = (value9 ^ 0x79df4f8e4c8d9617L) + 0x67ab357dbb748defL;
                                       } else {
                                          count28++;
                                          count25 = Integer.MIN_VALUE;
                                          count27--;
                                       }

                                       count29++;
                                       value4 = (value8 ^ -0x79f42e97740f4a7cL) - 0x4de6fbfdf120af2eL;
                                       value5 = (value9 ^ 0x278b34c096785ac6L) + 0x46bb3f3e63db72b9L;
                                       break searchBlock;
                                    }

                                    if (count31 != 26) {
                                       if (count31 == 31) {
                                          return this;
                                       }

                                       if (count31 == 45) {
                                          countArray11 = new int[count20];
                                          value4 = (value4 ^ 0x634bb611ce070949L) + 0x98559ac39c755caL;
                                          value5 = (value5 ^ 0x1b873078013a4457L) - 0x49b0aa4e6b7eecc0L;
                                          continue;
                                       }

                                       if (count31 == 30) {
                                          countArray9 = new int[count20 + 1];
                                          value4 = (value4 ^ -0x5edd0d7161c118e2L) + 0x66bc22146218d292L;
                                          value5 = (value5 ^ -0x3ead5f6c839ac907L) + 0xf492550c349b6ddL;
                                          continue;
                                       }

                                       if (count31 == 43) {
                                          countMatrix[0] = curveAccumulator4.getIndexArrayAsIndexArray();
                                          value4 = (value4 ^ -0x452496640071d6f9L) - 0x19f084de048ce1e9L;
                                          value5 = (value5 ^ -0x1c1a510f57b38d35L) - 0x42d92c1135e6f6ecL;
                                          continue;
                                       }

                                       if (count31 == 3) {
                                          countMatrix[0] = countArray11;
                                          value4 = (value4 ^ -0x1a27f1cf7d85831eL) + 0x59db40c206c5f90cL;
                                          value5 = (value5 ^ 0x24dafe8391c868d0L) + 0x7a4b020ed9d1f417L;
                                          continue;
                                       }

                                       if (count31 == 42) {
                                          count27 = countArray7.length;
                                          value4 = (value4 ^ -0x6c3e097fb3de1732L) - 0x77feed2696342e8eL;
                                          value5 = (value5 ^ -0x17649fb5a5688504L) - 0x47a15ba69aba1658L;
                                          continue;
                                       }

                                       if (count31 == 33) {
                                          count25 = 1;
                                          value4 = (value4 ^ -0x42c002d8b1f92045L) - 0x69933be0952500baL;
                                          value5 = (value5 ^ 0x1eaa58c5f19787caL) + 0x55fb1bd9d20339e1L;
                                          continue;
                                       }

                                       if (count31 == 9) {
                                          count29 = 0;
                                          value4 = (value4 ^ -0x7d354f3a24e279d4L) + 0x5b7319d8ca2a39b3L;
                                          value5 = (value5 ^ -0x75ee9f65817a3047L) - 0x2b1f5184d4745da3L;
                                          continue;
                                       }

                                       if (count31 == 47) {
                                          if (countArray7[0] != 65537) {
                                             value4 = (value4 ^ 0x62eac466bd5710faL) - 0x1c1cceb64bd62c9eL;
                                             value5 = (value5 ^ 0x75b4db8e84282faeL) - 0x6367cadb8e138177L;
                                             continue;
                                          }

                                          value6 = (value4 ^ -0x55942598a3d6ee68L) + 0xd64166c03b3c1d3L;
                                          value7 = (value5 ^ 0x16fd36e4890e1f48L) - 0x6f94fc83e629e3b8L;
                                          break evalBlock;
                                       }

                                       if (count31 != 16) {
                                          if (count31 != 41) {
                                             if (count31 != 14) {
                                                if (count31 != 10) {
                                                   if (count31 != 2) {
                                                      if (count31 != 21) {
                                                         if (count31 != 27) {
                                                            if (count31 != 11) {
                                                               if (count31 != 24) {
                                                                  if (count31 == 19) {
                                                                     curveAccumulator3.updateState3();
                                                                     value4 = (value4 ^ 0x2f32d536f7178facL) - 0xa46f3db60167fffL;
                                                                     value5 = (value5 ^ -0x5ecef61cf45c9b6cL) + 0x2b94170dadeebb33L;
                                                                  } else {
                                                                     count28 = 0;
                                                                     value4 = (value4 ^ 0x59e4f37c3cd8c0acL) + 0x6ec1fc4b001f3bfL;
                                                                     value5 = (value5 ^ -0x7a3bb97e35b1f231L) + 0x2f55681c0c56ca8aL;
                                                                  }
                                                               } else {
                                                                  int length2 = countArray6.length;
                                                                  int count33 = count20 << 5;
                                                                  int count34 = length2;
                                                                  countArray10 = resolveIndexArray(countArray6, count34, count33);
                                                                  value4 = (value4 ^ 0x105296e66dcc09daL) - 0x235cb9c287a88457L;
                                                                  value5 = (value5 ^ -0x60b8740062204193L) - 0x472a250080bce07eL;
                                                               }
                                                            } else {
                                                               count23 = 0;
                                                               value4 = (value4 ^ 0x564fb785e04c7b6eL) - 0x15cf6055e80be354L;
                                                               value5 = (value5 ^ -0x777189a7f78199e3L) - 0x57e559a31de862adL;
                                                            }
                                                         } else {
                                                            curveAccumulator = new CurveAccumulator();
                                                            value4 = (value4 ^ 0x35855a403a4ec654L) + 0x5801861d979336e6L;
                                                            value5 = (value5 ^ 0x505ed91a3de8b1d0L) + 0x4bbdb497003165d1L;
                                                         }
                                                      } else {
                                                         countArray11 = Arrays.copyOf(countArray12, count20);
                                                         value4 = (value4 ^ -0x74c134333d304c72L) - 0x673d09540744ccaaL;
                                                         value5 = (value5 ^ -0xc5f117f24dc3b5cL) - 0x159512a17d17fbe9L;
                                                      }
                                                   } else {
                                                      System.arraycopy(countArray8, 0, countArray9, 1, count20);
                                                      value4 = (value4 ^ -0x2aae09065d8e9de1L) + 0x5a509d96207358cdL;
                                                      value5 = (value5 ^ -0x1bce46ff1f81b9fL) + 0x3152e9d03bfe7e84L;
                                                   }
                                                } else {
                                                   countArray8 = countArray9;
                                                   value4 = (value4 ^ -0x2eeec51fc88a25f5L) - 0x67a40bf047ddd37dL;
                                                   value5 = (value5 ^ 0x4de59a0bdc6787a3L) - 0x79b87cbfbbcb28feL;
                                                }
                                             } else {
                                                System.arraycopy(countMatrix[0], 0, countArray11, count24, countMatrix[0].length);
                                                value4 = (value4 ^ 0x25530b705ae9ddfaL) - 0x480340f9ca3e1261L;
                                                value5 = (value5 ^ -0x5d032e4ca7c43c7fL) + 0x3ffea34055b05b87L;
                                             }
                                             continue;
                                          }

                                          if ((count20 & 1) != 0) {
                                             value4 = (value4 ^ 0x9213fd84bb49639L) - 0x1d4f0a797f9f74f2L;
                                             value5 = (value5 ^ -0x58f17c5f9497fc0cL) - 0x56f35b8ff372aa8eL;
                                             continue;
                                          }

                                          value4 = (value4 ^ -0x6da41edaa45be572L) - 0x6fdae6d3ffbff5b3L;
                                          value5 = (value5 ^ -0x59f1da318f67f440L) + 0x5879ddcdf2e263f6L;
                                          break scanBlock;
                                       }
                                       break checkBlock;
                                    }

                                    count30++;
                                    value4 = (value4 ^ -0x60b14e0186579c35L) + 0x7eb6e9dd48303276L;
                                    value5 = (value5 ^ 0x540e0d4a2ee7f215L) + 0x16e75bc88953c4aeL;
                                 }

                                 if (count21 > countArray5[count30]) {
                                    value4 = (value4 ^ 0x1a3b5181dfe52a4dL) - 0x436ea5e15aaebf0L;
                                    value5 = (value5 ^ -0x75e67bcce42af8e7L) + 0xf13356ebe92c876L;
                                    continue;
                                 }

                                 value6 = (value4 ^ -0x3a0490c9eeb80179L) + 0x340918129cf6c75aL;
                                 value7 = (value5 ^ 0xe92e4d0319066c7L) - 0x6d73aaae6ba89960L;
                                 break evalBlock;
                              }

                              if (count29 > count30) {
                                 long value10 = (value4 ^ 0x46fe72469816a211L) + 0x2ee5ae00466bc99L;
                                 long value11 = (value5 ^ -0x32ba994a2685f952L) + 0x1d768fb75ad2e8f3L;
                                 count21--;
                                 boolean flag = true;

                                 int count35;
                                 for (count35 = count21 - count30; (count26 & 1) == 0; value11 = (value11 ^ 0x4ca4dfe891a6df82L) + 0x3464c5587059d782L) {
                                    count26 >>>= 1;
                                    count35++;
                                    value10 = (value10 ^ -0x3f2b18bf1a6f88bL) + 0x240ab213fdc6f96bL;
                                 }

                                 long value12 = (value10 ^ 0x7319adc01cab4dc3L) - 0x5308ab4013ab4c3fL;
                                 long value13 = (value11 ^ -0x7a4d2348fa86485L) - 0x17d92cc530356535L;
                                 Object entry3 = countMatrix[count26 >>> 1];
                                 int byteValue2 = 0;
                                 if (count35 != count21) {
                                    value12 = (value12 ^ -0x5def2b01ffa89d98L) + 0x5df02b9dffc8de58L;
                                    value13 = (value13 ^ 0x726177ed7c7141b2L) + 0x6e212d5ccbb14192L;
                                 } else {
                                    flag = false;
                                 }

                                 while (true) {
                                    count21--;
                                    byteValue2 <<= 1;
                                    long value14;
                                    long value15;
                                    if (count27 == 0) {
                                       value14 = (value12 ^ 0x1967639ddf65d1bfL) + 0x7649e7aae9b4fa5L;
                                       value15 = (value13 ^ 0x583af4d6b049ba66L) + 0x47c6ac568fb879deL;
                                    } else {
                                       byte byteValue3;
                                       if ((countArray7[count28] & count25) == 0) {
                                          value14 = (value12 ^ -0x4560ae14f72d8850L) + 0x667cb0a8f84de9b0L;
                                          value15 = (value13 ^ -0xf56f18d415a542L) - 0x1f096f98e5e9a57aL;
                                          byteValue3 = 0;
                                       } else {
                                          byteValue3 = 1;
                                          value14 = (value12 ^ -0x2adc2b29008ae080L) + 0x4b212bb5ceab0180L;
                                          value15 = (value13 ^ 0x7e054875c81d7002L) + 0x620537f538053002L;
                                       }

                                       byteValue2 |= byteValue3;
                                       count25 >>>= 1;
                                       if (count25 != 0) {
                                          value14 = (value14 ^ -0x1e6d750a65df89b6L) + 0x1f72759269dfea3aL;
                                          value15 = (value15 ^ -0x265132a080340f27L) - 0x38a54d3131c94fd7L;
                                       } else {
                                          count28++;
                                          count25 = Integer.MIN_VALUE;
                                          count27--;
                                       }
                                    }

                                    long value16;
                                    long value17;
                                    if ((byteValue2 & count22) == 0) {
                                       value16 = (value14 ^ 0x29d080108c825a8L) - 0x185080107c7e468L;
                                       value17 = (value15 ^ 0x4d32fd9b6d69110bL) + 0x32cea38adc9910fbL;
                                    } else {
                                       for (count35 = count21 - count30; (byteValue2 & 1) == 0; value15 = (value15 ^ 0x5fceef2d80909518L) + 0x41ba951d7f8f8ce8L) {
                                          byteValue2 >>>= 1;
                                          count35++;
                                          value14 = (value14 ^ 0x616102fba68ec43bL) - 0x60a10267598dc3d7L;
                                       }

                                       value16 = (value14 ^ -0x2dc362647c048eefL) + 0x4e1c64788324ef0fL;
                                       value17 = (value15 ^ 0x1d69cd5db6a7335bL) + 0x319b2cd955af2abL;
                                       entry3 = countMatrix[byteValue2 >>> 1];
                                       byteValue2 = 0;
                                    }

                                    long value18;
                                    long value19;
                                    if (count21 != count35) {
                                       value18 = (value16 ^ 0x42d3d0cadf3e0bdaL) - 0x414ed036511e0a36L;
                                       value19 = (value17 ^ 0x49935a33411c87f6L) + 0x376f4a32c1037f4eL;
                                    } else if (!flag) {
                                       value18 = (value16 ^ 0x36d9c02f40c409fcL) - 0x1548c012bfc4081cL;
                                       value19 = (value17 ^ -0x3f1c3019ec949042L) - 0x3f604a9a0d68d87aL;
                                       int[] countArray13 = resolveIndexArray(countArray12, (int[])entry3, countArray8, count20, value2, countArray10);
                                       countArray10 = countArray12;
                                       countArray12 = countArray13;
                                    } else {
                                       countArray12 = ((int[])entry3).clone();
                                       flag = false;
                                       value18 = (value16 ^ -0x4ea56293beb76666L) + 0x6f3a652c40b8878aL;
                                       value19 = (value17 ^ -0x5df28c3951bae4e7L) - 0x6202ceb96242e517L;
                                    }

                                    if (count21 == 0) {
                                       int[] countArray14 = new int[2 * count20];
                                       System.arraycopy(countArray12, 0, countArray14, count20, count20);
                                       int count36 = (int)value2;
                                       int[] countArray15 = resolveIndexArray(countArray14, countArray8, count20, count36);
                                       int[] countArray16 = Arrays.copyOf(countArray15, count20);
                                       return new SignumComparable(1, countArray16);
                                    }

                                    long value20 = (value18 ^ 0x429cf0374b53837bL) - 0x4183f02ac5328297L;
                                    long value21 = (value19 ^ -0x5c95c60d97b80ef3L) - 0x62ea188e28454f0bL;
                                    if (flag) {
                                       value12 = (value20 ^ 0xdb8aaddb9c87e8L) + 0xb9756aab8479d8L;
                                       value13 = (value21 ^ -0x304d507eb39a7ed5L) - 0x4eb1aa7f44637ee5L;
                                    } else {
                                       int[] countArray17 = resolveIndexArrayByIndexArray(countArray12, countArray8, count20, value2, countArray10);
                                       countArray10 = countArray12;
                                       countArray12 = countArray17;
                                       value12 = (value20 ^ 0x51e54fe96e490ddL) - 0x502546289e36f3dL;
                                       value13 = (value21 ^ -0x68195ab47251c4ffL) - 0x765da54483ae04ffL;
                                    }
                                 }
                              }

                              value4 = (value4 ^ -0xe9fdb42e141cbb1L) + 0x575010b8820f4eebL;
                              value5 = (value5 ^ 0x6f40fe556ef69a32L) + 0x7f7af6d6ab5e6d77L;
                              continue;
                           } else {
                              count23++;
                              value4 = (value4 ^ 0x1c1a81cd97197428L) - 0x97db060e76d40c8L;
                              value5 = (value5 ^ -0x5877cbc228789668L) + 0x67be72532b142e07L;
                           }

                           if (count23 >= count22) {
                              long value22 = (value4 ^ 0x5631891c28fb1f8L) + 0x22e42aa73c098668L;
                              long value23 = (value5 ^ 0x5a37817d9cbdb9f0L) + 0x19dfda8ea1e9f191L;
                              value3 = ((long)countArray8[count20 - 1] & 4294967295L) + (((long)countArray8[count20 - 2] & 4294967295L) << 32);
                              value4 = (value22 ^ -0x53108376bca989faL) - 0x65601fda58d73b13L;
                              value5 = (value23 ^ -0x1d4b07c6f018aae5L) + 0xa69e41ea61ada75L;
                           } else {
                              value4 = (value4 ^ 0x3714849364b1800fL) - 0x2e432d9998f8a538L;
                              value5 = (value5 ^ 0x33681f1abad06a08L) + 0x10c446e7e799e905L;
                           }
                           continue;
                        }

                        if (count25 >= count22) {
                           long value24 = (value4 ^ 0x392cf39d697175e3L) - 0x2c4ed1b3ed1d2b7L;
                           long value25 = (value5 ^ -0x71b06ff0453cea93L) + 0x259a8195e2911b20L;
                           count25 = 1 << (count21 - 1 & 31);
                           value4 = (value24 ^ -0x364a7c75573dc9bcL) - 0x71e642b6da5744afL;
                           value5 = (value25 ^ -0x59d3cfb5dd1d22f5L) + 0x3ba34cbc4f31668L;
                        } else {
                           value4 = (value4 ^ 0x2165811dac3242e4L) - 0xe8b0bc8b3900fcfL;
                           value5 = (value5 ^ -0x38780b6c74d60707L) - 0x76b63100ad09ba62L;
                        }
                        continue;
                     }

                     count22 = 1 << count30;
                     value4 = (value6 ^ 0x58d0c36ee92b3c82L) - 0x20487050e34e4ccL;
                     value5 = (value7 ^ 0x5ad1be93af15f122L) - 0x2f7cca8aed1b9991L;
                     continue;
                  }

                  count20 = countArray8.length;
                  value4 = (value4 ^ 0x514640564fe5936fL) + 0x6151b71b3cc80caL;
                  value5 = (value5 ^ -0x730096ec0fa8dfa4L) + 0x4c06f11887fced06L;
                  continue;
               }

               return signumComparable5;
            }

            count30 = 0;
            value4 = (value4 ^ 0x68bb4040274613b2L) + 0x37f39d9547232ac0L;
            value5 = (value5 ^ -0x767be5410e51b9b5L) - 0x5afd71ff3e66328L;
         } else {
            Object entry4 = countMatrix[count25 - 1];
            Object entry5 = null;
            Object entry6 = entry4;
            countMatrix[count25] = resolveIndexArray(countArray11, (int[])entry6, countArray8, count20, value2, (int[])entry5);
            value4 = (value4 ^ -0x514d25dda260375aL) - 0x3adf68f17a863f47L;
            value5 = (value5 ^ 0x5e66e811263eaabfL) + 0x7da5ef0b9fb1c855L;
         }
      }
   }

   public boolean checkIndexByIndex(int count20) {
      if (count20 >= 0) {
         int count21 = count20 >>> 5;
         return (this.computeCountByIndex(count21) & 1 << (count20 & 31)) != 0;
      } else {
         throw new ArithmeticException("Negative bit address");
      }
   }

   private SignumComparable resolveSignumComparable13(SignumComparable signumComparable7) {
      return this.resolveSignumComparableArray2(signumComparable7)[0];
   }

   public static int numberOfLeadingZeros(int count20) {
      return 32 - Integer.numberOfLeadingZeros(count20);
   }

   private static int[] resolveIndexArrayByByteArray(byte[] byteValueArray) {
      int count20 = 0;
      int count21 = 0;
      int count22 = 0;
      long value2 = 0x2d1f40b4e43377aL;
      long value3 = 0x72f9c1bd1fde51aaL;

      long value4;
      long value5;
      byte byteValue;
      outerLoop:
      while (true) {
         long value6;
         long value7;
         scanLoop:
         while (true) {
            long value8;
            long value9;
            innerLoop:
            while (true) {
               while (true) {
                  int count23 = (int)(value2 % 9L);
                  if (count23 != 2) {
                     if (count23 == 6) {
                        byteValue = 1;
                        value4 = (value2 ^ 0x5da955a30cb903ebL) + 0x35674c9d0446ffa9L;
                        value5 = (value3 ^ 0x71ea4945fa7ef811L) - 0x2ea9c74229e5680fL;
                        break outerLoop;
                     }

                     if (count23 == 0) {
                        count20++;
                        value2 = (value2 ^ 0x3aa8878e3b1ab442L) - 0xb5ef438cf6f37f6L;
                        value3 = (value3 ^ 0x3a155d2efed18026L) - 0x59f9f57f0def63a7L;
                        break;
                     }

                     if (count23 == 8) {
                        break;
                     }

                     if (count23 == 4) {
                        if (byteValueArray[count20] == -1) {
                           value2 = (value2 ^ 0x4477b0d4b6159256L) - 0x2f5c55d66b510d74L;
                           value3 = (value3 ^ 0x3e574b62311e2abeL) + 0x6173995970079730L;
                           continue;
                        }

                        value6 = (value2 ^ 0xfc9efc71398f3feL) + 0x1531d719382210f4L;
                        value7 = (value3 ^ 0x26c9b747a9e62bf5L) + 0x49e1cd3d073f9667L;
                        break scanLoop;
                     }

                     if (count23 == 7) {
                        count20 = 0;
                        value2 = (value2 ^ -0x3786b1d627372da6L) + 0x44f9a9c90119227cL;
                        value3 = (value3 ^ -0x3dfd96df573e42eaL) + 0x4c14dbcf17d629b4L;
                        continue;
                     }

                     if (count23 != 1) {
                        if (count23 == 5) {
                           count22 = byteValueArray.length;
                           value2 = (value2 ^ 0x667325d72c7648fdL) - 0x4570ba7178bfb754L;
                           value3 = (value3 ^ 0x14aaa317fdefd02eL) - 0xe707152693d9e46L;
                           continue;
                        }

                        if (byteValueArray[count21] == 0) {
                           value2 = (value2 ^ -0x6918e5c9317111a4L) + 0x5ffd5672fce0c8bdL;
                           value3 = (value3 ^ -0x646fba5d6bfeed04L) - 0x1990ce12284c051cL;
                           continue;
                        }

                        value8 = (value2 ^ 0x113ab9b1e9c620c9L) - 0x1cae48121fd76db8L;
                        value9 = (value3 ^ 0x7f625f5d437a339cL) + 0x23d4749ac8b1c04L;
                        break innerLoop;
                     }
                  } else {
                     count21++;
                     value2 = (value2 ^ 0xc78c4c2c487ef2eL) - 0x7ee230a9b9e70e2L;
                     value3 = (value3 ^ 0x5e2a3375c2f30201L) - 0xdd87b60cbd60d1dL;
                  }

                  if (count21 >= count22) {
                     value8 = (value2 ^ 0x1e9adbb1fe893466L) + 0x262fafda3cabd666L;
                     value9 = (value3 ^ 0x14d0dd20f593c82eL) + 0x3ade32ee591252eeL;
                     break innerLoop;
                  }

                  value2 = (value2 ^ -0x200a6774e0f5bdd4L) - 0x6a8bd1d9683da661L;
                  value3 = (value3 ^ -0x6789ec6eab52ac1dL) + 0x58a585f24e32b0c1L;
               }

               if (count20 >= count22) {
                  value6 = (value2 ^ 0x6c3cfbc2c8e01e7cL) - 0x536e8803c0d66200L;
                  value7 = (value3 ^ 0x4d0164899a9cfbd7L) + 0x550624199891ef9aL;
                  break scanLoop;
               }

               value2 = (value2 ^ 0xca589ccc151f08L) - 0x147ee51bec1dc792L;
               value3 = (value3 ^ -0x6a96ed7db5be6cf2L) + 0x2a356a2707b7e9f9L;
            }

            if (count21 != count22) {
               value4 = (value8 ^ 0x531d3e6d61d84a1bL) - 0x12a90a1901104d18L;
               value5 = (value9 ^ 0xe698d2596dfb38eL) + 0x28e77efdb44ac1e5L;
               byteValue = 0;
               break outerLoop;
            }

            value2 = (value8 ^ -0x6fe58df52d70247fL) - 0x51a5d5fd6e3bbd8eL;
            value3 = (value9 ^ 0x607a6bd7cfb942ffL) - 0x6909a39075182d4cL;
         }

         count21 = count20;
         value2 = (value6 ^ 0x5b63892c64959c81L) - 0x2c768303aa9ef137L;
         value3 = (value7 ^ 0x6788bc911aa5e1fdL) - 0x454c276b70aadcc8L;
      }

      byte byteValue2 = byteValue;
      int count24 = count22 - count20 + byteValue2 + 3 >>> 2;
      int[] countArray6 = new int[count24];
      int count25 = count22 - 1;
      int count26 = count24 - 1;

      while (count26 >= 0) {
         countArray6[count26] = byteValueArray[count25--] & 255;
         int count27 = Math.min(3, count25 - count20 + 1);
         if (count27 >= 0) {
            value4 = (value4 ^ -0x7b5bc6911eada37cL) + 0x7c63f8e2deb1c484L;
            value5 = (value5 ^ -0x5d854d8c89797ba7L) - 0x5e39720cad7d7c57L;
         } else {
            count27 = 0;
         }

         for (int byteValue3 = 8; byteValue3 <= 8 * count27; value5 = (value5 ^ 0x1afd660dff48515cL) - 0x15b4a5fa26b7cf54L) {
            countArray6[count26] |= (byteValueArray[count25--] & 255) << byteValue3;
            byteValue3 += 8;
            value4 = (value4 ^ 0x64f42ea0e82f576cL) + 0x5cb3e1a0e7dccb2cL;
         }

         long value10 = (value4 ^ 0x2f2286aaa82e818bL) + 0x26dd79a6a7dd8189L;
         long value11 = (value5 ^ 0x410bfc83696966c7L) - 0x3f0a3c7c98d8deb9L;
         int count28 = -1 >>> 8 * (3 - count27);
         countArray6[count26] = ~countArray6[count26] & count28;
         count26--;
         value4 = (value10 ^ -0x20de2c17c612d139L) - 0x49213197e8e4ed3bL;
         value5 = (value11 ^ -0xb9fbc0ab93fc08bL) + 0x53e03e6d42bfda95L;
      }

      long value12 = (value4 ^ -0x6a17f599d993aa3bL) + 0x6da805da1d9bcdc3L;
      long value13 = (value5 ^ 0x269a981275e39561L) + 0x26666812539d74a1L;
      int count29 = countArray6.length - 1;

      while (count29 >= 0) {
         countArray6[count29] = (int)(((long)countArray6[count29] & 4294967295L) + (value13 % 384304L ^ -161178L));
         if (countArray6[count29] != 0) {
            break;
         }

         long value14 = (value12 ^ -0x2e204fc1f9619f97L) - 0x4e278fee01921fd9L;
         long value15 = (value13 ^ -0x2ed7a9d99523d635L) + 0x31182a1e66bbe83bL;
         count29--;
         value12 = (value14 ^ 0x3847339399b414d9L) + 0x1840ee6d77540cd7L;
         value13 = (value15 ^ -0x2e9fcbb600e71263L) + 0x30e00bc9d8f8ac6dL;
      }

      return countArray6;
   }

   private SignumComparable[] resolveSignumComparableArray2(SignumComparable signumComparable7) {
      Object entry = null;
      Object entry2 = null;
      entry = new CurveAccumulator();
      CurveAccumulator curveAccumulator = new CurveAccumulator(this);
      CurveAccumulator curveAccumulator2 = new CurveAccumulator(signumComparable7);
      entry2 = curveAccumulator.resolveCurveAccumulator2(curveAccumulator2, (CurveAccumulator)entry);
      SignumComparable signumComparable8;
      if (!((CurveAccumulator)entry).isActive2()) {
         int count20 = this.count * signumComparable7.count;
         signumComparable8 = ((CurveAccumulator)entry).resolveSignumComparableByIndex(count20);
      } else {
         signumComparable8 = signumComparable5;
      }

      SignumComparable signumComparable9 = signumComparable8;
      SignumComparable signumComparable10;
      if (!((CurveAccumulator)entry2).isActive2()) {
         int count21 = this.count;
         signumComparable10 = ((CurveAccumulator)entry2).resolveSignumComparableByIndex(count21);
      } else {
         signumComparable10 = signumComparable5;
      }

      SignumComparable signumComparable11 = signumComparable10;
      return new SignumComparable[]{signumComparable9, signumComparable11};
   }

   public int getCount5() {
      long value2 = 0x6e0084f5d15a6bdbL;

      while ((int)(value2 % 3L) != 1) {
         value2 = (value2 ^ 0x4019d8f9c5055645L) + 0x405d9386bfb0288aL;
      }

      int byteValue = 0;
      return this.computeCountByIndex(byteValue);
   }

   private int getCount7() {
      return this.count < 0 ? -1 : 0;
   }

   public SignumComparable resolveSignumComparable15(SignumComparable signumComparable7) {
      int byteValue = 0;
      int count20 = 1343821706;
      char character = '\ue849';
      int[] entry = null;
      entry = new int[Math.max(this.getCountAsIndex(), signumComparable7.getCountAsIndex())];

      for (int index = 0; index < entry.length; index++) {
         int count21 = entry.length - index - 1;
         int count22 = this.computeCountByIndex(count21);
         int count23 = entry.length - index - 1;
         entry[index] = count22 & signumComparable7.computeCountByIndex(count23);
      }

      return resolveSignumComparable((int[])entry);
   }

   public SignumComparable resolveSignumComparable2(SignumComparable signumComparable8, SignumComparable signumComparable9) {
        SignumComparable signumComparable7 = null;
      SignumComparable signumComparable10 = signumComparable8;
      long value3 = 0x5df01474332422cdL;
      long value4 = 0x6a22222e1f88211aL;
      if (signumComparable9.count > 0) {
         value3 = (value3 ^ 0x406bbda44e97da8eL) + 0x5787db1b543f81e5L;
         value4 = (value4 ^ 0x319898aac370d6cfL) - 0x6451b90df9ac00f6L;
         if (signumComparable8.count == 0) {
            value3 = (value3 ^ 0x580abb6b48c10768L) + 0x15bffa88a2e549d8L;
            value4 = (value4 ^ 0x2198da4d75f7443bL) + 0x5eabdc4e49b75dc8L;
            SignumComparable signumComparable11 = signumComparable;
            SignumComparable signumComparable12;
            if (!signumComparable9.equals(signumComparable11)) {
               signumComparable12 = signumComparable;
            } else {
               value3 = (value3 ^ 0x79c857fda5f72458L) + 0x42bb4f4a7ae501e1L;
               value4 = (value4 ^ 0x72e4dde0a203aa3eL) + 0x7b9c152418f58526L;
               signumComparable12 = signumComparable5;
            }

            return signumComparable12;
         } else {
            long value5 = (value3 ^ 0x4c9ec89ef9b785bL) + 0xbf254dcd65de2aeL;
            long value6 = (value4 ^ 0x69182fd87e15783L) - 0x2ee303fe094761a4L;
            SignumComparable signumComparable13 = signumComparable;
            if (!this.equals(signumComparable13)) {
               long value7 = (value5 ^ 0x55ec3fd91cd395afL) + 0x55ac3a590caf7493L;
               long value8 = (value6 ^ 0x7dc8dfd0a714d7bbL) + 0x437df2f5ef357b5L;
               SignumComparable signumComparable14 = signumComparable5;
               long value9;
               long value10;
               if (!this.equals(signumComparable14)) {
                  value9 = (value7 ^ -0x271b69e4bc15322bL) - 0x275b6de5426943d3L;
                  value10 = (value8 ^ -0x3bbaa98eac3ed206L) - 0x443aaa6ead40d28aL;
               } else {
                  if (signumComparable8.count >= 0) {
                     return signumComparable5;
                  }

                  value9 = (value7 ^ 0x16c5067d92728b61L) + 0x12c301fc8e0e76e1L;
                  value10 = (value8 ^ -0x56069a565a7c08c9L) + 0x582965685c807729L;
               }

               SignumComparable signumComparable15 = signumComparableArray2[1];
               long value11;
               long value12;
               if (!this.equals(signumComparable15)) {
                  value11 = (value9 ^ 0x5ef2c44c8c9cdb39L) + 0x5aae43cb7c6ca709L;
                  value12 = (value10 ^ -0x24520ecd3aefebc3L) - 0x55a30f32430febcdL;
               } else {
                  int byteValue = 0;
                  if (!signumComparable8.checkIndexByIndex(byteValue)) {
                     SignumComparable signumComparable16 = signumComparable;
                     return !signumComparable9.equals(signumComparable16) ? signumComparable : signumComparable5;
                  }

                  value11 = (value9 ^ -0x3d9eb926a30644bbL) - 0x41e13ea733767943L;
                  value12 = (value10 ^ -0x7f82bf84109d2640L) + 0x7fad3f96a7615940L;
               }

               long value13;
               long value14;
               boolean flag;
               if (signumComparable8.count >= 0) {
                  value13 = (value11 ^ 0x49ce01f62f52ad1eL) + 0x49ca0075d92e9ce2L;
                  value14 = (value12 ^ 0x6627e7a3e770da4fL) + 0x1de2e75e9f4f59c1L;
                  flag = false;
               } else {
                  flag = true;
                  value13 = (value11 ^ 0x703494909e7c8487L) + 0x6ff4938f8a0c837bL;
                  value14 = (value12 ^ 0x563e34142cfcfe86L) + 0x2dea33f3e3cb7e7aL;
               }

               boolean flag2 = flag;
               if (!flag) {
                  value13 = (value13 ^ -0x4cf6070f12d264d0L) - 0x50f88890e52298f0L;
                  value14 = (value14 ^ 0x49cce496b2884a08L) + 0x383be4717177ca08L;
               } else {
                  signumComparable10 = signumComparable8.getSignumComparable2();
               }

               searchBlock: {
                  if (this.count < 0) {
                     value13 = (value13 ^ -0x22a0a2922128f09aL) - 0x22a72313b54d0526L;
                     value14 = (value14 ^ -0x5587dbe10fe46791L) + 0x59a823f9afe897e1L;
                  } else if (this.computeCountBySignumComparable(signumComparable9) < 0) {
                     signumComparable7 = this;
                     break searchBlock;
                  }

                  signumComparable7 = this.resolveSignumComparable2(signumComparable9);
               }

               SignumComparable signumComparable17 = signumComparable7;
               byte byteValue2 = 0;
               SignumComparable signumComparable18;
               if (!signumComparable9.checkIndexByIndex(byteValue2)) {
                  int count20 = signumComparable9.getCount4();
                  SignumComparable signumComparable19 = signumComparable9.resolveSignumComparable6(count20);
                  SignumComparable signumComparable20 = signumComparable.resolveSignumComparable4(count20);
                  SignumComparable signumComparable21 = this.count >= 0 && this.computeCountBySignumComparable(signumComparable19) < 0 ? this : this.resolveSignumComparable2(signumComparable19);
                  SignumComparable signumComparable22 = signumComparable;
                  SignumComparable signumComparable23 = !signumComparable19.equals(signumComparable22) ? signumComparable21.resolveSignumComparableBySignumComparable(signumComparable10, signumComparable19) : signumComparable5;
                  SignumComparable signumComparable24 = signumComparable17.resolveSignumComparable(signumComparable10, count20);
                  SignumComparable signumComparable25 = signumComparable20.resolveSignumComparable12(signumComparable19);
                  SignumComparable signumComparable26 = signumComparable19.resolveSignumComparable12(signumComparable20);
                  if (signumComparable9.countArray.length >= 33554432) {
                     CurveAccumulator curveAccumulator = new CurveAccumulator();
                     CurveAccumulator curveAccumulator2 = new CurveAccumulator(signumComparable23.resolveSignumComparable4(signumComparable20));
                     CurveAccumulator curveAccumulator3 = new CurveAccumulator(signumComparable25);
                     curveAccumulator2.applyCurveAccumulator(curveAccumulator3, curveAccumulator);
                     CurveAccumulator curveAccumulator4 = new CurveAccumulator();
                     CurveAccumulator curveAccumulator5 = new CurveAccumulator(signumComparable24.resolveSignumComparable4(signumComparable19));
                     CurveAccumulator curveAccumulator6 = new CurveAccumulator(signumComparable26);
                     curveAccumulator5.applyCurveAccumulator(curveAccumulator6, curveAccumulator4);
                     curveAccumulator.applyCurveAccumulator(curveAccumulator4);
                     CurveAccumulator curveAccumulator7 = new CurveAccumulator();
                     CurveAccumulator curveAccumulator8 = new CurveAccumulator(signumComparable9);
                     signumComparable18 = curveAccumulator.resolveCurveAccumulator4(curveAccumulator8, curveAccumulator7).getSignumComparable();
                  } else {
                     SignumComparable signumComparable27 = signumComparable23.resolveSignumComparable4(signumComparable20).resolveSignumComparable4(signumComparable25);
                     SignumComparable signumComparable28 = signumComparable24.resolveSignumComparable4(signumComparable19).resolveSignumComparable4(signumComparable26);
                     signumComparable18 = signumComparable27.resolveSignumComparable5(signumComparable28).resolveSignumComparable2(signumComparable9);
                  }
               } else {
                  signumComparable18 = signumComparable17.resolveSignumComparableBySignumComparable(signumComparable10, signumComparable9);
               }

               return flag2 ? signumComparable18.resolveSignumComparable12(signumComparable9) : signumComparable18;
            } else {
               SignumComparable signumComparable29 = signumComparable;
               return !signumComparable9.equals(signumComparable29) ? signumComparable : signumComparable5;
            }
         }
      } else {
         value3 = (value3 ^ -0x27be01e2c8974eb2L) - 0x2df6b95e026238acL;
         value4 = (value4 ^ -0x363065cdbc59da0cL) + 0x638e7be124b819bdL;
         throw new ArithmeticException("BigInteger: modulus not positive");
      }
   }

   private SignumComparable resolveSignumComparable(int count20, int count21, int count22, int count23) {
      int count24 = 0;
      int count25 = 0;
      int count26 = 0;
      int count27 = 0;
      int count28 = 0;
      int[] countArray6 = null;
      long value2 = 0x50685b3b562a82d0L;
      long value3 = -0x56e26ba4ea6d020L;

      while (true) {
         int count29 = (int)(value2 % 20L);
         if (count29 != 7) {
            if (count29 != 6) {
               if (count29 == 17) {
                  if (count22 != 0) {
                     long value4 = (value2 ^ 0x1d2dc9955c67cfd2L) + 0x388a690e87f9874fL;
                     long value5 = (value3 ^ 0x43bb01dad9fe3bd3L) + 0x4ef36e58d00b5f7eL;
                     count24 = count21 + (count22 - 1) * count20 - count28;
                     value2 = (value4 ^ -0x5cfeff37f8f8eb75L) + 0x79f6e7716d6f6a5bL;
                     value3 = (value5 ^ -0x37b21861236b1a5fL) + 0x68556e00c651425eL;
                  } else {
                     value2 = (value2 ^ 0x3c6d1b8dc9b357eL) + 0x612186a20f7e3298L;
                     value3 = (value3 ^ 0x4395252591c2984cL) - 0x4ff68b538d37ffbdL;
                  }
                  continue;
               }

               if (count29 == 18) {
                  count26 = count25 - count24 + 1;
                  value2 = (value2 ^ 0x1c33fd5d85cacb38L) - 0x200d7b8af027e75dL;
                  value3 = (value3 ^ -0xf8e797ecb4e84adL) + 0x7db92c9621f15c92L;
                  continue;
               }

               if (count29 == 3) {
                  count24 = 0;
                  value2 = (value2 ^ 0x7b3cd8b4a89c9f3eL) + 0x2cb7971e399b57b7L;
                  value3 = (value3 ^ 0x3bd08ba9ae9e44d5L) - 0x3f720cdca07bc08cL;
                  continue;
               }

               if (count29 == 0) {
                  count27 = this.countArray.length;
                  value2 = (value2 ^ 0x2868ed4169abf3eeL) - 0x19ace0f58a5ddd83L;
                  value3 = (value3 ^ 0x4ee66ed397f4806L) - 0x1c6e5bf4b27c7686L;
                  continue;
               }

               if (count29 == 14) {
                  countArray6 = new int[count26];
                  value2 = (value2 ^ -0x6e50c24f5f04c5ddL) - 0x3c274bfa20f54344L;
                  value3 = (value3 ^ -0x23ca030fcab69884L) - 0xfb492a4de97ec09L;
                  continue;
               }

               if (count29 == 19) {
                  count24 = 0 - count28;
                  value2 = (value2 ^ -0x1d2697fddb8580cfL) + 0x70113c3f9926d1aaL;
                  value3 = (value3 ^ -0x7915c987399ccd1L) - 0x2947b9d9abcbf86cL;
                  continue;
               }

               if (count29 == 9) {
                  return this.getSignumComparable5();
               }

               if (count29 == 8) {
                  return signumComparable5;
               }

               if (count29 == 13) {
                  if (count24 != 0) {
                     value2 = (value2 ^ 0x8309499e924f6d8L) - 0x271c834a91a5d85bL;
                     value3 = (value3 ^ -0x1a2d57e6d8d4d4c3L) + 0x54b75c33e8db6d15L;
                  } else {
                     value2 = (value2 ^ -0x1d1cc6448b12a25cL) + 0x7c747e97589ddd8eL;
                     value3 = (value3 ^ -0x151bc9aac18125a3L) - 0x7ef867e6ca65fb87L;
                  }
                  continue;
               }

               if (count29 == 16) {
                  count25 = count21 - 1 - count28;
                  value2 = (value2 ^ 0x317f94c4712f7f3fL) + 0x4f8c30ad1ab61f3L;
                  value3 = (value3 ^ 0x10de81eb35e05094L) - 0x60bf4abf88f7664fL;
                  continue;
               }

               if (count29 == 12) {
                  if (count25 >= 0) {
                     value2 = (value2 ^ -0x2f728a0d5b5093c7L) - 0x30ef7d8440249643L;
                     value3 = (value3 ^ 0x1c9c45d8f8ab8038L) + 0x7dad95738dd2420aL;
                     continue;
                  }

                  return signumComparable5;
               }

               if (count29 != 2) {
                  if (count29 != 5) {
                     if (count29 == 10) {
                        return new SignumComparable(resolveIndexArray3(countArray6), 1);
                     }

                     if (count29 == 15) {
                        if (count26 < count27) {
                           value2 = (value2 ^ -0x32cb75165aa24461L) + 0x7c333599e31f7612L;
                           value3 = (value3 ^ 0x65aecba8036bf1e9L) + 0x3b269f4ecd7f6915L;
                        } else {
                           value2 = (value2 ^ 0x2db74af3f993863dL) - 0x5640d4757a840535L;
                           value3 = (value3 ^ -0x15781cada40432f4L) + 0x65f6dccef3e9b756L;
                        }
                        continue;
                     }

                     if (count29 != 11) {
                        if (count29 != 4) {
                           System.arraycopy(this.countArray, count24, countArray6, 0, count26);
                           value2 = (value2 ^ -0x1fff50dcb026175bL) + 0x76ba8ff594faa946L;
                           value3 = (value3 ^ 0x4f85041d764f12e1L) - 0x6e3ddda6d8f44763L;
                        } else {
                           count25 = count24 + count20 - 1;
                           value2 = (value2 ^ 0x24f3ffc02e44834fL) - 0x12bd70ece699f3a9L;
                           value3 = (value3 ^ 0x74f5300d489266a6L) - 0x21324ff9cd84c985L;
                        }
                        continue;
                     }

                     return signumComparable5;
                  }

                  if (count26 > 0) {
                     value2 = (value2 ^ -0x54136d3358871674L) - 0x55a60e5e95e93474L;
                     value3 = (value3 ^ -0x287fa566739775ecL) - 0x591355f82a0377f9L;
                     continue;
                  }

                  return signumComparable5;
               }

               value2 = (value2 ^ 0x2e15d059195a51ddL) + 0xf3c93357eebb423L;
               value3 = (value3 ^ -0x29f7d1a813056abeL) + 0x4cb854c4c0c4e25aL;
            }

            if (count24 >= 0) {
               value2 = (value2 ^ -0x4aa49f994367c975L) - 0x3a55a7b7f49f22b1L;
               value3 = (value3 ^ -0x12c0d95cd7c1c923L) - 0x271e38bd80aac23aL;
            } else {
               value2 = (value2 ^ -0x7a398abc4e6cd49L) + 0x7d077ccff1aa206aL;
               value3 = (value3 ^ 0x585683cbc3f06027L) + 0x594cfd1d9afce3d3L;
            }
         } else {
            count28 = count23 - count27;
            value2 = (value2 ^ -0x7491bb3719ef45c8L) + 0x4663af572e4527deL;
            value3 = (value3 ^ -0x75924886d375776bL) + 0xa0899db77fc9890L;
         }
      }
   }

   private static int computeCount(int[] countArray6, int[] countArray7, int count20, int count21, int count22) {
      long value2 = 0L;
      long value3 = 0L;
      long value4 = 0L;
      value2 = (long)count22 & 4294967295L;
      value3 = 0L;
      int count23 = countArray6.length - count20 - 1;

      for (int index = count21 - 1; index >= 0; index--) {
         value4 = ((long)countArray7[index] & 4294967295L) * value2 + ((long)countArray6[count23] & 4294967295L) + value3;
         countArray6[count23--] = (int)value4;
         value3 = value4 >>> 32;
      }

      return (int)value3;
   }

   private SignumComparable resolveSignumComparable7(int count20) {
      int count21 = 0;
      int count22 = 0;
      int count23 = 0;
      long value2 = 0x595ec66754dd8f82L;
      long value3 = 0x16248ee2d4872c1cL;
      count21 = count20 >>> 5;
      value2 = (value2 ^ -0x14bb5dd5d3467deaL) - 0x72a686ca867ea3d9L;
      value3 = (value3 ^ -0x702e3403854b0952L) - 0x5ecb2aebb9d85522L;
      count22 = count20 & 31;
      value2 = (value2 ^ -0x512d3581b7280461L) - 0x4ed62ee00d36b4e5L;
      value3 = (value3 ^ 0x2f3d5cce4da52381L) - 0x10259187d5c11c15L;
      count23 = this.countArray.length;
      value2 = (value2 ^ 0x12a52ff5f2c6ed58L) - 0x15e8083f7921f463L;
      value3 = (value3 ^ 0x68f2abbda39861abL) + 0x1475ee3c4667765cL;
      value2 = (value2 ^ 0x492143abb89ce7b1L) + 0x8e6c7e7ab05bec8L;
      value3 = (value3 ^ 0x60e29709dea28c8cL) - 0x3319b82efc220187L;
      if (count21 >= count23) {
         value2 = (value2 ^ -0x2ed03e82579b2b2L) - 0x6673cf172cef0afL;
         value3 = (value3 ^ -0x1feafa64e7a3d9a5L) + 0x4448d48c25fe8782L;
         SignumComparable signumComparable7;
         if (this.count < 0) {
            signumComparable7 = signumComparableArray2[1];
         } else {
            value2 = (value2 ^ -0x603b0ab5e8c33b9L) - 0xa54ab786155ebe9L;
            value3 = (value3 ^ 0x65b931bb9f1bc6d7L) - 0x37ae3b7fc6a6b903L;
            signumComparable7 = signumComparable5;
         }

         return signumComparable7;
      } else {
         long value4 = (value2 ^ -0x688f36ad43bd647cL) - 0x738605d859d5c31fL;
         long value5 = (value3 ^ 0x2452cb6753977e08L) + 0x34e32330200a50ffL;
         int[] countArray6;
         long value6;
         long value7;
         if (count22 != 0) {
            long value8 = (value4 ^ -0x48feace6b503ae6cL) - 0x56ff4d18c90c3074L;
            long value9 = (value5 ^ -0x3b4fd3d61ace34e0L) - 0x3b5013d99af034e0L;
            int count24 = 0;
            int count25 = this.countArray[0] >>> count22;
            long value10;
            long value11;
            if (count25 == 0) {
               value10 = (value8 ^ 0x19c432c5ab0b47deL) + 0x16c3d2ba9706c5d6L;
               value11 = (value9 ^ -0x24220598f44e9201L) - 0x28b569a3057092a1L;
               countArray6 = new int[count23 - count21 - 1];
            } else {
               countArray6 = new int[count23 - count21];
               countArray6[count24++] = count25;
               value10 = (value8 ^ 0x7e95ab08412839f2L) + 0x71945af8412837b2L;
               value11 = (value9 ^ 0x7dd2d19385448a5aL) + 0x7d51b17084c489baL;
            }

            int count26 = 32 - count22;

            for (int index = 0; index < count23 - count21 - 1; value11 = (value11 ^ -0x254622e46c6da187L) - 0x295943078d91a227L) {
               countArray6[count24++] = this.countArray[index++] << count26 | this.countArray[index] >>> count22;
               value10 = (value10 ^ 0x1e82fa3c1ce3e44aL) + 0x11810a03ecde644aL;
            }

            value6 = (value10 ^ 0x2ca232630eb34fa8L) + 0x23a1d25cfaaecd68L;
            value7 = (value11 ^ -0x1f66455997493331L) - 0x1f79a962286d33b1L;
         } else {
            int count27 = count23 - count21;
            countArray6 = Arrays.copyOf(this.countArray, count27);
            value6 = (value4 ^ -0x3c54beabb4c47d47L) - 0x43653ed3c8c97d89L;
            value7 = (value5 ^ 0x7800c52b847a31eeL) + 0x77ffc52883c6316eL;
         }

         if (this.count < 0) {
            boolean flag = false;
            int count28 = count23 - 1;
            int count29 = count23 - count21;

            while (count28 >= count29 && !flag) {
               long value12;
               long value13;
               boolean flag2;
               if (this.countArray[count28] == 0) {
                  value12 = (value6 ^ -0x3d90896df78aee91L) - 0x41a169920792f0dbL;
                  value13 = (value7 ^ 0x7ebb5b97b15decabL) + 0x7aa9376c90abecabL;
                  flag2 = false;
               } else {
                  flag2 = true;
                  value12 = (value6 ^ 0x4b52908a9199390aL) + 0x455170757199370aL;
                  value13 = (value7 ^ 0x1fa07af16222777aL) + 0x1ba016cee21e76daL;
               }

               flag = flag2;
               count28--;
               value6 = (value12 ^ 0x12078db8f36ddc63L) + 0xe067d87cf645c21L;
               value7 = (value13 ^ 0x178da464185b3540L) + 0x137a645be7a534c0L;
            }

            if (!flag && count22 != 0) {
               flag = this.countArray[count23 - count21 - 1] << 32 - count22 != 0;
            }

            if (flag) {
               countArray6 = this.resolveIndexArray(countArray6);
            }
         }

         return new SignumComparable(countArray6, this.count);
      }
   }

   private boolean isActive2() {
      SignumComparable signumComparable7 = null;
      int count20 = 0;
      long value2 = 0x636b41576fc8a681L;
      long value3 = 0x71402bf9077eca43L;

      while (true) {
         while (true) {
            int count21 = (int)(value2 % 5L);
            long value4;
            long value5;
            int count22;
            if (count21 != 0) {
               if (count21 != 3) {
                  if (count21 != 1) {
                     if (count21 == 4) {
                        SignumComparable signumComparable8 = signumComparable;
                        signumComparable7 = this.resolveSignumComparable5(signumComparable8);
                        value2 = (value2 ^ 0x42eff80693b1702cL) + 0x86e04dc8d2cdd9eL;
                        value3 = (value3 ^ -0x632b920c6203027dL) + 0x6fe0c0d485e5eddeL;
                     } else {
                        count20 = 5;
                        value2 = (value2 ^ 0x4b9bffc24589e5a3L) - 0x269183e2921afd2aL;
                        value3 = (value3 ^ 0x74d240c1e4ca1158L) + 0x1dc3db40d348214aL;
                     }
                     continue;
                  }
                  break;
               }

               count22 = Math.abs(count20) + 2;
               value4 = (value2 ^ 0x5000f6eadb3edb1dL) + 0x4fff0a1636dd48fbL;
               value5 = (value3 ^ -0x48c33fb735bca9beL) + 0x74fc404849c34dbeL;
            } else {
               if (count20 < 0) {
                  value2 = (value2 ^ 0x52831f997b8eb1e6L) + 0x41d6d7fab028c21cL;
                  value3 = (value3 ^ -0x17a70dc945746496L) + 0x68f58e040861d9a7L;
                  continue;
               }

               value4 = (value2 ^ -0x75dc3828cc1d732aL) - 0x66c9e043077b12b4L;
               value5 = (value3 ^ -0x1441bde6a16fb332L) + 0x685adddd2c48a603L;
               count22 = -(count20 + 2);
            }

            count20 = count22;
            value2 = (value4 ^ -0x77b0ba1f2b119db0L) + 0x476e71a5dc330022L;
            value3 = (value5 ^ -0x12309918721d8285L) - 0x6be8d55f6b76d8b3L;
            break;
         }

         if (computeCount(count20, this) == -1) {
            SignumComparable signumComparable9 = resolveSignumComparable(count20, signumComparable7, this);
            SignumComparable signumComparable10 = signumComparable9.resolveSignumComparable2(this);
            SignumComparable signumComparable11 = signumComparable5;
            return signumComparable10.equals(signumComparable11);
         }

         value2 = (value2 ^ -0x3c27e9aba295606fL) + 0x70bc85b23be9fa27L;
         value3 = (value3 ^ 0x5ebe69f1088cf77aL) + 0x42aa7b39d444011L;
      }
   }

   private SignumComparable resolveSignumComparable(SignumComparable signumComparable7, int count20) {
      SignumComparable signumComparable8 = null;
      Object entry = null;
      int count21 = 0;
      int count22 = 0;
      long value3 = 0x5c45d35f739d77a6L;
      long value4 = 0xe77609375cfc2d1L;
      signumComparable8 = signumComparable;
      value3 = (value3 ^ -0x75d61a32c5e552c2L) + 0x5b1c5a035f2726f5L;
      value4 = (value4 ^ 0x1c01c8d711686089L) - 0x5e798ffdc985cddaL;
      entry = this.resolveSignumComparable5(count20);
      value3 = (value3 ^ 0x3aaa0dc1cfa6ab06L) + 0x723811da292040adL;
      value4 = (value4 ^ -0x451cd8102d2ef7d8L) + 0x2f4fd9c8f7dd2842L;
      count21 = 0;
      value3 = (value3 ^ -0x51514f3c87241372L) + 0x6ad424d8ac6ee1c7L;
      value4 = (value4 ^ -0x7c489192e98fce44L) - 0x6e17f79aaeec1765L;
      count22 = signumComparable7.getCount3();
      value3 = (value3 ^ -0x780e27140a425b62L) + 0x537befec96239aa2L;
      value4 = (value4 ^ 0x24d80ea1834b8f38L) - 0x4f1bf386ac835be6L;
      int byteValue = 0;
      if (this.checkIndexByIndex(byteValue)) {
         value3 = (value3 ^ 0x4a288bf358bed8d2L) - 0x176c8b6e530561bbL;
         value4 = (value4 ^ -0x722e40d10c37817L) + 0x21e89d01c3b0c7b4L;
         int count23;
         if (count20 - 1 >= count22) {
            count23 = count22;
         } else {
            value3 = (value3 ^ 0x2e8f50ed188bbd7aL) + 0x5789bcf834507a21L;
            value4 = (value4 ^ 0x43b4d2fa10a35780L) + 0x515bb2c910e8f6aL;
            count23 = count20 - 1;
         }

         count22 = count23;
      }

      while (count21 < count22) {
         if (signumComparable7.checkIndexByIndex(count21)) {
            signumComparable8 = signumComparable8.resolveSignumComparable4((SignumComparable)entry).resolveSignumComparable5(count20);
         }

         if (++count21 < count22) {
            entry = ((SignumComparable)entry).getSignumComparable().resolveSignumComparable5(count20);
         }
      }

      return signumComparable8;
   }

   private static int[] resolveIndexArrayByIndexArray(int[] countArray6, int count20) {
      int count21 = 0;
      int count22 = 0;
      int count23 = 0;
      int[] countArray7 = null;
      int count24 = 0;
      int count25 = 0;
      int count26 = 0;
      int count27 = 0;
      long value2 = 0x8cee62add84c5f9L;
      long value3 = -0x5287574e884cc569L;

      while (true) {
         int count28 = (int)(value2 % 19L);
         searchBlock:
         if (count28 != 5) {
            if (count28 == 13) {
               countArray7 = new int[count23 + count21];
               value2 = (value2 ^ 0x49626809bf50a331L) - 0x2787e3e8862f2116L;
               value3 = (value3 ^ 0x344f96b660123c6dL) + 0x53100bcf014f2f2aL;
               continue;
            }

            if (count28 == 14) {
               countArray7 = null;
               value2 = (value2 ^ 0x4fbf16bb1975eff8L) + 0x4e7cb61e1163f38fL;
               value3 = (value3 ^ 0x1f981dc58afefbafL) + 0x72604ef3d6f1851cL;
               continue;
            }

            if (count28 == 12) {
               count23 = countArray6.length;
               value2 = (value2 ^ 0x695216a2ea6a291cL) + 0x7e6a1a81059e4bfL;
               value3 = (value3 ^ 0x174866dcd7c86414L) + 0x7885eb12008ea2ccL;
               continue;
            }

            if (count28 == 10) {
               count26 = countArray6[0] >>> count25;
               value2 = (value2 ^ -0x17e768c048db9f33L) - 0x3228514b6f70d030L;
               value3 = (value3 ^ 0x5e00475bc5577a8fL) - 0x7fb0998f5cb80054L;
               continue;
            }

            if (count28 == 6) {
               break;
            }

            if (count28 == 8) {
               count22 = count20 & 31;
               value2 = (value2 ^ 0x65da8bc8ed5fbb7bL) + 0x23d0abb961aaa35bL;
               value3 = (value3 ^ 0x21de272ff0c57e79L) - 0x7366d541f6654aaeL;
               continue;
            }

            if (count28 != 7) {
               if (count28 != 18) {
                  if (count28 != 1) {
                     if (count28 != 0) {
                        if (count28 != 4) {
                           if (count28 != 3) {
                              if (count28 != 15) {
                                 if (count28 != 9) {
                                    if (count28 == 16) {
                                       break;
                                    }

                                    if (count28 != 17) {
                                       if (count28 != 2) {
                                          countArray7[count24++] = countArray6[count27++] << count22 | countArray6[count27] >>> count25;
                                          value2 = (value2 ^ 0x761ddad2273129f5L) - 0x1c22aec0a7122c25L;
                                          value3 = (value3 ^ 0x6b5e1b9b2a672322L) - 0x3caaa0d7cd5a0e2aL;
                                       } else {
                                          count25 = 32 - count22;
                                          value2 = (value2 ^ 0x109382511d9b5b40L) - 0x2e8aadd6911af1fL;
                                          value3 = (value3 ^ -0x5d57ff84cd629eefL) - 0x4ede7c3a4a966958L;
                                       }
                                       continue;
                                    }

                                    System.arraycopy(countArray6, 0, countArray7, 0, count23);
                                    break;
                                 }

                                 count21 = count20 >>> 5;
                                 value2 = (value2 ^ -0x2bf45a1b6563dac0L) - 0x62d3eea3f277e73cL;
                                 value3 = (value3 ^ 0x3380bda49fff46b1L) - 0x7f5c109b3d4b6224L;
                                 continue;
                              }

                              countArray7[count24++] = count26;
                              value2 = (value2 ^ 0x5402e0ca297bfb5bL) - 0x327080b8708263fL;
                              value3 = (value3 ^ 0x60cf1f6b9a4d9293L) + 0x7c2f1489f456f3d6L;
                              continue;
                           }

                           if (count26 == 0) {
                              long value4 = (value2 ^ 0x77829bd1daf1769bL) - 0x1c9c876ef1189436L;
                              long value5 = (value3 ^ -0x9c45e8335a96ad2L) + 0x2904d70b7bee7d75L;
                              countArray7 = new int[count23 + count21];
                              value2 = (value4 ^ -0x23b9ad7585eeffbL) + 0x6ecb0c0f5ac108d0L;
                              value3 = (value5 ^ 0x3dcb90011cc123c7L) + 0x4f8ab7633101f0bL;
                           } else {
                              value2 = (value2 ^ -0x3845c40eb310b0d1L) - 0x591928aad4e7042dL;
                              value3 = (value3 ^ 0x3b58ae3b47a9b1a9L) + 0x224f53a3d876a560L;
                           }
                           continue;
                        }

                        if (count22 != 0) {
                           long value6 = (value2 ^ -0x7929eb7ab978f8acL) - 0x77f6584639ec78f4L;
                           long value7 = (value3 ^ -0x26e32f985a9285a5L) + 0x71d0463c7c2c8486L;
                           count24 = 0;
                           value2 = (value6 ^ -0x14ad5e5291c7201dL) - 0xc0b9417ee763ca2L;
                           value3 = (value7 ^ 0x52aa86f45ab2d67L) - 0x3341dddb8d4239e0L;
                        } else {
                           value2 = (value2 ^ -0x3909b1250dd5351fL) - 0x66cb6c125588ef55L;
                           value3 = (value3 ^ -0x643c96e60876e9dbL) - 0x48a10b6abd2816fdL;
                        }
                        continue;
                     }

                     countArray7 = new int[count23 + count21 + 1];
                     value2 = (value2 ^ -0x73f60a3a6f521396L) - 0x587e2297ba8e78b1L;
                     value3 = (value3 ^ -0x64e2028799d02459L) + 0x616be84e708280acL;
                     continue;
                  }
                  break searchBlock;
               }
            } else {
               value2 = (value2 ^ 0x6aacb1de67ec04e5L) - 0x5c409b65720bd0L;
               value3 = (value3 ^ -0x6ab6ba5cecbfe96bL) + 0x5d777dd4398f53bdL;
            }

            count27 = 0;
            value2 = (value2 ^ 0x41aba7b2076af41eL) - 0x1cc1431e49bccf31L;
            value3 = (value3 ^ 0x73e8e4ae938b6f2eL) + 0x6da2c48b9c2f6d4dL;
            continue;
         } else {
            value2 = (value2 ^ -0x610f4d0745650fe2L) + 0x70a8b78a0f148d51L;
            value3 = (value3 ^ -0x545fcc6d3b72dfc6L) + 0x76bb9a834900cfcL;
         }

         if (count27 >= count23 - 1) {
            countArray7[count24] = countArray6[count27] << count22;
            break;
         }

         value2 = (value2 ^ 0x21858bed6d9e4144L) + 0x2a11b47067b134b5L;
         value3 = (value3 ^ 0x52daf2bfb34cd975L) + 0x505a80e1b9a8d595L;
      }

      return countArray7;
   }

   public byte[] getByteArrayAsByteArray() {
      int count20 = 0;
      byte[] byteValueArray = null;
      int count21 = 0;
      int count22 = 0;
      int count23 = 0;
      int count24 = 0;
      long value2 = 0x2437511f7fed4607L;
      long value3 = 0x2b8a03c3f7b1b9c6L;

      while (true) {
         int count25 = (int)(value2 % 13L);
         if (count25 != 2) {
            if (count25 != 8) {
               searchBlock: {
                  if (count25 != 0) {
                     if (count25 == 1) {
                        count22 = 4;
                        value2 = (value2 ^ -0x2dae88c2a10d5ca9L) + 0x6254269cfb793139L;
                        value3 = (value3 ^ 0x76314a61bc532b19L) - 0x247951f4f1dc83a0L;
                        continue;
                     }

                     if (count25 == 6) {
                        count22 = 1;
                        value2 = (value2 ^ 0x2d0dac9590e48b84L) - 0x4609e398d0906b99L;
                        value3 = (value3 ^ 0xe0a00002346cb3aL) - 0x3a5e2f53e48a660fL;
                        continue;
                     }

                     if (count25 == 4) {
                        int count26 = count24++;
                        count23 = this.computeCountByIndex(count26);
                        value2 = (value2 ^ 0xa193e5cf5a38e87L) - 0x1560a5b7bd28d9eeL;
                        value3 = (value3 ^ -0x3ee4fbfb90ef5157L) - 0x417ea3f5d6f95c53L;
                        continue;
                     }

                     if (count25 == 12) {
                        count21 = count20 - 1;
                        value2 = (value2 ^ 0x7ecd6c3aed97aaa3L) - 0x7e877aa34d9eeb6L;
                        value3 = (value3 ^ -0x3d35fe59a0f1f9e8L) - 0x49a92c2125c11339L;
                        continue;
                     }

                     if (count25 == 5) {
                        count22++;
                        break searchBlock;
                     }

                     if (count25 != 7) {
                        if (count25 == 11) {
                           count23 = 0;
                           value2 = (value2 ^ 0x3fd9947d66d41850L) + 0xe4dac36f825fd4eL;
                           value3 = (value3 ^ -0x54d91ba2f599fce9L) - 0x660152baa8104949L;
                           continue;
                        }

                        if (count25 != 3) {
                           if (count25 != 9) {
                              count20 = this.getCount3() / 8 + 1;
                              value2 = (value2 ^ 0x1b3d9d74bc756f63L) + 0x3ecd86db65fe4e5L;
                              value3 = (value3 ^ 0x3970932340449d4eL) - 0x5427cfffb8de1e48L;
                           } else {
                              count24 = 0;
                              value2 = (value2 ^ 0x1e95fb9b5a164d82L) + 0x276fff06fc313458L;
                              value3 = (value3 ^ 0xc11d4d1111cc44cL) - 0x5a5a8ca1fc1a8fe0L;
                           }
                           continue;
                        }

                        value2 = (value2 ^ 0x4d3e2b936d86e8b8L) - 0x23fc3bb799c947deL;
                        value3 = (value3 ^ 0x563f005eaab69275L) - 0x1b2fe33984b9d3b0L;
                        break searchBlock;
                     }
                  } else {
                     count21--;
                     value2 = (value2 ^ 0x21bf2f7bfacff826L) - 0x71f464b493ce84L;
                     value3 = (value3 ^ -0xd85eda3209091f3L) - 0x66ec83f62cf6d981L;
                  }

                  if (count21 < 0) {
                     return byteValueArray;
                  }

                  value2 = (value2 ^ 0x1baadcdabf995b8cL) - 0x14d2a0582f9f7ee7L;
                  value3 = (value3 ^ 0x6fd54fb4823aa7fbL) - 0x28a9d7789e30e08dL;
                  continue;
               }

               byteValueArray[count21] = (byte)count23;
               value2 = (value2 ^ -0x240f65c820f815c1L) + 0x7abf7a7813f93747L;
               value3 = (value3 ^ -0x1531d8ec0af02285L) + 0x16d6d3caabbe342cL;
            } else {
               byteValueArray = new byte[count20];
               value2 = (value2 ^ 0x6debf3e9ab5f01e3L) + 0x2cdac1d8c5a54658L;
               value3 = (value3 ^ -0x2a2844871bd07774L) - 0xf5262f9f15ce484L;
            }
         } else if (count22 != 4) {
            long value4 = (value2 ^ -0x28761c2a19872acbL) + 0x57398cc39c6b43d6L;
            long value5 = (value3 ^ 0x73f45c05c5f98391L) - 0x372e08463ad56111L;
            count23 >>>= 8;
            value2 = (value4 ^ 0x2d92be0971a66b72L) + 0x3ba72ec26a1639e8L;
            value3 = (value5 ^ 0x1ee0e8eafcd17d3cL) - 0x6262fb855697f667L;
         } else {
            value2 = (value2 ^ -0x7e5376ca8940b2caL) - 0x4c72ce324924df9dL;
            value3 = (value3 ^ -0x6bc7f2eec66a27a3L) - 0x4c04800ceda7cb18L;
         }
      }
   }

   public final int computeCount(SignumComparable signumComparable7) {
      int[] countArray6 = null;
      int count20 = 0;
      int[] countArray7 = null;
      int count21 = 0;
      int count22 = 0;
      int count23 = 0;
      int count24 = 0;
      long value2 = 0x1dc5083813adb58cL;
      long value3 = -0x10ec51666eec2290L;

      byte byteValue;
      while (true) {
         int count25 = (int)(value2 % 15L);
         if (count25 == 5) {
            countArray7 = signumComparable7.countArray;
            value2 = (value2 ^ -0x4f01b375e8185281L) - 0x3d089afccd2c549fL;
            value3 = (value3 ^ 0x2a6af61df0fe7703L) - 0xe8a9d30d614bd9L;
         } else if (count25 != 10) {
            if (count25 != 14) {
               if (count25 == 13) {
                  if (count20 > count21) {
                     return 1;
                  }

                  value2 = (value2 ^ -0x7e206ee01ea37bd4L) - 0x5447772b04fc1102L;
                  value3 = (value3 ^ 0x586bab0e817d69afL) + 0x73743a16d33f8c0L;
                  continue;
               }

               if (count25 == 8) {
                  countArray6 = this.countArray;
                  value2 = (value2 ^ 0x5bd3acc92474b2d6L) - 0x1afe0ce41899a692L;
                  value3 = (value3 ^ 0x42a78f596f87e232L) - 0x1ff5b5456c77ee35L;
                  continue;
               }

               if (count25 == 9) {
                  count23 = countArray6[count22];
                  value2 = (value2 ^ 0x3cf8fd3fed10bbf0L) - 0x9cad387073a47caL;
                  value3 = (value3 ^ 0x2bb1baeb604904aeL) + 0x6039c379970af040L;
                  continue;
               }

               if (count25 == 3) {
                  count24 = countArray7[count22];
                  value2 = (value2 ^ 0x1f75d00f721ff198L) + 0x191c365b1c2a7a8dL;
                  value3 = (value3 ^ 0x614b23f51d4cfa1aL) + 0x8c5c3623154eedcL;
                  continue;
               }

               if (count25 == 6) {
                  if (((long)count23 & 4294967295L) >= ((long)count24 & 4294967295L)) {
                     byteValue = 1;
                     break;
                  }

                  value2 = (value2 ^ -0x34d9d978c07dd807L) + 0x400b218c790a64cbL;
                  value3 = (value3 ^ -0x54c0997e57cd3ffbL) + 0x7ea401aad2fafb4L;
                  continue;
               }

               if (count25 == 4) {
                  count22 = 0;
                  value2 = (value2 ^ -0x9c520fcb3879ef5L) - 0x3447846d53da28d1L;
                  value3 = (value3 ^ 0x3b9fcb5eafd8006fL) - 0x3aea8f8638431ecL;
                  continue;
               }

               if (count25 == 12) {
                  count21 = countArray7.length;
                  value2 = (value2 ^ 0x436b6c126fb7a2b3L) + 0x4b622e8119af4157L;
                  value3 = (value3 ^ 0x26dd9101a398e32fL) - 0x1b53390e26d3238cL;
                  continue;
               }

               if (count25 == 7) {
                  count20 = countArray6.length;
                  value2 = (value2 ^ 0x6f270a7e25744d1bL) - 0xb5f3ada35b55c44L;
                  value3 = (value3 ^ -0x21e9732ac5846301L) + 0x6a5fe26028c08397L;
                  continue;
               }

               if (count25 == 0) {
                  byteValue = -1;
                  break;
               }

               if (count23 != count24) {
                  value2 = (value2 ^ 0x288a98ae1edaa740L) - 0x73280814d5b79551L;
                  value3 = (value3 ^ -0x31bebf7e26667ffdL) + 0x6dc5f6c12c1589fbL;
                  continue;
               }

               long value4 = (value2 ^ 0x310cec2d9c49a86L) - 0x55a115f989351fdbL;
               long value5 = (value3 ^ 0x4502c32bc70b2405L) - 0x649f6b39b13dea5aL;
               count22++;
               value2 = (value4 ^ 0x5b838552d36791c2L) + 0x1ff4c7f9ecf7ac0bL;
               value3 = (value5 ^ 0x4e3ead5a89704897L) + 0x126f62adfebc6facL;
            }

            if (count22 >= count20) {
               return 0;
            }

            value2 = (value2 ^ 0x2c505d47245e6229L) - 0x47512afe193ddf02L;
            value3 = (value3 ^ 0x7bf87a7f4e0836ecL) - 0x7d6d83ebc55c1878L;
         } else {
            if (count20 < count21) {
               return -1;
            }

            value2 = (value2 ^ -0x58561fa10d239eddL) + 0x3ba88309af453221L;
            value3 = (value3 ^ 0x41b8bb4ecf94c81aL) + 0x203d9e6be93edcfaL;
         }
      }

      return byteValue;
   }

   public SignumComparable resolveSignumComparable3(SignumComparable signumComparable7) {
      int[] entry = null;
      entry = new int[Math.max(this.getCountAsIndex(), signumComparable7.getCountAsIndex())];

      for (int index = 0; index < entry.length; index++) {
         int count20 = entry.length - index - 1;
         int count21 = this.computeCountByIndex(count20);
         int count22 = entry.length - index - 1;
         entry[index] = count21 | signumComparable7.computeCountByIndex(count22);
      }

      return resolveSignumComparable((int[])entry);
   }

   private SignumComparable getSignumComparable3() {
      int count20 = 0;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      count20 = (this.countArray.length + 1) / 2;
      entry = this.resolveSignumComparable(count20);
      entry2 = this.resolveSignumComparable9(count20);
      entry3 = ((SignumComparable)entry2).getSignumComparable();
      entry4 = ((SignumComparable)entry).getSignumComparable();
      int count21 = count20 * 32;
      SignumComparable signumComparable7 = ((SignumComparable)entry3).resolveSignumComparable4(count21);
      SignumComparable signumComparable8 = ((SignumComparable)entry).resolveSignumComparable5((SignumComparable)entry2).getSignumComparable();
      SignumComparable signumComparable9 = ((SignumComparable)entry3).resolveSignumComparable5((SignumComparable)entry4);
      SignumComparable signumComparable10 = signumComparable8.resolveSignumComparable11(signumComparable9);
      signumComparable7 = signumComparable7.resolveSignumComparable5(signumComparable10);
      int count22 = count20 * 32;
      return signumComparable7.resolveSignumComparable4(count22).resolveSignumComparable5((SignumComparable)entry4);
   }

   private static int[] resolveIndexArrayByIndexArray(int[] countArray6) {
      int count20 = 0;
      int count21 = 0;
      long value2 = 0x42ee82525474fcd3L;
      long value3 = -0x21d4dffacd84ba73L;

      byte byteValue;
      while (true) {
         int count22 = (int)(value2 % 8L);
         if (count22 == 6) {
            byteValue = 1;
            break;
         }

         long value4;
         long value5;
         if (count22 == 0) {
            if (countArray6[count20] == -1) {
               value2 = (value2 ^ 0x602a6a9dda3f6d1eL) + 0x11afe105343e7569L;
               value3 = (value3 ^ 0x73c689aef9ae69acL) - 0x2f20c4958f3d8006L;
               continue;
            }

            value4 = (value2 ^ 0x41141bb3878dc920L) - 0xf3dd0251f142699L;
            value5 = (value3 ^ 0x104d3f0d8ca5a649L) - 0x4e9a5af23c35b3ebL;
         } else {
            guardBlock: {
               long value6;
               long value7;
               if (count22 == 1) {
                  if (countArray6[count21] == 0) {
                     value2 = (value2 ^ -0x2d84715e6300571eL) + 0x6473076e462f6782L;
                     value3 = (value3 ^ 0x4ed9f78726c4a43eL) + 0x56ec8fb47b29dbd1L;
                     continue;
                  }

                  value6 = (value2 ^ -0x77560f5dacbd931dL) - 0x615ede9384135c7dL;
                  value7 = (value3 ^ 0x7f61ac976f27c9d1L) + 0x46a4d6a4330c6ea4L;
               } else {
                  searchBlock:
                  if (count22 != 5) {
                     if (count22 != 7) {
                        if (count22 != 4) {
                           if (count22 != 2) {
                              count20 = 0;
                              value2 = (value2 ^ -0xc331557a5e0b5fbL) - 0x6607b5f45e5b2212L;
                              value3 = (value3 ^ 0x63744cb9dfde9956L) - 0x5074cd5d541f1847L;
                              continue;
                           }
                           break searchBlock;
                        }
                     } else {
                        count20++;
                        value2 = (value2 ^ -0x70af22189222ee47L) + 0x4bd11a067b5df9c6L;
                        value3 = (value3 ^ -0x5680d6ff8f7327e9L) + 0x3ec6fccd6d163e81L;
                     }

                     if (count20 < countArray6.length) {
                        value2 = (value2 ^ -0x31e911993777e85fL) - 0x46c94ed4798a0aa5L;
                        value3 = (value3 ^ 0x160ec2ae7351e58bL) + 0x4ad77b972ac09687L;
                        continue;
                     }

                     value4 = (value2 ^ -0x5a83872f69bee37cL) - 0x7e4d86bccce1fcf9L;
                     value5 = (value3 ^ -0xc0d4aaadfe37f06L) - 0x17bb9e785c9de66aL;
                     break guardBlock;
                  } else {
                     count21++;
                     value2 = (value2 ^ -0x63905c1696a67e32L) + 0x58f920bdb215a59fL;
                     value3 = (value3 ^ -0x6c5087cd94a37086L) - 0x5a09426afd8af987L;
                  }

                  if (count21 < countArray6.length) {
                     value2 = (value2 ^ -0x6b69e96e4fd44a14L) - 0x78084b01c4a65a25L;
                     value3 = (value3 ^ 0x17c9088730acca60L) + 0x243547e664d5087eL;
                     continue;
                  }

                  value6 = (value2 ^ -0x7a6ef252eee1b4eeL) - 0x50c7c1077a647063L;
                  value7 = (value3 ^ 0x1b7889c34055a1a2L) - 0x5158bda22d85d7d1L;
               }

               if (count21 != countArray6.length) {
                  byteValue = 0;
                  break;
               }

               value2 = (value6 ^ -0x604e31b07f5cd325L) + 0x740a7866329c1430L;
               value3 = (value7 ^ 0x46c183c00b837edfL) - 0x5ed952ab90840761L;
               continue;
            }
         }

         count21 = count20;
         value2 = (value4 ^ -0x7956906e3e3583cbL) + 0x2ac0ed9e39e729f0L;
         value3 = (value5 ^ -0x6021afb45c05282eL) + 0x4bb85b04feec5ddbL;
      }

      byte byteValue2 = byteValue;
      int[] countArray7 = new int[countArray6.length - count20 + byteValue2];

      for (int index = count20; index < countArray6.length; index++) {
         countArray7[index - count20 + byteValue2] = ~countArray6[index];
      }

      int count23 = countArray7.length - 1;

      while (++countArray7[count23] == 0) {
         count23--;
      }

      return countArray7;
   }

   public int getCount3() {
      int count20 = 0;
      int[] countArray6 = null;
      int count21 = 0;
      int count22 = 0;
      long value2 = 0x2f3f2ba4274d8450L;
      long value3 = -0x4b1339dab61ce5feL;

      while (true) {
         int count23 = (int)(value2 % 10L);
         if (count23 != 6) {
            if (count23 != 0) {
               searchBlock: {
                  long value4;
                  long value5;
                  boolean flag;
                  if (count23 != 9) {
                     if (count23 == 4) {
                        count21 = countArray6.length;
                        value2 = (value2 ^ -0x5b148de125606361L) - 0x327ac328f59b0948L;
                        value3 = (value3 ^ 0x23dba96e3ab17f20L) - 0x73df349651268f36L;
                        continue;
                     }

                     if (count23 == 7) {
                        if (this.count < 0) {
                           value2 = (value2 ^ -0x779ad9daa7a6e5b5L) + 0x1550f7c4b426bfa4L;
                           value3 = (value3 ^ -0x78270b843023cc1eL) - 0x5d3ce167f5907c95L;
                           continue;
                        }

                        count20 = count22;
                        break searchBlock;
                     }

                     if (count23 == 3) {
                        if (count20 != -1) {
                           break;
                        }

                        value2 = (value2 ^ -0x7e4a39499823ac69L) + 0x1e13d01d25b37e94L;
                        value3 = (value3 ^ -0x53dfc16a1728c9b3L) - 0x4c9b4d30601d5702L;
                        continue;
                     }

                     if (count23 == 1) {
                        if (count21 != 0) {
                           long value6 = (value2 ^ 0x3106a48aad67b813L) + 0x1b7885a6aab9480eL;
                           long value7 = (value3 ^ 0x49b9808d2dfad94bL) + 0x5ad766f8764409cfL;
                           count22 = (count21 - 1 << 5) + numberOfLeadingZeros(this.countArray[0]);
                           value2 = (value6 ^ 0x3677d8b740cd1cb2L) + 0x262abc33a5496a3dL;
                           value3 = (value7 ^ 0x6faa2801902fe6acL) - 0xe126f24a5e413e5L;
                        } else {
                           value2 = (value2 ^ -0x2c0b8b29a9102bddL) - 0x7216c73afb5b0a41L;
                           value3 = (value3 ^ 0x5eda7fbce27f7a8L) + 0x3f32a82ce0e2315cL;
                        }
                        continue;
                     }

                     if (count23 == 5) {
                        count20 = 0;
                        break searchBlock;
                     }

                     if (Integer.bitCount(this.countArray[0]) == 1) {
                        value2 = (value2 ^ 0x74960ae89caf9677L) + 0xfa9895597a9b3d4L;
                        value3 = (value3 ^ 0x1bed1c25bc939aedL) - 0x16e3c9a83eb974acL;
                        continue;
                     }

                     value4 = (value2 ^ -0x364cbeaf86f91384L) - 0x63792e2284efc231L;
                     value5 = (value3 ^ 0x2496b7c0ba973afaL) + 0xc049cb4c341eb61L;
                     flag = false;
                  } else {
                     flag = true;
                     value4 = (value2 ^ -0x5714f21059718d45L) - 0x6815029c5e8a0e85L;
                     value5 = (value3 ^ 0x4e285472baf8148cL) + 0x45d8339189d80b8cL;
                  }

                  boolean flag2 = flag;
                  int count24 = 1;

                  while (count24 < count21 && flag2) {
                     long value8;
                     long value9;
                     if (this.countArray[count24] != 0) {
                        value8 = (value4 ^ 0x2ccbdc6fde51c1a5L) + 0x1bcacbe3d9ce3e5dL;
                        value9 = (value5 ^ 0x52de9794422fe17dL) + 0x51c5776bc221209bL;
                        flag = false;
                     } else {
                        flag = true;
                        value8 = (value4 ^ 0x3b19188018aeec4fL) + 0x2b19088017955bc7L;
                        value9 = (value5 ^ 0x2461a1801ccefabaL) - 0x23e19e80143dcaa6L;
                     }

                     flag2 = flag;
                     count24++;
                     value4 = (value8 ^ 0x792f0efda629f10eL) + 0x6926fe75a2166f06L;
                     value5 = (value9 ^ -0x6338c9da3d99718dL) - 0x6c432a23bda57e6fL;
                  }

                  count20 = !flag2 ? count22 : count22 - 1;
               }

               this.count17 = count20 + 1;
               break;
            } else {
               count20 = this.count17 - 1;
               value2 = (value2 ^ 0x540b65a68b12776L) + 0x4c9a74f13d4de4adL;
               value3 = (value3 ^ 0x2dd0baafdba89b5dL) + 0x3c724c7fb800dc60L;
            }
         } else {
            countArray6 = this.countArray;
            value2 = (value2 ^ 0x38c42a0b509cf35dL) + 0xfe93b8c8bbfeb99L;
            value3 = (value3 ^ -0x511778c2566ab530L) + 0x2f5c3104181d7f11L;
         }
      }

      return count20;
   }

   private static int[] resolveIndexArray(int[] countArray6, int[] countArray7) {
      int[] countArray8 = countArray6;
      int[] countArray9 = countArray7;
      int[] countArray10 = null;
      int count20 = 0;
      int[] countArray11 = null;
      long value2 = 0L;
      int count21 = 0;
      long value3 = 0x409d56038ec69316L;
      long value4 = -0x16850a4f350510cfL;

      long value5;
      long value6;
      boolean flag;
      while (true) {
         int count22 = (int)(value3 % 16L);
         if (count22 == 8) {
            count20 = countArray9.length;
            value3 = (value3 ^ -0x2bc0a2ee905b59bbL) + 0xb6459c00ecb67d3L;
            value4 = (value4 ^ -0x52cb4b2576ab707dL) + 0x413f099464161071L;
         } else if (count22 != 4) {
            long value7;
            long value8;
            if (count22 != 13) {
               if (count22 == 0) {
                  countArray11 = new int[count21];
                  value3 = (value3 ^ -0xb49df29c122f0d3L) + 0x10d43cc5061d3ae8L;
                  value4 = (value4 ^ 0x74100df506e87c94L) + 0x2b143ec9f7348fb5L;
                  continue;
               }

               if (count22 == 6) {
                  if (countArray8.length >= countArray9.length) {
                     value3 = (value3 ^ 0x7eafeacf06128596L) + 0x16c2e0b19913b32aL;
                     value4 = (value4 ^ -0x5e85247c33349f17L) - 0x3b8c844d7563b4fbL;
                  } else {
                     value3 = (value3 ^ -0x7e3d42cc017e78e7L) - 0x5cb2593f1f451753L;
                     value4 = (value4 ^ -0x2eda1652b93dac7aL) - 0x4ad19010e1f2b24cL;
                  }
                  continue;
               }

               if (count22 == 7) {
                  countArray11[count21] = (int)value2;
                  value3 = (value3 ^ 0x474ee4ae5ceb2bd8L) + 0x4465f5885d96b1deL;
                  value4 = (value4 ^ 0x308d841d1edf2700L) + 0x1a9baf253b947b35L;
                  continue;
               }

               if (count22 == 1) {
                  value2 = ((long)countArray8[--count21] & 4294967295L) + ((long)countArray9[--count20] & 4294967295L) + (value2 >>> 32);
                  value3 = (value3 ^ -0x2071dc82c6c21645L) - 0x582b6ecf2e956a58L;
                  value4 = (value4 ^ -0x63e288c98cf30aa5L) + 0x7f52ae23c8c7d8d9L;
                  continue;
               }

               long value9;
               long value10;
               if (count22 != 14) {
                  if (count22 != 11) {
                     if (count22 != 15) {
                        if (count22 != 2) {
                           if (count22 != 3) {
                              if (count22 != 10) {
                                 if (count22 != 5) {
                                    if (count22 != 9) {
                                       countArray10 = countArray8;
                                       value3 = (value3 ^ -0x3587f3a734576c5fL) - 0x66b305eddec06a8aL;
                                       value4 = (value4 ^ -0x2b620b3bf9585e33L) + 0x33a3b30dd61c6429L;
                                    } else {
                                       value2 = ((long)countArray8[--count21] & 4294967295L) + ((long)countArray9[0] & 4294967295L);
                                       value3 = (value3 ^ 0x40919f9f89c4128L) + 0x6b7c1ef30e2727b6L;
                                       value4 = (value4 ^ -0x3a5d2c5646a0cd1eL) - 0x6d47453c78046702L;
                                    }
                                 } else {
                                    value2 = 0L;
                                    value3 = (value3 ^ 0x4c0df95875d869a3L) - 0x33ea1ce5ebbb0f5bL;
                                    value4 = (value4 ^ -0x75f75e28224d28ceL) - 0xba20f384fb55ffL;
                                 }
                              } else {
                                 count21 = countArray8.length;
                                 value3 = (value3 ^ -0x256a94ca029383ebL) - 0x65ed11ad79b36597L;
                                 value4 = (value4 ^ 0x614c02a8c950839cL) - 0x41c01c4044528964L;
                              }
                           } else {
                              countArray8 = countArray9;
                              value3 = (value3 ^ -0x492709d481b36127L) + 0x46c67690de1fd97aL;
                              value4 = (value4 ^ -0x619a6db66aadabd6L) - 0x7fbe315b51031c73L;
                           }
                        } else {
                           countArray11[count21] = (int)value2;
                           value3 = (value3 ^ -0x1911c359b4479b49L) + 0x3339b0a0bbbf6709L;
                           value4 = (value4 ^ -0x6b3a7924b71b0465L) - 0x72ad58f68ab1c66cL;
                        }
                        continue;
                     }

                     flag = true;
                     value5 = (value3 ^ 0x7bb3b2dcdfd2919cL) - 0x349292ccbe316f64L;
                     value6 = (value4 ^ -0x1c23e265848c46ffL) + 0x23cc0b99e4ef86ffL;
                     break;
                  }

                  if (count20 == 1) {
                     value3 = (value3 ^ -0x27d788073eb9ff9fL) + 0x3cdc77266da5b2cfL;
                     value4 = (value4 ^ 0x124ceb4dadd74a52L) + 0x1a13f2210bd07fd3L;
                     continue;
                  }

                  value9 = (value3 ^ 0x368d5eec2438a384L) + 0x4e701013b0d1180eL;
                  value10 = (value4 ^ 0x7497422fc6e7bb1aL) - 0xc9c2ef40be79160L;
               } else {
                  value9 = (value3 ^ 0x49916564c1278c26L) + 0x3a2374b9ae325895L;
                  value10 = (value4 ^ -0x2907c3e73d7e5bf9L) + 0x18311fe9438dcb63L;
               }

               if (count20 > 0) {
                  value3 = (value9 ^ 0x4e3a817a16e00361L) + 0x1daa5411ebb9e895L;
                  value4 = (value10 ^ -0x29c5a06811a642f7L) - 0x476a9ec81a40f9aL;
                  continue;
               }

               value7 = (value9 ^ 0x2956776f57eccba4L) - 0x4a4a8a4cb8728dcbL;
               value8 = (value10 ^ 0x3369a3f8f1f0c433L) - 0x5560f5f8f35ee379L;
            } else {
               value7 = (value3 ^ 0xba519dd9124e426L) - 0x6837affafe3ab04dL;
               value8 = (value4 ^ -0x412552ee6d0c9334L) + 0x30500f1deba0855cL;
            }

            if (value2 >>> 32 == 0L) {
               value5 = (value7 ^ 0x2152015fb6c4ac8eL) - 0x1d212141efc40f1L;
               value6 = (value8 ^ -0x1ac63dab97953067L) - 0x25757262ed6e5a94L;
               flag = false;
               break;
            }

            value3 = (value7 ^ -0x6824c449ef74ccbfL) - 0x784af8eb78c1b8c0L;
            value4 = (value8 ^ -0x6f069ef664ac2f1cL) - 0x38b60f059c674541L;
         } else {
            countArray9 = countArray10;
            value3 = (value3 ^ 0x5f9cbf9c463254edL) + 0x3a9935c03bf0a281L;
            value4 = (value4 ^ 0x20bd9aa081f4409L) - 0x64a4a8043de8a09eL;
         }
      }

      boolean flag2 = flag;

      while (count21 > 0 && flag2) {
         count21--;
         long value11;
         long value12;
         if ((countArray11[count21] = countArray8[count21] + 1) != 0) {
            value11 = (value5 ^ 0x418870743925aa81L) - 0x3e7850741924a67fL;
            value12 = (value6 ^ 0x2f860b76e20e23bbL) - 0x187a0a91a20de39bL;
            flag = false;
         } else {
            flag = true;
            value11 = (value5 ^ -0x43ae81858ff4d5eeL) - 0x74314e6a4ffb25eeL;
            value12 = (value6 ^ -0x60edb5cd66be1c52L) + 0x7711b63286c1dc5aL;
         }

         flag2 = flag;
         value5 = (value11 ^ -0x7e32ce9964961788L) - 0x7eb511667ad86808L;
         value6 = (value12 ^ -0x592199f6972d0513L) + 0x5ecda206d84ec59bL;
      }

      while (count21 > 0) {
         count21--;
         countArray11[count21] = countArray8[count21];
      }

      if (!flag2) {
         return countArray11;
      } else {
         int[] countArray12 = new int[countArray11.length + 1];
         System.arraycopy(countArray11, 0, countArray12, 1, countArray11.length);
         countArray12[0] = 1;
         return countArray12;
      }
   }

   private static int[] resolveIndexArray(long value2, int[] countArray6) {
      int count20 = 0;
      int[] entry = null;
      long value3 = 0L;
      count20 = (int)(value2 >>> 32);
      if (count20 != 0) {
         entry = new int[2];
         if (countArray6.length != 1) {
            long value4 = ((long)((int)value2) & 4294967295L) - ((long)countArray6[1] & 4294967295L);
            entry[1] = (int)value4;
            long value5 = ((long)count20 & 4294967295L) - ((long)countArray6[0] & 4294967295L) + (value4 >> 32);
            entry[0] = (int)value5;
            return (int[])entry;
         } else {
            value3 = ((long)((int)value2) & 4294967295L) - ((long)countArray6[0] & 4294967295L);
            entry[1] = (int)value3;
            if (value3 >> 32 == 0L) {
               entry[0] = count20;
            }

            return (int[])entry;
         }
      } else {
         return new int[1];
      }
   }

   private static SignumComparable resolveSignumComparable(SignumComparable signumComparable7, SignumComparable signumComparable8) {
      short shortValue = 19587;
      short shortValue2 = 8715;
      char character = '\ud845';
      int count20 = 0;
      int count21 = 0;
      int count22 = 0;
      int count23 = 0;
      int count24 = 0;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      Object entry5 = null;
      Object entry6 = null;
      Object entry7 = null;
      Object entry8 = null;
      Object entry9 = null;
      Object entry10 = null;
      Object entry11 = null;
      Object entry12 = null;
      Object entry13 = null;
      Object entry14 = null;
      Object entry15 = null;
      Object entry16 = null;
      int count25 = 0;
      Object entry17 = null;
      count20 = signumComparable7.countArray.length;
      count21 = signumComparable8.countArray.length;
      count22 = Math.max(count20, count21);
      count23 = (count22 + 2) / 3;
      count24 = count22 - 2 * count23;
      int byteValue = 0;
      entry3 = signumComparable7.resolveSignumComparable(count23, count24, byteValue, count22);
      byte byteValue2 = 1;
      entry2 = signumComparable7.resolveSignumComparable(count23, count24, byteValue2, count22);
      int byteValue3 = 2;
      entry = signumComparable7.resolveSignumComparable(count23, count24, byteValue3, count22);
      byte byteValue4 = 0;
      entry6 = signumComparable8.resolveSignumComparable(count23, count24, byteValue4, count22);
      byte byteValue5 = 1;
      entry5 = signumComparable8.resolveSignumComparable(count23, count24, byteValue5, count22);
      byte byteValue6 = 2;
      entry4 = signumComparable8.resolveSignumComparable(count23, count24, byteValue6, count22);
      boolean flag = true;
      entry7 = ((SignumComparable)entry).resolveSignumComparable((SignumComparable)entry4, flag);
      entry15 = ((SignumComparable)entry3).resolveSignumComparable5((SignumComparable)entry);
      entry16 = ((SignumComparable)entry6).resolveSignumComparable5((SignumComparable)entry4);
      SignumComparable signumComparable9 = ((SignumComparable)entry15).resolveSignumComparable11((SignumComparable)entry2);
      SignumComparable signumComparable10 = ((SignumComparable)entry16).resolveSignumComparable11((SignumComparable)entry5);
      boolean flag2 = true;
      SignumComparable signumComparable11 = signumComparable10;
      entry10 = signumComparable9.resolveSignumComparable(signumComparable11, flag2);
      entry15 = ((SignumComparable)entry15).resolveSignumComparable5((SignumComparable)entry2);
      entry16 = ((SignumComparable)entry16).resolveSignumComparable5((SignumComparable)entry5);
      boolean flag3 = true;
      entry8 = ((SignumComparable)entry15).resolveSignumComparable((SignumComparable)entry16, flag3);
      signumComparable9 = ((SignumComparable)entry15).resolveSignumComparable5((SignumComparable)entry3);
      byte byteValue7 = 1;
      signumComparable9 = signumComparable9.resolveSignumComparable4(byteValue7).resolveSignumComparable11((SignumComparable)entry);
      SignumComparable signumComparable12 = ((SignumComparable)entry16).resolveSignumComparable5((SignumComparable)entry6);
      byte byteValue8 = 1;
      signumComparable10 = signumComparable12.resolveSignumComparable4(byteValue8).resolveSignumComparable11((SignumComparable)entry4);
      boolean flag4 = true;
      SignumComparable signumComparable13 = signumComparable10;
      entry9 = signumComparable9.resolveSignumComparable(signumComparable13, flag4);
      boolean flag5 = true;
      entry11 = ((SignumComparable)entry3).resolveSignumComparable((SignumComparable)entry6, flag5);
      entry13 = ((SignumComparable)entry9).resolveSignumComparable11((SignumComparable)entry10).getSignumComparableAsSignumComparable();
      signumComparable9 = ((SignumComparable)entry8).resolveSignumComparable11((SignumComparable)entry10);
      byte byteValue9 = 1;
      entry14 = signumComparable9.resolveSignumComparable6(byteValue9);
      entry12 = ((SignumComparable)entry8).resolveSignumComparable11((SignumComparable)entry7);
      signumComparable9 = ((SignumComparable)entry13).resolveSignumComparable11((SignumComparable)entry12);
      byte byteValue10 = 1;
      entry13 = signumComparable9.resolveSignumComparable6(byteValue10);
      entry12 = ((SignumComparable)entry12).resolveSignumComparable11((SignumComparable)entry14).resolveSignumComparable11((SignumComparable)entry11);
      byte byteValue11 = 1;
      SignumComparable signumComparable14 = ((SignumComparable)entry11).resolveSignumComparable4(byteValue11);
      entry13 = ((SignumComparable)entry13).resolveSignumComparable11(signumComparable14);
      entry14 = ((SignumComparable)entry14).resolveSignumComparable11((SignumComparable)entry13);
      count25 = count23 * 32;
      entry17 = ((SignumComparable)entry11)
         .resolveSignumComparable4(count25)
         .resolveSignumComparable5((SignumComparable)entry13)
         .resolveSignumComparable4(count25)
         .resolveSignumComparable5((SignumComparable)entry12)
         .resolveSignumComparable4(count25)
         .resolveSignumComparable5((SignumComparable)entry14)
         .resolveSignumComparable4(count25)
         .resolveSignumComparable5((SignumComparable)entry7);
      return (SignumComparable)(signumComparable7.count == signumComparable8.count ? entry17 : ((SignumComparable)entry17).getSignumComparable2());
   }

   private static int[] resolveIndexArrayByIndexArray(int[] countArray6, long value2) {
      int count20 = 0;
      int count21 = 0;
      int[] countArray7 = null;
      long value3 = 0L;
      long value4 = 0x7d6a69f3ad98e53L;
      long value5 = -0x4a267465f6fc1c97L;

      long value6;
      long value7;
      boolean flag;
      while (true) {
         int count22 = (int)(value4 % 13L);
         if (count22 == 12) {
            count20 = (int)(value2 >>> 32);
            value4 = (value4 ^ -0x25499e467f91d6a8L) - 0x6fa357a6e987ae18L;
            value5 = (value5 ^ -0x11cc8ef09d72ca88L) - 0x7916ac71fe89f826L;
         } else if (count22 == 11) {
            count21 = countArray6.length;
            value4 = (value4 ^ 0x5dcb5e0aad2ff378L) - 0x32aa9048eb8bb61L;
            value5 = (value5 ^ 0x27daa572f9d805c0L) - 0x157689797773cac9L;
         } else if (count22 == 9) {
            value3 = 0L;
            value4 = (value4 ^ -0x26549b9fadf4c0fcL) + 0x491bb212bbc9e89eL;
            value5 = (value5 ^ 0x3ab6fd2d16662dc9L) - 0xabd005a6d0d8e44L;
         } else if (count22 != 10) {
            if (count22 == 7) {
               countArray7[count21] = (int)value3;
               value4 = (value4 ^ 0x2617f61f23d5a432L) - 0x69cd46abfdd794dbL;
               value5 = (value5 ^ -0x114a10192bb16bcdL) + 0x55002453a31474cbL;
            } else if (count22 != 1) {
               if (count22 != 3) {
                  if (count22 == 4) {
                     count21--;
                     value3 = ((long)countArray6[count21] & 4294967295L) - value2;
                     value4 = (value4 ^ -0xfdeb846f46c4b58L) + 0x7b030562e88199baL;
                     value5 = (value5 ^ 0x490a870878619116L) - 0x7eed78f177b9f7cfL;
                     continue;
                  }

                  if (count22 == 2) {
                     flag = true;
                     value6 = (value4 ^ -0x4ace0c9d34d33eebL) - 0x6b0f129e3713410bL;
                     value7 = (value5 ^ -0x245ea9de1cf9b003L) - 0x375eae20dcf9cd2dL;
                     break;
                  }

                  if (count22 == 0) {
                     count21--;
                     value3 = ((long)countArray6[count21] & 4294967295L) - ((long)count20 & 4294967295L) + (value3 >> 32);
                     value4 = (value4 ^ 0x8a01353e2798c36L) - 0x8341873bdd7a8a8L;
                     value5 = (value5 ^ -0x25c3ac15544d9994L) + 0x31c0ef94c71ce0f7L;
                     continue;
                  }

                  if (count22 != 5) {
                     if (count22 != 6) {
                        countArray7[count21] = (int)value3;
                        value4 = (value4 ^ -0x64d065dfd55b33d9L) - 0x5a7d6419ad35d58L;
                        value5 = (value5 ^ 0x32a26fffd64cb4d9L) + 0x2e6c4264b96aafbeL;
                     } else {
                        countArray7[count21] = (int)value3;
                        value4 = (value4 ^ 0x57824182488656d6L) + 0x109572752390d2ccL;
                        value5 = (value5 ^ 0x60e0f72c9192418cL) + 0x754ad1a2425bfa8bL;
                     }
                     continue;
                  }

                  value4 = (value4 ^ 0x6d74d52b47ca91bcL) - 0x8905fffbca10fc7L;
                  value5 = (value5 ^ -0x626480e8a14b943aL) - 0x58ba83efc0d32628L;
               }

               if (value3 >> 32 == 0L) {
                  value6 = (value4 ^ 0xecffa8bd8f18a2fL) + 0x695f2075194a7d25L;
                  value7 = (value5 ^ -0x215f8b7033f889cfL) - 0x616a388bab2065f3L;
                  flag = false;
                  break;
               }

               value4 = (value4 ^ 0x6e054ae9b0e57e61L) + 0xa2490d7313e5157L;
               value5 = (value5 ^ -0x5ad0277bfaa7c5cfL) - 0x75f98c9763e129f3L;
            } else {
               countArray7 = new int[count21];
               value4 = (value4 ^ -0x3fa341fd30a13516L) + 0x478b66a340aef9c6L;
               value5 = (value5 ^ 0x24bbc128595a0833L) - 0x70c43ad74d8ed2cfL;
            }
         } else if (count20 != 0) {
            long value8 = (value4 ^ 0x79209f46be114afL) + 0x4cf93562966fe3fbL;
            long value9 = (value5 ^ 0x1abda6e7e434df2L) + 0x44da61a7db97df5dL;
            count21--;
            value3 = ((long)countArray6[count21] & 4294967295L) - (value2 & 4294967295L);
            value4 = (value8 ^ -0x298b8e4737df57b6L) + 0x6c1352176301a1dcL;
            value5 = (value9 ^ -0x423a91681092540bL) + 0x1bb84371397fcf39L;
         } else {
            value4 = (value4 ^ -0x61555ba423f6c28aL) - 0x5439a66e8e69e8b3L;
            value5 = (value5 ^ 0x6b0c8a3763985c32L) + 0x5a2e1f05161553d5L;
         }
      }

      boolean flag2 = flag;

      while (count21 > 0 && flag2) {
         count21--;
         long value10;
         long value11;
         if ((countArray7[count21] = countArray6[count21] - 1) != -1) {
            value10 = (value6 ^ -0x7534b163b3c177a3L) - 0x7634bd8bb42177d3L;
            value11 = (value7 ^ -0x5c40c8c0694e6a17L) + 0x60ab32d175506ce7L;
            flag = false;
         } else {
            flag = true;
            value10 = (value6 ^ -0x42dc180f41977c76L) - 0x631d263081d7838aL;
            value11 = (value7 ^ -0x3f6794e2b51dd61aL) - 0x4077a90d365de722L;
         }

         flag2 = flag;
         value6 = (value10 ^ -0x14614ab558d1d83fL) - 0x156a54b69b11df3fL;
         value7 = (value11 ^ -0x1bfa87c17785e4b3L) - 0x5bfa982d77e618bdL;
      }

      while (count21 > 0) {
         count21--;
         countArray7[count21] = countArray6[count21];
      }

      return countArray7;
   }

   public SignumComparable resolveSignumComparable3(int count20) {
      int count21 = 0;
      int[] countArray6 = null;
      int count22 = 0;
      long value2 = 0x2af3f4fb48957b4bL;
      long value3 = -0x6684cd33ce9181d1L;

      while (true) {
         int count23 = (int)(value2 % 9L);
         if (count23 == 7) {
            throw new ArithmeticException("Negative bit address");
         }

         if (count23 == 3) {
            return resolveSignumComparable(countArray6);
         }

         if (count23 != 5) {
            if (count23 != 8) {
               if (count23 != 1) {
                  if (count23 != 0) {
                     if (count23 != 6) {
                        if (count23 != 4) {
                           count21 = count20 >>> 5;
                           value2 = (value2 ^ -0x744156f6b7ee5850L) + 0xad75c6e7de23f64L;
                           value3 = (value3 ^ -0x14a4cbb24e280068L) + 0x9ec3ea5c78d33a9L;
                           continue;
                        }

                        count22++;
                        value2 = (value2 ^ -0x7f5a3ecc45a2ba81L) - 0x4873a82d17bfc83eL;
                        value3 = (value3 ^ -0x43340a7a691b4da0L) + 0x5be2d240c88c5d44L;
                     }

                     if (count22 >= countArray6.length) {
                        long value4 = (value2 ^ -0x5e74269e25b54932L) + 0x689642788752bb73L;
                        long value5 = (value3 ^ 0x40e3e48dfb5b2cedL) + 0x700ab7cc7413f3b9L;
                        countArray6[countArray6.length - count21 - 1] = countArray6[countArray6.length - count21 - 1] | 1 << (count20 & 31);
                        value2 = (value4 ^ 0x63c0ed5337553c5bL) - 0x2bc499030bec0d0L;
                        value3 = (value5 ^ 0x24cf4aef755f78f6L) - 0x2c5c6c93784ad43L;
                     } else {
                        value2 = (value2 ^ -0x167c00ae8fd05859L) - 0x31e3360c94b5c49fL;
                        value3 = (value3 ^ 0x406518782354fa6fL) + 0x1e834c80fbdfe385L;
                     }
                  } else {
                     countArray6 = new int[Math.max(this.getCountAsIndex(), count21 + 2)];
                     value2 = (value2 ^ 0x15d61942a50d835L) - 0x2602fb9495eeb6fL;
                     value3 = (value3 ^ 0x1c38cdf649b8ce2bL) - 0x51d0e591fd4708a8L;
                  }
               } else {
                  countArray6[countArray6.length - count22 - 1] = this.computeCountByIndex(count22);
                  value2 = (value2 ^ -0x6e30cf3b300a8584L) + 0x423282b9700e14bdL;
                  value3 = (value3 ^ 0x398c40a7027f9a31L) + 0x3b86a61bb2844ca3L;
               }
            } else if (count20 >= 0) {
               value2 = (value2 ^ 0x5c25a9a6c44fc601L) + 0x57aa8f962681a32L;
               value3 = (value3 ^ -0x1826af0d153fab13L) - 0x7f1e726415817d46L;
            } else {
               value2 = (value2 ^ -0x7666638ed35ff8faL) - 0x7d0658a8400c7a3cL;
               value3 = (value3 ^ 0x31d11d8e3cce0ccL) - 0x6afbed242f158cedL;
            }
         } else {
            count22 = 0;
            value2 = (value2 ^ -0x729d842dc35a3392L) - 0x1b2fe7787efb3eaaL;
            value3 = (value3 ^ -0x31647741ddc87a57L) + 0x759b9e14f8758edeL;
         }
      }
   }

   private SignumComparable resolveSignumComparable10(SignumComparable signumComparable7) {
      return this.resolveSignumComparableArray2(signumComparable7)[1];
   }

   public SignumComparable resolveSignumComparable5(SignumComparable signumComparable7) {
      short shortValue = 19294;
      short shortValue2 = 596;
      char character = '\ucc14';
      int count20 = 0;
      if (signumComparable7.count != 0) {
         if (this.count != 0) {
            if (signumComparable7.count != this.count) {
               count20 = this.computeCount(signumComparable7);
               if (count20 != 0) {
                  int[] countArray6;
                  if (count20 <= 0) {
                     int[] countArray7 = this.countArray;
                     countArray6 = resolveIndexArrayByIndexArray(signumComparable7.countArray, countArray7);
                  } else {
                     int[] countArray8 = signumComparable7.countArray;
                     countArray6 = resolveIndexArrayByIndexArray(this.countArray, countArray8);
                  }

                  int[] countArray9 = countArray6;
                  int[] countArray10 = resolveIndexArray3(countArray9);
                  return new SignumComparable(countArray10, count20 != this.count ? -1 : 1);
               } else {
                  return signumComparable5;
               }
            } else {
               int[] countArray11 = signumComparable7.countArray;
               return new SignumComparable(resolveIndexArray(this.countArray, countArray11), this.count);
            }
         } else {
            return signumComparable7;
         }
      } else {
         return this;
      }
   }

   public SignumComparable resolveSignumComparable11(SignumComparable signumComparable7) {
      int byteValue = 41;
      int count20 = 0;
      if (signumComparable7.count != 0) {
         if (this.count != 0) {
            if (signumComparable7.count == this.count) {
               count20 = this.computeCount(signumComparable7);
               if (count20 != 0) {
                  int[] countArray6;
                  if (count20 <= 0) {
                     int[] countArray7 = this.countArray;
                     countArray6 = resolveIndexArrayByIndexArray(signumComparable7.countArray, countArray7);
                  } else {
                     int[] countArray8 = signumComparable7.countArray;
                     countArray6 = resolveIndexArrayByIndexArray(this.countArray, countArray8);
                  }

                  int[] countArray9 = countArray6;
                  int[] countArray10 = resolveIndexArray3(countArray9);
                  return new SignumComparable(countArray10, count20 != this.count ? -1 : 1);
               } else {
                  return signumComparable5;
               }
            } else {
               int[] countArray11 = signumComparable7.countArray;
               return new SignumComparable(resolveIndexArray(this.countArray, countArray11), this.count);
            }
         } else {
            return signumComparable7.getSignumComparable2();
         }
      } else {
         return this;
      }
   }

   public SignumComparable resolveSignumComparable12(SignumComparable signumComparable7) {
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      if (signumComparable7.count == 1) {
         SignumComparable signumComparable8 = signumComparable;
         if (signumComparable7.equals(signumComparable8)) {
            return signumComparable5;
         } else {
            entry = this;
            if (this.count < 0 || this.computeCount(signumComparable7) >= 0) {
               entry = this.resolveSignumComparable2(signumComparable7);
            }

            SignumComparable signumComparable9 = signumComparable;
            if (!((SignumComparable)entry).equals(signumComparable9)) {
               entry2 = new CurveAccumulator((SignumComparable)entry);
               entry3 = new CurveAccumulator(signumComparable7);
               entry4 = ((CurveAccumulator)entry2).resolveCurveAccumulator3((CurveAccumulator)entry3);
               int byteValue = 1;
               return ((CurveAccumulator)entry4).resolveSignumComparableByIndex(byteValue);
            } else {
               return signumComparable;
            }
         }
      } else {
         throw new ArithmeticException("BigInteger: modulus not positive");
      }
   }

   public SignumComparable resolveSignumComparable7(SignumComparable signumComparable7) {
      return signumComparable7.countArray.length >= 80 && this.countArray.length - signumComparable7.countArray.length >= 40
         ? this.resolveSignumComparable10(signumComparable7)
         : this.resolveSignumComparable8(signumComparable7);
   }

   private SignumComparable resolveSignumComparable8(SignumComparable signumComparable7) {
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      entry = new CurveAccumulator();
      entry2 = new CurveAccumulator(this.countArray);
      entry3 = new CurveAccumulator(signumComparable7.countArray);
      CurveAccumulator curveAccumulator = ((CurveAccumulator)entry2).resolveCurveAccumulator((CurveAccumulator)entry3, (CurveAccumulator)entry);
      int count20 = this.count;
      return curveAccumulator.resolveSignumComparableByIndex(count20);
   }

   public SignumComparable resolveSignumComparable6(SignumComparable signumComparable7) {
      int[] entry = null;
      entry = new int[Math.max(this.getCountAsIndex(), signumComparable7.getCountAsIndex())];

      for (int index = 0; index < entry.length; index++) {
         int count20 = entry.length - index - 1;
         int count21 = this.computeCountByIndex(count20);
         int count22 = entry.length - index - 1;
         entry[index] = count21 & ~signumComparable7.computeCountByIndex(count22);
      }

      return resolveSignumComparable((int[])entry);
   }

   public SignumComparable resolveSignumComparable9(SignumComparable signumComparable7) {
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      if (signumComparable7.count != 0) {
         if (this.count != 0) {
            entry = new CurveAccumulator(this);
            entry2 = new CurveAccumulator(signumComparable7);
            entry3 = ((CurveAccumulator)entry).resolveCurveAccumulator2((CurveAccumulator)entry2);
            int byteValue = 1;
            return ((CurveAccumulator)entry3).resolveSignumComparableByIndex(byteValue);
         } else {
            return signumComparable7.getSignumComparable5();
         }
      } else {
         return this.getSignumComparable5();
      }
   }

   private SignumComparable(long value2) {
      if (value2 < 0L) {
         value2 = -value2;
         this.count = -1;
      } else {
         this.count = 1;
      }

      int count20 = (int)(value2 >>> 32);
      if (count20 == 0) {
         this.countArray = new int[1];
         this.countArray[0] = (int)value2;
      } else {
         this.countArray = new int[2];
         this.countArray[0] = count20;
         this.countArray[1] = (int)value2;
      }
   }

   public SignumComparable(int[] countArray6, int count20) {
      this.count = countArray6.length == 0 ? 0 : count20;
      this.countArray = countArray6;
      if (this.countArray.length >= 67108864) {
         this.updateState2();
      }
   }

   private SignumComparable(int count20, int[] countArray6) {
      this.countArray = resolveIndexArray2(countArray6);
      if (count20 >= -1 && count20 <= 1) {
         if (this.countArray.length == 0) {
            this.count = 0;
         } else {
            if (count20 == 0) {
               throw new NumberFormatException("signum-magnitude mismatch");
            }

            this.count = count20;
         }

         if (this.countArray.length >= 67108864) {
            this.updateState2();
         }
      } else {
         throw new NumberFormatException("Invalid signum value");
      }
   }

   private SignumComparable(byte[] byteValueArray, int count20) {
      this.count = byteValueArray.length == 0 ? 0 : count20;
      this.countArray = resolveIndexArray(byteValueArray);
      if (this.countArray.length >= 67108864) {
         this.updateState2();
      }
   }

   public SignumComparable(byte[] byteValueArray) {
      if (byteValueArray.length == 0) {
         throw new NumberFormatException("Zero length BigInteger");
      } else {
         if (byteValueArray[0] < 0) {
            this.countArray = resolveIndexArrayByByteArray(byteValueArray);
            this.count = -1;
         } else {
            this.countArray = resolveIndexArray(byteValueArray);
            this.count = this.countArray.length == 0 ? 0 : 1;
         }

         if (this.countArray.length >= 67108864) {
            this.updateState2();
         }
      }
   }

   public SignumComparable(int count20, Random random) {
      this(1, resolveByteArray(count20, random));
   }

   private SignumComparable(int[] countArray6) {
      if (countArray6.length == 0) {
         throw new NumberFormatException("Zero length BigInteger");
      } else {
         if (countArray6[0] < 0) {
            this.countArray = resolveIndexArrayByIndexArray(countArray6);
            this.count = -1;
         } else {
            this.countArray = resolveIndexArray3(countArray6);
            this.count = this.countArray.length == 0 ? 0 : 1;
         }

         if (this.countArray.length >= 67108864) {
            this.updateState2();
         }
      }
   }

   public SignumComparable(char[] characterArray, int count20, int count21) {
      int count22 = 0;

      while (count22 < count21 && Character.digit(characterArray[count22], 10) == 0) {
         count22++;
      }

      if (count22 == count21) {
         this.count = 0;
         this.countArray = signumComparable5.countArray;
      } else {
         int count23 = count21 - count22;
         this.count = count20;
         int count24;
         if (count21 < 10) {
            count24 = 1;
         } else {
            long value2 = ((long)count23 * timestampArray[10] >>> 10) + 1L;
            if (value2 + 31L >= 4294967296L) {
               updateState();
            }

            count24 = (int)(value2 + 31L) >>> 5;
         }

         int[] countArray6 = new int[count24];
         int count25 = count23 % countArray2[10];
         if (count25 == 0) {
            count25 = countArray2[10];
         }

         int count26 = count24 - 1;
         int count27;
         int count28 = count27 = count22 + count25;
         countArray6[count26] = this.computeCount(characterArray, count22, count28);

         while (count27 < count21) {
            int count29 = count27;
            int count30 = count27 += countArray2[10];
            int count31 = count29;
            int count32 = this.computeCount(characterArray, count31, count30);
            int count33 = countArray4[10];
            applyIndexArray2(countArray6, count33, count32);
         }

         this.countArray = resolveIndexArray3(countArray6);
         if (this.countArray.length >= 67108864) {
            this.updateState2();
         }
      }
   }

   public SignumComparable(String text, int count20) {
      int count21 = 0;
      int length = text.length();
      if (count20 < 2 || count20 > 36) {
         throw new NumberFormatException("Radix out of range");
      } else if (length == 0) {
         throw new NumberFormatException("Zero length BigInteger");
      } else {
         int byteValue = 1;
         int count22 = text.lastIndexOf(45);
         int count23 = text.lastIndexOf(43);
         if (count22 >= 0) {
            if (count22 != 0 || count23 >= 0) {
               throw new NumberFormatException("Illegal embedded sign character");
            }

            byteValue = -1;
            count21 = 1;
         } else if (count23 >= 0) {
            if (count23 != 0) {
               throw new NumberFormatException("Illegal embedded sign character");
            }

            count21 = 1;
         }

         if (count21 == length) {
            throw new NumberFormatException("Zero length BigInteger");
         } else {
            while (count21 < length && Character.digit(text.charAt(count21), count20) == 0) {
               count21++;
            }

            if (count21 == length) {
               this.count = 0;
               this.countArray = signumComparable5.countArray;
            } else {
               int count24 = length - count21;
               this.count = byteValue;
               long value2 = ((long)count24 * timestampArray[count20] >>> 10) + 1L;
               if (value2 + 31L >= 4294967296L) {
                  updateState();
               }

               int count25 = (int)(value2 + 31L) >>> 5;
               int[] countArray6 = new int[count25];
               int count26 = count24 % countArray2[count20];
               if (count26 == 0) {
                  count26 = countArray2[count20];
               }

               int count27;
               String text2 = text.substring(count21, count27 = count21 + count26);
               countArray6[count25 - 1] = Integer.parseInt(text2, count20);
               if (countArray6[count25 - 1] < 0) {
                  throw new NumberFormatException("Illegal digit");
               } else {
                  int count28 = countArray4[count20];

                  while (count27 < length) {
                     int count29 = Integer.parseInt(text.substring(count27, count27 += countArray2[count20]), count20);
                     if (count29 < 0) {
                        throw new NumberFormatException("Illegal digit");
                     }

                     applyIndexArray2(countArray6, count28, count29);
                  }

                  this.countArray = resolveIndexArray3(countArray6);
                  if (this.countArray.length >= 67108864) {
                     this.updateState2();
                  }
               }
            }
         }
      }
   }

   public SignumComparable(int count20, int count21, Random random) {
      if (count20 < 2) {
         throw new ArithmeticException("bitLength < 2");
      } else {
         SignumComparable signumComparable7 = count20 < 95 ? resolveSignumComparableByRandom(count20, count21, random) : resolveSignumComparable(count20, count21, random);
         this.count = 1;
         this.countArray = signumComparable7.countArray;
      }
   }

   public SignumComparable(String text) {
      this(text, 10);
   }

   public SignumComparable(int count20, byte[] byteValueArray) {
      this.countArray = resolveIndexArray(byteValueArray);
      if (count20 >= -1 && count20 <= 1) {
         if (this.countArray.length == 0) {
            this.count = 0;
         } else {
            if (count20 == 0) {
               throw new NumberFormatException("signum-magnitude mismatch");
            }

            this.count = count20;
         }

         if (this.countArray.length >= 67108864) {
            this.updateState2();
         }
      } else {
         throw new NumberFormatException("Invalid signum value");
      }
   }

   static {
      for (int index = 1; index <= 16; index++) {
         int[] countArray6 = new int[]{index};
         signumComparableArray4[index] = new SignumComparable(countArray6, 1);
         signumComparableArray2[index] = new SignumComparable(countArray6, -1);
      }

      signumComparableArray = new SignumComparable[37][];
      valueArray = new double[37];

      for (int index2 = 2; index2 <= 36; index2++) {
         signumComparableArray[index2] = new SignumComparable[]{resolveSignumComparableByTime((long)index2)};
         valueArray[index2] = Math.log((double)index2);
      }

      signumComparable5 = new SignumComparable(new int[0], 0);
      signumComparable = resolveSignumComparableByTime(1L);
      signumComparable4 = resolveSignumComparableByTime(2L);
      signumComparable6 = resolveSignumComparableByTime(-1L);
      signumComparable3 = resolveSignumComparableByTime(10L);
      countArray5 = new int[]{7, 25, 81, 241, 673, 1793, Integer.MAX_VALUE};
      stringArray = new String[64];
      stringArray[63] = "000000000000000000000000000000000000000000000000000000000000000";

      for (int index3 = 0; index3 < 63; index3++) {
         stringArray[index3] = stringArray[63].substring(0, index3);
      }

      countArray3 = new int[]{
         0, 0, 62, 39, 31, 27, 24, 22, 20, 19, 18, 18, 17, 17, 16, 16, 15, 15, 15, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 12, 12, 12, 12, 12, 12, 12, 12
      };
      signumComparableArray3 = new SignumComparable[]{
         null,
         null,
         resolveSignumComparableByTime(0x4000000000000000L),
         resolveSignumComparableByTime(0x383d9170b85ff80bL),
         resolveSignumComparableByTime(0x4000000000000000L),
         resolveSignumComparableByTime(0x6765c793fa10079dL),
         resolveSignumComparableByTime(0x41c21cb8e1000000L),
         resolveSignumComparableByTime(0x3642798750226111L),
         resolveSignumComparableByTime(0x1000000000000000L),
         resolveSignumComparableByTime(0x12bf307ae81ffd59L),
         resolveSignumComparableByTime(0xde0b6b3a7640000L),
         resolveSignumComparableByTime(0x4d28cb56c33fa539L),
         resolveSignumComparableByTime(0x1eca170c00000000L),
         resolveSignumComparableByTime(0x780c7372621bd74dL),
         resolveSignumComparableByTime(0x1e39a5057d810000L),
         resolveSignumComparableByTime(0x5b27ac993df97701L),
         resolveSignumComparableByTime(0x1000000000000000L),
         resolveSignumComparableByTime(0x27b95e997e21d9f1L),
         resolveSignumComparableByTime(0x5da0e1e53c5c8000L),
         resolveSignumComparableByTime(0xb16a458ef403f19L),
         resolveSignumComparableByTime(0x16bcc41e90000000L),
         resolveSignumComparableByTime(0x2d04b7fdd9c0ef49L),
         resolveSignumComparableByTime(0x5658597bcaa24000L),
         resolveSignumComparableByTime(0x6feb266931a75b7L),
         resolveSignumComparableByTime(0xc29e98000000000L),
         resolveSignumComparableByTime(0x14adf4b7320334b9L),
         resolveSignumComparableByTime(0x226ed36478bfa000L),
         resolveSignumComparableByTime(0x383d9170b85ff80bL),
         resolveSignumComparableByTime(0x5a3c23e39c000000L),
         resolveSignumComparableByTime(0x4e900abb53e6b71L),
         resolveSignumComparableByTime(0x7600ec618141000L),
         resolveSignumComparableByTime(0xaee5720ee830681L),
         resolveSignumComparableByTime(0x1000000000000000L),
         resolveSignumComparableByTime(0x172588ad4f5f0981L),
         resolveSignumComparableByTime(0x211e44f7d02c1000L),
         resolveSignumComparableByTime(0x2ee56725f06e5c71L),
         resolveSignumComparableByTime(0x41c21cb8e1000000L)
      };
      countArray2 = new int[]{0, 0, 30, 19, 15, 13, 11, 11, 10, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5};
      countArray4 = new int[]{
         0,
         0,
         1073741824,
         1162261467,
         1073741824,
         1220703125,
         362797056,
         1977326743,
         1073741824,
         387420489,
         1000000000,
         214358881,
         429981696,
         815730721,
         1475789056,
         170859375,
         268435456,
         410338673,
         612220032,
         893871739,
         1280000000,
         1801088541,
         113379904,
         148035889,
         191102976,
         244140625,
         308915776,
         387420489,
         481890304,
         594823321,
         729000000,
         887503681,
         1073741824,
         1291467969,
         1544804416,
         1838265625,
         60466176
      };
      serialPersistentFields = new ObjectStreamField[]{
         new ObjectStreamField("signum", int.class),
         new ObjectStreamField("magnitude", byte[].class),
         new ObjectStreamField("bitCount", int.class),
         new ObjectStreamField("bitLength", int.class),
         new ObjectStreamField("firstNonzeroByteNum", int.class),
         new ObjectStreamField("lowestSetBit", int.class)
      };
   }

   @Override
   public boolean equals(Object other) {
      SignumComparable signumComparable7 = null;
      int[] entry = null;
      int count20 = 0;
      int[] entry2 = null;
      if (other == this) {
         return true;
      } else if (!(other instanceof SignumComparable)) {
         return false;
      } else {
         signumComparable7 = (SignumComparable)other;
         if (signumComparable7.count != this.count) {
            return false;
         } else {
            entry = this.countArray;
            count20 = entry.length;
            entry2 = signumComparable7.countArray;
            if (count20 != entry2.length) {
               return false;
            } else {
               for (int index = 0; index < count20; index++) {
                  if (entry2[index] != entry[index]) {
                     return false;
                  }
               }

               return true;
            }
         }
      }
   }

   @Override
   public String toString() {
      return this.resolveTextByIndex(10);
   }

   @Override
   public int hashCode() {
      int count20 = 0;
      count20 = 0;

      for (int index = 0; index < this.countArray.length; index++) {
         count20 = (int)((long)(31 * count20) + ((long)this.countArray[index] & 4294967295L));
      }

      return count20 * this.count;
   }

   @Override
   public int compareTo(Object entry) {
      SignumComparable signumComparable7 = (SignumComparable)entry;
      return this.computeCountBySignumComparable(signumComparable7);
   }
}
