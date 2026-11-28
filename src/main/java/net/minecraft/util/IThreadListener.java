package net.minecraft.util;

import com.google.common.util.concurrent.ListenableFuture;

public interface IThreadListener {
   ListenableFuture addScheduledTaskDirect(Runnable runnable);

   boolean isCallingFromMinecraftThread();

   ListenableFuture addScheduledTask(Runnable runnable);
}
