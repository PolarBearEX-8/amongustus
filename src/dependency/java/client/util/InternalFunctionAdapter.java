package client.util;

import client.setting.SpecialOptionType;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class InternalFunctionAdapter implements Function {
   public String resolveText(ItemStack stack) {
      return SpecialOptionType.resolveSpecialOptionType(stack.getIndexOffset()).getLabelText();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
