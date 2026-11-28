package net.minecraft.client.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;

public class ModelArmorStand extends ModelArmorStandArmor {
   public ModelRenderer standRightSide;
   public ModelRenderer standLeftSide;
   public ModelRenderer standWaist;
   public ModelRenderer standBase;

   public ModelArmorStand(float f) {
      super(f, 64, 64);
      this.bipedHead = new ModelRenderer(this, 0, 0);
      this.bipedHead.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2, f);
      this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.bipedBody = new ModelRenderer(this, 0, 26);
      this.bipedBody.addBox(-6.0F, 0.0F, -1.5F, 12, 3, 3, f);
      this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.bipedRightArm = new ModelRenderer(this, 24, 0);
      this.bipedRightArm.addBox(-2.0F, -2.0F, -1.0F, 2, 12, 2, f);
      this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
      this.bipedLeftArm = new ModelRenderer(this, 32, 16);
      this.bipedLeftArm.mirror = true;
      this.bipedLeftArm.addBox(0.0F, -2.0F, -1.0F, 2, 12, 2, f);
      this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
      this.bipedRightLeg = new ModelRenderer(this, 8, 0);
      this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, f);
      this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
      this.bipedLeftLeg = new ModelRenderer(this, 40, 16);
      this.bipedLeftLeg.mirror = true;
      this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, f);
      this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
      this.standRightSide = new ModelRenderer(this, 16, 0);
      this.standRightSide.addBox(-3.0F, 3.0F, -1.0F, 2, 7, 2, f);
      this.standRightSide.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.standRightSide.showModel = true;
      this.standLeftSide = new ModelRenderer(this, 48, 16);
      this.standLeftSide.addBox(1.0F, 3.0F, -1.0F, 2, 7, 2, f);
      this.standLeftSide.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.standWaist = new ModelRenderer(this, 0, 48);
      this.standWaist.addBox(-4.0F, 10.0F, -1.0F, 8, 2, 2, f);
      this.standWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.standBase = new ModelRenderer(this, 0, 32);
      this.standBase.addBox(-6.0F, 11.0F, -6.0F, 12, 1, 12, f);
      this.standBase.setRotationPoint(0.0F, 12.0F, 0.0F);
   }

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      super.render(entity, f, f2, f3, f4, f5, f6, flag);
      GlStateManager.pushMatrix();
      if (this.isChild) {
         float f7 = 2.0F;
         GlStateManager.scale(1.0F / f7, 1.0F / f7, 1.0F / f7);
         GlStateManager.translate(0.0F, 24.0F * f6, 0.0F);
         this.standRightSide.render(f6);
         this.standLeftSide.render(f6);
         this.standWaist.render(f6);
         this.standBase.render(f6);
      } else {
         if (entity.isSneaking()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }

         this.standRightSide.render(f6);
         this.standLeftSide.render(f6);
         this.standWaist.render(f6);
         this.standBase.render(f6);
      }

      GlStateManager.popMatrix();
   }

   public ModelArmorStand() {
      this(0.0F);
   }

   @Override
   public void postRenderArm(float f) {
      boolean showModel = this.bipedRightArm.showModel;
      this.bipedRightArm.showModel = true;
      super.postRenderArm(f);
      this.bipedRightArm.showModel = showModel;
   }

   @Override
   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      super.setRotationAngles(f, f2, f3, f4, f5, f6, entity);
      if (entity instanceof EntityArmorStand) {
         EntityArmorStand entityArmorStand = (EntityArmorStand)entity;
         this.bipedLeftArm.showModel = entityArmorStand.getShowArms();
         this.bipedRightArm.showModel = entityArmorStand.getShowArms();
         this.standBase.showModel = !entityArmorStand.hasNoBasePlate();
         this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
         this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
         this.standRightSide.rotateAngleX = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getX();
         this.standRightSide.rotateAngleY = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getY();
         this.standRightSide.rotateAngleZ = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getZ();
         this.standLeftSide.rotateAngleX = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getX();
         this.standLeftSide.rotateAngleY = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getY();
         this.standLeftSide.rotateAngleZ = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getZ();
         this.standWaist.rotateAngleX = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getX();
         this.standWaist.rotateAngleY = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getY();
         this.standWaist.rotateAngleZ = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getZ();
         float f7 = (entityArmorStand.getLeftLegRotation().getX() + entityArmorStand.getRightLegRotation().getX()) / 2.0F;
         float f8 = (entityArmorStand.getLeftLegRotation().getY() + entityArmorStand.getRightLegRotation().getY()) / 2.0F;
         float f9 = (entityArmorStand.getLeftLegRotation().getZ() + entityArmorStand.getRightLegRotation().getZ()) / 2.0F;
         this.standBase.rotateAngleX = 0.0F;
         this.standBase.rotateAngleY = (float) (Math.PI / 180.0) * -entity.rotationYaw;
         this.standBase.rotateAngleZ = 0.0F;
      }
   }
}
