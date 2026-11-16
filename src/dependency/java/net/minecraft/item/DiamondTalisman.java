package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityWolf;
import net.minecraft.util.MathHelper;

public class DiamondTalisman extends PassiveFloatEntry {
   public BoxesManager boxesManager5;
   public BoxesManager boxesManager7;
   public BoxesManager boxesManager6;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager8;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      super.applyEntity(entity, posX, posX2, posX3, posX4, posY, posZ, flag);
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      if (this.enabled2) {
         float factor = 2.0F;
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, 5.0F * posZ, 2.0F * posZ);
         this.boxesManager5.applyFloat2(posZ);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(1.0F / factor, 1.0F / factor, 1.0F / factor);
         GlStateManager.translate(0.0F, 24.0F * posZ, 0.0F);
         this.boxesManager7.applyFloat3(posZ);
         this.boxesManager6.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager4.applyFloat3(posZ);
         this.boxesManager3.applyFloat3(posZ);
         this.boxesManager2.applyFloat2(posZ);
         this.boxesManager8.applyFloat3(posZ);
         GlStateManager.popMatrix();
      } else {
         this.boxesManager5.applyFloat2(posZ);
         this.boxesManager7.applyFloat3(posZ);
         this.boxesManager6.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager4.applyFloat3(posZ);
         this.boxesManager3.applyFloat3(posZ);
         this.boxesManager2.applyFloat2(posZ);
         this.boxesManager8.applyFloat3(posZ);
      }
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager5.factor = posY / (180.0F / (float)Math.PI);
      this.boxesManager5.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager2.factor = posX3;
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      EntityWolf entityWolf = (EntityWolf)livingEntity;
      if (entityWolf.isActive35()) {
         this.boxesManager2.factor9 = 0.0F;
      } else {
         this.boxesManager2.factor9 = MathHelper.cos(posX * 0.6662F) * 1.4F * posY;
      }

      if (entityWolf.isActive33()) {
         this.boxesManager8.applyFloat(-1.0F, 16.0F, -3.0F);
         this.boxesManager8.factor = (float) (Math.PI * 2.0 / 5.0);
         this.boxesManager8.factor9 = 0.0F;
         this.boxesManager7.applyFloat(0.0F, 18.0F, 0.0F);
         this.boxesManager7.factor = (float) (Math.PI / 4);
         this.boxesManager2.applyFloat(-1.0F, 21.0F, 6.0F);
         this.boxesManager6.applyFloat(-2.5F, 22.0F, 2.0F);
         this.boxesManager6.factor = (float) (Math.PI * 3.0 / 2.0);
         this.boxesManager.applyFloat(0.5F, 22.0F, 2.0F);
         this.boxesManager.factor = (float) (Math.PI * 3.0 / 2.0);
         this.boxesManager4.factor = 5.811947F;
         this.boxesManager4.applyFloat(-2.49F, 17.0F, -4.0F);
         this.boxesManager3.factor = 5.811947F;
         this.boxesManager3.applyFloat(0.51F, 17.0F, -4.0F);
      } else {
         this.boxesManager7.applyFloat(0.0F, 14.0F, 2.0F);
         this.boxesManager7.factor = (float) (Math.PI / 2);
         this.boxesManager8.applyFloat(-1.0F, 14.0F, -3.0F);
         this.boxesManager8.factor = this.boxesManager7.factor;
         this.boxesManager2.applyFloat(-1.0F, 12.0F, 8.0F);
         this.boxesManager6.applyFloat(-2.5F, 16.0F, 7.0F);
         this.boxesManager.applyFloat(0.5F, 16.0F, 7.0F);
         this.boxesManager4.applyFloat(-2.5F, 16.0F, -4.0F);
         this.boxesManager3.applyFloat(0.5F, 16.0F, -4.0F);
         this.boxesManager6.factor = MathHelper.cos(posX * 0.6662F) * 1.4F * posY;
         this.boxesManager.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.4F * posY;
         this.boxesManager4.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.4F * posY;
         this.boxesManager3.factor = MathHelper.cos(posX * 0.6662F) * 1.4F * posY;
      }

      this.boxesManager5.factor8 = entityWolf.computeFactor3(posZ) + entityWolf.computeFactorByFloat(posZ, 0.0F);
      this.boxesManager8.factor8 = entityWolf.computeFactorByFloat(posZ, -0.08F);
      this.boxesManager7.factor8 = entityWolf.computeFactorByFloat(posZ, -0.16F);
      this.boxesManager2.factor8 = entityWolf.computeFactorByFloat(posZ, -0.2F);
   }

   public DiamondTalisman() {
      float factor = 0.0F;
      float factor2 = 13.5F;
      this.boxesManager5 = new BoxesManager(this, 0, 0);
      this.boxesManager5.applyFloatByFloat(-3.0F, -3.0F, -2.0F, 6, 6, 4, factor);
      this.boxesManager5.applyFloat(-1.0F, factor2, -7.0F);
      this.boxesManager7 = new BoxesManager(this, 18, 14);
      this.boxesManager7.applyFloatByFloat(-4.0F, -2.0F, -3.0F, 6, 9, 6, factor);
      this.boxesManager7.applyFloat(0.0F, 14.0F, 2.0F);
      this.boxesManager8 = new BoxesManager(this, 21, 0);
      this.boxesManager8.applyFloatByFloat(-4.0F, -3.0F, -3.0F, 8, 6, 7, factor);
      this.boxesManager8.applyFloat(-1.0F, 14.0F, 2.0F);
      this.boxesManager6 = new BoxesManager(this, 0, 18);
      this.boxesManager6.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 8, 2, factor);
      this.boxesManager6.applyFloat(-2.5F, 16.0F, 7.0F);
      this.boxesManager = new BoxesManager(this, 0, 18);
      this.boxesManager.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 8, 2, factor);
      this.boxesManager.applyFloat(0.5F, 16.0F, 7.0F);
      this.boxesManager4 = new BoxesManager(this, 0, 18);
      this.boxesManager4.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 8, 2, factor);
      this.boxesManager4.applyFloat(-2.5F, 16.0F, -4.0F);
      this.boxesManager3 = new BoxesManager(this, 0, 18);
      this.boxesManager3.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 8, 2, factor);
      this.boxesManager3.applyFloat(0.5F, 16.0F, -4.0F);
      this.boxesManager2 = new BoxesManager(this, 9, 18);
      this.boxesManager2.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 8, 2, factor);
      this.boxesManager2.applyFloat(-1.0F, 12.0F, 8.0F);
      this.boxesManager5.resolveBoxesManagerByIndex(16, 14).applyFloatByFloat(-3.0F, -5.0F, 0.0F, 2, 2, 1, factor);
      this.boxesManager5.resolveBoxesManagerByIndex(16, 14).applyFloatByFloat(1.0F, -5.0F, 0.0F, 2, 2, 1, factor);
      this.boxesManager5.resolveBoxesManagerByIndex(0, 10).applyFloatByFloat(-1.5F, 0.0F, -5.0F, 3, 3, 4, factor);
   }
}
