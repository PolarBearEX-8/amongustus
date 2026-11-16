package client.util;

import net.minecraft.entity.EntityLivingBase;

public class HitBoxCoordinator extends RotationPool {
   public HitBoxAdapter hitBoxAdapter;
   public boolean enabled;

   @Override
   public boolean isActive() {
      if (!this.hitBoxAdapter.isActive32()) {
         return false;
      } else if (this.hitBoxAdapter.isInWater()) {
         return false;
      } else if (!this.hitBoxAdapter.onGround) {
         return false;
      } else {
         EntityLivingBase entityLivingBase = this.hitBoxAdapter.getEntityLivingBase7();
         return entityLivingBase == null ? true : (this.hitBoxAdapter.getCoordinate(entityLivingBase) < 144.0 && entityLivingBase.getTargetEntity() != null ? false : this.enabled);
      }
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public HitBoxCoordinator(HitBoxAdapter hitBoxAdapter2) {
      this.hitBoxAdapter = hitBoxAdapter2;
      this.setCount(5);
   }

   @Override
   public void updateState() {
      this.hitBoxAdapter.setEnabled8(false);
   }

   @Override
   public void updateState3() {
      this.hitBoxAdapter.getPathfindManager().updateState4();
      this.hitBoxAdapter.setEnabled8(true);
   }
}
