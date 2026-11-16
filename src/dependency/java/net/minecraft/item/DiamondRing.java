package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class DiamondRing extends PassiveFloatEntry {
   public BoxesManager boxesManager6 = new BoxesManager(this, 0, 0);
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager5;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager4;
   public float factor2 = 8.0F;
   public float factor3 = 4.0F;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      if (this.enabled2) {
         float factor = 2.0F;
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, this.factor2 * posZ, this.factor3 * posZ);
         this.boxesManager6.applyFloat3(posZ);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(1.0F / factor, 1.0F / factor, 1.0F / factor);
         GlStateManager.translate(0.0F, 24.0F * posZ, 0.0F);
         this.boxesManager3.applyFloat3(posZ);
         this.boxesManager5.applyFloat3(posZ);
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager4.applyFloat3(posZ);
         GlStateManager.popMatrix();
      } else {
         this.boxesManager6.applyFloat3(posZ);
         this.boxesManager3.applyFloat3(posZ);
         this.boxesManager5.applyFloat3(posZ);
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager4.applyFloat3(posZ);
      }
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      this.boxesManager6.factor = posY / (180.0F / (float)Math.PI);
      this.boxesManager6.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager3.factor = (float) (Math.PI / 2);
      this.boxesManager5.factor = MathHelper.cos(posX * 0.6662F) * 1.4F * posX2;
      this.boxesManager2.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.4F * posX2;
      this.boxesManager.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.4F * posX2;
      this.boxesManager4.factor = MathHelper.cos(posX * 0.6662F) * 1.4F * posX2;
   }

   public DiamondRing(int count, float factor) {
      this.boxesManager6.applyFloatByFloat(-4.0F, -4.0F, -8.0F, 8, 8, 8, factor);
      this.boxesManager6.applyFloat(0.0F, (float)(18 - count), -6.0F);
      this.boxesManager3 = new BoxesManager(this, 28, 8);
      this.boxesManager3.applyFloatByFloat(-5.0F, -10.0F, -7.0F, 10, 16, 8, factor);
      this.boxesManager3.applyFloat(0.0F, (float)(17 - count), 2.0F);
      this.boxesManager5 = new BoxesManager(this, 0, 16);
      this.boxesManager5.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, count, 4, factor);
      this.boxesManager5.applyFloat(-3.0F, (float)(24 - count), 7.0F);
      this.boxesManager2 = new BoxesManager(this, 0, 16);
      this.boxesManager2.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, count, 4, factor);
      this.boxesManager2.applyFloat(3.0F, (float)(24 - count), 7.0F);
      this.boxesManager = new BoxesManager(this, 0, 16);
      this.boxesManager.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, count, 4, factor);
      this.boxesManager.applyFloat(-3.0F, (float)(24 - count), -5.0F);
      this.boxesManager4 = new BoxesManager(this, 0, 16);
      this.boxesManager4.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, count, 4, factor);
      this.boxesManager4.applyFloat(3.0F, (float)(24 - count), -5.0F);
   }
}
