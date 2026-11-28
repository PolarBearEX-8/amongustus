package net.augustus.config;

import java.util.Arrays;

/**
 * Pure-Java FIPS-197 AES-256-ECB cipher with PKCS#7 padding.
 * Avoids JCE 128-bit jurisdiction policy restrictions present in older Java 8 runtimes
 * (such as Mojang's bundled jre-legacy 1.8.0_51).
 */
public final class Aes256Ecb {
   private static final int BLOCK_SIZE = 16;
   private static final int ROUNDS = 14;

   private static final int[] SBOX = new int[]{
      0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76,
      0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0,
      0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15,
      0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75,
      0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84,
      0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF,
      0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8,
      0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
      0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73,
      0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB,
      0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79,
      0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08,
      0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A,
      0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E,
      0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF,
      0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
   };

   private static final int[] INV_SBOX = new int[256];
   private static final int[] RCON = new int[]{0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40};

   private static final int[] MUL2 = new int[256];
   private static final int[] MUL3 = new int[256];
   private static final int[] MUL9 = new int[256];
   private static final int[] MUL11 = new int[256];
   private static final int[] MUL13 = new int[256];
   private static final int[] MUL14 = new int[256];

   static {
      for (int i = 0; i < 256; i++) {
         INV_SBOX[SBOX[i]] = i;
         MUL2[i] = gmul(i, 2);
         MUL3[i] = gmul(i, 3);
         MUL9[i] = gmul(i, 9);
         MUL11[i] = gmul(i, 11);
         MUL13[i] = gmul(i, 13);
         MUL14[i] = gmul(i, 14);
      }
   }

   private final int[] roundKeys;

   public Aes256Ecb(byte[] key) {
      if (key == null || key.length != 32) {
         throw new IllegalArgumentException("AES-256 requires a 32-byte key");
      }
      this.roundKeys = expandKey(key);
   }

   private static int gmul(int a, int b) {
      int p = 0;
      for (int i = 0; i < 8; i++) {
         if ((b & 1) != 0) {
            p ^= a;
         }
         boolean hi = (a & 0x80) != 0;
         a = (a << 1) & 0xFF;
         if (hi) {
            a ^= 0x1B;
         }
         b >>>= 1;
      }
      return p;
   }

   private static int[] expandKey(byte[] key) {
      int[] w = new int[4 * (ROUNDS + 1)];
      for (int i = 0; i < 8; i++) {
         w[i] = ((key[4 * i] & 0xFF) << 24)
            | ((key[4 * i + 1] & 0xFF) << 16)
            | ((key[4 * i + 2] & 0xFF) << 8)
            | (key[4 * i + 3] & 0xFF);
      }
      for (int i = 8; i < w.length; i++) {
         int temp = w[i - 1];
         if (i % 8 == 0) {
            temp = subWord((temp << 8) | (temp >>> 24)) ^ (RCON[(i / 8) - 1] << 24);
         } else if (i % 8 == 4) {
            temp = subWord(temp);
         }
         w[i] = w[i - 8] ^ temp;
      }
      return w;
   }

   private static int subWord(int word) {
      return (SBOX[(word >>> 24) & 0xFF] << 24)
         | (SBOX[(word >>> 16) & 0xFF] << 16)
         | (SBOX[(word >>> 8) & 0xFF] << 8)
         | SBOX[word & 0xFF];
   }

   public byte[] encryptPkcs7(byte[] input, int offset, int length) {
      int padLen = BLOCK_SIZE - (length % BLOCK_SIZE);
      int total = length + padLen;
      byte[] out = new byte[total];
      byte[] block = new byte[BLOCK_SIZE];
      int fullBlocks = length / BLOCK_SIZE;
      for (int b = 0; b < fullBlocks; b++) {
         encryptBlock(input, offset + b * BLOCK_SIZE, out, b * BLOCK_SIZE);
      }
      int rem = length % BLOCK_SIZE;
      System.arraycopy(input, offset + fullBlocks * BLOCK_SIZE, block, 0, rem);
      Arrays.fill(block, rem, BLOCK_SIZE, (byte)padLen);
      encryptBlock(block, 0, out, fullBlocks * BLOCK_SIZE);
      return out;
   }

   public byte[] decryptPkcs7(byte[] input, int offset, int length) {
      if (length <= 0 || (length % BLOCK_SIZE) != 0) {
         throw new IllegalArgumentException("Invalid AES ciphertext length: " + length);
      }
      byte[] out = new byte[length];
      for (int pos = 0; pos < length; pos += BLOCK_SIZE) {
         decryptBlock(input, offset + pos, out, pos);
      }
      int pad = out[length - 1] & 0xFF;
      if (pad < 1 || pad > BLOCK_SIZE) {
         throw new IllegalArgumentException("Invalid PKCS#7 padding value: " + pad);
      }
      for (int i = length - pad; i < length; i++) {
         if ((out[i] & 0xFF) != pad) {
            throw new IllegalArgumentException("Corrupted PKCS#7 padding byte at offset " + i);
         }
      }
      return Arrays.copyOf(out, length - pad);
   }

   private void encryptBlock(byte[] in, int inOff, byte[] out, int outOff) {
      int[] s = new int[16];
      for (int i = 0; i < 16; i++) {
         s[i] = in[inOff + i] & 0xFF;
      }
      addRoundKey(s, 0);

      for (int round = 1; round < ROUNDS; round++) {
         for (int i = 0; i < 16; i++) {
            s[i] = SBOX[s[i]];
         }
         shiftRows(s);
         mixColumns(s);
         addRoundKey(s, round);
      }

      for (int i = 0; i < 16; i++) {
         s[i] = SBOX[s[i]];
      }
      shiftRows(s);
      addRoundKey(s, ROUNDS);

      for (int i = 0; i < 16; i++) {
         out[outOff + i] = (byte)s[i];
      }
   }

   private void decryptBlock(byte[] in, int inOff, byte[] out, int outOff) {
      int[] s = new int[16];
      for (int i = 0; i < 16; i++) {
         s[i] = in[inOff + i] & 0xFF;
      }
      addRoundKey(s, ROUNDS);

      for (int round = ROUNDS - 1; round >= 1; round--) {
         invShiftRows(s);
         for (int i = 0; i < 16; i++) {
            s[i] = INV_SBOX[s[i]];
         }
         addRoundKey(s, round);
         invMixColumns(s);
      }

      invShiftRows(s);
      for (int i = 0; i < 16; i++) {
         s[i] = INV_SBOX[s[i]];
      }
      addRoundKey(s, 0);

      for (int i = 0; i < 16; i++) {
         out[outOff + i] = (byte)s[i];
      }
   }

   private void addRoundKey(int[] s, int round) {
      int base = round * 4;
      for (int col = 0; col < 4; col++) {
         int rk = this.roundKeys[base + col];
         int idx = col * 4;
         s[idx] ^= (rk >>> 24) & 0xFF;
         s[idx + 1] ^= (rk >>> 16) & 0xFF;
         s[idx + 2] ^= (rk >>> 8) & 0xFF;
         s[idx + 3] ^= rk & 0xFF;
      }
   }

   private static void shiftRows(int[] s) {
      int t = s[1]; s[1] = s[5]; s[5] = s[9]; s[9] = s[13]; s[13] = t;
      t = s[2]; s[2] = s[10]; s[10] = t;
      t = s[6]; s[6] = s[14]; s[14] = t;
      t = s[15]; s[15] = s[11]; s[11] = s[7]; s[7] = s[3]; s[3] = t;
   }

   private static void invShiftRows(int[] s) {
      int t = s[13]; s[13] = s[9]; s[9] = s[5]; s[5] = s[1]; s[1] = t;
      t = s[2]; s[2] = s[10]; s[10] = t;
      t = s[6]; s[6] = s[14]; s[14] = t;
      t = s[3]; s[3] = s[7]; s[7] = s[11]; s[11] = s[15]; s[15] = t;
   }

   private static void mixColumns(int[] s) {
      for (int col = 0; col < 4; col++) {
         int i = col * 4;
         int a0 = s[i], a1 = s[i + 1], a2 = s[i + 2], a3 = s[i + 3];
         s[i]     = MUL2[a0] ^ MUL3[a1] ^ a2 ^ a3;
         s[i + 1] = a0 ^ MUL2[a1] ^ MUL3[a2] ^ a3;
         s[i + 2] = a0 ^ a1 ^ MUL2[a2] ^ MUL3[a3];
         s[i + 3] = MUL3[a0] ^ a1 ^ a2 ^ MUL2[a3];
      }
   }

   private static void invMixColumns(int[] s) {
      for (int col = 0; col < 4; col++) {
         int i = col * 4;
         int a0 = s[i], a1 = s[i + 1], a2 = s[i + 2], a3 = s[i + 3];
         s[i]     = MUL14[a0] ^ MUL11[a1] ^ MUL13[a2] ^ MUL9[a3];
         s[i + 1] = MUL9[a0] ^ MUL14[a1] ^ MUL11[a2] ^ MUL13[a3];
         s[i + 2] = MUL13[a0] ^ MUL9[a1] ^ MUL14[a2] ^ MUL11[a3];
         s[i + 3] = MUL11[a0] ^ MUL13[a1] ^ MUL9[a2] ^ MUL14[a3];
      }
   }
}
