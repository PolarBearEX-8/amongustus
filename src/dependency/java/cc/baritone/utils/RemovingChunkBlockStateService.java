package cc.baritone.utils;

import client.util.IBlockState;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraft.util.BlockPos;

public final class RemovingChunkBlockStateService implements BlockStateService {
   public static final byte byteFlag = 0;
   public static final byte byteFlag2 = 1;
   public static final int count = 456022910;
   public final BreadthFirstWatcher[][] breadthFirstWatcherArray = new BreadthFirstWatcher[32][32];
   public final int count2;
   public final int count3;
   public final int count4;
   public boolean enabled;

   public final synchronized void updateState() {
      long dataRef = (Long)BaritoneManager.getSettings().cachedChunksExpirySeconds.dataRef;
      if (dataRef >= 0L) {
         long timestamp = System.currentTimeMillis();
         long value = timestamp - dataRef * 1000L;

         for (int index = 0; index < 32; index++) {
            for (int index2 = 0; index2 < 32; index2++) {
               if (this.breadthFirstWatcherArray[index][index2] != null && this.breadthFirstWatcherArray[index][index2].timestamp < value) {
                  System.out
                     .println(
                        "Removing chunk "
                           + (index + 32 * this.count2)
                           + ","
                           + (index2 + 32 * this.count3)
                           + " because it was cached "
                           + (timestamp - this.breadthFirstWatcherArray[index][index2].timestamp) / 1000L
                           + " seconds ago, and max age is "
                           + dataRef
                     );
                  this.breadthFirstWatcherArray[index][index2] = null;
               }
            }
         }
      }
   }

   public synchronized void applyTextByText(String text) {
      try {
         Path path = Paths.get(text);
         if (!Files.exists(path)) {
            Files.createDirectories(path);
         }

         Path path2 = resolvePath(path, this.count2, this.count3);
         if (!Files.exists(path2)) {
            return;
         }

         System.out.println("Loading region " + this.count2 + "," + this.count3 + " from disk " + path);
         long value = System.nanoTime() / 1000000L;
         FileInputStream inputStream = new FileInputStream(path2.toFile());
         Throwable throwable = null;
         boolean flag = false ;

         try {
            flag = true;
            GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream, 32768);
            Throwable throwable2 = null;
            boolean flag2 = false ;

            try {
               flag2 = true;
               DataInputStream dataInputStream = new DataInputStream(gZIPInputStream);
               Throwable throwable3 = null;
               boolean flag3 = false ;

               try {
                  flag3 = true;
                  int count5 = dataInputStream.readInt();
                  if (count5 != 456022910) {
                     throw new IOException("Bad magic value " + count5);
                  }

                  boolean[][] flagMatrix = new boolean[32][32];
                  BitSet[][] bitSetMatrix = new BitSet[32][32];
                  Map[][] mapMatrix = new Map[32][32];
                  IBlockState[][][] blockStateMatrix = new IBlockState[32][32][];
                  long[][] valueMatrix = new long[32][32];

                  for (int index = 0; index < 32; index++) {
                     for (int index2 = 0; index2 < 32; index2++) {
                        switch (dataInputStream.read()) {
                           case 1:
                              byte[] byteValueArray = new byte[16384];
                              dataInputStream.readFully(byteValueArray);
                              bitSetMatrix[index][index2] = BitSet.valueOf(byteValueArray);
                              mapMatrix[index][index2] = new HashMap();
                              blockStateMatrix[index][index2] = new IBlockState[256];
                              flagMatrix[index][index2] = true;
                              break;
                           case 0:
                           default:
                              throw new IOException("Malformed stream");
                        }
                     }
                  }

                  for (int index3 = 0; index3 < 32; index3++) {
                     for (int index4 = 0; index4 < 32; index4++) {
                        if (flagMatrix[index3][index4]) {
                           for (int index5 = 0; index5 < 256; index5++) {
                              blockStateMatrix[index3][index4][index5] = EnhancedCustom.resolveBlockByText(dataInputStream.readUTF()).getDefaultState();
                           }
                        }
                     }
                  }

                  for (int index6 = 0; index6 < 32; index6++) {
                     for (int index7 = 0; index7 < 32; index7++) {
                        if (flagMatrix[index6][index7]) {
                           int count6 = dataInputStream.readShort() & '\uffff';

                           for (int index8 = 0; index8 < count6; index8++) {
                              String text2 = dataInputStream.readUTF();
                              EnhancedCustom.resolveBlockByText(text2);
                              java.util.ArrayList list = new java.util.ArrayList();
                              mapMatrix[index6][index7].put(text2, list);
                              int count7 = dataInputStream.readShort() & '\uffff';
                              if (count7 == 0) {
                                 count7 = 65536;
                              }

                              for (int index9 = 0; index9 < count7; index9++) {
                                 byte byteValue = dataInputStream.readByte();
                                 int count8 = byteValue & 15;
                                 int count9 = byteValue >>> 4 & 15;
                                 int count10 = dataInputStream.readByte() & 255;
                                 list.add(new BlockPos(count8, count10, count9));
                              }
                           }
                        }
                     }
                  }

                  for (int index10 = 0; index10 < 32; index10++) {
                     for (int index11 = 0; index11 < 32; index11++) {
                        if (flagMatrix[index10][index11]) {
                           valueMatrix[index10][index11] = dataInputStream.readLong();
                        }
                     }
                  }

                  for (int index12 = 0; index12 < 32; index12++) {
                     for (int index13 = 0; index13 < 32; index13++) {
                        if (flagMatrix[index12][index13]) {
                           int count11 = this.count2;
                           int count12 = this.count3;
                           int count13 = index12 + 32 * count11;
                           int count14 = index13 + 32 * count12;
                           this.breadthFirstWatcherArray[index12][index13] = new BreadthFirstWatcher(
                              count13, count14, bitSetMatrix[index12][index13], blockStateMatrix[index12][index13], mapMatrix[index12][index13], valueMatrix[index12][index13]
                           );
                        }
                     }
                  }

                  flag3 = false;
               } catch (Throwable throwable4) {
                  throwable3 = throwable4;
                  throw throwable4;
               } finally {
                  if (flag3) {
                     if (throwable3 != null) {
                        try {
                           dataInputStream.close();
                        } catch (Throwable throwable5) {
                           throwable3.addSuppressed(throwable5);
                        }
                     } else {
                        dataInputStream.close();
                     }
                  }
               }

               dataInputStream.close();
               flag2 = false;
            } catch (Throwable throwable6) {
               throwable2 = throwable6;
               throw throwable6;
            } finally {
               if (flag2) {
                  if (throwable2 != null) {
                     try {
                        gZIPInputStream.close();
                     } catch (Throwable throwable7) {
                        throwable2.addSuppressed(throwable7);
                     }
                  } else {
                     gZIPInputStream.close();
                  }
               }
            }

            gZIPInputStream.close();
            flag = false;
         } catch (Throwable throwable8) {
            throwable = throwable8;
            throw throwable8;
         } finally {
            if (flag) {
               if (throwable != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable throwable9) {
                     throwable.addSuppressed(throwable9);
                  }
               } else {
                  inputStream.close();
               }
            }
         }

         inputStream.close();
         this.updateState();
         this.enabled = false;
         long value2 = System.nanoTime() / 1000000L;
         System.out.println("Loaded region successfully in " + (value2 - value) + "ms");
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   @Override
   public final int getCount2() {
      return this.count2;
   }

   @Override
   public final IBlockState resolveIBlockState(int count5, int count6, int count7) {
      BreadthFirstWatcher breadthFirstWatcher = this.breadthFirstWatcherArray[count5 >> 4][count7 >> 4];
      return breadthFirstWatcher != null ? breadthFirstWatcher.resolveIBlockState(count5 & 15, count6, count7 & 15, this.count4) : null;
   }

   public final synchronized BreadthFirstWatcher getBreadthFirstWatcher() {
      BreadthFirstWatcher breadthFirstWatcher = null;

      for (int index = 0; index < 32; index++) {
         for (int index2 = 0; index2 < 32; index2++) {
            if (this.breadthFirstWatcherArray[index][index2] != null && (breadthFirstWatcher == null || this.breadthFirstWatcherArray[index][index2].timestamp > breadthFirstWatcher.timestamp)) {
               breadthFirstWatcher = this.breadthFirstWatcherArray[index][index2];
            }
         }
      }

      return breadthFirstWatcher;
   }

   public final synchronized void applyText(String text) {
      if (this.enabled) {
         this.updateState();

         try {
            Path path = Paths.get(text);
            if (!Files.exists(path)) {
               Files.createDirectories(path);
            }

            System.out.println("Saving region " + this.count2 + "," + this.count3 + " to disk " + path);
            Path path2 = resolvePath(path, this.count2, this.count3);
            if (!Files.exists(path2)) {
               Files.createFile(path2);
            }

            FileOutputStream outputStream = new FileOutputStream(path2.toFile());
            Throwable throwable = null;
            boolean flag = false ;

            try {
               flag = true;
               GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream, 16384);
               Throwable throwable2 = null;
               boolean flag2 = false ;

               try {
                  flag2 = true;
                  DataOutputStream dataOutputStream = new DataOutputStream(gZIPOutputStream);
                  Throwable throwable3 = null;
                  boolean flag3 = false ;

                  try {
                     flag3 = true;
                     dataOutputStream.writeInt(456022910);

                     for (int index = 0; index < 32; index++) {
                        for (int index2 = 0; index2 < 32; index2++) {
                           BreadthFirstWatcher breadthFirstWatcher = this.breadthFirstWatcherArray[index][index2];
                           if (breadthFirstWatcher == null) {
                              dataOutputStream.write(0);
                           } else {
                              dataOutputStream.write(1);
                              byte[] byteValueArray = breadthFirstWatcher.toByteArray();
                              dataOutputStream.write(byteValueArray);
                              dataOutputStream.write(new byte[16384 - byteValueArray.length]);
                           }
                        }
                     }

                     for (int index3 = 0; index3 < 32; index3++) {
                        for (int index4 = 0; index4 < 32; index4++) {
                           if (this.breadthFirstWatcherArray[index3][index4] != null) {
                              for (int index5 = 0; index5 < 256; index5++) {
                                 dataOutputStream.writeUTF(EnhancedCustom.resolveText(this.breadthFirstWatcherArray[index3][index4].getIBlockStateArray()[index5].getBlock()));
                              }
                           }
                        }
                     }

                     for (int index6 = 0; index6 < 32; index6++) {
                        for (int index7 = 0; index7 < 32; index7++) {
                           if (this.breadthFirstWatcherArray[index6][index7] != null) {
                              Map map = this.breadthFirstWatcherArray[index6][index7].getMap();
                              dataOutputStream.writeShort(map.entrySet().size());

                              for (Entry entry : (Iterable<Entry>)(Iterable<?>)map.entrySet()) {
                                 dataOutputStream.writeUTF((String)entry.getKey());
                                 dataOutputStream.writeShort(((List)entry.getValue()).size());

                                 for (BlockPos blockPos : (Iterable<BlockPos>)(Iterable<?>)(List)entry.getValue()) {
                                    dataOutputStream.writeByte((byte)(blockPos.getZ() << 4 | blockPos.getX()));
                                    dataOutputStream.writeByte((byte)blockPos.getY());
                                 }
                              }
                           }
                        }
                     }

                     for (int index8 = 0; index8 < 32; index8++) {
                        for (int index9 = 0; index9 < 32; index9++) {
                           if (this.breadthFirstWatcherArray[index8][index9] != null) {
                              dataOutputStream.writeLong(this.breadthFirstWatcherArray[index8][index9].timestamp);
                           }
                        }
                     }

                     flag3 = false;
                  } catch (Throwable throwable4) {
                     throwable3 = throwable4;
                     throw throwable4;
                  } finally {
                     if (flag3) {
                        if (throwable3 != null) {
                           try {
                              dataOutputStream.close();
                           } catch (Throwable throwable5) {
                              throwable3.addSuppressed(throwable5);
                           }
                        } else {
                           dataOutputStream.close();
                        }
                     }
                  }

                  dataOutputStream.close();
                  flag2 = false;
               } catch (Throwable throwable6) {
                  throwable2 = throwable6;
                  throw throwable6;
               } finally {
                  if (flag2) {
                     if (throwable2 != null) {
                        try {
                           gZIPOutputStream.close();
                        } catch (Throwable throwable7) {
                           throwable2.addSuppressed(throwable7);
                        }
                     } else {
                        gZIPOutputStream.close();
                     }
                  }
               }

               gZIPOutputStream.close();
               flag = false;
            } catch (Throwable throwable8) {
               throwable = throwable8;
               throw throwable8;
            } finally {
               if (flag) {
                  if (throwable != null) {
                     try {
                        outputStream.close();
                     } catch (Throwable throwable9) {
                        throwable.addSuppressed(throwable9);
                     }
                  } else {
                     outputStream.close();
                  }
               }
            }

            outputStream.close();
            this.enabled = false;
            System.out.println("Saved region successfully");
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }
   }

   public final synchronized void applyBreadthFirstWatcher(int count5, int count6, BreadthFirstWatcher breadthFirstWatcher) {
      this.breadthFirstWatcherArray[count5][count6] = breadthFirstWatcher;
      this.enabled = true;
   }

   public RemovingChunkBlockStateService(int count5, int count6, int count7) {
      this.count2 = count5;
      this.count3 = count6;
      this.enabled = false;
      this.count4 = count7;
   }

   @Override
   public final boolean checkIndex(int count5, int count6) {
      return this.breadthFirstWatcherArray[count5 >> 4][count6 >> 4] != null;
   }

   private static Path resolvePath(Path path, int count5, int count6) {
      return Paths.get(path.toString(), "r." + count5 + "." + count6 + ".bcr");
   }

   @Override
   public final int getCount3() {
      return this.count3;
   }

   public final java.util.ArrayList resolveArrayList(String text) {
      java.util.ArrayList list = new java.util.ArrayList();

      for (int index = 0; index < 32; index++) {
         for (int index2 = 0; index2 < 32; index2++) {
            if (this.breadthFirstWatcherArray[index][index2] != null) {
               java.util.ArrayList list2 = this.breadthFirstWatcherArray[index][index2].resolveArrayList(text);
               if (list2 != null) {
                  list.addAll(list2);
               }
            }
         }
      }

      return list;
   }
}
