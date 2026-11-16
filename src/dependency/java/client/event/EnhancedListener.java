package client.event;

import com.google.common.util.concurrent.ListenableFuture;

public interface EnhancedListener {
   ListenableFuture resolveListenableFuture(Runnable task);

   ListenableFuture resolveListenableFutureByRunnable(Runnable task);

   boolean isSingleplayer();
}
