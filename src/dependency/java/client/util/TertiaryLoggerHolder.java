package client.util;

import client.render.WorldRenderer;
import client.render.WorldRendererArrayEntry;
import client.setting.BlockSolidType;
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
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.item.DiamondChestplate;
import net.minecraft.item.IronShard;
import net.minecraft.item.ItemArrayEntry12Entry;
import net.minecraft.item.ItemArrayEntryAdapter;
import net.minecraft.item.ItemEntry50Entry;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class TertiaryLoggerHolder {
   public static final Logger logger = LogManager.getLogger();
   public static final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Chunk Batcher %d").setDaemon(true).build();
   public final List list = Lists.newArrayList();
   public final BlockingQueue blockingQueue2 = Queues.newArrayBlockingQueue(100);
   public final BlockingQueue blockingQueue;
   public final DampenerService dampenerService = new DampenerService();
   public final ItemEntry50Entry itemEntry50Entry = new ItemEntry50Entry();
   public final Queue queue = Queues.newArrayDeque();
   public final CustomRunnableAdapter customRunnableAdapter;
   public final int count;
   public List list2 = new ArrayList();

   public void updateState4() {
      while (this.list2.size() != this.count) {
         try {
            this.checkTime(Long.MAX_VALUE);
            WorldRendererArrayEntry worldRendererArrayEntry = (WorldRendererArrayEntry)this.blockingQueue.poll(100L, TimeUnit.MILLISECONDS);
            if (worldRendererArrayEntry != null) {
               this.list2.add(worldRendererArrayEntry);
            }
         } catch (InterruptedException interruptedException) {
         }
      }
   }

   public void add(WorldRendererArrayEntry worldRendererArrayEntry) {
      this.blockingQueue.add(worldRendererArrayEntry);
   }

   public void updateState2() {
      while (!this.blockingQueue2.isEmpty()) {
         ItemArrayEntry12Entry itemArrayEntry12Entry = (ItemArrayEntry12Entry)this.blockingQueue2.poll();
         if (itemArrayEntry12Entry != null) {
            itemArrayEntry12Entry.updateState();
         }
      }
   }

   public void updateState() {
      this.updateState2();

      while (this.checkTime(0L)) {
      }

      ArrayList list3 = Lists.newArrayList();

      while (list3.size() != this.count) {
         try {
            list3.add(this.take());
         } catch (InterruptedException interruptedException) {
         }
      }

      this.blockingQueue.addAll(list3);
   }

   public WorldRendererArrayEntry take() throws InterruptedException {
      return (WorldRendererArrayEntry)this.blockingQueue.take();
   }

   public boolean matchesRenderChunkByRenderChunk(RenderChunk renderChunk) {
      renderChunk.getReentrantLock2().lock();

      try {
         ItemArrayEntry12Entry itemArrayEntry12Entry = renderChunk.getItemArrayEntry12Entry();

         try {
            this.customRunnableAdapter.applyItemArrayEntry12Entry(itemArrayEntry12Entry);
         } catch (InterruptedException interruptedException) {
         }
      } finally {
         renderChunk.getReentrantLock2().unlock();
      }

      return true;
   }

   public void updateState3() {
      this.blockingQueue.addAll(this.list2);
      this.list2.clear();
   }

   public boolean isActive() {
      return this.blockingQueue2.isEmpty() && this.queue.isEmpty();
   }

   private void applyWorldRenderer(WorldRenderer worldRenderer, int count2, RenderChunk renderChunk) {
      GL11.glNewList(count2, 4864);
      GlStateManager.pushMatrix();
      renderChunk.updateState();
      this.dampenerService.applyWorldRenderer(worldRenderer);
      GlStateManager.popMatrix();
      GL11.glEndList();
   }

   public boolean matchesRenderChunk2(RenderChunk renderChunk) {
      renderChunk.getReentrantLock2().lock();

      boolean flag;
      try {
         ItemArrayEntry12Entry itemArrayEntry12Entry = renderChunk.getItemArrayEntry12Entry();
         itemArrayEntry12Entry.applyRunnable(new InternalRunnableAdapter(this, itemArrayEntry12Entry));
         flag = this.blockingQueue2.offer(itemArrayEntry12Entry);
         if (!flag) {
            itemArrayEntry12Entry.updateState();
         }
      } finally {
         renderChunk.getReentrantLock2().unlock();
      }

      return flag;
   }

   public static BlockingQueue resolveBlockingQueue(TertiaryLoggerHolder tertiaryLoggerHolder) {
      return tertiaryLoggerHolder.blockingQueue2;
   }

   public ListenableFuture resolveListenableFuture(BlockSolidType blockSolidType, WorldRenderer worldRenderer, RenderChunk renderChunk, IronShard ironShard) {
      if (Minecraft.getMinecraft().isSingleplayer()) {
         if (OpenGlHelper.isActive()) {
            this.applyWorldRenderer(worldRenderer, renderChunk.resolveDiamondChestplate(blockSolidType.ordinal()));
         } else {
            this.applyWorldRenderer(worldRenderer, ((ItemArrayEntryAdapter)renderChunk).computeCount(blockSolidType, ironShard), renderChunk);
         }

         worldRenderer.applyDouble(0.0, 0.0, 0.0);
         return Futures.immediateFuture(null);
      } else {
         ListenableFutureTask listenableFutureTask = ListenableFutureTask.create(new SecondaryRunnableAdapter(this, blockSolidType, worldRenderer, renderChunk, ironShard), null);
         synchronized (this.queue) {
            this.queue.add(listenableFutureTask);
            return listenableFutureTask;
         }
      }
   }

   public ItemArrayEntry12Entry takeAsItemArrayEntry12() throws InterruptedException {
      return (ItemArrayEntry12Entry)this.blockingQueue2.take();
   }

   private void applyWorldRenderer(WorldRenderer worldRenderer, DiamondChestplate diamondChestplate) {
      this.itemEntry50Entry.setDiamondChestplate(diamondChestplate);
      this.itemEntry50Entry.applyWorldRenderer(worldRenderer);
   }

   public boolean checkTime(long value) {
      boolean flag = false;

      boolean flag2;
      do {
         flag2 = false;
         ListenableFutureTask listenableFutureTask;
         synchronized (this.queue) {
            listenableFutureTask = (ListenableFutureTask)this.queue.poll();
         }

         if (listenableFutureTask != null) {
            listenableFutureTask.run();
            flag2 = true;
            flag = true;
         }
      } while (value != 0L && flag2 && value - System.nanoTime() >= 0L);

      return flag;
   }

   public boolean matchesRenderChunk(RenderChunk renderChunk) {
      renderChunk.getReentrantLock2().lock();

      try {
         ItemArrayEntry12Entry itemArrayEntry12EntryAsItemArrayEntry = renderChunk.getItemArrayEntry12EntryAsItemArrayEntry12();
         if (itemArrayEntry12EntryAsItemArrayEntry != null) {
            itemArrayEntry12EntryAsItemArrayEntry.applyRunnable(new TertiaryRunnableAdapter(this, itemArrayEntry12EntryAsItemArrayEntry));
            return this.blockingQueue2.offer(itemArrayEntry12EntryAsItemArrayEntry);
         }
      } finally {
         renderChunk.getReentrantLock2().unlock();
      }

      return true;
   }

   public String getText() {
      return String.format("pC: %03d, pU: %1d, aB: %1d", this.blockingQueue2.size(), this.queue.size(), this.blockingQueue.size());
   }

   public TertiaryLoggerHolder(int count2) {
      int count3 = Math.max(1, (int)((double)Runtime.getRuntime().maxMemory() * 0.3) / 10485760);
      int count4 = Math.max(1, MathHelper.getInt12(Runtime.getRuntime().availableProcessors() - 2, 1, count3 / 5));
      if (count2 < 0) {
         this.count = MathHelper.getInt12(count4 * 8, 1, count3);
      } else {
         this.count = count2;
      }

      for (int index = 0; index < count4; index++) {
         CustomRunnableAdapter customRunnableAdapter2 = new CustomRunnableAdapter(this);
         threadFactory.newThread(customRunnableAdapter2).start();
         this.list.add(customRunnableAdapter2);
      }

      this.blockingQueue = Queues.newArrayBlockingQueue(this.count);

      for (int index2 = 0; index2 < this.count; index2++) {
         this.blockingQueue.add(new WorldRendererArrayEntry());
      }

      this.customRunnableAdapter = new CustomRunnableAdapter(this, new WorldRendererArrayEntry());
   }

   public TertiaryLoggerHolder() {
      this(-1);
   }
}
