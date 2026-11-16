package net.minecraft.block;

import java.util.concurrent.Callable;

public final class BlockTask implements Callable {
   public final int count;
   public final Block block;

   public BlockTask(int count2, Block block2) {
      this.count = count2;
      this.block = block2;
   }

   public String getText() throws Exception {
      try {
         return String.format("ID #%d (%s // %s)", this.count, this.block.getTextAsText(), this.block.getClass().getCanonicalName());
      } catch (Throwable throwable) {
         return "ID #" + this.count;
      }
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
