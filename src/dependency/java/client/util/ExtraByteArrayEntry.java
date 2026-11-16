package client.util;

import com.google.common.collect.Lists;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class ExtraByteArrayEntry {
   public static final byte[] byteFlagArray = new byte[4096];
   public final File file;
   public RandomAccessFile randomAccessFile;
   public final int[] countArray = new int[1024];
   public final int[] countArray2 = new int[1024];
   public List list;
   public int count;
   public long timestamp;

   public synchronized void applyByteArray(int count2, int count3, byte[] byteValueArray, int count4) {
      try {
         int count5 = this.computeCount(count2, count3);
         int count6 = count5 >> 8;
         int count7 = count5 & 0xFF;
         int count8 = (count4 + 5) / 4096 + 1;
         if (count8 >= 256) {
            return;
         }

         if (count6 != 0 && count7 == count8) {
            this.applyByteArray(count6, byteValueArray, count4);
         } else {
            for (int index = 0; index < count7; index++) {
               this.list.set(count6 + index, Boolean.TRUE);
            }

            int count9 = this.list.indexOf(Boolean.TRUE);
            int count10 = 0;
            if (count9 != -1) {
               for (int index2 = count9; index2 < this.list.size(); index2++) {
                  if (count10 != 0) {
                     if ((Boolean)this.list.get(index2)) {
                        count10++;
                     } else {
                        count10 = 0;
                     }
                  } else if ((Boolean)this.list.get(index2)) {
                     count9 = index2;
                     count10 = 1;
                  }

                  if (count10 >= count8) {
                     break;
                  }
               }
            }

            if (count10 >= count8) {
               int count11 = count9;
               this.applyIndexByIndex(count2, count3, count9 << 8 | count8);

               for (int index3 = 0; index3 < count8; index3++) {
                  this.list.set(count11 + index3, Boolean.FALSE);
               }

               this.applyByteArray(count11, byteValueArray, count4);
            } else {
               this.randomAccessFile.seek(this.randomAccessFile.length());
               int size = this.list.size();

               for (int index4 = 0; index4 < count8; index4++) {
                  this.randomAccessFile.write(byteFlagArray);
                  this.list.add(Boolean.FALSE);
               }

               this.count += 4096 * count8;
               this.applyByteArray(size, byteValueArray, count4);
               this.applyIndexByIndex(count2, count3, size << 8 | count8);
            }
         }

         this.applyIndex(count2, count3, (int)(VanillaRunnable.currentTimeMillis() / 1000L));
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   private boolean checkIndex(int count2, int count3) {
      return count2 < 0 || count2 >= 32 || count3 < 0 || count3 >= 32;
   }

   private int computeCount(int count2, int count3) {
      return this.countArray[count2 + count3 * 32];
   }

   public synchronized DataInputStream resolveDataInputStream(int count2, int count3) {
      if (this.checkIndex(count2, count3)) {
         return null;
      } else {
         try {
            int count4 = this.computeCount(count2, count3);
            if (count4 == 0) {
               return null;
            } else {
               int count5 = count4 >> 8;
               int count6 = count4 & 0xFF;
               if (count5 + count6 > this.list.size()) {
                  return null;
               } else {
                  this.randomAccessFile.seek((long)(count5 * 4096));
                  int count7 = this.randomAccessFile.readInt();
                  if (count7 > 4096 * count6) {
                     return null;
                  } else if (count7 <= 0) {
                     return null;
                  } else {
                     byte byteValue = this.randomAccessFile.readByte();
                     if (byteValue == 1) {
                        byte[] byteValueArray = new byte[count7 - 1];
                        this.randomAccessFile.read(byteValueArray);
                        return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(byteValueArray))));
                     } else if (byteValue == 2) {
                        byte[] byteValueArray2 = new byte[count7 - 1];
                        this.randomAccessFile.read(byteValueArray2);
                        return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(byteValueArray2))));
                     } else {
                        return null;
                     }
                  }
               }
            }
         } catch (IOException ioException) {
            return null;
         }
      }
   }

   private void applyByteArray(int count2, byte[] byteValueArray, int count3) throws IOException {
      this.randomAccessFile.seek((long)(count2 * 4096));
      this.randomAccessFile.writeInt(count3 + 1);
      this.randomAccessFile.writeByte(2);
      this.randomAccessFile.write(byteValueArray, 0, count3);
   }

   public ExtraByteArrayEntry(File file2) {
      this.file = file2;
      this.count = 0;

      try {
         if (file2.exists()) {
            this.timestamp = file2.lastModified();
         }

         this.randomAccessFile = new RandomAccessFile(file2, "rw");
         if (this.randomAccessFile.length() < 4096L) {
            for (int index = 0; index < 1024; index++) {
               this.randomAccessFile.writeInt(0);
            }

            for (int index2 = 0; index2 < 1024; index2++) {
               this.randomAccessFile.writeInt(0);
            }

            this.count += 8192;
         }

         if ((this.randomAccessFile.length() & 4095L) != 0L) {
            for (int index3 = 0; (long)index3 < (this.randomAccessFile.length() & 4095L); index3++) {
               this.randomAccessFile.write(0);
            }
         }

         int count2 = (int)this.randomAccessFile.length() / 4096;
         this.list = Lists.newArrayListWithCapacity(count2);

         for (int index4 = 0; index4 < count2; index4++) {
            this.list.add(Boolean.TRUE);
         }

         this.list.set(0, Boolean.FALSE);
         this.list.set(1, Boolean.FALSE);
         this.randomAccessFile.seek(0L);

         for (int index5 = 0; index5 < 1024; index5++) {
            int count3 = this.randomAccessFile.readInt();
            this.countArray[index5] = count3;
            if (count3 != 0 && (count3 >> 8) + (count3 & 0xFF) <= this.list.size()) {
               for (int index6 = 0; index6 < (count3 & 0xFF); index6++) {
                  this.list.set((count3 >> 8) + index6, Boolean.FALSE);
               }
            }
         }

         for (int index7 = 0; index7 < 1024; index7++) {
            int count4 = this.randomAccessFile.readInt();
            this.countArray2[index7] = count4;
         }
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   private void applyIndexByIndex(int count2, int count3, int count4) throws IOException {
      this.countArray[count2 + count3 * 32] = count4;
      this.randomAccessFile.seek((long)((count2 + count3 * 32) * 4));
      this.randomAccessFile.writeInt(count4);
   }

   private void applyIndex(int count2, int count3, int count4) throws IOException {
      this.countArray2[count2 + count3 * 32] = count4;
      this.randomAccessFile.seek((long)(4096 + (count2 + count3 * 32) * 4));
      this.randomAccessFile.writeInt(count4);
   }

   public boolean checkIndexByIndex(int count2, int count3) {
      return this.computeCount(count2, count3) != 0;
   }

   public void close() throws IOException {
      if (this.randomAccessFile != null) {
         this.randomAccessFile.close();
      }
   }

   public DataOutputStream resolveDataOutputStream(int count2, int count3) {
      return this.checkIndex(count2, count3) ? null : new DataOutputStream(new DeflaterOutputStream(new ByteArrayOutputStreamAdapter(this, count2, count3)));
   }
}
