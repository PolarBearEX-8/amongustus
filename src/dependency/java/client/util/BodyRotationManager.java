package client.util;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEnderdragon;
import net.minecraft.entity.EntityLivingBase;

public class BodyRotationManager extends PassiveFloatEntry {
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager6;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager5;
   public BoxesManager boxesManager9;
   public BoxesManager boxesManager8;
   public BoxesManager boxesManager10;
   public BoxesManager boxesManager12;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager7;
   public BoxesManager boxesManager11;
   public BoxesManager boxesManager;
   public float factor2;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      GlStateManager.pushMatrix();
      EntityEnderdragon entityEnderdragon = (EntityEnderdragon)entity;
      float factor = entityEnderdragon.factor41 + (entityEnderdragon.factor40 - entityEnderdragon.factor41) * this.factor2;
      this.boxesManager2.factor = (float)(Math.sin((double)(factor * (float) Math.PI * 2.0F)) + 1.0) * 0.2F;
      float factor3 = (float)(Math.sin((double)(factor * (float) Math.PI * 2.0F - 1.0F)) + 1.0);
      float factor4 = (factor3 * factor3 * 1.0F + factor3 * 2.0F) * 0.05F;
      GlStateManager.translate(0.0F, factor4 - 2.0F, -3.0F);
      GlStateManager.rotate(factor4 * 2.0F, 1.0F, 0.0F, 0.0F);
      float factor5 = 0.0F;
      float factor6 = 1.5F;
      double[] offsetArray = entityEnderdragon.resolveDoubleArray(6, this.factor2);
      float factor7 = this.computeFactor(entityEnderdragon.resolveDoubleArray(5, this.factor2)[0] - entityEnderdragon.resolveDoubleArray(10, this.factor2)[0]);
      float factor8 = this.computeFactor(entityEnderdragon.resolveDoubleArray(5, this.factor2)[0] + (double)(factor7 / 2.0F));
      float factor9 = factor * (float) Math.PI * 2.0F;
      float factor10 = 20.0F;
      float factor11 = -12.0F;

      for (int index = 0; index < 5; index++) {
         double[] offsetArray2 = entityEnderdragon.resolveDoubleArray(5 - index, this.factor2);
         float factor12 = (float)Math.cos((double)((float)index * 0.45F + factor9)) * 0.15F;
         this.boxesManager6.factor9 = this.computeFactor(offsetArray2[0] - offsetArray[0]) * (float) Math.PI / 180.0F * factor6;
         this.boxesManager6.factor = factor12 + (float)(offsetArray2[1] - offsetArray[1]) * (float) Math.PI / 180.0F * factor6 * 5.0F;
         this.boxesManager6.factor8 = -this.computeFactor(offsetArray2[0] - (double)factor8) * (float) Math.PI / 180.0F * factor6;
         this.boxesManager6.factor2 = factor10;
         this.boxesManager6.factor10 = factor11;
         this.boxesManager6.factor5 = factor5;
         factor10 = (float)((double)factor10 + Math.sin((double)this.boxesManager6.factor) * 10.0);
         factor11 = (float)((double)factor11 - Math.cos((double)this.boxesManager6.factor9) * Math.cos((double)this.boxesManager6.factor) * 10.0);
         factor5 = (float)((double)factor5 - Math.sin((double)this.boxesManager6.factor9) * Math.cos((double)this.boxesManager6.factor) * 10.0);
         this.boxesManager6.applyFloat3(posZ);
      }

      this.boxesManager4.factor2 = factor10;
      this.boxesManager4.factor10 = factor11;
      this.boxesManager4.factor5 = factor5;
      double[] offsetArray3 = entityEnderdragon.resolveDoubleArray(0, this.factor2);
      this.boxesManager4.factor9 = this.computeFactor(offsetArray3[0] - offsetArray[0]) * (float) Math.PI / 180.0F * 1.0F;
      this.boxesManager4.factor8 = -this.computeFactor(offsetArray3[0] - (double)factor8) * (float) Math.PI / 180.0F * 1.0F;
      this.boxesManager4.applyFloat3(posZ);
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-factor7 * factor6 * 1.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(0.0F, -1.0F, 0.0F);
      this.boxesManager5.factor8 = 0.0F;
      this.boxesManager5.applyFloat3(posZ);

      for (int index2 = 0; index2 < 2; index2++) {
         GlStateManager.enableLighting();
         float factor13 = factor * (float) Math.PI * 2.0F;
         this.boxesManager11.factor = 0.125F - (float)Math.cos((double)factor13) * 0.2F;
         this.boxesManager11.factor9 = 0.25F;
         this.boxesManager11.factor8 = (float)(Math.sin((double)factor13) + 0.125) * 0.8F;
         this.boxesManager.factor8 = -((float)(Math.sin((double)(factor13 + 2.0F)) + 0.5)) * 0.75F;
         this.boxesManager9.factor = 1.0F + factor4 * 0.1F;
         this.boxesManager10.factor = 0.5F + factor4 * 0.1F;
         this.boxesManager3.factor = 0.75F + factor4 * 0.1F;
         this.boxesManager8.factor = 1.3F + factor4 * 0.1F;
         this.boxesManager12.factor = -0.5F - factor4 * 0.1F;
         this.boxesManager7.factor = 0.75F + factor4 * 0.1F;
         this.boxesManager11.applyFloat3(posZ);
         this.boxesManager8.applyFloat3(posZ);
         this.boxesManager9.applyFloat3(posZ);
         GlStateManager.translate3f3(-1.0F, 1.0F, 1.0F);
         if (index2 == 0) {
            GlStateManager.glCullFace(1028);
         }
      }

      GlStateManager.popMatrix();
      GlStateManager.glCullFace(1029);
      GlStateManager.disableAlpha();
      float factor14 = -((float)Math.sin((double)(factor * (float) Math.PI * 2.0F))) * 0.0F;
      float factor15 = factor * (float) Math.PI * 2.0F;
      float factor16 = 10.0F;
      float factor17 = 60.0F;
      float factor18 = 0.0F;
      double[] offsetArray4 = entityEnderdragon.resolveDoubleArray(11, this.factor2);

      for (int index3 = 0; index3 < 12; index3++) {
         double[] offsetArray5 = entityEnderdragon.resolveDoubleArray(12 + index3, this.factor2);
         factor14 = (float)((double)factor14 + Math.sin((double)((float)index3 * 0.45F + factor15)) * 0.05F);
         this.boxesManager6.factor9 = (this.computeFactor(offsetArray5[0] - offsetArray4[0]) * factor6 + 180.0F) * (float) Math.PI / 180.0F;
         this.boxesManager6.factor = factor14 + (float)(offsetArray5[1] - offsetArray4[1]) * (float) Math.PI / 180.0F * factor6 * 5.0F;
         this.boxesManager6.factor8 = this.computeFactor(offsetArray5[0] - (double)factor8) * (float) Math.PI / 180.0F * factor6;
         this.boxesManager6.factor2 = factor16;
         this.boxesManager6.factor10 = factor17;
         this.boxesManager6.factor5 = factor18;
         factor16 = (float)((double)factor16 + Math.sin((double)this.boxesManager6.factor) * 10.0);
         factor17 = (float)((double)factor17 - Math.cos((double)this.boxesManager6.factor9) * Math.cos((double)this.boxesManager6.factor) * 10.0);
         factor18 = (float)((double)factor18 - Math.sin((double)this.boxesManager6.factor9) * Math.cos((double)this.boxesManager6.factor) * 10.0);
         this.boxesManager6.applyFloat3(posZ);
      }

      GlStateManager.popMatrix();
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      this.factor2 = posZ;
   }

   private float computeFactor(double offset) {
      double offset2 = offset;

      while (offset2 >= 180.0) {
         offset2 -= 360.0;
      }

      while (offset2 < -180.0) {
         offset2 += 360.0;
      }

      return (float)offset2;
   }

   public BodyRotationManager(float factor) {
      this.count2 = 256;
      this.count = 256;
      this.put("body.body", 0, 0);
      this.put("wing.skin", -56, 88);
      this.put("wingtip.skin", -56, 144);
      this.put("rearleg.main", 0, 0);
      this.put("rearfoot.main", 112, 0);
      this.put("rearlegtip.main", 196, 0);
      this.put("head.upperhead", 112, 30);
      this.put("wing.bone", 112, 88);
      this.put("head.upperlip", 176, 44);
      this.put("jaw.jaw", 176, 65);
      this.put("frontleg.main", 112, 104);
      this.put("wingtip.bone", 112, 136);
      this.put("frontfoot.main", 144, 104);
      this.put("neck.box", 192, 104);
      this.put("frontlegtip.main", 226, 138);
      this.put("body.scale", 220, 53);
      this.put("head.scale", 0, 0);
      this.put("neck.scale", 48, 0);
      this.put("head.nostril", 112, 0);
      float factor3 = -16.0F;
      this.boxesManager4 = new BoxesManager(this, "head");
      this.boxesManager4.resolveBoxesManager("upperlip", -6.0F, -1.0F, -8.0F + factor3, 12, 5, 16);
      this.boxesManager4.resolveBoxesManager("upperhead", -8.0F, -8.0F, 6.0F + factor3, 16, 16, 16);
      this.boxesManager4.enabled = true;
      this.boxesManager4.resolveBoxesManager("scale", -5.0F, -12.0F, 12.0F + factor3, 2, 4, 6);
      this.boxesManager4.resolveBoxesManager("nostril", -5.0F, -3.0F, -6.0F + factor3, 2, 2, 4);
      this.boxesManager4.enabled = false;
      this.boxesManager4.resolveBoxesManager("scale", 3.0F, -12.0F, 12.0F + factor3, 2, 4, 6);
      this.boxesManager4.resolveBoxesManager("nostril", 3.0F, -3.0F, -6.0F + factor3, 2, 2, 4);
      this.boxesManager2 = new BoxesManager(this, "jaw");
      this.boxesManager2.applyFloat(0.0F, 4.0F, 8.0F + factor3);
      this.boxesManager2.resolveBoxesManager("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
      this.boxesManager4.applyBoxesManager(this.boxesManager2);
      this.boxesManager6 = new BoxesManager(this, "neck");
      this.boxesManager6.resolveBoxesManager("box", -5.0F, -5.0F, -5.0F, 10, 10, 10);
      this.boxesManager6.resolveBoxesManager("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6);
      this.boxesManager5 = new BoxesManager(this, "body");
      this.boxesManager5.applyFloat(0.0F, 4.0F, 8.0F);
      this.boxesManager5.resolveBoxesManager("body", -12.0F, 0.0F, -16.0F, 24, 24, 64);
      this.boxesManager5.resolveBoxesManager("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12);
      this.boxesManager5.resolveBoxesManager("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12);
      this.boxesManager5.resolveBoxesManager("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12);
      this.boxesManager11 = new BoxesManager(this, "wing");
      this.boxesManager11.applyFloat(-12.0F, 5.0F, 2.0F);
      this.boxesManager11.resolveBoxesManager("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
      this.boxesManager11.resolveBoxesManager("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.boxesManager = new BoxesManager(this, "wingtip");
      this.boxesManager.applyFloat(-56.0F, 0.0F, 0.0F);
      this.boxesManager.resolveBoxesManager("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
      this.boxesManager.resolveBoxesManager("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.boxesManager11.applyBoxesManager(this.boxesManager);
      this.boxesManager8 = new BoxesManager(this, "frontleg");
      this.boxesManager8.applyFloat(-12.0F, 20.0F, 2.0F);
      this.boxesManager8.resolveBoxesManager("main", -4.0F, -4.0F, -4.0F, 8, 24, 8);
      this.boxesManager12 = new BoxesManager(this, "frontlegtip");
      this.boxesManager12.applyFloat(0.0F, 20.0F, -1.0F);
      this.boxesManager12.resolveBoxesManager("main", -3.0F, -1.0F, -3.0F, 6, 24, 6);
      this.boxesManager8.applyBoxesManager(this.boxesManager12);
      this.boxesManager7 = new BoxesManager(this, "frontfoot");
      this.boxesManager7.applyFloat(0.0F, 23.0F, 0.0F);
      this.boxesManager7.resolveBoxesManager("main", -4.0F, 0.0F, -12.0F, 8, 4, 16);
      this.boxesManager12.applyBoxesManager(this.boxesManager7);
      this.boxesManager9 = new BoxesManager(this, "rearleg");
      this.boxesManager9.applyFloat(-16.0F, 16.0F, 42.0F);
      this.boxesManager9.resolveBoxesManager("main", -8.0F, -4.0F, -8.0F, 16, 32, 16);
      this.boxesManager10 = new BoxesManager(this, "rearlegtip");
      this.boxesManager10.applyFloat(0.0F, 32.0F, -4.0F);
      this.boxesManager10.resolveBoxesManager("main", -6.0F, -2.0F, 0.0F, 12, 32, 12);
      this.boxesManager9.applyBoxesManager(this.boxesManager10);
      this.boxesManager3 = new BoxesManager(this, "rearfoot");
      this.boxesManager3.applyFloat(0.0F, 31.0F, 4.0F);
      this.boxesManager3.resolveBoxesManager("main", -9.0F, 0.0F, -20.0F, 18, 6, 24);
      this.boxesManager10.applyBoxesManager(this.boxesManager3);
   }
}
