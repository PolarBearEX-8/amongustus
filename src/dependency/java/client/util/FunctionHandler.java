package client.util;

import client.setting.SystemOptionType;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class FunctionHandler implements Function {
   public String resolveText(ItemStack stack) {
      return SystemOptionType.resolveSystemOptionType(stack.getIndexOffset()).getLabelText2();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
