package net.minecraft.client.renderer.entity.layers;

import net.minecraft.entity.EntityLivingBase;

public interface LayerRenderer {
   boolean shouldCombineTextures();

   void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7);
}
