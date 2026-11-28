package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderEnderCrystal extends Render {
   private static final ResourceLocation enderCrystalTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
   public ModelBase modelEnderCrystal = new ModelEnderCrystal(0.0F, true);

   public RenderEnderCrystal(RenderManager renderManager2) {
      super(renderManager2);
      this.shadowSize = 0.5F;
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityEnderCrystal)entity, d, d2, d3, f, f2);
   }

   public ResourceLocation getEntityTexture(EntityEnderCrystal entityEnderCrystal) {
      return enderCrystalTextures;
   }

   public void doRender(EntityEnderCrystal entityEnderCrystal, double d, double d2, double d3, float f, float f2) {
      float f3 = (float)entityEnderCrystal.innerRotation + f2;
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d, (float)d2, (float)d3);
      this.bindTexture(enderCrystalTextures);
      float f4 = MathHelper.sin(f3 * 0.2F) / 2.0F + 0.5F;
      f4 = f4 * f4 + f4;
      this.modelEnderCrystal.render(entityEnderCrystal, 0.0F, f3 * 3.0F, f4 * 0.2F, 0.0F, 0.0F, 0.0625F, true);
      GlStateManager.popMatrix();
      super.doRender(entityEnderCrystal, d, d2, d3, f, f2);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityEnderCrystal)entity);
   }
}
