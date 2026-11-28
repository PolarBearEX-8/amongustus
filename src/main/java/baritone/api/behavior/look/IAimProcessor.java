package baritone.api.behavior.look;

import baritone.api.utils.Rotation;

public interface IAimProcessor {
   ITickableAimProcessor fork();

   Rotation peekRotation(Rotation rotation);
}
