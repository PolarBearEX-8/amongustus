package client.util;

import net.minecraft.entity.EntitySlime;

public class ManagedClientManagerAdapter extends RotationPool {
   public EntitySlime entitySlime;

   @Override
   public void updateState2() {
      if (this.entitySlime.getRandom().nextFloat() < 0.8F) {
         this.entitySlime.getPrimaryItemEntry().updateState();
      }

      ((VirtualFloatEntry)this.entitySlime.getEntityPillagerAdapter()).applyDouble(1.2);
   }

   @Override
   public boolean isActive() {
      return this.entitySlime.isInWater() || this.entitySlime.isWet();
   }

   public ManagedClientManagerAdapter(EntitySlime entitySlime2) {
      this.entitySlime = entitySlime2;
      this.setCount(5);
      ((PathfindManagerAdapter)entitySlime2.getPathfindManager()).setEnabled2(true);
   }
}
