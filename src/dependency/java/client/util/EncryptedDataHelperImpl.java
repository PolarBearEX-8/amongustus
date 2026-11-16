package client.util;

import java.util.Arrays;

public class EncryptedDataHelperImpl {
   public static final int count = 16;

   public static byte[] resolveByteArray(int count2) {
      int count3 = count2;
      byte[] entry = null;
      if (count2 < 16) {
         count3 = 16;
      }

      entry = new byte[count3];
      (new SignumComparableControllerImpl()).applyByteArray((byte[])entry);
      return (byte[])entry;
   }

   private static void applyByteArray(byte[] byteValueArray, byte[] byteValueArray2, byte[] byteValueArray3) {
      byte[] byteValueArray4 = null;
      int byteValue = 0;
      int count2 = 0;
      int count3 = 0;
      int count4 = 0;
      long value = 0x70165321e70a4497L;
      long value2 = -0x5a24b5f32102d0a4L;

      while (true) {
         while (true) {
            int count5 = (int)(value % 16L);
            if (count5 == 0) {
               byteValue += 16;
               value = (value ^ -0x7329c767b6d44eeaL) + 0x5e3bd98ed6178ee2L;
               value2 = (value2 ^ -0x41a72e5eb1597387L) + 0x7e7de55fdd2f9a28L;
               break;
            }

            if (count5 == 5) {
               byteValue = 0;
               value = (value ^ 0x85b731ccaeaec9dL) - 0x554c7228a08f2320L;
               value2 = (value2 ^ 0x175f156341f38fd0L) - 0x697040c8323409acL;
            } else if (count5 == 2) {
               byteValueArray4[count2] ^= byteValueArray[count2 % byteValueArray.length];
               value = (value ^ -0x4523b7912033da0fL) + 0x774985114378de49L;
               value2 = (value2 ^ 0x2f6874a6f1dfa06bL) - 0x79883cc8c30ffa49L;
            } else {
               if (count5 == 12) {
                  count2++;
                  value = (value ^ -0x7e39e0b02e7fa41dL) + 0x7c0c43c73bfc0b47L;
                  value2 = (value2 ^ -0x59e9dfad2f774eb8L) - 0x2df83fbfa3ebc582L;
               } else {
                  guardBlock: {
                     searchBlock: {
                        if (count5 != 9) {
                           if (count5 == 11) {
                              count3 = 0;
                              value = (value ^ 0x6f1b1bf2aa494717L) - 0x3b40dc780ce30173L;
                              value2 = (value2 ^ -0x7177d3a3d318cc5eL) - 0x10939238372caf98L;
                              continue;
                           }

                           if (count5 == 7) {
                              byteValueArray4 = Arrays.copyOf(byteValueArray2, byteValueArray2.length);
                              value = (value ^ -0x24dabb08ebe5e468L) - 0x3eff24630949169aL;
                              value2 = (value2 ^ -0x585248bf16a7f167L) - 0x1c85ab493c5a41d8L;
                              continue;
                           }

                           if (count5 == 8) {
                              break;
                           }

                           if (count5 == 10) {
                              System.arraycopy(byteValueArray4, 0, byteValueArray3, byteValue, count2);
                              value = (value ^ 0x6bfb68aeb3708cb5L) - 0xd6129811235fcdfL;
                              value2 = (value2 ^ -0x56e932b910f2cae3L) + 0x432db417be25c8f3L;
                              continue;
                           }

                           if (count5 == 6) {
                              break guardBlock;
                           }

                           if (count5 == 14) {
                              count4 = (count3 + 1) % 16;
                              value = (value ^ 0x798f6639de11c2bdL) - 0x524545bafa80a4e6L;
                              value2 = (value2 ^ -0x7b16528463052a59L) + 0x1c7e2f75ac43d6b3L;
                              continue;
                           }

                           if (count5 == 1) {
                              count2 = 0;
                              value = (value ^ 0x5be7b6721b63a1acL) + 0x732ca7e604aee39L;
                              value2 = (value2 ^ 0x5fdd4912d1921eaeL) - 0x59c76ff198f02e09L;
                              continue;
                           }

                           if (count5 == 4) {
                              byteValueArray4[count3] ^= byteValueArray[(count3 + count2) % byteValueArray.length];
                              value = (value ^ 0x5c4510f67161cc00L) - 0x6297ad16ea925535L;
                              value2 = (value2 ^ 0x2b5b75f861516406L) - 0x5302807af33c622cL;
                              continue;
                           }

                           if (count5 != 15) {
                              if (count5 != 3) {
                                 byteValueArray4[count3] = (byte)((byteValueArray4[count3] << 1 | (byteValueArray4[count4] & 128) >>> 7) & 0xFF);
                                 value = (value ^ 0x393f3b664eb39b18L) - 0x464fba1374745b1L;
                                 value2 = (value2 ^ 0x30c4b0c5a442fa2bL) - 0x5b7a854ac60dc37eL;
                                 continue;
                              }
                              break searchBlock;
                           }

                           count3++;
                           value = (value ^ -0x100ca0929932713dL) + 0x17976b811483264dL;
                           value2 = (value2 ^ 0x3d8a42e67ea0d64dL) - 0xb594f9a3f0d8caeL;
                        }

                        if (count3 < 16) {
                           value = (value ^ -0x35a5f4dc8913afb9L) + 0x56c48d8b5ec2c050L;
                           value2 = (value2 ^ 0x11e560811b5c7746L) - 0x6341601da4d97668L;
                           continue;
                        }

                        long value3 = (value ^ -0x7c3dc156950b6363L) - 0x7c2672bae5832595L;
                        long value4 = (value2 ^ 0x3cbf1fe1ac9e2944L) + 0x7353fd94f546b72fL;
                        count2++;
                        value = (value3 ^ -0x4a3703612a567cc5L) - 0x7a1fdd4c7eed33c1L;
                        value2 = (value4 ^ -0x712da5e9dff3fb9fL) - 0x42cf196e053bdcaeL;
                     }

                     if (count2 >= 20) {
                        long value5 = (value ^ -0x6967d0268e11f031L) + 0x566ff5f31cf2bf73L;
                        long value6 = (value2 ^ 0x493069ce434d4ef4L) + 0x190cb62a620516c3L;
                        count2 = Math.min(16, byteValueArray3.length - byteValue);
                        value = (value5 ^ 0x7e7610cd83fd6e20L) - 0x5b3f2e0c765bc0c5L;
                        value2 = (value6 ^ 0x6d64d66d6b17bfe0L) + 0x628adf341c1d635L;
                     } else {
                        value = (value ^ 0x6cfdd8301aebfa19L) - 0x6ec3aac4c1903efL;
                        value2 = (value2 ^ 0x253871cc175650aeL) + 0x3f516e13c78f33c0L;
                     }
                     continue;
                  }
               }

               if (count2 >= 16) {
                  long value7 = (value ^ 0x3c439b74fd30eb62L) - 0x2895c13fa8ab6508L;
                  long value8 = (value2 ^ 0x7009b3d5493babe8L) + 0x2016d2c78b3bb922L;
                  count2 = 0;
                  value = (value7 ^ -0x1eda9f922ce5c309L) - 0x71c7220ac844a308L;
                  value2 = (value8 ^ 0x7126e89a7cb9ccfdL) + 0x143365c0e60a78dL;
               } else {
                  value = (value ^ 0x1cd15beff39cec38L) + 0x19cec21bfb43d0a4L;
                  value2 = (value2 ^ 0x23dc2ad55d1408eL) + 0x2a42eb2e547fae9aL;
               }
            }
         }

         if (byteValue >= byteValueArray3.length) {
            return;
         }

         value = (value ^ 0x61cb90272c3f0638L) - 0x54f8a450a34723cfL;
         value2 = (value2 ^ 0x4338e87104576660L) + 0x6f66bf654e1a03feL;
      }
   }

   public static byte[] resolveByteArrayByByteArray(byte[] byteValueArray, byte[] byteValueArray2) {
      byte[] byteValueArray3 = null;
      int count2 = 0;
      byte[] byteValueArray4 = null;
      byte[] byteValueArray5 = null;
      byte[] byteValueArray6 = null;
      int count3 = 0;
      int count4 = 0;
      byte[] byteValueArray7 = null;
      long value = 0x255f9ca0e20e8e01L;
      long value2 = -0x356961a8ef2f1a97L;

      while (true) {
         int count5 = (int)(value % 26L);
         if (count5 == 20) {
            break;
         }

         if (count5 != 23) {
            if (count5 == 1) {
               count3 = 0;
               value = (value ^ 0x512ecbb7f3025fd5L) - 0x12ff0f2a9cff1709L;
               value2 = (value2 ^ -0x8664cea88e4e3f7L) - 0x36ed8bc5cdd6d0f4L;
            } else {
               long value3;
               long value4;
               guardBlock: {
                  if (count5 != 5) {
                     if (count5 == 13) {
                        System.arraycopy(byteValueArray6, 0, byteValueArray7, 0, byteValueArray7.length);
                        break;
                     }

                     if (count5 != 15) {
                        if (count5 == 17) {
                           byteValueArray6[count3] = (byte)(byteValueArray4[count3] ^ byteValueArray5[count3]);
                           value = (value ^ -0x60da829097ba4524L) - 0x2b6d2beaad3b0c13L;
                           value2 = (value2 ^ -0xc360ed348533ef0L) + 0x1dc8f69f1e1dff18L;
                           continue;
                        }

                        if (count5 == 19) {
                           byteValueArray4 = new byte[count2];
                           value = (value ^ -0x665dffcd450929bfL) + 0x39929dc1aa6a1541L;
                           value2 = (value2 ^ 0x5e4c78b612f12029L) + 0xf6bb52d93f0124eL;
                           continue;
                        }

                        searchBlock:
                        if (count5 != 18) {
                           if (count5 == 0) {
                              byteValueArray6 = new byte[count2];
                              value = (value ^ 0x59ddb6ce50157475L) + 0x49be1a11da149832L;
                              value2 = (value2 ^ 0x2a80cd8d761962a7L) + 0x5d8566070e57f291L;
                              continue;
                           }

                           if (count5 == 2) {
                              byteValueArray3 = new byte[16];
                              value = (value ^ 0x6bdd2d355307fb41L) + 0x19d6217d18ccd5d1L;
                              value2 = (value2 ^ -0x493bf3720a0b705fL) - 0x2dc401bbc2a1a725L;
                              continue;
                           }

                           if (count5 == 10) {
                              applyByteArray(byteValueArray2, byteValueArray3, byteValueArray5);
                              value = (value ^ 0x45db66204a420c9eL) + 0xedc69c79f282004L;
                              value2 = (value2 ^ -0x4e3f9d62fe7aad3aL) - 0x2060420758b566cfL;
                              continue;
                           }

                           if (count5 == 14) {
                              value = (value ^ -0x71830d1c0cb7beebL) - 0x7c0bf36e7106b670L;
                              value2 = (value2 ^ -0x3db4b58ca12e73bfL) + 0x27fbbb64c080968bL;
                              continue;
                           }

                           if (count5 != 24) {
                              if (count5 == 8) {
                                 value3 = (value ^ -0x17220f8350b60a1dL) + 0x6854302ca6c6f21dL;
                                 value4 = (value2 ^ -0x3820639fe5dfbcefL) - 0x3acd9c2005dfbef1L;
                                 break guardBlock;
                              }

                              if (count5 == 6) {
                                 byteValueArray7 = new byte[byteValueArray6.length - count3];
                                 value = (value ^ -0x65d7cc5aa7be1eb1L) + 0x3db5afee442d2a96L;
                                 value2 = (value2 ^ 0x1027640abb0df03cL) - 0x2b93dcfcac468524L;
                                 continue;
                              }

                              if (count5 == 25) {
                                 count2 = byteValueArray.length - 16;
                                 value = (value ^ 0x6a1ccf40e011a880L) + 0x6a6e709e1857189cL;
                                 value2 = (value2 ^ 0x55e1e76707aacbc7L) + 0x428a4ceef5044644L;
                                 continue;
                              }

                              if (count5 == 9) {
                                 if (byteValueArray.length >= 32) {
                                    value = (value ^ 0x3819c768b4e636deL) + 0x2d7be52bd7b4763bL;
                                    value2 = (value2 ^ -0x72ef351a6ce6461aL) + 0x650c9c47330b2e98L;
                                 } else {
                                    value = (value ^ 0x50a7dee64043e5d5L) - 0xd1cb24243337ad3L;
                                    value2 = (value2 ^ -0x212ef557b9f12d00L) - 0x3dbe50baf5d74592L;
                                 }
                                 continue;
                              }

                              if (count5 != 16) {
                                 if (count5 == 11) {
                                    throw new IllegalArgumentException("Invalid encrypted data");
                                 }

                                 if (count5 != 21) {
                                    if (count5 != 7) {
                                       if (count5 != 12) {
                                          if (count5 != 3) {
                                             System.arraycopy(byteValueArray, 0, byteValueArray3, 0, 16);
                                             value = (value ^ -0xd37dfe03f0bc21fL) - 0x61bc150687c97a6eL;
                                             value2 = (value2 ^ -0x253825c1a7f702dL) + 0x2b00a42b7838924L;
                                          } else {
                                             System.arraycopy(byteValueArray, 16, byteValueArray4, 0, count2);
                                             value = (value ^ -0x6b2a146c2a622febL) + 0x560d64d86a7d1276L;
                                             value2 = (value2 ^ -0x7cf3de3faaeb67b6L) - 0x62b4363d071055feL;
                                          }
                                       } else {
                                          byteValueArray5 = new byte[count2];
                                          value = (value ^ 0x258fc1928b20a548L) - 0x27ac3e7849fdb7f8L;
                                          value2 = (value2 ^ 0x5037a9b03dd492a6L) - 0x78a20d5996e139aaL;
                                       }
                                       continue;
                                    }

                                    if (count3 > 0) {
                                       value = (value ^ 0x6a5203aba977bf04L) + 0x4fddba33021c1deL;
                                       value2 = (value2 ^ 0x4e34f1ee72c857dcL) - 0x34a6f3de515d33a5L;
                                       continue;
                                    }

                                    return byteValueArray6;
                                 }
                                 break searchBlock;
                              }

                              if ((byteValueArray6[count4] & 255) != count3) {
                                 return byteValueArray6;
                              }

                              long value5 = (value ^ 0x24cfdd5df33039d2L) - 0x13a3551bf7a50a58L;
                              long value6 = (value2 ^ 0x5ff1b1a97d7df4a0L) + 0x419cbafa7d72f26eL;
                              count4++;
                              value = (value5 ^ -0x152ae93eb105d643L) - 0x5611995d4abc2bcfL;
                              value2 = (value6 ^ 0xe626d296cfc3fa3L) - 0x5953c058c594cc98L;
                           }

                           if (count4 < byteValueArray6.length) {
                              value = (value ^ 0x48471d2d782dc09L) - 0x104a64b97b37363dL;
                              value2 = (value2 ^ 0x1734d6d27c082138L) + 0x62ba7c1a1289d22fL;
                              continue;
                           }

                           value3 = (value ^ 0x3b3488cbf76c0ff3L) - 0x1eb5cba6c145c60dL;
                           value4 = (value2 ^ -0x46683d21aba39271L) - 0x50376d46eb339a54L;
                           break guardBlock;
                        } else {
                           count3++;
                           value = (value ^ 0x6fa605908c82e264L) + 0x3c7eb5d722afb37L;
                           value2 = (value2 ^ -0x4c9d54cb300326b7L) + 0x11fa011b94a43e62L;
                        }

                        if (count3 >= count2) {
                           long value7 = (value ^ 0x7a4c423c1cbb1e20L) - 0x73225c9e15daef3L;
                           long value8 = (value2 ^ 0x4a810b7ead1f3b82L) + 0x57e7605196829fd9L;
                           count3 = byteValueArray6[byteValueArray6.length - 1] & 255;
                           value = (value7 ^ -0x560a654dc5ad179eL) + 0x4ba9a4dc9a749a93L;
                           value2 = (value8 ^ 0x239722956e803c68L) - 0x40a170fdda6185b9L;
                        } else {
                           value = (value ^ 0x78cfcd6b5dc1b1ecL) - 0x661c9418dceae850L;
                           value2 = (value2 ^ 0x7da0f085c3655091L) - 0x60a5f804f957342L;
                        }
                        continue;
                     }
                  } else if (count3 <= 16) {
                     value = (value ^ 0x29d3a41198ad0d69L) - 0x1215e79c62a830c6L;
                     value2 = (value2 ^ -0x6936cd351f75169cL) - 0x2b1474c855a6ee5fL;
                     continue;
                  }

                  return byteValueArray6;
               }

               value = (value3 ^ -0x2b4a5233b4056be8L) + 0x5350484f6c601fb8L;
               value2 = (value4 ^ -0x5d284b64d834bd50L) - 0x4b4704a18ce0bb2eL;
            }
         } else {
            count4 = byteValueArray6.length - count3;
            value = (value ^ 0x50ce8057e30e001aL) - 0x41fec46d37acfd5L;
            value2 = (value2 ^ -0x13f1a189755e9294L) - 0x42a5bf3e9979ee05L;
         }
      }

      return byteValueArray7;
   }

   public static byte[] resolveByteArray(byte[] byteValueArray, byte[] byteValueArray2) {
      byte[] byteValueArray3 = null;
      int count2 = 0;
      byte[] byteValueArray4 = null;
      int count3 = 0;
      byte[] byteValueArray5 = null;
      int count4 = 0;
      byte[] byteValueArray6 = null;
      long value = 0x7bebbd18615d6487L;
      long value2 = -0x426a01fa1dbdd937L;

      while (true) {
         int count5 = (int)(value % 20L);
         if (count5 == 8) {
            applyByteArray(byteValueArray2, byteValueArray3, byteValueArray5);
            value = (value ^ -0x6a0da23c4db09f0eL) + 0x30ca6c9b6d9e3b5bL;
            value2 = (value2 ^ 0x49ae95a66a8c92a4L) + 0x3db9bf464452df48L;
         } else if (count5 != 5) {
            if (count5 != 12) {
               searchBlock: {
                  if (count5 != 16) {
                     if (count5 == 10) {
                        System.arraycopy(byteValueArray3, 0, byteValueArray6, 0, byteValueArray3.length);
                        value = (value ^ -0x38c1347fdda3c008L) + 0x4ab6949cf15a6728L;
                        value2 = (value2 ^ 0x3465c6ecde652bf0L) + 0x23c98519defe49e7L;
                        continue;
                     }

                     if (count5 == 1) {
                        System.arraycopy(byteValueArray, 0, byteValueArray4, 0, byteValueArray.length);
                        value = (value ^ -0x1f0ca1172190c78cL) - 0x2973bc0b680b5c32L;
                        value2 = (value2 ^ -0x6b2fa3507c1a8a21L) - 0x69c0ce92b7187ab4L;
                        continue;
                     }

                     if (count5 == 17) {
                        count4++;
                        value = (value ^ -0xa791c13a0db65d7L) - 0x74ec7b3fc025ea01L;
                        value2 = (value2 ^ -0x4beb7ba04505343eL) - 0x488dabf40ab482d8L;
                        break searchBlock;
                     }

                     if (count5 == 9) {
                        count4 = 0;
                        value = (value ^ -0x373854810cf7a3c3L) - 0x76180b4826440fa5L;
                        value2 = (value2 ^ 0x7ea4a70a8ed458c7L) + 0x665f651abe3d80c6L;
                        continue;
                     }

                     if (count5 == 11) {
                        if (byteValueArray2.length >= 16) {
                           value = (value ^ -0x4b8da80e95cf4cf7L) + 0x703e529fca02930aL;
                           value2 = (value2 ^ 0x35d47e27ad78e179L) - 0x7ff2c0ca2f11df8eL;
                        } else {
                           value = (value ^ -0x325f6086aa2bc320L) - 0x646f72d5b34338e0L;
                           value2 = (value2 ^ 0xe3cbe0e72394266L) - 0x6866507ff6b71e60L;
                        }
                        continue;
                     }

                     if (count5 == 14) {
                        byteValueArray4[byteValueArray.length + count3] = (byte)count2;
                        value = (value ^ -0x31181b427d5bd8c5L) - 0x3f38f6e49d5a49b7L;
                        value2 = (value2 ^ 0x74dff34efca89f90L) + 0x3cd5950ad36bb9fbL;
                        continue;
                     }

                     if (count5 == 19) {
                        throw new IllegalArgumentException("Key must be at least 16 bytes");
                     }

                     if (count5 == 0) {
                        byteValueArray4 = new byte[byteValueArray.length + count2];
                        value = (value ^ -0x7ae8704300d48482L) - 0x7f54f8b7d157b0b9L;
                        value2 = (value2 ^ 0xcd1437d3ae00ac3L) - 0x578c8a037802570dL;
                        continue;
                     }

                     if (count5 == 15) {
                        break searchBlock;
                     }

                     if (count5 == 6) {
                        byteValueArray6[byteValueArray3.length + count4] = (byte)(byteValueArray4[count4] ^ byteValueArray5[count4]);
                        value = (value ^ 0x33a6569f8f0479a2L) - 0x15bccb0a46b57eb3L;
                        value2 = (value2 ^ 0x4af2fb1253169507L) - 0x23d29023f98d23deL;
                        continue;
                     }

                     if (count5 != 18) {
                        if (count5 != 4) {
                           if (count5 != 2) {
                              if (count5 != 7) {
                                 if (count5 != 13) {
                                    (new EnhancedLongEntry()).applyByteArrayByByteArray(byteValueArray3);
                                    value = (value ^ -0x5a10307615793ca8L) + 0x6f124ffafa424845L;
                                    value2 = (value2 ^ 0x724997259f60f03eL) + 0x6622b7b01b0af3deL;
                                 } else if (count2 != 0) {
                                    value = (value ^ 0x5a4f282cb6dc81e4L) + 0x45f0e1243bfa5b13L;
                                    value2 = (value2 ^ 0x1712ad6ec8108a46L) + 0x2b8c7213780b7b8bL;
                                 } else {
                                    value = (value ^ -0x5f54c7df41d5ae6aL) + 0x75796bf8c13b380eL;
                                    value2 = (value2 ^ 0x7ea5f3e143621160L) - 0x1099766fa7011187L;
                                 }
                              } else {
                                 count3 = 0;
                                 value = (value ^ -0x7eba9447dc32f1ebL) + 0x54c9ff51d85c97a2L;
                                 value2 = (value2 ^ -0x26c62d1b3b20d43eL) + 0x3f9070d75ec9359aL;
                              }
                           } else {
                              byteValueArray5 = new byte[byteValueArray4.length];
                              value = (value ^ -0x185d13f37649e6e1L) + 0x738e1468ba77ed17L;
                              value2 = (value2 ^ 0xed42cded018fafdL) - 0x4e4a5ea35e6dbe51L;
                           }
                        } else {
                           byteValueArray3 = new byte[16];
                           value = (value ^ 0x478f6e8e968698ffL) - 0x1f832242cb465f24L;
                           value2 = (value2 ^ 0x28d361cf947aff4L) - 0x658094efa48636e4L;
                        }
                        continue;
                     }

                     count3++;
                     value = (value ^ 0x4ca38af2a91e2154L) + 0xc6da773133448feL;
                     value2 = (value2 ^ 0x2ef66167118cbbb6L) - 0x3adfe7577792e2c3L;
                  }

                  if (count3 >= count2) {
                     long value3 = (value ^ -0x38f79916842ee63eL) - 0x9d70a9d4219838cL;
                     long value4 = (value2 ^ -0x6c7ae0d786d0be9fL) - 0x75cd7a9f5fd32470L;
                     byteValueArray6 = new byte[byteValueArray3.length + byteValueArray4.length];
                     value = (value3 ^ 0x145f306be0e02771L) - 0x5a44f2a45219502dL;
                     value2 = (value4 ^ -0x7e88a745dd9af70cL) - 0x18bc01f114f85068L;
                  } else {
                     value = (value ^ -0x2b2d97950c18a9dfL) - 0x206c45b0aa2d8d7bL;
                     value2 = (value2 ^ -0x72a12b6ffff0cd6eL) + 0x5a5f16fbaa398a58L;
                  }
                  continue;
               }

               if (count4 >= byteValueArray4.length) {
                  return byteValueArray6;
               }

               value = (value ^ -0x3abfc9cddea1c69fL) + 0x66ce4b75a784c0d0L;
               value2 = (value2 ^ -0x27e557bf4b2bb13fL) + 0x3a7e910b25a9a736L;
            } else {
               count2 = 16 - byteValueArray.length % 16;
               value = (value ^ -0x1d4f2dd7a792409eL) - 0x186f962acb54b00dL;
               value2 = (value2 ^ -0x1b36c8439a29ff9dL) + 0x3bc1056a704f7afbL;
            }
         } else {
            count2 = 16;
            value = (value ^ -0x2a28145a970197a9L) - 0x2b99306516c3e502L;
            value2 = (value2 ^ -0x4e25828de40ec3f5L) + 0x4298db78a16fcff7L;
         }
      }
   }
}
