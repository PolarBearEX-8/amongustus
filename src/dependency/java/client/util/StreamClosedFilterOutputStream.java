package client.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StreamClosedFilterOutputStream extends FilterOutputStream {
   public int count = 0;
   public int count2;
   public int count3;
   public int count4;
   public final byte[] byteFlagArray;
   public boolean enabled = false;
   public int count5;
   public final int count6;
   public final char[] charCodeArray;
   public final boolean enabled2;

   @Override
   public void write(int count7) throws IOException {
      Object entry = null;
      entry = new byte[]{(byte)(count7 & 0xFF)};
      byte byteValue = 1;
      byte byteValue2 = 0;
      this.write((byte[])entry, byteValue2, byteValue);
   }

   public StreamClosedFilterOutputStream(OutputStream outputStream, char[] characterArray, byte[] byteValueArray, int count7, boolean flag) {
      super(outputStream);
      this.count3 = 0;
      this.charCodeArray = characterArray;
      this.byteFlagArray = byteValueArray;
      this.count6 = count7;
      this.enabled2 = flag;
   }

   @Override
   public void close() throws IOException {
      long value = 0x1d373b7c185578faL;
      long value2 = 0x53d1c560d1ee6772L;

      while (true) {
         int count7 = (int)(value % 19L);
         if (count7 != 5) {
            if (count7 != 9) {
               if (count7 != 10) {
                  if (count7 != 2) {
                     if (count7 != 14) {
                        if (count7 != 3) {
                           if (count7 == 18) {
                              break;
                           }

                           if (count7 != 16) {
                              if (count7 != 6) {
                                 if (count7 != 13) {
                                    if (count7 != 8) {
                                       if (count7 != 15) {
                                          if (count7 != 7) {
                                             if (count7 != 0) {
                                                if (count7 != 11) {
                                                   if (count7 != 17) {
                                                      if (count7 != 1) {
                                                         if (count7 == 12) {
                                                            super.out.close();
                                                            break;
                                                         }

                                                         if (!this.enabled2) {
                                                            value = (value ^ 0x7b7de3fe4b4b0bb6L) + 0x1cbc8b2f2e85ffb6L;
                                                            value2 = (value2 ^ 0x383de456d59b119L) + 0x5e7264a0f9dd5e41L;
                                                         } else {
                                                            value = (value ^ -0x22d8b08180af4557L) - 0x779136ed2354facbL;
                                                            value2 = (value2 ^ 0x12e8f24f69459047L) + 0x27ea3e5239e288dL;
                                                         }
                                                      } else {
                                                         super.out.write(this.charCodeArray[this.count2 >> 2]);
                                                         value = (value ^ 0x6f10dbc1e5cbabc0L) + 0x4d3cace2a72e8abdL;
                                                         value2 = (value2 ^ -0x5e7d30c0850b81f3L) + 0x702e9ab781c72f3cL;
                                                      }
                                                   } else {
                                                      super.out.write(this.charCodeArray[this.count2 << 4 & 63 | this.count5 >> 4]);
                                                      value = (value ^ 0x6136fb3b47db98d6L) - 0x1aa171e0387a2b9bL;
                                                      value2 = (value2 ^ -0xa9ecc657577dd1L) - 0x32c4b2da041628acL;
                                                   }
                                                } else {
                                                   super.out.write(this.charCodeArray[this.count2 >> 2]);
                                                   value = (value ^ 0x47dc305b306173a7L) + 0x252583c9bd812245L;
                                                   value2 = (value2 ^ -0xff99336eda019e9L) - 0x19ac726630cb1a35L;
                                                }
                                             } else {
                                                if (this.enabled) {
                                                   break;
                                                }

                                                value = (value ^ 0x47e238c48afb2610L) - 0xf439820097b5c80L;
                                                value2 = (value2 ^ 0x150ce2bdfc9e9e56L) - 0x63f5dc251ec00cf9L;
                                             }
                                          } else {
                                             super.out.write(61);
                                             value = (value ^ -0x76b0d2cf473141afL) + 0x211fd2e415e8cb3aL;
                                             value2 = (value2 ^ -0x323fb36237d02b69L) - 0x300b2cf217db5c0dL;
                                          }
                                       } else {
                                          super.out.write(61);
                                          value = (value ^ 0x6302d82ddd9287a0L) - 0x34eb02508ccfdcd4L;
                                          value2 = (value2 ^ 0x4afdc6027d52509L) + 0x49345891bbeff157L;
                                       }
                                    } else if (this.count != 1) {
                                       long value3 = (value ^ 0x78a61f6f6ffb9a93L) - 0x69f6cfc73327fe1dL;
                                       long value4 = (value2 ^ -0x8999af2a36b7826L) + 0x43775d3bde64561fL;
                                       if (this.count != 2) {
                                          value = (value3 ^ -0x58400e3711fa2ea8L) - 0x66cff503c18b9f3aL;
                                          value2 = (value4 ^ 0x2e0b9c635a00092fL) - 0x3bcb90d984524f90L;
                                       } else {
                                          value = (value3 ^ 0x29b9af35ff1b3273L) + 0x329c303308295238L;
                                          value2 = (value4 ^ -0x58db0db151a9b7f2L) - 0x52f96426998be63eL;
                                       }
                                    } else {
                                       value = (value ^ 0x4dfc31c5a361605eL) - 0x584054c1e9f32e6aL;
                                       value2 = (value2 ^ -0x4a71a4c8a16ac744L) - 0x558b462face42e8eL;
                                    }
                                 } else {
                                    super.out.write(this.charCodeArray[this.count2 << 4 & 63]);
                                    value = (value ^ -0x41c36cd63ba499a6L) - 0x78d083fee7fe268cL;
                                    value2 = (value2 ^ -0x1c26b5ea2ef8643L) + 0x783d76b29e86ddabL;
                                 }
                              } else {
                                 super.out.write(61);
                                 value = (value ^ -0x405f2a432e16d2c2L) - 0x4501c8fa098ca847L;
                                 value2 = (value2 ^ 0x1c765a0da0a08f6aL) - 0x3be505441bf58616L;
                              }
                           } else {
                              this.updateState();
                              value = (value ^ -0x6b1b658f36eb65e9L) - 0x4af0325e08b12006L;
                              value2 = (value2 ^ -0x4567c44720442167L) - 0x179b21cbec44b9d1L;
                           }
                           continue;
                        }

                        if (!this.enabled2) {
                           value = (value ^ 0x764373407a99166L) + 0x2b0771be05a0ef1bL;
                           value2 = (value2 ^ 0x40ed3903d144775eL) - 0x66700e572a3afab0L;
                        } else {
                           value = (value ^ -0x1884c22501d89b42L) + 0x1f4296bdee1e421fL;
                           value2 = (value2 ^ 0x64fe50a89aef3c9eL) + 0x465b7f2bd2bda797L;
                        }
                        continue;
                     }

                     this.updateState();
                     value = (value ^ 0x564b7abac7be5131L) + 0x6275fe7e35eb6ef6L;
                     value2 = (value2 ^ -0x40116142362dc2ecL) - 0x7f3d495500373e3eL;
                     continue;
                  }

                  super.out.write(this.charCodeArray[this.count5 << 2 & 63]);
                  value = (value ^ 0xa14c81499b29949L) + 0x3e7cf06c183c0c5bL;
                  value2 = (value2 ^ 0x4af872d686bf73a5L) - 0x536513e16a00cf5eL;
                  continue;
               }
            } else {
               value = (value ^ 0x43a30ac17d5db659L) - 0x2eb900c4ec38439L;
               value2 = (value2 ^ 0x2e51edc0211a532eL) - 0x3b81203c393c758fL;
            }

            this.count = 0;
            value = (value ^ -0x79baf46498877367L) - 0x737ad8154c848cb7L;
            value2 = (value2 ^ -0x5b389976d2baf280L) + 0x6ca8980a50a00bc1L;
         } else {
            this.enabled = true;
            value = (value ^ 0x1a6ffef8da44458cL) - 0x3dc9871157b22b7dL;
            value2 = (value2 ^ -0x3ee2f62bd1fb20d0L) + 0x596a1eb1e5b832e6L;
         }
      }
   }

   private void updateState() throws IOException {
      if (this.count3 == this.count6) {
         super.out.write(this.byteFlagArray);
         this.count3 = 0;
      }
   }

   @Override
   public void write(byte[] byteValueArray, int count7, int count8) throws IOException {
      int count9 = count7;
      int count10 = count8;
      int count11 = 0;
      int count12 = 0;
      long value = 0x181068f71a3ad996L;
      long value2 = -0x143ad9f6e59407b6L;

      while (true) {
         int count13 = (int)(value % 35L);
         if (count13 == 0) {
            super.out.write(this.charCodeArray[count12 >>> 12 & 63]);
            value = (value ^ -0x6c07456d47b555ceL) + 0x43a2e01a0b5a300fL;
            value2 = (value2 ^ 0x409f00df4752eeb9L) + 0xa25d6ab1a9787f3L;
         } else if (count13 == 5) {
            super.out.write(this.charCodeArray[this.count2 >> 2]);
            value = (value ^ -0x5b5edd0e497fcd98L) + 0x67a655f099673f4bL;
            value2 = (value2 ^ -0x21090a5b872579a3L) - 0x323534119dc5a1c2L;
         } else if (count13 != 28) {
            long value3;
            long value4;
            searchBlock: {
               if (count13 != 18) {
                  if (count13 != 7) {
                     if (count13 == 31) {
                        return;
                     }

                     if (count13 == 8) {
                        this.updateState();
                        value = (value ^ -0x3f6050dac9bbf20aL) + 0x351dfe0a1c1d3372L;
                        value2 = (value2 ^ 0xbe9f8a2b24354d3L) + 0x2bd9a8c83223695aL;
                        continue;
                     }

                     if (count13 == 32) {
                        if (!this.enabled) {
                           value = (value ^ 0x1e46bafab0214c84L) + 0x18c9a8fed4cdf20dL;
                           value2 = (value2 ^ 0x6e11e6027288cac3L) - 0x43f3b7951ae8b7f6L;
                        } else {
                           value = (value ^ -0x44428b5543513d81L) - 0x35c7f5d5d07206e9L;
                           value2 = (value2 ^ -0x13f00b05fe2cbce4L) - 0x7f2ab8372b81a58dL;
                        }
                        continue;
                     }

                     if (count13 == 13) {
                        return;
                     }

                     if (count13 == 29) {
                        throw new ArrayIndexOutOfBoundsException();
                     }

                     if (count13 != 4) {
                        if (count13 == 9) {
                           if (count10 != 0) {
                              long value5 = (value ^ 0xa16d9c7c7e09f41L) + 0x3c7f0cdf56b89eb6L;
                              long value6 = (value2 ^ 0x3e6a978accba1f88L) + 0x2ede38f90e6f7e65L;
                              if (this.count == 0) {
                                 value = (value5 ^ -0x1689fa3f26f72f5dL) - 0x74ecc75722828b93L;
                                 value2 = (value6 ^ 0x107ed7c6b0039e35L) - 0x52c00a5b1409bbf6L;
                              } else {
                                 value = (value5 ^ -0x13c8998bbc14a585L) - 0x69bee028e0857cecL;
                                 value2 = (value6 ^ 0x6b042cbb59d8aa7aL) - 0x32f390d43b80a4e5L;
                              }
                              continue;
                           }

                           return;
                        }

                        if (count13 != 26) {
                           if (count13 == 22) {
                              this.count3 += 4;
                              value = (value ^ -0x985e60b0168122dL) - 0x52b7b3a5d98dc4f1L;
                              value2 = (value2 ^ 0x1acdb6f9d75eaba5L) - 0x1e375d01274d4f8L;
                              continue;
                           }

                           if (count13 == 15) {
                              this.count++;
                              return;
                           }

                           if (count13 == 6) {
                              count12 = (byteValueArray[count9++] & 255) << 16 | (byteValueArray[count9++] & 255) << 8 | byteValueArray[count9++] & 255;
                              value = (value ^ -0x360db5e462211ccL) + 0x37700cfb623bdcc0L;
                              value2 = (value2 ^ 0x34cfde42f9285897L) - 0x539e3c5d05ea0f2L;
                              continue;
                           }

                           if (count13 == 1) {
                              if (count10 <= byteValueArray.length - count9) {
                                 value = (value ^ -0x225a2f16e4cdd480L) + 0x2b65a8428f45546fL;
                                 value2 = (value2 ^ 0xdf4499a87afb288L) - 0x448cd36e914a2854L;
                                 continue;
                              }

                              throw new ArrayIndexOutOfBoundsException();
                           }

                           if (count13 == 23) {
                              if (this.count == 1) {
                                 value = (value ^ 0x756144cfaf7e08baL) + 0x4dc712e38ee17fdcL;
                                 value2 = (value2 ^ 0x7e60f06f0015e146L) - 0x3b87e281dbca9ccaL;
                                 continue;
                              }

                              value3 = (value ^ 0x3a23ccf8f9e852bbL) - 0x5e0a88f5b14dfb0L;
                              value4 = (value2 ^ 0x6a952d739b1aefc5L) + 0x65f6ebbbfd83db5eL;
                              break searchBlock;
                           }

                           if (count13 == 17) {
                              super.out.write(this.charCodeArray[count12 >>> 6 & 63]);
                              value = (value ^ 0x3f74ad9e9f276046L) - 0x122dfb293bcbb269L;
                              value2 = (value2 ^ 0x3a1664669999e3f6L) - 0x4b84bf932de1dad7L;
                              continue;
                           }

                           if (count13 == 16) {
                              if (count10 >= 0) {
                                 value = (value ^ -0x6dcb41660fd9467aL) + 0x54be4ffcf2f69e37L;
                                 value2 = (value2 ^ 0x2207d5a83ce2ce14L) - 0x536031ca1e6c32a5L;
                                 continue;
                              }

                              throw new ArrayIndexOutOfBoundsException();
                           }

                           if (count13 == 33) {
                              this.count3 += 4;
                              value = (value ^ -0x5337f5f5365710bbL) - 0x2fafb1001dbf6579L;
                              value2 = (value2 ^ 0x64e76d88d4ed6509L) - 0x30bbcad0654800d0L;
                              continue;
                           }

                           if (count13 != 19) {
                              if (count13 == 27) {
                                 this.count2 = byteValueArray[count9++] & 255;
                                 value = (value ^ 0x3293ba371db74fd4L) - 0x10a17563bb0060f1L;
                                 value2 = (value2 ^ -0x60145d1b12abe765L) + 0x54c78698c5986e4L;
                                 continue;
                              }

                              if (count13 != 34) {
                                 if (count13 == 25) {
                                    super.out.write(this.charCodeArray[this.count2 << 4 & 63 | this.count5 >> 4]);
                                    value = (value ^ 0x6ad4d48da4e75408L) - 0xdb8f0ca266ad818L;
                                    value2 = (value2 ^ 0x2b3ee83678e7fd46L) - 0x7a868fd4978276f7L;
                                    continue;
                                 }

                                 if (count13 == 21) {
                                    super.out.write(this.charCodeArray[count12 >>> 18 & 63]);
                                    value = (value ^ -0x695126aa2096b420L) - 0x5255a0d9f5046598L;
                                    value2 = (value2 ^ -0x78978afd032b7329L) + 0x56a60ba17391f2ebL;
                                    continue;
                                 }

                                 if (count13 == 3) {
                                    if (count9 >= 0) {
                                       value = (value ^ -0x42e1486464d5f279L) - 0x63f0c440b00ba957L;
                                       value2 = (value2 ^ 0x6907f50df3486b0aL) - 0x29a18d498469811bL;
                                       continue;
                                    }

                                    throw new ArrayIndexOutOfBoundsException();
                                 }

                                 if (count13 == 12) {
                                    this.count5 = byteValueArray[count9++] & 255;
                                    value = (value ^ -0x70ba161e6b1079aL) - 0x4296cf918807b0b5L;
                                    value2 = (value2 ^ 0x1b4a2f41348bb7feL) - 0x7208d775cec9c733L;
                                    continue;
                                 }

                                 if (count13 == 30) {
                                    throw new IOException("Stream is closed");
                                 }

                                 if (count13 == 14) {
                                    this.count = count10 - count11 * 3;
                                    value = (value ^ -0x25d5bee8b075d64bL) - 0xe4482cb05aaaf94L;
                                    value2 = (value2 ^ -0x546abe38e0c0202eL) + 0x203cda43063eb0bcL;
                                    continue;
                                 }

                                 if (count13 == 2) {
                                    count10--;
                                    this.updateState();
                                    value = (value ^ 0x1ce1aee3300826f9L) - 0x1e8047cdd0ae99c0L;
                                    value2 = (value2 ^ 0x7a0fb3a46048b8b2L) - 0x5fc07dffa2f80165L;
                                    continue;
                                 }

                                 if (count13 != 10) {
                                    if (count13 != 11) {
                                       if (count13 != 24) {
                                          count11 = count10 / 3;
                                          value = (value ^ 0x6449368416fc8b5fL) + 0x4d823af1043a26a7L;
                                          value2 = (value2 ^ 0x60c9667f5c5ce542L) - 0x702df8033f5f7ce8L;
                                       } else {
                                          super.out.write(this.charCodeArray[count12 & 63]);
                                          value = (value ^ -0x35ddaa1824e9c41eL) + 0x45e332bbf1994889L;
                                          value2 = (value2 ^ 0x4e98d167daa6abddL) + 0x45daf479f495991bL;
                                       }
                                    } else {
                                       super.out.write(this.charCodeArray[this.count4 & 63]);
                                       value = (value ^ 0x1a7575805a108c0dL) - 0x2a972f6eac1fe11dL;
                                       value2 = (value2 ^ -0x1c4e60299c687600L) - 0x152686c6232a9fb9L;
                                    }
                                    continue;
                                 }

                                 if (--count10 == 0) {
                                    value = (value ^ 0x260eb1259813b1feL) - 0x4d77288868b8689fL;
                                    value2 = (value2 ^ 0x2145238623c786b7L) + 0x73892d61bc3bcf02L;
                                    continue;
                                 }

                                 value3 = (value ^ -0x2dcc115fde9e4c03L) - 0x363592a02444a1b7L;
                                 value4 = (value2 ^ -0x44b89c595fe2d3aaL) + 0x732d70ab7c91c220L;
                                 break searchBlock;
                              }
                           } else {
                              this.count2 = byteValueArray[count9++] & 255;
                           }

                           return;
                        } else {
                           this.count5 = byteValueArray[count9++] & 255;
                           return;
                        }
                     }

                     return;
                  }

                  value = (value ^ -0x77aeda022bb9b64dL) - 0x796e41b8ba4517d2L;
                  value2 = (value2 ^ -0x20bdbf6e0aa7877aL) + 0x2f7d1bcf8572ba85L;
               }

               if (count11-- > 0) {
                  value = (value ^ -0x9d949b352eb2ad3L) - 0x7e555e72bb4e2d0fL;
                  value2 = (value2 ^ 0x5c1a3ad2f0585fc1L) + 0x1a3ad958792f4b8L;
                  continue;
               }

               long value7 = (value ^ 0x19f2c2923b94bafcL) + 0xaee255ae9ee9773L;
               long value8 = (value2 ^ 0x6a108dd6e58963ebL) + 0x7b31a10aaa3c9f90L;
               if (this.count == 1) {
                  value = (value7 ^ -0x4d117dd8249267faL) + 0x50496ec4ed2ca6bbL;
                  value2 = (value8 ^ -0x75a20f4ec8c60fc6L) + 0x20d6fdf32a3d4252L;
                  continue;
               }

               long value9 = (value7 ^ -0x26955bca71143e05L) - 0x2eed8f2f04ebe119L;
               long value10 = (value8 ^ -0x534cd38f9f253f86L) - 0x6b01af2b3951b4d6L;
               if (this.count == 2) {
                  value = (value9 ^ 0x7b543950fcef1137L) + 0x6ed6c5711e6769b4L;
                  value2 = (value10 ^ -0x3b08cb0ab0dc79b5L) - 0x1070b59e943e1f68L;
                  continue;
               }

               return;
            }

            this.count4 = byteValueArray[count9++] & 255;
            value = (value3 ^ -0x47decd9d59bdf692L) + 0x74693fd18b26a033L;
            value2 = (value4 ^ -0x489fc067b9b0fc9cL) - 0x74c12ab3a255f284L;
         } else {
            super.out.write(this.charCodeArray[this.count5 << 2 & 63 | this.count4 >> 6]);
            value = (value ^ -0x234a1eef7937bb58L) + 0x734757e64063de65L;
            value2 = (value2 ^ -0x3ab11d2e202d66f9L) + 0x3931cca4e61649d7L;
         }
      }
   }
}
