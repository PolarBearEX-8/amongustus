package net.minecraft.item;

import client.util.RotationPool;
import net.minecraft.entity.EntityMob;

public class ComplexItemEntry extends RotationPool {
   public EntityMob entityMob;
   public double value;
   public double value2;
   public int count2;

   @Override
   public void updateState3() {
      double offset = (Math.PI * 2) * this.entityMob.getRandom().nextDouble();
      this.value = Math.cos(offset);
      this.value2 = Math.sin(offset);
      this.count2 = 20 + this.entityMob.getRandom().nextInt(20);
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.count2 >= 0;
   }

   @Override
   public void updateState2() {
      this.count2--;
      this.entityMob
         .getEntityRavagerAdapter()
         .applyDouble(
            this.entityMob.posX + this.value,
            this.entityMob.posY + (double)this.entityMob.getHeight2(),
            this.entityMob.posZ + this.value2,
            10.0F,
            (float)this.entityMob.getCount8()
         );
   }

   @Override
   public boolean isActive() {
      return this.entityMob.getRandom().nextFloat() < 0.02F;
   }

   public ComplexItemEntry(EntityMob entityMob2) {
      this.entityMob = entityMob2;
      this.setCount(3);
   }
}
