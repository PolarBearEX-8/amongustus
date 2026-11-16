package client.render;

import client.util.PassiveFloatEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySquid;
import net.minecraft.util.ResourceLocation;

public class TextureAtlasRenderer extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/squid.png");

   public float computeFactor(EntitySquid entitySquid, float factor) {
      return entitySquid.factor48 + (entitySquid.factor49 - entitySquid.factor48) * factor;
   }

   public void applyEntitySquid(EntitySquid entitySquid, float posX, float posY, float posZ) {
      float factor = entitySquid.factor53 + (entitySquid.factor52 - entitySquid.factor53) * posZ;
      float factor2 = entitySquid.factor38 + (entitySquid.factor41 - entitySquid.factor38) * posZ;
      GlStateManager.translate(0.0F, 0.5F, 0.0F);
      GlStateManager.rotate(180.0F - posY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(factor, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(factor2, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, -1.2F, 0.0F);
   }

   public ResourceLocation resolveResourceLocation(EntitySquid entitySquid) {
      return resourceLocation3;
   }

   @Override
   public float computeFactor(EntityLivingBase livingEntity, float factor) {
      return this.computeFactor((EntitySquid)livingEntity, factor);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      this.applyEntitySquid((EntitySquid)livingEntity, posX, posY, posZ);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntitySquid)entity);
   }

   public TextureAtlasRenderer(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
   }
}
