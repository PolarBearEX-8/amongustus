package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;

public class DiamondBlade extends PassiveFloatEntry {
   public BoxesManager boxesManager;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager.applyFloat3(posZ);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager.factor = posY / (180.0F / (float)Math.PI);
   }

   public DiamondBlade() {
      this(0, 0, 32, 32);
   }

   public DiamondBlade(int count, int count2, int count3, int count4) {
      this.count2 = count3;
      this.count = count4;
      this.boxesManager = new BoxesManager(this, count, count2);
      this.boxesManager.applyFloatByFloat(-3.0F, -6.0F, -3.0F, 6, 8, 6, 0.0F);
      this.boxesManager.applyFloat(0.0F, 0.0F, 0.0F);
   }
}
