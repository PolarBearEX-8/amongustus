package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWitch extends ModelVillager {
   public boolean holdingItem;
   public ModelRenderer witchMole = new ModelRenderer(this).setTextureSize(64, 128);
   public ModelRenderer witchHat;

   public ModelWitch(float f) {
      super(f, 0.0F, 64, 128);
      this.witchMole.setRotationPoint(0.0F, -2.0F, 0.0F);
      this.witchMole.setTextureOffset(0, 0).addBox(0.0F, 3.0F, -6.75F, 1, 1, 1, -0.25F);
      this.villagerNose.addChild(this.witchMole);
      this.witchHat = new ModelRenderer(this).setTextureSize(64, 128);
      this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
      this.witchHat.setTextureOffset(0, 64).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
      this.villagerHead.addChild(this.witchHat);
      ModelRenderer modelRenderer = new ModelRenderer(this).setTextureSize(64, 128);
      modelRenderer.setRotationPoint(1.75F, -4.0F, 2.0F);
      modelRenderer.setTextureOffset(0, 76).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
      modelRenderer.rotateAngleX = -0.05235988F;
      modelRenderer.rotateAngleZ = 0.02617994F;
      this.witchHat.addChild(modelRenderer);
      ModelRenderer modelRenderer2 = new ModelRenderer(this).setTextureSize(64, 128);
      modelRenderer2.setRotationPoint(1.75F, -4.0F, 2.0F);
      modelRenderer2.setTextureOffset(0, 87).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
      modelRenderer2.rotateAngleX = -0.10471976F;
      modelRenderer2.rotateAngleZ = 0.05235988F;
      modelRenderer.addChild(modelRenderer2);
      ModelRenderer modelRenderer3 = new ModelRenderer(this).setTextureSize(64, 128);
      modelRenderer3.setRotationPoint(1.75F, -2.0F, 2.0F);
      modelRenderer3.setTextureOffset(0, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
      modelRenderer3.rotateAngleX = (float) (-Math.PI / 15);
      modelRenderer3.rotateAngleZ = 0.10471976F;
      modelRenderer2.addChild(modelRenderer3);
   }

   @Override
   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      super.setRotationAngles(f, f2, f3, f4, f5, f6, entity);
      this.villagerNose.offsetX = this.villagerNose.offsetY = this.villagerNose.offsetZ = 0.0F;
      float f7 = 0.01F * (float)(entity.getEntityId() % 10);
      this.villagerNose.rotateAngleX = MathHelper.sin((float)entity.ticksExisted * f7) * 4.5F * (float) Math.PI / 180.0F;
      this.villagerNose.rotateAngleY = 0.0F;
      this.villagerNose.rotateAngleZ = MathHelper.cos((float)entity.ticksExisted * f7) * 2.5F * (float) Math.PI / 180.0F;
      if (this.holdingItem) {
         this.villagerNose.rotateAngleX = -0.9F;
         this.villagerNose.offsetZ = -0.09375F;
         this.villagerNose.offsetY = 0.1875F;
      }
   }
}
