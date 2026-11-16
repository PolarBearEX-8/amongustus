package client.util;

public class Controller15 {
   public long timestamp;
   public long timestamp2 = System.nanoTime() ^ Runtime.getRuntime().freeMemory();

   public void applyByteArray(byte[] byteValueArray) {
      int count = 0;
      int count2 = 0;
      long value = 0L;
      long value2 = 0L;
      long value3 = 0L;
      int count3 = 0;
      long value4 = 0x168a0925cdea3d78L;
      long value5 = 0x2c395cf7f78f7769L;

      while (true) {
         int count4 = (int)(value4 % 19L);
         if (count4 != 12) {
            guardBlock: {
               long value6;
               long value7;
               searchBlock: {
                  if (count4 != 13) {
                     if (count4 == 4) {
                        if (count < count2) {
                           value4 = (value4 ^ -0x24790c04fc4f7772L) + 0x2d0e01927a05bd6fL;
                           value5 = (value5 ^ -0x28cecd10a4b05b48L) - 0x21750db92c908fb9L;
                           continue;
                        }

                        value6 = (value4 ^ -0x6236ca944fdbf19fL) - 0x6106ba8f4997c0c2L;
                        value7 = (value5 ^ 0x25f5daea839746c8L) - 0x43e695d5e1dd4b92L;
                        break searchBlock;
                     }

                     if (count4 == 3) {
                        this.timestamp2 = value2 ^ value ^ value2 >>> 18 ^ value >>> 5;
                        value4 = (value4 ^ 0x4b6a87bfaa353a5bL) + 0x118798a3b3fa35cL;
                        value5 = (value5 ^ -0x467fbce141a105fdL) - 0x5c4b0c39702ad774L;
                        continue;
                     }

                     if (count4 == 8) {
                        this.timestamp = value;
                        value4 = (value4 ^ -0x560109e07c299836L) - 0x47daef76149c5f04L;
                        value5 = (value5 ^ -0x1215ce6056f2ae94L) - 0x6c2ff16c591bdf7aL;
                        continue;
                     }

                     if (count4 == 6) {
                        byteValueArray[count] = (byte)((int)(value3 >> count3 * 8));
                        value4 = (value4 ^ -0x1dd9065c8904f685L) + 0x4dfe20b1fd90f819L;
                        value5 = (value5 ^ -0x6d3c5834e42b00e3L) - 0x2f90d5c5361d764L;
                        continue;
                     }

                     if (count4 == 5) {
                        value2 ^= value2 << 23;
                        value4 = (value4 ^ -0x52756eacb1bcf20dL) - 0x5260928743896a26L;
                        value5 = (value5 ^ 0x1c7e10fab412ef12L) + 0x4b790f397889c91aL;
                        continue;
                     }

                     if (count4 == 0) {
                        count3 = 0;
                        value4 = (value4 ^ -0x7b2c8514482b5410L) + 0x68a74927f55a34bbL;
                        value5 = (value5 ^ -0x59c07c26a4ba3327L) + 0x71c97d3154ef3addL;
                        continue;
                     }

                     if (count4 == 9) {
                        value2 = this.timestamp;
                        value4 = (value4 ^ 0x23138d3c6b672ecfL) - 0x4a115f290ad8f1f6L;
                        value5 = (value5 ^ 0x5089beedffad18c1L) + 0x1f50d1b336a1d03L;
                        continue;
                     }

                     if (count4 != 15) {
                        if (count4 == 7) {
                           value3 *= 0x5851f42d4c957f2dL;
                           value4 = (value4 ^ 0x1516f30cd30a54L) + 0x3cbb3cec65aae234L;
                           value5 = (value5 ^ 0x146ec116a26b4aecL) - 0xb2dfb94507c5bc8L;
                           continue;
                        }

                        if (count4 != 17) {
                           if (count4 != 1) {
                              if (count4 != 14) {
                                 if (count4 != 11) {
                                    if (count4 != 18) {
                                       if (count4 != 2) {
                                          if (count4 != 16) {
                                             value = this.timestamp2;
                                             value4 = (value4 ^ -0x73cfc4a032384afeL) - 0x72f53585c8faed1dL;
                                             value5 = (value5 ^ -0xe4f764c691e392cL) + 0x78206a86bd50d9bdL;
                                          } else {
                                             value3 ^= value3 >>> 33;
                                             value4 = (value4 ^ 0x1565a205d91916bfL) - 0x1ce0dda09b28e9cfL;
                                             value5 = (value5 ^ -0x758ca1c5e7f32b0eL) - 0x3f5ae8ebfea80e1L;
                                          }
                                       } else {
                                          count = 0;
                                          value4 = (value4 ^ -0x60ceb69f12210593L) - 0x2a6681f28e84c18aL;
                                          value5 = (value5 ^ -0x70eaddfa956fdd1eL) - 0x67b957c9def8221aL;
                                       }
                                    } else {
                                       count2 = byteValueArray.length;
                                       value4 = (value4 ^ 0x5257d174c12678bfL) - 0xbf265ce7e277967L;
                                       value5 = (value5 ^ -0x6c78352ddfbd1499L) - 0x313fac8935ade72aL;
                                    }
                                 } else {
                                    value3 ^= value3 >>> 33;
                                    value4 = (value4 ^ -0x237b3cda24bb20c4L) - 0x3d9db9a0f4580afaL;
                                    value5 = (value5 ^ 0x4e1ba88e0f2561ccL) + 0x2a2d9e4b025fea0eL;
                                 }
                              } else {
                                 value3 = value + value2;
                                 value4 = (value4 ^ 0x54149bcbb73ce6c3L) + 0x31fdc2178829b319L;
                                 value5 = (value5 ^ 0x7e394b28395d0367L) - 0x6289b25dc618c413L;
                              }
                           } else {
                              value3 ^= value3 >>> 33;
                              value4 = (value4 ^ 0x16406d6686cd2b59L) - 0x5efa9a6be98f329L;
                              value5 = (value5 ^ -0x60f2de62b09c80fdL) - 0x6cc81d39be7a67a4L;
                           }
                           continue;
                        }
                        break guardBlock;
                     }

                     count3++;
                     count++;
                     value4 = (value4 ^ 0x2e5b69a5cf44a866L) - 0x6595422a07dcdab6L;
                     value5 = (value5 ^ 0x4e5ff6a5ae167564L) - 0x422815c7ca8d6a43L;
                  }

                  if (count3 < 8) {
                     value4 = (value4 ^ 0x7ddaa8cc42af0072L) - 0x38859576cb51c331L;
                     value5 = (value5 ^ -0x4a654cc4ad976656L) - 0x27b2db3206e848adL;
                     continue;
                  }

                  value6 = (value4 ^ 0x1cbef85c39f56ad1L) + 0x2ac653c141d4478dL;
                  value7 = (value5 ^ -0x706130c1b8171347L) + 0x7f56e451af2b1918L;
               }

               value4 = (value6 ^ 0x57899b056d8c8dfbL) - 0x1520ff7ffb4a2bafL;
               value5 = (value7 ^ 0x780e7e1dd71c7eedL) + 0x16188e29f9e556d5L;
            }

            if (count >= count2) {
               return;
            }

            value4 = (value4 ^ -0x43d7c82aa8b7979dL) - 0x55d2d2d099bce4e0L;
            value5 = (value5 ^ 0x2cbd4094eff90d02L) - 0x564e144877046958L;
         } else {
            value3 *= 0x2545f4914f6cdd1dL;
            value4 = (value4 ^ -0x482cd21ee1111a6eL) + 0x24105e9c64fc6558L;
            value5 = (value5 ^ -0x407b412a0ae0f8L) + 0x497ab70585504c6bL;
         }
      }
   }

   public Controller15() {
      this.timestamp = System.currentTimeMillis() ^ Runtime.getRuntime().totalMemory();

      for (int index = 0; index < 15; index++) {
         this.timestamp2 = this.timestamp2 ^ System.nanoTime();
         this.timestamp2 = (this.timestamp2 ^ this.timestamp2 >>> 30) * 0x2545f4914f6cdd1dL ^ (this.timestamp2 << index) + (long)index;
         Object entry = new Object();
         this.timestamp = this.timestamp ^ (long)entry.hashCode() ^ System.nanoTime();
         this.timestamp = (this.timestamp ^ this.timestamp >>> 27) * 0x5851f42d4c957f2dL ^ Thread.currentThread().getId() << index;
         this.timestamp2 = this.timestamp2 ^ this.timestamp;
         this.timestamp = this.timestamp ^ this.timestamp2;
      }

      for (int index2 = 0; index2 < 20; index2++) {
         long timestamp3 = this.timestamp2;
         long timestamp4 = this.timestamp;
         long keySalt = timestamp4 ^ timestamp4 << 23;
         this.timestamp2 = keySalt ^ timestamp3 ^ keySalt >>> 18 ^ timestamp3 >>> 5;
         this.timestamp = timestamp3;
      }
   }

   public long computeTimestamp(int count, int count2) {
      long value = 0L;
      long value2 = 0L;
      long value3 = 0L;
      int count3 = 0;
      int count4 = 0;
      value = this.timestamp2;
      value2 = this.timestamp;
      value2 ^= value2 << 23;
      value3 = value + value2;
      this.timestamp2 = value2 ^ value ^ value2 >>> 18 ^ value >>> 5;
      this.timestamp = value;
      value3 ^= value3 >>> 33;
      value3 *= 0x2545f4914f6cdd1dL;
      value3 ^= value3 >>> 33;
      value3 *= 0x5851f42d4c957f2dL;
      value3 ^= value3 >>> 33;
      if (count != 2) {
         if (count == 3) {
            return (long)((double)(value3 >>> 11) * 1.110223E-16F);
         } else if (count == 0) {
            return value3;
         } else {
            return count == 1 ? (long)((int)value3) : value3;
         }
      } else if (count2 <= 0) {
         throw new IllegalArgumentException("!");
      } else if ((count2 & -count2) == count2) {
         return (long)((int)((long)count2 * (long)((int)(value3 >>> 1)) >>> 31));
      } else {
         do {
            count3 = (int)(value3 >>> 1);
            count4 = count3 % count2;
         } while (count3 - count4 + (count2 - 1) < 0);

         return (long)count4;
      }
   }
}
