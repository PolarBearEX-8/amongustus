package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class DiamondAmulet extends PassiveFloatEntry {
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager6;
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager5;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager2.applyFloat3(posZ);
      this.boxesManager3.applyFloat3(posZ);
      this.boxesManager6.applyFloat3(posZ);
      this.boxesManager4.applyFloat3(posZ);
      this.boxesManager.applyFloat3(posZ);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      this.boxesManager2.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager2.factor = posY / (180.0F / (float)Math.PI);
      this.boxesManager.factor2 = 3.0F;
      this.boxesManager.factor10 = -1.0F;
      this.boxesManager.factor = -0.75F;
      this.boxesManager6.factor = MathHelper.cos(posX * 0.6662F) * 1.4F * posX2 * 0.5F;
      this.boxesManager4.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.4F * posX2 * 0.5F;
      this.boxesManager6.factor9 = 0.0F;
      this.boxesManager4.factor9 = 0.0F;
   }

   public DiamondAmulet(float factor) {
      this(factor, 0.0F, 64, 64);
   }

   public DiamondAmulet(float factor, float factor2, int count, int count2) {
      this.boxesManager2 = new BoxesManager(this).resolveBoxesManager(count, count2);
      this.boxesManager2.applyFloat(0.0F, 0.0F + factor2, 0.0F);
      this.boxesManager2.resolveBoxesManagerByIndex(0, 0).applyFloatByFloat(-4.0F, -10.0F, -4.0F, 8, 10, 8, factor);
      this.boxesManager5 = new BoxesManager(this).resolveBoxesManager(count, count2);
      this.boxesManager5.applyFloat(0.0F, factor2 - 2.0F, 0.0F);
      this.boxesManager5.resolveBoxesManagerByIndex(24, 0).applyFloatByFloat(-1.0F, -1.0F, -6.0F, 2, 4, 2, factor);
      this.boxesManager2.applyBoxesManager(this.boxesManager5);
      this.boxesManager3 = new BoxesManager(this).resolveBoxesManager(count, count2);
      this.boxesManager3.applyFloat(0.0F, 0.0F + factor2, 0.0F);
      this.boxesManager3.resolveBoxesManagerByIndex(16, 20).applyFloatByFloat(-4.0F, 0.0F, -3.0F, 8, 12, 6, factor);
      this.boxesManager3.resolveBoxesManagerByIndex(0, 38).applyFloatByFloat(-4.0F, 0.0F, -3.0F, 8, 18, 6, factor + 0.5F);
      this.boxesManager = new BoxesManager(this).resolveBoxesManager(count, count2);
      this.boxesManager.applyFloat(0.0F, 0.0F + factor2 + 2.0F, 0.0F);
      this.boxesManager.resolveBoxesManagerByIndex(44, 22).applyFloatByFloat(-8.0F, -2.0F, -2.0F, 4, 8, 4, factor);
      this.boxesManager.resolveBoxesManagerByIndex(44, 22).applyFloatByFloat(4.0F, -2.0F, -2.0F, 4, 8, 4, factor);
      this.boxesManager.resolveBoxesManagerByIndex(40, 38).applyFloatByFloat(-4.0F, 2.0F, -2.0F, 8, 4, 4, factor);
      this.boxesManager6 = new BoxesManager(this, 0, 22).resolveBoxesManager(count, count2);
      this.boxesManager6.applyFloat(-2.0F, 12.0F + factor2, 0.0F);
      this.boxesManager6.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 12, 4, factor);
      this.boxesManager4 = new BoxesManager(this, 0, 22).resolveBoxesManager(count, count2);
      this.boxesManager4.enabled = true;
      this.boxesManager4.applyFloat(2.0F, 12.0F + factor2, 0.0F);
      this.boxesManager4.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 12, 4, factor);
   }
}
