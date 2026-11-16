package net.minecraft.crash;

import java.util.concurrent.Callable;

public final class GotTask implements Callable {
   public final int count;

   public GotTask(int count2) {
      this.count = count2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      if (this.count < 0) {
         return "Unknown? (Got " + this.count + ")";
      } else {
         String text = String.format("%4s", Integer.toBinaryString(this.count)).replace(" ", "0");
         return String.format("%1$d / 0x%1$X / 0b%2$s", this.count, text);
      }
   }
}
