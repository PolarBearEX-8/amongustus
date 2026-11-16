package client.util;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationResourceLocationCallbaImpl implements ResourceLocationCallback {
   @Override
   public Object resolveTarget(ResourceLocation location) {
      return Item.getItem(location.toString());
   }
}
