package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class DiamondDagger extends PassiveFloatEntry {
   public BoxesManager boxesManager7 = new BoxesManager(this, 0, 0);
   public BoxesManager boxesManager8;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager6;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager5;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager4;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      if (this.enabled2) {
         float factor = 2.0F;
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, 5.0F * posZ, 2.0F * posZ);
         this.boxesManager7.applyFloat3(posZ);
         this.boxesManager3.applyFloat3(posZ);
         this.boxesManager4.applyFloat3(posZ);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(1.0F / factor, 1.0F / factor, 1.0F / factor);
         GlStateManager.translate(0.0F, 24.0F * posZ, 0.0F);
         this.boxesManager8.applyFloat3(posZ);
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager6.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager5.applyFloat3(posZ);
         GlStateManager.popMatrix();
      } else {
         this.boxesManager7.applyFloat3(posZ);
         this.boxesManager3.applyFloat3(posZ);
         this.boxesManager4.applyFloat3(posZ);
         this.boxesManager8.applyFloat3(posZ);
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager6.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager5.applyFloat3(posZ);
      }
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      this.boxesManager7.factor = posY / (180.0F / (float)Math.PI);
      this.boxesManager7.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager3.factor = this.boxesManager7.factor;
      this.boxesManager3.factor9 = this.boxesManager7.factor9;
      this.boxesManager4.factor = this.boxesManager7.factor;
      this.boxesManager4.factor9 = this.boxesManager7.factor9;
      this.boxesManager8.factor = (float) (Math.PI / 2);
      this.boxesManager2.factor = MathHelper.cos(posX * 0.6662F) * 1.4F * posX2;
      this.boxesManager6.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.4F * posX2;
      this.boxesManager.factor8 = posX3;
      this.boxesManager5.factor8 = -posX3;
   }

   public DiamondDagger() {
      this.boxesManager7.applyFloatByFloat(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
      this.boxesManager7.applyFloat(0.0F, (float)15, -4.0F);
      this.boxesManager3 = new BoxesManager(this, 14, 0);
      this.boxesManager3.applyFloatByFloat(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
      this.boxesManager3.applyFloat(0.0F, (float)15, -4.0F);
      this.boxesManager4 = new BoxesManager(this, 14, 4);
      this.boxesManager4.applyFloatByFloat(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
      this.boxesManager4.applyFloat(0.0F, (float)15, -4.0F);
      this.boxesManager8 = new BoxesManager(this, 0, 9);
      this.boxesManager8.applyFloatByFloat(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
      this.boxesManager8.applyFloat(0.0F, (float)16, 0.0F);
      this.boxesManager2 = new BoxesManager(this, 26, 0);
      this.boxesManager2.resolveBoxesManager(-1.0F, 0.0F, -3.0F, 3, 5, 3);
      this.boxesManager2.applyFloat(-2.0F, (float)19, 1.0F);
      this.boxesManager6 = new BoxesManager(this, 26, 0);
      this.boxesManager6.resolveBoxesManager(-1.0F, 0.0F, -3.0F, 3, 5, 3);
      this.boxesManager6.applyFloat(1.0F, (float)19, 1.0F);
      this.boxesManager = new BoxesManager(this, 24, 13);
      this.boxesManager.resolveBoxesManager(0.0F, 0.0F, -3.0F, 1, 4, 6);
      this.boxesManager.applyFloat(-4.0F, (float)13, 0.0F);
      this.boxesManager5 = new BoxesManager(this, 24, 13);
      this.boxesManager5.resolveBoxesManager(-1.0F, 0.0F, -3.0F, 1, 4, 6);
      this.boxesManager5.applyFloat(4.0F, (float)13, 0.0F);
   }
}
