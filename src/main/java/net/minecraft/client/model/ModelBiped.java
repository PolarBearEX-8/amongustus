package net.minecraft.client.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBiped extends ModelBase {
   public ModelRenderer bipedHead;
   public ModelRenderer bipedHeadwear;
   public ModelRenderer bipedBody;
   public ModelRenderer bipedRightArm;
   public ModelRenderer bipedLeftArm;
   public ModelRenderer bipedRightLeg;
   public ModelRenderer bipedLeftLeg;
   public int heldItemLeft;
   public int heldItemRight;
   public boolean isSneak;
   public boolean aimedBow;

   public ModelBiped(float f, float f2, int n, int n2) {
      this.textureWidth = n;
      this.textureHeight = n2;
      this.bipedHead = new ModelRenderer(this, 0, 0);
      this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f);
      this.bipedHead.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
      this.bipedHeadwear = new ModelRenderer(this, 32, 0);
      this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f + 0.5F);
      this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
      this.bipedBody = new ModelRenderer(this, 16, 16);
      this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, f);
      this.bipedBody.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
      this.bipedRightArm = new ModelRenderer(this, 40, 16);
      this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, f);
      this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + f2, 0.0F);
      this.bipedLeftArm = new ModelRenderer(this, 40, 16);
      this.bipedLeftArm.mirror = true;
      this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, f);
      this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + f2, 0.0F);
      this.bipedRightLeg = new ModelRenderer(this, 0, 16);
      this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
      this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + f2, 0.0F);
      this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
      this.bipedLeftLeg.mirror = true;
      this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
      this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + f2, 0.0F);
   }

   public void setInvisible(boolean flag) {
      this.bipedHead.showModel = flag;
      this.bipedHeadwear.showModel = flag;
      this.bipedBody.showModel = flag;
      this.bipedRightArm.showModel = flag;
      this.bipedLeftArm.showModel = flag;
      this.bipedRightLeg.showModel = flag;
      this.bipedLeftLeg.showModel = flag;
   }

   @Override
   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      this.bipedHead.rotateAngleY = f4 / (180.0F / (float)Math.PI);
      this.bipedHead.rotateAngleX = f5 / (180.0F / (float)Math.PI);
      this.bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f2 * 0.5F;
      this.bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f2 * 0.5F;
      this.bipedRightArm.rotateAngleZ = 0.0F;
      this.bipedLeftArm.rotateAngleZ = 0.0F;
      this.bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f2;
      this.bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f2;
      this.bipedRightLeg.rotateAngleY = 0.0F;
      this.bipedLeftLeg.rotateAngleY = 0.0F;
      if (this.isRiding) {
         this.bipedRightArm.rotateAngleX += (float) (-Math.PI / 5);
         this.bipedLeftArm.rotateAngleX += (float) (-Math.PI / 5);
         this.bipedRightLeg.rotateAngleX = (float) (-Math.PI * 2.0 / 5.0);
         this.bipedLeftLeg.rotateAngleX = (float) (-Math.PI * 2.0 / 5.0);
         this.bipedRightLeg.rotateAngleY = (float) (Math.PI / 10);
         this.bipedLeftLeg.rotateAngleY = (float) (-Math.PI / 10);
      }

      if (this.heldItemLeft != 0) {
         this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - (float) (Math.PI / 10) * (float)this.heldItemLeft;
      }

      this.bipedRightArm.rotateAngleY = 0.0F;
      this.bipedRightArm.rotateAngleZ = 0.0F;
      switch (this.heldItemRight) {
         case 0:
         case 2:
         default:
            break;
         case 1:
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - (float) (Math.PI / 10) * (float)this.heldItemRight;
            break;
         case 3:
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - (float) (Math.PI / 10) * (float)this.heldItemRight;
            this.bipedRightArm.rotateAngleY = (float) (-Math.PI / 6);
      }

      this.bipedLeftArm.rotateAngleY = 0.0F;
      if (this.swingProgress > -9990.0F) {
         float swingProgress2 = this.swingProgress;
         this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(swingProgress2) * (float) Math.PI * 2.0F) * 0.2F;
         this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
         this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
         this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
         this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
         this.bipedRightArm.rotateAngleY = this.bipedRightArm.rotateAngleY + this.bipedBody.rotateAngleY;
         this.bipedLeftArm.rotateAngleY = this.bipedLeftArm.rotateAngleY + this.bipedBody.rotateAngleY;
         this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX + this.bipedBody.rotateAngleY;
         swingProgress2 = 1.0F - this.swingProgress;
         swingProgress2 *= swingProgress2;
         swingProgress2 *= swingProgress2;
         swingProgress2 = 1.0F - swingProgress2;
         float f7 = MathHelper.sin(swingProgress2 * (float) Math.PI);
         float f8 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
         this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f7 * 1.2 + (double)f8));
         this.bipedRightArm.rotateAngleY = this.bipedRightArm.rotateAngleY + this.bipedBody.rotateAngleY * 2.0F;
         this.bipedRightArm.rotateAngleZ = this.bipedRightArm.rotateAngleZ + MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
      }

      if (this.isSneak) {
         this.bipedBody.rotateAngleX = 0.5F;
         this.bipedRightArm.rotateAngleX += 0.4F;
         this.bipedLeftArm.rotateAngleX += 0.4F;
         this.bipedRightLeg.rotationPointZ = 4.0F;
         this.bipedLeftLeg.rotationPointZ = 4.0F;
         this.bipedRightLeg.rotationPointY = 9.0F;
         this.bipedLeftLeg.rotationPointY = 9.0F;
         this.bipedHead.rotationPointY = 1.0F;
      } else {
         this.bipedBody.rotateAngleX = 0.0F;
         this.bipedRightLeg.rotationPointZ = 0.1F;
         this.bipedLeftLeg.rotationPointZ = 0.1F;
         this.bipedRightLeg.rotationPointY = 12.0F;
         this.bipedLeftLeg.rotationPointY = 12.0F;
         this.bipedHead.rotationPointY = 0.0F;
      }

      this.bipedRightArm.rotateAngleZ = this.bipedRightArm.rotateAngleZ + MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
      this.bipedLeftArm.rotateAngleZ = this.bipedLeftArm.rotateAngleZ - (MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F);
      this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX + MathHelper.sin(f3 * 0.067F) * 0.05F;
      this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX - MathHelper.sin(f3 * 0.067F) * 0.05F;
      if (this.aimedBow) {
         float f9 = 0.0F;
         float f10 = 0.0F;
         this.bipedRightArm.rotateAngleZ = 0.0F;
         this.bipedLeftArm.rotateAngleZ = 0.0F;
         this.bipedRightArm.rotateAngleY = -(0.1F - f9 * 0.6F) + this.bipedHead.rotateAngleY;
         this.bipedLeftArm.rotateAngleY = 0.1F - f9 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
         this.bipedRightArm.rotateAngleX = (float) (-Math.PI / 2) + this.bipedHead.rotateAngleX;
         this.bipedLeftArm.rotateAngleX = (float) (-Math.PI / 2) + this.bipedHead.rotateAngleX;
         this.bipedRightArm.rotateAngleX -= f9 * 1.2F - f10 * 0.4F;
         this.bipedLeftArm.rotateAngleX -= f9 * 1.2F - f10 * 0.4F;
         this.bipedRightArm.rotateAngleZ = this.bipedRightArm.rotateAngleZ + MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
         this.bipedLeftArm.rotateAngleZ = this.bipedLeftArm.rotateAngleZ - (MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F);
         this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX + MathHelper.sin(f3 * 0.067F) * 0.05F;
         this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX - MathHelper.sin(f3 * 0.067F) * 0.05F;
      }

      copyModelAngles(this.bipedHead, this.bipedHeadwear);
   }

   public ModelBiped() {
      this(0.0F);
   }

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      this.setRotationAngles(f, f2, f3, f4, f5, f6, entity);
      GlStateManager.pushMatrix();
      if (this.isChild) {
         float f7 = 2.0F;
         GlStateManager.scale(1.5F / f7, 1.5F / f7, 1.5F / f7);
         GlStateManager.translate(0.0F, 16.0F * f6, 0.0F);
         this.bipedHead.render(f6);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.scale(1.0F / f7, 1.0F / f7, 1.0F / f7);
         GlStateManager.translate(0.0F, 24.0F * f6, 0.0F);
         this.bipedBody.render(f6);
         this.bipedRightArm.render(f6);
         this.bipedLeftArm.render(f6);
         this.bipedRightLeg.render(f6);
         this.bipedLeftLeg.render(f6);
         if (flag) {
            this.bipedHeadwear.render(f6);
         }
      } else {
         if (entity.isSneaking()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }

         this.bipedHead.render(f6);
         this.bipedBody.render(f6);
         this.bipedRightArm.render(f6);
         this.bipedLeftArm.render(f6);
         this.bipedRightLeg.render(f6);
         this.bipedLeftLeg.render(f6);
         if (flag) {
            this.bipedHeadwear.render(f6);
         }
      }

      GlStateManager.popMatrix();
   }

   public void postRenderArm(float f) {
      this.bipedRightArm.postRender(f);
   }

   @Override
   public void setModelAttributes(ModelBase modelBase) {
      super.setModelAttributes(modelBase);
      if (modelBase instanceof ModelBiped) {
         ModelBiped modelBiped = (ModelBiped)modelBase;
         this.heldItemLeft = modelBiped.heldItemLeft;
         this.heldItemRight = modelBiped.heldItemRight;
         this.isSneak = modelBiped.isSneak;
         this.aimedBow = modelBiped.aimedBow;
      }
   }

   public ModelBiped(float f) {
      this(f, 0.0F, 64, 32);
   }
}
