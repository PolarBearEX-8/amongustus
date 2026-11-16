package client.util;

import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

public class OutputArrayController {
   public static final byte[] byteFlagArray2 = new byte[]{13, 10};
   public static final int count2 = 76;
   public static final OutputArrayController outputArrayController2 = new OutputArrayController(false, byteFlagArray2, 76, true);
   public static final OutputArrayController outputArrayController = new OutputArrayController(false, null, -1, true);
   public static final OutputArrayController outputArrayController3 = new OutputArrayController(true, null, -1, true);
   public final int count;
   public final boolean enabled;
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
      '-',
      '_'
   };
   public final boolean enabled2;
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
      '+',
      '/'
   };
   public final byte[] byteFlagArray;

   public String resolveText(byte[] byteValueArray) {
      byte[] byteValueArray2 = this.resolveByteArray(byteValueArray);
      return new String(byteValueArray2, 0, 0, byteValueArray2.length);
   }

   public OutputArrayController getOutputArrayController() {
      AuxiliaryCharArrayEntry.getStringArray();
      return this.enabled ? new OutputArrayController(this.enabled2, this.byteFlagArray, this.count, false) : this;
   }

   public byte[] resolveByteArray(byte[] byteValueArray) {
      int count3 = 0;
      AuxiliaryCharArrayEntry.getStringArray();
      byte[] entry = null;
      int count4 = 0;
      int length = byteValueArray.length;
      count3 = this.computeCount(length);
      entry = new byte[count3];
      int length2 = byteValueArray.length;
      byte byteValue = 0;
      count4 = this.computeCount(byteValueArray, byteValue, length2, (byte[])entry);
      return (byte[])(count4 == entry.length ? entry : Arrays.copyOf((byte[])entry, count4));
   }

   public OutputArrayController(boolean flag, byte[] byteValueArray, int count3, boolean flag2, Controller27 controller) {
      this(flag, byteValueArray, count3, flag2);
   }

   private final int computeCount(int count3) {
      int count4 = 0;
      AuxiliaryCharArrayEntry.getStringArray();
      int count5 = 0;
      long value = 0x1db2d5dab9b5e7e0L;
      long value2 = -0x68f66dd3f2561b41L;

      while (true) {
         int count6 = (int)(value % 5L);
         if (count6 != 2) {
            if (count6 == 3) {
               count4 = 4 * (count3 / 3) + (count5 != 0 ? count5 + 1 : 0);
               break;
            }

            if (count6 != 1) {
               if (count6 != 4) {
                  break;
               }

               count4 = 0;
               value = (value ^ -0x2e277ad985782bccL) - 0x625d1b8d67676d9aL;
               value2 = (value2 ^ 0x3d9c15d76996fb2fL) + 0x6d7f1ef43ccd0385L;
            } else {
               count4 = 4 * ((count3 + 2) / 3);
               value = (value ^ 0x7fd7cc3494c64044L) + 0x89a9326c485ae51L;
               value2 = (value2 ^ -0x4cadc057048d4b9aL) - 0x538c31e7103cc698L;
            }
         } else if (!this.enabled) {
            long value3 = (value ^ 0x7b7a7c6b5faf24deL) + 0x211adf8089cf3bb4L;
            long value4 = (value2 ^ 0x634c36a55293fc83L) + 0x11ec383930a19106L;
            count5 = count3 % 3;
            value = (value3 ^ 0x4e66be0f4318b94cL) - 0x27c0f6e993147dceL;
            value2 = (value4 ^ 0x631592908f33064eL) + 0x5864ac66d347c770L;
         } else {
            value = (value ^ 0x34e497ecf107c565L) - 0x8c9491a4d63d35cL;
            value2 = (value2 ^ 0x6a7989ca63ad2cddL) - 0x1eea6961f3948c76L;
         }
      }

      if (this.count > 0) {
         count4 += (count4 - 1) / this.count * this.byteFlagArray.length;
      }

      return count4;
   }

   private OutputArrayController(boolean flag, byte[] byteValueArray, int count3, boolean flag2) {
      this.enabled2 = flag;
      this.byteFlagArray = byteValueArray;
      this.count = count3;
      this.enabled = flag2;
   }

   public int computeCount(byte[] byteValueArray, byte[] byteValueArray2) {
      AuxiliaryCharArrayEntry.getStringArray();
      int count3 = 0;
      int length = byteValueArray.length;
      count3 = this.computeCount(length);
      if (byteValueArray2.length >= count3) {
         int length2 = byteValueArray.length;
         byte byteValue = 0;
         return this.computeCount(byteValueArray, byteValue, length2, byteValueArray2);
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

   public OutputStream resolveOutputStream(OutputStream outputStream) {
      Objects.requireNonNull(outputStream);
      return new StreamClosedFilterOutputStream(outputStream, this.enabled2 ? charCodeArray : charCodeArray2, this.byteFlagArray, this.count, this.enabled);
   }

   private int computeCount(byte[] byteValueArray, int count3, int count4, byte[] byteValueArray2) {
      AuxiliaryCharArrayEntry.getStringArray();
      char[] characterArray = this.enabled2 ? charCodeArray : charCodeArray2;
      int count5 = count3;
      int count6 = (count4 - count3) / 3 * 3;
      int count7 = count3 + count6;
      if (this.count > 0 && count6 > this.count / 4 * 3) {
         count6 = this.count / 4 * 3;
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
         if (count13 == this.count && count9 < count4) {
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

   public ByteBuffer resolveByteBuffer(ByteBuffer buffer) {
      int count3 = 0;
      byte[] byteValueArray = null;
      AuxiliaryCharArrayEntry.getStringArray();
      int count4 = 0;
      byte[] byteValueArray2 = null;
      long value2 = 0x33c2dc27b29601f3L;
      long value3 = 0x275dab8755b7543aL;

      while (true) {
         int count5 = (int)(value2 % 12L);
         if (count5 != 10) {
            if (count5 != 0) {
               if (count5 != 5) {
                  if (count5 == 3) {
                     break;
                  }

                  if (count5 != 2) {
                     if (count5 != 11) {
                        if (count5 != 1) {
                           if (count5 != 7) {
                              if (count5 != 4) {
                                 if (count5 != 9) {
                                    if (count5 != 8) {
                                       byte[] byteValueArray3 = buffer.array();
                                       int count6 = buffer.arrayOffset() + buffer.limit();
                                       int count7 = buffer.arrayOffset() + buffer.position();
                                       byte[] byteValueArray4 = byteValueArray3;
                                       count4 = this.computeCount(byteValueArray4, count7, count6, byteValueArray);
                                       value2 = (value2 ^ -0x213d15bf81543c89L) + 0x378d794d186ba809L;
                                       value3 = (value3 ^ 0x38b47b4497f4bf49L) + 0x3afcc8b8d043c853L;
                                       continue;
                                    }

                                    byteValueArray = Arrays.copyOf(byteValueArray, count4);
                                    break;
                                 }

                                 if (!buffer.hasArray()) {
                                    long value4 = (value2 ^ -0x47a869f7617ef5f7L) + 0x1e0dc79e87a5db68L;
                                    long value5 = (value3 ^ 0x954a030418c12a8L) - 0x4a37d8041354f75cL;
                                    byteValueArray2 = new byte[buffer.remaining()];
                                    value2 = (value4 ^ 0x1029c51779e9363fL) + 0x64c5a448f225a9e6L;
                                    value3 = (value5 ^ 0x2c1122bedc219b0dL) + 0x9727e1ca3c045e3L;
                                 } else {
                                    value2 = (value2 ^ -0x56588ca2b0bbfe25L) + 0x2203b9ca2c62a5f0L;
                                    value3 = (value3 ^ 0x725650fc910e51a5L) - 0x2faab8904b75054fL;
                                 }
                                 continue;
                              }

                              count4 = 0;
                              value2 = (value2 ^ 0x7ed2d96813d526b0L) + 0xb6a0e564edcd529L;
                              value3 = (value3 ^ 0x22e98e95342ee203L) - 0x3e9c7a3e799f9138L;
                              continue;
                           }

                           byteValueArray = new byte[count3];
                           value2 = (value2 ^ 0x69ea32ec742427b2L) + 0xaffe5d268e3a843L;
                           value3 = (value3 ^ 0x1679048e0618aefaL) - 0x3efd6d15923afd30L;
                           continue;
                        }

                        buffer.get(byteValueArray2);
                        value2 = (value2 ^ -0x1d71dd0892ba583eL) - 0x1ea470c4369cf3b9L;
                        value3 = (value3 ^ -0x69e3038eb54e9e10L) + 0x16a7cf4e33543da8L;
                        continue;
                     }

                     int count8 = buffer.remaining();
                     count3 = this.computeCount(count8);
                     value2 = (value2 ^ 0x23b035bf0da0a654L) + 0x480e1db4b811d3c0L;
                     value3 = (value3 ^ -0x3dbd2357b3d2b19aL) - 0x3b037fdfdbcf1672L;
                     continue;
                  }

                  int length = byteValueArray2.length;
                  byte byteValue = 0;
                  count4 = this.computeCount(byteValueArray2, byteValue, length, byteValueArray);
                  value2 = (value2 ^ -0xc6421deb1003b3aL) - 0x481c4ba17ef34fb3L;
                  value3 = (value3 ^ 0x4acda7b8cabcf013L) + 0x293d825cbc01ef1cL;
                  continue;
               }
            } else {
               value2 = (value2 ^ 0x6f76a298a84c1b9cL) - 0x254ff55568f15067L;
               value3 = (value3 ^ 0x41e87b6b4a1bc80aL) + 0x5644ceaa04f910a5L;
            }

            if (count4 == byteValueArray.length) {
               break;
            }

            value2 = (value2 ^ -0x564442be97999e00L) + 0x79b9ae55e9794fffL;
            value3 = (value3 ^ -0x32170f76166bc0c4L) - 0x454101be475e1c1dL;
         } else {
            ((Buffer)buffer).position(buffer.limit());
            value2 = (value2 ^ 0x898581a76f9d757L) + 0xe41a5fe3b0ffb7fL;
            value3 = (value3 ^ -0x2eec9fa393ddefe2L) + 0x57d8106eaf8c8550L;
         }
      }

      return ByteBuffer.wrap(byteValueArray);
   }
}
