package net.augustus.modules.modes.highjump;

import net.augustus.events.EventMotion;
import net.augustus.events.listeners.MotionListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.player.PlayerUtil;
import java.util.function.BiFunction;
import net.augustus.modules.movement.HighJump;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class DamageHighJumpMode extends HighJumpMode implements MotionListener {
   public final DoubleValue damageHeight;

   public DamageHighJumpMode(HighJump highJump, SettingGroup doubleValue) {
      super(highJump, doubleValue, "Damage");
      this.damageHeight = new DoubleValue(this.parentModule, "DamageHeight", 1.0, 0.42, 5.0, 2, this.modeSetting).named("Height").visibleWhen(() -> this.isSelected());
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new DamageHighJumpMode((HighJump)obj, (SettingGroup)obj2);
   }

   @Override
   public void onEnable() {
      super.onEnable();
      if (mc.thePlayer != null) {
         PlayerUtil.damageSelfShort();
      }
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(18);
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (mc.thePlayer.onGround && mc.thePlayer.hurtTime != 0) {
         mc.thePlayer.motionY = this.damageHeight.getValue();
         this.groundTicks = 1;
      } else {
         this.groundTicks = 0;
      }
   }

}
