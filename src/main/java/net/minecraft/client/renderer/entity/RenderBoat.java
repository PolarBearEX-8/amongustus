package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderBoat extends Render {
   private static final ResourceLocation boatTextures = new ResourceLocation("textures/entity/boat.png");
   public ModelBase modelBoat = new ModelBoat();

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityBoat)entity);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityBoat)entity, d, d2, d3, f, f2);
   }

   public RenderBoat(RenderManager renderManager2) {
      super(renderManager2);
      this.shadowSize = 0.5F;
   }

   public ResourceLocation getEntityTexture(EntityBoat entityBoat) {
      return boatTextures;
   }

   public void doRender(EntityBoat entityBoat, double d, double d2, double d3, float f, float f2) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d, (float)d2 + 0.25F, (float)d3);
      GlStateManager.rotate(180.0F - f, 0.0F, 1.0F, 0.0F);
      float f3 = (float)entityBoat.getTimeSinceHit() - f2;
      float f4 = entityBoat.getDamageTaken() - f2;
      if (f4 < 0.0F) {
         f4 = 0.0F;
      }

      if (f3 > 0.0F) {
         GlStateManager.rotate(MathHelper.sin(f3) * f3 * f4 / 10.0F * (float)entityBoat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
      }

      float f5 = 0.75F;
      GlStateManager.scale(f5, f5, f5);
      GlStateManager.scale(1.0F / f5, 1.0F / f5, 1.0F / f5);
      this.bindEntityTexture(entityBoat);
      GlStateManager.scale(-1.0F, -1.0F, 1.0F);
      this.modelBoat.render(entityBoat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, true);
      GlStateManager.popMatrix();
      super.doRender(entityBoat, d, d2, d3, f, f2);
   }
}
