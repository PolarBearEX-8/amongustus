package client.util;

import net.minecraft.item.ItemArrayEntry12Entry;

public class TertiaryRunnableAdapter implements Runnable {
   public final ItemArrayEntry12Entry itemArrayEntry12Entry;
   public final TertiaryLoggerHolder tertiaryLoggerHolder;

   public TertiaryRunnableAdapter(TertiaryLoggerHolder tertiaryLoggerHolder2, ItemArrayEntry12Entry itemArrayEntry12Entry2) {
      this.tertiaryLoggerHolder = tertiaryLoggerHolder2;
      this.itemArrayEntry12Entry = itemArrayEntry12Entry2;
   }

   @Override
   public void run() {
      TertiaryLoggerHolder.resolveBlockingQueue(this.tertiaryLoggerHolder).remove(this.itemArrayEntry12Entry);
   }
}
