package net.augustus.modules.modes.velocity;

import net.augustus.events.EventMotion;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.MotionListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class ReverseVelocityMode extends VelocityMode implements UpdateListener, MotionListener, PacketListener {
   public final DoubleValue xzVelocityStrength;
   public final DoubleValue yVelocityStrength;
   public final DoubleValue reverseStart;
   public final BooleanValue reverseStrafe;
   public final RangeSetting reverseStrafeEnd;

   public ReverseVelocityMode(Velocity velocity, SettingGroup doubleValue) {
       super(velocity, doubleValue, "Reverse");
      this.xzVelocityStrength = new DoubleValue(this.parentModule, "XZ-Velocity Strength", 100.0, 0.0, 100.0, 0).visibleWhen(() -> this.isSelected());
      this.yVelocityStrength = new DoubleValue(this.parentModule, "Y-Velocity Strength", 100.0, 0.0, 100.0, 0).visibleWhen(() -> this.isSelected());
      this.reverseStart = new DoubleValue(this.parentModule, "ReverseStart", 9.0, 1.0, 10.0, 0, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.reverseStrafe = (BooleanValue)new BooleanValue(this.parentModule, "ReverseStrafe", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.reverseStrafeEnd = (new RangeSetting(this.parentModule, "ReverseStrafeEnd", "ReverseStrafeStart", 9.0, 7.0, 1.0, 10.0, 0, this.modeSetting)).withMaxFirst().visibleWhen(() -> this.isReverseStrafeRangeVisible());
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(26);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new ReverseVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (this.canApplyVelocity() && !event.isCancelled()) {
         Packet packet = event.getPacket();
         if (mc.thePlayer != null && packet instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)packet;
            if (packet2.getEntityID() == mc.thePlayer.getEntityId()) {
               packet2.setMotionX((int)(((double)packet2.getMotionX()) * (this.xzVelocityStrength.getValue(true) / 100.0)));
               packet2.setMotionY((int)(((double)packet2.getMotionX()) * (this.yVelocityStrength.getValue(true) / 100.0)));
               packet2.setMotionZ((int)(((double)packet2.getMotionZ()) * (this.xzVelocityStrength.getValue(true) / 100.0)));
            }
         }
      }
   }

   @Override
   public void onPreUpdate() {
      if (this.canApplyVelocity()) {
         if ((double)mc.thePlayer.hurtTime == this.reverseStart.getValue()) {
            mc.thePlayer.motionX *= -1.0;
            mc.thePlayer.motionZ *= -1.0;
            if (this.shouldReverseStrafe()) {
               MoveUtil.setSpeed((float)MoveUtil.getSpeed());
            }
         }
      }
   }

   @Override
   public void onPreMotion(EventMotion event) {
   }

   @Override
   public void onPostMotion() {
      if (this.canApplyVelocity()) {
         if (this.shouldReverseStrafe()) {
            MoveUtil.strafe();
         }
      }
   }

   private boolean shouldReverseStrafe() {
      if (this.reverseStrafe.getValue()) {
         if (((double)mc.thePlayer.hurtTime) <= this.reverseStrafeEnd.getMaxValue() && (double)mc.thePlayer.hurtTime >= this.reverseStrafeEnd.getMinValue() && mc.thePlayer.hurtTime > 0) {
            return true;
         }
      }

      return false;
   }

   private boolean isReverseStrafeRangeVisible() {
      return this.isSelected() && this.reverseStrafe.getValue();
   }

}
