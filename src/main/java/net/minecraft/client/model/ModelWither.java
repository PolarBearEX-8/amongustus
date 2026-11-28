package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.MathHelper;

public class ModelWither extends ModelBase {
   public ModelRenderer[] upperBodyParts;
   public ModelRenderer[] heads;

   @Override
   public void setLivingAnimations(EntityLivingBase entity, float f, float f2, float f3) {
      EntityWither entityWither = (EntityWither)entity;

      for (int i = 1; i < 3; i++) {
         this.heads[i].rotateAngleY = (entityWither.getHeadYRotation(i - 1) - entity.renderYawOffset) / (180.0F / (float)Math.PI);
         this.heads[i].rotateAngleX = entityWither.getHeadXRotation(i - 1) / (180.0F / (float)Math.PI);
      }
   }

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      this.setRotationAngles(f, f2, f3, f4, f5, f6, entity);

      for (ModelRenderer modelRenderer : this.heads) {
         modelRenderer.render(f6);
      }

      for (ModelRenderer modelRenderer2 : this.upperBodyParts) {
         modelRenderer2.render(f6);
      }
   }

   @Override
   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      float f7 = MathHelper.cos(f3 * 0.1F);
      this.upperBodyParts[1].rotateAngleX = (0.065F + 0.05F * f7) * (float) Math.PI;
      this.upperBodyParts[2].setRotationPoint(-2.0F, 6.9F + MathHelper.cos(this.upperBodyParts[1].rotateAngleX) * 10.0F, -0.5F + MathHelper.sin(this.upperBodyParts[1].rotateAngleX) * 10.0F);
      this.upperBodyParts[2].rotateAngleX = (0.265F + 0.1F * f7) * (float) Math.PI;
      this.heads[0].rotateAngleY = f4 / (180.0F / (float)Math.PI);
      this.heads[0].rotateAngleX = f5 / (180.0F / (float)Math.PI);
   }

   public ModelWither(float f) {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.upperBodyParts = new ModelRenderer[3];
      this.upperBodyParts[0] = new ModelRenderer(this, 0, 16);
      this.upperBodyParts[0].addBox(-10.0F, 3.9F, -0.5F, 20, 3, 3, f);
      this.upperBodyParts[1] = new ModelRenderer(this).setTextureSize(this.textureWidth, this.textureHeight);
      this.upperBodyParts[1].setRotationPoint(-2.0F, 6.9F, -0.5F);
      this.upperBodyParts[1].setTextureOffset(0, 22).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, f);
      this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11, 2, 2, f);
      this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11, 2, 2, f);
      this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11, 2, 2, f);
      this.upperBodyParts[2] = new ModelRenderer(this, 12, 22);
      this.upperBodyParts[2].addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, f);
      this.heads = new ModelRenderer[3];
      this.heads[0] = new ModelRenderer(this, 0, 0);
      this.heads[0].addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, f);
      this.heads[1] = new ModelRenderer(this, 32, 0);
      this.heads[1].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, f);
      this.heads[1].rotationPointX = -8.0F;
      this.heads[1].rotationPointY = 4.0F;
      this.heads[2] = new ModelRenderer(this, 32, 0);
      this.heads[2].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, f);
      this.heads[2].rotationPointX = 10.0F;
      this.heads[2].rotationPointY = 4.0F;
   }
}
