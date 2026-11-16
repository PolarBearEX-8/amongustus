package client.render;

import client.util.InternalResourceLocationEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.DiamondOrb;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class QuadBatchRenderer extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/creeper/creeper.png");

   public int computeCount(EntityCreeper entityCreeper, float factor, float factor2) {
      float factor3 = entityCreeper.computeFactor3(factor2);
      return (int)(factor3 * 10.0F) % 2 == 0 ? 0 : MathHelper.getInt12((int)(factor3 * 0.2F * 255.0F), 0, 255) << 24 | 16777215;
   }

   public void applyEntityCreeper(EntityCreeper entityCreeper, float factor) {
      float factor2 = entityCreeper.computeFactor3(factor);
      float factor3 = 1.0F + MathHelper.abs(factor2 * 100.0F) * factor2 * 0.01F;
      float factor4 = MathHelper.clamp_float(factor2, 0.0F, 1.0F);
      float factor5 = factor4 * factor4;
      float factor6 = factor5 * factor5;
      float factor7 = (1.0F + factor6 * 0.4F) * factor3;
      float factor8 = (1.0F + factor6 * 0.1F) / factor3;
      GlStateManager.translate3f3(factor7, factor8, factor7);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityCreeper((EntityCreeper)livingEntity, factor);
   }

   public ResourceLocation resolveResourceLocationByEntityCreeper(EntityCreeper entityCreeper) {
      return resourceLocation3;
   }

   @Override
   public int computeCount(EntityLivingBase livingEntity, float factor, float factor2) {
      return this.computeCount((EntityCreeper)livingEntity, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityCreeper((EntityCreeper)entity);
   }

   public QuadBatchRenderer(RenderManager renderManager) {
      super(renderManager, new DiamondOrb(), 0.5F);
      this.add(new InternalResourceLocationEntry(this));
   }
}
