package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityWitherskull;
import net.minecraft.item.DiamondGem;
import net.minecraft.util.ResourceLocation;

public class CommonResourceLocationEntryAdapter extends EntitySkinLocation {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/wither/wither.png");
   public DiamondGem diamondGem = new DiamondGem();

   public ResourceLocation resolveResourceLocation(EntityWitherskull entityWitherskull) {
      return entityWitherskull.isActive16() ? resourceLocation3 : resourceLocation4;
   }

   public void applyEntityWitherskull(EntityWitherskull entityWitherskull, double posX, double posY, double posZ, float factor, float factor2) {
      GlStateManager.pushMatrix();
      GlStateManager.disableAlpha();
      float factor3 = this.computeFactor(entityWitherskull.prevRotationYaw, entityWitherskull.rotationYaw, factor2);
      float factor4 = entityWitherskull.prevRotationPitch + (entityWitherskull.rotationPitch - entityWitherskull.prevRotationPitch) * factor2;
      GlStateManager.translate((float)posX, (float)posY, (float)posZ);
      float factor5 = 0.0625F;
      GlStateManager.disableLighting();
      GlStateManager.translate3f3(-1.0F, -1.0F, 1.0F);
      GlStateManager.applyState14();
      this.matchesEntity3(entityWitherskull);
      this.diamondGem.applyEntity(entityWitherskull, 0.0F, 0.0F, 0.0F, factor3, factor4, factor5, true);
      GlStateManager.popMatrix();
      super.applyEntity(entityWitherskull, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityWitherskull((EntityWitherskull)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityWitherskull)entity);
   }

   private float computeFactor(float posX, float posY, float posZ) {
      float factor = posY - posX;

      while (factor < -180.0F) {
         factor += 360.0F;
      }

      while (factor >= 180.0F) {
         factor -= 360.0F;
      }

      return posX + posZ * factor;
   }

   public CommonResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
   }
}
