package net.augustus.modules.modes.jesus;

import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Jesus;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.util.MathHelper;

public class JumpJesusMode extends AbstractJesusMode implements TickListener {
   public final DoubleValue motion;
   public final BooleanValue onlyMove;
   private final TimeHelper timeHelper;

   public JumpJesusMode(Jesus jesus, SettingGroup doubleValue) {
      super(jesus, doubleValue, "Jump");
      this.motion = new DoubleValue(this.parentModule, "Motion", 0.6, 0.01, 2.0, 2, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.onlyMove = (BooleanValue)new BooleanValue(this.parentModule, "OnlyMove", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.timeHelper = new TimeHelper();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onTick() {
      if (this.shouldApplyJesus() && mc.thePlayer.isInWater()) {
         if (!this.onlyMove.getValue() || MoveUtil.isMoving()) {
            mc.thePlayer.motionY = this.motion.getValue();
            if (this.timeHelper.reached(200L) && mc.thePlayer.isSprinting()) {
               float f = mc.thePlayer.rotationYaw * (float) (Math.PI / 180.0);
               mc.thePlayer.motionX = mc.thePlayer.motionX - (double)(MathHelper.sin(f) * 0.2F);
               mc.thePlayer.motionZ = mc.thePlayer.motionZ + (double)(MathHelper.cos(f) * 0.2F);
               MoveUtil.strafe();
            }
         }

         this.timeHelper.reset();
      }
   }

}
