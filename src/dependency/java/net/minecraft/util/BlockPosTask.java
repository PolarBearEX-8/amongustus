package net.minecraft.util;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;

public final class BlockPosTask implements Callable {
   public final BlockPos blockPos;

   public String getText() throws Exception {
      return CrashReportCategory.resolveText(this.blockPos);
   }

   public BlockPosTask(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
