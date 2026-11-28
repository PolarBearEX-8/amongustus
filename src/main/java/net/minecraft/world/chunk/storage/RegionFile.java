package net.minecraft.world.chunk.storage;

import java.io.ByteArrayOutputStream;
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
import net.minecraft.server.MinecraftServer;

public class RegionFile {
   private static final byte[] emptySector = new byte[4096];
   private final File fileName;
   private RandomAccessFile dataFile;
   private final int[] offsets = new int[1024];
   private final int[] chunkTimestamps = new int[1024];
   private List sectorFree;
   private int sizeDelta;
   private long lastModified;

   public synchronized void write(int n, int n2, byte[] bytes, int n3) {
      try {
         int offset = this.getOffset(n, n2);
         int n4 = offset >> 8;
         int n5 = offset & 0xFF;
         int n6 = (n3 + 5) / 4096 + 1;
         if (n6 >= 256) {
            return;
         }

         if (n4 != 0 && n5 == n6) {
            this.write(n4, bytes, n3);
         } else {
            for (int i = 0; i < n5; i++) {
               this.sectorFree.set(n4 + i, Boolean.TRUE);
            }

            int n7 = this.sectorFree.indexOf(Boolean.TRUE);
            int n8 = 0;
            if (n7 != -1) {
               for (int j = n7; j < this.sectorFree.size(); j++) {
                  if (n8 != 0) {
                     if ((Boolean)this.sectorFree.get(j)) {
                        n8++;
                     } else {
                        n8 = 0;
                     }
                  } else if ((Boolean)this.sectorFree.get(j)) {
                     n7 = j;
                     n8 = 1;
                  }

                  if (n8 >= n6) {
                     break;
                  }
               }
            }

            if (n8 >= n6) {
               n4 = n7;
               this.setOffset(n, n2, n7 << 8 | n6);

               for (int k = 0; k < n6; k++) {
                  this.sectorFree.set(n4 + k, Boolean.FALSE);
               }

               this.write(n4, bytes, n3);
            } else {
               this.dataFile.seek(this.dataFile.length());
               n4 = this.sectorFree.size();

               for (int m = 0; m < n6; m++) {
                  this.dataFile.write(emptySector);
                  this.sectorFree.add(Boolean.FALSE);
               }

               this.sizeDelta += 4096 * n6;
               this.write(n4, bytes, n3);
               this.setOffset(n, n2, n4 << 8 | n6);
            }
         }

         this.setChunkTimestamp(n, n2, (int)(MinecraftServer.getCurrentTimeMillis() / 1000L));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void setChunkTimestamp(int n, int n2, int n3) throws IOException {
      this.chunkTimestamps[n + n2 * 32] = n3;
      this.dataFile.seek((long)(4096 + (n + n2 * 32) * 4));
      this.dataFile.writeInt(n3);
   }

   public DataOutputStream getChunkDataOutputStream(int n, int n2) {
      return this.outOfBounds(n, n2) ? null : new DataOutputStream(new DeflaterOutputStream(new RegionFile.ChunkBuffer(this, n, n2)));
   }

   private void write(int n, byte[] bytes, int n2) throws IOException {
      this.dataFile.seek((long)(n * 4096));
      this.dataFile.writeInt(n2 + 1);
      this.dataFile.writeByte(2);
      this.dataFile.write(bytes, 0, n2);
   }

   public synchronized DataInputStream getChunkDataInputStream(int n, int n2) {
      if (this.outOfBounds(n, n2)) {
         return null;
      } else {
         try {
            int offset = this.getOffset(n, n2);
            if (offset == 0) {
               return null;
            } else {
               int n3 = offset >> 8;
               int n4 = offset & 0xFF;
               if (n3 + n4 > this.sectorFree.size()) {
                  return null;
               } else {
                  this.dataFile.seek((long)(n3 * 4096));
                  int n5 = this.dataFile.readInt();
                  if (n5 > 4096 * n4) {
                     return null;
                  } else if (n5 <= 0) {
                     return null;
                  } else {
                     byte b = this.dataFile.readByte();
                     if (b == 1) {
                        byte[] bytes = new byte[n5 - 1];
                        this.dataFile.read(bytes);
                        return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(bytes))));
                     } else if (b == 2) {
                        byte[] bytes2 = new byte[n5 - 1];
                        this.dataFile.read(bytes2);
                        return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(bytes2))));
                     } else {
                        return null;
                     }
                  }
               }
            }
         } catch (IOException e) {
            return null;
         }
      }
   }

   private boolean outOfBounds(int n, int n2) {
      return n < 0 || n >= 32 || n2 < 0 || n2 >= 32;
   }

   public void close() throws IOException {
      if (this.dataFile != null) {
         this.dataFile.close();
      }
   }

   private int getOffset(int n, int n2) {
      return this.offsets[n + n2 * 32];
   }

   private void setOffset(int n, int n2, int n3) throws IOException {
      this.offsets[n + n2 * 32] = n3;
      this.dataFile.seek((long)((n + n2 * 32) * 4));
      this.dataFile.writeInt(n3);
   }

   public boolean isChunkSaved(int n, int n2) {
      return this.getOffset(n, n2) != 0;
   }

   public RegionFile(File file) {
      this.fileName = file;
      this.sizeDelta = 0;

      try {
         if (file.exists()) {
            this.lastModified = file.lastModified();
         }

         this.dataFile = new RandomAccessFile(file, "rw");
         if (this.dataFile.length() < 4096L) {
            for (int i = 0; i < 1024; i++) {
               this.dataFile.writeInt(0);
            }

            for (int j = 0; j < 1024; j++) {
               this.dataFile.writeInt(0);
            }

            this.sizeDelta += 8192;
         }

         if ((this.dataFile.length() & 4095L) != 0L) {
            for (int k = 0; (long)k < (this.dataFile.length() & 4095L); k++) {
               this.dataFile.write(0);
            }
         }

         int n = (int)this.dataFile.length() / 4096;
         this.sectorFree = Lists.newArrayListWithCapacity(n);

         for (int m = 0; m < n; m++) {
            this.sectorFree.add(Boolean.TRUE);
         }

         this.sectorFree.set(0, Boolean.FALSE);
         this.sectorFree.set(1, Boolean.FALSE);
         this.dataFile.seek(0L);

         for (int idx = 0; idx < 1024; idx++) {
            int n2 = this.dataFile.readInt();
            this.offsets[idx] = n2;
            if (n2 != 0 && (n2 >> 8) + (n2 & 0xFF) <= this.sectorFree.size()) {
               for (int index = 0; index < (n2 & 0xFF); index++) {
                  this.sectorFree.set((n2 >> 8) + index, Boolean.FALSE);
               }
            }
         }

         for (int i2 = 0; i2 < 1024; i2++) {
            int n3 = this.dataFile.readInt();
            this.chunkTimestamps[i2] = n3;
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }


   public static class ChunkBuffer extends ByteArrayOutputStream {
      private int chunkX;
      private int chunkZ;
      public final RegionFile regionFile;

      public ChunkBuffer(RegionFile regionFile2, int n, int n2) {
         super(8096);
         this.regionFile = regionFile2;
         this.chunkX = n;
         this.chunkZ = n2;
      }

      @Override
      public void close() throws IOException {
         this.regionFile.write(this.chunkX, this.chunkZ, this.buf, this.count);
      }
   }
}
