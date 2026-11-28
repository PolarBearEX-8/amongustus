package baritone.api.behavior;

import baritone.api.utils.Rotation;
import baritone.api.behavior.look.IAimProcessor;

public interface ILookBehavior extends IBehavior {
   IAimProcessor getAimProcessor();

   void updateTarget(Rotation rotation, boolean flag);
}
