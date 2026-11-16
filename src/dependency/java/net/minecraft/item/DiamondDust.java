package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class DiamondDust extends PassiveFloatEntry {
   public BoxesManager boxesManager = new BoxesManager(this).resolveBoxesManagerByIndex(0, 0).resolveBoxesManager(-6.0F, -5.0F, 0.0F, 6, 10, 0);
   public BoxesManager boxesManager6 = new BoxesManager(this).resolveBoxesManagerByIndex(16, 0).resolveBoxesManager(0.0F, -5.0F, 0.0F, 6, 10, 0);
   public BoxesManager boxesManager2 = new BoxesManager(this).resolveBoxesManagerByIndex(0, 10).resolveBoxesManager(0.0F, -4.0F, -0.99F, 5, 8, 1);
   public BoxesManager boxesManager4 = new BoxesManager(this).resolveBoxesManagerByIndex(12, 10).resolveBoxesManager(0.0F, -4.0F, -0.01F, 5, 8, 1);
   public BoxesManager boxesManager3 = new BoxesManager(this).resolveBoxesManagerByIndex(24, 10).resolveBoxesManager(0.0F, -4.0F, 0.0F, 5, 8, 0);
   public BoxesManager boxesManager7 = new BoxesManager(this).resolveBoxesManagerByIndex(24, 10).resolveBoxesManager(0.0F, -4.0F, 0.0F, 5, 8, 0);
   public BoxesManager boxesManager5 = new BoxesManager(this).resolveBoxesManagerByIndex(12, 0).resolveBoxesManager(-1.0F, -5.0F, 0.0F, 2, 10, 0);

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager.applyFloat3(posZ);
      this.boxesManager6.applyFloat3(posZ);
      this.boxesManager5.applyFloat3(posZ);
      this.boxesManager2.applyFloat3(posZ);
      this.boxesManager4.applyFloat3(posZ);
      this.boxesManager3.applyFloat3(posZ);
      this.boxesManager7.applyFloat3(posZ);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      float factor = (MathHelper.abs(posX * 0.02F) * 0.1F + 1.25F) * posX4;
      this.boxesManager.factor9 = (float) Math.PI + factor;
      this.boxesManager6.factor9 = -factor;
      this.boxesManager2.factor9 = factor;
      this.boxesManager4.factor9 = -factor;
      this.boxesManager3.factor9 = factor - factor * 2.0F * posX2;
      this.boxesManager7.factor9 = factor - factor * 2.0F * posX3;
      this.boxesManager2.factor5 = MathHelper.abs(factor);
      this.boxesManager4.factor5 = MathHelper.abs(factor);
      this.boxesManager3.factor5 = MathHelper.abs(factor);
      this.boxesManager7.factor5 = MathHelper.abs(factor);
   }

   public DiamondDust() {
      this.boxesManager.applyFloat(0.0F, 0.0F, -1.0F);
      this.boxesManager6.applyFloat(0.0F, 0.0F, 1.0F);
      this.boxesManager5.factor9 = (float) (Math.PI / 2);
   }
}
