package client.util;

import client.setting.MovementProperty;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class DefaultFunctionHandler implements Function {
   public String resolveText(ItemStack stack) {
      return MovementProperty.resolveMovementProperty(stack.getIndexOffset() + 4).getLabelText();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
