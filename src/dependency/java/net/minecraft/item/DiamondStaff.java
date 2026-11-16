package net.minecraft.item;

import client.util.BoxesManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class DiamondStaff extends DiamondAmulet {
   public boolean enabled3;
   public BoxesManager boxesManager8 = new BoxesManager(this).resolveBoxesManager(64, 128);
   public BoxesManager boxesManager12;

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager5.factor4 = this.boxesManager5.factor6 = this.boxesManager5.factor3 = 0.0F;
      float factor = 0.01F * (float)(entity.getEntityId() % 10);
      this.boxesManager5.factor = MathHelper.abs((float)entity.ticksExisted * factor) * 4.5F * (float) Math.PI / 180.0F;
      this.boxesManager5.factor9 = 0.0F;
      this.boxesManager5.factor8 = MathHelper.cos((float)entity.ticksExisted * factor) * 2.5F * (float) Math.PI / 180.0F;
      if (this.enabled3) {
         this.boxesManager5.factor = -0.9F;
         this.boxesManager5.factor3 = -0.09375F;
         this.boxesManager5.factor6 = 0.1875F;
      }
   }

   public DiamondStaff(float factor) {
      super(factor, 0.0F, 64, 128);
      this.boxesManager8.applyFloat(0.0F, -2.0F, 0.0F);
      this.boxesManager8.resolveBoxesManagerByIndex(0, 0).applyFloatByFloat(0.0F, 3.0F, -6.75F, 1, 1, 1, -0.25F);
      this.boxesManager5.applyBoxesManager(this.boxesManager8);
      this.boxesManager12 = new BoxesManager(this).resolveBoxesManager(64, 128);
      this.boxesManager12.applyFloat(-5.0F, -10.03125F, -5.0F);
      this.boxesManager12.resolveBoxesManagerByIndex(0, 64).resolveBoxesManager(0.0F, 0.0F, 0.0F, 10, 2, 10);
      this.boxesManager2.applyBoxesManager(this.boxesManager12);
      BoxesManager boxesManager = new BoxesManager(this).resolveBoxesManager(64, 128);
      boxesManager.applyFloat(1.75F, -4.0F, 2.0F);
      boxesManager.resolveBoxesManagerByIndex(0, 76).resolveBoxesManager(0.0F, 0.0F, 0.0F, 7, 4, 7);
      boxesManager.factor = -0.05235988F;
      boxesManager.factor8 = 0.02617994F;
      this.boxesManager12.applyBoxesManager(boxesManager);
      BoxesManager boxesManager2 = new BoxesManager(this).resolveBoxesManager(64, 128);
      boxesManager2.applyFloat(1.75F, -4.0F, 2.0F);
      boxesManager2.resolveBoxesManagerByIndex(0, 87).resolveBoxesManager(0.0F, 0.0F, 0.0F, 4, 4, 4);
      boxesManager2.factor = -0.10471976F;
      boxesManager2.factor8 = 0.05235988F;
      boxesManager.applyBoxesManager(boxesManager2);
      BoxesManager boxesManager3 = new BoxesManager(this).resolveBoxesManager(64, 128);
      boxesManager3.applyFloat(1.75F, -2.0F, 2.0F);
      boxesManager3.resolveBoxesManagerByIndex(0, 95).applyFloatByFloat(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
      boxesManager3.factor = (float) (-Math.PI / 15);
      boxesManager3.factor8 = 0.10471976F;
      boxesManager2.applyBoxesManager(boxesManager3);
   }
}
