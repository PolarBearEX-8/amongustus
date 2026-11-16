package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;

public class DiamondRod extends PassiveFloatEntry {
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager4;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager3.applyFloat3(posZ);
      if (this.boxesManager2 != null) {
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager4.applyFloat3(posZ);
      }
   }

   public DiamondRod(int count) {
      this.boxesManager3 = new BoxesManager(this, 0, count);
      this.boxesManager3.resolveBoxesManager(-4.0F, 16.0F, -4.0F, 8, 8, 8);
      if (count > 0) {
         this.boxesManager3 = new BoxesManager(this, 0, count);
         this.boxesManager3.resolveBoxesManager(-3.0F, 17.0F, -3.0F, 6, 6, 6);
         this.boxesManager2 = new BoxesManager(this, 32, 0);
         this.boxesManager2.resolveBoxesManager(-3.25F, 18.0F, -3.5F, 2, 2, 2);
         this.boxesManager = new BoxesManager(this, 32, 4);
         this.boxesManager.resolveBoxesManager(1.25F, 18.0F, -3.5F, 2, 2, 2);
         this.boxesManager4 = new BoxesManager(this, 32, 8);
         this.boxesManager4.resolveBoxesManager(0.0F, 21.0F, -3.5F, 1, 1, 1);
      }
   }
}
