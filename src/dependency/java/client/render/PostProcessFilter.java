package client.render;

import client.util.PassiveFloatEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityOzelot;
import net.minecraft.util.ResourceLocation;

public class PostProcessFilter extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/cat/black.png");
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/cat/ocelot.png");
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/entity/cat/red.png");
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/cat/siamese.png");

   public void applyEntityOzelot(EntityOzelot entityOzelot, float factor) {
      super.applyEntityLivingBase(entityOzelot, factor);
      if (entityOzelot.isActive32()) {
         GlStateManager.translate3f3(0.8F, 0.8F, 0.8F);
      }
   }

   public ResourceLocation resolveResourceLocation(EntityOzelot entityOzelot) {
      switch (entityOzelot.getCount10()) {
         case 0:
         default:
            return resourceLocation3;
         case 1:
            return resourceLocation5;
         case 2:
            return resourceLocation6;
         case 3:
            return resourceLocation4;
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityOzelot((EntityOzelot)livingEntity, factor);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityOzelot)entity);
   }

   public PostProcessFilter(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
   }
}
