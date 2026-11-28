package baritone.behavior;

import baritone.api.utils.IPlayerContext;
import baritone.api.utils.Rotation;

public final class AimProcessor extends AbstractAimProcessor {
   public AimProcessor(IPlayerContext playerContext) {
      super(playerContext);
   }

   @Override
   public Rotation getPrevRotation() {
      return this.ctx.playerRotations();
   }
}
