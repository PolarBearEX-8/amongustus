package net.augustus.modules.modes.highjump;

import net.augustus.events.EventMotion;
import net.augustus.events.listeners.MotionListener;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.movement.HighJump;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class VanillaHighJumpMode extends HighJumpMode implements MotionListener {
   public final DoubleValue vanillaHeight;

   public VanillaHighJumpMode(HighJump highJump, SettingGroup doubleValue) {
       super(highJump, doubleValue, "Vanilla");
      this.vanillaHeight = new DoubleValue(this.parentModule, "VanillaHeight", 1.0, 0.42, 5.0, 2, this.modeSetting).named("Height").visibleWhen(() -> this.isSelected());
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new VanillaHighJumpMode((HighJump)obj, (SettingGroup)obj2);
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(18);
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (this.isActivated() && mc.thePlayer.onGround) {
         mc.thePlayer.motionY = this.vanillaHeight.getValue();
      }
   }

}
