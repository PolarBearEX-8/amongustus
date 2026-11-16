package cc.baritone.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Mp4BadHeuristicHandler implements HeuristicHandler {
   public final Path path;
   public final Map map;

   private static long computeTimestamp(ModularListener modularListener) {
      return -modularListener.getTimestamp();
   }

   private synchronized void applyBreadthFirstSystemByBreadthFirstSyst(BreadthFirstSystem breadthFirstSystem) {
      this.map.put(breadthFirstSystem, new HashSet());
      Path path2 = this.path.resolve(breadthFirstSystem.name().toLowerCase() + ".mp4");
      if (Files.exists(path2)) {
         try {
            FileInputStream inputStream = new FileInputStream(path2.toFile());
            Throwable throwable = null;
            boolean flag = false ;

            try {
               flag = true;
               BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
               Throwable throwable2 = null;
               boolean flag2 = false ;

               try {
                  flag2 = true;
                  DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
                  Throwable throwable3 = null;
                  boolean flag3 = false ;

                  try {
                     flag3 = true;
                     long value = dataInputStream.readLong();
                     if (value != 121977993584L) {
                        throw new IOException("Bad magic value " + value);
                     }

                     value = dataInputStream.readLong();

                     while (value-- > 0L) {
                        String text = dataInputStream.readUTF();
                        long value2 = dataInputStream.readLong();
                        int count = dataInputStream.readInt();
                        int count2 = dataInputStream.readInt();
                        int count3 = dataInputStream.readInt();
                        ((Set)this.map.get(breadthFirstSystem)).add(new ExtendedCustom(text, breadthFirstSystem, new BetterBlockPosXSYSZSBlockPos(count, count2, count3), value2));
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
                           bufferedInputStream.close();
                        } catch (Throwable throwable7) {
                           throwable2.addSuppressed(throwable7);
                        }
                     } else {
                        bufferedInputStream.close();
                     }
                  }
               }

               bufferedInputStream.close();
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
         } catch (IOException ioException) {
         }
      }
   }

   @Override
   public ModularListener resolveModularListener(BreadthFirstSystem breadthFirstSystem) {
      return ((ModularListener)((Set)this.map.get(breadthFirstSystem)).stream().min(Comparator.comparingLong(Mp4BadHeuristicHandler::computeTimestamp)).orElse(null));
   }

   public Mp4BadHeuristicHandler(Path path2) {
      this.path = path2;
      if (!Files.exists(path2)) {
         try {
            Files.createDirectories(path2);
         } catch (IOException ioException) {
         }
      }

      System.out.println("Would save waypoints to " + path2);
      this.map = new HashMap();
      this.updateState();
   }

   @Override
   public void applyModularListener(ModularListener modularListener) {
      if (((Set)this.map.get(modularListener.getBreadthFirstSystem())).remove(modularListener)) {
         this.applyBreadthFirstSystem(modularListener.getBreadthFirstSystem());
      }
   }

   private void updateState() {
      for (BreadthFirstSystem breadthFirstSystem : BreadthFirstSystem.values()) {
         this.applyBreadthFirstSystemByBreadthFirstSyst(breadthFirstSystem);
      }
   }

   @Override
   public Set getSet() {
      return (Set)this.map.values().stream().flatMap(items -> ((Collection<?>)items).stream()).collect(Collectors.toSet());
   }

   private synchronized void applyBreadthFirstSystem(BreadthFirstSystem breadthFirstSystem) {
      Path path2 = this.path.resolve(breadthFirstSystem.name().toLowerCase() + ".mp4");

      try {
         FileOutputStream outputStream = new FileOutputStream(path2.toFile());
         Throwable throwable = null;
         boolean flag = false ;

         try {
            flag = true;
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            Throwable throwable2 = null;
            boolean flag2 = false ;

            try {
               flag2 = true;
               DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
               Throwable throwable3 = null;
               boolean flag3 = false ;

               try {
                  flag3 = true;
                  dataOutputStream.writeLong(121977993584L);
                  dataOutputStream.writeLong((long)((Set)this.map.get(breadthFirstSystem)).size());

                  for (ModularListener modularListener : (Iterable<ModularListener>)(Iterable<?>)(Set)this.map.get(breadthFirstSystem)) {
                     dataOutputStream.writeUTF(modularListener.getLabelText());
                     dataOutputStream.writeLong(modularListener.getTimestamp());
                     dataOutputStream.writeInt(modularListener.getBetterBlockPosXSYSZSBlockPos().getX());
                     dataOutputStream.writeInt(modularListener.getBetterBlockPosXSYSZSBlockPos().getY());
                     dataOutputStream.writeInt(modularListener.getBetterBlockPosXSYSZSBlockPos().getZ());
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
                        bufferedOutputStream.close();
                     } catch (Throwable throwable7) {
                        throwable2.addSuppressed(throwable7);
                     }
                  } else {
                     bufferedOutputStream.close();
                  }
               }
            }

            bufferedOutputStream.close();
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
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   @Override
   public void applyModularListenerByModularListener(ModularListener modularListener) {
      if (((Set)this.map.get(modularListener.getBreadthFirstSystem())).add(modularListener)) {
         this.applyBreadthFirstSystem(modularListener.getBreadthFirstSystem());
      }
   }

   @Override
   public Set resolveSet(BreadthFirstSystem breadthFirstSystem) {
      return Collections.unmodifiableSet((Set)this.map.get(breadthFirstSystem));
   }
}
