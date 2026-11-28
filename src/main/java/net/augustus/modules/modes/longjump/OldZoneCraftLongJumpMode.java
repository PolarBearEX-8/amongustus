package net.augustus.modules.modes.longjump;

import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.player.PlayerUtil;
import java.util.function.BiFunction;
import net.augustus.modules.movement.LongJump;
import net.augustus.settings.SettingGroup;

public class OldZoneCraftLongJumpMode extends AbstractLongJumpMode implements UpdateListener {
   private boolean boosted = false;

   public OldZoneCraftLongJumpMode(LongJump longJump, SettingGroup doubleValue) {
      super(longJump, doubleValue, "OldZoneCraft");
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(43);
   }

   @Override
   public void onEnable() {
      this.boosted = false;
      if (mc.thePlayer != null) {
         PlayerUtil.damageSelfShort();
      }
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new OldZoneCraftLongJumpMode((LongJump)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      event.setMoveForward(this.boosted ? 1.0F : 0.0F);
   }

   @Override
   public void onPreUpdate() {
      if (mc.thePlayer.hurtTime > 0) {
         mc.getTimer().timerSpeed = 1.2F;
         MoveUtil.setSpeed(0.48F);
         mc.thePlayer.motionY = 0.62F;
         this.boosted = true;
      } else {
         mc.getTimer().timerSpeed = 1.0F;
      }

      if (this.boosted && mc.thePlayer.fallDistance > 0.0F) {
         mc.thePlayer.motionY = -0.1;
      }
   }

}
