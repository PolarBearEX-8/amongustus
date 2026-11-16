package client.util;

import net.minecraft.util.ResourceLocation;

public interface ResourceLocationCallback {
   Object resolveTarget(ResourceLocation location);
}
