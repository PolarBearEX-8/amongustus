package javazoom.jl.converter;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RiffFile {
   public static final int DDC_SUCCESS = 0;
   public static final int DDC_FAILURE = 1;
   public static final int DDC_OUT_OF_MEMORY = 2;
   public static final int DDC_FILE_ERROR = 3;
   public static final int DDC_INVALID_CALL = 4;
   public static final int DDC_USER_ABORT = 5;
   public static final int DDC_INVALID_FILE = 6;
   public static final int RFM_UNKNOWN = 0;
   public static final int RFM_WRITE = 1;
   public static final int RFM_READ = 2;
   private RiffChunkHeader riff_header;
   public int fmode;
   public RandomAccessFile file = null;

   public int Write4(int n, int n2) {
      short s = (short)(n >>> 16 & 65535);
      short s2 = (short)(n & 65535);
      int n3 = ((short)(s2 >>> 8 & 0xFF | s2 << 8 & 0xFF00)) << 16 & -65536 | ((short)(s >>> 8 & 0xFF | s << 8 & 0xFF00)) & '\uffff';
      if (this.fmode != 1) {
         return 4;
      } else {
         try {
            this.file.writeInt(n3);
            this.fmode = 1;
         } catch (IOException e) {
            return 3;
         }

         this.riff_header.ckSize += n2;
         return 0;
      }
   }

   public int Write(RiffChunkHeader riffChunkHeader, int n) {
      byte[] bytes = new byte[8];
      bytes[0] = (byte)(riffChunkHeader.ckID >>> 24 & 0xFF);
      bytes[1] = (byte)(riffChunkHeader.ckID >>> 16 & 0xFF);
      bytes[2] = (byte)(riffChunkHeader.ckID >>> 8 & 0xFF);
      bytes[3] = (byte)(riffChunkHeader.ckID & 0xFF);
      byte b = (byte)(riffChunkHeader.ckSize >>> 24 & 0xFF);
      byte b2 = (byte)(riffChunkHeader.ckSize >>> 16 & 0xFF);
      byte b3 = (byte)(riffChunkHeader.ckSize >>> 8 & 0xFF);
      bytes[4] = (byte)(riffChunkHeader.ckSize & 0xFF);
      bytes[5] = b3;
      bytes[6] = b2;
      bytes[7] = b;
      if (this.fmode != 1) {
         return 4;
      } else {
         try {
            this.file.write(bytes, 0, n);
            this.fmode = 1;
         } catch (IOException e) {
            return 3;
         }

         this.riff_header.ckSize += n;
         return 0;
      }
   }

   public int Backpatch(long l, byte[] bytes, int n) {
      if (this.file == null) {
         return 4;
      } else {
         try {
            this.file.seek(l);
         } catch (IOException e) {
            return 3;
         }

         return this.Write2(bytes, n);
      }
   }

   public int Open(String str, int n) {
      int n2 = 0;
      if (this.fmode != 0) {
         n2 = this.Close();
      }

      if (n2 == 0) {
         switch (n) {
            case 1:
               try {
                  this.file = new RandomAccessFile(str, "rw");

                  try {
                     byte[] bytes = new byte[8];
                     bytes[0] = (byte)(this.riff_header.ckID >>> 24 & 0xFF);
                     bytes[1] = (byte)(this.riff_header.ckID >>> 16 & 0xFF);
                     bytes[2] = (byte)(this.riff_header.ckID >>> 8 & 0xFF);
                     bytes[3] = (byte)(this.riff_header.ckID & 0xFF);
                     byte b = (byte)(this.riff_header.ckSize >>> 24 & 0xFF);
                     byte b2 = (byte)(this.riff_header.ckSize >>> 16 & 0xFF);
                     byte b3 = (byte)(this.riff_header.ckSize >>> 8 & 0xFF);
                     bytes[4] = (byte)(this.riff_header.ckSize & 0xFF);
                     bytes[5] = b3;
                     bytes[6] = b2;
                     bytes[7] = b;
                     this.file.write(bytes, 0, 8);
                     this.fmode = 1;
                  } catch (IOException e) {
                     this.file.close();
                     this.fmode = 0;
                  }
               } catch (IOException e2) {
                  this.fmode = 0;
                  n2 = 3;
               }
               break;
            case 2:
               try {
                  this.file = new RandomAccessFile(str, "r");

                  try {
                     byte[] bytes2 = new byte[8];
                     this.file.read(bytes2, 0, 8);
                     this.fmode = 2;
                     this.riff_header.ckID = bytes2[0] << 24 & 0xFF000000 | bytes2[1] << 16 & 0xFF0000 | bytes2[2] << 8 & 0xFF00 | bytes2[3] & 255;
                     this.riff_header.ckSize = bytes2[4] << 24 & 0xFF000000 | bytes2[5] << 16 & 0xFF0000 | bytes2[6] << 8 & 0xFF00 | bytes2[7] & 255;
                  } catch (IOException e3) {
                     this.file.close();
                     this.fmode = 0;
                  }
               } catch (IOException e4) {
                  this.fmode = 0;
                  n2 = 3;
               }
               break;
            default:
               n2 = 4;
         }
      }

      return n2;
   }

   public static int FourCC(String str) {
      byte[] bytes = new byte[]{32, 32, 32, 32};
      str.getBytes(0, 4, bytes, 0);
      return bytes[0] << 24 & 0xFF000000 | bytes[1] << 16 & 0xFF0000 | bytes[2] << 8 & 0xFF00 | bytes[3] & 0xFF;
   }

   public int Write2(byte[] bytes, int n) {
      if (this.fmode != 1) {
         return 4;
      } else {
         try {
            this.file.write(bytes, 0, n);
            this.fmode = 1;
         } catch (IOException e) {
            return 3;
         }

         this.riff_header.ckSize += n;
         return 0;
      }
   }

   public int Close() {
      byte b = 0;
      switch (this.fmode) {
         case 1:
            try {
               this.file.seek(0L);

               try {
                  byte[] bytes = new byte[8];
                  bytes[0] = (byte)(this.riff_header.ckID >>> 24 & 0xFF);
                  bytes[1] = (byte)(this.riff_header.ckID >>> 16 & 0xFF);
                  bytes[2] = (byte)(this.riff_header.ckID >>> 8 & 0xFF);
                  bytes[3] = (byte)(this.riff_header.ckID & 0xFF);
                  bytes[7] = (byte)(this.riff_header.ckSize >>> 24 & 0xFF);
                  bytes[6] = (byte)(this.riff_header.ckSize >>> 16 & 0xFF);
                  bytes[5] = (byte)(this.riff_header.ckSize >>> 8 & 0xFF);
                  bytes[4] = (byte)(this.riff_header.ckSize & 0xFF);
                  this.file.write(bytes, 0, 8);
                  this.file.close();
               } catch (IOException e) {
                  b = 3;
               }
            } catch (IOException e2) {
               b = 3;
            }
            break;
         case 2:
            try {
               this.file.close();
            } catch (IOException e3) {
               b = 3;
            }
      }

      this.file = null;
      this.fmode = 0;
      return b;
   }

   public int Backpatch2(long l, RiffChunkHeader riffChunkHeader, int n) {
      if (this.file == null) {
         return 4;
      } else {
         try {
            this.file.seek(l);
         } catch (IOException e) {
            return 3;
         }

         return this.Write(riffChunkHeader, n);
      }
   }

   public int Write3(short[] sArr, int n) {
      byte[] bytes = new byte[n];
      int n2 = 0;

      for (int i = 0; i < n; i += 2) {
         bytes[i] = (byte)(sArr[n2] & 255);
         bytes[i + 1] = (byte)(sArr[n2++] >>> 8 & 0xFF);
      }

      if (this.fmode != 1) {
         return 4;
      } else {
         try {
            this.file.write(bytes, 0, n);
            this.fmode = 1;
         } catch (IOException e) {
            return 3;
         }

         this.riff_header.ckSize += n;
         return 0;
      }
   }

   private String DDCRET_String(int n) {
      switch (n) {
         case 0:
            return "DDC_SUCCESS";
         case 1:
            return "DDC_FAILURE";
         case 2:
            return "DDC_OUT_OF_MEMORY";
         case 3:
            return "DDC_FILE_ERROR";
         case 4:
            return "DDC_INVALID_CALL";
         case 5:
            return "DDC_USER_ABORT";
         case 6:
            return "DDC_INVALID_FILE";
         default:
            return "Unknown Error";
      }
   }

   public int getFmode() {
      return this.fmode;
   }

   public RiffFile() {
      this.fmode = 0;
      this.riff_header = new RiffChunkHeader(this);
      this.riff_header.ckID = FourCC("RIFF");
      this.riff_header.ckSize = 0;
   }

   public int Expect(String str, int n) {
      byte b = 0;
      int n2 = 0;

      try {
         while (n-- != 0) {
            b = this.file.readByte();
            if (b != str.charAt(n2++)) {
               return 3;
            }
         }

         return 0;
      } catch (IOException e) {
         return 3;
      }
   }

   public int Write5(short s, int n) {
      short s2 = (short)(s >>> 8 & 0xFF | s << 8 & 0xFF00);
      if (this.fmode != 1) {
         return 4;
      } else {
         try {
            this.file.writeShort(s2);
            this.fmode = 1;
         } catch (IOException e) {
            return 3;
         }

         this.riff_header.ckSize += n;
         return 0;
      }
   }

   public long CurrentFilePosition() {
      long l;
      try {
         l = this.file.getFilePointer();
      } catch (IOException e) {
         l = -1L;
      }

      return l;
   }

   public int Seek(long l) {
      byte b;
      try {
         this.file.seek(l);
         b = 0;
      } catch (IOException e) {
         b = 3;
      }

      return b;
   }

   public int Read(byte[] bytes, int n) {
      byte b = 0;

      try {
         this.file.read(bytes, 0, n);
      } catch (IOException e) {
         b = 3;
      }

      return b;
   }
}
