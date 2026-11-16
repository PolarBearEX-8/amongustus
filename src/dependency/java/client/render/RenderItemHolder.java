package client.render;

import client.event.PostRenderProcessor;
import client.util.ResourceLocationAdapter;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class RenderItemHolder implements PostRenderProcessor {
   public final RenderItem renderItem;

   @Override
   public ResourceLocationAdapter resolveResourceLocationAdapter(ItemStack stack) {
      return ItemPotion.checkCondition(stack.getIndexOffset())
         ? new ResourceLocationAdapter("bottle_splash", "inventory")
         : new ResourceLocationAdapter("bottle_drinkable", "inventory");
   }

   public RenderItemHolder(RenderItem renderItem2) {
      this.renderItem = renderItem2;
   }
}
