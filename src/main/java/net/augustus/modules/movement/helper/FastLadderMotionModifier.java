package net.augustus.modules.movement.helper;

import net.augustus.modules.modes.fastladder.MotionModifierFastLadderMode;
import net.augustus.modules.modes.motion.MotionModifierEngine;
import net.augustus.modules.Module;
import net.augustus.modules.movement.FastLadder;

public class FastLadderMotionModifier extends MotionModifierEngine {
   public final FastLadder fastLadder;
   public final MotionModifierFastLadderMode motionModifierFastLadderMode;

   public FastLadderMotionModifier(MotionModifierFastLadderMode mode, Module module, FastLadder fastLadder2) {
       super(module);
       this.motionModifierFastLadderMode = mode;
       this.fastLadder = fastLadder2;
   }

   @Override
   public boolean isOnLadder() {
      return this.fastLadder.isOnClimbable();
   }
}
