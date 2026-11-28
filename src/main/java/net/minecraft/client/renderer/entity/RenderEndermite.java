package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.ResourceLocation;

public class RenderEndermite extends RenderLiving {
   private static final ResourceLocation ENDERMITE_TEXTURES = new ResourceLocation("textures/entity/endermite.png");

   public RenderEndermite(RenderManager renderManager2) {
      super(renderManager2, new ModelEnderMite(), 0.3F);
   }

   public float getDeathMaxRotation(EntityEndermite entityEndermite) {
      return 180.0F;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityEndermite)entity);
   }

   public ResourceLocation getEntityTexture(EntityEndermite entityEndermite) {
      return ENDERMITE_TEXTURES;
   }

   @Override
   public float getDeathMaxRotation(EntityLivingBase entity) {
      return this.getDeathMaxRotation((EntityEndermite)entity);
   }
}
