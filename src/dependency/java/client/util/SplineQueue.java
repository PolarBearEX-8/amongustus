package client.util;

public class SplineQueue {
   public static byte[] resolveByteArray(byte[] byteValueArray) {
      int count = 0;
      int count2 = 0;
      byte[] byteValueArray2 = null;
      long value = 0xb951fca1daae4e6L;
      long value2 = -0x6811ea934b7a8debL;

      while (true) {
         long value3;
         long value4;
         while (true) {
            int count3 = (int)(value % 15L);
            if (count3 != 13) {
               if (count3 == 12) {
                  count = -1;
                  value = (value ^ -0x72bdba9189040892L) - 0x549b326bf425c2aaL;
                  value2 = (value2 ^ 0x25c07143350bb0f0L) + 0x4169dc41cd1216d8L;
                  continue;
               }

               if (count3 == 2) {
                  byteValueArray2 = new byte[count2];
                  value = (value ^ -0x6d01fa9e1339e7aaL) + 0x2d306e90fe70b2f7L;
                  value2 = (value2 ^ 0x6c03d099fe256ab5L) + 0x7d6bdb1ec73cb63cL;
                  continue;
               }

               if (count3 == 6) {
                  throw new IllegalArgumentException("Invalid PKCS#1 padding");
               }

               if (count3 == 8) {
                  if (byteValueArray[0] != 0) {
                     value = (value ^ -0x756363d33c838153L) - 0x44d84e026dc58082L;
                     value2 = (value2 ^ -0x6441d45a40fd87aL) - 0x566f70f3f1a94112L;
                  } else {
                     value = (value ^ 0x260f00348a11ebd4L) + 0x434d7b6b33a595edL;
                     value2 = (value2 ^ -0x4af2251de03e1b34L) + 0x198c91d20f547f3bL;
                  }
                  continue;
               }

               if (count3 == 3) {
                  throw new IllegalArgumentException("Invalid PKCS#1 padding");
               }

               if (count3 == 1) {
                  return byteValueArray2;
               }

               if (count3 == 5) {
                  count2 = 2;
                  value = (value ^ 0x4771f016732a5994L) + 0x4377b6a9cd225ebeL;
                  value2 = (value2 ^ 0x2a943baecbbde6fbL) + 0x29618d115f96505eL;
                  continue;
               }

               if (count3 == 10) {
                  System.arraycopy(byteValueArray, count + 1, byteValueArray2, 0, count2);
                  return byteValueArray2;
               }

               if (count3 == 14) {
                  count2 = byteValueArray.length - count - 1;
                  value = (value ^ 0x59fbfd9298c107faL) + 0x660bba60839c4292L;
                  value2 = (value2 ^ 0x30c206cfda936202L) - 0x4d5b2c0eaf6aa282L;
                  continue;
               }

               if (count3 == 7) {
                  if (byteValueArray[1] == 2) {
                     value = (value ^ -0x1d8b3f1c4dd0307eL) - 0x516b77a1e1e391f9L;
                     value2 = (value2 ^ 0x59682ae181e530feL) - 0x171782c1b1694dddL;
                  } else {
                     value = (value ^ -0x7db34d9bf84251caL) - 0x6e59af4154ec1fc8L;
                     value2 = (value2 ^ 0xc94191748847603L) - 0x3f16023104720b98L;
                  }
                  continue;
               }

               if (count3 == 0) {
                  if (byteValueArray.length < 11) {
                     value = (value ^ -0x1ec92a9eab92568eL) + 0x769e0dc6c39e4989L;
                     value2 = (value2 ^ 0x4d25b6de3b030240L) + 0x235a40fa5b7d4c39L;
                  } else {
                     value = (value ^ -0x7a4b473bc5258adcL) - 0x619aecb56f192890L;
                     value2 = (value2 ^ -0x19a3f29ed0ce2d3aL) + 0x3b7c9f0ceba83953L;
                  }
                  continue;
               }

               if (count3 == 9) {
                  count = count2;
                  value = (value ^ -0x3f57f74e4f6d4badL) + 0x726870f6f6fab3bfL;
                  value2 = (value2 ^ -0x56dedc3bc4e3a4eaL) + 0x2dad4de409bf8e98L;
                  continue;
               }

               if (count3 == 4) {
                  value3 = (value ^ -0x4fa0704dac745fe0L) + 0x705874b1cc895fe0L;
                  value4 = (value2 ^ -0x78fc43e155cfea2aL) - 0x78fd9c0955cfeaaaL;
                  break;
               }

               if (byteValueArray[count2] == 0) {
                  value = (value ^ -0x25eed5068a8f78b6L) + 0x6ade2e4b87496f82L;
                  value2 = (value2 ^ 0x42ebafa357bb8f81L) + 0x412c5bc99ad4706aL;
                  continue;
               }

               long value5 = (value ^ -0x3558ecf683e58429L) + 0x2d106fe0e4f18bf7L;
               long value6 = (value2 ^ -0x2f61b26b1a0917beL) + 0x79c42b1d795c5139L;
               count2++;
               value = (value5 ^ 0x6f426109414d0f0dL) + 0x36869f275aa94bbL;
               value2 = (value6 ^ -0x388ad2bbf80d4148L) + 0x3fc0d3a4685d2755L;
            }

            if (count2 >= byteValueArray.length) {
               value3 = (value ^ -0x20ced4c07c2d751L) - 0x69d99d4c1f24ad63L;
               value4 = (value2 ^ 0x25a718791f07359dL) + 0x629309be8018b1b8L;
               break;
            }

            value = (value ^ 0x747db20f6c89445bL) + 0x19f1821faba8283bL;
            value2 = (value2 ^ -0x3853aa3826d28728L) - 0x9b99e3955dfba7cL;
         }

         if (count >= 10) {
            value = (value3 ^ -0xa0e09f7ab553debL) + 0x7a13968bc484d7acL;
            value2 = (value4 ^ 0x2a468bfa75ba4279L) + 0x2b87e8e73cf491f3L;
         } else {
            value = (value3 ^ 0x16648e82cfb3a4b4L) + 0x6adb349a95d960b1L;
            value2 = (value4 ^ -0x4c39b27a041a5cdbL) - 0x5e73b239ca6e78aeL;
         }
      }
   }

   public static byte[] resolveByteArray(byte[] byteValueArray, int count) {
      byte[] byteValueArray2 = null;
      EnhancedLongEntry enhancedLongEntry = null;
      int count2 = 0;
      int count3 = 0;
      byte byteValue = 0;
      byte[] byteValueArray3 = null;
      long value = 0x6f62c148126563e0L;
      long value2 = 0x717199f737693406L;

      while (true) {
         int count4 = (int)(value % 17L);
         if (count4 == 7) {
            break;
         }

         if (count4 != 4) {
            if (count4 != 11) {
               if (count4 != 6) {
                  if (count4 != 0) {
                     if (count4 != 8) {
                        if (count4 == 9) {
                           byteValueArray3 = new byte[1];
                           value = (value ^ -0x3fa5b1cce16d5be6L) + 0x5d0b4f9a9d695e29L;
                           value2 = (value2 ^ 0x3ef88c36d7f6260eL) + 0x7b12a55e5f9fc87L;
                           continue;
                        }

                        if (count4 == 15) {
                           if (byteValueArray.length <= count - 11) {
                              value = (value ^ -0x7629265ee7b5ba6bL) + 0x4a9afcc3f388d684L;
                              value2 = (value2 ^ 0x51f83d46d7a4647cL) - 0x4c7256004aff71e9L;
                           } else {
                              value = (value ^ -0x22fd3458ec66c2ffL) - 0x6666131e11d28a24L;
                              value2 = (value2 ^ 0x7a152c0f6d0ac743L) + 0x6fb76aaaf74b1d2fL;
                           }
                           continue;
                        }

                        if (count4 == 2) {
                           count2 = count - byteValueArray.length - 3;
                           value = (value ^ -0x1c60bc0f5a7a7afdL) - 0x441addbe532b3b99L;
                           value2 = (value2 ^ 0x962e65f1f5565cbL) - 0x4e576d7c1834b4f5L;
                           continue;
                        }

                        if (count4 == 1) {
                           byteValue = byteValueArray3[0];
                           value = (value ^ 0x27fbef6d9b4f5c1fL) + 0x36f90e90bdb80175L;
                           value2 = (value2 ^ 0x7ec9c9ea587aa831L) + 0x2b37ca974f564ec0L;
                           continue;
                        }

                        if (count4 == 14) {
                           System.arraycopy(byteValueArray, 0, byteValueArray2, count2 + 3, byteValueArray.length);
                           break;
                        }

                        if (count4 == 5) {
                           enhancedLongEntry.applyByteArrayByByteArray(byteValueArray3);
                           value = (value ^ 0x5a2e9e6b36f9c493L) - 0x3cb52010ce6e062L;
                           value2 = (value2 ^ -0x3f71fe97003e42efL) + 0x329d7a6fce575785L;
                           continue;
                        }

                        if (count4 == 12) {
                           byteValueArray2[0] = 0;
                           value = (value ^ 0x68df2965c21e1aeL) + 0x48bad486fa0e4e4aL;
                           value2 = (value2 ^ -0x270f71a776d50685L) + 0x51cbcc70f6adac83L;
                           continue;
                        }

                        if (count4 == 3) {
                           if (byteValue == 0) {
                              value = (value ^ 0x4e22e788356c6edcL) + 0x6062293f14a2b2faL;
                              value2 = (value2 ^ -0x25a714c091a050bdL) + 0x67328dd5bdf58193L;
                           } else {
                              value = (value ^ -0x2691fac0db63da87L) - 0x70800ef950324445L;
                              value2 = (value2 ^ -0x5fa18afbd38edd8eL) - 0x1e3dab06584e3910L;
                           }
                           continue;
                        }

                        if (count4 != 13) {
                           if (count4 != 10) {
                              throw new IllegalArgumentException("Data too large for block size");
                           }

                           byteValueArray2[1] = 2;
                           value = (value ^ -0x2650ad492e3d43aeL) - 0x10ade2bde6ba416dL;
                           value2 = (value2 ^ 0x22fa4a408fa8729bL) + 0x1781b91c3231a86aL;
                           continue;
                        }

                        count3++;
                        value = (value ^ -0x2a578938ee3f9b85L) - 0x5fd0aec4e45326f7L;
                        value2 = (value2 ^ -0x1bc92ea6a52bc1c6L) + 0x9f94ae0a5da9fedL;
                     }

                     if (count3 >= count2) {
                        long value3 = (value ^ 0x6501954cad9dbf4L) + 0x1068aabb08b98b8cL;
                        long value4 = (value2 ^ -0x199e3c204ea8d5b8L) - 0x7eddbe100b36c5fL;
                        byteValueArray2[count2 + 2] = 0;
                        value = (value3 ^ -0x3462c600f9c2792dL) - 0x427382059a7237a5L;
                        value2 = (value4 ^ 0x3fa5be27615189e9L) - 0x710f3c6e2db26d4L;
                     } else {
                        value = (value ^ 0x50cd1671151d5bb3L) + 0x76163b791c6bf60dL;
                        value2 = (value2 ^ 0x777ce58df958b97fL) + 0x480a2370755ce044L;
                     }
                  } else {
                     byteValueArray2 = new byte[count];
                     value = (value ^ 0x487c0fe64b64f88aL) - 0x6d94f6b1d6891041L;
                     value2 = (value2 ^ -0x1e891535131ef17dL) + 0x905caca373f9dd8L;
                  }
               } else {
                  count3 = 0;
                  value = (value ^ 0x69d2c46c1a041e12L) + 0x3aa900438f2d3ad9L;
                  value2 = (value2 ^ -0x5d711fab0f91428cL) + 0x7702a020012d3134L;
               }
            } else {
               enhancedLongEntry = new EnhancedLongEntry();
               value = (value ^ -0xaaf4d07d83a145aL) - 0x3447b5e95851effaL;
               value2 = (value2 ^ 0x24bce1267ebb779cL) + 0x38242f7f20b0e2e5L;
            }
         } else {
            byteValueArray2[count3 + 2] = byteValue;
            value = (value ^ 0x3690729091c26b9eL) + 0x345d1e9188385be0L;
            value2 = (value2 ^ 0x483179e45f9bcbbdL) - 0x69a09ed18c585993L;
         }
      }

      return byteValueArray2;
   }
}
