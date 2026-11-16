package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.MathHelper;

public class DiamondHammer extends PassiveFloatEntry {
   public BoxesManager boxesManager17;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager25;
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager10;
   public BoxesManager boxesManager15;
   public BoxesManager boxesManager19;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager5;
   public BoxesManager boxesManager7;
   public BoxesManager boxesManager9;
   public BoxesManager boxesManager39;
   public BoxesManager boxesManager29;
   public BoxesManager boxesManager6;
   public BoxesManager boxesManager37;
   public BoxesManager boxesManager14;
   public BoxesManager boxesManager8;
   public BoxesManager boxesManager31;
   public BoxesManager boxesManager23;
   public BoxesManager boxesManager21;
   public BoxesManager boxesManager27;
   public BoxesManager boxesManager16;
   public BoxesManager boxesManager32;
   public BoxesManager boxesManager28;
   public BoxesManager boxesManager36;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager33;
   public BoxesManager boxesManager24;
   public BoxesManager boxesManager38;
   public BoxesManager boxesManager12;
   public BoxesManager boxesManager34;
   public BoxesManager boxesManager18;
   public BoxesManager boxesManager35;
   public BoxesManager boxesManager20;
   public BoxesManager boxesManager30;
   public BoxesManager boxesManager11;
   public BoxesManager boxesManager13;
   public BoxesManager boxesManager22;
   public BoxesManager boxesManager26;

   private void applyBoxesManager(BoxesManager boxesManager40, float posX, float posY, float posZ) {
      boxesManager40.factor = posX;
      boxesManager40.factor9 = posY;
      boxesManager40.factor8 = posZ;
   }

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      EntityHorse entityHorse = (EntityHorse)entity;
      int count = entityHorse.getCount12();
      float factor = entityHorse.computeFactor3(0.0F);
      boolean active = entityHorse.isActive33();
      boolean flag2 = active && entityHorse.isActive34();
      boolean flag3 = active && entityHorse.isActive32();
      boolean flag4 = count == 1 || count == 2;
      float factor2 = entityHorse.getFactor3();
      boolean flag5 = entityHorse.entity != null;
      if (flag2) {
         this.boxesManager5.applyFloat3(posZ);
         this.boxesManager38.applyFloat3(posZ);
         this.boxesManager12.applyFloat3(posZ);
         this.boxesManager34.applyFloat3(posZ);
         this.boxesManager18.applyFloat3(posZ);
         this.boxesManager35.applyFloat3(posZ);
         this.boxesManager20.applyFloat3(posZ);
         this.boxesManager30.applyFloat3(posZ);
         this.boxesManager11.applyFloat3(posZ);
         this.boxesManager13.applyFloat3(posZ);
         if (flag5) {
            this.boxesManager22.applyFloat3(posZ);
            this.boxesManager26.applyFloat3(posZ);
         }
      }

      if (!active) {
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(factor2, 0.5F + factor2 * 0.5F, factor2);
         GlStateManager.translate(0.0F, 0.95F * (1.0F - factor2), 0.0F);
      }

      this.boxesManager37.applyFloat3(posZ);
      this.boxesManager14.applyFloat3(posZ);
      this.boxesManager8.applyFloat3(posZ);
      this.boxesManager31.applyFloat3(posZ);
      this.boxesManager23.applyFloat3(posZ);
      this.boxesManager21.applyFloat3(posZ);
      this.boxesManager27.applyFloat3(posZ);
      this.boxesManager16.applyFloat3(posZ);
      this.boxesManager32.applyFloat3(posZ);
      this.boxesManager28.applyFloat3(posZ);
      this.boxesManager36.applyFloat3(posZ);
      this.boxesManager2.applyFloat3(posZ);
      if (!active) {
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(factor2, factor2, factor2);
         GlStateManager.translate(0.0F, 1.35F * (1.0F - factor2), 0.0F);
      }

      this.boxesManager9.applyFloat3(posZ);
      this.boxesManager39.applyFloat3(posZ);
      this.boxesManager29.applyFloat3(posZ);
      this.boxesManager6.applyFloat3(posZ);
      this.boxesManager3.applyFloat3(posZ);
      this.boxesManager7.applyFloat3(posZ);
      if (!active) {
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         float factor3 = 0.5F + factor2 * factor2 * 0.5F;
         GlStateManager.translate3f3(factor3, factor3, factor3);
         if (factor <= 0.0F) {
            GlStateManager.translate(0.0F, 1.35F * (1.0F - factor2), 0.0F);
         } else {
            GlStateManager.translate(0.0F, 0.9F * (1.0F - factor2) * factor + 1.35F * (1.0F - factor2) * (1.0F - factor), 0.15F * (1.0F - factor2) * factor);
         }
      }

      if (flag4) {
         this.boxesManager15.applyFloat3(posZ);
         this.boxesManager19.applyFloat3(posZ);
      } else {
         this.boxesManager4.applyFloat3(posZ);
         this.boxesManager10.applyFloat3(posZ);
      }

      this.boxesManager17.applyFloat3(posZ);
      if (!active) {
         GlStateManager.popMatrix();
      }

      if (flag3) {
         this.boxesManager33.applyFloat3(posZ);
         this.boxesManager24.applyFloat3(posZ);
      }
   }

   private float computeFactor(float posX, float posY, float posZ) {
      float factor = posY - posX;

      while (factor < -180.0F) {
         factor += 360.0F;
      }

      while (factor >= 180.0F) {
         factor -= 360.0F;
      }

      return posX + posZ * factor;
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      super.applyEntityLivingBase(livingEntity, posX, posY, posZ);
      float factor = this.computeFactor(livingEntity.factor12, livingEntity.scaleFactor8, posZ);
      float factor2 = this.computeFactor(livingEntity.scaleFactor22, livingEntity.scaleFactor12, posZ);
      float factor3 = livingEntity.prevRotationPitch + (livingEntity.rotationPitch - livingEntity.prevRotationPitch) * posZ;
      float factor4 = factor2 - factor;
      float factor5 = factor3 / (180.0F / (float)Math.PI);
      if (factor4 > 20.0F) {
         factor4 = 20.0F;
      }

      if (factor4 < -20.0F) {
         factor4 = -20.0F;
      }

      if (posY > 0.2F) {
         factor5 += MathHelper.cos(posX * 0.4F) * 0.15F * posY;
      }

      EntityHorse entityHorse = (EntityHorse)livingEntity;
      float factor6 = entityHorse.computeFactor3(posZ);
      float factor7 = entityHorse.computeFactor4(posZ);
      float factor8 = 1.0F - factor7;
      float factor9 = entityHorse.computeFactor5(posZ);
      boolean flag = entityHorse.count35 != 0;
      boolean active = entityHorse.isActive34();
      boolean flag2 = entityHorse.entity != null;
      float factor10 = (float)livingEntity.ticksExisted + posZ;
      float factor11 = MathHelper.cos(posX * 0.6662F + (float) Math.PI);
      float factor12 = factor11 * 0.8F * posY;
      this.boxesManager17.factor2 = 4.0F;
      this.boxesManager17.factor10 = -10.0F;
      this.boxesManager39.factor2 = 3.0F;
      this.boxesManager29.factor10 = 14.0F;
      this.boxesManager24.factor2 = 3.0F;
      this.boxesManager24.factor10 = 10.0F;
      this.boxesManager9.factor = 0.0F;
      this.boxesManager17.factor = (float) (Math.PI / 6) + factor5;
      this.boxesManager17.factor9 = factor4 / (180.0F / (float)Math.PI);
      this.boxesManager17.factor = factor7 * ((float) (Math.PI / 12) + factor5) + factor6 * 2.18166F + (1.0F - Math.max(factor7, factor6)) * this.boxesManager17.factor;
      this.boxesManager17.factor9 = factor7 * factor4 / (180.0F / (float)Math.PI) + (1.0F - Math.max(factor7, factor6)) * this.boxesManager17.factor9;
      this.boxesManager17.factor2 = factor7 * -6.0F + factor6 * 11.0F + (1.0F - Math.max(factor7, factor6)) * this.boxesManager17.factor2;
      this.boxesManager17.factor10 = factor7 * -1.0F + factor6 * -10.0F + (1.0F - Math.max(factor7, factor6)) * this.boxesManager17.factor10;
      this.boxesManager39.factor2 = factor7 * 9.0F + factor8 * this.boxesManager39.factor2;
      this.boxesManager29.factor10 = factor7 * 18.0F + factor8 * this.boxesManager29.factor10;
      this.boxesManager24.factor2 = factor7 * 5.5F + factor8 * this.boxesManager24.factor2;
      this.boxesManager24.factor10 = factor7 * 15.0F + factor8 * this.boxesManager24.factor10;
      this.boxesManager9.factor = factor7 * -45.0F / (180.0F / (float)Math.PI) + factor8 * this.boxesManager9.factor;
      this.boxesManager4.factor2 = this.boxesManager17.factor2;
      this.boxesManager10.factor2 = this.boxesManager17.factor2;
      this.boxesManager15.factor2 = this.boxesManager17.factor2;
      this.boxesManager19.factor2 = this.boxesManager17.factor2;
      this.boxesManager3.factor2 = this.boxesManager17.factor2;
      this.boxesManager.factor2 = 0.02F;
      this.boxesManager25.factor2 = 0.0F;
      this.boxesManager7.factor2 = this.boxesManager17.factor2;
      this.boxesManager4.factor10 = this.boxesManager17.factor10;
      this.boxesManager10.factor10 = this.boxesManager17.factor10;
      this.boxesManager15.factor10 = this.boxesManager17.factor10;
      this.boxesManager19.factor10 = this.boxesManager17.factor10;
      this.boxesManager3.factor10 = this.boxesManager17.factor10;
      this.boxesManager.factor10 = 0.02F - factor9 * 1.0F;
      this.boxesManager25.factor10 = 0.0F + factor9 * 1.0F;
      this.boxesManager7.factor10 = this.boxesManager17.factor10;
      this.boxesManager4.factor = this.boxesManager17.factor;
      this.boxesManager10.factor = this.boxesManager17.factor;
      this.boxesManager15.factor = this.boxesManager17.factor;
      this.boxesManager19.factor = this.boxesManager17.factor;
      this.boxesManager3.factor = this.boxesManager17.factor;
      this.boxesManager.factor = 0.0F - 0.09424778F * factor9;
      this.boxesManager25.factor = 0.0F + (float) (Math.PI / 20) * factor9;
      this.boxesManager7.factor = this.boxesManager17.factor;
      this.boxesManager4.factor9 = this.boxesManager17.factor9;
      this.boxesManager10.factor9 = this.boxesManager17.factor9;
      this.boxesManager15.factor9 = this.boxesManager17.factor9;
      this.boxesManager19.factor9 = this.boxesManager17.factor9;
      this.boxesManager3.factor9 = this.boxesManager17.factor9;
      this.boxesManager.factor9 = 0.0F;
      this.boxesManager25.factor9 = 0.0F;
      this.boxesManager7.factor9 = this.boxesManager17.factor9;
      this.boxesManager33.factor = factor12 / 5.0F;
      this.boxesManager24.factor = -factor12 / 5.0F;
      float factor13 = (float) (Math.PI / 12) * factor7;
      float factor14 = MathHelper.cos(factor10 * 0.6F + (float) Math.PI);
      this.boxesManager27.factor2 = -2.0F * factor7 + 9.0F * factor8;
      this.boxesManager27.factor10 = -2.0F * factor7 + -8.0F * factor8;
      this.boxesManager28.factor2 = this.boxesManager27.factor2;
      this.boxesManager28.factor10 = this.boxesManager27.factor10;
      this.boxesManager14.factor2 = this.boxesManager37.factor2 + MathHelper.abs((float) (Math.PI / 2) + factor13 + factor8 * -factor11 * 0.5F * posY) * 7.0F;
      this.boxesManager14.factor10 = this.boxesManager37.factor10 + MathHelper.cos((float) (Math.PI * 3.0 / 2.0) + factor13 + factor8 * -factor11 * 0.5F * posY) * 7.0F;
      this.boxesManager23.factor2 = this.boxesManager31.factor2 + MathHelper.abs((float) (Math.PI / 2) + factor13 + factor8 * factor11 * 0.5F * posY) * 7.0F;
      this.boxesManager23.factor10 = this.boxesManager31.factor10 + MathHelper.cos((float) (Math.PI * 3.0 / 2.0) + factor13 + factor8 * factor11 * 0.5F * posY) * 7.0F;
      float factor15 = ((float) (-Math.PI / 3) + factor14) * factor7 + factor12 * factor8;
      float factor16 = ((float) (-Math.PI / 3) + -factor14) * factor7 + -factor12 * factor8;
      this.boxesManager16.factor2 = this.boxesManager27.factor2 + MathHelper.abs((float) (Math.PI / 2) + factor15) * 7.0F;
      this.boxesManager16.factor10 = this.boxesManager27.factor10 + MathHelper.cos((float) (Math.PI * 3.0 / 2.0) + factor15) * 7.0F;
      this.boxesManager36.factor2 = this.boxesManager28.factor2 + MathHelper.abs((float) (Math.PI / 2) + factor16) * 7.0F;
      this.boxesManager36.factor10 = this.boxesManager28.factor10 + MathHelper.cos((float) (Math.PI * 3.0 / 2.0) + factor16) * 7.0F;
      this.boxesManager37.factor = factor13 + -factor11 * 0.5F * posY * factor8;
      this.boxesManager14.factor = -0.08726646F * factor7 + (-factor11 * 0.5F * posY - Math.max(0.0F, factor11 * 0.5F * posY)) * factor8;
      this.boxesManager8.factor = this.boxesManager14.factor;
      this.boxesManager31.factor = factor13 + factor11 * 0.5F * posY * factor8;
      this.boxesManager23.factor = -0.08726646F * factor7 + (factor11 * 0.5F * posY - Math.max(0.0F, -factor11 * 0.5F * posY)) * factor8;
      this.boxesManager21.factor = this.boxesManager23.factor;
      this.boxesManager27.factor = factor15;
      this.boxesManager16.factor = (this.boxesManager27.factor + (float) Math.PI * Math.max(0.0F, 0.2F + factor14 * 0.2F)) * factor7
         + (factor12 + Math.max(0.0F, factor11 * 0.5F * posY)) * factor8;
      this.boxesManager32.factor = this.boxesManager16.factor;
      this.boxesManager28.factor = factor16;
      this.boxesManager36.factor = (this.boxesManager28.factor + (float) Math.PI * Math.max(0.0F, 0.2F - factor14 * 0.2F)) * factor7
         + (-factor12 + Math.max(0.0F, -factor11 * 0.5F * posY)) * factor8;
      this.boxesManager2.factor = this.boxesManager36.factor;
      this.boxesManager8.factor2 = this.boxesManager14.factor2;
      this.boxesManager8.factor10 = this.boxesManager14.factor10;
      this.boxesManager21.factor2 = this.boxesManager23.factor2;
      this.boxesManager21.factor10 = this.boxesManager23.factor10;
      this.boxesManager32.factor2 = this.boxesManager16.factor2;
      this.boxesManager32.factor10 = this.boxesManager16.factor10;
      this.boxesManager2.factor2 = this.boxesManager36.factor2;
      this.boxesManager2.factor10 = this.boxesManager36.factor10;
      if (active) {
         this.boxesManager38.factor2 = factor7 * 0.5F + factor8 * 2.0F;
         this.boxesManager38.factor10 = factor7 * 11.0F + factor8 * 2.0F;
         this.boxesManager12.factor2 = this.boxesManager38.factor2;
         this.boxesManager34.factor2 = this.boxesManager38.factor2;
         this.boxesManager18.factor2 = this.boxesManager38.factor2;
         this.boxesManager20.factor2 = this.boxesManager38.factor2;
         this.boxesManager35.factor2 = this.boxesManager38.factor2;
         this.boxesManager30.factor2 = this.boxesManager38.factor2;
         this.boxesManager33.factor2 = this.boxesManager24.factor2;
         this.boxesManager12.factor10 = this.boxesManager38.factor10;
         this.boxesManager34.factor10 = this.boxesManager38.factor10;
         this.boxesManager18.factor10 = this.boxesManager38.factor10;
         this.boxesManager20.factor10 = this.boxesManager38.factor10;
         this.boxesManager35.factor10 = this.boxesManager38.factor10;
         this.boxesManager30.factor10 = this.boxesManager38.factor10;
         this.boxesManager33.factor10 = this.boxesManager24.factor10;
         this.boxesManager38.factor = this.boxesManager9.factor;
         this.boxesManager12.factor = this.boxesManager9.factor;
         this.boxesManager34.factor = this.boxesManager9.factor;
         this.boxesManager22.factor2 = this.boxesManager17.factor2;
         this.boxesManager26.factor2 = this.boxesManager17.factor2;
         this.boxesManager5.factor2 = this.boxesManager17.factor2;
         this.boxesManager11.factor2 = this.boxesManager17.factor2;
         this.boxesManager13.factor2 = this.boxesManager17.factor2;
         this.boxesManager22.factor10 = this.boxesManager17.factor10;
         this.boxesManager26.factor10 = this.boxesManager17.factor10;
         this.boxesManager5.factor10 = this.boxesManager17.factor10;
         this.boxesManager11.factor10 = this.boxesManager17.factor10;
         this.boxesManager13.factor10 = this.boxesManager17.factor10;
         this.boxesManager22.factor = factor5;
         this.boxesManager26.factor = factor5;
         this.boxesManager5.factor = this.boxesManager17.factor;
         this.boxesManager11.factor = this.boxesManager17.factor;
         this.boxesManager13.factor = this.boxesManager17.factor;
         this.boxesManager5.factor9 = this.boxesManager17.factor9;
         this.boxesManager11.factor9 = this.boxesManager17.factor9;
         this.boxesManager22.factor9 = this.boxesManager17.factor9;
         this.boxesManager13.factor9 = this.boxesManager17.factor9;
         this.boxesManager26.factor9 = this.boxesManager17.factor9;
         if (flag2) {
            this.boxesManager18.factor = (float) (-Math.PI / 3);
            this.boxesManager35.factor = (float) (-Math.PI / 3);
            this.boxesManager20.factor = (float) (-Math.PI / 3);
            this.boxesManager30.factor = (float) (-Math.PI / 3);
            this.boxesManager18.factor8 = 0.0F;
            this.boxesManager35.factor8 = 0.0F;
            this.boxesManager20.factor8 = 0.0F;
            this.boxesManager30.factor8 = 0.0F;
         } else {
            this.boxesManager18.factor = factor12 / 3.0F;
            this.boxesManager35.factor = factor12 / 3.0F;
            this.boxesManager20.factor = factor12 / 3.0F;
            this.boxesManager30.factor = factor12 / 3.0F;
            this.boxesManager18.factor8 = factor12 / 5.0F;
            this.boxesManager35.factor8 = factor12 / 5.0F;
            this.boxesManager20.factor8 = -factor12 / 5.0F;
            this.boxesManager30.factor8 = -factor12 / 5.0F;
         }
      }

      float factor17 = -1.3089F + posY * 1.5F;
      if (factor17 > 0.0F) {
         factor17 = 0.0F;
      }

      if (flag) {
         this.boxesManager39.factor9 = MathHelper.cos(factor10 * 0.7F);
         factor17 = 0.0F;
      } else {
         this.boxesManager39.factor9 = 0.0F;
      }

      this.boxesManager29.factor9 = this.boxesManager39.factor9;
      this.boxesManager6.factor9 = this.boxesManager39.factor9;
      this.boxesManager29.factor2 = this.boxesManager39.factor2;
      this.boxesManager6.factor2 = this.boxesManager39.factor2;
      this.boxesManager29.factor10 = this.boxesManager39.factor10;
      this.boxesManager6.factor10 = this.boxesManager39.factor10;
      this.boxesManager39.factor = factor17;
      this.boxesManager29.factor = factor17;
      this.boxesManager6.factor = -0.2618F + factor17;
   }

   public DiamondHammer() {
      this.count2 = 128;
      this.count = 128;
      this.boxesManager9 = new BoxesManager(this, 0, 34);
      this.boxesManager9.resolveBoxesManager(-5.0F, -8.0F, -19.0F, 10, 10, 24);
      this.boxesManager9.applyFloat(0.0F, 11.0F, 9.0F);
      this.boxesManager39 = new BoxesManager(this, 44, 0);
      this.boxesManager39.resolveBoxesManager(-1.0F, -1.0F, 0.0F, 2, 2, 3);
      this.boxesManager39.applyFloat(0.0F, 3.0F, 14.0F);
      this.applyBoxesManager(this.boxesManager39, -1.134464F, 0.0F, 0.0F);
      this.boxesManager29 = new BoxesManager(this, 38, 7);
      this.boxesManager29.resolveBoxesManager(-1.5F, -2.0F, 3.0F, 3, 4, 7);
      this.boxesManager29.applyFloat(0.0F, 3.0F, 14.0F);
      this.applyBoxesManager(this.boxesManager29, -1.134464F, 0.0F, 0.0F);
      this.boxesManager6 = new BoxesManager(this, 24, 3);
      this.boxesManager6.resolveBoxesManager(-1.5F, -4.5F, 9.0F, 3, 4, 7);
      this.boxesManager6.applyFloat(0.0F, 3.0F, 14.0F);
      this.applyBoxesManager(this.boxesManager6, -1.40215F, 0.0F, 0.0F);
      this.boxesManager37 = new BoxesManager(this, 78, 29);
      this.boxesManager37.resolveBoxesManager(-2.5F, -2.0F, -2.5F, 4, 9, 5);
      this.boxesManager37.applyFloat(4.0F, 9.0F, 11.0F);
      this.boxesManager14 = new BoxesManager(this, 78, 43);
      this.boxesManager14.resolveBoxesManager(-2.0F, 0.0F, -1.5F, 3, 5, 3);
      this.boxesManager14.applyFloat(4.0F, 16.0F, 11.0F);
      this.boxesManager8 = new BoxesManager(this, 78, 51);
      this.boxesManager8.resolveBoxesManager(-2.5F, 5.1F, -2.0F, 4, 3, 4);
      this.boxesManager8.applyFloat(4.0F, 16.0F, 11.0F);
      this.boxesManager31 = new BoxesManager(this, 96, 29);
      this.boxesManager31.resolveBoxesManager(-1.5F, -2.0F, -2.5F, 4, 9, 5);
      this.boxesManager31.applyFloat(-4.0F, 9.0F, 11.0F);
      this.boxesManager23 = new BoxesManager(this, 96, 43);
      this.boxesManager23.resolveBoxesManager(-1.0F, 0.0F, -1.5F, 3, 5, 3);
      this.boxesManager23.applyFloat(-4.0F, 16.0F, 11.0F);
      this.boxesManager21 = new BoxesManager(this, 96, 51);
      this.boxesManager21.resolveBoxesManager(-1.5F, 5.1F, -2.0F, 4, 3, 4);
      this.boxesManager21.applyFloat(-4.0F, 16.0F, 11.0F);
      this.boxesManager27 = new BoxesManager(this, 44, 29);
      this.boxesManager27.resolveBoxesManager(-1.9F, -1.0F, -2.1F, 3, 8, 4);
      this.boxesManager27.applyFloat(4.0F, 9.0F, -8.0F);
      this.boxesManager16 = new BoxesManager(this, 44, 41);
      this.boxesManager16.resolveBoxesManager(-1.9F, 0.0F, -1.6F, 3, 5, 3);
      this.boxesManager16.applyFloat(4.0F, 16.0F, -8.0F);
      this.boxesManager32 = new BoxesManager(this, 44, 51);
      this.boxesManager32.resolveBoxesManager(-2.4F, 5.1F, -2.1F, 4, 3, 4);
      this.boxesManager32.applyFloat(4.0F, 16.0F, -8.0F);
      this.boxesManager28 = new BoxesManager(this, 60, 29);
      this.boxesManager28.resolveBoxesManager(-1.1F, -1.0F, -2.1F, 3, 8, 4);
      this.boxesManager28.applyFloat(-4.0F, 9.0F, -8.0F);
      this.boxesManager36 = new BoxesManager(this, 60, 41);
      this.boxesManager36.resolveBoxesManager(-1.1F, 0.0F, -1.6F, 3, 5, 3);
      this.boxesManager36.applyFloat(-4.0F, 16.0F, -8.0F);
      this.boxesManager2 = new BoxesManager(this, 60, 51);
      this.boxesManager2.resolveBoxesManager(-1.6F, 5.1F, -2.1F, 4, 3, 4);
      this.boxesManager2.applyFloat(-4.0F, 16.0F, -8.0F);
      this.boxesManager17 = new BoxesManager(this, 0, 0);
      this.boxesManager17.resolveBoxesManager(-2.5F, -10.0F, -1.5F, 5, 5, 7);
      this.boxesManager17.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager17, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.boxesManager = new BoxesManager(this, 24, 18);
      this.boxesManager.resolveBoxesManager(-2.0F, -10.0F, -7.0F, 4, 3, 6);
      this.boxesManager.applyFloat(0.0F, 3.95F, -10.0F);
      this.applyBoxesManager(this.boxesManager, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.boxesManager25 = new BoxesManager(this, 24, 27);
      this.boxesManager25.resolveBoxesManager(-2.0F, -7.0F, -6.5F, 4, 2, 5);
      this.boxesManager25.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager25, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.boxesManager17.applyBoxesManager(this.boxesManager);
      this.boxesManager17.applyBoxesManager(this.boxesManager25);
      this.boxesManager4 = new BoxesManager(this, 0, 0);
      this.boxesManager4.resolveBoxesManager(0.45F, -12.0F, 4.0F, 2, 3, 1);
      this.boxesManager4.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager4, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.boxesManager10 = new BoxesManager(this, 0, 0);
      this.boxesManager10.resolveBoxesManager(-2.45F, -12.0F, 4.0F, 2, 3, 1);
      this.boxesManager10.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager10, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.boxesManager15 = new BoxesManager(this, 0, 12);
      this.boxesManager15.resolveBoxesManager(-2.0F, -16.0F, 4.0F, 2, 7, 1);
      this.boxesManager15.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager15, (float) (Math.PI / 6), 0.0F, (float) (Math.PI / 12));
      this.boxesManager19 = new BoxesManager(this, 0, 12);
      this.boxesManager19.resolveBoxesManager(0.0F, -16.0F, 4.0F, 2, 7, 1);
      this.boxesManager19.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager19, (float) (Math.PI / 6), 0.0F, (float) (-Math.PI / 12));
      this.boxesManager3 = new BoxesManager(this, 0, 12);
      this.boxesManager3.resolveBoxesManager(-2.05F, -9.8F, -2.0F, 4, 14, 8);
      this.boxesManager3.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager3, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.boxesManager33 = new BoxesManager(this, 0, 34);
      this.boxesManager33.resolveBoxesManager(-3.0F, 0.0F, 0.0F, 8, 8, 3);
      this.boxesManager33.applyFloat(-7.5F, 3.0F, 10.0F);
      this.applyBoxesManager(this.boxesManager33, 0.0F, (float) (Math.PI / 2), 0.0F);
      this.boxesManager24 = new BoxesManager(this, 0, 47);
      this.boxesManager24.resolveBoxesManager(-3.0F, 0.0F, 0.0F, 8, 8, 3);
      this.boxesManager24.applyFloat(4.5F, 3.0F, 10.0F);
      this.applyBoxesManager(this.boxesManager24, 0.0F, (float) (Math.PI / 2), 0.0F);
      this.boxesManager38 = new BoxesManager(this, 80, 0);
      this.boxesManager38.resolveBoxesManager(-5.0F, 0.0F, -3.0F, 10, 1, 8);
      this.boxesManager38.applyFloat(0.0F, 2.0F, 2.0F);
      this.boxesManager12 = new BoxesManager(this, 106, 9);
      this.boxesManager12.resolveBoxesManager(-1.5F, -1.0F, -3.0F, 3, 1, 2);
      this.boxesManager12.applyFloat(0.0F, 2.0F, 2.0F);
      this.boxesManager34 = new BoxesManager(this, 80, 9);
      this.boxesManager34.resolveBoxesManager(-4.0F, -1.0F, 3.0F, 8, 1, 2);
      this.boxesManager34.applyFloat(0.0F, 2.0F, 2.0F);
      this.boxesManager35 = new BoxesManager(this, 74, 0);
      this.boxesManager35.resolveBoxesManager(-0.5F, 6.0F, -1.0F, 1, 2, 2);
      this.boxesManager35.applyFloat(5.0F, 3.0F, 2.0F);
      this.boxesManager18 = new BoxesManager(this, 70, 0);
      this.boxesManager18.resolveBoxesManager(-0.5F, 0.0F, -0.5F, 1, 6, 1);
      this.boxesManager18.applyFloat(5.0F, 3.0F, 2.0F);
      this.boxesManager30 = new BoxesManager(this, 74, 4);
      this.boxesManager30.resolveBoxesManager(-0.5F, 6.0F, -1.0F, 1, 2, 2);
      this.boxesManager30.applyFloat(-5.0F, 3.0F, 2.0F);
      this.boxesManager20 = new BoxesManager(this, 80, 0);
      this.boxesManager20.resolveBoxesManager(-0.5F, 0.0F, -0.5F, 1, 6, 1);
      this.boxesManager20.applyFloat(-5.0F, 3.0F, 2.0F);
      this.boxesManager11 = new BoxesManager(this, 74, 13);
      this.boxesManager11.resolveBoxesManager(1.5F, -8.0F, -4.0F, 1, 2, 2);
      this.boxesManager11.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager11, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.boxesManager13 = new BoxesManager(this, 74, 13);
      this.boxesManager13.resolveBoxesManager(-2.5F, -8.0F, -4.0F, 1, 2, 2);
      this.boxesManager13.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager13, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.boxesManager22 = new BoxesManager(this, 44, 10);
      this.boxesManager22.resolveBoxesManager(2.6F, -6.0F, -6.0F, 0, 3, 16);
      this.boxesManager22.applyFloat(0.0F, 4.0F, -10.0F);
      this.boxesManager26 = new BoxesManager(this, 44, 5);
      this.boxesManager26.resolveBoxesManager(-2.6F, -6.0F, -6.0F, 0, 3, 16);
      this.boxesManager26.applyFloat(0.0F, 4.0F, -10.0F);
      this.boxesManager7 = new BoxesManager(this, 58, 0);
      this.boxesManager7.resolveBoxesManager(-1.0F, -11.5F, 5.0F, 2, 16, 4);
      this.boxesManager7.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager7, (float) (Math.PI / 6), 0.0F, 0.0F);
      this.boxesManager5 = new BoxesManager(this, 80, 12);
      this.boxesManager5.applyFloatByFloat(-2.5F, -10.1F, -7.0F, 5, 5, 12, 0.2F);
      this.boxesManager5.applyFloat(0.0F, 4.0F, -10.0F);
      this.applyBoxesManager(this.boxesManager5, (float) (Math.PI / 6), 0.0F, 0.0F);
   }
}
