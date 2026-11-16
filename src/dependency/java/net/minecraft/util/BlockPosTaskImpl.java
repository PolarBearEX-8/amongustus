package net.minecraft.util;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;

public final class BlockPosTaskImpl implements Callable {
   public final BlockPos blockPos;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return CrashReportCategory.resolveText(this.blockPos);
   }

   public BlockPosTaskImpl(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }
}
