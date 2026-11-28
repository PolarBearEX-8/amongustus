package baritone.utils;

import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.event.events.TickEvent;
import baritone.api.utils.input.Input;
import baritone.behavior.Behavior;
import baritone.api.utils.IInputOverrideHandler;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.MovementInputFromOptions;

public final class InputOverrideHandler extends Behavior implements IInputOverrideHandler {
   private final Map inputForceStateMap = new HashMap();
   private final BlockBreakHelper blockBreakHelper;
   private final BlockPlaceHelper blockPlaceHelper;

   private boolean inControl() {
      for (Input input : new Input[]{Input.MOVE_FORWARD, Input.MOVE_BACK, Input.MOVE_LEFT, Input.MOVE_RIGHT, Input.SNEAK, Input.JUMP}) {
         if (this.isInputForcedDown(input)) {
            return true;
         }
      }

      return this.baritone.getPathingBehavior2().isPathing() || this.baritone != BaritoneAPI.getProvider().getPrimaryBaritone();
   }

   @Override
   public final void onTick(TickEvent tickEvent) {
      if (tickEvent.getType() != TickEvent.Type.OUT) {
         if (this.isInputForcedDown(Input.CLICK_LEFT)) {
            this.setInputForceState(Input.CLICK_RIGHT, false);
         }

         this.blockBreakHelper.tick(this.isInputForcedDown(Input.CLICK_LEFT));
         this.blockPlaceHelper.tick(this.isInputForcedDown(Input.CLICK_RIGHT));
         if (this.inControl()) {
            if (this.ctx.player().movementInput.getClass() != PlayerMovementInput.class) {
               this.ctx.player().movementInput = new PlayerMovementInput(this);
            }
         } else if (this.ctx.player().movementInput.getClass() == PlayerMovementInput.class) {
            this.ctx.player().movementInput = new MovementInputFromOptions(this.ctx.getMc().gameSettings);
         }
      }
   }

   @Override
   public final boolean isInputForcedDown(Input input) {
      return input == null ? false : (Boolean)this.inputForceStateMap.getOrDefault(input, false);
   }

   public InputOverrideHandler(Baritone baritone2) {
      super(baritone2);
      this.blockBreakHelper = new BlockBreakHelper(baritone2.getPlayerContext());
      this.blockPlaceHelper = new BlockPlaceHelper(baritone2.getPlayerContext());
   }

   @Override
   public final void clearAllKeys() {
      this.inputForceStateMap.clear();
   }

   public BlockBreakHelper getBlockBreakHelper() {
      return this.blockBreakHelper;
   }

   @Override
   public final void setInputForceState(Input input, boolean flag) {
      this.inputForceStateMap.put(input, flag);
   }
}
