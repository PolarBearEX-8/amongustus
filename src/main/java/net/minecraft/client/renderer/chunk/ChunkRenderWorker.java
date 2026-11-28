package net.minecraft.client.renderer.chunk;

import com.google.common.util.concurrent.FutureCallback;
import java.util.List;
import java.util.concurrent.CancellationException;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumWorldBlockLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkRenderWorker implements Runnable {
   private static final Logger LOGGER = LogManager.getLogger();
   private final ChunkRenderDispatcher chunkRenderDispatcher;
   private final RegionRenderCacheBuilder regionRenderCacheBuilder;

   public void processTask(ChunkCompileTaskGenerator generator) throws InterruptedException {
      generator.getLock().lock();

      try {
         if (generator.getStatus() != ChunkCompileTaskGenerator.Status.PENDING) {
            if (!generator.isFinished()) {
               LOGGER.warn("Chunk render task was " + generator.getStatus() + " when I expected it to be pending; ignoring task");
            }

            return;
         }

         generator.setStatus(ChunkCompileTaskGenerator.Status.COMPILING);
      } finally {
         generator.getLock().unlock();
      }

      Entity renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
      if (renderViewEntity == null) {
         generator.finish();
      } else {
         generator.setRegionRenderCacheBuilder(this.getRegionRenderCacheBuilder());
         float posX = (float)renderViewEntity.posX;
         float f = (float)renderViewEntity.posY + renderViewEntity.getEyeHeight();
         float posZ = (float)renderViewEntity.posZ;
         ChunkCompileTaskGenerator.Type type = generator.getType();
         if (type == ChunkCompileTaskGenerator.Type.REBUILD_CHUNK) {
            generator.getRenderChunk().rebuildChunk(posX, f, posZ, generator);
         } else if (type == ChunkCompileTaskGenerator.Type.RESORT_TRANSPARENCY) {
            generator.getRenderChunk().resortTransparency(posX, f, posZ, generator);
         }

         generator.getLock().lock();

         try {
            if (generator.getStatus() != ChunkCompileTaskGenerator.Status.COMPILING) {
               if (!generator.isFinished()) {
                  LOGGER.warn("Chunk render task was " + generator.getStatus() + " when I expected it to be compiling; aborting task");
               }

               this.freeRenderBuilder(generator);
               return;
            }

            generator.setStatus(ChunkCompileTaskGenerator.Status.UPLOADING);
         } finally {
            generator.getLock().unlock();
         }

         CompiledChunk compiledChunk = generator.getCompiledChunk();
         ArrayList list = Lists.newArrayList();
         boolean flag = OpenGlHelper.useVbo();
         if (type == ChunkCompileTaskGenerator.Type.REBUILD_CHUNK) {
            for (EnumWorldBlockLayer enumWorldBlockLayer : EnumWorldBlockLayer.values()) {
               if (compiledChunk != null && compiledChunk.isLayerStarted(enumWorldBlockLayer)) {
                  RenderChunk renderChunk = generator.getRenderChunk();
                  if (flag || renderChunk instanceof ListedRenderChunk) {
                     list.add(this.chunkRenderDispatcher.uploadChunk(enumWorldBlockLayer, generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(enumWorldBlockLayer), renderChunk, compiledChunk));
                  }
               }
            }
         } else if (type == ChunkCompileTaskGenerator.Type.RESORT_TRANSPARENCY) {
            RenderChunk renderChunk2 = generator.getRenderChunk();
            if (flag || renderChunk2 instanceof ListedRenderChunk) {
               list.add(
                  this.chunkRenderDispatcher
                     .uploadChunk(
                        EnumWorldBlockLayer.TRANSLUCENT,
                        generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(EnumWorldBlockLayer.TRANSLUCENT),
                        renderChunk2,
                        compiledChunk
                     )
               );
            }
         }

         ListenableFuture listenableFuture = Futures.allAsList(list);
         generator.addFinishRunnable(() -> listenableFuture.cancel(false));
         Futures.addCallback(listenableFuture, new ChunkRenderWorker.DefaultFutureCallback(this, generator, compiledChunk), MoreExecutors.directExecutor());
      }
   }

   private RegionRenderCacheBuilder getRegionRenderCacheBuilder() throws InterruptedException {
      return this.regionRenderCacheBuilder != null ? this.regionRenderCacheBuilder : this.chunkRenderDispatcher.allocateRenderBuilder();
   }

   private void freeRenderBuilder(ChunkCompileTaskGenerator generator) {
      if (this.regionRenderCacheBuilder == null) {
         this.chunkRenderDispatcher.freeRenderBuilder(generator.getRegionRenderCacheBuilder());
      }
   }

   public ChunkRenderWorker(ChunkRenderDispatcher chunkRenderDispatcher2, RegionRenderCacheBuilder regionRenderCacheBuilder2) {
      this.chunkRenderDispatcher = chunkRenderDispatcher2;
      this.regionRenderCacheBuilder = regionRenderCacheBuilder2;
   }

   public static Logger accessLOGGER() {
      return LOGGER;
   }

   public ChunkRenderWorker(ChunkRenderDispatcher chunkRenderDispatcher2) {
      this(chunkRenderDispatcher2, (RegionRenderCacheBuilder)null);
   }

   public static void invokeFreeRenderBuilder(ChunkRenderWorker chunkRenderWorker, ChunkCompileTaskGenerator generator) {
      chunkRenderWorker.freeRenderBuilder(generator);
   }

   @Override
   public void run() {
      while (true) {
         try {
            this.processTask(this.chunkRenderDispatcher.getNextChunkUpdate());
         } catch (InterruptedException e) {
            LOGGER.debug("Stopping due to interrupt");
            return;
         } catch (Throwable e2) {
            CrashReport crashReport = CrashReport.makeCrashReport(e2, "Batching chunks");
            Minecraft.getMinecraft().crashed(Minecraft.getMinecraft().addGraphicsAndWorldToCrashReport(crashReport));
            return;
         }
      }
   }


   public static class DefaultFutureCallback implements FutureCallback {
      public final ChunkCompileTaskGenerator compileTask;
      public final CompiledChunk compiledChunk;
      public final ChunkRenderWorker chunkRenderWorker;

      public void onSuccess(Object obj) {
         this.onSuccess2((List)obj);
      }

      public DefaultFutureCallback(ChunkRenderWorker chunkRenderWorker2, ChunkCompileTaskGenerator generator, CompiledChunk compiledChunk2) {
         this.chunkRenderWorker = chunkRenderWorker2;
         this.compileTask = generator;
         this.compiledChunk = compiledChunk2;
      }

      public void onFailure(Throwable t) {
         ChunkRenderWorker.invokeFreeRenderBuilder(this.chunkRenderWorker, this.compileTask);
         if (!(t instanceof CancellationException) && !(t instanceof InterruptedException)) {
            Minecraft.getMinecraft().crashed(CrashReport.makeCrashReport(t, "Rendering chunk"));
         }
      }

      public void onSuccess2(List list) {
         ChunkRenderWorker.invokeFreeRenderBuilder(this.chunkRenderWorker, this.compileTask);
         this.compileTask.getLock().lock();

         try {
            if (this.compileTask.getStatus() != ChunkCompileTaskGenerator.Status.UPLOADING) {
               if (!this.compileTask.isFinished()) {
                  ChunkRenderWorker.accessLOGGER().warn("Chunk render task was " + this.compileTask.getStatus() + " when I expected it to be uploading; aborting task");
               }

               return;
            }

            this.compileTask.setStatus(ChunkCompileTaskGenerator.Status.DONE);
         } finally {
            this.compileTask.getLock().unlock();
         }

         this.compileTask.getRenderChunk().setCompiledChunk(this.compiledChunk);
      }
   }
}
