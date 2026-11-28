package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;

public class ChunkCompileTaskGenerator {
   private final RenderChunk renderChunk;
   private final ReentrantLock lock = new ReentrantLock();
   private final List listFinishRunnables = Lists.newArrayList();
   private final ChunkCompileTaskGenerator.Type type;
   private RegionRenderCacheBuilder regionRenderCacheBuilder;
   private CompiledChunk compiledChunk;
   private ChunkCompileTaskGenerator.Status status = ChunkCompileTaskGenerator.Status.PENDING;
   private boolean finished;

   public void finish() {
      this.lock.lock();

      try {
         if (this.type == ChunkCompileTaskGenerator.Type.REBUILD_CHUNK && this.status != ChunkCompileTaskGenerator.Status.DONE) {
            this.renderChunk.setNeedsUpdate(true);
         }

         this.finished = true;
         this.status = ChunkCompileTaskGenerator.Status.DONE;

         for (Runnable runnable : (Iterable<Runnable>)(this.listFinishRunnables)) {
            runnable.run();
         }
      } finally {
         this.lock.unlock();
      }
   }

   public CompiledChunk getCompiledChunk() {
      return this.compiledChunk;
   }

   public RenderChunk getRenderChunk() {
      return this.renderChunk;
   }

   public void addFinishRunnable(Runnable runnable) {
      this.lock.lock();

      try {
         this.listFinishRunnables.add(runnable);
         if (this.finished) {
            runnable.run();
         }
      } finally {
         this.lock.unlock();
      }
   }

   public void setCompiledChunk(CompiledChunk compiledChunk2) {
      this.compiledChunk = compiledChunk2;
   }

   public RegionRenderCacheBuilder getRegionRenderCacheBuilder() {
      return this.regionRenderCacheBuilder;
   }

   public void setStatus(ChunkCompileTaskGenerator.Status status2) {
      this.lock.lock();

      try {
         this.status = status2;
      } finally {
         this.lock.unlock();
      }
   }

   public ChunkCompileTaskGenerator.Status getStatus() {
      return this.status;
   }

   public ChunkCompileTaskGenerator.Type getType() {
      return this.type;
   }

   public ReentrantLock getLock() {
      return this.lock;
   }

   public boolean isFinished() {
      return this.finished;
   }

   public ChunkCompileTaskGenerator(RenderChunk renderChunk2, ChunkCompileTaskGenerator.Type type2) {
      this.renderChunk = renderChunk2;
      this.type = type2;
   }

   public void setRegionRenderCacheBuilder(RegionRenderCacheBuilder regionRenderCacheBuilder2) {
      this.regionRenderCacheBuilder = regionRenderCacheBuilder2;
   }


   public enum Type {
      REBUILD_CHUNK,
      RESORT_TRANSPARENCY;

   }

   public enum Status {
      PENDING,
      COMPILING,
      UPLOADING,
      DONE;

   }
}
