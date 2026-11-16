package client.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLavaslime;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.AdvancedItemArrayEntry;
import net.minecraft.util.ResourceLocation;

public class DepthBufferFilter extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/slime/magmacube.png");

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityLavaslime((EntityLavaslime)livingEntity, factor);
   }

   public ResourceLocation resolveResourceLocation(EntityLavaslime entityLavaslime) {
      return resourceLocation3;
   }

   public void applyEntityLavaslime(EntityLavaslime entityLavaslime, float factor) {
      int count = entityLavaslime.getCount10();
      float factor2 = (entityLavaslime.factor40 + (entityLavaslime.factor44 - entityLavaslime.factor40) * factor) / ((float)count * 0.5F + 1.0F);
      float factor3 = 1.0F / (factor2 + 1.0F);
      float factor4 = (float)count;
      GlStateManager.translate3f3(factor3 * factor4, 1.0F / factor3 * factor4, factor3 * factor4);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityLavaslime)entity);
   }

   public DepthBufferFilter(RenderManager renderManager) {
      super(renderManager, new AdvancedItemArrayEntry(), 0.25F);
   }
}
