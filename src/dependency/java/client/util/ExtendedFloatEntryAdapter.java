package client.util;

import net.minecraft.entity.Entity;

public class ExtendedFloatEntryAdapter extends FloatEntryRegistry {
   public boolean enabled8;
   public boolean enabled7;

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager3.enabled3 = true;
      float factor = -14.0F;
      this.boxesManager7.factor = 0.0F;
      this.boxesManager7.factor2 = factor;
      this.boxesManager7.factor10 = -0.0F;
      this.boxesManager5.factor -= 0.0F;
      this.boxesManager6.factor -= 0.0F;
      this.boxesManager2.factor = (float)((double)this.boxesManager2.factor * 0.5);
      this.boxesManager.factor = (float)((double)this.boxesManager.factor * 0.5);
      this.boxesManager5.factor = (float)((double)this.boxesManager5.factor * 0.5);
      this.boxesManager6.factor = (float)((double)this.boxesManager6.factor * 0.5);
      float factor2 = 0.4F;
      if (this.boxesManager2.factor > factor2) {
         this.boxesManager2.factor = factor2;
      }

      if (this.boxesManager.factor > factor2) {
         this.boxesManager.factor = factor2;
      }

      if (this.boxesManager2.factor < -factor2) {
         this.boxesManager2.factor = -factor2;
      }

      if (this.boxesManager.factor < -factor2) {
         this.boxesManager.factor = -factor2;
      }

      if (this.boxesManager5.factor > factor2) {
         this.boxesManager5.factor = factor2;
      }

      if (this.boxesManager6.factor > factor2) {
         this.boxesManager6.factor = factor2;
      }

      if (this.boxesManager5.factor < -factor2) {
         this.boxesManager5.factor = -factor2;
      }

      if (this.boxesManager6.factor < -factor2) {
         this.boxesManager6.factor = -factor2;
      }

      if (this.enabled8) {
         this.boxesManager2.factor = -0.5F;
         this.boxesManager.factor = -0.5F;
         this.boxesManager2.factor8 = 0.05F;
         this.boxesManager.factor8 = -0.05F;
      }

      this.boxesManager2.factor10 = 0.0F;
      this.boxesManager.factor10 = 0.0F;
      this.boxesManager5.factor10 = 0.0F;
      this.boxesManager6.factor10 = 0.0F;
      this.boxesManager5.factor2 = 9.0F + factor;
      this.boxesManager6.factor2 = 9.0F + factor;
      this.boxesManager3.factor10 = -0.0F;
      this.boxesManager3.factor2 = factor + 1.0F;
      this.boxesManager4.factor5 = this.boxesManager3.factor5;
      this.boxesManager4.factor2 = this.boxesManager3.factor2;
      this.boxesManager4.factor10 = this.boxesManager3.factor10;
      this.boxesManager4.factor = this.boxesManager3.factor;
      this.boxesManager4.factor9 = this.boxesManager3.factor9;
      this.boxesManager4.factor8 = this.boxesManager3.factor8;
      if (this.enabled7) {
         float factor3 = 1.0F;
         this.boxesManager3.factor2 -= factor3 * 5.0F;
      }
   }

   public ExtendedFloatEntryAdapter(float factor) {
      super(0.0F, -14.0F, 64, 32);
      float factor2 = -14.0F;
      this.boxesManager4 = new BoxesManager(this, 0, 16);
      this.boxesManager4.applyFloatByFloat(-4.0F, -8.0F, -4.0F, 8, 8, 8, factor - 0.5F);
      this.boxesManager4.applyFloat(0.0F, 0.0F + factor2, 0.0F);
      this.boxesManager7 = new BoxesManager(this, 32, 16);
      this.boxesManager7.applyFloatByFloat(-4.0F, 0.0F, -2.0F, 8, 12, 4, factor);
      this.boxesManager7.applyFloat(0.0F, 0.0F + factor2, 0.0F);
      this.boxesManager2 = new BoxesManager(this, 56, 0);
      this.boxesManager2.applyFloatByFloat(-1.0F, -2.0F, -1.0F, 2, 30, 2, factor);
      this.boxesManager2.applyFloat(-3.0F, 2.0F + factor2, 0.0F);
      this.boxesManager = new BoxesManager(this, 56, 0);
      this.boxesManager.enabled = true;
      this.boxesManager.applyFloatByFloat(-1.0F, -2.0F, -1.0F, 2, 30, 2, factor);
      this.boxesManager.applyFloat(5.0F, 2.0F + factor2, 0.0F);
      this.boxesManager5 = new BoxesManager(this, 56, 0);
      this.boxesManager5.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 30, 2, factor);
      this.boxesManager5.applyFloat(-2.0F, 12.0F + factor2, 0.0F);
      this.boxesManager6 = new BoxesManager(this, 56, 0);
      this.boxesManager6.enabled = true;
      this.boxesManager6.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 30, 2, factor);
      this.boxesManager6.applyFloat(2.0F, 12.0F + factor2, 0.0F);
   }
}
