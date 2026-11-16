package client.event;

import net.minecraft.util.ResourceLocation;

public interface PreRenderDispatcher {
   void setResourceLocation(ResourceLocation resourceLocation);

   Class getMemberRef();

   void setMemberRef(Class memberRef);

   ResourceLocation getResourceLocation();
}
