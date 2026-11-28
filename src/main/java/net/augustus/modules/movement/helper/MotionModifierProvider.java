package net.augustus.modules.movement.helper;

import net.augustus.modules.modes.motion.MotionModifierEngine;
import net.augustus.modules.misc.Dummy;

public interface MotionModifierProvider {
   Dummy getDummy();

   MotionModifierEngine getMotionModifierEngine();
}
