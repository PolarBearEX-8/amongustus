package net.minecraft.item;

import client.util.BoxesManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySheep;

public class ItemEntryHandler extends DiamondRing {
   public float factor4;

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager6.factor = this.factor4;
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      super.applyEntityLivingBase(livingEntity, posX, posY, posZ);
      this.boxesManager6.factor2 = 6.0F + ((EntitySheep)livingEntity).computeFactor3(posZ) * 9.0F;
      this.factor4 = ((EntitySheep)livingEntity).computeFactor4(posZ);
   }

   public ItemEntryHandler() {
      super(12, 0.0F);
      this.boxesManager6 = new BoxesManager(this, 0, 0);
      this.boxesManager6.applyFloatByFloat(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
      this.boxesManager6.applyFloat(0.0F, 6.0F, -8.0F);
      this.boxesManager3 = new BoxesManager(this, 28, 8);
      this.boxesManager3.applyFloatByFloat(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
      this.boxesManager3.applyFloat(0.0F, 5.0F, 2.0F);
      float factor = 0.5F;
      this.boxesManager5 = new BoxesManager(this, 0, 16);
      this.boxesManager5.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 6, 4, factor);
      this.boxesManager5.applyFloat(-3.0F, 12.0F, 7.0F);
      this.boxesManager2 = new BoxesManager(this, 0, 16);
      this.boxesManager2.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 6, 4, factor);
      this.boxesManager2.applyFloat(3.0F, 12.0F, 7.0F);
      this.boxesManager = new BoxesManager(this, 0, 16);
      this.boxesManager.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 6, 4, factor);
      this.boxesManager.applyFloat(-3.0F, 12.0F, -5.0F);
      this.boxesManager4 = new BoxesManager(this, 0, 16);
      this.boxesManager4.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 6, 4, factor);
      this.boxesManager4.applyFloat(3.0F, 12.0F, -5.0F);
   }
}
