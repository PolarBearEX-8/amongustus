package client.util;

public class EnhancedLongEntry {
   public long timestamp3;
   public long timestamp2;

   public long computeTimestamp(int count, int count2) {
      long value = 0L;
      long value2 = 0L;
      long value3 = 0L;
      int count3 = 0;
      int count4 = 0;
      value = this.timestamp2;
      value2 = this.timestamp3;
      value2 ^= value2 << 23;
      value3 = value + value2;
      this.timestamp2 = value2 ^ value ^ value2 >>> 18 ^ value >>> 5;
      this.timestamp3 = value;
      value3 ^= value3 >>> 33;
      value3 *= 0x2545f4914f6cdd1dL;
      value3 ^= value3 >>> 33;
      value3 *= 0x5851f42d4c957f2dL;
      value3 ^= value3 >>> 33;
      if (count == 0) {
         return value3;
      } else if (count == 1) {
         return (long)((int)value3);
      } else if (count != 2) {
         return count == 3 ? (long)((double)(value3 >>> 11) * 1.110223E-16F) : value3;
      } else if (count2 <= 0) {
         throw new IllegalArgumentException("Bound must be positive");
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

   public void applyByteArray(byte[] byteValueArray) {
      int count = 0;
      int count2 = 0;
      long value = 0L;
      long value2 = 0L;
      long value3 = 0L;
      int count3 = 0;
      long value4 = 0x4e5d3918f78d162L;
      long value5 = -0x410b1e575c4c1ad2L;

      while (true) {
         int count4 = (int)(value4 % 20L);
         if (count4 == 17) {
            value3 >>= 8;
            value4 = (value4 ^ -0x2896aa0750a5ce40L) - 0x7655f15134ba1fbaL;
            value5 = (value5 ^ 0x6d862260a3a56e3fL) + 0x28b78ddab3925968L;
         } else if (count4 != 0) {
            if (count4 != 1) {
               if (count4 != 15) {
                  if (count4 == 2) {
                     value3 ^= value3 >>> 33;
                     value4 = (value4 ^ 0x39d689839842810L) + 0x11a6a451ea3e78c1L;
                     value5 = (value5 ^ -0x4a8b8eb89c3c6544L) + 0x1d718ffaa8796edaL;
                     continue;
                  }

                  long value6;
                  long value7;
                  if (count4 != 4) {
                     if (count4 == 9) {
                        this.timestamp3 = value;
                        value4 = (value4 ^ -0x3d972e024a5d3b41L) + 0x7f07adb183e75b3fL;
                        value5 = (value5 ^ 0x5704ba0caf6868c2L) - 0x3eb5183cd7710dd3L;
                        continue;
                     }

                     if (count4 == 14) {
                        count = 0;
                        value4 = (value4 ^ 0x3a78c048d5d94b6L) + 0x60805257222a4ae4L;
                        value5 = (value5 ^ -0x254cd5d2aeed901aL) - 0x7cefbd605fbacb69L;
                        continue;
                     }

                     if (count4 != 19) {
                        if (count4 == 11) {
                           value3 *= 0x5851f42d4c957f2dL;
                           value4 = (value4 ^ 0x25ce252df5ff1a8L) + 0xdabcf678914be31L;
                           value5 = (value5 ^ 0x2a6b89c9aef6d00bL) + 0x46503eb537d390cdL;
                           continue;
                        }

                        if (count4 == 8) {
                           count2 = byteValueArray.length;
                           value4 = (value4 ^ 0x3d806bb1db69f817L) + 0x1d2105885c9e3e9cL;
                           value5 = (value5 ^ -0x2f33a4b6771b51f2L) - 0xc452ee46f3c2118L;
                           continue;
                        }

                        if (count4 == 13) {
                           value3 = value + value2;
                           value4 = (value4 ^ -0x6f46ba5171275aa8L) + 0x77be71d2b0197840L;
                           value5 = (value5 ^ 0x69142c53b14b24b6L) + 0x3cbad9c912ed0fb1L;
                           continue;
                        }

                        if (count4 == 18) {
                           value2 ^= value2 << 23;
                           value4 = (value4 ^ -0x6beca44b93d7f87dL) - 0x7aefbc948d0883bcL;
                           value5 = (value5 ^ 0x68bfe09a702b21ccL) - 0x7b3c8e05389c1d4bL;
                           continue;
                        }

                        if (count4 == 6) {
                           byteValueArray[count] = (byte)((int)(value3 & 255L));
                           value4 = (value4 ^ 0x783e7ace4d12b150L) + 0x1dcb83678f7effbfL;
                           value5 = (value5 ^ 0x3475ccc74362ca49L) - 0x54b2f5ab2665315fL;
                           continue;
                        }

                        if (count4 == 5) {
                           value3 ^= value3 >>> 33;
                           value4 = (value4 ^ 0x4930f3ab95bc586cL) - 0xaeb4a31fcbe8db6L;
                           value5 = (value5 ^ 0x512aa17b293bc306L) - 0x7ad97b3af5f7ee88L;
                           continue;
                        }

                        if (count4 == 12) {
                           count3 = 0;
                           value4 = (value4 ^ 0x176da9dc451f6edaL) - 0x1fd31fd8cf5f069bL;
                           value5 = (value5 ^ 0x5949b64aa46e368dL) + 0x7b0d66bbe0151411L;
                           continue;
                        }

                        if (count4 == 16) {
                           value = this.timestamp2;
                           value4 = (value4 ^ 0x17bd4b139f35d646L) - 0x4c66cc6b7e7df44L;
                           value5 = (value5 ^ -0x63df3354d27fdc94L) + 0x6dad8c1b023f4e9bL;
                           continue;
                        }

                        if (count4 == 3) {
                           value3 *= 0x2545f4914f6cdd1dL;
                           value4 = (value4 ^ 0x1980dcf523e44cbbL) - 0x4a1f4a812573bb02L;
                           value5 = (value5 ^ -0x450f100ea6a35cebL) + 0x65c56046e2f9b51aL;
                           continue;
                        }

                        if (count4 == 10) {
                           value2 = this.timestamp3;
                           value4 = (value4 ^ 0x2e68beb9b5df73adL) + 0x47f0c3a5268bf7cfL;
                           value5 = (value5 ^ -0x10ffa71876bf9a4bL) + 0x71fdf67c21a1fd5bL;
                           continue;
                        }

                        count3++;
                        count++;
                        value4 = (value4 ^ 0x418b60a9d3d7a957L) + 0x3793876f57548f3fL;
                        value5 = (value5 ^ 0x531a4fb19e6e14faL) - 0x35a9f8f7342e773eL;
                     }

                     if (count3 < 8) {
                        value4 = (value4 ^ 0x1468dc2ff5513b59L) + 0x55b2ce504dcee2eL;
                        value5 = (value5 ^ 0x1d0176c5c06ab502L) + 0x5392e18d6bf152bfL;
                        continue;
                     }

                     value6 = (value4 ^ -0x70025ae8a2a95013L) - 0x6e39c22ef62a8f6bL;
                     value7 = (value5 ^ -0xdf8b9dfa344e77dL) + 0x7298ed87899f5283L;
                  } else {
                     if (count < count2) {
                        value4 = (value4 ^ 0x42c2da080fd0ed3eL) - 0x446b53e725605ea8L;
                        value5 = (value5 ^ 0x25be2a85e28bbf32L) + 0x3e391285f9d5f040L;
                        continue;
                     }

                     value6 = (value4 ^ -0x8b4d95ffcd1c00L) + 0x7333260a5cbefb13L;
                     value7 = (value5 ^ -0x3acb3abcb7b59573L) - 0x4ef37f2535bcf332L;
                  }

                  value4 = (value6 ^ 0x5ffd0eb18f2ced8cL) + 0x5bc2f184ebf33e88L;
                  value5 = (value7 ^ -0x3aab9f00cde351b5L) - 0x35275d5cb38332f1L;
               }

               if (count >= count2) {
                  return;
               }

               value4 = (value4 ^ 0x2354ec7d9d581285L) - 0x3a1963d069458ec2L;
               value5 = (value5 ^ 0x3b747312467849aeL) - 0x611c2d73c128a0e2L;
            } else {
               this.timestamp2 = value2 ^ value ^ value2 >>> 18 ^ value >>> 5;
               value4 = (value4 ^ 0x29a8ccce4e97ddebL) + 0x47643894c2e12fbL;
               value5 = (value5 ^ -0x5b28290b737802a3L) + 0x4c9a289ac1939e16L;
            }
         } else {
            value3 ^= value3 >>> 33;
            value4 = (value4 ^ -0x39667008a5e5dcc8L) + 0x611c5c3f448bd190L;
            value5 = (value5 ^ 0xd83891f642248b7L) - 0x7d5f93364fd0a73fL;
         }
      }
   }

   public int getCount() {
      byte byteValue = 0;
      byte byteValue2 = 1;
      return (int)this.computeTimestamp(byteValue2, byteValue);
   }

   public int computeCount(int count) {
      byte byteValue = 2;
      return (int)this.computeTimestamp(byteValue, count);
   }

   public void applyByteArrayByByteArray(byte[] byteValueArray) {
      this.applyByteArray(byteValueArray);
   }

   public boolean isActive() {
      byte byteValue = 0;
      byte byteValue2 = 0;
      return (this.computeTimestamp(byteValue2, byteValue) & 1L) != 0L;
   }

   public long getTimestamp() {
      byte byteValue = 0;
      byte byteValue2 = 0;
      return this.computeTimestamp(byteValue2, byteValue);
   }

   public double getValue() {
      byte byteValue = 0;
      byte byteValue2 = 3;
      return (double)this.computeTimestamp(byteValue2, byteValue);
   }

   public EnhancedLongEntry() {
      long timestamp = System.nanoTime();
      long timestamp4 = System.currentTimeMillis();
      long value = Runtime.getRuntime().freeMemory();
      long value2 = Runtime.getRuntime().totalMemory();
      long id = Thread.currentThread().getId();
      long keySalt = timestamp ^ value ^ id;
      long keySalt2 = timestamp4 ^ value2 ^ id << 17;
      long value3 = (keySalt ^ keySalt >>> 33) * 0x2545f4914f6cdd1dL;
      long value4 = (value3 ^ value3 >>> 33) * 0x5851f42d4c957f2dL;
      long keySalt3 = value4 ^ value4 >>> 33;
      long value5 = (keySalt2 ^ keySalt2 >>> 33) * 0x2545f4914f6cdd1dL;
      long value6 = (value5 ^ value5 >>> 33) * 0x5851f42d4c957f2dL;
      long keySalt4 = value6 ^ value6 >>> 33;

      for (int index = 0; index < 10; index++) {
         long timestamp5 = System.nanoTime();
         long keySalt5 = keySalt3 ^ timestamp5;
         long value7 = (keySalt5 ^ keySalt5 >>> 33) * 0x2545f4914f6cdd1dL;
         long value8 = (value7 ^ value7 >>> 33) * 0x5851f42d4c957f2dL;
         keySalt3 = value8 ^ value8 >>> 33;

         for (int index2 = 0; index2 < index * 100; index2++) {
            keySalt4 += keySalt4 << 5;
         }

         long keySalt6 = keySalt4 ^ System.nanoTime();
         long value9 = (keySalt6 ^ keySalt6 >>> 33) * 0x2545f4914f6cdd1dL;
         long value10 = (value9 ^ value9 >>> 33) * 0x5851f42d4c957f2dL;
         keySalt4 = value10 ^ value10 >>> 33;
      }

      Object[] entryArray = new Object[5];

      for (int index3 = 0; index3 < entryArray.length; index3++) {
         entryArray[index3] = new Object();
         keySalt3 ^= (long)entryArray[index3].hashCode();
         keySalt4 ^= (long)(entryArray[index3].hashCode() << index3 * 7);
      }

      for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
         keySalt3 ^= (long)stackTraceElement.hashCode();
         keySalt4 ^= (long)(stackTraceElement.getLineNumber() * 31);
      }

      long value11 = (keySalt3 ^ keySalt3 >>> 30) * 0x14057b7ef767814fL;
      long value12 = (value11 ^ value11 >>> 27) * 0x5851f42d4c957f2dL;
      long keySalt7 = value12 ^ value12 >>> 31;
      long value13 = (keySalt4 ^ keySalt4 >>> 30) * 0x14057b7ef767814fL;
      long value14 = (value13 ^ value13 >>> 27) * 0x5851f42d4c957f2dL;
      long keySalt8 = value14 ^ value14 >>> 31;
      this.timestamp2 = keySalt7;
      this.timestamp3 = keySalt8;

      for (int index4 = 0; index4 < 20; index4++) {
         long timestamp6 = this.timestamp2;
         long timestamp7 = this.timestamp3;
         long keySalt9 = timestamp7 ^ timestamp7 << 23;
         this.timestamp2 = keySalt9 ^ timestamp6 ^ keySalt9 >>> 18 ^ timestamp6 >>> 5;
         this.timestamp3 = timestamp6;
      }
   }
}
