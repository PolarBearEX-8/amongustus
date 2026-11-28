package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.util.ResourceLocation;

public class RenderGiantZombie extends RenderLiving {
   private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
   private float scale;

   @Override
   public void transformHeldFull3DItemLayer() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityGiantZombie)entity, f);
   }

   public void preRenderCallback(EntityGiantZombie entityGiantZombie, float f) {
      GlStateManager.scale(this.scale, this.scale, this.scale);
   }

   public RenderGiantZombie(RenderManager renderManager2, ModelBase modelBase, float f, float f2) {
      super(renderManager2, modelBase, f * f2);
      this.scale = f2;
      this.addLayer(new LayerHeldItem(this));
      this.addLayer(new RenderGiantZombie.DefaultLayerBipedArmor(this, this));
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityGiantZombie)entity);
   }

   public ResourceLocation getEntityTexture(EntityGiantZombie entityGiantZombie) {
      return zombieTextures;
   }


   public static class DefaultLayerBipedArmor extends LayerBipedArmor {
      public final RenderGiantZombie renderGiantZombie;

      public DefaultLayerBipedArmor(RenderGiantZombie renderGiantZombie2, RendererLivingEntity rendererLivingEntity) {
         super(rendererLivingEntity);
         this.renderGiantZombie = renderGiantZombie2;
      }

      @Override
      public void initArmor() {
         this.modelLeggings = new ModelZombie(0.5F, true);
         this.modelArmor = new ModelZombie(1.0F, true);
      }
   }
}
