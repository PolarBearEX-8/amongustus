package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.ResourceLocation;

public class RenderPigZombie extends RenderBiped {
   private static final ResourceLocation ZOMBIE_PIGMAN_TEXTURE = new ResourceLocation("textures/entity/zombie_pigman.png");

   public ResourceLocation getEntityTexture(EntityPigZombie entityPigZombie) {
      return ZOMBIE_PIGMAN_TEXTURE;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityPigZombie)entity);
   }

   public RenderPigZombie(RenderManager renderManager2) {
      super(renderManager2, new ModelZombie(), 0.5F, 1.0F);
      this.addLayer(new LayerHeldItem(this));
      this.addLayer(new RenderPigZombie.DefaultLayerBipedArmor(this, this));
   }

   @Override
   public ResourceLocation getEntityTexture(EntityLiving entityLiving) {
      return this.getEntityTexture((EntityPigZombie)entityLiving);
   }


   public static class DefaultLayerBipedArmor extends LayerBipedArmor {
      public final RenderPigZombie renderPigZombie;

      public DefaultLayerBipedArmor(RenderPigZombie renderPigZombie2, RendererLivingEntity rendererLivingEntity) {
         super(rendererLivingEntity);
         this.renderPigZombie = renderPigZombie2;
      }

      @Override
      public void initArmor() {
         this.modelLeggings = new ModelZombie(0.5F, true);
         this.modelArmor = new ModelZombie(1.0F, true);
      }
   }
}
