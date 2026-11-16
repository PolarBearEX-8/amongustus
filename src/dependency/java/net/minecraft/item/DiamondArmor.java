package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class DiamondArmor extends PassiveFloatEntry {
   public BoxesManager boxesManager6;
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager11;
   public BoxesManager boxesManager5;
   public BoxesManager boxesManager7;
   public BoxesManager boxesManager8;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager9;
   public BoxesManager boxesManager10;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager2;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager6.applyFloat3(posZ);
      this.boxesManager4.applyFloat3(posZ);
      this.boxesManager11.applyFloat3(posZ);
      this.boxesManager5.applyFloat3(posZ);
      this.boxesManager7.applyFloat3(posZ);
      this.boxesManager8.applyFloat3(posZ);
      this.boxesManager.applyFloat3(posZ);
      this.boxesManager9.applyFloat3(posZ);
      this.boxesManager10.applyFloat3(posZ);
      this.boxesManager3.applyFloat3(posZ);
      this.boxesManager2.applyFloat3(posZ);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      this.boxesManager6.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager6.factor = posY / (180.0F / (float)Math.PI);
      float factor = (float) (Math.PI / 4);
      this.boxesManager5.factor8 = -factor;
      this.boxesManager7.factor8 = factor;
      this.boxesManager8.factor8 = -factor * 0.74F;
      this.boxesManager.factor8 = factor * 0.74F;
      this.boxesManager9.factor8 = -factor * 0.74F;
      this.boxesManager10.factor8 = factor * 0.74F;
      this.boxesManager3.factor8 = -factor;
      this.boxesManager2.factor8 = factor;
      float factor2 = -0.0F;
      float factor3 = (float) (Math.PI / 8);
      this.boxesManager5.factor9 = factor3 * 2.0F + factor2;
      this.boxesManager7.factor9 = -factor3 * 2.0F - factor2;
      this.boxesManager8.factor9 = factor3 * 1.0F + factor2;
      this.boxesManager.factor9 = -factor3 * 1.0F - factor2;
      this.boxesManager9.factor9 = -factor3 * 1.0F + factor2;
      this.boxesManager10.factor9 = factor3 * 1.0F - factor2;
      this.boxesManager3.factor9 = -factor3 * 2.0F + factor2;
      this.boxesManager2.factor9 = factor3 * 2.0F - factor2;
      float factor4 = -(MathHelper.cos(posX * 0.6662F * 2.0F + 0.0F) * 0.4F) * posX2;
      float factor5 = -(MathHelper.cos(posX * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * posX2;
      float factor6 = -(MathHelper.cos(posX * 0.6662F * 2.0F + (float) (Math.PI / 2)) * 0.4F) * posX2;
      float factor7 = -(MathHelper.cos(posX * 0.6662F * 2.0F + (float) (Math.PI * 3.0 / 2.0)) * 0.4F) * posX2;
      float factor8 = Math.abs(MathHelper.abs(posX * 0.6662F + 0.0F) * 0.4F) * posX2;
      float factor9 = Math.abs(MathHelper.abs(posX * 0.6662F + (float) Math.PI) * 0.4F) * posX2;
      float factor10 = Math.abs(MathHelper.abs(posX * 0.6662F + (float) (Math.PI / 2)) * 0.4F) * posX2;
      float factor11 = Math.abs(MathHelper.abs(posX * 0.6662F + (float) (Math.PI * 3.0 / 2.0)) * 0.4F) * posX2;
      this.boxesManager5.factor9 += factor4;
      this.boxesManager7.factor9 += -factor4;
      this.boxesManager8.factor9 += factor5;
      this.boxesManager.factor9 += -factor5;
      this.boxesManager9.factor9 += factor6;
      this.boxesManager10.factor9 += -factor6;
      this.boxesManager3.factor9 += factor7;
      this.boxesManager2.factor9 += -factor7;
      this.boxesManager5.factor8 += factor8;
      this.boxesManager7.factor8 += -factor8;
      this.boxesManager8.factor8 += factor9;
      this.boxesManager.factor8 += -factor9;
      this.boxesManager9.factor8 += factor10;
      this.boxesManager10.factor8 += -factor10;
      this.boxesManager3.factor8 += factor11;
      this.boxesManager2.factor8 += -factor11;
   }

   public DiamondArmor() {
      float factor = 0.0F;
      this.boxesManager6 = new BoxesManager(this, 32, 4);
      this.boxesManager6.applyFloatByFloat(-4.0F, -4.0F, -8.0F, 8, 8, 8, factor);
      this.boxesManager6.applyFloat(0.0F, (float)15, -3.0F);
      this.boxesManager4 = new BoxesManager(this, 0, 0);
      this.boxesManager4.applyFloatByFloat(-3.0F, -3.0F, -3.0F, 6, 6, 6, factor);
      this.boxesManager4.applyFloat(0.0F, (float)15, 0.0F);
      this.boxesManager11 = new BoxesManager(this, 0, 12);
      this.boxesManager11.applyFloatByFloat(-5.0F, -4.0F, -6.0F, 10, 8, 12, factor);
      this.boxesManager11.applyFloat(0.0F, (float)15, 9.0F);
      this.boxesManager5 = new BoxesManager(this, 18, 0);
      this.boxesManager5.applyFloatByFloat(-15.0F, -1.0F, -1.0F, 16, 2, 2, factor);
      this.boxesManager5.applyFloat(-4.0F, (float)15, 2.0F);
      this.boxesManager7 = new BoxesManager(this, 18, 0);
      this.boxesManager7.applyFloatByFloat(-1.0F, -1.0F, -1.0F, 16, 2, 2, factor);
      this.boxesManager7.applyFloat(4.0F, (float)15, 2.0F);
      this.boxesManager8 = new BoxesManager(this, 18, 0);
      this.boxesManager8.applyFloatByFloat(-15.0F, -1.0F, -1.0F, 16, 2, 2, factor);
      this.boxesManager8.applyFloat(-4.0F, (float)15, 1.0F);
      this.boxesManager = new BoxesManager(this, 18, 0);
      this.boxesManager.applyFloatByFloat(-1.0F, -1.0F, -1.0F, 16, 2, 2, factor);
      this.boxesManager.applyFloat(4.0F, (float)15, 1.0F);
      this.boxesManager9 = new BoxesManager(this, 18, 0);
      this.boxesManager9.applyFloatByFloat(-15.0F, -1.0F, -1.0F, 16, 2, 2, factor);
      this.boxesManager9.applyFloat(-4.0F, (float)15, 0.0F);
      this.boxesManager10 = new BoxesManager(this, 18, 0);
      this.boxesManager10.applyFloatByFloat(-1.0F, -1.0F, -1.0F, 16, 2, 2, factor);
      this.boxesManager10.applyFloat(4.0F, (float)15, 0.0F);
      this.boxesManager3 = new BoxesManager(this, 18, 0);
      this.boxesManager3.applyFloatByFloat(-15.0F, -1.0F, -1.0F, 16, 2, 2, factor);
      this.boxesManager3.applyFloat(-4.0F, (float)15, -1.0F);
      this.boxesManager2 = new BoxesManager(this, 18, 0);
      this.boxesManager2.applyFloatByFloat(-1.0F, -1.0F, -1.0F, 16, 2, 2, factor);
      this.boxesManager2.applyFloat(4.0F, (float)15, -1.0F);
   }
}
