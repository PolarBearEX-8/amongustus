package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelLeashKnot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.util.ResourceLocation;

public class RenderLeashKnot extends Render {
   private static final ResourceLocation leashKnotTextures = new ResourceLocation("textures/entity/lead_knot.png");
   public ModelLeashKnot leashKnotModel = new ModelLeashKnot();

   public void doRender(EntityLeashKnot entityLeashKnot, double d, double d2, double d3, float f, float f2) {
      GlStateManager.pushMatrix();
      GlStateManager.disableCull();
      GlStateManager.translate((float)d, (float)d2, (float)d3);
      float f3 = 0.0625F;
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(-1.0F, -1.0F, 1.0F);
      GlStateManager.enableAlpha();
      this.bindEntityTexture(entityLeashKnot);
      this.leashKnotModel.render(entityLeashKnot, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f3, true);
      GlStateManager.popMatrix();
      super.doRender(entityLeashKnot, d, d2, d3, f, f2);
   }

   public RenderLeashKnot(RenderManager renderManager2) {
      super(renderManager2);
   }

   public ResourceLocation getEntityTexture(EntityLeashKnot entityLeashKnot) {
      return leashKnotTextures;
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityLeashKnot)entity, d, d2, d3, f, f2);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityLeashKnot)entity);
   }
}
