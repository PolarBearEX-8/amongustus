package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderCreeper extends RenderLiving {
   private static final ResourceLocation creeperTextures = new ResourceLocation("textures/entity/creeper/creeper.png");

   public ResourceLocation getEntityTexture(EntityCreeper entityCreeper) {
      return creeperTextures;
   }

   public int getColorMultiplier(EntityCreeper entityCreeper, float f, float f2) {
      float creeperFlashIntensity = entityCreeper.getCreeperFlashIntensity(f2);
      if ((int)(creeperFlashIntensity * 10.0F) % 2 == 0) {
         return 0;
      } else {
         int n = (int)(creeperFlashIntensity * 0.2F * 255.0F);
         n = MathHelper.clamp_int(n, 0, 255);
         return n << 24 | 16777215;
      }
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityCreeper)entity, f);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityCreeper)entity);
   }

   @Override
   public int getColorMultiplier(EntityLivingBase entity, float f, float f2) {
      return this.getColorMultiplier((EntityCreeper)entity, f, f2);
   }

   public void preRenderCallback(EntityCreeper entityCreeper, float f) {
      float creeperFlashIntensity = entityCreeper.getCreeperFlashIntensity(f);
      float f2 = 1.0F + MathHelper.sin(creeperFlashIntensity * 100.0F) * creeperFlashIntensity * 0.01F;
      creeperFlashIntensity = MathHelper.clamp_float(creeperFlashIntensity, 0.0F, 1.0F);
      creeperFlashIntensity *= creeperFlashIntensity;
      creeperFlashIntensity *= creeperFlashIntensity;
      float f3 = (1.0F + creeperFlashIntensity * 0.4F) * f2;
      GlStateManager.scale(f3, (1.0F + creeperFlashIntensity * 0.1F) / f2, f3);
   }

   public RenderCreeper(RenderManager renderManager2) {
      super(renderManager2, new ModelCreeper(), 0.5F);
      this.addLayer(new LayerCreeperCharge(this));
   }
}
