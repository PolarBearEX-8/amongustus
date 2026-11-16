package cc.baritone.utils;

public final class DepthFirstPipelineCallbackHandler implements CallbackHandler {
   public final double value = 1.3333333333333333;
   public int[] countArray;
   public int count = 0;
   public int[] countArray2;
   public float[][][] factorArray;
   public float[][][] factorArray2;
   public float[] factorArray3;
   public float[][] factorArray4;
   public float[][] factorArray5;
   public int[] countArray3;
   public DepthFirstPipeline depthFirstPipeline;
   public AStarService aStarService;
   public SfdSerController sfdSerController;
   public SfdSerController sfdSerController2;
   public ExtraCustom extraCustom;
   public int count2;
   public ControllerSupport controllerSupport;
   public RuntimeComponent10 runtimeComponent10;
   public RecordNodeDelegate[] recordNodeDelegateArray;
   public RecordNodeDelegate[] recordNodeDelegateArray2;
   public int count3;
   public int count4;
   public int count5;
   public int count6;
   public int count7;
   public int count8;
   public int count9;
   public float[] factorArray6 = new float[32];
   public float[] factorArray7 = new float[32];
   public final int[] countArray4 = new int[4];
   public int[] countArray5 = new int[]{0};
   public int[] countArray6 = new int[]{0};
   public int[] countArray7 = new int[]{0};
   public int[] countArray8 = new int[]{0};
   public int[] countArray9 = new int[576];
   public float[] factorArray8 = new float[576];
   public float[] factorArray9 = new float[18];
   public float[] factorArray10 = new float[36];
   public int count10 = 0;
   public static final int count11 = 18;
   public static final int count12 = 32;
   public static final int[][] countArray10 = new int[][]{{0, 0, 0, 0, 3, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4}, {0, 1, 2, 3, 0, 1, 2, 3, 1, 2, 3, 1, 2, 3, 2, 3}};
   public static final int[] countArray11 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 3, 2, 0};
   public RecordNodeImpl[] recordNodeImplArray;
   public static final float[] factorArray11 = new float[]{
      1.0F,
      0.70710677F,
      0.5F,
      0.35355338F,
      0.25F,
      0.17677669F,
      0.125F,
      0.088388346F,
      0.0625F,
      0.044194173F,
      0.03125F,
      0.022097087F,
      0.015625F,
      0.011048543F,
      0.0078125F,
      0.0055242716F,
      0.00390625F,
      0.0027621358F,
      0.001953125F,
      0.0013810679F,
      9.765625E-4F,
      6.9053395E-4F,
      4.8828125E-4F,
      3.4526698E-4F,
      2.4414062E-4F,
      1.7263349E-4F,
      1.2207031E-4F,
      8.6316744E-5F,
      6.1035156E-5F,
      4.3158372E-5F,
      3.0517578E-5F,
      2.1579186E-5F,
      1.5258789E-5F,
      1.0789593E-5F,
      7.6293945E-6F,
      5.3947965E-6F,
      3.8146973E-6F,
      2.6973983E-6F,
      1.9073486E-6F,
      1.3486991E-6F,
      9.536743E-7F,
      6.7434956E-7F,
      4.7683716E-7F,
      3.3717478E-7F,
      2.3841858E-7F,
      1.6858739E-7F,
      1.1920929E-7F,
      8.4293696E-8F,
      5.9604645E-8F,
      4.2146848E-8F,
      2.9802322E-8F,
      2.1073424E-8F,
      1.4901161E-8F,
      1.0536712E-8F,
      7.450581E-9F,
      5.268356E-9F,
      3.7252903E-9F,
      2.634178E-9F,
      1.8626451E-9F,
      1.317089E-9F,
      9.313226E-10F,
      6.585445E-10F,
      4.656613E-10F,
      3.2927225E-10F
   };
   public static final float[] factorArray12 = getFloatArray();
   public static final float[][] factorArray13 = new float[][]{
      {
            1.0F,
            0.8408964F,
            0.70710677F,
            0.59460354F,
            0.5F,
            0.4204482F,
            0.35355338F,
            0.29730177F,
            0.25F,
            0.2102241F,
            0.17677669F,
            0.14865088F,
            0.125F,
            0.10511205F,
            0.088388346F,
            0.07432544F,
            0.0625F,
            0.052556027F,
            0.044194173F,
            0.03716272F,
            0.03125F,
            0.026278013F,
            0.022097087F,
            0.01858136F,
            0.015625F,
            0.013139007F,
            0.011048543F,
            0.00929068F,
            0.0078125F,
            0.0065695033F,
            0.0055242716F,
            0.00464534F
      },
      {
            1.0F,
            0.70710677F,
            0.5F,
            0.35355338F,
            0.25F,
            0.17677669F,
            0.125F,
            0.088388346F,
            0.0625F,
            0.044194173F,
            0.03125F,
            0.022097087F,
            0.015625F,
            0.011048543F,
            0.0078125F,
            0.0055242716F,
            0.00390625F,
            0.0027621358F,
            0.001953125F,
            0.0013810679F,
            9.765625E-4F,
            6.9053395E-4F,
            4.8828125E-4F,
            3.4526698E-4F,
            2.4414062E-4F,
            1.7263349E-4F,
            1.2207031E-4F,
            8.6316744E-5F,
            6.1035156E-5F,
            4.3158372E-5F,
            3.0517578E-5F,
            2.1579186E-5F
      }
   };
   public static final float[] factorArray14 = new float[]{
      0.0F,
      0.2679492F,
      0.57735026F,
      1.0F,
      1.7320508F,
      3.732051F,
      10.0E10F,
      -3.732051F,
      -1.7320508F,
      -1.0F,
      -0.57735026F,
      -0.2679492F,
      0.0F,
      0.2679492F,
      0.57735026F,
      1.0F
   };
   public static int[][] countArray12;
   public static final float[] factorArray15 = new float[]{0.8574929F, 0.881742F, 0.94962865F, 0.9833146F, 0.9955178F, 0.9991606F, 0.9998992F, 0.99999315F};
   public static final float[] factorArray16 = new float[]{
      -0.51449573F, -0.47173196F, -0.31337744F, -0.1819132F, -0.09457419F, -0.040965583F, -0.014198569F, -0.0036999746F
   };
   public static final float[][] factorArray17 = new float[][]{
      {
            -0.016141215F,
            -0.05360318F,
            -0.100707136F,
            -0.16280818F,
            -0.5F,
            -0.38388735F,
            -0.6206114F,
            -1.1659756F,
            -3.8720753F,
            -4.225629F,
            -1.519529F,
            -0.97416484F,
            -0.73744076F,
            -1.2071068F,
            -0.5163616F,
            -0.45426053F,
            -0.40715656F,
            -0.3696946F,
            -0.3387627F,
            -0.31242222F,
            -0.28939587F,
            -0.26880082F,
            -0.5F,
            -0.23251417F,
            -0.21596715F,
            -0.20004979F,
            -0.18449493F,
            -0.16905846F,
            -0.15350361F,
            -0.13758625F,
            -0.12103922F,
            -0.20710678F,
            -0.084752575F,
            -0.06415752F,
            -0.041131172F,
            -0.014790705F
      },
      {
            -0.016141215F,
            -0.05360318F,
            -0.100707136F,
            -0.16280818F,
            -0.5F,
            -0.38388735F,
            -0.6206114F,
            -1.1659756F,
            -3.8720753F,
            -4.225629F,
            -1.519529F,
            -0.97416484F,
            -0.73744076F,
            -1.2071068F,
            -0.5163616F,
            -0.45426053F,
            -0.40715656F,
            -0.3696946F,
            -0.33908543F,
            -0.3151181F,
            -0.29642227F,
            -0.28184548F,
            -0.5411961F,
            -0.2621323F,
            -0.25387916F,
            -0.2329629F,
            -0.19852729F,
            -0.15233535F,
            -0.0964964F,
            -0.03342383F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F
      },
      {
            -0.0483008F,
            -0.15715657F,
            -0.28325045F,
            -0.42953748F,
            -1.2071068F,
            -0.8242648F,
            -1.1451749F,
            -1.769529F,
            -4.5470223F,
            -3.489053F,
            -0.7329629F,
            -0.15076515F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F
      },
      {
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            -0.15076514F,
            -0.7329629F,
            -3.489053F,
            -4.5470223F,
            -1.769529F,
            -1.1451749F,
            -0.8313774F,
            -1.306563F,
            -0.54142016F,
            -0.46528974F,
            -0.4106699F,
            -0.3700468F,
            -0.3387627F,
            -0.31242222F,
            -0.28939587F,
            -0.26880082F,
            -0.5F,
            -0.23251417F,
            -0.21596715F,
            -0.20004979F,
            -0.18449493F,
            -0.16905846F,
            -0.15350361F,
            -0.13758625F,
            -0.12103922F,
            -0.20710678F,
            -0.084752575F,
            -0.06415752F,
            -0.041131172F,
            -0.014790705F
      }
   };
   public RecordNode recordNode;
   public static final int[][][] countArray13 = new int[][][]{
      {{6, 5, 5, 5}, {9, 9, 9, 9}, {6, 9, 9, 9}},
      {{6, 5, 7, 3}, {9, 9, 12, 6}, {6, 9, 12, 6}},
      {{11, 10, 0, 0}, {18, 18, 0, 0}, {15, 18, 0, 0}},
      {{7, 7, 7, 0}, {12, 12, 12, 0}, {6, 15, 12, 0}},
      {{6, 6, 6, 3}, {12, 9, 9, 6}, {6, 12, 9, 6}},
      {{8, 8, 5, 0}, {15, 12, 9, 0}, {6, 18, 9, 0}}
   };

   private void applyIndex4(int count13, int count14) {
      this.countArray5[0] = 0;
      this.countArray6[0] = 0;
      this.countArray7[0] = 0;
      this.countArray8[0] = 0;
      int count15 = this.count5 + this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count;
      int count16;
      int count17;
      if (this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count2 != 0
         && this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count3 == 2) {
         count16 = this.count9 == 8 ? 72 : 36;
         count17 = 576;
      } else {
         int count18 = this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count4 + 1;
         int count19 = count18 + this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count5 + 1;
         if (count19 > this.recordNodeImplArray[this.count9].countArray.length - 1) {
            count19 = this.recordNodeImplArray[this.count9].countArray.length - 1;
         }

         count16 = this.recordNodeImplArray[this.count9].countArray[count18];
         count17 = this.recordNodeImplArray[this.count9].countArray[count19];
      }

      int count20 = 0;

      for (int byteValue = 0; byteValue < this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count6 << 1; byteValue += 2) {
         HelperImpl helperImpl;
         if (byteValue < count16) {
            helperImpl = HelperImpl.helperImplArray[this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].countArray[0]];
         } else if (byteValue < count17) {
            helperImpl = HelperImpl.helperImplArray[this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].countArray[1]];
         } else {
            helperImpl = HelperImpl.helperImplArray[this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].countArray[2]];
         }

         HelperImpl.computeCount(helperImpl, this.countArray5, this.countArray6, this.countArray7, this.countArray8, this.controllerSupport);
         this.countArray2[count20++] = this.countArray5[0];
         this.countArray2[count20++] = this.countArray6[0];
         this.count = this.count + this.countArray5[0] + this.countArray6[0];
      }

      HelperImpl helperImpl2 = HelperImpl.helperImplArray[this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count7 + 32];

      int count21;
      for (count21 = this.controllerSupport.getCount4(); count21 < count15 && count20 < 576; count21 = this.controllerSupport.getCount4()) {
         HelperImpl.computeCount(helperImpl2, this.countArray5, this.countArray6, this.countArray7, this.countArray8, this.controllerSupport);
         this.countArray2[count20++] = this.countArray7[0];
         this.countArray2[count20++] = this.countArray8[0];
         this.countArray2[count20++] = this.countArray5[0];
         this.countArray2[count20++] = this.countArray6[0];
         this.count = this.count + this.countArray7[0] + this.countArray8[0] + this.countArray5[0] + this.countArray6[0];
      }

      if (count21 > count15) {
         this.controllerSupport.applyIndex2(count21 - count15);
         count20 -= 4;
      }

      int count22 = this.controllerSupport.getCount4();
      if (count22 < count15) {
         this.controllerSupport.computeCount(count15 - count22);
      }

      if (count20 < 576) {
         this.countArray3[count13] = count20;
      } else {
         this.countArray3[count13] = 576;
      }

      if (count20 < 0) {
         count20 = 0;
      }

      while (count20 < 576) {
         this.countArray2[count20] = 0;
         count20++;
      }
   }

   private void applyIndex3(int count13, int count14) {
      RecordNodeSupport recordNodeSupport = this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14];
      if (recordNodeSupport.count2 == 0 || recordNodeSupport.count3 != 2 || recordNodeSupport.count8 != 0) {
         short shortValue;
         if (recordNodeSupport.count2 != 0 && recordNodeSupport.count8 != 0 && recordNodeSupport.count3 == 2) {
            shortValue = 18;
         } else {
            shortValue = 558;
         }

         for (int byteValue = 0; byteValue < shortValue; byteValue += 18) {
            for (int index = 0; index < 8; index++) {
               int count15 = byteValue + 17 - index;
               int count16 = byteValue + 18 + index;
               float factor = this.factorArray3[count15];
               float factor2 = this.factorArray3[count16];
               this.factorArray3[count15] = factor * factorArray15[index] - factor2 * factorArray16[index];
               this.factorArray3[count16] = factor2 * factorArray15[index] + factor * factorArray16[index];
            }
         }
      }
   }

   private void applyIndexByIndex(int count13, int count14) {
      int count15 = 0;
      RecordNodeSupport recordNodeSupport = this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14];
      this.applyIndex(count13, count14);
      if (recordNodeSupport.count2 == 0 || recordNodeSupport.count3 != 2) {
         for (int index = 0; index < 21; index++) {
            this.recordNodeDelegateArray2[count13].countArray[index] = this.countArray[count15];
            count15++;
         }

         this.recordNodeDelegateArray2[count13].countArray[21] = 0;
         this.recordNodeDelegateArray2[count13].countArray[22] = 0;
      } else if (recordNodeSupport.count8 != 0) {
         for (int index2 = 0; index2 < 8; index2++) {
            this.recordNodeDelegateArray2[count13].countArray[index2] = this.countArray[count15];
            count15++;
         }

         for (int index3 = 3; index3 < 12; index3++) {
            for (int index4 = 0; index4 < 3; index4++) {
               this.recordNodeDelegateArray2[count13].countArray2[index4][index3] = this.countArray[count15];
               count15++;
            }
         }

         for (int index5 = 0; index5 < 3; index5++) {
            this.recordNodeDelegateArray2[count13].countArray2[index5][12] = 0;
         }
      } else {
         for (int index6 = 0; index6 < 12; index6++) {
            for (int index7 = 0; index7 < 3; index7++) {
               this.recordNodeDelegateArray2[count13].countArray2[index7][index6] = this.countArray[count15];
               count15++;
            }
         }

         for (int index8 = 0; index8 < 3; index8++) {
            this.recordNodeDelegateArray2[count13].countArray2[index8][12] = 0;
         }
      }
   }

   private void applyIndex(int count13, int count14) {
      int count15 = this.aStarService.getCount15();
      int byteValue = 0;
      RecordNodeSupport recordNodeSupport = this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14];
      int count16 = recordNodeSupport.count9;
      byte byteValue2;
      if (recordNodeSupport.count3 == 2) {
         if (recordNodeSupport.count8 == 0) {
            byteValue2 = 1;
         } else if (recordNodeSupport.count8 == 1) {
            byteValue2 = 2;
         } else {
            byteValue2 = 0;
         }
      } else {
         byteValue2 = 0;
      }

      if (count15 != 1 && count15 != 3 || count13 != 1) {
         if (count16 < 400) {
            this.countArray4[0] = (count16 >>> 4) / 5;
            this.countArray4[1] = (count16 >>> 4) % 5;
            this.countArray4[2] = (count16 & 15) >>> 2;
            this.countArray4[3] = count16 & 3;
            this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count10 = 0;
            byteValue = 0;
         } else if (count16 < 500) {
            this.countArray4[0] = (count16 - 400 >>> 2) / 5;
            this.countArray4[1] = (count16 - 400 >>> 2) % 5;
            this.countArray4[2] = count16 - 400 & 3;
            this.countArray4[3] = 0;
            this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count10 = 0;
            byteValue = 1;
         } else if (count16 < 512) {
            this.countArray4[0] = (count16 - 500) / 3;
            this.countArray4[1] = (count16 - 500) % 3;
            this.countArray4[2] = 0;
            this.countArray4[3] = 0;
            this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count10 = 1;
            byteValue = 2;
         }
      }

      if ((count15 == 1 || count15 == 3) && count13 == 1) {
         int count17 = count16 >>> 1;
         if (count17 < 180) {
            this.countArray4[0] = count17 / 36;
            this.countArray4[1] = count17 % 36 / 6;
            this.countArray4[2] = count17 % 36 % 6;
            this.countArray4[3] = 0;
            this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count10 = 0;
            byteValue = 3;
         } else if (count17 < 244) {
            this.countArray4[0] = (count17 - 180 & 63) >>> 4;
            this.countArray4[1] = (count17 - 180 & 15) >>> 2;
            this.countArray4[2] = count17 - 180 & 3;
            this.countArray4[3] = 0;
            this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count10 = 0;
            byteValue = 4;
         } else if (count17 < 255) {
            this.countArray4[0] = (count17 - 244) / 3;
            this.countArray4[1] = (count17 - 244) % 3;
            this.countArray4[2] = 0;
            this.countArray4[3] = 0;
            this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14].count10 = 0;
            byteValue = 5;
         }
      }

      for (int index = 0; index < 45; index++) {
         this.countArray[index] = 0;
      }

      int count18 = 0;

      for (int index2 = 0; index2 < 4; index2++) {
         for (int index3 = 0; index3 < countArray13[byteValue][byteValue2][index2]; index3++) {
            this.countArray[count18] = this.countArray4[index2] == 0 ? 0 : this.controllerSupport.computeCount(this.countArray4[index2]);
            count18++;
         }
      }
   }

   private void applyIndex2(int count13, int count14) {
      RecordNodeSupport recordNodeSupport = this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14];
      int count15 = recordNodeSupport.count9;
      int count16 = countArray10[0][count15];
      int count17 = countArray10[1][count15];
      if (recordNodeSupport.count2 != 0 && recordNodeSupport.count3 == 2) {
         if (recordNodeSupport.count8 != 0) {
            for (int index = 0; index < 8; index++) {
               this.recordNodeDelegateArray2[count13].countArray[index] = this.controllerSupport.computeCount(countArray10[0][recordNodeSupport.count9]);
            }

            for (int index2 = 3; index2 < 6; index2++) {
               for (int index3 = 0; index3 < 3; index3++) {
                  this.recordNodeDelegateArray2[count13].countArray2[index3][index2] = this.controllerSupport.computeCount(countArray10[0][recordNodeSupport.count9]);
               }
            }

            for (int index4 = 6; index4 < 12; index4++) {
               for (int index5 = 0; index5 < 3; index5++) {
                  this.recordNodeDelegateArray2[count13].countArray2[index5][index4] = this.controllerSupport.computeCount(countArray10[1][recordNodeSupport.count9]);
               }
            }

            int byteValue = 12;

            for (int index6 = 0; index6 < 3; index6++) {
               this.recordNodeDelegateArray2[count13].countArray2[index6][byteValue] = 0;
            }
         } else {
            this.recordNodeDelegateArray2[count13].countArray2[0][0] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[1][0] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[2][0] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[0][1] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[1][1] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[2][1] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[0][2] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[1][2] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[2][2] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[0][3] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[1][3] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[2][3] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[0][4] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[1][4] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[2][4] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[0][5] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[1][5] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[2][5] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray2[0][6] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[1][6] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[2][6] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[0][7] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[1][7] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[2][7] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[0][8] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[1][8] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[2][8] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[0][9] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[1][9] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[2][9] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[0][10] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[1][10] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[2][10] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[0][11] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[1][11] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[2][11] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray2[0][12] = 0;
            this.recordNodeDelegateArray2[count13].countArray2[1][12] = 0;
            this.recordNodeDelegateArray2[count13].countArray2[2][12] = 0;
         }
      } else {
         if (this.runtimeComponent10.recordNodeAdapterArray[count13].countArray[0] == 0 || count14 == 0) {
            this.recordNodeDelegateArray2[count13].countArray[0] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray[1] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray[2] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray[3] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray[4] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray[5] = this.controllerSupport.computeCount(count16);
         }

         if (this.runtimeComponent10.recordNodeAdapterArray[count13].countArray[1] == 0 || count14 == 0) {
            this.recordNodeDelegateArray2[count13].countArray[6] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray[7] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray[8] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray[9] = this.controllerSupport.computeCount(count16);
            this.recordNodeDelegateArray2[count13].countArray[10] = this.controllerSupport.computeCount(count16);
         }

         if (this.runtimeComponent10.recordNodeAdapterArray[count13].countArray[2] == 0 || count14 == 0) {
            this.recordNodeDelegateArray2[count13].countArray[11] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray[12] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray[13] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray[14] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray[15] = this.controllerSupport.computeCount(count17);
         }

         if (this.runtimeComponent10.recordNodeAdapterArray[count13].countArray[3] == 0 || count14 == 0) {
            this.recordNodeDelegateArray2[count13].countArray[16] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray[17] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray[18] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray[19] = this.controllerSupport.computeCount(count17);
            this.recordNodeDelegateArray2[count13].countArray[20] = this.controllerSupport.computeCount(count17);
         }

         this.recordNodeDelegateArray2[count13].countArray[21] = 0;
         this.recordNodeDelegateArray2[count13].countArray[22] = 0;
      }
   }

   public void applyFloatArray(float[] factorArray18, float[] factorArray19, int count13) {
      if (count13 == 2) {
         factorArray19[0] = 0.0F;
         factorArray19[1] = 0.0F;
         factorArray19[2] = 0.0F;
         factorArray19[3] = 0.0F;
         factorArray19[4] = 0.0F;
         factorArray19[5] = 0.0F;
         factorArray19[6] = 0.0F;
         factorArray19[7] = 0.0F;
         factorArray19[8] = 0.0F;
         factorArray19[9] = 0.0F;
         factorArray19[10] = 0.0F;
         factorArray19[11] = 0.0F;
         factorArray19[12] = 0.0F;
         factorArray19[13] = 0.0F;
         factorArray19[14] = 0.0F;
         factorArray19[15] = 0.0F;
         factorArray19[16] = 0.0F;
         factorArray19[17] = 0.0F;
         factorArray19[18] = 0.0F;
         factorArray19[19] = 0.0F;
         factorArray19[20] = 0.0F;
         factorArray19[21] = 0.0F;
         factorArray19[22] = 0.0F;
         factorArray19[23] = 0.0F;
         factorArray19[24] = 0.0F;
         factorArray19[25] = 0.0F;
         factorArray19[26] = 0.0F;
         factorArray19[27] = 0.0F;
         factorArray19[28] = 0.0F;
         factorArray19[29] = 0.0F;
         factorArray19[30] = 0.0F;
         factorArray19[31] = 0.0F;
         factorArray19[32] = 0.0F;
         factorArray19[33] = 0.0F;
         factorArray19[34] = 0.0F;
         factorArray19[35] = 0.0F;
         int byteValue = 0;

         for (int index = 0; index < 3; index++) {
            factorArray18[15 + index] = factorArray18[15 + index] + factorArray18[12 + index];
            factorArray18[12 + index] = factorArray18[12 + index] + factorArray18[9 + index];
            factorArray18[9 + index] = factorArray18[9 + index] + factorArray18[6 + index];
            factorArray18[6 + index] = factorArray18[6 + index] + factorArray18[3 + index];
            factorArray18[3 + index] = factorArray18[3 + index] + factorArray18[0 + index];
            factorArray18[15 + index] = factorArray18[15 + index] + factorArray18[9 + index];
            factorArray18[9 + index] = factorArray18[9 + index] + factorArray18[3 + index];
            float factor = factorArray18[12 + index] * 0.5F;
            float factor2 = factorArray18[6 + index] * 0.8660254F;
            float factor3 = factorArray18[0 + index] + factor;
            float factor4 = factorArray18[0 + index] - factorArray18[12 + index];
            float factor5 = factor3 + factor2;
            float factor6 = factor3 - factor2;
            float factor7 = factorArray18[15 + index] * 0.5F;
            float factor8 = factorArray18[9 + index] * 0.8660254F;
            float factor9 = factorArray18[3 + index] + factor7;
            float factor10 = factorArray18[3 + index] - factorArray18[15 + index];
            float factor11 = factor9 + factor8;
            float factor12 = (factor9 - factor8) * 1.9318516F;
            float factor13 = factor10 * 0.70710677F;
            float factor14 = factor11 * 0.5176381F;
            float factor15 = factor5 + factor14;
            float factor16 = factor5 - factor14;
            float factor17 = factor4 + factor13;
            float factor18 = factor4 - factor13;
            float factor19 = factor6 + factor12;
            float factor20 = factor6 - factor12;
            float factor21 = factor15 * 0.5043145F;
            float factor22 = factor17 * 0.5411961F;
            float factor23 = factor19 * 0.6302362F;
            float factor24 = factor20 * 0.8213398F;
            float factor25 = factor18 * 1.306563F;
            float factor26 = factor16 * 3.830649F;
            float factor27 = -factor21 * 0.7933533F;
            float factor28 = -factor21 * 0.6087614F;
            float factor29 = -factor22 * 0.9238795F;
            float factor30 = -factor22 * 0.38268343F;
            float factor31 = -factor23 * 0.9914449F;
            float factor32 = -factor23 * 0.13052619F;
            float factor33 = factor25 * 0.38268343F;
            float factor34 = factor26 * 0.6087614F;
            float factor35 = -factor26 * 0.7933533F;
            float factor36 = -factor25 * 0.9238795F;
            float factor37 = -factor24 * 0.9914449F;
            float factor38 = factor24 * 0.13052619F;
            factorArray19[byteValue + 6] = factorArray19[byteValue + 6] + factor38;
            factorArray19[byteValue + 7] = factorArray19[byteValue + 7] + factor33;
            factorArray19[byteValue + 8] = factorArray19[byteValue + 8] + factor34;
            factorArray19[byteValue + 9] = factorArray19[byteValue + 9] + factor35;
            factorArray19[byteValue + 10] = factorArray19[byteValue + 10] + factor36;
            factorArray19[byteValue + 11] = factorArray19[byteValue + 11] + factor37;
            factorArray19[byteValue + 12] = factorArray19[byteValue + 12] + factor31;
            factorArray19[byteValue + 13] = factorArray19[byteValue + 13] + factor29;
            factorArray19[byteValue + 14] = factorArray19[byteValue + 14] + factor27;
            factorArray19[byteValue + 15] = factorArray19[byteValue + 15] + factor28;
            factorArray19[byteValue + 16] = factorArray19[byteValue + 16] + factor30;
            factorArray19[byteValue + 17] = factorArray19[byteValue + 17] + factor32;
            byteValue += 6;
         }
      } else {
         factorArray18[17] += factorArray18[16];
         factorArray18[16] += factorArray18[15];
         factorArray18[15] += factorArray18[14];
         factorArray18[14] += factorArray18[13];
         factorArray18[13] += factorArray18[12];
         factorArray18[12] += factorArray18[11];
         factorArray18[11] += factorArray18[10];
         factorArray18[10] += factorArray18[9];
         factorArray18[9] += factorArray18[8];
         factorArray18[8] += factorArray18[7];
         factorArray18[7] += factorArray18[6];
         factorArray18[6] += factorArray18[5];
         factorArray18[5] += factorArray18[4];
         factorArray18[4] += factorArray18[3];
         factorArray18[3] += factorArray18[2];
         factorArray18[2] += factorArray18[1];
         factorArray18[1] += factorArray18[0];
         factorArray18[17] += factorArray18[15];
         factorArray18[15] += factorArray18[13];
         factorArray18[13] += factorArray18[11];
         factorArray18[11] += factorArray18[9];
         factorArray18[9] += factorArray18[7];
         factorArray18[7] += factorArray18[5];
         factorArray18[5] += factorArray18[3];
         factorArray18[3] += factorArray18[1];
         float factor39 = factorArray18[0] + factorArray18[0];
         float factor40 = factor39 + factorArray18[12];
         float factor41 = factor40 + factorArray18[4] * 1.8793852F + factorArray18[8] * 1.5320889F + factorArray18[16] * 0.34729636F;
         float factor42 = factor39 + factorArray18[4] - factorArray18[8] - factorArray18[12] - factorArray18[12] - factorArray18[16];
         float factor43 = factor40 - factorArray18[4] * 0.34729636F - factorArray18[8] * 1.8793852F + factorArray18[16] * 1.5320889F;
         float factor44 = factor40 - factorArray18[4] * 1.5320889F + factorArray18[8] * 0.34729636F - factorArray18[16] * 1.8793852F;
         float factor45 = factorArray18[0] - factorArray18[4] + factorArray18[8] - factorArray18[12] + factorArray18[16];
         float factor46 = factorArray18[6] * 1.7320508F;
         float factor47 = factorArray18[2] * 1.9696155F + factor46 + factorArray18[10] * 1.2855753F + factorArray18[14] * 0.6840403F;
         float factor48 = (factorArray18[2] - factorArray18[10] - factorArray18[14]) * 1.7320508F;
         float factor49 = factorArray18[2] * 1.2855753F - factor46 - factorArray18[10] * 0.6840403F + factorArray18[14] * 1.9696155F;
         float factor50 = factorArray18[2] * 0.6840403F - factor46 + factorArray18[10] * 1.9696155F - factorArray18[14] * 1.2855753F;
         float factor51 = factorArray18[1] + factorArray18[1];
         float factor52 = factor51 + factorArray18[13];
         float factor53 = factor52 + factorArray18[5] * 1.8793852F + factorArray18[9] * 1.5320889F + factorArray18[17] * 0.34729636F;
         float factor54 = factor51 + factorArray18[5] - factorArray18[9] - factorArray18[13] - factorArray18[13] - factorArray18[17];
         float factor55 = factor52 - factorArray18[5] * 0.34729636F - factorArray18[9] * 1.8793852F + factorArray18[17] * 1.5320889F;
         float factor56 = factor52 - factorArray18[5] * 1.5320889F + factorArray18[9] * 0.34729636F - factorArray18[17] * 1.8793852F;
         float factor57 = (factorArray18[1] - factorArray18[5] + factorArray18[9] - factorArray18[13] + factorArray18[17]) * 0.70710677F;
         float factor58 = factorArray18[7] * 1.7320508F;
         float factor59 = factorArray18[3] * 1.9696155F + factor58 + factorArray18[11] * 1.2855753F + factorArray18[15] * 0.6840403F;
         float factor60 = (factorArray18[3] - factorArray18[11] - factorArray18[15]) * 1.7320508F;
         float factor61 = factorArray18[3] * 1.2855753F - factor58 - factorArray18[11] * 0.6840403F + factorArray18[15] * 1.9696155F;
         float factor62 = factorArray18[3] * 0.6840403F - factor58 + factorArray18[11] * 1.9696155F - factorArray18[15] * 1.2855753F;
         float factor63 = factor41 + factor47;
         float factor64 = (factor53 + factor59) * 0.5019099F;
         float factor65 = factor63 + factor64;
         float factor66 = factor63 - factor64;
         float factor67 = factor42 + factor48;
         float factor68 = (factor54 + factor60) * 0.5176381F;
         float factor69 = factor67 + factor68;
         float factor70 = factor67 - factor68;
         float factor71 = factor43 + factor49;
         float factor72 = (factor55 + factor61) * 0.55168897F;
         float factor73 = factor71 + factor72;
         float factor74 = factor71 - factor72;
         float factor75 = factor44 + factor50;
         float factor76 = (factor56 + factor62) * 0.61038727F;
         float factor77 = factor75 + factor76;
         float factor78 = factor75 - factor76;
         float factor79 = factor45 + factor57;
         float factor80 = factor45 - factor57;
         float factor81 = factor44 - factor50;
         float factor82 = (factor56 - factor62) * 0.8717234F;
         float factor83 = factor81 + factor82;
         float factor84 = factor81 - factor82;
         float factor85 = factor43 - factor49;
         float factor86 = (factor55 - factor61) * 1.1831008F;
         float factor87 = factor85 + factor86;
         float factor88 = factor85 - factor86;
         float factor89 = factor42 - factor48;
         float factor90 = (factor54 - factor60) * 1.9318516F;
         float factor91 = factor89 + factor90;
         float factor92 = factor89 - factor90;
         float factor93 = factor41 - factor47;
         float factor94 = (factor53 - factor59) * 5.7368565F;
         float factor95 = factor93 + factor94;
         float factor96 = factor93 - factor94;
         float[] factorArray20 = factorArray17[count13];
         factorArray19[0] = -factor96 * factorArray20[0];
         factorArray19[1] = -factor92 * factorArray20[1];
         factorArray19[2] = -factor88 * factorArray20[2];
         factorArray19[3] = -factor84 * factorArray20[3];
         factorArray19[4] = -factor80 * factorArray20[4];
         factorArray19[5] = -factor78 * factorArray20[5];
         factorArray19[6] = -factor74 * factorArray20[6];
         factorArray19[7] = -factor70 * factorArray20[7];
         factorArray19[8] = -factor66 * factorArray20[8];
         factorArray19[9] = factor66 * factorArray20[9];
         factorArray19[10] = factor70 * factorArray20[10];
         factorArray19[11] = factor74 * factorArray20[11];
         factorArray19[12] = factor78 * factorArray20[12];
         factorArray19[13] = factor80 * factorArray20[13];
         factorArray19[14] = factor84 * factorArray20[14];
         factorArray19[15] = factor88 * factorArray20[15];
         factorArray19[16] = factor92 * factorArray20[16];
         factorArray19[17] = factor96 * factorArray20[17];
         factorArray19[18] = factor95 * factorArray20[18];
         factorArray19[19] = factor91 * factorArray20[19];
         factorArray19[20] = factor87 * factorArray20[20];
         factorArray19[21] = factor83 * factorArray20[21];
         factorArray19[22] = factor79 * factorArray20[22];
         factorArray19[23] = factor77 * factorArray20[23];
         factorArray19[24] = factor73 * factorArray20[24];
         factorArray19[25] = factor69 * factorArray20[25];
         factorArray19[26] = factor65 * factorArray20[26];
         factorArray19[27] = factor65 * factorArray20[27];
         factorArray19[28] = factor69 * factorArray20[28];
         factorArray19[29] = factor73 * factorArray20[29];
         factorArray19[30] = factor77 * factorArray20[30];
         factorArray19[31] = factor79 * factorArray20[31];
         factorArray19[32] = factor83 * factorArray20[32];
         factorArray19[33] = factor87 * factorArray20[33];
         factorArray19[34] = factor91 * factorArray20[34];
         factorArray19[35] = factor95 * factorArray20[35];
      }
   }

   private void updateState2() {
      for (int index = 0; index < 18; index++) {
         for (int byteValue = 0; byteValue < 18; byteValue += 3) {
            this.factorArray2[0][index][byteValue] = (this.factorArray2[0][index][byteValue] + this.factorArray2[1][index][byteValue]) * 0.5F;
            this.factorArray2[0][index][byteValue + 1] = (this.factorArray2[0][index][byteValue + 1] + this.factorArray2[1][index][byteValue + 1]) * 0.5F;
            this.factorArray2[0][index][byteValue + 2] = (this.factorArray2[0][index][byteValue + 2] + this.factorArray2[1][index][byteValue + 2]) * 0.5F;
         }
      }
   }

   private void applyFloatArray(float[][] factorMatrix, int count13, int count14) {
      RecordNodeSupport recordNodeSupport = this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14];
      int count15 = 0;
      int count16 = 0;
      int count17 = 0;
      int count18 = 0;
      float[][] factorMatrix2 = factorMatrix;
      int count19;
      if (recordNodeSupport.count2 == 0 || recordNodeSupport.count3 != 2) {
         count19 = this.recordNodeImplArray[this.count9].countArray[1];
      } else if (recordNodeSupport.count8 != 0) {
         count19 = this.recordNodeImplArray[this.count9].countArray[1];
      } else {
         count17 = this.recordNodeImplArray[this.count9].countArray2[1];
         count19 = (count17 << 2) - count17;
         count16 = 0;
      }

      float factor = (float)Math.pow(2.0, 0.25 * ((double)recordNodeSupport.count11 - 210.0));

      for (int index = 0; index < this.countArray3[count13]; index++) {
         int count20 = index % 18;
         int count21 = (index - count20) / 18;
         if (this.countArray2[index] == 0) {
            factorMatrix2[count21][count20] = 0.0F;
         } else {
            int count22 = this.countArray2[index];
            if (count22 < factorArray12.length) {
               if (this.countArray2[index] > 0) {
                  factorMatrix2[count21][count20] = factor * factorArray12[count22];
               } else if (-count22 < factorArray12.length) {
                  factorMatrix2[count21][count20] = -factor * factorArray12[-count22];
               } else {
                  factorMatrix2[count21][count20] = -factor * (float)Math.pow((double)(-count22), 1.3333333333333333);
               }
            } else if (this.countArray2[index] > 0) {
               factorMatrix2[count21][count20] = factor * (float)Math.pow((double)count22, 1.3333333333333333);
            } else {
               factorMatrix2[count21][count20] = -factor * (float)Math.pow((double)(-count22), 1.3333333333333333);
            }
         }
      }

      for (int index2 = 0; index2 < this.countArray3[count13]; index2++) {
         int count23 = index2 % 18;
         int count24 = (index2 - count23) / 18;
         if (count18 == count19) {
            if (recordNodeSupport.count2 == 0 || recordNodeSupport.count3 != 2) {
               count19 = this.recordNodeImplArray[this.count9].countArray[++count15 + 1];
            } else if (recordNodeSupport.count8 != 0) {
               if (count18 == this.recordNodeImplArray[this.count9].countArray[8]) {
                  int count25 = this.recordNodeImplArray[this.count9].countArray2[4];
                  count19 = (count25 << 2) - count25;
                  count15 = 3;
                  count17 = this.recordNodeImplArray[this.count9].countArray2[4] - this.recordNodeImplArray[this.count9].countArray2[3];
                  int count26 = this.recordNodeImplArray[this.count9].countArray2[3];
                  count16 = (count26 << 2) - count26;
               } else if (count18 < this.recordNodeImplArray[this.count9].countArray[8]) {
                  count19 = this.recordNodeImplArray[this.count9].countArray[++count15 + 1];
               } else {
                  int count27 = this.recordNodeImplArray[this.count9].countArray2[++count15 + 1];
                  count19 = (count27 << 2) - count27;
                  int count28 = this.recordNodeImplArray[this.count9].countArray2[count15];
                  count17 = this.recordNodeImplArray[this.count9].countArray2[count15 + 1] - count28;
                  count16 = (count28 << 2) - count28;
               }
            } else {
               int count29 = this.recordNodeImplArray[this.count9].countArray2[++count15 + 1];
               count19 = (count29 << 2) - count29;
               int count30 = this.recordNodeImplArray[this.count9].countArray2[count15];
               count17 = this.recordNodeImplArray[this.count9].countArray2[count15 + 1] - count30;
               count16 = (count30 << 2) - count30;
            }
         }

         if (recordNodeSupport.count2 == 0 || (recordNodeSupport.count3 != 2 || recordNodeSupport.count8 != 0) && (recordNodeSupport.count3 != 2 || recordNodeSupport.count8 == 0 || index2 < 36)) {
            int count31 = this.recordNodeDelegateArray2[count13].countArray[count15];
            if (recordNodeSupport.count10 != 0) {
               count31 += countArray11[count15];
            }

            int count32 = count31 << recordNodeSupport.count12;
            factorMatrix2[count24][count23] = factorMatrix2[count24][count23] * factorArray11[count32];
         } else {
            int count33 = (count18 - count16) / count17;
            int count34 = (this.recordNodeDelegateArray2[count13].countArray2[count33][count15] << recordNodeSupport.count12) + (recordNodeSupport.countArray2[count33] << 2);
            factorMatrix2[count24][count23] = factorMatrix2[count24][count23] * factorArray11[count34];
         }

         count18++;
      }

      for (int index3 = this.countArray3[count13]; index3 < 576; index3++) {
         int count35 = index3 % 18;
         int count36 = (index3 - count35) / 18;
         if (count35 < 0) {
            count35 = 0;
         }

         if (count36 < 0) {
            count36 = 0;
         }

         factorMatrix2[count36][count35] = 0.0F;
      }
   }

   private static float[] getFloatArray() {
      float[] factorArray18 = new float[8192];

      for (int index = 0; index < 8192; index++) {
         factorArray18[index] = (float)Math.pow((double)index, 1.3333333333333333);
      }

      return factorArray18;
   }

   public void updateState3() {
      int count13 = this.aStarService.getCount25();
      this.isActive();

      for (int index = 0; index < count13; index++) {
         this.controllerSupport.applyIndex(this.depthFirstPipeline.computeCount3(8));
      }

      int count14 = this.controllerSupport.getCount4() >>> 3;
      int count15;
      if ((count15 = this.controllerSupport.getCount4() & 7) != 0) {
         this.controllerSupport.computeCount(8 - count15);
         count14++;
      }

      int count16 = this.count4 - count14 - this.runtimeComponent10.count;
      this.count4 += count13;
      if (count16 >= 0) {
         if (count14 > 4096) {
            this.count4 -= 4096;
            this.controllerSupport.applyIndexByIndex(4096);
         }

         while (count16 > 0) {
            this.controllerSupport.computeCount(8);
            count16--;
         }

         for (int index2 = 0; index2 < this.count3; index2++) {
            for (int index3 = 0; index3 < this.count6; index3++) {
               this.count5 = this.controllerSupport.getCount4();
               if (this.aStarService.getCount16() == 1) {
                  this.applyIndex2(index3, index2);
               } else {
                  this.applyIndexByIndex(index3, index2);
               }

               this.applyIndex4(index3, index2);
               this.applyFloatArray(this.factorArray[index3], index3, index2);
            }

            this.applyIndex(index2);
            if (this.count2 == 3 && this.count6 > 1) {
               this.updateState2();
            }

            for (int index4 = this.count7; index4 <= this.count8; index4++) {
               this.applyFloatArrayByFloatArray(this.factorArray2[index4], index4, index2);
               this.applyIndex3(index4, index2);
               this.applyIndex5(index4, index2);

               for (int byteValue = 18; byteValue < 576; byteValue += 36) {
                  for (int byteValue2 = 1; byteValue2 < 18; byteValue2 += 2) {
                     this.factorArray3[byteValue + byteValue2] = -this.factorArray3[byteValue + byteValue2];
                  }
               }

               if (index4 != 0 && this.count2 != 2) {
                  for (int index5 = 0; index5 < 18; index5++) {
                     int count17 = 0;

                     for (int byteValue3 = 0; byteValue3 < 576; byteValue3 += 18) {
                        this.factorArray7[count17] = this.factorArray3[byteValue3 + index5];
                        count17++;
                     }

                     this.sfdSerController2.applyFloatArray(this.factorArray7);
                     this.sfdSerController2.applyExtraCustom11(this.extraCustom);
                  }
               } else {
                  for (int index6 = 0; index6 < 18; index6++) {
                     int count18 = 0;

                     for (int byteValue4 = 0; byteValue4 < 576; byteValue4 += 18) {
                        this.factorArray6[count18] = this.factorArray3[byteValue4 + index6];
                        count18++;
                     }

                     this.sfdSerController.applyFloatArray(this.factorArray6);
                     this.sfdSerController.applyExtraCustom11(this.extraCustom);
                  }
               }
            }
         }

         this.count10++;
         this.extraCustom.applyIndex(1);
      }
   }

   private boolean isActive() {
      if (this.aStarService.getCount16() == 1) {
         this.runtimeComponent10.count = this.depthFirstPipeline.computeCount3(9);
         if (this.count6 == 1) {
            this.runtimeComponent10.count2 = this.depthFirstPipeline.computeCount3(5);
         } else {
            this.runtimeComponent10.count2 = this.depthFirstPipeline.computeCount3(3);
         }

         for (int index = 0; index < this.count6; index++) {
            this.runtimeComponent10.recordNodeAdapterArray[index].countArray[0] = this.depthFirstPipeline.computeCount3(1);
            this.runtimeComponent10.recordNodeAdapterArray[index].countArray[1] = this.depthFirstPipeline.computeCount3(1);
            this.runtimeComponent10.recordNodeAdapterArray[index].countArray[2] = this.depthFirstPipeline.computeCount3(1);
            this.runtimeComponent10.recordNodeAdapterArray[index].countArray[3] = this.depthFirstPipeline.computeCount3(1);
         }

         for (int index2 = 0; index2 < 2; index2++) {
            for (int index3 = 0; index3 < this.count6; index3++) {
               this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count = this.depthFirstPipeline.computeCount3(12);
               this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count6 = this.depthFirstPipeline.computeCount3(9);
               this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count11 = this.depthFirstPipeline.computeCount3(8);
               this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count9 = this.depthFirstPipeline.computeCount3(4);
               this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count2 = this.depthFirstPipeline.computeCount3(1);
               if (this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count2 != 0) {
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count3 = this.depthFirstPipeline.computeCount3(2);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count8 = this.depthFirstPipeline.computeCount3(1);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].countArray[0] = this.depthFirstPipeline.computeCount3(5);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].countArray[1] = this.depthFirstPipeline.computeCount3(5);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].countArray2[0] = this.depthFirstPipeline.computeCount3(3);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].countArray2[1] = this.depthFirstPipeline.computeCount3(3);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].countArray2[2] = this.depthFirstPipeline.computeCount3(3);
                  if (this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count3 == 0) {
                     return false;
                  }

                  if (this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count3 == 2
                     && this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count8 == 0) {
                     this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count4 = 8;
                  } else {
                     this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count4 = 7;
                  }

                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count5 = 20
                     - this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count4;
               } else {
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].countArray[0] = this.depthFirstPipeline.computeCount3(5);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].countArray[1] = this.depthFirstPipeline.computeCount3(5);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].countArray[2] = this.depthFirstPipeline.computeCount3(5);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count4 = this.depthFirstPipeline.computeCount3(4);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count5 = this.depthFirstPipeline.computeCount3(3);
                  this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count3 = 0;
               }

               this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count10 = this.depthFirstPipeline.computeCount3(1);
               this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count12 = this.depthFirstPipeline.computeCount3(1);
               this.runtimeComponent10.recordNodeAdapterArray[index3].recordNodeSupportArray[index2].count7 = this.depthFirstPipeline.computeCount3(1);
            }
         }
      } else {
         this.runtimeComponent10.count = this.depthFirstPipeline.computeCount3(8);
         if (this.count6 == 1) {
            this.runtimeComponent10.count2 = this.depthFirstPipeline.computeCount3(1);
         } else {
            this.runtimeComponent10.count2 = this.depthFirstPipeline.computeCount3(2);
         }

         for (int index4 = 0; index4 < this.count6; index4++) {
            this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count = this.depthFirstPipeline.computeCount3(12);
            this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count6 = this.depthFirstPipeline.computeCount3(9);
            this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count11 = this.depthFirstPipeline.computeCount3(8);
            this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count9 = this.depthFirstPipeline.computeCount3(9);
            this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count2 = this.depthFirstPipeline.computeCount3(1);
            if (this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count2 != 0) {
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count3 = this.depthFirstPipeline.computeCount3(2);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count8 = this.depthFirstPipeline.computeCount3(1);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].countArray[0] = this.depthFirstPipeline.computeCount3(5);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].countArray[1] = this.depthFirstPipeline.computeCount3(5);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].countArray2[0] = this.depthFirstPipeline.computeCount3(3);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].countArray2[1] = this.depthFirstPipeline.computeCount3(3);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].countArray2[2] = this.depthFirstPipeline.computeCount3(3);
               if (this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count3 == 0) {
                  return false;
               }

               if (this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count3 == 2
                  && this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count8 == 0) {
                  this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count4 = 8;
               } else {
                  this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count4 = 7;
                  this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count5 = 20
                     - this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count4;
               }
            } else {
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].countArray[0] = this.depthFirstPipeline.computeCount3(5);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].countArray[1] = this.depthFirstPipeline.computeCount3(5);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].countArray[2] = this.depthFirstPipeline.computeCount3(5);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count4 = this.depthFirstPipeline.computeCount3(4);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count5 = this.depthFirstPipeline.computeCount3(3);
               this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count3 = 0;
            }

            this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count12 = this.depthFirstPipeline.computeCount3(1);
            this.runtimeComponent10.recordNodeAdapterArray[index4].recordNodeSupportArray[0].count7 = this.depthFirstPipeline.computeCount3(1);
         }
      }

      return true;
   }

   private void applyIndex(int count13, int count14, int count15) {
      if (count13 == 0) {
         this.factorArray5[0][count15] = 1.0F;
         this.factorArray5[1][count15] = 1.0F;
      } else if ((count13 & 1) != 0) {
         this.factorArray5[0][count15] = factorArray13[count14][count13 + 1 >>> 1];
         this.factorArray5[1][count15] = 1.0F;
      } else {
         this.factorArray5[0][count15] = 1.0F;
         this.factorArray5[1][count15] = factorArray13[count14][count13 >>> 1];
      }
   }

   private void applyFloatArrayByFloatArray(float[][] factorMatrix, int count13, int count14) {
      RecordNodeSupport recordNodeSupport = this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14];
      float[][] factorMatrix2 = factorMatrix;
      if (recordNodeSupport.count2 != 0 && recordNodeSupport.count3 == 2) {
         for (int index = 0; index < 576; index++) {
            this.factorArray3[index] = 0.0F;
         }

         if (recordNodeSupport.count8 != 0) {
            for (int index2 = 0; index2 < 36; index2++) {
               int count15 = index2 % 18;
               int count16 = (index2 - count15) / 18;
               this.factorArray3[index2] = factorMatrix2[count16][count15];
            }

            for (int index3 = 3; index3 < 13; index3++) {
               int count17 = this.recordNodeImplArray[this.count9].countArray2[index3];
               int count18 = this.recordNodeImplArray[this.count9].countArray2[index3 + 1] - count17;
               int count19 = (count17 << 2) - count17;
               int count20 = 0;

               for (int byteValue = 0; count20 < count18; byteValue += 3) {
                  int count21 = count19 + count20;
                  int count22 = count19 + byteValue;
                  int count23 = count21 % 18;
                  int count24 = (count21 - count23) / 18;
                  this.factorArray3[count22] = factorMatrix2[count24][count23];
                  int count25 = count21 + count18;
                  count22++;
                  int count26 = count25 % 18;
                  int count27 = (count25 - count26) / 18;
                  this.factorArray3[count22] = factorMatrix2[count27][count26];
                  int count28 = count25 + count18;
                  count22++;
                  int count29 = count28 % 18;
                  int count30 = (count28 - count29) / 18;
                  this.factorArray3[count22] = factorMatrix2[count30][count29];
                  count20++;
               }
            }
         } else {
            for (int index4 = 0; index4 < 576; index4++) {
               int count31 = countArray12[this.count9][index4];
               int count32 = count31 % 18;
               int count33 = (count31 - count32) / 18;
               this.factorArray3[index4] = factorMatrix2[count33][count32];
            }
         }
      } else {
         for (int index5 = 0; index5 < 576; index5++) {
            int count34 = index5 % 18;
            int count35 = (index5 - count34) / 18;
            this.factorArray3[index5] = factorMatrix2[count35][count34];
         }
      }
   }

   public static int[] resolveIndexArray(int[] countArray14) {
      int count13 = 0;
      int[] countArray15 = new int[576];

      for (int index = 0; index < 13; index++) {
         int count14 = countArray14[index];
         int count15 = countArray14[index + 1];

         for (int index2 = 0; index2 < 3; index2++) {
            for (int index3 = count14; index3 < count15; index3++) {
               countArray15[3 * index3 + index2] = count13++;
            }
         }
      }

      return countArray15;
   }

   private void applyIndex(int count13) {
      if (this.count6 == 1) {
         for (int index = 0; index < 32; index++) {
            for (int byteValue = 0; byteValue < 18; byteValue += 3) {
               this.factorArray2[0][index][byteValue] = this.factorArray[0][index][byteValue];
               this.factorArray2[0][index][byteValue + 1] = this.factorArray[0][index][byteValue + 1];
               this.factorArray2[0][index][byteValue + 2] = this.factorArray[0][index][byteValue + 2];
            }
         }
      } else {
         RecordNodeSupport recordNodeSupport = this.runtimeComponent10.recordNodeAdapterArray[0].recordNodeSupportArray[count13];
         int count14 = this.aStarService.getCount15();
         boolean flag = this.aStarService.getCount17() == 1 && (count14 & 2) != 0;
         boolean flag2 = this.aStarService.getCount17() == 1 && (count14 & 1) != 0;
         boolean flag3 = this.aStarService.getCount16() == 0 || this.aStarService.getCount16() == 2;
         int count15 = recordNodeSupport.count9 & 1;

         for (int index2 = 0; index2 < 576; index2++) {
            this.countArray9[index2] = 7;
            this.factorArray8[index2] = 0.0F;
         }

         if (flag2) {
            if (recordNodeSupport.count2 != 0 && recordNodeSupport.count3 == 2) {
               if (recordNodeSupport.count8 != 0) {
                  int count16 = 0;

                  for (int index3 = 0; index3 < 3; index3++) {
                     int count17 = 2;

                     for (int index4 = 12; index4 >= 3; index4--) {
                        int count18 = this.recordNodeImplArray[this.count9].countArray2[index4];
                        int count19 = this.recordNodeImplArray[this.count9].countArray2[index4 + 1] - count18;

                        for (int index5 = (count18 << 2) - count18 + (index3 + 1) * count19 - 1; count19 > 0; index5--) {
                           if (this.factorArray[1][index5 / 18][index5 % 18] != 0.0F) {
                              count17 = index4;
                              index4 = -10;
                              count19 = -10;
                           }

                           count19--;
                        }
                     }

                     int count20 = count17 + 1;
                     if (count20 > count16) {
                        count16 = count20;
                     }

                     while (count20 < 12) {
                        int count21 = this.recordNodeImplArray[this.count9].countArray2[count20];
                        int count22 = this.recordNodeImplArray[this.count9].countArray2[count20 + 1] - count21;

                        for (int index6 = (count21 << 2) - count21 + index3 * count22; count22 > 0; count22--) {
                           this.countArray9[index6] = this.recordNodeDelegateArray2[1].countArray2[index3][count20];
                           if (this.countArray9[index6] != 7) {
                              if (flag3) {
                                 this.applyIndex(this.countArray9[index6], count15, index6);
                              } else {
                                 this.factorArray8[index6] = factorArray14[this.countArray9[index6]];
                              }
                           }

                           index6++;
                        }

                        count20++;
                     }

                     int count23 = this.recordNodeImplArray[this.count9].countArray2[10];
                     int count24 = this.recordNodeImplArray[this.count9].countArray2[11] - count23;
                     int count25 = (count23 << 2) - count23 + index3 * count24;
                     int count26 = this.recordNodeImplArray[this.count9].countArray2[11];
                     int count27 = this.recordNodeImplArray[this.count9].countArray2[12] - count26;

                     for (int index7 = (count26 << 2) - count26 + index3 * count27; count27 > 0; count27--) {
                        this.countArray9[index7] = this.countArray9[count25];
                        if (flag3) {
                           this.factorArray5[0][index7] = this.factorArray5[0][count25];
                           this.factorArray5[1][index7] = this.factorArray5[1][count25];
                        } else {
                           this.factorArray8[index7] = this.factorArray8[count25];
                        }

                        index7++;
                     }
                  }

                  if (count16 <= 3) {
                     int count28 = 2;
                     int count29 = 17;
                     int count30 = -1;

                     while (count28 >= 0) {
                        if (this.factorArray[1][count28][count29] != 0.0F) {
                           count30 = (count28 << 4) + (count28 << 1) + count29;
                           count28 = -1;
                        } else if (--count29 < 0) {
                           count28--;
                           count29 = 17;
                        }
                     }

                     int count31 = 0;

                     while (this.recordNodeImplArray[this.count9].countArray[count31] <= count30) {
                        count31++;
                     }

                     int count32 = count31;

                     for (int index8 = this.recordNodeImplArray[this.count9].countArray[count31]; count32 < 8; count32++) {
                        for (int index9 = this.recordNodeImplArray[this.count9].countArray[count32 + 1] - this.recordNodeImplArray[this.count9].countArray[count32];
                           index9 > 0;
                           index9--
                        ) {
                           this.countArray9[index8] = this.recordNodeDelegateArray2[1].countArray[count32];
                           if (this.countArray9[index8] != 7) {
                              if (flag3) {
                                 this.applyIndex(this.countArray9[index8], count15, index8);
                              } else {
                                 this.factorArray8[index8] = factorArray14[this.countArray9[index8]];
                              }
                           }

                           index8++;
                        }
                     }
                  }
               } else {
                  for (int index10 = 0; index10 < 3; index10++) {
                     int count33 = -1;

                     for (int index11 = 12; index11 >= 0; index11--) {
                        int count34 = this.recordNodeImplArray[this.count9].countArray2[index11];
                        int count35 = this.recordNodeImplArray[this.count9].countArray2[index11 + 1] - count34;

                        for (int index12 = (count34 << 2) - count34 + (index10 + 1) * count35 - 1; count35 > 0; index12--) {
                           if (this.factorArray[1][index12 / 18][index12 % 18] != 0.0F) {
                              count33 = index11;
                              index11 = -10;
                              count35 = -10;
                           }

                           count35--;
                        }
                     }

                     for (int index13 = count33 + 1; index13 < 12; index13++) {
                        int count36 = this.recordNodeImplArray[this.count9].countArray2[index13];
                        int count37 = this.recordNodeImplArray[this.count9].countArray2[index13 + 1] - count36;

                        for (int index14 = (count36 << 2) - count36 + index10 * count37; count37 > 0; count37--) {
                           this.countArray9[index14] = this.recordNodeDelegateArray2[1].countArray2[index10][index13];
                           if (this.countArray9[index14] != 7) {
                              if (flag3) {
                                 this.applyIndex(this.countArray9[index14], count15, index14);
                              } else {
                                 this.factorArray8[index14] = factorArray14[this.countArray9[index14]];
                              }
                           }

                           index14++;
                        }
                     }

                     int count38 = this.recordNodeImplArray[this.count9].countArray2[10];
                     int count39 = this.recordNodeImplArray[this.count9].countArray2[11];
                     int count40 = count39 - count38;
                     int count41 = (count38 << 2) - count38 + index10 * count40;
                     int count42 = this.recordNodeImplArray[this.count9].countArray2[12] - count39;

                     for (int index15 = (count39 << 2) - count39 + index10 * count42; count42 > 0; count42--) {
                        this.countArray9[index15] = this.countArray9[count41];
                        if (flag3) {
                           this.factorArray5[0][index15] = this.factorArray5[0][count41];
                           this.factorArray5[1][index15] = this.factorArray5[1][count41];
                        } else {
                           this.factorArray8[index15] = this.factorArray8[count41];
                        }

                        index15++;
                     }
                  }
               }
            } else {
               int count43 = 31;
               int count44 = 17;
               int count45 = 0;

               while (count43 >= 0) {
                  if (this.factorArray[1][count43][count44] != 0.0F) {
                     count45 = (count43 << 4) + (count43 << 1) + count44;
                     count43 = -1;
                  } else if (--count44 < 0) {
                     count43--;
                     count44 = 17;
                  }
               }

               int count46 = 0;

               while (this.recordNodeImplArray[this.count9].countArray[count46] <= count45) {
                  count46++;
               }

               int count47 = count46;

               int count48;
               for (count48 = this.recordNodeImplArray[this.count9].countArray[count46]; count47 < 21; count47++) {
                  for (int index16 = this.recordNodeImplArray[this.count9].countArray[count47 + 1] - this.recordNodeImplArray[this.count9].countArray[count47];
                     index16 > 0;
                     index16--
                  ) {
                     this.countArray9[count48] = this.recordNodeDelegateArray2[1].countArray[count47];
                     if (this.countArray9[count48] != 7) {
                        if (flag3) {
                           this.applyIndex(this.countArray9[count48], count15, count48);
                        } else {
                           this.factorArray8[count48] = factorArray14[this.countArray9[count48]];
                        }
                     }

                     count48++;
                  }
               }

               int count49 = this.recordNodeImplArray[this.count9].countArray[20];

               for (int index17 = 576 - this.recordNodeImplArray[this.count9].countArray[21]; index17 > 0 && count48 < 576; index17--) {
                  this.countArray9[count48] = this.countArray9[count49];
                  if (flag3) {
                     this.factorArray5[0][count48] = this.factorArray5[0][count49];
                     this.factorArray5[1][count48] = this.factorArray5[1][count49];
                  } else {
                     this.factorArray8[count48] = this.factorArray8[count49];
                  }

                  count48++;
               }
            }
         }

         int count50 = 0;

         for (int index18 = 0; index18 < 32; index18++) {
            for (int index19 = 0; index19 < 18; index19++) {
               if (this.countArray9[count50] == 7) {
                  if (flag) {
                     this.factorArray2[0][index18][index19] = (this.factorArray[0][index18][index19] + this.factorArray[1][index18][index19]) * 0.70710677F;
                     this.factorArray2[1][index18][index19] = (this.factorArray[0][index18][index19] - this.factorArray[1][index18][index19]) * 0.70710677F;
                  } else {
                     this.factorArray2[0][index18][index19] = this.factorArray[0][index18][index19];
                     this.factorArray2[1][index18][index19] = this.factorArray[1][index18][index19];
                  }
               } else if (flag2) {
                  if (flag3) {
                     this.factorArray2[0][index18][index19] = this.factorArray[0][index18][index19] * this.factorArray5[0][count50];
                     this.factorArray2[1][index18][index19] = this.factorArray[0][index18][index19] * this.factorArray5[1][count50];
                  } else {
                     this.factorArray2[1][index18][index19] = this.factorArray[0][index18][index19] / (1.0F + this.factorArray8[count50]);
                     this.factorArray2[0][index18][index19] = this.factorArray2[1][index18][index19] * this.factorArray8[count50];
                  }
               }

               count50++;
            }
         }
      }
   }

   public void updateState4() {
      this.count4 = 0;

      for (int index = 0; index < 2; index++) {
         for (int index2 = 0; index2 < 576; index2++) {
            this.factorArray4[index][index2] = 0.0F;
         }
      }

      this.controllerSupport = new ControllerSupport();
   }

   private void applyIndex5(int count13, int count14) {
      RecordNodeSupport recordNodeSupport = this.runtimeComponent10.recordNodeAdapterArray[count13].recordNodeSupportArray[count14];

      for (int byteValue = 0; byteValue < 576; byteValue += 18) {
         int count15 = recordNodeSupport.count2 != 0 && recordNodeSupport.count8 != 0 && byteValue < 36 ? 0 : recordNodeSupport.count3;
         float[] factorArray18 = this.factorArray3;

         for (int index = 0; index < 18; index++) {
            this.factorArray9[index] = factorArray18[index + byteValue];
         }

         this.applyFloatArray(this.factorArray9, this.factorArray10, count15);

         for (int index2 = 0; index2 < 18; index2++) {
            factorArray18[index2 + byteValue] = this.factorArray9[index2];
         }

         float[][] factorArray19 = this.factorArray4;
         factorArray18[0 + byteValue] = this.factorArray10[0] + factorArray19[count13][byteValue + 0];
         factorArray19[count13][byteValue + 0] = this.factorArray10[18];
         factorArray18[1 + byteValue] = this.factorArray10[1] + factorArray19[count13][byteValue + 1];
         factorArray19[count13][byteValue + 1] = this.factorArray10[19];
         factorArray18[2 + byteValue] = this.factorArray10[2] + factorArray19[count13][byteValue + 2];
         factorArray19[count13][byteValue + 2] = this.factorArray10[20];
         factorArray18[3 + byteValue] = this.factorArray10[3] + factorArray19[count13][byteValue + 3];
         factorArray19[count13][byteValue + 3] = this.factorArray10[21];
         factorArray18[4 + byteValue] = this.factorArray10[4] + factorArray19[count13][byteValue + 4];
         factorArray19[count13][byteValue + 4] = this.factorArray10[22];
         factorArray18[5 + byteValue] = this.factorArray10[5] + factorArray19[count13][byteValue + 5];
         factorArray19[count13][byteValue + 5] = this.factorArray10[23];
         factorArray18[6 + byteValue] = this.factorArray10[6] + factorArray19[count13][byteValue + 6];
         factorArray19[count13][byteValue + 6] = this.factorArray10[24];
         factorArray18[7 + byteValue] = this.factorArray10[7] + factorArray19[count13][byteValue + 7];
         factorArray19[count13][byteValue + 7] = this.factorArray10[25];
         factorArray18[8 + byteValue] = this.factorArray10[8] + factorArray19[count13][byteValue + 8];
         factorArray19[count13][byteValue + 8] = this.factorArray10[26];
         factorArray18[9 + byteValue] = this.factorArray10[9] + factorArray19[count13][byteValue + 9];
         factorArray19[count13][byteValue + 9] = this.factorArray10[27];
         factorArray18[10 + byteValue] = this.factorArray10[10] + factorArray19[count13][byteValue + 10];
         factorArray19[count13][byteValue + 10] = this.factorArray10[28];
         factorArray18[11 + byteValue] = this.factorArray10[11] + factorArray19[count13][byteValue + 11];
         factorArray19[count13][byteValue + 11] = this.factorArray10[29];
         factorArray18[12 + byteValue] = this.factorArray10[12] + factorArray19[count13][byteValue + 12];
         factorArray19[count13][byteValue + 12] = this.factorArray10[30];
         factorArray18[13 + byteValue] = this.factorArray10[13] + factorArray19[count13][byteValue + 13];
         factorArray19[count13][byteValue + 13] = this.factorArray10[31];
         factorArray18[14 + byteValue] = this.factorArray10[14] + factorArray19[count13][byteValue + 14];
         factorArray19[count13][byteValue + 14] = this.factorArray10[32];
         factorArray18[15 + byteValue] = this.factorArray10[15] + factorArray19[count13][byteValue + 15];
         factorArray19[count13][byteValue + 15] = this.factorArray10[33];
         factorArray18[16 + byteValue] = this.factorArray10[16] + factorArray19[count13][byteValue + 16];
         factorArray19[count13][byteValue + 16] = this.factorArray10[34];
         factorArray18[17 + byteValue] = this.factorArray10[17] + factorArray19[count13][byteValue + 17];
         factorArray19[count13][byteValue + 17] = this.factorArray10[35];
      }
   }

   @Override
   public void updateState() {
      this.updateState3();
   }

   public DepthFirstPipelineCallbackHandler(
      DepthFirstPipeline depthFirstPipeline2, AStarService aStarService2, SfdSerController sfdSerController3, SfdSerController sfdSerController4, ExtraCustom extraCustom2, int count13
   ) {
      HelperImpl.updateState();
      this.countArray2 = new int[580];
      this.factorArray = new float[2][32][18];
      this.factorArray2 = new float[2][32][18];
      this.factorArray3 = new float[576];
      this.factorArray4 = new float[2][576];
      this.factorArray5 = new float[2][576];
      this.countArray3 = new int[2];
      this.recordNodeDelegateArray = new RecordNodeDelegate[2];
      this.recordNodeDelegateArray[0] = new RecordNodeDelegate();
      this.recordNodeDelegateArray[1] = new RecordNodeDelegate();
      this.recordNodeDelegateArray2 = this.recordNodeDelegateArray;
      this.recordNodeImplArray = new RecordNodeImpl[9];
      int[] countArray14 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
      int[] countArray15 = new int[]{0, 4, 8, 12, 18, 24, 32, 42, 56, 74, 100, 132, 174, 192};
      int[] countArray16 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 114, 136, 162, 194, 232, 278, 330, 394, 464, 540, 576};
      int[] countArray17 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 136, 180, 192};
      int[] countArray18 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
      int[] countArray19 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192};
      int[] countArray20 = new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 44, 52, 62, 74, 90, 110, 134, 162, 196, 238, 288, 342, 418, 576};
      int[] countArray21 = new int[]{0, 4, 8, 12, 16, 22, 30, 40, 52, 66, 84, 106, 136, 192};
      int[] countArray22 = new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 42, 50, 60, 72, 88, 106, 128, 156, 190, 230, 276, 330, 384, 576};
      int[] countArray23 = new int[]{0, 4, 8, 12, 16, 22, 28, 38, 50, 64, 80, 100, 126, 192};
      int[] countArray24 = new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 44, 54, 66, 82, 102, 126, 156, 194, 240, 296, 364, 448, 550, 576};
      int[] countArray25 = new int[]{0, 4, 8, 12, 16, 22, 30, 42, 58, 78, 104, 138, 180, 192};
      int[] countArray26 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
      int[] countArray27 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192};
      int[] countArray28 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
      int[] countArray29 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192};
      int[] countArray30 = new int[]{0, 12, 24, 36, 48, 60, 72, 88, 108, 132, 160, 192, 232, 280, 336, 400, 476, 566, 568, 570, 572, 574, 576};
      int[] countArray31 = new int[]{0, 8, 16, 24, 36, 52, 72, 96, 124, 160, 162, 164, 166, 192};
      this.recordNodeImplArray[0] = new RecordNodeImpl(countArray14, countArray15);
      this.recordNodeImplArray[1] = new RecordNodeImpl(countArray16, countArray17);
      this.recordNodeImplArray[2] = new RecordNodeImpl(countArray18, countArray19);
      this.recordNodeImplArray[3] = new RecordNodeImpl(countArray20, countArray21);
      this.recordNodeImplArray[4] = new RecordNodeImpl(countArray22, countArray23);
      this.recordNodeImplArray[5] = new RecordNodeImpl(countArray24, countArray25);
      this.recordNodeImplArray[6] = new RecordNodeImpl(countArray26, countArray27);
      this.recordNodeImplArray[7] = new RecordNodeImpl(countArray28, countArray29);
      this.recordNodeImplArray[8] = new RecordNodeImpl(countArray30, countArray31);
      if (countArray12 == null) {
         countArray12 = new int[9][];

         for (int index = 0; index < 9; index++) {
            countArray12[index] = resolveIndexArray(this.recordNodeImplArray[index].countArray2);
         }
      }

      int[] countArray32 = new int[]{0, 6, 11, 16, 21};
      int[] countArray33 = new int[]{0, 6, 12};
      this.recordNode = new RecordNode(this, countArray32, countArray33);
      this.countArray = new int[54];
      this.depthFirstPipeline = depthFirstPipeline2;
      this.aStarService = aStarService2;
      this.sfdSerController = sfdSerController3;
      this.sfdSerController2 = sfdSerController4;
      this.extraCustom = extraCustom2;
      this.count2 = count13;
      this.count4 = 0;
      this.count6 = this.aStarService.getCount17() == 3 ? 1 : 2;
      this.count3 = this.aStarService.getCount16() == 1 ? 2 : 1;
      this.count9 = this.aStarService.getCount18() + (this.aStarService.getCount16() == 1 ? 3 : (this.aStarService.getCount16() == 2 ? 6 : 0));
      if (this.count6 == 2) {
         switch (this.count2) {
            case 0:
            default:
               this.count7 = 0;
               this.count8 = 1;
               break;
            case 1:
            case 3:
               this.count7 = this.count8 = 0;
               break;
            case 2:
               this.count7 = this.count8 = 1;
         }
      } else {
         this.count7 = this.count8 = 0;
      }

      for (int index2 = 0; index2 < 2; index2++) {
         for (int index3 = 0; index3 < 576; index3++) {
            this.factorArray4[index2][index3] = 0.0F;
         }
      }

      this.countArray3[0] = this.countArray3[1] = 576;
      this.controllerSupport = new ControllerSupport();
      this.runtimeComponent10 = new RuntimeComponent10();
   }
}
