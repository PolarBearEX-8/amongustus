package client.util;

import net.minecraft.entity.EntityMob;

public class SupplementalClientManagerAdapter extends RotationPool {
   public EntityMob entityMob;

   @Override
   public void updateState2() {
      if (this.entityMob.getRandom().nextFloat() < 0.8F) {
         this.entityMob.getPrimaryItemEntry().updateState();
      }
   }

   @Override
   public boolean isActive() {
      return this.entityMob.isInWater() || this.entityMob.isWet();
   }

   public SupplementalClientManagerAdapter(EntityMob entityMob2) {
      this.entityMob = entityMob2;
      this.setCount(4);
      ((PathfindManagerAdapter)entityMob2.getPathfindManager()).setEnabled2(true);
   }
}
