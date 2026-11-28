package baritone.api.utils;

import baritone.api.behavior.IBehavior;
import baritone.api.utils.input.Input;

public interface IInputOverrideHandler extends IBehavior {
   void clearAllKeys();

   boolean isInputForcedDown(Input input);

   void setInputForceState(Input input, boolean flag);
}
