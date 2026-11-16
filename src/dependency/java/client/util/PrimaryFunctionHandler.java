package client.util;

import client.setting.CombatAlphaProperty;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class PrimaryFunctionHandler implements Function {
   public String resolveText(ItemStack stack) {
      return CombatAlphaProperty.resolveCombatAlphaProperty(stack.getIndexOffset()).getLabelText();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
