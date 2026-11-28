package baritone.cache;

import baritone.Baritone;
import baritone.api.cache.ICachedRegion;
import baritone.api.utils.BlockUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public final class CachedRegion implements ICachedRegion {
   private static final byte CHUNK_NOT_PRESENT = 0;
   private static final byte CHUNK_PRESENT = 1;
   private static final int MAGIC = 456022910;
   private final CachedChunk[][] chunks = new CachedChunk[32][32];
   private final int x;
   private final int z;
   private final int dimension;
   private boolean dirty;

   @Override
   public final int getZ() {
      return this.z;
   }

   public final synchronized void saveToDisk(String str) {
      if (this.dirty) {
         this.pruneExpiredChunks();

         try {
            Path path = Paths.get(str);
            if (!Files.exists(path)) {
               Files.createDirectories(path);
            }

            System.out.println("Saving region " + this.x + "," + this.z + " to disk " + path);
            Path regionFile = getRegionFile(path, this.x, this.z);
            if (!Files.exists(regionFile)) {
               Files.createFile(regionFile);
            }

            try (
               FileOutputStream fileOutputStream = new FileOutputStream(regionFile.toFile());
               GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream, 16384);
               DataOutputStream out = new DataOutputStream(gzipOutputStream);
            ) {
               out.writeInt(456022910);

               for (int i = 0; i < 32; i++) {
                  for (int j = 0; j < 32; j++) {
                     CachedChunk cachedChunk = this.chunks[i][j];
                     if (cachedChunk == null) {
                        out.write(0);
                     } else {
                        out.write(1);
                        byte[] bytes = cachedChunk.toByteArray();
                        out.write(bytes);
                        out.write(new byte[16384 - bytes.length]);
                     }
                  }
               }

               for (int k = 0; k < 32; k++) {
                  for (int m = 0; m < 32; m++) {
                     if (this.chunks[k][m] != null) {
                        for (int idx = 0; idx < 256; idx++) {
                           out.writeUTF(BlockUtils.blockToString(this.chunks[k][m].getTopBlocks()[idx].getBlock()));
                        }
                     }
                  }
               }

               for (int index = 0; index < 32; index++) {
                  for (int i2 = 0; i2 < 32; i2++) {
                     if (this.chunks[index][i2] != null) {
                        Map specialBlocks = this.chunks[index][i2].getSpecialBlocks();
                        out.writeShort(specialBlocks.entrySet().size());

                        for (Entry entry : (Iterable<Entry>)(specialBlocks.entrySet())) {
                           out.writeUTF((String)entry.getKey());
                           out.writeShort(((List)entry.getValue()).size());

                           for (BlockPos pos : (Iterable<BlockPos>)((List)entry.getValue())) {
                              out.writeByte((byte)(pos.getZ() << 4 | pos.getX()));
                              out.writeByte((byte)pos.getY());
                           }
                        }
                     }
                  }
               }

               for (int i3 = 0; i3 < 32; i3++) {
                  for (int i4 = 0; i4 < 32; i4++) {
                     if (this.chunks[i3][i4] != null) {
                        out.writeLong(this.chunks[i3][i4].timestamp);
                     }
                  }
               }
            }

            this.dirty = false;
            System.out.println("Saved region successfully");
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   public final synchronized void pruneExpiredChunks() {
      long value = (Long)Baritone.settings().cachedChunksExpirySeconds.value;
      if (value >= 0L) {
         long l = System.currentTimeMillis();
         long l2 = l - value * 1000L;

         for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
               if (this.chunks[i][j] != null && this.chunks[i][j].timestamp < l2) {
                  System.out
                     .println(
                        "Removing chunk "
                           + (i + 32 * this.x)
                           + ","
                           + (j + 32 * this.z)
                           + " because it was cached "
                           + (l - this.chunks[i][j].timestamp) / 1000L
                           + " seconds ago, and max age is "
                           + value
                     );
                  this.chunks[i][j] = null;
               }
            }
         }
      }
   }

   @Override
   public final boolean isCached(int n, int n2) {
      return this.chunks[n >> 4][n2 >> 4] != null;
   }

   @Override
   public final IBlockState getBlock(int n, int n2, int n3) {
      CachedChunk cachedChunk = this.chunks[n >> 4][n3 >> 4];
      return cachedChunk != null ? cachedChunk.getBlock(n & 15, n2, n3 & 15, this.dimension) : null;
   }

   @Override
   public final int getX() {
      return this.x;
   }

   public final synchronized CachedChunk getMostRecentlyCachedChunk() {
      CachedChunk cachedChunk = null;

      for (int i = 0; i < 32; i++) {
         for (int j = 0; j < 32; j++) {
            if (this.chunks[i][j] != null && (cachedChunk == null || this.chunks[i][j].timestamp > cachedChunk.timestamp)) {
               cachedChunk = this.chunks[i][j];
            }
         }
      }

      return cachedChunk;
   }

   public CachedRegion(int n, int n2, int n3) {
      this.x = n;
      this.z = n2;
      this.dirty = false;
      this.dimension = n3;
   }

   public synchronized void loadFromDisk(String str) {
      try {
         Path path = Paths.get(str);
         if (!Files.exists(path)) {
            Files.createDirectories(path);
         }

         Path regionFile = getRegionFile(path, this.x, this.z);
         if (!Files.exists(regionFile)) {
            return;
         }

         System.out.println("Loading region " + this.x + "," + this.z + " from disk " + path);
         long l = System.nanoTime() / 1000000L;

         try (
            FileInputStream fileInputStream = new FileInputStream(regionFile.toFile());
            GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream, 32768);
            DataInputStream in = new DataInputStream(gzipInputStream);
         ) {
            int n = in.readInt();
            if (n != 456022910) {
               throw new IOException("Bad magic value " + n);
            }

            boolean[][] flagsArr = new boolean[32][32];
            BitSet[][] bitSetsArr = new BitSet[32][32];
            Map[][] mapsArr = new Map[32][32];
            IBlockState[][][] statesArrs = new IBlockState[32][32][];
            long[][] longsArr = new long[32][32];

            for (int i = 0; i < 32; i++) {
               for (int j = 0; j < 32; j++) {
                  switch (in.read()) {
                     case 1:
                        byte[] bytes = new byte[16384];
                        in.readFully(bytes);
                        bitSetsArr[i][j] = BitSet.valueOf(bytes);
                        mapsArr[i][j] = new HashMap();
                        statesArrs[i][j] = new IBlockState[256];
                        flagsArr[i][j] = true;
                        break;
                     case 0:
                     default:
                        throw new IOException("Malformed stream");
                  }
               }
            }

            for (int k = 0; k < 32; k++) {
               for (int m = 0; m < 32; m++) {
                  if (flagsArr[k][m]) {
                     for (int idx = 0; idx < 256; idx++) {
                        statesArrs[k][m][idx] = BlockUtils.stringToBlockRequired(in.readUTF()).getDefaultState();
                     }
                  }
               }
            }

            for (int index = 0; index < 32; index++) {
               for (int i2 = 0; i2 < 32; i2++) {
                  if (flagsArr[index][i2]) {
                     int n3 = in.readShort() & '\uffff';

                     for (int i3 = 0; i3 < n3; i3++) {
                        String str2 = in.readUTF();
                        BlockUtils.stringToBlockRequired(str2);
                        ArrayList list = new ArrayList();
                        mapsArr[index][i2].put(str2, list);
                        int n4 = in.readShort() & '\uffff';
                        if (n4 == 0) {
                           n4 = 65536;
                        }

                        for (int i4 = 0; i4 < n4; i4++) {
                           byte b = in.readByte();
                           int n5 = b & 15;
                           int n6 = b >>> 4 & 15;
                           int n7 = in.readByte() & 255;
                           list.add(new BlockPos(n5, n7, n6));
                        }
                     }
                  }
               }
            }

            for (int i5 = 0; i5 < 32; i5++) {
               for (int i6 = 0; i6 < 32; i6++) {
                  if (flagsArr[i5][i6]) {
                     longsArr[i5][i6] = in.readLong();
                  }
               }
            }

            for (int i7 = 0; i7 < 32; i7++) {
               for (int i8 = 0; i8 < 32; i8++) {
                  if (flagsArr[i7][i8]) {
                     int n8 = this.x;
                     int n9 = this.z;
                     int n10 = i7 + 32 * n8;
                     int n11 = i8 + 32 * n9;
                     this.chunks[i7][i8] = new CachedChunk(n10, n11, bitSetsArr[i7][i8], statesArrs[i7][i8], mapsArr[i7][i8], longsArr[i7][i8]);
                  }
               }
            }
         }

         this.pruneExpiredChunks();
         this.dirty = false;
         long l2 = System.nanoTime() / 1000000L;
         System.out.println("Loaded region successfully in " + (l2 - l) + "ms");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public final synchronized void putChunk(int n, int n2, CachedChunk cachedChunk) {
      this.chunks[n][n2] = cachedChunk;
      this.dirty = true;
   }

   private static Path getRegionFile(Path path, int n, int n2) {
      return Paths.get(path.toString(), "r." + n + "." + n2 + ".bcr");
   }

   public final ArrayList getLocationsOf(String str) {
      ArrayList list = new ArrayList();

      for (int i = 0; i < 32; i++) {
         for (int j = 0; j < 32; j++) {
            if (this.chunks[i][j] != null) {
               ArrayList locationsOf = this.chunks[i][j].getLocationsOf(str);
               if (locationsOf != null) {
                  list.addAll(locationsOf);
               }
            }
         }
      }

      return list;
   }
}
