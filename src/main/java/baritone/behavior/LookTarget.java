package baritone.behavior;

import baritone.api.utils.Rotation;

public class LookTarget {
   public final Rotation rotation;
   public final LookTargetMode mode;

   public LookTarget(Rotation rotation2, LookTargetMode lookTargetMode) {
      this.rotation = rotation2;
      this.mode = lookTargetMode;
   }
}
