package net.augustus.cosmetics.model;

public class GeoBoneSnapshot {
   public float positionOffsetX;
   public float rotationValueZ;
   public float rotationValueY;
   public float scaleValueY;
   public float positionOffsetZ;
   public float positionOffsetY;
   public float scaleValueZ;
   public boolean isCurrentlyRunningPositionAnimation;
   public String name;
   public float rotationValueX;
   public boolean isCurrentlyRunningScaleAnimation;
   private IGeoBone modelRenderer;
   public boolean isCurrentlyRunningRotationAnimation;
   public float mostRecentResetScaleTick;
   public float mostRecentResetPositionTick;
   public float mostRecentResetRotationTick = 0.0F;
   public float scaleValueX;

   public GeoBoneSnapshot(IGeoBone geoBone) {
      this.mostRecentResetPositionTick = 0.0F;
      this.mostRecentResetScaleTick = 0.0F;
      this.isCurrentlyRunningRotationAnimation = true;
      this.isCurrentlyRunningPositionAnimation = true;
      this.isCurrentlyRunningScaleAnimation = true;
      this.rotationValueX = geoBone.getRotationX();
      this.rotationValueY = geoBone.getRotationY();
      this.rotationValueZ = geoBone.getRotationZ();
      this.positionOffsetX = geoBone.getPositionX();
      this.positionOffsetY = geoBone.getPositionY();
      this.positionOffsetZ = geoBone.getPositionZ();
      this.scaleValueX = geoBone.getScaleX();
      this.scaleValueY = geoBone.getScaleY();
      this.scaleValueZ = geoBone.getScaleZ();
      this.modelRenderer = geoBone;
      this.name = geoBone.getName();
   }

   public GeoBoneSnapshot(GeoBoneSnapshot geoBoneSnapshot) {
      this.mostRecentResetPositionTick = 0.0F;
      this.mostRecentResetScaleTick = 0.0F;
      this.isCurrentlyRunningRotationAnimation = true;
      this.isCurrentlyRunningPositionAnimation = true;
      this.isCurrentlyRunningScaleAnimation = true;
      this.scaleValueX = geoBoneSnapshot.scaleValueX;
      this.scaleValueY = geoBoneSnapshot.scaleValueY;
      this.scaleValueZ = geoBoneSnapshot.scaleValueZ;
      this.positionOffsetX = geoBoneSnapshot.positionOffsetX;
      this.positionOffsetY = geoBoneSnapshot.positionOffsetY;
      this.positionOffsetZ = geoBoneSnapshot.positionOffsetZ;
      this.rotationValueX = geoBoneSnapshot.rotationValueX;
      this.rotationValueY = geoBoneSnapshot.rotationValueY;
      this.rotationValueZ = geoBoneSnapshot.rotationValueZ;
      this.modelRenderer = geoBoneSnapshot.modelRenderer;
      this.name = geoBoneSnapshot.name;
   }

   @Override
   public int hashCode() {
      return this.name.hashCode();
   }

   public GeoBoneSnapshot(IGeoBone geoBone, boolean flag) {
      this.mostRecentResetPositionTick = 0.0F;
      this.mostRecentResetScaleTick = 0.0F;
      this.isCurrentlyRunningRotationAnimation = true;
      this.isCurrentlyRunningPositionAnimation = true;
      this.isCurrentlyRunningScaleAnimation = true;
      if (flag) {
         this.rotationValueX = 0.0F;
         this.rotationValueY = 0.0F;
         this.rotationValueZ = 0.0F;
      }

      this.rotationValueX = geoBone.getRotationX();
      this.rotationValueY = geoBone.getRotationY();
      this.rotationValueZ = geoBone.getRotationZ();
      this.positionOffsetX = geoBone.getPositionX();
      this.positionOffsetY = geoBone.getPositionY();
      this.positionOffsetZ = geoBone.getPositionZ();
      this.scaleValueX = geoBone.getScaleX();
      this.scaleValueY = geoBone.getScaleY();
      this.scaleValueZ = geoBone.getScaleZ();
      this.modelRenderer = geoBone;
      this.name = geoBone.getName();
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GeoBoneSnapshot geoBoneSnapshot = (GeoBoneSnapshot)obj;
         return this.name.equals(geoBoneSnapshot.name);
      } else {
         return false;
      }
   }



}
