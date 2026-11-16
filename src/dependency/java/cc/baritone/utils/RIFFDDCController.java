package cc.baritone.utils;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RIFFDDCController {
   public static final int count2 = 0;
   public static final int count3 = 1;
   public static final int count4 = 2;
   public static final int count5 = 3;
   public static final int count6 = 4;
   public static final int count7 = 5;
   public static final int count8 = 6;
   public static final int count9 = 0;
   public static final int count10 = 1;
   public static final int count11 = 2;
   public RecordNodeBridge recordNodeBridge;
   public int count;
   public RandomAccessFile randomAccessFile = null;

   public int computeCount(RecordNodeBridge recordNodeBridge2, int count12) {
      byte[] byteValueArray = new byte[8];
      byteValueArray[0] = (byte)(recordNodeBridge2.count >>> 24 & 0xFF);
      byteValueArray[1] = (byte)(recordNodeBridge2.count >>> 16 & 0xFF);
      byteValueArray[2] = (byte)(recordNodeBridge2.count >>> 8 & 0xFF);
      byteValueArray[3] = (byte)(recordNodeBridge2.count & 0xFF);
      int byteValue = (byte)(recordNodeBridge2.count2 >>> 24 & 0xFF);
      byte byteValue2 = (byte)(recordNodeBridge2.count2 >>> 16 & 0xFF);
      byte byteValue3 = (byte)(recordNodeBridge2.count2 >>> 8 & 0xFF);
      byte byteValue4 = (byte)(recordNodeBridge2.count2 & 0xFF);
      byteValueArray[4] = byteValue4;
      byteValueArray[5] = byteValue3;
      byteValueArray[6] = byteValue2;
      byteValueArray[7] = (byte)byteValue;
      if (this.count != 1) {
         return 4;
      } else {
         try {
            this.randomAccessFile.write(byteValueArray, 0, count12);
            this.count = 1;
         } catch (IOException ioException) {
            return 3;
         }

         this.recordNodeBridge.count2 += count12;
         return 0;
      }
   }

   public int computeCountByText(String text, int count12) {
      int count13 = count12;
      int count14 = 0;

      try {
         while (count13-- != 0) {
            if (this.randomAccessFile.readByte() != text.charAt(count14++)) {
               return 3;
            }
         }

         return 0;
      } catch (IOException ioException) {
         return 3;
      }
   }

   public static int computeCount(String text) {
      byte[] byteValueArray = new byte[]{32, 32, 32, 32};
      text.getBytes(0, 4, byteValueArray, 0);
      return byteValueArray[0] << 24 & 0xFF000000 | byteValueArray[1] << 16 & 0xFF0000 | byteValueArray[2] << 8 & 0xFF00 | byteValueArray[3] & 0xFF;
   }

   public int computeCount(String text, int count12) {
      int count13 = 0;
      if (this.count != 0) {
         count13 = this.getCount();
      }

      if (count13 == 0) {
         switch (count12) {
            case 1:
               try {
                  this.randomAccessFile = new RandomAccessFile(text, "rw");

                  try {
                     byte[] byteValueArray = new byte[8];
                     byteValueArray[0] = (byte)(this.recordNodeBridge.count >>> 24 & 0xFF);
                     byteValueArray[1] = (byte)(this.recordNodeBridge.count >>> 16 & 0xFF);
                     byteValueArray[2] = (byte)(this.recordNodeBridge.count >>> 8 & 0xFF);
                     byteValueArray[3] = (byte)(this.recordNodeBridge.count & 0xFF);
                     int byteValue = (byte)(this.recordNodeBridge.count2 >>> 24 & 0xFF);
                     byte byteValue2 = (byte)(this.recordNodeBridge.count2 >>> 16 & 0xFF);
                     byte byteValue3 = (byte)(this.recordNodeBridge.count2 >>> 8 & 0xFF);
                     byte byteValue4 = (byte)(this.recordNodeBridge.count2 & 0xFF);
                     byteValueArray[4] = byteValue4;
                     byteValueArray[5] = byteValue3;
                     byteValueArray[6] = byteValue2;
                     byteValueArray[7] = (byte)byteValue;
                     this.randomAccessFile.write(byteValueArray, 0, 8);
                     this.count = 1;
                  } catch (IOException ioException) {
                     this.randomAccessFile.close();
                     this.count = 0;
                  }
               } catch (IOException ioException2) {
                  this.count = 0;
                  count13 = 3;
               }
               break;
            case 2:
               try {
                  this.randomAccessFile = new RandomAccessFile(text, "r");

                  try {
                     byte[] byteValueArray2 = new byte[8];
                     this.randomAccessFile.read(byteValueArray2, 0, 8);
                     this.count = 2;
                     this.recordNodeBridge.count = byteValueArray2[0] << 24 & 0xFF000000 | byteValueArray2[1] << 16 & 0xFF0000 | byteValueArray2[2] << 8 & 0xFF00 | byteValueArray2[3] & 255;
                     this.recordNodeBridge.count2 = byteValueArray2[4] << 24 & 0xFF000000 | byteValueArray2[5] << 16 & 0xFF0000 | byteValueArray2[6] << 8 & 0xFF00 | byteValueArray2[7] & 255;
                  } catch (IOException ioException3) {
                     this.randomAccessFile.close();
                     this.count = 0;
                  }
               } catch (IOException ioException4) {
                  this.count = 0;
                  count13 = 3;
               }
               break;
            default:
               count13 = 4;
         }
      }

      return count13;
   }

   public long getFilePointer() {
      long value;
      try {
         value = this.randomAccessFile.getFilePointer();
      } catch (IOException ioException) {
         value = -1L;
      }

      return value;
   }

   public int computeCount(long value, byte[] byteValueArray, int count12) {
      if (this.randomAccessFile == null) {
         return 4;
      } else {
         try {
            this.randomAccessFile.seek(value);
         } catch (IOException ioException) {
            return 3;
         }

         return this.computeCount(byteValueArray, count12);
      }
   }

   public int seek(long value) {
      byte byteValue;
      try {
         this.randomAccessFile.seek(value);
         byteValue = 0;
      } catch (IOException ioException) {
         byteValue = 3;
      }

      return byteValue;
   }

   public int computeCount(short[] shortValueArray, int count12) {
      byte[] byteValueArray = new byte[count12];
      int count13 = 0;

      for (int byteValue = 0; byteValue < count12; byteValue += 2) {
         byteValueArray[byteValue] = (byte)(shortValueArray[count13] & 255);
         byteValueArray[byteValue + 1] = (byte)(shortValueArray[count13++] >>> 8 & 0xFF);
      }

      if (this.count != 1) {
         return 4;
      } else {
         try {
            this.randomAccessFile.write(byteValueArray, 0, count12);
            this.count = 1;
         } catch (IOException ioException) {
            return 3;
         }

         this.recordNodeBridge.count2 += count12;
         return 0;
      }
   }

   public int getCount() {
      int byteValue = 0;
      switch (this.count) {
         case 1:
            try {
               this.randomAccessFile.seek(0L);

               try {
                  byte[] byteValueArray = new byte[8];
                  byteValueArray[0] = (byte)(this.recordNodeBridge.count >>> 24 & 0xFF);
                  byteValueArray[1] = (byte)(this.recordNodeBridge.count >>> 16 & 0xFF);
                  byteValueArray[2] = (byte)(this.recordNodeBridge.count >>> 8 & 0xFF);
                  byteValueArray[3] = (byte)(this.recordNodeBridge.count & 0xFF);
                  byteValueArray[7] = (byte)(this.recordNodeBridge.count2 >>> 24 & 0xFF);
                  byteValueArray[6] = (byte)(this.recordNodeBridge.count2 >>> 16 & 0xFF);
                  byteValueArray[5] = (byte)(this.recordNodeBridge.count2 >>> 8 & 0xFF);
                  byteValueArray[4] = (byte)(this.recordNodeBridge.count2 & 0xFF);
                  this.randomAccessFile.write(byteValueArray, 0, 8);
                  this.randomAccessFile.close();
               } catch (IOException ioException) {
                  byteValue = 3;
               }
            } catch (IOException ioException2) {
               byteValue = 3;
            }
            break;
         case 2:
            try {
               this.randomAccessFile.close();
            } catch (IOException ioException3) {
               byteValue = 3;
            }
      }

      this.randomAccessFile = null;
      this.count = 0;
      return byteValue;
   }

   public int read(byte[] byteValueArray, int count12) {
      int byteValue = 0;

      try {
         this.randomAccessFile.read(byteValueArray, 0, count12);
      } catch (IOException ioException) {
         byteValue = 3;
      }

      return byteValue;
   }

   public int computeCount(int count12, int count13) {
      short shortValue = (short)(count12 >>> 16 & 65535);
      short shortValue2 = (short)(count12 & 65535);
      short shortValue3 = (short)(shortValue >>> 8 & 0xFF | shortValue << 8 & 0xFF00);
      int count14 = (short)(shortValue2 >>> 8 & 0xFF | shortValue2 << 8 & 0xFF00) << 16 & -65536 | shortValue3 & '\uffff';
      if (this.count != 1) {
         return 4;
      } else {
         try {
            this.randomAccessFile.writeInt(count14);
            this.count = 1;
         } catch (IOException ioException) {
            return 3;
         }

         this.recordNodeBridge.count2 += count13;
         return 0;
      }
   }

   public int computeCount(long value, RecordNodeBridge recordNodeBridge2, int count12) {
      if (this.randomAccessFile == null) {
         return 4;
      } else {
         try {
            this.randomAccessFile.seek(value);
         } catch (IOException ioException) {
            return 3;
         }

         return this.computeCount(recordNodeBridge2, count12);
      }
   }

   public int computeCount(byte[] byteValueArray, int count12) {
      if (this.count != 1) {
         return 4;
      } else {
         try {
            this.randomAccessFile.write(byteValueArray, 0, count12);
            this.count = 1;
         } catch (IOException ioException) {
            return 3;
         }

         this.recordNodeBridge.count2 += count12;
         return 0;
      }
   }

   public RIFFDDCController() {
      this.count = 0;
      this.recordNodeBridge = new RecordNodeBridge(this);
      this.recordNodeBridge.count = computeCount("RIFF");
      this.recordNodeBridge.count2 = 0;
   }

   private String resolveText(int count12) {
      switch (count12) {
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

   public int computeCount(short shortValue, int count12) {
      short shortValue2 = (short)(shortValue >>> 8 & 0xFF | shortValue << 8 & 0xFF00);
      if (this.count != 1) {
         return 4;
      } else {
         try {
            this.randomAccessFile.writeShort(shortValue2);
            this.count = 1;
         } catch (IOException ioException) {
            return 3;
         }

         this.recordNodeBridge.count2 += count12;
         return 0;
      }
   }

   public int getCountAsIndex() {
      return this.count;
   }
}
