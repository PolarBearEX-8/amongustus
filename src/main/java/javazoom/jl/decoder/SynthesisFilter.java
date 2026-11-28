package javazoom.jl.decoder;

import java.io.IOException;

public final class SynthesisFilter {
   private float[] v1;
   private float[] v2;
   private float[] actual_v;
   private int actual_write_pos;
   private float[] samples;
   private int channel;
   private float scalefactor;
   private float[] eq;
   private float[] _tmpOut = new float[32];
   private static final double MY_PI = Math.PI;
   private static final float cos1_64 = (float)(1.0 / (2.0 * Math.cos(0.04908738521234052)));
   private static final float cos3_64 = (float)(1.0 / (2.0 * Math.cos(0.14726215563702155)));
   private static final float cos5_64 = (float)(1.0 / (2.0 * Math.cos(0.2454369260617026)));
   private static final float cos7_64 = (float)(1.0 / (2.0 * Math.cos(0.3436116964863836)));
   private static final float cos9_64 = (float)(1.0 / (2.0 * Math.cos(0.44178646691106466)));
   private static final float cos11_64 = (float)(1.0 / (2.0 * Math.cos(0.5399612373357456)));
   private static final float cos13_64 = (float)(1.0 / (2.0 * Math.cos(0.6381360077604268)));
   private static final float cos15_64 = (float)(1.0 / (2.0 * Math.cos(0.7363107781851077)));
   private static final float cos17_64 = (float)(1.0 / (2.0 * Math.cos(0.8344855486097889)));
   private static final float cos19_64 = (float)(1.0 / (2.0 * Math.cos(0.9326603190344698)));
   private static final float cos21_64 = (float)(1.0 / (2.0 * Math.cos(1.030835089459151)));
   private static final float cos23_64 = (float)(1.0 / (2.0 * Math.cos(1.1290098598838318)));
   private static final float cos25_64 = (float)(1.0 / (2.0 * Math.cos(1.227184630308513)));
   private static final float cos27_64 = (float)(1.0 / (2.0 * Math.cos(1.325359400733194)));
   private static final float cos29_64 = (float)(1.0 / (2.0 * Math.cos(1.423534171157875)));
   private static final float cos31_64 = (float)(1.0 / (2.0 * Math.cos(1.521708941582556)));
   private static final float cos1_32 = (float)(1.0 / (2.0 * Math.cos(0.09817477042468103)));
   private static final float cos3_32 = (float)(1.0 / (2.0 * Math.cos(0.2945243112740431)));
   private static final float cos5_32 = (float)(1.0 / (2.0 * Math.cos(0.4908738521234052)));
   private static final float cos7_32 = (float)(1.0 / (2.0 * Math.cos(0.6872233929727672)));
   private static final float cos9_32 = (float)(1.0 / (2.0 * Math.cos(0.8835729338221293)));
   private static final float cos11_32 = (float)(1.0 / (2.0 * Math.cos(1.0799224746714913)));
   private static final float cos13_32 = (float)(1.0 / (2.0 * Math.cos(1.2762720155208536)));
   private static final float cos15_32 = (float)(1.0 / (2.0 * Math.cos(1.4726215563702154)));
   private static final float cos1_16 = (float)(1.0 / (2.0 * Math.cos(Math.PI / 16)));
   private static final float cos3_16 = (float)(1.0 / (2.0 * Math.cos(0.5890486225480862)));
   private static final float cos5_16 = (float)(1.0 / (2.0 * Math.cos(0.9817477042468103)));
   private static final float cos7_16 = (float)(1.0 / (2.0 * Math.cos(1.3744467859455345)));
   private static final float cos1_8 = (float)(1.0 / (2.0 * Math.cos(Math.PI / 8)));
   private static final float cos3_8 = (float)(1.0 / (2.0 * Math.cos(Math.PI * 3.0 / 8.0)));
   private static final float cos1_4 = (float)(1.0 / (2.0 * Math.cos(Math.PI / 4)));
   private static float[] d = null;
   private static float[][] d16 = (float[][])null;

   private void compute_pcm_samples8(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[8 + n] * floats[0]
                  + actualV[7 + n] * floats[1]
                  + actualV[6 + n] * floats[2]
                  + actualV[5 + n] * floats[3]
                  + actualV[4 + n] * floats[4]
                  + actualV[3 + n] * floats[5]
                  + actualV[2 + n] * floats[6]
                  + actualV[1 + n] * floats[7]
                  + actualV[0 + n] * floats[8]
                  + actualV[15 + n] * floats[9]
                  + actualV[14 + n] * floats[10]
                  + actualV[13 + n] * floats[11]
                  + actualV[12 + n] * floats[12]
                  + actualV[11 + n] * floats[13]
                  + actualV[10 + n] * floats[14]
                  + actualV[9 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   public void calculate_pcm_samples(Obuffer obuffer) {
      this.compute_new_v();
      this.compute_pcm_samples(obuffer);
      this.actual_write_pos = this.actual_write_pos + 1 & 15;
      this.actual_v = this.actual_v == this.v1 ? this.v2 : this.v1;

      for (int i = 0; i < 32; i++) {
         this.samples[i] = 0.0F;
      }
   }

   private void compute_pcm_samples9(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[9 + n] * floats[0]
                  + actualV[8 + n] * floats[1]
                  + actualV[7 + n] * floats[2]
                  + actualV[6 + n] * floats[3]
                  + actualV[5 + n] * floats[4]
                  + actualV[4 + n] * floats[5]
                  + actualV[3 + n] * floats[6]
                  + actualV[2 + n] * floats[7]
                  + actualV[1 + n] * floats[8]
                  + actualV[0 + n] * floats[9]
                  + actualV[15 + n] * floats[10]
                  + actualV[14 + n] * floats[11]
                  + actualV[13 + n] * floats[12]
                  + actualV[12 + n] * floats[13]
                  + actualV[11 + n] * floats[14]
                  + actualV[10 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private static float[] load_d() {
      try {
         Class clazz = float.class;
         return (float[])JavaLayerUtils.deserializeArrayResource("sfd.ser", clazz, 512);
      } catch (IOException e) {
         throw new ExceptionInInitializerError(e);
      }
   }

   private void compute_pcm_samples10(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[10 + n] * floats[0]
                  + actualV[9 + n] * floats[1]
                  + actualV[8 + n] * floats[2]
                  + actualV[7 + n] * floats[3]
                  + actualV[6 + n] * floats[4]
                  + actualV[5 + n] * floats[5]
                  + actualV[4 + n] * floats[6]
                  + actualV[3 + n] * floats[7]
                  + actualV[2 + n] * floats[8]
                  + actualV[1 + n] * floats[9]
                  + actualV[0 + n] * floats[10]
                  + actualV[15 + n] * floats[11]
                  + actualV[14 + n] * floats[12]
                  + actualV[13 + n] * floats[13]
                  + actualV[12 + n] * floats[14]
                  + actualV[11 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private void compute_pcm_samples0(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[0 + n] * floats[0]
                  + actualV[15 + n] * floats[1]
                  + actualV[14 + n] * floats[2]
                  + actualV[13 + n] * floats[3]
                  + actualV[12 + n] * floats[4]
                  + actualV[11 + n] * floats[5]
                  + actualV[10 + n] * floats[6]
                  + actualV[9 + n] * floats[7]
                  + actualV[8 + n] * floats[8]
                  + actualV[7 + n] * floats[9]
                  + actualV[6 + n] * floats[10]
                  + actualV[5 + n] * floats[11]
                  + actualV[4 + n] * floats[12]
                  + actualV[3 + n] * floats[13]
                  + actualV[2 + n] * floats[14]
                  + actualV[1 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private static float[][] splitArray(float[] floats, int n) {
      int n2 = floats.length / n;
      float[][] floatsArr = new float[n2][];

      for (int i = 0; i < n2; i++) {
         floatsArr[i] = subArray(floats, i * n, n);
      }

      return floatsArr;
   }

   public void setEQ(float[] floats) {
      this.eq = floats;
      if (this.eq == null) {
         this.eq = new float[32];

         for (int i = 0; i < 32; i++) {
            this.eq[i] = 1.0F;
         }
      }

      if (this.eq.length < 32) {
         throw new IllegalArgumentException("eq0");
      }
   }

   private void compute_pcm_samples6(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[6 + n] * floats[0]
                  + actualV[5 + n] * floats[1]
                  + actualV[4 + n] * floats[2]
                  + actualV[3 + n] * floats[3]
                  + actualV[2 + n] * floats[4]
                  + actualV[1 + n] * floats[5]
                  + actualV[0 + n] * floats[6]
                  + actualV[15 + n] * floats[7]
                  + actualV[14 + n] * floats[8]
                  + actualV[13 + n] * floats[9]
                  + actualV[12 + n] * floats[10]
                  + actualV[11 + n] * floats[11]
                  + actualV[10 + n] * floats[12]
                  + actualV[9 + n] * floats[13]
                  + actualV[8 + n] * floats[14]
                  + actualV[7 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private void compute_pcm_samples3(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[3 + n] * floats[0]
                  + actualV[2 + n] * floats[1]
                  + actualV[1 + n] * floats[2]
                  + actualV[0 + n] * floats[3]
                  + actualV[15 + n] * floats[4]
                  + actualV[14 + n] * floats[5]
                  + actualV[13 + n] * floats[6]
                  + actualV[12 + n] * floats[7]
                  + actualV[11 + n] * floats[8]
                  + actualV[10 + n] * floats[9]
                  + actualV[9 + n] * floats[10]
                  + actualV[8 + n] * floats[11]
                  + actualV[7 + n] * floats[12]
                  + actualV[6 + n] * floats[13]
                  + actualV[5 + n] * floats[14]
                  + actualV[4 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   public void input_samples(float[] floats) {
      for (int i = 31; i >= 0; i--) {
         this.samples[i] = floats[i] * this.eq[i];
      }
   }

   private void compute_pcm_samples5(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[5 + n] * floats[0]
                  + actualV[4 + n] * floats[1]
                  + actualV[3 + n] * floats[2]
                  + actualV[2 + n] * floats[3]
                  + actualV[1 + n] * floats[4]
                  + actualV[0 + n] * floats[5]
                  + actualV[15 + n] * floats[6]
                  + actualV[14 + n] * floats[7]
                  + actualV[13 + n] * floats[8]
                  + actualV[12 + n] * floats[9]
                  + actualV[11 + n] * floats[10]
                  + actualV[10 + n] * floats[11]
                  + actualV[9 + n] * floats[12]
                  + actualV[8 + n] * floats[13]
                  + actualV[7 + n] * floats[14]
                  + actualV[6 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private void compute_pcm_samples(Obuffer obuffer) {
      switch (this.actual_write_pos) {
         case 0:
            this.compute_pcm_samples0(obuffer);
            break;
         case 1:
            this.compute_pcm_samples1(obuffer);
            break;
         case 2:
            this.compute_pcm_samples2(obuffer);
            break;
         case 3:
            this.compute_pcm_samples3(obuffer);
            break;
         case 4:
            this.compute_pcm_samples4(obuffer);
            break;
         case 5:
            this.compute_pcm_samples5(obuffer);
            break;
         case 6:
            this.compute_pcm_samples6(obuffer);
            break;
         case 7:
            this.compute_pcm_samples7(obuffer);
            break;
         case 8:
            this.compute_pcm_samples8(obuffer);
            break;
         case 9:
            this.compute_pcm_samples9(obuffer);
            break;
         case 10:
            this.compute_pcm_samples10(obuffer);
            break;
         case 11:
            this.compute_pcm_samples11(obuffer);
            break;
         case 12:
            this.compute_pcm_samples12(obuffer);
            break;
         case 13:
            this.compute_pcm_samples13(obuffer);
            break;
         case 14:
            this.compute_pcm_samples14(obuffer);
            break;
         case 15:
            this.compute_pcm_samples15(obuffer);
      }

      if (obuffer != null) {
         obuffer.appendSamples(this.channel, this._tmpOut);
      }
   }

   private void compute_new_v_orig() {
      float[] floats = new float[32];
      float[] floats2 = new float[16];
      float[] floats3 = new float[16];

      for (int i = 31; i >= 0; i--) {
         floats[i] = 0.0F;
      }

      float[] samples2 = this.samples;
      floats2[0] = samples2[0] + samples2[31];
      floats2[1] = samples2[1] + samples2[30];
      floats2[2] = samples2[2] + samples2[29];
      floats2[3] = samples2[3] + samples2[28];
      floats2[4] = samples2[4] + samples2[27];
      floats2[5] = samples2[5] + samples2[26];
      floats2[6] = samples2[6] + samples2[25];
      floats2[7] = samples2[7] + samples2[24];
      floats2[8] = samples2[8] + samples2[23];
      floats2[9] = samples2[9] + samples2[22];
      floats2[10] = samples2[10] + samples2[21];
      floats2[11] = samples2[11] + samples2[20];
      floats2[12] = samples2[12] + samples2[19];
      floats2[13] = samples2[13] + samples2[18];
      floats2[14] = samples2[14] + samples2[17];
      floats2[15] = samples2[15] + samples2[16];
      floats3[0] = floats2[0] + floats2[15];
      floats3[1] = floats2[1] + floats2[14];
      floats3[2] = floats2[2] + floats2[13];
      floats3[3] = floats2[3] + floats2[12];
      floats3[4] = floats2[4] + floats2[11];
      floats3[5] = floats2[5] + floats2[10];
      floats3[6] = floats2[6] + floats2[9];
      floats3[7] = floats2[7] + floats2[8];
      floats3[8] = (floats2[0] - floats2[15]) * cos1_32;
      floats3[9] = (floats2[1] - floats2[14]) * cos3_32;
      floats3[10] = (floats2[2] - floats2[13]) * cos5_32;
      floats3[11] = (floats2[3] - floats2[12]) * cos7_32;
      floats3[12] = (floats2[4] - floats2[11]) * cos9_32;
      floats3[13] = (floats2[5] - floats2[10]) * cos11_32;
      floats3[14] = (floats2[6] - floats2[9]) * cos13_32;
      floats3[15] = (floats2[7] - floats2[8]) * cos15_32;
      floats2[0] = floats3[0] + floats3[7];
      floats2[1] = floats3[1] + floats3[6];
      floats2[2] = floats3[2] + floats3[5];
      floats2[3] = floats3[3] + floats3[4];
      floats2[4] = (floats3[0] - floats3[7]) * cos1_16;
      floats2[5] = (floats3[1] - floats3[6]) * cos3_16;
      floats2[6] = (floats3[2] - floats3[5]) * cos5_16;
      floats2[7] = (floats3[3] - floats3[4]) * cos7_16;
      floats2[8] = floats3[8] + floats3[15];
      floats2[9] = floats3[9] + floats3[14];
      floats2[10] = floats3[10] + floats3[13];
      floats2[11] = floats3[11] + floats3[12];
      floats2[12] = (floats3[8] - floats3[15]) * cos1_16;
      floats2[13] = (floats3[9] - floats3[14]) * cos3_16;
      floats2[14] = (floats3[10] - floats3[13]) * cos5_16;
      floats2[15] = (floats3[11] - floats3[12]) * cos7_16;
      floats3[0] = floats2[0] + floats2[3];
      floats3[1] = floats2[1] + floats2[2];
      floats3[2] = (floats2[0] - floats2[3]) * cos1_8;
      floats3[3] = (floats2[1] - floats2[2]) * cos3_8;
      floats3[4] = floats2[4] + floats2[7];
      floats3[5] = floats2[5] + floats2[6];
      floats3[6] = (floats2[4] - floats2[7]) * cos1_8;
      floats3[7] = (floats2[5] - floats2[6]) * cos3_8;
      floats3[8] = floats2[8] + floats2[11];
      floats3[9] = floats2[9] + floats2[10];
      floats3[10] = (floats2[8] - floats2[11]) * cos1_8;
      floats3[11] = (floats2[9] - floats2[10]) * cos3_8;
      floats3[12] = floats2[12] + floats2[15];
      floats3[13] = floats2[13] + floats2[14];
      floats3[14] = (floats2[12] - floats2[15]) * cos1_8;
      floats3[15] = (floats2[13] - floats2[14]) * cos3_8;
      floats2[0] = floats3[0] + floats3[1];
      floats2[1] = (floats3[0] - floats3[1]) * cos1_4;
      floats2[2] = floats3[2] + floats3[3];
      floats2[3] = (floats3[2] - floats3[3]) * cos1_4;
      floats2[4] = floats3[4] + floats3[5];
      floats2[5] = (floats3[4] - floats3[5]) * cos1_4;
      floats2[6] = floats3[6] + floats3[7];
      floats2[7] = (floats3[6] - floats3[7]) * cos1_4;
      floats2[8] = floats3[8] + floats3[9];
      floats2[9] = (floats3[8] - floats3[9]) * cos1_4;
      floats2[10] = floats3[10] + floats3[11];
      floats2[11] = (floats3[10] - floats3[11]) * cos1_4;
      floats2[12] = floats3[12] + floats3[13];
      floats2[13] = (floats3[12] - floats3[13]) * cos1_4;
      floats2[14] = floats3[14] + floats3[15];
      floats2[15] = (floats3[14] - floats3[15]) * cos1_4;
      floats[19] = -(floats[4] = (floats[12] = floats2[7]) + floats2[5]) - floats2[6];
      floats[27] = -floats2[6] - floats2[7] - floats2[4];
      floats[6] = (floats[10] = (floats[14] = floats2[15]) + floats2[11]) + floats2[13];
      floats[17] = -(floats[2] = floats2[15] + floats2[13] + floats2[9]) - floats2[14];
      float f;
      floats[21] = (f = -floats2[14] - floats2[15] - floats2[10] - floats2[11]) - floats2[13];
      floats[29] = -floats2[14] - floats2[15] - floats2[12] - floats2[8];
      floats[25] = f - floats2[12];
      floats[31] = -floats2[0];
      floats[0] = floats2[1];
      floats[23] = -(floats[8] = floats2[3]) - floats2[2];
      floats2[0] = (samples2[0] - samples2[31]) * cos1_64;
      floats2[1] = (samples2[1] - samples2[30]) * cos3_64;
      floats2[2] = (samples2[2] - samples2[29]) * cos5_64;
      floats2[3] = (samples2[3] - samples2[28]) * cos7_64;
      floats2[4] = (samples2[4] - samples2[27]) * cos9_64;
      floats2[5] = (samples2[5] - samples2[26]) * cos11_64;
      floats2[6] = (samples2[6] - samples2[25]) * cos13_64;
      floats2[7] = (samples2[7] - samples2[24]) * cos15_64;
      floats2[8] = (samples2[8] - samples2[23]) * cos17_64;
      floats2[9] = (samples2[9] - samples2[22]) * cos19_64;
      floats2[10] = (samples2[10] - samples2[21]) * cos21_64;
      floats2[11] = (samples2[11] - samples2[20]) * cos23_64;
      floats2[12] = (samples2[12] - samples2[19]) * cos25_64;
      floats2[13] = (samples2[13] - samples2[18]) * cos27_64;
      floats2[14] = (samples2[14] - samples2[17]) * cos29_64;
      floats2[15] = (samples2[15] - samples2[16]) * cos31_64;
      floats3[0] = floats2[0] + floats2[15];
      floats3[1] = floats2[1] + floats2[14];
      floats3[2] = floats2[2] + floats2[13];
      floats3[3] = floats2[3] + floats2[12];
      floats3[4] = floats2[4] + floats2[11];
      floats3[5] = floats2[5] + floats2[10];
      floats3[6] = floats2[6] + floats2[9];
      floats3[7] = floats2[7] + floats2[8];
      floats3[8] = (floats2[0] - floats2[15]) * cos1_32;
      floats3[9] = (floats2[1] - floats2[14]) * cos3_32;
      floats3[10] = (floats2[2] - floats2[13]) * cos5_32;
      floats3[11] = (floats2[3] - floats2[12]) * cos7_32;
      floats3[12] = (floats2[4] - floats2[11]) * cos9_32;
      floats3[13] = (floats2[5] - floats2[10]) * cos11_32;
      floats3[14] = (floats2[6] - floats2[9]) * cos13_32;
      floats3[15] = (floats2[7] - floats2[8]) * cos15_32;
      floats2[0] = floats3[0] + floats3[7];
      floats2[1] = floats3[1] + floats3[6];
      floats2[2] = floats3[2] + floats3[5];
      floats2[3] = floats3[3] + floats3[4];
      floats2[4] = (floats3[0] - floats3[7]) * cos1_16;
      floats2[5] = (floats3[1] - floats3[6]) * cos3_16;
      floats2[6] = (floats3[2] - floats3[5]) * cos5_16;
      floats2[7] = (floats3[3] - floats3[4]) * cos7_16;
      floats2[8] = floats3[8] + floats3[15];
      floats2[9] = floats3[9] + floats3[14];
      floats2[10] = floats3[10] + floats3[13];
      floats2[11] = floats3[11] + floats3[12];
      floats2[12] = (floats3[8] - floats3[15]) * cos1_16;
      floats2[13] = (floats3[9] - floats3[14]) * cos3_16;
      floats2[14] = (floats3[10] - floats3[13]) * cos5_16;
      floats2[15] = (floats3[11] - floats3[12]) * cos7_16;
      floats3[0] = floats2[0] + floats2[3];
      floats3[1] = floats2[1] + floats2[2];
      floats3[2] = (floats2[0] - floats2[3]) * cos1_8;
      floats3[3] = (floats2[1] - floats2[2]) * cos3_8;
      floats3[4] = floats2[4] + floats2[7];
      floats3[5] = floats2[5] + floats2[6];
      floats3[6] = (floats2[4] - floats2[7]) * cos1_8;
      floats3[7] = (floats2[5] - floats2[6]) * cos3_8;
      floats3[8] = floats2[8] + floats2[11];
      floats3[9] = floats2[9] + floats2[10];
      floats3[10] = (floats2[8] - floats2[11]) * cos1_8;
      floats3[11] = (floats2[9] - floats2[10]) * cos3_8;
      floats3[12] = floats2[12] + floats2[15];
      floats3[13] = floats2[13] + floats2[14];
      floats3[14] = (floats2[12] - floats2[15]) * cos1_8;
      floats3[15] = (floats2[13] - floats2[14]) * cos3_8;
      floats2[0] = floats3[0] + floats3[1];
      floats2[1] = (floats3[0] - floats3[1]) * cos1_4;
      floats2[2] = floats3[2] + floats3[3];
      floats2[3] = (floats3[2] - floats3[3]) * cos1_4;
      floats2[4] = floats3[4] + floats3[5];
      floats2[5] = (floats3[4] - floats3[5]) * cos1_4;
      floats2[6] = floats3[6] + floats3[7];
      floats2[7] = (floats3[6] - floats3[7]) * cos1_4;
      floats2[8] = floats3[8] + floats3[9];
      floats2[9] = (floats3[8] - floats3[9]) * cos1_4;
      floats2[10] = floats3[10] + floats3[11];
      floats2[11] = (floats3[10] - floats3[11]) * cos1_4;
      floats2[12] = floats3[12] + floats3[13];
      floats2[13] = (floats3[12] - floats3[13]) * cos1_4;
      floats2[14] = floats3[14] + floats3[15];
      floats2[15] = (floats3[14] - floats3[15]) * cos1_4;
      floats[5] = (floats[11] = (floats[13] = (floats[15] = floats2[15]) + floats2[7]) + floats2[11]) + floats2[5] + floats2[13];
      floats[7] = (floats[9] = floats2[15] + floats2[11] + floats2[3]) + floats2[13];
      floats[16] = -(floats[1] = (f = floats2[13] + floats2[15] + floats2[9]) + floats2[1]) - floats2[14];
      floats[18] = -(floats[3] = f + floats2[5] + floats2[7]) - floats2[6] - floats2[14];
      floats[22] = (f = -floats2[10] - floats2[11] - floats2[14] - floats2[15]) - floats2[13] - floats2[2] - floats2[3];
      floats[20] = f - floats2[13] - floats2[5] - floats2[6] - floats2[7];
      floats[24] = f - floats2[12] - floats2[2] - floats2[3];
      float f2;
      floats[26] = f - floats2[12] - (f2 = floats2[4] + floats2[6] + floats2[7]);
      floats[30] = (f = -floats2[8] - floats2[12] - floats2[14] - floats2[15]) - floats2[0];
      floats[28] = f - f2;
      float[] actualV = this.actual_v;
      actualV[0 + this.actual_write_pos] = floats[0];
      actualV[16 + this.actual_write_pos] = floats[1];
      actualV[32 + this.actual_write_pos] = floats[2];
      actualV[48 + this.actual_write_pos] = floats[3];
      actualV[64 + this.actual_write_pos] = floats[4];
      actualV[80 + this.actual_write_pos] = floats[5];
      actualV[96 + this.actual_write_pos] = floats[6];
      actualV[112 + this.actual_write_pos] = floats[7];
      actualV[128 + this.actual_write_pos] = floats[8];
      actualV[144 + this.actual_write_pos] = floats[9];
      actualV[160 + this.actual_write_pos] = floats[10];
      actualV[176 + this.actual_write_pos] = floats[11];
      actualV[192 + this.actual_write_pos] = floats[12];
      actualV[208 + this.actual_write_pos] = floats[13];
      actualV[224 + this.actual_write_pos] = floats[14];
      actualV[240 + this.actual_write_pos] = floats[15];
      actualV[256 + this.actual_write_pos] = 0.0F;
      actualV[272 + this.actual_write_pos] = -floats[15];
      actualV[288 + this.actual_write_pos] = -floats[14];
      actualV[304 + this.actual_write_pos] = -floats[13];
      actualV[320 + this.actual_write_pos] = -floats[12];
      actualV[336 + this.actual_write_pos] = -floats[11];
      actualV[352 + this.actual_write_pos] = -floats[10];
      actualV[368 + this.actual_write_pos] = -floats[9];
      actualV[384 + this.actual_write_pos] = -floats[8];
      actualV[400 + this.actual_write_pos] = -floats[7];
      actualV[416 + this.actual_write_pos] = -floats[6];
      actualV[432 + this.actual_write_pos] = -floats[5];
      actualV[448 + this.actual_write_pos] = -floats[4];
      actualV[464 + this.actual_write_pos] = -floats[3];
      actualV[480 + this.actual_write_pos] = -floats[2];
      actualV[496 + this.actual_write_pos] = -floats[1];
   }

   private void compute_pcm_samples1(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[1 + n] * floats[0]
                  + actualV[0 + n] * floats[1]
                  + actualV[15 + n] * floats[2]
                  + actualV[14 + n] * floats[3]
                  + actualV[13 + n] * floats[4]
                  + actualV[12 + n] * floats[5]
                  + actualV[11 + n] * floats[6]
                  + actualV[10 + n] * floats[7]
                  + actualV[9 + n] * floats[8]
                  + actualV[8 + n] * floats[9]
                  + actualV[7 + n] * floats[10]
                  + actualV[6 + n] * floats[11]
                  + actualV[5 + n] * floats[12]
                  + actualV[4 + n] * floats[13]
                  + actualV[3 + n] * floats[14]
                  + actualV[2 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   public void input_sample(float f, int n) {
      this.samples[n] = this.eq[n] * f;
   }

   private void compute_pcm_samples15(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[15 + n] * floats[0]
                  + actualV[14 + n] * floats[1]
                  + actualV[13 + n] * floats[2]
                  + actualV[12 + n] * floats[3]
                  + actualV[11 + n] * floats[4]
                  + actualV[10 + n] * floats[5]
                  + actualV[9 + n] * floats[6]
                  + actualV[8 + n] * floats[7]
                  + actualV[7 + n] * floats[8]
                  + actualV[6 + n] * floats[9]
                  + actualV[5 + n] * floats[10]
                  + actualV[4 + n] * floats[11]
                  + actualV[3 + n] * floats[12]
                  + actualV[2 + n] * floats[13]
                  + actualV[1 + n] * floats[14]
                  + actualV[0 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private void compute_pcm_samples13(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[13 + n] * floats[0]
                  + actualV[12 + n] * floats[1]
                  + actualV[11 + n] * floats[2]
                  + actualV[10 + n] * floats[3]
                  + actualV[9 + n] * floats[4]
                  + actualV[8 + n] * floats[5]
                  + actualV[7 + n] * floats[6]
                  + actualV[6 + n] * floats[7]
                  + actualV[5 + n] * floats[8]
                  + actualV[4 + n] * floats[9]
                  + actualV[3 + n] * floats[10]
                  + actualV[2 + n] * floats[11]
                  + actualV[1 + n] * floats[12]
                  + actualV[0 + n] * floats[13]
                  + actualV[15 + n] * floats[14]
                  + actualV[14 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private void compute_new_v() {
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
      float f19 = 0.0F;
      float f20 = 0.0F;
      float f21 = 0.0F;
      float f22 = 0.0F;
      float f23 = 0.0F;
      float f24 = 0.0F;
      float f25 = 0.0F;
      float f26 = 0.0F;
      float f27 = 0.0F;
      float f28 = 0.0F;
      float f29 = 0.0F;
      float f30 = 0.0F;
      float f31 = 0.0F;
      float f32 = 0.0F;
      float[] samples2 = this.samples;
      float f33 = samples2[0];
      float f34 = samples2[1];
      float f35 = samples2[2];
      float f36 = samples2[3];
      float f37 = samples2[4];
      float f38 = samples2[5];
      float f39 = samples2[6];
      float f40 = samples2[7];
      float f41 = samples2[8];
      float f42 = samples2[9];
      float f43 = samples2[10];
      float f44 = samples2[11];
      float f45 = samples2[12];
      float f46 = samples2[13];
      float f47 = samples2[14];
      float f48 = samples2[15];
      float f49 = samples2[16];
      float f50 = samples2[17];
      float f51 = samples2[18];
      float f52 = samples2[19];
      float f53 = samples2[20];
      float f54 = samples2[21];
      float f55 = samples2[22];
      float f56 = samples2[23];
      float f57 = samples2[24];
      float f58 = samples2[25];
      float f59 = samples2[26];
      float f60 = samples2[27];
      float f61 = samples2[28];
      float f62 = samples2[29];
      float f63 = samples2[30];
      float f64 = samples2[31];
      float f65 = f33 + f64;
      float f66 = f34 + f63;
      float f67 = f35 + f62;
      float f68 = f36 + f61;
      float f69 = f37 + f60;
      float f70 = f38 + f59;
      float f71 = f39 + f58;
      float f72 = f40 + f57;
      float f73 = f41 + f56;
      float f74 = f42 + f55;
      float f75 = f43 + f54;
      float f76 = f44 + f53;
      float f77 = f45 + f52;
      float f78 = f46 + f51;
      float f79 = f47 + f50;
      float f80 = f48 + f49;
      float f81 = f65 + f80;
      float f82 = f66 + f79;
      float f83 = f67 + f78;
      float f84 = f68 + f77;
      float f85 = f69 + f76;
      float f86 = f70 + f75;
      float f87 = f71 + f74;
      float f88 = f72 + f73;
      float f89 = (f65 - f80) * cos1_32;
      float f90 = (f66 - f79) * cos3_32;
      float f91 = (f67 - f78) * cos5_32;
      float f92 = (f68 - f77) * cos7_32;
      float f93 = (f69 - f76) * cos9_32;
      float f94 = (f70 - f75) * cos11_32;
      float f95 = (f71 - f74) * cos13_32;
      float f96 = (f72 - f73) * cos15_32;
      f65 = f81 + f88;
      f66 = f82 + f87;
      f67 = f83 + f86;
      f68 = f84 + f85;
      f69 = (f81 - f88) * cos1_16;
      f70 = (f82 - f87) * cos3_16;
      f71 = (f83 - f86) * cos5_16;
      f72 = (f84 - f85) * cos7_16;
      f73 = f89 + f96;
      f74 = f90 + f95;
      f75 = f91 + f94;
      f76 = f92 + f93;
      f77 = (f89 - f96) * cos1_16;
      f78 = (f90 - f95) * cos3_16;
      f79 = (f91 - f94) * cos5_16;
      f80 = (f92 - f93) * cos7_16;
      f81 = f65 + f68;
      f82 = f66 + f67;
      f83 = (f65 - f68) * cos1_8;
      f84 = (f66 - f67) * cos3_8;
      f85 = f69 + f72;
      f86 = f70 + f71;
      f87 = (f69 - f72) * cos1_8;
      f88 = (f70 - f71) * cos3_8;
      f89 = f73 + f76;
      f90 = f74 + f75;
      f91 = (f73 - f76) * cos1_8;
      f92 = (f74 - f75) * cos3_8;
      f93 = f77 + f80;
      f94 = f78 + f79;
      f95 = (f77 - f80) * cos1_8;
      f96 = (f78 - f79) * cos3_8;
      f65 = f81 + f82;
      f66 = (f81 - f82) * cos1_4;
      f67 = f83 + f84;
      f68 = (f83 - f84) * cos1_4;
      f69 = f85 + f86;
      f70 = (f85 - f86) * cos1_4;
      f71 = f87 + f88;
      f72 = (f87 - f88) * cos1_4;
      f73 = f89 + f90;
      f74 = (f89 - f90) * cos1_4;
      f75 = f91 + f92;
      f76 = (f91 - f92) * cos1_4;
      f77 = f93 + f94;
      f78 = (f93 - f94) * cos1_4;
      f79 = f95 + f96;
      f80 = (f95 - f96) * cos1_4;
      f13 = -(f28 = f72 + f70) - f71;
      f5 = -f71 - f72 - f69;
      f26 = (f22 = f80 + f76) + f78;
      f15 = -(f30 = f80 + f78 + f74) - f79;
      float f97;
      f11 = (f97 = -f79 - f80 - f75 - f76) - f78;
      f3 = -f79 - f80 - f77 - f73;
      f7 = f97 - f77;
      f = -f65;
      f9 = -f68 - f67;
      f65 = (f33 - f64) * cos1_64;
      float f98 = (f34 - f63) * cos3_64;
      f67 = (f35 - f62) * cos5_64;
      float f99 = (f36 - f61) * cos7_64;
      f69 = (f37 - f60) * cos9_64;
      f70 = (f38 - f59) * cos11_64;
      f71 = (f39 - f58) * cos13_64;
      float f100 = (f40 - f57) * cos15_64;
      f73 = (f41 - f56) * cos17_64;
      f74 = (f42 - f55) * cos19_64;
      f75 = (f43 - f54) * cos21_64;
      f76 = (f44 - f53) * cos23_64;
      f77 = (f45 - f52) * cos25_64;
      f78 = (f46 - f51) * cos27_64;
      f79 = (f47 - f50) * cos29_64;
      float f101 = (f48 - f49) * cos31_64;
      f81 = f65 + f101;
      f82 = f98 + f79;
      f83 = f67 + f78;
      f84 = f99 + f77;
      f85 = f69 + f76;
      f86 = f70 + f75;
      f87 = f71 + f74;
      f88 = f100 + f73;
      f89 = (f65 - f101) * cos1_32;
      f90 = (f98 - f79) * cos3_32;
      f91 = (f67 - f78) * cos5_32;
      f92 = (f99 - f77) * cos7_32;
      f93 = (f69 - f76) * cos9_32;
      f94 = (f70 - f75) * cos11_32;
      f95 = (f71 - f74) * cos13_32;
      f96 = (f100 - f73) * cos15_32;
      f65 = f81 + f88;
      float f102 = f82 + f87;
      f67 = f83 + f86;
      float f103 = f84 + f85;
      f69 = (f81 - f88) * cos1_16;
      f70 = (f82 - f87) * cos3_16;
      f71 = (f83 - f86) * cos5_16;
      float f104 = (f84 - f85) * cos7_16;
      f73 = f89 + f96;
      f74 = f90 + f95;
      f75 = f91 + f94;
      f76 = f92 + f93;
      f77 = (f89 - f96) * cos1_16;
      f78 = (f90 - f95) * cos3_16;
      f79 = (f91 - f94) * cos5_16;
      float f105 = (f92 - f93) * cos7_16;
      f81 = f65 + f103;
      f82 = f102 + f67;
      f83 = (f65 - f103) * cos1_8;
      f84 = (f102 - f67) * cos3_8;
      f85 = f69 + f104;
      f86 = f70 + f71;
      f87 = (f69 - f104) * cos1_8;
      f88 = (f70 - f71) * cos3_8;
      f89 = f73 + f76;
      f90 = f74 + f75;
      f91 = (f73 - f76) * cos1_8;
      f92 = (f74 - f75) * cos3_8;
      f93 = f77 + f105;
      f94 = f78 + f79;
      f95 = (f77 - f105) * cos1_8;
      f96 = (f78 - f79) * cos3_8;
      f65 = f81 + f82;
      float f106 = (f81 - f82) * cos1_4;
      f67 = f83 + f84;
      float f107 = (f83 - f84) * cos1_4;
      f69 = f85 + f86;
      f70 = (f85 - f86) * cos1_4;
      f71 = f87 + f88;
      float f108 = (f87 - f88) * cos1_4;
      f73 = f89 + f90;
      f74 = (f89 - f90) * cos1_4;
      f75 = f91 + f92;
      f76 = (f91 - f92) * cos1_4;
      f77 = f93 + f94;
      f78 = (f93 - f94) * cos1_4;
      f79 = f95 + f96;
      float f109 = (f95 - f96) * cos1_4;
      f27 = (f21 = (f19 = f109 + f108) + f76) + f70 + f78;
      f25 = (f23 = f109 + f76 + f107) + f78;
      f16 = -(f31 = (f97 = f78 + f109 + f74) + f106) - f79;
      f14 = -(f29 = f97 + f70 + f108) - f71 - f79;
      f10 = (f97 = -f75 - f76 - f79 - f109) - f78 - f67 - f107;
      f12 = f97 - f78 - f70 - f71 - f108;
      f8 = f97 - f77 - f67 - f107;
      float f110;
      f6 = f97 - f77 - (f110 = f69 + f71 + f108);
      f2 = (f97 = -f73 - f77 - f79 - f109) - f65;
      f4 = f97 - f110;
      float[] actualV = this.actual_v;
      int actualWritePos = this.actual_write_pos;
      actualV[0 + actualWritePos] = f66;
      actualV[16 + actualWritePos] = f31;
      actualV[32 + actualWritePos] = f30;
      actualV[48 + actualWritePos] = f29;
      actualV[64 + actualWritePos] = f28;
      actualV[80 + actualWritePos] = f27;
      actualV[96 + actualWritePos] = f26;
      actualV[112 + actualWritePos] = f25;
      actualV[128 + actualWritePos] = f68;
      actualV[144 + actualWritePos] = f23;
      actualV[160 + actualWritePos] = f22;
      actualV[176 + actualWritePos] = f21;
      actualV[192 + actualWritePos] = f72;
      actualV[208 + actualWritePos] = f19;
      actualV[224 + actualWritePos] = f80;
      actualV[240 + actualWritePos] = f109;
      actualV[256 + actualWritePos] = 0.0F;
      actualV[272 + actualWritePos] = -f109;
      actualV[288 + actualWritePos] = -f80;
      actualV[304 + actualWritePos] = -f19;
      actualV[320 + actualWritePos] = -f72;
      actualV[336 + actualWritePos] = -f21;
      actualV[352 + actualWritePos] = -f22;
      actualV[368 + actualWritePos] = -f23;
      actualV[384 + actualWritePos] = -f68;
      actualV[400 + actualWritePos] = -f25;
      actualV[416 + actualWritePos] = -f26;
      actualV[432 + actualWritePos] = -f27;
      actualV[448 + actualWritePos] = -f28;
      actualV[464 + actualWritePos] = -f29;
      actualV[480 + actualWritePos] = -f30;
      actualV[496 + actualWritePos] = -f31;
      actualV = this.actual_v == this.v1 ? this.v2 : this.v1;
      actualV[0 + actualWritePos] = -f66;
      actualV[16 + actualWritePos] = f16;
      actualV[32 + actualWritePos] = f15;
      actualV[48 + actualWritePos] = f14;
      actualV[64 + actualWritePos] = f13;
      actualV[80 + actualWritePos] = f12;
      actualV[96 + actualWritePos] = f11;
      actualV[112 + actualWritePos] = f10;
      actualV[128 + actualWritePos] = f9;
      actualV[144 + actualWritePos] = f8;
      actualV[160 + actualWritePos] = f7;
      actualV[176 + actualWritePos] = f6;
      actualV[192 + actualWritePos] = f5;
      actualV[208 + actualWritePos] = f4;
      actualV[224 + actualWritePos] = f3;
      actualV[240 + actualWritePos] = f2;
      actualV[256 + actualWritePos] = f;
      actualV[272 + actualWritePos] = f2;
      actualV[288 + actualWritePos] = f3;
      actualV[304 + actualWritePos] = f4;
      actualV[320 + actualWritePos] = f5;
      actualV[336 + actualWritePos] = f6;
      actualV[352 + actualWritePos] = f7;
      actualV[368 + actualWritePos] = f8;
      actualV[384 + actualWritePos] = f9;
      actualV[400 + actualWritePos] = f10;
      actualV[416 + actualWritePos] = f11;
      actualV[432 + actualWritePos] = f12;
      actualV[448 + actualWritePos] = f13;
      actualV[464 + actualWritePos] = f14;
      actualV[480 + actualWritePos] = f15;
      actualV[496 + actualWritePos] = f16;
   }

   private void compute_pcm_samples2(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[2 + n] * floats[0]
                  + actualV[1 + n] * floats[1]
                  + actualV[0 + n] * floats[2]
                  + actualV[15 + n] * floats[3]
                  + actualV[14 + n] * floats[4]
                  + actualV[13 + n] * floats[5]
                  + actualV[12 + n] * floats[6]
                  + actualV[11 + n] * floats[7]
                  + actualV[10 + n] * floats[8]
                  + actualV[9 + n] * floats[9]
                  + actualV[8 + n] * floats[10]
                  + actualV[7 + n] * floats[11]
                  + actualV[6 + n] * floats[12]
                  + actualV[5 + n] * floats[13]
                  + actualV[4 + n] * floats[14]
                  + actualV[3 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private void compute_pcm_samples7(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[7 + n] * floats[0]
                  + actualV[6 + n] * floats[1]
                  + actualV[5 + n] * floats[2]
                  + actualV[4 + n] * floats[3]
                  + actualV[3 + n] * floats[4]
                  + actualV[2 + n] * floats[5]
                  + actualV[1 + n] * floats[6]
                  + actualV[0 + n] * floats[7]
                  + actualV[15 + n] * floats[8]
                  + actualV[14 + n] * floats[9]
                  + actualV[13 + n] * floats[10]
                  + actualV[12 + n] * floats[11]
                  + actualV[11 + n] * floats[12]
                  + actualV[10 + n] * floats[13]
                  + actualV[9 + n] * floats[14]
                  + actualV[8 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private void compute_pcm_samples11(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[11 + n] * floats[0]
                  + actualV[10 + n] * floats[1]
                  + actualV[9 + n] * floats[2]
                  + actualV[8 + n] * floats[3]
                  + actualV[7 + n] * floats[4]
                  + actualV[6 + n] * floats[5]
                  + actualV[5 + n] * floats[6]
                  + actualV[4 + n] * floats[7]
                  + actualV[3 + n] * floats[8]
                  + actualV[2 + n] * floats[9]
                  + actualV[1 + n] * floats[10]
                  + actualV[0 + n] * floats[11]
                  + actualV[15 + n] * floats[12]
                  + actualV[14 + n] * floats[13]
                  + actualV[13 + n] * floats[14]
                  + actualV[12 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   public SynthesisFilter(int n, float f, float[] floats) {
      if (d == null) {
         d = load_d();
         d16 = splitArray(d, 16);
      }

      this.v1 = new float[512];
      this.v2 = new float[512];
      this.samples = new float[32];
      this.channel = n;
      this.scalefactor = f;
      this.setEQ(this.eq);
      this.reset();
   }

   private void compute_pcm_samples4(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[4 + n] * floats[0]
                  + actualV[3 + n] * floats[1]
                  + actualV[2 + n] * floats[2]
                  + actualV[1 + n] * floats[3]
                  + actualV[0 + n] * floats[4]
                  + actualV[15 + n] * floats[5]
                  + actualV[14 + n] * floats[6]
                  + actualV[13 + n] * floats[7]
                  + actualV[12 + n] * floats[8]
                  + actualV[11 + n] * floats[9]
                  + actualV[10 + n] * floats[10]
                  + actualV[9 + n] * floats[11]
                  + actualV[8 + n] * floats[12]
                  + actualV[7 + n] * floats[13]
                  + actualV[6 + n] * floats[14]
                  + actualV[5 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private void compute_pcm_samples12(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[12 + n] * floats[0]
                  + actualV[11 + n] * floats[1]
                  + actualV[10 + n] * floats[2]
                  + actualV[9 + n] * floats[3]
                  + actualV[8 + n] * floats[4]
                  + actualV[7 + n] * floats[5]
                  + actualV[6 + n] * floats[6]
                  + actualV[5 + n] * floats[7]
                  + actualV[4 + n] * floats[8]
                  + actualV[3 + n] * floats[9]
                  + actualV[2 + n] * floats[10]
                  + actualV[1 + n] * floats[11]
                  + actualV[0 + n] * floats[12]
                  + actualV[15 + n] * floats[13]
                  + actualV[14 + n] * floats[14]
                  + actualV[13 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }

   private static float[] subArray(float[] floats, int n, int n2) {
      if (n + n2 > floats.length) {
         n2 = floats.length - n;
      }

      if (n2 < 0) {
         n2 = 0;
      }

      float[] floats2 = new float[n2];

      for (int i = 0; i < n2; i++) {
         floats2[i] = floats[n + i];
      }

      return floats2;
   }

   public void reset() {
      for (int i = 0; i < 512; i++) {
         this.v1[i] = this.v2[i] = 0.0F;
      }

      for (int j = 0; j < 32; j++) {
         this.samples[j] = 0.0F;
      }

      this.actual_v = this.v1;
      this.actual_write_pos = 15;
   }

   private void compute_pcm_samples14(Obuffer obuffer) {
      float[] actualV = this.actual_v;
      float[] tmpOut = this._tmpOut;
      int n = 0;

      for (int i = 0; i < 32; i++) {
         float[] floats = d16[i];
         float f = (
               actualV[14 + n] * floats[0]
                  + actualV[13 + n] * floats[1]
                  + actualV[12 + n] * floats[2]
                  + actualV[11 + n] * floats[3]
                  + actualV[10 + n] * floats[4]
                  + actualV[9 + n] * floats[5]
                  + actualV[8 + n] * floats[6]
                  + actualV[7 + n] * floats[7]
                  + actualV[6 + n] * floats[8]
                  + actualV[5 + n] * floats[9]
                  + actualV[4 + n] * floats[10]
                  + actualV[3 + n] * floats[11]
                  + actualV[2 + n] * floats[12]
                  + actualV[1 + n] * floats[13]
                  + actualV[0 + n] * floats[14]
                  + actualV[15 + n] * floats[15]
            )
            * this.scalefactor;
         tmpOut[i] = f;
         n += 16;
      }
   }
}
