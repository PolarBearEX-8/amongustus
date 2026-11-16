package cc.baritone.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public final class DepthFirstPipeline implements ServiceAdapterImpl {
   public static byte byteFlag = 0;
   public static byte byteFlag2 = 1;
   public static final int count10 = 433;
   public final int[] countArray = new int[433];
   public int count11;
   public byte[] byteFlagArray = new byte[1732];
   public int count12;
   public int count13;
   public int count14;
   public int count15 = 0;
   public boolean enabled;
   public final int[] countArray2 = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071};
   public final PushbackInputStream pushbackInputStream;
   public final AStarService aStarService = new AStarService();
   public final byte[] byteFlagArray2 = new byte[4];
   public ControllerProvider[] controllerProviderArray = new ControllerProvider[1];
   public byte[] byteFlagArray3 = null;
   public boolean enabled2 = true;

   private int computeCount(InputStream inputStream) throws IOException {
      byte[] byteValueArray = new byte[4];
      int count = -10;
      inputStream.read(byteValueArray, 0, 3);
      if (byteValueArray[0] == 73 && byteValueArray[1] == 68 && byteValueArray[2] == 51) {
         inputStream.read(byteValueArray, 0, 3);
         int byteValue = byteValueArray[0];
         byteValue = byteValueArray[1];
         inputStream.read(byteValueArray, 0, 4);
         count = (byteValueArray[0] << 21) + (byteValueArray[1] << 14) + (byteValueArray[2] << 7) + byteValueArray[3];
      }

      return count + 10;
   }

   public int computeCount3(int count) {
      int count2 = this.count13 + count;
      if (this.count12 < 0) {
         this.count12 = 0;
      }

      if (count2 <= 32) {
         int count3 = this.countArray[this.count12] >>> 32 - count2 & this.countArray2[count];
         if ((this.count13 += count) == 32) {
            this.count13 = 0;
            this.count12++;
         }

         return count3;
      } else {
         int count4 = this.countArray[this.count12] & 65535;
         this.count12++;
         int count5 = this.countArray[this.count12] & -65536;
         count4 = count4 << 16 & -65536 | count5 >>> 16 & 65535;
         count4 >>>= 48 - count2;
         count4 &= this.countArray2[count];
         this.count13 = count2 - 32;
         return count4;
      }
   }

   public int computeCount(byte byteValue) throws AStarScheduler {
      if (this.computeCountByByteArray(this.byteFlagArray2, 0, 3) != 3) {
         throw this.resolveAStarScheduler(260, null);
      } else {
         int count = this.byteFlagArray2[0] << 16 & 0xFF0000 | this.byteFlagArray2[1] << 8 & 0xFF00 | this.byteFlagArray2[2] << 0 & 0xFF;

         do {
            count <<= 8;
            if (this.computeCountByByteArray(this.byteFlagArray2, 3, 1) != 1) {
               throw this.resolveAStarScheduler(260, null);
            }

            count |= this.byteFlagArray2[3] & 255;
         } while (!this.checkIndex(count, byteValue, this.count14));

         return count;
      }
   }

   public void applyIndex(int count) {
      this.count14 = count & -193;
      this.enabled = (count & 192) == 192;
   }

   public int computeCount(int count) {
      return this.computeCount3(count);
   }

   public InputStream getInputStream() {
      return this.byteFlagArray3 == null ? null : new ByteArrayInputStream(this.byteFlagArray3);
   }

   public int getCount15() {
      return this.count15;
   }

   public boolean checkIndex(int count, int count2, int count3) {
      boolean flag;
      if (count2 == byteFlag) {
         flag = (count & -2097152) == -2097152;
      } else {
         flag = (count & -521216) == count3 && (count & 192) == 192 == this.enabled;
      }

      if (flag) {
         flag = (count >>> 10 & 3) != 3;
      }

      if (flag) {
         flag = (count >>> 17 & 3) != 0;
      }

      if (flag) {
         flag = (count >>> 19 & 3) != 1;
      }

      return flag;
   }

   private void applyInputStream(InputStream inputStream) {
      int count = -1;

      try {
         inputStream.mark(10);
         count = this.computeCount(inputStream);
         this.count15 = count;
      } catch (IOException ioException) {
      } finally {
         try {
            inputStream.reset();
         } catch (IOException ioException2) {
         }
      }

      try {
         if (count > 0) {
            this.byteFlagArray3 = new byte[count];
            inputStream.read(this.byteFlagArray3, 0, this.byteFlagArray3.length);
         }
      } catch (IOException ioException3) {
      }
   }

   private int computeCount(byte[] byteValueArray, int count, int count2) throws AStarScheduler {
      int count3 = 0;

      try {
         while (count2 > 0) {
            int count4 = this.pushbackInputStream.read(byteValueArray, count, count2);
            if (count4 == -1) {
               while (count2-- > 0) {
                  byteValueArray[count++] = 0;
               }
               break;
            }

            count3 += count4;
            count += count4;
            count2 -= count4;
         }

         return count3;
      } catch (IOException ioException) {
         throw this.resolveAStarScheduler(258, ioException);
      }
   }

   public void updateState2() {
      this.count11 = -1;
      this.count12 = -1;
      this.count13 = -1;
   }

   public int computeCountByIndex(int count) {
      return this.computeCount3(count);
   }

   public void updateState3() throws AStarScheduler {
      try {
         this.pushbackInputStream.close();
      } catch (IOException ioException) {
         throw this.resolveAStarScheduler(258, ioException);
      }
   }

   public AStarScheduler resolveAStarScheduler(int count, Throwable throwable) {
      return new AStarScheduler(count, throwable);
   }

   private AStarService getAStarService() throws AStarScheduler {
      if (this.count11 == -1) {
         this.updateState4();
      }

      return this.aStarService;
   }

   public AStarScheduler resolveAStarScheduler(int count) {
      return new AStarScheduler(count, null);
   }

   public boolean checkIndex(int count) throws AStarScheduler {
      int count2 = this.computeCountByByteArray(this.byteFlagArray2, 0, 4);
      int count3 = this.byteFlagArray2[0] << 24 & 0xFF000000
         | this.byteFlagArray2[1] << 16 & 0xFF0000
         | this.byteFlagArray2[2] << 8 & 0xFF00
         | this.byteFlagArray2[3] << 0 & 0xFF;

      try {
         this.pushbackInputStream.unread(this.byteFlagArray2, 0, count2);
      } catch (IOException ioException) {
      }

      boolean flag = false;
      switch (count2) {
         case 0:
            flag = true;
            break;
         case 4:
            flag = this.checkIndex(count3, count, this.count14);
      }

      return flag;
   }

   public void updateState5() throws AStarScheduler {
      int count = 0;
      byte[] byteFlagArray4 = this.byteFlagArray;
      int count2 = this.count11;

      for (int byteValue = 0; byteValue < count2; byteValue += 4) {
         byte byteValue2 = 0;
         byte byteValue3 = 0;
         byte byteValue4 = 0;
         byte byteValue5 = byteFlagArray4[byteValue];
         if (byteValue + 1 < count2) {
            byteValue2 = byteFlagArray4[byteValue + 1];
         }

         if (byteValue + 2 < count2) {
            byteValue3 = byteFlagArray4[byteValue + 2];
         }

         if (byteValue + 3 < count2) {
            byteValue4 = byteFlagArray4[byteValue + 3];
         }

         this.countArray[count++] = byteValue5 << 24 & 0xFF000000 | byteValue2 << 16 & 0xFF0000 | byteValue3 << 8 & 0xFF00 | byteValue4 & 255;
      }

      this.count12 = 0;
      this.count13 = 0;
   }

   public int computeCount2(int count) throws AStarScheduler {
      int count2 = this.computeCount(this.byteFlagArray, 0, count);
      this.count11 = count;
      this.count12 = -1;
      this.count13 = -1;
      return count2;
   }

   public DepthFirstPipeline(InputStream inputStream) {
      if (inputStream == null) {
         throw new NullPointerException("in");
      } else {
         BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
         this.applyInputStream(bufferedInputStream);
         this.enabled2 = true;
         this.pushbackInputStream = new PushbackInputStream(bufferedInputStream, 1732);
         this.updateState2();
      }
   }

   private int computeCountByByteArray(byte[] byteValueArray, int count, int count2) throws AStarScheduler {
      int count3 = 0;

      try {
         while (count2 > 0) {
            int count4 = this.pushbackInputStream.read(byteValueArray, count, count2);
            if (count4 == -1) {
               break;
            }

            count3 += count4;
            count += count4;
            count2 -= count4;
         }

         return count3;
      } catch (IOException ioException) {
         throw this.resolveAStarScheduler(258, ioException);
      }
   }

   public void updateState() throws AStarScheduler {
      if (this.count12 == -1 && this.count13 == -1 && this.count11 > 0) {
         try {
            this.pushbackInputStream.unread(this.byteFlagArray, 0, this.count11);
         } catch (IOException ioException) {
            throw this.resolveAStarScheduler(258);
         }
      }
   }

   public AStarService getAStarServiceAsAStarService() throws AStarScheduler {
      AStarService aStarService2 = null;

      try {
         aStarService2 = this.getAStarService();
         if (this.enabled2) {
            aStarService2.applyByteArray(this.byteFlagArray);
            this.enabled2 = false;
         }
      } catch (AStarScheduler exception) {
         if (exception.getCount10() == 261) {
            try {
               this.updateState2();
               aStarService2 = this.getAStarService();
            } catch (AStarScheduler exception2) {
               if (exception2.getCount10() != 260) {
                  throw this.resolveAStarScheduler(exception2.getCount10(), exception2);
               }
            }
         } else if (exception.getCount10() != 260) {
            throw this.resolveAStarScheduler(exception.getCount10(), exception);
         }
      }

      return aStarService2;
   }

   private void updateState4() throws AStarScheduler {
      this.aStarService.applyDepthFirstPipeline(this, this.controllerProviderArray);
   }
}
