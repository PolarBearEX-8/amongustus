package baritone.utils;

import baritone.api.utils.input.Input;
import net.minecraft.util.MovementInput;

public class PlayerMovementInput extends MovementInput {
   private final InputOverrideHandler handler;

   @Override
   public void updatePlayerMoveState(float f, float f2) {
      this.moveStrafe = 0.0F;
      this.moveForward = 0.0F;
      this.jump = this.handler.isInputForcedDown(Input.JUMP);
      if (this.handler.isInputForcedDown(Input.MOVE_FORWARD)) {
         this.moveForward++;
      }

      if (this.handler.isInputForcedDown(Input.MOVE_BACK)) {
         this.moveForward--;
      }

      if (this.handler.isInputForcedDown(Input.MOVE_LEFT)) {
         this.moveStrafe++;
      }

      if (this.handler.isInputForcedDown(Input.MOVE_RIGHT)) {
         this.moveStrafe--;
      }

      if (this.sneak = this.handler.isInputForcedDown(Input.SNEAK)) {
         this.moveStrafe = (float)((double)this.moveStrafe * 0.3);
         this.moveForward = (float)((double)this.moveForward * 0.3);
      }
   }

   public PlayerMovementInput(InputOverrideHandler inputOverrideHandler) {
      this.handler = inputOverrideHandler;
   }
}
