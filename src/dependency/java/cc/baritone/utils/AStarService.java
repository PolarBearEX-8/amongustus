package cc.baritone.utils;

public final class AStarService {
   public static final int[][] countArray = new int[][]{{22050, 24000, 16000, 1}, {44100, 48000, 32000, 1}, {11025, 12000, 8000, 1}};
   public static final int count = 0;
   public static final int count2 = 2;
   public static final int count3 = 1;
   public static final int count4 = 0;
   public static final int count5 = 1;
   public static final int count6 = 2;
   public static final int count7 = 3;
   public static final int count8 = 0;
   public static final int count9 = 1;
   public static final int count10 = 2;
   public int count11;
   public int count12;
   public int count13;
   public int count14;
   public int count15;
   public int count16;
   public int count17;
   public int count18;
   public int count19;
   public int count20;
   public boolean enabled;
   public boolean enabled2;
   public double[] valueArray = new double[]{-1.0, 384.0, 1152.0, 1152.0};
   public boolean enabled3;
   public int count21;
   public int count22;
   public int count23;
   public byte[] byteFlagArray;
   public byte byteFlag;
   public ControllerProvider controllerProvider;
   public short shortCode;
   public int count24;
   public int count25;
   public int count26;
   public static final int[][][] countArray2 = new int[][][]{
      {
            {0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0},
            {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0},
            {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}
      },
      {
            {0, 32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000, 0},
            {0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000, 0},
            {0, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 0}
      },
      {
            {0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0},
            {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0},
            {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}
      }
   };
   public static final String[][][] stringArray = new String[][][]{
      {
            {
                  "free format",
                  "32 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "176 kbit/s",
                  "192 kbit/s",
                  "224 kbit/s",
                  "256 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "8 kbit/s",
                  "16 kbit/s",
                  "24 kbit/s",
                  "32 kbit/s",
                  "40 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "8 kbit/s",
                  "16 kbit/s",
                  "24 kbit/s",
                  "32 kbit/s",
                  "40 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "forbidden"
            }
      },
      {
            {
                  "free format",
                  "32 kbit/s",
                  "64 kbit/s",
                  "96 kbit/s",
                  "128 kbit/s",
                  "160 kbit/s",
                  "192 kbit/s",
                  "224 kbit/s",
                  "256 kbit/s",
                  "288 kbit/s",
                  "320 kbit/s",
                  "352 kbit/s",
                  "384 kbit/s",
                  "416 kbit/s",
                  "448 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "32 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "160 kbit/s",
                  "192 kbit/s",
                  "224 kbit/s",
                  "256 kbit/s",
                  "320 kbit/s",
                  "384 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "32 kbit/s",
                  "40 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "160 kbit/s",
                  "192 kbit/s",
                  "224 kbit/s",
                  "256 kbit/s",
                  "320 kbit/s",
                  "forbidden"
            }
      },
      {
            {
                  "free format",
                  "32 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "176 kbit/s",
                  "192 kbit/s",
                  "224 kbit/s",
                  "256 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "8 kbit/s",
                  "16 kbit/s",
                  "24 kbit/s",
                  "32 kbit/s",
                  "40 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "8 kbit/s",
                  "16 kbit/s",
                  "24 kbit/s",
                  "32 kbit/s",
                  "40 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "forbidden"
            }
      }
   };

   public AStarService() {
      this.byteFlag = DepthFirstPipeline.byteFlag;
      this.count26 = -1;
   }

   public void applyByteArray(byte[] byteValueArray) throws AStarScheduler {
      String text = "Xing";
      byte[] byteValueArray2 = new byte[4];
      byte byteValue;
      if (this.count16 == 1) {
         if (this.count17 == 3) {
            byteValue = 17;
         } else {
            byteValue = 32;
         }
      } else if (this.count17 == 3) {
         byteValue = 9;
      } else {
         byteValue = 17;
      }

      try {
         System.arraycopy(byteValueArray, byteValue, byteValueArray2, 0, 4);
         if (text.equals(new String(byteValueArray2))) {
            this.enabled3 = true;
            this.count21 = -1;
            this.count23 = -1;
            this.count22 = -1;
            this.byteFlagArray = new byte[100];
            byte[] byteValueArray3 = new byte[4];
            System.arraycopy(byteValueArray, byteValue + 4, byteValueArray3, 0, byteValueArray3.length);
            int count27 = 4 + byteValueArray3.length;
            if ((byteValueArray3[3] & 1) != 0) {
               System.arraycopy(byteValueArray, byteValue + count27, byteValueArray2, 0, byteValueArray2.length);
               this.count21 = byteValueArray2[0] << 24 & 0xFF000000 | byteValueArray2[1] << 16 & 0xFF0000 | byteValueArray2[2] << 8 & 0xFF00 | byteValueArray2[3] & 255;
               count27 += 4;
            }

            if ((byteValueArray3[3] & 2) != 0) {
               System.arraycopy(byteValueArray, byteValue + count27, byteValueArray2, 0, byteValueArray2.length);
               this.count23 = byteValueArray2[0] << 24 & 0xFF000000 | byteValueArray2[1] << 16 & 0xFF0000 | byteValueArray2[2] << 8 & 0xFF00 | byteValueArray2[3] & 255;
               count27 += 4;
            }

            if ((byteValueArray3[3] & 4) != 0) {
               System.arraycopy(byteValueArray, byteValue + count27, this.byteFlagArray, 0, this.byteFlagArray.length);
               count27 += this.byteFlagArray.length;
            }

            if ((byteValueArray3[3] & 8) != 0) {
               System.arraycopy(byteValueArray, byteValue + count27, byteValueArray2, 0, byteValueArray2.length);
               this.count22 = byteValueArray2[0] << 24 & 0xFF000000 | byteValueArray2[1] << 16 & 0xFF0000 | byteValueArray2[2] << 8 & 0xFF00 | byteValueArray2[3] & 255;
               count27 += 4;
            }
         }
      } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
         throw new AStarScheduler("XingVBRHeader Corrupted", arrayIndexOutOfBoundsException);
      }

      String text2 = "VBRI";
      byte byteValue2 = 32;

      try {
         System.arraycopy(byteValueArray, byteValue2, byteValueArray2, 0, 4);
         if (text2.equals(new String(byteValueArray2))) {
            this.enabled3 = true;
            this.count21 = -1;
            this.count23 = -1;
            this.count22 = -1;
            this.byteFlagArray = new byte[100];
            int count28 = 10;
            System.arraycopy(byteValueArray, byteValue2 + 10, byteValueArray2, 0, byteValueArray2.length);
            this.count23 = byteValueArray2[0] << 24 & 0xFF000000 | byteValueArray2[1] << 16 & 0xFF0000 | byteValueArray2[2] << 8 & 0xFF00 | byteValueArray2[3] & 255;
            count28 += 4;
            System.arraycopy(byteValueArray, byteValue2 + 14, byteValueArray2, 0, byteValueArray2.length);
            this.count21 = byteValueArray2[0] << 24 & 0xFF000000 | byteValueArray2[1] << 16 & 0xFF0000 | byteValueArray2[2] << 8 & 0xFF00 | byteValueArray2[3] & 255;
            count28 += 4;
         }
      } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException2) {
         throw new AStarScheduler("VBRIVBRHeader Corrupted", arrayIndexOutOfBoundsException2);
      }
   }

   public int getCountAsIndex() {
      return countArray2[this.count16][this.count11 - 1][this.count13];
   }

   public int getCount22() {
      return this.count22;
   }

   public int getCount19() {
      return this.count19;
   }

   @Override
   public String toString() {
      StringBuffer builder = new StringBuffer(200);
      builder.append("Layer ");
      builder.append(this.getText2());
      builder.append(" frame ");
      builder.append(this.getText());
      builder.append(' ');
      builder.append(this.getTextAsText());
      if (!this.isActive2()) {
         builder.append(" no");
      }

      builder.append(" checksums");
      builder.append(' ');
      builder.append(this.getText3());
      builder.append(',');
      builder.append(' ');
      builder.append(this.getText4());
      return builder.toString();
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public int getCount16() {
      return this.count16;
   }

   public int getCount13() {
      return this.count13;
   }

   public boolean isActive2() {
      return this.count12 == 0;
   }

   public String getText3() {
      switch (this.count18) {
         case 0:
            if (this.count16 == 1) {
               return "44.1 kHz";
            } else {
               if (this.count16 == 0) {
                  return "22.05 kHz";
               }

               return "11.025 kHz";
            }
         case 1:
            if (this.count16 == 1) {
               return "48 kHz";
            } else {
               if (this.count16 == 0) {
                  return "24 kHz";
               }

               return "12 kHz";
            }
         case 2:
            if (this.count16 == 1) {
               return "32 kHz";
            } else {
               if (this.count16 == 0) {
                  return "16 kHz";
               }

               return "8 kHz";
            }
         default:
            return null;
      }
   }

   public int computeCount(int count27) {
      if (this.enabled3) {
         return this.count21;
      } else {
         return this.count24 + 5 - this.count14 == 0 ? 0 : count27 / (this.count24 + 5 - this.count14);
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public int getCount20() {
      return this.count20;
   }

   public int getCount26() {
      return this.count26;
   }

   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, ControllerProvider[] controllerProviderArray) throws AStarScheduler {
      boolean flag = false;

      int count27;
      do {
         count27 = depthFirstPipeline.computeCount(this.byteFlag);
         this.count26 = count27;
         if (this.byteFlag == DepthFirstPipeline.byteFlag) {
            this.count16 = count27 >>> 19 & 1;
            if ((count27 >>> 20 & 1) == 0) {
               if (this.count16 != 0) {
                  throw depthFirstPipeline.resolveAStarScheduler(256);
               }

               this.count16 = 2;
            }

            if ((this.count18 = count27 >>> 10 & 3) == 3) {
               throw depthFirstPipeline.resolveAStarScheduler(256);
            }
         }

         this.count11 = 4 - (count27 >>> 17) & 3;
         this.count12 = count27 >>> 16 & 1;
         this.count13 = count27 >>> 12 & 15;
         this.count14 = count27 >>> 9 & 1;
         this.count17 = count27 >>> 6 & 3;
         this.count15 = count27 >>> 4 & 3;
         if (this.count17 == 1) {
            this.count20 = (this.count15 << 2) + 4;
         } else {
            this.count20 = 0;
         }

         if ((count27 >>> 3 & 1) == 1) {
            this.enabled = true;
         }

         if ((count27 >>> 2 & 1) == 1) {
            this.enabled2 = true;
         }

         if (this.count11 == 1) {
            this.count19 = 32;
         } else {
            int count28 = this.count13;
            if (this.count17 != 3) {
               if (count28 == 4) {
                  count28 = 1;
               } else {
                  count28 -= 4;
               }
            }

            if (count28 != 1 && count28 != 2) {
               if (this.count18 != 1 && (count28 < 3 || count28 > 5)) {
                  this.count19 = 30;
               } else {
                  this.count19 = 27;
               }
            } else if (this.count18 == 2) {
               this.count19 = 12;
            } else {
               this.count19 = 8;
            }
         }

         if (this.count20 > this.count19) {
            this.count20 = this.count19;
         }

         this.getCount2();
         int count29 = depthFirstPipeline.computeCount2(this.count24);
         if (this.count24 >= 0 && count29 != this.count24) {
            throw depthFirstPipeline.resolveAStarScheduler(261);
         }

         if (depthFirstPipeline.checkIndex(this.byteFlag)) {
            if (this.byteFlag == DepthFirstPipeline.byteFlag) {
               this.byteFlag = DepthFirstPipeline.byteFlag2;
               depthFirstPipeline.applyIndex(count27 & -521024);
            }

            flag = true;
         } else {
            depthFirstPipeline.updateState();
         }
      } while (!flag);

      depthFirstPipeline.updateState5();
      if (this.count12 == 0) {
         this.shortCode = (short)depthFirstPipeline.computeCount3(16);
         if (this.controllerProvider == null) {
            this.controllerProvider = new ControllerProvider();
         }

         this.controllerProvider.applyIndex(count27, 16);
         controllerProviderArray[0] = this.controllerProvider;
      } else {
         controllerProviderArray[0] = null;
      }
   }

   public int getCount15() {
      return this.count15;
   }

   public boolean isActive() {
      return this.shortCode == this.controllerProvider.getShortCode();
   }

   public int getCount25() {
      return this.count25;
   }

   public int getCount3() {
      return countArray[this.count16][this.count18];
   }

   public float getFactor() {
      if (!this.enabled3) {
         return new float[][]{{8.707483F, 8.0F, 12.0F}, {26.12245F, 24.0F, 36.0F}, {26.12245F, 24.0F, 36.0F}}[this.count11 - 1][this.count18];
      } else {
         double offset = this.valueArray[this.getCount11()] / (double)this.getCount3();
         if (this.count16 == 0 || this.count16 == 2) {
            offset /= 2.0;
         }

         return (float)(offset * 1000.0);
      }
   }

   public String getText4() {
      return this.enabled3 ? this.getCount() / 1000 + " kb/s" : stringArray[this.count16][this.count11 - 1][this.count13];
   }

   public int getCount2() {
      if (this.count11 == 1) {
         this.count24 = 12 * countArray2[this.count16][0][this.count13] / countArray[this.count16][this.count18];
         if (this.count14 != 0) {
            this.count24++;
         }

         this.count24 <<= 2;
         this.count25 = 0;
      } else {
         this.count24 = 144 * countArray2[this.count16][this.count11 - 1][this.count13] / countArray[this.count16][this.count18];
         if (this.count16 == 0 || this.count16 == 2) {
            this.count24 >>= 1;
         }

         if (this.count14 != 0) {
            this.count24++;
         }

         if (this.count11 == 3) {
            if (this.count16 == 1) {
               this.count25 = this.count24 - (this.count17 == 3 ? 17 : 32) - (this.count12 != 0 ? 0 : 2) - 4;
            } else {
               this.count25 = this.count24 - (this.count17 == 3 ? 9 : 17) - (this.count12 != 0 ? 0 : 2) - 4;
            }
         } else {
            this.count25 = 0;
         }
      }

      this.count24 -= 4;
      return this.count24;
   }

   public String getText2() {
      switch (this.count11) {
         case 1:
            return "I";
         case 2:
            return "II";
         case 3:
            return "III";
         default:
            return null;
      }
   }

   public int getCount() {
      return this.enabled3
         ? (int)((float)(this.count23 * 8) / (this.getFactor() * (float)this.count21)) * 1000
         : countArray2[this.count16][this.count11 - 1][this.count13];
   }

   public String getTextAsText() {
      switch (this.count16) {
         case 0:
            return "MPEG-2 LSF";
         case 1:
            return "MPEG-1";
         case 2:
            return "MPEG-2.5 LSF";
         default:
            return null;
      }
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public float computeFactor(int count27) {
      return (float)this.computeCountByIndex(count27) * this.getFactor();
   }

   public int getCount17() {
      return this.count17;
   }

   public int getCount18() {
      return this.count18;
   }

   public boolean isActiveAsFlag() {
      return this.count14 != 0;
   }

   public int getCount11() {
      return this.count11;
   }

   public String getText() {
      switch (this.count17) {
         case 0:
            return "Stereo";
         case 1:
            return "Joint stereo";
         case 2:
            return "Dual channel";
         case 3:
            return "Single channel";
         default:
            return null;
      }
   }

   public int computeCountByIndex(int count27) {
      if (this.enabled3) {
         return this.count21;
      } else {
         return this.count24 + 4 - this.count14 == 0 ? 0 : count27 / (this.count24 + 4 - this.count14);
      }
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }
}
