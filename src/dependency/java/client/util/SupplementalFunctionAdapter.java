package client.util;

import client.setting.CombatTimeoutProperty;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class SupplementalFunctionAdapter implements Function {
   public String resolveText(ItemStack stack) {
      return CombatTimeoutProperty.resolveCombatTimeoutProperty(stack.getIndexOffset()).getLabelText2();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
