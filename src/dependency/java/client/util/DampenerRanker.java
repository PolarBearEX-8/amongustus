package client.util;

import java.util.Random;
import net.minecraft.item.ItemStack;

public class DampenerRanker extends DampenerSorter {
   public final ItemStack itemStack;
   public float factor;
   public boolean enabled;

   public DampenerRanker getDampenerRanker() {
      this.enabled = true;
      return this;
   }

   public DampenerRanker withFactor(float factor2) {
      this.factor = factor2;
      return this;
   }

   public DampenerRanker(ItemStack stack, int count) {
      super(count);
      this.itemStack = stack;
   }

   public ItemStack resolveItemStack(Random random) {
      ItemStack stack = this.itemStack.getItemStack();
      if (this.factor > 0.0F) {
         int count = (int)(this.factor * (float)this.itemStack.processAction());
         int count2 = stack.processAction() - random.nextInt(random.nextInt(count) + 1);
         if (count2 > count) {
            count2 = count;
         }

         if (count2 < 1) {
            count2 = 1;
         }

         stack.applyIndexByIndex(count2);
      }

      if (this.enabled) {
         LvlManager.resolveItemStack(random, stack, 30);
      }

      return stack;
   }
}
