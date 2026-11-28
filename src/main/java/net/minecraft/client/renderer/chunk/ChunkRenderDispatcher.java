package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class ChunkRenderDispatcher {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Chunk Batcher %d").setDaemon(true).build();
   private final List listThreadedWorkers = Lists.newArrayList();
   private final BlockingQueue queueChunkUpdates = Queues.newArrayBlockingQueue(100);
   private final BlockingQueue queueFreeRenderBuilders;
   private final WorldVertexBufferUploader worldVertexUploader = new WorldVertexBufferUploader();
   private final VertexBufferUploader vertexUploader = new VertexBufferUploader();
   private final Queue queueChunkUploads = Queues.newArrayDeque();
   private final ChunkRenderWorker renderWorker;
   private final int countRenderBuilders;
   private List listPausedBuilders = new ArrayList();

   public void stopChunkUpdates() {
      this.clearChunkUpdates();

      while (this.runChunkUploads(0L)) {
      }

      ArrayList list = Lists.newArrayList();

      while (list.size() != this.countRenderBuilders) {
         try {
            list.add(this.allocateRenderBuilder());
         } catch (InterruptedException e) {
         }
      }

      this.queueFreeRenderBuilders.addAll(list);
   }

   public void resumeChunkUpdates() {
      this.queueFreeRenderBuilders.addAll(this.listPausedBuilders);
      this.listPausedBuilders.clear();
   }

   public boolean updateChunkNow(RenderChunk renderChunk) {
      renderChunk.getLockCompileTask().lock();

      boolean flag;
      try {
         ChunkCompileTaskGenerator generator = renderChunk.makeCompileTaskChunk();

         try {
            this.renderWorker.processTask(generator);
         } catch (InterruptedException e) {
         }

         flag = true;
      } finally {
         renderChunk.getLockCompileTask().unlock();
      }

      return flag;
   }

   public static BlockingQueue accessQueueChunkUpdates(ChunkRenderDispatcher chunkRenderDispatcher) {
      return chunkRenderDispatcher.queueChunkUpdates;
   }

   public String getDebugInfo() {
      return String.format("pC: %03d, pU: %1d, aB: %1d", this.queueChunkUpdates.size(), this.queueChunkUploads.size(), this.queueFreeRenderBuilders.size());
   }

   public void freeRenderBuilder(RegionRenderCacheBuilder regionRenderCacheBuilder) {
      this.queueFreeRenderBuilders.add(regionRenderCacheBuilder);
   }

   public RegionRenderCacheBuilder allocateRenderBuilder() throws InterruptedException {
      return (RegionRenderCacheBuilder)this.queueFreeRenderBuilders.take();
   }

   private void uploadDisplayList(WorldRenderer worldRenderer, int n, RenderChunk renderChunk) {
      GL11.glNewList(n, 4864);
      GlStateManager.pushMatrix();
      renderChunk.multModelviewMatrix();
      this.worldVertexUploader.draw(worldRenderer);
      GlStateManager.popMatrix();
      GL11.glEndList();
   }

   public ChunkCompileTaskGenerator getNextChunkUpdate() throws InterruptedException {
      return (ChunkCompileTaskGenerator)this.queueChunkUpdates.take();
   }

   public ChunkRenderDispatcher(int n) {
      int n2 = Math.max(1, (int)((double)Runtime.getRuntime().maxMemory() * 0.3) / 10485760);
      int n3 = Math.max(1, MathHelper.clamp_int(Runtime.getRuntime().availableProcessors() - 2, 1, n2 / 5));
      if (n < 0) {
         this.countRenderBuilders = MathHelper.clamp_int(n3 * 8, 1, n2);
      } else {
         this.countRenderBuilders = n;
      }

      for (int i = 0; i < n3; i++) {
         ChunkRenderWorker chunkRenderWorker = new ChunkRenderWorker(this);
         threadFactory.newThread(chunkRenderWorker).start();
         this.listThreadedWorkers.add(chunkRenderWorker);
      }

      this.queueFreeRenderBuilders = Queues.newArrayBlockingQueue(this.countRenderBuilders);

      for (int j = 0; j < this.countRenderBuilders; j++) {
         this.queueFreeRenderBuilders.add(new RegionRenderCacheBuilder());
      }

      this.renderWorker = new ChunkRenderWorker(this, new RegionRenderCacheBuilder());
   }

   public void clearChunkUpdates() {
      while (!this.queueChunkUpdates.isEmpty()) {
         ChunkCompileTaskGenerator generator = (ChunkCompileTaskGenerator)this.queueChunkUpdates.poll();
         if (generator != null) {
            generator.finish();
         }
      }
   }

   public boolean updateTransparencyLater(RenderChunk renderChunk) {
      renderChunk.getLockCompileTask().lock();

      boolean flag;
      try {
         ChunkCompileTaskGenerator generator = renderChunk.makeCompileTaskTransparency();
         if (generator == null) {
            return true;
         }

         generator.addFinishRunnable(new ChunkRenderDispatcher.WorkerTask2(this, generator));
         flag = this.queueChunkUpdates.offer(generator);
      } finally {
         renderChunk.getLockCompileTask().unlock();
      }

      return flag;
   }

   public boolean updateChunkLater(RenderChunk renderChunk) {
      renderChunk.getLockCompileTask().lock();

      boolean flag;
      try {
         ChunkCompileTaskGenerator generator = renderChunk.makeCompileTaskChunk();
         generator.addFinishRunnable(new ChunkRenderDispatcher.WorkerTask(this, generator));
         boolean flag2 = this.queueChunkUpdates.offer(generator);
         if (!flag2) {
            generator.finish();
         }

         flag = flag2;
      } finally {
         renderChunk.getLockCompileTask().unlock();
      }

      return flag;
   }

   public boolean runChunkUploads(long l) {
      boolean flag = false;

      long l2;
      do {
         boolean flag2 = false;
         ListenableFutureTask listenableFutureTask = null;
         synchronized (this.queueChunkUploads) {
            listenableFutureTask = (ListenableFutureTask)this.queueChunkUploads.poll();
         }

         if (listenableFutureTask != null) {
            listenableFutureTask.run();
            flag2 = true;
            flag = true;
         }

         if (l == 0L || !flag2) {
            break;
         }

         l2 = l - System.nanoTime();
      } while (l2 >= 0L);

      return flag;
   }

   public void pauseChunkUpdates() {
      while (this.listPausedBuilders.size() != this.countRenderBuilders) {
         try {
            this.runChunkUploads(Long.MAX_VALUE);
            RegionRenderCacheBuilder regionRenderCacheBuilder = (RegionRenderCacheBuilder)this.queueFreeRenderBuilders.poll(100L, TimeUnit.MILLISECONDS);
            if (regionRenderCacheBuilder != null) {
               this.listPausedBuilders.add(regionRenderCacheBuilder);
            }
         } catch (InterruptedException e) {
         }
      }
   }

   private void uploadVertexBuffer(WorldRenderer worldRenderer, VertexBuffer vertexBuffer) {
      this.vertexUploader.setVertexBuffer(vertexBuffer);
      this.vertexUploader.draw(worldRenderer);
   }

   public boolean hasNoChunkUpdates() {
      return this.queueChunkUpdates.isEmpty() && this.queueChunkUploads.isEmpty();
   }

   public ListenableFuture uploadChunk(EnumWorldBlockLayer enumWorldBlockLayer, WorldRenderer worldRenderer, RenderChunk renderChunk, CompiledChunk compiledChunk) {
      if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
         if (OpenGlHelper.useVbo()) {
            this.uploadVertexBuffer(worldRenderer, renderChunk.getVertexBufferByLayer(enumWorldBlockLayer.ordinal()));
         } else {
            this.uploadDisplayList(worldRenderer, ((ListedRenderChunk)renderChunk).getDisplayList(enumWorldBlockLayer, compiledChunk), renderChunk);
         }

         worldRenderer.setTranslation(0.0, 0.0, 0.0);
         return Futures.immediateFuture(null);
      } else {
         ListenableFutureTask listenableFutureTask = ListenableFutureTask.create(new ChunkRenderDispatcher.WorkerTask3(this, enumWorldBlockLayer, worldRenderer, renderChunk, compiledChunk), null);
         synchronized (this.queueChunkUploads) {
            this.queueChunkUploads.add(listenableFutureTask);
            return listenableFutureTask;
         }
      }
   }

   public ChunkRenderDispatcher() {
      this(-1);
   }


   public static class WorkerTask implements Runnable {
      public final ChunkCompileTaskGenerator compileTask;
      public final ChunkRenderDispatcher chunkRenderDispatcher;

      public WorkerTask(ChunkRenderDispatcher chunkRenderDispatcher2, ChunkCompileTaskGenerator generator) {
         this.chunkRenderDispatcher = chunkRenderDispatcher2;
         this.compileTask = generator;
      }

      @Override
      public void run() {
         ChunkRenderDispatcher.accessQueueChunkUpdates(this.chunkRenderDispatcher).remove(this.compileTask);
      }
   }

   public static class WorkerTask2 implements Runnable {
      public final ChunkCompileTaskGenerator compileTask;
      public final ChunkRenderDispatcher chunkRenderDispatcher;

      @Override
      public void run() {
         ChunkRenderDispatcher.accessQueueChunkUpdates(this.chunkRenderDispatcher).remove(this.compileTask);
      }

      public WorkerTask2(ChunkRenderDispatcher chunkRenderDispatcher2, ChunkCompileTaskGenerator generator) {
         this.chunkRenderDispatcher = chunkRenderDispatcher2;
         this.compileTask = generator;
      }
   }

   public static class WorkerTask3 implements Runnable {
      public final EnumWorldBlockLayer blockLayer;
      public final WorldRenderer worldRenderer;
      public final RenderChunk renderChunk;
      public final CompiledChunk compiledChunk;
      public final ChunkRenderDispatcher chunkRenderDispatcher;

      @Override
      public void run() {
         this.chunkRenderDispatcher.uploadChunk(this.blockLayer, this.worldRenderer, this.renderChunk, this.compiledChunk);
      }

      public WorkerTask3(ChunkRenderDispatcher chunkRenderDispatcher2, EnumWorldBlockLayer enumWorldBlockLayer, WorldRenderer worldRenderer2, RenderChunk renderChunk2, CompiledChunk compiledChunk2) {
         this.chunkRenderDispatcher = chunkRenderDispatcher2;
         this.blockLayer = enumWorldBlockLayer;
         this.worldRenderer = worldRenderer2;
         this.renderChunk = renderChunk2;
         this.compiledChunk = compiledChunk2;
      }
   }
}
