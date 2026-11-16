package client.util;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityOzelot;
import net.minecraft.util.MathHelper;

public class HeadMainManager extends PassiveFloatEntry {
   public BoxesManager boxesManager7;
   public BoxesManager boxesManager8;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager6;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager5;
   public int count3 = 1;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      if (this.enabled2) {
         float factor = 2.0F;
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(1.5F / factor, 1.5F / factor, 1.5F / factor);
         GlStateManager.translate(0.0F, 10.0F * posZ, 4.0F * posZ);
         this.boxesManager.applyFloat3(posZ);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(1.0F / factor, 1.0F / factor, 1.0F / factor);
         GlStateManager.translate(0.0F, 24.0F * posZ, 0.0F);
         this.boxesManager5.applyFloat3(posZ);
         this.boxesManager7.applyFloat3(posZ);
         this.boxesManager8.applyFloat3(posZ);
         this.boxesManager3.applyFloat3(posZ);
         this.boxesManager4.applyFloat3(posZ);
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager6.applyFloat3(posZ);
         GlStateManager.popMatrix();
      } else {
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager5.applyFloat3(posZ);
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager6.applyFloat3(posZ);
         this.boxesManager7.applyFloat3(posZ);
         this.boxesManager8.applyFloat3(posZ);
         this.boxesManager3.applyFloat3(posZ);
         this.boxesManager4.applyFloat3(posZ);
      }
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      this.boxesManager.factor = posY / (180.0F / (float)Math.PI);
      this.boxesManager.factor9 = posX4 / (180.0F / (float)Math.PI);
      if (this.count3 != 3) {
         this.boxesManager5.factor = (float) (Math.PI / 2);
         if (this.count3 == 2) {
            this.boxesManager7.factor = MathHelper.cos(posX * 0.6662F) * 1.0F * posX2;
            this.boxesManager8.factor = MathHelper.cos(posX * 0.6662F + 0.3F) * 1.0F * posX2;
            this.boxesManager3.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI + 0.3F) * 1.0F * posX2;
            this.boxesManager4.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.0F * posX2;
            this.boxesManager6.factor = 1.7278761F + (float) (Math.PI / 10) * MathHelper.cos(posX) * posX2;
         } else {
            this.boxesManager7.factor = MathHelper.cos(posX * 0.6662F) * 1.0F * posX2;
            this.boxesManager8.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.0F * posX2;
            this.boxesManager3.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.0F * posX2;
            this.boxesManager4.factor = MathHelper.cos(posX * 0.6662F) * 1.0F * posX2;
            if (this.count3 == 1) {
               this.boxesManager6.factor = 1.7278761F + (float) (Math.PI / 4) * MathHelper.cos(posX) * posX2;
            } else {
               this.boxesManager6.factor = 1.7278761F + 0.47123894F * MathHelper.cos(posX) * posX2;
            }
         }
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      EntityOzelot entityOzelot = (EntityOzelot)livingEntity;
      this.boxesManager5.factor2 = 12.0F;
      this.boxesManager5.factor10 = -10.0F;
      this.boxesManager.factor2 = 15.0F;
      this.boxesManager.factor10 = -9.0F;
      this.boxesManager2.factor2 = 15.0F;
      this.boxesManager2.factor10 = 8.0F;
      this.boxesManager6.factor2 = 20.0F;
      this.boxesManager6.factor10 = 14.0F;
      this.boxesManager3.factor2 = this.boxesManager4.factor2 = 13.8F;
      this.boxesManager3.factor10 = this.boxesManager4.factor10 = -5.0F;
      this.boxesManager7.factor2 = this.boxesManager8.factor2 = 18.0F;
      this.boxesManager7.factor10 = this.boxesManager8.factor10 = 5.0F;
      this.boxesManager2.factor = 0.9F;
      if (entityOzelot.isSprinting()) {
         this.boxesManager5.factor2++;
         this.boxesManager.factor2 += 2.0F;
         this.boxesManager2.factor2++;
         this.boxesManager6.factor2 += -4.0F;
         this.boxesManager6.factor10 += 2.0F;
         this.boxesManager2.factor = (float) (Math.PI / 2);
         this.boxesManager6.factor = (float) (Math.PI / 2);
         this.count3 = 0;
      } else if (entityOzelot.isRiding()) {
         this.boxesManager6.factor2 = this.boxesManager2.factor2;
         this.boxesManager6.factor10 += 2.0F;
         this.boxesManager2.factor = (float) (Math.PI / 2);
         this.boxesManager6.factor = (float) (Math.PI / 2);
         this.count3 = 2;
      } else if (entityOzelot.isActive33()) {
         this.boxesManager5.factor = (float) (Math.PI / 4);
         this.boxesManager5.factor2 += -4.0F;
         this.boxesManager5.factor10 += 5.0F;
         this.boxesManager.factor2 += -3.3F;
         this.boxesManager.factor10++;
         this.boxesManager2.factor2 += 8.0F;
         this.boxesManager2.factor10 += -2.0F;
         this.boxesManager6.factor2 += 2.0F;
         this.boxesManager6.factor10 += -0.8F;
         this.boxesManager2.factor = 1.7278761F;
         this.boxesManager6.factor = 2.670354F;
         this.boxesManager3.factor = this.boxesManager4.factor = (float) (-Math.PI / 20);
         this.boxesManager3.factor2 = this.boxesManager4.factor2 = 15.8F;
         this.boxesManager3.factor10 = this.boxesManager4.factor10 = -7.0F;
         this.boxesManager7.factor = this.boxesManager8.factor = (float) (-Math.PI / 2);
         this.boxesManager7.factor2 = this.boxesManager8.factor2 = 21.0F;
         this.boxesManager7.factor10 = this.boxesManager8.factor10 = 1.0F;
         this.count3 = 3;
      } else {
         this.count3 = 1;
      }
   }

   public HeadMainManager() {
      this.put("head.main", 0, 0);
      this.put("head.nose", 0, 24);
      this.put("head.ear1", 0, 10);
      this.put("head.ear2", 6, 10);
      this.boxesManager = new BoxesManager(this, "head");
      this.boxesManager.resolveBoxesManager("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
      this.boxesManager.resolveBoxesManager("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
      this.boxesManager.resolveBoxesManager("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
      this.boxesManager.resolveBoxesManager("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
      this.boxesManager.applyFloat(0.0F, 15.0F, -9.0F);
      this.boxesManager5 = new BoxesManager(this, 20, 0);
      this.boxesManager5.applyFloatByFloat(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
      this.boxesManager5.applyFloat(0.0F, 12.0F, -10.0F);
      this.boxesManager2 = new BoxesManager(this, 0, 15);
      this.boxesManager2.resolveBoxesManager(-0.5F, 0.0F, 0.0F, 1, 8, 1);
      this.boxesManager2.factor = 0.9F;
      this.boxesManager2.applyFloat(0.0F, 15.0F, 8.0F);
      this.boxesManager6 = new BoxesManager(this, 4, 15);
      this.boxesManager6.resolveBoxesManager(-0.5F, 0.0F, 0.0F, 1, 8, 1);
      this.boxesManager6.applyFloat(0.0F, 20.0F, 14.0F);
      this.boxesManager7 = new BoxesManager(this, 8, 13);
      this.boxesManager7.resolveBoxesManager(-1.0F, 0.0F, 1.0F, 2, 6, 2);
      this.boxesManager7.applyFloat(1.1F, 18.0F, 5.0F);
      this.boxesManager8 = new BoxesManager(this, 8, 13);
      this.boxesManager8.resolveBoxesManager(-1.0F, 0.0F, 1.0F, 2, 6, 2);
      this.boxesManager8.applyFloat(-1.1F, 18.0F, 5.0F);
      this.boxesManager3 = new BoxesManager(this, 40, 0);
      this.boxesManager3.resolveBoxesManager(-1.0F, 0.0F, 0.0F, 2, 10, 2);
      this.boxesManager3.applyFloat(1.2F, 13.8F, -5.0F);
      this.boxesManager4 = new BoxesManager(this, 40, 0);
      this.boxesManager4.resolveBoxesManager(-1.0F, 0.0F, 0.0F, 2, 10, 2);
      this.boxesManager4.applyFloat(-1.2F, 13.8F, -5.0F);
   }
}
