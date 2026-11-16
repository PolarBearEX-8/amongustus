package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBoat;
import net.minecraft.item.SecondaryItemArrayEntry;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class AuxiliaryResourceLocationEntryAdapter extends EntitySkinLocation {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/boat.png");
   public PassiveFloatEntry passiveFloatEntry = new SecondaryItemArrayEntry();

   public void applyEntityBoat(EntityBoat entityBoat, double posX, double posY, double posZ, float factor, float factor2) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX, (float)posY + 0.25F, (float)posZ);
      GlStateManager.rotate(180.0F - factor, 0.0F, 1.0F, 0.0F);
      float factor3 = (float)entityBoat.getCount4() - factor2;
      float factor4 = entityBoat.getFloat() - factor2;
      if (factor4 < 0.0F) {
         factor4 = 0.0F;
      }

      if (factor3 > 0.0F) {
         GlStateManager.rotate(MathHelper.abs(factor3) * factor3 * factor4 / 10.0F * (float)entityBoat.getCount5(), 1.0F, 0.0F, 0.0F);
      }

      float factor5 = 0.75F;
      GlStateManager.translate3f3(factor5, factor5, factor5);
      GlStateManager.translate3f3(1.0F / factor5, 1.0F / factor5, 1.0F / factor5);
      this.matchesEntity3(entityBoat);
      GlStateManager.translate3f3(-1.0F, -1.0F, 1.0F);
      this.passiveFloatEntry.applyEntity(entityBoat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, true);
      GlStateManager.popMatrix();
      super.applyEntity(entityBoat, posX, posY, posZ, factor, factor2);
   }

   public ResourceLocation resolveResourceLocationByEntityBoat(EntityBoat entityBoat) {
      return resourceLocation3;
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityBoat((EntityBoat)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityBoat((EntityBoat)entity);
   }

   public AuxiliaryResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
      this.factor = 0.5F;
   }
}
