package net.minecraft.util;

import net.augustus.events.EventMoveInput;

public class MovementInput {
   public float moveStrafe;
   public float moveForward;
   public float rawMoveStrafe;
   public float rawMoveForward;
   public boolean jump;
   public boolean sneak;

   public EventMoveInput applyMoveFix(EventMoveInput event) {
      return null;
   }

   public void updatePlayerMoveState(float f, float f2) {
   }
}
