package client.util;

import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public class StandardFunctionAdapter implements Function {
   public final String[] stringArray;

   public String resolveText(ItemStack stack) {
      int indexOffset = stack.getIndexOffset();
      if (indexOffset < 0 || indexOffset >= this.stringArray.length) {
         indexOffset = 0;
      }

      return this.stringArray[indexOffset];
   }

   public StandardFunctionAdapter(String[] textArray) {
      this.stringArray = textArray;
   }

   public Object apply(Object entry) {
      return this.resolveText((ItemStack)entry);
   }
}
