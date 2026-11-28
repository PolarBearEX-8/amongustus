package net.minecraft.client.model;

import net.minecraft.entity.Entity;

public class ModelLeashKnot extends ModelBase {
   public ModelRenderer knotRenderer;

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      this.setRotationAngles(f, f2, f3, f4, f5, f6, entity);
      this.knotRenderer.render(f6);
   }

   public ModelLeashKnot(int n, int n2, int n3, int n4) {
      this.textureWidth = n3;
      this.textureHeight = n4;
      this.knotRenderer = new ModelRenderer(this, n, n2);
      this.knotRenderer.addBox(-3.0F, -6.0F, -3.0F, 6, 8, 6, 0.0F);
      this.knotRenderer.setRotationPoint(0.0F, 0.0F, 0.0F);
   }

   public ModelLeashKnot() {
      this(0, 0, 32, 32);
   }

   @Override
   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      super.setRotationAngles(f, f2, f3, f4, f5, f6, entity);
      this.knotRenderer.rotateAngleY = f4 / (180.0F / (float)Math.PI);
      this.knotRenderer.rotateAngleX = f5 / (180.0F / (float)Math.PI);
   }
}
