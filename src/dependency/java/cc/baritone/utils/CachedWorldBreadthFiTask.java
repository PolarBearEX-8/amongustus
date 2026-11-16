package cc.baritone.utils;

import client.util.AlertNotificationSound;

public class CachedWorldBreadthFiTask implements Runnable {
   public final CachedWorldBreadthFirstPipeline cachedWorldBreadthFirstPipeline;

   @Override
   public void run() {
      while (true) {
         try {
            AStarVerifier aStarVerifier = (AStarVerifier)CachedWorldBreadthFirstPipeline.resolveLinkedBlockingQueue(this.cachedWorldBreadthFirstPipeline).take();
            AlertNotificationSound alertNotificationSound = (AlertNotificationSound)CachedWorldBreadthFirstPipeline.resolveMap(this.cachedWorldBreadthFirstPipeline).remove(aStarVerifier);
            if (CachedWorldBreadthFirstPipeline.resolveLinkedBlockingQueue(this.cachedWorldBreadthFirstPipeline).size()
               <= (Integer)BaritoneManager.getSettings().chunkPackerQueueMaxSize.dataRef) {
               BreadthFirstWatcher breadthFirstWatcher = BreadthFirstWatcherHelper.resolveBreadthFirstWatcher(alertNotificationSound);
               CachedWorldBreadthFirstPipeline.applyCachedWorldBreadthFirstPip(this.cachedWorldBreadthFirstPipeline, breadthFirstWatcher);
            }
         } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            return;
         } catch (Throwable throwable) {
            throwable.printStackTrace();
         }
      }
   }

   public CachedWorldBreadthFiTask(CachedWorldBreadthFirstPipeline cachedWorldBreadthFirstPipeline2, Controller4 controller) {
      this(cachedWorldBreadthFirstPipeline2);
   }

   private CachedWorldBreadthFiTask(CachedWorldBreadthFirstPipeline cachedWorldBreadthFirstPipeline2) {
      this.cachedWorldBreadthFirstPipeline = cachedWorldBreadthFirstPipeline2;
   }
}
