package baritone.api.event.events;

import baritone.api.utils.Rotation;

public final class RotationMoveEvent {
   private final RotationMoveEvent.Type type;
   private final Rotation original;
   private float yaw;
   private float pitch;

   public Rotation getOriginal() {
      return this.original;
   }

   public float getPitch() {
      return this.pitch;
   }

   public RotationMoveEvent(RotationMoveEvent.Type type2, float f, float f2) {
      this.type = type2;
      this.original = new Rotation(f, f2);
      this.yaw = f;
      this.pitch = f2;
   }

   public void setYaw(float f) {
      this.yaw = f;
   }

   public void setPitch(float f) {
      this.pitch = f;
   }

   public RotationMoveEvent.Type getType() {
      return this.type;
   }

   public float getYaw() {
      return this.yaw;
   }


   public enum Type {
      MOTION_UPDATE,
      JUMP;
   }
}
