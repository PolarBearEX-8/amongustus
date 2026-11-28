package net.optifine.entity.model;

import net.minecraft.util.ResourceLocation;

public interface IEntityRenderer {
   Class getEntityClass();

   ResourceLocation getLocationTextureCustom();

   void setLocationTextureCustom(ResourceLocation location);

   void setEntityClass(Class clazz);
}
