package client.util;

import client.setting.MovementWeightProperty;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import net.minecraft.item.ItemEntry133Entry;

public class EMfDf9lTUMessageToMessageDecoder extends MessageToMessageDecoder {
   public byte[] byteFlagArray45;
   public byte[] byteFlagArray113;
   public byte[] byteFlagArray80;
   public byte[] byteFlagArray6;
   public static final int[] countArray2;
   public byte[] byteFlagArray46;
   public byte[] byteFlagArray35;
   public byte[] byteFlagArray139;
   public byte[] byteFlagArray10;
   public static final int count5 = 10;
   public byte[] byteFlagArray73;
   public byte[] byteFlagArray93;
   public byte[] byteFlagArray49;
   public static final int count = 16;
   public byte[] byteFlagArray54;
   public byte[] byteFlagArray177;
   public byte[] byteFlagArray9;
   public byte[] byteFlagArray126;
   public byte[] byteFlagArray91;
   public byte[] byteFlagArray94;
   public byte[] byteFlagArray98;
   public byte[] byteFlagArray154;
   public byte[] byteFlagArray75;
   public ManagedByteArrayEntry managedByteArrayEntry;
   public byte[] byteFlagArray107;
   public byte[] byteFlagArray141;
   public byte[] byteFlagArray116;
   public static final int count3 = 16;
   public byte[] byteFlagArray84;
   public byte[] byteFlagArray109;
   public byte[] byteFlagArray188;
   public byte[] byteFlagArray42;
   public byte[] byteFlagArray83;
   public byte[] byteFlagArray50;
   public byte[] byteFlagArray119;
   public byte[] byteFlagArray165;
   public byte[] byteFlagArray151;
   public byte[] byteFlagArray104;
   public byte[] byteFlagArray168;
   public byte[] byteFlagArray147;
   public byte[] byteFlagArray41;
   public byte[] byteFlagArray158;
   public byte[] byteFlagArray29;
   public byte[] byteFlagArray;
   public byte[] byteFlagArray19;
   public byte[] byteFlagArray129;
   public final int[] countArray = new int[16];
   public byte[] byteFlagArray195;
   public byte[] byteFlagArray131;
   public byte[] byteFlagArray132;
   public byte[] byteFlagArray89;
   public static final int count4 = 12;
   public byte[] byteFlagArray115;
   public byte[] byteFlagArray33;
   public byte[] byteFlagArray26;
   public byte[] byteFlagArray200;
   public byte[] byteFlagArray95;
   public byte[] byteFlagArray87;
   public byte[] byteFlagArray2;
   public byte[] byteFlagArray137;
   public byte[] byteFlagArray172;
   public byte[] byteFlagArray47;
   public byte[] byteFlagArray8;
   public byte[] byteFlagArray21;
   public byte[] byteFlagArray114;
   public byte[] byteFlagArray25;
   public byte[] byteFlagArray27;
   public byte[] byteFlagArray157;
   public byte[] byteFlagArray171;
   public byte[] byteFlagArray44;
   public byte[] byteFlagArray117;
   public byte[] byteFlagArray166;
   public byte[] byteFlagArray192;
   public final int[] countArray5 = new int[16];
   public byte[] byteFlagArray69;
   public byte[] byteFlagArray15;
   public byte[] byteFlagArray92;
   public byte[] byteFlagArray78;
   public byte[] byteFlagArray204;
   public byte[] byteFlagArray24;
   public byte[] byteFlagArray108;
   public byte[] byteFlagArray143;
   public byte[] byteFlagArray190;
   public byte[] byteFlagArray140;
   public byte[] byteFlagArray186;
   public byte[] byteFlagArray138;
   public byte[] byteFlagArray53;
   public byte[] byteFlagArray167;
   public byte[] byteFlagArray112;
   public byte[] byteFlagArray198;
   public byte[] byteFlagArray30;
   public byte[] byteFlagArray96;
   public static final int[] countArray3;
   public byte[] byteFlagArray43;
   public byte[] byteFlagArray193;
   public byte[] byteFlagArray102;
   public byte[] byteFlagArray34;
   public byte[] byteFlagArray62;
   public byte[] byteFlagArray7;
   public byte[] byteFlagArray90;
   public final byte[] byteFlagArray105 = new byte[16];
   public byte[] byteFlagArray100;
   public byte[] byteFlagArray97;
   public byte[] byteFlagArray60;
   public byte[] byteFlagArray170;
   public byte[] byteFlagArray173;
   public byte[] byteFlagArray133;
   public byte[] byteFlagArray14;
   public byte[] byteFlagArray194;
   public byte[] byteFlagArray122;
   public byte[] byteFlagArray88;
   public byte[] byteFlagArray36;
   public byte[] byteFlagArray71;
   public byte[] byteFlagArray20;
   public byte[] byteFlagArray67;
   public static final int count2 = 14;
   public byte[] byteFlagArray142;
   public byte[] byteFlagArray82;
   public byte[] byteFlagArray178;
   public byte[] byteFlagArray40;
   public byte[] byteFlagArray32;
   public static final int[][] countArray4;
   public byte[] byteFlagArray4;
   public byte[] byteFlagArray174;
   public byte[] byteFlagArray182;
   public byte[] byteFlagArray74;
   public byte[] byteFlagArray181;
   public byte[] byteFlagArray59;
   public byte[] byteFlagArray23;
   public byte[] byteFlagArray70;
   public byte[] byteFlagArray161;
   public byte[] byteFlagArray189;
   public byte[] byteFlagArray103;
   public byte[] byteFlagArray77;
   public static final int[] countArray6;
   public byte[] byteFlagArray12;
   public byte[] byteFlagArray176;
   public byte[] byteFlagArray85;
   public byte[] byteFlagArray156;
   public byte[] byteFlagArray17;
   public byte[] byteFlagArray124;
   public byte[] byteFlagArray125;
   public byte[] byteFlagArray130;
   public byte[] byteFlagArray196;
   public byte[] byteFlagArray162;
   public final byte[] byteFlagArray184 = new byte[16];
   public byte[] byteFlagArray106;
   public byte[] byteFlagArray31;
   public byte[] byteFlagArray128;
   public byte[] byteFlagArray127;
   public byte[] byteFlagArray68;
   public byte[] byteFlagArray144;
   public byte[] byteFlagArray201;
   public byte[] byteFlagArray164;
   public byte[] byteFlagArray101;
   public byte[] byteFlagArray57;
   public byte[] byteFlagArray81;
   public byte[] byteFlagArray183;
   public byte[] byteFlagArray66;
   public byte[] byteFlagArray118;
   public byte[] byteFlagArray5;
   public byte[] byteFlagArray185;
   public byte[] byteFlagArray123;
   public byte[] byteFlagArray48;
   public byte[] byteFlagArray145;
   public byte[] byteFlagArray64;
   public byte[] byteFlagArray205;
   public byte[] byteFlagArray3;
   public byte[] byteFlagArray28;
   public byte[] byteFlagArray175;
   public final SpecialLongEntry specialLongEntry;
   public byte[] byteFlagArray149;
   public byte[] byteFlagArray76;
   public byte[] byteFlagArray110;
   public int[] countArray7;
   public byte[] byteFlagArray111;
   public byte[] byteFlagArray72;
   public byte[] byteFlagArray13;
   public byte[] byteFlagArray191;
   public byte[] byteFlagArray52;
   public byte[] byteFlagArray163;
   public byte[] byteFlagArray51;
   public byte[] byteFlagArray135;
   public byte[] byteFlagArray146;
   public byte[] byteFlagArray160;
   public byte[] byteFlagArray65;
   public byte[] byteFlagArray197;
   public byte[] byteFlagArray180;
   public byte[] byteFlagArray16;
   public byte[] byteFlagArray121;
   public byte[] byteFlagArray79;
   public byte[] byteFlagArray11;
   public byte[] byteFlagArray134;
   public byte[] byteFlagArray202;
   public byte[] byteFlagArray148;
   public byte[] byteFlagArray169;
   public byte[] byteFlagArray203;
   public byte[] byteFlagArray18;
   public byte[] byteFlagArray150;
   public byte[] byteFlagArray37;
   public byte[] byteFlagArray55;
   public byte[] byteFlagArray159;
   public byte[] byteFlagArray187;
   public byte[] byteFlagArray199;
   public byte[] byteFlagArray155;
   public byte[] byteFlagArray22;
   public byte[] byteFlagArray120;
   public byte[] byteFlagArray63;
   public byte[] byteFlagArray179;
   public byte[] byteFlagArray56;
   public byte[] byteFlagArray39;
   public byte[] byteFlagArray153;
   public byte[] byteFlagArray86;
   public byte[] byteFlagArray152;
   public byte[] byteFlagArray61;
   public byte[] byteFlagArray136;
   public byte[] byteFlagArray38;
   public byte[] byteFlagArray99;
   public byte[] byteFlagArray58;

   public void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
      list.add(this.resolveByteBuf(byteBuf));
   }

   private int[] resolveIndexArray(byte[] byteValueArray) {
      int byteValue = 0;
      int count6 = 0;
      int count7 = 0;
      int[] countArray8 = null;
      int count8 = 0;
      int count9 = 0;
      int[] countArray9 = null;
      long value = 0x3734f88acb8e22c6L;
      long value2 = -0x4e7a0a6f53e42ca9L;

      while (true) {
         int count10 = (int)(value % 43L);
         if (count10 != 23) {
            if (count10 != 19) {
               branchBlock:
               if (count10 != 14) {
                  if (count10 == 30) {
                     byteValue = 0;
                     value = (value ^ 0x201c2c377b25b0f8L) - 0x4c5ab58c002b6d8bL;
                     value2 = (value2 ^ -0x72541155083ce48aL) - 0x52c3e8a9c6ca522L;
                     continue;
                  }

                  if (count10 == 15) {
                     System.arraycopy(byteValueArray, 0, this.byteFlagArray15, 0, byteValueArray.length);
                     value = (value ^ -0x3f32c89d6e991c5bL) + 0x5db3c77381477831L;
                     value2 = (value2 ^ 0x4a6fab6207b32d43L) + 0x5cd637bc94cc2cecL;
                     continue;
                  }

                  scanBlock: {
                     evalBlock: {
                        checkBlock: {
                           guardBlock: {
                              long value3;
                              long value4;
                              searchBlock: {
                                 if (count10 != 29) {
                                    if (count10 == 36) {
                                       break scanBlock;
                                    }

                                    if (count10 == 26) {
                                       break checkBlock;
                                    }

                                    if (count10 == 38) {
                                       value = (value ^ 0x29057ff5a48ce198L) - 0x3a179b040919a62aL;
                                       value2 = (value2 ^ -0x1e30b8878e1e1a63L) + 0x3ee4c3c581280455L;
                                       break branchBlock;
                                    }

                                    if (count10 == 16) {
                                       break evalBlock;
                                    }

                                    if (count10 == 33) {
                                       countArray9[4 * count9 + 3] = countArray8[count9] & 0xFF;
                                       value = (value ^ 0x5d937ec3fd01a7d7L) + 0x5365a3ed984b3431L;
                                       value2 = (value2 ^ 0x5aa45de89924c756L) + 0x4975ce9fc8d655e4L;
                                       continue;
                                    }

                                    if (count10 == 2) {
                                       break branchBlock;
                                    }

                                    if (count10 == 41) {
                                       count8++;
                                       value = (value ^ 0x588f184a4a8c6ed7L) - 0x415e1358ea61642bL;
                                       value2 = (value2 ^ -0x5edcdca95db6e942L) - 0x69ef29090130fe2aL;
                                       break guardBlock;
                                    }

                                    if (count10 == 1) {
                                       count9 = 0;
                                       value = (value ^ -0x68e73c3b15142216L) - 0x349fa74747b358a9L;
                                       value2 = (value2 ^ -0xe2b05ad5ed1a1f6L) + 0x5d99741b23d7153fL;
                                       continue;
                                    }

                                    if (count10 == 10) {
                                       count9 = count9 << 8 & -256 | count9 >>> 24 & 0xFF;
                                       value = (value ^ 0xfe102ff65445496L) + 0x3a7de75630825675L;
                                       value2 = (value2 ^ 0x79c6a13b0e3be897L) + 0x1eca61c849b6536aL;
                                       continue;
                                    }

                                    if (count10 == 4) {
                                       countArray9[4 * count9] = countArray8[count9] >>> 24 & 0xFF;
                                       value = (value ^ -0x6c0f87ef5b0cf49fL) + 0x56b4bebf36fee473L;
                                       value2 = (value2 ^ -0x28bc478710e9502fL) + 0x6d2727581fd11850L;
                                       continue;
                                    }

                                    if (count10 == 22) {
                                       count9 = countArray8[count8 - 1];
                                       value = (value ^ -0x3431e21c1b7b8e73L) + 0x7b959fbda43cd9ddL;
                                       value2 = (value2 ^ 0x1d6e0c3bcbf50540L) - 0x69fa4346defe90dL;
                                       continue;
                                    }

                                    if (count10 == 35) {
                                       count9 ^= countArray2[count8 / count6] << 24;
                                       value = (value ^ 0x16dd322c81b1db48L) - 0x3168e6908cdaad97L;
                                       value2 = (value2 ^ -0x4526fd7716ba93c7L) - 0x51d58b9be3636e8cL;
                                       continue;
                                    }

                                    if (count10 == 20) {
                                       if (count8 % count6 != 0) {
                                          long value5 = (value ^ -0x192b4db7c94939cbL) + 0x58dedd4df7fdff27L;
                                          long value6 = (value2 ^ -0x15eabba850ed64e0L) - 0x5df7df2c69c17c3dL;
                                          if (count6 <= 6) {
                                             value = (value5 ^ 0x701f17942d6e4843L) + 0x1d3014834e87909fL;
                                             value2 = (value6 ^ 0x20d18fdb424f2cc3L) - 0x56775853e324a6acL;
                                          } else {
                                             value = (value5 ^ -0x736d1d5b85c934b0L) + 0x41f2646d1715ac76L;
                                             value2 = (value6 ^ 0x6e702ad17e831e4cL) + 0x4a9731691023ccc6L;
                                          }
                                       } else {
                                          value = (value ^ 0x468395bf2e325a95L) - 0x3e0748792c54a775L;
                                          value2 = (value2 ^ -0x3c340b0ccdade59bL) + 0x265127c9070b1858L;
                                       }
                                       continue;
                                    }

                                    if (count10 == 32) {
                                       count7 = 4 * (byteValue + 1);
                                       value = (value ^ -0xf52c1063160bb01L) - 0xb2516bdc329cd00L;
                                       value2 = (value2 ^ 0x18cc70c5f318b22aL) - 0x630815e0ff8d4080L;
                                       continue;
                                    }

                                    if (count10 == 9) {
                                       if (byteValueArray.length != this.byteFlagArray15.length) {
                                          value = (value ^ 0x18322bdd5b5b2769L) + 0x372725c65b19c417L;
                                          value2 = (value2 ^ 0x6fd2d2d1adcd4695L) + 0x58887859873ad17eL;
                                       } else {
                                          value = (value ^ 0x4f8dc9d4d90e44ddL) - 0x5016474e98e3bd3L;
                                          value2 = (value2 ^ 0x15af6fecb322b080L) - 0x3bdfbc6f555ebf39L;
                                       }
                                       continue;
                                    }

                                    if (count10 == 11) {
                                       countArray9[4 * count9 + 2] = countArray8[count9] >>> 8 & 0xFF;
                                       value = (value ^ -0x3b9da44a5dc64410L) - 0x63b1a6bc14555a6aL;
                                       value2 = (value2 ^ -0x39c17a2b52d2325fL) - 0xa56aea6b053f59cL;
                                       continue;
                                    }

                                    if (count10 == 27) {
                                       return countArray9;
                                    }

                                    if (count10 != 37) {
                                       if (count10 == 6) {
                                          byteValue = 10;
                                          value = (value ^ 0x41894fbedb7cd446L) - 0x39d479dfafef0d5L;
                                          value2 = (value2 ^ 0x3099c94c05d25642L) - 0x47802ced44045caaL;
                                          continue;
                                       }

                                       if (count10 == 39) {
                                          byteValue = 12;
                                          value = (value ^ -0x51bacce23e6f5ff5L) + 0x79167d363244c318L;
                                          value2 = (value2 ^ 0x7f5ac29f9478ff19L) + 0x12be2e257f32b98L;
                                          continue;
                                       }

                                       if (count10 == 28) {
                                          count9++;
                                          value = (value ^ 0x50e12ef6e247ddd5L) + 0x60a14f13d6689b0fL;
                                          value2 = (value2 ^ 0x1c2f2202713f1258L) + 0x299ad1954e61f1e8L;
                                          break evalBlock;
                                       }

                                       if (count10 == 17) {
                                          count6 = 0;
                                          value = (value ^ 0x509b05069597d472L) - 0x368d9a6fdb5f6844L;
                                          value2 = (value2 ^ -0x76e072b90d44ecc2L) + 0xb2357db5fd7473cL;
                                          continue;
                                       }

                                       if (count10 == 40) {
                                          if (this.byteFlagArray15 == null) {
                                             value = (value ^ -0x35dc105c20b882beL) + 0x3c8215cba52d3057L;
                                             value2 = (value2 ^ -0x4f7b613057667cffL) + 0x1b167dccc0ff55ebL;
                                          } else {
                                             value = (value ^ 0x42d14ec189052929L) - 0x5ba589583d031f42L;
                                             value2 = (value2 ^ -0x7f24ca93fe9f349eL) + 0x7afee106e6107a21L;
                                          }
                                          continue;
                                       }

                                       if (count10 == 0) {
                                          if (byteValueArray.length != 16) {
                                             long value7 = (value ^ 0x637d1190aff1113L) + 0x2885ac9941366114L;
                                             long value8 = (value2 ^ 0x4916317f81ac4d57L) - 0x72bb9d0b6845fdb7L;
                                             if (byteValueArray.length != 24) {
                                                long value9 = (value7 ^ -0x4895f0e46dcbcd24L) + 0x5aec467fe6cd14abL;
                                                long value10 = (value8 ^ -0x313b17f752006347L) - 0x4d02a859518591f5L;
                                                byteValue = 14;
                                                value = (value9 ^ -0x15840f1d591da409L) + 0x36e91d6f5d5ebdbeL;
                                                value2 = (value10 ^ -0x51ac489844d2c219L) - 0x2f655d8a7491d4ffL;
                                             } else {
                                                value = (value7 ^ -0xc0ec7a1e1935387L) - 0x2c5434de0f4e950dL;
                                                value2 = (value8 ^ -0x35a84568f57e4cafL) + 0x78fd08bb8780ed32L;
                                             }
                                          } else {
                                             value = (value ^ -0x2479a5928949bdc5L) + 0x4839470065015717L;
                                             value2 = (value2 ^ -0x7837fe5f2acf7a68L) + 0x7d7fb8ef098cf872L;
                                          }
                                          continue;
                                       }

                                       if (count10 == 7) {
                                          count8 = 0;
                                          value = (value ^ -0x5161602f751d3ca7L) - 0x613ffcb034358a05L;
                                          value2 = (value2 ^ 0x6ef48a4a30fac8f2L) + 0x40014f8c1bf56d08L;
                                          continue;
                                       }

                                       if (count10 == 31) {
                                          countArray9[4 * count9 + 1] = countArray8[count9] >>> 16 & 0xFF;
                                          value = (value ^ 0x274eed0e5d16a31fL) + 0x5df1733565df8945L;
                                          value2 = (value2 ^ 0x42ec1c2ab3e1f0d3L) + 0x3908d721131513d3L;
                                          continue;
                                       }

                                       if (count10 == 24) {
                                          if (count8 % count6 != 4) {
                                             value = (value ^ -0x1d422b72cdaa8a8dL) + 0x7176a9bb585f55edL;
                                             value2 = (value2 ^ -0x53a937c78452bf49L) + 0x78811944e6203db2L;
                                          } else {
                                             value = (value ^ -0x1c141cf81d471639L) + 0x1d1ed3be170af15eL;
                                             value2 = (value2 ^ 0x5da4b86c8ab67c2fL) + 0x4a4b40ff6a19a1f1L;
                                          }
                                          continue;
                                       }

                                       if (count10 == 5) {
                                          value = (value ^ 0x155f18f5fb9db53L) - 0x3404e322cbc7c2deL;
                                          value2 = (value2 ^ 0x624697475ae1e468L) - 0x7b587daa53452e82L;
                                          break branchBlock;
                                       }

                                       if (count10 == 42) {
                                          if (this.countArray7 == null) {
                                             value = (value ^ 0x19b54f67ff864772L) + 0x354e1a13623253f7L;
                                             value2 = (value2 ^ -0x54ac9c407f7914d1L) - 0x1ca85edbfacb747L;
                                             continue;
                                          }

                                          return this.countArray7;
                                       }

                                       if (count10 == 21) {
                                          value = (value ^ 0x1c8fdc92b76ca849L) + 0x19fdf85c889b0a9L;
                                          value2 = (value2 ^ -0x2235c67eaee2d38fL) - 0x5172e9f9f5d2a71eL;
                                          break scanBlock;
                                       }

                                       if (count10 == 3) {
                                          return this.countArray7;
                                       }

                                       if (count10 == 8) {
                                          count8++;
                                          value = (value ^ 0x5d534892295fe50fL) - 0x26aede198ee2d760L;
                                          value2 = (value2 ^ -0x5e756d7858f0f8b3L) - 0x17662e28e1305c5eL;
                                          break checkBlock;
                                       }

                                       if (count10 == 25) {
                                          break guardBlock;
                                       }

                                       if (count10 != 12) {
                                          if (count10 != 13) {
                                             if (count10 != 34) {
                                                byteValue = 1;
                                                value = (value ^ -0x4e8ea8401ee06c4eL) + 0x25fa03fc3fce6fb9L;
                                                value2 = (value2 ^ -0x16a8e6414f3dc0a2L) + 0x3273500c8635c0fdL;
                                             } else {
                                                countArray8[count8] = (byteValueArray[4 * count8] & 255) << 24
                                                   | (byteValueArray[4 * count8 + 1] & 255) << 16
                                                   | (byteValueArray[4 * count8 + 2] & 255) << 8
                                                   | byteValueArray[4 * count8 + 3] & 255;
                                                value = (value ^ -0x638d7fb3d1ba899eL) + 0x5aadc8bf6009387bL;
                                                value2 = (value2 ^ 0x1238d72c33064f25L) - 0x740f1f2dc8a22532L;
                                             }
                                          } else {
                                             countArray8 = new int[count7];
                                             value = (value ^ 0x67e97e189915fa6dL) + 0x1c33f440e043da1L;
                                             value2 = (value2 ^ 0x49229164c9b6ee01L) - 0x16bc3245103a010cL;
                                          }
                                          continue;
                                       }

                                       value3 = (value ^ -0x5f91dcfb8777e02eL) + 0x5fd5dcfbb878122eL;
                                       value4 = (value2 ^ -0x3d965b92ddb5c4dL) - 0x6c1d663a2e1c628dL;
                                       break searchBlock;
                                    }

                                    if (byteValueArray[count6] != this.byteFlagArray15[count6]) {
                                       value = (value ^ 0x3e0b04929a64606eL) + 0x2e36d300208eb1b0L;
                                       value2 = (value2 ^ -0x78af2de9633f0f2L) - 0x4328ca5fc82cba63L;
                                       continue;
                                    }

                                    long value11 = (value ^ 0x30352e76fde99874L) - 0x38358c3d043de3d9L;
                                    long value12 = (value2 ^ 0x3b2e198be0d6afe4L) - 0x7cd3d02aadd1d729L;
                                    count6++;
                                    value = (value11 ^ 0x7d6a07c28fd5f28eL) - 0x5038cc33a5add934L;
                                    value2 = (value12 ^ -0x4e90aa8d3a04f95bL) - 0x2c9661685fca2444L;
                                 }

                                 if (count6 < byteValueArray.length) {
                                    value = (value ^ -0x153496141837420cL) - 0x5481703b2f00caf9L;
                                    value2 = (value2 ^ -0x6378436229598667L) - 0x587bdf96be8033c3L;
                                    continue;
                                 }

                                 value3 = (value ^ -0x767c617a0897446cL) + 0x64952af701f8ef42L;
                                 value4 = (value2 ^ 0xdcb9246865f7248L) - 0x7cf9e3645d25a797L;
                              }

                              if (byteValue == 0) {
                                 value = (value3 ^ 0x79e83829d4521195L) - 0x3ac1034411fe38b6L;
                                 value2 = (value4 ^ 0x1eaa4508a24f9688L) + 0x44704a74f06ebbeeL;
                              } else {
                                 value = (value3 ^ -0x7d78ec71c3095bf0L) - 0x7136be187b0e007eL;
                                 value2 = (value4 ^ -0x498cf60387c6de51L) + 0x37323a63f6445933L;
                              }
                              continue;
                           }

                           if (count8 >= count6) {
                              long value13 = (value ^ 0x2b830e2e44b00b35L) + 0x2f6614e45b2557c7L;
                              long value14 = (value2 ^ -0x2cdefeea44e2d8b1L) - 0x410b71bfa24cf1bL;
                              count8 = count6;
                              value = (value13 ^ -0x689793483bef945dL) - 0x496b842904d6ee69L;
                              value2 = (value14 ^ 0x17c6f3fa0fc005ccL) + 0x6bb32ad8608356a5L;
                           } else {
                              value = (value ^ 0x11cb5026bf335ed2L) + 0x31ea1f8b0d9fe0fL;
                              value2 = (value2 ^ -0x424b77fbfdb9a6d4L) - 0x5c5759d2c9a966edL;
                           }
                           continue;
                        }

                        if (count8 >= count7) {
                           long value15 = (value ^ -0x64c1cdb1ee0ad7a6L) + 0x603453f154b64c8bL;
                           long value16 = (value2 ^ -0x53ed10bb0a86ba9bL) + 0x61f204e7eb5c95c6L;
                           countArray9 = new int[count7 * 4];
                           value = (value15 ^ -0x31ea7eafb1e02adaL) + 0x1b279d846b063655L;
                           value2 = (value16 ^ 0x244e496496846343L) - 0x2dee962b1948768cL;
                        } else {
                           value = (value ^ 0x69b8f4e4b0ce2a7bL) + 0x1f67c774e03f3673L;
                           value2 = (value2 ^ 0x209354d00e4b1f12L) - 0x1f7f671c1ad50630L;
                        }
                        continue;
                     }

                     if (count9 >= count7) {
                        long value17 = (value ^ 0x4b31404b12c75b71L) + 0x39ee8eb65597dc73L;
                        long value18 = (value2 ^ -0x3db3a51cbe261f5cL) - 0x77884d31dd77a394L;
                        this.byteFlagArray15 = new byte[byteValueArray.length];
                        value = (value17 ^ 0x16112506e17535abL) + 0x30aa41c97d0f62a6L;
                        value2 = (value18 ^ 0x76e6906545abb3bbL) + 0x50c9f7ec787f66afL;
                     } else {
                        value = (value ^ 0x498ab69878bc4e90L) + 0x550df0971ba42212L;
                        value2 = (value2 ^ -0x4df9c4b06c98b757L) - 0x1c72466bbca9e823L;
                     }
                     continue;
                  }

                  countArray8[count8] = countArray8[count8 - count6] ^ count9;
                  value = (value ^ -0x323becd0f0eb2597L) - 0x77880ec27063b070L;
                  value2 = (value2 ^ 0x7bda6ca4ff450a3dL) - 0x73304e15c8f10e89L;
                  continue;
               } else {
                  this.countArray7 = countArray9;
                  return countArray9;
               }

               count6 = byteValueArray.length / 4;
               value = (value ^ 0x228e1932b0248dd3L) + 0x4f0a760f2e30b863L;
               value2 = (value2 ^ 0x3e39fd6f70300f24L) - 0x2e6c67af1e72a524L;
            } else {
               count9 = countArray3[count9 >>> 24 & 0xFF] << 24
                  | countArray3[count9 >>> 16 & 0xFF] << 16
                  | countArray3[count9 >>> 8 & 0xFF] << 8
                  | countArray3[count9 & 0xFF];
               value = (value ^ 0x44c6fcfde42c1cb2L) + 0x11c4366e8d4bcdd5L;
               value2 = (value2 ^ -0x4342d3a99012cba6L) + 0x12fc43be6cdc63L;
            }
         } else {
            count9 = countArray3[count9 >>> 24 & 0xFF] << 24
               | countArray3[count9 >>> 16 & 0xFF] << 16
               | countArray3[count9 >>> 8 & 0xFF] << 8
               | countArray3[count9 & 0xFF];
            value = (value ^ -0x7e6ec536d1565c3L) - 0x4240f6679a185c05L;
            value2 = (value2 ^ -0x7f2b25e3bd975301L) + 0x785a3ee68f0475e3L;
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
            countArray8[index3] = countArray3[countArray8[index3]];
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
            countArray8[index4 * 4] = countArray4[2][count11] ^ countArray4[3][count12] ^ count13 ^ count14;
            countArray8[index4 * 4 + 1] = count11 ^ countArray4[2][count12] ^ countArray4[3][count13] ^ count14;
            countArray8[index4 * 4 + 2] = count11 ^ count12 ^ countArray4[2][count13] ^ countArray4[3][count14];
            countArray8[index4 * 4 + 3] = countArray4[3][count11] ^ count12 ^ count13 ^ countArray4[2][count14];
         }

         int count15 = index2 * 16;

         for (int index5 = 0; index5 < 16; index5++) {
            countArray8[index5] ^= countArray9[count15 + index5];
         }
      }

      for (int index6 = 0; index6 < 16; index6++) {
         countArray8[index6] = countArray3[countArray8[index6]];
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

   private void applyByteArray(byte[] byteValueArray) {
      long value = 0x3fbc3079674eL;
      value = 0x2c6c34768e3aL ^ value;
      CoreByteArrayEntry coreByteArrayEntry = null;
      long value2 = 0x3169514a28141035L;

      while (true) {
         int count7 = (int)(value2 % 102L);
         if (count7 == 44) {
            ItemEntry133Entry itemEntry133Entry = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray2 = new byte[]{
               51,
               30,
               -84,
               48,
               -47,
               -50,
               61,
               61,
               27,
               -112,
               89,
               -111,
               -15,
               -31,
               108,
               -56,
               -7,
               -106,
               -35,
               47,
               45,
               24,
               127,
               -61,
               41,
               110,
               -112,
               23,
               -52,
               -4,
               -54,
               -86
            };
            byte[] byteValueArray3 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray2, coreByteArrayEntry);
            this.byteFlagArray110 = itemEntry133Entry.resolveByteArray(byteValueArray3);
            value2 = (value2 ^ 0x18bdedcb8fd67ea5L) + 0x612854c65e80231fL;
         } else if (count7 == 89) {
            ItemEntry133Entry itemEntry133Entry2 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray4 = new byte[]{
               -29,
               -51,
               39,
               93,
               -48,
               -96,
               -125,
               -68,
               16,
               17,
               -15,
               -116,
               -65,
               61,
               -51,
               31,
               48,
               -117,
               11,
               15,
               -55,
               -47,
               22,
               -23,
               8,
               -27,
               78,
               -115,
               90,
               98,
               99,
               -49
            };
            byte[] byteValueArray5 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray4, coreByteArrayEntry);
            this.byteFlagArray124 = itemEntry133Entry2.resolveByteArray(byteValueArray5);
            value2 = (value2 ^ 0x30d4de63a781c316L) - 0xe04dd745486ecd8L;
         } else if (count7 == 66) {
            ItemEntry133Entry itemEntry133Entry3 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray6 = new byte[]{
               62,
               116,
               51,
               59,
               104,
               -88,
               -104,
               -32,
               -18,
               -106,
               71,
               121,
               71,
               -15,
               -126,
               -18,
               112,
               -50,
               105,
               80,
               -33,
               14,
               -43,
               38,
               -24,
               -15,
               70,
               -25,
               -35,
               66,
               -113,
               20
            };
            byte[] byteValueArray7 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray6, coreByteArrayEntry);
            this.byteFlagArray82 = itemEntry133Entry3.resolveByteArray(byteValueArray7);
            value2 = (value2 ^ -0x37b2344656a0bfe2L) - 0x73afb1cf788b2f70L;
         } else if (count7 == 90) {
            ItemEntry133Entry itemEntry133Entry4 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray8 = new byte[]{
               42,
               92,
               -126,
               73,
               54,
               16,
               -107,
               97,
               -118,
               126,
               -62,
               -4,
               82,
               -59,
               64,
               98,
               86,
               -59,
               -4,
               47,
               -37,
               -32,
               49,
               -26,
               -58,
               67,
               -37,
               113,
               -121,
               -87,
               119,
               -43
            };
            byte[] byteValueArray9 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray8, coreByteArrayEntry);
            this.byteFlagArray176 = itemEntry133Entry4.resolveByteArray(byteValueArray9);
            value2 = (value2 ^ -0x478fa0b2bc90a51aL) - 0x44db72f47f79e93dL;
         } else if (count7 == 41) {
            ItemEntry133Entry itemEntry133Entry5 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray10 = new byte[]{
               73,
               104,
               36,
               -61,
               110,
               -13,
               91,
               -69,
               -118,
               -61,
               24,
               18,
               -88,
               11,
               -103,
               -120,
               -125,
               -17,
               24,
               -91,
               -92,
               -126,
               -19,
               -42,
               -89,
               62,
               69,
               -19,
               -119,
               114,
               -16,
               35
            };
            byte[] byteValueArray11 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray10, coreByteArrayEntry);
            this.byteFlagArray76 = itemEntry133Entry5.resolveByteArray(byteValueArray11);
            value2 = (value2 ^ 0x49377ce36028d584L) - 0x94609859fe65df6L;
         } else if (count7 == 57) {
            ItemEntry133Entry itemEntry133Entry6 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray12 = new byte[]{
               96,
               -69,
               -88,
               -88,
               106,
               12,
               35,
               -75,
               -58,
               116,
               -78,
               -85,
               48,
               21,
               -97,
               106,
               28,
               38,
               -50,
               -118,
               106,
               -31,
               4,
               50,
               -73,
               -41,
               42,
               105,
               23,
               -51,
               -18,
               -128
            };
            byte[] byteValueArray13 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray12, coreByteArrayEntry);
            this.byteFlagArray187 = itemEntry133Entry6.resolveByteArray(byteValueArray13);
            value2 = (value2 ^ 0x33a77e7e5cbfe9e4L) + 0x74fca1580b9fa0d9L;
         } else if (count7 == 38) {
            ItemEntry133Entry itemEntry133Entry7 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray14 = new byte[]{
               116, 29, -120, -71, 13, 18, 126, -42, -31, 112, 28, 49, -107, 109, -24, 8, 102, 63, -62, 26, 78, -21, -124, 55, 97, 1, 123, 38, 113, 56, -64, -4
            };
            byte[] byteValueArray15 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray14, coreByteArrayEntry);
            this.byteFlagArray9 = itemEntry133Entry7.resolveByteArray(byteValueArray15);
            value2 = (value2 ^ 0x240d27b147bbf567L) - 0x1f6fd017e4495cd8L;
         } else if (count7 == 95) {
            ItemEntry133Entry itemEntry133Entry8 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray16 = new byte[]{
               -40,
               -88,
               61,
               -22,
               11,
               51,
               -22,
               35,
               -49,
               -5,
               -21,
               -44,
               -107,
               -26,
               2,
               -12,
               30,
               -53,
               -81,
               107,
               113,
               69,
               -116,
               7,
               92,
               -57,
               88,
               90,
               -33,
               16,
               -71,
               -28
            };
            byte[] byteValueArray17 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray16, coreByteArrayEntry);
            this.byteFlagArray99 = itemEntry133Entry8.resolveByteArray(byteValueArray17);
            value2 = (value2 ^ 0x3e8c5bddf0e9f886L) + 0x18a2af901dfc99abL;
         } else if (count7 == 74) {
            ItemEntry133Entry itemEntry133Entry9 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray18 = new byte[]{
               -97,
               -67,
               -125,
               123,
               69,
               57,
               -61,
               2,
               -38,
               -105,
               109,
               98,
               17,
               8,
               -88,
               -117,
               -93,
               17,
               14,
               94,
               -84,
               -38,
               -77,
               -113,
               28,
               76,
               119,
               -67,
               70,
               28,
               -49,
               51
            };
            byte[] byteValueArray19 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray18, coreByteArrayEntry);
            this.byteFlagArray42 = itemEntry133Entry9.resolveByteArray(byteValueArray19);
            value2 = (value2 ^ 0x35fb5dcfcb44692dL) + 0x3061bbce8386165fL;
         } else if (count7 == 63) {
            ItemEntry133Entry itemEntry133Entry10 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray20 = new byte[]{
               -18, 0, -84, -13, -77, 32, 89, -90, 62, -120, 66, -19, 73, 87, 14, 11, -120, 107, -117, -90, -48, 71, 70, 10, 89, 5, 89, 91, 111, 9, -2, 72
            };
            byte[] byteValueArray21 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray20, coreByteArrayEntry);
            this.byteFlagArray113 = itemEntry133Entry10.resolveByteArray(byteValueArray21);
            value2 = (value2 ^ -0x4717194740dd4e30L) - 0x3feea47292f71a66L;
         } else if (count7 == 30) {
            ItemEntry133Entry itemEntry133Entry11 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray22 = new byte[]{
               47,
               48,
               -58,
               -89,
               -58,
               23,
               -48,
               95,
               -21,
               -123,
               -46,
               -23,
               -28,
               -108,
               -99,
               -108,
               -65,
               53,
               -65,
               39,
               8,
               57,
               -59,
               -124,
               -99,
               -11,
               126,
               0,
               107,
               -13,
               81,
               -16
            };
            byte[] byteValueArray23 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray22, coreByteArrayEntry);
            this.byteFlagArray197 = itemEntry133Entry11.resolveByteArray(byteValueArray23);
            value2 = (value2 ^ -0x7e90f0983334de30L) + 0x7e98257b92f7c2b1L;
         } else if (count7 == 39) {
            ItemEntry133Entry itemEntry133Entry12 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray24 = new byte[]{
               -34, 45, 33, 43, 66, 64, -73, 1, -73, 37, 91, 40, 61, 81, 15, 102, -86, 87, 90, -21, 23, -12, -89, 106, -102, -56, 69, 104, 120, -126, 15, 39
            };
            byte[] byteValueArray25 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray24, coreByteArrayEntry);
            this.byteFlagArray171 = itemEntry133Entry12.resolveByteArray(byteValueArray25);
            value2 = (value2 ^ 0x1275d86a31b3490eL) + 0x9af4927c8a363c9L;
         } else if (count7 == 17) {
            ItemEntry133Entry itemEntry133Entry13 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray26 = new byte[]{
               63, -6, 80, -33, 62, -80, 49, 76, 94, 15, -3, 16, 18, 103, 36, 1, 81, -66, -64, -68, 36, -73, -93, -121, 62, -15, 93, -50, 81, 85, -75, -55
            };
            byte[] byteValueArray27 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray26, coreByteArrayEntry);
            this.byteFlagArray89 = itemEntry133Entry13.resolveByteArray(byteValueArray27);
            value2 = (value2 ^ 0x4d2f6b6fc2d25dc0L) - 0x1bdf621b78d02ae8L;
         } else if (count7 == 27) {
            ItemEntry133Entry itemEntry133Entry14 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray28 = new byte[]{
               15, -36, 52, 49, -43, -28, -66, 18, -127, 80, 107, 49, -89, -74, -17, 43, -86, 43, -3, 92, 24, -64, 100, -73, 54, 7, -43, 68, 31, -58, -56, -77
            };
            byte[] byteValueArray29 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray28, coreByteArrayEntry);
            this.byteFlagArray132 = itemEntry133Entry14.resolveByteArray(byteValueArray29);
            value2 = (value2 ^ -0x33850b65a479d821L) + 0x67604a2d0c824086L;
         } else if (count7 == 97) {
            ItemEntry133Entry itemEntry133Entry15 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray30 = new byte[]{
               -82,
               -123,
               14,
               -39,
               -86,
               -2,
               59,
               100,
               -80,
               -105,
               56,
               124,
               33,
               -126,
               86,
               82,
               119,
               -35,
               19,
               -36,
               99,
               51,
               -54,
               57,
               -28,
               125,
               125,
               -112,
               25,
               -19,
               16,
               -105
            };
            byte[] byteValueArray31 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray30, coreByteArrayEntry);
            this.byteFlagArray19 = itemEntry133Entry15.resolveByteArray(byteValueArray31);
            value2 = (value2 ^ 0x495a86df59ad7fdaL) + 0x25dce045aa225088L;
         } else if (count7 == 98) {
            ItemEntry133Entry itemEntry133Entry16 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray32 = new byte[]{
               -23,
               -15,
               -58,
               -46,
               127,
               27,
               -21,
               16,
               88,
               -78,
               123,
               -95,
               30,
               35,
               97,
               -90,
               -77,
               91,
               -90,
               -114,
               -23,
               -117,
               -52,
               -31,
               21,
               97,
               -75,
               -41,
               -3,
               -120,
               96,
               9
            };
            byte[] byteValueArray33 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray32, coreByteArrayEntry);
            this.byteFlagArray22 = itemEntry133Entry16.resolveByteArray(byteValueArray33);
            value2 = (value2 ^ -0x49eab11444f2c244L) + 0x5bba2d8c4aab54faL;
         } else if (count7 == 54) {
            ItemEntry133Entry itemEntry133Entry17 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray34 = new byte[]{
               0,
               -114,
               92,
               120,
               -67,
               45,
               -90,
               -29,
               59,
               -67,
               -92,
               75,
               57,
               -71,
               -64,
               -115,
               -88,
               -63,
               14,
               50,
               60,
               -13,
               -23,
               114,
               35,
               120,
               116,
               13,
               -104,
               69,
               107,
               -44
            };
            byte[] byteValueArray35 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray34, coreByteArrayEntry);
            this.byteFlagArray59 = itemEntry133Entry17.resolveByteArray(byteValueArray35);
            value2 = (value2 ^ -0x4f2aced5a6d7d003L) - 0x473a98cdeaa99475L;
         } else if (count7 == 34) {
            ItemEntry133Entry itemEntry133Entry18 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray36 = new byte[]{
               -50,
               -99,
               -45,
               -28,
               -112,
               -107,
               107,
               71,
               -46,
               -21,
               -2,
               -104,
               -45,
               51,
               -69,
               -86,
               20,
               -115,
               84,
               37,
               -3,
               110,
               52,
               86,
               -103,
               87,
               75,
               -28,
               43,
               53,
               -95,
               -48
            };
            byte[] byteValueArray37 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray36, coreByteArrayEntry);
            this.byteFlagArray91 = itemEntry133Entry18.resolveByteArray(byteValueArray37);
            value2 = (value2 ^ -0x5538eaf97df16500L) - 0x6580848b33538db7L;
         } else if (count7 == 43) {
            ItemEntry133Entry itemEntry133Entry19 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray38 = new byte[]{
               107,
               -119,
               50,
               -67,
               32,
               -39,
               89,
               45,
               -75,
               111,
               -65,
               -26,
               119,
               106,
               13,
               -118,
               -111,
               -51,
               -16,
               -61,
               -78,
               -40,
               -119,
               -20,
               -125,
               -22,
               36,
               76,
               4,
               -80,
               -67,
               -109
            };
            byte[] byteValueArray39 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray38, coreByteArrayEntry);
            this.byteFlagArray28 = itemEntry133Entry19.resolveByteArray(byteValueArray39);
            value2 = (value2 ^ 0x66c99b5b81fb5ff5L) + 0x1eccc76d9c01d9d0L;
         } else if (count7 == 36) {
            ItemEntry133Entry itemEntry133Entry20 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray40 = new byte[]{
               -32,
               20,
               -31,
               -22,
               -48,
               98,
               -44,
               -72,
               -5,
               -45,
               120,
               -29,
               -60,
               126,
               -27,
               121,
               20,
               117,
               25,
               -1,
               -6,
               118,
               91,
               90,
               34,
               -113,
               14,
               -106,
               -32,
               -70,
               -43,
               -17
            };
            byte[] byteValueArray41 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray40, coreByteArrayEntry);
            this.byteFlagArray135 = itemEntry133Entry20.resolveByteArray(byteValueArray41);
            value2 = (value2 ^ 0xd80d722f47d5e7eL) + 0x18afa78ddc855279L;
         } else if (count7 == 88) {
            ItemEntry133Entry itemEntry133Entry21 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray42 = new byte[]{
               -118,
               75,
               -88,
               106,
               39,
               114,
               23,
               -26,
               -46,
               89,
               -37,
               115,
               -127,
               106,
               20,
               60,
               32,
               63,
               19,
               -36,
               35,
               -114,
               -125,
               -51,
               -61,
               -103,
               44,
               26,
               -110,
               -69,
               -42,
               -106
            };
            byte[] byteValueArray43 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray42, coreByteArrayEntry);
            this.byteFlagArray204 = itemEntry133Entry21.resolveByteArray(byteValueArray43);
            value2 = (value2 ^ -0x552ecf275735ec07L) + 0x54f4284b6ec99d90L;
         } else if (count7 == 81) {
            coreByteArrayEntry = new CoreByteArrayEntry();
            value2 = (value2 ^ 0x6771cc8a8e09c13bL) + 0x82ecfe1bfc99caaL;
         } else if (count7 != 62) {
            if (count7 == 45) {
               ItemEntry133Entry itemEntry133Entry22 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
               byte[] byteValueArray44 = new byte[]{
                  -57,
                  6,
                  60,
                  -104,
                  -12,
                  -116,
                  -110,
                  90,
                  -117,
                  -35,
                  100,
                  -18,
                  65,
                  -110,
                  64,
                  59,
                  110,
                  -70,
                  78,
                  58,
                  -39,
                  103,
                  -40,
                  112,
                  -46,
                  -55,
                  -122,
                  -39,
                  28,
                  80,
                  54,
                  -28
               };
               byte[] byteValueArray45 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray44, coreByteArrayEntry);
               this.byteFlagArray94 = itemEntry133Entry22.resolveByteArray(byteValueArray45);
               break;
            }

            if (count7 != 52) {
               if (count7 != 58) {
                  if (count7 != 40) {
                     if (count7 != 23) {
                        if (count7 != 59) {
                           if (count7 != 1) {
                              if (count7 != 2) {
                                 if (count7 != 70) {
                                    if (count7 != 96) {
                                       if (count7 != 80) {
                                          if (count7 != 32) {
                                             if (count7 != 100) {
                                                if (count7 != 37) {
                                                   if (count7 != 60) {
                                                      if (count7 != 56) {
                                                         if (count7 != 99) {
                                                            if (count7 != 14) {
                                                               if (count7 != 0) {
                                                                  if (count7 != 24) {
                                                                     if (count7 != 13) {
                                                                        if (count7 != 3) {
                                                                           if (count7 != 5) {
                                                                              if (count7 == 76) {
                                                                                 ItemEntry133Entry itemEntry133Entry23 = new ItemEntry133Entry(
                                                                                    MovementWeightProperty.SHA3_256
                                                                                 );
                                                                                 byte[] byteValueArray46 = new byte[]{
                                                                                    -78,
                                                                                    115,
                                                                                    -98,
                                                                                    16,
                                                                                    -75,
                                                                                    124,
                                                                                    -41,
                                                                                    -93,
                                                                                    -112,
                                                                                    -99,
                                                                                    -99,
                                                                                    62,
                                                                                    -53,
                                                                                    47,
                                                                                    98,
                                                                                    122,
                                                                                    121,
                                                                                    -34,
                                                                                    56,
                                                                                    31,
                                                                                    -46,
                                                                                    -104,
                                                                                    51,
                                                                                    80,
                                                                                    108,
                                                                                    -70,
                                                                                    -84,
                                                                                    20,
                                                                                    107,
                                                                                    121,
                                                                                    32,
                                                                                    -90
                                                                                 };
                                                                                 byte[] byteValueArray47 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray46, coreByteArrayEntry);
                                                                                 this.byteFlagArray109 = itemEntry133Entry23.resolveByteArray(byteValueArray47);
                                                                                 value2 = (value2 ^ -0x2825ee69f349f35bL) + 0x7b4f02149d5fe49dL;
                                                                              } else if (count7 != 9) {
                                                                                 if (count7 != 46) {
                                                                                    if (count7 != 83) {
                                                                                       if (count7 != 73) {
                                                                                          if (count7 != 26) {
                                                                                             if (count7 != 86) {
                                                                                                if (count7 == 35) {
                                                                                                   ItemEntry133Entry itemEntry133Entry24 = new ItemEntry133Entry(
                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                   );
                                                                                                   byte[] byteValueArray48 = new byte[]{
                                                                                                      -105,
                                                                                                      -82,
                                                                                                      55,
                                                                                                      -68,
                                                                                                      108,
                                                                                                      46,
                                                                                                      6,
                                                                                                      63,
                                                                                                      -64,
                                                                                                      -106,
                                                                                                      118,
                                                                                                      -4,
                                                                                                      42,
                                                                                                      -65,
                                                                                                      -25,
                                                                                                      -78,
                                                                                                      -72,
                                                                                                      -88,
                                                                                                      -66,
                                                                                                      85,
                                                                                                      -105,
                                                                                                      119,
                                                                                                      -51,
                                                                                                      -88,
                                                                                                      -23,
                                                                                                      -75,
                                                                                                      40,
                                                                                                      94,
                                                                                                      96,
                                                                                                      -32,
                                                                                                      93,
                                                                                                      113
                                                                                                   };
                                                                                                   byte[] byteValueArray49 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                      byteValueArray, byteValueArray48, coreByteArrayEntry
                                                                                                   );
                                                                                                   this.byteFlagArray11 = itemEntry133Entry24.resolveByteArray(byteValueArray49);
                                                                                                   value2 = (value2 ^ -0x781433210ffb445dL)
                                                                                                      + 0x76b8a222e654c7dbL;
                                                                                                } else if (count7 != 11) {
                                                                                                   if (count7 != 92) {
                                                                                                      if (count7 != 25) {
                                                                                                         if (count7 != 8) {
                                                                                                            if (count7 == 75) {
                                                                                                               ItemEntry133Entry itemEntry133Entry25 = new ItemEntry133Entry(
                                                                                                                  MovementWeightProperty.SHA3_256
                                                                                                               );
                                                                                                               byte[] byteValueArray50 = new byte[]{
                                                                                                                  -44,
                                                                                                                  88,
                                                                                                                  -19,
                                                                                                                  -83,
                                                                                                                  -119,
                                                                                                                  96,
                                                                                                                  56,
                                                                                                                  48,
                                                                                                                  13,
                                                                                                                  87,
                                                                                                                  99,
                                                                                                                  -58,
                                                                                                                  -120,
                                                                                                                  -51,
                                                                                                                  39,
                                                                                                                  -75,
                                                                                                                  23,
                                                                                                                  89,
                                                                                                                  111,
                                                                                                                  103,
                                                                                                                  -81,
                                                                                                                  66,
                                                                                                                  65,
                                                                                                                  -50,
                                                                                                                  74,
                                                                                                                  -118,
                                                                                                                  -34,
                                                                                                                  107,
                                                                                                                  -118,
                                                                                                                  46,
                                                                                                                  -94,
                                                                                                                  37
                                                                                                               };
                                                                                                               byte[] byteValueArray51 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                  byteValueArray, byteValueArray50, coreByteArrayEntry
                                                                                                               );
                                                                                                               this.byteFlagArray20 = itemEntry133Entry25.resolveByteArray(
                                                                                                                  byteValueArray51
                                                                                                               );
                                                                                                               value2 = (value2 ^ 0x757b7a0b1f58f0bL)
                                                                                                                  - 0xaf2f2658c91fb15L;
                                                                                                            } else if (count7 != 33) {
                                                                                                               if (count7 != 53) {
                                                                                                                  if (count7 != 64) {
                                                                                                                     if (count7 != 67) {
                                                                                                                        if (count7 != 68) {
                                                                                                                           if (count7 != 18) {
                                                                                                                              if (count7 != 61) {
                                                                                                                                 if (count7 != 31) {
                                                                                                                                    if (count7 != 94) {
                                                                                                                                       if (count7 != 50) {
                                                                                                                                          if (count7 != 87) {
                                                                                                                                             if (count7 == 29) {
                                                                                                                                                ItemEntry133Entry itemEntry133Entry26 = new ItemEntry133Entry(
                                                                                                                                                   MovementWeightProperty.SHA3_256
                                                                                                                                                );
                                                                                                                                                byte[] byteValueArray52 = new byte[]{
                                                                                                                                                   -119,
                                                                                                                                                   -53,
                                                                                                                                                   1,
                                                                                                                                                   -91,
                                                                                                                                                   -2,
                                                                                                                                                   -3,
                                                                                                                                                   107,
                                                                                                                                                   126,
                                                                                                                                                   36,
                                                                                                                                                   44,
                                                                                                                                                   123,
                                                                                                                                                   -2,
                                                                                                                                                   -78,
                                                                                                                                                   13,
                                                                                                                                                   -9,
                                                                                                                                                   120,
                                                                                                                                                   86,
                                                                                                                                                   66,
                                                                                                                                                   121,
                                                                                                                                                   -41,
                                                                                                                                                   82,
                                                                                                                                                   33,
                                                                                                                                                   22,
                                                                                                                                                   -39,
                                                                                                                                                   -90,
                                                                                                                                                   83,
                                                                                                                                                   -37,
                                                                                                                                                   50,
                                                                                                                                                   72,
                                                                                                                                                   -112,
                                                                                                                                                   60,
                                                                                                                                                   -106
                                                                                                                                                };
                                                                                                                                                byte[] byteValueArray53 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                   byteValueArray,
                                                                                                                                                   byteValueArray52,
                                                                                                                                                   coreByteArrayEntry
                                                                                                                                                );
                                                                                                                                                this.byteFlagArray100 = itemEntry133Entry26.resolveByteArray(
                                                                                                                                                   byteValueArray53
                                                                                                                                                );
                                                                                                                                                value2 = (
                                                                                                                                                      value2
                                                                                                                                                         ^ 0x639c2f6da0ba13b0L
                                                                                                                                                   )
                                                                                                                                                   - 0x100cce8a24098a8dL;
                                                                                                                                             } else if (count7
                                                                                                                                                != 65) {
                                                                                                                                                if (count7 != 16
                                                                                                                                                   )
                                                                                                                                                 {
                                                                                                                                                   if (count7
                                                                                                                                                      != 21) {
                                                                                                                                                      if (count7
                                                                                                                                                         != 15) {
                                                                                                                                                         if (count7
                                                                                                                                                            != 12
                                                                                                                                                            )
                                                                                                                                                          {
                                                                                                                                                            if (count7
                                                                                                                                                               != 4
                                                                                                                                                               )
                                                                                                                                                             {
                                                                                                                                                               if (count7
                                                                                                                                                                  != 48
                                                                                                                                                                  )
                                                                                                                                                                {
                                                                                                                                                                  if (count7
                                                                                                                                                                     != 78
                                                                                                                                                                     )
                                                                                                                                                                   {
                                                                                                                                                                     if (count7
                                                                                                                                                                        != 10
                                                                                                                                                                        )
                                                                                                                                                                      {
                                                                                                                                                                        if (count7
                                                                                                                                                                           == 82
                                                                                                                                                                           )
                                                                                                                                                                         {
                                                                                                                                                                           ItemEntry133Entry itemEntry133Entry27 = new ItemEntry133Entry(
                                                                                                                                                                              MovementWeightProperty.SHA3_256
                                                                                                                                                                           );
                                                                                                                                                                           byte[] byteValueArray54 = new byte[]{
                                                                                                                                                                              41,
                                                                                                                                                                              16,
                                                                                                                                                                              116,
                                                                                                                                                                              -16,
                                                                                                                                                                              -3,
                                                                                                                                                                              85,
                                                                                                                                                                              -30,
                                                                                                                                                                              -39,
                                                                                                                                                                              -9,
                                                                                                                                                                              50,
                                                                                                                                                                              -88,
                                                                                                                                                                              -14,
                                                                                                                                                                              108,
                                                                                                                                                                              -112,
                                                                                                                                                                              36,
                                                                                                                                                                              -48,
                                                                                                                                                                              82,
                                                                                                                                                                              -25,
                                                                                                                                                                              70,
                                                                                                                                                                              -67,
                                                                                                                                                                              -99,
                                                                                                                                                                              16,
                                                                                                                                                                              35,
                                                                                                                                                                              102,
                                                                                                                                                                              -72,
                                                                                                                                                                              -69,
                                                                                                                                                                              -59,
                                                                                                                                                                              -79,
                                                                                                                                                                              -68,
                                                                                                                                                                              1,
                                                                                                                                                                              40,
                                                                                                                                                                              -80
                                                                                                                                                                           };
                                                                                                                                                                           byte[] byteValueArray55 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                              byteValueArray,
                                                                                                                                                                              byteValueArray54,
                                                                                                                                                                              coreByteArrayEntry
                                                                                                                                                                           );
                                                                                                                                                                           this.byteFlagArray170 = itemEntry133Entry27.resolveByteArray(
                                                                                                                                                                              byteValueArray55
                                                                                                                                                                           );
                                                                                                                                                                           value2 = (
                                                                                                                                                                                 value2
                                                                                                                                                                                    ^ -0x91ef4d578754e21L
                                                                                                                                                                              )
                                                                                                                                                                              + 0x5e37ae9c7b04281eL;
                                                                                                                                                                        } else if (count7
                                                                                                                                                                           != 79
                                                                                                                                                                           )
                                                                                                                                                                         {
                                                                                                                                                                           if (count7
                                                                                                                                                                              != 22
                                                                                                                                                                              )
                                                                                                                                                                            {
                                                                                                                                                                              if (count7
                                                                                                                                                                                 == 19
                                                                                                                                                                                 )
                                                                                                                                                                               {
                                                                                                                                                                                 ItemEntry133Entry itemEntry133Entry28 = new ItemEntry133Entry(
                                                                                                                                                                                    MovementWeightProperty.SHA3_256
                                                                                                                                                                                 );
                                                                                                                                                                                 byte[] byteValueArray56 = new byte[]{
                                                                                                                                                                                    39,
                                                                                                                                                                                    75,
                                                                                                                                                                                    127,
                                                                                                                                                                                    88,
                                                                                                                                                                                    82,
                                                                                                                                                                                    -88,
                                                                                                                                                                                    52,
                                                                                                                                                                                    -58,
                                                                                                                                                                                    -18,
                                                                                                                                                                                    27,
                                                                                                                                                                                    44,
                                                                                                                                                                                    -48,
                                                                                                                                                                                    -82,
                                                                                                                                                                                    127,
                                                                                                                                                                                    62,
                                                                                                                                                                                    -107,
                                                                                                                                                                                    20,
                                                                                                                                                                                    -14,
                                                                                                                                                                                    0,
                                                                                                                                                                                    93,
                                                                                                                                                                                    -105,
                                                                                                                                                                                    21,
                                                                                                                                                                                    -106,
                                                                                                                                                                                    13,
                                                                                                                                                                                    -38,
                                                                                                                                                                                    16,
                                                                                                                                                                                    98,
                                                                                                                                                                                    95,
                                                                                                                                                                                    -42,
                                                                                                                                                                                    103,
                                                                                                                                                                                    -22,
                                                                                                                                                                                    -15
                                                                                                                                                                                 };
                                                                                                                                                                                 byte[] byteValueArray57 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                    byteValueArray,
                                                                                                                                                                                    byteValueArray56,
                                                                                                                                                                                    coreByteArrayEntry
                                                                                                                                                                                 );
                                                                                                                                                                                 this.byteFlagArray97 = itemEntry133Entry28.resolveByteArray(
                                                                                                                                                                                    byteValueArray57
                                                                                                                                                                                 );
                                                                                                                                                                                 value2 = (
                                                                                                                                                                                       value2
                                                                                                                                                                                          ^ 0x5dd255e06ab46df2L
                                                                                                                                                                                    )
                                                                                                                                                                                    + 0x5854955cd3c2f9a2L;
                                                                                                                                                                              } else if (count7
                                                                                                                                                                                 != 72
                                                                                                                                                                                 )
                                                                                                                                                                               {
                                                                                                                                                                                 if (count7
                                                                                                                                                                                    != 7
                                                                                                                                                                                    )
                                                                                                                                                                                  {
                                                                                                                                                                                    if (count7
                                                                                                                                                                                       != 77
                                                                                                                                                                                       )
                                                                                                                                                                                     {
                                                                                                                                                                                       if (count7
                                                                                                                                                                                          != 69
                                                                                                                                                                                          )
                                                                                                                                                                                        {
                                                                                                                                                                                          if (count7
                                                                                                                                                                                             != 93
                                                                                                                                                                                             )
                                                                                                                                                                                           {
                                                                                                                                                                                             if (count7
                                                                                                                                                                                                != 42
                                                                                                                                                                                                )
                                                                                                                                                                                              {
                                                                                                                                                                                                if (count7
                                                                                                                                                                                                   != 20
                                                                                                                                                                                                   )
                                                                                                                                                                                                 {
                                                                                                                                                                                                   if (count7
                                                                                                                                                                                                      != 49
                                                                                                                                                                                                      )
                                                                                                                                                                                                    {
                                                                                                                                                                                                      if (count7
                                                                                                                                                                                                         != 71
                                                                                                                                                                                                         )
                                                                                                                                                                                                       {
                                                                                                                                                                                                         if (count7
                                                                                                                                                                                                            != 47
                                                                                                                                                                                                            )
                                                                                                                                                                                                          {
                                                                                                                                                                                                            if (count7
                                                                                                                                                                                                               == 55
                                                                                                                                                                                                               )
                                                                                                                                                                                                             {
                                                                                                                                                                                                               break;
                                                                                                                                                                                                            }

                                                                                                                                                                                                            if (count7
                                                                                                                                                                                                               != 28
                                                                                                                                                                                                               )
                                                                                                                                                                                                             {
                                                                                                                                                                                                               if (count7
                                                                                                                                                                                                                  != 85
                                                                                                                                                                                                                  )
                                                                                                                                                                                                                {
                                                                                                                                                                                                                  if (count7
                                                                                                                                                                                                                     != 101
                                                                                                                                                                                                                     )
                                                                                                                                                                                                                   {
                                                                                                                                                                                                                     if (count7
                                                                                                                                                                                                                        != 84
                                                                                                                                                                                                                        )
                                                                                                                                                                                                                      {
                                                                                                                                                                                                                        if (count7
                                                                                                                                                                                                                           != 51
                                                                                                                                                                                                                           )
                                                                                                                                                                                                                         {
                                                                                                                                                                                                                           if (count7
                                                                                                                                                                                                                              != 6
                                                                                                                                                                                                                              )
                                                                                                                                                                                                                            {
                                                                                                                                                                                                                              ItemEntry133Entry itemEntry133Entry29 = new ItemEntry133Entry(
                                                                                                                                                                                                                                 MovementWeightProperty.SHA3_256
                                                                                                                                                                                                                              );
                                                                                                                                                                                                                              byte[] byteValueArray58 = new byte[]{
                                                                                                                                                                                                                                 -121,
                                                                                                                                                                                                                                 -29,
                                                                                                                                                                                                                                 79,
                                                                                                                                                                                                                                 -111,
                                                                                                                                                                                                                                 6,
                                                                                                                                                                                                                                 91,
                                                                                                                                                                                                                                 71,
                                                                                                                                                                                                                                 -96,
                                                                                                                                                                                                                                 54,
                                                                                                                                                                                                                                 97,
                                                                                                                                                                                                                                 -26,
                                                                                                                                                                                                                                 18,
                                                                                                                                                                                                                                 78,
                                                                                                                                                                                                                                 105,
                                                                                                                                                                                                                                 69,
                                                                                                                                                                                                                                 9,
                                                                                                                                                                                                                                 105,
                                                                                                                                                                                                                                 -42,
                                                                                                                                                                                                                                 -59,
                                                                                                                                                                                                                                 -86,
                                                                                                                                                                                                                                 52,
                                                                                                                                                                                                                                 66,
                                                                                                                                                                                                                                 -70,
                                                                                                                                                                                                                                 114,
                                                                                                                                                                                                                                 -52,
                                                                                                                                                                                                                                 -28,
                                                                                                                                                                                                                                 -84,
                                                                                                                                                                                                                                 105,
                                                                                                                                                                                                                                 -72,
                                                                                                                                                                                                                                 -59,
                                                                                                                                                                                                                                 -31,
                                                                                                                                                                                                                                 -120
                                                                                                                                                                                                                              };
                                                                                                                                                                                                                              byte[] byteValueArray59 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                                                 byteValueArray,
                                                                                                                                                                                                                                 byteValueArray58,
                                                                                                                                                                                                                                 coreByteArrayEntry
                                                                                                                                                                                                                              );
                                                                                                                                                                                                                              this.byteFlagArray191 = itemEntry133Entry29.resolveByteArray(
                                                                                                                                                                                                                                 byteValueArray59
                                                                                                                                                                                                                              );
                                                                                                                                                                                                                              value2 = (
                                                                                                                                                                                                                                    value2
                                                                                                                                                                                                                                       ^ -0x90277403961dfbdL
                                                                                                                                                                                                                                 )
                                                                                                                                                                                                                                 + 0x49327e3cb06639baL;
                                                                                                                                                                                                                           } else {
                                                                                                                                                                                                                              ItemEntry133Entry itemEntry133Entry30 = new ItemEntry133Entry(
                                                                                                                                                                                                                                 MovementWeightProperty.SHA3_256
                                                                                                                                                                                                                              );
                                                                                                                                                                                                                              byte[] byteValueArray60 = new byte[]{
                                                                                                                                                                                                                                 50,
                                                                                                                                                                                                                                 -67,
                                                                                                                                                                                                                                 -91,
                                                                                                                                                                                                                                 -114,
                                                                                                                                                                                                                                 -45,
                                                                                                                                                                                                                                 98,
                                                                                                                                                                                                                                 -32,
                                                                                                                                                                                                                                 94,
                                                                                                                                                                                                                                 -59,
                                                                                                                                                                                                                                 -88,
                                                                                                                                                                                                                                 34,
                                                                                                                                                                                                                                 -35,
                                                                                                                                                                                                                                 -112,
                                                                                                                                                                                                                                 -52,
                                                                                                                                                                                                                                 65,
                                                                                                                                                                                                                                 -111,
                                                                                                                                                                                                                                 48,
                                                                                                                                                                                                                                 -105,
                                                                                                                                                                                                                                 58,
                                                                                                                                                                                                                                 17,
                                                                                                                                                                                                                                 127,
                                                                                                                                                                                                                                 -2,
                                                                                                                                                                                                                                 53,
                                                                                                                                                                                                                                 -110,
                                                                                                                                                                                                                                 47,
                                                                                                                                                                                                                                 -65,
                                                                                                                                                                                                                                 33,
                                                                                                                                                                                                                                 82,
                                                                                                                                                                                                                                 -24,
                                                                                                                                                                                                                                 86,
                                                                                                                                                                                                                                 -116,
                                                                                                                                                                                                                                 -94
                                                                                                                                                                                                                              };
                                                                                                                                                                                                                              byte[] byteValueArray61 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                                                 byteValueArray,
                                                                                                                                                                                                                                 byteValueArray60,
                                                                                                                                                                                                                                 coreByteArrayEntry
                                                                                                                                                                                                                              );
                                                                                                                                                                                                                              this.byteFlagArray63 = itemEntry133Entry30.resolveByteArray(
                                                                                                                                                                                                                                 byteValueArray61
                                                                                                                                                                                                                              );
                                                                                                                                                                                                                              value2 = (
                                                                                                                                                                                                                                    value2
                                                                                                                                                                                                                                       ^ -0x9a38179e6a1e67L
                                                                                                                                                                                                                                 )
                                                                                                                                                                                                                                 + 0x43291bab4c81279fL;
                                                                                                                                                                                                                           }
                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                           ItemEntry133Entry itemEntry133Entry31 = new ItemEntry133Entry(
                                                                                                                                                                                                                              MovementWeightProperty.SHA3_256
                                                                                                                                                                                                                           );
                                                                                                                                                                                                                           byte[] byteValueArray62 = new byte[]{
                                                                                                                                                                                                                              74,
                                                                                                                                                                                                                              -72,
                                                                                                                                                                                                                              -80,
                                                                                                                                                                                                                              -115,
                                                                                                                                                                                                                              -70,
                                                                                                                                                                                                                              -23,
                                                                                                                                                                                                                              -23,
                                                                                                                                                                                                                              100,
                                                                                                                                                                                                                              -28,
                                                                                                                                                                                                                              -68,
                                                                                                                                                                                                                              -89,
                                                                                                                                                                                                                              -87,
                                                                                                                                                                                                                              29,
                                                                                                                                                                                                                              -33,
                                                                                                                                                                                                                              -4,
                                                                                                                                                                                                                              111,
                                                                                                                                                                                                                              -56,
                                                                                                                                                                                                                              -120,
                                                                                                                                                                                                                              -65,
                                                                                                                                                                                                                              41,
                                                                                                                                                                                                                              -42,
                                                                                                                                                                                                                              -96,
                                                                                                                                                                                                                              67,
                                                                                                                                                                                                                              -46,
                                                                                                                                                                                                                              39,
                                                                                                                                                                                                                              40,
                                                                                                                                                                                                                              -6,
                                                                                                                                                                                                                              57,
                                                                                                                                                                                                                              -34,
                                                                                                                                                                                                                              4,
                                                                                                                                                                                                                              -110,
                                                                                                                                                                                                                              -69
                                                                                                                                                                                                                           };
                                                                                                                                                                                                                           byte[] byteValueArray63 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                                              byteValueArray,
                                                                                                                                                                                                                              byteValueArray62,
                                                                                                                                                                                                                              coreByteArrayEntry
                                                                                                                                                                                                                           );
                                                                                                                                                                                                                           this.byteFlagArray44 = itemEntry133Entry31.resolveByteArray(
                                                                                                                                                                                                                              byteValueArray63
                                                                                                                                                                                                                           );
                                                                                                                                                                                                                           value2 = (
                                                                                                                                                                                                                                 value2
                                                                                                                                                                                                                                    ^ 0x43a268ebbfe495d2L
                                                                                                                                                                                                                              )
                                                                                                                                                                                                                              - 0x189cba6fb4650ba8L;
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                     } else {
                                                                                                                                                                                                                        ItemEntry133Entry itemEntry133Entry32 = new ItemEntry133Entry(
                                                                                                                                                                                                                           MovementWeightProperty.SHA3_256
                                                                                                                                                                                                                        );
                                                                                                                                                                                                                        byte[] byteValueArray64 = new byte[]{
                                                                                                                                                                                                                           69,
                                                                                                                                                                                                                           31,
                                                                                                                                                                                                                           -42,
                                                                                                                                                                                                                           -60,
                                                                                                                                                                                                                           -19,
                                                                                                                                                                                                                           24,
                                                                                                                                                                                                                           -60,
                                                                                                                                                                                                                           108,
                                                                                                                                                                                                                           18,
                                                                                                                                                                                                                           75,
                                                                                                                                                                                                                           38,
                                                                                                                                                                                                                           -77,
                                                                                                                                                                                                                           109,
                                                                                                                                                                                                                           91,
                                                                                                                                                                                                                           -97,
                                                                                                                                                                                                                           104,
                                                                                                                                                                                                                           4,
                                                                                                                                                                                                                           116,
                                                                                                                                                                                                                           -107,
                                                                                                                                                                                                                           -120,
                                                                                                                                                                                                                           -43,
                                                                                                                                                                                                                           -61,
                                                                                                                                                                                                                           21,
                                                                                                                                                                                                                           -85,
                                                                                                                                                                                                                           111,
                                                                                                                                                                                                                           122,
                                                                                                                                                                                                                           -64,
                                                                                                                                                                                                                           -28,
                                                                                                                                                                                                                           -11,
                                                                                                                                                                                                                           75,
                                                                                                                                                                                                                           -28,
                                                                                                                                                                                                                           63
                                                                                                                                                                                                                        };
                                                                                                                                                                                                                        byte[] byteValueArray65 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                                           byteValueArray,
                                                                                                                                                                                                                           byteValueArray64,
                                                                                                                                                                                                                           coreByteArrayEntry
                                                                                                                                                                                                                        );
                                                                                                                                                                                                                        this.byteFlagArray12 = itemEntry133Entry32.resolveByteArray(
                                                                                                                                                                                                                           byteValueArray65
                                                                                                                                                                                                                        );
                                                                                                                                                                                                                        value2 = (
                                                                                                                                                                                                                              value2
                                                                                                                                                                                                                                 ^ -0x35ed5142256c616aL
                                                                                                                                                                                                                           )
                                                                                                                                                                                                                           - 0x2b13791b836d2ecfL;
                                                                                                                                                                                                                     }
                                                                                                                                                                                                                  } else {
                                                                                                                                                                                                                     ItemEntry133Entry itemEntry133Entry33 = new ItemEntry133Entry(
                                                                                                                                                                                                                        MovementWeightProperty.SHA3_256
                                                                                                                                                                                                                     );
                                                                                                                                                                                                                     byte[] byteValueArray66 = new byte[]{
                                                                                                                                                                                                                        -31,
                                                                                                                                                                                                                        23,
                                                                                                                                                                                                                        50,
                                                                                                                                                                                                                        81,
                                                                                                                                                                                                                        -90,
                                                                                                                                                                                                                        -96,
                                                                                                                                                                                                                        77,
                                                                                                                                                                                                                        -80,
                                                                                                                                                                                                                        100,
                                                                                                                                                                                                                        -27,
                                                                                                                                                                                                                        29,
                                                                                                                                                                                                                        -8,
                                                                                                                                                                                                                        -19,
                                                                                                                                                                                                                        -91,
                                                                                                                                                                                                                        -72,
                                                                                                                                                                                                                        -47,
                                                                                                                                                                                                                        -98,
                                                                                                                                                                                                                        111,
                                                                                                                                                                                                                        -36,
                                                                                                                                                                                                                        -24,
                                                                                                                                                                                                                        -40,
                                                                                                                                                                                                                        37,
                                                                                                                                                                                                                        80,
                                                                                                                                                                                                                        127,
                                                                                                                                                                                                                        123,
                                                                                                                                                                                                                        -22,
                                                                                                                                                                                                                        -63,
                                                                                                                                                                                                                        99,
                                                                                                                                                                                                                        76,
                                                                                                                                                                                                                        87,
                                                                                                                                                                                                                        17,
                                                                                                                                                                                                                        58
                                                                                                                                                                                                                     };
                                                                                                                                                                                                                     byte[] byteValueArray67 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                                        byteValueArray,
                                                                                                                                                                                                                        byteValueArray66,
                                                                                                                                                                                                                        coreByteArrayEntry
                                                                                                                                                                                                                     );
                                                                                                                                                                                                                     this.byteFlagArray98 = itemEntry133Entry33.resolveByteArray(
                                                                                                                                                                                                                        byteValueArray67
                                                                                                                                                                                                                     );
                                                                                                                                                                                                                     value2 = (
                                                                                                                                                                                                                           value2
                                                                                                                                                                                                                              ^ -0x157b3f5ea0ac29cbL
                                                                                                                                                                                                                        )
                                                                                                                                                                                                                        - 0x386b5cf83a35cd9cL;
                                                                                                                                                                                                                  }
                                                                                                                                                                                                               } else {
                                                                                                                                                                                                                  ItemEntry133Entry itemEntry133Entry34 = new ItemEntry133Entry(
                                                                                                                                                                                                                     MovementWeightProperty.SHA3_256
                                                                                                                                                                                                                  );
                                                                                                                                                                                                                  byte[] byteValueArray68 = new byte[]{
                                                                                                                                                                                                                     73,
                                                                                                                                                                                                                     13,
                                                                                                                                                                                                                     -61,
                                                                                                                                                                                                                     24,
                                                                                                                                                                                                                     -88,
                                                                                                                                                                                                                     -67,
                                                                                                                                                                                                                     -30,
                                                                                                                                                                                                                     69,
                                                                                                                                                                                                                     -82,
                                                                                                                                                                                                                     -31,
                                                                                                                                                                                                                     -106,
                                                                                                                                                                                                                     126,
                                                                                                                                                                                                                     -7,
                                                                                                                                                                                                                     19,
                                                                                                                                                                                                                     -67,
                                                                                                                                                                                                                     -83,
                                                                                                                                                                                                                     -34,
                                                                                                                                                                                                                     -22,
                                                                                                                                                                                                                     49,
                                                                                                                                                                                                                     1,
                                                                                                                                                                                                                     -29,
                                                                                                                                                                                                                     -56,
                                                                                                                                                                                                                     79,
                                                                                                                                                                                                                     -94,
                                                                                                                                                                                                                     40,
                                                                                                                                                                                                                     -61,
                                                                                                                                                                                                                     60,
                                                                                                                                                                                                                     -12,
                                                                                                                                                                                                                     -73,
                                                                                                                                                                                                                     55,
                                                                                                                                                                                                                     -61,
                                                                                                                                                                                                                     13
                                                                                                                                                                                                                  };
                                                                                                                                                                                                                  byte[] byteValueArray69 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                                     byteValueArray,
                                                                                                                                                                                                                     byteValueArray68,
                                                                                                                                                                                                                     coreByteArrayEntry
                                                                                                                                                                                                                  );
                                                                                                                                                                                                                  this.byteFlagArray66 = itemEntry133Entry34.resolveByteArray(
                                                                                                                                                                                                                     byteValueArray69
                                                                                                                                                                                                                  );
                                                                                                                                                                                                                  value2 = (
                                                                                                                                                                                                                        value2
                                                                                                                                                                                                                           ^ -0x6e64c542afd589d6L
                                                                                                                                                                                                                     )
                                                                                                                                                                                                                     - 0x75488275481ea87eL;
                                                                                                                                                                                                               }
                                                                                                                                                                                                            } else {
                                                                                                                                                                                                               ItemEntry133Entry itemEntry133Entry35 = new ItemEntry133Entry(
                                                                                                                                                                                                                  MovementWeightProperty.SHA3_256
                                                                                                                                                                                                               );
                                                                                                                                                                                                               byte[] byteValueArray70 = new byte[]{
                                                                                                                                                                                                                  101,
                                                                                                                                                                                                                  -61,
                                                                                                                                                                                                                  -33,
                                                                                                                                                                                                                  -109,
                                                                                                                                                                                                                  48,
                                                                                                                                                                                                                  -49,
                                                                                                                                                                                                                  34,
                                                                                                                                                                                                                  83,
                                                                                                                                                                                                                  -125,
                                                                                                                                                                                                                  -76,
                                                                                                                                                                                                                  26,
                                                                                                                                                                                                                  22,
                                                                                                                                                                                                                  91,
                                                                                                                                                                                                                  -29,
                                                                                                                                                                                                                  -104,
                                                                                                                                                                                                                  40,
                                                                                                                                                                                                                  81,
                                                                                                                                                                                                                  -50,
                                                                                                                                                                                                                  56,
                                                                                                                                                                                                                  90,
                                                                                                                                                                                                                  -60,
                                                                                                                                                                                                                  -21,
                                                                                                                                                                                                                  -123,
                                                                                                                                                                                                                  -12,
                                                                                                                                                                                                                  1,
                                                                                                                                                                                                                  53,
                                                                                                                                                                                                                  -46,
                                                                                                                                                                                                                  118,
                                                                                                                                                                                                                  36,
                                                                                                                                                                                                                  -74,
                                                                                                                                                                                                                  -5,
                                                                                                                                                                                                                  -121
                                                                                                                                                                                                               };
                                                                                                                                                                                                               byte[] byteValueArray71 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                                  byteValueArray,
                                                                                                                                                                                                                  byteValueArray70,
                                                                                                                                                                                                                  coreByteArrayEntry
                                                                                                                                                                                                               );
                                                                                                                                                                                                               this.byteFlagArray68 = itemEntry133Entry35.resolveByteArray(
                                                                                                                                                                                                                  byteValueArray71
                                                                                                                                                                                                               );
                                                                                                                                                                                                               value2 = (
                                                                                                                                                                                                                     value2
                                                                                                                                                                                                                        ^ -0x2a23397f09ec72b5L
                                                                                                                                                                                                                  )
                                                                                                                                                                                                                  - 0x69bbd729d90e23cL;
                                                                                                                                                                                                            }
                                                                                                                                                                                                         } else {
                                                                                                                                                                                                            ItemEntry133Entry itemEntry133Entry36 = new ItemEntry133Entry(
                                                                                                                                                                                                               MovementWeightProperty.SHA3_256
                                                                                                                                                                                                            );
                                                                                                                                                                                                            byte[] byteValueArray72 = new byte[]{
                                                                                                                                                                                                               -54,
                                                                                                                                                                                                               120,
                                                                                                                                                                                                               -41,
                                                                                                                                                                                                               113,
                                                                                                                                                                                                               30,
                                                                                                                                                                                                               -45,
                                                                                                                                                                                                               20,
                                                                                                                                                                                                               -23,
                                                                                                                                                                                                               20,
                                                                                                                                                                                                               3,
                                                                                                                                                                                                               -93,
                                                                                                                                                                                                               -82,
                                                                                                                                                                                                               -12,
                                                                                                                                                                                                               -21,
                                                                                                                                                                                                               124,
                                                                                                                                                                                                               -108,
                                                                                                                                                                                                               60,
                                                                                                                                                                                                               -107,
                                                                                                                                                                                                               -42,
                                                                                                                                                                                                               -106,
                                                                                                                                                                                                               36,
                                                                                                                                                                                                               112,
                                                                                                                                                                                                               -66,
                                                                                                                                                                                                               -127,
                                                                                                                                                                                                               58,
                                                                                                                                                                                                               -14,
                                                                                                                                                                                                               10,
                                                                                                                                                                                                               -104,
                                                                                                                                                                                                               -1,
                                                                                                                                                                                                               14,
                                                                                                                                                                                                               -118,
                                                                                                                                                                                                               -1
                                                                                                                                                                                                            };
                                                                                                                                                                                                            byte[] byteValueArray73 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                               byteValueArray,
                                                                                                                                                                                                               byteValueArray72,
                                                                                                                                                                                                               coreByteArrayEntry
                                                                                                                                                                                                            );
                                                                                                                                                                                                            this.byteFlagArray141 = itemEntry133Entry36.resolveByteArray(
                                                                                                                                                                                                               byteValueArray73
                                                                                                                                                                                                            );
                                                                                                                                                                                                            value2 = (
                                                                                                                                                                                                                  value2
                                                                                                                                                                                                                     ^ -0x4b359342a344cf5fL
                                                                                                                                                                                                               )
                                                                                                                                                                                                               + 0x60c16cc68abd506dL;
                                                                                                                                                                                                         }
                                                                                                                                                                                                      } else {
                                                                                                                                                                                                         ItemEntry133Entry itemEntry133Entry37 = new ItemEntry133Entry(
                                                                                                                                                                                                            MovementWeightProperty.SHA3_256
                                                                                                                                                                                                         );
                                                                                                                                                                                                         byte[] byteValueArray74 = new byte[]{
                                                                                                                                                                                                            86,
                                                                                                                                                                                                            -49,
                                                                                                                                                                                                            117,
                                                                                                                                                                                                            122,
                                                                                                                                                                                                            83,
                                                                                                                                                                                                            113,
                                                                                                                                                                                                            121,
                                                                                                                                                                                                            105,
                                                                                                                                                                                                            126,
                                                                                                                                                                                                            103,
                                                                                                                                                                                                            -43,
                                                                                                                                                                                                            61,
                                                                                                                                                                                                            16,
                                                                                                                                                                                                            0,
                                                                                                                                                                                                            117,
                                                                                                                                                                                                            -80,
                                                                                                                                                                                                            119,
                                                                                                                                                                                                            116,
                                                                                                                                                                                                            77,
                                                                                                                                                                                                            -27,
                                                                                                                                                                                                            -88,
                                                                                                                                                                                                            -2,
                                                                                                                                                                                                            3,
                                                                                                                                                                                                            -62,
                                                                                                                                                                                                            93,
                                                                                                                                                                                                            37,
                                                                                                                                                                                                            -83,
                                                                                                                                                                                                            5,
                                                                                                                                                                                                            -76,
                                                                                                                                                                                                            34,
                                                                                                                                                                                                            -65,
                                                                                                                                                                                                            -70
                                                                                                                                                                                                         };
                                                                                                                                                                                                         byte[] byteValueArray75 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                            byteValueArray,
                                                                                                                                                                                                            byteValueArray74,
                                                                                                                                                                                                            coreByteArrayEntry
                                                                                                                                                                                                         );
                                                                                                                                                                                                         this.byteFlagArray6 = itemEntry133Entry37.resolveByteArray(
                                                                                                                                                                                                            byteValueArray75
                                                                                                                                                                                                         );
                                                                                                                                                                                                         value2 = (
                                                                                                                                                                                                               value2
                                                                                                                                                                                                                  ^ -0x2d9bb762fa121092L
                                                                                                                                                                                                            )
                                                                                                                                                                                                            + 0x58dd34b2fc80faf4L;
                                                                                                                                                                                                      }
                                                                                                                                                                                                   } else {
                                                                                                                                                                                                      ItemEntry133Entry itemEntry133Entry38 = new ItemEntry133Entry(
                                                                                                                                                                                                         MovementWeightProperty.SHA3_256
                                                                                                                                                                                                      );
                                                                                                                                                                                                      byte[] byteValueArray76 = new byte[]{
                                                                                                                                                                                                         -28,
                                                                                                                                                                                                         -72,
                                                                                                                                                                                                         -118,
                                                                                                                                                                                                         70,
                                                                                                                                                                                                         126,
                                                                                                                                                                                                         -126,
                                                                                                                                                                                                         -44,
                                                                                                                                                                                                         -100,
                                                                                                                                                                                                         46,
                                                                                                                                                                                                         -94,
                                                                                                                                                                                                         -67,
                                                                                                                                                                                                         -38,
                                                                                                                                                                                                         -40,
                                                                                                                                                                                                         6,
                                                                                                                                                                                                         47,
                                                                                                                                                                                                         103,
                                                                                                                                                                                                         -2,
                                                                                                                                                                                                         4,
                                                                                                                                                                                                         123,
                                                                                                                                                                                                         -30,
                                                                                                                                                                                                         -8,
                                                                                                                                                                                                         -94,
                                                                                                                                                                                                         71,
                                                                                                                                                                                                         45,
                                                                                                                                                                                                         15,
                                                                                                                                                                                                         -40,
                                                                                                                                                                                                         13,
                                                                                                                                                                                                         82,
                                                                                                                                                                                                         -30,
                                                                                                                                                                                                         61,
                                                                                                                                                                                                         95,
                                                                                                                                                                                                         57
                                                                                                                                                                                                      };
                                                                                                                                                                                                      byte[] byteValueArray77 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                         byteValueArray,
                                                                                                                                                                                                         byteValueArray76,
                                                                                                                                                                                                         coreByteArrayEntry
                                                                                                                                                                                                      );
                                                                                                                                                                                                      this.byteFlagArray25 = itemEntry133Entry38.resolveByteArray(
                                                                                                                                                                                                         byteValueArray77
                                                                                                                                                                                                      );
                                                                                                                                                                                                      value2 = (
                                                                                                                                                                                                            value2
                                                                                                                                                                                                               ^ -0x3ab22cdf4f7f6850L
                                                                                                                                                                                                         )
                                                                                                                                                                                                         - 0x78e9e76a0d22c4efL;
                                                                                                                                                                                                   }
                                                                                                                                                                                                } else {
                                                                                                                                                                                                   ItemEntry133Entry itemEntry133Entry39 = new ItemEntry133Entry(
                                                                                                                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                                                                                                                   );
                                                                                                                                                                                                   byte[] byteValueArray78 = new byte[]{
                                                                                                                                                                                                      126,
                                                                                                                                                                                                      8,
                                                                                                                                                                                                      -42,
                                                                                                                                                                                                      31,
                                                                                                                                                                                                      38,
                                                                                                                                                                                                      41,
                                                                                                                                                                                                      5,
                                                                                                                                                                                                      86,
                                                                                                                                                                                                      64,
                                                                                                                                                                                                      114,
                                                                                                                                                                                                      97,
                                                                                                                                                                                                      -109,
                                                                                                                                                                                                      -42,
                                                                                                                                                                                                      66,
                                                                                                                                                                                                      82,
                                                                                                                                                                                                      32,
                                                                                                                                                                                                      -40,
                                                                                                                                                                                                      -117,
                                                                                                                                                                                                      -16,
                                                                                                                                                                                                      -122,
                                                                                                                                                                                                      56,
                                                                                                                                                                                                      125,
                                                                                                                                                                                                      -102,
                                                                                                                                                                                                      -118,
                                                                                                                                                                                                      125,
                                                                                                                                                                                                      -26,
                                                                                                                                                                                                      -59,
                                                                                                                                                                                                      -44,
                                                                                                                                                                                                      30,
                                                                                                                                                                                                      27,
                                                                                                                                                                                                      42,
                                                                                                                                                                                                      -36
                                                                                                                                                                                                   };
                                                                                                                                                                                                   byte[] byteValueArray79 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                      byteValueArray,
                                                                                                                                                                                                      byteValueArray78,
                                                                                                                                                                                                      coreByteArrayEntry
                                                                                                                                                                                                   );
                                                                                                                                                                                                   this.byteFlagArray69 = itemEntry133Entry39.resolveByteArray(
                                                                                                                                                                                                      byteValueArray79
                                                                                                                                                                                                   );
                                                                                                                                                                                                   value2 = (
                                                                                                                                                                                                         value2
                                                                                                                                                                                                            ^ -0x109a02286711e1fcL
                                                                                                                                                                                                      )
                                                                                                                                                                                                      - 0x7f3f34758508dca6L;
                                                                                                                                                                                                }
                                                                                                                                                                                             } else {
                                                                                                                                                                                                ItemEntry133Entry itemEntry133Entry40 = new ItemEntry133Entry(
                                                                                                                                                                                                   MovementWeightProperty.SHA3_256
                                                                                                                                                                                                );
                                                                                                                                                                                                byte[] byteValueArray80 = new byte[]{
                                                                                                                                                                                                   82,
                                                                                                                                                                                                   -89,
                                                                                                                                                                                                   -31,
                                                                                                                                                                                                   14,
                                                                                                                                                                                                   -90,
                                                                                                                                                                                                   -56,
                                                                                                                                                                                                   72,
                                                                                                                                                                                                   -67,
                                                                                                                                                                                                   68,
                                                                                                                                                                                                   -53,
                                                                                                                                                                                                   -112,
                                                                                                                                                                                                   -92,
                                                                                                                                                                                                   -108,
                                                                                                                                                                                                   64,
                                                                                                                                                                                                   -80,
                                                                                                                                                                                                   -16,
                                                                                                                                                                                                   104,
                                                                                                                                                                                                   97,
                                                                                                                                                                                                   78,
                                                                                                                                                                                                   114,
                                                                                                                                                                                                   52,
                                                                                                                                                                                                   122,
                                                                                                                                                                                                   37,
                                                                                                                                                                                                   -122,
                                                                                                                                                                                                   39,
                                                                                                                                                                                                   -26,
                                                                                                                                                                                                   -5,
                                                                                                                                                                                                   -48,
                                                                                                                                                                                                   -63,
                                                                                                                                                                                                   90,
                                                                                                                                                                                                   123,
                                                                                                                                                                                                   19
                                                                                                                                                                                                };
                                                                                                                                                                                                byte[] byteValueArray81 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                   byteValueArray,
                                                                                                                                                                                                   byteValueArray80,
                                                                                                                                                                                                   coreByteArrayEntry
                                                                                                                                                                                                );
                                                                                                                                                                                                this.byteFlagArray189 = itemEntry133Entry40.resolveByteArray(
                                                                                                                                                                                                   byteValueArray81
                                                                                                                                                                                                );
                                                                                                                                                                                                value2 = (
                                                                                                                                                                                                      value2
                                                                                                                                                                                                         ^ -0x4d16ddfa006666aaL
                                                                                                                                                                                                   )
                                                                                                                                                                                                   - 0x4ff86cd4ad4ab4c1L;
                                                                                                                                                                                             }
                                                                                                                                                                                          } else {
                                                                                                                                                                                             ItemEntry133Entry itemEntry133Entry41 = new ItemEntry133Entry(
                                                                                                                                                                                                MovementWeightProperty.SHA3_256
                                                                                                                                                                                             );
                                                                                                                                                                                             byte[] byteValueArray82 = new byte[]{
                                                                                                                                                                                                -113,
                                                                                                                                                                                                -22,
                                                                                                                                                                                                57,
                                                                                                                                                                                                52,
                                                                                                                                                                                                113,
                                                                                                                                                                                                -101,
                                                                                                                                                                                                -16,
                                                                                                                                                                                                -119,
                                                                                                                                                                                                -33,
                                                                                                                                                                                                98,
                                                                                                                                                                                                -91,
                                                                                                                                                                                                -24,
                                                                                                                                                                                                -58,
                                                                                                                                                                                                56,
                                                                                                                                                                                                66,
                                                                                                                                                                                                -49,
                                                                                                                                                                                                -57,
                                                                                                                                                                                                127,
                                                                                                                                                                                                123,
                                                                                                                                                                                                -88,
                                                                                                                                                                                                78,
                                                                                                                                                                                                26,
                                                                                                                                                                                                15,
                                                                                                                                                                                                -4,
                                                                                                                                                                                                -7,
                                                                                                                                                                                                7,
                                                                                                                                                                                                -62,
                                                                                                                                                                                                -116,
                                                                                                                                                                                                -24,
                                                                                                                                                                                                8,
                                                                                                                                                                                                -23,
                                                                                                                                                                                                -48
                                                                                                                                                                                             };
                                                                                                                                                                                             byte[] byteValueArray83 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                                byteValueArray,
                                                                                                                                                                                                byteValueArray82,
                                                                                                                                                                                                coreByteArrayEntry
                                                                                                                                                                                             );
                                                                                                                                                                                             this.byteFlagArray60 = itemEntry133Entry41.resolveByteArray(
                                                                                                                                                                                                byteValueArray83
                                                                                                                                                                                             );
                                                                                                                                                                                             value2 = (
                                                                                                                                                                                                   value2
                                                                                                                                                                                                      ^ 0x266284a7abc40863L
                                                                                                                                                                                                )
                                                                                                                                                                                                - 0x1474372588d39b49L;
                                                                                                                                                                                          }
                                                                                                                                                                                       } else {
                                                                                                                                                                                          ItemEntry133Entry itemEntry133Entry42 = new ItemEntry133Entry(
                                                                                                                                                                                             MovementWeightProperty.SHA3_256
                                                                                                                                                                                          );
                                                                                                                                                                                          byte[] byteValueArray84 = new byte[]{
                                                                                                                                                                                             -33,
                                                                                                                                                                                             -72,
                                                                                                                                                                                             13,
                                                                                                                                                                                             69,
                                                                                                                                                                                             114,
                                                                                                                                                                                             -88,
                                                                                                                                                                                             -29,
                                                                                                                                                                                             -4,
                                                                                                                                                                                             16,
                                                                                                                                                                                             11,
                                                                                                                                                                                             85,
                                                                                                                                                                                             -112,
                                                                                                                                                                                             94,
                                                                                                                                                                                             -73,
                                                                                                                                                                                             20,
                                                                                                                                                                                             39,
                                                                                                                                                                                             120,
                                                                                                                                                                                             -99,
                                                                                                                                                                                             41,
                                                                                                                                                                                             -60,
                                                                                                                                                                                             58,
                                                                                                                                                                                             121,
                                                                                                                                                                                             0,
                                                                                                                                                                                             -70,
                                                                                                                                                                                             19,
                                                                                                                                                                                             -92,
                                                                                                                                                                                             -119,
                                                                                                                                                                                             -112,
                                                                                                                                                                                             -15,
                                                                                                                                                                                             -94,
                                                                                                                                                                                             123,
                                                                                                                                                                                             -96
                                                                                                                                                                                          };
                                                                                                                                                                                          byte[] byteValueArray85 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                             byteValueArray,
                                                                                                                                                                                             byteValueArray84,
                                                                                                                                                                                             coreByteArrayEntry
                                                                                                                                                                                          );
                                                                                                                                                                                          this.byteFlagArray83 = itemEntry133Entry42.resolveByteArray(
                                                                                                                                                                                             byteValueArray85
                                                                                                                                                                                          );
                                                                                                                                                                                          value2 = (
                                                                                                                                                                                                value2
                                                                                                                                                                                                   ^ 0x557cce54332391c8L
                                                                                                                                                                                             )
                                                                                                                                                                                             - 0x2efc88aaea5e8f0fL;
                                                                                                                                                                                       }
                                                                                                                                                                                    } else {
                                                                                                                                                                                       ItemEntry133Entry itemEntry133Entry43 = new ItemEntry133Entry(
                                                                                                                                                                                          MovementWeightProperty.SHA3_256
                                                                                                                                                                                       );
                                                                                                                                                                                       byte[] byteValueArray86 = new byte[]{
                                                                                                                                                                                          48,
                                                                                                                                                                                          69,
                                                                                                                                                                                          3,
                                                                                                                                                                                          -88,
                                                                                                                                                                                          68,
                                                                                                                                                                                          -119,
                                                                                                                                                                                          -28,
                                                                                                                                                                                          40,
                                                                                                                                                                                          100,
                                                                                                                                                                                          10,
                                                                                                                                                                                          27,
                                                                                                                                                                                          40,
                                                                                                                                                                                          90,
                                                                                                                                                                                          -80,
                                                                                                                                                                                          105,
                                                                                                                                                                                          49,
                                                                                                                                                                                          59,
                                                                                                                                                                                          125,
                                                                                                                                                                                          -91,
                                                                                                                                                                                          -60,
                                                                                                                                                                                          -65,
                                                                                                                                                                                          -89,
                                                                                                                                                                                          22,
                                                                                                                                                                                          120,
                                                                                                                                                                                          -108,
                                                                                                                                                                                          -24,
                                                                                                                                                                                          113,
                                                                                                                                                                                          -59,
                                                                                                                                                                                          100,
                                                                                                                                                                                          -62,
                                                                                                                                                                                          -8,
                                                                                                                                                                                          -76
                                                                                                                                                                                       };
                                                                                                                                                                                       byte[] byteValueArray87 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                          byteValueArray,
                                                                                                                                                                                          byteValueArray86,
                                                                                                                                                                                          coreByteArrayEntry
                                                                                                                                                                                       );
                                                                                                                                                                                       this.byteFlagArray104 = itemEntry133Entry43.resolveByteArray(
                                                                                                                                                                                          byteValueArray87
                                                                                                                                                                                       );
                                                                                                                                                                                       value2 = (
                                                                                                                                                                                             value2
                                                                                                                                                                                                ^ 0x6262f39ab2336f8cL
                                                                                                                                                                                          )
                                                                                                                                                                                          - 0x1af676a1dbc28956L;
                                                                                                                                                                                    }
                                                                                                                                                                                 } else {
                                                                                                                                                                                    ItemEntry133Entry itemEntry133Entry44 = new ItemEntry133Entry(
                                                                                                                                                                                       MovementWeightProperty.SHA3_256
                                                                                                                                                                                    );
                                                                                                                                                                                    byte[] byteValueArray88 = new byte[]{
                                                                                                                                                                                       -31,
                                                                                                                                                                                       64,
                                                                                                                                                                                       127,
                                                                                                                                                                                       65,
                                                                                                                                                                                       77,
                                                                                                                                                                                       -117,
                                                                                                                                                                                       24,
                                                                                                                                                                                       -30,
                                                                                                                                                                                       -2,
                                                                                                                                                                                       67,
                                                                                                                                                                                       119,
                                                                                                                                                                                       55,
                                                                                                                                                                                       -12,
                                                                                                                                                                                       -10,
                                                                                                                                                                                       -12,
                                                                                                                                                                                       -110,
                                                                                                                                                                                       -54,
                                                                                                                                                                                       -103,
                                                                                                                                                                                       44,
                                                                                                                                                                                       92,
                                                                                                                                                                                       25,
                                                                                                                                                                                       -105,
                                                                                                                                                                                       0,
                                                                                                                                                                                       -39,
                                                                                                                                                                                       96,
                                                                                                                                                                                       94,
                                                                                                                                                                                       -40,
                                                                                                                                                                                       -15,
                                                                                                                                                                                       111,
                                                                                                                                                                                       80,
                                                                                                                                                                                       -21,
                                                                                                                                                                                       28
                                                                                                                                                                                    };
                                                                                                                                                                                    byte[] byteValueArray89 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                       byteValueArray,
                                                                                                                                                                                       byteValueArray88,
                                                                                                                                                                                       coreByteArrayEntry
                                                                                                                                                                                    );
                                                                                                                                                                                    this.byteFlagArray200 = itemEntry133Entry44.resolveByteArray(
                                                                                                                                                                                       byteValueArray89
                                                                                                                                                                                    );
                                                                                                                                                                                    value2 = (
                                                                                                                                                                                          value2
                                                                                                                                                                                             ^ -0x347c86412059db30L
                                                                                                                                                                                       )
                                                                                                                                                                                       - 0x26c5fbb400547560L;
                                                                                                                                                                                 }
                                                                                                                                                                              } else {
                                                                                                                                                                                 ItemEntry133Entry itemEntry133Entry45 = new ItemEntry133Entry(
                                                                                                                                                                                    MovementWeightProperty.SHA3_256
                                                                                                                                                                                 );
                                                                                                                                                                                 byte[] byteValueArray90 = new byte[]{
                                                                                                                                                                                    -14,
                                                                                                                                                                                    84,
                                                                                                                                                                                    -33,
                                                                                                                                                                                    51,
                                                                                                                                                                                    28,
                                                                                                                                                                                    74,
                                                                                                                                                                                    -24,
                                                                                                                                                                                    -22,
                                                                                                                                                                                    -59,
                                                                                                                                                                                    -84,
                                                                                                                                                                                    32,
                                                                                                                                                                                    -57,
                                                                                                                                                                                    -14,
                                                                                                                                                                                    67,
                                                                                                                                                                                    -6,
                                                                                                                                                                                    -100,
                                                                                                                                                                                    -119,
                                                                                                                                                                                    -33,
                                                                                                                                                                                    67,
                                                                                                                                                                                    78,
                                                                                                                                                                                    -72,
                                                                                                                                                                                    -30,
                                                                                                                                                                                    3,
                                                                                                                                                                                    -10,
                                                                                                                                                                                    12,
                                                                                                                                                                                    -24,
                                                                                                                                                                                    91,
                                                                                                                                                                                    19,
                                                                                                                                                                                    29,
                                                                                                                                                                                    68,
                                                                                                                                                                                    -54,
                                                                                                                                                                                    -39
                                                                                                                                                                                 };
                                                                                                                                                                                 byte[] byteValueArray91 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                    byteValueArray,
                                                                                                                                                                                    byteValueArray90,
                                                                                                                                                                                    coreByteArrayEntry
                                                                                                                                                                                 );
                                                                                                                                                                                 this.byteFlagArray38 = itemEntry133Entry45.resolveByteArray(
                                                                                                                                                                                    byteValueArray91
                                                                                                                                                                                 );
                                                                                                                                                                                 value2 = (
                                                                                                                                                                                       value2
                                                                                                                                                                                          ^ -0x6e7f28d16cf3b7c4L
                                                                                                                                                                                    )
                                                                                                                                                                                    - 0x2e65e1d58c49753cL;
                                                                                                                                                                              }
                                                                                                                                                                           } else {
                                                                                                                                                                              ItemEntry133Entry itemEntry133Entry46 = new ItemEntry133Entry(
                                                                                                                                                                                 MovementWeightProperty.SHA3_256
                                                                                                                                                                              );
                                                                                                                                                                              byte[] byteValueArray92 = new byte[]{
                                                                                                                                                                                 -62,
                                                                                                                                                                                 30,
                                                                                                                                                                                 123,
                                                                                                                                                                                 121,
                                                                                                                                                                                 -114,
                                                                                                                                                                                 40,
                                                                                                                                                                                 62,
                                                                                                                                                                                 -125,
                                                                                                                                                                                 -5,
                                                                                                                                                                                 123,
                                                                                                                                                                                 -56,
                                                                                                                                                                                 43,
                                                                                                                                                                                 -28,
                                                                                                                                                                                 -24,
                                                                                                                                                                                 -52,
                                                                                                                                                                                 13,
                                                                                                                                                                                 118,
                                                                                                                                                                                 -26,
                                                                                                                                                                                 77,
                                                                                                                                                                                 4,
                                                                                                                                                                                 -100,
                                                                                                                                                                                 36,
                                                                                                                                                                                 87,
                                                                                                                                                                                 -85,
                                                                                                                                                                                 -3,
                                                                                                                                                                                 3,
                                                                                                                                                                                 89,
                                                                                                                                                                                 52,
                                                                                                                                                                                 -9,
                                                                                                                                                                                 35,
                                                                                                                                                                                 -128,
                                                                                                                                                                                 92
                                                                                                                                                                              };
                                                                                                                                                                              byte[] byteValueArray93 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                                 byteValueArray,
                                                                                                                                                                                 byteValueArray92,
                                                                                                                                                                                 coreByteArrayEntry
                                                                                                                                                                              );
                                                                                                                                                                              this.byteFlagArray47 = itemEntry133Entry46.resolveByteArray(
                                                                                                                                                                                 byteValueArray93
                                                                                                                                                                              );
                                                                                                                                                                              value2 = (
                                                                                                                                                                                    value2
                                                                                                                                                                                       ^ -0xfff28034aa8d228L
                                                                                                                                                                                 )
                                                                                                                                                                                 - 0x532ebfb1fdcf1addL;
                                                                                                                                                                           }
                                                                                                                                                                        } else {
                                                                                                                                                                           ItemEntry133Entry itemEntry133Entry47 = new ItemEntry133Entry(
                                                                                                                                                                              MovementWeightProperty.SHA3_256
                                                                                                                                                                           );
                                                                                                                                                                           byte[] byteValueArray94 = new byte[]{
                                                                                                                                                                              89,
                                                                                                                                                                              68,
                                                                                                                                                                              98,
                                                                                                                                                                              4,
                                                                                                                                                                              70,
                                                                                                                                                                              -15,
                                                                                                                                                                              49,
                                                                                                                                                                              -73,
                                                                                                                                                                              90,
                                                                                                                                                                              -113,
                                                                                                                                                                              -47,
                                                                                                                                                                              -25,
                                                                                                                                                                              -72,
                                                                                                                                                                              -13,
                                                                                                                                                                              -117,
                                                                                                                                                                              -106,
                                                                                                                                                                              21,
                                                                                                                                                                              18,
                                                                                                                                                                              -91,
                                                                                                                                                                              -124,
                                                                                                                                                                              23,
                                                                                                                                                                              -96,
                                                                                                                                                                              3,
                                                                                                                                                                              -1,
                                                                                                                                                                              -33,
                                                                                                                                                                              112,
                                                                                                                                                                              70,
                                                                                                                                                                              100,
                                                                                                                                                                              -10,
                                                                                                                                                                              80,
                                                                                                                                                                              -74,
                                                                                                                                                                              -22
                                                                                                                                                                           };
                                                                                                                                                                           byte[] byteValueArray95 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                              byteValueArray,
                                                                                                                                                                              byteValueArray94,
                                                                                                                                                                              coreByteArrayEntry
                                                                                                                                                                           );
                                                                                                                                                                           this.byteFlagArray96 = itemEntry133Entry47.resolveByteArray(
                                                                                                                                                                              byteValueArray95
                                                                                                                                                                           );
                                                                                                                                                                           value2 = (
                                                                                                                                                                                 value2
                                                                                                                                                                                    ^ -0x1101095106a3b576L
                                                                                                                                                                              )
                                                                                                                                                                              - 0x4c2d02ac75ca9d1eL;
                                                                                                                                                                        }
                                                                                                                                                                     } else {
                                                                                                                                                                        ItemEntry133Entry itemEntry133Entry48 = new ItemEntry133Entry(
                                                                                                                                                                           MovementWeightProperty.SHA3_256
                                                                                                                                                                        );
                                                                                                                                                                        byte[] byteValueArray96 = new byte[]{
                                                                                                                                                                           -85,
                                                                                                                                                                           63,
                                                                                                                                                                           79,
                                                                                                                                                                           -35,
                                                                                                                                                                           9,
                                                                                                                                                                           -111,
                                                                                                                                                                           -96,
                                                                                                                                                                           -35,
                                                                                                                                                                           11,
                                                                                                                                                                           -92,
                                                                                                                                                                           51,
                                                                                                                                                                           125,
                                                                                                                                                                           -52,
                                                                                                                                                                           -83,
                                                                                                                                                                           47,
                                                                                                                                                                           35,
                                                                                                                                                                           -106,
                                                                                                                                                                           13,
                                                                                                                                                                           112,
                                                                                                                                                                           -89,
                                                                                                                                                                           -66,
                                                                                                                                                                           -69,
                                                                                                                                                                           -105,
                                                                                                                                                                           -97,
                                                                                                                                                                           -55,
                                                                                                                                                                           -75,
                                                                                                                                                                           2,
                                                                                                                                                                           14,
                                                                                                                                                                           64,
                                                                                                                                                                           -56,
                                                                                                                                                                           -30,
                                                                                                                                                                           -70
                                                                                                                                                                        };
                                                                                                                                                                        byte[] byteValueArray97 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                           byteValueArray,
                                                                                                                                                                           byteValueArray96,
                                                                                                                                                                           coreByteArrayEntry
                                                                                                                                                                        );
                                                                                                                                                                        this.byteFlagArray169 = itemEntry133Entry48.resolveByteArray(
                                                                                                                                                                           byteValueArray97
                                                                                                                                                                        );
                                                                                                                                                                        value2 = (
                                                                                                                                                                              value2
                                                                                                                                                                                 ^ -0x4c5251bfc1339fbbL
                                                                                                                                                                           )
                                                                                                                                                                           - 0x75dfb7af898b858fL;
                                                                                                                                                                     }
                                                                                                                                                                  } else {
                                                                                                                                                                     ItemEntry133Entry itemEntry133Entry49 = new ItemEntry133Entry(
                                                                                                                                                                        MovementWeightProperty.SHA3_256
                                                                                                                                                                     );
                                                                                                                                                                     byte[] byteValueArray98 = new byte[]{
                                                                                                                                                                        -13,
                                                                                                                                                                        83,
                                                                                                                                                                        103,
                                                                                                                                                                        107,
                                                                                                                                                                        77,
                                                                                                                                                                        -122,
                                                                                                                                                                        -69,
                                                                                                                                                                        -106,
                                                                                                                                                                        27,
                                                                                                                                                                        -64,
                                                                                                                                                                        77,
                                                                                                                                                                        95,
                                                                                                                                                                        40,
                                                                                                                                                                        69,
                                                                                                                                                                        87,
                                                                                                                                                                        95,
                                                                                                                                                                        117,
                                                                                                                                                                        -102,
                                                                                                                                                                        -47,
                                                                                                                                                                        120,
                                                                                                                                                                        68,
                                                                                                                                                                        -87,
                                                                                                                                                                        -68,
                                                                                                                                                                        -60,
                                                                                                                                                                        13,
                                                                                                                                                                        -25,
                                                                                                                                                                        107,
                                                                                                                                                                        -120,
                                                                                                                                                                        18,
                                                                                                                                                                        37,
                                                                                                                                                                        -108,
                                                                                                                                                                        -81
                                                                                                                                                                     };
                                                                                                                                                                     byte[] byteValueArray99 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                        byteValueArray,
                                                                                                                                                                        byteValueArray98,
                                                                                                                                                                        coreByteArrayEntry
                                                                                                                                                                     );
                                                                                                                                                                     this.byteFlagArray61 = itemEntry133Entry49.resolveByteArray(
                                                                                                                                                                        byteValueArray99
                                                                                                                                                                     );
                                                                                                                                                                     value2 = (
                                                                                                                                                                           value2
                                                                                                                                                                              ^ 0x77998143a06dadfdL
                                                                                                                                                                        )
                                                                                                                                                                        + 0x2b29edfa2666967L;
                                                                                                                                                                  }
                                                                                                                                                               } else {
                                                                                                                                                                  ItemEntry133Entry itemEntry133Entry50 = new ItemEntry133Entry(
                                                                                                                                                                     MovementWeightProperty.SHA3_256
                                                                                                                                                                  );
                                                                                                                                                                  byte[] byteValueArray100 = new byte[]{
                                                                                                                                                                     -4,
                                                                                                                                                                     10,
                                                                                                                                                                     -76,
                                                                                                                                                                     94,
                                                                                                                                                                     11,
                                                                                                                                                                     101,
                                                                                                                                                                     -12,
                                                                                                                                                                     26,
                                                                                                                                                                     104,
                                                                                                                                                                     -80,
                                                                                                                                                                     -2,
                                                                                                                                                                     116,
                                                                                                                                                                     111,
                                                                                                                                                                     116,
                                                                                                                                                                     73,
                                                                                                                                                                     64,
                                                                                                                                                                     90,
                                                                                                                                                                     -43,
                                                                                                                                                                     -50,
                                                                                                                                                                     106,
                                                                                                                                                                     15,
                                                                                                                                                                     43,
                                                                                                                                                                     -103,
                                                                                                                                                                     -97,
                                                                                                                                                                     -63,
                                                                                                                                                                     -36,
                                                                                                                                                                     28,
                                                                                                                                                                     106,
                                                                                                                                                                     -125,
                                                                                                                                                                     -13,
                                                                                                                                                                     31,
                                                                                                                                                                     -4
                                                                                                                                                                  };
                                                                                                                                                                  byte[] byteValueArray101 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                     byteValueArray,
                                                                                                                                                                     byteValueArray100,
                                                                                                                                                                     coreByteArrayEntry
                                                                                                                                                                  );
                                                                                                                                                                  this.byteFlagArray182 = itemEntry133Entry50.resolveByteArray(
                                                                                                                                                                     byteValueArray101
                                                                                                                                                                  );
                                                                                                                                                                  value2 = (
                                                                                                                                                                        value2
                                                                                                                                                                           ^ 0xaba39f1bc18f117L
                                                                                                                                                                     )
                                                                                                                                                                     - 0x785407d105b78b7fL;
                                                                                                                                                               }
                                                                                                                                                            } else {
                                                                                                                                                               ItemEntry133Entry itemEntry133Entry51 = new ItemEntry133Entry(
                                                                                                                                                                  MovementWeightProperty.SHA3_256
                                                                                                                                                               );
                                                                                                                                                               byte[] byteValueArray102 = new byte[]{
                                                                                                                                                                  -16,
                                                                                                                                                                  -52,
                                                                                                                                                                  121,
                                                                                                                                                                  97,
                                                                                                                                                                  -38,
                                                                                                                                                                  -114,
                                                                                                                                                                  50,
                                                                                                                                                                  4,
                                                                                                                                                                  -24,
                                                                                                                                                                  -20,
                                                                                                                                                                  106,
                                                                                                                                                                  26,
                                                                                                                                                                  12,
                                                                                                                                                                  49,
                                                                                                                                                                  -9,
                                                                                                                                                                  103,
                                                                                                                                                                  -120,
                                                                                                                                                                  5,
                                                                                                                                                                  -87,
                                                                                                                                                                  -54,
                                                                                                                                                                  114,
                                                                                                                                                                  -23,
                                                                                                                                                                  -23,
                                                                                                                                                                  56,
                                                                                                                                                                  -28,
                                                                                                                                                                  125,
                                                                                                                                                                  -70,
                                                                                                                                                                  -40,
                                                                                                                                                                  -18,
                                                                                                                                                                  -84,
                                                                                                                                                                  71,
                                                                                                                                                                  48
                                                                                                                                                               };
                                                                                                                                                               byte[] byteValueArray103 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                                  byteValueArray,
                                                                                                                                                                  byteValueArray102,
                                                                                                                                                                  coreByteArrayEntry
                                                                                                                                                               );
                                                                                                                                                               this.byteFlagArray106 = itemEntry133Entry51.resolveByteArray(
                                                                                                                                                                  byteValueArray103
                                                                                                                                                               );
                                                                                                                                                               value2 = (
                                                                                                                                                                     value2
                                                                                                                                                                        ^ -0xd30501c58ad9c2L
                                                                                                                                                                  )
                                                                                                                                                                  + 0x76813ba24366650aL;
                                                                                                                                                            }
                                                                                                                                                         } else {
                                                                                                                                                            ItemEntry133Entry itemEntry133Entry52 = new ItemEntry133Entry(
                                                                                                                                                               MovementWeightProperty.SHA3_256
                                                                                                                                                            );
                                                                                                                                                            byte[] byteValueArray104 = new byte[]{
                                                                                                                                                               -52,
                                                                                                                                                               123,
                                                                                                                                                               114,
                                                                                                                                                               -55,
                                                                                                                                                               -98,
                                                                                                                                                               104,
                                                                                                                                                               -54,
                                                                                                                                                               81,
                                                                                                                                                               15,
                                                                                                                                                               42,
                                                                                                                                                               -126,
                                                                                                                                                               6,
                                                                                                                                                               71,
                                                                                                                                                               -53,
                                                                                                                                                               89,
                                                                                                                                                               -126,
                                                                                                                                                               69,
                                                                                                                                                               -99,
                                                                                                                                                               -78,
                                                                                                                                                               21,
                                                                                                                                                               -32,
                                                                                                                                                               27,
                                                                                                                                                               103,
                                                                                                                                                               -128,
                                                                                                                                                               -6,
                                                                                                                                                               88,
                                                                                                                                                               57,
                                                                                                                                                               10,
                                                                                                                                                               123,
                                                                                                                                                               -86,
                                                                                                                                                               -33,
                                                                                                                                                               -97
                                                                                                                                                            };
                                                                                                                                                            byte[] byteValueArray105 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                               byteValueArray,
                                                                                                                                                               byteValueArray104,
                                                                                                                                                               coreByteArrayEntry
                                                                                                                                                            );
                                                                                                                                                            this.byteFlagArray84 = itemEntry133Entry52.resolveByteArray(
                                                                                                                                                               byteValueArray105
                                                                                                                                                            );
                                                                                                                                                            value2 = (
                                                                                                                                                                  value2
                                                                                                                                                                     ^ -0x25a9cfeadb3f52b9L
                                                                                                                                                               )
                                                                                                                                                               - 0x5e2548ec83d9197dL;
                                                                                                                                                         }
                                                                                                                                                      } else {
                                                                                                                                                         ItemEntry133Entry itemEntry133Entry53 = new ItemEntry133Entry(
                                                                                                                                                            MovementWeightProperty.SHA3_256
                                                                                                                                                         );
                                                                                                                                                         byte[] byteValueArray106 = new byte[]{
                                                                                                                                                            80,
                                                                                                                                                            -21,
                                                                                                                                                            -112,
                                                                                                                                                            -2,
                                                                                                                                                            -69,
                                                                                                                                                            -127,
                                                                                                                                                            -35,
                                                                                                                                                            65,
                                                                                                                                                            34,
                                                                                                                                                            127,
                                                                                                                                                            -86,
                                                                                                                                                            -11,
                                                                                                                                                            114,
                                                                                                                                                            -23,
                                                                                                                                                            126,
                                                                                                                                                            -28,
                                                                                                                                                            47,
                                                                                                                                                            48,
                                                                                                                                                            68,
                                                                                                                                                            123,
                                                                                                                                                            116,
                                                                                                                                                            75,
                                                                                                                                                            70,
                                                                                                                                                            0,
                                                                                                                                                            -76,
                                                                                                                                                            -77,
                                                                                                                                                            114,
                                                                                                                                                            62,
                                                                                                                                                            -106,
                                                                                                                                                            -10,
                                                                                                                                                            -71,
                                                                                                                                                            -65
                                                                                                                                                         };
                                                                                                                                                         byte[] byteValueArray107 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                            byteValueArray,
                                                                                                                                                            byteValueArray106,
                                                                                                                                                            coreByteArrayEntry
                                                                                                                                                         );
                                                                                                                                                         this.byteFlagArray175 = itemEntry133Entry53.resolveByteArray(
                                                                                                                                                            byteValueArray107
                                                                                                                                                         );
                                                                                                                                                         value2 = (
                                                                                                                                                               value2
                                                                                                                                                                  ^ -0x3307b78b4540219eL
                                                                                                                                                            )
                                                                                                                                                            + 0x7e8fcff1ea9b831cL;
                                                                                                                                                      }
                                                                                                                                                   } else {
                                                                                                                                                      ItemEntry133Entry itemEntry133Entry54 = new ItemEntry133Entry(
                                                                                                                                                         MovementWeightProperty.SHA3_256
                                                                                                                                                      );
                                                                                                                                                      byte[] byteValueArray108 = new byte[]{
                                                                                                                                                         65,
                                                                                                                                                         81,
                                                                                                                                                         -69,
                                                                                                                                                         -58,
                                                                                                                                                         -48,
                                                                                                                                                         46,
                                                                                                                                                         54,
                                                                                                                                                         108,
                                                                                                                                                         27,
                                                                                                                                                         -3,
                                                                                                                                                         -40,
                                                                                                                                                         88,
                                                                                                                                                         -69,
                                                                                                                                                         -117,
                                                                                                                                                         -107,
                                                                                                                                                         -77,
                                                                                                                                                         -31,
                                                                                                                                                         -112,
                                                                                                                                                         14,
                                                                                                                                                         -127,
                                                                                                                                                         -78,
                                                                                                                                                         55,
                                                                                                                                                         40,
                                                                                                                                                         105,
                                                                                                                                                         79,
                                                                                                                                                         -59,
                                                                                                                                                         -15,
                                                                                                                                                         -23,
                                                                                                                                                         -18,
                                                                                                                                                         97,
                                                                                                                                                         -19,
                                                                                                                                                         -104
                                                                                                                                                      };
                                                                                                                                                      byte[] byteValueArray109 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                         byteValueArray,
                                                                                                                                                         byteValueArray108,
                                                                                                                                                         coreByteArrayEntry
                                                                                                                                                      );
                                                                                                                                                      this.byteFlagArray119 = itemEntry133Entry54.resolveByteArray(
                                                                                                                                                         byteValueArray109
                                                                                                                                                      );
                                                                                                                                                      value2 = (
                                                                                                                                                            value2
                                                                                                                                                               ^ 0x33838dc8fcc86572L
                                                                                                                                                         )
                                                                                                                                                         + 0x167cd4e57cd0adL;
                                                                                                                                                   }
                                                                                                                                                } else {
                                                                                                                                                   ItemEntry133Entry itemEntry133Entry55 = new ItemEntry133Entry(
                                                                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                                                                   );
                                                                                                                                                   byte[] byteValueArray110 = new byte[]{
                                                                                                                                                      89,
                                                                                                                                                      71,
                                                                                                                                                      113,
                                                                                                                                                      -17,
                                                                                                                                                      -35,
                                                                                                                                                      17,
                                                                                                                                                      -77,
                                                                                                                                                      73,
                                                                                                                                                      75,
                                                                                                                                                      76,
                                                                                                                                                      -70,
                                                                                                                                                      27,
                                                                                                                                                      -4,
                                                                                                                                                      121,
                                                                                                                                                      -48,
                                                                                                                                                      113,
                                                                                                                                                      108,
                                                                                                                                                      -87,
                                                                                                                                                      56,
                                                                                                                                                      76,
                                                                                                                                                      73,
                                                                                                                                                      53,
                                                                                                                                                      45,
                                                                                                                                                      -31,
                                                                                                                                                      69,
                                                                                                                                                      -42,
                                                                                                                                                      -104,
                                                                                                                                                      -23,
                                                                                                                                                      112,
                                                                                                                                                      86,
                                                                                                                                                      -25,
                                                                                                                                                      -58
                                                                                                                                                   };
                                                                                                                                                   byte[] byteValueArray111 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                      byteValueArray,
                                                                                                                                                      byteValueArray110,
                                                                                                                                                      coreByteArrayEntry
                                                                                                                                                   );
                                                                                                                                                   this.byteFlagArray145 = itemEntry133Entry55.resolveByteArray(
                                                                                                                                                      byteValueArray111
                                                                                                                                                   );
                                                                                                                                                   value2 = (
                                                                                                                                                         value2
                                                                                                                                                            ^ -0x21656cc6bc654375L
                                                                                                                                                      )
                                                                                                                                                      - 0x597ebf4948a9053dL;
                                                                                                                                                }
                                                                                                                                             } else {
                                                                                                                                                ItemEntry133Entry itemEntry133Entry56 = new ItemEntry133Entry(
                                                                                                                                                   MovementWeightProperty.SHA3_256
                                                                                                                                                );
                                                                                                                                                byte[] byteValueArray112 = new byte[]{
                                                                                                                                                   -34,
                                                                                                                                                   -95,
                                                                                                                                                   -102,
                                                                                                                                                   86,
                                                                                                                                                   -116,
                                                                                                                                                   29,
                                                                                                                                                   68,
                                                                                                                                                   -102,
                                                                                                                                                   43,
                                                                                                                                                   9,
                                                                                                                                                   14,
                                                                                                                                                   -104,
                                                                                                                                                   -54,
                                                                                                                                                   53,
                                                                                                                                                   11,
                                                                                                                                                   -78,
                                                                                                                                                   41,
                                                                                                                                                   -68,
                                                                                                                                                   -53,
                                                                                                                                                   24,
                                                                                                                                                   6,
                                                                                                                                                   -20,
                                                                                                                                                   76,
                                                                                                                                                   39,
                                                                                                                                                   46,
                                                                                                                                                   -77,
                                                                                                                                                   71,
                                                                                                                                                   -11,
                                                                                                                                                   -33,
                                                                                                                                                   -2,
                                                                                                                                                   -58,
                                                                                                                                                   -44
                                                                                                                                                };
                                                                                                                                                byte[] byteValueArray113 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                   byteValueArray,
                                                                                                                                                   byteValueArray112,
                                                                                                                                                   coreByteArrayEntry
                                                                                                                                                );
                                                                                                                                                this.byteFlagArray71 = itemEntry133Entry56.resolveByteArray(
                                                                                                                                                   byteValueArray113
                                                                                                                                                );
                                                                                                                                                value2 = (
                                                                                                                                                      value2
                                                                                                                                                         ^ -0x534fd9c247c6eab6L
                                                                                                                                                   )
                                                                                                                                                   - 0x48b93df28a18446eL;
                                                                                                                                             }
                                                                                                                                          } else {
                                                                                                                                             ItemEntry133Entry itemEntry133Entry57 = new ItemEntry133Entry(
                                                                                                                                                MovementWeightProperty.SHA3_256
                                                                                                                                             );
                                                                                                                                             byte[] byteValueArray114 = new byte[]{
                                                                                                                                                88,
                                                                                                                                                -47,
                                                                                                                                                -72,
                                                                                                                                                -36,
                                                                                                                                                -87,
                                                                                                                                                -63,
                                                                                                                                                72,
                                                                                                                                                115,
                                                                                                                                                108,
                                                                                                                                                -11,
                                                                                                                                                -47,
                                                                                                                                                -114,
                                                                                                                                                -97,
                                                                                                                                                -6,
                                                                                                                                                -32,
                                                                                                                                                -50,
                                                                                                                                                86,
                                                                                                                                                118,
                                                                                                                                                7,
                                                                                                                                                3,
                                                                                                                                                -78,
                                                                                                                                                86,
                                                                                                                                                117,
                                                                                                                                                -27,
                                                                                                                                                -28,
                                                                                                                                                65,
                                                                                                                                                -82,
                                                                                                                                                -104,
                                                                                                                                                -23,
                                                                                                                                                -13,
                                                                                                                                                96,
                                                                                                                                                -92
                                                                                                                                             };
                                                                                                                                             byte[] byteValueArray115 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                byteValueArray,
                                                                                                                                                byteValueArray114,
                                                                                                                                                coreByteArrayEntry
                                                                                                                                             );
                                                                                                                                             this.byteFlagArray58 = itemEntry133Entry57.resolveByteArray(
                                                                                                                                                byteValueArray115
                                                                                                                                             );
                                                                                                                                             value2 = (
                                                                                                                                                   value2
                                                                                                                                                      ^ 0x67bace7c588760b6L
                                                                                                                                                )
                                                                                                                                                + 0x34053a19e1f079afL;
                                                                                                                                          }
                                                                                                                                       } else {
                                                                                                                                          ItemEntry133Entry itemEntry133Entry58 = new ItemEntry133Entry(
                                                                                                                                             MovementWeightProperty.SHA3_256
                                                                                                                                          );
                                                                                                                                          byte[] byteValueArray116 = new byte[]{
                                                                                                                                             -59,
                                                                                                                                             -34,
                                                                                                                                             100,
                                                                                                                                             81,
                                                                                                                                             39,
                                                                                                                                             -85,
                                                                                                                                             -69,
                                                                                                                                             120,
                                                                                                                                             107,
                                                                                                                                             -121,
                                                                                                                                             -105,
                                                                                                                                             -85,
                                                                                                                                             89,
                                                                                                                                             -36,
                                                                                                                                             -57,
                                                                                                                                             61,
                                                                                                                                             -8,
                                                                                                                                             12,
                                                                                                                                             -101,
                                                                                                                                             63,
                                                                                                                                             -73,
                                                                                                                                             107,
                                                                                                                                             127,
                                                                                                                                             105,
                                                                                                                                             -76,
                                                                                                                                             -76,
                                                                                                                                             9,
                                                                                                                                             75,
                                                                                                                                             -28,
                                                                                                                                             -58,
                                                                                                                                             63,
                                                                                                                                             -14
                                                                                                                                          };
                                                                                                                                          byte[] byteValueArray117 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                             byteValueArray,
                                                                                                                                             byteValueArray116,
                                                                                                                                             coreByteArrayEntry
                                                                                                                                          );
                                                                                                                                          this.byteFlagArray157 = itemEntry133Entry58.resolveByteArray(
                                                                                                                                             byteValueArray117
                                                                                                                                          );
                                                                                                                                          value2 = (
                                                                                                                                                value2
                                                                                                                                                   ^ -0x36881d90b915112L
                                                                                                                                             )
                                                                                                                                             + 0x74796665e30a69e3L;
                                                                                                                                       }
                                                                                                                                    } else {
                                                                                                                                       ItemEntry133Entry itemEntry133Entry59 = new ItemEntry133Entry(
                                                                                                                                          MovementWeightProperty.SHA3_256
                                                                                                                                       );
                                                                                                                                       byte[] byteValueArray118 = new byte[]{
                                                                                                                                          -89,
                                                                                                                                          59,
                                                                                                                                          51,
                                                                                                                                          -73,
                                                                                                                                          -47,
                                                                                                                                          -87,
                                                                                                                                          3,
                                                                                                                                          -85,
                                                                                                                                          62,
                                                                                                                                          120,
                                                                                                                                          -15,
                                                                                                                                          104,
                                                                                                                                          4,
                                                                                                                                          35,
                                                                                                                                          -49,
                                                                                                                                          -3,
                                                                                                                                          -44,
                                                                                                                                          -14,
                                                                                                                                          114,
                                                                                                                                          71,
                                                                                                                                          -1,
                                                                                                                                          74,
                                                                                                                                          -97,
                                                                                                                                          26,
                                                                                                                                          -13,
                                                                                                                                          127,
                                                                                                                                          118,
                                                                                                                                          11,
                                                                                                                                          -52,
                                                                                                                                          70,
                                                                                                                                          -46,
                                                                                                                                          79
                                                                                                                                       };
                                                                                                                                       byte[] byteValueArray119 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                          byteValueArray, byteValueArray118, coreByteArrayEntry
                                                                                                                                       );
                                                                                                                                       this.byteFlagArray27 = itemEntry133Entry59.resolveByteArray(
                                                                                                                                          byteValueArray119
                                                                                                                                       );
                                                                                                                                       value2 = (
                                                                                                                                             value2
                                                                                                                                                ^ -0x53f51df4543dfbfaL
                                                                                                                                          )
                                                                                                                                          - 0x56e011676b368187L;
                                                                                                                                    }
                                                                                                                                 } else {
                                                                                                                                    ItemEntry133Entry itemEntry133Entry60 = new ItemEntry133Entry(
                                                                                                                                       MovementWeightProperty.SHA3_256
                                                                                                                                    );
                                                                                                                                    byte[] byteValueArray120 = new byte[]{
                                                                                                                                       64,
                                                                                                                                       -68,
                                                                                                                                       -94,
                                                                                                                                       108,
                                                                                                                                       -121,
                                                                                                                                       33,
                                                                                                                                       19,
                                                                                                                                       7,
                                                                                                                                       42,
                                                                                                                                       -39,
                                                                                                                                       -117,
                                                                                                                                       -80,
                                                                                                                                       120,
                                                                                                                                       -34,
                                                                                                                                       118,
                                                                                                                                       -114,
                                                                                                                                       -49,
                                                                                                                                       -72,
                                                                                                                                       -46,
                                                                                                                                       -84,
                                                                                                                                       -115,
                                                                                                                                       122,
                                                                                                                                       -15,
                                                                                                                                       -101,
                                                                                                                                       -53,
                                                                                                                                       120,
                                                                                                                                       10,
                                                                                                                                       68,
                                                                                                                                       -78,
                                                                                                                                       -5,
                                                                                                                                       101,
                                                                                                                                       -27
                                                                                                                                    };
                                                                                                                                    byte[] byteValueArray121 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                       byteValueArray, byteValueArray120, coreByteArrayEntry
                                                                                                                                    );
                                                                                                                                    this.byteFlagArray137 = itemEntry133Entry60.resolveByteArray(
                                                                                                                                       byteValueArray121
                                                                                                                                    );
                                                                                                                                    value2 = (
                                                                                                                                          value2
                                                                                                                                             ^ -0x2bf47fb1327fa39fL
                                                                                                                                       )
                                                                                                                                       + 0x72efefa77876772cL;
                                                                                                                                 }
                                                                                                                              } else {
                                                                                                                                 ItemEntry133Entry itemEntry133Entry61 = new ItemEntry133Entry(
                                                                                                                                    MovementWeightProperty.SHA3_256
                                                                                                                                 );
                                                                                                                                 byte[] byteValueArray122 = new byte[]{
                                                                                                                                    -113,
                                                                                                                                    37,
                                                                                                                                    82,
                                                                                                                                    -33,
                                                                                                                                    98,
                                                                                                                                    25,
                                                                                                                                    23,
                                                                                                                                    119,
                                                                                                                                    -104,
                                                                                                                                    44,
                                                                                                                                    52,
                                                                                                                                    105,
                                                                                                                                    72,
                                                                                                                                    4,
                                                                                                                                    115,
                                                                                                                                    118,
                                                                                                                                    -5,
                                                                                                                                    -95,
                                                                                                                                    -80,
                                                                                                                                    -26,
                                                                                                                                    -106,
                                                                                                                                    -52,
                                                                                                                                    -8,
                                                                                                                                    -56,
                                                                                                                                    59,
                                                                                                                                    -30,
                                                                                                                                    71,
                                                                                                                                    -35,
                                                                                                                                    -34,
                                                                                                                                    -51,
                                                                                                                                    -11,
                                                                                                                                    -35
                                                                                                                                 };
                                                                                                                                 byte[] byteValueArray123 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                    byteValueArray, byteValueArray122, coreByteArrayEntry
                                                                                                                                 );
                                                                                                                                 this.byteFlagArray128 = itemEntry133Entry61.resolveByteArray(
                                                                                                                                    byteValueArray123
                                                                                                                                 );
                                                                                                                                 value2 = (
                                                                                                                                       value2
                                                                                                                                          ^ -0x639105e1bc5ecdb7L
                                                                                                                                    )
                                                                                                                                    + 0x49ff9529dfee9185L;
                                                                                                                              }
                                                                                                                           } else {
                                                                                                                              ItemEntry133Entry itemEntry133Entry62 = new ItemEntry133Entry(
                                                                                                                                 MovementWeightProperty.SHA3_256
                                                                                                                              );
                                                                                                                              byte[] byteValueArray124 = new byte[]{
                                                                                                                                 120,
                                                                                                                                 -75,
                                                                                                                                 -119,
                                                                                                                                 -128,
                                                                                                                                 111,
                                                                                                                                 63,
                                                                                                                                 29,
                                                                                                                                 -108,
                                                                                                                                 56,
                                                                                                                                 -84,
                                                                                                                                 -14,
                                                                                                                                 -16,
                                                                                                                                 -81,
                                                                                                                                 108,
                                                                                                                                 100,
                                                                                                                                 91,
                                                                                                                                 -46,
                                                                                                                                 -111,
                                                                                                                                 62,
                                                                                                                                 -10,
                                                                                                                                 9,
                                                                                                                                 -1,
                                                                                                                                 -120,
                                                                                                                                 54,
                                                                                                                                 -34,
                                                                                                                                 51,
                                                                                                                                 -49,
                                                                                                                                 119,
                                                                                                                                 4,
                                                                                                                                 122,
                                                                                                                                 69,
                                                                                                                                 -104
                                                                                                                              };
                                                                                                                              byte[] byteValueArray125 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                 byteValueArray, byteValueArray124, coreByteArrayEntry
                                                                                                                              );
                                                                                                                              this.byteFlagArray39 = itemEntry133Entry62.resolveByteArray(
                                                                                                                                 byteValueArray125
                                                                                                                              );
                                                                                                                              value2 = (
                                                                                                                                    value2
                                                                                                                                       ^ 0x6c946bc83380a5daL
                                                                                                                                 )
                                                                                                                                 + 0x275557a79b91a772L;
                                                                                                                           }
                                                                                                                        } else {
                                                                                                                           ItemEntry133Entry itemEntry133Entry63 = new ItemEntry133Entry(
                                                                                                                              MovementWeightProperty.SHA3_256
                                                                                                                           );
                                                                                                                           byte[] byteValueArray126 = new byte[]{
                                                                                                                              59,
                                                                                                                              67,
                                                                                                                              -86,
                                                                                                                              90,
                                                                                                                              21,
                                                                                                                              -57,
                                                                                                                              113,
                                                                                                                              -10,
                                                                                                                              119,
                                                                                                                              -122,
                                                                                                                              -107,
                                                                                                                              39,
                                                                                                                              35,
                                                                                                                              -86,
                                                                                                                              14,
                                                                                                                              -60,
                                                                                                                              -38,
                                                                                                                              100,
                                                                                                                              -106,
                                                                                                                              48,
                                                                                                                              -43,
                                                                                                                              96,
                                                                                                                              -34,
                                                                                                                              9,
                                                                                                                              -57,
                                                                                                                              -58,
                                                                                                                              93,
                                                                                                                              102,
                                                                                                                              102,
                                                                                                                              -65,
                                                                                                                              23,
                                                                                                                              32
                                                                                                                           };
                                                                                                                           byte[] byteValueArray127 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                              byteValueArray, byteValueArray126, coreByteArrayEntry
                                                                                                                           );
                                                                                                                           this.byteFlagArray115 = itemEntry133Entry63.resolveByteArray(
                                                                                                                              byteValueArray127
                                                                                                                           );
                                                                                                                           value2 = (value2 ^ -0x751e5ced3ddc2eL)
                                                                                                                              - 0x3adee0d846b6f8a4L;
                                                                                                                        }
                                                                                                                     } else {
                                                                                                                        ItemEntry133Entry itemEntry133Entry64 = new ItemEntry133Entry(
                                                                                                                           MovementWeightProperty.SHA3_256
                                                                                                                        );
                                                                                                                        byte[] byteValueArray128 = new byte[]{
                                                                                                                           41,
                                                                                                                           -29,
                                                                                                                           21,
                                                                                                                           0,
                                                                                                                           102,
                                                                                                                           -125,
                                                                                                                           -13,
                                                                                                                           109,
                                                                                                                           116,
                                                                                                                           -75,
                                                                                                                           -45,
                                                                                                                           110,
                                                                                                                           108,
                                                                                                                           -108,
                                                                                                                           42,
                                                                                                                           -36,
                                                                                                                           -70,
                                                                                                                           83,
                                                                                                                           55,
                                                                                                                           43,
                                                                                                                           13,
                                                                                                                           -124,
                                                                                                                           2,
                                                                                                                           29,
                                                                                                                           85,
                                                                                                                           -41,
                                                                                                                           90,
                                                                                                                           102,
                                                                                                                           124,
                                                                                                                           124,
                                                                                                                           -46,
                                                                                                                           -64
                                                                                                                        };
                                                                                                                        byte[] byteValueArray129 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                           byteValueArray, byteValueArray128, coreByteArrayEntry
                                                                                                                        );
                                                                                                                        this.byteFlagArray51 = itemEntry133Entry64.resolveByteArray(
                                                                                                                           byteValueArray129
                                                                                                                        );
                                                                                                                        value2 = (value2 ^ 0x2a0e67df5edffb5dL)
                                                                                                                           + 0x163ad3f9b69edd96L;
                                                                                                                     }
                                                                                                                  } else {
                                                                                                                     ItemEntry133Entry itemEntry133Entry65 = new ItemEntry133Entry(
                                                                                                                        MovementWeightProperty.SHA3_256
                                                                                                                     );
                                                                                                                     byte[] byteValueArray130 = new byte[]{
                                                                                                                        -33,
                                                                                                                        -71,
                                                                                                                        -122,
                                                                                                                        95,
                                                                                                                        -126,
                                                                                                                        42,
                                                                                                                        -63,
                                                                                                                        110,
                                                                                                                        108,
                                                                                                                        71,
                                                                                                                        57,
                                                                                                                        31,
                                                                                                                        -3,
                                                                                                                        9,
                                                                                                                        114,
                                                                                                                        21,
                                                                                                                        -41,
                                                                                                                        70,
                                                                                                                        76,
                                                                                                                        41,
                                                                                                                        -76,
                                                                                                                        10,
                                                                                                                        -93,
                                                                                                                        54,
                                                                                                                        -1,
                                                                                                                        -30,
                                                                                                                        -86,
                                                                                                                        7,
                                                                                                                        -29,
                                                                                                                        -50,
                                                                                                                        118,
                                                                                                                        -59
                                                                                                                     };
                                                                                                                     byte[] byteValueArray131 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                        byteValueArray, byteValueArray130, coreByteArrayEntry
                                                                                                                     );
                                                                                                                     this.byteFlagArray41 = itemEntry133Entry65.resolveByteArray(
                                                                                                                        byteValueArray131
                                                                                                                     );
                                                                                                                     value2 = (value2 ^ -0x416401c73d0d82aL)
                                                                                                                        + 0x4bba61e3ad0331f2L;
                                                                                                                  }
                                                                                                               } else {
                                                                                                                  ItemEntry133Entry itemEntry133Entry66 = new ItemEntry133Entry(
                                                                                                                     MovementWeightProperty.SHA3_256
                                                                                                                  );
                                                                                                                  byte[] byteValueArray132 = new byte[]{
                                                                                                                     123,
                                                                                                                     116,
                                                                                                                     59,
                                                                                                                     -37,
                                                                                                                     9,
                                                                                                                     28,
                                                                                                                     -33,
                                                                                                                     47,
                                                                                                                     62,
                                                                                                                     -53,
                                                                                                                     -86,
                                                                                                                     -58,
                                                                                                                     -30,
                                                                                                                     98,
                                                                                                                     -1,
                                                                                                                     19,
                                                                                                                     125,
                                                                                                                     62,
                                                                                                                     -12,
                                                                                                                     -58,
                                                                                                                     112,
                                                                                                                     -88,
                                                                                                                     100,
                                                                                                                     1,
                                                                                                                     -9,
                                                                                                                     -117,
                                                                                                                     69,
                                                                                                                     124,
                                                                                                                     46,
                                                                                                                     -117,
                                                                                                                     95,
                                                                                                                     50
                                                                                                                  };
                                                                                                                  byte[] byteValueArray133 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                     byteValueArray, byteValueArray132, coreByteArrayEntry
                                                                                                                  );
                                                                                                                  this.byteFlagArray199 = itemEntry133Entry66.resolveByteArray(
                                                                                                                     byteValueArray133
                                                                                                                  );
                                                                                                                  value2 = (value2 ^ -0x657d25bdb19a520fL)
                                                                                                                     + 0xb97ba989be5cdd4L;
                                                                                                               }
                                                                                                            } else {
                                                                                                               ItemEntry133Entry itemEntry133Entry67 = new ItemEntry133Entry(
                                                                                                                  MovementWeightProperty.SHA3_256
                                                                                                               );
                                                                                                               byte[] byteValueArray134 = new byte[]{
                                                                                                                  -105,
                                                                                                                  58,
                                                                                                                  118,
                                                                                                                  69,
                                                                                                                  -117,
                                                                                                                  -97,
                                                                                                                  -67,
                                                                                                                  -50,
                                                                                                                  -99,
                                                                                                                  124,
                                                                                                                  -51,
                                                                                                                  -51,
                                                                                                                  -31,
                                                                                                                  -35,
                                                                                                                  30,
                                                                                                                  93,
                                                                                                                  -127,
                                                                                                                  127,
                                                                                                                  112,
                                                                                                                  97,
                                                                                                                  28,
                                                                                                                  -105,
                                                                                                                  -71,
                                                                                                                  110,
                                                                                                                  34,
                                                                                                                  -51,
                                                                                                                  -39,
                                                                                                                  52,
                                                                                                                  3,
                                                                                                                  -71,
                                                                                                                  85,
                                                                                                                  101
                                                                                                               };
                                                                                                               byte[] byteValueArray135 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                  byteValueArray, byteValueArray134, coreByteArrayEntry
                                                                                                               );
                                                                                                               this.byteFlagArray172 = itemEntry133Entry67.resolveByteArray(
                                                                                                                  byteValueArray135
                                                                                                               );
                                                                                                               value2 = (value2 ^ -0x51bf55c1e9d04cabL)
                                                                                                                  + 0xb83bebe0bb4ac06L;
                                                                                                            }
                                                                                                         } else {
                                                                                                            ItemEntry133Entry itemEntry133Entry68 = new ItemEntry133Entry(
                                                                                                               MovementWeightProperty.SHA3_256
                                                                                                            );
                                                                                                            byte[] byteValueArray136 = new byte[]{
                                                                                                               25,
                                                                                                               2,
                                                                                                               26,
                                                                                                               3,
                                                                                                               -62,
                                                                                                               -19,
                                                                                                               77,
                                                                                                               109,
                                                                                                               70,
                                                                                                               79,
                                                                                                               100,
                                                                                                               -92,
                                                                                                               68,
                                                                                                               -93,
                                                                                                               -86,
                                                                                                               -86,
                                                                                                               22,
                                                                                                               110,
                                                                                                               83,
                                                                                                               121,
                                                                                                               78,
                                                                                                               54,
                                                                                                               125,
                                                                                                               -85,
                                                                                                               -80,
                                                                                                               -52,
                                                                                                               -118,
                                                                                                               -87,
                                                                                                               57,
                                                                                                               -57,
                                                                                                               -20,
                                                                                                               -122
                                                                                                            };
                                                                                                            byte[] byteValueArray137 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                               byteValueArray, byteValueArray136, coreByteArrayEntry
                                                                                                            );
                                                                                                            this.byteFlagArray143 = itemEntry133Entry68.resolveByteArray(
                                                                                                               byteValueArray137
                                                                                                            );
                                                                                                            value2 = (value2 ^ 0x1304dd6a8ab855ecL)
                                                                                                               - 0x14369c147f68ea7bL;
                                                                                                         }
                                                                                                      } else {
                                                                                                         ItemEntry133Entry itemEntry133Entry69 = new ItemEntry133Entry(
                                                                                                            MovementWeightProperty.SHA3_256
                                                                                                         );
                                                                                                         byte[] byteValueArray138 = new byte[]{
                                                                                                            -37,
                                                                                                            67,
                                                                                                            67,
                                                                                                            -90,
                                                                                                            100,
                                                                                                            -1,
                                                                                                            58,
                                                                                                            -15,
                                                                                                            12,
                                                                                                            81,
                                                                                                            122,
                                                                                                            -50,
                                                                                                            -119,
                                                                                                            4,
                                                                                                            -116,
                                                                                                            78,
                                                                                                            83,
                                                                                                            -52,
                                                                                                            -67,
                                                                                                            27,
                                                                                                            29,
                                                                                                            15,
                                                                                                            73,
                                                                                                            38,
                                                                                                            19,
                                                                                                            15,
                                                                                                            -96,
                                                                                                            38,
                                                                                                            -37,
                                                                                                            70,
                                                                                                            88,
                                                                                                            -34
                                                                                                         };
                                                                                                         byte[] byteValueArray139 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                            byteValueArray, byteValueArray138, coreByteArrayEntry
                                                                                                         );
                                                                                                         this.byteFlagArray117 = itemEntry133Entry69.resolveByteArray(byteValueArray139);
                                                                                                         value2 = (value2 ^ 0x4f4b728630032febL)
                                                                                                            - 0x1f7d9bc340f7962cL;
                                                                                                      }
                                                                                                   } else {
                                                                                                      ItemEntry133Entry itemEntry133Entry70 = new ItemEntry133Entry(
                                                                                                         MovementWeightProperty.SHA3_256
                                                                                                      );
                                                                                                      byte[] byteValueArray140 = new byte[]{
                                                                                                         75,
                                                                                                         44,
                                                                                                         -16,
                                                                                                         -69,
                                                                                                         -100,
                                                                                                         46,
                                                                                                         -72,
                                                                                                         -32,
                                                                                                         -61,
                                                                                                         -92,
                                                                                                         -16,
                                                                                                         -6,
                                                                                                         -105,
                                                                                                         -41,
                                                                                                         -112,
                                                                                                         32,
                                                                                                         -37,
                                                                                                         111,
                                                                                                         127,
                                                                                                         30,
                                                                                                         -100,
                                                                                                         -120,
                                                                                                         118,
                                                                                                         122,
                                                                                                         -21,
                                                                                                         -76,
                                                                                                         -39,
                                                                                                         112,
                                                                                                         -113,
                                                                                                         -102,
                                                                                                         -72,
                                                                                                         -95
                                                                                                      };
                                                                                                      byte[] byteValueArray141 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                         byteValueArray, byteValueArray140, coreByteArrayEntry
                                                                                                      );
                                                                                                      this.byteFlagArray126 = itemEntry133Entry70.resolveByteArray(byteValueArray141);
                                                                                                      value2 = (value2 ^ -0x5517ff5d62668f8fL)
                                                                                                         + 0x6d4dcd970ff7beacL;
                                                                                                   }
                                                                                                } else {
                                                                                                   ItemEntry133Entry itemEntry133Entry71 = new ItemEntry133Entry(
                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                   );
                                                                                                   byte[] byteValueArray142 = new byte[]{
                                                                                                      -55,
                                                                                                      -25,
                                                                                                      116,
                                                                                                      -5,
                                                                                                      -12,
                                                                                                      -116,
                                                                                                      -70,
                                                                                                      -19,
                                                                                                      -7,
                                                                                                      56,
                                                                                                      -95,
                                                                                                      -91,
                                                                                                      -16,
                                                                                                      -61,
                                                                                                      67,
                                                                                                      -120,
                                                                                                      116,
                                                                                                      116,
                                                                                                      76,
                                                                                                      44,
                                                                                                      107,
                                                                                                      1,
                                                                                                      -29,
                                                                                                      10,
                                                                                                      -37,
                                                                                                      11,
                                                                                                      126,
                                                                                                      -44,
                                                                                                      -47,
                                                                                                      -81,
                                                                                                      -20,
                                                                                                      -53
                                                                                                   };
                                                                                                   byte[] byteValueArray143 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                      byteValueArray, byteValueArray142, coreByteArrayEntry
                                                                                                   );
                                                                                                   this.byteFlagArray133 = itemEntry133Entry71.resolveByteArray(byteValueArray143);
                                                                                                   value2 = (value2 ^ -0x637468edfc148b06L)
                                                                                                      + 0x66ccfed621457530L;
                                                                                                }
                                                                                             } else {
                                                                                                ItemEntry133Entry itemEntry133Entry72 = new ItemEntry133Entry(
                                                                                                   MovementWeightProperty.SHA3_256
                                                                                                );
                                                                                                byte[] byteValueArray144 = new byte[]{
                                                                                                   -25,
                                                                                                   -88,
                                                                                                   -36,
                                                                                                   68,
                                                                                                   -104,
                                                                                                   -53,
                                                                                                   31,
                                                                                                   -81,
                                                                                                   36,
                                                                                                   91,
                                                                                                   25,
                                                                                                   -109,
                                                                                                   -74,
                                                                                                   79,
                                                                                                   17,
                                                                                                   29,
                                                                                                   15,
                                                                                                   3,
                                                                                                   -75,
                                                                                                   33,
                                                                                                   2,
                                                                                                   -39,
                                                                                                   123,
                                                                                                   -26,
                                                                                                   77,
                                                                                                   -48,
                                                                                                   -75,
                                                                                                   -112,
                                                                                                   -52,
                                                                                                   59,
                                                                                                   60,
                                                                                                   89
                                                                                                };
                                                                                                byte[] byteValueArray145 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                   byteValueArray, byteValueArray144, coreByteArrayEntry
                                                                                                );
                                                                                                this.byteFlagArray35 = itemEntry133Entry72.resolveByteArray(byteValueArray145);
                                                                                                value2 = (value2 ^ -0x7153f6493aa5a1f3L) + 0x31c3ad59b21be192L;
                                                                                             }
                                                                                          } else {
                                                                                             ItemEntry133Entry itemEntry133Entry73 = new ItemEntry133Entry(
                                                                                                MovementWeightProperty.SHA3_256
                                                                                             );
                                                                                             byte[] byteValueArray146 = new byte[]{
                                                                                                -65,
                                                                                                21,
                                                                                                5,
                                                                                                76,
                                                                                                -113,
                                                                                                -88,
                                                                                                -28,
                                                                                                4,
                                                                                                96,
                                                                                                100,
                                                                                                57,
                                                                                                39,
                                                                                                -57,
                                                                                                104,
                                                                                                -114,
                                                                                                44,
                                                                                                -100,
                                                                                                36,
                                                                                                75,
                                                                                                32,
                                                                                                110,
                                                                                                -8,
                                                                                                4,
                                                                                                -11,
                                                                                                38,
                                                                                                -19,
                                                                                                -113,
                                                                                                -26,
                                                                                                56,
                                                                                                89,
                                                                                                -15,
                                                                                                -4
                                                                                             };
                                                                                             byte[] byteValueArray147 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                byteValueArray, byteValueArray146, coreByteArrayEntry
                                                                                             );
                                                                                             this.byteFlagArray138 = itemEntry133Entry73.resolveByteArray(byteValueArray147);
                                                                                             value2 = (value2 ^ -0x4a49369f6c855df8L) - 0x550d71fe5a0abdd1L;
                                                                                          }
                                                                                       } else {
                                                                                          ItemEntry133Entry itemEntry133Entry74 = new ItemEntry133Entry(
                                                                                             MovementWeightProperty.SHA3_256
                                                                                          );
                                                                                          byte[] byteValueArray148 = new byte[]{
                                                                                             94,
                                                                                             -124,
                                                                                             -2,
                                                                                             20,
                                                                                             74,
                                                                                             -78,
                                                                                             -123,
                                                                                             71,
                                                                                             -117,
                                                                                             124,
                                                                                             -63,
                                                                                             -48,
                                                                                             -4,
                                                                                             26,
                                                                                             -72,
                                                                                             -85,
                                                                                             -8,
                                                                                             -1,
                                                                                             -111,
                                                                                             61,
                                                                                             -11,
                                                                                             4,
                                                                                             34,
                                                                                             -85,
                                                                                             -76,
                                                                                             -128,
                                                                                             -30,
                                                                                             -109,
                                                                                             70,
                                                                                             -65,
                                                                                             -33,
                                                                                             -35
                                                                                          };
                                                                                          byte[] byteValueArray149 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                             byteValueArray, byteValueArray148, coreByteArrayEntry
                                                                                          );
                                                                                          this.byteFlagArray55 = itemEntry133Entry74.resolveByteArray(byteValueArray149);
                                                                                          value2 = (value2 ^ -0x510ee3ce8aeb9c1dL) + 0x2cdfd0b0ea4512b8L;
                                                                                       }
                                                                                    } else {
                                                                                       ItemEntry133Entry itemEntry133Entry75 = new ItemEntry133Entry(
                                                                                          MovementWeightProperty.SHA3_256
                                                                                       );
                                                                                       byte[] byteValueArray150 = new byte[]{
                                                                                          37,
                                                                                          49,
                                                                                          45,
                                                                                          -17,
                                                                                          -42,
                                                                                          -96,
                                                                                          -105,
                                                                                          -60,
                                                                                          -39,
                                                                                          127,
                                                                                          -128,
                                                                                          102,
                                                                                          -96,
                                                                                          -120,
                                                                                          74,
                                                                                          -20,
                                                                                          54,
                                                                                          70,
                                                                                          -42,
                                                                                          -52,
                                                                                          42,
                                                                                          118,
                                                                                          -74,
                                                                                          -55,
                                                                                          -104,
                                                                                          -66,
                                                                                          18,
                                                                                          -73,
                                                                                          34,
                                                                                          28,
                                                                                          -92,
                                                                                          75
                                                                                       };
                                                                                       byte[] byteValueArray151 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                          byteValueArray, byteValueArray150, coreByteArrayEntry
                                                                                       );
                                                                                       this.byteFlagArray192 = itemEntry133Entry75.resolveByteArray(byteValueArray151);
                                                                                       value2 = (value2 ^ -0x7b427c64160e199L) - 0x700faac36bf314bbL;
                                                                                    }
                                                                                 } else {
                                                                                    ItemEntry133Entry itemEntry133Entry76 = new ItemEntry133Entry(
                                                                                       MovementWeightProperty.SHA3_256
                                                                                    );
                                                                                    byte[] byteValueArray152 = new byte[]{
                                                                                       80,
                                                                                       92,
                                                                                       19,
                                                                                       -84,
                                                                                       -35,
                                                                                       24,
                                                                                       -126,
                                                                                       -85,
                                                                                       40,
                                                                                       -108,
                                                                                       -36,
                                                                                       65,
                                                                                       30,
                                                                                       32,
                                                                                       87,
                                                                                       -34,
                                                                                       -12,
                                                                                       88,
                                                                                       2,
                                                                                       -57,
                                                                                       48,
                                                                                       -21,
                                                                                       -51,
                                                                                       23,
                                                                                       66,
                                                                                       64,
                                                                                       -108,
                                                                                       -43,
                                                                                       -121,
                                                                                       -54,
                                                                                       107,
                                                                                       -89
                                                                                    };
                                                                                    byte[] byteValueArray153 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray152, coreByteArrayEntry);
                                                                                    this.byteFlagArray161 = itemEntry133Entry76.resolveByteArray(byteValueArray153);
                                                                                    value2 = (value2 ^ -0x6493bc9e3026e5cfL) - 0x6ba96c760953f8e1L;
                                                                                 }
                                                                              } else {
                                                                                 ItemEntry133Entry itemEntry133Entry77 = new ItemEntry133Entry(
                                                                                    MovementWeightProperty.SHA3_256
                                                                                 );
                                                                                 byte[] byteValueArray154 = new byte[]{
                                                                                    -51,
                                                                                    -67,
                                                                                    25,
                                                                                    -23,
                                                                                    120,
                                                                                    125,
                                                                                    -37,
                                                                                    101,
                                                                                    25,
                                                                                    -5,
                                                                                    94,
                                                                                    -25,
                                                                                    114,
                                                                                    103,
                                                                                    -4,
                                                                                    -110,
                                                                                    -7,
                                                                                    109,
                                                                                    -56,
                                                                                    1,
                                                                                    -101,
                                                                                    -25,
                                                                                    -82,
                                                                                    36,
                                                                                    90,
                                                                                    -66,
                                                                                    -63,
                                                                                    -77,
                                                                                    -79,
                                                                                    -49,
                                                                                    72,
                                                                                    102
                                                                                 };
                                                                                 byte[] byteValueArray155 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray154, coreByteArrayEntry);
                                                                                 this.byteFlagArray16 = itemEntry133Entry77.resolveByteArray(byteValueArray155);
                                                                                 value2 = (value2 ^ -0x79436269f271fc8cL) - 0x2440c737eb87b772L;
                                                                              }
                                                                           } else {
                                                                              ItemEntry133Entry itemEntry133Entry78 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                              byte[] byteValueArray156 = new byte[]{
                                                                                 12,
                                                                                 -104,
                                                                                 127,
                                                                                 36,
                                                                                 -112,
                                                                                 -101,
                                                                                 100,
                                                                                 17,
                                                                                 -125,
                                                                                 97,
                                                                                 20,
                                                                                 94,
                                                                                 82,
                                                                                 5,
                                                                                 -125,
                                                                                 82,
                                                                                 49,
                                                                                 -67,
                                                                                 27,
                                                                                 -9,
                                                                                 -112,
                                                                                 93,
                                                                                 -15,
                                                                                 126,
                                                                                 12,
                                                                                 -87,
                                                                                 -96,
                                                                                 75,
                                                                                 72,
                                                                                 -39,
                                                                                 56,
                                                                                 -62
                                                                              };
                                                                              byte[] byteValueArray157 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray156, coreByteArrayEntry);
                                                                              this.byteFlagArray88 = itemEntry133Entry78.resolveByteArray(byteValueArray157);
                                                                              value2 = (value2 ^ 0x6c7b5491603417bL) - 0x28e40ed47c64882eL;
                                                                           }
                                                                        } else {
                                                                           ItemEntry133Entry itemEntry133Entry79 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                           byte[] byteValueArray158 = new byte[]{
                                                                              57,
                                                                              -21,
                                                                              -81,
                                                                              -103,
                                                                              -75,
                                                                              2,
                                                                              -89,
                                                                              -11,
                                                                              -127,
                                                                              11,
                                                                              -96,
                                                                              118,
                                                                              69,
                                                                              49,
                                                                              -53,
                                                                              38,
                                                                              49,
                                                                              86,
                                                                              105,
                                                                              55,
                                                                              -58,
                                                                              96,
                                                                              101,
                                                                              -110,
                                                                              0,
                                                                              -118,
                                                                              103,
                                                                              -90,
                                                                              65,
                                                                              -72,
                                                                              91,
                                                                              116
                                                                           };
                                                                           byte[] byteValueArray159 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray158, coreByteArrayEntry);
                                                                           this.byteFlagArray146 = itemEntry133Entry79.resolveByteArray(byteValueArray159);
                                                                           value2 = (value2 ^ -0x6be5b65b913b47e7L) - 0x44261da6f9d7672bL;
                                                                        }
                                                                     } else {
                                                                        ItemEntry133Entry itemEntry133Entry80 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                        byte[] byteValueArray160 = new byte[]{
                                                                           70,
                                                                           9,
                                                                           -102,
                                                                           67,
                                                                           -81,
                                                                           -112,
                                                                           57,
                                                                           -40,
                                                                           -87,
                                                                           42,
                                                                           41,
                                                                           -102,
                                                                           116,
                                                                           81,
                                                                           71,
                                                                           -117,
                                                                           125,
                                                                           75,
                                                                           -10,
                                                                           7,
                                                                           126,
                                                                           -10,
                                                                           -102,
                                                                           -56,
                                                                           70,
                                                                           1,
                                                                           96,
                                                                           -84,
                                                                           16,
                                                                           -84,
                                                                           -78,
                                                                           14
                                                                        };
                                                                        byte[] byteValueArray161 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray160, coreByteArrayEntry);
                                                                        this.byteFlagArray18 = itemEntry133Entry80.resolveByteArray(byteValueArray161);
                                                                        value2 = (value2 ^ 0x69de856f3b1496f6L) + 0x1129d8c82201ebd7L;
                                                                     }
                                                                  } else {
                                                                     ItemEntry133Entry itemEntry133Entry81 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                     byte[] byteValueArray162 = new byte[]{
                                                                        -31,
                                                                        24,
                                                                        -119,
                                                                        58,
                                                                        -33,
                                                                        64,
                                                                        110,
                                                                        -13,
                                                                        -114,
                                                                        -48,
                                                                        -4,
                                                                        108,
                                                                        -128,
                                                                        49,
                                                                        118,
                                                                        77,
                                                                        118,
                                                                        -68,
                                                                        111,
                                                                        116,
                                                                        -106,
                                                                        -127,
                                                                        -74,
                                                                        11,
                                                                        -95,
                                                                        31,
                                                                        -70,
                                                                        87,
                                                                        95,
                                                                        -110,
                                                                        -100,
                                                                        45
                                                                     };
                                                                     byte[] byteValueArray163 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray162, coreByteArrayEntry);
                                                                     this.byteFlagArray201 = itemEntry133Entry81.resolveByteArray(byteValueArray163);
                                                                     value2 = (value2 ^ -0x434798671aaae97eL) + 0x5a4728866d87c9dfL;
                                                                  }
                                                               } else {
                                                                  ItemEntry133Entry itemEntry133Entry82 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                  byte[] byteValueArray164 = new byte[]{
                                                                     -38,
                                                                     -20,
                                                                     89,
                                                                     91,
                                                                     -84,
                                                                     -56,
                                                                     -108,
                                                                     -112,
                                                                     124,
                                                                     70,
                                                                     103,
                                                                     -86,
                                                                     95,
                                                                     7,
                                                                     -13,
                                                                     19,
                                                                     3,
                                                                     -46,
                                                                     36,
                                                                     -38,
                                                                     -25,
                                                                     -90,
                                                                     87,
                                                                     65,
                                                                     -85,
                                                                     -65,
                                                                     110,
                                                                     88,
                                                                     -49,
                                                                     37,
                                                                     75,
                                                                     -53
                                                                  };
                                                                  byte[] byteValueArray165 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray164, coreByteArrayEntry);
                                                                  this.byteFlagArray24 = itemEntry133Entry82.resolveByteArray(byteValueArray165);
                                                                  value2 = (value2 ^ -0x3a52a9b3c3c0bf4aL) + 0x55df2a2cbf9098fbL;
                                                               }
                                                            } else {
                                                               ItemEntry133Entry itemEntry133Entry83 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                               byte[] byteValueArray166 = new byte[]{
                                                                  29,
                                                                  124,
                                                                  61,
                                                                  24,
                                                                  86,
                                                                  49,
                                                                  107,
                                                                  -109,
                                                                  -128,
                                                                  35,
                                                                  26,
                                                                  43,
                                                                  123,
                                                                  -4,
                                                                  -7,
                                                                  78,
                                                                  96,
                                                                  -93,
                                                                  10,
                                                                  -47,
                                                                  67,
                                                                  -52,
                                                                  -41,
                                                                  -117,
                                                                  66,
                                                                  106,
                                                                  -87,
                                                                  -22,
                                                                  -108,
                                                                  -14,
                                                                  17,
                                                                  -23
                                                               };
                                                               byte[] byteValueArray167 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray166, coreByteArrayEntry);
                                                               this.byteFlagArray181 = itemEntry133Entry83.resolveByteArray(byteValueArray167);
                                                               value2 = (value2 ^ -0x4940e97edd3cd95aL) - 0x47779bc87783dca8L;
                                                            }
                                                         } else {
                                                            ItemEntry133Entry itemEntry133Entry84 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                            byte[] byteValueArray168 = new byte[]{
                                                               -58,
                                                               32,
                                                               -85,
                                                               109,
                                                               7,
                                                               -103,
                                                               62,
                                                               -62,
                                                               -26,
                                                               112,
                                                               -34,
                                                               -77,
                                                               -55,
                                                               -125,
                                                               -29,
                                                               -41,
                                                               108,
                                                               122,
                                                               -78,
                                                               21,
                                                               -116,
                                                               -55,
                                                               75,
                                                               -62,
                                                               -31,
                                                               -53,
                                                               -48,
                                                               40,
                                                               -91,
                                                               -110,
                                                               105,
                                                               -67
                                                            };
                                                            byte[] byteValueArray169 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray168, coreByteArrayEntry);
                                                            this.byteFlagArray166 = itemEntry133Entry84.resolveByteArray(byteValueArray169);
                                                            value2 = (value2 ^ -0x256a42af2db90a5eL) + 0x59940ad80c723019L;
                                                         }
                                                      } else {
                                                         ItemEntry133Entry itemEntry133Entry85 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                         byte[] byteValueArray170 = new byte[]{
                                                            114,
                                                            -108,
                                                            -51,
                                                            -16,
                                                            -67,
                                                            -21,
                                                            67,
                                                            -43,
                                                            40,
                                                            66,
                                                            -59,
                                                            58,
                                                            118,
                                                            110,
                                                            12,
                                                            -34,
                                                            -127,
                                                            100,
                                                            39,
                                                            32,
                                                            33,
                                                            -44,
                                                            -67,
                                                            -2,
                                                            33,
                                                            21,
                                                            -56,
                                                            -35,
                                                            -103,
                                                            90,
                                                            86,
                                                            -123
                                                         };
                                                         byte[] byteValueArray171 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray170, coreByteArrayEntry);
                                                         this.byteFlagArray125 = itemEntry133Entry85.resolveByteArray(byteValueArray171);
                                                         value2 = (value2 ^ 0x4e7710c3b18396e1L) + 0x577129dd793e3d04L;
                                                      }
                                                   } else {
                                                      ItemEntry133Entry itemEntry133Entry86 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                      byte[] byteValueArray172 = new byte[]{
                                                         35,
                                                         47,
                                                         40,
                                                         56,
                                                         -101,
                                                         125,
                                                         57,
                                                         75,
                                                         19,
                                                         39,
                                                         28,
                                                         85,
                                                         -112,
                                                         -109,
                                                         91,
                                                         -26,
                                                         -100,
                                                         76,
                                                         14,
                                                         17,
                                                         -27,
                                                         37,
                                                         -110,
                                                         45,
                                                         78,
                                                         -28,
                                                         -90,
                                                         109,
                                                         98,
                                                         39,
                                                         16,
                                                         -41
                                                      };
                                                      byte[] byteValueArray173 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray172, coreByteArrayEntry);
                                                      this.byteFlagArray152 = itemEntry133Entry86.resolveByteArray(byteValueArray173);
                                                      value2 = (value2 ^ 0x4c734ecd8afac65eL) + 0x3561dd1101455ac6L;
                                                   }
                                                } else {
                                                   ItemEntry133Entry itemEntry133Entry87 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                   byte[] byteValueArray174 = new byte[]{
                                                      42,
                                                      91,
                                                      100,
                                                      -29,
                                                      48,
                                                      23,
                                                      106,
                                                      -69,
                                                      -70,
                                                      -55,
                                                      61,
                                                      -39,
                                                      -21,
                                                      76,
                                                      89,
                                                      88,
                                                      -82,
                                                      -63,
                                                      -124,
                                                      -99,
                                                      -22,
                                                      15,
                                                      71,
                                                      -48,
                                                      -29,
                                                      95,
                                                      -92,
                                                      108,
                                                      51,
                                                      15,
                                                      -71,
                                                      68
                                                   };
                                                   byte[] byteValueArray175 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray174, coreByteArrayEntry);
                                                   this.byteFlagArray10 = itemEntry133Entry87.resolveByteArray(byteValueArray175);
                                                   value2 = (value2 ^ -0x2161615562940661L) - 0x54ba654558173ebbL;
                                                }
                                             } else {
                                                ItemEntry133Entry itemEntry133Entry88 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                byte[] byteValueArray176 = new byte[]{
                                                   82,
                                                   -67,
                                                   116,
                                                   100,
                                                   52,
                                                   26,
                                                   -10,
                                                   61,
                                                   104,
                                                   -24,
                                                   -23,
                                                   -50,
                                                   -38,
                                                   32,
                                                   16,
                                                   44,
                                                   -122,
                                                   -94,
                                                   84,
                                                   -9,
                                                   -113,
                                                   23,
                                                   -119,
                                                   96,
                                                   80,
                                                   -57,
                                                   64,
                                                   37,
                                                   -49,
                                                   40,
                                                   48,
                                                   -63
                                                };
                                                byte[] byteValueArray177 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray176, coreByteArrayEntry);
                                                this.byteFlagArray101 = itemEntry133Entry88.resolveByteArray(byteValueArray177);
                                                value2 = (value2 ^ -0x67b910e5136ce308L) + 0x5670597dbe0c4497L;
                                             }
                                          } else {
                                             ItemEntry133Entry itemEntry133Entry89 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                             byte[] byteValueArray178 = new byte[]{
                                                108,
                                                -11,
                                                23,
                                                61,
                                                -81,
                                                -49,
                                                -59,
                                                63,
                                                -102,
                                                124,
                                                -27,
                                                109,
                                                -45,
                                                -112,
                                                78,
                                                -108,
                                                18,
                                                97,
                                                84,
                                                20,
                                                -97,
                                                1,
                                                26,
                                                -21,
                                                -82,
                                                43,
                                                92,
                                                -51,
                                                45,
                                                -6,
                                                89,
                                                -58
                                             };
                                             byte[] byteValueArray179 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray178, coreByteArrayEntry);
                                             this.byteFlagArray190 = itemEntry133Entry89.resolveByteArray(byteValueArray179);
                                             value2 = (value2 ^ -0x33295cb3ebce842fL) - 0x5d90ec92c4272e04L;
                                          }
                                       } else {
                                          ItemEntry133Entry itemEntry133Entry90 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                          byte[] byteValueArray180 = new byte[]{
                                             -59,
                                             -127,
                                             -41,
                                             -68,
                                             42,
                                             -10,
                                             53,
                                             19,
                                             20,
                                             78,
                                             100,
                                             -81,
                                             71,
                                             -93,
                                             -83,
                                             -99,
                                             -37,
                                             31,
                                             120,
                                             81,
                                             -94,
                                             27,
                                             -127,
                                             6,
                                             -17,
                                             -99,
                                             -90,
                                             80,
                                             -5,
                                             58,
                                             118,
                                             9
                                          };
                                          byte[] byteValueArray181 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray180, coreByteArrayEntry);
                                          this.byteFlagArray74 = itemEntry133Entry90.resolveByteArray(byteValueArray181);
                                          value2 = (value2 ^ -0x34c43cc6f655b1e9L) + 0x7e1658ce8eeee4e2L;
                                       }
                                    } else {
                                       ItemEntry133Entry itemEntry133Entry91 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                       byte[] byteValueArray182 = new byte[]{
                                          -60,
                                          1,
                                          -108,
                                          109,
                                          -105,
                                          21,
                                          53,
                                          -97,
                                          95,
                                          -62,
                                          -47,
                                          -6,
                                          4,
                                          69,
                                          13,
                                          -109,
                                          106,
                                          -5,
                                          -62,
                                          -8,
                                          -94,
                                          121,
                                          -74,
                                          -23,
                                          116,
                                          118,
                                          63,
                                          -60,
                                          115,
                                          117,
                                          112,
                                          -67
                                       };
                                       byte[] byteValueArray183 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray182, coreByteArrayEntry);
                                       this.byteFlagArray139 = itemEntry133Entry91.resolveByteArray(byteValueArray183);
                                       value2 = (value2 ^ -0x8f9285d1a8aa54fL) + 0x60068a4fe30bed10L;
                                    }
                                 } else {
                                    ItemEntry133Entry itemEntry133Entry92 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                    byte[] byteValueArray184 = new byte[]{
                                       -81,
                                       69,
                                       -24,
                                       11,
                                       -48,
                                       -96,
                                       33,
                                       55,
                                       48,
                                       -117,
                                       -22,
                                       -34,
                                       25,
                                       55,
                                       -53,
                                       7,
                                       -73,
                                       -23,
                                       27,
                                       119,
                                       -81,
                                       18,
                                       0,
                                       115,
                                       -77,
                                       -110,
                                       53,
                                       21,
                                       112,
                                       -55,
                                       -103,
                                       -8
                                    };
                                    byte[] byteValueArray185 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray184, coreByteArrayEntry);
                                    this.byteFlagArray103 = itemEntry133Entry92.resolveByteArray(byteValueArray185);
                                    value2 = (value2 ^ 0x1daaf3bb9009eeb6L) - 0x196de22288856038L;
                                 }
                              } else {
                                 ItemEntry133Entry itemEntry133Entry93 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                 byte[] byteValueArray186 = new byte[]{
                                    -25,
                                    88,
                                    -2,
                                    -115,
                                    86,
                                    -13,
                                    125,
                                    -115,
                                    103,
                                    56,
                                    -42,
                                    -53,
                                    57,
                                    -40,
                                    -62,
                                    -43,
                                    21,
                                    -71,
                                    -6,
                                    -77,
                                    43,
                                    -109,
                                    -72,
                                    -59,
                                    31,
                                    50,
                                    72,
                                    -120,
                                    53,
                                    103,
                                    -112,
                                    -94
                                 };
                                 byte[] byteValueArray187 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray186, coreByteArrayEntry);
                                 this.byteFlagArray174 = itemEntry133Entry93.resolveByteArray(byteValueArray187);
                                 value2 = (value2 ^ 0x386b0175fc91bc01L) + 0x80b86897eb89c0bL;
                              }
                           } else {
                              ItemEntry133Entry itemEntry133Entry94 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                              byte[] byteValueArray188 = new byte[]{
                                 94,
                                 9,
                                 126,
                                 -47,
                                 68,
                                 11,
                                 92,
                                 70,
                                 113,
                                 81,
                                 115,
                                 105,
                                 64,
                                 -72,
                                 111,
                                 79,
                                 -80,
                                 31,
                                 93,
                                 -90,
                                 -104,
                                 -90,
                                 115,
                                 62,
                                 69,
                                 -53,
                                 36,
                                 37,
                                 118,
                                 -63,
                                 -81,
                                 76
                              };
                              byte[] byteValueArray189 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray188, coreByteArrayEntry);
                              this.byteFlagArray154 = itemEntry133Entry94.resolveByteArray(byteValueArray189);
                              value2 = (value2 ^ -0x184583aec9aca1f4L) - 0x6a19719c0313302dL;
                           }
                        } else {
                           ItemEntry133Entry itemEntry133Entry95 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                           byte[] byteValueArray190 = new byte[]{
                              119,
                              -116,
                              41,
                              -60,
                              18,
                              -1,
                              -6,
                              -20,
                              91,
                              -42,
                              -102,
                              60,
                              -78,
                              -128,
                              47,
                              127,
                              46,
                              91,
                              -3,
                              -6,
                              -60,
                              -13,
                              -124,
                              39,
                              -84,
                              65,
                              -9,
                              -55,
                              60,
                              -65,
                              -31,
                              120
                           };
                           byte[] byteValueArray191 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray190, coreByteArrayEntry);
                           this.byteFlagArray81 = itemEntry133Entry95.resolveByteArray(byteValueArray191);
                           value2 = (value2 ^ 0x58069fa61fa87230L) - 0x446c95037e8b5085L;
                        }
                     } else {
                        ItemEntry133Entry itemEntry133Entry96 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                        byte[] byteValueArray192 = new byte[]{
                           4,
                           55,
                           39,
                           -73,
                           33,
                           70,
                           -95,
                           -36,
                           -103,
                           -44,
                           77,
                           118,
                           -29,
                           -89,
                           122,
                           -66,
                           29,
                           14,
                           -77,
                           19,
                           43,
                           12,
                           83,
                           -96,
                           7,
                           -43,
                           70,
                           28,
                           84,
                           -28,
                           -100,
                           -24
                        };
                        byte[] byteValueArray193 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray192, coreByteArrayEntry);
                        this.byteFlagArray29 = itemEntry133Entry96.resolveByteArray(byteValueArray193);
                        value2 = (value2 ^ -0x1f9c74b64ad851cdL) - 0x4d7d0b16311af95eL;
                     }
                  } else {
                     ItemEntry133Entry itemEntry133Entry97 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                     byte[] byteValueArray194 = new byte[]{
                        -96,
                        100,
                        21,
                        -80,
                        1,
                        41,
                        84,
                        -12,
                        -97,
                        -8,
                        33,
                        7,
                        -67,
                        -17,
                        115,
                        -29,
                        3,
                        59,
                        96,
                        -97,
                        -17,
                        118,
                        -32,
                        -32,
                        109,
                        13,
                        -122,
                        -17,
                        17,
                        82,
                        -13,
                        -10
                     };
                     byte[] byteValueArray195 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray194, coreByteArrayEntry);
                     this.byteFlagArray72 = itemEntry133Entry97.resolveByteArray(byteValueArray195);
                     value2 = (value2 ^ -0x5208ba8dbb2bb691L) + 0x306f194229c7c54bL;
                  }
               } else {
                  ItemEntry133Entry itemEntry133Entry98 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                  byte[] byteValueArray196 = new byte[]{
                     -70,
                     -81,
                     49,
                     20,
                     54,
                     12,
                     -8,
                     -115,
                     44,
                     4,
                     -62,
                     -103,
                     104,
                     113,
                     81,
                     34,
                     -55,
                     95,
                     -43,
                     17,
                     -123,
                     104,
                     39,
                     99,
                     -72,
                     -33,
                     -79,
                     47,
                     79,
                     -66,
                     -16,
                     -127
                  };
                  byte[] byteValueArray197 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray196, coreByteArrayEntry);
                  this.byteFlagArray205 = itemEntry133Entry98.resolveByteArray(byteValueArray197);
                  value2 = (value2 ^ 0x56a6314992ccabacL) - 0x1b019678ce744752L;
               }
            } else {
               ItemEntry133Entry itemEntry133Entry99 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
               byte[] byteValueArray198 = new byte[]{
                  -119,
                  62,
                  -66,
                  -84,
                  7,
                  0,
                  47,
                  -48,
                  31,
                  -47,
                  52,
                  -2,
                  -35,
                  -36,
                  -45,
                  64,
                  105,
                  35,
                  -19,
                  -50,
                  119,
                  81,
                  -123,
                  70,
                  -65,
                  -115,
                  -59,
                  -91,
                  -24,
                  -110,
                  -127,
                  25
               };
               byte[] byteValueArray199 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray198, coreByteArrayEntry);
               this.byteFlagArray130 = itemEntry133Entry99.resolveByteArray(byteValueArray199);
               value2 = (value2 ^ 0x3088ae27f7397615L) + 0x50e204db0da3a886L;
            }
         } else {
            ItemEntry133Entry itemEntry133Entry100 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray200 = new byte[]{
               50,
               69,
               90,
               -55,
               -14,
               -57,
               -93,
               82,
               59,
               -91,
               -81,
               78,
               -121,
               53,
               -87,
               85,
               56,
               -124,
               74,
               -67,
               -103,
               -92,
               -36,
               41,
               -8,
               124,
               -94,
               55,
               67,
               -18,
               -57,
               -103
            };
            byte[] byteValueArray201 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray200, coreByteArrayEntry);
            this.byteFlagArray163 = itemEntry133Entry100.resolveByteArray(byteValueArray201);
            value2 = (value2 ^ 0x7382bba467ade437L) + 0x9ca1f5b933db935L;
         }
      }
   }

   private ByteBuf resolveByteBuf(ByteBuf byteBuf) {
      ByteBuf byteBuf2 = null;
      byte[] byteValueArray = null;
      byte[] byteValueArray2 = null;
      int count6 = 0;
      byte[] byteValueArray3 = null;
      byte[] byteValueArray4 = null;
      int[] countArray8 = null;
      byte[] byteValueArray5 = null;
      byte[] byteValueArray6 = null;
      int count7 = 0;
      int count8 = 0;
      byte[] byteValueArray7 = null;
      int[] countArray9 = null;
      int byteValue = 0;
      int count9 = 0;
      int count10 = 0;
      byte[] byteValueArray8 = null;
      long value = 0x19228a2125cfc55dL;
      long value2 = 0x2b9c99a61ca1fb03L;

      while (true) {
         int count11 = (int)(value % 42L);
         if (count11 != 41) {
            long value3;
            long value4;
            evalBlock: {
               checkBlock: {
                  guardBlock: {
                     searchBlock: {
                        if (count11 != 29) {
                           if (count11 == 39) {
                              countArray9[count9] = byteValueArray8[count9] & 255;
                              value = (value ^ 0x1fbdd429f8b57ee7L) - 0x15bad567d08e82cdL;
                              value2 = (value2 ^ -0x6d707f8fefa2e8f2L) + 0xf6af747c06c12a6L;
                              continue;
                           }

                           if (count11 == 22) {
                              break guardBlock;
                           }

                           if (count11 == 33) {
                              byteValueArray7[byteValue + count10] = (byte)(byteValueArray3[byteValue + count10] ^ (byte)countArray9[count10]);
                              value = (value ^ 0x32dd310f8aef0286L) - 0x4ed44523d553b316L;
                              value2 = (value2 ^ 0x2ed0aaab863c3385L) + 0x348290f6c4b78213L;
                              continue;
                           }

                           if (count11 == 23) {
                              break;
                           }

                           if (count11 == 13) {
                              byte[] byteFlagArray206 = this.byteFlagArray86;
                              countArray8 = this.resolveIndexArray(byteFlagArray206);
                              value = (value ^ 0x2510b7e9cc47feaaL) - 0x1e1831b0e5003ec6L;
                              value2 = (value2 ^ -0x7d70e13c1f396e1eL) + 0x19fa965276ee7bf7L;
                              continue;
                           }

                           if (count11 == 5) {
                              byteBuf2 = null;
                              value = (value ^ -0x7b5127d6104abebL) + 0x3a023f0468b9e672L;
                              value2 = (value2 ^ 0x1192deb37f2da05bL) + 0x267c569e97742ab9L;
                              continue;
                           }

                           if (count11 == 20) {
                              byteBuf2 = Unpooled.copiedBuffer(byteValueArray);
                              break;
                           }

                           if (count11 == 21) {
                              break;
                           }

                           if (count11 == 8) {
                              byteValueArray7 = new byte[count6];
                              value = (value ^ -0x1eed29f82977fb0aL) + 0x34a606d10e9f8374L;
                              value2 = (value2 ^ 0x602ee860a4f99c47L) + 0x161a37fd699ab8d6L;
                              continue;
                           }

                           if (count11 == 6) {
                              count8++;
                              value = (value ^ 0x4b509941f567107aL) + 0x41ec439636f43fa5L;
                              value2 = (value2 ^ -0x6d18aa5f92e23cb8L) - 0x246a766097bfa3caL;
                              break searchBlock;
                           }

                           if (count11 == 11) {
                              count10++;
                              value = (value ^ 0x4aae69840ddfb93cL) - 0x1017c2bb88bd7259L;
                              value2 = (value2 ^ -0x43dcb7fbb3c20634L) - 0x3bc335f89cc22a5aL;
                              break guardBlock;
                           }

                           if (count11 == 18) {
                              System.arraycopy(byteValueArray, byteValueArray2.length + count6, byteValueArray4, 0, 16);
                              value = (value ^ -0x88b7bed050acd2bL) + 0x5a167a11e6e5300cL;
                              value2 = (value2 ^ -0x21d7d3adf08f6c8aL) - 0x22ace3dc8ed02545L;
                              continue;
                           }

                           if (count11 == 17) {
                              count7 = 0;
                              value = (value ^ 0x64bec026e7ef85fcL) + 0x167f5117fdafffc7L;
                              value2 = (value2 ^ 0x1b12e716937de0a8L) + 0x3ab365bd75927d42L;
                              continue;
                           }

                           if (count11 == 10) {
                              break;
                           }

                           if (count11 == 38) {
                              byteBuf.readBytes(byteValueArray);
                              value = (value ^ -0x73b50235ab8f8956L) - 0x6995a586e476e267L;
                              value2 = (value2 ^ 0x69e8a7f107179f9bL) + 0x61037e53846ea87L;
                              continue;
                           }

                           if (count11 == 26) {
                              countArray9 = new int[16];
                              value = (value ^ -0x10e736cdd259487L) + 0x55db1c794eed3abdL;
                              value2 = (value2 ^ -0x32b6e6bcae452a05L) - 0x35e679db7a908a98L;
                              continue;
                           }

                           if (count11 == 34) {
                              if (this.byteFlagArray56.length < 32) {
                                 value = (value ^ 0x381936da1d5b3affL) + 0x1dc32b97faba8b41L;
                                 value2 = (value2 ^ 0x4981f9a775868632L) + 0x1872a860bf20d7ccL;
                                 continue;
                              }

                              value3 = (value ^ 0x375bbccc51538e01L) - 0x26f9c9843ee912cfL;
                              value4 = (value2 ^ 0x17a0dcd736de5de5L) + 0x46645b712ccf08b0L;
                              break evalBlock;
                           }

                           if (count11 == 12) {
                              byteValueArray4 = new byte[16];
                              value = (value ^ 0x348827991e629080L) + 0x4e1ab872a48e7f86L;
                              value2 = (value2 ^ -0x4d024e3ba0634f45L) + 0x694fd3fc6c048710L;
                              continue;
                           }

                           if (count11 == 36) {
                              if (this.byteFlagArray39.length < 32) {
                                 value = (value ^ 0xbef08da0c4f690dL) - 0x2693eaec90a53191L;
                                 value2 = (value2 ^ 0x5758e3fcad890b42L) - 0x4cfe25785a486318L;
                                 continue;
                              }

                              value3 = (value ^ -0x4b82ffe1b42db53bL) + 0x2df884cbc226287dL;
                              value4 = (value2 ^ 0x72831c5400b4d0e8L) + 0x7a8d9aac30c9a98dL;
                              break evalBlock;
                           }

                           if (count11 == 15) {
                              byte[] byteFlagArray207 = this.byteFlagArray86;
                              byteValueArray5 = this.resolveByteArray(byteFlagArray207, countArray8);
                              value = (value ^ 0x13bf3a0bdc31f9efL) + 0x128f2d1fedac7bdfL;
                              value2 = (value2 ^ 0x545c23018f06d1a3L) - 0x27cbceb9e1609daeL;
                              continue;
                           }

                           if (count11 == 37) {
                              count9 = 0;
                              value = (value ^ 0x40dbe19fdcacb46aL) - 0xd112bd14e7bf02fL;
                              value2 = (value2 ^ 0x6818c7f98ba4aaa5L) + 0x5c3f2093183b3b75L;
                              continue;
                           }

                           if (count11 == 28) {
                              byteValue = 0;
                              value = (value ^ -0x4c76368f73fc2c96L) + 0x754bb5e38ad3458bL;
                              value2 = (value2 ^ -0x1b6ef9e12e8856f4L) - 0x336eec9b56e73b12L;
                              continue;
                           }

                           long value5;
                           long value6;
                           if (count11 != 16) {
                              if (count11 == 27) {
                                 this.specialLongEntry.updateState4();
                                 value = (value ^ -0x61d28aea99fcd316L) + 0x3f4f80f159bbbd73L;
                                 value2 = (value2 ^ 0x630a72c2a11b7adL) + 0x40cf81be690461abL;
                                 continue;
                              }

                              if (count11 != 19) {
                                 if (count11 == 2) {
                                    byteValueArray8[15] = 1;
                                    value = (value ^ 0x5a25ad1052a7bcaeL) + 0x881f950b1238092L;
                                    value2 = (value2 ^ -0x706dcf924d229024L) + 0x3ee3870e0009c305L;
                                    continue;
                                 }

                                 if (count11 == 7) {
                                    break searchBlock;
                                 }

                                 if (count11 == 31) {
                                    Object entry = null;
                                    byteValueArray6 = this.resolveByteArray(byteValueArray2, byteValueArray3, (byte[])entry, countArray8, byteValueArray5);
                                    value = (value ^ -0x3b86111004529875L) - 0x785d3d5e9500daa1L;
                                    value2 = (value2 ^ 0x77c075c54f41c629L) + 0x6b4af008d67601ffL;
                                    continue;
                                 }

                                 if (count11 == 32) {
                                    break checkBlock;
                                 }

                                 if (count11 == 40) {
                                    System.arraycopy(byteValueArray, 0, byteValueArray2, 0, byteValueArray2.length);
                                    value = (value ^ 0x3b3e13ea000ff643L) + 0x32c6d785882dc30cL;
                                    value2 = (value2 ^ 0x7ecfa49f2f0a990dL) + 0x44b087aeeb7706d6L;
                                    continue;
                                 }

                                 if (count11 == 35) {
                                    count9 = Math.min(16, byteValueArray3.length - byteValue);
                                    value = (value ^ -0x5f698c8bcdfa7ac5L) + 0x585b1a214debb632L;
                                    value2 = (value2 ^ -0x384dc684f0c034ffL) - 0x39d806aef235e696L;
                                    continue;
                                 }

                                 if (count11 == 24) {
                                    count8 = 0;
                                    value = (value ^ 0x4d21fb51639d417dL) + 0x1cefa39119ccd424L;
                                    value2 = (value2 ^ 0x1b5eb053bdb9adaL) - 0x37b55f04c449168fL;
                                    continue;
                                 }

                                 if (count11 == 4) {
                                    byteValueArray = new byte[byteBuf.readableBytes()];
                                    value = (value ^ -0x6a7e0fafdc5cd0e2L) - 0x49ddca5fa1c0b9f8L;
                                    value2 = (value2 ^ -0x7a46a1661772803aL) - 0x642f270ac1d138f0L;
                                    continue;
                                 }

                                 if (count11 == 0) {
                                    System.arraycopy(byteValueArray, byteValueArray2.length, byteValueArray3, 0, count6);
                                    value = (value ^ -0x4361d64f645facc7L) + 0x46fdf478e783dde5L;
                                    value2 = (value2 ^ 0x4eabe56d6c60b62eL) - 0x2eada2a633c8387fL;
                                    continue;
                                 }

                                 if (count11 != 9) {
                                    if (count11 == 30) {
                                       byteValueArray3 = new byte[count6];
                                       value = (value ^ -0x7ed64b93f41fe0b7L) - 0x32dfe5595a924671L;
                                       value2 = (value2 ^ -0x668bd4b69b5a98aaL) + 0x47763fe4f3b47815L;
                                       continue;
                                    }

                                    if (count11 == 1) {
                                       count7 |= byteValueArray4[count8] ^ byteValueArray6[count8];
                                       value = (value ^ -0x32bac91a9e93e087L) - 0x77163aa36dc5e8f6L;
                                       value2 = (value2 ^ -0x5b7c16faf0efc00fL) - 0x253b3da2f38e2da5L;
                                       continue;
                                    }

                                    if (count11 != 25) {
                                       if (count11 != 14) {
                                          System.arraycopy(byteValueArray2, 0, byteValueArray8, 0, byteValueArray2.length);
                                          value = (value ^ -0x340ae42df8f2d766L) - 0x5e40d1228435697dL;
                                          value2 = (value2 ^ 0x519e7a4fdc80426fL) - 0x550ceed77df7c7cbL;
                                       } else {
                                          count10 = 0;
                                          value = (value ^ -0x7612f185d161c3eeL) - 0x57ed9bcbc470c44eL;
                                          value2 = (value2 ^ 0x19891ae3c8b70519L) + 0x18e4a775db2bc2ebL;
                                       }
                                       continue;
                                    }

                                    count9++;
                                    value = (value ^ 0x606673b7d2183ce6L) - 0xce70f27456ceecbL;
                                    value2 = (value2 ^ -0x1b0103d9b6f96d72L) - 0x6ba260581e67f31bL;
                                    break checkBlock;
                                 }
                              } else {
                                 if (++byteValueArray8[count10] != 0) {
                                    value = (value ^ -0x3ee4e7cc0a8f0f4eL) - 0x68097355c3ee48e5L;
                                    value2 = (value2 ^ -0x4f737d9dbd7e13e0L) + 0x2da957ee32132cf5L;
                                    continue;
                                 }

                                 long value7 = (value ^ 0x340f962f2b513769L) + 0x6303029ee2327df2L;
                                 long value8 = (value2 ^ 0x3a1c7aeba51bc7d8L) - 0x57c6b08b2b76ec83L;
                                 count10--;
                                 value = (value7 ^ -0x2d4cbf4040f2cf27L) + 0x574837db9ddca9ecL;
                                 value2 = (value8 ^ -0x6dd4797740b3ca71L) - 0x7682ce16f819ab76L;
                              }

                              if (count10 >= 12) {
                                 value = (value ^ 0x77a5993aceeccde2L) - 0x544e0463d9847d90L;
                                 value2 = (value2 ^ -0x5bc73b509b52a488L) + 0x2a65ba7153366222L;
                                 continue;
                              }

                              value5 = (value ^ 0x4ce9d44a0884b96cL) + 0x2d8dbb48e9b4bc7fL;
                              value6 = (value2 ^ 0x7eba671659d47228L) + 0x5f03f3a8edb1a427L;
                           } else {
                              value5 = (value ^ -0x710c4054a004cc0dL) - 0x7a51a1a920090f13L;
                              value6 = (value2 ^ -0x63d2432e912d8f08L) - 0x7bda9330a22df088L;
                           }

                           byteValue += 16;
                           value = (value5 ^ -0x8db6f05eb142711L) - 0x35e884a6bec32deeL;
                           value2 = (value6 ^ -0x7d487021cd312c8eL) + 0x36e1a9d5fe7c9d1aL;
                        }

                        if (byteValue >= byteValueArray3.length) {
                           byteBuf2 = Unpooled.copiedBuffer(byteValueArray7);
                           break;
                        }

                        value = (value ^ 0x8ca3a8bf24a865fL) - 0x4eaf61ba2dfb7afbL;
                        value2 = (value2 ^ -0x75a3418a3193ca9bL) + 0xc4e78de50dc02bbL;
                        continue;
                     }

                     if (count8 >= 16) {
                        long value9 = (value ^ -0x7bb8d6dfffcce852L) - 0x7cffc3f4a9a83b91L;
                        long value10 = (value2 ^ 0x69402418bd5c9190L) + 0xd0384a8177f7292L;
                        if (count7 != 0) {
                           break;
                        }

                        long value11 = (value9 ^ -0x56cc9738ea0e6411L) - 0x64fa5d4e1e69d430L;
                        long value12 = (value10 ^ -0x50d3995c489f4042L) + 0x391af0897b812a56L;
                        byteValueArray8 = new byte[16];
                        value = (value11 ^ 0x732e36ae67790c4cL) - 0x2d76f40150191d4L;
                        value2 = (value12 ^ 0x4b906708e12e1808L) + 0x6fa29303f8664559L;
                        continue;
                     }

                     value = (value ^ 0x345db652d6868378L) - 0xb45dd1c6a6ae6b8L;
                     value2 = (value2 ^ -0x43596fca9dcc2e4fL) - 0x3da20ad2baa3367bL;
                     continue;
                  }

                  if (count10 >= count9) {
                     long value13 = (value ^ -0x371411c58bce890bL) + 0xe4cbc35ba53da60L;
                     long value14 = (value2 ^ 0x3625b13b1fbbd95eL) + 0x4dbbbeefd53fba58L;
                     count10 = byteValueArray8.length - 1;
                     value = (value13 ^ -0x43759168c7e8f10bL) + 0x4f4b1b7bed0856fdL;
                     value2 = (value14 ^ -0x3414399dd5ae919fL) + 0x637c4fa80f654f6eL;
                  } else {
                     value = (value ^ -0x261e2a1fcbad5ff3L) + 0x7af1f910c6abdd08L;
                     value2 = (value2 ^ 0x24d946a632e03c54L) + 0x38e504fd671985c2L;
                  }
                  continue;
               }

               if (count9 >= 16) {
                  long value15 = (value ^ -0x23c503352567fcb6L) - 0x6e8891b292f2f945L;
                  long value16 = (value2 ^ 0x3f011413f81e3f89L) + 0x4919888d3d807a08L;
                  this.applyIndexArray(countArray9, countArray8);
                  value = (value15 ^ 0x7ab8ae195fdaef5eL) - 0x3c5095fc7d304a64L;
                  value2 = (value16 ^ -0x317271ff1afccbeL) + 0x15cd9bf317a2466aL;
               } else {
                  value = (value ^ 0x587e7b13001832ddL) + 0x3e3a8a129cd0bd98L;
                  value2 = (value2 ^ 0x3afeedf838c9681fL) - 0x4e7ee8b13289573aL;
               }
               continue;
            }

            byteValueArray2 = new byte[12];
            value = (value3 ^ -0x7386f42f8e58649L) + 0x27cfc959b271607bL;
            value2 = (value4 ^ -0x2b6416cc5c0cf64cL) - 0x53724050afc993a6L;
         } else {
            count6 = byteValueArray.length - byteValueArray2.length - 16;
            value = (value ^ -0x3b73ba65aa089f52L) + 0x6afcc5c410104da1L;
            value2 = (value2 ^ -0x42e2d4f1094b1387L) + 0x493888cfebc00911L;
         }
      }

      return byteBuf2;
   }

   public void applyByteArray2(byte[] byteValueArray) {
      long value = 0x48a351db7b07L;
      value = 0x2c6c34768e3aL ^ value;
      CoreByteArrayEntry coreByteArrayEntry = null;
      long value2 = 0x1664e54c83a47726L;

      while (true) {
         int count6 = (int)(value2 % 104L);
         if (count6 == 19) {
            ItemEntry133Entry itemEntry133Entry = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray2 = new byte[]{
               74,
               -12,
               -43,
               38,
               -50,
               -127,
               52,
               -1,
               -45,
               -60,
               -2,
               8,
               117,
               -105,
               21,
               -15,
               14,
               -69,
               94,
               69,
               -51,
               -54,
               -15,
               -41,
               91,
               36,
               103,
               -15,
               -92,
               119,
               20,
               -7
            };
            byte[] byteValueArray3 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray2, coreByteArrayEntry);
            this.byteFlagArray149 = itemEntry133Entry.resolveByteArray(byteValueArray3);
            value2 = (value2 ^ 0x6d1dd01e73e33a55L) - 0x24c4cac1f15707e1L;
         } else if (count6 == 69) {
            ItemEntry133Entry itemEntry133Entry2 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray4 = new byte[]{
               -13,
               -117,
               56,
               -119,
               81,
               -103,
               -115,
               20,
               93,
               -81,
               -20,
               -64,
               120,
               -116,
               -28,
               87,
               65,
               46,
               103,
               -114,
               -80,
               -6,
               39,
               21,
               -114,
               -8,
               44,
               -76,
               -126,
               119,
               35,
               -39
            };
            byte[] byteValueArray5 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray4, coreByteArrayEntry);
            this.byteFlagArray2 = itemEntry133Entry2.resolveByteArray(byteValueArray5);
            value2 = (value2 ^ 0x7de5fa681d8730abL) + 0xe5db0f265508b77L;
         } else if (count6 == 80) {
            ItemEntry133Entry itemEntry133Entry3 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray6 = new byte[]{
               -46,
               90,
               127,
               97,
               -37,
               127,
               -58,
               -44,
               -107,
               -45,
               36,
               -39,
               31,
               -93,
               -50,
               124,
               10,
               85,
               79,
               110,
               43,
               -81,
               -10,
               109,
               -126,
               -29,
               -46,
               -51,
               -120,
               76,
               89,
               14
            };
            byte[] byteValueArray7 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray6, coreByteArrayEntry);
            this.byteFlagArray188 = itemEntry133Entry3.resolveByteArray(byteValueArray7);
            value2 = (value2 ^ -0x31219fdbf5a20130L) - 0x61281f258cecb96fL;
         } else if (count6 == 41) {
            ItemEntry133Entry itemEntry133Entry4 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray8 = new byte[]{
               34,
               111,
               -103,
               -50,
               -103,
               61,
               49,
               38,
               120,
               -10,
               -104,
               126,
               -81,
               -57,
               67,
               -47,
               57,
               -13,
               60,
               29,
               68,
               109,
               -31,
               80,
               34,
               7,
               77,
               -56,
               -2,
               -60,
               -111,
               -49
            };
            byte[] byteValueArray9 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray8, coreByteArrayEntry);
            this.byteFlagArray70 = itemEntry133Entry4.resolveByteArray(byteValueArray9);
            value2 = (value2 ^ 0x249f73edeb9455f5L) - 0xa7ccbef790c94bL;
         } else if (count6 == 98) {
            ItemEntry133Entry itemEntry133Entry5 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray10 = new byte[]{
               55, -48, -38, 120, -9, 48, -37, 107, 124, 67, -39, -19, -58, -118, 1, -60, 40, 19, 29, 58, -58, -64, 82, 66, -34, 78, 12, 69, 123, -101, 76, 101
            };
            byte[] byteValueArray11 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray10, coreByteArrayEntry);
            this.byteFlagArray34 = itemEntry133Entry5.resolveByteArray(byteValueArray11);
            value2 = (value2 ^ 0x23c27ca037334fdaL) + 0x4377aaa68706ef5L;
         } else if (count6 == 62) {
            ItemEntry133Entry itemEntry133Entry6 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray12 = new byte[]{
               105,
               58,
               -10,
               102,
               24,
               -20,
               37,
               42,
               75,
               -65,
               -38,
               -49,
               -85,
               -111,
               23,
               -103,
               -106,
               -14,
               -113,
               72,
               86,
               93,
               7,
               -29,
               47,
               -49,
               -39,
               -91,
               79,
               -3,
               -4,
               108
            };
            byte[] byteValueArray13 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray12, coreByteArrayEntry);
            this.byteFlagArray65 = itemEntry133Entry6.resolveByteArray(byteValueArray13);
            value2 = (value2 ^ 0x124bc40ecea4fc0fL) + 0x41352436b6daf195L;
         } else if (count6 == 63) {
            ItemEntry133Entry itemEntry133Entry7 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray14 = new byte[]{
               101,
               -123,
               8,
               -11,
               -104,
               -82,
               120,
               -97,
               54,
               -10,
               -69,
               83,
               -99,
               -126,
               -8,
               -115,
               -65,
               86,
               80,
               86,
               2,
               66,
               -120,
               -60,
               -1,
               -128,
               84,
               70,
               -77,
               -107,
               -68,
               -128
            };
            byte[] byteValueArray15 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray14, coreByteArrayEntry);
            this.byteFlagArray123 = itemEntry133Entry7.resolveByteArray(byteValueArray15);
            value2 = (value2 ^ 0x431008a32952b20L) + 0x2b11b6389dd8ca5eL;
         } else if (count6 == 66) {
            ItemEntry133Entry itemEntry133Entry8 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray16 = new byte[]{
               -123,
               -25,
               -24,
               -125,
               -120,
               33,
               123,
               -91,
               -7,
               93,
               -80,
               -59,
               -126,
               -78,
               -103,
               74,
               -1,
               68,
               14,
               56,
               113,
               -11,
               77,
               -40,
               -57,
               85,
               107,
               50,
               -51,
               31,
               5,
               112
            };
            byte[] byteValueArray17 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray16, coreByteArrayEntry);
            this.byteFlagArray92 = itemEntry133Entry8.resolveByteArray(byteValueArray17);
            value2 = (value2 ^ 0x21d1277734ca1686L) - 0x199dd2532994dd53L;
         } else if (count6 == 103) {
            ItemEntry133Entry itemEntry133Entry9 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray18 = new byte[]{
               57,
               -89,
               101,
               -64,
               -119,
               -51,
               52,
               67,
               -62,
               23,
               32,
               -108,
               -92,
               -101,
               -104,
               -92,
               43,
               -126,
               -2,
               122,
               118,
               -57,
               23,
               13,
               -111,
               -114,
               95,
               -12,
               16,
               27,
               -34,
               -24
            };
            byte[] byteValueArray19 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray18, coreByteArrayEntry);
            this.byteFlagArray4 = itemEntry133Entry9.resolveByteArray(byteValueArray19);
            value2 = (value2 ^ -0x5132e279fa78eeddL) - 0x52fd586e018c39acL;
         } else if (count6 == 77) {
            ItemEntry133Entry itemEntry133Entry10 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray20 = new byte[]{
               66,
               79,
               67,
               -35,
               97,
               -99,
               67,
               -35,
               9,
               90,
               -55,
               -113,
               -91,
               -55,
               -112,
               -35,
               35,
               40,
               -46,
               46,
               -85,
               113,
               42,
               -33,
               125,
               34,
               120,
               -32,
               48,
               -124,
               -49,
               119
            };
            byte[] byteValueArray21 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray20, coreByteArrayEntry);
            this.byteFlagArray160 = itemEntry133Entry10.resolveByteArray(byteValueArray21);
            value2 = (value2 ^ 0x572defabfe65e031L) - 0x5b2d81423d5db594L;
         } else if (count6 == 73) {
            ItemEntry133Entry itemEntry133Entry11 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray22 = new byte[]{
               -126, 112, 22, -20, 91, -40, -53, 4, 100, 91, -6, 70, 82, 12, 39, -13, 30, -93, 68, 42, -29, -29, -98, -20, 61, -68, 109, -69, 104, 60, -71, -36
            };
            byte[] byteValueArray23 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray22, coreByteArrayEntry);
            this.byteFlagArray164 = itemEntry133Entry11.resolveByteArray(byteValueArray23);
            value2 = (value2 ^ -0x44b9aef235c8f8b4L) + 0x62fdcfc53c3c3c6cL;
         } else if (count6 == 64) {
            ItemEntry133Entry itemEntry133Entry12 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray24 = new byte[]{
               -111,
               -26,
               -107,
               91,
               88,
               -25,
               -128,
               59,
               -83,
               -83,
               -77,
               116,
               105,
               23,
               8,
               101,
               118,
               34,
               28,
               -118,
               -94,
               -30,
               103,
               39,
               20,
               -18,
               63,
               -29,
               20,
               61,
               -78,
               -45
            };
            byte[] byteValueArray25 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray24, coreByteArrayEntry);
            this.byteFlagArray93 = itemEntry133Entry12.resolveByteArray(byteValueArray25);
            value2 = (value2 ^ 0x33a97dc499571016L) - 0x313c171e0c7e6e30L;
         } else if (count6 == 94) {
            ItemEntry133Entry itemEntry133Entry13 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray26 = new byte[]{
               -50,
               43,
               55,
               -76,
               -114,
               94,
               114,
               -127,
               -43,
               35,
               -112,
               85,
               103,
               70,
               48,
               70,
               85,
               -18,
               -54,
               85,
               -119,
               -81,
               34,
               -14,
               66,
               -80,
               60,
               -103,
               -54,
               79,
               -52,
               -91
            };
            byte[] byteValueArray27 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray26, coreByteArrayEntry);
            this.byteFlagArray127 = itemEntry133Entry13.resolveByteArray(byteValueArray27);
            value2 = (value2 ^ 0x4239ddbc449d1fddL) + 0x533b9e0b6bf2984aL;
         } else if (count6 == 46) {
            ItemEntry133Entry itemEntry133Entry14 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray28 = new byte[]{
               -16,
               -15,
               -51,
               -44,
               -125,
               10,
               -97,
               -90,
               21,
               -90,
               65,
               -128,
               102,
               101,
               98,
               98,
               -86,
               -103,
               103,
               67,
               -5,
               120,
               -99,
               74,
               -120,
               30,
               12,
               112,
               125,
               122,
               30,
               30
            };
            byte[] byteValueArray29 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray28, coreByteArrayEntry);
            this.byteFlagArray198 = itemEntry133Entry14.resolveByteArray(byteValueArray29);
            value2 = (value2 ^ -0x68d12d55c28364d2L) + 0x5a8630c3553465eaL;
         } else if (count6 == 78) {
            ItemEntry133Entry itemEntry133Entry15 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray30 = new byte[]{
               -21,
               -85,
               -30,
               -35,
               10,
               -86,
               58,
               97,
               -53,
               105,
               -69,
               -79,
               -5,
               -74,
               5,
               -44,
               -125,
               -41,
               -107,
               -74,
               -101,
               -121,
               -61,
               -98,
               4,
               84,
               41,
               111,
               16,
               104,
               122,
               -15
            };
            byte[] byteValueArray31 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray30, coreByteArrayEntry);
            this.byteFlagArray = itemEntry133Entry15.resolveByteArray(byteValueArray31);
            value2 = (value2 ^ -0x35dda499c8f7f4faL) + 0x6664ee9c7bce1e9bL;
         } else if (count6 == 61) {
            ItemEntry133Entry itemEntry133Entry16 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray32 = new byte[]{
               44,
               -105,
               -64,
               125,
               48,
               22,
               123,
               -108,
               41,
               103,
               106,
               2,
               -105,
               -117,
               74,
               -36,
               -122,
               104,
               4,
               -15,
               -33,
               61,
               33,
               -104,
               -25,
               5,
               -120,
               115,
               -35,
               97,
               3,
               -31
            };
            byte[] byteValueArray33 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray32, coreByteArrayEntry);
            this.byteFlagArray120 = itemEntry133Entry16.resolveByteArray(byteValueArray33);
            value2 = (value2 ^ 0x3423f5a75b2647d6L) + 0x3309bc74d98162b1L;
         } else if (count6 == 35) {
            ItemEntry133Entry itemEntry133Entry17 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray34 = new byte[]{
               110,
               33,
               -104,
               66,
               -67,
               -121,
               18,
               113,
               81,
               42,
               -3,
               -125,
               -76,
               63,
               -117,
               -119,
               -52,
               8,
               -104,
               -75,
               -88,
               60,
               14,
               -4,
               52,
               -84,
               -91,
               112,
               -102,
               69,
               -63,
               -16
            };
            byte[] byteValueArray35 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray34, coreByteArrayEntry);
            this.byteFlagArray79 = itemEntry133Entry17.resolveByteArray(byteValueArray35);
            value2 = (value2 ^ -0x322bc15a5d8ad039L) + 0x3681c943e82b3206L;
         } else if (count6 == 60) {
            ItemEntry133Entry itemEntry133Entry18 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray36 = new byte[]{
               -43, -68, -40, 97, 10, -84, 22, 68, 43, 70, 64, 18, 77, -111, -26, -23, 36, 23, -45, -83, -37, 16, -15, -14, 70, 8, -108, 98, 75, 36, -34, 58
            };
            byte[] byteValueArray37 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray36, coreByteArrayEntry);
            this.byteFlagArray136 = itemEntry133Entry18.resolveByteArray(byteValueArray37);
            value2 = (value2 ^ 0x35a028f514891af4L) - 0x2b5b1bb8b05cbb73L;
         } else if (count6 == 55) {
            ItemEntry133Entry itemEntry133Entry19 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray38 = new byte[]{
               105, -82, -1, 87, -2, 108, -44, 76, -120, -21, 61, 97, 126, -32, 55, -87, 73, -56, 86, 108, -28, -92, 108, 69, 106, 56, 91, -98, -40, 119, 2, 28
            };
            byte[] byteValueArray39 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray38, coreByteArrayEntry);
            this.byteFlagArray114 = itemEntry133Entry19.resolveByteArray(byteValueArray39);
            value2 = (value2 ^ -0x5293dc84c806e631L) - 0x7cfe5d862c80fb41L;
         } else if (count6 == 54) {
            ItemEntry133Entry itemEntry133Entry20 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray40 = new byte[]{
               -6,
               116,
               95,
               -89,
               -65,
               -61,
               -59,
               -73,
               97,
               -13,
               34,
               9,
               -109,
               76,
               -93,
               92,
               121,
               91,
               -23,
               -88,
               -50,
               -44,
               -67,
               -47,
               71,
               -42,
               100,
               -80,
               -18,
               -23,
               -60,
               -5
            };
            byte[] byteValueArray41 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray40, coreByteArrayEntry);
            this.byteFlagArray23 = itemEntry133Entry20.resolveByteArray(byteValueArray41);
            value2 = (value2 ^ -0x3a59a9c3a3ebd630L) - 0x79869ceb2da0e33cL;
         } else if (count6 == 79) {
            ItemEntry133Entry itemEntry133Entry21 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray42 = new byte[]{
               -101, 87, 107, -82, -49, -49, 71, 76, 86, 10, 108, -22, 0, -45, 88, 8, 29, 12, 125, -96, 36, 14, 70, 58, 124, 84, 82, 70, 55, -52, -67, 3
            };
            byte[] byteValueArray43 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray42, coreByteArrayEntry);
            this.byteFlagArray53 = itemEntry133Entry21.resolveByteArray(byteValueArray43);
            value2 = (value2 ^ -0xbb722da06db537fL) + 0x6612029bdb2be8a0L;
         } else if (count6 == 37) {
            ItemEntry133Entry itemEntry133Entry22 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray44 = new byte[]{
               67, 1, -120, 98, -76, -73, 29, 53, -77, 38, -54, 12, 55, 78, -80, 2, 70, 52, 70, -65, 29, 105, -89, -119, 0, -45, -53, 122, -100, 50, -97, -7
            };
            byte[] byteValueArray45 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray44, coreByteArrayEntry);
            this.byteFlagArray95 = itemEntry133Entry22.resolveByteArray(byteValueArray45);
            value2 = (value2 ^ -0x5608c7835fcb15cdL) + 0x334d175cf009151dL;
         } else if (count6 == 56) {
            ItemEntry133Entry itemEntry133Entry23 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray46 = new byte[]{
               15,
               97,
               66,
               -99,
               -61,
               -63,
               -8,
               32,
               -49,
               -127,
               -4,
               25,
               -35,
               100,
               -42,
               -77,
               -113,
               -109,
               -31,
               86,
               5,
               -11,
               -65,
               -79,
               -105,
               -128,
               1,
               69,
               -64,
               75,
               -22,
               -30
            };
            byte[] byteValueArray47 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray46, coreByteArrayEntry);
            this.byteFlagArray64 = itemEntry133Entry23.resolveByteArray(byteValueArray47);
            value2 = (value2 ^ 0x31a1e12cfefca0c8L) + 0x1c297f5cea3e13daL;
         } else if (count6 == 38) {
            ItemEntry133Entry itemEntry133Entry24 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray48 = new byte[]{
               -102,
               31,
               -20,
               91,
               -3,
               113,
               -12,
               41,
               -80,
               -31,
               76,
               100,
               127,
               -19,
               46,
               -109,
               82,
               -8,
               87,
               -118,
               -1,
               -60,
               -2,
               -88,
               58,
               -46,
               84,
               -25,
               90,
               -69,
               -56,
               3
            };
            byte[] byteValueArray49 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray48, coreByteArrayEntry);
            this.byteFlagArray177 = itemEntry133Entry24.resolveByteArray(byteValueArray49);
            value2 = (value2 ^ -0x315a357cdae68be0L) + 0x433de83e91a9dc1dL;
         } else if (count6 == 43) {
            ItemEntry133Entry itemEntry133Entry25 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray50 = new byte[]{
               -61,
               59,
               5,
               -113,
               -33,
               10,
               -28,
               24,
               113,
               -31,
               -118,
               94,
               -81,
               -111,
               119,
               74,
               -108,
               -99,
               -98,
               36,
               -126,
               81,
               10,
               -122,
               27,
               -4,
               -105,
               116,
               -18,
               -5,
               -108,
               -1
            };
            byte[] byteValueArray51 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray50, coreByteArrayEntry);
            this.byteFlagArray178 = itemEntry133Entry25.resolveByteArray(byteValueArray51);
            value2 = (value2 ^ -0x26eb3b68fc6a37f7L) + 0x68cf13fab0262df6L;
         } else if (count6 == 100) {
            ItemEntry133Entry itemEntry133Entry26 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray52 = new byte[]{
               -103,
               121,
               88,
               -123,
               111,
               -104,
               -125,
               65,
               -106,
               -84,
               31,
               -108,
               29,
               33,
               44,
               100,
               -116,
               -21,
               1,
               34,
               -52,
               95,
               98,
               -71,
               -56,
               -15,
               37,
               77,
               83,
               -102,
               65,
               -102
            };
            byte[] byteValueArray53 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray52, coreByteArrayEntry);
            this.byteFlagArray112 = itemEntry133Entry26.resolveByteArray(byteValueArray53);
            value2 = (value2 ^ -0x21b961aee89a28d7L) + 0x2cb411a537b6895cL;
         } else if (count6 == 25) {
            ItemEntry133Entry itemEntry133Entry27 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray54 = new byte[]{
               -47,
               -62,
               -11,
               -67,
               106,
               100,
               -21,
               52,
               -24,
               -16,
               75,
               69,
               32,
               -121,
               94,
               39,
               -112,
               6,
               -10,
               46,
               -95,
               -1,
               -100,
               33,
               -97,
               -127,
               -42,
               -108,
               104,
               -33,
               87,
               61
            };
            byte[] byteValueArray55 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray54, coreByteArrayEntry);
            this.byteFlagArray142 = itemEntry133Entry27.resolveByteArray(byteValueArray55);
            value2 = (value2 ^ -0x12b5ce3a51944305L) - 0x79d3a7ddecb53826L;
         } else if (count6 == 0) {
            ItemEntry133Entry itemEntry133Entry28 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray56 = new byte[]{
               -15,
               -121,
               -28,
               35,
               -98,
               -116,
               57,
               15,
               34,
               -47,
               32,
               -21,
               -65,
               17,
               61,
               117,
               -31,
               -58,
               53,
               65,
               81,
               65,
               127,
               83,
               -79,
               -65,
               6,
               35,
               120,
               -31,
               -72,
               -57
            };
            byte[] byteValueArray57 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray56, coreByteArrayEntry);
            this.byteFlagArray144 = itemEntry133Entry28.resolveByteArray(byteValueArray57);
            value2 = (value2 ^ -0x2951de07c94d93afL) + 0x7f08e549c4876f06L;
         } else if (count6 == 52) {
            ItemEntry133Entry itemEntry133Entry29 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray58 = new byte[]{
               36, -11, 126, -62, -69, -26, 48, 18, 69, 66, -32, -45, 91, 68, 23, 0, -126, -32, -20, -128, -36, 90, 68, 15, 65, 41, -58, 13, 72, -31, 78, 46
            };
            byte[] byteValueArray59 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray58, coreByteArrayEntry);
            this.byteFlagArray37 = itemEntry133Entry29.resolveByteArray(byteValueArray59);
            value2 = (value2 ^ -0x2c85ae2ec56ac642L) + 0x47c123bc12786b72L;
         } else if (count6 == 5) {
            ItemEntry133Entry itemEntry133Entry30 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray60 = new byte[]{
               -51,
               -56,
               -52,
               75,
               -100,
               13,
               120,
               98,
               50,
               108,
               122,
               22,
               46,
               -101,
               -81,
               93,
               -93,
               -121,
               17,
               -122,
               86,
               108,
               124,
               114,
               82,
               120,
               -115,
               -40,
               -50,
               -114,
               48,
               -53
            };
            byte[] byteValueArray61 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray60, coreByteArrayEntry);
            this.byteFlagArray155 = itemEntry133Entry30.resolveByteArray(byteValueArray61);
            value2 = (value2 ^ -0x1d571318dbdf0ed7L) - 0x5b58960ee160c045L;
         } else if (count6 == 6) {
            ItemEntry133Entry itemEntry133Entry31 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray62 = new byte[]{
               -100,
               -56,
               11,
               -128,
               -69,
               -8,
               94,
               105,
               101,
               -19,
               -121,
               100,
               -17,
               31,
               -102,
               56,
               93,
               -109,
               -52,
               -23,
               -42,
               -27,
               -27,
               -40,
               12,
               -77,
               -117,
               -49,
               -27,
               16,
               73,
               33
            };
            byte[] byteValueArray63 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray62, coreByteArrayEntry);
            this.byteFlagArray122 = itemEntry133Entry31.resolveByteArray(byteValueArray63);
            value2 = (value2 ^ 0x22122b6631500341L) + 0x254b68bb265ba368L;
         } else if (count6 == 96) {
            ItemEntry133Entry itemEntry133Entry32 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray64 = new byte[]{
               -87,
               -36,
               30,
               111,
               27,
               -110,
               69,
               115,
               -13,
               -123,
               -101,
               -54,
               -29,
               -112,
               -38,
               -30,
               112,
               96,
               50,
               81,
               -62,
               58,
               110,
               54,
               -43,
               -115,
               -22,
               102,
               79,
               17,
               -2,
               -106
            };
            byte[] byteValueArray65 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray64, coreByteArrayEntry);
            this.byteFlagArray129 = itemEntry133Entry32.resolveByteArray(byteValueArray65);
            value2 = (value2 ^ 0x693ae9847992d513L) + 0x692a20b15edb7cceL;
         } else if (count6 == 82) {
            ItemEntry133Entry itemEntry133Entry33 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray66 = new byte[]{
               56,
               70,
               -98,
               47,
               -116,
               67,
               96,
               -112,
               -55,
               40,
               14,
               -53,
               64,
               126,
               -26,
               37,
               -89,
               -115,
               -33,
               116,
               -19,
               105,
               -26,
               -7,
               -78,
               -82,
               59,
               74,
               114,
               76,
               45,
               -35
            };
            byte[] byteValueArray67 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray66, coreByteArrayEntry);
            this.byteFlagArray77 = itemEntry133Entry33.resolveByteArray(byteValueArray67);
            value2 = (value2 ^ 0x20456b5cbe69ec1aL) + 0x3380d22c28252c9fL;
         } else if (count6 == 58) {
            ItemEntry133Entry itemEntry133Entry34 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray68 = new byte[]{
               106,
               -118,
               -127,
               1,
               -49,
               -90,
               -20,
               -66,
               59,
               -42,
               33,
               79,
               1,
               24,
               90,
               -123,
               121,
               -17,
               -122,
               -60,
               49,
               -56,
               126,
               26,
               11,
               84,
               -16,
               -63,
               -55,
               73,
               -125,
               -115
            };
            byte[] byteValueArray69 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray68, coreByteArrayEntry);
            this.byteFlagArray148 = itemEntry133Entry34.resolveByteArray(byteValueArray69);
            value2 = (value2 ^ 0x6673b1f21966087bL) + 0x459cac6e79b9cc69L;
         } else if (count6 == 85) {
            ItemEntry133Entry itemEntry133Entry35 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray70 = new byte[]{
               -78, 3, 61, -89, -1, -110, 94, 95, 106, 50, -113, 65, -90, 33, 96, 84, -74, 84, -125, -56, 110, 11, 97, 19, 24, -64, 60, -59, 51, -27, 39, 73
            };
            byte[] byteValueArray71 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray70, coreByteArrayEntry);
            this.byteFlagArray17 = itemEntry133Entry35.resolveByteArray(byteValueArray71);
            value2 = (value2 ^ 0x3da70851f992752fL) - 0xc8ed3dc8e4a57c9L;
         } else if (count6 == 91) {
            ItemEntry133Entry itemEntry133Entry36 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray72 = new byte[]{
               -54,
               14,
               90,
               -5,
               -89,
               33,
               -64,
               -78,
               112,
               -22,
               45,
               -75,
               62,
               -56,
               -52,
               -105,
               64,
               53,
               25,
               98,
               -88,
               -14,
               -25,
               103,
               0,
               -59,
               -73,
               83,
               115,
               -72,
               52,
               -99
            };
            byte[] byteValueArray73 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray72, coreByteArrayEntry);
            this.byteFlagArray78 = itemEntry133Entry36.resolveByteArray(byteValueArray73);
            value2 = (value2 ^ -0x148a542640b401deL) + 0x680f2b10711c9365L;
         } else if (count6 == 27) {
            ItemEntry133Entry itemEntry133Entry37 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray74 = new byte[]{
               -7, 16, 42, 78, 25, 61, 12, -5, 82, -66, 97, 72, 51, 91, 115, -21, 67, -20, 77, 37, 60, 69, 124, 86, -51, -3, -42, 102, -98, -125, 110, 54
            };
            byte[] byteValueArray75 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray74, coreByteArrayEntry);
            this.byteFlagArray43 = itemEntry133Entry37.resolveByteArray(byteValueArray75);
            value2 = (value2 ^ -0x5153c3a02c50eccdL) - 0x6f864374b2f7a0ebL;
         } else if (count6 == 76) {
            ItemEntry133Entry itemEntry133Entry38 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray76 = new byte[]{
               122,
               -28,
               -70,
               92,
               111,
               28,
               -116,
               -104,
               103,
               61,
               40,
               86,
               120,
               90,
               -94,
               -63,
               24,
               89,
               44,
               45,
               67,
               100,
               66,
               8,
               -34,
               75,
               -114,
               -124,
               120,
               104,
               121,
               -90
            };
            byte[] byteValueArray77 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray76, coreByteArrayEntry);
            this.byteFlagArray45 = itemEntry133Entry38.resolveByteArray(byteValueArray77);
            value2 = (value2 ^ -0x674e36936cbc791aL) + 0x3ca662d5118b7526L;
         } else if (count6 == 84) {
            ItemEntry133Entry itemEntry133Entry39 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray78 = new byte[]{
               -109,
               29,
               63,
               92,
               72,
               37,
               -26,
               29,
               7,
               -8,
               62,
               -20,
               -61,
               -23,
               67,
               27,
               2,
               -79,
               106,
               -39,
               -62,
               -23,
               -24,
               55,
               127,
               -111,
               -100,
               -41,
               -114,
               111,
               -51,
               -62
            };
            byte[] byteValueArray79 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray78, coreByteArrayEntry);
            this.byteFlagArray186 = itemEntry133Entry39.resolveByteArray(byteValueArray79);
            value2 = (value2 ^ -0x39d90f755a705ebaL) - 0x1182961693c9250dL;
         } else if (count6 == 18) {
            ItemEntry133Entry itemEntry133Entry40 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray80 = new byte[]{
               26,
               -97,
               44,
               -9,
               -28,
               57,
               -56,
               43,
               -93,
               -66,
               -70,
               -13,
               -38,
               -116,
               -50,
               127,
               -100,
               46,
               -124,
               -37,
               33,
               42,
               -69,
               81,
               -114,
               -108,
               46,
               -33,
               -11,
               -23,
               -29,
               -13
            };
            byte[] byteValueArray81 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray80, coreByteArrayEntry);
            this.byteFlagArray80 = itemEntry133Entry40.resolveByteArray(byteValueArray81);
            value2 = (value2 ^ -0x1a550490497a1b9fL) - 0x7c422c016e88f398L;
         } else if (count6 == 7) {
            ItemEntry133Entry itemEntry133Entry41 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray82 = new byte[]{
               -52,
               -122,
               -98,
               2,
               -127,
               72,
               123,
               103,
               -68,
               15,
               -104,
               85,
               106,
               -96,
               99,
               122,
               75,
               -64,
               93,
               102,
               -79,
               98,
               -65,
               72,
               99,
               69,
               -15,
               -45,
               -124,
               103,
               -70,
               -98
            };
            byte[] byteValueArray83 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray82, coreByteArrayEntry);
            this.byteFlagArray116 = itemEntry133Entry41.resolveByteArray(byteValueArray83);
            value2 = (value2 ^ 0x7e1a79b8377330cdL) + 0x1b9a191f825af888L;
         } else if (count6 == 13) {
            ItemEntry133Entry itemEntry133Entry42 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray84 = new byte[]{
               64,
               -43,
               99,
               -22,
               -68,
               -92,
               -83,
               -56,
               -53,
               -68,
               35,
               74,
               6,
               -3,
               51,
               113,
               41,
               -30,
               -27,
               114,
               108,
               98,
               32,
               84,
               -72,
               125,
               72,
               -92,
               60,
               -104,
               -59,
               117
            };
            byte[] byteValueArray85 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray84, coreByteArrayEntry);
            this.byteFlagArray173 = itemEntry133Entry42.resolveByteArray(byteValueArray85);
            value2 = (value2 ^ -0x5f6c06c7d18b713dL) - 0x50c6863f63a34a63L;
         } else if (count6 == 70) {
            ItemEntry133Entry itemEntry133Entry43 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray86 = new byte[]{
               -105,
               42,
               -123,
               -4,
               42,
               -68,
               83,
               127,
               -45,
               37,
               -78,
               113,
               71,
               -58,
               -45,
               -85,
               86,
               -67,
               -97,
               0,
               -31,
               -82,
               119,
               64,
               -126,
               1,
               -28,
               -115,
               96,
               80,
               97,
               74
            };
            byte[] byteValueArray87 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray86, coreByteArrayEntry);
            this.byteFlagArray26 = itemEntry133Entry43.resolveByteArray(byteValueArray87);
            value2 = (value2 ^ -0xad4fa55ac5c4cacL) - 0x6598d8b66c8eaf97L;
         } else if (count6 == 99) {
            ItemEntry133Entry itemEntry133Entry44 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray88 = new byte[]{
               116, -87, -35, -89, -21, 46, -61, -40, -4, 99, 95, 35, 66, -121, 26, -22, 50, 47, -89, -94, 24, -117, 124, 16, 62, 105, 108, 73, 51, 23, 12, 51
            };
            byte[] byteValueArray89 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray88, coreByteArrayEntry);
            this.byteFlagArray40 = itemEntry133Entry44.resolveByteArray(byteValueArray89);
            value2 = (value2 ^ -0x7d8fcb3dede13ae9L) - 0x6d7b2196d927bd19L;
         } else if (count6 == 28) {
            ItemEntry133Entry itemEntry133Entry45 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray90 = new byte[]{
               70,
               82,
               -65,
               -123,
               -106,
               -96,
               -106,
               86,
               -87,
               -65,
               -46,
               45,
               -113,
               100,
               60,
               -25,
               13,
               -95,
               -15,
               -122,
               -44,
               2,
               28,
               113,
               9,
               -62,
               -35,
               -54,
               -14,
               -27,
               69,
               75
            };
            byte[] byteValueArray91 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray90, coreByteArrayEntry);
            this.byteFlagArray48 = itemEntry133Entry45.resolveByteArray(byteValueArray91);
            value2 = (value2 ^ -0x28d0181f340021f4L) - 0x31e18b24c5a42767L;
         } else if (count6 == 65) {
            ItemEntry133Entry itemEntry133Entry46 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray92 = new byte[]{
               -62,
               12,
               -77,
               -115,
               -5,
               78,
               -62,
               69,
               -45,
               49,
               23,
               27,
               -50,
               13,
               28,
               51,
               -108,
               -84,
               -128,
               -47,
               119,
               54,
               120,
               55,
               -15,
               -96,
               -31,
               121,
               85,
               -96,
               26,
               34
            };
            byte[] byteValueArray93 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray92, coreByteArrayEntry);
            this.byteFlagArray118 = itemEntry133Entry46.resolveByteArray(byteValueArray93);
            value2 = (value2 ^ -0x78fa7133bd5d364dL) - 0x738d53651be51d6aL;
         } else if (count6 == 16) {
            ItemEntry133Entry itemEntry133Entry47 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray94 = new byte[]{
               -32,
               82,
               -74,
               85,
               -69,
               11,
               27,
               -70,
               54,
               -88,
               98,
               64,
               -116,
               -8,
               -87,
               -97,
               -72,
               -106,
               -81,
               -128,
               85,
               32,
               125,
               -79,
               -44,
               -1,
               -126,
               124,
               32,
               59,
               56,
               83
            };
            byte[] byteValueArray95 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray94, coreByteArrayEntry);
            this.byteFlagArray107 = itemEntry133Entry47.resolveByteArray(byteValueArray95);
            value2 = (value2 ^ -0x7bcf6b5ca2c3e9adL) - 0x4173def198217e57L;
         } else if (count6 == 29) {
            ItemEntry133Entry itemEntry133Entry48 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray96 = new byte[]{
               -50,
               116,
               12,
               78,
               -55,
               120,
               115,
               -38,
               35,
               -17,
               74,
               -118,
               79,
               -20,
               99,
               -52,
               -27,
               -17,
               -62,
               -45,
               -118,
               -50,
               103,
               -72,
               14,
               -85,
               -61,
               0,
               -93,
               51,
               -16,
               -15
            };
            byte[] byteValueArray97 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray96, coreByteArrayEntry);
            this.byteFlagArray131 = itemEntry133Entry48.resolveByteArray(byteValueArray97);
            value2 = (value2 ^ 0x68ba2888e2247eb2L) + 0x1d0b3124cbca7761L;
         } else if (count6 == 22) {
            ItemEntry133Entry itemEntry133Entry49 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray98 = new byte[]{
               117,
               -106,
               73,
               -32,
               -97,
               -43,
               85,
               112,
               -26,
               -50,
               -122,
               98,
               3,
               35,
               25,
               -46,
               -43,
               36,
               -107,
               88,
               57,
               7,
               -116,
               -97,
               -99,
               -97,
               -30,
               61,
               59,
               -21,
               -37,
               85
            };
            byte[] byteValueArray99 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray98, coreByteArrayEntry);
            this.byteFlagArray140 = itemEntry133Entry49.resolveByteArray(byteValueArray99);
            value2 = (value2 ^ 0x5773623da2ce2c0cL) + 0x431f567b80622e8eL;
         } else if (count6 == 9) {
            ItemEntry133Entry itemEntry133Entry50 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray100 = new byte[]{
               -97,
               -25,
               42,
               -45,
               -12,
               78,
               20,
               110,
               -59,
               106,
               -41,
               -50,
               -124,
               -50,
               -105,
               -93,
               -119,
               -123,
               -66,
               62,
               -60,
               61,
               33,
               -117,
               -64,
               80,
               -82,
               -2,
               31,
               -12,
               21,
               -95
            };
            byte[] byteValueArray101 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray100, coreByteArrayEntry);
            this.byteFlagArray67 = itemEntry133Entry50.resolveByteArray(byteValueArray101);
            value2 = (value2 ^ -0x4e25f9b7d569bf83L) - 0x64137c08af46ff22L;
         } else if (count6 != 8) {
            if (count6 == 14) {
               this.applyByteArray(byteValueArray);
               break;
            }

            if (count6 != 57) {
               if (count6 != 39) {
                  if (count6 != 10) {
                     if (count6 != 32) {
                        if (count6 != 95) {
                           if (count6 != 87) {
                              if (count6 != 2) {
                                 if (count6 == 68) {
                                    ItemEntry133Entry itemEntry133Entry51 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                    byte[] byteValueArray102 = new byte[]{
                                       -95,
                                       -45,
                                       -82,
                                       -66,
                                       -13,
                                       -52,
                                       10,
                                       -26,
                                       19,
                                       9,
                                       63,
                                       79,
                                       -105,
                                       -12,
                                       -15,
                                       -58,
                                       6,
                                       84,
                                       38,
                                       -4,
                                       -95,
                                       67,
                                       119,
                                       53,
                                       45,
                                       -125,
                                       -92,
                                       -96,
                                       -14,
                                       81,
                                       111,
                                       -36
                                    };
                                    byte[] byteValueArray103 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray102, coreByteArrayEntry);
                                    this.byteFlagArray203 = itemEntry133Entry51.resolveByteArray(byteValueArray103);
                                    value2 = (value2 ^ 0x3eb2172e533092aL) - 0x32691100230859b7L;
                                 } else if (count6 != 12) {
                                    if (count6 != 15) {
                                       if (count6 != 33) {
                                          if (count6 != 71) {
                                             if (count6 != 86) {
                                                if (count6 == 4) {
                                                   break;
                                                }

                                                if (count6 != 17) {
                                                   if (count6 != 93) {
                                                      if (count6 != 34) {
                                                         if (count6 != 45) {
                                                            if (count6 != 3) {
                                                               if (count6 != 74) {
                                                                  if (count6 != 89) {
                                                                     if (count6 != 101) {
                                                                        if (count6 != 75) {
                                                                           if (count6 != 88) {
                                                                              if (count6 != 31) {
                                                                                 if (count6 == 24) {
                                                                                    ItemEntry133Entry itemEntry133Entry52 = new ItemEntry133Entry(
                                                                                       MovementWeightProperty.SHA3_256
                                                                                    );
                                                                                    byte[] byteValueArray104 = new byte[]{
                                                                                       -32,
                                                                                       69,
                                                                                       -51,
                                                                                       -25,
                                                                                       33,
                                                                                       -53,
                                                                                       86,
                                                                                       -98,
                                                                                       27,
                                                                                       15,
                                                                                       67,
                                                                                       51,
                                                                                       39,
                                                                                       72,
                                                                                       62,
                                                                                       -122,
                                                                                       11,
                                                                                       105,
                                                                                       -99,
                                                                                       86,
                                                                                       -71,
                                                                                       -4,
                                                                                       48,
                                                                                       18,
                                                                                       41,
                                                                                       70,
                                                                                       -97,
                                                                                       71,
                                                                                       -10,
                                                                                       -105,
                                                                                       -120,
                                                                                       44
                                                                                    };
                                                                                    byte[] byteValueArray105 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray104, coreByteArrayEntry);
                                                                                    this.byteFlagArray168 = itemEntry133Entry52.resolveByteArray(byteValueArray105);
                                                                                    value2 = (value2 ^ 0x47bbbb5265651dd6L) - 0x59ed8622799227afL;
                                                                                 } else if (count6 != 92) {
                                                                                    if (count6 != 67) {
                                                                                       if (count6 != 23) {
                                                                                          if (count6 != 102) {
                                                                                             if (count6 != 59) {
                                                                                                if (count6 == 30) {
                                                                                                   ItemEntry133Entry itemEntry133Entry53 = new ItemEntry133Entry(
                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                   );
                                                                                                   byte[] byteValueArray106 = new byte[]{
                                                                                                      1,
                                                                                                      -93,
                                                                                                      98,
                                                                                                      -5,
                                                                                                      71,
                                                                                                      -87,
                                                                                                      -99,
                                                                                                      42,
                                                                                                      34,
                                                                                                      78,
                                                                                                      -65,
                                                                                                      107,
                                                                                                      44,
                                                                                                      46,
                                                                                                      -33,
                                                                                                      -6,
                                                                                                      -47,
                                                                                                      -125,
                                                                                                      121,
                                                                                                      118,
                                                                                                      -66,
                                                                                                      84,
                                                                                                      -7,
                                                                                                      31,
                                                                                                      59,
                                                                                                      -22,
                                                                                                      63,
                                                                                                      -31,
                                                                                                      -91,
                                                                                                      -50,
                                                                                                      -13,
                                                                                                      -106
                                                                                                   };
                                                                                                   byte[] byteValueArray107 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                      byteValueArray, byteValueArray106, coreByteArrayEntry
                                                                                                   );
                                                                                                   this.byteFlagArray32 = itemEntry133Entry53.resolveByteArray(byteValueArray107);
                                                                                                   value2 = (value2 ^ -0x4239c23d1d002ca7L)
                                                                                                      + 0x6603e19ddcc1bd8fL;
                                                                                                } else if (count6 != 97) {
                                                                                                   if (count6 != 50) {
                                                                                                      if (count6 != 49) {
                                                                                                         if (count6 != 1) {
                                                                                                            if (count6 != 40) {
                                                                                                               if (count6 != 81) {
                                                                                                                  if (count6 != 53) {
                                                                                                                     if (count6 != 21) {
                                                                                                                        if (count6 != 42) {
                                                                                                                           if (count6 != 44) {
                                                                                                                              if (count6 != 36) {
                                                                                                                                 if (count6 != 11) {
                                                                                                                                    if (count6 != 90) {
                                                                                                                                       if (count6 != 48) {
                                                                                                                                          if (count6 != 72) {
                                                                                                                                             if (count6 != 47) {
                                                                                                                                                if (count6 != 26
                                                                                                                                                   )
                                                                                                                                                 {
                                                                                                                                                   if (count6
                                                                                                                                                      != 51) {
                                                                                                                                                      if (count6
                                                                                                                                                         == 20) {
                                                                                                                                                         ItemEntry133Entry itemEntry133Entry54 = new ItemEntry133Entry(
                                                                                                                                                            MovementWeightProperty.SHA3_256
                                                                                                                                                         );
                                                                                                                                                         byte[] byteValueArray108 = new byte[]{
                                                                                                                                                            98,
                                                                                                                                                            -3,
                                                                                                                                                            -100,
                                                                                                                                                            -119,
                                                                                                                                                            8,
                                                                                                                                                            -22,
                                                                                                                                                            -57,
                                                                                                                                                            -83,
                                                                                                                                                            65,
                                                                                                                                                            23,
                                                                                                                                                            -69,
                                                                                                                                                            98,
                                                                                                                                                            -100,
                                                                                                                                                            116,
                                                                                                                                                            38,
                                                                                                                                                            30,
                                                                                                                                                            99,
                                                                                                                                                            -115,
                                                                                                                                                            110,
                                                                                                                                                            51,
                                                                                                                                                            -88,
                                                                                                                                                            107,
                                                                                                                                                            50,
                                                                                                                                                            -69,
                                                                                                                                                            -102,
                                                                                                                                                            95,
                                                                                                                                                            -3,
                                                                                                                                                            -51,
                                                                                                                                                            88,
                                                                                                                                                            -114,
                                                                                                                                                            58,
                                                                                                                                                            55
                                                                                                                                                         };
                                                                                                                                                         byte[] byteValueArray109 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                            byteValueArray,
                                                                                                                                                            byteValueArray108,
                                                                                                                                                            coreByteArrayEntry
                                                                                                                                                         );
                                                                                                                                                         this.byteFlagArray121 = itemEntry133Entry54.resolveByteArray(
                                                                                                                                                            byteValueArray109
                                                                                                                                                         );
                                                                                                                                                         value2 = (
                                                                                                                                                               value2
                                                                                                                                                                  ^ -0x41d2db33e739e3c9L
                                                                                                                                                            )
                                                                                                                                                            + 0x63e7343d9b60b0fcL;
                                                                                                                                                      } else {
                                                                                                                                                         ItemEntry133Entry itemEntry133Entry55 = new ItemEntry133Entry(
                                                                                                                                                            MovementWeightProperty.SHA3_256
                                                                                                                                                         );
                                                                                                                                                         byte[] byteValueArray110 = new byte[]{
                                                                                                                                                            27,
                                                                                                                                                            32,
                                                                                                                                                            -113,
                                                                                                                                                            3,
                                                                                                                                                            122,
                                                                                                                                                            122,
                                                                                                                                                            -20,
                                                                                                                                                            -29,
                                                                                                                                                            75,
                                                                                                                                                            -44,
                                                                                                                                                            14,
                                                                                                                                                            -51,
                                                                                                                                                            60,
                                                                                                                                                            -84,
                                                                                                                                                            53,
                                                                                                                                                            92,
                                                                                                                                                            -9,
                                                                                                                                                            59,
                                                                                                                                                            71,
                                                                                                                                                            -128,
                                                                                                                                                            -32,
                                                                                                                                                            -21,
                                                                                                                                                            -1,
                                                                                                                                                            -82,
                                                                                                                                                            10,
                                                                                                                                                            -70,
                                                                                                                                                            -92,
                                                                                                                                                            -98,
                                                                                                                                                            119,
                                                                                                                                                            125,
                                                                                                                                                            -105,
                                                                                                                                                            31
                                                                                                                                                         };
                                                                                                                                                         byte[] byteValueArray111 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                            byteValueArray,
                                                                                                                                                            byteValueArray110,
                                                                                                                                                            coreByteArrayEntry
                                                                                                                                                         );
                                                                                                                                                         this.byteFlagArray3 = itemEntry133Entry55.resolveByteArray(
                                                                                                                                                            byteValueArray111
                                                                                                                                                         );
                                                                                                                                                         value2 = (
                                                                                                                                                               value2
                                                                                                                                                                  ^ 0xb4d86bfa8eb774cL
                                                                                                                                                            )
                                                                                                                                                            + 0x1843e5d507c5ef15L;
                                                                                                                                                      }
                                                                                                                                                   } else {
                                                                                                                                                      ItemEntry133Entry itemEntry133Entry56 = new ItemEntry133Entry(
                                                                                                                                                         MovementWeightProperty.SHA3_256
                                                                                                                                                      );
                                                                                                                                                      byte[] byteValueArray112 = new byte[]{
                                                                                                                                                         83,
                                                                                                                                                         -78,
                                                                                                                                                         116,
                                                                                                                                                         -77,
                                                                                                                                                         -10,
                                                                                                                                                         71,
                                                                                                                                                         -111,
                                                                                                                                                         -89,
                                                                                                                                                         -48,
                                                                                                                                                         -110,
                                                                                                                                                         49,
                                                                                                                                                         46,
                                                                                                                                                         -122,
                                                                                                                                                         -109,
                                                                                                                                                         -27,
                                                                                                                                                         -70,
                                                                                                                                                         13,
                                                                                                                                                         -83,
                                                                                                                                                         41,
                                                                                                                                                         47,
                                                                                                                                                         48,
                                                                                                                                                         -38,
                                                                                                                                                         14,
                                                                                                                                                         50,
                                                                                                                                                         -5,
                                                                                                                                                         64,
                                                                                                                                                         -44,
                                                                                                                                                         -105,
                                                                                                                                                         -39,
                                                                                                                                                         -125,
                                                                                                                                                         -122,
                                                                                                                                                         18
                                                                                                                                                      };
                                                                                                                                                      byte[] byteValueArray113 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                         byteValueArray,
                                                                                                                                                         byteValueArray112,
                                                                                                                                                         coreByteArrayEntry
                                                                                                                                                      );
                                                                                                                                                      this.byteFlagArray111 = itemEntry133Entry56.resolveByteArray(
                                                                                                                                                         byteValueArray113
                                                                                                                                                      );
                                                                                                                                                      value2 = (
                                                                                                                                                            value2
                                                                                                                                                               ^ -0x7feb89ab57e85e1cL
                                                                                                                                                         )
                                                                                                                                                         + 0x7d7c98fd5a3db34bL;
                                                                                                                                                   }
                                                                                                                                                } else {
                                                                                                                                                   ItemEntry133Entry itemEntry133Entry57 = new ItemEntry133Entry(
                                                                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                                                                   );
                                                                                                                                                   byte[] byteValueArray114 = new byte[]{
                                                                                                                                                      97,
                                                                                                                                                      -28,
                                                                                                                                                      -12,
                                                                                                                                                      4,
                                                                                                                                                      46,
                                                                                                                                                      20,
                                                                                                                                                      43,
                                                                                                                                                      -38,
                                                                                                                                                      -54,
                                                                                                                                                      54,
                                                                                                                                                      82,
                                                                                                                                                      89,
                                                                                                                                                      -48,
                                                                                                                                                      -113,
                                                                                                                                                      -60,
                                                                                                                                                      -10,
                                                                                                                                                      15,
                                                                                                                                                      32,
                                                                                                                                                      119,
                                                                                                                                                      119,
                                                                                                                                                      61,
                                                                                                                                                      -118,
                                                                                                                                                      100,
                                                                                                                                                      62,
                                                                                                                                                      17,
                                                                                                                                                      54,
                                                                                                                                                      4,
                                                                                                                                                      120,
                                                                                                                                                      119,
                                                                                                                                                      -43,
                                                                                                                                                      -125,
                                                                                                                                                      -112
                                                                                                                                                   };
                                                                                                                                                   byte[] byteValueArray115 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                      byteValueArray,
                                                                                                                                                      byteValueArray114,
                                                                                                                                                      coreByteArrayEntry
                                                                                                                                                   );
                                                                                                                                                   this.byteFlagArray150 = itemEntry133Entry57.resolveByteArray(
                                                                                                                                                      byteValueArray115
                                                                                                                                                   );
                                                                                                                                                   value2 = (
                                                                                                                                                         value2
                                                                                                                                                            ^ -0x4cd622b5f8a14bdL
                                                                                                                                                      )
                                                                                                                                                      + 0x5c9a8def27de8abbL;
                                                                                                                                                }
                                                                                                                                             } else {
                                                                                                                                                ItemEntry133Entry itemEntry133Entry58 = new ItemEntry133Entry(
                                                                                                                                                   MovementWeightProperty.SHA3_256
                                                                                                                                                );
                                                                                                                                                byte[] byteValueArray116 = new byte[]{
                                                                                                                                                   85,
                                                                                                                                                   61,
                                                                                                                                                   -104,
                                                                                                                                                   -97,
                                                                                                                                                   26,
                                                                                                                                                   123,
                                                                                                                                                   42,
                                                                                                                                                   -69,
                                                                                                                                                   0,
                                                                                                                                                   16,
                                                                                                                                                   120,
                                                                                                                                                   46,
                                                                                                                                                   115,
                                                                                                                                                   -59,
                                                                                                                                                   68,
                                                                                                                                                   80,
                                                                                                                                                   -92,
                                                                                                                                                   -110,
                                                                                                                                                   61,
                                                                                                                                                   75,
                                                                                                                                                   69,
                                                                                                                                                   -1,
                                                                                                                                                   -126,
                                                                                                                                                   101,
                                                                                                                                                   11,
                                                                                                                                                   126,
                                                                                                                                                   -22,
                                                                                                                                                   -82,
                                                                                                                                                   63,
                                                                                                                                                   27,
                                                                                                                                                   34,
                                                                                                                                                   -102
                                                                                                                                                };
                                                                                                                                                byte[] byteValueArray117 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                   byteValueArray,
                                                                                                                                                   byteValueArray116,
                                                                                                                                                   coreByteArrayEntry
                                                                                                                                                );
                                                                                                                                                this.byteFlagArray195 = itemEntry133Entry58.resolveByteArray(
                                                                                                                                                   byteValueArray117
                                                                                                                                                );
                                                                                                                                                value2 = (
                                                                                                                                                      value2
                                                                                                                                                         ^ 0x5705c67885ab52c9L
                                                                                                                                                   )
                                                                                                                                                   - 0x46df856e15c76356L;
                                                                                                                                             }
                                                                                                                                          } else {
                                                                                                                                             ItemEntry133Entry itemEntry133Entry59 = new ItemEntry133Entry(
                                                                                                                                                MovementWeightProperty.SHA3_256
                                                                                                                                             );
                                                                                                                                             byte[] byteValueArray118 = new byte[]{
                                                                                                                                                46,
                                                                                                                                                -14,
                                                                                                                                                31,
                                                                                                                                                90,
                                                                                                                                                -73,
                                                                                                                                                78,
                                                                                                                                                -37,
                                                                                                                                                -44,
                                                                                                                                                -59,
                                                                                                                                                27,
                                                                                                                                                104,
                                                                                                                                                96,
                                                                                                                                                -123,
                                                                                                                                                53,
                                                                                                                                                17,
                                                                                                                                                -94,
                                                                                                                                                -121,
                                                                                                                                                110,
                                                                                                                                                -16,
                                                                                                                                                -32,
                                                                                                                                                96,
                                                                                                                                                16,
                                                                                                                                                105,
                                                                                                                                                -72,
                                                                                                                                                116,
                                                                                                                                                41,
                                                                                                                                                -102,
                                                                                                                                                -79,
                                                                                                                                                52,
                                                                                                                                                115,
                                                                                                                                                -92,
                                                                                                                                                9
                                                                                                                                             };
                                                                                                                                             byte[] byteValueArray119 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                                byteValueArray,
                                                                                                                                                byteValueArray118,
                                                                                                                                                coreByteArrayEntry
                                                                                                                                             );
                                                                                                                                             this.byteFlagArray90 = itemEntry133Entry59.resolveByteArray(
                                                                                                                                                byteValueArray119
                                                                                                                                             );
                                                                                                                                             value2 = (
                                                                                                                                                   value2
                                                                                                                                                      ^ 0x4f86643415dc6969L
                                                                                                                                                )
                                                                                                                                                + 0x1e86a24438fa3fc5L;
                                                                                                                                          }
                                                                                                                                       } else {
                                                                                                                                          ItemEntry133Entry itemEntry133Entry60 = new ItemEntry133Entry(
                                                                                                                                             MovementWeightProperty.SHA3_256
                                                                                                                                          );
                                                                                                                                          byte[] byteValueArray120 = new byte[]{
                                                                                                                                             -127,
                                                                                                                                             120,
                                                                                                                                             -121,
                                                                                                                                             -32,
                                                                                                                                             110,
                                                                                                                                             50,
                                                                                                                                             -37,
                                                                                                                                             111,
                                                                                                                                             9,
                                                                                                                                             -124,
                                                                                                                                             37,
                                                                                                                                             123,
                                                                                                                                             -36,
                                                                                                                                             -32,
                                                                                                                                             53,
                                                                                                                                             76,
                                                                                                                                             -116,
                                                                                                                                             -83,
                                                                                                                                             -70,
                                                                                                                                             108,
                                                                                                                                             -39,
                                                                                                                                             16,
                                                                                                                                             -103,
                                                                                                                                             -86,
                                                                                                                                             -97,
                                                                                                                                             -3,
                                                                                                                                             -3,
                                                                                                                                             26,
                                                                                                                                             3,
                                                                                                                                             -115,
                                                                                                                                             -52,
                                                                                                                                             -38
                                                                                                                                          };
                                                                                                                                          byte[] byteValueArray121 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                             byteValueArray,
                                                                                                                                             byteValueArray120,
                                                                                                                                             coreByteArrayEntry
                                                                                                                                          );
                                                                                                                                          this.byteFlagArray75 = itemEntry133Entry60.resolveByteArray(
                                                                                                                                             byteValueArray121
                                                                                                                                          );
                                                                                                                                          value2 = (
                                                                                                                                                value2
                                                                                                                                                   ^ -0x4a139b3b5d3fd32aL
                                                                                                                                             )
                                                                                                                                             + 0x4a7185b26bce37abL;
                                                                                                                                       }
                                                                                                                                    } else {
                                                                                                                                       ItemEntry133Entry itemEntry133Entry61 = new ItemEntry133Entry(
                                                                                                                                          MovementWeightProperty.SHA3_256
                                                                                                                                       );
                                                                                                                                       byte[] byteValueArray122 = new byte[]{
                                                                                                                                          -77,
                                                                                                                                          86,
                                                                                                                                          -88,
                                                                                                                                          28,
                                                                                                                                          -53,
                                                                                                                                          -41,
                                                                                                                                          45,
                                                                                                                                          -51,
                                                                                                                                          -23,
                                                                                                                                          82,
                                                                                                                                          98,
                                                                                                                                          -6,
                                                                                                                                          -127,
                                                                                                                                          49,
                                                                                                                                          77,
                                                                                                                                          27,
                                                                                                                                          127,
                                                                                                                                          -51,
                                                                                                                                          -87,
                                                                                                                                          -101,
                                                                                                                                          22,
                                                                                                                                          118,
                                                                                                                                          -19,
                                                                                                                                          -14,
                                                                                                                                          31,
                                                                                                                                          -9,
                                                                                                                                          -127,
                                                                                                                                          -69,
                                                                                                                                          100,
                                                                                                                                          104,
                                                                                                                                          42,
                                                                                                                                          95
                                                                                                                                       };
                                                                                                                                       byte[] byteValueArray123 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                          byteValueArray, byteValueArray122, coreByteArrayEntry
                                                                                                                                       );
                                                                                                                                       this.byteFlagArray179 = itemEntry133Entry61.resolveByteArray(
                                                                                                                                          byteValueArray123
                                                                                                                                       );
                                                                                                                                       value2 = (
                                                                                                                                             value2
                                                                                                                                                ^ -0x44ea8e13311a955aL
                                                                                                                                          )
                                                                                                                                          + 0x74181df191fce786L;
                                                                                                                                    }
                                                                                                                                 } else {
                                                                                                                                    ItemEntry133Entry itemEntry133Entry62 = new ItemEntry133Entry(
                                                                                                                                       MovementWeightProperty.SHA3_256
                                                                                                                                    );
                                                                                                                                    byte[] byteValueArray124 = new byte[]{
                                                                                                                                       -86,
                                                                                                                                       109,
                                                                                                                                       78,
                                                                                                                                       2,
                                                                                                                                       -5,
                                                                                                                                       -11,
                                                                                                                                       -24,
                                                                                                                                       22,
                                                                                                                                       -93,
                                                                                                                                       -30,
                                                                                                                                       95,
                                                                                                                                       -19,
                                                                                                                                       -47,
                                                                                                                                       39,
                                                                                                                                       9,
                                                                                                                                       8,
                                                                                                                                       59,
                                                                                                                                       120,
                                                                                                                                       -95,
                                                                                                                                       85,
                                                                                                                                       -53,
                                                                                                                                       -115,
                                                                                                                                       81,
                                                                                                                                       29,
                                                                                                                                       64,
                                                                                                                                       -117,
                                                                                                                                       105,
                                                                                                                                       -57,
                                                                                                                                       63,
                                                                                                                                       -32,
                                                                                                                                       93,
                                                                                                                                       -58
                                                                                                                                    };
                                                                                                                                    byte[] byteValueArray125 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                       byteValueArray, byteValueArray124, coreByteArrayEntry
                                                                                                                                    );
                                                                                                                                    this.byteFlagArray193 = itemEntry133Entry62.resolveByteArray(
                                                                                                                                       byteValueArray125
                                                                                                                                    );
                                                                                                                                    value2 = (
                                                                                                                                          value2
                                                                                                                                             ^ -0x5160c0f6a58aaaaeL
                                                                                                                                       )
                                                                                                                                       + 0x7994ee3a2449878fL;
                                                                                                                                 }
                                                                                                                              } else {
                                                                                                                                 ItemEntry133Entry itemEntry133Entry63 = new ItemEntry133Entry(
                                                                                                                                    MovementWeightProperty.SHA3_256
                                                                                                                                 );
                                                                                                                                 byte[] byteValueArray126 = new byte[]{
                                                                                                                                    -80,
                                                                                                                                    26,
                                                                                                                                    14,
                                                                                                                                    -76,
                                                                                                                                    101,
                                                                                                                                    108,
                                                                                                                                    -29,
                                                                                                                                    -12,
                                                                                                                                    -28,
                                                                                                                                    70,
                                                                                                                                    -114,
                                                                                                                                    27,
                                                                                                                                    -4,
                                                                                                                                    -65,
                                                                                                                                    -7,
                                                                                                                                    -9,
                                                                                                                                    117,
                                                                                                                                    99,
                                                                                                                                    25,
                                                                                                                                    -35,
                                                                                                                                    -17,
                                                                                                                                    45,
                                                                                                                                    -21,
                                                                                                                                    97,
                                                                                                                                    -43,
                                                                                                                                    -87,
                                                                                                                                    118,
                                                                                                                                    24,
                                                                                                                                    27,
                                                                                                                                    88,
                                                                                                                                    -23,
                                                                                                                                    -70
                                                                                                                                 };
                                                                                                                                 byte[] byteValueArray127 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                    byteValueArray, byteValueArray126, coreByteArrayEntry
                                                                                                                                 );
                                                                                                                                 this.byteFlagArray196 = itemEntry133Entry63.resolveByteArray(
                                                                                                                                    byteValueArray127
                                                                                                                                 );
                                                                                                                                 value2 = (
                                                                                                                                       value2
                                                                                                                                          ^ 0x2f1d50daf7597be0L
                                                                                                                                    )
                                                                                                                                    + 0x301fda9a2e10221L;
                                                                                                                              }
                                                                                                                           } else {
                                                                                                                              ItemEntry133Entry itemEntry133Entry64 = new ItemEntry133Entry(
                                                                                                                                 MovementWeightProperty.SHA3_256
                                                                                                                              );
                                                                                                                              byte[] byteValueArray128 = new byte[]{
                                                                                                                                 46,
                                                                                                                                 87,
                                                                                                                                 37,
                                                                                                                                 49,
                                                                                                                                 -110,
                                                                                                                                 79,
                                                                                                                                 -124,
                                                                                                                                 96,
                                                                                                                                 -59,
                                                                                                                                 72,
                                                                                                                                 -50,
                                                                                                                                 65,
                                                                                                                                 92,
                                                                                                                                 -87,
                                                                                                                                 101,
                                                                                                                                 -46,
                                                                                                                                 34,
                                                                                                                                 -105,
                                                                                                                                 83,
                                                                                                                                 -90,
                                                                                                                                 -34,
                                                                                                                                 -97,
                                                                                                                                 -21,
                                                                                                                                 -40,
                                                                                                                                 16,
                                                                                                                                 -53,
                                                                                                                                 -9,
                                                                                                                                 -74,
                                                                                                                                 -123,
                                                                                                                                 -126,
                                                                                                                                 42,
                                                                                                                                 -60
                                                                                                                              };
                                                                                                                              byte[] byteValueArray129 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                                 byteValueArray, byteValueArray128, coreByteArrayEntry
                                                                                                                              );
                                                                                                                              this.byteFlagArray62 = itemEntry133Entry64.resolveByteArray(
                                                                                                                                 byteValueArray129
                                                                                                                              );
                                                                                                                              value2 = (
                                                                                                                                    value2
                                                                                                                                       ^ -0x649211bc450b93ddL
                                                                                                                                 )
                                                                                                                                 + 0x40704f0311f870feL;
                                                                                                                           }
                                                                                                                        } else {
                                                                                                                           ItemEntry133Entry itemEntry133Entry65 = new ItemEntry133Entry(
                                                                                                                              MovementWeightProperty.SHA3_256
                                                                                                                           );
                                                                                                                           byte[] byteValueArray130 = new byte[]{
                                                                                                                              -79,
                                                                                                                              -79,
                                                                                                                              125,
                                                                                                                              121,
                                                                                                                              -61,
                                                                                                                              27,
                                                                                                                              -108,
                                                                                                                              -52,
                                                                                                                              -2,
                                                                                                                              97,
                                                                                                                              -112,
                                                                                                                              -55,
                                                                                                                              53,
                                                                                                                              5,
                                                                                                                              111,
                                                                                                                              1,
                                                                                                                              -76,
                                                                                                                              -83,
                                                                                                                              -69,
                                                                                                                              83,
                                                                                                                              94,
                                                                                                                              -110,
                                                                                                                              -73,
                                                                                                                              -41,
                                                                                                                              109,
                                                                                                                              118,
                                                                                                                              -7,
                                                                                                                              -57,
                                                                                                                              -19,
                                                                                                                              33,
                                                                                                                              -95,
                                                                                                                              20
                                                                                                                           };
                                                                                                                           byte[] byteValueArray131 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                              byteValueArray, byteValueArray130, coreByteArrayEntry
                                                                                                                           );
                                                                                                                           this.byteFlagArray102 = itemEntry133Entry65.resolveByteArray(
                                                                                                                              byteValueArray131
                                                                                                                           );
                                                                                                                           value2 = (
                                                                                                                                 value2 ^ -0x4eb8de6e7e5d6108L
                                                                                                                              )
                                                                                                                              - 0x705da1806610d3a1L;
                                                                                                                        }
                                                                                                                     } else {
                                                                                                                        ItemEntry133Entry itemEntry133Entry66 = new ItemEntry133Entry(
                                                                                                                           MovementWeightProperty.SHA3_256
                                                                                                                        );
                                                                                                                        byte[] byteValueArray132 = new byte[]{
                                                                                                                           -125,
                                                                                                                           110,
                                                                                                                           20,
                                                                                                                           11,
                                                                                                                           44,
                                                                                                                           11,
                                                                                                                           84,
                                                                                                                           -3,
                                                                                                                           42,
                                                                                                                           -58,
                                                                                                                           29,
                                                                                                                           -105,
                                                                                                                           105,
                                                                                                                           52,
                                                                                                                           -48,
                                                                                                                           -40,
                                                                                                                           33,
                                                                                                                           -26,
                                                                                                                           82,
                                                                                                                           67,
                                                                                                                           -30,
                                                                                                                           42,
                                                                                                                           56,
                                                                                                                           119,
                                                                                                                           -23,
                                                                                                                           -50,
                                                                                                                           -35,
                                                                                                                           15,
                                                                                                                           75,
                                                                                                                           -54,
                                                                                                                           26,
                                                                                                                           -13
                                                                                                                        };
                                                                                                                        byte[] byteValueArray133 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                           byteValueArray, byteValueArray132, coreByteArrayEntry
                                                                                                                        );
                                                                                                                        this.byteFlagArray147 = itemEntry133Entry66.resolveByteArray(
                                                                                                                           byteValueArray133
                                                                                                                        );
                                                                                                                        value2 = (value2 ^ 0x305ac8b21c6c7b5L)
                                                                                                                           - 0x5a1775332cad600fL;
                                                                                                                     }
                                                                                                                  } else {
                                                                                                                     ItemEntry133Entry itemEntry133Entry67 = new ItemEntry133Entry(
                                                                                                                        MovementWeightProperty.SHA3_256
                                                                                                                     );
                                                                                                                     byte[] byteValueArray134 = new byte[]{
                                                                                                                        -9,
                                                                                                                        -26,
                                                                                                                        122,
                                                                                                                        40,
                                                                                                                        124,
                                                                                                                        -24,
                                                                                                                        101,
                                                                                                                        100,
                                                                                                                        118,
                                                                                                                        78,
                                                                                                                        -53,
                                                                                                                        112,
                                                                                                                        98,
                                                                                                                        -85,
                                                                                                                        -95,
                                                                                                                        30,
                                                                                                                        12,
                                                                                                                        -82,
                                                                                                                        -41,
                                                                                                                        -109,
                                                                                                                        -38,
                                                                                                                        123,
                                                                                                                        38,
                                                                                                                        -60,
                                                                                                                        -93,
                                                                                                                        15,
                                                                                                                        111,
                                                                                                                        -1,
                                                                                                                        67,
                                                                                                                        102,
                                                                                                                        -123,
                                                                                                                        -61
                                                                                                                     };
                                                                                                                     byte[] byteValueArray135 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                        byteValueArray, byteValueArray134, coreByteArrayEntry
                                                                                                                     );
                                                                                                                     this.byteFlagArray153 = itemEntry133Entry67.resolveByteArray(
                                                                                                                        byteValueArray135
                                                                                                                     );
                                                                                                                     value2 = (value2 ^ 0x779146d5c5f6f5b1L)
                                                                                                                        - 0x1096896513481dd3L;
                                                                                                                  }
                                                                                                               } else {
                                                                                                                  ItemEntry133Entry itemEntry133Entry68 = new ItemEntry133Entry(
                                                                                                                     MovementWeightProperty.SHA3_256
                                                                                                                  );
                                                                                                                  byte[] byteValueArray136 = new byte[]{
                                                                                                                     -114,
                                                                                                                     42,
                                                                                                                     -104,
                                                                                                                     87,
                                                                                                                     36,
                                                                                                                     -80,
                                                                                                                     45,
                                                                                                                     -94,
                                                                                                                     2,
                                                                                                                     -111,
                                                                                                                     -22,
                                                                                                                     -39,
                                                                                                                     24,
                                                                                                                     -126,
                                                                                                                     -44,
                                                                                                                     -1,
                                                                                                                     47,
                                                                                                                     4,
                                                                                                                     -87,
                                                                                                                     125,
                                                                                                                     104,
                                                                                                                     19,
                                                                                                                     120,
                                                                                                                     -126,
                                                                                                                     -22,
                                                                                                                     1,
                                                                                                                     -112,
                                                                                                                     -105,
                                                                                                                     86,
                                                                                                                     113,
                                                                                                                     75,
                                                                                                                     -98
                                                                                                                  };
                                                                                                                  byte[] byteValueArray137 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                     byteValueArray, byteValueArray136, coreByteArrayEntry
                                                                                                                  );
                                                                                                                  this.byteFlagArray151 = itemEntry133Entry68.resolveByteArray(
                                                                                                                     byteValueArray137
                                                                                                                  );
                                                                                                                  value2 = (value2 ^ 0x73f9b6b74814af5L)
                                                                                                                     - 0x158a9b22c952ab1bL;
                                                                                                               }
                                                                                                            } else {
                                                                                                               ItemEntry133Entry itemEntry133Entry69 = new ItemEntry133Entry(
                                                                                                                  MovementWeightProperty.SHA3_256
                                                                                                               );
                                                                                                               byte[] byteValueArray138 = new byte[]{
                                                                                                                  -22,
                                                                                                                  26,
                                                                                                                  112,
                                                                                                                  56,
                                                                                                                  -114,
                                                                                                                  -27,
                                                                                                                  24,
                                                                                                                  -119,
                                                                                                                  7,
                                                                                                                  -110,
                                                                                                                  -99,
                                                                                                                  117,
                                                                                                                  14,
                                                                                                                  40,
                                                                                                                  -85,
                                                                                                                  87,
                                                                                                                  75,
                                                                                                                  -24,
                                                                                                                  54,
                                                                                                                  -12,
                                                                                                                  -84,
                                                                                                                  2,
                                                                                                                  -11,
                                                                                                                  -52,
                                                                                                                  64,
                                                                                                                  5,
                                                                                                                  112,
                                                                                                                  85,
                                                                                                                  76,
                                                                                                                  107,
                                                                                                                  -67,
                                                                                                                  34
                                                                                                               };
                                                                                                               byte[] byteValueArray139 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                                  byteValueArray, byteValueArray138, coreByteArrayEntry
                                                                                                               );
                                                                                                               this.byteFlagArray57 = itemEntry133Entry69.resolveByteArray(
                                                                                                                  byteValueArray139
                                                                                                               );
                                                                                                               value2 = (value2 ^ -0x5f6b76de6b2f377cL)
                                                                                                                  + 0x58e1ca599371ec3bL;
                                                                                                            }
                                                                                                         } else {
                                                                                                            ItemEntry133Entry itemEntry133Entry70 = new ItemEntry133Entry(
                                                                                                               MovementWeightProperty.SHA3_256
                                                                                                            );
                                                                                                            byte[] byteValueArray140 = new byte[]{
                                                                                                               -103,
                                                                                                               52,
                                                                                                               -53,
                                                                                                               -85,
                                                                                                               89,
                                                                                                               -16,
                                                                                                               95,
                                                                                                               63,
                                                                                                               -97,
                                                                                                               -56,
                                                                                                               35,
                                                                                                               24,
                                                                                                               47,
                                                                                                               9,
                                                                                                               -76,
                                                                                                               -4,
                                                                                                               19,
                                                                                                               85,
                                                                                                               -82,
                                                                                                               -86,
                                                                                                               -24,
                                                                                                               -93,
                                                                                                               37,
                                                                                                               -119,
                                                                                                               -124,
                                                                                                               -80,
                                                                                                               -89,
                                                                                                               4,
                                                                                                               13,
                                                                                                               106,
                                                                                                               -23,
                                                                                                               -95
                                                                                                            };
                                                                                                            byte[] byteValueArray141 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                               byteValueArray, byteValueArray140, coreByteArrayEntry
                                                                                                            );
                                                                                                            this.byteFlagArray49 = itemEntry133Entry70.resolveByteArray(
                                                                                                               byteValueArray141
                                                                                                            );
                                                                                                            value2 = (value2 ^ 0x2b4b0cd6bf943ebbL)
                                                                                                               + 0x1478a57156a5ba4aL;
                                                                                                         }
                                                                                                      } else {
                                                                                                         ItemEntry133Entry itemEntry133Entry71 = new ItemEntry133Entry(
                                                                                                            MovementWeightProperty.SHA3_256
                                                                                                         );
                                                                                                         byte[] byteValueArray142 = new byte[]{
                                                                                                            43,
                                                                                                            96,
                                                                                                            115,
                                                                                                            -85,
                                                                                                            -23,
                                                                                                            54,
                                                                                                            116,
                                                                                                            13,
                                                                                                            -14,
                                                                                                            -6,
                                                                                                            23,
                                                                                                            124,
                                                                                                            83,
                                                                                                            71,
                                                                                                            121,
                                                                                                            -20,
                                                                                                            -60,
                                                                                                            -9,
                                                                                                            -114,
                                                                                                            27,
                                                                                                            -97,
                                                                                                            118,
                                                                                                            -34,
                                                                                                            -9,
                                                                                                            -53,
                                                                                                            30,
                                                                                                            -63,
                                                                                                            103,
                                                                                                            -11,
                                                                                                            -44,
                                                                                                            -73,
                                                                                                            -108
                                                                                                         };
                                                                                                         byte[] byteValueArray143 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                            byteValueArray, byteValueArray142, coreByteArrayEntry
                                                                                                         );
                                                                                                         this.byteFlagArray156 = itemEntry133Entry71.resolveByteArray(byteValueArray143);
                                                                                                         value2 = (value2 ^ -0x11fd85a6d55dd404L)
                                                                                                            + 0x5d069c360b9bce6aL;
                                                                                                      }
                                                                                                   } else {
                                                                                                      ItemEntry133Entry itemEntry133Entry72 = new ItemEntry133Entry(
                                                                                                         MovementWeightProperty.SHA3_256
                                                                                                      );
                                                                                                      byte[] byteValueArray144 = new byte[]{
                                                                                                         122,
                                                                                                         -14,
                                                                                                         -35,
                                                                                                         36,
                                                                                                         61,
                                                                                                         -33,
                                                                                                         113,
                                                                                                         110,
                                                                                                         127,
                                                                                                         67,
                                                                                                         103,
                                                                                                         104,
                                                                                                         -120,
                                                                                                         -62,
                                                                                                         -75,
                                                                                                         0,
                                                                                                         89,
                                                                                                         39,
                                                                                                         -24,
                                                                                                         -39,
                                                                                                         -90,
                                                                                                         -55,
                                                                                                         -26,
                                                                                                         68,
                                                                                                         -96,
                                                                                                         -27,
                                                                                                         -103,
                                                                                                         112,
                                                                                                         -92,
                                                                                                         107,
                                                                                                         -43,
                                                                                                         34
                                                                                                      };
                                                                                                      byte[] byteValueArray145 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                         byteValueArray, byteValueArray144, coreByteArrayEntry
                                                                                                      );
                                                                                                      this.byteFlagArray159 = itemEntry133Entry72.resolveByteArray(byteValueArray145);
                                                                                                      value2 = (value2 ^ -0x3570d08dd9c5dd06L)
                                                                                                         + 0x6c8e80d7461dd7abL;
                                                                                                   }
                                                                                                } else {
                                                                                                   ItemEntry133Entry itemEntry133Entry73 = new ItemEntry133Entry(
                                                                                                      MovementWeightProperty.SHA3_256
                                                                                                   );
                                                                                                   byte[] byteValueArray146 = new byte[]{
                                                                                                      -77,
                                                                                                      29,
                                                                                                      -105,
                                                                                                      85,
                                                                                                      39,
                                                                                                      -29,
                                                                                                      -65,
                                                                                                      101,
                                                                                                      -27,
                                                                                                      -102,
                                                                                                      14,
                                                                                                      -111,
                                                                                                      -17,
                                                                                                      117,
                                                                                                      -27,
                                                                                                      -78,
                                                                                                      4,
                                                                                                      21,
                                                                                                      -105,
                                                                                                      -5,
                                                                                                      -8,
                                                                                                      21,
                                                                                                      10,
                                                                                                      -123,
                                                                                                      68,
                                                                                                      -11,
                                                                                                      9,
                                                                                                      -96,
                                                                                                      110,
                                                                                                      98,
                                                                                                      12,
                                                                                                      35
                                                                                                   };
                                                                                                   byte[] byteValueArray147 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                      byteValueArray, byteValueArray146, coreByteArrayEntry
                                                                                                   );
                                                                                                   this.byteFlagArray158 = itemEntry133Entry73.resolveByteArray(byteValueArray147);
                                                                                                   value2 = (value2 ^ -0x6f6305305bcbddd7L)
                                                                                                      - 0x4cf53d194494662cL;
                                                                                                }
                                                                                             } else {
                                                                                                ItemEntry133Entry itemEntry133Entry74 = new ItemEntry133Entry(
                                                                                                   MovementWeightProperty.SHA3_256
                                                                                                );
                                                                                                byte[] byteValueArray148 = new byte[]{
                                                                                                   -20,
                                                                                                   77,
                                                                                                   -12,
                                                                                                   83,
                                                                                                   4,
                                                                                                   39,
                                                                                                   8,
                                                                                                   37,
                                                                                                   -66,
                                                                                                   92,
                                                                                                   85,
                                                                                                   77,
                                                                                                   -62,
                                                                                                   116,
                                                                                                   28,
                                                                                                   -39,
                                                                                                   -30,
                                                                                                   42,
                                                                                                   -15,
                                                                                                   91,
                                                                                                   -101,
                                                                                                   -95,
                                                                                                   37,
                                                                                                   18,
                                                                                                   -49,
                                                                                                   -128,
                                                                                                   102,
                                                                                                   -16,
                                                                                                   -122,
                                                                                                   10,
                                                                                                   19,
                                                                                                   -42
                                                                                                };
                                                                                                byte[] byteValueArray149 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                   byteValueArray, byteValueArray148, coreByteArrayEntry
                                                                                                );
                                                                                                this.byteFlagArray33 = itemEntry133Entry74.resolveByteArray(byteValueArray149);
                                                                                                value2 = (value2 ^ -0x775e9a869625a43bL) + 0x5cb819999cc05a14L;
                                                                                             }
                                                                                          } else {
                                                                                             ItemEntry133Entry itemEntry133Entry75 = new ItemEntry133Entry(
                                                                                                MovementWeightProperty.SHA3_256
                                                                                             );
                                                                                             byte[] byteValueArray150 = new byte[]{
                                                                                                -84,
                                                                                                55,
                                                                                                -50,
                                                                                                -92,
                                                                                                -57,
                                                                                                -95,
                                                                                                -105,
                                                                                                8,
                                                                                                108,
                                                                                                31,
                                                                                                126,
                                                                                                95,
                                                                                                22,
                                                                                                97,
                                                                                                -43,
                                                                                                62,
                                                                                                14,
                                                                                                40,
                                                                                                82,
                                                                                                -54,
                                                                                                82,
                                                                                                -1,
                                                                                                -23,
                                                                                                -38,
                                                                                                -95,
                                                                                                115,
                                                                                                4,
                                                                                                75,
                                                                                                73,
                                                                                                -36,
                                                                                                -115,
                                                                                                -54
                                                                                             };
                                                                                             byte[] byteValueArray151 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                                byteValueArray, byteValueArray150, coreByteArrayEntry
                                                                                             );
                                                                                             this.byteFlagArray14 = itemEntry133Entry75.resolveByteArray(byteValueArray151);
                                                                                             value2 = (value2 ^ -0x27ed6ee77eec55b3L) + 0x7f591899ab42d389L;
                                                                                          }
                                                                                       } else {
                                                                                          ItemEntry133Entry itemEntry133Entry76 = new ItemEntry133Entry(
                                                                                             MovementWeightProperty.SHA3_256
                                                                                          );
                                                                                          byte[] byteValueArray152 = new byte[]{
                                                                                             -41,
                                                                                             13,
                                                                                             -96,
                                                                                             1,
                                                                                             -62,
                                                                                             106,
                                                                                             1,
                                                                                             108,
                                                                                             -46,
                                                                                             -50,
                                                                                             109,
                                                                                             -63,
                                                                                             -95,
                                                                                             98,
                                                                                             -95,
                                                                                             -126,
                                                                                             74,
                                                                                             -71,
                                                                                             121,
                                                                                             85,
                                                                                             -111,
                                                                                             71,
                                                                                             -68,
                                                                                             -61,
                                                                                             38,
                                                                                             79,
                                                                                             -118,
                                                                                             -68,
                                                                                             -69,
                                                                                             94,
                                                                                             127,
                                                                                             1
                                                                                          };
                                                                                          byte[] byteValueArray153 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                             byteValueArray, byteValueArray152, coreByteArrayEntry
                                                                                          );
                                                                                          this.byteFlagArray167 = itemEntry133Entry76.resolveByteArray(byteValueArray153);
                                                                                          value2 = (value2 ^ -0x4db5c4e726556c5cL) - 0x71ccb63548866495L;
                                                                                       }
                                                                                    } else {
                                                                                       ItemEntry133Entry itemEntry133Entry77 = new ItemEntry133Entry(
                                                                                          MovementWeightProperty.SHA3_256
                                                                                       );
                                                                                       byte[] byteValueArray154 = new byte[]{
                                                                                          34,
                                                                                          69,
                                                                                          -85,
                                                                                          -126,
                                                                                          123,
                                                                                          -8,
                                                                                          -123,
                                                                                          85,
                                                                                          -62,
                                                                                          -93,
                                                                                          -94,
                                                                                          -120,
                                                                                          -31,
                                                                                          -66,
                                                                                          17,
                                                                                          -10,
                                                                                          -38,
                                                                                          -123,
                                                                                          111,
                                                                                          55,
                                                                                          -100,
                                                                                          92,
                                                                                          -13,
                                                                                          38,
                                                                                          -65,
                                                                                          35,
                                                                                          -15,
                                                                                          -57,
                                                                                          -104,
                                                                                          -8,
                                                                                          -9,
                                                                                          -87
                                                                                       };
                                                                                       byte[] byteValueArray155 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                          byteValueArray, byteValueArray154, coreByteArrayEntry
                                                                                       );
                                                                                       this.byteFlagArray21 = itemEntry133Entry77.resolveByteArray(byteValueArray155);
                                                                                       value2 = (value2 ^ 0x124a7b64348a75c7L) + 0x1c57a7f55e8f3637L;
                                                                                    }
                                                                                 } else {
                                                                                    ItemEntry133Entry itemEntry133Entry78 = new ItemEntry133Entry(
                                                                                       MovementWeightProperty.SHA3_256
                                                                                    );
                                                                                    byte[] byteValueArray156 = new byte[]{
                                                                                       31,
                                                                                       90,
                                                                                       -51,
                                                                                       13,
                                                                                       63,
                                                                                       -112,
                                                                                       97,
                                                                                       111,
                                                                                       -120,
                                                                                       -124,
                                                                                       -37,
                                                                                       115,
                                                                                       -115,
                                                                                       -51,
                                                                                       -68,
                                                                                       -26,
                                                                                       117,
                                                                                       -52,
                                                                                       15,
                                                                                       92,
                                                                                       -95,
                                                                                       -108,
                                                                                       127,
                                                                                       52,
                                                                                       19,
                                                                                       9,
                                                                                       -33,
                                                                                       -78,
                                                                                       -3,
                                                                                       -7,
                                                                                       111,
                                                                                       -74
                                                                                    };
                                                                                    byte[] byteValueArray157 = AuxiliaryCharArrayEntry.resolveByteArray(
                                                                                       byteValueArray, byteValueArray156, coreByteArrayEntry
                                                                                    );
                                                                                    this.byteFlagArray30 = itemEntry133Entry78.resolveByteArray(byteValueArray157);
                                                                                    value2 = (value2 ^ 0x31ceabb48cac496eL) - 0x12165529ac8b087bL;
                                                                                 }
                                                                              } else {
                                                                                 ItemEntry133Entry itemEntry133Entry79 = new ItemEntry133Entry(
                                                                                    MovementWeightProperty.SHA3_256
                                                                                 );
                                                                                 byte[] byteValueArray158 = new byte[]{
                                                                                    -112,
                                                                                    43,
                                                                                    106,
                                                                                    22,
                                                                                    33,
                                                                                    9,
                                                                                    84,
                                                                                    82,
                                                                                    114,
                                                                                    -70,
                                                                                    -79,
                                                                                    -103,
                                                                                    -87,
                                                                                    -70,
                                                                                    48,
                                                                                    119,
                                                                                    31,
                                                                                    -121,
                                                                                    113,
                                                                                    90,
                                                                                    -57,
                                                                                    109,
                                                                                    30,
                                                                                    -10,
                                                                                    107,
                                                                                    -50,
                                                                                    -57,
                                                                                    -25,
                                                                                    -102,
                                                                                    -128,
                                                                                    99,
                                                                                    -24
                                                                                 };
                                                                                 byte[] byteValueArray159 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray158, coreByteArrayEntry);
                                                                                 this.byteFlagArray165 = itemEntry133Entry79.resolveByteArray(byteValueArray159);
                                                                                 value2 = (value2 ^ 0x2af4b6430d220757L) + 0x57816e272a258f0eL;
                                                                              }
                                                                           } else {
                                                                              ItemEntry133Entry itemEntry133Entry80 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                              byte[] byteValueArray160 = new byte[]{
                                                                                 -108,
                                                                                 77,
                                                                                 41,
                                                                                 42,
                                                                                 -120,
                                                                                 -100,
                                                                                 -104,
                                                                                 -36,
                                                                                 -97,
                                                                                 -110,
                                                                                 -35,
                                                                                 -89,
                                                                                 -118,
                                                                                 54,
                                                                                 57,
                                                                                 113,
                                                                                 42,
                                                                                 33,
                                                                                 104,
                                                                                 65,
                                                                                 -107,
                                                                                 21,
                                                                                 17,
                                                                                 74,
                                                                                 76,
                                                                                 -40,
                                                                                 118,
                                                                                 18,
                                                                                 -29,
                                                                                 -89,
                                                                                 -52,
                                                                                 -71
                                                                              };
                                                                              byte[] byteValueArray161 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray160, coreByteArrayEntry);
                                                                              this.byteFlagArray7 = itemEntry133Entry80.resolveByteArray(byteValueArray161);
                                                                              value2 = (value2 ^ -0x187cd1d7abed39a7L) + 0x1f156cd99a61736bL;
                                                                           }
                                                                        } else {
                                                                           ItemEntry133Entry itemEntry133Entry81 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                           byte[] byteValueArray162 = new byte[]{
                                                                              -107,
                                                                              99,
                                                                              17,
                                                                              20,
                                                                              -99,
                                                                              -115,
                                                                              -107,
                                                                              108,
                                                                              88,
                                                                              72,
                                                                              107,
                                                                              52,
                                                                              62,
                                                                              93,
                                                                              59,
                                                                              47,
                                                                              116,
                                                                              32,
                                                                              92,
                                                                              16,
                                                                              -99,
                                                                              -87,
                                                                              61,
                                                                              -81,
                                                                              48,
                                                                              -89,
                                                                              105,
                                                                              72,
                                                                              -16,
                                                                              -32,
                                                                              48,
                                                                              -50
                                                                           };
                                                                           byte[] byteValueArray163 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray162, coreByteArrayEntry);
                                                                           this.byteFlagArray31 = itemEntry133Entry81.resolveByteArray(byteValueArray163);
                                                                           value2 = (value2 ^ 0x168597bf2eeb6af2L) + 0x1f8352e3123ae794L;
                                                                        }
                                                                     } else {
                                                                        ItemEntry133Entry itemEntry133Entry82 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                        byte[] byteValueArray164 = new byte[]{
                                                                           42,
                                                                           -36,
                                                                           57,
                                                                           22,
                                                                           -110,
                                                                           -91,
                                                                           15,
                                                                           -102,
                                                                           108,
                                                                           -25,
                                                                           -90,
                                                                           21,
                                                                           -109,
                                                                           35,
                                                                           -89,
                                                                           -98,
                                                                           79,
                                                                           -116,
                                                                           67,
                                                                           -55,
                                                                           65,
                                                                           -9,
                                                                           58,
                                                                           71,
                                                                           73,
                                                                           97,
                                                                           74,
                                                                           108,
                                                                           45,
                                                                           -21,
                                                                           127,
                                                                           44
                                                                        };
                                                                        byte[] byteValueArray165 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray164, coreByteArrayEntry);
                                                                        this.byteFlagArray13 = itemEntry133Entry82.resolveByteArray(byteValueArray165);
                                                                        value2 = (value2 ^ 0x3985b04448c362b2L) + 0x359e9f4e9890ce3cL;
                                                                     }
                                                                  } else {
                                                                     ItemEntry133Entry itemEntry133Entry83 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                     byte[] byteValueArray166 = new byte[]{
                                                                        -27,
                                                                        -2,
                                                                        -59,
                                                                        -76,
                                                                        35,
                                                                        106,
                                                                        110,
                                                                        60,
                                                                        -50,
                                                                        -25,
                                                                        -46,
                                                                        -8,
                                                                        -97,
                                                                        -79,
                                                                        -109,
                                                                        89,
                                                                        -120,
                                                                        91,
                                                                        76,
                                                                        -77,
                                                                        -76,
                                                                        78,
                                                                        14,
                                                                        -128,
                                                                        -53,
                                                                        62,
                                                                        24,
                                                                        98,
                                                                        53,
                                                                        -10,
                                                                        80,
                                                                        -81
                                                                     };
                                                                     byte[] byteValueArray167 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray166, coreByteArrayEntry);
                                                                     this.byteFlagArray5 = itemEntry133Entry83.resolveByteArray(byteValueArray167);
                                                                     value2 = (value2 ^ 0x73e23d37bfee039bL) - 0x52c8a565d076683fL;
                                                                  }
                                                               } else {
                                                                  ItemEntry133Entry itemEntry133Entry84 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                                  byte[] byteValueArray168 = new byte[]{
                                                                     -36,
                                                                     -114,
                                                                     -117,
                                                                     5,
                                                                     85,
                                                                     24,
                                                                     -63,
                                                                     50,
                                                                     -21,
                                                                     -104,
                                                                     -42,
                                                                     -70,
                                                                     -84,
                                                                     -126,
                                                                     -28,
                                                                     -56,
                                                                     108,
                                                                     60,
                                                                     -86,
                                                                     6,
                                                                     49,
                                                                     50,
                                                                     -116,
                                                                     33,
                                                                     61,
                                                                     116,
                                                                     41,
                                                                     -54,
                                                                     3,
                                                                     122,
                                                                     38,
                                                                     108
                                                                  };
                                                                  byte[] byteValueArray169 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray168, coreByteArrayEntry);
                                                                  this.byteFlagArray36 = itemEntry133Entry84.resolveByteArray(byteValueArray169);
                                                                  value2 = (value2 ^ 0x26c0a77716d4c12L) + 0xdd228176334503bL;
                                                               }
                                                            } else {
                                                               ItemEntry133Entry itemEntry133Entry85 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                               byte[] byteValueArray170 = new byte[]{
                                                                  -108,
                                                                  109,
                                                                  -56,
                                                                  -32,
                                                                  -91,
                                                                  -112,
                                                                  -22,
                                                                  -107,
                                                                  -91,
                                                                  67,
                                                                  122,
                                                                  34,
                                                                  -85,
                                                                  -71,
                                                                  57,
                                                                  119,
                                                                  -61,
                                                                  102,
                                                                  99,
                                                                  9,
                                                                  -110,
                                                                  60,
                                                                  -101,
                                                                  36,
                                                                  109,
                                                                  11,
                                                                  5,
                                                                  -127,
                                                                  81,
                                                                  -119,
                                                                  -55,
                                                                  -59
                                                               };
                                                               byte[] byteValueArray171 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray170, coreByteArrayEntry);
                                                               this.byteFlagArray180 = itemEntry133Entry85.resolveByteArray(byteValueArray171);
                                                               value2 = (value2 ^ -0x606881ce97da0cfbL) + 0x1a454585a36aa7daL;
                                                            }
                                                         } else {
                                                            ItemEntry133Entry itemEntry133Entry86 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                            byte[] byteValueArray172 = new byte[]{
                                                               -32,
                                                               66,
                                                               -83,
                                                               -20,
                                                               84,
                                                               -126,
                                                               62,
                                                               -109,
                                                               42,
                                                               103,
                                                               32,
                                                               -97,
                                                               -62,
                                                               -96,
                                                               -1,
                                                               30,
                                                               -92,
                                                               -14,
                                                               -93,
                                                               -46,
                                                               -25,
                                                               -110,
                                                               12,
                                                               -101,
                                                               114,
                                                               56,
                                                               11,
                                                               -66,
                                                               -31,
                                                               45,
                                                               122,
                                                               69
                                                            };
                                                            byte[] byteValueArray173 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray172, coreByteArrayEntry);
                                                            this.byteFlagArray8 = itemEntry133Entry86.resolveByteArray(byteValueArray173);
                                                            value2 = (value2 ^ -0x234a2338c879ad3eL) - 0x71365607ecf5f670L;
                                                         }
                                                      } else {
                                                         ItemEntry133Entry itemEntry133Entry87 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                         byte[] byteValueArray174 = new byte[]{
                                                            40,
                                                            23,
                                                            99,
                                                            -124,
                                                            -20,
                                                            79,
                                                            -115,
                                                            -29,
                                                            40,
                                                            -10,
                                                            -71,
                                                            65,
                                                            31,
                                                            -58,
                                                            34,
                                                            17,
                                                            -75,
                                                            -122,
                                                            -122,
                                                            13,
                                                            -88,
                                                            -90,
                                                            -120,
                                                            -81,
                                                            108,
                                                            5,
                                                            42,
                                                            -114,
                                                            -57,
                                                            -121,
                                                            14,
                                                            -94
                                                         };
                                                         byte[] byteValueArray175 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray174, coreByteArrayEntry);
                                                         this.byteFlagArray56 = itemEntry133Entry87.resolveByteArray(byteValueArray175);
                                                         value2 = (value2 ^ -0x1e1ab7cda2106fb4L) + 0x52d87f22c08852e6L;
                                                      }
                                                   } else {
                                                      ItemEntry133Entry itemEntry133Entry88 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                      byte[] byteValueArray176 = new byte[]{
                                                         -39,
                                                         43,
                                                         107,
                                                         -127,
                                                         -125,
                                                         115,
                                                         -35,
                                                         -72,
                                                         -110,
                                                         -89,
                                                         -81,
                                                         -98,
                                                         62,
                                                         71,
                                                         -125,
                                                         91,
                                                         121,
                                                         -57,
                                                         55,
                                                         68,
                                                         104,
                                                         9,
                                                         70,
                                                         -65,
                                                         -20,
                                                         42,
                                                         85,
                                                         78,
                                                         70,
                                                         127,
                                                         34,
                                                         -97
                                                      };
                                                      byte[] byteValueArray177 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray176, coreByteArrayEntry);
                                                      this.byteFlagArray54 = itemEntry133Entry88.resolveByteArray(byteValueArray177);
                                                      value2 = (value2 ^ 0x29b1ff4f58eb4c53L) - 0x25e66fa0b3411e0L;
                                                   }
                                                } else {
                                                   ItemEntry133Entry itemEntry133Entry89 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                                   byte[] byteValueArray178 = new byte[]{
                                                      -49,
                                                      104,
                                                      -49,
                                                      -11,
                                                      -127,
                                                      -31,
                                                      -74,
                                                      100,
                                                      14,
                                                      56,
                                                      -127,
                                                      0,
                                                      66,
                                                      -106,
                                                      -49,
                                                      99,
                                                      -61,
                                                      9,
                                                      -71,
                                                      -104,
                                                      -55,
                                                      110,
                                                      81,
                                                      -99,
                                                      56,
                                                      105,
                                                      84,
                                                      5,
                                                      -28,
                                                      20,
                                                      -72,
                                                      103
                                                   };
                                                   byte[] byteValueArray179 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray178, coreByteArrayEntry);
                                                   this.byteFlagArray46 = itemEntry133Entry89.resolveByteArray(byteValueArray179);
                                                   value2 = (value2 ^ 0x2bc8e2971300848aL) + 0x222e320a35c95840L;
                                                }
                                             } else {
                                                coreByteArrayEntry = new CoreByteArrayEntry();
                                                value2 = (value2 ^ -0x72c63efec2f4b409L) - 0x42eca45c5ba4fb1aL;
                                             }
                                          } else {
                                             ItemEntry133Entry itemEntry133Entry90 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                             byte[] byteValueArray180 = new byte[]{
                                                -58,
                                                -31,
                                                -111,
                                                -7,
                                                -9,
                                                -62,
                                                31,
                                                8,
                                                -40,
                                                -43,
                                                -7,
                                                1,
                                                100,
                                                -78,
                                                -95,
                                                -38,
                                                -123,
                                                -92,
                                                80,
                                                -70,
                                                -64,
                                                -22,
                                                0,
                                                36,
                                                -37,
                                                -80,
                                                93,
                                                -81,
                                                114,
                                                72,
                                                5,
                                                -35
                                             };
                                             byte[] byteValueArray181 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray180, coreByteArrayEntry);
                                             this.byteFlagArray202 = itemEntry133Entry90.resolveByteArray(byteValueArray181);
                                             value2 = (value2 ^ 0x45181960f50512bfL) + 0x24d3bfe65ea56c12L;
                                          }
                                       } else {
                                          ItemEntry133Entry itemEntry133Entry91 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                          byte[] byteValueArray182 = new byte[]{
                                             126,
                                             -67,
                                             -42,
                                             38,
                                             -107,
                                             22,
                                             -94,
                                             -108,
                                             85,
                                             27,
                                             -49,
                                             -11,
                                             -61,
                                             99,
                                             79,
                                             81,
                                             103,
                                             100,
                                             20,
                                             -74,
                                             83,
                                             -119,
                                             91,
                                             -18,
                                             -6,
                                             127,
                                             -19,
                                             -38,
                                             99,
                                             72,
                                             113,
                                             -97
                                          };
                                          byte[] byteValueArray183 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray182, coreByteArrayEntry);
                                          this.byteFlagArray185 = itemEntry133Entry91.resolveByteArray(byteValueArray183);
                                          value2 = (value2 ^ -0x5a3a5f0b7cde32d7L) + 0x42bd0c60255fcbf0L;
                                       }
                                    } else {
                                       ItemEntry133Entry itemEntry133Entry92 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                       byte[] byteValueArray184 = new byte[]{
                                          84,
                                          103,
                                          -27,
                                          104,
                                          51,
                                          111,
                                          -99,
                                          35,
                                          53,
                                          -69,
                                          100,
                                          -40,
                                          -25,
                                          32,
                                          12,
                                          100,
                                          97,
                                          104,
                                          -11,
                                          -91,
                                          70,
                                          125,
                                          -24,
                                          -109,
                                          -36,
                                          44,
                                          -20,
                                          -57,
                                          26,
                                          -122,
                                          51,
                                          33
                                       };
                                       byte[] byteValueArray185 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray184, coreByteArrayEntry);
                                       this.byteFlagArray134 = itemEntry133Entry92.resolveByteArray(byteValueArray185);
                                       value2 = (value2 ^ -0x459432749c1cd459L) - 0x7eef50f8cadaa3fbL;
                                    }
                                 } else {
                                    ItemEntry133Entry itemEntry133Entry93 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                    byte[] byteValueArray186 = new byte[]{
                                       31,
                                       -92,
                                       -117,
                                       -84,
                                       -58,
                                       -23,
                                       -89,
                                       51,
                                       -54,
                                       -103,
                                       -17,
                                       3,
                                       -53,
                                       -26,
                                       -123,
                                       75,
                                       -30,
                                       44,
                                       122,
                                       29,
                                       -44,
                                       -64,
                                       -107,
                                       85,
                                       23,
                                       62,
                                       -24,
                                       -74,
                                       -104,
                                       -83,
                                       -104,
                                       59
                                    };
                                    byte[] byteValueArray187 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray186, coreByteArrayEntry);
                                    this.byteFlagArray162 = itemEntry133Entry93.resolveByteArray(byteValueArray187);
                                    value2 = (value2 ^ 0x53a16864addead49L) + 0x466d039ff83cbec1L;
                                 }
                              } else {
                                 ItemEntry133Entry itemEntry133Entry94 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                                 byte[] byteValueArray188 = new byte[]{
                                    -69,
                                    123,
                                    -42,
                                    88,
                                    -82,
                                    -57,
                                    -104,
                                    -57,
                                    16,
                                    -64,
                                    13,
                                    62,
                                    -21,
                                    1,
                                    7,
                                    -118,
                                    85,
                                    6,
                                    7,
                                    -53,
                                    55,
                                    97,
                                    72,
                                    -8,
                                    -41,
                                    -15,
                                    -76,
                                    96,
                                    33,
                                    -1,
                                    56,
                                    73
                                 };
                                 byte[] byteValueArray189 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray188, coreByteArrayEntry);
                                 this.byteFlagArray108 = itemEntry133Entry94.resolveByteArray(byteValueArray189);
                                 value2 = (value2 ^ -0x2881c99b9fc8ab52L) - 0x53cdaf1fdcbf556eL;
                              }
                           } else {
                              this.byteFlagArray86 = byteValueArray;
                              value2 = (value2 ^ -0x60ef12740eb46a2L) + 0x620735694f4b4229L;
                           }
                        } else {
                           ItemEntry133Entry itemEntry133Entry95 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                           byte[] byteValueArray190 = new byte[]{
                              41,
                              -54,
                              57,
                              -57,
                              -44,
                              -54,
                              -28,
                              37,
                              53,
                              17,
                              113,
                              -9,
                              -60,
                              74,
                              -26,
                              -36,
                              46,
                              12,
                              -62,
                              -18,
                              -41,
                              -24,
                              -90,
                              -99,
                              46,
                              36,
                              -63,
                              126,
                              57,
                              -91,
                              -120,
                              109
                           };
                           byte[] byteValueArray191 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray190, coreByteArrayEntry);
                           this.byteFlagArray52 = itemEntry133Entry95.resolveByteArray(byteValueArray191);
                           value2 = (value2 ^ -0x56825cb9eefc9cefL) + 0x6f014f73142434ceL;
                        }
                     } else {
                        ItemEntry133Entry itemEntry133Entry96 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                        byte[] byteValueArray192 = new byte[]{
                           112,
                           -24,
                           -11,
                           20,
                           -16,
                           -28,
                           -81,
                           57,
                           13,
                           78,
                           54,
                           60,
                           -1,
                           38,
                           -19,
                           -78,
                           -88,
                           -59,
                           -80,
                           100,
                           -7,
                           -126,
                           61,
                           -56,
                           -21,
                           81,
                           65,
                           75,
                           -44,
                           97,
                           124,
                           31
                        };
                        byte[] byteValueArray193 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray192, coreByteArrayEntry);
                        this.byteFlagArray85 = itemEntry133Entry96.resolveByteArray(byteValueArray193);
                        value2 = (value2 ^ 0x15f4ed69e30b3eaaL) + 0x2a4c88545b54edc8L;
                     }
                  } else {
                     ItemEntry133Entry itemEntry133Entry97 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                     byte[] byteValueArray194 = new byte[]{
                        -28,
                        -65,
                        -18,
                        -52,
                        -127,
                        -36,
                        -125,
                        -15,
                        -16,
                        -122,
                        4,
                        28,
                        -104,
                        109,
                        119,
                        109,
                        14,
                        65,
                        125,
                        -113,
                        -84,
                        -40,
                        -126,
                        -22,
                        -71,
                        -54,
                        -45,
                        -84,
                        17,
                        -72,
                        110,
                        31
                     };
                     byte[] byteValueArray195 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray194, coreByteArrayEntry);
                     this.byteFlagArray183 = itemEntry133Entry97.resolveByteArray(byteValueArray195);
                     value2 = (value2 ^ -0x43786abbbdfd5b3cL) - 0x76c84312caf32896L;
                  }
               } else {
                  ItemEntry133Entry itemEntry133Entry98 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
                  byte[] byteValueArray196 = new byte[]{
                     -124,
                     -117,
                     -31,
                     23,
                     119,
                     53,
                     -80,
                     -70,
                     53,
                     -2,
                     -6,
                     107,
                     126,
                     3,
                     -23,
                     75,
                     -42,
                     113,
                     -74,
                     116,
                     31,
                     35,
                     41,
                     48,
                     -101,
                     86,
                     30,
                     76,
                     -69,
                     83,
                     -66,
                     -77
                  };
                  byte[] byteValueArray197 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray196, coreByteArrayEntry);
                  this.byteFlagArray73 = itemEntry133Entry98.resolveByteArray(byteValueArray197);
                  value2 = (value2 ^ 0x636dd1d448310995L) + 0x61ed07d98faa3af4L;
               }
            } else {
               ItemEntry133Entry itemEntry133Entry99 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
               byte[] byteValueArray198 = new byte[]{
                  -61,
                  -7,
                  -12,
                  -43,
                  17,
                  117,
                  -55,
                  82,
                  -4,
                  -63,
                  123,
                  -87,
                  -79,
                  24,
                  -103,
                  93,
                  -50,
                  7,
                  116,
                  -54,
                  -29,
                  -116,
                  -30,
                  -122,
                  -59,
                  -40,
                  120,
                  12,
                  -53,
                  35,
                  45,
                  66
               };
               byte[] byteValueArray199 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray198, coreByteArrayEntry);
               this.byteFlagArray194 = itemEntry133Entry99.resolveByteArray(byteValueArray199);
               value2 = (value2 ^ 0x3688de4c1b52e317L) - 0xd3560c5a02fac71L;
            }
         } else {
            ItemEntry133Entry itemEntry133Entry100 = new ItemEntry133Entry(MovementWeightProperty.SHA3_256);
            byte[] byteValueArray200 = new byte[]{
               20,
               -65,
               -21,
               30,
               -26,
               -67,
               113,
               50,
               -13,
               -53,
               3,
               78,
               -28,
               125,
               -86,
               77,
               11,
               -6,
               14,
               104,
               115,
               -109,
               30,
               31,
               24,
               -91,
               -111,
               28,
               -82,
               -54,
               39,
               -125
            };
            byte[] byteValueArray201 = AuxiliaryCharArrayEntry.resolveByteArray(byteValueArray, byteValueArray200, coreByteArrayEntry);
            this.byteFlagArray87 = itemEntry133Entry100.resolveByteArray(byteValueArray201);
            value2 = (value2 ^ -0x24bcaa34501e96e6L) - 0x7af32a3d976a56daL;
         }
      }
   }

   private byte[] resolveByteArray(byte[] byteValueArray, int[] countArray8) {
      short shortValue = 3923;
      short shortValue2 = 12685;
      char character = '\ua6da';
      int count6 = 0;
      int count7 = 0;
      long value = 0x35886235111033fcL;
      long value2 = 0xfc890122fa50a44L;

      while (true) {
         innerLoop:
         while (true) {
            long value3;
            long value4;
            while (true) {
               int count8 = (int)(value % 20L);
               if (count8 != 14) {
                  if (count8 == 1) {
                     this.byteFlagArray105[count6] = (byte)this.countArray[count6];
                     value = (value ^ -0x82d33c9c4bf6b2dL) + 0x5d042e48d10053f5L;
                     value2 = (value2 ^ -0x667911797a766ca9L) + 0x49288fdc5d85f661L;
                     continue;
                  }

                  if (count8 == 0) {
                     this.byteFlagArray50 = new byte[16];
                     value = (value ^ 0x6f4803d34bd219b6L) + 0x2dd3ec5059ed82d3L;
                     value2 = (value2 ^ 0x7cc3a956bfbb0cb0L) - 0x2a017e65dca2fbeL;
                     continue;
                  }

                  if (count8 == 4) {
                     count7 = 0;
                     value = (value ^ 0x18c0c930b08932e7L) - 0x3bcafbfb8cfaccadL;
                     value2 = (value2 ^ -0x42e86e38b7e3eea9L) + 0x3160d9626a3dbcf7L;
                     continue;
                  }

                  if (count8 != 19) {
                     if (count8 == 2) {
                        break innerLoop;
                     }

                     if (count8 == 9) {
                        count6++;
                        value = (value ^ -0x215b87c7b7d66667L) - 0x41ac879d46f600f6L;
                        value2 = (value2 ^ -0x33357fb9623a5f89L) - 0x2e92b343e8119899L;
                        break innerLoop;
                     }

                     if (count8 == 8) {
                        count6 = 0;
                        value = (value ^ -0x779ac6fee5f9f0fbL) + 0x3a8f008897f2e4f0L;
                        value2 = (value2 ^ -0x613b3b3ecac21f96L) + 0x5bd2f823bf568bf5L;
                        continue;
                     }

                     if (count8 == 18) {
                        return this.byteFlagArray105;
                     }

                     if (count8 != 11) {
                        if (count8 == 10) {
                           count6 = 0;
                           value = (value ^ -0x419960004d7e53daL) - 0x4bd12e6dccf8f8a5L;
                           value2 = (value2 ^ 0x7ba4fc67142f8a7L) - 0x6355fe81789f9ab6L;
                           continue;
                        }

                        if (count8 == 7) {
                           return this.byteFlagArray50;
                        }

                        if (count8 == 16) {
                           if (this.byteFlagArray15 == null) {
                              value = (value ^ 0x3dc91e17cd113c2L) + 0x365f9c1f1c6654cL;
                              value2 = (value2 ^ -0x3ef00dc5145b874eL) + 0x18fe55ef04fb96d4L;
                           } else {
                              value = (value ^ -0x145e8b93c5e856f7L) + 0x78785b4eea1ad193L;
                              value2 = (value2 ^ 0x3f12915e62aa8981L) + 0x24bf11d29fcaf8f3L;
                           }
                           continue;
                        }

                        if (count8 != 15) {
                           if (count8 == 5) {
                              this.countArray[count6] = 0;
                              value = (value ^ 0x295d03804aa55f30L) + 0x50d254d615af9f04L;
                              value2 = (value2 ^ -0x6d6d9efb812fdf82L) - 0x563a8664ec0b1167L;
                              continue;
                           }

                           if (count8 == 3) {
                              count6 = 1;
                              value = (value ^ 0x407108429df7a858L) + 0xb147f67263f4e35L;
                              value2 = (value2 ^ 0x2f1bab9cca7da13aL) + 0x3f4c95c35fee7e73L;
                              continue;
                           }

                           if (count8 == 6) {
                              count6 = 0;
                              value = (value ^ 0x2627e4f978e1723L) + 0x3c4f90105da94b5dL;
                              value2 = (value2 ^ 0x3150c3957ed9bd2dL) + 0x18df0029785ce894L;
                              continue;
                           }

                           if (count8 == 12) {
                              if (this.byteFlagArray50 == null) {
                                 value = (value ^ 0x41502b4a65c0cb46L) + 0x21c992b3eea4ddbcL;
                                 value2 = (value2 ^ -0x3c77b36d42c15621L) + 0x51b4588b77183463L;
                              } else {
                                 value = (value ^ 0x4f3ce6081470a1e8L) + 0x18dbddd076cba0b7L;
                                 value2 = (value2 ^ -0x410d7ad55f07f8e0L) - 0x524c2aee040a4a9dL;
                              }
                              continue;
                           }

                           if (count8 == 13) {
                              System.arraycopy(this.byteFlagArray105, 0, this.byteFlagArray50, 0, 16);
                              return this.byteFlagArray105;
                           }

                           value3 = (value ^ -0x45f66ecca8ba3733L) - 0x69f86f12d743c73bL;
                           value4 = (value2 ^ -0x1140a0aa78d4dfe6L) - 0x1976c8d27d251feaL;
                           break;
                        }

                        count6++;
                        value = (value ^ -0x1ffe42bdcd40dde7L) - 0x7e61a3235359a283L;
                        value2 = (value2 ^ -0xccc9094fb786f90L) - 0x185fd725b1c873bfL;
                     }

                     if (count6 >= 16) {
                        long value5 = (value ^ -0x32978fbad91867adL) - 0x6504e159c130f3c1L;
                        long value6 = (value2 ^ 0x2b4419dd39b647bdL) - 0x41b305d86366dff4L;
                        if (this.byteFlagArray50 != null) {
                           value = (value5 ^ 0x1406307e6e6a3bbcL) + 0x2c2c538c7836d7ceL;
                           value2 = (value6 ^ 0x14c45f040aacc426L) - 0x56be66c65388d343L;
                        } else {
                           value = (value5 ^ 0x6b33d4827532048fL) - 0xf59f42aaddc7b80L;
                           value2 = (value6 ^ -0x57d304ac7cc16a05L) - 0x6c0a595edbd9eda2L;
                        }
                     } else {
                        value = (value ^ 0x5d313c2341152238L) + 0x185b685165bca76eL;
                        value2 = (value2 ^ 0x40c7b98a6580d00dL) - 0x47d7906c0933f5f8L;
                     }
                     continue;
                  }

                  if (byteValueArray[count7] != this.byteFlagArray15[count7]) {
                     value = (value ^ 0x475137718520a5a5L) + 0x3a2fb292f07a84c6L;
                     value2 = (value2 ^ -0x625ef62eff4cf789L) - 0x72815f36324360beL;
                     continue;
                  }

                  long value7 = (value ^ -0x7acfb640be98b4ccL) - 0x5584f59fc5c941deL;
                  long value8 = (value2 ^ -0x12e430d54ac5a82eL) - 0x78964d9113a7112L;
                  count7++;
                  value = (value7 ^ 0x41b9e961a24f80e1L) - 0x42e2390612c92b62L;
                  value2 = (value8 ^ -0x202f79fb4f7e888L) - 0xcb1c2ce81d54bcaL;
               }

               if (count7 >= byteValueArray.length) {
                  value3 = (value ^ -0x6d9d20a6140b7b4L) + 0x5a13877a2f06f3ffL;
                  value4 = (value2 ^ 0x63ae437f2bca3ad9L) + 0x619a88299e0398d9L;
                  break;
               }

               value = (value ^ -0x59c54c06b77a9968L) + 0x7bd44e757d6ccdb5L;
               value2 = (value2 ^ 0x4f6e3273b27929f1L) + 0x376c8d1fb4bb4211L;
            }

            if (count6 != 0) {
               return this.byteFlagArray50;
            }

            value = (value3 ^ 0x3b823fefdd071709L) + 0x22f2495b2261729aL;
            value2 = (value4 ^ -0x41132a3eeb1d8bcL) + 0x677b115ffcdfcfb2L;
         }

         if (count6 >= 16) {
            long value9 = (value ^ -0x453e0238170769c1L) - 0x5b790a6ea8c900d4L;
            long value10 = (value2 ^ -0x3bf9b75f20b23a34L) - 0x58a484566675c254L;
            int[] countArray9 = this.countArray;
            this.applyIndexArray(countArray9, countArray8);
            value = (value9 ^ -0x17e9b774f6011e60L) - 0x702f519a2a8cbf4bL;
            value2 = (value10 ^ 0x12c8e5f1b9a788fL) - 0x4f940345d72bf27aL;
         } else {
            value = (value ^ -0x11e8735b0bdadb48L) - 0x567c31e58d23036dL;
            value2 = (value2 ^ -0x1e689cacb10d2dcdL) - 0x5348c4e056c1c794L;
         }
      }
   }

   private byte[] resolveByteArray(byte[] byteValueArray, byte[] byteValueArray2, byte[] byteValueArray3, int[] countArray8, byte[] byteValueArray4) {
      int count6 = byteValueArray3 != null ? byteValueArray3.length : 0;
      if (this.managedByteArrayEntry == null || !this.managedByteArrayEntry.matchesByteArray(byteValueArray4)) {
         this.managedByteArrayEntry = new ManagedByteArrayEntry(byteValueArray4);
      }

      byte[] byteValueArray5 = new byte[16];
      if (byteValueArray3 != null && count6 > 0) {
         int count7 = (count6 + 16 - 1) / 16;

         for (int index = 0; index < count7; index++) {
            int count8 = Math.min(16, count6 - index * 16);

            for (int index2 = 0; index2 < count8; index2++) {
               byteValueArray5[index2] ^= byteValueArray3[index * 16 + index2];
            }

            this.applyByteArrayByByteArray(byteValueArray5);
         }
      }

      if (byteValueArray2.length > 0) {
         int count9 = (byteValueArray2.length + 16 - 1) / 16;

         for (int index3 = 0; index3 < count9; index3++) {
            int count10 = Math.min(16, byteValueArray2.length - index3 * 16);

            for (int index4 = 0; index4 < count10; index4++) {
               byteValueArray5[index4] ^= byteValueArray2[index3 * 16 + index4];
            }

            this.applyByteArrayByByteArray(byteValueArray5);
         }
      }

      byte[] byteValueArray6 = new byte[16];
      long value2 = (long)count6 * 8L;
      long value3 = (long)byteValueArray2.length * 8L;

      for (int index5 = 7; index5 >= 0; index5--) {
         byteValueArray6[index5] = (byte)((int)(value2 & 255L));
         value2 >>>= 8;
         byteValueArray6[index5 + 8] = (byte)((int)(value3 & 255L));
         value3 >>>= 8;
      }

      for (int index6 = 0; index6 < 16; index6++) {
         byteValueArray5[index6] ^= byteValueArray6[index6];
      }

      this.applyByteArrayByByteArray(byteValueArray5);
      byte[] byteValueArray7 = new byte[16];
      System.arraycopy(byteValueArray, 0, byteValueArray7, 0, byteValueArray.length);
      byteValueArray7[15] = 1;

      for (int index7 = 0; index7 < 16; index7++) {
         this.countArray5[index7] = byteValueArray7[index7] & 255;
      }

      int[] countArray9 = this.countArray5;
      this.applyIndexArray(countArray9, countArray8);

      for (int index8 = 0; index8 < 16; index8++) {
         this.byteFlagArray184[index8] = (byte)this.countArray5[index8];
      }

      byte[] byteValueArray8 = new byte[16];

      for (int index9 = 0; index9 < 16; index9++) {
         byteValueArray8[index9] = (byte)(byteValueArray5[index9] ^ this.byteFlagArray184[index9]);
      }

      return byteValueArray8;
   }

   private void applyByteArrayByByteArray(byte[] byteValueArray) {
      long value = 0L;
      long value2 = 0L;
      int count6 = 0;
      int count7 = 0;
      long value3 = 0L;
      long value4 = 0x2b473f9d00edf222L;
      long value5 = 0x5444323ffcdda176L;

      while (true) {
         int count8 = (int)(value4 % 14L);
         if (count8 == 0) {
            value2 ^= value3 & 4294967295L;
            value4 = (value4 ^ -0x52931937cc23a1acL) - 0x633fb91ede09cac4L;
            value5 = (value5 ^ 0x772d03002030bce1L) + 0xb59227531ea3bfcL;
         } else if (count8 != 3) {
            if (count8 != 9) {
               searchBlock:
               if (count8 != 11) {
                  if (count8 == 2) {
                     value = 0L;
                     value4 = (value4 ^ 0x69a9db64120d60eaL) + 0x2b1ea4dc8723f629L;
                     value5 = (value5 ^ -0x7a6d66c43a200778L) + 0x24b74c34dfbec577L;
                     continue;
                  }

                  if (count8 != 1) {
                     if (count8 == 4) {
                        count7 = byteValueArray[count6] & 255;
                        value4 = (value4 ^ -0x323fefc8a26b686fL) - 0x44fefb02146154aeL;
                        value5 = (value5 ^ -0xc8a6c17f11062b0L) - 0x2281671b0471fc76L;
                        continue;
                     }

                     if (count8 == 7) {
                        break searchBlock;
                     }

                     if (count8 == 5) {
                        if (count7 == 0) {
                           value4 = (value4 ^ 0x6c4fbb49146fda78L) + 0x9e45c5117b9a1efL;
                           value5 = (value5 ^ -0x4575a3347a28be42L) + 0x247b219a984ec856L;
                        } else {
                           value4 = (value4 ^ 0x1a8b0370999fcde2L) + 0x133c97150e545438L;
                           value5 = (value5 ^ -0x72f872135eb8f1f2L) + 0x4fb61ac9e23166f7L;
                        }
                        continue;
                     }

                     if (count8 == 6) {
                        byteValueArray[count6 + 8] = (byte)((int)(value2 >>> 56 - count6 * 8 & 255L));
                        value4 = (value4 ^ 0x4e9c5b6e9a8993ddL) - 0xbe67dd01ef78042L;
                        value5 = (value5 ^ 0x173c046846a320f9L) - 0x7d2a6bed044fc1e1L;
                        continue;
                     }

                     if (count8 != 8) {
                        if (count8 != 10) {
                           if (count8 != 12) {
                              value2 = 0L;
                              value4 = (value4 ^ 0x23e102b0a86ef0e0L) - 0x9642545cf81d6cL;
                              value5 = (value5 ^ -0x3b2eda6347a317c4L) + 0x383a918c859a17b3L;
                           } else {
                              byteValueArray[count6] = (byte)((int)(value >>> 56 - count6 * 8 & 255L));
                              value4 = (value4 ^ -0x12e5d8312abb8876L) - 0x730f50d895c09398L;
                              value5 = (value5 ^ 0x65e636e5bc083142L) + 0x2a4c6da757096c78L;
                           }
                        } else {
                           value ^= value3 >>> 32;
                           value4 = (value4 ^ -0x1d2ce4c3907d2100L) + 0x7c2896409e7bcef2L;
                           value5 = (value5 ^ -0x106a5cafccfb849fL) - 0x5cc22501dfa12a46L;
                        }
                        continue;
                     }

                     count6++;
                     value4 = (value4 ^ -0x53aaeb0906c809c9L) - 0x37b1eb4ff0cdb2beL;
                     value5 = (value5 ^ -0x2cc2ab7e07959ca6L) - 0x31a9a7e6fc881137L;
                  }

                  if (count6 >= 16) {
                     long value6 = (value4 ^ 0x5aadbadb71a07212L) + 0x165168db8723cad5L;
                     long value7 = (value5 ^ -0x31fe7b1e0357d511L) - 0x6f3d07f6f869f78aL;
                     count6 = 0;
                     value4 = (value6 ^ 0x758711a823ab48e2L) - 0x2fa55549422bda4fL;
                     value5 = (value7 ^ -0x10f7a7d5615283feL) - 0x6976f2431ac97cbeL;
                  } else {
                     value4 = (value4 ^ -0x7944ada571074131L) - 0x69f6d731cca46c10L;
                     value5 = (value5 ^ 0x124a0ef216cfa166L) - 0x4259299b74061247L;
                  }
                  continue;
               } else {
                  count6++;
                  value4 = (value4 ^ 0x29fec9cf952896b8L) - 0x36d3e452331c9dd8L;
                  value5 = (value5 ^ -0x48396c2fc2d7d3feL) - 0x2c5536e72d769dbeL;
               }

               if (count6 >= 8) {
                  return;
               }

               value4 = (value4 ^ 0x499a6b96a2ca686cL) - 0x224521246b3acd5L;
               value5 = (value5 ^ -0x69cbaa0701d813bfL) + 0x7b001400b25b4b6dL;
            } else {
               count6 = 0;
               value4 = (value4 ^ 0x2bdda6e1c520e651L) - 0x16ad99e89177b02bL;
               value5 = (value5 ^ -0x43c33ea8b6b1bdc5L) + 0xabc807849e3c9a5L;
            }
         } else {
            value3 = this.managedByteArrayEntry.timestampArray[count6][count7];
            value4 = (value4 ^ 0x1e664232a47165e2L) - 0x2f744f4fdd9fbd43L;
            value5 = (value5 ^ -0x64774672c9521522L) + 0x6edac0a7e2d4342fL;
         }
      }
   }

   public EMfDf9lTUMessageToMessageDecoder(SpecialLongEntry specialLongEntry2) {
      this.byteFlagArray15 = null;
      this.countArray7 = null;
      this.byteFlagArray50 = null;
      this.byteFlagArray56 = new byte[1];
      this.byteFlagArray203 = new byte[1];
      this.byteFlagArray52 = new byte[1];
      this.byteFlagArray30 = new byte[1];
      this.byteFlagArray114 = new byte[1];
      this.byteFlagArray123 = new byte[1];
      this.byteFlagArray147 = new byte[1];
      this.byteFlagArray151 = new byte[1];
      this.byteFlagArray142 = new byte[1];
      this.byteFlagArray45 = new byte[1];
      this.byteFlagArray64 = new byte[1];
      this.byteFlagArray159 = new byte[1];
      this.byteFlagArray180 = new byte[1];
      this.byteFlagArray7 = new byte[1];
      this.byteFlagArray37 = new byte[1];
      this.byteFlagArray112 = new byte[1];
      this.byteFlagArray70 = new byte[1];
      this.byteFlagArray164 = new byte[1];
      this.byteFlagArray49 = new byte[1];
      this.byteFlagArray186 = new byte[1];
      this.byteFlagArray2 = new byte[1];
      this.byteFlagArray155 = new byte[1];
      this.byteFlagArray73 = new byte[1];
      this.byteFlagArray26 = new byte[1];
      this.byteFlagArray149 = new byte[1];
      this.byteFlagArray120 = new byte[1];
      this.byteFlagArray48 = new byte[1];
      this.byteFlagArray185 = new byte[1];
      this.byteFlagArray188 = new byte[1];
      this.byteFlagArray158 = new byte[1];
      this.byteFlagArray62 = new byte[1];
      this.byteFlagArray13 = new byte[1];
      this.byteFlagArray193 = new byte[1];
      this.byteFlagArray129 = new byte[1];
      this.byteFlagArray194 = new byte[1];
      this.byteFlagArray160 = new byte[1];
      this.byteFlagArray90 = new byte[1];
      this.byteFlagArray127 = new byte[1];
      this.byteFlagArray54 = new byte[1];
      this.byteFlagArray140 = new byte[1];
      this.byteFlagArray85 = new byte[1];
      this.byteFlagArray148 = new byte[1];
      this.byteFlagArray80 = new byte[1];
      this.byteFlagArray78 = new byte[1];
      this.byteFlagArray65 = new byte[1];
      this.byteFlagArray32 = new byte[1];
      this.byteFlagArray198 = new byte[1];
      this.byteFlagArray77 = new byte[1];
      this.byteFlagArray116 = new byte[1];
      this.byteFlagArray102 = new byte[1];
      this.byteFlagArray46 = new byte[1];
      this.byteFlagArray31 = new byte[1];
      this.byteFlagArray95 = new byte[1];
      this.byteFlagArray40 = new byte[1];
      this.byteFlagArray33 = new byte[1];
      this.byteFlagArray92 = new byte[1];
      this.byteFlagArray5 = new byte[1];
      this.byteFlagArray43 = new byte[1];
      this.byteFlagArray8 = new byte[1];
      this.byteFlagArray167 = new byte[1];
      this.byteFlagArray14 = new byte[1];
      this.byteFlagArray136 = new byte[1];
      this.byteFlagArray173 = new byte[1];
      this.byteFlagArray178 = new byte[1];
      this.byteFlagArray75 = new byte[1];
      this.byteFlagArray118 = new byte[1];
      this.byteFlagArray144 = new byte[1];
      this.byteFlagArray4 = new byte[1];
      this.byteFlagArray57 = new byte[1];
      this.byteFlagArray195 = new byte[1];
      this.byteFlagArray107 = new byte[1];
      this.byteFlagArray196 = new byte[1];
      this.byteFlagArray131 = new byte[1];
      this.byteFlagArray168 = new byte[1];
      this.byteFlagArray202 = new byte[1];
      this.byteFlagArray34 = new byte[1];
      this.byteFlagArray153 = new byte[1];
      this.byteFlagArray67 = new byte[1];
      this.byteFlagArray36 = new byte[1];
      this.byteFlagArray111 = new byte[1];
      this.byteFlagArray108 = new byte[1];
      this.byteFlagArray122 = new byte[1];
      this.byteFlagArray53 = new byte[1];
      this.byteFlagArray177 = new byte[1];
      this.byteFlagArray21 = new byte[1];
      this.byteFlagArray3 = new byte[1];
      this.byteFlagArray162 = new byte[1];
      this.byteFlagArray23 = new byte[1];
      this.byteFlagArray179 = new byte[1];
      this.byteFlagArray150 = new byte[1];
      this.byteFlagArray121 = new byte[1];
      this.byteFlagArray134 = new byte[1];
      this.byteFlagArray17 = new byte[1];
      this.byteFlagArray156 = new byte[1];
      this.byteFlagArray165 = new byte[1];
      this.byteFlagArray = new byte[1];
      this.byteFlagArray79 = new byte[1];
      this.byteFlagArray183 = new byte[1];
      this.byteFlagArray87 = new byte[1];
      this.byteFlagArray93 = new byte[1];
      this.byteFlagArray126 = new byte[1];
      this.byteFlagArray199 = new byte[1];
      this.byteFlagArray59 = new byte[1];
      this.byteFlagArray115 = new byte[1];
      this.byteFlagArray12 = new byte[1];
      this.byteFlagArray128 = new byte[1];
      this.byteFlagArray20 = new byte[1];
      this.byteFlagArray71 = new byte[1];
      this.byteFlagArray104 = new byte[1];
      this.byteFlagArray191 = new byte[1];
      this.byteFlagArray143 = new byte[1];
      this.byteFlagArray76 = new byte[1];
      this.byteFlagArray192 = new byte[1];
      this.byteFlagArray60 = new byte[1];
      this.byteFlagArray19 = new byte[1];
      this.byteFlagArray171 = new byte[1];
      this.byteFlagArray138 = new byte[1];
      this.byteFlagArray98 = new byte[1];
      this.byteFlagArray72 = new byte[1];
      this.byteFlagArray38 = new byte[1];
      this.byteFlagArray35 = new byte[1];
      this.byteFlagArray16 = new byte[1];
      this.byteFlagArray55 = new byte[1];
      this.byteFlagArray152 = new byte[1];
      this.byteFlagArray84 = new byte[1];
      this.byteFlagArray190 = new byte[1];
      this.byteFlagArray18 = new byte[1];
      this.byteFlagArray103 = new byte[1];
      this.byteFlagArray41 = new byte[1];
      this.byteFlagArray135 = new byte[1];
      this.byteFlagArray187 = new byte[1];
      this.byteFlagArray61 = new byte[1];
      this.byteFlagArray201 = new byte[1];
      this.byteFlagArray113 = new byte[1];
      this.byteFlagArray25 = new byte[1];
      this.byteFlagArray125 = new byte[1];
      this.byteFlagArray29 = new byte[1];
      this.byteFlagArray27 = new byte[1];
      this.byteFlagArray88 = new byte[1];
      this.byteFlagArray181 = new byte[1];
      this.byteFlagArray22 = new byte[1];
      this.byteFlagArray9 = new byte[1];
      this.byteFlagArray146 = new byte[1];
      this.byteFlagArray141 = new byte[1];
      this.byteFlagArray172 = new byte[1];
      this.byteFlagArray110 = new byte[1];
      this.byteFlagArray42 = new byte[1];
      this.byteFlagArray189 = new byte[1];
      this.byteFlagArray66 = new byte[1];
      this.byteFlagArray6 = new byte[1];
      this.byteFlagArray137 = new byte[1];
      this.byteFlagArray91 = new byte[1];
      this.byteFlagArray132 = new byte[1];
      this.byteFlagArray169 = new byte[1];
      this.byteFlagArray145 = new byte[1];
      this.byteFlagArray139 = new byte[1];
      this.byteFlagArray117 = new byte[1];
      this.byteFlagArray47 = new byte[1];
      this.byteFlagArray10 = new byte[1];
      this.byteFlagArray97 = new byte[1];
      this.byteFlagArray11 = new byte[1];
      this.byteFlagArray96 = new byte[1];
      this.byteFlagArray175 = new byte[1];
      this.byteFlagArray81 = new byte[1];
      this.byteFlagArray63 = new byte[1];
      this.byteFlagArray82 = new byte[1];
      this.byteFlagArray182 = new byte[1];
      this.byteFlagArray204 = new byte[1];
      this.byteFlagArray133 = new byte[1];
      this.byteFlagArray44 = new byte[1];
      this.byteFlagArray166 = new byte[1];
      this.byteFlagArray161 = new byte[1];
      this.byteFlagArray106 = new byte[1];
      this.byteFlagArray170 = new byte[1];
      this.byteFlagArray200 = new byte[1];
      this.byteFlagArray28 = new byte[1];
      this.byteFlagArray205 = new byte[1];
      this.byteFlagArray163 = new byte[1];
      this.byteFlagArray109 = new byte[1];
      this.byteFlagArray157 = new byte[1];
      this.byteFlagArray119 = new byte[1];
      this.byteFlagArray130 = new byte[1];
      this.byteFlagArray124 = new byte[1];
      this.byteFlagArray58 = new byte[1];
      this.byteFlagArray101 = new byte[1];
      this.byteFlagArray99 = new byte[1];
      this.byteFlagArray176 = new byte[1];
      this.byteFlagArray100 = new byte[1];
      this.byteFlagArray69 = new byte[1];
      this.byteFlagArray74 = new byte[1];
      this.byteFlagArray51 = new byte[1];
      this.byteFlagArray197 = new byte[1];
      this.byteFlagArray83 = new byte[1];
      this.byteFlagArray174 = new byte[1];
      this.byteFlagArray24 = new byte[1];
      this.byteFlagArray89 = new byte[1];
      this.byteFlagArray154 = new byte[1];
      this.byteFlagArray39 = new byte[1];
      this.byteFlagArray68 = new byte[1];
      this.byteFlagArray94 = new byte[1];
      this.byteFlagArray86 = new byte[1];
      this.specialLongEntry = specialLongEntry2;
   }

   static {
      int count6 = 0;
      long value = 0x700034f013881b39L;
      long value2 = -0x5d7b881025bf6103L;
      int[] tempCountArray2 = null;
      int[] tempCountArray3 = null;
      int[][] tempCountArray4 = null;
      int[] tempCountArray6 = null;

      while (true) {
         int count7 = (int)(value % 7L);
         if (count7 != 3) {
            if (count7 != 6) {
               if (count7 == 4) {
                  tempCountArray4 = new int[16][256];
                  value = (value ^ -0x25a9fc89d4cf55aL) + 0x50bec50260a18ad9L;
                  value2 = (value2 ^ -0x54c723f24b9c6da7L) + 0x13c7d33a71081770L;
                  continue;
               }

               if (count7 == 1) {
                  tempCountArray3 = new int[]{
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
                  value = (value ^ -0x5fa78c7b6cacfa5fL) - 0x614b580e28f1cd50L;
                  value2 = (value2 ^ 0x2444050fbb103a0cL) - 0x4bebfe46f779949fL;
                  continue;
               }

               if (count7 == 0) {
                  count6 = 0;
                  value = (value ^ 0x648684dbc1406726L) - 0x2727588f781ca054L;
                  value2 = (value2 ^ 0x59b3dc64db756394L) - 0x2cbb9e711893ee35L;
                  continue;
               }

               if (count7 != 5) {
                  tempCountArray6 = new int[]{
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
                  value = (value ^ -0x4a85e0f4ad331afL) - 0x159cafee17eb6d86L;
                  value2 = (value2 ^ 0x678c6cb9882d52fbL) - 0x5f7c3c2c35d0ffc1L;
                  continue;
               }

               int[] countArray8 = tempCountArray4[2];
               long value3;
               long value4;
               int count8;
               if ((count6 & 128) == 0) {
                  value3 = (value ^ 0x7183c2249fc0433fL) + 0x317d422399bfc32fL;
                  value4 = (value2 ^ 0x6500a0b38b21ce36L) - 0x5affa07084e041d2L;
                  count8 = count6 << 1 & 0xFF;
               } else {
                  count8 = (count6 << 1 ^ 27) & 0xFF;
                  value3 = (value ^ -0x4a0175886d4a5f31L) + 0x75d279d48e555fbfL;
                  value4 = (value2 ^ -0x673d7a8a8de0ac2eL) + 0x77be7accb2093052L;
               }

               countArray8[count6] = count8;
               tempCountArray4[3][count6] = tempCountArray4[2][count6] ^ count6;
               tempCountArray4[9][count6] = tempCountArray4[2][tempCountArray4[2][tempCountArray4[2][count6]]] ^ count6;
               tempCountArray4[11][count6] = tempCountArray4[2][tempCountArray4[2][count6] ^ count6] ^ count6;
               tempCountArray4[13][count6] = tempCountArray4[2][tempCountArray4[2][count6 ^ count6]] ^ count6;
               tempCountArray4[14][count6] = tempCountArray4[2][tempCountArray4[2][count6 ^ count6] ^ count6];
               count6++;
               value = (value3 ^ 0x5c72e5cb41a935f0L) + 0x380bbb616c2fb6f9L;
               value2 = (value4 ^ 0x6f8ba7e6ed897dfbL) + 0x2d25b76550a8f2b7L;
            }

            if (count6 >= 256) {
               break;
            }

            value = (value ^ -0x34167981579daaefL) + 0x6f991ef5fad76ae6L;
            value2 = (value2 ^ -0x22a27e36e3ab73baL) - 0x4143ded4407d5c04L;
         } else {
            tempCountArray2 = new int[]{0, 1, 2, 4, 8, 16, 32, 64, 128, 27, 54};
            value = (value ^ -0xa1a5d836b62ab9L) - 0x39b84b5ae0970e15L;
            value2 = (value2 ^ -0x58c4d7617fc959ddL) + 0x99af7214a0490c5L;
         }
      }

      countArray2 = tempCountArray2;
      countArray3 = tempCountArray3;
      countArray4 = tempCountArray4;
      countArray6 = tempCountArray6;
   }

   public void decode(ChannelHandlerContext channelHandlerContext, Object entry, List list) throws Exception {
      ByteBuf byteBuf = (ByteBuf)entry;
      this.applyChannelHandlerContext(channelHandlerContext, byteBuf, list);
   }
}
