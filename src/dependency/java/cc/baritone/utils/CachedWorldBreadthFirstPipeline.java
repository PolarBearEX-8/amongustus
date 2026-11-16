package cc.baritone.utils;

import client.util.AlertNotificationSound;
import com.google.common.cache.CacheBuilder;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import net.minecraft.util.BlockPos;

public final class CachedWorldBreadthFirstPipeline implements BreadthFirstPipeline, DRNHandler {
   public static final int count = 58594;
   public Long2ObjectMap long2ObjectMap = new Long2ObjectOpenHashMap();
   public final String labelText;
   public final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
   public final Map map = CacheBuilder.newBuilder().softValues().build().asMap();
   public final int count2;

   private void applyRemovingChunkBlockStateSerByRemovingChunkBlo(RemovingChunkBlockStateService removingChunkBlockStateService) {
      if (removingChunkBlockStateService != null) {
         removingChunkBlockStateService.applyTextByText(this.labelText);
      }
   }

   public static void applyCachedWorldBreadthFirstPip(CachedWorldBreadthFirstPipeline cachedWorldBreadthFirstPipeline, BreadthFirstWatcher breadthFirstWatcher) {
      cachedWorldBreadthFirstPipeline.applyBreadthFirstWatcher(breadthFirstWatcher);
   }

   @Override
   public final void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound) {
      AStarVerifier aStarVerifier = new AStarVerifier(alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset);
      if (this.map.put(aStarVerifier, alertNotificationSound) == null) {
         this.linkedBlockingQueue.add(aStarVerifier);
      }
   }

   @Override
   public final java.util.ArrayList resolveArrayList(String text, int count3, int count4, int count5, int count6) {
      java.util.ArrayList list = new java.util.ArrayList();
      int count7 = count4 >> 9;
      int count8 = count5 >> 9;

      for (int index = 0; index <= count6; index++) {
         for (int index2 = -index; index2 <= index; index2++) {
            for (int index3 = -index; index3 <= index; index3++) {
               if (index2 * index2 + index3 * index3 == index) {
                  int count9 = index2 + count7;
                  int count10 = index3 + count8;
                  RemovingChunkBlockStateService removingChunkBlockStateService = this.resolveRemovingChunkBlockStateServi(count9, count10);
                  if (removingChunkBlockStateService != null) {
                     list.addAll(removingChunkBlockStateService.resolveArrayList(text));
                  }
               }
            }
         }

         if (list.size() >= count3) {
            return list;
         }
      }

      return list;
   }

   @Override
   public final boolean checkIndex(int count3, int count4) {
      RemovingChunkBlockStateService removingChunkBlockStateService = this.resolveRemovingChunkBlockStateServiByIndex(count3 >> 9, count4 >> 9);
      return removingChunkBlockStateService == null ? false : removingChunkBlockStateService.checkIndex(count3 & 511, count4 & 511);
   }

   public void applyIndex(int count3, int count4) {
      this.resolveRemovingChunkBlockStateServi(count3, count4);
   }

   public static LinkedBlockingQueue resolveLinkedBlockingQueue(CachedWorldBreadthFirstPipeline cachedWorldBreadthFirstPipeline) {
      return cachedWorldBreadthFirstPipeline.linkedBlockingQueue;
   }

   public CachedWorldBreadthFirstPipeline(Path path, int count3) {
      if (!Files.exists(path)) {
         try {
            Files.createDirectories(path);
         } catch (IOException ioException) {
         }
      }

      this.labelText = path.toString();
      this.count2 = count3;
      System.out.println("Cached world directory: " + path);
      BaritoneManager.getExecutor().execute(new CachedWorldBreadthFiTask(this, null));
      BaritoneManager.getExecutor().execute(this::updateState4);
   }

   public final boolean checkIndex2(int count3, int count4) {
      return this.resolveRemovingChunkBlockStateServiByIndex(count3 >> 9, count4 >> 9) != null;
   }

   @Override
   public BlockStateService resolveBlockStateService(int count3, int count4) {
      return this.resolveRemovingChunkBlockStateServiByIndex(count3, count4);
   }

   public static Map resolveMap(CachedWorldBreadthFirstPipeline cachedWorldBreadthFirstPipeline) {
      return cachedWorldBreadthFirstPipeline.map;
   }

   public final synchronized RemovingChunkBlockStateService resolveRemovingChunkBlockStateServiByIndex(int count3, int count4) {
      return (RemovingChunkBlockStateService)this.long2ObjectMap.get(this.computeTimestamp(count3, count4));
   }

   private void updateState4() {
      try {
         Thread.sleep(30000L);

         while (true) {
            this.updateState();
            Thread.sleep(600000L);
         }
      } catch (InterruptedException interruptedException) {
         interruptedException.printStackTrace();
      }
   }

   private boolean checkIndexByIndex(int count3, int count4) {
      return count3 <= 58594 && count3 >= -58594 && count4 <= 58594 && count4 >= -58594;
   }

   private RemovingChunkBlockStateService resolveRemovingChunkBlockStateServi(int count3, int count4, long value) {
      RemovingChunkBlockStateService removingChunkBlockStateService = new RemovingChunkBlockStateService(count3, count4, this.count2);
      removingChunkBlockStateService.applyTextByText(this.labelText);
      return removingChunkBlockStateService;
   }

   private BlockPos getBlockPos() {
      for (ActiveListener activeListener : (Iterable<ActiveListener>)(Iterable<?>)SettingsTxtManager.getExtraListener().getList()) {
         NativeListener nativeListener = activeListener.getSupplementalListener().getNativeListener();
         if (nativeListener != null && nativeListener.getBreadthFirstPipeline() == this && activeListener.getStaticListener().getEntityPlayerSP() != null) {
            return activeListener.getStaticListener().getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
         }
      }

      BreadthFirstWatcher breadthFirstWatcher = null;

      for (RemovingChunkBlockStateService removingChunkBlockStateService : (Iterable<RemovingChunkBlockStateService>)(Iterable<?>)this.values()) {
         if (removingChunkBlockStateService != null) {
            BreadthFirstWatcher breadthFirstWatcher2 = removingChunkBlockStateService.getBreadthFirstWatcher();
            if (breadthFirstWatcher2 != null && (breadthFirstWatcher == null || breadthFirstWatcher.timestamp < breadthFirstWatcher2.timestamp)) {
               breadthFirstWatcher = breadthFirstWatcher2;
            }
         }
      }

      return breadthFirstWatcher == null ? new BlockPos(0, 0, 0) : new BlockPos((breadthFirstWatcher.count3 << 4) + 8, 0, (breadthFirstWatcher.count4 << 4) + 8);
   }

   @Override
   public final void updateState() {
      if (!(Boolean)BaritoneManager.getSettings().chunkCaching.dataRef) {
         System.out.println("Not saving to disk; chunk caching is disabled.");
         this.values().forEach((item -> CachedWorldBreadthFirstPipeline.applyRemovingChunkBlockStateSer((RemovingChunkBlockStateService)item)));
         this.updateState3();
      } else {
         long value = System.nanoTime() / 1000000L;
         this.values().parallelStream().forEach((item -> this.applyRemovingChunkBlockStateSer2((RemovingChunkBlockStateService)item)));
         long value2 = System.nanoTime() / 1000000L;
         System.out.println("World save took " + (value2 - value) + "ms");
         this.updateState3();
      }
   }

   @Override
   public final void updateState2() {
      long value = System.nanoTime() / 1000000L;
      this.values().forEach((item -> this.applyRemovingChunkBlockStateSerByRemovingChunkBlo((RemovingChunkBlockStateService)item)));
      long value2 = System.nanoTime() / 1000000L;
      System.out.println("World load took " + (value2 - value) + "ms");
   }

   private static void applyRemovingChunkBlockStateSer(RemovingChunkBlockStateService removingChunkBlockStateService) {
      if (removingChunkBlockStateService != null) {
         removingChunkBlockStateService.updateState();
      }
   }

   private synchronized RemovingChunkBlockStateService resolveRemovingChunkBlockStateServi(int count3, int count4) {
      return (RemovingChunkBlockStateService)this.long2ObjectMap
         .computeIfAbsent(this.computeTimestamp(count3, count4), (long key) -> this.resolveRemovingChunkBlockStateServi(count3, count4, key));
   }

   private void applyBreadthFirstWatcher(BreadthFirstWatcher breadthFirstWatcher) {
      this.resolveRemovingChunkBlockStateServi(breadthFirstWatcher.count3 >> 5, breadthFirstWatcher.count4 >> 5).applyBreadthFirstWatcher(breadthFirstWatcher.count3 & 31, breadthFirstWatcher.count4 & 31, breadthFirstWatcher);
   }

   private long computeTimestamp(int count3, int count4) {
      return !this.checkIndexByIndex(count3, count4) ? 0L : (long)count3 & 4294967295L | ((long)count4 & 4294967295L) << 32;
   }

   private synchronized List values() {
      return new java.util.ArrayList(this.long2ObjectMap.values());
   }

   private synchronized void updateState3() {
      if ((Boolean)BaritoneManager.getSettings().pruneRegionsFromRAM.dataRef) {
         BlockPos blockPos = this.getBlockPos();

         for (RemovingChunkBlockStateService removingChunkBlockStateService : (Iterable<RemovingChunkBlockStateService>)(Iterable<?>)this.values()) {
            if (removingChunkBlockStateService != null) {
               int count3 = (removingChunkBlockStateService.getCount2() << 9) + 256 - blockPos.getX();
               int count4 = (removingChunkBlockStateService.getCount3() << 9) + 256 - blockPos.getZ();
               if (Math.sqrt((double)(count3 * count3 + count4 * count4)) > 1024.0) {
                  if (!(Boolean)BaritoneManager.getSettings().censorCoordinates.dataRef) {
                     this.applyTextByText("Deleting cached region " + removingChunkBlockStateService.getCount2() + "," + removingChunkBlockStateService.getCount3() + " from ram");
                  }

                  this.long2ObjectMap.remove(this.computeTimestamp(removingChunkBlockStateService.getCount2(), removingChunkBlockStateService.getCount3()));
               }
            }
         }
      }
   }

   private void applyRemovingChunkBlockStateSer2(RemovingChunkBlockStateService removingChunkBlockStateService) {
      if (removingChunkBlockStateService != null) {
         removingChunkBlockStateService.applyText(this.labelText);
      }
   }
}
