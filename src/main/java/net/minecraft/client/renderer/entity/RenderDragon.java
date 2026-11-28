package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.layers.LayerEnderDragonDeath;
import net.minecraft.client.renderer.entity.layers.LayerEnderDragonEyes;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderDragon extends RenderLiving {
   private static final ResourceLocation enderDragonCrystalBeamTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");
   private static final ResourceLocation enderDragonExplodingTextures = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");
   private static final ResourceLocation enderDragonTextures = new ResourceLocation("textures/entity/enderdragon/dragon.png");
   public ModelDragon modelDragon = (ModelDragon)this.mainModel;

   public ResourceLocation getEntityTexture(EntityDragon entityDragon) {
      return enderDragonTextures;
   }

   @Override
   public void doRender(EntityLiving entityLiving, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityDragon)entityLiving, d, d2, d3, f, f2);
   }

   public void renderModel2(EntityDragon entityDragon, float f, float f2, float f3, float f4, float f5, float f6, boolean flag, boolean flag2) {
      if (entityDragon.deathTicks > 0) {
         float f7 = (float)entityDragon.deathTicks / 200.0F;
         GlStateManager.depthFunc(515);
         GlStateManager.enableAlpha();
         GlStateManager.alphaFunc(516, f7);
         this.bindTexture(enderDragonExplodingTextures);
         this.mainModel.render(entityDragon, f, f2, f3, f4, f5, f6, true);
         GlStateManager.alphaFunc(516, 0.1F);
         GlStateManager.depthFunc(514);
      }

      this.bindEntityTexture(entityDragon);
      this.mainModel.render(entityDragon, f, f2, f3, f4, f5, f6, true);
      if (entityDragon.hurtTime > 0) {
         GlStateManager.depthFunc(514);
         GlStateManager.disableTexture2D();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.color(1.0F, 0.0F, 0.0F, 0.5F);
         this.mainModel.render(entityDragon, f, f2, f3, f4, f5, f6, true);
         GlStateManager.enableTexture2D();
         GlStateManager.disableBlend();
         GlStateManager.depthFunc(515);
      }
   }

   public void doRender(EntityDragon entityDragon, double d, double d2, double d3, float f, float f2) {
      BossStatus.setBossStatus(entityDragon, false);
      super.doRender((EntityLiving)entityDragon, d, d2, d3, f, f2);
      if (entityDragon.healingEnderCrystal != null) {
         this.drawRechargeRay(entityDragon, d, d2, d3, f2);
      }
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityDragon)entity, d, d2, d3, f, f2);
   }

   @Override
   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityDragon)entity, d, d2, d3, f, f2);
   }

   public RenderDragon(RenderManager renderManager2) {
      super(renderManager2, new ModelDragon(0.0F), 0.5F);
      this.addLayer(new LayerEnderDragonEyes(this));
      this.addLayer(new LayerEnderDragonDeath());
   }

   public void drawRechargeRay(EntityDragon entityDragon, double d, double d2, double d3, float f) {
      float f3 = MathHelper.sin(((float)entityDragon.healingEnderCrystal.innerRotation + f) * 0.2F) / 2.0F + 0.5F;
      f3 = (f3 * f3 + f3) * 0.2F;
      float f4 = (float)(entityDragon.healingEnderCrystal.posX - entityDragon.posX - (entityDragon.prevPosX - entityDragon.posX) * (double)(1.0F - f));
      float f5 = (float)((double)f3 + entityDragon.healingEnderCrystal.posY - 1.0 - entityDragon.posY - (entityDragon.prevPosY - entityDragon.posY) * (double)(1.0F - f));
      float f6 = (float)(entityDragon.healingEnderCrystal.posZ - entityDragon.posZ - (entityDragon.prevPosZ - entityDragon.posZ) * (double)(1.0F - f));
      float f7 = MathHelper.sqrt_float(f4 * f4 + f6 * f6);
      float f8 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6);
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d, (float)d2 + 2.0F, (float)d3);
      GlStateManager.rotate((float)(-Math.atan2((double)f6, (double)f4)) * 180.0F / (float) Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate((float)(-Math.atan2((double)f7, (double)f5)) * 180.0F / (float) Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableCull();
      this.bindTexture(enderDragonCrystalBeamTextures);
      GlStateManager.shadeModel(7425);
      float f9 = 0.0F - ((float)entityDragon.ticksExisted + f) * 0.01F;
      float f10 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6) / 32.0F - ((float)entityDragon.ticksExisted + f) * 0.01F;
      worldRenderer.begin(5, DefaultVertexFormats.POSITION_TEX_COLOR);

      for (int i = 0; i <= 8; i++) {
         float f11 = MathHelper.sin((float)(i % 8) * (float) Math.PI * 2.0F / 8.0F) * 0.75F;
         float f12 = MathHelper.cos((float)(i % 8) * (float) Math.PI * 2.0F / 8.0F) * 0.75F;
         float f13 = (float)(i % 8) * 1.0F / 8.0F;
         worldRenderer.pos((double)(f11 * 0.2F), (double)(f12 * 0.2F), 0.0).tex((double)f13, (double)f10).color(0, 0, 0, 255).endVertex();
         worldRenderer.pos((double)f11, (double)f12, (double)f8).tex((double)f13, (double)f9).color(255, 255, 255, 255).endVertex();
      }

      instance.draw();
      GlStateManager.enableCull();
      GlStateManager.shadeModel(7424);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.popMatrix();
   }

   @Override
   public void renderModel(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag, boolean flag2) {
      this.renderModel2((EntityDragon)entity, f, f2, f3, f4, f5, f6, flag, flag2);
   }

   @Override
   public void rotateCorpse(EntityLivingBase entity, float f, float f2, float f3) {
      this.rotateCorpse((EntityDragon)entity, f, f2, f3);
   }

   public void rotateCorpse(EntityDragon entityDragon, float f, float f2, float f3) {
      float f4 = (float)entityDragon.getMovementOffsets(7, f3)[0];
      float f5 = (float)(entityDragon.getMovementOffsets(5, f3)[1] - entityDragon.getMovementOffsets(10, f3)[1]);
      GlStateManager.rotate(-f4, 0.0F, 1.0F, 0.0F);
      if (entityDragon.getEntityId() != -1337) {
         GlStateManager.rotate(f5 * 10.0F, 1.0F, 0.0F, 0.0F);
      }

      GlStateManager.translate(0.0F, 0.0F, 1.0F);
      if (entityDragon.deathTime > 0) {
         float f6 = ((float)entityDragon.deathTime + f3 - 1.0F) / 20.0F * 1.6F;
         f6 = MathHelper.sqrt_float(f6);
         if (f6 > 1.0F) {
            f6 = 1.0F;
         }

         GlStateManager.rotate(f6 * this.getDeathMaxRotation(entityDragon), 0.0F, 0.0F, 1.0F);
      }
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityDragon)entity);
   }
}
