package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;

public class Custom implements Callable {
   public final ItemStack itemStack;
   public final RenderItem renderItem;

   public String getText() throws Exception {
      return String.valueOf(this.itemStack.getItem());
   }

   public Custom(RenderItem renderItem2, ItemStack stack) {
      this.renderItem = renderItem2;
      this.itemStack = stack;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
