package client.util;

public class NdpEPUZews8Manager {
   public static final char[] charCodeArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
   public static final int[] countArray = new int[128];

   private static byte[] resolveByteArray(String text) {
      int count = 0;
      int count2 = 0;
      byte[] byteValueArray = null;
      int count3 = 0;
      int count4 = 0;
      int count5 = 0;
      int count6 = 0;
      char character = 0;
      long value = 0xb9097dc0b3504adL;
      long value2 = -0x357210857395c8cbL;

      while (true) {
         int count7 = (int)(value % 21L);
         if (count7 != 7) {
            if (count7 != 5) {
               evalBlock: {
                  long value3;
                  long value4;
                  checkBlock: {
                     guardBlock: {
                        searchBlock: {
                           if (count7 != 8) {
                              if (count7 == 20) {
                                 count4 = 0;
                                 value = (value ^ -0x7b7e0370a20dcbbeL) - 0x685183ef530afa15L;
                                 value2 = (value2 ^ -0x1d64ae49c1d649f6L) + 0x4ddac2b4295b5542L;
                                 continue;
                              }

                              if (count7 == 12) {
                                 count5 = 0;
                                 value = (value ^ 0x54a4b72f6ab17fd7L) + 0x57769ab61e8924feL;
                                 value2 = (value2 ^ 0x454b6a5055dcf651L) + 0x76b62f592df71d61L;
                                 continue;
                              }

                              if (count7 == 3) {
                                 count--;
                                 break searchBlock;
                              }

                              if (count7 == 1) {
                                 if (count4 < count) {
                                    value = (value ^ 0x7b99e4f035513ff6L) + 0x319ca883ba9eaad4L;
                                    value2 = (value2 ^ -0xd863ee6ea14a15aL) + 0xc75314d6bb0b29cL;
                                    continue;
                                 }

                                 value3 = (value ^ -0x448e27ce6201f698L) + 0x4b893a16608ad71dL;
                                 value4 = (value2 ^ -0x1345e16723f4d99bL) - 0x6b127e04804d58b5L;
                                 break checkBlock;
                              }

                              if (count7 == 11) {
                                 byteValueArray[count3++] = (byte)(count5 >>> 8 & 0xFF);
                                 value = (value ^ 0x7e7237a36414711aL) + 0x38f56735aee16aL;
                                 value2 = (value2 ^ -0x73a35499a35e0cd6L) + 0x32c998494f76879bL;
                                 continue;
                              }

                              if (count7 == 6) {
                                 if (text.charAt(count - 1) == '=') {
                                    value = (value ^ -0x451bdb9094b6de94L) + 0x7ebd60017afcd4f1L;
                                    value2 = (value2 ^ -0x1740e1516d59f587L) - 0x55e6017412330690L;
                                    continue;
                                 }

                                 value = (value ^ -0xb4b3909cd712a72L) + 0x3c8e4278a2c14113L;
                                 value2 = (value2 ^ -0x13c5b125f4a4b384L) - 0x526951a08be8408dL;
                                 break evalBlock;
                              }

                              if (count7 == 4) {
                                 byteValueArray = new byte[count2];
                                 value = (value ^ -0x964af3295454d58L) + 0x431be78f0405bda8L;
                                 value2 = (value2 ^ 0x4b92fbe908601709L) - 0x3ef0f03afcd03bbfL;
                                 continue;
                              }

                              if (count7 == 18) {
                                 count--;
                                 break evalBlock;
                              }

                              if (count7 == 19) {
                                 if (text.charAt(count - 1) == '=') {
                                    value = (value ^ 0x3561e85c712b6851L) - 0x137b8e90ed1d029aL;
                                    value2 = (value2 ^ 0x30176c73bd476c61L) - 0x3df9ace33cc915b6L;
                                    continue;
                                 }

                                 value = (value ^ 0x769f05bc495f33abL) + 0x2c51860eeaf725e0L;
                                 value2 = (value2 ^ -0x1014fa02c749e3d8L) + 0x6d0a2c8f48e61a11L;
                                 break searchBlock;
                              }

                              if (count7 == 13) {
                                 count3 = 0;
                                 value = (value ^ 0x97f62db0925e1fcL) - 0x1dd0aa9d16cc8df0L;
                                 value2 = (value2 ^ -0x39a2a3b00b45ec41L) + 0x6e5f405f57009d9dL;
                                 continue;
                              }

                              if (count7 == 14) {
                                 count6 = 0;
                                 value = (value ^ 0x63cc619cba251d65L) + 0x5a1a9591c0c9c61bL;
                                 value2 = (value2 ^ 0x293a80d8695ff88eL) + 0x54800925c868143L;
                                 continue;
                              }

                              if (count7 == 15) {
                                 value = (value ^ -0x1112dc5175330a2eL) + 0x69ab380f87af0105L;
                                 value2 = (value2 ^ -0x34a0ceb74222c91fL) + 0x59e27a597c28cb76L;
                                 break guardBlock;
                              }

                              if (count7 == 17) {
                                 count = text.length();
                                 value = (value ^ 0x3728092534cb5b2dL) + 0x13cf4a21bc714eeaL;
                                 value2 = (value2 ^ -0x422a90dc770e87beL) + 0x5e3e497a4dfa6e62L;
                                 continue;
                              }

                              if (count7 == 2) {
                                 if (count3 >= count2) {
                                    value = (value ^ -0x7a538ea47066ad3L) + 0x7f1b78472a682ebeL;
                                    value2 = (value2 ^ -0x47712a230f7a4212L) + 0x44f7f100a5aa11e9L;
                                 } else {
                                    value = (value ^ -0x5ce1196e0168d6f1L) - 0x621370faf5a007a4L;
                                    value2 = (value2 ^ -0x5824a75f83a91353L) + 0x7b1544621e06e821L;
                                 }
                                 continue;
                              }

                              if (count7 != 0) {
                                 if (count7 != 16) {
                                    if (count7 != 10) {
                                       character = text.charAt(count4);
                                       value = (value ^ 0x7bb12f8149fd8fc7L) + 0x5cfb3c37151118a5L;
                                       value2 = (value2 ^ -0x49b9d0d3a082fe2dL) + 0x488cdf51c498bf13L;
                                    } else {
                                       byteValueArray[count3++] = (byte)(count5 & 0xFF);
                                       value = (value ^ 0x7960d92d7b878d74L) + 0x2b1f29a1a3118980L;
                                       value2 = (value2 ^ -0x9051e5dd4b67aafL) - 0x67c5d1abf69d7112L;
                                    }
                                    continue;
                                 }
                                 break guardBlock;
                              }
                           } else {
                              count6++;
                              count4++;
                              value = (value ^ 0x4ed8a6db1da87872L) + 0x6ca6023961686981L;
                              value2 = (value2 ^ -0x47810b483e045230L) + 0x73e412768af84748L;
                           }

                           if (count6 < 4) {
                              value = (value ^ -0x518bbb9aaefebd98L) + 0x664662620dfa8070L;
                              value2 = (value2 ^ 0x5508c3bb364fc635L) - 0x37bd6c111041ba91L;
                              continue;
                           }

                           value3 = (value ^ -0x3e26bb7159c3ff49L) - 0x6e94ac11c72bf1bcL;
                           value4 = (value2 ^ 0x1e4ec24a7af10c4L) + 0x347e1cd3954b2444L;
                           break checkBlock;
                        }

                        if (count > 0) {
                           value = (value ^ -0x768f1396ee168160L) + 0x625c33aaf7c05bf6L;
                           value2 = (value2 ^ -0x37131424f024b558L) + 0x672b2c2529af95f5L;
                           continue;
                        }

                        value = (value ^ 0x359f6d38130c8d4fL) - 0x61a4c8a375684062L;
                        value2 = (value2 ^ -0x259ad16f0e717b00L) + 0x6a6049841f6a4754L;
                        break evalBlock;
                     }

                     if (count4 >= count) {
                        return byteValueArray;
                     }

                     value = (value ^ -0x352c981c0ff77d23L) + 0x7de4ef770b77abe0L;
                     value2 = (value2 ^ -0x5858bf563bf09058L) + 0x69e8972518e009e8L;
                     continue;
                  }

                  byteValueArray[count3++] = (byte)(count5 >>> 16 & 0xFF);
                  value = (value3 ^ -0x42ecd040b16f42b5L) - 0x7ce2e1d2f1ec48c9L;
                  value2 = (value4 ^ 0xd14920e27d17a15L) + 0x42b0d8bada9f0d3cL;
                  continue;
               }

               count2 = count * 3 / 4;
               value = (value ^ 0x224141e8c042bb0fL) - 0x1ee7a27d8c1127dbL;
               value2 = (value2 ^ -0x5abfd1c3e7511a1L) + 0x28e9a56dd3845711L;
            } else {
               count5 |= countArray[character] << 18 - 6 * count6;
               value = (value ^ 0x56af2bfb2e0d01adL) + 0xa936e71eaf7b102L;
               value2 = (value2 ^ -0x1c9b3416f0c758d8L) + 0x4e067bc95336d324L;
            }
         } else if (count3 >= count2) {
            value = (value ^ 0xad96338098b5eb9L) + 0x2df3e6126f03f3ceL;
            value2 = (value2 ^ -0x78f4ce94515d398L) + 0x9941854ba7ba92L;
         } else {
            value = (value ^ 0x822d432619f2663L) + 0x5d9c9a492a7ee660L;
            value2 = (value2 ^ -0x6560985914bc40bL) + 0x3ac02fe7daaba710L;
         }
      }
   }

   public static boolean matchesIndexArray(int[][] countMatrix) {
      int count = 0;
      if (countMatrix.length <= 1) {
         return true;
      } else {
         count = countMatrix[0].length;

         for (int index = 1; index < countMatrix.length; index++) {
            if (countMatrix[index].length != count) {
               return false;
            }
         }

         return true;
      }
   }

   public static int[] resolveIndexArray(byte[] byteValueArray, int count) {
      int count2 = count;
      int count3 = 0;
      int byteValue = 0;
      int count4 = 0;
      count3 = 0;
      byteValue = 0;

      do {
         count4 = byteValueArray[count2++] & 255;
         count3 |= (count4 & 127) << byteValue;
         byteValue += 7;
      } while ((count4 & 128) != 0);

      return new int[]{count3, count2};
   }

   public static String resolveText(byte[] byteValueArray, int count, int count2) {
      StringBuilder builder = null;
      int count3 = 0;
      int count4 = 0;
      long value = 0x2f23adea9db3764bL;
      long value2 = 0x5cac60615adaf556L;

      while (true) {
         int count5 = (int)(value % 18L);
         if (count5 == 11) {
            builder.append(charCodeArray[count4 >>> 18 & 63]);
            value = (value ^ -0x722c59bc7f81c64L) + 0x4a6e7b51e99ce670L;
            value2 = (value2 ^ 0x39de097388dadf31L) - 0x6223ec7f8a067911L;
         } else if (count5 != 13) {
            if (count5 != 1) {
               if (count5 != 10) {
                  searchBlock:
                  if (count5 != 12) {
                     if (count5 == 2) {
                        count4 |= byteValueArray[count3 + 2] & 255;
                        value = (value ^ -0x1407be1dfb4c5041L) + 0x2211eecfef0baafaL;
                        value2 = (value2 ^ -0x4049d3f16af6c098L) - 0x61afb1e4a56b88faL;
                        continue;
                     }

                     if (count5 == 14) {
                        builder.append(charCodeArray[count4 >>> 6 & 63]);
                        value = (value ^ -0x7352c31e5e0a252eL) + 0x5b3cb6c742ea5a01L;
                        value2 = (value2 ^ 0x3b0f2b7a0869bcf9L) - 0x36065b5588339fd0L;
                        continue;
                     }

                     if (count5 == 8) {
                        if (count3 + 1 >= count + count2) {
                           value = (value ^ 0x224d2a9f82d11823L) + 0x2d41caff0eb32c49L;
                           value2 = (value2 ^ 0x7b6889a2e72d1011L) - 0x69525c7665cc0243L;
                        } else {
                           value = (value ^ -0x6e6b3e9dc203b129L) - 0x7e98799aef11a5edL;
                           value2 = (value2 ^ -0x63f43adc520c6b2eL) - 0x4c23f7566dcaa388L;
                        }
                        continue;
                     }

                     if (count5 == 16) {
                        count4 |= (byteValueArray[count3 + 1] & 255) << 8;
                        value = (value ^ 0x5a64c9e8cbd7a921L) - 0xabcc892d44bcf7L;
                        value2 = (value2 ^ -0x5e96e86097ac96ffL) + 0x239540f025a67b83L;
                        continue;
                     }

                     if (count5 == 6) {
                        if (count3 + 1 >= count + count2) {
                           long value3 = (value ^ -0x6c21d09b298f2a99L) + 0x7993d12aff2672baL;
                           long value4 = (value2 ^ -0x27f21198092664d2L) - 0x44a7042853a16e52L;
                           builder.append('=');
                           value = (value3 ^ 0x2fc9ce691d2bf867L) + 0x4a18060e671421a9L;
                           value2 = (value4 ^ -0x41f2add9546cbd7bL) + 0x423e9e13929f1932L;
                        } else {
                           value = (value ^ -0x3bd049f194290733L) - 0x277fb1d85019ad63L;
                           value2 = (value2 ^ 0x7518c10801a7cc2L) - 0x5451e6295d447c6dL;
                        }
                        continue;
                     }

                     if (count5 != 17) {
                        if (count5 == 9) {
                           count4 = (byteValueArray[count3] & 255) << 16;
                           value = (value ^ -0x7d1af54ec24acbbaL) + 0x33fc6818048db491L;
                           value2 = (value2 ^ 0x387adb0307e4bd6cL) - 0x514a0c3e5f3bd8faL;
                           continue;
                        }

                        if (count5 == 3) {
                           count4 = 0;
                           value = (value ^ 0x19269c0a4bc4c5cfL) - 0x13016c92637f4d43L;
                           value2 = (value2 ^ 0x1d781f0109def2a2L) - 0x7f771dd2ca8a7713L;
                           continue;
                        }

                        if (count5 == 5) {
                           count3 = count;
                           value = (value ^ 0x5c6a93ffd5bacb30L) + 0x13e880c9c8e0a951L;
                           value2 = (value2 ^ 0x7975be75cf11e1d3L) + 0x46ef493e474b0780L;
                           continue;
                        }

                        if (count5 == 0) {
                           if (count3 + 2 >= count + count2) {
                              value = (value ^ 0xded1095ad31d94fL) - 0x4c9f4758ad2c4ba4L;
                              value2 = (value2 ^ -0x5488fa1088d0ac5aL) + 0x1c806df14bf554aeL;
                           } else {
                              value = (value ^ -0x7095912425aa06beL) + 0x380414c30a7e4bccL;
                              value2 = (value2 ^ 0x60dfbd7c81de094cL) + 0x7545735f2e46fdd6L;
                           }
                           continue;
                        }

                        if (count5 == 7) {
                           builder = new StringBuilder((count2 + 2) / 3 * 4);
                           value = (value ^ -0x62fc42aabc863699L) - 0x6f47e1426638c683L;
                           value2 = (value2 ^ -0x230a867113fc853cL) + 0x6e68c7dc321e0186L;
                           continue;
                        }

                        if (count5 == 4) {
                           value = (value ^ -0x39185843bbc274cbL) - 0x6533cb13726473dL;
                           value2 = (value2 ^ -0xcf14e3b89494950L) + 0x7504fbe118ce523cL;
                           break searchBlock;
                        }
                     } else {
                        value = (value ^ 0x193fe075b023749aL) + 0x378e3401c21cadbcL;
                        value2 = (value2 ^ 0x7814529b3975b67cL) - 0x7fd8624fffba9ad5L;
                     }

                     if (count3 + 2 >= count + count2) {
                        long value5 = (value ^ 0x1ffef62393159997L) + 0x1071937beb03a7eL;
                        long value6 = (value2 ^ 0x6f4d39fc5e9acde3L) - 0x7468ba7ac25991ceL;
                        builder.append('=');
                        value = (value5 ^ 0x2f75710909f5d84eL) + 0x121af0a3fd11896aL;
                        value2 = (value6 ^ -0xce2ca968fed51a1L) + 0x74f2783c1b724995L;
                     } else {
                        value = (value ^ 0x1a5097f058676b64L) + 0xe86e0cc78304daeL;
                        value2 = (value2 ^ 0x620e88d3af114071L) - 0xf006327d5868736L;
                     }
                     continue;
                  }

                  count3 += 3;
                  value = (value ^ -0x426e8f18bca84617L) + 0x6f341ed28d6371adL;
                  value2 = (value2 ^ 0x72e5da98eb538cc6L) - 0x4528b402f6a7186bL;
               }

               if (count3 >= count + count2) {
                  return builder.toString();
               }

               value = (value ^ 0x1a419a8c8f8b28bfL) + 0x4d58d9e4d11214b2L;
               value2 = (value2 ^ -0x1ecf78cdd69944e8L) + 0x6b507fd4ce75f2d4L;
            } else {
               builder.append(charCodeArray[count4 & 63]);
               value = (value ^ 0xdf82317db57988eL) - 0x14c9432f9902d969L;
               value2 = (value2 ^ -0x6fb0161a140a6462L) + 0x1f8b32b34e6d1570L;
            }
         } else {
            builder.append(charCodeArray[count4 >>> 12 & 63]);
            value = (value ^ -0x5d61ad6d9c9d3d3aL) + 0x6c39bee67f63dc31L;
            value2 = (value2 ^ 0x5fdc20559611f569L) - 0x3ba58e9a8001c2e9L;
         }
      }
   }

   public static int computeCount(byte[] byteValueArray, int count, int count2) {
      int count3 = count;

      int count4;
      for (count4 = count2; (count4 & -128) != 0; count4 >>>= 7) {
         byteValueArray[count3++] = (byte)(count4 & 127 | 128);
      }

      byteValueArray[count3++] = (byte)(count4 & 127);
      return count3;
   }

   static {
      int count = 0;
      count = 0;

      while (count < charCodeArray.length) {
         countArray[charCodeArray[count]] = count++;
      }
   }
}
