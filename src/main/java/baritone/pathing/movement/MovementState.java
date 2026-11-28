package baritone.pathing.movement;

import baritone.api.pathing.movement.MovementStatus;
import baritone.api.utils.input.Input;
import java.util.HashMap;
import java.util.Map;

public class MovementState {
   private MovementStatus status;
   private MovementTarget target = new MovementTarget();
   private final Map inputState = new HashMap();

   public Map getInputState() {
      return this.inputState;
   }

   public MovementState setStatus(MovementStatus movementStatus) {
      this.status = movementStatus;
      return this;
   }

   public MovementState setInput(Input input, boolean flag) {
      this.inputState.put(input, flag);
      return this;
   }

   public MovementStatus getStatus() {
      return this.status;
   }

   public MovementState setTarget(MovementTarget movementTarget) {
      this.target = movementTarget;
      return this;
   }

   public MovementTarget getTarget() {
      return this.target;
   }
}
