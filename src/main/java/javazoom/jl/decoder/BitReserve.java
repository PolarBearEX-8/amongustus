package javazoom.jl.decoder;

public final class BitReserve {
   private static final int BUFSIZE = 32768;
   private static final int BUFSIZE_MASK = 32767;
   private int offset;
   private int totbit;
   private int buf_byte_idx;
   private final int[] buf = new int[32768];
   private int buf_bit_idx;

   public int getTotbit() {
      return this.totbit;
   }

   public void hputbuf(int n) {
      int offset2 = this.offset;
      this.buf[offset2++] = n & 128;
      this.buf[offset2++] = n & 64;
      this.buf[offset2++] = n & 32;
      this.buf[offset2++] = n & 16;
      this.buf[offset2++] = n & 8;
      this.buf[offset2++] = n & 4;
      this.buf[offset2++] = n & 2;
      this.buf[offset2++] = n & 1;
      if (offset2 == 32768) {
         this.offset = 0;
      } else {
         this.offset = offset2;
      }
   }

   public void rewindNbytes(int n) {
      int n2 = n << 3;
      this.totbit -= n2;
      this.buf_byte_idx -= n2;
      if (this.buf_byte_idx < 0) {
         this.buf_byte_idx += 32768;
      }
   }

   public int hget1bit() {
      this.totbit++;
      int n = this.buf[this.buf_byte_idx];
      this.buf_byte_idx = this.buf_byte_idx + 1 & 32767;
      return n;
   }

   public void rewindNbits(int n) {
      this.totbit -= n;
      this.buf_byte_idx -= n;
      if (this.buf_byte_idx < 0) {
         this.buf_byte_idx += 32768;
      }
   }

   public BitReserve() {
      this.offset = 0;
      this.totbit = 0;
      this.buf_byte_idx = 0;
   }

   public int hgetbits(int n) {
      this.totbit += n;
      int n2 = 0;
      int bufByteIdx = this.buf_byte_idx;
      if (bufByteIdx + n < 32768) {
         while (n-- > 0) {
            n2 <<= 1;
            n2 |= this.buf[bufByteIdx++] != 0 ? 1 : 0;
         }
      } else {
         while (n-- > 0) {
            n2 <<= 1;
            n2 |= this.buf[bufByteIdx] != 0 ? 1 : 0;
            bufByteIdx = bufByteIdx + 1 & 32767;
         }
      }

      this.buf_byte_idx = bufByteIdx;
      return n2;
   }
}
