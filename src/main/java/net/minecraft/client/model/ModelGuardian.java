package net.minecraft.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class ModelGuardian extends ModelBase {
   public ModelRenderer guardianBody;
   public ModelRenderer guardianEye;
   public ModelRenderer[] guardianSpines;
   public ModelRenderer[] guardianTail;

   @Override
   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      EntityGuardian entityGuardian = (EntityGuardian)entity;
      float f7 = f3 - (float)entityGuardian.ticksExisted;
      this.guardianBody.rotateAngleY = f4 / (180.0F / (float)Math.PI);
      this.guardianBody.rotateAngleX = f5 / (180.0F / (float)Math.PI);
      float[] floats = new float[]{1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F};
      float[] floats2 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F};
      float[] floats3 = new float[]{0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F};
      float[] floats4 = new float[]{0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F};
      float[] floats5 = new float[]{-8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F};
      float[] floats6 = new float[]{8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F};
      float f8 = (1.0F - entityGuardian.getSpikesAnimation(f7)) * 0.55F;

      for (int i = 0; i < 12; i++) {
         this.guardianSpines[i].rotateAngleX = (float) Math.PI * floats[i];
         this.guardianSpines[i].rotateAngleY = (float) Math.PI * floats2[i];
         this.guardianSpines[i].rotateAngleZ = (float) Math.PI * floats3[i];
         this.guardianSpines[i].rotationPointX = floats4[i] * (1.0F + MathHelper.cos(f3 * 1.5F + (float)i) * 0.01F - f8);
         this.guardianSpines[i].rotationPointY = 16.0F + floats5[i] * (1.0F + MathHelper.cos(f3 * 1.5F + (float)i) * 0.01F - f8);
         this.guardianSpines[i].rotationPointZ = floats6[i] * (1.0F + MathHelper.cos(f3 * 1.5F + (float)i) * 0.01F - f8);
      }

      this.guardianEye.rotationPointZ = -8.25F;
      Object renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
      if (entityGuardian.hasTargetedEntity()) {
         renderViewEntity = entityGuardian.getTargetedEntity();
      }

      if (renderViewEntity != null) {
         Vec3 positionEyes = ((Entity)renderViewEntity).getPositionEyes(0.0F);
         Vec3 positionEyes2 = entity.getPositionEyes(0.0F);
         if ((positionEyes.yCoord - positionEyes2.yCoord) > 0.0) {
            this.guardianEye.rotationPointY = 0.0F;
         } else {
            this.guardianEye.rotationPointY = 1.0F;
         }

         Vec3 look = entity.getLook(0.0F);
         look = new Vec3(look.xCoord, 0.0, look.zCoord);
         double d2 = look.dotProduct(new Vec3(positionEyes2.xCoord - positionEyes.xCoord, 0.0, positionEyes2.zCoord - positionEyes.zCoord).normalize().rotateYaw((float) (Math.PI / 2)));
         this.guardianEye.rotationPointX = MathHelper.sqrt_float((float)Math.abs(d2)) * 2.0F * (float)Math.signum(d2);
      }

      this.guardianEye.showModel = true;
      float tailAnimation = entityGuardian.getTailAnimation(f7);
      this.guardianTail[0].rotateAngleY = MathHelper.sin(tailAnimation) * (float) Math.PI * 0.05F;
      this.guardianTail[1].rotateAngleY = MathHelper.sin(tailAnimation) * (float) Math.PI * 0.1F;
      this.guardianTail[1].rotationPointX = -1.5F;
      this.guardianTail[1].rotationPointY = 0.5F;
      this.guardianTail[1].rotationPointZ = 14.0F;
      this.guardianTail[2].rotateAngleY = MathHelper.sin(tailAnimation) * (float) Math.PI * 0.15F;
      this.guardianTail[2].rotationPointX = 0.5F;
      this.guardianTail[2].rotationPointY = 0.5F;
      this.guardianTail[2].rotationPointZ = 6.0F;
   }

   public int getModelVersion() {
      return 54;
   }

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      this.setRotationAngles(f, f2, f3, f4, f5, f6, entity);
      this.guardianBody.render(f6);
   }

   public ModelGuardian() {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.guardianSpines = new ModelRenderer[12];
      this.guardianBody = new ModelRenderer(this);
      this.guardianBody.setTextureOffset(0, 0).addBox(-6.0F, 10.0F, -8.0F, 12, 12, 16);
      this.guardianBody.setTextureOffset(0, 28).addBox(-8.0F, 10.0F, -6.0F, 2, 12, 12);
      this.guardianBody.setTextureOffset(0, 28).addBox(6.0F, 10.0F, -6.0F, 2, 12, 12, true);
      this.guardianBody.setTextureOffset(16, 40).addBox(-6.0F, 8.0F, -6.0F, 12, 2, 12);
      this.guardianBody.setTextureOffset(16, 40).addBox(-6.0F, 22.0F, -6.0F, 12, 2, 12);

      for (int i = 0; i < this.guardianSpines.length; i++) {
         this.guardianSpines[i] = new ModelRenderer(this, 0, 0);
         this.guardianSpines[i].addBox(-1.0F, -4.5F, -1.0F, 2, 9, 2);
         this.guardianBody.addChild(this.guardianSpines[i]);
      }

      this.guardianEye = new ModelRenderer(this, 8, 0);
      this.guardianEye.addBox(-1.0F, 15.0F, 0.0F, 2, 2, 1);
      this.guardianBody.addChild(this.guardianEye);
      this.guardianTail = new ModelRenderer[3];
      this.guardianTail[0] = new ModelRenderer(this, 40, 0);
      this.guardianTail[0].addBox(-2.0F, 14.0F, 7.0F, 4, 4, 8);
      this.guardianTail[1] = new ModelRenderer(this, 0, 54);
      this.guardianTail[1].addBox(0.0F, 14.0F, 0.0F, 3, 3, 7);
      this.guardianTail[2] = new ModelRenderer(this);
      this.guardianTail[2].setTextureOffset(41, 32).addBox(0.0F, 14.0F, 0.0F, 2, 2, 6);
      this.guardianTail[2].setTextureOffset(25, 19).addBox(1.0F, 10.5F, 3.0F, 1, 9, 9);
      this.guardianBody.addChild(this.guardianTail[0]);
      this.guardianTail[0].addChild(this.guardianTail[1]);
      this.guardianTail[1].addChild(this.guardianTail[2]);
   }
}
