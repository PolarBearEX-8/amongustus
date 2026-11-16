package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationEntryAdapter extends EntitySkinLocation {
   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      GlStateManager.pushMatrix();
      applyAxisAlignedBB(entity.getBoundingBox2(), posX - entity.lastTickPosX, posY - entity.lastTickPosY, posZ - entity.lastTickPosZ);
      GlStateManager.popMatrix();
      super.applyEntity(entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return null;
   }

   public ResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
   }
}
