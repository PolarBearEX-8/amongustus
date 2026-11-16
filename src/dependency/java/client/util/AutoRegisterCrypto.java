package client.util;

import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

public class AutoRegisterCrypto {
   public static final byte[] byteFlagArray2 = new byte[]{13, 10};
   public final boolean enabled;
   public static final AutoRegisterCrypto autoRegisterCrypto3 = new AutoRegisterCrypto(true, null, -1, true);
   public final int count2;
   public static final char[] charCodeArray = new char[]{
      'A',
      'B',
      'C',
      'D',
      'E',
      'F',
      'G',
      'H',
      'I',
      'J',
      'K',
      'L',
      'M',
      'N',
      'O',
      'P',
      'Q',
      'R',
      'S',
      'T',
      'U',
      'V',
      'W',
      'X',
      'Y',
      'Z',
      'a',
      'b',
      'c',
      'd',
      'e',
      'f',
      'g',
      'h',
      'i',
      'j',
      'k',
      'l',
      'm',
      'n',
      'o',
      'p',
      'q',
      'r',
      's',
      't',
      'u',
      'v',
      'w',
      'x',
      'y',
      'z',
      '0',
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9',
      '+',
      '/'
   };
   public static final AutoRegisterCrypto autoRegisterCrypto2 = new AutoRegisterCrypto(false, null, -1, true);
   public static final char[] charCodeArray2 = new char[]{
      'A',
      'B',
      'C',
      'D',
      'E',
      'F',
      'G',
      'H',
      'I',
      'J',
      'K',
      'L',
      'M',
      'N',
      'O',
      'P',
      'Q',
      'R',
      'S',
      'T',
      'U',
      'V',
      'W',
      'X',
      'Y',
      'Z',
      'a',
      'b',
      'c',
      'd',
      'e',
      'f',
      'g',
      'h',
      'i',
      'j',
      'k',
      'l',
      'm',
      'n',
      'o',
      'p',
      'q',
      'r',
      's',
      't',
      'u',
      'v',
      'w',
      'x',
      'y',
      'z',
      '0',
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9',
      '-',
      '_'
   };
   public final byte[] byteFlagArray;
   public static final AutoRegisterCrypto autoRegisterCrypto = new AutoRegisterCrypto(false, byteFlagArray2, 76, true);
   public final boolean enabled2;
   public static final int count = 76;

   private int computeCount(byte[] byteValueArray, int count3, int count4, byte[] byteValueArray2) {
      char[] characterArray = this.enabled2 ? charCodeArray2 : charCodeArray;
      int count5 = count3;
      int count6 = (count4 - count3) / 3 * 3;
      int count7 = count3 + count6;
      if (this.count2 > 0 && count6 > this.count2 / 4 * 3) {
         count6 = this.count2 / 4 * 3;
      }

      int count8 = 0;

      while (count5 < count7) {
         int count9 = Math.min(count5 + count6, count7);
         int count10 = count5;
         int count11 = count8;

         while (count10 < count9) {
            int count12 = (byteValueArray[count10++] & 255) << 16 | (byteValueArray[count10++] & 255) << 8 | byteValueArray[count10++] & 255;
            byteValueArray2[count11++] = (byte)characterArray[count12 >>> 18 & 63];
            byteValueArray2[count11++] = (byte)characterArray[count12 >>> 12 & 63];
            byteValueArray2[count11++] = (byte)characterArray[count12 >>> 6 & 63];
            byteValueArray2[count11++] = (byte)characterArray[count12 & 63];
         }

         int count13 = (count9 - count5) / 3 * 4;
         count8 += count13;
         count5 = count9;
         if (count13 == this.count2 && count9 < count4) {
            for (byte byteValue : this.byteFlagArray) {
               byteValueArray2[count8++] = byteValue;
            }
         }
      }

      if (count5 < count4) {
         int count14 = byteValueArray[count5++] & 255;
         byteValueArray2[count8++] = (byte)characterArray[count14 >> 2];
         if (count5 == count4) {
            byteValueArray2[count8++] = (byte)characterArray[count14 << 4 & 63];
            if (this.enabled) {
               byteValueArray2[count8++] = 61;
               byteValueArray2[count8++] = 61;
            }
         } else {
            int count15 = byteValueArray[count5++] & 255;
            byteValueArray2[count8++] = (byte)characterArray[count14 << 4 & 63 | count15 >> 4];
            byteValueArray2[count8++] = (byte)characterArray[count15 << 2 & 63];
            if (this.enabled) {
               byteValueArray2[count8++] = 61;
            }
         }
      }

      return count8;
   }

   public AutoRegisterCrypto getAutoRegisterCrypto() {
      return this.enabled ? new AutoRegisterCrypto(this.enabled2, this.byteFlagArray, this.count2, false) : this;
   }

   public OutputStream resolveOutputStream(OutputStream outputStream) {
      Objects.requireNonNull(outputStream);
      return new BufferedFilterOutputStream(outputStream, this.enabled2 ? charCodeArray2 : charCodeArray, this.byteFlagArray, this.count2, this.enabled);
   }

   private final int computeCount(int count3) {
      int count4 = 0;
      int count5 = 0;
      long value = 0x6f5b48c9fcb892c5L;
      long value2 = 0x5509e242974f05d2L;

      while (true) {
         int count6 = (int)(value % 5L);
         if (count6 == 2) {
            break;
         }

         if (count6 == 1) {
            count4 = 4 * (count3 / 3) + (count5 != 0 ? count5 + 1 : 0);
            break;
         }

         if (count6 != 3) {
            if (count6 != 4) {
               if (!this.enabled) {
                  long value3 = (value ^ 0x1ed3b69f78bd30afL) + 0x4a4e0b79035974a0L;
                  long value4 = (value2 ^ -0x12e15c31fda389d1L) - 0x12d69c6131d2812fL;
                  count5 = count3 % 3;
                  value = (value3 ^ -0x38119760eb73126L) - 0x2ff7a2dbf19092e2L;
                  value2 = (value4 ^ 0x13d1abaa451a8006L) - 0x63027b1a494230fbL;
               } else {
                  value = (value ^ -0x37900765cba4b837L) + 0x2ddb597768e0ebe3L;
                  value2 = (value2 ^ 0x551a5cfb1dab67aeL) + 0x90c27ea343723b2L;
               }
            } else {
               count4 = 0;
               value = (value ^ -0x4e97abd9badc7bcL) + 0x7dfe7bb74c23ba14L;
               value2 = (value2 ^ 0x445a345411896403L) - 0x3c7211501cb9e704L;
            }
         } else {
            count4 = 4 * ((count3 + 2) / 3);
            value = (value ^ 0x1a092de9066f55f3L) + 0x38f7e49d64b36e0eL;
            value2 = (value2 ^ -0x3e5012879d3027afL) - 0x237f62b892940d91L;
         }
      }

      if (this.count2 > 0) {
         count4 += (count4 - 1) / this.count2 * this.byteFlagArray.length;
      }

      return count4;
   }

   public String getText(byte[] byteValueArray) {
      byte[] byteValueArray2 = this.resolveByteArray(byteValueArray);
      return new String(byteValueArray2, 0, 0, byteValueArray2.length);
   }

   public ByteBuffer resolveByteBuffer(ByteBuffer buffer) {
      int count4 = 0;
      byte[] byteValueArray = null;
      int count5 = 0;
      byte[] byteValueArray2 = null;
      long value2 = 0x4059408ceacfabcfL;
      long value3 = 0x74573a3a928ce963L;

      while (true) {
         int count6 = (int)(value2 % 12L);
         if (count6 == 1) {
            break;
         }

         if (count6 != 0) {
            if (count6 != 4) {
               if (count6 != 3) {
                  if (count6 != 7) {
                     if (count6 != 9) {
                        if (count6 == 8) {
                           byteValueArray = Arrays.copyOf(byteValueArray, count5);
                           break;
                        }

                        if (count6 != 11) {
                           if (count6 != 5) {
                              if (count6 == 2) {
                                 count5 = 0;
                                 value2 = (value2 ^ 0x4eccbaaccb46f06bL) + 0x37848b0e15a0b1baL;
                                 value3 = (value3 ^ -0x5311680ce26d2eeL) + 0x3989120563b0e276L;
                                 continue;
                              }

                              if (count6 != 6) {
                                 ((Buffer)buffer).position(buffer.limit());
                                 value2 = (value2 ^ -0x79409dfff9daa40eL) - 0x5896992957953342L;
                                 value3 = (value3 ^ -0x532cffe59fbbeaecL) + 0x2e024afd81e8d4c6L;
                                 continue;
                              }

                              value2 = (value2 ^ 0x6a79dfe0d4671e35L) - 0x13e7bdabf99f3e7aL;
                              value3 = (value3 ^ 0x68620e9a4dc5b377L) + 0x63fdfb457582fe7aL;
                           }

                           if (count5 == byteValueArray.length) {
                              break;
                           }

                           value2 = (value2 ^ 0x3e8442e24064d9f2L) + 0x50d1ea317e3289adL;
                           value3 = (value3 ^ 0x76d2d7033869078aL) + 0x5ea2ac08cc5bd512L;
                        } else {
                           buffer.get(byteValueArray2);
                           value2 = (value2 ^ 0x4966a28b1f930e37L) + 0x605edbab6c09148L;
                           value3 = (value3 ^ -0x73c487e248ebde9aL) + 0xecd51e2de3f774L;
                        }
                     } else {
                        byteValueArray = new byte[count4];
                        value2 = (value2 ^ 0xd0c4b4e6a6391ddL) - 0x5a962fc2a4a1f40eL;
                        value3 = (value3 ^ 0x18372304d5930f87L) + 0xd1713789fc9a399L;
                     }
                  } else if (!buffer.hasArray()) {
                     long value4 = (value2 ^ 0x73ff5716bd33ed15L) + 0x4771752ed1d1a56cL;
                     long value5 = (value3 ^ 0xcc3e707473d429aL) + 0x2f5f143ea1527fbcL;
                     byteValueArray2 = new byte[buffer.remaining()];
                     value2 = (value4 ^ -0x7f6bdd8e9e2c0a94L) - 0x6d813b7c0453c4ffL;
                     value3 = (value5 ^ -0x2610959a8eb83039L) + 0x4a5c0d49c5497903L;
                  } else {
                     value2 = (value2 ^ 0x35ba32d3c8e56f02L) - 0x35c50218d3ff62a5L;
                     value3 = (value3 ^ 0x10b87f3386803902L) + 0x563150c630b7f5dbL;
                  }
               } else {
                  int count7 = buffer.remaining();
                  count4 = this.computeCount(count7);
                  value2 = (value2 ^ 0x66f68268c2767a05L) + 0x3e427fd90c1f3e27L;
                  value3 = (value3 ^ 0x70b19b47e9eefd41L) - 0x2a7e6480a95f947bL;
               }
            } else {
               byte[] byteValueArray3 = buffer.array();
               int count8 = buffer.arrayOffset() + buffer.position();
               int count9 = buffer.arrayOffset() + buffer.limit();
               int count10 = count9;
               int count11 = count8;
               byte[] byteValueArray4 = byteValueArray3;
               count5 = this.computeCount(byteValueArray4, count11, count10, byteValueArray);
               value2 = (value2 ^ -0x3d92602bb25b88feL) + 0x57aa60602b576a90L;
               value3 = (value3 ^ 0x7f8489bd53af3a04L) + 0x1e5f69fdbd77e3abL;
            }
         } else {
            int length = byteValueArray2.length;
            int count12 = length;
            byte byteValue = 0;
            count5 = this.computeCount(byteValueArray2, byteValue, count12, byteValueArray);
            value2 = (value2 ^ 0x37bc5af117e5c6feL) + 0x1ecffdbea78b655fL;
            value3 = (value3 ^ -0x13a42af2d95ad227L) + 0x681bf257313afceaL;
         }
      }

      return ByteBuffer.wrap(byteValueArray);
   }

   public byte[] resolveByteArray(byte[] byteValueArray) {
      int count4 = 0;
      byte[] entry = null;
      int count5 = 0;
      int length = byteValueArray.length;
      count4 = this.computeCount(length);
      entry = new byte[count4];
      int length2 = byteValueArray.length;
      int count6 = length2;
      byte byteValue = 0;
      count5 = this.computeCount(byteValueArray, byteValue, count6, (byte[])entry);
      return (byte[])(count5 == entry.length ? entry : Arrays.copyOf((byte[])entry, count5));
   }

   public int computeCount(byte[] byteValueArray, byte[] byteValueArray2) {
      int count4 = 0;
      int length = byteValueArray.length;
      count4 = this.computeCount(length);
      if (byteValueArray2.length >= count4) {
         int length2 = byteValueArray.length;
         int count5 = length2;
         byte byteValue = 0;
         return this.computeCount(byteValueArray, byteValue, count5, byteValueArray2);
      } else {
         throw new IllegalArgumentException("Output byte array is too small for encoding all input bytes");
      }
   }

   public static char[] getCharCodeArray() {
      return charCodeArray;
   }

   public static char[] getCharCodeArray2() {
      return charCodeArray2;
   }

   public AutoRegisterCrypto(boolean flag, byte[] byteValueArray, int count3, boolean flag2, InterpolationAggregator interpolationAggregator) {
      this(flag, byteValueArray, count3, flag2);
   }

   private AutoRegisterCrypto(boolean flag, byte[] byteValueArray, int count3, boolean flag2) {
      this.enabled2 = flag;
      this.byteFlagArray = byteValueArray;
      this.count2 = count3;
      this.enabled = flag2;
   }
}
