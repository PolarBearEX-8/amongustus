package client.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BufferedFilterOutputStream extends FilterOutputStream {
   public final int count2;
   public int count6;
   public int count3;
   public int count5 = 0;
   public final byte[] byteFlagArray;
   public final boolean enabled2;
   public boolean enabled = false;
   public final char[] charCodeArray;
   public int count4;
   public int count;

   private void updateState() throws IOException {
      if (this.count6 == this.count2) {
         this.out.write(this.byteFlagArray);
         this.count6 = 0;
      }
   }

   public BufferedFilterOutputStream(OutputStream outputStream, char[] characterArray, byte[] byteValueArray, int count7, boolean flag) {
      super(outputStream);
      this.count6 = 0;
      this.charCodeArray = characterArray;
      this.byteFlagArray = byteValueArray;
      this.count2 = count7;
      this.enabled2 = flag;
   }

   @Override
   public void write(byte[] byteValueArray, int count7, int count8) throws IOException {
      int count9 = count7;
      int count10 = count8;
      int count11 = 0;
      int count12 = 0;
      long value = 0x31fd193554d952b1L;
      long value2 = -0x1785f91fcb17df0fL;

      while (true) {
         int count13 = (int)(value % 35L);
         if (count13 == 27) {
            this.out.write(this.charCodeArray[count12 >>> 6 & 63]);
            value = (value ^ -0x27826c0ad0b359c9L) + 0x35ed637d935bb6f2L;
            value2 = (value2 ^ 0x762e083188379a05L) - 0xd35515fac71cf87L;
         } else if (count13 == 14) {
            this.count6 += 4;
            value = (value ^ 0x661898440738c02L) - 0x2f6b452da739c477L;
            value2 = (value2 ^ -0x665890381b35e25bL) + 0x46b8bfd020c572eaL;
         } else if (count13 != 3) {
            if (count13 == 31) {
               this.count = byteValueArray[count9++] & 255;
               return;
            } else if (count13 != 11) {
               if (count13 == 8) {
                  return;
               }

               if (count13 != 0) {
                  if (count13 != 4) {
                     if (count13 == 32) {
                        count11 = count10 / 3;
                        value = (value ^ -0x6a8e28b4eb9c8ed1L) - 0x4ac99f76b570c384L;
                        value2 = (value2 ^ 0x1a8e89b69a1b567bL) - 0x173d7f3632f062ccL;
                        continue;
                     }

                     if (count13 != 28) {
                        if (count13 == 1) {
                           this.count5++;
                           return;
                        }

                        if (count13 == 17) {
                           count10--;
                           this.updateState();
                           value = (value ^ -0x19d908ed1dc8799aL) - 0x6784fe517d54c5b0L;
                           value2 = (value2 ^ -0x103178f9274dfc49L) - 0x5cfd4b0417c24528L;
                           continue;
                        }

                        if (count13 == 22) {
                           this.out.write(this.charCodeArray[this.count3 & 63]);
                           value = (value ^ -0x76b0cac295aa6046L) - 0x5361b6166866fac5L;
                           value2 = (value2 ^ -0x7349672006466d6L) + 0x7e964c5d5f0f7405L;
                           continue;
                        }

                        if (count13 == 18) {
                           this.count4 = byteValueArray[count9++] & 255;
                           value = (value ^ -0x305e11e237fdbe5dL) - 0x416ca6832c0ceb6eL;
                           value2 = (value2 ^ 0x3052b238bb493b9bL) - 0x22e7f41bdf53cdfbL;
                           continue;
                        }

                        if (count13 != 9) {
                           if (count13 == 29) {
                              this.count = byteValueArray[count9++] & 255;
                              value = (value ^ -0x39c440167d7ddd1eL) - 0x5812d79777d8179eL;
                              value2 = (value2 ^ -0xa20bbe52f1da594L) - 0x67c7b86ff2cc0841L;
                              continue;
                           }

                           if (count13 == 34) {
                              if (count10 == 0) {
                                 return;
                              }

                              long value3 = (value ^ 0x2b48c8c55cbd5a60L) - 0x1184f4f944fc12c8L;
                              long value4 = (value2 ^ -0x3f4ef457ad9bcc17L) + 0x4b492ad8849a8a77L;
                              if (this.count5 == 0) {
                                 value = (value3 ^ 0x821df27f29fcc6dL) - 0x3bf3b588f09e642eL;
                                 value2 = (value4 ^ 0x6c4cbcfdcd5f1bb3L) - 0x59d015b67b2f8fdaL;
                              } else {
                                 value = (value3 ^ -0xc17d0da6dceaa3dL) - 0x5f1d9c20fa11f2c7L;
                                 value2 = (value4 ^ -0x1524d86d8a87b38fL) - 0x462aa56b12acaf94L;
                              }
                              continue;
                           }

                           if (count13 == 21) {
                              this.updateState();
                              value = (value ^ 0x5e8440164ce6b8a6L) - 0x2b61e3b151cd31d6L;
                              value2 = (value2 ^ 0x379ac9fb37bb4badL) + 0x76bf48ba58645aceL;
                              continue;
                           }

                           if (count13 != 10) {
                              if (count13 != 5) {
                                 if (count13 != 25) {
                                    if (count13 != 33) {
                                       if (count13 != 12) {
                                          if (count13 != 16) {
                                             if (count13 != 23) {
                                                if (count13 == 15) {
                                                   throw new IOException("Stream is closed");
                                                }

                                                long value5;
                                                long value6;
                                                if (count13 != 19) {
                                                   if (count13 == 24) {
                                                      this.out.write(this.charCodeArray[this.count << 4 & 63 | this.count4 >> 4]);
                                                      value = (value ^ -0x49fbde6ff8437b0dL) - 0x71872f5480d680c3L;
                                                      value2 = (value2 ^ -0x1cb6453496480eedL) + 0xcea3255aa8d3352L;
                                                      continue;
                                                   }

                                                   if (count13 == 7) {
                                                      this.out.write(this.charCodeArray[count12 >>> 18 & 63]);
                                                      value = (value ^ -0x560be223df47d64cL) + 0x2bbf899922e53303L;
                                                      value2 = (value2 ^ 0x2b2ff541bf1d832dL) - 0x2a25d5f5f4930c87L;
                                                      continue;
                                                   }

                                                   if (count13 != 20) {
                                                      this.count5 = count10 - count11 * 3;
                                                      value = (value ^ -0x7c8fc223a3e20151L) + 0x27ef4f0d944494fdL;
                                                      value2 = (value2 ^ -0x25f70f5cd8b494deL) - 0x4e2f1e6dd692d1deL;
                                                      continue;
                                                   }

                                                   if (--count10 == 0) {
                                                      value = (value ^ -0x413f7172ece345b1L) + 0x7dc308ffcf23e77eL;
                                                      value2 = (value2 ^ -0x572f85d39e3480fL) + 0x13546bcfa1741d58L;
                                                      continue;
                                                   }

                                                   value5 = (value ^ -0x6c523fa3ea9ca958L) + 0x1be68bdd4d353e41L;
                                                   value6 = (value2 ^ -0x36424e4e18b0333fL) - 0xa0d108d6ef5f776L;
                                                } else {
                                                   if (this.count5 == 1) {
                                                      value = (value ^ 0xc396e76c30687cbL) + 0x34904d87c1f6a26dL;
                                                      value2 = (value2 ^ 0x733a5d31b73e22c8L) + 0x4d9a3bace51109f0L;
                                                      continue;
                                                   }

                                                   value5 = (value ^ -0x53a88796fa8ba316L) + 0x7de13e905f4e1458L;
                                                   value6 = (value2 ^ -0x65d52c17b45ae503L) + 0xfbda504ed58f2f8L;
                                                }

                                                this.count3 = byteValueArray[count9++] & 255;
                                                value = (value5 ^ 0x304da88a76344d37L) + 0x412d21b4394f8b78L;
                                                value2 = (value6 ^ 0x6c1b4d567e25d6c2L) - 0x1745a9a55ce87ef1L;
                                                continue;
                                             }

                                             this.out.write(this.charCodeArray[this.count4 << 2 & 63 | this.count3 >> 6]);
                                             value = (value ^ 0x32fd63312cea6b74L) + 0x19278fdd01b8573fL;
                                             value2 = (value2 ^ -0x5d2076e4e9d975d1L) + 0x433cd7cad2748482L;
                                             continue;
                                          }

                                          count12 = (byteValueArray[count9++] & 255) << 16 | (byteValueArray[count9++] & 255) << 8 | byteValueArray[count9++] & 255;
                                          value = (value ^ -0x5a7bfa4bc09e5fc6L) - 0x4fd4203c8f9a39fbL;
                                          value2 = (value2 ^ -0x6cd7e801d32afff5L) + 0x272b75016c41efd7L;
                                          continue;
                                       }

                                       this.out.write(this.charCodeArray[count12 & 63]);
                                       value = (value ^ 0x74ab487d7c01e2d7L) + 0x3186d77a9810f11L;
                                       value2 = (value2 ^ 0x34c30d66b310066fL) + 0x3f96614745155bfbL;
                                       continue;
                                    }

                                    this.out.write(this.charCodeArray[count12 >>> 12 & 63]);
                                    value = (value ^ 0x37cdb516ed129fefL) + 0x2553bc479880f267L;
                                    value2 = (value2 ^ -0x160dfe07c2632cd6L) - 0x639ac90dbec7f11dL;
                                    continue;
                                 }

                                 this.out.write(this.charCodeArray[this.count >> 2]);
                                 value = (value ^ 0x2b6a6e2597dda203L) - 0xdcf6be430e0dd2eL;
                                 value2 = (value2 ^ 0x5d6c4d787daeea0fL) + 0x459176d4f0fb20feL;
                                 continue;
                              }

                              if (!this.enabled) {
                                 value = (value ^ -0x2eac462e9f5207f0L) + 0x257cc5aceebc2010L;
                                 value2 = (value2 ^ -0x3e1a5438e3078905L) + 0x25305d4ac849532cL;
                              } else {
                                 value = (value ^ -0xc5f364ba3ccbc9dL) - 0x6ea0cae02289f42aL;
                                 value2 = (value2 ^ -0x340af251854b86d8L) + 0xdb4efe7b601f8d4L;
                              }
                              continue;
                           }

                           this.count4 = byteValueArray[count9++] & 255;
                           return;
                        }

                        if (count10 <= byteValueArray.length - count9) {
                           value = (value ^ 0x714d900d098a5db9L) - 0x18f8ef64275c33a1L;
                           value2 = (value2 ^ 0x2929e44c386353f5L) - 0xcf06d42d5b5d36eL;
                           continue;
                        }
                     } else if (count9 >= 0) {
                        value = (value ^ -0x7244c45c3e3c4e3bL) - 0x58bf80a241f77b4fL;
                        value2 = (value2 ^ -0x6ac5de2a905dfdf9L) + 0x3b9d943cff9704c8L;
                        continue;
                     }

                     throw new ArrayIndexOutOfBoundsException();
                  }
               } else {
                  value = (value ^ -0x2911f54e40ce9f58L) + 0x2fe984927dc02c30L;
                  value2 = (value2 ^ -0x2210f56231b5d0fL) + 0x7246c6bb8f2d2497L;
               }

               if (count11-- > 0) {
                  value = (value ^ 0x7075f5d61b80663eL) - 0x13d4a9c5fa1cf0c8L;
                  value2 = (value2 ^ 0x2d9d13a5343f22d2L) + 0x437f26216d1f281fL;
               } else {
                  long value7 = (value ^ -0x544c942e1520392aL) + 0x63456d2aa9d17266L;
                  long value8 = (value2 ^ 0x3c0112adaac12e72L) + 0x2f75773f1ef09806L;
                  if (this.count5 == 1) {
                     value = (value7 ^ 0x2e530f2900bb351eL) + 0x397506c81429709bL;
                     value2 = (value8 ^ 0x6b1434457c20a687L) + 0x29a406b4f4ebad92L;
                  } else {
                     long value9 = (value7 ^ 0x46ba44b676b7232L) + 0x2215b7a640eb29dfL;
                     long value10 = (value8 ^ 0x5aaa5beae90133e8L) - 0x4d52eeac5fa1db2eL;
                     if (this.count5 != 2) {
                        return;
                     }

                     value = (value9 ^ -0x4ea906f0fb77de1cL) - 0x77f147a0b02742bfL;
                     value2 = (value10 ^ 0x698777eaedfedd39L) - 0x2463e8375caada08L;
                  }
               }
            } else {
               if (count10 < 0) {
                  throw new ArrayIndexOutOfBoundsException();
               }

               value = (value ^ -0x1399111b2985313cL) + 0x2322bcad95748a5eL;
               value2 = (value2 ^ 0x9bdbac03c0465a2L) + 0x7c23297e67843ce2L;
            }
         } else {
            this.count6 += 4;
            value = (value ^ 0x33eb03bfc6193e44L) - 0x515e7baff4255703L;
            value2 = (value2 ^ 0x54380c0a6f2a623eL) + 0x41e67c7dceccdb1fL;
         }
      }
   }

   @Override
   public void write(int count7) throws IOException {
      Object entry = null;
      entry = new byte[]{(byte)(count7 & 0xFF)};
      byte byteValue = 1;
      byte byteValue2 = 0;
      this.write((byte[])entry, byteValue2, byteValue);
   }

   @Override
   public void close() throws IOException {
      long value = 0x6523c9a70da8747L;
      long value2 = -0x3816cafdc030d3ccL;

      while (true) {
         int count7 = (int)(value % 19L);
         if (count7 == 9) {
            this.out.write(61);
            value = (value ^ 0x1e8420397dfbd1e4L) + 0x1d6c246415180661L;
            value2 = (value2 ^ 0x2e2dd4fea59828fdL) + 0x3e44e8bc84fe81f3L;
         } else if (count7 == 0) {
            this.updateState();
            value = (value ^ -0x31fccaf1bba98f70L) - 0x5fc6852a00419088L;
            value2 = (value2 ^ 0x799bc6314d3de7b5L) + 0x49c180b92657826dL;
         } else if (count7 == 15) {
            this.updateState();
            value = (value ^ 0x28a67a5b187a4e42L) + 0x39892bb493ae0e0L;
            value2 = (value2 ^ -0x63c36740118e307L) + 0x7f3e027e4c4cba25L;
         } else if (count7 != 1) {
            if (count7 != 16) {
               if (count7 == 11) {
                  if (!this.enabled2) {
                     value = (value ^ 0x920a263758c6d2L) - 0x3c92d96813e63e07L;
                     value2 = (value2 ^ -0x4e37170da6b004eL) + 0x4910a5a34f4aaeb9L;
                  } else {
                     value = (value ^ -0x7790a183b528e2cL) - 0x56e634ac897eeabcL;
                     value2 = (value2 ^ 0x69783628bc7b489eL) - 0x36919cad5e498714L;
                  }
                  continue;
               }

               if (count7 == 2) {
                  this.enabled = true;
                  value = (value ^ -0x5bf2c4ad63e583f9L) + 0x7a87a9b23e39da64L;
                  value2 = (value2 ^ 0x5b0a9841769411c7L) + 0x6ff6728fb9ed803fL;
                  continue;
               }

               if (count7 == 12) {
                  this.out.close();
                  break;
               }

               if (count7 != 5) {
                  if (count7 != 7) {
                     if (count7 != 3) {
                        if (count7 != 13) {
                           if (count7 != 8) {
                              if (count7 != 4) {
                                 if (count7 != 18) {
                                    if (count7 != 6) {
                                       if (count7 != 10) {
                                          this.out.write(this.charCodeArray[this.count << 4 & 63 | this.count4 >> 4]);
                                          value = (value ^ -0x2842fab0faa6109dL) - 0x7993fa7abbb32049L;
                                          value2 = (value2 ^ 0x25ba25a789567b8eL) + 0x7481af6c7cfd546bL;
                                       } else {
                                          this.out.write(this.charCodeArray[this.count >> 2]);
                                          value = (value ^ 0x3729b377ecccb5ecL) - 0x135cd37cf676b444L;
                                          value2 = (value2 ^ -0x30d2a23980638c31L) - 0x128266d787b7a5fbL;
                                       }
                                    } else if (!this.enabled2) {
                                       value = (value ^ 0x1eb3899305bbec53L) + 0x234e5ecea0358fecL;
                                       value2 = (value2 ^ 0x79cef70a1332aad6L) + 0x1858e00ea134ee1L;
                                    } else {
                                       value = (value ^ 0x6cdd8be89d81af67L) - 0x5249776185a662c6L;
                                       value2 = (value2 ^ -0x3ea9e8ccd0134533L) + 0x55ad28944c6a46f7L;
                                    }
                                 } else {
                                    this.out.write(this.charCodeArray[this.count4 << 2 & 63]);
                                    value = (value ^ 0x1131e6262f9a7b70L) - 0x7aa7fbffdd84b7L;
                                    value2 = (value2 ^ 0x5003af27ccead036L) - 0x21c2fb2045a29d23L;
                                 }
                                 continue;
                              }

                              if (this.enabled) {
                                 break;
                              }

                              value = (value ^ -0x511ebba5ad1f8ec5L) + 0x5ecc52924b931516L;
                              value2 = (value2 ^ -0x4f86d4a4962b9929L) - 0x3a4a23e19ba30c1cL;
                              continue;
                           }

                           this.out.write(61);
                           value = (value ^ 0x4682d50583329f6eL) - 0x42375468eedfbf20L;
                           value2 = (value2 ^ 0x355c313827ee9812L) + 0x482c941b9f6c9017L;
                           continue;
                        }

                        this.out.write(this.charCodeArray[this.count >> 2]);
                        value = (value ^ 0x48e9b0a8078a6146L) - 0xf9eb461aec91a54L;
                        value2 = (value2 ^ -0x5e8a08929af87cbL) + 0x1047b9feef330a73L;
                        continue;
                     }

                     this.out.write(this.charCodeArray[this.count << 4 & 63]);
                     value = (value ^ -0x62c71e84494aeef7L) - 0x74bb078c13269abbL;
                     value2 = (value2 ^ 0x22f836c5a1e66f6L) - 0x518da2803b4f0c26L;
                     continue;
                  }

                  this.out.write(61);
                  value = (value ^ 0x7473a47927d4389L) - 0x1f426029bc52d063L;
                  value2 = (value2 ^ -0x3880630fb7d21098L) + 0x31ff43830ae97fe7L;
                  continue;
               }

               value = (value ^ 0x31147a47ad0badefL) + 0x1653047d76bbf039L;
               value2 = (value2 ^ -0x7d6ba13731d374f1L) + 0x4f8d43e6c69b516dL;
            }

            this.count5 = 0;
            value = (value ^ -0x1cc5a8d575d5e93eL) + 0x3cee2b5de3f1fc29L;
            value2 = (value2 ^ 0x78ed06f518ce1a80L) - 0x7b3f8f0f584b40d7L;
         } else if (this.count5 != 1) {
            long value3 = (value ^ 0x5c9d233b7120f854L) - 0x19d90805bd30b1dbL;
            long value4 = (value2 ^ -0x5f71f5f31cd20c0bL) - 0x75361f5c0a1df2ceL;
            if (this.count5 != 2) {
               value = (value3 ^ -0x7af09cc6794404c8L) - 0x759404785f93c274L;
               value2 = (value4 ^ -0xc1b20d6e29aea8aL) - 0x1fe23ab9ea1d482aL;
            } else {
               value = (value3 ^ -0x669af935da313915L) + 0x45f5b49ba286d581L;
               value2 = (value4 ^ -0x45c90386047356f3L) - 0x47a7044803e09682L;
            }
         } else {
            value = (value ^ -0x467b942fa057ac04L) + 0x68c2236c356bd73eL;
            value2 = (value2 ^ 0x819f32bca086dbL) - 0x2eb2dc17615894b5L;
         }
      }
   }
}
