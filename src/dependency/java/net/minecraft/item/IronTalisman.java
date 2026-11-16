package net.minecraft.item;

import client.setting.MovementWeightProperty;
import client.util.ContextStringEntry;
import client.util.IntArrayEntry12Entry;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class IronTalisman {
   public final MovementWeightProperty movementWeightProperty;
   public final IntArrayEntry12Entry intArrayEntry12Entry;

   private void applyTimeArray(long[][] valueMatrix, byte[] byteValueArray, int count, int count2) {
      int count3 = 0;
      int count4 = 0;
      int count5 = 0;
      int count6 = 0;
      long value = 0x60c182ae2869235eL;
      long value2 = 0x336c6332363a9b70L;

      while (true) {
         int count7 = (int)(value % 11L);
         if (count7 == 0) {
            count5 = 0;
            value = (value ^ 0x76a2af7af75fd183L) + 0x1918727e19229f2L;
            value2 = (value2 ^ 0x5a0f67e18bd84875L) + 0x3aa703fab6106916L;
         } else if (count7 != 8) {
            guardBlock: {
               searchBlock: {
                  if (count7 != 1) {
                     if (count7 == 9) {
                        break;
                     }

                     if (count7 == 2) {
                        count4 = count2;
                        value = (value ^ 0x52930308b964537L) - 0x13dca6356d87646bL;
                        value2 = (value2 ^ 0x64f7c0c1dafba72cL) - 0x94f62b7fa58a64fL;
                        continue;
                     }

                     if (count7 == 4) {
                        break searchBlock;
                     }

                     if (count7 == 10) {
                        if (count3 % 8 == 0) {
                           value = (value ^ -0xbcd929ca8f60b35L) + 0x7f96546e1c245053L;
                           value2 = (value2 ^ -0x52fc3e6d37ddbbe4L) + 0x5724ec50371bd44dL;
                           continue;
                        }
                        break guardBlock;
                     }

                     if (count7 == 3) {
                        this.applyTimeArray(valueMatrix, byteValueArray, count3, count6, count5);
                        value = (value ^ -0x60410e2adbab76ceL) + 0x7c9a82b98b5290cdL;
                        value2 = (value2 ^ -0x7b9db4b5158d7bf6L) - 0x70f5f7874580af9fL;
                        continue;
                     }

                     if (count7 == 5) {
                        count3 = count;
                        value = (value ^ 0x25e8b34cf7da2bc1L) - 0x2ad6e4b177f1c8d0L;
                        value2 = (value2 ^ 0x65014da421556743L) + 0x670b808d55775f30L;
                        continue;
                     }

                     if (count7 != 6) {
                        if (count4 >= 64) {
                           value = (value ^ -0x557193a3a56846fdL) + 0x479e7c0cd1e201b9L;
                           value2 = (value2 ^ 0x5706169c62fbee2aL) - 0x7adbfc2b908ea0acL;
                           continue;
                        }
                        break guardBlock;
                     }

                     count3 += 64;
                     count4 -= 64;
                     long value3 = (value ^ -0x52a6d9db81f85437L) - 0x7fec30befb3897d0L;
                     long value4 = (value2 ^ 0x2acc95cb8208eb02L) + 0x58e06b42cc78c795L;
                     count6++;
                     value = (value3 ^ -0x47f619ea6dcaabbeL) - 0x31d47f6d2898a551L;
                     value2 = (value4 ^ 0x3e95bb70eedaadddL) + 0x2cd619ac5714ecb8L;
                  }

                  if (count6 < 5) {
                     value = (value ^ 0x1e2acd689b19f852L) - 0x2f8adc930a694d0bL;
                     value2 = (value2 ^ -0x4dc33054c5466cf3L) + 0x195ef98b28f81677L;
                     continue;
                  }

                  long value5 = (value ^ 0x33e2a8ec1dfeece3L) - 0x2a013f7968ce2a12L;
                  long value6 = (value2 ^ 0x107a4709f5598400L) - 0x31c83b5490a1cc15L;
                  count5++;
                  value = (value5 ^ -0x79ae0e90ce18bfccL) - 0x1cf108282ae7693cL;
                  value2 = (value6 ^ 0x6cf49f3885516ff1L) + 0x4bae037135256cf8L;
               }

               if (count5 >= 5) {
                  return;
               }

               value = (value ^ 0x28bb92d4ec22520fL) - 0x42523c8f236b4c4eL;
               value2 = (value2 ^ -0x55cea053096a1c38L) - 0x44fdb8e227771c06L;
               continue;
            }

            this.applyTimeArray(valueMatrix, byteValueArray, count3, count4, count6, count5);
            break;
         } else {
            count6 = 0;
            value = (value ^ 0x73cb3a50ba367ae1L) + 0xc2a2fe333947cd9L;
            value2 = (value2 ^ 0x69c78cacf0cb77efL) + 0x17f0749f181885a5L;
         }
      }
   }

   private boolean matchesByteArray(byte[] byteValueArray, int count) {
      int count2 = 0;
      int count3 = 0;
      count2 = count / 8;
      count3 = count % 8;
      return 0 != (byteValueArray[count2] & 1 << count3);
   }

   public int computeCount(InputStream inputStream, long[][] valueMatrix, byte[] byteValueArray) throws IOException {
      int count = 0;

      for (count = this.computeCount(inputStream, byteValueArray); count == ContextStringEntry.computeCount(this.movementWeightProperty); count = this.computeCount(inputStream, byteValueArray)) {
         byte byteValue = 0;
         this.applyTimeArray(valueMatrix, byteValueArray, byteValue, count);
         this.intArrayEntry12Entry.applyTimeArray(valueMatrix);
      }

      return count;
   }

   public void applyByteArray(byte[] byteValueArray, long[][] valueMatrix) {
      int count = 0;
      int count2 = 0;
      int count3 = 0;
      count = byteValueArray.length << 3;
      count2 = 0;

      do {
         count3 = Math.min(ContextStringEntry.computeCount(this.movementWeightProperty), count - count2);
         this.applyTimeArray(valueMatrix, byteValueArray, count2, count3);
         this.intArrayEntry12Entry.applyTimeArray(valueMatrix);
         count2 += ContextStringEntry.computeCount(this.movementWeightProperty);
      } while (count2 < count);
   }

   private void applyTimeArray(long[][] valueMatrix, byte[] byteValueArray, int count, int count2, int count3, int count4) {
      int count5 = 0;
      int count6 = 0;
      int count7 = 0;
      int count8 = 0;
      int count9 = 0;
      long value = -0x7c161bf44d1bc36eL;
      count5 = count + count2;
      value = (value ^ -0x3c2f68d09bb975e0L) + 0x773f5e08915b69e9L;
      count6 = 0;
      value = (value ^ 0x33ac998cc186d78dL) + 0x3ed8584542719815L;
      count7 = count3;
      value = (value ^ -0x173999bf669eecd8L) - 0x55fe9baab397c1d1L;
      count8 = count4;
      value = (value ^ 0x1f1749be01f8b623L) - 0x5ccc842466a3f0f1L;
      count9 = count;
      value = (value ^ -0x5c0a187a8b79e74cL) + 0x4d4df63bd7a62117L;

      while (count9 < count5) {
         value = (value ^ 0x65123f833f3ae49eL) - 0x33acb86a1aa1993L;
         if (!this.matchesByteArray(byteValueArray, count9)) {
            value = (value ^ -0x6c3c57eacc7948d5L) - 0xfe2b094a0b41ccaL;
         } else {
            value = (value ^ 0x120bef1585a1368bL) + 0x7b414d2aab3ae082L;
            valueMatrix[count7][count8] = valueMatrix[count7][count8] ^ (value % 714723L ^ -432719L) << count6;
            value = (value ^ -0x621c3f0779b29c66L) + 0x5f320617a841a18eL;
         }

         if (++count6 != 64) {
            value = (value ^ -0x2ac3df491e472f24L) + 0x4022bf3fc0a6c660L;
         } else {
            value = (value ^ -0x3686df4fbc9e1522L) + 0x2be3afb3b0278a46L;
            count7++;
            count6 = 0;
            value = (value ^ -0x5691d75424a90482L) - 0x48160921e8f16916L;
         }

         if (count3 != 5) {
            value = (value ^ -0x1209c7a6e82368bL) - 0x3a258b8a06f83f12L;
         } else {
            value = (value ^ -0x588cf7a1174543c1L) + 0x12e8dc8eb0ddcfbeL;
            count8++;
            count7 = 0;
            value = (value ^ -0x46df18d64853729L) + 0x7213746bc8303637L;
         }

         count9++;
         value = (value ^ -0x338ee88dde7879beL) + 0x669f0a3e5a7941c5L;
      }
   }

   private int computeCount(InputStream inputStream, byte[] byteValueArray) throws IOException {
      int count = 0;
      int count2 = 0;
      long value = 0x17f07710af031595L;
      long value2 = -0x29b0649d632c37daL;

      while (true) {
         int count3 = (int)(value % 8L);
         if (count3 != 3) {
            if (count3 == 6) {
               count2 = inputStream.read(byteValueArray);
               value = (value ^ 0x28e0e084ea03f3bbL) - 0x3a748545799b99aL;
               value2 = (value2 ^ -0x36babf7bfd29d367L) - 0x7c34f7e70600596bL;
               continue;
            }

            if (count3 == 2) {
               return count * 8;
            }

            if (count3 != 4) {
               if (count3 != 1) {
                  if (count3 != 5) {
                     if (count3 != 0) {
                        count += count2;
                        value = (value ^ -0x6393d3815db342eL) - 0x5fa5ca91c414f73cL;
                        value2 = (value2 ^ 0x7ab0475ed50362efL) + 0x7159c896508ddc23L;
                     } else {
                        Arrays.fill(byteValueArray, count, byteValueArray.length, (byte)0);
                        value = (value ^ 0x4e69c583f828f5cbL) + 0x1ae1c9ed2ee4d087L;
                        value2 = (value2 ^ 0x22136f50753eb633L) - 0x15fa231bde206920L;
                     }
                  } else {
                     count = 0;
                     value = (value ^ 0x72c51f9385938102L) - 0x5b03b9d4df098759L;
                     value2 = (value2 ^ 0x1e762ae1927f7000L) + 0x3cb4ea411c5ea780L;
                  }
               } else {
                  count2 = inputStream.read(byteValueArray, count, byteValueArray.length - count);
                  value = (value ^ 0x6ff674c11439a936L) + 0x3207b88f689d84c5L;
                  value2 = (value2 ^ 0x385c8884fe3b81fbL) - 0xaca908bad1fc760L;
               }
               continue;
            }

            value = (value ^ -0x7a043c2b2dbf22dL) + 0x7fc16cf569afafccL;
            value2 = (value2 ^ -0x574bbcb0a9805a08L) - 0x4c0f6052889089a5L;
         }

         if (count2 <= 0) {
            long value3 = (value ^ -0x106036ccf62d141cL) + 0x768157f86ce5e9bdL;
            long value4 = (value2 ^ -0x73114fc1e2da07e6L) + 0x5799ad7dbb1924b3L;
            if (count >= byteValueArray.length) {
               value = (value3 ^ -0x45bbb87521dd20bdL) - 0x6489b21ae0f4dcbdL;
               value2 = (value4 ^ 0x2f6c1138cab630eeL) - 0x1e2d583214a0eb5L;
            } else {
               value = (value3 ^ 0xec4eecb10414dbeL) - 0x5985a74a221365f2L;
               value2 = (value4 ^ 0x4ef293e2e67b8906L) - 0x6f5a62e18425689eL;
            }
         } else {
            value = (value ^ 0x255480cc23b09c58L) + 0x9399ccc9498a70cL;
            value2 = (value2 ^ -0x52b16d92cc025b46L) - 0xf425b3332a1b056L;
         }
      }
   }

   private void applyTimeArray(long[][] valueMatrix, byte[] byteValueArray, int count, int count2, int count3) {
      byte byteValue = 0;
      int count4 = 0;
      long value = 0L;
      byteValue = 8;
      count4 = count / 8;
      value = 0L;

      for (int index = byteValue - 1; index >= 0; index--) {
         value <<= 8;
         value += (long)Byte.toUnsignedInt(byteValueArray[count4 + index]);
      }

      valueMatrix[count2][count3] = valueMatrix[count2][count3] ^ value;
   }

   public IronTalisman(MovementWeightProperty movementWeightProperty2) {
      this.movementWeightProperty = movementWeightProperty2;
      this.intArrayEntry12Entry = new IntArrayEntry12Entry();
   }
}
