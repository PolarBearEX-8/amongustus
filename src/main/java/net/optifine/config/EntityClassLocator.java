package net.optifine.config;

import net.minecraft.util.ResourceLocation;
import net.optifine.util.EntityUtils;

public class EntityClassLocator implements IObjectLocator {
   @Override
   public Object getObject(ResourceLocation location) {
      return EntityUtils.getEntityClassByName(location.getResourcePath());
   }
}
