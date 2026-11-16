package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import client.render.WorldRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEnderdragon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class AltResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/enderdragon/dragon.png");
   public BodyRotationManager bodyRotationManager = (BodyRotationManager)this.passiveFloatEntry;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityEnderdragon((EntityEnderdragon)livingEntity, posX, posY, posZ, factor, factor2);
   }

   public void applyEntityEnderdragon(EntityEnderdragon entityEnderdragon, float posX, float posY, float posZ) {
      float factor = (float)entityEnderdragon.resolveDoubleArray(7, posZ)[0];
      float factor2 = (float)(entityEnderdragon.resolveDoubleArray(5, posZ)[1] - entityEnderdragon.resolveDoubleArray(10, posZ)[1]);
      GlStateManager.rotate(-factor, 0.0F, 1.0F, 0.0F);
      if (entityEnderdragon.getEntityId() != -1337) {
         GlStateManager.rotate(factor2 * 10.0F, 1.0F, 0.0F, 0.0F);
      }

      GlStateManager.translate(0.0F, 0.0F, 1.0F);
      if (entityEnderdragon.indexOffset29 > 0) {
         float factor3 = MathHelper.sin(((float)entityEnderdragon.indexOffset29 + posZ - 1.0F) / 20.0F * 1.6F);
         if (factor3 > 1.0F) {
            factor3 = 1.0F;
         }

         GlStateManager.rotate(factor3 * this.computeFactor(entityEnderdragon), 0.0F, 0.0F, 1.0F);
      }
   }

   public void applyEntityEnderdragon(
      EntityEnderdragon entityEnderdragon, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag, boolean flag2
   ) {
      if (entityEnderdragon.count28 > 0) {
         float factor = (float)entityEnderdragon.count28 / 200.0F;
         GlStateManager.glDepthFunc(515);
         GlStateManager.applyState14();
         GlStateManager.alphaFunc(516, factor);
         this.applyResourceLocation2(resourceLocation3);
         this.passiveFloatEntry.applyEntity(entityEnderdragon, posX, posX2, posX3, posX4, posY, posZ, true);
         GlStateManager.alphaFunc(516, 0.1F);
         GlStateManager.glDepthFunc(514);
      }

      this.matchesEntity3(entityEnderdragon);
      this.passiveFloatEntry.applyEntity(entityEnderdragon, posX, posX2, posX3, posX4, posY, posZ, true);
      if (entityEnderdragon.hurtTime > 0) {
         GlStateManager.glDepthFunc(514);
         GlStateManager.enableTexture2D();
         GlStateManager.disableBlend();
         GlStateManager.glStateAction3(770, 771);
         GlStateManager.color4f2(1.0F, 0.0F, 0.0F, 0.5F);
         this.passiveFloatEntry.applyEntity(entityEnderdragon, posX, posX2, posX3, posX4, posY, posZ, true);
         GlStateManager.disableTexture2D();
         GlStateManager.enableBlend();
         GlStateManager.glDepthFunc(515);
      }
   }

   public ResourceLocation resolveResourceLocation(EntityEnderdragon entityEnderdragon) {
      return resourceLocation4;
   }

   public void applyEntityEnderdragon(EntityEnderdragon entityEnderdragon, double posX, double posY, double posZ, float factor) {
      float factor2 = MathHelper.abs(((float)entityEnderdragon.entityEndercrystal.count12 + factor) * 0.2F) / 2.0F + 0.5F;
      float factor3 = (factor2 * factor2 + factor2) * 0.2F;
      float factor4 = (float)(entityEnderdragon.entityEndercrystal.posX - entityEnderdragon.posX - (entityEnderdragon.prevPosX - entityEnderdragon.posX) * (double)(1.0F - factor));
      float factor5 = (float)((double)factor3 + entityEnderdragon.entityEndercrystal.posY - 1.0 - entityEnderdragon.posY - (entityEnderdragon.prevPosY - entityEnderdragon.posY) * (double)(1.0F - factor));
      float factor6 = (float)(entityEnderdragon.entityEndercrystal.posZ - entityEnderdragon.posZ - (entityEnderdragon.prevPosZ - entityEnderdragon.posZ) * (double)(1.0F - factor));
      float factor7 = MathHelper.sin(factor4 * factor4 + factor6 * factor6);
      float factor8 = MathHelper.sin(factor4 * factor4 + factor5 * factor5 + factor6 * factor6);
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX, (float)posY + 2.0F, (float)posZ);
      GlStateManager.rotate((float)(-Math.atan2((double)factor6, (double)factor4)) * 180.0F / (float) Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate((float)(-Math.atan2((double)factor7, (double)factor5)) * 180.0F / (float) Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableAlpha();
      this.applyResourceLocation2(resourceLocation5);
      GlStateManager.glShadeModel(7425);
      float factor9 = 0.0F - ((float)entityEnderdragon.ticksExisted + factor) * 0.01F;
      float factor10 = MathHelper.sin(factor4 * factor4 + factor5 * factor5 + factor6 * factor6) / 32.0F - ((float)entityEnderdragon.ticksExisted + factor) * 0.01F;
      worldRenderer.updateAuxiliaryLoggerEntry(5, DefaultVertexFormats.auxiliaryLoggerEntry6);

      for (int index = 0; index <= 8; index++) {
         float factor11 = MathHelper.abs((float)(index % 8) * (float) Math.PI * 2.0F / 8.0F) * 0.75F;
         float factor12 = MathHelper.cos((float)(index % 8) * (float) Math.PI * 2.0F / 8.0F) * 0.75F;
         float factor13 = (float)(index % 8) * 1.0F / 8.0F;
         worldRenderer.getWorldRenderer((double)(factor11 * 0.2F), (double)(factor12 * 0.2F), 0.0)
            .getWorldRenderer6((double)factor13, (double)factor10)
            .resolveWorldRenderer(0, 0, 0, 255)
            .updateState();
         worldRenderer.getWorldRenderer((double)factor11, (double)factor12, (double)factor8)
            .getWorldRenderer6((double)factor13, (double)factor9)
            .resolveWorldRenderer(255, 255, 255, 255)
            .updateState();
      }

      tessellator.updateState();
      GlStateManager.enableLighting();
      GlStateManager.glShadeModel(7424);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.popMatrix();
   }

   public void applyEntityEnderdragon(EntityEnderdragon entityEnderdragon, double posX, double posY, double posZ, float factor, float factor2) {
      DefaultFloatEntry.applyPreRenderSubscriber(entityEnderdragon, false);
      super.applyEntityMob(entityEnderdragon, posX, posY, posZ, factor, factor2);
      if (entityEnderdragon.entityEndercrystal != null) {
         this.applyEntityEnderdragon(entityEnderdragon, posX, posY, posZ, factor2);
      }
   }

   @Override
   public void applyEntityMob(EntityMob entityMob, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityEnderdragon((EntityEnderdragon)entityMob, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag, boolean flag2) {
      this.applyEntityEnderdragon((EntityEnderdragon)livingEntity, posX, posX2, posX3, posX4, posY, posZ, flag, flag2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityEnderdragon((EntityEnderdragon)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      this.applyEntityEnderdragon((EntityEnderdragon)livingEntity, posX, posY, posZ);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityEnderdragon)entity);
   }

   public AltResourceLocationEntry(RenderManager renderManager) {
      super(renderManager, new BodyRotationManager(0.0F), 0.5F);
      this.add(new TertiaryResourceLocationEntry(this));
      this.add(new AimSynchronizer());
   }
}
