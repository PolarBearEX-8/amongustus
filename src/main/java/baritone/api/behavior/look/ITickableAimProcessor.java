package baritone.api.behavior.look;

import baritone.api.utils.Rotation;

public interface ITickableAimProcessor extends IAimProcessor {
   void tick();

   Rotation nextRotation(Rotation rotation);

   void advance(int n);
}
