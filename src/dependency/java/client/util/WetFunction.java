package client.util;

import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class WetFunction implements Function {
   public String resolveText(ItemStack stack) {
      return (stack.getIndexOffset() & 1) == 1 ? "wet" : "dry";
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
