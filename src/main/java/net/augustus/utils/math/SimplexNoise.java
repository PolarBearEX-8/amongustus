package net.augustus.utils.math;

public class SimplexNoise {
   private static final byte[] PERMUTATION_SEED = new byte[]{
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
   private static final float F2 = 0.36602542F;
   private static final byte[] PERM_MOD_12 = new byte[512];
   private static final float F4 = 0.309017F;
   private static final SimplexGrad4[] GRAD4 = new SimplexGrad4[]{
      new SimplexGrad4(0, 1, 1, 1),
      new SimplexGrad4(0, 1, 1, -1),
      new SimplexGrad4(0, 1, -1, 1),
      new SimplexGrad4(0, 1, -1, -1),
      new SimplexGrad4(0, -1, 1, 1),
      new SimplexGrad4(0, -1, 1, -1),
      new SimplexGrad4(0, -1, -1, 1),
      new SimplexGrad4(0, -1, -1, -1),
      new SimplexGrad4(1, 0, 1, 1),
      new SimplexGrad4(1, 0, 1, -1),
      new SimplexGrad4(1, 0, -1, 1),
      new SimplexGrad4(1, 0, -1, -1),
      new SimplexGrad4(-1, 0, 1, 1),
      new SimplexGrad4(-1, 0, 1, -1),
      new SimplexGrad4(-1, 0, -1, 1),
      new SimplexGrad4(-1, 0, -1, -1),
      new SimplexGrad4(1, 1, 0, 1),
      new SimplexGrad4(1, 1, 0, -1),
      new SimplexGrad4(1, -1, 0, 1),
      new SimplexGrad4(1, -1, 0, -1),
      new SimplexGrad4(-1, 1, 0, 1),
      new SimplexGrad4(-1, 1, 0, -1),
      new SimplexGrad4(-1, -1, 0, 1),
      new SimplexGrad4(-1, -1, 0, -1),
      new SimplexGrad4(1, 1, 1, 0),
      new SimplexGrad4(1, 1, -1, 0),
      new SimplexGrad4(1, -1, 1, 0),
      new SimplexGrad4(1, -1, -1, 0),
      new SimplexGrad4(-1, 1, 1, 0),
      new SimplexGrad4(-1, 1, -1, 0),
      new SimplexGrad4(-1, -1, 1, 0),
      new SimplexGrad4(-1, -1, -1, 0)
   };
   private static final byte[] PERM = new byte[512];
   private static final SimplexGrad3[] GRAD3 = new SimplexGrad3[]{
      new SimplexGrad3(1, 1, 0),
      new SimplexGrad3(-1, 1, 0),
      new SimplexGrad3(1, -1, 0),
      new SimplexGrad3(-1, -1, 0),
      new SimplexGrad3(1, 0, 1),
      new SimplexGrad3(-1, 0, 1),
      new SimplexGrad3(1, 0, -1),
      new SimplexGrad3(-1, 0, -1),
      new SimplexGrad3(0, 1, 1),
      new SimplexGrad3(0, -1, 1),
      new SimplexGrad3(0, 1, -1),
      new SimplexGrad3(0, -1, -1)
   };
   private static final float G3 = 0.16666667F;
   private static final float G2 = 0.21132487F;
   private static final float G4 = 0.1381966F;
   private static final float F3 = 0.33333334F;

   public static float noise(float f, float f2, float f3) {
      float f4 = (f + f2 + f3) * 0.33333334F;
      int n = fastFloor(f + f4);
      int n2 = fastFloor(f2 + f4);
      int n3 = fastFloor(f3 + f4);
      float f5 = (float)(n + n2 + n3) * 0.16666667F;
      float f6 = (float)n - f5;
      float f7 = (float)n2 - f5;
      float f8 = (float)n3 - f5;
      float f9 = f - f6;
      float f10 = f2 - f7;
      float f11 = f3 - f8;
      byte b;
      byte b2;
      byte b3;
      byte b4;
      byte b5;
      byte b6;
      if (f9 >= f10) {
         if (f10 >= f11) {
            b = 1;
            b2 = 0;
            b3 = 0;
            b4 = 1;
            b5 = 1;
            b6 = 0;
         } else if (f9 >= f11) {
            b = 1;
            b2 = 0;
            b3 = 0;
            b4 = 1;
            b5 = 0;
            b6 = 1;
         } else {
            b = 0;
            b2 = 0;
            b3 = 1;
            b4 = 1;
            b5 = 0;
            b6 = 1;
         }
      } else if (f10 < f11) {
         b = 0;
         b2 = 0;
         b3 = 1;
         b4 = 0;
         b5 = 1;
         b6 = 1;
      } else if (f9 < f11) {
         b = 0;
         b2 = 1;
         b3 = 0;
         b4 = 0;
         b5 = 1;
         b6 = 1;
      } else {
         b = 0;
         b2 = 1;
         b3 = 0;
         b4 = 1;
         b5 = 1;
         b6 = 0;
      }

      float f12 = f9 - (float)b + 0.16666667F;
      float f13 = f10 - (float)b2 + 0.16666667F;
      float f14 = f11 - (float)b3 + 0.16666667F;
      float f15 = f9 - (float)b4 + 0.33333334F;
      float f16 = f10 - (float)b5 + 0.33333334F;
      float f17 = f11 - (float)b6 + 0.33333334F;
      float f18 = f9 - 1.0F + 0.5F;
      float f19 = f10 - 1.0F + 0.5F;
      float f20 = f11 - 1.0F + 0.5F;
      int n4 = n & 0xFF;
      int n5 = n2 & 0xFF;
      int n6 = n3 & 0xFF;
      int n7 = PERM_MOD_12[n4 + PERM[n5 + PERM[n6] & 0xFF] & 0xFF] & 255;
      int n8 = PERM_MOD_12[n4 + b + PERM[n5 + b2 + PERM[n6 + b3] & 0xFF] & 0xFF] & 255;
      int n9 = PERM_MOD_12[n4 + b4 + PERM[n5 + b5 + PERM[n6 + b6] & 0xFF] & 0xFF] & 255;
      int n10 = PERM_MOD_12[n4 + 1 + PERM[n5 + 1 + PERM[n6 + 1] & 0xFF] & 0xFF] & 255;
      float f21 = 0.6F - f9 * f9 - f10 * f10 - f11 * f11;
      float f22;
      if (f21 < 0.0F) {
         f22 = 0.0F;
      } else {
         f21 *= f21;
         f22 = f21 * f21 * dot(GRAD3[n7], f9, f10, f11);
      }

      float f23 = 0.6F - f12 * f12 - f13 * f13 - f14 * f14;
      float f24;
      if (f23 < 0.0F) {
         f24 = 0.0F;
      } else {
         f23 *= f23;
         f24 = f23 * f23 * dot(GRAD3[n8], f12, f13, f14);
      }

      float f25 = 0.6F - f15 * f15 - f16 * f16 - f17 * f17;
      float f26;
      if (f25 < 0.0F) {
         f26 = 0.0F;
      } else {
         f25 *= f25;
         f26 = f25 * f25 * dot(GRAD3[n9], f15, f16, f17);
      }

      float f27 = 0.6F - f18 * f18 - f19 * f19 - f20 * f20;
      float f28;
      if (f27 < 0.0F) {
         f28 = 0.0F;
      } else {
         f27 *= f27;
         f28 = f27 * f27 * dot(GRAD3[n10], f18, f19, f20);
      }

      return 32.0F * (f22 + f24 + f26 + f28);
   }

   public static float noise(float f, float f2) {
      float f3 = (f + f2) * 0.36602542F;
      int n = fastFloor(f + f3);
      int n2 = fastFloor(f2 + f3);
      float f4 = (float)(n + n2) * 0.21132487F;
      float f5 = (float)n - f4;
      float f6 = (float)n2 - f4;
      float f7 = f - f5;
      float f8 = f2 - f6;
      byte b;
      byte b2;
      if (f7 > f8) {
         b = 1;
         b2 = 0;
      } else {
         b = 0;
         b2 = 1;
      }

      float f9 = f7 - (float)b + 0.21132487F;
      float f10 = f8 - (float)b2 + 0.21132487F;
      float f11 = f7 - 1.0F + 0.42264974F;
      float f12 = f8 - 1.0F + 0.42264974F;
      int n3 = n & 0xFF;
      int n4 = n2 & 0xFF;
      int n5 = PERM_MOD_12[n3 + PERM[n4] & 0xFF] & 255;
      int n6 = PERM_MOD_12[n3 + b + PERM[n4 + b2] & 0xFF] & 255;
      int n7 = PERM_MOD_12[n3 + 1 + PERM[n4 + 1] & 0xFF] & 255;
      float f13 = 0.5F - f7 * f7 - f8 * f8;
      float f14;
      if (f13 < 0.0F) {
         f14 = 0.0F;
      } else {
         f13 *= f13;
         f14 = f13 * f13 * dot(GRAD3[n5], f7, f8);
      }

      float f15 = 0.5F - f9 * f9 - f10 * f10;
      float f16;
      if (f15 < 0.0F) {
         f16 = 0.0F;
      } else {
         f15 *= f15;
         f16 = f15 * f15 * dot(GRAD3[n6], f9, f10);
      }

      float f17 = 0.5F - f11 * f11 - f12 * f12;
      float f18;
      if (f17 < 0.0F) {
         f18 = 0.0F;
      } else {
         f17 *= f17;
         f18 = f17 * f17 * dot(GRAD3[n7], f11, f12);
      }

      return 70.0F * (f14 + f16 + f18);
   }

   static {

      for (int i = 0; i < 512; i++) {
         PERM[i] = PERMUTATION_SEED[i & 0xFF];
         PERM_MOD_12[i] = (byte)((PERM[i] & 255) % 12);
      }
   }

   private static int fastFloor(float f) {
      int n = (int)f;
      return f < (float)n ? n - 1 : n;
   }

   private static float dot(SimplexGrad4 simplexGrad4, float f, float f2, float f3, float f4) {
      return (float)simplexGrad4.x * f + (float)simplexGrad4.y * f2 + (float)simplexGrad4.z * f3 + (float)simplexGrad4.w * f4;
   }

   private static float dot(SimplexGrad3 simplexGrad3, float f, float f2, float f3) {
      return (float)simplexGrad3.x * f + (float)simplexGrad3.y * f2 + (float)simplexGrad3.z * f3;
   }

   public static float noise(float f, float f2, float f3, float f4) {
      float f5 = (f + f2 + f3 + f4) * 0.309017F;
      int n = fastFloor(f + f5);
      int n2 = fastFloor(f2 + f5);
      int n3 = fastFloor(f3 + f5);
      int n4 = fastFloor(f4 + f5);
      float f6 = (float)(n + n2 + n3 + n4) * 0.1381966F;
      float f7 = (float)n - f6;
      float f8 = (float)n2 - f6;
      float f9 = (float)n3 - f6;
      float f10 = (float)n4 - f6;
      float f11 = f - f7;
      float f12 = f2 - f8;
      float f13 = f3 - f9;
      float f14 = f4 - f10;
      int n5 = 0;
      int n6 = 0;
      int n7 = 0;
      int n8 = 0;
      if (f11 > f12) {
         n5++;
      } else {
         n6++;
      }

      if (f11 > f13) {
         n5++;
      } else {
         n7++;
      }

      if (f11 > f14) {
         n5++;
      } else {
         n8++;
      }

      if (f12 > f13) {
         n6++;
      } else {
         n7++;
      }

      if (f12 > f14) {
         n6++;
      } else {
         n8++;
      }

      if (f13 > f14) {
         n7++;
      } else {
         n8++;
      }

      int n9 = n5 >= 3 ? 1 : 0;
      int n10 = n6 >= 3 ? 1 : 0;
      int n11 = n7 >= 3 ? 1 : 0;
      int n12 = n8 >= 3 ? 1 : 0;
      int n13 = n5 >= 2 ? 1 : 0;
      int n14 = n6 >= 2 ? 1 : 0;
      int n15 = n7 >= 2 ? 1 : 0;
      int n16 = n8 >= 2 ? 1 : 0;
      int n17 = n5 >= 1 ? 1 : 0;
      int n18 = n6 >= 1 ? 1 : 0;
      int n19 = n7 >= 1 ? 1 : 0;
      int n20 = n8 >= 1 ? 1 : 0;
      float f15 = f11 - (float)n9 + 0.1381966F;
      float f16 = f12 - (float)n10 + 0.1381966F;
      float f17 = f13 - (float)n11 + 0.1381966F;
      float f18 = f14 - (float)n12 + 0.1381966F;
      float f19 = f11 - (float)n13 + 0.2763932F;
      float f20 = f12 - (float)n14 + 0.2763932F;
      float f21 = f13 - (float)n15 + 0.2763932F;
      float f22 = f14 - (float)n16 + 0.2763932F;
      float f23 = f11 - (float)n17 + 0.41458982F;
      float f24 = f12 - (float)n18 + 0.41458982F;
      float f25 = f13 - (float)n19 + 0.41458982F;
      float f26 = f14 - (float)n20 + 0.41458982F;
      float f27 = f11 - 1.0F + 0.5527864F;
      float f28 = f12 - 1.0F + 0.5527864F;
      float f29 = f13 - 1.0F + 0.5527864F;
      float f30 = f14 - 1.0F + 0.5527864F;
      int n21 = n & 0xFF;
      int n22 = n2 & 0xFF;
      int n23 = n3 & 0xFF;
      int n24 = n4 & 0xFF;
      int n25 = (PERM[n21 + PERM[n22 + PERM[n23 + PERM[n24] & 0xFF] & 0xFF] & 0xFF] & 255) % 32;
      int n26 = (PERM[n21 + n9 + PERM[n22 + n10 + PERM[n23 + n11 + PERM[n24 + n12] & 0xFF] & 0xFF] & 0xFF] & 255) % 32;
      int n27 = (PERM[n21 + n13 + PERM[n22 + n14 + PERM[n23 + n15 + PERM[n24 + n16] & 0xFF] & 0xFF] & 0xFF] & 255) % 32;
      int n28 = (PERM[n21 + n17 + PERM[n22 + n18 + PERM[n23 + n19 + PERM[n24 + n20] & 0xFF] & 0xFF] & 0xFF] & 255) % 32;
      int n29 = (PERM[n21 + 1 + PERM[n22 + 1 + PERM[n23 + 1 + PERM[n24 + 1] & 0xFF] & 0xFF] & 0xFF] & 255) % 32;
      float f31 = 0.6F - f11 * f11 - f12 * f12 - f13 * f13 - f14 * f14;
      float f32;
      if (f31 < 0.0F) {
         f32 = 0.0F;
      } else {
         f31 *= f31;
         f32 = f31 * f31 * dot(GRAD4[n25], f11, f12, f13, f14);
      }

      float f33 = 0.6F - f15 * f15 - f16 * f16 - f17 * f17 - f18 * f18;
      float f34;
      if (f33 < 0.0F) {
         f34 = 0.0F;
      } else {
         f33 *= f33;
         f34 = f33 * f33 * dot(GRAD4[n26], f15, f16, f17, f18);
      }

      float f35 = 0.6F - f19 * f19 - f20 * f20 - f21 * f21 - f22 * f22;
      float f36;
      if (f35 < 0.0F) {
         f36 = 0.0F;
      } else {
         f35 *= f35;
         f36 = f35 * f35 * dot(GRAD4[n27], f19, f20, f21, f22);
      }

      float f37 = 0.6F - f23 * f23 - f24 * f24 - f25 * f25 - f26 * f26;
      float f38;
      if (f37 < 0.0F) {
         f38 = 0.0F;
      } else {
         f37 *= f37;
         f38 = f37 * f37 * dot(GRAD4[n28], f23, f24, f25, f26);
      }

      float f39 = 0.6F - f27 * f27 - f28 * f28 - f29 * f29 - f30 * f30;
      float f40;
      if (f39 < 0.0F) {
         f40 = 0.0F;
      } else {
         f39 *= f39;
         f40 = f39 * f39 * dot(GRAD4[n29], f27, f28, f29, f30);
      }

      return 27.0F * (f32 + f34 + f36 + f38 + f40);
   }

   private static float dot(SimplexGrad3 simplexGrad3, float f, float f2) {
      return (float)simplexGrad3.x * f + (float)simplexGrad3.y * f2;
   }
}
