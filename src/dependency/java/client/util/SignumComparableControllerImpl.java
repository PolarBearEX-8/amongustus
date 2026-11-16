package client.util;

import java.util.Random;

public class SignumComparableControllerImpl {
   public static final SignumComparable signumComparable = SignumComparable.resolveSignumComparableByTime(2L);
   public final Random random;

   public SignumComparableControllerImpl() {
      long keySalt = System.currentTimeMillis() ^ Thread.currentThread().getId() ^ Runtime.getRuntime().freeMemory() ^ System.nanoTime();
      this.random = new Random(keySalt);
   }

   public SignumComparable resolveSignumComparable(int count) {
      int count2 = 0;
      Object entry = null;
      count2 = (count + 7) / 8;
      entry = new byte[count2];
      this.applyByteArray((byte[])entry);
      if (count <= 0) {
      }

      return new SignumComparable(1, (byte[])entry);
   }

   private boolean matchesSignumComparable(SignumComparable signumComparable2, int count) {
      SignumComparable[] signumComparableArray = null;
      SignumComparable[] signumComparableArray2 = null;
      int count2 = 0;
      int count3 = 0;
      Object entry = null;
      SignumComparable signumComparable3 = null;
      SignumComparable signumComparable4 = null;
      int count4 = 0;
      SignumComparable signumComparable5 = null;
      SignumComparable signumComparable6 = null;
      int count5 = 0;
      long value = 0x2ef7d9b06506151fL;
      long value2 = -0x787991c613a47b6eL;

      while (true) {
         int count6 = (int)(value % 40L);
         if (count6 == 2) {
            SignumComparable signumComparable7 = signumComparable;
            signumComparable4 = signumComparable4.resolveSignumComparable2(signumComparable7, signumComparable2);
            value = (value ^ 0x788ece82cbf068a3L) - 0x5954dc52549606fbL;
            value2 = (value2 ^ -0x4e8e9a90731abf51L) + 0x1831b6779484095dL;
         } else if (count6 != 35) {
            if (count6 == 36) {
               return false;
            }

            if (count6 == 17) {
               long value3 = (value ^ 0x7d4b91f9c69910eeL) + 0x1ab470f7c698f022L;
               long value4 = (value2 ^ 0x19f324d72e9f3dd6L) - 0x76d1c34e688e44aL;
               value = (value3 ^ 0x68ba1a04978ce39dL) + 0x4c04af289d870f88L;
               value2 = (value4 ^ -0x3d2ca642a97428e6L) - 0x3608deab10056d49L;
            } else if (count6 == 34) {
               signumComparable6 = signumComparable5;
               value = (value ^ -0x22644a16ff85d16fL) + 0x7a14f0eeeba3e01cL;
               value2 = (value2 ^ 0x68291efacfe14e0bL) - 0x6fa1403bb2ca1e0L;
            } else {
               evalBlock:
               if (count6 != 26) {
                  if (count6 == 31) {
                     count2 = 0;
                     value = (value ^ -0x5ba327fc5e6858dfL) - 0x4577f81cf677ab7cL;
                     value2 = (value2 ^ -0x6101adfdddb5d4e0L) + 0x4d107390def8f3d4L;
                     continue;
                  }

                  if (count6 == 38) {
                     SignumComparable signumComparable8 = signumComparable;
                     signumComparable6 = signumComparable6.resolveSignumComparable17(signumComparable8);
                     value = (value ^ 0x4b9b27f53c164483L) + 0x15bd5c8132fb2275L;
                     value2 = (value2 ^ 0xfa27842fe515104L) - 0x36254fe155cc25bdL;
                     continue;
                  }

                  if (count6 == 3) {
                     entry = signumComparableArray2[count3];
                     value = (value ^ -0x1ebf9d3e468f2331L) + 0x3947201ca941381fL;
                     value2 = (value2 ^ -0x3527b29763bfebfdL) - 0x564c40f591d0b889L;
                     continue;
                  }

                  checkBlock: {
                     guardBlock: {
                        searchBlock:
                        if (count6 != 7) {
                           if (count6 == 5) {
                              return false;
                           }

                           if (count6 == 29) {
                              count3 = 0;
                              value = (value ^ -0x170411177d86e72L) - 0x3908a6187bfc0d0eL;
                              value2 = (value2 ^ -0x3462cca1e567a542L) + 0x7cd41cdf22b1ed15L;
                              continue;
                           }

                           if (count6 != 9) {
                              if (count6 == 16) {
                                 if (signumComparable3.computeCountBySignumComparable(signumComparable5) >= 0) {
                                    value = (value ^ -0x466e2b100983da62L) - 0x6c566e61e4490e7dL;
                                    value2 = (value2 ^ 0x486a8078b8f9e0eL) + 0x1fc2b1b1f6e94ad6L;
                                 } else {
                                    value = (value ^ 0x3e56506974ece8fcL) + 0x2a3d0238f846a97fL;
                                    value2 = (value2 ^ -0x5b6186a62aa4f9d1L) + 0x58f7957db64ee59fL;
                                 }
                                 continue;
                              }

                              if (count6 == 39) {
                                 return true;
                              }

                              if (count6 == 30) {
                                 signumComparableArray = new SignumComparable[]{
                                    SignumComparable.resolveSignumComparableByTime(3L),
                                    SignumComparable.resolveSignumComparableByTime(5L),
                                    SignumComparable.resolveSignumComparableByTime(7L),
                                    SignumComparable.resolveSignumComparableByTime(11L),
                                    SignumComparable.resolveSignumComparableByTime(13L),
                                    SignumComparable.resolveSignumComparableByTime(17L),
                                    SignumComparable.resolveSignumComparableByTime(19L),
                                    SignumComparable.resolveSignumComparableByTime(23L),
                                    SignumComparable.resolveSignumComparableByTime(29L)
                                 };
                                 value = (value ^ -0x4f4f69842a12b84dL) - 0x2a6bc9b2dc93c541L;
                                 value2 = (value2 ^ 0x310ea85c21edc820L) + 0x15db5e2116d72527L;
                                 continue;
                              }

                              if (count6 == 22) {
                                 count3++;
                                 value = (value ^ -0x5f11263fe0b5790L) - 0x6a1e82241b3cee29L;
                                 value2 = (value2 ^ -0x739cbfbef53cd0a5L) + 0x7683a9345408147bL;
                                 break checkBlock;
                              }

                              if (count6 != 4) {
                                 if (count6 == 27) {
                                    return true;
                                 }

                                 if (count6 == 23) {
                                    count2 = signumComparableArray2.length;
                                    value = (value ^ -0x4a92d2d3e1bfae4dL) - 0x15f2e19e93e9f007L;
                                    value2 = (value2 ^ 0x1cbf8a905c913065L) - 0x68eea884cdf4485bL;
                                    continue;
                                 }

                                 if (count6 == 11) {
                                    if (!signumComparable2.equals(entry)) {
                                       value = (value ^ -0x2b75ed94b41c7eb6L) + 0x6aaf1dbb4512954bL;
                                       value2 = (value2 ^ -0x2b6dfb6578538439L) + 0x3f40bcf83243b5a5L;
                                       continue;
                                    }

                                    return true;
                                 }

                                 if (count6 == 20) {
                                    signumComparableArray2 = signumComparableArray;
                                    value = (value ^ -0x756a181a3f8e085bL) + 0x62e8ab5ce35c2b66L;
                                    value2 = (value2 ^ -0x32d00c789810061aL) - 0x11c85dee1c2b0edbL;
                                    continue;
                                 }

                                 if (count6 == 1) {
                                    signumComparable3 = (new SignumComparable(signumComparable2.getCount3(), this.random)).resolveSignumComparable2(signumComparable2);
                                    value = (value ^ -0x670928ca065f038eL) + 0x59d92290c5732fd1L;
                                    value2 = (value2 ^ 0x670eb851d381822L) - 0x336d3c58ce116fbdL;
                                    continue;
                                 }

                                 if (count6 != 10) {
                                    if (count6 == 13) {
                                       value = (value ^ -0x597bbcc1e855c155L) - 0x6d4d9f5f8ad0384dL;
                                       value2 = (value2 ^ -0x40c9b1367c3da208L) - 0x57e47c12d034a82fL;
                                       continue;
                                    }

                                    if (count6 == 12) {
                                       SignumComparable signumComparable9 = signumComparable2.resolveSignumComparable2((SignumComparable)entry);
                                       SignumComparable signumComparable10 = SignumComparable.signumComparable5;
                                       if (signumComparable9.equals(signumComparable10)) {
                                          return false;
                                       }

                                       value = (value ^ -0x65c7db43f8c7e4f3L) + 0x4a78652e3345344dL;
                                       value2 = (value2 ^ -0x307906a6f4486a22L) - 0x4efe350ac79dca4bL;
                                       continue;
                                    }

                                    if (count6 == 21) {
                                       break checkBlock;
                                    }

                                    if (count6 == 32) {
                                       count4 = 0;
                                       value = (value ^ 0x189986d281001effL) + 0x41029258ab2770ebL;
                                       value2 = (value2 ^ -0x7d859cad39954b5L) + 0x9702b3d84e81db5L;
                                       continue;
                                    }

                                    if (count6 == 28) {
                                       SignumComparable signumComparable11 = SignumComparable.signumComparable;
                                       if (signumComparable3.computeCountBySignumComparable(signumComparable11) <= 0) {
                                          value = (value ^ 0x3803b4ddd47d9f54L) + 0x35b5ffb4a73e90b1L;
                                          value2 = (value2 ^ 0x552a6e32984af3fdL) - 0x7fb7c239cce1950aL;
                                       } else {
                                          value = (value ^ 0x1bfd47da9b32ec3dL) - 0x1837520e1179ce19L;
                                          value2 = (value2 ^ 0x3b5f1975b5fd000eL) + 0x363cc75ce25272ddL;
                                       }
                                       continue;
                                    }

                                    if (count6 == 15) {
                                       SignumComparable signumComparable12 = SignumComparable.signumComparable;
                                       if (signumComparable2.computeCountBySignumComparable(signumComparable12) > 0) {
                                          value = (value ^ -0x208b7602aa209fb6L) + 0x5b4e7a6ef1ae85c3L;
                                          value2 = (value2 ^ -0x312043ffe1a2f9d6L) + 0x76ddfaa866cf0de2L;
                                          continue;
                                       }

                                       return false;
                                    }

                                    if (count6 == 8) {
                                       if (signumComparable4.equals(signumComparable5)) {
                                          value = (value ^ 0x11db5540b96e1cbbL) - 0x32cc14527aae857eL;
                                          value2 = (value2 ^ 0x6697661c04e5df0bL) - 0x1f8b94d013c0ea0cL;
                                          continue;
                                       }

                                       long value5 = (value ^ -0x71261b61e1df84f6L) + 0x7e6b7b926f19163fL;
                                       long value6 = (value2 ^ 0x66a6ba7912d6986dL) + 0xfae4948871afb57L;
                                       count4++;
                                       value = (value5 ^ 0x1470f9d604656457L) + 0x6a77bdb67218e94L;
                                       value2 = (value6 ^ 0x349cf2face6893c8L) - 0x4c1c50f9b5a426c1L;
                                       break evalBlock;
                                    }

                                    if (count6 == 25) {
                                       SignumComparable signumComparable13 = signumComparable;
                                       SignumComparable signumComparable14 = signumComparable2.resolveSignumComparable2(signumComparable13);
                                       SignumComparable signumComparable15 = SignumComparable.signumComparable5;
                                       if (!signumComparable14.equals(signumComparable15)) {
                                          value = (value ^ 0x68079c6ac0268c6aL) - 0x3f793ee8a39b1325L;
                                          value2 = (value2 ^ 0x319d84fcc29ed6c6L) + 0x57a2b3838026cbdfL;
                                       } else {
                                          value = (value ^ 0x47433acfb9bb5bafL) - 0x457e767283c6a25eL;
                                          value2 = (value2 ^ 0x5e7440bbbdabd17L) - 0x47ea3d8412415c72L;
                                       }
                                       continue;
                                    }

                                    if (count6 == 18) {
                                       SignumComparable signumComparable16 = SignumComparable.signumComparable;
                                       if (signumComparable4.equals(signumComparable16)) {
                                          value = (value ^ -0xf8bab6703daccaL) - 0x26fdcdaad37a2e10L;
                                          value2 = (value2 ^ -0x4175d0705cefee6fL) + 0x7449980caab8e642L;
                                       } else {
                                          value = (value ^ 0x58c00bd596413ebbL) - 0x185b0fd69eb5d21eL;
                                          value2 = (value2 ^ 0x15067842ebe84c33L) + 0x5208f7935a3b83b6L;
                                       }
                                       continue;
                                    }

                                    if (count6 != 14) {
                                       if (count6 != 6) {
                                          if (count6 == 19) {
                                             if (!signumComparable4.equals(signumComparable5)) {
                                                long value7 = (value ^ -0x6ed113f30371f203L) - 0x58bf0af991b8575dL;
                                                long value8 = (value2 ^ 0x709de37d6487bb38L) - 0x6dd6a6d57d7bd904L;
                                                value = (value7 ^ 0xdafbc1e479eb894L) - 0x1b33d30b754fe8bdL;
                                                value2 = (value8 ^ 0x3eeda341e5a4bd74L) + 0x5bcfd0e3ac5f7d3aL;
                                             } else {
                                                value = (value ^ 0x346ad5c8c78f4ea0L) + 0x4c052d4a45466bfeL;
                                                value2 = (value2 ^ -0x38c5c7cb56d8a561L) + 0x46881ff450244697L;
                                             }
                                             continue;
                                          }

                                          if (count6 == 0) {
                                             return false;
                                          }

                                          SignumComparable signumComparable17 = signumComparable;
                                          if (!signumComparable2.equals(signumComparable17)) {
                                             value = (value ^ 0x5500d563ac2d3ba4L) + 0x1a939b0c251545dL;
                                             value2 = (value2 ^ -0x68bde92754c8bd02L) - 0x143424832646eb92L;
                                             continue;
                                          }

                                          return true;
                                       }

                                       SignumComparable signumComparable18 = SignumComparable.signumComparable;
                                       if (!signumComparable4.equals(signumComparable18)) {
                                          value = (value ^ 0x756892947cb19d3aL) - 0x14ec3393845d07dcL;
                                          value2 = (value2 ^ 0x30fc49345cad9cd2L) - 0x3ab2602e0d27cd97L;
                                          continue;
                                       }

                                       return false;
                                    }
                                    break guardBlock;
                                 }

                                 count2++;
                                 value = (value ^ 0x38316a593c7fb124L) - 0x57a5ece8e780bf7fL;
                                 value2 = (value2 ^ 0x27deaffa545d49d9L) + 0x5a53edb22a9c09c4L;
                                 break searchBlock;
                              }
                           } else {
                              value = (value ^ 0x61a8445a5106c40dL) + 0x32d7ea528afdf6f0L;
                              value2 = (value2 ^ 0x37431ad89a4e6218L) - 0x2bac33b3c5bdfd02L;
                           }

                           count5++;
                           value = (value ^ -0x380602d952a74f29L) - 0x455cdc45c74c425dL;
                           value2 = (value2 ^ -0x2ea99f4c16bd59fbL) + 0x4df8ed9f7df58b8fL;
                           break guardBlock;
                        }

                        SignumComparable signumComparable19 = signumComparable;
                        SignumComparable signumComparable20 = signumComparable6.resolveSignumComparable2(signumComparable19);
                        SignumComparable signumComparable21 = SignumComparable.signumComparable5;
                        if (!signumComparable20.equals(signumComparable21)) {
                           long value9 = (value ^ 0x3ef1a5eff94ead98L) + 0x2d0525a4d46da3c3L;
                           long value10 = (value2 ^ 0x16c9cc1621e4bf90L) - 0x545ef4e9fd1b647bL;
                           count5 = 0;
                           value = (value9 ^ -0x3d653e445ec5bef5L) - 0x1f60bf983a5d231bL;
                           value2 = (value10 ^ 0x47f9ceb8703ae7d0L) + 0x2127ae57407253feL;
                        } else {
                           value = (value ^ 0xa306340ca7fa574L) + 0x5f1e66056675443bL;
                           value2 = (value2 ^ 0x2a2f9e5fde0de29eL) + 0x110a94f1a7a64344L;
                        }
                        continue;
                     }

                     if (count5 >= count) {
                        return true;
                     }

                     value = (value ^ -0x6dbc56392fe3db39L) + 0x4493d721c7cfea78L;
                     value2 = (value2 ^ 0x5b7aea5639c3fca6L) - 0x6a2661ca00e4a352L;
                     continue;
                  }

                  if (count3 >= count2) {
                     long value11 = (value ^ -0x607fd7db3482f3a7L) + 0x7a953fb4e33b99b2L;
                     long value12 = (value2 ^ 0x1c6be9d4c1c5033L) - 0x2217a7e8556ad75L;
                     SignumComparable signumComparable22 = SignumComparable.signumComparable;
                     signumComparable5 = signumComparable2.resolveSignumComparable11(signumComparable22);
                     value = (value11 ^ 0x775312c963a53439L) - 0x16970094ab3c0c78L;
                     value2 = (value12 ^ -0x636e52cc3ff4bd93L) + 0x592d376aac5b7db6L;
                  } else {
                     value = (value ^ 0x178246bb72f658a1L) - 0x39e5cf5f908e77c1L;
                     value2 = (value2 ^ -0x17fa941cda103cf6L) - 0xb3425821a0a5d9dL;
                  }
                  continue;
               }

               if (count4 >= count2 - 1) {
                  return false;
               }

               value = (value ^ -0x131e74ad4299a15aL) + 0x76eec1979017b9aeL;
               value2 = (value2 ^ 0x3fb7c24ca63ebb6fL) + 0x3262bb5f0fb8ee3fL;
            }
         } else {
            signumComparable4 = signumComparable3.resolveSignumComparable2(signumComparable6, signumComparable2);
            value = (value ^ -0x1303a7d0cc24f571L) - 0x58b189e50d9a60daL;
            value2 = (value2 ^ 0xffa4ba962dce1c3L) + 0x6c82b3311b326fc6L;
         }
      }
   }

   public SignumComparable resolveSignumComparable(int count, int count2) {
      SignumComparable signumComparable2 = null;
      signumComparable2 = this.resolveSignumComparable(count);
      byte byteValue = 0;
      if (!signumComparable2.checkIndexByIndex(byteValue)) {
         SignumComparable signumComparable3 = SignumComparable.signumComparable;
         signumComparable2 = signumComparable2.resolveSignumComparable5(signumComparable3);
      }

      while (!this.matchesSignumComparable(signumComparable2, count2)) {
         SignumComparable signumComparable4 = signumComparable;
         signumComparable2 = signumComparable2.resolveSignumComparable5(signumComparable4);
      }

      return signumComparable2;
   }

   public void applyByteArray(byte[] byteValueArray) {
      this.random.nextBytes(byteValueArray);

      for (int index = 0; index < byteValueArray.length; index++) {
         byteValueArray[index] ^= (byte)((int)(System.nanoTime() & 255L));
         if (index % 5 == 0) {
            long keySalt = System.nanoTime() ^ Thread.currentThread().getId() << 8;
            byteValueArray[index] ^= (byte)((int)(keySalt & 255L));
         }

         if (index % 10 == 0) {
            try {
               Thread.sleep(0L, 100);
            } catch (InterruptedException interruptedException) {
               Thread.currentThread().interrupt();
            }
         }
      }
   }
}
