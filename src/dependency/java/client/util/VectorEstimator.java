package client.util;

public class VectorEstimator {
   public void applyTimeArray(long[][] valueMatrix) {
      long[][] valueMatrix2 = null;
      int count = 0;
      long value = 0x317b4f01b6ed5191L;
      long value2 = 0x418bea5752902c47L;

      while (true) {
         int count2 = (int)(value % 12L);
         if (count2 != 10) {
            searchBlock: {
               if (count2 != 2) {
                  if (count2 == 8) {
                     count = 0;
                     value = (value ^ -0x743e58f1dec92817L) - 0x45a488b3ef04589fL;
                     value2 = (value2 ^ -0x1668474dbe4d6b35L) - 0x7bbf4780b2c9b304L;
                     continue;
                  }

                  if (count2 == 4) {
                     valueMatrix[0][count] = valueMatrix2[0][count] ^ ~valueMatrix2[1][count] & valueMatrix2[2][count];
                     value = (value ^ -0x59815da393ead2b9L) + 0x43ab448f945847aaL;
                     value2 = (value2 ^ -0x26875e9bc024de1fL) - 0x79472a76087636feL;
                     continue;
                  }

                  if (count2 == 0) {
                     count++;
                     value = (value ^ 0x1cee74821dac4fc7L) + 0x8ba9a0073a2e7acL;
                     value2 = (value2 ^ -0x2db1632dcbf186f4L) + 0x6f906fafdc800f15L;
                     break searchBlock;
                  }

                  if (count2 == 5) {
                     valueMatrix[1][count] = valueMatrix2[1][count] ^ ~valueMatrix2[2][count] & valueMatrix2[3][count];
                     value = (value ^ 0x141faf97b3bc67b3L) + 0x5393452e31759285L;
                     value2 = (value2 ^ 0x5d1623203b5c60ecL) + 0x742ae94ea8f61516L;
                     continue;
                  }

                  if (count2 == 6) {
                     valueMatrix[3][count] = valueMatrix2[3][count] ^ ~valueMatrix2[4][count] & valueMatrix2[0][count];
                     value = (value ^ -0x603c89559cd21248L) + 0x70e09a948d79cfc8L;
                     value2 = (value2 ^ -0x716fb6220589fd16L) + 0xdeff164042c44dbL;
                     continue;
                  }

                  if (count2 != 1) {
                     if (count2 != 3) {
                        if (count2 != 9) {
                           if (count2 != 11) {
                              System.arraycopy(valueMatrix[count], 0, valueMatrix2[count], 0, valueMatrix[count].length);
                              value = (value ^ 0x17ff10a707752316L) + 0x32cedc26c82cbed4L;
                              value2 = (value2 ^ 0x56f13c7410cdaf1eL) - 0x3bb906f808195f96L;
                           } else {
                              valueMatrix[2][count] = valueMatrix2[2][count] ^ ~valueMatrix2[3][count] & valueMatrix2[4][count];
                              value = (value ^ -0x5edba03a9aa99affL) - 0x7187630ca69c5648L;
                              value2 = (value2 ^ -0x727622826433b5bdL) + 0x2ad7f99bbd88bf3cL;
                           }
                        } else {
                           valueMatrix2 = new long[5][5];
                           value = (value ^ 0x56625984118d820dL) - 0x1d1083ed65687b14L;
                           value2 = (value2 ^ 0x4ed1f01f3f4ca554L) + 0x2d06556630fd6d51L;
                        }
                        continue;
                     }
                     break searchBlock;
                  }

                  count++;
                  value = (value ^ -0xb59228bc794df18L) - 0x4436acad74728bd7L;
                  value2 = (value2 ^ 0x2c239b8dacf2bf4dL) + 0x4b3b1a8f1cfbaeebL;
               }

               if (count >= valueMatrix.length) {
                  long value3 = (value ^ 0xc3054f3c93ab9f8L) - 0x3b177315da98acc9L;
                  long value4 = (value2 ^ -0x7b97efcbfcbe3f2dL) + 0x448e4ed873745015L;
                  count = 0;
                  value = (value3 ^ -0x432793ad0d9cd5b4L) - 0x50ed4b5b307c371aL;
                  value2 = (value4 ^ -0x65b7301e279ab9c8L) - 0x5baaeaf4dea607aL;
               } else {
                  value = (value ^ -0x1cca4903593ac9bL) - 0x6c463f1baa48651cL;
                  value2 = (value2 ^ 0x56838b0f67e693c5L) - 0x4543b868fa38c31L;
               }
               continue;
            }

            if (count >= 5) {
               return;
            }

            value = (value ^ 0x2da2680ae9f118c8L) + 0x4fccaf2ecca24049L;
            value2 = (value2 ^ 0x6c1fa69050a14402L) - 0x41521a5ca1522becL;
         } else {
            valueMatrix[4][count] = valueMatrix2[4][count] ^ ~valueMatrix2[0][count] & valueMatrix2[1][count];
            value = (value ^ -0x3278b275fd503af5L) + 0x342fc493061b479fL;
            value2 = (value2 ^ 0x139c58a143cb42e8L) + 0x32b359adea0caebaL;
         }
      }
   }
}
