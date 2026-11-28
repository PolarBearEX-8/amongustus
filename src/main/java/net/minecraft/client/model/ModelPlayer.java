package net.minecraft.client.model;

import net.augustus.ClientManagers;
import net.augustus.render.ObjModelLoader;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelPlayer extends ModelBiped {
   public ModelRenderer bipedLeftArmwear;
   public ModelRenderer bipedRightArmwear;
   public ModelRenderer bipedLeftLegwear;
   public ModelRenderer bipedRightLegwear;
   public ModelRenderer bipedBodyWear;
   private ModelRenderer bipedCape;
   private ModelRenderer bipedDeadmau5Head;
   private boolean smallArms;

   public ModelPlayer(float f, boolean flag) {
      super(f, 0.0F, 64, 64);
      this.smallArms = flag;
      this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
      this.bipedDeadmau5Head.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, f);
      this.bipedCape = new ModelRenderer(this, 0, 0);
      this.bipedCape.setTextureSize(64, 32);
      this.bipedCape.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, f);
      if (flag) {
         this.bipedLeftArm = new ModelRenderer(this, 32, 48);
         this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, f);
         this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);
         this.bipedRightArm = new ModelRenderer(this, 40, 16);
         this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, f);
         this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
         this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
         this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, f + 0.25F);
         this.bipedLeftArmwear.setRotationPoint(5.0F, 2.5F, 0.0F);
         this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
         this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, f + 0.25F);
         this.bipedRightArmwear.setRotationPoint(-5.0F, 2.5F, 10.0F);
      } else {
         this.bipedLeftArm = new ModelRenderer(this, 32, 48);
         this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, f);
         this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
         this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
         this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, f + 0.25F);
         this.bipedLeftArmwear.setRotationPoint(5.0F, 2.0F, 0.0F);
         this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
         this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, f + 0.25F);
         this.bipedRightArmwear.setRotationPoint(-5.0F, 2.0F, 10.0F);
      }

      this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
      this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
      this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
      this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
      this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f + 0.25F);
      this.bipedLeftLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);
      this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
      this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f + 0.25F);
      this.bipedRightLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);
      this.bipedBodyWear = new ModelRenderer(this, 16, 32);
      this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, f + 0.25F);
      this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);
   }

   public void renderDeadmau5Head(float f) {
      copyModelAngles(this.bipedHead, this.bipedDeadmau5Head);
      this.bipedDeadmau5Head.rotationPointX = 0.0F;
      this.bipedDeadmau5Head.rotationPointY = 0.0F;
      this.bipedDeadmau5Head.render(f);
   }

   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      super.setRotationAngles(f, f2, f3, f4, f5, f6, entity);
      copyModelAngles(this.bipedLeftLeg, this.bipedLeftLegwear);
      copyModelAngles(this.bipedRightLeg, this.bipedRightLegwear);
      copyModelAngles(this.bipedLeftArm, this.bipedLeftArmwear);
      copyModelAngles(this.bipedRightArm, this.bipedRightArmwear);
      copyModelAngles(this.bipedBody, this.bipedBodyWear);
   }

   public void renderLeftArm() {
      this.bipedLeftArm.render(0.0625F);
      this.bipedLeftArmwear.render(0.0625F);
   }

   public void setInvisible(boolean flag) {
      super.setInvisible(flag);
      this.bipedLeftArmwear.showModel = flag;
      this.bipedRightArmwear.showModel = flag;
      this.bipedLeftLegwear.showModel = flag;
      this.bipedRightLegwear.showModel = flag;
      this.bipedBodyWear.showModel = flag;
      this.bipedCape.showModel = flag;
      this.bipedDeadmau5Head.showModel = flag;
   }

   public void postRenderArm(float f) {
      if (this.smallArms) {
         this.bipedRightArm.rotationPointX++;
         this.bipedRightArm.postRender(f);
         this.bipedRightArm.rotationPointX--;
      } else {
         this.bipedRightArm.postRender(f);
      }
   }

   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      if (ClientManagers.moduleManager.modelRenderer.shouldRenderCustomModel(entity)) {
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, 1.5F, 0.0F);
         GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.disableBlend();
         GlStateManager.enableAlpha();
         ObjModelLoader.renderModel();
         GlStateManager.popMatrix();
      } else {
         super.render(entity, f, f2, f3, f4, f5, f6, flag);
         if (flag) {
            GlStateManager.pushMatrix();
            if (this.isChild) {
               float f7 = 2.0F;
               GlStateManager.scale(1.0F / f7, 1.0F / f7, 1.0F / f7);
               GlStateManager.translate(0.0F, 24.0F * f6, 0.0F);
               this.bipedLeftLegwear.render(f6);
               this.bipedRightLegwear.render(f6);
               this.bipedLeftArmwear.render(f6);
               this.bipedRightArmwear.render(f6);
               this.bipedBodyWear.render(f6);
            } else {
               if (entity.isSneaking()) {
                  GlStateManager.translate(0.0F, 0.2F, 0.0F);
               }

               this.bipedLeftLegwear.render(f6);
               this.bipedRightLegwear.render(f6);
               this.bipedLeftArmwear.render(f6);
               this.bipedRightArmwear.render(f6);
               this.bipedBodyWear.render(f6);
            }

            GlStateManager.popMatrix();
         }
      }
   }

   public void renderCape(float f) {
      this.bipedCape.render(f);
   }

   public void renderRightArm() {
      this.bipedRightArm.render(0.0625F);
      this.bipedRightArmwear.render(0.0625F);
   }
}
