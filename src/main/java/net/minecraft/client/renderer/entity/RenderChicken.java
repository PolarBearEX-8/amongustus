package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderChicken extends RenderLiving {
   private static final ResourceLocation chickenTextures = new ResourceLocation("textures/entity/chicken.png");

   @Override
   public float handleRotationFloat(EntityLivingBase entity, float f) {
      return this.handleRotationFloat((EntityChicken)entity, f);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityChicken)entity);
   }

   public float handleRotationFloat(EntityChicken entityChicken, float f) {
      float f2 = entityChicken.prevWingRotation + (entityChicken.wingRotation - entityChicken.prevWingRotation) * f;
      float f3 = entityChicken.prevDestPos + (entityChicken.destPos - entityChicken.prevDestPos) * f;
      return (MathHelper.sin(f2) + 1.0F) * f3;
   }

   public RenderChicken(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
   }

   public ResourceLocation getEntityTexture(EntityChicken entityChicken) {
      return chickenTextures;
   }
}
