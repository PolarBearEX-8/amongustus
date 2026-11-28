package javazoom.jl.decoder;

public final class LayerIIIDecoder implements FrameDecoder {
   public final double d43 = 1.3333333333333333;
   public int[] scalefac_buffer;
   private int checkSum = 0;
   private int[] is_1d;
   private float[][][] ro;
   private float[][][] lr;
   private float[] out_1d;
   private float[][] prevblck;
   private float[][] k;
   private int[] nonzero;
   private Bitstream stream;
   private Header header;
   private SynthesisFilter filter1;
   private SynthesisFilter filter2;
   private Obuffer buffer;
   private int which_channels;
   private BitReserve br;
   private LayerIIISideInfo si;
   private LayerIIIScaleFactors[] scalefacArr;
   private LayerIIIScaleFactors[] scalefac;
   private int max_gr;
   private int frame_start;
   private int part2_start;
   private int channels;
   private int first_channel;
   private int last_channel;
   private int sfreq;
   private float[] samples1 = new float[32];
   private float[] samples2 = new float[32];
   private final int[] new_slen = new int[4];
   public int[] x = new int[]{0};
   public int[] y = new int[]{0};
   public int[] v = new int[]{0};
   public int[] w = new int[]{0};
   public int[] is_pos = new int[576];
   public float[] is_ratio = new float[576];
   public float[] tsOutCopy = new float[18];
   public float[] rawout = new float[36];
   private int frameCount = 0;
   private static final int SSLIMIT = 18;
   private static final int SBLIMIT = 32;
   private static final int[][] slen = new int[][]{{0, 0, 0, 0, 3, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4}, {0, 1, 2, 3, 0, 1, 2, 3, 1, 2, 3, 1, 2, 3, 2, 3}};
   public static final int[] pretab = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 3, 2, 0};
   private LayerIIIScaleFactorBandIndex[] sfBandIndex;
   public static final float[] two_to_negative_half_pow = new float[]{
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
   public static final float[] t_43 = create_t_43();
   public static final float[][] io = new float[][]{
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
   public static final float[] TAN12 = new float[]{
      0.0F,
      0.2679492F,
      0.57735026F,
      1.0F,
      1.7320508F,
      3.732051F,
      1.0E11F,
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
   private static int[][] reorder_table;
   private static final float[] cs = new float[]{0.8574929F, 0.881742F, 0.94962865F, 0.9833146F, 0.9955178F, 0.9991606F, 0.9998992F, 0.99999315F};
   private static final float[] ca = new float[]{
      -0.51449573F, -0.47173196F, -0.31337744F, -0.1819132F, -0.09457419F, -0.040965583F, -0.014198569F, -0.0036999746F
   };
   public static final float[][] win = new float[][]{
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
   public LayerIIIScaleFactorTable sftable;
   public static final int[][][] nr_of_sfb_block = new int[][][]{
      {{6, 5, 5, 5}, {9, 9, 9, 9}, {6, 9, 9, 9}},
      {{6, 5, 7, 3}, {9, 9, 12, 6}, {6, 9, 12, 6}},
      {{11, 10, 0, 0}, {18, 18, 0, 0}, {15, 18, 0, 0}},
      {{7, 7, 7, 0}, {12, 12, 12, 0}, {6, 15, 12, 0}},
      {{6, 6, 6, 3}, {12, 9, 9, 6}, {6, 12, 9, 6}},
      {{8, 8, 5, 0}, {15, 12, 9, 0}, {6, 18, 9, 0}}
   };

   private void hybrid(int n, int n2) {
      LayerIIIGranuleInfo layerIIIGranuleInfo = this.si.ch[n].gr[n2];

      for (int i = 0; i < 576; i += 18) {
         int n3 = layerIIIGranuleInfo.window_switching_flag != 0 && layerIIIGranuleInfo.mixed_block_flag != 0 && i < 36 ? 0 : layerIIIGranuleInfo.block_type;
         float[] out1d = this.out_1d;

         for (int j = 0; j < 18; j++) {
            this.tsOutCopy[j] = out1d[j + i];
         }

         this.inv_mdct(this.tsOutCopy, this.rawout, n3);

         for (int m = 0; m < 18; m++) {
            out1d[m + i] = this.tsOutCopy[m];
         }

         float[][] prevblck2 = this.prevblck;
         out1d[0 + i] = this.rawout[0] + prevblck2[n][i + 0];
         prevblck2[n][i + 0] = this.rawout[18];
         out1d[1 + i] = this.rawout[1] + prevblck2[n][i + 1];
         prevblck2[n][i + 1] = this.rawout[19];
         out1d[2 + i] = this.rawout[2] + prevblck2[n][i + 2];
         prevblck2[n][i + 2] = this.rawout[20];
         out1d[3 + i] = this.rawout[3] + prevblck2[n][i + 3];
         prevblck2[n][i + 3] = this.rawout[21];
         out1d[4 + i] = this.rawout[4] + prevblck2[n][i + 4];
         prevblck2[n][i + 4] = this.rawout[22];
         out1d[5 + i] = this.rawout[5] + prevblck2[n][i + 5];
         prevblck2[n][i + 5] = this.rawout[23];
         out1d[6 + i] = this.rawout[6] + prevblck2[n][i + 6];
         prevblck2[n][i + 6] = this.rawout[24];
         out1d[7 + i] = this.rawout[7] + prevblck2[n][i + 7];
         prevblck2[n][i + 7] = this.rawout[25];
         out1d[8 + i] = this.rawout[8] + prevblck2[n][i + 8];
         prevblck2[n][i + 8] = this.rawout[26];
         out1d[9 + i] = this.rawout[9] + prevblck2[n][i + 9];
         prevblck2[n][i + 9] = this.rawout[27];
         out1d[10 + i] = this.rawout[10] + prevblck2[n][i + 10];
         prevblck2[n][i + 10] = this.rawout[28];
         out1d[11 + i] = this.rawout[11] + prevblck2[n][i + 11];
         prevblck2[n][i + 11] = this.rawout[29];
         out1d[12 + i] = this.rawout[12] + prevblck2[n][i + 12];
         prevblck2[n][i + 12] = this.rawout[30];
         out1d[13 + i] = this.rawout[13] + prevblck2[n][i + 13];
         prevblck2[n][i + 13] = this.rawout[31];
         out1d[14 + i] = this.rawout[14] + prevblck2[n][i + 14];
         prevblck2[n][i + 14] = this.rawout[32];
         out1d[15 + i] = this.rawout[15] + prevblck2[n][i + 15];
         prevblck2[n][i + 15] = this.rawout[33];
         out1d[16 + i] = this.rawout[16] + prevblck2[n][i + 16];
         prevblck2[n][i + 16] = this.rawout[34];
         out1d[17 + i] = this.rawout[17] + prevblck2[n][i + 17];
         prevblck2[n][i + 17] = this.rawout[35];
      }
   }

   private void antialias(int n, int n2) {
      LayerIIIGranuleInfo layerIIIGranuleInfo = this.si.ch[n].gr[n2];
      if (layerIIIGranuleInfo.window_switching_flag == 0 || layerIIIGranuleInfo.block_type != 2 || layerIIIGranuleInfo.mixed_block_flag != 0) {
         short s;
         if (layerIIIGranuleInfo.window_switching_flag != 0 && layerIIIGranuleInfo.mixed_block_flag != 0 && layerIIIGranuleInfo.block_type == 2) {
            s = 18;
         } else {
            s = 558;
         }

         for (int i = 0; i < s; i += 18) {
            for (int j = 0; j < 8; j++) {
               int n3 = i + 17 - j;
               int n4 = i + 18 + j;
               float f = this.out_1d[n3];
               float f2 = this.out_1d[n4];
               this.out_1d[n3] = f * cs[j] - f2 * ca[j];
               this.out_1d[n4] = f2 * cs[j] + f * ca[j];
            }
         }
      }
   }

   public LayerIIIDecoder(Bitstream bitstream, Header header2, SynthesisFilter synthesisFilter, SynthesisFilter synthesisFilter2, Obuffer obuffer, int n) {
      HuffCodeTab.inithuff();
      this.is_1d = new int[580];
      this.ro = new float[2][32][18];
      this.lr = new float[2][32][18];
      this.out_1d = new float[576];
      this.prevblck = new float[2][576];
      this.k = new float[2][576];
      this.nonzero = new int[2];
      this.scalefacArr = new LayerIIIScaleFactors[2];
      this.scalefacArr[0] = new LayerIIIScaleFactors();
      this.scalefacArr[1] = new LayerIIIScaleFactors();
      this.scalefac = this.scalefacArr;
      this.sfBandIndex = new LayerIIIScaleFactorBandIndex[9];
      int[] ints = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
      int[] ints2 = new int[]{0, 4, 8, 12, 18, 24, 32, 42, 56, 74, 100, 132, 174, 192};
      int[] ints3 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 114, 136, 162, 194, 232, 278, 330, 394, 464, 540, 576};
      int[] ints4 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 136, 180, 192};
      int[] ints5 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
      int[] ints6 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192};
      int[] ints7 = new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 44, 52, 62, 74, 90, 110, 134, 162, 196, 238, 288, 342, 418, 576};
      int[] ints8 = new int[]{0, 4, 8, 12, 16, 22, 30, 40, 52, 66, 84, 106, 136, 192};
      int[] ints9 = new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 42, 50, 60, 72, 88, 106, 128, 156, 190, 230, 276, 330, 384, 576};
      int[] ints10 = new int[]{0, 4, 8, 12, 16, 22, 28, 38, 50, 64, 80, 100, 126, 192};
      int[] ints11 = new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 44, 54, 66, 82, 102, 126, 156, 194, 240, 296, 364, 448, 550, 576};
      int[] ints12 = new int[]{0, 4, 8, 12, 16, 22, 30, 42, 58, 78, 104, 138, 180, 192};
      int[] ints13 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
      int[] ints14 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192};
      int[] ints15 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
      int[] ints16 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192};
      int[] ints17 = new int[]{0, 12, 24, 36, 48, 60, 72, 88, 108, 132, 160, 192, 232, 280, 336, 400, 476, 566, 568, 570, 572, 574, 576};
      int[] ints18 = new int[]{0, 8, 16, 24, 36, 52, 72, 96, 124, 160, 162, 164, 166, 192};
      this.sfBandIndex[0] = new LayerIIIScaleFactorBandIndex(ints, ints2);
      this.sfBandIndex[1] = new LayerIIIScaleFactorBandIndex(ints3, ints4);
      this.sfBandIndex[2] = new LayerIIIScaleFactorBandIndex(ints5, ints6);
      this.sfBandIndex[3] = new LayerIIIScaleFactorBandIndex(ints7, ints8);
      this.sfBandIndex[4] = new LayerIIIScaleFactorBandIndex(ints9, ints10);
      this.sfBandIndex[5] = new LayerIIIScaleFactorBandIndex(ints11, ints12);
      this.sfBandIndex[6] = new LayerIIIScaleFactorBandIndex(ints13, ints14);
      this.sfBandIndex[7] = new LayerIIIScaleFactorBandIndex(ints15, ints16);
      this.sfBandIndex[8] = new LayerIIIScaleFactorBandIndex(ints17, ints18);
      if (reorder_table == null) {
         reorder_table = new int[9][];

         for (int i = 0; i < 9; i++) {
            reorder_table[i] = reorder_table(this.sfBandIndex[i].s);
         }
      }

      int[] ints19 = new int[]{0, 6, 11, 16, 21};
      int[] ints20 = new int[]{0, 6, 12};
      this.sftable = new LayerIIIScaleFactorTable(this, ints19, ints20);
      this.scalefac_buffer = new int[54];
      this.stream = bitstream;
      this.header = header2;
      this.filter1 = synthesisFilter;
      this.filter2 = synthesisFilter2;
      this.buffer = obuffer;
      this.which_channels = n;
      this.frame_start = 0;
      this.channels = this.header.getHMode() == 3 ? 1 : 2;
      this.max_gr = this.header.getHVersion() == 1 ? 2 : 1;
      this.sfreq = this.header.getHSampleFrequency() + (this.header.getHVersion() == 1 ? 3 : (this.header.getHVersion() == 2 ? 6 : 0));
      if (this.channels == 2) {
         switch (this.which_channels) {
            case 0:
            default:
               this.first_channel = 0;
               this.last_channel = 1;
               break;
            case 1:
            case 3:
               this.first_channel = this.last_channel = 0;
               break;
            case 2:
               this.first_channel = this.last_channel = 1;
         }
      } else {
         this.first_channel = this.last_channel = 0;
      }

      for (int j = 0; j < 2; j++) {
         for (int m = 0; m < 576; m++) {
            this.prevblck[j][m] = 0.0F;
         }
      }

      this.nonzero[0] = this.nonzero[1] = 576;
      this.br = new BitReserve();
      this.si = new LayerIIISideInfo();
   }

   private void dequantize_sample(float[][] floatsArr, int n, int n2) {
      LayerIIIGranuleInfo layerIIIGranuleInfo = this.si.ch[n].gr[n2];
      int n3 = 0;
      int n4 = 0;
      int n5 = 0;
      int n6 = 0;
      float[][] floatsArr2 = floatsArr;
      int n7;
      if (layerIIIGranuleInfo.window_switching_flag == 0 || layerIIIGranuleInfo.block_type != 2) {
         n7 = this.sfBandIndex[this.sfreq].l[1];
      } else if (layerIIIGranuleInfo.mixed_block_flag != 0) {
         n7 = this.sfBandIndex[this.sfreq].l[1];
      } else {
         n5 = this.sfBandIndex[this.sfreq].s[1];
         n7 = (n5 << 2) - n5;
         n4 = 0;
      }

      float f = (float)Math.pow(2.0, 0.25 * ((double)layerIIIGranuleInfo.global_gain - 210.0));

      for (int i = 0; i < this.nonzero[n]; i++) {
         int n8 = i % 18;
         int n9 = (i - n8) / 18;
         if (this.is_1d[i] == 0) {
            floatsArr2[n9][n8] = 0.0F;
         } else {
            int n10 = this.is_1d[i];
            if (n10 < t_43.length) {
               if (this.is_1d[i] > 0) {
                  floatsArr2[n9][n8] = f * t_43[n10];
               } else if (-n10 < t_43.length) {
                  floatsArr2[n9][n8] = -f * t_43[-n10];
               } else {
                  floatsArr2[n9][n8] = -f * (float)Math.pow((double)(-n10), 1.3333333333333333);
               }
            } else if (this.is_1d[i] > 0) {
               floatsArr2[n9][n8] = f * (float)Math.pow((double)n10, 1.3333333333333333);
            } else {
               floatsArr2[n9][n8] = -f * (float)Math.pow((double)(-n10), 1.3333333333333333);
            }
         }
      }

      for (int j = 0; j < this.nonzero[n]; j++) {
         int n11 = j % 18;
         int n12 = (j - n11) / 18;
         if (n6 == n7) {
            if (layerIIIGranuleInfo.window_switching_flag == 0 || layerIIIGranuleInfo.block_type != 2) {
               n7 = this.sfBandIndex[this.sfreq].l[++n3 + 1];
            } else if (layerIIIGranuleInfo.mixed_block_flag != 0) {
               if (n6 == this.sfBandIndex[this.sfreq].l[8]) {
                  n7 = this.sfBandIndex[this.sfreq].s[4];
                  n7 = (n7 << 2) - n7;
                  n3 = 3;
                  n5 = this.sfBandIndex[this.sfreq].s[4] - this.sfBandIndex[this.sfreq].s[3];
                  n4 = this.sfBandIndex[this.sfreq].s[3];
                  n4 = (n4 << 2) - n4;
               } else if (n6 < this.sfBandIndex[this.sfreq].l[8]) {
                  n7 = this.sfBandIndex[this.sfreq].l[++n3 + 1];
               } else {
                  n7 = this.sfBandIndex[this.sfreq].s[++n3 + 1];
                  n7 = (n7 << 2) - n7;
                  n4 = this.sfBandIndex[this.sfreq].s[n3];
                  n5 = this.sfBandIndex[this.sfreq].s[n3 + 1] - n4;
                  n4 = (n4 << 2) - n4;
               }
            } else {
               n7 = this.sfBandIndex[this.sfreq].s[++n3 + 1];
               n7 = (n7 << 2) - n7;
               n4 = this.sfBandIndex[this.sfreq].s[n3];
               n5 = this.sfBandIndex[this.sfreq].s[n3 + 1] - n4;
               n4 = (n4 << 2) - n4;
            }
         }

         if (layerIIIGranuleInfo.window_switching_flag == 0 || (layerIIIGranuleInfo.block_type != 2 || layerIIIGranuleInfo.mixed_block_flag != 0) && (layerIIIGranuleInfo.block_type != 2 || layerIIIGranuleInfo.mixed_block_flag == 0 || j < 36)) {
            int n13 = this.scalefac[n].l[n3];
            if (layerIIIGranuleInfo.preflag != 0) {
               n13 += pretab[n3];
            }

            n13 <<= layerIIIGranuleInfo.scalefac_scale;
            floatsArr2[n12][n11] = floatsArr2[n12][n11] * two_to_negative_half_pow[n13];
         } else {
            int n14 = (n6 - n4) / n5;
            int n15 = this.scalefac[n].s[n14][n3] << layerIIIGranuleInfo.scalefac_scale;
            n15 += layerIIIGranuleInfo.subblock_gain[n14] << 2;
            floatsArr2[n12][n11] = floatsArr2[n12][n11] * two_to_negative_half_pow[n15];
         }

         n6++;
      }

      for (int m = this.nonzero[n]; m < 576; m++) {
         int n16 = m % 18;
         int n17 = (m - n16) / 18;
         if (n16 < 0) {
            n16 = 0;
         }

         if (n17 < 0) {
            n17 = 0;
         }

         floatsArr2[n17][n16] = 0.0F;
      }
   }

   private void get_LSF_scale_data(int n, int n2) {
      int hModeExtension = this.header.getHModeExtension();
      byte b = 0;
      LayerIIIGranuleInfo layerIIIGranuleInfo = this.si.ch[n].gr[n2];
      int scalefacCompress = layerIIIGranuleInfo.scalefac_compress;
      byte b2;
      if (layerIIIGranuleInfo.block_type == 2) {
         if (layerIIIGranuleInfo.mixed_block_flag == 0) {
            b2 = 1;
         } else if (layerIIIGranuleInfo.mixed_block_flag == 1) {
            b2 = 2;
         } else {
            b2 = 0;
         }
      } else {
         b2 = 0;
      }

      if (hModeExtension != 1 && hModeExtension != 3 || n != 1) {
         if (scalefacCompress < 400) {
            this.new_slen[0] = (scalefacCompress >>> 4) / 5;
            this.new_slen[1] = (scalefacCompress >>> 4) % 5;
            this.new_slen[2] = (scalefacCompress & 15) >>> 2;
            this.new_slen[3] = scalefacCompress & 3;
            this.si.ch[n].gr[n2].preflag = 0;
            b = 0;
         } else if (scalefacCompress < 500) {
            this.new_slen[0] = (scalefacCompress - 400 >>> 2) / 5;
            this.new_slen[1] = (scalefacCompress - 400 >>> 2) % 5;
            this.new_slen[2] = scalefacCompress - 400 & 3;
            this.new_slen[3] = 0;
            this.si.ch[n].gr[n2].preflag = 0;
            b = 1;
         } else if (scalefacCompress < 512) {
            this.new_slen[0] = (scalefacCompress - 500) / 3;
            this.new_slen[1] = (scalefacCompress - 500) % 3;
            this.new_slen[2] = 0;
            this.new_slen[3] = 0;
            this.si.ch[n].gr[n2].preflag = 1;
            b = 2;
         }
      }

      if ((hModeExtension == 1 || hModeExtension == 3) && n == 1) {
         int n3 = scalefacCompress >>> 1;
         if (n3 < 180) {
            this.new_slen[0] = n3 / 36;
            this.new_slen[1] = n3 % 36 / 6;
            this.new_slen[2] = n3 % 36 % 6;
            this.new_slen[3] = 0;
            this.si.ch[n].gr[n2].preflag = 0;
            b = 3;
         } else if (n3 < 244) {
            this.new_slen[0] = (n3 - 180 & 63) >>> 4;
            this.new_slen[1] = (n3 - 180 & 15) >>> 2;
            this.new_slen[2] = n3 - 180 & 3;
            this.new_slen[3] = 0;
            this.si.ch[n].gr[n2].preflag = 0;
            b = 4;
         } else if (n3 < 255) {
            this.new_slen[0] = (n3 - 244) / 3;
            this.new_slen[1] = (n3 - 244) % 3;
            this.new_slen[2] = 0;
            this.new_slen[3] = 0;
            this.si.ch[n].gr[n2].preflag = 0;
            b = 5;
         }
      }

      for (int i = 0; i < 45; i++) {
         this.scalefac_buffer[i] = 0;
      }

      int n4 = 0;

      for (int j = 0; j < 4; j++) {
         for (int m = 0; m < nr_of_sfb_block[b][b2][j]; m++) {
            this.scalefac_buffer[n4] = this.new_slen[j] == 0 ? 0 : this.br.hgetbits(this.new_slen[j]);
            n4++;
         }
      }
   }

   private void huffman_decode(int n, int n2) {
      this.x[0] = 0;
      this.y[0] = 0;
      this.v[0] = 0;
      this.w[0] = 0;
      int n3 = this.part2_start + this.si.ch[n].gr[n2].part2_3_length;
      int n4;
      int n5;
      if (this.si.ch[n].gr[n2].window_switching_flag != 0 && this.si.ch[n].gr[n2].block_type == 2) {
         n4 = this.sfreq == 8 ? 72 : 36;
         n5 = 576;
      } else {
         int n6 = this.si.ch[n].gr[n2].region0_count + 1;
         int n7 = n6 + this.si.ch[n].gr[n2].region1_count + 1;
         if (n7 > this.sfBandIndex[this.sfreq].l.length - 1) {
            n7 = this.sfBandIndex[this.sfreq].l.length - 1;
         }

         n4 = this.sfBandIndex[this.sfreq].l[n6];
         n5 = this.sfBandIndex[this.sfreq].l[n7];
      }

      int n8 = 0;

      for (int i = 0; i < this.si.ch[n].gr[n2].big_values << 1; i += 2) {
         HuffCodeTab huffCodeTab;
         if (i < n4) {
            huffCodeTab = HuffCodeTab.ht[this.si.ch[n].gr[n2].table_select[0]];
         } else if (i < n5) {
            huffCodeTab = HuffCodeTab.ht[this.si.ch[n].gr[n2].table_select[1]];
         } else {
            huffCodeTab = HuffCodeTab.ht[this.si.ch[n].gr[n2].table_select[2]];
         }

         HuffCodeTab.huffman_decoder(huffCodeTab, this.x, this.y, this.v, this.w, this.br);
         this.is_1d[n8++] = this.x[0];
         this.is_1d[n8++] = this.y[0];
         this.checkSum = this.checkSum + this.x[0] + this.y[0];
      }

      HuffCodeTab huffCodeTab2 = HuffCodeTab.ht[this.si.ch[n].gr[n2].count1table_select + 32];

      int n9;
      for (n9 = this.br.getTotbit(); n9 < n3 && n8 < 576; n9 = this.br.getTotbit()) {
         HuffCodeTab.huffman_decoder(huffCodeTab2, this.x, this.y, this.v, this.w, this.br);
         this.is_1d[n8++] = this.v[0];
         this.is_1d[n8++] = this.w[0];
         this.is_1d[n8++] = this.x[0];
         this.is_1d[n8++] = this.y[0];
         this.checkSum = this.checkSum + this.v[0] + this.w[0] + this.x[0] + this.y[0];
      }

      if (n9 > n3) {
         this.br.rewindNbits(n9 - n3);
         n8 -= 4;
      }

      n9 = this.br.getTotbit();
      if (n9 < n3) {
         this.br.hgetbits(n3 - n9);
      }

      if (n8 < 576) {
         this.nonzero[n] = n8;
      } else {
         this.nonzero[n] = 576;
      }

      if (n8 < 0) {
         n8 = 0;
      }

      while (n8 < 576) {
         this.is_1d[n8] = 0;
         n8++;
      }
   }

   public static int[] reorder_table(int[] ints) {
      int n = 0;
      int[] ints2 = new int[576];

      for (int i = 0; i < 13; i++) {
         int n2 = ints[i];
         int n3 = ints[i + 1];

         for (int j = 0; j < 3; j++) {
            for (int m = n2; m < n3; m++) {
               ints2[3 * m + j] = n++;
            }
         }
      }

      return ints2;
   }

   public void decode() {
      int nSlots = this.header.getNSlots();
      this.get_side_info();

      for (int i = 0; i < nSlots; i++) {
         this.br.hputbuf(this.stream.get_bits(8));
      }

      int n = this.br.getTotbit() >>> 3;
      int n2;
      if ((n2 = this.br.getTotbit() & 7) != 0) {
         this.br.hgetbits(8 - n2);
         n++;
      }

      int n3 = this.frame_start - n - this.si.main_data_begin;
      this.frame_start += nSlots;
      if (n3 >= 0) {
         if (n > 4096) {
            this.frame_start -= 4096;
            this.br.rewindNbytes(4096);
         }

         while (n3 > 0) {
            this.br.hgetbits(8);
            n3--;
         }

         for (int j = 0; j < this.max_gr; j++) {
            for (int m = 0; m < this.channels; m++) {
               this.part2_start = this.br.getTotbit();
               if (this.header.getHVersion() == 1) {
                  this.get_scale_factors(m, j);
               } else {
                  this.get_LSF_scale_factors(m, j);
               }

               this.huffman_decode(m, j);
               this.dequantize_sample(this.ro[m], m, j);
            }

            this.stereo(j);
            if (this.which_channels == 3 && this.channels > 1) {
               this.do_downmix();
            }

            for (int idx = this.first_channel; idx <= this.last_channel; idx++) {
               this.reorder(this.lr[idx], idx, j);
               this.antialias(idx, j);
               this.hybrid(idx, j);

               for (int index = 18; index < 576; index += 36) {
                  for (int i2 = 1; i2 < 18; i2 += 2) {
                     this.out_1d[index + i2] = -this.out_1d[index + i2];
                  }
               }

               if (idx != 0 && this.which_channels != 2) {
                  for (int i3 = 0; i3 < 18; i3++) {
                     int n4 = 0;

                     for (int i4 = 0; i4 < 576; i4 += 18) {
                        this.samples2[n4] = this.out_1d[i4 + i3];
                        n4++;
                     }

                     this.filter2.input_samples(this.samples2);
                     this.filter2.calculate_pcm_samples(this.buffer);
                  }
               } else {
                  for (int i5 = 0; i5 < 18; i5++) {
                     int n5 = 0;

                     for (int i6 = 0; i6 < 576; i6 += 18) {
                        this.samples1[n5] = this.out_1d[i6 + i5];
                        n5++;
                     }

                     this.filter1.input_samples(this.samples1);
                     this.filter1.calculate_pcm_samples(this.buffer);
                  }
               }
            }
         }

         this.frameCount++;
         this.buffer.write_buffer(1);
      }
   }

   private void i_stereo_k_values(int n, int n2, int n3) {
      if (n == 0) {
         this.k[0][n3] = 1.0F;
         this.k[1][n3] = 1.0F;
      } else if ((n & 1) != 0) {
         this.k[0][n3] = io[n2][n + 1 >>> 1];
         this.k[1][n3] = 1.0F;
      } else {
         this.k[0][n3] = 1.0F;
         this.k[1][n3] = io[n2][n >>> 1];
      }
   }

   private void stereo(int n) {
      if (this.channels == 1) {
         for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 18; j += 3) {
               this.lr[0][i][j] = this.ro[0][i][j];
               this.lr[0][i][j + 1] = this.ro[0][i][j + 1];
               this.lr[0][i][j + 2] = this.ro[0][i][j + 2];
            }
         }
      } else {
         LayerIIIGranuleInfo layerIIIGranuleInfo = this.si.ch[0].gr[n];
         int hModeExtension = this.header.getHModeExtension();
         boolean flag = this.header.getHMode() == 1 && (hModeExtension & 2) != 0;
         boolean flag2 = this.header.getHMode() == 1 && (hModeExtension & 1) != 0;
         boolean flag3 = this.header.getHVersion() == 0 || this.header.getHVersion() == 2;
         int n2 = layerIIIGranuleInfo.scalefac_compress & 1;

         for (int m = 0; m < 576; m++) {
            this.is_pos[m] = 7;
            this.is_ratio[m] = 0.0F;
         }

         if (flag2) {
            if (layerIIIGranuleInfo.window_switching_flag != 0 && layerIIIGranuleInfo.block_type == 2) {
               if (layerIIIGranuleInfo.mixed_block_flag != 0) {
                  int n3 = 0;

                  for (int idx = 0; idx < 3; idx++) {
                     int n4 = 2;

                     for (int index = 12; index >= 3; index--) {
                        int n5 = this.sfBandIndex[this.sfreq].s[index];
                        int n6 = this.sfBandIndex[this.sfreq].s[index + 1] - n5;

                        for (int i2 = (n5 << 2) - n5 + (idx + 1) * n6 - 1; n6 > 0; i2--) {
                           if (this.ro[1][i2 / 18][i2 % 18] != 0.0F) {
                              n4 = index;
                              index = -10;
                              n6 = -10;
                           }

                           n6--;
                        }
                     }

                     int n7 = n4 + 1;
                     if (n7 > n3) {
                        n3 = n7;
                     }

                     while (n7 < 12) {
                        int n8 = this.sfBandIndex[this.sfreq].s[n7];
                        int n9 = this.sfBandIndex[this.sfreq].s[n7 + 1] - n8;

                        for (int i3 = (n8 << 2) - n8 + idx * n9; n9 > 0; n9--) {
                           this.is_pos[i3] = this.scalefac[1].s[idx][n7];
                           if (this.is_pos[i3] != 7) {
                              if (flag3) {
                                 this.i_stereo_k_values(this.is_pos[i3], n2, i3);
                              } else {
                                 this.is_ratio[i3] = TAN12[this.is_pos[i3]];
                              }
                           }

                           i3++;
                        }

                        n7++;
                     }

                     n7 = this.sfBandIndex[this.sfreq].s[10];
                     int n10 = this.sfBandIndex[this.sfreq].s[11] - n7;
                     n7 = (n7 << 2) - n7 + idx * n10;
                     int n11 = this.sfBandIndex[this.sfreq].s[11];
                     n10 = this.sfBandIndex[this.sfreq].s[12] - n11;

                     for (int i4 = (n11 << 2) - n11 + idx * n10; n10 > 0; n10--) {
                        this.is_pos[i4] = this.is_pos[n7];
                        if (flag3) {
                           this.k[0][i4] = this.k[0][n7];
                           this.k[1][i4] = this.k[1][n7];
                        } else {
                           this.is_ratio[i4] = this.is_ratio[n7];
                        }

                        i4++;
                     }
                  }

                  if (n3 <= 3) {
                     int n12 = 2;
                     int n13 = 17;
                     int n14 = -1;

                     while (n12 >= 0) {
                        if (this.ro[1][n12][n13] != 0.0F) {
                           n14 = (n12 << 4) + (n12 << 1) + n13;
                           n12 = -1;
                        } else if (--n13 < 0) {
                           n12--;
                           n13 = 17;
                        }
                     }

                     n12 = 0;

                     while (this.sfBandIndex[this.sfreq].l[n12] <= n14) {
                        n12++;
                     }

                     int n15 = n12;

                     for (int i5 = this.sfBandIndex[this.sfreq].l[n12]; n15 < 8; n15++) {
                        for (int i6 = this.sfBandIndex[this.sfreq].l[n15 + 1] - this.sfBandIndex[this.sfreq].l[n15]; i6 > 0; i6--) {
                           this.is_pos[i5] = this.scalefac[1].l[n15];
                           if (this.is_pos[i5] != 7) {
                              if (flag3) {
                                 this.i_stereo_k_values(this.is_pos[i5], n2, i5);
                              } else {
                                 this.is_ratio[i5] = TAN12[this.is_pos[i5]];
                              }
                           }

                           i5++;
                        }
                     }
                  }
               } else {
                  for (int i7 = 0; i7 < 3; i7++) {
                     int n16 = -1;

                     for (int i8 = 12; i8 >= 0; i8--) {
                        int n17 = this.sfBandIndex[this.sfreq].s[i8];
                        int n18 = this.sfBandIndex[this.sfreq].s[i8 + 1] - n17;

                        for (int i9 = (n17 << 2) - n17 + (i7 + 1) * n18 - 1; n18 > 0; i9--) {
                           if (this.ro[1][i9 / 18][i9 % 18] != 0.0F) {
                              n16 = i8;
                              i8 = -10;
                              n18 = -10;
                           }

                           n18--;
                        }
                     }

                     for (int i10 = n16 + 1; i10 < 12; i10++) {
                        int n19 = this.sfBandIndex[this.sfreq].s[i10];
                        int n20 = this.sfBandIndex[this.sfreq].s[i10 + 1] - n19;

                        for (int i11 = (n19 << 2) - n19 + i7 * n20; n20 > 0; n20--) {
                           this.is_pos[i11] = this.scalefac[1].s[i7][i10];
                           if (this.is_pos[i11] != 7) {
                              if (flag3) {
                                 this.i_stereo_k_values(this.is_pos[i11], n2, i11);
                              } else {
                                 this.is_ratio[i11] = TAN12[this.is_pos[i11]];
                              }
                           }

                           i11++;
                        }
                     }

                     int n21 = this.sfBandIndex[this.sfreq].s[10];
                     int n22 = this.sfBandIndex[this.sfreq].s[11];
                     int n23 = n22 - n21;
                     int n24 = (n21 << 2) - n21 + i7 * n23;
                     n23 = this.sfBandIndex[this.sfreq].s[12] - n22;

                     for (int i12 = (n22 << 2) - n22 + i7 * n23; n23 > 0; n23--) {
                        this.is_pos[i12] = this.is_pos[n24];
                        if (flag3) {
                           this.k[0][i12] = this.k[0][n24];
                           this.k[1][i12] = this.k[1][n24];
                        } else {
                           this.is_ratio[i12] = this.is_ratio[n24];
                        }

                        i12++;
                     }
                  }
               }
            } else {
               int n25 = 31;
               int n26 = 17;
               int n27 = 0;

               while (n25 >= 0) {
                  if (this.ro[1][n25][n26] != 0.0F) {
                     n27 = (n25 << 4) + (n25 << 1) + n26;
                     n25 = -1;
                  } else if (--n26 < 0) {
                     n25--;
                     n26 = 17;
                  }
               }

               n25 = 0;

               while (this.sfBandIndex[this.sfreq].l[n25] <= n27) {
                  n25++;
               }

               int n28 = n25;

               for (n25 = this.sfBandIndex[this.sfreq].l[n25]; n28 < 21; n28++) {
                  for (int i13 = this.sfBandIndex[this.sfreq].l[n28 + 1] - this.sfBandIndex[this.sfreq].l[n28]; i13 > 0; i13--) {
                     this.is_pos[n25] = this.scalefac[1].l[n28];
                     if (this.is_pos[n25] != 7) {
                        if (flag3) {
                           this.i_stereo_k_values(this.is_pos[n25], n2, n25);
                        } else {
                           this.is_ratio[n25] = TAN12[this.is_pos[n25]];
                        }
                     }

                     n25++;
                  }
               }

               n28 = this.sfBandIndex[this.sfreq].l[20];

               for (int i14 = 576 - this.sfBandIndex[this.sfreq].l[21]; i14 > 0 && n25 < 576; i14--) {
                  this.is_pos[n25] = this.is_pos[n28];
                  if (flag3) {
                     this.k[0][n25] = this.k[0][n28];
                     this.k[1][n25] = this.k[1][n28];
                  } else {
                     this.is_ratio[n25] = this.is_ratio[n28];
                  }

                  n25++;
               }
            }
         }

         int n29 = 0;

         for (int i15 = 0; i15 < 32; i15++) {
            for (int i16 = 0; i16 < 18; i16++) {
               if (this.is_pos[n29] == 7) {
                  if (flag) {
                     this.lr[0][i15][i16] = (this.ro[0][i15][i16] + this.ro[1][i15][i16]) * 0.70710677F;
                     this.lr[1][i15][i16] = (this.ro[0][i15][i16] - this.ro[1][i15][i16]) * 0.70710677F;
                  } else {
                     this.lr[0][i15][i16] = this.ro[0][i15][i16];
                     this.lr[1][i15][i16] = this.ro[1][i15][i16];
                  }
               } else if (flag2) {
                  if (flag3) {
                     this.lr[0][i15][i16] = this.ro[0][i15][i16] * this.k[0][n29];
                     this.lr[1][i15][i16] = this.ro[0][i15][i16] * this.k[1][n29];
                  } else {
                     this.lr[1][i15][i16] = this.ro[0][i15][i16] / (1.0F + this.is_ratio[n29]);
                     this.lr[0][i15][i16] = this.lr[1][i15][i16] * this.is_ratio[n29];
                  }
               }

               n29++;
            }
         }
      }
   }

   public void seek_notify() {
      this.frame_start = 0;

      for (int i = 0; i < 2; i++) {
         for (int j = 0; j < 576; j++) {
            this.prevblck[i][j] = 0.0F;
         }
      }

      this.br = new BitReserve();
   }

   public void inv_mdct(float[] floats, float[] floats2, int n) {
      float f = 0.0F;
      float f2 = 0.0F;
      float f3 = 0.0F;
      float f4 = 0.0F;
      float f5 = 0.0F;
      float f6 = 0.0F;
      float f7 = 0.0F;
      float f8 = 0.0F;
      float f9 = 0.0F;
      float f10 = 0.0F;
      float f11 = 0.0F;
      float f12 = 0.0F;
      float f13 = 0.0F;
      float f14 = 0.0F;
      float f15 = 0.0F;
      float f16 = 0.0F;
      float f17 = 0.0F;
      float f18 = 0.0F;
      if (n == 2) {
         floats2[0] = 0.0F;
         floats2[1] = 0.0F;
         floats2[2] = 0.0F;
         floats2[3] = 0.0F;
         floats2[4] = 0.0F;
         floats2[5] = 0.0F;
         floats2[6] = 0.0F;
         floats2[7] = 0.0F;
         floats2[8] = 0.0F;
         floats2[9] = 0.0F;
         floats2[10] = 0.0F;
         floats2[11] = 0.0F;
         floats2[12] = 0.0F;
         floats2[13] = 0.0F;
         floats2[14] = 0.0F;
         floats2[15] = 0.0F;
         floats2[16] = 0.0F;
         floats2[17] = 0.0F;
         floats2[18] = 0.0F;
         floats2[19] = 0.0F;
         floats2[20] = 0.0F;
         floats2[21] = 0.0F;
         floats2[22] = 0.0F;
         floats2[23] = 0.0F;
         floats2[24] = 0.0F;
         floats2[25] = 0.0F;
         floats2[26] = 0.0F;
         floats2[27] = 0.0F;
         floats2[28] = 0.0F;
         floats2[29] = 0.0F;
         floats2[30] = 0.0F;
         floats2[31] = 0.0F;
         floats2[32] = 0.0F;
         floats2[33] = 0.0F;
         floats2[34] = 0.0F;
         floats2[35] = 0.0F;
         byte b = 0;

         for (int i = 0; i < 3; i++) {
            floats[15 + i] = floats[15 + i] + floats[12 + i];
            floats[12 + i] = floats[12 + i] + floats[9 + i];
            floats[9 + i] = floats[9 + i] + floats[6 + i];
            floats[6 + i] = floats[6 + i] + floats[3 + i];
            floats[3 + i] = floats[3 + i] + floats[0 + i];
            floats[15 + i] = floats[15 + i] + floats[9 + i];
            floats[9 + i] = floats[9 + i] + floats[3 + i];
            float f19 = floats[12 + i] * 0.5F;
            float f20 = floats[6 + i] * 0.8660254F;
            float f21 = floats[0 + i] + f19;
            f17 = floats[0 + i] - floats[12 + i];
            f18 = f21 + f20;
            f16 = f21 - f20;
            f19 = floats[15 + i] * 0.5F;
            f20 = floats[9 + i] * 0.8660254F;
            f21 = floats[3 + i] + f19;
            f14 = floats[3 + i] - floats[15 + i];
            f13 = f21 + f20;
            f15 = f21 - f20;
            f15 *= 1.9318516F;
            f14 *= 0.70710677F;
            f13 *= 0.5176381F;
            float f22 = f18 + f13;
            f13 = f18 - f13;
            float f23 = f17 + f14;
            f14 = f17 - f14;
            float f24 = f16 + f15;
            f15 = f16 - f15;
            f18 = f22 * 0.5043145F;
            f17 = f23 * 0.5411961F;
            f16 = f24 * 0.6302362F;
            f15 *= 0.8213398F;
            f14 *= 1.306563F;
            f13 *= 3.830649F;
            f10 = -f18 * 0.7933533F;
            f9 = -f18 * 0.6087614F;
            f11 = -f17 * 0.9238795F;
            f8 = -f17 * 0.38268343F;
            f12 = -f16 * 0.9914449F;
            f7 = -f16 * 0.13052619F;
            f17 = f14 * 0.38268343F;
            f16 = f13 * 0.6087614F;
            float f25 = -f13 * 0.7933533F;
            f14 = -f14 * 0.9238795F;
            f13 = -f15 * 0.9914449F;
            f18 = f15 * 0.13052619F;
            floats2[b + 6] = floats2[b + 6] + f18;
            floats2[b + 7] = floats2[b + 7] + f17;
            floats2[b + 8] = floats2[b + 8] + f16;
            floats2[b + 9] = floats2[b + 9] + f25;
            floats2[b + 10] = floats2[b + 10] + f14;
            floats2[b + 11] = floats2[b + 11] + f13;
            floats2[b + 12] = floats2[b + 12] + f12;
            floats2[b + 13] = floats2[b + 13] + f11;
            floats2[b + 14] = floats2[b + 14] + f10;
            floats2[b + 15] = floats2[b + 15] + f9;
            floats2[b + 16] = floats2[b + 16] + f8;
            floats2[b + 17] = floats2[b + 17] + f7;
            b += 6;
         }
      } else {
         floats[17] += floats[16];
         floats[16] += floats[15];
         floats[15] += floats[14];
         floats[14] += floats[13];
         floats[13] += floats[12];
         floats[12] += floats[11];
         floats[11] += floats[10];
         floats[10] += floats[9];
         floats[9] += floats[8];
         floats[8] += floats[7];
         floats[7] += floats[6];
         floats[6] += floats[5];
         floats[5] += floats[4];
         floats[4] += floats[3];
         floats[3] += floats[2];
         floats[2] += floats[1];
         floats[1] += floats[0];
         floats[17] += floats[15];
         floats[15] += floats[13];
         floats[13] += floats[11];
         floats[11] += floats[9];
         floats[9] += floats[7];
         floats[7] += floats[5];
         floats[5] += floats[3];
         floats[3] += floats[1];
         float f26 = floats[0] + floats[0];
         float f27 = f26 + floats[12];
         float f28 = f27 + floats[4] * 1.8793852F + floats[8] * 1.5320889F + floats[16] * 0.34729636F;
         float f29 = f26 + floats[4] - floats[8] - floats[12] - floats[12] - floats[16];
         float f30 = f27 - floats[4] * 0.34729636F - floats[8] * 1.8793852F + floats[16] * 1.5320889F;
         float f31 = f27 - floats[4] * 1.5320889F + floats[8] * 0.34729636F - floats[16] * 1.8793852F;
         float f32 = floats[0] - floats[4] + floats[8] - floats[12] + floats[16];
         float f33 = floats[6] * 1.7320508F;
         float f34 = floats[2] * 1.9696155F + f33 + floats[10] * 1.2855753F + floats[14] * 0.6840403F;
         float f35 = (floats[2] - floats[10] - floats[14]) * 1.7320508F;
         float f36 = floats[2] * 1.2855753F - f33 - floats[10] * 0.6840403F + floats[14] * 1.9696155F;
         float f37 = floats[2] * 0.6840403F - f33 + floats[10] * 1.9696155F - floats[14] * 1.2855753F;
         float f38 = floats[1] + floats[1];
         float f39 = f38 + floats[13];
         float f40 = f39 + floats[5] * 1.8793852F + floats[9] * 1.5320889F + floats[17] * 0.34729636F;
         float f41 = f38 + floats[5] - floats[9] - floats[13] - floats[13] - floats[17];
         float f42 = f39 - floats[5] * 0.34729636F - floats[9] * 1.8793852F + floats[17] * 1.5320889F;
         float f43 = f39 - floats[5] * 1.5320889F + floats[9] * 0.34729636F - floats[17] * 1.8793852F;
         float f44 = (floats[1] - floats[5] + floats[9] - floats[13] + floats[17]) * 0.70710677F;
         float f45 = floats[7] * 1.7320508F;
         float f46 = floats[3] * 1.9696155F + f45 + floats[11] * 1.2855753F + floats[15] * 0.6840403F;
         float f47 = (floats[3] - floats[11] - floats[15]) * 1.7320508F;
         float f48 = floats[3] * 1.2855753F - f45 - floats[11] * 0.6840403F + floats[15] * 1.9696155F;
         float f49 = floats[3] * 0.6840403F - f45 + floats[11] * 1.9696155F - floats[15] * 1.2855753F;
         float f50 = f28 + f34;
         float f51 = (f40 + f46) * 0.5019099F;
         f18 = f50 + f51;
         f = f50 - f51;
         f50 = f29 + f35;
         f51 = (f41 + f47) * 0.5176381F;
         f17 = f50 + f51;
         f2 = f50 - f51;
         f50 = f30 + f36;
         f51 = (f42 + f48) * 0.55168897F;
         f16 = f50 + f51;
         f3 = f50 - f51;
         f50 = f31 + f37;
         f51 = (f43 + f49) * 0.61038727F;
         f15 = f50 + f51;
         f4 = f50 - f51;
         f14 = f32 + f44;
         f5 = f32 - f44;
         f50 = f31 - f37;
         f51 = (f43 - f49) * 0.8717234F;
         f13 = f50 + f51;
         f6 = f50 - f51;
         f50 = f30 - f36;
         f51 = (f42 - f48) * 1.1831008F;
         f12 = f50 + f51;
         f7 = f50 - f51;
         f50 = f29 - f35;
         f51 = (f41 - f47) * 1.9318516F;
         f11 = f50 + f51;
         f8 = f50 - f51;
         f50 = f28 - f34;
         f51 = (f40 - f46) * 5.7368565F;
         f10 = f50 + f51;
         f9 = f50 - f51;
         float[] floats3 = win[n];
         floats2[0] = -f9 * floats3[0];
         floats2[1] = -f8 * floats3[1];
         floats2[2] = -f7 * floats3[2];
         floats2[3] = -f6 * floats3[3];
         floats2[4] = -f5 * floats3[4];
         floats2[5] = -f4 * floats3[5];
         floats2[6] = -f3 * floats3[6];
         floats2[7] = -f2 * floats3[7];
         floats2[8] = -f * floats3[8];
         floats2[9] = f * floats3[9];
         floats2[10] = f2 * floats3[10];
         floats2[11] = f3 * floats3[11];
         floats2[12] = f4 * floats3[12];
         floats2[13] = f5 * floats3[13];
         floats2[14] = f6 * floats3[14];
         floats2[15] = f7 * floats3[15];
         floats2[16] = f8 * floats3[16];
         floats2[17] = f9 * floats3[17];
         floats2[18] = f10 * floats3[18];
         floats2[19] = f11 * floats3[19];
         floats2[20] = f12 * floats3[20];
         floats2[21] = f13 * floats3[21];
         floats2[22] = f14 * floats3[22];
         floats2[23] = f15 * floats3[23];
         floats2[24] = f16 * floats3[24];
         floats2[25] = f17 * floats3[25];
         floats2[26] = f18 * floats3[26];
         floats2[27] = f18 * floats3[27];
         floats2[28] = f17 * floats3[28];
         floats2[29] = f16 * floats3[29];
         floats2[30] = f15 * floats3[30];
         floats2[31] = f14 * floats3[31];
         floats2[32] = f13 * floats3[32];
         floats2[33] = f12 * floats3[33];
         floats2[34] = f11 * floats3[34];
         floats2[35] = f10 * floats3[35];
      }
   }

   private void do_downmix() {
      for (int i = 0; i < 18; i++) {
         for (int j = 0; j < 18; j += 3) {
            this.lr[0][i][j] = (this.lr[0][i][j] + this.lr[1][i][j]) * 0.5F;
            this.lr[0][i][j + 1] = (this.lr[0][i][j + 1] + this.lr[1][i][j + 1]) * 0.5F;
            this.lr[0][i][j + 2] = (this.lr[0][i][j + 2] + this.lr[1][i][j + 2]) * 0.5F;
         }
      }
   }

   @Override
   public void decodeFrame() {
      this.decode();
   }

   private static float[] create_t_43() {
      float[] floats = new float[8192];
      double d = 1.3333333333333333;

      for (int i = 0; i < 8192; i++) {
         floats[i] = (float)Math.pow((double)i, 1.3333333333333333);
      }

      return floats;
   }

   private void get_LSF_scale_factors(int n, int n2) {
      int n3 = 0;
      LayerIIIGranuleInfo layerIIIGranuleInfo = this.si.ch[n].gr[n2];
      this.get_LSF_scale_data(n, n2);
      if (layerIIIGranuleInfo.window_switching_flag == 0 || layerIIIGranuleInfo.block_type != 2) {
         for (int i = 0; i < 21; i++) {
            this.scalefac[n].l[i] = this.scalefac_buffer[n3];
            n3++;
         }

         this.scalefac[n].l[21] = 0;
         this.scalefac[n].l[22] = 0;
      } else if (layerIIIGranuleInfo.mixed_block_flag != 0) {
         for (int j = 0; j < 8; j++) {
            this.scalefac[n].l[j] = this.scalefac_buffer[n3];
            n3++;
         }

         for (int m = 3; m < 12; m++) {
            for (int idx = 0; idx < 3; idx++) {
               this.scalefac[n].s[idx][m] = this.scalefac_buffer[n3];
               n3++;
            }
         }

         for (int index = 0; index < 3; index++) {
            this.scalefac[n].s[index][12] = 0;
         }
      } else {
         for (int i2 = 0; i2 < 12; i2++) {
            for (int i3 = 0; i3 < 3; i3++) {
               this.scalefac[n].s[i3][i2] = this.scalefac_buffer[n3];
               n3++;
            }
         }

         for (int i4 = 0; i4 < 3; i4++) {
            this.scalefac[n].s[i4][12] = 0;
         }
      }
   }

   private void reorder(float[][] floatsArr, int n, int n2) {
      LayerIIIGranuleInfo layerIIIGranuleInfo = this.si.ch[n].gr[n2];
      float[][] floatsArr2 = floatsArr;
      if (layerIIIGranuleInfo.window_switching_flag != 0 && layerIIIGranuleInfo.block_type == 2) {
         for (int i = 0; i < 576; i++) {
            this.out_1d[i] = 0.0F;
         }

         if (layerIIIGranuleInfo.mixed_block_flag != 0) {
            for (int j = 0; j < 36; j++) {
               int n3 = j % 18;
               int n4 = (j - n3) / 18;
               this.out_1d[j] = floatsArr2[n4][n3];
            }

            for (int m = 3; m < 13; m++) {
               int n5 = this.sfBandIndex[this.sfreq].s[m];
               int n6 = this.sfBandIndex[this.sfreq].s[m + 1] - n5;
               int n7 = (n5 << 2) - n5;
               int n8 = 0;

               for (int idx = 0; n8 < n6; idx += 3) {
                  int n9 = n7 + n8;
                  int n10 = n7 + idx;
                  int n11 = n9 % 18;
                  int n12 = (n9 - n11) / 18;
                  this.out_1d[n10] = floatsArr2[n12][n11];
                  n9 += n6;
                  n10++;
                  n11 = n9 % 18;
                  n12 = (n9 - n11) / 18;
                  this.out_1d[n10] = floatsArr2[n12][n11];
                  n9 += n6;
                  n10++;
                  n11 = n9 % 18;
                  n12 = (n9 - n11) / 18;
                  this.out_1d[n10] = floatsArr2[n12][n11];
                  n8++;
               }
            }
         } else {
            for (int index = 0; index < 576; index++) {
               int n13 = reorder_table[this.sfreq][index];
               int n14 = n13 % 18;
               int n15 = (n13 - n14) / 18;
               this.out_1d[index] = floatsArr2[n15][n14];
            }
         }
      } else {
         for (int i2 = 0; i2 < 576; i2++) {
            int n16 = i2 % 18;
            int n17 = (i2 - n16) / 18;
            this.out_1d[i2] = floatsArr2[n17][n16];
         }
      }
   }

   private void get_scale_factors(int n, int n2) {
      LayerIIIGranuleInfo layerIIIGranuleInfo = this.si.ch[n].gr[n2];
      int scalefacCompress = layerIIIGranuleInfo.scalefac_compress;
      int n3 = slen[0][scalefacCompress];
      int n4 = slen[1][scalefacCompress];
      if (layerIIIGranuleInfo.window_switching_flag != 0 && layerIIIGranuleInfo.block_type == 2) {
         if (layerIIIGranuleInfo.mixed_block_flag != 0) {
            for (int i = 0; i < 8; i++) {
               this.scalefac[n].l[i] = this.br.hgetbits(slen[0][layerIIIGranuleInfo.scalefac_compress]);
            }

            for (int j = 3; j < 6; j++) {
               for (int m = 0; m < 3; m++) {
                  this.scalefac[n].s[m][j] = this.br.hgetbits(slen[0][layerIIIGranuleInfo.scalefac_compress]);
               }
            }

            for (int idx = 6; idx < 12; idx++) {
               for (int index = 0; index < 3; index++) {
                  this.scalefac[n].s[index][idx] = this.br.hgetbits(slen[1][layerIIIGranuleInfo.scalefac_compress]);
               }
            }


            for (int i2 = 0; i2 < 3; i2++) {
               this.scalefac[n].s[i2][(byte)12] = 0;
            }
         } else {
            this.scalefac[n].s[0][0] = this.br.hgetbits(n3);
            this.scalefac[n].s[1][0] = this.br.hgetbits(n3);
            this.scalefac[n].s[2][0] = this.br.hgetbits(n3);
            this.scalefac[n].s[0][1] = this.br.hgetbits(n3);
            this.scalefac[n].s[1][1] = this.br.hgetbits(n3);
            this.scalefac[n].s[2][1] = this.br.hgetbits(n3);
            this.scalefac[n].s[0][2] = this.br.hgetbits(n3);
            this.scalefac[n].s[1][2] = this.br.hgetbits(n3);
            this.scalefac[n].s[2][2] = this.br.hgetbits(n3);
            this.scalefac[n].s[0][3] = this.br.hgetbits(n3);
            this.scalefac[n].s[1][3] = this.br.hgetbits(n3);
            this.scalefac[n].s[2][3] = this.br.hgetbits(n3);
            this.scalefac[n].s[0][4] = this.br.hgetbits(n3);
            this.scalefac[n].s[1][4] = this.br.hgetbits(n3);
            this.scalefac[n].s[2][4] = this.br.hgetbits(n3);
            this.scalefac[n].s[0][5] = this.br.hgetbits(n3);
            this.scalefac[n].s[1][5] = this.br.hgetbits(n3);
            this.scalefac[n].s[2][5] = this.br.hgetbits(n3);
            this.scalefac[n].s[0][6] = this.br.hgetbits(n4);
            this.scalefac[n].s[1][6] = this.br.hgetbits(n4);
            this.scalefac[n].s[2][6] = this.br.hgetbits(n4);
            this.scalefac[n].s[0][7] = this.br.hgetbits(n4);
            this.scalefac[n].s[1][7] = this.br.hgetbits(n4);
            this.scalefac[n].s[2][7] = this.br.hgetbits(n4);
            this.scalefac[n].s[0][8] = this.br.hgetbits(n4);
            this.scalefac[n].s[1][8] = this.br.hgetbits(n4);
            this.scalefac[n].s[2][8] = this.br.hgetbits(n4);
            this.scalefac[n].s[0][9] = this.br.hgetbits(n4);
            this.scalefac[n].s[1][9] = this.br.hgetbits(n4);
            this.scalefac[n].s[2][9] = this.br.hgetbits(n4);
            this.scalefac[n].s[0][10] = this.br.hgetbits(n4);
            this.scalefac[n].s[1][10] = this.br.hgetbits(n4);
            this.scalefac[n].s[2][10] = this.br.hgetbits(n4);
            this.scalefac[n].s[0][11] = this.br.hgetbits(n4);
            this.scalefac[n].s[1][11] = this.br.hgetbits(n4);
            this.scalefac[n].s[2][11] = this.br.hgetbits(n4);
            this.scalefac[n].s[0][12] = 0;
            this.scalefac[n].s[1][12] = 0;
            this.scalefac[n].s[2][12] = 0;
         }
      } else {
         if (this.si.ch[n].scfsi[0] == 0 || n2 == 0) {
            this.scalefac[n].l[0] = this.br.hgetbits(n3);
            this.scalefac[n].l[1] = this.br.hgetbits(n3);
            this.scalefac[n].l[2] = this.br.hgetbits(n3);
            this.scalefac[n].l[3] = this.br.hgetbits(n3);
            this.scalefac[n].l[4] = this.br.hgetbits(n3);
            this.scalefac[n].l[5] = this.br.hgetbits(n3);
         }

         if (this.si.ch[n].scfsi[1] == 0 || n2 == 0) {
            this.scalefac[n].l[6] = this.br.hgetbits(n3);
            this.scalefac[n].l[7] = this.br.hgetbits(n3);
            this.scalefac[n].l[8] = this.br.hgetbits(n3);
            this.scalefac[n].l[9] = this.br.hgetbits(n3);
            this.scalefac[n].l[10] = this.br.hgetbits(n3);
         }

         if (this.si.ch[n].scfsi[2] == 0 || n2 == 0) {
            this.scalefac[n].l[11] = this.br.hgetbits(n4);
            this.scalefac[n].l[12] = this.br.hgetbits(n4);
            this.scalefac[n].l[13] = this.br.hgetbits(n4);
            this.scalefac[n].l[14] = this.br.hgetbits(n4);
            this.scalefac[n].l[15] = this.br.hgetbits(n4);
         }

         if (this.si.ch[n].scfsi[3] == 0 || n2 == 0) {
            this.scalefac[n].l[16] = this.br.hgetbits(n4);
            this.scalefac[n].l[17] = this.br.hgetbits(n4);
            this.scalefac[n].l[18] = this.br.hgetbits(n4);
            this.scalefac[n].l[19] = this.br.hgetbits(n4);
            this.scalefac[n].l[20] = this.br.hgetbits(n4);
         }

         this.scalefac[n].l[21] = 0;
         this.scalefac[n].l[22] = 0;
      }
   }

   private boolean get_side_info() {
      if (this.header.getHVersion() == 1) {
         this.si.main_data_begin = this.stream.get_bits(9);
         if (this.channels == 1) {
            this.si.private_bits = this.stream.get_bits(5);
         } else {
            this.si.private_bits = this.stream.get_bits(3);
         }

         for (int i = 0; i < this.channels; i++) {
            this.si.ch[i].scfsi[0] = this.stream.get_bits(1);
            this.si.ch[i].scfsi[1] = this.stream.get_bits(1);
            this.si.ch[i].scfsi[2] = this.stream.get_bits(1);
            this.si.ch[i].scfsi[3] = this.stream.get_bits(1);
         }

         for (int j = 0; j < 2; j++) {
            for (int m = 0; m < this.channels; m++) {
               this.si.ch[m].gr[j].part2_3_length = this.stream.get_bits(12);
               this.si.ch[m].gr[j].big_values = this.stream.get_bits(9);
               this.si.ch[m].gr[j].global_gain = this.stream.get_bits(8);
               this.si.ch[m].gr[j].scalefac_compress = this.stream.get_bits(4);
               this.si.ch[m].gr[j].window_switching_flag = this.stream.get_bits(1);
               if (this.si.ch[m].gr[j].window_switching_flag != 0) {
                  this.si.ch[m].gr[j].block_type = this.stream.get_bits(2);
                  this.si.ch[m].gr[j].mixed_block_flag = this.stream.get_bits(1);
                  this.si.ch[m].gr[j].table_select[0] = this.stream.get_bits(5);
                  this.si.ch[m].gr[j].table_select[1] = this.stream.get_bits(5);
                  this.si.ch[m].gr[j].subblock_gain[0] = this.stream.get_bits(3);
                  this.si.ch[m].gr[j].subblock_gain[1] = this.stream.get_bits(3);
                  this.si.ch[m].gr[j].subblock_gain[2] = this.stream.get_bits(3);
                  if (this.si.ch[m].gr[j].block_type == 0) {
                     return false;
                  }

                  if (this.si.ch[m].gr[j].block_type == 2 && this.si.ch[m].gr[j].mixed_block_flag == 0) {
                     this.si.ch[m].gr[j].region0_count = 8;
                  } else {
                     this.si.ch[m].gr[j].region0_count = 7;
                  }

                  this.si.ch[m].gr[j].region1_count = 20 - this.si.ch[m].gr[j].region0_count;
               } else {
                  this.si.ch[m].gr[j].table_select[0] = this.stream.get_bits(5);
                  this.si.ch[m].gr[j].table_select[1] = this.stream.get_bits(5);
                  this.si.ch[m].gr[j].table_select[2] = this.stream.get_bits(5);
                  this.si.ch[m].gr[j].region0_count = this.stream.get_bits(4);
                  this.si.ch[m].gr[j].region1_count = this.stream.get_bits(3);
                  this.si.ch[m].gr[j].block_type = 0;
               }

               this.si.ch[m].gr[j].preflag = this.stream.get_bits(1);
               this.si.ch[m].gr[j].scalefac_scale = this.stream.get_bits(1);
               this.si.ch[m].gr[j].count1table_select = this.stream.get_bits(1);
            }
         }
      } else {
         this.si.main_data_begin = this.stream.get_bits(8);
         if (this.channels == 1) {
            this.si.private_bits = this.stream.get_bits(1);
         } else {
            this.si.private_bits = this.stream.get_bits(2);
         }

         for (int idx = 0; idx < this.channels; idx++) {
            this.si.ch[idx].gr[0].part2_3_length = this.stream.get_bits(12);
            this.si.ch[idx].gr[0].big_values = this.stream.get_bits(9);
            this.si.ch[idx].gr[0].global_gain = this.stream.get_bits(8);
            this.si.ch[idx].gr[0].scalefac_compress = this.stream.get_bits(9);
            this.si.ch[idx].gr[0].window_switching_flag = this.stream.get_bits(1);
            if (this.si.ch[idx].gr[0].window_switching_flag != 0) {
               this.si.ch[idx].gr[0].block_type = this.stream.get_bits(2);
               this.si.ch[idx].gr[0].mixed_block_flag = this.stream.get_bits(1);
               this.si.ch[idx].gr[0].table_select[0] = this.stream.get_bits(5);
               this.si.ch[idx].gr[0].table_select[1] = this.stream.get_bits(5);
               this.si.ch[idx].gr[0].subblock_gain[0] = this.stream.get_bits(3);
               this.si.ch[idx].gr[0].subblock_gain[1] = this.stream.get_bits(3);
               this.si.ch[idx].gr[0].subblock_gain[2] = this.stream.get_bits(3);
               if (this.si.ch[idx].gr[0].block_type == 0) {
                  return false;
               }

               if (this.si.ch[idx].gr[0].block_type == 2 && this.si.ch[idx].gr[0].mixed_block_flag == 0) {
                  this.si.ch[idx].gr[0].region0_count = 8;
               } else {
                  this.si.ch[idx].gr[0].region0_count = 7;
                  this.si.ch[idx].gr[0].region1_count = 20 - this.si.ch[idx].gr[0].region0_count;
               }
            } else {
               this.si.ch[idx].gr[0].table_select[0] = this.stream.get_bits(5);
               this.si.ch[idx].gr[0].table_select[1] = this.stream.get_bits(5);
               this.si.ch[idx].gr[0].table_select[2] = this.stream.get_bits(5);
               this.si.ch[idx].gr[0].region0_count = this.stream.get_bits(4);
               this.si.ch[idx].gr[0].region1_count = this.stream.get_bits(3);
               this.si.ch[idx].gr[0].block_type = 0;
            }

            this.si.ch[idx].gr[0].scalefac_scale = this.stream.get_bits(1);
            this.si.ch[idx].gr[0].count1table_select = this.stream.get_bits(1);
         }
      }

      return true;
   }
}
