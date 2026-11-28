package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.util.ResourceLocation;

public class RenderSilverfish extends RenderLiving {
   private static final ResourceLocation silverfishTextures = new ResourceLocation("textures/entity/silverfish.png");

   public ResourceLocation getEntityTexture(EntitySilverfish entitySilverfish) {
      return silverfishTextures;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntitySilverfish)entity);
   }

   @Override
   public float getDeathMaxRotation(EntityLivingBase entity) {
      return this.getDeathMaxRotation((EntitySilverfish)entity);
   }

   public RenderSilverfish(RenderManager renderManager2) {
      super(renderManager2, new ModelSilverfish(), 0.3F);
   }

   public float getDeathMaxRotation(EntitySilverfish entitySilverfish) {
      return 180.0F;
   }
}
