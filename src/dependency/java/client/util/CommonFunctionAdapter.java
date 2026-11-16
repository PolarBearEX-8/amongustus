package client.util;

import client.setting.MovementProperty;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class CommonFunctionAdapter implements Function {
   public String resolveText(ItemStack stack) {
      return MovementProperty.resolveMovementProperty(stack.getIndexOffset()).getLabelText();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
