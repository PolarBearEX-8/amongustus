package client.util;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityRabbit;
import net.minecraft.util.MathHelper;

public class AuxiliaryHeadMainManager extends PassiveFloatEntry {
   public BoxesManager boxesManager7;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager5;
   public BoxesManager boxesManager8;
   public BoxesManager boxesManager10;
   public BoxesManager boxesManager9;
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager11;
   public BoxesManager boxesManager6;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager12;
   public float factor2 = 0.0F;
   public float factor3 = 0.0F;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      if (this.enabled2) {
         float factor = 2.0F;
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, 5.0F * posZ, 2.0F * posZ);
         this.boxesManager4.applyFloat3(posZ);
         this.boxesManager6.applyFloat3(posZ);
         this.boxesManager11.applyFloat3(posZ);
         this.boxesManager12.applyFloat3(posZ);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(1.0F / factor, 1.0F / factor, 1.0F / factor);
         GlStateManager.translate(0.0F, 24.0F * posZ, 0.0F);
         this.boxesManager7.applyFloat3(posZ);
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager5.applyFloat3(posZ);
         this.boxesManager8.applyFloat3(posZ);
         this.boxesManager10.applyFloat3(posZ);
         this.boxesManager9.applyFloat3(posZ);
         this.boxesManager3.applyFloat3(posZ);
         GlStateManager.popMatrix();
      } else {
         this.boxesManager7.applyFloat3(posZ);
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager5.applyFloat3(posZ);
         this.boxesManager8.applyFloat3(posZ);
         this.boxesManager10.applyFloat3(posZ);
         this.boxesManager9.applyFloat3(posZ);
         this.boxesManager4.applyFloat3(posZ);
         this.boxesManager11.applyFloat3(posZ);
         this.boxesManager6.applyFloat3(posZ);
         this.boxesManager3.applyFloat3(posZ);
         this.boxesManager12.applyFloat3(posZ);
      }
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      float factor = posX3 - (float)entity.ticksExisted;
      EntityRabbit entityRabbit = (EntityRabbit)entity;
      this.boxesManager12.factor = this.boxesManager4.factor = this.boxesManager11.factor = this.boxesManager6.factor = posY * (float) (Math.PI / 180.0);
      this.boxesManager12.factor9 = this.boxesManager4.factor9 = posX4 * (float) (Math.PI / 180.0);
      this.boxesManager11.factor9 = this.boxesManager12.factor9 - (float) (Math.PI / 12);
      this.boxesManager6.factor9 = this.boxesManager12.factor9 + (float) (Math.PI / 12);
      this.factor2 = MathHelper.abs(entityRabbit.computeFactor3(factor) * (float) Math.PI);
      this.boxesManager.factor = this.boxesManager5.factor = (this.factor2 * 50.0F - 21.0F) * (float) (Math.PI / 180.0);
      this.boxesManager7.factor = this.boxesManager2.factor = this.factor2 * 50.0F * (float) (Math.PI / 180.0);
      this.boxesManager10.factor = this.boxesManager9.factor = (this.factor2 * -40.0F - 11.0F) * (float) (Math.PI / 180.0);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
   }

   private void applyBoxesManager(BoxesManager boxesManager13, float posX, float posY, float posZ) {
      boxesManager13.factor = posX;
      boxesManager13.factor9 = posY;
      boxesManager13.factor8 = posZ;
   }

   public AuxiliaryHeadMainManager() {
      this.put("head.main", 0, 0);
      this.put("head.nose", 0, 24);
      this.put("head.ear1", 0, 10);
      this.put("head.ear2", 6, 10);
      this.boxesManager7 = new BoxesManager(this, 26, 24);
      this.boxesManager7.resolveBoxesManager(-1.0F, 5.5F, -3.7F, 2, 1, 7);
      this.boxesManager7.applyFloat(3.0F, 17.5F, 3.7F);
      this.boxesManager7.enabled = true;
      this.applyBoxesManager(this.boxesManager7, 0.0F, 0.0F, 0.0F);
      this.boxesManager2 = new BoxesManager(this, 8, 24);
      this.boxesManager2.resolveBoxesManager(-1.0F, 5.5F, -3.7F, 2, 1, 7);
      this.boxesManager2.applyFloat(-3.0F, 17.5F, 3.7F);
      this.boxesManager2.enabled = true;
      this.applyBoxesManager(this.boxesManager2, 0.0F, 0.0F, 0.0F);
      this.boxesManager = new BoxesManager(this, 30, 15);
      this.boxesManager.resolveBoxesManager(-1.0F, 0.0F, 0.0F, 2, 4, 5);
      this.boxesManager.applyFloat(3.0F, 17.5F, 3.7F);
      this.boxesManager.enabled = true;
      this.applyBoxesManager(this.boxesManager, (float) (-Math.PI / 9), 0.0F, 0.0F);
      this.boxesManager5 = new BoxesManager(this, 16, 15);
      this.boxesManager5.resolveBoxesManager(-1.0F, 0.0F, 0.0F, 2, 4, 5);
      this.boxesManager5.applyFloat(-3.0F, 17.5F, 3.7F);
      this.boxesManager5.enabled = true;
      this.applyBoxesManager(this.boxesManager5, (float) (-Math.PI / 9), 0.0F, 0.0F);
      this.boxesManager8 = new BoxesManager(this, 0, 0);
      this.boxesManager8.resolveBoxesManager(-3.0F, -2.0F, -10.0F, 6, 5, 10);
      this.boxesManager8.applyFloat(0.0F, 19.0F, 8.0F);
      this.boxesManager8.enabled = true;
      this.applyBoxesManager(this.boxesManager8, (float) (-Math.PI / 9), 0.0F, 0.0F);
      this.boxesManager10 = new BoxesManager(this, 8, 15);
      this.boxesManager10.resolveBoxesManager(-1.0F, 0.0F, -1.0F, 2, 7, 2);
      this.boxesManager10.applyFloat(3.0F, 17.0F, -1.0F);
      this.boxesManager10.enabled = true;
      this.applyBoxesManager(this.boxesManager10, (float) (-Math.PI / 18), 0.0F, 0.0F);
      this.boxesManager9 = new BoxesManager(this, 0, 15);
      this.boxesManager9.resolveBoxesManager(-1.0F, 0.0F, -1.0F, 2, 7, 2);
      this.boxesManager9.applyFloat(-3.0F, 17.0F, -1.0F);
      this.boxesManager9.enabled = true;
      this.applyBoxesManager(this.boxesManager9, (float) (-Math.PI / 18), 0.0F, 0.0F);
      this.boxesManager4 = new BoxesManager(this, 32, 0);
      this.boxesManager4.resolveBoxesManager(-2.5F, -4.0F, -5.0F, 5, 4, 5);
      this.boxesManager4.applyFloat(0.0F, 16.0F, -1.0F);
      this.boxesManager4.enabled = true;
      this.applyBoxesManager(this.boxesManager4, 0.0F, 0.0F, 0.0F);
      this.boxesManager11 = new BoxesManager(this, 52, 0);
      this.boxesManager11.resolveBoxesManager(-2.5F, -9.0F, -1.0F, 2, 5, 1);
      this.boxesManager11.applyFloat(0.0F, 16.0F, -1.0F);
      this.boxesManager11.enabled = true;
      this.applyBoxesManager(this.boxesManager11, 0.0F, (float) (-Math.PI / 12), 0.0F);
      this.boxesManager6 = new BoxesManager(this, 58, 0);
      this.boxesManager6.resolveBoxesManager(0.5F, -9.0F, -1.0F, 2, 5, 1);
      this.boxesManager6.applyFloat(0.0F, 16.0F, -1.0F);
      this.boxesManager6.enabled = true;
      this.applyBoxesManager(this.boxesManager6, 0.0F, (float) (Math.PI / 12), 0.0F);
      this.boxesManager3 = new BoxesManager(this, 52, 6);
      this.boxesManager3.resolveBoxesManager(-1.5F, -1.5F, 0.0F, 3, 3, 2);
      this.boxesManager3.applyFloat(0.0F, 20.0F, 7.0F);
      this.boxesManager3.enabled = true;
      this.applyBoxesManager(this.boxesManager3, -0.3490659F, 0.0F, 0.0F);
      this.boxesManager12 = new BoxesManager(this, 32, 9);
      this.boxesManager12.resolveBoxesManager(-0.5F, -2.5F, -5.5F, 1, 1, 1);
      this.boxesManager12.applyFloat(0.0F, 16.0F, -1.0F);
      this.boxesManager12.enabled = true;
      this.applyBoxesManager(this.boxesManager12, 0.0F, 0.0F, 0.0F);
   }
}
