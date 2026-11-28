package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;

public class ModelArmorStandArmor extends ModelBiped {
   public ModelArmorStandArmor() {
      this(0.0F);
   }

   public ModelArmorStandArmor(float f, int n, int n2) {
      super(f, 0.0F, n, n2);
   }

   public ModelArmorStandArmor(float f) {
      this(f, 64, 32);
   }

   @Override
   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      if (entity instanceof EntityArmorStand) {
         EntityArmorStand entityArmorStand = (EntityArmorStand)entity;
         this.bipedHead.rotateAngleX = (float) (Math.PI / 180.0) * entityArmorStand.getHeadRotation().getX();
         this.bipedHead.rotateAngleY = (float) (Math.PI / 180.0) * entityArmorStand.getHeadRotation().getY();
         this.bipedHead.rotateAngleZ = (float) (Math.PI / 180.0) * entityArmorStand.getHeadRotation().getZ();
         this.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);
         this.bipedBody.rotateAngleX = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getX();
         this.bipedBody.rotateAngleY = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getY();
         this.bipedBody.rotateAngleZ = (float) (Math.PI / 180.0) * entityArmorStand.getBodyRotation().getZ();
         this.bipedLeftArm.rotateAngleX = (float) (Math.PI / 180.0) * entityArmorStand.getLeftArmRotation().getX();
         this.bipedLeftArm.rotateAngleY = (float) (Math.PI / 180.0) * entityArmorStand.getLeftArmRotation().getY();
         this.bipedLeftArm.rotateAngleZ = (float) (Math.PI / 180.0) * entityArmorStand.getLeftArmRotation().getZ();
         this.bipedRightArm.rotateAngleX = (float) (Math.PI / 180.0) * entityArmorStand.getRightArmRotation().getX();
         this.bipedRightArm.rotateAngleY = (float) (Math.PI / 180.0) * entityArmorStand.getRightArmRotation().getY();
         this.bipedRightArm.rotateAngleZ = (float) (Math.PI / 180.0) * entityArmorStand.getRightArmRotation().getZ();
         this.bipedLeftLeg.rotateAngleX = (float) (Math.PI / 180.0) * entityArmorStand.getLeftLegRotation().getX();
         this.bipedLeftLeg.rotateAngleY = (float) (Math.PI / 180.0) * entityArmorStand.getLeftLegRotation().getY();
         this.bipedLeftLeg.rotateAngleZ = (float) (Math.PI / 180.0) * entityArmorStand.getLeftLegRotation().getZ();
         this.bipedLeftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
         this.bipedRightLeg.rotateAngleX = (float) (Math.PI / 180.0) * entityArmorStand.getRightLegRotation().getX();
         this.bipedRightLeg.rotateAngleY = (float) (Math.PI / 180.0) * entityArmorStand.getRightLegRotation().getY();
         this.bipedRightLeg.rotateAngleZ = (float) (Math.PI / 180.0) * entityArmorStand.getRightLegRotation().getZ();
         this.bipedRightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
         copyModelAngles(this.bipedHead, this.bipedHeadwear);
      }
   }
}
