package net.augustus.modules.modes.faststop;

import net.augustus.Augustus;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventSafeWalk;
import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.FastStop;
import net.augustus.settings.SettingGroup;

public abstract class FastStopMode extends AbstractSubMode {
   public boolean isMovingInput;
   public boolean wasMovingInput;

   public FastStopMode(FastStop fastStop, SettingGroup doubleValue, String str) {
      super(fastStop, doubleValue, str);
   }

   public void updateMoveInputState(EventMoveInput event) {
      this.wasMovingInput = this.isMovingInput;
      this.isMovingInput = event.getMoveForward() != 0.0F || event.getMoveStrafe() != 0.0F;
   }

   public abstract void onUpdate();

   public abstract void onMoveInput(EventMoveInput event);

   public abstract void onSafeWalk(EventSafeWalk event);

   public boolean isNotInLiquid() {
      return !mc.thePlayer.isInLava() && !mc.thePlayer.isInWater();
   }

   public boolean justStoppedMoving() {
      return !this.isMovingInput && this.wasMovingInput && this.isAllowedByScaffold();
   }

   public boolean isAllowedByScaffold() {
      return !((FastStop)this.parentModule).notWhileFlying.getValue() || !Augustus.getInstance().getModuleManager().fly.isToggled();
   }

   public boolean isPlayerMoving() {
      return MoveUtil.hasHorizontalMotion();
   }




}
