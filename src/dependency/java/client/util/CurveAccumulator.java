package client.util;

import java.util.Arrays;

public class CurveAccumulator {
   public static final boolean enabled = !CurveAccumulator.class.desiredAssertionStatus();
   public static final int count3 = 6;
   public static final int count = 3;
   public int count2 = 0;
   public int count4;
   public int[] countArray;
   public static final CurveAccumulator curveAccumulator = new CurveAccumulator(1);

   public void applyCurveAccumulatorByCurveAccumulator(CurveAccumulator curveAccumulator2) {
      int count5 = 0;
      count5 = curveAccumulator2.count4;
      if (this.countArray.length < count5) {
         this.countArray = new int[count5];
      }

      System.arraycopy(curveAccumulator2.countArray, curveAccumulator2.count2, this.countArray, 0, count5);
      this.count4 = count5;
      this.count2 = 0;
   }

   private final void applyIndex5(int count5) {
      int[] entry = null;
      int count6 = 0;
      int count7 = 0;
      int count8 = 0;
      int count9 = 0;
      entry = this.countArray;
      count6 = 32 - count5;
      count7 = this.count2;
      count8 = (int)entry[count7];

      for (int index = count7 + this.count4 - 1; count7 < index; count7++) {
         count9 = count8;
         count8 = (int)entry[count7 + 1];
         entry[count7] = count9 << count5 | count8 >>> count6;
      }

      entry[this.count2 + this.count4 - 1] = entry[this.count2 + this.count4 - 1] << count5;
   }

   public void updateState() {
      int count5 = 0;
      this.count2 = this.count4 = 0;
      count5 = 0;

      for (int index = this.countArray.length; count5 < index; count5++) {
         this.countArray[count5] = 0;
      }
   }

   public CurveAccumulator resolveCurveAccumulator(int count5) {
      int count6 = 0;
      long value = 0x1fcffe7d9774f966L;
      if (!this.isActive4()) {
         value = (value ^ 0xe486d40c86b74beL) - 0x7d5acbfe6c7ad02cL;
         if (count5 <= 64) {
            value = (value ^ -0x3393d70cc8f0abe6L) + 0x442b27e400c80b4L;
            count6 = computeCountByIndex(this.countArray[this.count2 + this.count4 - 1]);
            value = (value ^ -0x6dd8ce1b2ed30e4L) - 0x2c5bd5cc8c5399bL;
            if (count5 >= 33) {
               long value2 = (value ^ -0x103fe22d3e808419L) - 0x16c314b7ea30c40L;
               long value3 = (long)this.countArray[this.count2 + this.count4 - 1] & 4294967295L;
               if (this.count4 <= 1) {
                  value2 = (value2 ^ -0x1dd26c2540274acL) - 0x69dd2905541384acL;
               } else {
                  value3 |= (long)this.countArray[this.count2 + this.count4 - 2] << 32;
               }

               long value4 = (long)count6 & 4294967295L;
               long value5 = value4 * (2L - value3 * value4);
               long value6;
               if (count5 != 64) {
                  long value7 = (value2 ^ 0x363c6596f92468beL) - 0x31e3dc6e86dc6746L;
                  value6 = value5 & ((value7 % 230559L ^ 142631L) << count5) - (value7 % 414058L ^ 47647L);
               } else {
                  value6 = value5;
               }

               long value8 = value6;
               CurveAccumulator curveAccumulator2 = new CurveAccumulator(new int[2]);
               curveAccumulator2.countArray[0] = (int)(value8 >>> 32);
               curveAccumulator2.countArray[1] = (int)value8;
               curveAccumulator2.count4 = 2;
               curveAccumulator2.updateState3();
               return curveAccumulator2;
            } else {
               value = (value ^ -0x2355fa2228565ad9L) + 0x43570a366e9dbfeeL;
               int count7;
               if (count5 != 32) {
                  count7 = count6 & (1 << count5) - 1;
               } else {
                  value = (value ^ -0x75eb6c3e001f0edfL) - 0x29d6c2a9137bcfL;
                  count7 = count6;
               }

               int count8 = count7;
               return new CurveAccumulator(count8);
            }
         } else {
            value = (value ^ -0x5000dce397aa27cdL) + 0x5be5d09eb466330L;
            return this.resolveCurveAccumulatorByIndex(count5);
         }
      } else {
         value = (value ^ -0x6a03377234553ca9L) + 0x4ba55cae5fe7f816L;
         throw new ArithmeticException("Non-invertible. (GCD != 1)");
      }
   }

   public long computeTimestampByIndex(int count5) {
      int[] entry = null;
      int count6 = 0;
      int count7 = 0;
      if (this.count4 != 0 && count5 != 0) {
         entry = this.getIndexArray();
         count6 = entry.length;
         count7 = entry[0];
         if (count6 <= 2 && (count7 >= 0 || count6 != 2)) {
            long value = count6 != 2 ? (long)count7 & 4294967295L : (long)entry[1] & 4294967295L | ((long)count7 & 4294967295L) << 32;
            return count5 != -1 ? value : -value;
         } else {
            return Long.MIN_VALUE;
         }
      } else {
         return 0L;
      }
   }

   private CurveAccumulator resolveCurveAccumulatorByCurveAccumulator(CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3, boolean flag) {
      int count5 = 0;
      int count6 = 0;
      int[] countArray2 = null;
      CurveAccumulator curveAccumulator4 = null;
      int[] countArray3 = null;
      int count7 = 0;
      int count8 = 0;
      int count9 = 0;
      int count10 = 0;
      int count11 = 0;
      int count12 = 0;
      int count13 = 0;
      int count14 = 0;
      int count15 = 0;
      boolean flag2 = false;
      int count16 = 0;
      int count17 = 0;
      int count18 = 0;
      int count19 = 0;
      int[] countArray4 = null;
      long value = 0L;
      long value2 = 0x414cb6b89566a81aL;
      long value3 = 0x1cc8d37912c4b912L;

      while (true) {
         innerLoop:
         while (true) {
            while (true) {
               int count20 = (int)(value2 % 56L);
               if (count20 != 53) {
                  if (count20 == 26) {
                     curveAccumulator4 = new CurveAccumulator(countArray3);
                     value2 = (value2 ^ -0x5c3ec7b1b7a055ecL) + 0x361fbacd08a811daL;
                     value3 = (value3 ^ 0x75156ee8091883cL) - 0x7ed4af10c7f552d0L;
                     continue;
                  }

                  if (count20 != 37) {
                     if (count20 == 48) {
                        flag2 = false;
                        value2 = (value2 ^ -0x7aa73d3822b2564aL) - 0x3645e3261cdc9bfdL;
                        value3 = (value3 ^ -0x8b87360e2a70570L) - 0x6725124312b64940L;
                        continue;
                     }

                     if (count20 == 7) {
                        if (count5 <= 0) {
                           long value4 = (value2 ^ 0x20b49a5ce6f285d5L) + 0xb4fec7a0adda4d1L;
                           long value5 = (value3 ^ -0x60acd4e9d133b740L) + 0x7fe550414c07f9b9L;
                           countArray2 = Arrays.copyOfRange(curveAccumulator2.countArray, curveAccumulator2.count2, curveAccumulator2.count2 + curveAccumulator2.count4);
                           value2 = (value4 ^ 0x466453d46aa8a0d8L) - 0x260379fc19334aeL;
                           value3 = (value5 ^ 0x782cb0722a783aebL) + 0x3770fd9f8321d522L;
                        } else {
                           value2 = (value2 ^ 0x520c8b83183a55d4L) - 0x2c3684a6b684e979L;
                           value3 = (value3 ^ 0x36018e5fa70239feL) - 0x275c91dd977bb6a1L;
                        }
                        continue;
                     }

                     if (count20 == 39) {
                        count8 = this.countArray[count7];
                        value2 = (value2 ^ -0x5fddbd6d9c436785L) + 0x3a6042ccdc1ab50bL;
                        value3 = (value3 ^ 0x778304cf1327f4b9L) + 0x1d64a5daaaafc929L;
                        continue;
                     }

                     if (count20 == 49) {
                        curveAccumulator3.countArray = new int[count7];
                        value2 = (value2 ^ -0x6e409e6d613b066L) - 0x7994c092ab8ad612L;
                        value3 = (value3 ^ 0x2c07e2c82a213f0dL) + 0x76edea439253ce3fL;
                        continue;
                     }

                     if (count20 == 14) {
                        if (Integer.numberOfLeadingZeros(this.countArray[this.count2]) < count5) {
                           long value6 = (value2 ^ 0x19a103bc5d81daefL) + 0x220e0a3b5215e98cL;
                           long value7 = (value3 ^ -0x63cc483bbc973049L) + 0x402dfb9d0b06ae0bL;
                           countArray3 = new int[this.count4 + 2];
                           value2 = (value6 ^ 0x7d11c19e16d2bf0dL) + 0x3a6541708df519f2L;
                           value3 = (value7 ^ 0x451f4058ce37e902L) - 0x272738e9f7cad7cL;
                        } else {
                           value2 = (value2 ^ 0x1eac091b2fce8a19L) - 0x609819dcf40868bL;
                           value3 = (value3 ^ 0x5f80632daf5892a8L) + 0x1d74dd4ac8365cfdL;
                        }
                        continue;
                     }

                     if (count20 == 5) {
                        curveAccumulator4 = new CurveAccumulator(new int[this.count4 + 1]);
                        value2 = (value2 ^ 0x43441b4d699d4680L) - 0x221ee5de4c99a187L;
                        value3 = (value3 ^ 0x12a55571d381c9d5L) + 0x7531b7a4382a06edL;
                        continue;
                     }

                     if (count20 == 6) {
                        System.arraycopy(this.countArray, this.count2, curveAccumulator4.countArray, 1, this.count4);
                        value2 = (value2 ^ -0x286dee941d8b00dL) + 0x377653c9193ce88dL;
                        value3 = (value3 ^ -0x2536ebb6ab44f92bL) + 0x4ac53c5de4b46b63L;
                        continue;
                     }

                     if (count20 == 0) {
                        count9 = 32 - count5;
                        value2 = (value2 ^ 0xa0d73d584016fccL) + 0x3b39afd03ceba88dL;
                        value3 = (value3 ^ -0x31a62cb5069f581L) - 0x17aacedeccef4bb4L;
                        continue;
                     }

                     if (count20 == 35) {
                        count14 = 0;
                        value2 = (value2 ^ 0x18340842fa035951L) + 0x462145bc04b13581L;
                        value3 = (value3 ^ 0x2965a866bb5684c2L) + 0x2c4cab3cef46556bL;
                        continue;
                     }

                     if (count20 == 28) {
                        curveAccumulator4.countArray[0] = 0;
                        value2 = (value2 ^ -0x2b36c74aaa86f2a7L) - 0x3c45e33312423e21L;
                        value3 = (value3 ^ -0x20c134edae492190L) + 0x17243f32c3f3876eL;
                        continue;
                     }

                     if (count20 == 1) {
                        curveAccumulator3.count2 = 0;
                        value2 = (value2 ^ -0x57b030e0e8b14bc3L) - 0x591e8903fcdda9e0L;
                        value3 = (value3 ^ 0x3479973915eb2ca1L) - 0x581af7befe188ed4L;
                        continue;
                     }

                     if (count20 == 20) {
                        curveAccumulator4.count2 = 1;
                        value2 = (value2 ^ 0xfe26510b68a838cL) - 0x23a950c669907589L;
                        value3 = (value3 ^ 0x3536ca8082649a92L) + 0x48de8d5a630ffe32L;
                        continue;
                     }

                     if (count20 == 2) {
                        count5 = Integer.numberOfLeadingZeros(curveAccumulator2.countArray[curveAccumulator2.count2]);
                        value2 = (value2 ^ 0x8c822a1a207e67fL) - 0x136a2a5b18b825bfL;
                        value3 = (value3 ^ -0x4ba414034f283e32L) + 0x52a0469eb69a7af0L;
                        continue;
                     }

                     if (count20 == 24) {
                        countArray4 = curveAccumulator3.countArray;
                        value2 = (value2 ^ -0x74076f72970845c7L) + 0x45d4f3d4b083cdc1L;
                        value3 = (value3 ^ -0x3f784a0e17ee07e0L) - 0x43ccc4701b490c25L;
                        continue;
                     }

                     if (count20 == 54) {
                        count15 = count16 + count18;
                        value2 = (value2 ^ 0x53d5332695890157L) + 0x4d432adf5301692bL;
                        value3 = (value3 ^ -0x7b1c18583c6a1220L) + 0x30f5b19a1ffeddd4L;
                        continue;
                     }

                     if (count20 == 47) {
                        countArray3[count10] = count11 << count5 | count8 >>> count9;
                        value2 = (value2 ^ -0x9c3145c9112910fL) + 0x37947723a2a8ad0fL;
                        value3 = (value3 ^ 0x3bb58a2f39cfdeccL) + 0x71fe458eb52d1d2fL;
                        continue;
                     }

                     if (count20 == 19) {
                        curveAccumulator4.count2 = 1;
                        value2 = (value2 ^ 0x22d5039c958ea855L) + 0x59aa7a1b67d29599L;
                        value3 = (value3 ^ 0x64a9fadbb911b431L) + 0x436f64fe4f61d8e2L;
                        continue;
                     }

                     if (count20 == 32) {
                        curveAccumulator4.count4 = this.count4 + 1;
                        value2 = (value2 ^ 0x7af0bc0a1cd71ebdL) - 0x4c2528c01c0789a1L;
                        value3 = (value3 ^ 0x7a13dcd56eb91195L) + 0x3bc43b788a2d959eL;
                        continue;
                     }

                     if (count20 == 12) {
                        count12 = countArray2[1];
                        value2 = (value2 ^ -0x4b1ec5ad4f1e7f4L) + 0x70ae0b6f574a6272L;
                        value3 = (value3 ^ 0x4fa90509b48cee5eL) - 0x1a6cdcbb262665bdL;
                        continue;
                     }

                     if (count20 != 11) {
                        if (count20 == 22) {
                           curveAccumulator4.count2 = 1;
                           value2 = (value2 ^ 0x6d712758c31d162L) + 0x487b174565d2eb87L;
                           value3 = (value3 ^ 0x1c7cdb960038a413L) - 0x770bc166eb2bf335L;
                           continue;
                        }

                        if (count20 == 46) {
                           count18 = curveAccumulator4.countArray[count13 + 1 + curveAccumulator4.count2];
                           value2 = (value2 ^ 0x10d1856208c34cf4L) + 0x3ea74d1c16d5d99fL;
                           value3 = (value3 ^ -0x9073833c78d8aecL) - 0x3d7378e204ea2683L;
                           continue;
                        }

                        if (count20 == 23) {
                           count14 = -1;
                           value2 = (value2 ^ -0x7a429401c15f5b5fL) + 0x7bc833518d8fc068L;
                           value3 = (value3 ^ -0x6407205b4b135688L) - 0x51db98d68a7dc17eL;
                           continue;
                        }

                        if (count20 != 41) {
                           if (count20 == 42) {
                              curveAccumulator4.count4 = this.count4;
                              value2 = (value2 ^ 0x11538384b84c7080L) - 0x20a3a773fc6ca34L;
                              value3 = (value3 ^ -0x2f69569d78b5b345L) + 0x704ad2b15937ebc2L;
                              continue;
                           }

                           if (count20 == 50) {
                              countArray2 = new int[count6];
                              value2 = (value2 ^ -0x34347d7f7bbcf02fL) - 0x50820ca3be893d4eL;
                              value3 = (value3 ^ -0x5096db4d4c3c2fb0L) - 0x480b5f5fd59a714bL;
                              continue;
                           }

                           if (count20 == 55) {
                              count17 = count16 + Integer.MIN_VALUE;
                              value2 = (value2 ^ -0x49f06694543cdcfaL) + 0x430ff5b7586e8f55L;
                              value3 = (value3 ^ 0x116d8aa10c187cfbL) + 0x50d25a5a45ca010aL;
                              continue;
                           }

                           if (count20 == 40) {
                              break;
                           }

                           if (count20 == 34) {
                              count8 = 0;
                              value2 = (value2 ^ -0x2b2450f3e92d208dL) - 0x78eb16ec002f4f91L;
                              value3 = (value3 ^ 0x3f61994b8f9b50b3L) + 0x250ae87a9854030eL;
                              continue;
                           }

                           if (count20 == 15) {
                              byte byteValue = 1;
                              int count21 = this.count4;
                              int count22 = this.count2;
                              applyIndexArray(this.countArray, count22, count21, countArray3, byteValue, count5);
                              value2 = (value2 ^ -0x7e2e8bc6769f13feL) + 0x66dcec572a389af0L;
                              value3 = (value3 ^ -0x1e322317c522012cL) - 0x49ca58ce02097296L;
                              continue;
                           }

                           if (count20 == 45) {
                              curveAccumulator4 = new CurveAccumulator(countArray3);
                              value2 = (value2 ^ 0xe9a309749ddcadfL) + 0x4e126ca977fdb8f9L;
                              value3 = (value3 ^ -0x234e15286911c3ebL) - 0x22f6dc292a64ae41L;
                              continue;
                           }

                           if (count20 == 25) {
                              count16 = curveAccumulator4.countArray[count13 + curveAccumulator4.count2];
                              value2 = (value2 ^ -0x7be11df4b545e327L) - 0x793fd52d12f08621L;
                              value3 = (value3 ^ 0x736c39ed6145090fL) - 0x6add259b722678b0L;
                              continue;
                           }

                           if (count20 == 17) {
                              count10 = 1;
                              value2 = (value2 ^ 0x73028a2822cc2872L) + 0x14e981b2d166fc95L;
                              value3 = (value3 ^ 0xa7229e482c56ce3L) - 0x18fa5c5fc5088154L;
                              continue;
                           }

                           long value8;
                           long value9;
                           guardBlock: {
                              long value10;
                              long value11;
                              searchBlock: {
                                 long value12;
                                 long value13;
                                 boolean flag3;
                                 if (count20 != 44) {
                                    if (count20 == 18) {
                                       count13 = 0;
                                       value2 = (value2 ^ 0x596dd37096bdcb61L) + 0x6c9cecf3347406d6L;
                                       value3 = (value3 ^ 0x7b3b52832dcec005L) + 0x28d861e22d3e1958L;
                                       continue;
                                    }

                                    if (count20 == 36) {
                                       curveAccumulator3.count4 = count7;
                                       value2 = (value2 ^ 0x1ff0fc765e5f2601L) + 0x1cac801745e28c9bL;
                                       value3 = (value3 ^ -0x30bd918b63eaf1f3L) + 0xe709949c04e8bf7L;
                                       continue;
                                    }

                                    if (count20 == 8) {
                                       value = (long)count9 & 4294967295L;
                                       value2 = (value2 ^ 0x66829f51f941e43fL) - 0xeb02700a446889bL;
                                       value3 = (value3 ^ -0x23fea83d19b2cc28L) + 0x3496834e2dfbd8f4L;
                                       continue;
                                    }

                                    if (count20 != 16) {
                                       if (count20 == 31) {
                                          count7 = this.count2;
                                          value2 = (value2 ^ 0x46c7e802ce4390d8L) - 0x128a4132945004b5L;
                                          value3 = (value3 ^ 0x55608554df74dfd9L) + 0x29bcbbd7162cfc48L;
                                          continue;
                                       }

                                       if (count20 == 29) {
                                          byte byteValue2 = 0;
                                          int count23 = curveAccumulator2.count2;
                                          applyIndexArray(curveAccumulator2.countArray, count23, count6, countArray2, byteValue2, count5);
                                          value2 = (value2 ^ 0x95452a4a5aba44fL) + 0x4bed08e7a71f0b4L;
                                          value3 = (value3 ^ -0xd357149c62b12a8L) + 0x5d2d98a799ef9eaaL;
                                          continue;
                                       }

                                       if (count20 != 33) {
                                          if (count20 == 4) {
                                             countArray3 = new int[this.count4 + 1];
                                             value2 = (value2 ^ 0x646e093e63997ebL) - 0xf94b6799cba28baL;
                                             value3 = (value3 ^ 0x13151099ef45d8f8L) + 0x7854c23dbfa2eee4L;
                                             continue;
                                          }

                                          if (count20 == 30) {
                                             count6 = curveAccumulator2.count4;
                                             value2 = (value2 ^ 0x2830b6abc11007ccL) + 0x1e3e28ab8c78b4c5L;
                                             value3 = (value3 ^ -0x7b9547a9fbe526b7L) + 0x343e902b97a53709L;
                                             continue;
                                          }

                                          if (count20 == 9) {
                                             if (curveAccumulator3.countArray.length >= count7) {
                                                value2 = (value2 ^ 0x31f0fe5b6ffeee92L) + 0x31093590dcd356a1L;
                                                value3 = (value3 ^ -0x568e8821d55dcd07L) - 0x1df00487e0ae1127L;
                                             } else {
                                                value2 = (value2 ^ -0x7fdbb04c2c33eea4L) - 0x5c158ad41fa7baacL;
                                                value3 = (value3 ^ -0x3b8951892dbda5aaL) + 0x764990da8d7d1333L;
                                             }
                                             continue;
                                          }

                                          if (count20 == 13) {
                                             curveAccumulator4.count2 = 0;
                                             value2 = (value2 ^ 0x30b20820a499e611L) + 0x6a7e4a9215350d8L;
                                             value3 = (value3 ^ -0xaf91cb68f136351L) + 0x4d77ef7307e0dfaeL;
                                             continue;
                                          }

                                          if (count20 == 3) {
                                             count15 = 0;
                                             value2 = (value2 ^ -0x28f24f1c0b11ad7bL) + 0x7b4e944434535132L;
                                             value3 = (value3 ^ 0x2c1b5962e54532c5L) - 0x605b54b771fda47L;
                                             continue;
                                          }

                                          if (count20 == 43) {
                                             count9 = countArray2[0];
                                             value2 = (value2 ^ -0x75d5495413aa72ecL) + 0x5029183087339f41L;
                                             value3 = (value3 ^ 0x40d929ec12137ad0L) + 0x1d75d0c49942172aL;
                                             continue;
                                          }

                                          if (count20 == 21) {
                                             count11 = count8;
                                             value2 = (value2 ^ -0x5f7e986064819df0L) + 0x610f72e513b9d7baL;
                                             value3 = (value3 ^ 0x6c811bbf7b6fd2b6L) - 0x24c37a85526dd878L;
                                             continue;
                                          }

                                          if (count20 == 27) {
                                             curveAccumulator4.count4 = this.count4;
                                             value2 = (value2 ^ 0x4933f99f83c4f221L) + 0xb4c3bdfc1a74009L;
                                             value3 = (value3 ^ -0x72a4a6b47dd00bd8L) - 0x3bf6370bfa459214L;
                                             continue;
                                          }

                                          if (count20 != 51) {
                                             if (count20 != 52) {
                                                if (count20 != 10) {
                                                   count7 = count19 - count6 + 1;
                                                   value2 = (value2 ^ -0x7be15ccec7099bb6L) + 0x6398a8f1d75d4e5dL;
                                                   value3 = (value3 ^ -0x6a6f6005d6fb2449L) + 0x749ccef6d03ca71L;
                                                } else if (curveAccumulator4.count4 != count19) {
                                                   value2 = (value2 ^ -0x6f596382f309f7f2L) - 0x5a27cf1c4745d769L;
                                                   value3 = (value3 ^ -0x7b0e2204bdc74248L) - 0x26f5a4e9784218a7L;
                                                } else {
                                                   value2 = (value2 ^ -0x5e402ba54f608caL) - 0x5dc22988eb3e6167L;
                                                   value3 = (value3 ^ 0x4ad0dbdbafc5b213L) + 0x6b13df27e7a0db19L;
                                                }
                                             } else {
                                                curveAccumulator4.count4++;
                                                value2 = (value2 ^ -0x79793835ed316116L) - 0x6983399207a0dfa3L;
                                                value3 = (value3 ^ -0x6f687357239fdc5cL) + 0x31f0c73b265146eeL;
                                             }
                                             continue;
                                          }
                                          break innerLoop;
                                       }

                                       if (count16 == count9) {
                                          value2 = (value2 ^ -0x19da530465e6e58bL) - 0x591734903bcc2a15L;
                                          value3 = (value3 ^ 0x7989d051d96f85ddL) + 0x2056cde189f16971L;
                                          continue;
                                       }

                                       long value14 = (value2 ^ 0x3dc7005b4d76e47eL) - 0x53a7768c5882ed3fL;
                                       long value15 = (value3 ^ 0x727f14fdd98797e5L) + 0x58571b7a18b2f247L;
                                       long value16 = (long)count16 << 32 | (long)count18 & 4294967295L;
                                       if (value16 < 0L) {
                                          value10 = (value14 ^ -0x7329e9c9afa7b87L) + 0x2844e0e364fd83e7L;
                                          value11 = (value15 ^ -0x144bf00773cec8f0L) - 0x1aabfc288c113510L;
                                          long value17 = computeTimestampByTime(value16, count9);
                                          count14 = (int)(value17 & 4294967295L);
                                          count15 = (int)(value17 >>> 32);
                                       } else {
                                          count14 = (int)(value16 / value);
                                          count15 = (int)(value16 - (long)count14 * value);
                                          value10 = (value14 ^ 0x62630a8bbb5242b4L) - 0x5e61067449524154L;
                                          value11 = (value15 ^ -0xe365f2701c2f082L) - 0x10b85f48fa0cfcf2L;
                                       }

                                       if (count14 == 0) {
                                          value8 = (value10 ^ -0x2c2d58f3e1b60834L) + 0x514d9f0419b95334L;
                                          value9 = (value11 ^ -0x2dd7063fba90541bL) - 0x32178640435b995bL;
                                          break guardBlock;
                                       }
                                       break searchBlock;
                                    }

                                    flag3 = true;
                                    value12 = (value2 ^ 0x5b3bfea56a3cc9f7L) - 0x37099a6299fbc697L;
                                    value13 = (value3 ^ -0x555cff6c62e171b3L) - 0x59a2ff919aea7dc3L;
                                 } else {
                                    if (count15 + Integer.MIN_VALUE < count17) {
                                       value2 = (value2 ^ -0x7ee629db47f7d62cL) + 0x3d215522a5c16c38L;
                                       value3 = (value3 ^ -0x5cf3e5da37b3fb4fL) - 0x1049b6f4db1bc3efL;
                                       continue;
                                    }

                                    value12 = (value2 ^ 0x432a7ba02853354eL) - 0x4ab4061ca198032L;
                                    value13 = (value3 ^ 0x7e4bfb9913d9769eL) + 0x2afb288ffd794a2eL;
                                    flag3 = false;
                                 }

                                 flag2 = flag3;
                                 value10 = (value12 ^ 0x8696c5dfa514827L) - 0x8572c2a0a4f47e7L;
                                 value11 = (value13 ^ 0x4b73b7b19ba48cc3L) + 0x448dab906a5b83c3L;
                              }

                              value8 = (value10 ^ 0x73440128eecfef9L) + 0x4ebc0128a544a67L;
                              value9 = (value11 ^ -0x16a246bb219a0777L) - 0x194446bcda65e887L;
                              if (flag2) {
                                 value8 = (value8 ^ -0x21f27cff0071b9eL) + 0x2b1f48300b4863deL;
                                 value9 = (value9 ^ 0x42c53609c17cb482L) + 0x3e432a0840b34b82L;
                              } else {
                                 long value18 = (long)curveAccumulator4.countArray[count13 + 2 + curveAccumulator4.count2] & 4294967295L;
                                 long value19 = ((long)count15 & 4294967295L) << 32 | value18;
                                 long value20 = ((long)count12 & 4294967295L) * ((long)count14 & 4294967295L);
                                 if (!this.checkTime(value20, value19)) {
                                    value8 = (value8 ^ -0x153a136d531c70eaL) + 0x1a3c5392ac5f7b0aL;
                                    value9 = (value9 ^ 0x7551b660bb1706a4L) + 0x72afaa5f4b0cf964L;
                                 } else {
                                    count14--;
                                    count15 = (int)(((long)count15 & 4294967295L) + value);
                                    if (((long)count15 & 4294967295L) < value) {
                                       value8 = (value8 ^ -0x434b886e68c498e2L) + 0x6c6bee919306e302L;
                                       value9 = (value9 ^ 0x1e8091dd8630dba4L) + 0x1a8091bc7def3664L;
                                    } else {
                                       value20 -= (long)count12 & 4294967295L;
                                       long value21 = ((long)count15 & 4294967295L) << 32 | value18;
                                       if (!this.checkTime(value20, value21)) {
                                          value8 = (value8 ^ 0x3a8a77efa4386b61L) - 0x157a11985bf82921L;
                                          value9 = (value9 ^ -0x456d9048fef80083L) - 0x49921c6cff03ecf3L;
                                       } else {
                                          count14--;
                                       }
                                    }
                                 }
                              }

                              curveAccumulator4.countArray[count13 + curveAccumulator4.count2] = 0;
                              int count24 = count13 + curveAccumulator4.count2;
                              int[] countArray5 = curveAccumulator4.countArray;
                              int count25 = this.computeCount(countArray5, countArray2, count14, count6, count24);
                              if (count25 + Integer.MIN_VALUE <= count17) {
                                 value8 = (value8 ^ 0x2fe4a0e804a77ee5L) - 0x22e46097fb6535a5L;
                                 value9 = (value9 ^ -0x7318263a05d1ad35L) - 0x73dea63bf61a5245L;
                              } else {
                                 int count26 = count13 + 1 + curveAccumulator4.count2;
                                 int[] countArray6 = curveAccumulator4.countArray;
                                 this.computeCount(countArray2, countArray6, count26);
                                 count14--;
                              }

                              countArray4[count13] = count14;
                           }

                           count13++;
                           value2 = (value8 ^ 0x7434690655bfec80L) + 0x14118bd284958ee9L;
                           value3 = (value9 ^ -0x764f631a4bf8af8cL) - 0x7380abffc5f80ecdL;
                        }

                        if (count13 >= count7 - 1) {
                           checkBlock: {
                              boolean flag4 = false;
                              int count27 = curveAccumulator4.countArray[count7 - 1 + curveAccumulator4.count2];
                              int count28 = count27 + Integer.MIN_VALUE;
                              int count29 = curveAccumulator4.countArray[count7 + curveAccumulator4.count2];
                              int count30;
                              int count31;
                              if (count27 != count9) {
                                 long value22 = (long)count27 << 32 | (long)count29 & 4294967295L;
                                 if (value22 < 0L) {
                                    long value23 = computeTimestampByTime(value22, count9);
                                    count30 = (int)(value23 & 4294967295L);
                                    count31 = (int)(value23 >>> 32);
                                 } else {
                                    count30 = (int)(value22 / value);
                                    count31 = (int)(value22 - (long)count30 * value);
                                 }

                                 if (count30 == 0) {
                                    break checkBlock;
                                 }
                              } else {
                                 count30 = -1;
                                 count31 = count27 + count29;
                                 flag4 = count31 + Integer.MIN_VALUE < count28;
                              }

                              if (!flag4) {
                                 long value24 = (long)curveAccumulator4.countArray[count7 + 1 + curveAccumulator4.count2] & 4294967295L;
                                 long value25 = ((long)count31 & 4294967295L) << 32 | value24;
                                 long value26 = ((long)count12 & 4294967295L) * ((long)count30 & 4294967295L);
                                 if (this.checkTime(value26, value25)) {
                                    count30--;
                                    int count32 = (int)(((long)count31 & 4294967295L) + value);
                                    if (((long)count32 & 4294967295L) >= value) {
                                       long value27 = value26 - ((long)count12 & 4294967295L);
                                       long value28 = ((long)count32 & 4294967295L) << 32 | value24;
                                       if (this.checkTime(value27, value28)) {
                                          count30--;
                                       }
                                    }
                                 }
                              }

                              curveAccumulator4.countArray[count7 - 1 + curveAccumulator4.count2] = 0;
                              int count33;
                              if (!flag) {
                                 int count34 = count7 - 1 + curveAccumulator4.count2;
                                 int[] countArray7 = curveAccumulator4.countArray;
                                 count33 = this.computeCountByIndexArray(countArray7, countArray2, count30, count6, count34);
                              } else {
                                 int count35 = count7 - 1 + curveAccumulator4.count2;
                                 int[] countArray8 = curveAccumulator4.countArray;
                                 count33 = this.computeCount(countArray8, countArray2, count30, count6, count35);
                              }

                              if (count33 + Integer.MIN_VALUE > count28) {
                                 if (flag) {
                                    int count36 = count7 - 1 + 1 + curveAccumulator4.count2;
                                    int[] countArray9 = curveAccumulator4.countArray;
                                    this.computeCount(countArray2, countArray9, count36);
                                 }

                                 count30--;
                              }

                              countArray4[count7 - 1] = count30;
                           }

                           if (flag) {
                              if (count5 > 0) {
                                 curveAccumulator4.applyIndex(count5);
                              }

                              curveAccumulator4.updateState3();
                           }

                           curveAccumulator3.updateState3();
                           return flag ? curveAccumulator4 : null;
                        }

                        value2 = (value2 ^ -0x779c0224d4d53facL) + 0x2a67a59d1ea1c3f6L;
                        value3 = (value3 ^ 0x2e8275ffcce38348L) + 0x53f67c0595f4d591L;
                        continue;
                     }

                     value2 = (value2 ^ 0x260e7019a11b2172L) + 0x613bb655e521fdb2L;
                     value3 = (value3 ^ 0x5504f288a0d24d6bL) + 0x3f3782317c89004dL;
                     break innerLoop;
                  }

                  value2 = (value2 ^ -0x145554fa23a257d9L) - 0x356ef7b870e6e04fL;
                  value3 = (value3 ^ -0x5b869374b3cf9f3dL) + 0x7821df2b45d1dbeaL;
                  break innerLoop;
               }

               count10++;
               count7++;
               value2 = (value2 ^ -0x64d2db8c96f018d8L) - 0x66ad4f189bc2d1ddL;
               value3 = (value3 ^ -0x20b4ccb58a40a57aL) - 0x609867fd67aaf89eL;
               break;
            }

            if (count10 >= this.count4 + 1) {
               long value29 = (value2 ^ 0x3d4dba233cb37c2L) - 0x15c300b8b8fe223dL;
               long value30 = (value3 ^ 0x3313191c4fad59f0L) + 0xae041d2bec70608L;
               countArray3[this.count4 + 1] = count8 << count5;
               value2 = (value29 ^ -0x1a0b04daaeb7b9b0L) + 0x370a920a2f15c18eL;
               value3 = (value30 ^ -0x514afe11d592d4e5L) - 0x25c57f2b07fe1703L;
            } else {
               value2 = (value2 ^ 0x452ce11f94595d63L) - 0x1bb2a461637b28d6L;
               value3 = (value3 ^ -0x6a738fd10657ebeeL) + 0xd511bc5615b7497L;
            }
         }

         count19 = curveAccumulator4.count4;
         value2 = (value2 ^ -0x846976fcc247c36L) - 0x52f780f40fd97d0bL;
         value3 = (value3 ^ 0x23f0f80c36b826e5L) + 0x70087537c982dddeL;
      }
   }

   public final int computeCount(CurveAccumulator curveAccumulator2) {
      int count5 = 0;
      int[] entry = null;
      int count6 = 0;
      int count7 = 0;
      int count8 = 0;
      count5 = curveAccumulator2.count4;
      if (this.count4 < count5) {
         return -1;
      } else if (this.count4 > count5) {
         return 1;
      } else {
         entry = curveAccumulator2.countArray;
         count6 = this.count2;

         for (int index = curveAccumulator2.count2; count6 < this.count4 + this.count2; index++) {
            count7 = this.countArray[count6] + Integer.MIN_VALUE;
            count8 = entry[index] + Integer.MIN_VALUE;
            if (count7 < count8) {
               return -1;
            }

            if (count7 > count8) {
               return 1;
            }

            count6++;
         }

         return 0;
      }
   }

   private void applyIndex2(int count5) {
      if (this.count4 >= count5) {
         this.count2 = this.count2 + (this.count4 - count5);
         this.count4 = count5;
      }
   }

   private int computeCount(CurveAccumulator curveAccumulator2, int count5) {
      int count6 = 0;
      int count7 = 0;
      int[] entry = null;
      int count8 = 0;
      int count9 = 0;
      int count10 = 0;
      count6 = curveAccumulator2.count4;
      count7 = this.count4 - count5;
      if (count7 < count6) {
         return -1;
      } else if (count7 > count6) {
         return 1;
      } else {
         entry = curveAccumulator2.countArray;
         count8 = this.count2;

         for (int index = curveAccumulator2.count2; count8 < count7 + this.count2; index++) {
            count9 = this.countArray[count8] + Integer.MIN_VALUE;
            count10 = entry[index] + Integer.MIN_VALUE;
            if (count9 < count10) {
               return -1;
            }

            if (count9 > count10) {
               return 1;
            }

            count8++;
         }

         return 0;
      }
   }

   public long computeTimestamp(long value, CurveAccumulator curveAccumulator2) {
      long value2 = value;
      int count5 = 0;
      if (value != 0L) {
         if (this.count4 != 0) {
            if (value < 0L) {
               value2 = -value;
            }

            count5 = (int)(value2 >>> 32);
            curveAccumulator2.updateState();
            if (count5 != 0) {
               return this.resolveCurveAccumulatorByCurveAccumulator(value2, curveAccumulator2).getTimestamp();
            } else {
               int count6 = (int)value2;
               return (long)this.computeCount(count6, curveAccumulator2) & 4294967295L;
            }
         } else {
            curveAccumulator2.count4 = curveAccumulator2.count2 = 0;
            return 0L;
         }
      } else {
         throw new ArithmeticException("BigInteger divide by zero");
      }
   }

   public void applyIndexByIndex(int count5) {
      long value = 0x3ebfc8a28a294cb1L;

      while (true) {
         if ((int)(value % 3L) != 0) {
            this.applyIndex3(count5);
            break;
         }

         if (count5 <= 0) {
            break;
         }

         value = (value ^ -0x5ddfe5b4c5d28ac6L) - 0x40dc1a6cb41e5b42L;
      }
   }

   public CurveAccumulator resolveCurveAccumulator(CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3, boolean flag) {
      if (curveAccumulator2.count4 != 0) {
         if (this.count4 != 0) {
            int count5 = this.computeCount(curveAccumulator2);
            if (count5 >= 0) {
               if (count5 != 0) {
                  curveAccumulator3.updateState();
                  if (curveAccumulator2.count4 != 1) {
                     if (this.count4 >= 6) {
                        int count6 = Math.min(this.getCount(), curveAccumulator2.getCount());
                        if (count6 >= 96) {
                           CurveAccumulator curveAccumulator4 = new CurveAccumulator(this);
                           CurveAccumulator curveAccumulator5 = new CurveAccumulator(curveAccumulator2);
                           curveAccumulator4.applyIndex(count6);
                           curveAccumulator5.applyIndex(count6);
                           CurveAccumulator curveAccumulator6 = curveAccumulator4.resolveCurveAccumulator(curveAccumulator5, curveAccumulator3);
                           curveAccumulator6.applyIndex3(count6);
                           return curveAccumulator6;
                        }
                     }

                     return this.resolveCurveAccumulatorByCurveAccumulator(curveAccumulator2, curveAccumulator3, flag);
                  } else {
                     int count7 = curveAccumulator2.countArray[curveAccumulator2.count2];
                     int count8 = this.computeCount(count7, curveAccumulator3);
                     if (!flag) {
                        return null;
                     } else {
                        return count8 != 0 ? new CurveAccumulator(count8) : new CurveAccumulator();
                     }
                  }
               } else {
                  curveAccumulator3.countArray[0] = curveAccumulator3.count4 = 1;
                  curveAccumulator3.count2 = 0;
                  return !flag ? null : new CurveAccumulator();
               }
            } else {
               curveAccumulator3.count4 = curveAccumulator3.count2 = 0;
               return !flag ? null : new CurveAccumulator(this);
            }
         } else {
            curveAccumulator3.count4 = curveAccumulator3.count2 = 0;
            return !flag ? null : new CurveAccumulator();
         }
      } else {
         throw new ArithmeticException("BigInteger divide by zero");
      }
   }

   public void applyCurveAccumulator(CurveAccumulator curveAccumulator2) {
      int count5 = 0;
      int count6 = 0;
      long value = 0x6f99c49735d26a84L;
      long value2 = 0x63290a7ca6ab2ed7L;
      count5 = this.count4;
      value = (value ^ -0x4c08545405d75621L) + 0x5e38b277d8e38f76L;
      value2 = (value2 ^ -0x5fdebddb91fb0221L) + 0x692defe58ba74662L;
      count6 = curveAccumulator2.count4;
      value = (value ^ -0x2f38360b3a634c52L) - 0x7360c26998207165L;
      value2 = (value2 ^ -0x12a9862d4216a08fL) - 0x3030389e9be718bbL;
      long value3;
      long value4;
      int count7;
      if (this.count4 <= curveAccumulator2.count4) {
         value3 = (value ^ -0x703cd1aa3ac67d4fL) + 0x52a5410c1ed778acL;
         value4 = (value2 ^ -0x79fb7e330b3f61b0L) - 0x36a39fd0d0e6a4e2L;
         count7 = curveAccumulator2.count4;
      } else {
         value = (value ^ -0x38ea99d91a2cc135L) - 0x64acf76101fde37aL;
         value2 = (value2 ^ -0x23c49a4d15f20cbdL) - 0x6c7a844abfa9cfd5L;
         count7 = this.count4;
         value3 = (value ^ 0x66def71b7a11649bL) + 0x1db190fada115b8bL;
         value4 = (value2 ^ -0x536cdaa696daa4c9L) - 0x6b72e53698e3472bL;
      }

      int count8 = count7;
      long value5;
      long value6;
      int[] countArray2;
      if (this.countArray.length >= count8) {
         value5 = (value3 ^ -0x68b2b07d95c9a312L) + 0x6f434981ea2e339eL;
         value6 = (value4 ^ 0xb3a186afdedd879L) - 0xb1a08292dabc7e9L;
         countArray2 = this.countArray;
      } else {
         countArray2 = new int[count8];
         value5 = (value3 ^ -0x35089b85f641b3ffL) - 0x3636dc65f859bbffL;
         value6 = (value4 ^ 0x62ef9130416c5aa4L) + 0x5e116f303ed3b964L;
      }

      int[] countArray3 = countArray2;
      int count9 = countArray3.length - 1;
      long value7 = 0L;

      long value8;
      long value9;
      while (true) {
         if (count5 <= 0) {
            value8 = (value5 ^ -0xdb1d84927019dbL) - 0x68e462651489221bL;
            value9 = (value6 ^ 0x345af0a5d7bcbbe3L) + 0xbc6d09da8a358a1L;
            break;
         }

         if (count6 <= 0) {
            value8 = (value5 ^ -0x1aea78502850fc14L) - 0x63157e91ca68fd64L;
            value9 = (value6 ^ -0x64a0da8954270aa1L) + 0x64c11aeaa447133dL;
            break;
         }

         long value10 = ((long)this.countArray[--count5 + this.count2] & 4294967295L) + ((long)curveAccumulator2.countArray[--count6 + curveAccumulator2.count2] & 4294967295L) + value7;
         countArray3[count9--] = (int)value10;
         value7 = value10 >>> 32;
         value5 = (value5 ^ -0x3813fae1d5bce6b7L) - 0x411bfd01d5bd06c7L;
         value6 = (value6 ^ 0x6d177b8c289e58cbL) + 0x530955742781b7c9L;
      }

      while (count5 > 0) {
         count5--;
         long value11;
         long value12;
         if (value7 != 0L) {
            value11 = (value8 ^ 0x1d33b29a0b527b70L) - 0xaf3b185f6ada4d0L;
            value12 = (value9 ^ 0x71a6c9f5720470d1L) + 0x4f5ac76d4dfb8fafL;
         } else if (countArray3 != this.countArray) {
            value11 = (value8 ^ -0x4e7be3c0ca2f625aL) + 0x507bfbdf13cf7466L;
            value12 = (value9 ^ 0x379c0a8c9c7bcde1L) + 0x984067473e64a9fL;
         } else {
            if (count9 == count5 + this.count2) {
               return;
            }

            value11 = (value8 ^ 0x20c6de74f9141698L) - 0x2046466ca8f41278L;
            value12 = (value9 ^ 0x1c6f3c06c7526c64L) - 0x1c6eec05473253dcL;
         }

         long value13 = ((long)this.countArray[count5 + this.count2] & 4294967295L) + value7;
         countArray3[count9--] = (int)value13;
         value7 = value13 >>> 32;
         value8 = (value11 ^ -0x3cfe55d01445eb3eL) - 0x3d019a119656143eL;
         value9 = (value12 ^ -0x489169a1e36e49f3L) - 0x776d963e0c6fa9f5L;
      }

      while (count6 > 0) {
         long value14 = ((long)curveAccumulator2.countArray[--count6 + curveAccumulator2.count2] & 4294967295L) + value7;
         countArray3[count9--] = (int)value14;
         value7 = value14 >>> 32;
      }

      if (value7 > 0L) {
         if (countArray3.length >= ++count8) {
            countArray3[count9--] = 1;
         } else {
            int[] countArray4 = new int[count8];
            System.arraycopy(countArray3, 0, countArray4, 1, countArray3.length);
            countArray4[0] = 1;
            countArray3 = countArray4;
         }
      }

      this.countArray = countArray3;
      this.count4 = count8;
      this.count2 = countArray3.length - count8;
   }

   public boolean isActive() {
      return this.count4 == 1 && this.countArray[this.count2] == 1;
   }

   public static long computeTimestampByTime(long value, int count5) {
      long value2 = 0L;
      long value3 = 0L;
      long value4 = 0L;
      long value5 = 0x1ceca7d44abee1d4L;
      long value6 = -0x17bce7d760249266L;

      while (true) {
         int count6 = (int)(value5 % 14L);
         if (count6 == 0) {
            return value3 << 32 | value4 & 4294967295L;
         }

         long value7;
         long value8;
         if (count6 != 7) {
            if (count6 == 9) {
               value4 = (value >>> 1) / (value2 >>> 1);
               value5 = (value5 ^ 0x110926add2ae9045L) + 0x582e54246b5e4e1dL;
               value6 = (value6 ^ 0x67ad54f905318053L) + 0x4fe13c50c9d68ee2L;
               continue;
            }

            if (count6 != 6) {
               if (count6 != 1) {
                  if (count6 != 13) {
                     if (count6 != 4) {
                        if (count6 != 2) {
                           if (count6 != 5) {
                              if (count6 != 11) {
                                 if (count6 != 10) {
                                    if (count6 != 12) {
                                       if (count6 != 3) {
                                          value3 += value2;
                                          value5 = (value5 ^ 0x7b5b6362834c9167L) - 0x22fe3327b8999787L;
                                          value6 = (value6 ^ 0x5e0f93053928a043L) - 0xbfd58b0e03b77a6L;
                                       } else {
                                          value3 = value - value4 * value2;
                                          value5 = (value5 ^ 0x4d1c456debf1ff4eL) - 0x272a8328e6bae715L;
                                          value6 = (value6 ^ -0x25abab2474652f40L) - 0x572f481c29cac4c6L;
                                       }
                                    } else if (value2 != (value6 % 696524L ^ -33533L)) {
                                       value5 = (value5 ^ 0x7acfa60f7e72b67cL) - 0x3d74856f62780693L;
                                       value6 = (value6 ^ -0x10b963b90de20649L) + 0x17f1302b7513436L;
                                    } else {
                                       value5 = (value5 ^ 0xe0970a555e572aL) + 0x472a7deb91bcae1bL;
                                       value6 = (value6 ^ 0x1d2e874ab1c52ef3L) - 0x4f70ba97d96a2619L;
                                    }
                                 } else {
                                    value3 = 0L;
                                    value5 = (value5 ^ -0x60847363aafbd722L) + 0x641af591eccf9a68L;
                                    value6 = (value6 ^ -0x2a663cebdd76e03eL) + 0x1493e671918a957fL;
                                 }
                              } else {
                                 value4 = (long)((int)value);
                                 value5 = (value5 ^ 0x70ce19071bdce8dbL) + 0x365bea9f2ee1dcb6L;
                                 value6 = (value6 ^ -0x4667d853118868f7L) - 0x58d8a256b786c37bL;
                              }
                           } else {
                              value4 += value6 % 485544L ^ 211927L;
                              value5 = (value5 ^ 0x38c6cb090c3eb3e8L) + 0x52eb8de5e2e538f6L;
                              value6 = (value6 ^ -0x462d78e50a6fcfedL) + 0x47a3ca41c3cea713L;
                           }
                        } else {
                           value2 = (long)count5 & 4294967295L;
                           value5 = (value5 ^ -0x556957fe1c2c5399L) + 0x765cd2ace5922c87L;
                           value6 = (value6 ^ -0x2ca969c51d0d457fL) + 0x500bfa0f348afbc3L;
                        }
                     } else {
                        value4 -= value6 % 288642L ^ -160783L;
                        value5 = (value5 ^ -0x5625aa15ad0b4888L) + 0x1c7705758004cbfdL;
                        value6 = (value6 ^ 0x20694a36c3676cf6L) - 0x1a6b2e00d1cc4357L;
                     }
                  } else {
                     value3 -= value2;
                     value5 = (value5 ^ -0x3d66e6dadf5ab8afL) + 0x54a884441517b0e1L;
                     value6 = (value6 ^ -0x32d4386d5eaa92eL) + 0x16ad1130bab9aebbL;
                  }
                  continue;
               }

               value5 = (value5 ^ -0x4d0aab82cddc80a2L) + 0x62811485b9f22d17L;
               value6 = (value6 ^ -0x60ac82a78d4ba070L) + 0x2c798f7d0d73e2a3L;
            }

            if (value3 < 0L) {
               value5 = (value5 ^ 0x31b0bc55c1e03825L) - 0x2f306f535cff6877L;
               value6 = (value6 ^ 0x6e700aa26fa5a054L) - 0x5faf2d4427365b79L;
               continue;
            }

            value7 = (value5 ^ 0x728e480e3c25c167L) - 0x652a28284ff07cc0L;
            value8 = (value6 ^ 0x449f748b9b8f6166L) + 0x25ac9763f46bafc7L;
         } else {
            value7 = (value5 ^ 0x7287741ca13a7ddcL) + 0xdeae2e95c17d052L;
            value8 = (value6 ^ 0x79adf191ee31092dL) + 0x3dd4fe00825fbc38L;
         }

         if (value3 < value2) {
            return value3 << 32 | value4 & 4294967295L;
         }

         value5 = (value7 ^ -0x5d79dcbc7bdc85c3L) + 0x5fcc400ef3bcb6c1L;
         value6 = (value8 ^ -0x7898958e88713802L) - 0x6ab1a11236ee262aL;
      }
   }

   private final int computeCount(int count5) {
      return this.countArray[this.count2 + count5];
   }

   private final int getCount() {
      int count5 = 0;
      int count6 = 0;
      long value = 0x1f63b1a4bd059498L;
      long value2 = -0x1a1fb9acc27560daL;

      while (true) {
         int count7 = (int)(value % 9L);
         if (count7 == 1) {
            break;
         }

         long value3;
         long value4;
         searchBlock: {
            if (count7 != 3) {
               if (count7 == 2) {
                  count5 = this.count4 - 1;
                  value = (value ^ -0x45d51a4af4f367c4L) + 0x6c54c0d84dd7c13fL;
                  value2 = (value2 ^ 0x777e0e3667b3702cL) - 0x6d5dc1a80fe14dc8L;
                  continue;
               }

               if (count7 == 4) {
                  if (this.countArray[count5 + this.count2] == 0) {
                     value = (value ^ -0x15602ee46d1b9cf6L) + 0x63eaee2bb50b1a7fL;
                     value2 = (value2 ^ 0x6402e8ade5016eebL) + 0x494e46bc03df74eL;
                     continue;
                  }

                  value3 = (value ^ 0xd076bdda9dfd51aL) + 0x426264e5a9efcc8fL;
                  value4 = (value2 ^ -0x15810ec28def617aL) - 0x7db71404d6d2384fL;
                  break searchBlock;
               }

               if (count7 != 5) {
                  if (count7 != 8) {
                     if (count6 != 0) {
                        return (this.count4 - 1 - count5 << 5) + Integer.numberOfTrailingZeros(count6);
                     }

                     return -1;
                  }

                  if (this.count4 == 0) {
                     break;
                  }

                  value = (value ^ 0x68a8caee2afa6dbfL) - 0x45a9ebb31b918fdL;
                  value2 = (value2 ^ -0x2ff2529f0a852113L) - 0x51d6df39ff869d6cL;
                  continue;
               }

               count5--;
               value = (value ^ 0x2afc328e5ace5a08L) - 0x301a7a67dcb39c7eL;
               value2 = (value2 ^ 0x1ad07358189ee573L) - 0x1bf2203279fa7e9aL;
            }

            if (count5 > 0) {
               value = (value ^ 0xe4152d2eae0d2bdL) - 0x3a1a1eedc6ced592L;
               value2 = (value2 ^ 0x19899d87c21346ebL) - 0xce7ff64b7bd3a3dL;
               continue;
            }

            value3 = (value ^ -0x67ada6487e00f264L) - 0x5ec75cb06dc1a4eeL;
            value4 = (value2 ^ 0x8b9a5af49fb8f1dL) + 0x2c7a35792968d680L;
         }

         count6 = this.countArray[count5 + this.count2];
         value = (value3 ^ 0x5b86b3015be3342bL) + 0x4f8548395d7f2f92L;
         value2 = (value4 ^ 0x5f9e3174acc97ec4L) - 0x307a4c10efbf493dL;
      }

      return -1;
   }

   private CurveAccumulator resolveCurveAccumulator(CurveAccumulator curveAccumulator2) {
      CurveAccumulator curveAccumulator3 = curveAccumulator2;
      CurveAccumulator curveAccumulator4 = null;
      CurveAccumulator curveAccumulator5 = null;
      int count5 = 0;
      int count6 = 0;
      long value2 = 0x2336ed546efe1320L;
      long value3 = 0x7fccd208432b36a4L;
      curveAccumulator4 = this;
      value2 = (value2 ^ 0x7caa152b47a36aceL) - 0x3ce377b926d165f1L;
      value3 = (value3 ^ -0x461d11a4b6988af0L) - 0x7fd00c56f620e187L;
      curveAccumulator5 = new CurveAccumulator();
      value2 = (value2 ^ -0x7e675737a5f9618bL) + 0x6517f315fbcbdbb7L;
      value3 = (value3 ^ 0x32ab7289c3091d40L) - 0x45136f0342405aa8L;
      count5 = this.getCount();
      value2 = (value2 ^ -0x582ab67dd6936cb2L) + 0x613f9c700e5b5603L;
      value3 = (value3 ^ 0x46ac64afa1edb253L) - 0x2d10131f452be80cL;
      count6 = curveAccumulator2.getCount();
      value2 = (value2 ^ -0x638d81cbfbf2aa86L) - 0x30032b79a8f0b899L;
      value3 = (value3 ^ 0x57ba0362261f53aeL) + 0x4471d919167daf9cL;
      long value4;
      long value5;
      int count7;
      if (count5 >= count6) {
         value4 = (value2 ^ -0x4739528b997d634bL) + 0x46ddcaee7f79a1c0L;
         value5 = (value3 ^ -0x65e6e894f6bd928eL) - 0x633b7742c4f61c29L;
         count7 = count6;
      } else {
         value2 = (value2 ^ 0x1c05412ee70400e0L) - 0x14e490bb020bda15L;
         value3 = (value3 ^ -0x75895db5de9a5ecbL) - 0x52eb0c619cdc686cL;
         count7 = count5;
         value4 = (value2 ^ 0x177aee8bd7f2d271L) - 0xea5e97628ae2e71L;
         value5 = (value3 ^ 0x3ef37e9dfd4be9eaL) - 0x19b2ba7bb4c6a6daL;
      }

      int count8 = count7;
      if (count8 == 0) {
         value4 = (value4 ^ -0x192038dbc840716cL) + 0x61bd3edc3784766cL;
         value5 = (value5 ^ -0x5732dcc6883b3f52L) + 0x583b22e8d0bc4062L;
      } else {
         this.applyIndex(count8);
         curveAccumulator2.applyIndex(count8);
      }

      long value6;
      long value7;
      boolean flag;
      if (count8 != count5) {
         value6 = (value4 ^ 0x2558b96c487cace1L) + 0x2338692447c3941fL;
         value7 = (value5 ^ 0x1b962c1291d9d909L) + 0x972140d6ea72909L;
         flag = false;
      } else {
         flag = true;
         value6 = (value4 ^ 0x29a885a250a5bf3eL) + 0x26a87d62509aa4c2L;
         value7 = (value5 ^ 0x734bae8276400d49L) + 0x30b5957dd23ffcc9L;
      }

      boolean flag2 = (boolean)flag;
      long value8;
      long value9;
      CurveAccumulator curveAccumulator6;
      if (!flag2) {
         value8 = (value6 ^ -0x2611d6a88641da91L) + 0x36edd92d79861a91L;
         value9 = (value7 ^ -0x3aabf2bf0548f368L) - 0x454c0ac0bab30388L;
         curveAccumulator6 = this;
      } else {
         curveAccumulator6 = curveAccumulator2;
         value8 = (value6 ^ -0x1cfd91fe17d47ee7L) + 0x5d0195ffe8187ee7L;
         value9 = (value7 ^ -0x542d73c9e0ce3c9bL) + 0x592e73e9e8cf3e9dL;
      }

      CurveAccumulator curveAccumulator7 = curveAccumulator6;
      long value10;
      long value11;
      int count9;
      if (!flag2) {
         value10 = (value8 ^ 0x653526fcf45b851fL) - 0x1ced216b0c57851fL;
         value11 = (value9 ^ 0x398364f5799d947eL) - 0x1882a453399c8c6eL;
         count9 = 1;
      } else {
         count9 = -1;
         value10 = (value8 ^ -0x468fa0d74b86ca3bL) - 0x68b039174c38d4c5L;
         value11 = (value9 ^ 0x21788a255203ecacL) - 0x2137862351fea39cL;
      }

      int count10;
      while ((count10 = curveAccumulator7.getCount()) >= 0) {
         curveAccumulator7.applyIndex(count10);
         long value12;
         long value13;
         if (count9 <= 0) {
            value12 = (value10 ^ -0x7ae21a962dcc70c3L) - 0x7d04e2d82def8b3dL;
            value13 = (value11 ^ -0x6b0f0c3eaaad8fb7L) - 0x73af2d3f4cce3037L;
            curveAccumulator3 = curveAccumulator7;
         } else {
            curveAccumulator4 = curveAccumulator7;
            value12 = (value10 ^ 0x2dcb6141173be7c3L) + 0x2ac8a13f1708dd3dL;
            value13 = (value11 ^ -0x1fb58353c552c208L) - 0x6049bcabfaacf2e8L;
         }

         long value14;
         long value15;
         if (curveAccumulator4.count4 >= 2) {
            value14 = (value12 ^ 0x5dd5b154baab5aecL) - 0x454c8ed34566b5ecL;
            value15 = (value13 ^ -0x57706b5d4d8854e2L) + 0x58796b5d8e0d96f2L;
         } else {
            if (curveAccumulator3.count4 < 2) {
               int count11 = curveAccumulator4.countArray[curveAccumulator4.count2];
               int count12 = curveAccumulator3.countArray[curveAccumulator3.count2];
               int count13 = computeCount(count11, count12);
               curveAccumulator5.countArray[0] = count13;
               curveAccumulator5.count4 = 1;
               curveAccumulator5.count2 = 0;
               if (count8 > 0) {
                  curveAccumulator5.applyIndex3(count8);
               }

               return curveAccumulator5;
            }

            value14 = (value12 ^ 0x4a58b84a4b28984aL) - 0x39c797b5b4e8784aL;
            value15 = (value13 ^ 0x50a021989bde2584L) + 0x50a0208764a6257cL;
         }

         if ((count9 = curveAccumulator4.computeCountByCurveAccumulator(curveAccumulator3)) == 0) {
            break;
         }

         long value16 = (value14 ^ -0x681b903d153fd53cL) + 0x70e397c2ea401a3cL;
         long value17 = (value15 ^ -0x145fed32e4a30bc6L) - 0x66600e4b1adbac2aL;
         long value18;
         long value19;
         CurveAccumulator curveAccumulator8;
         if (count9 < 0) {
            value18 = (value16 ^ -0x65b1b207923ba8acL) + 0x764db5986d3c4cacL;
            value19 = (value17 ^ -0x648501cafd9cad5dL) - 0x77393a1301df51a1L;
            curveAccumulator8 = curveAccumulator3;
         } else {
            curveAccumulator8 = curveAccumulator4;
            value18 = (value16 ^ -0x432668a19fccaf07L) - 0x644870c99feeaff9L;
            value19 = (value17 ^ -0x4030498f3ecc0b44L) + 0x65398e2f3ed15454L;
         }

         curveAccumulator7 = curveAccumulator8;
         value10 = (value18 ^ -0x1c7e93f42ac41c82L) + 0x5c7e9409d508e082L;
         value11 = (value19 ^ 0x6156266ebbaca758L) + 0x1eb21e118454a2b8L;
      }

      if (count8 > 0) {
         curveAccumulator4.applyIndex3(count8);
      }

      return curveAccumulator4;
   }

   public CurveAccumulator resolveCurveAccumulator(CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3) {
      return this.resolveCurveAccumulator(curveAccumulator2, curveAccumulator3, true);
   }

   public void applyIndex(int count5) {
      int count6 = 0;
      int count7 = 0;
      int count8 = 0;
      long value2 = 0x3db824ca3ffe9dfaL;
      long value3 = -0x5298dd4f2625bb80L;

      while (true) {
         int count9 = (int)(value2 % 11L);
         if (count9 != 10) {
            if (count9 == 5) {
               if (count7 != 0) {
                  long value4 = (value2 ^ -0x8eb82dcaf97f684L) - 0x7ebbfe6ff2e040fcL;
                  long value5 = (value3 ^ 0x42e6f88d23035617L) + 0x5d6b235b8e9bf152L;
                  count8 = SignumComparable.numberOfLeadingZeros(this.countArray[this.count2]);
                  value2 = (value4 ^ -0x2cd7c2103e919b1aL) - 0x6cc743312a5c1506L;
                  value3 = (value5 ^ -0xdb65ecceaded0f8L) + 0x53cb12e82d7de862L;
                  continue;
               }
               break;
            }

            if (count9 == 0) {
               int count10 = 32 - count7;
               this.applyIndex5(count10);
               value2 = (value2 ^ 0x7adab9c9e873d2d3L) - 0x1043c3ec4b18b986L;
               value3 = (value3 ^ -0x725481a01fccc7f6L) + 0x5b01a4b59b84a6dfL;
               continue;
            }

            if (count9 == 7) {
               return;
            }

            if (count9 == 4) {
               this.count4--;
               return;
            }

            if (count9 == 8) {
               count7 = count5 & 31;
               value2 = (value2 ^ -0x784d08c61b0717edL) - 0x38f32db389ab2ce1L;
               value3 = (value3 ^ 0x73da5bdaa9618ac9L) - 0x5bfc3f2d4d67b13dL;
               continue;
            }

            if (count9 == 2) {
               break;
            }

            if (count9 == 1) {
               return;
            }

            if (count9 == 3) {
               if (count7 >= count8) {
                  value2 = (value2 ^ 0xff94cf2fbecefadL) + 0xae8a15e4aa31d05L;
                  value3 = (value3 ^ -0x243c09280d2a35a8L) - 0x6c79190bbb27bbe8L;
                  continue;
               }

               this.applyIndex7(count7);
               return;
            }

            if (count9 != 9) {
               this.count4 -= count6;
               value2 = (value2 ^ -0x5faabd777087b5e0L) + 0x678d50c82903fa61L;
               value3 = (value3 ^ 0x683b4df177cf6c79L) - 0x44e54fcca41cc22fL;
               continue;
            }

            if (this.count4 != 0) {
               long value6 = (value2 ^ -0x2c44e46897afddefL) + 0x309d489f225c15a5L;
               long value7 = (value3 ^ -0x339f59aeb32783baL) - 0x56b94d941ea35220L;
               count6 = count5 >>> 5;
               value2 = (value6 ^ -0xd5d233bd2f6ff7eL) + 0x25a4bc8ee3624d85L;
               value3 = (value7 ^ 0x5e5c771168c0dd3aL) + 0x4a1e9e53c45c634cL;
               continue;
            }
         }

         return;
      }
   }

   private SignumComparable resolveSignumComparable(int count5) {
      int count6 = 0;
      long value = 0x4f818a5a0e2bab2fL;
      long value2 = 0x61555ca0a9a800bcL;

      while (true) {
         byte byteValue;
         while (true) {
            int count7 = (int)(value % 9L);
            if (count7 != 5) {
               if (count7 == 8) {
                  return SignumComparable.signumComparable5;
               }

               long value3;
               long value4;
               searchBlock: {
                  if (count7 != 3) {
                     if (count7 != 4) {
                        if (count7 == 2) {
                           byte byteValue2 = 1;
                           return this.resolveSignumComparableByIndex(byteValue2);
                        }

                        if (count7 == 1) {
                           count6 = count5;
                           value = (value ^ 0x289958de2f948931L) + 0x2e19625b96f9d87fL;
                           value2 = (value2 ^ 0x2a3796395a72171bL) + 0x36f1988055fc210L;
                           continue;
                        }

                        if (count7 == 6) {
                           if (this.count4 >= count5) {
                              value = (value ^ 0x1e9215170125aab4L) + 0x8e3cf1433182b84L;
                              value2 = (value2 ^ -0x5eb789efafeb4cf2L) + 0x5ec29eaba78db41L;
                           } else {
                              value = (value ^ 0x6ac379e50b917ab2L) - 0x2fb57efe14bf21ecL;
                              value2 = (value2 ^ 0x6c1e99f39e8d8389L) - 0xce48dc0402cdc71L;
                           }
                           continue;
                        }

                        if (count7 == 0) {
                           if (!this.isActive2()) {
                              value = (value ^ 0x6767a20c0cc0d1aaL) - 0xeaf08bdd1d9dfd8L;
                              value2 = (value2 ^ -0xcb348cc238a2d0dL) - 0x6e13c06403b7c030L;
                              continue;
                           }

                           return SignumComparable.signumComparable5;
                        }

                        if (this.countArray[this.count2 + this.count4 - count6] == 0) {
                           value = (value ^ -0x9ab4cf1490c1f90L) + 0x4518d2aca87fa54fL;
                           value2 = (value2 ^ -0x6be3c181770880aL) - 0x75e22e54ab11cca5L;
                           continue;
                        }

                        value3 = (value ^ 0x2f1f65a3c4adcabaL) + 0x1dd07c519a3d8e85L;
                        value4 = (value2 ^ 0x6d4e7fe31e138b0cL) - 0x1e0b2587c7d613fL;
                        break searchBlock;
                     }
                  } else {
                     count6--;
                     value = (value ^ -0x6478c97151849ee6L) - 0x3212c19269242743L;
                     value2 = (value2 ^ 0x3c6f25e33d84fe44L) - 0x14d2a554912ec7bL;
                  }

                  if (count6 > 0) {
                     value = (value ^ 0x14fd77ab69ef35c3L) - 0x17e2223bfd3fb093L;
                     value2 = (value2 ^ -0x4f9abd8c2efe459fL) - 0x1d500c66e1e2d375L;
                     continue;
                  }

                  value3 = (value ^ -0x132712c2a395c73fL) + 0x5ec8850022d51f8aL;
                  value4 = (value2 ^ -0x5f57f2b03063c704L) - 0x6b2db6c85adc2e15L;
               }

               if (count6 > 0) {
                  value = (value3 ^ -0x1ba762950b70b598L) + 0x1adbc64285a2b59dL;
                  value2 = (value4 ^ 0x6b77d6d4da7ec327L) + 0x180397174e3ba971L;
                  continue;
               }

               byteValue = 0;
               break;
            }

            byteValue = 1;
            break;
         }

         byte byteValue3 = byteValue;
         return new SignumComparable(Arrays.copyOfRange(this.countArray, this.count2 + this.count4 - count6, this.count2 + this.count4), byteValue3);
      }
   }

   private final long computeTimestamp(int count5) {
      return (long)this.countArray[this.count2 + count5] & 4294967295L;
   }

   private long getTimestamp() {
      long value = 0L;

      assert this.count4 <= 2 : "this MutableBigInteger exceeds the range of long";

      if (this.count4 != 0) {
         value = (long)this.countArray[this.count2] & 4294967295L;
         return this.count4 != 2 ? value : value << 32 | (long)this.countArray[this.count2 + 1] & 4294967295L;
      } else {
         return 0L;
      }
   }

   private int[] getIndexArray() {
      return this.count2 <= 0 && this.countArray.length == this.count4
         ? this.countArray
         : Arrays.copyOfRange(this.countArray, this.count2, this.count2 + this.count4);
   }

   private int computeCount(int count5, int count6, int[] countArray2, int count7) {
      long value = 0L;
      long value2 = 0L;
      value = 0L;
      value2 = ((long)count6 & 4294967295L) + ((long)countArray2[1 + count7] & 4294967295L);
      countArray2[1 + count7] = (int)value2;
      value2 = ((long)count5 & 4294967295L) + ((long)countArray2[count7] & 4294967295L) + value;
      countArray2[count7] = (int)value2;
      long value3 = value2 >>> 32;
      return (int)value3;
   }

   private void applyIndex4(int count5) {
      if (count5 > this.countArray.length) {
         this.countArray = new int[count5];
      }

      Arrays.fill(this.countArray, -1);
      this.count2 = 0;
      this.count4 = count5;
   }

   public int computeCount(int count5, CurveAccumulator curveAccumulator2) {
      long value = 0L;
      long value2 = 0L;
      int count6 = 0;
      int count7 = 0;
      long value3 = 0xc55071e0a769a47L;
      long value4 = -0x4301e11e83a0594fL;
      value = (long)count5 & 4294967295L;
      value3 = (value3 ^ -0x55af1f91097fbb80L) + 0x6917438a66273d5fL;
      value4 = (value4 ^ 0x311c469573b3edebL) - 0x7ac12bf471dac382L;
      if (this.count4 != 1) {
         long value5 = (value3 ^ -0x41aa58b8ae01fa77L) + 0x689bd7b655b353f9L;
         long value6 = (value4 ^ 0xf219b7c5a9e320L) + 0x71c8f87463b51ab6L;
         if (curveAccumulator2.countArray.length >= this.count4) {
            value5 = (value5 ^ 0x3f8e8cdded6273ecL) - 0xc86843cdd5db09cL;
            value6 = (value6 ^ 0x528064a3347b30a1L) - 0x51801c62cba8cf61L;
         } else {
            curveAccumulator2.countArray = new int[this.count4];
         }

         curveAccumulator2.count2 = 0;
         curveAccumulator2.count4 = this.count4;
         int count8 = Integer.numberOfLeadingZeros(count5);
         int count9 = this.countArray[this.count2];
         long value7 = (long)count9 & 4294967295L;
         long value8;
         long value9;
         if (value7 >= value) {
            value8 = (value5 ^ 0xe3d9d0315c89e61L) + 0x20a6d00eb3779dfL;
            value9 = (value6 ^ -0x132853141ca24ff6L) + 0x1c50ab65633cb00aL;
            curveAccumulator2.countArray[0] = (int)(value7 / value);
            count9 = (int)(value7 - (long)curveAccumulator2.countArray[0] * value);
            value7 = (long)count9 & 4294967295L;
         } else {
            curveAccumulator2.countArray[0] = 0;
            value8 = (value5 ^ 0x1403dd17e642a2cfL) + 0xbfcad0d19c19e41L;
            value9 = (value6 ^ 0x467fcf1c4007eb34L) - 0x3e47b2e43ffd14ecL;
         }

         int count10 = this.count4;

         while (--count10 > 0) {
            long value10 = value7 << 32 | (long)this.countArray[this.count2 + this.count4 - count10] & 4294967295L;
            int count11;
            long value11;
            long value12;
            if (value10 < 0L) {
               value11 = (value8 ^ 0x33f81c47cd4d2753L) - 0x103813c2bd4add53L;
               value12 = (value9 ^ 0x1aadbbe79abcbca9L) - 0x1995679e66644359L;
               long value13 = computeTimestampByTime(value10, count5);
               count11 = (int)(value13 & 4294967295L);
               count9 = (int)(value13 >>> 32);
            } else {
               count11 = (int)(value10 / value);
               count9 = (int)(value10 - (long)count11 * value);
               value11 = (value8 ^ -0x242e04c585b528b8L) + 0x57aec72995b9b2c8L;
               value12 = (value9 ^ 0x65abcc99851f0070L) - 0x5a9bb4687b050030L;
            }

            curveAccumulator2.countArray[this.count4 - count10] = count11;
            value7 = (long)count9 & 4294967295L;
            value8 = (value11 ^ -0x4a6b0731f2742afaL) + 0x6d73c7b6037ab4faL;
            value9 = (value12 ^ 0x2e6621b6c7ce410dL) - 0x265de14db935bef5L;
         }

         curveAccumulator2.updateState3();
         return count8 <= 0 ? count9 : count9 % count5;
      } else {
         value3 = (value3 ^ 0x27e99aca0c3792ebL) + 0x19849905a2d56c74L;
         value4 = (value4 ^ -0x2f2f65286ef1ae28L) + 0x61483aa9fcc890acL;
         value2 = (long)this.countArray[this.count2] & 4294967295L;
         value3 = (value3 ^ 0xfcab43ff8eca6e1L) - 0xfb68505d3396ee3L;
         value4 = (value4 ^ 0x3b964ef9d81773b7L) - 0x620e59ec1ecc127dL;
         count6 = (int)(value2 / value);
         value3 = (value3 ^ -0xdb26b633c3cb1aL) + 0x585c99676e4cf746L;
         value4 = (value4 ^ -0x4696b5e7ab2436f4L) - 0x73a754fb217d8628L;
         count7 = (int)(value2 - (long)count6 * value);
         value3 = (value3 ^ 0x7e01510197b2cf4aL) - 0x17fe970f900bdd73L;
         value4 = (value4 ^ -0x42af920d7ebab377L) - 0x858f21aab87a69dL;
         curveAccumulator2.countArray[0] = count6;
         value3 = (value3 ^ 0x13748ed195d487d4L) - 0x4467f50352105904L;
         value4 = (value4 ^ 0x541ed5c0de5f4d33L) + 0x12931db973745139L;
         curveAccumulator2.count4 = count6 != 0 ? 1 : 0;
         curveAccumulator2.count2 = 0;
         return count7;
      }
   }

   private int computeCount(int[] countArray2, int[] countArray3, int count5, int count6, int count7) {
      int count8 = count7;
      long value = 0L;
      long value2 = 0L;
      int count9 = 0;
      long value3 = 0L;
      long value4 = 0L;
      long value5 = 0x7e64213846cb3616L;
      long value6 = 0x3b3f44104c2b5fb9L;

      while (true) {
         int count10 = (int)(value5 % 9L);
         if (count10 == 3) {
            value4 = (long)countArray2[count8] - value3;
            value5 = (value5 ^ -0x7a6e09a8d9dd6adbL) + 0x35fc30b082342209L;
            value6 = (value6 ^ 0x5bc73c4c79f7103L) + 0x32b21b9fbba9f3c7L;
         } else if (count10 != 2) {
            if (count10 != 5) {
               if (count10 != 6) {
                  if (count10 == 1) {
                     countArray2[count8--] = (int)value4;
                     value5 = (value5 ^ 0x15cdcd97a964d1bbL) + 0x5f2e0d029a4edeacL;
                     value6 = (value6 ^ 0x32a949fc4e1e898fL) + 0x3695a2cc83be0f89L;
                     continue;
                  }

                  if (count10 == 0) {
                     value2 = 0L;
                     value5 = (value5 ^ -0x69422f7574af5a91L) - 0x6c5e9d9c201e5ec7L;
                     value6 = (value6 ^ -0x4255b6ccddd99171L) - 0x3aa8871c5a7dc591L;
                     continue;
                  }

                  if (count10 == 7) {
                     value = (long)count5 & 4294967295L;
                     value5 = (value5 ^ 0x65db17c4ac0848bcL) + 0x40b7adccd333585aL;
                     value6 = (value6 ^ -0x3fe4f6fd80020021L) - 0x1b3c6d7b6577eb03L;
                     continue;
                  }

                  if (count10 != 4) {
                     count9 = count6 - 1;
                     value5 = (value5 ^ 0x7e3b4d9b88f0c2a6L) + 0x5f315fcce89c5637L;
                     value6 = (value6 ^ -0x3cdb767271dfa235L) - 0x59befec76292e56cL;
                     continue;
                  }
               } else {
                  long value7 = value3 >>> 32;
                  long value8;
                  long value9;
                  byte byteValue;
                  if ((value4 & 4294967295L) <= ((long)(~((int)value3)) & 4294967295L)) {
                     value8 = (value5 ^ 0x51cc894a722a6d07L) + 0x2f4c79362dea6afdL;
                     value9 = (value6 ^ -0x139b61eff5d71455L) + 0x141b8a0ff617eb57L;
                     byteValue = 0;
                  } else {
                     byteValue = 1;
                     value8 = (value5 ^ -0x778e2bad6df4b4b5L) - 0x786eabd271f4b6c7L;
                     value9 = (value6 ^ 0x7503cea0d167b35fL) - 0x730346a0ce9f4d5dL;
                  }

                  value2 = value7 + (long)byteValue;
                  count9--;
                  value5 = (value8 ^ 0x7a3e99138356df3aL) + 0x4dc7f9acb1beefd1L;
                  value6 = (value9 ^ -0x6bf201efc14d57a3L) - 0x6888ea323c94ab27L;
               }

               if (count9 < 0) {
                  return (int)value2;
               }

               value5 = (value5 ^ -0x3952b6044aa52121L) + 0x755f0320ff61f5acL;
               value6 = (value6 ^ -0x720734ddd8e3ba51L) - 0x2cf35ceb352c761L;
            } else {
               count8 += count6;
               value5 = (value5 ^ -0x282f4b1f6977a49L) - 0x239518bbff840c6fL;
               value6 = (value6 ^ 0xdc298ba23c3b923L) - 0x5e885fc3c2435b8eL;
            }
         } else {
            value3 = ((long)countArray3[count9] & 4294967295L) * value + value2;
            value5 = (value5 ^ 0x63d9e04dded144a7L) - 0x1cc8c3e12dc28804L;
            value6 = (value6 ^ -0x3d3f10c43d70fcb6L) + 0x6c2174b7ec0cf8c0L;
         }
      }
   }

   public int[] getIndexArrayAsIndexArray() {
      int[] entry = null;
      entry = new int[this.count4];

      for (int index = 0; index < this.count4; index++) {
         entry[index] = this.countArray[this.count2 + index];
      }

      return (int[])entry;
   }

   public boolean isActive2() {
      return this.count4 == 0;
   }

   public void applyIndexArray(int[] countArray2) {
      int count5 = 0;
      count5 = countArray2.length;
      if (this.countArray.length < count5) {
         this.countArray = new int[count5];
      }

      System.arraycopy(countArray2, 0, this.countArray, 0, count5);
      this.count4 = count5;
      this.count2 = 0;
   }

   private int computeCountByCurveAccumulator(CurveAccumulator curveAccumulator2) {
      CurveAccumulator curveAccumulator3 = curveAccumulator2;
      CurveAccumulator curveAccumulator4 = null;
      int count5 = 0;
      CurveAccumulator curveAccumulator5 = null;
      int count6 = 0;
      int count7 = 0;
      long value = 0L;
      long value2 = 0x6d2d71c8c381cbd5L;
      long value3 = -0x62fcb702b3879371L;

      while (true) {
         int count8 = (int)(value2 % 19L);
         if (count8 != 12) {
            if (count8 == 17) {
               break;
            }

            if (count8 == 8) {
               curveAccumulator4.countArray[curveAccumulator4.count2 + count6] = (int)value;
               value2 = (value2 ^ 0xdfd2a1f09fe0b2L) + 0x319f10c6e7cdc9bdL;
               value3 = (value3 ^ -0x77def179c7ad6219L) + 0x314639301164ca7L;
            } else if (count8 != 7) {
               if (count8 == 10) {
                  count6--;
                  value = ((long)curveAccumulator4.countArray[curveAccumulator4.count2 + count6] & 4294967295L) - (long)((int)(-(value >> 32)));
                  value2 = (value2 ^ 0x68a4579c308c87acL) - 0x38f4443524048156L;
                  value3 = (value3 ^ -0x5ae8a302e05f6f01L) + 0x54142f127ba9c1abL;
               } else if (count8 != 9) {
                  if (count8 == 4) {
                     curveAccumulator3 = curveAccumulator5;
                     value2 = (value2 ^ 0x7b776b59d2d6195L) + 0x40f4120ce17dd900L;
                     value3 = (value3 ^ -0xffc9dfa3f2337dcL) - 0x13089d1a281c69a7L;
                  } else {
                     long value4;
                     long value5;
                     searchBlock: {
                        if (count8 != 16) {
                           if (count8 == 6) {
                              value4 = (value2 ^ -0x148b32cf4fe2f763L) + 0x4a67cf1725cb09dfL;
                              value5 = (value3 ^ 0x6b392fa35f07b0f7L) - 0x2d87a2f24d50ec32L;
                              break searchBlock;
                           }

                           if (count8 != 11) {
                              if (count8 != 18) {
                                 if (count8 != 13) {
                                    if (count8 != 5) {
                                       if (count8 != 2) {
                                          if (count8 != 0) {
                                             if (count8 != 15) {
                                                if (count8 != 3) {
                                                   count6 = curveAccumulator4.count4;
                                                   value2 = (value2 ^ -0x5f7bfe39c839a412L) - 0x607462b8f48ca587L;
                                                   value3 = (value3 ^ 0x7365e82a347dd3b2L) + 0x2eb13cd1b6cbc231L;
                                                } else if (count5 >= 0) {
                                                   value2 = (value2 ^ -0x64c19e3f8937112bL) + 0x6a8ea7e9540ef9cbL;
                                                   value3 = (value3 ^ 0x29ffabfebbf0f2afL) + 0x52c16d9b86db8872L;
                                                } else {
                                                   value2 = (value2 ^ -0x245771a3677dee06L) - 0x480534d1b64f5168L;
                                                   value3 = (value3 ^ -0x10a469b7f2c50047L) + 0x6543ae17a63b4357L;
                                                }
                                             } else {
                                                count5 = curveAccumulator4.computeCount(curveAccumulator3);
                                                value2 = (value2 ^ 0x3bb20c2bd02a6afaL) + 0x1d156cfe589db7d5L;
                                                value3 = (value3 ^ -0x3db24825b276809fL) - 0x703c9aa3ee9b66fdL;
                                             }
                                          } else {
                                             curveAccumulator4 = curveAccumulator3;
                                             value2 = (value2 ^ 0x13321ea4122b2248L) - 0x6b583931e7fab5d6L;
                                             value3 = (value3 ^ 0x226163ffbcf6752eL) + 0xd71c622f2efbb2L;
                                          }
                                       } else {
                                          value = 0L;
                                          value2 = (value2 ^ 0x3d6f0ec47b8c41f4L) - 0x551a3c0a3f1653d5L;
                                          value3 = (value3 ^ 0x3ab1f814e40bc67cL) + 0x321f323bce883ac9L;
                                       }
                                    } else {
                                       if (count5 == 0) {
                                          return 0;
                                       }

                                       value2 = (value2 ^ -0x511a6aea32c35575L) - 0x3da87b8f4db65bb7L;
                                       value3 = (value3 ^ 0x49b76027574174f9L) - 0x5e83f5aa2614684dL;
                                    }
                                 } else {
                                    count6--;
                                    count7--;
                                    value = ((long)curveAccumulator4.countArray[curveAccumulator4.count2 + count6] & 4294967295L)
                                       - ((long)curveAccumulator3.countArray[curveAccumulator3.count2 + count7] & 4294967295L)
                                       - (long)((int)(-(value >> 32)));
                                    value2 = (value2 ^ 0x27a2763de2227a76L) + 0xafeda3775bcfc4fL;
                                    value3 = (value3 ^ -0x7f8675b18db0d170L) - 0x5c9518a8ec0f73L;
                                 }
                              } else {
                                 curveAccumulator5 = curveAccumulator4;
                                 value2 = (value2 ^ -0x275aec255174a901L) - 0x1580cdde516421ecL;
                                 value3 = (value3 ^ 0x36b218147379cadbL) - 0x1a2984080b7f1aedL;
                              }
                              continue;
                           }
                        } else {
                           value2 = (value2 ^ -0x517be5d4d7223fa3L) - 0x660bde073e59f5dbL;
                           value3 = (value3 ^ -0x5a8801284bc58300L) + 0x3a17775532374aa4L;
                        }

                        if (count7 > 0) {
                           value2 = (value2 ^ -0x7d682efb92a099ccL) + 0x786ec9b731403096L;
                           value3 = (value3 ^ -0x2a42bc09aa900c3bL) - 0x5cdf523b1f29e6e0L;
                           continue;
                        }

                        value4 = (value2 ^ 0x73d2c34faf8051e1L) - 0x20aa750b3cd0988bL;
                        value5 = (value3 ^ -0x2a70f3109c5036d7L) - 0x5241389202533c8dL;
                     }

                     if (count6 <= 0) {
                        curveAccumulator4.updateState3();
                        break;
                     }

                     value2 = (value4 ^ -0x4b90c52b5ce4089bL) - 0x7b285dca5dbcdbefL;
                     value3 = (value5 ^ 0x648ea2bf61f81a2fL) - 0x551fb0084239d1caL;
                  }
               } else {
                  curveAccumulator4 = this;
                  value2 = (value2 ^ 0x46db65a3154c5dccL) + 0x968ad16e916efbdL;
                  value3 = (value3 ^ -0x77e00d4a18f54c5fL) - 0x2aaff40f0cc99e55L;
               }
            } else {
               curveAccumulator4.countArray[curveAccumulator4.count2 + count6] = (int)value;
               value2 = (value2 ^ -0xc5b3ce71c7f2e4dL) + 0x39a2ee40780dd4fdL;
               value3 = (value3 ^ -0x7ca847ee18a9553L) - 0x7808da09530b12e3L;
            }
         } else {
            count7 = curveAccumulator3.count4;
            value2 = (value2 ^ 0x297e1914455b6995L) - 0x8d6d230205320ecL;
            value3 = (value3 ^ -0x5e12759a96260893L) - 0x4bd8a6497bba335dL;
         }
      }

      return count5;
   }

   public CurveAccumulator resolveCurveAccumulator2(CurveAccumulator curveAccumulator2) {
      CurveAccumulator curveAccumulator3 = curveAccumulator2;
      CurveAccumulator curveAccumulator4 = null;
      Object entry = null;
      Object entry2 = null;
      curveAccumulator4 = this;
      entry = new CurveAccumulator();

      while (curveAccumulator3.count4 != 0) {
         if (Math.abs(curveAccumulator4.count4 - curveAccumulator3.count4) < 2) {
            return curveAccumulator4.resolveCurveAccumulator(curveAccumulator3);
         }

         entry2 = curveAccumulator4.resolveCurveAccumulator4(curveAccumulator3, (CurveAccumulator)entry);
         curveAccumulator4 = curveAccumulator3;
         curveAccumulator3 = (CurveAccumulator)entry2;
      }

      return curveAccumulator4;
   }

   public void applyCurveAccumulator(CurveAccumulator curveAccumulator2, int count5) {
      int count6 = 0;
      int count7 = 0;
      long value = 0x29e2c6f581885734L;
      long value2 = 0x619d0d5c8914cadfL;

      while (true) {
         int count8 = (int)(value % 5L);
         if (count8 != 4) {
            if (count8 != 3) {
               if (count8 != 1) {
                  long value3;
                  long value4;
                  int count9;
                  if (count8 != 0) {
                     count9 = this.count4;
                     value3 = (value ^ 0x65937a5cc92762daL) + 0x62736a2c391ae13aL;
                     value4 = (value2 ^ 0x7cbf4e69c769a07aL) + 0x537d4e6741686066L;
                  } else {
                     if (this.count4 > count7) {
                        value = (value ^ 0x3876eb9d8d32ca35L) + 0x11d02dde358e95e2L;
                        value2 = (value2 ^ -0x34688ec608ca33e2L) + 0x64b7f806da029b2fL;
                        continue;
                     }

                     value3 = (value ^ 0x18f1c6e63013f3acL) + 0x32571272926d7c59L;
                     value4 = (value2 ^ 0x3bd900898665a004L) + 0x74f629b6c9330715L;
                     count9 = count7;
                  }

                  int count10 = count9;
                  long value5;
                  long value6;
                  int[] countArray2;
                  if (this.countArray.length >= count10) {
                     value5 = (value3 ^ 0x7b88471649b9a067L) + 0x748846f5b8989fa5L;
                     value6 = (value4 ^ -0x3fa4f777a0c8913L) - 0x2bfcaf777d2d5525L;
                     countArray2 = this.countArray;
                  } else {
                     countArray2 = new int[count10];
                     value5 = (value3 ^ -0x43800f4bcb5d27edL) - 0x43a1106c2c62d80fL;
                     value6 = (value4 ^ -0x405058d9ffc70559L) - 0x699298d9ffe8c95fL;
                  }

                  int[] countArray3 = countArray2;
                  int count11 = countArray3.length - 1;
                  long value7 = 0L;

                  long value8;
                  long value9;
                  while (true) {
                     if (count6 <= 0) {
                        value8 = (value5 ^ -0x2d9b30b5cb265c2dL) - 0x319c40c62cc75fcfL;
                        value9 = (value6 ^ 0x1a5ecdd52f5e8f46L) - 0x621322d16c27cbeL;
                        break;
                     }

                     if (count7 <= 0) {
                        value8 = (value5 ^ -0x4107ead8d5db33bbL) - 0x4627eadb29e4c43dL;
                        value9 = (value6 ^ 0x41ad79c0f9191e39L) + 0x406b19c016f91227L;
                        break;
                     }

                     count6--;
                     long value10;
                     long value11;
                     if (--count7 + curveAccumulator2.count2 >= curveAccumulator2.countArray.length) {
                        value10 = (value5 ^ 0x16ff24cfa634cfe6L) + 0x11df23bd620b3c26L;
                        value11 = (value6 ^ -0x62d649d0fa98772dL) + 0x74e7562d036688cdL;
                        count9 = 0;
                     } else {
                        count9 = curveAccumulator2.countArray[count7 + curveAccumulator2.count2];
                        value10 = (value5 ^ 0x54ea7806c892bd3eL) + 0x53ea6806376d8adeL;
                        value11 = (value6 ^ 0x42f8ff1da38b7101L) + 0x41389f1b5c8ab0ffL;
                     }

                     int count12 = count9;
                     long value12 = ((long)this.countArray[count6 + this.count2] & 4294967295L) + ((long)count12 & 4294967295L) + value7;
                     countArray3[count11--] = (int)value12;
                     value7 = value12 >>> 32;
                     value5 = (value10 ^ -0x3ab73c507e91d71cL) - 0x3cb83d627f1de8dcL;
                     value6 = (value11 ^ 0x2831fe93bc6c6833L) + 0x27ef9e915c6ba82dL;
                  }

                  while (count6 > 0) {
                     count6--;
                     long value13;
                     long value14;
                     if (value7 != 0L) {
                        value13 = (value8 ^ 0x7027876766083f1aL) + 0x70268764a2080cfaL;
                        value14 = (value9 ^ -0x14056a294448930eL) - 0x15c58a29ab695b12L;
                     } else if (countArray3 != this.countArray) {
                        value13 = (value8 ^ -0x3a99a3310fe60222L) - 0x3c99b441f007b5c2L;
                        value14 = (value9 ^ 0x7f22d74b081c4eb3L) + 0x56e2b74907fbc2adL;
                     } else {
                        if (count11 == count6 + this.count2) {
                           return;
                        }

                        value13 = (value8 ^ -0x699235fb05c24e39L) - 0x6d9345fbf9eb71bbL;
                        value14 = (value9 ^ 0x5564e435ceaad71bL) + 0x2ca4a43349a94b05L;
                     }

                     long value15 = ((long)this.countArray[count6 + this.count2] & 4294967295L) + value7;
                     countArray3[count11--] = (int)value15;
                     value7 = value15 >>> 32;
                     value8 = (value13 ^ 0x1af30103c7ebc34fL) + 0x15d30101426640cdL;
                     value9 = (value14 ^ -0x5ef033408a35dd20L) - 0x5ef233436d362120L;
                  }

                  long value16 = (value8 ^ -0x46c8b87bc5d41e14L) - 0x48e8b97c39d9a1d4L;
                  long value17 = (value9 ^ -0x5517b173aaa4c75L) - 0x26917b17bccb908bL;

                  while (count7 > 0) {
                     long value18;
                     long value19;
                     if (--count7 + curveAccumulator2.count2 >= curveAccumulator2.countArray.length) {
                        value18 = (value16 ^ -0x26e0397c8394fc94L) - 0x29013a7f7418ff54L;
                        value19 = (value17 ^ 0x23f593c23f4c0fa6L) + 0x223373c1d94c03a2L;
                        count9 = 0;
                     } else {
                        count9 = curveAccumulator2.countArray[count7 + curveAccumulator2.count2];
                        value18 = (value16 ^ -0x770864d1bedb4298L) - 0x782974e23ee47558L;
                        value19 = (value17 ^ -0x3dfb2419d7f91ff2L) - 0x3dfb441a17fae006L;
                     }

                     int count13 = count9;
                     long value20 = ((long)count13 & 4294967295L) + value7;
                     countArray3[count11--] = (int)value20;
                     value7 = value20 >>> 32;
                     value16 = (value18 ^ -0xd06f541960dd9d9L) - 0x1227067267b1da1bL;
                     value17 = (value19 ^ -0x7f342e52b59b0367L) + 0x7f49b1aac6633487L;
                  }

                  if (value7 > 0L) {
                     if (countArray3.length >= ++count10) {
                        countArray3[count11--] = 1;
                     } else {
                        int[] countArray4 = new int[count10];
                        System.arraycopy(countArray3, 0, countArray4, 1, countArray3.length);
                        countArray4[0] = 1;
                        countArray3 = countArray4;
                     }
                  }

                  this.countArray = countArray3;
                  this.count4 = count10;
                  this.count2 = countArray3.length - count10;
                  return;
               }

               count7 = curveAccumulator2.count4 + count5;
               value = (value ^ 0x5a7793831fe96e22L) + 0x8b0282fb392c8L;
               value2 = (value2 ^ -0x1ea8b024d3014d27L) + 0x3ba7c58eba237bbdL;
               continue;
            }
         } else if (!curveAccumulator2.isActive2()) {
            long value21 = (value ^ 0x5132ae3dc4e1eb05L) - 0x583834b2d3b06616L;
            long value22 = (value2 ^ -0x4295561c18362224L) + 0x7bd28d4b59aa7468L;
            count6 = this.count4;
            value = (value21 ^ -0x7bce3c1836616a83L) + 0x63ed6a7bd4f5e60dL;
            value2 = (value22 ^ -0x450efecb7b38a689L) - 0x6f1c8459a4784c55L;
            continue;
         }

         return;
      }
   }

   private boolean checkTime(long value, long value2) {
      return value + Long.MIN_VALUE > value2 + Long.MIN_VALUE;
   }

   public SignumComparable resolveSignumComparableByIndex(int count5) {
      return this.count4 != 0 && count5 != 0 ? new SignumComparable(this.getIndexArray(), count5) : SignumComparable.signumComparable5;
   }

   private int computeCount(int[] countArray2, int count5, int count6, int count7, int count8) {
      long value = 0L;
      long value2 = 0L;
      long value3 = 0L;
      value = (long)count7 & 4294967295L;
      int count9 = count8 + 2;
      value2 = ((long)count6 & 4294967295L) * value;
      value3 = (long)countArray2[count9] - value2;
      countArray2[count9--] = (int)value3;
      long value4 = (value2 >>> 32) + (long)((value3 & 4294967295L) <= ((long)(~((int)value2)) & 4294967295L) ? 0 : 1);
      long value5 = ((long)count5 & 4294967295L) * value + value4;
      long value6 = (long)countArray2[count9] - value5;
      countArray2[count9--] = (int)value6;
      long value7 = (value5 >>> 32) + (long)((value6 & 4294967295L) <= ((long)(~((int)value5)) & 4294967295L) ? 0 : 1);
      return (int)value7;
   }

   public void updateState2() {
      this.count2 = this.count4 = 0;
   }

   private final void applyIndex7(int count5) {
      int[] entry = null;
      int count6 = 0;
      int count7 = 0;
      int count8 = 0;
      entry = this.countArray;
      count6 = 32 - count5;
      count7 = this.count2 + this.count4 - 1;

      for (int index = (int)entry[count7]; count7 > this.count2; count7--) {
         count8 = index;
         index = (int)entry[count7 - 1];
         entry[count7] = index << count6 | count8 >>> count5;
      }

      entry[this.count2] = entry[this.count2] >>> count5;
   }

   private static void applyIndexArray(int[] countArray2, int count5, int count6, int[] countArray3, int count7, int count8) {
      int count9 = count5;
      int count10 = 0;
      int count11 = 0;
      int count12 = 0;
      count10 = 32 - count8;
      count11 = countArray2[count5];

      for (int index = 0; index < count6 - 1; index++) {
         count12 = count11;
         count11 = countArray2[++count9];
         countArray3[count7 + index] = count12 << count8 | count11 >>> count10;
      }

      countArray3[count7 + count6 - 1] = count11 << count8;
   }

   public CurveAccumulator resolveCurveAccumulator3(CurveAccumulator curveAccumulator2) {
      int count5 = 0;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      Object entry5 = null;
      Object entry6 = null;
      Object entry7 = null;
      Object entry8 = null;
      if (!curveAccumulator2.isActive3()) {
         if (!this.isActive4()) {
            count5 = curveAccumulator2.getCount();
            entry = new CurveAccumulator(curveAccumulator2);
            ((CurveAccumulator)entry).applyIndex(count5);
            if (!((CurveAccumulator)entry).isActive()) {
               entry2 = this.resolveCurveAccumulatorByCurveAccumulator((CurveAccumulator)entry);
               entry3 = this.resolveCurveAccumulator(count5);
               entry4 = resolveCurveAccumulator((CurveAccumulator)entry, count5);
               entry5 = ((CurveAccumulator)entry).resolveCurveAccumulator(count5);
               entry6 = new CurveAccumulator();
               entry7 = new CurveAccumulator();
               entry8 = new CurveAccumulator();
               ((CurveAccumulator)entry2).applyIndex3(count5);
               ((CurveAccumulator)entry2).applyCurveAccumulator((CurveAccumulator)entry4, (CurveAccumulator)entry8);
               ((CurveAccumulator)entry3).applyCurveAccumulator((CurveAccumulator)entry, (CurveAccumulator)entry6);
               ((CurveAccumulator)entry6).applyCurveAccumulator((CurveAccumulator)entry5, (CurveAccumulator)entry7);
               ((CurveAccumulator)entry8).applyCurveAccumulator((CurveAccumulator)entry7);
               return ((CurveAccumulator)entry8).resolveCurveAccumulator4(curveAccumulator2, (CurveAccumulator)entry6);
            } else {
               return this.resolveCurveAccumulator(count5);
            }
         } else {
            throw new ArithmeticException("BigInteger not invertible.");
         }
      } else {
         return this.resolveCurveAccumulatorByCurveAccumulator(curveAccumulator2);
      }
   }

   public static int computeCount(int count5, int count6) {
      int count7 = 0;
      int count8 = 0;
      long value = 0x3ec247ae0a781aa8L;
      long value2 = 0x7de139313821a0baL;
      if (count6 != 0) {
         value = (value ^ -0x3e17747ef407dd42L) + 0x3a142aa8481bc56L;
         value2 = (value2 ^ 0x393d39b5ca70aa38L) + 0x399d9168da4f3c9cL;
         if (count5 != 0) {
            value = (value ^ -0x61734259de53014L) + 0x71cd94b10ba9d1e5L;
            value2 = (value2 ^ 0x3d9ec0dcfed51655L) - 0x715fefdc84bf3d51L;
            count7 = Integer.numberOfTrailingZeros(count5);
            value = (value ^ 0x5024e80657affac4L) + 0x267581303b63e97bL;
            value2 = (value2 ^ -0x46776d873d0f8a99L) + 0x5123f6e32470a4a6L;
            count8 = Integer.numberOfTrailingZeros(count6);
            value = (value ^ -0x63d405f445037fb6L) + 0x146dd8b95cb933b6L;
            value2 = (value2 ^ 0x1e8ce0f024769d62L) - 0x6c1d399cdf2055f5L;
            int count9 = count5 >>> count7;
            value = (value ^ 0x3d0109ca774d8d6aL) + 0x4c78e2b2197530fL;
            value2 = (value2 ^ 0x2eb16862fb4fe60eL) + 0x2a2e84db9befaf04L;
            int count10 = count6 >>> count8;
            value = (value ^ -0x13f0d871ebc8a301L) - 0x6b865e8d3bc7de14L;
            value2 = (value2 ^ -0x36e029f2652b51dL) - 0x193cb79f29b5af3bL;
            long value3;
            long value4;
            int count11;
            if (count7 >= count8) {
               value3 = (value ^ 0x656a02deb70e13d9L) + 0x2e7d3c3acc98bdc8L;
               value4 = (value2 ^ -0x3e156da2a2a83e48L) - 0x68de044987287673L;
               count11 = count8;
            } else {
               value = (value ^ -0x26fc7f7a9e02c003L) - 0x6d2b458ce5da5e14L;
               value2 = (value2 ^ -0x6f981c02e68d62a0L) - 0x395b53e94b0db32bL;
               count11 = count7;
               value3 = (value ^ -0x2e3d2a379a4d291bL) + 0x51c2b348258d29e5L;
               value4 = (value2 ^ -0x3c5b92b96dff056dL) - 0x3c6464b96e00856dL;
            }

            int count12 = count11;

            while (count9 != count10) {
               if (count9 + Integer.MIN_VALUE <= count10 + Integer.MIN_VALUE) {
                  long value5 = (value3 ^ -0x358f9ef4851b50f7L) + 0x4a505f053ae360f9L;
                  long value6 = (value4 ^ 0x28779bf215a8ce18L) + 0x27ed89f20b574618L;
                  count10 -= count9;
                  count10 >>>= Integer.numberOfTrailingZeros(count10);
                  value3 = (value5 ^ -0x462764a6eb648a72L) - 0x46578b48eb997582L;
                  value4 = (value6 ^ -0x47d66df7c4a63fd2L) - 0x47d86e0806d9bfd2L;
               } else {
                  count9 -= count10;
                  count9 >>>= Integer.numberOfTrailingZeros(count9);
                  value3 = (value3 ^ -0x70ab2bdab658f3b9L) - 0x70d34c24b65cfbb9L;
                  value4 = (value4 ^ 0x3384041ffe5133f4L) + 0x337c040fb62f3174L;
               }
            }

            return count9 << count12;
         } else {
            return count6;
         }
      } else {
         return count5;
      }
   }

   public boolean isActive4() {
      return this.count4 == 0 || (this.countArray[this.count2 + this.count4 - 1] & 1) == 0;
   }

   private CurveAccumulator resolveCurveAccumulator3(CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3) {
      int count5 = 0;
      CurveAccumulator curveAccumulator4 = null;
      CurveAccumulator curveAccumulator5 = null;
      SignumComparable signumComparable = null;
      CurveAccumulator curveAccumulator6 = null;
      CurveAccumulator curveAccumulator7 = null;
      long value2 = 0x4b39bfe6e64bb90dL;
      long value3 = 0xbe850603f9909beL;

      while (true) {
         int count6 = (int)(value2 % 24L);
         if (count6 == 0) {
            curveAccumulator6.applyCurveAccumulator(curveAccumulator2);
            value2 = (value2 ^ 0x228a4551881082b1L) - 0xe7f176e60c5fd55L;
            value3 = (value3 ^ 0x3a29e4f5460fa895L) + 0x187a7129d9fc82d0L;
         } else if (count6 == 6) {
            CurveAccumulator curveAccumulator8 = new CurveAccumulator(signumComparable);
            curveAccumulator7.computeCount2(curveAccumulator8);
            value2 = (value2 ^ -0x5221cce17ff783a5L) + 0x7f8ffcf831f88c13L;
            value3 = (value3 ^ 0xc5c3e561c62d6b8L) - 0x2e0d6987d2edaf19L;
         } else if (count6 == 20) {
            int count7 = count5 * 32;
            curveAccumulator5.applyIndex6(count7);
            value2 = (value2 ^ 0x5f834164c9f5fdL) + 0x23273235c53258e9L;
            value3 = (value3 ^ -0x4ed5136a2f6acc65L) - 0x44effbdfab736c5eL;
         } else if (count6 != 17) {
            if (count6 != 2) {
               if (count6 == 18) {
                  signumComparable = curveAccumulator2.resolveSignumComparable(count5);
                  value2 = (value2 ^ -0x699916effc4f11c4L) - 0x4bf85363376485a2L;
                  value3 = (value3 ^ -0x4365bd1e54d5a98L) + 0x3e13357107e22ec0L;
                  continue;
               }

               if (count6 == 7) {
                  curveAccumulator6.applyCurveAccumulatorByCurveAccumulator(this, count5);
                  value2 = (value2 ^ -0x6d02dbe55b25e9eL) + 0x7a183da9c2457590L;
                  value3 = (value3 ^ -0x3dca823f285c35ccL) + 0x209118270b3a468bL;
                  continue;
               }

               if (count6 != 8) {
                  if (count6 == 23) {
                     break;
                  }

                  if (count6 != 22) {
                     if (count6 != 21) {
                        if (count6 != 10) {
                           if (count6 != 3) {
                              if (count6 != 11) {
                                 if (count6 != 9) {
                                    if (count6 == 19) {
                                       curveAccumulator4 = new CurveAccumulator(this);
                                       value2 = (value2 ^ -0x552e1630910c79a6L) + 0x6e6680618cfd2890L;
                                       value3 = (value3 ^ -0x70f611a1bffc266aL) + 0x65beb7bdaed55df5L;
                                       continue;
                                    }

                                    if (count6 == 1) {
                                       curveAccumulator7 = new CurveAccumulator(curveAccumulator3.getSignumComparable().resolveSignumComparable4(signumComparable));
                                       value2 = (value2 ^ 0x13904a8a7964de6eL) - 0x3eced1ed5433af3dL;
                                       value3 = (value3 ^ 0xb9f20dbc95f51a5L) + 0x69409abb60511681L;
                                       continue;
                                    }

                                    if (count6 != 13) {
                                       if (count6 != 16) {
                                          if (count6 != 4) {
                                             if (count6 != 5) {
                                                if (count6 != 14) {
                                                   if (count6 != 15) {
                                                      if (this.computeCount(curveAccumulator2, count5) >= 0) {
                                                         long value4 = (value2 ^ 0x2b2b1dd9619095d5L) - 0x5c8d00dfbecc219fL;
                                                         long value5 = (value3 ^ -0x6866300b1cd29ee2L) - 0x36ba95355b99121dL;
                                                         curveAccumulator3.applyIndex4(count5);
                                                         value2 = (value4 ^ -0x66a36ee713a8046cL) - 0x68f48630becbf3f1L;
                                                         value3 = (value5 ^ 0x2219bc6a669c3d16L) - 0x3400c4ee8b75aff8L;
                                                      } else {
                                                         value2 = (value2 ^ -0x441b4fab571564deL) - 0x7a8c8b56c34a8474L;
                                                         value3 = (value3 ^ 0x37d0cc217f190fd0L) + 0xe937d1a6a818037L;
                                                      }
                                                   } else {
                                                      int count8 = 32 * count5;
                                                      curveAccumulator5.applyIndex3(count8);
                                                      value2 = (value2 ^ -0x5d8301230287b45aL) + 0x56d94d03b34ca277L;
                                                      value3 = (value3 ^ -0x37bd62569871acb7L) + 0x256a0e0b6c78d07aL;
                                                   }
                                                } else {
                                                   curveAccumulator6 = curveAccumulator4;
                                                   value2 = (value2 ^ 0x64924771b3bccc19L) - 0x627aec2ae323fda4L;
                                                   value3 = (value3 ^ -0xb3b40b9c6e0446bL) + 0x1a141ee21574b67eL;
                                                }
                                             } else {
                                                curveAccumulator4.applyCurveAccumulator(curveAccumulator5);
                                                value2 = (value2 ^ -0x42b453dab2fa1a8cL) + 0x6d0efc511c577d4eL;
                                                value3 = (value3 ^ 0x54b603f191a958cbL) + 0x3694649679fd29a0L;
                                             }
                                          } else {
                                             CurveAccumulator curveAccumulator9 = curveAccumulator;
                                             curveAccumulator3.computeCount2(curveAccumulator9);
                                             value2 = (value2 ^ -0x280b431c35b6c21L) - 0x250756aa2305dfeaL;
                                             value3 = (value3 ^ -0x5275b2e519e406f0L) + 0x57a2005a86d20bbaL;
                                          }
                                       } else {
                                          curveAccumulator4.computeCount2(curveAccumulator5);
                                          value2 = (value2 ^ 0x7db6f7b918719b7fL) - 0x1d4253e6d6adbe41L;
                                          value3 = (value3 ^ -0x7b7be412c4c212b0L) + 0x3c95b5b00a0b1dfcL;
                                       }
                                       continue;
                                    }
                                 } else {
                                    value2 = (value2 ^ 0x2208d88e8698a374L) - 0x25d45c4e1be63e30L;
                                    value3 = (value3 ^ 0x3052c730a2b0e2bL) - 0x6ab8a71c79a7a02aL;
                                 }

                                 if (curveAccumulator6.computeCount(curveAccumulator7) >= 0) {
                                    curveAccumulator6.computeCount2(curveAccumulator7);
                                    break;
                                 }

                                 value2 = (value2 ^ -0x2a0ee1143761f834L) + 0x62b9828b82586eafL;
                                 value3 = (value3 ^ -0x165b607bd5e1937cL) - 0x5cb1114bb7252352L;
                              } else {
                                 curveAccumulator7 = new CurveAccumulator(signumComparable);
                                 value2 = (value2 ^ 0x216b88bfe72f65aaL) - 0x14f4b9d53d190256L;
                                 value3 = (value3 ^ -0x47d2f4e178745477L) - 0x706460715125f467L;
                              }
                           } else {
                              int count9 = 32 * count5;
                              curveAccumulator7.applyIndex3(count9);
                              value2 = (value2 ^ -0x74f85c7bacf3ec3dL) + 0x7dd30708be6ef9eeL;
                              value3 = (value3 ^ 0x36a988951c920d93L) - 0x570a03ba38194d05L;
                           }
                        } else {
                           curveAccumulator6 = curveAccumulator4.resolveCurveAccumulatorByCurveAccumulator(curveAccumulator5, curveAccumulator3);
                           value2 = (value2 ^ -0x23028716869ebadL) - 0x41bb1f31a49ec260L;
                           value3 = (value3 ^ -0x5311fdf26ab28ca5L) + 0x49c72d8c2f862cc1L;
                        }
                     } else {
                        count5 = curveAccumulator2.count4 / 2;
                        value2 = (value2 ^ 0x7bd6f2f812efb8ccL) + 0x1b446a3f189790d2L;
                        value3 = (value3 ^ 0xe65581f796eda2eL) - 0x428aa71136fd9448L;
                     }
                  } else {
                     curveAccumulator5 = new CurveAccumulator(curveAccumulator2);
                     value2 = (value2 ^ -0x429ec668095f55b7L) + 0x6f9c51b3a01e5b3dL;
                     value3 = (value3 ^ -0x11757534efec83c1L) + 0x2aee5c73e29007b2L;
                  }
                  continue;
               }
            } else {
               value2 = (value2 ^ -0x6883234b287f1adcL) + 0x7eeee59795248a62L;
               value3 = (value3 ^ -0x78fb48151f9c94faL) - 0x25fd906f26964976L;
            }

            int count10 = 32 * count5;
            curveAccumulator6.applyIndex3(count10);
            value2 = (value2 ^ 0x77114a87e2761e0aL) + 0x14839664dc4535d5L;
            value3 = (value3 ^ -0x59181acf1df2830dL) - 0x3de18d7696a864c4L;
         } else {
            int count11 = 32 * count5;
            curveAccumulator4.applyIndex6(count11);
            value2 = (value2 ^ 0x257aaaca1ea7182fL) - 0x53751494ef0b5fc0L;
            value3 = (value3 ^ 0x2ff472c7bface0c0L) - 0x7fca6f97cccd7edaL;
         }
      }

      return curveAccumulator6;
   }

   public long getTimestampAsTime() {
      for (long index = 0x29647e813568c6aL; (int)(index % 3L) == 2; index = (index ^ -0x4d87b99be55ea06dL) + 0x7e800f27dd2f1101L) {
         if (this.count4 == 0) {
            return 0L;
         }
      }

      return (long)this.count4 * 32L - (long)Integer.numberOfLeadingZeros(this.countArray[this.count2]);
   }

   private final void applyIndex8(int count5) {
      if (this.countArray.length < count5) {
         this.countArray = new int[count5];
         this.count2 = 0;
         this.count4 = count5;
      }
   }

   private CurveAccumulator resolveCurveAccumulator(int count5, int count6, int count7) {
      int count8 = 0;
      int count9 = 0;
      int[] countArray2 = null;
      long value = 0x1f16c6b4f708c660L;
      long value2 = 0x20d2879e053d8e45L;

      while (true) {
         int count10 = (int)(value % 10L);
         if (count10 == 3) {
            break;
         }

         if (count10 == 7) {
            count9 = this.count4;
            value = (value ^ 0x47adc260720e4399L) + 0x119a7ec815059c17L;
            value2 = (value2 ^ -0x7a516d2e99b2896fL) - 0x1aef6788fb19312fL;
         } else {
            if (count10 != 9) {
               if (count10 == 8) {
                  if (count8 < this.count4) {
                     value = (value ^ -0x111dc728cd44e655L) - 0x7f9a74598664781eL;
                     value2 = (value2 ^ 0x2cfb1cf988324837L) - 0x5a886cb7943c830bL;
                     continue;
                  }
                  break;
               }

               if (count10 != 6) {
                  if (count10 != 5) {
                     if (count10 != 4) {
                        if (count10 == 0) {
                           count8 = count5 * count7;
                           value = (value ^ 0x45faef8c22e174ccL) - 0x16aca4e97d9aa678L;
                           value2 = (value2 ^ -0x2555950d39cb4c13L) + 0x4669a250eeb8c6e0L;
                           continue;
                        }

                        if (count10 != 2) {
                           if (count5 != count6 - 1) {
                              long value3 = (value ^ 0x3dad9b7a70d8089bL) + 0x3551311cfd1dec9dL;
                              long value4 = (value2 ^ 0x1fb25626d70c11acL) - 0xe2be8310af2a2beL;
                              count9 = (count5 + 1) * count7;
                              value = (value3 ^ -0x4d7419da34515c99L) + 0x7f9964b3523e7bb8L;
                              value2 = (value4 ^ 0x714fff6563474f2aL) + 0x7a2dc554640e3ec3L;
                           } else {
                              value = (value ^ -0x3eb874cc9d919bf6L) - 0x6cfc7b1478805352L;
                              value2 = (value2 ^ -0x28e2b3af6851b38eL) - 0x274408092d7275aeL;
                           }
                           continue;
                        }

                        countArray2 = Arrays.copyOfRange(this.countArray, this.count2 + this.count4 - count9, this.count2 + this.count4 - count8);
                     }

                     return new CurveAccumulator(countArray2);
                  }

                  value = (value ^ 0x6ee9bad4f25471eaL) + 0x5bbb884249f7ae2bL;
                  value2 = (value2 ^ 0x6a74b6b9eb323279L) + 0x7362fdafdb99b210L;
               }

               if (count9 <= this.count4) {
                  value = (value ^ -0x14ef036c06aca49eL) - 0x60cf2f2200a434a4L;
                  value2 = (value2 ^ 0x30623ce8b6cf4638L) + 0x4d050107989279eL;
                  continue;
               }
            }

            return new CurveAccumulator();
         }
      }

      return new CurveAccumulator();
   }

   public final void updateState3() {
      int count5 = 0;
      int count6 = 0;
      int count7 = 0;
      long value = 0x200b5516296bb273L;
      long value2 = -0x31b51dc28f1dc13eL;

      while (true) {
         while (true) {
            int count8 = (int)(value % 10L);
            if (count8 == 2) {
               this.count2 = 0;
               break;
            }

            if (count8 != 7) {
               if (count8 == 6) {
                  if (++count5 >= count6) {
                     value = (value ^ 0x37e72854e59023d5L) - 0x330a791632bd8f7eL;
                     value2 = (value2 ^ -0x44a9974223d2c2e1L) - 0x78792c2282e5513aL;
                  } else {
                     value = (value ^ -0x55313458228d82faL) + 0x7875570443801889L;
                     value2 = (value2 ^ 0x20a4fe505383c84fL) + 0x5959515be21cc10L;
                  }
               } else if (count8 == 9) {
                  this.count4 -= count7;
                  value = (value ^ -0x7a9b1c0a0d558414L) + 0x31709e7a96b4fe75L;
                  value2 = (value2 ^ 0x1967f43958c12b97L) + 0x5dbea14243594fa4L;
               } else {
                  if (count8 == 8) {
                     this.count2 = this.count4 != 0 ? this.count2 + count7 : 0;
                     return;
                  }

                  if (count8 != 4) {
                     if (count8 == 5) {
                        break;
                     }

                     if (count8 == 1) {
                        if (this.count4 != 0) {
                           long value3 = (value ^ 0x2a4bfcd9a045b83cL) + 0x266d191b368fdc2L;
                           long value4 = (value2 ^ -0x62a77f0b54fdf1c1L) - 0x70f34dda2b132d3bL;
                           count5 = this.count2;
                           value = (value3 ^ -0x1eb6278d9b4b21efL) - 0x7f589f2c355d35f2L;
                           value2 = (value4 ^ -0x1bfffc38ecb690bdL) - 0x49c78088af2b8763L;
                        } else {
                           value = (value ^ -0x641cc90f27b5abd4L) - 0x6c68c75a877c6397L;
                           value2 = (value2 ^ -0x755ff5d8b24f73fL) + 0x6840efeb45d0f1b3L;
                        }
                        continue;
                     }

                     if (count8 != 0) {
                        count7 = count5 - this.count2;
                        value = (value ^ 0x30c4693a75e8e78aL) - 0x17c94d215a73947cL;
                        value2 = (value2 ^ -0x2a24bf3fa7746397L) - 0xfb1c1e8e7496fd0L;
                        continue;
                     }

                     if (this.countArray[count5] == 0) {
                        long value5 = (value ^ -0x54910a17b76415dbL) + 0x6621a3aeb6867a5dL;
                        long value6 = (value2 ^ -0x7be1b89c33281c2fL) - 0x7582c8b9e20f6d1dL;
                        count6 = count5 + this.count4;
                        value = (value5 ^ 0x5b9b0bc93292158cL) - 0x293f6a4c4b4617deL;
                        value2 = (value6 ^ -0x1ad753b7c37093ccL) + 0x73dae0bf9c36997fL;
                        continue;
                     }
                  }

                  return;
               }
            } else if (this.countArray[count5] == 0) {
               value = (value ^ 0x40c8a719be98d242L) + 0x30778aa1e3eaaa3bL;
               value2 = (value2 ^ -0x6f1085b2242d96deL) + 0x49df1acc3e13148dL;
            } else {
               value = (value ^ 0x489d468c45517d19L) + 0x30fb0b4ad77569c5L;
               value2 = (value2 ^ -0x43fddda2aece5a0bL) + 0x2b1d8e68142daa31L;
            }
         }

         return;
      }
   }

   public static CurveAccumulator resolveCurveAccumulator(CurveAccumulator curveAccumulator2, int count5) {
      CurveAccumulator curveAccumulator3 = new CurveAccumulator(1);
      CurveAccumulator curveAccumulator4 = new CurveAccumulator(curveAccumulator2);
      return resolveCurveAccumulator(curveAccumulator3, curveAccumulator4, count5);
   }

   public void applyCurveAccumulator2(CurveAccumulator curveAccumulator2, int count5) {
      int count6 = 0;
      int count7 = 0;
      long value = 0x685c564021cda8f8L;
      long value2 = -0x59d68504053bc32aL;

      while (true) {
         int count8 = (int)(value % 5L);
         int count9;
         if (count8 != 4) {
            if (count8 == 1) {
               break;
            }

            if (count8 != 3) {
               if (count8 != 0) {
                  count7 = curveAccumulator2.count4 + count5;
                  value = (value ^ -0xdbf028c066e3324L) + 0x7a34b2ea1f87b839L;
                  value2 = (value2 ^ 0xdb45f018fc562e5L) - 0x5b7470a6ed2beb3cL;
                  continue;
               }

               if (!curveAccumulator2.isActive2()) {
                  long value3 = (value ^ -0x1e4f86c66df8487dL) - 0x770b70f055ca853aL;
                  long value4 = (value2 ^ 0x694080a6ede7dbd0L) - 0x39c29da3860e43bcL;
                  count6 = this.count4;
                  value = (value3 ^ 0x32e2968448b76a68L) + 0x37b913127bde7435L;
                  value2 = (value4 ^ 0x3f89d675150e2cc4L) - 0x466c9e590601bd05L;
                  continue;
               }
               break;
            }

            if (this.count4 > count7) {
               value = (value ^ 0x3136c30644092ecaL) + 0x46eb960aaef91687L;
               value2 = (value2 ^ 0x45fd53c059381206L) + 0x4cc3bde3410d89faL;
               continue;
            }

            count9 = count7;
         } else {
            count9 = this.count4;
         }

         int count10 = count9;
         int[] countArray2;
         if (this.countArray.length >= count10) {
            countArray2 = this.countArray;
            Arrays.fill(this.countArray, this.count2 + this.count4, this.countArray.length, 0);
         } else {
            countArray2 = new int[count10];
         }

         int count11 = countArray2.length - 1;
         System.arraycopy(this.countArray, this.count2, countArray2, count11 + 1 - count6, count6);
         int count12 = count7 - count6;
         int count13 = count11 - count6;
         int count14 = Math.min(count12, curveAccumulator2.countArray.length - curveAccumulator2.count2);
         System.arraycopy(curveAccumulator2.countArray, curveAccumulator2.count2, countArray2, count13 + 1 - count12, count14);

         for (int index = count13 + 1 - count12 + count14; index < count13 + 1; index++) {
            countArray2[index] = 0;
         }

         this.countArray = countArray2;
         this.count4 = count10;
         this.count2 = countArray2.length - count10;
         return;
      }
   }

   public CurveAccumulator resolveCurveAccumulator4(CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3) {
      return this.resolveCurveAccumulator2(curveAccumulator2, curveAccumulator3, true);
   }

   public final int computeCount3(CurveAccumulator curveAccumulator2) {
      int count5 = 0;
      int count6 = 0;
      long value = 0x61638157e9d0e117L;
      long value2 = -0x7c2e170e385830b4L;
      count5 = curveAccumulator2.count4;
      value = (value ^ -0x78e44b407dbeec86L) + 0x258bc00ed5647497L;
      value2 = (value2 ^ 0x333cbb7e21ca09f0L) + 0x498ad820ba85ab91L;
      count6 = this.count4;
      value = (value ^ -0x22c56fa948a662f5L) - 0x5379706083793b8bL;
      value2 = (value2 ^ -0x15b5a49b1fb77be6L) + 0x29a89e0174650424L;
      if (count6 <= 0) {
         value = (value ^ 0x56072e96588617abL) + 0x3365dbe2565c7f29L;
         value2 = (value2 ^ -0xebe369d227bfaafL) + 0x77550101e5cbb19cL;
         byte byteValue;
         if (count5 > 0) {
            byteValue = -1;
         } else {
            value = (value ^ -0x762c5a316e4ab907L) - 0x63915e19cdacbb00L;
            value2 = (value2 ^ 0x5c961bf286d32647L) - 0x110ad0ab2b4fa660L;
            byteValue = 0;
         }

         return byteValue;
      } else {
         long value3 = (value ^ 0x4ca5ff4fa2250da6L) + 0x4221aa4e6ef8007fL;
         long value4 = (value2 ^ -0xb0024adc8cda310L) - 0x7ab5d2e6e4beb66aL;
         if (count6 > count5) {
            return 1;
         } else {
            long value5 = (value3 ^ -0x5da8ac1e481066feL) - 0x5e31335fadd87702L;
            long value6 = (value4 ^ -0x1f2dc06b70a9a133L) - 0x604e3a9374b1becbL;
            if (count6 < count5 - 1) {
               return -1;
            } else {
               long value7 = (value5 ^ 0x48583e78c578dbeaL) + 0x37d82a37c4984b56L;
               long value8 = (value6 ^ -0x713dbb6ba1018b87L) + 0x75c1bc6bd9a66bc9L;
               int[] countArray2 = curveAccumulator2.countArray;
               int count7 = 0;
               int count8 = 0;
               long value9;
               long value10;
               if (count6 == count5) {
                  value9 = (value7 ^ 0x27ee1cf4f0bb585dL) + 0x1e660bb38f5ac7a5L;
                  value10 = (value8 ^ 0x768ad4604d4c6523L) + 0x2d892bdfcd345b1fL;
               } else {
                  if (countArray2[count7] != 1) {
                     return -1;
                  }

                  count7++;
                  count8 = Integer.MIN_VALUE;
                  value9 = (value7 ^ 0x345f31040ffa97dfL) + 0x2bd72f03fa1a8f23L;
                  value10 = (value8 ^ 0x35e8145d773e448L) + 0x13d7ebb5333dbb8L;
               }

               int[] countArray3 = this.countArray;
               int count9 = this.count2;
               int count10 = count7;

               while (count9 < count6 + this.count2) {
                  int count11 = countArray2[count10++];
                  long value11 = (long)((count11 >>> 1) + count8) & 4294967295L;
                  long value12 = (long)countArray3[count9++] & 4294967295L;
                  if (value12 != value11) {
                     return value12 >= value11 ? 1 : -1;
                  }

                  long value13 = (value9 ^ -0x224d22c705e1ea5L) - 0x1ba4e531859e9f59L;
                  long value14 = (value10 ^ 0x6c0b580a3d1fc4d0L) + 0x140aa7f5fd07bb30L;
                  count8 = (count11 & 1) << 31;
                  value9 = (value13 ^ 0x559905ebe4375299L) + 0x4c9902a6a3ced269L;
                  value10 = (value14 ^ 0x53186f39a69e32f9L) + 0x50f7950761862d49L;
               }

               return count8 != 0 ? -1 : 0;
            }
         }
      }
   }

   public SplineParser resolveSplineParser(int count5, int count6) {
      int[] entry = null;
      int count7 = 0;
      int count8 = 0;
      if (this.count4 != 0 && count5 != 0) {
         entry = this.getIndexArray();
         count7 = entry.length;
         count8 = (int)entry[0];
         if (count7 <= 2 && (count8 >= 0 || count7 != 2)) {
            long value = count7 != 2 ? (long)count8 & 4294967295L : (long)entry[1] & 4294967295L | ((long)count8 & 4294967295L) << 32;
            return SplineParser.resolveSplineParserByTime(count5 != -1 ? value : -value, count6);
         } else {
            return new SplineParser(new SignumComparable((int[])entry, count5), Long.MIN_VALUE, count6, 0);
         }
      } else {
         return SplineParser.resolveSplineParser3(count6);
      }
   }

   public CurveAccumulator resolveCurveAccumulator2(CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3, boolean flag) {
      return curveAccumulator2.count4 >= 80 && this.count4 - curveAccumulator2.count4 >= 40 ? this.resolveCurveAccumulator2(curveAccumulator2, curveAccumulator3) : this.resolveCurveAccumulator(curveAccumulator2, curveAccumulator3, flag);
   }

   public boolean isActive3() {
      return this.isActive2() ? false : (this.countArray[this.count2 + this.count4 - 1] & 1) == 1;
   }

   public void applyCurveAccumulatorByCurveAccumulator(CurveAccumulator curveAccumulator2, int count5) {
      CurveAccumulator curveAccumulator3 = null;
      curveAccumulator3 = new CurveAccumulator(curveAccumulator2);
      if (curveAccumulator3.count2 + curveAccumulator3.count4 >= count5) {
         curveAccumulator3.count2 = curveAccumulator3.count2 + curveAccumulator3.count4 - count5;
         curveAccumulator3.count4 = count5;
      }

      curveAccumulator3.updateState3();
      this.applyCurveAccumulator(curveAccumulator3);
   }

   public void applyCurveAccumulator(CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3) {
      int count5 = 0;
      int count6 = 0;
      int count7 = 0;
      long value = 0L;
      int count8 = 0;
      int count9 = 0;
      long value2 = 0L;
      int count10 = 0;
      long value3 = 0L;
      long value4 = 0x1294628b053e85b9L;
      long value5 = -0x66a99e1d27772a5aL;

      while (true) {
         int count11 = (int)(value4 % 27L);
         if (count11 == 22) {
            value = 0L;
            value4 = (value4 ^ 0x3cfee1c5fe3e8eb1L) - 0x1f6e040a5c62fd62L;
            value5 = (value5 ^ -0x10ebe1810cf230dbL) + 0x22144b4451cac4a5L;
         } else if (count11 == 14) {
            count10 = count6 + count8;
            value4 = (value4 ^ -0x23fa41dd7bb86f56L) - 0x64988e6609ae8852L;
            value5 = (value5 ^ -0x2f18eb48fd121246L) - 0x14ab7ac0af5748e4L;
         } else if (count11 != 2) {
            guardBlock:
            if (count11 != 19) {
               if (count11 == 12) {
                  count9 = count6 - 1;
                  value4 = (value4 ^ 0x33dd861e62d6a374L) + 0x26c98e2130ada053L;
                  value5 = (value5 ^ -0x1da9c3f80455e2a5L) - 0x3f7c973159f23aaeL;
                  continue;
               }

               if (count11 == 26) {
                  count5 = this.count4;
                  value4 = (value4 ^ -0x10e81c1df7795ce4L) + 0x3daeec31978f4622L;
                  value5 = (value5 ^ 0x3d458b0afbf31482L) + 0x356e4145ca08b741L;
                  continue;
               }

               if (count11 == 20) {
                  value2 = ((long)curveAccumulator2.countArray[count8 + curveAccumulator2.count2] & 4294967295L) * ((long)this.countArray[count5 - 1 + this.count2] & 4294967295L) + value;
                  value4 = (value4 ^ -0x218c8758408c1566L) - 0x4001981b16292eb8L;
                  value5 = (value5 ^ 0x1e9ae319342fe32dL) - 0x6939bcf5b6cad6c2L;
                  continue;
               }

               if (count11 == 0) {
                  count7 = count5 + count6;
                  value4 = (value4 ^ -0x1812454e9fad00d8L) + 0x5f1591984d1da787L;
                  value5 = (value5 ^ -0x6343e2d26d753733L) - 0x5914ad97e5d8f1cbL;
                  continue;
               }

               searchBlock: {
                  if (count11 != 5) {
                     if (count11 == 4) {
                        count8 = count6 - 1;
                        value4 = (value4 ^ 0x3c7148b1eb1c3787L) - 0x3b191f43d7488de6L;
                        value5 = (value5 ^ -0x1456d7ee999e4d18L) + 0x5159875899e9b390L;
                        continue;
                     }

                     if (count11 == 8) {
                        if (curveAccumulator3.countArray.length >= count7) {
                           value4 = (value4 ^ -0x1cf3a3d923370284L) - 0x580e2a87c4940e87L;
                           value5 = (value5 ^ 0x7bd3efa625d69524L) - 0x2211c8c359c5e3e5L;
                        } else {
                           value4 = (value4 ^ -0x69947a18ab192e9dL) + 0x7309b75b3f31dd45L;
                           value5 = (value5 ^ -0x6df6f30db0594c85L) + 0x76f30390351392e4L;
                        }
                        continue;
                     }

                     if (count11 == 18) {
                        break;
                     }

                     if (count11 == 15) {
                        value = value3 >>> 32;
                        value4 = (value4 ^ -0x49df5dc22c905feeL) - 0x7813f324ceca57f7L;
                        value5 = (value5 ^ -0x2e512ca9f55afdc7L) + 0x7658ae4d306fb294L;
                        continue;
                     }

                     if (count11 == 16) {
                        curveAccumulator3.count2 = 0;
                        value4 = (value4 ^ -0x55757b330e0c3f56L) - 0x7f657261ac81dea5L;
                        value5 = (value5 ^ -0xc85afea95e59022L) + 0x43d18e761bfbb422L;
                        continue;
                     }

                     if (count11 == 13) {
                        curveAccumulator3.countArray[count10] = (int)value3;
                        value4 = (value4 ^ -0x602f8ecc7a1925aeL) - 0x5f52a5ee44a7e281L;
                        value5 = (value5 ^ 0x898a416049679ffL) + 0x4bb9ce624b321ad0L;
                        continue;
                     }

                     if (count11 == 1) {
                        break searchBlock;
                     }

                     if (count11 != 23) {
                        if (count11 == 11) {
                           curveAccumulator3.countArray[count9] = (int)value2;
                           value4 = (value4 ^ 0x198c96930fd342a1L) - 0x168b82ea1c911f7fL;
                           value5 = (value5 ^ -0x45bb907d77389e97L) + 0x38862545fc1093a6L;
                           continue;
                        }

                        if (count11 == 10) {
                           value = 0L;
                           value4 = (value4 ^ 0x2cd190fd9b285bccL) - 0x540af67de95cc4deL;
                           value5 = (value5 ^ -0x65f6e7dce5601497L) + 0x7130243527956cd3L;
                           continue;
                        }

                        if (count11 == 3) {
                           break guardBlock;
                        }

                        if (count11 == 6) {
                           curveAccumulator3.countArray = new int[count7];
                           value4 = (value4 ^ 0x6a31e660ccb481acL) + 0x39b1be7cc0b481cdL;
                           value5 = (value5 ^ -0x3cfc173b435847f1L) + 0x53b22a3b8b0eb6efL;
                           continue;
                        }

                        if (count11 == 24) {
                           count6 = curveAccumulator2.count4;
                           value4 = (value4 ^ -0x67a873b954dc0da3L) + 0x79fcfe2cd065e4cdL;
                           value5 = (value5 ^ 0x7fce68f45feafaecL) - 0xebd3216ba6d04f4L;
                           continue;
                        }

                        if (count11 == 7) {
                           count9 = count6 + count5 - 1;
                           value4 = (value4 ^ -0x5c9b14f167057976L) - 0x22a17de1246e9a57L;
                           value5 = (value5 ^ -0x66706f285ad75f58L) + 0x29d7c7725fc99e62L;
                           continue;
                        }

                        if (count11 == 21) {
                           count8 = count5 - 2;
                           value4 = (value4 ^ -0x2175a7306bd67d6bL) + 0x773ce92193dee9cbL;
                           value5 = (value5 ^ 0x4da9cd6ff4c2ad7bL) + 0x638e412be8318bdaL;
                           continue;
                        }

                        if (count11 != 25) {
                           if (count11 != 17) {
                              value = value2 >>> 32;
                              value4 = (value4 ^ 0x6108c4fdf7d2f094L) - 0x23fc2b07dfda2637L;
                              value5 = (value5 ^ -0x4b3847efde6248efL) + 0x6b0f575db5baa8a2L;
                           } else {
                              value3 = ((long)curveAccumulator2.countArray[count9 + curveAccumulator2.count2] & 4294967295L) * ((long)this.countArray[count8 + this.count2] & 4294967295L)
                                 + ((long)curveAccumulator3.countArray[count10] & 4294967295L)
                                 + value;
                              value4 = (value4 ^ 0x4b3471a32da75933L) - 0x3936d4cd7d0428aeL;
                              value5 = (value5 ^ -0x1d03bb4345dbfe42L) - 0x500a21208a36dd89L;
                           }
                           continue;
                        }

                        count9--;
                        count10--;
                        value4 = (value4 ^ 0x31d1fa266a64dc40L) - 0x296a29cc45786ac9L;
                        value5 = (value5 ^ 0x34fee334f4fbac4dL) + 0x7f877ca30d6a0137L;
                        break searchBlock;
                     }
                  } else {
                     count8--;
                     value4 = (value4 ^ 0x1a2ee0356f549880L) - 0x5669e8a75bccce27L;
                     value5 = (value5 ^ 0xa5e6d228c29e94aL) + 0x188ae9b7eb4c5a7aL;
                  }

                  if (count8 < 0) {
                     curveAccumulator3.updateState3();
                     break;
                  }

                  value4 = (value4 ^ 0x1a7d68d80e5abfcbL) + 0x41c089449eb2a587L;
                  value5 = (value5 ^ -0x20ac652af70ed96aL) - 0x1154b279998418d7L;
                  continue;
               }

               if (count9 < 0) {
                  long value6 = (value4 ^ 0x642144b61cae78ecL) + 0x3e7a24240672eea5L;
                  long value7 = (value5 ^ -0x65433da87bbccd37L) - 0x203a598d9e4e8f21L;
                  curveAccumulator3.countArray[count8] = (int)value;
                  value4 = (value6 ^ -0x19a888a549bf88b3L) - 0x57c149849543b6c6L;
                  value5 = (value7 ^ -0xef23e0bdd5faaa6L) + 0x16b085a247c0c6baL;
               } else {
                  value4 = (value4 ^ -0x7047dda4406c7a69L) - 0x773c934f1127034bL;
                  value5 = (value5 ^ -0x4b7f9a2de07d33ebL) - 0x62014a205a224d84L;
               }
               continue;
            } else {
               count8--;
               count9--;
               value4 = (value4 ^ -0x198c2731973993dL) - 0x75768946791d55ebL;
               value5 = (value5 ^ 0x2df6f6a209260445L) - 0x4b82b0211d646e98L;
            }

            if (count8 < 0) {
               long value8 = (value4 ^ -0x101c661c81499d04L) + 0x78f5da9efd17963aL;
               long value9 = (value5 ^ 0x26c686108d605e3fL) + 0x4b8f9f8f86e2285eL;
               curveAccumulator3.countArray[count5 - 1] = (int)value;
               value4 = (value8 ^ -0x43ab2478a12eb3c7L) - 0x6161978e2bb796fbL;
               value5 = (value9 ^ 0x437eb834255515a9L) - 0x5c41e28a404ff6a7L;
            } else {
               value4 = (value4 ^ -0x6a6e0ca1ae3db5ecL) + 0x76e37db3507339fdL;
               value5 = (value5 ^ 0x66d59c35ae69db4L) - 0x536a5ce625c170f2L;
            }
         } else {
            curveAccumulator3.count4 = count7;
            value4 = (value4 ^ 0x5b31899be71fdf37L) + 0xa4980315c7cead9L;
            value5 = (value5 ^ -0x1ce9b2cdf1c875e7L) + 0x17ef4ccd1116ed0aL;
         }
      }
   }

   public static long computeTimestamp(long value) {
      long value2 = 0L;
      long value3 = -0x15ca1a1697e2f941L;
      value3 = (value3 ^ -0x81489c2c9ca4837L) - 0x66b0dfeb911ef687L;
      value2 = value * (2L - value * value);
      value3 = (value3 ^ 0x2b600715fc13f97L) + 0x4f4d058f24d977baL;
      value2 *= 2L - value * value2;
      value3 = (value3 ^ -0x727563ad2918feceL) - 0x41d0e4d7d9d31384L;
      value2 *= 2L - value * value2;
      value3 = (value3 ^ 0x5089da1438c4b15bL) + 0x51af98e8ad8ee95eL;
      value2 *= 2L - value * value2;
      value3 = (value3 ^ 0x5fee291ecaca48cbL) + 0x1faac681d4866d03L;
      value2 *= 2L - value * value2;
      value3 = (value3 ^ 0x10979477c9d6f5e0L) - 0x1915723b54de149L;
      if (!enabled) {
         value3 = (value3 ^ 0x312617ceeff0a71bL) + 0x5221cc7dccf3a15cL;
         if (value2 * value != (value3 % 512483L ^ -227617L)) {
            throw new AssertionError();
         }
      }

      return value2;
   }

   public static CurveAccumulator resolveCurveAccumulator(CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3, int count5) {
      CurveAccumulator curveAccumulator4 = curveAccumulator2;
      CurveAccumulator curveAccumulator5 = null;
      int count6 = 0;
      int count7 = 0;
      int count8 = 0;
      int count9 = 0;
      long value = 0x825ffb8876d1d31L;
      long value2 = -0x30a982028368c32L;

      while (true) {
         int count10 = (int)(value % 19L);
         if (count10 != 3) {
            if (count10 == 18) {
               CurveAccumulator curveAccumulator6 = new CurveAccumulator();
               curveAccumulator4 = curveAccumulator4.resolveCurveAccumulator4(curveAccumulator3, curveAccumulator6);
               break;
            }

            if (count10 != 9) {
               if (count10 == 17) {
                  break;
               }

               if (count10 != 1) {
                  if (count10 == 14) {
                     count6 = -computeCountByIndex(curveAccumulator3.countArray[curveAccumulator3.count2 + curveAccumulator3.count4 - 1]);
                     value = (value ^ -0x2f03cbff2af276eeL) - 0x65a88107c7f086b3L;
                     value2 = (value2 ^ 0x78f9cff378b9f820L) + 0x770acd05e43362c1L;
                     continue;
                  }

                  if (count10 == 13) {
                     curveAccumulator5 = new CurveAccumulator();
                     value = (value ^ -0x7661288e369c6b98L) - 0x908f47e6e8e5868L;
                     value2 = (value2 ^ 0x5f2c3b2893426583L) + 0x2e7b194d066fcbf7L;
                     continue;
                  }

                  if (count10 == 6) {
                     count8 = count5 >> 5;
                     value = (value ^ -0x676764fc1b91c4e8L) + 0x7fc70038feae4d12L;
                     value2 = (value2 ^ -0x2ead0daf46f77ca5L) + 0x3072344c84b244adL;
                     continue;
                  }

                  if (count10 == 16) {
                     curveAccumulator4.applyIndex(count7);
                     value = (value ^ -0x3b04f7729907acacL) + 0x12bdf9a1798e2870L;
                     value2 = (value2 ^ -0x369d0f6b68521f86L) + 0x76b78efdcfd5de1dL;
                     continue;
                  }

                  if (count10 == 12) {
                     count8 = count6 * curveAccumulator4.countArray[curveAccumulator4.count2 + curveAccumulator4.count4 - 1];
                     value = (value ^ -0x3e6a82429d5e07daL) + 0x1628237e2ae15befL;
                     value2 = (value2 ^ -0x3603ebd1faed72d8L) - 0x53cba5a3fe19057aL;
                     continue;
                  }

                  if (count10 == 5) {
                     curveAccumulator3.applyCurveAccumulator(count9, curveAccumulator5);
                     value = (value ^ 0x60a66333aa27b54cL) + 0x404418d04d05d4c9L;
                     value2 = (value2 ^ 0x43c360965d8a14eL) + 0x382aa751f75c0589L;
                     continue;
                  }

                  if (count10 == 15) {
                     curveAccumulator4.applyCurveAccumulator(curveAccumulator5);
                     value = (value ^ 0x67a730556c99bbb4L) + 0x6d0f094686be7dadL;
                     value2 = (value2 ^ -0x7491f8f6d65daf6L) - 0x32db196cd32cbd3dL;
                     continue;
                  }

                  if (count10 == 11) {
                     count8 &= (1 << count7) - 1;
                     value = (value ^ -0x66df88370a83d7b7L) - 0x29568df2bc60c696L;
                     value2 = (value2 ^ -0xe0bd4748318c6b3L) - 0x45de2587facb0b51L;
                     continue;
                  }

                  if (count10 == 10) {
                     count7 = 0;
                     value = (value ^ 0x189618d7f8bf6ffaL) + 0xe4038605d00a63L;
                     value2 = (value2 ^ 0x3a6c096861b04bdfL) - 0xa84e1ed87552caL;
                     continue;
                  }

                  if (count10 != 0) {
                     if (count10 != 4) {
                        if (count10 != 7) {
                           if (count10 != 2) {
                              if (curveAccumulator4.computeCount(curveAccumulator3) < 0) {
                                 break;
                              }

                              value = (value ^ 0x687ba3b0208218d3L) - 0x34f59713546cc52fL;
                              value2 = (value2 ^ -0x2202f02605bd4e7cL) + 0x675b2ced2819d140L;
                              continue;
                           }

                           if (count7 == 0) {
                              value = (value ^ 0x13c8568f6426ad5L) - 0x5f28a023587aa92aL;
                              value2 = (value2 ^ 0x2329f659eb8d2444L) + 0x7b9ac97c1d8c9ea4L;
                           } else {
                              value = (value ^ 0x22c1b1840ca45477L) - 0x3f88fd6d7dd8cc4cL;
                              value2 = (value2 ^ 0x6eaa05cb4715dda6L) - 0x62d2d0b19ba003fbL;
                           }
                           continue;
                        }

                        curveAccumulator4.count4--;
                        value = (value ^ -0xab1cf1eb3ee14fcL) + 0x7fe91f523e9af84eL;
                        value2 = (value2 ^ -0x59a4bfdc1bd21ef7L) - 0x294fa8c52167a008L;
                        continue;
                     }

                     curveAccumulator4.applyCurveAccumulator(curveAccumulator5);
                     value = (value ^ 0x7e219cb13376349dL) - 0x393e7c39d78f1a55L;
                     value2 = (value2 ^ -0x168db40a83047a3L) + 0x1bd96d9ebbdc6e01L;
                     continue;
                  }
               } else {
                  count7++;
                  value = (value ^ 0x799cc6c6bdbbe718L) - 0x39612d89e3cb3f38L;
                  value2 = (value2 ^ -0x4f1a230ccee6cb8eL) + 0x1ef8a8f94b5de514L;
               }

               if (count7 >= count8) {
                  long value3 = (value ^ -0x3a847447d3fcf885L) + 0x7ef75f340d64612bL;
                  long value4 = (value2 ^ 0x29e76ef504857878L) - 0x67f9a2fd40d9e0eaL;
                  count7 = count5 & 31;
                  value = (value3 ^ -0x1527cca4056db008L) + 0x6ec2e496ca6a1d8cL;
                  value2 = (value4 ^ -0xf6a4303c81628d1L) + 0x3a463130b6073b98L;
               } else {
                  value = (value ^ -0x5957a504ecf26653L) + 0x4ca3a1ffb95fc85eL;
                  value2 = (value2 ^ 0x6f1b9b0f8c959c9eL) + 0x6c394c38d122567eL;
               }
            } else {
               curveAccumulator3.applyCurveAccumulator(count8, curveAccumulator5);
               value = (value ^ -0x6dd555cf55dddef0L) + 0x290c50ecab2d1b52L;
               value2 = (value2 ^ 0x4ca3b7022aeb3f93L) + 0x1d855a03c878df23L;
            }
         } else {
            count9 = count6 * curveAccumulator4.countArray[curveAccumulator4.count2 + curveAccumulator4.count4 - 1];
            value = (value ^ -0x3ea7337afa8290c3L) + 0x50e17ba60414af42L;
            value2 = (value2 ^ -0x6af7a9e673bfee95L) - 0x35ec804db8cdf1f4L;
         }
      }

      return curveAccumulator4;
   }

   public int computeCount2(CurveAccumulator curveAccumulator2) {
      CurveAccumulator curveAccumulator3 = curveAccumulator2;
      CurveAccumulator curveAccumulator4 = null;
      int[] countArray2 = null;
      int count5 = 0;
      CurveAccumulator curveAccumulator5 = null;
      long value = 0L;
      int count6 = 0;
      int count7 = 0;
      int count8 = 0;
      int count9 = 0;
      long value2 = 0x5159f2cd76c923abL;
      long value3 = -0x2f6c3ea31db0025dL;

      while (true) {
         int count10 = (int)(value2 % 28L);
         if (count10 == 12) {
            count6--;
            value = ((long)curveAccumulator4.countArray[count6 + curveAccumulator4.count2] & 4294967295L) - (long)((int)(-(value >> 32)));
            value2 = (value2 ^ 0x52cf720d9733108fL) + 0x7b1da140c4a5311L;
            value3 = (value3 ^ -0x7be1686d412f709bL) - 0x57fe0526458e8125L;
         } else if (count10 != 21) {
            if (count10 == 17) {
               return 0;
            }

            long value4;
            long value5;
            if (count10 != 24) {
               if (count10 == 25) {
                  countArray2[count8--] = (int)value;
                  value2 = (value2 ^ -0x50610853011ae544L) + 0x5b11eda5b7f7bc55L;
                  value3 = (value3 ^ -0x14e218ea624f5163L) + 0x749e50286937a15dL;
                  continue;
               }

               if (count10 == 27) {
                  curveAccumulator5 = curveAccumulator4;
                  value2 = (value2 ^ -0x7d62748ba57adcf6L) - 0x624f80473480eccfL;
                  value3 = (value3 ^ 0x76c9910fd991449fL) + 0x5d21302350130c7cL;
                  continue;
               }

               if (count10 != 5) {
                  if (count10 == 15) {
                     this.updateState2();
                     return 0;
                  }

                  if (count10 == 9) {
                     if (countArray2.length >= count9) {
                        value2 = (value2 ^ -0x3bbe90cc6442e29aL) + 0x25adcdc41647e202L;
                        value3 = (value3 ^ 0x54ab2e9e613bd142L) + 0xa8e79de9577a07aL;
                     } else {
                        value2 = (value2 ^ -0x63424704dbfb0fd0L) + 0x5a67827d1e8d313eL;
                        value3 = (value3 ^ 0x5983a58f379ac2beL) - 0x20a23ead53aab4b5L;
                     }
                     continue;
                  }

                  if (count10 == 22) {
                     count9 = curveAccumulator4.count4;
                     value2 = (value2 ^ 0x358aa3b7ff457ba3L) + 0x23fa8560e69437c8L;
                     value3 = (value3 ^ -0x370efc2e0fc2d9d0L) - 0x14a94453704c166L;
                     continue;
                  }

                  if (count10 == 1) {
                     curveAccumulator3 = curveAccumulator5;
                     value2 = (value2 ^ 0x6c85c29f58928b0cL) - 0x2ba877bd4ac228ebL;
                     value3 = (value3 ^ 0x405abd4211d2866bL) - 0x15c6af4bcbdd3f79L;
                     continue;
                  }

                  if (count10 == 11) {
                     count8 = countArray2.length - 1;
                     value2 = (value2 ^ -0x37674204c5074e78L) - 0x5bfabb7a83918c6aL;
                     value3 = (value3 ^ 0x346b5a5851a4e985L) + 0x388db8d0a39d5496L;
                     continue;
                  }

                  if (count10 == 3) {
                     count6 = curveAccumulator4.count4;
                     value2 = (value2 ^ 0xe4f324d5eccb7e7L) - 0x820741efdd193ccL;
                     value3 = (value3 ^ 0x226cc130bb9ddc86L) - 0x3ebf5c672aba9c84L;
                     continue;
                  }

                  if (count10 == 0) {
                     this.count2 = this.countArray.length - count9;
                     value2 = (value2 ^ 0x3e402f90a2d93052L) - 0x24e1a49217160508L;
                     value3 = (value3 ^ 0x7ad0721f32b2c289L) + 0x2c417f9a292a2771L;
                     continue;
                  }

                  if (count10 == 14) {
                     this.updateState3();
                     return count5;
                  }

                  if (count10 == 20) {
                     countArray2 = this.countArray;
                     value2 = (value2 ^ -0x75c85c479b28eb31L) + 0x65f56155bd3d8f51L;
                     value3 = (value3 ^ -0x121ca0c630b133d8L) + 0x788b71e7a5d61e86L;
                     continue;
                  }

                  if (count10 == 2) {
                     curveAccumulator4 = curveAccumulator3;
                     value2 = (value2 ^ -0x1f17eb847a3ae363L) - 0x79057f617e56ed4eL;
                     value3 = (value3 ^ 0x5a0e962875d0b877L) + 0x1e852478ac150f93L;
                     continue;
                  }

                  if (count10 == 6) {
                     if (count5 != 0) {
                        value2 = (value2 ^ 0x31d1af5c86b7853cL) + 0x2ba484f7af2582a4L;
                        value3 = (value3 ^ -0x4aec1516bb2f2e73L) - 0x5b6582a486087347L;
                     } else {
                        value2 = (value2 ^ 0x4f5b0d45d3407581L) - 0x7323f22a0c1a047cL;
                        value3 = (value3 ^ 0x74425c0f2b697c5eL) + 0x7b05ee6ecc795bbL;
                     }
                     continue;
                  }

                  if (count10 == 23) {
                     countArray2 = new int[count9];
                     value2 = (value2 ^ 0x24977b3f56e4e8c7L) - 0x9c60413744fcc4fL;
                     value3 = (value3 ^ -0x477858ada2af73c5L) - 0x222d21910a0f1802L;
                     continue;
                  }

                  if (count10 == 7) {
                     curveAccumulator4 = this;
                     value2 = (value2 ^ -0x3e33cdc83e67b41cL) - 0x5576991764176597L;
                     value3 = (value3 ^ 0x1092002a8ddb623eL) + 0x46820960bdff2b92L;
                     continue;
                  }

                  if (count10 == 8) {
                     count5 = curveAccumulator4.computeCount(curveAccumulator3);
                     value2 = (value2 ^ -0x3983e656923b981bL) + 0x66bde7d26c6266ddL;
                     value3 = (value3 ^ 0x3c940e1e35fa6a95L) + 0x410e4217224a228fL;
                     continue;
                  }

                  if (count10 == 19) {
                     count6--;
                     count7--;
                     value = ((long)curveAccumulator4.countArray[count6 + curveAccumulator4.count2] & 4294967295L)
                        - ((long)curveAccumulator3.countArray[count7 + curveAccumulator3.count2] & 4294967295L)
                        - (long)((int)(-(value >> 32)));
                     value2 = (value2 ^ 0xd5a3a17632b0d10L) + 0x18ed6375a21b255eL;
                     value3 = (value3 ^ 0x6fbe6c4b4da1adc4L) - 0xe949206c0b2ec14L;
                     continue;
                  }

                  if (count10 == 4) {
                     countArray2[count8--] = (int)value;
                     value2 = (value2 ^ -0x3bb6dd933720329bL) - 0x71313b7af9703095L;
                     value3 = (value3 ^ -0x558cfe02827a2f04L) + 0x34b9b98563e1104bL;
                     continue;
                  }

                  if (count10 != 18) {
                     if (count10 == 10) {
                        if (count5 >= 0) {
                           value2 = (value2 ^ 0x3040051584e11a7dL) + 0x1fc6d381136bd57fL;
                           value3 = (value3 ^ -0x6cc646474ac1c69bL) - 0x5f9df72d83de49ceL;
                        } else {
                           value2 = (value2 ^ -0x3ef074518e3a8618L) + 0x460be86be45a2e9dL;
                           value3 = (value3 ^ -0x5f1817e2d9674d8eL) - 0xfd58bf5f533a0e6L;
                        }
                        continue;
                     }

                     if (count10 != 13) {
                        if (count10 != 16) {
                           this.count4 = count9;
                           value2 = (value2 ^ 0x5bbd66476e0dbe68L) - 0x4b50a5d2a179b3aL;
                           value3 = (value3 ^ 0x32f66c43267f9d3bL) + 0x6787b2480e1118e7L;
                        } else {
                           count7 = curveAccumulator3.count4;
                           value2 = (value2 ^ -0x40bd5b10800f8ec5L) - 0x6c93e5d990a26fb8L;
                           value3 = (value3 ^ 0x6ac98b9bea11c4f8L) - 0x433279e648c8da8dL;
                        }
                        continue;
                     }

                     return count5;
                  }

                  value2 = (value2 ^ -0x2958d26a9f57cca0L) + 0x7d5ff1e0277b2507L;
                  value3 = (value3 ^ 0x46e201b5ea4f868fL) + 0x2bb5b5d4f6a70a5dL;
               }

               if (count7 > 0) {
                  value2 = (value2 ^ -0x1fc829b863194019L) - 0x678fb0ea666742ebL;
                  value3 = (value3 ^ 0x3ff6a9f942da02f0L) - 0x21b8b9373d9a9046L;
                  continue;
               }

               value4 = (value2 ^ -0x1b7835452560ec9bL) + 0x7bbf3840d3adfcf6L;
               value5 = (value3 ^ 0xbe735f3d0ffc62eL) + 0x18891e0332f739feL;
            } else {
               value4 = (value2 ^ -0x17294a1c7e2fa5b2L) + 0x712289bf21eaf690L;
               value5 = (value3 ^ -0x1820dce3532f1e69L) + 0x5cd96c26c47d7942L;
            }

            if (count6 <= 0) {
               long value6 = (value4 ^ -0x330fd2c4113ebd5dL) + 0x3302ed777aa59d1bL;
               long value7 = (value5 ^ 0xd3ab6f4ecd57af4L) - 0x7dca1f6efb7ffd4bL;
               this.countArray = countArray2;
               value2 = (value6 ^ -0x310ad3cbf56e3f61L) + 0x4b36fcf8687ec4cfL;
               value3 = (value7 ^ 0x4ac387032823a1acL) + 0x787c994246c9ae9fL;
            } else {
               value2 = (value4 ^ -0x31452321ae87e9acL) + 0x7515464c76bf256L;
               value3 = (value5 ^ -0x3c817389d19cdafdL) + 0x495fbabbdb4d8c5fL;
            }
         } else {
            value = 0L;
            value2 = (value2 ^ -0x4f197a762ab0222fL) - 0x3fc10a74b77fa0a9L;
            value3 = (value3 ^ 0x7e4d74eb148091c3L) - 0x4384df5b2ba4fd2fL;
         }
      }
   }

   private int computeCountByIndexArray(int[] countArray2, int[] countArray3, int count5, int count6, int count7) {
      int count8 = count7;
      long value = 0L;
      long value2 = 0L;
      int count9 = 0;
      long value3 = 0L;
      long value4 = 0L;
      long value5 = 0x4b8b9b96f0b5c322L;
      long value6 = -0x477e85beb121721bL;

      while (true) {
         int count10 = (int)(value5 % 8L);
         if (count10 != 7) {
            if (count10 == 5) {
               count8 += count6;
               value5 = (value5 ^ -0x6c346a8339d9a022L) - 0x7c2cf42fffeec8d5L;
               value6 = (value6 ^ 0x1ac3ab10b42a8e0dL) - 0x286495097e4904b2L;
               continue;
            }

            if (count10 == 2) {
               value = (long)count5 & 4294967295L;
               value5 = (value5 ^ -0x21e32433f12463fbL) - 0x5bb0f37c00759c7cL;
               value6 = (value6 ^ 0x3bc76edd2a90e898L) + 0x164370fd9c92ba29L;
               continue;
            }

            if (count10 == 1) {
               value4 = (long)countArray2[count8--] - value3;
               value5 = (value5 ^ -0x40f06bc7ae3dc97aL) - 0x6b806a8e37f499b7L;
               value6 = (value6 ^ 0x26b72e83917da76L) - 0x51cecbfb426543f1L;
               continue;
            }

            if (count10 == 6) {
               count9 = count6 - 1;
               value5 = (value5 ^ 0x7479e0c8599488aaL) + 0x4cfc02bc17a3262bL;
               value6 = (value6 ^ 0x3dcb466329ccb7cL) - 0x9b5260d1d00d60aL;
               continue;
            }

            if (count10 == 3) {
               value2 = 0L;
               value5 = (value5 ^ 0x5195183f1a5eaebaL) - 0x40b380aea560b4cL;
               value6 = (value6 ^ 0xfddc0fd67298c61L) + 0x62bd1b2c4d540331L;
               continue;
            }

            if (count10 != 0) {
               value3 = ((long)countArray3[count9] & 4294967295L) * value + value2;
               value5 = (value5 ^ -0x666f2218a8aee9f0L) - 0x59cfdcf779b65f23L;
               value6 = (value6 ^ -0x2acd99c679a17a57L) - 0x13de0d8a7dbdbe7fL;
               continue;
            }

            long value7 = value3 >>> 32;
            long value8;
            long value9;
            byte byteValue;
            if ((value4 & 4294967295L) <= ((long)(~((int)value3)) & 4294967295L)) {
               value8 = (value5 ^ -0x2429b51c499456a3L) - 0x562e4521ad95595dL;
               value9 = (value6 ^ 0x189c9d3de777a74fL) - 0x8638d3adc945d41L;
               byteValue = 0;
            } else {
               byteValue = 1;
               value8 = (value5 ^ 0x5c300719825e79ebL) + 0x23efff187e5dd615L;
               value9 = (value6 ^ -0x6b3edd27fe7c30eaL) + 0x733f2128017f3b16L;
            }

            value2 = value7 + (long)byteValue;
            count9--;
            value5 = (value8 ^ -0x1a9e5dd3e3c847c5L) - 0x2bb08966e0116b24L;
            value6 = (value9 ^ -0x6677962a00dd3e46L) - 0x28b967c1e7625af8L;
         }

         if (count9 < 0) {
            return (int)value2;
         }

         value5 = (value5 ^ 0x25185aa3ed290847L) - 0x2661787ba1e4ed0cL;
         value6 = (value6 ^ -0x3b4d060c46acd8a8L) - 0x6d9333a4181b699L;
      }
   }

   public void applyIndex(int count5, int count6) {
      this.countArray[this.count2 + count5] = count6;
   }

   public CurveAccumulator resolveCurveAccumulator2(CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3) {
      int count5 = 0;
      int count6 = 0;
      int count7 = 0;
      int count8 = 0;
      int count9 = 0;
      long value = 0L;
      int count10 = 0;
      CurveAccumulator curveAccumulator4 = null;
      CurveAccumulator curveAccumulator5 = null;
      int count11 = 0;
      CurveAccumulator curveAccumulator6 = null;
      CurveAccumulator curveAccumulator7 = null;
      CurveAccumulator curveAccumulator8 = null;
      CurveAccumulator curveAccumulator9 = null;
      int count12 = 0;
      long value2 = 0x72eaa940165b44ebL;
      long value3 = -0x72447299aa902771L;

      while (true) {
         int count13 = (int)(value2 % 31L);
         if (count13 == 25) {
            count5 = this.count4;
            value2 = (value2 ^ 0x793e61c071805001L) + 0x533a980ca0466ba3L;
            value3 = (value3 ^ 0x7389835f1ccf8d2dL) - 0x2d75de27d77a08ffL;
         } else if (count13 == 15) {
            count12 = count11 - 2;
            value2 = (value2 ^ 0x749d8414bae19b0L) + 0xfbed8c24327edf9L;
            value3 = (value3 ^ -0xedbcb9e16d46b7bL) - 0x44760025b942bb6L;
         } else if (count13 == 5) {
            curveAccumulator7.applyCurveAccumulator2(curveAccumulator9, count9);
            value2 = (value2 ^ 0xb2d231454ae0286L) + 0xb884d6c80fc9a27L;
            value3 = (value3 ^ -0x4ddb3ffff7d36141L) + 0xbf6d309e2958480L;
         } else if (count13 == 10) {
            curveAccumulator5.applyIndexByIndex(count10);
            value2 = (value2 ^ -0x751763b424b7a5e8L) + 0x36be34dd6a5d80eL;
            value3 = (value3 ^ -0x762cdc82e66c62d8L) - 0x280372625212ba7eL;
         } else if (count13 == 12) {
            curveAccumulator4 = new CurveAccumulator(curveAccumulator2);
            value2 = (value2 ^ 0x5124e21d0db42856L) + 0x15d3d55dccbf2458L;
            value3 = (value3 ^ 0x448ff58609104d99L) + 0x24ae6c6cc7a8ee98L;
         } else if (count13 != 1) {
            if (count13 != 6) {
               if (count13 == 7) {
                  count7 = 1 << 32 - Integer.numberOfLeadingZeros(count6 / 80);
                  value2 = (value2 ^ 0x3a75584a5b5bf824L) + 0x8557b26905a325fL;
                  value3 = (value3 ^ 0x1957d5da908ab75cL) + 0x7ae719f93f5cedbL;
                  continue;
               }

               if (count13 == 9) {
                  curveAccumulator9 = curveAccumulator7.resolveCurveAccumulatorByCurveAccumulator(curveAccumulator4, curveAccumulator8);
                  value2 = (value2 ^ -0x2955161d146e4b37L) + 0x627207f7e183e4a0L;
                  value3 = (value3 ^ -0x2e27966419ef03d2L) - 0x5b1877b651166240L;
                  continue;
               }

               if (count13 == 4) {
                  count6 = curveAccumulator2.count4;
                  value2 = (value2 ^ -0x437b63f05831ab48L) - 0x7aeab9fce291d602L;
                  value3 = (value3 ^ -0x7573dcfdd35b7301L) - 0x5b932d2054fdda85L;
                  continue;
               }

               if (count13 == 19) {
                  count9 = count8 * count7;
                  value2 = (value2 ^ -0x20c55eac9d4d1ac1L) - 0x1fd1dea80a9c5992L;
                  value3 = (value3 ^ -0xfaf5319cfb5760aL) + 0x7664771f6974ed25L;
                  continue;
               }

               if (count13 == 3) {
                  curveAccumulator9.applyIndex(count10);
                  return curveAccumulator9;
               }

               if (count13 != 28) {
                  if (count13 != 18) {
                     if (count13 != 24) {
                        if (count13 == 16) {
                           curveAccumulator3.applyCurveAccumulator(curveAccumulator8);
                           value2 = (value2 ^ -0x6b7a3f07272e658aL) + 0x139f8f3356a599bbL;
                           value3 = (value3 ^ -0x37421478d9c135dcL) - 0x7523f4ccdc43ef8dL;
                           continue;
                        }

                        if (count13 == 30) {
                           int count14 = count11 - 1;
                           curveAccumulator6 = curveAccumulator5.resolveCurveAccumulator(count14, count11, count9);
                           value2 = (value2 ^ 0x226725ac1e16a31aL) - 0x1c4679c3d56420aL;
                           value3 = (value3 ^ -0x657499bbd97311dfL) + 0x19f8609f890d5c3dL;
                           continue;
                        }

                        if (count13 == 13) {
                           count10 = (int)Math.max(0L, value - curveAccumulator2.getTimestampAsTime());
                           value2 = (value2 ^ -0x577dac4ebc9bbceL) + 0x7d6f87da2e3008e7L;
                           value3 = (value3 ^ 0x53f50cfe9ad4b48cL) + 0x5665d027c19105feL;
                           continue;
                        }

                        if (count13 != 23) {
                           if (count13 != 17) {
                              if (count13 != 14) {
                                 if (count13 != 29) {
                                    if (count13 != 11) {
                                       if (count13 != 2) {
                                          if (count13 != 27) {
                                             if (count13 != 22) {
                                                if (count13 != 20) {
                                                   if (count13 != 8) {
                                                      if (count13 != 0) {
                                                         if (count13 != 21) {
                                                            curveAccumulator8 = new CurveAccumulator();
                                                            value2 = (value2 ^ 0x602012391259cb47L) - 0x59d5cc3a277f4ff0L;
                                                            value3 = (value3 ^ -0x716f253a8e79d790L) + 0x2abbfa266c08b05L;
                                                         } else if (count11 >= 2) {
                                                            value2 = (value2 ^ -0x3641026743cf445eL) - 0x4f7addff73ad6f76L;
                                                            value3 = (value3 ^ 0xf654a1f0b6cd72eL) + 0x7e219f2ebb223884L;
                                                         } else {
                                                            value2 = (value2 ^ -0x3d079b9d1432326aL) - 0x62f5a4f5db455ee3L;
                                                            value3 = (value3 ^ -0x4dfb52405ca99ac1L) + 0x5be7fe834f200d6eL;
                                                         }
                                                      } else {
                                                         curveAccumulator7.applyCurveAccumulator2(curveAccumulator6, count9);
                                                         value2 = (value2 ^ -0x3eab0fced83eba54L) + 0x58a0710426e0a432L;
                                                         value3 = (value3 ^ -0x45abf6b0fbb063f0L) + 0x7e3d9f1f17a31609L;
                                                      }
                                                   } else {
                                                      curveAccumulator4.applyIndexByIndex(count10);
                                                      value2 = (value2 ^ 0x1a29114e83b37437L) - 0x47699db925b13301L;
                                                      value3 = (value3 ^ 0x436b63a8280260b6L) - 0x1346d2313ffbe6e2L;
                                                   }
                                                } else {
                                                   int count15 = count12 * count9;
                                                   curveAccumulator3.applyCurveAccumulator(curveAccumulator8, count15);
                                                   value2 = (value2 ^ -0x5e8530f927d819e3L) + 0x472c8211c8821751L;
                                                   value3 = (value3 ^ 0x2368e6996508cdd5L) - 0x58bd3ce01af0a845L;
                                                }
                                             } else {
                                                count11 = (int)((curveAccumulator5.getTimestampAsTime() + value) / value);
                                                value2 = (value2 ^ 0x58d774d228963564L) - 0x573e12395ec7da6L;
                                                value3 = (value3 ^ 0x4f342ba6a2f1b4b5L) + 0x158af96f524a437cL;
                                             }
                                          } else {
                                             curveAccumulator5 = new CurveAccumulator(this);
                                             value2 = (value2 ^ 0x21297c7af27be889L) + 0x4cec8ae375db80c1L;
                                             value3 = (value3 ^ -0x1a8f58a6a82e083fL) + 0x11f79245e7dedb8aL;
                                          }
                                       } else {
                                          count11 = 2;
                                          value2 = (value2 ^ 0x61e9a24d8c68a073L) - 0x4196344b56b829aL;
                                          value3 = (value3 ^ -0x5d54601d6f703a99L) + 0x6deff5e963971604L;
                                       }
                                    } else {
                                       value = 32L * (long)count9;
                                       value2 = (value2 ^ -0x6e5eaa99be69bce0L) + 0x274a79e562ca8d1eL;
                                       value3 = (value3 ^ 0x7c55dcc3d7735328L) + 0x71edfe49fbe36f29L;
                                    }
                                 } else {
                                    int count16 = count12 - 1;
                                    curveAccumulator7 = curveAccumulator5.resolveCurveAccumulator(count16, count11, count9);
                                    value2 = (value2 ^ -0x204ebe14af93a728L) + 0x53f61a3a451ad794L;
                                    value3 = (value3 ^ 0x173103ca42aa523aL) - 0x1d879232f8eb68aeL;
                                 }
                              } else {
                                 count8 = (count6 + count7 - 1) / count7;
                                 value2 = (value2 ^ -0x3ef09e0fbd820016L) - 0x596d8f86144b118dL;
                                 value3 = (value3 ^ -0x55816923e65580daL) - 0x2f34f699c5b16373L;
                              }
                           } else {
                              curveAccumulator3.count2 = curveAccumulator3.count4 = 0;
                              value2 = (value2 ^ -0x18cce1e4f0158b71L) - 0x69cd9a77079c2663L;
                              value3 = (value3 ^ -0x5902c5fed2bfbc5bL) + 0x7c27b45226b43c3aL;
                           }
                           continue;
                        }

                        return curveAccumulator9;
                     }
                  } else {
                     count12--;
                     value2 = (value2 ^ 0x28522cb32dfe1ceL) - 0x2e0d028ec95272d8L;
                     value3 = (value3 ^ -0x58975b41f0171276L) + 0x42460082647d2820L;
                  }

                  if (count12 <= 0) {
                     long value4 = (value2 ^ -0x3684fd723790a53bL) + 0x62192550245d3ddfL;
                     long value5 = (value3 ^ -0x1fd3ecc438dde54cL) + 0x76a571004c4cbf12L;
                     curveAccumulator9 = curveAccumulator7.resolveCurveAccumulatorByCurveAccumulator(curveAccumulator4, curveAccumulator8);
                     value2 = (value4 ^ -0x58c1c0b499564c8cL) - 0x6cc1b1f651b818a4L;
                     value3 = (value5 ^ -0x207dc177864041f9L) - 0x79154e0673e940ebL;
                  } else {
                     value2 = (value2 ^ -0x46b161f32b1c814aL) + 0x6cbca8417289f27cL;
                     value3 = (value3 ^ -0x3ed845d52747bdbeL) + 0x4dfc3a76365e50c5L;
                  }
                  continue;
               }
            } else if (count5 >= count6) {
               value2 = (value2 ^ 0x7cea94f22469d8c9L) - 0x725196621e087d7L;
               value3 = (value3 ^ 0x7bd8fd58e8bc308bL) - 0x6bd9e1c5103e8634L;
               continue;
            }

            return this;
         } else {
            int count17 = count11 - 2;
            curveAccumulator7 = curveAccumulator5.resolveCurveAccumulator(count17, count11, count9);
            value2 = (value2 ^ -0x55b1d49732d22dcfL) + 0x580756afe7cec9bbL;
            value3 = (value3 ^ 0x5ce077f1d4c5ee8fL) - 0x7848116cbe5d0153L;
         }
      }
   }

   public void applyIndex6(int count5) {
      long value = 0x42bf8ae0c3a4a6c0L;

      while (true) {
         if ((int)(value % 4L) != 0) {
            this.updateState2();
            break;
         }

         if (count5 / 32 < this.count4) {
            this.applyIndex(count5);
            break;
         }

         value = (value ^ -0xcc9511781701fcdL) - 0x5f18aa08aa01cceaL;
      }
   }

   public void applyCurveAccumulator(int count5, CurveAccumulator curveAccumulator2) {
      long value = 0L;
      long value2 = 0x4fd101304c05c6f7L;
      long value3 = -0x130f883a2c835ca5L;

      while (true) {
         int count6 = (int)(value2 % 7L);
         if (count6 != 1) {
            if (count6 != 0) {
               if (count6 != 2) {
                  int[] countArray2;
                  if (count6 != 6) {
                     if (count6 == 4) {
                        curveAccumulator2.applyCurveAccumulatorByCurveAccumulator(this);
                        return;
                     }

                     if (count6 != 5) {
                        if (count5 != 1) {
                           long value4 = (value2 ^ -0x6d680948f462b6acL) + 0x62f40b508c2ec65bL;
                           long value5 = (value3 ^ -0x7ffd021e6eb83b0eL) + 0x67a332f0847a9776L;
                           if (count5 != 0) {
                              long value6 = (value4 ^ 0x6ddaf87a7f615870L) + 0x2d20e55d9dfafcbaL;
                              long value7 = (value5 ^ -0x45fc232081b32bcfL) + 0x78e9a03ac8cec8f0L;
                              value = (long)count5 & 4294967295L;
                              value2 = (value6 ^ 0x48078185d46fec2cL) + 0x4a324604c6d49272L;
                              value3 = (value7 ^ 0x66e85418b86935faL) - 0x2732c7b65c893676L;
                           } else {
                              value2 = (value4 ^ 0x1da5568c9dd81a7fL) - 0x46bb4415d72da1acL;
                              value3 = (value5 ^ -0x2f30d8658566c940L) - 0x5c6e9b60c225905cL;
                           }
                        } else {
                           value2 = (value2 ^ -0x12b900bdef38538dL) + 0x6b6efa0da5c26d3fL;
                           value3 = (value3 ^ -0x2d9c308ad7f8adbL) + 0x3b2c8810b343213bL;
                        }
                        continue;
                     }

                     countArray2 = new int[this.count4 + 1];
                  } else {
                     if (curveAccumulator2.countArray.length < this.count4 + 1) {
                        value2 = (value2 ^ 0x7408d0a21ae522d4L) - 0x6533793a2f38141L;
                        value3 = (value3 ^ -0xe96ea3403f8d96eL) + 0x3060c45f038303L;
                        continue;
                     }

                     countArray2 = curveAccumulator2.countArray;
                  }

                  int[] countArray3 = countArray2;
                  long value8 = 0L;

                  for (int index = this.count4 - 1; index >= 0; index--) {
                     long value9 = value * ((long)this.countArray[index + this.count2] & 4294967295L) + value8;
                     countArray3[index + 1] = (int)value9;
                     value8 = value9 >>> 32;
                  }

                  if (value8 != 0L) {
                     curveAccumulator2.count2 = 0;
                     curveAccumulator2.count4 = this.count4 + 1;
                     countArray3[0] = (int)value8;
                  } else {
                     curveAccumulator2.count2 = 1;
                     curveAccumulator2.count4 = this.count4;
                  }

                  curveAccumulator2.countArray = countArray3;
                  return;
               }

               return;
            }

            curveAccumulator2.updateState();
         }

         return;
      }
   }

   public SignumComparable getSignumComparable() {
      this.updateState3();
      int count5 = this.isActive2() ? 0 : 1;
      return this.resolveSignumComparableByIndex(count5);
   }

   public CurveAccumulator resolveCurveAccumulatorByIndex(int count5) {
      CurveAccumulator curveAccumulator2 = null;
      CurveAccumulator curveAccumulator3 = null;
      CurveAccumulator curveAccumulator4 = null;
      CurveAccumulator curveAccumulator5 = null;
      CurveAccumulator curveAccumulator6 = null;
      CurveAccumulator curveAccumulator7 = null;
      CurveAccumulator curveAccumulator8 = null;
      CurveAccumulator curveAccumulator9 = null;
      CurveAccumulator curveAccumulator10 = null;
      long value = 0x5c4c91ec35472c68L;
      long value2 = -0x49c9a281cebabcf8L;

      while (true) {
         int count6 = (int)(value % 41L);
         if (count6 != 13) {
            guardBlock: {
               searchBlock: {
                  if (count6 != 5) {
                     if (count6 == 25) {
                        value = (value ^ 0x499ed723b2e7ea2bL) - 0x3db40e00c823ef67L;
                        value2 = (value2 ^ -0x63936cf38cdb439eL) + 0x18041db59ff45764L;
                        break searchBlock;
                     }

                     if (count6 == 36) {
                        return curveAccumulator9;
                     }

                     if (count6 == 10) {
                        curveAccumulator5 = curveAccumulator10;
                        value = (value ^ 0x4cbb7b2d72ce2760L) - 0x73b403799a10abaL;
                        value2 = (value2 ^ 0x5c47d7d7b6545dcL) - 0x5f17c96e450291f2L;
                        continue;
                     }

                     if (count6 == 26) {
                        curveAccumulator7 = curveAccumulator2;
                        value = (value ^ 0x30ab75279ceb8603L) - 0x7830dc07a1453c4cL;
                        value2 = (value2 ^ 0xe52f2ceb06e456dL) + 0x598e2163e4c27547L;
                        continue;
                     }

                     if (count6 == 28) {
                        curveAccumulator8.applyCurveAccumulator(curveAccumulator5);
                        value = (value ^ -0xc72b55728cf3049L) + 0x35c452ef6a57305aL;
                        value2 = (value2 ^ -0x5393d0fe841e2ea7L) - 0x5c0e36f1525d7399L;
                        continue;
                     }

                     if (count6 == 32) {
                        curveAccumulator5 = curveAccumulator10;
                        value = (value ^ -0x533a27684e6cfa5bL) - 0x72671c9981a855f6L;
                        value2 = (value2 ^ -0x7dc7be3ad1529924L) - 0x24a4aede56c9c341L;
                        continue;
                     }

                     if (count6 == 9) {
                        int count7 = curveAccumulator5.countArray[curveAccumulator5.count2];
                        curveAccumulator8.applyCurveAccumulator(count7, curveAccumulator10);
                        value = (value ^ -0x5cf41ef4c063f22dL) + 0x721349bb3760faa7L;
                        value2 = (value2 ^ 0x54e219348a18dbfaL) + 0x47da1be5fd4a8b4aL;
                        continue;
                     }

                     if (count6 == 31) {
                        throw new ArithmeticException("BigInteger not invertible.");
                     }

                     if (count6 == 29) {
                        curveAccumulator9.applyCurveAccumulator(curveAccumulator5);
                        value = (value ^ 0x7df9a7a7279fa981L) - 0x6322e12324e034faL;
                        value2 = (value2 ^ 0x26d39353099f94f6L) + 0x4ef53cd6d7ba3f64L;
                        continue;
                     }

                     if (count6 == 16) {
                        curveAccumulator8 = new CurveAccumulator(curveAccumulator5);
                        value = (value ^ -0xc2e8ba1be9981b3L) + 0x4f3926ee3457cbd3L;
                        value2 = (value2 ^ -0x4ea5bd5af16cc7b7L) - 0x59e318eb81c56e2L;
                        continue;
                     }

                     if (count6 == 14) {
                        curveAccumulator2.applyIndex3(count5);
                        value = (value ^ 0x4722b246d624a449L) - 0x1872174767f69f44L;
                        value2 = (value2 ^ 0x25bc7b400fb4c61aL) + 0x33ff7c2b590b1bdcL;
                        continue;
                     }

                     if (count6 == 38) {
                        throw new ArithmeticException("BigInteger not invertible.");
                     }

                     if (count6 == 7) {
                        value = (value ^ -0x1c4d9b3eb95ce07dL) - 0x7c2c1fefc1ed37d1L;
                        value2 = (value2 ^ 0x2cc31c191238c882L) + 0x21491cb45becc555L;
                        break guardBlock;
                     }

                     if (count6 == 8) {
                        curveAccumulator10 = curveAccumulator7;
                        value = (value ^ -0x3247aad5bdead843L) + 0x2b27b206a36aaa0fL;
                        value2 = (value2 ^ 0x288cd14c06136d91L) - 0x38455d7a851d5015L;
                        continue;
                     }

                     if (count6 == 19) {
                        curveAccumulator4 = new CurveAccumulator(this);
                        value = (value ^ -0x6e0158fb8724c436L) + 0x411be5d7929dd725L;
                        value2 = (value2 ^ -0xf1ad1517d19786bL) + 0xee5b376528a9d91L;
                        continue;
                     }

                     if (count6 == 35) {
                        curveAccumulator10 = new CurveAccumulator();
                        value = (value ^ -0xb5539669fd1c2a2L) + 0x3b7b0c1e09ba597dL;
                        value2 = (value2 ^ 0x59b94566f071f979L) + 0x773e721344f79fe5L;
                        continue;
                     }

                     if (count6 == 21) {
                        if (curveAccumulator6.count4 != 0) {
                           value = (value ^ 0x94678ddab4ba054L) + 0xc6e3a00499b45dcL;
                           value2 = (value2 ^ 0xbbf16a4c116733fL) + 0xb7bcde82185d855L;
                        } else {
                           value = (value ^ -0x2b516d0a7b728593L) + 0x39670d259cc469a3L;
                           value2 = (value2 ^ 0x740298eb15173ebfL) - 0x5b3682460179f176L;
                        }
                        continue;
                     }

                     if (count6 == 37) {
                        if (curveAccumulator5.count4 != 1) {
                           long value3 = (value ^ -0x8466c24510a4b90L) + 0x7510519aa9053804L;
                           long value4 = (value2 ^ 0x398139786df0ed8cL) - 0x7bfc6243dff32f9L;
                           curveAccumulator5.applyCurveAccumulator(curveAccumulator9, curveAccumulator10);
                           value = (value3 ^ -0x1fd591e0f64c531eL) - 0x7dc416298edde672L;
                           value2 = (value4 ^ -0x7446fcdeac642137L) + 0x7c8339ac9d4bdb0cL;
                        } else {
                           value = (value ^ -0x6cdc3fdc2b88defcL) - 0x3867e85658eae2c1L;
                           value2 = (value2 ^ 0x31e83ada8192909aL) - 0x55368a59ea56ac1L;
                        }
                        continue;
                     }

                     if (count6 == 24) {
                        curveAccumulator7 = curveAccumulator6;
                        value = (value ^ -0x2c980a651e90ca78L) - 0x25dbc9af83e2e423L;
                        value2 = (value2 ^ -0xf7c8e8e3eb7575bL) - 0x30853b4882b90174L;
                        continue;
                     }

                     if (count6 == 20) {
                        curveAccumulator2 = curveAccumulator6;
                        value = (value ^ 0x48cbe7ec4f71e72dL) + 0x5858ef0070e7c47dL;
                        value2 = (value2 ^ -0x1ae8ec339f2b098dL) - 0x32597ff8529bc438L;
                        continue;
                     }

                     if (count6 == 27) {
                        curveAccumulator6 = curveAccumulator7;
                        value = (value ^ -0x31923a6b9aeb2a7eL) + 0x62e0a381151f33b5L;
                        value2 = (value2 ^ 0x6d7b45e14b674c61L) - 0x4efb086959787feL;
                        continue;
                     }

                     if (count6 == 18) {
                        curveAccumulator6 = curveAccumulator2.resolveCurveAccumulator4(curveAccumulator4, curveAccumulator5);
                        value = (value ^ 0x701235c5a8e7858aL) - 0x2826fa9debc43362L;
                        value2 = (value2 ^ -0x60ba559af89c7a03L) + 0x5241e03b78bc9ebaL;
                        continue;
                     }

                     if (count6 == 33) {
                        curveAccumulator5 = new CurveAccumulator();
                        value = (value ^ -0x72f2bc0f1e3894e0L) - 0x537e25fcecc49354L;
                        value2 = (value2 ^ 0x4d12c16ff309869bL) - 0x55333a7bab85debfL;
                        continue;
                     }

                     if (count6 == 3) {
                        curveAccumulator6 = curveAccumulator2.resolveCurveAccumulator4(curveAccumulator4, curveAccumulator5);
                        value = (value ^ -0x792f3b9fcd63b2d7L) + 0x6ff7e486e5ed5586L;
                        value2 = (value2 ^ -0x26eaad706ea500d8L) + 0x1ec1ae23d9c2d114L;
                        continue;
                     }

                     if (count6 == 6) {
                        break searchBlock;
                     }

                     if (count6 == 22) {
                        break guardBlock;
                     }

                     if (count6 != 40) {
                        if (count6 == 23) {
                           curveAccumulator2 = new CurveAccumulator(1);
                           value = (value ^ -0x49d31b6f2827d988L) + 0x52ba59a5ba5935f5L;
                           value2 = (value2 ^ 0x37c702ba85a0f004L) - 0x601a729df17ca363L;
                           continue;
                        }

                        if (count6 == 4) {
                           if (!curveAccumulator4.isActive()) {
                              value = (value ^ -0x6e29395b5834d068L) - 0x216db7b9d36ce112L;
                              value2 = (value2 ^ 0x1d1265550e23d87L) + 0x3bea519cc488a1aL;
                              continue;
                           }

                           return curveAccumulator9;
                        }

                        if (count6 == 11) {
                           curveAccumulator4 = curveAccumulator7;
                           value = (value ^ -0x79bdc91ecace4b84L) + 0x1554851455835e65L;
                           value2 = (value2 ^ -0x558f6e8db258ac59L) + 0x2b1494b39748e721L;
                           continue;
                        }

                        if (count6 == 30) {
                           curveAccumulator2 = curveAccumulator6;
                           value = (value ^ -0x2cac123d31691d60L) + 0x6445352d6a3531f3L;
                           value2 = (value2 ^ -0x64bc314b346677d0L) - 0x260fd184531d5c67L;
                           continue;
                        }

                        if (count6 == 34) {
                           curveAccumulator3 = new CurveAccumulator(curveAccumulator2);
                           value = (value ^ 0x567d2d73a6d4f434L) + 0xc2d1999b734dd3eL;
                           value2 = (value2 ^ -0x2447f99113679d37L) + 0x46acfedc21cc9ac7L;
                           continue;
                        }

                        if (count6 == 39) {
                           if (curveAccumulator6.count4 != 0) {
                              value = (value ^ -0x6e88583aea07e8c2L) + 0x593495d4c1f54517L;
                              value2 = (value2 ^ -0x2e0caab75e85bf06L) - 0x1987d7f04cb11dc1L;
                           } else {
                              value = (value ^ 0x4d7d614155826a94L) - 0x16bf512a8d482240L;
                              value2 = (value2 ^ 0x5537706dfd00eb48L) + 0x7528e5ab8b36c0c1L;
                           }
                           continue;
                        }

                        if (count6 == 2) {
                           curveAccumulator10 = curveAccumulator7;
                           value = (value ^ -0x5abef363aec2460eL) + 0x2be773b2a002a830L;
                           value2 = (value2 ^ 0x134617ab89c1c4d8L) - 0x75841c5c86531d29L;
                           continue;
                        }

                        if (count6 == 0) {
                           curveAccumulator9 = new CurveAccumulator(1);
                           value = (value ^ -0x41c6bdef2cee06cfL) + 0x76bf9414545ba54dL;
                           value2 = (value2 ^ -0x49b255b75dd5d2d8L) - 0x6570f4ec8f598041L;
                           continue;
                        }

                        if (count6 != 17) {
                           if (count6 != 15) {
                              if (count6 != 12) {
                                 curveAccumulator7 = curveAccumulator2;
                                 value = (value ^ 0xd826349b85945acL) + 0x4b5d15112a5277b5L;
                                 value2 = (value2 ^ -0x383cb2f9d59e6ffcL) - 0x105a7c609fdab1d4L;
                              } else if (curveAccumulator5.count4 != 1) {
                                 long value5 = (value ^ 0x123410aa1a3da8d0L) - 0x2cacf004a2ba70L;
                                 long value6 = (value2 ^ 0x1685cfb6461e5133L) + 0x60633a3cc73cfae0L;
                                 curveAccumulator5.applyCurveAccumulator(curveAccumulator8, curveAccumulator10);
                                 value = (value5 ^ -0x5bd6a9c60c14f1ceL) + 0x56b872e4f6dff4a2L;
                                 value2 = (value6 ^ -0x595db27a87bf139dL) + 0x12b6d42c97182763L;
                              } else {
                                 value = (value ^ 0x55aff77b9071e36aL) - 0x59affbe5f0cb64d0L;
                                 value2 = (value2 ^ -0x216592fd09d575a5L) + 0x7bdba816f3f78904L;
                              }
                           } else {
                              int count8 = curveAccumulator5.countArray[curveAccumulator5.count2];
                              curveAccumulator9.applyCurveAccumulator(count8, curveAccumulator10);
                              value = (value ^ -0x6856598ef6d54263L) + 0x5c3b43ba848bed6cL;
                              value2 = (value2 ^ 0x5225d35c4a0cd160L) - 0x485faf347cf045eeL;
                           }
                           continue;
                        }

                        return curveAccumulator3;
                     }

                     value = (value ^ 0x47f8b0614972d141L) - 0x3994e437dc6e66faL;
                     value2 = (value2 ^ -0x6de9d0649193889bL) - 0x4963da828c50869cL;
                  }

                  if (!curveAccumulator2.isActive()) {
                     value = (value ^ 0x72d047d7fd5d7552L) - 0x49fea4ee2f13da21L;
                     value2 = (value2 ^ -0x690c6f98e985e75aL) + 0x2c058bd561122e6bL;
                     continue;
                  }

                  curveAccumulator3.computeCount2(curveAccumulator8);
                  return curveAccumulator3;
               }

               curveAccumulator7 = curveAccumulator5;
               value = (value ^ 0x1b6d0d129d972416L) + 0x2eea598586f205e9L;
               value2 = (value2 ^ -0x57b1c79202f1764eL) + 0x577de33d51d0fcaeL;
               continue;
            }

            curveAccumulator7 = curveAccumulator5;
            value = (value ^ -0x33c8a8ddaaf0103bL) + 0x283cedde9a410862L;
            value2 = (value2 ^ 0x405dc1458e51789dL) + 0x2822ce290708622bL;
         } else {
            curveAccumulator6 = curveAccumulator4.resolveCurveAccumulator4(curveAccumulator2, curveAccumulator5);
            value = (value ^ -0xa2b6388c0c078bL) + 0x492e9b67e8bf8af0L;
            value2 = (value2 ^ 0x5f22de14438fcb6eL) - 0x5ccecc304905b487L;
         }
      }
   }

   private CurveAccumulator resolveCurveAccumulatorByCurveAccumulator(CurveAccumulator curveAccumulator2) {
      CurveAccumulator curveAccumulator3 = null;
      CurveAccumulator curveAccumulator4 = null;
      CurveAccumulator curveAccumulator5 = null;
      CurveCollector curveCollector = null;
      CurveCollector curveCollector2 = null;
      CurveAccumulator curveAccumulator6 = null;
      CurveCollector curveCollector3 = null;
      int count5 = 0;
      int count6 = 0;
      CurveAccumulator curveAccumulator7 = null;
      long value = 0x796a037997f88ca6L;
      long value2 = -0x460880f1b134dcdcL;

      while (true) {
         int count7 = (int)(value % 38L);
         if (count7 == 5) {
            curveCollector = curveCollector3;
            value = (value ^ -0x2a5d54cda25bb347L) + 0x2d956f23e0a0bdb6L;
            value2 = (value2 ^ -0x55fa1c4e6c4afa6L) + 0x777994925d7ad993L;
         } else if (count7 != 29) {
            if (count7 != 17) {
               if (count7 != 36) {
                  if (count7 != 22) {
                     if (count7 == 35) {
                        throw new ArithmeticException("BigInteger not invertible.");
                     }

                     if (count7 != 19) {
                        if (count7 != 34) {
                           if (count7 != 12) {
                              if (count7 != 6) {
                                 if (count7 == 18) {
                                    return resolveCurveAccumulator(curveCollector, curveAccumulator3, count5);
                                 }

                                 if (count7 != 21) {
                                    if (count7 != 14) {
                                       if (count7 != 10) {
                                          if (count7 != 33) {
                                             if (count7 != 31) {
                                                if (count7 != 3) {
                                                   if (count7 == 28) {
                                                      curveCollector2 = curveCollector;
                                                      value = (value ^ 0x87fb5dc9675dadcL) - 0xec9b80ca71d1781L;
                                                      value2 = (value2 ^ -0x3e06ba7928db6c4bL) - 0x7a6c543e28ed28deL;
                                                      continue;
                                                   }

                                                   if (count7 == 1) {
                                                      curveAccumulator6 = null;
                                                      value = (value ^ -0x5e9b4b491ae43ce1L) - 0x659ef1b2401f915fL;
                                                      value2 = (value2 ^ 0x707d06819a4de06cL) - 0x7cc12beba9080a27L;
                                                      continue;
                                                   }

                                                   if (count7 != 26) {
                                                      if (count7 != 16) {
                                                         if (count7 != 24) {
                                                            if (count7 != 4) {
                                                               if (count7 != 15) {
                                                                  if (count7 != 0) {
                                                                     if (count7 != 30) {
                                                                        if (count7 == 2) {
                                                                           if (!curveAccumulator4.isActive2()) {
                                                                              value = (value ^ 0x58d532574c187565L) + 0x52fa3b1b65c1beacL;
                                                                              value2 = (value2 ^ -0x10e316ea8c1c5021L) - 0xd042aaf73199929L;
                                                                           } else {
                                                                              value = (value ^ -0x1e5dd4019744fd51L) - 0x381229ed12596faaL;
                                                                              value2 = (value2 ^ -0x17c524a258621953L) - 0x202ba71c9eab6123L;
                                                                           }
                                                                           continue;
                                                                        }

                                                                        if (count7 == 32) {
                                                                           CurveAccumulator curveAccumulator8 = new CurveAccumulator();
                                                                           curveAccumulator7 = curveCollector.resolveCurveAccumulator4(curveAccumulator3, curveAccumulator8);
                                                                           value = (value ^ 0x67d60af2ebcc8490L) + 0x34f27df891e5ed3L;
                                                                           value2 = (value2 ^ -0x6a4e95e02fdceefcL) - 0x5b2a288300689a0L;
                                                                           continue;
                                                                        }

                                                                        if (count7 == 11) {
                                                                           curveAccumulator4.applyIndex(count6);
                                                                           value = (value ^ 0x1dad252c40f1f0a0L) + 0x1cffb4b55096fb1aL;
                                                                           value2 = (value2 ^ -0x2fe15020ef3840ebL) + 0x71b21aa8a2e7f8e3L;
                                                                           continue;
                                                                        }

                                                                        if (count7 == 8) {
                                                                           curveCollector.applyCurveAccumulator2(curveAccumulator3);
                                                                           value = (value ^ 0x16cabd8fa057b87dL) + 0x192fbf74df549d1L;
                                                                           value2 = (value2 ^ -0x62eaa387c187b59eL) - 0x53cfe2f276ea7a0aL;
                                                                           continue;
                                                                        }

                                                                        if (count7 == 9) {
                                                                           count5 = 0;
                                                                           value = (value ^ 0x26c7c6a5f3c9161dL) + 0x460c9838c6236359L;
                                                                           value2 = (value2 ^ 0x4da381b9ae6c0252L) + 0x5373dca1f5bb8370L;
                                                                           continue;
                                                                        }

                                                                        if (count7 != 13) {
                                                                           if (count7 != 20) {
                                                                              if (count7 != 25) {
                                                                                 if (count7 != 37) {
                                                                                    if (count7 != 23) {
                                                                                       if (count7 != 7) {
                                                                                          if (curveCollector.count5 >= 0) {
                                                                                             value = (value ^ 0x9cbfc48b7443efeL) - 0xae222d3bd8a2489L;
                                                                                             value2 = (value2 ^ 0x4c06281e0cf3e75fL) + 0x3e8d8a6838f9d6c7L;
                                                                                          } else {
                                                                                             value = (value ^ 0x6017b7399dd23c1dL) + 0x4d84702c7dbf06f0L;
                                                                                             value2 = (value2 ^ -0x788b7b56ac47f58aL) + 0x5ffee46bef22ee08L;
                                                                                          }
                                                                                       } else {
                                                                                          curveCollector3 = null;
                                                                                          value = (value ^ -0x688cffd42a0bd30L) - 0x4e3cf85fef2e7566L;
                                                                                          value2 = (value2 ^ -0x65054031adf96703L) - 0x2a7350fedb617b6eL;
                                                                                       }
                                                                                    } else {
                                                                                       curveCollector.applyCurveAccumulatorByCurveAccumulator(curveAccumulator7);
                                                                                       value = (value ^ -0x6e721bb694c17827L) - 0x51e7785afb9e9e57L;
                                                                                       value2 = (value2 ^ -0x7c1a9d73f0c262eaL) - 0x24170555b4b801d8L;
                                                                                    }
                                                                                 } else {
                                                                                    count6 = curveAccumulator4.getCount();
                                                                                    value = (value ^ -0x583617744772eb9dL) - 0x79e714493e4d0b09L;
                                                                                    value2 = (value2 ^ 0x3373e4b21271880fL) + 0x69960990c9e8c411L;
                                                                                 }
                                                                              } else if (curveAccumulator4.computeCount(curveAccumulator5) >= 0) {
                                                                                 value = (value ^ 0x3fd5afc836a737daL) - 0x3cde36e885462733L;
                                                                                 value2 = (value2 ^ -0x6a1cba02836ab8fbL) + 0x6389fbb7c0aa59daL;
                                                                              } else {
                                                                                 value = (value ^ -0x12ee120bba44cfcL) - 0x629acc2904e6ae65L;
                                                                                 value2 = (value2 ^ -0xa7952695bb2d22fL) - 0x19091f6d29d789f4L;
                                                                              }
                                                                           } else {
                                                                              curveAccumulator4 = curveAccumulator5;
                                                                              value = (value ^ 0x7ffd5b1e46b125cbL) - 0x52c492d40a8afa23L;
                                                                              value2 = (value2 ^ -0x7700bc3f768a53deL) + 0x42791583e8f253e2L;
                                                                           }
                                                                           continue;
                                                                        }
                                                                     } else {
                                                                        value = (value ^ -0x611e0dd08dade6dfL) - 0x7d43e4576e9f6c92L;
                                                                        value2 = (value2 ^ 0x5522b5804759c88bL) - 0x33702d096e14f005L;
                                                                     }

                                                                     if (curveAccumulator4.isActive()) {
                                                                        long value3 = (value ^ -0x4eff073cebcb57aL) + 0x5f7206c851b01f2bL;
                                                                        long value4 = (value2 ^ 0x14d0b596033870b3L) - 0x637dbd0cd96a5825L;
                                                                        if (curveCollector.computeCount(curveAccumulator3) < 0) {
                                                                           value = (value3 ^ 0x2897e9218d9ca7d0L) + 0x59d72efb41593e99L;
                                                                           value2 = (value4 ^ -0x2342a53f50c01214L) - 0x2ad239a39839e388L;
                                                                        } else {
                                                                           value = (value3 ^ 0x7b287db3953973f4L) - 0x1b86599ea1c14d1eL;
                                                                           value2 = (value4 ^ -0x2b7e1d9734269402L) - 0x1e9774abe5bd2e30L;
                                                                        }
                                                                     } else {
                                                                        value = (value ^ -0x4e79da82b5773b47L) - 0x5936cff4e3e05eb8L;
                                                                        value2 = (value2 ^ -0x466caa904b642f6eL) + 0x53d663c3a682fe58L;
                                                                     }
                                                                  } else {
                                                                     count5 += count6;
                                                                     value = (value ^ 0xa885a540737a19L) - 0x42a210a4fcc2e091L;
                                                                     value2 = (value2 ^ 0x53da772261d66a77L) + 0x2c5b3a647eb76cabL;
                                                                  }
                                                               } else {
                                                                  curveAccumulator4.computeCount2(curveAccumulator5);
                                                                  value = (value ^ -0x214e6211b52fb90bL) + 0x51661390e1e0a043L;
                                                                  value2 = (value2 ^ -0xdf96094e379a493L) - 0x2bc0a1e8998d9124L;
                                                               }
                                                            } else {
                                                               curveCollector = new CurveCollector(1);
                                                               value = (value ^ 0x1ec3758d287a149cL) + 0x72ac4d38c9490556L;
                                                               value2 = (value2 ^ -0x1ff728bfe4fefa3L) - 0xd1446a007b481baL;
                                                            }
                                                         } else {
                                                            curveCollector3 = curveCollector2;
                                                            value = (value ^ -0x2efe3de3662a2720L) - 0x4738cea0acbfbb28L;
                                                            value2 = (value2 ^ 0x5fc75c07d3564cd2L) - 0x218982986746ff4eL;
                                                         }
                                                      } else {
                                                         curveAccumulator4 = new CurveAccumulator(this);
                                                         value = (value ^ -0x710e51020f7f3866L) - 0x5246a4cb8494e8c4L;
                                                         value2 = (value2 ^ 0x1a2a2db61f78ab1bL) - 0x3ecb04404b43ef14L;
                                                      }
                                                      continue;
                                                   }
                                                } else {
                                                   value = (value ^ -0x613f69be77adb9d2L) - 0x76ef97f4be65d7e7L;
                                                   value2 = (value2 ^ -0x7c205fee43a339cL) - 0xfa6a51cc199b72aL;
                                                }

                                                count6 = curveAccumulator4.getCount();
                                                value = (value ^ 0x79df7b96054b20daL) + 0x886923963cb0399L;
                                                value2 = (value2 ^ 0x23c593d2a81376bdL) - 0x4ca5b8be5747700dL;
                                             } else {
                                                curveCollector2.applyIndex3(count6);
                                                value = (value ^ 0x4015001da048b16L) + 0x721e29dc40b52099L;
                                                value2 = (value2 ^ 0x68eaada55e000130L) - 0x260ac7caac3475b3L;
                                             }
                                          } else if (!curveAccumulator4.isActive4()) {
                                             value = (value ^ 0x46e45929f31835e6L) + 0x12314babf3e7a32eL;
                                             value2 = (value2 ^ 0x5d7dc22a066ec97L) - 0x64fdffd99852caceL;
                                          } else {
                                             value = (value ^ 0xc90281297d29821L) - 0x44cf86e2d3f6fa43L;
                                             value2 = (value2 ^ -0x3f07ce83b6528c5L) + 0x3966c7ffd9587f43L;
                                          }
                                       } else {
                                          curveAccumulator3 = new CurveAccumulator(curveAccumulator2);
                                          value = (value ^ 0x530c30827d6b4ec0L) + 0x341940885834be04L;
                                          value2 = (value2 ^ 0x5e84a13c803df5f6L) + 0x766fd946bad006fdL;
                                       }
                                    } else {
                                       count5 = count6;
                                       value = (value ^ 0x7480360a6c85416fL) - 0x4b56e7dfe3054e2cL;
                                       value2 = (value2 ^ 0xb059cc3e14fbaceL) + 0x7b8669a8d33684cfL;
                                    }
                                 } else {
                                    curveAccumulator4.applyIndex(count6);
                                    value = (value ^ -0x4fe28f76488fe6dL) + 0x1c56e99a366214e3L;
                                    value2 = (value2 ^ -0x3a2b4e94960d4d8eL) - 0x244df0b893d2e2b6L;
                                 }
                              } else {
                                 curveAccumulator5 = curveAccumulator6;
                                 value = (value ^ -0x10e5043df6aea8fdL) + 0x7d73577a24538e4bL;
                                 value2 = (value2 ^ -0x1e9ab415c30df33bL) + 0x713224372a85a145L;
                              }
                           } else if (((curveAccumulator4.countArray[curveAccumulator4.count2 + curveAccumulator4.count4 - 1] ^ curveAccumulator5.countArray[curveAccumulator5.count2 + curveAccumulator5.count4 - 1]) & 3) != 0) {
                              long value5 = (value ^ -0x17ba56b1b1282dc4L) - 0x7d942f26e2b854cfL;
                              long value6 = (value2 ^ 0x5186e237f97d3d00L) + 0x5ddb1cd4aa2e94bcL;
                              curveAccumulator4.applyCurveAccumulator(curveAccumulator5);
                              value = (value5 ^ -0x142b60c45ff18326L) - 0x77b5d1e08ea6a6ecL;
                              value2 = (value6 ^ -0x31eaba0a5acb7d18L) + 0x913478dfc23352aL;
                           } else {
                              value = (value ^ -0x7d809b7a315a6202L) - 0x53bc138949cbe2f5L;
                              value2 = (value2 ^ 0x67185f1d3761c058L) + 0x533057eb4fdf534L;
                           }
                        } else {
                           curveAccumulator5 = new CurveAccumulator(curveAccumulator3);
                           value = (value ^ -0x7a3b1825cda51e39L) + 0x1b1e167f0b0f6009L;
                           value2 = (value2 ^ -0x4e7ef2db013b176cL) - 0xe4cc8bb46f74ef2L;
                        }
                     } else {
                        curveCollector.applyCurveCollectorByCurveCollector(curveCollector2);
                        value = (value ^ 0x25104d459b435228L) + 0x30c27e5962ae1c09L;
                        value2 = (value2 ^ -0x9453a730113a3d1L) + 0x4bd888dd7a9e3243L;
                     }
                  } else {
                     curveCollector2 = new CurveCollector();
                     value = (value ^ -0x7eb2b403614546bcL) - 0x7c0287e92e776eb1L;
                     value2 = (value2 ^ 0x206cb9be39bbf648L) - 0x44a389a7e1ba31f6L;
                  }
               } else {
                  curveAccumulator6 = curveAccumulator4;
                  value = (value ^ 0x115e30680c4f7a83L) - 0x3b0bd4b16e2d35c9L;
                  value2 = (value2 ^ -0x577f6edc919d4bc3L) - 0x45c671d06de91ba7L;
               }
            } else {
               curveCollector.applyCurveCollector(curveCollector2);
               value = (value ^ 0x2957fa36707b808eL) + 0x5fa163f19e1de18cL;
               value2 = (value2 ^ 0x65cdfcc63a69d329L) - 0x3a481fcbc06a1cd8L;
            }
         } else {
            curveCollector2.applyIndex3(count6);
            value = (value ^ 0x782730a364fdd0dL) - 0x36420c4055b807ecL;
            value2 = (value2 ^ -0x1dd8e4778b3dffbeL) - 0x68e83ab66abf3bd4L;
         }
      }
   }

   public void applyIndex3(int count5) {
      int count6 = 0;
      int count7 = 0;
      int count8 = 0;
      int count9 = 0;
      int[] countArray2 = null;
      int count10 = 0;
      int count11 = 0;
      long value = 0x509b570db84d6d02L;
      long value2 = 0x140f65e15d310d3L;

      while (true) {
         innerLoop:
         while (true) {
            while (true) {
               int count12 = (int)(value % 31L);
               if (count12 != 11) {
                  if (count12 == 24) {
                     if (this.count4 != 0) {
                        long value3 = (value ^ 0x76ef9d8860e91378L) - 0x15b293df24768701L;
                        long value4 = (value2 ^ 0x32a2f91cd1ce872fL) - 0x327bfa104fd3018L;
                        count6 = count5 >>> 5;
                        value = (value3 ^ -0x48edd4e030d719dcL) - 0x65327c2ffa58e8f7L;
                        value2 = (value4 ^ 0x33b9348991952a2fL) + 0x572f9572ab647fcdL;
                        continue;
                     }

                     return;
                  }

                  if (count12 == 4) {
                     return;
                  }

                  if (count12 == 8) {
                     this.countArray[this.count2 + this.count4 + count11] = 0;
                     value = (value ^ -0x70e6250fbb5a833fL) - 0x4bc79f227cae8e99L;
                     value2 = (value2 ^ -0x253a461a22e5c8ffL) - 0x51fd03d02611d768L;
                     continue;
                  }

                  if (count12 == 29) {
                     if (count7 <= 32 - count8) {
                        value = (value ^ -0x3d5a17aeda96618dL) + 0x6cedc4f1b6b08037L;
                        value2 = (value2 ^ 0x4b5b344dbc6872adL) + 0x51dc43a6882f5bc4L;
                        continue;
                     }

                     value = (value ^ -0x5fbdf1d7a9ca94a6L) + 0xa0baaea4583cd1eL;
                     value2 = (value2 ^ 0x4d26bedbe3e4e3aeL) + 0x4b87c93cbbbbecc7L;
                     break;
                  }

                  if (count12 != 17) {
                     if (count12 == 0) {
                        countArray2 = new int[count9];
                        value = (value ^ -0x3bd4f5103870c6d0L) - 0x4ee534f3a9bb8c50L;
                        value2 = (value2 ^ -0x3b5cfd8ade0d8d7fL) - 0x49eae5ed71635f0dL;
                        continue;
                     }

                     searchBlock:
                     if (count12 != 9) {
                        if (count12 == 1) {
                           this.applyIndex5(count7);
                           return;
                        }

                        if (count12 == 13) {
                           count7 = count5 & 31;
                           value = (value ^ -0x6e1fc640893e19ccL) + 0x50491a8972de43bfL;
                           value2 = (value2 ^ -0x32718937c1b2c163L) + 0x75f8b4f02a59afe5L;
                           continue;
                        }

                        if (count12 != 12) {
                           if (count12 == 10) {
                              value = (value ^ -0x4c8b14a295e99077L) + 0x3ae0f3640a9f355dL;
                              value2 = (value2 ^ -0x1b8d2ce7150677c2L) - 0x1d144cdc16063e7eL;
                              break innerLoop;
                           }

                           if (count12 == 25) {
                              if (count7 == 0) {
                                 return;
                              }

                              long value5 = (value ^ 0x25b26b2d2d96163bL) - 0x25c652ff1bc149cfL;
                              long value6 = (value2 ^ 0x7512bd9f8717a21aL) + 0x336c0329251f564fL;
                              if (count7 <= 32 - count8) {
                                 value = (value5 ^ -0x6ef30fc992678bb5L) + 0x5fd7861f2aef6d7eL;
                                 value2 = (value6 ^ -0x451e9caa2b4a3996L) - 0x6768754a1262a031L;
                                 continue;
                              }

                              int count13 = 32 - count7;
                              this.applyIndex7(count13);
                              return;
                           }

                           if (count12 == 22) {
                              return;
                           }

                           if (count12 == 3) {
                              this.countArray[count11] = 0;
                              value = (value ^ 0x2e3cbc21f8b26490L) + 0x120dcc2d309fcdebL;
                              value2 = (value2 ^ -0x6a338ec47b4a08b8L) - 0x59db07b2211b4279L;
                              continue;
                           }

                           if (count12 == 5) {
                              count8 = SignumComparable.numberOfLeadingZeros(this.countArray[this.count2]);
                              value = (value ^ 0x2c076791a72e288bL) + 0x139c10359fdee2f7L;
                              value2 = (value2 ^ -0x146f722f12f453e9L) - 0x64d069d2b1321c5aL;
                              continue;
                           }

                           if (count12 != 19) {
                              if (count12 == 23) {
                                 return;
                              }

                              if (count12 == 27) {
                                 this.countArray[count11] = this.countArray[this.count2 + count11];
                                 value = (value ^ -0x7de81737d48b8bdbL) + 0x51870f7279ad4998L;
                                 value2 = (value2 ^ -0x3455d18aae111f9cL) - 0x2e5f7184f4a873ebL;
                                 continue;
                              }

                              if (count12 == 20) {
                                 break innerLoop;
                              }

                              if (count12 != 30) {
                                 if (count12 != 16) {
                                    if (count12 == 14) {
                                       return;
                                    }

                                    if (count12 == 26) {
                                       return;
                                    }

                                    if (count12 == 2) {
                                       countArray2[count10] = this.countArray[this.count2 + count10];
                                       value = (value ^ -0x1b5fe4e913a15ebcL) - 0x60573023f770af16L;
                                       value2 = (value2 ^ 0x14e7f02a16d717aaL) - 0x24300421eadaf42cL;
                                       continue;
                                    }

                                    if (count12 != 21) {
                                       if (count12 == 6) {
                                          if (count5 > 32 - count8) {
                                             long value7 = (value ^ -0x4d3422c10ee49a5fL) - 0x5689692a86e03534L;
                                             long value8 = (value2 ^ 0x21861c90d6994336L) + 0x1a01620596602829L;
                                             count9 = this.count4 + count6 + 1;
                                             value = (value7 ^ -0x5a2eedc29c73778eL) - 0x401ebdc5d306148fL;
                                             value2 = (value8 ^ 0x7d583f1faec07403L) - 0x13b9d3ed0006023fL;
                                          } else {
                                             value = (value ^ -0x45c4578b81e70747L) - 0x39589c01ddb232c6L;
                                             value2 = (value2 ^ -0x70edaf953fdc077fL) + 0x19214217998ed703L;
                                          }
                                          continue;
                                       }

                                       if (count12 == 15) {
                                          this.applyIndex5(count7);
                                          return;
                                       }

                                       if (count12 == 28) {
                                          count10 = 0;
                                          value = (value ^ -0x3e32997d4f1caa68L) - 0x3e9ff18668059fefL;
                                          value2 = (value2 ^ 0x749fa9034a93b55L) + 0x1d0cf5e95248da3cL;
                                          continue;
                                       }

                                       if (count12 == 18) {
                                          count11 = 0;
                                          value = (value ^ 0x2a103da877961431L) + 0x47df4926269095b1L;
                                          value2 = (value2 ^ 0x73ec008f4f72e920L) - 0x5854c0495e47bdb0L;
                                          continue;
                                       }
                                    } else {
                                       count11++;
                                       value = (value ^ -0x3b009cbcd7442056L) - 0x556074e5acaa6af2L;
                                       value2 = (value2 ^ 0x2c719e3cab0f4ae2L) + 0x8e8fa2d11d7b6deL;
                                    }

                                    if (count11 >= count9) {
                                       long value9 = (value ^ -0x198bb4aec2e26c9dL) - 0x14e67528410caf49L;
                                       long value10 = (value2 ^ -0x69f81cccb3d7dc46L) + 0x3ca744a2d2fb1f96L;
                                       this.count2 = 0;
                                       value = (value9 ^ 0x5dca1ca9107cb1e5L) - 0x2b7fbeb368661ea8L;
                                       value2 = (value10 ^ 0x65993b64e5ba2526L) - 0xad5377bcddd252aL;
                                    } else {
                                       value = (value ^ -0x704ed1a2b6b4ef8L) - 0x277fa58781c3c3cbL;
                                       value2 = (value2 ^ -0x6654b9df36283cbeL) + 0x1f225a7aed1fb23fL;
                                    }
                                    continue;
                                 }

                                 count9--;
                                 break;
                              }
                              break searchBlock;
                           }
                        } else {
                           count11++;
                           value = (value ^ 0x6fbc4b5bfced4410L) + 0x3c1cf5a657640eb3L;
                           value2 = (value2 ^ 0x7ec6a62529566ba7L) - 0x21507225efa82f4aL;
                        }

                        if (count11 < count9 - this.count4) {
                           value = (value ^ -0x269480042ba19927L) - 0x6f597d9f18034602L;
                           value2 = (value2 ^ -0x156fc09767c83edeL) + 0xce4628dca11da32L;
                           continue;
                        }

                        long value11 = (value ^ 0xdcfdf804f8f0d67L) - 0x19396fc373e573eL;
                        long value12 = (value2 ^ 0x6638e46a81e8f927L) - 0x684c3e96766c03aL;
                        value = (value11 ^ -0x71ee01b1ba43f84dL) + 0x40d08e85a1da6680L;
                        value2 = (value12 ^ 0x4441e5fc472e2ab5L) - 0x731f3de0394bc4c5L;
                        break innerLoop;
                     } else {
                        count10++;
                        value = (value ^ -0x77721d539c33d613L) + 0x6310bddd6b99246eL;
                        value2 = (value2 ^ -0xe79cce6b6835b2fL) + 0x2603ebee9c08ccadL;
                     }

                     if (count10 >= this.count4) {
                        long value13 = (value ^ -0x2724f9234b0e665aL) - 0x42de1db5c16ea3fdL;
                        long value14 = (value2 ^ -0x772335e9fa7fa3aeL) + 0x3129bcf8a0a3daf6L;
                        this.applyIndexArray(countArray2, count9);
                        value = (value13 ^ -0x1ef0261073133655L) - 0x2222be240b407f41L;
                        value2 = (value14 ^ -0x3462a4fbab970740L) - 0x46517926d57b69daL;
                     } else {
                        value = (value ^ 0x4c7a1a393ebab65bL) + 0x479f380c4127f92L;
                        value2 = (value2 ^ -0x3cc5953da53ad0adL) + 0x1fa771843e028cc9L;
                     }
                     continue;
                  }
               } else {
                  count11++;
                  value = (value ^ 0x7cce659be11e5c64L) - 0x6729b2130f7eebdcL;
                  value2 = (value2 ^ -0x7c46dc4a8fe5e44bL) - 0x7789bbc1d6d07011L;
               }

               if (count11 >= this.count4) {
                  long value15 = (value ^ -0x59dcd70d7c7d9987L) + 0x7538aebdae171e1fL;
                  long value16 = (value2 ^ 0x528d6d391e89ea75L) - 0x5659f6d1b69f9fd1L;
                  count11 = this.count4;
                  value = (value15 ^ 0x58f2c341e7682031L) + 0x1cd606a6b2d5dfdaL;
                  value2 = (value16 ^ 0x783b76866e95ba83L) + 0x598b6cfbd5c4d2ccL;
               } else {
                  value = (value ^ -0x30e5b88a2b29608fL) + 0x78afe5149fc99f74L;
                  value2 = (value2 ^ 0x4121230ed194efL) + 0x615744f1ff4d9e40L;
               }
            }

            if (this.countArray.length >= count9) {
               long value17 = (value ^ -0x48530b0372f3a0faL) - 0x4974f03d5c0c9cbbL;
               long value18 = (value2 ^ -0x658c8c75657ef347L) + 0x17a9464c48a4dc93L;
               if (this.countArray.length - this.count2 < count9) {
                  long value19 = (value17 ^ 0xe212b5ca66779dbL) - 0x2c908ce3a1161ffeL;
                  long value20 = (value18 ^ 0x67ee7f9fad65ffa7L) - 0x62a047bae254db73L;
                  count11 = 0;
                  value = (value19 ^ -0x10f6958bf8041212L) + 0x5ecff9fba7d30e95L;
                  value2 = (value20 ^ -0x7692f706fbc7f4c4L) - 0x700029bc8cf60729L;
               } else {
                  value = (value17 ^ -0x47fcc7da2ec5275dL) + 0x72c303e1b4eb2a4bL;
                  value2 = (value18 ^ -0x20ac099eb97003ebL) - 0x4f6d28fec777cf3aL;
               }
            } else {
               value = (value ^ 0x40213367e34380e4L) + 0x2af055f39d54d74bL;
               value2 = (value2 ^ 0x22c257c975217beaL) + 0x4d291aecdb9ab0bdL;
            }
         }

         this.count4 = count9;
         value = (value ^ -0x3917dac841ba585cL) - 0x5c84d15f34658ec8L;
         value2 = (value2 ^ 0x518fa416fe68a78L) + 0x17d28ebdf94ae60aL;
      }
   }

   public boolean isActiveAsFlag() {
      if (this.count4 + this.count2 <= this.countArray.length) {
         return this.count4 != 0 ? this.countArray[this.count2] != 0 : true;
      } else {
         return false;
      }
   }

   private CurveAccumulator resolveCurveAccumulatorByCurveAccumulator(long value, CurveAccumulator curveAccumulator2) {
      long value2 = value;
      CurveAccumulator curveAccumulator3 = null;
      int count5 = 0;
      int count6 = 0;
      int[] countArray2 = null;
      long value4 = (long)0;
      int count7 = 0;
      long value5 = 0L;
      int count8 = 0;
      int count9 = 0;
      int count10 = 0;
      int count11 = 0;
      boolean flag = false;
      int count12 = 0;
      int count13 = 0;
      int count14 = 0;
      long value6 = 0x4b546ed1e54fceb4L;
      long value7 = 0x5cb65a1cc86f5588L;

      while (true) {
         int count15 = (int)(value6 % 35L);
         if (count15 == 20) {
            count5 = curveAccumulator3.count4;
            value6 = (value6 ^ -0x79c8c9c9b27de1f6L) + 0x4898731e49715447L;
            value7 = (value7 ^ -0x1848dcd8a90d8d86L) + 0x55f12dff1864ae6cL;
         } else if (count15 != 25) {
            if (count15 != 18) {
               if (count15 != 28) {
                  if (count15 != 29) {
                     if (count15 != 3) {
                        if (count15 != 33) {
                           checkBlock: {
                              long value8;
                              long value9;
                              guardBlock: {
                                 long value10;
                                 long value11;
                                 searchBlock: {
                                    long value12;
                                    long value13;
                                    boolean flag2;
                                    if (count15 != 8) {
                                       if (count15 == 15) {
                                          flag = false;
                                          value6 = (value6 ^ 0x37e319df003fbeedL) + 0x1b2b7d4a98e9a572L;
                                          value7 = (value7 ^ 0x5b0f4de34284aecaL) + 0x68f99a66c9bfe714L;
                                          continue;
                                       }

                                       if (count15 == 23) {
                                          if (count12 == count7) {
                                             value6 = (value6 ^ -0x48806a1317d3dc77L) + 0x7a0b56a98d4dbd7bL;
                                             value7 = (value7 ^ -0x35bd420e45c321b8L) + 0x7dcd88a5f4fb269L;
                                             continue;
                                          }

                                          long value14 = (value6 ^ 0x2512e18ed247fa5aL) - 0x2b929b59250e3b80L;
                                          long value15 = (value7 ^ 0x9117d4f4d9911f7L) - 0x40fbc807f553b362L;
                                          long value16 = (long)count12 << 32 | (long)count14 & 4294967295L;
                                          if (value16 < 0L) {
                                             value10 = (value14 ^ -0x83e507526109a62L) - 0x6fc09279a7a4a582L;
                                             value11 = (value15 ^ 0xa00f32abcbc31aL) - 0x9ffb315a4abce6L;
                                             long value17 = computeTimestampByTime(value16, count7);
                                             count10 = (int)(value17 & 4294967295L);
                                             count11 = (int)(value17 >>> 32);
                                          } else {
                                             count10 = (int)(value16 / value5);
                                             count11 = (int)(value16 - (long)count10 * value5);
                                             value10 = (value14 ^ 0x4ca9999448b97c74L) + 0x3b579793c7895394L;
                                             value11 = (value15 ^ 0x10ec089a210ddee5L) + 0xf2c08661efb56ddL;
                                          }

                                          if (count10 == 0) {
                                             value8 = (value10 ^ -0x15d1be8f751add36L) - 0x6a25c0cf769adeb6L;
                                             value9 = (value11 ^ 0x189447012af68305L) + 0x793bd0126b282fdL;
                                             break guardBlock;
                                          }
                                          break searchBlock;
                                       }

                                       if (count15 == 10) {
                                          count10 = 0;
                                          value6 = (value6 ^ -0x5b28262d861b216L) - 0x6025dc77d44586ebL;
                                          value7 = (value7 ^ -0x5671279b85a13322L) + 0x20d876369870f108L;
                                          continue;
                                       }

                                       if (count15 == 17) {
                                          curveAccumulator3 = new CurveAccumulator(new int[this.count4 + 1]);
                                          value6 = (value6 ^ 0x9e8938fb6173296L) - 0x72b293c88a035acL;
                                          value7 = (value7 ^ -0x7ffec8bcbaea14d3L) - 0x347231b8a5bd8655L;
                                          continue;
                                       }

                                       if (count15 == 19) {
                                          if (value4 <= 0) {
                                             value6 = (value6 ^ 0x33de04d8465c13beL) - 0x5960ead48dff316aL;
                                             value7 = (value7 ^ -0x7904c37b75f711f6L) + 0x47ac8b1f17707170L;
                                          } else {
                                             value6 = (value6 ^ 0xa4a5db4f14a49d3L) - 0x39d5b07e9fc7ff6dL;
                                             value7 = (value7 ^ 0xace2cfa73815260L) + 0x26f670139390e222L;
                                          }
                                          continue;
                                       }

                                       if (count15 == 34) {
                                          count11 = count12 + count14;
                                          value6 = (value6 ^ 0x59c3ce88d1a53b23L) - 0x4bb09e93f1891cbL;
                                          value7 = (value7 ^ -0x4149105ac0cbf719L) + 0x1851c4577ab3c902L;
                                          continue;
                                       }

                                       if (count15 == 21) {
                                          curveAccumulator3.count2 = 1;
                                          value6 = (value6 ^ 0x3a9ef5ba5ca2d15eL) + 0x4c7e715199baac08L;
                                          value7 = (value7 ^ 0x3d794fc71b4877bfL) + 0x1657f7afbd1ddfb8L;
                                          continue;
                                       }

                                       if (count15 == 5) {
                                          count6 = count5 - 2 + 1;
                                          value6 = (value6 ^ 0x6d57ec1c5698368eL) + 0x3129602ec7a0ea8cL;
                                          value7 = (value7 ^ 0x53800ca330629596L) + 0x5775ec4d971bbc24L;
                                          continue;
                                       }

                                       if (count15 == 7) {
                                          curveAccumulator3.countArray[0] = 0;
                                          value6 = (value6 ^ -0x7655c76ca0926e17L) + 0x7e66fbc3c67963caL;
                                          value7 = (value7 ^ -0x5f638d3d1d98f7f1L) + 0x3f7b827d5102a8f2L;
                                          continue;
                                       }

                                       if (count15 == 13) {
                                          curveAccumulator2.countArray = new int[count6];
                                          value6 = (value6 ^ 0x141ede44da867f8bL) + 0x1ca63762f04c5adaL;
                                          value7 = (value7 ^ 0x172f65e1f193d2edL) - 0x6febbb0ff6ec763L;
                                          continue;
                                       }

                                       if (count15 == 14) {
                                          count14 = curveAccumulator3.countArray[count9 + 1 + curveAccumulator3.count2];
                                          value6 = (value6 ^ 0x3a97bd8e6bfd5ad5L) - 0x141705725cac83adL;
                                          value7 = (value7 ^ -0x43df4c4bc50dd12fL) + 0x7287228354caa763L;
                                          continue;
                                       }

                                       if (count15 == 11) {
                                          count12 = curveAccumulator3.countArray[count9 + curveAccumulator3.count2];
                                          value6 = (value6 ^ -0x2ea31cedb471eb37L) - 0x247605efc93ebf30L;
                                          value7 = (value7 ^ -0xd1a27da73118f1aL) + 0x32d4b1fae43927aL;
                                          continue;
                                       }

                                       if (count15 == 22) {
                                          curveAccumulator2.count2 = 0;
                                          value6 = (value6 ^ -0x15c72aa9bd22805L) - 0xb83f10fe72cf393L;
                                          value7 = (value7 ^ 0x2922adc365bc23c9L) + 0x2b55854866ae7016L;
                                          continue;
                                       }

                                       if (count15 == 16) {
                                          System.arraycopy(this.countArray, this.count2, curveAccumulator3.countArray, 1, this.count4);
                                          value6 = (value6 ^ -0x7e94ebcd3702b3e1L) + 0x5f613f7ce3e5af1aL;
                                          value7 = (value7 ^ 0x9a97e2eca860a7aL) - 0x5a6ce606bcaf7919L;
                                          continue;
                                       }

                                       if (count15 == 1) {
                                          curveAccumulator3.count2 = 0;
                                          value6 = (value6 ^ -0xc9381e3c2431ae5L) + 0x6247e2a2a127b34cL;
                                          value7 = (value7 ^ 0x5b2a88dff2c529b9L) + 0xb54fb8dd4ba4d69L;
                                          continue;
                                       }

                                       if (count15 == 9) {
                                          break checkBlock;
                                       }

                                       if (count15 != 27) {
                                          if (count15 != 0) {
                                             if (count15 != 32) {
                                                if (count15 != 6) {
                                                   if (count15 != 4) {
                                                      if (count15 != 12) {
                                                         if (count15 != 31) {
                                                            if (count15 != 30) {
                                                               if (count15 != 2) {
                                                                  if (count15 != 26) {
                                                                     count8 = (int)(value2 & 4294967295L);
                                                                     value6 = (value6 ^ -0x372cbf6a87fb52bfL) - 0x7408766c0b165fe3L;
                                                                     value7 = (value7 ^ 0x292d4059d4e30f46L) - 0x20d1070eea7f1bfbL;
                                                                  } else {
                                                                     count9 = 0;
                                                                     value6 = (value6 ^ -0x66b872bd53e76580L) - 0x7c4462098c53905cL;
                                                                     value7 = (value7 ^ -0xca3e6515d107c9bL) + 0x550c1ba700f44651L;
                                                                  }
                                                               } else {
                                                                  curveAccumulator3.count4++;
                                                                  value6 = (value6 ^ 0x7cc609be86e02455L) + 0x1a112e9d6511d873L;
                                                                  value7 = (value7 ^ 0x69ce00236f2ded69L) - 0x1b69b94d32ea36f6L;
                                                               }
                                                            } else {
                                                               curveAccumulator3.applyIndex3((int)value4);
                                                               value6 = (value6 ^ 0x6d7662e1ae58c595L) - 0x3abea604d0f03ff9L;
                                                               value7 = (value7 ^ -0x4598c215437117b7L) + 0x23a6ff1a011d6433L;
                                                            }
                                                         } else {
                                                            count10 = -1;
                                                            value6 = (value6 ^ 0x58960fc2cdad066fL) + 0x4daf8347699ad8edL;
                                                            value7 = (value7 ^ 0x77929ead69cc93ccL) + 0x18333680b59bc326L;
                                                         }
                                                      } else {
                                                         value2 <<= value4;
                                                         value6 = (value6 ^ 0x2ff20abce30bc926L) + 0x50c3d73f809de0b4L;
                                                         value7 = (value7 ^ 0x4bc595d26fbae7c1L) - 0x196c447c8cc2a105L;
                                                      }
                                                   } else if (curveAccumulator2.countArray.length >= count6) {
                                                      value6 = (value6 ^ -0x3421363e382d2efeL) - 0x1c7078faf311a82aL;
                                                      value7 = (value7 ^ 0x33e464489c5d086L) - 0x3ef6574429642ac0L;
                                                   } else {
                                                      value6 = (value6 ^ 0x2128735d2511efe6L) - 0x31b2da309fcef7d1L;
                                                      value7 = (value7 ^ -0x1158db7f1f13601L) + 0x365517d05d77b5baL;
                                                   }
                                                } else {
                                                   curveAccumulator3.count4 = this.count4;
                                                   value6 = (value6 ^ 0x7e68c5c2e812906aL) - 0x58e3c74ded6f8696L;
                                                   value7 = (value7 ^ -0x254f1b87cc5fce77L) - 0x46b6e88a226b268fL;
                                                }
                                             } else {
                                                count11 = 0;
                                                value6 = (value6 ^ 0x31f118484ee75f40L) - 0x2c8f7c1e34448ed2L;
                                                value7 = (value7 ^ -0x7175bd421c5f3c5aL) + 0x635833cbaf4daae2L;
                                             }
                                          } else if (curveAccumulator3.count4 != count5) {
                                             value6 = (value6 ^ -0x702336f42d25475aL) - 0xb2709b546962c66L;
                                             value7 = (value7 ^ 0x19c903f8ae5dc18eL) - 0x5f92bf608d5337d5L;
                                          } else {
                                             value6 = (value6 ^ 0x4402bc6dbbf2ec22L) + 0x282fc21768d1da0L;
                                             value7 = (value7 ^ -0x55604c62562df5d6L) - 0x10b64eba1fa1f895L;
                                          }
                                          continue;
                                       }

                                       flag2 = true;
                                       value12 = (value6 ^ -0x2faf149745d917b0L) - 0x502f56d785de1850L;
                                       value13 = (value7 ^ -0x3ef8cb78c416c5c8L) + 0x3f050c833596fa38L;
                                    } else {
                                       if (count11 + Integer.MIN_VALUE < count13) {
                                          value6 = (value6 ^ -0x3e5d68c5f58ea051L) + 0x62405d396a899bfbL;
                                          value7 = (value7 ^ -0x1629209a150394c1L) - 0x665b0c6295dcc378L;
                                          continue;
                                       }

                                       value12 = (value6 ^ 0x3e5e0e5def8ad215L) + 0x25ecc617cdc52661L;
                                       value13 = (value7 ^ 0x4ea248d06be176adL) - 0x4ef76d716d7a6e6L;
                                       flag2 = false;
                                    }

                                    flag = flag2;
                                    value10 = (value12 ^ -0x94405522e005dc5L) - 0x6e9a4594afb55e25L;
                                    value11 = (value13 ^ -0x3312922806bce5cdL) + 0x3b2ae333f73d1a33L;
                                 }

                                 value8 = (value10 ^ -0xd6582baf67761a2L) - 0x7279c4bcf7888a42L;
                                 value9 = (value11 ^ -0x5273bc3f93c9a5c3L) - 0x65743ebf940e25cbL;
                                 if (flag) {
                                    value8 = (value8 ^ -0x1d4cb01142c21761L) - 0x6292b25582f31881L;
                                    value9 = (value9 ^ -0x57f6ce5caec3b390L) - 0x67f8f0a0b1143b90L;
                                 } else {
                                    long value18 = (long)curveAccumulator3.countArray[count9 + 2 + curveAccumulator3.count2] & 4294967295L;
                                    long value19 = ((long)count11 & 4294967295L) << 32 | value18;
                                    long value20 = ((long)count8 & 4294967295L) * ((long)count10 & 4294967295L);
                                    if (!this.checkTime(value20, value19)) {
                                       value8 = (value8 ^ -0x7ae991506acda0beL) - 0x7cedd1966af1cb3eL;
                                       value9 = (value9 ^ 0x61be4987e01d5ba8L) - 0x2182387c1ff52458L;
                                    } else {
                                       count10--;
                                       count11 = (int)(((long)count11 & 4294967295L) + value5);
                                       if (((long)count11 & 4294967295L) < value5) {
                                          value8 = (value8 ^ -0x26a0449819092c4L) - 0x756c468e42249d24L;
                                          value9 = (value9 ^ 0x16eca600a25227d1L) + 0x152b9e009e4ddfd1L;
                                       } else {
                                          long value21 = value20 - ((long)count8 & 4294967295L);
                                          long value22 = ((long)count11 & 4294967295L) << 32 | value18;
                                          if (!this.checkTime(value21, value22)) {
                                             value8 = (value8 ^ 0xf8616567b0803dbL) - 0x77de9ae4507ffc5L;
                                             value9 = (value9 ^ -0x215ca291bd3a3a30L) + 0x4d60b29a3ec33dd0L;
                                          } else {
                                             count10--;
                                          }
                                       }
                                    }
                                 }

                                 curveAccumulator3.countArray[count9 + curveAccumulator3.count2] = 0;
                                 int count16 = count9 + curveAccumulator3.count2;
                                 int[] countArray3 = curveAccumulator3.countArray;
                                 int count17 = this.computeCount(countArray3, count7, count8, count10, count16);
                                 if (count17 + Integer.MIN_VALUE <= count13) {
                                    value8 = (value8 ^ 0x6ede648a2a149cfcL) + 0x194a2485e9f3731cL;
                                    value9 = (value9 ^ -0x7d35ab13bec95bf4L) - 0x7eb653e3c10da3fcL;
                                 } else {
                                    int count18 = count9 + 1 + curveAccumulator3.count2;
                                    int[] countArray4 = curveAccumulator3.countArray;
                                    this.computeCount(count7, count8, countArray4, count18);
                                    count10--;
                                 }

                                 countArray2[count9] = count10;
                              }

                              count9++;
                              value6 = (value8 ^ -0x9bf4564beb6b395L) + 0x7200945839b65cadL;
                              value7 = (value9 ^ -0x5341387c44bff354L) - 0x61f8257ab31906e7L;
                           }

                           if (count9 >= count6) {
                              if (value4 > 0) {
                                 curveAccumulator3.applyIndex((int)value4);
                              }

                              curveAccumulator2.updateState3();
                              curveAccumulator3.updateState3();
                              return curveAccumulator3;
                           }

                           value6 = (value6 ^ -0x5f0444facb0ef0b2L) - 0x5cafb66cec3c2c02L;
                           value7 = (value7 ^ 0x37e2af9e87bf486eL) + 0x61836b285dec151eL;
                        } else {
                           value4 = (long)Long.numberOfLeadingZeros(value2);
                           value6 = (value6 ^ -0x59c3de9a7bc1a363L) - 0x27ef408c30c639bbL;
                           value7 = (value7 ^ -0xa3c3b842c396367L) + 0x66344e0fd6e57177L;
                        }
                     } else {
                        curveAccumulator2.count4 = count6;
                        value6 = (value6 ^ -0x260b74cfa291b16bL) - 0x3e4fc10fd977cc59L;
                        value7 = (value7 ^ -0x5d0679428301f1abL) - 0x6a851c9b69b3cc3L;
                     }
                  } else {
                     count7 = (int)(value2 >>> 32);
                     value6 = (value6 ^ -0xe16d2a46bfb7e60L) - 0x322b35b929ff7676L;
                     value7 = (value7 ^ 0xe616e931421d58fL) + 0x6aa9224763d96fc9L;
                  }
               } else {
                  value5 = (long)count7 & 4294967295L;
                  value6 = (value6 ^ -0x45c50484d9f7d12L) - 0x4281bf8aa5e7ccb6L;
                  value7 = (value7 ^ 0x7af5c48c4133c33bL) - 0x195c7b59b35dc73eL;
               }
            } else {
               countArray2 = curveAccumulator2.countArray;
               value6 = (value6 ^ 0x98c2f201c627aa1L) - 0x3955b3dd9da4138eL;
               value7 = (value7 ^ -0x47de48474ea4b61eL) + 0x4e8f4147ce946315L;
            }
         } else {
            count13 = count12 + Integer.MIN_VALUE;
            value6 = (value6 ^ 0x52ac09174c8b2176L) + 0x24fd338d0a5a2cb1L;
            value7 = (value7 ^ -0x6e129ce5050bbd31L) + 0x1bc222b0f4b5803fL;
         }
      }
   }

   public void applyIndexArray(int[] countArray2, int count5) {
      this.countArray = countArray2;
      this.count4 = count5;
      this.count2 = 0;
   }

   private CurveAccumulator resolveCurveAccumulatorByCurveAccumulator(CurveAccumulator curveAccumulator2, CurveAccumulator curveAccumulator3) {
      int count5 = 0;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      CurveAccumulator curveAccumulator4 = null;
      count5 = curveAccumulator2.count4;
      if (count5 % 2 == 0 && count5 >= 80) {
         entry = new CurveAccumulator(this);
         int count6 = 32 * (count5 / 2);
         ((CurveAccumulator)entry).applyIndex6(count6);
         int count7 = count5 / 2;
         this.applyIndex2(count7);
         entry2 = new CurveAccumulator();
         entry3 = ((CurveAccumulator)entry).resolveCurveAccumulator3(curveAccumulator2, (CurveAccumulator)entry2);
         int count8 = count5 / 2;
         this.applyCurveAccumulator2((CurveAccumulator)entry3, count8);
         curveAccumulator4 = this.resolveCurveAccumulator3(curveAccumulator2, curveAccumulator3);
         int count9 = count5 / 2;
         curveAccumulator3.applyCurveAccumulator2((CurveAccumulator)entry2, count9);
         return curveAccumulator4;
      } else {
         return this.resolveCurveAccumulator(curveAccumulator2, curveAccumulator3);
      }
   }

   public static int computeCountByIndex(int count5) {
      int count6 = 0;
      count6 = count5 * (2 - count5 * count5);
      count6 *= 2 - count5 * count6;
      count6 *= 2 - count5 * count6;
      return count6 * (2 - count5 * count6);
   }

   private int computeCount(int[] countArray2, int[] countArray3, int count5) {
      long value = 0L;
      long value2 = 0L;
      value = 0L;

      for (int index = countArray2.length - 1; index >= 0; index--) {
         value2 = ((long)countArray2[index] & 4294967295L) + ((long)countArray3[index + count5] & 4294967295L) + value;
         countArray3[index + count5] = (int)value2;
         value = value2 >>> 32;
      }

      return (int)value;
   }

   public CurveAccumulator(CurveAccumulator curveAccumulator2) {
      this.count4 = curveAccumulator2.count4;
      this.countArray = Arrays.copyOfRange(curveAccumulator2.countArray, curveAccumulator2.count2, curveAccumulator2.count2 + this.count4);
   }

   public CurveAccumulator() {
      this.countArray = new int[1];
      this.count4 = 0;
   }

   public CurveAccumulator(int[] countArray2) {
      this.countArray = countArray2;
      this.count4 = countArray2.length;
   }

   public CurveAccumulator(SignumComparable signumComparable) {
      this.count4 = signumComparable.countArray.length;
      this.countArray = Arrays.copyOf(signumComparable.countArray, this.count4);
   }

   public CurveAccumulator(int count5) {
      this.countArray = new int[1];
      this.count4 = 1;
      this.countArray[0] = count5;
   }

   @Override
   public String toString() {
      return this.resolveSignumComparableByIndex(1).toString();
   }
}
