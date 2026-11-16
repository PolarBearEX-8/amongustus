package net.minecraft.item;

import client.setting.MovementDelayProperty;

public class IronIngot {
   public final MovementDelayProperty movementDelayProperty;
   public int count2;
   public int count3;
   public final int count;

   public int getCount3() {
      return this.count3;
   }

   public MovementDelayProperty getMovementDelayProperty() {
      return this.movementDelayProperty;
   }

   public int getCount2() {
      return this.count2;
   }

   public void applyIndex(int count4) {
      if (count4 < this.count2) {
         this.count2 = count4;
      } else if (count4 > this.count3) {
         this.count3 = count4;
      }
   }

   public int getCount() {
      return this.count;
   }

   public IronIngot(MovementDelayProperty movementDelayProperty2, int count4, int count5) {
      this.movementDelayProperty = movementDelayProperty2;
      this.count2 = count4;
      this.count3 = count4;
      this.count = count5;
   }
}
