package baritone.pathing.movement;

import baritone.api.utils.Rotation;
import java.util.Optional;

public class MovementTarget {
   public Rotation rotation;
   private boolean forceRotations;

   public boolean isForceRotations() {
      return this.forceRotations;
   }

   public MovementTarget() {
      this(null, false);
   }

   public final Optional getRotation() {
      return Optional.ofNullable(this.rotation);
   }

   public MovementTarget(Rotation rotation2, boolean flag) {
      this.rotation = rotation2;
      this.forceRotations = flag;
   }
}
