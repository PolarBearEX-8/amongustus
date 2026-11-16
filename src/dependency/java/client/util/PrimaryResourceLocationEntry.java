package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGhast;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.DiamondWand;
import net.minecraft.util.ResourceLocation;

public class PrimaryResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/ghast/ghast.png");
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");

   public ResourceLocation resolveResourceLocation(EntityGhast entityGhast) {
      return entityGhast.isActive29() ? resourceLocation3 : resourceLocation4;
   }

   public void applyEntityGhast(EntityGhast entityGhast, float factor) {
      float factor2 = 1.0F;
      float factor3 = (8.0F + factor2) / 2.0F;
      float factor4 = (8.0F + 1.0F / factor2) / 2.0F;
      GlStateManager.translate3f3(factor4, factor3, factor4);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityGhast((EntityGhast)livingEntity, factor);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityGhast)entity);
   }

   public PrimaryResourceLocationEntry(RenderManager renderManager) {
      super(renderManager, new DiamondWand(), 0.5F);
   }
}
