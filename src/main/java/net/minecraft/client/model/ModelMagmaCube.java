package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMagmaCube;

public class ModelMagmaCube extends ModelBase {
   public ModelRenderer[] segments = new ModelRenderer[8];
   public ModelRenderer core;

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      this.setRotationAngles(f, f2, f3, f4, f5, f6, entity);
      this.core.render(f6);

      for (int i = 0; i < this.segments.length; i++) {
         this.segments[i].render(f6);
      }
   }

   @Override
   public void setLivingAnimations(EntityLivingBase entity, float f, float f2, float f3) {
      EntityMagmaCube entityMagmaCube = (EntityMagmaCube)entity;
      float f4 = entityMagmaCube.prevSquishFactor + (entityMagmaCube.squishFactor - entityMagmaCube.prevSquishFactor) * f3;
      if (f4 < 0.0F) {
         f4 = 0.0F;
      }

      for (int i = 0; i < this.segments.length; i++) {
         this.segments[i].rotationPointY = (float)(-(4 - i)) * f4 * 1.7F;
      }
   }

   public ModelMagmaCube() {
      for (int i = 0; i < this.segments.length; i++) {
         byte b = 0;
         int n = i;
         if (i == 2) {
            b = 24;
            n = 10;
         } else if (i == 3) {
            b = 24;
            n = 19;
         }

         this.segments[i] = new ModelRenderer(this, b, n);
         this.segments[i].addBox(-4.0F, (float)(16 + i), -4.0F, 8, 1, 8);
      }

      this.core = new ModelRenderer(this, 0, 16);
      this.core.addBox(-2.0F, 18.0F, -2.0F, 4, 4, 4);
   }
}
