package net.minecraft.client.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;

public class ModelDragon extends ModelBase {
   public ModelRenderer head;
   public ModelRenderer spine;
   public ModelRenderer jaw;
   public ModelRenderer body;
   public ModelRenderer rearLeg;
   public ModelRenderer frontLeg;
   public ModelRenderer rearLegTip;
   public ModelRenderer frontLegTip;
   public ModelRenderer rearFoot;
   public ModelRenderer frontFoot;
   public ModelRenderer wing;
   public ModelRenderer wingTip;
   private float partialTicks;

   public ModelDragon(float f) {
      this.textureWidth = 256;
      this.textureHeight = 256;
      this.setTextureOffset("body.body", 0, 0);
      this.setTextureOffset("wing.skin", -56, 88);
      this.setTextureOffset("wingtip.skin", -56, 144);
      this.setTextureOffset("rearleg.main", 0, 0);
      this.setTextureOffset("rearfoot.main", 112, 0);
      this.setTextureOffset("rearlegtip.main", 196, 0);
      this.setTextureOffset("head.upperhead", 112, 30);
      this.setTextureOffset("wing.bone", 112, 88);
      this.setTextureOffset("head.upperlip", 176, 44);
      this.setTextureOffset("jaw.jaw", 176, 65);
      this.setTextureOffset("frontleg.main", 112, 104);
      this.setTextureOffset("wingtip.bone", 112, 136);
      this.setTextureOffset("frontfoot.main", 144, 104);
      this.setTextureOffset("neck.box", 192, 104);
      this.setTextureOffset("frontlegtip.main", 226, 138);
      this.setTextureOffset("body.scale", 220, 53);
      this.setTextureOffset("head.scale", 0, 0);
      this.setTextureOffset("neck.scale", 48, 0);
      this.setTextureOffset("head.nostril", 112, 0);
      float f2 = -16.0F;
      this.head = new ModelRenderer(this, "head");
      this.head.addBox("upperlip", -6.0F, -1.0F, -8.0F + f2, 12, 5, 16);
      this.head.addBox("upperhead", -8.0F, -8.0F, 6.0F + f2, 16, 16, 16);
      this.head.mirror = true;
      this.head.addBox("scale", -5.0F, -12.0F, 12.0F + f2, 2, 4, 6);
      this.head.addBox("nostril", -5.0F, -3.0F, -6.0F + f2, 2, 2, 4);
      this.head.mirror = false;
      this.head.addBox("scale", 3.0F, -12.0F, 12.0F + f2, 2, 4, 6);
      this.head.addBox("nostril", 3.0F, -3.0F, -6.0F + f2, 2, 2, 4);
      this.jaw = new ModelRenderer(this, "jaw");
      this.jaw.setRotationPoint(0.0F, 4.0F, 8.0F + f2);
      this.jaw.addBox("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
      this.head.addChild(this.jaw);
      this.spine = new ModelRenderer(this, "neck");
      this.spine.addBox("box", -5.0F, -5.0F, -5.0F, 10, 10, 10);
      this.spine.addBox("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6);
      this.body = new ModelRenderer(this, "body");
      this.body.setRotationPoint(0.0F, 4.0F, 8.0F);
      this.body.addBox("body", -12.0F, 0.0F, -16.0F, 24, 24, 64);
      this.body.addBox("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12);
      this.body.addBox("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12);
      this.body.addBox("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12);
      this.wing = new ModelRenderer(this, "wing");
      this.wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
      this.wing.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
      this.wing.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.wingTip = new ModelRenderer(this, "wingtip");
      this.wingTip.setRotationPoint(-56.0F, 0.0F, 0.0F);
      this.wingTip.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
      this.wingTip.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.wing.addChild(this.wingTip);
      this.frontLeg = new ModelRenderer(this, "frontleg");
      this.frontLeg.setRotationPoint(-12.0F, 20.0F, 2.0F);
      this.frontLeg.addBox("main", -4.0F, -4.0F, -4.0F, 8, 24, 8);
      this.frontLegTip = new ModelRenderer(this, "frontlegtip");
      this.frontLegTip.setRotationPoint(0.0F, 20.0F, -1.0F);
      this.frontLegTip.addBox("main", -3.0F, -1.0F, -3.0F, 6, 24, 6);
      this.frontLeg.addChild(this.frontLegTip);
      this.frontFoot = new ModelRenderer(this, "frontfoot");
      this.frontFoot.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.frontFoot.addBox("main", -4.0F, 0.0F, -12.0F, 8, 4, 16);
      this.frontLegTip.addChild(this.frontFoot);
      this.rearLeg = new ModelRenderer(this, "rearleg");
      this.rearLeg.setRotationPoint(-16.0F, 16.0F, 42.0F);
      this.rearLeg.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16);
      this.rearLegTip = new ModelRenderer(this, "rearlegtip");
      this.rearLegTip.setRotationPoint(0.0F, 32.0F, -4.0F);
      this.rearLegTip.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12);
      this.rearLeg.addChild(this.rearLegTip);
      this.rearFoot = new ModelRenderer(this, "rearfoot");
      this.rearFoot.setRotationPoint(0.0F, 31.0F, 4.0F);
      this.rearFoot.addBox("main", -9.0F, 0.0F, -20.0F, 18, 6, 24);
      this.rearLegTip.addChild(this.rearFoot);
   }

   private float updateRotations(double d) {
      while (d >= 180.0) {
         d -= 360.0;
      }

      while (d < -180.0) {
         d += 360.0;
      }

      return (float)d;
   }

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      GlStateManager.pushMatrix();
      EntityDragon entityDragon = (EntityDragon)entity;
      float f7 = entityDragon.prevAnimTime + (entityDragon.animTime - entityDragon.prevAnimTime) * this.partialTicks;
      this.jaw.rotateAngleX = (float)(Math.sin((double)(f7 * (float) Math.PI * 2.0F)) + 1.0) * 0.2F;
      float f8 = (float)(Math.sin((double)(f7 * (float) Math.PI * 2.0F - 1.0F)) + 1.0);
      f8 = (f8 * f8 * 1.0F + f8 * 2.0F) * 0.05F;
      GlStateManager.translate(0.0F, f8 - 2.0F, -3.0F);
      GlStateManager.rotate(f8 * 2.0F, 1.0F, 0.0F, 0.0F);
      float f9 = -30.0F;
      float f10 = 0.0F;
      float f11 = 1.5F;
      double[] movementOffsets = entityDragon.getMovementOffsets(6, this.partialTicks);
      float f12 = this.updateRotations(entityDragon.getMovementOffsets(5, this.partialTicks)[0] - entityDragon.getMovementOffsets(10, this.partialTicks)[0]);
      float f13 = this.updateRotations(entityDragon.getMovementOffsets(5, this.partialTicks)[0] + (double)(f12 / 2.0F));
      f9 += 2.0F;
      float f14 = f7 * (float) Math.PI * 2.0F;
      f9 = 20.0F;
      float f15 = -12.0F;

      for (int i = 0; i < 5; i++) {
         double[] movementOffsets2 = entityDragon.getMovementOffsets(5 - i, this.partialTicks);
         float f16 = (float)Math.cos((double)((float)i * 0.45F + f14)) * 0.15F;
         this.spine.rotateAngleY = this.updateRotations(movementOffsets2[0] - movementOffsets[0]) * (float) Math.PI / 180.0F * f11;
         this.spine.rotateAngleX = f16 + (float)(movementOffsets2[1] - movementOffsets[1]) * (float) Math.PI / 180.0F * f11 * 5.0F;
         this.spine.rotateAngleZ = -this.updateRotations(movementOffsets2[0] - (double)f13) * (float) Math.PI / 180.0F * f11;
         this.spine.rotationPointY = f9;
         this.spine.rotationPointZ = f15;
         this.spine.rotationPointX = f10;
         f9 = (float)((double)f9 + Math.sin((double)this.spine.rotateAngleX) * 10.0);
         f15 = (float)((double)f15 - Math.cos((double)this.spine.rotateAngleY) * Math.cos((double)this.spine.rotateAngleX) * 10.0);
         f10 = (float)((double)f10 - Math.sin((double)this.spine.rotateAngleY) * Math.cos((double)this.spine.rotateAngleX) * 10.0);
         this.spine.render(f6);
      }

      this.head.rotationPointY = f9;
      this.head.rotationPointZ = f15;
      this.head.rotationPointX = f10;
      double[] movementOffsets3 = entityDragon.getMovementOffsets(0, this.partialTicks);
      this.head.rotateAngleY = this.updateRotations(movementOffsets3[0] - movementOffsets[0]) * (float) Math.PI / 180.0F * 1.0F;
      this.head.rotateAngleZ = -this.updateRotations(movementOffsets3[0] - (double)f13) * (float) Math.PI / 180.0F * 1.0F;
      this.head.render(f6);
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-f12 * f11 * 1.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(0.0F, -1.0F, 0.0F);
      this.body.rotateAngleZ = 0.0F;
      this.body.render(f6);

      for (int j = 0; j < 2; j++) {
         GlStateManager.enableCull();
         float f17 = f7 * (float) Math.PI * 2.0F;
         this.wing.rotateAngleX = 0.125F - (float)Math.cos((double)f17) * 0.2F;
         this.wing.rotateAngleY = 0.25F;
         this.wing.rotateAngleZ = (float)(Math.sin((double)f17) + 0.125) * 0.8F;
         this.wingTip.rotateAngleZ = -((float)(Math.sin((double)(f17 + 2.0F)) + 0.5)) * 0.75F;
         this.rearLeg.rotateAngleX = 1.0F + f8 * 0.1F;
         this.rearLegTip.rotateAngleX = 0.5F + f8 * 0.1F;
         this.rearFoot.rotateAngleX = 0.75F + f8 * 0.1F;
         this.frontLeg.rotateAngleX = 1.3F + f8 * 0.1F;
         this.frontLegTip.rotateAngleX = -0.5F - f8 * 0.1F;
         this.frontFoot.rotateAngleX = 0.75F + f8 * 0.1F;
         this.wing.render(f6);
         this.frontLeg.render(f6);
         this.rearLeg.render(f6);
         GlStateManager.scale(-1.0F, 1.0F, 1.0F);
         if (j == 0) {
            GlStateManager.cullFace(1028);
         }
      }

      GlStateManager.popMatrix();
      GlStateManager.cullFace(1029);
      GlStateManager.disableCull();
      float f18 = -((float)Math.sin((double)(f7 * (float) Math.PI * 2.0F))) * 0.0F;
      f14 = f7 * (float) Math.PI * 2.0F;
      f9 = 10.0F;
      f15 = 60.0F;
      f10 = 0.0F;
      movementOffsets = entityDragon.getMovementOffsets(11, this.partialTicks);

      for (int k = 0; k < 12; k++) {
         movementOffsets3 = entityDragon.getMovementOffsets(12 + k, this.partialTicks);
         f18 = (float)((double)f18 + Math.sin((double)((float)k * 0.45F + f14)) * 0.05F);
         this.spine.rotateAngleY = (this.updateRotations(movementOffsets3[0] - movementOffsets[0]) * f11 + 180.0F) * (float) Math.PI / 180.0F;
         this.spine.rotateAngleX = f18 + (float)(movementOffsets3[1] - movementOffsets[1]) * (float) Math.PI / 180.0F * f11 * 5.0F;
         this.spine.rotateAngleZ = this.updateRotations(movementOffsets3[0] - (double)f13) * (float) Math.PI / 180.0F * f11;
         this.spine.rotationPointY = f9;
         this.spine.rotationPointZ = f15;
         this.spine.rotationPointX = f10;
         f9 = (float)((double)f9 + Math.sin((double)this.spine.rotateAngleX) * 10.0);
         f15 = (float)((double)f15 - Math.cos((double)this.spine.rotateAngleY) * Math.cos((double)this.spine.rotateAngleX) * 10.0);
         f10 = (float)((double)f10 - Math.sin((double)this.spine.rotateAngleY) * Math.cos((double)this.spine.rotateAngleX) * 10.0);
         this.spine.render(f6);
      }

      GlStateManager.popMatrix();
   }

   @Override
   public void setLivingAnimations(EntityLivingBase entity, float f, float f2, float f3) {
      this.partialTicks = f3;
   }
}
