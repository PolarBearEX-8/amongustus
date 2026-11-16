package net.minecraft.item;

import client.setting.MovementWeightProperty;
import client.setting.Setting;
import client.util.ContextStringEntry;

public class DynamicItemEntry {
   private static Setting[] settingArray;
   public final MovementWeightProperty movementWeightProperty;

   private boolean matchesTimeArray(long[][] valueMatrix, int count, int count2, int count3) {
      return (valueMatrix[count][count2] & 1L << count3) != 0L;
   }

   public void applyTimeArray(long[][] valueMatrix, byte[] byteValueArray, int count, int count2, int count3) {
      byte byteValue = 0;
      int count4 = 0;
      long value = 0L;
      byte byteValue2 = 0;
      byteValue = 8;
      count4 = count / 8;
      value = valueMatrix[count2][count3];

      for (int index = byteValue - 1; index >= 0; index--) {
         byteValue2 = (byte)((int)(value & 255L));
         byteValueArray[count4 + (byteValue - 1 - index)] = byteValue2;
         value >>= 8;
      }
   }

   public int computeCount(long[][] valueMatrix, byte[] byteValueArray, int count, int count2, int count3, int count4) {
      int count5 = 0;
      int count6 = 0;
      long value = 0xf77a4e7b973b532L;
      long value2 = -0x1ff12b9565b736f6L;

      while (true) {
         int count7 = (int)(value % 7L);
         if (count7 != 6) {
            if (count7 != 1) {
               if (count7 != 2) {
                  if (count7 != 5) {
                     if (count7 != 3) {
                        count6 = 0;
                        value = (value ^ -0x443c70771d5805b0L) + 0x57f0df0924f24a05L;
                        value2 = (value2 ^ 0x6771e73acad61308L) + 0xc6a69674f6321f1L;
                        continue;
                     }

                     count5++;
                     count6++;
                     value = (value ^ 0x7e003ee1ddd93a61L) + 0x334d0cade90a0bc0L;
                     value2 = (value2 ^ -0x305b2c9ccce0bf1dL) - 0x46f0de0dac116f12L;
                  }

                  if (count6 >= 64) {
                     break;
                  }

                  value = (value ^ -0x4fe55aa11cc57af0L) + 0x164e94d36a3e3f4fL;
                  value2 = (value2 ^ 0x5435b6776df4aee6L) - 0x29ae26503db3ab2L;
               } else {
                  count5 = count;
                  value = (value ^ -0x36e678d12afba202L) - 0x7a3c6e5cecb2a9f1L;
                  value2 = (value2 ^ -0x46182f04b75fc52fL) + 0x487e408a42d82a15L;
               }
            } else {
               this.applyByteArray(byteValueArray, count5);
               value = (value ^ -0x56b803467f43c5aL) - 0x75af9c08a904d982L;
               value2 = (value2 ^ -0xf3b10503bfefc2dL) + 0x4357a9e17c0fa9edL;
            }
         } else {
            if (count5 == count2) {
               break;
            }

            long value3 = (value ^ -0x55a753a9207703aL) + 0x61e53d8c6afeba84L;
            long value4 = (value2 ^ 0x684a298ea69f83a5L) - 0x1ab2353f73d76667L;
            if (!this.matchesTimeArray(valueMatrix, count3, count4, count6)) {
               value = (value3 ^ -0x1310555f30d9fc4fL) - 0x3fa67e9299e3c38bL;
               value2 = (value4 ^ 0x3738a7f3b75ac4efL) - 0xfc3e02fa87adf66L;
            } else {
               value = (value3 ^ -0x1b69753879c3f69L) + 0x72373fb10c6c927bL;
               value2 = (value4 ^ -0x5b000e8b26563efaL) - 0xfa4cef75378849fL;
            }
         }
      }

      return count5;
   }

   private void applyByteArray(byte[] byteValueArray, int count) {
      int count2 = count / 8;
   }

   private void applyTimeArray(long[][] valueMatrix, byte[] byteValueArray, int count, int count2) {
      int count3 = 0;
      int count4 = 0;
      int count5 = 0;
      int count6 = 0;
      long value = 0x432325a5e467bedcL;
      long value2 = 0x6344e52aa27572cfL;

      while (true) {
         long value3;
         long value4;
         scanLoop:
         while (true) {
            innerLoop:
            while (true) {
               while (true) {
                  int count7 = (int)(value % 12L);
                  if (count7 != 8) {
                     if (count7 == 1) {
                        if (count2 - count3 >= 64) {
                           value = (value ^ -0x29d1d4ae4d2e6e56L) + 0x632a26de30278011L;
                           value2 = (value2 ^ -0x3adfe675080c98e0L) + 0xc98aa8d7e8e5f21L;
                           continue;
                        }

                        value3 = (value ^ 0x5647246e0e6570c6L) + 0xb9b82dccef2614fL;
                        value4 = (value2 ^ 0x3f5ae5daf3cf0d77L) - 0x3eb586b5750da054L;
                        break scanLoop;
                     }

                     if (count7 != 2) {
                        if (count7 == 4) {
                           count3 = count;
                           value = (value ^ 0x2f3c3c923fa424bbL) - 0x2b371283ec2ba9b2L;
                           value2 = (value2 ^ -0x5f10f23da027165fL) + 0x4f1ad9d380aebb69L;
                           continue;
                        }

                        if (count7 == 3) {
                           count6 = 0;
                           value = (value ^ -0x750ac5c22a394e95L) - 0x2dc228e6f482efe5L;
                           value2 = (value2 ^ 0x15f4b2d70fff4e6L) - 0x8179efa055ef94bL;
                           continue;
                        }

                        if (count7 != 11) {
                           if (count7 == 10) {
                              count5 = 0;
                              value = (value ^ 0x15587239b7589570L) - 0x23d3df97dae864e7L;
                              value2 = (value2 ^ 0x7cabdc9cc584ae55L) + 0x3e57e10da3c24e10L;
                              continue;
                           }

                           if (count7 != 7) {
                              if (count7 == 5) {
                                 count4 = count + count2;
                                 value = (value ^ -0x2908c8d58d3ed5e8L) - 0x58ce706ad759d217L;
                                 value2 = (value2 ^ -0x4d8e127b8c5cbccdL) + 0x9caab811e5b9e44L;
                                 continue;
                              }

                              if (count7 != 6) {
                                 this.applyTimeArray(valueMatrix, byteValueArray, count3, count6, count5);
                                 value = (value ^ -0xfa796afdd0c8331L) + 0x389227dc7d4e6603L;
                                 value2 = (value2 ^ -0x9d644b112eecc40L) - 0x3f6b9a25d25ee7e6L;
                                 continue;
                              }

                              if (count3 == count4) {
                                 return;
                              }

                              long value5 = (value ^ 0x2781073b8a5a505aL) + 0x2b6a1f3b0688a375L;
                              long value6 = (value2 ^ -0x1fce227800472eb0L) - 0x7f7d561b27a07140L;
                              if (count3 % 8 == 0) {
                                 value = (value5 ^ -0x2e616dbafb6ea9ccL) - 0x3665cddf7d7b5d8cL;
                                 value2 = (value6 ^ 0x4a2a70fd7028dc7aL) + 0x9d5c3f88e997f2dL;
                                 continue;
                              }

                              value3 = (value5 ^ 0x1136e794941ae8bbL) - 0x381efbf630db8f0cL;
                              value4 = (value6 ^ -0x2b63f69f6c99568dL) + 0x5950290d03f8d901L;
                              break scanLoop;
                           }
                           break innerLoop;
                        }
                        break;
                     }

                     count3 += 64;
                     value = (value ^ -0x5243c03c739e4126L) - 0x7239f96a3cb5b104L;
                     value2 = (value2 ^ -0x2a6f44595324251eL) + 0x1347c5679c24bd93L;
                  }

                  count6++;
                  value = (value ^ -0x32a13656c5fedc13L) + 0x6ec4c37ecbaaf362L;
                  value2 = (value2 ^ -0x6ca8643ccf2d00e3L) - 0x56ab5873a8e16156L;
                  break;
               }

               if (count6 >= 5) {
                  long value7 = (value ^ -0x258beca8039b8fb6L) - 0x65221f53f58640fdL;
                  long value8 = (value2 ^ 0x168de50ef1f6adbfL) - 0x2aa5a1c0f7c33038L;
                  count5++;
                  value = (value7 ^ -0x7089878830c39eccL) + 0x5f281a9dce9d3e8fL;
                  value2 = (value8 ^ -0x41fb249ef10f4c80L) + 0x379c8f10340eb1ddL;
                  break;
               }

               value = (value ^ 0xdd9e123ff2d6d3eL) + 0x12f5142bf67ed4b5L;
               value2 = (value2 ^ -0x2213857da9b31319L) - 0x5352c2e2910e6513L;
            }

            if (count5 >= 5) {
               return;
            }

            value = (value ^ -0x398ccea1f75d0ba7L) + 0x4717ed778a765df5L;
            value2 = (value2 ^ -0x2d90cb23a77e0686L) - 0x2471bfc520532b33L;
         }

         count3 = this.computeCount(valueMatrix, byteValueArray, count3, count4, count6, count5);
         value = (value3 ^ -0x3c79d40a2e535385L) + 0x37ec125fe70f3c5bL;
         value2 = (value4 ^ -0x59faefe4a2a023bL) + 0x2782bc4b52ea476L;
      }
   }

   public byte[] resolveByteArray(long[][] valueMatrix, byte[] byteValueArray) {
      int count = 0;
      int count2 = 0;
      int count3 = 0;
      count = this.movementWeightProperty.getCount();
      count2 = 0;

      do {
         count3 = Math.min(ContextStringEntry.computeCount(this.movementWeightProperty), count - count2);
         this.applyTimeArray(valueMatrix, byteValueArray, count2, count3);
         count2 += ContextStringEntry.computeCount(this.movementWeightProperty);
      } while (count2 < count);

      return byteValueArray;
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   public DynamicItemEntry(MovementWeightProperty movementWeightProperty2) {
      this.movementWeightProperty = movementWeightProperty2;
   }
}
