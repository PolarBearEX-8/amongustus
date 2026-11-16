package client.util;

public class ManagedLongEntry {
   public final int count2;
   public int count3;
   public long timestamp;
   public final int[] countArray3;
   public int[] countArray2;
   public final int count4;
   public int count5;
   public final int count = 64;
   public byte[] byteFlagArray;
   public long[] timestampArray;
   public final long[] timestampArray2;

   public long getTimestamp() {
      int count6 = 0;
      long value = 0L;
      int count7 = 0;
      int count8 = 0;
      int count9 = 0;
      int count10 = 0;
      long value2 = 0L;
      long value3 = 0L;
      long value4 = 0L;
      long value5 = 0L;
      int count11 = 0;
      long value6 = 0L;
      int count12 = 0;
      int count13 = 0;
      int count14 = 0;
      long value7 = 0L;
      long value8 = 0L;
      long value9 = 0L;
      long value10 = 0L;
      int count15 = 0;
      int count16 = 0;
      byte[] entry = null;
      byte[] entry2 = null;
      long value11 = 0L;
      int count17 = 0;
      long value12 = 0L;
      int count18 = 0;
      int count19 = 0;
      count6 = this.count5;
      value = this.timestampArray[this.count5];
      count7 = 0;

      do {
         count8 = this.count5;
         count9 = (this.count5 + 1) % 64;
         count10 = this.countArray2[this.count3 % 256];
         value2 = this.timestampArray[count8];
         value3 = this.timestampArray[count9];
         value4 = this.timestampArray[count10];
         value2 = (value2 << this.countArray3[0] | value2 >>> 64 - this.countArray3[0]) ^ value2 >>> this.countArray3[1];
         value3 = (value3 << this.countArray3[2] | value3 >>> 64 - this.countArray3[2]) + (value3 >>> this.countArray3[3]);
         value4 = (value4 << this.countArray3[4] | value4 >>> 64 - this.countArray3[4]) ^ value4 >>> this.countArray3[5];
         value5 = value2 ^ value3 ^ value4;
         value5 *= this.timestampArray2[this.count3 % this.timestampArray2.length];
         value5 ^= value5 >>> this.countArray3[6];
         this.timestampArray[count8] = value5;
         this.timestampArray[count10] = value2 + value3 + (long)(this.byteFlagArray[this.count3 % 32] & 255);
         this.count5 = (this.count5 + this.countArray2[this.count5 % 256] % 7 + 1) % 64;
         this.count3++;
         if (++count7 > 5 && (this.timestampArray[this.count5] & 31L) == 0L) {
            break;
         }

         if ((this.timestampArray[this.count5] & 7L) == 0L) {
            count11 = 0;
            value6 = this.timestampArray[this.count5];

            do {
               count12 = this.count5;
               count13 = (this.count5 + 1) % 64;
               count14 = this.countArray2[this.count3 % 256];
               value7 = this.timestampArray[count12];
               value8 = this.timestampArray[count13];
               value9 = this.timestampArray[count14];
               value7 = (value7 << this.countArray3[0] | value7 >>> 64 - this.countArray3[0]) ^ value7 >>> this.countArray3[1];
               value8 = (value8 << this.countArray3[2] | value8 >>> 64 - this.countArray3[2]) + (value8 >>> this.countArray3[3]);
               value9 = (value9 << this.countArray3[4] | value9 >>> 64 - this.countArray3[4]) ^ value9 >>> this.countArray3[5];
               value10 = value7 ^ value8 ^ value9;
               value10 *= this.timestampArray2[this.count3 % this.timestampArray2.length];
               value10 ^= value10 >>> this.countArray3[6];
               this.timestampArray[count12] = value10;
               this.timestampArray[count14] = value7 + value8 + (long)(this.byteFlagArray[this.count3 % 32] & 255);
               this.count5 = (this.count5 + this.countArray2[this.count5 % 256] % 7 + 1) % 64;
               this.count3++;
               count11++;
               count7++;
               if ((this.timestampArray[this.count5] & 3L) == 0L) {
                  count15 = this.countArray2[this.count5 * 5 % 256];
                  this.timestampArray[count15] = this.timestampArray[count15] ^ (this.timestampArray[this.count5] << 11 | this.timestampArray[this.count5] >>> 53);
               }
            } while (
               (count11 < 3 || (this.timestampArray[this.count5] & 15L) != (value6 & 15L))
                  && (count11 <= 2 || (this.timestampArray[this.count5] & 31L) != 31L)
                  && count11 <= 10
            );
         }

         if (count7 > 15 && (this.timestampArray[this.count5] & 15L) == 10L) {
            count11 = 0;

            do {
               count16 = this.countArray2[(this.count5 + count11) % 256];
               this.timestampArray[count16] = this.timestampArray[count16] ^ this.timestampArray[(count16 + 1) % 64];
               count11++;
               if ((this.timestampArray[count16] & 3L) == 3L && count11 > 2) {
                  break;
               }
            } while (count11 < 5);
         }
      } while ((count7 <= 20 || this.count5 != count6) && (count7 <= 30 || (this.timestampArray[this.count5] & 255L) != (value & 255L)) && count7 <= 100);

      entry = new byte[32];

      for (int index = 0; index < entry.length; index++) {
         entry[index] = (byte)((int)(this.timestampArray[index % 64] & 255L));
      }

      entry2 = entry;
      value11 = 0L;

      for (int index2 = 0; index2 < 8; index2++) {
         count17 = (this.count5 + index2 * 7) % 64;
         value12 = this.timestampArray[count17];
         count18 = index2 * 4 % entry2.length;
         count19 = entry2[count18] & 255
            | (entry2[(count18 + 1) % entry2.length] & 255) << 8
            | (entry2[(count18 + 2) % entry2.length] & 255) << 16
            | (entry2[(count18 + 3) % entry2.length] & 255) << 24;
         value12 ^= (long)count19;
         value12 = value12 << 11 + index2 % 7 | value12 >>> 64 - (11 + index2 % 7);
         value12 *= this.timestampArray2[index2 % this.timestampArray2.length];
         value12 ^= value12 >>> 7 + index2 % 11;
         value11 = (value11 << 5 + index2 % 8 | value11 >>> 64 - (5 + index2 % 8)) ^ value12;
      }

      value11 ^= value11 >>> 27;
      value11 *= 0x3c79ac492ba7b653L;
      value11 ^= value11 >>> 33;
      value11 *= 0x1c69b3f74ac4ae35L;
      value11 ^= value11 >>> 27;
      this.timestamp = value11;
      return value11;
   }

   public ManagedLongEntry() {
        this(0x28071102ac663eceL, 0x105793d17003L);
      
   }

   public ManagedLongEntry(long value, long value2) {
      this.count2 = 256;
      this.count4 = 32;
      this.timestampArray2 = new long[]{
         -0x61c8864680b583ebL,
         -0x40a7b892e31b1a47L,
         -0x6b2fb644ecceee15L,
         -0x714bb57897a7eaefL,
         -0x24b0f46e8a51de9bL,
         -0x441fa9ccfc5b9ea1L,
         0x43ec7231afe45587L,
         0x23957ab6aaf9845dL
      };
      this.countArray3 = new int[]{11, 23, 31, 37, 41, 13, 19, 29};
      this.timestampArray = new long[64];
      this.countArray2 = new int[256];
      this.byteFlagArray = new byte[32];
      this.count5 = 0;
      this.count3 = 0;
      long value3 = value;

      for (int index = 0; index < 64; index++) {
         long keySalt = value3 ^ value3 >>> 12;
         long keySalt2 = keySalt ^ keySalt << 25;
         long keySalt3 = keySalt2 ^ keySalt2 >>> 27;
         this.timestampArray[index] = keySalt3 * this.timestampArray2[index % this.timestampArray2.length];
         value3 = this.timestampArray[index];
      }

      for (int index2 = 0; index2 < 256; index2++) {
         this.countArray2[index2] = (int)Math.abs(value3 % 64L);
         value3 = value3 * 25214903917L + 11L & 281474976710655L;
      }

      for (int index3 = 0; index3 < 32; index3++) {
         this.byteFlagArray[index3] = (byte)((int)(value3 & 255L));
         value3 = value3 * 25214903917L + 11L & 281474976710655L;
      }

      for (int index4 = 0; index4 < 7; index4++) {
         int count6 = this.count5;
         int count7 = (this.count5 + 1) % 64;
         int count8 = this.countArray2[this.count3 % 256];
         long value4 = this.timestampArray[count6];
         long value5 = this.timestampArray[count7];
         long value6 = this.timestampArray[count8];
         long keySalt4 = (value4 << this.countArray3[0] | value4 >>> 64 - this.countArray3[0]) ^ value4 >>> this.countArray3[1];
         long value7 = (value5 << this.countArray3[2] | value5 >>> 64 - this.countArray3[2]) + (value5 >>> this.countArray3[3]);
         long keySalt5 = (value6 << this.countArray3[4] | value6 >>> 64 - this.countArray3[4]) ^ value6 >>> this.countArray3[5];
         long value8 = (keySalt4 ^ value7 ^ keySalt5) * this.timestampArray2[this.count3 % this.timestampArray2.length];
         long keySalt6 = value8 ^ value8 >>> this.countArray3[6];
         this.timestampArray[count6] = keySalt6;
         this.timestampArray[count8] = keySalt4 + value7 + (long)(this.byteFlagArray[this.count3 % 32] & 255);
         this.count5 = (this.count5 + this.countArray2[this.count5 % 256] % 7 + 1) % 64;
         this.count3++;
      }
   }
}
