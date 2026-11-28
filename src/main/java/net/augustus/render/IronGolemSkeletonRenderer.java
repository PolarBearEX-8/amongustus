package net.augustus.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.model.ModelRenderer;

public class IronGolemSkeletonRenderer extends AbstractSkeletonRenderer {
   public ModelRenderer getHead2(ModelIronGolem modelIronGolem) {
      return modelIronGolem.ironGolemHead;
   }

   public ModelRenderer getBody2(ModelIronGolem modelIronGolem) {
      return modelIronGolem.ironGolemBody;
   }

   public ModelRenderer getRightLeg2(ModelIronGolem modelIronGolem) {
      return modelIronGolem.ironGolemRightLeg;
   }

   public ModelRenderer getLeftLeg2(ModelIronGolem modelIronGolem) {
      return modelIronGolem.ironGolemLeftLeg;
   }

   public ModelRenderer getRightArm2(ModelIronGolem modelIronGolem) {
      return modelIronGolem.ironGolemRightArm;
   }

   public ModelRenderer getLeftArm2(ModelIronGolem modelIronGolem) {
      return modelIronGolem.ironGolemLeftArm;
   }

   @Override
   public ModelRenderer getLeftArm(ModelBase modelBase) {
      return this.getLeftArm2((ModelIronGolem)modelBase);
   }

   @Override
   public ModelRenderer getRightArm(ModelBase modelBase) {
      return this.getRightArm2((ModelIronGolem)modelBase);
   }

   @Override
   public ModelRenderer getLeftLeg(ModelBase modelBase) {
      return this.getLeftLeg2((ModelIronGolem)modelBase);
   }

   @Override
   public ModelRenderer getRightLeg(ModelBase modelBase) {
      return this.getRightLeg2((ModelIronGolem)modelBase);
   }

   @Override
   public ModelRenderer getBody(ModelBase modelBase) {
      return this.getBody2((ModelIronGolem)modelBase);
   }

   @Override
   public ModelRenderer getHead(ModelBase modelBase) {
      return this.getHead2((ModelIronGolem)modelBase);
   }
}
