package client.util;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class AutoRegisterPayload {
   public static final int count2 = 512;
   public static final int[] countArray4 = new int[8];
   public static final int[] countArray = new int[64];
   public static final int[] countArray5 = new int[8];
   public static final int[] countArray2 = new int[]{1779033703, -1150833019, 1013904242, -1521486534, 1359893119, -1694144372, 528734635, 1541459225};
   public static final int count = 64;
   public static final int[] countArray3 = new int[]{
      1116352408,
      1899447441,
      -1245643825,
      -373957723,
      961987163,
      1508970993,
      -1841331548,
      -1424204075,
      -670586216,
      310598401,
      607225278,
      1426881987,
      1925078388,
      -2132889090,
      -1680079193,
      -1046744716,
      -459576895,
      -272742522,
      264347078,
      604807628,
      770255983,
      1249150122,
      1555081692,
      1996064986,
      -1740746414,
      -1473132947,
      -1341970488,
      -1084653625,
      -958395405,
      -710438585,
      113926993,
      338241895,
      666307205,
      773529912,
      1294757372,
      1396182291,
      1695183700,
      1986661051,
      -2117940946,
      -1838011259,
      -1564481375,
      -1474664885,
      -1035236496,
      -949202525,
      -778901479,
      -694614492,
      -200395387,
      275423344,
      430227734,
      506948616,
      659060556,
      883997877,
      958139571,
      1322822218,
      1537002063,
      1747873779,
      1955562222,
      2024104815,
      -2067236844,
      -1933114872,
      -1866530822,
      -1538233109,
      -1090935817,
      -965641998
   };

   private static int computeCount3(int count3) {
      return Integer.rotateRight(count3, 2) ^ Integer.rotateRight(count3, 13) ^ Integer.rotateRight(count3, 22);
   }

   private static int computeCount(int count3) {
      return Integer.rotateRight(count3, 17) ^ Integer.rotateRight(count3, 19) ^ count3 >>> 10;
   }

   public static byte[] getByteArray(byte[] byteValueArray) {
      int[] countArray6 = null;
      int count3 = 0;
      int count4 = 0;
      int count5 = 0;
      int count6 = 0;
      int count7 = 0;
      long value = 0x44b304435692b617L;
      long value2 = -0x2ca0e6f0a8034babL;

      while (true) {
         int count8 = (int)(value % 21L);
         if (count8 == 9) {
            countArray4[4] = countArray4[4] + count6;
            value = (value ^ 0x65c62d0a47075f33L) - 0x16dbf02d648343b2L;
            value2 = (value2 ^ 0x77a856d0fc54a01cL) + 0x7073dd47e1463a2bL;
         } else if (count8 != 8) {
            if (count8 != 12) {
               if (count8 != 5) {
                  checkBlock: {
                     guardBlock: {
                        searchBlock: {
                           if (count8 != 3) {
                              if (count8 == 18) {
                                 break guardBlock;
                              }

                              if (count8 == 14) {
                                 break searchBlock;
                              }

                              if (count8 == 0) {
                                 count5++;
                                 value = (value ^ 0x7708acaffd489e2L) - 0x1bd284f1bf88eafeL;
                                 value2 = (value2 ^ -0x60052e9b7965aa64L) - 0x56e790c82d6c9c14L;
                                 break searchBlock;
                              }

                              if (count8 == 10) {
                                 count5 = 16;
                                 value = (value ^ 0x6d9715dbc45b749eL) + 0x1b2a147db237924bL;
                                 value2 = (value2 ^ -0x35fe42367e8b7747L) + 0x62a31de574016080L;
                                 continue;
                              }

                              if (count8 == 16) {
                                 count5++;
                                 value = (value ^ 0x3cca51f735e92413L) + 0x21393401cad3fb8cL;
                                 value2 = (value2 ^ 0x28624fb68a03d688L) - 0x65f7c734382c5041L;
                                 break checkBlock;
                              }

                              if (count8 == 6) {
                                 break checkBlock;
                              }

                              if (count8 != 7) {
                                 if (count8 != 13) {
                                    if (count8 != 17) {
                                       if (count8 != 11) {
                                          if (count8 != 20) {
                                             if (count8 != 19) {
                                                if (count8 != 1) {
                                                   if (count8 != 2) {
                                                      if (count8 != 15) {
                                                         System.arraycopy(countArray4, 0, countArray4, 1, countArray4.length - 1);
                                                         value = (value ^ -0xddc8a075350b8aeL) - 0x75ca18556556f8b3L;
                                                         value2 = (value2 ^ 0x3411dcad13de572fL) + 0x575e612df244e1b3L;
                                                      } else {
                                                         System.arraycopy(countArray6, count3 * 16, countArray, 0, 16);
                                                         value = (value ^ -0x42d88d146e594453L) - 0x6442071d307cde84L;
                                                         value2 = (value2 ^ 0x437e9d5401aa7188L) + 0x2b73f5e4ded8b68L;
                                                      }
                                                   } else {
                                                      countArray6 = resolveIndexArray(byteValueArray);
                                                      value = (value ^ 0x5d92facd9b620a81L) - 0x5099dca0b706195bL;
                                                      value2 = (value2 ^ 0x9cc1318b2574188L) + 0x280f298acc3d62cdL;
                                                   }
                                                } else {
                                                   countArray[count5] = computeCount(countArray[count5 - 2])
                                                      + countArray[count5 - 7]
                                                      + computeCountByIndex(countArray[count5 - 15])
                                                      + countArray[count5 - 16];
                                                   value = (value ^ -0x1850efd533a5088aL) - 0x61a889297d9e03a8L;
                                                   value2 = (value2 ^ 0x51ec6c9f05c96aaeL) - 0x28ee6fedd93c8f84L;
                                                }
                                             } else {
                                                countArray4[0] = count6 + count7;
                                                value = (value ^ 0x750313e36a18130bL) - 0xbf2aa5f920cef3aL;
                                                value2 = (value2 ^ 0x364e316ade976807L) + 0x6fd4329b36c1abcaL;
                                             }
                                          } else {
                                             countArray5[count5] = countArray5[count5] + countArray4[count5];
                                             value = (value ^ -0x1a00e9daf8dc41a8L) + 0x4b3a06384e184ef8L;
                                             value2 = (value2 ^ -0x72f0498e997bd989L) + 0x605f643b980146b6L;
                                          }
                                       } else {
                                          count7 = computeCount3(countArray4[0]) + computeCount(countArray4[0], countArray4[1], countArray4[2]);
                                          value = (value ^ 0xc1f3cc0cf16dfcL) + 0x179e048741cbdd3cL;
                                          value2 = (value2 ^ -0x7940c638164af699L) - 0x18085ce62e2fb11cL;
                                       }
                                    } else {
                                       count4 = countArray6.length / 16;
                                       value = (value ^ 0x131f2227ff2c40e5L) + 0x37e5597a129d961aL;
                                       value2 = (value2 ^ -0x5ed99808bdbf1ba9L) + 0x33a0afb478c5e2fcL;
                                    }
                                 } else {
                                    count6 = countArray4[7]
                                       + computeCount2(countArray4[4])
                                       + computeCountByIndex(countArray4[4], countArray4[5], countArray4[6])
                                       + countArray3[count5]
                                       + countArray[count5];
                                    value = (value ^ 0x1ae35240c592abd0L) + 0x38645b05581ebe5bL;
                                    value2 = (value2 ^ 0x5e59a445cb5394eaL) - 0x74829bc0542bbcaaL;
                                 }
                                 continue;
                              }
                           } else {
                              count5++;
                              value = (value ^ 0x44298d9b71be4b11L) - 0x5c8d6d908dd8a2cbL;
                              value2 = (value2 ^ 0x2769743f42853c8bL) - 0x139f3f0c8bcf4394L;
                           }

                           if (count5 < countArray5.length) {
                              value = (value ^ 0x52fb1d92ae5ee090L) - 0x142815eb186dfb64L;
                              value2 = (value2 ^ 0x156f28397cb4791dL) + 0x66098c61b45a9851L;
                              continue;
                           }

                           long value3 = (value ^ -0x441449133f08f9faL) + 0x6b908dde1f5fefdaL;
                           long value4 = (value2 ^ -0x58555cea41fc0530L) - 0x3463f3e5cfad7e21L;
                           count3++;
                           value = (value3 ^ 0x215ef9c23eb912f1L) + 0x41eccef46f5fdcbdL;
                           value2 = (value4 ^ 0x705716ea12717db2L) + 0x47af27874ed8b1d8L;
                           break guardBlock;
                        }

                        if (count5 >= countArray.length) {
                           long value5 = (value ^ -0x5afa006f9110ef87L) + 0x7560d01ad15d5297L;
                           long value6 = (value2 ^ 0x40e2c46a89b0de11L) - 0x71f17cbdd248d689L;
                           System.arraycopy(countArray5, 0, countArray4, 0, countArray5.length);
                           value = (value5 ^ -0x363272aa23f427edL) - 0x67b891e4025ec27cL;
                           value2 = (value6 ^ 0x628d25a62b922c48L) + 0x15c028720455864eL;
                        } else {
                           value = (value ^ 0x779fc063385cb756L) + 0x3c454db39655a08L;
                           value2 = (value2 ^ 0x23595c53a0b608d2L) + 0x6ccc67231a40150L;
                        }
                        continue;
                     }

                     if (count3 >= count4) {
                        return resolveByteArray(countArray5);
                     }

                     value = (value ^ -0x4a19927b659d6376L) + 0xd5b9ec8f05ebe8fL;
                     value2 = (value2 ^ 0x23ec6220bc3ca557L) - 0xda66396af2404c5L;
                     continue;
                  }

                  if (count5 >= countArray.length) {
                     long value7 = (value ^ -0x2866a7709f7ad79cL) - 0x796abad5d7a7f79fL;
                     long value8 = (value2 ^ -0x6e5e149ed7eb77feL) + 0x1cb5bd21ea1709bbL;
                     count5 = 0;
                     value = (value7 ^ 0x3a4ceedb5d0b6496L) - 0x2335dea1a0e1df45L;
                     value2 = (value8 ^ -0x71906af822d95600L) + 0x4ca91b69eb555c44L;
                  } else {
                     value = (value ^ 0x5730ded9366e2192L) + 0x294a2ba61a714d8dL;
                     value2 = (value2 ^ -0x60638dfe963e9e6dL) + 0x6a99c9f140dcbf73L;
                  }
               } else {
                  count5 = 0;
                  value = (value ^ 0x65ad269cc15578b5L) - 0x1d2ccdfa149f2707L;
                  value2 = (value2 ^ -0x78129c13052ed5c0L) + 0x11078770f2edee9L;
               }
            } else {
               count3 = 0;
               value = (value ^ -0x215b22e75680626cL) + 0x4f5bb209130e17ceL;
               value2 = (value2 ^ -0x5141ef1990c2262fL) + 0xd10d5b8b6821540L;
            }
         } else {
            System.arraycopy(countArray2, 0, countArray5, 0, countArray2.length);
            value = (value ^ -0x5a13ba48cd6b895L) + 0x7ad2d1e8afc82888L;
            value2 = (value2 ^ -0x74e3e335c70b2209L) + 0x200973885b72b9d4L;
         }
      }
   }

   private static int computeCount(int count3, int count4, int count5) {
      return count3 & count4 | count3 & count5 | count4 & count5;
   }

   private static byte[] resolveByteArray(int[] countArray6) {
      ByteBuffer buffer = null;
      buffer = ByteBuffer.allocate(countArray6.length * 4);

      for (int count3 : countArray6) {
         buffer.putInt(count3);
      }

      return buffer.array();
   }

   private static int computeCountByIndex(int count3, int count4, int count5) {
      return count3 & count4 | ~count3 & count5;
   }

   public static int[] resolveIndexArray(byte[] byteValueArray) {
      int count3 = byteValueArray.length % 64;
      IntBuffer buffer = IntBuffer.allocate((byteValueArray.length / 64 + (count3 + 1 + 8 > 64 ? 2 : 1)) * 16);
      ByteBuffer buffer2 = ByteBuffer.wrap(byteValueArray);
      int count4 = 0;

      for (int index = byteValueArray.length / 4; count4 < index; count4++) {
         buffer.put(buffer2.getInt());
      }

      ByteBuffer buffer3 = ByteBuffer.allocate(4);
      ((Buffer)buffer3.put(buffer2).put((byte)-128)).rewind();
      buffer.put(buffer3.getInt());
      ((Buffer)buffer).position(buffer.capacity() - 2);
      long value = (long)byteValueArray.length * 8L;
      buffer.put((int)(value >>> 32));
      buffer.put((int)value);
      return buffer.array();
   }

   private static int computeCount2(int count3) {
      return Integer.rotateRight(count3, 6) ^ Integer.rotateRight(count3, 11) ^ Integer.rotateRight(count3, 25);
   }

   private static int computeCountByIndex(int count3) {
      return Integer.rotateRight(count3, 7) ^ Integer.rotateRight(count3, 18) ^ count3 >>> 3;
   }
}
