package net.augustus.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;

public class VillagerSkeletonRenderer extends AbstractSkeletonRenderer {
   public ModelRenderer getHead2(ModelVillager modelVillager) {
      return modelVillager.villagerHead;
   }

   public ModelRenderer getBody2(ModelVillager modelVillager) {
      return modelVillager.villagerBody;
   }

   public ModelRenderer getRightLeg2(ModelVillager modelVillager) {
      return modelVillager.rightVillagerLeg;
   }

   public ModelRenderer getLeftLeg2(ModelVillager modelVillager) {
      return modelVillager.leftVillagerLeg;
   }

   public ModelRenderer getRightArm2(ModelVillager modelVillager) {
      return modelVillager.villagerArms;
   }

   public ModelRenderer getLeftArm2(ModelVillager modelVillager) {
      return modelVillager.villagerArms;
   }

   @Override
   public int getRightArmCubeIndex() {
      return 0;
   }

   @Override
   public int getLeftArmCubeIndex() {
      return 1;
   }

   @Override
   public float getBodyBottomOffset() {
      return -0.38F;
   }

   @Override
   public ModelRenderer getLeftArm(ModelBase modelBase) {
      return this.getLeftArm2((ModelVillager)modelBase);
   }

   @Override
   public ModelRenderer getRightArm(ModelBase modelBase) {
      return this.getRightArm2((ModelVillager)modelBase);
   }

   @Override
   public ModelRenderer getLeftLeg(ModelBase modelBase) {
      return this.getLeftLeg2((ModelVillager)modelBase);
   }

   @Override
   public ModelRenderer getRightLeg(ModelBase modelBase) {
      return this.getRightLeg2((ModelVillager)modelBase);
   }

   @Override
   public ModelRenderer getBody(ModelBase modelBase) {
      return this.getBody2((ModelVillager)modelBase);
   }

   @Override
   public ModelRenderer getHead(ModelBase modelBase) {
      return this.getHead2((ModelVillager)modelBase);
   }
}
