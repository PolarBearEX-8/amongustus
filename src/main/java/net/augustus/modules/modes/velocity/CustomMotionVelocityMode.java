package net.augustus.modules.modes.velocity;

import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class CustomMotionVelocityMode extends VelocityMode implements PacketListener, MoveEntityListener {
   public final DoubleValue xzMotionInAir;
   public final DoubleValue xzMotionOnGround;
   private boolean pendingVelocity;

   public CustomMotionVelocityMode(Velocity velocity, SettingGroup doubleValue) {
       super(velocity, doubleValue, "CustomMotion");
      this.xzMotionInAir = new DoubleValue(this.parentModule, "XZMotionInAir", 0.2, 0.0, 1.0, 4, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.xzMotionOnGround = new DoubleValue(this.parentModule, "XZMotionOnGround", 0.2, 0.0, 1.0, 4, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.pendingVelocity = true;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(20);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new CustomMotionVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S12PacketEntityVelocity && this.canApplyVelocity()) {
         if (((S12PacketEntityVelocity)packet).getEntityID() == mc.thePlayer.getEntityId()) {
            this.pendingVelocity = true;
         }
      }
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (this.pendingVelocity) {
         this.pendingVelocity = false;
         double direction = MoveUtil.getDirection();
         double d = mc.thePlayer.onGround ? this.xzMotionOnGround.getValue() : this.xzMotionInAir.getValue();
         event.setX(-(Math.sin(direction) * d));
         event.setZ(Math.cos(direction) * d);
      }
   }

}
