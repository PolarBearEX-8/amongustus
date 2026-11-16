package client.util;

import client.render.WorldRendererArrayEntry;
import client.setting.BlockSolidType;
import client.setting.MovementRangeProperty;
import client.setting.PrimaryOptionType;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.item.IronShard;
import net.minecraft.item.ItemArrayEntry12Entry;
import net.minecraft.item.ItemArrayEntryAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomRunnableAdapter implements Runnable {
   public static final Logger logger = LogManager.getLogger();
   public final TertiaryLoggerHolder tertiaryLoggerHolder;
   public final WorldRendererArrayEntry worldRendererArrayEntry;

   private void applyItemArrayEntry12EntryByItemArrayEntry12(ItemArrayEntry12Entry itemArrayEntry12Entry) {
      if (this.worldRendererArrayEntry == null) {
         this.tertiaryLoggerHolder.add(itemArrayEntry12Entry.getWorldRendererArrayEntry());
      }
   }

   private static void cancel(ListenableFuture listenableFuture) {
      listenableFuture.cancel(false);
   }

   public void applyItemArrayEntry12Entry(ItemArrayEntry12Entry itemArrayEntry12Entry) throws InterruptedException {
      itemArrayEntry12Entry.getReentrantLock().lock();

      try {
         if (itemArrayEntry12Entry.getPrimaryOptionType() != PrimaryOptionType.PENDING) {
            if (!itemArrayEntry12Entry.isEnabled()) {
               logger.warn("Chunk render task was " + itemArrayEntry12Entry.getPrimaryOptionType() + " when I expected it to be pending; ignoring task");
            }

            return;
         }

         itemArrayEntry12Entry.applyPrimaryOptionType(PrimaryOptionType.COMPILING);
      } finally {
         itemArrayEntry12Entry.getReentrantLock().unlock();
      }

      Entity renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
      if (renderViewEntity == null) {
         itemArrayEntry12Entry.updateState();
      } else {
         itemArrayEntry12Entry.setWorldRendererArrayEntry(this.getWorldRendererArrayEntry());
         float posX = (float)renderViewEntity.posX;
         float factor = (float)renderViewEntity.posY + renderViewEntity.getHeight2();
         float posZ = (float)renderViewEntity.posZ;
         MovementRangeProperty movementRangeProperty = itemArrayEntry12Entry.getMovementRangeProperty();
         if (movementRangeProperty == MovementRangeProperty.REBUILD_CHUNK) {
            itemArrayEntry12Entry.getRenderChunk().applyItemArrayEntry12Entry(posX, factor, posZ, itemArrayEntry12Entry);
         } else if (movementRangeProperty == MovementRangeProperty.RESORT_TRANSPARENCY) {
            itemArrayEntry12Entry.getRenderChunk().applyItemArrayEntry12EntryByItemArrayEntry12(posX, factor, posZ, itemArrayEntry12Entry);
         }

         itemArrayEntry12Entry.getReentrantLock().lock();

         try {
            if (itemArrayEntry12Entry.getPrimaryOptionType() != PrimaryOptionType.COMPILING) {
               if (!itemArrayEntry12Entry.isEnabled()) {
                  logger.warn("Chunk render task was " + itemArrayEntry12Entry.getPrimaryOptionType() + " when I expected it to be compiling; aborting task");
               }

               this.applyItemArrayEntry12EntryByItemArrayEntry12(itemArrayEntry12Entry);
               return;
            }

            itemArrayEntry12Entry.applyPrimaryOptionType(PrimaryOptionType.UPLOADING);
         } finally {
            itemArrayEntry12Entry.getReentrantLock().unlock();
         }

         IronShard ironShard = itemArrayEntry12Entry.getIronShard();
         ArrayList list = Lists.newArrayList();
         boolean active = OpenGlHelper.isActive();
         if (movementRangeProperty == MovementRangeProperty.REBUILD_CHUNK) {
            for (BlockSolidType blockSolidType : BlockSolidType.values()) {
               if (ironShard != null && ironShard.ordinalByBlockSolidType(blockSolidType)) {
                  RenderChunk renderChunk = itemArrayEntry12Entry.getRenderChunk();
                  if (active || renderChunk instanceof ItemArrayEntryAdapter) {
                     list.add(this.tertiaryLoggerHolder.resolveListenableFuture(blockSolidType, itemArrayEntry12Entry.getWorldRendererArrayEntry().ordinal(blockSolidType), renderChunk, ironShard));
                  }
               }
            }
         } else if (movementRangeProperty == MovementRangeProperty.RESORT_TRANSPARENCY) {
            RenderChunk renderChunk2 = itemArrayEntry12Entry.getRenderChunk();
            if (active || renderChunk2 instanceof ItemArrayEntryAdapter) {
               list.add(
                  this.tertiaryLoggerHolder
                     .resolveListenableFuture(BlockSolidType.TRANSLUCENT, itemArrayEntry12Entry.getWorldRendererArrayEntry().ordinal(BlockSolidType.TRANSLUCENT), renderChunk2, ironShard)
               );
            }
         }

         ListenableFuture listenableFuture = Futures.allAsList(list);
         itemArrayEntry12Entry.applyRunnable(() -> cancel(listenableFuture));
         Futures.addCallback(listenableFuture, new ExtendedFutureCallbackAdapter(this, itemArrayEntry12Entry, ironShard), MoreExecutors.directExecutor());
      }
   }

   public static void applyCustomRunnableAdapter(CustomRunnableAdapter customRunnableAdapter, ItemArrayEntry12Entry itemArrayEntry12Entry) {
      customRunnableAdapter.applyItemArrayEntry12EntryByItemArrayEntry12(itemArrayEntry12Entry);
   }

   public static Logger getLogger() {
      return logger;
   }

   private WorldRendererArrayEntry getWorldRendererArrayEntry() throws InterruptedException {
      return this.worldRendererArrayEntry != null ? this.worldRendererArrayEntry : this.tertiaryLoggerHolder.take();
   }

   public CustomRunnableAdapter(TertiaryLoggerHolder tertiaryLoggerHolder2, WorldRendererArrayEntry worldRendererArrayEntry2) {
      this.tertiaryLoggerHolder = tertiaryLoggerHolder2;
      this.worldRendererArrayEntry = worldRendererArrayEntry2;
   }

   public CustomRunnableAdapter(TertiaryLoggerHolder tertiaryLoggerHolder2) {
      this(tertiaryLoggerHolder2, (WorldRendererArrayEntry)null);
   }

   @Override
   public void run() {
      while (true) {
         try {
            this.applyItemArrayEntry12Entry(this.tertiaryLoggerHolder.takeAsItemArrayEntry12());
         } catch (InterruptedException interruptedException) {
            logger.debug("Stopping due to interrupt");
            return;
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Batching chunks");
            Minecraft mc = Minecraft.getMinecraft();
            crashReport = Minecraft.getMinecraft().resolveCrashReport(crashReport);
            mc.applyCrashReport(crashReport);
            return;
         }
      }
   }
}
