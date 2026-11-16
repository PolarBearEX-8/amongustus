package client.util;

import client.setting.CustomOptionType;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class CoreFunctionAdapter implements Function {
   public String resolveText(ItemStack stack) {
      return CustomOptionType.resolveCustomOptionType(stack.getIndexOffset()).getLabelText2();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
