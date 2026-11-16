package client.util;

public class HitBoxObserver extends RotationPool {
   public final AuxiliaryUUIDEntry ownerEntity;
   public final double moveSpeed;
   public final float maxDistance;

   public HitBoxObserver(AuxiliaryUUIDEntry ownerEntity, double moveSpeed, float maxDistance) {
      this.ownerEntity = ownerEntity;
      this.moveSpeed = moveSpeed;
      this.maxDistance = maxDistance;
   }

   @Override
   public boolean isActive() {
      return false;
   }
}
