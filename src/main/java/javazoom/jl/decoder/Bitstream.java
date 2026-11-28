package javazoom.jl.decoder;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public final class Bitstream implements BitstreamErrors {
   public static byte INITIAL_SYNC = 0;
   public static byte STRICT_SYNC = 1;
   private static final int BUFFER_INT_SIZE = 433;
   private final int[] framebuffer = new int[433];
   private int framesize;
   private byte[] frame_bytes = new byte[1732];
   private int wordpointer;
   private int bitindex;
   private int syncword;
   private int header_pos = 0;
   private boolean single_ch_mode;
   private final int[] bitmask = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071};
   private final PushbackInputStream source;
   private final Header header = new Header();
   private final byte[] syncbuf = new byte[4];
   private Crc16[] crc = new Crc16[1];
   private byte[] rawid3v2 = null;
   private boolean firstframe = true;

   public int get_bits(int n) {
      int n2 = 0;
      int n3 = this.bitindex + n;
      if (this.wordpointer < 0) {
         this.wordpointer = 0;
      }

      if (n3 <= 32) {
         n2 = this.framebuffer[this.wordpointer] >>> 32 - n3 & this.bitmask[n];
         if ((this.bitindex += n) == 32) {
            this.bitindex = 0;
            this.wordpointer++;
         }

         return n2;
      } else {
         int n4 = this.framebuffer[this.wordpointer] & 65535;
         this.wordpointer++;
         n2 = n4 << 16 & -65536 | (this.framebuffer[this.wordpointer] & -65536) >>> 16 & 65535;
         n2 >>>= 48 - n3;
         n2 &= this.bitmask[n];
         this.bitindex = n3 - 32;
         return n2;
      }
   }

   private Header readNextFrame() throws BitstreamException {
      if (this.framesize == -1) {
         this.nextFrame();
      }

      return this.header;
   }

   public void closeFrame() {
      this.framesize = -1;
      this.wordpointer = -1;
      this.bitindex = -1;
   }

   public InputStream getRawID3v2() {
      return this.rawid3v2 == null ? null : new ByteArrayInputStream(this.rawid3v2);
   }

   public int readBits(int n) {
      return this.get_bits(n);
   }

   private int readFully(byte[] bytes, int n, int n2) throws BitstreamException {
      int n3 = 0;

      try {
         while (n2 > 0) {
            int n4 = this.source.read(bytes, n, n2);
            if (n4 == -1) {
               while (n2-- > 0) {
                  bytes[n++] = 0;
               }
               break;
            }

            n3 += n4;
            n += n4;
            n2 -= n4;
         }

         return n3;
      } catch (IOException e) {
         throw this.newBitstreamException(258, e);
      }
   }

   private void nextFrame() throws BitstreamException {
      this.header.read_header(this, this.crc);
   }

   public void close() throws BitstreamException {
      try {
         this.source.close();
      } catch (IOException e) {
         throw this.newBitstreamException(258, e);
      }
   }

   public Bitstream(InputStream in) {
      if (in == null) {
         throw new NullPointerException("in");
      } else {
         BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
         this.loadID3v2(bufferedInputStream);
         this.firstframe = true;
         this.source = new PushbackInputStream(bufferedInputStream, 1732);
         this.closeFrame();
      }
   }

   private int readID3v2Header(InputStream in) throws IOException {
      byte[] bytes = new byte[4];
      int n = -10;
      in.read(bytes, 0, 3);
      if (bytes[0] == 73 && bytes[1] == 68 && bytes[2] == 51) {
         in.read(bytes, 0, 3);
         byte b = bytes[0];
         b = bytes[1];
         in.read(bytes, 0, 4);
         n = (bytes[0] << 21) + (bytes[1] << 14) + (bytes[2] << 7) + bytes[3];
      }

      return n + 10;
   }

   public int getHeaderPos() {
      return this.header_pos;
   }

   public void parse_frame() throws BitstreamException {
      int n = 0;
      byte[] frameBytes = this.frame_bytes;
      int framesize2 = this.framesize;

      for (int i = 0; i < framesize2; i += 4) {
         byte b = 0;
         byte b2 = 0;
         byte b3 = 0;
         byte b4 = 0;
         b = frameBytes[i];
         if (i + 1 < framesize2) {
            b2 = frameBytes[i + 1];
         }

         if (i + 2 < framesize2) {
            b3 = frameBytes[i + 2];
         }

         if (i + 3 < framesize2) {
            b4 = frameBytes[i + 3];
         }

         this.framebuffer[n++] = b << 24 & 0xFF000000 | b2 << 16 & 0xFF0000 | b3 << 8 & 0xFF00 | b4 & 255;
      }

      this.wordpointer = 0;
      this.bitindex = 0;
   }

   public boolean isSyncMark(int n, int n2, int n3) {
      boolean flag = false;
      if (n2 == INITIAL_SYNC) {
         flag = (n & -2097152) == -2097152;
      } else {
         flag = (n & -521216) == n3 && (n & 192) == 192 == this.single_ch_mode;
      }

      if (flag) {
         flag = (n >>> 10 & 3) != 3;
      }

      if (flag) {
         flag = (n >>> 17 & 3) != 0;
      }

      if (flag) {
         flag = (n >>> 19 & 3) != 1;
      }

      return flag;
   }

   public BitstreamException newBitstreamException(int n) {
      return new BitstreamException(n, null);
   }

   public void unreadFrame() throws BitstreamException {
      if (this.wordpointer == -1 && this.bitindex == -1 && this.framesize > 0) {
         try {
            this.source.unread(this.frame_bytes, 0, this.framesize);
         } catch (IOException e) {
            throw this.newBitstreamException(258);
         }
      }
   }

   public Header readFrame() throws BitstreamException {
      Header header2 = null;

      try {
         header2 = this.readNextFrame();
         if (this.firstframe) {
            header2.parseVBR(this.frame_bytes);
            this.firstframe = false;
         }
      } catch (BitstreamException e) {
         if (e.getErrorcode() == 261) {
            try {
               this.closeFrame();
               header2 = this.readNextFrame();
            } catch (BitstreamException e2) {
               if (e2.getErrorcode() != 260) {
                  throw this.newBitstreamException(e2.getErrorcode(), e2);
               }
            }
         } else if (e.getErrorcode() != 260) {
            throw this.newBitstreamException(e.getErrorcode(), e);
         }
      }

      return header2;
   }

   public int syncHeader(byte b) throws BitstreamException {
      if (this.readBytes(this.syncbuf, 0, 3) != 3) {
         throw this.newBitstreamException(260, null);
      } else {
         int n2 = this.syncbuf[0] << 16 & 0xFF0000 | this.syncbuf[1] << 8 & 0xFF00 | this.syncbuf[2] << 0 & 0xFF;

         boolean flag;
         do {
            n2 <<= 8;
            if (this.readBytes(this.syncbuf, 3, 1) != 1) {
               throw this.newBitstreamException(260, null);
            }

            n2 |= this.syncbuf[3] & 255;
            flag = this.isSyncMark(n2, b, this.syncword);
         } while (!flag);

         return n2;
      }
   }

   public boolean isSyncCurrentPosition(int n) throws BitstreamException {
      int n2 = this.readBytes(this.syncbuf, 0, 4);
      int n3 = this.syncbuf[0] << 24 & 0xFF000000 | this.syncbuf[1] << 16 & 0xFF0000 | this.syncbuf[2] << 8 & 0xFF00 | this.syncbuf[3] << 0 & 0xFF;

      try {
         this.source.unread(this.syncbuf, 0, n2);
      } catch (IOException e) {
      }

      boolean flag = false;
      switch (n2) {
         case 0:
            flag = true;
            break;
         case 4:
            flag = this.isSyncMark(n3, n, this.syncword);
      }

      return flag;
   }

   public int readCheckedBits(int n) {
      return this.get_bits(n);
   }

   public BitstreamException newBitstreamException(int n, Throwable t) {
      return new BitstreamException(n, t);
   }

   public void set_syncword(int n) {
      this.syncword = n & -193;
      this.single_ch_mode = (n & 192) == 192;
   }

   private void loadID3v2(InputStream in) {
      int n = -1;

      try {
         in.mark(10);
         n = this.readID3v2Header(in);
         this.header_pos = n;
      } catch (IOException e) {
      } finally {
         try {
            in.reset();
         } catch (IOException e2) {
         }
      }

      try {
         if (n > 0) {
            this.rawid3v2 = new byte[n];
            in.read(this.rawid3v2, 0, this.rawid3v2.length);
         }
      } catch (IOException e3) {
      }
   }

   private int readBytes(byte[] bytes, int n, int n2) throws BitstreamException {
      int n3 = 0;

      try {
         while (n2 > 0) {
            int n4 = this.source.read(bytes, n, n2);
            if (n4 == -1) {
               break;
            }

            n3 += n4;
            n += n4;
            n2 -= n4;
         }

         return n3;
      } catch (IOException e) {
         throw this.newBitstreamException(258, e);
      }
   }

   public int read_frame_data(int n) throws BitstreamException {
      int n2 = 0;
      n2 = this.readFully(this.frame_bytes, 0, n);
      this.framesize = n;
      this.wordpointer = -1;
      this.bitindex = -1;
      return n2;
   }
}
