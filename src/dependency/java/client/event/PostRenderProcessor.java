package client.event;

import client.util.ResourceLocationAdapter;
import net.minecraft.item.ItemStack;

public interface PostRenderProcessor {
   ResourceLocationAdapter resolveResourceLocationAdapter(ItemStack stack);
}
