package client.util;

import client.setting.InternalOptionType;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class ExtraFunctionAdapter implements Function {
   public String resolveText(ItemStack stack) {
      return InternalOptionType.resolveInternalOptionType(stack.getIndexOffset()).getLabelText2();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
