package client.render;

import client.event.PostRenderProcessor;
import client.util.ResourceLocationAdapter;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;

public class RenderItemWrapper implements PostRenderProcessor {
   public final RenderItem renderItem;

   @Override
   public ResourceLocationAdapter resolveResourceLocationAdapter(ItemStack stack) {
      return new ResourceLocationAdapter("spawn_egg", "inventory");
   }

   public RenderItemWrapper(RenderItem renderItem2) {
      this.renderItem = renderItem2;
   }
}
