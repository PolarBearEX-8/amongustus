package cc.baritone.utils;

import java.io.IOException;

public final class SfdSerController {
   public float[] factorArray;
   public float[] factorArray2;
   public float[] factorArray3;
   public int count;
   public float[] factorArray4;
   public int count2;
   public float factor;
   public float[] factorArray5;
   public float[] factorArray6 = new float[32];
   public static final double value = Math.PI;
   public static final float factor2 = (float)(1.0 / (2.0 * Math.cos(0.04908738521234052)));
   public static final float factor3 = (float)(1.0 / (2.0 * Math.cos(0.14726215563702155)));
   public static final float factor4 = (float)(1.0 / (2.0 * Math.cos(0.2454369260617026)));
   public static final float factor5 = (float)(1.0 / (2.0 * Math.cos(0.3436116964863836)));
   public static final float factor6 = (float)(1.0 / (2.0 * Math.cos(0.44178646691106466)));
   public static final float factor7 = (float)(1.0 / (2.0 * Math.cos(0.5399612373357456)));
   public static final float factor8 = (float)(1.0 / (2.0 * Math.cos(0.6381360077604268)));
   public static final float factor9 = (float)(1.0 / (2.0 * Math.cos(0.7363107781851077)));
   public static final float factor10 = (float)(1.0 / (2.0 * Math.cos(0.8344855486097889)));
   public static final float factor11 = (float)(1.0 / (2.0 * Math.cos(0.9326603190344698)));
   public static final float factor12 = (float)(1.0 / (2.0 * Math.cos(1.030835089459151)));
   public static final float factor13 = (float)(1.0 / (2.0 * Math.cos(1.1290098598838318)));
   public static final float factor14 = (float)(1.0 / (2.0 * Math.cos(1.227184630308513)));
   public static final float factor15 = (float)(1.0 / (2.0 * Math.cos(1.325359400733194)));
   public static final float factor16 = (float)(1.0 / (2.0 * Math.cos(1.423534171157875)));
   public static final float factor17 = (float)(1.0 / (2.0 * Math.cos(1.521708941582556)));
   public static final float factor18 = (float)(1.0 / (2.0 * Math.cos(0.09817477042468103)));
   public static final float factor19 = (float)(1.0 / (2.0 * Math.cos(0.2945243112740431)));
   public static final float factor20 = (float)(1.0 / (2.0 * Math.cos(0.4908738521234052)));
   public static final float factor21 = (float)(1.0 / (2.0 * Math.cos(0.6872233929727672)));
   public static final float factor22 = (float)(1.0 / (2.0 * Math.cos(0.8835729338221293)));
   public static final float factor23 = (float)(1.0 / (2.0 * Math.cos(1.0799224746714913)));
   public static final float factor24 = (float)(1.0 / (2.0 * Math.cos(1.2762720155208536)));
   public static final float factor25 = (float)(1.0 / (2.0 * Math.cos(1.4726215563702154)));
   public static final float factor26 = (float)(1.0 / (2.0 * Math.cos(Math.PI / 16)));
   public static final float factor27 = (float)(1.0 / (2.0 * Math.cos(0.5890486225480862)));
   public static final float factor28 = (float)(1.0 / (2.0 * Math.cos(0.9817477042468103)));
   public static final float factor29 = (float)(1.0 / (2.0 * Math.cos(1.3744467859455345)));
   public static final float factor30 = (float)(1.0 / (2.0 * Math.cos(Math.PI / 8)));
   public static final float factor31 = (float)(1.0 / (2.0 * Math.cos(Math.PI * 3.0 / 8.0)));
   public static final float factor32 = (float)(1.0 / (2.0 * Math.cos(Math.PI / 4)));
   public static float[] factorArray7 = null;
   public static float[][] factorArray8 = null;

   private void applyExtraCustom4(ExtraCustom extraCustom) {
      switch (this.count) {
         case 0:
            this.applyExtraCustom17(extraCustom);
            break;
         case 1:
            this.applyExtraCustom14(extraCustom);
            break;
         case 2:
            this.applyExtraCustom10(extraCustom);
            break;
         case 3:
            this.applyExtraCustomByExtraCustom(extraCustom);
            break;
         case 4:
            this.applyExtraCustom8(extraCustom);
            break;
         case 5:
            this.applyExtraCustom(extraCustom);
            break;
         case 6:
            this.applyExtraCustom16(extraCustom);
            break;
         case 7:
            this.applyExtraCustom15(extraCustom);
            break;
         case 8:
            this.applyExtraCustom3(extraCustom);
            break;
         case 9:
            this.applyExtraCustom12(extraCustom);
            break;
         case 10:
            this.applyExtraCustom5(extraCustom);
            break;
         case 11:
            this.applyExtraCustom6(extraCustom);
            break;
         case 12:
            this.applyExtraCustom2(extraCustom);
            break;
         case 13:
            this.applyExtraCustom13(extraCustom);
            break;
         case 14:
            this.applyExtraCustom7(extraCustom);
            break;
         case 15:
            this.applyExtraCustom9(extraCustom);
      }

      if (extraCustom != null) {
         extraCustom.applyFloatArray(this.count2, this.factorArray6);
      }
   }

   private static float[] getFloatArray() {
      try {
         Class classItem = float.class;
         return (float[])ClsTypeHelper.resolveTarget("sfd.ser", classItem, 512);
      } catch (IOException ioException) {
         throw new ExceptionInInitializerError(ioException);
      }
   }

   public void applyExtraCustom11(ExtraCustom extraCustom) {
      this.updateState3();
      this.applyExtraCustom4(extraCustom);
      this.count = this.count + 1 & 15;
      this.factorArray3 = this.factorArray3 == this.factorArray ? this.factorArray2 : this.factorArray;

      for (int index = 0; index < 32; index++) {
         this.factorArray4[index] = 0.0F;
      }
   }

   private void applyExtraCustom6(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[11 + byteValue] * factorArray11[0]
                  + factorArray9[10 + byteValue] * factorArray11[1]
                  + factorArray9[9 + byteValue] * factorArray11[2]
                  + factorArray9[8 + byteValue] * factorArray11[3]
                  + factorArray9[7 + byteValue] * factorArray11[4]
                  + factorArray9[6 + byteValue] * factorArray11[5]
                  + factorArray9[5 + byteValue] * factorArray11[6]
                  + factorArray9[4 + byteValue] * factorArray11[7]
                  + factorArray9[3 + byteValue] * factorArray11[8]
                  + factorArray9[2 + byteValue] * factorArray11[9]
                  + factorArray9[1 + byteValue] * factorArray11[10]
                  + factorArray9[0 + byteValue] * factorArray11[11]
                  + factorArray9[15 + byteValue] * factorArray11[12]
                  + factorArray9[14 + byteValue] * factorArray11[13]
                  + factorArray9[13 + byteValue] * factorArray11[14]
                  + factorArray9[12 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void applyExtraCustom17(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[0 + byteValue] * factorArray11[0]
                  + factorArray9[15 + byteValue] * factorArray11[1]
                  + factorArray9[14 + byteValue] * factorArray11[2]
                  + factorArray9[13 + byteValue] * factorArray11[3]
                  + factorArray9[12 + byteValue] * factorArray11[4]
                  + factorArray9[11 + byteValue] * factorArray11[5]
                  + factorArray9[10 + byteValue] * factorArray11[6]
                  + factorArray9[9 + byteValue] * factorArray11[7]
                  + factorArray9[8 + byteValue] * factorArray11[8]
                  + factorArray9[7 + byteValue] * factorArray11[9]
                  + factorArray9[6 + byteValue] * factorArray11[10]
                  + factorArray9[5 + byteValue] * factorArray11[11]
                  + factorArray9[4 + byteValue] * factorArray11[12]
                  + factorArray9[3 + byteValue] * factorArray11[13]
                  + factorArray9[2 + byteValue] * factorArray11[14]
                  + factorArray9[1 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void applyExtraCustom3(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[8 + byteValue] * factorArray11[0]
                  + factorArray9[7 + byteValue] * factorArray11[1]
                  + factorArray9[6 + byteValue] * factorArray11[2]
                  + factorArray9[5 + byteValue] * factorArray11[3]
                  + factorArray9[4 + byteValue] * factorArray11[4]
                  + factorArray9[3 + byteValue] * factorArray11[5]
                  + factorArray9[2 + byteValue] * factorArray11[6]
                  + factorArray9[1 + byteValue] * factorArray11[7]
                  + factorArray9[0 + byteValue] * factorArray11[8]
                  + factorArray9[15 + byteValue] * factorArray11[9]
                  + factorArray9[14 + byteValue] * factorArray11[10]
                  + factorArray9[13 + byteValue] * factorArray11[11]
                  + factorArray9[12 + byteValue] * factorArray11[12]
                  + factorArray9[11 + byteValue] * factorArray11[13]
                  + factorArray9[10 + byteValue] * factorArray11[14]
                  + factorArray9[9 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void applyExtraCustom(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[5 + byteValue] * factorArray11[0]
                  + factorArray9[4 + byteValue] * factorArray11[1]
                  + factorArray9[3 + byteValue] * factorArray11[2]
                  + factorArray9[2 + byteValue] * factorArray11[3]
                  + factorArray9[1 + byteValue] * factorArray11[4]
                  + factorArray9[0 + byteValue] * factorArray11[5]
                  + factorArray9[15 + byteValue] * factorArray11[6]
                  + factorArray9[14 + byteValue] * factorArray11[7]
                  + factorArray9[13 + byteValue] * factorArray11[8]
                  + factorArray9[12 + byteValue] * factorArray11[9]
                  + factorArray9[11 + byteValue] * factorArray11[10]
                  + factorArray9[10 + byteValue] * factorArray11[11]
                  + factorArray9[9 + byteValue] * factorArray11[12]
                  + factorArray9[8 + byteValue] * factorArray11[13]
                  + factorArray9[7 + byteValue] * factorArray11[14]
                  + factorArray9[6 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void applyExtraCustom14(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[1 + byteValue] * factorArray11[0]
                  + factorArray9[0 + byteValue] * factorArray11[1]
                  + factorArray9[15 + byteValue] * factorArray11[2]
                  + factorArray9[14 + byteValue] * factorArray11[3]
                  + factorArray9[13 + byteValue] * factorArray11[4]
                  + factorArray9[12 + byteValue] * factorArray11[5]
                  + factorArray9[11 + byteValue] * factorArray11[6]
                  + factorArray9[10 + byteValue] * factorArray11[7]
                  + factorArray9[9 + byteValue] * factorArray11[8]
                  + factorArray9[8 + byteValue] * factorArray11[9]
                  + factorArray9[7 + byteValue] * factorArray11[10]
                  + factorArray9[6 + byteValue] * factorArray11[11]
                  + factorArray9[5 + byteValue] * factorArray11[12]
                  + factorArray9[4 + byteValue] * factorArray11[13]
                  + factorArray9[3 + byteValue] * factorArray11[14]
                  + factorArray9[2 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void applyExtraCustom7(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[14 + byteValue] * factorArray11[0]
                  + factorArray9[13 + byteValue] * factorArray11[1]
                  + factorArray9[12 + byteValue] * factorArray11[2]
                  + factorArray9[11 + byteValue] * factorArray11[3]
                  + factorArray9[10 + byteValue] * factorArray11[4]
                  + factorArray9[9 + byteValue] * factorArray11[5]
                  + factorArray9[8 + byteValue] * factorArray11[6]
                  + factorArray9[7 + byteValue] * factorArray11[7]
                  + factorArray9[6 + byteValue] * factorArray11[8]
                  + factorArray9[5 + byteValue] * factorArray11[9]
                  + factorArray9[4 + byteValue] * factorArray11[10]
                  + factorArray9[3 + byteValue] * factorArray11[11]
                  + factorArray9[2 + byteValue] * factorArray11[12]
                  + factorArray9[1 + byteValue] * factorArray11[13]
                  + factorArray9[0 + byteValue] * factorArray11[14]
                  + factorArray9[15 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   public void applyFloat(float factor33, int count3) {
      this.factorArray4[count3] = this.factorArray5[count3] * factor33;
   }

   public SfdSerController(int count3, float factor33, float[] factorArray9) {
      if (factorArray7 == null) {
         factorArray7 = getFloatArray();
         factorArray8 = resolveFloatArray(factorArray7, 16);
      }

      this.factorArray = new float[512];
      this.factorArray2 = new float[512];
      this.factorArray4 = new float[32];
      this.count2 = count3;
      this.factor = factor33;
      this.applyFloatArrayByFloatArray(this.factorArray5);
      this.updateState();
   }

   private void applyExtraCustom8(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[4 + byteValue] * factorArray11[0]
                  + factorArray9[3 + byteValue] * factorArray11[1]
                  + factorArray9[2 + byteValue] * factorArray11[2]
                  + factorArray9[1 + byteValue] * factorArray11[3]
                  + factorArray9[0 + byteValue] * factorArray11[4]
                  + factorArray9[15 + byteValue] * factorArray11[5]
                  + factorArray9[14 + byteValue] * factorArray11[6]
                  + factorArray9[13 + byteValue] * factorArray11[7]
                  + factorArray9[12 + byteValue] * factorArray11[8]
                  + factorArray9[11 + byteValue] * factorArray11[9]
                  + factorArray9[10 + byteValue] * factorArray11[10]
                  + factorArray9[9 + byteValue] * factorArray11[11]
                  + factorArray9[8 + byteValue] * factorArray11[12]
                  + factorArray9[7 + byteValue] * factorArray11[13]
                  + factorArray9[6 + byteValue] * factorArray11[14]
                  + factorArray9[5 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   public void applyFloatArray(float[] factorArray9) {
      for (int index = 31; index >= 0; index--) {
         this.factorArray4[index] = factorArray9[index] * this.factorArray5[index];
      }
   }

   private void applyExtraCustom13(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[13 + byteValue] * factorArray11[0]
                  + factorArray9[12 + byteValue] * factorArray11[1]
                  + factorArray9[11 + byteValue] * factorArray11[2]
                  + factorArray9[10 + byteValue] * factorArray11[3]
                  + factorArray9[9 + byteValue] * factorArray11[4]
                  + factorArray9[8 + byteValue] * factorArray11[5]
                  + factorArray9[7 + byteValue] * factorArray11[6]
                  + factorArray9[6 + byteValue] * factorArray11[7]
                  + factorArray9[5 + byteValue] * factorArray11[8]
                  + factorArray9[4 + byteValue] * factorArray11[9]
                  + factorArray9[3 + byteValue] * factorArray11[10]
                  + factorArray9[2 + byteValue] * factorArray11[11]
                  + factorArray9[1 + byteValue] * factorArray11[12]
                  + factorArray9[0 + byteValue] * factorArray11[13]
                  + factorArray9[15 + byteValue] * factorArray11[14]
                  + factorArray9[14 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void applyExtraCustom2(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[12 + byteValue] * factorArray11[0]
                  + factorArray9[11 + byteValue] * factorArray11[1]
                  + factorArray9[10 + byteValue] * factorArray11[2]
                  + factorArray9[9 + byteValue] * factorArray11[3]
                  + factorArray9[8 + byteValue] * factorArray11[4]
                  + factorArray9[7 + byteValue] * factorArray11[5]
                  + factorArray9[6 + byteValue] * factorArray11[6]
                  + factorArray9[5 + byteValue] * factorArray11[7]
                  + factorArray9[4 + byteValue] * factorArray11[8]
                  + factorArray9[3 + byteValue] * factorArray11[9]
                  + factorArray9[2 + byteValue] * factorArray11[10]
                  + factorArray9[1 + byteValue] * factorArray11[11]
                  + factorArray9[0 + byteValue] * factorArray11[12]
                  + factorArray9[15 + byteValue] * factorArray11[13]
                  + factorArray9[14 + byteValue] * factorArray11[14]
                  + factorArray9[13 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void applyExtraCustom10(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[2 + byteValue] * factorArray11[0]
                  + factorArray9[1 + byteValue] * factorArray11[1]
                  + factorArray9[0 + byteValue] * factorArray11[2]
                  + factorArray9[15 + byteValue] * factorArray11[3]
                  + factorArray9[14 + byteValue] * factorArray11[4]
                  + factorArray9[13 + byteValue] * factorArray11[5]
                  + factorArray9[12 + byteValue] * factorArray11[6]
                  + factorArray9[11 + byteValue] * factorArray11[7]
                  + factorArray9[10 + byteValue] * factorArray11[8]
                  + factorArray9[9 + byteValue] * factorArray11[9]
                  + factorArray9[8 + byteValue] * factorArray11[10]
                  + factorArray9[7 + byteValue] * factorArray11[11]
                  + factorArray9[6 + byteValue] * factorArray11[12]
                  + factorArray9[5 + byteValue] * factorArray11[13]
                  + factorArray9[4 + byteValue] * factorArray11[14]
                  + factorArray9[3 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void updateState3() {
      float[] factorArray9 = this.factorArray4;
      float factor33 = factorArray9[0];
      float factor34 = factorArray9[1];
      float factor35 = factorArray9[2];
      float factor36 = factorArray9[3];
      float factor37 = factorArray9[4];
      float factor38 = factorArray9[5];
      float factor39 = factorArray9[6];
      float factor40 = factorArray9[7];
      float factor41 = factorArray9[8];
      float factor42 = factorArray9[9];
      float factor43 = factorArray9[10];
      float factor44 = factorArray9[11];
      float factor45 = factorArray9[12];
      float factor46 = factorArray9[13];
      float factor47 = factorArray9[14];
      float factor48 = factorArray9[15];
      float factor49 = factorArray9[16];
      float factor50 = factorArray9[17];
      float factor51 = factorArray9[18];
      float factor52 = factorArray9[19];
      float factor53 = factorArray9[20];
      float factor54 = factorArray9[21];
      float factor55 = factorArray9[22];
      float factor56 = factorArray9[23];
      float factor57 = factorArray9[24];
      float factor58 = factorArray9[25];
      float factor59 = factorArray9[26];
      float factor60 = factorArray9[27];
      float factor61 = factorArray9[28];
      float factor62 = factorArray9[29];
      float factor63 = factorArray9[30];
      float factor64 = factorArray9[31];
      float factor65 = factor33 + factor64;
      float factor66 = factor34 + factor63;
      float factor67 = factor35 + factor62;
      float factor68 = factor36 + factor61;
      float factor69 = factor37 + factor60;
      float factor70 = factor38 + factor59;
      float factor71 = factor39 + factor58;
      float factor72 = factor40 + factor57;
      float factor73 = factor41 + factor56;
      float factor74 = factor42 + factor55;
      float factor75 = factor43 + factor54;
      float factor76 = factor44 + factor53;
      float factor77 = factor45 + factor52;
      float factor78 = factor46 + factor51;
      float factor79 = factor47 + factor50;
      float factor80 = factor48 + factor49;
      float factor81 = factor65 + factor80;
      float factor82 = factor66 + factor79;
      float factor83 = factor67 + factor78;
      float factor84 = factor68 + factor77;
      float factor85 = factor69 + factor76;
      float factor86 = factor70 + factor75;
      float factor87 = factor71 + factor74;
      float factor88 = factor72 + factor73;
      float factor89 = (factor65 - factor80) * factor18;
      float factor90 = (factor66 - factor79) * factor19;
      float factor91 = (factor67 - factor78) * factor20;
      float factor92 = (factor68 - factor77) * factor21;
      float factor93 = (factor69 - factor76) * factor22;
      float factor94 = (factor70 - factor75) * factor23;
      float factor95 = (factor71 - factor74) * factor24;
      float factor96 = (factor72 - factor73) * factor25;
      float factor97 = factor81 + factor88;
      float factor98 = factor82 + factor87;
      float factor99 = factor83 + factor86;
      float factor100 = factor84 + factor85;
      float factor101 = (factor81 - factor88) * factor26;
      float factor102 = (factor82 - factor87) * factor27;
      float factor103 = (factor83 - factor86) * factor28;
      float factor104 = (factor84 - factor85) * factor29;
      float factor105 = factor89 + factor96;
      float factor106 = factor90 + factor95;
      float factor107 = factor91 + factor94;
      float factor108 = factor92 + factor93;
      float factor109 = (factor89 - factor96) * factor26;
      float factor110 = (factor90 - factor95) * factor27;
      float factor111 = (factor91 - factor94) * factor28;
      float factor112 = (factor92 - factor93) * factor29;
      float factor113 = factor97 + factor100;
      float factor114 = factor98 + factor99;
      float factor115 = (factor97 - factor100) * factor30;
      float factor116 = (factor98 - factor99) * factor31;
      float factor117 = factor101 + factor104;
      float factor118 = factor102 + factor103;
      float factor119 = (factor101 - factor104) * factor30;
      float factor120 = (factor102 - factor103) * factor31;
      float factor121 = factor105 + factor108;
      float factor122 = factor106 + factor107;
      float factor123 = (factor105 - factor108) * factor30;
      float factor124 = (factor106 - factor107) * factor31;
      float factor125 = factor109 + factor112;
      float factor126 = factor110 + factor111;
      float factor127 = (factor109 - factor112) * factor30;
      float factor128 = (factor110 - factor111) * factor31;
      float factor129 = factor113 + factor114;
      float factor130 = (factor113 - factor114) * factor32;
      float factor131 = factor115 + factor116;
      float factor132 = (factor115 - factor116) * factor32;
      float factor133 = factor117 + factor118;
      float factor134 = (factor117 - factor118) * factor32;
      float factor135 = factor119 + factor120;
      float factor136 = (factor119 - factor120) * factor32;
      float factor137 = factor121 + factor122;
      float factor138 = (factor121 - factor122) * factor32;
      float factor139 = factor123 + factor124;
      float factor140 = (factor123 - factor124) * factor32;
      float factor141 = factor125 + factor126;
      float factor142 = (factor125 - factor126) * factor32;
      float factor143 = factor127 + factor128;
      float factor144 = (factor127 - factor128) * factor32;
      float factor145;
      float factor146 = -(factor145 = factor136 + factor134) - factor135;
      float factor147 = -factor135 - factor136 - factor133;
      float factor148;
      float factor149 = (factor148 = factor144 + factor140) + factor142;
      float factor150;
      float factor151 = -(factor150 = factor144 + factor142 + factor138) - factor143;
      float factor152;
      float factor153 = (factor152 = -factor143 - factor144 - factor139 - factor140) - factor142;
      float factor154 = -factor143 - factor144 - factor141 - factor137;
      float factor155 = factor152 - factor141;
      float factor156 = -factor129;
      float factor157 = -factor132 - factor131;
      float factor158 = (factor33 - factor64) * factor2;
      float factor159 = (factor34 - factor63) * factor3;
      float factor160 = (factor35 - factor62) * factor4;
      float factor161 = (factor36 - factor61) * factor5;
      float factor162 = (factor37 - factor60) * factor6;
      float factor163 = (factor38 - factor59) * factor7;
      float factor164 = (factor39 - factor58) * factor8;
      float factor165 = (factor40 - factor57) * factor9;
      float factor166 = (factor41 - factor56) * factor10;
      float factor167 = (factor42 - factor55) * factor11;
      float factor168 = (factor43 - factor54) * factor12;
      float factor169 = (factor44 - factor53) * factor13;
      float factor170 = (factor45 - factor52) * factor14;
      float factor171 = (factor46 - factor51) * factor15;
      float factor172 = (factor47 - factor50) * factor16;
      float factor173 = (factor48 - factor49) * factor17;
      float factor174 = factor158 + factor173;
      float factor175 = factor159 + factor172;
      float factor176 = factor160 + factor171;
      float factor177 = factor161 + factor170;
      float factor178 = factor162 + factor169;
      float factor179 = factor163 + factor168;
      float factor180 = factor164 + factor167;
      float factor181 = factor165 + factor166;
      float factor182 = (factor158 - factor173) * factor18;
      float factor183 = (factor159 - factor172) * factor19;
      float factor184 = (factor160 - factor171) * factor20;
      float factor185 = (factor161 - factor170) * factor21;
      float factor186 = (factor162 - factor169) * factor22;
      float factor187 = (factor163 - factor168) * factor23;
      float factor188 = (factor164 - factor167) * factor24;
      float factor189 = (factor165 - factor166) * factor25;
      float factor190 = factor174 + factor181;
      float factor191 = factor175 + factor180;
      float factor192 = factor176 + factor179;
      float factor193 = factor177 + factor178;
      float factor194 = (factor174 - factor181) * factor26;
      float factor195 = (factor175 - factor180) * factor27;
      float factor196 = (factor176 - factor179) * factor28;
      float factor197 = (factor177 - factor178) * factor29;
      float factor198 = factor182 + factor189;
      float factor199 = factor183 + factor188;
      float factor200 = factor184 + factor187;
      float factor201 = factor185 + factor186;
      float factor202 = (factor182 - factor189) * factor26;
      float factor203 = (factor183 - factor188) * factor27;
      float factor204 = (factor184 - factor187) * factor28;
      float factor205 = (factor185 - factor186) * factor29;
      float factor206 = factor190 + factor193;
      float factor207 = factor191 + factor192;
      float factor208 = (factor190 - factor193) * factor30;
      float factor209 = (factor191 - factor192) * factor31;
      float factor210 = factor194 + factor197;
      float factor211 = factor195 + factor196;
      float factor212 = (factor194 - factor197) * factor30;
      float factor213 = (factor195 - factor196) * factor31;
      float factor214 = factor198 + factor201;
      float factor215 = factor199 + factor200;
      float factor216 = (factor198 - factor201) * factor30;
      float factor217 = (factor199 - factor200) * factor31;
      float factor218 = factor202 + factor205;
      float factor219 = factor203 + factor204;
      float factor220 = (factor202 - factor205) * factor30;
      float factor221 = (factor203 - factor204) * factor31;
      float factor222 = factor206 + factor207;
      float factor223 = (factor206 - factor207) * factor32;
      float factor224 = factor208 + factor209;
      float factor225 = (factor208 - factor209) * factor32;
      float factor226 = factor210 + factor211;
      float factor227 = (factor210 - factor211) * factor32;
      float factor228 = factor212 + factor213;
      float factor229 = (factor212 - factor213) * factor32;
      float factor230 = factor214 + factor215;
      float factor231 = (factor214 - factor215) * factor32;
      float factor232 = factor216 + factor217;
      float factor233 = (factor216 - factor217) * factor32;
      float factor234 = factor218 + factor219;
      float factor235 = (factor218 - factor219) * factor32;
      float factor236 = factor220 + factor221;
      float factor237 = (factor220 - factor221) * factor32;
      float factor238;
      float factor239;
      float factor240 = (factor239 = (factor238 = factor237 + factor229) + factor233) + factor227 + factor235;
      float factor241;
      float factor242 = (factor241 = factor237 + factor233 + factor225) + factor235;
      float factor243;
      float factor244;
      float factor245 = -(factor243 = (factor244 = factor235 + factor237 + factor231) + factor223) - factor236;
      float factor246;
      float factor247 = -(factor246 = factor244 + factor227 + factor229) - factor228 - factor236;
      float factor248;
      float factor249 = (factor248 = -factor232 - factor233 - factor236 - factor237) - factor235 - factor224 - factor225;
      float factor250 = factor248 - factor235 - factor227 - factor228 - factor229;
      float factor251 = factor248 - factor234 - factor224 - factor225;
      float factor252;
      float factor253 = factor248 - factor234 - (factor252 = factor226 + factor228 + factor229);
      float factor254;
      float factor255 = (factor254 = -factor230 - factor234 - factor236 - factor237) - factor222;
      float factor256 = factor254 - factor252;
      float[] factorArray10 = this.factorArray3;
      int count3 = this.count;
      factorArray10[0 + count3] = factor130;
      factorArray10[16 + count3] = factor243;
      factorArray10[32 + count3] = factor150;
      factorArray10[48 + count3] = factor246;
      factorArray10[64 + count3] = factor145;
      factorArray10[80 + count3] = factor240;
      factorArray10[96 + count3] = factor149;
      factorArray10[112 + count3] = factor242;
      factorArray10[128 + count3] = factor132;
      factorArray10[144 + count3] = factor241;
      factorArray10[160 + count3] = factor148;
      factorArray10[176 + count3] = factor239;
      factorArray10[192 + count3] = factor136;
      factorArray10[208 + count3] = factor238;
      factorArray10[224 + count3] = factor144;
      factorArray10[240 + count3] = factor237;
      factorArray10[256 + count3] = 0.0F;
      factorArray10[272 + count3] = -factor237;
      factorArray10[288 + count3] = -factor144;
      factorArray10[304 + count3] = -factor238;
      factorArray10[320 + count3] = -factor136;
      factorArray10[336 + count3] = -factor239;
      factorArray10[352 + count3] = -factor148;
      factorArray10[368 + count3] = -factor241;
      factorArray10[384 + count3] = -factor132;
      factorArray10[400 + count3] = -factor242;
      factorArray10[416 + count3] = -factor149;
      factorArray10[432 + count3] = -factor240;
      factorArray10[448 + count3] = -factor145;
      factorArray10[464 + count3] = -factor246;
      factorArray10[480 + count3] = -factor150;
      factorArray10[496 + count3] = -factor243;
      float[] factorArray11 = this.factorArray3 == this.factorArray ? this.factorArray2 : this.factorArray;
      factorArray11[0 + count3] = -factor130;
      factorArray11[16 + count3] = factor245;
      factorArray11[32 + count3] = factor151;
      factorArray11[48 + count3] = factor247;
      factorArray11[64 + count3] = factor146;
      factorArray11[80 + count3] = factor250;
      factorArray11[96 + count3] = factor153;
      factorArray11[112 + count3] = factor249;
      factorArray11[128 + count3] = factor157;
      factorArray11[144 + count3] = factor251;
      factorArray11[160 + count3] = factor155;
      factorArray11[176 + count3] = factor253;
      factorArray11[192 + count3] = factor147;
      factorArray11[208 + count3] = factor256;
      factorArray11[224 + count3] = factor154;
      factorArray11[240 + count3] = factor255;
      factorArray11[256 + count3] = factor156;
      factorArray11[272 + count3] = factor255;
      factorArray11[288 + count3] = factor154;
      factorArray11[304 + count3] = factor256;
      factorArray11[320 + count3] = factor147;
      factorArray11[336 + count3] = factor253;
      factorArray11[352 + count3] = factor155;
      factorArray11[368 + count3] = factor251;
      factorArray11[384 + count3] = factor157;
      factorArray11[400 + count3] = factor249;
      factorArray11[416 + count3] = factor153;
      factorArray11[432 + count3] = factor250;
      factorArray11[448 + count3] = factor146;
      factorArray11[464 + count3] = factor247;
      factorArray11[480 + count3] = factor151;
      factorArray11[496 + count3] = factor245;
   }

   private static float[][] resolveFloatArray(float[] factorArray9, int count3) {
      int count4 = factorArray9.length / count3;
      float[][] factorMatrix = new float[count4][];

      for (int index = 0; index < count4; index++) {
         factorMatrix[index] = resolveFloatArray(factorArray9, index * count3, count3);
      }

      return factorMatrix;
   }

   private void applyExtraCustom5(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[10 + byteValue] * factorArray11[0]
                  + factorArray9[9 + byteValue] * factorArray11[1]
                  + factorArray9[8 + byteValue] * factorArray11[2]
                  + factorArray9[7 + byteValue] * factorArray11[3]
                  + factorArray9[6 + byteValue] * factorArray11[4]
                  + factorArray9[5 + byteValue] * factorArray11[5]
                  + factorArray9[4 + byteValue] * factorArray11[6]
                  + factorArray9[3 + byteValue] * factorArray11[7]
                  + factorArray9[2 + byteValue] * factorArray11[8]
                  + factorArray9[1 + byteValue] * factorArray11[9]
                  + factorArray9[0 + byteValue] * factorArray11[10]
                  + factorArray9[15 + byteValue] * factorArray11[11]
                  + factorArray9[14 + byteValue] * factorArray11[12]
                  + factorArray9[13 + byteValue] * factorArray11[13]
                  + factorArray9[12 + byteValue] * factorArray11[14]
                  + factorArray9[11 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void applyExtraCustom12(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[9 + byteValue] * factorArray11[0]
                  + factorArray9[8 + byteValue] * factorArray11[1]
                  + factorArray9[7 + byteValue] * factorArray11[2]
                  + factorArray9[6 + byteValue] * factorArray11[3]
                  + factorArray9[5 + byteValue] * factorArray11[4]
                  + factorArray9[4 + byteValue] * factorArray11[5]
                  + factorArray9[3 + byteValue] * factorArray11[6]
                  + factorArray9[2 + byteValue] * factorArray11[7]
                  + factorArray9[1 + byteValue] * factorArray11[8]
                  + factorArray9[0 + byteValue] * factorArray11[9]
                  + factorArray9[15 + byteValue] * factorArray11[10]
                  + factorArray9[14 + byteValue] * factorArray11[11]
                  + factorArray9[13 + byteValue] * factorArray11[12]
                  + factorArray9[12 + byteValue] * factorArray11[13]
                  + factorArray9[11 + byteValue] * factorArray11[14]
                  + factorArray9[10 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private static float[] resolveFloatArray(float[] factorArray9, int count3, int count4) {
      int count5 = count4;
      if (count3 + count4 > factorArray9.length) {
         count5 = factorArray9.length - count3;
      }

      if (count5 < 0) {
         count5 = 0;
      }

      float[] factorArray10 = new float[count5];

      for (int index = 0; index < count5; index++) {
         factorArray10[index] = factorArray9[count3 + index];
      }

      return factorArray10;
   }

   private void applyExtraCustom16(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[6 + byteValue] * factorArray11[0]
                  + factorArray9[5 + byteValue] * factorArray11[1]
                  + factorArray9[4 + byteValue] * factorArray11[2]
                  + factorArray9[3 + byteValue] * factorArray11[3]
                  + factorArray9[2 + byteValue] * factorArray11[4]
                  + factorArray9[1 + byteValue] * factorArray11[5]
                  + factorArray9[0 + byteValue] * factorArray11[6]
                  + factorArray9[15 + byteValue] * factorArray11[7]
                  + factorArray9[14 + byteValue] * factorArray11[8]
                  + factorArray9[13 + byteValue] * factorArray11[9]
                  + factorArray9[12 + byteValue] * factorArray11[10]
                  + factorArray9[11 + byteValue] * factorArray11[11]
                  + factorArray9[10 + byteValue] * factorArray11[12]
                  + factorArray9[9 + byteValue] * factorArray11[13]
                  + factorArray9[8 + byteValue] * factorArray11[14]
                  + factorArray9[7 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void applyExtraCustom9(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[15 + byteValue] * factorArray11[0]
                  + factorArray9[14 + byteValue] * factorArray11[1]
                  + factorArray9[13 + byteValue] * factorArray11[2]
                  + factorArray9[12 + byteValue] * factorArray11[3]
                  + factorArray9[11 + byteValue] * factorArray11[4]
                  + factorArray9[10 + byteValue] * factorArray11[5]
                  + factorArray9[9 + byteValue] * factorArray11[6]
                  + factorArray9[8 + byteValue] * factorArray11[7]
                  + factorArray9[7 + byteValue] * factorArray11[8]
                  + factorArray9[6 + byteValue] * factorArray11[9]
                  + factorArray9[5 + byteValue] * factorArray11[10]
                  + factorArray9[4 + byteValue] * factorArray11[11]
                  + factorArray9[3 + byteValue] * factorArray11[12]
                  + factorArray9[2 + byteValue] * factorArray11[13]
                  + factorArray9[1 + byteValue] * factorArray11[14]
                  + factorArray9[0 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void applyExtraCustom15(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[7 + byteValue] * factorArray11[0]
                  + factorArray9[6 + byteValue] * factorArray11[1]
                  + factorArray9[5 + byteValue] * factorArray11[2]
                  + factorArray9[4 + byteValue] * factorArray11[3]
                  + factorArray9[3 + byteValue] * factorArray11[4]
                  + factorArray9[2 + byteValue] * factorArray11[5]
                  + factorArray9[1 + byteValue] * factorArray11[6]
                  + factorArray9[0 + byteValue] * factorArray11[7]
                  + factorArray9[15 + byteValue] * factorArray11[8]
                  + factorArray9[14 + byteValue] * factorArray11[9]
                  + factorArray9[13 + byteValue] * factorArray11[10]
                  + factorArray9[12 + byteValue] * factorArray11[11]
                  + factorArray9[11 + byteValue] * factorArray11[12]
                  + factorArray9[10 + byteValue] * factorArray11[13]
                  + factorArray9[9 + byteValue] * factorArray11[14]
                  + factorArray9[8 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   private void updateState2() {
      float[] factorArray9 = new float[32];
      float[] factorArray10 = new float[16];
      float[] factorArray11 = new float[16];

      for (int index = 31; index >= 0; index--) {
         factorArray9[index] = 0.0F;
      }

      float[] factorArray12 = this.factorArray4;
      factorArray10[0] = factorArray12[0] + factorArray12[31];
      factorArray10[1] = factorArray12[1] + factorArray12[30];
      factorArray10[2] = factorArray12[2] + factorArray12[29];
      factorArray10[3] = factorArray12[3] + factorArray12[28];
      factorArray10[4] = factorArray12[4] + factorArray12[27];
      factorArray10[5] = factorArray12[5] + factorArray12[26];
      factorArray10[6] = factorArray12[6] + factorArray12[25];
      factorArray10[7] = factorArray12[7] + factorArray12[24];
      factorArray10[8] = factorArray12[8] + factorArray12[23];
      factorArray10[9] = factorArray12[9] + factorArray12[22];
      factorArray10[10] = factorArray12[10] + factorArray12[21];
      factorArray10[11] = factorArray12[11] + factorArray12[20];
      factorArray10[12] = factorArray12[12] + factorArray12[19];
      factorArray10[13] = factorArray12[13] + factorArray12[18];
      factorArray10[14] = factorArray12[14] + factorArray12[17];
      factorArray10[15] = factorArray12[15] + factorArray12[16];
      factorArray11[0] = factorArray10[0] + factorArray10[15];
      factorArray11[1] = factorArray10[1] + factorArray10[14];
      factorArray11[2] = factorArray10[2] + factorArray10[13];
      factorArray11[3] = factorArray10[3] + factorArray10[12];
      factorArray11[4] = factorArray10[4] + factorArray10[11];
      factorArray11[5] = factorArray10[5] + factorArray10[10];
      factorArray11[6] = factorArray10[6] + factorArray10[9];
      factorArray11[7] = factorArray10[7] + factorArray10[8];
      factorArray11[8] = (factorArray10[0] - factorArray10[15]) * factor18;
      factorArray11[9] = (factorArray10[1] - factorArray10[14]) * factor19;
      factorArray11[10] = (factorArray10[2] - factorArray10[13]) * factor20;
      factorArray11[11] = (factorArray10[3] - factorArray10[12]) * factor21;
      factorArray11[12] = (factorArray10[4] - factorArray10[11]) * factor22;
      factorArray11[13] = (factorArray10[5] - factorArray10[10]) * factor23;
      factorArray11[14] = (factorArray10[6] - factorArray10[9]) * factor24;
      factorArray11[15] = (factorArray10[7] - factorArray10[8]) * factor25;
      factorArray10[0] = factorArray11[0] + factorArray11[7];
      factorArray10[1] = factorArray11[1] + factorArray11[6];
      factorArray10[2] = factorArray11[2] + factorArray11[5];
      factorArray10[3] = factorArray11[3] + factorArray11[4];
      factorArray10[4] = (factorArray11[0] - factorArray11[7]) * factor26;
      factorArray10[5] = (factorArray11[1] - factorArray11[6]) * factor27;
      factorArray10[6] = (factorArray11[2] - factorArray11[5]) * factor28;
      factorArray10[7] = (factorArray11[3] - factorArray11[4]) * factor29;
      factorArray10[8] = factorArray11[8] + factorArray11[15];
      factorArray10[9] = factorArray11[9] + factorArray11[14];
      factorArray10[10] = factorArray11[10] + factorArray11[13];
      factorArray10[11] = factorArray11[11] + factorArray11[12];
      factorArray10[12] = (factorArray11[8] - factorArray11[15]) * factor26;
      factorArray10[13] = (factorArray11[9] - factorArray11[14]) * factor27;
      factorArray10[14] = (factorArray11[10] - factorArray11[13]) * factor28;
      factorArray10[15] = (factorArray11[11] - factorArray11[12]) * factor29;
      factorArray11[0] = factorArray10[0] + factorArray10[3];
      factorArray11[1] = factorArray10[1] + factorArray10[2];
      factorArray11[2] = (factorArray10[0] - factorArray10[3]) * factor30;
      factorArray11[3] = (factorArray10[1] - factorArray10[2]) * factor31;
      factorArray11[4] = factorArray10[4] + factorArray10[7];
      factorArray11[5] = factorArray10[5] + factorArray10[6];
      factorArray11[6] = (factorArray10[4] - factorArray10[7]) * factor30;
      factorArray11[7] = (factorArray10[5] - factorArray10[6]) * factor31;
      factorArray11[8] = factorArray10[8] + factorArray10[11];
      factorArray11[9] = factorArray10[9] + factorArray10[10];
      factorArray11[10] = (factorArray10[8] - factorArray10[11]) * factor30;
      factorArray11[11] = (factorArray10[9] - factorArray10[10]) * factor31;
      factorArray11[12] = factorArray10[12] + factorArray10[15];
      factorArray11[13] = factorArray10[13] + factorArray10[14];
      factorArray11[14] = (factorArray10[12] - factorArray10[15]) * factor30;
      factorArray11[15] = (factorArray10[13] - factorArray10[14]) * factor31;
      factorArray10[0] = factorArray11[0] + factorArray11[1];
      factorArray10[1] = (factorArray11[0] - factorArray11[1]) * factor32;
      factorArray10[2] = factorArray11[2] + factorArray11[3];
      factorArray10[3] = (factorArray11[2] - factorArray11[3]) * factor32;
      factorArray10[4] = factorArray11[4] + factorArray11[5];
      factorArray10[5] = (factorArray11[4] - factorArray11[5]) * factor32;
      factorArray10[6] = factorArray11[6] + factorArray11[7];
      factorArray10[7] = (factorArray11[6] - factorArray11[7]) * factor32;
      factorArray10[8] = factorArray11[8] + factorArray11[9];
      factorArray10[9] = (factorArray11[8] - factorArray11[9]) * factor32;
      factorArray10[10] = factorArray11[10] + factorArray11[11];
      factorArray10[11] = (factorArray11[10] - factorArray11[11]) * factor32;
      factorArray10[12] = factorArray11[12] + factorArray11[13];
      factorArray10[13] = (factorArray11[12] - factorArray11[13]) * factor32;
      factorArray10[14] = factorArray11[14] + factorArray11[15];
      factorArray10[15] = (factorArray11[14] - factorArray11[15]) * factor32;
      factorArray9[19] = -(factorArray9[4] = (factorArray9[12] = factorArray10[7]) + factorArray10[5]) - factorArray10[6];
      factorArray9[27] = -factorArray10[6] - factorArray10[7] - factorArray10[4];
      factorArray9[6] = (factorArray9[10] = (factorArray9[14] = factorArray10[15]) + factorArray10[11]) + factorArray10[13];
      factorArray9[17] = -(factorArray9[2] = factorArray10[15] + factorArray10[13] + factorArray10[9]) - factorArray10[14];
      float factor33;
      factorArray9[21] = (factor33 = -factorArray10[14] - factorArray10[15] - factorArray10[10] - factorArray10[11]) - factorArray10[13];
      factorArray9[29] = -factorArray10[14] - factorArray10[15] - factorArray10[12] - factorArray10[8];
      factorArray9[25] = factor33 - factorArray10[12];
      factorArray9[31] = -factorArray10[0];
      factorArray9[0] = factorArray10[1];
      factorArray9[23] = -(factorArray9[8] = factorArray10[3]) - factorArray10[2];
      factorArray10[0] = (factorArray12[0] - factorArray12[31]) * factor2;
      factorArray10[1] = (factorArray12[1] - factorArray12[30]) * factor3;
      factorArray10[2] = (factorArray12[2] - factorArray12[29]) * factor4;
      factorArray10[3] = (factorArray12[3] - factorArray12[28]) * factor5;
      factorArray10[4] = (factorArray12[4] - factorArray12[27]) * factor6;
      factorArray10[5] = (factorArray12[5] - factorArray12[26]) * factor7;
      factorArray10[6] = (factorArray12[6] - factorArray12[25]) * factor8;
      factorArray10[7] = (factorArray12[7] - factorArray12[24]) * factor9;
      factorArray10[8] = (factorArray12[8] - factorArray12[23]) * factor10;
      factorArray10[9] = (factorArray12[9] - factorArray12[22]) * factor11;
      factorArray10[10] = (factorArray12[10] - factorArray12[21]) * factor12;
      factorArray10[11] = (factorArray12[11] - factorArray12[20]) * factor13;
      factorArray10[12] = (factorArray12[12] - factorArray12[19]) * factor14;
      factorArray10[13] = (factorArray12[13] - factorArray12[18]) * factor15;
      factorArray10[14] = (factorArray12[14] - factorArray12[17]) * factor16;
      factorArray10[15] = (factorArray12[15] - factorArray12[16]) * factor17;
      factorArray11[0] = factorArray10[0] + factorArray10[15];
      factorArray11[1] = factorArray10[1] + factorArray10[14];
      factorArray11[2] = factorArray10[2] + factorArray10[13];
      factorArray11[3] = factorArray10[3] + factorArray10[12];
      factorArray11[4] = factorArray10[4] + factorArray10[11];
      factorArray11[5] = factorArray10[5] + factorArray10[10];
      factorArray11[6] = factorArray10[6] + factorArray10[9];
      factorArray11[7] = factorArray10[7] + factorArray10[8];
      factorArray11[8] = (factorArray10[0] - factorArray10[15]) * factor18;
      factorArray11[9] = (factorArray10[1] - factorArray10[14]) * factor19;
      factorArray11[10] = (factorArray10[2] - factorArray10[13]) * factor20;
      factorArray11[11] = (factorArray10[3] - factorArray10[12]) * factor21;
      factorArray11[12] = (factorArray10[4] - factorArray10[11]) * factor22;
      factorArray11[13] = (factorArray10[5] - factorArray10[10]) * factor23;
      factorArray11[14] = (factorArray10[6] - factorArray10[9]) * factor24;
      factorArray11[15] = (factorArray10[7] - factorArray10[8]) * factor25;
      factorArray10[0] = factorArray11[0] + factorArray11[7];
      factorArray10[1] = factorArray11[1] + factorArray11[6];
      factorArray10[2] = factorArray11[2] + factorArray11[5];
      factorArray10[3] = factorArray11[3] + factorArray11[4];
      factorArray10[4] = (factorArray11[0] - factorArray11[7]) * factor26;
      factorArray10[5] = (factorArray11[1] - factorArray11[6]) * factor27;
      factorArray10[6] = (factorArray11[2] - factorArray11[5]) * factor28;
      factorArray10[7] = (factorArray11[3] - factorArray11[4]) * factor29;
      factorArray10[8] = factorArray11[8] + factorArray11[15];
      factorArray10[9] = factorArray11[9] + factorArray11[14];
      factorArray10[10] = factorArray11[10] + factorArray11[13];
      factorArray10[11] = factorArray11[11] + factorArray11[12];
      factorArray10[12] = (factorArray11[8] - factorArray11[15]) * factor26;
      factorArray10[13] = (factorArray11[9] - factorArray11[14]) * factor27;
      factorArray10[14] = (factorArray11[10] - factorArray11[13]) * factor28;
      factorArray10[15] = (factorArray11[11] - factorArray11[12]) * factor29;
      factorArray11[0] = factorArray10[0] + factorArray10[3];
      factorArray11[1] = factorArray10[1] + factorArray10[2];
      factorArray11[2] = (factorArray10[0] - factorArray10[3]) * factor30;
      factorArray11[3] = (factorArray10[1] - factorArray10[2]) * factor31;
      factorArray11[4] = factorArray10[4] + factorArray10[7];
      factorArray11[5] = factorArray10[5] + factorArray10[6];
      factorArray11[6] = (factorArray10[4] - factorArray10[7]) * factor30;
      factorArray11[7] = (factorArray10[5] - factorArray10[6]) * factor31;
      factorArray11[8] = factorArray10[8] + factorArray10[11];
      factorArray11[9] = factorArray10[9] + factorArray10[10];
      factorArray11[10] = (factorArray10[8] - factorArray10[11]) * factor30;
      factorArray11[11] = (factorArray10[9] - factorArray10[10]) * factor31;
      factorArray11[12] = factorArray10[12] + factorArray10[15];
      factorArray11[13] = factorArray10[13] + factorArray10[14];
      factorArray11[14] = (factorArray10[12] - factorArray10[15]) * factor30;
      factorArray11[15] = (factorArray10[13] - factorArray10[14]) * factor31;
      factorArray10[0] = factorArray11[0] + factorArray11[1];
      factorArray10[1] = (factorArray11[0] - factorArray11[1]) * factor32;
      factorArray10[2] = factorArray11[2] + factorArray11[3];
      factorArray10[3] = (factorArray11[2] - factorArray11[3]) * factor32;
      factorArray10[4] = factorArray11[4] + factorArray11[5];
      factorArray10[5] = (factorArray11[4] - factorArray11[5]) * factor32;
      factorArray10[6] = factorArray11[6] + factorArray11[7];
      factorArray10[7] = (factorArray11[6] - factorArray11[7]) * factor32;
      factorArray10[8] = factorArray11[8] + factorArray11[9];
      factorArray10[9] = (factorArray11[8] - factorArray11[9]) * factor32;
      factorArray10[10] = factorArray11[10] + factorArray11[11];
      factorArray10[11] = (factorArray11[10] - factorArray11[11]) * factor32;
      factorArray10[12] = factorArray11[12] + factorArray11[13];
      factorArray10[13] = (factorArray11[12] - factorArray11[13]) * factor32;
      factorArray10[14] = factorArray11[14] + factorArray11[15];
      factorArray10[15] = (factorArray11[14] - factorArray11[15]) * factor32;
      factorArray9[5] = (factorArray9[11] = (factorArray9[13] = (factorArray9[15] = factorArray10[15]) + factorArray10[7]) + factorArray10[11]) + factorArray10[5] + factorArray10[13];
      factorArray9[7] = (factorArray9[9] = factorArray10[15] + factorArray10[11] + factorArray10[3]) + factorArray10[13];
      float factor34;
      factorArray9[16] = -(factorArray9[1] = (factor34 = factorArray10[13] + factorArray10[15] + factorArray10[9]) + factorArray10[1]) - factorArray10[14];
      factorArray9[18] = -(factorArray9[3] = factor34 + factorArray10[5] + factorArray10[7]) - factorArray10[6] - factorArray10[14];
      float factor35;
      factorArray9[22] = (factor35 = -factorArray10[10] - factorArray10[11] - factorArray10[14] - factorArray10[15]) - factorArray10[13] - factorArray10[2] - factorArray10[3];
      factorArray9[20] = factor35 - factorArray10[13] - factorArray10[5] - factorArray10[6] - factorArray10[7];
      factorArray9[24] = factor35 - factorArray10[12] - factorArray10[2] - factorArray10[3];
      float factor36;
      factorArray9[26] = factor35 - factorArray10[12] - (factor36 = factorArray10[4] + factorArray10[6] + factorArray10[7]);
      float factor37;
      factorArray9[30] = (factor37 = -factorArray10[8] - factorArray10[12] - factorArray10[14] - factorArray10[15]) - factorArray10[0];
      factorArray9[28] = factor37 - factor36;
      float[] factorArray13 = this.factorArray3;
      factorArray13[0 + this.count] = factorArray9[0];
      factorArray13[16 + this.count] = factorArray9[1];
      factorArray13[32 + this.count] = factorArray9[2];
      factorArray13[48 + this.count] = factorArray9[3];
      factorArray13[64 + this.count] = factorArray9[4];
      factorArray13[80 + this.count] = factorArray9[5];
      factorArray13[96 + this.count] = factorArray9[6];
      factorArray13[112 + this.count] = factorArray9[7];
      factorArray13[128 + this.count] = factorArray9[8];
      factorArray13[144 + this.count] = factorArray9[9];
      factorArray13[160 + this.count] = factorArray9[10];
      factorArray13[176 + this.count] = factorArray9[11];
      factorArray13[192 + this.count] = factorArray9[12];
      factorArray13[208 + this.count] = factorArray9[13];
      factorArray13[224 + this.count] = factorArray9[14];
      factorArray13[240 + this.count] = factorArray9[15];
      factorArray13[256 + this.count] = 0.0F;
      factorArray13[272 + this.count] = -factorArray9[15];
      factorArray13[288 + this.count] = -factorArray9[14];
      factorArray13[304 + this.count] = -factorArray9[13];
      factorArray13[320 + this.count] = -factorArray9[12];
      factorArray13[336 + this.count] = -factorArray9[11];
      factorArray13[352 + this.count] = -factorArray9[10];
      factorArray13[368 + this.count] = -factorArray9[9];
      factorArray13[384 + this.count] = -factorArray9[8];
      factorArray13[400 + this.count] = -factorArray9[7];
      factorArray13[416 + this.count] = -factorArray9[6];
      factorArray13[432 + this.count] = -factorArray9[5];
      factorArray13[448 + this.count] = -factorArray9[4];
      factorArray13[464 + this.count] = -factorArray9[3];
      factorArray13[480 + this.count] = -factorArray9[2];
      factorArray13[496 + this.count] = -factorArray9[1];
   }

   private void applyExtraCustomByExtraCustom(ExtraCustom extraCustom) {
      float[] factorArray9 = this.factorArray3;
      float[] factorArray10 = this.factorArray6;
      int byteValue = 0;

      for (int index = 0; index < 32; index++) {
         float[] factorArray11 = factorArray8[index];
         float factor33 = (
               factorArray9[3 + byteValue] * factorArray11[0]
                  + factorArray9[2 + byteValue] * factorArray11[1]
                  + factorArray9[1 + byteValue] * factorArray11[2]
                  + factorArray9[0 + byteValue] * factorArray11[3]
                  + factorArray9[15 + byteValue] * factorArray11[4]
                  + factorArray9[14 + byteValue] * factorArray11[5]
                  + factorArray9[13 + byteValue] * factorArray11[6]
                  + factorArray9[12 + byteValue] * factorArray11[7]
                  + factorArray9[11 + byteValue] * factorArray11[8]
                  + factorArray9[10 + byteValue] * factorArray11[9]
                  + factorArray9[9 + byteValue] * factorArray11[10]
                  + factorArray9[8 + byteValue] * factorArray11[11]
                  + factorArray9[7 + byteValue] * factorArray11[12]
                  + factorArray9[6 + byteValue] * factorArray11[13]
                  + factorArray9[5 + byteValue] * factorArray11[14]
                  + factorArray9[4 + byteValue] * factorArray11[15]
            )
            * this.factor;
         factorArray10[index] = factor33;
         byteValue += 16;
      }
   }

   public void updateState() {
      for (int index = 0; index < 512; index++) {
         this.factorArray[index] = this.factorArray2[index] = 0.0F;
      }

      for (int index2 = 0; index2 < 32; index2++) {
         this.factorArray4[index2] = 0.0F;
      }

      this.factorArray3 = this.factorArray;
      this.count = 15;
   }

   public void applyFloatArrayByFloatArray(float[] factorArray9) {
      this.factorArray5 = factorArray9;
      if (this.factorArray5 == null) {
         this.factorArray5 = new float[32];

         for (int index = 0; index < 32; index++) {
            this.factorArray5[index] = 1.0F;
         }
      }

      if (this.factorArray5.length < 32) {
         throw new IllegalArgumentException("eq0");
      }
   }
}
