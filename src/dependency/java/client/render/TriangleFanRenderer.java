package client.render;

import client.util.CompositeIntArrayEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEndermite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class TriangleFanRenderer extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/endermite.png");

   public float computeFactor(EntityEndermite entityEndermite) {
      return 180.0F;
   }

   @Override
   public float computeFactor(EntityLivingBase livingEntity) {
      return this.computeFactor((EntityEndermite)livingEntity);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityEndermite)entity);
   }

   public ResourceLocation resolveResourceLocation(EntityEndermite entityEndermite) {
      return resourceLocation3;
   }

   public TriangleFanRenderer(RenderManager renderManager) {
      super(renderManager, new CompositeIntArrayEntry(), 0.3F);
   }
}
