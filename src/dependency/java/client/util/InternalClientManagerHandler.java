package client.util;

import net.minecraft.entity.EntitySlime;

public class InternalClientManagerHandler extends RotationPool {
   public EntitySlime entitySlime;

   @Override
   public void updateState2() {
      ((VirtualFloatEntry)this.entitySlime.getEntityPillagerAdapter()).applyDouble(1.0);
   }

   public InternalClientManagerHandler(EntitySlime entitySlime2) {
      this.entitySlime = entitySlime2;
      this.setCount(5);
   }

   @Override
   public boolean isActive() {
      return true;
   }
}
