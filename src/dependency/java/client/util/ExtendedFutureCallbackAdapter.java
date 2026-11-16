package client.util;

import client.setting.PrimaryOptionType;
import com.google.common.util.concurrent.FutureCallback;
import java.util.List;
import java.util.concurrent.CancellationException;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.item.IronShard;
import net.minecraft.item.ItemArrayEntry12Entry;

public class ExtendedFutureCallbackAdapter implements FutureCallback {
   public final ItemArrayEntry12Entry itemArrayEntry12Entry;
   public final IronShard ironShard;
   public final CustomRunnableAdapter customRunnableAdapter;

   public void applyList(List list) {
      CustomRunnableAdapter.applyCustomRunnableAdapter(this.customRunnableAdapter, this.itemArrayEntry12Entry);
      this.itemArrayEntry12Entry.getReentrantLock().lock();

      try {
         if (this.itemArrayEntry12Entry.getPrimaryOptionType() != PrimaryOptionType.UPLOADING) {
            if (!this.itemArrayEntry12Entry.isEnabled()) {
               CustomRunnableAdapter.getLogger()
                  .warn("Chunk render task was " + this.itemArrayEntry12Entry.getPrimaryOptionType() + " when I expected it to be uploading; aborting task");
            }

            return;
         }

         this.itemArrayEntry12Entry.applyPrimaryOptionType(PrimaryOptionType.DONE);
      } finally {
         this.itemArrayEntry12Entry.getReentrantLock().unlock();
      }

      this.itemArrayEntry12Entry.getRenderChunk().applyIronShard(this.ironShard);
   }

   public void onSuccess(Object entry) {
      this.applyList((List)entry);
   }

   public void onFailure(Throwable throwable) {
      CustomRunnableAdapter.applyCustomRunnableAdapter(this.customRunnableAdapter, this.itemArrayEntry12Entry);
      if (!(throwable instanceof CancellationException) && !(throwable instanceof InterruptedException)) {
         Minecraft mc = Minecraft.getMinecraft();
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Rendering chunk");
         mc.applyCrashReport(crashReport);
      }
   }

   public ExtendedFutureCallbackAdapter(CustomRunnableAdapter customRunnableAdapter2, ItemArrayEntry12Entry itemArrayEntry12Entry2, IronShard ironShard2) {
      this.customRunnableAdapter = customRunnableAdapter2;
      this.itemArrayEntry12Entry = itemArrayEntry12Entry2;
      this.ironShard = ironShard2;
   }
}
