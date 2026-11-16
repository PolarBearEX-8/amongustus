package client.util;

import client.setting.MovementWeightProperty;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
import net.minecraft.item.ItemEntry133Entry;

public class PacketPayloadEncoder extends MessageToMessageEncoder {
   public byte[] byteFlagArray161;
   public byte[] byteFlagArray19;
   public byte[] byteFlagArray75;
   public byte[] byteFlagArray118;
   public byte[] byteFlagArray153;
   public byte[] byteFlagArray147;
   public byte[] byteFlagArray185;
   public byte[] byteFlagArray113;
   public byte[] byteFlagArray71;
   public byte[] byteFlagArray126;
   public byte[] byteFlagArray62;
   public byte[] byteFlagArray102;
   public byte[] byteFlagArray189;
   public byte[] byteFlagArray139;
   public byte[] byteFlagArray27;
   public byte[] byteFlagArray191;
   public byte[] byteFlagArray68;
   public byte[] byteFlagArray122;
   public static final int count5 = 14;
   public byte[] byteFlagArray105;
   public byte[] byteFlagArray138;
   public byte[] byteFlagArray70;
   public byte[] byteFlagArray188;
   public byte[] byteFlagArray83;
   public byte[] byteFlagArray148;
   public byte[] byteFlagArray202;
   public int[] countArray6;
   public byte[] byteFlagArray179;
   public byte[] byteFlagArray61;
   public byte[] byteFlagArray151;
   public static int[] countArray3;
   public byte[] byteFlagArray2;
   public static final int count3 = 16;
   public static int[] countArray7;
   public byte[] byteFlagArray101;
   public byte[] byteFlagArray199;
   public byte[] byteFlagArray79;
   public byte[] byteFlagArray54;
   public byte[] byteFlagArray194;
   public byte[] byteFlagArray49;
   public byte[] byteFlagArray124;
   public byte[] byteFlagArray156;
   public byte[] byteFlagArray29;
   public byte[] byteFlagArray34;
   public byte[] byteFlagArray67;
   public byte[] byteFlagArray12;
   public byte[] byteFlagArray21;
   public byte[] byteFlagArray184;
   public byte[] byteFlagArray134;
   public byte[] byteFlagArray100;
   public final SpecialLongEntry specialLongEntry;
   public byte[] byteFlagArray52;
   public byte[] byteFlagArray43;
   public byte[] byteFlagArray205;
   public byte[] byteFlagArray72;
   public byte[] byteFlagArray20;
   public byte[] byteFlagArray84;
   public byte[] byteFlagArray96;
   public byte[] byteFlagArray56;
   public byte[] byteFlagArray135;
   public byte[] byteFlagArray10;
   public byte[] byteFlagArray163;
   public byte[] byteFlagArray41;
   public byte[] byteFlagArray103;
   public byte[] byteFlagArray39;
   public byte[] byteFlagArray170;
   public byte[] byteFlagArray111;
   public byte[] byteFlagArray3;
   public byte[] byteFlagArray186;
   public byte[] byteFlagArray106;
   public byte[] byteFlagArray107;
   public byte[] byteFlagArray198;
   public byte[] byteFlagArray144;
   public byte[] byteFlagArray53;
   public byte[] byteFlagArray150;
   public byte[] byteFlagArray181;
   public byte[] byteFlagArray98;
   public byte[] byteFlagArray155;
   public byte[] byteFlagArray9;
   public byte[] byteFlagArray200;
   public byte[] byteFlagArray28;
   public byte[] byteFlagArray78;
   public byte[] byteFlagArray114;
   public byte[] byteFlagArray55;
   public static final int count4 = 12;
   public byte[] byteFlagArray177;
   public byte[] byteFlagArray175;
   public byte[] byteFlagArray82;
   public byte[] byteFlagArray26;
   public byte[] byteFlagArray149;
   public byte[] byteFlagArray109;
   public byte[] byteFlagArray146;
   public byte[] byteFlagArray172;
   public byte[] byteFlagArray119;
   public byte[] byteFlagArray108;
   public final byte[] byteFlagArray125;
   public byte[] byteFlagArray166;
   public byte[] byteFlagArray204;
   public byte[] byteFlagArray142;
   public byte[] byteFlagArray74;
   public byte[] byteFlagArray128;
   public byte[] byteFlagArray190;
   public byte[] byteFlagArray130;
   public byte[] byteFlagArray171;
   public byte[] byteFlagArray86;
   public byte[] byteFlagArray69;
   public byte[] byteFlagArray104;
   public byte[] byteFlagArray187;
   public CustomByteArrayEntry customByteArrayEntry;
   public byte[] byteFlagArray182;
   public byte[] byteFlagArray6;
   public byte[] byteFlagArray193;
   public byte[] byteFlagArray66;
   public static final int count = 16;
   public byte[] byteFlagArray183;
   public byte[] byteFlagArray169;
   public byte[] byteFlagArray157;
   public byte[] byteFlagArray18;
   public byte[] byteFlagArray164;
   public byte[] byteFlagArray112;
   public byte[] byteFlagArray25;
   public byte[] byteFlagArray140;
   public byte[] byteFlagArray99;
   public byte[] byteFlagArray81;
   public byte[] byteFlagArray33;
   public byte[] byteFlagArray35;
   public byte[] byteFlagArray129;
   public byte[] byteFlagArray5;
   public byte[] byteFlagArray46;
   public byte[] byteFlagArray159;
   public byte[] byteFlagArray131;
   public byte[] byteFlagArray77;
   public byte[] byteFlagArray76;
   public byte[] byteFlagArray136;
   public byte[] byteFlagArray85;
   public byte[] byteFlagArray32;
   public byte[] byteFlagArray141;
   public byte[] byteFlagArray174;
   public byte[] byteFlagArray8;
   public byte[] byteFlagArray195;
   public byte[] byteFlagArray132;
   public byte[] byteFlagArray152;
   public byte[] byteFlagArray50;
   public byte[] byteFlagArray90;
   public byte[] byteFlagArray58;
   public byte[] byteFlagArray115;
   public byte[] byteFlagArray17;
   public static int[][] countArray;
   public byte[] byteFlagArray123;
   public byte[] byteFlagArray51;
   public final int[] countArray4;
   public byte[] byteFlagArray154;
   public byte[] byteFlagArray176;
   public byte[] byteFlagArray65;
   public byte[] byteFlagArray196;
   public byte[] byteFlagArray192;
   public byte[] byteFlagArray;
   public byte[] byteFlagArray110;
   public byte[] byteFlagArray89;
   public byte[] byteFlagArray121;
   public byte[] byteFlagArray13;
   public byte[] byteFlagArray22;
   public byte[] byteFlagArray57;
   public byte[] byteFlagArray4;
   public byte[] byteFlagArray48;
   public byte[] byteFlagArray59;
   public static final int count2 = 10;
   public byte[] byteFlagArray11;
   public byte[] byteFlagArray60;
   public byte[] byteFlagArray38;
   public byte[] byteFlagArray168;
   public byte[] byteFlagArray201;
   public byte[] byteFlagArray16;
   public byte[] byteFlagArray37;
   public byte[] byteFlagArray93;
   public byte[] byteFlagArray92;
   public byte[] byteFlagArray120;
   public byte[] byteFlagArray197;
   public byte[] byteFlagArray127;
   public byte[] byteFlagArray158;
   public byte[] byteFlagArray133;
   public byte[] byteFlagArray80;
   public byte[] byteFlagArray42;
   public byte[] byteFlagArray7;
   public byte[] byteFlagArray24;
   public byte[] byteFlagArray173;
   public byte[] byteFlagArray162;
   public byte[] byteFlagArray94;
   public byte[] byteFlagArray63;
   public byte[] byteFlagArray117;
   public final byte[] byteFlagArray180;
   public byte[] byteFlagArray95;
   public byte[] byteFlagArray40;
   public byte[] byteFlagArray14;
   public byte[] byteFlagArray203;
   public byte[] byteFlagArray167;
   public final int[] countArray5 = new int[16];
   public byte[] byteFlagArray145;
   public byte[] byteFlagArray23;
   public byte[] byteFlagArray165;
   public byte[] byteFlagArray64;
   public byte[] byteFlagArray44;
   public byte[] byteFlagArray15;
   public byte[] byteFlagArray47;
   public byte[] byteFlagArray87;
   public byte[] byteFlagArray178;
   public byte[] byteFlagArray91;
   public byte[] byteFlagArray88;
   public byte[] byteFlagArray30;
   public byte[] byteFlagArray116;
   public byte[] byteFlagArray45;
   public byte[] byteFlagArray73;
   public byte[] byteFlagArray137;
   public byte[] byteFlagArray160;
   public byte[] byteFlagArray36;
   public static int[] countArray2;
   public byte[] byteFlagArray31;
   public byte[] byteFlagArray143;
   public byte[] byteFlagArray97;

   private byte[] resolveByteArray(byte[] byteValueArray, int[] countArray8) {
      short shortValue = 29181;
      short shortValue2 = 5278;
      char character = '\u9e9c';
      int count6 = 0;
      int count7 = 0;
      long value = 0x3b206d12e3f136f5L;
      long value2 = -0x2e3f0a79c41cc6ebL;

      while (true) {
         int count8 = (int)(value % 20L);
         if (count8 != 0) {
            if (count8 != 5) {
               if (count8 != 10) {
                  if (count8 == 9) {
                     count7 = 0;
                     value = (value ^ 0x679f6877b4fcba7eL) - 0x13c72847ba8a8bf0L;
                     value2 = (value2 ^ 0x296873f4bb37dc2aL) - 0x2a1ec2949562fcd6L;
                  } else {
                     checkBlock:
                     if (count8 != 14) {
                        if (count8 == 17) {
                           count6 = 0;
                           value = (value ^ 0x409a76bc62d88fa2L) - 0x2777ac86cfa8577cL;
                           value2 = (value2 ^ 0x95c973039c77222L) - 0xd28924a1367d954L;
                           continue;
                        }

                        if (count8 == 2) {
                           return this.byteFlagArray10;
                        }

                        if (count8 == 13) {
                           count6++;
                           value = (value ^ -0x26cfed70b0e99b74L) + 0x42ed1a003c6f4b3eL;
                           value2 = (value2 ^ -0x7f06b0437829e202L) + 0x2bf25a2f3de03c7eL;
                        } else {
                           guardBlock: {
                              long value3;
                              long value4;
                              searchBlock: {
                                 if (count8 != 11) {
                                    if (count8 == 7) {
                                       count6 = 1;
                                       value = (value ^ 0x3a6395b9ab3952e6L) - 0x10fd75f53ebb31c0L;
                                       value2 = (value2 ^ -0x641b010d9d535bffL) - 0x5adbd5cdc0c99a31L;
                                       continue;
                                    }

                                    if (count8 == 3) {
                                       if (this.byteFlagArray10 == null) {
                                          value = (value ^ -0x5dab03aaff2033b7L) + 0x7da096edfdb18ba9L;
                                          value2 = (value2 ^ 0x2a30083cf070c4e2L) + 0x5a06d8e3139856efL;
                                       } else {
                                          value = (value ^ 0x2bd57468865510f5L) - 0xfbda3b72207ee43L;
                                          value2 = (value2 ^ -0x1807f29ab430da35L) - 0x4a73911cdb6d9d0L;
                                       }
                                       continue;
                                    }

                                    if (count8 == 8) {
                                       return this.byteFlagArray125;
                                    }

                                    if (count8 == 12) {
                                       this.countArray5[count6] = 0;
                                       value = (value ^ 0xadcb27d686574acL) + 0x15c456c7a7ec3d16L;
                                       value2 = (value2 ^ 0x25d5887a442a35bL) - 0x38ca2e3b0d309d55L;
                                       continue;
                                    }

                                    if (count8 == 19) {
                                       count6 = 0;
                                       value = (value ^ -0x24e42c4a21b53d9aL) + 0x5d58cdac2741c770L;
                                       value2 = (value2 ^ 0x273ff7ff327cddceL) - 0x45bb38bbe2d18bceL;
                                       continue;
                                    }

                                    if (count8 == 6) {
                                       value3 = (value ^ -0xdd5c89462175926L) - 0x11e62d697b189aa6L;
                                       value4 = (value2 ^ 0x5daf6f1f17c3dc15L) - 0x4da55703153fd415L;
                                       break searchBlock;
                                    }

                                    if (count8 == 15) {
                                       break guardBlock;
                                    }

                                    if (count8 != 16) {
                                       if (count8 == 4) {
                                          this.byteFlagArray10 = new byte[16];
                                          value = (value ^ -0x2fea684c8b46ee7dL) + 0x39a85ff67178a8cfL;
                                          value2 = (value2 ^ 0x4a4f99ce7b7e2ab9L) + 0x28bd265cca81bed3L;
                                          continue;
                                       }

                                       if (count8 == 18) {
                                          count6 = 0;
                                          value = (value ^ 0x7a40df76f8135c7cL) - 0x34bce970f0aaecL;
                                          value2 = (value2 ^ 0x60a2a23cadc40661L) + 0x73f9bd91ef0c14bL;
                                          continue;
                                       }
                                       break checkBlock;
                                    }

                                    if (byteValueArray[count7] != this.byteFlagArray109[count7]) {
                                       value = (value ^ -0x5056ca20f0a06191L) + 0x22729480e648d2bbL;
                                       value2 = (value2 ^ 0xcf4dc08c4bce114L) - 0x4a96f056968ffe23L;
                                       continue;
                                    }

                                    long value5 = (value ^ 0x197c93bfee6119f6L) + 0x12d1e8859c71daf4L;
                                    long value6 = (value2 ^ -0x1404fa6228a2aedeL) - 0x42ec0de6347aaec1L;
                                    count7++;
                                    value = (value5 ^ -0x164578c593ee02c1L) - 0x7c7ca94f09a4b72eL;
                                    value2 = (value6 ^ -0x5bed3fd45cfc4406L) - 0x1eb2619ee417af6fL;
                                 }

                                 if (count7 < byteValueArray.length) {
                                    value = (value ^ 0x65fdef7e64893f41L) - 0x31d357cdf7d6b7e6L;
                                    value2 = (value2 ^ -0x6c6abe35b56510d7L) - 0x7d460b6352597e7fL;
                                    continue;
                                 }

                                 value3 = (value ^ 0x741768b031b7c3c9L) + 0x81784a54f0135dcL;
                                 value4 = (value2 ^ -0x4daa03a594e7dfaaL) + 0x489475b2ac0054e3L;
                              }

                              if (count6 == 0) {
                                 value = (value3 ^ -0xddf951272b92df2L) - 0x198962f4139fb9adL;
                                 value2 = (value4 ^ 0xbd97b54f28606b2L) - 0x7eb99b3d7d7b215fL;
                                 continue;
                              }

                              return this.byteFlagArray10;
                           }
                        }

                        if (count6 >= 16) {
                           long value7 = (value ^ -0x7c9e4919a0314d73L) - 0x675bb1d7b34b5d45L;
                           long value8 = (value2 ^ 0x483a7a078b205088L) - 0x734f98c83575f850L;
                           if (this.byteFlagArray10 != null) {
                              value = (value7 ^ -0x27ecfce05b9be356L) + 0x3e03fe6a5ed5b773L;
                              value2 = (value8 ^ 0x507d17f3bd76a845L) + 0x5dfd3142649230daL;
                           } else {
                              value = (value7 ^ -0x60c03b95568fac86L) - 0x70b48da9eb37b48fL;
                              value2 = (value8 ^ -0x504fe3e7f6b1baacL) + 0x3e41aafe2ba6ae91L;
                           }
                        } else {
                           value = (value ^ -0x4ffe0f75a6a50bebL) - 0x68b3fa2c0edac06aL;
                           value2 = (value2 ^ -0x2fd922cca9fd15fdL) - 0x383f28dbdff944aaL;
                        }
                        continue;
                     } else {
                        count6++;
                        value = (value ^ 0x7ac253d114b46c5cL) + 0x62db64533c8d94bL;
                        value2 = (value2 ^ -0x364178e03851fe80L) - 0x2f146bcb0406f1fL;
                     }

                     if (count6 >= 16) {
                        long value9 = (value ^ -0x5ac022b805d0ac70L) - 0x36d6bc59a93ff867L;
                        long value10 = (value2 ^ -0xb22724c053bc37L) - 0x38de6b8148bd5118L;
                        int[] countArray9 = this.countArray5;
                        this.applyIndexArray(countArray9, countArray8);
                        value = (value9 ^ -0x25692cdbe52076e5L) + 0x626b84b000f0dbf0L;
                        value2 = (value10 ^ -0x787ab85495001babL) - 0x15a19f398a70b647L;
                     } else {
                        value = (value ^ -0x4af68204f9b0635eL) - 0x637d04221ce8220bL;
                        value2 = (value2 ^ 0x2bb004d4faaa6d38L) - 0x524741c1d4cc3e9fL;
                     }
                  }
               } else {
                  System.arraycopy(this.byteFlagArray125, 0, this.byteFlagArray10, 0, 16);
                  return this.byteFlagArray125;
               }
            } else if (this.byteFlagArray109 == null) {
               value = (value ^ 0x6ca50d0919618f00L) + 0x11c4a4dcd0095972L;
               value2 = (value2 ^ -0x62e2ee732e984949L) + 0x57f4f851abd87111L;
            } else {
               value = (value ^ -0x6e05bc83240b6501L) - 0x60dc9c0e6bce6013L;
               value2 = (value2 ^ -0x305bb6edda632349L) + 0x42974eb154348784L;
            }
         } else {
            this.byteFlagArray125[count6] = (byte)this.countArray5[count6];
            value = (value ^ -0x34d622cc18f38e0cL) + 0x579ebf35ef0fcfa9L;
            value2 = (value2 ^ -0x35d2411463091d61L) + 0x71ea798ead69472L;
         }
      }
   }

   private void applyByteArray2(byte[] byteValueArray) {
      long value = 0L;
      long value2 = 0L;
      int count6 = 0;
      int count7 = 0;
      long value3 = 0L;
      long value4 = 0x498c8dcba74187eL;
      long value5 = 0x29ef6944f93272b6L;

      while (true) {
         int count8 = (int)(value4 % 14L);
         if (count8 != 13) {
            if (count8 != 4) {
               if (count8 == 5) {
                  byteValueArray[count6 + 8] = (byte)((int)(value2 >>> 56 - count6 * 8 & 255L));
                  value4 = (value4 ^ -0x193ed21ea717bcbaL) - 0x52be67e82862a3dfL;
                  value5 = (value5 ^ -0x2806dd19b5f200dcL) + 0x3d24fbc8f63b59e2L;
                  continue;
               }

               if (count8 == 0) {
                  value3 = this.customByteArrayEntry.timestampArray[count6][count7];
                  value4 = (value4 ^ -0x7f4ee36ddb813e13L) - 0x6fde071caef83354L;
                  value5 = (value5 ^ -0x6072e56fe4fd84d4L) + 0x29e89c3e9c8366feL;
                  continue;
               }

               if (count8 != 1) {
                  if (count8 != 9) {
                     if (count8 != 2) {
                        if (count8 != 10) {
                           if (count8 == 6) {
                              count7 = byteValueArray[count6] & 255;
                              value4 = (value4 ^ 0x5b2f160b42005601L) - 0x20191db48a361ec2L;
                              value5 = (value5 ^ -0x7ed2f99041eb9aa0L) - 0x324de80b8adda68cL;
                              continue;
                           }

                           if (count8 == 8) {
                              value2 = 0L;
                              value4 = (value4 ^ -0x29b83db415ea05e5L) - 0x148f813d62da3482L;
                              value5 = (value5 ^ -0x53d7827ba11f0fd5L) + 0x6aee17e80e09b347L;
                              continue;
                           }

                           if (count8 == 3) {
                              value ^= value3 >>> 32;
                              value4 = (value4 ^ 0x532c8564e5818b10L) + 0x301e0106a535d7c6L;
                              value5 = (value5 ^ 0x73c82334d6cfbd5L) - 0x6c62dcf76e0c42caL;
                              continue;
                           }

                           if (count8 != 12) {
                              if (count8 != 11) {
                                 count6 = 0;
                                 value4 = (value4 ^ 0xa0edf7030b608bL) + 0xc5469447f4c70deL;
                                 value5 = (value5 ^ -0x5d63776a6127164eL) + 0x7c9dfc887ecebbc0L;
                              } else {
                                 value2 ^= value3 & 4294967295L;
                                 value4 = (value4 ^ -0x5a41478423453a7L) - 0x65e5a099addd678cL;
                                 value5 = (value5 ^ -0x2e3e373c419d0503L) - 0x65adbd0da5720ce3L;
                              }
                              continue;
                           }

                           count6++;
                           value4 = (value4 ^ 0x64eb959c4b76aa8eL) + 0x488cb8dcc6a62592L;
                           value5 = (value5 ^ -0x241d0705742b1da4L) + 0x190e2943d5780445L;
                        }

                        if (count6 >= 16) {
                           long value6 = (value4 ^ -0x5f35d70e2caa63aeL) + 0x7493957746c50cbeL;
                           long value7 = (value5 ^ -0xc89898cb5c31490L) + 0x8a2acc26e503567L;
                           count6 = 0;
                           value4 = (value6 ^ 0x346b3ed63ec97191L) - 0x601994d4a2ba5228L;
                           value5 = (value7 ^ 0x4c10ef631dc18e7bL) - 0xce49621433f5b22L;
                        } else {
                           value4 = (value4 ^ 0x1207580e5676d374L) - 0x6b63fcd88490dc2eL;
                           value5 = (value5 ^ 0x119955de3da6898L) - 0x5b213fe22eeadeffL;
                        }
                     } else {
                        value = 0L;
                        value4 = (value4 ^ 0x4bd7afd019ffd086L) + 0x21b2028961317d0L;
                        value5 = (value5 ^ 0x3d3a5c183874e9e3L) + 0x72f685c86ca41123L;
                     }
                  } else if (count7 == 0) {
                     value4 = (value4 ^ -0x7a01c2931286781cL) - 0x6d0b378bceb8ff91L;
                     value5 = (value5 ^ -0x60beca3a1c415607L) + 0x11503df1bc1d3e83L;
                  } else {
                     value4 = (value4 ^ 0x404ddeaddf47f8b5L) - 0x48bea93252ee1482L;
                     value5 = (value5 ^ -0x13f3012fbc2bcfd5L) - 0xb5c39032ebb319cL;
                  }
                  continue;
               }
            } else {
               count6++;
               value4 = (value4 ^ 0x7d0d15857aa99d75L) - 0x2139794b3647a6e0L;
               value5 = (value5 ^ 0x30e85741f58e975aL) + 0x4792c86759108c20L;
            }

            if (count6 >= 8) {
               return;
            }

            value4 = (value4 ^ 0x666e218d7d2e8b58L) - 0x52c50f700ee9cccL;
            value5 = (value5 ^ -0x5101adf4c808167bL) + 0x35b746df0b336394L;
         } else {
            byteValueArray[count6] = (byte)((int)(value >>> 56 - count6 * 8 & 255L));
            value4 = (value4 ^ 0x427029fc4136116eL) + 0x32fe00cf10a49912L;
            value5 = (value5 ^ -0x3ff38822fd9ab760L) + 0x3663631ed5cd1037L;
         }
      }
   }

   private ByteBuf resolveByteBuf(ByteBuf byteBuf) {
      ByteBuf byteBuf2 = null;
      byte[] byteValueArray = null;
      byte[] byteValueArray2 = null;
      long value2 = 0L;
      long value3 = 0L;
      long value4 = 0L;
      long value5 = 0L;
      int count6 = 0;
      byte[] byteValueArray3 = null;
      byte[] byteValueArray4 = null;
      byte[] byteValueArray5 = null;
      int[] countArray8 = null;
      int byteValue = 0;
      int count7 = 0;
      int count8 = 0;
      int[] countArray9 = null;
      byte[] byteValueArray6 = null;
      byte[] byteValueArray7 = null;
      long value6 = 0x6a80e5bbb3e3dd54L;
      long value7 = -0xed4f1323e197135L;

      while (true) {
         long value8;
         long value9;
         while (true) {
            int count9 = (int)(value6 % 43L);
            if (count9 == 29) {
               if (this.byteFlagArray150.length < 32) {
                  value6 = (value6 ^ -0x302d91f442c11e0eL) + 0x760f24880fcf9d7dL;
                  value7 = (value7 ^ -0x6470c34a01638674L) - 0x2a3658e25527a1f8L;
               } else {
                  value8 = (value6 ^ -0x4725e32f8d8a9667L) - 0x2d603892a17634b9L;
                  value9 = (value7 ^ 0x561174f53ec04fd5L) - 0x13b85c503cb16c13L;
                  break;
               }
            } else {
               evalBlock: {
                  checkBlock: {
                     if (count9 != 38) {
                        if (count9 == 5) {
                           count7++;
                           value6 = (value6 ^ -0x2935a9b019cc4889L) - 0x7510e54c73b522bdL;
                           value7 = (value7 ^ -0x6a7af462200a6b87L) - 0x23013b40ad122bf9L;
                           break checkBlock;
                        }

                        if (count9 == 42) {
                           break checkBlock;
                        }

                        if (count9 == 6) {
                           System.arraycopy(byteValueArray2, 0, byteValueArray4, 0, byteValueArray2.length);
                           value6 = (value6 ^ 0x49f5637b257c57dbL) + 0x3e834f0abfdd0221L;
                           value7 = (value7 ^ -0x605913f5b160663eL) - 0x5c6e64c0b601158dL;
                           continue;
                        }

                        if (count9 == 2) {
                           value3 = 25214903917L;
                           value6 = (value6 ^ 0x4dc94b07c01d7e8fL) + 0x30deaf819b249885L;
                           value7 = (value7 ^ -0x3d513fe8feb2664aL) + 0x4eef1d751ac39ccbL;
                           continue;
                        }

                        if (count9 == 10) {
                           System.arraycopy(byteValueArray6, 0, byteValueArray7, byteValueArray2.length + byteValueArray5.length, byteValueArray6.length);
                           value6 = (value6 ^ 0x672397b6ed2b6bebL) - 0x1a8c217e548433d7L;
                           value7 = (value7 ^ -0x30d58f93d8662172L) - 0x733fca12d44aab66L;
                           continue;
                        }

                        if (count9 == 41) {
                           byteValueArray5[byteValue + count8] = (byte)(byteValueArray[byteValue + count8] ^ (byte)countArray8[count8]);
                           value6 = (value6 ^ 0x398120ece0d3b62fL) - 0xcae7c3e9220d649L;
                           value7 = (value7 ^ -0x46449679ba7a455aL) - 0x39e502a67ff5f33dL;
                           continue;
                        }

                        if (count9 == 17) {
                           break evalBlock;
                        }

                        if (count9 == 22) {
                           value5 = 281474976710655L;
                           value6 = (value6 ^ 0xd30a056d028a059L) + 0x27cdea7e6ac9490cL;
                           value7 = (value7 ^ 0x530253fa7120516L) + 0x51c6c01cbd5b4023L;
                           continue;
                        }

                        if (count9 == 8) {
                           System.arraycopy(byteValueArray2, 0, byteValueArray7, 0, byteValueArray2.length);
                           value6 = (value6 ^ -0x5c890256431121aL) + 0x5eeeabde3f8ebd0fL;
                           value7 = (value7 ^ 0x14801ab6fc1d16b8L) + 0x6fba20ed3b12b090L;
                           continue;
                        }

                        if (count9 == 35) {
                           count7 = 0;
                           value6 = (value6 ^ 0x472fa4ed630dc454L) + 0x2c61804e483d8841L;
                           value7 = (value7 ^ -0x3c8c024440be3d47L) + 0x5752a329422b40acL;
                           continue;
                        }

                        if (count9 == 4) {
                           byteBuf2 = Unpooled.copiedBuffer(byteValueArray);
                           return byteBuf2;
                        }

                        if (count9 == 40) {
                           this.specialLongEntry.updateState4();
                           value6 = (value6 ^ 0x37ec6ffcc2d08552L) - 0x5c36f8d3467f9241L;
                           value7 = (value7 ^ 0x1dbda158575596d1L) + 0x54362ccecd3b16ceL;
                           continue;
                        }

                        if (count9 == 13) {
                           count7 = Math.min(16, byteValueArray.length - byteValue);
                           value6 = (value6 ^ 0xa0fe38c36cb308bL) + 0x249f6ebc9107d5e8L;
                           value7 = (value7 ^ 0x2f78e707e382abd3L) + 0x71398668adb33b08L;
                           continue;
                        }

                        if (count9 == 9) {
                           System.arraycopy(byteValueArray5, 0, byteValueArray7, byteValueArray2.length, byteValueArray5.length);
                           value6 = (value6 ^ -0x4e08ebdffcd76f13L) + 0x6fc861183a7fd533L;
                           value7 = (value7 ^ 0x533463e9d80cf1a4L) + 0x45df9e6008b2d95dL;
                           continue;
                        }

                        if (count9 == 37) {
                           byteValueArray7 = new byte[byteValueArray2.length + byteValueArray5.length + byteValueArray6.length];
                           value6 = (value6 ^ -0x7a65fd5c35f3fd84L) + 0x275904c8d581cf5fL;
                           value7 = (value7 ^ -0x1a51be0f89d06291L) - 0x6a17d268cf033b7fL;
                           continue;
                        }

                        if (count9 == 39) {
                           return byteBuf2;
                        }

                        if (count9 == 20) {
                           byteValue = 0;
                           value6 = (value6 ^ 0x642e2d9e3d0f7459L) - 0x1f4fe60f7ea73316L;
                           value7 = (value7 ^ 0x69e32dcdbd09204cL) - 0x414f630766d326c2L;
                           continue;
                        }

                        if (count9 == 21) {
                           byteValueArray = new byte[byteBuf.readableBytes()];
                           value6 = (value6 ^ -0x354e107d7a50f5e5L) + 0x3cd3b43141b63d18L;
                           value7 = (value7 ^ -0x2a8132bb198cdd74L) + 0x2bb503b9f41c3c2cL;
                           continue;
                        }

                        if (count9 == 12) {
                           countArray8 = new int[16];
                           value6 = (value6 ^ 0x40a8c29bdccf761bL) - 0x3eda7ffae281cf8fL;
                           value7 = (value7 ^ 0x2d267518a77b798eL) - 0x383bf35f1247d9caL;
                           continue;
                        }

                        long value10;
                        long value11;
                        guardBlock: {
                           searchBlock:
                           if (count9 != 31) {
                              if (count9 != 1) {
                                 if (count9 == 19) {
                                    value10 = (value6 ^ -0x7c6f4b96cfb1dcf4L) + 0x7d8f4c16f035e304L;
                                    value11 = (value7 ^ 0x7684a13fe2af9113L) - 0x127c9f2121a29113L;
                                    break guardBlock;
                                 }

                                 if (count9 == 7) {
                                    value4 = 11L;
                                    value6 = (value6 ^ -0x67059515361524d4L) + 0x315f3d8e480928a8L;
                                    value7 = (value7 ^ 0x2b714384072c80deL) + 0x18b7992e4362d2afL;
                                    continue;
                                 }

                                 if (count9 == 30) {
                                    count8 = 0;
                                    value6 = (value6 ^ 0x49800087e46f6430L) + 0x2a5078536e4dcf81L;
                                    value7 = (value7 ^ -0x1f473e7308a15b12L) + 0x1328e3155b4de53L;
                                    continue;
                                 }

                                 if (count9 == 24) {
                                    if (this.byteFlagArray113.length < 32) {
                                       value6 = (value6 ^ -0x44b5afda78ad7899L) + 0x69f6a002980cc2c6L;
                                       value7 = (value7 ^ -0x676a6e879dfc6ee7L) + 0x483a7468658c4c7eL;
                                       continue;
                                    }

                                    value8 = (value6 ^ 0x5740ab56af5122ebL) + 0x38581c826eaf933cL;
                                    value9 = (value7 ^ -0x121749878b06b67bL) + 0x7e6896aaccc99729L;
                                    break;
                                 }

                                 if (count9 == 14) {
                                    byteValueArray4 = new byte[16];
                                    value6 = (value6 ^ 0x387364ecd69cb438L) - 0xb9101f70b4c84efL;
                                    value7 = (value7 ^ -0x926f0c5ddd655bbL) - 0x5721fad1345c2bd3L;
                                    continue;
                                 }

                                 if (count9 != 34) {
                                    if (count9 == 33) {
                                       return byteBuf2;
                                    }

                                    if (count9 == 32) {
                                       value2 = System.nanoTime();
                                       value6 = (value6 ^ -0x89436af9688af9aL) - 0x5424415cb03dec8cL;
                                       value7 = (value7 ^ -0x21fcf1fcade1a4b6L) - 0x66873d8669e7ae5aL;
                                       continue;
                                    }

                                    if (count9 == 23) {
                                       byte[] byteFlagArray206 = this.byteFlagArray185;
                                       byteValueArray3 = this.resolveByteArray(byteFlagArray206, countArray9);
                                       value6 = (value6 ^ 0x5498b5caf4ed81e3L) - 0x763c7e1fe3dd765L;
                                       value7 = (value7 ^ -0x4e9c0ec359dc0e74L) - 0x512175df8953d3ecL;
                                       continue;
                                    }

                                    if (count9 == 36) {
                                       count8++;
                                       value6 = (value6 ^ 0x4b84aba050e7afebL) + 0xd85b1dea484b2c2L;
                                       value7 = (value7 ^ 0x440bf5743cade0ebL) + 0x4660f12cbec35f00L;
                                       break evalBlock;
                                    }

                                    if (count9 != 16) {
                                       if (count9 != 18) {
                                          if (count9 != 15) {
                                             if (count9 != 11) {
                                                if (count9 != 28) {
                                                   if (count9 != 27) {
                                                      if (count9 != 25) {
                                                         if (count9 != 3) {
                                                            if (count9 != 0) {
                                                               count6 = 0;
                                                               value6 = (value6 ^ -0x3475fcda06c24d80L) + 0x6b9a59367f6adae7L;
                                                               value7 = (value7 ^ -0x3a080046562348f3L) - 0x1c9165a553bf4410L;
                                                            } else {
                                                               value2 = value2 * value3 + value4 & value5;
                                                               value6 = (value6 ^ -0x4840ded2fa9ffb4fL) - 0x228221e5b4bd1c29L;
                                                               value7 = (value7 ^ 0xfd61edabd084ad8L) + 0x1f12da1bc6b71567L;
                                                            }
                                                         } else {
                                                            byteValueArray5 = new byte[byteValueArray.length];
                                                            value6 = (value6 ^ 0x386e0508760a6d9L) - 0x6d006f19362936efL;
                                                            value7 = (value7 ^ 0x3cef348f63899d20L) - 0x6cf1196d22e9e0bbL;
                                                         }
                                                      } else {
                                                         byteValueArray4[15] = 1;
                                                         value6 = (value6 ^ -0x72d834d6633ee81cL) - 0x5d4c9bf6472c8c3eL;
                                                         value7 = (value7 ^ -0x566f391c05a65812L) + 0x3de989284cbdbf46L;
                                                      }
                                                   } else {
                                                      byteBuf2 = null;
                                                      value6 = (value6 ^ 0x1bdcada20541ef54L) - 0x64e125839d6bf654L;
                                                      value7 = (value7 ^ -0x1ce7d63ff35fdad3L) + 0x4420a57f29890d87L;
                                                   }
                                                } else {
                                                   byteBuf.readBytes(byteValueArray);
                                                   value6 = (value6 ^ 0x2c838a8fd65721a1L) + 0x1c2a51fc3a8001bbL;
                                                   value7 = (value7 ^ 0x725021e1a5ea5ff5L) + 0x7e622851d132764fL;
                                                }
                                             } else {
                                                byteValueArray2[count6] = (byte)((int)(value2 >> 16 & 255L));
                                                value6 = (value6 ^ -0x2ecf90761e22dd5eL) - 0x17cd2ab2f5f7423eL;
                                                value7 = (value7 ^ 0x47d30feb4d0da8f9L) - 0x2b71723d6cdc7039L;
                                             }
                                             continue;
                                          }

                                          byteBuf2 = Unpooled.copiedBuffer(byteValueArray7);
                                          return byteBuf2;
                                       }

                                       countArray8[count7] = byteValueArray4[count7] & 255;
                                       value6 = (value6 ^ -0x29edd0a0f4831213L) + 0x260712fa5e714e45L;
                                       value7 = (value7 ^ -0x26cfef42d9323c18L) - 0x61a83c895c121ee6L;
                                       continue;
                                    }

                                    count6++;
                                    value6 = (value6 ^ 0x128bb9af4cd8e235L) - 0x14a19267176a4ae6L;
                                    value7 = (value7 ^ -0x440b836bfab7646aL) - 0x6be29e864f0d05d5L;
                                    break searchBlock;
                                 }

                                 if (++byteValueArray4[count8] != 0) {
                                    value6 = (value6 ^ 0x7dff284b3b05b601L) - 0x16221095338b950eL;
                                    value7 = (value7 ^ 0x78ec72078756c28fL) - 0x6db7aae2d5bad242L;
                                    continue;
                                 }

                                 long value12 = (value6 ^ -0x46801cbe58c42a3eL) + 0x265d3664506e4835L;
                                 long value13 = (value7 ^ -0x7de1b2133e295366L) - 0x23258189973ab04fL;
                                 count8--;
                                 value6 = (value12 ^ 0x7f092d0f96396cf8L) - 0x4380c60d2e4206d5L;
                                 value7 = (value13 ^ 0x43bdb3235e60f580L) + 0x932954c3c0837a0L;
                              }

                              if (count8 >= 12) {
                                 value6 = (value6 ^ 0xc61f3813f0bdbefL) + 0x22cd84da05b55b43L;
                                 value7 = (value7 ^ -0x33225671462f1c3L) + 0x61063c1540a3f5b0L;
                                 continue;
                              }

                              value10 = (value6 ^ 0x387bbaaa6ecd677dL) + 0x4342358696e03a8L;
                              value11 = (value7 ^ 0xb5c98e3673447ffL) - 0x4fabbf0b9cd466c7L;
                              break guardBlock;
                           }

                           if (count6 >= byteValueArray2.length) {
                              long value14 = (value6 ^ 0x2499057cfd4681a6L) - 0x17011968a0fd569L;
                              long value15 = (value7 ^ 0x5fcf0cac7b9af757L) - 0x47d6325209952966L;
                              byte[] byteFlagArray207 = this.byteFlagArray185;
                              countArray9 = this.resolveIndexArray(byteFlagArray207);
                              value6 = (value14 ^ 0x39f1683a74bca3ffL) - 0x1b7db4dd4c8ee112L;
                              value7 = (value15 ^ -0x6cc69cf76d7baff2L) + 0x6c42dae9ab34b351L;
                           } else {
                              value6 = (value6 ^ 0x479388075b82a6fbL) + 0x225cc61e20934808L;
                              value7 = (value7 ^ 0x7579eb9de5f347d0L) - 0x6b2778638da71e68L;
                           }
                           continue;
                        }

                        byteValue += 16;
                        value6 = (value10 ^ -0x39ad344bbb855e9cL) + 0x7b7785901d79edbcL;
                        value7 = (value11 ^ -0x469ead481441043cL) - 0x37039a62e737a195L;
                     }

                     if (byteValue >= byteValueArray.length) {
                        long value16 = (value6 ^ 0x69173e6ce5de2a8fL) - 0x22b36ea7742ab1ddL;
                        long value17 = (value7 ^ 0x1d2637fa22e0a282L) - 0x17436d2015d9f8a5L;
                        Object entry = null;
                        byteValueArray6 = this.resolveByteArray(byteValueArray2, byteValueArray5, (byte[])entry, countArray9, byteValueArray3);
                        value6 = (value16 ^ 0x16f6096e63eb5e8eL) + 0x55e659f932f67dd9L;
                        value7 = (value17 ^ -0x33079787e5aca3c2L) + 0x3339f52f06de20dfL;
                     } else {
                        value6 = (value6 ^ -0x35fa3922ec6083a4L) - 0x25cdc8ec577474c3L;
                        value7 = (value7 ^ -0xacd2bd753e17353L) + 0x172cd747c71bb43eL;
                     }
                     continue;
                  }

                  if (count7 >= 16) {
                     long value18 = (value6 ^ 0x130a94c754f86435L) - 0x4aaee4b41f7573ffL;
                     long value19 = (value7 ^ -0x17c592ef4d8626bcL) - 0x67a97543979236e2L;
                     this.applyIndexArray(countArray8, countArray9);
                     value6 = (value18 ^ 0x5a897401db073e28L) - 0xdb550ba64194706L;
                     value7 = (value19 ^ 0x354f8753418532ccL) + 0x6fb536dd4041b8c6L;
                  } else {
                     value6 = (value6 ^ -0x79ef92e6eded2877L) + 0x2c65c22e3a5e8415L;
                     value7 = (value7 ^ -0x7fdc60ce204a6527L) - 0x472afb58ab7f9817L;
                  }
                  continue;
               }

               if (count8 >= count7) {
                  long value20 = (value6 ^ -0x5c9461b9c699a641L) + 0x5616477b12051cf6L;
                  long value21 = (value7 ^ -0x4d9cec04225c3a25L) - 0x37f619439ec2c1f0L;
                  count8 = byteValueArray4.length - 1;
                  value6 = (value20 ^ -0x345a664298a6694dL) + 0x4c31c16d4dfb360fL;
                  value7 = (value21 ^ 0x2c79124e06676cc7L) + 0x6cff9826adcf22fdL;
               } else {
                  value6 = (value6 ^ 0x3523da7d0a7a4d1eL) - 0x36f1df90cd236741L;
                  value7 = (value7 ^ 0x6ff17d63cbb9c322L) + 0x1e3317448fc9c262L;
               }
            }
         }

         byteValueArray2 = new byte[12];
         value6 = (value8 ^ -0x2bd3243e12184768L) - 0x5d63bd69d464dae5L;
         value7 = (value9 ^ 0x530dc65a7f06858cL) + 0x16bb9e3ba0fefb3fL;
      }
   }

   private int[] resolveIndexArray(byte[] byteValueArray) {
      int byteValue = 0;
      int count6 = 0;
      int count7 = 0;
      int[] countArray8 = null;
      int count8 = 0;
      int count9 = 0;
      int[] countArray9 = null;
      long value = 0x578bfedda85b2db2L;
      long value2 = -0x397ff0cce0df4bbL;

      while (true) {
         int count10 = (int)(value % 43L);
         if (count10 != 14) {
            branchBlock: {
               scanBlock: {
                  evalBlock: {
                     long value3;
                     long value4;
                     checkBlock: {
                        if (count10 == 3) {
                           count8++;
                           value = (value ^ 0x14c4c548b78da7a8L) - 0xe3bb13c1ee9a649L;
                           value2 = (value2 ^ -0x10b924215085350cL) - 0x187ab1de7d702693L;
                        } else {
                           guardBlock: {
                              searchBlock:
                              if (count10 != 6) {
                                 if (count10 == 16) {
                                    if (this.byteFlagArray109 == null) {
                                       value = (value ^ 0x4a0f789b0253d9f0L) - 0x19f80639e7edca8fL;
                                       value2 = (value2 ^ -0x4a60d4b963a5e682L) - 0x603c5e9c1a4ca44cL;
                                    } else {
                                       value = (value ^ -0x6eec96a2b28cc015L) + 0x4a41205e8205ef9fL;
                                       value2 = (value2 ^ 0x75ace0146a01b683L) + 0x385720eba2269091L;
                                    }
                                    continue;
                                 }

                                 if (count10 == 8) {
                                    value = (value ^ 0x212f71d372242621L) + 0x2fc66f2ec2b95727L;
                                    value2 = (value2 ^ 0x1e70f78a410c71c9L) - 0x1d6f8c2fae003fecL;
                                    break scanBlock;
                                 }

                                 if (count10 == 35) {
                                    countArray8 = new int[count7];
                                    value = (value ^ 0x710e80e6a4e338afL) + 0x6b8fe4abf8f378a3L;
                                    value2 = (value2 ^ 0x5c1ad0d23d50c7e5L) - 0x42afe8c3d2d8466cL;
                                    continue;
                                 }

                                 if (count10 == 37) {
                                    countArray8[count8] = (byteValueArray[4 * count8] & 255) << 24
                                       | (byteValueArray[4 * count8 + 1] & 255) << 16
                                       | (byteValueArray[4 * count8 + 2] & 255) << 8
                                       | byteValueArray[4 * count8 + 3] & 255;
                                    value = (value ^ -0x7701eb429100b398L) + 0x7d6bd92f1605ae06L;
                                    value2 = (value2 ^ -0x54c69cebbbbbac12L) - 0x6897dcbf2dd00204L;
                                    continue;
                                 }

                                 if (count10 == 11) {
                                    if (this.countArray6 == null) {
                                       value = (value ^ 0xa617824f3d04b77L) - 0x53106af4b64c41e7L;
                                       value2 = (value2 ^ -0x1619b0292b97ffdL) + 0xa4d92c2e001784aL;
                                       continue;
                                    }

                                    return this.countArray6;
                                 }

                                 if (count10 == 17) {
                                    break guardBlock;
                                 }

                                 if (count10 == 22) {
                                    if (count8 % count6 != 4) {
                                       value = (value ^ 0x603f8f35cc76c6f3L) + 0x6f5f0613081103d7L;
                                       value2 = (value2 ^ 0x69e407c41921d3c9L) + 0x14eb5037caaacd7aL;
                                    } else {
                                       value = (value ^ -0x54ee94bda578e9cdL) - 0x5c6abe6fbfdff36bL;
                                       value2 = (value2 ^ 0x32badae8fc3cb1e9L) + 0x537e354b0a5cd3deL;
                                    }
                                    continue;
                                 }

                                 if (count10 == 19) {
                                    countArray9[4 * count9 + 1] = countArray8[count9] >>> 16 & 0xFF;
                                    value = (value ^ 0x136ba7ab43af70b1L) - 0x43aad4a9a81d0befL;
                                    value2 = (value2 ^ -0x6d266d1a4504fda1L) - 0x4d2198d81c636c4fL;
                                    continue;
                                 }

                                 if (count10 == 34) {
                                    if (count8 % count6 != 0) {
                                       long value5 = (value ^ -0x78acba8782ff09dbL) + 0x1b60c34e0814a029L;
                                       long value6 = (value2 ^ -0x7ee9cdd12672eb20L) - 0x532c336da981628aL;
                                       if (count6 <= 6) {
                                          value = (value5 ^ 0xaecef1c6a2550e2L) + 0x6174fe1bc67c0e42L;
                                          value2 = (value6 ^ -0x736d1858a156e8d3L) + 0x548dbcc4ea774987L;
                                       } else {
                                          value = (value5 ^ -0x2470f8d55478bbf6L) - 0x5f4a30974ea11c70L;
                                          value2 = (value6 ^ -0x7f6c5cd079a5b4e5L) + 0x648524495fde0552L;
                                       }
                                    } else {
                                       value = (value ^ 0x26b4f7aac37ab273L) - 0x41fc17c01553430dL;
                                       value2 = (value2 ^ -0x4f026317708c5f30L) + 0x6cb9cb80c62a8f89L;
                                    }
                                    continue;
                                 }

                                 if (count10 == 30) {
                                    break evalBlock;
                                 }

                                 if (count10 == 2) {
                                    count9 = countArray8[count8 - 1];
                                    value = (value ^ 0x272ec7a375ca77ffL) + 0x5603c50767dd6933L;
                                    value2 = (value2 ^ 0x2563255ad2086539L) - 0x6a359c5d7181a4afL;
                                    continue;
                                 }

                                 if (count10 == 5) {
                                    break scanBlock;
                                 }

                                 if (count10 == 12) {
                                    byteValue = 0;
                                    value = (value ^ -0x54c31eecf44b343fL) - 0x4d1bb223f906e04dL;
                                    value2 = (value2 ^ 0x6094fea1a84915fcL) - 0x29dc588d0b2040fL;
                                    continue;
                                 }

                                 if (count10 == 10) {
                                    count9 = count9 << 8 & -256 | count9 >>> 24 & 0xFF;
                                    value = (value ^ 0x7e51ce114c895527L) - 0x1645d6956d401592L;
                                    value2 = (value2 ^ -0x46adf6cbf27a88adL) - 0x4b77780245f9b82bL;
                                    continue;
                                 }

                                 if (count10 == 39) {
                                    count8 = 0;
                                    value = (value ^ 0xce3da82f98f81d7L) - 0x60e31dbe4cc09420L;
                                    value2 = (value2 ^ 0x7bbea38691917b2eL) + 0x5893892695df362fL;
                                    continue;
                                 }

                                 if (count10 == 4) {
                                    count9 ^= countArray2[count8 / count6] << 24;
                                    value = (value ^ 0x65e23123d8340056L) - 0x34cf2a35f0386f03L;
                                    value2 = (value2 ^ -0x37e8b23c1c5a1a85L) - 0x14d14e9aa04c3487L;
                                    continue;
                                 }

                                 if (count10 == 38) {
                                    countArray9[4 * count9] = countArray8[count9] >>> 24 & 0xFF;
                                    value = (value ^ 0x3ad40b0119a4496cL) + 0x378121858b5353a5L;
                                    value2 = (value2 ^ -0x52d0388b76f2390eL) - 0x4a74331a448e394L;
                                    continue;
                                 }

                                 if (count10 == 0) {
                                    count7 = 4 * (byteValue + 1);
                                    value = (value ^ 0x3ecda995330cc679L) + 0x5dd539ea34d6fedbL;
                                    value2 = (value2 ^ -0x206afb768393b327L) - 0x6c4fe2eb6abc410fL;
                                    continue;
                                 }

                                 if (count10 != 9) {
                                    if (count10 == 40) {
                                       byteValue = 1;
                                       value = (value ^ -0x111006a77c47c75aL) + 0x56c7c4deacb61cb2L;
                                       value2 = (value2 ^ 0x7e1ef2c05e7cca8fL) - 0x658852a6c07b589dL;
                                       continue;
                                    }

                                    if (count10 == 33) {
                                       count9 = 0;
                                       value = (value ^ -0x4cbcedc41eb93f28L) + 0x508fdfd053813912L;
                                       value2 = (value2 ^ -0x7fd50077d007472L) - 0x6e2a0e3a2fd6f108L;
                                       continue;
                                    }

                                    if (count10 == 31) {
                                       return countArray9;
                                    }

                                    if (count10 == 24) {
                                       this.countArray6 = countArray9;
                                       return countArray9;
                                    }

                                    if (count10 == 20) {
                                       count9 = countArray7[count9 >>> 24 & 0xFF] << 24
                                          | countArray7[count9 >>> 16 & 0xFF] << 16
                                          | countArray7[count9 >>> 8 & 0xFF] << 8
                                          | countArray7[count9 & 0xFF];
                                       value = (value ^ 0x1f4d0ea6ee93fdb8L) - 0x296753657077a6f8L;
                                       value2 = (value2 ^ -0x471b6cf22e51a92cL) + 0x886e8896fd4038bL;
                                       continue;
                                    }

                                    if (count10 == 1) {
                                       byteValue = 12;
                                       value = (value ^ 0x7e30a24c34720422L) - 0x4cc9a98661dd1334L;
                                       value2 = (value2 ^ 0x24cb24fc6d8f8227L) + 0x60cc8e782224b333L;
                                       continue;
                                    }

                                    if (count10 == 36) {
                                       count9++;
                                       value = (value ^ -0x794d805e75e87d7fL) + 0x1130e98ffc8ca0beL;
                                       value2 = (value2 ^ 0x6023a29a6685d772L) + 0x128c1930ce8d35d6L;
                                       break evalBlock;
                                    }

                                    if (count10 == 25) {
                                       countArray9[4 * count9 + 2] = countArray8[count9] >>> 8 & 0xFF;
                                       value = (value ^ -0x1299d7330ece2cb8L) + 0x673cac2ae7d340a5L;
                                       value2 = (value2 ^ -0x3ec52c61c28ae4b7L) - 0x258e51312df42ceeL;
                                       continue;
                                    }

                                    if (count10 == 21) {
                                       value = (value ^ -0x3acea40ff7224f93L) - 0x5e5756009b6c124dL;
                                       value2 = (value2 ^ -0x6c990736d743597aL) + 0x4e61afe29c899a20L;
                                       break branchBlock;
                                    }

                                    if (count10 == 42) {
                                       if (byteValueArray.length != this.byteFlagArray109.length) {
                                          value = (value ^ -0x379da9797c2cd6abL) + 0x2ad09eb2dd1e0106L;
                                          value2 = (value2 ^ 0x29dcb3b7ee572a0bL) - 0x205e54b7cf1f66dL;
                                       } else {
                                          value = (value ^ 0x1e477639363a4edbL) + 0x8695a84c1dd58bdL;
                                          value2 = (value2 ^ -0x2ea19fb16ecefee0L) + 0x37c5cc0c46ac7966L;
                                       }
                                       continue;
                                    }

                                    if (count10 == 32) {
                                       return this.countArray6;
                                    }

                                    if (count10 == 29) {
                                       value3 = (value ^ -0x28dfc5dc0647698cL) - 0x50e009e15887958cL;
                                       value4 = (value2 ^ 0x26b637838db47680L) - 0x229634828a545680L;
                                       break checkBlock;
                                    }

                                    if (count10 == 15) {
                                       break branchBlock;
                                    }

                                    if (count10 == 27) {
                                       byteValue = 10;
                                       value = (value ^ 0x3322a414e5be17e2L) + 0xa277a7bd8db6ed1L;
                                       value2 = (value2 ^ -0x6b98c0a34624c713L) - 0x18724b648614b448L;
                                       continue;
                                    }

                                    if (count10 != 23) {
                                       if (count10 == 28) {
                                          value = (value ^ 0x2f29ed1954d68764L) + 0x8c583efa1ddace2L;
                                          value2 = (value2 ^ 0x7536a176651bca5fL) - 0x1bc77916ddc58614L;
                                          break scanBlock;
                                       }

                                       if (count10 != 18) {
                                          if (count10 != 13) {
                                             if (count10 != 41) {
                                                if (count10 != 7) {
                                                   System.arraycopy(byteValueArray, 0, this.byteFlagArray109, 0, byteValueArray.length);
                                                   value = (value ^ -0x121d6d7a4812908eL) - 0x6301ae49ffd26833L;
                                                   value2 = (value2 ^ 0xa8dcd0f7d1a835dL) + 0x71865e67b39f06c0L;
                                                } else {
                                                   countArray9[4 * count9 + 3] = countArray8[count9] & 0xFF;
                                                   value = (value ^ 0x79b260c963bf8115L) + 0x5675c5f461d7f3c1L;
                                                   value2 = (value2 ^ -0x73cd2457733210bdL) - 0x14cf330dd419b966L;
                                                }
                                             } else {
                                                count6 = 0;
                                                value = (value ^ -0x3fdc373c0980ed8dL) - 0x381e0a391d0de74bL;
                                                value2 = (value2 ^ 0x36e695cb4e048ad8L) - 0x28248f38fcdf6afbL;
                                             }
                                          } else {
                                             count9 = countArray7[count9 >>> 24 & 0xFF] << 24
                                                | countArray7[count9 >>> 16 & 0xFF] << 16
                                                | countArray7[count9 >>> 8 & 0xFF] << 8
                                                | countArray7[count9 & 0xFF];
                                             value = (value ^ 0x7849263c64ba4027L) + 0x5cb105deba7666dbL;
                                             value2 = (value2 ^ -0x6e42393d10e2b3a8L) - 0x19775f7d03b3988cL;
                                          }
                                          continue;
                                       }

                                       count8++;
                                       value = (value ^ 0x1c2ba65fcb0fc6f4L) - 0x527dcfc1ec7d00f8L;
                                       value2 = (value2 ^ 0xc709f97c7491392L) + 0x49d45e98e2cee15bL;
                                       break searchBlock;
                                    }
                                 } else {
                                    if (byteValueArray[count6] != this.byteFlagArray109[count6]) {
                                       value = (value ^ -0xf1d9d3477d44718L) + 0x644f0c8f2abe09fdL;
                                       value2 = (value2 ^ 0x1f57ba5d708ccdb3L) - 0x5af10aa93be5a9f6L;
                                       continue;
                                    }

                                    long value7 = (value ^ 0xe997480fb0e109bL) + 0x19d48db4ec27ca00L;
                                    long value8 = (value2 ^ -0x21a47aaaab7c2a47L) + 0x3e8862767326cd1L;
                                    count6++;
                                    value = (value7 ^ 0x780a1741337e739fL) + 0x454c8a6848ce07d6L;
                                    value2 = (value8 ^ 0x4c288bed1a481fc9L) + 0x330ac762be9b30d6L;
                                 }

                                 if (count6 < byteValueArray.length) {
                                    value = (value ^ -0x1545a80e17cbfc80L) - 0x5305d02cb489d252L;
                                    value2 = (value2 ^ 0x5946cf95b6be25ceL) - 0x77890039c6af9f7fL;
                                    continue;
                                 }

                                 value3 = (value ^ 0x4b8bd4bafda011a6L) + 0x57464d4f0307f60fL;
                                 value4 = (value2 ^ -0x5bbc0fe7b6707014L) + 0x5d0fcfb8923460b7L;
                                 break checkBlock;
                              }

                              if (count8 >= count6) {
                                 long value9 = (value ^ 0x3727a76f9ad02348L) + 0x4385906dbaaddb84L;
                                 long value10 = (value2 ^ 0x64eb87d6b4ddb4d7L) + 0x1e659687b357bee0L;
                                 count8 = count6;
                                 value = (value9 ^ -0x69de5d45d57a283L) - 0x6b0895e65911ddbcL;
                                 value2 = (value10 ^ -0x79cc88f15b00c0a9L) - 0x70fc1e598e8d76ccL;
                              } else {
                                 value = (value ^ 0x1fef0bf586eddc5dL) + 0x65f3565bea968091L;
                                 value2 = (value2 ^ 0x63bdc66ffcbfb31cL) + 0x2cf096cbddcd8b39L;
                              }
                              continue;
                           }
                        }

                        if (count8 >= count7) {
                           long value11 = (value ^ -0x7f8840b2ebbe1ba1L) - 0x7e9e4b394c556354L;
                           long value12 = (value2 ^ 0x6b754f788e6c96d9L) + 0x6267c2bb627e554eL;
                           countArray9 = new int[count7 * 4];
                           value = (value11 ^ 0x412f0e0dd403250fL) - 0x66260d67421e3f1cL;
                           value2 = (value12 ^ -0x6a17eab02bed8e7L) - 0xf1d73f167b1865dL;
                        } else {
                           value = (value ^ 0x7bfab0efb68849b0L) - 0x14465c5c5be991dfL;
                           value2 = (value2 ^ 0xffb2cbc627e0a97L) - 0x77cc698d1d005320L;
                        }
                        continue;
                     }

                     if (byteValue == 0) {
                        value = (value3 ^ 0x1aa40720b2dacf7eL) - 0x6e035e4521e4dcd8L;
                        value2 = (value4 ^ -0xdde79c602971999L) - 0x3677029fbb351043L;
                     } else {
                        value = (value3 ^ 0x19fc752e36a8b017L) - 0x15da1939d4bb58f5L;
                        value2 = (value4 ^ 0x697e7ffff0fc2f77L) + 0x6685282c9b1abe84L;
                     }
                     continue;
                  }

                  if (count9 >= count7) {
                     long value13 = (value ^ 0x137a35b42a9565eaL) + 0x5e6a9f1a53fac3dbL;
                     long value14 = (value2 ^ -0x333dcb620d7e666aL) - 0x6de9c40f9b8ac87eL;
                     this.byteFlagArray109 = new byte[byteValueArray.length];
                     value = (value13 ^ -0x1b8cc7582fc3f715L) - 0x51be7eb3a4540bdfL;
                     value2 = (value14 ^ -0x275121918e507747L) - 0x7658bf1c57534f8aL;
                  } else {
                     value = (value ^ 0x273ebac7cf8e79e3L) + 0x12da84b51103e488L;
                     value2 = (value2 ^ 0x5619581d8060742cL) + 0x4d542508eac60d26L;
                  }
                  continue;
               }

               count6 = byteValueArray.length / 4;
               value = (value ^ 0x146eb6a9cbc9245aL) + 0x535664c8cde840cL;
               value2 = (value2 ^ -0x6a86799795772f1dL) + 0x76dd42ea9678fb77L;
               continue;
            }

            countArray8[count8] = countArray8[count8 - count6] ^ count9;
            value = (value ^ -0x57421fc5bc1ef2d5L) + 0x5b08a60feb71d0fdL;
            value2 = (value2 ^ -0x3154d48e7919a387L) + 0x287e31f95e67a77dL;
         } else if (byteValueArray.length != 16) {
            long value15 = (value ^ 0x1bfdfacb61684691L) + 0xcfdf8108cff77cfL;
            long value16 = (value2 ^ 0x7270464c6836c26aL) + 0xbe643fdb1d84048L;
            if (byteValueArray.length != 24) {
               long value17 = (value15 ^ -0x24e93ef9f9c8843dL) + 0x5ef4f75019f4f3fL;
               long value18 = (value16 ^ 0x58e7a5307dfdf359L) - 0x3228e5ac57a2ee21L;
               byteValue = 14;
               value = (value17 ^ -0x35003caa76216d19L) + 0x656fb23346d59961L;
               value2 = (value18 ^ 0x495a7d4c2793e403L) + 0x17dbb2aedfc293a8L;
            } else {
               value = (value15 ^ -0x36dee30fb8e567e8L) + 0x42b3a06d364b7c9eL;
               value2 = (value16 ^ -0x54fb55b5a81da363L) + 0x3bf66da940d84617L;
            }
         } else {
            value = (value ^ -0x60ad19040e09dd90L) - 0x74790aae81c39c01L;
            value2 = (value2 ^ 0x502d1b5968c03ea0L) - 0x6553b05c7119b657L;
         }
      }
   }

   private void applyIndexArray(int[] countArray8, int[] countArray9) {
      int count6 = countArray9.length / 16 - 1;

      for (int index = 0; index < 16; index++) {
         countArray8[index] ^= countArray9[index];
      }

      for (int index2 = 1; index2 < count6; index2++) {
         for (int index3 = 0; index3 < 16; index3++) {
            countArray8[index3] = countArray7[countArray8[index3]];
         }

         int count7 = countArray8[1];
         countArray8[1] = countArray8[5];
         countArray8[5] = countArray8[9];
         countArray8[9] = countArray8[13];
         countArray8[13] = count7;
         int count8 = countArray8[2];
         int count9 = countArray8[6];
         countArray8[2] = countArray8[10];
         countArray8[6] = countArray8[14];
         countArray8[10] = count8;
         countArray8[14] = count9;
         int count10 = countArray8[15];
         countArray8[15] = countArray8[11];
         countArray8[11] = countArray8[7];
         countArray8[7] = countArray8[3];
         countArray8[3] = count10;

         for (int index4 = 0; index4 < 4; index4++) {
            int count11 = countArray8[index4 * 4];
            int count12 = countArray8[index4 * 4 + 1];
            int count13 = countArray8[index4 * 4 + 2];
            int count14 = countArray8[index4 * 4 + 3];
            countArray8[index4 * 4] = countArray[2][count11] ^ countArray[3][count12] ^ count13 ^ count14;
            countArray8[index4 * 4 + 1] = count11 ^ countArray[2][count12] ^ countArray[3][count13] ^ count14;
            countArray8[index4 * 4 + 2] = count11 ^ count12 ^ countArray[2][count13] ^ countArray[3][count14];
            countArray8[index4 * 4 + 3] = countArray[3][count11] ^ count12 ^ count13 ^ countArray[2][count14];
         }

         int count15 = index2 * 16;

         for (int index5 = 0; index5 < 16; index5++) {
            countArray8[index5] ^= countArray9[count15 + index5];
         }
      }

      for (int index6 = 0; index6 < 16; index6++) {
         countArray8[index6] = countArray7[countArray8[index6]];
      }

      int count16 = countArray8[1];
      countArray8[1] = countArray8[5];
      countArray8[5] = countArray8[9];
      countArray8[9] = countArray8[13];
      countArray8[13] = count16;
      int count17 = countArray8[2];
      int count18 = countArray8[6];
      countArray8[2] = countArray8[10];
      countArray8[6] = countArray8[14];
      countArray8[10] = count17;
      countArray8[14] = count18;
      int count19 = countArray8[15];
      countArray8[15] = countArray8[11];
      countArray8[11] = countArray8[7];
      countArray8[7] = countArray8[3];
      countArray8[3] = count19;
      int count20 = count6 * 16;

      for (int index7 = 0; index7 < 16; index7++) {
         countArray8[index7] ^= countArray9[count20 + index7];
      }
   }

   public void applyByteArrayByByteArray(byte[] byteValueArray) {
      long value = 0x39f47d65cb4aL;
      value = 0x6b66f8461d8eL ^ value;
      CoreByteArrayEntry coreByteArrayEntry = null;
      long value2 = 0x4e7d39fe0d6ef23L;

      while (true) {
         int count6 = (int)(value2 % 104L);
         if (count6 == 80) {
            ItemEntry133Entry itemEntry133Entry = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray2 = new byte[]{
               -121, 77, 25, -23, -92, 10, 97, -81, 72, 103, 81, 6, 107, 32, -10, -68, 61, 26, -36, -45, 99, -89, -26, -123, 10, 32, -94, 118, 98, -32, 79, 40
            };
            byte[] byteValueArray3 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray2, coreByteArrayEntry);
            this.byteFlagArray55 = itemEntry133Entry.resolveByteArray(byteValueArray3);
            value2 = (value2 ^ 0x3e7ac109de287456L) + 0x2f86c4b9ab853f9fL;
         } else if (count6 == 96) {
            ItemEntry133Entry itemEntry133Entry2 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray4 = new byte[]{
               -62, 29, -98, -2, -88, 104, -96, 34, 68, 88, -16, 18, 25, 71, 82, 39, -24, 59, -51, 4, -32, -1, 59, 47, 94, 74, -99, 19, -20, 87, -29, 83
            };
            byte[] byteValueArray5 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray4, coreByteArrayEntry);
            this.byteFlagArray186 = itemEntry133Entry2.resolveByteArray(byteValueArray5);
            value2 = (value2 ^ 0x5d6204c268672d78L) + 0x4d6ea6aae6d2442dL;
         } else if (count6 == 61) {
            ItemEntry133Entry itemEntry133Entry3 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray6 = new byte[]{
               -72,
               -44,
               84,
               -19,
               -13,
               70,
               -8,
               -94,
               -42,
               124,
               45,
               -106,
               119,
               75,
               1,
               -46,
               -110,
               -98,
               29,
               86,
               30,
               11,
               -4,
               -118,
               58,
               -50,
               -41,
               86,
               -7,
               -22,
               -7,
               -33
            };
            byte[] byteValueArray7 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray6, coreByteArrayEntry);
            this.byteFlagArray92 = itemEntry133Entry3.resolveByteArray(byteValueArray7);
            value2 = (value2 ^ -0x7424d10fa5e78d84L) + 0x58ad2284f267d3c7L;
         } else if (count6 == 46) {
            ItemEntry133Entry itemEntry133Entry4 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray8 = new byte[]{
               118,
               -29,
               97,
               -68,
               46,
               -88,
               79,
               -105,
               -106,
               -34,
               -29,
               -31,
               9,
               -100,
               -112,
               -61,
               9,
               62,
               100,
               -71,
               67,
               87,
               17,
               80,
               100,
               -113,
               -98,
               109,
               89,
               -1,
               120,
               73
            };
            byte[] byteValueArray9 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray8, coreByteArrayEntry);
            this.byteFlagArray177 = itemEntry133Entry4.resolveByteArray(byteValueArray9);
            value2 = (value2 ^ -0x6e9f99849da58d74L) + 0x71ffdce0f4714850L;
         } else if (count6 == 68) {
            ItemEntry133Entry itemEntry133Entry5 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray10 = new byte[]{
               107,
               -27,
               4,
               62,
               87,
               -113,
               -91,
               -66,
               -7,
               30,
               43,
               1,
               -37,
               61,
               -35,
               64,
               -116,
               35,
               -20,
               75,
               -44,
               33,
               -115,
               -43,
               -97,
               -94,
               -20,
               -90,
               29,
               -72,
               71,
               -87
            };
            byte[] byteValueArray11 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray10, coreByteArrayEntry);
            this.byteFlagArray82 = itemEntry133Entry5.resolveByteArray(byteValueArray11);
            value2 = (value2 ^ 0x27e4abef65b57526L) - 0x548e426bb283cd9cL;
         } else if (count6 == 10) {
            ItemEntry133Entry itemEntry133Entry6 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray12 = new byte[]{
               -99, -38, 3, 7, 73, -51, 97, 41, 124, 116, 78, -12, 102, -73, 111, 79, 108, -8, -52, -35, 38, 122, -4, 122, -91, -60, 107, -82, 18, 46, -24, 120
            };
            byte[] byteValueArray13 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray12, coreByteArrayEntry);
            this.byteFlagArray31 = itemEntry133Entry6.resolveByteArray(byteValueArray13);
            value2 = (value2 ^ 0x53c82c78420034c6L) - 0x766258c3260a89bL;
         } else if (count6 == 45) {
            ItemEntry133Entry itemEntry133Entry7 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray14 = new byte[]{
               69,
               126,
               -58,
               -71,
               -88,
               -104,
               91,
               80,
               117,
               37,
               111,
               113,
               -7,
               -80,
               92,
               -37,
               106,
               46,
               -4,
               11,
               -105,
               -68,
               -106,
               -23,
               -124,
               74,
               -106,
               123,
               -8,
               -49,
               30,
               -50
            };
            byte[] byteValueArray15 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray14, coreByteArrayEntry);
            this.byteFlagArray52 = itemEntry133Entry7.resolveByteArray(byteValueArray15);
            value2 = (value2 ^ 0x3c64c9a141be8ccfL) + 0x66dc0c77b1018853L;
         } else if (count6 == 2) {
            ItemEntry133Entry itemEntry133Entry8 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray16 = new byte[]{
               89,
               70,
               5,
               77,
               -118,
               -37,
               115,
               -63,
               -109,
               -8,
               -56,
               111,
               61,
               -68,
               -16,
               12,
               -99,
               -68,
               -16,
               54,
               -22,
               -107,
               100,
               9,
               76,
               -123,
               -56,
               -68,
               27,
               47,
               86,
               52
            };
            byte[] byteValueArray17 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray16, coreByteArrayEntry);
            this.byteFlagArray15 = itemEntry133Entry8.resolveByteArray(byteValueArray17);
            value2 = (value2 ^ -0x50b206aa9a0e9fL) - 0x57dd9fc4907e2f3bL;
         } else if (count6 == 93) {
            ItemEntry133Entry itemEntry133Entry9 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray18 = new byte[]{
               124, -34, 23, -52, 48, 5, 4, -72, 86, 101, 64, 100, 66, -110, -60, 96, 94, 72, 19, -35, -95, -83, -42, 51, 15, -122, -128, 64, 58, 67, 79, -67
            };
            byte[] byteValueArray19 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray18, coreByteArrayEntry);
            this.byteFlagArray131 = itemEntry133Entry9.resolveByteArray(byteValueArray19);
            value2 = (value2 ^ 0x4901e007d5183e4aL) + 0x32ace18d8370015bL;
         } else if (count6 == 27) {
            coreByteArrayEntry = new CoreByteArrayEntry();
            value2 = (value2 ^ 0x68fd65700da9060fL) - 0x525d87f5d54db0c4L;
         } else if (count6 == 88) {
            ItemEntry133Entry itemEntry133Entry10 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray20 = new byte[]{
               -25,
               28,
               7,
               47,
               106,
               14,
               -111,
               90,
               -94,
               -87,
               112,
               -105,
               40,
               -22,
               -74,
               22,
               -21,
               75,
               107,
               -92,
               -126,
               57,
               34,
               105,
               75,
               59,
               -28,
               -50,
               -40,
               -62,
               -6,
               11
            };
            byte[] byteValueArray21 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray20, coreByteArrayEntry);
            this.byteFlagArray120 = itemEntry133Entry10.resolveByteArray(byteValueArray21);
            value2 = (value2 ^ 0x5dc5cf5571b7e4ebL) + 0x2da7b70f4ad03589L;
         } else if (count6 == 9) {
            ItemEntry133Entry itemEntry133Entry11 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray22 = new byte[]{
               -16, 22, 24, 81, 95, -20, 30, -39, -33, 71, -44, 64, -118, 96, 46, 4, 115, -57, 85, 66, 73, 93, 65, -105, 4, -107, 18, -67, 74, 72, 88, 44
            };
            byte[] byteValueArray23 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray22, coreByteArrayEntry);
            this.byteFlagArray60 = itemEntry133Entry11.resolveByteArray(byteValueArray23);
            value2 = (value2 ^ 0x5ddb6d1ed97344e3L) - 0x285ac7fbaaf3f0d9L;
         } else if (count6 == 32) {
            ItemEntry133Entry itemEntry133Entry12 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray24 = new byte[]{
               83,
               -9,
               -116,
               49,
               69,
               126,
               60,
               -75,
               9,
               -56,
               -55,
               -90,
               27,
               -2,
               72,
               -16,
               -84,
               80,
               86,
               117,
               -52,
               94,
               83,
               -123,
               61,
               124,
               -82,
               104,
               73,
               125,
               108,
               -127
            };
            byte[] byteValueArray25 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray24, coreByteArrayEntry);
            this.byteFlagArray143 = itemEntry133Entry12.resolveByteArray(byteValueArray25);
            value2 = (value2 ^ 0x3c5991e7a7ff5540L) + 0x2e640847b2f45c0cL;
         } else if (count6 == 8) {
            ItemEntry133Entry itemEntry133Entry13 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray26 = new byte[]{
               -118, 86, -89, -70, -96, -52, 118, 11, 57, -76, 59, -42, -42, 2, -97, 26, 51, -19, 15, 125, 95, -51, 65, 15, -67, 70, 25, -25, -2, 86, -108, 43
            };
            byte[] byteValueArray27 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray26, coreByteArrayEntry);
            this.byteFlagArray53 = itemEntry133Entry13.resolveByteArray(byteValueArray27);
            value2 = (value2 ^ 0x6f14821fdbf4c5edL) - 0xb71fb9aeee49556L;
         } else if (count6 == 79) {
            ItemEntry133Entry itemEntry133Entry14 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray28 = new byte[]{
               -73, -95, -62, 91, -61, 52, 23, 91, 63, 1, 56, 124, -91, 67, 62, -101, -30, 119, -42, -96, 63, -95, -19, 126, 52, -65, 20, 3, 39, -109, 95, -6
            };
            byte[] byteValueArray29 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray28, coreByteArrayEntry);
            this.byteFlagArray115 = itemEntry133Entry14.resolveByteArray(byteValueArray29);
            value2 = (value2 ^ 0x60d4bd51f87109dbL) + 0x4c6855f9a925378dL;
         } else if (count6 == 99) {
            ItemEntry133Entry itemEntry133Entry15 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray30 = new byte[]{
               -3, 10, 26, -97, -111, -22, 92, 70, 34, -74, 83, 65, 121, 47, 31, 104, -56, -61, 10, 22, 58, 29, 79, -93, -75, 79, -126, -81, 66, 90, 72, 66
            };
            byte[] byteValueArray31 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray30, coreByteArrayEntry);
            this.byteFlagArray36 = itemEntry133Entry15.resolveByteArray(byteValueArray31);
            value2 = (value2 ^ 0x65501739ef3decc8L) - 0x2e0baaa86257e88dL;
         } else if (count6 == 30) {
            ItemEntry133Entry itemEntry133Entry16 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray32 = new byte[]{
               7, -30, -58, -81, -28, 28, 5, -50, 73, 61, 40, 24, 86, -101, 78, 53, 64, 125, 114, -93, -76, 116, 26, -116, -77, 115, -57, -66, 76, 65, -88, 22
            };
            byte[] byteValueArray33 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray32, coreByteArrayEntry);
            this.byteFlagArray4 = itemEntry133Entry16.resolveByteArray(byteValueArray33);
            value2 = (value2 ^ -0x1eafe8abcd20ebcfL) - 0x3224607d979e3d16L;
         } else if (count6 == 75) {
            ItemEntry133Entry itemEntry133Entry17 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray34 = new byte[]{
               83,
               -112,
               127,
               -59,
               -66,
               -59,
               -25,
               1,
               107,
               38,
               25,
               -124,
               69,
               -21,
               -69,
               85,
               -14,
               -5,
               64,
               -14,
               111,
               41,
               -53,
               -89,
               -6,
               -5,
               -65,
               -78,
               -106,
               -20,
               -99,
               -64
            };
            byte[] byteValueArray35 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray34, coreByteArrayEntry);
            this.byteFlagArray153 = itemEntry133Entry17.resolveByteArray(byteValueArray35);
            value2 = (value2 ^ 0x60f3712a61c16354L) - 0xaa72755aa4fc255L;
         } else if (count6 == 12) {
            ItemEntry133Entry itemEntry133Entry18 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray36 = new byte[]{
               -17, 40, 62, 13, -113, 95, -116, -108, -107, 51, -16, 64, -77, 102, 36, -69, 116, 60, 3, -82, -4, 90, 26, 63, 44, -31, -93, 54, 66, 1, -9, -122
            };
            byte[] byteValueArray37 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray36, coreByteArrayEntry);
            this.byteFlagArray2 = itemEntry133Entry18.resolveByteArray(byteValueArray37);
            value2 = (value2 ^ -0x65707f27b4abdfb1L) + 0x6752945507395605L;
         } else if (count6 == 90) {
            ItemEntry133Entry itemEntry133Entry19 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray38 = new byte[]{
               -35,
               -92,
               -41,
               9,
               -98,
               17,
               107,
               19,
               77,
               13,
               -28,
               -22,
               -86,
               95,
               -32,
               -81,
               -87,
               97,
               112,
               98,
               78,
               101,
               93,
               2,
               -111,
               11,
               -127,
               -33,
               -15,
               110,
               -31,
               -46
            };
            byte[] byteValueArray39 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray38, coreByteArrayEntry);
            this.byteFlagArray118 = itemEntry133Entry19.resolveByteArray(byteValueArray39);
            value2 = (value2 ^ -0x6a6ed845fd5c0c8dL) - 0x74607d608fb4c4ecL;
         } else if (count6 == 85) {
            ItemEntry133Entry itemEntry133Entry20 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray40 = new byte[]{
               -42,
               -61,
               47,
               21,
               -88,
               122,
               89,
               58,
               21,
               -88,
               -52,
               -76,
               -102,
               -70,
               -53,
               -29,
               -101,
               -99,
               39,
               -57,
               60,
               -31,
               58,
               -70,
               97,
               -90,
               -92,
               7,
               -119,
               -59,
               -44,
               -65
            };
            byte[] byteValueArray41 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray40, coreByteArrayEntry);
            this.byteFlagArray47 = itemEntry133Entry20.resolveByteArray(byteValueArray41);
            value2 = (value2 ^ -0x4de606309b3d5f32L) + 0x6da2918acfb47875L;
         } else if (count6 == 76) {
            ItemEntry133Entry itemEntry133Entry21 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray42 = new byte[]{
               -23, 43, -102, 55, 56, 14, 112, 19, -110, 69, -111, -92, 14, -99, 15, 7, 30, 77, 91, -36, 8, -68, 74, -32, -89, -36, -62, -18, 105, 36, 31, 90
            };
            byte[] byteValueArray43 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray42, coreByteArrayEntry);
            this.byteFlagArray25 = itemEntry133Entry21.resolveByteArray(byteValueArray43);
            value2 = (value2 ^ -0x5821935d300510adL) - 0x740b2927818d312fL;
         } else if (count6 == 33) {
            ItemEntry133Entry itemEntry133Entry22 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray44 = new byte[]{
               108,
               103,
               121,
               -62,
               -33,
               82,
               96,
               86,
               -89,
               -70,
               62,
               -116,
               -77,
               -55,
               -11,
               107,
               90,
               -92,
               78,
               -13,
               -98,
               82,
               42,
               51,
               -57,
               -108,
               18,
               -95,
               -109,
               1,
               -55,
               -45
            };
            byte[] byteValueArray45 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray44, coreByteArrayEntry);
            this.byteFlagArray13 = itemEntry133Entry22.resolveByteArray(byteValueArray45);
            value2 = (value2 ^ 0x70a36e4c66517a3aL) + 0x223756f86fd0da04L;
         } else if (count6 == 7) {
            ItemEntry133Entry itemEntry133Entry23 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray46 = new byte[]{
               -24,
               -36,
               14,
               -65,
               -96,
               117,
               99,
               -27,
               61,
               -16,
               -27,
               62,
               58,
               34,
               -118,
               108,
               -67,
               -20,
               -32,
               14,
               82,
               83,
               27,
               101,
               -73,
               41,
               -83,
               122,
               17,
               -72,
               -112,
               116
            };
            byte[] byteValueArray47 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray46, coreByteArrayEntry);
            this.byteFlagArray22 = itemEntry133Entry23.resolveByteArray(byteValueArray47);
            value2 = (value2 ^ -0x74236e2609f840edL) + 0x726096871ec84257L;
         } else if (count6 == 100) {
            ItemEntry133Entry itemEntry133Entry24 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray48 = new byte[]{
               -108,
               -53,
               -111,
               9,
               62,
               41,
               -123,
               -124,
               -3,
               -128,
               -67,
               47,
               52,
               -95,
               2,
               -32,
               -71,
               -26,
               -55,
               44,
               47,
               34,
               55,
               -128,
               -74,
               95,
               92,
               -125,
               -93,
               -28,
               -81,
               84
            };
            byte[] byteValueArray49 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray48, coreByteArrayEntry);
            this.byteFlagArray184 = itemEntry133Entry24.resolveByteArray(byteValueArray49);
            value2 = (value2 ^ -0x4b0e95870353fce6L) + 0x79134fbfea18ec15L;
         } else if (count6 == 47) {
            ItemEntry133Entry itemEntry133Entry25 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray50 = new byte[]{
               111, -9, 42, 109, -27, -13, -105, 30, -102, -84, -80, 66, 61, 86, -91, -114, 108, -43, -14, 6, 88, 41, 68, 24, 108, 43, 91, -58, -5, 26, 82, 84
            };
            byte[] byteValueArray51 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray50, coreByteArrayEntry);
            this.byteFlagArray101 = itemEntry133Entry25.resolveByteArray(byteValueArray51);
            value2 = (value2 ^ 0x5b28691bc11dc3b9L) + 0x4d35575d0e0f6a0L;
         } else if (count6 == 56) {
            ItemEntry133Entry itemEntry133Entry26 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray52 = new byte[]{
               -17, 38, 44, 79, 76, 92, -28, -10, 50, 99, 27, -2, 83, -48, 93, -7, 41, -15, -126, 106, 3, 15, 38, -115, 103, 14, 28, 84, 23, 40, 75, 31
            };
            byte[] byteValueArray53 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray52, coreByteArrayEntry);
            this.byteFlagArray73 = itemEntry133Entry26.resolveByteArray(byteValueArray53);
            value2 = (value2 ^ 0x2f0903ca5909e1b3L) + 0xda56980c058c449L;
         } else if (count6 == 5) {
            ItemEntry133Entry itemEntry133Entry27 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray54 = new byte[]{
               66, 58, 99, 22, -61, 49, -96, -5, -84, 30, 103, 23, 47, -38, -61, -84, 75, -127, 44, -121, -48, 49, -50, -21, -82, 68, 56, 8, 76, 23, 92, 100
            };
            byte[] byteValueArray55 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray54, coreByteArrayEntry);
            this.byteFlagArray72 = itemEntry133Entry27.resolveByteArray(byteValueArray55);
            value2 = (value2 ^ -0x542dbce285a9c73eL) - 0x7715083657d04e2dL;
         } else if (count6 == 101) {
            ItemEntry133Entry itemEntry133Entry28 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray56 = new byte[]{
               33,
               38,
               74,
               -83,
               -24,
               -15,
               -31,
               109,
               -21,
               63,
               -98,
               -89,
               7,
               -114,
               -118,
               68,
               100,
               70,
               -97,
               50,
               -8,
               -1,
               2,
               22,
               35,
               -88,
               17,
               -49,
               -120,
               122,
               101,
               106
            };
            byte[] byteValueArray57 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray56, coreByteArrayEntry);
            this.byteFlagArray18 = itemEntry133Entry28.resolveByteArray(byteValueArray57);
            value2 = (value2 ^ 0x397d9efd3c463363L) - 0x2374ebc3b17f98abL;
         } else if (count6 == 11) {
            ItemEntry133Entry itemEntry133Entry29 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray58 = new byte[]{
               -74, -89, 108, -8, -25, -75, -11, 83, 10, -6, 44, 24, -91, -38, 44, 9, 34, 105, -33, -117, -68, 32, -74, 93, -34, -66, 105, 86, 99, 126, 115, 2
            };
            byte[] byteValueArray59 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray58, coreByteArrayEntry);
            this.byteFlagArray28 = itemEntry133Entry29.resolveByteArray(byteValueArray59);
            value2 = (value2 ^ 0x91aa53b70bc1b8dL) - 0x25bfbabebd8d3632L;
         } else if (count6 == 72) {
            ItemEntry133Entry itemEntry133Entry30 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray60 = new byte[]{
               45,
               -19,
               124,
               -118,
               -77,
               -81,
               -17,
               123,
               13,
               107,
               -76,
               -103,
               112,
               -79,
               -73,
               79,
               120,
               -50,
               101,
               -21,
               -85,
               -105,
               -66,
               -9,
               100,
               -127,
               23,
               -25,
               28,
               5,
               55,
               83
            };
            byte[] byteValueArray61 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray60, coreByteArrayEntry);
            this.byteFlagArray56 = itemEntry133Entry30.resolveByteArray(byteValueArray61);
            value2 = (value2 ^ 0x3fcd087af9a1c0b9L) - 0x8102d533952ef60L;
         } else if (count6 == 74) {
            ItemEntry133Entry itemEntry133Entry31 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray62 = new byte[]{
               117,
               -42,
               -23,
               20,
               -51,
               24,
               110,
               -99,
               -102,
               -105,
               94,
               -84,
               97,
               -72,
               122,
               114,
               -68,
               52,
               -6,
               -76,
               -93,
               -10,
               115,
               -24,
               -49,
               44,
               21,
               7,
               -82,
               47,
               70,
               65
            };
            byte[] byteValueArray63 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray62, coreByteArrayEntry);
            this.byteFlagArray43 = itemEntry133Entry31.resolveByteArray(byteValueArray63);
            value2 = (value2 ^ 0x2f54e3d5c086abbbL) - 0xc65ce22c9549767L;
         } else if (count6 == 86) {
            ItemEntry133Entry itemEntry133Entry32 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray64 = new byte[]{
               26, 99, 79, -96, 88, 5, -38, 40, 124, -45, 123, 94, -99, -65, 56, -9, -87, 62, -95, 11, -6, 125, 46, 15, 51, -84, -1, -128, 18, 51, -21, -23
            };
            byte[] byteValueArray65 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray64, coreByteArrayEntry);
            this.byteFlagArray151 = itemEntry133Entry32.resolveByteArray(byteValueArray65);
            value2 = (value2 ^ 0x5dad3144a236957L) + 0x1f92c984e851e5b6L;
         } else if (count6 == 13) {
            ItemEntry133Entry itemEntry133Entry33 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray66 = new byte[]{
               -77,
               57,
               100,
               51,
               -102,
               3,
               -2,
               -112,
               -113,
               35,
               -73,
               77,
               -21,
               -77,
               63,
               -51,
               -5,
               78,
               113,
               -98,
               78,
               -72,
               -104,
               -57,
               -124,
               44,
               52,
               -40,
               36,
               116,
               -24,
               78
            };
            byte[] byteValueArray67 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray66, coreByteArrayEntry);
            this.byteFlagArray107 = itemEntry133Entry33.resolveByteArray(byteValueArray67);
            value2 = (value2 ^ -0x21c73ed899b7a213L) + 0x32b7c85344efd97bL;
         } else if (count6 != 25) {
            if (count6 == 39) {
               this.applyByteArray(byteValueArray);
               break;
            }

            if (count6 != 67) {
               if (count6 != 64) {
                  if (count6 != 42) {
                     if (count6 != 18) {
                        if (count6 != 62) {
                           if (count6 != 35) {
                              if (count6 != 24) {
                                 if (count6 != 70) {
                                    if (count6 == 3) {
                                       ItemEntry133Entry itemEntry133Entry34 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                       byte[] byteValueArray68 = new byte[]{
                                          -28,
                                          -34,
                                          44,
                                          97,
                                          60,
                                          72,
                                          6,
                                          47,
                                          0,
                                          -97,
                                          -8,
                                          -75,
                                          -51,
                                          19,
                                          121,
                                          -15,
                                          6,
                                          -121,
                                          -27,
                                          89,
                                          -9,
                                          -117,
                                          99,
                                          3,
                                          34,
                                          -87,
                                          19,
                                          16,
                                          -20,
                                          70,
                                          8,
                                          -46
                                       };
                                       byte[] byteValueArray69 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray68, coreByteArrayEntry);
                                       this.byteFlagArray12 = itemEntry133Entry34.resolveByteArray(byteValueArray69);
                                       value2 = (value2 ^ 0x59f6464a4f659026L) + 0x46137b0137a4bc4fL;
                                    } else if (count6 != 102) {
                                       if (count6 != 84) {
                                          if (count6 != 63) {
                                             if (count6 != 19) {
                                                if (count6 != 51) {
                                                   if (count6 != 54) {
                                                      if (count6 != 50) {
                                                         if (count6 != 4) {
                                                            if (count6 != 91) {
                                                               if (count6 != 48) {
                                                                  if (count6 != 15) {
                                                                     if (count6 != 26) {
                                                                        if (count6 != 44) {
                                                                           if (count6 != 0) {
                                                                              if (count6 == 103) {
                                                                                 break;
                                                                              }

                                                                              if (count6 != 28) {
                                                                                 if (count6 != 34) {
                                                                                    if (count6 != 43) {
                                                                                       if (count6 != 94) {
                                                                                          if (count6 != 81) {
                                                                                             if (count6 != 89) {
                                                                                                if (count6 != 60) {
                                                                                                   if (count6 != 95) {
                                                                                                      if (count6 != 82) {
                                                                                                         if (count6 != 22) {
                                                                                                            if (count6 != 55) {
                                                                                                               if (count6 != 92) {
                                                                                                                  if (count6 != 16) {
                                                                                                                     if (count6 != 59) {
                                                                                                                        if (count6 == 40) {
                                                                                                                           ItemEntry133Entry itemEntry133Entry35 = new ItemEntry133Entry(
                                                                                                                              MovementWeightProperty.SHA3_256
                                                                                                                           );
                                                                                                                           byte[] byteValueArray70 = new byte[]{
                                                                                                                              -27,
                                                                                                                              72,
                                                                                                                              110,
                                                                                                                              -67,
                                                                                                                              5,
                                                                                                                              -113,
                                                                                                                              -80,
                                                                                                                              -116,
                                                                                                                              68,
                                                                                                                              91,
                                                                                                                              -67,
                                                                                                                              -78,
                                                                                                                              70,
                                                                                                                              -107,
                                                                                                                              101,
                                                                                                                              -15,
                                                                                                                              -121,
                                                                                                                              -23,
                                                                                                                              -125,
                                                                                                                              -52,
                                                                                                                              -67,
                                                                                                                              45,
                                                                                                                              48,
                                                                                                                              -70,
                                                                                                                              -25,
                                                                                                                              74,
                                                                                                                              72,
                                                                                                                              -119,
                                                                                                                              125,
                                                                                                                              -93,
                                                                                                                              34,
                                                                                                                              1
                                                                                                                           };
                                                                                                                           byte[] byteValueArray71 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                              byteValueArray, byteValueArray70, coreByteArrayEntry
                                                                                                                           );
                                                                                                                           this.byteFlagArray134 = itemEntry133Entry35.resolveByteArray(
                                                                                                                              byteValueArray71
                                                                                                                           );
                                                                                                                           value2 = (
                                                                                                                                 value2 ^ 0x2e5fe524e14fd300L
                                                                                                                              )
                                                                                                                              - 0x5a5a36fde3ed724L;
                                                                                                                        } else if (count6 != 98) {
                                                                                                                           if (count6 != 83) {
                                                                                                                              if (count6 != 58) {
                                                                                                                                 if (count6 != 17) {
                                                                                                                                    if (count6 != 73) {
                                                                                                                                       if (count6 != 29) {
                                                                                                                                          if (count6 != 65) {
                                                                                                                                             if (count6 != 52) {
                                                                                                                                                if (count6 != 53
                                                                                                                                                   )
                                                                                                                                                 {
                                                                                                                                                   if (count6
                                                                                                                                                      != 77) {
                                                                                                                                                      if (count6
                                                                                                                                                         != 38) {
                                                                                                                                                         if (count6
                                                                                                                                                            != 37
                                                                                                                                                            )
                                                                                                                                                          {
                                                                                                                                                            if (count6
                                                                                                                                                               != 97
                                                                                                                                                               )
                                                                                                                                                             {
                                                                                                                                                               if (count6
                                                                                                                                                                  != 87
                                                                                                                                                                  )
                                                                                                                                                                {
                                                                                                                                                                  if (count6
                                                                                                                                                                     != 69
                                                                                                                                                                     )
                                                                                                                                                                   {
                                                                                                                                                                     if (count6
                                                                                                                                                                        != 78
                                                                                                                                                                        )
                                                                                                                                                                      {
                                                                                                                                                                        if (count6
                                                                                                                                                                           != 21
                                                                                                                                                                           )
                                                                                                                                                                         {
                                                                                                                                                                           if (count6
                                                                                                                                                                              != 31
                                                                                                                                                                              )
                                                                                                                                                                            {
                                                                                                                                                                              if (count6
                                                                                                                                                                                 != 6
                                                                                                                                                                                 )
                                                                                                                                                                               {
                                                                                                                                                                                 if (count6
                                                                                                                                                                                    == 49
                                                                                                                                                                                    )
                                                                                                                                                                                  {
                                                                                                                                                                                    ItemEntry133Entry itemEntry133Entry36 = new ItemEntry133Entry(
                                                                                                                                                                                       MovementWeightProperty.SHA3_256
                                                                                                                                                                                    );
                                                                                                                                                                                    byte[] byteValueArray72 = new byte[]{
                                                                                                                                                                                       36,
                                                                                                                                                                                       72,
                                                                                                                                                                                       -103,
                                                                                                                                                                                       54,
                                                                                                                                                                                       53,
                                                                                                                                                                                       68,
                                                                                                                                                                                       42,
                                                                                                                                                                                       -76,
                                                                                                                                                                                       -111,
                                                                                                                                                                                       116,
                                                                                                                                                                                       -22,
                                                                                                                                                                                       24,
                                                                                                                                                                                       -52,
                                                                                                                                                                                       98,
                                                                                                                                                                                       -6,
                                                                                                                                                                                       -115,
                                                                                                                                                                                       -70,
                                                                                                                                                                                       34,
                                                                                                                                                                                       -94,
                                                                                                                                                                                       -8,
                                                                                                                                                                                       -125,
                                                                                                                                                                                       54,
                                                                                                                                                                                       -67,
                                                                                                                                                                                       110,
                                                                                                                                                                                       -14,
                                                                                                                                                                                       -35,
                                                                                                                                                                                       80,
                                                                                                                                                                                       -99,
                                                                                                                                                                                       56,
                                                                                                                                                                                       -51,
                                                                                                                                                                                       -103,
                                                                                                                                                                                       -59
                                                                                                                                                                                    };
                                                                                                                                                                                    byte[] byteValueArray73 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                       byteValueArray,
                                                                                                                                                                                       byteValueArray72,
                                                                                                                                                                                       coreByteArrayEntry
                                                                                                                                                                                    );
                                                                                                                                                                                    this.byteFlagArray96 = itemEntry133Entry36.resolveByteArray(
                                                                                                                                                                                       byteValueArray73
                                                                                                                                                                                    );
                                                                                                                                                                                    value2 = (
                                                                                                                                                                                          value2
                                                                                                                                                                                             ^ 0x3ce85467fbcbe37L
                                                                                                                                                                                       )
                                                                                                                                                                                       - 0x513d07c68252122L;
                                                                                                                                                                                 } else if (count6
                                                                                                                                                                                    != 23
                                                                                                                                                                                    )
                                                                                                                                                                                  {
                                                                                                                                                                                    if (count6
                                                                                                                                                                                       != 20
                                                                                                                                                                                       )
                                                                                                                                                                                     {
                                                                                                                                                                                       if (count6
                                                                                                                                                                                          == 41
                                                                                                                                                                                          )
                                                                                                                                                                                        {
                                                                                                                                                                                          ItemEntry133Entry itemEntry133Entry37 = new ItemEntry133Entry(
                                                                                                                                                                                             MovementWeightProperty.SHA3_256
                                                                                                                                                                                          );
                                                                                                                                                                                          byte[] byteValueArray74 = new byte[]{
                                                                                                                                                                                             -67,
                                                                                                                                                                                             11,
                                                                                                                                                                                             -107,
                                                                                                                                                                                             83,
                                                                                                                                                                                             -70,
                                                                                                                                                                                             -62,
                                                                                                                                                                                             106,
                                                                                                                                                                                             -83,
                                                                                                                                                                                             -27,
                                                                                                                                                                                             101,
                                                                                                                                                                                             -98,
                                                                                                                                                                                             -4,
                                                                                                                                                                                             31,
                                                                                                                                                                                             -124,
                                                                                                                                                                                             -19,
                                                                                                                                                                                             -3,
                                                                                                                                                                                             -124,
                                                                                                                                                                                             16,
                                                                                                                                                                                             -74,
                                                                                                                                                                                             14,
                                                                                                                                                                                             -36,
                                                                                                                                                                                             110,
                                                                                                                                                                                             48,
                                                                                                                                                                                             -7,
                                                                                                                                                                                             80,
                                                                                                                                                                                             11,
                                                                                                                                                                                             12,
                                                                                                                                                                                             -83,
                                                                                                                                                                                             96,
                                                                                                                                                                                             -35,
                                                                                                                                                                                             82,
                                                                                                                                                                                             -106
                                                                                                                                                                                          };
                                                                                                                                                                                          byte[] byteValueArray75 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                             byteValueArray,
                                                                                                                                                                                             byteValueArray74,
                                                                                                                                                                                             coreByteArrayEntry
                                                                                                                                                                                          );
                                                                                                                                                                                          this.byteFlagArray183 = itemEntry133Entry37.resolveByteArray(
                                                                                                                                                                                             byteValueArray75
                                                                                                                                                                                          );
                                                                                                                                                                                          value2 = (
                                                                                                                                                                                                value2
                                                                                                                                                                                                   ^ 0x66ce39520c33667fL
                                                                                                                                                                                             )
                                                                                                                                                                                             + 0x24e74d684d0cba6fL;
                                                                                                                                                                                       } else if (count6
                                                                                                                                                                                          != 14
                                                                                                                                                                                          )
                                                                                                                                                                                        {
                                                                                                                                                                                          if (count6
                                                                                                                                                                                             != 1
                                                                                                                                                                                             )
                                                                                                                                                                                           {
                                                                                                                                                                                             if (count6
                                                                                                                                                                                                != 66
                                                                                                                                                                                                )
                                                                                                                                                                                              {
                                                                                                                                                                                                if (count6
                                                                                                                                                                                                   != 57
                                                                                                                                                                                                   )
                                                                                                                                                                                                 {
                                                                                                                                                                                                   if (count6
                                                                                                                                                                                                      != 71
                                                                                                                                                                                                      )
                                                                                                                                                                                                    {
                                                                                                                                                                                                      ItemEntry133Entry itemEntry133Entry38 = new ItemEntry133Entry(
                                                                                                                                                                                                         MovementWeightProperty.SHA3_256
                                                                                                                                                                                                      );
                                                                                                                                                                                                      byte[] byteValueArray76 = new byte[]{
                                                                                                                                                                                                         72,
                                                                                                                                                                                                         119,
                                                                                                                                                                                                         104,
                                                                                                                                                                                                         21,
                                                                                                                                                                                                         -29,
                                                                                                                                                                                                         18,
                                                                                                                                                                                                         -71,
                                                                                                                                                                                                         -106,
                                                                                                                                                                                                         -108,
                                                                                                                                                                                                         115,
                                                                                                                                                                                                         -53,
                                                                                                                                                                                                         -13,
                                                                                                                                                                                                         -75,
                                                                                                                                                                                                         -42,
                                                                                                                                                                                                         100,
                                                                                                                                                                                                         122,
                                                                                                                                                                                                         -39,
                                                                                                                                                                                                         -51,
                                                                                                                                                                                                         124,
                                                                                                                                                                                                         2,
                                                                                                                                                                                                         82,
                                                                                                                                                                                                         95,
                                                                                                                                                                                                         5,
                                                                                                                                                                                                         110,
                                                                                                                                                                                                         -128,
                                                                                                                                                                                                         79,
                                                                                                                                                                                                         47,
                                                                                                                                                                                                         2,
                                                                                                                                                                                                         -63,
                                                                                                                                                                                                         90,
                                                                                                                                                                                                         30,
                                                                                                                                                                                                         -30
                                                                                                                                                                                                      };
                                                                                                                                                                                                      byte[] byteValueArray77 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                         byteValueArray,
                                                                                                                                                                                                         byteValueArray76,
                                                                                                                                                                                                         coreByteArrayEntry
                                                                                                                                                                                                      );
                                                                                                                                                                                                      this.byteFlagArray171 = itemEntry133Entry38.resolveByteArray(
                                                                                                                                                                                                         byteValueArray77
                                                                                                                                                                                                      );
                                                                                                                                                                                                      value2 = (
                                                                                                                                                                                                            value2
                                                                                                                                                                                                               ^ 0x2cb71618dece4bacL
                                                                                                                                                                                                         )
                                                                                                                                                                                                         - 0x2a74ded86e1a50b9L;
                                                                                                                                                                                                   } else {
                                                                                                                                                                                                      ItemEntry133Entry itemEntry133Entry39 = new ItemEntry133Entry(
                                                                                                                                                                                                         MovementWeightProperty.SHA3_256
                                                                                                                                                                                                      );
                                                                                                                                                                                                      byte[] byteValueArray78 = new byte[]{
                                                                                                                                                                                                         -43,
                                                                                                                                                                                                         -16,
                                                                                                                                                                                                         28,
                                                                                                                                                                                                         95,
                                                                                                                                                                                                         -69,
                                                                                                                                                                                                         9,
                                                                                                                                                                                                         -127,
                                                                                                                                                                                                         97,
                                                                                                                                                                                                         108,
                                                                                                                                                                                                         -15,
                                                                                                                                                                                                         -86,
                                                                                                                                                                                                         55,
                                                                                                                                                                                                         36,
                                                                                                                                                                                                         88,
                                                                                                                                                                                                         -98,
                                                                                                                                                                                                         -77,
                                                                                                                                                                                                         -25,
                                                                                                                                                                                                         0,
                                                                                                                                                                                                         -7,
                                                                                                                                                                                                         118,
                                                                                                                                                                                                         99,
                                                                                                                                                                                                         47,
                                                                                                                                                                                                         32,
                                                                                                                                                                                                         81,
                                                                                                                                                                                                         -47,
                                                                                                                                                                                                         69,
                                                                                                                                                                                                         -121,
                                                                                                                                                                                                         -54,
                                                                                                                                                                                                         -67,
                                                                                                                                                                                                         -102,
                                                                                                                                                                                                         -107,
                                                                                                                                                                                                         53
                                                                                                                                                                                                      };
                                                                                                                                                                                                      byte[] byteValueArray79 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                         byteValueArray,
                                                                                                                                                                                                         byteValueArray78,
                                                                                                                                                                                                         coreByteArrayEntry
                                                                                                                                                                                                      );
                                                                                                                                                                                                      this.byteFlagArray145 = itemEntry133Entry39.resolveByteArray(
                                                                                                                                                                                                         byteValueArray79
                                                                                                                                                                                                      );
                                                                                                                                                                                                      value2 = (
                                                                                                                                                                                                            value2
                                                                                                                                                                                                               ^ 0x6367d7b77d5d5e31L
                                                                                                                                                                                                         )
                                                                                                                                                                                                         + 0x172e540a4fab2fa9L;
                                                                                                                                                                                                   }
                                                                                                                                                                                                } else {
                                                                                                                                                                                                   ItemEntry133Entry itemEntry133Entry40 = new ItemEntry133Entry(
                                                                                                                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                                                                                                                   );
                                                                                                                                                                                                   byte[] byteValueArray80 = new byte[]{
                                                                                                                                                                                                      -62,
                                                                                                                                                                                                      -82,
                                                                                                                                                                                                      69,
                                                                                                                                                                                                      -5,
                                                                                                                                                                                                      -41,
                                                                                                                                                                                                      -64,
                                                                                                                                                                                                      -89,
                                                                                                                                                                                                      -52,
                                                                                                                                                                                                      -44,
                                                                                                                                                                                                      35,
                                                                                                                                                                                                      109,
                                                                                                                                                                                                      -32,
                                                                                                                                                                                                      -34,
                                                                                                                                                                                                      -65,
                                                                                                                                                                                                      -116,
                                                                                                                                                                                                      -18,
                                                                                                                                                                                                      52,
                                                                                                                                                                                                      65,
                                                                                                                                                                                                      82,
                                                                                                                                                                                                      -8,
                                                                                                                                                                                                      -120,
                                                                                                                                                                                                      -113,
                                                                                                                                                                                                      -5,
                                                                                                                                                                                                      -37,
                                                                                                                                                                                                      -108,
                                                                                                                                                                                                      -47,
                                                                                                                                                                                                      41,
                                                                                                                                                                                                      119,
                                                                                                                                                                                                      -83,
                                                                                                                                                                                                      22,
                                                                                                                                                                                                      102,
                                                                                                                                                                                                      -54
                                                                                                                                                                                                   };
                                                                                                                                                                                                   byte[] byteValueArray81 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                      byteValueArray,
                                                                                                                                                                                                      byteValueArray80,
                                                                                                                                                                                                      coreByteArrayEntry
                                                                                                                                                                                                   );
                                                                                                                                                                                                   this.byteFlagArray181 = itemEntry133Entry40.resolveByteArray(
                                                                                                                                                                                                      byteValueArray81
                                                                                                                                                                                                   );
                                                                                                                                                                                                   value2 = (
                                                                                                                                                                                                         value2
                                                                                                                                                                                                            ^ 0x1a2e880d4310d45dL
                                                                                                                                                                                                      )
                                                                                                                                                                                                      - 0x1111aaec4d5d1d16L;
                                                                                                                                                                                                }
                                                                                                                                                                                             } else {
                                                                                                                                                                                                ItemEntry133Entry itemEntry133Entry41 = new ItemEntry133Entry(
                                                                                                                                                                                                   MovementWeightProperty.SHA3_256
                                                                                                                                                                                                );
                                                                                                                                                                                                byte[] byteValueArray82 = new byte[]{
                                                                                                                                                                                                   21,
                                                                                                                                                                                                   -21,
                                                                                                                                                                                                   -36,
                                                                                                                                                                                                   -91,
                                                                                                                                                                                                   -50,
                                                                                                                                                                                                   -73,
                                                                                                                                                                                                   -39,
                                                                                                                                                                                                   -53,
                                                                                                                                                                                                   -52,
                                                                                                                                                                                                   101,
                                                                                                                                                                                                   -101,
                                                                                                                                                                                                   -56,
                                                                                                                                                                                                   50,
                                                                                                                                                                                                   -12,
                                                                                                                                                                                                   11,
                                                                                                                                                                                                   65,
                                                                                                                                                                                                   -125,
                                                                                                                                                                                                   -126,
                                                                                                                                                                                                   44,
                                                                                                                                                                                                   91,
                                                                                                                                                                                                   64,
                                                                                                                                                                                                   82,
                                                                                                                                                                                                   -17,
                                                                                                                                                                                                   122,
                                                                                                                                                                                                   39,
                                                                                                                                                                                                   95,
                                                                                                                                                                                                   112,
                                                                                                                                                                                                   98,
                                                                                                                                                                                                   64,
                                                                                                                                                                                                   50,
                                                                                                                                                                                                   -81,
                                                                                                                                                                                                   -36
                                                                                                                                                                                                };
                                                                                                                                                                                                byte[] byteValueArray83 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                   byteValueArray,
                                                                                                                                                                                                   byteValueArray82,
                                                                                                                                                                                                   coreByteArrayEntry
                                                                                                                                                                                                );
                                                                                                                                                                                                this.byteFlagArray129 = itemEntry133Entry41.resolveByteArray(
                                                                                                                                                                                                   byteValueArray83
                                                                                                                                                                                                );
                                                                                                                                                                                                value2 = (
                                                                                                                                                                                                      value2
                                                                                                                                                                                                         ^ 0xf93c5970d003499L
                                                                                                                                                                                                   )
                                                                                                                                                                                                   - 0x5444bd396dccfb55L;
                                                                                                                                                                                             }
                                                                                                                                                                                          } else {
                                                                                                                                                                                             ItemEntry133Entry itemEntry133Entry42 = new ItemEntry133Entry(
                                                                                                                                                                                                MovementWeightProperty.SHA3_256
                                                                                                                                                                                             );
                                                                                                                                                                                             byte[] byteValueArray84 = new byte[]{
                                                                                                                                                                                                53,
                                                                                                                                                                                                71,
                                                                                                                                                                                                -12,
                                                                                                                                                                                                -116,
                                                                                                                                                                                                66,
                                                                                                                                                                                                -46,
                                                                                                                                                                                                -27,
                                                                                                                                                                                                -31,
                                                                                                                                                                                                -91,
                                                                                                                                                                                                -61,
                                                                                                                                                                                                80,
                                                                                                                                                                                                -77,
                                                                                                                                                                                                -44,
                                                                                                                                                                                                -58,
                                                                                                                                                                                                -108,
                                                                                                                                                                                                -47,
                                                                                                                                                                                                -34,
                                                                                                                                                                                                -102,
                                                                                                                                                                                                -71,
                                                                                                                                                                                                31,
                                                                                                                                                                                                -101,
                                                                                                                                                                                                -85,
                                                                                                                                                                                                49,
                                                                                                                                                                                                -39,
                                                                                                                                                                                                -43,
                                                                                                                                                                                                19,
                                                                                                                                                                                                9,
                                                                                                                                                                                                27,
                                                                                                                                                                                                -63,
                                                                                                                                                                                                81,
                                                                                                                                                                                                -66,
                                                                                                                                                                                                80
                                                                                                                                                                                             };
                                                                                                                                                                                             byte[] byteValueArray85 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                byteValueArray,
                                                                                                                                                                                                byteValueArray84,
                                                                                                                                                                                                coreByteArrayEntry
                                                                                                                                                                                             );
                                                                                                                                                                                             this.byteFlagArray168 = itemEntry133Entry42.resolveByteArray(
                                                                                                                                                                                                byteValueArray85
                                                                                                                                                                                             );
                                                                                                                                                                                             value2 = (
                                                                                                                                                                                                   value2
                                                                                                                                                                                                      ^ -0x85fccf69aa4a33eL
                                                                                                                                                                                                )
                                                                                                                                                                                                + 0x7f9a5576635d23a5L;
                                                                                                                                                                                          }
                                                                                                                                                                                       } else {
                                                                                                                                                                                          ItemEntry133Entry itemEntry133Entry43 = new ItemEntry133Entry(
                                                                                                                                                                                             MovementWeightProperty.SHA3_256
                                                                                                                                                                                          );
                                                                                                                                                                                          byte[] byteValueArray86 = new byte[]{
                                                                                                                                                                                             78,
                                                                                                                                                                                             22,
                                                                                                                                                                                             94,
                                                                                                                                                                                             -25,
                                                                                                                                                                                             92,
                                                                                                                                                                                             -78,
                                                                                                                                                                                             -14,
                                                                                                                                                                                             -119,
                                                                                                                                                                                             97,
                                                                                                                                                                                             -34,
                                                                                                                                                                                             105,
                                                                                                                                                                                             -19,
                                                                                                                                                                                             32,
                                                                                                                                                                                             -84,
                                                                                                                                                                                             29,
                                                                                                                                                                                             -32,
                                                                                                                                                                                             78,
                                                                                                                                                                                             24,
                                                                                                                                                                                             -12,
                                                                                                                                                                                             104,
                                                                                                                                                                                             73,
                                                                                                                                                                                             107,
                                                                                                                                                                                             -116,
                                                                                                                                                                                             -76,
                                                                                                                                                                                             -47,
                                                                                                                                                                                             12,
                                                                                                                                                                                             -44,
                                                                                                                                                                                             -36,
                                                                                                                                                                                             9,
                                                                                                                                                                                             -66,
                                                                                                                                                                                             -41,
                                                                                                                                                                                             63
                                                                                                                                                                                          };
                                                                                                                                                                                          byte[] byteValueArray87 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                             byteValueArray,
                                                                                                                                                                                             byteValueArray86,
                                                                                                                                                                                             coreByteArrayEntry
                                                                                                                                                                                          );
                                                                                                                                                                                          this.byteFlagArray182 = itemEntry133Entry43.resolveByteArray(
                                                                                                                                                                                             byteValueArray87
                                                                                                                                                                                          );
                                                                                                                                                                                          value2 = (
                                                                                                                                                                                                value2
                                                                                                                                                                                                   ^ -0x335d85bb72ecbaeaL
                                                                                                                                                                                             )
                                                                                                                                                                                             - 0x3ed97f360c464e14L;
                                                                                                                                                                                       }
                                                                                                                                                                                    } else {
                                                                                                                                                                                       ItemEntry133Entry itemEntry133Entry44 = new ItemEntry133Entry(
                                                                                                                                                                                          MovementWeightProperty.SHA3_256
                                                                                                                                                                                       );
                                                                                                                                                                                       byte[] byteValueArray88 = new byte[]{
                                                                                                                                                                                          -26,
                                                                                                                                                                                          51,
                                                                                                                                                                                          48,
                                                                                                                                                                                          -90,
                                                                                                                                                                                          112,
                                                                                                                                                                                          -18,
                                                                                                                                                                                          109,
                                                                                                                                                                                          -119,
                                                                                                                                                                                          72,
                                                                                                                                                                                          12,
                                                                                                                                                                                          63,
                                                                                                                                                                                          63,
                                                                                                                                                                                          -28,
                                                                                                                                                                                          27,
                                                                                                                                                                                          101,
                                                                                                                                                                                          23,
                                                                                                                                                                                          -80,
                                                                                                                                                                                          44,
                                                                                                                                                                                          -38,
                                                                                                                                                                                          57,
                                                                                                                                                                                          101,
                                                                                                                                                                                          80,
                                                                                                                                                                                          -98,
                                                                                                                                                                                          -71,
                                                                                                                                                                                          -10,
                                                                                                                                                                                          -34,
                                                                                                                                                                                          1,
                                                                                                                                                                                          -11,
                                                                                                                                                                                          -11,
                                                                                                                                                                                          90,
                                                                                                                                                                                          -123,
                                                                                                                                                                                          -34
                                                                                                                                                                                       };
                                                                                                                                                                                       byte[] byteValueArray89 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                          byteValueArray,
                                                                                                                                                                                          byteValueArray88,
                                                                                                                                                                                          coreByteArrayEntry
                                                                                                                                                                                       );
                                                                                                                                                                                       this.byteFlagArray166 = itemEntry133Entry44.resolveByteArray(
                                                                                                                                                                                          byteValueArray89
                                                                                                                                                                                       );
                                                                                                                                                                                       value2 = (
                                                                                                                                                                                             value2
                                                                                                                                                                                                ^ 0xba16a4dc3c625d2L
                                                                                                                                                                                          )
                                                                                                                                                                                          + 0xae74418f613e342L;
                                                                                                                                                                                    }
                                                                                                                                                                                 } else {
                                                                                                                                                                                    ItemEntry133Entry itemEntry133Entry45 = new ItemEntry133Entry(
                                                                                                                                                                                       MovementWeightProperty.SHA3_256
                                                                                                                                                                                    );
                                                                                                                                                                                    byte[] byteValueArray90 = new byte[]{
                                                                                                                                                                                       123,
                                                                                                                                                                                       -49,
                                                                                                                                                                                       -40,
                                                                                                                                                                                       -66,
                                                                                                                                                                                       -58,
                                                                                                                                                                                       80,
                                                                                                                                                                                       17,
                                                                                                                                                                                       -41,
                                                                                                                                                                                       102,
                                                                                                                                                                                       104,
                                                                                                                                                                                       -71,
                                                                                                                                                                                       -75,
                                                                                                                                                                                       -47,
                                                                                                                                                                                       72,
                                                                                                                                                                                       -10,
                                                                                                                                                                                       -41,
                                                                                                                                                                                       -89,
                                                                                                                                                                                       5,
                                                                                                                                                                                       -111,
                                                                                                                                                                                       97,
                                                                                                                                                                                       -40,
                                                                                                                                                                                       80,
                                                                                                                                                                                       60,
                                                                                                                                                                                       67,
                                                                                                                                                                                       37,
                                                                                                                                                                                       80,
                                                                                                                                                                                       101,
                                                                                                                                                                                       -94,
                                                                                                                                                                                       -49,
                                                                                                                                                                                       -108,
                                                                                                                                                                                       -119,
                                                                                                                                                                                       47
                                                                                                                                                                                    };
                                                                                                                                                                                    byte[] byteValueArray91 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                       byteValueArray,
                                                                                                                                                                                       byteValueArray90,
                                                                                                                                                                                       coreByteArrayEntry
                                                                                                                                                                                    );
                                                                                                                                                                                    this.byteFlagArray196 = itemEntry133Entry45.resolveByteArray(
                                                                                                                                                                                       byteValueArray91
                                                                                                                                                                                    );
                                                                                                                                                                                    value2 = (
                                                                                                                                                                                          value2
                                                                                                                                                                                             ^ 0x2d4f2ea810d5fa6eL
                                                                                                                                                                                       )
                                                                                                                                                                                       - 0x671c26f7528f65aL;
                                                                                                                                                                                 }
                                                                                                                                                                              } else {
                                                                                                                                                                                 ItemEntry133Entry itemEntry133Entry46 = new ItemEntry133Entry(
                                                                                                                                                                                    MovementWeightProperty.SHA3_256
                                                                                                                                                                                 );
                                                                                                                                                                                 byte[] byteValueArray92 = new byte[]{
                                                                                                                                                                                    -88,
                                                                                                                                                                                    -32,
                                                                                                                                                                                    1,
                                                                                                                                                                                    76,
                                                                                                                                                                                    -1,
                                                                                                                                                                                    12,
                                                                                                                                                                                    47,
                                                                                                                                                                                    90,
                                                                                                                                                                                    123,
                                                                                                                                                                                    -128,
                                                                                                                                                                                    -82,
                                                                                                                                                                                    -52,
                                                                                                                                                                                    2,
                                                                                                                                                                                    5,
                                                                                                                                                                                    -66,
                                                                                                                                                                                    24,
                                                                                                                                                                                    101,
                                                                                                                                                                                    -80,
                                                                                                                                                                                    -24,
                                                                                                                                                                                    -39,
                                                                                                                                                                                    -35,
                                                                                                                                                                                    -48,
                                                                                                                                                                                    -126,
                                                                                                                                                                                    -122,
                                                                                                                                                                                    18,
                                                                                                                                                                                    94,
                                                                                                                                                                                    -17,
                                                                                                                                                                                    110,
                                                                                                                                                                                    -23,
                                                                                                                                                                                    7,
                                                                                                                                                                                    29,
                                                                                                                                                                                    85
                                                                                                                                                                                 };
                                                                                                                                                                                 byte[] byteValueArray93 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                    byteValueArray,
                                                                                                                                                                                    byteValueArray92,
                                                                                                                                                                                    coreByteArrayEntry
                                                                                                                                                                                 );
                                                                                                                                                                                 this.byteFlagArray3 = itemEntry133Entry46.resolveByteArray(
                                                                                                                                                                                    byteValueArray93
                                                                                                                                                                                 );
                                                                                                                                                                                 value2 = (
                                                                                                                                                                                       value2
                                                                                                                                                                                          ^ 0x4b4fbfe32b3fae4cL
                                                                                                                                                                                    )
                                                                                                                                                                                    + 0x151d53f280ee344bL;
                                                                                                                                                                              }
                                                                                                                                                                           } else {
                                                                                                                                                                              ItemEntry133Entry itemEntry133Entry47 = new ItemEntry133Entry(
                                                                                                                                                                                 MovementWeightProperty.SHA3_256
                                                                                                                                                                              );
                                                                                                                                                                              byte[] byteValueArray94 = new byte[]{
                                                                                                                                                                                 -119,
                                                                                                                                                                                 -122,
                                                                                                                                                                                 -21,
                                                                                                                                                                                 25,
                                                                                                                                                                                 -69,
                                                                                                                                                                                 82,
                                                                                                                                                                                 -84,
                                                                                                                                                                                 -126,
                                                                                                                                                                                 -84,
                                                                                                                                                                                 -56,
                                                                                                                                                                                 34,
                                                                                                                                                                                 108,
                                                                                                                                                                                 -55,
                                                                                                                                                                                 118,
                                                                                                                                                                                 120,
                                                                                                                                                                                 110,
                                                                                                                                                                                 -25,
                                                                                                                                                                                 124,
                                                                                                                                                                                 -9,
                                                                                                                                                                                 -66,
                                                                                                                                                                                 -31,
                                                                                                                                                                                 -31,
                                                                                                                                                                                 30,
                                                                                                                                                                                 9,
                                                                                                                                                                                 -59,
                                                                                                                                                                                 -39,
                                                                                                                                                                                 -14,
                                                                                                                                                                                 -43,
                                                                                                                                                                                 -43,
                                                                                                                                                                                 -119,
                                                                                                                                                                                 -63,
                                                                                                                                                                                 84
                                                                                                                                                                              };
                                                                                                                                                                              byte[] byteValueArray95 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                 byteValueArray,
                                                                                                                                                                                 byteValueArray94,
                                                                                                                                                                                 coreByteArrayEntry
                                                                                                                                                                              );
                                                                                                                                                                              this.byteFlagArray126 = itemEntry133Entry47.resolveByteArray(
                                                                                                                                                                                 byteValueArray95
                                                                                                                                                                              );
                                                                                                                                                                              value2 = (
                                                                                                                                                                                    value2
                                                                                                                                                                                       ^ 0x4fb0a5be9469e2b3L
                                                                                                                                                                                 )
                                                                                                                                                                                 + 0x3f4d3a5b41ec9cdeL;
                                                                                                                                                                           }
                                                                                                                                                                        } else {
                                                                                                                                                                           ItemEntry133Entry itemEntry133Entry48 = new ItemEntry133Entry(
                                                                                                                                                                              MovementWeightProperty.SHA3_256
                                                                                                                                                                           );
                                                                                                                                                                           byte[] byteValueArray96 = new byte[]{
                                                                                                                                                                              55,
                                                                                                                                                                              6,
                                                                                                                                                                              -115,
                                                                                                                                                                              94,
                                                                                                                                                                              -106,
                                                                                                                                                                              -11,
                                                                                                                                                                              -126,
                                                                                                                                                                              -113,
                                                                                                                                                                              -61,
                                                                                                                                                                              -7,
                                                                                                                                                                              -47,
                                                                                                                                                                              -81,
                                                                                                                                                                              88,
                                                                                                                                                                              -38,
                                                                                                                                                                              85,
                                                                                                                                                                              100,
                                                                                                                                                                              11,
                                                                                                                                                                              -110,
                                                                                                                                                                              72,
                                                                                                                                                                              77,
                                                                                                                                                                              -62,
                                                                                                                                                                              26,
                                                                                                                                                                              121,
                                                                                                                                                                              65,
                                                                                                                                                                              -65,
                                                                                                                                                                              74,
                                                                                                                                                                              58,
                                                                                                                                                                              -78,
                                                                                                                                                                              -11,
                                                                                                                                                                              -102,
                                                                                                                                                                              -103,
                                                                                                                                                                              33
                                                                                                                                                                           };
                                                                                                                                                                           byte[] byteValueArray97 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                              byteValueArray,
                                                                                                                                                                              byteValueArray96,
                                                                                                                                                                              coreByteArrayEntry
                                                                                                                                                                           );
                                                                                                                                                                           this.byteFlagArray16 = itemEntry133Entry48.resolveByteArray(
                                                                                                                                                                              byteValueArray97
                                                                                                                                                                           );
                                                                                                                                                                           value2 = (
                                                                                                                                                                                 value2
                                                                                                                                                                                    ^ 0x16629edfd79c829aL
                                                                                                                                                                              )
                                                                                                                                                                              - 0x13ce2a08023df60eL;
                                                                                                                                                                        }
                                                                                                                                                                     } else {
                                                                                                                                                                        ItemEntry133Entry itemEntry133Entry49 = new ItemEntry133Entry(
                                                                                                                                                                           MovementWeightProperty.SHA3_256
                                                                                                                                                                        );
                                                                                                                                                                        byte[] byteValueArray98 = new byte[]{
                                                                                                                                                                           41,
                                                                                                                                                                           110,
                                                                                                                                                                           -100,
                                                                                                                                                                           -1,
                                                                                                                                                                           121,
                                                                                                                                                                           -45,
                                                                                                                                                                           108,
                                                                                                                                                                           41,
                                                                                                                                                                           65,
                                                                                                                                                                           32,
                                                                                                                                                                           94,
                                                                                                                                                                           -82,
                                                                                                                                                                           66,
                                                                                                                                                                           -126,
                                                                                                                                                                           44,
                                                                                                                                                                           -38,
                                                                                                                                                                           -11,
                                                                                                                                                                           45,
                                                                                                                                                                           80,
                                                                                                                                                                           75,
                                                                                                                                                                           -92,
                                                                                                                                                                           86,
                                                                                                                                                                           89,
                                                                                                                                                                           -109,
                                                                                                                                                                           16,
                                                                                                                                                                           -83,
                                                                                                                                                                           121,
                                                                                                                                                                           -76,
                                                                                                                                                                           112,
                                                                                                                                                                           58,
                                                                                                                                                                           -23,
                                                                                                                                                                           117
                                                                                                                                                                        };
                                                                                                                                                                        byte[] byteValueArray99 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                           byteValueArray,
                                                                                                                                                                           byteValueArray98,
                                                                                                                                                                           coreByteArrayEntry
                                                                                                                                                                        );
                                                                                                                                                                        this.byteFlagArray33 = itemEntry133Entry49.resolveByteArray(
                                                                                                                                                                           byteValueArray99
                                                                                                                                                                        );
                                                                                                                                                                        value2 = (
                                                                                                                                                                              value2
                                                                                                                                                                                 ^ -0x1702c067cd63836dL
                                                                                                                                                                           )
                                                                                                                                                                           - 0x7c214903ba84f767L;
                                                                                                                                                                     }
                                                                                                                                                                  } else {
                                                                                                                                                                     ItemEntry133Entry itemEntry133Entry50 = new ItemEntry133Entry(
                                                                                                                                                                        MovementWeightProperty.SHA3_256
                                                                                                                                                                     );
                                                                                                                                                                     byte[] byteValueArray100 = new byte[]{
                                                                                                                                                                        9,
                                                                                                                                                                        42,
                                                                                                                                                                        38,
                                                                                                                                                                        -13,
                                                                                                                                                                        -95,
                                                                                                                                                                        -113,
                                                                                                                                                                        15,
                                                                                                                                                                        54,
                                                                                                                                                                        79,
                                                                                                                                                                        2,
                                                                                                                                                                        61,
                                                                                                                                                                        -50,
                                                                                                                                                                        -121,
                                                                                                                                                                        -110,
                                                                                                                                                                        -100,
                                                                                                                                                                        -33,
                                                                                                                                                                        -9,
                                                                                                                                                                        70,
                                                                                                                                                                        -17,
                                                                                                                                                                        -125,
                                                                                                                                                                        -9,
                                                                                                                                                                        -82,
                                                                                                                                                                        -109,
                                                                                                                                                                        -123,
                                                                                                                                                                        -68,
                                                                                                                                                                        -38,
                                                                                                                                                                        69,
                                                                                                                                                                        36,
                                                                                                                                                                        -95,
                                                                                                                                                                        -91,
                                                                                                                                                                        -62,
                                                                                                                                                                        31
                                                                                                                                                                     };
                                                                                                                                                                     byte[] byteValueArray101 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                        byteValueArray,
                                                                                                                                                                        byteValueArray100,
                                                                                                                                                                        coreByteArrayEntry
                                                                                                                                                                     );
                                                                                                                                                                     this.byteFlagArray121 = itemEntry133Entry50.resolveByteArray(
                                                                                                                                                                        byteValueArray101
                                                                                                                                                                     );
                                                                                                                                                                     value2 = (
                                                                                                                                                                           value2
                                                                                                                                                                              ^ -0x72fdf0df74b93da8L
                                                                                                                                                                        )
                                                                                                                                                                        - 0x35b0a6ebd075167aL;
                                                                                                                                                                  }
                                                                                                                                                               } else {
                                                                                                                                                                  ItemEntry133Entry itemEntry133Entry51 = new ItemEntry133Entry(
                                                                                                                                                                     MovementWeightProperty.SHA3_256
                                                                                                                                                                  );
                                                                                                                                                                  byte[] byteValueArray102 = new byte[]{
                                                                                                                                                                     -21,
                                                                                                                                                                     -64,
                                                                                                                                                                     -31,
                                                                                                                                                                     48,
                                                                                                                                                                     -51,
                                                                                                                                                                     1,
                                                                                                                                                                     -22,
                                                                                                                                                                     -22,
                                                                                                                                                                     91,
                                                                                                                                                                     18,
                                                                                                                                                                     32,
                                                                                                                                                                     28,
                                                                                                                                                                     -51,
                                                                                                                                                                     -8,
                                                                                                                                                                     35,
                                                                                                                                                                     -88,
                                                                                                                                                                     87,
                                                                                                                                                                     68,
                                                                                                                                                                     101,
                                                                                                                                                                     40,
                                                                                                                                                                     -93,
                                                                                                                                                                     -80,
                                                                                                                                                                     -69,
                                                                                                                                                                     41,
                                                                                                                                                                     -99,
                                                                                                                                                                     19,
                                                                                                                                                                     112,
                                                                                                                                                                     -100,
                                                                                                                                                                     84,
                                                                                                                                                                     -15,
                                                                                                                                                                     -3,
                                                                                                                                                                     86
                                                                                                                                                                  };
                                                                                                                                                                  byte[] byteValueArray103 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                     byteValueArray,
                                                                                                                                                                     byteValueArray102,
                                                                                                                                                                     coreByteArrayEntry
                                                                                                                                                                  );
                                                                                                                                                                  this.byteFlagArray170 = itemEntry133Entry51.resolveByteArray(
                                                                                                                                                                     byteValueArray103
                                                                                                                                                                  );
                                                                                                                                                                  value2 = (
                                                                                                                                                                        value2
                                                                                                                                                                           ^ -0x1bfbfea64d6c8481L
                                                                                                                                                                     )
                                                                                                                                                                     - 0x75921d9b3dc992a9L;
                                                                                                                                                               }
                                                                                                                                                            } else {
                                                                                                                                                               ItemEntry133Entry itemEntry133Entry52 = new ItemEntry133Entry(
                                                                                                                                                                  MovementWeightProperty.SHA3_256
                                                                                                                                                               );
                                                                                                                                                               byte[] byteValueArray104 = new byte[]{
                                                                                                                                                                  -126,
                                                                                                                                                                  -34,
                                                                                                                                                                  58,
                                                                                                                                                                  -26,
                                                                                                                                                                  -117,
                                                                                                                                                                  -51,
                                                                                                                                                                  -27,
                                                                                                                                                                  -100,
                                                                                                                                                                  21,
                                                                                                                                                                  -93,
                                                                                                                                                                  -77,
                                                                                                                                                                  60,
                                                                                                                                                                  26,
                                                                                                                                                                  89,
                                                                                                                                                                  -92,
                                                                                                                                                                  -18,
                                                                                                                                                                  -66,
                                                                                                                                                                  44,
                                                                                                                                                                  106,
                                                                                                                                                                  117,
                                                                                                                                                                  86,
                                                                                                                                                                  -103,
                                                                                                                                                                  32,
                                                                                                                                                                  119,
                                                                                                                                                                  45,
                                                                                                                                                                  1,
                                                                                                                                                                  7,
                                                                                                                                                                  -6,
                                                                                                                                                                  1,
                                                                                                                                                                  -35,
                                                                                                                                                                  -72,
                                                                                                                                                                  -86
                                                                                                                                                               };
                                                                                                                                                               byte[] byteValueArray105 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                  byteValueArray,
                                                                                                                                                                  byteValueArray104,
                                                                                                                                                                  coreByteArrayEntry
                                                                                                                                                               );
                                                                                                                                                               this.byteFlagArray139 = itemEntry133Entry52.resolveByteArray(
                                                                                                                                                                  byteValueArray105
                                                                                                                                                               );
                                                                                                                                                               value2 = (
                                                                                                                                                                     value2
                                                                                                                                                                        ^ 0x17495bc0499aad65L
                                                                                                                                                                  )
                                                                                                                                                                  - 0x73dbe2a2221f676L;
                                                                                                                                                            }
                                                                                                                                                         } else {
                                                                                                                                                            ItemEntry133Entry itemEntry133Entry53 = new ItemEntry133Entry(
                                                                                                                                                               MovementWeightProperty.SHA3_256
                                                                                                                                                            );
                                                                                                                                                            byte[] byteValueArray106 = new byte[]{
                                                                                                                                                               59,
                                                                                                                                                               -119,
                                                                                                                                                               -105,
                                                                                                                                                               -75,
                                                                                                                                                               -120,
                                                                                                                                                               99,
                                                                                                                                                               -99,
                                                                                                                                                               43,
                                                                                                                                                               98,
                                                                                                                                                               107,
                                                                                                                                                               65,
                                                                                                                                                               104,
                                                                                                                                                               52,
                                                                                                                                                               -84,
                                                                                                                                                               -28,
                                                                                                                                                               -51,
                                                                                                                                                               -26,
                                                                                                                                                               43,
                                                                                                                                                               94,
                                                                                                                                                               -102,
                                                                                                                                                               37,
                                                                                                                                                               86,
                                                                                                                                                               -33,
                                                                                                                                                               102,
                                                                                                                                                               -65,
                                                                                                                                                               -89,
                                                                                                                                                               -93,
                                                                                                                                                               27,
                                                                                                                                                               -28,
                                                                                                                                                               -80,
                                                                                                                                                               -127,
                                                                                                                                                               -81
                                                                                                                                                            };
                                                                                                                                                            byte[] byteValueArray107 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                               byteValueArray,
                                                                                                                                                               byteValueArray106,
                                                                                                                                                               coreByteArrayEntry
                                                                                                                                                            );
                                                                                                                                                            this.byteFlagArray110 = itemEntry133Entry53.resolveByteArray(
                                                                                                                                                               byteValueArray107
                                                                                                                                                            );
                                                                                                                                                            value2 = (
                                                                                                                                                                  value2
                                                                                                                                                                     ^ 0x7da5c2b79f64a3caL
                                                                                                                                                               )
                                                                                                                                                               + 0x15257792bf74e5dbL;
                                                                                                                                                         }
                                                                                                                                                      } else {
                                                                                                                                                         ItemEntry133Entry itemEntry133Entry54 = new ItemEntry133Entry(
                                                                                                                                                            MovementWeightProperty.SHA3_256
                                                                                                                                                         );
                                                                                                                                                         byte[] byteValueArray108 = new byte[]{
                                                                                                                                                            108,
                                                                                                                                                            40,
                                                                                                                                                            -84,
                                                                                                                                                            -22,
                                                                                                                                                            108,
                                                                                                                                                            5,
                                                                                                                                                            -3,
                                                                                                                                                            -56,
                                                                                                                                                            -36,
                                                                                                                                                            56,
                                                                                                                                                            63,
                                                                                                                                                            49,
                                                                                                                                                            -5,
                                                                                                                                                            -32,
                                                                                                                                                            108,
                                                                                                                                                            81,
                                                                                                                                                            -62,
                                                                                                                                                            -31,
                                                                                                                                                            53,
                                                                                                                                                            -58,
                                                                                                                                                            -5,
                                                                                                                                                            31,
                                                                                                                                                            77,
                                                                                                                                                            -88,
                                                                                                                                                            27,
                                                                                                                                                            -4,
                                                                                                                                                            -35,
                                                                                                                                                            57,
                                                                                                                                                            -56,
                                                                                                                                                            -128,
                                                                                                                                                            -45,
                                                                                                                                                            -15
                                                                                                                                                         };
                                                                                                                                                         byte[] byteValueArray109 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                            byteValueArray,
                                                                                                                                                            byteValueArray108,
                                                                                                                                                            coreByteArrayEntry
                                                                                                                                                         );
                                                                                                                                                         this.byteFlagArray104 = itemEntry133Entry54.resolveByteArray(
                                                                                                                                                            byteValueArray109
                                                                                                                                                         );
                                                                                                                                                         value2 = (
                                                                                                                                                               value2
                                                                                                                                                                  ^ -0x313d7cc50b29c605L
                                                                                                                                                            )
                                                                                                                                                            - 0x612701f7455a00edL;
                                                                                                                                                      }
                                                                                                                                                   } else {
                                                                                                                                                      ItemEntry133Entry itemEntry133Entry55 = new ItemEntry133Entry(
                                                                                                                                                         MovementWeightProperty.SHA3_256
                                                                                                                                                      );
                                                                                                                                                      byte[] byteValueArray110 = new byte[]{
                                                                                                                                                         -108,
                                                                                                                                                         -87,
                                                                                                                                                         76,
                                                                                                                                                         117,
                                                                                                                                                         -61,
                                                                                                                                                         -61,
                                                                                                                                                         -70,
                                                                                                                                                         -116,
                                                                                                                                                         20,
                                                                                                                                                         30,
                                                                                                                                                         -111,
                                                                                                                                                         -32,
                                                                                                                                                         116,
                                                                                                                                                         -109,
                                                                                                                                                         -106,
                                                                                                                                                         92,
                                                                                                                                                         -28,
                                                                                                                                                         5,
                                                                                                                                                         -46,
                                                                                                                                                         -108,
                                                                                                                                                         -90,
                                                                                                                                                         -117,
                                                                                                                                                         107,
                                                                                                                                                         -32,
                                                                                                                                                         84,
                                                                                                                                                         -62,
                                                                                                                                                         55,
                                                                                                                                                         3,
                                                                                                                                                         -54,
                                                                                                                                                         -121,
                                                                                                                                                         -21,
                                                                                                                                                         5
                                                                                                                                                      };
                                                                                                                                                      byte[] byteValueArray111 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                         byteValueArray,
                                                                                                                                                         byteValueArray110,
                                                                                                                                                         coreByteArrayEntry
                                                                                                                                                      );
                                                                                                                                                      this.byteFlagArray57 = itemEntry133Entry55.resolveByteArray(
                                                                                                                                                         byteValueArray111
                                                                                                                                                      );
                                                                                                                                                      value2 = (
                                                                                                                                                            value2
                                                                                                                                                               ^ -0x5019f3cf33c296eL
                                                                                                                                                         )
                                                                                                                                                         + 0x5ef1e057d7455490L;
                                                                                                                                                   }
                                                                                                                                                } else {
                                                                                                                                                   ItemEntry133Entry itemEntry133Entry56 = new ItemEntry133Entry(
                                                                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                                                                   );
                                                                                                                                                   byte[] byteValueArray112 = new byte[]{
                                                                                                                                                      -2,
                                                                                                                                                      -5,
                                                                                                                                                      2,
                                                                                                                                                      -85,
                                                                                                                                                      -94,
                                                                                                                                                      -34,
                                                                                                                                                      -57,
                                                                                                                                                      22,
                                                                                                                                                      -16,
                                                                                                                                                      2,
                                                                                                                                                      -76,
                                                                                                                                                      -56,
                                                                                                                                                      -20,
                                                                                                                                                      56,
                                                                                                                                                      -89,
                                                                                                                                                      -41,
                                                                                                                                                      -117,
                                                                                                                                                      -59,
                                                                                                                                                      -42,
                                                                                                                                                      93,
                                                                                                                                                      -53,
                                                                                                                                                      -22,
                                                                                                                                                      63,
                                                                                                                                                      -65,
                                                                                                                                                      -56,
                                                                                                                                                      -94,
                                                                                                                                                      119,
                                                                                                                                                      59,
                                                                                                                                                      55,
                                                                                                                                                      -95,
                                                                                                                                                      -80,
                                                                                                                                                      -48
                                                                                                                                                   };
                                                                                                                                                   byte[] byteValueArray113 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                      byteValueArray,
                                                                                                                                                      byteValueArray112,
                                                                                                                                                      coreByteArrayEntry
                                                                                                                                                   );
                                                                                                                                                   this.byteFlagArray204 = itemEntry133Entry56.resolveByteArray(
                                                                                                                                                      byteValueArray113
                                                                                                                                                   );
                                                                                                                                                   value2 = (
                                                                                                                                                         value2
                                                                                                                                                            ^ -0x47af4aeebfabb605L
                                                                                                                                                      )
                                                                                                                                                      - 0x33e39f70a13d377aL;
                                                                                                                                                }
                                                                                                                                             } else {
                                                                                                                                                ItemEntry133Entry itemEntry133Entry57 = new ItemEntry133Entry(
                                                                                                                                                   MovementWeightProperty.SHA3_256
                                                                                                                                                );
                                                                                                                                                byte[] byteValueArray114 = new byte[]{
                                                                                                                                                   26,
                                                                                                                                                   66,
                                                                                                                                                   41,
                                                                                                                                                   -3,
                                                                                                                                                   -41,
                                                                                                                                                   -116,
                                                                                                                                                   -50,
                                                                                                                                                   -61,
                                                                                                                                                   126,
                                                                                                                                                   -61,
                                                                                                                                                   -57,
                                                                                                                                                   -126,
                                                                                                                                                   -115,
                                                                                                                                                   -53,
                                                                                                                                                   13,
                                                                                                                                                   -101,
                                                                                                                                                   118,
                                                                                                                                                   73,
                                                                                                                                                   6,
                                                                                                                                                   -84,
                                                                                                                                                   120,
                                                                                                                                                   66,
                                                                                                                                                   -101,
                                                                                                                                                   -106,
                                                                                                                                                   21,
                                                                                                                                                   60,
                                                                                                                                                   6,
                                                                                                                                                   102,
                                                                                                                                                   94,
                                                                                                                                                   -48,
                                                                                                                                                   -43,
                                                                                                                                                   -51
                                                                                                                                                };
                                                                                                                                                byte[] byteValueArray115 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                   byteValueArray,
                                                                                                                                                   byteValueArray114,
                                                                                                                                                   coreByteArrayEntry
                                                                                                                                                );
                                                                                                                                                this.byteFlagArray138 = itemEntry133Entry57.resolveByteArray(
                                                                                                                                                   byteValueArray115
                                                                                                                                                );
                                                                                                                                                value2 = (
                                                                                                                                                      value2
                                                                                                                                                         ^ 0x6988996cdc6f0669L
                                                                                                                                                   )
                                                                                                                                                   + 0x2f727196df755e8bL;
                                                                                                                                             }
                                                                                                                                          } else {
                                                                                                                                             ItemEntry133Entry itemEntry133Entry58 = new ItemEntry133Entry(
                                                                                                                                                MovementWeightProperty.SHA3_256
                                                                                                                                             );
                                                                                                                                             byte[] byteValueArray116 = new byte[]{
                                                                                                                                                87,
                                                                                                                                                -79,
                                                                                                                                                48,
                                                                                                                                                99,
                                                                                                                                                89,
                                                                                                                                                1,
                                                                                                                                                53,
                                                                                                                                                -21,
                                                                                                                                                -85,
                                                                                                                                                120,
                                                                                                                                                49,
                                                                                                                                                -114,
                                                                                                                                                -32,
                                                                                                                                                17,
                                                                                                                                                19,
                                                                                                                                                -19,
                                                                                                                                                21,
                                                                                                                                                -118,
                                                                                                                                                -128,
                                                                                                                                                -16,
                                                                                                                                                -70,
                                                                                                                                                -50,
                                                                                                                                                -8,
                                                                                                                                                -53,
                                                                                                                                                127,
                                                                                                                                                -77,
                                                                                                                                                -31,
                                                                                                                                                -32,
                                                                                                                                                115,
                                                                                                                                                113,
                                                                                                                                                -11,
                                                                                                                                                -1
                                                                                                                                             };
                                                                                                                                             byte[] byteValueArray117 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                byteValueArray,
                                                                                                                                                byteValueArray116,
                                                                                                                                                coreByteArrayEntry
                                                                                                                                             );
                                                                                                                                             this.byteFlagArray78 = itemEntry133Entry58.resolveByteArray(
                                                                                                                                                byteValueArray117
                                                                                                                                             );
                                                                                                                                             value2 = (
                                                                                                                                                   value2
                                                                                                                                                      ^ -0x241460c93266d6c4L
                                                                                                                                                )
                                                                                                                                                - 0x48ba233c0c3bc298L;
                                                                                                                                          }
                                                                                                                                       } else {
                                                                                                                                          ItemEntry133Entry itemEntry133Entry59 = new ItemEntry133Entry(
                                                                                                                                             MovementWeightProperty.SHA3_256
                                                                                                                                          );
                                                                                                                                          byte[] byteValueArray118 = new byte[]{
                                                                                                                                             -57,
                                                                                                                                             68,
                                                                                                                                             -19,
                                                                                                                                             96,
                                                                                                                                             -48,
                                                                                                                                             9,
                                                                                                                                             -72,
                                                                                                                                             -109,
                                                                                                                                             42,
                                                                                                                                             112,
                                                                                                                                             110,
                                                                                                                                             123,
                                                                                                                                             68,
                                                                                                                                             -124,
                                                                                                                                             -115,
                                                                                                                                             -127,
                                                                                                                                             65,
                                                                                                                                             -70,
                                                                                                                                             -71,
                                                                                                                                             -53,
                                                                                                                                             112,
                                                                                                                                             107,
                                                                                                                                             76,
                                                                                                                                             -81,
                                                                                                                                             -71,
                                                                                                                                             41,
                                                                                                                                             -48,
                                                                                                                                             -4,
                                                                                                                                             -117,
                                                                                                                                             -46,
                                                                                                                                             -25,
                                                                                                                                             54
                                                                                                                                          };
                                                                                                                                          byte[] byteValueArray119 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                             byteValueArray,
                                                                                                                                             byteValueArray118,
                                                                                                                                             coreByteArrayEntry
                                                                                                                                          );
                                                                                                                                          this.byteFlagArray132 = itemEntry133Entry59.resolveByteArray(
                                                                                                                                             byteValueArray119
                                                                                                                                          );
                                                                                                                                          value2 = (
                                                                                                                                                value2
                                                                                                                                                   ^ 0x328c1355f88049b8L
                                                                                                                                             )
                                                                                                                                             + 0xb719518233512acL;
                                                                                                                                       }
                                                                                                                                    } else {
                                                                                                                                       ItemEntry133Entry itemEntry133Entry60 = new ItemEntry133Entry(
                                                                                                                                          MovementWeightProperty.SHA3_256
                                                                                                                                       );
                                                                                                                                       byte[] byteValueArray120 = new byte[]{
                                                                                                                                          -102,
                                                                                                                                          -111,
                                                                                                                                          -31,
                                                                                                                                          103,
                                                                                                                                          115,
                                                                                                                                          112,
                                                                                                                                          19,
                                                                                                                                          -7,
                                                                                                                                          -1,
                                                                                                                                          39,
                                                                                                                                          99,
                                                                                                                                          -97,
                                                                                                                                          -24,
                                                                                                                                          -19,
                                                                                                                                          127,
                                                                                                                                          102,
                                                                                                                                          121,
                                                                                                                                          -111,
                                                                                                                                          -55,
                                                                                                                                          -82,
                                                                                                                                          29,
                                                                                                                                          39,
                                                                                                                                          -16,
                                                                                                                                          25,
                                                                                                                                          112,
                                                                                                                                          14,
                                                                                                                                          113,
                                                                                                                                          87,
                                                                                                                                          -104,
                                                                                                                                          -113,
                                                                                                                                          -11,
                                                                                                                                          -75
                                                                                                                                       };
                                                                                                                                       byte[] byteValueArray121 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                          byteValueArray, byteValueArray120, coreByteArrayEntry
                                                                                                                                       );
                                                                                                                                       this.byteFlagArray116 = itemEntry133Entry60.resolveByteArray(
                                                                                                                                          byteValueArray121
                                                                                                                                       );
                                                                                                                                       value2 = (
                                                                                                                                             value2
                                                                                                                                                ^ 0x751d46b2f5750f7L
                                                                                                                                          )
                                                                                                                                          - 0x39350ca4334ddc01L;
                                                                                                                                    }
                                                                                                                                 } else {
                                                                                                                                    ItemEntry133Entry itemEntry133Entry61 = new ItemEntry133Entry(
                                                                                                                                       MovementWeightProperty.SHA3_256
                                                                                                                                    );
                                                                                                                                    byte[] byteValueArray122 = new byte[]{
                                                                                                                                       -123,
                                                                                                                                       -26,
                                                                                                                                       126,
                                                                                                                                       91,
                                                                                                                                       28,
                                                                                                                                       -44,
                                                                                                                                       -51,
                                                                                                                                       102,
                                                                                                                                       46,
                                                                                                                                       -75,
                                                                                                                                       -58,
                                                                                                                                       -30,
                                                                                                                                       113,
                                                                                                                                       44,
                                                                                                                                       54,
                                                                                                                                       40,
                                                                                                                                       -36,
                                                                                                                                       71,
                                                                                                                                       74,
                                                                                                                                       -54,
                                                                                                                                       -14,
                                                                                                                                       -99,
                                                                                                                                       -22,
                                                                                                                                       -120,
                                                                                                                                       86,
                                                                                                                                       43,
                                                                                                                                       80,
                                                                                                                                       110,
                                                                                                                                       48,
                                                                                                                                       107,
                                                                                                                                       107,
                                                                                                                                       -104
                                                                                                                                    };
                                                                                                                                    byte[] byteValueArray123 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                       byteValueArray, byteValueArray122, coreByteArrayEntry
                                                                                                                                    );
                                                                                                                                    this.byteFlagArray27 = itemEntry133Entry61.resolveByteArray(
                                                                                                                                       byteValueArray123
                                                                                                                                    );
                                                                                                                                    value2 = (
                                                                                                                                          value2
                                                                                                                                             ^ 0xc99905d24165a54L
                                                                                                                                       )
                                                                                                                                       + 0x19ae358ecda7164dL;
                                                                                                                                 }
                                                                                                                              } else {
                                                                                                                                 ItemEntry133Entry itemEntry133Entry62 = new ItemEntry133Entry(
                                                                                                                                    MovementWeightProperty.SHA3_256
                                                                                                                                 );
                                                                                                                                 byte[] byteValueArray124 = new byte[]{
                                                                                                                                    112,
                                                                                                                                    93,
                                                                                                                                    -72,
                                                                                                                                    -4,
                                                                                                                                    124,
                                                                                                                                    67,
                                                                                                                                    62,
                                                                                                                                    -50,
                                                                                                                                    -107,
                                                                                                                                    47,
                                                                                                                                    -13,
                                                                                                                                    9,
                                                                                                                                    -112,
                                                                                                                                    101,
                                                                                                                                    -5,
                                                                                                                                    -29,
                                                                                                                                    118,
                                                                                                                                    102,
                                                                                                                                    44,
                                                                                                                                    82,
                                                                                                                                    -22,
                                                                                                                                    -24,
                                                                                                                                    -13,
                                                                                                                                    15,
                                                                                                                                    -40,
                                                                                                                                    -58,
                                                                                                                                    -29,
                                                                                                                                    -24,
                                                                                                                                    -112,
                                                                                                                                    -76,
                                                                                                                                    -102,
                                                                                                                                    29
                                                                                                                                 };
                                                                                                                                 byte[] byteValueArray125 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                    byteValueArray, byteValueArray124, coreByteArrayEntry
                                                                                                                                 );
                                                                                                                                 this.byteFlagArray173 = itemEntry133Entry62.resolveByteArray(
                                                                                                                                    byteValueArray125
                                                                                                                                 );
                                                                                                                                 value2 = (
                                                                                                                                       value2
                                                                                                                                          ^ 0x10b83109ce3a5f5dL
                                                                                                                                    )
                                                                                                                                    + 0x453837a2433a918cL;
                                                                                                                              }
                                                                                                                           } else {
                                                                                                                              ItemEntry133Entry itemEntry133Entry63 = new ItemEntry133Entry(
                                                                                                                                 MovementWeightProperty.SHA3_256
                                                                                                                              );
                                                                                                                              byte[] byteValueArray126 = new byte[]{
                                                                                                                                 6,
                                                                                                                                 -61,
                                                                                                                                 122,
                                                                                                                                 -60,
                                                                                                                                 -82,
                                                                                                                                 -62,
                                                                                                                                 -27,
                                                                                                                                 -41,
                                                                                                                                 57,
                                                                                                                                 108,
                                                                                                                                 112,
                                                                                                                                 62,
                                                                                                                                 -117,
                                                                                                                                 104,
                                                                                                                                 0,
                                                                                                                                 -68,
                                                                                                                                 -52,
                                                                                                                                 -27,
                                                                                                                                 114,
                                                                                                                                 -83,
                                                                                                                                 -6,
                                                                                                                                 -18,
                                                                                                                                 -97,
                                                                                                                                 15,
                                                                                                                                 -122,
                                                                                                                                 -122,
                                                                                                                                 -104,
                                                                                                                                 -109,
                                                                                                                                 127,
                                                                                                                                 -91,
                                                                                                                                 71,
                                                                                                                                 -120
                                                                                                                              };
                                                                                                                              byte[] byteValueArray127 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                 byteValueArray, byteValueArray126, coreByteArrayEntry
                                                                                                                              );
                                                                                                                              this.byteFlagArray63 = itemEntry133Entry63.resolveByteArray(
                                                                                                                                 byteValueArray127
                                                                                                                              );
                                                                                                                              value2 = (
                                                                                                                                    value2
                                                                                                                                       ^ 0x62e1ea4218855cb1L
                                                                                                                                 )
                                                                                                                                 + 0xd3103acce87374eL;
                                                                                                                           }
                                                                                                                        } else {
                                                                                                                           ItemEntry133Entry itemEntry133Entry64 = new ItemEntry133Entry(
                                                                                                                              MovementWeightProperty.SHA3_256
                                                                                                                           );
                                                                                                                           byte[] byteValueArray128 = new byte[]{
                                                                                                                              24,
                                                                                                                              72,
                                                                                                                              -55,
                                                                                                                              113,
                                                                                                                              -56,
                                                                                                                              125,
                                                                                                                              -81,
                                                                                                                              74,
                                                                                                                              -74,
                                                                                                                              -124,
                                                                                                                              -30,
                                                                                                                              70,
                                                                                                                              -20,
                                                                                                                              31,
                                                                                                                              -8,
                                                                                                                              72,
                                                                                                                              -43,
                                                                                                                              114,
                                                                                                                              -20,
                                                                                                                              124,
                                                                                                                              66,
                                                                                                                              4,
                                                                                                                              -3,
                                                                                                                              94,
                                                                                                                              -79,
                                                                                                                              67,
                                                                                                                              -51,
                                                                                                                              -7,
                                                                                                                              3,
                                                                                                                              99,
                                                                                                                              -80,
                                                                                                                              -66
                                                                                                                           };
                                                                                                                           byte[] byteValueArray129 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                              byteValueArray, byteValueArray128, coreByteArrayEntry
                                                                                                                           );
                                                                                                                           this.byteFlagArray37 = itemEntry133Entry64.resolveByteArray(
                                                                                                                              byteValueArray129
                                                                                                                           );
                                                                                                                           value2 = (
                                                                                                                                 value2 ^ -0x7c2a4f617cec74b6L
                                                                                                                              )
                                                                                                                              + 0x5857b87a48e836f5L;
                                                                                                                        }
                                                                                                                     } else {
                                                                                                                        ItemEntry133Entry itemEntry133Entry65 = new ItemEntry133Entry(
                                                                                                                           MovementWeightProperty.SHA3_256
                                                                                                                        );
                                                                                                                        byte[] byteValueArray130 = new byte[]{
                                                                                                                           79,
                                                                                                                           -33,
                                                                                                                           -127,
                                                                                                                           125,
                                                                                                                           -8,
                                                                                                                           116,
                                                                                                                           -111,
                                                                                                                           96,
                                                                                                                           -119,
                                                                                                                           -65,
                                                                                                                           117,
                                                                                                                           55,
                                                                                                                           -47,
                                                                                                                           14,
                                                                                                                           117,
                                                                                                                           -7,
                                                                                                                           -35,
                                                                                                                           20,
                                                                                                                           122,
                                                                                                                           -14,
                                                                                                                           -70,
                                                                                                                           36,
                                                                                                                           -115,
                                                                                                                           -99,
                                                                                                                           -119,
                                                                                                                           -107,
                                                                                                                           122,
                                                                                                                           -114,
                                                                                                                           -71,
                                                                                                                           56,
                                                                                                                           119,
                                                                                                                           117
                                                                                                                        };
                                                                                                                        byte[] byteValueArray131 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                           byteValueArray, byteValueArray130, coreByteArrayEntry
                                                                                                                        );
                                                                                                                        this.byteFlagArray19 = itemEntry133Entry65.resolveByteArray(
                                                                                                                           byteValueArray131
                                                                                                                        );
                                                                                                                        value2 = (value2 ^ -0x4cc2179fef155584L)
                                                                                                                           + 0x50dc38ded78569ddL;
                                                                                                                     }
                                                                                                                  } else {
                                                                                                                     ItemEntry133Entry itemEntry133Entry66 = new ItemEntry133Entry(
                                                                                                                        MovementWeightProperty.SHA3_256
                                                                                                                     );
                                                                                                                     byte[] byteValueArray132 = new byte[]{
                                                                                                                        80,
                                                                                                                        63,
                                                                                                                        -36,
                                                                                                                        1,
                                                                                                                        -96,
                                                                                                                        -111,
                                                                                                                        65,
                                                                                                                        88,
                                                                                                                        -77,
                                                                                                                        -4,
                                                                                                                        -48,
                                                                                                                        -111,
                                                                                                                        28,
                                                                                                                        -38,
                                                                                                                        13,
                                                                                                                        -101,
                                                                                                                        105,
                                                                                                                        22,
                                                                                                                        -22,
                                                                                                                        2,
                                                                                                                        -15,
                                                                                                                        -91,
                                                                                                                        -65,
                                                                                                                        -28,
                                                                                                                        -66,
                                                                                                                        60,
                                                                                                                        -106,
                                                                                                                        20,
                                                                                                                        -124,
                                                                                                                        121,
                                                                                                                        -66,
                                                                                                                        71
                                                                                                                     };
                                                                                                                     byte[] byteValueArray133 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                        byteValueArray, byteValueArray132, coreByteArrayEntry
                                                                                                                     );
                                                                                                                     this.byteFlagArray105 = itemEntry133Entry66.resolveByteArray(
                                                                                                                        byteValueArray133
                                                                                                                     );
                                                                                                                     value2 = (value2 ^ 0x2700702a61affd9dL)
                                                                                                                        + 0x35b1b41451b217c6L;
                                                                                                                  }
                                                                                                               } else {
                                                                                                                  ItemEntry133Entry itemEntry133Entry67 = new ItemEntry133Entry(
                                                                                                                     MovementWeightProperty.SHA3_256
                                                                                                                  );
                                                                                                                  byte[] byteValueArray134 = new byte[]{
                                                                                                                     -100,
                                                                                                                     91,
                                                                                                                     122,
                                                                                                                     104,
                                                                                                                     -91,
                                                                                                                     97,
                                                                                                                     107,
                                                                                                                     107,
                                                                                                                     6,
                                                                                                                     117,
                                                                                                                     -111,
                                                                                                                     -84,
                                                                                                                     -26,
                                                                                                                     14,
                                                                                                                     3,
                                                                                                                     -85,
                                                                                                                     -45,
                                                                                                                     -5,
                                                                                                                     76,
                                                                                                                     73,
                                                                                                                     73,
                                                                                                                     -88,
                                                                                                                     -62,
                                                                                                                     -40,
                                                                                                                     -44,
                                                                                                                     -75,
                                                                                                                     99,
                                                                                                                     -4,
                                                                                                                     104,
                                                                                                                     65,
                                                                                                                     36,
                                                                                                                     -84
                                                                                                                  };
                                                                                                                  byte[] byteValueArray135 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                     byteValueArray, byteValueArray134, coreByteArrayEntry
                                                                                                                  );
                                                                                                                  this.byteFlagArray178 = itemEntry133Entry67.resolveByteArray(
                                                                                                                     byteValueArray135
                                                                                                                  );
                                                                                                                  value2 = (value2 ^ -0x2c6631e87c0e5f20L)
                                                                                                                     - 0x6993d6a74c7f8ed2L;
                                                                                                               }
                                                                                                            } else {
                                                                                                               ItemEntry133Entry itemEntry133Entry68 = new ItemEntry133Entry(
                                                                                                                  MovementWeightProperty.SHA3_256
                                                                                                               );
                                                                                                               byte[] byteValueArray136 = new byte[]{
                                                                                                                  -59,
                                                                                                                  87,
                                                                                                                  76,
                                                                                                                  -45,
                                                                                                                  -75,
                                                                                                                  -127,
                                                                                                                  -19,
                                                                                                                  -6,
                                                                                                                  -94,
                                                                                                                  -12,
                                                                                                                  -39,
                                                                                                                  37,
                                                                                                                  88,
                                                                                                                  28,
                                                                                                                  96,
                                                                                                                  -23,
                                                                                                                  71,
                                                                                                                  -82,
                                                                                                                  -30,
                                                                                                                  119,
                                                                                                                  -28,
                                                                                                                  83,
                                                                                                                  72,
                                                                                                                  95,
                                                                                                                  -48,
                                                                                                                  -72,
                                                                                                                  -84,
                                                                                                                  30,
                                                                                                                  127,
                                                                                                                  -79,
                                                                                                                  -36,
                                                                                                                  -72
                                                                                                               };
                                                                                                               byte[] byteValueArray137 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                  byteValueArray, byteValueArray136, coreByteArrayEntry
                                                                                                               );
                                                                                                               this.byteFlagArray187 = itemEntry133Entry68.resolveByteArray(
                                                                                                                  byteValueArray137
                                                                                                               );
                                                                                                               value2 = (value2 ^ -0x443990cccac02f2fL)
                                                                                                                  - 0x45a819ee45445750L;
                                                                                                            }
                                                                                                         } else {
                                                                                                            ItemEntry133Entry itemEntry133Entry69 = new ItemEntry133Entry(
                                                                                                               MovementWeightProperty.SHA3_256
                                                                                                            );
                                                                                                            byte[] byteValueArray138 = new byte[]{
                                                                                                               64,
                                                                                                               39,
                                                                                                               66,
                                                                                                               -69,
                                                                                                               -1,
                                                                                                               -102,
                                                                                                               31,
                                                                                                               -102,
                                                                                                               111,
                                                                                                               -85,
                                                                                                               -99,
                                                                                                               -110,
                                                                                                               107,
                                                                                                               19,
                                                                                                               -92,
                                                                                                               107,
                                                                                                               65,
                                                                                                               78,
                                                                                                               65,
                                                                                                               12,
                                                                                                               82,
                                                                                                               125,
                                                                                                               -99,
                                                                                                               -57,
                                                                                                               127,
                                                                                                               22,
                                                                                                               27,
                                                                                                               -99,
                                                                                                               0,
                                                                                                               -115,
                                                                                                               -88,
                                                                                                               -58
                                                                                                            };
                                                                                                            byte[] byteValueArray139 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                               byteValueArray, byteValueArray138, coreByteArrayEntry
                                                                                                            );
                                                                                                            this.byteFlagArray62 = itemEntry133Entry69.resolveByteArray(
                                                                                                               byteValueArray139
                                                                                                            );
                                                                                                            value2 = (value2 ^ 0x176a61ab711076ddL)
                                                                                                               + 0x2d59add58647f251L;
                                                                                                         }
                                                                                                      } else {
                                                                                                         ItemEntry133Entry itemEntry133Entry70 = new ItemEntry133Entry(
                                                                                                            MovementWeightProperty.SHA3_256
                                                                                                         );
                                                                                                         byte[] byteValueArray140 = new byte[]{
                                                                                                            -34,
                                                                                                            84,
                                                                                                            -94,
                                                                                                            -10,
                                                                                                            -19,
                                                                                                            6,
                                                                                                            61,
                                                                                                            -28,
                                                                                                            75,
                                                                                                            105,
                                                                                                            -15,
                                                                                                            -85,
                                                                                                            109,
                                                                                                            -91,
                                                                                                            -32,
                                                                                                            125,
                                                                                                            120,
                                                                                                            -65,
                                                                                                            51,
                                                                                                            -70,
                                                                                                            -21,
                                                                                                            -39,
                                                                                                            59,
                                                                                                            -111,
                                                                                                            98,
                                                                                                            84,
                                                                                                            26,
                                                                                                            -44,
                                                                                                            -52,
                                                                                                            -26,
                                                                                                            119,
                                                                                                            -40
                                                                                                         };
                                                                                                         byte[] byteValueArray141 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                            byteValueArray, byteValueArray140, coreByteArrayEntry
                                                                                                         );
                                                                                                         this.byteFlagArray197 = itemEntry133Entry70.resolveByteArray(byteValueArray141);
                                                                                                         value2 = (value2 ^ 0x4e3f831a6327bc18L)
                                                                                                            - 0x33b3a2bdaa206609L;
                                                                                                      }
                                                                                                   } else {
                                                                                                      ItemEntry133Entry itemEntry133Entry71 = new ItemEntry133Entry(
                                                                                                         MovementWeightProperty.SHA3_256
                                                                                                      );
                                                                                                      byte[] byteValueArray142 = new byte[]{
                                                                                                         54,
                                                                                                         -71,
                                                                                                         6,
                                                                                                         57,
                                                                                                         117,
                                                                                                         -110,
                                                                                                         39,
                                                                                                         -20,
                                                                                                         45,
                                                                                                         -53,
                                                                                                         -37,
                                                                                                         -56,
                                                                                                         -96,
                                                                                                         -66,
                                                                                                         126,
                                                                                                         37,
                                                                                                         121,
                                                                                                         -10,
                                                                                                         -24,
                                                                                                         87,
                                                                                                         64,
                                                                                                         110,
                                                                                                         -19,
                                                                                                         117,
                                                                                                         -107,
                                                                                                         -58,
                                                                                                         100,
                                                                                                         -89,
                                                                                                         120,
                                                                                                         44,
                                                                                                         -67,
                                                                                                         -57
                                                                                                      };
                                                                                                      byte[] byteValueArray143 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                         byteValueArray, byteValueArray142, coreByteArrayEntry
                                                                                                      );
                                                                                                      this.byteFlagArray174 = itemEntry133Entry71.resolveByteArray(byteValueArray143);
                                                                                                      value2 = (value2 ^ 0x715ceefc8d3a33d7L)
                                                                                                         - 0x2adef38df88d3636L;
                                                                                                   }
                                                                                                } else {
                                                                                                   ItemEntry133Entry itemEntry133Entry72 = new ItemEntry133Entry(
                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                   );
                                                                                                   byte[] byteValueArray144 = new byte[]{
                                                                                                      46,
                                                                                                      16,
                                                                                                      -99,
                                                                                                      65,
                                                                                                      84,
                                                                                                      -87,
                                                                                                      91,
                                                                                                      106,
                                                                                                      -64,
                                                                                                      30,
                                                                                                      63,
                                                                                                      10,
                                                                                                      -8,
                                                                                                      -31,
                                                                                                      36,
                                                                                                      -111,
                                                                                                      -117,
                                                                                                      114,
                                                                                                      12,
                                                                                                      1,
                                                                                                      -123,
                                                                                                      -14,
                                                                                                      78,
                                                                                                      62,
                                                                                                      11,
                                                                                                      30,
                                                                                                      -80,
                                                                                                      87,
                                                                                                      -66,
                                                                                                      53,
                                                                                                      -63,
                                                                                                      125
                                                                                                   };
                                                                                                   byte[] byteValueArray145 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                      byteValueArray, byteValueArray144, coreByteArrayEntry
                                                                                                   );
                                                                                                   this.byteFlagArray7 = itemEntry133Entry72.resolveByteArray(byteValueArray145);
                                                                                                   value2 = (value2 ^ 0x465d85f9601486d3L)
                                                                                                      - 0x251401eef806b0afL;
                                                                                                }
                                                                                             } else {
                                                                                                ItemEntry133Entry itemEntry133Entry73 = new ItemEntry133Entry(
                                                                                                   MovementWeightProperty.SHA3_256
                                                                                                );
                                                                                                byte[] byteValueArray146 = new byte[]{
                                                                                                   -118,
                                                                                                   -12,
                                                                                                   -91,
                                                                                                   -31,
                                                                                                   5,
                                                                                                   -41,
                                                                                                   -65,
                                                                                                   15,
                                                                                                   68,
                                                                                                   -38,
                                                                                                   -33,
                                                                                                   9,
                                                                                                   -64,
                                                                                                   -51,
                                                                                                   -107,
                                                                                                   -7,
                                                                                                   -101,
                                                                                                   -106,
                                                                                                   125,
                                                                                                   127,
                                                                                                   93,
                                                                                                   29,
                                                                                                   -23,
                                                                                                   -110,
                                                                                                   -118,
                                                                                                   91,
                                                                                                   -44,
                                                                                                   70,
                                                                                                   104,
                                                                                                   -23,
                                                                                                   -95,
                                                                                                   92
                                                                                                };
                                                                                                byte[] byteValueArray147 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                   byteValueArray, byteValueArray146, coreByteArrayEntry
                                                                                                );
                                                                                                this.byteFlagArray93 = itemEntry133Entry73.resolveByteArray(byteValueArray147);
                                                                                                value2 = (value2 ^ 0x177515588f0293fbL) - 0x5f628f1044febbcL;
                                                                                             }
                                                                                          } else {
                                                                                             ItemEntry133Entry itemEntry133Entry74 = new ItemEntry133Entry(
                                                                                                MovementWeightProperty.SHA3_256
                                                                                             );
                                                                                             byte[] byteValueArray148 = new byte[]{
                                                                                                -70,
                                                                                                41,
                                                                                                -69,
                                                                                                -12,
                                                                                                65,
                                                                                                -14,
                                                                                                68,
                                                                                                26,
                                                                                                74,
                                                                                                113,
                                                                                                -34,
                                                                                                -128,
                                                                                                42,
                                                                                                9,
                                                                                                -80,
                                                                                                -5,
                                                                                                -15,
                                                                                                -6,
                                                                                                -117,
                                                                                                103,
                                                                                                -87,
                                                                                                56,
                                                                                                114,
                                                                                                93,
                                                                                                -25,
                                                                                                24,
                                                                                                -65,
                                                                                                -74,
                                                                                                90,
                                                                                                -15,
                                                                                                124,
                                                                                                -118
                                                                                             };
                                                                                             byte[] byteValueArray149 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                byteValueArray, byteValueArray148, coreByteArrayEntry
                                                                                             );
                                                                                             this.byteFlagArray42 = itemEntry133Entry74.resolveByteArray(byteValueArray149);
                                                                                             value2 = (value2 ^ -0x610469d741d76744L) - 0x4d910cd0518f869bL;
                                                                                          }
                                                                                       } else {
                                                                                          ItemEntry133Entry itemEntry133Entry75 = new ItemEntry133Entry(
                                                                                             MovementWeightProperty.SHA3_256
                                                                                          );
                                                                                          byte[] byteValueArray150 = new byte[]{
                                                                                             56,
                                                                                             -37,
                                                                                             25,
                                                                                             -80,
                                                                                             83,
                                                                                             118,
                                                                                             27,
                                                                                             121,
                                                                                             -51,
                                                                                             38,
                                                                                             10,
                                                                                             -24,
                                                                                             63,
                                                                                             -91,
                                                                                             -118,
                                                                                             43,
                                                                                             -9,
                                                                                             -45,
                                                                                             -77,
                                                                                             -120,
                                                                                             -118,
                                                                                             68,
                                                                                             106,
                                                                                             49,
                                                                                             118,
                                                                                             -36,
                                                                                             5,
                                                                                             55,
                                                                                             -49,
                                                                                             -21,
                                                                                             -35,
                                                                                             -19
                                                                                          };
                                                                                          byte[] byteValueArray151 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                             byteValueArray, byteValueArray150, coreByteArrayEntry
                                                                                          );
                                                                                          this.byteFlagArray30 = itemEntry133Entry75.resolveByteArray(byteValueArray151);
                                                                                          value2 = (value2 ^ -0x6af829305d1a8844L) + 0x2479da2f19d8462fL;
                                                                                       }
                                                                                    } else {
                                                                                       ItemEntry133Entry itemEntry133Entry76 = new ItemEntry133Entry(
                                                                                          MovementWeightProperty.SHA3_256
                                                                                       );
                                                                                       byte[] byteValueArray152 = new byte[]{
                                                                                          -94,
                                                                                          -118,
                                                                                          -60,
                                                                                          8,
                                                                                          -96,
                                                                                          33,
                                                                                          76,
                                                                                          108,
                                                                                          -123,
                                                                                          39,
                                                                                          14,
                                                                                          -19,
                                                                                          -13,
                                                                                          -14,
                                                                                          -66,
                                                                                          35,
                                                                                          -53,
                                                                                          -41,
                                                                                          -33,
                                                                                          114,
                                                                                          -13,
                                                                                          91,
                                                                                          -118,
                                                                                          114,
                                                                                          -114,
                                                                                          -86,
                                                                                          -122,
                                                                                          44,
                                                                                          33,
                                                                                          -72,
                                                                                          85,
                                                                                          -119
                                                                                       };
                                                                                       byte[] byteValueArray153 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                          byteValueArray, byteValueArray152, coreByteArrayEntry
                                                                                       );
                                                                                       this.byteFlagArray68 = itemEntry133Entry76.resolveByteArray(byteValueArray153);
                                                                                       value2 = (value2 ^ 0x2b51d10a5d6691deL) - 0x2f2b365400439488L;
                                                                                    }
                                                                                 } else {
                                                                                    ItemEntry133Entry itemEntry133Entry77 = new ItemEntry133Entry(
                                                                                       MovementWeightProperty.SHA3_256
                                                                                    );
                                                                                    byte[] byteValueArray154 = new byte[]{
                                                                                       -28,
                                                                                       96,
                                                                                       -124,
                                                                                       -124,
                                                                                       126,
                                                                                       81,
                                                                                       81,
                                                                                       64,
                                                                                       97,
                                                                                       124,
                                                                                       10,
                                                                                       -53,
                                                                                       -82,
                                                                                       -16,
                                                                                       -35,
                                                                                       -77,
                                                                                       -43,
                                                                                       5,
                                                                                       -36,
                                                                                       105,
                                                                                       -21,
                                                                                       -109,
                                                                                       -22,
                                                                                       -102,
                                                                                       -17,
                                                                                       75,
                                                                                       76,
                                                                                       105,
                                                                                       -109,
                                                                                       -95,
                                                                                       71,
                                                                                       17
                                                                                    };
                                                                                    byte[] byteValueArray155 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                       byteValueArray, byteValueArray154, coreByteArrayEntry
                                                                                    );
                                                                                    this.byteFlagArray34 = itemEntry133Entry77.resolveByteArray(byteValueArray155);
                                                                                    value2 = (value2 ^ -0xd1ef14889b6509aL) - 0x5c1f723f16c535dfL;
                                                                                 }
                                                                              } else {
                                                                                 ItemEntry133Entry itemEntry133Entry78 = new ItemEntry133Entry(
                                                                                    MovementWeightProperty.SHA3_256
                                                                                 );
                                                                                 byte[] byteValueArray156 = new byte[]{
                                                                                    -29,
                                                                                    80,
                                                                                    -41,
                                                                                    -128,
                                                                                    -121,
                                                                                    -11,
                                                                                    70,
                                                                                    99,
                                                                                    37,
                                                                                    97,
                                                                                    13,
                                                                                    60,
                                                                                    -2,
                                                                                    103,
                                                                                    -117,
                                                                                    -118,
                                                                                    -103,
                                                                                    -44,
                                                                                    -114,
                                                                                    -113,
                                                                                    -20,
                                                                                    -82,
                                                                                    -110,
                                                                                    -96,
                                                                                    87,
                                                                                    -43,
                                                                                    -52,
                                                                                    -110,
                                                                                    -16,
                                                                                    -38,
                                                                                    4,
                                                                                    79
                                                                                 };
                                                                                 byte[] byteValueArray157 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray156, coreByteArrayEntry);
                                                                                 this.byteFlagArray147 = itemEntry133Entry78.resolveByteArray(byteValueArray157);
                                                                                 value2 = (value2 ^ 0x6ca638acbeda4ebL) - 0x491c81537e5a2f59L;
                                                                              }
                                                                           } else {
                                                                              ItemEntry133Entry itemEntry133Entry79 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                              byte[] byteValueArray158 = new byte[]{
                                                                                 -28,
                                                                                 58,
                                                                                 -16,
                                                                                 82,
                                                                                 65,
                                                                                 -81,
                                                                                 -74,
                                                                                 -107,
                                                                                 94,
                                                                                 111,
                                                                                 75,
                                                                                 -29,
                                                                                 -58,
                                                                                 46,
                                                                                 55,
                                                                                 -122,
                                                                                 3,
                                                                                 -98,
                                                                                 93,
                                                                                 -90,
                                                                                 26,
                                                                                 33,
                                                                                 20,
                                                                                 81,
                                                                                 93,
                                                                                 -52,
                                                                                 52,
                                                                                 -22,
                                                                                 68,
                                                                                 -98,
                                                                                 123,
                                                                                 -66
                                                                              };
                                                                              byte[] byteValueArray159 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray158, coreByteArrayEntry);
                                                                              this.byteFlagArray167 = itemEntry133Entry79.resolveByteArray(byteValueArray159);
                                                                              value2 = (value2 ^ 0x7092c54d4d4b9247L) + 0x4651bdb129a2dd6aL;
                                                                           }
                                                                        } else {
                                                                           ItemEntry133Entry itemEntry133Entry80 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                           byte[] byteValueArray160 = new byte[]{
                                                                              49,
                                                                              -61,
                                                                              31,
                                                                              -21,
                                                                              43,
                                                                              -19,
                                                                              -127,
                                                                              78,
                                                                              -42,
                                                                              -75,
                                                                              49,
                                                                              95,
                                                                              -121,
                                                                              -66,
                                                                              -61,
                                                                              -66,
                                                                              51,
                                                                              114,
                                                                              -40,
                                                                              14,
                                                                              -44,
                                                                              15,
                                                                              10,
                                                                              -28,
                                                                              120,
                                                                              33,
                                                                              -8,
                                                                              -88,
                                                                              37,
                                                                              71,
                                                                              -52,
                                                                              24
                                                                           };
                                                                           byte[] byteValueArray161 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray160, coreByteArrayEntry);
                                                                           this.byteFlagArray188 = itemEntry133Entry80.resolveByteArray(byteValueArray161);
                                                                           value2 = (value2 ^ 0x2251619b04397d11L) - 0x70f88e281d463908L;
                                                                        }
                                                                     } else {
                                                                        ItemEntry133Entry itemEntry133Entry81 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                        byte[] byteValueArray162 = new byte[]{
                                                                           -118,
                                                                           -24,
                                                                           32,
                                                                           90,
                                                                           -97,
                                                                           54,
                                                                           91,
                                                                           -83,
                                                                           -43,
                                                                           -97,
                                                                           117,
                                                                           -128,
                                                                           -123,
                                                                           9,
                                                                           58,
                                                                           -74,
                                                                           4,
                                                                           -127,
                                                                           -105,
                                                                           -48,
                                                                           -39,
                                                                           -75,
                                                                           -33,
                                                                           -8,
                                                                           17,
                                                                           99,
                                                                           122,
                                                                           -55,
                                                                           2,
                                                                           14,
                                                                           66,
                                                                           34
                                                                        };
                                                                        byte[] byteValueArray163 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray162, coreByteArrayEntry);
                                                                        this.byteFlagArray85 = itemEntry133Entry81.resolveByteArray(byteValueArray163);
                                                                        value2 = (value2 ^ 0x6d32b5a3c4a0e3f0L) - 0x20aa80b7f6fca187L;
                                                                     }
                                                                  } else {
                                                                     ItemEntry133Entry itemEntry133Entry82 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                     byte[] byteValueArray164 = new byte[]{
                                                                        71,
                                                                        13,
                                                                        27,
                                                                        -39,
                                                                        -8,
                                                                        -31,
                                                                        56,
                                                                        33,
                                                                        -78,
                                                                        13,
                                                                        -25,
                                                                        -85,
                                                                        -51,
                                                                        -96,
                                                                        28,
                                                                        -42,
                                                                        -103,
                                                                        -55,
                                                                        8,
                                                                        89,
                                                                        -58,
                                                                        -119,
                                                                        117,
                                                                        -89,
                                                                        78,
                                                                        14,
                                                                        -69,
                                                                        -34,
                                                                        -111,
                                                                        -104,
                                                                        -48,
                                                                        -95
                                                                     };
                                                                     byte[] byteValueArray165 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray164, coreByteArrayEntry);
                                                                     this.byteFlagArray127 = itemEntry133Entry82.resolveByteArray(byteValueArray165);
                                                                     value2 = (value2 ^ 0x599e59313a362366L) - 0x31e892e97af802c2L;
                                                                  }
                                                               } else {
                                                                  this.byteFlagArray185 = byteValueArray;
                                                                  value2 = (value2 ^ 0x62cefba918965d9eL) - 0x6765d1aac125e888L;
                                                               }
                                                            } else {
                                                               ItemEntry133Entry itemEntry133Entry83 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                               byte[] byteValueArray166 = new byte[]{
                                                                  8,
                                                                  5,
                                                                  105,
                                                                  52,
                                                                  61,
                                                                  82,
                                                                  -87,
                                                                  -2,
                                                                  -100,
                                                                  -27,
                                                                  -34,
                                                                  -13,
                                                                  -81,
                                                                  65,
                                                                  -50,
                                                                  65,
                                                                  -67,
                                                                  -68,
                                                                  -108,
                                                                  -70,
                                                                  93,
                                                                  21,
                                                                  21,
                                                                  -55,
                                                                  -91,
                                                                  -108,
                                                                  -82,
                                                                  -88,
                                                                  -33,
                                                                  19,
                                                                  38,
                                                                  8
                                                               };
                                                               byte[] byteValueArray167 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray166, coreByteArrayEntry);
                                                               this.byteFlagArray203 = itemEntry133Entry83.resolveByteArray(byteValueArray167);
                                                               value2 = (value2 ^ 0x23bcfcce7a75e10L) + 0x6a79e4815b2a07a0L;
                                                            }
                                                         } else {
                                                            ItemEntry133Entry itemEntry133Entry84 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                            byte[] byteValueArray168 = new byte[]{
                                                               74,
                                                               6,
                                                               -31,
                                                               -18,
                                                               -35,
                                                               -65,
                                                               -69,
                                                               10,
                                                               -117,
                                                               -41,
                                                               -126,
                                                               107,
                                                               -83,
                                                               7,
                                                               54,
                                                               8,
                                                               -75,
                                                               -66,
                                                               73,
                                                               68,
                                                               93,
                                                               -59,
                                                               -8,
                                                               -105,
                                                               121,
                                                               99,
                                                               42,
                                                               16,
                                                               -87,
                                                               -87,
                                                               11,
                                                               -90
                                                            };
                                                            byte[] byteValueArray169 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray168, coreByteArrayEntry);
                                                            this.byteFlagArray140 = itemEntry133Entry84.resolveByteArray(byteValueArray169);
                                                            value2 = (value2 ^ 0x7379e3633a4c912aL) + 0xfb0649c3aa287abL;
                                                         }
                                                      } else {
                                                         ItemEntry133Entry itemEntry133Entry85 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                         byte[] byteValueArray170 = new byte[]{
                                                            -128,
                                                            -111,
                                                            -73,
                                                            102,
                                                            18,
                                                            -41,
                                                            -81,
                                                            48,
                                                            99,
                                                            -33,
                                                            -2,
                                                            93,
                                                            -28,
                                                            35,
                                                            -110,
                                                            -127,
                                                            61,
                                                            -55,
                                                            9,
                                                            51,
                                                            -18,
                                                            45,
                                                            46,
                                                            -20,
                                                            54,
                                                            56,
                                                            -108,
                                                            117,
                                                            -43,
                                                            98,
                                                            -86,
                                                            10
                                                         };
                                                         byte[] byteValueArray171 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray170, coreByteArrayEntry);
                                                         this.byteFlagArray76 = itemEntry133Entry85.resolveByteArray(byteValueArray171);
                                                         value2 = (value2 ^ 0x40e8453a857ea510L) + 0x300f07f7504c4dbL;
                                                      }
                                                   } else {
                                                      ItemEntry133Entry itemEntry133Entry86 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                      byte[] byteValueArray172 = new byte[]{
                                                         61,
                                                         16,
                                                         -19,
                                                         -36,
                                                         -102,
                                                         10,
                                                         7,
                                                         22,
                                                         -21,
                                                         67,
                                                         -5,
                                                         47,
                                                         126,
                                                         77,
                                                         53,
                                                         -33,
                                                         -95,
                                                         121,
                                                         92,
                                                         -97,
                                                         59,
                                                         -70,
                                                         1,
                                                         -17,
                                                         2,
                                                         22,
                                                         113,
                                                         -82,
                                                         -88,
                                                         40,
                                                         42,
                                                         49
                                                      };
                                                      byte[] byteValueArray173 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray172, coreByteArrayEntry);
                                                      this.byteFlagArray9 = itemEntry133Entry86.resolveByteArray(byteValueArray173);
                                                      value2 = (value2 ^ -0x6da01b8cb89604f1L) - 0x5c9d300850c44afaL;
                                                   }
                                                } else {
                                                   ItemEntry133Entry itemEntry133Entry87 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                   byte[] byteValueArray174 = new byte[]{
                                                      -117,
                                                      22,
                                                      69,
                                                      73,
                                                      34,
                                                      -44,
                                                      -125,
                                                      -80,
                                                      -20,
                                                      48,
                                                      -65,
                                                      16,
                                                      -91,
                                                      -85,
                                                      -91,
                                                      13,
                                                      10,
                                                      -76,
                                                      102,
                                                      55,
                                                      -6,
                                                      -33,
                                                      -55,
                                                      58,
                                                      -121,
                                                      -104,
                                                      1,
                                                      96,
                                                      121,
                                                      94,
                                                      -40,
                                                      58
                                                   };
                                                   byte[] byteValueArray175 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray174, coreByteArrayEntry);
                                                   this.byteFlagArray202 = itemEntry133Entry87.resolveByteArray(byteValueArray175);
                                                   value2 = (value2 ^ 0x79ff7e4c81b38290L) + 0x3c908896decc9a19L;
                                                }
                                             } else {
                                                ItemEntry133Entry itemEntry133Entry88 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                byte[] byteValueArray176 = new byte[]{
                                                   17,
                                                   -68,
                                                   67,
                                                   -80,
                                                   112,
                                                   -43,
                                                   93,
                                                   -104,
                                                   -22,
                                                   -81,
                                                   -87,
                                                   12,
                                                   124,
                                                   18,
                                                   -61,
                                                   49,
                                                   -117,
                                                   -52,
                                                   119,
                                                   -14,
                                                   -122,
                                                   78,
                                                   -67,
                                                   -45,
                                                   114,
                                                   -49,
                                                   -70,
                                                   -125,
                                                   -35,
                                                   -94,
                                                   118,
                                                   101
                                                };
                                                byte[] byteValueArray177 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray176, coreByteArrayEntry);
                                                this.byteFlagArray89 = itemEntry133Entry88.resolveByteArray(byteValueArray177);
                                                value2 = (value2 ^ 0x322d2243b030a133L) - 0x71b8ebeab339dd25L;
                                             }
                                          } else {
                                             ItemEntry133Entry itemEntry133Entry89 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                             byte[] byteValueArray178 = new byte[]{
                                                -105,
                                                -52,
                                                32,
                                                -22,
                                                21,
                                                -123,
                                                -113,
                                                1,
                                                -43,
                                                33,
                                                29,
                                                -13,
                                                114,
                                                -35,
                                                -102,
                                                -1,
                                                -65,
                                                19,
                                                86,
                                                -10,
                                                -112,
                                                58,
                                                4,
                                                -103,
                                                -2,
                                                -2,
                                                75,
                                                111,
                                                35,
                                                21,
                                                -26,
                                                -40
                                             };
                                             byte[] byteValueArray179 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray178, coreByteArrayEntry);
                                             this.byteFlagArray41 = itemEntry133Entry89.resolveByteArray(byteValueArray179);
                                             value2 = (value2 ^ 0x1370b01e04963bd9L) - 0x6ef745734af920dL;
                                          }
                                       } else {
                                          ItemEntry133Entry itemEntry133Entry90 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                          byte[] byteValueArray180 = new byte[]{
                                             -110,
                                             83,
                                             7,
                                             47,
                                             125,
                                             101,
                                             125,
                                             104,
                                             10,
                                             -6,
                                             -17,
                                             100,
                                             112,
                                             3,
                                             -51,
                                             -85,
                                             -23,
                                             -117,
                                             -61,
                                             61,
                                             -3,
                                             126,
                                             -63,
                                             19,
                                             75,
                                             -120,
                                             89,
                                             90,
                                             88,
                                             -41,
                                             -12,
                                             17
                                          };
                                          byte[] byteValueArray181 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray180, coreByteArrayEntry);
                                          this.byteFlagArray164 = itemEntry133Entry90.resolveByteArray(byteValueArray181);
                                          value2 = (value2 ^ -0x55010a4a13f16a4fL) + 0x64a3b3081e76b0ebL;
                                       }
                                    } else {
                                       ItemEntry133Entry itemEntry133Entry91 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                       byte[] byteValueArray182 = new byte[]{
                                          -50,
                                          -33,
                                          -103,
                                          30,
                                          79,
                                          -86,
                                          67,
                                          -127,
                                          -35,
                                          -27,
                                          -92,
                                          67,
                                          96,
                                          -90,
                                          -62,
                                          68,
                                          -1,
                                          112,
                                          -18,
                                          115,
                                          104,
                                          -85,
                                          -108,
                                          1,
                                          18,
                                          -37,
                                          -97,
                                          40,
                                          58,
                                          85,
                                          96,
                                          119
                                       };
                                       byte[] byteValueArray183 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray182, coreByteArrayEntry);
                                       this.byteFlagArray150 = itemEntry133Entry91.resolveByteArray(byteValueArray183);
                                       value2 = (value2 ^ -0x41893b4d8d611040L) - 0x419999a8e3ce5057L;
                                    }
                                 } else {
                                    ItemEntry133Entry itemEntry133Entry92 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                    byte[] byteValueArray184 = new byte[]{
                                       21,
                                       102,
                                       -71,
                                       -99,
                                       34,
                                       90,
                                       108,
                                       46,
                                       73,
                                       -113,
                                       -42,
                                       104,
                                       -39,
                                       -42,
                                       -65,
                                       25,
                                       -70,
                                       0,
                                       68,
                                       -97,
                                       4,
                                       -48,
                                       102,
                                       9,
                                       -67,
                                       37,
                                       -103,
                                       82,
                                       100,
                                       -124,
                                       -104,
                                       -19
                                    };
                                    byte[] byteValueArray185 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray184, coreByteArrayEntry);
                                    this.byteFlagArray193 = itemEntry133Entry92.resolveByteArray(byteValueArray185);
                                    value2 = (value2 ^ -0x4f1bdaadd92ff8f0L) - 0x601562dcda015d7dL;
                                 }
                              } else {
                                 ItemEntry133Entry itemEntry133Entry93 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                 byte[] byteValueArray186 = new byte[]{
                                    -83,
                                    -57,
                                    108,
                                    -21,
                                    106,
                                    9,
                                    -86,
                                    120,
                                    -56,
                                    82,
                                    -11,
                                    78,
                                    30,
                                    72,
                                    43,
                                    65,
                                    -19,
                                    -19,
                                    62,
                                    111,
                                    106,
                                    94,
                                    71,
                                    118,
                                    120,
                                    -95,
                                    73,
                                    -127,
                                    -120,
                                    90,
                                    53,
                                    29
                                 };
                                 byte[] byteValueArray187 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray186, coreByteArrayEntry);
                                 this.byteFlagArray39 = itemEntry133Entry93.resolveByteArray(byteValueArray187);
                                 value2 = (value2 ^ -0x5a6f626a3657546eL) - 0x45ef745dcc432ae0L;
                              }
                           } else {
                              ItemEntry133Entry itemEntry133Entry94 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                              byte[] byteValueArray188 = new byte[]{
                                 -37,
                                 -92,
                                 -4,
                                 -82,
                                 82,
                                 10,
                                 88,
                                 86,
                                 17,
                                 126,
                                 18,
                                 -122,
                                 61,
                                 -75,
                                 -50,
                                 14,
                                 94,
                                 -56,
                                 -32,
                                 49,
                                 26,
                                 95,
                                 -53,
                                 42,
                                 7,
                                 49,
                                 106,
                                 -96,
                                 -68,
                                 -102,
                                 -105,
                                 -73
                              };
                              byte[] byteValueArray189 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray188, coreByteArrayEntry);
                              this.byteFlagArray95 = itemEntry133Entry94.resolveByteArray(byteValueArray189);
                              value2 = (value2 ^ -0x3c878aad926a1270L) + 0x1c433d01a670e7a3L;
                           }
                        } else {
                           ItemEntry133Entry itemEntry133Entry95 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                           byte[] byteValueArray190 = new byte[]{
                              -38,
                              65,
                              -117,
                              93,
                              -119,
                              -40,
                              -102,
                              -42,
                              -70,
                              -24,
                              -6,
                              -13,
                              29,
                              17,
                              78,
                              -5,
                              104,
                              8,
                              9,
                              26,
                              124,
                              -96,
                              -29,
                              59,
                              -72,
                              -128,
                              0,
                              -31,
                              -35,
                              -119,
                              -93,
                              -64
                           };
                           byte[] byteValueArray191 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray190, coreByteArrayEntry);
                           this.byteFlagArray189 = itemEntry133Entry95.resolveByteArray(byteValueArray191);
                           value2 = (value2 ^ 0x299b211f02657888L) + 0x53ea8dff4ab43eb1L;
                        }
                     } else {
                        ItemEntry133Entry itemEntry133Entry96 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                        byte[] byteValueArray192 = new byte[]{
                           2,
                           117,
                           -37,
                           -27,
                           92,
                           -120,
                           -80,
                           -93,
                           61,
                           -101,
                           -32,
                           -110,
                           54,
                           -99,
                           -10,
                           47,
                           -124,
                           116,
                           -30,
                           55,
                           -121,
                           101,
                           69,
                           100,
                           17,
                           -18,
                           -107,
                           -30,
                           -68,
                           -10,
                           44,
                           31
                        };
                        byte[] byteValueArray193 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray192, coreByteArrayEntry);
                        this.byteFlagArray194 = itemEntry133Entry96.resolveByteArray(byteValueArray193);
                        value2 = (value2 ^ -0x3de240368939cfefL) + 0x3bd4619dad65cc83L;
                     }
                  } else {
                     ItemEntry133Entry itemEntry133Entry97 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                     byte[] byteValueArray194 = new byte[]{
                        122,
                        -31,
                        -120,
                        -77,
                        71,
                        -12,
                        -50,
                        -19,
                        -108,
                        52,
                        -96,
                        0,
                        124,
                        68,
                        99,
                        64,
                        -2,
                        24,
                        -104,
                        3,
                        30,
                        49,
                        -47,
                        -7,
                        -114,
                        52,
                        -75,
                        -36,
                        40,
                        18,
                        -102,
                        16
                     };
                     byte[] byteValueArray195 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray194, coreByteArrayEntry);
                     this.byteFlagArray201 = itemEntry133Entry97.resolveByteArray(byteValueArray195);
                     value2 = (value2 ^ 0xb66c763e7ee5effL) - 0x5cf792f67c50c5baL;
                  }
               } else {
                  ItemEntry133Entry itemEntry133Entry98 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                  byte[] byteValueArray196 = new byte[]{
                     -109,
                     97,
                     104,
                     -113,
                     34,
                     78,
                     27,
                     3,
                     81,
                     -87,
                     -51,
                     48,
                     104,
                     -124,
                     -26,
                     43,
                     -2,
                     -109,
                     -21,
                     -33,
                     -83,
                     -78,
                     -98,
                     125,
                     81,
                     -49,
                     -121,
                     125,
                     -116,
                     41,
                     46,
                     13
                  };
                  byte[] byteValueArray197 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray196, coreByteArrayEntry);
                  this.byteFlagArray80 = itemEntry133Entry98.resolveByteArray(byteValueArray197);
                  value2 = (value2 ^ 0xe8b6b381d6d5e12L) - 0x1d24e64a62229beL;
               }
            } else {
               ItemEntry133Entry itemEntry133Entry99 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
               byte[] byteValueArray198 = new byte[]{
                  24,
                  23,
                  -12,
                  52,
                  -122,
                  -10,
                  20,
                  -12,
                  -105,
                  -41,
                  -4,
                  71,
                  -128,
                  38,
                  17,
                  3,
                  -75,
                  -112,
                  -82,
                  24,
                  -52,
                  89,
                  -65,
                  107,
                  44,
                  -17,
                  18,
                  -29,
                  -53,
                  -25,
                  -110,
                  -91
               };
               byte[] byteValueArray199 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray198, coreByteArrayEntry);
               this.byteFlagArray14 = itemEntry133Entry99.resolveByteArray(byteValueArray199);
               value2 = (value2 ^ 0x39279845847f9b83L) + 0x103820843a6d7e43L;
            }
         } else {
            ItemEntry133Entry itemEntry133Entry100 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray200 = new byte[]{
               -50,
               -36,
               -113,
               -25,
               -62,
               24,
               123,
               -61,
               -66,
               -30,
               122,
               -24,
               51,
               34,
               -8,
               89,
               2,
               62,
               -121,
               124,
               -73,
               -84,
               86,
               43,
               -124,
               -71,
               -9,
               -8,
               127,
               -117,
               -103,
               -13
            };
            byte[] byteValueArray201 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray200, coreByteArrayEntry);
            this.byteFlagArray90 = itemEntry133Entry100.resolveByteArray(byteValueArray201);
            value2 = (value2 ^ -0x3fd86ce8dc661d4cL) - 0x52499b22794c9b1bL;
         }
      }
   }

   public void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
      list.add(this.resolveByteBuf(byteBuf));
   }

   private byte[] resolveByteArray(byte[] byteValueArray, byte[] byteValueArray2, byte[] byteValueArray3, int[] countArray8, byte[] byteValueArray4) {
      short shortValue = 24364;
      short shortValue2 = 17327;
      short shortValue3 = 7011;
      int count6 = byteValueArray3 != null ? byteValueArray3.length : 0;
      if (this.customByteArrayEntry == null || !this.customByteArrayEntry.matchesByteArray(byteValueArray4)) {
         this.customByteArrayEntry = new CustomByteArrayEntry(byteValueArray4);
      }

      byte[] byteValueArray5 = new byte[16];
      if (byteValueArray3 != null && count6 > 0) {
         int count7 = (count6 + 16 - 1) / 16;

         for (int index = 0; index < count7; index++) {
            int count8 = Math.min(16, count6 - index * 16);

            for (int index2 = 0; index2 < count8; index2++) {
               byteValueArray5[index2] ^= byteValueArray3[index * 16 + index2];
            }

            this.applyByteArray2(byteValueArray5);
         }
      }

      if (byteValueArray2.length > 0) {
         int count9 = (byteValueArray2.length + 16 - 1) / 16;

         for (int index3 = 0; index3 < count9; index3++) {
            int count10 = Math.min(16, byteValueArray2.length - index3 * 16);

            for (int index4 = 0; index4 < count10; index4++) {
               byteValueArray5[index4] ^= byteValueArray2[index3 * 16 + index4];
            }

            this.applyByteArray2(byteValueArray5);
         }
      }

      byte[] byteValueArray6 = new byte[16];
      long value = (long)count6 * 8L;
      long value2 = (long)byteValueArray2.length * 8L;

      for (int index5 = 7; index5 >= 0; index5--) {
         byteValueArray6[index5] = (byte)((int)(value & 255L));
         value >>>= 8;
         byteValueArray6[index5 + 8] = (byte)((int)(value2 & 255L));
         value2 >>>= 8;
      }

      for (int index6 = 0; index6 < 16; index6++) {
         byteValueArray5[index6] ^= byteValueArray6[index6];
      }

      this.applyByteArray2(byteValueArray5);
      byte[] byteValueArray7 = new byte[16];
      System.arraycopy(byteValueArray, 0, byteValueArray7, 0, byteValueArray.length);
      byteValueArray7[15] = 1;

      for (int index7 = 0; index7 < 16; index7++) {
         this.countArray4[index7] = byteValueArray7[index7] & 255;
      }

      int[] countArray9 = this.countArray4;
      this.applyIndexArray(countArray9, countArray8);

      for (int index8 = 0; index8 < 16; index8++) {
         this.byteFlagArray180[index8] = (byte)this.countArray4[index8];
      }

      byte[] byteValueArray8 = new byte[16];

      for (int index9 = 0; index9 < 16; index9++) {
         byteValueArray8[index9] = (byte)(byteValueArray5[index9] ^ this.byteFlagArray180[index9]);
      }

      return byteValueArray8;
   }

   private void applyByteArray(byte[] byteValueArray) {
      long value = 0x25de89723f37L;
      value = 0x6b66f8461d8eL ^ value;
      CoreByteArrayEntry coreByteArrayEntry = null;
      long value2 = 0x5c0b601d818def8bL;

      while (true) {
         int count7 = (int)(value2 % 102L);
         if (count7 == 43) {
            ItemEntry133Entry itemEntry133Entry = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray2 = new byte[]{
               -37,
               33,
               119,
               -15,
               40,
               81,
               -97,
               50,
               -120,
               107,
               116,
               33,
               102,
               -128,
               40,
               27,
               -84,
               -30,
               -28,
               51,
               20,
               103,
               -85,
               13,
               104,
               59,
               90,
               57,
               -106,
               -8,
               -104,
               56
            };
            byte[] byteValueArray3 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray2, coreByteArrayEntry);
            this.byteFlagArray24 = itemEntry133Entry.resolveByteArray(byteValueArray3);
            value2 = (value2 ^ -0x70122786a86dcbeaL) + 0x4ebda5825a1f240cL;
         } else if (count7 == 86) {
            ItemEntry133Entry itemEntry133Entry2 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray4 = new byte[]{
               125,
               122,
               110,
               -71,
               -53,
               -59,
               23,
               33,
               62,
               -28,
               -27,
               -73,
               -44,
               -19,
               91,
               -42,
               -53,
               28,
               99,
               27,
               32,
               -22,
               87,
               -126,
               -66,
               -55,
               122,
               55,
               19,
               -119,
               -88,
               -25
            };
            byte[] byteValueArray5 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray4, coreByteArrayEntry);
            this.byteFlagArray59 = itemEntry133Entry2.resolveByteArray(byteValueArray5);
            value2 = (value2 ^ -0x3ba13a11a716f21eL) + 0x41b0755fbe68b281L;
         } else if (count7 == 52) {
            ItemEntry133Entry itemEntry133Entry3 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray6 = new byte[]{
               120,
               120,
               38,
               55,
               -36,
               -18,
               -99,
               -48,
               35,
               -125,
               15,
               -55,
               47,
               119,
               -61,
               -116,
               -127,
               -3,
               -42,
               -8,
               -114,
               118,
               -119,
               -16,
               88,
               15,
               109,
               91,
               123,
               109,
               -1,
               44
            };
            byte[] byteValueArray7 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray6, coreByteArrayEntry);
            this.byteFlagArray8 = itemEntry133Entry3.resolveByteArray(byteValueArray7);
            value2 = (value2 ^ -0x4268c0ffec91c805L) + 0x54b3481cdad2630cL;
         } else if (count7 == 79) {
            ItemEntry133Entry itemEntry133Entry4 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray8 = new byte[]{
               -50, 83, -110, 126, -89, -88, 29, 88, 106, 87, 110, -16, 92, 28, 22, 33, 82, 29, 21, 16, 49, 55, 114, 65, 66, 15, 74, -38, 22, 126, 127, 19
            };
            byte[] byteValueArray9 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray8, coreByteArrayEntry);
            this.byteFlagArray165 = itemEntry133Entry4.resolveByteArray(byteValueArray9);
            value2 = (value2 ^ 0x9ac00aa098fc122L) + 0x22fadd695bca5915L;
         } else if (count7 == 82) {
            ItemEntry133Entry itemEntry133Entry5 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray10 = new byte[]{
               78, 2, 68, 43, 78, -102, 33, 46, -54, 84, -128, -5, -93, -103, 105, -49, 44, -19, 13, 34, -112, 84, -98, -37, -93, -99, -84, 0, -33, -68, 4, 66
            };
            byte[] byteValueArray11 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray10, coreByteArrayEntry);
            this.byteFlagArray = itemEntry133Entry5.resolveByteArray(byteValueArray11);
            value2 = (value2 ^ 0x50b6d1dbe07bd440L) - 0x1186b7205674089fL;
         } else if (count7 == 69) {
            ItemEntry133Entry itemEntry133Entry6 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray12 = new byte[]{
               -71,
               95,
               -15,
               73,
               32,
               -119,
               77,
               -120,
               -18,
               -28,
               24,
               -103,
               -39,
               -117,
               16,
               56,
               66,
               56,
               -126,
               -13,
               104,
               34,
               -41,
               -19,
               2,
               -72,
               35,
               -33,
               -54,
               46,
               54,
               16
            };
            byte[] byteValueArray13 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray12, coreByteArrayEntry);
            this.byteFlagArray160 = itemEntry133Entry6.resolveByteArray(byteValueArray13);
            value2 = (value2 ^ -0x25b88a80fcf869acL) + 0x6c473990962db3adL;
         } else if (count7 == 88) {
            ItemEntry133Entry itemEntry133Entry7 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray14 = new byte[]{
               97, -9, 46, -2, -3, -3, -81, 53, 111, -125, -122, 115, 35, -43, -22, 37, -90, 8, 19, -35, 3, 22, 5, 17, -59, -66, -89, 51, -38, -93, 23, -21
            };
            byte[] byteValueArray15 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray14, coreByteArrayEntry);
            this.byteFlagArray136 = itemEntry133Entry7.resolveByteArray(byteValueArray15);
            value2 = (value2 ^ -0x36dabfe2e0e76a5aL) + 0x62bc3d93198bee41L;
         } else if (count7 == 15) {
            ItemEntry133Entry itemEntry133Entry8 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray16 = new byte[]{
               -45,
               -112,
               -30,
               -69,
               43,
               34,
               34,
               -12,
               -34,
               -70,
               -94,
               8,
               -49,
               -73,
               3,
               -85,
               120,
               -30,
               -11,
               -20,
               101,
               35,
               -85,
               -95,
               -95,
               -95,
               -70,
               -112,
               -122,
               -2,
               -13,
               -10
            };
            byte[] byteValueArray17 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray16, coreByteArrayEntry);
            this.byteFlagArray58 = itemEntry133Entry8.resolveByteArray(byteValueArray17);
            value2 = (value2 ^ -0x35b34fc5109469e1L) + 0x461a71ad9cbd597dL;
         } else if (count7 == 48) {
            ItemEntry133Entry itemEntry133Entry9 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray18 = new byte[]{
               -73,
               -127,
               91,
               -81,
               98,
               -80,
               -114,
               114,
               -54,
               24,
               18,
               -119,
               67,
               9,
               17,
               -77,
               32,
               42,
               103,
               -88,
               -26,
               -47,
               -101,
               -86,
               -71,
               -68,
               -18,
               -101,
               106,
               -44,
               -122,
               121
            };
            byte[] byteValueArray19 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray18, coreByteArrayEntry);
            this.byteFlagArray23 = itemEntry133Entry9.resolveByteArray(byteValueArray19);
            value2 = (value2 ^ -0x6744edc8a70f559bL) - 0x5af5374082849365L;
         } else if (count7 == 28) {
            ItemEntry133Entry itemEntry133Entry10 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray20 = new byte[]{
               85,
               117,
               119,
               39,
               -108,
               89,
               100,
               16,
               -16,
               102,
               115,
               28,
               69,
               87,
               70,
               28,
               80,
               67,
               13,
               -50,
               -94,
               92,
               -120,
               -37,
               -76,
               -68,
               -104,
               -95,
               -124,
               -126,
               -57,
               73
            };
            byte[] byteValueArray21 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray20, coreByteArrayEntry);
            this.byteFlagArray75 = itemEntry133Entry10.resolveByteArray(byteValueArray21);
            value2 = (value2 ^ -0x7acfbb1d6bc24f4eL) + 0x179e2cd5982f7829L;
         } else if (count7 == 58) {
            ItemEntry133Entry itemEntry133Entry11 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray22 = new byte[]{
               9, -99, 53, -38, -60, -47, -53, -78, 59, -39, 34, -58, 13, -10, 49, 53, 28, 89, 30, 94, -80, -118, 51, 54, 47, 24, -117, 64, -92, -121, -13, 109
            };
            byte[] byteValueArray23 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray22, coreByteArrayEntry);
            this.byteFlagArray64 = itemEntry133Entry11.resolveByteArray(byteValueArray23);
            value2 = (value2 ^ -0x333f4faac2e2f575L) - 0x38442b05a0e4befbL;
         } else if (count7 == 81) {
            ItemEntry133Entry itemEntry133Entry12 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray24 = new byte[]{
               -97,
               54,
               -20,
               -52,
               116,
               86,
               -88,
               20,
               97,
               -51,
               -45,
               -19,
               -111,
               -10,
               12,
               -36,
               11,
               64,
               30,
               -14,
               -101,
               -90,
               -99,
               -85,
               115,
               89,
               112,
               -1,
               -73,
               -68,
               114,
               54
            };
            byte[] byteValueArray25 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray24, coreByteArrayEntry);
            this.byteFlagArray38 = itemEntry133Entry12.resolveByteArray(byteValueArray25);
            value2 = (value2 ^ -0x1d9c95a0eabc2bfeL) - 0x44e2a77161f5ae65L;
         } else if (count7 == 6) {
            ItemEntry133Entry itemEntry133Entry13 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray26 = new byte[]{
               36,
               89,
               114,
               -58,
               15,
               -123,
               -117,
               40,
               31,
               -125,
               -2,
               -12,
               -4,
               70,
               -10,
               -83,
               -29,
               53,
               -63,
               55,
               -16,
               67,
               -17,
               116,
               28,
               -62,
               2,
               76,
               -49,
               -54,
               89,
               -103
            };
            byte[] byteValueArray27 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray26, coreByteArrayEntry);
            this.byteFlagArray11 = itemEntry133Entry13.resolveByteArray(byteValueArray27);
            value2 = (value2 ^ -0x7a563f19ce43f4ccL) - 0x6556ea4b1aed8f1bL;
         } else if (count7 == 54) {
            ItemEntry133Entry itemEntry133Entry14 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray28 = new byte[]{
               -9,
               92,
               -15,
               56,
               -123,
               -102,
               -58,
               -56,
               -120,
               79,
               32,
               110,
               106,
               -11,
               -11,
               93,
               -36,
               62,
               -127,
               100,
               64,
               -64,
               96,
               95,
               12,
               -13,
               108,
               -104,
               -100,
               22,
               -75,
               -15
            };
            byte[] byteValueArray29 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray28, coreByteArrayEntry);
            this.byteFlagArray113 = itemEntry133Entry14.resolveByteArray(byteValueArray29);
            value2 = (value2 ^ 0x7379fadb39f0c70dL) + 0x74cc1391ff5cf183L;
         } else if (count7 == 66) {
            ItemEntry133Entry itemEntry133Entry15 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray30 = new byte[]{
               34,
               127,
               -57,
               -34,
               -86,
               -115,
               120,
               -27,
               72,
               -75,
               -24,
               -57,
               67,
               -127,
               -91,
               61,
               59,
               -20,
               -10,
               -6,
               66,
               -100,
               112,
               -109,
               -11,
               -33,
               36,
               -114,
               85,
               8,
               -15,
               2
            };
            byte[] byteValueArray31 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray30, coreByteArrayEntry);
            this.byteFlagArray99 = itemEntry133Entry15.resolveByteArray(byteValueArray31);
            value2 = (value2 ^ -0x5bdb47bde542402L) + 0x59736f4e45e04039L;
         } else if (count7 == 0) {
            ItemEntry133Entry itemEntry133Entry16 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray32 = new byte[]{
               -41,
               74,
               -71,
               50,
               -79,
               121,
               -114,
               -96,
               -70,
               10,
               124,
               -104,
               -27,
               57,
               31,
               -60,
               -67,
               76,
               -57,
               -19,
               86,
               48,
               9,
               5,
               -99,
               62,
               72,
               -1,
               -110,
               -85,
               61,
               -17
            };
            byte[] byteValueArray33 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray32, coreByteArrayEntry);
            this.byteFlagArray146 = itemEntry133Entry16.resolveByteArray(byteValueArray33);
            value2 = (value2 ^ -0x65ba23c80d41cc48L) - 0x7e95fbd2918e0fe1L;
         } else if (count7 == 17) {
            ItemEntry133Entry itemEntry133Entry17 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray34 = new byte[]{
               88, 93, 109, -72, -96, 3, 44, -10, -48, -77, 93, 98, -36, 115, 113, -19, 100, 50, 43, 24, -17, 54, -22, 127, -51, -66, 46, 55, 84, -79, 44, -111
            };
            byte[] byteValueArray35 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray34, coreByteArrayEntry);
            this.byteFlagArray191 = itemEntry133Entry17.resolveByteArray(byteValueArray35);
            value2 = (value2 ^ 0x6466e6a1d5b70716L) - 0xdc178f9203d1abbL;
         } else if (count7 == 76) {
            ItemEntry133Entry itemEntry133Entry18 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray36 = new byte[]{
               -24, -87, 43, 10, 83, -70, 83, 38, -61, 91, -12, -28, -4, 91, -73, -37, 27, 45, -106, 109, 75, 122, -115, 31, 11, 18, 69, 94, 73, -10, -12, -104
            };
            byte[] byteValueArray37 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray36, coreByteArrayEntry);
            this.byteFlagArray61 = itemEntry133Entry18.resolveByteArray(byteValueArray37);
            value2 = (value2 ^ 0x45a2b21f305c8ffcL) - 0x4753debb419c9306L;
         } else if (count7 == 100) {
            ItemEntry133Entry itemEntry133Entry19 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray38 = new byte[]{
               17,
               -26,
               97,
               -5,
               -9,
               -11,
               116,
               116,
               77,
               -104,
               38,
               -86,
               -34,
               -61,
               -106,
               71,
               105,
               -61,
               76,
               -98,
               35,
               -117,
               90,
               -75,
               92,
               -63,
               110,
               -66,
               19,
               53,
               -116,
               -78
            };
            byte[] byteValueArray39 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray38, coreByteArrayEntry);
            this.byteFlagArray119 = itemEntry133Entry19.resolveByteArray(byteValueArray39);
            value2 = (value2 ^ 0x25d179ae9946cd6dL) + 0xb9db4c9f56bb0cbL;
         } else if (count7 == 40) {
            ItemEntry133Entry itemEntry133Entry20 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray40 = new byte[]{
               -90,
               -99,
               67,
               -57,
               -16,
               -95,
               16,
               -22,
               52,
               -117,
               -105,
               50,
               -9,
               89,
               -54,
               -36,
               -73,
               122,
               114,
               -16,
               -43,
               -12,
               50,
               66,
               122,
               120,
               -22,
               -128,
               39,
               -95,
               -102,
               45
            };
            byte[] byteValueArray41 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray40, coreByteArrayEntry);
            this.byteFlagArray149 = itemEntry133Entry20.resolveByteArray(byteValueArray41);
            value2 = (value2 ^ 0xeaee7eb43a3508bL) - 0x2b592a5d95dd300cL;
         } else if (count7 == 60) {
            ItemEntry133Entry itemEntry133Entry21 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray42 = new byte[]{
               -110,
               14,
               114,
               -96,
               15,
               49,
               -106,
               0,
               62,
               80,
               -18,
               90,
               74,
               110,
               117,
               20,
               -18,
               -124,
               106,
               50,
               -56,
               112,
               -24,
               -67,
               16,
               4,
               35,
               19,
               100,
               -52,
               -112,
               -39
            };
            byte[] byteValueArray43 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray42, coreByteArrayEntry);
            this.byteFlagArray198 = itemEntry133Entry21.resolveByteArray(byteValueArray43);
            value2 = (value2 ^ 0x2c1c983ed6447a83L) - 0x46f313442c7bbd37L;
         } else if (count7 == 32) {
            ItemEntry133Entry itemEntry133Entry22 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray44 = new byte[]{
               -30,
               -39,
               86,
               -66,
               116,
               -125,
               37,
               51,
               47,
               -23,
               5,
               -96,
               57,
               -102,
               38,
               -10,
               -48,
               -18,
               124,
               -14,
               -109,
               34,
               -38,
               -109,
               4,
               124,
               -14,
               -20,
               -43,
               -9,
               -43,
               -35
            };
            byte[] byteValueArray45 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray44, coreByteArrayEntry);
            this.byteFlagArray128 = itemEntry133Entry22.resolveByteArray(byteValueArray45);
            value2 = (value2 ^ 0x18e6a0d713d654eL) - 0x56efaa895914250aL;
         } else if (count7 == 49) {
            ItemEntry133Entry itemEntry133Entry23 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray46 = new byte[]{
               -104,
               -116,
               -26,
               21,
               -62,
               -80,
               97,
               -115,
               58,
               34,
               25,
               5,
               -108,
               29,
               73,
               -78,
               -117,
               126,
               -87,
               -128,
               -15,
               73,
               28,
               -86,
               -40,
               16,
               -35,
               -16,
               67,
               13,
               35,
               118
            };
            byte[] byteValueArray47 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray46, coreByteArrayEntry);
            this.byteFlagArray200 = itemEntry133Entry23.resolveByteArray(byteValueArray47);
            value2 = (value2 ^ 0x430cedb542cdbd47L) + 0x29558d569c4570f2L;
         } else if (count7 == 85) {
            ItemEntry133Entry itemEntry133Entry24 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray48 = new byte[]{
               -95, -58, 1, -36, 4, 123, 33, 105, 3, -11, 99, 87, -25, 30, -13, -60, 21, -21, 26, 78, -101, -25, -35, 84, -122, 40, -124, 81, 76, -55, -36, 74
            };
            byte[] byteValueArray49 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray48, coreByteArrayEntry);
            this.byteFlagArray65 = itemEntry133Entry24.resolveByteArray(byteValueArray49);
            value2 = (value2 ^ -0x480f02ba7b51776eL) + 0x728018b6301a7e77L;
         } else if (count7 == 5) {
            ItemEntry133Entry itemEntry133Entry25 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray50 = new byte[]{
               46,
               30,
               -81,
               114,
               -65,
               -57,
               110,
               65,
               -4,
               92,
               105,
               97,
               18,
               -58,
               125,
               110,
               1,
               63,
               -31,
               -84,
               109,
               -24,
               -68,
               18,
               115,
               -68,
               96,
               -1,
               -112,
               23,
               47,
               -102
            };
            byte[] byteValueArray51 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray50, coreByteArrayEntry);
            this.byteFlagArray87 = itemEntry133Entry25.resolveByteArray(byteValueArray51);
            value2 = (value2 ^ 0x70da17f48dcac240L) - 0x1a9d008f841230d0L;
         } else if (count7 == 36) {
            ItemEntry133Entry itemEntry133Entry26 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray52 = new byte[]{
               -45, -30, -105, 49, -87, 80, 1, 45, 0, -120, 122, -61, 80, 7, 46, 77, -69, 70, -3, -89, -6, 8, 33, 118, 16, 83, -85, 79, -20, 45, -102, -118
            };
            byte[] byteValueArray53 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray52, coreByteArrayEntry);
            this.byteFlagArray51 = itemEntry133Entry26.resolveByteArray(byteValueArray53);
            value2 = (value2 ^ 0x376cf9097af3955cL) - 0x36de4be180782efeL;
         } else if (count7 == 4) {
            ItemEntry133Entry itemEntry133Entry27 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray54 = new byte[]{
               -11, 43, -30, -65, 2, -71, 43, -60, 110, 94, 75, 86, 1, 113, -13, 1, 75, 23, 107, 40, -13, -44, 124, -87, 86, 25, -78, 11, -78, 61, 105, 86
            };
            byte[] byteValueArray55 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray54, coreByteArrayEntry);
            this.byteFlagArray122 = itemEntry133Entry27.resolveByteArray(byteValueArray55);
            value2 = (value2 ^ 0x1676e46acf5246fL) - 0x3111f32aa1746385L;
         } else if (count7 == 13) {
            ItemEntry133Entry itemEntry133Entry28 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray56 = new byte[]{
               110, 113, 91, 71, 20, 117, -116, 99, 41, 65, -40, 96, 61, 53, 47, -80, -96, 51, -52, -56, -38, -58, -93, -19, 54, -22, -11, 0, -98, 63, -67, -94
            };
            byte[] byteValueArray57 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray56, coreByteArrayEntry);
            this.byteFlagArray29 = itemEntry133Entry28.resolveByteArray(byteValueArray57);
            value2 = (value2 ^ -0xd7e7bcf66b13075L) - 0x2901e6cac02f630fL;
         } else if (count7 == 22) {
            ItemEntry133Entry itemEntry133Entry29 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray58 = new byte[]{
               61,
               -57,
               -106,
               116,
               113,
               -123,
               -54,
               -18,
               46,
               104,
               -71,
               88,
               44,
               84,
               2,
               35,
               111,
               -50,
               -91,
               -52,
               -119,
               21,
               -85,
               -14,
               -46,
               66,
               -17,
               -86,
               113,
               -13,
               -122,
               -123
            };
            byte[] byteValueArray59 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray58, coreByteArrayEntry);
            this.byteFlagArray21 = itemEntry133Entry29.resolveByteArray(byteValueArray59);
            value2 = (value2 ^ 0x1900bf4c1b85fcccL) + 0x4e90a27e5a9797fdL;
         } else if (count7 == 95) {
            ItemEntry133Entry itemEntry133Entry30 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray60 = new byte[]{
               77,
               -73,
               80,
               -112,
               -64,
               -40,
               12,
               -128,
               51,
               -108,
               93,
               1,
               36,
               115,
               -65,
               31,
               114,
               -109,
               8,
               -30,
               57,
               43,
               -98,
               -127,
               88,
               94,
               -46,
               119,
               -60,
               26,
               113,
               -35
            };
            byte[] byteValueArray61 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray60, coreByteArrayEntry);
            this.byteFlagArray6 = itemEntry133Entry30.resolveByteArray(byteValueArray61);
            value2 = (value2 ^ 0x384a25da632a858dL) + 0x2fa0a2ee36244087L;
         } else if (count7 == 30) {
            ItemEntry133Entry itemEntry133Entry31 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray62 = new byte[]{
               -87, -112, 68, 120, -35, 123, 111, 79, 124, 89, -43, -91, -6, -95, 69, -107, 20, -59, -90, -53, 35, -93, 62, 34, -36, 77, 19, -64, 28, 6, 36, 8
            };
            byte[] byteValueArray63 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray62, coreByteArrayEntry);
            this.byteFlagArray40 = itemEntry133Entry31.resolveByteArray(byteValueArray63);
            value2 = (value2 ^ 0x1b8825776195f3aeL) + 0x9d01793c9bd23dfL;
         } else if (count7 == 45) {
            ItemEntry133Entry itemEntry133Entry32 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray64 = new byte[]{
               85,
               -31,
               -32,
               -41,
               -83,
               -89,
               -107,
               -27,
               118,
               -8,
               82,
               109,
               80,
               -17,
               -18,
               -64,
               -77,
               37,
               119,
               5,
               -116,
               -88,
               -90,
               -121,
               47,
               -108,
               33,
               -111,
               36,
               67,
               26,
               80
            };
            byte[] byteValueArray65 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray64, coreByteArrayEntry);
            this.byteFlagArray192 = itemEntry133Entry32.resolveByteArray(byteValueArray65);
            value2 = (value2 ^ -0x1278be79bed9e382L) + 0x73f8e4ff78cf68c4L;
         } else if (count7 == 8) {
            ItemEntry133Entry itemEntry133Entry33 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray66 = new byte[]{
               95,
               -113,
               13,
               57,
               -76,
               -35,
               71,
               -83,
               -42,
               -127,
               73,
               -45,
               -103,
               115,
               69,
               18,
               -30,
               85,
               -9,
               109,
               -73,
               113,
               69,
               51,
               68,
               109,
               -96,
               120,
               -7,
               111,
               91,
               66
            };
            byte[] byteValueArray67 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray66, coreByteArrayEntry);
            this.byteFlagArray172 = itemEntry133Entry33.resolveByteArray(byteValueArray67);
            value2 = (value2 ^ -0x71c0e1256f4ed371L) - 0x615f9367e326e800L;
         } else if (count7 == 98) {
            ItemEntry133Entry itemEntry133Entry34 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray68 = new byte[]{
               24,
               60,
               -107,
               109,
               43,
               -24,
               -50,
               -26,
               -51,
               -109,
               7,
               -64,
               121,
               25,
               19,
               -45,
               102,
               -55,
               45,
               -5,
               106,
               -15,
               53,
               -16,
               15,
               60,
               105,
               -63,
               124,
               -32,
               -39,
               -119
            };
            byte[] byteValueArray69 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray68, coreByteArrayEntry);
            this.byteFlagArray156 = itemEntry133Entry34.resolveByteArray(byteValueArray69);
            value2 = (value2 ^ -0x296e9ed5c1bab98fL) + 0x28d9476edb30903dL;
         } else if (count7 == 91) {
            ItemEntry133Entry itemEntry133Entry35 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray70 = new byte[]{
               18, 47, 19, 18, -16, 7, 61, -90, 125, -8, 7, -38, 86, 23, 124, 4, 109, 96, 17, -39, 94, -111, 117, 95, 62, 49, 9, -127, -111, 66, 75, -67
            };
            byte[] byteValueArray71 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray70, coreByteArrayEntry);
            this.byteFlagArray98 = itemEntry133Entry35.resolveByteArray(byteValueArray71);
            value2 = (value2 ^ 0x61fe07fbbd27984aL) - 0x24a3a9b22f39c6cbL;
         } else if (count7 == 33) {
            ItemEntry133Entry itemEntry133Entry36 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray72 = new byte[]{
               7, -99, -14, -29, 76, 36, -63, 69, -22, 9, 40, -38, -90, -33, 67, 41, -1, -55, -93, -75, 91, 19, 69, -4, -43, 108, 106, -112, -127, 47, 20, -15
            };
            byte[] byteValueArray73 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray72, coreByteArrayEntry);
            this.byteFlagArray199 = itemEntry133Entry36.resolveByteArray(byteValueArray73);
            value2 = (value2 ^ 0x68f0f443bf1f3013L) - 0x680e5d5f71b259faL;
         } else if (count7 == 14) {
            ItemEntry133Entry itemEntry133Entry37 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray74 = new byte[]{
               26,
               -34,
               96,
               -81,
               108,
               15,
               -45,
               38,
               -99,
               -118,
               63,
               51,
               35,
               108,
               37,
               -106,
               56,
               -72,
               -127,
               73,
               -18,
               55,
               -48,
               -101,
               81,
               -118,
               -13,
               -90,
               41,
               16,
               25,
               0
            };
            byte[] byteValueArray75 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray74, coreByteArrayEntry);
            this.byteFlagArray158 = itemEntry133Entry37.resolveByteArray(byteValueArray75);
            value2 = (value2 ^ -0xef9efc2ac85e26L) + 0x5b4492ae6ee43873L;
         } else if (count7 == 71) {
            ItemEntry133Entry itemEntry133Entry38 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray76 = new byte[]{
               -122, 43, -72, -56, -86, 16, -122, 26, 96, -98, 29, -61, 27, 74, 54, 80, 65, 69, -58, -41, 1, -75, 67, 108, 64, 46, 48, 42, -49, -124, 103, -87
            };
            byte[] byteValueArray77 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray76, coreByteArrayEntry);
            this.byteFlagArray81 = itemEntry133Entry38.resolveByteArray(byteValueArray77);
            value2 = (value2 ^ -0x4ff52e50aaafe4e7L) - 0x498b7d731523a47aL;
         } else if (count7 == 75) {
            ItemEntry133Entry itemEntry133Entry39 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray78 = new byte[]{
               15,
               114,
               -3,
               -4,
               70,
               -118,
               102,
               -57,
               104,
               -109,
               69,
               -88,
               54,
               111,
               43,
               9,
               -109,
               39,
               -118,
               16,
               81,
               -24,
               -112,
               35,
               71,
               -31,
               -13,
               -106,
               -72,
               7,
               57,
               115
            };
            byte[] byteValueArray79 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray78, coreByteArrayEntry);
            this.byteFlagArray157 = itemEntry133Entry39.resolveByteArray(byteValueArray79);
            value2 = (value2 ^ -0x1280a9108e95d22bL) - 0x4e5ea2885a03cffaL;
         } else if (count7 == 26) {
            ItemEntry133Entry itemEntry133Entry40 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray80 = new byte[]{
               118,
               -107,
               25,
               -113,
               -75,
               119,
               56,
               -37,
               20,
               79,
               3,
               -39,
               116,
               -66,
               57,
               -72,
               -77,
               119,
               -101,
               -117,
               54,
               55,
               -105,
               86,
               77,
               -92,
               -109,
               117,
               -7,
               63,
               -65,
               95
            };
            byte[] byteValueArray81 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray80, coreByteArrayEntry);
            this.byteFlagArray148 = itemEntry133Entry40.resolveByteArray(byteValueArray81);
            value2 = (value2 ^ 0x43dd27db6ea93bdaL) - 0x3b7ea6264d7c6132L;
         } else if (count7 == 61) {
            ItemEntry133Entry itemEntry133Entry41 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray82 = new byte[]{
               76,
               -36,
               -14,
               85,
               103,
               13,
               -90,
               84,
               52,
               120,
               7,
               18,
               -11,
               61,
               104,
               35,
               -112,
               115,
               -51,
               -117,
               30,
               -10,
               -20,
               -72,
               10,
               -14,
               -25,
               -40,
               -23,
               124,
               -98,
               100
            };
            byte[] byteValueArray83 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray82, coreByteArrayEntry);
            this.byteFlagArray179 = itemEntry133Entry41.resolveByteArray(byteValueArray83);
            value2 = (value2 ^ -0x1864ef05d1c8479eL) + 0x39415f8e273f601aL;
         } else if (count7 == 62) {
            ItemEntry133Entry itemEntry133Entry42 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray84 = new byte[]{
               -85,
               114,
               26,
               -64,
               -40,
               -114,
               -89,
               -46,
               -114,
               83,
               -74,
               34,
               -10,
               76,
               45,
               46,
               -120,
               9,
               -74,
               121,
               17,
               117,
               6,
               -62,
               114,
               -38,
               39,
               -104,
               -31,
               110,
               -28,
               -48
            };
            byte[] byteValueArray85 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray84, coreByteArrayEntry);
            this.byteFlagArray169 = itemEntry133Entry42.resolveByteArray(byteValueArray85);
            value2 = (value2 ^ 0xc501bdb1a20cdbaL) + 0x23139b67d0cb537bL;
         } else if (count7 == 18) {
            ItemEntry133Entry itemEntry133Entry43 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray86 = new byte[]{
               9, 37, 19, 13, -79, 116, 70, -123, 88, 126, 4, 91, 14, 102, 96, 90, -56, -5, 26, 90, -66, -121, -107, -68, 22, 120, -98, 16, -98, -69, 37, -58
            };
            byte[] byteValueArray87 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray86, coreByteArrayEntry);
            this.byteFlagArray108 = itemEntry133Entry43.resolveByteArray(byteValueArray87);
            value2 = (value2 ^ 0x5c21fb7f03646cf0L) + 0x1f715e15ebe671cdL;
         } else if (count7 == 70) {
            ItemEntry133Entry itemEntry133Entry44 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray88 = new byte[]{
               116,
               20,
               12,
               114,
               107,
               117,
               -31,
               -119,
               69,
               2,
               -86,
               -73,
               -13,
               -120,
               -10,
               -92,
               -123,
               101,
               -70,
               13,
               89,
               -118,
               56,
               -21,
               -65,
               -69,
               31,
               -91,
               81,
               24,
               -46,
               41
            };
            byte[] byteValueArray89 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray88, coreByteArrayEntry);
            this.byteFlagArray130 = itemEntry133Entry44.resolveByteArray(byteValueArray89);
            value2 = (value2 ^ -0x7d32f6c3eb406c89L) + 0x7a712713c7c0d935L;
         } else if (count7 == 47) {
            ItemEntry133Entry itemEntry133Entry45 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray90 = new byte[]{
               -110, 113, -70, -84, 48, -38, -117, 4, -94, -125, -21, -22, 66, 7, 79, 31, -65, -79, 44, 53, 37, -87, 75, 48, -113, 73, -16, 76, 120, -5, 35, 64
            };
            byte[] byteValueArray91 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray90, coreByteArrayEntry);
            this.byteFlagArray133 = itemEntry133Entry45.resolveByteArray(byteValueArray91);
            value2 = (value2 ^ -0x18543d5b2885c9bL) - 0x684000946fc52d6eL;
         } else if (count7 == 42) {
            ItemEntry133Entry itemEntry133Entry46 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray92 = new byte[]{
               -100,
               10,
               -57,
               27,
               -63,
               41,
               -22,
               74,
               99,
               55,
               -6,
               19,
               69,
               -74,
               -89,
               54,
               -56,
               -125,
               85,
               28,
               3,
               -127,
               -8,
               -36,
               89,
               -85,
               106,
               92,
               -84,
               -111,
               -66,
               -59
            };
            byte[] byteValueArray93 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray92, coreByteArrayEntry);
            this.byteFlagArray77 = itemEntry133Entry46.resolveByteArray(byteValueArray93);
            value2 = (value2 ^ -0x5a9e8662da5a7b54L) + 0x756e7351b919d031L;
         } else if (count7 == 21) {
            ItemEntry133Entry itemEntry133Entry47 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray94 = new byte[]{
               40,
               112,
               -64,
               -45,
               102,
               -16,
               85,
               -8,
               92,
               -32,
               36,
               -123,
               -45,
               -17,
               -21,
               -68,
               -45,
               -1,
               -23,
               -3,
               121,
               -48,
               -64,
               -108,
               36,
               -118,
               -1,
               22,
               75,
               86,
               37,
               -66
            };
            byte[] byteValueArray95 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray94, coreByteArrayEntry);
            this.byteFlagArray54 = itemEntry133Entry47.resolveByteArray(byteValueArray95);
            value2 = (value2 ^ 0x5aceea020d9b1584L) + 0x36fd28152eeee6c9L;
         } else if (count7 == 51) {
            ItemEntry133Entry itemEntry133Entry48 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray96 = new byte[]{
               -66,
               -26,
               -90,
               2,
               -62,
               89,
               -63,
               -33,
               -63,
               -53,
               -10,
               100,
               -21,
               -56,
               82,
               119,
               -90,
               107,
               -68,
               -123,
               34,
               51,
               120,
               -45,
               9,
               101,
               -32,
               105,
               -39,
               -78,
               -32,
               -77
            };
            byte[] byteValueArray97 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray96, coreByteArrayEntry);
            this.byteFlagArray106 = itemEntry133Entry48.resolveByteArray(byteValueArray97);
            value2 = (value2 ^ 0x4625b728d8877d44L) - 0x11e56d89743b5039L;
         } else if (count7 == 9) {
            coreByteArrayEntry = new CoreByteArrayEntry();
            value2 = (value2 ^ -0x41185a74baf43d3L) - 0x6719c12327a7c163L;
         } else if (count7 == 31) {
            ItemEntry133Entry itemEntry133Entry49 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray98 = new byte[]{
               -14,
               -24,
               -115,
               49,
               -51,
               84,
               125,
               67,
               52,
               6,
               -19,
               42,
               74,
               -15,
               44,
               -27,
               94,
               17,
               -70,
               -106,
               90,
               -90,
               -71,
               -80,
               61,
               -19,
               -42,
               -83,
               -109,
               94,
               -5,
               -75
            };
            byte[] byteValueArray99 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray98, coreByteArrayEntry);
            this.byteFlagArray161 = itemEntry133Entry49.resolveByteArray(byteValueArray99);
            value2 = (value2 ^ 0x32b994a46b9e830fL) - 0x5924b35a35a06fbaL;
         } else if (count7 == 67) {
            ItemEntry133Entry itemEntry133Entry50 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray100 = new byte[]{
               -32, 56, 117, -47, 77, 47, 74, -60, -99, -68, 113, 69, -83, 84, -28, 42, 8, -23, 48, -62, -51, 47, 54, -124, -22, -34, 3, 80, 94, -65, -43, -115
            };
            byte[] byteValueArray101 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray100, coreByteArrayEntry);
            this.byteFlagArray111 = itemEntry133Entry50.resolveByteArray(byteValueArray101);
            value2 = (value2 ^ 0x2dca3ec7e6662b06L) + 0x2a2f5b6cb8a00663L;
         } else if (count7 == 50) {
            ItemEntry133Entry itemEntry133Entry51 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray102 = new byte[]{
               46,
               -109,
               -107,
               3,
               -71,
               88,
               -38,
               2,
               -62,
               -113,
               114,
               38,
               -104,
               55,
               12,
               72,
               102,
               115,
               123,
               -112,
               78,
               -66,
               -121,
               24,
               -31,
               11,
               107,
               37,
               -78,
               84,
               -39,
               -15
            };
            byte[] byteValueArray103 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray102, coreByteArrayEntry);
            this.byteFlagArray190 = itemEntry133Entry51.resolveByteArray(byteValueArray103);
            value2 = (value2 ^ 0x6d097ace890a59d0L) - 0xff60dae914efd23L;
         } else if (count7 == 65) {
            ItemEntry133Entry itemEntry133Entry52 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray104 = new byte[]{
               -70,
               4,
               31,
               115,
               -67,
               79,
               -42,
               -78,
               12,
               110,
               -99,
               -76,
               104,
               95,
               120,
               59,
               -29,
               -117,
               50,
               -41,
               -93,
               -123,
               30,
               -11,
               -70,
               110,
               9,
               53,
               -71,
               -9,
               -41,
               94
            };
            byte[] byteValueArray105 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray104, coreByteArrayEntry);
            this.byteFlagArray123 = itemEntry133Entry52.resolveByteArray(byteValueArray105);
            value2 = (value2 ^ -0x1f9e30da01be499cL) + 0x219975f58b4ece85L;
         } else if (count7 == 53) {
            ItemEntry133Entry itemEntry133Entry53 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray106 = new byte[]{
               41,
               -86,
               22,
               100,
               96,
               -106,
               -60,
               -56,
               40,
               -110,
               -28,
               83,
               -42,
               -29,
               -29,
               -121,
               103,
               -124,
               99,
               -46,
               80,
               -39,
               -68,
               25,
               7,
               53,
               -66,
               -76,
               75,
               126,
               28,
               -90
            };
            byte[] byteValueArray107 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray106, coreByteArrayEntry);
            this.byteFlagArray48 = itemEntry133Entry53.resolveByteArray(byteValueArray107);
            value2 = (value2 ^ 0x3424317193df712bL) - 0x38c0c0d2cf37859L;
         } else if (count7 == 72) {
            ItemEntry133Entry itemEntry133Entry54 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray108 = new byte[]{
               31, -99, 6, -5, 111, -51, 14, -23, 24, -70, -68, -85, 103, 96, 21, 55, 41, 35, 24, 112, 76, 38, -81, -27, -29, -29, -66, -95, 68, 12, 27, -107
            };
            byte[] byteValueArray109 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray108, coreByteArrayEntry);
            this.byteFlagArray26 = itemEntry133Entry54.resolveByteArray(byteValueArray109);
            value2 = (value2 ^ 0x7e8c6f199a832012L) - 0xe7baabcf7bd7619L;
         } else if (count7 == 23) {
            ItemEntry133Entry itemEntry133Entry55 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray110 = new byte[]{
               -45, 36, -50, 26, 75, -94, 72, 58, 73, 35, 7, 93, 93, 23, 78, 19, 5, -1, 7, 120, 0, -68, 43, -122, 67, -5, -92, -23, -27, 42, 91, -66
            };
            byte[] byteValueArray111 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray110, coreByteArrayEntry);
            this.byteFlagArray35 = itemEntry133Entry55.resolveByteArray(byteValueArray111);
            value2 = (value2 ^ -0x7ea8baef9cd564aaL) + 0x7eb2ecc30d669a51L;
         } else if (count7 == 25) {
            ItemEntry133Entry itemEntry133Entry56 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray112 = new byte[]{
               19,
               -52,
               78,
               -4,
               -121,
               83,
               125,
               -28,
               90,
               -17,
               -79,
               -109,
               -38,
               41,
               -35,
               105,
               -111,
               101,
               44,
               -114,
               42,
               105,
               27,
               -95,
               90,
               123,
               -77,
               69,
               54,
               12,
               -50,
               105
            };
            byte[] byteValueArray113 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray112, coreByteArrayEntry);
            this.byteFlagArray5 = itemEntry133Entry56.resolveByteArray(byteValueArray113);
            value2 = (value2 ^ 0x10b8a360994aa08aL) + 0x1bf674722e354b68L;
         } else if (count7 != 92) {
            if (count7 == 2) {
               ItemEntry133Entry itemEntry133Entry57 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
               byte[] byteValueArray114 = new byte[]{
                  127,
                  23,
                  103,
                  -101,
                  -58,
                  74,
                  -78,
                  82,
                  -45,
                  30,
                  -82,
                  36,
                  51,
                  -65,
                  53,
                  105,
                  -35,
                  -93,
                  -100,
                  15,
                  -37,
                  -99,
                  47,
                  -59,
                  -58,
                  92,
                  71,
                  -128,
                  69,
                  41,
                  -28,
                  78
               };
               byte[] byteValueArray115 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray114, coreByteArrayEntry);
               this.byteFlagArray205 = itemEntry133Entry57.resolveByteArray(byteValueArray115);
               break;
            }

            if (count7 == 27) {
               break;
            }

            if (count7 != 64) {
               if (count7 != 68) {
                  if (count7 != 73) {
                     if (count7 != 56) {
                        if (count7 != 94) {
                           if (count7 != 35) {
                              if (count7 != 57) {
                                 if (count7 != 84) {
                                    if (count7 != 24) {
                                       if (count7 == 90) {
                                          ItemEntry133Entry itemEntry133Entry58 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                          byte[] byteValueArray116 = new byte[]{
                                             16,
                                             67,
                                             78,
                                             -20,
                                             -62,
                                             2,
                                             -68,
                                             -26,
                                             -98,
                                             -68,
                                             -101,
                                             116,
                                             110,
                                             20,
                                             67,
                                             -67,
                                             103,
                                             80,
                                             -97,
                                             100,
                                             96,
                                             -17,
                                             -95,
                                             -63,
                                             117,
                                             -81,
                                             40,
                                             -21,
                                             95,
                                             -8,
                                             -40,
                                             2
                                          };
                                          byte[] byteValueArray117 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray116, coreByteArrayEntry);
                                          this.byteFlagArray86 = itemEntry133Entry58.resolveByteArray(byteValueArray117);
                                          value2 = (value2 ^ -0x1c70a5c7cb558a67L) - 0x24bf10ebf77312b3L;
                                       } else if (count7 != 44) {
                                          if (count7 != 41) {
                                             if (count7 == 1) {
                                                ItemEntry133Entry itemEntry133Entry59 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                byte[] byteValueArray118 = new byte[]{
                                                   106,
                                                   72,
                                                   -77,
                                                   21,
                                                   -92,
                                                   -49,
                                                   106,
                                                   11,
                                                   13,
                                                   -11,
                                                   -23,
                                                   57,
                                                   -112,
                                                   124,
                                                   -47,
                                                   32,
                                                   -28,
                                                   29,
                                                   -53,
                                                   62,
                                                   -115,
                                                   14,
                                                   80,
                                                   60,
                                                   -107,
                                                   65,
                                                   -85,
                                                   108,
                                                   94,
                                                   27,
                                                   77,
                                                   2
                                                };
                                                byte[] byteValueArray119 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray118, coreByteArrayEntry);
                                                this.byteFlagArray50 = itemEntry133Entry59.resolveByteArray(byteValueArray119);
                                                value2 = (value2 ^ -0x4a165ad5a7bccb01L) + 0x1990703ee3d534b5L;
                                             } else if (count7 != 87) {
                                                if (count7 == 34) {
                                                   ItemEntry133Entry itemEntry133Entry60 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                   byte[] byteValueArray120 = new byte[]{
                                                      -32,
                                                      85,
                                                      -98,
                                                      -83,
                                                      111,
                                                      -18,
                                                      -71,
                                                      -67,
                                                      98,
                                                      38,
                                                      -117,
                                                      81,
                                                      108,
                                                      103,
                                                      -66,
                                                      15,
                                                      -64,
                                                      -16,
                                                      6,
                                                      -108,
                                                      -114,
                                                      59,
                                                      49,
                                                      -93,
                                                      53,
                                                      35,
                                                      117,
                                                      92,
                                                      105,
                                                      -128,
                                                      -42,
                                                      15
                                                   };
                                                   byte[] byteValueArray121 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray120, coreByteArrayEntry);
                                                   this.byteFlagArray117 = itemEntry133Entry60.resolveByteArray(byteValueArray121);
                                                   value2 = (value2 ^ -0x1c2d9016e6d263dfL) - 0x6657d31184742d0aL;
                                                } else if (count7 != 55) {
                                                   if (count7 != 80) {
                                                      if (count7 != 97) {
                                                         if (count7 != 77) {
                                                            if (count7 != 74) {
                                                               if (count7 != 78) {
                                                                  if (count7 != 3) {
                                                                     if (count7 != 38) {
                                                                        if (count7 != 83) {
                                                                           if (count7 != 93) {
                                                                              if (count7 != 39) {
                                                                                 if (count7 != 29) {
                                                                                    if (count7 == 37) {
                                                                                       ItemEntry133Entry itemEntry133Entry61 = new ItemEntry133Entry(
                                                                                          MovementWeightProperty.SHA3_256
                                                                                       );
                                                                                       byte[] byteValueArray122 = new byte[]{
                                                                                          48,
                                                                                          -61,
                                                                                          -99,
                                                                                          -47,
                                                                                          -39,
                                                                                          39,
                                                                                          -33,
                                                                                          105,
                                                                                          57,
                                                                                          19,
                                                                                          -93,
                                                                                          115,
                                                                                          75,
                                                                                          18,
                                                                                          43,
                                                                                          23,
                                                                                          82,
                                                                                          -68,
                                                                                          22,
                                                                                          114,
                                                                                          107,
                                                                                          96,
                                                                                          -70,
                                                                                          -89,
                                                                                          121,
                                                                                          -53,
                                                                                          -108,
                                                                                          -71,
                                                                                          110,
                                                                                          75,
                                                                                          93,
                                                                                          -112
                                                                                       };
                                                                                       byte[] byteValueArray123 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                          byteValueArray, byteValueArray122, coreByteArrayEntry
                                                                                       );
                                                                                       this.byteFlagArray84 = itemEntry133Entry61.resolveByteArray(byteValueArray123);
                                                                                       value2 = (value2 ^ 0x335606f122b386L) + 0xf61eba3765243cdL;
                                                                                    } else if (count7 != 59) {
                                                                                       if (count7 != 7) {
                                                                                          if (count7 != 11) {
                                                                                             if (count7 != 101) {
                                                                                                if (count7 != 46) {
                                                                                                   if (count7 != 20) {
                                                                                                      if (count7 != 16) {
                                                                                                         if (count7 != 99) {
                                                                                                            if (count7 != 63) {
                                                                                                               if (count7 != 96) {
                                                                                                                  if (count7 != 12) {
                                                                                                                     if (count7 != 10) {
                                                                                                                        if (count7 != 19) {
                                                                                                                           ItemEntry133Entry itemEntry133Entry62 = new ItemEntry133Entry(
                                                                                                                              MovementWeightProperty.SHA3_256
                                                                                                                           );
                                                                                                                           byte[] byteValueArray124 = new byte[]{
                                                                                                                              -16,
                                                                                                                              68,
                                                                                                                              -21,
                                                                                                                              125,
                                                                                                                              102,
                                                                                                                              25,
                                                                                                                              -128,
                                                                                                                              67,
                                                                                                                              -13,
                                                                                                                              44,
                                                                                                                              -45,
                                                                                                                              78,
                                                                                                                              118,
                                                                                                                              -32,
                                                                                                                              -87,
                                                                                                                              -118,
                                                                                                                              127,
                                                                                                                              -107,
                                                                                                                              -89,
                                                                                                                              18,
                                                                                                                              -48,
                                                                                                                              -43,
                                                                                                                              7,
                                                                                                                              -25,
                                                                                                                              -117,
                                                                                                                              -87,
                                                                                                                              -111,
                                                                                                                              96,
                                                                                                                              125,
                                                                                                                              6,
                                                                                                                              34,
                                                                                                                              -109
                                                                                                                           };
                                                                                                                           byte[] byteValueArray125 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                              byteValueArray, byteValueArray124, coreByteArrayEntry
                                                                                                                           );
                                                                                                                           this.byteFlagArray100 = itemEntry133Entry62.resolveByteArray(
                                                                                                                              byteValueArray125
                                                                                                                           );
                                                                                                                           value2 = (value2 ^ 0xdc948de6bfa5c12L)
                                                                                                                              + 0x133fb0b0fadeff9cL;
                                                                                                                        } else {
                                                                                                                           ItemEntry133Entry itemEntry133Entry63 = new ItemEntry133Entry(
                                                                                                                              MovementWeightProperty.SHA3_256
                                                                                                                           );
                                                                                                                           byte[] byteValueArray126 = new byte[]{
                                                                                                                              -44,
                                                                                                                              -68,
                                                                                                                              99,
                                                                                                                              -77,
                                                                                                                              -125,
                                                                                                                              -7,
                                                                                                                              -52,
                                                                                                                              79,
                                                                                                                              33,
                                                                                                                              -18,
                                                                                                                              -102,
                                                                                                                              -6,
                                                                                                                              37,
                                                                                                                              -25,
                                                                                                                              -71,
                                                                                                                              -114,
                                                                                                                              46,
                                                                                                                              88,
                                                                                                                              87,
                                                                                                                              114,
                                                                                                                              89,
                                                                                                                              -17,
                                                                                                                              -110,
                                                                                                                              -94,
                                                                                                                              -118,
                                                                                                                              89,
                                                                                                                              -3,
                                                                                                                              108,
                                                                                                                              -119,
                                                                                                                              -49,
                                                                                                                              27,
                                                                                                                              -71
                                                                                                                           };
                                                                                                                           byte[] byteValueArray127 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                              byteValueArray, byteValueArray126, coreByteArrayEntry
                                                                                                                           );
                                                                                                                           this.byteFlagArray45 = itemEntry133Entry63.resolveByteArray(
                                                                                                                              byteValueArray127
                                                                                                                           );
                                                                                                                           value2 = (
                                                                                                                                 value2 ^ 0x21bc5e179dcecce7L
                                                                                                                              )
                                                                                                                              - 0x285a1f48089a644cL;
                                                                                                                        }
                                                                                                                     } else {
                                                                                                                        ItemEntry133Entry itemEntry133Entry64 = new ItemEntry133Entry(
                                                                                                                           MovementWeightProperty.SHA3_256
                                                                                                                        );
                                                                                                                        byte[] byteValueArray128 = new byte[]{
                                                                                                                           45,
                                                                                                                           45,
                                                                                                                           -107,
                                                                                                                           -95,
                                                                                                                           -104,
                                                                                                                           9,
                                                                                                                           88,
                                                                                                                           -88,
                                                                                                                           -97,
                                                                                                                           -90,
                                                                                                                           16,
                                                                                                                           -53,
                                                                                                                           24,
                                                                                                                           3,
                                                                                                                           57,
                                                                                                                           6,
                                                                                                                           91,
                                                                                                                           112,
                                                                                                                           125,
                                                                                                                           70,
                                                                                                                           21,
                                                                                                                           -60,
                                                                                                                           -4,
                                                                                                                           -41,
                                                                                                                           -44,
                                                                                                                           -83,
                                                                                                                           -92,
                                                                                                                           -40,
                                                                                                                           -58,
                                                                                                                           -10,
                                                                                                                           61,
                                                                                                                           -12
                                                                                                                        };
                                                                                                                        byte[] byteValueArray129 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                           byteValueArray, byteValueArray128, coreByteArrayEntry
                                                                                                                        );
                                                                                                                        this.byteFlagArray67 = itemEntry133Entry64.resolveByteArray(
                                                                                                                           byteValueArray129
                                                                                                                        );
                                                                                                                        value2 = (value2 ^ 0x6dd20c7b2cee0f34L)
                                                                                                                           + 0x8469fff9ca1e895L;
                                                                                                                     }
                                                                                                                  } else {
                                                                                                                     ItemEntry133Entry itemEntry133Entry65 = new ItemEntry133Entry(
                                                                                                                        MovementWeightProperty.SHA3_256
                                                                                                                     );
                                                                                                                     byte[] byteValueArray130 = new byte[]{
                                                                                                                        36,
                                                                                                                        -11,
                                                                                                                        -3,
                                                                                                                        71,
                                                                                                                        5,
                                                                                                                        39,
                                                                                                                        -59,
                                                                                                                        20,
                                                                                                                        18,
                                                                                                                        77,
                                                                                                                        90,
                                                                                                                        -128,
                                                                                                                        108,
                                                                                                                        73,
                                                                                                                        -67,
                                                                                                                        -7,
                                                                                                                        -1,
                                                                                                                        31,
                                                                                                                        -113,
                                                                                                                        123,
                                                                                                                        -25,
                                                                                                                        -6,
                                                                                                                        -102,
                                                                                                                        -19,
                                                                                                                        -75,
                                                                                                                        67,
                                                                                                                        -36,
                                                                                                                        -86,
                                                                                                                        -45,
                                                                                                                        110,
                                                                                                                        11,
                                                                                                                        -84
                                                                                                                     };
                                                                                                                     byte[] byteValueArray131 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                        byteValueArray, byteValueArray130, coreByteArrayEntry
                                                                                                                     );
                                                                                                                     this.byteFlagArray94 = itemEntry133Entry65.resolveByteArray(
                                                                                                                        byteValueArray131
                                                                                                                     );
                                                                                                                     value2 = (value2 ^ 0x31d4855a8c109b75L)
                                                                                                                        + 0x31b0b500ac8f86acL;
                                                                                                                  }
                                                                                                               } else {
                                                                                                                  ItemEntry133Entry itemEntry133Entry66 = new ItemEntry133Entry(
                                                                                                                     MovementWeightProperty.SHA3_256
                                                                                                                  );
                                                                                                                  byte[] byteValueArray132 = new byte[]{
                                                                                                                     9,
                                                                                                                     -111,
                                                                                                                     26,
                                                                                                                     18,
                                                                                                                     -59,
                                                                                                                     55,
                                                                                                                     -97,
                                                                                                                     -95,
                                                                                                                     20,
                                                                                                                     51,
                                                                                                                     13,
                                                                                                                     97,
                                                                                                                     107,
                                                                                                                     -50,
                                                                                                                     -51,
                                                                                                                     6,
                                                                                                                     -99,
                                                                                                                     34,
                                                                                                                     -79,
                                                                                                                     24,
                                                                                                                     -108,
                                                                                                                     16,
                                                                                                                     77,
                                                                                                                     63,
                                                                                                                     -95,
                                                                                                                     -95,
                                                                                                                     63,
                                                                                                                     125,
                                                                                                                     72,
                                                                                                                     -10,
                                                                                                                     23,
                                                                                                                     -13
                                                                                                                  };
                                                                                                                  byte[] byteValueArray133 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                     byteValueArray, byteValueArray132, coreByteArrayEntry
                                                                                                                  );
                                                                                                                  this.byteFlagArray44 = itemEntry133Entry66.resolveByteArray(
                                                                                                                     byteValueArray133
                                                                                                                  );
                                                                                                                  value2 = (value2 ^ -0x4577e956c30b1f82L)
                                                                                                                     - 0x36cbdde86888a87fL;
                                                                                                               }
                                                                                                            } else {
                                                                                                               ItemEntry133Entry itemEntry133Entry67 = new ItemEntry133Entry(
                                                                                                                  MovementWeightProperty.SHA3_256
                                                                                                               );
                                                                                                               byte[] byteValueArray134 = new byte[]{
                                                                                                                  63,
                                                                                                                  -14,
                                                                                                                  122,
                                                                                                                  2,
                                                                                                                  6,
                                                                                                                  -88,
                                                                                                                  -63,
                                                                                                                  -123,
                                                                                                                  -37,
                                                                                                                  -13,
                                                                                                                  -46,
                                                                                                                  -28,
                                                                                                                  -15,
                                                                                                                  -51,
                                                                                                                  66,
                                                                                                                  -43,
                                                                                                                  -43,
                                                                                                                  15,
                                                                                                                  66,
                                                                                                                  60,
                                                                                                                  33,
                                                                                                                  -60,
                                                                                                                  -117,
                                                                                                                  -35,
                                                                                                                  73,
                                                                                                                  13,
                                                                                                                  68,
                                                                                                                  15,
                                                                                                                  -79,
                                                                                                                  50,
                                                                                                                  -96,
                                                                                                                  -58
                                                                                                               };
                                                                                                               byte[] byteValueArray135 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                  byteValueArray, byteValueArray134, coreByteArrayEntry
                                                                                                               );
                                                                                                               this.byteFlagArray142 = itemEntry133Entry67.resolveByteArray(
                                                                                                                  byteValueArray135
                                                                                                               );
                                                                                                               value2 = (value2 ^ -0x4242f82b8191481fL)
                                                                                                                  - 0x7188fe298c78070fL;
                                                                                                            }
                                                                                                         } else {
                                                                                                            ItemEntry133Entry itemEntry133Entry68 = new ItemEntry133Entry(
                                                                                                               MovementWeightProperty.SHA3_256
                                                                                                            );
                                                                                                            byte[] byteValueArray136 = new byte[]{
                                                                                                               59,
                                                                                                               -80,
                                                                                                               -36,
                                                                                                               -116,
                                                                                                               -12,
                                                                                                               95,
                                                                                                               80,
                                                                                                               123,
                                                                                                               111,
                                                                                                               113,
                                                                                                               63,
                                                                                                               -2,
                                                                                                               -92,
                                                                                                               43,
                                                                                                               -122,
                                                                                                               120,
                                                                                                               120,
                                                                                                               75,
                                                                                                               -103,
                                                                                                               -127,
                                                                                                               7,
                                                                                                               -106,
                                                                                                               121,
                                                                                                               105,
                                                                                                               127,
                                                                                                               -84,
                                                                                                               -128,
                                                                                                               95,
                                                                                                               42,
                                                                                                               41,
                                                                                                               124,
                                                                                                               118
                                                                                                            };
                                                                                                            byte[] byteValueArray137 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                               byteValueArray, byteValueArray136, coreByteArrayEntry
                                                                                                            );
                                                                                                            this.byteFlagArray144 = itemEntry133Entry68.resolveByteArray(
                                                                                                               byteValueArray137
                                                                                                            );
                                                                                                            value2 = (value2 ^ 0x4de3b951e993218eL)
                                                                                                               + 0x1ff6537885831127L;
                                                                                                         }
                                                                                                      } else {
                                                                                                         ItemEntry133Entry itemEntry133Entry69 = new ItemEntry133Entry(
                                                                                                            MovementWeightProperty.SHA3_256
                                                                                                         );
                                                                                                         byte[] byteValueArray138 = new byte[]{
                                                                                                            -76,
                                                                                                            -110,
                                                                                                            127,
                                                                                                            11,
                                                                                                            10,
                                                                                                            -40,
                                                                                                            67,
                                                                                                            -77,
                                                                                                            85,
                                                                                                            15,
                                                                                                            68,
                                                                                                            -36,
                                                                                                            -94,
                                                                                                            -5,
                                                                                                            126,
                                                                                                            -95,
                                                                                                            -80,
                                                                                                            122,
                                                                                                            -91,
                                                                                                            47,
                                                                                                            16,
                                                                                                            87,
                                                                                                            -8,
                                                                                                            -85,
                                                                                                            -113,
                                                                                                            -100,
                                                                                                            102,
                                                                                                            -45,
                                                                                                            27,
                                                                                                            -118,
                                                                                                            116,
                                                                                                            -12
                                                                                                         };
                                                                                                         byte[] byteValueArray139 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                            byteValueArray, byteValueArray138, coreByteArrayEntry
                                                                                                         );
                                                                                                         this.byteFlagArray176 = itemEntry133Entry69.resolveByteArray(byteValueArray139);
                                                                                                         value2 = (value2 ^ -0x28c41f3d383e557aL)
                                                                                                            + 0x65a358bde510592dL;
                                                                                                      }
                                                                                                   } else {
                                                                                                      ItemEntry133Entry itemEntry133Entry70 = new ItemEntry133Entry(
                                                                                                         MovementWeightProperty.SHA3_256
                                                                                                      );
                                                                                                      byte[] byteValueArray140 = new byte[]{
                                                                                                         -71,
                                                                                                         -52,
                                                                                                         63,
                                                                                                         97,
                                                                                                         121,
                                                                                                         -127,
                                                                                                         -36,
                                                                                                         111,
                                                                                                         -124,
                                                                                                         22,
                                                                                                         -38,
                                                                                                         122,
                                                                                                         80,
                                                                                                         104,
                                                                                                         60,
                                                                                                         63,
                                                                                                         113,
                                                                                                         -47,
                                                                                                         -77,
                                                                                                         -2,
                                                                                                         108,
                                                                                                         94,
                                                                                                         -47,
                                                                                                         110,
                                                                                                         68,
                                                                                                         71,
                                                                                                         -100,
                                                                                                         -64,
                                                                                                         -19,
                                                                                                         -8,
                                                                                                         -28,
                                                                                                         -19
                                                                                                      };
                                                                                                      byte[] byteValueArray141 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                         byteValueArray, byteValueArray140, coreByteArrayEntry
                                                                                                      );
                                                                                                      this.byteFlagArray155 = itemEntry133Entry70.resolveByteArray(byteValueArray141);
                                                                                                      value2 = (value2 ^ -0x2d6abeb8713e5d1eL)
                                                                                                         - 0x57f5ecd70d3058c8L;
                                                                                                   }
                                                                                                } else {
                                                                                                   ItemEntry133Entry itemEntry133Entry71 = new ItemEntry133Entry(
                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                   );
                                                                                                   byte[] byteValueArray142 = new byte[]{
                                                                                                      15,
                                                                                                      32,
                                                                                                      121,
                                                                                                      -73,
                                                                                                      -61,
                                                                                                      48,
                                                                                                      -110,
                                                                                                      74,
                                                                                                      4,
                                                                                                      65,
                                                                                                      119,
                                                                                                      44,
                                                                                                      -96,
                                                                                                      -104,
                                                                                                      24,
                                                                                                      64,
                                                                                                      -96,
                                                                                                      44,
                                                                                                      -83,
                                                                                                      120,
                                                                                                      -42,
                                                                                                      -49,
                                                                                                      68,
                                                                                                      -38,
                                                                                                      85,
                                                                                                      30,
                                                                                                      126,
                                                                                                      -45,
                                                                                                      51,
                                                                                                      -41,
                                                                                                      52,
                                                                                                      -105
                                                                                                   };
                                                                                                   byte[] byteValueArray143 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                      byteValueArray, byteValueArray142, coreByteArrayEntry
                                                                                                   );
                                                                                                   this.byteFlagArray69 = itemEntry133Entry71.resolveByteArray(byteValueArray143);
                                                                                                   value2 = (value2 ^ -0x2e28ec42a5fdde87L)
                                                                                                      - 0x61a88fdf5be81044L;
                                                                                                }
                                                                                             } else {
                                                                                                ItemEntry133Entry itemEntry133Entry72 = new ItemEntry133Entry(
                                                                                                   MovementWeightProperty.SHA3_256
                                                                                                );
                                                                                                byte[] byteValueArray144 = new byte[]{
                                                                                                   121,
                                                                                                   -4,
                                                                                                   4,
                                                                                                   97,
                                                                                                   -28,
                                                                                                   -124,
                                                                                                   93,
                                                                                                   -81,
                                                                                                   122,
                                                                                                   -17,
                                                                                                   76,
                                                                                                   6,
                                                                                                   24,
                                                                                                   83,
                                                                                                   -23,
                                                                                                   -45,
                                                                                                   -41,
                                                                                                   62,
                                                                                                   -106,
                                                                                                   23,
                                                                                                   -48,
                                                                                                   17,
                                                                                                   -125,
                                                                                                   96,
                                                                                                   89,
                                                                                                   127,
                                                                                                   -11,
                                                                                                   61,
                                                                                                   -82,
                                                                                                   127,
                                                                                                   91,
                                                                                                   -88
                                                                                                };
                                                                                                byte[] byteValueArray145 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                   byteValueArray, byteValueArray144, coreByteArrayEntry
                                                                                                );
                                                                                                this.byteFlagArray79 = itemEntry133Entry72.resolveByteArray(byteValueArray145);
                                                                                                value2 = (value2 ^ 0x680f05206e377d52L) - 0x59585179ab607699L;
                                                                                             }
                                                                                          } else {
                                                                                             ItemEntry133Entry itemEntry133Entry73 = new ItemEntry133Entry(
                                                                                                MovementWeightProperty.SHA3_256
                                                                                             );
                                                                                             byte[] byteValueArray146 = new byte[]{
                                                                                                -23,
                                                                                                43,
                                                                                                -101,
                                                                                                94,
                                                                                                -83,
                                                                                                31,
                                                                                                -24,
                                                                                                -16,
                                                                                                -60,
                                                                                                87,
                                                                                                68,
                                                                                                -7,
                                                                                                -91,
                                                                                                5,
                                                                                                94,
                                                                                                -52,
                                                                                                -127,
                                                                                                127,
                                                                                                -48,
                                                                                                88,
                                                                                                -42,
                                                                                                -115,
                                                                                                -72,
                                                                                                -42,
                                                                                                -41,
                                                                                                126,
                                                                                                -64,
                                                                                                6,
                                                                                                77,
                                                                                                61,
                                                                                                -117,
                                                                                                108
                                                                                             };
                                                                                             byte[] byteValueArray147 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                byteValueArray, byteValueArray146, coreByteArrayEntry
                                                                                             );
                                                                                             this.byteFlagArray91 = itemEntry133Entry73.resolveByteArray(byteValueArray147);
                                                                                             value2 = (value2 ^ -0xf5a2140e6e97f7cL) + 0x2eee300e96a22067L;
                                                                                          }
                                                                                       } else {
                                                                                          ItemEntry133Entry itemEntry133Entry74 = new ItemEntry133Entry(
                                                                                             MovementWeightProperty.SHA3_256
                                                                                          );
                                                                                          byte[] byteValueArray148 = new byte[]{
                                                                                             96,
                                                                                             -54,
                                                                                             -31,
                                                                                             -127,
                                                                                             -34,
                                                                                             93,
                                                                                             -29,
                                                                                             -40,
                                                                                             30,
                                                                                             92,
                                                                                             -102,
                                                                                             -37,
                                                                                             -85,
                                                                                             -39,
                                                                                             -31,
                                                                                             -114,
                                                                                             112,
                                                                                             -90,
                                                                                             -35,
                                                                                             -100,
                                                                                             11,
                                                                                             13,
                                                                                             108,
                                                                                             16,
                                                                                             -17,
                                                                                             -47,
                                                                                             -122,
                                                                                             82,
                                                                                             3,
                                                                                             -38,
                                                                                             36,
                                                                                             93
                                                                                          };
                                                                                          byte[] byteValueArray149 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                             byteValueArray, byteValueArray148, coreByteArrayEntry
                                                                                          );
                                                                                          this.byteFlagArray163 = itemEntry133Entry74.resolveByteArray(byteValueArray149);
                                                                                          value2 = (value2 ^ -0x50719fc36b36f897L) + 0x238cad483168a94eL;
                                                                                       }
                                                                                    } else {
                                                                                       ItemEntry133Entry itemEntry133Entry75 = new ItemEntry133Entry(
                                                                                          MovementWeightProperty.SHA3_256
                                                                                       );
                                                                                       byte[] byteValueArray150 = new byte[]{
                                                                                          -58,
                                                                                          12,
                                                                                          -120,
                                                                                          -105,
                                                                                          8,
                                                                                          -116,
                                                                                          44,
                                                                                          10,
                                                                                          26,
                                                                                          34,
                                                                                          -121,
                                                                                          -77,
                                                                                          33,
                                                                                          40,
                                                                                          -59,
                                                                                          1,
                                                                                          -30,
                                                                                          15,
                                                                                          41,
                                                                                          36,
                                                                                          69,
                                                                                          -75,
                                                                                          -53,
                                                                                          -116,
                                                                                          18,
                                                                                          102,
                                                                                          52,
                                                                                          -86,
                                                                                          -3,
                                                                                          95,
                                                                                          97,
                                                                                          -89
                                                                                       };
                                                                                       byte[] byteValueArray151 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                          byteValueArray, byteValueArray150, coreByteArrayEntry
                                                                                       );
                                                                                       this.byteFlagArray97 = itemEntry133Entry75.resolveByteArray(byteValueArray151);
                                                                                       value2 = (value2 ^ 0x24d24e9f9739800dL) + 0xe289d64f2269e76L;
                                                                                    }
                                                                                 } else {
                                                                                    ItemEntry133Entry itemEntry133Entry76 = new ItemEntry133Entry(
                                                                                       MovementWeightProperty.SHA3_256
                                                                                    );
                                                                                    byte[] byteValueArray152 = new byte[]{
                                                                                       -104,
                                                                                       43,
                                                                                       -13,
                                                                                       120,
                                                                                       -23,
                                                                                       88,
                                                                                       -11,
                                                                                       -25,
                                                                                       29,
                                                                                       -86,
                                                                                       92,
                                                                                       -106,
                                                                                       112,
                                                                                       -88,
                                                                                       -60,
                                                                                       72,
                                                                                       -56,
                                                                                       30,
                                                                                       45,
                                                                                       45,
                                                                                       33,
                                                                                       118,
                                                                                       65,
                                                                                       -125,
                                                                                       51,
                                                                                       89,
                                                                                       17,
                                                                                       74,
                                                                                       33,
                                                                                       127,
                                                                                       -24,
                                                                                       -25
                                                                                    };
                                                                                    byte[] byteValueArray153 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                       byteValueArray, byteValueArray152, coreByteArrayEntry
                                                                                    );
                                                                                    this.byteFlagArray152 = itemEntry133Entry76.resolveByteArray(byteValueArray153);
                                                                                    value2 = (value2 ^ -0x39843100bed42479L) + 0x777b6424b7f67a88L;
                                                                                 }
                                                                              } else {
                                                                                 ItemEntry133Entry itemEntry133Entry77 = new ItemEntry133Entry(
                                                                                    MovementWeightProperty.SHA3_256
                                                                                 );
                                                                                 byte[] byteValueArray154 = new byte[]{
                                                                                    -20,
                                                                                    36,
                                                                                    -75,
                                                                                    37,
                                                                                    -113,
                                                                                    -26,
                                                                                    -63,
                                                                                    18,
                                                                                    113,
                                                                                    -67,
                                                                                    94,
                                                                                    55,
                                                                                    -78,
                                                                                    24,
                                                                                    88,
                                                                                    27,
                                                                                    3,
                                                                                    105,
                                                                                    -15,
                                                                                    -18,
                                                                                    -48,
                                                                                    97,
                                                                                    -68,
                                                                                    28,
                                                                                    35,
                                                                                    11,
                                                                                    127,
                                                                                    -52,
                                                                                    -86,
                                                                                    -54,
                                                                                    29,
                                                                                    -39
                                                                                 };
                                                                                 byte[] byteValueArray155 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray154, coreByteArrayEntry);
                                                                                 this.byteFlagArray103 = itemEntry133Entry77.resolveByteArray(byteValueArray155);
                                                                                 value2 = (value2 ^ -0x7ffbd0da5fd6162cL) + 0x60bfceeacd4b4112L;
                                                                              }
                                                                           } else {
                                                                              ItemEntry133Entry itemEntry133Entry78 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                              byte[] byteValueArray156 = new byte[]{
                                                                                 -23,
                                                                                 -20,
                                                                                 -88,
                                                                                 79,
                                                                                 -119,
                                                                                 -69,
                                                                                 -110,
                                                                                 -48,
                                                                                 103,
                                                                                 6,
                                                                                 99,
                                                                                 79,
                                                                                 -91,
                                                                                 -24,
                                                                                 36,
                                                                                 61,
                                                                                 -107,
                                                                                 -22,
                                                                                 99,
                                                                                 -58,
                                                                                 -102,
                                                                                 -119,
                                                                                 -108,
                                                                                 -4,
                                                                                 -71,
                                                                                 20,
                                                                                 20,
                                                                                 -57,
                                                                                 104,
                                                                                 -14,
                                                                                 110,
                                                                                 -127
                                                                              };
                                                                              byte[] byteValueArray157 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray156, coreByteArrayEntry);
                                                                              this.byteFlagArray49 = itemEntry133Entry78.resolveByteArray(byteValueArray157);
                                                                              value2 = (value2 ^ -0x2fff2de068c51a8bL) + 0x4c48b4abf782183cL;
                                                                           }
                                                                        } else {
                                                                           ItemEntry133Entry itemEntry133Entry79 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                           byte[] byteValueArray158 = new byte[]{
                                                                              -62,
                                                                              66,
                                                                              18,
                                                                              -109,
                                                                              11,
                                                                              -107,
                                                                              68,
                                                                              -115,
                                                                              20,
                                                                              -91,
                                                                              -18,
                                                                              33,
                                                                              -90,
                                                                              -68,
                                                                              112,
                                                                              127,
                                                                              -55,
                                                                              -81,
                                                                              -62,
                                                                              52,
                                                                              -16,
                                                                              -120,
                                                                              32,
                                                                              -28,
                                                                              93,
                                                                              -47,
                                                                              -121,
                                                                              104,
                                                                              58,
                                                                              -74,
                                                                              -59,
                                                                              1
                                                                           };
                                                                           byte[] byteValueArray159 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray158, coreByteArrayEntry);
                                                                           this.byteFlagArray74 = itemEntry133Entry79.resolveByteArray(byteValueArray159);
                                                                           value2 = (value2 ^ 0x3e362b009ab065aL) - 0x52cf5d7c01b464cL;
                                                                        }
                                                                     } else {
                                                                        ItemEntry133Entry itemEntry133Entry80 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                        byte[] byteValueArray160 = new byte[]{
                                                                           64,
                                                                           105,
                                                                           12,
                                                                           -82,
                                                                           73,
                                                                           -81,
                                                                           28,
                                                                           -120,
                                                                           12,
                                                                           -117,
                                                                           1,
                                                                           124,
                                                                           -120,
                                                                           -87,
                                                                           61,
                                                                           117,
                                                                           -11,
                                                                           -46,
                                                                           -95,
                                                                           93,
                                                                           42,
                                                                           -34,
                                                                           -4,
                                                                           -100,
                                                                           -22,
                                                                           98,
                                                                           -125,
                                                                           -100,
                                                                           -103,
                                                                           105,
                                                                           -26,
                                                                           90
                                                                        };
                                                                        byte[] byteValueArray161 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray160, coreByteArrayEntry);
                                                                        this.byteFlagArray66 = itemEntry133Entry80.resolveByteArray(byteValueArray161);
                                                                        value2 = (value2 ^ -0x3fb1064184e76f67L) + 0x5dbb3c2ec76b0b10L;
                                                                     }
                                                                  } else {
                                                                     ItemEntry133Entry itemEntry133Entry81 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                     byte[] byteValueArray162 = new byte[]{
                                                                        -57,
                                                                        -64,
                                                                        -64,
                                                                        127,
                                                                        -59,
                                                                        -36,
                                                                        -67,
                                                                        -7,
                                                                        77,
                                                                        11,
                                                                        -2,
                                                                        4,
                                                                        13,
                                                                        0,
                                                                        85,
                                                                        40,
                                                                        80,
                                                                        73,
                                                                        107,
                                                                        5,
                                                                        -4,
                                                                        -83,
                                                                        37,
                                                                        39,
                                                                        -47,
                                                                        74,
                                                                        76,
                                                                        101,
                                                                        37,
                                                                        31,
                                                                        -54,
                                                                        -59
                                                                     };
                                                                     byte[] byteValueArray163 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray162, coreByteArrayEntry);
                                                                     this.byteFlagArray137 = itemEntry133Entry81.resolveByteArray(byteValueArray163);
                                                                     value2 = (value2 ^ 0x4205dcf9578e988bL) - 0x4a5cf4d89d20665fL;
                                                                  }
                                                               } else {
                                                                  ItemEntry133Entry itemEntry133Entry82 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                  byte[] byteValueArray164 = new byte[]{
                                                                     54,
                                                                     45,
                                                                     -106,
                                                                     -21,
                                                                     111,
                                                                     15,
                                                                     13,
                                                                     19,
                                                                     -77,
                                                                     -117,
                                                                     -71,
                                                                     -89,
                                                                     -118,
                                                                     92,
                                                                     122,
                                                                     -7,
                                                                     53,
                                                                     -51,
                                                                     -43,
                                                                     -15,
                                                                     91,
                                                                     -103,
                                                                     121,
                                                                     79,
                                                                     -13,
                                                                     5,
                                                                     -74,
                                                                     34,
                                                                     -52,
                                                                     -5,
                                                                     -27,
                                                                     75
                                                                  };
                                                                  byte[] byteValueArray165 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray164, coreByteArrayEntry);
                                                                  this.byteFlagArray135 = itemEntry133Entry82.resolveByteArray(byteValueArray165);
                                                                  value2 = (value2 ^ -0x57122dc4965e2d82L) + 0x767e73206b8b8328L;
                                                               }
                                                            } else {
                                                               ItemEntry133Entry itemEntry133Entry83 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                               byte[] byteValueArray166 = new byte[]{
                                                                  -90,
                                                                  -99,
                                                                  -55,
                                                                  123,
                                                                  51,
                                                                  -48,
                                                                  -65,
                                                                  53,
                                                                  35,
                                                                  -14,
                                                                  -20,
                                                                  94,
                                                                  -112,
                                                                  -77,
                                                                  47,
                                                                  101,
                                                                  -18,
                                                                  -31,
                                                                  -108,
                                                                  -39,
                                                                  -18,
                                                                  66,
                                                                  -108,
                                                                  -105,
                                                                  94,
                                                                  69,
                                                                  -18,
                                                                  64,
                                                                  -115,
                                                                  -7,
                                                                  -90,
                                                                  13
                                                               };
                                                               byte[] byteValueArray167 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray166, coreByteArrayEntry);
                                                               this.byteFlagArray124 = itemEntry133Entry83.resolveByteArray(byteValueArray167);
                                                               value2 = (value2 ^ 0x7884fcb41d13207cL) - 0x1ee92c65d84c8162L;
                                                            }
                                                         } else {
                                                            ItemEntry133Entry itemEntry133Entry84 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                            byte[] byteValueArray168 = new byte[]{
                                                               -106,
                                                               9,
                                                               71,
                                                               47,
                                                               -6,
                                                               6,
                                                               64,
                                                               -6,
                                                               98,
                                                               59,
                                                               -56,
                                                               -100,
                                                               -127,
                                                               49,
                                                               -102,
                                                               35,
                                                               -22,
                                                               123,
                                                               -67,
                                                               -7,
                                                               85,
                                                               -113,
                                                               98,
                                                               32,
                                                               13,
                                                               86,
                                                               -26,
                                                               -93,
                                                               -81,
                                                               -47,
                                                               -107,
                                                               45
                                                            };
                                                            byte[] byteValueArray169 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray168, coreByteArrayEntry);
                                                            this.byteFlagArray154 = itemEntry133Entry84.resolveByteArray(byteValueArray169);
                                                            value2 = (value2 ^ -0x6f4cc11da3f1a2f8L) - 0x547d4a4fb4dada39L;
                                                         }
                                                      } else {
                                                         ItemEntry133Entry itemEntry133Entry85 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                         byte[] byteValueArray170 = new byte[]{
                                                            98,
                                                            -67,
                                                            0,
                                                            -109,
                                                            -21,
                                                            57,
                                                            114,
                                                            46,
                                                            121,
                                                            -73,
                                                            -1,
                                                            14,
                                                            -44,
                                                            -44,
                                                            93,
                                                            87,
                                                            -31,
                                                            -33,
                                                            -85,
                                                            30,
                                                            104,
                                                            -104,
                                                            121,
                                                            23,
                                                            125,
                                                            -50,
                                                            -62,
                                                            116,
                                                            -22,
                                                            44,
                                                            77,
                                                            109
                                                         };
                                                         byte[] byteValueArray171 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray170, coreByteArrayEntry);
                                                         this.byteFlagArray141 = itemEntry133Entry85.resolveByteArray(byteValueArray171);
                                                         value2 = (value2 ^ 0x754183a8551f9ff5L) + 0x4c4d7cedfa71b3L;
                                                      }
                                                   } else {
                                                      ItemEntry133Entry itemEntry133Entry86 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                      byte[] byteValueArray172 = new byte[]{
                                                         66,
                                                         -26,
                                                         51,
                                                         -93,
                                                         2,
                                                         -67,
                                                         -65,
                                                         93,
                                                         56,
                                                         -111,
                                                         -12,
                                                         109,
                                                         77,
                                                         85,
                                                         75,
                                                         -11,
                                                         77,
                                                         114,
                                                         101,
                                                         93,
                                                         88,
                                                         -93,
                                                         51,
                                                         -92,
                                                         -91,
                                                         -35,
                                                         -110,
                                                         18,
                                                         127,
                                                         -116,
                                                         126,
                                                         -21
                                                      };
                                                      byte[] byteValueArray173 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray172, coreByteArrayEntry);
                                                      this.byteFlagArray102 = itemEntry133Entry86.resolveByteArray(byteValueArray173);
                                                      value2 = (value2 ^ 0x2add48ca0d70591dL) - 0x39499c81f2900021L;
                                                   }
                                                } else {
                                                   ItemEntry133Entry itemEntry133Entry87 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                   byte[] byteValueArray174 = new byte[]{
                                                      13,
                                                      45,
                                                      -124,
                                                      -110,
                                                      -94,
                                                      50,
                                                      -111,
                                                      22,
                                                      -120,
                                                      -104,
                                                      65,
                                                      -79,
                                                      58,
                                                      117,
                                                      -86,
                                                      -99,
                                                      84,
                                                      56,
                                                      -61,
                                                      11,
                                                      -42,
                                                      119,
                                                      7,
                                                      22,
                                                      97,
                                                      7,
                                                      54,
                                                      16,
                                                      18,
                                                      51,
                                                      16,
                                                      24
                                                   };
                                                   byte[] byteValueArray175 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray174, coreByteArrayEntry);
                                                   this.byteFlagArray83 = itemEntry133Entry87.resolveByteArray(byteValueArray175);
                                                   value2 = (value2 ^ -0x21b40d01a47100f6L) + 0x37e642507f1334a0L;
                                                }
                                             } else {
                                                ItemEntry133Entry itemEntry133Entry88 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                byte[] byteValueArray176 = new byte[]{
                                                   96,
                                                   3,
                                                   -13,
                                                   3,
                                                   69,
                                                   11,
                                                   60,
                                                   -38,
                                                   36,
                                                   58,
                                                   79,
                                                   -100,
                                                   -41,
                                                   -46,
                                                   -58,
                                                   116,
                                                   127,
                                                   125,
                                                   71,
                                                   105,
                                                   113,
                                                   -101,
                                                   -12,
                                                   3,
                                                   85,
                                                   30,
                                                   -40,
                                                   -68,
                                                   -68,
                                                   33,
                                                   29,
                                                   -82
                                                };
                                                byte[] byteValueArray177 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray176, coreByteArrayEntry);
                                                this.byteFlagArray17 = itemEntry133Entry88.resolveByteArray(byteValueArray177);
                                                value2 = (value2 ^ 0x3e7af6271f363f5cL) - 0xb6c55618d43bf3aL;
                                             }
                                          } else {
                                             ItemEntry133Entry itemEntry133Entry89 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                             byte[] byteValueArray178 = new byte[]{
                                                52,
                                                78,
                                                -37,
                                                -110,
                                                81,
                                                56,
                                                8,
                                                -66,
                                                -105,
                                                -68,
                                                -15,
                                                82,
                                                122,
                                                60,
                                                83,
                                                120,
                                                -19,
                                                98,
                                                -112,
                                                18,
                                                64,
                                                -23,
                                                0,
                                                116,
                                                -114,
                                                -97,
                                                -117,
                                                48,
                                                -97,
                                                -26,
                                                -49,
                                                -89
                                             };
                                             byte[] byteValueArray179 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray178, coreByteArrayEntry);
                                             this.byteFlagArray112 = itemEntry133Entry89.resolveByteArray(byteValueArray179);
                                             value2 = (value2 ^ -0x42d705f9ddbad882L) + 0x3d9662a01f8dcf8fL;
                                          }
                                       } else {
                                          ItemEntry133Entry itemEntry133Entry90 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                          byte[] byteValueArray180 = new byte[]{
                                             -115,
                                             41,
                                             -16,
                                             -57,
                                             84,
                                             -92,
                                             -101,
                                             -56,
                                             103,
                                             66,
                                             -103,
                                             102,
                                             -126,
                                             32,
                                             -69,
                                             -90,
                                             -126,
                                             31,
                                             4,
                                             -11,
                                             -102,
                                             77,
                                             -3,
                                             -90,
                                             12,
                                             126,
                                             -58,
                                             86,
                                             69,
                                             -95,
                                             70,
                                             67
                                          };
                                          byte[] byteValueArray181 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray180, coreByteArrayEntry);
                                          this.byteFlagArray175 = itemEntry133Entry90.resolveByteArray(byteValueArray181);
                                          value2 = (value2 ^ -0x29ec1d280cfb8a96L) - 0x6868aa1891acd277L;
                                       }
                                    } else {
                                       ItemEntry133Entry itemEntry133Entry91 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                       byte[] byteValueArray182 = new byte[]{
                                          -124,
                                          -34,
                                          43,
                                          -63,
                                          -112,
                                          44,
                                          68,
                                          71,
                                          -57,
                                          44,
                                          -19,
                                          16,
                                          -72,
                                          127,
                                          -53,
                                          81,
                                          -94,
                                          -94,
                                          69,
                                          90,
                                          -12,
                                          90,
                                          109,
                                          -121,
                                          -103,
                                          -18,
                                          83,
                                          -126,
                                          -94,
                                          39,
                                          -44,
                                          35
                                       };
                                       byte[] byteValueArray183 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray182, coreByteArrayEntry);
                                       this.byteFlagArray88 = itemEntry133Entry91.resolveByteArray(byteValueArray183);
                                       value2 = (value2 ^ 0x6bd2643378361a59L) + 0x5a61beae392f66ebL;
                                    }
                                 } else {
                                    ItemEntry133Entry itemEntry133Entry92 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                    byte[] byteValueArray184 = new byte[]{
                                       -118,
                                       -37,
                                       -50,
                                       104,
                                       127,
                                       -62,
                                       -18,
                                       73,
                                       -47,
                                       26,
                                       58,
                                       -93,
                                       57,
                                       57,
                                       12,
                                       -115,
                                       -82,
                                       97,
                                       27,
                                       23,
                                       8,
                                       -60,
                                       82,
                                       -96,
                                       -119,
                                       -109,
                                       82,
                                       -76,
                                       -102,
                                       78,
                                       -7,
                                       51
                                    };
                                    byte[] byteValueArray185 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray184, coreByteArrayEntry);
                                    this.byteFlagArray70 = itemEntry133Entry92.resolveByteArray(byteValueArray185);
                                    value2 = (value2 ^ -0x50094acb4f0db1d3L) - 0xccf8aed4a010f41L;
                                 }
                              } else {
                                 ItemEntry133Entry itemEntry133Entry93 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                 byte[] byteValueArray186 = new byte[]{
                                    114,
                                    36,
                                    74,
                                    91,
                                    -19,
                                    -22,
                                    102,
                                    -91,
                                    -100,
                                    -27,
                                    -18,
                                    -72,
                                    119,
                                    100,
                                    -51,
                                    54,
                                    100,
                                    76,
                                    6,
                                    -118,
                                    -53,
                                    10,
                                    -98,
                                    -10,
                                    58,
                                    -77,
                                    20,
                                    70,
                                    116,
                                    73,
                                    -114,
                                    56
                                 };
                                 byte[] byteValueArray187 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray186, coreByteArrayEntry);
                                 this.byteFlagArray20 = itemEntry133Entry93.resolveByteArray(byteValueArray187);
                                 value2 = (value2 ^ 0x366a29504a1f6519L) + 0x4836ad393b6c14d2L;
                              }
                           } else {
                              ItemEntry133Entry itemEntry133Entry94 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                              byte[] byteValueArray188 = new byte[]{
                                 83,
                                 120,
                                 70,
                                 13,
                                 -72,
                                 100,
                                 -37,
                                 93,
                                 -50,
                                 -21,
                                 -44,
                                 18,
                                 -80,
                                 41,
                                 18,
                                 -59,
                                 100,
                                 55,
                                 11,
                                 29,
                                 20,
                                 -1,
                                 -128,
                                 67,
                                 -80,
                                 68,
                                 -45,
                                 39,
                                 74,
                                 69,
                                 -122,
                                 48
                              };
                              byte[] byteValueArray189 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray188, coreByteArrayEntry);
                              this.byteFlagArray195 = itemEntry133Entry94.resolveByteArray(byteValueArray189);
                              value2 = (value2 ^ 0x7cc74cbcdff3e001L) + 0x19b5158940f13f70L;
                           }
                        } else {
                           ItemEntry133Entry itemEntry133Entry95 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                           byte[] byteValueArray190 = new byte[]{
                              -9,
                              75,
                              -37,
                              46,
                              18,
                              -116,
                              2,
                              118,
                              -20,
                              17,
                              -127,
                              125,
                              -105,
                              124,
                              -38,
                              -30,
                              -62,
                              -79,
                              -42,
                              -9,
                              97,
                              20,
                              -29,
                              -34,
                              -55,
                              37,
                              -100,
                              59,
                              71,
                              -69,
                              94,
                              -29
                           };
                           byte[] byteValueArray191 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray190, coreByteArrayEntry);
                           this.byteFlagArray71 = itemEntry133Entry95.resolveByteArray(byteValueArray191);
                           value2 = (value2 ^ -0x75a60f72263b2e00L) - 0x2c6dd5029347496aL;
                        }
                     } else {
                        ItemEntry133Entry itemEntry133Entry96 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                        byte[] byteValueArray192 = new byte[]{
                           47,
                           117,
                           -31,
                           -107,
                           -29,
                           -124,
                           13,
                           -83,
                           8,
                           -111,
                           -102,
                           101,
                           58,
                           -115,
                           -15,
                           38,
                           127,
                           -10,
                           -54,
                           -57,
                           -30,
                           13,
                           75,
                           82,
                           -106,
                           108,
                           -52,
                           121,
                           24,
                           81,
                           -41,
                           -59
                        };
                        byte[] byteValueArray193 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray192, coreByteArrayEntry);
                        this.byteFlagArray46 = itemEntry133Entry96.resolveByteArray(byteValueArray193);
                        value2 = (value2 ^ -0x686c09df26e6b0ffL) + 0x5b51abacd4ccd90L;
                     }
                  } else {
                     ItemEntry133Entry itemEntry133Entry97 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                     byte[] byteValueArray194 = new byte[]{
                        6,
                        -30,
                        112,
                        1,
                        -88,
                        84,
                        103,
                        97,
                        127,
                        -62,
                        -77,
                        3,
                        120,
                        -30,
                        -121,
                        -67,
                        -125,
                        -29,
                        66,
                        -97,
                        -110,
                        9,
                        -94,
                        58,
                        -103,
                        -49,
                        108,
                        78,
                        -96,
                        82,
                        -75,
                        -56
                     };
                     byte[] byteValueArray195 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray194, coreByteArrayEntry);
                     this.byteFlagArray162 = itemEntry133Entry97.resolveByteArray(byteValueArray195);
                     value2 = (value2 ^ -0x55b19391900dd490L) + 0x7530238b551c6124L;
                  }
               } else {
                  ItemEntry133Entry itemEntry133Entry98 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                  byte[] byteValueArray196 = new byte[]{
                     7,
                     11,
                     -127,
                     120,
                     97,
                     50,
                     -56,
                     2,
                     -127,
                     23,
                     124,
                     -109,
                     97,
                     86,
                     121,
                     79,
                     -53,
                     21,
                     -92,
                     64,
                     -8,
                     -53,
                     88,
                     -4,
                     -29,
                     -87,
                     -20,
                     -64,
                     32,
                     85,
                     -122,
                     -35
                  };
                  byte[] byteValueArray197 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray196, coreByteArrayEntry);
                  this.byteFlagArray32 = itemEntry133Entry98.resolveByteArray(byteValueArray197);
                  value2 = (value2 ^ 0x6369458590fb7d50L) - 0x4e05835dbe5758d6L;
               }
            } else {
               ItemEntry133Entry itemEntry133Entry99 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
               byte[] byteValueArray198 = new byte[]{
                  -62,
                  -65,
                  32,
                  -71,
                  9,
                  127,
                  118,
                  -35,
                  -49,
                  -26,
                  -12,
                  71,
                  -70,
                  107,
                  103,
                  98,
                  -96,
                  -27,
                  122,
                  122,
                  74,
                  -79,
                  67,
                  40,
                  -54,
                  -117,
                  -124,
                  60,
                  -22,
                  -110,
                  -107,
                  97
               };
               byte[] byteValueArray199 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray198, coreByteArrayEntry);
               this.byteFlagArray159 = itemEntry133Entry99.resolveByteArray(byteValueArray199);
               value2 = (value2 ^ -0x5e0118379a3f276aL) + 0x7fa967ca737cb705L;
            }
         } else {
            ItemEntry133Entry itemEntry133Entry100 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray200 = new byte[]{
               -45,
               -86,
               -65,
               -70,
               28,
               -1,
               52,
               -54,
               112,
               21,
               -56,
               124,
               -36,
               -23,
               65,
               -20,
               -17,
               104,
               -53,
               12,
               21,
               117,
               28,
               11,
               68,
               -118,
               17,
               -71,
               -45,
               -40,
               -41,
               -46
            };
            byte[] byteValueArray201 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray200, coreByteArrayEntry);
            this.byteFlagArray114 = itemEntry133Entry100.resolveByteArray(byteValueArray201);
            value2 = (value2 ^ -0x1ce04694fe3cdd65L) + 0x3aa5f442836f78f0L;
         }
      }
   }

   public PacketPayloadEncoder(SpecialLongEntry specialLongEntry2) {
      this.countArray4 = new int[16];
      this.byteFlagArray125 = new byte[16];
      this.byteFlagArray180 = new byte[16];
      this.byteFlagArray109 = null;
      this.countArray6 = null;
      this.byteFlagArray10 = null;
      this.byteFlagArray150 = new byte[1];
      this.byteFlagArray145 = new byte[1];
      this.byteFlagArray101 = new byte[1];
      this.byteFlagArray4 = new byte[1];
      this.byteFlagArray116 = new byte[1];
      this.byteFlagArray204 = new byte[1];
      this.byteFlagArray188 = new byte[1];
      this.byteFlagArray121 = new byte[1];
      this.byteFlagArray202 = new byte[1];
      this.byteFlagArray164 = new byte[1];
      this.byteFlagArray53 = new byte[1];
      this.byteFlagArray127 = new byte[1];
      this.byteFlagArray170 = new byte[1];
      this.byteFlagArray22 = new byte[1];
      this.byteFlagArray19 = new byte[1];
      this.byteFlagArray138 = new byte[1];
      this.byteFlagArray167 = new byte[1];
      this.byteFlagArray96 = new byte[1];
      this.byteFlagArray7 = new byte[1];
      this.byteFlagArray80 = new byte[1];
      this.byteFlagArray166 = new byte[1];
      this.byteFlagArray55 = new byte[1];
      this.byteFlagArray18 = new byte[1];
      this.byteFlagArray89 = new byte[1];
      this.byteFlagArray153 = new byte[1];
      this.byteFlagArray31 = new byte[1];
      this.byteFlagArray139 = new byte[1];
      this.byteFlagArray104 = new byte[1];
      this.byteFlagArray120 = new byte[1];
      this.byteFlagArray147 = new byte[1];
      this.byteFlagArray62 = new byte[1];
      this.byteFlagArray171 = new byte[1];
      this.byteFlagArray41 = new byte[1];
      this.byteFlagArray93 = new byte[1];
      this.byteFlagArray177 = new byte[1];
      this.byteFlagArray85 = new byte[1];
      this.byteFlagArray95 = new byte[1];
      this.byteFlagArray193 = new byte[1];
      this.byteFlagArray78 = new byte[1];
      this.byteFlagArray72 = new byte[1];
      this.byteFlagArray201 = new byte[1];
      this.byteFlagArray68 = new byte[1];
      this.byteFlagArray107 = new byte[1];
      this.byteFlagArray203 = new byte[1];
      this.byteFlagArray14 = new byte[1];
      this.byteFlagArray12 = new byte[1];
      this.byteFlagArray25 = new byte[1];
      this.byteFlagArray143 = new byte[1];
      this.byteFlagArray184 = new byte[1];
      this.byteFlagArray36 = new byte[1];
      this.byteFlagArray151 = new byte[1];
      this.byteFlagArray115 = new byte[1];
      this.byteFlagArray183 = new byte[1];
      this.byteFlagArray57 = new byte[1];
      this.byteFlagArray187 = new byte[1];
      this.byteFlagArray30 = new byte[1];
      this.byteFlagArray42 = new byte[1];
      this.byteFlagArray194 = new byte[1];
      this.byteFlagArray189 = new byte[1];
      this.byteFlagArray126 = new byte[1];
      this.byteFlagArray34 = new byte[1];
      this.byteFlagArray52 = new byte[1];
      this.byteFlagArray16 = new byte[1];
      this.byteFlagArray181 = new byte[1];
      this.byteFlagArray182 = new byte[1];
      this.byteFlagArray2 = new byte[1];
      this.byteFlagArray134 = new byte[1];
      this.byteFlagArray178 = new byte[1];
      this.byteFlagArray118 = new byte[1];
      this.byteFlagArray131 = new byte[1];
      this.byteFlagArray37 = new byte[1];
      this.byteFlagArray47 = new byte[1];
      this.byteFlagArray39 = new byte[1];
      this.byteFlagArray76 = new byte[1];
      this.byteFlagArray92 = new byte[1];
      this.byteFlagArray105 = new byte[1];
      this.byteFlagArray28 = new byte[1];
      this.byteFlagArray140 = new byte[1];
      this.byteFlagArray90 = new byte[1];
      this.byteFlagArray129 = new byte[1];
      this.byteFlagArray33 = new byte[1];
      this.byteFlagArray3 = new byte[1];
      this.byteFlagArray132 = new byte[1];
      this.byteFlagArray168 = new byte[1];
      this.byteFlagArray56 = new byte[1];
      this.byteFlagArray13 = new byte[1];
      this.byteFlagArray196 = new byte[1];
      this.byteFlagArray174 = new byte[1];
      this.byteFlagArray43 = new byte[1];
      this.byteFlagArray173 = new byte[1];
      this.byteFlagArray63 = new byte[1];
      this.byteFlagArray186 = new byte[1];
      this.byteFlagArray110 = new byte[1];
      this.byteFlagArray197 = new byte[1];
      this.byteFlagArray60 = new byte[1];
      this.byteFlagArray27 = new byte[1];
      this.byteFlagArray15 = new byte[1];
      this.byteFlagArray73 = new byte[1];
      this.byteFlagArray82 = new byte[1];
      this.byteFlagArray9 = new byte[1];
      this.byteFlagArray163 = new byte[1];
      this.byteFlagArray148 = new byte[1];
      this.byteFlagArray176 = new byte[1];
      this.byteFlagArray17 = new byte[1];
      this.byteFlagArray79 = new byte[1];
      this.byteFlagArray71 = new byte[1];
      this.byteFlagArray23 = new byte[1];
      this.byteFlagArray86 = new byte[1];
      this.byteFlagArray46 = new byte[1];
      this.byteFlagArray179 = new byte[1];
      this.byteFlagArray199 = new byte[1];
      this.byteFlagArray66 = new byte[1];
      this.byteFlagArray58 = new byte[1];
      this.byteFlagArray192 = new byte[1];
      this.byteFlagArray49 = new byte[1];
      this.byteFlagArray21 = new byte[1];
      this.byteFlagArray48 = new byte[1];
      this.byteFlagArray54 = new byte[1];
      this.byteFlagArray40 = new byte[1];
      this.byteFlagArray144 = new byte[1];
      this.byteFlagArray77 = new byte[1];
      this.byteFlagArray112 = new byte[1];
      this.byteFlagArray169 = new byte[1];
      this.byteFlagArray97 = new byte[1];
      this.byteFlagArray70 = new byte[1];
      this.byteFlagArray64 = new byte[1];
      this.byteFlagArray198 = new byte[1];
      this.byteFlagArray61 = new byte[1];
      this.byteFlagArray119 = new byte[1];
      this.byteFlagArray11 = new byte[1];
      this.byteFlagArray50 = new byte[1];
      this.byteFlagArray81 = new byte[1];
      this.byteFlagArray67 = new byte[1];
      this.byteFlagArray87 = new byte[1];
      this.byteFlagArray162 = new byte[1];
      this.byteFlagArray106 = new byte[1];
      this.byteFlagArray156 = new byte[1];
      this.byteFlagArray114 = new byte[1];
      this.byteFlagArray157 = new byte[1];
      this.byteFlagArray75 = new byte[1];
      this.byteFlagArray98 = new byte[1];
      this.byteFlagArray69 = new byte[1];
      this.byteFlagArray91 = new byte[1];
      this.byteFlagArray146 = new byte[1];
      this.byteFlagArray65 = new byte[1];
      this.byteFlagArray117 = new byte[1];
      this.byteFlagArray200 = new byte[1];
      this.byteFlagArray190 = new byte[1];
      this.byteFlagArray83 = new byte[1];
      this.byteFlagArray165 = new byte[1];
      this.byteFlagArray44 = new byte[1];
      this.byteFlagArray160 = new byte[1];
      this.byteFlagArray94 = new byte[1];
      this.byteFlagArray35 = new byte[1];
      this.byteFlagArray26 = new byte[1];
      this.byteFlagArray6 = new byte[1];
      this.byteFlagArray103 = new byte[1];
      this.byteFlagArray137 = new byte[1];
      this.byteFlagArray84 = new byte[1];
      this.byteFlagArray99 = new byte[1];
      this.byteFlagArray154 = new byte[1];
      this.byteFlagArray158 = new byte[1];
      this.byteFlagArray100 = new byte[1];
      this.byteFlagArray141 = new byte[1];
      this.byteFlagArray111 = new byte[1];
      this.byteFlagArray108 = new byte[1];
      this.byteFlagArray38 = new byte[1];
      this.byteFlagArray128 = new byte[1];
      this.byteFlagArray175 = new byte[1];
      this.byteFlagArray5 = new byte[1];
      this.byteFlagArray29 = new byte[1];
      this.byteFlagArray74 = new byte[1];
      this.byteFlagArray152 = new byte[1];
      this.byteFlagArray136 = new byte[1];
      this.byteFlagArray24 = new byte[1];
      this.byteFlagArray195 = new byte[1];
      this.byteFlagArray149 = new byte[1];
      this.byteFlagArray142 = new byte[1];
      this.byteFlagArray45 = new byte[1];
      this.byteFlagArray32 = new byte[1];
      this.byteFlagArray130 = new byte[1];
      this.byteFlagArray8 = new byte[1];
      this.byteFlagArray123 = new byte[1];
      this.byteFlagArray59 = new byte[1];
      this.byteFlagArray20 = new byte[1];
      this.byteFlagArray159 = new byte[1];
      this.byteFlagArray133 = new byte[1];
      this.byteFlagArray122 = new byte[1];
      this.byteFlagArray51 = new byte[1];
      this.byteFlagArray124 = new byte[1];
      this.byteFlagArray135 = new byte[1];
      this.byteFlagArray155 = new byte[1];
      this.byteFlagArray88 = new byte[1];
      this.byteFlagArray113 = new byte[1];
      this.byteFlagArray102 = new byte[1];
      this.byteFlagArray172 = new byte[1];
      this.byteFlagArray191 = new byte[1];
      this.byteFlagArray = new byte[1];
      this.byteFlagArray161 = new byte[1];
      this.byteFlagArray205 = new byte[1];
      this.byteFlagArray185 = new byte[1];
      this.specialLongEntry = specialLongEntry2;
   }

   static {
      int count6 = 0;
      long value = 0x5d71b5fa378be093L;
      long value2 = 0x5e56b45eb2a54feL;

      while (true) {
         int count7 = (int)(value % 7L);
         if (count7 != 2) {
            if (count7 == 4) {
               countArray = new int[16][256];
               value = (value ^ 0x59a986d8d5f3b6e5L) - 0x2f63c41a3b9ab4e9L;
               value2 = (value2 ^ 0x74a3910d5007dd5bL) + 0x4f5d4ab755ab7dbeL;
               continue;
            }

            if (count7 != 6) {
               if (count7 != 1) {
                  if (count7 != 3) {
                     if (count7 != 0) {
                        countArray3 = new int[]{
                           82,
                           9,
                           106,
                           213,
                           48,
                           54,
                           165,
                           56,
                           191,
                           64,
                           163,
                           158,
                           129,
                           243,
                           215,
                           251,
                           124,
                           227,
                           57,
                           130,
                           155,
                           47,
                           255,
                           135,
                           52,
                           142,
                           67,
                           68,
                           196,
                           222,
                           233,
                           203,
                           84,
                           123,
                           148,
                           50,
                           166,
                           194,
                           35,
                           61,
                           238,
                           76,
                           149,
                           11,
                           66,
                           250,
                           195,
                           78,
                           8,
                           46,
                           161,
                           102,
                           40,
                           217,
                           36,
                           178,
                           118,
                           91,
                           162,
                           73,
                           109,
                           139,
                           209,
                           37,
                           114,
                           248,
                           246,
                           100,
                           134,
                           104,
                           152,
                           22,
                           212,
                           164,
                           92,
                           204,
                           93,
                           101,
                           182,
                           146,
                           108,
                           112,
                           72,
                           80,
                           253,
                           237,
                           185,
                           218,
                           94,
                           21,
                           70,
                           87,
                           167,
                           141,
                           157,
                           132,
                           144,
                           216,
                           171,
                           0,
                           140,
                           188,
                           211,
                           10,
                           247,
                           228,
                           88,
                           5,
                           184,
                           179,
                           69,
                           6,
                           208,
                           44,
                           30,
                           143,
                           202,
                           63,
                           15,
                           2,
                           193,
                           175,
                           189,
                           3,
                           1,
                           19,
                           138,
                           107,
                           58,
                           145,
                           17,
                           65,
                           79,
                           103,
                           220,
                           234,
                           151,
                           242,
                           207,
                           206,
                           240,
                           180,
                           230,
                           115,
                           150,
                           172,
                           116,
                           34,
                           231,
                           173,
                           53,
                           133,
                           226,
                           249,
                           55,
                           232,
                           28,
                           117,
                           223,
                           110,
                           71,
                           241,
                           26,
                           113,
                           29,
                           41,
                           197,
                           137,
                           111,
                           183,
                           98,
                           14,
                           170,
                           24,
                           190,
                           27,
                           252,
                           86,
                           62,
                           75,
                           198,
                           210,
                           121,
                           32,
                           154,
                           219,
                           192,
                           254,
                           120,
                           205,
                           90,
                           244,
                           31,
                           221,
                           168,
                           51,
                           136,
                           7,
                           199,
                           49,
                           177,
                           18,
                           16,
                           89,
                           39,
                           128,
                           236,
                           95,
                           96,
                           81,
                           127,
                           169,
                           25,
                           181,
                           74,
                           13,
                           45,
                           229,
                           122,
                           159,
                           147,
                           201,
                           156,
                           239,
                           160,
                           224,
                           59,
                           77,
                           174,
                           42,
                           245,
                           176,
                           200,
                           235,
                           187,
                           60,
                           131,
                           83,
                           153,
                           97,
                           23,
                           43,
                           4,
                           126,
                           186,
                           119,
                           214,
                           38,
                           225,
                           105,
                           20,
                           99,
                           85,
                           33,
                           12,
                           125
                        };
                        value = (value ^ 0x214b4fa582dd8cc4L) - 0x1a209828258fa172L;
                        value2 = (value2 ^ 0x79ae19eb1b1a2282L) + 0x30009108d501f950L;
                     } else {
                        countArray2 = new int[]{0, 1, 2, 4, 8, 16, 32, 64, 128, 27, 54};
                        value = (value ^ -0x3c08bba373244c1eL) + 0x679cdb3fc8651b42L;
                        value2 = (value2 ^ 0x1409fb04c8397c1aL) - 0x1d18f9fb0c36dfaL;
                     }
                  } else {
                     countArray7 = new int[]{
                        99,
                        124,
                        119,
                        123,
                        242,
                        107,
                        111,
                        197,
                        48,
                        1,
                        103,
                        43,
                        254,
                        215,
                        171,
                        118,
                        202,
                        130,
                        201,
                        125,
                        250,
                        89,
                        71,
                        240,
                        173,
                        212,
                        162,
                        175,
                        156,
                        164,
                        114,
                        192,
                        183,
                        253,
                        147,
                        38,
                        54,
                        63,
                        247,
                        204,
                        52,
                        165,
                        229,
                        241,
                        113,
                        216,
                        49,
                        21,
                        4,
                        199,
                        35,
                        195,
                        24,
                        150,
                        5,
                        154,
                        7,
                        18,
                        128,
                        226,
                        235,
                        39,
                        178,
                        117,
                        9,
                        131,
                        44,
                        26,
                        27,
                        110,
                        90,
                        160,
                        82,
                        59,
                        214,
                        179,
                        41,
                        227,
                        47,
                        132,
                        83,
                        209,
                        0,
                        237,
                        32,
                        252,
                        177,
                        91,
                        106,
                        203,
                        190,
                        57,
                        74,
                        76,
                        88,
                        207,
                        208,
                        239,
                        170,
                        251,
                        67,
                        77,
                        51,
                        133,
                        69,
                        249,
                        2,
                        127,
                        80,
                        60,
                        159,
                        168,
                        81,
                        163,
                        64,
                        143,
                        146,
                        157,
                        56,
                        245,
                        188,
                        182,
                        218,
                        33,
                        16,
                        255,
                        243,
                        210,
                        205,
                        12,
                        19,
                        236,
                        95,
                        151,
                        68,
                        23,
                        196,
                        167,
                        126,
                        61,
                        100,
                        93,
                        25,
                        115,
                        96,
                        129,
                        79,
                        220,
                        34,
                        42,
                        144,
                        136,
                        70,
                        238,
                        184,
                        20,
                        222,
                        94,
                        11,
                        219,
                        224,
                        50,
                        58,
                        10,
                        73,
                        6,
                        36,
                        92,
                        194,
                        211,
                        172,
                        98,
                        145,
                        149,
                        228,
                        121,
                        231,
                        200,
                        55,
                        109,
                        141,
                        213,
                        78,
                        169,
                        108,
                        86,
                        244,
                        234,
                        101,
                        122,
                        174,
                        8,
                        186,
                        120,
                        37,
                        46,
                        28,
                        166,
                        180,
                        198,
                        232,
                        221,
                        116,
                        31,
                        75,
                        189,
                        139,
                        138,
                        112,
                        62,
                        181,
                        102,
                        72,
                        3,
                        246,
                        14,
                        97,
                        53,
                        87,
                        185,
                        134,
                        193,
                        29,
                        158,
                        225,
                        248,
                        152,
                        17,
                        105,
                        217,
                        142,
                        148,
                        155,
                        30,
                        135,
                        233,
                        206,
                        85,
                        40,
                        223,
                        140,
                        161,
                        137,
                        13,
                        191,
                        230,
                        66,
                        104,
                        65,
                        153,
                        45,
                        15,
                        176,
                        84,
                        187,
                        22
                     };
                     value = (value ^ -0x7a907a6de1f2aed9L) + 0x665cfcbf6eec6e89L;
                     value2 = (value2 ^ 0x26b15f93c82a6edbL) - 0x284cb81e879cdfc8L;
                  }
               } else {
                  count6 = 0;
                  value = (value ^ -0x26efad5024e91b5cL) + 0x73d9ffb375f9276eL;
                  value2 = (value2 ^ 0xeb091d729454e08L) - 0x7513b720929d00bdL;
               }
               continue;
            }

            int[] countArray8 = countArray[2];
            long value3;
            long value4;
            int count8;
            if ((count6 & 128) == 0) {
               value3 = (value ^ 0x4c266ca4c12d9bf1L) - 0x3c19db6440e36b91L;
               value4 = (value2 ^ -0x532bb1ff38793420L) + 0x6c2c31ff427ec6e0L;
               count8 = count6 << 1 & 0xFF;
            } else {
               count8 = (count6 << 1 ^ 27) & 0xFF;
               value3 = (value ^ 0x78b9a81eb9fac34eL) - 0x47b797ea481ac342L;
               value4 = (value2 ^ -0x6bff25283291e237L) + 0x7400a6344d161cb9L;
            }

            countArray8[count6] = count8;
            countArray[3][count6] = countArray[2][count6] ^ count6;
            countArray[9][count6] = countArray[2][countArray[2][countArray[2][count6]]] ^ count6;
            countArray[11][count6] = countArray[2][countArray[2][count6] ^ count6] ^ count6;
            countArray[13][count6] = countArray[2][countArray[2][count6 ^ count6]] ^ count6;
            countArray[14][count6] = countArray[2][countArray[2][count6 ^ count6] ^ count6];
            count6++;
            value = (value3 ^ -0x7f384dc74987e7d5L) + 0x77627e5c3295584eL;
            value2 = (value4 ^ 0x69d7ee800a31ec72L) + 0x36f2c10481528356L;
         }

         if (count6 >= 256) {
            break;
         }

         value = (value ^ 0x4620ad0c1588359L) + 0x4c5d6ac407a3d04L;
         value2 = (value2 ^ 0x5bc534b29b4ec9b3L) + 0x44ffe52df9b95f87L;
      }
   }

   public void encode(ChannelHandlerContext channelHandlerContext, Object entry, List list) throws Exception {
      ByteBuf byteBuf = (ByteBuf)entry;
      this.applyChannelHandlerContext(channelHandlerContext, byteBuf, list);
   }
}
