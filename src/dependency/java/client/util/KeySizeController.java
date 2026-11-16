package client.util;

public class KeySizeController {
   public static final int count = 16;
   public final int[] countArray = new int[16];
   public static final int[] countArray2 = new int[]{
      82,
      9,
      106,
      213,
      48,
      54,
      165,
      56,
      191,
      64,
      163,
      158,
      129,
      243,
      215,
      251,
      124,
      227,
      57,
      130,
      155,
      47,
      255,
      135,
      52,
      142,
      67,
      68,
      196,
      222,
      233,
      203,
      84,
      123,
      148,
      50,
      166,
      194,
      35,
      61,
      238,
      76,
      149,
      11,
      66,
      250,
      195,
      78,
      8,
      46,
      161,
      102,
      40,
      217,
      36,
      178,
      118,
      91,
      162,
      73,
      109,
      139,
      209,
      37,
      114,
      248,
      246,
      100,
      134,
      104,
      152,
      22,
      212,
      164,
      92,
      204,
      93,
      101,
      182,
      146,
      108,
      112,
      72,
      80,
      253,
      237,
      185,
      218,
      94,
      21,
      70,
      87,
      167,
      141,
      157,
      132,
      144,
      216,
      171,
      0,
      140,
      188,
      211,
      10,
      247,
      228,
      88,
      5,
      184,
      179,
      69,
      6,
      208,
      44,
      30,
      143,
      202,
      63,
      15,
      2,
      193,
      175,
      189,
      3,
      1,
      19,
      138,
      107,
      58,
      145,
      17,
      65,
      79,
      103,
      220,
      234,
      151,
      242,
      207,
      206,
      240,
      180,
      230,
      115,
      150,
      172,
      116,
      34,
      231,
      173,
      53,
      133,
      226,
      249,
      55,
      232,
      28,
      117,
      223,
      110,
      71,
      241,
      26,
      113,
      29,
      41,
      197,
      137,
      111,
      183,
      98,
      14,
      170,
      24,
      190,
      27,
      252,
      86,
      62,
      75,
      198,
      210,
      121,
      32,
      154,
      219,
      192,
      254,
      120,
      205,
      90,
      244,
      31,
      221,
      168,
      51,
      136,
      7,
      199,
      49,
      177,
      18,
      16,
      89,
      39,
      128,
      236,
      95,
      96,
      81,
      127,
      169,
      25,
      181,
      74,
      13,
      45,
      229,
      122,
      159,
      147,
      201,
      156,
      239,
      160,
      224,
      59,
      77,
      174,
      42,
      245,
      176,
      200,
      235,
      187,
      60,
      131,
      83,
      153,
      97,
      23,
      43,
      4,
      126,
      186,
      119,
      214,
      38,
      225,
      105,
      20,
      99,
      85,
      33,
      12,
      125
   };
   public static final int[] countArray3 = new int[]{0, 1, 2, 4, 8, 16, 32, 64, 128, 27, 54};
   public final int[] countArray4 = new int[16];
   public static final int count2 = 12;
   public static final int count3 = 14;
   public static final int count4 = 16;
   public int[] countArray5;
   public byte[] byteFlagArray;
   public static final int count5 = 10;
   public final byte[] byteFlagArray2;
   public RecordNode8 recordNode8;
   public byte[] byteFlagArray3;
   public final byte[] byteFlagArray4 = new byte[16];
   public static final int[] countArray6 = new int[]{
      99,
      124,
      119,
      123,
      242,
      107,
      111,
      197,
      48,
      1,
      103,
      43,
      254,
      215,
      171,
      118,
      202,
      130,
      201,
      125,
      250,
      89,
      71,
      240,
      173,
      212,
      162,
      175,
      156,
      164,
      114,
      192,
      183,
      253,
      147,
      38,
      54,
      63,
      247,
      204,
      52,
      165,
      229,
      241,
      113,
      216,
      49,
      21,
      4,
      199,
      35,
      195,
      24,
      150,
      5,
      154,
      7,
      18,
      128,
      226,
      235,
      39,
      178,
      117,
      9,
      131,
      44,
      26,
      27,
      110,
      90,
      160,
      82,
      59,
      214,
      179,
      41,
      227,
      47,
      132,
      83,
      209,
      0,
      237,
      32,
      252,
      177,
      91,
      106,
      203,
      190,
      57,
      74,
      76,
      88,
      207,
      208,
      239,
      170,
      251,
      67,
      77,
      51,
      133,
      69,
      249,
      2,
      127,
      80,
      60,
      159,
      168,
      81,
      163,
      64,
      143,
      146,
      157,
      56,
      245,
      188,
      182,
      218,
      33,
      16,
      255,
      243,
      210,
      205,
      12,
      19,
      236,
      95,
      151,
      68,
      23,
      196,
      167,
      126,
      61,
      100,
      93,
      25,
      115,
      96,
      129,
      79,
      220,
      34,
      42,
      144,
      136,
      70,
      238,
      184,
      20,
      222,
      94,
      11,
      219,
      224,
      50,
      58,
      10,
      73,
      6,
      36,
      92,
      194,
      211,
      172,
      98,
      145,
      149,
      228,
      121,
      231,
      200,
      55,
      109,
      141,
      213,
      78,
      169,
      108,
      86,
      244,
      234,
      101,
      122,
      174,
      8,
      186,
      120,
      37,
      46,
      28,
      166,
      180,
      198,
      232,
      221,
      116,
      31,
      75,
      189,
      139,
      138,
      112,
      62,
      181,
      102,
      72,
      3,
      246,
      14,
      97,
      53,
      87,
      185,
      134,
      193,
      29,
      158,
      225,
      248,
      152,
      17,
      105,
      217,
      142,
      148,
      155,
      30,
      135,
      233,
      206,
      85,
      40,
      223,
      140,
      161,
      137,
      13,
      191,
      230,
      66,
      104,
      65,
      153,
      45,
      15,
      176,
      84,
      187,
      22
   };
   public static final int[][] countArray7 = new int[16][256];

   private void applyByteArray(byte[] byteValueArray) {
      long value = 0L;
      long value2 = 0L;
      int count6 = 0;
      int count7 = 0;
      long value3 = 0L;
      long value4 = 0x573f0af42d267d8aL;
      long value5 = 0x1a0be38d46a51a37L;

      while (true) {
         int count8 = (int)(value4 % 14L);
         if (count8 != 9) {
            searchBlock: {
               if (count8 != 6) {
                  if (count8 == 11) {
                     value2 ^= value3 & 4294967295L;
                     value4 = (value4 ^ -0x6c070ecdf0d53a79L) - 0x6699be3109af2187L;
                     value5 = (value5 ^ 0x1093daf2c2817c91L) - 0x382f43315c7af59dL;
                     continue;
                  }

                  if (count8 == 8) {
                     value ^= value3 >>> 32;
                     value4 = (value4 ^ -0x5c76ee1d3e099a9fL) + 0x2fc346f6ba7297a0L;
                     value5 = (value5 ^ 0x10aa1753c5b1ca70L) + 0x2a6c354b973eb0b2L;
                     continue;
                  }

                  if (count8 == 3) {
                     break searchBlock;
                  }

                  if (count8 == 0) {
                     count6 = 0;
                     value4 = (value4 ^ -0x201b72ca33aa05aeL) - 0x7b92abd51295641bL;
                     value5 = (value5 ^ 0x79b867b15aa8558eL) + 0x5e9846195bfab148L;
                     continue;
                  }

                  if (count8 == 4) {
                     byteValueArray[count6 + 8] = (byte)((int)(value2 >>> 56 - count6 * 8 & 255L));
                     value4 = (value4 ^ -0x6d95b4e3fee6e99cL) + 0x5aab17a565016cb8L;
                     value5 = (value5 ^ -0x4465b526e421f3a7L) + 0x344c67e54acf1bc5L;
                     continue;
                  }

                  if (count8 != 7) {
                     if (count8 == 2) {
                        value3 = this.recordNode8.timestampArray[count6][count7];
                        value4 = (value4 ^ 0x7cb3943dd84d996L) - 0x2741e8cb43a08ffaL;
                        value5 = (value5 ^ 0x1e1d2338a9c39660L) + 0x7fd3be75c7e880bcL;
                        continue;
                     }

                     if (count8 != 5) {
                        if (count8 != 1) {
                           if (count8 != 12) {
                              if (count8 != 13) {
                                 value = 0L;
                                 value4 = (value4 ^ -0x76cf57a5ffedeb62L) + 0x6711221c5531e428L;
                                 value5 = (value5 ^ -0x788134a86ccdecffL) - 0x3e506bc77bb5c8adL;
                              } else if (count7 == 0) {
                                 value4 = (value4 ^ 0x50c59fcd06f84202L) - 0x3a28826e8d40d8f2L;
                                 value5 = (value5 ^ 0x2bc3f1cee68d0eceL) + 0x670629323ee5a94fL;
                              } else {
                                 value4 = (value4 ^ 0x7deb90ef166f444aL) + 0x2783ae6d6eed50e3L;
                                 value5 = (value5 ^ 0x3c5011f87b769642L) - 0x1cb2578a097ea315L;
                              }
                           } else {
                              value2 = 0L;
                              value4 = (value4 ^ 0x22c84801c68e0084L) - 0x6f36238ab4d57e0L;
                              value5 = (value5 ^ 0x33ed23de6cfff62L) + 0x202248c11553fbc3L;
                           }
                        } else {
                           byteValueArray[count6] = (byte)((int)(value >>> 56 - count6 * 8 & 255L));
                           value4 = (value4 ^ -0x7da6aaf7dd63d3ecL) + 0x481f258cb33c1731L;
                           value5 = (value5 ^ -0x61b55888769df5bbL) - 0x56da679ac17b814fL;
                        }
                        continue;
                     }

                     count6++;
                     value4 = (value4 ^ -0x6fd1825dfbd8cba3L) - 0x735e867378f780abL;
                     value5 = (value5 ^ 0x47324f58e00f63cL) - 0x17ddd90207af3dffL;
                     break searchBlock;
                  }
               } else {
                  count6++;
                  value4 = (value4 ^ -0x11e88c494905ec78L) + 0x6656e3db0fcdf983L;
                  value5 = (value5 ^ -0x684f36ff3ac77c89L) - 0x4ef4d9072c082439L;
               }

               if (count6 >= 8) {
                  return;
               }

               value4 = (value4 ^ -0x65068b771408e8b8L) - 0x65c647fcd3e96ca4L;
               value5 = (value5 ^ -0x18df0fc5729ec16fL) + 0x32cdbd66e1792db9L;
               continue;
            }

            if (count6 >= 16) {
               long value6 = (value4 ^ -0x2d6fa8d048ec7201L) - 0x6afaad163c140e49L;
               long value7 = (value5 ^ 0x683d04c48c8a39e7L) + 0x7369b8d9192484ccL;
               count6 = 0;
               value4 = (value6 ^ 0x7f16062c1331d394L) + 0x3a235e9645cbb54L;
               value5 = (value7 ^ -0x1b358bd98b124ff5L) - 0x6dfca86eb9762978L;
            } else {
               value4 = (value4 ^ 0x1cfd2acf0d17815fL) - 0x3263f5099e3238ffL;
               value5 = (value5 ^ -0x28992b92d75e7d65L) + 0x2c6f58722fbd3fdcL;
            }
         } else {
            count7 = byteValueArray[count6] & 255;
            value4 = (value4 ^ 0x5b96e9fdeb3d0154L) - 0x44b0185301183d2aL;
            value5 = (value5 ^ -0x3d9ee3b6d3e8ac01L) + 0x15b64ccec80d7e92L;
         }
      }
   }

   private static String resolveText(byte[] byteValueArray, int count6, int count7) {
      StringBuilder builder = null;
      builder = new StringBuilder();

      for (int index = count6; index < count6 + count7; index++) {
         builder.append(String.format("%02X", byteValueArray[index]));
      }

      return builder.toString();
   }

   static {

      for (int index = 0; index < 256; index++) {
         countArray7[2][index] = (index & 128) == 0 ? index << 1 & 0xFF : (index << 1 ^ 27) & 0xFF;
         countArray7[3][index] = countArray7[2][index] ^ index;
         countArray7[9][index] = countArray7[2][countArray7[2][countArray7[2][index]]] ^ index;
         countArray7[11][index] = countArray7[2][countArray7[2][index] ^ index] ^ index;
         countArray7[13][index] = countArray7[2][countArray7[2][index ^ index]] ^ index;
         countArray7[14][index] = countArray7[2][countArray7[2][index ^ index] ^ index];
      }
   }

   public KeySizeController() {
      this.byteFlagArray2 = new byte[16];
      this.byteFlagArray3 = null;
      this.countArray5 = null;
      this.byteFlagArray = null;
   }

   public byte[] resolveByteArray(byte[] byteValueArray, byte[] byteValueArray2, byte[] byteValueArray3) {
      byte[] byteValueArray4 = null;
      long value = 0L;
      long value2 = 0L;
      long value3 = 0L;
      long value4 = 0L;
      int count6 = 0;
      byte[] byteValueArray5 = null;
      byte[] byteValueArray6 = null;
      byte[] byteValueArray7 = null;
      int[] countArray8 = null;
      int byteValue = 0;
      int count7 = 0;
      int count8 = 0;
      int[] countArray9 = null;
      byte[] byteValueArray8 = null;
      byte[] byteValueArray9 = null;
      long value5 = 0x2783c448b4d7d4bfL;
      long value6 = 0x373a3d0472394e5fL;

      while (true) {
         int count9 = (int)(value5 % 39L);
         if (count9 != 30) {
            scanBlock: {
               evalBlock: {
                  checkBlock: {
                     guardBlock: {
                        long value7;
                        long value8;
                        searchBlock: {
                           if (count9 != 22) {
                              if (count9 == 32) {
                                 count8 = 0;
                                 value5 = (value5 ^ 0x619e4db70377a30aL) + 0x18bcbf2d6acfee1aL;
                                 value6 = (value6 ^ 0x2682b1cb3ea19c49L) - 0x719caacb79c79283L;
                                 continue;
                              }

                              if (count9 != 16) {
                                 if (count9 == 17) {
                                    System.arraycopy(byteValueArray4, 0, byteValueArray6, 0, byteValueArray4.length);
                                    value5 = (value5 ^ -0x5769eb7d7698ae4bL) + 0x6df4d01af781392bL;
                                    value6 = (value6 ^ 0x128b89105791c5adL) + 0x484524f6f4671374L;
                                    continue;
                                 }

                                 if (count9 == 25) {
                                    count8++;
                                    value5 = (value5 ^ 0x5a7cfa82cd8f07e3L) + 0x1a74ee4420dc3300L;
                                    value6 = (value6 ^ -0x1611c61bd7e3338L) - 0x79ce63f75805fbaaL;
                                    break scanBlock;
                                 }

                                 if (count9 == 20) {
                                    value3 = 11L;
                                    value5 = (value5 ^ -0x6ce5c1762b113f48L) + 0x25a6e852493343d0L;
                                    value6 = (value6 ^ 0x4462f6a423a7a482L) + 0x4717c58a3c7223a3L;
                                    continue;
                                 }

                                 if (count9 == 31) {
                                    if (byteValueArray2.length == 24) {
                                       value5 = (value5 ^ 0x44424715ad18a4deL) - 0xd280033e42bde91L;
                                       value6 = (value6 ^ 0x32aada55a1e980f6L) + 0x2b7dca639fabf818L;
                                    } else {
                                       value5 = (value5 ^ -0x339d9a254b381b53L) - 0x6dfacd53fb2e13aeL;
                                       value6 = (value6 ^ -0x6f610f94a2147ce9L) - 0x79917468a70421afL;
                                    }
                                    continue;
                                 }

                                 if (count9 == 24) {
                                    byteValueArray6 = new byte[16];
                                    value5 = (value5 ^ -0x5c48a8bb368e7f02L) - 0x507fb0b8af547180L;
                                    value6 = (value6 ^ -0x46116f496943c403L) + 0x31cba046d476f217L;
                                    continue;
                                 }

                                 if (count9 == 33) {
                                    throw new IllegalArgumentException("Invalid key size. Must be 16, 24, or 32 bytes");
                                 }

                                 if (count9 == 14) {
                                    byteValue = 0;
                                    value5 = (value5 ^ -0x3552b2220ded7d6eL) + 0x3140bb10393f323fL;
                                    value6 = (value6 ^ -0x63955b1511853321L) - 0x7daa48eed214a02dL;
                                    continue;
                                 }

                                 if (count9 == 21) {
                                    countArray8[count7] = byteValueArray6[count7] & 255;
                                    value5 = (value5 ^ -0x2853cdba26b57f86L) + 0x6eca133ce0c9994eL;
                                    value6 = (value6 ^ -0x4775929148d006a9L) - 0x15d2a68e6052aaa8L;
                                    continue;
                                 }

                                 if (count9 == 11) {
                                    value2 = 25214903917L;
                                    value5 = (value5 ^ -0x349b4cd5528d0d00L) - 0x79545f23ed8a85dfL;
                                    value6 = (value6 ^ 0x1f5ac7e74fd54469L) - 0x1c5f98912236ee64L;
                                    continue;
                                 }

                                 if (count9 == 19) {
                                    value4 = 281474976710655L;
                                    value5 = (value5 ^ 0xb23e6faa0766f33L) + 0x2e2cced4489fdc45L;
                                    value6 = (value6 ^ 0x4cbcfc4b4da6f9e3L) - 0x25f3273646c6f86fL;
                                    continue;
                                 }

                                 if (count9 == 3) {
                                    break evalBlock;
                                 }

                                 if (count9 == 1) {
                                    if (byteValueArray2.length == 32) {
                                       value5 = (value5 ^ 0x6e1629fd861592d7L) + 0x6e8d585a79fe375L;
                                       value6 = (value6 ^ 0x17b3e83081f5f3deL) - 0xa94bf013ea319c0L;
                                    } else {
                                       value5 = (value5 ^ -0x792a411b4d86d12eL) - 0x5d9339f5de29c983L;
                                       value6 = (value6 ^ 0x2b6713dc0c6354bdL) - 0x48ca3595b41434caL;
                                    }
                                    continue;
                                 }

                                 if (count9 == 35) {
                                    byteValueArray7 = new byte[byteValueArray.length];
                                    value5 = (value5 ^ 0x333cfb38c4122171L) + 0x6d44e962b29007d1L;
                                    value6 = (value6 ^ 0x58c8292ca1d65075L) + 0x49a6b595a724973dL;
                                    continue;
                                 }

                                 if (count9 == 12) {
                                    byteValueArray5 = this.resolveByteArray(byteValueArray2, countArray9);
                                    value5 = (value5 ^ -0x7a613dabeec1b64L) + 0x799295c423dc3856L;
                                    value6 = (value6 ^ -0x3ea19bcdf025ba9aL) + 0x66d0209dac9f81aeL;
                                    continue;
                                 }

                                 if (count9 == 9) {
                                    break scanBlock;
                                 }

                                 if (count9 == 13) {
                                    count6++;
                                    value5 = (value5 ^ -0x775a1121d4c213f0L) + 0x78eb3187f802f913L;
                                    value6 = (value6 ^ -0x7c1a604b0e29d698L) - 0x7fa3eb77d575e88aL;
                                    break evalBlock;
                                 }

                                 if (count9 == 8) {
                                    byteValueArray4[count6] = (byte)((int)(value >> 16 & 255L));
                                    value5 = (value5 ^ 0x18515490b2f62728L) + 0x3ff5bd768beddb93L;
                                    value6 = (value6 ^ 0x4994a0c873265beeL) + 0x59830e215baeb5e7L;
                                    continue;
                                 }

                                 if (count9 == 6) {
                                    value = value * value2 + value3 & value4;
                                    value5 = (value5 ^ 0x1ec35cdab3fe02eaL) - 0x217aeea5c67aacebL;
                                    value6 = (value6 ^ 0x715ed8bf01e2db7eL) - 0x2ac9494bb9b5d7c4L;
                                    continue;
                                 }

                                 if (count9 == 5) {
                                    System.arraycopy(byteValueArray8, 0, byteValueArray9, byteValueArray4.length + byteValueArray7.length, byteValueArray8.length);
                                    break;
                                 }

                                 if (count9 == 38) {
                                    byteValueArray6[15] = 1;
                                    value5 = (value5 ^ 0x6ad05d7e27e3fc15L) - 0xa7b6594da59fad8L;
                                    value6 = (value6 ^ -0x344d3dd10035bdbcL) - 0x2b80da21430a4ab2L;
                                    continue;
                                 }

                                 if (count9 == 18) {
                                    byteValueArray9 = new byte[byteValueArray4.length + byteValueArray7.length + byteValueArray8.length];
                                    value5 = (value5 ^ 0x2ddf8c72c57be548L) + 0x19bb188f4bcaab9bL;
                                    value6 = (value6 ^ 0x19d47823e49568b1L) - 0x2a8ad273af3877d6L;
                                    continue;
                                 }

                                 if (count9 == 34) {
                                    count7 = 0;
                                    value5 = (value5 ^ -0x49cd87a28be378d8L) - 0x27ff2282749464e9L;
                                    value6 = (value6 ^ 0x35b74490e96dbb3cL) - 0x567502a897212d2fL;
                                    continue;
                                 }

                                 if (count9 == 10) {
                                    countArray8 = new int[16];
                                    value5 = (value5 ^ -0x5f9e610eda33ad04L) + 0x4bcb9cfcf339e6b8L;
                                    value6 = (value6 ^ -0x224501c6b4332446L) - 0x2f2667d31b619a55L;
                                    continue;
                                 }

                                 if (count9 == 29) {
                                    break checkBlock;
                                 }

                                 if (count9 != 15) {
                                    if (count9 == 2) {
                                       count6 = 0;
                                       value5 = (value5 ^ 0x7b8276971683326dL) + 0x1682a9c57d3f28eaL;
                                       value6 = (value6 ^ 0x59a9aa8b3162d904L) + 0x6e520fed2bb882cfL;
                                       continue;
                                    }

                                    if (count9 == 23) {
                                       byteValueArray4 = new byte[12];
                                       value5 = (value5 ^ 0x3eff21d32ca32d23L) + 0x753a27293e184536L;
                                       value6 = (value6 ^ 0xaf01312dff66ce7L) - 0x6d932cf6a805522fL;
                                       continue;
                                    }

                                    if (count9 != 7) {
                                       if (count9 == 37) {
                                          count7 = Math.min(16, byteValueArray.length - byteValue);
                                          value5 = (value5 ^ -0x64bfb8c746d8b3c4L) + 0x2adbc4269b619d62L;
                                          value6 = (value6 ^ 0x33f2dffea2008ab6L) + 0x54259d9a735ee408L;
                                          continue;
                                       }

                                       if (count9 == 26) {
                                          System.arraycopy(byteValueArray7, 0, byteValueArray9, byteValueArray4.length, byteValueArray7.length);
                                          value5 = (value5 ^ -0xb1a779b8851e980L) - 0x60de18fef990924fL;
                                          value6 = (value6 ^ -0x7ccecb28b0bea37eL) - 0x3381442b84725891L;
                                          continue;
                                       }

                                       if (count9 == 36) {
                                          byteValueArray7[byteValue + count8] = (byte)(byteValueArray[byteValue + count8] ^ (byte)countArray8[count8]);
                                          value5 = (value5 ^ 0x12b8fdcaca71c2bfL) + 0x13dd3e41689be306L;
                                          value6 = (value6 ^ -0x7f171df6014adba4L) - 0x5d8e5de1bbfd73e9L;
                                          continue;
                                       }

                                       if (count9 != 28) {
                                          if (count9 == 0) {
                                             break;
                                          }

                                          if (count9 != 4) {
                                             if (byteValueArray2.length == 16) {
                                                value5 = (value5 ^ 0x59c3ea817736f4e7L) - 0x46c6135d8da4a6dcL;
                                                value6 = (value6 ^ -0x5a7c08339b02c89aL) - 0x62b8a8b279c1fc17L;
                                             } else {
                                                value5 = (value5 ^ -0x72a6f1c58c7eb893L) + 0x56059242f01a6901L;
                                                value6 = (value6 ^ -0x3879400860a0bdabL) + 0x456d0af06f58f9f2L;
                                             }
                                          } else {
                                             value = System.nanoTime();
                                             value5 = (value5 ^ 0x7a570e7f18999f75L) + 0x297eeb03d2fbcb5dL;
                                             value6 = (value6 ^ 0x462115b65633cd0cL) + 0x67a5663bc547641dL;
                                          }
                                          continue;
                                       }

                                       count7++;
                                       value5 = (value5 ^ -0x31e0c2f9bd74d67dL) + 0x66fa9f34a8b0272bL;
                                       value6 = (value6 ^ -0x553c0912e1e07b8aL) + 0x6147d7eef6500db8L;
                                       break checkBlock;
                                    }
                                    break guardBlock;
                                 }

                                 value7 = (value5 ^ -0x31d8a82871a859a7L) + 0x3e26b42f89ada259L;
                                 value8 = (value6 ^ -0x5af2acef1a5d4960L) - 0x5afcd2f064a14a60L;
                                 break searchBlock;
                              }
                           } else {
                              if (++byteValueArray6[count8] != 0) {
                                 value5 = (value5 ^ 0x133810ab736acd24L) - 0xda5d577f0003374L;
                                 value6 = (value6 ^ 0x10b98f0fb451facaL) + 0x28643cde76fbea38L;
                                 continue;
                              }

                              long value9 = (value5 ^ 0x7e58d6061f59c8ddL) + 0xcfae82d7bd0d275L;
                              long value10 = (value6 ^ -0x3ee389c504eec3d9L) + 0x58c685d1bdbca91dL;
                              count8--;
                              value5 = (value9 ^ 0x332b0458d5da6791L) + 0x2a93a6425443cd2cL;
                              value6 = (value10 ^ 0x16ec05963630739bL) - 0x441d8324d7e735ddL;
                           }

                           if (count8 >= 12) {
                              value5 = (value5 ^ -0xb73794187d19fbL) - 0x79628525fa366c48L;
                              value6 = (value6 ^ -0x767d02725d42362fL) - 0x34c67ab9bea734e9L;
                              continue;
                           }

                           value7 = (value5 ^ -0x5f475a250957fda8L) - 0x590007438cb1eec3L;
                           value8 = (value6 ^ -0x450679edfa118c10L) - 0x3016f45756af2608L;
                        }

                        byteValue += 16;
                        value5 = (value7 ^ 0x53a7a6082d831a1fL) - 0x614f1e1f05a27bc4L;
                        value6 = (value8 ^ 0x5c8797e65edf440fL) + 0x71ed4f6f74912386L;
                     }

                     if (byteValue >= byteValueArray.length) {
                        long value11 = (value5 ^ -0x4816437bddf173d0L) - 0x7d312764eddf0a2dL;
                        long value12 = (value6 ^ -0x6436fcb59d0f44edL) - 0xf2bdbc3af88e87eL;
                        byteValueArray8 = this.resolveByteArray(byteValueArray4, byteValueArray7, byteValueArray3, countArray9, byteValueArray5);
                        value5 = (value11 ^ 0x163e8daf8ff68508L) - 0xc14abdb23217ac0L;
                        value6 = (value12 ^ 0x7f21e7890a3662ecL) + 0x5f66739831a5af02L;
                     } else {
                        value5 = (value5 ^ 0x31692e7c10db7d9eL) + 0x4fb1e796f3777fdL;
                        value6 = (value6 ^ 0x1c5e9247886ee595L) + 0x1ca21c77170027caL;
                     }
                     continue;
                  }

                  if (count7 >= 16) {
                     long value13 = (value5 ^ 0x6d504d35f8f10543L) + 0x29c5eac53275b293L;
                     long value14 = (value6 ^ 0x19d579eb85b93039L) + 0xc2d59087cf6a5e9L;
                     this.applyIndexArray(countArray8, countArray9);
                     value5 = (value13 ^ -0x4e54231c9ecaba89L) - 0x41738ffcf77d7ff7L;
                     value6 = (value14 ^ -0x6c08a69e3f01b05fL) + 0x5247ffbc987e9691L;
                  } else {
                     value5 = (value5 ^ 0x11468a40ecd0e069L) - 0x61a17567c96cc9ffL;
                     value6 = (value6 ^ 0x11a2166288dd81deL) - 0x6e1cfd1e763094d1L;
                  }
                  continue;
               }

               if (count6 >= byteValueArray4.length) {
                  long value15 = (value5 ^ 0x7a50448f2e6f62ffL) + 0x287fad38f5517d00L;
                  long value16 = (value6 ^ 0x53a3d19c4ec9c3d7L) - 0x2fe26da02a78eefdL;
                  countArray9 = this.resolveIndexArray(byteValueArray2);
                  value5 = (value15 ^ 0x227a42b52e4504fcL) - 0x3ed999c88cf4437cL;
                  value6 = (value16 ^ 0x2172a207706aea6bL) + 0x7d0102fbae1a63ffL;
               } else {
                  value5 = (value5 ^ 0x6195d0f320043893L) + 0x950b3ce3f8d4d32L;
                  value6 = (value6 ^ 0x329e19eed3f65012L) + 0x47afccf7a5789225L;
               }
               continue;
            }

            if (count8 >= count7) {
               long value17 = (value5 ^ 0x589ee7c3bd6bad1bL) + 0x129508fd50786818L;
               long value18 = (value6 ^ -0x56f205516d99ecbdL) + 0x38608cdb282d5aa3L;
               count8 = byteValueArray6.length - 1;
               value5 = (value17 ^ -0x11d8a4d5f45468bbL) + 0x59297afc1f1744d2L;
               value6 = (value18 ^ -0x31eca8709f7afed1L) - 0x1133d60b3326e0L;
            } else {
               value5 = (value5 ^ -0x1e1d1a046ebf97f5L) - 0x66739a33022a7d2dL;
               value6 = (value6 ^ -0x2c48a5c7497bfccfL) + 0x4fc1284df5bba29eL;
            }
         } else {
            System.arraycopy(byteValueArray4, 0, byteValueArray9, 0, byteValueArray4.length);
            value5 = (value5 ^ 0x1b0e3b2b68f3ef11L) + 0x29cba0fe8ccfba3L;
            value6 = (value6 ^ -0x1429c2a39a673f87L) + 0x7bfdf10410e2d7e6L;
         }
      }

      return byteValueArray9;
   }

   public byte[] resolveByteArrayByByteArray(byte[] byteValueArray, byte[] byteValueArray2) {
      return this.resolveByteArray(byteValueArray, byteValueArray2, (byte[])null);
   }

   public byte[] resolveByteArrayByByteArray(byte[] byteValueArray, byte[] byteValueArray2, byte[] byteValueArray3) {
      byte[] byteValueArray4 = null;
      int count6 = 0;
      byte[] byteValueArray5 = null;
      byte[] byteValueArray6 = null;
      int[] countArray8 = null;
      byte[] byteValueArray7 = null;
      byte[] byteValueArray8 = null;
      int count7 = 0;
      int count8 = 0;
      byte[] byteValueArray9 = null;
      int[] countArray9 = null;
      int byteValue = 0;
      int count9 = 0;
      int count10 = 0;
      byte[] byteValueArray10 = null;
      long value = 0x7118fc83a9e80ba0L;
      long value2 = -0x76c206f481f9c17dL;

      while (true) {
         int count11 = (int)(value % 41L);
         if (count11 == 7) {
            System.arraycopy(byteValueArray, 0, byteValueArray4, 0, byteValueArray4.length);
            value = (value ^ -0x7d9ab21129c3067dL) + 0x7ee2aad33e56a326L;
            value2 = (value2 ^ 0x3697264d2afe6a0L) + 0x5b08552037d925dfL;
         } else if (count11 != 39) {
            if (count11 != 24) {
               if (count11 != 3) {
                  if (count11 != 0) {
                     scanBlock: {
                        evalBlock:
                        if (count11 != 33) {
                           if (count11 == 20) {
                              count10 = 0;
                              value = (value ^ -0x5cd7284cdd27a819L) - 0x3da01b1f113a695bL;
                              value2 = (value2 ^ 0x37e745ea024d630bL) + 0x65a5087d4f651537L;
                              continue;
                           }

                           long value3;
                           long value4;
                           checkBlock:
                           if (count11 != 35) {
                              if (count11 == 13) {
                                 byteValue = 0;
                                 value = (value ^ 0x4721b24e693ed792L) + 0x1bbcdba88d59bc78L;
                                 value2 = (value2 ^ -0x782f8e6c6037da81L) - 0x6815e31200db5d98L;
                                 continue;
                              }

                              if (count11 == 38) {
                                 throw new IllegalArgumentException("Data too short for GCM mode");
                              }

                              if (count11 == 11) {
                                 byteValueArray6 = new byte[16];
                                 value = (value ^ 0x60c8bb2e3679cc68L) - 0x291afbac71999ea1L;
                                 value2 = (value2 ^ 0x469d6915e89f7abfL) + 0x78c3ae0f1fdc0d31L;
                                 continue;
                              }

                              if (count11 == 26) {
                                 byteValueArray9[byteValue + count10] = (byte)(byteValueArray5[byteValue + count10] ^ (byte)countArray9[count10]);
                                 value = (value ^ 0x79a2d3283767debaL) - 0x3612f003c1ae91b4L;
                                 value2 = (value2 ^ -0x2cfbc9c2404b4a7dL) - 0x3345217dee805794L;
                                 continue;
                              }

                              guardBlock: {
                                 searchBlock: {
                                    if (count11 != 36) {
                                       if (count11 == 17) {
                                          System.arraycopy(byteValueArray4, 0, byteValueArray10, 0, byteValueArray4.length);
                                          value = (value ^ 0x3b1dd84beaf9d0e5L) + 0x2f8d8596425a24adL;
                                          value2 = (value2 ^ 0x27516091b6df7eaaL) - 0x6313d00f6ea3c905L;
                                          continue;
                                       }

                                       if (count11 == 40) {
                                          count7 = 0;
                                          value = (value ^ 0x7dd07f18a0652549L) - 0x4bd4b0c337826742L;
                                          value2 = (value2 ^ -0x39e41ff77509c7d4L) - 0x111768110b262fc7L;
                                          continue;
                                       }

                                       if (count11 == 4) {
                                          count6 = byteValueArray.length - byteValueArray4.length - 16;
                                          value = (value ^ 0x377edd7e146828c4L) + 0x1d1ff54aa90c0600L;
                                          value2 = (value2 ^ -0x2a82e4a6a9b29320L) + 0x5f34c93b2c6e80cbL;
                                          continue;
                                       }

                                       if (count11 == 32) {
                                          break scanBlock;
                                       }

                                       if (count11 == 2) {
                                          countArray9[count9] = byteValueArray10[count9] & 255;
                                          value = (value ^ -0x2ecf0071df8dddb0L) + 0x670e935dd50bde10L;
                                          value2 = (value2 ^ -0x369dde3091e32075L) - 0x2a30cdceed951974L;
                                          continue;
                                       }

                                       if (count11 == 5) {
                                          count9 = 0;
                                          value = (value ^ 0x29d377a90375f533L) - 0x105345a4acdd0ceeL;
                                          value2 = (value2 ^ -0x5f92d619876c308cL) + 0x4e9b56ac101af79L;
                                          continue;
                                       }

                                       if (count11 == 18) {
                                          throw new IllegalArgumentException("Invalid key size. Must be 16, 24, or 32 bytes");
                                       }

                                       if (count11 == 15) {
                                          byteValueArray7 = this.resolveByteArray(byteValueArray2, countArray8);
                                          value = (value ^ -0x396ea8e42e6b53b6L) + 0x7a5d56eac9099589L;
                                          value2 = (value2 ^ -0x621eb0c894f84088L) + 0x2e02099dd9f4fb77L;
                                          continue;
                                       }

                                       if (count11 == 23) {
                                          count9++;
                                          value = (value ^ -0x4918f262a6cf3fcdL) + 0x7e39b218a7d38431L;
                                          value2 = (value2 ^ 0x225c64925b87d99L) + 0x613401523f1917c6L;
                                          break evalBlock;
                                       }

                                       if (count11 == 12) {
                                          throw new SecurityException("GCM authentication failed");
                                       }

                                       if (count11 == 31) {
                                          break searchBlock;
                                       }

                                       if (count11 == 22) {
                                          byteValueArray10 = new byte[16];
                                          value = (value ^ -0x6a24cce34e63a9d4L) + 0x4f2f345be58ca1deL;
                                          value2 = (value2 ^ -0x3b8a9e0ee4db1565L) - 0x70f35ca8886b210cL;
                                          continue;
                                       }

                                       if (count11 == 30) {
                                          if (byteValueArray2.length == 32) {
                                             value = (value ^ 0x2fe46ac43d1bb18dL) + 0x1c84b05d8f2b4bfbL;
                                             value2 = (value2 ^ 0x25f6b73643fc0004L) + 0x3a7757c2f1c1e9bL;
                                          } else {
                                             value = (value ^ -0x1d5b0fc295651621L) + 0x6eedda2c17e31263L;
                                             value2 = (value2 ^ -0x78516e4f0678303L) - 0x39b70b31a99555bbL;
                                          }
                                          continue;
                                       }

                                       if (count11 == 6) {
                                          byteValueArray5 = new byte[count6];
                                          value = (value ^ -0x1f417a6346a89614L) - 0x28c67f07b2117134L;
                                          value2 = (value2 ^ 0x5642b04e4c13ee7bL) - 0x2b332b5cd111150bL;
                                          continue;
                                       }

                                       if (count11 == 1) {
                                          byteValueArray9 = new byte[count6];
                                          value = (value ^ -0x138d8e5f6780254L) - 0x1e6d41428dfd2612L;
                                          value2 = (value2 ^ 0x51432a0bca82d3c9L) + 0x577f8e7b1de7f36bL;
                                          continue;
                                       }

                                       if (count11 == 29) {
                                          break guardBlock;
                                       }

                                       if (count11 == 14) {
                                          System.arraycopy(byteValueArray, byteValueArray4.length, byteValueArray5, 0, count6);
                                          value = (value ^ 0x765a6f1f6d794d11L) - 0x2d01fe9250beb17eL;
                                          value2 = (value2 ^ 0x6cbad03812bebf1bL) - 0x576c660b7b4700efL;
                                          continue;
                                       }

                                       if (count11 == 9) {
                                          if (byteValueArray2.length == 16) {
                                             value = (value ^ -0x3ed948aebc19376eL) - 0x6fb5ae7af6156f55L;
                                             value2 = (value2 ^ 0x1c92832da5e66a8aL) + 0x6166b8bcc1001b90L;
                                          } else {
                                             value = (value ^ -0x30bea6cf2c7ca768L) + 0x4c5ae0e6e291bd35L;
                                             value2 = (value2 ^ 0x1c5371db17b9d84fL) - 0x6edae1fedd3cd90aL;
                                          }
                                          continue;
                                       }

                                       if (count11 == 10) {
                                          count10++;
                                          value = (value ^ -0x34d4fa9d291defc0L) - 0x74271cd3e71b800eL;
                                          value2 = (value2 ^ 0x150b8e29fd6ae859L) - 0x7f6c7fe630a51b1dL;
                                          break guardBlock;
                                       }

                                       if (count11 == 21) {
                                          System.arraycopy(byteValueArray, byteValueArray4.length + count6, byteValueArray6, 0, 16);
                                          value = (value ^ -0x52e45730ca98f48aL) - 0x49fc71088b887bcdL;
                                          value2 = (value2 ^ 0x47e2ca800364d947L) + 0x17fed0030a4cb2deL;
                                          continue;
                                       }

                                       if (count11 == 16) {
                                          if (byteValueArray.length >= 28) {
                                             value = (value ^ -0x85ca097d287af05L) + 0x573ee2e3662f0a81L;
                                             value2 = (value2 ^ 0x1e469fba902adc56L) + 0x1ea1968c10a3286dL;
                                          } else {
                                             value = (value ^ -0x7ecaa0226de611c5L) - 0x45e8fabbc1716549L;
                                             value2 = (value2 ^ -0x466dd15f42357ec1L) + 0x152ccf7418555a5aL;
                                          }
                                          continue;
                                       }

                                       if (count11 == 28) {
                                          count9 = Math.min(16, byteValueArray5.length - byteValue);
                                          value = (value ^ -0x4c3e68afbb2c4be0L) + 0x584119ffaae2eb6cL;
                                          value2 = (value2 ^ -0x6512a08d346205d3L) - 0x5c18eff911dbc093L;
                                          continue;
                                       }

                                       if (count11 == 34) {
                                          byteValueArray8 = this.resolveByteArray(byteValueArray4, byteValueArray5, byteValueArray3, countArray8, byteValueArray7);
                                          value = (value ^ 0x1cd605fff2908844L) - 0x42ee5d4ebb51eb5eL;
                                          value2 = (value2 ^ -0x4785ae4a7b780451L) - 0x26cddeae21d795e4L;
                                          continue;
                                       }

                                       if (count11 == 27) {
                                          byteValueArray4 = new byte[12];
                                          value = (value ^ 0x14b736abae87a3d9L) + 0x5c9294b4df3ad4b1L;
                                          value2 = (value2 ^ 0x1abac0fb38a6482eL) + 0x2e89c47c1c29850cL;
                                          continue;
                                       }

                                       if (count11 == 37) {
                                          count8++;
                                          value = (value ^ 0x4517afd0fa9fba0cL) - 0x6799860a359aa14L;
                                          value2 = (value2 ^ 0x58912e8e5cbc2754L) + 0x363e111b2439c3b4L;
                                          break searchBlock;
                                       }

                                       if (count11 == 19) {
                                          countArray8 = this.resolveIndexArray(byteValueArray2);
                                          value = (value ^ -0x38123a9c581ce03aL) - 0x50dc7a5a68ab6e67L;
                                          value2 = (value2 ^ -0x1cadd86e7e7c56cbL) - 0x198ad0944e54ac8bL;
                                          continue;
                                       }

                                       if (count11 == 25) {
                                          count8 = 0;
                                          value = (value ^ -0x60bec3c44aaea697L) - 0x7e51f711e708a2bbL;
                                          value2 = (value2 ^ 0x182102ce0d130d64L) + 0x300442bd73d18250L;
                                          continue;
                                       }
                                    } else {
                                       if (++byteValueArray10[count10] != 0) {
                                          value = (value ^ 0x3f058395439e8832L) - 0x1876dfc8574d3a39L;
                                          value2 = (value2 ^ 0x447b8d0bb068dc43L) - 0x3e0a491bf062d7c1L;
                                          continue;
                                       }

                                       long value5 = (value ^ -0x3752098cdf24ebf0L) + 0x5230ed59c76e39a9L;
                                       long value6 = (value2 ^ -0x3ab7e4ef410e72e1L) + 0x410340ed1935f983L;
                                       count10--;
                                       value = (value5 ^ 0x2ae60ec3ac120cabL) + 0x8fcb0c49b5fb026L;
                                       value2 = (value6 ^ 0x53dfa3aa3dbe5730L) + 0x2e99243284fe8f7aL;
                                    }

                                    if (count10 >= 12) {
                                       value = (value ^ -0x78476f6fe73a89c1L) + 0x51a2313d210878a1L;
                                       value2 = (value2 ^ -0x597c4044528fe585L) + 0x4641ebe431fa0ed1L;
                                       continue;
                                    }

                                    value3 = (value ^ -0x58529a84c05a6e42L) + 0x7c36a8852af1edbbL;
                                    value4 = (value2 ^ -0x6ba51f44213a8320L) - 0x4daa273ddc0b5a4aL;
                                    break checkBlock;
                                 }

                                 if (count8 >= 16) {
                                    long value7 = (value ^ 0x41ec3dffcf7ba035L) + 0x1bdd98488cad003L;
                                    long value8 = (value2 ^ -0x3c193690111caaabL) - 0x3b12700449725a0dL;
                                    if (count7 == 0) {
                                       value = (value7 ^ 0x79fdc5bf81bf5cf9L) + 0x49a6498ba574b885L;
                                       value2 = (value8 ^ 0x202666376006a6e0L) - 0x3d1a26c379e76ac5L;
                                    } else {
                                       value = (value7 ^ 0x2adcc1c0b2aca0ebL) - 0x4e0a7681f8dbb60eL;
                                       value2 = (value8 ^ 0x2ecfa8f57d26fdf9L) - 0x421100ec8ac35546L;
                                    }
                                 } else {
                                    value = (value ^ -0x50cb0b1bb6a3ee51L) + 0x4bf49b578889944fL;
                                    value2 = (value2 ^ 0x678c24e386df0f8aL) + 0xa34b306b91ae4beL;
                                 }
                                 continue;
                              }

                              if (count10 >= count9) {
                                 long value9 = (value ^ -0x92bdf150c0992b8L) - 0x60bc0863c218eceaL;
                                 long value10 = (value2 ^ -0x827d35244a256beL) - 0x20d71de5eb8ff46eL;
                                 count10 = byteValueArray10.length - 1;
                                 value = (value9 ^ -0x7c73e71c41c7d121L) - 0x65f1448b6a8fb505L;
                                 value2 = (value10 ^ -0x54d3217a0e7a768aL) - 0x547a881761820d61L;
                              } else {
                                 value = (value ^ 0x1b0a09765c123713L) - 0x4d71b1f477286aabL;
                                 value2 = (value2 ^ 0x17b7075704ab70ffL) - 0x1cf6a1570330b7a4L;
                              }
                              continue;
                           } else {
                              value3 = (value ^ 0x66ceadf651edf340L) - 0x6549aa8dae1370c0L;
                              value4 = (value2 ^ -0x5f7f85c012343633L) + 0x607fe5edab39b64dL;
                           }

                           byteValue += 16;
                           value = (value3 ^ 0xd3c3045fd1f29eaL) + 0x34559b55e41db0a2L;
                           value2 = (value4 ^ 0x6404d9ed3cddfce6L) + 0x103d9a7514ad4561L;
                           break scanBlock;
                        }

                        if (count9 >= 16) {
                           long value11 = (value ^ -0x4ce36ac53d40f899L) - 0x7f4b39c893a242fbL;
                           long value12 = (value2 ^ -0x4ac79b66ae219f7eL) + 0x35a19d6b4f413243L;
                           this.applyIndexArray(countArray9, countArray8);
                           value = (value11 ^ 0x5dd7dafa51c94a82L) - 0x63a8ffda11196b35L;
                           value2 = (value12 ^ -0x20d7cc9ed20df073L) - 0x4a7228414a9d9569L;
                        } else {
                           value = (value ^ 0x70e3e994dc7ec2edL) - 0x77dfb5fa8a1a85d1L;
                           value2 = (value2 ^ -0x2a872ae1f015dc15L) + 0x492ffcfe94fe4f4bL;
                        }
                        continue;
                     }

                     if (byteValue >= byteValueArray5.length) {
                        return byteValueArray9;
                     }

                     value = (value ^ 0xe417a31da1f8a96L) - 0x111d2525fda7798fL;
                     value2 = (value2 ^ 0x3d1bad8bcd518237L) - 0x7dfbc637ad7c5f6fL;
                  } else {
                     count7 |= byteValueArray6[count8] ^ byteValueArray8[count8];
                     value = (value ^ -0x1588af461164f46L) + 0x5de0240c08065974L;
                     value2 = (value2 ^ -0x33f728e57eb2ba70L) - 0x6d55a6741c0c5c98L;
                  }
               } else if (byteValueArray2.length == 24) {
                  value = (value ^ 0xbc719fdbe86bfe3L) + 0x3f14fdf0117da44fL;
                  value2 = (value2 ^ 0x7c17016a17264d77L) - 0x636e74d7fec4d11cL;
               } else {
                  value = (value ^ 0x544341da117923f9L) - 0x53104101f3ae7d25L;
                  value2 = (value2 ^ 0x50cb6965f7974363L) + 0x603f3a9cb3240259L;
               }
            } else {
               countArray9 = new int[16];
               value = (value ^ 0x16690ee61e77255aL) - 0x13c32ec9f733c288L;
               value2 = (value2 ^ -0x7b2add14805f06d1L) - 0x788413ab70f60e15L;
            }
         } else {
            byteValueArray10[15] = 1;
            value = (value ^ -0x49523c5517c56652L) - 0x7b086515e3aa4391L;
            value2 = (value2 ^ -0x65f869b0b2d5248aL) + 0x1f21d852cf36e070L;
         }
      }
   }

   private int[] resolveIndexArray(byte[] byteValueArray) {
      int byteValue = 0;
      int count6 = 0;
      int count7 = 0;
      int[] countArray8 = null;
      int count8 = 0;
      int count9 = 0;
      int[] countArray9 = null;
      long value = 0x2186e019571f41c9L;
      long value2 = -0x54d2c77963f5c54aL;

      while (true) {
         scanLoop:
         while (true) {
            innerLoop:
            while (true) {
               while (true) {
                  int count10 = (int)(value % 43L);
                  if (count10 == 28) {
                     count8++;
                     value = (value ^ -0x4e381a16a7183bfeL) + 0x2dbc2d84e53e6f8cL;
                     value2 = (value2 ^ 0x37f69e071d4179d7L) + 0x5f6002b1e513bb1fL;
                     break;
                  }

                  if (count10 == 29) {
                     count6 = 0;
                     value = (value ^ 0x52c8f64e558dbfacL) - 0x43902c219972687aL;
                     value2 = (value2 ^ 0x42838eb0d639a78dL) - 0x643fa489d3d58256L;
                  } else {
                     if (count10 == 34) {
                        break innerLoop;
                     }

                     if (count10 == 26) {
                        return this.countArray5;
                     }

                     guardBlock: {
                        long value3;
                        long value4;
                        searchBlock: {
                           if (count10 != 4) {
                              if (count10 == 32) {
                                 value = (value ^ -0x4c5892f5b62a0218L) + 0x5afaa16557d437f5L;
                                 value2 = (value2 ^ 0x74b9aaaa26c19ca0L) + 0x4a7beb6f9b7ef08fL;
                                 break scanLoop;
                              }

                              if (count10 == 0) {
                                 count9 ^= countArray3[count8 / count6] << 24;
                                 value = (value ^ 0x590be2534b1dcb6dL) + 0xf5f54fa5c569838L;
                                 value2 = (value2 ^ 0x4bbc704190ee6430L) + 0x5c907b66e404c687L;
                                 continue;
                              }

                              if (count10 == 11) {
                                 if (this.countArray5 == null) {
                                    value = (value ^ -0x6eb2c86f13ebd8b5L) - 0x74f040563414861bL;
                                    value2 = (value2 ^ -0xf9be72a67a61cb4L) - 0x3e3a981f2165fd65L;
                                    continue;
                                 }

                                 return this.countArray5;
                              }

                              if (count10 == 36) {
                                 countArray9[4 * count9] = countArray8[count9] >>> 24 & 0xFF;
                                 value = (value ^ -0x55840559d18ec47aL) + 0x6d913c910a92e1acL;
                                 value2 = (value2 ^ -0x56b4c84bde0bc285L) + 0x4c905dd19376e2a1L;
                                 continue;
                              }

                              if (count10 == 22) {
                                 countArray9[4 * count9 + 2] = countArray8[count9] >>> 8 & 0xFF;
                                 value = (value ^ -0x5f35f8f7af8b5896L) - 0x773739f7561e10ecL;
                                 value2 = (value2 ^ -0x5b7f89d5d35b224cL) + 0x394f9f55563f728bL;
                                 continue;
                              }

                              if (count10 != 27) {
                                 if (count10 == 38) {
                                    count9 = countArray8[count8 - 1];
                                    value = (value ^ -0x5d35c8d4e798b833L) - 0x4ea84875cc1593faL;
                                    value2 = (value2 ^ 0x6c92472a077307c1L) - 0xb20844ff13c6fbfL;
                                    continue;
                                 }

                                 if (count10 == 14) {
                                    if (count8 % count6 != 4) {
                                       value = (value ^ -0x631c05651597f92dL) + 0x7ebfb296331c06b6L;
                                       value2 = (value2 ^ -0x36f4b399ff0baf6fL) + 0x79211ea67a08dfd4L;
                                    } else {
                                       value = (value ^ 0xf99694714029a8dL) + 0x2143f62c6b5aecf7L;
                                       value2 = (value2 ^ 0x25938371e45c2f6eL) + 0x250593965816530eL;
                                    }
                                    continue;
                                 }

                                 if (count10 == 5) {
                                    break scanLoop;
                                 }

                                 if (count10 == 6) {
                                    return countArray9;
                                 }

                                 if (count10 == 21) {
                                    count9 = count9 << 8 & -256 | count9 >>> 24 & 0xFF;
                                    value = (value ^ -0x3e9f5ae57bff8098L) - 0x7a7233db1f6f0d01L;
                                    value2 = (value2 ^ -0x1a53217ed0d81bacL) - 0x777fd8120395124dL;
                                    continue;
                                 }

                                 if (count10 == 23) {
                                    countArray9[4 * count9 + 3] = countArray8[count9] & 0xFF;
                                    value = (value ^ 0x5570c0bc915199b5L) - 0x232192ed31f2548aL;
                                    value2 = (value2 ^ -0x350d29f497a96d7eL) - 0x13ab9dcb2607eaadL;
                                    continue;
                                 }

                                 if (count10 == 3) {
                                    break;
                                 }

                                 if (count10 != 9) {
                                    if (count10 == 17) {
                                       this.countArray5 = countArray9;
                                       return countArray9;
                                    }

                                    if (count10 == 39) {
                                       count9 = countArray6[count9 >>> 24 & 0xFF] << 24
                                          | countArray6[count9 >>> 16 & 0xFF] << 16
                                          | countArray6[count9 >>> 8 & 0xFF] << 8
                                          | countArray6[count9 & 0xFF];
                                       value = (value ^ -0x749e3bd6617b640dL) + 0x435d177aadee2fb2L;
                                       value2 = (value2 ^ 0x34da01a8b1592527L) + 0x314acafcdc2dfe1aL;
                                       continue;
                                    }

                                    if (count10 == 13) {
                                       count8++;
                                       value = (value ^ -0x2d4da4b9442b5399L) + 0x2b18e97c186e5533L;
                                       value2 = (value2 ^ -0x7f2c253780546d11L) - 0x5f9a186bb3a0cbe4L;
                                       break guardBlock;
                                    }

                                    if (count10 != 15) {
                                       if (count10 == 31) {
                                          countArray9[4 * count9 + 1] = countArray8[count9] >>> 16 & 0xFF;
                                          value = (value ^ 0x5e8d3ebef6cf5f73L) - 0x4cc0da575b122bbL;
                                          value2 = (value2 ^ 0x15e2eb76f110dc4aL) + 0x7940ad598ef129e9L;
                                          continue;
                                       }

                                       if (count10 == 30) {
                                          count9 = countArray6[count9 >>> 24 & 0xFF] << 24
                                             | countArray6[count9 >>> 16 & 0xFF] << 16
                                             | countArray6[count9 >>> 8 & 0xFF] << 8
                                             | countArray6[count9 & 0xFF];
                                          value = (value ^ 0x6eb3c2ab0d3431dbL) - 0x3bb0e2bf1606398L;
                                          value2 = (value2 ^ -0x4d273cceaae05b13L) + 0x69dc96ea1188fdd4L;
                                          continue;
                                       }

                                       if (count10 == 42) {
                                          byteValue = 0;
                                          value = (value ^ -0x29594ea6707aae78L) - 0x2309c705f76b3092L;
                                          value2 = (value2 ^ 0x76917445f69ed7d8L) - 0x683537bce1fbf113L;
                                          continue;
                                       }

                                       if (count10 == 16) {
                                          if (count8 % count6 != 0) {
                                             long value5 = (value ^ -0x4776c77662aa83e6L) - 0x6e0b3a63e5d2ec11L;
                                             long value6 = (value2 ^ 0x2c8cdc02f5aea47bL) - 0x41c862ba7909f4aaL;
                                             if (count6 <= 6) {
                                                value = (value5 ^ -0x656183973c9e9643L) + 0x31d1b002e180a3c2L;
                                                value2 = (value6 ^ 0x59f527d54aec0a22L) + 0x2d485e2e77a2830dL;
                                             } else {
                                                value = (value5 ^ 0x16d2bc46eb135325L) - 0x44d10c038f716749L;
                                                value2 = (value6 ^ -0x766aa40cc499ed1aL) + 0x1b7e8f480d27da66L;
                                             }
                                          } else {
                                             value = (value ^ 0x5a423ed96d663044L) - 0x25e8fb79d2f336e2L;
                                             value2 = (value2 ^ -0x168d36a8086dcc7fL) - 0x13a868ed55c86e51L;
                                          }
                                          continue;
                                       }

                                       if (count10 == 18) {
                                          count9 = 0;
                                          value = (value ^ -0x76dd2f49ea945e64L) - 0x569ea710786b33b8L;
                                          value2 = (value2 ^ 0x57019ab024554a44L) + 0x278560c717c29d01L;
                                          continue;
                                       }

                                       if (count10 == 24) {
                                          countArray8[count8] = (byteValueArray[4 * count8] & 255) << 24
                                             | (byteValueArray[4 * count8 + 1] & 255) << 16
                                             | (byteValueArray[4 * count8 + 2] & 255) << 8
                                             | byteValueArray[4 * count8 + 3] & 255;
                                          value = (value ^ -0x133e0f8d447d9e0dL) + 0x60fba61cf84e20dcL;
                                          value2 = (value2 ^ 0x4eef785dc2df7cbdL) + 0x233e450efb455ee5L;
                                          continue;
                                       }

                                       if (count10 == 10) {
                                          count8 = 0;
                                          value = (value ^ -0x49475b974f92b19fL) - 0x7ce91dc47584ea23L;
                                          value2 = (value2 ^ 0x39f93939d4a2ac5L) - 0x713674139d6a7410L;
                                          continue;
                                       }

                                       if (count10 == 40) {
                                          count7 = 4 * (byteValue + 1);
                                          value = (value ^ 0xf85fbb862a223fdL) + 0x5c2000c0d58a8f38L;
                                          value2 = (value2 ^ -0x1d6234beb56b5f4cL) - 0x6033816dbc1eddd7L;
                                          continue;
                                       }

                                       if (count10 == 35) {
                                          byteValue = 10;
                                          value = (value ^ 0xb08578e178572dcL) - 0x574fb9751dfb96bL;
                                          value2 = (value2 ^ 0x47b6710c18ba4e4aL) - 0x409ae78e7745845fL;
                                          continue;
                                       }

                                       if (count10 == 20) {
                                          value = (value ^ 0x787b731f4092965fL) - 0x52bbc98a2ac852feL;
                                          value2 = (value2 ^ -0x29d217328f8ea7e8L) + 0xe492fc32d38187L;
                                          break innerLoop;
                                       }

                                       if (count10 == 12) {
                                          countArray8 = new int[count7];
                                          value = (value ^ -0x67405e365ae27322L) + 0x29c7c2820023ffabL;
                                          value2 = (value2 ^ 0x2ba32627194e7db3L) - 0x733c99ce95b0cf69L;
                                          continue;
                                       }

                                       if (count10 == 41) {
                                          break guardBlock;
                                       }

                                       if (count10 == 1) {
                                          if (byteValueArray.length != 16) {
                                             long value7 = (value ^ 0x6c8e15c1560be343L) - 0x1b48d48709343ff5L;
                                             long value8 = (value2 ^ 0x5dcf62e0a58f08eaL) - 0x37bc1814e549b081L;
                                             if (byteValueArray.length != 24) {
                                                long value9 = (value7 ^ -0x7e639a00512ffad6L) + 0x5fd8446952c2d93dL;
                                                long value10 = (value8 ^ -0x6867fd6823910171L) - 0x203dd592bd43fd17L;
                                                byteValue = 14;
                                                value = (value9 ^ 0x3b853a81e2b806ccL) - 0x114d831fccddc28bL;
                                                value2 = (value10 ^ 0x1ca72b926daaa68fL) - 0x44a1aba4b0f51cf0L;
                                             } else {
                                                value = (value7 ^ 0x51b31edea33d8141L) - 0x48af5cc9ad0ba3f4L;
                                                value2 = (value8 ^ 0x37b836ea0e1f2a40L) - 0x51daeb73929a4d9L;
                                             }
                                          } else {
                                             value = (value ^ 0x2e7773c1355da0b3L) - 0x5854e86d64095a4eL;
                                             value2 = (value2 ^ 0x6cf11dcd606e7746L) - 0x2dd5cc9ca34ce418L;
                                          }
                                          continue;
                                       }

                                       if (count10 == 37) {
                                          value = (value ^ 0x57f2c223b4849f6cL) - 0x4ca1d1f1a23f6a44L;
                                          value2 = (value2 ^ -0x4adbfd25995f6caaL) - 0x2ae8aa0dc095592fL;
                                          break innerLoop;
                                       }

                                       if (count10 == 2) {
                                          byteValue = 12;
                                          value = (value ^ 0x534cb07245a61427L) - 0x7695c79a35fd0b5bL;
                                          value2 = (value2 ^ -0x43a24a31e9e3f15cL) + 0x40f3f12243b965b5L;
                                          continue;
                                       }

                                       if (count10 == 19) {
                                          if (byteValueArray.length != this.byteFlagArray3.length) {
                                             value = (value ^ 0x5bd38f89e1f2c83L) + 0x179e2d22dedc37aL;
                                             value2 = (value2 ^ -0x2174a5cbec2ffef1L) - 0x2d4ec9cb7b06d10fL;
                                          } else {
                                             value = (value ^ -0x1e20011ebfc726a9L) - 0x4d95a7a8422b4290L;
                                             value2 = (value2 ^ 0x1f943aa64ef26d12L) - 0x5957339143d76ebfL;
                                          }
                                          continue;
                                       }

                                       if (count10 == 25) {
                                          byteValue = 1;
                                          value = (value ^ -0x8bd7519bbd9f495L) - 0x6845b179e7a41846L;
                                          value2 = (value2 ^ -0x3ab3f054d9900bafL) + 0x180ae4926ff9646bL;
                                          continue;
                                       }

                                       if (count10 == 33) {
                                          System.arraycopy(byteValueArray, 0, this.byteFlagArray3, 0, byteValueArray.length);
                                          value = (value ^ 0x418c2db4f13a142fL) + 0x3286d14dd65fe761L;
                                          value2 = (value2 ^ -0x350b0e8efeecc8fdL) + 0x63ea85d4615a0315L;
                                          continue;
                                       }

                                       if (count10 != 8) {
                                          if (this.byteFlagArray3 == null) {
                                             value = (value ^ 0x793b707d66f2b9dcL) - 0x686ffaf8bbc8424L;
                                             value2 = (value2 ^ 0x62838810340beec8L) + 0x6a154b10b192ed7dL;
                                          } else {
                                             value = (value ^ 0x42e2b6fcac302f80L) - 0xe62c1cb14d2d155L;
                                             value2 = (value2 ^ 0x4a4ba32d25844280L) - 0x21ccfc64f242e631L;
                                          }
                                          continue;
                                       }

                                       value3 = (value ^ 0x16a50573e18eb16bL) - 0x1264fab1618d90dbL;
                                       value4 = (value2 ^ -0x73557b3effe77773L) + 0x749683beffe77785L;
                                       break searchBlock;
                                    }
                                 } else {
                                    count9++;
                                    value = (value ^ 0x7d8b1cfb90a28315L) - 0x2493bf76156bdc38L;
                                    value2 = (value2 ^ 0x29976ef461cabad4L) - 0x3defedbe19883365L;
                                 }

                                 if (count9 >= count7) {
                                    long value11 = (value ^ -0x17746f7c2d9225feL) + 0x48944a69f2b12143L;
                                    long value12 = (value2 ^ 0xadd4f3dfaace81aL) + 0x5ec6120d635a14cfL;
                                    this.byteFlagArray3 = new byte[byteValueArray.length];
                                    value = (value11 ^ -0x67faea19d9fc9f69L) - 0x177d5cdc5d676daeL;
                                    value2 = (value12 ^ 0x2956fd740a574871L) - 0x352413301d8b45d7L;
                                 } else {
                                    value = (value ^ 0x271514807a11afabL) + 0x3c075b7b8b3ef0c3L;
                                    value2 = (value2 ^ 0x7759a8cb67b371e0L) + 0x1d3475f3c51e2e0eL;
                                 }
                                 continue;
                              }
                           } else {
                              if (byteValueArray[count6] != this.byteFlagArray3[count6]) {
                                 value = (value ^ -0x6e43db64e5fcebfdL) - 0x6123905ee0eb6af6L;
                                 value2 = (value2 ^ -0x53997b349da4cc11L) - 0x5877b03ac2a7f7e0L;
                                 continue;
                              }

                              long value13 = (value ^ 0x38df291ab4a56caeL) + 0x5ad3f26549c41b6bL;
                              long value14 = (value2 ^ -0x6ce21c0f87916fa2L) - 0x47167950796f74ccL;
                              count6++;
                              value = (value13 ^ -0x5820e353acb9270dL) + 0x64c69e12c91e9865L;
                              value2 = (value14 ^ -0x6d5a0bff59d923d6L) + 0x4945cedfa6873462L;
                           }

                           if (count6 < byteValueArray.length) {
                              value = (value ^ 0xf5bd8339196e704L) - 0x15de04cd8a18e9d1L;
                              value2 = (value2 ^ -0x5db5a446562ca579L) - 0x5e662e28b95d6cbbL;
                              continue;
                           }

                           value3 = (value ^ 0x55779bad2fda6b16L) + 0x75f18ee5cc20a1aL;
                           value4 = (value2 ^ -0x5dec1b6f22ec49e5L) - 0x34b01f50bfb5f64dL;
                        }

                        if (byteValue == 0) {
                           value = (value3 ^ 0x3282f3d0b2c23796L) - 0x7b1bd8944b12de9L;
                           value2 = (value4 ^ 0x1dd12e2e0c635465L) + 0x754e5835386d90edL;
                        } else {
                           value = (value3 ^ -0x353a3fbe6c6f7f3eL) - 0x4b439712955e3947L;
                           value2 = (value4 ^ 0x6e6528ad8e964c03L) - 0x4c271addc12ecd3cL;
                        }
                        continue;
                     }

                     if (count8 >= count7) {
                        long value15 = (value ^ -0x10d3dd0b7529cf8eL) + 0x181d432a276ae640L;
                        long value16 = (value2 ^ 0x34c26c32314a33acL) - 0x2bd2a54dfcffd327L;
                        countArray9 = new int[count7 * 4];
                        value = (value15 ^ -0x782eeea8eebea955L) - 0x64c1ed0611f90105L;
                        value2 = (value16 ^ -0x58944f354c8aa78eL) + 0x4b1c2baad8c80b79L;
                     } else {
                        value = (value ^ -0x227e2bc2e00e101dL) - 0x789bbd772d0bb0a8L;
                        value2 = (value2 ^ 0x298445ebbbfc8318L) + 0x7c39d90b95fafb87L;
                     }
                  }
               }

               if (count8 >= count6) {
                  long value17 = (value ^ -0x735d668525e33ccfL) - 0x519871f153d66ebbL;
                  long value18 = (value2 ^ 0x5c9e612c098aa0c8L) - 0x35f84dd6ee4b61bcL;
                  count8 = count6;
                  value = (value17 ^ -0x611a808bdfd36adL) + 0x4b11d3b6a78ff317L;
                  value2 = (value18 ^ 0x4bb0cb512e01577cL) - 0x24a74a61c5b51786L;
               } else {
                  value = (value ^ 0x6c4c3add2d7d07e3L) - 0x3f66851eff08b59aL;
                  value2 = (value2 ^ 0x721105c23b06e13aL) - 0x452a55adf65e44aeL;
               }
            }

            count6 = byteValueArray.length / 4;
            value = (value ^ -0x7600854bb07554a6L) + 0x70572e8a2116f3ddL;
            value2 = (value2 ^ -0x7fb461dadcfe980dL) - 0x30601a1282dec37fL;
         }

         countArray8[count8] = countArray8[count8 - count6] ^ count9;
         value = (value ^ -0x4e9df4efecbf0e92L) + 0x6918d8ba7a792e76L;
         value2 = (value2 ^ -0x780232fe46bca204L) + 0x67582274f6b1b440L;
      }
   }

   private byte[] resolveByteArray(byte[] byteValueArray, byte[] byteValueArray2, byte[] byteValueArray3, int[] countArray8, byte[] byteValueArray4) {
      int count6 = byteValueArray3 != null ? byteValueArray3.length : 0;
      if (this.recordNode8 == null || !this.recordNode8.matchesByteArray(byteValueArray4)) {
         this.recordNode8 = new RecordNode8(byteValueArray4);
      }

      byte[] byteValueArray5 = new byte[16];
      if (byteValueArray3 != null && count6 > 0) {
         int count7 = (count6 + 16 - 1) / 16;

         for (int index = 0; index < count7; index++) {
            int count8 = Math.min(16, count6 - index * 16);

            for (int index2 = 0; index2 < count8; index2++) {
               byteValueArray5[index2] ^= byteValueArray3[index * 16 + index2];
            }

            this.applyByteArray(byteValueArray5);
         }
      }

      if (byteValueArray2.length > 0) {
         int count9 = (byteValueArray2.length + 16 - 1) / 16;

         for (int index3 = 0; index3 < count9; index3++) {
            int count10 = Math.min(16, byteValueArray2.length - index3 * 16);

            for (int index4 = 0; index4 < count10; index4++) {
               byteValueArray5[index4] ^= byteValueArray2[index3 * 16 + index4];
            }

            this.applyByteArray(byteValueArray5);
         }
      }

      byte[] byteValueArray6 = new byte[16];
      long value2 = (long)count6 * 8L;
      long value3 = (long)byteValueArray2.length * 8L;

      for (int index5 = 7; index5 >= 0; index5--) {
         byteValueArray6[index5] = (byte)((int)(value2 & 255L));
         value2 >>>= 8;
         byteValueArray6[index5 + 8] = (byte)((int)(value3 & 255L));
         value3 >>>= 8;
      }

      for (int index6 = 0; index6 < 16; index6++) {
         byteValueArray5[index6] ^= byteValueArray6[index6];
      }

      this.applyByteArray(byteValueArray5);
      byte[] byteValueArray7 = new byte[16];
      System.arraycopy(byteValueArray, 0, byteValueArray7, 0, byteValueArray.length);
      byteValueArray7[15] = 1;

      for (int index7 = 0; index7 < 16; index7++) {
         this.countArray4[index7] = byteValueArray7[index7] & 255;
      }

      int[] countArray9 = this.countArray4;
      this.applyIndexArray(countArray9, countArray8);

      for (int index8 = 0; index8 < 16; index8++) {
         this.byteFlagArray2[index8] = (byte)this.countArray4[index8];
      }

      byte[] byteValueArray8 = new byte[16];

      for (int index9 = 0; index9 < 16; index9++) {
         byteValueArray8[index9] = (byte)(byteValueArray5[index9] ^ this.byteFlagArray2[index9]);
      }

      return byteValueArray8;
   }

   private byte[] resolveByteArray(byte[] byteValueArray, int[] countArray8) {
      int count6 = 0;
      int count7 = 0;
      long value2 = 0x33fa95ae86ad6ed7L;
      long value3 = 0x78b8574299b8a697L;

      while (true) {
         while (true) {
            int count8 = (int)(value2 % 20L);
            if (count8 == 5) {
               count6++;
               value2 = (value2 ^ 0x3c8114e541a9f80eL) - 0x794216307d74c98cL;
               value3 = (value3 ^ 0x700d0ae071ddf61L) - 0x7cd74d8e5b2901f2L;
               break;
            }

            if (count8 == 10) {
               return this.byteFlagArray;
            }

            if (count8 == 16) {
               System.arraycopy(this.byteFlagArray4, 0, this.byteFlagArray, 0, 16);
               return this.byteFlagArray4;
            }

            if (count8 == 8) {
               count6 = 0;
               value2 = (value2 ^ 0x295d911d78ccda21L) + 0x46c79a35cf1b00bfL;
               value3 = (value3 ^ -0xfbfd5aaaf091032L) + 0x30fef3ac82d65e8dL;
            } else if (count8 == 17) {
               count6 = 0;
               value2 = (value2 ^ -0x6f04e693347dee7dL) + 0x18ed0a04bc3548f9L;
               value3 = (value3 ^ -0x6f5efa35aa0b5f6eL) - 0x6cf78f7f9d219347L;
            } else {
               guardBlock: {
                  long value4;
                  long value5;
                  searchBlock: {
                     if (count8 != 1) {
                        if (count8 == 15) {
                           break;
                        }

                        if (count8 == 3) {
                           break guardBlock;
                        }

                        if (count8 == 2) {
                           this.countArray[count6] = 0;
                           value2 = (value2 ^ 0x4e58d06ac6f10a5dL) - 0x348db731f8a92482L;
                           value3 = (value3 ^ 0x3c3bf5da2f6090eL) - 0x36742781496cd10eL;
                           continue;
                        }

                        if (count8 == 6) {
                           this.byteFlagArray = new byte[16];
                           value2 = (value2 ^ 0x3b0494d0ff2a64eL) - 0xa77f138c2bb4158L;
                           value3 = (value3 ^ 0x7734b3f1e830a52aL) - 0x6225d3d598c28f8aL;
                           continue;
                        }

                        if (count8 == 14) {
                           count7 = 0;
                           value2 = (value2 ^ -0x480e9586f0e5497aL) - 0x36ef535fdda53cf9L;
                           value3 = (value3 ^ -0x504671ac20e48e31L) + 0x64bec760fc49a600L;
                           continue;
                        }

                        if (count8 == 13) {
                           if (this.byteFlagArray == null) {
                              value2 = (value2 ^ 0x23e19027c488f36L) + 0x674ca7dcf8401dbeL;
                              value3 = (value3 ^ -0x597a3ab1adcb38ffL) + 0x11d117481613b35aL;
                           } else {
                              value2 = (value2 ^ 0x54edf79f4acf219eL) + 0x20d0679b01d9de31L;
                              value3 = (value3 ^ 0x40cf3ddbf1ba1858L) + 0x6f30e7ce378a819bL;
                           }
                           continue;
                        }

                        if (count8 == 4) {
                           count6 = 1;
                           value2 = (value2 ^ 0x4e7dadc5502cdd44L) - 0x13cbe7e8ea12dfb6L;
                           value3 = (value3 ^ -0x2cdb8ba7560ca605L) + 0x5bbd0386f914eae6L;
                           continue;
                        }

                        if (count8 == 7) {
                           count6++;
                           value2 = (value2 ^ 0x6d75e0105cb8e997L) + 0x3e65a176ed50fa43L;
                           value3 = (value3 ^ -0x1a42dc610304fe85L) - 0x3dd4c3518a0408aL;
                           break guardBlock;
                        }

                        if (count8 == 0) {
                           value4 = (value2 ^ -0x1daf71b510587344L) - 0x61af8a352299837cL;
                           value5 = (value3 ^ 0x707e7f248bb3f2abL) + 0x307e071b8991f1a7L;
                           break searchBlock;
                        }

                        if (count8 == 11) {
                           if (this.byteFlagArray3 == null) {
                              value2 = (value2 ^ -0x560bef4680415727L) - 0x1d8e858faba7c1bdL;
                              value3 = (value3 ^ -0x3b4137baafeddefL) + 0x5b212604f6260f80L;
                           } else {
                              value2 = (value2 ^ 0x6e5f5485a072d229L) - 0x46987281fd7c5359L;
                              value3 = (value3 ^ 0x63acd33fd0e32abeL) + 0x4db18b50b5a39b84L;
                           }
                           continue;
                        }

                        if (count8 != 19) {
                           if (count8 != 9) {
                              count6 = 0;
                              value2 = (value2 ^ 0x6131c531ae9b08f9L) + 0x4940e104ac7b7fd8L;
                              value3 = (value3 ^ 0x59c05a1052bfce63L) - 0x20d7fa49e46773L;
                           } else {
                              this.byteFlagArray4[count6] = (byte)this.countArray[count6];
                              value2 = (value2 ^ -0x4de845baf6704306L) - 0x45d61b6fcaac6f90L;
                              value3 = (value3 ^ -0x4143311ef2e82c9L) + 0x4fb246f8a793b922L;
                           }
                           continue;
                        }
                     } else {
                        if (byteValueArray[count7] != this.byteFlagArray3[count7]) {
                           value2 = (value2 ^ 0x6d187692ca7a46dbL) - 0x1d082aacd5564f0eL;
                           value3 = (value3 ^ -0x44bece57c3e772a0L) + 0x52386eb7f82af3ebL;
                           continue;
                        }

                        long value6 = (value2 ^ -0x6675100f6a81eff3L) - 0x52fdbace74ec3578L;
                        long value7 = (value3 ^ 0x1469111e41db15c3L) - 0x7fb0e77c90112bf3L;
                        count7++;
                        value2 = (value6 ^ -0x4a15f95fd1188f6cL) + 0x7d67b6668ead29c7L;
                        value3 = (value7 ^ 0x1fac156580638a65L) + 0x46ceef9994c71f46L;
                     }

                     if (count7 < byteValueArray.length) {
                        value2 = (value2 ^ -0x3b769781065e97e8L) - 0x78a68181a0be9bceL;
                        value3 = (value3 ^ -0x3fe3e10b72080e1bL) + 0x7088e1ab30b7a178L;
                        continue;
                     }

                     value4 = (value2 ^ 0x3b98d44f62bc01a3L) + 0x7d6136ef275c648L;
                     value5 = (value3 ^ 0x5b011aea9bbe3ec5L) - 0x7c2218e9897bb31eL;
                  }

                  if (count6 != 0) {
                     return this.byteFlagArray;
                  }

                  value2 = (value4 ^ -0x3650397fbf7fe7d6L) - 0x145f062368eb2b39L;
                  value3 = (value5 ^ 0x5931bd8483e82d24L) - 0x28ba9c6ada674a13L;
                  continue;
               }

               if (count6 >= 16) {
                  long value8 = (value2 ^ 0x410d8352f8dc68aeL) + 0x55c9bfeb973386daL;
                  long value9 = (value3 ^ 0x602a33bf2e967173L) - 0x7f69c395448c719aL;
                  if (this.byteFlagArray != null) {
                     value2 = (value8 ^ 0xe27836ddfaf2f31L) - 0x5586813fb531b2d2L;
                     value3 = (value9 ^ -0x2cb302d84a3ce4fbL) - 0x4e7108502fdf13c5L;
                  } else {
                     value2 = (value8 ^ -0x18149f29c7085a4fL) - 0x62e2f583a4df50b4L;
                     value3 = (value9 ^ 0x70a87a5d3e1c403bL) + 0x3f6dd8fb39b9a7cdL;
                  }
               } else {
                  value2 = (value2 ^ 0x1c3926dd5af1373L) - 0x5218795082132f43L;
                  value3 = (value3 ^ 0x2ca804a95539df6fL) + 0x1e7de2759939d817L;
               }
            }
         }

         if (count6 >= 16) {
            long value10 = (value2 ^ 0x5f7a018b38e4b45aL) - 0x17d6dd65fa961a38L;
            long value11 = (value3 ^ -0x5b91e096ad9a138L) - 0x726342b91d008327L;
            int[] countArray9 = this.countArray;
            this.applyIndexArray(countArray9, countArray8);
            value2 = (value10 ^ -0x26fb0853c48ff2c2L) - 0x28b9d7886cfe5d0dL;
            value3 = (value11 ^ -0x5f1dd54caa104f3aL) + 0x599929b01a6fcb99L;
         } else {
            value2 = (value2 ^ 0x32a6573b710e0647L) + 0xc273728cfea5faL;
            value3 = (value3 ^ -0x3565779aa576abeeL) - 0xb5ab7826716b0fdL;
         }
      }
   }

   public byte[] resolveByteArray(byte[] byteValueArray, byte[] byteValueArray2) {
      return this.resolveByteArrayByByteArray(byteValueArray, byteValueArray2, (byte[])null);
   }

   private void applyIndexArray(int[] countArray8, int[] countArray9) {
      int count6 = countArray9.length / 16 - 1;

      for (int index = 0; index < 16; index++) {
         countArray8[index] ^= countArray9[index];
      }

      for (int index2 = 1; index2 < count6; index2++) {
         for (int index3 = 0; index3 < 16; index3++) {
            countArray8[index3] = countArray6[countArray8[index3]];
         }

         int count7 = countArray8[1];
         countArray8[1] = countArray8[5];
         countArray8[5] = countArray8[9];
         countArray8[9] = countArray8[13];
         countArray8[13] = count7;
         int count8 = countArray8[2];
         int count9 = countArray8[6];
         countArray8[2] = countArray8[10];
         countArray8[6] = countArray8[14];
         countArray8[10] = count8;
         countArray8[14] = count9;
         int count10 = countArray8[15];
         countArray8[15] = countArray8[11];
         countArray8[11] = countArray8[7];
         countArray8[7] = countArray8[3];
         countArray8[3] = count10;

         for (int index4 = 0; index4 < 4; index4++) {
            int count11 = countArray8[index4 * 4];
            int count12 = countArray8[index4 * 4 + 1];
            int count13 = countArray8[index4 * 4 + 2];
            int count14 = countArray8[index4 * 4 + 3];
            countArray8[index4 * 4] = countArray7[2][count11] ^ countArray7[3][count12] ^ count13 ^ count14;
            countArray8[index4 * 4 + 1] = count11 ^ countArray7[2][count12] ^ countArray7[3][count13] ^ count14;
            countArray8[index4 * 4 + 2] = count11 ^ count12 ^ countArray7[2][count13] ^ countArray7[3][count14];
            countArray8[index4 * 4 + 3] = countArray7[3][count11] ^ count12 ^ count13 ^ countArray7[2][count14];
         }

         int count15 = index2 * 16;

         for (int index5 = 0; index5 < 16; index5++) {
            countArray8[index5] ^= countArray9[count15 + index5];
         }
      }

      for (int index6 = 0; index6 < 16; index6++) {
         countArray8[index6] = countArray6[countArray8[index6]];
      }

      int count16 = countArray8[1];
      countArray8[1] = countArray8[5];
      countArray8[5] = countArray8[9];
      countArray8[9] = countArray8[13];
      countArray8[13] = count16;
      int count17 = countArray8[2];
      int count18 = countArray8[6];
      countArray8[2] = countArray8[10];
      countArray8[6] = countArray8[14];
      countArray8[10] = count17;
      countArray8[14] = count18;
      int count19 = countArray8[15];
      countArray8[15] = countArray8[11];
      countArray8[11] = countArray8[7];
      countArray8[7] = countArray8[3];
      countArray8[3] = count19;
      int count20 = count6 * 16;

      for (int index7 = 0; index7 < 16; index7++) {
         countArray8[index7] ^= countArray9[count20 + index7];
      }
   }

   private static String resolveText(byte[] byteValueArray) {
      int length = byteValueArray.length;
      int byteValue = 0;
      return resolveText(byteValueArray, byteValue, length);
   }
}
