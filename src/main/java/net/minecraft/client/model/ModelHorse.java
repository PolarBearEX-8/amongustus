package net.minecraft.client.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.MathHelper;

public class ModelHorse extends ModelBase {
   public ModelRenderer head;
   public ModelRenderer upperMouth;
   public ModelRenderer lowerMouth;
   public ModelRenderer horseLeftEar;
   public ModelRenderer horseRightEar;
   public ModelRenderer muleLeftEar;
   public ModelRenderer muleRightEar;
   public ModelRenderer neck;
   public ModelRenderer horseFaceRopes;
   public ModelRenderer mane;
   public ModelRenderer body;
   public ModelRenderer tailBase;
   public ModelRenderer tailMiddle;
   public ModelRenderer tailTip;
   public ModelRenderer backLeftLeg;
   public ModelRenderer backLeftShin;
   public ModelRenderer backLeftHoof;
   public ModelRenderer backRightLeg;
   public ModelRenderer backRightShin;
   public ModelRenderer backRightHoof;
   public ModelRenderer frontLeftLeg;
   public ModelRenderer frontLeftShin;
   public ModelRenderer frontLeftHoof;
   public ModelRenderer frontRightLeg;
   public ModelRenderer frontRightShin;
   public ModelRenderer frontRightHoof;
   public ModelRenderer muleLeftChest;
   public ModelRenderer muleRightChest;
   public ModelRenderer horseSaddleBottom;
   public ModelRenderer horseSaddleFront;
   public ModelRenderer horseSaddleBack;
   public ModelRenderer horseLeftSaddleRope;
   public ModelRenderer horseLeftSaddleMetal;
   public ModelRenderer horseRightSaddleRope;
   public ModelRenderer horseRightSaddleMetal;
   public ModelRenderer horseLeftFaceMetal;
   public ModelRenderer horseRightFaceMetal;
   public ModelRenderer horseLeftRein;
   public ModelRenderer horseRightRein;

   @Override
   public void setLivingAnimations(EntityLivingBase entity, float f, float f2, float f3) {
      super.setLivingAnimations(entity, f, f2, f3);
      float f4 = this.updateHorseRotation(entity.prevRenderYawOffset, entity.renderYawOffset, f3);
      float f5 = this.updateHorseRotation(entity.prevRotationYawHead, entity.rotationYawHead, f3);
      float f6 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f3;
      float f7 = f5 - f4;
      float f8 = f6 / (180.0F / (float)Math.PI);
      if (f7 > 20.0F) {
         f7 = 20.0F;
      }

      if (f7 < -20.0F) {
         f7 = -20.0F;
      }

      if (f2 > 0.2F) {
         f8 += MathHelper.cos(f * 0.4F) * 0.15F * f2;
      }

      EntityHorse entityHorse = (EntityHorse)entity;
      float grassEatingAmount = entityHorse.getGrassEatingAmount(f3);
      float rearingAmount = entityHorse.getRearingAmount(f3);
      float f9 = 1.0F - rearingAmount;
      float mouthOpennessAngle = entityHorse.getMouthOpennessAngle(f3);
      boolean flag = entityHorse.tailCounter != 0;
      boolean isHorseSaddled = entityHorse.isHorseSaddled();
      boolean flag2 = entityHorse.riddenByEntity != null;
      float f10 = (float)entity.ticksExisted + f3;
      float f11 = MathHelper.cos(f * 0.6662F + (float) Math.PI);
      float f12 = f11 * 0.8F * f2;
      this.head.rotationPointY = 4.0F;
      this.head.rotationPointZ = -10.0F;
      this.tailBase.rotationPointY = 3.0F;
      this.tailMiddle.rotationPointZ = 14.0F;
      this.muleRightChest.rotationPointY = 3.0F;
      this.muleRightChest.rotationPointZ = 10.0F;
      this.body.rotateAngleX = 0.0F;
      this.head.rotateAngleX = (float) (Math.PI / 6) + f8;
      this.head.rotateAngleY = f7 / (180.0F / (float)Math.PI);
      this.head.rotateAngleX = rearingAmount * ((float) (Math.PI / 12) + f8) + grassEatingAmount * 2.18166F + (1.0F - Math.max(rearingAmount, grassEatingAmount)) * this.head.rotateAngleX;
      this.head.rotateAngleY = rearingAmount * f7 / (180.0F / (float)Math.PI) + (1.0F - Math.max(rearingAmount, grassEatingAmount)) * this.head.rotateAngleY;
      this.head.rotationPointY = rearingAmount * -6.0F + grassEatingAmount * 11.0F + (1.0F - Math.max(rearingAmount, grassEatingAmount)) * this.head.rotationPointY;
      this.head.rotationPointZ = rearingAmount * -1.0F + grassEatingAmount * -10.0F + (1.0F - Math.max(rearingAmount, grassEatingAmount)) * this.head.rotationPointZ;
      this.tailBase.rotationPointY = rearingAmount * 9.0F + f9 * this.tailBase.rotationPointY;
      this.tailMiddle.rotationPointZ = rearingAmount * 18.0F + f9 * this.tailMiddle.rotationPointZ;
      this.muleRightChest.rotationPointY = rearingAmount * 5.5F + f9 * this.muleRightChest.rotationPointY;
      this.muleRightChest.rotationPointZ = rearingAmount * 15.0F + f9 * this.muleRightChest.rotationPointZ;
      this.body.rotateAngleX = rearingAmount * -45.0F / (180.0F / (float)Math.PI) + f9 * this.body.rotateAngleX;
      this.horseLeftEar.rotationPointY = this.head.rotationPointY;
      this.horseRightEar.rotationPointY = this.head.rotationPointY;
      this.muleLeftEar.rotationPointY = this.head.rotationPointY;
      this.muleRightEar.rotationPointY = this.head.rotationPointY;
      this.neck.rotationPointY = this.head.rotationPointY;
      this.upperMouth.rotationPointY = 0.02F;
      this.lowerMouth.rotationPointY = 0.0F;
      this.mane.rotationPointY = this.head.rotationPointY;
      this.horseLeftEar.rotationPointZ = this.head.rotationPointZ;
      this.horseRightEar.rotationPointZ = this.head.rotationPointZ;
      this.muleLeftEar.rotationPointZ = this.head.rotationPointZ;
      this.muleRightEar.rotationPointZ = this.head.rotationPointZ;
      this.neck.rotationPointZ = this.head.rotationPointZ;
      this.upperMouth.rotationPointZ = 0.02F - mouthOpennessAngle * 1.0F;
      this.lowerMouth.rotationPointZ = 0.0F + mouthOpennessAngle * 1.0F;
      this.mane.rotationPointZ = this.head.rotationPointZ;
      this.horseLeftEar.rotateAngleX = this.head.rotateAngleX;
      this.horseRightEar.rotateAngleX = this.head.rotateAngleX;
      this.muleLeftEar.rotateAngleX = this.head.rotateAngleX;
      this.muleRightEar.rotateAngleX = this.head.rotateAngleX;
      this.neck.rotateAngleX = this.head.rotateAngleX;
      this.upperMouth.rotateAngleX = 0.0F - 0.09424778F * mouthOpennessAngle;
      this.lowerMouth.rotateAngleX = 0.0F + (float) (Math.PI / 20) * mouthOpennessAngle;
      this.mane.rotateAngleX = this.head.rotateAngleX;
      this.horseLeftEar.rotateAngleY = this.head.rotateAngleY;
      this.horseRightEar.rotateAngleY = this.head.rotateAngleY;
      this.muleLeftEar.rotateAngleY = this.head.rotateAngleY;
      this.muleRightEar.rotateAngleY = this.head.rotateAngleY;
      this.neck.rotateAngleY = this.head.rotateAngleY;
      this.upperMouth.rotateAngleY = 0.0F;
      this.lowerMouth.rotateAngleY = 0.0F;
      this.mane.rotateAngleY = this.head.rotateAngleY;
      this.muleLeftChest.rotateAngleX = f12 / 5.0F;
      this.muleRightChest.rotateAngleX = -f12 / 5.0F;
      float f13 = (float) (Math.PI / 2);
      float f14 = (float) (Math.PI * 3.0 / 2.0);
      float f15 = (float) (-Math.PI / 3);
      float f16 = (float) (Math.PI / 12) * rearingAmount;
      float f17 = MathHelper.cos(f10 * 0.6F + (float) Math.PI);
      this.frontLeftLeg.rotationPointY = -2.0F * rearingAmount + 9.0F * f9;
      this.frontLeftLeg.rotationPointZ = -2.0F * rearingAmount + -8.0F * f9;
      this.frontRightLeg.rotationPointY = this.frontLeftLeg.rotationPointY;
      this.frontRightLeg.rotationPointZ = this.frontLeftLeg.rotationPointZ;
      this.backLeftShin.rotationPointY = this.backLeftLeg.rotationPointY + MathHelper.sin((float) (Math.PI / 2) + f16 + f9 * -f11 * 0.5F * f2) * 7.0F;
      this.backLeftShin.rotationPointZ = this.backLeftLeg.rotationPointZ
         + MathHelper.cos((float) (Math.PI * 3.0 / 2.0) + f16 + f9 * -f11 * 0.5F * f2) * 7.0F;
      this.backRightShin.rotationPointY = this.backRightLeg.rotationPointY + MathHelper.sin((float) (Math.PI / 2) + f16 + f9 * f11 * 0.5F * f2) * 7.0F;
      this.backRightShin.rotationPointZ = this.backRightLeg.rotationPointZ
         + MathHelper.cos((float) (Math.PI * 3.0 / 2.0) + f16 + f9 * f11 * 0.5F * f2) * 7.0F;
      float f18 = ((float) (-Math.PI / 3) + f17) * rearingAmount + f12 * f9;
      float f19 = ((float) (-Math.PI / 3) + -f17) * rearingAmount + -f12 * f9;
      this.frontLeftShin.rotationPointY = this.frontLeftLeg.rotationPointY + MathHelper.sin((float) (Math.PI / 2) + f18) * 7.0F;
      this.frontLeftShin.rotationPointZ = this.frontLeftLeg.rotationPointZ + MathHelper.cos((float) (Math.PI * 3.0 / 2.0) + f18) * 7.0F;
      this.frontRightShin.rotationPointY = this.frontRightLeg.rotationPointY + MathHelper.sin((float) (Math.PI / 2) + f19) * 7.0F;
      this.frontRightShin.rotationPointZ = this.frontRightLeg.rotationPointZ + MathHelper.cos((float) (Math.PI * 3.0 / 2.0) + f19) * 7.0F;
      this.backLeftLeg.rotateAngleX = f16 + -f11 * 0.5F * f2 * f9;
      this.backLeftShin.rotateAngleX = -0.08726646F * rearingAmount + (-f11 * 0.5F * f2 - Math.max(0.0F, f11 * 0.5F * f2)) * f9;
      this.backLeftHoof.rotateAngleX = this.backLeftShin.rotateAngleX;
      this.backRightLeg.rotateAngleX = f16 + f11 * 0.5F * f2 * f9;
      this.backRightShin.rotateAngleX = -0.08726646F * rearingAmount + (f11 * 0.5F * f2 - Math.max(0.0F, -f11 * 0.5F * f2)) * f9;
      this.backRightHoof.rotateAngleX = this.backRightShin.rotateAngleX;
      this.frontLeftLeg.rotateAngleX = f18;
      this.frontLeftShin.rotateAngleX = (this.frontLeftLeg.rotateAngleX + (float) Math.PI * Math.max(0.0F, 0.2F + f17 * 0.2F)) * rearingAmount
         + (f12 + Math.max(0.0F, f11 * 0.5F * f2)) * f9;
      this.frontLeftHoof.rotateAngleX = this.frontLeftShin.rotateAngleX;
      this.frontRightLeg.rotateAngleX = f19;
      this.frontRightShin.rotateAngleX = (this.frontRightLeg.rotateAngleX + (float) Math.PI * Math.max(0.0F, 0.2F - f17 * 0.2F)) * rearingAmount
         + (-f12 + Math.max(0.0F, -f11 * 0.5F * f2)) * f9;
      this.frontRightHoof.rotateAngleX = this.frontRightShin.rotateAngleX;
      this.backLeftHoof.rotationPointY = this.backLeftShin.rotationPointY;
      this.backLeftHoof.rotationPointZ = this.backLeftShin.rotationPointZ;
      this.backRightHoof.rotationPointY = this.backRightShin.rotationPointY;
      this.backRightHoof.rotationPointZ = this.backRightShin.rotationPointZ;
      this.frontLeftHoof.rotationPointY = this.frontLeftShin.rotationPointY;
      this.frontLeftHoof.rotationPointZ = this.frontLeftShin.rotationPointZ;
      this.frontRightHoof.rotationPointY = this.frontRightShin.rotationPointY;
      this.frontRightHoof.rotationPointZ = this.frontRightShin.rotationPointZ;
      if (isHorseSaddled) {
         this.horseSaddleBottom.rotationPointY = rearingAmount * 0.5F + f9 * 2.0F;
         this.horseSaddleBottom.rotationPointZ = rearingAmount * 11.0F + f9 * 2.0F;
         this.horseSaddleFront.rotationPointY = this.horseSaddleBottom.rotationPointY;
         this.horseSaddleBack.rotationPointY = this.horseSaddleBottom.rotationPointY;
         this.horseLeftSaddleRope.rotationPointY = this.horseSaddleBottom.rotationPointY;
         this.horseRightSaddleRope.rotationPointY = this.horseSaddleBottom.rotationPointY;
         this.horseLeftSaddleMetal.rotationPointY = this.horseSaddleBottom.rotationPointY;
         this.horseRightSaddleMetal.rotationPointY = this.horseSaddleBottom.rotationPointY;
         this.muleLeftChest.rotationPointY = this.muleRightChest.rotationPointY;
         this.horseSaddleFront.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
         this.horseSaddleBack.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
         this.horseLeftSaddleRope.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
         this.horseRightSaddleRope.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
         this.horseLeftSaddleMetal.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
         this.horseRightSaddleMetal.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
         this.muleLeftChest.rotationPointZ = this.muleRightChest.rotationPointZ;
         this.horseSaddleBottom.rotateAngleX = this.body.rotateAngleX;
         this.horseSaddleFront.rotateAngleX = this.body.rotateAngleX;
         this.horseSaddleBack.rotateAngleX = this.body.rotateAngleX;
         this.horseLeftRein.rotationPointY = this.head.rotationPointY;
         this.horseRightRein.rotationPointY = this.head.rotationPointY;
         this.horseFaceRopes.rotationPointY = this.head.rotationPointY;
         this.horseLeftFaceMetal.rotationPointY = this.head.rotationPointY;
         this.horseRightFaceMetal.rotationPointY = this.head.rotationPointY;
         this.horseLeftRein.rotationPointZ = this.head.rotationPointZ;
         this.horseRightRein.rotationPointZ = this.head.rotationPointZ;
         this.horseFaceRopes.rotationPointZ = this.head.rotationPointZ;
         this.horseLeftFaceMetal.rotationPointZ = this.head.rotationPointZ;
         this.horseRightFaceMetal.rotationPointZ = this.head.rotationPointZ;
         this.horseLeftRein.rotateAngleX = f8;
         this.horseRightRein.rotateAngleX = f8;
         this.horseFaceRopes.rotateAngleX = this.head.rotateAngleX;
         this.horseLeftFaceMetal.rotateAngleX = this.head.rotateAngleX;
         this.horseRightFaceMetal.rotateAngleX = this.head.rotateAngleX;
         this.horseFaceRopes.rotateAngleY = this.head.rotateAngleY;
         this.horseLeftFaceMetal.rotateAngleY = this.head.rotateAngleY;
         this.horseLeftRein.rotateAngleY = this.head.rotateAngleY;
         this.horseRightFaceMetal.rotateAngleY = this.head.rotateAngleY;
         this.horseRightRein.rotateAngleY = this.head.rotateAngleY;
         if (flag2) {
            this.horseLeftSaddleRope.rotateAngleX = (float) (-Math.PI / 3);
            this.horseLeftSaddleMetal.rotateAngleX = (float) (-Math.PI / 3);
            this.horseRightSaddleRope.rotateAngleX = (float) (-Math.PI / 3);
            this.horseRightSaddleMetal.rotateAngleX = (float) (-Math.PI / 3);
            this.horseLeftSaddleRope.rotateAngleZ = 0.0F;
            this.horseLeftSaddleMetal.rotateAngleZ = 0.0F;
            this.horseRightSaddleRope.rotateAngleZ = 0.0F;
            this.horseRightSaddleMetal.rotateAngleZ = 0.0F;
         } else {
            this.horseLeftSaddleRope.rotateAngleX = f12 / 3.0F;
            this.horseLeftSaddleMetal.rotateAngleX = f12 / 3.0F;
            this.horseRightSaddleRope.rotateAngleX = f12 / 3.0F;
            this.horseRightSaddleMetal.rotateAngleX = f12 / 3.0F;
            this.horseLeftSaddleRope.rotateAngleZ = f12 / 5.0F;
            this.horseLeftSaddleMetal.rotateAngleZ = f12 / 5.0F;
            this.horseRightSaddleRope.rotateAngleZ = -f12 / 5.0F;
            this.horseRightSaddleMetal.rotateAngleZ = -f12 / 5.0F;
         }
      }

      f13 = -1.3089F + f2 * 1.5F;
      if (f13 > 0.0F) {
         f13 = 0.0F;
      }

      if (flag) {
         this.tailBase.rotateAngleY = MathHelper.cos(f10 * 0.7F);
         f13 = 0.0F;
      } else {
         this.tailBase.rotateAngleY = 0.0F;
      }

      this.tailMiddle.rotateAngleY = this.tailBase.rotateAngleY;
      this.tailTip.rotateAngleY = this.tailBase.rotateAngleY;
      this.tailMiddle.rotationPointY = this.tailBase.rotationPointY;
      this.tailTip.rotationPointY = this.tailBase.rotationPointY;
      this.tailMiddle.rotationPointZ = this.tailBase.rotationPointZ;
      this.tailTip.rotationPointZ = this.tailBase.rotationPointZ;
      this.tailBase.rotateAngleX = f13;
      this.tailMiddle.rotateAngleX = f13;
      this.tailTip.rotateAngleX = -0.2618F + f13;
   }

   private void setBoxRotation(ModelRenderer modelRenderer, float f, float f2, float f3) {
      modelRenderer.rotateAngleX = f;
      modelRenderer.rotateAngleY = f2;
      modelRenderer.rotateAngleZ = f3;
   }

   public ModelHorse() {
      this.textureWidth = 128;
      this.textureHeight = 128;
      this.body = new ModelRenderer(this, 0, 34);
      this.body.addBox(-5.0F, -8.0F, -19.0F, 10, 10, 24);
      this.body.setRotationPoint(0.0F, 11.0F, 9.0F);
      this.tailBase = new ModelRenderer(this, 44, 0);
      this.tailBase.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3);
      this.tailBase.setRotationPoint(0.0F, 3.0F, 14.0F);
      this.setBoxRotation(this.tailBase, -1.134464F, 0.0F, 0.0F);
      this.tailMiddle = new ModelRenderer(this, 38, 7);
      this.tailMiddle.addBox(-1.5F, -2.0F, 3.0F, 3, 4, 7);
      this.tailMiddle.setRotationPoint(0.0F, 3.0F, 14.0F);
      this.setBoxRotation(this.tailMiddle, -1.134464F, 0.0F, 0.0F);
      this.tailTip = new ModelRenderer(this, 24, 3);
      this.tailTip.addBox(-1.5F, -4.5F, 9.0F, 3, 4, 7);
      this.tailTip.setRotationPoint(0.0F, 3.0F, 14.0F);
      this.setBoxRotation(this.tailTip, -1.40215F, 0.0F, 0.0F);
      this.backLeftLeg = new ModelRenderer(this, 78, 29);
      this.backLeftLeg.addBox(-2.5F, -2.0F, -2.5F, 4, 9, 5);
      this.backLeftLeg.setRotationPoint(4.0F, 9.0F, 11.0F);
      this.backLeftShin = new ModelRenderer(this, 78, 43);
      this.backLeftShin.addBox(-2.0F, 0.0F, -1.5F, 3, 5, 3);
      this.backLeftShin.setRotationPoint(4.0F, 16.0F, 11.0F);
      this.backLeftHoof = new ModelRenderer(this, 78, 51);
      this.backLeftHoof.addBox(-2.5F, 5.1F, -2.0F, 4, 3, 4);
      this.backLeftHoof.setRotationPoint(4.0F, 16.0F, 11.0F);
      this.backRightLeg = new ModelRenderer(this, 96, 29);
      this.backRightLeg.addBox(-1.5F, -2.0F, -2.5F, 4, 9, 5);
      this.backRightLeg.setRotationPoint(-4.0F, 9.0F, 11.0F);
      this.backRightShin = new ModelRenderer(this, 96, 43);
      this.backRightShin.addBox(-1.0F, 0.0F, -1.5F, 3, 5, 3);
      this.backRightShin.setRotationPoint(-4.0F, 16.0F, 11.0F);
      this.backRightHoof = new ModelRenderer(this, 96, 51);
      this.backRightHoof.addBox(-1.5F, 5.1F, -2.0F, 4, 3, 4);
      this.backRightHoof.setRotationPoint(-4.0F, 16.0F, 11.0F);
      this.frontLeftLeg = new ModelRenderer(this, 44, 29);
      this.frontLeftLeg.addBox(-1.9F, -1.0F, -2.1F, 3, 8, 4);
      this.frontLeftLeg.setRotationPoint(4.0F, 9.0F, -8.0F);
      this.frontLeftShin = new ModelRenderer(this, 44, 41);
      this.frontLeftShin.addBox(-1.9F, 0.0F, -1.6F, 3, 5, 3);
      this.frontLeftShin.setRotationPoint(4.0F, 16.0F, -8.0F);
      this.frontLeftHoof = new ModelRenderer(this, 44, 51);
      this.frontLeftHoof.addBox(-2.4F, 5.1F, -2.1F, 4, 3, 4);
      this.frontLeftHoof.setRotationPoint(4.0F, 16.0F, -8.0F);
      this.frontRightLeg = new ModelRenderer(this, 60, 29);
      this.frontRightLeg.addBox(-1.1F, -1.0F, -2.1F, 3, 8, 4);
      this.frontRightLeg.setRotationPoint(-4.0F, 9.0F, -8.0F);
      this.frontRightShin = new ModelRenderer(this, 60, 41);
      this.frontRightShin.addBox(-1.1F, 0.0F, -1.6F, 3, 5, 3);
      this.frontRightShin.setRotationPoint(-4.0F, 16.0F, -8.0F);
      this.frontRightHoof = new ModelRenderer(this, 60, 51);
      this.frontRightHoof.addBox(-1.6F, 5.1F, -2.1F, 4, 3, 4);
      this.frontRightHoof.setRotationPoint(-4.0F, 16.0F, -8.0F);
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-2.5F, -10.0F, -1.5F, 5, 5, 7);
      this.head.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.head, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.upperMouth = new ModelRenderer(this, 24, 18);
      this.upperMouth.addBox(-2.0F, -10.0F, -7.0F, 4, 3, 6);
      this.upperMouth.setRotationPoint(0.0F, 3.95F, -10.0F);
      this.setBoxRotation(this.upperMouth, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.lowerMouth = new ModelRenderer(this, 24, 27);
      this.lowerMouth.addBox(-2.0F, -7.0F, -6.5F, 4, 2, 5);
      this.lowerMouth.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.lowerMouth, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.head.addChild(this.upperMouth);
      this.head.addChild(this.lowerMouth);
      this.horseLeftEar = new ModelRenderer(this, 0, 0);
      this.horseLeftEar.addBox(0.45F, -12.0F, 4.0F, 2, 3, 1);
      this.horseLeftEar.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.horseLeftEar, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.horseRightEar = new ModelRenderer(this, 0, 0);
      this.horseRightEar.addBox(-2.45F, -12.0F, 4.0F, 2, 3, 1);
      this.horseRightEar.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.horseRightEar, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.muleLeftEar = new ModelRenderer(this, 0, 12);
      this.muleLeftEar.addBox(-2.0F, -16.0F, 4.0F, 2, 7, 1);
      this.muleLeftEar.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.muleLeftEar, (float) (Math.PI / 6), 0.0F, (float) (Math.PI / 12));
      this.muleRightEar = new ModelRenderer(this, 0, 12);
      this.muleRightEar.addBox(0.0F, -16.0F, 4.0F, 2, 7, 1);
      this.muleRightEar.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.muleRightEar, (float) (Math.PI / 6), 0.0F, (float) (-Math.PI / 12));
      this.neck = new ModelRenderer(this, 0, 12);
      this.neck.addBox(-2.05F, -9.8F, -2.0F, 4, 14, 8);
      this.neck.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.neck, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.muleLeftChest = new ModelRenderer(this, 0, 34);
      this.muleLeftChest.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
      this.muleLeftChest.setRotationPoint(-7.5F, 3.0F, 10.0F);
      this.setBoxRotation(this.muleLeftChest, 0.0F, (float) (Math.PI / 2), 0.0F);
      this.muleRightChest = new ModelRenderer(this, 0, 47);
      this.muleRightChest.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
      this.muleRightChest.setRotationPoint(4.5F, 3.0F, 10.0F);
      this.setBoxRotation(this.muleRightChest, 0.0F, (float) (Math.PI / 2), 0.0F);
      this.horseSaddleBottom = new ModelRenderer(this, 80, 0);
      this.horseSaddleBottom.addBox(-5.0F, 0.0F, -3.0F, 10, 1, 8);
      this.horseSaddleBottom.setRotationPoint(0.0F, 2.0F, 2.0F);
      this.horseSaddleFront = new ModelRenderer(this, 106, 9);
      this.horseSaddleFront.addBox(-1.5F, -1.0F, -3.0F, 3, 1, 2);
      this.horseSaddleFront.setRotationPoint(0.0F, 2.0F, 2.0F);
      this.horseSaddleBack = new ModelRenderer(this, 80, 9);
      this.horseSaddleBack.addBox(-4.0F, -1.0F, 3.0F, 8, 1, 2);
      this.horseSaddleBack.setRotationPoint(0.0F, 2.0F, 2.0F);
      this.horseLeftSaddleMetal = new ModelRenderer(this, 74, 0);
      this.horseLeftSaddleMetal.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
      this.horseLeftSaddleMetal.setRotationPoint(5.0F, 3.0F, 2.0F);
      this.horseLeftSaddleRope = new ModelRenderer(this, 70, 0);
      this.horseLeftSaddleRope.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
      this.horseLeftSaddleRope.setRotationPoint(5.0F, 3.0F, 2.0F);
      this.horseRightSaddleMetal = new ModelRenderer(this, 74, 4);
      this.horseRightSaddleMetal.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
      this.horseRightSaddleMetal.setRotationPoint(-5.0F, 3.0F, 2.0F);
      this.horseRightSaddleRope = new ModelRenderer(this, 80, 0);
      this.horseRightSaddleRope.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
      this.horseRightSaddleRope.setRotationPoint(-5.0F, 3.0F, 2.0F);
      this.horseLeftFaceMetal = new ModelRenderer(this, 74, 13);
      this.horseLeftFaceMetal.addBox(1.5F, -8.0F, -4.0F, 1, 2, 2);
      this.horseLeftFaceMetal.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.horseLeftFaceMetal, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.horseRightFaceMetal = new ModelRenderer(this, 74, 13);
      this.horseRightFaceMetal.addBox(-2.5F, -8.0F, -4.0F, 1, 2, 2);
      this.horseRightFaceMetal.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.horseRightFaceMetal, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.horseLeftRein = new ModelRenderer(this, 44, 10);
      this.horseLeftRein.addBox(2.6F, -6.0F, -6.0F, 0, 3, 16);
      this.horseLeftRein.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.horseRightRein = new ModelRenderer(this, 44, 5);
      this.horseRightRein.addBox(-2.6F, -6.0F, -6.0F, 0, 3, 16);
      this.horseRightRein.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.mane = new ModelRenderer(this, 58, 0);
      this.mane.addBox(-1.0F, -11.5F, 5.0F, 2, 16, 4);
      this.mane.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.mane, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.horseFaceRopes = new ModelRenderer(this, 80, 12);
      this.horseFaceRopes.addBox(-2.5F, -10.1F, -7.0F, 5, 5, 12, 0.2F);
      this.horseFaceRopes.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.setBoxRotation(this.horseFaceRopes, (float) (Math.PI / 6), 0.0F, 0.0F);
   }

   private float updateHorseRotation(float f, float f2, float f3) {
      float f4 = f2 - f;

      while (f4 < -180.0F) {
         f4 += 360.0F;
      }

      while (f4 >= 180.0F) {
         f4 -= 360.0F;
      }

      return f + f3 * f4;
   }

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      EntityHorse entityHorse = (EntityHorse)entity;
      int horseType = entityHorse.getHorseType();
      float grassEatingAmount = entityHorse.getGrassEatingAmount(0.0F);
      boolean isAdultHorse = entityHorse.isAdultHorse();
      boolean flag2 = isAdultHorse && entityHorse.isHorseSaddled();
      boolean flag3 = isAdultHorse && entityHorse.isChested();
      boolean flag4 = horseType == 1 || horseType == 2;
      float horseSize = entityHorse.getHorseSize();
      boolean flag5 = entityHorse.riddenByEntity != null;
      if (flag2) {
         this.horseFaceRopes.render(f6);
         this.horseSaddleBottom.render(f6);
         this.horseSaddleFront.render(f6);
         this.horseSaddleBack.render(f6);
         this.horseLeftSaddleRope.render(f6);
         this.horseLeftSaddleMetal.render(f6);
         this.horseRightSaddleRope.render(f6);
         this.horseRightSaddleMetal.render(f6);
         this.horseLeftFaceMetal.render(f6);
         this.horseRightFaceMetal.render(f6);
         if (flag5) {
            this.horseLeftRein.render(f6);
            this.horseRightRein.render(f6);
         }
      }

      if (!isAdultHorse) {
         GlStateManager.pushMatrix();
         GlStateManager.scale(horseSize, 0.5F + horseSize * 0.5F, horseSize);
         GlStateManager.translate(0.0F, 0.95F * (1.0F - horseSize), 0.0F);
      }

      this.backLeftLeg.render(f6);
      this.backLeftShin.render(f6);
      this.backLeftHoof.render(f6);
      this.backRightLeg.render(f6);
      this.backRightShin.render(f6);
      this.backRightHoof.render(f6);
      this.frontLeftLeg.render(f6);
      this.frontLeftShin.render(f6);
      this.frontLeftHoof.render(f6);
      this.frontRightLeg.render(f6);
      this.frontRightShin.render(f6);
      this.frontRightHoof.render(f6);
      if (!isAdultHorse) {
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.scale(horseSize, horseSize, horseSize);
         GlStateManager.translate(0.0F, 1.35F * (1.0F - horseSize), 0.0F);
      }

      this.body.render(f6);
      this.tailBase.render(f6);
      this.tailMiddle.render(f6);
      this.tailTip.render(f6);
      this.neck.render(f6);
      this.mane.render(f6);
      if (!isAdultHorse) {
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         float f7 = 0.5F + horseSize * horseSize * 0.5F;
         GlStateManager.scale(f7, f7, f7);
         if (grassEatingAmount <= 0.0F) {
            GlStateManager.translate(0.0F, 1.35F * (1.0F - horseSize), 0.0F);
         } else {
            GlStateManager.translate(0.0F, 0.9F * (1.0F - horseSize) * grassEatingAmount + 1.35F * (1.0F - horseSize) * (1.0F - grassEatingAmount), 0.15F * (1.0F - horseSize) * grassEatingAmount);
         }
      }

      if (flag4) {
         this.muleLeftEar.render(f6);
         this.muleRightEar.render(f6);
      } else {
         this.horseLeftEar.render(f6);
         this.horseRightEar.render(f6);
      }

      this.head.render(f6);
      if (!isAdultHorse) {
         GlStateManager.popMatrix();
      }

      if (flag3) {
         this.muleLeftChest.render(f6);
         this.muleRightChest.render(f6);
      }
   }
}
