package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class AimEstimator extends FloatEntryRegistry {
   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      float factor = MathHelper.abs(this.factor * (float) Math.PI);
      float factor2 = MathHelper.abs((1.0F - (1.0F - this.factor) * (1.0F - this.factor)) * (float) Math.PI);
      this.boxesManager2.factor8 = 0.0F;
      this.boxesManager.factor8 = 0.0F;
      this.boxesManager2.factor9 = -(0.1F - factor * 0.6F);
      this.boxesManager.factor9 = 0.1F - factor * 0.6F;
      this.boxesManager2.factor = (float) (-Math.PI / 2);
      this.boxesManager.factor = (float) (-Math.PI / 2);
      this.boxesManager2.factor -= factor * 1.2F - factor2 * 0.4F;
      this.boxesManager.factor -= factor * 1.2F - factor2 * 0.4F;
      this.boxesManager2.factor8 = this.boxesManager2.factor8 + MathHelper.cos(posX3 * 0.09F) * 0.05F + 0.05F;
      this.boxesManager.factor8 = this.boxesManager.factor8 - (MathHelper.cos(posX3 * 0.09F) * 0.05F + 0.05F);
      this.boxesManager2.factor = this.boxesManager2.factor + MathHelper.abs(posX3 * 0.067F) * 0.05F;
      this.boxesManager.factor = this.boxesManager.factor - MathHelper.abs(posX3 * 0.067F) * 0.05F;
   }

   public AimEstimator() {
      this(0.0F, 0.0F, false);
   }

   public AimEstimator(float factor, float factor2, boolean flag) {
      super(factor, 0.0F, 64, flag ? 32 : 64);
      if (flag) {
         this.boxesManager3 = new BoxesManager(this, 0, 0);
         this.boxesManager3.applyFloatByFloat(-4.0F, -10.0F, -4.0F, 8, 8, 8, factor);
         this.boxesManager3.applyFloat(0.0F, 0.0F + factor2, 0.0F);
      } else {
         this.boxesManager3 = new BoxesManager(this);
         this.boxesManager3.applyFloat(0.0F, 0.0F + factor2, 0.0F);
         this.boxesManager3.resolveBoxesManagerByIndex(0, 32).applyFloatByFloat(-4.0F, -10.0F, -4.0F, 8, 10, 8, factor);
         this.boxesManager3.resolveBoxesManagerByIndex(24, 32).applyFloatByFloat(-1.0F, -3.0F, -6.0F, 2, 4, 2, factor);
      }
   }
}
