package net.minecraft.item;

import client.render.WorldRendererArrayEntry;
import client.setting.MovementRangeProperty;
import client.setting.PrimaryOptionType;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.client.renderer.chunk.RenderChunk;

public class ItemArrayEntry12Entry {
   public final RenderChunk renderChunk;
   public final ReentrantLock reentrantLock = new ReentrantLock();
   public final List list = Lists.newArrayList();
   public final MovementRangeProperty movementRangeProperty;
   public WorldRendererArrayEntry worldRendererArrayEntry;
   public IronShard ironShard;
   public PrimaryOptionType primaryOptionType = PrimaryOptionType.PENDING;
   public boolean enabled;

   public PrimaryOptionType getPrimaryOptionType() {
      return this.primaryOptionType;
   }

   public MovementRangeProperty getMovementRangeProperty() {
      return this.movementRangeProperty;
   }

   public IronShard getIronShard() {
      return this.ironShard;
   }

   public void setIronShard(IronShard ironShard2) {
      this.ironShard = ironShard2;
   }

   public void applyPrimaryOptionType(PrimaryOptionType primaryOptionType2) {
      this.reentrantLock.lock();

      try {
         this.primaryOptionType = primaryOptionType2;
      } finally {
         this.reentrantLock.unlock();
      }
   }

   public void setWorldRendererArrayEntry(WorldRendererArrayEntry worldRendererArrayEntry2) {
      this.worldRendererArrayEntry = worldRendererArrayEntry2;
   }

   public ReentrantLock getReentrantLock() {
      return this.reentrantLock;
   }

   public WorldRendererArrayEntry getWorldRendererArrayEntry() {
      return this.worldRendererArrayEntry;
   }

   public RenderChunk getRenderChunk() {
      return this.renderChunk;
   }

   public void applyRunnable(Runnable task) {
      this.reentrantLock.lock();

      try {
         this.list.add(task);
         if (this.enabled) {
            task.run();
         }
      } finally {
         this.reentrantLock.unlock();
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void updateState() {
      this.reentrantLock.lock();

      try {
         if (this.movementRangeProperty == MovementRangeProperty.REBUILD_CHUNK && this.primaryOptionType != PrimaryOptionType.DONE) {
            this.renderChunk.setEnabled(true);
         }

         this.enabled = true;
         this.primaryOptionType = PrimaryOptionType.DONE;
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            ((Runnable)iterator.next()).run();
         }
      } finally {
         this.reentrantLock.unlock();
      }
   }

   public ItemArrayEntry12Entry(RenderChunk renderChunk2, MovementRangeProperty movementRangeProperty2) {
      this.renderChunk = renderChunk2;
      this.movementRangeProperty = movementRangeProperty2;
   }
}
