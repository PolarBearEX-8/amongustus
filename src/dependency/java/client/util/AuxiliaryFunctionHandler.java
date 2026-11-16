package client.util;

import client.setting.CombatToggleProperty;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class AuxiliaryFunctionHandler implements Function {
   public String resolveText(ItemStack stack) {
      return CombatToggleProperty.resolveCombatToggleProperty(stack.getIndexOffset()).getLabelText2();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
