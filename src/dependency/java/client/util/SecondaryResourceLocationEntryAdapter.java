package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.WorldRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SecondaryResourceLocationEntryAdapter extends EntitySkinLocation {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/arrow.png");

   public void applyEntityArrow(EntityArrow entityArrow, double posX, double posY, double posZ, float factor, float factor2) {
      this.matchesEntity3(entityArrow);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX, (float)posY, (float)posZ);
      GlStateManager.rotate(entityArrow.prevRotationYaw + (entityArrow.rotationYaw - entityArrow.prevRotationYaw) * factor2 - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(entityArrow.prevRotationPitch + (entityArrow.rotationPitch - entityArrow.prevRotationPitch) * factor2, 0.0F, 0.0F, 1.0F);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      float factor3 = 0.0F;
      float factor4 = 0.5F;
      float factor5 = (float)0 / 32.0F;
      float factor6 = (float)5 / 32.0F;
      float factor7 = 0.0F;
      float factor8 = 0.15625F;
      float factor9 = (float)5 / 32.0F;
      float factor10 = (float)10 / 32.0F;
      float factor11 = 0.05625F;
      GlStateManager.disableLighting();
      float factor12 = (float)entityArrow.count15 - factor2;
      if (factor12 > 0.0F) {
         GlStateManager.rotate(-MathHelper.abs(factor12 * 3.0F) * factor12, 0.0F, 0.0F, 1.0F);
      }

      GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate3f3(factor11, factor11, factor11);
      GlStateManager.translate(-4.0F, 0.0F, 0.0F);
      GL11.glNormal3f(factor11, 0.0F, 0.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(-7.0, -2.0, -2.0).getWorldRenderer6((double)factor7, (double)factor9).updateState();
      worldRenderer.getWorldRenderer(-7.0, -2.0, 2.0).getWorldRenderer6((double)factor8, (double)factor9).updateState();
      worldRenderer.getWorldRenderer(-7.0, 2.0, 2.0).getWorldRenderer6((double)factor8, (double)factor10).updateState();
      worldRenderer.getWorldRenderer(-7.0, 2.0, -2.0).getWorldRenderer6((double)factor7, (double)factor10).updateState();
      tessellator.updateState();
      GL11.glNormal3f(-factor11, 0.0F, 0.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(-7.0, 2.0, -2.0).getWorldRenderer6((double)factor7, (double)factor9).updateState();
      worldRenderer.getWorldRenderer(-7.0, 2.0, 2.0).getWorldRenderer6((double)factor8, (double)factor9).updateState();
      worldRenderer.getWorldRenderer(-7.0, -2.0, 2.0).getWorldRenderer6((double)factor8, (double)factor10).updateState();
      worldRenderer.getWorldRenderer(-7.0, -2.0, -2.0).getWorldRenderer6((double)factor7, (double)factor10).updateState();
      tessellator.updateState();

      for (int index = 0; index < 4; index++) {
         GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glNormal3f(0.0F, 0.0F, factor11);
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
         worldRenderer.getWorldRenderer(-8.0, -2.0, 0.0).getWorldRenderer6((double)factor3, (double)factor5).updateState();
         worldRenderer.getWorldRenderer(8.0, -2.0, 0.0).getWorldRenderer6((double)factor4, (double)factor5).updateState();
         worldRenderer.getWorldRenderer(8.0, 2.0, 0.0).getWorldRenderer6((double)factor4, (double)factor6).updateState();
         worldRenderer.getWorldRenderer(-8.0, 2.0, 0.0).getWorldRenderer6((double)factor3, (double)factor6).updateState();
         tessellator.updateState();
      }

      GlStateManager.applyState8();
      GlStateManager.popMatrix();
      super.applyEntity(entityArrow, posX, posY, posZ, factor, factor2);
   }

   public ResourceLocation resolveResourceLocationByEntityArrow(EntityArrow entityArrow) {
      return resourceLocation3;
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityArrow((EntityArrow)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityArrow((EntityArrow)entity);
   }

   public SecondaryResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
   }
}
