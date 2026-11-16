package net.minecraft.item;

import client.util.BoxesManager;
import net.minecraft.entity.Entity;

public class ItemEntryRegistry extends DiamondGem {
   public final BoxesManager boxesManager3 = new BoxesManager(this, 32, 0);

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      super.applyEntity(entity, posX, posX2, posX3, posX4, posY, posZ, flag);
      this.boxesManager3.applyFloat3(posZ);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager3.factor9 = this.boxesManager.factor9;
      this.boxesManager3.factor = this.boxesManager.factor;
   }

   public ItemEntryRegistry() {
      super(0, 0, 64, 64);
      this.boxesManager3.applyFloatByFloat(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F);
      this.boxesManager3.applyFloat(0.0F, 0.0F, 0.0F);
   }
}
