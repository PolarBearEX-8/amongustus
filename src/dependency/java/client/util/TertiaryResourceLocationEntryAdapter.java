package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashknot;
import net.minecraft.item.DiamondBlade;
import net.minecraft.util.ResourceLocation;

public class TertiaryResourceLocationEntryAdapter extends EntitySkinLocation {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/lead_knot.png");
   public DiamondBlade diamondBlade = new DiamondBlade();

   public ResourceLocation resolveResourceLocation(EntityLeashknot entityLeashknot) {
      return resourceLocation3;
   }

   public void applyEntityLeashknot(EntityLeashknot entityLeashknot, double posX, double posY, double posZ, float factor, float factor2) {
      GlStateManager.pushMatrix();
      GlStateManager.disableAlpha();
      GlStateManager.translate((float)posX, (float)posY, (float)posZ);
      float factor3 = 0.0625F;
      GlStateManager.disableLighting();
      GlStateManager.translate3f3(-1.0F, -1.0F, 1.0F);
      GlStateManager.applyState14();
      this.matchesEntity3(entityLeashknot);
      this.diamondBlade.applyEntity(entityLeashknot, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, factor3, true);
      GlStateManager.popMatrix();
      super.applyEntity(entityLeashknot, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityLeashknot((EntityLeashknot)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityLeashknot)entity);
   }

   public TertiaryResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
   }
}
