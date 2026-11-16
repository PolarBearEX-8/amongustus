package client.util;

import client.setting.MovementOffsetProperty;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public final class CustomFunctionAdapter implements Function {
   public String resolveText(ItemStack stack) {
      return MovementOffsetProperty.resolveMovementOffsetProperty(stack.getIndexOffset()).getLabelText();
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
