package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class DiamondSpear extends PassiveFloatEntry {
   public BoxesManager boxesManager;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager5;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager.applyFloat3(posZ);
      this.boxesManager3.applyFloat3(posZ);
      this.boxesManager2.applyFloat3(posZ);
      this.boxesManager4.applyFloat3(posZ);
      this.boxesManager5.applyFloat3(posZ);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager2.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager2.factor = posY / (180.0F / (float)Math.PI);
      this.boxesManager.factor9 = posX4 / (180.0F / (float)Math.PI) * 0.25F;
      float factor = MathHelper.abs(this.boxesManager.factor9);
      float factor2 = MathHelper.cos(this.boxesManager.factor9);
      this.boxesManager4.factor8 = 1.0F;
      this.boxesManager5.factor8 = -1.0F;
      this.boxesManager4.factor9 = 0.0F + this.boxesManager.factor9;
      this.boxesManager5.factor9 = (float) Math.PI + this.boxesManager.factor9;
      this.boxesManager4.factor5 = factor2 * 5.0F;
      this.boxesManager4.factor10 = -factor * 5.0F;
      this.boxesManager5.factor5 = -factor2 * 5.0F;
      this.boxesManager5.factor10 = factor * 5.0F;
   }

   public DiamondSpear() {
      float factor = 4.0F;
      float factor2 = 0.0F;
      this.boxesManager2 = new BoxesManager(this, 0, 0).resolveBoxesManager(64, 64);
      this.boxesManager2.applyFloatByFloat(-4.0F, -8.0F, -4.0F, 8, 8, 8, factor2 - 0.5F);
      this.boxesManager2.applyFloat(0.0F, 0.0F + factor, 0.0F);
      this.boxesManager4 = new BoxesManager(this, 32, 0).resolveBoxesManager(64, 64);
      this.boxesManager4.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 12, 2, 2, factor2 - 0.5F);
      this.boxesManager4.applyFloat(0.0F, 0.0F + factor + 9.0F - 7.0F, 0.0F);
      this.boxesManager5 = new BoxesManager(this, 32, 0).resolveBoxesManager(64, 64);
      this.boxesManager5.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 12, 2, 2, factor2 - 0.5F);
      this.boxesManager5.applyFloat(0.0F, 0.0F + factor + 9.0F - 7.0F, 0.0F);
      this.boxesManager = new BoxesManager(this, 0, 16).resolveBoxesManager(64, 64);
      this.boxesManager.applyFloatByFloat(-5.0F, -10.0F, -5.0F, 10, 10, 10, factor2 - 0.5F);
      this.boxesManager.applyFloat(0.0F, 0.0F + factor + 9.0F, 0.0F);
      this.boxesManager3 = new BoxesManager(this, 0, 36).resolveBoxesManager(64, 64);
      this.boxesManager3.applyFloatByFloat(-6.0F, -12.0F, -6.0F, 12, 12, 12, factor2 - 0.5F);
      this.boxesManager3.applyFloat(0.0F, 0.0F + factor + 20.0F, 0.0F);
   }
}
