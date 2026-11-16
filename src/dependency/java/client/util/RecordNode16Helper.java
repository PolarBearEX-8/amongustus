package client.util;

public class RecordNode16Helper {
   public static final float factor = 0.309017F;
   public static final float factor2 = 0.1381966F;
   public static final RecordNode16[] recordNode16Array = new RecordNode16[]{
      new RecordNode16(0, 1, 1, 1),
      new RecordNode16(0, 1, 1, -1),
      new RecordNode16(0, 1, -1, 1),
      new RecordNode16(0, 1, -1, -1),
      new RecordNode16(0, -1, 1, 1),
      new RecordNode16(0, -1, 1, -1),
      new RecordNode16(0, -1, -1, 1),
      new RecordNode16(0, -1, -1, -1),
      new RecordNode16(1, 0, 1, 1),
      new RecordNode16(1, 0, 1, -1),
      new RecordNode16(1, 0, -1, 1),
      new RecordNode16(1, 0, -1, -1),
      new RecordNode16(-1, 0, 1, 1),
      new RecordNode16(-1, 0, 1, -1),
      new RecordNode16(-1, 0, -1, 1),
      new RecordNode16(-1, 0, -1, -1),
      new RecordNode16(1, 1, 0, 1),
      new RecordNode16(1, 1, 0, -1),
      new RecordNode16(1, -1, 0, 1),
      new RecordNode16(1, -1, 0, -1),
      new RecordNode16(-1, 1, 0, 1),
      new RecordNode16(-1, 1, 0, -1),
      new RecordNode16(-1, -1, 0, 1),
      new RecordNode16(-1, -1, 0, -1),
      new RecordNode16(1, 1, 1, 0),
      new RecordNode16(1, 1, -1, 0),
      new RecordNode16(1, -1, 1, 0),
      new RecordNode16(1, -1, -1, 0),
      new RecordNode16(-1, 1, 1, 0),
      new RecordNode16(-1, 1, -1, 0),
      new RecordNode16(-1, -1, 1, 0),
      new RecordNode16(-1, -1, -1, 0)
   };
   public static final float factor3 = 0.16666667F;
   public static final float factor4 = 0.33333334F;
   public static final byte[] byteFlagArray = new byte[512];
   public static final byte[] byteFlagArray2 = new byte[]{
      -105,
      -96,
      -119,
      91,
      90,
      15,
      -125,
      13,
      -55,
      95,
      96,
      53,
      -62,
      -23,
      7,
      -31,
      -116,
      36,
      103,
      30,
      69,
      -114,
      8,
      99,
      37,
      -16,
      21,
      10,
      23,
      -66,
      6,
      -108,
      -9,
      120,
      -22,
      75,
      0,
      26,
      -59,
      62,
      94,
      -4,
      -37,
      -53,
      117,
      35,
      11,
      32,
      57,
      -79,
      33,
      88,
      -19,
      -107,
      56,
      87,
      -82,
      20,
      125,
      -120,
      -85,
      -88,
      68,
      -81,
      74,
      -91,
      71,
      -122,
      -117,
      48,
      27,
      -90,
      77,
      -110,
      -98,
      -25,
      83,
      111,
      -27,
      122,
      60,
      -45,
      -123,
      -26,
      -36,
      105,
      92,
      41,
      55,
      46,
      -11,
      40,
      -12,
      102,
      -113,
      54,
      65,
      25,
      63,
      -95,
      1,
      -40,
      80,
      73,
      -47,
      76,
      -124,
      -69,
      -48,
      89,
      18,
      -87,
      -56,
      -60,
      -121,
      -126,
      116,
      -68,
      -97,
      86,
      -92,
      100,
      109,
      -58,
      -83,
      -70,
      3,
      64,
      52,
      -39,
      -30,
      -6,
      124,
      123,
      5,
      -54,
      38,
      -109,
      118,
      126,
      -1,
      82,
      85,
      -44,
      -49,
      -50,
      59,
      -29,
      47,
      16,
      58,
      17,
      -74,
      -67,
      28,
      42,
      -33,
      -73,
      -86,
      -43,
      119,
      -8,
      -104,
      2,
      44,
      -102,
      -93,
      70,
      -35,
      -103,
      101,
      -101,
      -89,
      43,
      -84,
      9,
      -127,
      22,
      39,
      -3,
      19,
      98,
      108,
      110,
      79,
      113,
      -32,
      -24,
      -78,
      -71,
      112,
      104,
      -38,
      -10,
      97,
      -28,
      -5,
      34,
      -14,
      -63,
      -18,
      -46,
      -112,
      12,
      -65,
      -77,
      -94,
      -15,
      81,
      51,
      -111,
      -21,
      -7,
      14,
      -17,
      107,
      49,
      -64,
      -42,
      31,
      -75,
      -57,
      106,
      -99,
      -72,
      84,
      -52,
      -80,
      115,
      121,
      50,
      45,
      127,
      4,
      -106,
      -2,
      -118,
      -20,
      -51,
      93,
      -34,
      114,
      67,
      29,
      24,
      72,
      -13,
      -115,
      -128,
      -61,
      78,
      66,
      -41,
      61,
      -100,
      -76
   };
   public static final byte[] byteFlagArray3 = new byte[512];
   public static final float factor5 = 0.21132487F;
   public static final float factor6 = 0.36602542F;
   public static final RecordNode4[] recordNode4Array = new RecordNode4[]{
      new RecordNode4(1, 1, 0),
      new RecordNode4(-1, 1, 0),
      new RecordNode4(1, -1, 0),
      new RecordNode4(-1, -1, 0),
      new RecordNode4(1, 0, 1),
      new RecordNode4(-1, 0, 1),
      new RecordNode4(1, 0, -1),
      new RecordNode4(-1, 0, -1),
      new RecordNode4(0, 1, 1),
      new RecordNode4(0, -1, 1),
      new RecordNode4(0, 1, -1),
      new RecordNode4(0, -1, -1)
   };

   private static float computeFactor(RecordNode16 recordNode, float posX, float posX2, float posY, float posZ) {
      return (float)recordNode.byteFlag * posX + (float)recordNode.byteFlag4 * posX2 + (float)recordNode.byteFlag3 * posY + (float)recordNode.byteFlag2 * posZ;
   }

   private static float computeFactor(RecordNode4 recordNode, float posX, float posY, float posZ) {
      return (float)recordNode.byteFlag2 * posX + (float)recordNode.byteFlag3 * posY + (float)recordNode.byteFlag * posZ;
   }

   public static float computeFactor(float posX, float posY, float posZ) {
      float factor7 = (posX + posY + posZ) * 0.33333334F;
      int count = computeCount(posX + factor7);
      int count2 = computeCount(posY + factor7);
      int count3 = computeCount(posZ + factor7);
      float factor8 = (float)(count + count2 + count3) * 0.16666667F;
      float factor9 = (float)count - factor8;
      float factor10 = (float)count2 - factor8;
      float factor11 = (float)count3 - factor8;
      float factor12 = posX - factor9;
      float factor13 = posY - factor10;
      float factor14 = posZ - factor11;
      byte byteValue;
      byte byteValue2;
      byte byteValue3;
      byte byteValue4;
      byte byteValue5;
      byte byteValue6;
      if (factor12 >= factor13) {
         if (factor13 >= factor14) {
            byteValue = 1;
            byteValue2 = 0;
            byteValue3 = 0;
            byteValue4 = 1;
            byteValue5 = 1;
            byteValue6 = 0;
         } else if (factor12 >= factor14) {
            byteValue = 1;
            byteValue2 = 0;
            byteValue3 = 0;
            byteValue4 = 1;
            byteValue5 = 0;
            byteValue6 = 1;
         } else {
            byteValue = 0;
            byteValue2 = 0;
            byteValue3 = 1;
            byteValue4 = 1;
            byteValue5 = 0;
            byteValue6 = 1;
         }
      } else if (factor13 < factor14) {
         byteValue = 0;
         byteValue2 = 0;
         byteValue3 = 1;
         byteValue4 = 0;
         byteValue5 = 1;
         byteValue6 = 1;
      } else if (factor12 < factor14) {
         byteValue = 0;
         byteValue2 = 1;
         byteValue3 = 0;
         byteValue4 = 0;
         byteValue5 = 1;
         byteValue6 = 1;
      } else {
         byteValue = 0;
         byteValue2 = 1;
         byteValue3 = 0;
         byteValue4 = 1;
         byteValue5 = 1;
         byteValue6 = 0;
      }

      float factor15 = factor12 - (float)byteValue + 0.16666667F;
      float factor16 = factor13 - (float)byteValue2 + 0.16666667F;
      float factor17 = factor14 - (float)byteValue3 + 0.16666667F;
      float factor18 = factor12 - (float)byteValue4 + 0.33333334F;
      float factor19 = factor13 - (float)byteValue5 + 0.33333334F;
      float factor20 = factor14 - (float)byteValue6 + 0.33333334F;
      float factor21 = factor12 - 1.0F + 0.5F;
      float factor22 = factor13 - 1.0F + 0.5F;
      float factor23 = factor14 - 1.0F + 0.5F;
      int count4 = count & 0xFF;
      int count5 = count2 & 0xFF;
      int count6 = count3 & 0xFF;
      int count7 = byteFlagArray[count4 + byteFlagArray3[count5 + byteFlagArray3[count6] & 0xFF] & 0xFF] & 255;
      int count8 = byteFlagArray[count4 + byteValue + byteFlagArray3[count5 + byteValue2 + byteFlagArray3[count6 + byteValue3] & 0xFF] & 0xFF] & 255;
      int count9 = byteFlagArray[count4 + byteValue4 + byteFlagArray3[count5 + byteValue5 + byteFlagArray3[count6 + byteValue6] & 0xFF] & 0xFF] & 255;
      int count10 = byteFlagArray[count4 + 1 + byteFlagArray3[count5 + 1 + byteFlagArray3[count6 + 1] & 0xFF] & 0xFF] & 255;
      float factor24 = 0.6F - factor12 * factor12 - factor13 * factor13 - factor14 * factor14;
      float factor25;
      if (factor24 < 0.0F) {
         factor25 = 0.0F;
      } else {
         float factor26 = factor24 * factor24;
         factor25 = factor26 * factor26 * computeFactor(recordNode4Array[count7], factor12, factor13, factor14);
      }

      float factor27 = 0.6F - factor15 * factor15 - factor16 * factor16 - factor17 * factor17;
      float factor28;
      if (factor27 < 0.0F) {
         factor28 = 0.0F;
      } else {
         float factor29 = factor27 * factor27;
         factor28 = factor29 * factor29 * computeFactor(recordNode4Array[count8], factor15, factor16, factor17);
      }

      float factor30 = 0.6F - factor18 * factor18 - factor19 * factor19 - factor20 * factor20;
      float factor31;
      if (factor30 < 0.0F) {
         factor31 = 0.0F;
      } else {
         float factor32 = factor30 * factor30;
         factor31 = factor32 * factor32 * computeFactor(recordNode4Array[count9], factor18, factor19, factor20);
      }

      float factor33 = 0.6F - factor21 * factor21 - factor22 * factor22 - factor23 * factor23;
      float factor34;
      if (factor33 < 0.0F) {
         factor34 = 0.0F;
      } else {
         float factor35 = factor33 * factor33;
         factor34 = factor35 * factor35 * computeFactor(recordNode4Array[count10], factor21, factor22, factor23);
      }

      return 32.0F * (factor25 + factor28 + factor31 + factor34);
   }

   static {
      String[] textArray = new String[3];

      for (int index = 0; index < 512; index++) {
         byteFlagArray3[index] = byteFlagArray2[index & 0xFF];
         byteFlagArray[index] = (byte)((byteFlagArray3[index] & 255) % 12);
      }
   }

   public static float computeFactor(float factor7, float factor8) {
      float factor9 = (factor7 + factor8) * 0.36602542F;
      int count = computeCount(factor7 + factor9);
      int count2 = computeCount(factor8 + factor9);
      float factor10 = (float)(count + count2) * 0.21132487F;
      float factor11 = (float)count - factor10;
      float factor12 = (float)count2 - factor10;
      float factor13 = factor7 - factor11;
      float factor14 = factor8 - factor12;
      byte byteValue;
      byte byteValue2;
      if (factor13 > factor14) {
         byteValue = 1;
         byteValue2 = 0;
      } else {
         byteValue = 0;
         byteValue2 = 1;
      }

      float factor15 = factor13 - (float)byteValue + 0.21132487F;
      float factor16 = factor14 - (float)byteValue2 + 0.21132487F;
      float factor17 = factor13 - 1.0F + 0.42264974F;
      float factor18 = factor14 - 1.0F + 0.42264974F;
      int count3 = count & 0xFF;
      int count4 = count2 & 0xFF;
      int count5 = byteFlagArray[count3 + byteFlagArray3[count4] & 0xFF] & 255;
      int count6 = byteFlagArray[count3 + byteValue + byteFlagArray3[count4 + byteValue2] & 0xFF] & 255;
      int count7 = byteFlagArray[count3 + 1 + byteFlagArray3[count4 + 1] & 0xFF] & 255;
      float factor19 = 0.5F - factor13 * factor13 - factor14 * factor14;
      float factor20;
      if (factor19 < 0.0F) {
         factor20 = 0.0F;
      } else {
         float factor21 = factor19 * factor19;
         factor20 = factor21 * factor21 * computeFactor(recordNode4Array[count5], factor13, factor14);
      }

      float factor22 = 0.5F - factor15 * factor15 - factor16 * factor16;
      float factor23;
      if (factor22 < 0.0F) {
         factor23 = 0.0F;
      } else {
         float factor24 = factor22 * factor22;
         factor23 = factor24 * factor24 * computeFactor(recordNode4Array[count6], factor15, factor16);
      }

      float factor25 = 0.5F - factor17 * factor17 - factor18 * factor18;
      float factor26;
      if (factor25 < 0.0F) {
         factor26 = 0.0F;
      } else {
         float factor27 = factor25 * factor25;
         factor26 = factor27 * factor27 * computeFactor(recordNode4Array[count7], factor17, factor18);
      }

      return 70.0F * (factor20 + factor23 + factor26);
   }

   private static float computeFactor(RecordNode4 recordNode, float factor7, float factor8) {
      return (float)recordNode.byteFlag2 * factor7 + (float)recordNode.byteFlag3 * factor8;
   }

   private static int computeCount(float factor7) {
      int count = (int)factor7;
      return factor7 < (float)count ? count - 1 : count;
   }

   public static float computeFactor(float posX, float posX2, float posY, float posZ) {
      float factor7 = (posX + posX2 + posY + posZ) * 0.309017F;
      int count = computeCount(posX + factor7);
      int count2 = computeCount(posX2 + factor7);
      int count3 = computeCount(posY + factor7);
      int count4 = computeCount(posZ + factor7);
      float factor8 = (float)(count + count2 + count3 + count4) * 0.1381966F;
      float factor9 = (float)count - factor8;
      float factor10 = (float)count2 - factor8;
      float factor11 = (float)count3 - factor8;
      float factor12 = (float)count4 - factor8;
      float factor13 = posX - factor9;
      float factor14 = posX2 - factor10;
      float factor15 = posY - factor11;
      float factor16 = posZ - factor12;
      int count5 = 0;
      int count6 = 0;
      int count7 = 0;
      int count8 = 0;
      if (factor13 > factor14) {
         count5++;
      } else {
         count6++;
      }

      if (factor13 > factor15) {
         count5++;
      } else {
         count7++;
      }

      if (factor13 > factor16) {
         count5++;
      } else {
         count8++;
      }

      if (factor14 > factor15) {
         count6++;
      } else {
         count7++;
      }

      if (factor14 > factor16) {
         count6++;
      } else {
         count8++;
      }

      if (factor15 > factor16) {
         count7++;
      } else {
         count8++;
      }

      int count9 = count5 >= 3 ? 1 : 0;
      int count10 = count6 >= 3 ? 1 : 0;
      int count11 = count7 >= 3 ? 1 : 0;
      int count12 = count8 >= 3 ? 1 : 0;
      int count13 = count5 >= 2 ? 1 : 0;
      int count14 = count6 >= 2 ? 1 : 0;
      int count15 = count7 >= 2 ? 1 : 0;
      int count16 = count8 >= 2 ? 1 : 0;
      int count17 = count5 >= 1 ? 1 : 0;
      int count18 = count6 >= 1 ? 1 : 0;
      int count19 = count7 >= 1 ? 1 : 0;
      int count20 = count8 >= 1 ? 1 : 0;
      float factor17 = factor13 - (float)count9 + 0.1381966F;
      float factor18 = factor14 - (float)count10 + 0.1381966F;
      float factor19 = factor15 - (float)count11 + 0.1381966F;
      float factor20 = factor16 - (float)count12 + 0.1381966F;
      float factor21 = factor13 - (float)count13 + 0.2763932F;
      float factor22 = factor14 - (float)count14 + 0.2763932F;
      float factor23 = factor15 - (float)count15 + 0.2763932F;
      float factor24 = factor16 - (float)count16 + 0.2763932F;
      float factor25 = factor13 - (float)count17 + 0.41458982F;
      float factor26 = factor14 - (float)count18 + 0.41458982F;
      float factor27 = factor15 - (float)count19 + 0.41458982F;
      float factor28 = factor16 - (float)count20 + 0.41458982F;
      float factor29 = factor13 - 1.0F + 0.5527864F;
      float factor30 = factor14 - 1.0F + 0.5527864F;
      float factor31 = factor15 - 1.0F + 0.5527864F;
      float factor32 = factor16 - 1.0F + 0.5527864F;
      int count21 = count & 0xFF;
      int count22 = count2 & 0xFF;
      int count23 = count3 & 0xFF;
      int count24 = count4 & 0xFF;
      int count25 = (byteFlagArray3[count21 + byteFlagArray3[count22 + byteFlagArray3[count23 + byteFlagArray3[count24] & 0xFF] & 0xFF] & 0xFF] & 255) % 32;
      int count26 = (
            byteFlagArray3[count21 + count9 + byteFlagArray3[count22 + count10 + byteFlagArray3[count23 + count11 + byteFlagArray3[count24 + count12] & 0xFF] & 0xFF] & 0xFF]
               & 255
         )
         % 32;
      int count27 = (
            byteFlagArray3[count21 + count13 + byteFlagArray3[count22 + count14 + byteFlagArray3[count23 + count15 + byteFlagArray3[count24 + count16] & 0xFF] & 0xFF] & 0xFF]
               & 255
         )
         % 32;
      int count28 = (
            byteFlagArray3[count21 + count17 + byteFlagArray3[count22 + count18 + byteFlagArray3[count23 + count19 + byteFlagArray3[count24 + count20] & 0xFF] & 0xFF] & 0xFF]
               & 255
         )
         % 32;
      int count29 = (byteFlagArray3[count21 + 1 + byteFlagArray3[count22 + 1 + byteFlagArray3[count23 + 1 + byteFlagArray3[count24 + 1] & 0xFF] & 0xFF] & 0xFF] & 255)
         % 32;
      float factor33 = 0.6F - factor13 * factor13 - factor14 * factor14 - factor15 * factor15 - factor16 * factor16;
      float factor34;
      if (factor33 < 0.0F) {
         factor34 = 0.0F;
      } else {
         float factor35 = factor33 * factor33;
         factor34 = factor35 * factor35 * computeFactor(recordNode16Array[count25], factor13, factor14, factor15, factor16);
      }

      float factor36 = 0.6F - factor17 * factor17 - factor18 * factor18 - factor19 * factor19 - factor20 * factor20;
      float factor37;
      if (factor36 < 0.0F) {
         factor37 = 0.0F;
      } else {
         float factor38 = factor36 * factor36;
         factor37 = factor38 * factor38 * computeFactor(recordNode16Array[count26], factor17, factor18, factor19, factor20);
      }

      float factor39 = 0.6F - factor21 * factor21 - factor22 * factor22 - factor23 * factor23 - factor24 * factor24;
      float factor40;
      if (factor39 < 0.0F) {
         factor40 = 0.0F;
      } else {
         float factor41 = factor39 * factor39;
         factor40 = factor41 * factor41 * computeFactor(recordNode16Array[count27], factor21, factor22, factor23, factor24);
      }

      float factor42 = 0.6F - factor25 * factor25 - factor26 * factor26 - factor27 * factor27 - factor28 * factor28;
      float factor43;
      if (factor42 < 0.0F) {
         factor43 = 0.0F;
      } else {
         float factor44 = factor42 * factor42;
         factor43 = factor44 * factor44 * computeFactor(recordNode16Array[count28], factor25, factor26, factor27, factor28);
      }

      float factor45 = 0.6F - factor29 * factor29 - factor30 * factor30 - factor31 * factor31 - factor32 * factor32;
      float factor46;
      if (factor45 < 0.0F) {
         factor46 = 0.0F;
      } else {
         float factor47 = factor45 * factor45;
         factor46 = factor47 * factor47 * computeFactor(recordNode16Array[count29], factor29, factor30, factor31, factor32);
      }

      return 27.0F * (factor34 + factor37 + factor40 + factor43 + factor46);
   }
}
