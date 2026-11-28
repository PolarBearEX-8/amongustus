package net.augustus.modules.modes.longjump;

import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import java.util.function.BiFunction;
import net.augustus.modules.movement.LongJump;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.util.MathHelper;

public class NcpLongJumpMode extends AbstractLongJumpMode implements UpdateListener {
   public final DoubleValue ncpSpeed;

   public NcpLongJumpMode(LongJump longJump, SettingGroup doubleValue) {
       super(longJump, doubleValue, "NCP");
      this.ncpSpeed = new DoubleValue(this.parentModule, "NCPSpeed", 4.0, 1.0, 10.0, 2, this.modeSetting).visibleWhen(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(43);
   }

   @Override
   public void onEnable() {
      ((LongJump)this.parentModule).pendingJump = true;
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new NcpLongJumpMode((LongJump)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreUpdate() {
      if (((LongJump)this.parentModule).pendingJump) {
         mc.thePlayer.jump();
         MoveUtil.setSpeed((float)(MathHelper.clamp_double(this.ncpSpeed.getValue(), 0.0, 50.0) * MoveUtil.getSpeed()));
         ((LongJump)this.parentModule).pendingJump = false;
      } else {
         MoveUtil.setSpeed((float)MoveUtil.getSpeed());
      }
   }

}
