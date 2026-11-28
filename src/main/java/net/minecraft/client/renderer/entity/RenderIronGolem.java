package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerIronGolemFlower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.util.ResourceLocation;

public class RenderIronGolem extends RenderLiving {
   private static final ResourceLocation ironGolemTextures = new ResourceLocation("textures/entity/iron_golem.png");

   public RenderIronGolem(RenderManager renderManager2) {
      super(renderManager2, new ModelIronGolem(), 0.5F);
      this.addLayer(new LayerIronGolemFlower(this));
   }

   public void rotateCorpse(EntityIronGolem entityIronGolem, float f, float f2, float f3) {
      super.rotateCorpse(entityIronGolem, f, f2, f3);
      if ((double)entityIronGolem.limbSwingAmount >= 0.01) {
         float f4 = 13.0F;
         float f6 = (Math.abs((entityIronGolem.limbSwing - entityIronGolem.limbSwingAmount * (1.0F - f3) + 6.0F) % f4 - f4 * 0.5F) - f4 * 0.25F) / (f4 * 0.25F);
         GlStateManager.rotate(6.5F * f6, 0.0F, 0.0F, 1.0F);
      }
   }

   @Override
   public void rotateCorpse(EntityLivingBase entity, float f, float f2, float f3) {
      this.rotateCorpse((EntityIronGolem)entity, f, f2, f3);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityIronGolem)entity);
   }

   public ResourceLocation getEntityTexture(EntityIronGolem entityIronGolem) {
      return ironGolemTextures;
   }
}
