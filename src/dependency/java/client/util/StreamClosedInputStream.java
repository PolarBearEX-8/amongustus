package client.util;

import java.io.IOException;
import java.io.InputStream;

public class StreamClosedInputStream extends InputStream {
   public int count;
   public int count2 = 0;
   public boolean enabled;
   public int count3 = 18;
   public final int[] countArray;
   public boolean enabled2;
   public final InputStream inputStream;
   public byte[] byteFlagArray;
   public final boolean enabled3;

   public StreamClosedInputStream(long value, InputStream inputStream2, int[] countArray2, boolean flag) {
      this.count = -8;
      this.enabled = false;
      this.enabled2 = false;
      this.byteFlagArray = new byte[1];
      this.inputStream = inputStream2;
      this.countArray = countArray2;
      this.enabled3 = flag;
   }

   @Override
   public int read() throws IOException {
      byte[] byteFlagArray2 = this.byteFlagArray;
      return this.read(byteFlagArray2, 0, 1) == -1 ? -1 : this.byteFlagArray[0] & 0xFF;
   }

   @Override
   public int available() throws IOException {

      for (long index = 0x257f568b3df3948aL; (int)(index % 3L) == 1; index = (index ^ 0x2971f5de9a5b3d76L) + 0x2f1535a277870548L) {
         if (!this.enabled2) {
            return this.inputStream.available();
         }
      }

      throw new IOException("Stream is closed");
   }

   @Override
   public void close() throws IOException {
      if (!this.enabled2) {
         this.enabled2 = true;
         this.inputStream.close();
      }
   }

   @Override
   public int read(byte[] byteValueArray, int count4, int count5) throws IOException {
      int count6 = count4;
      int count7 = count5;
      int count8 = 0;
      int count9 = 0;
      long value = 0x36af8f80a22cb598L;
      long value2 = -0x46975cb1ca657758L;

      while (true) {
         int count10 = (int)(value % 62L);
         if (count10 != 29) {
            checkBlock: {
               guardBlock:
               if (count10 != 5) {
                  if (count10 == 20) {
                     throw new IOException("Illegal base64 ending sequence:" + this.count3);
                  }

                  if (count10 == 11) {
                     this.count = 0;
                     value = (value ^ 0x20d8681185cb227dL) - 0x2316e54a71d54724L;
                     value2 = (value2 ^ -0x59aa223d86482878L) + 0x3bc86824219412fbL;
                     continue;
                  }

                  if (count10 == 33) {
                     return count6 - count8;
                  }

                  if (count10 == 9) {
                     if (count7 != 0) {
                        long value3 = (value ^ -0x9facf95aeee36d6L) - 0x73a709e3f8541684L;
                        long value4 = (value2 ^ 0x5e15d6da412915a2L) - 0x73e334ee2d7bec36L;
                        byteValueArray[count6++] = (byte)(this.count2 >> 8);
                        value = (value3 ^ -0x1d0168ca76d8ee3eL) - 0x32be44585989eaa7L;
                        value2 = (value4 ^ 0x6f8c4a6100f3d1aeL) + 0x388521fd013188a5L;
                     } else {
                        value = (value ^ -0x390f3d3b51c537acL) + 0x4276460fbc2ba92cL;
                        value2 = (value2 ^ -0x75c7570eb89e0758L) - 0x3a3530227b116e36L;
                     }
                     continue;
                  }

                  if (count10 == 35) {
                     return -1;
                  }

                  if (count10 == 43) {
                     this.count2 = 0;
                     value = (value ^ 0x60558f6c85b181dcL) - 0x9eddcae25266db6L;
                     value2 = (value2 ^ -0x5e554b6011edc781L) + 0x578c708c712f2470L;
                     continue;
                  }

                  if (count10 == 26) {
                     if (!this.enabled2) {
                        value = (value ^ 0x352a800df1dec3b9L) + 0x2a476850e49716ecL;
                        value2 = (value2 ^ -0x57e0a5b48a65c34eL) + 0x61aba6a69b10b2ffL;
                     } else {
                        value = (value ^ 0x6eb4d3162003fb13L) - 0x15107ead7d6957a4L;
                        value2 = (value2 ^ -0x4715083d61aaf764L) + 0x14f8ac02598ae1L;
                     }
                     continue;
                  }

                  searchBlock: {
                     if (count10 != 53) {
                        if (count10 == 30) {
                           if (count7 >= 0) {
                              value = (value ^ -0x20ba9db0e37098e6L) + 0x160bb89f38eacd3fL;
                              value2 = (value2 ^ -0x5e68c79fa4a7c11bL) - 0x112911ad4ebcb924L;
                              continue;
                           }

                           throw new IndexOutOfBoundsException();
                        }

                        if (count10 == 32) {
                           count7--;
                           if (this.count3 != 0) {
                              value = (value ^ -0x652a1507ea49cb1cL) - 0x37420545eadceb36L;
                              value2 = (value2 ^ 0x2bada11b3853ce8dL) + 0x1289d7289572b8f4L;
                           } else {
                              value = (value ^ -0x3f923c913133477bL) + 0x4e68b157e8947c6cL;
                              value2 = (value2 ^ -0x50b898fed7418b1fL) - 0x2d726342efca3a17L;
                           }
                           continue;
                        }

                        if (count10 == 7) {
                           this.enabled = true;
                           value = (value ^ 0x7091c7db291c28ddL) + 0x6ef1f15506729aa8L;
                           value2 = (value2 ^ 0x148ce07e29b358d4L) + 0x66de3397dd59f883L;
                           continue;
                        }

                        if (count10 == 18) {
                           count9 = this.inputStream.read();
                           value = (value ^ 0x61cfcf9c6d15d46eL) - 0xa02c1334e2e2d86L;
                           value2 = (value2 ^ 0xdabc4a2a64d3b27L) - 0x5985016134fcd978L;
                           continue;
                        }

                        if (count10 == 21) {
                           if (count9 != 61) {
                              long value5 = (value ^ -0x5df9218f22b0b86bL) - 0x4d4404fb1bece097L;
                              long value6 = (value2 ^ 0x52efa727bb1986bL) - 0x65e74ebad0106cc7L;
                              if ((count9 = this.countArray[count9]) != -1) {
                                 value = (value5 ^ -0x3d2169ab82c8e1a7L) + 0x4c9d64059d8284b9L;
                                 value2 = (value6 ^ -0x6ad8419a42eb5363L) - 0x7a60c3113c191764L;
                              } else {
                                 value = (value5 ^ 0x60c4b13150681323L) - 0x1cafa01b0e1e1d88L;
                                 value2 = (value6 ^ -0x3ba55c3659dc15fL) - 0x51c8c4b1245db0b7L;
                              }
                           } else {
                              value = (value ^ 0x4f8261fb2f0c8339L) + 0x1fe30a5b748f90ecL;
                              value2 = (value2 ^ -0x131fc6ba0258246cL) + 0x4cbf3ff08eb3b71cL;
                           }
                           continue;
                        }

                        if (count10 == 46) {
                           if (this.inputStream.read() == 61) {
                              value = (value ^ 0x644e1500634068dfL) - 0x130f12d474b3da2eL;
                              value2 = (value2 ^ 0x243eff21a91b47b7L) + 0x33b0e91b15b21955L;
                           } else {
                              value = (value ^ 0x3a57a19d8e86a441L) - 0x8df7911d923a681L;
                              value2 = (value2 ^ -0x352582ae4bf98d18L) - 0x63ab89d89a495580L;
                           }
                           continue;
                        }

                        if (count10 == 6) {
                           if (this.count3 != 12) {
                              value = (value ^ 0x53cef0e376cc74c1L) - 0x2211033b3c99822dL;
                              value2 = (value2 ^ -0x97f335631b20e56L) - 0x6f3f45c831bec929L;
                           } else {
                              value = (value ^ 0x7d9e778be72cb872L) + 0x73d629127a981e5L;
                              value2 = (value2 ^ 0x1ed1229a52e86f7fL) + 0x51235aa615a26279L;
                           }
                           continue;
                        }

                        if (count10 == 59) {
                           value = (value ^ -0x291c51bd3819c9dbL) + 0x38b8bfdf601c7236L;
                           value2 = (value2 ^ 0x1051ab582a91f65aL) - 0x4faeda304fbf8ea7L;
                           break searchBlock;
                        }

                        if (count10 == 13) {
                           this.count = 16;
                           value = (value ^ -0x7b7c63ef8af4d5c7L) + 0x226af05c945fb9d0L;
                           value2 = (value2 ^ 0x1613bfec6524fb8eL) - 0x2128b78a095a0525L;
                           continue;
                        }

                        if (count10 == 15) {
                           this.count2 = this.count2 | count9 << this.count3;
                           value = (value ^ -0x70fa24a7ff3595f8L) - 0x3b561a69320f73fbL;
                           value2 = (value2 ^ -0x3b7485d7e33d374bL) - 0x6efaaf32dfc01769L;
                           continue;
                        }

                        if (count10 == 22) {
                           if (count7 != 0) {
                              value = (value ^ -0x51ddce81f5531261L) + 0x225b9b903f4cc48dL;
                              value2 = (value2 ^ -0x637eba8d92c8c649L) - 0x1014cc2ca1d82d32L;
                              continue;
                           }

                           return count6 - count8;
                        }

                        if (count10 == 1) {
                           count8 = count6;
                           value = (value ^ 0x5b156a8cd0998607L) + 0x66a450190c38bdfL;
                           value2 = (value2 ^ 0x11bf83f663e6286aL) - 0x47f3a5a85689f1bfL;
                           continue;
                        }

                        if (count10 == 16) {
                           break searchBlock;
                        }

                        if (count10 == 17) {
                           byteValueArray[count6++] = (byte)(this.count2 >> 16);
                           value = (value ^ -0x280d635ecb3fa1fL) - 0x16353aeb2b7744cdL;
                           value2 = (value2 ^ 0x349f267bf944485eL) + 0x299055f1a9cf13d3L;
                           continue;
                        }

                        if (count10 == 4) {
                           return count6 - count8;
                        }

                        if (count10 == 42) {
                           if (count7 != 0) {
                              long value7 = (value ^ 0x42976a3c798b4092L) + 0x2a92beb6cf404100L;
                              long value8 = (value2 ^ 0x679cbf428c0e802cL) - 0x178e917565efbea5L;
                              byteValueArray[count6++] = (byte)(this.count2 >> 8);
                              value = (value7 ^ -0x7fed9e2b6e8fdb09L) - 0x6694e59a06a0b48eL;
                              value2 = (value8 ^ 0x5906d482c40a6ee3L) + 0x3207d0181adc5b14L;
                           } else {
                              value = (value ^ 0x4a4408c04ee90728L) + 0x2198ce53a736fa1cL;
                              value2 = (value2 ^ -0x5b334fee258f1dd2L) + 0x9bfd426cb2b0550L;
                           }
                           continue;
                        }

                        if (count10 == 49) {
                           if (!this.enabled) {
                              value = (value ^ 0x194ff6d09b43fb38L) + 0x38c672d2f9078bb6L;
                              value2 = (value2 ^ 0x640c25d86dd88443L) - 0x1a9cf7248c5349c7L;
                           } else {
                              value = (value ^ 0x3df6d47e4c88971fL) + 0x401dbf982cbe874fL;
                              value2 = (value2 ^ -0xe5c9e50162f771dL) + 0x609bf7ab0f46e1efL;
                           }
                           continue;
                        }

                        if (count10 == 51) {
                           if (count6 >= 0) {
                              value = (value ^ 0x75f7f24728fdac89L) + 0x91b7271083ed202L;
                              value2 = (value2 ^ 0x14ccf25564cfaa39L) + 0x53d396ac813faf8eL;
                              continue;
                           }

                           throw new IndexOutOfBoundsException();
                        }

                        if (count10 == 47) {
                           throw new IOException("Base64 stream has one un-decoded dangling byte.");
                        }

                        if (count10 == 50) {
                           byteValueArray[count6++] = (byte)(this.count2 >> 16);
                           value = (value ^ -0x7becdbe50fa98db5L) + 0x3d3080761ab18ee9L;
                           value2 = (value2 ^ 0x6f501db2e98a6643L) - 0xfeea70a61022e4L;
                           continue;
                        }

                        if (count10 == 19) {
                           count7--;
                           if (this.count3 != 0) {
                              value = (value ^ -0x79d5abe84f62e521L) + 0x4fb2e16d41955335L;
                              value2 = (value2 ^ -0x67d0864adb1c44a1L) - 0x4659febd5096087cL;
                           } else {
                              value = (value ^ -0x1ba76f1e8838e812L) - 0x5934c0c9540ed293L;
                              value2 = (value2 ^ -0x5766e06d49fd8cc6L) - 0x64c2d17cc765ff11L;
                           }
                           continue;
                        }

                        if (count10 == 44) {
                           if (this.count3 == 18) {
                              value = (value ^ -0x42274e56146038c4L) - 0x7179acbb115a75fcL;
                              value2 = (value2 ^ 0x7557508b20c59aa9L) - 0x12d3406fc94e7a70L;
                           } else {
                              value = (value ^ 0x447ac887d2f250a1L) + 0x5736adecd92e0c1L;
                              value2 = (value2 ^ -0x1aba5fa5f4b511e9L) + 0x4a6e30dd0a7e98fbL;
                           }
                           continue;
                        }

                        if (count10 == 3) {
                           count7--;
                           this.count -= 8;
                           value = (value ^ 0x4ddb125e404677e8L) - 0x185edc2288a27a7eL;
                           value2 = (value2 ^ 0x8857ece4fc3470dL) - 0x6b06b0827677e7ebL;
                           continue;
                        }

                        if (count10 == 37) {
                           if (count7 <= byteValueArray.length - count6) {
                              value = (value ^ -0x4ddccec7d61a43d8L) - 0x445e986a5de0e320L;
                              value2 = (value2 ^ 0x63ea20fefaf7350fL) + 0x844e00858a9a05cL;
                              continue;
                           }

                           throw new IndexOutOfBoundsException();
                        }

                        if (count10 == 58) {
                           if (count9 != -1) {
                              value = (value ^ -0xd0ffd57109370cdL) - 0x6429f45d63c6ad86L;
                              value2 = (value2 ^ -0x64376ff253086972L) + 0x5fdacbfbe8bd2e50L;
                           } else {
                              value = (value ^ 0xa897ae834b9c200L) + 0x47802f3fcd253c8dL;
                              value2 = (value2 ^ 0x489176f1a4e15f21L) - 0x2a01748737045439L;
                           }
                           continue;
                        }

                        if (count10 == 24) {
                           if (this.count3 != 0) {
                              long value9 = (value ^ -0x38a29db83588f5eaL) + 0x73a46fab146556e9L;
                              long value10 = (value2 ^ 0x72631e6fc8d80ba6L) + 0x360b9f052b443872L;
                              this.count3 -= 6;
                              value = (value9 ^ -0x4c30164fb310fe7eL) + 0x5b94fd70e5259a9bL;
                              value2 = (value10 ^ 0x398358dba9d8055aL) - 0x38dc8caed275dfa7L;
                           } else {
                              value = (value ^ -0x44729ae0a616cda5L) + 0x11761916f16decc8L;
                              value2 = (value2 ^ -0xcb64dda2d013bbaL) - 0x3838b875186a8833L;
                           }
                           continue;
                        }

                        if (count10 == 23) {
                           if (this.count < 0) {
                              value = (value ^ -0xe022edaea57f6dcL) - 0x58abe86e2e2e9937L;
                              value2 = (value2 ^ 0x37590d3f336a9089L) + 0x77b7ec565880bd07L;
                           } else {
                              value = (value ^ -0x474fe72f39422bfeL) - 0x38d6a74e66b020dfL;
                              value2 = (value2 ^ 0x23287f79adf659c8L) - 0x5728261f433bf07eL;
                           }
                           continue;
                        }

                        if (count10 == 38) {
                           byteValueArray[count6++] = (byte)(this.count2 >> this.count);
                           value = (value ^ -0x4e2a28333236cad8L) - 0x39d5f8ac25274833L;
                           value2 = (value2 ^ -0x3b1470d3fb18001bL) - 0x6503857adceb7310L;
                           continue;
                        }

                        if (count10 == 14) {
                           if (!this.enabled3) {
                              throw new IOException("Illegal base64 character " + Integer.toString(count9, 16));
                           }

                           value = (value ^ -0xee65cd73136c7a0L) + 0x2e418f532b185c93L;
                           value2 = (value2 ^ 0xab4c207f4ad7e44L) - 0x74d81c5dd2ebbe7dL;
                           continue;
                        }

                        if (count10 == 61) {
                           this.count3 = 18;
                           value = (value ^ 0x54287e4ebfe96320L) + 0x243291b3b25fb04L;
                           value2 = (value2 ^ 0x49271b96e56792d4L) - 0x428f7e1adb9664f0L;
                           continue;
                        }

                        if (count10 == 39) {
                           byteValueArray[count6++] = (byte)(this.count2 >> this.count);
                           value = (value ^ 0x1715b9627578f98bL) + 0x172c363c104ee075L;
                           value2 = (value2 ^ 0xde9e5c8f14676e7L) - 0x109d17d8ef3aacc4L;
                           continue;
                        }

                        if (count10 == 54) {
                           value = (value ^ -0x69f07e94ba892a40L) - 0x707f85f45a9a0377L;
                           value2 = (value2 ^ -0x6a98ac919a67caa6L) + 0x75e84a2cbb4ea27bL;
                           break checkBlock;
                        }

                        if (count10 == 60) {
                           return count6 - count8;
                        }

                        if (count10 == 28) {
                           if (this.count3 == 18) {
                              value = (value ^ 0x766ce735f71d4c09L) - 0x1eb6339476ff29e7L;
                              value2 = (value2 ^ -0x5edef6f0c868d9f8L) + 0x4fed72e77c48bce1L;
                           } else {
                              value = (value ^ -0x3b40346755ec88f4L) - 0x5ae82a3c8695fec9L;
                              value2 = (value2 ^ -0x32b0ae7513702e4aL) + 0x5b27107b5af1ea0fL;
                           }
                           continue;
                        }

                        if (count10 != 36) {
                           if (count10 == 34) {
                              this.count2 = 0;
                              value = (value ^ -0x74b12fd36984f7f4L) - 0x41a2c4883542b7c4L;
                              value2 = (value2 ^ 0x643d16c9374b58dL) - 0x2a7fcee455777e41L;
                              continue;
                           }

                           if (count10 == 57) {
                              this.count2 >>= 8;
                              value = (value ^ 0x5076f42150e0fbb5L) + 0x346aecfa34b5fed6L;
                              value2 = (value2 ^ 0x6dc70f849c1e3aceL) + 0x2e886069c732c206L;
                              continue;
                           }

                           if (count10 == 8) {
                              throw new IndexOutOfBoundsException();
                           }

                           if (count10 != 56) {
                              if (count10 == 48) {
                                 if (this.count < 0) {
                                    value = (value ^ -0x5b21b09929d72167L) - 0x39fcb0d28d7582ceL;
                                    value2 = (value2 ^ 0x5e0b4583163aba93L) - 0x651d179f1d27c84cL;
                                 } else {
                                    value = (value ^ 0x6f3438a9b280bfe8L) - 0x56ca3135fcde2e8fL;
                                    value2 = (value2 ^ -0xe25b65059e74786L) + 0x3b382cd0bc1b78feL;
                                 }
                                 continue;
                              }

                              if (count10 == 10) {
                                 return -1;
                              }

                              if (count10 == 27) {
                                 if (this.count >= 0) {
                                    value = (value ^ -0x17b8a0668aa4f8faL) - 0x4ad548c03912a47cL;
                                    value2 = (value2 ^ 0xdbe2196f7e3003eL) + 0xdefef15748ac7bcL;
                                    continue;
                                 }

                                 return -1;
                              }

                              if (count10 == 0) {
                                 return count6 - count8;
                              }

                              if (count10 == 2) {
                                 this.count2 >>= 8;
                                 value = (value ^ -0x28ecd2a6b415d685L) + 0x77aa47a31f1f598cL;
                                 value2 = (value2 ^ 0x456cc4649bd2b141L) - 0x40ffe772c13018bbL;
                                 continue;
                              }

                              if (count10 == 12) {
                                 if (count7 != 0) {
                                    value = (value ^ -0x2660cfc387a60981L) + 0x410339c8cd9b8abdL;
                                    value2 = (value2 ^ -0x1c9f7c7de0df1d7aL) - 0x9dbdccda78dc210L;
                                 } else {
                                    value = (value ^ 0x3136c15f1982858dL) + 0x18ef6e9975634619L;
                                    value2 = (value2 ^ 0x2f952c4af2603cf4L) + 0x6953a67e0484bbdeL;
                                 }
                                 continue;
                              }

                              if (count10 == 52) {
                                 if (this.count < 0) {
                                    value = (value ^ -0x2d6842468c7c2519L) + 0x5de8e499650767ebL;
                                    value2 = (value2 ^ -0x1c81a29d50ac41f8L) + 0x30f2b6debc0019dcL;
                                    continue;
                                 }

                                 return count6 - count8;
                              }

                              if (count10 != 45) {
                                 if (count10 != 41) {
                                    if (count10 != 55) {
                                       if (count10 == 25) {
                                          throw new IOException("Stream is closed");
                                       }

                                       if (count10 != 31) {
                                          this.count = 0;
                                          value = (value ^ 0x452035de5dcf3c1cL) + 0x30b758568610f9a9L;
                                          value2 = (value2 ^ -0x171550c161aff0ddL) + 0x67cb39e951c0ae87L;
                                       } else if (this.count3 != 6) {
                                          value = (value ^ -0x98a4b5a1e761e7aL) - 0x4c86ab381a2fea3aL;
                                          value2 = (value2 ^ 0x4357c4088144fb71L) - 0x4cdee38119cb5ec9L;
                                       } else {
                                          value = (value ^ 0x2a4dc074235539cbL) - 0x515d36b2c15f9fa6L;
                                          value2 = (value2 ^ -0x1e919be0e9595db5L) + 0x58afa51eccd9591L;
                                       }
                                    } else if (this.count >= 0) {
                                       value = (value ^ 0x24f4df1ee703f652L) + 0x15ddb98d5198aa4fL;
                                       value2 = (value2 ^ 0x7f3801c7108cd5ecL) + 0x3c32c4272b9bf6e0L;
                                    } else {
                                       value = (value ^ 0x1edc28e8b61482ceL) + 0x39c9711d4d73f255L;
                                       value2 = (value2 ^ -0x26b1c4547bc99fa6L) - 0x426d4816862ef8b0L;
                                    }
                                 } else if (this.count3 == 12) {
                                    value = (value ^ 0x7ac57c882448079dL) - 0x37b9b07491af15b4L;
                                    value2 = (value2 ^ -0x7789c305ab07febcL) + 0x10fadf7992660088L;
                                 } else {
                                    value = (value ^ 0x33afe07465c8e51bL) + 0x2a5884bd3a25a46bL;
                                    value2 = (value2 ^ 0x3821ebf3c7860bbL) + 0xdf7561a0e0db545L;
                                 }
                                 continue;
                              }
                              break checkBlock;
                           }
                           break guardBlock;
                        }
                     } else {
                        value = (value ^ 0x7320637926fd51f6L) + 0x3d7f2fe310d05565L;
                        value2 = (value2 ^ 0x29995febc6571b42L) - 0x587f388b972c1bfL;
                     }

                     if (count6 == count8) {
                        return -1;
                     }

                     return count6 - count8;
                  }

                  value = (value ^ -0x7935297a7a0e0769L) - 0x125650c595443e9dL;
                  value2 = (value2 ^ 0x3bea29f07af06721L) - 0x484077135caf372dL;
               } else {
                  value = (value ^ 0x140e6482ae3304a4L) + 0x417373978752bddbL;
                  value2 = (value2 ^ 0x340ae5e12808cb4cL) + 0x680aee748b0f2f0dL;
               }

               if (count7 > 0) {
                  value = (value ^ -0x307877e5c3ecd160L) - 0x6eedb40b80ec0470L;
                  value2 = (value2 ^ -0x14eef8637d9413d7L) - 0x2d6a1576e0ae70b2L;
                  continue;
               }

               return count6 - count8;
            }

            this.enabled = true;
            return count6 - count8;
         } else {
            count7--;
            this.count -= 8;
            value = (value ^ -0x8a8c006d8491f5eL) - 0x6b1ad464bc84c5ddL;
            value2 = (value2 ^ 0x26de4f3efe5d2524L) - 0x24fe195ea5bb914dL;
         }
      }
   }
}
