package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.WorldRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPainting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class AdvancedResourceLocationEntryAdapter extends EntitySkinLocation {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/painting/paintings_kristoffer_zetterstrand.png");

   private void applyEntityPainting(EntityPainting entityPainting, float factor, float factor2) {
      int count = MathHelper.ceiling_double_int(entityPainting.posX);
      int count2 = MathHelper.ceiling_double_int(entityPainting.posY + (double)(factor2 / 16.0F));
      int count3 = MathHelper.ceiling_double_int(entityPainting.posZ);
      EnumFacing enumFacing = entityPainting.enumFacing2;
      if (enumFacing == EnumFacing.NORTH) {
         count = MathHelper.ceiling_double_int(entityPainting.posX + (double)(factor / 16.0F));
      }

      if (enumFacing == EnumFacing.WEST) {
         count3 = MathHelper.ceiling_double_int(entityPainting.posZ - (double)(factor / 16.0F));
      }

      if (enumFacing == EnumFacing.SOUTH) {
         count = MathHelper.ceiling_double_int(entityPainting.posX - (double)(factor / 16.0F));
      }

      if (enumFacing == EnumFacing.EAST) {
         count3 = MathHelper.ceiling_double_int(entityPainting.posZ + (double)(factor / 16.0F));
      }

      int count4 = this.renderManager.world.computeCount(count, count2, count3, 0);
      int count5 = count4 % 65536;
      int count6 = count4 / 65536;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count5, (float)count6);
      GlStateManager.translate3f2(1.0F, 1.0F, 1.0F);
   }

   public ResourceLocation resolveResourceLocationByEntityPainting(EntityPainting entityPainting) {
      return resourceLocation3;
   }

   private void applyEntityPainting(EntityPainting entityPainting, int count, int count2, int count3, int count4) {
      float factor = (float)(-count) / 2.0F;
      float factor2 = (float)(-count2) / 2.0F;
      float factor3 = 0.5F;
      float factor4 = 0.75F;
      float factor5 = 0.8125F;
      float factor6 = 0.0F;
      float factor7 = 0.0625F;
      float factor8 = 0.75F;
      float factor9 = 0.8125F;
      float factor10 = 0.001953125F;
      float factor11 = 0.001953125F;
      float factor12 = 0.7519531F;
      float factor13 = 0.7519531F;
      float factor14 = 0.0F;
      float factor15 = 0.0625F;

      for (int index = 0; index < count / 16; index++) {
         for (int index2 = 0; index2 < count2 / 16; index2++) {
            float factor16 = factor + (float)((index + 1) * 16);
            float factor17 = factor + (float)(index * 16);
            float factor18 = factor2 + (float)((index2 + 1) * 16);
            float factor19 = factor2 + (float)(index2 * 16);
            this.applyEntityPainting(entityPainting, (factor16 + factor17) / 2.0F, (factor18 + factor19) / 2.0F);
            float factor20 = (float)(count3 + count - index * 16) / 256.0F;
            float factor21 = (float)(count3 + count - (index + 1) * 16) / 256.0F;
            float factor22 = (float)(count4 + count2 - index2 * 16) / 256.0F;
            float factor23 = (float)(count4 + count2 - (index2 + 1) * 16) / 256.0F;
            Tessellator tessellator = Tessellator.getTessellator();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry12);
            worldRenderer.getWorldRenderer((double)factor16, (double)factor19, (double)(-factor3))
               .getWorldRenderer6((double)factor21, (double)factor22)
               .resolveWorldRenderer(0.0F, 0.0F, -1.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor19, (double)(-factor3))
               .getWorldRenderer6((double)factor20, (double)factor22)
               .resolveWorldRenderer(0.0F, 0.0F, -1.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor18, (double)(-factor3))
               .getWorldRenderer6((double)factor20, (double)factor23)
               .resolveWorldRenderer(0.0F, 0.0F, -1.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor18, (double)(-factor3))
               .getWorldRenderer6((double)factor21, (double)factor23)
               .resolveWorldRenderer(0.0F, 0.0F, -1.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor18, (double)factor3)
               .getWorldRenderer6((double)factor4, (double)factor6)
               .resolveWorldRenderer(0.0F, 0.0F, 1.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor18, (double)factor3)
               .getWorldRenderer6((double)factor5, (double)factor6)
               .resolveWorldRenderer(0.0F, 0.0F, 1.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor19, (double)factor3)
               .getWorldRenderer6((double)factor5, (double)factor7)
               .resolveWorldRenderer(0.0F, 0.0F, 1.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor19, (double)factor3)
               .getWorldRenderer6((double)factor4, (double)factor7)
               .resolveWorldRenderer(0.0F, 0.0F, 1.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor18, (double)(-factor3))
               .getWorldRenderer6((double)factor8, (double)factor10)
               .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor18, (double)(-factor3))
               .getWorldRenderer6((double)factor9, (double)factor10)
               .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor18, (double)factor3)
               .getWorldRenderer6((double)factor9, (double)factor11)
               .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor18, (double)factor3)
               .getWorldRenderer6((double)factor8, (double)factor11)
               .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor19, (double)factor3)
               .getWorldRenderer6((double)factor8, (double)factor10)
               .resolveWorldRenderer(0.0F, -1.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor19, (double)factor3)
               .getWorldRenderer6((double)factor9, (double)factor10)
               .resolveWorldRenderer(0.0F, -1.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor19, (double)(-factor3))
               .getWorldRenderer6((double)factor9, (double)factor11)
               .resolveWorldRenderer(0.0F, -1.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor19, (double)(-factor3))
               .getWorldRenderer6((double)factor8, (double)factor11)
               .resolveWorldRenderer(0.0F, -1.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor18, (double)factor3)
               .getWorldRenderer6((double)factor13, (double)factor14)
               .resolveWorldRenderer(-1.0F, 0.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor19, (double)factor3)
               .getWorldRenderer6((double)factor13, (double)factor15)
               .resolveWorldRenderer(-1.0F, 0.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor19, (double)(-factor3))
               .getWorldRenderer6((double)factor12, (double)factor15)
               .resolveWorldRenderer(-1.0F, 0.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor16, (double)factor18, (double)(-factor3))
               .getWorldRenderer6((double)factor12, (double)factor14)
               .resolveWorldRenderer(-1.0F, 0.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor18, (double)(-factor3))
               .getWorldRenderer6((double)factor13, (double)factor14)
               .resolveWorldRenderer(1.0F, 0.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor19, (double)(-factor3))
               .getWorldRenderer6((double)factor13, (double)factor15)
               .resolveWorldRenderer(1.0F, 0.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor19, (double)factor3)
               .getWorldRenderer6((double)factor12, (double)factor15)
               .resolveWorldRenderer(1.0F, 0.0F, 0.0F)
               .updateState();
            worldRenderer.getWorldRenderer((double)factor17, (double)factor18, (double)factor3)
               .getWorldRenderer6((double)factor12, (double)factor14)
               .resolveWorldRenderer(1.0F, 0.0F, 0.0F)
               .updateState();
            tessellator.updateState();
         }
      }
   }

   public void applyEntityPainting(EntityPainting entityPainting, double posX, double posY, double posZ, float factor, float factor2) {
      GlStateManager.pushMatrix();
      GlStateManager.translate3d2(posX, posY, posZ);
      GlStateManager.rotate(180.0F - factor, 0.0F, 1.0F, 0.0F);
      GlStateManager.disableLighting();
      this.matchesEntity3(entityPainting);
      BezierProfiler bezierProfiler = entityPainting.bezierProfiler;
      float factor3 = 0.0625F;
      GlStateManager.translate3f3(factor3, factor3, factor3);
      this.applyEntityPainting(entityPainting, bezierProfiler.sizeX, bezierProfiler.sizeY, bezierProfiler.offsetX, bezierProfiler.offsetY);
      GlStateManager.applyState8();
      GlStateManager.popMatrix();
      super.applyEntity(entityPainting, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityPainting((EntityPainting)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityPainting((EntityPainting)entity);
   }

   public AdvancedResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
   }
}
