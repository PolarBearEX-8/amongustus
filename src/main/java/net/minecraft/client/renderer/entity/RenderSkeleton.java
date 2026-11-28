package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;

public class RenderSkeleton extends RenderBiped {
   private static final ResourceLocation skeletonTextures = new ResourceLocation("textures/entity/skeleton/skeleton.png");
   private static final ResourceLocation witherSkeletonTextures = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntitySkeleton)entity, f);
   }

   @Override
   public void transformHeldFull3DItemLayer() {
      GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntitySkeleton)entity);
   }

   public RenderSkeleton(RenderManager renderManager2) {
      super(renderManager2, new ModelSkeleton(), 0.5F);
      this.addLayer(new LayerHeldItem(this));
      this.addLayer(new RenderSkeleton.DefaultLayerBipedArmor(this, this));
   }

   @Override
   public ResourceLocation getEntityTexture(EntityLiving entityLiving) {
      return this.getEntityTexture((EntitySkeleton)entityLiving);
   }

   public void preRenderCallback(EntitySkeleton entitySkeleton, float f) {
      if (entitySkeleton.getSkeletonType() == 1) {
         GlStateManager.scale(1.2F, 1.2F, 1.2F);
      }
   }

   public ResourceLocation getEntityTexture(EntitySkeleton entitySkeleton) {
      return entitySkeleton.getSkeletonType() == 1 ? witherSkeletonTextures : skeletonTextures;
   }


   public static class DefaultLayerBipedArmor extends LayerBipedArmor {
      public final RenderSkeleton renderSkeleton;

      public DefaultLayerBipedArmor(RenderSkeleton renderSkeleton2, RendererLivingEntity rendererLivingEntity) {
         super(rendererLivingEntity);
         this.renderSkeleton = renderSkeleton2;
      }

      @Override
      public void initArmor() {
         this.modelLeggings = new ModelSkeleton(0.5F, true);
         this.modelArmor = new ModelSkeleton(1.0F, true);
      }
   }
}
