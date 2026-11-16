package client.util;

import client.setting.CombatFactorProperty;
import client.setting.CombatProfileProperty;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class NativeFunctionAdapter implements Function {
   public String resolveText(ItemStack stack) {
      return CombatFactorProperty.resolveCombatFactorProperty(CombatProfileProperty.RED, stack.getIndexOffset()).getLabelText2();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
