package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBat;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class GenericResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/bat.png");

   public ResourceLocation resolveResourceLocationByEntityBat(EntityBat entityBat) {
      return resourceLocation3;
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityBat((EntityBat)livingEntity, factor);
   }

   public void applyEntityBat(EntityBat entityBat, float posX, float posY, float posZ) {
      if (!entityBat.isActive29()) {
         GlStateManager.translate(0.0F, MathHelper.cos(posX * 0.3F) * 0.1F, 0.0F);
      } else {
         GlStateManager.translate(0.0F, -0.1F, 0.0F);
      }

      super.applyEntityLivingBase(entityBat, posX, posY, posZ);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      this.applyEntityBat((EntityBat)livingEntity, posX, posY, posZ);
   }

   public void applyEntityBat(EntityBat entityBat, float factor) {
      GlStateManager.translate3f3(0.35F, 0.35F, 0.35F);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityBat((EntityBat)entity);
   }

   public GenericResourceLocationEntry(RenderManager renderManager) {
      super(renderManager, new AuxiliaryFloatEntryAdapter(), 0.25F);
   }
}
