package client.util;

import java.io.IOException;
import java.io.InputStream;

public class TJYDmLyRyQInputStream extends InputStream {
   public int count = 0;
   public int count3 = 18;
   public final int[] countArray;
   public boolean enabled;
   public byte[] byteFlagArray;
   public final InputStream inputStream;
   public boolean enabled2;
   public int count2 = -8;
   public final boolean enabled3;

   public TJYDmLyRyQInputStream(InputStream inputStream2, int[] countArray2, long value, boolean flag) {
      this.enabled2 = false;
      this.enabled = false;
      this.byteFlagArray = new byte[1];
      this.inputStream = inputStream2;
      this.countArray = countArray2;
      this.enabled3 = flag;
   }

   @Override
   public int read(byte[] byteValueArray, int count4, int count5) throws IOException {
      int count6 = count4;
      int count7 = count5;
      int count8 = 0;
      int count9 = 0;
      long value = 0x40e829e4cf98fa98L;
      long value2 = -0x42ec95d4c41eca89L;

      while (true) {
         while (true) {
            int count10 = (int)(value % 62L);
            if (count10 != 29) {
               if (count10 == 18) {
                  throw new IOException("Stream is closed");
               }

               if (count10 == 14) {
                  if (count7 >= 0) {
                     value = (value ^ -0x3a6694e5d436b69dL) - 0x4307fbc4560836bdL;
                     value2 = (value2 ^ -0xebc0a000804045L) + 0x545436012f079931L;
                     continue;
                  }
                  break;
               }

               if (count10 == 45) {
                  if (count9 != 61) {
                     long value3 = (value ^ 0x70320341ea01a482L) - 0x52959823d06b36daL;
                     long value4 = (value2 ^ 0x74b1508dfb442601L) - 0x253defb1d673044aL;
                     if ((count9 = this.countArray[count9]) != -1) {
                        value = (value3 ^ -0x5fdfec358ec0f00L) + 0x4f8a9abc19f25805L;
                        value2 = (value4 ^ -0x1d3005b7b15c33dcL) - 0x65cda1d2423f9256L;
                     } else {
                        value = (value3 ^ 0x105ce2a30eeb5c18L) - 0xb73f899d0c41797L;
                        value2 = (value4 ^ -0x36c042b4c1d41f62L) - 0x1f6335cf48adfd8dL;
                     }
                  } else {
                     value = (value ^ 0x38a34aebb3d7307fL) + 0xd246d418d01ece6L;
                     value2 = (value2 ^ -0x1e497cbaf554f5abL) - 0x3c69250285442e36L;
                  }
                  continue;
               }

               if (count10 == 58) {
                  this.count >>= 8;
                  value = (value ^ 0x49652d7dcdf9e9c6L) - 0x930ea08f904bccaL;
                  value2 = (value2 ^ -0x19306cf64e1c898dL) - 0x63a4da75932e23feL;
                  continue;
               }

               if (count10 == 26) {
                  this.count = this.count | count9 << this.count3;
                  value = (value ^ -0x2906b9434762dae7L) + 0x53a3d4b9eb1ada5cL;
                  value2 = (value2 ^ -0x5e877b2b5db098f3L) + 0x291ee45442e41418L;
                  continue;
               }

               if (count10 == 55) {
                  if (!this.enabled2) {
                     value = (value ^ -0x2502ed21ac743f1cL) + 0x14cc5d0b39f47dd1L;
                     value2 = (value2 ^ -0x4804db1646597b72L) + 0x3e982abd5207c8e3L;
                  } else {
                     value = (value ^ -0x6017fcddfe61c158L) - 0x57e4b3450272220fL;
                     value2 = (value2 ^ -0x39520452a81fb207L) - 0x5a2c7a9b77cad5c4L;
                  }
                  continue;
               }

               if (count10 == 34) {
                  if (this.count2 >= 0) {
                     value = (value ^ 0x48897bce09171b42L) - 0x16eb08257a7b0992L;
                     value2 = (value2 ^ 0x2b97da46c8e255e9L) + 0xe2aac53feefbbbdL;
                  } else {
                     value = (value ^ -0x6a4cda0f33c697ddL) - 0x7dffde532f1de266L;
                     value2 = (value2 ^ -0x304cad4359956d1dL) - 0x39d15b16c7b2769L;
                  }
                  continue;
               }

               if (count10 == 47) {
                  byteValueArray[count6++] = (byte)(this.count >> 16);
                  value = (value ^ 0x4a48d6025a1c892L) - 0x28229ae50529fd4cL;
                  value2 = (value2 ^ -0x1ddda85d521e20b2L) - 0x3da267132de820adL;
                  continue;
               }

               if (count10 == 7) {
                  throw new IOException("Illegal base64 ending sequence:" + this.count3);
               }

               if (count10 == 38) {
                  count8 = count6;
                  value = (value ^ -0x4b5de8f655dec539L) - 0x6f70c46dfdb1ebfcL;
                  value2 = (value2 ^ 0x66979e5ac894ffc9L) + 0x7c698bb515a1d993L;
                  continue;
               }

               if (count10 == 3) {
                  if (count9 != -1) {
                     value = (value ^ -0x13cd7a0cc3a8a7a7L) + 0x2b5496533c699ca9L;
                     value2 = (value2 ^ 0x48c3d1b3749cf53L) - 0x5c41fbc34d3a0510L;
                  } else {
                     value = (value ^ 0x7b9652c6ebfd2f0fL) - 0xefb4ead9b522622L;
                     value2 = (value2 ^ -0x7b799481e927d007L) - 0x15f27c93aca17ba5L;
                  }
                  continue;
               }

               if (count10 == 57) {
                  if (this.count3 == 18) {
                     value = (value ^ -0x6309618e4c40be4fL) - 0x6a435f2a259021b2L;
                     value2 = (value2 ^ -0x3202c0c4e4b881b1L) + 0x6c7ee18576e45d1cL;
                  } else {
                     value = (value ^ 0x52d4ef729cdf9604L) + 0x455b4347bee32368L;
                     value2 = (value2 ^ 0x721d84e1dd24727cL) - 0x728fbf0d1595bce9L;
                  }
                  continue;
               }

               if (count10 == 50) {
                  if (count7 != 0) {
                     value = (value ^ 0x33f8e76e6832a5a3L) + 0x144fd7a850843a0L;
                     value2 = (value2 ^ -0x73f046070e2a8343L) - 0x32853ea35d4eb2feL;
                  } else {
                     value = (value ^ 0x3b7abf67283477ceL) + 0x653f86c5e52585a2L;
                     value2 = (value2 ^ 0x112f91d0a58f211eL) - 0x6120211055bbaf2aL;
                  }
                  continue;
               }

               if (count10 != 39) {
                  if (count10 == 1) {
                     this.count3 = 18;
                     value = (value ^ 0x3d4cda3db1a26b3aL) - 0x25e190cf60581b62L;
                     value2 = (value2 ^ 0x66aeb7c923e4a389L) - 0x77516a3a768854baL;
                     continue;
                  }

                  if (count10 != 16) {
                     if (count10 == 22) {
                        this.count = 0;
                        value = (value ^ -0x29fea6e9533d366aL) + 0x703a73b42ab26a1cL;
                        value2 = (value2 ^ 0x432887550fb7d64eL) - 0xe1884ba15427605L;
                        continue;
                     }

                     if (count10 != 56) {
                        if (count10 == 51) {
                           count9 = this.inputStream.read();
                           value = (value ^ -0x371b0c7d0c82a02bL) + 0x48d6c25c0948f6adL;
                           value2 = (value2 ^ 0x16c9638baa9fa13L) + 0x768218a2894f8350L;
                           continue;
                        }

                        if (count10 != 19) {
                           if (count10 == 46) {
                              byteValueArray[count6++] = (byte)(this.count >> 16);
                              value = (value ^ 0x37469c08b5be6ec5L) - 0x5e4136050ce6152L;
                              value2 = (value2 ^ 0x110cf324363611d1L) - 0x2128b2a916ec72ffL;
                              continue;
                           }

                           if (count10 == 27) {
                              this.count2 = 0;
                              value = (value ^ -0x23ca535f6816b73aL) - 0x7764d2b48197818cL;
                              value2 = (value2 ^ 0x6039c3944da8455eL) - 0x9b470b89987360cL;
                              continue;
                           }

                           if (count10 == 42) {
                              throw new IOException("Base64 stream has one un-decoded dangling byte.");
                           }

                           if (count10 == 44) {
                              byteValueArray[count6++] = (byte)(this.count >> this.count2);
                              value = (value ^ 0x19c4c33001f8abebL) - 0x2f33125474f22d5fL;
                              value2 = (value2 ^ 0x2f7c9769574e677cL) - 0x17df8e63eb3fd172L;
                              continue;
                           }

                           if (count10 == 4) {
                              this.enabled2 = true;
                              value = (value ^ -0x27e32a130e719480L) - 0x750e1880fa1204bfL;
                              value2 = (value2 ^ -0x242fd9a1bf3a384fL) - 0x3c9af7541e4a375bL;
                              continue;
                           }

                           guardBlock: {
                              searchBlock:
                              if (count10 != 35) {
                                 if (count10 == 8) {
                                    this.count >>= 8;
                                    value = (value ^ -0x705cbbbfe7bd690fL) - 0x554ae527a9a2a2feL;
                                    value2 = (value2 ^ 0x52354fc35269b306L) + 0x508731aecb21de04L;
                                    continue;
                                 }

                                 if (count10 == 17) {
                                    if (this.count3 == 12) {
                                       value = (value ^ -0x4058dd435c482a7cL) - 0x7617700f51ea9414L;
                                       value2 = (value2 ^ 0x37efb8372ff9c0d3L) + 0x31b81889c3af49b0L;
                                    } else {
                                       value = (value ^ -0x10f2f6296fb112e5L) - 0x292c12c3d8fa2415L;
                                       value2 = (value2 ^ -0x6418e2b800e36655L) + 0x13f01110c9c85813L;
                                    }
                                    continue;
                                 }

                                 if (count10 == 15) {
                                    count7--;
                                    this.count2 -= 8;
                                    value = (value ^ 0x6e5bd90f2892bdaL) - 0x49895642d9c0ffcL;
                                    value2 = (value2 ^ 0x634cc47046b688dcL) + 0x225e9542c191722eL;
                                    continue;
                                 }

                                 if (count10 != 2) {
                                    if (count10 == 30) {
                                       break guardBlock;
                                    }

                                    if (count10 == 23) {
                                       if (this.count2 >= 0) {
                                          value = (value ^ -0x7d1d90093ad564f6L) + 0x6883f04b269f8f01L;
                                          value2 = (value2 ^ 0x186297eef71ff741L) - 0x7a2dc968a19a43fbL;
                                       } else {
                                          value = (value ^ 0x46927ece7a7e3ab5L) + 0x1dd3695ee6455238L;
                                          value2 = (value2 ^ 0x68a11af3208f8b62L) - 0x443d3a09f3b9404fL;
                                       }
                                       continue;
                                    }

                                    if (count10 == 9) {
                                       count7--;
                                       if (this.count3 != 0) {
                                          value = (value ^ -0x58ed4327564cba5eL) + 0x6d6ba0102d4a308bL;
                                          value2 = (value2 ^ -0x6072cc640927b845L) + 0x44839f2c8c11c9e7L;
                                       } else {
                                          value = (value ^ -0x5ea5a78cb9f8af04L) + 0x1c077531a8b11c3fL;
                                          value2 = (value2 ^ -0x4101cb8bc9294242L) - 0x7145fcf8bfad2cb7L;
                                       }
                                       continue;
                                    }

                                    if (count10 == 11) {
                                       if (this.count2 < 0) {
                                          value = (value ^ 0x6f66ba8cd9b0597dL) + 0x14adf1e43256d8a3L;
                                          value2 = (value2 ^ -0x315c2efab0588f35L) - 0x2368e1c4c3b4c840L;
                                       } else {
                                          value = (value ^ -0x4c4f250265011217L) + 0x5bbd880f2babd8ccL;
                                          value2 = (value2 ^ -0x102234e99bc7410L) + 0x1bb3588f58b11dd5L;
                                       }
                                       continue;
                                    }

                                    if (count10 == 52) {
                                       if (count7 <= byteValueArray.length - count6) {
                                          value = (value ^ 0x7bcf43a3a604c906L) - 0xa25cc5f219a33a4L;
                                          value2 = (value2 ^ -0x1b51651c77609b0eL) - 0x63f0658d06c03a6bL;
                                          continue;
                                       }
                                       break;
                                    }

                                    if (count10 == 48) {
                                       count7--;
                                       this.count2 -= 8;
                                       value = (value ^ 0x2c733ec0f8d568c0L) + 0xcc489efd49ff14cL;
                                       value2 = (value2 ^ -0x367674e348845f4fL) - 0x78c3193dbe55db84L;
                                       continue;
                                    }

                                    if (count10 == 24) {
                                       if (this.count2 >= 0) {
                                          return count6 - count8;
                                       }

                                       value = (value ^ -0x1610b0757a541314L) + 0x7d286d25e342e3bfL;
                                       value2 = (value2 ^ -0x71ba3571480784daL) + 0x1b3d03cf195abcf5L;
                                       continue;
                                    }

                                    if (count10 == 37) {
                                       if (this.count3 != 6) {
                                          value = (value ^ -0x1cdc7d5694ac5b1fL) - 0x65d943572ec9e41dL;
                                          value2 = (value2 ^ -0x29027c2ca77b3fb7L) - 0x636d16c36b69aee0L;
                                       } else {
                                          value = (value ^ -0x4a8eb35eb8ac133aL) + 0x2510b9f3cd648beaL;
                                          value2 = (value2 ^ 0x10d4d562ac9e5c39L) - 0x5f0733e02d004121L;
                                       }
                                       continue;
                                    }

                                    if (count10 == 13) {
                                       value = (value ^ 0x4ca88a04ffbf2edeL) + 0x30422eda1e4dae46L;
                                       value2 = (value2 ^ 0x4ff593933127c040L) + 0x3d71d7c078269842L;
                                       break searchBlock;
                                    }

                                    if (count10 == 33) {
                                       value = (value ^ -0xa2b2b9fbfce6186L) - 0x38b21b124af2bf35L;
                                       value2 = (value2 ^ 0x315ac64a5806d000L) + 0x2c5f5a9a8932de04L;
                                       break guardBlock;
                                    }

                                    if (count10 == 21) {
                                       if (this.count2 < 0) {
                                          value = (value ^ 0x32619c6083997b73L) + 0x1abdf7d197fdd1b6L;
                                          value2 = (value2 ^ 0x1839e2efbe6cecdbL) - 0x6a3da9962d05d211L;
                                       } else {
                                          value = (value ^ 0x1661b7226ee8e60aL) + 0x6c262ac2d41ee123L;
                                          value2 = (value2 ^ 0x65ea5f3e6de44ebaL) - 0x68327079513f7ba6L;
                                       }
                                       continue;
                                    }

                                    if (count10 == 61) {
                                       count7--;
                                       if (this.count3 != 0) {
                                          value = (value ^ -0x6aa62392198fe015L) + 0x442bc9a59419e79fL;
                                          value2 = (value2 ^ 0x5c6ec1cd1c2a11c2L) + 0x3e5c3bcde3d29bL;
                                       } else {
                                          value = (value ^ 0x62bf97281ce28aa1L) - 0x459dbefa583f16daL;
                                          value2 = (value2 ^ 0x4b2582683de132b7L) - 0x491e5a8dfb46709cL;
                                       }
                                       continue;
                                    }

                                    if (count10 == 28) {
                                       if (!this.enabled3) {
                                          throw new IOException("Illegal base64 character " + Integer.toString(count9, 16));
                                       }

                                       value = (value ^ 0x1f64eeea32248729L) + 0x19c7f169eca666b0L;
                                       value2 = (value2 ^ 0x22e175269a228c8aL) + 0x1557af792b0f206cL;
                                       continue;
                                    }

                                    if (count10 == 32) {
                                       if (count7 != 0) {
                                          long value5 = (value ^ -0x218bd3a4d7389dd5L) + 0x387b35afe7673616L;
                                          long value6 = (value2 ^ -0x54a4bdf90aed87eaL) - 0x10de2a884a1a50bdL;
                                          byteValueArray[count6++] = (byte)(this.count >> 8);
                                          value = (value5 ^ -0x47dd47076575c690L) + 0x548185ad21b29a36L;
                                          value2 = (value6 ^ -0x68137072966e1eafL) + 0x5bb2556ac616874cL;
                                       } else {
                                          value = (value ^ -0xb85bee860b25f05L) + 0x30f4baa1293fd379L;
                                          value2 = (value2 ^ -0x507a6b8d958ab93eL) + 0x7692d1438526bf27L;
                                       }
                                       continue;
                                    }

                                    if (count10 == 53) {
                                       this.count2 = 16;
                                       value = (value ^ -0x54d36792b08c9c1aL) - 0x74138163fff23938L;
                                       value2 = (value2 ^ 0x8bacd0dab4ff1aaL) + 0x7e80f11ea84178afL;
                                       continue;
                                    }

                                    if (count10 == 5) {
                                       if (this.count3 != 0) {
                                          long value7 = (value ^ -0x447937e671c9c59cL) + 0x5d337cf4a8fe7f4fL;
                                          long value8 = (value2 ^ 0x6be3395eb39da2fL) - 0x16eea34147a55fb9L;
                                          this.count3 -= 6;
                                          value = (value7 ^ 0x553b7093e104c946L) + 0x27cec31cef966faaL;
                                          value2 = (value8 ^ -0x5a8b95f544d7683cL) + 0x43fc438b393f86b1L;
                                       } else {
                                          value = (value ^ -0x32e3a01bd0cea631L) - 0x20273e7abbb5f6c5L;
                                          value2 = (value2 ^ 0x24bf7a77ba29b92bL) + 0x2d2f8385e811462cL;
                                       }
                                       continue;
                                    }

                                    if (count10 == 12) {
                                       this.count2 = 0;
                                       value = (value ^ -0x54dea1952e759f47L) + 0x730f8dbbbeb8f3b4L;
                                       value2 = (value2 ^ -0x1bf4292e88e40a80L) - 0x382bf04314ed94b8L;
                                       continue;
                                    }

                                    if (count10 == 36) {
                                       if (this.count3 == 18) {
                                          value = (value ^ 0x737175910edcc357L) + 0x2482e940a4200136L;
                                          value2 = (value2 ^ -0x7b087b11431d89a4L) - 0x2fa25a5ef09ab483L;
                                       } else {
                                          value = (value ^ -0x73a388470ec96da8L) - 0x5a9fc26539d00d43L;
                                          value2 = (value2 ^ -0x2a98da2fd483a2aaL) + 0x3f796471dc709a7aL;
                                       }
                                       continue;
                                    }

                                    if (count10 == 59) {
                                       byteValueArray[count6++] = (byte)(this.count >> this.count2);
                                       value = (value ^ -0x6cfe7762d46e8149L) - 0x20a7c2dd77d27fafL;
                                       value2 = (value2 ^ -0x5073516e1ab76054L) + 0x3c965ed30e2099fcL;
                                       continue;
                                    }

                                    if (count10 != 54) {
                                       if (count10 == 60) {
                                          return count6 - count8;
                                       }

                                       if (count10 == 0) {
                                          if (count7 != 0) {
                                             long value9 = (value ^ -0x4c5e03c15fb92db4L) - 0x4e2e7d5efc2cea27L;
                                             long value10 = (value2 ^ -0x3e8a433ac49e37c7L) - 0x5e1e5a29a977960L;
                                             byteValueArray[count6++] = (byte)(this.count >> 8);
                                             value = (value9 ^ -0x3220e5d5495beba3L) - 0x30ac4ccb9c80354eL;
                                             value2 = (value10 ^ 0x5db80e697e486c53L) + 0xc22277af7161d5L;
                                          } else {
                                             value = (value ^ 0x21d9586af87548b0L) - 0x1bf62c7f006e505cL;
                                             value2 = (value2 ^ 0x2aa5410e75772b07L) - 0x4707b66a104da799L;
                                          }
                                          continue;
                                       }

                                       if (count10 == 31) {
                                          return -1;
                                       }

                                       if (count10 == 10) {
                                          return -1;
                                       }

                                       if (count10 == 43) {
                                          return count6 - count8;
                                       }

                                       if (count10 == 49) {
                                          if (this.count3 != 12) {
                                             value = (value ^ 0x132a8982346186cbL) - 0x1c9e07cfc45829d0L;
                                             value2 = (value2 ^ -0x7e9f492031257479L) + 0x5b82144c33257b63L;
                                          } else {
                                             value = (value ^ -0x4a4a30fbc98f08bL) - 0x4e68879beef6728eL;
                                             value2 = (value2 ^ 0x6cbb5b535b3d13aeL) - 0x3ec6f525b3929874L;
                                          }
                                          continue;
                                       }

                                       if (count10 == 6) {
                                          this.count = 0;
                                          value = (value ^ -0x26ff56be718dd3a8L) - 0x5ba147a4fcc6a2ddL;
                                          value2 = (value2 ^ 0x5ed93a01b8bdabd8L) + 0x10c0b7d4f8c2adbfL;
                                          continue;
                                       }

                                       if (count6 >= 0) {
                                          value = (value ^ 0x77dd5348a7526bacL) + 0x10f69176a27f08f0L;
                                          value2 = (value2 ^ -0x22da8e91187b9360L) - 0x735a2cf8750c7683L;
                                          continue;
                                       }
                                       break;
                                    }
                                 } else {
                                    value = (value ^ 0x36dd81a9e43a7256L) - 0x3a0e6e0d0d730566L;
                                    value2 = (value2 ^ 0x54b3f976423ef5b2L) - 0x2a3d2f019dd657c1L;
                                 }

                                 value = (value ^ -0x3201fb2501962f90L) + 0x37cdae635d5d9d29L;
                                 value2 = (value2 ^ 0x2e217cbaa7e77a95L) + 0x6c8f542206fdaef6L;
                              }

                              if (count7 > 0) {
                                 value = (value ^ -0x652f80d868e02b8aL) + 0x61a46f02d538118cL;
                                 value2 = (value2 ^ -0x221569392e60f926L) + 0x714317b43c75c902L;
                                 continue;
                              }

                              return count6 - count8;
                           }

                           if (count6 != count8) {
                              value = (value ^ 0xb7bfed35f50500aL) - 0x2ce9712a39881a54L;
                              value2 = (value2 ^ 0x6f7e83ecb58086d0L) + 0x7f0e0ff5f95039c0L;
                           } else {
                              value = (value ^ 0x254cef1debb4bf2eL) - 0x169f4a1b0d8b07f8L;
                              value2 = (value2 ^ 0x23049643aacaf884L) - 0x6d9a4109004c9afaL;
                           }
                           continue;
                        }

                        if (this.inputStream.read() == 61) {
                           value = (value ^ 0x2743a5828b2122cL) + 0x41548a85183f6a36L;
                           value2 = (value2 ^ -0x3e06575e01a5244fL) - 0x4c4c213a029acbeL;
                        } else {
                           value = (value ^ 0x29bfa8acd1ee1a65L) + 0x23b09b65bbbe19d9L;
                           value2 = (value2 ^ 0x4cc02d664a66afd9L) - 0xb24fcdbe58eb8b8L;
                        }
                        continue;
                     }

                     if (!this.enabled) {
                        value = (value ^ 0x37c6066c350e71bcL) - 0x4f2f4c0f712614bbL;
                        value2 = (value2 ^ 0x473d7fd579f2ed4L) + 0x786656dcaa985d91L;
                     } else {
                        value = (value ^ -0x43583a297fb20d11L) + 0x27cb930f6635e67bL;
                        value2 = (value2 ^ 0x17e7ca7766bd775aL) - 0x5f44a1b13fd421c5L;
                     }
                     continue;
                  }

                  if (count7 != 0) {
                     value = (value ^ -0x44eb400c700adae8L) + 0x218ec37a8ff3fc55L;
                     value2 = (value2 ^ -0x1115bdceb88886c6L) - 0x771aa92180bee702L;
                  } else {
                     value = (value ^ 0x51e3395d5fb999fL) + 0x122342ad354b3f6aL;
                     value2 = (value2 ^ 0x5593fbdb84b6ac8eL) - 0x4ac8c9c08d6aad51L;
                  }
                  continue;
               }

               value = (value ^ 0x13f71c4ef8813b89L) - 0x6aad5e900486801L;
               value2 = (value2 ^ -0x158270a7d4275427L) + 0x5e2355c007cf41d4L;
            }

            this.enabled2 = true;
            return count6 - count8;
         }

         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public int read() throws IOException {
      byte[] byteFlagArray2 = this.byteFlagArray;
      return this.read(byteFlagArray2, 0, 1) == -1 ? -1 : this.byteFlagArray[0] & 0xFF;
   }

   @Override
   public void close() throws IOException {
      if (!this.enabled) {
         this.enabled = true;
         this.inputStream.close();
      }
   }

   @Override
   public int available() throws IOException {
      for (long index = 0x6dcc3eb13c37422eL; (int)(index % 3L) == 2; index = (index ^ -0x307731f599c7045eL) + 0x6f740df4a5935ed9L) {
         if (!this.enabled) {
            return this.inputStream.available();
         }
      }

      throw new IOException("Stream is closed");
   }
}
