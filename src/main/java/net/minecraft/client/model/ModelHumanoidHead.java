package net.minecraft.client.model;

import net.minecraft.entity.Entity;

public class ModelHumanoidHead extends ModelSkeletonHead {
   public final ModelRenderer head = new ModelRenderer(this, 32, 0);

   @Override
   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      super.setRotationAngles(f, f2, f3, f4, f5, f6, entity);
      this.head.rotateAngleY = this.skeletonHead.rotateAngleY;
      this.head.rotateAngleX = this.skeletonHead.rotateAngleX;
   }

   public ModelHumanoidHead() {
      super(0, 0, 64, 64);
      this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F);
      this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
   }

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      super.render(entity, f, f2, f3, f4, f5, f6, flag);
      this.head.render(f6);
   }
}
