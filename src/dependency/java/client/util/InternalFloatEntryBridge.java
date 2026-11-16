package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class InternalFloatEntryBridge extends FloatEntryRegistry {
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

   public InternalFloatEntryBridge(float factor, float factor2, int count, int count2) {
      super(factor, factor2, count, count2);
   }

   public InternalFloatEntryBridge(float factor, boolean flag) {
      super(factor, 0.0F, 64, flag ? 32 : 64);
   }

   public InternalFloatEntryBridge() {
      this(0.0F, false);
   }
}
