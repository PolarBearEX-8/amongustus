package client.util;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationResourceLocationCallba implements ResourceLocationCallback {
   @Override
   public Object resolveTarget(ResourceLocation location) {
      return TargetWatcher.get(location.getText1());
   }
}
