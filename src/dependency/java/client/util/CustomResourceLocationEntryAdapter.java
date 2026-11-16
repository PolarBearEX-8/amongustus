package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.WorldRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class CustomResourceLocationEntryAdapter extends EntitySkinLocation {
   public float factor3;

   public void applyEntityFireball(EntityFireball entityFireball, double posX, double posY, double posZ, float factor, float factor2) {
      GlStateManager.pushMatrix();
      this.matchesEntity3(entityFireball);
      GlStateManager.translate((float)posX, (float)posY, (float)posZ);
      GlStateManager.disableLighting();
      GlStateManager.translate3f3(this.factor3, this.factor3, this.factor3);
      ServiceStringEntry serviceStringEntry = Minecraft.getMinecraft().getRenderItem().getSecondaryMapEntry().resolveServiceStringEntry(Items.fireCharge);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      float factor4 = serviceStringEntry.getFactor5();
      float factor5 = serviceStringEntry.getFactor2();
      float factor6 = serviceStringEntry.getFactor();
      float factor7 = serviceStringEntry.getFactor6();
      GlStateManager.rotate(180.0F - this.renderManager.scaleFactor, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-this.renderManager.scaleFactor7, 1.0F, 0.0F, 0.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry12);
      worldRenderer.getWorldRenderer(-0.5, -0.25, 0.0).getWorldRenderer6((double)factor4, (double)factor7).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(0.5, -0.25, 0.0).getWorldRenderer6((double)factor5, (double)factor7).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(0.5, 0.75, 0.0).getWorldRenderer6((double)factor5, (double)factor6).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(-0.5, 0.75, 0.0).getWorldRenderer6((double)factor4, (double)factor6).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      tessellator.updateState();
      GlStateManager.applyState8();
      GlStateManager.popMatrix();
      super.applyEntity(entityFireball, posX, posY, posZ, factor, factor2);
   }

   public ResourceLocation resolveResourceLocation(EntityFireball entityFireball) {
      return ListenerAdapter27.resourceLocation;
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityFireball((EntityFireball)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityFireball)entity);
   }

   public CustomResourceLocationEntryAdapter(RenderManager renderManager, float factor) {
      super(renderManager);
      this.factor3 = factor;
   }
}
