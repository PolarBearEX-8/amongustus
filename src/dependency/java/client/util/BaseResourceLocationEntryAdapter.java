package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.WorldRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityXporb;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class BaseResourceLocationEntryAdapter extends EntitySkinLocation {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/experience_orb.png");

   public void applyEntityXporb(EntityXporb entityXporb, double posX, double posY, double posZ, float factor, float factor2) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX, (float)posY, (float)posZ);
      this.matchesEntity3(entityXporb);
      int count = entityXporb.getCount4();
      float factor3 = (float)(count % 4 * 16 + 0) / 64.0F;
      float factor4 = (float)(count % 4 * 16 + 16) / 64.0F;
      float factor5 = (float)(count / 4 * 16 + 0) / 64.0F;
      float factor6 = (float)(count / 4 * 16 + 16) / 64.0F;
      float factor7 = 1.0F;
      float factor8 = 0.5F;
      float factor9 = 0.25F;
      int count2 = entityXporb.computeCount(factor2);
      int count3 = count2 % 65536;
      int count4 = count2 / 65536;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count3 / 1.0F, (float)count4 / 1.0F);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      float factor10 = ((float)entityXporb.count12 + factor2) / 2.0F;
      if (BuildManager.isActive33()) {
         factor10 = LoadingManager.computeFactor(factor10);
      }

      int count5 = (int)((MathHelper.abs(factor10 + 0.0F) + 1.0F) * 0.5F * 255.0F);
      int count6 = (int)((MathHelper.abs(factor10 + (float) (Math.PI * 4.0 / 3.0)) + 1.0F) * 0.1F * 255.0F);
      GlStateManager.rotate(180.0F - this.renderManager.scaleFactor, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-this.renderManager.scaleFactor7, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate3f3(0.3F, 0.3F, 0.3F);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry2);
      int count7 = 255;
      if (BuildManager.isActive33()) {
         int count8 = LoadingManager.computeCount(factor10);
         if (count8 >= 0) {
            count5 = count8 >> 16 & 0xFF;
            count7 = count8 >> 8 & 0xFF;
            count6 = count8 >> 0 & 0xFF;
         }
      }

      worldRenderer.getWorldRenderer((double)(0.0F - factor8), (double)(0.0F - factor9), 0.0)
         .getWorldRenderer6((double)factor3, (double)factor6)
         .resolveWorldRenderer(count5, count7, count6, 128)
         .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor7 - factor8), (double)(0.0F - factor9), 0.0)
         .getWorldRenderer6((double)factor4, (double)factor6)
         .resolveWorldRenderer(count5, count7, count6, 128)
         .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor7 - factor8), (double)(1.0F - factor9), 0.0)
         .getWorldRenderer6((double)factor4, (double)factor5)
         .resolveWorldRenderer(count5, count7, count6, 128)
         .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
         .updateState();
      worldRenderer.getWorldRenderer((double)(0.0F - factor8), (double)(1.0F - factor9), 0.0)
         .getWorldRenderer6((double)factor3, (double)factor5)
         .resolveWorldRenderer(count5, count7, count6, 128)
         .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
         .updateState();
      tessellator.updateState();
      GlStateManager.enableBlend();
      GlStateManager.applyState8();
      GlStateManager.popMatrix();
      super.applyEntity(entityXporb, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityXporb((EntityXporb)entity, posX, posY, posZ, factor, factor2);
   }

   public ResourceLocation resolveResourceLocationByEntityXporb(EntityXporb entityXporb) {
      return resourceLocation3;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityXporb((EntityXporb)entity);
   }

   public BaseResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
      this.factor = 0.15F;
      this.factor2 = 0.75F;
   }
}
