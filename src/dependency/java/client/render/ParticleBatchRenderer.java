package client.render;

import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityChicken;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ParticleBatchRenderer extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/chicken.png");

   @Override
   public float computeFactor(EntityLivingBase livingEntity, float factor) {
      return this.computeFactor((EntityChicken)livingEntity, factor);
   }

   public ResourceLocation resolveResourceLocation(EntityChicken entityChicken) {
      return resourceLocation3;
   }

   public float computeFactor(EntityChicken entityChicken, float factor) {
      float factor2 = entityChicken.factor54 + (entityChicken.factor51 - entityChicken.factor54) * factor;
      float factor3 = entityChicken.factor52 + (entityChicken.factor53 - entityChicken.factor52) * factor;
      return (MathHelper.abs(factor2) + 1.0F) * factor3;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityChicken)entity);
   }

   public ParticleBatchRenderer(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
   }
}
