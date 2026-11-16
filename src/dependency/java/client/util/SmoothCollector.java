package client.util;

public class SmoothCollector {
   public static final char[] charCodeArray = new char[]{
      '0',
      '0',
      '0',
      '0',
      '0',
      '0',
      '0',
      '0',
      '0',
      '0',
      '1',
      '1',
      '1',
      '1',
      '1',
      '1',
      '1',
      '1',
      '1',
      '1',
      '2',
      '2',
      '2',
      '2',
      '2',
      '2',
      '2',
      '2',
      '2',
      '2',
      '3',
      '3',
      '3',
      '3',
      '3',
      '3',
      '3',
      '3',
      '3',
      '3',
      '4',
      '4',
      '4',
      '4',
      '4',
      '4',
      '4',
      '4',
      '4',
      '4',
      '5',
      '5',
      '5',
      '5',
      '5',
      '5',
      '5',
      '5',
      '5',
      '5',
      '6',
      '6',
      '6',
      '6',
      '6',
      '6',
      '6',
      '6',
      '6',
      '6',
      '7',
      '7',
      '7',
      '7',
      '7',
      '7',
      '7',
      '7',
      '7',
      '7',
      '8',
      '8',
      '8',
      '8',
      '8',
      '8',
      '8',
      '8',
      '8',
      '8',
      '9',
      '9',
      '9',
      '9',
      '9',
      '9',
      '9',
      '9',
      '9',
      '9'
   };
   public final char[] charCodeArray3;
   public static final boolean enabled = !SplineParser.class.desiredAssertionStatus();
   public static final char[] charCodeArray2 = new char[]{
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
      '0',
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9'
   };
   public final StringBuilder stringBuilder = new StringBuilder();

   public SmoothCollector() {
      this.charCodeArray3 = new char[19];
   }

   public int computeCount(long value) {
      long value2 = value;
      long value3 = 0L;
      int count = 0;
      int count2 = 0;
      int count3 = 0;
      int count4 = 0;
      long value4 = 0x7b40a8b94037d5L;
      long value5 = -0x45d57c984601fbecL;

      while (true) {
         int count5 = (int)(value4 % 21L);
         if (count5 != 10) {
            if (count5 == 12) {
               return count2;
            }

            if (count5 == 2) {
               count = count4 - count3 * 100;
               value4 = (value4 ^ -0x5e81d273f6431bcaL) + 0x7aa9cef0dd416f1bL;
               value5 = (value5 ^ -0x10fe7c29d25395cL) + 0x5884f859908a1209L;
            } else if (count5 == 5) {
               count2--;
               this.charCodeArray3[count2] = charCodeArray2[count];
               value4 = (value4 ^ 0x6f8b546c778e13ceL) - 0x9a4778ad5fa1e57L;
               value5 = (value5 ^ -0xa84fc13fa86f62eL) - 0x1578b899c62ec22eL;
            } else {
               if (count5 != 19) {
                  if (count5 == 8) {
                     if (value2 >= 0L) {
                        value4 = (value4 ^ 0x134f84424cc9ce0L) - 0x8a241ef591ffaedL;
                        value5 = (value5 ^ -0x4518b1f390f5ba9aL) - 0x7d42e7a35366bfebL;
                        continue;
                     }

                     throw new AssertionError();
                  }

                  if (count5 == 3) {
                     count = (int)(value2 - value3 * 100L);
                     value4 = (value4 ^ 0x2911e80d585e0473L) - 0x5ae4ac4cd01474e8L;
                     value5 = (value5 ^ 0x5565901d3b53a4e4L) - 0x3ca714fcd71ed9b0L;
                     continue;
                  }

                  if (count5 == 18) {
                     if (count4 >= 10) {
                        value4 = (value4 ^ 0x4d6015adeab00c44L) - 0xed60a1793f554e6L;
                        value5 = (value5 ^ 0x5bd3eb519d555e8dL) - 0x3bedb1910b2a5933L;
                        continue;
                     }

                     return count2;
                  }

                  if (count5 == 1) {
                     count4 = count3;
                     value4 = (value4 ^ 0x6a1ea1e3100ebc78L) - 0x51c4fc83f0e7d767L;
                     value5 = (value5 ^ -0x382aa7a1f6cb3626L) + 0x1e4ca5c1bb3d70c8L;
                     continue;
                  }

                  if (count5 != 14) {
                     if (count5 != 13) {
                        if (count5 == 6) {
                           count2--;
                           this.charCodeArray3[count2] = charCodeArray[count];
                           value4 = (value4 ^ 0x71a5b31cde7193bcL) + 0x3e1c6cff382458b6L;
                           value5 = (value5 ^ -0x2f8d0e0d2fd29e3fL) + 0x16d21982c25c5179L;
                           continue;
                        }

                        if (count5 == 7) {
                           count3 = count4 / 100;
                           value4 = (value4 ^ 0x1440dcf62fac500cL) - 0x1d39423d56def4f1L;
                           value5 = (value5 ^ 0x4a918c5b4a5430d9L) - 0x4183db13085aaee2L;
                           continue;
                        }

                        if (count5 == 17) {
                           if (enabled) {
                              value4 = (value4 ^ 0x567d17fc16fbde11L) + 0x1fd2c5aa3ce25031L;
                              value5 = (value5 ^ 0x513cae976a6df711L) - 0x38529df10af917cL;
                           } else {
                              value4 = (value4 ^ 0x184783387d1e9b2aL) + 0x6712e3199d13fb03L;
                              value5 = (value5 ^ -0x25151329eab68aa7L) + 0x7d743607cc89f2c5L;
                           }
                           continue;
                        }

                        if (count5 != 20) {
                           if (count5 == 16) {
                              value2 = value3;
                              value4 = (value4 ^ -0x2cef4af5721a3ae9L) + 0x3fe3fae752890a60L;
                              value5 = (value5 ^ -0x3987e8d5708d77b6L) + 0x58ae66b149dcbc6aL;
                              continue;
                           }

                           if (count5 != 15) {
                              if (count5 == 4) {
                                 count2--;
                                 this.charCodeArray3[count2] = charCodeArray[count];
                                 value4 = (value4 ^ 0x41fa6421f1a8ce0fL) - 0x77f510c63281c7bL;
                                 value5 = (value5 ^ 0x382f709b82c38727L) - 0x49803e6885397dd9L;
                                 continue;
                              }

                              if (count5 == 0) {
                                 count2--;
                                 this.charCodeArray3[count2] = charCodeArray[count4];
                                 return count2;
                              }

                              if (count5 == 9) {
                                 value3 = value2 / 100L;
                                 value4 = (value4 ^ -0xee799f2273fcf59L) + 0x5d41316e6e4e23fbL;
                                 value5 = (value5 ^ 0xa25870b73248bcdL) - 0x24101ccdbd6144abL;
                                 continue;
                              }
                           } else {
                              value4 = (value4 ^ 0x36f851d0d977b90eL) - 0x3bb8eb525dbc66a4L;
                              value5 = (value5 ^ -0x2ccc87fa673e960eL) - 0x2e46fb891c8f2315L;
                           }

                           if (value2 <= 2147483647L) {
                              long value6 = (value4 ^ 0x7da8f13ddbb5285eL) - 0x307576b96f9da34L;
                              long value7 = (value5 ^ -0x2743f73631fcf25bL) - 0x7c4a853931b4e6deL;
                              count4 = (int)value2;
                              value4 = (value6 ^ 0x15a9e5c910d297f0L) + 0x69abb92150d3b38L;
                              value5 = (value7 ^ -0x9922036c056e106L) + 0xf11ffdf4188af15L;
                           } else {
                              value4 = (value4 ^ -0x27a9da5aae026773L) + 0x1aff239ae0467df0L;
                              value5 = (value5 ^ -0x77f4cf04adcd352L) + 0x75398da093ac424dL;
                           }
                           continue;
                        }

                        count2--;
                        this.charCodeArray3[count2] = charCodeArray2[count];
                        value4 = (value4 ^ 0x4b7c8bb8441c7bafL) + 0x1d334a565790cdL;
                        value5 = (value5 ^ -0x27c9028e8f3bc2c8L) + 0x14bcae16ddca3bf8L;
                        continue;
                     }

                     throw new AssertionError();
                  }
               } else {
                  value4 = (value4 ^ -0x4bd4b1a883983ceL) + 0x5b8c804cc449876dL;
                  value5 = (value5 ^ 0x11cbc1c0efb7799eL) + 0xa1e9163e14668c3L;
               }

               if (count4 < 100) {
                  long value8 = (value4 ^ 0x1f1efe72241912c2L) - 0x4137ba19da6ace5dL;
                  long value9 = (value5 ^ 0x2bab4aaeec18efecL) - 0x43c20d7a1ba205adL;
                  count2--;
                  this.charCodeArray3[count2] = charCodeArray2[count4];
                  value4 = (value8 ^ -0x260e3fc458999544L) - 0x707add168db2ccecL;
                  value5 = (value9 ^ 0x2931a0486e22974dL) - 0x24e2df706af95850L;
               } else {
                  value4 = (value4 ^ 0x767449075582b424L) + 0x1c7eba058a978834L;
                  value5 = (value5 ^ 0x6ea25139c36d5f3bL) - 0x601823257cfe1f6cL;
               }
            }
         } else {
            count2 = this.charCodeArray3.length;
            value4 = (value4 ^ 0x6cb05f013eb7110aL) + 0xb8d4a9895e3fb6cL;
            value5 = (value5 ^ -0x4bde3b173cae4793L) - 0x6e033df1cc893cacL;
         }
      }
   }

   public StringBuilder setLength() {
      this.stringBuilder.setLength(0);
      return this.stringBuilder;
   }

   public char[] getCharCodeArray3() {
      return this.charCodeArray3;
   }
}
