package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.ResourceLocation;

public class RenderOcelot extends RenderLiving {
   private static final ResourceLocation blackOcelotTextures = new ResourceLocation("textures/entity/cat/black.png");
   private static final ResourceLocation ocelotTextures = new ResourceLocation("textures/entity/cat/ocelot.png");
   private static final ResourceLocation redOcelotTextures = new ResourceLocation("textures/entity/cat/red.png");
   private static final ResourceLocation siameseOcelotTextures = new ResourceLocation("textures/entity/cat/siamese.png");

   public void preRenderCallback(EntityOcelot entityOcelot, float f) {
      super.preRenderCallback(entityOcelot, f);
      if (entityOcelot.isTamed()) {
         GlStateManager.scale(0.8F, 0.8F, 0.8F);
      }
   }

   public RenderOcelot(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
   }

   public ResourceLocation getEntityTexture(EntityOcelot entityOcelot) {
      switch (entityOcelot.getTameSkin()) {
         case 0:
         default:
            return ocelotTextures;
         case 1:
            return blackOcelotTextures;
         case 2:
            return redOcelotTextures;
         case 3:
            return siameseOcelotTextures;
      }
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityOcelot)entity, f);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityOcelot)entity);
   }
}
